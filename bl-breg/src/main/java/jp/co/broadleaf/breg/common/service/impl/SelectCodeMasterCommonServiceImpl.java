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

import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommonId;
import jp.co.broadleaf.breg.common.service.SelectCodeMasterCommonService;
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
 * セレクトコードマストのServiceクラス。
 * </pre>
 */
public class SelectCodeMasterCommonServiceImpl implements SelectCodeMasterCommonService{

  /** セレクトコードマストDAO */
  private GenericDao<SelectCodeMasterCommon,SelectCodeMasterCommonId> selectMasterCommonDao;
  
  /**
   * セレクトコードマストDAOを設定する
   * 
   * @param selectMasterComonDao セレクトコードマストDAO
   */
  @Resource
  public void setSelectCodeMasterCommonDao(GenericDao<SelectCodeMasterCommon,SelectCodeMasterCommonId> selectMasterComonDao){
    this.selectMasterCommonDao = selectMasterComonDao;
  }
  
  /**
   * セレクトコードマスト情報を取得
   * 
   * @param goodsMakerCd メーカーコード
   * @return セレクトコードマスト情報
   */
  @Override
  public List<SelectCodeMasterCommon> getSelectCodeInfo(int goodsMakerCd) {
    NumberFilter makerCdFilter = NumberFilterBuilder.equals(SelectCodeMasterCommon.GOODS_MAKER_CD, goodsMakerCd);
    NumberFilter logicUndeletedFilter = NumberFilterBuilder.equals(
        SelectCodeMasterCommon.LOGICAL_DEL_DIV, SelectCodeMasterCommon.LOGICAL_UNDELETED);
    Filter filter = new AndFilter(makerCdFilter,logicUndeletedFilter);
    return selectMasterCommonDao.findByFilter(filter, Limit.NO_LIMIT);
  }
}
