//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:                作成担当:趙　雷雷
// 作成日:2017/02/13        修正内容:商品ガイド:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.goodsguide.service.impl;

import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;
import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommonId;
import jp.co.broadleaf.breg.goodsguide.service.GoodsGuideService;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.FieldAscOrder;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.StringFilter;
import jp.co.broadleaf.framework.dao.criteria.StringFilterBuilder;

import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 *  優良品番検索サビース.
 * </pre>
 */
public class GoodsGuideServiceImpl implements GoodsGuideService {

  /**
   * 優良品番検索結果
   * 
   * @param logical 論理削除区分＝0:有効
   * @param makerCode メーカーコード
   * @param primeCode 優良品番
   * @return 優良品番検索結果
   */
  @Override
  public List<GoodsMasterMaker> getPrimeByCode(int logical, int makerCode, String primeCode) {
    final int blEntryFlg = 2;

    NumberFilter logicalFilter = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV, logical);
    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, makerCode);
    NumberFilter blEntryFlgFilter = NumberFilterBuilder.equals(GoodsMasterMaker.BL_ENTRY_FLG, blEntryFlg);
    Filter filter = new AndFilter(makerCodeFilter, logicalFilter, blEntryFlgFilter);

    if (!"".equals(primeCode)) {
      StringFilter primeCodeFilter = StringFilterBuilder.contains(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H, primeCode);
      filter = new AndFilter(makerCodeFilter, primeCodeFilter, logicalFilter);
    }

    FieldAscOrder fieldDescOrder = new FieldAscOrder(GoodsMasterMaker.PARTS_MAKER_CD);
    List<GoodsMasterMaker> goodsMasterMakerList = goodsMasterMakerDao.findByFilter(filter, null, fieldDescOrder);
    return goodsMasterMakerList;
  }

  /**
   * 優良品番検索結果
   * 
   * @param logical 論理削除区分＝0:有効
   * @param makerCode メーカーコード
   * @param primeCode 優良品番
   * @return 優良品番検索結果
   */
  @Override
  public List<GoodsMasterMaker> getPrimeByCodeAll(int logical, int makerCode, String primeCode) {

    NumberFilter logicalFilter = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV, logical);
    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, makerCode);
    Filter filter = new AndFilter(makerCodeFilter, logicalFilter);

    if (!"".equals(primeCode)) {
      StringFilter primeCodeFilter = StringFilterBuilder.contains(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H, primeCode);
      filter = new AndFilter(makerCodeFilter, primeCodeFilter, logicalFilter);
    }

    FieldAscOrder fieldDescOrder = new FieldAscOrder(GoodsMasterMaker.PARTS_MAKER_CD);
    List<GoodsMasterMaker> goodsMasterMakerList = goodsMasterMakerDao.findByFilter(filter, null, fieldDescOrder);
    return goodsMasterMakerList;
  }

  /** 優良品番検索DAO */
  private GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterMakerDao;

  /**
   * <pre>
   * 優良品番検索DAO.
   * </pre>
   * 
   * @param goodsMasterMakerDao 優良品番検索DAO
   */
  @Resource
  public void setGoodsMasterMakerDao(GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterMakerDao) {
    this.goodsMasterMakerDao = goodsMasterMakerDao;
  }

  // 純正
  /**
   * 純正品番検索結果
   * 
   * @param logical 論理削除区分＝0:有効
   * @param makerCode メーカーコード
   * @param pureGoodsCode 純正品番
   * @return 純正品番検索結果
   */
  public List<PuregoodsMasterCommon> getPureGoodsByCode(int logical, int makerCode, String pureGoodsCode) {

    NumberFilter logicalFilter = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV, logical);
    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, makerCode);
    Filter filter = new AndFilter(makerCodeFilter, logicalFilter);

    if (!"".equals(pureGoodsCode)) {
      StringFilter primeCodeFilter = StringFilterBuilder.contains(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H,
          pureGoodsCode);
      filter = new AndFilter(makerCodeFilter, primeCodeFilter, logicalFilter);
    }
    FieldAscOrder fieldDescOrder = new FieldAscOrder(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H);
    List<PuregoodsMasterCommon> pureGoodsList = puregoodsMasterCommonDao.findByFilter(filter, null, fieldDescOrder);
    return pureGoodsList;
  }

  /**
   * 純正品番検索結果
   * 
   * @param primePartsNoWithH 結合元品番
   * @param joinSourceMakerCode 結合元メーカーコード
   * @param partsMakerCd 部品メーカーコード
   * @param prmSetDtlNo1 純正設定詳細コード１
   * @return 純正品番検索結果
   */
  @Override
  public List<PuregoodsMasterCommon> getPuregoodsMaster(String primePartsNoWithH, Integer joinSourceMakerCode,
                                                        int partsMakerCd, int prmSetDtlNo1) {
    NumberFilter joinSourceMakerCodeFilter = NumberFilterBuilder
        .equalsIfNotNull(PuregoodsMasterCommon.JOIN_SOURCE_MAKER_CODE, joinSourceMakerCode);
    StringFilter primePartsNoWithHFilter = StringFilterBuilder
        .equalsIfNotNull(PuregoodsMasterCommon.PRIME_PARTS_NO_WITH_H, primePartsNoWithH);
    NumberFilter partsMakerCdFilter = NumberFilterBuilder.equalsIfNotNull(PuregoodsMasterCommon.PARTS_MAKER_CD,
        partsMakerCd);
    NumberFilter prmSetDtlNo1Filter = NumberFilterBuilder.equalsIfNotNull(PuregoodsMasterCommon.PRM_SET_DTL_NO_1,
        prmSetDtlNo1);
    Filter filter = new AndFilter(joinSourceMakerCodeFilter, primePartsNoWithHFilter, partsMakerCdFilter,
        prmSetDtlNo1Filter);
    FieldAscOrder fieldDescOrder = new FieldAscOrder(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H);
    List<PuregoodsMasterCommon> puregoodsMasterCommonList = puregoodsMasterCommonDao.findByFilter(filter,
        Limit.NO_LIMIT, fieldDescOrder);
    return puregoodsMasterCommonList;
  }

  /** 純正品番検索DAO */
  private GenericDao<PuregoodsMasterCommon, PuregoodsMasterCommonId> puregoodsMasterCommonDao;

  /**
   * <pre>
   * 純正品番検索DAO.
   * </pre>
   *
   * @param puregoodsMasterCommonDao 純正品番Dao
   */
  @Resource
  public void setPuregoodsMasterCommonDao(GenericDao<PuregoodsMasterCommon, PuregoodsMasterCommonId> puregoodsMasterCommonDao) {
    this.puregoodsMasterCommonDao = puregoodsMasterCommonDao;
  }

}
