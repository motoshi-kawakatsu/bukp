//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:filterモードのEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.totalinfo.enums;

/**
 * filterモード
 */
public enum FilterObjectEnum {

  /**
   * 0:初期検索
   */
  InitialSearch(0, "初期検索"),

  /**
   * 1:BLコード
   */
  TbsPartsCode(1, "BLコード"),

  /**
   * 2:カーコード
   */
  PartsMakerCd(2, "カーコード"),

  /**
   * 3:優良品番
   */
  PrimePartsNoWithH(3, "優良品番"),

  /**
   * 4:純正品番
   */
  JoinSourPartsNoWithH(4, "純正品番");

  /**
   * filterモードの数値
   */
  private int value = 0;

  /**
   * filterモードの名称
   */
  private String name = null;

  /**
   * コンストラクタ
   * 
   * @param value filterモードの数値
   * @param name filterモードの名称
   */
  FilterObjectEnum(int value, String name) {
    this.value = value;
    this.name = name;
  }

  /**
   * filterモードの数値を取得する
   *
   * @return filterモードの数値
   */
  public int getValue() {
    return this.value;
  }

  /**
   * filterモードの名称を取得する
   *
   * @return filterモードの名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * 数値からfilterモードを取得する
   *
   * @param value filterモードの数値
   * @return filterモード
   */
  public static FilterObjectEnum valueof(int value) {
    switch (value) {
    case 0:
      // BLコード
      return TbsPartsCode;
    case 1:
      // カーコード
      return PartsMakerCd;
    case 2:
      // 優良品番
      return PrimePartsNoWithH;
    case 3:
      // 純正品番
      return JoinSourPartsNoWithH;
    case 4:
      // 初期検索
      return InitialSearch;

    default:
      // その他場合
      return null;
    }
  }

}
