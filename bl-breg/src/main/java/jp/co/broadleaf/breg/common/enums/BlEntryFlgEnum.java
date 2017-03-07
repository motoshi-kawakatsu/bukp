//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:BL登録区分のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * BL登録区分
 */
public enum BlEntryFlgEnum {

  /**
   * 0:未登録
   */
  Unregistered(0, "未登録", "未"),

  /**
   * 1:申請中
   */
  Apply(1, "申請中", "中"),

  /**
   * 2:登録済み
   */
  Registered(2, "登録済み", "済");

  /**
   * BL登録区分の数値
   */
  private int value = 0;

  /**
   * BL登録区分の名称
   */
  private String name = null;
  /**
   * BL登録区分の略称
   */
  private String abbreviation = null;

  /**
   * コンストラクタ
   * 
   * @param value BL登録区分の数値
   * @param name BL登録区分の名称
   * @param abbreviation BL登録区分の略称
   */
  BlEntryFlgEnum(int value, String name, String abbreviation) {
    this.value = value;
    this.name = name;
    this.abbreviation = abbreviation;
  }

  /**
   * BL登録区分の数値を取得する
   *
   * @return BL登録区分の数値
   */
  public int getValue() {
    return this.value;
  }

  /**
   * BL登録区分の名称を取得する
   *
   * @return BL登録区分の名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * BL登録区分の略称を取得する
   *
   * @return BL登録区分の略称
   */
  public String getAbbreviation() {
    return this.abbreviation;
  }

  /**
   * 数値からBL登録区分を取得する
   *
   * @param value BL登録区分の数値
   * @return BL登録区分
   */
  public static BlEntryFlgEnum valueof(int value) {
    switch (value) {
    case 0:
      // 未登録
      return Unregistered;
    case 1:
      // 申請中
      return Apply;
    case 2:
      // 登録済み
      return Registered;
    default:
      // その他場合
      return null;
    }
  }

  /**
   * BL登録区分の数値を取得する
   * 
   * @param abbreviation BL登録区分の略称
   * @return BL登録区分の数値
   */
  public static int getCode(String abbreviation) {
    if (abbreviation == null) {
      return Unregistered.getValue();
    }
    if (abbreviation.equals(Unregistered.getAbbreviation())) {
      return Unregistered.getValue();
    }
    if (abbreviation.equals(Apply.getAbbreviation())) {
      return Apply.getValue();
    }
    if (abbreviation.equals(Registered.getAbbreviation())) {
      return Registered.getValue();
    }
    return Unregistered.getValue();
  }
}
