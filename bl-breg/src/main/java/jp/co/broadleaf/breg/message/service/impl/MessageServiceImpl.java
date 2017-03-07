//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:趙　命ラン
// 作成日:2017/02/15    修正内容:MessageServiceImpl.java:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.message.service.impl;

import jp.co.broadleaf.breg.common.entity.MessageCommon;
import jp.co.broadleaf.breg.common.entity.MessageCommonId;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.StringFilter;
import jp.co.broadleaf.framework.dao.criteria.StringFilterBuilder;

import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * </pre>
 */
public class MessageServiceImpl implements MessageService {
  /** 符号 **/
  private static final String SYMBOL1 = "$1";
  /** 符号 **/
  private static final String SYMBOL2 = "$2";

  /** message DAO */
  private GenericDao<MessageCommon, MessageCommonId> messageCommonDao;

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param messageCommonDao message DAO
   */
  @Resource
  public void setMessageCommonDao(GenericDao<MessageCommon, MessageCommonId> messageCommonDao) {
    this.messageCommonDao = messageCommonDao;
  }

  /**
   * <pre>
   * messageの取得.
   * </pre>
   * 
   * @param msgId String
   * @return messageContents String
   */
  public String messageInfo(String msgId) {
    // msgIdによりデータを取得
    StringFilter ftMsgId = StringFilterBuilder.equals(MessageCommon.MSG_ID, msgId);
    Filter filter = new AndFilter(ftMsgId);
    List<MessageCommon> messageList = messageCommonDao.findByFilter(filter, Limit.NO_LIMIT);
    if (messageList == null || messageList.isEmpty() ) {
      return null;
    }
    String messageContents = messageList.get(0).getMsgContents();
    return messageContents;
  }
  
  /**
   * <pre>
   * メッセージリスト情報の取得.
   * </pre>
   * 
   * @return メッセージリスト情報
   */
  public List<MessageCommon> getMessageList() {
    // メッセージリスト情報を取得
    List<MessageCommon> messageList = messageCommonDao.findAll();
    if (messageList == null || messageList.isEmpty() ) {
      return null;
    }
    return messageList;
  }

  /**
   * <pre>
   * 交替messageの取得.
   * </pre>
   * 
   * @param attributeName1 置換文字列1
   * @param attributeName2 置換文字列2
   * @param bregMessageCodes messageId
   * @return String
   */
  public String getMessage(String attributeName1, String attributeName2, String bregMessageCodes) {
    String errorMeassage = "";
    if (messageInfo(bregMessageCodes) != null) {
      if (messageInfo(bregMessageCodes).contains(SYMBOL1) && attributeName1 != null) {
        errorMeassage = messageInfo(bregMessageCodes).replace(SYMBOL1, attributeName1);
        if (messageInfo(bregMessageCodes).contains(SYMBOL2) && attributeName2 != null) {
          errorMeassage = errorMeassage.replace(SYMBOL2, attributeName2);
        }
      } else if (messageInfo(bregMessageCodes).contains(SYMBOL2) && attributeName2 != null) {
        errorMeassage = messageInfo(bregMessageCodes).replace(SYMBOL2, attributeName2);
      } else {
        errorMeassage = messageInfo(bregMessageCodes);
      }
    }
    return errorMeassage;
  }
}
