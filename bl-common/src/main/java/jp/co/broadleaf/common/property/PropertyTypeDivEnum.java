//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/24   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.common.property;

/**
 * 型区分
 */
public enum PropertyTypeDivEnum {

  /**
   * 1:文字列
   */
  String(1),

  /**
   * 2:数値
   */
  Int(2),

  /**
   * 3:数値（大）
   */
  Long(3),

  /**
   * 4:日付
   */
  Date(4);

  /**
   * 型区分の数値
   */
  private int value = 0;

  /**
   * コンストラクタ
   * 
   * @param value 型区分の数値
   */
  PropertyTypeDivEnum(int value) {
    this.value = value;
  }

  /**
   * 数値から型区分を取得する
   * 
   * @param value 型区分の数値
   * @return 型区分
   */
  public static PropertyTypeDivEnum valueOf(int value) {
    switch (value) {
    case 1:
      // 文字列
      return String;
    case 2:
      // 数値
      return Int;
    case 3:
      // 数値（大）
      return Long;
    case 4:
      // 日付
      return Date;
    default:
      // その他場合
      return null;
    }
  }

  /**
   * 型区分の数値を取得する
   * 
   * @return 型区分の数値
   */
  public int value() {
    return this.value;
  }
}
