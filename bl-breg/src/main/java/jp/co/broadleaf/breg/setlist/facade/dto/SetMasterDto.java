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
package jp.co.broadleaf.breg.setlist.facade.dto;

import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterI;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * セットマスタ(メーカー)のDtoのクラス。
 */
public class SetMasterDto implements SetMasterI, Serializable {
  /**  */
  private static final long serialVersionUID = 596647692740113015L;

  /**
   * check
   */
  private Boolean check;
  
  /** 
   * compareFlg
   *  */
  public String compareFlag;
  /**
   * UUID
   */
  public String uuid;

  /**
   * 作成日時
   */
  public Timestamp insDtTime;

  /**
   * 更新日時
   */
  public Timestamp updDtTime;

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
  public Integer logicalDelDiv;

  /**
   * 優良設定詳細コード１
   */
  public Integer prmSetDtlNo1;

  /**
   * 部品メーカーコード
   */
  public Integer partsMakerCd;

  /**
   * 商品中分類コード
   */
  public Integer goodsMGroup;

  /**
   * BLコード
   */
  public Integer tbsPartsCode;

  /**
   * セット親品番
   */
  public String setMainPartsNo;

  /**
   * セット表示順位
   */
  public Integer setDispOrder;

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
  public Double setQty;

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
  public Timestamp startTime;

  /**
   * チェック区分
   */
  public Short checkFlg;

  /**
   * データステータス
   */
  public Short errorFlg;

  /**
   * BL登録区分
   */
  public Short blEntryFlg;

  /**
   * インポート区分
   */
  public Short importKbn;

  /**
   * 処理区分
   */
  public Short manageKbn;

  /**
   * エラー内容
   */
  public String errorDetail;

  /**
   * 削除依頼区分
   */
  public Short deleteFlg;

  /**
   * 削除理由
   */
  public String deleteReason;

  /**
   * 申請状態
   */
  public Short applyCondition;

  

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
  public Timestamp getInsDtTime() {
    return insDtTime;
  }

  /**
   * <pre>
   * 【insDtTime】を設定する。
   * </pre>
   *
   * @param insDtTime 【insDtTime】
   */
  public void setInsDtTime(Timestamp insDtTime) {
    this.insDtTime = insDtTime;
  }

  /**
   * <pre>
   * 【updDtTime】を取得する。
   * </pre>
   *
   * @return 【updDtTime】
   */
  public Timestamp getUpdDtTime() {
    return updDtTime;
  }

  /**
   * <pre>
   * 【updDtTime】を設定する。
   * </pre>
   *
   * @param updDtTime 【updDtTime】
   */
  public void setUpdDtTime(Timestamp updDtTime) {
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
  public Integer getLogicalDelDiv() {
    return logicalDelDiv;
  }

  /**
   * <pre>
   * 【logicalDelDiv】を設定する。
   * </pre>
   *
   * @param logicalDelDiv 【logicalDelDiv】
   */
  public void setLogicalDelDiv(Integer logicalDelDiv) {
    this.logicalDelDiv = logicalDelDiv;
  }

  /**
   * <pre>
   * 【prmSetDtlNo1】を取得する。
   * </pre>
   *
   * @return 【prmSetDtlNo1】
   */
  public Integer getPrmSetDtlNo1() {
    return prmSetDtlNo1;
  }

  /**
   * <pre>
   * 【prmSetDtlNo1】を設定する。
   * </pre>
   *
   * @param prmSetDtlNo1 【prmSetDtlNo1】
   */
  public void setPrmSetDtlNo1(Integer prmSetDtlNo1) {
    this.prmSetDtlNo1 = prmSetDtlNo1;
  }

  /**
   * <pre>
   * 【partsMakerCd】を取得する。
   * </pre>
   *
   * @return 【partsMakerCd】
   */
  public Integer getPartsMakerCd() {
    return partsMakerCd;
  }

  /**
   * <pre>
   * 【partsMakerCd】を設定する。
   * </pre>
   *
   * @param partsMakerCd 【partsMakerCd】
   */
  public void setPartsMakerCd(Integer partsMakerCd) {
    this.partsMakerCd = partsMakerCd;
  }

  /**
   * <pre>
   * 【goodsMGroup】を取得する。
   * </pre>
   *
   * @return 【goodsMGroup】
   */
  public Integer getGoodsMGroup() {
    return goodsMGroup;
  }

  /**
   * <pre>
   * 【goodsMGroup】を設定する。
   * </pre>
   *
   * @param goodsMGroup 【goodsMGroup】
   */
  public void setGoodsMGroup(Integer goodsMGroup) {
    this.goodsMGroup = goodsMGroup;
  }

  /**
   * <pre>
   * 【tbsPartsCode】を取得する。
   * </pre>
   *
   * @return 【tbsPartsCode】
   */
  public Integer getTbsPartsCode() {
    return tbsPartsCode;
  }

  /**
   * <pre>
   * 【tbsPartsCode】を設定する。
   * </pre>
   *
   * @param tbsPartsCode 【tbsPartsCode】
   */
  public void setTbsPartsCode(Integer tbsPartsCode) {
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
  public Integer getSetDispOrder() {
    return setDispOrder;
  }

  /**
   * <pre>
   * 【setDispOrder】を設定する。
   * </pre>
   *
   * @param setDispOrder 【setDispOrder】
   */
  public void setSetDispOrder(Integer setDispOrder) {
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
  public Double getSetQty() {
    return setQty;
  }

  /**
   * <pre>
   * 【setQty】を設定する。
   * </pre>
   *
   * @param setQty 【setQty】
   */
  public void setSetQty(Double setQty) {
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
  public Timestamp getStartTime() {
    return startTime;
  }

  /**
   * <pre>
   * 【startTime】を設定する。
   * </pre>
   *
   * @param startTime 【startTime】
   */
  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  /**
   * <pre>
   * 【checkFlg】を取得する。
   * </pre>
   *
   * @return 【checkFlg】
   */
  public Short getCheckFlg() {
    return checkFlg;
  }

  /**
   * <pre>
   * 【checkFlg】を設定する。
   * </pre>
   *
   * @param checkFlg 【checkFlg】
   */
  public void setCheckFlg(Short checkFlg) {
    this.checkFlg = checkFlg;
  }

  /**
   * <pre>
   * 【errorFlg】を取得する。
   * </pre>
   *
   * @return 【errorFlg】
   */
  public Short getErrorFlg() {
    return errorFlg;
  }

  /**
   * <pre>
   * 【errorFlg】を設定する。
   * </pre>
   *
   * @param errorFlg 【errorFlg】
   */
  public void setErrorFlg(Short errorFlg) {
    this.errorFlg = errorFlg;
  }

  /**
   * <pre>
   * 【blEntryFlg】を取得する。
   * </pre>
   *
   * @return 【blEntryFlg】
   */
  public Short getBlEntryFlg() {
    return blEntryFlg;
  }

  /**
   * <pre>
   * 【blEntryFlg】を設定する。
   * </pre>
   *
   * @param blEntryFlg 【blEntryFlg】
   */
  public void setBlEntryFlg(Short blEntryFlg) {
    this.blEntryFlg = blEntryFlg;
  }

  /**
   * <pre>
   * 【importKbn】を取得する。
   * </pre>
   *
   * @return 【importKbn】
   */
  public Short getImportKbn() {
    return importKbn;
  }

  /**
   * <pre>
   * 【importKbn】を設定する。
   * </pre>
   *
   * @param importKbn 【importKbn】
   */
  public void setImportKbn(Short importKbn) {
    this.importKbn = importKbn;
  }

  /**
   * <pre>
   * 【manageKbn】を取得する。
   * </pre>
   *
   * @return 【manageKbn】
   */
  public Short getManageKbn() {
    return manageKbn;
  }

  /**
   * <pre>
   * 【manageKbn】を設定する。
   * </pre>
   *
   * @param manageKbn 【manageKbn】
   */
  public void setManageKbn(Short manageKbn) {
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
  public Short getDeleteFlg() {
    return deleteFlg;
  }

  /**
   * <pre>
   * 【deleteFlg】を設定する。
   * </pre>
   *
   * @param deleteFlg 【deleteFlg】
   */
  public void setDeleteFlg(Short deleteFlg) {
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
  public Short getApplyCondition() {
    return applyCondition;
  }

  /**
   * <pre>
   * 【applyCondition】を設定する。
   * </pre>
   *
   * @param applyCondition 【applyCondition】
   */
  public void setApplyCondition(Short applyCondition) {
    this.applyCondition = applyCondition;
  }

  /**
   * <pre>
   * dto&id's property isEquals
   * </pre>
   *
   * @param id SetMasterMakerId
   * @return Boolean true false
   */
  public Boolean isEqualsId(SetMasterMakerId id){
    if(this.prmSetDtlNo1==id.getPrmSetDtlNo1()
        &&this.partsMakerCd==id.getPartsMakerCd()
        &&this.setMainPartsNo.equals(getSetMainPartsNo())
        &&this.setDispOrder==id.getSetDispOrder()){
      return true;
    }else{
      return false;
    }
      
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "SetMasterDto [prmSetDtlNo1=" + prmSetDtlNo1 + ", partsMakerCd=" + partsMakerCd + ", setMainPartsNo="
        + setMainPartsNo + ", setDispOrder=" + setDispOrder + "]";
  }
  
  

}
