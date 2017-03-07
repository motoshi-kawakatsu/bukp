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
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerRealTime;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerRealTimeId;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ModeEnum;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterMakerModel;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterSearchModel;
import jp.co.broadleaf.breg.goodslist.service.GoodsMasterMakerService;
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

import org.apache.commons.lang3.Validate;

import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 商品一覧サービスクラス.
 * </pre>
 */
public class GoodsMasterMakerServiceImpl implements GoodsMasterMakerService {

  /** 1:エラー有り */
  private static final int ERROR_DATA = 1;

  /** 商品DAO */
  private GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterDao;

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param goodsMasterDaoNew 商品DAO
   */
  @Resource
  public void setGoodsMasterMakerDao(GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterDaoNew) {
    this.goodsMasterDao = goodsMasterDaoNew;
  }

  // --------------- add by liangsd ------------------>>>
  /** 商品DAO */
  private GenericDao<GoodsMasterMakerRealTime, GoodsMasterMakerRealTimeId> goodsMasterRealTimeDao;

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param goodsMasterRealTimeDaoNew 商品DAO
   */
  @Resource
  public void setGoodsMasterMakerRealTimeDao(GenericDao<GoodsMasterMakerRealTime, GoodsMasterMakerRealTimeId> goodsMasterRealTimeDaoNew) {
    this.goodsMasterRealTimeDao = goodsMasterRealTimeDaoNew;
  }

  // --------------- add by liangsd ------------------<<<

  /**
   * <pre>
   * 商品マスタ(メーカー)登録.
   * </pre>
   * 
   * @param goodsModel 登録する項目（優良設定詳細コード１ など）の連係情報
   * @return 登録した商品マスタ(メーカー)情報の件数
   */
  @Override
  public int insertGoodsInfoList(GoodsMasterMakerModel goodsModel) {
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
  public void updateGoodsInfo(GoodsMasterMaker goodsMaster) {
    GoodsMasterMakerId goodsMasterId = new GoodsMasterMakerId();
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    goodsMasterId.setPartsMakerCd(goodsMaster.getPartsMakerCd());
    // 優良品番(－付き品番) = 引数.優良品番(－付き品番)
    goodsMasterId.setPrimePartsNoWithH(goodsMaster.getPrimePartsNoWithH());
    // 部品メーカーコード = 引数.部品メーカーコード
    goodsMasterId.setPrmSetDtlNo1(goodsMaster.getPrmSetDtlNo1());
    // 検索結果を取得
    GoodsMasterMaker goodsMasterResult = goodsMasterDao.findById(goodsMasterId);
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
      // 申請状態
      goodsMasterResult.setApplyCondition(goodsMaster.getApplyCondition());

      // 商品マスタ(メーカー)情報更新
      goodsMasterDao.update(goodsMasterResult);
    }
  }

  /**
   * <pre>
   * チェックリスト画面の商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param count 現在位置
   * @param maxRows maxRows
   * @param makerCode makerCode
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public GoodsMasterMakerModel searchErrorGoodsList(Long count, Long maxRows, int makerCode) {
    GoodsMasterMakerModel itemMasterModel = new GoodsMasterMakerModel();
    // データステータス = 「1:エラー有り」
    NumberFilter errorFlg = NumberFilterBuilder.equals(GoodsMasterMaker.ERROR_FLG, ERROR_DATA);
    NumberFilter makerCd = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, makerCode);
    // 削除依頼区分= 0
    NumberFilter deleteFlg = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV, 0);

    Filter filter = new AndFilter(errorFlg, makerCd, deleteFlg);
    Order orderby;

    FieldOrder prmSetDtlNo1 = new FieldAscOrder(GoodsMasterMaker.PRM_SET_DTL_NO_1);
    FieldOrder goodsMGroup = new FieldAscOrder(GoodsMasterMaker.GOODS_M_GROUP);
    FieldOrder blCodeOrder = new FieldAscOrder(GoodsMasterMaker.TBS_PARTS_CODE);
    FieldOrder primePartsNoWithH = new FieldAscOrder(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H);
    // 画面の優良品番はテープルにありません
    orderby = new MultiFieldOrder(prmSetDtlNo1, goodsMGroup, blCodeOrder, primePartsNoWithH);

    long goodsCount = goodsMasterDao.countByFilter(filter);
    Limit limit;
    if (count == null) {
      limit = Limit.NO_LIMIT;
    } else {
      limit = new Limit(count, maxRows);
    }
    List<GoodsMasterMaker> itemMasterList = goodsMasterDao.findByFilter(filter, limit, orderby);
    itemMasterModel.setGoodsList(itemMasterList);
    itemMasterModel.setSearchCounts(goodsCount);
    return itemMasterModel;
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
   * @param mode 画面mode
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public GoodsMasterMakerModel searchGoodsInfoList(GoodsMasterSearchModel goodsMasterSearchModel, int order,
                                                   Long skipRows, Long maxRows, Boolean isPage, int mode) {
    GoodsMasterMakerModel goodsMasterModel = new GoodsMasterMakerModel();
    Filter filter = getFilter(goodsMasterSearchModel, mode);
    Order orderby;
    // BLコード
    FieldOrder blCodeOrder = new FieldAscOrder(GoodsMasterMaker.TBS_PARTS_CODE);
    // 優良品番(－付き品番)
    FieldOrder primePartsNoWithHOrder = new FieldAscOrder(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H);
    // 優良設定詳細コード１
    FieldOrder prmSetDtlNo1Order = new FieldAscOrder(GoodsMasterMaker.PRM_SET_DTL_NO_1);
    // 商品中分類コード
    FieldOrder goodsMGroupOrder = new FieldAscOrder(GoodsMasterMaker.GOODS_M_GROUP);
    // 層別コード
    FieldOrder partsLayerCdOrder = new FieldAscOrder(GoodsMasterMaker.PARTS_LAYER_CD);
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
    long searchCount = goodsMasterDao.countByFilter(filter);
    List<GoodsMasterMaker> goodsMasterList = goodsMasterDao.findByFilter(filter, limit, orderby);
    goodsMasterModel.setGoodsList(goodsMasterList);
    goodsMasterModel.setSearchCounts(searchCount);
    return goodsMasterModel;
  }

  /**
   * <pre>
   * Filter取得.
   * </pre>
   * 
   * @param mode 画面mode
   * @param goodsMasterSearchModel 商品マスタ(メーカー)
   * @return Filter
   */
  private Filter getFilter(GoodsMasterSearchModel goodsMasterSearchModel, int mode) {
    Filter filter = null;
    // BLコード = 引数.BLコード
    NumberFilter blCode = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.TBS_PARTS_CODE,
        goodsMasterSearchModel.getTbsPartsCode());
    // 優良品番(－付き品番) = 引数.優良品番(－付き品番)
    StringFilter primePartsNoWithH = StringFilterBuilder.containsIfNotEmpty(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H,
        goodsMasterSearchModel.getPrimePartsNoWithH());
    // 優良部品カナ名称= 引数.優良部品カナ名称
    StringFilter primePartsKanaNm = StringFilterBuilder.containsIfNotEmpty(GoodsMasterMaker.PRIME_PARTS_KANA_NM,
        goodsMasterSearchModel.getPrimePartsKanaNm());
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.PRM_SET_DTL_NO_1,
        goodsMasterSearchModel.getPrmSetDtlNo1());
    // 部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.PARTS_MAKER_CD,
        goodsMasterSearchModel.getPartsMakerCd());
    // 申請状態= 引数.申請状態
    NumberFilter applyCondition = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.APPLY_CONDITION,
        goodsMasterSearchModel.getApplyCondition());
    // 削除依頼区分= 引数.削除依頼区分
    NumberFilter deleteFlg = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.DELETE_FLG,
        goodsMasterSearchModel.getDeleteFlg());
    // 装備名称= 引数.装備名称
    StringFilter equipName = StringFilterBuilder.containsIfNotEmpty(GoodsMasterMaker.EQUIP_NAME,
        goodsMasterSearchModel.getEquipName());
    // データステータス= 引数.データステータス
    NumberFilter errorFlg = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.ERROR_FLG,
        goodsMasterSearchModel.getErrorFlg());
    // 商品詳細(B向け)= 引数.商品詳細(B向け)
    StringFilter goodsDetailB = StringFilterBuilder.containsIfNotEmpty(GoodsMasterMaker.GOODS_DETAIL_B,
        goodsMasterSearchModel.getGoodsDetailB());
    // 商品詳細(C向け)= 引数.商品詳細(C向け)
    StringFilter goodsDetailC = StringFilterBuilder.containsIfNotEmpty(GoodsMasterMaker.GOODS_DETAIL_C,
        goodsMasterSearchModel.getGoodsDetailC());
    // 商品中分類コード= 引数.商品中分類コード
    NumberFilter goodsMGroup = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.GOODS_M_GROUP,
        goodsMasterSearchModel.getGoodsMGroup());
    // 処理区分= 引数. 処理区分
    NumberFilter manageKbn = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.MANAGE_KBN,
        goodsMasterSearchModel.getManageKbn());
    // オープン価格区分= 引数.オープン価格区分
    NumberFilter openPriceDiv = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.OPEN_PRICE_DIV,
        goodsMasterSearchModel.getOpenPriceDiv());

    // 層別コード= 引数.層別コード
    StringFilter partsLayerCd = StringFilterBuilder.equalsIfNotEmpty(GoodsMasterMaker.PARTS_LAYER_CD,
        goodsMasterSearchModel.getPartsLayerCd());
    // 優良部品規格・特記事項(C向け)= 引数.優良部品規格・特記事項(C向け)
    StringFilter primePartsSpecialNoteC = StringFilterBuilder.containsIfNotEmpty(
        GoodsMasterMaker.PRIME_PARTS_SPECIAL_NOTE_C, goodsMasterSearchModel.getPrimePartsSpecialNoteC());
    // 優良部品規格・特記事項(B向け)= 優良部品規格・特記事項(B向け)
    StringFilter primePartsSpecialNoteB = StringFilterBuilder.containsIfNotEmpty(
        GoodsMasterMaker.PRIME_PARTS_SPECIAL_NOTE_B, goodsMasterSearchModel.getPrimePartsSpecialNoteB());
    // 作成日時 <=引数. 作成日時
    DateFilter insDtTimeEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(GoodsMasterMaker.INS_DT_TIME,
        goodsMasterSearchModel.getInsDtTimeEnd());
    // 作成日時>=引数. 作成日時
    DateFilter insDtTimeStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(GoodsMasterMaker.INS_DT_TIME,
        goodsMasterSearchModel.getInsDtTimeStart());
    // 更新日時 <=引数. 作成日時
    DateFilter updDtTimeEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(GoodsMasterMaker.UPD_DT_TIME,
        goodsMasterSearchModel.getUpdDtTimeEnd());
    // 更新日時>=引数. 更新日時
    DateFilter updDtTimeStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(GoodsMasterMaker.UPD_DT_TIME,
        goodsMasterSearchModel.getUpdDtTimeStart());

    // 適用日付<= 引数.適用日付
    DateFilter startDateEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(GoodsMasterMaker.START_TIME,
        goodsMasterSearchModel.getStartDateEnd());
    // 適用日付>= 引数.適用日付
    DateFilter startDateStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(GoodsMasterMaker.START_TIME,
        goodsMasterSearchModel.getStartDateStart());
    // 論理削除区分
    StringFilter logicalDelDiv = StringFilterBuilder.equalsIfNotNull(GoodsMasterMaker.LOGICAL_DEL_DIV, "0");
    // インポート区分
    NumberFilter importKbn = null;
    if (mode == ModeEnum.Input.getValue()) {
      importKbn = NumberFilterBuilder.in(GoodsMasterMaker.IMPORT_KBN, 0, 1, 2);
    } else if (mode == ModeEnum.Reference.getValue()) {
      importKbn = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.IMPORT_KBN, 0);
    } else if (mode == ModeEnum.Select.getValue()) {
      importKbn = NumberFilterBuilder.in(GoodsMasterMaker.IMPORT_KBN, 1, 2);
      if (applyCondition == null) {
        applyCondition = NumberFilterBuilder.in(GoodsMasterMaker.APPLY_CONDITION, ApplyConditionEnum.NoApply.getValue(),
            ApplyConditionEnum.Applyagain.getValue());
      }
    } else if (mode == 4) {// 【インポート】流れの場合
      importKbn = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.IMPORT_KBN, 1);
    }
    filter = new AndFilter(blCode, primePartsNoWithH, primePartsKanaNm, prmSetDtlNo1, applyCondition, deleteFlg,
        equipName, errorFlg, goodsDetailB, goodsDetailC, goodsMGroup, manageKbn, openPriceDiv, partsLayerCd,
        primePartsSpecialNoteC, primePartsSpecialNoteB, insDtTimeEnd, insDtTimeStart, updDtTimeEnd, updDtTimeStart,
        startDateEnd, startDateStart, logicalDelDiv, partsMakerCd, importKbn);
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
  public GoodsMasterMaker searchGoodsById(GoodsMasterMakerId goodsMasterId) {
    return goodsMasterDao.findById(goodsMasterId);
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param primePartsNoWithH 優良品番(－付き品番)
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public GoodsMasterMaker searchGoodsById(int prmSetDtlNo1, int partsMakerCd, String primePartsNoWithH) {
    GoodsMasterMakerId goodsMasterId = new GoodsMasterMakerId();
    // 部品メーカーコード = 引数.部品メーカーコード
    goodsMasterId.setPartsMakerCd(partsMakerCd);
    // 優良品番(－付き品番) = 引数.結合先品番
    goodsMasterId.setPrimePartsNoWithH(primePartsNoWithH);
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    goodsMasterId.setPrmSetDtlNo1(prmSetDtlNo1);
    return goodsMasterDao.findById(goodsMasterId);
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param partsMakerCd 部品メーカーコード
   * @param joinDestPartsNo 優良品番
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public List<GoodsMasterMaker> searchGoods(String partsMakerCd, String joinDestPartsNo) {
    Validate.notNull(partsMakerCd, "partsMakerCd must not be null");
    Validate.notNull(joinDestPartsNo, "joinDestPartsNo must not be null");

    GoodsMasterMaker searchCmd = new GoodsMasterMaker();
    searchCmd.setPartsMakerCd(Integer.parseInt(partsMakerCd));
    searchCmd.setPrimePartsNoWithH(joinDestPartsNo);
    searchCmd.setLogicalDelDiv(DeleteFlgEnum.NoDelete.getValue());

    return goodsMasterDao.findByExample(searchCmd);
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public GoodsMasterMakerModel searchGoodsInfoAll() {
    GoodsMasterMakerModel goodsMasterModel = new GoodsMasterMakerModel();
    goodsMasterModel.setGoodsList(goodsMasterDao.findAll());
    return goodsMasterModel;
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
   * @param mode 画面mode
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public GoodsMasterMakerModel searchGoodsInfoAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd, int mode) {
    GoodsMasterMakerModel goodsMasterModel = new GoodsMasterMakerModel();
    // 部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.PARTS_MAKER_CD, makerCd);
    // 論理削除区分
    StringFilter logicalDelDiv = StringFilterBuilder.equalsIfNotNull(GoodsMasterMaker.LOGICAL_DEL_DIV, "0");
    // インポート区分
    NumberFilter importKbn = null;
    NumberFilter applyCondition = null;
    if (mode == ModeEnum.Input.getValue()) {
      importKbn = NumberFilterBuilder.in(GoodsMasterMaker.IMPORT_KBN, 0, 1, 2);
    } else if (mode == ModeEnum.Reference.getValue()) {
      importKbn = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.IMPORT_KBN, 0);
      applyCondition = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.APPLY_CONDITION,
          ApplyConditionEnum.NoApply.getValue());
    } else if (mode == ModeEnum.Select.getValue()) {
      importKbn = NumberFilterBuilder.in(GoodsMasterMaker.IMPORT_KBN, 1, 2);
      if (isPage) {
        applyCondition = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.APPLY_CONDITION,
            ApplyConditionEnum.NoApply.getValue());
      } else {
        applyCondition = NumberFilterBuilder.in(GoodsMasterMaker.APPLY_CONDITION, ApplyConditionEnum.NoApply.getValue(),
            ApplyConditionEnum.Applyagain.getValue());
      }
    } else if (mode == 4) {// 【インポート】流れの場合
      importKbn = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.IMPORT_KBN, 1);
      applyCondition = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.APPLY_CONDITION,
          ApplyConditionEnum.NoApply.getValue());
    }
    Filter filter = new AndFilter(partsMakerCd, logicalDelDiv, importKbn, applyCondition);
    Limit limit = Limit.NO_LIMIT;

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
    if (isPage && maxRows != -1) {
      limit = new Limit(skipRows, maxRows);
    }
    goodsMasterModel.setGoodsList(goodsMasterDao.findByFilter(filter, limit, orderby));
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
  public void logicaldeleteGoodsInfo(GoodsMasterMaker goodsMaster) {
    GoodsMasterMakerId goodsMasterId = new GoodsMasterMakerId();
    // 部品メーカーコード = 引数.部品メーカーコード
    goodsMasterId.setPartsMakerCd(goodsMaster.getPartsMakerCd());
    // 優良品番(－付き品番) = 引数.優良品番(－付き品番)
    goodsMasterId.setPrimePartsNoWithH(goodsMaster.getPrimePartsNoWithH());
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    goodsMasterId.setPrmSetDtlNo1(goodsMaster.getPrmSetDtlNo1());
    // 検索結果を取得
    GoodsMasterMaker goodsMasterResult = goodsMasterDao.findById(goodsMasterId);
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
  public void deleteGoodsInfo(GoodsMasterMaker goodsMaster) {
    GoodsMasterMakerId goodsMasterId = new GoodsMasterMakerId();
    // 部品メーカーコード = 引数.部品メーカーコード
    goodsMasterId.setPartsMakerCd(goodsMaster.getPartsMakerCd());
    // 優良品番(－付き品番) = 引数.優良品番(－付き品番)
    goodsMasterId.setPrimePartsNoWithH(goodsMaster.getPrimePartsNoWithH());
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    goodsMasterId.setPrmSetDtlNo1(goodsMaster.getPrmSetDtlNo1());
    // 検索結果を取得
    GoodsMasterMaker goodsMasterResult = goodsMasterDao.findById(goodsMasterId);
    if (goodsMasterResult != null) {
      goodsMasterDao.delete(goodsMasterResult);
    }
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param importKbn インポート区分(メーカー)
   * @param applyCondition 申請状態(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public List<GoodsMasterMaker> searchGoodsInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                                    Integer partsMakerCd, Integer logicalDeleteCode) {
    NumberFilter ftImportKbn = NumberFilterBuilder.equals(GoodsMasterMaker.IMPORT_KBN, importKbn);
    NumberFilter ftApplyCondition = NumberFilterBuilder.equals(GoodsMasterMaker.APPLY_CONDITION, applyCondition);
    StringFilter ftUpdAccountId = StringFilterBuilder.notEquals(GoodsMasterMaker.UPD_ACCOUNT_ID, updAccountId);
    NumberFilter ftPartsMakerCd = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, partsMakerCd);
    NumberFilter ftLogicalDeleteCode = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV, logicalDeleteCode);
    Filter filter = new AndFilter(ftImportKbn, ftApplyCondition, ftUpdAccountId, ftPartsMakerCd, ftLogicalDeleteCode);
    List<GoodsMasterMaker> itemMasterList = goodsMasterDao.findByFilter(filter, Limit.NO_LIMIT);
    return itemMasterList;
  }

  /**
   * <pre>
   * チェックリスト画面の商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public GoodsMasterMakerModel searchGoodsList(String updAccountId, int partsMakerCd) {
    GoodsMasterMakerModel goodsMasterModel = new GoodsMasterMakerModel();
    // 更新者
    StringFilter updAccount = StringFilterBuilder.notEquals(GoodsMasterMaker.UPD_ACCOUNT_ID, updAccountId);
    // 部品メーカーコード
    NumberFilter partsMaker = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.PARTS_MAKER_CD, partsMakerCd);
    // インポート区分
    StringFilter importKbn = StringFilterBuilder.equalsIfNotNull(GoodsMasterMaker.IMPORT_KBN, "0");
    // インポート区分
    StringFilter blEntryFlg = StringFilterBuilder.equalsIfNotNull(GoodsMasterMaker.BL_ENTRY_FLG, "0");
    // 論理削除区分
    StringFilter logicalDelDiv = StringFilterBuilder.equalsIfNotNull(GoodsMasterMaker.LOGICAL_DEL_DIV, "0");
    Filter filter = new AndFilter(updAccount, partsMaker, importKbn, blEntryFlg, logicalDelDiv);
    List<GoodsMasterMaker> goodsMasterList = goodsMasterDao.findByFilter(filter, Limit.NO_LIMIT);
    goodsMasterModel.setGoodsList(goodsMasterList);
    return goodsMasterModel;
  }

  // --------------- add by liangsd ------------------>>>
  /**
   * <pre>
   * 商品マスタ(メーカー)削除.
   * </pre>
   * 
   * @param loginId loginId
   */
  public void deleteKGoodsInfo(String loginId) {
    // 検索結果を取得
    List<GoodsMasterMakerRealTime> goodsMasterResult = searchAllbyLoginId(loginId);
    if (goodsMasterResult != null && goodsMasterResult.size() > 0) {
      for (GoodsMasterMakerRealTime deleteRealTime : goodsMasterResult) {
        goodsMasterRealTimeDao.delete(deleteRealTime);
      }
    }

  }

  /**
   * <pre>
   * 商品マスタ(メーカー)登録.
   * </pre>
   * 
   * @param goodsMasterList 商品マスタ(メーカー)
   */
  public void insertKGoodsInfoList(List<GoodsMasterMakerRealTime> goodsMasterList) {
    goodsMasterRealTimeDao.batchInsert(goodsMasterList);
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param loginId ID
   * @return 商品マスタ(メーカー)情報
   */
  public List<GoodsMasterMakerRealTime> searchAllbyLoginId(String loginId) {
    GoodsMasterMakerRealTime goodsMasterMakerRealTime = new GoodsMasterMakerRealTime();
    goodsMasterMakerRealTime.setLoginId(loginId);
    // 検索結果を取得
    List<GoodsMasterMakerRealTime> goodsMasterResult = goodsMasterRealTimeDao.findByExample(goodsMasterMakerRealTime);

    return goodsMasterResult;
  }
  // --------------- add by liangsd ------------------<<<
}
