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

import java.util.Locale;

/**
 * メッセージが見つかれない例外クラス
 */
@SuppressWarnings("serial")
public class MessageNotFoundException extends RuntimeException {

  /**
   * コンストラクタ
   * 
   * @param code メッセージコード
   * @param locale ロケール
   */
  public MessageNotFoundException(String code, Locale locale) {
    super("No message found under code '" + code + "' for locale '" + locale + "'.");
  }

  /**
   * コンストラクタ
   * 
   * @param code メッセージコード
   */
  public MessageNotFoundException(String code) {
    super("No message found under code '" + code + "' for locale '" + Locale.getDefault() + "'.");
  }

}
