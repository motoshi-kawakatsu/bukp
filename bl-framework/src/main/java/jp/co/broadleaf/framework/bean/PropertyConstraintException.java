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
 * <pre>
 * プロパティ制約チェック例外クラスです。
 * </pre>
 */
public class PropertyConstraintException extends RuntimeException {

  /** serialVersionUID */
  private static final long serialVersionUID = 4041203205462299385L;

  /**
   * コンストラクタ
   * 
   * @param message メッセージ
   */
  public PropertyConstraintException(String message) {
    super(message);
  }
}
