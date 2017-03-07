//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 王　天コン
// 作 成 日        2017/02/27   修正内容 : 結合一覧：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.service.impl;

import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.CarmakerMasterCommon;
import jp.co.broadleaf.breg.common.entity.CarmakerMasterCommonId;
import jp.co.broadleaf.breg.common.service.CarmakerMasterCommonService;
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
 * カーメーカーコードマスタのServiceクラス
 * </pre>
 */
public class CarmakerMasterCommonServiceImpl implements CarmakerMasterCommonService {

  /** カーメーカーコードマスタDAO */
  private GenericDao<CarmakerMasterCommon, CarmakerMasterCommonId> carmakerMasterCommonDao;

  /**
   * カーメーカーコードマスタDAOを設定する
   * 
   * @param carmakerMasterCommonDao カーメーカーコードマスタDAO
   */
  @Resource
  public void setCarmakerMasterCommonDao(GenericDao<CarmakerMasterCommon, CarmakerMasterCommonId> carmakerMasterCommonDao) {
    this.carmakerMasterCommonDao = carmakerMasterCommonDao;
  }

  /**
   * カーメーカーコードマスタ情報を取得
   * 
   * @param makerCd メーカーコード
   * @return カーメーカーコードマスタ情報
   */
  @Override
  public List<CarmakerMasterCommon> getCarmakerMasterInfo(int makerCd) {
    NumberFilter blCodeFilter = NumberFilterBuilder.equals(CarmakerMasterCommon.GOODS_MAKER, makerCd);
    NumberFilter logicUndeletedFilter = NumberFilterBuilder.equals(BlCodeMasterCommon.LOGICAL_DEL_DIV,
        BlCodeMasterCommon.LOGICAL_UNDELETED);
    Filter filter = new AndFilter(blCodeFilter, logicUndeletedFilter);
    return carmakerMasterCommonDao.findByFilter(filter, Limit.NO_LIMIT);
  }
}
