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
package jp.co.broadleaf.framework.validation;

import jp.co.broadleaf.framework.validation.impl.DefaultFormValidator;

/**
 * <pre>
 * フォーム検証工場
 * </pre>
 */
public abstract class FormValidatorFactory {

  /**
   * デフォルトフォーム検証を作成する
   * 
   * @return デフォルトフォーム検証
   */
  public static FormValidator create() {
    return new DefaultFormValidator();
  }
}
