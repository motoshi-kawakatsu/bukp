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
 * 商品マスタのDtoのクラス。
 */
public class GoodsTotalinfoDto implements Serializable {
  /** 
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * 申請状態
   */
  private String blApplyResultsFlg;

  /**
   * コンストラクタ。
   */
  public GoodsTotalinfoDto() {

  }

  /**
   * 優良設定詳細コード１
   */
  public int prmSetDtlNo1;

  /**
   * 部品メーカーコード
   */
  public String partsMakerCd;

  /**
   * 商品中分類コード
   */
  public Integer goodsMGroup;

  /**
   * BLコード
   */
  public Integer tbsPartsCode;

  /**
   * 優良品番(－付き品番)
   */
  public String primePartsNoWithH;

  /**
   * 優良部品カナ名称
   */
  public String primePartsKanaNm;

  /**
   * 優良部品名称
   */
  public String primePartsName;

  /**
   * 新価格
   */
  public String newPrice;

  /**
   * オープン価格区分
   */
  public String openPriceDiv;

  /**
   * JAN
   */
  public Long jan;

  /**
   * 層別コード
   */
  public String partsLayerCd;

  /**
   * 装備名称
   */
  public String equipName;

  /**
   * 優良部品規格・特記事項
   */
  public String primePartsSpecialNote;

  /**
   * 優良部品規格・特記事項(C向け)
   */
  public String primePartsSpecialNoteC;

  /**
   * 商品詳細(B向け)
   */
  public String goodsDetailB;

  /**
   * 商品詳細(C向け)
   */
  public String goodsDetailC;

  /**
   * 商品サイズ(長さ）
   */
  public String goodsSize1;

  /**
   * 商品サイズ(幅）
   */
  public String goodsSize2;

  /**
   * 商品サイズ(高さ）
   */
  public String goodsSize3;

  /**
   * 梱包サイズ(長さ）
   */
  public String packageSize1;

  /**
   * 梱包サイズ(幅）
   */
  public String packageSize2;

  /**
   * 梱包サイズ(高さ）
   */
  public String packageSize3;

  /**
   * サイズ単位
   */
  public String sizeUnit;

  /**
   * 商品重量
   */
  public String goodsWeight;

  /**
   * カラムステータス
   */
  public String columnStatus;

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
  public String getPartsMakerCd() {
    return partsMakerCd;
  }

  /**
   * <pre>
   * 【partsMakerCd】を設定する。
   * </pre>
   *
   * @param partsMakerCd 【partsMakerCd】
   */
  public void setPartsMakerCd(String partsMakerCd) {
    this.partsMakerCd = partsMakerCd;
  }

  /**
   * <pre>
   * 【goodsMGroup】を取得する。
   * </pre>
   *
   * @return 【goodsMGroup】
   */
  public Integer getGoodsMGroup() {
    return goodsMGroup;
  }

  /**
   * <pre>
   * 【goodsMGroup】を設定する。
   * </pre>
   *
   * @param goodsMGroup 【goodsMGroup】
   */
  public void setGoodsMGroup(Integer goodsMGroup) {
    this.goodsMGroup = goodsMGroup;
  }

  /**
   * <pre>
   * 【tbsPartsCode】を取得する。
   * </pre>
   *
   * @return 【tbsPartsCode】
   */
  public Integer getTbsPartsCode() {
    return tbsPartsCode;
  }

  /**
   * <pre>
   * 【tbsPartsCode】を設定する。
   * </pre>
   *
   * @param tbsPartsCode 【tbsPartsCode】
   */
  public void setTbsPartsCode(Integer tbsPartsCode) {
    this.tbsPartsCode = tbsPartsCode;
  }

  /**
   * <pre>
   * 【primePartsNoWithH】を取得する。
   * </pre>
   *
   * @return 【primePartsNoWithH】
   */
  public String getPrimePartsNoWithH() {
    return primePartsNoWithH;
  }

  /**
   * <pre>
   * 【primePartsNoWithH】を設定する。
   * </pre>
   *
   * @param primePartsNoWithH 【primePartsNoWithH】
   */
  public void setPrimePartsNoWithH(String primePartsNoWithH) {
    this.primePartsNoWithH = primePartsNoWithH;
  }

  /**
   * <pre>
   * 【primePartsKanaNm】を取得する。
   * </pre>
   *
   * @return 【primePartsKanaNm】
   */
  public String getPrimePartsKanaNm() {
    return primePartsKanaNm;
  }

  /**
   * <pre>
   * 【primePartsKanaNm】を設定する。
   * </pre>
   *
   * @param primePartsKanaNm 【primePartsKanaNm】
   */
  public void setPrimePartsKanaNm(String primePartsKanaNm) {
    this.primePartsKanaNm = primePartsKanaNm;
  }

  /**
   * <pre>
   * 【primePartsName】を取得する。
   * </pre>
   *
   * @return 【primePartsName】
   */
  public String getPrimePartsName() {
    return primePartsName;
  }

  /**
   * <pre>
   * 【primePartsName】を設定する。
   * </pre>
   *
   * @param primePartsName 【primePartsName】
   */
  public void setPrimePartsName(String primePartsName) {
    this.primePartsName = primePartsName;
  }

  /**
   * <pre>
   * 【newPrice】を取得する。
   * </pre>
   *
   * @return 【newPrice】
   */
  public String getNewPrice() {
    return newPrice;
  }

  /**
   * <pre>
   * 【newPrice】を設定する。
   * </pre>
   *
   * @param newPrice 【newPrice】
   */
  public void setNewPrice(String newPrice) {
    this.newPrice = newPrice;
  }

  /**
   * <pre>
   * 【openPriceDiv】を取得する。
   * </pre>
   *
   * @return 【openPriceDiv】
   */
  public String getOpenPriceDiv() {
    return openPriceDiv;
  }

  /**
   * <pre>
   * 【openPriceDiv】を設定する。
   * </pre>
   *
   * @param openPriceDiv 【openPriceDiv】
   */
  public void setOpenPriceDiv(String openPriceDiv) {
    this.openPriceDiv = openPriceDiv;
  }

  /**
   * <pre>
   * 【jan】を取得する。
   * </pre>
   *
   * @return 【jan】
   */
  public Long getJan() {
    return jan;
  }

  /**
   * <pre>
   * 【jan】を設定する。
   * </pre>
   *
   * @param jan 【jan】
   */
  public void setJan(Long jan) {
    this.jan = jan;
  }

  /**
   * <pre>
   * 【partsLayerCd】を取得する。
   * </pre>
   *
   * @return 【partsLayerCd】
   */
  public String getPartsLayerCd() {
    return partsLayerCd;
  }

  /**
   * <pre>
   * 【partsLayerCd】を設定する。
   * </pre>
   *
   * @param partsLayerCd 【partsLayerCd】
   */
  public void setPartsLayerCd(String partsLayerCd) {
    this.partsLayerCd = partsLayerCd;
  }

  /**
   * <pre>
   * 【equipName】を取得する。
   * </pre>
   *
   * @return 【equipName】
   */
  public String getEquipName() {
    return equipName;
  }

  /**
   * <pre>
   * 【equipName】を設定する。
   * </pre>
   *
   * @param equipName 【equipName】
   */
  public void setEquipName(String equipName) {
    this.equipName = equipName;
  }

  /**
   * <pre>
   * 【primePartsSpecialNote】を取得する。
   * </pre>
   *
   * @return 【primePartsSpecialNote】
   */
  public String getPrimePartsSpecialNote() {
    return primePartsSpecialNote;
  }

  /**
   * <pre>
   * 【primePartsSpecialNote】を設定する。
   * </pre>
   *
   * @param primePartsSpecialNote 【primePartsSpecialNote】
   */
  public void setPrimePartsSpecialNote(String primePartsSpecialNote) {
    this.primePartsSpecialNote = primePartsSpecialNote;
  }

  /**
   * <pre>
   * 【primePartsSpecialNoteC】を取得する。
   * </pre>
   *
   * @return 【primePartsSpecialNoteC】
   */
  public String getPrimePartsSpecialNoteC() {
    return primePartsSpecialNoteC;
  }

  /**
   * <pre>
   * 【primePartsSpecialNoteC】を設定する。
   * </pre>
   *
   * @param primePartsSpecialNoteC 【primePartsSpecialNoteC】
   */
  public void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC) {
    this.primePartsSpecialNoteC = primePartsSpecialNoteC;
  }

  /**
   * <pre>
   * 【goodsDetailB】を取得する。
   * </pre>
   *
   * @return 【goodsDetailB】
   */
  public String getGoodsDetailB() {
    return goodsDetailB;
  }

  /**
   * <pre>
   * 【goodsDetailB】を設定する。
   * </pre>
   *
   * @param goodsDetailB 【goodsDetailB】
   */
  public void setGoodsDetailB(String goodsDetailB) {
    this.goodsDetailB = goodsDetailB;
  }

  /**
   * <pre>
   * 【goodsDetailC】を取得する。
   * </pre>
   *
   * @return 【goodsDetailC】
   */
  public String getGoodsDetailC() {
    return goodsDetailC;
  }

  /**
   * <pre>
   * 【goodsDetailC】を設定する。
   * </pre>
   *
   * @param goodsDetailC 【goodsDetailC】
   */
  public void setGoodsDetailC(String goodsDetailC) {
    this.goodsDetailC = goodsDetailC;
  }

  /**
   * <pre>
   * 【goodsSize1】を取得する。
   * </pre>
   *
   * @return 【goodsSize1】
   */
  public String getGoodsSize1() {
    return goodsSize1;
  }

  /**
   * <pre>
   * 【goodsSize1】を設定する。
   * </pre>
   *
   * @param goodsSize1 【goodsSize1】
   */
  public void setGoodsSize1(String goodsSize1) {
    this.goodsSize1 = goodsSize1;
  }

  /**
   * <pre>
   * 【goodsSize2】を取得する。
   * </pre>
   *
   * @return 【goodsSize2】
   */
  public String getGoodsSize2() {
    return goodsSize2;
  }

  /**
   * <pre>
   * 【goodsSize2】を設定する。
   * </pre>
   *
   * @param goodsSize2 【goodsSize2】
   */
  public void setGoodsSize2(String goodsSize2) {
    this.goodsSize2 = goodsSize2;
  }

  /**
   * <pre>
   * 【goodsSize3】を取得する。
   * </pre>
   *
   * @return 【goodsSize3】
   */
  public String getGoodsSize3() {
    return goodsSize3;
  }

  /**
   * <pre>
   * 【goodsSize3】を設定する。
   * </pre>
   *
   * @param goodsSize3 【goodsSize3】
   */
  public void setGoodsSize3(String goodsSize3) {
    this.goodsSize3 = goodsSize3;
  }

  /**
   * <pre>
   * 【packageSize1】を取得する。
   * </pre>
   *
   * @return 【packageSize1】
   */
  public String getPackageSize1() {
    return packageSize1;
  }

  /**
   * <pre>
   * 【packageSize1】を設定する。
   * </pre>
   *
   * @param packageSize1 【packageSize1】
   */
  public void setPackageSize1(String packageSize1) {
    this.packageSize1 = packageSize1;
  }

  /**
   * <pre>
   * 【packageSize2】を取得する。
   * </pre>
   *
   * @return 【packageSize2】
   */
  public String getPackageSize2() {
    return packageSize2;
  }

  /**
   * <pre>
   * 【packageSize2】を設定する。
   * </pre>
   *
   * @param packageSize2 【packageSize2】
   */
  public void setPackageSize2(String packageSize2) {
    this.packageSize2 = packageSize2;
  }

  /**
   * <pre>
   * 【packageSize3】を取得する。
   * </pre>
   *
   * @return 【packageSize3】
   */
  public String getPackageSize3() {
    return packageSize3;
  }

  /**
   * <pre>
   * 【packageSize3】を設定する。
   * </pre>
   *
   * @param packageSize3 【packageSize3】
   */
  public void setPackageSize3(String packageSize3) {
    this.packageSize3 = packageSize3;
  }

  /**
   * <pre>
   * 【sizeUnit】を取得する。
   * </pre>
   *
   * @return 【sizeUnit】
   */
  public String getSizeUnit() {
    return sizeUnit;
  }

  /**
   * <pre>
   * 【sizeUnit】を設定する。
   * </pre>
   *
   * @param sizeUnit 【sizeUnit】
   */
  public void setSizeUnit(String sizeUnit) {
    this.sizeUnit = sizeUnit;
  }

  /**
   * <pre>
   * 【goodsWeight】を取得する。
   * </pre>
   *
   * @return 【goodsWeight】
   */
  public String getGoodsWeight() {
    return goodsWeight;
  }

  /**
   * <pre>
   * 【goodsWeight】を設定する。
   * </pre>
   *
   * @param goodsWeight 【goodsWeight】
   */
  public void setGoodsWeight(String goodsWeight) {
    this.goodsWeight = goodsWeight;
  }

  /**
   * <pre>
   * 【weightUnit】を取得する。
   * </pre>
   *
   * @return 【weightUnit】
   */
  public String getWeightUnit() {
    return weightUnit;
  }

  /**
   * <pre>
   * 【weightUnit】を設定する。
   * </pre>
   *
   * @param weightUnit 【weightUnit】
   */
  public void setWeightUnit(String weightUnit) {
    this.weightUnit = weightUnit;
  }

  /**
   * <pre>
   * 【url1】を取得する。
   * </pre>
   *
   * @return 【url1】
   */
  public String getUrl1() {
    return url1;
  }

  /**
   * <pre>
   * 【url1】を設定する。
   * </pre>
   *
   * @param url1 【url1】
   */
  public void setUrl1(String url1) {
    this.url1 = url1;
  }

  /**
   * <pre>
   * 【url2】を取得する。
   * </pre>
   *
   * @return 【url2】
   */
  public String getUrl2() {
    return url2;
  }

  /**
   * <pre>
   * 【url2】を設定する。
   * </pre>
   *
   * @param url2 【url2】
   */
  public void setUrl2(String url2) {
    this.url2 = url2;
  }

  /**
   * <pre>
   * 【url3】を取得する。
   * </pre>
   *
   * @return 【url3】
   */
  public String getUrl3() {
    return url3;
  }

  /**
   * <pre>
   * 【url3】を設定する。
   * </pre>
   *
   * @param url3 【url3】
   */
  public void setUrl3(String url3) {
    this.url3 = url3;
  }

  /**
   * <pre>
   * 【imageNo】を取得する。
   * </pre>
   *
   * @return 【imageNo】
   */
  public short getImageNo() {
    return imageNo;
  }

  /**
   * <pre>
   * 【imageNo】を設定する。
   * </pre>
   *
   * @param imageNo 【imageNo】
   */
  public void setImageNo(short imageNo) {
    this.imageNo = imageNo;
  }

  /**
   * <pre>
   * 【applyNo】を取得する。
   * </pre>
   *
   * @return 【applyNo】
   */
  public String getApplyNo() {
    return applyNo;
  }

  /**
   * <pre>
   * 【applyNo】を設定する。
   * </pre>
   *
   * @param applyNo 【applyNo】
   */
  public void setApplyNo(String applyNo) {
    this.applyNo = applyNo;
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
  public String getdeleteFlg() {
    return deleteFlg;
  }

  /**
   * <pre>
   * 【deleteFlg】を設定する。
   * </pre>
   *
   * @param deleteFlgNew 【deleteFlg】
   */
  public void setdeleteFlg(String deleteFlgNew) {
    this.deleteFlg = deleteFlgNew;
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
   * 【errFlg】を取得する。
   * </pre>
   *
   * @return 【errFlg】
   */
  public short getErrFlg() {
    return errFlg;
  }

  /**
   * <pre>
   * 【errFlg】を設定する。
   * </pre>
   *
   * @param errFlg 【errFlg】
   */
  public void setErrFlg(short errFlg) {
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
   * 重量単位
   */
  public String weightUnit;

  /**
   * URL1
   */
  public String url1;

  /**
   * URL2
   */
  public String url2;

  /**
   * URL3
   */
  public String url3;

  /**
   * 画像数
   */
  public short imageNo;

  /**
   * 申請No
   */
  public String applyNo;

  /**
   * 処理区分
   */
  public String dealFlg;

  /**
   * 削除依頼区分
   */
  public String deleteFlg;

  /**
   * 削除理由
   */
  public String deleteReason;

  /**
   * データステータス
   */
  public short errFlg;

  /**
   * BL登録区分
   */
  public String blEntryFlg;

  /**
   * 適用日時
   */
  public String startTime;

  /**
   * 適用日時
   */
  public String insDtTime;
  /**
   * 適用日時
   */
  public String updDtTime;

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
