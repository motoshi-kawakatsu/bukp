//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 湯凱
// 作 成 日       2017/02/06   修正内容 : 会社情報：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.companysetting.form;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.framework.validation.FormValidator;
import jp.co.broadleaf.framework.validation.FormValidatorFactory;

/**
 * <pre>
 * 会社情報Formのクラス
 * </pre>
 */
public class CompanyInfoForm {
  
  /**
   * メーカーコード
   */
  private int makerCd;

  /**
   * メーカーコード名称
   */
  private String makerCdName;
  
  /**
   * メーカーコード名称(半角)
   */
  private String makerCdKana;
  
  /**
   * 会社名称
   */
  private String companyName;
  
  /**
   * 会社名称(カナ)
   */
  private String companyKana;
  
  /**
   * 郵便番号
   */
  private String postNo;
  
  /**
   * 住所
   */
  private String address;
  
  /**
   * TEL
   */
  private String tel;
  
  /**
   * FAX
   */
  private String fax;
  
  /**
   * 備考
   */
  private String remark;
  
  /**
   * 商品ページ内行数設定
   */
  private String goodsRows;
  
  /**
   * セットページ内行数設定
   */
  private String setRows;
  
  /**
   * 結合ページ内行数設定
   */
  private String joinRows;
  
  /**
   * 申請履歴ページ内行数設定
   */
  private String applyResumeRows;
  
  /**
   * 申請詳細ページ内行数設定
   */
  private String applyDetailRows;
  
  /**
   * 商品インポート方法
   */
  private String goodsImportType;
  
  /**
   * セットインポート方法
   */
  private String setImportType;
  
  /**
   * 結合インポート方法
   */
  private String joinImportType;
  
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
   * 【makerCdName】を取得する。
   * </pre>
   *
   * @return 【makerCdName】
   */
  public String getMakerCdName() {
    return makerCdName;
  }

  /**
   * <pre>
   * 【makerCdName】を設定する。
   * </pre>
   *
   * @param makerCdName 【makerCdName】
   */
  public void setMakerCdName(String makerCdName) {
    this.makerCdName = makerCdName;
  }

  /**
   * <pre>
   * 【makerCdKana】を取得する。
   * </pre>
   *
   * @return 【makerCdKana】
   */
  public String getMakerCdKana() {
    return makerCdKana;
  }

  /**
   * <pre>
   * 【makerCdKana】を設定する。
   * </pre>
   *
   * @param makerCdKana 【makerCdKana】
   */
  public void setMakerCdKana(String makerCdKana) {
    this.makerCdKana = makerCdKana;
  }

  /**
   * <pre>
   * 【companyName】を取得する。
   * </pre>
   *
   * @return 【companyName】
   */
  public String getCompanyName() {
    return companyName;
  }

  /**
   * <pre>
   * 【companyName】を設定する。
   * </pre>
   *
   * @param companyName 【companyName】
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  /**
   * <pre>
   * 【companyKana】を取得する。
   * </pre>
   *
   * @return 【companyKana】
   */
  public String getCompanyKana() {
    return companyKana;
  }

  /**
   * <pre>
   * 【companyKana】を設定する。
   * </pre>
   *
   * @param companyKana 【companyKana】
   */
  public void setCompanyKana(String companyKana) {
    this.companyKana = companyKana;
  }

  /**
   * <pre>
   * 【postNo】を取得する。
   * </pre>
   *
   * @return 【postNo】
   */
  public String getPostNo() {
    return postNo;
  }

  /**
   * <pre>
   * 【postNo】を設定する。
   * </pre>
   *
   * @param postNo 【postNo】
   */
  public void setPostNo(String postNo) {
    this.postNo = postNo;
  }

  /**
   * <pre>
   * 【address】を取得する。
   * </pre>
   *
   * @return 【address】
   */
  public String getAddress() {
    return address;
  }

  /**
   * <pre>
   * 【address】を設定する。
   * </pre>
   *
   * @param address 【address】
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * <pre>
   * 【tel】を取得する。
   * </pre>
   *
   * @return 【tel】
   */
  public String getTel() {
    return tel;
  }

  /**
   * <pre>
   * 【tel】を設定する。
   * </pre>
   *
   * @param tel 【tel】
   */
  public void setTel(String tel) {
    this.tel = tel;
  }

  /**
   * <pre>
   * 【fax】を取得する。
   * </pre>
   *
   * @return 【fax】
   */
  public String getFax() {
    return fax;
  }

  /**
   * <pre>
   * 【fax】を設定する。
   * </pre>
   *
   * @param fax 【fax】
   */
  public void setFax(String fax) {
    this.fax = fax;
  }

  /**
   * <pre>
   * 【notes】を取得する。
   * </pre>
   *
   * @return 【notes】
   */
  public String getNotes() {
    return remark;
  }

  /**
   * <pre>
   * 【notes】を設定する。
   * </pre>
   *
   * @param notes 【notes】
   */
  public void setNotes(String notes) {
    this.remark = notes;
  }

  /**
   * <pre>
   * 【remark】を取得する。
   * </pre>
   *
   * @return 【remark】
   */
  public String getRemark() {
    return remark;
  }

  /**
   * <pre>
   * 【remark】を設定する。
   * </pre>
   *
   * @param remark 【remark】
   */
  public void setRemark(String remark) {
    this.remark = remark;
  }

  /**
   * <pre>
   * 【goodsRows】を取得する。
   * </pre>
   *
   * @return 【goodsRows】
   */
  public String getGoodsRows() {
    return goodsRows;
  }

  /**
   * <pre>
   * 【goodsRows】を設定する。
   * </pre>
   *
   * @param goodsRows 【goodsRows】
   */
  public void setGoodsRows(String goodsRows) {
    this.goodsRows = goodsRows;
  }

  /**
   * <pre>
   * 【setRows】を取得する。
   * </pre>
   *
   * @return 【setRows】
   */
  public String getSetRows() {
    return setRows;
  }

  /**
   * <pre>
   * 【setRows】を設定する。
   * </pre>
   *
   * @param setRows 【setRows】
   */
  public void setSetRows(String setRows) {
    this.setRows = setRows;
  }

  /**
   * <pre>
   * 【joinRows】を取得する。
   * </pre>
   *
   * @return 【joinRows】
   */
  public String getJoinRows() {
    return joinRows;
  }

  /**
   * <pre>
   * 【joinRows】を設定する。
   * </pre>
   *
   * @param joinRows 【joinRows】
   */
  public void setJoinRows(String joinRows) {
    this.joinRows = joinRows;
  }

  /**
   * <pre>
   * 【applyResumeRows】を取得する。
   * </pre>
   *
   * @return 【applyResumeRows】
   */
  public String getApplyResumeRows() {
    return applyResumeRows;
  }

  /**
   * <pre>
   * 【applyResumeRows】を設定する。
   * </pre>
   *
   * @param applyResumeRows 【applyResumeRows】
   */
  public void setApplyResumeRows(String applyResumeRows) {
    this.applyResumeRows = applyResumeRows;
  }

  /**
   * <pre>
   * 【applyDetailRows】を取得する。
   * </pre>
   *
   * @return 【applyDetailRows】
   */
  public String getApplyDetailRows() {
    return applyDetailRows;
  }

  /**
   * <pre>
   * 【applyDetailRows】を設定する。
   * </pre>
   *
   * @param applyDetailRows 【applyDetailRows】
   */
  public void setApplyDetailRows(String applyDetailRows) {
    this.applyDetailRows = applyDetailRows;
  }

  /**
   * <pre>
   * 【goodsImportType】を取得する。
   * </pre>
   *
   * @return 【goodsImportType】
   */
  public String getGoodsImportType() {
    return goodsImportType;
  }

  /**
   * <pre>
   * 【goodsImportType】を設定する。
   * </pre>
   *
   * @param goodsImportType 【goodsImportType】
   */
  public void setGoodsImportType(String goodsImportType) {
    this.goodsImportType = goodsImportType;
  }

  /**
   * <pre>
   * 【setImportType】を取得する。
   * </pre>
   *
   * @return 【setImportType】
   */
  public String getSetImportType() {
    return setImportType;
  }

  /**
   * <pre>
   * 【setImportType】を設定する。
   * </pre>
   *
   * @param setImportType 【setImportType】
   */
  public void setSetImportType(String setImportType) {
    this.setImportType = setImportType;
  }

  /**
   * <pre>
   * 【joinImportType】を取得する。
   * </pre>
   *
   * @return 【joinImportType】
   */
  public String getJoinImportType() {
    return joinImportType;
  }

  /**
   * <pre>
   * 【joinImportType】を設定する。
   * </pre>
   *
   * @param joinImportType 【joinImportType】
   */
  public void setJoinImportType(String joinImportType) {
    this.joinImportType = joinImportType;
  }
  
  /**
   * 画面入力チェック
   */
  public void validate(){
    // 画面入力チェック
    FormValidator validator = FormValidatorFactory.create();
    validator.field("makerName", makerCdName).required(BregMessageCodes.E00001, "メーカーコード名称")
      .maxLength(30, BregMessageCodes.E00003, "メーカーコード名称", "30");
    validator.field("makerKana", makerCdKana).required(BregMessageCodes.E00001, "メーカーコード名称(半角)")
      .halfwidthKatakanas(BregMessageCodes.E00004, "メーカーコード名称(半角)")
      .maxLength(30, BregMessageCodes.E00003, "メーカーコード名称(半角)", "30");
    validator.field("companyName", companyName).required(BregMessageCodes.E00001, "会社名称")
      .maxLength(30, BregMessageCodes.E00003, "会社名称", "30");
    validator.field("companyKana", companyKana).required(BregMessageCodes.E00001, "会社名称(カナ)")
      .halfwidthKatakanas(BregMessageCodes.E00004, "会社名称(カナ)")
      .maxLength(30, BregMessageCodes.E00003, "会社名称(カナ)", "30");
    validator.field("postCode", postNo).required(BregMessageCodes.E00001, "郵便番号")
      .maxLength(16, BregMessageCodes.E00003, "郵便番号", "16");
    validator.field("address", address).required(BregMessageCodes.E00001, "住所")
      .maxLength(60, BregMessageCodes.E00003, "住所", "60");
    validator.field("telNo", tel).required(BregMessageCodes.E00001, "TEL")
      .maxLength(16, BregMessageCodes.E00003, "TEL", "16");
    validator.field("faxNo", fax).required(BregMessageCodes.E00001, "FAX")
      .maxLength(16, BregMessageCodes.E00003, "FAX", "16");
    validator.field("note", remark).maxLength(1024, BregMessageCodes.E00003, "備考", "1024");
    validator.field("goodsRows", goodsRows).maxLength(4, BregMessageCodes.E00003, "商品ページ内行数設定", "4")
      .halfwidthDigits(BregMessageCodes.E00005, "商品ページ内行数設定");
    validator.field("setRows", setRows).maxLength(4, BregMessageCodes.E00003, "セットページ内行数設定", "4")
      .halfwidthDigits(BregMessageCodes.E00005, "セットページ内行数設定");
    validator.field("joinRows", joinRows).maxLength(4, BregMessageCodes.E00003, "結合ページ内行数設定", "4")
      .halfwidthDigits(BregMessageCodes.E00005, "結合ページ内行数設定");
    validator.field("applyHistoryRows", applyResumeRows).maxLength(4, BregMessageCodes.E00003, "申請履歴ページ内行数設定", "4")
      .halfwidthDigits(BregMessageCodes.E00005, "申請履歴ページ内行数設定");
    validator.field("applyDetailRows", applyDetailRows).maxLength(4, BregMessageCodes.E00003, "申請詳細ページ内行数設定", "4")
      .halfwidthDigits(BregMessageCodes.E00005, "申請詳細ページ内行数設定");
    validator.validate();
  }
  
}
