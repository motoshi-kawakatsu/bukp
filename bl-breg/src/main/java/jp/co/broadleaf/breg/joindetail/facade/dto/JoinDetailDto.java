//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 李超傑
// 作 成 日       2017/02/06   修正内容 : 結合詳細：新規作成
package jp.co.broadleaf.breg.joindetail.facade.dto;

import java.io.Serializable;

/**
 * <pre>
 *　結合詳細のDTOクラスです
 * </pre>
 */
public class JoinDetailDto implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3331250013055162792L;

	/**
	 * 優良設定詳細コード１
	 */
	private String prmSetDtlNo1;

	/**
	 * 部品メーカーコード
	 */
	private String partsMakerCd;

	/**
	 * 商品中分類コード
	 */
	private String goodsMGroup;
	/**
	 * 申請状態
	 */
	private String applyCondition;
	/**
	 * 処理区分
	 */
	private String manageKbn;
	/**
	 * エラー区分
	 */
	private String errorFlg;
	/**
	 * BLコード
	 */
	private String tbsPartsCode;
	/**
	 * 結合元メーカーコード
	 */
	private String joinSourceMakerCode;

	/**
	 * 結合元品番(－付き品番)
	 */
	private String joinSourPartsNoWithH;

	/**
	 * 優良設定詳細コード２/種別コード
	 */
	private String prmSetDtlNo2;

	/**
	 * 結合表示順位
	 */
	private Integer joinDispOrder;
	/**
	 * 結合先品番(－付き品番)/優良品番
	 */
	private String joinDestPartsNo;
	/**
	 * 優良部品名称
	 */
	private String primePartsName;
	/**
	 * QTY
	 */
	private Double joinQty;

	/**
	 * 結合規格・特記事項
	 */
	private String joinSpecialNote;

	/**
	 * 結合規格・特記事項
	 */
	private String primePartsSpecialNoteC;

	/**
	 * 削除依頼区分
	 */
	private String deleteFlg;
	/**
	 * 削除理由
	 */
	private String deleteReason;
	/**
	 * 適用日付
	 */
	private String startTime;
	/**
	 * エラー内容
	 */
	private String errorDetail;
	/**
	 * チェック区分
	 */
	private String checkFlg;

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
	 * @param prmSetDtlNo1
	 *            【prmSetDtlNo1】
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
	 * @param partsMakerCd
	 *            【partsMakerCd】
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
	 * @param goodsMGroup
	 *            【goodsMGroup】
	 */
	public void setGoodsMGroup(String goodsMGroup) {
		this.goodsMGroup = goodsMGroup;
	}

	/**
	 * <pre>
	 * 【applyCondition】を取得する。
	 * </pre>
	 *
	 * @return 【applyCondition】
	 */
	public String getApplyCondition() {
		return applyCondition;
	}

	/**
	 * <pre>
	 * 【applyCondition】を設定する。
	 * </pre>
	 *
	 * @param applyCondition
	 *            【applyCondition】
	 */
	public void setApplyCondition(String applyCondition) {
		this.applyCondition = applyCondition;
	}

	/**
	 * <pre>
	 * 【manageKbn】を取得する。
	 * </pre>
	 *
	 * @return 【manageKbn】
	 */
	public String getManageKbn() {
		return manageKbn;
	}

	/**
	 * <pre>
	 * 【manageKbn】を設定する。
	 * </pre>
	 *
	 * @param manageKbn
	 *            【manageKbn】
	 */
	public void setManageKbn(String manageKbn) {
		this.manageKbn = manageKbn;
	}

	/**
	 * <pre>
	 * 【errorFlg】を取得する。
	 * </pre>
	 *
	 * @return 【errorFlg】
	 */
	public String getErrorFlg() {
		return errorFlg;
	}

	/**
	 * <pre>
	 * 【errorFlg】を設定する。
	 * </pre>
	 *
	 * @param errorFlg
	 *            【errorFlg】
	 */
	public void setErrorFlg(String errorFlg) {
		this.errorFlg = errorFlg;
	}

	/**
	 * <pre>
	 * 【tbsPartsCode】を取得する。
	 * </pre>
	 *
	 * @return 【tbsPartsCode】
	 */
	public String getTbsPartsCode() {
		return tbsPartsCode;
	}

	/**
	 * <pre>
	 * 【tbsPartsCode】を設定する。
	 * </pre>
	 *
	 * @param tbsPartsCode
	 *            【tbsPartsCode】
	 */
	public void setTbsPartsCode(String tbsPartsCode) {
		this.tbsPartsCode = tbsPartsCode;
	}

	/**
	 * <pre>
	 * 【joinSourceMakerCode】を取得する。
	 * </pre>
	 *
	 * @return 【joinSourceMakerCode】
	 */
	public String getJoinSourceMakerCode() {
		return joinSourceMakerCode;
	}

	/**
	 * <pre>
	 * 【joinSourceMakerCode】を設定する。
	 * </pre>
	 *
	 * @param joinSourceMakerCode
	 *            【joinSourceMakerCode】
	 */
	public void setJoinSourceMakerCode(String joinSourceMakerCode) {
		this.joinSourceMakerCode = joinSourceMakerCode;
	}

	/**
	 * <pre>
	 * 【joinSourPartsNoWithH】を取得する。
	 * </pre>
	 *
	 * @return 【joinSourPartsNoWithH】
	 */
	public String getJoinSourPartsNoWithH() {
		return joinSourPartsNoWithH;
	}

	/**
	 * <pre>
	 * 【joinSourPartsNoWithH】を設定する。
	 * </pre>
	 *
	 * @param joinSourPartsNoWithH
	 *            【joinSourPartsNoWithH】
	 */
	public void setJoinSourPartsNoWithH(String joinSourPartsNoWithH) {
		this.joinSourPartsNoWithH = joinSourPartsNoWithH;
	}

	/**
	 * <pre>
	 * 【prmSetDtlNo2】を取得する。
	 * </pre>
	 *
	 * @return 【prmSetDtlNo2】
	 */
	public String getPrmSetDtlNo2() {
		return prmSetDtlNo2;
	}

	/**
	 * <pre>
	 * 【prmSetDtlNo2】を設定する。
	 * </pre>
	 *
	 * @param prmSetDtlNo2
	 *            【prmSetDtlNo2】
	 */
	public void setPrmSetDtlNo2(String prmSetDtlNo2) {
		this.prmSetDtlNo2 = prmSetDtlNo2;
	}

	/**
	 * <pre>
	 * 【joinDispOrder】を取得する。
	 * </pre>
	 *
	 * @return 【joinDispOrder】
	 */
	public Integer getJoinDispOrder() {
		return joinDispOrder;
	}

	/**
	 * <pre>
	 * 【joinDispOrder】を設定する。
	 * </pre>
	 *
	 * @param joinDispOrder
	 *            【joinDispOrder】
	 */
	public void setJoinDispOrder(Integer joinDispOrder) {
		this.joinDispOrder = joinDispOrder;
	}

	/**
	 * <pre>
	 * 【joinDestPartsNo】を取得する。
	 * </pre>
	 *
	 * @return 【joinDestPartsNo】
	 */
	public String getJoinDestPartsNo() {
		return joinDestPartsNo;
	}

	/**
	 * <pre>
	 * 【joinDestPartsNo】を設定する。
	 * </pre>
	 *
	 * @param joinDestPartsNo
	 *            【joinDestPartsNo】
	 */
	public void setJoinDestPartsNo(String joinDestPartsNo) {
		this.joinDestPartsNo = joinDestPartsNo;
	}

	/**
	 * <pre>
	 * 【primePartsName】を取得する。
	 * </pre>
	 *
	 * @return 【primePartsName】
	 */
	public String getPrimePartsName() {
		return primePartsName;
	}

	/**
	 * <pre>
	 * 【primePartsName】を設定する。
	 * </pre>
	 *
	 * @param primePartsName
	 *            【primePartsName】
	 */
	public void setPrimePartsName(String primePartsName) {
		this.primePartsName = primePartsName;
	}

	/**
	 * <pre>
	 * 【joinQty】を取得する。
	 * </pre>
	 *
	 * @return 【joinQty】
	 */
	public Double getJoinQty() {
		return joinQty;
	}

	/**
	 * <pre>
	 * 【joinQty】を設定する。
	 * </pre>
	 *
	 * @param joinQty
	 *            【joinQty】
	 */
	public void setJoinQty(Double joinQty) {
		this.joinQty = joinQty;
	}

	/**
	 * <pre>
	 * 【joinSpecialNote】を取得する。
	 * </pre>
	 *
	 * @return 【joinSpecialNote】
	 */
	public String getJoinSpecialNote() {
		return joinSpecialNote;
	}

	/**
	 * <pre>
	 * 【joinSpecialNote】を設定する。
	 * </pre>
	 *
	 * @param joinSpecialNote
	 *            【joinSpecialNote】
	 */
	public void setJoinSpecialNote(String joinSpecialNote) {
		this.joinSpecialNote = joinSpecialNote;
	}

	/**
	 * <pre>
	 * 【primePartsSpecialNoteC】を取得する。
	 * </pre>
	 *
	 * @return 【primePartsSpecialNoteC】
	 */
	public String getPrimePartsSpecialNoteC() {
		return primePartsSpecialNoteC;
	}

	/**
	 * <pre>
	 * 【primePartsSpecialNoteC】を設定する。
	 * </pre>
	 *
	 * @param primePartsSpecialNoteC
	 *            【primePartsSpecialNoteC】
	 */
	public void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC) {
		this.primePartsSpecialNoteC = primePartsSpecialNoteC;
	}

	/**
	 * <pre>
	 * 【deleteFlg】を取得する。
	 * </pre>
	 *
	 * @return 【deleteFlg】
	 */
	public String getDeleteFlg() {
		return deleteFlg;
	}

	/**
	 * <pre>
	 * 【deleteFlg】を設定する。
	 * </pre>
	 *
	 * @param deleteFlg
	 *            【deleteFlg】
	 */
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	/**
	 * <pre>
	 * 【deleteReason】を取得する。
	 * </pre>
	 *
	 * @return 【deleteReason】
	 */
	public String getDeleteReason() {
		return deleteReason;
	}

	/**
	 * <pre>
	 * 【deleteReason】を設定する。
	 * </pre>
	 *
	 * @param deleteReason
	 *            【deleteReason】
	 */
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	/**
	 * <pre>
	 * 【startTime】を取得する。
	 * </pre>
	 *
	 * @return 【startTime】
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * <pre>
	 * 【startTime】を設定する。
	 * </pre>
	 *
	 * @param startTime
	 *            【startTime】
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * <pre>
	 * 【errorDetail】を取得する。
	 * </pre>
	 *
	 * @return 【errorDetail】
	 */
	public String getErrorDetail() {
		return errorDetail;
	}

	/**
	 * <pre>
	 * 【errorDetail】を設定する。
	 * </pre>
	 *
	 * @param errorDetail
	 *            【errorDetail】
	 */
	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}
	
	/**
	 * <pre>
	 * 【checkFlg】を取得する。
	 * </pre>
	 *
	 * @return 【checkFlg】
	 */
	public String getCheckFlg() {
		return checkFlg;
	}

	/**
	 * <pre>
	 * 【checkFlg】を設定する。
	 * </pre>
	 *
	 * @param checkFlg
	 *            【checkFlg】
	 */
	public void setCheckFlg(String checkFlg) {
		this.checkFlg = checkFlg;
	}

}
