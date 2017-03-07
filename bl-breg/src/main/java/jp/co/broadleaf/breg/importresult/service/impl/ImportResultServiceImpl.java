//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                               作成担当 : magy
// 作 成 日       2017/02/07   修正内容 : インポート結果:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.importresult.service.impl;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommonId;
import jp.co.broadleaf.breg.common.entity.GoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterCommonId;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;
import jp.co.broadleaf.breg.common.entity.GoodsMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterWorkMakerId;
import jp.co.broadleaf.breg.common.entity.GoodsRateMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommonId;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMakerId;
import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMakerId;
import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommonId;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommonId;
import jp.co.broadleaf.breg.common.entity.SetMasterCommon;
import jp.co.broadleaf.breg.common.entity.SetMasterCommonId;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMakerId;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.BlEntryFlgEnum;
import jp.co.broadleaf.breg.common.enums.CheckFlgEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.common.enums.ImportTypeEnum;
import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.common.enums.OpenPriceDivEnum;
import jp.co.broadleaf.breg.importresult.model.ImportResultModel;
import jp.co.broadleaf.breg.importresult.service.ImportResultService;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.common.util.BroadleafStringUtils;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.FieldDescOrder;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.StringFilter;
import jp.co.broadleaf.framework.dao.criteria.StringFilterBuilder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

/**
 * インポート結果サビース.
 */
public class ImportResultServiceImpl implements ImportResultService {

  /**
   * インポート結果取得します.
   * 
   * @param importType インポート類型
   * @param makerCd メーカーコード
   * @param accountCd アカウントID
   * @param importKbnEnum インポート類型
   * @return インポート結果のModel
   */
  @Override
  public ImportResultModel searchAll(int importType, int makerCd, String accountCd, ImportKbnEnum importKbnEnum) {
    ImportResultModel importResultModel = new ImportResultModel();

    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter partsMakerCd = NumberFilterBuilder.equals(GoodsMasterWorkMaker.PARTS_MAKER_CD, makerCd);
    // 論理削除区分＝0:有効
    NumberFilter logicalDelDiv = NumberFilterBuilder.equals(GoodsMasterWorkMaker.LOGICAL_DEL_DIV,
        DeleteFlgEnum.NoDelete.getValue());
    // インポート区分＝0:インポート（一括申請） AND
    NumberFilter importKbn = NumberFilterBuilder.equals(GoodsMasterWorkMaker.IMPORT_KBN, importKbnEnum.getValue());
    if (importType == 1) {
      Filter filter = new AndFilter(partsMakerCd, logicalDelDiv);
      importResultModel.setItemWorkList(goodsMasterWorkMakerDao.findByFilter(filter, Limit.NO_LIMIT));

    } else {
      Filter filter = new AndFilter(partsMakerCd, logicalDelDiv, importKbn);
      importResultModel.setItemWorkList(goodsMasterWorkMakerDao.findByFilter(filter, Limit.NO_LIMIT));
    }

    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter setPartsMakerCd = NumberFilterBuilder.equals(SetMasterWorkMaker.PARTS_MAKER_CD, makerCd);
    // 論理削除区分＝0:有効
    NumberFilter setLogicalDelDiv = NumberFilterBuilder.equals(SetMasterWorkMaker.LOGICAL_DEL_DIV,
        DeleteFlgEnum.NoDelete.getValue());
    // インポート区分＝0:インポート（一括申請） AND
    NumberFilter setImportKbn = NumberFilterBuilder.equals(SetMasterWorkMaker.IMPORT_KBN, importKbnEnum.getValue());
    if (importType == 1) {
      Filter setFilter = new AndFilter(setPartsMakerCd, setLogicalDelDiv);
      importResultModel.setSetMasterWorkList(setMasterWorkMakerDao.findByFilter(setFilter, Limit.NO_LIMIT));
    } else {
      Filter setFilter = new AndFilter(setPartsMakerCd, setLogicalDelDiv, setImportKbn);
      importResultModel.setSetMasterWorkList(setMasterWorkMakerDao.findByFilter(setFilter, Limit.NO_LIMIT));
    }
    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter joinPartsMakerCd = NumberFilterBuilder.equals(JoinMasterWorkMaker.PARTS_MAKER_CD, makerCd);
    // 論理削除区分＝0:有効
    NumberFilter joinLogicalDelDiv = NumberFilterBuilder.equals(JoinMasterWorkMaker.LOGICAL_DEL_DIV,
        DeleteFlgEnum.NoDelete.getValue());
    // インポート区分＝0:インポート（一括申請） AND
    NumberFilter joinImportKbn = NumberFilterBuilder.equals(JoinMasterWorkMaker.IMPORT_KBN, importKbnEnum.getValue());
    if (importType == 1) {
      Filter joinFilter = new AndFilter(joinPartsMakerCd, joinLogicalDelDiv);
      importResultModel.setJoinMasterWorkList(joinMasterWorkMakerDao.findByFilter(joinFilter, Limit.NO_LIMIT));
    } else {
      Filter joinFilter = new AndFilter(joinPartsMakerCd, joinLogicalDelDiv, joinImportKbn);
      importResultModel.setJoinMasterWorkList(joinMasterWorkMakerDao.findByFilter(joinFilter, Limit.NO_LIMIT));
    }
    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter itemPartsMakerCd = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, makerCd);
    // 論理削除区分＝0:有効
    NumberFilter itemLogicalDelDiv = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV,
        DeleteFlgEnum.NoDelete.getValue());

    Filter itemFilter = new AndFilter(itemPartsMakerCd, itemLogicalDelDiv);
    importResultModel.setItemMasterList(goodsMasterMakerDao.findByFilter(itemFilter, Limit.NO_LIMIT));

    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter goodsRateMakerCd = NumberFilterBuilder.equals(GoodsRateMasterCommon.GOODS_MAKER, makerCd);
    Filter goodsRateFilter = new AndFilter(goodsRateMakerCd);
    List<GoodsRateMasterCommon> goodsRateMasterCommons = goodsRateMasterCommonDao.findByFilter(goodsRateFilter,
        Limit.NO_LIMIT);
    for (GoodsRateMasterCommon goodsRateMasterCommon : goodsRateMasterCommons) {
      GOODS_RATE_CODE.add(goodsRateMasterCommon.getGoodsRateGrpCode());
    }

    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter blCodeMakerCd = NumberFilterBuilder.equals(BlCodeMasterCommon.GOODS_MAKER, makerCd);
    Filter blCodeFilter = new AndFilter(blCodeMakerCd);
    List<BlCodeMasterCommon> blCodeMasterCommons = blCodeMasterCommonDao.findByFilter(blCodeFilter, Limit.NO_LIMIT);
    for (BlCodeMasterCommon blCodeMasterCommon : blCodeMasterCommons) {
      BL_CODE_SET.add(blCodeMasterCommon.getBlCode());
    }

    // 部品メーカーコード = 引数.部品メーカーコード
    NumberFilter selectMakerCd = NumberFilterBuilder.equals(SelectCodeMasterCommon.GOODS_MAKER_CD, makerCd);
    Filter selectFilter = new AndFilter(selectMakerCd);
    List<SelectCodeMasterCommon> selectCodeMasterCommons = selectCodeMasterCommonDao.findByFilter(selectFilter,
        Limit.NO_LIMIT);
    for (SelectCodeMasterCommon selectCodeMasterCommon : selectCodeMasterCommons) {
      SELECT_CODE_SET.add(selectCodeMasterCommon.getPrmSetDtlNo1());
    }
    return importResultModel;
  }

  /**
   * インポート結果のチェック.
   * 
   * @param importResultModel インポート結果のmodel
   * @return インポート結果のDto
   */
  @Override
  public ImportResultModel checkImportList(ImportResultModel importResultModel) {

    int itemErrorCount = 0;
    int setErrorCount = 0;
    int joinErrorCount = 0;
    getMessage();

    // 商品マスタ(メーカー)案のチェック
    if (!BroadleafUtils.isEmpty(importResultModel.getItemWorkList())) {
      for (GoodsMasterWorkMaker itemWork : importResultModel.getItemWorkList()) {
        if (!checkItemWork(itemWork, importResultModel)) {
          itemErrorCount += 1;
        }
      }
    }
    // セットマスタ(メーカー)案のチェック
    if (!BroadleafUtils.isEmpty(importResultModel.getSetMasterWorkList())) {
      for (SetMasterWorkMaker setMasterWorkMaker : importResultModel.getSetMasterWorkList()) {
        if (!checkSetWork(setMasterWorkMaker, importResultModel)) {
          setErrorCount += 1;
        }
      }
    }
    // 結合マスタ(メーカー)案のチェック
    if (!BroadleafUtils.isEmpty(importResultModel.getJoinMasterWorkList())) {
      for (JoinMasterWorkMaker joinMasterWorkMaker : importResultModel.getJoinMasterWorkList()) {
        if (!checkJoinWork(joinMasterWorkMaker, importResultModel)) {
          joinErrorCount += 1;
        }
      }
    }

    importResultModel.setGoodsErrorCount(itemErrorCount);
    importResultModel.setSetErrorCount(setErrorCount);
    importResultModel.setJoinErrorCount(joinErrorCount);
    updateWorkDB(importResultModel);
    return importResultModel;
  }

  /**
   * ワークDB更新登録.
   * 
   * @param importResultModel インポート結果のModel
   */
  public void updateWorkDB(ImportResultModel importResultModel) {
    if (!BroadleafUtils.isEmpty(importResultModel.getItemWorkList())) {
      for (GoodsMasterWorkMaker goodsMasterWorkMaker : importResultModel.getItemWorkList()) {
        goodsMasterWorkMakerDao.update(goodsMasterWorkMaker);
      }
    }
    if (!BroadleafUtils.isEmpty(importResultModel.getSetMasterWorkList())) {
      for (SetMasterWorkMaker setMasterWorkMaker : importResultModel.getSetMasterWorkList()) {
        setMasterWorkMakerDao.update(setMasterWorkMaker);
      }
    }
    if (!BroadleafUtils.isEmpty(importResultModel.getJoinMasterWorkList())) {
      for (JoinMasterWorkMaker joinMasterWorkMaker : importResultModel.getJoinMasterWorkList()) {
        joinMasterWorkMakerDao.update(joinMasterWorkMaker);
      }
    }
  }

  /**
   * 本DB更新登録.
   * 
   * @param makerCd メーカーコード
   * @param accountCd アカウントID
   * @param goodsType 商品インポート類型
   * @param setType セットインポート類型
   * @param joinType 結合インポート類型
   * @param importResultModel インポート結果のModel
   * @param importKbnEnum インポート類型
   * @return インポート結果のModel
   */
  @Override
  public ImportResultModel updateHonDb(int makerCd, String accountCd, ImportTypeEnum goodsType, ImportTypeEnum setType,
                                       ImportTypeEnum joinType, ImportResultModel importResultModel,
                                       ImportKbnEnum importKbnEnum) {
    checkCommonDB(makerCd, accountCd, importResultModel);
    // 全件の場合
    if (goodsType == ImportTypeEnum.All && !BroadleafUtils.isEmpty(importResultModel.getItemWorkList())) {
      // 商品マスタ(メーカー)本 削除
      // 部品メーカーコード = 引数.部品メーカーコード
      NumberFilter partsMakerCd = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, makerCd);
      // 論理削除区分＝0:有効
      NumberFilter logicalDelDiv = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV,
          DeleteFlgEnum.NoDelete.getValue());
      Filter filter = new AndFilter(partsMakerCd, logicalDelDiv);
      List<GoodsMasterMaker> goodsMasterMakers = goodsMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
      int delete = 0;
      for (GoodsMasterMaker goodsMasterMaker : goodsMasterMakers) {
        boolean invalid = true;
        for (GoodsMasterWorkMaker goodsMasterWorkMaker : importResultModel.getItemWorkInvalidList()) {
          if (goodsMasterWorkMaker.getPrmSetDtlNo1() == goodsMasterMaker.getPrmSetDtlNo1()
              && goodsMasterWorkMaker.getPartsMakerCd() == goodsMasterMaker.getPartsMakerCd()
              && BroadleafStringUtils.compareTo(goodsMasterWorkMaker.getPrimePartsNoWithH(),
                  goodsMasterMaker.getPrimePartsNoWithH()) == 0) {
            invalid = false;
            break;
          }
        }
        if (invalid) {
          goodsMasterMaker.setDeleteFlg((short) DeleteFlgEnum.Delete.getValue());
          goodsMasterMaker.setDeleteReason(DELETE_KBN);
          goodsMasterMaker.setManageKbn((short) ManageKbnEnum.Delete.getValue());
          goodsMasterMaker.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
          goodsMasterMaker.setImportKbn((short) importKbnEnum.getValue());
          goodsMasterMakerDao.update(goodsMasterMaker);
          delete += 1;
        }
      }
      importResultModel.setGoodsDeleteCount(delete);
    }
    // 全件の場合
    if (setType == ImportTypeEnum.All && !BroadleafUtils.isEmpty(importResultModel.getSetMasterWorkList())) {
      // セットマスタ(メーカー)本 削除
      // 部品メーカーコード = 引数.部品メーカーコード
      NumberFilter setPartsMakerCd = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, makerCd);
      // 論理削除区分＝0:有効
      NumberFilter setLogicalDelDiv = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV,
          DeleteFlgEnum.NoDelete.getValue());
      Filter setFilter = new AndFilter(setPartsMakerCd, setLogicalDelDiv);
      List<SetMasterMaker> setMasterMakers2 = setMasterMakerDao.findByFilter(setFilter, Limit.NO_LIMIT);
      int delete = 0;
      for (SetMasterMaker setMasterMaker : setMasterMakers2) {
        boolean invalid = true;
        for (SetMasterWorkMaker setMasterWorkMaker : importResultModel.getSetMasterWorkInvalidList()) {
          if (setMasterMaker.getPrmSetDtlNo1() == setMasterWorkMaker.getPrmSetDtlNo1()
              && setMasterMaker.getPartsMakerCd() == setMasterWorkMaker.getPartsMakerCd()
              && BroadleafStringUtils.compareTo(setMasterMaker.getSetMainPartsNo(),
                  setMasterWorkMaker.getSetMainPartsNo()) == 0
              && setMasterMaker.getSetDispOrder() == setMasterWorkMaker.getSetDispOrder()) {
            invalid = false;
            break;
          }
        }
        if (invalid) {
          setMasterMaker.setDeleteFlg((short) DeleteFlgEnum.Delete.getValue());
          setMasterMaker.setDeleteReason(DELETE_KBN);
          setMasterMaker.setManageKbn((short) ManageKbnEnum.Delete.getValue());
          setMasterMaker.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
          setMasterMaker.setImportKbn((short) importKbnEnum.getValue());
          setMasterMakerDao.update(setMasterMaker);
          delete += 1;
        }
      }
      importResultModel.setSetDeleteCount(delete);
    }
    // 全件の場合
    if (joinType == ImportTypeEnum.All && !BroadleafUtils.isEmpty(importResultModel.getJoinMasterWorkList())) {
      // 結合マスタ(メーカー)削除
      // 部品メーカーコード = 引数.部品メーカーコード 論理削除区分＝0:有効
      NumberFilter joinPartsMakerCd = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, makerCd);
      NumberFilter joinLogicalDelDiv = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV,
          DeleteFlgEnum.NoDelete.getValue());
      Filter joinFilter = new AndFilter(joinPartsMakerCd, joinLogicalDelDiv);
      List<JoinMasterMaker> joinMasterMakers2 = joinMasterMakerDao.findByFilter(joinFilter, Limit.NO_LIMIT);
      int delete = 0;
      for (JoinMasterMaker joinMasterMaker : joinMasterMakers2) {
        boolean invalid = true;
        for (JoinMasterWorkMaker joinMasterWorkMaker : importResultModel.getJoinMasterWorkInvalidList()) {
          if (joinMasterMaker.getPrmSetDtlNo1() == joinMasterWorkMaker.getPrmSetDtlNo1()
              && joinMasterMaker.getPartsMakerCd() == joinMasterWorkMaker.getPartsMakerCd()
              && joinMasterMaker.getTbsPartsCode() == joinMasterWorkMaker.getTbsPartsCode()
              && joinMasterMaker.getJoinSourceMakerCode() == joinMasterWorkMaker.getJoinSourceMakerCode()
              && joinMasterMaker.getPrmSetDtlNo2() == joinMasterWorkMaker.getPrmSetDtlNo2()
              && BroadleafStringUtils.compareTo(joinMasterMaker.getJoinSourPartsNoWithH(),
                  joinMasterWorkMaker.getJoinSourPartsNoWithH()) == 0
              && joinMasterMaker.getJoinDispOrder() == joinMasterWorkMaker.getJoinDispOrder()) {
            invalid = false;
            break;
          }
        }
        if (invalid) {
          joinMasterMaker.setDeleteFlg((short) DeleteFlgEnum.Delete.getValue());
          joinMasterMaker.setDeleteReason(DELETE_KBN);
          joinMasterMaker.setManageKbn((short) ManageKbnEnum.Delete.getValue());
          joinMasterMaker.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
          joinMasterMaker.setImportKbn((short) importKbnEnum.getValue());
          joinMasterMakerDao.update(joinMasterMaker);
          delete += 1;
        }
      }
      importResultModel.setJoinDeleteCount(delete);
    }
    importResultModel = updateDB(makerCd, accountCd, goodsType, setType, joinType, importResultModel, importKbnEnum);
    return importResultModel;
  }

  /**
   * 商品マスタ(メーカー)案のチェック.
   * 
   * @param itemWork 商品マスタ(メーカー)案
   * @param importResultModel インポート結果のModel
   * @return 「True」：エラーなし、「False」：エラーあり
   */
  private boolean checkItemWork(GoodsMasterWorkMaker itemWork, ImportResultModel importResultModel) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(checkGoods01(itemWork));
    // check 2.1.4 オープン価格チェック
    if (itemWork.getOpenPriceDiv().intValue() == OpenPriceDivEnum.OpenPrice.getValue() && itemWork.getNewPrice() != 0) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00303) + HALF_SEMICOLON);
    }

    // check2.1.7セレクトコード、分類コード、BLコード存在チェック
    if (SELECT_CODE_SET.add(itemWork.getPrmSetDtlNo1()) ? SELECT_CODE_SET.remove(itemWork.getPrmSetDtlNo1()) : false) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00008).replace(REPLACE, PRM_SET_DTL_NO_1) + HALF_SEMICOLON);
    }
    if (itemWork.getTbsPartsCode() != null && BL_CODE_SET.add(itemWork.getTbsPartsCode())
        ? BL_CODE_SET.remove(itemWork.getTbsPartsCode()) : false) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00008).replace(REPLACE, TBS_PARTS_CODE) + HALF_SEMICOLON);
    }
    if (itemWork.getGoodsMGroup() != null && GOODS_RATE_CODE.add(itemWork.getGoodsMGroup())
        ? GOODS_RATE_CODE.remove(itemWork.getGoodsMGroup()) : false) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00008).replace(REPLACE, GOODS_M_GROUP) + HALF_SEMICOLON);
    }

    // check 2.1.8 削除申請理由チェック
    if (itemWork.getDeleteFlg() != null && itemWork.getDeleteFlg() == DeleteFlgEnum.Delete.getValue()
        && (itemWork.getDeleteReason() == null || itemWork.getDeleteReason().isEmpty())) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00017) + HALF_SEMICOLON);
    }

    boolean errorExistFlag = false;
    if (stringBuffer.length() == 0) {
      itemWork.setErrorDetail(stringBuffer.toString());
      itemWork.setErrorFlg((short) ErrorFlgEnum.NoError.getValue());
      itemWork.setCheckFlg((short) CheckFlgEnum.Checked.getValue());
      errorExistFlag = true;
    } else {
      itemWork.setErrorDetail(stringBuffer.toString());
      itemWork.setErrorFlg((short) ErrorFlgEnum.Error.getValue());
      itemWork.setCheckFlg((short) CheckFlgEnum.Checked.getValue());
      errorExistFlag = false;
    }
    // check 2.1.3 価格ゼロチェック
    if (itemWork.getNewPrice() == null || itemWork.getNewPrice() == 0) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.W00201) + HALF_SEMICOLON);
      itemWork.setErrorDetail(stringBuffer.toString());
    }
    return errorExistFlag;
  }

  /**
   * セットマスタ(メーカー)案のチェック.
   * 
   * @param setMasterWork セットマスタ(メーカー)案
   * @param importResultModel インポート結果のModel
   * @return 「True」：エラーなし、「False」：エラーあり
   */
  private boolean checkSetWork(SetMasterWorkMaker setMasterWork, ImportResultModel importResultModel) {
    StringBuffer stringBuffer = new StringBuffer();
    // check 2.2.1 必須項目チェック check 2.1.8 削除申請理由チェック check 2.2.9 親子品番重複チェック
    stringBuffer.append(checkSet01(setMasterWork));
    // check 2.2.3 商品マスタ存在チェック
    if (!BroadleafStringUtils.isEmpty(setMasterWork.getSetMainPartsNo())) {
      NumberFilter ftLogicalDelDiv = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV,
          DeleteFlgEnum.NoDelete.getValue());
      NumberFilter ftPrmSetDtlNo1 = NumberFilterBuilder.equals(GoodsMasterMaker.PRM_SET_DTL_NO_1,
          setMasterWork.getPrmSetDtlNo1());
      NumberFilter ftPartsMakerCd = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD,
          setMasterWork.getPartsMakerCd());
      StringFilter ftPrimePartsNoWithH = StringFilterBuilder.equals(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H,
          setMasterWork.getSetMainPartsNo());
      NumberFilter blCode = NumberFilterBuilder.equals(GoodsMasterMaker.BL_ENTRY_FLG,
          BlEntryFlgEnum.Registered.getValue());
      Filter filter = new AndFilter(ftLogicalDelDiv, ftPrmSetDtlNo1, ftPartsMakerCd, ftPrimePartsNoWithH, blCode);
      List<GoodsMasterMaker> goods = goodsMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
      if (!BroadleafUtils.isEmpty(goods)) {
        GoodsMasterMaker goodsMasterMaker = goods.get(0);
        // check 2.2.4 商品マスタ状態チェック
        // 未登録
        if (goodsMasterMaker.getPrimePartsNoWithH().equals(setMasterWork.getSetMainPartsNo())
            && goodsMasterMaker.getApplyCondition() != null
            && goodsMasterMaker.getApplyCondition() == ApplyConditionEnum.NoApply.getValue()) {
          stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00502).replace(REPLACE, SET_MAIN_PARTS_NO)
              .replace(REPLACE2, setMasterWork.getSetMainPartsNo()).replace(REPLACE3, NO_APPLY) + HALF_SEMICOLON);
        }
        // 申請中
        if (goodsMasterMaker.getPrimePartsNoWithH().equals(setMasterWork.getSetMainPartsNo())
            && goodsMasterMaker.getApplyCondition() != null
            && goodsMasterMaker.getApplyCondition() == ApplyConditionEnum.Apply.getValue()) {
          stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00502).replace(REPLACE, SET_MAIN_PARTS_NO)
              .replace(REPLACE2, setMasterWork.getSetMainPartsNo()).replace(REPLACE3, APPLY) + HALF_SEMICOLON);
        }
      } else {
        stringBuffer
            .append(MESSAGE_MAP.get(BregMessageCodes.E00501).replace(REPLACE, SET_MAIN_PARTS_NO) + HALF_SEMICOLON);
      }
    }
    if (!BroadleafStringUtils.isEmpty(setMasterWork.getSetSubPartsNo())) {
      NumberFilter ftLogicalDelDiv = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV,
          DeleteFlgEnum.NoDelete.getValue());
      NumberFilter ftPrmSetDtlNo1 = NumberFilterBuilder.equals(GoodsMasterMaker.PRM_SET_DTL_NO_1,
          setMasterWork.getPrmSetDtlNo1());
      NumberFilter ftPartsMakerCd = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD,
          setMasterWork.getPartsMakerCd());
      StringFilter ftPrimePartsNoWithH = StringFilterBuilder.equals(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H,
          setMasterWork.getSetSubPartsNo());
      NumberFilter blCode = NumberFilterBuilder.equals(GoodsMasterMaker.BL_ENTRY_FLG,
          BlEntryFlgEnum.Registered.getValue());
      Filter filter = new AndFilter(ftLogicalDelDiv, ftPrmSetDtlNo1, ftPartsMakerCd, ftPrimePartsNoWithH, blCode);
      List<GoodsMasterMaker> goods = goodsMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
      if (!BroadleafUtils.isEmpty(goods)) {
        GoodsMasterMaker goodsMasterMaker = goods.get(0);

        // 未登録
        if (goodsMasterMaker.getPrimePartsNoWithH().equals(setMasterWork.getSetSubPartsNo())
            && goodsMasterMaker.getApplyCondition() != null
            && goodsMasterMaker.getApplyCondition() == ApplyConditionEnum.NoApply.getValue()) {
          stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00502).replace(REPLACE, SET_PARTS_NO)
              .replace(REPLACE2, setMasterWork.getSetSubPartsNo()).replace(REPLACE3, NO_APPLY) + HALF_SEMICOLON);
        } // 申請中
        if (goodsMasterMaker.getPrimePartsNoWithH().equals(setMasterWork.getSetSubPartsNo())
            && goodsMasterMaker.getApplyCondition() != null
            && goodsMasterMaker.getApplyCondition() == ApplyConditionEnum.Apply.getValue()) {
          stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00502).replace(REPLACE, SET_PARTS_NO)
              .replace(REPLACE2, setMasterWork.getSetSubPartsNo()).replace(REPLACE3, APPLY) + HALF_SEMICOLON);
        }

      } else {
        stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00501).replace(REPLACE, SET_PARTS_NO) + HALF_SEMICOLON);
      }
    }
    // check2.2.7セレクトコード、分類コード、BLコード存在チェック
    if (SELECT_CODE_SET.add(setMasterWork.getPrmSetDtlNo1()) ? SELECT_CODE_SET.remove(setMasterWork.getPrmSetDtlNo1())
        : false) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00008).replace(REPLACE, PRM_SET_DTL_NO_1) + HALF_SEMICOLON);
    }
    if (setMasterWork.getTbsPartsCode() != null && BL_CODE_SET.add(setMasterWork.getTbsPartsCode())
        ? BL_CODE_SET.remove(setMasterWork.getTbsPartsCode()) : false) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00008).replace(REPLACE, TBS_PARTS_CODE) + HALF_SEMICOLON);
    }
    if (setMasterWork.getGoodsMGroup() != null && GOODS_RATE_CODE.add(setMasterWork.getGoodsMGroup())
        ? GOODS_RATE_CODE.remove(setMasterWork.getGoodsMGroup()) : false) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00008).replace(REPLACE, GOODS_M_GROUP) + HALF_SEMICOLON);
    }

    if (stringBuffer.length() == 0) {
      setMasterWork.setErrorDetail(stringBuffer.toString());
      setMasterWork.setErrorFlg((short) ErrorFlgEnum.NoError.getValue());
      setMasterWork.setCheckFlg((short) CheckFlgEnum.Checked.getValue());
      return true;
    } else {
      setMasterWork.setErrorDetail(stringBuffer.toString());
      setMasterWork.setErrorFlg((short) ErrorFlgEnum.Error.getValue());
      setMasterWork.setCheckFlg((short) CheckFlgEnum.Checked.getValue());
      return false;
    }
  }

  /**
   * 結合マスタ(メーカー)案のチェック.
   * 
   * @param joinMasterWork 結合マスタ(メーカー)案
   * @param importResultModel インポート結果のModel
   * @return 「True」：エラーなし、「False」：エラーあり
   */
  private boolean checkJoinWork(JoinMasterWorkMaker joinMasterWork, ImportResultModel importResultModel) {
    StringBuffer stringBuffer = new StringBuffer();
    // 1: 必須項目チェック 2:未入力項目チェック3商品マスタ存在チェック4商品マスタ状態チェック11削除申請理由チェックcheck 2.3.6
    stringBuffer.append(checkJoin0102(joinMasterWork, importResultModel));
    boolean check10 = false;
    NumberFilter makerCdFilter = NumberFilterBuilder.equals(PuregoodsMasterCommon.PARTS_MAKER_CD,
        joinMasterWork.getPartsMakerCd());
    NumberFilter partsMakerCdFilter = NumberFilterBuilder.equals(PuregoodsMasterCommon.JOIN_SOURCE_MAKER_CODE,
        joinMasterWork.getJoinSourceMakerCode());
    StringFilter primePartsNoWithHFilter = StringFilterBuilder.equals(PuregoodsMasterCommon.PRIME_PARTS_NO_WITH_H,
        joinMasterWork.getJoinSourPartsNoWithH());
    NumberFilter selectCdFilter = NumberFilterBuilder.equals(PuregoodsMasterCommon.PRM_SET_DTL_NO_1,
        joinMasterWork.getPrmSetDtlNo1());

    Filter pureFilter = new AndFilter(makerCdFilter, partsMakerCdFilter, primePartsNoWithHFilter, selectCdFilter);
    List<PuregoodsMasterCommon> puregoodsMasterCommons = puregoodsMasterCommonDao.findByFilter(pureFilter,
        Limit.NO_LIMIT);
    if (puregoodsMasterCommons.isEmpty()) {
      // check 2.3.7
      Filter pureFilter2 = new AndFilter(selectCdFilter, makerCdFilter, partsMakerCdFilter);
      List<PuregoodsMasterCommon> puregoodsMasterCommonList = puregoodsMasterCommonDao.findByFilter(pureFilter2,
          Limit.NO_LIMIT);
      StringBuffer result = new StringBuffer();
      String joinPartNo = joinMasterWork.getJoinSourPartsNoWithH();
      joinPartNo = joinPartNo.replace(HALF_HYPHEN, EMPTY).replace(HALF_SPACE, EMPTY);
      for (PuregoodsMasterCommon puregoodsMasterCommon : puregoodsMasterCommonList) {
        String pureGoodsNo = puregoodsMasterCommon.getPrimePartsNoWithH();
        pureGoodsNo = pureGoodsNo.replace(HALF_HYPHEN, EMPTY).replace(HALF_SPACE, EMPTY);
        // 結合元品番がPM部品マスタの純正品番一覧に無く､ハイフンやスペースの違いでPM部品マスタと一致する品番があれば､
        // 品番体系をPM部品マスタの体系へ変更した一覧を表示しエラーとする｡
        if (joinPartNo.equals(pureGoodsNo)) {
          result.append(
              MESSAGE_MAP.get(BregMessageCodes.E00704).replace(REPLACE, puregoodsMasterCommon.getPrimePartsNoWithH())
                  + HALF_SEMICOLON);
          check10 = check10(puregoodsMasterCommon, joinMasterWork);
          break;
        } else {
          if (joinMasterWork.getJoinSourceMakerCode() == 5) {
            if (joinPartNo.length() == 11 || (joinPartNo.length() > 11 && Character.isDigit(joinPartNo.charAt(11)))) {
              if (joinPartNo.startsWith(pureGoodsNo)) {
                result.append(MESSAGE_MAP.get(BregMessageCodes.E00704).replace(REPLACE,
                    puregoodsMasterCommon.getPrimePartsNoWithH()) + HALF_SEMICOLON);
                check10 = check10(puregoodsMasterCommon, joinMasterWork);
                break;
              }
            } else if (joinPartNo.length() > 11 && Character.isLetter(joinPartNo.charAt(11))) {
              if (joinPartNo.equals(pureGoodsNo)) {
                result.append(MESSAGE_MAP.get(BregMessageCodes.E00704).replace(REPLACE,
                    puregoodsMasterCommon.getPrimePartsNoWithH()) + HALF_SEMICOLON);
                check10 = check10(puregoodsMasterCommon, joinMasterWork);
                break;
              }
            }
          }
        }
      }
      if (result.toString().isEmpty()) {
        stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00703) + HALF_SEMICOLON);
      } else {
        stringBuffer.append(result);
      }
    } else {
      for (PuregoodsMasterCommon puregoodsMasterCommon : puregoodsMasterCommons) {
        check10 = check10(puregoodsMasterCommon, joinMasterWork);
        break;
      }
    }
    // check2.3.12セレクトコード、分類コード、BLコード存在チェック

    if (SELECT_CODE_SET.add(joinMasterWork.getPrmSetDtlNo1()) ? SELECT_CODE_SET.remove(joinMasterWork.getPrmSetDtlNo1())
        : false) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00008).replace(REPLACE, PRM_SET_DTL_NO_1) + HALF_SEMICOLON);
    }
    if (BL_CODE_SET.add(joinMasterWork.getTbsPartsCode()) ? BL_CODE_SET.remove(joinMasterWork.getTbsPartsCode())
        : false) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00008).replace(REPLACE, TBS_PARTS_CODE) + HALF_SEMICOLON);
    }
    if (joinMasterWork.getGoodsMGroup() != null && GOODS_RATE_CODE.add(joinMasterWork.getGoodsMGroup())
        ? GOODS_RATE_CODE.remove(joinMasterWork.getGoodsMGroup()) : false) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00008).replace(REPLACE, GOODS_M_GROUP) + HALF_SEMICOLON);
    }
    boolean errorExistFlag = false;
    if (stringBuffer.length() == 0) {
      joinMasterWork.setErrorDetail(stringBuffer.toString());
      joinMasterWork.setErrorFlg((short) ErrorFlgEnum.NoError.getValue());
      joinMasterWork.setCheckFlg((short) CheckFlgEnum.Checked.getValue());
      errorExistFlag = true;
    } else {
      joinMasterWork.setErrorDetail(stringBuffer.toString());
      joinMasterWork.setErrorFlg((short) ErrorFlgEnum.Error.getValue());
      joinMasterWork.setCheckFlg((short) CheckFlgEnum.Checked.getValue());
      errorExistFlag = false;
    }
    if (check10) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.W00502) + HALF_SEMICOLON);
    }
    // check 2.3.9
    JoinMasterCommon joinMasterCommon = new JoinMasterCommon();
    joinMasterCommon.setPrmSetDtlNo1(joinMasterWork.getPrmSetDtlNo1());
    joinMasterCommon.setPartsMakerCd(joinMasterWork.getPartsMakerCd());
    joinMasterCommon.setTbsPartsCode(joinMasterWork.getTbsPartsCode());
    joinMasterCommon.setJoinSourceMakerCode(joinMasterWork.getJoinSourceMakerCode());
    joinMasterCommon.setPrmSetDtlNo2(joinMasterWork.getPrmSetDtlNo2());
    joinMasterCommon.setJoinSourPartsNoWithH(joinMasterWork.getJoinSourPartsNoWithH());
    joinMasterCommon.setJoinDispOrder(joinMasterWork.getJoinDispOrder());
    List<JoinMasterCommon> joinMasterCommons = joinMasterCommonDao.findByExample(joinMasterCommon);
    if (!joinMasterCommons.isEmpty()) {
      for (JoinMasterCommon common : joinMasterCommons) {
        if (common.getDeleteFlg() != null && common.getDeleteFlg().intValue() == DeleteFlgEnum.Delete.getValue()) {
          stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.W00501) + HALF_SEMICOLON);
          joinMasterWork.setErrorDetail(stringBuffer.toString());
          break;
        }
      }
    }
    return errorExistFlag;
  }

  /**
   * 結合マスタ(メーカー)案のチェック.
   * 
   * @param puregoodsMasterCommon 純正商品マスタ(共有)
   * @param joinMasterWork 結合マスタ
   * @return チェック結果
   */
  private boolean check10(PuregoodsMasterCommon puregoodsMasterCommon, JoinMasterWorkMaker joinMasterWork) {
    if (puregoodsMasterCommon.getTbsPartsCode() != null
        && puregoodsMasterCommon.getTbsPartsCode().equals(joinMasterWork.getTbsPartsCode())) {

      BlCodeMasterCommonId blCodeMasterCommonId = new BlCodeMasterCommonId(
          puregoodsMasterCommon.getJoinSourceMakerCode(), puregoodsMasterCommon.getTbsPartsCode());
      if (blCodeMasterCommonDao.findById(blCodeMasterCommonId) == null) {
        return true;
      }
    }
    return false;
  }

  /**
   * 商品マスタ(共有)案のチェック.
   * 
   * @param goodsMasterCommon 商品マスタ(共有)
   * @param goodsMasterWorkMaker 商品マスタ
   * @return チェック結果
   */
  private boolean checkGoodsCommon1(GoodsMasterCommon goodsMasterCommon, GoodsMasterWorkMaker goodsMasterWorkMaker) {

    if (BroadleafStringUtils.compareTo(goodsMasterCommon.getPrmSetDtlNo1(), goodsMasterWorkMaker.getPrmSetDtlNo1()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getPartsMakerCd(),
            goodsMasterWorkMaker.getPartsMakerCd()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getGoodsMGroup(),
            goodsMasterWorkMaker.getGoodsMGroup()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getTbsPartsCode(),
            goodsMasterWorkMaker.getTbsPartsCode()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getTbsPartsCode(),
            goodsMasterWorkMaker.getTbsPartsCode()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getPrimePartsKanaNm(),
            goodsMasterWorkMaker.getPrimePartsKanaNm()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getPrimePartsName(),
            goodsMasterWorkMaker.getPrimePartsName()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getNewPrice(), goodsMasterWorkMaker.getNewPrice()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getOpenPriceDiv(),
            goodsMasterWorkMaker.getOpenPriceDiv()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getJan(), goodsMasterWorkMaker.getJan()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getPartsLayerCd(),
            goodsMasterWorkMaker.getPartsLayerCd()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getEquipName(), goodsMasterWorkMaker.getEquipName()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getPrimePartsSpecialNote(),
            goodsMasterWorkMaker.getPrimePartsSpecialNoteB()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getPrimePartsSpecialNoteC(),
            goodsMasterWorkMaker.getPrimePartsSpecialNoteC()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getGoodsDetailB(),
            goodsMasterWorkMaker.getGoodsDetailB()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getGoodsDetailC(),
            goodsMasterWorkMaker.getGoodsDetailC()) == 0) {
      return true;
    }

    return false;
  }

  /**
   * セットマスタ(共有)案のチェック.
   * 
   * @param setMasterCommon セットマスタ(共有)
   * @param setMasterWorkMaker セットマスタ
   * @return チェック結果
   */
  private boolean checkSetCommon(SetMasterCommon setMasterCommon, SetMasterWorkMaker setMasterWorkMaker) {

    if (BroadleafStringUtils.compareTo(setMasterCommon.getPrmSetDtlNo1(), setMasterWorkMaker.getPrmSetDtlNo1()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getPartsMakerCd(), setMasterWorkMaker.getPartsMakerCd()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getGoodsMGroup(), setMasterWorkMaker.getGoodsMGroup()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getTbsPartsCode(), setMasterWorkMaker.getTbsPartsCode()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getSetMainPartsNo(),
            setMasterWorkMaker.getSetMainPartsNo()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getSetDispOrder(), setMasterWorkMaker.getSetDispOrder()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getSetSubPartsNo(),
            setMasterWorkMaker.getSetSubPartsNo()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getSetKanaName(), setMasterWorkMaker.getSetKanaName()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getSetName(), setMasterWorkMaker.getSetName()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getSetQty(), setMasterWorkMaker.getSetQty()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getSetApecialNote(),
            setMasterWorkMaker.getSetSpecialNote()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getPrimePartsSpecialNoteC(),
            setMasterWorkMaker.getPrimePartsSpecialNoteC()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getStartTime(), setMasterWorkMaker.getStartTime()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getDeleteReason(), setMasterWorkMaker.getDeleteReason()) == 0
        && BroadleafStringUtils.compareTo(setMasterCommon.getDeleteFlg(), setMasterWorkMaker.getDeleteFlg()) == 0) {
      return true;
    }

    return false;
  }

  /**
   * 結合マスタ(共有)案のチェック.
   * 
   * @param joinMasterCommon 結合マスタ(共有)
   * @param joinMasterWorkMaker 結合マスタ
   * @return チェック結果
   */
  private boolean checkJoinCommon(JoinMasterCommon joinMasterCommon, JoinMasterWorkMaker joinMasterWorkMaker) {

    if (BroadleafStringUtils.compareTo(joinMasterCommon.getPrmSetDtlNo1(), joinMasterWorkMaker.getPrmSetDtlNo1()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getPartsMakerCd(),
            joinMasterWorkMaker.getPartsMakerCd()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getGoodsMGroup(), joinMasterWorkMaker.getGoodsMGroup()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getTbsPartsCode(),
            joinMasterWorkMaker.getTbsPartsCode()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getJoinSourceMakerCode(),
            joinMasterWorkMaker.getJoinSourceMakerCode()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getPrmSetDtlNo2(),
            joinMasterWorkMaker.getPrmSetDtlNo2()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getJoinSourPartsNoWithH(),
            joinMasterWorkMaker.getJoinSourPartsNoWithH()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getJoinDispOrder(),
            joinMasterWorkMaker.getJoinDispOrder()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getJoinDestPartsNo(),
            joinMasterWorkMaker.getJoinDestPartsNo()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getJoinQty(), joinMasterWorkMaker.getJoinQty()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getPrimePartsSpecialNoteC(),
            joinMasterWorkMaker.getPrimePartsSpecialNoteC()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getJoinSpecialNote(),
            joinMasterWorkMaker.getJoinSpecialNote()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getStartTime(), joinMasterWorkMaker.getStartTime()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getDeleteReason(),
            joinMasterWorkMaker.getDeleteReason()) == 0
        && BroadleafStringUtils.compareTo(joinMasterCommon.getDeleteFlg(), joinMasterWorkMaker.getDeleteFlg()) == 0) {
      return true;
    }

    return false;
  }

  /**
   * 結合マスタ(メーカー)案のチェック.
   * 
   * @param goodsMasterCommon 商品マスタ(共有)
   * @param goodsMasterWorkMaker 商品マスタ
   * @return チェック結果
   */
  private boolean checkGoodsCommon2(GoodsMasterCommon goodsMasterCommon, GoodsMasterWorkMaker goodsMasterWorkMaker) {

    if (BroadleafStringUtils.compareTo(goodsMasterCommon.getGoodsSize1(), goodsMasterWorkMaker.getGoodsSize1()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getGoodsSize2(), goodsMasterWorkMaker.getGoodsSize2()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getGoodsSize3(), goodsMasterWorkMaker.getGoodsSize3()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getPackageSize1(),
            goodsMasterWorkMaker.getPackageSize1()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getPackageSize2(),
            goodsMasterWorkMaker.getPackageSize2()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getPackageSize3(),
            goodsMasterWorkMaker.getPackageSize3()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getSizeUnit(), goodsMasterWorkMaker.getSizeUnit()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getGoodsWeight(),
            goodsMasterWorkMaker.getGoodsWeight()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getWeightUnit(), goodsMasterWorkMaker.getWeightUnit()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getUrl1(), goodsMasterWorkMaker.getUrl1()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getUrl2(), goodsMasterWorkMaker.getUrl2()) == 0
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getUrl3(), goodsMasterWorkMaker.getUrl3()) == 0

        && BroadleafStringUtils.compareTo(goodsMasterCommon.getStartTime(), goodsMasterWorkMaker.getStartTime()) == 0

        && BroadleafStringUtils.compareTo(goodsMasterCommon.getDeleteReason(),
            goodsMasterWorkMaker.getDeleteReason()) == 0
        && goodsMasterWorkMaker.getImageNo() != null
        && goodsMasterCommon.getImageNo() == goodsMasterWorkMaker.getImageNo()
        && BroadleafStringUtils.compareTo(goodsMasterCommon.getDeleteFlg(), goodsMasterWorkMaker.getDeleteFlg()) == 0) {
      return true;
    }

    return false;

  }

  /**
   * 結合マスタ(メーカー)案のチェック.
   * 
   * @param joinMasterWork 結合マスタ
   * @param importResultModel インポート結果のModel
   * @return チェック結果
   */
  private String checkJoin0102(JoinMasterWorkMaker joinMasterWork, ImportResultModel importResultModel) {

    StringBuffer stringBuffer = new StringBuffer();
    // check 2.3.2 未入力項目チェック
    if (joinMasterWork.getStartTime() == null) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, START_TIME) + HALF_SEMICOLON);
    }
    if (joinMasterWork.getJoinQty() == null) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, JOIN_QTY) + HALF_SEMICOLON);
    }
    if (joinMasterWork.getGoodsMGroup() == null) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, GOODS_M_GROUP) + HALF_SEMICOLON);
    }
    // check 2.3.11 削除申請理由チェック
    if (joinMasterWork.getDeleteFlg() != null && joinMasterWork.getDeleteFlg() == DeleteFlgEnum.Delete.getValue()
        && (joinMasterWork.getDeleteReason() == null || joinMasterWork.getDeleteReason().isEmpty())) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00017) + HALF_SEMICOLON);
    }

    // check 2.3.3 check 2.3.4
    if (!BroadleafStringUtils.isEmpty(joinMasterWork.getJoinDestPartsNo())) {

      NumberFilter ftLogicalDelDiv = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV,
          DeleteFlgEnum.NoDelete.getValue());
      NumberFilter ftPrmSetDtlNo1 = NumberFilterBuilder.equals(GoodsMasterMaker.PRM_SET_DTL_NO_1,
          joinMasterWork.getPrmSetDtlNo1());
      NumberFilter ftPartsMakerCd = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD,
          joinMasterWork.getPartsMakerCd());
      StringFilter ftPrimePartsNoWithH = StringFilterBuilder.equals(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H,
          joinMasterWork.getJoinDestPartsNo());
      NumberFilter blCode = NumberFilterBuilder.equals(GoodsMasterMaker.BL_ENTRY_FLG,
          BlEntryFlgEnum.Registered.getValue());
      Filter filter = new AndFilter(ftLogicalDelDiv, ftPrmSetDtlNo1, ftPartsMakerCd, ftPrimePartsNoWithH, blCode);
      List<GoodsMasterMaker> goods = goodsMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
      if (!BroadleafUtils.isEmpty(goods)) {
        GoodsMasterMaker goodsMasterMaker = goods.get(0);
        if (joinMasterWork.getJoinDestPartsNo().equals(goodsMasterMaker.getPrimePartsNoWithH())
            && goodsMasterMaker.getApplyCondition() != null
            && goodsMasterMaker.getApplyCondition() == ApplyConditionEnum.NoApply.getValue()) {
          stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00702)
              .replace(REPLACE, joinMasterWork.getJoinDestPartsNo()).replace(REPLACE2, NO_APPLY) + HALF_SEMICOLON);
        }
        if (joinMasterWork.getJoinDestPartsNo().equals(goodsMasterMaker.getPrimePartsNoWithH())
            && goodsMasterMaker.getApplyCondition() != null
            && goodsMasterMaker.getApplyCondition() == ApplyConditionEnum.Apply.getValue()) {
          stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00702)
              .replace(REPLACE, joinMasterWork.getJoinDestPartsNo()).replace(REPLACE2, APPLY) + HALF_SEMICOLON);
        }

      } else {
        stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00701) + HALF_SEMICOLON);
      }
    }
    // check 2.3.12 優良品番の重複チェック
    for (JoinMasterWorkMaker joinMasterWorkMaker : importResultModel.getJoinMasterWorkList()) {
      if (BroadleafStringUtils.compareTo(joinMasterWork.getUuid(), joinMasterWorkMaker.getUuid()) != 0
          && BroadleafStringUtils.compareTo(joinMasterWork.getPrmSetDtlNo1(),
              joinMasterWorkMaker.getPrmSetDtlNo1()) == 0
          && BroadleafStringUtils.compareTo(joinMasterWork.getPartsMakerCd(),
              joinMasterWorkMaker.getPartsMakerCd()) == 0
          && BroadleafStringUtils.compareTo(joinMasterWork.getTbsPartsCode(),
              joinMasterWorkMaker.getTbsPartsCode()) == 0
          && BroadleafStringUtils.compareTo(joinMasterWork.getJoinSourceMakerCode(),
              joinMasterWorkMaker.getJoinSourceMakerCode()) == 0
          && BroadleafStringUtils.compareTo(joinMasterWork.getJoinSourPartsNoWithH(),
              joinMasterWorkMaker.getJoinSourPartsNoWithH()) == 0
          && BroadleafStringUtils.compareTo(joinMasterWork.getJoinDestPartsNo(),
              joinMasterWorkMaker.getJoinDestPartsNo()) == 0) {
        stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00301) + HALF_SEMICOLON);
        break;
      }
    }
    return stringBuffer.toString();
  }

  /**
   * 商品マスタ(メーカー)案のチェック.
   * 
   * @param itemWork 商品マスタ
   * @return チェック結果
   */
  private String checkGoods01(GoodsMasterWorkMaker itemWork) {

    StringBuffer stringBuffer = new StringBuffer();
    // check 2.1.2 未入力項目チェック
    if (itemWork.getImageNo() == null) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, IMAGE_NO) + HALF_SEMICOLON);
    }
    if (itemWork.getStartTime() == null) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, START_TIME) + HALF_SEMICOLON);
    }
    if (itemWork.getGoodsMGroup() == null) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, GOODS_M_GROUP) + HALF_SEMICOLON);
    }
    if (itemWork.getTbsPartsCode() == null) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, TBS_PARTS_CODE) + HALF_SEMICOLON);
    }
    if (BroadleafStringUtils.isEmpty(itemWork.getPrimePartsName())) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, PRIME_PARTS_NAME) + HALF_SEMICOLON);
    }
    if (BroadleafStringUtils.isEmpty(itemWork.getPrimePartsKanaNm())) {
      stringBuffer
          .append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, PRIME_PARTS_KANA_NAME) + HALF_SEMICOLON);
    }
    if (itemWork.getNewPrice() == null) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, NEW_PRICE) + HALF_SEMICOLON);
    }
    if (itemWork.getOpenPriceDiv() == null) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, OPEN_PRICE) + HALF_SEMICOLON);
    }
    return stringBuffer.toString();
  }

  /**
   * セットマスタ(メーカー)案のチェック.
   * 
   * @param setMasterWork セットマスタ
   * @return チェック結果
   */
  private String checkSet01(SetMasterWorkMaker setMasterWork) {

    StringBuffer stringBuffer = new StringBuffer();
    // check 2.2.2 未入力項目チェック
    if (setMasterWork.getStartTime() == null) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, START_TIME) + HALF_SEMICOLON);
    }
    if (setMasterWork.getGoodsMGroup() == null) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, GOODS_M_GROUP) + HALF_SEMICOLON);
    }
    if (setMasterWork.getTbsPartsCode() == null) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, TBS_PARTS_CODE) + HALF_SEMICOLON);
    }
    if (BroadleafStringUtils.isEmpty(setMasterWork.getSetSubPartsNo())) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, SET_PARTS_NO) + HALF_SEMICOLON);
    }
    if (setMasterWork.getSetQty() == null) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, SET_QTY) + HALF_SEMICOLON);
    }
    if (BroadleafStringUtils.isEmpty(setMasterWork.getSetName())) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, SET_NAME) + HALF_SEMICOLON);
    }
    if (BroadleafStringUtils.isEmpty(setMasterWork.getSetKanaName())) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00002).replace(REPLACE, SET_KANA_NAME) + HALF_SEMICOLON);
    }
    // check 2.1.8 削除申請理由チェック
    if (setMasterWork.getDeleteFlg() != null && setMasterWork.getDeleteFlg() == DeleteFlgEnum.Delete.getValue()
        && (setMasterWork.getDeleteReason() == null || setMasterWork.getDeleteReason().isEmpty())) {
      stringBuffer.append(MESSAGE_MAP.get(BregMessageCodes.E00017) + HALF_SEMICOLON);
    }

    // check 2.1.9 親子品番重複チェック

    if (setMasterWork.getSetSubPartsNo() != null
        && BroadleafStringUtils.compareTo(setMasterWork.getSetMainPartsNo(), setMasterWork.getSetSubPartsNo()) == 0) {
      stringBuffer.append("親商品と子商品が同一商品を指定できません。" + HALF_SEMICOLON);
    }
    return stringBuffer.toString();
  }

  /**
   * 本DB更新登録.
   * 
   * @param makerCd メーカーコード
   * @param accountCd アカウントID
   * @param goodsType 商品インポート類型
   * @param setType セットインポート類型
   * @param joinType 結合インポート類型
   * @param importResultModel インポート結果のModel
   * @param importKbnEnum インポート類型
   * @return インポート結果のModel
   */
  private ImportResultModel updateDB(int makerCd, String accountCd, ImportTypeEnum goodsType, ImportTypeEnum setType,
                                     ImportTypeEnum joinType, ImportResultModel importResultModel,
                                     ImportKbnEnum importKbnEnum) {
    int goodsUpdate = 0;
    int goodsInsert = 0;
    if (goodsType != ImportTypeEnum.NoImport && !BroadleafUtils.isEmpty(importResultModel.getItemWorkList())) {
      for (GoodsMasterWorkMaker goodsMasterWorkMaker : importResultModel.getItemWorkList()) {
        GoodsMasterMakerId goodsMasterMakerId = new GoodsMasterMakerId(goodsMasterWorkMaker.getPrmSetDtlNo1(),
            goodsMasterWorkMaker.getPartsMakerCd(), goodsMasterWorkMaker.getPrimePartsNoWithH());
        GoodsMasterMaker goodsMasterMaker = toAnotherObj(goodsMasterWorkMaker, GoodsMasterMaker.class);
        GoodsMasterMaker entity = goodsMasterMakerDao.findById(goodsMasterMakerId);
        if (entity == null) {
          goodsMasterMaker.setLogicalDelDiv(DeleteFlgEnum.NoDelete.getValue());
          goodsMasterMaker.setManageKbn((short) ManageKbnEnum.Add.getValue());
          goodsMasterMaker.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
          goodsMasterMaker.setImportKbn((short) importKbnEnum.getValue());
          goodsMasterMakerDao.insert(goodsMasterMaker);
          goodsInsert += 1;
        } else {
          goodsMasterMaker.setUpdDtTime(entity.getUpdDtTime());
          if (goodsMasterWorkMaker.getDeleteFlg() != null
              && goodsMasterWorkMaker.getDeleteFlg() == DeleteFlgEnum.Delete.getValue()) {
            entity.setDeleteFlg((short) DeleteFlgEnum.Delete.getValue());
            entity.setDeleteReason(goodsMasterWorkMaker.getDeleteReason());
            entity.setManageKbn((short) ManageKbnEnum.Delete.getValue());
            entity.setImportKbn((short) importKbnEnum.getValue());
            entity.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
            goodsMasterMakerDao.update(entity);
          } else {
            if (entity.getLogicalDelDiv() == DeleteFlgEnum.NoDelete.getValue()) {
              goodsMasterMaker.setLogicalDelDiv(DeleteFlgEnum.NoDelete.getValue());
              goodsMasterMaker.setManageKbn((short) ManageKbnEnum.Update.getValue());
              goodsMasterMaker.setBlEntryFlg(entity.getBlEntryFlg());
              goodsMasterMaker.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
              goodsMasterMaker.setImportKbn((short) importKbnEnum.getValue());
              goodsMasterMakerDao.update(goodsMasterMaker);
              goodsUpdate += 1;
            }
          }
        }
      }
    }
    int setUpdate = 0;
    int setInsert = 0;
    if (setType != ImportTypeEnum.NoImport && !BroadleafUtils.isEmpty(importResultModel.getSetMasterWorkList())) {
      for (SetMasterWorkMaker setMasterWorkMaker : importResultModel.getSetMasterWorkList()) {
        SetMasterMakerId setMasterMakerId = new SetMasterMakerId(setMasterWorkMaker.getPrmSetDtlNo1(),
            setMasterWorkMaker.getPartsMakerCd(), setMasterWorkMaker.getSetMainPartsNo(),
            setMasterWorkMaker.getSetDispOrder());
        SetMasterMaker setMasterMaker = toAnotherObj(setMasterWorkMaker, SetMasterMaker.class);
        SetMasterMaker setEntity = setMasterMakerDao.findById(setMasterMakerId);
        if (setEntity == null) {
          setMasterMaker.setLogicalDelDiv(DeleteFlgEnum.NoDelete.getValue());
          setMasterMaker.setManageKbn((short) ManageKbnEnum.Add.getValue());
          setMasterMaker.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
          setMasterMaker.setImportKbn((short) importKbnEnum.getValue());
          setMasterMakerDao.insert(setMasterMaker);
          setInsert += 1;
        } else {
          setMasterMaker.setUpdDtTime(setEntity.getUpdDtTime());
          if (setMasterWorkMaker.getDeleteFlg() != null
              && setMasterWorkMaker.getDeleteFlg() == DeleteFlgEnum.Delete.getValue()) {
            setEntity.setManageKbn((short) ManageKbnEnum.Delete.getValue());
            setEntity.setDeleteFlg((short) DeleteFlgEnum.Delete.getValue());
            setEntity.setDeleteReason(setMasterWorkMaker.getDeleteReason());
            setEntity.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
            setEntity.setImportKbn((short) importKbnEnum.getValue());
            setMasterMakerDao.update(setEntity);
          } else {
            if (setEntity.getLogicalDelDiv() == DeleteFlgEnum.NoDelete.getValue()) {
              setMasterMaker.setLogicalDelDiv(DeleteFlgEnum.NoDelete.getValue());
              setMasterMaker.setManageKbn((short) ManageKbnEnum.Update.getValue());
              setMasterMaker.setBlEntryFlg(setEntity.getBlEntryFlg());
              setMasterMaker.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
              setMasterMaker.setImportKbn((short) importKbnEnum.getValue());
              setMasterMakerDao.update(setMasterMaker);
              setUpdate += 1;
            }
          }
        }
      }
    }
    int joinUpdate = 0;
    int joinInsert = 0;
    if (joinType != ImportTypeEnum.NoImport && !BroadleafUtils.isEmpty(importResultModel.getJoinMasterWorkList())) {
      for (JoinMasterWorkMaker joinMasterWorkMaker : importResultModel.getJoinMasterWorkList()) {
        JoinMasterMakerId joinMasterMakerId = new JoinMasterMakerId(joinMasterWorkMaker.getPrmSetDtlNo1(),
            joinMasterWorkMaker.getPartsMakerCd(), joinMasterWorkMaker.getTbsPartsCode(),
            joinMasterWorkMaker.getJoinSourceMakerCode(), joinMasterWorkMaker.getPrmSetDtlNo2(),
            joinMasterWorkMaker.getJoinSourPartsNoWithH(), joinMasterWorkMaker.getJoinDispOrder());
        JoinMasterMaker joinMasterMaker = toAnotherObj(joinMasterWorkMaker, JoinMasterMaker.class);
        JoinMasterMaker joinEntity = joinMasterMakerDao.findById(joinMasterMakerId);
        if (joinEntity == null) {
          joinMasterMaker.setLogicalDelDiv(DeleteFlgEnum.NoDelete.getValue());
          joinMasterMaker.setManageKbn((short) ManageKbnEnum.Add.getValue());
          joinMasterMaker.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
          joinMasterMaker.setImportKbn((short) importKbnEnum.getValue());
          joinMasterMakerDao.insert(joinMasterMaker);
          joinInsert += 1;
        } else {
          joinMasterMaker.setUpdDtTime(joinEntity.getUpdDtTime());
          if (joinMasterWorkMaker.getDeleteFlg() != null
              && joinMasterWorkMaker.getDeleteFlg() == DeleteFlgEnum.Delete.getValue()) {
            joinEntity.setDeleteFlg((short) DeleteFlgEnum.Delete.getValue());
            joinEntity.setManageKbn((short) ManageKbnEnum.Delete.getValue());
            joinEntity.setDeleteReason(joinMasterWorkMaker.getDeleteReason());
            joinEntity.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
            joinEntity.setImportKbn((short) importKbnEnum.getValue());
            joinMasterMakerDao.update(joinEntity);
          } else {
            if (joinEntity.getLogicalDelDiv() == DeleteFlgEnum.NoDelete.getValue()) {
              joinMasterMaker.setLogicalDelDiv(DeleteFlgEnum.NoDelete.getValue());
              joinMasterMaker.setManageKbn((short) ManageKbnEnum.Update.getValue());
              joinMasterMaker.setBlEntryFlg(joinEntity.getBlEntryFlg());
              joinMasterMaker.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
              joinMasterMaker.setImportKbn((short) importKbnEnum.getValue());
              joinMasterMakerDao.update(joinMasterMaker);
              joinUpdate += 1;
            }
          }
        }
      }
    }
    int goodsInvalid = importResultModel.getItemWorkInvalidList().size();
    int setInvalid = importResultModel.getSetMasterWorkInvalidList().size();
    int joinInvalid = importResultModel.getJoinMasterWorkInvalidList().size();

    int goodsDelete = 0;
    int setDelete = 0;
    int joinDelete = 0;

    int goodsTotal = 0;
    int setTotal = 0;
    int joinTotal = 0;

    if (goodsType == ImportTypeEnum.All) {
      goodsTotal = importResultModel.getGoodsDeleteCount() + goodsInsert;
      goodsDelete = importResultModel.getGoodsDeleteCount() - goodsUpdate;
    } else {
      goodsTotal = importResultModel.getItemWorkList().size();
      goodsDelete = importResultModel.getItemWorkList().size() - goodsInsert - goodsUpdate;
    }
    if (setType == ImportTypeEnum.All) {
      setTotal = importResultModel.getSetDeleteCount() + setInsert;
      setDelete = importResultModel.getSetDeleteCount() - setUpdate;
    } else {
      setTotal = importResultModel.getSetMasterWorkList().size();
      setDelete = importResultModel.getSetMasterWorkList().size() - setInsert - setUpdate;
    }
    if (joinType == ImportTypeEnum.All) {
      joinTotal = importResultModel.getJoinDeleteCount() + joinInsert;
      joinDelete = importResultModel.getJoinDeleteCount() - joinUpdate;
    } else {
      joinTotal = importResultModel.getJoinMasterWorkList().size();
      joinDelete = importResultModel.getJoinMasterWorkList().size() - joinInsert - joinUpdate;
    }
    int[] goodsInfo = new int[] { goodsTotal, goodsInsert, goodsUpdate, goodsDelete, goodsInvalid };
    int[] setInfo = new int[] { setTotal, setInsert, setUpdate, setDelete, setInvalid };
    int[] joinInfo = new int[] { joinTotal, joinInsert, joinUpdate, joinDelete, joinInvalid };
    importResultModel.setGoodsInfo(goodsInfo);
    importResultModel.setSetInfo(setInfo);
    importResultModel.setJoinInfo(joinInfo);
    return importResultModel;
  }

  /**
   * 本DB更新登録.
   * 
   * @param makerCd メーカーコード
   * @param accountCd アカウントID
   * @param importResultModel インポート結果のModel
   * @return インポート結果のModel
   */
  private ImportResultModel checkCommonDB(int makerCd, String accountCd, ImportResultModel importResultModel) {

    List<GoodsMasterWorkMaker> goodsMasterWorkMakers = new ArrayList<>();
    for (Iterator<?> iterator = importResultModel.getItemWorkList().iterator(); iterator.hasNext();) {
      GoodsMasterWorkMaker goodsMasterWorkMaker = (GoodsMasterWorkMaker) iterator.next();
      // 部品メーカーコード = 引数.部品メーカーコード
      NumberFilter partsMakerCd = NumberFilterBuilder.equals(GoodsMasterCommon.PARTS_MAKER_CD, makerCd);
      // 論理削除区分＝0:有効
      NumberFilter logicalDelDiv = NumberFilterBuilder.equals(GoodsMasterCommon.LOGICAL_DEL_DIV,
          DeleteFlgEnum.NoDelete.getValue());
      NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equals(GoodsMasterCommon.PRM_SET_DTL_NO_1,
          goodsMasterWorkMaker.getPrmSetDtlNo1());
      StringFilter primePartsNoWithH = StringFilterBuilder.equals(GoodsMasterCommon.PRIME_PARTS_NO_WITH_H,
          goodsMasterWorkMaker.getPrimePartsNoWithH());
      Filter filter = new AndFilter(partsMakerCd, logicalDelDiv, prmSetDtlNo1, primePartsNoWithH);
      FieldDescOrder fieldDescOrder = new FieldDescOrder(GoodsMasterCommon.INS_DT_TIME);
      List<GoodsMasterCommon> goodsMasterCommons = goodsMasterCommonDao.findByFilter(filter, Limit.FIRST,
          fieldDescOrder);
      if (!goodsMasterCommons.isEmpty()) {
        GoodsMasterCommon goodsMasterCommon = goodsMasterCommons.get(0);
        if (goodsMasterCommon.getBlEntryFlg() == ApplyConditionEnum.Apply.getValue()
            || (checkGoodsCommon1(goodsMasterCommon, goodsMasterWorkMaker)
                && checkGoodsCommon2(goodsMasterCommon, goodsMasterWorkMaker))) {
          goodsMasterWorkMakers.add(goodsMasterWorkMaker);
          iterator.remove();
        }
      }
    }
    importResultModel.setItemWorkInvalidList(goodsMasterWorkMakers);

    List<SetMasterWorkMaker> setMasterWorkMakers = new ArrayList<>();

    for (Iterator<?> iterator = importResultModel.getSetMasterWorkList().iterator(); iterator.hasNext();) {
      SetMasterWorkMaker setMasterWorkMaker = (SetMasterWorkMaker) iterator.next();
      // 部品メーカーコード = 引数.部品メーカーコード
      NumberFilter partsMakerCd = NumberFilterBuilder.equals(SetMasterCommon.PARTS_MAKER_CD, makerCd);
      // 論理削除区分＝0:有効
      NumberFilter logicalDelDiv = NumberFilterBuilder.equals(SetMasterCommon.LOGICAL_DEL_DIV,
          DeleteFlgEnum.NoDelete.getValue());
      NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equals(SetMasterCommon.PRM_SET_DTL_NO_1,
          setMasterWorkMaker.getPrmSetDtlNo1());
      StringFilter primePartsNoWithH = StringFilterBuilder.equals(SetMasterCommon.SET_MAIN_PARTS_NO,
          setMasterWorkMaker.getSetMainPartsNo());
      NumberFilter setDispOrder = NumberFilterBuilder.equals(SetMasterCommon.SET_DISP_ORDER,
          setMasterWorkMaker.getSetDispOrder());
      Filter filter = new AndFilter(partsMakerCd, logicalDelDiv, prmSetDtlNo1, primePartsNoWithH, setDispOrder);
      FieldDescOrder fieldDescOrder = new FieldDescOrder(SetMasterCommon.INS_DT_TIME);
      List<SetMasterCommon> setMasterCommons = setMasterCommonDao.findByFilter(filter, Limit.FIRST, fieldDescOrder);
      if (!setMasterCommons.isEmpty()) {
        SetMasterCommon setMasterCommon = setMasterCommons.get(0);
        if (setMasterCommon.getBlEntryFlg() == ApplyConditionEnum.Apply.getValue()
            || checkSetCommon(setMasterCommon, setMasterWorkMaker)) {
          setMasterWorkMakers.add(setMasterWorkMaker);
          iterator.remove();
        }
      }
    }

    importResultModel.setSetMasterWorkInvalidList(setMasterWorkMakers);

    List<JoinMasterWorkMaker> joinMasterWorkMakers = new ArrayList<>();
    for (Iterator<?> iterator = importResultModel.getJoinMasterWorkList().iterator(); iterator.hasNext();) {
      JoinMasterWorkMaker joinMasterWorkMaker = (JoinMasterWorkMaker) iterator.next();
      // 部品メーカーコード = 引数.部品メーカーコード
      NumberFilter partsMakerCd = NumberFilterBuilder.equals(JoinMasterCommon.PARTS_MAKER_CD, makerCd);
      // 論理削除区分＝0:有効
      NumberFilter logicalDelDiv = NumberFilterBuilder.equals(JoinMasterCommon.LOGICAL_DEL_DIV,
          DeleteFlgEnum.NoDelete.getValue());
      NumberFilter prmSetDtlNo1 = NumberFilterBuilder.equals(JoinMasterCommon.PRM_SET_DTL_NO_1,
          joinMasterWorkMaker.getPrmSetDtlNo1());
      NumberFilter prmSetDtlNo2 = NumberFilterBuilder.equals(JoinMasterCommon.PRM_SET_DTL_NO_2,
          joinMasterWorkMaker.getPrmSetDtlNo2());
      NumberFilter joinSourceMakerCode = NumberFilterBuilder.equals(JoinMasterCommon.JOIN_SOURCE_MAKER_CODE,
          joinMasterWorkMaker.getJoinSourceMakerCode());
      StringFilter primePartsNoWithH = StringFilterBuilder.equals(JoinMasterCommon.JOIN_SOUR_PARTS_NO_WITH_H,
          joinMasterWorkMaker.getJoinSourPartsNoWithH());
      NumberFilter setDispOrder = NumberFilterBuilder.equals(JoinMasterCommon.JOIN_DISP_ORDER,
          joinMasterWorkMaker.getJoinDispOrder());
      Filter filter = new AndFilter(partsMakerCd, logicalDelDiv, joinSourceMakerCode, prmSetDtlNo1, prmSetDtlNo2,
          primePartsNoWithH, setDispOrder);
      FieldDescOrder fieldDescOrder = new FieldDescOrder(SetMasterCommon.INS_DT_TIME);
      List<JoinMasterCommon> joinMasterCommons = joinMasterCommonDao.findByFilter(filter, Limit.FIRST, fieldDescOrder);
      if (!joinMasterCommons.isEmpty()) {
        JoinMasterCommon joinMasterCommon = joinMasterCommons.get(0);
        if (joinMasterCommon.getBlEntryFlg() == ApplyConditionEnum.Apply.getValue()
            || checkJoinCommon(joinMasterCommon, joinMasterWorkMaker)) {
          joinMasterWorkMakers.add(joinMasterWorkMaker);
          iterator.remove();
        }
      }
    }
    importResultModel.setJoinMasterWorkInvalidList(joinMasterWorkMakers);
    return importResultModel;
  }

  /**
   * 対象変換
   * 
   * @param oneObj 対象元
   * @param <V> 対象先のクラス
   * @param <K> 対象先のクラス
   * @param anotherClassObj 対象先のクラス
   * @return 対象先
   */
  private <K, V> V toAnotherObj(K oneObj, Class<V> anotherClassObj) {
    V anotherObj = null;
    try {
      anotherObj = anotherClassObj.newInstance();
      Class<?> oneClassObj = oneObj.getClass();

      Method[] methodFroms = oneClassObj.getDeclaredMethods();
      for (Method methodForm : methodFroms) {

        if (methodForm.getName().startsWith(GET_METHOD)) {
          String setMethodName = SET_METHOD + methodForm.getName().substring(3);
          try {
            Method methodTo = anotherClassObj.getDeclaredMethod(setMethodName, methodForm.getReturnType());
            methodForm.setAccessible(true);
            Object attrForOneObj = methodForm.invoke(oneObj);
            methodTo.setAccessible(true);
            methodTo.invoke(anotherObj, attrForOneObj);
          } catch (Exception e) {
            try {
              Method methodTo = anotherClassObj.getDeclaredMethod(setMethodName, short.class);
              methodForm.setAccessible(true);
              Object attrForOneObj = methodForm.invoke(oneObj);
              methodTo.setAccessible(true);
              methodTo.invoke(anotherObj, attrForOneObj);
            } catch (Exception e2) {
              e2.printStackTrace();
              continue;
            }
            e.printStackTrace();
            continue;
          }

        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return anotherObj;
  }

  /**
   * メッセージを取得します.
   */
  private void getMessage() {

    MESSAGE_MAP.put(BregMessageCodes.E00001, messageService.messageInfo(BregMessageCodes.E00001));
    MESSAGE_MAP.put(BregMessageCodes.E00002, messageService.messageInfo(BregMessageCodes.E00002));
    MESSAGE_MAP.put(BregMessageCodes.E00003, messageService.messageInfo(BregMessageCodes.E00003));
    MESSAGE_MAP.put(BregMessageCodes.E00008, messageService.messageInfo(BregMessageCodes.E00008));
    MESSAGE_MAP.put(BregMessageCodes.E00303, messageService.messageInfo(BregMessageCodes.E00303));
    MESSAGE_MAP.put(BregMessageCodes.E00301, messageService.messageInfo(BregMessageCodes.E00301));
    MESSAGE_MAP.put(BregMessageCodes.E00503, messageService.messageInfo(BregMessageCodes.E00503));
    MESSAGE_MAP.put(BregMessageCodes.E00502, messageService.messageInfo(BregMessageCodes.E00502));
    MESSAGE_MAP.put(BregMessageCodes.E00501, messageService.messageInfo(BregMessageCodes.E00501));
    MESSAGE_MAP.put(BregMessageCodes.E00701, messageService.messageInfo(BregMessageCodes.E00701));
    MESSAGE_MAP.put(BregMessageCodes.E00702, messageService.messageInfo(BregMessageCodes.E00702));
    MESSAGE_MAP.put(BregMessageCodes.E00703, messageService.messageInfo(BregMessageCodes.E00703));
    MESSAGE_MAP.put(BregMessageCodes.E00704, messageService.messageInfo(BregMessageCodes.E00704));
    MESSAGE_MAP.put(BregMessageCodes.E00705, messageService.messageInfo(BregMessageCodes.E00705));
    MESSAGE_MAP.put(BregMessageCodes.W00501, messageService.messageInfo(BregMessageCodes.W00501));
    MESSAGE_MAP.put(BregMessageCodes.E00017, messageService.messageInfo(BregMessageCodes.E00017));
    MESSAGE_MAP.put(BregMessageCodes.W00201, messageService.messageInfo(BregMessageCodes.W00201));
    MESSAGE_MAP.put(BregMessageCodes.W00502, messageService.messageInfo(BregMessageCodes.W00502));

  }

  /** 商品DAO */
  private GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterMakerDao;
  /** 商品DAO */
  private GenericDao<GoodsMasterWorkMaker, GoodsMasterWorkMakerId> goodsMasterWorkMakerDao;
  /** 商品DAO */
  private GenericDao<GoodsMasterCommon, GoodsMasterCommonId> goodsMasterCommonDao;
  /** 結合DAO */
  private GenericDao<JoinMasterWorkMaker, JoinMasterWorkMakerId> joinMasterWorkMakerDao;
  /** セットDAO */
  private GenericDao<SetMasterCommon, SetMasterCommonId> setMasterCommonDao;
  /** セットDAO */
  private GenericDao<SetMasterWorkMaker, SetMasterWorkMakerId> setMasterWorkMakerDao;
  /** 結合DAO */
  private GenericDao<JoinMasterMaker, JoinMasterMakerId> joinMasterMakerDao;
  /** セットDAO */
  private GenericDao<SetMasterMaker, SetMasterMakerId> setMasterMakerDao;
  /** 結合DAO **/
  private GenericDao<JoinMasterCommon, JoinMasterCommonId> joinMasterCommonDao;
  /** 純正商品マスタDAO **/
  private GenericDao<PuregoodsMasterCommon, PuregoodsMasterCommonId> puregoodsMasterCommonDao;
  /** BLコードDAO **/
  private GenericDao<BlCodeMasterCommon, BlCodeMasterCommonId> blCodeMasterCommonDao;
  /** セレクトコードDAO **/
  private GenericDao<SelectCodeMasterCommon, SelectCodeMasterCommonId> selectCodeMasterCommonDao;
  /** BLコードDAO **/
  private GenericDao<GoodsRateMasterCommon, Integer> goodsRateMasterCommonDao;

  /** MessageMap **/
  private static final HashMap<String, String> MESSAGE_MAP = new HashMap<>();
  /** BLコードSet **/
  private static final HashSet<Integer> BL_CODE_SET = new HashSet<Integer>();
  /** セレクトコードSet **/
  private static final HashSet<Integer> SELECT_CODE_SET = new HashSet<Integer>();
  /** 分類コードSet **/
  private static final HashSet<Integer> GOODS_RATE_CODE = new HashSet<Integer>();
  /** セット親品番 **/
  public static final String SET_MAIN_PARTS_NO = "セット親品番";
  /** セット表示順位 **/
  public static final String SET_DISP_ORDER = "セット表示順位";
  /** 結合元メーカーコード **/
  public static final String JOIN_SOURCE_MAKER_CODE = "結合元メーカーコード";
  /** 優良設定詳細コード２ **/
  public static final String PRM_SET_DTL_NO_2 = "優良設定詳細コード２";
  /** 優良設定詳細コード２ **/
  public static final String PRM_SET_DTL_NO_1 = "セレクトコード";
  /** 結合表示順位 **/
  public static final String JOIN_DISP_ORDER = "結合表示順位";
  /** 商品中分類コード **/
  private static final String GOODS_M_GROUP = "商品中分類コード";
  /** BLコード **/
  private static final String TBS_PARTS_CODE = "BLコード";
  /** 優良部品名称 **/
  private static final String PRIME_PARTS_NAME = "優良部品名称";
  /** 優良部品カナ名称 **/
  private static final String PRIME_PARTS_KANA_NAME = "優良部品カナ名称";
  /** 新価格 **/
  private static final String NEW_PRICE = "価格（税抜）";
  /** オープン価格区分 **/
  private static final String OPEN_PRICE = "オープン価格区分";
  /** 画像数 **/
  public static final String IMAGE_NO = "画像数";
  /** 適用日時 **/
  public static final String START_TIME = "適用日時";
  /** セット子品番 **/
  private static final String SET_PARTS_NO = "セット子品番";
  /** セットQTY **/
  private static final String SET_QTY = "セットQTY";
  /** セット名称 **/
  private static final String SET_NAME = "セット名称";
  /** 品名 **/
  private static final String SET_KANA_NAME = "品名";
  /** 結合QTY **/
  private static final String JOIN_QTY = "結合QTY";
  /** 一括削除 **/
  private static final String DELETE_KBN = "一括削除";
  /** 申請中 **/
  private static final String NO_APPLY = "未申請";
  /** 一括削除 **/
  private static final String APPLY = "申請中";
  /** ゲット **/
  private static final String GET_METHOD = "get";
  /** セット **/
  private static final String SET_METHOD = "set";
  /** 文字列:半角; **/
  private static final String HALF_SEMICOLON = ";";
  /** 文字列:半角-. */
  public static final String HALF_HYPHEN = "-";
  /** 文字列：空白文字. */
  public static final String EMPTY = "";
  /** 文字列：半角スペース. */
  public static final String HALF_SPACE = " ";
  /** 文字列：半角スペース. */
  public static final String REPLACE = "$1";
  /** 文字列：半角スペース. */
  public static final String REPLACE2 = "$2";
  /** 文字列：半角スペース. */
  public static final String REPLACE3 = "$3";
  /** Messageサービス */
  private MessageService messageService;

  /**
   * Messageサービス.
   * 
   * @param messageService サービス
   */
  @Resource
  public void setMessageService(MessageService messageService) {
    this.messageService = messageService;
  }

  /**
   * 【goodsMasterMakerDao】を設定する。
   * 
   * @param goodsMasterMakerDao 【goodsMasterMakerDao】
   */
  @Resource
  public void setGoodsMasterMakerDao(GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterMakerDao) {
    this.goodsMasterMakerDao = goodsMasterMakerDao;
  }

  /**
   * 【goodsMasterWorkMakerDao】を設定する。
   * 
   * @param goodsMasterWorkMakerDao 【goodsMasterWorkMakerDao】
   */
  @Resource
  public void setGoodsMasterWorkMakerDao(GenericDao<GoodsMasterWorkMaker, GoodsMasterWorkMakerId> goodsMasterWorkMakerDao) {
    this.goodsMasterWorkMakerDao = goodsMasterWorkMakerDao;
  }

  /**
   * 【joinMasterWorkMakerDao】を設定する。
   * 
   * @param joinMasterWorkMakerDao 【joinMasterWorkMakerDao】
   */
  @Resource
  public void setJoinMasterWorkMakerDao(GenericDao<JoinMasterWorkMaker, JoinMasterWorkMakerId> joinMasterWorkMakerDao) {
    this.joinMasterWorkMakerDao = joinMasterWorkMakerDao;
  }

  /**
   * 【setMasterWorkMakerDao】を設定する。
   * 
   * @param setMasterWorkMakerDao 【setMasterWorkMakerDao】
   */
  @Resource
  public void setSetMasterWorkMakerDao(GenericDao<SetMasterWorkMaker, SetMasterWorkMakerId> setMasterWorkMakerDao) {
    this.setMasterWorkMakerDao = setMasterWorkMakerDao;
  }

  /**
   * 【joinMasterMakerDao】を設定する。
   * 
   * @param joinMasterMakerDao 【joinMasterMakerDao】
   */
  @Resource
  public void setJoinMasterMakerDao(GenericDao<JoinMasterMaker, JoinMasterMakerId> joinMasterMakerDao) {
    this.joinMasterMakerDao = joinMasterMakerDao;
  }

  /**
   * 【setMasterMakerDao】を設定する。
   * 
   * @param setMasterMakerDao 【setMasterMakerDao】
   */
  @Resource
  public void setSetMasterMakerDao(GenericDao<SetMasterMaker, SetMasterMakerId> setMasterMakerDao) {
    this.setMasterMakerDao = setMasterMakerDao;
  }

  /**
   * 【joinMasterCommonDao】を取得する。
   *
   * @return 【joinMasterCommonDao】
   */
  public GenericDao<JoinMasterCommon, JoinMasterCommonId> getJoinMasterCommonDao() {
    return joinMasterCommonDao;
  }

  /**
   * 【joinMasterCommonDao】を設定する。
   *
   * @param joinMasterCommonDao 【joinMasterCommonDao】
   */
  @Resource
  public void setJoinMasterCommonDao(GenericDao<JoinMasterCommon, JoinMasterCommonId> joinMasterCommonDao) {
    this.joinMasterCommonDao = joinMasterCommonDao;
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
   * 【goodsMasterCommonDao】を設定する。
   * 
   * @param goodsMasterCommonDao 【goodsMasterCommonDao】
   */
  @Resource
  public void setGoodsMasterCommonDao(GenericDao<GoodsMasterCommon, GoodsMasterCommonId> goodsMasterCommonDao) {
    this.goodsMasterCommonDao = goodsMasterCommonDao;
  }

  /**
   * 【setMasterCommonDao】を設定する。
   * 
   * @param setMasterCommonDao 【setMasterCommonDao】
   */
  @Resource
  public void setSetMasterCommonDao(GenericDao<SetMasterCommon, SetMasterCommonId> setMasterCommonDao) {
    this.setMasterCommonDao = setMasterCommonDao;
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

}
