//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : xuck
// 作 成 日       2017/02/13   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applyheadmastercommon.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <pre>
 * </pre>
 */
public class ApplyHeadMasterCommonDto implements Serializable {

  /**  */
  private static final long serialVersionUID = -1732657261820973369L;

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
  private String insAccountId;

  /**
   * 更新アカウントID
   */
  private String updAccountId;

  /**
   * 論理削除区分
   */
  private Integer logicalDeleteCode;
  /**
   * 部品メーカーコード
   */
  private int partsMakerCd;
  /**
   * 申請No
   */
  private int applyNo;
  /**
   * 申請時コメント
   */
  private String applyComments;
  /**
   * 申請日時
   */
  private Timestamp applyDtTime;
  /**
   * BL申請結果区分
   */
  private short blApplyResultsFlg;
  /**
   * BL申請結果コメント
   */
  private String blApplyResultsComments;
  /**
   * BL申請判断日
   */
  private Integer blApplyJudgmentDate;
  /**
   * BL申請判断時間
   */
  private Integer blApplyJudgmentTime;
  /**
   * 申請種類
   */
  private Integer applyType;

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
   * 【logicalDeleteCode】を取得する。
   * </pre>
   *
   * @return 【logicalDeleteCode】
   */
  public Integer getLogicalDeleteCode() {
    return logicalDeleteCode;
  }

  /**
   * <pre>
   * 【logicalDeleteCode】を設定する。
   * </pre>
   *
   * @param logicalDeleteCode 【logicalDeleteCode】
   */
  public void setLogicalDeleteCode(Integer logicalDeleteCode) {
    this.logicalDeleteCode = logicalDeleteCode;
  }

  /**
   * 部品メーカーコードを返します。
   * 
   * @return 部品メーカーコード
   */
  public int getPartsMakerCd() {
    return partsMakerCd;
  }

  /**
   * 部品メーカーコードを設定します。
   * 
   * @param partsMakerCd セットする部品メーカーコード
   */
  public void setPartsMakerCd(int partsMakerCd) {
    this.partsMakerCd = partsMakerCd;
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
   * 【applyComments】を取得する。
   * </pre>
   *
   * @return 【applyComments】
   */
  public String getApplyComments() {
    return applyComments;
  }

  /**
   * <pre>
   * 【applyComments】を設定する。
   * </pre>
   *
   * @param applyComments 【applyComments】
   */
  public void setApplyComments(String applyComments) {
    this.applyComments = applyComments;
  }

  /**
   * 申請日時を返します。
   * 
   * @return 申請日時
   */
  public Timestamp getApplyDtTime() {
    return applyDtTime;
  }

  /**
   * 申請日時を設定します。
   * 
   * @param applyDtTime セットする申請日時
   */
  public void setApplyDtTime(Timestamp applyDtTime) {
    this.applyDtTime = applyDtTime;
  }

  /**
   * <pre>
   * 【blApplyResultsFlg】を取得する。
   * </pre>
   *
   * @return 【blApplyResultsFlg】
   */
  public short getBlApplyResultsFlg() {
    return blApplyResultsFlg;
  }

  /**
   * <pre>
   * 【blApplyResultsFlg】を設定する。
   * </pre>
   *
   * @param blApplyResultsFlg 【blApplyResultsFlg】
   */
  public void setBlApplyResultsFlg(short blApplyResultsFlg) {
    this.blApplyResultsFlg = blApplyResultsFlg;
  }

  /**
   * <pre>
   * 【blApplyResultsComments】を取得する。
   * </pre>
   *
   * @return 【blApplyResultsComments】
   */
  public String getBlApplyResultsComments() {
    return blApplyResultsComments;
  }

  /**
   * <pre>
   * 【blApplyResultsComments】を設定する。
   * </pre>
   *
   * @param blApplyResultsComments 【blApplyResultsComments】
   */
  public void setBlApplyResultsComments(String blApplyResultsComments) {
    this.blApplyResultsComments = blApplyResultsComments;
  }

  /**
   * <pre>
   * 【blApplyJudgmentDate】を取得する。
   * </pre>
   *
   * @return 【blApplyJudgmentDate】
   */
  public Integer getBlApplyJudgmentDate() {
    return blApplyJudgmentDate;
  }

  /**
   * <pre>
   * 【blApplyJudgmentDate】を設定する。
   * </pre>
   *
   * @param blApplyJudgmentDate 【blApplyJudgmentDate】
   */
  public void setBlApplyJudgmentDate(Integer blApplyJudgmentDate) {
    this.blApplyJudgmentDate = blApplyJudgmentDate;
  }

  /**
   * <pre>
   * 【blApplyJudgmentTime】を取得する。
   * </pre>
   *
   * @return 【blApplyJudgmentTime】
   */
  public Integer getBlApplyJudgmentTime() {
    return blApplyJudgmentTime;
  }

  /**
   * <pre>
   * 【blApplyJudgmentTime】を設定する。
   * </pre>
   *
   * @param blApplyJudgmentTime 【blApplyJudgmentTime】
   */
  public void setBlApplyJudgmentTime(Integer blApplyJudgmentTime) {
    this.blApplyJudgmentTime = blApplyJudgmentTime;
  }

  /**
   * <pre>
   * 【applyType】を取得する。
   * </pre>
   *
   * @return 【applyType】
   */
  public Integer getApplyType() {
    return applyType;
  }

  /**
   * <pre>
   * 【applyType】を設定する。
   * </pre>
   *
   * @param applyType 【applyType】
   */
  public void setApplyType(Integer applyType) {
    this.applyType = applyType;
  }

  /**
   * <pre>
   * 【applyType】を取得する。
   * </pre>
   *
   * @return 【applyType】
   */
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(uuid).append(insDtTime).append(updDtTime).append(insAccountId)
        .append(updAccountId).append(logicalDeleteCode).append(applyNo).append(applyComments).append(blApplyResultsFlg)
        .append(blApplyResultsComments).append(blApplyJudgmentDate).append(blApplyJudgmentTime).append(applyType)
        .toHashCode();
  }

  /**
   * <pre>
   * 【applyType】を取得する。
   * </pre>
   * 
   * @param obj obj
   * @return 【applyType】
   */
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ApplyHeadMasterCommonDto other = (ApplyHeadMasterCommonDto) obj;
    return new EqualsBuilder().append(uuid, other.uuid).append(insDtTime, other.insDtTime)
        .append(updDtTime, other.updDtTime).append(insAccountId, other.insAccountId)
        .append(updAccountId, other.updAccountId).append(logicalDeleteCode, other.logicalDeleteCode)
        .append(applyNo, other.applyNo).append(applyComments, other.applyComments)
        .append(blApplyResultsFlg, other.blApplyResultsFlg).append(blApplyResultsComments, other.blApplyResultsComments)
        .append(blApplyJudgmentDate, other.blApplyJudgmentDate).append(blApplyJudgmentTime, other.blApplyJudgmentTime)
        .append(applyType, other.applyType).isEquals();
  }

}
