//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:陸磊
// 作成日:2017/03/06    修正内容:タブページのEnumファイル:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.enums;

/**
 * <pre>
 * タブページ
 * </pre>
 */
public enum SelectTabEnum {

  /**
   * タブページ
   */
  GoodsTab("1", "商品"),

  /**
   * タブページ
   */
  SetTab("2", "セット"),

  /**
   * タブページ
   */
  JoinTab("3", "結合");

  /**
   * タブページのモードの数値
   */
  private String value = "1";

  /**
   * タブページのモードの名称
   */
  private String name = "商品";

  /**
   * コンストラクタ
   * 
   * @param value タブページのモードの数値
   * @param name タブページのモードの名称
   */
  SelectTabEnum(String value, String name) {
    this.value = value;
    this.name = name;
  }

  /**
   * 画面のモードの数値を取得する
   *
   * @return 画面のモードの数値
   */
  public String getValue() {
    return this.value;
  }

  /**
   * 画面のモードの名称を取得する
   *
   * @return 画面のモードの名称
   */
  public String getName() {
    return this.name;
  }
}