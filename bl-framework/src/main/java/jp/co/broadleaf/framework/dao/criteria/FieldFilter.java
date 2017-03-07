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
package jp.co.broadleaf.framework.dao.criteria;

import java.util.Arrays;

/**
 * フィールドフィルタの定義クラス
 * 
 * @param <T> パラメータ
 * @param <O> 演算子
 */
public abstract class FieldFilter<T, O> implements Filter {

  /**
   * フィールド
   */
  private String field;

  /**
   * 演算子
   */
  private O operator;

  /**
   * パラメータ配列
   */
  private T[] values;

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param field フィールド
   * @param operation 演算子
   * @param values パラメータ
   */
  @SuppressWarnings("unchecked")
  public FieldFilter(String field, O operation, T... values) {
    if (field == null) {
      throw new IllegalArgumentException("Field must not be null");
    }
    if (operation == null) {
      throw new IllegalArgumentException("Operator must not be null");
    }
    this.field = field;
    this.operator = operation;
    this.values = values;
  }

  /**
   * フィールドの取得
   * 
   * @return フィールド
   */
  public String getField() {
    return field;
  }

  /**
   * 演算子の取得
   * 
   * @return 演算子
   */
  public O getOperator() {
    return operator;
  }

  /**
   * パラメータの取得
   * 
   * @return パラメータ
   */
  public T getValue() {
    T value = null;
    if (values != null && values.length > 0) {
      value = values[0];
    }
    return value;
  }

  /**
   * パラメータ配列の取得
   * 
   * @return パラメータ配列
   */
  public T[] getValues() {
    return values;
  }

  /**
   * 検証処理
   */
  abstract void validate();

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((field == null) ? 0 : field.hashCode());
    result = prime * result + ((operator == null) ? 0 : operator.hashCode());
    result = prime * result + Arrays.hashCode(values);
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    @SuppressWarnings("rawtypes")
    FieldFilter other = (FieldFilter) obj;
    if (field == null) {
      if (other.field != null)
        return false;
    } else if (!field.equals(other.field))
      return false;
    if (operator == null) {
      if (other.operator != null)
        return false;
    } else if (!operator.equals(other.operator))
      return false;
    if (!Arrays.equals(values, other.values))
      return false;
    return true;
  }

}
