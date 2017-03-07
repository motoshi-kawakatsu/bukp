//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:申請状態のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * サイズ単位
 */
public enum SizeUnitEnum {

  /**
   * 01:mm
   */
  MM("01", "mm"),

  /**
   * 02:cm
   */
  CM("02", "cm"),
  /**
   * 03:m
   */
  M("03", "m"),
  /**
   * 00:Empty
   */
  Empty("00", "");

  /**
   * サイズ単位の数値
   */
  private String value = null;

  /**
   * サイズ単位の名称
   */
  private String name = null;

  /**
   * コンストラクタ
   * 
   * @param value サイズ単位の数値
   * @param name サイズ単位の名称
   */
  SizeUnitEnum(String value, String name) {
    this.value = value;
    this.name = name;
  }

  /**
   * サイズ単位の数値を取得する
   *
   * @return サイズ単位の数値
   */
  public String getValue() {
    return this.value;
  }

  /**
   * サイズ単位の名称を取得する
   *
   * @return サイズ単位の名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * 数値からサイズ単位を取得する
   *
   * @param value サイズ単位の数値
   * @return サイズ単位
   */
  public static SizeUnitEnum valueof(String value) {
    if (value == null) {
      return SizeUnitEnum.Empty;
    } else if (value.equals(MM.getValue())) {
      return SizeUnitEnum.MM;
    } else if (value.equals(CM.getValue())) {
      return SizeUnitEnum.CM;
    } else if (value.equals(M.getValue())) {
      return SizeUnitEnum.M;
    } else {
      return SizeUnitEnum.Empty;
    }
  }

  /**
   * サイズ単位の数値を取得する
   * 
   * @param name サイズ単位の名称
   * @return サイズ単位の数値
   */
  public static String getCode(String name) {
    if (name == null) {
      return null;
    }
    if (name.equals(MM.getName())) {
      return MM.getValue();
    } else if (name.equals(CM.getName())) {
      return CM.getValue();
    } else if (name.equals(M.getName())) {
      return M.getValue();
    }
    return Empty.getValue();
  }

}
