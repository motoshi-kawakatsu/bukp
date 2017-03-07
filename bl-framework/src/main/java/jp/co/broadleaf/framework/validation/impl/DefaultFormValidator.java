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
package jp.co.broadleaf.framework.validation.impl;

import jp.co.broadleaf.framework.exception.FieldError;
import jp.co.broadleaf.framework.exception.ValidationException;
import jp.co.broadleaf.framework.validation.FieldValidator;
import jp.co.broadleaf.framework.validation.FormValidator;
import jp.co.broadleaf.framework.validation.StringFieldValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * デフォルトフォームの検証
 * </pre>
 */
public class DefaultFormValidator implements FormValidator {

  /** フィールド検証list */
  private final List<FieldValidator> fieldValidators = new ArrayList<FieldValidator>();

  /**
   * フィールド検証listを設定する
   * 
   * @param fieldValidator フィールド検証データ
   * @param <T> フィールド検証コア
   * @return フィールド検証list
   */
  @Override
  public <T extends FieldValidator> T field(T fieldValidator) {
    fieldValidators.add(fieldValidator);
    return fieldValidator;
  }

  /**
   * コアを変更する
   * 
   * @param fieldName フィールド名
   * @param fieldValue フィールド値
   * @return 変更したコア
   */
  @Override
  public StringFieldValidator field(String fieldName, String fieldValue) {
    return field(new DefaultStringFieldValidator(fieldName, fieldValue));
  }

  /**
   * フィールドを検証する
   * 
   * @throws ValidationException 検証例外フィールドが存在する
   */
  @Override
  public void validate() throws ValidationException {
    List<FieldError> fieldErrors = new ArrayList<FieldError>();
    for (FieldValidator fieldValidator : fieldValidators) {
      if (!fieldValidator.isValid()) {
        fieldErrors.add(fieldValidator.getError());
      }
    }
    if (fieldErrors.size() > 0) {
      throw new ValidationException(fieldErrors);
    }
  }

  /**
   * フィールド検証リストを取得する。
   * 
   * @return フィールド検証リスト
   */
  protected List<FieldValidator> getFieldValidators() {
    return fieldValidators;
  }

}
