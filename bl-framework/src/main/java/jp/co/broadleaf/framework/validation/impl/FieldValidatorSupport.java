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
import jp.co.broadleaf.framework.validation.FieldValidator;

/**
 * <pre>
 * フィールド検証
 * </pre>
 * 
 * @param <T> コア
 */
public class FieldValidatorSupport<T> implements FieldValidator {

  /** フィールド名 */
  private String fieldName;

  /** フィールド値 */
  private T fieldValue;

  /** フィールドエラー */
  private FieldError fieldError;

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param fieldName フィールド名
   * @param fieldValue フィールド値
   */
  public FieldValidatorSupport(String fieldName, T fieldValue) {
    this.fieldName = fieldName;
    this.fieldValue = fieldValue;
  }

  /**
   * フィールド有効が判断する
   * 
   * @return 判断結果
   */
  @Override
  public boolean isValid() {
    return fieldError == null;
  }

  /**
   * 検証例外用のフィールドエラーを取得する
   * 
   * @return 検証例外用のフィールドエラー
   */
  @Override
  public FieldError getError() {
    return fieldError;
  }

  /**
   * 検証例外用のフィールドエラーを設定する
   * 
   * @param error エラーデータ
   */
  protected void setError(FieldError error) {
    this.fieldError = error;
  }

  /**
   * fieldNameを取得する
   * 
   * @return フィールド名
   */
  public String getFieldName() {
    return fieldName;
  }

  /**
   * fieldValueを取得する
   * 
   * @return フィールド値
   */
  public T getFieldValue() {
    return fieldValue;
  }

  /**
   * FieldErrorをセットする
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   */
  protected void setFieldError(String errorCode, String... errorArguments) {
    setError(new FieldError(getFieldName(), errorCode, errorArguments));
  }

}
