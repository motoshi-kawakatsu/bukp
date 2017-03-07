//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.dto;

import java.util.Date;

/**
 * <pre>
 * BLコードマスタDto
 * </pre>
 */
public class BlCodeMasterDto {

  /**
   * UUID
   */
  private String uuid;

  /**
   * 作成日時
   */
  private Date insDtTime;

  /**
   * 更新日時
   */
  private Date updDtTime;

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
   * BLグループコード
   */
  private Integer blGroupCode;
  
  /**
   * BLコード
   */
  private int blCode;
  
  /**
   * BLコード枝番
   */
  private Integer blEdaCode;
  
  /**
   * BLコード名称（全角）
   */
  private String blFullName;
  
  /**
   * BLコード名称（半角）
   */
  private String blHalfName;
  
  /**
   * 商品中分類コード
   */
  private Integer goodsRateGrpCode;
  
  /**
   * 提供日付
   */
  private Integer offerDate;
  
  /**
   * 装備分類
   */
  private Integer equipGroup;
  
  /**
   * 優良検索区分
   */
  private Integer primeSearchKbn;

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
   * 【blGroupCode】を取得する。
   * </pre>
   *
   * @return 【blGroupCode】
   */
  public Integer getBlGroupCode() {
    return blGroupCode;
  }

  /**
   * <pre>
   * 【blGroupCode】を設定する。
   * </pre>
   *
   * @param blGroupCode 【blGroupCode】
   */
  public void setBlGroupCode(Integer blGroupCode) {
    this.blGroupCode = blGroupCode;
  }

  /**
   * <pre>
   * 【blCode】を取得する。
   * </pre>
   *
   * @return 【blCode】
   */
  public int getBlCode() {
    return blCode;
  }

  /**
   * <pre>
   * 【blCode】を設定する。
   * </pre>
   *
   * @param blCode 【blCode】
   */
  public void setBlCode(int blCode) {
    this.blCode = blCode;
  }

  /**
   * <pre>
   * 【blEdaCode】を取得する。
   * </pre>
   *
   * @return 【blEdaCode】
   */
  public Integer getBlEdaCode() {
    return blEdaCode;
  }

  /**
   * <pre>
   * 【blEdaCode】を設定する。
   * </pre>
   *
   * @param blEdaCode 【blEdaCode】
   */
  public void setBlEdaCode(Integer blEdaCode) {
    this.blEdaCode = blEdaCode;
  }

  /**
   * <pre>
   * 【blFullName】を取得する。
   * </pre>
   *
   * @return 【blFullName】
   */
  public String getBlFullName() {
    return blFullName;
  }

  /**
   * <pre>
   * 【blFullName】を設定する。
   * </pre>
   *
   * @param blFullName 【blFullName】
   */
  public void setBlFullName(String blFullName) {
    this.blFullName = blFullName;
  }

  /**
   * <pre>
   * 【blHalfName】を取得する。
   * </pre>
   *
   * @return 【blHalfName】
   */
  public String getBlHalfName() {
    return blHalfName;
  }

  /**
   * <pre>
   * 【blHalfName】を設定する。
   * </pre>
   *
   * @param blHalfName 【blHalfName】
   */
  public void setBlHalfName(String blHalfName) {
    this.blHalfName = blHalfName;
  }

  /**
   * <pre>
   * 【goodsRateGrpCode】を取得する。
   * </pre>
   *
   * @return 【goodsRateGrpCode】
   */
  public Integer getGoodsRateGrpCode() {
    return goodsRateGrpCode;
  }

  /**
   * <pre>
   * 【goodsRateGrpCode】を設定する。
   * </pre>
   *
   * @param goodsRateGrpCode 【goodsRateGrpCode】
   */
  public void setGoodsRateGrpCode(Integer goodsRateGrpCode) {
    this.goodsRateGrpCode = goodsRateGrpCode;
  }

  /**
   * <pre>
   * 【offerDate】を取得する。
   * </pre>
   *
   * @return 【offerDate】
   */
  public Integer getOfferDate() {
    return offerDate;
  }

  /**
   * <pre>
   * 【offerDate】を設定する。
   * </pre>
   *
   * @param offerDate 【offerDate】
   */
  public void setOfferDate(Integer offerDate) {
    this.offerDate = offerDate;
  }

  /**
   * <pre>
   * 【equipGroup】を取得する。
   * </pre>
   *
   * @return 【equipGroup】
   */
  public Integer getEquipGroup() {
    return equipGroup;
  }

  /**
   * <pre>
   * 【equipGroup】を設定する。
   * </pre>
   *
   * @param equipGroup 【equipGroup】
   */
  public void setEquipGroup(Integer equipGroup) {
    this.equipGroup = equipGroup;
  }

  /**
   * <pre>
   * 【primeSearchKbn】を取得する。
   * </pre>
   *
   * @return 【primeSearchKbn】
   */
  public Integer getPrimeSearchKbn() {
    return primeSearchKbn;
  }

  /**
   * <pre>
   * 【primeSearchKbn】を設定する。
   * </pre>
   *
   * @param primeSearchKbn 【primeSearchKbn】
   */
  public void setPrimeSearchKbn(Integer primeSearchKbn) {
    this.primeSearchKbn = primeSearchKbn;
  }
  
}
