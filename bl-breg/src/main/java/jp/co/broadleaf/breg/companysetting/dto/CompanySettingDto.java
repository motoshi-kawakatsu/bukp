//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 湯凱
// 作 成 日       2017/02/10   修正内容 : 会社情報：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.companysetting.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <pre>
 * 会社情報のDTOクラス。
 * </pre>
 */
public class CompanySettingDto  implements Serializable{
  
  /**  serialVersionUID*/
  private static final long serialVersionUID = 1L;

  /**
   * UUID
   */
  private String uuid;

  /**
   * 作成日時
   */
  private Timestamp insDtTime;

  /**
   * 更新日時
   */
  private Timestamp updDtTime;

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
  private int logicalDelDiv;
  
  /**
   * メーカーコード
   */
  private int makerCd;

  /**
   * メーカーコード名称
   */
  private String makerNameRF;

  /**
   * メーカーコード名称（半角）
   */
  private String makerKanaRF;

  /**
   * 会社名称
   */
  private String companyNameRF;

  /**
   * 会社名称（カナ）
   */
  private String companyKanaRF;

  /**
   * 郵便番号
   */
  private String postCodeRF;

  /**
   * 住所
   */
  private String addRF;

  /**
   * TEL
   */
  private String telNoRF;

  /**
   * FAX
   */
  private String faxNoRF;

  /**
   * 備考
   */
  private String noteRF;

  /**
   * 商品ページ内行数設定
   */
  private int goodsRows;

  /**
   * セットページ内行数設定
   */
  private int setRows;

  /**
   * 結合ページ内行数設定
   */
  private int joinRows;

  /**
   * 申請履歴ページ内行数設定
   */
  private int applyRecordRows;

  /**
   * 申請詳細ページ内行数設定
   */
  private int applyDetailRows;

  /**
   * 商品インポート方法
   */
  private int goodsImportType;

  /**
   * セットインポート方法
   */
  private int setImportType;

  /**
   * 結合インポート方法
   */
  private int joinImportType;
  
  /**
   * Bl側連絡メールアドレス
   */
  public String blMailAdd;

  /**
   * <pre>
   * 【makerCd】を取得する。
   * </pre>
   *
   * @return 【makerCd】
   */
  public int getMakerCd() {
    return makerCd;
  }

  /**
   * <pre>
   * 【makerCd】を設定する。
   * </pre>
   *
   * @param makerCd 【makerCd】
   */
  public void setMakerCd(int makerCd) {
    this.makerCd = makerCd;
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
   * 【makerCdName】を取得する。
   * </pre>
   *
   * @return 【makerCdName】
   */
  public String getMakerCdName() {
    return makerNameRF;
  }

  /**
   * <pre>
   * 【makerCdName】を設定する。
   * </pre>
   *
   * @param makerCdName 【makerCdName】
   */
  public void setMakerCdName(String makerCdName) {
    this.makerNameRF = makerCdName;
  }

  /**
   * <pre>
   * 【makerCdNameShort】を取得する。
   * </pre>
   *
   * @return 【makerCdNameShort】
   */
  public String getMakerCdNameShort() {
    return makerKanaRF;
  }

  /**
   * <pre>
   * 【makerCdNameShort】を設定する。
   * </pre>
   *
   * @param makerCdNameShort 【makerCdNameShort】
   */
  public void setMakerCdNameShort(String makerCdNameShort) {
    this.makerKanaRF = makerCdNameShort;
  }

  /**
   * <pre>
   * 【companyName】を取得する。
   * </pre>
   *
   * @return 【companyName】
   */
  public String getCompanyName() {
    return companyNameRF;
  }

  /**
   * <pre>
   * 【companyName】を設定する。
   * </pre>
   *
   * @param companyName 【companyName】
   */
  public void setCompanyName(String companyName) {
    this.companyNameRF = companyName;
  }

  /**
   * <pre>
   * 【companyNameKana】を取得する。
   * </pre>
   *
   * @return 【companyNameKana】
   */
  public String getCompanyNameKana() {
    return companyKanaRF;
  }

  /**
   * <pre>
   * 【companyNameKana】を設定する。
   * </pre>
   *
   * @param companyNameKana 【companyNameKana】
   */
  public void setCompanyNameKana(String companyNameKana) {
    this.companyKanaRF = companyNameKana;
  }

  /**
   * <pre>
   * 【mailNo】を取得する。
   * </pre>
   *
   * @return 【mailNo】
   */
  public String getMailNo() {
    return postCodeRF;
  }

  /**
   * <pre>
   * 【mailNo】を設定する。
   * </pre>
   *
   * @param mailNo 【mailNo】
   */
  public void setMailNo(String mailNo) {
    this.postCodeRF = mailNo;
  }

  /**
   * <pre>
   * 【address】を取得する。
   * </pre>
   *
   * @return 【address】
   */
  public String getAddress() {
    return addRF;
  }

  /**
   * <pre>
   * 【address】を設定する。
   * </pre>
   *
   * @param address 【address】
   */
  public void setAddress(String address) {
    this.addRF = address;
  }

  /**
   * <pre>
   * 【tel】を取得する。
   * </pre>
   *
   * @return 【tel】
   */
  public String getTel() {
    return telNoRF;
  }

  /**
   * <pre>
   * 【tel】を設定する。
   * </pre>
   *
   * @param tel 【tel】
   */
  public void setTel(String tel) {
    this.telNoRF = tel;
  }

  /**
   * <pre>
   * 【fax】を取得する。
   * </pre>
   *
   * @return 【fax】
   */
  public String getFax() {
    return faxNoRF;
  }

  /**
   * <pre>
   * 【fax】を設定する。
   * </pre>
   *
   * @param fax 【fax】
   */
  public void setFax(String fax) {
    this.faxNoRF = fax;
  }

  /**
   * <pre>
   * 【notes】を取得する。
   * </pre>
   *
   * @return 【notes】
   */
  public String getNotes() {
    return noteRF;
  }

  /**
   * <pre>
   * 【notes】を設定する。
   * </pre>
   *
   * @param notes 【notes】
   */
  public void setNotes(String notes) {
    this.noteRF = notes;
  }

  /**
   * <pre>
   * 【goodsRows】を取得する。
   * </pre>
   *
   * @return 【goodsRows】
   */
  public int getGoodsRows() {
    return goodsRows;
  }

  /**
   * <pre>
   * 【goodsRows】を設定する。
   * </pre>
   *
   * @param goodsRows 【goodsRows】
   */
  public void setGoodsRows(int goodsRows) {
    this.goodsRows = goodsRows;
  }

  /**
   * <pre>
   * 【setRows】を取得する。
   * </pre>
   *
   * @return 【setRows】
   */
  public int getSetRows() {
    return setRows;
  }

  /**
   * <pre>
   * 【setRows】を設定する。
   * </pre>
   *
   * @param setRows 【setRows】
   */
  public void setSetRows(int setRows) {
    this.setRows = setRows;
  }

  /**
   * <pre>
   * 【joinRows】を取得する。
   * </pre>
   *
   * @return 【joinRows】
   */
  public int getJoinRows() {
    return joinRows;
  }

  /**
   * <pre>
   * 【joinRows】を設定する。
   * </pre>
   *
   * @param joinRows 【joinRows】
   */
  public void setJoinRows(int joinRows) {
    this.joinRows = joinRows;
  }

  /**
   * <pre>
   * 【applyRecordRows】を取得する。
   * </pre>
   *
   * @return 【applyRecordRows】
   */
  public int getApplyRecordRows() {
    return applyRecordRows;
  }

  /**
   * <pre>
   * 【applyRecordRows】を設定する。
   * </pre>
   *
   * @param applyRecordRows 【applyRecordRows】
   */
  public void setApplyRecordRows(int applyRecordRows) {
    this.applyRecordRows = applyRecordRows;
  }

  /**
   * <pre>
   * 【applyDetailRows】を取得する。
   * </pre>
   *
   * @return 【applyDetailRows】
   */
  public int getApplyDetailRows() {
    return applyDetailRows;
  }

  /**
   * <pre>
   * 【applyDetailRows】を設定する。
   * </pre>
   *
   * @param applyDetailRows 【applyDetailRows】
   */
  public void setApplyDetailRows(int applyDetailRows) {
    this.applyDetailRows = applyDetailRows;
  }

  /**
   * <pre>
   * 【goodsImportType】を取得する。
   * </pre>
   *
   * @return 【goodsImportType】
   */
  public int getGoodsImportType() {
    return goodsImportType;
  }

  /**
   * <pre>
   * 【goodsImportType】を設定する。
   * </pre>
   *
   * @param goodsImportType 【goodsImportType】
   */
  public void setGoodsImportType(int goodsImportType) {
    this.goodsImportType = goodsImportType;
  }

  /**
   * <pre>
   * 【setImportType】を取得する。
   * </pre>
   *
   * @return 【setImportType】
   */
  public int getSetImportType() {
    return setImportType;
  }

  /**
   * <pre>
   * 【setImportType】を設定する。
   * </pre>
   *
   * @param setImportType 【setImportType】
   */
  public void setSetImportType(int setImportType) {
    this.setImportType = setImportType;
  }

  /**
   * <pre>
   * 【joinImportType】を取得する。
   * </pre>
   *
   * @return 【joinImportType】
   */
  public int getJoinImportType() {
    return joinImportType;
  }

  /**
   * <pre>
   * 【joinImportType】を設定する。
   * </pre>
   *
   * @param joinImportType 【joinImportType】
   */
  public void setJoinImportType(int joinImportType) {
    this.joinImportType = joinImportType;
  }
  
  /**
   * Bl側連絡メールアドレスを取得する。
   * 
   * @return Bl側連絡メールアドレス
   */
  public String getBlMailAdd() {
    return blMailAdd;
  }

  /**
   * Bl側連絡メールアドレスを設定する。
   * 
   * @param blMailAdd セットするBl側連絡メールアドレス
   */
  public void setBlMailAdd(String blMailAdd) {
    this.blMailAdd = blMailAdd;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(uuid).append(insDtTime).append(updDtTime).append(insAccountId)
        .append(updAccountId).append(logicalDelDiv).append(makerNameRF).append(makerKanaRF)
        .append(companyNameRF).append(companyKanaRF).append(postCodeRF).append(addRF).append(telNoRF).append(faxNoRF)
        .append(noteRF).append(goodsRows).append(setRows).append(joinRows).append(applyRecordRows)
        .append(applyDetailRows).append(goodsImportType).append(setImportType).append(joinImportType).append(blMailAdd).toHashCode();
  }

  /**
   * equalsメソッド
   * 
   * @param obj Object
   * @return 比較結果
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    CompanySettingDto other = (CompanySettingDto) obj;
    return new EqualsBuilder().append(this.makerCd, other.makerCd).append(this.makerNameRF, other.makerNameRF)
        .append(this.makerKanaRF, other.makerKanaRF).append(this.companyNameRF, other.companyNameRF)
        .append(this.companyKanaRF, other.companyKanaRF).append(this.postCodeRF, other.postCodeRF)
        .append(this.addRF, other.addRF).append(this.telNoRF, other.telNoRF).append(this.faxNoRF, other.faxNoRF)
        .append(this.noteRF, other.noteRF).append(this.goodsRows, other.goodsRows).append(this.setRows, other.setRows)
        .append(this.joinRows, other.joinRows).append(this.applyRecordRows, other.applyRecordRows)
        .append(this.applyDetailRows, other.applyDetailRows).append(this.goodsImportType, other.goodsImportType)
        .append(this.setImportType, other.setImportType).append(this.joinImportType, other.joinImportType)
        .append(this.blMailAdd, other.blMailAdd).isEquals();
  }

}
