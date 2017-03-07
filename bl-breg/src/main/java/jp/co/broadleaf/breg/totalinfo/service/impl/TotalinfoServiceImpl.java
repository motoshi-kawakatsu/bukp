//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 司　志超
// 作 成 日       2017/02/08     修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.totalinfo.service.impl;

import jp.co.broadleaf.breg.applyheadmastercommon.domain.model.ApplyHeadMasterCommonModel;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommon;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommonId;
import jp.co.broadleaf.breg.common.entity.GoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterCommonId;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommonId;
import jp.co.broadleaf.breg.common.entity.SetMasterCommon;
import jp.co.broadleaf.breg.common.entity.SetMasterCommonId;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterCommonModel;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterCommonModel;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterCommonModel;
import jp.co.broadleaf.breg.totalinfo.enums.FilterObjectEnum;
import jp.co.broadleaf.breg.totalinfo.service.TotalinfoService;
import jp.co.broadleaf.common.util.BroadleafStringUtils;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.FieldDescOrder;
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
 * 累積情報Serviceクラス.
 * </pre>
 */
public class TotalinfoServiceImpl implements TotalinfoService {
  
  /** 申請状態DAO */
  private GenericDao<ApplyHeadMasterCommon, ApplyHeadMasterCommonId> applyHeadMasterCommonDao;

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param applyHeadMasterCommonDao 申請状態DAO
   */
  @Resource
  public void setApplyHeadMasterCommonDao(GenericDao<ApplyHeadMasterCommon, ApplyHeadMasterCommonId> applyHeadMasterCommonDao) {
    this.applyHeadMasterCommonDao = applyHeadMasterCommonDao;
  }

  /** 商品DAO */
  private GenericDao<GoodsMasterCommon, GoodsMasterCommonId> goodsMasterCommonDao;

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param goodsMasterCommonDao 商品DAO
   */
  @Resource
  public void setGoodsMasterCommonDao(GenericDao<GoodsMasterCommon, GoodsMasterCommonId> goodsMasterCommonDao) {
    this.goodsMasterCommonDao = goodsMasterCommonDao;
  }

  /** セットマスタDAO */
  private GenericDao<SetMasterCommon, SetMasterCommonId> setMasterCommonDao;

  /**
   * <pre>
   * セットマスタDAOを設定する.
   * </pre>
   * 
   * @param setMasterCommonDao セットマスタDAO
   */
  @Resource
  public void setSetMasterCommonDao(GenericDao<SetMasterCommon, SetMasterCommonId> setMasterCommonDao) {
    this.setMasterCommonDao = setMasterCommonDao;
  }

  /** 結合マスタDAO */
  private GenericDao<JoinMasterCommon, JoinMasterCommonId> joinMasterCommonDao;

  /**
   * <pre>
   * 結合マスタDAOを設定する.
   * </pre>
   * 
   * @param joinMasterCommonDao  結合マスタDAO
   */
  @Resource
  public void setJoinMasterCommonDao(GenericDao<JoinMasterCommon, JoinMasterCommonId> joinMasterCommonDao) {
    this.joinMasterCommonDao = joinMasterCommonDao;
  }

  /**
   * <pre>
   * 申請状態取得.
   * </pre>
   * 
   * @return 申請状態情報
   */
  @Override
  public ApplyHeadMasterCommonModel searchApplyHeadMasterCommonList() {
    
    ApplyHeadMasterCommonModel headMasterCommonModel = new ApplyHeadMasterCommonModel();
    
    NumberFilter blCode = NumberFilterBuilder.equals(ApplyHeadMasterCommon.LOGICAL_DEL_DIV, 0);
    
    Filter filter = new AndFilter(blCode);
    Order orderby;
    FieldOrder blCodeOrder = new FieldDescOrder(ApplyHeadMasterCommon.APPLY_NO);
    FieldDescOrder applyResultsFlgOrder = new FieldDescOrder(ApplyHeadMasterCommon.BL_APPLY_RESULTS_FLG);
    // 画面の優良品番はテープルにありません
    orderby = new MultiFieldOrder(blCodeOrder, applyResultsFlgOrder);
    List<ApplyHeadMasterCommon> headMasterList = applyHeadMasterCommonDao.findByFilter(filter, Limit.NO_LIMIT, orderby);
    headMasterCommonModel.setApplyHeadMasterList(headMasterList);
    
    return headMasterCommonModel;
    
  }
  
  /**
   * <pre>
   * 商品マスタ取得.
   * </pre>
   * @param logicalDelDiv 論理削除区分
   * @param filterMode filterモード
   * @param tbsPartsCode BLコード
   * @param partsMakerCd カーコード
   * @param primePartsNoWithH 優良品番
   * @return 商品マスタ情報
   */
  @Override
  public GoodsMasterCommonModel searchItemMasterList(int logicalDelDiv,int filterMode,
         String tbsPartsCode,String partsMakerCd,String primePartsNoWithH) {
    GoodsMasterCommonModel itemMasterModel = new GoodsMasterCommonModel();
    
    NumberFilter logicDelDiv = NumberFilterBuilder.equals(GoodsMasterCommon.LOGICAL_DEL_DIV, logicalDelDiv);
    Filter filter = new AndFilter(logicDelDiv);
    FieldOrder firstOrder = null;
    if (FilterObjectEnum.TbsPartsCode.getValue() == filterMode) {
      // BLコードの場合
      firstOrder = new FieldDescOrder(GoodsMasterCommon.TBS_PARTS_CODE);
    } else if (FilterObjectEnum.PartsMakerCd.getValue() == filterMode) {
      // カーコードの場合
      firstOrder = new FieldDescOrder(GoodsMasterCommon.PARTS_MAKER_CD);
    } else if (FilterObjectEnum.PrimePartsNoWithH.getValue() == filterMode) {
      // 優良品番の場合
      firstOrder = new FieldDescOrder(GoodsMasterCommon.PRIME_PARTS_NO_WITH_H);
    }else{
      if (!BroadleafStringUtils.isEmpty(tbsPartsCode)) {
        firstOrder = new FieldDescOrder(GoodsMasterCommon.TBS_PARTS_CODE);
      }
      if (!BroadleafStringUtils.isEmpty(partsMakerCd)) {
        firstOrder = new FieldDescOrder(GoodsMasterCommon.PARTS_MAKER_CD);
      }
      if (!BroadleafStringUtils.isEmpty(primePartsNoWithH)) {
        firstOrder = new FieldDescOrder(GoodsMasterCommon.PRIME_PARTS_NO_WITH_H);
      }
      
    }
    Order orderby;
    FieldOrder applyNoOrder = new FieldDescOrder(GoodsMasterCommon.APPLY_NO);
    // 画面の優良品番はテープルにありません
    orderby = new MultiFieldOrder(firstOrder,applyNoOrder);
    List<GoodsMasterCommon> itemMasterList = goodsMasterCommonDao.findByFilter(filter, Limit.NO_LIMIT, orderby);
    itemMasterModel.setGoodsList(itemMasterList);
    return itemMasterModel;
  }

  /**
   * <pre>
   * セットマスタ取得.
   * </pre>
   * @param logicalDelDiv 論理削除区分
   * @param filterMode filterモード
   * @return セットマスタ
   */
  @Override
  public SetMasterCommonModel searchSetMasterInfoList(int logicalDelDiv,int filterMode) {
    SetMasterCommonModel setMasterModel = new SetMasterCommonModel();
    
    NumberFilter logicDelDiv = NumberFilterBuilder.equals(SetMasterCommon.LOGICAL_DEL_DIV, logicalDelDiv);
    Filter filter = new AndFilter(logicDelDiv);
    FieldOrder firstOrder = new FieldDescOrder(SetMasterCommon.TBS_PARTS_CODE);
    if (FilterObjectEnum.TbsPartsCode.getValue() == filterMode) {
      // BLコードの場合
      firstOrder = new FieldDescOrder(SetMasterCommon.TBS_PARTS_CODE);
    } else if (FilterObjectEnum.PartsMakerCd.getValue() == filterMode) {
      // カーコードの場合
      firstOrder = new FieldDescOrder(SetMasterCommon.PARTS_MAKER_CD);
    } else if (FilterObjectEnum.PrimePartsNoWithH.getValue() == filterMode) {
      // 優良品番の場合 
      firstOrder = new FieldDescOrder(SetMasterCommon.SET_MAIN_PARTS_NO);
    }
    Order orderby;
    FieldOrder applyNoOrder = new FieldDescOrder(SetMasterCommon.APPLY_NO);
    // 画面の優良品番はテープルにありません
    orderby = new MultiFieldOrder(firstOrder,applyNoOrder);
    List<SetMasterCommon> setMasterList = setMasterCommonDao.findByFilter(filter, Limit.NO_LIMIT, orderby);
    setMasterModel.setSetMasterCommonList(setMasterList);
    return setMasterModel;
  }

  /**
   * <pre>
   * 結合マスタの全データ取得.
   * </pre>
   * @param logicalDelDiv 論理削除区分
   * @param filterMode filterモード
   * @return 結合マスタの全データ
   */
  @Override
  public JoinMasterCommonModel searchJoinMasterInfoList(int logicalDelDiv,int filterMode) {
    JoinMasterCommonModel joinMasterModel = new JoinMasterCommonModel();
    
    NumberFilter logicDelDiv = NumberFilterBuilder.equals(JoinMasterCommon.LOGICAL_DEL_DIV, logicalDelDiv);
    Filter filter = new AndFilter(logicDelDiv);
    FieldOrder firstOrder = new FieldDescOrder(JoinMasterCommon.TBS_PARTS_CODE);
    if (FilterObjectEnum.TbsPartsCode.getValue() == filterMode) {
      // BLコードの場合
      firstOrder = new FieldDescOrder(JoinMasterCommon.TBS_PARTS_CODE);
    } else if (FilterObjectEnum.PartsMakerCd.getValue() == filterMode) {
      // カーコードの場合
      firstOrder = new FieldDescOrder(JoinMasterCommon.PARTS_MAKER_CD);
    } else if (FilterObjectEnum.PrimePartsNoWithH.getValue() == filterMode) {
      // 優良品番の場合
      firstOrder = new FieldDescOrder(JoinMasterCommon.JOIN_DEST_PARTS_NO);
    }else if(FilterObjectEnum.JoinSourPartsNoWithH.getValue() == filterMode){
      // 純正品番の場合
      firstOrder = new FieldDescOrder(JoinMasterCommon.JOIN_SOUR_PARTS_NO_WITH_H);
    }
    Order orderby;
    FieldOrder applyNoOrder = new FieldDescOrder(JoinMasterCommon.APPLY_NO);
    // 画面の優良品番はテープルにありません
    orderby = new MultiFieldOrder(firstOrder,applyNoOrder);
    List<JoinMasterCommon> joinMasterList = joinMasterCommonDao.findByFilter(filter, Limit.NO_LIMIT, orderby);
    joinMasterModel.setJoinMasterCommonList(joinMasterList);
    
    return joinMasterModel;
  }
  

}
