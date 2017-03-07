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
package jp.co.broadleaf.common.message;

import java.util.Date;

/**
 * <pre>
 * Broadleafプロジェクト用メッセージリゾルバクラスです。
 * </pre>
 */
public interface MessageResolver {

  /**
   * メッセージを取得する.
   * 
   * @param messageId メッセージID
   * @param applyDate 適用日
   * @param localeId ロケールID
   * @param messageParameters 置換文字列
   * @return メッセージ
   */
  MessageInfo getMessage(String messageId, Date applyDate, String localeId, String... messageParameters);

  /**
   * メッセージを取得する.
   * 
   * @param messageId メッセージID
   * @param applyDate 適用日
   * @param messageParameters 置換文字列
   * @return メッセージ
   */
  MessageInfo getMessage(String messageId, Date applyDate, String... messageParameters);

  /**
   * メッセージを取得する.
   * 
   * @param messageId メッセージID
   * @param localeId ロケールID
   * @param messageParameters 置換文字列
   * @return メッセージ
   */
  MessageInfo getMessage(String messageId, String localeId, String... messageParameters);

  /**
   * メッセージを取得する.
   * 
   * @param messageId メッセージID
   * @param messageParameters 置換文字列
   * @return メッセージ
   */
  MessageInfo getMessage(String messageId, String... messageParameters);
}
