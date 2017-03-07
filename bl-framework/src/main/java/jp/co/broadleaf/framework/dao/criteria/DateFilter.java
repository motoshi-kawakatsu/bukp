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

import java.util.Date;

/**
 * 日付型フィルタの定義クラス
 */
public class DateFilter extends FieldFilter<Date, DateFilterOp> {

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param field フィールド
   * @param operation 演算子
   * @param values パラメータ
   */
  public DateFilter(String field, DateFilterOp operation, Date... values) {
    super(field, operation, values);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(FilterVisitor visitor) {
    visitor.visitDateFilter(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void validate() {
    if (null == getValue() && DateFilterOp.IsNull != getOperator() && DateFilterOp.IsNotNull != getOperator()) {
      throw new IllegalArgumentException("DateFilter's value (field=" + getField() + ") must not be null");
    }
  }
}
