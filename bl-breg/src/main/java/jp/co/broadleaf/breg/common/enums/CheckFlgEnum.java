//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:チェック区分のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * チェック区分
 */
public enum CheckFlgEnum {

  /**
   * 0:未チェック
   */
  Unchecked(0, "未チェック", "未"),

  /**
   * 1:チェック済み
   */
  Checked(1, "チェック済み", "済");

  /**
   * チェック区分の数値
   */
  private int value = 0;

  /**
   * チェック区分の名称
   */
  private String name = null;
  /**
   * チェック区分の略称
   */
  private String abbreviation = null;

  /**
   * コンストラクタ
   * 
   * @param value チェック区分の数値
   * @param name チェック区分の名称
   * @param abbreviation チェック区分の略称
   */
  CheckFlgEnum(int value, String name, String abbreviation) {
    this.value = value;
    this.name = name;
    this.abbreviation = abbreviation;
  }

  /**
   * チェック区分の数値を取得する
   *
   * @return チェック区分の数値
   */
  public int getValue() {
    return this.value;
  }

  /**
   * チェック区分の名称を取得する
   *
   * @return チェック区分の名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * チェック区分の略称を取得する
   *
   * @return チェック区分の略称
   */
  public String getAbbreviation() {
    return this.abbreviation;
  }

  /**
   * 数値からチェック区分を取得する
   *
   * @param value チェック区分の数値
   * @return チェック区分
   */
  public static CheckFlgEnum valueof(int value) {
    switch (value) {
    case 0:
      // 未チェック
      return Unchecked;
    case 1:
      // チェック済み
      return Checked;
    default:
      // その他場合
      return null;
    }
  }

  /**
   * チェック区分の数値を取得する
   * 
   * @param abbreviation チェック区分の略称
   * @return チェック区分の数値
   */
  public static int getCode(String abbreviation) {
    if (abbreviation == null) {
      return Unchecked.getValue();
    }
    if (abbreviation.equals(Unchecked.getAbbreviation())) {
      return Unchecked.getValue();
    }
    if (abbreviation.equals(Checked.getAbbreviation())) {
      return Checked.getValue();
    }
    return Unchecked.getValue();
  }
}
