//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:削除依頼区分のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * 削除依頼区分
 */
public enum DeleteFlgEnum {

  /**
   * 0:なし
   */
  NoDelete(0, "削除しない", ""),

  /**
   * 1:削除する
   */
  Delete(1, "削除する", "削除する");

  /**
   * 削除依頼区分の数値
   */
  private int value = 0;

  /**
   * 削除依頼区分の名称
   */
  private String name = null;
  /**
   * 削除依頼区分の略称
   */
  private String abbreviation = null;

  /**
   * コンストラクタ
   * 
   * @param value 削除依頼区分の数値
   * @param name 削除依頼区分の名称
   * @param abbreviation 削除依頼区分の名称
   */
  DeleteFlgEnum(int value, String name, String abbreviation) {
    this.value = value;
    this.name = name;
    this.abbreviation = abbreviation;
  }

  /**
   * 削除依頼区分の数値を取得する
   *
   * @return 削除依頼区分の数値
   */
  public int getValue() {
    return this.value;
  }

  /**
   * 削除依頼区分の名称を取得する
   *
   * @return 削除依頼区分の名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * 削除依頼区分の略称を取得する
   *
   * @return 削除依頼区分の略称
   */
  public String getAbbreviation() {
    return this.abbreviation;
  }

  /**
   * 数値から削除依頼区分を取得する
   *
   * @param value 削除依頼区分の数値
   * @return 削除依頼区分
   */
  public static DeleteFlgEnum valueof(int value) {
    switch (value) {
    case 0:
      // なし
      return NoDelete;
    case 1:
      // 削除する
      return Delete;
    default:
      // その他場合
      return null;
    }
  }

  /**
   * 削除依頼区分の数値を取得する
   * 
   * @param name 削除依頼区分の名称
   * @return 削除依頼区分の数値
   */
  public static int getCode(String name) {
    if (NoDelete.getName().equals(name)) {
      return NoDelete.getValue();
    } else if (Delete.getName().equals(name)) {
      return Delete.getValue();
    }
    return NoDelete.getValue();
  }

  /**
   * チェック区分の数値を取得する
   * 
   * @param abbreviation チェック区分の略称
   * @return チェック区分の数値
   */
  public static int getCodeByAbbreviation(String abbreviation) {
    if (NoDelete.getAbbreviation().equals(abbreviation)) {
      return NoDelete.getValue();
    } else if (Delete.getAbbreviation().equals(abbreviation)) {
      return Delete.getValue();
    }
    return NoDelete.getValue();
  }
}
