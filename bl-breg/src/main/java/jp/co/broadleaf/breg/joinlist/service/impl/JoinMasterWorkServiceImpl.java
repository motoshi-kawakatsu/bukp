package jp.co.broadleaf.breg.joinlist.service.impl;

import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMakerId;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterSearchModel;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterWorkModel;
import jp.co.broadleaf.breg.joinlist.service.JoinMasterWorkService;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.DateFilter;
import jp.co.broadleaf.framework.dao.criteria.DateFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.FieldAscOrder;
import jp.co.broadleaf.framework.dao.criteria.FieldOrder;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.MultiFieldOrder;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.Order;
import jp.co.broadleaf.framework.dao.criteria.StringFilter;
import jp.co.broadleaf.framework.dao.criteria.StringFilterBuilder;

import org.apache.commons.lang3.Validate;

import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 結合マスタ(メーカー)ワークサービスクラス.
 * </pre>
 */
public class JoinMasterWorkServiceImpl implements JoinMasterWorkService {

  /** 結合マスタ(メーカー)ワークDAO */
  private GenericDao<JoinMasterWorkMaker, JoinMasterWorkMakerId> joinMasterWorkMakerDao;

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークDAOを設定する.
   * </pre>
   * 
   * @param joinMasterWorkMakerDao 結合マスタ(メーカー)ワークDAO
   */
  @Resource
  public void setJoinMasterWorkMakerDao(GenericDao<JoinMasterWorkMaker, JoinMasterWorkMakerId> joinMasterWorkMakerDao) {
    this.joinMasterWorkMakerDao = joinMasterWorkMakerDao;
  }

  /**
   * <pre>
   * 結合詳細の値を取得.
   * </pre>
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param tbsPartsCode BLコード
   * @param joinSourceMakerCode 結合元メーカーコード
   * @param prmSetDtlNo2 優良設定詳細コード２
   * @param joinSourPartsNoWithH 結合元品番(－付き品番)
   * @return 結合マスタ(メーカー)ワーク
   */
  public List<JoinMasterWorkMaker> getJoinDetail(String prmSetDtlNo1, String partsMakerCd, String tbsPartsCode,
                                                 String joinSourceMakerCode, String prmSetDtlNo2,
                                                 String joinSourPartsNoWithH) {
    Validate.notNull(prmSetDtlNo1, "prmSetDtlNo1 must not be null");
    Validate.notNull(partsMakerCd, "partsMakerCd must not be null");
    Validate.notNull(tbsPartsCode, "tbsPartsCode must not be null");
    Validate.notNull(joinSourceMakerCode, "joinSourceMakerCode must not be null");
    Validate.notNull(prmSetDtlNo2, "prmSetDtlNo2 must not be null");
    Validate.notNull(joinSourPartsNoWithH, "joinSourPartsNoWithH must not be null");

    JoinMasterWorkMaker searchCmd = new JoinMasterWorkMaker();
    searchCmd.setPrmSetDtlNo1(Integer.parseInt(prmSetDtlNo1));
    searchCmd.setPartsMakerCd(Integer.parseInt(partsMakerCd));
    searchCmd.setTbsPartsCode(Integer.parseInt(tbsPartsCode));
    searchCmd.setJoinSourceMakerCode(Integer.parseInt(joinSourceMakerCode));
    searchCmd.setPrmSetDtlNo2(Integer.parseInt(prmSetDtlNo2));
    searchCmd.setJoinSourPartsNoWithH(joinSourPartsNoWithH);
    searchCmd.setLogicalDelDiv(DeleteFlgEnum.NoDelete.getValue());

    return joinMasterWorkMakerDao.findByExample(searchCmd);
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークを取得.
   * </pre>
   * 
   * @param joinMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param order 表示順
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param importKbn インポート区分
   * @return 結合マスタ(メーカー)ワーク
   */
  @Override
  public JoinMasterWorkModel searchJoinMasterWorkInfoList(JoinMasterSearchModel joinMasterSearchModel, int order,
                                                          Long skipRows, Long maxRows, Boolean isPage,
                                                          Integer importKbn) {
    JoinMasterWorkModel joinMasterWorkModel = new JoinMasterWorkModel();
    // BLコード = 引数.BLコード
    NumberFilter tbsPartsCode = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.TBS_PARTS_CODE,
        joinMasterSearchModel.getTbsPartsCode());
    // 結合元品番(－付き品番) = 引数.純正品番
    StringFilter joinSourPartsNoWithH = StringFilterBuilder.equalsIfNotNull(
        JoinMasterWorkMaker.JOIN_SOUR_PARTS_NO_WITH_H, joinMasterSearchModel.getJoinSourPartsNoWithH());
    // 結合先品番(－付き品番) = 引数.優良品番
    StringFilter joinDestPartsno = StringFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.JOIN_DEST_PARTS_NO,
        joinMasterSearchModel.getJoinDestPartsNo());
    // 申請状態 = 引数.申請状態
    NumberFilter applyCondition = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.APPLY_CONDITION,
        joinMasterSearchModel.getApplyCondition());
    // 処理区分 = 引数.処理区分
    NumberFilter manageKbn = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.MANAGE_KBN,
        joinMasterSearchModel.getManageKbn());
    // データステータス = 引数.エラー区分
    NumberFilter errorFlg = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.ERROR_FLG,
        joinMasterSearchModel.getErrorFlg());
    // 優良設定詳細コード１ = 引数.セレクトコード
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.PRM_SET_DTL_NO_1,
        joinMasterSearchModel.getPrmSetDtlNo1());
    // 結合元メーカーコード = 引数.カーコード
    NumberFilter joinSourceMakerCode = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.JOIN_SOURCE_MAKER_CODE,
        joinMasterSearchModel.getJoinSourceMakerCode());
    // 適用日付>= 引数.適用日付
    DateFilter startTimeStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(JoinMasterWorkMaker.START_TIME,
        joinMasterSearchModel.getStartTimeStart());
    // 適用日付<= 引数.適用日付
    DateFilter startTimeEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(JoinMasterWorkMaker.START_TIME,
        joinMasterSearchModel.getStartTimeEnd());
    // 商品中分類コード = 引数.分類コード
    NumberFilter goodsMGroup = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.GOODS_M_GROUP,
        joinMasterSearchModel.getGoodsMGroup());
    // 優良設定詳細コード２ = 引数.種別コード
    NumberFilter prmSetDtlNo2 = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.PRM_SET_DTL_NO_2,
        joinMasterSearchModel.getPrmSetDtlNo2());
    // 作成日時>=引数. 作成日時
    DateFilter insDtTimeStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(JoinMasterWorkMaker.INS_DT_TIME,
        joinMasterSearchModel.getInsDtTimeStart());
    // 作成日時 <=引数. 作成日時
    DateFilter insDtTimeEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(JoinMasterWorkMaker.INS_DT_TIME,
        joinMasterSearchModel.getInsDtTimeEnd());
    // 結合規格・特記事項 = 引数.規格・特記
    StringFilter joinSpecialNote = StringFilterBuilder.containsIfNotEmpty(JoinMasterWorkMaker.JOIN_SPECIAL_NOTE,
        joinMasterSearchModel.getJoinSpecialNote());
    // 削除依頼区分 = 引数.削除依頼区分
    NumberFilter deleteFlg = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.DELETE_FLG,
        joinMasterSearchModel.getDeleteFlg());
    // 更新日時 <=引数. 作成日時
    DateFilter updDtTimeEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(JoinMasterWorkMaker.UPD_DT_TIME,
        joinMasterSearchModel.getUpdDtTimeEnd());
    // 更新日時>=引数. 更新日時
    DateFilter updDtTimeStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(JoinMasterWorkMaker.UPD_DT_TIME,
        joinMasterSearchModel.getUpdDtTimeStart());
    // 優良部品規格・特記事項(C向け) = 引数.規格/特記(一般)
    StringFilter primePartsSpecialNoteC = StringFilterBuilder.containsIfNotEmpty(
        JoinMasterWorkMaker.PRIME_PARTS_SPECIAL_NOTEC, joinMasterSearchModel.getPrimePartsSpecialNoteC());
    // 部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.PARTS_MAKER_CD,
        joinMasterSearchModel.getPartsMakerCd());
    // 論理削除区分
    NumberFilter logicalDelDiv = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.LOGICAL_DEL_DIV,
        JoinMasterMaker.LOGICAL_UNDELETED);
    // インポート区分
    NumberFilter importdiv = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.IMPORT_KBN, importKbn);
    Filter filter = new AndFilter(tbsPartsCode, joinSourPartsNoWithH, joinDestPartsno, applyCondition, manageKbn,
        errorFlg, prmSetDtlNo1, joinSourceMakerCode, startTimeStart, startTimeEnd, goodsMGroup, prmSetDtlNo2,
        insDtTimeStart, insDtTimeEnd, joinSpecialNote, deleteFlg, updDtTimeEnd, updDtTimeStart, primePartsSpecialNoteC,
        partsMakerCd, logicalDelDiv, importdiv);
    Order orderby = null;
    FieldOrder prmSetDtlCd1Order = new FieldAscOrder(JoinMasterMaker.PRM_SET_DTL_NO_1);
    FieldOrder goodsMGroupOrder = new FieldAscOrder(JoinMasterMaker.GOODS_M_GROUP);
    FieldOrder tbsPartsCodeOrder = new FieldAscOrder(JoinMasterMaker.TBS_PARTS_CODE);
    FieldOrder joinSourceMakerCodeOrder = new FieldAscOrder(JoinMasterMaker.JOIN_SOURCE_MAKER_CODE);
    FieldOrder joinSourPartsNoWithHOrder = new FieldAscOrder(JoinMasterMaker.JOIN_SOUR_PARTS_NO_WITH_H);
    FieldOrder prmSetDtlNo2Order = new FieldAscOrder(JoinMasterMaker.PRM_SET_DTL_NO_2);
    FieldOrder joinDispOrder = new FieldAscOrder(JoinMasterMaker.JOIN_DISP_ORDER);
    FieldOrder joinDestPartsNoOrder = new FieldAscOrder(JoinMasterMaker.JOIN_DEST_PARTS_NO);
    if (order == 0) {
      orderby = new MultiFieldOrder(prmSetDtlCd1Order, goodsMGroupOrder, tbsPartsCodeOrder, joinSourceMakerCodeOrder,
          joinSourPartsNoWithHOrder, prmSetDtlNo2Order, joinDispOrder);
    } else if (order == 1) {
      orderby = new MultiFieldOrder(prmSetDtlCd1Order, goodsMGroupOrder, tbsPartsCodeOrder, joinSourPartsNoWithHOrder,
          prmSetDtlNo2Order, joinSourceMakerCodeOrder, joinDispOrder);
    } else if (order == 2) {
      orderby = new MultiFieldOrder(prmSetDtlCd1Order, goodsMGroupOrder, tbsPartsCodeOrder, joinDestPartsNoOrder,
          joinSourceMakerCodeOrder, joinSourPartsNoWithHOrder);
    }
    Limit limit = Limit.NO_LIMIT;
    if (isPage && skipRows != null) {
      limit = new Limit(skipRows, maxRows);
    }
    long searchCounts = joinMasterWorkMakerDao.countByFilter(filter);
    joinMasterWorkModel.setSearchCounts(searchCounts);
    List<JoinMasterWorkMaker> joinMasterWorkList = joinMasterWorkMakerDao.findByFilter(filter, limit, orderby);
    joinMasterWorkModel.setJoinMasterWorkList(joinMasterWorkList);
    return joinMasterWorkModel;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの全データを取得.
   * </pre>
   * 
   * @return 結合マスタ(メーカー)ワーク
   */
  @Override
  public JoinMasterWorkModel searchJoinMasterWorkList() {
    JoinMasterWorkModel joinMasterWorkModel = new JoinMasterWorkModel();
    // 結合マスタ(メーカー)ワークの検索
    List<JoinMasterWorkMaker> joinMasterWorkList = joinMasterWorkMakerDao.findAll();
    joinMasterWorkModel.setJoinMasterWorkList(joinMasterWorkList);
    return joinMasterWorkModel;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークのデータ取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param importKbn インポート区分
   * @return 結合マスタ(メーカー)ワーク全データ
   */
  @Override
  public JoinMasterWorkModel searchJoinMasterWorkAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd,
                                                     Integer importKbn) {
    JoinMasterWorkModel joinMasterWorkModel = new JoinMasterWorkModel();
    // 部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.PARTS_MAKER_CD, makerCd);
    // 論理削除区分
    StringFilter logicalDelDiv = StringFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.LOGICAL_DEL_DIV, "0");
    // インポート区分
    NumberFilter importdiv = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.IMPORT_KBN, importKbn);
    // 申請状態
    NumberFilter applyCondition = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.APPLY_CONDITION,
        ApplyConditionEnum.NoApply.getValue());
    Filter filter = new AndFilter(partsMakerCd, logicalDelDiv, importdiv, applyCondition);
    Limit limit = Limit.NO_LIMIT;
    if (isPage) {
      limit = new Limit(skipRows, maxRows);
    }
    // 順番
    FieldOrder prmSetDtlCd1Order = new FieldAscOrder(JoinMasterMaker.PRM_SET_DTL_NO_1);
    FieldOrder goodsMGroupOrder = new FieldAscOrder(JoinMasterMaker.GOODS_M_GROUP);
    FieldOrder tbsPartsCodeOrder = new FieldAscOrder(JoinMasterMaker.TBS_PARTS_CODE);
    FieldOrder joinSourceMakerCodeOrder = new FieldAscOrder(JoinMasterMaker.JOIN_SOURCE_MAKER_CODE);
    FieldOrder joinSourPartsNoWithHOrder = new FieldAscOrder(JoinMasterMaker.JOIN_SOUR_PARTS_NO_WITH_H);
    FieldOrder prmSetDtlNo2Order = new FieldAscOrder(JoinMasterMaker.PRM_SET_DTL_NO_2);
    FieldOrder joinDispOrder = new FieldAscOrder(JoinMasterMaker.JOIN_DISP_ORDER);
    Order orderby = new MultiFieldOrder(prmSetDtlCd1Order, goodsMGroupOrder, tbsPartsCodeOrder,
        joinSourceMakerCodeOrder, joinSourPartsNoWithHOrder, prmSetDtlNo2Order, joinDispOrder);
    // 結合マスタ(メーカー)の検索
    List<JoinMasterWorkMaker> joinMasterWorkList = joinMasterWorkMakerDao.findByFilter(filter, limit, orderby);
    joinMasterWorkModel.setJoinMasterWorkList(joinMasterWorkList);
    return joinMasterWorkModel;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワーク登録.
   * </pre>
   * 
   * @param joinMasterWorkModel 登録する項目（優良設定詳細コード１ など）の連係情報
   * @return 登録した結合マスタ(メーカー)ワーク情報の件数
   */
  @Override
  public int insertJoinMasterWorkInfoList(JoinMasterWorkModel joinMasterWorkModel) {
    if (joinMasterWorkModel.getJoinMasterWorkList() == null || joinMasterWorkModel.getJoinMasterWorkList().isEmpty()) {
      return 0;
    }
    // 登録
    joinMasterWorkMakerDao.batchInsert(joinMasterWorkModel.getJoinMasterWorkList());
    // 登録した件数を返却する
    return joinMasterWorkModel.getJoinMasterWorkList().size();
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワーク情報更新.
   * </pre>
   * 
   * @param joinMasterWork 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  @Override
  public void updateJoinMasterWork(JoinMasterWorkMaker joinMasterWork) {
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equals(JoinMasterWorkMaker.PRM_SET_DTL_NO_1,
        joinMasterWork.getPrmSetDtlNo1());
    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter prtsMakerCd = NumberFilterBuilder.equals(JoinMasterWorkMaker.PARTS_MAKER_CD,
        joinMasterWork.getPartsMakerCd());
    // BLコード = 引数.BLコード
    NumberFilter blCd = NumberFilterBuilder.equals(JoinMasterWorkMaker.TBS_PARTS_CODE,
        joinMasterWork.getTbsPartsCode());
    // 結合元メーカーコード = 引数.結合元メーカーコード
    NumberFilter joinSourceMakerCd = NumberFilterBuilder.equals(JoinMasterWorkMaker.JOIN_SOURCE_MAKER_CODE,
        joinMasterWork.getJoinSourceMakerCode());
    // 優良設定詳細コード２ = 引数.優良設定詳細コード２
    NumberFilter prmSetDtlCd2 = NumberFilterBuilder.equals(JoinMasterWorkMaker.PRM_SET_DTL_NO_2,
        joinMasterWork.getPrmSetDtlNo2());
    // 結合元品番(－付き品番) = 引数.結合元品番(－付き品番)
    StringFilter joinSourcePartsno = StringFilterBuilder.equals(JoinMasterWorkMaker.JOIN_SOUR_PARTS_NO_WITH_H,
        joinMasterWork.getJoinSourPartsNoWithH());
    // 結合表示順位 = 引数.結合表示順位
    NumberFilter joinDispOrder = NumberFilterBuilder.equals(JoinMasterWorkMaker.JOIN_DISP_ORDER,
        joinMasterWork.getJoinDispOrder());

    Filter filter = new AndFilter(prmSetDtlNo1, prtsMakerCd, blCd, joinSourceMakerCd, prmSetDtlCd2, joinSourcePartsno,
        joinDispOrder);
    // 検索結果を取得
    JoinMasterWorkMaker joinMasterWorkResult = joinMasterWorkMakerDao.findOneByFilter(filter);
    if (joinMasterWorkResult != null) {
      // 更新する項目
      // 商品中分類コード
      joinMasterWorkResult.setGoodsMGroup(joinMasterWork.getGoodsMGroup());
      // 結合先品番(－付き品番)
      joinMasterWorkResult.setJoinDestPartsNo(joinMasterWork.getJoinDestPartsNo());
      // 結合QTY
      joinMasterWorkResult.setJoinQty(joinMasterWork.getJoinQty());
      // 結合規格・特記事項
      joinMasterWorkResult.setJoinSpecialNote(joinMasterWork.getJoinSpecialNote());
      // 優良部品規格・特記事項(C向け)
      joinMasterWorkResult.setPrimePartsSpecialNoteC(joinMasterWork.getPrimePartsSpecialNoteC());
      // 適用日付
      joinMasterWorkResult.setStartTime(joinMasterWork.getStartTime());
      // 削除時申請理由
      joinMasterWorkResult.setDeleteReason(joinMasterWork.getDeleteReason());
      // チェック区分
      joinMasterWorkResult.setCheckFlg(joinMasterWork.getCheckFlg());
      // データステータス
      joinMasterWorkResult.setCheckFlg(joinMasterWork.getErrorFlg());
      // エラー内容
      joinMasterWorkResult.setErrorDetail(joinMasterWork.getErrorDetail());
      // 削除依頼区分
      joinMasterWorkResult.setDeleteFlg(joinMasterWork.getDeleteFlg());
      // 申請状態
      joinMasterWorkResult.setApplyCondition(joinMasterWork.getApplyCondition());
      // 結合マスタ(メーカー)情報更新
      joinMasterWorkMakerDao.update(joinMasterWorkResult);
    } else {
      joinMasterWorkMakerDao.insert(joinMasterWork);
    }
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワーク削除.
   * </pre>
   * 
   * @param joinMasterWorkMaker 結合マスタ(メーカー)ワーク
   */
  @Override
  public void deleteJoinMasterWork(JoinMasterWorkMaker joinMasterWorkMaker) {
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equals(JoinMasterWorkMaker.PRM_SET_DTL_NO_1,
        joinMasterWorkMaker.getPrmSetDtlNo1());
    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equals(JoinMasterWorkMaker.PARTS_MAKER_CD,
        joinMasterWorkMaker.getPartsMakerCd());
    // BLコード = 引数.BLコード
    NumberFilter tbsPartsCode = NumberFilterBuilder.equals(JoinMasterWorkMaker.TBS_PARTS_CODE,
        joinMasterWorkMaker.getTbsPartsCode());
    // 結合元メーカーコード = 引数.結合元メーカーコード
    NumberFilter joinSourceMakerCode = NumberFilterBuilder.equals(JoinMasterWorkMaker.JOIN_SOURCE_MAKER_CODE,
        joinMasterWorkMaker.getJoinSourceMakerCode());
    // 優良設定詳細コード２ = 引数.優良設定詳細コード２
    NumberFilter prmSetDtlNo2 = NumberFilterBuilder.equals(JoinMasterWorkMaker.PRM_SET_DTL_NO_2,
        joinMasterWorkMaker.getPrmSetDtlNo2());
    // 結合元品番(－付き品番) = 引数.結合元品番(－付き品番)
    StringFilter joinSourPartsNoWithH = StringFilterBuilder.equals(JoinMasterWorkMaker.JOIN_SOUR_PARTS_NO_WITH_H,
        joinMasterWorkMaker.getJoinSourPartsNoWithH());
    // 結合表示順位 = 引数.結合表示順位
    NumberFilter joinDispOrder = NumberFilterBuilder.equals(JoinMasterWorkMaker.JOIN_DISP_ORDER,
        joinMasterWorkMaker.getJoinDispOrder());

    Filter filter = new AndFilter(prmSetDtlNo1, partsMakerCd, tbsPartsCode, joinSourceMakerCode, prmSetDtlNo2,
        joinSourPartsNoWithH, joinDispOrder);
    // 検索結果を取得
    JoinMasterWorkMaker joinMasterWorkResult = joinMasterWorkMakerDao.findOneByFilter(filter);
    if (joinMasterWorkResult != null) {
      joinMasterWorkMakerDao.delete(joinMasterWorkResult);
    }
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの全データを削除する.
   * </pre>
   */
  @Override
  public void deleteAll() {
    for (JoinMasterWorkMaker entity : joinMasterWorkMakerDao.findAll()) {
      joinMasterWorkMakerDao.delete(entity);
    }
  }

}
