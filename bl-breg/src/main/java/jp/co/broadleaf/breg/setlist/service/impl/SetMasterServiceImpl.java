//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : chenlong
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.setlist.service.impl;

import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.common.enums.LogicDeleteDivEnum;
import jp.co.broadleaf.breg.common.enums.ModeEnum;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterMakerModel;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterSearchModel;
import jp.co.broadleaf.breg.setlist.service.SetMasterService;
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
 * セットマスタ(メーカー)サービスクラス.
 * </pre>
 */
public class SetMasterServiceImpl implements SetMasterService {

  /** 1:エラー有り */
  private static final int ERROR_DATA = 1;
  /** 検索order */
  private Order order = null;
  /** 検索filter */
  private Filter filter = null;
  /** 検索limit */
  private Limit limit = null;

  /**
   * <pre>
   * セットマスタ(メーカー)登録.
   * </pre>
   * 
   * @param setMasterMakerModel 登録する項目（優良設定詳細コード１ など）の連係情報
   * @return 登録したセットマスタ(メーカー)情報の件数
   */
  @Override
  public int insertSetMasterMakerList(SetMasterMakerModel setMasterMakerModel) {
    // 登録
    setMasterMakerDao.batchInsert(setMasterMakerModel.getSetMasterMakerList());
    // 登録した件数を返却する
    return setMasterMakerModel.getSetMasterMakerList().size();
  }

  /**
   * <pre>
   * チェックリスト画面のセットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param count 現在位置
   * @param maxRows maxRows
   * @param makerCode makerCode
   * @return セットマスタ(メーカー)
   */
  @Override
  public SetMasterMakerModel searchSetErrorList(Long count, Long maxRows, int makerCode) {
    SetMasterMakerModel setMasterModel = new SetMasterMakerModel();
    // データステータス = 「1:エラー有り」
    NumberFilter errorFlg = NumberFilterBuilder.equals(SetMasterMaker.ERROR_FLG, ERROR_DATA);
    NumberFilter makerCd = NumberFilterBuilder.equals(SetMasterMaker.PARTS_MAKER_CD, makerCode);
    // 削除依頼区分
    NumberFilter deleteFlg = NumberFilterBuilder.equalsIfNotNull(SetMasterMaker.LOGICAL_DEL_DIV, 0);

    Filter filterby = new AndFilter(errorFlg, makerCd, deleteFlg);

    // 順番
    Order orderby;

    FieldOrder prmSetDtlNo1 = new FieldAscOrder(SetMasterMaker.PRM_SET_DTL_NO_1);
    FieldOrder goodsMGroup = new FieldAscOrder(SetMasterMaker.GOODS_M_GROUP);
    FieldOrder blCodeOrder = new FieldAscOrder(SetMasterMaker.TBS_PARTS_CODE);
    FieldOrder primePartsNoWithH = new FieldAscOrder(SetMasterMaker.SET_SUB_PARTS_NO);
    // 画面の優良品番はテープルにありません
    orderby = new MultiFieldOrder(prmSetDtlNo1, goodsMGroup, blCodeOrder, primePartsNoWithH);

    long setCount = setMasterMakerDao.countByFilter(filterby);
    Limit limitby = Limit.NO_LIMIT;
    if (count != null) {
      limitby = new Limit(count, maxRows);
    }
    // セットマスタ(メーカー)の検索
    List<SetMasterMaker> setMasterList = setMasterMakerDao.findByFilter(filterby, limitby, orderby);
    setMasterModel.setSetMasterMakerList(setMasterList);
    setMasterModel.setSearchCounts(setCount);
    return setMasterModel;
  }

  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @param setMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @return セットマスタ(メーカー)
   */
  @Override
  public SetMasterMakerModel searchSetMasterMakerList(SetMasterSearchModel setMasterSearchModel, Long skipRows,
                                                      Long maxRows, int fileSelectMode, int mode) {
    SetMasterMakerModel setMasterMakerModel = new SetMasterMakerModel();
    // セットマスタ(メーカー)の検索
    setSearchFilter(setMasterSearchModel, fileSelectMode, mode);
    setSearchOrder(setMasterSearchModel.getSort());
    setSearchLimit(skipRows, maxRows);
    long searchCounts = setMasterMakerDao.countByFilter(this.filter);
    List<SetMasterMaker> setMasterMakerList = setMasterMakerDao.findByFilter(this.filter, this.limit, this.order);
    setMasterMakerModel.setSetMasterMakerList(setMasterMakerList);
    setMasterMakerModel.setSearchCounts(searchCounts);
    return setMasterMakerModel;
  }
  
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param setMasterMakerId セットマスタ(メーカー)ID
   * @return セットマスタ(メーカー)情報
   */
  @Override
  public SetMasterMaker searchSetById(SetMasterMakerId setMasterMakerId) {
    return setMasterMakerDao.findById(setMasterMakerId);
  }

  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @param setMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @return セットマスタ(メーカー)
   */
  @Override
  public SetMasterMakerModel searchSetMasterMakerList(SetMasterSearchModel setMasterSearchModel, int fileSelectMode,
                                                      int mode) {
    SetMasterMakerModel setMasterMakerModel = new SetMasterMakerModel();
    // セットマスタ(メーカー)の検索
    setSearchFilter(setMasterSearchModel, fileSelectMode, mode);
    setSearchOrder(setMasterSearchModel.getSort());
    long searchCounts = setMasterMakerDao.countByFilter(this.filter);
    List<SetMasterMaker> setMasterMakerList = setMasterMakerDao.findByFilter(this.filter, Limit.NO_LIMIT, this.order);
    setMasterMakerModel.setSetMasterMakerList(setMasterMakerList);
    setMasterMakerModel.setSearchCounts(searchCounts);
    return setMasterMakerModel;
  }
  
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @return セットマスタ(メーカー)
   */
  @Override
  public List<SetMasterMaker>  findAll(){
   return setMasterMakerDao.findAll();
  }

  /**
   * <pre>
   * fiter set
   * </pre>
   * 
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @param setMasterSearchModel setMasterSearchModel
   */
  private void setSearchFilter(SetMasterSearchModel setMasterSearchModel, int fileSelectMode, int mode) {
    // インポート区分
    NumberFilter importKbn = null;
    if (mode == ModeEnum.Input.getValue()) {
      // 検索入力モード
      importKbn = NumberFilterBuilder.in(SetMasterMaker.IMPORT_KBN, ImportKbnEnum.ImportApp.getValue(),
          ImportKbnEnum.Import.getValue(), ImportKbnEnum.ManualInput.getValue());
    } else if (mode == ModeEnum.Select.getValue()) {
      // 選択モード
      importKbn = NumberFilterBuilder.in(SetMasterMaker.IMPORT_KBN, ImportKbnEnum.ManualInput.getValue(),
          ImportKbnEnum.ImportApp.getValue(),ImportKbnEnum.Import.getValue());
    } else if (mode == ModeEnum.Reference.getValue()) {
      // 参照モード
      importKbn = NumberFilterBuilder.in(SetMasterMaker.IMPORT_KBN, fileSelectMode);
    } else if (mode == ModeEnum.Error.getValue()) {
      // エラー修正モード
      importKbn = NumberFilterBuilder.in(SetMasterMaker.IMPORT_KBN, fileSelectMode);
    }

    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(SetMasterMaker.PARTS_MAKER_CD,
        setMasterSearchModel.getPartsMakerCd());
    // 論理削除区分=0
    NumberFilter logicalDelDiv = NumberFilterBuilder.equalsIfNotNull(SetMasterMaker.LOGICAL_DEL_DIV,
        setMasterSearchModel.getLogicalDelDiv());
    // BLコード = 引数.BLコード
    NumberFilter tbsPartsCode = NumberFilterBuilder.equalsIfNotNull(SetMasterMaker.TBS_PARTS_CODE,
        setMasterSearchModel.getTbsPartsCode());
    // セット親品番 = 引数.セット親品番
    StringFilter setMainPartsNo = StringFilterBuilder.containsIfNotEmpty(SetMasterMaker.SET_MAIN_PARTS_NO,
        setMasterSearchModel.getSetMainPartsNo());
    // 品名 = 引数.品名
    StringFilter setKanaName = StringFilterBuilder.containsIfNotEmpty(SetMasterMaker.SET_KANA_NAME,
        setMasterSearchModel.getSetKanaName());
    // 申請状態 = 引数.申請状態
    NumberFilter applyCondition = NumberFilterBuilder.equalsIfNotNull(SetMasterMaker.APPLY_CONDITION,
        setMasterSearchModel.getApplyCondition());
    if(setMasterSearchModel.getApplyCondition() == null && mode == ModeEnum.Select.getValue()){
      applyCondition = NumberFilterBuilder.in(SetMasterMaker.APPLY_CONDITION,
          ApplyConditionEnum.NoApply.getValue(), ApplyConditionEnum.Applyagain.getValue());
    }
    // セット子品番 = 引数.セット子品番
    StringFilter setSubPartsNo = StringFilterBuilder.containsIfNotEmpty(SetMasterMaker.SET_SUB_PARTS_NO,
        setMasterSearchModel.getSetSubPartsNo());
    // 優良設定詳細コード１ = 引数.セレクトコード
    NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equalsIfNotNull(SetMasterMaker.PRM_SET_DTL_NO_1,
        setMasterSearchModel.getPrmSetDtlNo1());
    // 削除依頼区分
    NumberFilter deleteFlg = NumberFilterBuilder.equalsIfNotNull(SetMasterMaker.DELETE_FLG,
        setMasterSearchModel.getDeleteFlg());
    // 商品中分類コード = 引数.分類コード
    NumberFilter goodsMGroup = NumberFilterBuilder.equalsIfNotNull(SetMasterMaker.GOODS_M_GROUP,
        setMasterSearchModel.getGoodsMGroup());
    // set規格・特記事項(C向け) = 引数.set規格/特記
    StringFilter setSpecialNote = StringFilterBuilder.containsIfNotEmpty(SetMasterMaker.SET_SPECIAL_NOTE,
        setMasterSearchModel.getSetSpecialNote());
    // 処理区分=引数.処理区分
    NumberFilter manageKbn = NumberFilterBuilder.equalsIfNotNull(SetMasterMaker.MANAGE_KBN,
        setMasterSearchModel.getManageKbn());
    // データステータス = 引数.エラー区分
    NumberFilter errorFlg = NumberFilterBuilder.equalsIfNotNull(SetMasterMaker.ERROR_FLG,
        setMasterSearchModel.getErrorFlg());

    // 適用日付 >= 引数.適用日start
    DateFilter startTimeStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(SetMasterMaker.START_TIME,
        setMasterSearchModel.getStartTimeStart());
    // 適用日付 <= 引数.適用日end
    DateFilter startTimeEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(SetMasterMaker.START_TIME,
        setMasterSearchModel.getStartTimeEnd());
    // 作成日時 >= 引数.作成日start
    DateFilter insDtTimeStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(SetMasterMaker.INS_DT_TIME,
        setMasterSearchModel.getInsDtTimeStart());
    // 作成日時 <= 引数.作成日end
    DateFilter insDtTimeEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(SetMasterMaker.INS_DT_TIME,
        setMasterSearchModel.getInsDtTimeEnd());
    // 更新日時 >= 引数.更新日
    DateFilter updDtTimeStart = DateFilterBuilder.greaterThanOrEqualsIfNotNull(SetMasterMaker.UPD_DT_TIME,
        setMasterSearchModel.getUpdDtTimeStart());
    // 更新日時 <= 引数.更新日
    DateFilter updDtTimeEnd = DateFilterBuilder.lessThanOrEqualsIfNotNull(SetMasterMaker.UPD_DT_TIME,
        setMasterSearchModel.getUpdDtTimeEnd());

    Filter filterby = new AndFilter(importKbn, prmSetDtlNo1, partsMakerCd, goodsMGroup, tbsPartsCode, setMainPartsNo,
        setSubPartsNo, setKanaName, setSpecialNote, startTimeStart, startTimeEnd, insDtTimeStart, insDtTimeEnd,
        updDtTimeStart, updDtTimeEnd, errorFlg, applyCondition, deleteFlg, manageKbn, logicalDelDiv);
    this.filter = filterby;
  }

  /**
   * <pre>
   * sort set
   * </pre>
   * 
   * @param sort sort
   */
  private void setSearchOrder(String sort) {
    // 順番
    Order orderby = null;
    FieldOrder prmSetDtlNo1Order = new FieldAscOrder(SetMasterMaker.PRM_SET_DTL_NO_1);
    FieldOrder goodsMGroupCdOrder = new FieldAscOrder(SetMasterMaker.GOODS_M_GROUP);
    FieldOrder blCodeOrder = new FieldAscOrder(SetMasterMaker.TBS_PARTS_CODE);
    FieldOrder setMainPartsNoOrder = new FieldAscOrder(SetMasterMaker.SET_MAIN_PARTS_NO);
    FieldOrder setSubPartsNo = new FieldAscOrder(SetMasterMaker.SET_SUB_PARTS_NO);
    FieldOrder setDispOrder = new FieldAscOrder(SetMasterMaker.SET_DISP_ORDER);
    // 画面の優良品番はテープルにありません
    switch (sort) {
    case "1":
      orderby = new MultiFieldOrder(prmSetDtlNo1Order, goodsMGroupCdOrder, blCodeOrder, setMainPartsNoOrder,
          setDispOrder);
      break;
    case "2":
      orderby = new MultiFieldOrder(prmSetDtlNo1Order, goodsMGroupCdOrder, blCodeOrder, setSubPartsNo);
      break;
    default:
      break;
    }
    this.order = orderby;
  }

  /**
   * <pre>
   * limit set
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   */
  private void setSearchLimit(Long skipRows, Long maxRows) {
    if (skipRows == null) {
      this.limit = Limit.NO_LIMIT;
      return;
    }
    this.limit = new Limit(skipRows, maxRows);
  }

  /**
   * <pre>
   * セットマスタ(メーカー)の全データ取得.
   * </pre>
   * 
   * @param makerCd makerCd
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @return セットマスタ(メーカー)の全データ
   */
  @Override
  public SetMasterMakerModel searchSetMasterMakerList(int fileSelectMode, int makerCd, int mode) {
    SetMasterMakerModel setMasterMakerModel = new SetMasterMakerModel();
    // 論理削除区分=0
    NumberFilter logicalDelDiv = NumberFilterBuilder.equalsIfNotNull(SetMasterMaker.LOGICAL_DEL_DIV,
        LogicDeleteDivEnum.Valid.getValue());
    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equalsIfNotNull(SetMasterMaker.PARTS_MAKER_CD, makerCd);
    // インポート区分
    NumberFilter importKbn = null;
    if (mode == ModeEnum.Input.getValue()) {
      // 検索入力モード
      importKbn = NumberFilterBuilder.in(SetMasterMaker.IMPORT_KBN, ImportKbnEnum.ImportApp.getValue(),
          ImportKbnEnum.Import.getValue(), ImportKbnEnum.ManualInput.getValue());
    } else if (mode == ModeEnum.Select.getValue()) {
      // 選択モード
      importKbn = NumberFilterBuilder.in(SetMasterMaker.IMPORT_KBN, ImportKbnEnum.ImportApp.getValue(),
          ImportKbnEnum.ManualInput.getValue(), ImportKbnEnum.Import.getValue());
    } else if (mode == ModeEnum.Reference.getValue()) {
      // 参照モード
      importKbn = NumberFilterBuilder.in(SetMasterMaker.IMPORT_KBN, fileSelectMode);
    } else if (mode == ModeEnum.Error.getValue()) {
      // エラー修正モード
      importKbn = NumberFilterBuilder.in(SetMasterMaker.IMPORT_KBN, fileSelectMode);
    }

    Filter filterby = new AndFilter(logicalDelDiv, partsMakerCd, importKbn);

    // セットマスタ(メーカー)の検索
    List<SetMasterMaker> setMasterMakerList = setMasterMakerDao.findByFilter(filterby, Limit.NO_LIMIT);
    setMasterMakerModel.setSetMasterMakerList(setMasterMakerList);
    return setMasterMakerModel;
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
   * @return セットマスタ(メーカー)情報
   */
  @Override
  public List<SetMasterMaker> searchSetInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                                Integer partsMakerCd, Integer logicalDeleteCode) {
    NumberFilter ftImportKbn = NumberFilterBuilder.equals(SetMasterMaker.IMPORT_KBN, importKbn);
    NumberFilter ftApplyCondition = NumberFilterBuilder.equals(SetMasterMaker.APPLY_CONDITION, applyCondition);
    StringFilter ftUpdAccountId = StringFilterBuilder.notEquals(SetMasterMaker.UPD_ACCOUNT_ID, updAccountId);
    NumberFilter ftPartsMakerCd = NumberFilterBuilder.equals(SetMasterMaker.PARTS_MAKER_CD, partsMakerCd);
    NumberFilter ftLogicalDeleteCode = NumberFilterBuilder.equals(SetMasterMaker.LOGICAL_DEL_DIV, logicalDeleteCode);
    Filter filterby = new AndFilter(ftImportKbn, ftApplyCondition, ftUpdAccountId, ftPartsMakerCd, ftLogicalDeleteCode);
    List<SetMasterMaker> setMasterList = setMasterMakerDao.findByFilter(filterby, Limit.NO_LIMIT);
    return setMasterList;
  }

  /**
   * <pre>
   * セットマスタ(メーカー)情報更新.
   * </pre>
   * 
   * @param setMasterMaker 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  @Override
  public void updateSetMasterMaker(SetMasterMaker setMasterMaker) {
    // primary key
    SetMasterMakerId setMasterId = new SetMasterMakerId();
    // 部品メーカーコード
    setMasterId.setPartsMakerCd(setMasterMaker.getPartsMakerCd());
    // 優良設定詳細コード１
    setMasterId.setPrmSetDtlNo1(setMasterMaker.getPrmSetDtlNo1());
    // セット表示順位
    setMasterId.setSetDispOrder(setMasterMaker.getSetDispOrder());
    // セット親品番
    setMasterId.setSetMainPartsNo(setMasterMaker.getSetMainPartsNo());
    SetMasterMaker setMasterResult = setMasterMakerDao.findById(setMasterId);
    if (null != setMasterResult) {
      // 申請状態
      if(setMasterMaker.getApplyCondition() == ApplyConditionEnum.Approval.getValue()){
        setMasterMaker.setApplyCondition((short)ApplyConditionEnum.Applyagain.getValue());
      }
      setMasterResult.setApplyCondition(setMasterMaker.getApplyCondition());
      // BL登録区分
      setMasterResult.setBlEntryFlg(setMasterMaker.getBlEntryFlg());
      // chekc区分
      setMasterResult.setCheckFlg(setMasterMaker.getCheckFlg());
      // delete区分
      setMasterResult.setDeleteFlg(setMasterMaker.getDeleteFlg());
      // 削除理由
      setMasterResult.setDeleteReason(setMasterMaker.getDeleteReason());
      // エラー内容
      setMasterResult.setErrorDetail(setMasterMaker.getErrorDetail());
      // error区分
      setMasterResult.setErrorFlg(setMasterMaker.getErrorFlg());
      // 商品中分類コード
      setMasterResult.setGoodsMGroup(setMasterMaker.getGoodsMGroup());
      // インポート区分
      setMasterResult.setImportKbn(setMasterMaker.getImportKbn());
      // 処理区分
      setMasterResult.setManageKbn(setMasterMaker.getManageKbn());
      // 優良部品規格・特記事項(C向け)
      setMasterResult.setPrimePartsSpecialNoteC(setMasterMaker.getPrimePartsSpecialNoteC());
      // 品名
      setMasterResult.setSetKanaName(setMasterMaker.getSetKanaName());
      // セット名称
      setMasterResult.setSetName(setMasterMaker.getSetName());
      // セットQTY
      setMasterResult.setSetQty(setMasterMaker.getSetQty());
      // セット規格・特記事項
      setMasterResult.setSetSpecialNote(setMasterMaker.getSetSpecialNote());
      // セット子品番
      setMasterResult.setSetSubPartsNo(setMasterMaker.getSetSubPartsNo());
      // 適用日時
      setMasterResult.setStartTime(setMasterMaker.getStartTime());
      // BLコード
      setMasterResult.setTbsPartsCode(setMasterMaker.getTbsPartsCode());
      setMasterMakerDao.update(setMasterResult);
    } else {
      setMasterMakerDao.insert(setMasterMaker);
    }
  }

  /** セットマスタ(メーカー)DAO */
  private GenericDao<SetMasterMaker, SetMasterMakerId> setMasterMakerDao;

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
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param setMasterMaker セットマスタ(メーカー)ID
   * @return セットマスタ(メーカー)情報
   */
  @Override
  public SetMasterMaker searchBySetMasterId(SetMasterMaker setMasterMaker) {
    SetMasterMakerId setMasterId = new SetMasterMakerId();
    setMasterId.setPartsMakerCd(setMasterMaker.getPartsMakerCd());
    setMasterId.setPrmSetDtlNo1(setMasterMaker.getPrmSetDtlNo1());
    setMasterId.setSetDispOrder(setMasterMaker.getSetDispOrder());
    setMasterId.setSetMainPartsNo(setMasterMaker.getSetMainPartsNo());
    return setMasterMakerDao.findById(setMasterId);
  }
  
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param setMasterId セットマスタ(メーカー)ID
   * @return セットマスタ(メーカー)情報
   */
  @Override
  public SetMasterMaker searchById(SetMasterMakerId setMasterId) {
    return setMasterMakerDao.findById(setMasterId);
  }
  
  /**
   * <pre>
   * セットマスタ(メーカー)delete.
   * </pre>
   * 
   * @param setMasterMakerModel セットマスタ(メーカー)ID
   */
  @Override
  public void deleteSetMasterMaker(SetMasterMakerModel setMasterMakerModel) {
     SetMasterMakerId setMasterMakerId = new SetMasterMakerId();
     for (SetMasterMaker setMasterMaker : setMasterMakerModel.getSetMasterMakerList()) {
       // 部品メーカーコード = 引数.部品メーカーコード
       setMasterMakerId.setPartsMakerCd(setMasterMaker.getPartsMakerCd());
      // セット親品番 = 引数.セット親品番
       setMasterMakerId.setSetMainPartsNo(setMasterMaker.getSetMainPartsNo());
      // セット表示順位 = 引数.セット表示順位
       setMasterMakerId.setSetDispOrder(setMasterMaker.getSetDispOrder());
      // 優良設定詳細コード１ = 引数.優良設定詳細コード１
       setMasterMakerId.setPrmSetDtlNo1(setMasterMaker.getPrmSetDtlNo1());
       SetMasterMaker setMasterResult = setMasterMakerDao.findById(setMasterMakerId);
       if (setMasterResult != null) {
         setMasterMakerDao.delete(setMasterResult);
       }
     }
  }

  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param setMasterId セットマスタ(メーカー)ID
   * @return セットマスタ(メーカー)情報
   */
  @Override
  public SetMasterMaker searchBySetMasterId(SetMasterMakerId setMasterId) {
    return setMasterMakerDao.findById(setMasterId);
  }

}
