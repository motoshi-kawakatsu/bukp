//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.framework.message;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

/**
 * Springで実現のメッセージリゾルバ
 */
public class SpringMessageResolver implements MessageResolver {

  /**
   * SpringのMessageSource
   */
  private MessageSource messageSource;

  /**
   * SpringのMessageSourceを設定する
   * 
   * @param messageSource SpringのMessageSource
   */
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * メッセージコードなどより、メッセージコンテントを取得する
   * 
   * @param code メッセージコード
   * @param args メッセージパラメータ (パラメータ定義例： "{0}", "{1,date}", "{2,time}")
   * @param defaultMessage デフォルトメッセージコンテント
   * @param locale ロケール
   * @return メッセージコンテント、見つかれない場合、デフォルトメッセージコンテントを戻る
   */
  @Override
  public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
    return messageSource.getMessage(code, args, defaultMessage, locale);
  }

  /**
   * メッセージコードなどより、メッセージコンテントを取得する
   * 
   * @param code メッセージコード
   * @param args メッセージパラメータ (パラメータ定義例： "{0}", "{1,date}", "{2,time}")
   * @param locale ロケール
   * @throws MessageNotFoundException
   *           メッセージを見つかれない場合、MessageNotFoundExceptionをスローする
   * @return メッセージコンテント
   */
  @Override
  public String getMessage(String code, Object[] args, Locale locale) throws MessageNotFoundException {
    try {
      return messageSource.getMessage(code, args, locale);
    } catch (NoSuchMessageException ex) {
      if (locale != null) {
        throw new MessageNotFoundException(code, locale);
      } else {
        throw new MessageNotFoundException(code);
      }
    }
  }

}
