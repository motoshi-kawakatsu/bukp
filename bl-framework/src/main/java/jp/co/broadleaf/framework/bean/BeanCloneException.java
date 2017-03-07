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
 * Clone例外クラスです。
 * </pre>
 */
public class BeanCloneException extends RuntimeException {

  /** serialVersionUID */
  private static final long serialVersionUID = -3495779072930342275L;

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param message メッセージ
   */
  public BeanCloneException(String message) {
    super(message);
  }

  /**
   * コンストラクタ
   * 
   * @param cause 原因
   */
  public BeanCloneException(Throwable cause) {
    super(cause);
  }

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param message メッセージ
   * @param cause 原因
   */
  public BeanCloneException(String message, Throwable cause) {
    super(message, cause);
  }

}
