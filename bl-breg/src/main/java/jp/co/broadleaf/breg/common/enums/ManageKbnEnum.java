//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:処理区分のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * 処理区分
 */
public enum ManageKbnEnum {

  /**
   * 0:追加
   */
  Add(0, "追加", "追"),

  /**
   * 1:更新
   */
  Update(1, "更新", "更"),

  /**
   * 2:削除
   */
  Delete(2, "削除", "削");

  /**
   * 処理区分の数値
   */
  private int value = 0;

  /**
   * 処理区分の名称
   */
  private String name = null;
  /**
   * 処理区分の略称
   */
  private String abbreviation = null;

  /**
   * コンストラクタ
   * 
   * @param value 処理区分の数値
   * @param name 処理区分の名称
   * @param abbreviation 処理区分の略称
   */
  ManageKbnEnum(int value, String name, String abbreviation) {
    this.value = value;
    this.name = name;
    this.abbreviation = abbreviation;
  }

  /**
   * 処理区分の数値を取得する
   *
   * @return 処理区分の数値
   */
  public int getValue() {
    return this.value;
  }

  /**
   * 処理区分の名称を取得する
   *
   * @return 処理区分の名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * 処理区分の略称を取得する
   *
   * @return 処理区分の略称
   */
  public String getAbbreviation() {
    return this.abbreviation;
  }

  /**
   * 数値から処理区分を取得する
   *
   * @param value 処理区分の数値
   * @return 処理区分
   */
  public static ManageKbnEnum valueof(int value) {
    switch (value) {
    case 0:
      // 追加
      return Add;
    case 1:
      // 更新
      return Update;
    case 2:
      // 削除
      return Delete;
    default:
      // その他場合
      return null;
    }
  }

  /**
   * 処理区分の数値を取得する
   * 
   * @param abbreviation 処理区分の略称
   * @return 処理区分の数値
   */
  public static int getCode(String abbreviation) {
    if (abbreviation == null) {
      return Add.getValue();
    }
    if (abbreviation.equals(Add.getAbbreviation())) {
      return Add.getValue();
    } else if (abbreviation.equals(Add.getName())) {
		return Add.getValue();
	}
    if (abbreviation.equals(Update.getAbbreviation())) {
      return Update.getValue();
    } else if (abbreviation.equals(Update.getName())) {
		return Update.getValue();
	}
    if (abbreviation.equals(Delete.getAbbreviation())) {
      return Delete.getValue();
    } else if (abbreviation.equals(Delete.getName())) {
		return Delete.getValue();
	}
    return Add.getValue();
  }
}
