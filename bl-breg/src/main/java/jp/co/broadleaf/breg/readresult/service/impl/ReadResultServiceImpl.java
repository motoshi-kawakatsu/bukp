//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//(c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号       作成担当 : 羅　進博
// 作 成 日       2017/02/08   修正内容 : 取込完了：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.readresult.service.impl;

import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMakerId;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.readresult.service.ReadResultService;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;

import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 取込完了サービスクラス.
 * </pre>
 */
public class ReadResultServiceImpl implements ReadResultService {
  /** goodsMasterMakerDao　商品マスタ(メーカー)本*/
  private GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterMakerDao;
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

  /** setMasterMakerDao　セットマスタ(メーカー)本*/
  private GenericDao<SetMasterMaker, SetMasterMakerId> setMasterMakerDao;
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

  /** joinMasterMakerDao　結合マスタ(メーカー)本*/
  private GenericDao<JoinMasterMaker, JoinMasterMakerId> joinMasterMakerDao;
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
   * 商品の情報をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @param manageStatus Integer
   * @return goodsInfo Integer
   */
  @Override
  public int getGoodsInfo(int loginUserCd, int manageStatus) {
    /** partsMakerCd = loginUserCd*/
    NumberFilter makerCdFilter = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, loginUserCd);
    /** 処理区分*/
    NumberFilter managerFilter = NumberFilterBuilder.equals(GoodsMasterMaker.MANAGE_KBN , manageStatus);
    /** 論理削除区分＝0:有効*/
    NumberFilter logicalDelDiv = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV, 0);
    /** 申請状態＝0:未申請*/
    NumberFilter applyCondition = NumberFilterBuilder.equals(GoodsMasterMaker.APPLY_CONDITION, ApplyConditionEnum.NoApply.getValue());
    /** インポート区分＝0:インポート（一括申請）*/
    NumberFilter importTypeFilter = NumberFilterBuilder.equals(GoodsMasterMaker.IMPORT_KBN, ImportKbnEnum.ImportApp.getValue());
    Filter filter = new AndFilter(makerCdFilter, logicalDelDiv, applyCondition, managerFilter, importTypeFilter);
    List<GoodsMasterMaker> goodsMasterList = goodsMasterMakerDao.findByFilter(filter, null);
    int goodsInfo = goodsMasterList.size();
    return goodsInfo;
  }

  /**
   * <pre>
   * セットの情報をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @param manageStatus Integer
   * @return goodsInfo Integer
   */
  @Override
  public int getSetInfo(int loginUserCd, int manageStatus) {
    /** 処理区分*/
    NumberFilter managerFilter = NumberFilterBuilder.equals(SetMasterMaker.MANAGE_KBN , manageStatus);
    /** partsMakerCd = loginUserCd*/
    NumberFilter makerCdFilter = NumberFilterBuilder.equals(SetMasterMaker.PARTS_MAKER_CD, loginUserCd);
    /** 論理削除区分＝0:有効*/
    NumberFilter logicalDelDiv = NumberFilterBuilder.equals(SetMasterMaker.LOGICAL_DEL_DIV, 0);
    /** 申請状態＝0:未申請*/
    NumberFilter applyCondition = NumberFilterBuilder.equals(SetMasterMaker.APPLY_CONDITION, ApplyConditionEnum.NoApply.getValue());
    /** インポート区分＝0:インポート（一括申請）*/
    NumberFilter importTypeFilter = NumberFilterBuilder.equals(GoodsMasterMaker.IMPORT_KBN, ImportKbnEnum.ImportApp.getValue());
    Filter filter = new AndFilter(makerCdFilter, logicalDelDiv,applyCondition, managerFilter, importTypeFilter);
    List<SetMasterMaker> setMasterList = setMasterMakerDao.findByFilter(filter, null);
    int setInfo = setMasterList.size();
    return setInfo;
  }

  /**
   * <pre>
   * 結合の情報をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @param manageStatus Integer
   * @return goodsInfo Integer
   */
  @Override
  public int getJoinInfo(int loginUserCd, int manageStatus) {
    /** 処理区分*/
    NumberFilter managerFilter = NumberFilterBuilder.equals(JoinMasterMaker.MANAGE_KBN , manageStatus);
    /** partsMakerCd = loginUserCd*/
    NumberFilter makerCdFilter = NumberFilterBuilder.equals(JoinMasterMaker.PARTS_MAKER_CD, loginUserCd);
    /** 論理削除区分＝0:有効*/
    NumberFilter logicalDelDiv = NumberFilterBuilder.equals(JoinMasterMaker.LOGICAL_DEL_DIV, 0);
    /** 申請状態＝0:未申請*/
    NumberFilter applyCondition = NumberFilterBuilder.equals(JoinMasterMaker.APPLY_CONDITION, ApplyConditionEnum.NoApply.getValue());
    /** インポート区分＝0:インポート（一括申請）*/
    NumberFilter importTypeFilter = NumberFilterBuilder.equals(GoodsMasterMaker.IMPORT_KBN, ImportKbnEnum.ImportApp.getValue());
    Filter filter = new AndFilter(makerCdFilter, logicalDelDiv,applyCondition , managerFilter, importTypeFilter);
    List<JoinMasterMaker> joinMasterList = joinMasterMakerDao.findByFilter(filter, null);
    int joinInfo = joinMasterList.size();
    return joinInfo;
  }

}
