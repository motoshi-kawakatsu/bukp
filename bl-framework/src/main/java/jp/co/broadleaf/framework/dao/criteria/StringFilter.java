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
 * 文字型フィルタの定義クラス
 */
public class StringFilter extends FieldFilter<String, StringFilterOp> {

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param field フィールド
   * @param operation 演算子
   * @param values パラメータ
   */
  public StringFilter(String field, StringFilterOp operation, String... values) {
    super(field, operation, values);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(FilterVisitor visitor) {
    visitor.visitStringFilter(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void validate() {
    if (null == getValue() && StringFilterOp.IsNull != getOperator() && StringFilterOp.IsNotNull != getOperator()) {
      throw new IllegalArgumentException("StringFilter's value (field=" + getField() + ") must not be null");
    }
  }
}
