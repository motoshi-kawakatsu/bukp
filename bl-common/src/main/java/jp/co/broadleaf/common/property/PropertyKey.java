//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/24   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.common.property;

/**
 * <pre>
 * プロパティキークラスです。
 * </pre>
 * 
 * @param <T> プロパティ値の本来の型
 */
public class PropertyKey<T> {

  /**
   * キー名
   */
  private String name;

  /**
   * プロパティ値の本来の型
   */
  private Class<T> dataType;

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param name キー名
   * @param dataType プロパティ値の本来の型
   */
  public PropertyKey(final String name, final Class<T> dataType) {
    this.name = name;
    this.dataType = dataType;
  }

  /**
   * <pre>
   * キー名を取得する。
   * </pre>
   *
   * @return キー名
   */
  public String getName() {
    return name;
  }

  /**
   * <pre>
   * プロパティ値の本来の型を取得する。
   * </pre>
   *
   * @return プロパティ値の本来の型
   */
  public Class<T> getDataType() {
    return dataType;
  }

}
