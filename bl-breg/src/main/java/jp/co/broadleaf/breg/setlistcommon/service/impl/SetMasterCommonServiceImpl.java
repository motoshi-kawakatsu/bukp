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
package jp.co.broadleaf.breg.setlistcommon.service.impl;

import jp.co.broadleaf.breg.common.entity.SetMasterCommon;
import jp.co.broadleaf.breg.common.entity.SetMasterCommonId;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.setlistcommon.service.SetMasterCommonService;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.FieldDescOrder;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.StringFilter;
import jp.co.broadleaf.framework.dao.criteria.StringFilterBuilder;
import jp.co.broadleaf.framework.security.SecurityContextHolder;

import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * セットマスタ(common)サービスクラス.
 * </pre>
 */
public class SetMasterCommonServiceImpl implements SetMasterCommonService {

  /** セットマスタ(common)DAO */
  private GenericDao<SetMasterCommon, SetMasterCommonId> setMasterCommonDao;

  /**
   * <pre>
   * セットマスタ(common)DAOを設定する.
   * </pre>
   * 
   * @param setMasterCommonDao セットマスタ(common)DAO
   */
  @Resource
  public void setSetMasterCommonDao(GenericDao<SetMasterCommon, SetMasterCommonId> setMasterCommonDao) {
    this.setMasterCommonDao = setMasterCommonDao;
  }

  /**
   * <pre>
   * セットマスタ(common)登録.
   * </pre>
   * 
   * @param setMasterList 登録する項目（優良設定詳細コード１ など）の連係情報
   * @return 登録したセットマスタ(common)情報の件数
   */
  @Override
  public int insertSetMasterCommonList(List<SetMasterCommon> setMasterList) {
    if (setMasterList.isEmpty()) {
      return 0;
    }
    // 登録
    setMasterCommonDao.batchInsert(setMasterList);
    // 登録した件数を返却する
    return setMasterList.size();
  }

  /**
   * <pre>
   * セットマスタ(common)情報更新.
   * </pre>
   * 
   * @param setMaster 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  @Override
  public void updateSetMasterCommon(SetMasterCommon setMaster) {
    SetMasterCommonId setMasterCommonId = new SetMasterCommonId();
    // 申請No
    setMasterCommonId.setApplyNo(setMaster.getApplyNo());
    // 部品メーカーコード
    setMasterCommonId.setPartsMakerCd(setMaster.getPartsMakerCd());
    // 優良設定詳細コード１
    setMasterCommonId.setPrmSetDtlNo1(setMaster.getPrmSetDtlNo1());
    // セット表示順位
    setMasterCommonId.setSetDispOrder(setMaster.getSetDispOrder());
    // セット親品番
    setMasterCommonId.setSetMainPartsNo(setMaster.getSetMainPartsNo());
    SetMasterCommon setMasterResult = setMasterCommonDao.findById(setMasterCommonId);
    if (null != setMasterResult) {
      // BL登録区分
      setMasterResult.setBlEntryFlg(setMaster.getBlEntryFlg());
      // 処理区分
      setMasterResult.setDealFlg(setMaster.getDealFlg());
      // 削除依頼区分
      setMasterResult.setDeleteFlg(setMaster.getDeleteFlg());
      // 削除理由
      setMasterResult.setDeleteReason(setMaster.getDeleteReason());
      // データステータス
      setMasterResult.setErrFlg(setMaster.getErrFlg());
      // 商品中分類コード
      setMasterResult.setGoodsMGroup(setMaster.getGoodsMGroup());
      // 優良部品規格・特記事項(C向け)
      setMasterResult.setPrimePartsSpecialNoteC(setMaster.getPrimePartsSpecialNoteC());
      // セット規格・特記事項
      setMasterResult.setSetApecialNote(setMaster.getSetApecialNote());
      // 品名
      setMasterResult.setSetKanaName(setMaster.getSetKanaName());
      // セット名称
      setMasterResult.setSetName(setMaster.getSetName());
      // セットQTY
      setMasterResult.setSetQty(setMaster.getSetQty());
      // セット子品番
      setMasterResult.setSetSubPartsNo(setMaster.getSetSubPartsNo());
      // 適用日時
      setMasterResult.setStartTime(setMaster.getStartTime());
      // BLコード
      setMasterResult.setTbsPartsCode(setMaster.getTbsPartsCode());
      setMasterCommonDao.update(setMasterResult);
    } else {
      setMasterCommonDao.insert(setMaster);
    }
  }

  /**
   * <pre>
   * セットマスタ(common)の検索.
   * </pre>
   * 
   * @param setMasterMaker 検索する項目（優良設定詳細コード１ など）の連係情報
   * @return 結合マスタ(common)
   */
  @Override
  public List<SetMasterCommon> searchSetMasterCommonList(SetMasterMaker setMasterMaker) {
    // 優良設定詳細コード１ = 引数.セレクトコード
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equalsIfNotNull(SetMasterCommon.PRM_SET_DTL_NO_1,
        setMasterMaker.getPrmSetDtlNo1());
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = loginPrincipal.getMakerCode();
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(SetMasterCommon.PARTS_MAKER_CD, makerCode);

    // セット親品番 = 引数.セット親品番
    StringFilter setMainPartsNo = StringFilterBuilder.equalsIfNotNull(SetMasterCommon.SET_MAIN_PARTS_NO,
        setMasterMaker.getSetMainPartsNo());
    // セット子品番 = 引数.セット子品番
    StringFilter setSubPartsNo = StringFilterBuilder.equalsIfNotNull(SetMasterCommon.SET_SUB_PARTS_NO,
        setMasterMaker.getSetSubPartsNo());
    // 論理削除区分
    NumberFilter logicalDelDiv = NumberFilterBuilder.equalsIfNotNull(SetMasterCommon.LOGICAL_DEL_DIV,
        SetMasterCommon.LOGICAL_UNDELETED);
    Filter filter = new AndFilter(prmSetDtlNo1, partsMakerCd, setMainPartsNo, setSubPartsNo, logicalDelDiv);
    FieldDescOrder orderby = new FieldDescOrder(SetMasterCommon.APPLY_NO);
    // セットマスタ(common)の検索
    List<SetMasterCommon> setMasterCommonList = setMasterCommonDao.findByFilter(filter, Limit.NO_LIMIT, orderby);
    return setMasterCommonList;
  }

  /**
   * <pre>
   * セットマスタ(common)delete.
   * </pre>
   * 
   * @param setMasterMakerModel セットマスタ(メーカー)ID
   */
  @Override
  public void deleteSet(SetMasterCommon setMaster) {
    SetMasterCommonId setMasterCommonId = new SetMasterCommonId();
    setMasterCommonId.setApplyNo(setMaster.getApplyNo());
    setMasterCommonId.setPartsMakerCd(setMaster.getPartsMakerCd());
    setMasterCommonId.setPrmSetDtlNo1(setMaster.getPrmSetDtlNo1());
    setMasterCommonId.setSetDispOrder(setMaster.getSetDispOrder());
    setMasterCommonId.setSetMainPartsNo(setMaster.getSetMainPartsNo());
    SetMasterCommon setMasterCommonResult = setMasterCommonDao.findById(setMasterCommonId);
    if (null != setMasterCommonResult) {
      setMasterCommonDao.delete(setMasterCommonResult);
    }
  }

}
