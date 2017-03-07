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

import jp.co.broadleaf.framework.exception.FieldError;

/**
 * <pre>
 * フィールド検証
 * </pre>
 */
public interface FieldValidator {

  /**
   * フィールド有効が判断する
   * 
   * @return 判断結果
   */
  boolean isValid();

  /**
   * 検証例外用のフィールドエラーを取得する
   * 
   * @return 検証例外用のフィールドエラー
   */
  FieldError getError();
}