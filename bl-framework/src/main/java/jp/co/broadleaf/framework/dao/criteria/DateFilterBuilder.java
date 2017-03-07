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
 * 日付型フィルタの構造クラス
 */
public abstract class DateFilterBuilder {

  /**
   * <pre>
   * 空の検索文を作成する
   * 
   * SQL文：field　is Null
   * </pre>
   * 
   * @param field フィールド
   * @return 日付型フィルタ
   */
  public static DateFilter isNull(String field) {
    return new DateFilter(field, DateFilterOp.IsNull);
  }

  /**
   * <pre>
   * 非空の検索文を作成する
   * 
   * SQL文：field　is Not Null
   * </pre>
   * 
   * @param field フィールド
   * @return 日付型フィルタ
   */
  public static DateFilter isNotNull(String field) {
    return new DateFilter(field, DateFilterOp.IsNotNull);
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
   * @return 日付型フィルタ
   */
  public static DateFilter equals(String field, Date value) {
    return new DateFilter(field, DateFilterOp.Equals, value);
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
   * @return 日付型フィルタ
   */
  public static DateFilter notEquals(String field, Date value) {
    return new DateFilter(field, DateFilterOp.NotEquals, value);
  }

  /**
   * <pre>
   * 「&gt;」の検索文を作成する
   * 
   * SQL文：field &gt; value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 日付型フィルタ
   */
  public static DateFilter greaterThan(String field, Date value) {
    return new DateFilter(field, DateFilterOp.GreaterThan, value);
  }

  /**
   * <pre>
   * 「&gt;=」の検索文を作成する
   * 
   * SQL文：field &gt;= value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 日付型フィルタ
   */
  public static DateFilter greaterThanOrEquals(String field, Date value) {
    return new DateFilter(field, DateFilterOp.GreaterThanOrEquals, value);
  }

  /**
   * <pre>
   * 「&gt;=」の検索文を作成する（valueが非空）
   * 
   * SQL文：field &gt;= value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 日付型フィルタ
   */
  public static DateFilter greaterThanOrEqualsIfNotNull(String field, Date value) {
    if (value == null) {
      return null;
    } else {
      return new DateFilter(field, DateFilterOp.GreaterThanOrEquals, value);
    }
  }

  /**
   * <pre>
   * 「&lt;」の検索文を作成する
   * 
   * SQL文：field &lt; value
   * </pre>
   * 
   * @param field フィールド
   * @param values パラメータ
   * @return 日付型フィルタ
   */
  public static DateFilter lessThan(String field, Date... values) {
    return new DateFilter(field, DateFilterOp.LessThan, values);
  }

  /**
   * <pre>
   * 「&lt;=」の検索文を作成する
   * 
   * SQL文：field &lt;= value
   * </pre>
   * 
   * @param field フィールド
   * @param values パラメータ
   * @return 日付型フィルタ
   */
  public static DateFilter lessThanOrEquals(String field, Date... values) {
    return new DateFilter(field, DateFilterOp.LessThanOrEquals, values);
  }

  /**
   * <pre>
   * 「&gt;=」の検索文を作成する（valueが非空）
   * 
   * SQL文：field &gt;= value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 日付型フィルタ
   */
  public static DateFilter lessThanOrEqualsIfNotNull(String field, Date value) {
    if (value == null) {
      return null;
    } else {
      return new DateFilter(field, DateFilterOp.LessThanOrEquals, value);
    }
  }
}
