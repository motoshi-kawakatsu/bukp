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
 * 文字型フィルタの構造クラスです.
 */
public abstract class StringFilterBuilder {

  /**
   * <pre>
   * 空の検索文を作成する
   * 
   * SQL文：field　is Null
   * </pre>
   * 
   * @param field フィールド
   * @return 文字型フィルタ
   */
  public static StringFilter isNull(String field) {
    return new StringFilter(field, StringFilterOp.IsNull);
  }

  /**
   * <pre>
   * 非空の検索文を作成する
   * 
   * SQL文：field　is not Null
   * </pre>
   * 
   * @param field フィールド
   * @return 文字型フィルタ
   */
  public static StringFilter isNotNull(String field) {
    return new StringFilter(field, StringFilterOp.IsNotNull);
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
   * @return 文字型フィルタ
   */
  public static StringFilter equals(String field, String value) {
    return new StringFilter(field, StringFilterOp.Equals, value);
  }

  /**
   * <pre>
   * 等式の検索文を作成する（valueが空白文字以外）
   * 
   * SQL文：field = value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 文字型フィルタ
   */
  public static StringFilter equalsIfNotEmpty(String field, String value) {
    if (value == null || value.length() == 0) {
      return null;
    } else {
      return new StringFilter(field, StringFilterOp.Equals, value);
    }
  }

  /**
   * <pre>
   * 等式の検索文を作成する（valueがNull以外）
   * 
   * SQL文：field = value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 文字型フィルタ
   */
  public static StringFilter equalsIfNotNull(String field, String value) {
    if (value == null) {
      return null;
    } else {
      return new StringFilter(field, StringFilterOp.Equals, value);
    }
  }

  /**
   * <pre>
   * 非等式の検索文を作成する
   * 
   * SQL文：field = value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 文字型フィルタ
   */
  public static StringFilter notEquals(String field, String value) {
    return new StringFilter(field, StringFilterOp.NotEquals, value);
  }

  /**
   * <pre>
   * 前方一致の検索文を作成する
   * 
   * SQL文：field like value%
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 文字型フィルタ
   */
  public static StringFilter beginsWith(String field, String value) {
    return new StringFilter(field, StringFilterOp.BeginsWith, value);
  }

  /**
   * <pre>
   * 前方一致の検索文を作成する（valueが空白文字以外）
   * 
   * SQL文：field like value%
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 文字型フィルタ
   */
  public static StringFilter beginsWithIfNotEmpty(String field, String value) {
    if (value == null || value.length() == 0) {
      return null;
    } else {
      return new StringFilter(field, StringFilterOp.BeginsWith, value);
    }
  }

  /**
   * <pre>
   * 非前方一致の検索文を作成する
   * 
   * SQL文：field not like value%
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 文字型フィルタ
   */
  public static StringFilter notBeginsWith(String field, String value) {
    return new StringFilter(field, StringFilterOp.NotBeginsWith, value);
  }

  /**
   * <pre>
   * 後方一致の検索文を作成する
   * 
   * SQL文：field like %value
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 文字型フィルタ
   */
  public static StringFilter endsWith(String field, String value) {
    return new StringFilter(field, StringFilterOp.EndsWith, value);
  }

  /**
   * <pre>
   * 後方一致の検索文を作成する（valueが空白文字以外）
   * 
   * SQL文：field like value%
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 文字型フィルタ
   */
  public static StringFilter endsWithIfNotEmpty(String field, String value) {
    if (value == null || value.length() == 0) {
      return null;
    } else {
      return new StringFilter(field, StringFilterOp.EndsWith, value);
    }
  }

  /**
   * <pre>
   * 非後方一致の検索文を作成する
   * 
   * SQL文：field like value%
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 文字型フィルタ
   */
  public static StringFilter notEndsWith(String field, String value) {
    return new StringFilter(field, StringFilterOp.NotEndsWith, value);
  }

  /**
   * <pre>
   * 部分一致の検索文を作成する
   * 
   * SQL文：field like %value%
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 文字型フィルタ
   */
  public static StringFilter contains(String field, String value) {
    return new StringFilter(field, StringFilterOp.Contains, value);
  }

  /**
   * <pre>
   * 部分一致の検索文を作成する（valueが空白文字以外）
   * 
   * SQL文：field like %value%
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 文字型フィルタ
   */
  public static StringFilter containsIfNotEmpty(String field, String value) {
    if (value == null || value.length() == 0) {
      return null;
    } else {
      return new StringFilter(field, StringFilterOp.Contains, value);
    }
  }

  /**
   * <pre>
   * 非部分一致の検索文を作成する
   * 
   * SQL文：field not like %value%
   * </pre>
   * 
   * @param field フィールド
   * @param value パラメータ
   * @return 文字型フィルタ
   */
  public static StringFilter notContains(String field, String value) {
    return new StringFilter(field, StringFilterOp.NotContains, value);
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
   * @return 文字型フィルタ
   */
  public static StringFilter in(String field, String... values) {
    return new StringFilter(field, StringFilterOp.In, values);
  }

  /**
   * <pre>
   * 「in」の検索文を作成する
   * 
   * SQL文：field not in (values)
   * </pre>
   * 
   * @param field フィールド
   * @param values パラメータ
   * @return 文字型フィルタ
   */
  public static StringFilter notIn(String field, String... values) {
    return new StringFilter(field, StringFilterOp.NotIn, values);
  }
}
