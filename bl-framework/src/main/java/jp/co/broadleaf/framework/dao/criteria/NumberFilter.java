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

/**
 * 数字型フィルタの定義クラス
 */
public class NumberFilter extends FieldFilter<Number, NumberFilterOp> {

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param field フィールド
   * @param operation 演算子
   * @param values パラメータ
   */
  public NumberFilter(String field, NumberFilterOp operation, Number... values) {
    super(field, operation, values);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(FilterVisitor visitor) {
    visitor.visitNumberFilter(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void validate() {
    if (null == getValue() && NumberFilterOp.IsNull != getOperator() && NumberFilterOp.IsNotNull != getOperator()) {
      throw new IllegalArgumentException("NumberFilter value (field=" + getField() + ") must not be null");
    }
  }
}
