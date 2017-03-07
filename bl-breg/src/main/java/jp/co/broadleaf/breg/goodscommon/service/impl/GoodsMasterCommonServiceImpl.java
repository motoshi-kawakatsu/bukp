//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : xuck
// 作 成 日       2017/02/14   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.goodscommon.service.impl;

import jp.co.broadleaf.breg.common.entity.GoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterCommonId;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.goodscommon.service.GoodsMasterCommonService;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.FieldDescOrder;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.StringFilter;
import jp.co.broadleaf.framework.dao.criteria.StringFilterBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 商品一覧サービスクラス.
 * </pre>
 */
public class GoodsMasterCommonServiceImpl implements GoodsMasterCommonService {

  /** 商品DAO */
  private GenericDao<GoodsMasterCommon, GoodsMasterCommonId> goodsMasterDao;

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param goodsMasterDaoNew 商品DAO
   */
  @Resource
  public void setGoodsMasterCommonDao(GenericDao<GoodsMasterCommon, GoodsMasterCommonId> goodsMasterDaoNew) {
    this.goodsMasterDao = goodsMasterDaoNew;
  }

  /**
   * <pre>
   * 商品マスタ(common)登録.
   * </pre>
   * 
   * @param goodsMasterList 商品マスタ(common)
   * @return 登録した商品マスタ(common)の件数
   */
  @Override
  public int insertGoodsInfoList(List<GoodsMasterCommon> goodsMasterList) {
    if (goodsMasterList.isEmpty()) {
      return 0;
    }
    // 登録
    goodsMasterDao.batchInsert(goodsMasterList);
    // 登録した件数を返却する
    return goodsMasterList.size();
  }

  /**
   * <pre>
   * 商品マスタ(common)の検索.
   * </pre>
   * 
   * @param goodsMasterMaker 検索する項目（優良設定詳細コード１ など）の連係情報
   * @return 商品マスタ(common)
   */
  @Override
  public List<GoodsMasterCommon> searchGoodsMasterCommonList(GoodsMasterMaker goodsMasterMaker) {
    List<GoodsMasterCommon> goodsMasterCommonList = new ArrayList<>();
    // 優良設定詳細コード１ = 引数.セレクトコード
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equalsIfNotNull(GoodsMasterCommon.PRM_SET_DTL_NO_1,
        goodsMasterMaker.getPrmSetDtlNo1());
    // 優良設定詳細コード１ = 引数.セレクトコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(GoodsMasterCommon.PARTS_MAKER_CD,
        goodsMasterMaker.getPartsMakerCd());
    // 優良品番(－付き品番) = 引数.優良品番(－付き品番)
    StringFilter primePartsNoWithH = StringFilterBuilder.equalsIfNotNull(GoodsMasterCommon.PRIME_PARTS_NO_WITH_H,
        goodsMasterMaker.getPrimePartsNoWithH());
    // 論理削除区分
    NumberFilter logicalDelDiv = NumberFilterBuilder.equalsIfNotNull(GoodsMasterCommon.LOGICAL_DEL_DIV,
        GoodsMasterCommon.LOGICAL_UNDELETED);

    Filter filter = new AndFilter(prmSetDtlNo1, partsMakerCd, primePartsNoWithH, logicalDelDiv);
    FieldDescOrder orderby = new FieldDescOrder(GoodsMasterCommon.APPLY_NO);
    // 結合マスタ(メーカー)の検索
    goodsMasterCommonList = goodsMasterDao.findByFilter(filter, Limit.NO_LIMIT, orderby);
    return goodsMasterCommonList;
  }

  /**
   * 商品マスタ(メーカー)更新処理.
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   */
  @Override
  public void updateGoodsInfo(GoodsMasterCommon goodsMaster) {
    GoodsMasterCommonId goodsMasterId = new GoodsMasterCommonId();
    // 部品メーカーコード
    goodsMasterId.setPartsMakerCd(goodsMaster.getPartsMakerCd());
    // 優良品番(－付き品番)
    goodsMasterId.setPrimePartsNoWithH(goodsMaster.getPrimePartsNoWithH());
    // 優良設定詳細コード１
    goodsMasterId.setPrmSetDtlNo1(goodsMaster.getPrmSetDtlNo1());
    // 申請No
    goodsMasterId.setApplyNo(goodsMaster.getApplyNo());
    GoodsMasterCommon goodsMasterResult = goodsMasterDao.findById(goodsMasterId);
    if (null != goodsMasterResult) {
      // BL登録区分
      goodsMasterResult.setBlEntryFlg(goodsMaster.getBlEntryFlg());
      // 処理区分
      goodsMasterResult.setDealFlg(goodsMaster.getBlEntryFlg());
      // 削除依頼区分
      goodsMasterResult.setDeleteFlg(goodsMaster.getDeleteFlg());
      // 削除理由
      goodsMasterResult.setDeleteReason(goodsMaster.getDeleteReason());
      // 装備名称
      goodsMasterResult.setEquipName(goodsMaster.getEquipName());
      // データステータス
      goodsMasterResult.setErrFlg(goodsMaster.getErrFlg());
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
      goodsMasterResult.setPackageSize1(goodsMaster.getPackageSize2());
      // 梱包サイズ(幅）
      goodsMasterResult.setPackageSize2(goodsMaster.getPackageSize3());
      // 梱包サイズ(高さ）
      goodsMasterResult.setPackageSize3(goodsMaster.getPackageSize1());
      // 層別コード
      goodsMasterResult.setPartsLayerCd(goodsMaster.getPartsLayerCd());
      // 優良部品カナ名称
      goodsMasterResult.setPrimePartsKanaNm(goodsMaster.getPrimePartsKanaNm());
      // 優良部品名称
      goodsMasterResult.setPrimePartsName(goodsMaster.getPrimePartsName());
      // 優良部品規格・特記事項
      goodsMasterResult.setPrimePartsSpecialNote(goodsMaster.getPrimePartsSpecialNote());
      // 優良部品規格・特記事項(C向け)
      goodsMasterResult.setPrimePartsSpecialNoteC(goodsMaster.getPrimePartsSpecialNoteC());
      // サイズ単位
      goodsMasterResult.setSizeUnit(goodsMaster.getSizeUnit());
      // 適用日時
      goodsMasterResult.setStartTime(goodsMaster.getStartTime());
      // BLコード
      goodsMasterResult.setTbsPartsCode(goodsMaster.getTbsPartsCode());
      // URL1
      goodsMasterResult.setUrl1(goodsMaster.getUrl1());
      // URL2
      goodsMasterResult.setUrl2(goodsMaster.getUrl2());
      // URL3
      goodsMasterResult.setUrl3(goodsMaster.getUrl3());
      // 重量単位
      goodsMasterResult.setWeightUnit(goodsMaster.getWeightUnit());
      // 商品マスタ(メーカー)情報更新
      goodsMasterDao.update(goodsMasterResult);
    } else {
      goodsMasterDao.insert(goodsMaster);
    }
  }

  /**
   * <pre>
   * 商品マスタ(common)削除.
   * </pre>
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   */
  @Override
  public void deleteGoods(GoodsMasterCommon goodsMaster) {
    GoodsMasterCommonId goodsMasterId = new GoodsMasterCommonId();
    // 部品メーカーコード = 引数.部品メーカーコード
    goodsMasterId.setPartsMakerCd(goodsMaster.getPartsMakerCd());
    // 優良品番(－付き品番) = 引数.優良品番(－付き品番)
    goodsMasterId.setPrimePartsNoWithH(goodsMaster.getPrimePartsNoWithH());
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    goodsMasterId.setPrmSetDtlNo1(goodsMaster.getPrmSetDtlNo1());
    // 検索結果を取得
    GoodsMasterCommon goodsMasterResult = goodsMasterDao.findById(goodsMasterId);
    if (goodsMasterResult != null) {
      goodsMasterDao.delete(goodsMasterResult);
    }
  }

}
