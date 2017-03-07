//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : wangnan
// 作 成 日       2017/02/27   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.selectmaker.service.impl;

import jp.co.broadleaf.breg.common.entity.SelectMaker;
import jp.co.broadleaf.breg.common.entity.SelectMakerId;
import jp.co.broadleaf.breg.selectmaker.service.SelectMakerService;
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
 *  チェック選択サービスクラス.
 * </pre>
 */
public class SelectMakerServiceImpl implements SelectMakerService {

  /** チェック選択DAO */
  private GenericDao<SelectMaker, SelectMakerId> selectMakerDao;

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param selectMakerDaoNew チェック選択DAO
   */
  @Resource
  public void setSelectMakerDao(GenericDao<SelectMaker, SelectMakerId> selectMakerDaoNew) {
    this.selectMakerDao = selectMakerDaoNew;
  }

  /**
   * <pre>
   * チェック選択に商品情報取得.
   * </pre>
   * 
   * @param gPrmSetDtlNo1 商品_優良設定詳細コード１
   * @param gPartsMakerCd 商品_部品メーカーコード
   * @param gPrimePartsNoWithH 商品_優良品番(－付き品番)
   * @return チェック選択情報
   */
  @Override
  public SelectMaker searchGoodsById(int gPrmSetDtlNo1, int gPartsMakerCd, String gPrimePartsNoWithH) {
    SelectMaker selectMaker = new SelectMaker();

    // 優良品番(－付き品番) = 引数.優良品番(－付き品番)
    StringFilter primePartsNoWithH = StringFilterBuilder.equalsIfNotNull(SelectMaker.G_PRIME_PARTS_NO_WITH_H,
        gPrimePartsNoWithH);
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equalsIfNotNull(SelectMaker.G_PRM_SET_DTL_NO_1, gPrmSetDtlNo1);
    // 部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(SelectMaker.G_PARTS_MAKER_CD, gPartsMakerCd);
    // 区分
    NumberFilter selectKbn = NumberFilterBuilder.equalsIfNotNull(SelectMaker.SELECT_KBN, 0);

    Filter filter = new AndFilter(primePartsNoWithH, prmSetDtlNo1, partsMakerCd, selectKbn);
    selectMaker = selectMakerDao.findOneByFilter(filter);
    return selectMaker;
  }

  /**
   * <pre>
   * チェック選択にセット情報取得.
   * </pre>
   * 
   * @param sPrmSetDtlNo1 優良設定詳細コード１
   * @param sPartsMakerCd 部品メーカーコード
   * @param setMainPartsNo セット親品番
   * @param setDispOrder セット表示順位
   * @return チェック選択情報
   */
  @Override
  public SelectMaker searchSetById(int sPrmSetDtlNo1, int sPartsMakerCd, String setMainPartsNo, int setDispOrder) {
    SelectMaker selectMaker = new SelectMaker();

    // セット親品番 = 引数.セット親品番
    StringFilter setMainPartsNofilter = StringFilterBuilder.equalsIfNotNull(SelectMaker.S_SET_MAIN_PARTS_NO,
        setMainPartsNo);
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    NumberFilter prmSetDtlNo1filter = NumberFilterBuilder.equalsIfNotNull(SelectMaker.S_PRM_SET_DTL_NO_1,
        sPrmSetDtlNo1);
    // 部品メーカーコード
    NumberFilter partsMakerCdfilter = NumberFilterBuilder.equalsIfNotNull(SelectMaker.S_PARTS_MAKER_CD, sPartsMakerCd);
    // セット表示順位
    NumberFilter setDispOrderfilter = NumberFilterBuilder.equalsIfNotNull(SelectMaker.S_SET_DISP_ORDER, setDispOrder);
    // 区分
    NumberFilter selectKbn = NumberFilterBuilder.equalsIfNotNull(SelectMaker.SELECT_KBN, 1);

    Filter filter = new AndFilter(setMainPartsNofilter, prmSetDtlNo1filter, partsMakerCdfilter, setDispOrderfilter,
        selectKbn);
    selectMaker = selectMakerDao.findOneByFilter(filter);
    return selectMaker;
  }

  /**
   * <pre>
   * チェック選択に結合情報取得.
   * </pre>
   * 
   * @param jPrmSetDtlNo1 結合_優良設定詳細コード１
   * @param jPartsMakerCd 結合_部品メーカーコード
   * @param jTbsPartsCode 結合_BLコード
   * @param jJoinSourceMakerCode 結合_結合元メーカーコード
   * @param jPrmSetDtlNo2 結合_優良設定詳細コード２
   * @param jJoinSourPartsNoWithH 結合_結合元品番(－付き品番)
   * @param jJoinDispOrder 結合_結合表示順位
   * @return チェック選択情報
   */
  @Override
  public SelectMaker searchJoinById(int jPrmSetDtlNo1, int jPartsMakerCd, int jTbsPartsCode, int jJoinSourceMakerCode,
                                    int jPrmSetDtlNo2, String jJoinSourPartsNoWithH, int jJoinDispOrder) {
    SelectMaker selectMaker = new SelectMaker();
    // 結合_優良設定詳細コード１
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equalsIfNotNull(SelectMaker.J_PRM_SET_DTL_NO_1, jPrmSetDtlNo1);
    // 結合_部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(SelectMaker.J_PARTS_MAKER_CD, jPartsMakerCd);
    // 結合_BLコード
    NumberFilter tbsPartsCode = NumberFilterBuilder.equalsIfNotNull(SelectMaker.J_TBS_PARTS_CODE, jTbsPartsCode);
    // 結合_結合元メーカーコード
    NumberFilter joinSourceMakerCode = NumberFilterBuilder.equalsIfNotNull(SelectMaker.J_JOIN_SOURCE_MAKER_CODE,
        jJoinSourceMakerCode);
    // 結合_優良設定詳細コード２
    NumberFilter prmSetDtlNo2 = NumberFilterBuilder.equalsIfNotNull(SelectMaker.J_PRM_SET_DTL_NO_2, jPrmSetDtlNo2);
    // 結合_結合元品番(－付き品番)
    StringFilter joinSourPartsNoWithH = StringFilterBuilder.equalsIfNotNull(SelectMaker.J_JOIN_SOUR_PARTS_NO_WITH_H,
        jJoinSourPartsNoWithH);
    // 結合_結合表示順位
    NumberFilter joinDispOrder = NumberFilterBuilder.equalsIfNotNull(SelectMaker.J_JOIN_DISP_ORDER, jJoinDispOrder);
    // 区分
    NumberFilter selectKbn = NumberFilterBuilder.equalsIfNotNull(SelectMaker.SELECT_KBN, 2);

    Filter filter = new AndFilter(prmSetDtlNo1, partsMakerCd, tbsPartsCode, joinSourceMakerCode, prmSetDtlNo2,
        joinSourPartsNoWithH, joinDispOrder, selectKbn);
    selectMaker = selectMakerDao.findOneByFilter(filter);
    return selectMaker;
  }

  /**
   * <pre>
   * チェック選択に商品情報削除.
   * </pre>
   * 
   * @param gPrmSetDtlNo1 商品_優良設定詳細コード１
   * @param gPartsMakerCd 商品_部品メーカーコード
   * @param gPrimePartsNoWithH 商品_優良品番(－付き品番)
   */
  @Override
  public void deleteGoodsById(int gPrmSetDtlNo1, int gPartsMakerCd, String gPrimePartsNoWithH) {
    SelectMaker selectMaker = searchGoodsById(gPrmSetDtlNo1, gPartsMakerCd, gPrimePartsNoWithH);
    if (selectMaker != null) {
      selectMakerDao.delete(selectMaker);
    }
  }

  /**
   * <pre>
   * チェック選択にセット情報削除.
   * </pre>
   * 
   * @param gPrmSetDtlNo1 優良設定詳細コード１
   * @param gPartsMakerCd 部品メーカーコード
   * @param setMainPartsNo セット親品番
   * @param setDispOrder セット表示順位
   */
  @Override
  public void deleteSetById(int sPrmSetDtlNo1, int sPartsMakerCd, String setMainPartsNo, int setDispOrder) {
    SelectMaker selectMaker = searchSetById(sPrmSetDtlNo1, sPartsMakerCd, setMainPartsNo, setDispOrder);
    if (selectMaker != null) {
      selectMakerDao.delete(selectMaker);
    }
  }

  /**
   * <pre>
   * チェック選択に結合情報削除.
   * </pre>
   * 
   * @param jPrmSetDtlNo1 結合_優良設定詳細コード１
   * @param jPartsMakerCd 結合_部品メーカーコード
   * @param jTbsPartsCode 結合_BLコード
   * @param jJoinSourceMakerCode 結合_結合元メーカーコード
   * @param jPrmSetDtlNo2 結合_優良設定詳細コード２
   * @param jJoinSourPartsNoWithH 結合_結合元品番(－付き品番)
   * @param jJoinDispOrder 結合_結合表示順位
   */
  @Override
  public void deleteJoinById(int jPrmSetDtlNo1, int jPartsMakerCd, int jTbsPartsCode, int jJoinSourceMakerCode,
                             int jPrmSetDtlNo2, String jJoinSourPartsNoWithH, int jJoinDispOrder) {
    SelectMaker selectMaker = searchJoinById(jPrmSetDtlNo1, jPartsMakerCd, jTbsPartsCode, jJoinSourceMakerCode,
        jPrmSetDtlNo2, jJoinSourPartsNoWithH, jJoinDispOrder);
    if (selectMaker != null) {
      selectMakerDao.delete(selectMaker);
    }
  }

  /**
   * <pre>
   * チェック選択に商品情報登録
   * </pre>
   * 
   * @param selectMaker チェック選択情報
   */
  @Override
  public void insertGoodsById(SelectMaker selectMaker) {
    SelectMaker selectMakerNew = searchGoodsById(selectMaker.getGPrmSetDtlNo1(), selectMaker.getGPartsMakerCd(),
        selectMaker.getGPrimePartsNoWithH());
    if (selectMakerNew == null) {
      selectMakerDao.insert(selectMaker);
    }
  }

  /**
   * <pre>
   * チェック選択に商品情報登録
   * </pre>
   * 
   * @param selectMaker チェック選択情報
   */
  @Override
  public void insertSetById(SelectMaker selectMaker) {
    SelectMaker selectMakerNew = searchSetById(selectMaker.getSPrmSetDtlNo1(), selectMaker.getSPartsMakerCd(),
        selectMaker.getSSetMainPartsNo(), selectMaker.getSSetDispOrder());
    if (selectMakerNew == null) {
      selectMakerDao.insert(selectMaker);
    }
  }

  /**
   * <pre>
   * チェック選択に結合情報登録
   * </pre>
   * 
   * @param selectMaker チェック選択情報
   */
  @Override
  public void insertJoinById(SelectMaker selectMaker) {
    SelectMaker selectMakerNew = searchJoinById(selectMaker.getJPrmSetDtlNo1(), selectMaker.getJPartsMakerCd(),
        selectMaker.getJTbsPartsCode(), selectMaker.getJJoinSourceMakerCode(), selectMaker.getJPrmSetDtlNo2(),
        selectMaker.getJJoinSourPartsNoWithH(), selectMaker.getJJoinDispOrder());
    if (selectMakerNew == null) {
      selectMakerDao.insert(selectMaker);
    }
  }

  /**
   * <pre>
   * チェック選択に情報取得.
   * </pre>
   * 
   * @param selectKbn セットする区分
   * @param partsMakerCd ログインのメーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @return チェック選択情報
   */
  @Override
  public List<SelectMaker> searchBySelectKbn(int selectKbn, int partsMakerCd, int logicalDeleteCode) {
    // 区分
    NumberFilter selectDiv = NumberFilterBuilder.equalsIfNotNull(SelectMaker.SELECT_KBN, selectKbn);
    NumberFilter ftPartsMakerCd = null;
    if (0 == selectKbn) {
      ftPartsMakerCd = NumberFilterBuilder.equals(SelectMaker.G_PARTS_MAKER_CD, partsMakerCd);
    } else if (1 == selectKbn) {
      ftPartsMakerCd = NumberFilterBuilder.equals(SelectMaker.S_PARTS_MAKER_CD, partsMakerCd);
    } else if (2 == selectKbn) {
      ftPartsMakerCd = NumberFilterBuilder.equals(SelectMaker.J_PARTS_MAKER_CD, partsMakerCd);
    }
    NumberFilter ftLogicalDeleteCode = NumberFilterBuilder.equals(SelectMaker.LOGICAL_DEL_DIV, logicalDeleteCode);
    Filter filter = new AndFilter(selectDiv, ftPartsMakerCd, ftLogicalDeleteCode);
    return selectMakerDao.findByFilter(filter, Limit.NO_LIMIT);
  }

  /**
   * <pre>
   * チェック選択に商品情報削除.
   * </pre>
   * 
   * @param partsMakerCd ログインのメーカーコード
   * @param selectKbn セットする区分
   */
  @Override
  public void deleteGoodsBySelectKbn(int selectKbn, int partsMakerCd) {
    List<SelectMaker> selectMakerList = searchBySelectKbn(selectKbn, partsMakerCd, 0);
    for (SelectMaker selectMaker : selectMakerList) {
      selectMakerDao.delete(selectMaker);
    }
  }

  /**
   * <pre>
   * チェック選択にセット情報削除.
   * </pre>
   * 
   * @param selectKbn セットする区分
   */
  @Override
  public void deleteSetBySelectKbn(int selectKbn, int partsMakerCd) {
    List<SelectMaker> selectMakerList = searchBySelectKbn(selectKbn, partsMakerCd, 0);
    for (SelectMaker selectMaker : selectMakerList) {
      selectMakerDao.delete(selectMaker);
    }
  }

  /**
   * <pre>
   * チェック選択にセット情報削除.
   * </pre>
   * 
   * @param selectKbn セットする区分
   */
  @Override
  public void deleteJoinBySelectKbn(int selectKbn, int partsMakerCd) {
    List<SelectMaker> selectMakerList = searchBySelectKbn(selectKbn, partsMakerCd, 0);
    for (SelectMaker selectMaker : selectMakerList) {
      selectMakerDao.delete(selectMaker);
    }
  }
}
