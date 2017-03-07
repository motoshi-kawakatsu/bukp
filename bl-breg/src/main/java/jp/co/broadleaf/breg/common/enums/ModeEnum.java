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
 * 画面のモード
 */
public enum ModeEnum {

  /**
   * 検索入力モード
   */
  Input(0, "検索入力"),

  /**
   * 結合選択モード
   */
  Select(1, "結合選択"),

  /**
   * 参照モード
   */
  Reference(2, "参照"),

  /**
   * エラー修正モード
   */
  Error(3, "エラー修正");

  /**
   * 画面のモードの数値
   */
  private int value = 0;

  /**
   * 画面のモードの名称
   */
  private String name = null;

  /**
   * コンストラクタ
   * 
   * @param value 画面のモードの数値
   * @param name 画面のモードの名称
   */
  ModeEnum(int value, String name) {
    this.value = value;
    this.name = name;
  }

  /**
   * 画面のモードの数値を取得する
   *
   * @return 画面のモードの数値
   */
  public int getValue() {
    return this.value;
  }

  /**
   * 画面のモードの名称を取得する
   *
   * @return 画面のモードの名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * 数値から状態の判断を取得する
   *
   * @param value 画面のモードの数値
   * @return 画面のモード
   */
  public static ModeEnum valueof(int value) {
    switch (value) {
    case 0:
      // 検索入力モード
      return Input;
    case 1:
      // 結合選択モード
      return Select;
    case 2:
      // 参照モード
      return Reference;
    case 3:
      // エラー修正モード
      return Error;
    default:
      // その他場合
      return null;
    }
  }

}
