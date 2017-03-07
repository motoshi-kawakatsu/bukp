//****************************************************************************//
// システム                                    : カーパーツマネージャー
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                            作成担当 : 秦志超
// 作 成 日       2017/02/06   修正内容 : 申請詳細:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applydetail.service.impl;

import jp.co.broadleaf.breg.applydetail.service.ApplyDetailService;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommon;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommonId;
import jp.co.broadleaf.breg.common.entity.GoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterCommonId;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommonId;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMakerId;
import jp.co.broadleaf.breg.common.entity.SetMasterCommon;
import jp.co.broadleaf.breg.common.entity.SetMasterCommonId;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.FieldDescOrder;
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
 * ApplyDetailServiceImpl
 * </pre>
 */
public class ApplyDetailServiceImpl implements ApplyDetailService {

  /** 共有側商品マスタDAO */
  private GenericDao<GoodsMasterCommon, GoodsMasterCommonId> goodsMasterCommonDao;
  /** 共有側結合マスタDAO */
  private GenericDao<JoinMasterCommon, JoinMasterCommonId> joinMasterCommonDao;
  /** 共有側セットマスタDAO */
  private GenericDao<SetMasterCommon, SetMasterCommonId> setMasterCommonDao;
  /** 共有側申請ヘッダマスタDAO */
  private GenericDao<ApplyHeadMasterCommon, ApplyHeadMasterCommonId> applyHeadMasterCommonDao;
  /** メーカー商品マスタDAO */
  private GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterMakerDao;
  /** メーカー結合マスタDAO */
  private GenericDao<JoinMasterMaker, JoinMasterMakerId> joinMasterMakerDao;
  /** メーカーセットマスタDAO */
  private GenericDao<SetMasterMaker, SetMasterMakerId> setMasterMakerDao;

  /**
   * データベースから、共有側の商品データを検索する
   * 
   * @param makerCode メーカーコード
   * @return 商品マスタ履歴
   */
  @Override
  public List<GoodsMasterCommon> searchGoodsCommon(int makerCode) {

    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(GoodsMasterCommon.PARTS_MAKER_CD, makerCode);
    FieldDescOrder fieldDescOrder = new FieldDescOrder(GoodsMasterCommon.APPLY_NO);
    Filter filter = new AndFilter(makerCodeFilter);
    List<GoodsMasterCommon> goodsMasterCommonList = goodsMasterCommonDao.findByFilter(filter, null, fieldDescOrder);

    return goodsMasterCommonList;
  }

  /**
   * データベースから、共有側の結合データを検索する
   * 
   * @param makerCode メーカーコード
   * @return 結合マスタ履歴
   */
  @Override
  public List<JoinMasterCommon> searchJoinCommon(int makerCode) {

    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(JoinMasterCommon.PARTS_MAKER_CD, makerCode);
    FieldDescOrder fieldDescOrder = new FieldDescOrder(JoinMasterCommon.APPLY_NO);
    Filter filter = new AndFilter(makerCodeFilter);
    List<JoinMasterCommon> joinMasterCommonList = joinMasterCommonDao.findByFilter(filter, null, fieldDescOrder);

    return joinMasterCommonList;
  }

  /**
   * データベースから、共有側のセットデータを検索する
   * 
   * @param makerCode メーカーコード
   * @return セットマスタ履歴
   */
  @Override
  public List<SetMasterCommon> searchSetCommon(int makerCode) {

    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(SetMasterCommon.PARTS_MAKER_CD, makerCode);
    FieldDescOrder fieldDescOrder = new FieldDescOrder(SetMasterCommon.APPLY_NO);
    Filter filter = new AndFilter(makerCodeFilter);
    List<SetMasterCommon> setMasterCommonList = setMasterCommonDao.findByFilter(filter, null, fieldDescOrder);

    return setMasterCommonList;
  }

  /**
   * データベースから、メーカー側の商品データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logical 論理削除区分
   * @param blApplyResultsFlg BL申請結果区分
   * @param updAccountId 更新アカウントID
   * @param importKbn インポート区分
   * @return 商品マスタ
   */
  @Override
  public List<GoodsMasterMaker> searchGoods(int makerCode, int logical, int blApplyResultsFlg, String updAccountId,
                                            int importKbn) {

    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, makerCode);
    NumberFilter logicalFilter = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV, logical);
    NumberFilter applyConditionFilter = NumberFilterBuilder.equals(GoodsMasterMaker.APPLY_CONDITION, blApplyResultsFlg);
    StringFilter updAccountIdFilter = StringFilterBuilder.equals(GoodsMasterMaker.UPD_ACCOUNT_ID, updAccountId);
    NumberFilter importKbnFilter = NumberFilterBuilder.equals(GoodsMasterMaker.IMPORT_KBN, importKbn);

    Filter filter = new AndFilter(applyConditionFilter, importKbnFilter, makerCodeFilter, updAccountIdFilter,
        logicalFilter);
    List<GoodsMasterMaker> goodsMasterMakerList = goodsMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);

    return goodsMasterMakerList;
  }

  /**
   * データベースから、メーカー側の結合データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logical 論理削除区分
   * @param blApplyResultsFlg BL申請結果区分
   * @param updAccountId 更新アカウントID
   * @param importKbn インポート区分
   * @return 結合マスタ
   */
  @Override
  public List<JoinMasterMaker> searchJoin(int makerCode, int logical, int blApplyResultsFlg, String updAccountId,
                                          int importKbn) {

    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(JoinMasterMaker.PARTS_MAKER_CD, makerCode);
    NumberFilter logicalFilter = NumberFilterBuilder.equals(JoinMasterMaker.LOGICAL_DEL_DIV, logical);
    NumberFilter applyConditionFilter = NumberFilterBuilder.equals(JoinMasterMaker.APPLY_CONDITION, blApplyResultsFlg);
    StringFilter updAccountIdFilter = StringFilterBuilder.equals(JoinMasterMaker.UPD_ACCOUNT_ID, updAccountId);
    NumberFilter importKbnFilter = NumberFilterBuilder.equals(JoinMasterMaker.IMPORT_KBN, importKbn);
    Filter filter = new AndFilter(makerCodeFilter, logicalFilter, applyConditionFilter, updAccountIdFilter,
        importKbnFilter);
    List<JoinMasterMaker> joinMasterMakerList = joinMasterMakerDao.findByFilter(filter, null);

    return joinMasterMakerList;
  }

  /**
   * データベースから、メーカー側のセットデータを検索する
   * 
   * @param makerCode メーカーコード
   * @param logical 論理削除区分
   * @param blApplyResultsFlg BL申請結果区分
   * @param updAccountId 更新アカウントID
   * @param importKbn インポート区分
   * @return セットマスタ
   */
  @Override
  public List<SetMasterMaker> searchSet(int makerCode, int logical, int blApplyResultsFlg, String updAccountId,
                                        int importKbn) {

    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(SetMasterMaker.PARTS_MAKER_CD, makerCode);
    NumberFilter logicalFilter = NumberFilterBuilder.equals(SetMasterMaker.LOGICAL_DEL_DIV, logical);
    NumberFilter applyConditionFilter = NumberFilterBuilder.equals(SetMasterMaker.APPLY_CONDITION, blApplyResultsFlg);
    StringFilter updAccountIdFilter = StringFilterBuilder.equals(SetMasterMaker.UPD_ACCOUNT_ID, updAccountId);
    NumberFilter importKbnFilter = NumberFilterBuilder.equals(SetMasterMaker.IMPORT_KBN, importKbn);
    Filter filter = new AndFilter(makerCodeFilter, logicalFilter, applyConditionFilter, updAccountIdFilter,
        importKbnFilter);
    List<SetMasterMaker> setMasterMakerList = setMasterMakerDao.findByFilter(filter, null);

    return setMasterMakerList;
  }

  /**
   * データベースから、共有側の商品データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return 商品マスタ履歴
   */
  @Override
  public List<GoodsMasterCommon> searchGoodsCommonShow(int makerCode, int logicDeleteDiv, int applyNo) {

    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(GoodsMasterCommon.PARTS_MAKER_CD, makerCode);
    NumberFilter logicDeleteDivFilter = NumberFilterBuilder.equals(GoodsMasterCommon.LOGICAL_DEL_DIV, logicDeleteDiv);
    NumberFilter applyNoFilter = NumberFilterBuilder.equals(GoodsMasterCommon.APPLY_NO, applyNo);
    Filter filter = new AndFilter(makerCodeFilter, logicDeleteDivFilter, applyNoFilter);
    List<GoodsMasterCommon> goodsMasterCommonList = goodsMasterCommonDao.findByFilter(filter, null);

    return goodsMasterCommonList;
  }

  /**
   * データベースから、共有側の結合データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return 結合マスタ履歴
   */
  @Override
  public List<JoinMasterCommon> searchJoinCommonShow(int makerCode, int logicDeleteDiv, int applyNo) {

    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(JoinMasterCommon.PARTS_MAKER_CD, makerCode);
    NumberFilter logicDeleteDivFilter = NumberFilterBuilder.equals(JoinMasterCommon.LOGICAL_DEL_DIV, logicDeleteDiv);
    NumberFilter applyNoFilter = NumberFilterBuilder.equals(JoinMasterCommon.APPLY_NO, applyNo);
    Filter filter = new AndFilter(makerCodeFilter, logicDeleteDivFilter, applyNoFilter);
    List<JoinMasterCommon> joinMasterCommonList = joinMasterCommonDao.findByFilter(filter, null);

    return joinMasterCommonList;
  }

  /**
   * データベースから、共有側のセットデータを検索する
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return セットマスタ履歴
   */
  @Override
  public List<SetMasterCommon> searchSetCommonShow(int makerCode, int logicDeleteDiv, int applyNo) {

    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(SetMasterCommon.PARTS_MAKER_CD, makerCode);
    NumberFilter logicDeleteDivFilter = NumberFilterBuilder.equals(SetMasterCommon.LOGICAL_DEL_DIV, logicDeleteDiv);
    NumberFilter applyNoFilter = NumberFilterBuilder.equals(SetMasterCommon.APPLY_NO, applyNo);
    Filter filter = new AndFilter(makerCodeFilter, logicDeleteDivFilter, applyNoFilter);
    List<SetMasterCommon> setMasterCommonList = setMasterCommonDao.findByFilter(filter, null);

    return setMasterCommonList;
  }

  /**
   * データベースから、共有側の商品履歴データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return 商品マスタ
   */
  @Override
  public List<GoodsMasterCommon> searchGoodsCommonJudge(int makerCode, int logicDeleteDiv, int applyNo) {

    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(GoodsMasterCommon.PARTS_MAKER_CD, makerCode);
    NumberFilter logicDeleteDivFilter = NumberFilterBuilder.equals(GoodsMasterCommon.LOGICAL_DEL_DIV, logicDeleteDiv);
    NumberFilter applyNoFilter = NumberFilterBuilder.lessThanIfNotNull(GoodsMasterCommon.APPLY_NO, applyNo);
    FieldDescOrder fieldDescOrder = new FieldDescOrder(GoodsMasterCommon.APPLY_NO);
    Filter filter = new AndFilter(makerCodeFilter, logicDeleteDivFilter, applyNoFilter);
    List<GoodsMasterCommon> goodsMasterCommonList = goodsMasterCommonDao.findByFilter(filter, null, fieldDescOrder);

    return goodsMasterCommonList;
  }

  /**
   * データベースから、共有側の結合履歴データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return 結合マスタ
   */
  @Override
  public List<JoinMasterCommon> searchJoinCommonJudge(int makerCode, int logicDeleteDiv, int applyNo) {

    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(JoinMasterCommon.PARTS_MAKER_CD, makerCode);
    NumberFilter logicDeleteDivFilter = NumberFilterBuilder.equals(JoinMasterCommon.LOGICAL_DEL_DIV, logicDeleteDiv);
    NumberFilter applyNoFilter = NumberFilterBuilder.lessThanIfNotNull(JoinMasterCommon.APPLY_NO, applyNo);
    FieldDescOrder fieldDescOrder = new FieldDescOrder(JoinMasterCommon.APPLY_NO);
    Filter filter = new AndFilter(makerCodeFilter, logicDeleteDivFilter, applyNoFilter);
    List<JoinMasterCommon> joinMasterCommonList = joinMasterCommonDao.findByFilter(filter, null, fieldDescOrder);

    return joinMasterCommonList;
  }

  /**
   * データベースから、共有側のセット履歴データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return セットマスタ
   */
  @Override
  public List<SetMasterCommon> searchSetCommonJudge(int makerCode, int logicDeleteDiv, int applyNo) {

    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(SetMasterCommon.PARTS_MAKER_CD, makerCode);
    NumberFilter logicDeleteDivFilter = NumberFilterBuilder.equals(SetMasterCommon.LOGICAL_DEL_DIV, logicDeleteDiv);
    NumberFilter applyNoFilter = NumberFilterBuilder.lessThanIfNotNull(SetMasterCommon.APPLY_NO, applyNo);
    FieldDescOrder fieldDescOrder = new FieldDescOrder(SetMasterCommon.APPLY_NO);
    Filter filter = new AndFilter(makerCodeFilter, logicDeleteDivFilter, applyNoFilter);
    List<SetMasterCommon> setMasterCommonList = setMasterCommonDao.findByFilter(filter, null, fieldDescOrder);

    return setMasterCommonList;
  }
  
  /**
   * 申請状態の取得
   * 
   * @param makerCode メーカーコード
   * @param applyNo 申請No
   * @return 申請状態
   */
  public List<ApplyHeadMasterCommon> searchApplyResult(int makerCode, int applyNo){
    
    NumberFilter makerCodeFilter = NumberFilterBuilder.equals(ApplyHeadMasterCommon.PARTS_MAKER_CD, makerCode);
    NumberFilter applyNoFilter = NumberFilterBuilder.equals(ApplyHeadMasterCommon.APPLY_NO, applyNo);
    Filter filter = new AndFilter(makerCodeFilter, applyNoFilter);
    List<ApplyHeadMasterCommon> applyHeadMasterCommon = applyHeadMasterCommonDao.findByFilter(filter, Limit.NO_LIMIT, null);
    return applyHeadMasterCommon;
  }

  /**
   * <pre>
   * 【goodsMasterCommonDao】を設定する。
   * </pre>
   *
   * @param goodsMasterCommonDao 【goodsMasterCommonDao】
   */
  @Resource
  public void setGoodsMasterCommonDao(GenericDao<GoodsMasterCommon, GoodsMasterCommonId> goodsMasterCommonDao) {
    this.goodsMasterCommonDao = goodsMasterCommonDao;
  }

  /**
   * <pre>
   * 【joinMasterCommonDao】を設定する。
   * </pre>
   *
   * @param joinMasterCommonDao 【joinMasterCommonDao】
   */
  @Resource
  public void setJoinMasterCommonDao(GenericDao<JoinMasterCommon, JoinMasterCommonId> joinMasterCommonDao) {
    this.joinMasterCommonDao = joinMasterCommonDao;
  }

  /**
   * <pre>
   * 【setMasterCommonDao】を設定する。
   * </pre>
   *
   * @param setMasterCommonDao 【setMasterCommonDao】
   */
  @Resource
  public void setSetMasterCommonDao(GenericDao<SetMasterCommon, SetMasterCommonId> setMasterCommonDao) {
    this.setMasterCommonDao = setMasterCommonDao;
  }

  /**
   * <pre>
   * 【goodsMasterMakerDao】を設定する。
   * </pre>
   *
   * @param goodsMasterMakerDao 【goodsMasterMakerDao】
   */
  @Resource
  public void setGoodsMasterMakerDao(GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterMakerDao) {
    this.goodsMasterMakerDao = goodsMasterMakerDao;
  }

  /**
   * <pre>
   * 【joinMasterMakerDao】を設定する。
   * </pre>
   *
   * @param joinMasterMakerDao 【joinMasterMakerDao】
   */
  @Resource
  public void setJoinMasterMakerDao(GenericDao<JoinMasterMaker, JoinMasterMakerId> joinMasterMakerDao) {
    this.joinMasterMakerDao = joinMasterMakerDao;
  }

  /**
   * <pre>
   * 【setMasterMakerDao】を設定する。
   * </pre>
   *
   * @param setMasterMakerDao 【setMasterMakerDao】
   */
  @Resource
  public void setSetMasterMakerDao(GenericDao<SetMasterMaker, SetMasterMakerId> setMasterMakerDao) {
    this.setMasterMakerDao = setMasterMakerDao;
  }
  
  /**
   * <pre>
   * 【applyHeadMasterCommonDao】を設定する。
   * </pre>
   *
   * @param applyHeadMasterCommonDao 【applyHeadMasterCommonDao】
   */
  @Resource
  public void setApplyHeadMasterCommonDao(GenericDao<ApplyHeadMasterCommon, ApplyHeadMasterCommonId> applyHeadMasterCommonDao) {
    this.applyHeadMasterCommonDao = applyHeadMasterCommonDao;
  }


}
