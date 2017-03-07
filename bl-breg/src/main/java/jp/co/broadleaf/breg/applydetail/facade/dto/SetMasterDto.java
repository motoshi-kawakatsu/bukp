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
 * セットマスタ(メーカー)のDtoのクラス。
 */
public class SetMasterDto {

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

  /** 論理削除区分 */
  private String logicalDelDiv;

  /** 優良設定詳細コード１ */
  private String prmSetDtlNo1;

  /** 部品メーカーコード */
  private int partsMakerCd;

  /** 商品中分類コード */
  private String goodsMGroupCd;

  /** BLコード */
  private String blCode;

  /** セット親品番 */
  private String setMainPartsNo;

  /** セット表示順位 */
  private int setDispOrder;

  /** セット子品番 */
  private String setSubPartsNo;

  /** 品名(半角) */
  private String productName;

  /** 品名(全角) */
  private String setName;

  /** セットQTY */
  private double setQty;

  /** セット規格・特記事項 */
  private String setSpecialNote;

  /** 優良部品規格・特記事項(C向け) */
  private String prmPartsSpecialNote;

  /** 適用日時 */
  private String applyTime;

  /** チェック区分 */
  private String checkDiv;

  /** データステータス */
  private String dataStatus;

  /** BL登録区分 */
  private String blLoginDiv;

  /** 申請状態 */
  private String applyCondition;

  /** 処理区分 */
  private String manageKbn;

  /** 削除削除依頼区分 */
  private String deleteFlg;

  /** 削除理由 */
  private String deleteReason;

  /** インポート区分 */
  private String importKbn;

  /** 履歴標識 */
  private String setFlg;

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
   * 【goodsMGroupCd】を取得する。
   * </pre>
   *
   * @return 【goodsMGroupCd】
   */
  public String getGoodsMGroupCd() {
    return goodsMGroupCd;
  }

  /**
   * <pre>
   * 【goodsMGroupCd】を設定する。
   * </pre>
   *
   * @param goodsMGroupCd 【goodsMGroupCd】
   */
  public void setGoodsMGroupCd(String goodsMGroupCd) {
    this.goodsMGroupCd = goodsMGroupCd;
  }

  /**
   * <pre>
   * 【blCode】を取得する。
   * </pre>
   *
   * @return 【blCode】
   */
  public String getBlCode() {
    return blCode;
  }

  /**
   * <pre>
   * 【blCode】を設定する。
   * </pre>
   *
   * @param blCode 【blCode】
   */
  public void setBlCode(String blCode) {
    this.blCode = blCode;
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
   * 【productName】を取得する。
   * </pre>
   *
   * @return 【productName】
   */
  public String getProductName() {
    return productName;
  }

  /**
   * <pre>
   * 【productName】を設定する。
   * </pre>
   *
   * @param productName 【productName】
   */
  public void setProductName(String productName) {
    this.productName = productName;
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
  public double getSetQty() {
    return setQty;
  }

  /**
   * <pre>
   * 【setQty】を設定する。
   * </pre>
   *
   * @param setQty 【setQty】
   */
  public void setSetQty(double setQty) {
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
   * 【prmPartsSpecialNote】を取得する。
   * </pre>
   *
   * @return 【prmPartsSpecialNote】
   */
  public String getPrmPartsSpecialNote() {
    return prmPartsSpecialNote;
  }

  /**
   * <pre>
   * 【prmPartsSpecialNote】を設定する。
   * </pre>
   *
   * @param prmPartsSpecialNote 【prmPartsSpecialNote】
   */
  public void setPrmPartsSpecialNote(String prmPartsSpecialNote) {
    this.prmPartsSpecialNote = prmPartsSpecialNote;
  }

  /**
   * <pre>
   * 【applyDate】を取得する。
   * </pre>
   *
   * @return 【applyDate】
   */
  public String getApplyTime() {
    return applyTime;
  }

  /**
   * <pre>
   * 【applyDate】を設定する。
   * </pre>
   *
   * @param applyTime 【applyTime】
   */
  public void setApplyTime(String applyTime) {
    this.applyTime = applyTime;
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
   * 【setFlg】を取得する。
   * </pre>
   *
   * @return 【setFlg】
   */
  public String getSetFlg() {
    return setFlg;
  }

  /**
   * <pre>
   * 【setFlg】を設定する。
   * </pre>
   *
   * @param setFlg 【setFlg】
   */
  public void setSetFlg(String setFlg) {
    this.setFlg = setFlg;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(uuid).append(insDtTime).append(updDtTime).append(insAccountId)
        .append(updAccountId).append(logicalDelDiv).append(prmSetDtlNo1).append(partsMakerCd).append(goodsMGroupCd)
        .append(blCode).append(setMainPartsNo).append(setDispOrder).append(setSubPartsNo).append(productName)
        .append(setName).append(setQty).append(setSpecialNote).append(prmPartsSpecialNote).append(applyTime)
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
    SetMasterDto other = (SetMasterDto) obj;
    return new EqualsBuilder().append(uuid, other.uuid).append(insDtTime, other.insDtTime)
        .append(updDtTime, other.updDtTime).append(insAccountId, other.insAccountId)
        .append(updAccountId, other.updAccountId).append(logicalDelDiv, other.logicalDelDiv)
        .append(prmSetDtlNo1, other.prmSetDtlNo1).append(partsMakerCd, other.partsMakerCd)
        .append(goodsMGroupCd, other.goodsMGroupCd).append(blCode, other.blCode)
        .append(setMainPartsNo, other.setMainPartsNo).append(setDispOrder, other.setDispOrder)
        .append(setSubPartsNo, other.setSubPartsNo).append(productName, other.productName)
        .append(setName, other.setName).append(setQty, other.setQty).append(setSpecialNote, other.setSpecialNote)
        .append(prmPartsSpecialNote, other.prmPartsSpecialNote).append(applyTime, other.applyTime)
        .append(checkDiv, other.checkDiv).append(dataStatus, other.dataStatus).append(blLoginDiv, other.blLoginDiv)
        .isEquals();
  }
}
