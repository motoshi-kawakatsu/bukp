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
package jp.co.broadleaf.framework.bean;

/**
 * プロパティアクセス例外クラスです。
 */
public class PropertyAccessException extends RuntimeException {

  /** serialVersionUID */
  private static final long serialVersionUID = 3891293135459712143L;

  /**
   * コンストラクタ
   * 
   * @param message メッセージ
   */
  public PropertyAccessException(String message) {
    super(message);
  }

}
