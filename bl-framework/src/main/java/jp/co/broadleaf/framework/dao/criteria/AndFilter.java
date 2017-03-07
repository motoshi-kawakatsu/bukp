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
 * And演算子フィルタの定義クラス
 * </pre>
 */
public class AndFilter extends CompositeFilter {

  /**
   * コンストラクタ
   * 
   * @param filters フィルタ
   */
  public AndFilter(Filter... filters) {
    super(filters);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(FilterVisitor visitor) {
    visitor.visitAndFilter(this);
  }
}
