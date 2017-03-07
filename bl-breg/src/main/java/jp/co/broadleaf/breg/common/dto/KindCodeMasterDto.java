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
 * 種別コードマストDto
 * </pre>
 */
public class KindCodeMasterDto {

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
   * 商品メーカーコード
   */
  private int goodsMaker;
  
  /**
   * 種別コード
   */
  private int kindCd;
  
  /**
   * 種別名称
   */
  private String kindName;
  
  /**
   * 提供日付
   */
  private int offerDate;

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
   * 【goodsMaker】を取得する。
   * </pre>
   *
   * @return 【goodsMaker】
   */
  public int getGoodsMaker() {
    return goodsMaker;
  }

  /**
   * <pre>
   * 【goodsMaker】を設定する。
   * </pre>
   *
   * @param goodsMaker 【goodsMaker】
   */
  public void setGoodsMaker(int goodsMaker) {
    this.goodsMaker = goodsMaker;
  }

  /**
   * <pre>
   * 【kindCd】を取得する。
   * </pre>
   *
   * @return 【kindCd】
   */
  public int getKindCd() {
    return kindCd;
  }

  /**
   * <pre>
   * 【kindCd】を設定する。
   * </pre>
   *
   * @param kindCd 【kindCd】
   */
  public void setKindCd(int kindCd) {
    this.kindCd = kindCd;
  }

  /**
   * <pre>
   * 【kindName】を取得する。
   * </pre>
   *
   * @return 【kindName】
   */
  public String getKindName() {
    return kindName;
  }

  /**
   * <pre>
   * 【kindName】を設定する。
   * </pre>
   *
   * @param kindName 【kindName】
   */
  public void setKindName(String kindName) {
    this.kindName = kindName;
  }

  /**
   * <pre>
   * 【offerDate】を取得する。
   * </pre>
   *
   * @return 【offerDate】
   */
  public int getOfferDate() {
    return offerDate;
  }

  /**
   * <pre>
   * 【offerDate】を設定する。
   * </pre>
   *
   * @param offerDate 【offerDate】
   */
  public void setOfferDate(int offerDate) {
    this.offerDate = offerDate;
  } 
  
  
}
