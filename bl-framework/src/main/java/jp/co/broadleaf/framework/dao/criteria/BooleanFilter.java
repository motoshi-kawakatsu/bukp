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
 * <pre>
 * 論理型フィルタの定義クラス
 * </pre>
 */
public class BooleanFilter extends FieldFilter<Boolean, BooleanFilterOp> {

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param field フィールド
   * @param operation 演算子
   * @param values パラメータ
   */
  public BooleanFilter(String field, BooleanFilterOp operation, Boolean... values) {
    super(field, operation, values);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(FilterVisitor visitor) {
    visitor.visitBooleanFilter(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void validate() {
    if (null == getValue() && BooleanFilterOp.IsNull != getOperator() && BooleanFilterOp.IsNotNull != getOperator()) {
      throw new IllegalArgumentException("BooleanFilter's value (field=" + getField() + ") must not be null");
    }
  }
}
