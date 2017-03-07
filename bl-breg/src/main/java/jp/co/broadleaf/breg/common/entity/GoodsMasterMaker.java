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
package jp.co.broadleaf.breg.common.entity;

import jp.co.broadleaf.common.domain.entity.BroadleafDomainEntity;
import jp.co.broadleaf.common.domain.entity.BroadleafOptimisticConcurrencyResolver;
import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.Entity;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrency;

import org.apache.commons.lang3.ArrayUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品マスタ(メーカー)本
 */
@Entity(name = "m_goods_maker", type = "maker")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class GoodsMasterMaker extends BroadleafDomainEntity<GoodsMasterMakerId> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * 優良設定詳細コード１
   */
  public static final String PRM_SET_DTL_NO_1 = "prmSetDtlNo1";

  /**
   * 部品メーカーコード
   */
  public static final String PARTS_MAKER_CD = "partsMakerCd";

  /**
   * 商品中分類コード
   */
  public static final String GOODS_M_GROUP = "goodsMGroup";

  /**
   * BLコード
   */
  public static final String TBS_PARTS_CODE = "tbsPartsCode";

  /**
   * 優良品番(－付き品番)
   */
  public static final String PRIME_PARTS_NO_WITH_H = "primePartsNoWithH";

  /**
   * 優良部品カナ名称
   */
  public static final String PRIME_PARTS_KANA_NM = "primePartsKanaNm";

  /**
   * 優良部品名称
   */
  public static final String PRIME_PARTS_NAME = "primePartsName";

  /**
   * 新価格
   */
  public static final String NEW_PRICE = "newPrice";

  /**
   * オープン価格区分
   */
  public static final String OPEN_PRICE_DIV = "openPriceDiv";

  /**
   * JAN
   */
  public static final String JAN = "jan";

  /**
   * 層別コード
   */
  public static final String PARTS_LAYER_CD = "partsLayerCd";

  /**
   * 装備名称
   */
  public static final String EQUIP_NAME = "equipName";

  /**
   * 優良部品規格・特記事項(B向け)
   */
  public static final String PRIME_PARTS_SPECIAL_NOTE_B = "primePartsSpecialNoteB";

  /**
   * 優良部品規格・特記事項(C向け)
   */
  public static final String PRIME_PARTS_SPECIAL_NOTE_C = "primePartsSpecialNoteC";

  /**
   * 商品詳細(B向け)
   */
  public static final String GOODS_DETAIL_B = "goodsDetailB";

  /**
   * 商品詳細(C向け)
   */
  public static final String GOODS_DETAIL_C = "goodsDetailC";

  /**
   * 商品サイズ(長さ）
   */
  public static final String GOODS_SIZE_1 = "goodsSize1";

  /**
   * 商品サイズ(幅）
   */
  public static final String GOODS_SIZE_2 = "goodsSize2";

  /**
   * 商品サイズ(高さ）
   */
  public static final String GOODS_SIZE_3 = "goodsSize3";

  /**
   * 梱包サイズ(長さ）
   */
  public static final String PACKAGE_SIZE_1 = "packageSize1";

  /**
   * 梱包サイズ(幅）
   */
  public static final String PACKAGE_SIZE_2 = "packageSize2";

  /**
   * 梱包サイズ(高さ）
   */
  public static final String PACKAGE_SIZE_3 = "packageSize3";

  /**
   * サイズ単位
   */
  public static final String SIZE_UNIT = "sizeUnit";

  /**
   * 商品重量
   */
  public static final String GOODS_WEIGHT = "goodsWeight";

  /**
   * 重量単位
   */
  public static final String WEIGHT_UNIT = "weightUnit";

  /**
   * URL1
   */
  public static final String URL_1 = "url1";

  /**
   * URL2
   */
  public static final String URL_2 = "url2";

  /**
   * URL3
   */
  public static final String URL_3 = "url3";

  /**
   * 画像数
   */
  public static final String IMAGE_NO = "imageNo";

  /**
   * 適用日時
   */
  public static final String START_TIME = "startTime";

  /**
   * チェック区分
   */
  public static final String CHECK_FLG = "checkFlg";

  /**
   * データステータス
   */
  public static final String ERROR_FLG = "errorFlg";

  /**
   * BL登録区分
   */
  public static final String BL_ENTRY_FLG = "blEntryFlg";

  /**
   * インポート区分
   */
  public static final String IMPORT_KBN = "importKbn";

  /**
   * 処理区分
   */
  public static final String MANAGE_KBN = "manageKbn";

  /**
   * エラー内容
   */
  public static final String ERROR_DETAIL = "errorDetail";

  /**
   * 削除依頼区分
   */
  public static final String DELETE_FLG = "deleteFlg";

  /**
   * 削除理由
   */
  public static final String DELETE_REASON = "deleteReason";

  /**
   * 申請状態
   */
  public static final String APPLY_CONDITION = "applyCondition";

  /**
   * コンストラクタ。
   */
  public GoodsMasterMaker() {

  }

  /**
   * コンストラクタ。
   * 
   * @param id ID
   */
  public GoodsMasterMaker(GoodsMasterMakerId id) {
    setPrmSetDtlNo1(id.getPrmSetDtlNo1());
    setPartsMakerCd(id.getPartsMakerCd());
    setPrimePartsNoWithH(id.getPrimePartsNoWithH());
  }

  /**
   * 優良設定詳細コード１を返します。
   * 
   * @return 優良設定詳細コード１
   */
  public int getPrmSetDtlNo1() {
    return container().getPropertyValue(PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 優良設定詳細コード１を設定します。
   * 
   * @param prmSetDtlNo1 セットする優良設定詳細コード１
   */
  public void setPrmSetDtlNo1(int prmSetDtlNo1) {
    container().setPropertyValue(PRM_SET_DTL_NO_1, prmSetDtlNo1);
  }

  /**
   * 部品メーカーコードを返します。
   * 
   * @return 部品メーカーコード
   */
  public int getPartsMakerCd() {
    return container().getPropertyValue(PARTS_MAKER_CD, Integer.class);
  }

  /**
   * 部品メーカーコードを設定します。
   * 
   * @param partsMakerCd セットする部品メーカーコード
   */
  public void setPartsMakerCd(int partsMakerCd) {
    container().setPropertyValue(PARTS_MAKER_CD, partsMakerCd);
  }

  /**
   * 商品中分類コードを返します。
   * 
   * @return 商品中分類コード
   */
  public Integer getGoodsMGroup() {
    return container().getPropertyValue(GOODS_M_GROUP, Integer.class);
  }

  /**
   * 商品中分類コードを設定します。
   * 
   * @param goodsMGroup セットする商品中分類コード
   */
  public void setGoodsMGroup(Integer goodsMGroup) {
    container().setPropertyValue(GOODS_M_GROUP, goodsMGroup);
  }

  /**
   * BLコードを返します。
   * 
   * @return BLコード
   */
  public Integer getTbsPartsCode() {
    return container().getPropertyValue(TBS_PARTS_CODE, Integer.class);
  }

  /**
   * BLコードを設定します。
   * 
   * @param tbsPartsCode セットするBLコード
   */
  public void setTbsPartsCode(Integer tbsPartsCode) {
    container().setPropertyValue(TBS_PARTS_CODE, tbsPartsCode);
  }

  /**
   * 優良品番(－付き品番)を返します。
   * 
   * @return 優良品番(－付き品番)
   */
  public String getPrimePartsNoWithH() {
    return container().getPropertyValue(PRIME_PARTS_NO_WITH_H, String.class);
  }

  /**
   * 優良品番(－付き品番)を設定します。
   * 
   * @param primePartsNoWithH セットする優良品番(－付き品番)
   */
  public void setPrimePartsNoWithH(String primePartsNoWithH) {
    container().setPropertyValue(PRIME_PARTS_NO_WITH_H, primePartsNoWithH);
  }

  /**
   * 優良部品カナ名称を返します。
   * 
   * @return 優良部品カナ名称
   */
  public String getPrimePartsKanaNm() {
    return container().getPropertyValue(PRIME_PARTS_KANA_NM, String.class);
  }

  /**
   * 優良部品カナ名称を設定します。
   * 
   * @param primePartsKanaNm セットする優良部品カナ名称
   */
  public void setPrimePartsKanaNm(String primePartsKanaNm) {
    container().setPropertyValue(PRIME_PARTS_KANA_NM, primePartsKanaNm);
  }

  /**
   * 優良部品名称を返します。
   * 
   * @return 優良部品名称
   */
  public String getPrimePartsName() {
    return container().getPropertyValue(PRIME_PARTS_NAME, String.class);
  }

  /**
   * 優良部品名称を設定します。
   * 
   * @param primePartsName セットする優良部品名称
   */
  public void setPrimePartsName(String primePartsName) {
    container().setPropertyValue(PRIME_PARTS_NAME, primePartsName);
  }

  /**
   * 新価格を返します。
   * 
   * @return 新価格
   */
  public Double getNewPrice() {
    return container().getPropertyValue(NEW_PRICE, Double.class);
  }

  /**
   * 新価格を設定します。
   * 
   * @param newPrice セットする新価格
   */
  public void setNewPrice(Double newPrice) {
    container().setPropertyValue(NEW_PRICE, newPrice);
  }

  /**
   * オープン価格区分を返します。
   * 
   * @return オープン価格区分
   */
  public Integer getOpenPriceDiv() {
    return container().getPropertyValue(OPEN_PRICE_DIV, Integer.class);
  }

  /**
   * オープン価格区分を設定します。
   * 
   * @param openPriceDiv セットするオープン価格区分
   */
  public void setOpenPriceDiv(Integer openPriceDiv) {
    container().setPropertyValue(OPEN_PRICE_DIV, openPriceDiv);
  }

  /**
   * JANを返します。
   * 
   * @return JAN
   */
  public Long getJan() {
    return container().getPropertyValue(JAN, Long.class);
  }

  /**
   * JANを設定します。
   * 
   * @param jan セットするJAN
   */
  public void setJan(Long jan) {
    container().setPropertyValue(JAN, jan);
  }

  /**
   * 層別コードを返します。
   * 
   * @return 層別コード
   */
  public String getPartsLayerCd() {
    return container().getPropertyValue(PARTS_LAYER_CD, String.class);
  }

  /**
   * 層別コードを設定します。
   * 
   * @param partsLayerCd セットする層別コード
   */
  public void setPartsLayerCd(String partsLayerCd) {
    container().setPropertyValue(PARTS_LAYER_CD, partsLayerCd);
  }

  /**
   * 装備名称を返します。
   * 
   * @return 装備名称
   */
  public String getEquipName() {
    return container().getPropertyValue(EQUIP_NAME, String.class);
  }

  /**
   * 装備名称を設定します。
   * 
   * @param equipName セットする装備名称
   */
  public void setEquipName(String equipName) {
    container().setPropertyValue(EQUIP_NAME, equipName);
  }

  /**
   * 優良部品規格・特記事項(B向け)を返します。
   * 
   * @return 優良部品規格・特記事項(B向け)
   */
  public String getPrimePartsSpecialNoteB() {
    return container().getPropertyValue(PRIME_PARTS_SPECIAL_NOTE_B, String.class);
  }

  /**
   * 優良部品規格・特記事項(B向け)を設定します。
   * 
   * @param primePartsSpecialNoteB セットする優良部品規格・特記事項(B向け)
   */
  public void setPrimePartsSpecialNoteB(String primePartsSpecialNoteB) {
    container().setPropertyValue(PRIME_PARTS_SPECIAL_NOTE_B, primePartsSpecialNoteB);
  }

  /**
   * 優良部品規格・特記事項(C向け)を返します。
   * 
   * @return 優良部品規格・特記事項(C向け)
   */
  public String getPrimePartsSpecialNoteC() {
    return container().getPropertyValue(PRIME_PARTS_SPECIAL_NOTE_C, String.class);
  }

  /**
   * 優良部品規格・特記事項(C向け)を設定します。
   * 
   * @param primePartsSpecialNoteC セットする優良部品規格・特記事項(C向け)
   */
  public void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC) {
    container().setPropertyValue(PRIME_PARTS_SPECIAL_NOTE_C, primePartsSpecialNoteC);
  }

  /**
   * 商品詳細(B向け)を返します。
   * 
   * @return 商品詳細(B向け)
   */
  public String getGoodsDetailB() {
    return container().getPropertyValue(GOODS_DETAIL_B, String.class);
  }

  /**
   * 商品詳細(B向け)を設定します。
   * 
   * @param goodsDetailB セットする商品詳細(B向け)
   */
  public void setGoodsDetailB(String goodsDetailB) {
    container().setPropertyValue(GOODS_DETAIL_B, goodsDetailB);
  }

  /**
   * 商品詳細(C向け)を返します。
   * 
   * @return 商品詳細(C向け)
   */
  public String getGoodsDetailC() {
    return container().getPropertyValue(GOODS_DETAIL_C, String.class);
  }

  /**
   * 商品詳細(C向け)を設定します。
   * 
   * @param goodsDetailC セットする商品詳細(C向け)
   */
  public void setGoodsDetailC(String goodsDetailC) {
    container().setPropertyValue(GOODS_DETAIL_C, goodsDetailC);
  }

  /**
   * 商品サイズ(長さ）を返します。
   * 
   * @return 商品サイズ(長さ）
   */
  public Integer getGoodsSize1() {
    return container().getPropertyValue(GOODS_SIZE_1, Integer.class);
  }

  /**
   * 商品サイズ(長さ）を設定します。
   * 
   * @param goodsSize1 セットする商品サイズ(長さ）
   */
  public void setGoodsSize1(Integer goodsSize1) {
    container().setPropertyValue(GOODS_SIZE_1, goodsSize1);
  }

  /**
   * 商品サイズ(幅）を返します。
   * 
   * @return 商品サイズ(幅）
   */
  public Integer getGoodsSize2() {
    return container().getPropertyValue(GOODS_SIZE_2, Integer.class);
  }

  /**
   * 商品サイズ(幅）を設定します。
   * 
   * @param goodsSize2 セットする商品サイズ(幅）
   */
  public void setGoodsSize2(Integer goodsSize2) {
    container().setPropertyValue(GOODS_SIZE_2, goodsSize2);
  }

  /**
   * 商品サイズ(高さ）を返します。
   * 
   * @return 商品サイズ(高さ）
   */
  public Integer getGoodsSize3() {
    return container().getPropertyValue(GOODS_SIZE_3, Integer.class);
  }

  /**
   * 商品サイズ(高さ）を設定します。
   * 
   * @param goodsSize3 セットする商品サイズ(高さ）
   */
  public void setGoodsSize3(Integer goodsSize3) {
    container().setPropertyValue(GOODS_SIZE_3, goodsSize3);
  }

  /**
   * 梱包サイズ(長さ）を返します。
   * 
   * @return 梱包サイズ(長さ）
   */
  public Integer getPackageSize1() {
    return container().getPropertyValue(PACKAGE_SIZE_1, Integer.class);
  }

  /**
   * 梱包サイズ(長さ）を設定します。
   * 
   * @param packageSize1 セットする梱包サイズ(長さ）
   */
  public void setPackageSize1(Integer packageSize1) {
    container().setPropertyValue(PACKAGE_SIZE_1, packageSize1);
  }

  /**
   * 梱包サイズ(幅）を返します。
   * 
   * @return 梱包サイズ(幅）
   */
  public Integer getPackageSize2() {
    return container().getPropertyValue(PACKAGE_SIZE_2, Integer.class);
  }

  /**
   * 梱包サイズ(幅）を設定します。
   * 
   * @param packageSize2 セットする梱包サイズ(幅）
   */
  public void setPackageSize2(Integer packageSize2) {
    container().setPropertyValue(PACKAGE_SIZE_2, packageSize2);
  }

  /**
   * 梱包サイズ(高さ）を返します。
   * 
   * @return 梱包サイズ(高さ）
   */
  public Integer getPackageSize3() {
    return container().getPropertyValue(PACKAGE_SIZE_3, Integer.class);
  }

  /**
   * 梱包サイズ(高さ）を設定します。
   * 
   * @param packageSize3 セットする梱包サイズ(高さ）
   */
  public void setPackageSize3(Integer packageSize3) {
    container().setPropertyValue(PACKAGE_SIZE_3, packageSize3);
  }

  /**
   * サイズ単位を返します。
   * 
   * @return サイズ単位
   */
  public String getSizeUnit() {
    return container().getPropertyValue(SIZE_UNIT, String.class);
  }

  /**
   * サイズ単位を設定します。
   * 
   * @param sizeUnit セットするサイズ単位
   */
  public void setSizeUnit(String sizeUnit) {
    container().setPropertyValue(SIZE_UNIT, sizeUnit);
  }

  /**
   * 商品重量を返します。
   * 
   * @return 商品重量
   */
  public Integer getGoodsWeight() {
    return container().getPropertyValue(GOODS_WEIGHT, Integer.class);
  }

  /**
   * 商品重量を設定します。
   * 
   * @param goodsWeight セットする商品重量
   */
  public void setGoodsWeight(Integer goodsWeight) {
    container().setPropertyValue(GOODS_WEIGHT, goodsWeight);
  }

  /**
   * 重量単位を返します。
   * 
   * @return 重量単位
   */
  public String getWeightUnit() {
    return container().getPropertyValue(WEIGHT_UNIT, String.class);
  }

  /**
   * 重量単位を設定します。
   * 
   * @param weightUnit セットする重量単位
   */
  public void setWeightUnit(String weightUnit) {
    container().setPropertyValue(WEIGHT_UNIT, weightUnit);
  }

  /**
   * URL1を返します。
   * 
   * @return URL1
   */
  public String getUrl1() {
    return container().getPropertyValue(URL_1, String.class);
  }

  /**
   * URL1を設定します。
   * 
   * @param url1 セットするURL1
   */
  public void setUrl1(String url1) {
    container().setPropertyValue(URL_1, url1);
  }

  /**
   * URL2を返します。
   * 
   * @return URL2
   */
  public String getUrl2() {
    return container().getPropertyValue(URL_2, String.class);
  }

  /**
   * URL2を設定します。
   * 
   * @param url2 セットするURL2
   */
  public void setUrl2(String url2) {
    container().setPropertyValue(URL_2, url2);
  }

  /**
   * URL3を返します。
   * 
   * @return URL3
   */
  public String getUrl3() {
    return container().getPropertyValue(URL_3, String.class);
  }

  /**
   * URL3を設定します。
   * 
   * @param url3 セットするURL3
   */
  public void setUrl3(String url3) {
    container().setPropertyValue(URL_3, url3);
  }

  /**
   * 画像数を返します。
   * 
   * @return 画像数
   */
  public short getImageNo() {
    return container().getPropertyValue(IMAGE_NO, Short.class);
  }

  /**
   * 画像数を設定します。
   * 
   * @param imageNo セットする画像数
   */
  public void setImageNo(short imageNo) {
    container().setPropertyValue(IMAGE_NO, imageNo);
  }

  /**
   * 適用日時を返します。
   * 
   * @return 適用日時
   */
  public Timestamp getStartTime() {
    return container().getPropertyValue(START_TIME, Timestamp.class);
  }

  /**
   * 適用日時を設定します。
   * 
   * @param startTime セットする適用日時
   */
  public void setStartTime(Timestamp startTime) {
    container().setPropertyValue(START_TIME, startTime);
  }

  /**
   * チェック区分を返します。
   * 
   * @return チェック区分
   */
  public short getCheckFlg() {
    return container().getPropertyValue(CHECK_FLG, Short.class);
  }

  /**
   * チェック区分を設定します。
   * 
   * @param checkFlg セットするチェック区分
   */
  public void setCheckFlg(short checkFlg) {
    container().setPropertyValue(CHECK_FLG, checkFlg);
  }

  /**
   * データステータスを返します。
   * 
   * @return データステータス
   */
  public short getErrorFlg() {
    return container().getPropertyValue(ERROR_FLG, Short.class);
  }

  /**
   * データステータスを設定します。
   * 
   * @param errorFlg セットするデータステータス
   */
  public void setErrorFlg(short errorFlg) {
    container().setPropertyValue(ERROR_FLG, errorFlg);
  }

  /**
   * BL登録区分を返します。
   * 
   * @return BL登録区分
   */
  public short getBlEntryFlg() {
    return container().getPropertyValue(BL_ENTRY_FLG, Short.class);
  }

  /**
   * BL登録区分を設定します。
   * 
   * @param blEntryFlg セットするBL登録区分
   */
  public void setBlEntryFlg(short blEntryFlg) {
    container().setPropertyValue(BL_ENTRY_FLG, blEntryFlg);
  }

  /**
   * インポート区分を返します。
   * 
   * @return インポート区分
   */
  public short getImportKbn() {
    return container().getPropertyValue(IMPORT_KBN, Short.class);
  }

  /**
   * インポート区分を設定します。
   * 
   * @param importKbn セットするインポート区分
   */
  public void setImportKbn(short importKbn) {
    container().setPropertyValue(IMPORT_KBN, importKbn);
  }

  /**
   * 処理区分を返します。
   * 
   * @return 処理区分
   */
  public short getManageKbn() {
    return container().getPropertyValue(MANAGE_KBN, Short.class);
  }

  /**
   * 処理区分を設定します。
   * 
   * @param manageKbn セットする処理区分
   */
  public void setManageKbn(short manageKbn) {
    container().setPropertyValue(MANAGE_KBN, manageKbn);
  }

  /**
   * エラー内容を返します。
   * 
   * @return エラー内容
   */
  public String getErrorDetail() {
    return container().getPropertyValue(ERROR_DETAIL, String.class);
  }

  /**
   * エラー内容を設定します。
   * 
   * @param errorDetail セットするエラー内容
   */
  public void setErrorDetail(String errorDetail) {
    container().setPropertyValue(ERROR_DETAIL, errorDetail);
  }

  /**
   * 削除依頼区分を返します。
   * 
   * @return 削除依頼区分
   */
  public Short getDeleteFlg() {
    return container().getPropertyValue(DELETE_FLG, Short.class);
  }

  /**
   * 削除依頼区分を設定します。
   * 
   * @param deleteFlg セットする削除依頼区分
   */
  public void setDeleteFlg(Short deleteFlg) {
    container().setPropertyValue(DELETE_FLG, deleteFlg);
  }

  /**
   * 削除理由を返します。
   * 
   * @return 削除理由
   */
  public String getDeleteReason() {
    return container().getPropertyValue(DELETE_REASON, String.class);
  }

  /**
   * 削除理由を設定します。
   * 
   * @param deleteReason セットする削除理由
   */
  public void setDeleteReason(String deleteReason) {
    container().setPropertyValue(DELETE_REASON, deleteReason);
  }

  /**
   * 申請状態を返します。
   * 
   * @return 申請状態
   */
  public Short getApplyCondition() {
    return container().getPropertyValue(APPLY_CONDITION, Short.class);
  }

  /**
   * 申請状態を設定します。
   * 
   * @param applyCondition セットする申請状態
   */
  public void setApplyCondition(Short applyCondition) {
    container().setPropertyValue(APPLY_CONDITION, applyCondition);
  }

  /**
   * エンティティIDを取得する。
   * 
   * @return エンティティID
   */
  @Override
  public GoodsMasterMakerId identifier() {
    return new GoodsMasterMakerId(getPrmSetDtlNo1(), getPartsMakerCd(), getPrimePartsNoWithH());
  }

  /**
   * プロパティ定義の配列を取得する。
   * 
   * @return プロパティ定義の配列
   */
  @Override
  public PropertyDefinition[] defineProperties() {
    return ArrayUtils.addAll(super.defineProperties(), definitions);
  }

  /**
   * プロパティ定義の配列
   */
  private static PropertyDefinition[] definitions;

  static {
    List<PropertyDefinition> list = new ArrayList<PropertyDefinition>();
    list.add(new PropertyDefinition(PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(GOODS_M_GROUP, Integer.class, true));
    list.add(new PropertyDefinition(TBS_PARTS_CODE, Integer.class, true));
    list.add(new PropertyDefinition(PRIME_PARTS_NO_WITH_H, String.class, false));
    list.add(new PropertyDefinition(PRIME_PARTS_KANA_NM, String.class, true));
    list.add(new PropertyDefinition(PRIME_PARTS_NAME, String.class, true));
    list.add(new PropertyDefinition(NEW_PRICE, Double.class, true));
    list.add(new PropertyDefinition(OPEN_PRICE_DIV, Integer.class, true));
    list.add(new PropertyDefinition(JAN, Long.class, true));
    list.add(new PropertyDefinition(PARTS_LAYER_CD, String.class, true));
    list.add(new PropertyDefinition(EQUIP_NAME, String.class, true));
    list.add(new PropertyDefinition(PRIME_PARTS_SPECIAL_NOTE_B, String.class, true));
    list.add(new PropertyDefinition(PRIME_PARTS_SPECIAL_NOTE_C, String.class, true));
    list.add(new PropertyDefinition(GOODS_DETAIL_B, String.class, true));
    list.add(new PropertyDefinition(GOODS_DETAIL_C, String.class, true));
    list.add(new PropertyDefinition(GOODS_SIZE_1, Integer.class, true));
    list.add(new PropertyDefinition(GOODS_SIZE_2, Integer.class, true));
    list.add(new PropertyDefinition(GOODS_SIZE_3, Integer.class, true));
    list.add(new PropertyDefinition(PACKAGE_SIZE_1, Integer.class, true));
    list.add(new PropertyDefinition(PACKAGE_SIZE_2, Integer.class, true));
    list.add(new PropertyDefinition(PACKAGE_SIZE_3, Integer.class, true));
    list.add(new PropertyDefinition(SIZE_UNIT, String.class, true));
    list.add(new PropertyDefinition(GOODS_WEIGHT, Integer.class, true));
    list.add(new PropertyDefinition(WEIGHT_UNIT, String.class, true));
    list.add(new PropertyDefinition(URL_1, String.class, true));
    list.add(new PropertyDefinition(URL_2, String.class, true));
    list.add(new PropertyDefinition(URL_3, String.class, true));
    list.add(new PropertyDefinition(IMAGE_NO, Short.class, false));
    list.add(new PropertyDefinition(START_TIME, Timestamp.class, false));
    list.add(new PropertyDefinition(CHECK_FLG, Short.class, false));
    list.add(new PropertyDefinition(ERROR_FLG, Short.class, false));
    list.add(new PropertyDefinition(BL_ENTRY_FLG, Short.class, false));
    list.add(new PropertyDefinition(IMPORT_KBN, Short.class, false));
    list.add(new PropertyDefinition(MANAGE_KBN, Short.class, false));
    list.add(new PropertyDefinition(ERROR_DETAIL, String.class, true));
    list.add(new PropertyDefinition(DELETE_FLG, Short.class, true));
    list.add(new PropertyDefinition(DELETE_REASON, String.class, true));
    list.add(new PropertyDefinition(APPLY_CONDITION, Short.class, true));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
