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
 *  論理型フィルタの構造クラスです.
 * </pre>
 */
public abstract class BooleanFilterBuilder {

  /**
   * <pre>
   * 空の検索文を作成する
   * 
   * SQL文：field　is null
   * </pre>
   * 
   * @param field フィールド
   * @return 論理型フィルタ
   */
  public static BooleanFilter isNull(String field) {
    return new BooleanFilter(field, BooleanFilterOp.IsNull);
  }

  /**
   * <pre>
   * 非空の検索文を作成する
   * 
   * SQL文：field　is not null
   * </pre>
   * 
   * @param field フィールド
   * @return 論理型フィルタ
   */
  public static BooleanFilter isNotNull(String field) {
    return new BooleanFilter(field, BooleanFilterOp.IsNotNull);
  }

  /**
   * <pre>
   * 等式の検索文を作成する
   * 
   * SQL文：field = value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 論理型フィルタ
   */
  public static BooleanFilter equals(String field, boolean value) {
    return new BooleanFilter(field, BooleanFilterOp.Equals, value);
  }

  /**
   * <pre>
   * 非等式の検索文を作成する
   * 
   * SQL文：field &lt;&gt; value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 論理型フィルタ
   */
  public static BooleanFilter notEquals(String field, boolean value) {
    return new BooleanFilter(field, BooleanFilterOp.NotEquals, value);
  }
}
