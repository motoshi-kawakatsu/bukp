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
package jp.co.broadleaf.breg.setlist.utils;

import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMaker;
import jp.co.broadleaf.breg.setlist.domain.model.SetMaster;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterI;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterId;

/**
 * <pre>
 * toolsClass for dataConvert
 * </pre>
 */
public final class DataConvert {
  /**
   * <pre>
   * constructor
   * </pre>
   */
  private DataConvert() {
  }

  /**
   * <pre>
   * entity and entityDto entityModel相互転換
   * </pre>
   * 
   * @param setMasterA SetMasterMaker
   * @param setMasterB SetMasterI
   */
  public static void setMasterMakerconvertToDtoOrModel(SetMasterMaker setMasterA, SetMasterI setMasterB) {
    // UUID
    setMasterB.setUuid(setMasterA.getUuid());
    // 作成日時
    setMasterB.setInsDtTime(setMasterA.getInsDtTime());
    // 更新日時
    setMasterB.setUpdDtTime(setMasterA.getUpdDtTime());
    // 作成アカウントID
    setMasterB.setInsAccountId(setMasterA.getInsAccountId());
    // 更新アカウントID
    setMasterB.setUpdAccountId(setMasterA.getUpdAccountId());
    // 論理削除区分
    setMasterB.setLogicalDelDiv(setMasterA.getLogicalDelDiv());

    // 優良設定詳細コード１not null primary key
    setMasterB.setPrmSetDtlNo1(setMasterA.getPrmSetDtlNo1());
    // 部品メーカーコードnot null primary key
    setMasterB.setPartsMakerCd(setMasterA.getPartsMakerCd());
    // 商品中分類コード
    setMasterB.setGoodsMGroup(setMasterA.getGoodsMGroup());
    // BLコード
    setMasterB.setTbsPartsCode(setMasterA.getTbsPartsCode());
    // セット親品番not null primary key
    setMasterB.setSetMainPartsNo(setMasterA.getSetMainPartsNo());
    // セット表示順位not null primary key
    setMasterB.setSetDispOrder(setMasterA.getSetDispOrder());
    // セット子品番
    setMasterB.setSetSubPartsNo(setMasterA.getSetSubPartsNo());
    // 品名
    setMasterB.setSetKanaName(setMasterA.getSetKanaName());
    // セット名称
    setMasterB.setSetName(setMasterA.getSetName());
    // セットQTY
    setMasterB.setSetQty(setMasterA.getSetQty());
    // セット規格・特記事項
    setMasterB.setSetSpecialNote(setMasterA.getSetSpecialNote());
    // 優良部品規格・特記事項(C向け)
    setMasterB.setPrimePartsSpecialNoteC(setMasterA.getPrimePartsSpecialNoteC());
    // 適用日付not null
    setMasterB.setStartTime(setMasterA.getStartTime());
    // チェック区分not null
    setMasterB.setCheckFlg(setMasterA.getCheckFlg());
    // データステータスnot null
    setMasterB.setErrorFlg(setMasterA.getErrorFlg());
    // BL登録区分not null
    setMasterB.setBlEntryFlg(setMasterA.getBlEntryFlg());
    // インポート区分not null
    setMasterB.setImportKbn(setMasterA.getImportKbn());
    // 処理区分not null
    setMasterB.setManageKbn(setMasterA.getManageKbn());
    // エラー内容
    setMasterB.setErrorDetail(setMasterA.getErrorDetail());
    // 削除依頼区分
    setMasterB.setDeleteFlg(setMasterA.getDeleteFlg());
    // 削除理由
    setMasterB.setDeleteReason(setMasterA.getDeleteReason());
    // 申請状態
    setMasterB.setApplyCondition(setMasterA.getApplyCondition());
  }
  /**
   * <pre>
   * entity and entityDto entityModel相互転換
   * </pre>
   * 
   * @param setMasterA SetMasterWorkMaker
   * @param setMasterB SetMasterI
   */
  public static void setMasterWorkMakerconvertToDtoOrModel(SetMasterWorkMaker setMasterA, SetMasterI setMasterB) {
    
    // UUID
    setMasterB.setUuid(setMasterA.getUuid());
    // 作成日時
    setMasterB.setInsDtTime(setMasterA.getInsDtTime());
    // 更新日時
    setMasterB.setUpdDtTime(setMasterA.getUpdDtTime());
    // 作成アカウントID
    setMasterB.setInsAccountId(setMasterA.getInsAccountId());
    // 更新アカウントID
    setMasterB.setUpdAccountId(setMasterA.getUpdAccountId());
    // 論理削除区分
    setMasterB.setLogicalDelDiv(setMasterA.getLogicalDelDiv());
    
    // 優良設定詳細コード１not null primary key
    setMasterB.setPrmSetDtlNo1(setMasterA.getPrmSetDtlNo1());
    // 部品メーカーコードnot null primary key
    setMasterB.setPartsMakerCd(setMasterA.getPartsMakerCd());
    // 商品中分類コード
    setMasterB.setGoodsMGroup(setMasterA.getGoodsMGroup());
    // BLコード
    setMasterB.setTbsPartsCode(setMasterA.getTbsPartsCode());
    // セット親品番not null primary key
    setMasterB.setSetMainPartsNo(setMasterA.getSetMainPartsNo());
    // セット表示順位not null primary key
    setMasterB.setSetDispOrder(setMasterA.getSetDispOrder());
    // セット子品番
    setMasterB.setSetSubPartsNo(setMasterA.getSetSubPartsNo());
    // 品名
    setMasterB.setSetKanaName(setMasterA.getSetKanaName());
    // セット名称
    setMasterB.setSetName(setMasterA.getSetName());
    // セットQTY
    setMasterB.setSetQty(setMasterA.getSetQty());
    // セット規格・特記事項
    setMasterB.setSetSpecialNote(setMasterA.getSetSpecialNote());
    // 優良部品規格・特記事項(C向け)
    setMasterB.setPrimePartsSpecialNoteC(setMasterA.getPrimePartsSpecialNoteC());
    // 適用日付not null
    setMasterB.setStartTime(setMasterA.getStartTime());
    // チェック区分not null
    setMasterB.setCheckFlg(setMasterA.getCheckFlg());
    // データステータスnot null
    setMasterB.setErrorFlg(setMasterA.getErrorFlg());
    // BL登録区分not null
    setMasterB.setBlEntryFlg(setMasterA.getBlEntryFlg());
    // インポート区分not null
    setMasterB.setImportKbn(setMasterA.getImportKbn());
    // 処理区分not null
    setMasterB.setManageKbn(setMasterA.getManageKbn());
    // エラー内容
    setMasterB.setErrorDetail(setMasterA.getErrorDetail());
    // 削除依頼区分
    setMasterB.setDeleteFlg(setMasterA.getDeleteFlg());
    // 削除理由
    setMasterB.setDeleteReason(setMasterA.getDeleteReason());
    // 申請状態
    setMasterB.setApplyCondition(setMasterA.getApplyCondition());
  }
  /**
   * <pre>
   * entity and entityDto entityModel相互転換
   * </pre>
   * 
   * @param setMasterA setMasterA
   * @param setMasterB SetMasterMaker
   */
  public static void dtoOrModelconvertToSetMasterMaker(SetMasterI setMasterA, SetMasterMaker setMasterB) {
    
    // UUID
    setMasterB.setUuid(setMasterA.getUuid());
    // 作成日時
    setMasterB.setInsDtTime(setMasterA.getInsDtTime());
    // 更新日時
    setMasterB.setUpdDtTime(setMasterA.getUpdDtTime());
    // 作成アカウントID
    setMasterB.setInsAccountId(setMasterA.getInsAccountId());
    // 更新アカウントID
    setMasterB.setUpdAccountId(setMasterA.getUpdAccountId());
    // 論理削除区分
    setMasterB.setLogicalDelDiv(setMasterA.getLogicalDelDiv());
    
    // 優良設定詳細コード１not null primary key
    setMasterB.setPrmSetDtlNo1(setMasterA.getPrmSetDtlNo1());
    // 部品メーカーコードnot null primary key
    setMasterB.setPartsMakerCd(setMasterA.getPartsMakerCd());
    // 商品中分類コード
    setMasterB.setGoodsMGroup(setMasterA.getGoodsMGroup());
    // BLコード
    setMasterB.setTbsPartsCode(setMasterA.getTbsPartsCode());
    // セット親品番not null primary key
    setMasterB.setSetMainPartsNo(setMasterA.getSetMainPartsNo());
    // セット表示順位not null primary key
    setMasterB.setSetDispOrder(setMasterA.getSetDispOrder());
    // セット子品番
    setMasterB.setSetSubPartsNo(setMasterA.getSetSubPartsNo());
    // 品名
    setMasterB.setSetKanaName(setMasterA.getSetKanaName());
    // セット名称
    setMasterB.setSetName(setMasterA.getSetName());
    // セットQTY
    setMasterB.setSetQty(setMasterA.getSetQty());
    // セット規格・特記事項
    setMasterB.setSetSpecialNote(setMasterA.getSetSpecialNote());
    // 優良部品規格・特記事項(C向け)
    setMasterB.setPrimePartsSpecialNoteC(setMasterA.getPrimePartsSpecialNoteC());
    // 適用日付not null
    setMasterB.setStartTime(setMasterA.getStartTime());
    // チェック区分not null
    setMasterB.setCheckFlg(setMasterA.getCheckFlg());
    // データステータスnot null
    setMasterB.setErrorFlg(setMasterA.getErrorFlg());
    // BL登録区分not null
    setMasterB.setBlEntryFlg(setMasterA.getBlEntryFlg());
    // インポート区分not null
    setMasterB.setImportKbn(setMasterA.getImportKbn());
    // 処理区分not null
    setMasterB.setManageKbn(setMasterA.getManageKbn());
    // エラー内容
    setMasterB.setErrorDetail(setMasterA.getErrorDetail());
    // 削除依頼区分
    setMasterB.setDeleteFlg(setMasterA.getDeleteFlg());
    // 削除理由
    setMasterB.setDeleteReason(setMasterA.getDeleteReason());
    // 申請状態
    setMasterB.setApplyCondition(setMasterA.getApplyCondition());
  }
  /**
   * <pre>
   * entity and entityDto entityModel相互転換
   * </pre>
   * 
   * @param setMasterA setMasterA
   * @param setMasterB SetMasterWorkMaker
   */
  public static void dtoOrModelconvertToSetMasterWorkMaker(SetMasterI setMasterA, SetMasterWorkMaker setMasterB) {
    
    // UUID
    setMasterB.setUuid(setMasterA.getUuid());
    // 作成日時
    setMasterB.setInsDtTime(setMasterA.getInsDtTime());
    // 更新日時
    setMasterB.setUpdDtTime(setMasterA.getUpdDtTime());
    // 作成アカウントID
    setMasterB.setInsAccountId(setMasterA.getInsAccountId());
    // 更新アカウントID
    setMasterB.setUpdAccountId(setMasterA.getUpdAccountId());
    // 論理削除区分
    setMasterB.setLogicalDelDiv(setMasterA.getLogicalDelDiv());
    
    // 優良設定詳細コード１not null primary key
    setMasterB.setPrmSetDtlNo1(setMasterA.getPrmSetDtlNo1());
    // 部品メーカーコードnot null primary key
    setMasterB.setPartsMakerCd(setMasterA.getPartsMakerCd());
    // 商品中分類コード
    setMasterB.setGoodsMGroup(setMasterA.getGoodsMGroup());
    // BLコード
    setMasterB.setTbsPartsCode(setMasterA.getTbsPartsCode());
    // セット親品番not null primary key
    setMasterB.setSetMainPartsNo(setMasterA.getSetMainPartsNo());
    // セット表示順位not null primary key
    setMasterB.setSetDispOrder(setMasterA.getSetDispOrder());
    // セット子品番
    setMasterB.setSetSubPartsNo(setMasterA.getSetSubPartsNo());
    // 品名
    setMasterB.setSetKanaName(setMasterA.getSetKanaName());
    // セット名称
    setMasterB.setSetName(setMasterA.getSetName());
    // セットQTY
    setMasterB.setSetQty(setMasterA.getSetQty());
    // セット規格・特記事項
    setMasterB.setSetSpecialNote(setMasterA.getSetSpecialNote());
    // 優良部品規格・特記事項(C向け)
    setMasterB.setPrimePartsSpecialNoteC(setMasterA.getPrimePartsSpecialNoteC());
    // 適用日付not null
    setMasterB.setStartTime(setMasterA.getStartTime());
    // チェック区分not null
    setMasterB.setCheckFlg(setMasterA.getCheckFlg());
    // データステータスnot null
    setMasterB.setErrorFlg(setMasterA.getErrorFlg());
    // BL登録区分not null
    setMasterB.setBlEntryFlg(setMasterA.getBlEntryFlg());
    // インポート区分not null
    setMasterB.setImportKbn(setMasterA.getImportKbn());
    // 処理区分not null
    setMasterB.setManageKbn(setMasterA.getManageKbn());
    // エラー内容
    setMasterB.setErrorDetail(setMasterA.getErrorDetail());
    // 削除依頼区分
    setMasterB.setDeleteFlg(setMasterA.getDeleteFlg());
    // 削除理由
    setMasterB.setDeleteReason(setMasterA.getDeleteReason());
    // 申請状態
    setMasterB.setApplyCondition(setMasterA.getApplyCondition());
  }

  /**
   * <pre>
   * searchDto searchModel 相互転換
   * </pre>
   * @param setMasterA setMaster
   * @param setMasterB setMaster
   */
  public static void searchConvert(SetMaster setMasterA, SetMaster setMasterB) {
 // UUID
    setMasterA.setUuid(setMasterB.getUuid());
    // 作成日時
    setMasterA.setInsDtTime(setMasterB.getInsDtTime());
    // 更新日時
    setMasterA.setUpdDtTime(setMasterB.getUpdDtTime());
    // 作成アカウントID
    setMasterA.setInsAccountId(setMasterB.getInsAccountId());
    // 更新アカウントID
    setMasterA.setUpdAccountId(setMasterB.getUpdAccountId());
    // 論理削除区分
    setMasterA.setLogicalDelDiv(setMasterB.getLogicalDelDiv());
    
    // 優良設定詳細コード１not null primary key
    setMasterA.setPrmSetDtlNo1(setMasterB.getPrmSetDtlNo1());
    // 部品メーカーコードnot null primary key
    setMasterA.setPartsMakerCd(setMasterB.getPartsMakerCd());
    // 商品中分類コード
    setMasterA.setGoodsMGroup(setMasterB.getGoodsMGroup());
    // BLコード
    setMasterA.setTbsPartsCode(setMasterB.getTbsPartsCode());
    // セット親品番not null primary key
    setMasterA.setSetMainPartsNo(setMasterB.getSetMainPartsNo());
    // セット表示順位not null primary key
    setMasterA.setSetDispOrder(setMasterB.getSetDispOrder());
    // セット子品番
    setMasterA.setSetSubPartsNo(setMasterB.getSetSubPartsNo());
    // 品名
    setMasterA.setSetKanaName(setMasterB.getSetKanaName());
    // セット名称
    setMasterA.setSetName(setMasterB.getSetName());
    // セットQTY
    setMasterA.setSetQty(setMasterB.getSetQty());
    // セット規格・特記事項
    setMasterA.setSetSpecialNote(setMasterB.getSetSpecialNote());
    // 優良部品規格・特記事項(C向け)
    setMasterA.setPrimePartsSpecialNoteC(setMasterB.getPrimePartsSpecialNoteC());
    // 適用日付not null
    setMasterA.setStartTime(setMasterB.getStartTime());
    // チェック区分not null
    setMasterA.setCheckFlg(setMasterB.getCheckFlg());
    // データステータスnot null
    setMasterA.setErrorFlg(setMasterB.getErrorFlg());
    // BL登録区分not null
    setMasterA.setBlEntryFlg(setMasterB.getBlEntryFlg());
    // インポート区分not null
    setMasterA.setImportKbn(setMasterB.getImportKbn());
    // 処理区分not null
    setMasterA.setManageKbn(setMasterB.getManageKbn());
    // エラー内容
    setMasterA.setErrorDetail(setMasterB.getErrorDetail());
    // 削除依頼区分
    setMasterA.setDeleteFlg(setMasterB.getDeleteFlg());
    // 削除理由
    setMasterA.setDeleteReason(setMasterB.getDeleteReason());
    // 申請状態
    setMasterA.setApplyCondition(setMasterB.getApplyCondition());
    
    setMasterA.setSort(setMasterB.getSort());
    setMasterA.setStartTimeStart(setMasterB.getStartTimeStart());
    setMasterA.setStartTimeEnd(setMasterB.getStartTimeEnd());
    setMasterA.setInsDtTimeStart(setMasterB.getInsDtTimeStart());
    setMasterA.setInsDtTimeEnd(setMasterB.getInsDtTimeEnd());
    setMasterA.setUpdDtTimeStart(setMasterB.getUpdDtTimeStart());
    setMasterA.setUpdDtTimeEnd(setMasterB.getUpdDtTimeEnd());
  }
  
  /**
   * date to string
   * 
   * @param id SetMasterId
   * @return String
   */
  public static SetMasterMakerId createSetMasterMakerId(SetMasterId id){
    return new SetMasterMakerId(id.getPrmSetDtlNo1(), id.getPartsMakerCd(), id.getSetMainPartsNo(), id.getSetDispOrder());
  }
  /**
   * date to string
   * 
   * @param id SetMasterMakerId
   * @return String
   */
  public  static SetMasterId createSetMasterId(SetMasterMakerId id){
    return new SetMasterId(id.getPrmSetDtlNo1(), id.getPartsMakerCd(), id.getSetMainPartsNo(), id.getSetDispOrder());
  }

  

  

}
