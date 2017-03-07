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
package jp.co.broadleaf.common.property;

/**
 * <pre>
 * プロパティアクセス例外クラスです。
 * </pre>
 */
public class PropertyResolveException extends RuntimeException {

  /** serialVersionUID */
  private static final long serialVersionUID = -1195629061066626218L;

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param message メッセージ
   */
  public PropertyResolveException(String message) {
    super(message);
  }

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param message メッセージ
   * @param cause 原因
   */
  public PropertyResolveException(String message, Throwable cause) {
    super(message, cause);
  }
}
