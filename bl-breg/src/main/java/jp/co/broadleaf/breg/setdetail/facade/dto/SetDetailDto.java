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
package jp.co.broadleaf.breg.setdetail.facade.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *　セート詳細のDTOクラスです
 * </pre>
 */
public class SetDetailDto implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -8659485119253408475L;

  /**
   * 優良設定詳細コード１
   */
  private int prmSetDtlNo1;

  /**
   * 部品メーカーコード
   */
  private int partsMakerCd;

  /**
   * 商品中分類コード
   */
  private int goodsMGroup;
  /**
   * BLコード
   */
  private int tbsPartsCode;
  /**
   * セット親品番
   */
  private String setMainPartsNo;
  /**
   * 作成日
   */
  private Date insDtTime;

  /**
   * 更新日
   */
  private Date updDtTime;
  /**
   * 申請状態
   */
  private int applyCondition;
  /**
   * 処理区分
   */
  private int manageKbn;
  /**
   * セット表示順位
   */
  private int setDispOrder;
  /**
   * セット子品番
   */
  private String setSubPartsNo;

  /**
   * セット品名
   */
  private String setKanaName;

  /**
   * セット名称
   */
  private String setName;
  /**
   * セットQTY
   */
  private Double setQty;

  /**
   * セット規格・特記事項
   */
  private String setSpecialNote;
  /**
   * セット規格・特記事項C
   */
  private String setSpecialNoteC;
  /**
   * 適用日付
   */
  private String applyDate;

  /**
   * 削除依頼区分
   */
  private String deleteFlag;
  /**
   * 削除理由
   */
  private String deleteReason;

  /**
   * エラー区分
   */
  private String errorFlag;

  /**
   * エラー内容
   */
  private String errorDetail;

  /**
   * BL登録区分
   */
  private short blLoginDiv;

  /**
   * <pre>
   * 【prmSetDtlNo1】を取得する。
   * </pre>
   *
   * @return 【prmSetDtlNo1】
   */
  public int getPrmSetDtlNo1() {
    return prmSetDtlNo1;
  }

  /**
   * <pre>
   * 【prmSetDtlNo1】を設定する。
   * </pre>
   *
   * @param prmSetDtlNo1 【prmSetDtlNo1】
   */
  public void setPrmSetDtlNo1(int prmSetDtlNo1) {
    this.prmSetDtlNo1 = prmSetDtlNo1;
  }

  /**
   * <pre>
   * 【partsMakerCd】を取得する。
   * </pre>
   *
   * @return 【partsMakerCd】
   */
  public int getPartsMakerCd() {
    return partsMakerCd;
  }

  /**
   * <pre>
   * 【setSpecialNoteC】を取得する。
   * </pre>
   *
   * @return 【setSpecialNoteC】
   */
  public String getSetSpecialNoteC() {
    return setSpecialNoteC;
  }

  /**
   * <pre>
   * 【setSpecialNoteC】を設定する。
   * </pre>
   *
   * @param setSpecialNoteC 【setSpecialNoteC】
   */
  public void setSetSpecialNoteC(String setSpecialNoteC) {
    this.setSpecialNoteC = setSpecialNoteC;
  }

  /**
   * <pre>
   * 【partsMakerCd】を設定する。
   * </pre>
   *
   * @param partsMakerCd 【partsMakerCd】
   */
  public void setPartsMakerCd(int partsMakerCd) {
    this.partsMakerCd = partsMakerCd;
  }

  /**
   * <pre>
   * 【goodsMGroup】を取得する。
   * </pre>
   *
   * @return 【goodsMGroup】
   */
  public int getGoodsMGroup() {
    return goodsMGroup;
  }

  /**
   * <pre>
   * 【goodsMGroup】を設定する。
   * </pre>
   *
   * @param goodsMGroup 【goodsMGroup】
   */
  public void setGoodsMGroup(int goodsMGroup) {
    this.goodsMGroup = goodsMGroup;
  }

  /**
   * <pre>
   * 【tbsPartsCode】を取得する。
   * </pre>
   *
   * @return 【tbsPartsCode】
   */
  public int getTbsPartsCode() {
    return tbsPartsCode;
  }

  /**
   * <pre>
   * 【tbsPartsCode】を設定する。
   * </pre>
   *
   * @param tbsPartsCode 【tbsPartsCode】
   */
  public void setTbsPartsCode(int tbsPartsCode) {
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
   * 【insDtTime】を取得する。
   * </pre>
   *
   * @return 【insDtTime】
   */
  public Date getInsDtTime() {
    return insDtTime;
  }

  /**
   * <pre>
   * 【insDtTime】を設定する。
   * </pre>
   *
   * @param insDtTime 【insDtTime】
   */
  public void setInsDtTime(Date insDtTime) {
    this.insDtTime = insDtTime;
  }

  /**
   * <pre>
   * 【updDtTime】を取得する。
   * </pre>
   *
   * @return 【updDtTime】
   */
  public Date getUpdDtTime() {
    return updDtTime;
  }

  /**
   * <pre>
   * 【updDtTime】を設定する。
   * </pre>
   *
   * @param updDtTime 【updDtTime】
   */
  public void setUpdDtTime(Date updDtTime) {
    this.updDtTime = updDtTime;
  }

  /**
   * <pre>
   * 【applyCondition】を取得する。
   * </pre>
   *
   * @return 【applyCondition】
   */
  public int getApplyCondition() {
    return applyCondition;
  }

  /**
   * <pre>
   * 【applyCondition】を設定する。
   * </pre>
   *
   * @param applyCondition 【applyCondition】
   */
  public void setApplyCondition(int applyCondition) {
    this.applyCondition = applyCondition;
  }

  /**
   * <pre>
   * 【manageKbn】を取得する。
   * </pre>
   *
   * @return 【manageKbn】
   */
  public int getManageKbn() {
    return manageKbn;
  }

  /**
   * <pre>
   * 【manageKbn】を設定する。
   * </pre>
   *
   * @param manageKbn 【manageKbn】
   */
  public void setManageKbn(int manageKbn) {
    this.manageKbn = manageKbn;
  }

  /**
   * <pre>
   * 【setDispOrder】を取得する。
   * </pre>
   *
   * @return 【setDispOrder】
   */
  public int getSetDispOrder() {
    return setDispOrder;
  }

  /**
   * <pre>
   * 【setDispOrder】を設定する。
   * </pre>
   *
   * @param setDispOrder 【setDispOrder】
   */
  public void setSetDispOrder(int setDispOrder) {
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
   * 【applyDate】を取得する。
   * </pre>
   *
   * @return 【applyDate】
   */
  public String getApplyDate() {
    return applyDate;
  }

  /**
   * <pre>
   * 【applyDate】を設定する。
   * </pre>
   *
   * @param applyDate 【applyDate】
   */
  public void setApplyDate(String applyDate) {
    this.applyDate = applyDate;
  }

  /**
   * <pre>
   * 【deleteFlag】を取得する。
   * </pre>
   *
   * @return 【deleteFlag】
   */
  public String getDeleteFlag() {
    return deleteFlag;
  }

  /**
   * <pre>
   * 【deleteFlag】を設定する。
   * </pre>
   *
   * @param deleteFlag 【deleteFlag】
   */
  public void setDeleteFlag(String deleteFlag) {
    this.deleteFlag = deleteFlag;
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
   * 【errorFlag】を取得する。
   * </pre>
   *
   * @return 【errorFlag】
   */
  public String getErrorFlag() {
    return errorFlag;
  }

  /**
   * <pre>
   * 【errorFlag】を設定する。
   * </pre>
   *
   * @param errorFlag 【errorFlag】
   */
  public void setErrorFlag(String errorFlag) {
    this.errorFlag = errorFlag;
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
   * 【blLoginDiv】を取得する。
   * </pre>
   *
   * @return 【blLoginDiv】
   */
  public short getBlLoginDiv() {
    return blLoginDiv;
  }

  /**
   * <pre>
   * 【blLoginDiv】を設定する。
   * </pre>
   *
   * @param blLoginDiv 【blLoginDiv】
   */
  public void setBlLoginDiv(short blLoginDiv) {
    this.blLoginDiv = blLoginDiv;
  }
}
