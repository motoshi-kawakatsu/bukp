//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:申請状態のEnutファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * 重量単位
 */
public enum WeightUnitEnum {

  /**
   * 01:g
   */
  G("01", "g"),

  /**
   * 02:kg
   */
  KG("02", "kg"),
  /**
   * 03:t
   */
  T("03", "t"),
  /**
   * 00:Empty
   */
  Empty("00", "");

  /**
   * 重量単位の数値
   */
  private String value = null;

  /**
   * 重量単位の名称
   */
  private String name = null;

  /**
   * コンストラクタ
   * 
   * @param value 重量単位の数値
   * @param name 重量単位の名称
   */
  WeightUnitEnum(String value, String name) {
    this.value = value;
    this.name = name;
  }

  /**
   * 重量単位の数値を取得する
   *
   * @return 重量単位の数値
   */
  public String getValue() {
    return this.value;
  }

  /**
   * 重量単位の名称を取得する
   *
   * @return 重量単位の名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * 数値から重量単位を取得する
   *
   * @param value 重量単位の数値
   * @return 重量単位
   */
  public static WeightUnitEnum valueof(String value) {
    if (value == null) {
      return WeightUnitEnum.Empty;
    } else if (value.equals(G.getValue())) {
      return WeightUnitEnum.G;
    } else if (value.equals(KG.getValue())) {
      return WeightUnitEnum.KG;
    } else if (value.equals(T.getValue())) {
      return WeightUnitEnum.T;
    } else {
      return WeightUnitEnum.Empty;
    }
  }

  /**
   * 重量単位の数値を取得する
   * 
   * @param name 重量単位の名称
   * @return 重量単位の数値
   */
  public static String getCode(String name) {
    if (name == null) {
      return null;
    }
    if (name.equals(G.getName())) {
      return G.getValue();
    } else if (name.equals(KG.getName())) {
      return KG.getValue();
    } else if (name.equals(T.getName())) {
      return T.getValue();
    }
    return Empty.getValue();
  }

}
