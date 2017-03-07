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
package jp.co.broadleaf.breg.joinlistcommon.service.impl;

import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommonId;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.joinlistcommon.service.JoinMasterCommonService;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.FieldDescOrder;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.StringFilter;
import jp.co.broadleaf.framework.dao.criteria.StringFilterBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 結合マスタ(common)サービスクラス.
 * </pre>
 */
public class JoinMasterCommonServiceImpl implements JoinMasterCommonService {

  /** 結合マスタ(common)DAO */
  private GenericDao<JoinMasterCommon, JoinMasterCommonId> joinMasterDao;

  /**
   * <pre>
   * 結合マスタ(common)DAOを設定する.
   * </pre>
   * 
   * @param joinMasterDaoNew 結合マスタ(common)DAO
   */
  @Resource
  public void setJoinMasterCommonDao(GenericDao<JoinMasterCommon, JoinMasterCommonId> joinMasterDaoNew) {
    this.joinMasterDao = joinMasterDaoNew;
  }

  /**
   * <pre>
   * 結合マスタ(common)の検索.
   * </pre>
   *
   * @param joinMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @return 結合マスタ(common)
   */
  @Override
  public List<JoinMasterCommon> searchJoinMasterCommonList(JoinMasterMaker joinMasterSearchModel) {
    List<JoinMasterCommon> joinMasterCommonList = new ArrayList<>();
    // 優良設定詳細コード１ = 引数.セレクトコード
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equalsIfNotNull(JoinMasterCommon.PRM_SET_DTL_NO_1,
        joinMasterSearchModel.getPrmSetDtlNo1());
    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(JoinMasterCommon.PARTS_MAKER_CD,
        joinMasterSearchModel.getPartsMakerCd());
    // BLコード = 引数.BLコード
    NumberFilter tbsPartsCode = NumberFilterBuilder.equalsIfNotNull(JoinMasterCommon.TBS_PARTS_CODE,
        joinMasterSearchModel.getTbsPartsCode());
    // 結合元メーカーコード = 引数.カーコード
    NumberFilter joinSourceMakerCode = NumberFilterBuilder.equalsIfNotNull(JoinMasterCommon.JOIN_SOURCE_MAKER_CODE,
        joinMasterSearchModel.getJoinSourceMakerCode());
    // 優良設定詳細コード２ = 引数.種別コード
    NumberFilter prmSetDtlNo2 = NumberFilterBuilder.equalsIfNotNull(JoinMasterCommon.PRM_SET_DTL_NO_2,
        joinMasterSearchModel.getPrmSetDtlNo2());
    // 結合元品番(－付き品番) = 引数.純正品番
    StringFilter joinSourPartsNoWithH = StringFilterBuilder.containsIfNotEmpty(
        JoinMasterCommon.JOIN_SOUR_PARTS_NO_WITH_H, joinMasterSearchModel.getJoinSourPartsNoWithH());
    // 表示順位 = 引数.表示順位
    NumberFilter joinDispOrder = NumberFilterBuilder.equalsIfNotNull(JoinMasterCommon.JOIN_DISP_ORDER,
        joinMasterSearchModel.getJoinDispOrder());
    // 論理削除区分
    NumberFilter logicalDelDiv = NumberFilterBuilder.equalsIfNotNull(JoinMasterCommon.LOGICAL_DEL_DIV,
        JoinMasterCommon.LOGICAL_UNDELETED);
    Filter filter = new AndFilter(prmSetDtlNo1, partsMakerCd, tbsPartsCode, joinSourceMakerCode, prmSetDtlNo2,
        joinSourPartsNoWithH, joinDispOrder, logicalDelDiv);
    FieldDescOrder orderby = new FieldDescOrder(JoinMasterCommon.APPLY_NO);
    // 結合マスタ(メーカー)の検索
    joinMasterCommonList = joinMasterDao.findByFilter(filter, Limit.NO_LIMIT, orderby);
    return joinMasterCommonList;
  }

  /**
   * <pre>
   * 結合マスタ(common)の登録.
   * </pre>
   * 
   * @param joinMasterList 結合マスタ(common)Model
   * @return 登録結合マスタ(common)の件数
   */
  @Override
  public int insertJoinMasterInfoList(List<JoinMasterCommon> joinMasterList) {
    if (joinMasterList.isEmpty()) {
      return 0;
    }
    // 登録
    joinMasterDao.batchInsert(joinMasterList);

    // 登録した件数を返却する
    return joinMasterList.size();
  }

  /**
   * <pre>
   * 結合マスタ(common)情報更新.
   * </pre>
   * 
   * @param joinMaster 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  @Override
  public void updateJoinMaster(JoinMasterCommon joinMaster) {
    JoinMasterCommonId joinMasterCommonId = new JoinMasterCommonId();
    // 申請No
    joinMasterCommonId.setApplyNo(joinMaster.getApplyNo());
    // 結合表示順位
    joinMasterCommonId.setJoinDispOrder(joinMaster.getJoinDispOrder());
    // 結合元メーカーコード
    joinMasterCommonId.setJoinSourceMakerCode(joinMaster.getJoinSourceMakerCode());
    // 結合元品番(－付き品番)
    joinMasterCommonId.setJoinSourPartsNoWithH(joinMaster.getJoinSourPartsNoWithH());
    // JoinMasterCommon
    joinMasterCommonId.setPartsMakerCd(joinMaster.getPartsMakerCd());
    // 優良設定詳細コード１
    joinMasterCommonId.setPrmSetDtlNo1(joinMaster.getPrmSetDtlNo1());
    // 優良設定詳細コード２
    joinMasterCommonId.setPrmSetDtlNo2(joinMaster.getPrmSetDtlNo2());
    // BLコード
    joinMasterCommonId.setTbsPartsCode(joinMaster.getTbsPartsCode());
    JoinMasterCommon joinMasterResult = joinMasterDao.findById(joinMasterCommonId);
    if (null != joinMasterResult) {
      // BL登録区分
      joinMasterResult.setBlEntryFlg(joinMaster.getBlEntryFlg());
      // 処理区分
      joinMasterResult.setDealFlg(joinMaster.getDealFlg());
      // 削除依頼区分
      joinMasterResult.setDeleteFlg(joinMaster.getDeleteFlg());
      // 削除時申請理由
      joinMasterResult.setDeleteReason(joinMaster.getDeleteReason());
      // データステータス
      joinMasterResult.setErrFlg(joinMaster.getErrFlg());
      // 商品中分類コード
      joinMasterResult.setGoodsMGroup(joinMaster.getGoodsMGroup());
      // 結合先品番(－付き品番)
      joinMasterResult.setJoinDestPartsNo(joinMaster.getJoinDestPartsNo());
      // 結合QTY
      joinMasterResult.setJoinQty(joinMaster.getJoinQty());
      // 結合規格・特記事項
      joinMasterResult.setJoinSpecialNote(joinMaster.getJoinSpecialNote());
      // 優良部品規格・特記事項(C向け)
      joinMasterResult.setPrimePartsSpecialNoteC(joinMaster.getPrimePartsSpecialNoteC());
      // 適用日時
      joinMasterResult.setStartTime(joinMaster.getStartTime());
      joinMasterDao.update(joinMasterResult);
    } else {
      joinMasterDao.insert(joinMaster);
    }
  }

  /**
   * <pre>
   * 結合マスタ(common)削除.
   * </pre>
   * 
   * @param joinMaster 結合マスタ(common)
   */
  @Override
  public void deleteJoin(JoinMasterCommon joinMaster) {
    JoinMasterCommonId joinMasterCommonId = new JoinMasterCommonId();
    joinMasterCommonId.setApplyNo(joinMaster.getApplyNo());
    joinMasterCommonId.setJoinDispOrder(joinMaster.getJoinDispOrder());
    joinMasterCommonId.setJoinSourceMakerCode(joinMaster.getJoinSourceMakerCode());
    joinMasterCommonId.setJoinSourPartsNoWithH(joinMaster.getJoinSourPartsNoWithH());
    joinMasterCommonId.setPartsMakerCd(joinMaster.getPartsMakerCd());
    joinMasterCommonId.setPrmSetDtlNo1(joinMaster.getPrmSetDtlNo1());
    joinMasterCommonId.setPrmSetDtlNo2(joinMaster.getPrmSetDtlNo2());
    joinMasterCommonId.setTbsPartsCode(joinMaster.getTbsPartsCode());
    JoinMasterCommon joinMasterCommonResult = joinMasterDao.findById(joinMasterCommonId);
    if (null != joinMasterCommonResult) {
      joinMasterDao.delete(joinMasterCommonResult);
    }
  }

  /**
   * <pre>
   * 結合マスタ(common)検索.
   * </pre>
   * 
   * @param joinMasterCommon 結合マスタ(common)
   * @return 登録結合マスタ(common)の件数
   */
  @Override
  public List<JoinMasterCommon> findJoin(JoinMasterCommon joinMasterCommon) {
    List<JoinMasterCommon> joinMasterCommons = joinMasterDao.findByExample(joinMasterCommon);
    return joinMasterCommons;
  }

}
