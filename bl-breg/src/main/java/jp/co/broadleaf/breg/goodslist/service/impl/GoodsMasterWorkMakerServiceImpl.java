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
package jp.co.broadleaf.breg.goodslist.service.impl;

import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterWorkMakerId;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterSearchModel;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterWorkMakerModel;
import jp.co.broadleaf.breg.goodslist.service.GoodsMasterWorkMakerService;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.DateFilter;
import jp.co.broadleaf.framework.dao.criteria.DateFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.FieldAscOrder;
import jp.co.broadleaf.framework.dao.criteria.FieldOrder;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.MultiFieldOrder;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.Order;
import jp.co.broadleaf.framework.dao.criteria.StringFilter;
import jp.co.broadleaf.framework.dao.criteria.StringFilterBuilder;

import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 商品一覧サービスクラス.
 * </pre>
 */
public class GoodsMasterWorkMakerServiceImpl implements GoodsMasterWorkMakerService {

  /** 商品DAO */
  private GenericDao<GoodsMasterWorkMaker, GoodsMasterWorkMakerId> goodsMasterDao;

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param goodsMasterDaoNew 商品DAO
   */
  @Resource
  public void setGoodsMasterWorkMakerDao(GenericDao<GoodsMasterWorkMaker, GoodsMasterWorkMakerId> goodsMasterDaoNew) {
    this.goodsMasterDao = goodsMasterDaoNew;
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)登録.
   * </pre>
   * 
   * @param goodsModel 登録する項目（優良設定詳細コード１ など）の連係情報
   * @return 登録した商品マスタ(メーカー)情報の件数
   */
  @Override
  public int insertGoodsInfoList(GoodsMasterWorkMakerModel goodsModel) {
    if (goodsModel.getGoodsList() == null || goodsModel.getGoodsList().isEmpty()) {
      return 0;
    }
    // 登録
    goodsMasterDao.batchInsert(goodsModel.getGoodsList());
    // 登録した件数を返却する
    return goodsModel.getGoodsList().size();
  }

  /**
   * 商品マスタ(メーカー)更新処理.
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   */
  @Override
  public void updateGoodsInfo(GoodsMasterWorkMaker goodsMaster) {
    GoodsMasterWorkMakerId goodsMasterId = new GoodsMasterWorkMakerId();
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    goodsMasterId.setPartsMakerCd(goodsMaster.getPartsMakerCd());
    // 優良品番(－付き品番) = 引数.優良品番(－付き品番)
    goodsMasterId.setPrimePartsNoWithH(goodsMaster.getPrimePartsNoWithH());
    // 部品メーカーコード = 引数.部品メーカーコード
    goodsMasterId.setPrmSetDtlNo1(goodsMaster.getPrmSetDtlNo1());
    // 検索結果を取得
    GoodsMasterWorkMaker goodsMasterResult = goodsMasterDao.findById(goodsMasterId);
    // 更新する項目
    if (goodsMasterResult != null) {
      // チェック区分
      goodsMasterResult.setCheckFlg(goodsMaster.getCheckFlg());
      // 削除依頼区分
      goodsMasterResult.setDeleteFlg(goodsMaster.getDeleteFlg());
      // 削除理由
      goodsMasterResult.setDeleteReason(goodsMaster.getDeleteReason());
      // 装備名称
      goodsMasterResult.setEquipName(goodsMaster.getEquipName());
      // エラー内容
      goodsMasterResult.setErrorDetail(goodsMaster.getErrorDetail());
      // データステータス
      goodsMasterResult.setErrorFlg(goodsMaster.getErrorFlg());
      // 商品詳細(B向け)
      goodsMasterResult.setGoodsDetailB(goodsMaster.getGoodsDetailB());
      // 商品詳細(C向け)
      goodsMasterResult.setGoodsDetailC(goodsMaster.getGoodsDetailC());
      // 商品中分類コード
      goodsMasterResult.setGoodsMGroup(goodsMaster.getGoodsMGroup());
      // 商品サイズ(長さ）
      goodsMasterResult.setGoodsSize1(goodsMaster.getGoodsSize1());
      // 商品サイズ(幅）
      goodsMasterResult.setGoodsSize2(goodsMaster.getGoodsSize2());
      // 商品サイズ(高さ）
      goodsMasterResult.setGoodsSize3(goodsMaster.getGoodsSize3());
      // 商品重量
      goodsMasterResult.setGoodsWeight(goodsMaster.getGoodsWeight());
      // 画像数
      goodsMasterResult.setImageNo(goodsMaster.getImageNo());
      // JAN
      goodsMasterResult.setJan(goodsMaster.getJan());
      // 新価格
      goodsMasterResult.setNewPrice(goodsMaster.getNewPrice());
      // オープン価格区分
      goodsMasterResult.setOpenPriceDiv(goodsMaster.getOpenPriceDiv());
      // 梱包サイズ(長さ）
      goodsMasterResult.setPackageSize1(goodsMaster.getPackageSize1());
      // 梱包サイズ(幅）
      goodsMasterResult.setPackageSize2(goodsMaster.getPackageSize2());
      // 梱包サイズ(高さ）
      goodsMasterResult.setPackageSize3(goodsMaster.getPackageSize3());
      // 層別コード
      goodsMasterResult.setPartsLayerCd(goodsMaster.getPartsLayerCd());
      // 優良部品カナ名称
      goodsMasterResult.setPrimePartsKanaNm(goodsMaster.getPrimePartsKanaNm());
      // 優良部品名称
      goodsMasterResult.setPrimePartsName(goodsMaster.getPrimePartsName());
      // 優良部品規格・特記事項(C向け)
      goodsMasterResult.setPrimePartsSpecialNoteC(goodsMaster.getPrimePartsSpecialNoteC());
      // 優良部品規格・特記事項(B向け)
      goodsMasterResult.setPrimePartsSpecialNoteB(goodsMaster.getPrimePartsSpecialNoteB());
      // サイズ単位
      goodsMasterResult.setSizeUnit(goodsMaster.getSizeUnit());
      // 適用日付
      goodsMasterResult.setStartTime(goodsMaster.getStartTime());
      // URL1
      goodsMasterResult.setUrl1(goodsMaster.getUrl1());
      // URL2
      goodsMasterResult.setUrl2(goodsMaster.getUrl2());
      // URL3
      goodsMasterResult.setUrl3(goodsMaster.getUrl3());
      // 重量単位
      goodsMasterResult.setWeightUnit(goodsMaster.getWeightUnit());
      // BLコード
      goodsMasterResult.setTbsPartsCode(goodsMaster.getTbsPartsCode());
      // 商品マスタ(メーカー)情報更新
      goodsMasterDao.update(goodsMasterResult);
    }
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param goodsMasterSearchModel 商品マスタ(メーカー)
   * @param order 順番の依頼
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param importKbn インポート区分
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public GoodsMasterWorkMakerModel searchGoodsInfoList(GoodsMasterSearchModel goodsMasterSearchModel, int order,
                                                       Long skipRows, Long maxRows, Boolean isPage, Short importKbn) {
    GoodsMasterWorkMakerModel goodsMasterModel = new GoodsMasterWorkMakerModel();
    Filter filter = getFilter(goodsMasterSearchModel, importKbn);
    Order orderby;
    // BLコード
    FieldOrder blCodeOrder = new FieldAscOrder(GoodsMasterWorkMaker.TBS_PARTS_CODE);
    // 優良品番(－付き品番)
    FieldOrder primePartsNoWithHOrder = new FieldAscOrder(GoodsMasterWorkMaker.PRIME_PARTS_NO_WITH_H);
    // 優良設定詳細コード１
    FieldOrder prmSetDtlNo1Order = new FieldAscOrder(GoodsMasterWorkMaker.PRM_SET_DTL_NO_1);
    // 商品中分類コード
    FieldOrder goodsMGroupOrder = new FieldAscOrder(GoodsMasterWorkMaker.GOODS_M_GROUP);
    // 層別コード
    FieldOrder partsLayerCdOrder = new FieldAscOrder(GoodsMasterWorkMaker.PARTS_LAYER_CD);
    // 画面の優良品番はテープルにありません
    if (order == 1) {
      orderby = new MultiFieldOrder(prmSetDtlNo1Order, goodsMGroupOrder, blCodeOrder, primePartsNoWithHOrder);
    } else {
      orderby = new MultiFieldOrder(prmSetDtlNo1Order, goodsMGroupOrder, blCodeOrder, partsLayerCdOrder,
          primePartsNoWithHOrder);
    }
    Limit limit = Limit.NO_LIMIT;
    if (isPage && skipRows != null) {
      limit = new Limit(skipRows, maxRows);
    }
    long searchCounts = goodsMasterDao.countByFilter(filter);
    goodsMasterModel.setSearchCounts(searchCounts);
    List<GoodsMasterWorkMaker> goodsMasterList = goodsMasterDao.findByFilter(filter, limit, orderby);
    goodsMasterModel.setGoodsMasterWorkMakerList(goodsMasterList);
    return goodsMasterModel;
  }

  /**
   * <pre>
   * Filter取得.
   * </pre>
   * 
   * @param goodsMasterSearchModel 商品マスタ(メーカー)
   * @param importKbn インポート区分
   * @return Filter
   */
  private Filter getFilter(GoodsMasterSearchModel goodsMasterSearchModel, Short importKbn) {
    Filter filter = null;
    // BLコード = 引数.BLコード
    NumberFilter blCode = NumberFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.TBS_PARTS_CODE,
        goodsMasterSearchModel.getTbsPartsCode());
    // 優良品番(－付き品番) = 引数.優良品番(－付き品番)
    StringFilter primePartsNoWithH = StringFilterBuilder.containsIfNotEmpty(GoodsMasterWorkMaker.PRIME_PARTS_NO_WITH_H,
        goodsMasterSearchModel.getPrimePartsNoWithH());
    // 優良部品カナ名称= 引数.優良部品カナ名称
    StringFilter primePartsKanaNm = StringFilterBuilder.containsIfNotEmpty(GoodsMasterWorkMaker.PRIME_PARTS_KANA_NM,
        goodsMasterSearchModel.getPrimePartsKanaNm());
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.PRM_SET_DTL_NO_1,
        goodsMasterSearchModel.getPrmSetDtlNo1());
    // 部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.PARTS_MAKER_CD,
        goodsMasterSearchModel.getPartsMakerCd());
    // 申請状態= 引数.申請状態
    NumberFilter applyCondition = NumberFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.APPLY_CONDITION,
        goodsMasterSearchModel.getApplyCondition());
    // 削除依頼区分= 引数.削除依頼区分
    NumberFilter deleteFlg = NumberFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.DELETE_FLG,
        goodsMasterSearchModel.getDeleteFlg());
    // 装備名称= 引数.装備名称
    StringFilter equipName = StringFilterBuilder.containsIfNotEmpty(GoodsMasterWorkMaker.EQUIP_NAME,
        goodsMasterSearchModel.getEquipName());
    // データステータス= 引数.データステータス
    NumberFilter errorFlg = NumberFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.ERROR_FLG,
        goodsMasterSearchModel.getErrorFlg());
    // 商品詳細(B向け)= 引数.商品詳細(B向け)
    StringFilter goodsDetailB = StringFilterBuilder.containsIfNotEmpty(GoodsMasterWorkMaker.GOODS_DETAIL_B,
        goodsMasterSearchModel.getGoodsDetailB());
    // 商品詳細(C向け)= 引数.商品詳細(C向け)
    StringFilter goodsDetailC = StringFilterBuilder.containsIfNotEmpty(GoodsMasterWorkMaker.GOODS_DETAIL_C,
        goodsMasterSearchModel.getGoodsDetailC());
    // 商品中分類コード= 引数.商品中分類コード
    NumberFilter goodsMGroup = NumberFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.GOODS_M_GROUP,
        goodsMasterSearchModel.getGoodsMGroup());
    // 処理区分= 引数. 処理区分
    NumberFilter manageKbn = NumberFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.MANAGE_KBN,
        goodsMasterSearchModel.getManageKbn());
    // オープン価格区分= 引数.オープン価格区分
    NumberFilter openPriceDiv = NumberFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.OPEN_PRICE_DIV,
        goodsMasterSearchModel.getOpenPriceDiv());

    // 層別コード= 引数.層別コード
    StringFilter partsLayerCd = StringFilterBuilder.equalsIfNotEmpty(GoodsMasterWorkMaker.PARTS_LAYER_CD,
        goodsMasterSearchModel.getPartsLayerCd());
    // 優良部品規格・特記事項(C向け)= 引数.優良部品規格・特記事項(C向け)
    StringFilter primePartsSpecialNoteC = StringFilterBuilder.containsIfNotEmpty(
        GoodsMasterWorkMaker.PRIME_PARTS_SPECIAL_NOTE_C, goodsMasterSearchModel.getPrimePartsSpecialNoteC());
    // 優良部品規格・特記事項(B向け)= 優良部品規格・特記事項(B向け)
    StringFilter primePartsSpecialNoteB = StringFilterBuilder.containsIfNotEmpty(
        GoodsMasterWorkMaker.PRIME_PARTS_SPECIAL_NOTE_B, goodsMasterSearchModel.getPrimePartsSpecialNoteB());
    // 作成日時 <=引数. 作成日時
    DateFilter insDtTimeEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(GoodsMasterWorkMaker.INS_DT_TIME,
        goodsMasterSearchModel.getInsDtTimeEnd());
    // 作成日時>=引数. 作成日時
    DateFilter insDtTimeStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(GoodsMasterWorkMaker.INS_DT_TIME,
        goodsMasterSearchModel.getInsDtTimeStart());
    // 更新日時 <=引数. 作成日時
    DateFilter updDtTimeEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(GoodsMasterWorkMaker.UPD_DT_TIME,
        goodsMasterSearchModel.getUpdDtTimeEnd());
    // 更新日時>=引数. 更新日時
    DateFilter updDtTimeStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(GoodsMasterWorkMaker.UPD_DT_TIME,
        goodsMasterSearchModel.getUpdDtTimeStart());

    // 適用日付<= 引数.適用日付
    DateFilter startDateEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(GoodsMasterWorkMaker.START_TIME,
        goodsMasterSearchModel.getStartDateEnd());
    // 適用日付>= 引数.適用日付
    DateFilter startDateStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(GoodsMasterWorkMaker.START_TIME,
        goodsMasterSearchModel.getStartDateStart());
    // 論理削除区分
    StringFilter logicalDelDiv = StringFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.LOGICAL_DEL_DIV, "0");
    // インポート区分
    NumberFilter importdiv = NumberFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.IMPORT_KBN, importKbn);

    filter = new AndFilter(blCode, primePartsNoWithH, primePartsKanaNm, prmSetDtlNo1, applyCondition, deleteFlg,
        equipName, errorFlg, goodsDetailB, goodsDetailC, goodsMGroup, manageKbn, openPriceDiv, partsLayerCd,
        primePartsSpecialNoteC, primePartsSpecialNoteB, insDtTimeEnd, insDtTimeStart, updDtTimeEnd, updDtTimeStart,
        startDateEnd, startDateStart, logicalDelDiv, partsMakerCd, importdiv);
    return filter;
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param goodsMasterId 商品マスタ(メーカー)ID
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public GoodsMasterWorkMaker searchGoodsById(GoodsMasterWorkMakerId goodsMasterId) {
    return goodsMasterDao.findById(goodsMasterId);
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param importKbn インポート区分
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public GoodsMasterWorkMakerModel searchGoodsInfoAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd,
                                                      Short importKbn) {
    GoodsMasterWorkMakerModel goodsMasterModel = new GoodsMasterWorkMakerModel();
    // 部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.PARTS_MAKER_CD, makerCd);
    // インポート区分
    NumberFilter importdiv = NumberFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.IMPORT_KBN, importKbn);
    // 申請状態
    NumberFilter applyCondition = NumberFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.APPLY_CONDITION,
        ApplyConditionEnum.NoApply.getValue());
    // 論理削除区分
    StringFilter logicalDelDiv = StringFilterBuilder.equalsIfNotNull(GoodsMasterWorkMaker.LOGICAL_DEL_DIV, "0");
    Filter filter = new AndFilter(partsMakerCd, logicalDelDiv, importdiv, applyCondition);
    Limit limit = Limit.NO_LIMIT;
    if (isPage) {
      limit = new Limit(skipRows, maxRows);
    }
    // BLコード
    FieldOrder blCodeOrder = new FieldAscOrder(GoodsMasterMaker.TBS_PARTS_CODE);
    // 優良品番(－付き品番)
    FieldOrder primePartsNoWithHOrder = new FieldAscOrder(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H);
    // 優良設定詳細コード１
    FieldOrder prmSetDtlNo1Order = new FieldAscOrder(GoodsMasterMaker.PRM_SET_DTL_NO_1);
    // 商品中分類コード
    FieldOrder goodsMGroupOrder = new FieldAscOrder(GoodsMasterMaker.GOODS_M_GROUP);
    // 画面の優良品番はテープルにありません
    Order orderby = new MultiFieldOrder(prmSetDtlNo1Order, goodsMGroupOrder, blCodeOrder, primePartsNoWithHOrder);
    goodsMasterModel.setGoodsMasterWorkMakerList(goodsMasterDao.findByFilter(filter, limit, orderby));
    return goodsMasterModel;
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)削除.
   * </pre>
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   */
  @Override
  public void logicaldeleteGoodsInfo(GoodsMasterWorkMaker goodsMaster) {
    GoodsMasterWorkMakerId goodsMasterId = new GoodsMasterWorkMakerId();
    // 部品メーカーコード = 引数.部品メーカーコード
    goodsMasterId.setPartsMakerCd(goodsMaster.getPartsMakerCd());
    // 優良品番(－付き品番) = 引数.優良品番(－付き品番)
    goodsMasterId.setPrimePartsNoWithH(goodsMaster.getPrimePartsNoWithH());
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    goodsMasterId.setPrmSetDtlNo1(goodsMaster.getPrmSetDtlNo1());
    // 検索結果を取得
    GoodsMasterWorkMaker goodsMasterResult = goodsMasterDao.findById(goodsMasterId);
    goodsMasterDao.logicalDelete(goodsMasterResult);
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)削除.
   * </pre>
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   */
  @Override
  public void deleteGoodsInfo(GoodsMasterWorkMaker goodsMaster) {
    GoodsMasterWorkMakerId goodsMasterId = new GoodsMasterWorkMakerId();
    // 部品メーカーコード = 引数.部品メーカーコード
    goodsMasterId.setPartsMakerCd(goodsMaster.getPartsMakerCd());
    // 優良品番(－付き品番) = 引数.優良品番(－付き品番)
    goodsMasterId.setPrimePartsNoWithH(goodsMaster.getPrimePartsNoWithH());
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    goodsMasterId.setPrmSetDtlNo1(goodsMaster.getPrmSetDtlNo1());
    // 検索結果を取得
    GoodsMasterWorkMaker goodsMasterResult = goodsMasterDao.findById(goodsMasterId);
    if (goodsMasterResult != null) {
      goodsMasterDao.delete(goodsMasterResult);
    }
  }
}
