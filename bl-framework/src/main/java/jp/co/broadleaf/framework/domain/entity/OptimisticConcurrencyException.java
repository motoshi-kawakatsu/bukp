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
package jp.co.broadleaf.framework.domain.entity;

/**
 * 楽観的排他ロックチェック例外クラスです。
 */
public class OptimisticConcurrencyException extends RuntimeException {

  /** serialVersionUID */
  private static final long serialVersionUID = 7485495935022797594L;

  /**
   * コンストラクタ
   */
  public OptimisticConcurrencyException() {
  }

  /**
   * コンストラクタ
   * 
   * @param cause エラーの原因
   */
  public OptimisticConcurrencyException(Throwable cause) {
    super(cause);
  }

}
