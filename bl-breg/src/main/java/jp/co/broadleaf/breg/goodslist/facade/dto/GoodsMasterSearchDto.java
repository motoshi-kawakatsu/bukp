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
 * セットマスタのDtoのクラス。
 */
public class GoodsMasterSearchDto implements Serializable {

  /**  */
  private static final long serialVersionUID = 1346350657454712322L;
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
   * 申請状態
   */
  private Short applyCondition;
  /**
   * 処理区分
   */
  private Short manageKbn;
  /**
   * データステータス
   */
  private Short errorFlg;

  /**
   * 優良設定詳細コード１
   */
  private Integer prmSetDtlNo1;
  /**
   * 層別コード
   */
  private String partsLayerCd;

  /**
   * 適用日付
   */
  private Timestamp startDateStart;

  /**
   * 適用日付
   */
  private Timestamp startDateEnd;

  /**
   * 商品中分類コード
   */
  private Integer goodsMGroup;
  /**
   * オープン価格区分
   */
  private Integer openPriceDiv;
  /**
   * 作成日時
   */
  private Date insDtTimeStart;
  /**
   * 作成日時
   */
  private Date insDtTimeEnd;

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
   * 更新日時
   */
  private Date updDtTimeStart;

  /**
   * 更新日時
   */
  private Date updDtTimeEnd;
  /**
   * 削除依頼区分
   */
  private Short deleteFlg;
  /**
   * 商品詳細(B向け)
   */
  private String goodsDetailB;

  /**
   * 商品詳細(C向け)
   */
  private String goodsDetailC;
  /**
   * 部品メーカーコード
   */
  private int partsMakerCd;

  /**
   * <pre>
   * 【 部品メーカーコード】を取得する。
   * </pre>
   *
   * @return 【 部品メーカーコード】
   */
  public int getPartsMakerCd() {
    return partsMakerCd;
  }

  /**
   * 部品メーカーコードの設定。
   *
   * @param partsMakerCd 部品メーカーコード
   */
  public void setPartsMakerCd(int partsMakerCd) {
    this.partsMakerCd = partsMakerCd;
  }

  /**
   * <pre>
   * 【 優良設定詳細コード１】を取得する。
   * </pre>
   *
   * @return 【 優良設定詳細コード１】
   */
  public Integer getPrmSetDtlNo1() {
    return prmSetDtlNo1;
  }

  /**
   * <pre>
   * 【 優良設定詳細コード１】を設定する。
   * </pre>
   *
   * @param prmSetDtlNo1 【 優良設定詳細コード１】
   */
  public void setPrmSetDtlNo1(Integer prmSetDtlNo1) {
    this.prmSetDtlNo1 = prmSetDtlNo1;
  }

  /**
   * <pre>
   * 【 商品中分類コード】を取得する。
   * </pre>
   *
   * @return 【 商品中分類コード】
   */
  public Integer getGoodsMGroup() {
    return goodsMGroup;
  }

  /**
   * <pre>
   * 【 商品中分類コード】を設定する。
   * </pre>
   *
   * @param goodsMGroup 【 商品中分類コード】
   */
  public void setGoodsMGroup(Integer goodsMGroup) {
    this.goodsMGroup = goodsMGroup;
  }

  /**
   * <pre>
   * 【BLコード】を取得する。
   * </pre>
   *
   * @return 【BLコード】
   */
  public Integer getTbsPartsCode() {
    return tbsPartsCode;
  }

  /**
   * <pre>
   * 【BLコード】を設定する。
   * </pre>
   *
   * @param tbsPartsCode 【BLコード】
   */
  public void setTbsPartsCode(Integer tbsPartsCode) {
    this.tbsPartsCode = tbsPartsCode;
  }

  /**
   * <pre>
   * 【優良部品規格・特記事項(C向け)】を取得する。
   * </pre>
   *
   * @return 【優良部品規格・特記事項(C向け)】
   */
  public String getPrimePartsSpecialNoteC() {
    return primePartsSpecialNoteC;
  }

  /**
   * <pre>
   * 【優良部品規格・特記事項(C向け)】を設定する。
   * </pre>
   *
   * @param primePartsSpecialNoteC 【優良部品規格・特記事項(C向け)】
   */
  public void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC) {
    this.primePartsSpecialNoteC = primePartsSpecialNoteC;
  }

  /**
   * <pre>
   * 【 データステータス】を取得する。
   * </pre>
   *
   * @return 【 データステータス】
   */
  public Short getErrorFlg() {
    return errorFlg;
  }

  /**
   * <pre>
   * 【 データステータス】を設定する。
   * </pre>
   *
   * @param errorFlg 【 データステータス】
   */
  public void setErrorFlg(Short errorFlg) {
    this.errorFlg = errorFlg;
  }

  /**
   * <pre>
   * 【処理区分】を取得する。
   * </pre>
   *
   * @return 【処理区分】
   */
  public Short getManageKbn() {
    return manageKbn;
  }

  /**
   * <pre>
   * 【処理区分】を設定する。
   * </pre>
   *
   * @param manageKbn 【処理区分】
   */
  public void setManageKbn(Short manageKbn) {
    this.manageKbn = manageKbn;
  }

  /**
   * <pre>
   * 【 削除依頼区分】を取得する。
   * </pre>
   *
   * @return 【 削除依頼区分】
   */
  public Short getDeleteFlg() {
    return deleteFlg;
  }

  /**
   * <pre>
   * 【 削除依頼区分】を設定する。
   * </pre>
   *
   * @param deleteFlg 【 削除依頼区分】
   */
  public void setDeleteFlg(Short deleteFlg) {
    this.deleteFlg = deleteFlg;
  }

  /**
   * <pre>
   * 【申請状態】を取得する。
   * </pre>
   *
   * @return 【申請状態】
   */
  public Short getApplyCondition() {
    return applyCondition;
  }

  /**
   * <pre>
   * 【申請状態】を設定する。
   * </pre>
   *
   * @param applyCondition 【申請状態】
   */
  public void setApplyCondition(Short applyCondition) {
    this.applyCondition = applyCondition;
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
   * 優良品番(－付き品番)の設定。
   *
   * @param primePartsNoWithH 優良品番(－付き品番)
   */
  public void setPrimePartsNoWithH(String primePartsNoWithH) {
    this.primePartsNoWithH = primePartsNoWithH;
  }

  /**
   * <pre>
   * 【優良部品カナ名称】を取得する。
   * </pre>
   *
   * @return 【優良部品カナ名称】
   */
  public String getPrimePartsKanaNm() {
    return primePartsKanaNm;
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
   * <pre>
   * 【層別コード】を取得する。
   * </pre>
   *
   * @return 【層別コード】
   */
  public String getPartsLayerCd() {
    return partsLayerCd;
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
   * <pre>
   * 【適用日付】を取得する。
   * </pre>
   *
   * @return 【適用日付】
   */
  public Timestamp getStartDateStart() {
    return startDateStart;
  }

  /**
   * 適用日付の設定。
   *
   * @param startDateStart 適用日付
   */
  public void setStartDateStart(Timestamp startDateStart) {
    this.startDateStart = startDateStart;
  }

  /**
   * <pre>
   * 【適用日付】を取得する。
   * </pre>
   *
   * @return 【適用日付】
   */
  public Timestamp getStartDateEnd() {
    return startDateEnd;
  }

  /**
   * 適用日付の設定。
   *
   * @param startDateEnd 適用日付
   */
  public void setStartDateEnd(Timestamp startDateEnd) {
    this.startDateEnd = startDateEnd;
  }

  /**
   * <pre>
   * 【オープン価格区分】を取得する。
   * </pre>
   *
   * @return 【オープン価格区分】
   */
  public Integer getOpenPriceDiv() {
    return openPriceDiv;
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
   * <pre>
   * 【作成日時】を取得する。
   * </pre>
   *
   * @return 【作成日時】
   */
  public Date getInsDtTimeStart() {
    return insDtTimeStart;
  }

  /**
   * 作成日時の設定。
   *
   * @param insDtTimeStart 作成日時
   */
  public void setInsDtTimeStart(Date insDtTimeStart) {
    this.insDtTimeStart = insDtTimeStart;
  }

  /**
   * <pre>
   * 【作成日時】を取得する。
   * </pre>
   *
   * @return 【作成日時】
   */
  public Date getInsDtTimeEnd() {
    return insDtTimeEnd;
  }

  /**
   * 作成日時の設定。
   *
   * @param insDtTimeEnd 作成日時
   */
  public void setInsDtTimeEnd(Date insDtTimeEnd) {
    this.insDtTimeEnd = insDtTimeEnd;
  }

  /**
   * <pre>
   * 【装備名称】を取得する。
   * </pre>
   *
   * @return 【装備名称】
   */
  public String getEquipName() {
    return equipName;
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
   * <pre>
   * 【 優良部品規格・特記事項(B向け)】を取得する。
   * </pre>
   *
   * @return 【 優良部品規格・特記事項(B向け)】
   */
  public String getPrimePartsSpecialNoteB() {
    return primePartsSpecialNoteB;
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
   * <pre>
   * 【更新日時】を取得する。
   * </pre>
   *
   * @return 【更新日時】
   */
  public Date getUpdDtTimeStart() {
    return updDtTimeStart;
  }

  /**
   * 更新日時の設定。
   *
   * @param updDtTimeStart 更新日時
   */
  public void setUpdDtTimeStart(Date updDtTimeStart) {
    this.updDtTimeStart = updDtTimeStart;
  }

  /**
   * <pre>
   * 【更新日時】を取得する。
   * </pre>
   *
   * @return 【更新日時】
   */
  public Date getUpdDtTimeEnd() {
    return updDtTimeEnd;
  }

  /**
   * 更新日時の設定。
   *
   * @param updDtTimeEnd 更新日時
   */
  public void setUpdDtTimeEnd(Date updDtTimeEnd) {
    this.updDtTimeEnd = updDtTimeEnd;
  }

  /**
   * <pre>
   * 【商品詳細(B向け)】を取得する。
   * </pre>
   *
   * @return 【商品詳細(B向け)】
   */
  public String getGoodsDetailB() {
    return goodsDetailB;
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
   * <pre>
   * 【商品詳細(C向け)】を取得する。
   * </pre>
   *
   * @return 【商品詳細(C向け)】
   */
  public String getGoodsDetailC() {
    return goodsDetailC;
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
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(tbsPartsCode).append(primePartsNoWithH).append(primePartsKanaNm)
        .append(applyCondition).append(manageKbn).append(errorFlg).append(prmSetDtlNo1).append(partsLayerCd)
        .append(startDateStart).append(startDateEnd).append(goodsMGroup).append(openPriceDiv).append(insDtTimeStart)
        .append(insDtTimeEnd).append(equipName).append(primePartsSpecialNoteB).append(primePartsSpecialNoteC)
        .append(updDtTimeStart).append(updDtTimeEnd).append(deleteFlg).append(goodsDetailB).append(goodsDetailC)
        .toHashCode();
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
    GoodsMasterSearchDto other = (GoodsMasterSearchDto) obj;
    return new EqualsBuilder().append(tbsPartsCode, other.tbsPartsCode)
        .append(primePartsNoWithH, other.primePartsNoWithH).append(primePartsKanaNm, other.primePartsKanaNm)
        .append(applyCondition, other.applyCondition).append(manageKbn, other.manageKbn)
        .append(errorFlg, other.errorFlg).append(prmSetDtlNo1, other.prmSetDtlNo1)
        .append(partsLayerCd, other.partsLayerCd).append(startDateStart, other.startDateStart)
        .append(startDateEnd, other.startDateEnd).append(goodsMGroup, other.goodsMGroup)
        .append(openPriceDiv, other.openPriceDiv).append(insDtTimeStart, other.insDtTimeStart)
        .append(insDtTimeEnd, other.insDtTimeEnd).append(equipName, other.equipName)
        .append(primePartsSpecialNoteB, other.primePartsSpecialNoteB)
        .append(primePartsSpecialNoteC, other.primePartsSpecialNoteC).append(updDtTimeStart, other.updDtTimeStart)
        .append(updDtTimeEnd, other.updDtTimeEnd).append(deleteFlg, other.deleteFlg)
        .append(goodsDetailB, other.goodsDetailB).append(goodsDetailC, other.goodsDetailC).isEquals();
  }
}
