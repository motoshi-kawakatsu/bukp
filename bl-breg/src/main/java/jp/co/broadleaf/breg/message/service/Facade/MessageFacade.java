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
package jp.co.broadleaf.breg.message.service.Facade;

import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 * 共通メッセージFacadeインタフェース.
 * </pre>
 */
public interface MessageFacade {

  /**
   * <pre>
   * 共通メッセージマップの取得.
   * </pre>
   * 
   * @return 共通メッセージマップ
   */
  HashMap<String, String> getMessageMap();

  /**
   * <pre>
   * 共通メッセージタイトルの取得.
   * </pre>
   * 
   * @param messageId 共通メッセージId
   * @param messageMap 共通メッセージマップ
   * @return 共通メッセージタイトル
   */
  String getMessageTitle(String messageId, HashMap<String, String> messageMap);

  /**
   * <pre>
   * 共通メッセージ内容の取得.
   * </pre>
   * 
   * @param messageId 共通メッセージId
   * @param messageMap 共通メッセージマップ
   * @return 共通メッセージ内容
   */
  String getMessageContents(String messageId, HashMap<String, String> messageMap);

  /**
   * <pre>
   * 画面表示用メッセージ内容の取得.
   * </pre>
   * 
   * @param messageIdList 共通メッセージIdリスト
   * @param messageMapALL 共通メッセージマップ
   * @return 画面表示用共通メッセージ内容
   */
  HashMap<String, String> getMessageContentsMap(List<String> messageIdList, HashMap<String, String> messageMapALL);
}
