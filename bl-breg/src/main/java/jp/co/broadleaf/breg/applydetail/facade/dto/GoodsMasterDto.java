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
   * <pre>
   * 商品マスタ(メーカー)のDtoのクラス。
   * </pre>
   */
  public class GoodsMasterDto {
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
  private String logicalDeleteCode;
  /** 優良設定詳細コード１ */
  private String prmSetDtlNo1;
  /** 部品メーカーコード */
  private int partsMakerCd;
  /** 商品中分類コード */
  private String goodsMGroup;
  /** BLコード */
  private String tbsPartsCode;
  /** 優良品番(－付き品番) */
  private String primePartsNoWithH;
  /** 優良部品カナ名称 */
  private String primePartsKanaNm;
  /** 優良部品名称 */
  private String primePartsName;
  /** 新価格 */
  private String newPrice;
  /** オープン価格区分 */
  private String openPriceDiv;
  /** JAN */
  private long jan;
  /** 層別コード */
  private String partsLayerCd;
  /** 装備名称 */
  private String equipName;
  /** 優良部品規格・特記事項(B向け) */
  private String primePartsSpecialNoteB;
  /** 優良部品規格・特記事項(C向け) */
  private String primePartsSpecialNoteC;
  /** 商品詳細(B向け) */
  private String goodsDetailB;
  /** 商品詳細(C向け) */
  private String goodsDetailC;
  /** 商品サイズ(長さ） */
  private String goodsSize1;
  /** 商品サイズ(幅） */
  private String goodsSize2;
  /** 商品サイズ(高さ） */
  private String goodsSize3;
  /** 梱包サイズ(長さ） */
  private String packageSize1;
  /** 梱包サイズ(幅） */
  private String packageSize2;
  /** 梱包サイズ(高さ） */
  private String packageSize3;
  /** サイズ単位 */
  private String sizeUnit;
  /** 商品重量 */
  private int goodsWeight;
  /** 重量単位 */
  private String weightUnit;
  /** URL1 */
  private String url1;
  /** URL2 */
  private String url2;
  /** URL3 */
  private String url3;
  /** 画像数 */
  private short imageNo;
  /** 適用日時 */
  private String applyTime;
  /** データステータス */
  private String errorFlg;
  /** BL登録区分 */
  private String blEntryFlg;
  /** 処理区分 */
  private String manageKbn;
  /** 削除依頼区分 */
  private String deleteFlg;
  /** 削除理由 */
  private String deleteReason;
  /** 申請状態 */
  private String applyCondition;
  /** 履歴標識 */
  private String goodsFlg;
  /**
   * <pre>
   * 【uuid】を取得する。
   * </pre>
   * @return 【uuid】
   */
  public String getUuid() {
    return uuid;
  }
  /**
   * <pre>
   * 【uuid】を設定する。
   * </pre>
   * @param uuid 【uuid】
   */
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
  /**
   * <pre>
   * 【insDtTime】を取得する。
   * </pre>
   * @return 【insDtTime】
   */
  public String getInsDtTime() {
    return insDtTime;
  }
  /**
   * <pre>
   * 【insDtTime】を設定する。
   * </pre>
   * @param insDtTime 【insDtTime】
   */
  public void setInsDtTime(String insDtTime) {
    this.insDtTime = insDtTime;
  }
  /**
   * <pre>
   * 【updDtTime】を取得する。
   * </pre>
   * @return 【updDtTime】
   */
  public String getUpdDtTime() {
    return updDtTime;
  }
  /**
   * <pre>
   * 【updDtTime】を設定する。
   * </pre>
   * @param updDtTime 【updDtTime】
   */
  public void setUpdDtTime(String updDtTime) {
    this.updDtTime = updDtTime;
  }
  /**
   * <pre>
   * 【insAccountId】を取得する。
   * </pre>
   * @return 【insAccountId】
   */
  public String getInsAccountId() {
    return insAccountId;
  }
  /**
   * <pre>
   * 【insAccountId】を設定する。
   * </pre>
   * @param insAccountId 【insAccountId】
   */
  public void setInsAccountId(String insAccountId) {
    this.insAccountId = insAccountId;
  }
  /**
   * <pre>
   * 【updAccountId】を取得する。
   * </pre>
   * @return 【updAccountId】
   */
  public String getUpdAccountId() {
    return updAccountId;
  }
  /**
   * <pre>
   * 【updAccountId】を設定する。
   * </pre>
   * @param updAccountId 【updAccountId】
   */
  public void setUpdAccountId(String updAccountId) {
    this.updAccountId = updAccountId;
  }
  /**
   * <pre>
   * 【logicalDeleteCode】を取得する。
   * </pre>
   * @return 【logicalDeleteCode】
   */
  public String getLogicalDeleteCode() {
    return logicalDeleteCode;
  }
  /**
   * <pre>
   * 【logicalDeleteCode】を設定する。
   * </pre>
   * @param logicalDeleteCode 【logicalDeleteCode】
   */
  public void setLogicalDeleteCode(String logicalDeleteCode) {
    this.logicalDeleteCode = logicalDeleteCode;
  }
  /**
   * <pre>
   * 【prmSetDtlNo1】を取得する。
   * </pre>
   * @return 【prmSetDtlNo1】
   */
  public String getPrmSetDtlNo1() {
    return prmSetDtlNo1;
  }
  /**
   * <pre>
   * 【prmSetDtlNo1】を設定する。
   * </pre>
   * @param prmSetDtlNo1 【prmSetDtlNo1】
   */
  public void setPrmSetDtlNo1(String prmSetDtlNo1) {
    this.prmSetDtlNo1 = prmSetDtlNo1;
  }
  /**
   * <pre>
   * 【partsMakerCd】を取得する。
   * </pre>
   * @return 【partsMakerCd】
   */
  public int getPartsMakerCd() {
    return partsMakerCd;
  }
  /**
   * <pre>
   * 【partsMakerCd】を設定する。
   * </pre>
   * @param partsMakerCd 【partsMakerCd】
   */
  public void setPartsMakerCd(int partsMakerCd) {
    this.partsMakerCd = partsMakerCd;
  }
  /**
   * <pre>
   * 【goodsMGroup】を取得する。
   * </pre>
   * @return 【goodsMGroup】
   */
  public String getGoodsMGroup() {
    return goodsMGroup;
  }
  /**
   * <pre>
   * 【goodsMGroup】を設定する。
   * </pre>
   * @param goodsMGroup 【goodsMGroup】
   */
  public void setGoodsMGroup(String goodsMGroup) {
    this.goodsMGroup = goodsMGroup;
  }
  /**
   * <pre>
   * 【tbsPartsCode】を取得する。
   * </pre>
   * @return 【tbsPartsCode】
   */
  public String getTbsPartsCode() {
    return tbsPartsCode;
  }
  /**
   * <pre>
   * 【tbsPartsCode】を設定する。
   * </pre>
   * @param tbsPartsCode 【tbsPartsCode】
   */
  public void setTbsPartsCode(String tbsPartsCode) {
    this.tbsPartsCode = tbsPartsCode;
  }
  /**
   * <pre>
   * 【primePartsNoWithH】を取得する。
   * </pre>
   * @return 【primePartsNoWithH】
   */
  public String getPrimePartsNoWithH() {
    return primePartsNoWithH;
  }
  /**
   * <pre>
   * 【primePartsNoWithH】を設定する。
   * </pre>
   * @param primePartsNoWithH 【primePartsNoWithH】
   */
  public void setPrimePartsNoWithH(String primePartsNoWithH) {
    this.primePartsNoWithH = primePartsNoWithH;
  }
  /**
   * <pre>
   * 【primePartsKanaNm】を取得する。
   * </pre>
   * @return 【primePartsKanaNm】
   */
  public String getPrimePartsKanaNm() {
    return primePartsKanaNm;
  }
  /**
   * <pre>
   * 【primePartsKanaNm】を設定する。
   * </pre>
   * @param primePartsKanaNm 【primePartsKanaNm】
   */
  public void setPrimePartsKanaNm(String primePartsKanaNm) {
    this.primePartsKanaNm = primePartsKanaNm;
  }
  /**
   * <pre>
   * 【primePartsName】を取得する。
   * </pre>
   * @return 【primePartsName】
   */
  public String getPrimePartsName() {
    return primePartsName;
  }
  /**
   * <pre>
   * 【primePartsName】を設定する。
   * </pre>
   * @param primePartsName 【primePartsName】
   */
  public void setPrimePartsName(String primePartsName) {
    this.primePartsName = primePartsName;
  }
  /**
   * <pre>
   * 【newPrice】を取得する。
   * </pre>
   * @return 【newPrice】
   */
  public String getNewPrice() {
    return newPrice;
  }
  /**
   * <pre>
   * 【newPrice】を設定する。
   * </pre>
   * @param newPrice 【newPrice】
   */
  public void setNewPrice(String newPrice) {
    this.newPrice = newPrice;
  }
  /**
   * <pre>
   * 【openPriceDiv】を取得する。
   * </pre>
   * @return 【openPriceDiv】
   */
  public String getOpenPriceDiv() {
    return openPriceDiv;
  }
  /**
   * <pre>
   * 【openPriceDiv】を設定する。
   * </pre>
   * @param openPriceDiv 【openPriceDiv】
   */
  public void setOpenPriceDiv(String openPriceDiv) {
    this.openPriceDiv = openPriceDiv;
  }
  /**
   * <pre>
   * 【jan】を取得する。
   * </pre>
   * @return 【jan】
   */
  public long getJan() {
    return jan;
  }
  /**
   * <pre>
   * 【jan】を設定する。
   * </pre>
   * @param jan 【jan】
   */
  public void setJan(long jan) {
    this.jan = jan;
  }
  /**
   * <pre>
   * 【partsLayerCd】を取得する。
   * </pre>
   * @return 【partsLayerCd】
   */
  public String getPartsLayerCd() {
    return partsLayerCd;
  }
  /**
   * <pre>
   * 【partsLayerCd】を設定する。
   * </pre>
   * @param partsLayerCd 【partsLayerCd】
   */
  public void setPartsLayerCd(String partsLayerCd) {
    this.partsLayerCd = partsLayerCd;
  }
  /**
   * <pre>
   * 【equipName】を取得する。
   * </pre>
   * @return 【equipName】
   */
  public String getEquipName() {
    return equipName;
  }
  /**
   * <pre>
   * 【equipName】を設定する。
   * </pre>
   * @param equipName 【equipName】
   */
  public void setEquipName(String equipName) {
    this.equipName = equipName;
  }
  /**
   * <pre>
   * 【primePartsSpecialNoteB】を取得する。
   * </pre>
   * @return 【primePartsSpecialNoteB】
   */
  public String getPrimePartsSpecialNoteB() {
    return primePartsSpecialNoteB;
  }
  /**
   * <pre>
   * 【primePartsSpecialNoteB】を設定する。
   * </pre>
   * @param primePartsSpecialNoteB 【primePartsSpecialNoteB】
   */
  public void setPrimePartsSpecialNoteB(String primePartsSpecialNoteB) {
    this.primePartsSpecialNoteB = primePartsSpecialNoteB;
  }
  /**
   * <pre>
   * 【primePartsSpecialNoteC】を取得する。
   * </pre>
   * @return 【primePartsSpecialNoteC】
   */
  public String getPrimePartsSpecialNoteC() {
    return primePartsSpecialNoteC;
  }
  /**
   * <pre>
   * 【primePartsSpecialNoteC】を設定する。
   * </pre>
   * @param primePartsSpecialNoteC 【primePartsSpecialNoteC】
   */
  public void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC) {
    this.primePartsSpecialNoteC = primePartsSpecialNoteC;
  }
  /**
   * <pre>
   * 【goodsDetailB】を取得する。
   * </pre>
   * @return 【goodsDetailB】
   */
  public String getGoodsDetailB() {
    return goodsDetailB;
  }
  /**
   * <pre>
   * 【goodsDetailB】を設定する。
   * </pre>
   * @param goodsDetailB 【goodsDetailB】
   */
  public void setGoodsDetailB(String goodsDetailB) {
    this.goodsDetailB = goodsDetailB;
  }
  /**
   * <pre>
   * 【goodsDetailC】を取得する。
   * </pre>
   * @return 【goodsDetailC】
   */
  public String getGoodsDetailC() {
    return goodsDetailC;
  }
  /**
   * <pre>
   * 【goodsDetailC】を設定する。
   * </pre>
   * @param goodsDetailC 【goodsDetailC】
   */
  public void setGoodsDetailC(String goodsDetailC) {
    this.goodsDetailC = goodsDetailC;
  }
  /**
   * <pre>
   * 【goodsSize1】を取得する。
   * </pre>
   * @return 【goodsSize1】
   */
  public String getGoodsSize1() {
    return goodsSize1;
  }
  /**
   * <pre>
   * 【goodsSize1】を設定する。
   * </pre>
   * @param goodsSize1 【goodsSize1】
   */
  public void setGoodsSize1(String goodsSize1) {
    this.goodsSize1 = goodsSize1;
  }
  /**
   * <pre>
   * 【goodsSize2】を取得する。
   * </pre>
   * @return 【goodsSize2】
   */
  public String getGoodsSize2() {
    return goodsSize2;
  }
  /**
   * <pre>
   * 【goodsSize2】を設定する。
   * </pre>
   * @param goodsSize2 【goodsSize2】
   */
  public void setGoodsSize2(String goodsSize2) {
    this.goodsSize2 = goodsSize2;
  }
  /**
   * <pre>
   * 【goodsSize3】を取得する。
   * </pre>
   * @return 【goodsSize3】
   */
  public String getGoodsSize3() {
    return goodsSize3;
  }
  /**
   * <pre>
   * 【goodsSize3】を設定する。
   * </pre>
   * @param goodsSize3 【goodsSize3】
   */
  public void setGoodsSize3(String goodsSize3) {
    this.goodsSize3 = goodsSize3;
  }
  /**
   * <pre>
   * 【packageSize1】を取得する。
   * </pre>
   * @return 【packageSize1】
   */
  public String getPackageSize1() {
    return packageSize1;
  }
  /**
   * <pre>
   * 【packageSize1】を設定する。
   * </pre>
   * @param packageSize1 【packageSize1】
   */
  public void setPackageSize1(String packageSize1) {
    this.packageSize1 = packageSize1;
  }
  /**
   * <pre>
   * 【packageSize2】を取得する。
   * </pre>
   * @return 【packageSize2】
   */
  public String getPackageSize2() {
    return packageSize2;
  }
  /**
   * <pre>
   * 【packageSize2】を設定する。
   * </pre>
   * @param packageSize2 【packageSize2】
   */
  public void setPackageSize2(String packageSize2) {
    this.packageSize2 = packageSize2;
  }
  /**
   * <pre>
   * 【packageSize3】を取得する。
   * </pre>
   * @return 【packageSize3】
   */
  public String getPackageSize3() {
    return packageSize3;
  }
  /**
   * <pre>
   * 【packageSize3】を設定する。
   * </pre>
   * @param packageSize3 【packageSize3】
   */
  public void setPackageSize3(String packageSize3) {
    this.packageSize3 = packageSize3;
  }
  /**
   * <pre>
   * 【sizeUnit】を取得する。
   * </pre>
   * @return 【sizeUnit】
   */
  public String getSizeUnit() {
    return sizeUnit;
  }
  /**
   * <pre>
   * 【sizeUnit】を設定する。
   * </pre>
   * @param sizeUnit 【sizeUnit】
   */
  public void setSizeUnit(String sizeUnit) {
    this.sizeUnit = sizeUnit;
  }
  /**
   * <pre>
   * 【goodsWeight】を取得する。
   * </pre>
   * @return 【goodsWeight】
   */
  public int getGoodsWeight() {
    return goodsWeight;
  }
  /**
   * <pre>
   * 【goodsWeight】を設定する。
   * </pre>
   * @param goodsWeight 【goodsWeight】
   */
  public void setGoodsWeight(int goodsWeight) {
    this.goodsWeight = goodsWeight;
  }
  /**
   * <pre>
   * 【weightUnit】を取得する。
   * </pre>
   * @return 【weightUnit】
   */
  public String getWeightUnit() {
    return weightUnit;
  }
  /**
   * <pre>
   * 【weightUnit】を設定する。
   * </pre>
   * @param weightUnit 【weightUnit】
   */
  public void setWeightUnit(String weightUnit) {
    this.weightUnit = weightUnit;
  }
  /**
   * <pre>
   * 【url1】を取得する。
   * </pre>
   * @return 【url1】
   */
  public String getUrl1() {
    return url1;
  }
  /**
   * <pre>
   * 【url1】を設定する。
   * </pre>
   * @param url1 【url1】
   */
  public void setUrl1(String url1) {
    this.url1 = url1;
  }
  /**
   * <pre>
   * 【url2】を取得する。
   * </pre>
   * @return 【url2】
   */
  public String getUrl2() {
    return url2;
  }
  /**
   * <pre>
   * 【url2】を設定する。
   * </pre>
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
   * @param url3 【url3】
   */
  public void setUrl3(String url3) {
    this.url3 = url3;
  }
  /**
   * <pre>
   * 【imageNo】を取得する。
   * </pre>
   * @return 【imageNo】
   */
  public short getImageNo() {
    return imageNo;
  }
  /**
   * <pre>
   * 【imageNo】を設定する。
   * </pre>
   * @param imageNo 【imageNo】
   */
  public void setImageNo(short imageNo) {
    this.imageNo = imageNo;
  }
  /**
   * <pre>
   * 【applyTime】を取得する。
   * </pre>
   * @return 【applyTime】
   */
  public String getapplyTime() {
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
   * 【errorFlg】を取得する。
   * </pre>
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
   * 【goodsFlg】を取得する。
   * </pre>
   *
   * @return 【goodsFlg】
   */
  public String getGoodsFlg() {
    return goodsFlg;
  }
  /**
   * <pre>
   * 【goodsFlg】を設定する。
   * </pre>
   *
   * @param goodsFlg 【goodsFlg】
   */
  public void setGoodsFlg(String goodsFlg) {
    this.goodsFlg = goodsFlg;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(uuid).append(insDtTime).append(updDtTime).append(insAccountId)
        .append(updAccountId).append(logicalDeleteCode).append(prmSetDtlNo1).append(partsMakerCd).append(goodsMGroup)
        .append(tbsPartsCode).append(primePartsNoWithH).append(primePartsKanaNm).append(primePartsName).append(newPrice)
        .append(openPriceDiv).append(jan).append(partsLayerCd).append(equipName).append(primePartsSpecialNoteB)
        .append(primePartsSpecialNoteC).append(goodsDetailB).append(goodsDetailC).append(goodsSize1).append(goodsSize2)
        .append(goodsSize3).append(packageSize1).append(packageSize2).append(packageSize3).append(sizeUnit)
        .append(goodsWeight).append(weightUnit).append(url1).append(url2).append(url3).append(imageNo).append(applyTime)
        .append(errorFlg).append(blEntryFlg).append(manageKbn).append(deleteFlg).append(deleteReason)
        .append(applyCondition).toHashCode();
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
    GoodsMasterDto other = (GoodsMasterDto) obj;
    return new EqualsBuilder().append(uuid, other.uuid).append(insDtTime, other.insDtTime)
        .append(updDtTime, other.updDtTime).append(insAccountId, other.insAccountId)
        .append(updAccountId, other.updAccountId).append(logicalDeleteCode, other.logicalDeleteCode)
        .append(prmSetDtlNo1, other.prmSetDtlNo1).append(partsMakerCd, other.partsMakerCd)
        .append(goodsMGroup, other.goodsMGroup).append(tbsPartsCode, other.tbsPartsCode)
        .append(primePartsNoWithH, other.primePartsNoWithH).append(primePartsKanaNm, other.primePartsKanaNm)
        .append(primePartsName, other.primePartsName).append(newPrice, other.newPrice)
        .append(openPriceDiv, other.openPriceDiv).append(jan, other.jan).append(partsLayerCd, other.partsLayerCd)
        .append(equipName, other.equipName).append(primePartsSpecialNoteB, other.primePartsSpecialNoteB)
        .append(primePartsSpecialNoteC, other.primePartsSpecialNoteC).append(goodsDetailB, other.goodsDetailB)
        .append(goodsDetailC, other.goodsDetailC).append(goodsSize1, other.goodsSize1)
        .append(goodsSize2, other.goodsSize2).append(goodsSize3, other.goodsSize3)
        .append(packageSize1, other.packageSize1).append(packageSize2, other.packageSize2)
        .append(packageSize3, other.packageSize3).append(sizeUnit, other.sizeUnit)
        .append(goodsWeight, other.goodsWeight).append(weightUnit, other.weightUnit).append(url1, other.url1)
        .append(url2, other.url2).append(url3, other.url3).append(imageNo, other.imageNo)
        .append(applyTime, other.applyTime).append(errorFlg, other.errorFlg).append(blEntryFlg, other.blEntryFlg)
        .append(manageKbn, other.manageKbn).append(deleteFlg, other.deleteFlg).append(deleteReason, other.deleteReason)
        .append(applyCondition, other.applyCondition).isEquals();
  }
}
