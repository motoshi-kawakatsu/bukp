package jp.co.broadleaf.breg.joinlist.service.impl;

import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMakerId;
import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMaker;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterModel;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterSearchModel;
import jp.co.broadleaf.breg.joinlist.service.JoinMasterService;
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

import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 結合マスタ(メーカー)サービスクラス.
 * </pre>
 */
public class JoinMasterServiceImpl implements JoinMasterService {

  /** 1:エラー有り */
  private static final int ERROR_DATA = 1;

  /** 結合マスタ(メーカー)DAO */
  private GenericDao<JoinMasterMaker, JoinMasterMakerId> joinMasterMakerDao;

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
   * チェックリスト画面の結合マスタ(メーカー)の全データ取得.
   * </pre>
   * 
   * @param count 現在位置
   * @param maxRows maxRows
   * @param makerCode makerCode
   * @return 結合マスタ(メーカー)の全データ
   */
  @Override
  public JoinMasterModel searchJoinErrorList(Long count, Long maxRows, int makerCode) {
    JoinMasterModel joinMasterModel = new JoinMasterModel();
    // データステータス = 「1:エラー有り」
    NumberFilter errorFlg = NumberFilterBuilder.equals(JoinMasterMaker.ERROR_FLG, ERROR_DATA);
    NumberFilter makerCd = NumberFilterBuilder.equals(JoinMasterMaker.PARTS_MAKER_CD, makerCode);
    // 論理削除区分
    StringFilter logicalDelDiv = StringFilterBuilder.equalsIfNotNull(JoinMasterMaker.LOGICAL_DEL_DIV, "0");

    Filter filter = new AndFilter(errorFlg, makerCd, logicalDelDiv);

    // 順番
    Order orderby;
    FieldOrder prmSetDtlNo1 = new FieldAscOrder(JoinMasterMaker.PRM_SET_DTL_NO_1);
    FieldOrder goodsMGroup = new FieldAscOrder(JoinMasterMaker.GOODS_M_GROUP);
    FieldOrder blCodeOrder = new FieldAscOrder(JoinMasterMaker.TBS_PARTS_CODE);
    FieldOrder primePartsNoWithH = new FieldAscOrder(JoinMasterMaker.JOIN_DEST_PARTS_NO);
    // 画面の優良品番はテープルにありません
    orderby = new MultiFieldOrder(prmSetDtlNo1, goodsMGroup, blCodeOrder, primePartsNoWithH);

    long joinCount = joinMasterMakerDao.countByFilter(filter);
    Limit limit = Limit.NO_LIMIT;
    if (count != null) {
      limit = new Limit(count, maxRows);
    }
    // 結合マスタ(メーカー)の検索
    List<JoinMasterMaker> joinMasterList = joinMasterMakerDao.findByFilter(filter, limit, orderby);
    joinMasterModel.setJoinMasterList(joinMasterList);
    joinMasterModel.setSearchCounts(joinCount);
    return joinMasterModel;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)の全データ取得.
   * </pre>
   * 
   * @return 結合マスタ(メーカー)の全データ
   */
  @Override
  public JoinMasterModel searchJoinMasterInfoList() {
    JoinMasterModel joinMasterModel = new JoinMasterModel();
    // 結合マスタ(メーカー)の検索
    List<JoinMasterMaker> joinMasterList = joinMasterMakerDao.findAll();
    joinMasterModel.setJoinMasterList(joinMasterList);
    return joinMasterModel;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)のデータ取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param searchMode searchMode
   * @return 結合マスタ(メーカー)全データ
   */
  @Override
  public JoinMasterModel searchJoinMasterInfoDtoAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd,
                                                    int searchMode) {
    JoinMasterModel joinMasterModel = new JoinMasterModel();
    // 部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.PARTS_MAKER_CD, makerCd);
    // 論理削除区分
    StringFilter logicalDelDiv = StringFilterBuilder.equalsIfNotNull(JoinMasterMaker.LOGICAL_DEL_DIV, "0");
    NumberFilter importdiv;
    NumberFilter applyCondition = null;
    // 結合選択モード
    if (searchMode == 0) {
      Integer[] nums = new Integer[2];
      for (int i = 0; i < 2; i++) {
        nums[i] = i + 1;
      }
      // インポート区分
      importdiv = NumberFilterBuilder.in(JoinMasterWorkMaker.IMPORT_KBN, nums);
      if (isPage) {
        applyCondition = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.APPLY_CONDITION,
            ApplyConditionEnum.NoApply.getValue());
      } else {
        applyCondition = NumberFilterBuilder.in(GoodsMasterMaker.APPLY_CONDITION, ApplyConditionEnum.NoApply.getValue(),
            ApplyConditionEnum.Applyagain.getValue());
      }
    }
    // 参照モード(【インポート（一括申請）】流れの場合)
    else if (searchMode == 1) {
      // インポート区分
      importdiv = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.IMPORT_KBN, 0);
      applyCondition = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.APPLY_CONDITION,
          ApplyConditionEnum.NoApply.getValue());
    }
    // 参照モード(【インポート】流れの場合)
    else if (searchMode == 2) {
      // インポート区分
      importdiv = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.IMPORT_KBN, 1);
      applyCondition = NumberFilterBuilder.equalsIfNotNull(GoodsMasterMaker.APPLY_CONDITION,
          ApplyConditionEnum.NoApply.getValue());
    }
    // 検索入力モード
    else {
      Integer[] nums = new Integer[3];
      for (int i = 0; i < 3; i++) {
        nums[i] = i;
      }
      // インポート区分
      importdiv = NumberFilterBuilder.in(JoinMasterWorkMaker.IMPORT_KBN, nums);
    }
    Filter filter = new AndFilter(partsMakerCd, logicalDelDiv, importdiv, applyCondition);
    Limit limit = Limit.NO_LIMIT;
    if (isPage && maxRows != -1) {
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
    List<JoinMasterMaker> joinMasterList = joinMasterMakerDao.findByFilter(filter, limit, orderby);
    joinMasterModel.setJoinMasterList(joinMasterList);
    return joinMasterModel;
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
   * @return 結合マスタ(メーカー)情報
   */
  @Override
  public List<JoinMasterMaker> searchJoinInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                                  Integer partsMakerCd, Integer logicalDeleteCode) {
    NumberFilter ftImportKbn = NumberFilterBuilder.equals(JoinMasterMaker.IMPORT_KBN, importKbn);
    NumberFilter ftApplyCondition = NumberFilterBuilder.equals(JoinMasterMaker.APPLY_CONDITION, applyCondition);
    StringFilter ftUpdAccountId = StringFilterBuilder.notEquals(JoinMasterMaker.UPD_ACCOUNT_ID, updAccountId);
    NumberFilter ftPartsMakerCd = NumberFilterBuilder.equals(JoinMasterMaker.PARTS_MAKER_CD, partsMakerCd);
    NumberFilter ftLogicalDeleteCode = NumberFilterBuilder.equals(JoinMasterMaker.LOGICAL_DEL_DIV, logicalDeleteCode);
    Filter filter = new AndFilter(ftImportKbn, ftApplyCondition, ftUpdAccountId, ftPartsMakerCd, ftLogicalDeleteCode);
    List<JoinMasterMaker> joinMasterList = joinMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
    return joinMasterList;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)を取得.
   * </pre>
   * 
   * @param joinMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param order 表示順
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param searchMode searchMode
   * @return 結合マスタ(メーカー)
   */
  @Override
  public JoinMasterModel searchJoinMasterInfoList(JoinMasterSearchModel joinMasterSearchModel, int order, Long skipRows,
                                                  Long maxRows, Boolean isPage, int searchMode) {
    JoinMasterModel joinMasterModel = new JoinMasterModel();
    // BLコード = 引数.BLコード
    NumberFilter tbsPartsCode = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.TBS_PARTS_CODE,
        joinMasterSearchModel.getTbsPartsCode());
    // 結合元品番(－付き品番) = 引数.純正品番
    StringFilter joinSourPartsNoWithH = StringFilterBuilder.equalsIfNotNull(JoinMasterMaker.JOIN_SOUR_PARTS_NO_WITH_H,
        joinMasterSearchModel.getJoinSourPartsNoWithH());
    // 結合先品番(－付き品番) = 引数.優良品番
    StringFilter joinDestPartsno = StringFilterBuilder.equalsIfNotNull(JoinMasterMaker.JOIN_DEST_PARTS_NO,
        joinMasterSearchModel.getJoinDestPartsNo());
    // 申請状態 = 引数.申請状態
    NumberFilter applyCondition = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.APPLY_CONDITION,
        joinMasterSearchModel.getApplyCondition());
    // 処理区分 = 引数.処理区分
    NumberFilter manageKbn = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.MANAGE_KBN,
        joinMasterSearchModel.getManageKbn());
    // データステータス = 引数.エラー区分
    NumberFilter errorFlg = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.ERROR_FLG,
        joinMasterSearchModel.getErrorFlg());
    // 優良設定詳細コード１ = 引数.セレクトコード
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.PRM_SET_DTL_NO_1,
        joinMasterSearchModel.getPrmSetDtlNo1());
    // 結合元メーカーコード = 引数.カーコード
    NumberFilter joinSourceMakerCode = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.JOIN_SOURCE_MAKER_CODE,
        joinMasterSearchModel.getJoinSourceMakerCode());
    // 適用日付>= 引数.適用日付
    DateFilter startTimeStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(JoinMasterMaker.START_TIME,
        joinMasterSearchModel.getStartTimeStart());
    // 適用日付<= 引数.適用日付
    DateFilter startTimeEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(JoinMasterMaker.START_TIME,
        joinMasterSearchModel.getStartTimeEnd());
    // 商品中分類コード = 引数.分類コード
    NumberFilter goodsMGroup = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.GOODS_M_GROUP,
        joinMasterSearchModel.getGoodsMGroup());
    // 優良設定詳細コード２ = 引数.種別コード
    NumberFilter prmSetDtlNo2 = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.PRM_SET_DTL_NO_2,
        joinMasterSearchModel.getPrmSetDtlNo2());
    // 作成日時>=引数. 作成日時
    DateFilter insDtTimeStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(JoinMasterMaker.INS_DT_TIME,
        joinMasterSearchModel.getInsDtTimeStart());
    // 作成日時 <=引数. 作成日時
    DateFilter insDtTimeEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(JoinMasterMaker.INS_DT_TIME,
        joinMasterSearchModel.getInsDtTimeEnd());
    // 結合規格・特記事項 = 引数.規格・特記
    StringFilter joinSpecialNote = StringFilterBuilder.containsIfNotEmpty(JoinMasterMaker.JOIN_SPECIAL_NOTE,
        joinMasterSearchModel.getJoinSpecialNote());
    // 削除依頼区分 = 引数.削除依頼区分
    NumberFilter deleteFlg = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.DELETE_FLG,
        joinMasterSearchModel.getDeleteFlg());
    // 更新日時 <=引数. 作成日時
    DateFilter updDtTimeEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(JoinMasterMaker.UPD_DT_TIME,
        joinMasterSearchModel.getUpdDtTimeEnd());
    // 更新日時>=引数. 更新日時
    DateFilter updDtTimeStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(JoinMasterMaker.UPD_DT_TIME,
        joinMasterSearchModel.getUpdDtTimeStart());
    // 優良部品規格・特記事項(C向け) = 引数.規格/特記(一般)
    StringFilter primePartsSpecialNoteC = StringFilterBuilder.containsIfNotEmpty(
        JoinMasterMaker.PRIME_PARTS_SPECIAL_NOTEC, joinMasterSearchModel.getPrimePartsSpecialNoteC());
    // 論理削除区分
    NumberFilter logicalDelDiv = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.LOGICAL_DEL_DIV,
        JoinMasterMaker.LOGICAL_UNDELETED);
    // 部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.PARTS_MAKER_CD,
        joinMasterSearchModel.getPartsMakerCd());
    NumberFilter importdiv = getImportdiv(searchMode);
    Filter filter = new AndFilter(tbsPartsCode, joinSourPartsNoWithH, joinDestPartsno, applyCondition, manageKbn,
        errorFlg, prmSetDtlNo1, joinSourceMakerCode, startTimeStart, startTimeEnd, goodsMGroup, prmSetDtlNo2,
        insDtTimeStart, insDtTimeEnd, joinSpecialNote, deleteFlg, updDtTimeEnd, updDtTimeStart, primePartsSpecialNoteC,
        logicalDelDiv, partsMakerCd, importdiv);
    // 順番
    Order orderby = getOrder(order);
    Limit limit = Limit.NO_LIMIT;
    if (isPage && skipRows != null) {
      limit = new Limit(skipRows, maxRows);
    }
    // 結合マスタ(メーカー)の検索
    long searchCounts = joinMasterMakerDao.countByFilter(filter);
    List<JoinMasterMaker> joinMasterList = joinMasterMakerDao.findByFilter(filter, limit, orderby);
    joinMasterModel.setJoinMasterList(joinMasterList);
    joinMasterModel.setSearchCounts(searchCounts);
    return joinMasterModel;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)インポート区分を取得.
   * </pre>
   * 
   * @param searchMode インポート区分の判断
   * @return 結合マスタ(メーカー)インポート区分
   */
  private NumberFilter getImportdiv(int searchMode) {
    NumberFilter importdiv;
    // 結合選択モード
    if (searchMode == 0) {
      Integer[] nums = new Integer[2];
      for (int i = 0; i < 2; i++) {
        nums[i] = i + 1;
      }
      // インポート区分
      importdiv = NumberFilterBuilder.in(JoinMasterWorkMaker.IMPORT_KBN, nums);
    }
    // 参照モード(【インポート（一括申請）】流れの場合)
    else if (searchMode == 1) {
      // インポート区分
      importdiv = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.IMPORT_KBN, 0);
    }
    // 参照モード(【インポート】流れの場合)
    else if (searchMode == 2) {
      // インポート区分
      importdiv = NumberFilterBuilder.equalsIfNotNull(JoinMasterWorkMaker.IMPORT_KBN, 1);
    }
    // 検索入力モード
    else {
      Integer[] nums = new Integer[3];
      for (int i = 0; i < 3; i++) {
        nums[i] = i;
      }
      // インポート区分
      importdiv = NumberFilterBuilder.in(JoinMasterWorkMaker.IMPORT_KBN, nums);
    }
    return importdiv;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)表示順を取得.
   * </pre>
   * 
   * @param order 表示順の判断
   * @return 結合マスタ(メーカー)表示順
   */
  private Order getOrder(int order) {
    // 順番
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
    return orderby;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)登録.
   * </pre>
   * 
   * @param joinMasterModel 登録する項目（優良設定詳細コード１ など）の連係情報
   * @return 登録した結合マスタ(メーカー)情報の件数
   */
  @Override
  public int insertJoinMasterInfoList(JoinMasterModel joinMasterModel) {
    if (joinMasterModel.getJoinMasterList() == null || joinMasterModel.getJoinMasterList().isEmpty()) {
      return 0;
    }
    // 登録
    joinMasterMakerDao.batchInsert(joinMasterModel.getJoinMasterList());

    // 登録した件数を返却する
    return joinMasterModel.getJoinMasterList().size();
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)情報更新.
   * </pre>
   * 
   * @param joinMaster 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  @Override
  public void updateJoinMaster(JoinMasterMaker joinMaster) {
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equals(JoinMasterMaker.PRM_SET_DTL_NO_1,
        joinMaster.getPrmSetDtlNo1());
    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equals(JoinMasterMaker.PARTS_MAKER_CD,
        joinMaster.getPartsMakerCd());
    // BLコード = 引数.BLコード
    NumberFilter tbsPartsCode = NumberFilterBuilder.equals(JoinMasterMaker.TBS_PARTS_CODE,
        joinMaster.getTbsPartsCode());
    // 結合元メーカーコード = 引数.結合元メーカーコード
    NumberFilter joinSourceMakerCode = NumberFilterBuilder.equals(JoinMasterMaker.JOIN_SOURCE_MAKER_CODE,
        joinMaster.getJoinSourceMakerCode());
    // 優良設定詳細コード２ = 引数.優良設定詳細コード２
    NumberFilter prmSetDtlNo2 = NumberFilterBuilder.equals(JoinMasterMaker.PRM_SET_DTL_NO_2,
        joinMaster.getPrmSetDtlNo2());
    // 結合元品番(－付き品番) = 引数.結合元品番(－付き品番)
    StringFilter joinSourPartsNoWithH = StringFilterBuilder.equals(JoinMasterMaker.JOIN_SOUR_PARTS_NO_WITH_H,
        joinMaster.getJoinSourPartsNoWithH());
    // 結合表示順位 = 引数.結合表示順位
    NumberFilter joinDispOrder = NumberFilterBuilder.equals(JoinMasterMaker.JOIN_DISP_ORDER,
        joinMaster.getJoinDispOrder());

    Filter filter = new AndFilter(prmSetDtlNo1, partsMakerCd, tbsPartsCode, joinSourceMakerCode, prmSetDtlNo2,
        joinSourPartsNoWithH, joinDispOrder);
    // 検索結果を取得
    JoinMasterMaker joinMasterResult = joinMasterMakerDao.findOneByFilter(filter);
    // 更新する項目
    if (joinMasterResult != null) {
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
      // 適用日付
      joinMasterResult.setStartTime(joinMaster.getStartTime());
      // 削除時申請理由
      joinMasterResult.setDeleteReason(joinMaster.getDeleteReason());
      // チェック区分
      joinMasterResult.setCheckFlg(joinMaster.getCheckFlg());
      // データステータス
      joinMasterResult.setErrorFlg(joinMaster.getErrorFlg());
      // BL登録区分
      joinMasterResult.setBlEntryFlg(joinMaster.getBlEntryFlg());
      // インポート区分
      joinMasterResult.setImportKbn(joinMaster.getImportKbn());
      // 処理区分
      joinMasterResult.setManageKbn(joinMaster.getManageKbn());
      // エラー内容
      joinMasterResult.setErrorDetail(joinMaster.getErrorDetail());
      // 削除依頼区分
      joinMasterResult.setDeleteFlg(joinMaster.getDeleteFlg());
      // 申請状態
      joinMasterResult.setApplyCondition(joinMaster.getApplyCondition());
      // 結合マスタ(メーカー)情報更新
      joinMasterMakerDao.update(joinMasterResult);
    }
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)削除.
   * </pre>
   * 
   * @param joinMaster 結合マスタ(メーカー)
   */
  @Override
  public void deleteJoinMaster(JoinMasterMaker joinMaster) {
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equals(JoinMasterMaker.PRM_SET_DTL_NO_1,
        joinMaster.getPrmSetDtlNo1());
    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equals(JoinMasterMaker.PARTS_MAKER_CD,
        joinMaster.getPartsMakerCd());
    // BLコード = 引数.BLコード
    NumberFilter tbsPartsCode = NumberFilterBuilder.equals(JoinMasterMaker.TBS_PARTS_CODE,
        joinMaster.getTbsPartsCode());
    // 結合元メーカーコード = 引数.結合元メーカーコード
    NumberFilter joinSourceMakerCode = NumberFilterBuilder.equals(JoinMasterMaker.JOIN_SOURCE_MAKER_CODE,
        joinMaster.getJoinSourceMakerCode());
    // 優良設定詳細コード２ = 引数.優良設定詳細コード２
    NumberFilter prmSetDtlNo2 = NumberFilterBuilder.equals(JoinMasterMaker.PRM_SET_DTL_NO_2,
        joinMaster.getPrmSetDtlNo2());
    // 結合元品番(－付き品番) = 引数.結合元品番(－付き品番)
    StringFilter joinSourPartsNoWithH = StringFilterBuilder.equals(JoinMasterMaker.JOIN_SOUR_PARTS_NO_WITH_H,
        joinMaster.getJoinSourPartsNoWithH());
    // 結合表示順位 = 引数.結合表示順位
    NumberFilter joinDispOrder = NumberFilterBuilder.equals(JoinMasterMaker.JOIN_DISP_ORDER,
        joinMaster.getJoinDispOrder());

    Filter filter = new AndFilter(prmSetDtlNo1, partsMakerCd, tbsPartsCode, joinSourceMakerCode, prmSetDtlNo2,
        joinSourPartsNoWithH, joinDispOrder);
    // 検索結果を取得
    JoinMasterMaker joinMasterResult = joinMasterMakerDao.findOneByFilter(filter);
    if (joinMasterResult != null) {
      joinMasterMakerDao.delete(joinMasterResult);
    }
  }

  /**
   * 該当キーの結合マスタ（メーカー）本情報を取得
   * 
   * @param selectorCd セレクトコード
   * @param partsMakerCd 部品メーカーコード
   * @param blCode BLコード
   * @param joinMakerCd カーメーカーコード
   * @param kindCode 種別コード
   * @param joinPartsNo 純正品番
   * @return 結合マスタ(メーカー)Model
   */
  @Override
  public JoinMasterModel getJoinMasterMakerInfo(String selectorCd, String partsMakerCd, String blCode,
                                                String joinMakerCd, String kindCode, String joinPartsNo) {
    StringFilter selectorCdFilter = StringFilterBuilder.equals(JoinMasterMaker.PRM_SET_DTL_NO_1, selectorCd);
    StringFilter partsMakerCdFilter = StringFilterBuilder.equals(JoinMasterMaker.PARTS_MAKER_CD, partsMakerCd);
    StringFilter blCodeFilter = StringFilterBuilder.equals(JoinMasterMaker.TBS_PARTS_CODE, blCode);
    StringFilter joinMakerCdFilter = StringFilterBuilder.equals(JoinMasterMaker.JOIN_SOURCE_MAKER_CODE, joinMakerCd);
    StringFilter kindCodeFilter = StringFilterBuilder.equals(JoinMasterMaker.PRM_SET_DTL_NO_2, kindCode);
    StringFilter joinPartsNoFilter = StringFilterBuilder.equals(JoinMasterMaker.JOIN_SOUR_PARTS_NO_WITH_H, joinPartsNo);
    Filter filter = new AndFilter(selectorCdFilter, partsMakerCdFilter, blCodeFilter, joinMakerCdFilter, kindCodeFilter,
        joinPartsNoFilter);
    List<JoinMasterMaker> joinMasterList = joinMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
    JoinMasterModel model = new JoinMasterModel();
    model.setJoinMasterList(joinMasterList);
    return model;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)取得.
   * </pre>
   * 
   * @param joinMasterId 結合マスタ(メーカー)ID
   * @return 結合マスタ(メーカー)情報
   */
  @Override
  public JoinMasterMaker searchByJoinMasterId(JoinMasterMakerId joinMasterId) {
    return joinMasterMakerDao.findById(joinMasterId);
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)取得.
   * </pre>
   * 
   * @param joinMasterId 結合マスタ(メーカー)ID
   * @param joinDestPartsNo 結合先品番(－付き品番)
   * @return 結合マスタ(メーカー)情報
   */
  @Override
  public JoinMasterMaker searchJoinMaster(JoinMasterMakerId joinMasterId, String joinDestPartsNo) {
    // 優良設定詳細コード１ = 引数.優良設定詳細コード１
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.PRM_SET_DTL_NO_1,
        joinMasterId.getPrmSetDtlNo1());
    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.PARTS_MAKER_CD,
        joinMasterId.getPartsMakerCd());
    // BLコード = 引数.BLコード
    NumberFilter tbsPartsCode = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.TBS_PARTS_CODE,
        joinMasterId.getTbsPartsCode());
    // 結合元メーカーコード = 引数.結合元メーカーコード
    NumberFilter joinSourceMakerCode = NumberFilterBuilder.equalsIfNotNull(JoinMasterMaker.JOIN_SOURCE_MAKER_CODE,
        joinMasterId.getJoinSourceMakerCode());
    // 結合元品番(－付き品番) = 引数.結合元品番(－付き品番)
    StringFilter joinSourPartsNoWithH = StringFilterBuilder.equalsIfNotNull(JoinMasterMaker.JOIN_SOUR_PARTS_NO_WITH_H,
        joinMasterId.getJoinSourPartsNoWithH());
    // 結合表示順位 = 引数.結合表示順位
    NumberFilter joinDispOrder = NumberFilterBuilder.equals(JoinMasterMaker.JOIN_DISP_ORDER,
        joinMasterId.getJoinDispOrder());
    // 結合先品番(－付き品番) = 引数.結合先品番(－付き品番)
    StringFilter joinDestPartsNoFilter = StringFilterBuilder.equalsIfNotNull(JoinMasterMaker.JOIN_DEST_PARTS_NO,
        joinDestPartsNo);
    Filter filter = new AndFilter(prmSetDtlNo1, partsMakerCd, tbsPartsCode, joinSourceMakerCode, joinSourPartsNoWithH,
        joinDispOrder, joinDestPartsNoFilter);
    // 検索結果を取得
    JoinMasterMaker joinMasterResult = joinMasterMakerDao.findOneByFilter(filter);
    return joinMasterResult;
  }

}
