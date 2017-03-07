//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2016 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : wangnan
// 作 成 日       2016/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.productlist.form;

import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsGridDto;

import java.util.List;

/**
 * ログインFormのクラスのクラス。
 */
public class ProductListForm {

  /**
   * BLコード
   */
  private String blCd;
  /**
   * 優良品番
   */
  private String goodsCd;
  /**
   * 品名（半角）
   */
  private String goodsName;
  /**
   * 申請状態
   */
  private String applyState;
  /**
   * 処理区分
   */
  private String processdiv;
  /**
   * エラー区分
   */
  private String errorDiv;
  /**
   * セレクトコード
   */
  private String selectCd;
  /**
   * 層別
   */
  private String oldCd;
  /**
   * 適用日start
   */
  private String applyDateStart;
  /**
   * 適用日END
   */
  private String applyDateEnd;
  /**
   * 分類コード
   */
  private String classifyCd;
  /**
   * OPENプランス
   */
  private String priceClass;
  /**
   * 作成日START
   */
  private String insertDateStart;
  /**
   * 作成日END
   */
  private String insertDateEnd;
  /**
   * 装備
   */
  private String equipment;
  /**
   * 規格/特記
   */
  private String primePartsSpecialNoteRFB;
  /**
   * 更新日START
   */
  private String updateDateStart;
  /**
   * 更新日END
   */
  private String updateDateEnd;
  /**
   * 削除依頼区分
   */
  private String deleteDiv;
  /**
   * 規格/特記(一般)
   */
  private String primePartsSpecialNoteRFC;
  /**
   * 商品詳細
   */
  private String goodDetail;
  /**
   * 商品詳細(一般)
   */
  private String goodDetailCommon;
  /**
   * 商品マスタ(メーカー)のgridDto
   */
  private List<GoodsGridDto> goodsGridDtoList;
  /**
   * 表示順
   */
  private int order;
  /**
   * mode
   */
  private Integer mode;
  /**
   * pageNo
   */
  public String pageNo;
  /**
   * 商品中分類コード
   */
  public String goodsMGroup;
  /**
   * インポート区分
   */
  public short importKbn;

  /**
   * <pre>
   * 【インポート区分】を取得する。
   * </pre>
   *
   * @return 【インポート区分】
   */
  public short getImportKbn() {
    return importKbn;
  }

  /**
   * <pre>
   * 【インポート区分】を設定する。
   * </pre>
   *
   * @param importKbn 【インポート区分】
   */
  public void setImportKbn(short importKbn) {
    this.importKbn = importKbn;
  }

  /**
   * <pre>
   * 【goodsMGroup】を取得する。
   * </pre>
   *
   * @return 【goodsMGroup】
   */
  public String getGoodsMGroup() {
    return goodsMGroup;
  }

  /**
   * <pre>
   * 【goodsMGroup】を設定する。
   * </pre>
   *
   * @param goodsMGroup 【goodsMGroup】
   */
  public void setGoodsMGroup(String goodsMGroup) {
    this.goodsMGroup = goodsMGroup;
  }

  /**
   * <pre>
  * 【pageNo】を取得する。
   * </pre>
   *
   * @return 【pageNo】
   */
  public String getPageNo() {
    return pageNo;
  }

  /**
   * <pre>
  * 【pageNo】を設定する。
   * </pre>
   *
   * @param pageNo 【pageNo】
   */
  public void setPageNo(String pageNo) {
    this.pageNo = pageNo;
  }

  /**
   * modeの取得。
   *
   * @return Integer mode
   */
  public Integer getMode() {
    return mode;
  }

  /**
   * modeの設定。
   *
   * @param mode mode
   */
  public void setMode(Integer mode) {
    this.mode = mode;
  }

  /**
   * 表示順の取得。
   *
   * @return int 表示順
   */
  public int getOrder() {
    return order;
  }

  /**
   * 表示順の設定。
   *
   * @param order 表示順
   */
  public void setOrder(int order) {
    this.order = order;
  }

  /**
   * gridDtoの取得。
   *
   * @return List gridDto
   */
  public List<GoodsGridDto> getGoodsGridDtoList() {
    return goodsGridDtoList;
  }

  /**
   * gridDtoの設定。
   *
   * @param goodsGridDtoList gridDto
   */
  public void setGoodsGridDtoList(List<GoodsGridDto> goodsGridDtoList) {
    this.goodsGridDtoList = goodsGridDtoList;
  }

  /**
   * 優良品番ドの取得。
   *
   * @return String 優良品番
   */
  public String getGoodsCd() {
    return goodsCd;
  }

  /**
   * 優良品番の設定。
   *
   * @param goodsCd メーカーコード
   */
  public void setGoodsCd(String goodsCd) {
    this.goodsCd = goodsCd;
  }

  /**
   * BLコードの取得。
   *
   * @return String BLコード
   */
  public String getBlCd() {
    return blCd;
  }

  /**
   * BLコードの設定。
   *
   * @param blCd BLコード
   */
  public void setBlCd(String blCd) {
    this.blCd = blCd;
  }

  /**
   * 品名の取得。
   *
   * @return String 品名
   */
  public String getGoodsName() {
    return goodsName;
  }

  /**
   * 品名の設定。
   *
   * @param goodsName 品名
   */
  public void setGoodsName(String goodsName) {
    this.goodsName = goodsName;
  }

  /**
   * 申請状態の取得。
   *
   * @return String 申請状態
   */
  public String getApplyState() {
    return applyState;
  }

  /**
   * 申請状態の設定。
   *
   * @param applyState 申請状態
   */
  public void setApplyState(String applyState) {
    this.applyState = applyState;
  }

  /**
   * 処理区分の取得。
   *
   * @return String 処理区分
   */
  public String getProcessdiv() {
    return processdiv;
  }

  /**
   * 処理区分の設定。
   *
   * @param processdiv 処理区分
   */
  public void setProcessdiv(String processdiv) {
    this.processdiv = processdiv;
  }

  /**
   * エラー区分の取得。
   *
   * @return String エラー区分
   */
  public String getErrorDiv() {
    return errorDiv;
  }

  /**
   * エラー区分の設定。
   *
   * @param errorDiv エラー区分
   */
  public void setErrorDiv(String errorDiv) {
    this.errorDiv = errorDiv;
  }

  /**
   * セレクトコードの取得。
   *
   * @return String セレクトコード
   */
  public String getSelectCd() {
    return selectCd;
  }

  /**
   * セレクトコードの設定。
   *
   * @param selectCd セレクトコード
   */
  public void setSelectCd(String selectCd) {
    this.selectCd = selectCd;
  }

  /**
   * 層別
   *
   * @return String 層別
   */
  public String getOldCd() {
    return oldCd;
  }

  /**
   * 層別の設定。
   *
   * @param oldCd 層別
   */
  public void setOldCd(String oldCd) {
    this.oldCd = oldCd;
  }

  /**
   * 適用日startの取得。
   *
   * @return String 適用日start
   */
  public String getApplyDateStart() {
    return applyDateStart;
  }

  /**
   * 適用日startの設定。
   *
   * @param applyDateStart 適用日start
   */
  public void setApplyDateStart(String applyDateStart) {
    this.applyDateStart = applyDateStart;
  }

  /**
   * 適用日ENDの取得。
   *
   * @return String 適用日END
   */
  public String getApplyDateEnd() {
    return applyDateEnd;
  }

  /**
   * 適用日ENDの設定。
   *
   * @param applyDateEnd 適用日END
   */
  public void setApplyDateEnd(String applyDateEnd) {
    this.applyDateEnd = applyDateEnd;
  }

  /**
   * 分類コードの取得。
   *
   * @return String 分類コード
   */
  public String getClassifyCd() {
    return classifyCd;
  }

  /**
   * 分類コードの設定。
   *
   * @param classifyCd 分類コード
   */
  public void setClassifyCd(String classifyCd) {
    this.classifyCd = classifyCd;
  }

  /**
   * OPENプランスの取得。
   *
   * @return String OPENプランス
   */
  public String getPriceClass() {
    return priceClass;
  }

  /**
   * OPENプランスの設定。
   *
   * @param priceClass OPENプランス
   */
  public void setPriceClass(String priceClass) {
    this.priceClass = priceClass;
  }

  /**
   * 作成日STARTの取得。
   *
   * @return String 作成日START
   */
  public String getInsertDateStart() {
    return insertDateStart;
  }

  /**
   * 作成日STARTの設定。
   *
   * @param insertDateStart 作成日START
   */
  public void setInsertDateStart(String insertDateStart) {
    this.insertDateStart = insertDateStart;
  }

  /**
   * 作成日ENDの取得。
   *
   * @return String 作成日END
   */
  public String getInsertDateEnd() {
    return insertDateEnd;
  }

  /**
   * 作成日ENDの設定。
   *
   * @param insertDateEnd 作成日END
   */
  public void setInsertDateEnd(String insertDateEnd) {
    this.insertDateEnd = insertDateEnd;
  }

  /**
   * 装備の取得。
   *
   * @return String 装備
   */
  public String getEquipment() {
    return equipment;
  }

  /**
   * 装備の設定。
   *
   * @param equipment 装備
   */
  public void setEquipment(String equipment) {
    this.equipment = equipment;
  }

  /**
   * 規格/特記の取得。
   *
   * @return String 規格/特記
   */
  public String getPrimePartsSpecialNoteRFB() {
    return primePartsSpecialNoteRFB;
  }

  /**
   * 規格/特記の設定。
   *
   * @param primePartsSpecialNoteRFB 規格/特記
   */
  public void setPrimePartsSpecialNoteRFB(String primePartsSpecialNoteRFB) {
    this.primePartsSpecialNoteRFB = primePartsSpecialNoteRFB;
  }

  /**
   * 更新日STARTの取得。
   *
   * @return String 更新日START
   */
  public String getUpdateDateStart() {
    return updateDateStart;
  }

  /**
   * 更新日STARTの設定。
   *
   * @param updateDateStart 更新日START
   */
  public void setUpdateDateStart(String updateDateStart) {
    this.updateDateStart = updateDateStart;
  }

  /**
   * 更新日ENDの取得。
   *
   * @return String 更新日END
   */
  public String getUpdateDateEnd() {
    return updateDateEnd;
  }

  /**
   * 更新日ENDの設定。
   *
   * @param updateDateEnd 更新日END
   */
  public void setUpdateDateEnd(String updateDateEnd) {
    this.updateDateEnd = updateDateEnd;
  }

  /**
   * 削除依頼区分の取得。
   *
   * @return String 削除依頼区分
   */
  public String getDeleteDiv() {
    return deleteDiv;
  }

  /**
   * 削除依頼区分の設定。
   *
   * @param deleteDiv 削除依頼区分
   */
  public void setDeleteDiv(String deleteDiv) {
    this.deleteDiv = deleteDiv;
  }

  /**
   * 規格/特記(一般)の取得。
   *
   * @return String 規格/特記(一般)
   */
  public String getPrimePartsSpecialNoteRFC() {
    return primePartsSpecialNoteRFC;
  }

  /**
   * 規格/特記(一般)の設定。
   *
   * @param primePartsSpecialNoteRFC 規格/特記(一般)
   */
  public void setPrimePartsSpecialNoteRFC(String primePartsSpecialNoteRFC) {
    this.primePartsSpecialNoteRFC = primePartsSpecialNoteRFC;
  }

  /**
   * 商品詳細の取得。
   *
   * @return String 商品詳細
   */
  public String getGoodDetail() {
    return goodDetail;
  }

  /**
   * 商品詳細の設定。
   *
   * @param goodDetail 商品詳細
   */
  public void setGoodDetail(String goodDetail) {
    this.goodDetail = goodDetail;
  }

  /**
   * 商品詳細(一般)の取得。
   *
   * @return String 商品詳細(一般)
   */
  public String getGoodDetailCommon() {
    return goodDetailCommon;
  }

  /**
   * 商品詳細(一般)の設定。
   *
   * @param goodDetailCommon 商品詳細(一般)
   */
  public void setGoodDetailCommon(String goodDetailCommon) {
    this.goodDetailCommon = goodDetailCommon;
  }
}
