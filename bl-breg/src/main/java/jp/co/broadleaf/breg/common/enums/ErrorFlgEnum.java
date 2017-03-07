//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:データステータスのEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * データステータス
 */
public enum ErrorFlgEnum {

  /**
   * 0:エラー無し
   */
  NoError(0, "エラーなしのみ表示", "無"),

  /**
   * 1:エラー有り
   */
  Error(1, "エラーありのみ表示", "有"),

  /**
   * 9:不定
   */
  Indefinite(9, "不定", "不定");

  /**
   * データステータスの数値
   */
  private int value = 0;

  /**
   * データステータスの名称
   */
  private String name = null;
  /**
   * データステータスの略称
   */
  private String abbreviation = null;

  /**
   * コンストラクタ
   * 
   * @param value データステータスの数値
   * @param name データステータスの名称
   * @param abbreviation データステータスの略称
   */
  ErrorFlgEnum(int value, String name, String abbreviation) {
    this.value = value;
    this.name = name;
    this.abbreviation = abbreviation;
  }

  /**
   * データステータスの数値を取得する
   *
   * @return データステータスの数値
   */
  public int getValue() {
    return this.value;
  }

  /**
   * データステータスの名称を取得する
   *
   * @return データステータスの名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * データステータスの略称を取得する
   *
   * @return データステータスの略称
   */
  public String getAbbreviation() {
    return this.abbreviation;
  }

  /**
   * 数値からデータステータスを取得する
   *
   * @param value データステータスの数値
   * @return データステータス
   */
  public static ErrorFlgEnum valueof(int value) {
    switch (value) {
    case 0:
      // エラー無し
      return NoError;
    case 1:
      // エラー有り
      return Error;
    case 9:
      // 不定
      return Indefinite;
    default:
      // その他場合
      return null;
    }
  }

  /**
   * データステータスの数値を取得する
   * 
   * @param abbreviation データステータスの略称
   * @return データステータスの数値
   */
  public static int getCode(String abbreviation) {
    if (abbreviation == null) {
      return Indefinite.getValue();
    } 
    if (abbreviation.equals(NoError.getAbbreviation())) {
      return NoError.getValue();
    } else if (abbreviation.equals(NoError.getName())) {
		return NoError.getValue();
	}
    if (abbreviation.equals(Error.getAbbreviation())) {
      return Error.getValue();
    } else if (abbreviation.equals(Error.getName())) {
		return Error.getValue();
	}
    return Indefinite.getValue();
  }
}
