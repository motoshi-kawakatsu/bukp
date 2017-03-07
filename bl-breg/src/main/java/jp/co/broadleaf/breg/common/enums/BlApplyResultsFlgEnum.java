//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:BL申請結果区分のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * BL申請結果区分
 */
public enum BlApplyResultsFlgEnum {

  /**
   * 0:未申請
   */
  NoApply(0, "未申請", "未"),

  /**
   * 1:申請中
   */
  Apply(1, "申請中", "中"),

  /**
   * 2:申請承認
   */
  Approval(2, "申請承認", "済"),
  
  /**
   * 3:再提出
   */
  ApplyAgain(3, "再提出", "再"),

  /**
   * 9:申請却下
   */
  Reject(9, "申請戻し", "却");

  /**
   * BL申請結果区分の数値
   */
  private int value = 0;

  /**
   * BL申請結果区分の名称
   */
  private String name = null;

  /**
   * BL申請結果区分の略称
   */
  private String ryakuName = null;

  /**
   * コンストラクタ
   * 
   * @param value BL申請結果区分の数値
   * @param name BL申請結果区分の名称
   * @param ryakuName BL申請結果区分の略称
   */
  BlApplyResultsFlgEnum(int value, String name, String ryakuName) {
    this.value = value;
    this.name = name;
    this.ryakuName = ryakuName;
  }

  /**
   * BL申請結果区分の数値を取得する
   *
   * @return BL申請結果区分の数値
   */
  public int getValue() {
    return this.value;
  }

  /**
   * BL申請結果区分の名称を取得する
   *
   * @return BL申請結果区分の名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * BL申請結果区分の略称を取得する
   *
   * @return BL申請結果区分の略称
   */
  public String getRyakuName() {
    return this.ryakuName;
  }

  /**
   * 数値からBL申請結果区分を取得する
   *
   * @param value BL申請結果区分の数値
   * @return BL申請結果区分
   */
  public static BlApplyResultsFlgEnum valueof(int value) {
    switch (value) {
    case 0:
      // 未申請
      return NoApply;
    case 1:
      // 申請中
      return Apply;
    case 2:
      // 申請承認
      return Approval;
    case 3:
      // 再提出
      return ApplyAgain;
    case 9:
      // 申請却下
      return Reject;
    default:
      // その他場合
      return null;
    }
  }

}
