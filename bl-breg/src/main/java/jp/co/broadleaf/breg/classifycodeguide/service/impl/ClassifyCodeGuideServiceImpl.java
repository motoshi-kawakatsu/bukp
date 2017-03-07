//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:                 作成担当:趙　雷雷
// 作成日:2017/02/12         修正内容:商品中分類コードガイド:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.classifycodeguide.service.impl;


import jp.co.broadleaf.breg.common.entity.GoodsRateMasterCommon;

import jp.co.broadleaf.breg.classifycodeguide.service.ClassifyCodeGuideService;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.StringFilterBuilder;

import java.util.List;

import javax.annotation.Resource;
/**
 * 中分類コード検索サビース.
 */
public class ClassifyCodeGuideServiceImpl implements ClassifyCodeGuideService {
  
  /**
   * 中分類コード検索結果
   * 
   * @param logical 論理削除区分＝0:有効.
   * @param makerCode メーカーコード.
   * @param goodsCode 中分類コード
   * @param goodsName 中分類コード名
   * @return 中分類コード検索結果DtoList
   */

  @Override
  public List <GoodsRateMasterCommon> getPrimeByCode(int logical, int makerCode, String goodsCode, String goodsName)
  { 
    NumberFilter logicalFilter = NumberFilterBuilder.equals(GoodsRateMasterCommon.LOGICAL_DEL_DIV, logical);
    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(GoodsRateMasterCommon.GOODS_MAKER, makerCode);
    
    Filter filter = new AndFilter(logicalFilter, makerCodeFilter);
    
    if (!"".equals(goodsCode)) {
      filter = new AndFilter(filter, NumberFilterBuilder.equals(GoodsRateMasterCommon.GOODS_RATE_GRP_CODE, Integer.parseInt(goodsCode))); 
    }
    if (!"".equals(goodsName)) {
      filter = new AndFilter(filter, StringFilterBuilder.contains(GoodsRateMasterCommon.GOODS_RATE_GRP_NAME, goodsName)); 
    }
    
    List <GoodsRateMasterCommon> goodsMasterMakerList = goodsRateMasterCommonDao.findByFilter(filter, null);
    return goodsMasterMakerList;
  }

/** 中分類コード検索DAO */
private GenericDao<GoodsRateMasterCommon, Integer> goodsRateMasterCommonDao;

/**
 * 中分類コード検索DAOを設定する.
 * 
 * @param goodsRateMasterCommonDao 中分類コード検索DAO
 */
@Resource
public void setGoodsRateMasterCommonDao(GenericDao<GoodsRateMasterCommon, Integer> goodsRateMasterCommonDao) {
  this.goodsRateMasterCommonDao = goodsRateMasterCommonDao;
} 
  
}
