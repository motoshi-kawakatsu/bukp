//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : wangnan
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.goodslist.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 商品マスタ(メーカー)のDtoのクラス。
 */
public class GoodsMasterMakerDto implements Serializable {
  /**  */
  private static final long serialVersionUID = 2853158980457298939L;

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
   * 優良設定詳細コード１
   */
  private Integer prmSetDtlNo1;

  /**
   * 部品メーカーコード
   */
  private Integer partsMakerCd;

  /**
   * 商品中分類コード
   */
  private Integer goodsMGroup;

  /**
   * BLコード
   */
  private Integer tbsPartsCode;

  /**
   * 優良品番(－付き品番)
   */
  private String primePartsNoWithH;

  /**
   * 優良部品カナ名称
   */
  private String primePartsKanaNm;

  /**
   * 優良部品名称
   */
  private String primePartsName;

  /**
   * 新価格
   */
  private Double newPrice;

  /**
   * オープン価格区分
   */
  private Integer openPriceDiv;

  /**
   * JAN
   */
  private Long jan;

  /**
   * 層別コード
   */
  private String partsLayerCd;

  /**
   * 装備名称
   */
  private String equipName;

  /**
   * 優良部品規格・特記事項(B向け)
   */
  private String primePartsSpecialNoteB;

  /**
   * 優良部品規格・特記事項(C向け)
   */
  private String primePartsSpecialNoteC;

  /**
   * 商品詳細(B向け)
   */
  private String goodsDetailB;

  /**
   * 商品詳細(C向け)
   */
  private String goodsDetailC;

  /**
   * 商品サイズ(長さ）
   */
  private Integer goodsSize1;

  /**
   * 商品サイズ(幅）
   */
  private Integer goodsSize2;

  /**
   * 商品サイズ(高さ）
   */
  private Integer goodsSize3;

  /**
   * 梱包サイズ(長さ）
   */
  private Integer packageSize1;

  /**
   * 梱包サイズ(幅）
   */
  private Integer packageSize2;

  /**
   * 梱包サイズ(高さ）
   */
  private Integer packageSize3;

  /**
   * サイズ単位
   */
  private String sizeUnit;

  /**
   * 商品重量
   */
  private Integer goodsWeight;

  /**
   * 重量単位
   */
  private String weightUnit;

  /**
   * URL1
   */
  private String url1;

  /**
   * URL2
   */
  private String url2;

  /**
   * URL3
   */
  private String url3;

  /**
   * 画像数
   */
  private Short imageNo;

  /**
   * 適用日付
   */
  private Timestamp startDate;

  /**
   * チェック区分
   */
  private Short checkFlg;

  /**
   * データステータス
   */
  private Short errorFlg;

  /**
   * BL登録区分
   */
  private Short blEntryFlg;

  /**
   * インポート区分
   */
  private Short importKbn;

  /**
   * 処理区分
   */
  private Short manageKbn;

  /**
   * エラー内容
   */
  private String errorDetail;

  /**
   * 削除依頼区分
   */
  private Short deleteFlg;

  /**
   * 削除理由
   */
  private String deleteReason;

  /**
   * 申請状態
   */
  private Short applyCondition;
  /**
   * 履歴比較のフラグ
   */
  private String compareFlag;

  /**
   * UUIDの取得。
   *
   * @return String UUID
   */
  public String getUuid() {
    return uuid;
  }

  /**
   * 作成日時の取得。
   *
   * @return Date 作成日時
   */
  public Date getInsDtTime() {
    return insDtTime;
  }

  /**
   * 更新日時の取得。
   *
   * @return Date 更新日時
   */
  public Date getUpdDtTime() {
    return updDtTime;
  }

  /**
   * 作成アカウントIDの取得。
   *
   * @return String 作成アカウントID
   */
  public String getInsAccountId() {
    return insAccountId;
  }

  /**
   * 更新アカウントIDの取得。
   *
   * @return String 更新アカウントID
   */
  public String getUpdAccountId() {
    return updAccountId;
  }

  /**
   * 論理削除区分の取得。
   *
   * @return Integer 論理削除区分
   */
  public Integer getLogicalDeleteCode() {
    return logicalDeleteCode;
  }

  /**
   * 優良設定詳細コード１の取得。
   *
   * @return Integer 優良設定詳細コード１
   */
  public Integer getPrmSetDtlNo1() {
    return prmSetDtlNo1;
  }

  /**
   * 部品メーカーコードの取得。
   *
   * @return Integer 部品メーカーコード
   */
  public Integer getPartsMakerCd() {
    return partsMakerCd;
  }

  /**
   * 商品中分類コードの取得。
   *
   * @return Integer 商品中分類コード
   */
  public Integer getGoodsMGroup() {
    return goodsMGroup;
  }

  /**
   * BLコードの取得。
   *
   * @return Integer BLコード
   */
  public Integer getTbsPartsCode() {
    return tbsPartsCode;
  }

  /**
   * 優良品番(－付き品番)の取得。
   *
   * @return String 優良品番(－付き品番)
   */
  public String getPrimePartsNoWithH() {
    return primePartsNoWithH;
  }

  /**
   * 優良部品カナ名称の取得。
   *
   * @return String 優良部品カナ名称
   */
  public String getPrimePartsKanaNm() {
    return primePartsKanaNm;
  }

  /**
   * 優良部品名称の取得。
   *
   * @return String 優良部品名称
   */
  public String getPrimePartsName() {
    return primePartsName;
  }

  /**
   * 新価格の取得。
   *
   * @return Double 新価格
   */
  public Double getNewPrice() {
    return newPrice;
  }

  /**
   * オープン価格区分の取得。
   *
   * @return Integer オープン価格区分
   */
  public Integer getOpenPriceDiv() {
    return openPriceDiv;
  }

  /**
   * JANの取得。
   *
   * @return Long JAN
   */
  public Long getJan() {
    return jan;
  }

  /**
   * 層別コードの取得。
   *
   * @return String 層別コード
   */
  public String getPartsLayerCd() {
    return partsLayerCd;
  }

  /**
   * 装備名称の取得。
   *
   * @return String 装備名称
   */
  public String getEquipName() {
    return equipName;
  }

  /**
   * 優良部品規格・特記事項(B向け)の取得。
   *
   * @return String 優良部品規格・特記事項(B向け)
   */
  public String getPrimePartsSpecialNoteB() {
    return primePartsSpecialNoteB;
  }

  /**
   * 優良部品規格・特記事項(C向け)の取得。
   *
   * @return String 優良部品規格・特記事項(C向け)
   */
  public String getPrimePartsSpecialNoteC() {
    return primePartsSpecialNoteC;
  }

  /**
   * 商品詳細(B向け)の取得。
   *
   * @return String 商品詳細(B向け)
   */
  public String getGoodsDetailB() {
    return goodsDetailB;
  }

  /**
   * 商品詳細(C向け)の取得。
   *
   * @return String 商品詳細(C向け)
   */
  public String getGoodsDetailC() {
    return goodsDetailC;
  }

  /**
   * 商品サイズ(長さ）の取得。
   *
   * @return Integer 商品サイズ(長さ）
   */
  public Integer getGoodsSize1() {
    return goodsSize1;
  }

  /**
   * 商品サイズ(幅）の取得。
   *
   * @return Integer 商品サイズ(幅）
   */
  public Integer getGoodsSize2() {
    return goodsSize2;
  }

  /**
   * 商品サイズ(高さ）の取得。
   *
   * @return Integer 商品サイズ(高さ）
   */
  public Integer getGoodsSize3() {
    return goodsSize3;
  }

  /**
   * 梱包サイズ(長さ）の取得。
   *
   * @return Integer 梱包サイズ(長さ）
   */
  public Integer getPackageSize1() {
    return packageSize1;
  }

  /**
   * 梱包サイズ(幅）の取得。
   *
   * @return Integer 梱包サイズ(幅）
   */
  public Integer getPackageSize2() {
    return packageSize2;
  }

  /**
   * 梱包サイズ(高さ）の取得。
   *
   * @return Integer 梱包サイズ(高さ）
   */
  public Integer getPackageSize3() {
    return packageSize3;
  }

  /**
   * サイズ単位の取得。
   *
   * @return String サイズ単位
   */
  public String getSizeUnit() {
    return sizeUnit;
  }

  /**
   * 商品重量の取得。
   *
   * @return Integer 商品重量
   */
  public Integer getGoodsWeight() {
    return goodsWeight;
  }

  /**
   * 重量単位の取得。
   *
   * @return String 重量単位
   */
  public String getWeightUnit() {
    return weightUnit;
  }

  /**
   * URL1の取得。
   *
   * @return String URL1
   */
  public String getUrl1() {
    return url1;
  }

  /**
   * URL2の取得。
   *
   * @return String URL2
   */
  public String getUrl2() {
    return url2;
  }

  /**
   * URL3の取得。
   *
   * @return String URL3
   */
  public String getUrl3() {
    return url3;
  }

  /**
   * 画像数の取得。
   *
   * @return Short 画像数
   */
  public Short getImageNo() {
    return imageNo;
  }

  /**
   * 適用日付の取得。
   *
   * @return Integer 適用日付
   */
  public Timestamp getStartDate() {
    return startDate;
  }

  /**
   * チェック区分の取得。
   *
   * @return Short チェック区分
   */
  public Short getCheckFlg() {
    return checkFlg;
  }

  /**
   * データステータスの取得。
   *
   * @return Short データステータス
   */
  public Short getErrorFlg() {
    return errorFlg;
  }

  /**
   * BL登録区分の取得。
   *
   * @return Short BL登録区分
   */
  public Short getBlEntryFlg() {
    return blEntryFlg;
  }

  /**
   * インポート区分の取得。
   *
   * @return Short インポート区分
   */
  public Short getImportKbn() {
    return importKbn;
  }

  /**
   * 処理区分の取得。
   *
   * @return Short 処理区分
   */
  public Short getManageKbn() {
    return manageKbn;
  }

  /**
   * エラー内容の取得。
   *
   * @return String エラー内容
   */
  public String getErrorDetail() {
    return errorDetail;
  }

  /**
   * 削除依頼区分の取得。
   *
   * @return Short 削除依頼区分
   */
  public Short getDeleteFlg() {
    return deleteFlg;
  }

  /**
   * 削除理由の取得。
   *
   * @return String 削除理由
   */
  public String getDeleteReason() {
    return deleteReason;
  }

  /**
   * 申請状態の取得。
   *
   * @return Short 申請状態
   */
  public Short getApplyCondition() {
    return applyCondition;
  }

  /**
   * 履歴比較のフラグの取得。
   *
   * @return String 履歴比較のフラグ
   */
  public String getCompareFlag() {
    return compareFlag;
  }

  /**
   * UUIDの設定。
   *
   * @param uuid UUID
   */
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  /**
   * 作成日時の設定。
   *
   * @param insDtTime 作成日時
   */
  public void setInsDtTime(Date insDtTime) {
    this.insDtTime = insDtTime;
  }

  /**
   * 更新日時の設定。
   *
   * @param updDtTime 更新日時
   */
  public void setUpdDtTime(Date updDtTime) {
    this.updDtTime = updDtTime;
  }

  /**
   * 作成アカウントIDの設定。
   *
   * @param insAccountId 作成アカウントID
   */
  public void setInsAccountId(String insAccountId) {
    this.insAccountId = insAccountId;
  }

  /**
   * 更新アカウントIDの設定。
   *
   * @param updAccountId 更新アカウントID
   */
  public void setUpdAccountId(String updAccountId) {
    this.updAccountId = updAccountId;
  }

  /**
   * 論理削除区分の設定。
   *
   * @param logicalDeleteCode 論理削除区分
   */
  public void setLogicalDeleteCode(Integer logicalDeleteCode) {
    this.logicalDeleteCode = logicalDeleteCode;
  }

  /**
   * 優良設定詳細コード１の設定。
   *
   * @param prmSetDtlNo1 優良設定詳細コード１
   */
  public void setPrmSetDtlNo1(Integer prmSetDtlNo1) {
    this.prmSetDtlNo1 = prmSetDtlNo1;
  }

  /**
   * 部品メーカーコードの設定。
   *
   * @param partsMakerCd 部品メーカーコード
   */
  public void setPartsMakerCd(Integer partsMakerCd) {
    this.partsMakerCd = partsMakerCd;
  }

  /**
   * 商品中分類コードの設定。
   *
   * @param goodsMGroup 商品中分類コード
   */
  public void setGoodsMGroup(Integer goodsMGroup) {
    this.goodsMGroup = goodsMGroup;
  }

  /**
   * BLコードの設定。
   *
   * @param tbsPartsCode BLコード
   */
  public void setTbsPartsCode(Integer tbsPartsCode) {
    this.tbsPartsCode = tbsPartsCode;
  }

  /**
   * 優良品番(－付き品番)の設定。
   *
   * @param primePartsNoWithH 優良品番(－付き品番)
   */
  public void setPrimePartsNoWithH(String primePartsNoWithH) {
    this.primePartsNoWithH = primePartsNoWithH;
  }

  /**
   * 優良部品カナ名称の設定。
   *
   * @param primePartsKanaNm 優良部品カナ名称
   */
  public void setPrimePartsKanaNm(String primePartsKanaNm) {
    this.primePartsKanaNm = primePartsKanaNm;
  }

  /**
   * 優良部品名称の設定。
   *
   * @param primePartsName 優良部品名称
   */
  public void setPrimePartsName(String primePartsName) {
    this.primePartsName = primePartsName;
  }

  /**
   * 新価格の設定。
   *
   * @param newPrice 新価格
   */
  public void setNewPrice(Double newPrice) {
    this.newPrice = newPrice;
  }

  /**
   * オープン価格区分の設定。
   *
   * @param openPriceDiv オープン価格区分
   */
  public void setOpenPriceDiv(Integer openPriceDiv) {
    this.openPriceDiv = openPriceDiv;
  }

  /**
   * JANの設定。
   *
   * @param jan JAN
   */
  public void setJan(Long jan) {
    this.jan = jan;
  }

  /**
   * 層別コードの設定。
   *
   * @param partsLayerCd 層別コード
   */
  public void setPartsLayerCd(String partsLayerCd) {
    this.partsLayerCd = partsLayerCd;
  }

  /**
   * 装備名称の設定。
   *
   * @param equipName 装備名称
   */
  public void setEquipName(String equipName) {
    this.equipName = equipName;
  }

  /**
   * 優良部品規格・特記事項(B向け)の設定。
   *
   * @param primePartsSpecialNoteB 優良部品規格・特記事項(B向け)
   */
  public void setPrimePartsSpecialNoteB(String primePartsSpecialNoteB) {
    this.primePartsSpecialNoteB = primePartsSpecialNoteB;
  }

  /**
   * 優良部品規格・特記事項(C向け)の設定。
   *
   * @param primePartsSpecialNoteC 優良部品規格・特記事項(C向け)
   */
  public void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC) {
    this.primePartsSpecialNoteC = primePartsSpecialNoteC;
  }

  /**
   * 商品詳細(B向け)の設定。
   *
   * @param goodsDetailB 商品詳細(B向け)
   */
  public void setGoodsDetailB(String goodsDetailB) {
    this.goodsDetailB = goodsDetailB;
  }

  /**
   * 商品詳細(C向け)の設定。
   *
   * @param goodsDetailC 商品詳細(C向け)
   */
  public void setGoodsDetailC(String goodsDetailC) {
    this.goodsDetailC = goodsDetailC;
  }

  /**
   * 商品サイズ(長さ）の設定。
   *
   * @param goodsSize1 商品サイズ(長さ）
   */
  public void setGoodsSize1(Integer goodsSize1) {
    this.goodsSize1 = goodsSize1;
  }

  /**
   * 商品サイズ(幅）の設定。
   *
   * @param goodsSize2 商品サイズ(幅）
   */
  public void setGoodsSize2(Integer goodsSize2) {
    this.goodsSize2 = goodsSize2;
  }

  /**
   * 商品サイズ(高さ）の設定。
   *
   * @param goodsSize3 商品サイズ(高さ）
   */
  public void setGoodsSize3(Integer goodsSize3) {
    this.goodsSize3 = goodsSize3;
  }

  /**
   * 梱包サイズ(長さ）の設定。
   *
   * @param packageSize1 梱包サイズ(長さ）
   */
  public void setPackageSize1(Integer packageSize1) {
    this.packageSize1 = packageSize1;
  }

  /**
   * 梱包サイズ(幅）の設定。
   *
   * @param packageSize2 梱包サイズ(幅）
   */
  public void setPackageSize2(Integer packageSize2) {
    this.packageSize2 = packageSize2;
  }

  /**
   * 梱包サイズ(高さ）の設定。
   *
   * @param packageSize3 梱包サイズ(高さ）
   */
  public void setPackageSize3(Integer packageSize3) {
    this.packageSize3 = packageSize3;
  }

  /**
   * サイズ単位の設定。
   *
   * @param sizeUnit サイズ単位
   */
  public void setSizeUnit(String sizeUnit) {
    this.sizeUnit = sizeUnit;
  }

  /**
   * 商品重量の設定。
   *
   * @param goodsWeight 商品重量
   */
  public void setGoodsWeight(Integer goodsWeight) {
    this.goodsWeight = goodsWeight;
  }

  /**
   * 重量単位の設定。
   *
   * @param weightUnit 重量単位
   */
  public void setWeightUnit(String weightUnit) {
    this.weightUnit = weightUnit;
  }

  /**
   * URL1の設定。
   *
   * @param url1 URL1
   */
  public void setUrl1(String url1) {
    this.url1 = url1;
  }

  /**
   * URL2の設定。
   *
   * @param url2 URL2
   */
  public void setUrl2(String url2) {
    this.url2 = url2;
  }

  /**
   * URL3の設定。
   *
   * @param url3 URL3
   */
  public void setUrl3(String url3) {
    this.url3 = url3;
  }

  /**
   * 画像数の設定。
   *
   * @param imageNo 画像数
   */
  public void setImageNo(Short imageNo) {
    this.imageNo = imageNo;
  }

  /**
   * 適用日付の設定。
   *
   * @param startDate 適用日付
   */
  public void setStartDate(Timestamp startDate) {
    this.startDate = startDate;
  }

  /**
   * チェック区分の設定。
   *
   * @param checkFlg チェック区分
   */
  public void setCheckFlg(Short checkFlg) {
    this.checkFlg = checkFlg;
  }

  /**
   * データステータスの設定。
   *
   * @param errorFlg データステータス
   */
  public void setErrorFlg(Short errorFlg) {
    this.errorFlg = errorFlg;
  }

  /**
   * BL登録区分の設定。
   *
   * @param blEntryFlg BL登録区分
   */
  public void setBlEntryFlg(Short blEntryFlg) {
    this.blEntryFlg = blEntryFlg;
  }

  /**
   * インポート区分の設定。
   *
   * @param importKbn インポート区分
   */
  public void setImportKbn(Short importKbn) {
    this.importKbn = importKbn;
  }

  /**
   * 処理区分の設定。
   *
   * @param manageKbn 処理区分
   */
  public void setManageKbn(Short manageKbn) {
    this.manageKbn = manageKbn;
  }

  /**
   * エラー内容の設定。
   *
   * @param errorDetail エラー内容
   */
  public void setErrorDetail(String errorDetail) {
    this.errorDetail = errorDetail;
  }

  /**
   * 削除依頼区分の設定。
   *
   * @param deleteFlg 削除依頼区分
   */
  public void setDeleteFlg(Short deleteFlg) {
    this.deleteFlg = deleteFlg;
  }

  /**
   * 削除理由の設定。
   *
   * @param deleteReason 削除理由
   */
  public void setDeleteReason(String deleteReason) {
    this.deleteReason = deleteReason;
  }

  /**
   * 申請状態の設定。
   *
   * @param applyCondition 申請状態
   */
  public void setApplyCondition(Short applyCondition) {
    this.applyCondition = applyCondition;
  }

  /**
   * 履歴比較のフラグの設定。
   *
   * @param compareFlag 履歴比較のフラグ
   */
  public void setCompareFlag(String compareFlag) {
    this.compareFlag = compareFlag;
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
        .append(goodsWeight).append(weightUnit).append(url1).append(url2).append(url3).append(imageNo).append(startDate)
        .append(checkFlg).append(errorFlg).append(blEntryFlg).append(importKbn).append(manageKbn).append(errorDetail)
        .append(deleteFlg).append(deleteReason).append(applyCondition).append(compareFlag).toHashCode();
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
    GoodsMasterMakerDto other = (GoodsMasterMakerDto) obj;
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
        .append(startDate, other.startDate).append(checkFlg, other.checkFlg).append(errorFlg, other.errorFlg)
        .append(blEntryFlg, other.blEntryFlg).append(importKbn, other.importKbn).append(manageKbn, other.manageKbn)
        .append(errorDetail, other.errorDetail).append(deleteFlg, other.deleteFlg)
        .append(deleteReason, other.deleteReason).append(applyCondition, other.applyCondition)
        .append(compareFlag, other.compareFlag).isEquals();
  }
}
