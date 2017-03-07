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
package jp.co.broadleaf.breg.applycommon.service.impl;

import jp.co.broadleaf.breg.applycommon.service.ApplyCommonService;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommonId;
import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;
import jp.co.broadleaf.breg.common.entity.GoodsRateMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommonId;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMakerId;
import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommonId;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommonId;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
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
 * </pre>
 */
public class ApplyCommonServiceImpl implements ApplyCommonService {

  /** 商品DAO */
  private GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterMakerDao;
  /** セットマスタ(メーカー)DAO */
  private GenericDao<SetMasterMaker, SetMasterMakerId> setMasterMakerDao;
  /** 結合マスタ(メーカー)DAO */
  private GenericDao<JoinMasterMaker, JoinMasterMakerId> joinMasterMakerDao;
  /** 結合DAO */
  private GenericDao<JoinMasterCommon, JoinMasterCommonId> joinMasterCommonDao;
  /** 会社情報マスタDAO */
  private GenericDao<CompanyInfoMasterCommon, Integer> companyInfoMasterCommonDao;
  /** 純正商品マスタDAO **/
  private GenericDao<PuregoodsMasterCommon, PuregoodsMasterCommonId> puregoodsMasterCommonDao;
  /** BLコードDAO **/
  private GenericDao<BlCodeMasterCommon, BlCodeMasterCommonId> blCodeMasterCommonDao;
  /** セレクトコードDAO **/
  private GenericDao<SelectCodeMasterCommon, SelectCodeMasterCommonId> selectCodeMasterCommonDao;
  /** BLコードDAO **/
  private GenericDao<GoodsRateMasterCommon, Integer> goodsRateMasterCommonDao;

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param importKbn インポート区分(メーカー)
   * @param applyCondition 申請状態(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @param isFromImport 論理boolean区分
   * @param isReApplication 論理boolean区分
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public List<GoodsMasterMaker> searchGoodsInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                                    Integer partsMakerCd, Integer logicalDeleteCode,
                                                    boolean isFromImport, boolean isReApplication) {
    NumberFilter ftImportKbn = NumberFilterBuilder.equals(GoodsMasterMaker.IMPORT_KBN, importKbn);
    NumberFilter ftApplyCondition = NumberFilterBuilder.equals(GoodsMasterMaker.APPLY_CONDITION, applyCondition);
    StringFilter ftUpdAccountId = null;
    if (isFromImport) {
      ftUpdAccountId = StringFilterBuilder.equals(GoodsMasterMaker.UPD_ACCOUNT_ID, updAccountId);
    } else {
      ftUpdAccountId = StringFilterBuilder.notEquals(GoodsMasterMaker.UPD_ACCOUNT_ID, updAccountId);
    }
    NumberFilter ftPartsMakerCd = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, partsMakerCd);
    NumberFilter ftLogicalDeleteCode = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV, logicalDeleteCode);
    Filter filter = new AndFilter(ftImportKbn, ftApplyCondition, ftUpdAccountId, ftPartsMakerCd, ftLogicalDeleteCode);
    if (isReApplication) {
      filter = new AndFilter(ftApplyCondition, ftUpdAccountId, ftPartsMakerCd, ftLogicalDeleteCode);
    }
    List<GoodsMasterMaker> itemMasterList = goodsMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
    return itemMasterList;
  }

  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param importKbn インポート区分(メーカー)
   * @param applyCondition 申請状態(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @param isFromImport 論理boolean区分
   * @param isReApplication 論理boolean区分
   * @return セットマスタ(メーカー)情報
   */
  @Override
  public List<SetMasterMaker> searchSetInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                                Integer partsMakerCd, Integer logicalDeleteCode, boolean isFromImport,
                                                boolean isReApplication) {
    NumberFilter ftImportKbn = NumberFilterBuilder.equals(SetMasterMaker.IMPORT_KBN, importKbn);
    NumberFilter ftApplyCondition = NumberFilterBuilder.equals(SetMasterMaker.APPLY_CONDITION, applyCondition);
    StringFilter ftUpdAccountId = null;
    if (isFromImport) {
      ftUpdAccountId = StringFilterBuilder.equals(SetMasterMaker.UPD_ACCOUNT_ID, updAccountId);
    } else {
      ftUpdAccountId = StringFilterBuilder.notEquals(SetMasterMaker.UPD_ACCOUNT_ID, updAccountId);
    }
    NumberFilter ftPartsMakerCd = NumberFilterBuilder.equals(SetMasterMaker.PARTS_MAKER_CD, partsMakerCd);
    NumberFilter ftLogicalDeleteCode = NumberFilterBuilder.equals(SetMasterMaker.LOGICAL_DEL_DIV, logicalDeleteCode);
    Filter filter = new AndFilter(ftImportKbn, ftApplyCondition, ftUpdAccountId, ftPartsMakerCd, ftLogicalDeleteCode);
    if (isReApplication) {
      filter = new AndFilter(ftApplyCondition, ftUpdAccountId, ftPartsMakerCd, ftLogicalDeleteCode);
    }
    List<SetMasterMaker> setMasterList = setMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
    return setMasterList;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)取得.
   * </pre>
   * 
   * @param importKbn インポート区分(メーカー)
   * @param applyCondition 申請状態(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @param isFromImport 論理boolean区分
   * @param isReApplication 論理boolean区分
   * @return 結合マスタ(メーカー)情報
   */
  @Override
  public List<JoinMasterMaker> searchJoinInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                                  Integer partsMakerCd, Integer logicalDeleteCode, boolean isFromImport,
                                                  boolean isReApplication) {
    NumberFilter ftImportKbn = NumberFilterBuilder.equals(JoinMasterMaker.IMPORT_KBN, importKbn);
    NumberFilter ftApplyCondition = NumberFilterBuilder.equals(JoinMasterMaker.APPLY_CONDITION, applyCondition);
    StringFilter ftUpdAccountId = null;
    if (isFromImport) {
      ftUpdAccountId = StringFilterBuilder.equals(JoinMasterMaker.UPD_ACCOUNT_ID, updAccountId);
    } else {
      ftUpdAccountId = StringFilterBuilder.notEquals(JoinMasterMaker.UPD_ACCOUNT_ID, updAccountId);
    }
    NumberFilter ftPartsMakerCd = NumberFilterBuilder.equals(JoinMasterMaker.PARTS_MAKER_CD, partsMakerCd);
    NumberFilter ftLogicalDeleteCode = NumberFilterBuilder.equals(JoinMasterMaker.LOGICAL_DEL_DIV, logicalDeleteCode);
    Filter filter = new AndFilter(ftImportKbn, ftApplyCondition, ftUpdAccountId, ftPartsMakerCd, ftLogicalDeleteCode);
    List<JoinMasterMaker> joinMasterList = joinMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
    return joinMasterList;
  }

  /**
   * <pre>
   * searchByExample
   * </pre>
   *
   * @param joinMaster JoinMasterCommon
   * @return List
   */
  @Override
  public List<JoinMasterCommon> searchByExample(JoinMasterCommon joinMaster) {
    return joinMasterCommonDao.findByExample(joinMaster);
  }

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param goodsMasterMakerDao 商品DAO
   */
  @Resource
  public void setGoodsMasterMakerDao(GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterMakerDao) {
    this.goodsMasterMakerDao = goodsMasterMakerDao;
  }

  /**
   * <pre>
   * セットマスタ(メーカー)DAOを設定する.
   * </pre>
   * 
   * @param setMasterMakerDao セットマスタ(メーカー)DAO
   */
  @Resource
  public void setSetMasterMakerDao(GenericDao<SetMasterMaker, SetMasterMakerId> setMasterMakerDao) {
    this.setMasterMakerDao = setMasterMakerDao;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)DAOを設定する.
   * </pre>
   * 
   * @param joinMasterMakerDao 結合マスタ(メーカー)DAO
   */
  @Resource
  public void setJoinMasterMakerDao(GenericDao<JoinMasterMaker, JoinMasterMakerId> joinMasterMakerDao) {
    this.joinMasterMakerDao = joinMasterMakerDao;
  }

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param joinMasterCommonDao 申請DAO
   */
  @Resource
  public void setJoinMasterCommonDao(GenericDao<JoinMasterCommon, JoinMasterCommonId> joinMasterCommonDao) {
    this.joinMasterCommonDao = joinMasterCommonDao;
  }

  /**
   * <pre>
   * 会社情報マスタを設定する。
   * </pre>
   *
   * @param companyInfoMasterCommonDao 会社情報マスタ
   */
  @Resource
  public void setCompanyInfoMasterCommonDao(GenericDao<CompanyInfoMasterCommon, Integer> companyInfoMasterCommonDao) {
    this.companyInfoMasterCommonDao = companyInfoMasterCommonDao;
  }

  /**
   * 【puregoodsMasterCommonDao】を設定する。
   *
   * @param puregoodsMasterCommonDao 【puregoodsMasterCommonDao】
   */
  @Resource
  public void setPuregoodsMasterCommonDao(GenericDao<PuregoodsMasterCommon, PuregoodsMasterCommonId> puregoodsMasterCommonDao) {
    this.puregoodsMasterCommonDao = puregoodsMasterCommonDao;
  }

  /**
   * 【blCodeMasterCommonDao】を設定する。
   *
   * @param blCodeMasterCommonDao 【blCodeMasterCommonDao】
   */
  @Resource
  public void setBlCodeMasterCommonDao(GenericDao<BlCodeMasterCommon, BlCodeMasterCommonId> blCodeMasterCommonDao) {
    this.blCodeMasterCommonDao = blCodeMasterCommonDao;
  }

  /**
   * 【selectCodeMasterCommonDao】を設定する。
   * 
   * @param selectCodeMasterCommonDao 【selectCodeMasterCommonDao】
   */
  @Resource
  public void setSelectCodeMasterCommonDao(GenericDao<SelectCodeMasterCommon, SelectCodeMasterCommonId> selectCodeMasterCommonDao) {
    this.selectCodeMasterCommonDao = selectCodeMasterCommonDao;
  }

  /**
   * 【goodsRateMasterCommonDao】を設定する。
   * 
   * @param goodsRateMasterCommonDao 【goodsRateMasterCommonDao】
   */
  @Resource
  public void setGoodsRateMasterCommonDao(GenericDao<GoodsRateMasterCommon, Integer> goodsRateMasterCommonDao) {
    this.goodsRateMasterCommonDao = goodsRateMasterCommonDao;
  }

  /**
   * <pre>
   * MailAddressを取得する.
   * </pre>
   * 
   * @param makerCode makerCode
   * @return blMailAddress
   */
  @Override
  public CompanyInfoMasterCommon getBlMailAdd(int makerCode) {
    CompanyInfoMasterCommon companyInfoMasterCommon = new CompanyInfoMasterCommon();
    companyInfoMasterCommon = companyInfoMasterCommonDao.findById(makerCode);
    return companyInfoMasterCommon;
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param logicalDelDiv int
   * @param prmSetDtlNo1 int
   * @param partsMakerCd int
   * @param primePartsNoWithH String
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public List<GoodsMasterMaker> searchGoods(int logicalDelDiv, int prmSetDtlNo1, int partsMakerCd,
                                            String primePartsNoWithH) {
    NumberFilter ftLogicalDelDiv = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV, logicalDelDiv);
    NumberFilter ftPrmSetDtlNo1 = NumberFilterBuilder.equals(GoodsMasterMaker.PRM_SET_DTL_NO_1, prmSetDtlNo1);
    NumberFilter ftPartsMakerCd = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, partsMakerCd);
    StringFilter ftPrimePartsNoWithH = StringFilterBuilder.equals(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H,
        primePartsNoWithH);
    Filter filter = new AndFilter(ftLogicalDelDiv, ftPrmSetDtlNo1, ftPartsMakerCd, ftPrimePartsNoWithH);
    List<GoodsMasterMaker> goods = goodsMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
    return goods;
  }

  /**
   * <pre>
   * 純正品番取得.
   * </pre>
   * 
   * @param prmSetDtlNo1 int
   * @param partsMakerCd int
   * @param joinSourceMakerCode int
   * @param joinSourPartsNoWithH String
   * @return 商品マスタ(メーカー)情報
   */
  public List<PuregoodsMasterCommon> searchPureGoods(int prmSetDtlNo1, int partsMakerCd, int joinSourceMakerCode,
                                                     String joinSourPartsNoWithH) {
    NumberFilter partsMakerCdFilter = NumberFilterBuilder.equals(PuregoodsMasterCommon.PARTS_MAKER_CD, partsMakerCd);
    NumberFilter joinSourceMakerCodeFilter = NumberFilterBuilder.equals(PuregoodsMasterCommon.JOIN_SOURCE_MAKER_CODE,
        joinSourceMakerCode);
    StringFilter primePartsNoWithHFilter = StringFilterBuilder.equals(PuregoodsMasterCommon.PRIME_PARTS_NO_WITH_H,
        joinSourPartsNoWithH);
    Filter pureFilter = new AndFilter(partsMakerCdFilter, joinSourceMakerCodeFilter, primePartsNoWithHFilter);
    return puregoodsMasterCommonDao.findByFilter(pureFilter, Limit.NO_LIMIT);
  }

  /**
   * <pre>
   * 純正品番取得.
   * </pre>
   * 
   * @param prmSetDtlNo1 int
   * @param partsMakerCd int
   * @param joinSourceMakerCode int
   * @return 純正品番情報
   */
  @Override
  public List<PuregoodsMasterCommon> searchPureGoodsByPartsMakerCd(int prmSetDtlNo1, int partsMakerCd,
                                                                   int joinSourceMakerCode) {
    NumberFilter partsMakerCdFilter = NumberFilterBuilder.equals(PuregoodsMasterCommon.PARTS_MAKER_CD, partsMakerCd);
    NumberFilter joinSourceMakerCodeFilter = NumberFilterBuilder.equals(PuregoodsMasterCommon.JOIN_SOURCE_MAKER_CODE,
        joinSourceMakerCode);
    Filter pureFilter = new AndFilter(partsMakerCdFilter, joinSourceMakerCodeFilter);
    return puregoodsMasterCommonDao.findByFilter(pureFilter, Limit.NO_LIMIT);
  }

  /**
   * <pre>
   * BLコードマスタ取得.
   * </pre>
   * 
   * @param joinSourceMakerCode int
   * @param tbsPartsCode int
   * @return BLコードマスタ情報
   */
  @Override
  public BlCodeMasterCommon searchBlCodeMasterCommonById(int joinSourceMakerCode, int tbsPartsCode) {
    BlCodeMasterCommonId blCodeMasterCommonId = new BlCodeMasterCommonId(joinSourceMakerCode, tbsPartsCode);
    return blCodeMasterCommonDao.findById(blCodeMasterCommonId);
  }

  /**
   * <pre>
   * セレクトコードマスト取得.
   * </pre>
   * 
   * @param partsMakerCd int
   * @return セレクトコードマスト情報
   */
  @Override
  public List<SelectCodeMasterCommon> searchSelectCodeMaster(int partsMakerCd) {
    NumberFilter selectMakerCd = NumberFilterBuilder.equals(SelectCodeMasterCommon.GOODS_MAKER_CD, partsMakerCd);
    Filter selectFilter = new AndFilter(selectMakerCd);
    return selectCodeMasterCommonDao.findByFilter(selectFilter, Limit.NO_LIMIT);
  }

  /**
   * <pre>
   * 商品中分類マスタ取得.
   * </pre>
   * 
   * @param partsMakerCd int
   * @return 商品中分類マスタ情報
   */
  @Override
  public List<GoodsRateMasterCommon> searchGoodsRateMaster(int partsMakerCd) {
    NumberFilter selectMakerCd = NumberFilterBuilder.equals(GoodsRateMasterCommon.GOODS_MAKER, partsMakerCd);
    Filter goodsRateFilter = new AndFilter(selectMakerCd);
    return goodsRateMasterCommonDao.findByFilter(goodsRateFilter, Limit.NO_LIMIT);
  }

  /**
   * <pre>
   * 結合マスタ取得.
   * </pre>
   * 
   * @param prmSetDtlNo1 int
   * @param partsMakerCd int
   * @param tbsPartsCode int
   * @param joinSourceMakerCode int
   * @param joinSourPartsNoWithH String
   * @return 結合マスタ情報
   */
  @Override
  public List<JoinMasterMaker> searchJoinByKeys(int prmSetDtlNo1, int partsMakerCd, int tbsPartsCode,
                                                int joinSourceMakerCode, String joinSourPartsNoWithH) {
    NumberFilter tfPrmSetDtlNo1 = NumberFilterBuilder.equals(JoinMasterMaker.PRM_SET_DTL_NO_1, prmSetDtlNo1);
    NumberFilter tfPartsMakerCd = NumberFilterBuilder.equals(JoinMasterMaker.PARTS_MAKER_CD, partsMakerCd);
    NumberFilter tfTbsPartsCode = NumberFilterBuilder.equals(JoinMasterMaker.TBS_PARTS_CODE, tbsPartsCode);
    NumberFilter tfJoinSourceMakerCode = NumberFilterBuilder.equals(JoinMasterMaker.JOIN_SOURCE_MAKER_CODE,
        joinSourceMakerCode);
    StringFilter tfJoinSourPartsNoWithH = StringFilterBuilder.equals(JoinMasterMaker.JOIN_SOUR_PARTS_NO_WITH_H,
        joinSourPartsNoWithH);
    Filter filter = new AndFilter(tfPrmSetDtlNo1,tfPartsMakerCd,tfTbsPartsCode,tfJoinSourceMakerCode,tfJoinSourPartsNoWithH);
    return joinMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
  }

  /**
   * <pre>
   * BLコードマスタ取得.
   * </pre>
   * 
   * @param partsMakerCd int
   * @return BLコードマスタ情報
   */
  @Override
  public List<BlCodeMasterCommon> searchBlCodeMaster(int partsMakerCd) {
    NumberFilter selectMakerCd = NumberFilterBuilder.equals(BlCodeMasterCommon.GOODS_MAKER, partsMakerCd);
    Filter blCodeFilter = new AndFilter(selectMakerCd);
    return blCodeMasterCommonDao.findByFilter(blCodeFilter, Limit.NO_LIMIT);
  }

}
