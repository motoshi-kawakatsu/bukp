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
 * /**
 * 結合マスタ(メーカー)のDtoのクラス。
 */
public class JoinTotalinfoDto implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 申請状態
   */
  private String blApplyResultsFlg;
  /**
   * UUID
   */
  private String uuid;

  /**
   * 作成日時
   */
  private String insDtTime;

  /**
   * 更新日時
   */
  private String updDtTime;

  /**
   * 作成アカウントID
   */
  private int insAccountId;

  /**
   * 更新アカウントID
   */
  private int updAccountId;

  /**
   * 論理削除区分
   */
  private int logicalDelDiv;

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
   * 結合元メーカーコード
   */
  private int joinSourceMakerCode;

  /**
   * 優良設定詳細コード２
   */
  private int prmSetDtlNo2;

  /**
   * 結合元品番(－付き品番)
   */
  private String joinSourPartsNoWithH;

  /**
   * 結合表示順位
   */
  private int joinDispOrder;

  /**
   * 結合先品番(－付き品番)
   */
  private String joinDestPartsNo;

  /**
   * 結合QTY
   */
  private Double joinQty;

  /**
   * 結合規格・特記事項
   */
  private String joinSpecialNote;

  /**
   * 優良部品規格・特記事項(C向け)
   */
  private String primePartsSpecialNotec;

  /**
   * 適用日付
   */
  private String startTime;

  /**
   * 削除時申請理由
   */
  private String deleteReason;
  
  /**
   * 申請No
   */
  private int applyNo;

  /**
   * データステータス
   */
  private Short errFlg;

  /**
   * BL登録区分
   */
  private String blEntryFlg;

  /**
   * 処理区分
   */
  private String dealFlg;

  /**
   * 削除依頼区分
   */
  private String deleteFlg;
  /**
   * カラムステータス
   */
  public String columnStatus;
  
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
  public int getInsAccountId() {
    return insAccountId;
  }

  /**
   * <pre>
   * 【insAccountId】を設定する。
   * </pre>
   *
   * @param insAccountId 【insAccountId】
   */
  public void setInsAccountId(int insAccountId) {
    this.insAccountId = insAccountId;
  }

  /**
   * <pre>
   * 【updAccountId】を取得する。
   * </pre>
   *
   * @return 【updAccountId】
   */
  public int getUpdAccountId() {
    return updAccountId;
  }

  /**
   * <pre>
   * 【updAccountId】を設定する。
   * </pre>
   *
   * @param updAccountId 【updAccountId】
   */
  public void setUpdAccountId(int updAccountId) {
    this.updAccountId = updAccountId;
  }

  /**
   * <pre>
   * 【logicalDelDiv】を取得する。
   * </pre>
   *
   * @return 【logicalDelDiv】
   */
  public int getLogicalDelDiv() {
    return logicalDelDiv;
  }

  /**
   * <pre>
   * 【logicalDelDiv】を設定する。
   * </pre>
   *
   * @param logicalDelDiv 【logicalDelDiv】
   */
  public void setLogicalDelDiv(int logicalDelDiv) {
    this.logicalDelDiv = logicalDelDiv;
  }

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
   * 【joinSourceMakerCode】を取得する。
   * </pre>
   *
   * @return 【joinSourceMakerCode】
   */
  public int getJoinSourceMakerCode() {
    return joinSourceMakerCode;
  }

  /**
   * <pre>
   * 【joinSourceMakerCode】を設定する。
   * </pre>
   *
   * @param joinSourceMakerCode 【joinSourceMakerCode】
   */
  public void setJoinSourceMakerCode(int joinSourceMakerCode) {
    this.joinSourceMakerCode = joinSourceMakerCode;
  }

  /**
   * <pre>
   * 【prmSetDtlNo2】を取得する。
   * </pre>
   *
   * @return 【prmSetDtlNo2】
   */
  public int getPrmSetDtlNo2() {
    return prmSetDtlNo2;
  }

  /**
   * <pre>
   * 【prmSetDtlNo2】を設定する。
   * </pre>
   *
   * @param prmSetDtlNo2 【prmSetDtlNo2】
   */
  public void setPrmSetDtlNo2(int prmSetDtlNo2) {
    this.prmSetDtlNo2 = prmSetDtlNo2;
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
   * @param joinSourPartsNoWithH 【joinSourPartsNoWithH】
   */
  public void setJoinSourPartsNoWithH(String joinSourPartsNoWithH) {
    this.joinSourPartsNoWithH = joinSourPartsNoWithH;
  }

  /**
   * <pre>
   * 【joinDispOrder】を取得する。
   * </pre>
   *
   * @return 【joinDispOrder】
   */
  public int getJoinDispOrder() {
    return joinDispOrder;
  }

  /**
   * <pre>
   * 【joinDispOrder】を設定する。
   * </pre>
   *
   * @param joinDispOrder 【joinDispOrder】
   */
  public void setJoinDispOrder(int joinDispOrder) {
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
   * @param joinDestPartsNo 【joinDestPartsNo】
   */
  public void setJoinDestPartsNo(String joinDestPartsNo) {
    this.joinDestPartsNo = joinDestPartsNo;
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
   * @param joinQty 【joinQty】
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
   * @param joinSpecialNote 【joinSpecialNote】
   */
  public void setJoinSpecialNote(String joinSpecialNote) {
    this.joinSpecialNote = joinSpecialNote;
  }

  /**
   * <pre>
   * 【primePartsSpecialNotec】を取得する。
   * </pre>
   *
   * @return 【primePartsSpecialNotec】
   */
  public String getPrimePartsSpecialNotec() {
    return primePartsSpecialNotec;
  }

  /**
   * <pre>
   * 【primePartsSpecialNotec】を設定する。
   * </pre>
   *
   * @param primePartsSpecialNotec 【primePartsSpecialNotec】
   */
  public void setPrimePartsSpecialNotec(String primePartsSpecialNotec) {
    this.primePartsSpecialNotec = primePartsSpecialNotec;
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
}
