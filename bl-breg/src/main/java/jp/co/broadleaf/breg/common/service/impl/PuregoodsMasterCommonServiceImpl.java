//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 湯凱
// 作 成 日       2017/03/03   修正内容 : 結合詳細：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.service.impl;

import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommonId;
import jp.co.broadleaf.breg.common.service.PuregoodsMasterCommonService;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
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
 * 純正商品マスタのServiceクラス
 * </pre>
 */
public class PuregoodsMasterCommonServiceImpl implements PuregoodsMasterCommonService{

  /** 純正商品マスタDAO **/
  private GenericDao<PuregoodsMasterCommon, PuregoodsMasterCommonId> puregoodsMasterCommonDao;
  
  /**
   * 純正商品マスタDAOを設定する
   * 
   * @param pureGoodsMasterCommonDao 層別マストDAO
   */
  @Resource
  public void setPuregoodsMasterCommonDao(GenericDao<PuregoodsMasterCommon, PuregoodsMasterCommonId> pureGoodsMasterCommonDao) {
    this.puregoodsMasterCommonDao = pureGoodsMasterCommonDao;
  }

  /**
   * 純正部品情報を取得
   * 
   * @param partsMakerCd 部品メーカーコード
   * @param joinSourPartsNo 結合元品番(－付き品番)
   * @return 純正部品情報
   */
  @Override
  public List<PuregoodsMasterCommon> getPureGoodsInfo(int partsMakerCd, String joinSourPartsNo) {
    NumberFilter partsMakerCdFilter = NumberFilterBuilder.equals(PuregoodsMasterCommon.JOIN_SOURCE_MAKER_CODE,
        partsMakerCd);
    StringFilter primePartsNoWithHFilter = StringFilterBuilder.equals(PuregoodsMasterCommon.PRIME_PARTS_NO_WITH_H,
        joinSourPartsNo);
    Filter pureFilter = new AndFilter(partsMakerCdFilter, primePartsNoWithHFilter);
    List<PuregoodsMasterCommon> puregoodsMasterCommons = puregoodsMasterCommonDao.findByFilter(pureFilter,
        Limit.NO_LIMIT);
    return puregoodsMasterCommons;
  }

  /**
   * 純正部品情報を取得
   * 
   * @param partsMakerCd 部品メーカーコード
   * @return 純正部品情報
   */
  @Override
  public List<PuregoodsMasterCommon> getPureGoodsMaster(int partsMakerCd) {
    NumberFilter partsMakerCdFilter = NumberFilterBuilder.equals(PuregoodsMasterCommon.JOIN_SOURCE_MAKER_CODE,
        partsMakerCd);
    List<PuregoodsMasterCommon> puregoodsMasterCommons = puregoodsMasterCommonDao
        .findByFilter(partsMakerCdFilter, Limit.NO_LIMIT);
    return puregoodsMasterCommons;
  }
  
  
}
