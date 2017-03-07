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

import jp.co.broadleaf.breg.common.entity.KindCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.KindCodeMasterCommonId;
import jp.co.broadleaf.breg.common.service.KindCodeMasterCommonService;
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
 * 種別コードマストのServiceクラス
 * </pre>
 */
public class KindCodeMasterCommonServiceImpl implements KindCodeMasterCommonService {

  /** 種別コードマストDAO */
  private GenericDao<KindCodeMasterCommon, KindCodeMasterCommonId> kindCodeMasterCommonDao;

  /**
   * 種別コードマストDAOを設定する
   * 
   * @param kindCodeMasterCommonDao 種別コードマストDAO
   */
  @Resource
  public void setKindCodeMasterCommonDao(GenericDao<KindCodeMasterCommon, KindCodeMasterCommonId> kindCodeMasterCommonDao) {
    this.kindCodeMasterCommonDao = kindCodeMasterCommonDao;
  }

  /**
   * 種別コードマスト情報を取得
   * 
   * @param makerCd メーカーコード
   * @return 種別コードマスト情報
   */
  @Override
  public List<KindCodeMasterCommon> getKindCodeInfo(int makerCd) {
    NumberFilter goodsMakerCdFilter = NumberFilterBuilder.equals(KindCodeMasterCommon.GOODS_MAKER, makerCd);
    NumberFilter logicUndeletedFilter = NumberFilterBuilder.equals(KindCodeMasterCommon.LOGICAL_DEL_DIV,
        KindCodeMasterCommon.LOGICAL_UNDELETED);
    Filter filter = new AndFilter(goodsMakerCdFilter, logicUndeletedFilter);
    return kindCodeMasterCommonDao.findByFilter(filter, Limit.NO_LIMIT);
  }
}
