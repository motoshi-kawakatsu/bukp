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
 * <pre>
 * ドメインエンティティ制約チェック例外クラスです。
 * </pre>
 */
public class EntityConstraintException extends RuntimeException {

  /** serialVersionUID */
  private static final long serialVersionUID = -3874867558680242702L;

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param message エラーメッセージ
   */
  public EntityConstraintException(String message) {
    super(message);
  }

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param message エラーメッセージ
   * @param cause エラー原因
   */
  public EntityConstraintException(String message, Throwable cause) {
    super(message, cause);
  }

}
