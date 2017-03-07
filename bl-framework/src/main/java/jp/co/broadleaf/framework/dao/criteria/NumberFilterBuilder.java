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
 * 数字型フィルタの構造クラスです.
 */
public abstract class NumberFilterBuilder {

  /**
   * <pre>
   * 空の検索文を作成する
   * 
   * SQL文：field　is Null
   * </pre>
   * 
   * @param field フィールド
   * @return 数字型フィルタ
   */
  public static NumberFilter isNull(String field) {
    return new NumberFilter(field, NumberFilterOp.IsNull);
  }

  /**
   * <pre>
   * 非空の検索文を作成する
   * 
   * SQL文：field　is not Null
   * </pre>
   * 
   * @param field フィールド
   * @return 数字型フィルタ
   */
  public static NumberFilter isNotNull(String field) {
    return new NumberFilter(field, NumberFilterOp.IsNotNull);
  }

  /**
   * <pre>
   * 等式の検索文を作成する（valueが非空）
   * 
   * SQL文：field = value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 数字型フィルタ
   */
  public static NumberFilter equalsIfNotNull(String field, Number value) {
    if (value == null) {
      return null;
    } else {
      return new NumberFilter(field, NumberFilterOp.Equals, value);
    }
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
   * @return 数字型フィルタ
   */
  public static NumberFilter equals(String field, Number value) {
    return new NumberFilter(field, NumberFilterOp.Equals, value);
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
   * @return 数字型フィルタ
   */
  public static NumberFilter notEquals(String field, Number value) {
    return new NumberFilter(field, NumberFilterOp.NotEquals, value);
  }

  /**
   * <pre>
   * 「&gt;」の検索文を作成する（valueが非空）
   * 
   * SQL文：field &gt; value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 数字型フィルタ
   */
  public static NumberFilter greaterThanIfNotNull(String field, Number value) {
    if (value == null) {
      return null;
    } else {
      return new NumberFilter(field, NumberFilterOp.GreaterThan, value);
    }
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
   * @return 数字型フィルタ
   */
  public static NumberFilter greaterThan(String field, Number value) {
    return new NumberFilter(field, NumberFilterOp.GreaterThan, value);
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
   * @return 数字型フィルタ
   */
  public static NumberFilter greaterThanOrEqualsIfNotNull(String field, Number value) {
    if (value == null) {
      return null;
    } else {
      return new NumberFilter(field, NumberFilterOp.GreaterThanOrEquals, value);
    }
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
   * @return 数字型フィルタ
   */
  public static NumberFilter greaterThanOrEquals(String field, Number value) {
    return new NumberFilter(field, NumberFilterOp.GreaterThanOrEquals, value);
  }

  /**
   * <pre>
   * 「&lt;」の検索文を作成する（valueが非空）
   * 
   * SQL文：field &lt; value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 数字型フィルタ
   */
  public static NumberFilter lessThanIfNotNull(String field, Number value) {
    if (value == null) {
      return null;
    } else {
      return new NumberFilter(field, NumberFilterOp.LessThan, value);
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
   * @param value パラメータ
   * @return 数字型フィルタ
   */
  public static NumberFilter lessThan(String field, Number value) {
    return new NumberFilter(field, NumberFilterOp.LessThan, value);
  }

  /**
   * <pre>
   * 「&lt;=」の検索文を作成する（valueが非空）
   * 
   * SQL文：field &lt;= value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 数字型フィルタ
   */
  public static NumberFilter lessThanOrEqualsIfNotNull(String field, Number value) {
    if (value == null) {
      return null;
    } else {
      return new NumberFilter(field, NumberFilterOp.LessThanOrEquals, value);
    }
  }

  /**
   * <pre>
   * 「&lt;=」の検索文を作成する
   * 
   * SQL文：field &lt;= value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 数字型フィルタ
   */
  public static NumberFilter lessThanOrEquals(String field, Number value) {
    return new NumberFilter(field, NumberFilterOp.LessThanOrEquals, value);
  }

  /**
   * <pre>
   * 「in」の検索文を作成する
   * 
   * SQL文：field in (values)
   * </pre>
   * 
   * @param field フィールド
   * @param values パラメータ
   * @return 数字型フィルタ
   */
  public static NumberFilter in(String field, Number... values) {
    return new NumberFilter(field, NumberFilterOp.In, values);
  }

  /**
   * <pre>
   * 「not in」の検索文を作成する
   * 
   * SQL文：field not in (values)
   * </pre>
   * 
   * @param field フィールド
   * @param values パラメータ
   * @return 数字型フィルタ
   */
  public static NumberFilter notIn(String field, Number... values) {
    return new NumberFilter(field, NumberFilterOp.NotIn, values);
  }
}
