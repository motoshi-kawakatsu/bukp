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
package jp.co.broadleaf.framework.exception;

import java.util.List;

/**
 * 検証例外クラス
 */
public class ValidationException extends RuntimeException {

  /** serialVersionUID */
  private static final long serialVersionUID = 5755676169172381515L;

  /**
   * フィールドエラー
   */
  private FieldError[] fieldErrors;

  /**
   * コンストラクタ
   */
  public ValidationException() {

  }

  /**
   * コンストラクタ
   * 
   * @param fieldError フィールドエラー
   */
  public ValidationException(FieldError fieldError) {
    this.fieldErrors = new FieldError[] { fieldError };
  }

  /**
   * コンストラクタ
   * 
   * @param fieldErrors フィールドエラー
   */
  public ValidationException(FieldError[] fieldErrors) {
    this.fieldErrors = fieldErrors;
  }

  /**
   * コンストラクタ
   * 
   * @param fieldErrors フィールドエラー
   */
  public ValidationException(List<FieldError> fieldErrors) {
    if (fieldErrors != null) {
      this.fieldErrors = fieldErrors.toArray(new FieldError[0]);
    }
  }

  /**
   * フィールドエラーを取得する
   * 
   * @return フィールドエラー
   */
  public FieldError[] getFieldErrors() {
    return fieldErrors;
  }
}
