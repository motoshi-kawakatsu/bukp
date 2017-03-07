//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : chenlong
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.setlist.form;

import java.io.Serializable;

/**
 * <pre>
 * setlist page's grid's attributes
 * </pre>
 */
public class SetMasterGridForm implements Serializable{

  /**  */
  private static final long serialVersionUID = -1543792495809298187L;

  /**
   * check
   */
  private Boolean check;
  
  /** 
   * compareFlg
   *  */
  public String compareFlag;
  /**
   * 隠匿域
   */
  public Integer hiddenArea;
  /**
   * sort
   */
  public String sort;
  /**
   * startTimeStart
   */
  public String startTimeStart;
  /**
   * startTimeEnd
   */
  public String startTimeEnd;
  /**
   * updDtTimeStart
   */
  public String updDtTimeStart;
  /**
   * updDtTimeEnd
   */
  public String updDtTimeEnd;
  /**
   * insDtTimeStart
   */
  public String insDtTimeStart;
  /**
   * insDtTimeEnd
   */
  public String insDtTimeEnd;

  /**
   * UUID
   */
  public String uuid;

  /**
   * 作成日時
   */
  public String insDtTime;

  /**
   * 更新日時
   */
  public String updDtTime;

  /**
   * 作成アカウントID
   */
  public String insAccountId;

  /**
   * 更新アカウントID
   */
  public String updAccountId;

  /**
   * 論理削除区分
   */
  public String logicalDelDiv;

  /**
   * 優良設定詳細コード１
   */
  public String prmSetDtlNo1;

  /**
   * 部品メーカーコード
   */
  public String partsMakerCd;

  /**
   * 商品中分類コード
   */
  public String goodsMGroup;

  /**
   * BLコード
   */
  public String tbsPartsCode;

  /**
   * セット親品番
   */
  public String setMainPartsNo;

  /**
   * セット表示順位
   */
  public String setDispOrder;

  /**
   * セット子品番
   */
  public String setSubPartsNo;

  /**
   * 品名
   */
  public String setKanaName;

  /**
   * セット名称
   */
  public String setName;

  /**
   * セットQTY
   */
  public String setQty;

  /**
   * セット規格・特記事項
   */
  public String setSpecialNote;

  /**
   * 優良部品規格・特記事項(C向け)
   */
  public String primePartsSpecialNoteC;

  /**
   * 適用日付
   */
  public String startTime;

  /**
   * チェック区分
   */
  public String checkFlg;

  /**
   * データステータス
   */
  public String errorFlg;

  /**
   * BL登録区分
   */
  public String blEntryFlg;

  /**
   * インポート区分
   */
  public String importKbn;

  /**
   * 処理区分
   */
  public String manageKbn;

  /**
   * エラー内容
   */
  public String errorDetail;

  /**
   * 削除依頼区分
   */
  public String deleteFlg;

  /**
   * 削除理由
   */
  public String deleteReason;

  /**
   * 申請状態
   */
  public String applyCondition;
  
  /**
   * <pre>
   * 【check】を取得する。
   * </pre>
   *
   * @return 【check】
   */
  public Boolean getCheck() {
    return check;
  }

  /**
   * <pre>
   * 【check】を設定する。
   * </pre>
   *
   * @param check 【check】
   */
  public void setCheck(Boolean check) {
    this.check = check;
  }

  /**
   * <pre>
   * 【compareFlag】を取得する。
   * </pre>
   *
   * @return 【compareFlag】
   */
  public String getCompareFlag() {
    return compareFlag;
  }

  /**
   * <pre>
   * 【compareFlag】を設定する。
   * </pre>
   *
   * @param compareFlag 【compareFlag】
   */
  public void setCompareFlag(String compareFlag) {
    this.compareFlag = compareFlag;
  }

  /**
   * <pre>
   * 【sort】を取得する。
   * </pre>
   *
   * @return 【sort】
   */
  public String getSort() {
    return sort;
  }

  /**
   * <pre>
   * 【sort】を設定する。
   * </pre>
   *
   * @param sort 【sort】
   */
  public void setSort(String sort) {
    this.sort = sort;
  }

  /**
   * <pre>
   * 【startTimeStart】を取得する。
   * </pre>
   *
   * @return 【startTimeStart】
   */
  public String getStartTimeStart() {
    return startTimeStart;
  }

  /**
   * <pre>
   * 【startTimeStart】を設定する。
   * </pre>
   *
   * @param startTimeStart 【startTimeStart】
   */
  public void setStartTimeStart(String startTimeStart) {
    this.startTimeStart = startTimeStart;
  }

  /**
   * <pre>
   * 【startTimeEnd】を取得する。
   * </pre>
   *
   * @return 【startTimeEnd】
   */
  public String getStartTimeEnd() {
    return startTimeEnd;
  }

  /**
   * <pre>
   * 【startTimeEnd】を設定する。
   * </pre>
   *
   * @param startTimeEnd 【startTimeEnd】
   */
  public void setStartTimeEnd(String startTimeEnd) {
    this.startTimeEnd = startTimeEnd;
  }

  /**
   * <pre>
   * 【updDtTimeStart】を取得する。
   * </pre>
   *
   * @return 【updDtTimeStart】
   */
  public String getUpdDtTimeStart() {
    return updDtTimeStart;
  }

  /**
   * <pre>
   * 【updDtTimeStart】を設定する。
   * </pre>
   *
   * @param updDtTimeStart 【updDtTimeStart】
   */
  public void setUpdDtTimeStart(String updDtTimeStart) {
    this.updDtTimeStart = updDtTimeStart;
  }

  /**
   * <pre>
   * 【updDtTimeEnd】を取得する。
   * </pre>
   *
   * @return 【updDtTimeEnd】
   */
  public String getUpdDtTimeEnd() {
    return updDtTimeEnd;
  }

  /**
   * <pre>
   * 【updDtTimeEnd】を設定する。
   * </pre>
   *
   * @param updDtTimeEnd 【updDtTimeEnd】
   */
  public void setUpdDtTimeEnd(String updDtTimeEnd) {
    this.updDtTimeEnd = updDtTimeEnd;
  }

  /**
   * <pre>
   * 【insDtTimeStart】を取得する。
   * </pre>
   *
   * @return 【insDtTimeStart】
   */
  public String getInsDtTimeStart() {
    return insDtTimeStart;
  }

  /**
   * <pre>
   * 【insDtTimeStart】を設定する。
   * </pre>
   *
   * @param insDtTimeStart 【insDtTimeStart】
   */
  public void setInsDtTimeStart(String insDtTimeStart) {
    this.insDtTimeStart = insDtTimeStart;
  }

  /**
   * <pre>
   * 【insDtTimeEnd】を取得する。
   * </pre>
   *
   * @return 【insDtTimeEnd】
   */
  public String getInsDtTimeEnd() {
    return insDtTimeEnd;
  }

  /**
   * <pre>
   * 【insDtTimeEnd】を設定する。
   * </pre>
   *
   * @param insDtTimeEnd 【insDtTimeEnd】
   */
  public void setInsDtTimeEnd(String insDtTimeEnd) {
    this.insDtTimeEnd = insDtTimeEnd;
  }

  /**
   * <pre>
   * 【uuid】を取得する。
   * </pre>
   *
   * @return 【uuid】
   */
  public String getUuid() {
    return uuid;
  }

  /**
   * <pre>
   * 【uuid】を設定する。
   * </pre>
   *
   * @param uuid 【uuid】
   */
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  /**
   * <pre>
   * 【insDtTime】を取得する。
   * </pre>
   *
   * @return 【insDtTime】
   */
  public String getInsDtTime() {
    return insDtTime;
  }

  /**
   * <pre>
   * 【insDtTime】を設定する。
   * </pre>
   *
   * @param insDtTime 【insDtTime】
   */
  public void setInsDtTime(String insDtTime) {
    this.insDtTime = insDtTime;
  }

  /**
   * <pre>
   * 【updDtTime】を取得する。
   * </pre>
   *
   * @return 【updDtTime】
   */
  public String getUpdDtTime() {
    return updDtTime;
  }

  /**
   * <pre>
   * 【updDtTime】を設定する。
   * </pre>
   *
   * @param updDtTime 【updDtTime】
   */
  public void setUpdDtTime(String updDtTime) {
    this.updDtTime = updDtTime;
  }

  /**
   * <pre>
   * 【insAccountId】を取得する。
   * </pre>
   *
   * @return 【insAccountId】
   */
  public String getInsAccountId() {
    return insAccountId;
  }

  /**
   * <pre>
   * 【insAccountId】を設定する。
   * </pre>
   *
   * @param insAccountId 【insAccountId】
   */
  public void setInsAccountId(String insAccountId) {
    this.insAccountId = insAccountId;
  }

  /**
   * <pre>
   * 【updAccountId】を取得する。
   * </pre>
   *
   * @return 【updAccountId】
   */
  public String getUpdAccountId() {
    return updAccountId;
  }

  /**
   * <pre>
   * 【updAccountId】を設定する。
   * </pre>
   *
   * @param updAccountId 【updAccountId】
   */
  public void setUpdAccountId(String updAccountId) {
    this.updAccountId = updAccountId;
  }

  /**
   * <pre>
   * 【logicalDelDiv】を取得する。
   * </pre>
   *
   * @return 【logicalDelDiv】
   */
  public String getLogicalDelDiv() {
    return logicalDelDiv;
  }

  /**
   * <pre>
   * 【logicalDelDiv】を設定する。
   * </pre>
   *
   * @param logicalDelDiv 【logicalDelDiv】
   */
  public void setLogicalDelDiv(String logicalDelDiv) {
    this.logicalDelDiv = logicalDelDiv;
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
   * @param tbsPartsCode 【tbsPartsCode】
   */
  public void setTbsPartsCode(String tbsPartsCode) {
    this.tbsPartsCode = tbsPartsCode;
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

  /**
   * <pre>
   * 【setDispOrder】を取得する。
   * </pre>
   *
   * @return 【setDispOrder】
   */
  public String getSetDispOrder() {
    return setDispOrder;
  }

  /**
   * <pre>
   * 【setDispOrder】を設定する。
   * </pre>
   *
   * @param setDispOrder 【setDispOrder】
   */
  public void setSetDispOrder(String setDispOrder) {
    this.setDispOrder = setDispOrder;
  }

  /**
   * <pre>
   * 【setSubPartsNo】を取得する。
   * </pre>
   *
   * @return 【setSubPartsNo】
   */
  public String getSetSubPartsNo() {
    return setSubPartsNo;
  }

  /**
   * <pre>
   * 【setSubPartsNo】を設定する。
   * </pre>
   *
   * @param setSubPartsNo 【setSubPartsNo】
   */
  public void setSetSubPartsNo(String setSubPartsNo) {
    this.setSubPartsNo = setSubPartsNo;
  }

  /**
   * <pre>
   * 【setKanaName】を取得する。
   * </pre>
   *
   * @return 【setKanaName】
   */
  public String getSetKanaName() {
    return setKanaName;
  }

  /**
   * <pre>
   * 【setKanaName】を設定する。
   * </pre>
   *
   * @param setKanaName 【setKanaName】
   */
  public void setSetKanaName(String setKanaName) {
    this.setKanaName = setKanaName;
  }

  /**
   * <pre>
   * 【setName】を取得する。
   * </pre>
   *
   * @return 【setName】
   */
  public String getSetName() {
    return setName;
  }

  /**
   * <pre>
   * 【setName】を設定する。
   * </pre>
   *
   * @param setName 【setName】
   */
  public void setSetName(String setName) {
    this.setName = setName;
  }

  /**
   * <pre>
   * 【setQty】を取得する。
   * </pre>
   *
   * @return 【setQty】
   */
  public String getSetQty() {
    return setQty;
  }

  /**
   * <pre>
   * 【setQty】を設定する。
   * </pre>
   *
   * @param setQty 【setQty】
   */
  public void setSetQty(String setQty) {
    this.setQty = setQty;
  }

  /**
   * <pre>
   * 【setSpecialNote】を取得する。
   * </pre>
   *
   * @return 【setSpecialNote】
   */
  public String getSetSpecialNote() {
    return setSpecialNote;
  }

  /**
   * <pre>
   * 【setSpecialNote】を設定する。
   * </pre>
   *
   * @param setSpecialNote 【setSpecialNote】
   */
  public void setSetSpecialNote(String setSpecialNote) {
    this.setSpecialNote = setSpecialNote;
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
   * @param primePartsSpecialNoteC 【primePartsSpecialNoteC】
   */
  public void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC) {
    this.primePartsSpecialNoteC = primePartsSpecialNoteC;
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
   * @param startTime 【startTime】
   */
  public void setStartTime(String startTime) {
    this.startTime = startTime;
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
   * @param checkFlg 【checkFlg】
   */
  public void setCheckFlg(String checkFlg) {
    this.checkFlg = checkFlg;
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
   * @param errorFlg 【errorFlg】
   */
  public void setErrorFlg(String errorFlg) {
    this.errorFlg = errorFlg;
  }

  /**
   * <pre>
   * 【blEntryFlg】を取得する。
   * </pre>
   *
   * @return 【blEntryFlg】
   */
  public String getBlEntryFlg() {
    return blEntryFlg;
  }

  /**
   * <pre>
   * 【blEntryFlg】を設定する。
   * </pre>
   *
   * @param blEntryFlg 【blEntryFlg】
   */
  public void setBlEntryFlg(String blEntryFlg) {
    this.blEntryFlg = blEntryFlg;
  }

  /**
   * <pre>
   * 【importKbn】を取得する。
   * </pre>
   *
   * @return 【importKbn】
   */
  public String getImportKbn() {
    return importKbn;
  }

  /**
   * <pre>
   * 【importKbn】を設定する。
   * </pre>
   *
   * @param importKbn 【importKbn】
   */
  public void setImportKbn(String importKbn) {
    this.importKbn = importKbn;
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
   * @param manageKbn 【manageKbn】
   */
  public void setManageKbn(String manageKbn) {
    this.manageKbn = manageKbn;
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
   * @param errorDetail 【errorDetail】
   */
  public void setErrorDetail(String errorDetail) {
    this.errorDetail = errorDetail;
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
   * @param deleteFlg 【deleteFlg】
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
   * @param deleteReason 【deleteReason】
   */
  public void setDeleteReason(String deleteReason) {
    this.deleteReason = deleteReason;
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
   * @param applyCondition 【applyCondition】
   */
  public void setApplyCondition(String applyCondition) {
    this.applyCondition = applyCondition;
  }

  /**
   * <pre>
   * 【hiddenArea】を取得する。
   * </pre>
   *
   * @return 【hiddenArea】
   */
  public Integer getHiddenArea() {
    return hiddenArea;
  }

  /**
   * <pre>
   * 【hiddenArea】を設定する。
   * </pre>
   *
   * @param hiddenArea 【hiddenArea】
   */
  public void setHiddenArea(Integer hiddenArea) {
    this.hiddenArea = hiddenArea;
  }

  

}
