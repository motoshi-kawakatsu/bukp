//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:状態の判断のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * 状態の判断
 */
public enum JudgeEnum {

  /**
   * 0:新規
   */
  Add(0, "新規"),

  /**
   * 1:不変
   */
  UnChange(1, "不変"),

  /**
   * 2:更新
   */
  Update(2, "更新"),

  /**
   * 3:削除
   */
  Delete(3, "削除"),

  /**
   * 4:修正
   */
  Modify(4, "修正");

  /**
   * 状態の判断の数値
   */
  private int value = 0;

  /**
   * 状態の判断の名称
   */
  private String name = null;

  /**
   * コンストラクタ
   * 
   * @param value 状態の判断の数値
   * @param name 状態の判断の名称
   */
  JudgeEnum(int value, String name) {
    this.value = value;
    this.name = name;
  }

  /**
   * 状態の判断の数値を取得する
   *
   * @return 状態の判断の数値
   */
  public int getValue() {
    return this.value;
  }

  /**
   * 状態の判断の名称を取得する
   *
   * @return 状態の判断の名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * 数値から状態の判断を取得する
   *
   * @param value 状態の判断の数値
   * @return 状態の判断
   */
  public static JudgeEnum valueof(int value) {
    switch (value) {
    case 0:
      // 新規
      return Add;
    case 1:
      // 不変
      return UnChange;
    case 2:
      // 更新
      return Update;
    case 3:
      // 削除
      return Delete;
    default:
      // その他場合
      return null;
    }
  }

}
