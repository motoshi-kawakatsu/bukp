//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/24   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.common.message.impl;

import jp.co.broadleaf.common.cache.CacheKeySpecs;
import jp.co.broadleaf.common.cache.CacheKeyUtils;
import jp.co.broadleaf.common.message.Message;
import jp.co.broadleaf.common.message.MessageId;
import jp.co.broadleaf.common.message.MessageInfo;
import jp.co.broadleaf.common.message.MessageResolver;
import jp.co.broadleaf.common.message.MessageSpecs;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.FieldDescOrder;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.Order;
import jp.co.broadleaf.framework.dao.criteria.StringFilter;
import jp.co.broadleaf.framework.dao.criteria.StringFilterBuilder;
import jp.co.broadleaf.framework.persistence.jdbc.JdbcDomainEntityManager;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * Broadleafプロジェクト用メッセージリゾルバ実現用クラスです。
 * </pre>
 */
public class MessageResolverImpl implements MessageResolver {

  /**
   * Redisテンプレート
   */
  private RedisTemplate<String, List<MessageInfo>> redisTemplate;

  /**
   * データストドメインエンティティマネージ
   */
  private JdbcDomainEntityManager<Message, MessageId> jdbcDomainEntityManager;

  /**
   * メッセージを取得する.
   * 
   * @param messageId メッセージID
   * @param applyDate 適用日
   * @param localeId ロケールID
   * @param messageParameters 置換文字列
   * @return メッセージ
   */
  @Override
  public MessageInfo getMessage(String messageId, Date applyDate, String localeId, String... messageParameters) {
    // パラメータチェック
    Validate.notNull(messageId, "MessageId is required");
    Validate.notNull(applyDate, "ApplyDate is required");
    Validate.notNull(localeId, "LocaleId is required");

    // キャッシュからメッセージID、ロケールIDよりメッセージリストを取得する
    List<MessageInfo> messages = getCachedMessages(messageId, localeId);
    if (CollectionUtils.isEmpty(messages)) {
      // キャッシュが存在しなければ、実のjdbcからメッセージを検索する
      messages = getJdbcMessages(messageId, localeId);
      if (CollectionUtils.isNotEmpty(messages)) {
        // 検索データがあれば、検索データがキャッシュに格納する
        cacheMessages(messageId, localeId, messages);
      }
    }
    // 適用日より、メッセージを検索する
    return findMessageByApplyDate(messages, messageId, localeId, applyDate, messageParameters);
  }

  /**
   * キャッシュからメッセージID、ロケールIDよりメッセージリストを取得する
   * 
   * @param messageId メッセージID
   * @param localeId ロケールID
   * @return メッセージリスト
   */
  private List<MessageInfo> getCachedMessages(String messageId, String localeId) {
    String key = getMessageCacheKey(messageId, localeId);
    List<MessageInfo> messages = redisTemplate.boundValueOps(key).get();
    return messages;
  }

  /**
   * メッセージデータがキャッシュに格納する
   * 
   * @param messageId メッセージID
   * @param localeId ロケールID
   * @param messages メッセージデータ
   */
  private void cacheMessages(String messageId, String localeId, List<MessageInfo> messages) {
    String key = getMessageCacheKey(messageId, localeId);
    redisTemplate.boundValueOps(key).set(messages);
  }

  /**
   * メッセージキャッシュキーを取得する。
   * 
   * @param messageId メッセージID
   * @param localeId ロケールID
   * @return キャッシュキー
   */
  private String getMessageCacheKey(String messageId, String localeId) {
    return CacheKeyUtils.getCacheKey(CacheKeySpecs.MESSAGE_SCHEMA, new String[] { messageId, localeId });
  }

  /**
   * 適用日より、メッセージを検索する
   * 
   * @param messageList メッセージリスト
   * @param messageId メッセージID
   * @param localeId ロケールID
   * @param applyDate 適用日
   * @param messageParameters 置換文字列
   * @return メッセージ
   */
  private MessageInfo findMessageByApplyDate(List<MessageInfo> messageList, String messageId, String localeId,
                                             Date applyDate, String... messageParameters) {
    // 適用日より、メッセージを検索する
    MessageInfo matchedMessage = null;
    if (messageList != null) {
      for (MessageInfo message : messageList) {
        if (applyDate.compareTo(message.getApplyEndDate()) <= 0
            && applyDate.compareTo(message.getApplyStartDate()) >= 0) {
          matchedMessage = message;
          break;
        }
      }
    }
    // メッセージマスタからもメッセージが取得できない場合、固定文言用メッセージを返す.
    if (matchedMessage == null) {
      matchedMessage = getMessageWhenMessageNotFound(messageId, localeId);
    }
    // 適用開始日、適用終了日を除去して、かつ置換文字列を代入して返す。
    return new MessageInfo(matchedMessage.getId(), matchedMessage.getType(), matchedMessage.getTitle(),
        handleMessagePlaceHolder(matchedMessage.getContents(), messageParameters), null, null);
  }

  /**
   * メッセージマスタからもメッセージが取得できない場合、固定文言用メッセージを返す.
   * 
   * @param messageId メッセージID
   * @param localeId ロケールID
   * @return 固定文言用メッセージ
   */
  private MessageInfo getMessageWhenMessageNotFound(String messageId, String localeId) {
    if (messageId.startsWith(MessageSpecs.ERROR_MESSAGE_PREFIX)) {
      return new MessageInfo(messageId, MessageSpecs.ERROR_MESSAGE_TYPE, E_JA_DEFAULT_TITLE, E_JA_MESSAGE_NOT_FOUND,
          null, null);
    } else if (messageId.startsWith(MessageSpecs.WARN_MESSAGE_PREFIX)) {
      return new MessageInfo(messageId, MessageSpecs.WARN_MESSAGE_TYPE, W_JA_DEFAULT_TITLE, W_JA_MESSAGE_NOT_FOUND,
          null, null);
    } else if (messageId.startsWith(MessageSpecs.INFO_MESSAGE_PREFIX)) {
      return new MessageInfo(messageId, MessageSpecs.INFO_MESSAGE_TYPE, I_JA_DEFAULT_TITLE, I_JA_MESSAGE_NOT_FOUND,
          null, null);
    } else {
      return new MessageInfo(messageId, MessageSpecs.ERROR_MESSAGE_TYPE, E_JA_DEFAULT_TITLE, E_JA_MESSAGE_NOT_FOUND,
          null, null);
    }
  }

  /**
   * メッセージ中のプレースフォルダを置換する。
   * 
   * @param message メッセージ
   * @param messageParameters 置換文字列
   * @return 置換した後のメッセージ
   */
  public String handleMessagePlaceHolder(final String message, final String... messageParameters) {
    if (messageParameters == null) {
      return message;
    }
    String text = message;
    int index = MessageSpecs.MESSAGE_PLACEHOLDER_START_INDEX;
    for (String replacement : messageParameters) {
      text = text.replaceAll(MessageSpecs.getMessagePlaceHolderRegex(index), replacement);
      index++;
    }
    return text;
  }

  /**
   * 実のjdbcからメッセージを検索する
   * 
   * @param messageId メッセージID
   * @param localeId ロケールID
   * @return 検索データ
   */
  private List<MessageInfo> getJdbcMessages(String messageId, String localeId) {
    // メッセージを検索する
    StringFilter messageIdFilter = StringFilterBuilder.equals(Message.MSG_ID, messageId);
    StringFilter localeIdFilter = StringFilterBuilder.equals(Message.LOCALE_ID, localeId);
    NumberFilter logicalUndeleteFilter = NumberFilterBuilder.equals(Message.LOGICAL_DEL_DIV, Message.LOGICAL_UNDELETED);
    Filter filter = new AndFilter(messageIdFilter, localeIdFilter, logicalUndeleteFilter);
    Order order = new FieldDescOrder(Message.APPLY_START_DT);
    List<Message> result = jdbcDomainEntityManager.findByFilter(Message.class, filter, null, order);
    // DTOへ変換した後、戻す
    List<MessageInfo> messages = new ArrayList<MessageInfo>();
    for (Message item : result) {
      messages.add(new MessageInfo(item.getMsgId(), item.getMsgType(), item.getMsgTitle(), item.getMsgContents(),
          item.getApplyStartDt(), item.getApplyEndDt()));
    }
    return messages;
  }

  /**
   * メッセージを取得する.
   * 
   * @param messageId メッセージID
   * @param applyDate 適用日
   * @param messageParameters 置換文字列
   * @return メッセージ
   */
  @Override
  public MessageInfo getMessage(String messageId, Date applyDate, String... messageParameters) {
    return getMessage(messageId, applyDate, DEFAULT_LOCALE_ID, messageParameters);
  }

  /**
   * メッセージを取得する.
   * 
   * @param messageId メッセージID
   * @param localeId ロケールID
   * @param messageParameters 置換文字列
   * @return メッセージ
   */
  @Override
  public MessageInfo getMessage(String messageId, String localeId, String... messageParameters) {
    return getMessage(messageId, BroadleafUtils.getSystemDate(), localeId, messageParameters);
  }

  /**
   * メッセージを取得する.
   * 
   * @param messageId メッセージID
   * @param messageParameters 置換文字列
   * @return メッセージ
   */
  @Override
  public MessageInfo getMessage(String messageId, String... messageParameters) {
    return getMessage(messageId, BroadleafUtils.getSystemDate(), messageParameters);
  }

  /**
   * Redisテンプレートを設定する。
   *
   * @param redisTemplate Redisテンプレート
   */
  @Resource
  public void setRedisTemplate(RedisTemplate<String, List<MessageInfo>> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * jdbcドメインエンティティマネージを設定する。
   *
   * @param jdbcDomainEntityManager jdbcドメインエンティティマネージ
   */
  public void setJdbcDomainEntityManager(JdbcDomainEntityManager<Message, MessageId> jdbcDomainEntityManager) {
    this.jdbcDomainEntityManager = jdbcDomainEntityManager;
  }

  /**
   * デフォルトロケールID
   */
  private static final String DEFAULT_LOCALE_ID = "ja";
  /**
   * ロケールIDがja且つエラーメッセージを取得できない場合、固定文言のタイトル定義
   */
  private static final String E_JA_DEFAULT_TITLE = "エラー";
  /**
   * ロケールIDがja且つウォーニングメッセージを取得できない場合、固定文言のタイトル定義
   */
  private static final String W_JA_DEFAULT_TITLE = "警告";
  /**
   * ロケールIDがja且つインフォメッセージを取得できない場合、固定文言のタイトル定義
   */
  private static final String I_JA_DEFAULT_TITLE = "情報";
  /**
   * ロケールIDがja且つエラーメッセージを取得できない場合、固定文言の定義
   */
  private static final String E_JA_MESSAGE_NOT_FOUND = "依頼された処理実施中にエラーが発生しました。エラー詳細については、システム管理者に問合せください";
  /**
   * ロケールIDがja且つウォーニングメッセージを取得できない場合、固定文言の定義
   */
  private static final String W_JA_MESSAGE_NOT_FOUND = "依頼された処理は終了しました。ただし、処理中に一部エラーが発生している可能性があります。システム管理者に問合せください";
  /**
   * ロケールIDがja且つインフォメッセージを取得できない場合、固定文言の定義
   */
  private static final String I_JA_MESSAGE_NOT_FOUND = "依頼された処理は終了しました。ただし、処理中に一部エラーが発生している可能性があります。システム管理者に問合せください";

}
