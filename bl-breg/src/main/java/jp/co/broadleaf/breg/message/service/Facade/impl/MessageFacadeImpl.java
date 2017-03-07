//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:曹　緩緩
// 作成日:2017/02/15    修正内容:共通メッセージ:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.message.service.Facade.impl;

import jp.co.broadleaf.breg.common.entity.MessageCommon;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.breg.message.service.Facade.MessageFacade;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 共通メッセージFacadeクラス.
 * </pre>
 */
public class MessageFacadeImpl implements MessageFacade {

  /**
   * <pre>
   * 共通メッセージマップの取得.
   * </pre>
   * 
   * @return 共通メッセージマップ
   */
  @Override
  public HashMap<String, String> getMessageMap() {
    // メッセージマップ(メッセージId,メッセージタイトル：メッセージ内容)
    HashMap<String, String> messageMap = new HashMap<String, String>();
    // 共通メッセージリスト情報を取得
    List<MessageCommon> messageList = messageService.getMessageList();
    if (messageList != null) {
      // メッセージマップを設定
      for (int index = 0; index < messageList.size(); index++) {
        MessageCommon message = messageList.get(index);
        String key = message.getMsgId();
        String value = message.getMsgTitle() + MESSAGETITLE_CONTENTS + message.getMsgContents();
        messageMap.put(key, value);
      }
    }

    return messageMap;
  }

  /**
   * <pre>
   * 共通メッセージタイトルの取得.
   * </pre>
   * 
   * @param messageId 共通メッセージId
   * @param messageMap 共通メッセージマップ
   * @return 共通メッセージタイトル
   */
  @Override
  public String getMessageTitle(String messageId, HashMap<String, String> messageMap) {
    String messageTitle = "";
    if (messageMap == null || messageMap.isEmpty()) {
      return messageTitle;
    }
    // メッセージマップからメッセージを取得
    String message = messageMap.get(messageId);
    if (message == null) {
      return messageTitle;
    }
    // メッセージタイトルを取得
    return message.split(MESSAGETITLE_CONTENTS)[0];
  }

  /**
   * <pre>
   * 共通メッセージタ内容の取得.
   * </pre>
   * 
   * @param messageId 共通メッセージId
   * @param messageMap 共通メッセージマップ
   * @return 共通メッセージ内容
   */
  @Override
  public String getMessageContents(String messageId, HashMap<String, String> messageMap) {
    String messageContents = "";
    if (messageMap == null || messageMap.isEmpty()) {
      return messageContents;
    }
    // メッセージマップからメッセージを取得
    String message = messageMap.get(messageId);
    if (message == null) {
      return messageContents;
    }
    // メッセージ内容を取得
    String[] messageArr = message.split(MESSAGETITLE_CONTENTS);
    if (messageArr.length > 0) {
      messageContents = messageArr[1];
    }

    return messageContents;
  }

  /**
   * <pre>
   * 画面表示用メッセージ内容の取得.
   * </pre>
   * 
   * @param messageIdList 共通メッセージIdリスト
   * @param messageMapALL 共通メッセージマップ
   * @return 画面表示用共通メッセージ内容
   */
  @Override
  public HashMap<String, String> getMessageContentsMap(List<String> messageIdList,
                                                       HashMap<String, String> messageMapALL) {
    HashMap<String, String> messageMap = new HashMap<>();
    for (String messageId : messageIdList) {
      messageMap.put(messageId, getMessageContents(messageId, messageMapALL));
    }
    return messageMap;
  }

  /** メッセージタイトルと内容間記号 */
  private static final String MESSAGETITLE_CONTENTS = "-";

  /** 共通メッセージサービス */
  private MessageService messageService;

  /**
   * <pre>
   * 共通メッセージ取得サービスを設定する.
   * </pre>
   * 
   * @param messageService 共通メッセージサービス
   */
  @Resource
  public void setMessageService(MessageService messageService) {
    this.messageService = messageService;
  }
}
