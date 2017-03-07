//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:申請状態のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * 申請状態
 */
public enum ApplyConditionEnum {

  /**
   * 0:未申請
   */
  NoApply(0, "未申請", "未"),

  /**
   * 1:申請中
   */
  Apply(1, "申請中", "中"),

  /**
   * 2:承認済
   */
  Approval(2, "承認済", "済"),

  /**
   * 3:再申請
   */
  Applyagain(3, "再申請", "再");

  /**
   * 申請状態の数値
   */
  private int value = 0;

  /**
   * 申請状態の名称
   */
  private String name = null;
  /**
   * 申請状態の略称
   */
  private String abbreviation = null;

  /**
   * コンストラクタ
   * 
   * @param value 申請状態の数値
   * @param name 申請状態の名称
   * @param abbreviation 申請状態の略称
   */
  ApplyConditionEnum(int value, String name, String abbreviation) {
    this.value = value;
    this.name = name;
    this.abbreviation = abbreviation;
  }

  /**
   * 申請状態の数値を取得する
   *
   * @return 申請状態の数値
   */
  public int getValue() {
    return this.value;
  }

  /**
   * 申請状態の略称を取得する
   *
   * @return 申請状態の略称
   */
  public String getAbbreviation() {
    return this.abbreviation;
  }

  /**
   * 申請状態の名称を取得する
   *
   * @return 申請状態の名称
   */
  public String getName() {
    return this.name;
  }

  /**
   * 数値から申請状態を取得する
   *
   * @param value 申請状態の数値
   * @return 申請状態
   */
  public static ApplyConditionEnum valueof(int value) {
    
    switch (value) {
    case 0:
      // 未申請
      return NoApply;
    case 1:
      // 申請中
      return Apply;
    case 2:
      // 承認済
      return Approval;
    case 3:
      return Applyagain;
    default:
      // その他場合
      return null;
    }
  }

	/**
	 * 申請状態の数値を取得する
	 * 
	 * @param abbreviation 申請状態の略称
	 * @return 申請状態の数値
	 */
	public static int getCode(String abbreviation) {
		if (abbreviation == null) {
			return NoApply.getValue();
		}
		if (abbreviation.equals(NoApply.getAbbreviation())) {
			return NoApply.getValue();
		} else if (abbreviation.equals(NoApply.getName())) {
			return NoApply.getValue();
		}
		if (abbreviation.equals(Apply.getAbbreviation())) {
			return Apply.getValue();
		} else if (abbreviation.equals(Apply.getName())) {
			return Apply.getValue();
		}
		if (abbreviation.equals(Approval.getAbbreviation())) {
			return Approval.getValue();
		} else if (abbreviation.equals(Approval.getName())) {
			return Approval.getValue();
		}
		if (abbreviation.equals(Applyagain.getAbbreviation())) {
			return Applyagain.getValue();
		} else if (abbreviation.equals(Applyagain.getName())) {
			return Applyagain.getValue();
		}
		return NoApply.getValue();
	}
}
