//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 湯凱
// 作 成 日       2017/02/13   修正内容 : 会社情報：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.service.impl;

import jp.co.broadleaf.breg.common.entity.PartsMasterCommon;
import jp.co.broadleaf.breg.common.entity.PartsMasterCommonId;
import jp.co.broadleaf.breg.common.service.PartsMasterCommonService;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.FieldAscOrder;
import jp.co.broadleaf.framework.dao.criteria.FieldOrder;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.MultiFieldOrder;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.Order;

import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 層別マストのServiceクラス
 * </pre>
 */
public class PartsMasterCommonServiceImpl implements PartsMasterCommonService {

  /** 層別マストDAO */
  private GenericDao<PartsMasterCommon, PartsMasterCommonId> partsMasterCommonDao;

  /**
   * 層別マストDAOを設定する
   * 
   * @param partsMasterCommonDao 層別マストDAO
   */
  @Resource
  public void setPartsMasterCommonDao(GenericDao<PartsMasterCommon, PartsMasterCommonId> partsMasterCommonDao) {
    this.partsMasterCommonDao = partsMasterCommonDao;
  }

  /**
   * 層別マスト情報を取得
   * 
   * @param makerCd メーカーコード
   * @return 層別マスト情報
   */
  @Override
  public List<PartsMasterCommon> getPartsMasterInfo(int makerCd) {
    NumberFilter goodsMakerCdFilter = NumberFilterBuilder.equals(PartsMasterCommon.GOODS_MAKER, makerCd);
    NumberFilter logicUndeletedFilter = NumberFilterBuilder.equals(PartsMasterCommon.LOGICAL_DEL_DIV,
        PartsMasterCommon.LOGICAL_UNDELETED);
    Filter filter = new AndFilter(goodsMakerCdFilter, logicUndeletedFilter);
    // BLコード
    FieldOrder partsLayer = new FieldAscOrder(PartsMasterCommon.PARTS_LAYER);
    Order orderby = new MultiFieldOrder(partsLayer);
    return partsMasterCommonDao.findByFilter(filter, Limit.NO_LIMIT, orderby);
  }
}
