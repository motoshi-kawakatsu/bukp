//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : xuck
// 作 成 日       2017/02/13   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applyheadmastercommon.service.impl;

import jp.co.broadleaf.breg.applyheadmastercommon.domain.model.ApplyHeadMasterCommonModel;
import jp.co.broadleaf.breg.applyheadmastercommon.service.ApplyHeadMasterCommonService;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommon;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommonId;
import jp.co.broadleaf.framework.dao.GenericDao;

import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * </pre>
 */
public class ApplyHeadMasterCommonServiceImpl implements ApplyHeadMasterCommonService {
  /**
   * dao
   */
  private GenericDao<ApplyHeadMasterCommon, ApplyHeadMasterCommonId> applyHeadMasterCommonDao;

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param applyHeadMasterCommonDao 申請ヘッダマスタDAO
   */
  @Resource
  public void setApplyHeadMasterCommonDao(GenericDao<ApplyHeadMasterCommon, ApplyHeadMasterCommonId> applyHeadMasterCommonDao) {
    this.applyHeadMasterCommonDao = applyHeadMasterCommonDao;
  }

  /**
   * <pre>
   * 申請ヘッダマスタ取得.
   * </pre>
   * 
   * @return 申請ヘッダマスタ情報
   */
  @Override
  public ApplyHeadMasterCommonModel searchApplyHeadMasterCommonList() {
    ApplyHeadMasterCommonModel applyHeadMasterCommonModel = new ApplyHeadMasterCommonModel();
    applyHeadMasterCommonModel.setApplyHeadMasterList(applyHeadMasterCommonDao.findAll());
    return applyHeadMasterCommonModel;
  }

  /**
   * <pre>
   * 申請ヘッダマスタ登録.
   * </pre>
   * 
   * @param applyHeadMasterCommonList 申請ヘッダマスタ
   * @throws Throwable Throwable
   * @return 登録した申請ヘッダマスタの件数
   */
  @Override
  public int insertApplyHeadMasterCommon(List<ApplyHeadMasterCommon> applyHeadMasterCommonList) {
    try {
      if (applyHeadMasterCommonList.isEmpty()) {
        return 0;
      }
      applyHeadMasterCommonDao.batchInsert(applyHeadMasterCommonList);
      return applyHeadMasterCommonList.size();
    } catch (Throwable e) {
      return -1;
    }
  }

}
