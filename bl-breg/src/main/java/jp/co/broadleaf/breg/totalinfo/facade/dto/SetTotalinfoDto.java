//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 司　志超
// 作 成 日       2017/02/08     修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.totalinfo.facade.dto;

import java.io.Serializable;


/**
 * セットマスタのDtoのクラス
 */
public class SetTotalinfoDto implements Serializable {
  /** 
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;
  /**
   * 申請状態
   */
  private String blApplyResultsFlg;
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
  public Integer logicalDelDiv;
  /**
   * カラムステータス
   */
  public String columnStatus;
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
  public String setApecialNote;

  /**
   * 優良部品規格・特記事項(C向け)
   */
  public String primePartsSpecialNoteC;

  /**
   * 適用日付
   */
  public String startTime;
  
  /**
   * 申請No
   */
  public int applyNo;

  /**
   * データステータス
   */
  public Short errFlg;

  /**
   * BL登録区分
   */
  public Short blEntryFlg;

  /**
   * 処理区分
   */
  public String dealFlg;

  /**
   * 削除依頼区分
   */
  public String deleteFlg;

  /**
   * 削除理由
   */
  public String deleteReason;

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
  public Integer getLogicalDelDiv() {
    return logicalDelDiv;
  }

  /**
   * <pre>
   * 【columnStatus】を取得する。
   * </pre>
   *
   * @return 【columnStatus】
   */
  public String getColumnStatus() {
    return columnStatus;
  }

  /**
   * <pre>
   * 【columnStatus】を設定する。
   * </pre>
   *
   * @param columnStatus 【columnStatus】
   */
  public void setColumnStatus(String columnStatus) {
    this.columnStatus = columnStatus;
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
   * 【setApecialNote】を取得する。
   * </pre>
   *
   * @return 【setApecialNote】
   */
  public String getSetApecialNote() {
    return setApecialNote;
  }

  /**
   * <pre>
   * 【setApecialNote】を設定する。
   * </pre>
   *
   * @param setApecialNote 【setApecialNote】
   */
  public void setSetApecialNote(String setApecialNote) {
    this.setApecialNote = setApecialNote;
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
   * 【applyNo】を取得する。
   * </pre>
   *
   * @return 【applyNo】
   */
  public int getApplyNo() {
    return applyNo;
  }

  /**
   * <pre>
   * 【applyNo】を設定する。
   * </pre>
   *
   * @param applyNo 【applyNo】
   */
  public void setApplyNo(int applyNo) {
    this.applyNo = applyNo;
  }

  /**
   * <pre>
   * 【errFlg】を取得する。
   * </pre>
   *
   * @return 【errFlg】
   */
  public Short getErrFlg() {
    return errFlg;
  }

  /**
   * <pre>
   * 【errFlg】を設定する。
   * </pre>
   *
   * @param errFlg 【errFlg】
   */
  public void setErrFlg(Short errFlg) {
    this.errFlg = errFlg;
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
   * 【dealFlg】を取得する。
   * </pre>
   *
   * @return 【dealFlg】
   */
  public String getDealFlg() {
    return dealFlg;
  }

  /**
   * <pre>
   * 【dealFlg】を設定する。
   * </pre>
   *
   * @param dealFlg 【dealFlg】
   */
  public void setDealFlg(String dealFlg) {
    this.dealFlg = dealFlg;
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
   * 【blApplyResultsFlg】を取得する。
   * </pre>
   *
   * @return 【blApplyResultsFlg】
   */
  public String getBlApplyResultsFlg() {
    return blApplyResultsFlg;
  }

  /**
   * <pre>
   * 【blApplyResultsFlg】を設定する。
   * </pre>
   *
   * @param blApplyResultsFlg 【blApplyResultsFlg】
   */
  public void setBlApplyResultsFlg(String blApplyResultsFlg) {
    this.blApplyResultsFlg = blApplyResultsFlg;
  }
}
