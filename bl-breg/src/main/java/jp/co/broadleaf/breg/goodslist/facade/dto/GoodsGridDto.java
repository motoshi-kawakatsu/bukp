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

/**
 * 商品マスタ(メーカー)のDtoのクラス。
 */
public class GoodsGridDto implements Serializable {
  /**
   * uid
   */
  private static final long serialVersionUID = 1L;

  /**
   * check
   */
  private Boolean check;
  /**
   * no
   */
  private int no;

  /**
   * 申請状態
   */
  private String applyStep;

  /**
   * 処理区分
   */
  private String manageSec;

  /**
   * セレクトコード名称
   */
  private String selCode;

  /**
   * 分類コード
   */
  private String secCodeName;

  /**
   * BLコード名称
   */
  private String blCodeName;

  /**
   * 優良品番
   */
  private String goodsNo;

  /**
   * 品名（半角）
   */
  private String nameS;

  /**
   * 品名（全角）
   */
  private String nameB;

  /**
   * 価格（税抜）
   */
  private String money;

  /**
   * OPENプランス
   */
  private String open;

  /**
   * JAN
   */
  private String jan;

  /**
   * 層別
   */
  private String layer;

  /**
   * 装備
   */
  private String equip;

  /**
   * 規格/特記
   */
  private String size;

  /**
   * 規格/特記（一般）
   */
  private String sizeCon;

  /**
   * 削除依頼区分
   */
  private String delSec;

  /**
   * 削除理由
   */
  private String delCon;

  /**
   * 商品詳細
   */
  private String goods;

  /**
   * 商品詳細（一般）
   */
  private String goodsCon;

  /**
   * 長さ
   */
  private String width1;

  /**
   * 幅
   */
  private String width2;

  /**
   * 高さ
   */
  private String width3;

  /**
   * 梱包（長さ）
   */
  private String packwidth1;

  /**
   * 梱包（幅）
   */
  private String packwidth2;

  /**
   * 梱包（高さ）
   */
  private String packwidth3;

  /**
   * 梱包単位
   */
  private String widthUnit;

  /**
   * 重量
   */
  private String weight;

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
   * 画像有無
   */
  private String img;

  /**
   * 作成日
   */
  private String dateCom;

  /**
   * 更新日
   */
  private String dateRe;

  /**
   * 適用日
   */
  private String dateSlice;

  /**
   * チェック区分
   */
  private String checkSec;

  /**
   * BL登録区分
   */
  private String blSec;

  /**
   * エラー区分
   */
  private String errSec;

  /**
   * エラー内容
   */
  private String errCon;
  /**
   * 隠匿域
   */
  private Integer hiddenArea;
  /**
   * インポート区分
   */
  private Integer importKbn;
  /**
   * 履歴比較のフラグ
   */
  private String compareFlag;

  /**
   * 履歴比較のフラグの取得。
   *
   * @return String 履歴比較のフラグ
   */
  public String getCompareFlag() {
    return compareFlag;
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
   * Noの取得。
   *
   * @return String No
   */
  public int getNo() {
    return no;
  }

  /**
   * Noの設定。
   *
   * @param no No
   */
  public void setNo(int no) {
    this.no = no;
  }

  /**
   * 申請状態の取得。
   *
   * @return String 申請状態
   */
  public String getApplyStep() {
    return applyStep;
  }

  /**
   * 申請状態の設定。
   *
   * @param applyStep 申請状態
   */
  public void setApplyStep(String applyStep) {
    this.applyStep = applyStep;
  }

  /**
   * 処理区分の取得。
   *
   * @return String 処理区分
   */
  public String getManageSec() {
    return manageSec;
  }

  /**
   * 処理区分の設定。
   *
   * @param manageSec 処理区分
   */
  public void setManageSec(String manageSec) {
    this.manageSec = manageSec;
  }

  /**
   * セレクトコード名称の取得。
   *
   * @return String セレクトコード名称
   */
  public String getSelCode() {
    return selCode;
  }

  /**
   * セレクトコード名称の設定。
   *
   * @param selCode セレクトコード名称
   */
  public void setSelCode(String selCode) {
    this.selCode = selCode;
  }

  /**
   * 分類コードの取得。
   *
   * @return String 分類コード
   */
  public String getSecCodeName() {
    return secCodeName;
  }

  /**
   * 分類コードの設定。
   *
   * @param secCodeName 分類コード
   */
  public void setSecCodeName(String secCodeName) {
    this.secCodeName = secCodeName;
  }

  /**
   * BLコードの取得。
   *
   * @return String BLコード
   */
  public String getBlCodeName() {
    return blCodeName;
  }

  /**
   * BLコードの設定。
   *
   * @param blCodeName BLコード
   */
  public void setBlCodeName(String blCodeName) {
    this.blCodeName = blCodeName;
  }

  /**
   * 優良品番の取得。
   *
   * @return String 優良品番
   */
  public String getGoodsNo() {
    return goodsNo;
  }

  /**
   * 優良品番の設定。
   *
   * @param goodsNo 優良品番
   */
  public void setGoodsNo(String goodsNo) {
    this.goodsNo = goodsNo;
  }

  /**
   * 品名（半角）の取得。
   *
   * @return String 品名（半角）
   */
  public String getNameS() {
    return nameS;
  }

  /**
   * 品名（半角）の設定。
   *
   * @param nameS 品名（半角）
   */
  public void setNameS(String nameS) {
    this.nameS = nameS;
  }

  /**
   * 品名（全角）の取得。
   *
   * @return String 品名（全角）
   */
  public String getNameB() {
    return nameB;
  }

  /**
   * 品名（全角）の設定。
   *
   * @param nameB 品名（全角）
   */
  public void setNameB(String nameB) {
    this.nameB = nameB;
  }

  /**
   * 価格の取得。
   *
   * @return String 価格
   */
  public String getMoney() {
    return money;
  }

  /**
   * 価格の設定。
   *
   * @param money 価格
   */
  public void setMoney(String money) {
    this.money = money;
  }

  /**
   * OPENプランスの取得。
   *
   * @return String OPENプランス
   */
  public String getOpen() {
    return open;
  }

  /**
   * OPENプランスの設定。
   *
   * @param open OPENプランス
   */
  public void setOpen(String open) {
    this.open = open;
  }

  /**
   * JANの取得。
   *
   * @return String JAN
   */
  public String getJan() {
    return jan;
  }

  /**
   * JANの設定。
   *
   * @param jan JAN
   */
  public void setJan(String jan) {
    this.jan = jan;
  }

  /**
   * 層別の取得。
   *
   * @return String 層別
   */
  public String getLayer() {
    return layer;
  }

  /**
   * 層別の設定。
   *
   * @param layer 層別
   */
  public void setLayer(String layer) {
    this.layer = layer;
  }

  /**
   * 装備の取得。
   *
   * @return String 装備
   */
  public String getEquip() {
    return equip;
  }

  /**
   * 装備の設定。
   *
   * @param equip 装備
   */
  public void setEquip(String equip) {
    this.equip = equip;
  }

  /**
   * 規格/特記の取得。
   *
   * @return String 規格/特記
   */
  public String getSize() {
    return size;
  }

  /**
   * 規格/特記の設定。
   *
   * @param size 規格/特記
   */
  public void setSize(String size) {
    this.size = size;
  }

  /**
   * 規格/特記（一般）の取得。
   *
   * @return String 規格/特記（一般）
   */
  public String getSizeCon() {
    return sizeCon;
  }

  /**
   * 規格/特記（一般）の設定。
   *
   * @param sizeCon 規格/特記（一般）
   */
  public void setSizeCon(String sizeCon) {
    this.sizeCon = sizeCon;
  }

  /**
   * 削除依頼区分の取得。
   *
   * @return String 削除依頼区分
   */
  public String getDelSec() {
    return delSec;
  }

  /**
   * 削除依頼区分の設定。
   *
   * @param delSec 削除依頼区分
   */
  public void setDelSec(String delSec) {
    this.delSec = delSec;
  }

  /**
   * 削除理由の取得。
   *
   * @return String 削除理由
   */
  public String getDelCon() {
    return delCon;
  }

  /**
   * 削除理由の設定。
   *
   * @param delCon 削除理由
   */
  public void setDelCon(String delCon) {
    this.delCon = delCon;
  }

  /**
   * 商品詳細の取得。
   *
   * @return String 商品詳細
   */
  public String getGoods() {
    return goods;
  }

  /**
   * 商品詳細の設定。
   *
   * @param goods 商品詳細
   */
  public void setGoods(String goods) {
    this.goods = goods;
  }

  /**
   * 商品詳細（一般）の取得。
   *
   * @return String 商品詳細（一般）
   */
  public String getGoodsCon() {
    return goodsCon;
  }

  /**
   * 商品詳細（一般）の設定。
   *
   * @param goodsCon 商品詳細（一般）
   */
  public void setGoodsCon(String goodsCon) {
    this.goodsCon = goodsCon;
  }

  /**
   * 長さの取得。
   *
   * @return String 長さ
   */
  public String getWidth1() {
    return width1;
  }

  /**
   * 長さの設定。
   *
   * @param width1 長さ
   */
  public void setWidth1(String width1) {
    this.width1 = width1;
  }

  /**
   * 幅の取得。
   *
   * @return String 幅
   */
  public String getWidth2() {
    return width2;
  }

  /**
   * 幅の設定。
   *
   * @param width2 幅
   */
  public void setWidth2(String width2) {
    this.width2 = width2;
  }

  /**
   * 高さの取得。
   *
   * @return String 高さ
   */
  public String getWidth3() {
    return width3;
  }

  /**
   * 高さの設定。
   *
   * @param width3 高さ
   */
  public void setWidth3(String width3) {
    this.width3 = width3;
  }

  /**
   * 梱包（長さ）の取得。
   *
   * @return String 梱包（長さ）
   */
  public String getPackwidth1() {
    return packwidth1;
  }

  /**
   * 梱包（長さ）の設定。
   *
   * @param packwidth1 梱包（長さ）
   */
  public void setPackwidth1(String packwidth1) {
    this.packwidth1 = packwidth1;
  }

  /**
   * 梱包（幅）の取得。
   *
   * @return String 梱包（幅）
   */
  public String getPackwidth2() {
    return packwidth2;
  }

  /**
   * 梱包（幅）の設定。
   *
   * @param packwidth2 梱包（幅）
   */
  public void setPackwidth2(String packwidth2) {
    this.packwidth2 = packwidth2;
  }

  /**
   * 梱包（高さ）の取得。
   *
   * @return String 梱包（高さ）
   */
  public String getPackwidth3() {
    return packwidth3;
  }

  /**
   * 梱包（高さ）の設定。
   *
   * @param packwidth3 梱包（高さ）
   */
  public void setPackwidth3(String packwidth3) {
    this.packwidth3 = packwidth3;
  }

  /**
   * サイズ単位の取得。
   *
   * @return String widthUnit
   */
  public String getWidthUnit() {
    return widthUnit;
  }

  /**
   * サイズ単位の設定。
   *
   * @param widthUnit サイズ単位
   */
  public void setWidthUnit(String widthUnit) {
    this.widthUnit = widthUnit;
  }

  /**
   * 重量の取得。
   *
   * @return String 重量
   */
  public String getWeight() {
    return weight;
  }

  /**
   * 重量の設定。
   *
   * @param weight 重量
   */
  public void setWeight(String weight) {
    this.weight = weight;
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
   * 重量単位の設定。
   *
   * @param weightUnit 重量単位
   */
  public void setWeightUnit(String weightUnit) {
    this.weightUnit = weightUnit;
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
   * URL1の設定。
   *
   * @param url1 URL1
   */
  public void setUrl1(String url1) {
    this.url1 = url1;
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
   * URL2の設定。
   *
   * @param url2 URL2
   */
  public void setUrl2(String url2) {
    this.url2 = url2;
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
   * URL3の設定。
   *
   * @param url3 URL3
   */
  public void setUrl3(String url3) {
    this.url3 = url3;
  }

  /**
   * 画像有無の取得。
   *
   * @return String 画像有無
   */
  public String getImg() {
    return img;
  }

  /**
   * 画像有無の設定。
   *
   * @param img 画像有無
   */
  public void setImg(String img) {
    this.img = img;
  }

  /**
   * 作成日の取得。
   *
   * @return String 作成日
   */
  public String getDateCom() {
    return dateCom;
  }

  /**
   * 作成日の設定。
   *
   * @param dateCom 作成日
   */
  public void setDateCom(String dateCom) {
    this.dateCom = dateCom;
  }

  /**
   * 更新日の取得。
   *
   * @return String 更新日
   */
  public String getDateRe() {
    return dateRe;
  }

  /**
   * 更新日の設定。
   *
   * @param dateRe 更新日
   */
  public void setDateRe(String dateRe) {
    this.dateRe = dateRe;
  }

  /**
   * 適用日の取得。
   *
   * @return String 適用日
   */
  public String getDateSlice() {
    return dateSlice;
  }

  /**
   * 適用日の設定。
   *
   * @param dateSlice 適用日
   */
  public void setDateSlice(String dateSlice) {
    this.dateSlice = dateSlice;
  }

  /**
   * チェック区分の取得。
   *
   * @return String チェック区分
   */
  public String getCheckSec() {
    return checkSec;
  }

  /**
   * チェック区分の設定。
   *
   * @param checkSec チェック区分
   */
  public void setCheckSec(String checkSec) {
    this.checkSec = checkSec;
  }

  /**
   * BL登録区分の取得。
   *
   * @return String BL登録区分
   */
  public String getBlSec() {
    return blSec;
  }

  /**
   * BL登録区分の設定。
   *
   * @param blSec BL登録区分
   */
  public void setBlSec(String blSec) {
    this.blSec = blSec;
  }

  /**
   * エラー区分の取得。
   *
   * @return String エラー区分
   */
  public String getErrSec() {
    return errSec;
  }

  /**
   * エラー区分の設定。
   *
   * @param errSec エラー区分
   */
  public void setErrSec(String errSec) {
    this.errSec = errSec;
  }

  /**
   * エラー内容の取得。
   *
   * @return String エラー内容
   */
  public String getErrCon() {
    return errCon;
  }

  /**
   * エラー内容の設定。
   *
   * @param errCon エラー内容
   */
  public void setErrCon(String errCon) {
    this.errCon = errCon;
  }

  /**
   * 隠匿域の取得。
   *
   * @return String 隠匿域
   */
  public Integer getHiddenArea() {
    return hiddenArea;
  }

  /**
   * 隠匿域の設定。
   *
   * @param hiddenArea 隠匿域
   */
  public void setHiddenArea(Integer hiddenArea) {
    this.hiddenArea = hiddenArea;
  }

  /**
   * インポート区分の取得。
   *
   * @return Short インポート区分
   */
  public Integer getImportKbn() {
    return importKbn;
  }

  /**
   * インポート区分の設定。
   *
   * @param importKbn インポート区分
   */
  public void setImportKbn(Integer importKbn) {
    this.importKbn = importKbn;
  }

  /**
   * checkの取得。
   *
   * @return Boolean check
   */
  public Boolean getCheck() {
    return check;
  }

  /**
   * checkの設定。
   *
   * @param check check
   */
  public void setCheck(Boolean check) {
    this.check = check;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(no).append(applyStep).append(manageSec).append(selCode)
        .append(secCodeName).append(blCodeName).append(goodsNo).append(nameS).append(nameB).append(money).append(open)
        .append(jan).append(layer).append(equip).append(size).append(jan).append(sizeCon).append(delSec).append(delCon)
        .append(goods).append(goodsCon).append(width1).append(width2).append(width3).append(packwidth1)
        .append(packwidth2).append(packwidth3).append(widthUnit).append(weight).append(weightUnit).append(url1)
        .append(url2).append(url3).append(img).append(dateCom).append(dateRe).append(dateSlice).append(checkSec)
        .append(blSec).append(errSec).append(errCon).append(importKbn).append(check).append(compareFlag).toHashCode();
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
    GoodsGridDto other = (GoodsGridDto) obj;
    return new EqualsBuilder().append(no, other.no).append(applyStep, other.applyStep)
        .append(manageSec, other.manageSec).append(selCode, other.selCode).append(secCodeName, other.secCodeName)
        .append(blCodeName, other.blCodeName).append(goodsNo, other.goodsNo).append(nameS, other.nameS)
        .append(nameB, other.nameB).append(money, other.money).append(open, other.open).append(layer, other.layer)
        .append(equip, other.equip).append(size, other.size).append(sizeCon, other.sizeCon).append(jan, other.jan)
        .append(delSec, other.delSec).append(delCon, other.delCon).append(goods, other.goods)
        .append(goodsCon, other.goodsCon).append(width1, other.width1).append(width2, other.width2)
        .append(width3, other.width3).append(packwidth1, other.packwidth1).append(packwidth2, other.packwidth2)
        .append(packwidth3, other.packwidth3).append(widthUnit, other.widthUnit).append(weight, other.weight)
        .append(url1, other.url1).append(url2, other.url2).append(url3, other.url3).append(img, other.img)
        .append(dateCom, other.dateCom).append(dateRe, other.dateRe).append(dateSlice, other.dateSlice)
        .append(checkSec, other.checkSec).append(blSec, other.blSec).append(errSec, other.errSec)
        .append(errCon, other.errCon).append(importKbn, other.importKbn).append(check, other.check)
        .append(compareFlag, other.compareFlag).isEquals();
  }

}
