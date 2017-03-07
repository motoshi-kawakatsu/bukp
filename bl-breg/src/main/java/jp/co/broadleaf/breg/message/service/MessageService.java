//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:趙　命ラン
// 作成日:2017/02/15    修正内容:MessageService.java:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.message.service;

import jp.co.broadleaf.breg.common.entity.MessageCommon;

import java.util.List;

/**
 * <pre>
 * 共通メッセージサービス
 * </pre>
 */
public interface MessageService {

  /**
   * <pre>
   * messageの取得.
   * </pre>
   * 
   * @return messageContents
   * @param msgId msgId
   */
  String messageInfo(String msgId);
  
  /**
   * <pre>
   * メッセージリスト情報の取得.
   * </pre>
   * 
   * @return メッセージリスト情報
   */
  List<MessageCommon> getMessageList();

  /**
   * <pre>
   * 交替messageの取得.
   * </pre>
   * 
   * @param attributeName1 置換文字列
   * @param attributeName2 置換文字列
   * @param bregMessageCodes messageId
   * @return String
   */
  String getMessage(String attributeName1, String attributeName2, String bregMessageCodes);
}
