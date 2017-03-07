//****************************************************************************//
// システム                                    : カーパーツマネージャー
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                            作成担当 : 秦志超
// 作 成 日       2017/02/06   修正内容 : 申請詳細:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applydetail.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 結合マスタ(メーカー)のDtoのクラス。
 */
public class JoinMasterDto {
  /** UUID */
  private String uuid;
  
  /** 作成日時 */
  private String insDtTime;

  /** 更新日時 */
  private String updDtTime;

  /** 作成アカウントID */
  private String insAccountId;

  /** 更新アカウントID */
  private String updAccountId;
  
  /** 処理区分 */
  private String manageKbn;

  /** 論理削除区分 */
  private String logicalDelDiv;

  /** 優良設定詳細コード１ */
  private String prmSetDtlCd1;

  /** 部品メーカーコード */
  private int prtsMakerCd;

  /** 商品中分類コード */
  private String itemMidclassifyCd;

  /** BLコード */
  private String tbsPartsCode;

  /** 結合元メーカーコード */
  private String joinSourceMakerCode;
  
  /** 優良設定詳細コード２ */
  private String prmSetDtlCd2;
  
  /** 結合元品番(－付き品番) */
  private String joinSourcePartsno;

  /** 結合表示順位 */
  private int joinDispOrder;

  /** 結合先品番(－付き品番) */
  private String joinDestPartsno;

  /** 結合QTY */
  private double joinQty;

  /** 結合規格・特記事項 */
  private String joinSpecialNote;

  /** 優良部品規格・特記事項(C向け) */
  private String prmPrtsSpecialNote;

  /** 適用日時 */
  private String applyTime;
  
  /** 削除時申請理由 */
  public String  delReason;
  
  /** 申請状態 */
  private String applyCondition;

  /** データステータス */
  private String errorFlg;

  /** チェック区分 */
  private String checkDiv;

  /** データステータス */
  private String dataStatus;

  /** BL登録区分 */
  private String blLoginDiv;
  
  /** 商品中分類コード */
  private String goodsMGroup;
  
  /** 優良設定詳細コード１ */
  private String prmSetDtlNo1;
  
  /** 優良設定詳細コード2 */
  private String prmSetDtlNo2;

  /** 削除依頼区分 */
  private String deleteFlg;
  
  /** インポート区分 */
  private String importKbn;
  
  /** 履歴標識 */
  private String joinFlg;

  
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
   * 【prmSetDtlCd1】を取得する。
   * </pre>
   *
   * @return 【prmSetDtlCd1】
   */
  public String getPrmSetDtlCd1() {
    return prmSetDtlCd1;
  }

  /**
   * <pre>
   * 【prmSetDtlCd1】を設定する。
   * </pre>
   *
   * @param prmSetDtlCd1 【prmSetDtlCd1】
   */
  public void setPrmSetDtlCd1(String prmSetDtlCd1) {
    this.prmSetDtlCd1 = prmSetDtlCd1;
  }

  /**
   * <pre>
   * 【prtsMakerCd】を取得する。
   * </pre>
   *
   * @return 【prtsMakerCd】
   */
  public int getPrtsMakerCd() {
    return prtsMakerCd;
  }

  /**
   * <pre>
   * 【prtsMakerCd】を設定する。
   * </pre>
   *
   * @param prtsMakerCd 【prtsMakerCd】
   */
  public void setPrtsMakerCd(int prtsMakerCd) {
    this.prtsMakerCd = prtsMakerCd;
  }

  /**
   * <pre>
   * 【itemMidclassifyCd】を取得する。
   * </pre>
   *
   * @return 【itemMidclassifyCd】
   */
  public String getItemMidclassifyCd() {
    return itemMidclassifyCd;
  }

  /**
   * <pre>
   * 【itemMidclassifyCd】を設定する。
   * </pre>
   *
   * @param itemMidclassifyCd 【itemMidclassifyCd】
   */
  public void setItemMidclassifyCd(String itemMidclassifyCd) {
    this.itemMidclassifyCd = itemMidclassifyCd;
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
   * @param joinSourceMakerCode 【joinSourceMakerCode】
   */
  public void setJoinSourceMakerCode(String joinSourceMakerCode) {
    this.joinSourceMakerCode = joinSourceMakerCode;
  }

  /**
   * <pre>
   * 【prmSetDtlCd2】を取得する。
   * </pre>
   *
   * @return 【prmSetDtlCd2】
   */
  public String getPrmSetDtlCd2() {
    return prmSetDtlCd2;
  }

  /**
   * <pre>
   * 【prmSetDtlCd2】を設定する。
   * </pre>
   *
   * @param prmSetDtlCd2 【prmSetDtlCd2】
   */
  public void setPrmSetDtlCd2(String prmSetDtlCd2) {
    this.prmSetDtlCd2 = prmSetDtlCd2;
  }

  /**
   * <pre>
   * 【joinSourcePartsno】を取得する。
   * </pre>
   *
   * @return 【joinSourcePartsno】
   */
  public String getJoinSourcePartsno() {
    return joinSourcePartsno;
  }

  /**
   * <pre>
   * 【joinSourcePartsno】を設定する。
   * </pre>
   *
   * @param joinSourcePartsno 【joinSourcePartsno】
   */
  public void setJoinSourcePartsno(String joinSourcePartsno) {
    this.joinSourcePartsno = joinSourcePartsno;
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
   * 【joinDestPartsno】を取得する。
   * </pre>
   *
   * @return 【joinDestPartsno】
   */
  public String getJoinDestPartsno() {
    return joinDestPartsno;
  }

  /**
   * <pre>
   * 【joinDestPartsno】を設定する。
   * </pre>
   *
   * @param joinDestPartsno 【joinDestPartsno】
   */
  public void setJoinDestPartsno(String joinDestPartsno) {
    this.joinDestPartsno = joinDestPartsno;
  }

  /**
   * <pre>
   * 【joinQty】を取得する。
   * </pre>
   *
   * @return 【joinQty】
   */
  public double getJoinQty() {
    return joinQty;
  }

  /**
   * <pre>
   * 【joinQty】を設定する。
   * </pre>
   *
   * @param joinQty 【joinQty】
   */
  public void setJoinQty(double joinQty) {
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
   * 【prmPrtsSpecialNote】を取得する。
   * </pre>
   *
   * @return 【prmPrtsSpecialNote】
   */
  public String getPrmPrtsSpecialNote() {
    return prmPrtsSpecialNote;
  }

  /**
   * <pre>
   * 【prmPrtsSpecialNote】を設定する。
   * </pre>
   *
   * @param prmPrtsSpecialNote 【prmPrtsSpecialNote】
   */
  public void setPrmPrtsSpecialNote(String prmPrtsSpecialNote) {
    this.prmPrtsSpecialNote = prmPrtsSpecialNote;
  }

  /**
   * <pre>
   * 【applyTime】を取得する。
   * </pre>
   *
   * @return 【applyTime】
   */
  public String getApplyTime() {
    return applyTime;
  }

  /**
   * <pre>
   * 【applyTime】を設定する。
   * </pre>
   *
   * @param applyTime 【applyTime】
   */
  public void setApplyTime(String applyTime) {
    this.applyTime = applyTime;
  }

  /**
   * <pre>
   * 【delReason】を取得する。
   * </pre>
   *
   * @return 【delReason】
   */
  public String getDelReason() {
    return delReason;
  }

  /**
   * <pre>
   * 【delReason】を設定する。
   * </pre>
   *
   * @param delReason 【delReason】
   */
  public void setDelReason(String delReason) {
    this.delReason = delReason;
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
   * 【checkDiv】を取得する。
   * </pre>
   *
   * @return 【checkDiv】
   */
  public String getCheckDiv() {
    return checkDiv;
  }

  /**
   * <pre>
   * 【checkDiv】を設定する。
   * </pre>
   *
   * @param checkDiv 【checkDiv】
   */
  public void setCheckDiv(String checkDiv) {
    this.checkDiv = checkDiv;
  }

  /**
   * <pre>
   * 【dataStatus】を取得する。
   * </pre>
   *
   * @return 【dataStatus】
   */
  public String getDataStatus() {
    return dataStatus;
  }

  /**
   * <pre>
   * 【dataStatus】を設定する。
   * </pre>
   *
   * @param dataStatus 【dataStatus】
   */
  public void setDataStatus(String dataStatus) {
    this.dataStatus = dataStatus;
  }

  /**
   * <pre>
   * 【blLoginDiv】を取得する。
   * </pre>
   *
   * @return 【blLoginDiv】
   */
  public String getBlLoginDiv() {
    return blLoginDiv;
  }

  /**
   * <pre>
   * 【blLoginDiv】を設定する。
   * </pre>
   *
   * @param blLoginDiv 【blLoginDiv】
   */
  public void setBlLoginDiv(String blLoginDiv) {
    this.blLoginDiv = blLoginDiv;
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
   * @param prmSetDtlNo2 【prmSetDtlNo2】
   */
  public void setPrmSetDtlNo2(String prmSetDtlNo2) {
    this.prmSetDtlNo2 = prmSetDtlNo2;
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
   * 【joinFlg】を取得する。
   * </pre>
   *
   * @return 【joinFlg】
   */
  public String getJoinFlg() {
    return joinFlg;
  }

  /**
   * <pre>
   * 【joinFlg】を設定する。
   * </pre>
   *
   * @param joinFlg 【joinFlg】
   */
  public void setJoinFlg(String joinFlg) {
    this.joinFlg = joinFlg;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(uuid).append(insDtTime).append(updDtTime).append(insAccountId)
        .append(updAccountId).append(logicalDelDiv).append(prmSetDtlCd1).append(prtsMakerCd).append(itemMidclassifyCd)
        .append(tbsPartsCode).append(joinSourceMakerCode).append(prmSetDtlCd2).append(joinSourcePartsno).append(joinDispOrder)
        .append(joinDestPartsno).append(joinQty).append(joinSpecialNote).append(prmPrtsSpecialNote).append(applyTime)
        .append(checkDiv).append(dataStatus).append(blLoginDiv).toHashCode();
  }

  /**
   * equalsメソッド
   * 
   * @param obj オブジェクト
   * @return 比較結果
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    JoinMasterDto other = (JoinMasterDto) obj;
    return new EqualsBuilder().append(uuid, other.uuid).append(insDtTime, other.insDtTime)
        .append(updDtTime, other.updDtTime).append(insAccountId, other.insAccountId)
        .append(updAccountId, other.updAccountId).append(logicalDelDiv, other.logicalDelDiv)
        .append(prmSetDtlCd1, other.prmSetDtlCd1).append(prtsMakerCd, other.prtsMakerCd)
        .append(itemMidclassifyCd, other.itemMidclassifyCd).append(tbsPartsCode, other.tbsPartsCode)
        .append(joinSourceMakerCode, other.joinSourceMakerCode).append(prmSetDtlCd2, other.prmSetDtlCd2)
        .append(joinSourcePartsno, other.joinSourcePartsno).append(joinDispOrder, other.joinDispOrder)
        .append(joinDestPartsno, other.joinDestPartsno).append(joinQty, other.joinQty).append(joinSpecialNote, other.joinSpecialNote)
        .append(prmPrtsSpecialNote, other.prmPrtsSpecialNote).append(applyTime, other.applyTime)
        .append(checkDiv, other.checkDiv).append(dataStatus, other.dataStatus).append(blLoginDiv, other.blLoginDiv)
        .isEquals();
  }
}
