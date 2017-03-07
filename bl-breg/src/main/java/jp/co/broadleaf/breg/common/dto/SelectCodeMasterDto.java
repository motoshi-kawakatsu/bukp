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
 * セレクトコードDto
 * </pre>
 */
public class SelectCodeMasterDto {

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
  private int goodsMakerCd;
  
  /**
   * 優良設定詳細コード１
   */
  private int prmSetDtlNo1;
  
  /**
   * 優良設定詳細名称
   */
  private String prmSetDtlName;

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
   * 【goodsMakerCd】を取得する。
   * </pre>
   *
   * @return 【goodsMakerCd】
   */
  public int getGoodsMakerCd() {
    return goodsMakerCd;
  }

  /**
   * <pre>
   * 【goodsMakerCd】を設定する。
   * </pre>
   *
   * @param goodsMakerCd 【goodsMakerCd】
   */
  public void setGoodsMakerCd(int goodsMakerCd) {
    this.goodsMakerCd = goodsMakerCd;
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
   * 【prmSetDtlName】を取得する。
   * </pre>
   *
   * @return 【prmSetDtlName】
   */
  public String getPrmSetDtlName() {
    return prmSetDtlName;
  }

  /**
   * <pre>
   * 【prmSetDtlName】を設定する。
   * </pre>
   *
   * @param prmSetDtlName 【prmSetDtlName】
   */
  public void setPrmSetDtlName(String prmSetDtlName) {
    this.prmSetDtlName = prmSetDtlName;
  }
  
  
}
