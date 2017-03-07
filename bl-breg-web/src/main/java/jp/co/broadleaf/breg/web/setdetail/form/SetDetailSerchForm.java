//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 燕京
// 作 成 日       2017/02/06   修正内容 : セート詳細：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.setdetail.form;

import java.io.Serializable;
/**
 * 
 * <pre>
 * セート詳細検索用フォーム
 * </pre>
 */
public class SetDetailSerchForm implements Serializable {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -4602559845843720667L;

	/**
	 * セレクトコード
	 */
	private String prmSetDtlNo1;

	/**
	 * カーコード
	 */
	private String partsMakerCd;

	/**
	 * 分類コード
	 */
	private String goodsMGroup;

	/**
	 * セット親品番
	 */
	private String setMainPartsNo;
	/**
	 * モード
	 */
	private String mode;

	/**
	 * <pre>
	 * 【mode】を取得する。
	 * </pre>
	 *
	 * @return 【mode】
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * <pre>
	 * 【mode】を設定する。
	 * </pre>
	 *
	 * @param mode
	 *            【mode】
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * <pre>
	 * 【prmSetDtlNo1】を取得する。
	 * </pre>
	 *
	 * @return 【prmSetDtlNo1】
	 */
	public String getPrmSetDtlNo1() {
		return prmSetDtlNo1;
	}

	/**
	 * <pre>
	 * 【prmSetDtlNo1】を設定する。
	 * </pre>
	 *
	 * @param prmSetDtlNo1 【prmSetDtlNo1】
	 */
	public void setPrmSetDtlNo1(String prmSetDtlNo1) {
		this.prmSetDtlNo1 = prmSetDtlNo1;
	}

	/**
	 * <pre>
	 * 【partsMakerCd】を取得する。
	 * </pre>
	 *
	 * @return 【partsMakerCd】
	 */
	public String getPartsMakerCd() {
		return partsMakerCd;
	}

	/**
	 * <pre>
	 * 【partsMakerCd】を設定する。
	 * </pre>
	 *
	 * @param partsMakerCd 【partsMakerCd】
	 */
	public void setPartsMakerCd(String partsMakerCd) {
		this.partsMakerCd = partsMakerCd;
	}

	/**
	 * <pre>
	 * 【goodsMGroup】を取得する。
	 * </pre>
	 *
	 * @return 【goodsMGroup】
	 */
	public String getGoodsMGroup() {
		return goodsMGroup;
	}

	/**
	 * <pre>
	 * 【goodsMGroup】を設定する。
	 * </pre>
	 *
	 * @param goodsMGroup 【goodsMGroup】
	 */
	public void setGoodsMGroup(String goodsMGroup) {
		this.goodsMGroup = goodsMGroup;
	}

	/**
	 * <pre>
	 * 【setMainPartsNo】を取得する。
	 * </pre>
	 *
	 * @return 【setMainPartsNo】
	 */
	public String getSetMainPartsNo() {
		return setMainPartsNo;
	}

	/**
	 * <pre>
	 * 【setMainPartsNo】を設定する。
	 * </pre>
	 *
	 * @param setMainPartsNo 【setMainPartsNo】
	 */
	public void setSetMainPartsNo(String setMainPartsNo) {
		this.setMainPartsNo = setMainPartsNo;
	}

	
}
