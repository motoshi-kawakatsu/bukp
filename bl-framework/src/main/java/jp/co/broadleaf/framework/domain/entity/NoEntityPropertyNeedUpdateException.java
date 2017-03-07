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
 * 更新用の項目が存在しない例外クラスです。
 */
public class NoEntityPropertyNeedUpdateException extends RuntimeException {

  /** serialVersionUID */
  private static final long serialVersionUID = 4260003328318776277L;

  /**
   * コンストラクタ
   */
  public NoEntityPropertyNeedUpdateException() {
    super("No entity property need to update.");
  }
}
