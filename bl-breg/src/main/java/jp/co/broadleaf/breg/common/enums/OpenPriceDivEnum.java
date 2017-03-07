//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:オープン価格区分のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * オープン価格区分
 */
public enum OpenPriceDivEnum {

  /**
   * 0:通常
   */
  Common(0, "通常"),

  /**
   * 1:オープン価格
   */
  OpenPrice(1, "オープン価格");

  /**
   * オープン価格区分の数値
   */
  private int value = 0;

  /**
   * オープン価格区分の名称
   */
  private String name = null;

  /**
   * コンストラクタ
   * 
   * @param value オープン価格区分の数値
   * @param name オープン価格区分の名称
   */
  OpenPriceDivEnum(int value, String name) {
    this.value = value;
    this.name = name;
  }

  /**
   * オープン価格区分の数値を取得する
   *
   * @return オープン価格区分の数値
   */
  public int getValue() {
    return this.value;
  }

  /**
   * オープン価格区分の名称を取得する
   *
   * @return オープン価格区分の名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * 数値からオープン価格区分を取得する
   *
   * @param value オープン価格区分の数値
   * @return オープン価格区分
   */
  public static OpenPriceDivEnum valueof(int value) {
    switch (value) {
    case 0:
      // 通常
      return Common;
    case 1:
      // オープン価格
      return OpenPrice;
    default:
      // その他場合
      return null;
    }
  }

  /**
   * オープン価格区分の数値を取得する
   * 
   * @param name オープン価格の名称
   * @return オープン価格の数値
   */
  public static int getCode(String name) {
    if (name == null) {
      return Common.getValue();
    }
    if (name.equals(Common.getName())) {
      return Common.getValue();
    }
    if (name.equals(OpenPrice.getName())) {
      return OpenPrice.getValue();
    }
    return Common.getValue();
  }
}
