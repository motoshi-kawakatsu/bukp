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

import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommonId;
import jp.co.broadleaf.breg.common.service.BlCodeMasterCommonService;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;

import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * BLコードマスタのServiceクラス
 * </pre>
 */
public class BlCodeMasterCommonServiceImpl implements BlCodeMasterCommonService {

  /** BLコードマスタDAO */
  private GenericDao<BlCodeMasterCommon, BlCodeMasterCommonId> blCodeMasterCommonDao;

  /**
   * BLコードマスタDAOを設定する
   * 
   * @param blCodeMasterCommonDao BLコードマスタDAO
   */
  @Resource
  public void setBlCodeMasterCommonDao(GenericDao<BlCodeMasterCommon, BlCodeMasterCommonId> blCodeMasterCommonDao) {
    this.blCodeMasterCommonDao = blCodeMasterCommonDao;
  }

  /**
   * BLコードマスタ情報を取得
   * 
   * @param makerCd メーカーコード
   * @return BLコードマスタ情報
   */
  @Override
  public List<BlCodeMasterCommon> getBlCodeMasterInfo(int makerCd) {
    NumberFilter blCodeFilter = NumberFilterBuilder.equals(BlCodeMasterCommon.GOODS_MAKER, makerCd);
    NumberFilter logicUndeletedFilter = NumberFilterBuilder.equals(BlCodeMasterCommon.LOGICAL_DEL_DIV,
        BlCodeMasterCommon.LOGICAL_UNDELETED);
    Filter filter = new AndFilter(blCodeFilter, logicUndeletedFilter);
    return blCodeMasterCommonDao.findByFilter(filter, Limit.NO_LIMIT);
  }

  /**
   * BLコードマスタ情報を取得
   * 
   * @param blCodeMasterCommonId blCodeMasterCommonId
   * @return BLコードマスタ情報
   */
  @Override
  public BlCodeMasterCommon getBlCodeMaster(BlCodeMasterCommonId blCodeMasterCommonId) {
    NumberFilter logicUndeletedFilter = NumberFilterBuilder.equals(BlCodeMasterCommon.LOGICAL_DEL_DIV,
        BlCodeMasterCommon.LOGICAL_UNDELETED);
    NumberFilter goodsMakerFilter = NumberFilterBuilder.equals(BlCodeMasterCommon.GOODS_MAKER,
        blCodeMasterCommonId.getGoodsMaker());
    NumberFilter blCodeFilter = NumberFilterBuilder.equals(BlCodeMasterCommon.BL_CODE,
        blCodeMasterCommonId.getBlCode());
    Filter filter = new AndFilter(logicUndeletedFilter, goodsMakerFilter, blCodeFilter);
    return blCodeMasterCommonDao.findOneByFilter(filter);
  }
}
