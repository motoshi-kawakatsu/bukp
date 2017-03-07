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

import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMakerId;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.common.enums.LogicDeleteDivEnum;
import jp.co.broadleaf.breg.common.enums.ModeEnum;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterMakerModel;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterSearchModel;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterWorkMakerModel;
import jp.co.broadleaf.breg.setlist.service.SetMasterWorkService;
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
 * セットマスタ(WORK)のinterface.
 * </pre>
 */
public class SetMasterWorkServiceImpl implements SetMasterWorkService {
  /** 検索order */
  private Order order = null;
  /** 検索filter */
  private Filter filter = null;
  /** 検索limit */
  private Limit limit = null;

  /**
   * <pre>
   * セットマスタ(WORK)の取得.
   * </pre>
   * 
   * @param prmSetDtlNo1 primarykey
   * @param partsMakerCd primarykey
   * @param goodsMGroup primarykey
   * @param setMainPartsNo primarykey
   * @return entity list
   */
  public List<SetMasterWorkMaker> getSetDetail(String prmSetDtlNo1, String partsMakerCd, String goodsMGroup,
                                               String setMainPartsNo) {
    Validate.notNull(prmSetDtlNo1, "prmSetDtlNo1 must not be null");
    Validate.notNull(partsMakerCd, "partsMakerCd must not be null");
    Validate.notNull(goodsMGroup, "goodsMGroup must not be null");
    Validate.notNull(setMainPartsNo, "setMainPartsNo must not be null");

    SetMasterWorkMaker searchCmd = new SetMasterWorkMaker();
    searchCmd.setPrmSetDtlNo1(Integer.parseInt(prmSetDtlNo1));
    searchCmd.setPartsMakerCd(Integer.parseInt(partsMakerCd));
    searchCmd.setGoodsMGroup(Integer.parseInt(goodsMGroup));
    searchCmd.setSetMainPartsNo(setMainPartsNo);
    // searchCmd.setLogicalDelDiv(LogicDeleteDivEnum.Valid.getValue());
    return setMasterWorkMakerDao.findByExample(searchCmd);
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
  public SetMasterWorkMaker searchById(SetMasterWorkMakerId setMasterId) {
    return setMasterWorkMakerDao.findById(setMasterId);
  }

  /**
   * <pre>
   * セットマスタ(work)delete.
   * </pre>
   * 
   * @param setMasterWorkMakerModel セットマスタ(メーカー)ID
   */
  @Override
  public void deleteSetMasterWorkMaker(SetMasterWorkMakerModel setMasterWorkMakerModel) {
    for (SetMasterWorkMaker setMasterWorkMaker : setMasterWorkMakerModel.getSetMasterWorkMakerList()) {
      setMasterWorkMakerDao.delete(setMasterWorkMaker);
    }
  }

  /**
   * <pre>
   * セットマスタ(ワーク)情報更新.
   * </pre>
   * 
   * @param setMasterWorkMaker 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  @Override
  public void updateSetMasterWorkMaker(SetMasterWorkMaker setMasterWorkMaker) {
    // primary key
    SetMasterWorkMakerId setMasterId = new SetMasterWorkMakerId();
    // 部品メーカーコード
    setMasterId.setPartsMakerCd(setMasterWorkMaker.getPartsMakerCd());
    // 優良設定詳細コード１
    setMasterId.setPrmSetDtlNo1(setMasterWorkMaker.getPrmSetDtlNo1());
    // セット表示順位
    setMasterId.setSetDispOrder(setMasterWorkMaker.getSetDispOrder());
    // セット親品番
    setMasterId.setSetMainPartsNo(setMasterWorkMaker.getSetMainPartsNo());
    SetMasterWorkMaker setMasterResult = setMasterWorkMakerDao.findById(setMasterId);
    if (null != setMasterResult) {
      // 申請状態
      if(setMasterWorkMaker.getApplyCondition() == ApplyConditionEnum.Approval.getValue()){
        setMasterWorkMaker.setApplyCondition((short)ApplyConditionEnum.Applyagain.getValue());
      }
      setMasterResult.setApplyCondition(setMasterWorkMaker.getApplyCondition());
      // BL登録区分
      setMasterResult.setBlEntryFlg(setMasterWorkMaker.getBlEntryFlg());
      // chekc区分
      setMasterResult.setCheckFlg(setMasterWorkMaker.getCheckFlg());
      // delete区分
      setMasterResult.setDeleteFlg(setMasterWorkMaker.getDeleteFlg());
      // 削除理由
      setMasterResult.setDeleteReason(setMasterWorkMaker.getDeleteReason());
      // エラー内容
      setMasterResult.setErrorDetail(setMasterWorkMaker.getErrorDetail());
      // error区分
      setMasterResult.setErrorFlg(setMasterWorkMaker.getErrorFlg());
      // 商品中分類コード
      setMasterResult.setGoodsMGroup(setMasterWorkMaker.getGoodsMGroup());
      // インポート区分
      setMasterResult.setImportKbn(setMasterWorkMaker.getImportKbn());
      // 処理区分
      setMasterResult.setManageKbn(setMasterWorkMaker.getManageKbn());
      // 優良部品規格・特記事項(C向け)
      setMasterResult.setPrimePartsSpecialNoteC(setMasterWorkMaker.getPrimePartsSpecialNoteC());
      // 品名
      setMasterResult.setSetKanaName(setMasterWorkMaker.getSetKanaName());
      // セット名称
      setMasterResult.setSetName(setMasterWorkMaker.getSetName());
      // セットQTY
      setMasterResult.setSetQty(setMasterWorkMaker.getSetQty());
      // セット規格・特記事項
      setMasterResult.setSetSpecialNote(setMasterWorkMaker.getSetSpecialNote());
      // セット子品番
      setMasterResult.setSetSubPartsNo(setMasterWorkMaker.getSetSubPartsNo());
      // 適用日時
      setMasterResult.setStartTime(setMasterWorkMaker.getStartTime());
      // BLコード
      setMasterResult.setTbsPartsCode(setMasterWorkMaker.getTbsPartsCode());
      setMasterWorkMakerDao.update(setMasterResult);
    } else {
      setMasterWorkMakerDao.insert(setMasterWorkMaker);
    }
  }

  /**
   * <pre>
   * セットマスタ(ワーク)取得.
   * </pre>
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @param setMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param skipRows スキップ
   * @param maxRows max
   * @return セットマスタ(ワーク)
   */
  @Override
  public SetMasterWorkMakerModel searchSetMasterWorkMakerList(SetMasterSearchModel setMasterSearchModel, Long skipRows,
                                                              Long maxRows,int fileSelectMode, int mode) {
    SetMasterWorkMakerModel setMasterWorkMakerModel = new SetMasterWorkMakerModel();
    // セットマスタ(メーカー)の検索
    setSearchFilter(setMasterSearchModel,fileSelectMode, mode);
    setSearchOrder(setMasterSearchModel.getSort());
    setSearchLimit(skipRows, maxRows);
    long searchCounts = setMasterWorkMakerDao.countByFilter(this.filter);
    List<SetMasterWorkMaker> setMasterWorkMakerList = setMasterWorkMakerDao.findByFilter(this.filter, this.limit,
        this.order);
    setMasterWorkMakerModel.setSetMasterWorkMakerList(setMasterWorkMakerList);
    setMasterWorkMakerModel.setSearchCounts(searchCounts);
    return setMasterWorkMakerModel;
  }

  /**
   * <pre>
   * セットマスタ(ワーク)取得.
   * </pre>
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @param setMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @return セットマスタ(ワーク)
   */
  @Override
  public SetMasterWorkMakerModel searchSetMasterWorkMakerList(SetMasterSearchModel setMasterSearchModel,int fileSelectMode, int mode) {
    SetMasterWorkMakerModel setMasterWorkMakerModel = new SetMasterWorkMakerModel();
    // セットマスタ(メーカー)の検索
    setSearchFilter(setMasterSearchModel,fileSelectMode,  mode);
    setSearchOrder(setMasterSearchModel.getSort());
    long searchCounts = setMasterWorkMakerDao.countByFilter(this.filter);
    List<SetMasterWorkMaker> setMasterWorkMakerList = setMasterWorkMakerDao.findByFilter(this.filter, Limit.NO_LIMIT,
        this.order);
    setMasterWorkMakerModel.setSetMasterWorkMakerList(setMasterWorkMakerList);
    setMasterWorkMakerModel.setSearchCounts(searchCounts);
    return setMasterWorkMakerModel;
  }

  /**
   * <pre>
   * セットマスタ(ワーク)登録.
   * </pre>
   * 
   * @param setMasterWorkMakerModel 登録する項目（優良設定詳細コード１ など）の連係情報
   * @return 登録したセットマスタ(ワーク)情報の件数
   */
  @Override
  public int insertSetMasterWorkMakerList(SetMasterWorkMakerModel setMasterWorkMakerModel) {
    // 登録
    setMasterWorkMakerDao.batchInsert(setMasterWorkMakerModel.getSetMasterWorkMakerList());
    // 登録した件数を返却する
    return setMasterWorkMakerModel.getSetMasterWorkMakerList().size();
  }

  /**
   * <pre>
   * セットマスタ(ワーク)の全データ取得.
   * </pre>
   * @param makerCd makerCd
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @return セットマスタ(ワーク)の全データ
   */
  @Override
  public SetMasterWorkMakerModel searchSetMasterWorkMakerList(int fileSelectMode, int makerCd, int mode) {

    // セットマスタ(ワーク)の検索
    SetMasterWorkMakerModel setMasterWorkMakerModel = new SetMasterWorkMakerModel();
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
          ImportKbnEnum.Import.getValue(),ImportKbnEnum.ManualInput.getValue());
    } else if (mode == ModeEnum.Select.getValue()) {
      // 選択モード
      importKbn = NumberFilterBuilder.in(SetMasterMaker.IMPORT_KBN, ImportKbnEnum.ManualInput.getValue(),
          ImportKbnEnum.Import.getValue());
    } else if (mode == ModeEnum.Reference.getValue()) {
      // 参照モード
      importKbn = NumberFilterBuilder.in(SetMasterMaker.IMPORT_KBN, fileSelectMode);
    } else if (mode == ModeEnum.Error.getValue()) {
      // エラー修正モード
      importKbn = NumberFilterBuilder.in(SetMasterMaker.IMPORT_KBN, fileSelectMode);
    }

    Filter filterby = new AndFilter(logicalDelDiv, partsMakerCd, importKbn);

    // セットマスタ(メーカー)の検索
     List<SetMasterWorkMaker> setMasterWorkMakerList = setMasterWorkMakerDao.findByFilter(filterby, Limit.NO_LIMIT);
     setMasterWorkMakerModel.setSetMasterWorkMakerList(setMasterWorkMakerList);
    return setMasterWorkMakerModel;
    
  }

  /**
   * <pre>
   * fiter set
   * </pre>
   * 
   * @param setMasterSearchModel SetMasterSearchModel
   */
  private void setSearchFilter(SetMasterSearchModel setMasterSearchModel,int fileSelectMode, int mode) {
 // インポート区分
    NumberFilter importKbn = null;
    if (mode == ModeEnum.Input.getValue()) {
      // 検索入力モード
      importKbn = NumberFilterBuilder.in(SetMasterMaker.IMPORT_KBN, ImportKbnEnum.ImportApp.getValue(),
          ImportKbnEnum.Import.getValue(),ImportKbnEnum.ManualInput.getValue());
    } else if (mode == ModeEnum.Select.getValue()) {
      // 選択モード
      importKbn = NumberFilterBuilder.in(SetMasterMaker.IMPORT_KBN, ImportKbnEnum.ManualInput.getValue(),
          ImportKbnEnum.Import.getValue());
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
        setMasterSearchModel.getUpdDtTimeStart());

    Filter filterby = new AndFilter(importKbn,prmSetDtlNo1, partsMakerCd, goodsMGroup, tbsPartsCode, setMainPartsNo, setSubPartsNo, setKanaName,
        setSpecialNote, startTimeStart, startTimeEnd, insDtTimeStart, insDtTimeEnd, updDtTimeStart, updDtTimeEnd,
        errorFlg, applyCondition, deleteFlg, manageKbn, logicalDelDiv);
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
    if (skipRows != null) {
      this.limit = new Limit(skipRows, maxRows);
    } else {
      this.limit = Limit.NO_LIMIT;
    }
  }

  /** セットマスタ(ワーク)DAO */
  private GenericDao<SetMasterWorkMaker, SetMasterWorkMakerId> setMasterWorkMakerDao;

  /**
   * <pre>
   * セットマスタ(ワーク)DAOを設定する.
   * </pre>
   * 
   * @param setMasterWorkMakerDao セットマスタ(ワーク)DAO
   */
  @Resource
  public void setSetMasterWorkMakerDao(GenericDao<SetMasterWorkMaker, SetMasterWorkMakerId> setMasterWorkMakerDao) {
    this.setMasterWorkMakerDao = setMasterWorkMakerDao;
  }
}
