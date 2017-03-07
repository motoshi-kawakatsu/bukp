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
package jp.co.broadleaf.breg.web.setlist.util;

import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.BlEntryFlgEnum;
import jp.co.broadleaf.breg.common.enums.CheckFlgEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterId;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterSearchDto;
import jp.co.broadleaf.breg.web.setlist.form.SetMasterGridForm;
import jp.co.broadleaf.breg.web.setlist.form.SetMasterSearchForm;
import jp.co.broadleaf.common.util.BroadleafFormatUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
   * searchDto searchForm 相互転換
   * </pre>
   * 
   * @param form SetMasterSearchForm
   * @param dto SetMasterSearchDto
   * 
   */
  public static void searchFormFormatToSearchDto(SetMasterSearchForm form, SetMasterSearchDto dto) {
    if(isEmptyStr(form.getPageNo())){
      dto.setPageNo(1);
    }else{
      dto.setPageNo(Integer.valueOf(form.getPageNo()));
    }
    // maxRows
    if (form.getMaxRows() == null || form.getMaxRows().isEmpty()) {
      dto.setMaxRows(null);
    } else {
      dto.setMaxRows(Long.valueOf(form.getMaxRows()));
    }
    // skipRows
    if (form.getSkipRows() == null || form.getSkipRows().isEmpty()) {
      dto.setSkipRows(null);
    } else {
      dto.setSkipRows(Long.valueOf(form.getSkipRows()));
    }
    // sort
    if (!isEmptyStr(form.getSort())) {
      dto.setSort(form.getSort());
    }
    if (!isEmptyStr(form.getInsDtTimeStart())) {
      // 作成日時start
      dto.setInsDtTimeStart(new Timestamp(BroadleafFormatUtils
          .stringToDate(form.getInsDtTimeStart().concat("　00:00:00"), BroadleafFormatUtils.DATE_F007).getTime()));
    }
    if (!isEmptyStr(form.getInsDtTimeEnd())) {
      // 作成日時end
      dto.setInsDtTimeEnd(new Timestamp(BroadleafFormatUtils
          .stringToDate(form.getInsDtTimeEnd().concat("　23:59:59"), BroadleafFormatUtils.DATE_F007).getTime()));
    }
    if (!isEmptyStr(form.getUpdDtTimeStart())) {
      // 更新日時start
      dto.setUpdDtTimeStart(new Timestamp(BroadleafFormatUtils
          .stringToDate(form.getUpdDtTimeStart().concat("　00:00:00"), BroadleafFormatUtils.DATE_F007).getTime()));
    }
    if (!isEmptyStr(form.getUpdDtTimeEnd())) {
      // 更新日時end
      dto.setUpdDtTimeEnd(new Timestamp(BroadleafFormatUtils
          .stringToDate(form.getUpdDtTimeEnd().concat("　23:59:59"), BroadleafFormatUtils.DATE_F007).getTime()));
    }
    if (!isEmptyStr(form.getStartTimeStart())) {
      // 適用日付start
      dto.setStartTimeStart(new Timestamp(BroadleafFormatUtils
          .stringToDate(form.getStartTimeStart().concat("　00:00:00"), BroadleafFormatUtils.DATE_F007).getTime()));
    }
    if (!isEmptyStr(form.getStartTimeEnd())) {
      // 適用日付end
      dto.setStartTimeEnd(new Timestamp(BroadleafFormatUtils
          .stringToDate(form.getStartTimeEnd().concat("　23:59:59"), BroadleafFormatUtils.DATE_F007).getTime()));
    }
    // BLコード
    if (!isEmptyStr(form.getTbsPartsCode())) {
      dto.setTbsPartsCode(Integer.valueOf(getCode(form.getTbsPartsCode())));
    }
    // セット親品番not null primary key
    if (!isEmptyStr(form.getSetMainPartsNo())) {
      dto.setSetMainPartsNo(form.getSetMainPartsNo());
    }
    // 品名
    if (!isEmptyStr(form.getSetKanaName())) {
      dto.setSetKanaName(form.getSetKanaName());
    }
    // 申請状態
    if (!isEmptyStr(form.getApplyCondition())) {
      dto.setApplyCondition(Short.valueOf(form.getApplyCondition()));
    }
    // セット子品番
    if (!isEmptyStr(form.getSetSubPartsNo())) {
      dto.setSetSubPartsNo(form.getSetSubPartsNo());
    }
    // 優良設定詳細コード１not null primary key
    if (!isEmptyStr(form.getPrmSetDtlNo1())) {
      dto.setPrmSetDtlNo1(Integer.valueOf(getCode(form.getPrmSetDtlNo1())));
    }
    // 削除依頼区分
    if (!isEmptyStr(form.getDeleteFlg())) {
      dto.setDeleteFlg(Short.valueOf(form.getDeleteFlg()));
    }
    // 商品中分類コード
    if (!isEmptyStr(form.getGoodsMGroup())) {
      dto.setGoodsMGroup(Integer.valueOf(getCode(form.getGoodsMGroup())));
    }
    // セット規格・特記事項
    if (!isEmptyStr(form.getSetSpecialNote())) {
      dto.setSetSpecialNote(form.getSetSpecialNote());
    }
    // 処理区分not null
    if (!isEmptyStr(form.getManageKbn())) {
      dto.setManageKbn(Short.valueOf(form.getManageKbn()));
    }
    // エラー区分
    if (!isEmptyStr(form.getErrorFlg())) {
      dto.setErrorFlg(Short.valueOf(form.getErrorFlg()));
    }

  }

  /**
   * <pre>
   * searchDto searchForm 相互転換
   * </pre>
   * @param classifyCodeGuideMap classifyCodeGuideMap
   * @param form SetMasterSearchForm
   * @param dto SetMasterSearchDto
   * @param blCodeNameMap Map
   * @param selectCodeNameMap Map
   * @param goodsCodeNameMap Map
   * 
   */
  public static void dtoFormatToGridForm(SetMasterDto dto, SetMasterGridForm form,Map<String,String> blCodeNameMap,Map<String,String> selectCodeNameMap, Map<String, String> goodsCodeNameMap,Map<String,String> classifyCodeGuideMap) {
    //check
    form.setCheck(dto.getCheck());
    //compareFlag
    form.setCompareFlag(dto.getCompareFlag());
    // UUID
    form.setUuid(dto.getUuid());
    // 作成日時
    form.setInsDtTime(dateToString(dto.getInsDtTime()));
    // 更新日時
    form.setUpdDtTime(dateToString(dto.getUpdDtTime()));
    // 作成アカウントID
    form.setInsAccountId(dto.getInsAccountId());
    // 更新アカウントID
    form.setUpdAccountId(dto.getUpdAccountId());
    // 論理削除区分
    form.setLogicalDelDiv(dto.getLogicalDelDiv().toString());
    // 優良設定詳細コード１not null primary key
    String selectCode = selectCodeNameMap.get(dto.getPrmSetDtlNo1().toString());
    if(null==selectCode){
      form.setPrmSetDtlNo1("9999：指定無し");
    }else{
      form.setPrmSetDtlNo1(selectCode);      
    }
    // 部品メーカーコードnot null primary key
    form.setPartsMakerCd(dto.getPartsMakerCd().toString());
    // 商品中分類コード
    String classifyCode = classifyCodeGuideMap.get(dto.getGoodsMGroup()==null?"":dto.getGoodsMGroup().toString());
    if(null==classifyCode){
      
      form.setGoodsMGroup("");
    }else{
      form.setGoodsMGroup(classifyCode);
    }
    // BLコード
    String blCode = blCodeNameMap.get(dto.getTbsPartsCode() == null ? "" : dto.getTbsPartsCode().toString());
    if(null==blCode){
      
      form.setTbsPartsCode("");
    }else{
      form.setTbsPartsCode(blCode);
    }
    // セット親品番not null primary key
    String goodsMainCode = goodsCodeNameMap.get(ifEmptyStrFormat(dto.getSetMainPartsNo()));
    if(null==goodsMainCode){
      form.setSetMainPartsNo("");
    }else{
      form.setSetMainPartsNo(goodsMainCode);
    }
    // セット表示順位not null primary key
    form.setSetDispOrder(dto.getSetDispOrder().toString());
    // セット子品番
    String goodsSubCode = goodsCodeNameMap.get(ifEmptyStrFormat(dto.getSetSubPartsNo()));
    if(null==goodsSubCode){
      form.setSetSubPartsNo("");
    }else{
      form.setSetSubPartsNo(goodsSubCode);
    }
    // 品名
    form.setSetKanaName(dto.getSetKanaName() == null ? "" : dto.getSetKanaName());
    // セット名称
    form.setSetName(dto.getSetName() == null ? "" : dto.getSetName());
    // セットQTY
    form.setSetQty(dto.getSetQty() == null ? "" : dto.getSetQty().toString());
    // セット規格・特記事項
    form.setSetSpecialNote(dto.getSetSpecialNote() == null ? "" : dto.getSetSpecialNote());
    // 優良部品規格・特記事項(C向け)
    form.setPrimePartsSpecialNoteC(dto.getPrimePartsSpecialNoteC() == null ? "" : dto.getPrimePartsSpecialNoteC());
    // 適用日付not null
    form.setStartTime(dateToString(dto.getStartTime()));
    // チェック区分not null
    if(dto.getCheckFlg() != null && CheckFlgEnum.valueof(dto.getCheckFlg()) != null ){
      form.setCheckFlg(CheckFlgEnum.valueof(dto.getCheckFlg()).getAbbreviation());
    }
    // データステータスnot null
    if(dto.getErrorFlg() != null && ErrorFlgEnum.valueof(dto.getErrorFlg()).getAbbreviation() != null){
      form.setErrorFlg(ErrorFlgEnum.valueof(dto.getErrorFlg()).getAbbreviation());
    }
    // BL登録区分not null
    if(dto.getBlEntryFlg() != null && BlEntryFlgEnum.valueof(dto.getBlEntryFlg()) != null){
      form.setBlEntryFlg(BlEntryFlgEnum.valueof(dto.getBlEntryFlg()).getAbbreviation());
    }
    // インポート区分not null
    if(dto.getImportKbn() != null && ImportKbnEnum.valueof(dto.getImportKbn()) !=null ){
      form.setImportKbn(ImportKbnEnum.valueof(dto.getImportKbn()).getName());
    }
    // 処理区分not null
    if(dto.getManageKbn() != null && ManageKbnEnum.valueof(dto.getManageKbn()) != null ){
      form.setManageKbn(ManageKbnEnum.valueof(dto.getManageKbn()).getAbbreviation());
    }
    // エラー区分
    form.setErrorFlg(ErrorFlgEnum.valueof(dto.getErrorFlg() == null ? 0 : dto.getErrorFlg()).getAbbreviation());
    // エラー内容
    form.setErrorDetail(dto.getErrorDetail() == null ? "" : dto.getErrorDetail());
    // 削除依頼区分
    form.setDeleteFlg(DeleteFlgEnum.valueof(dto.getDeleteFlg() == null ? 0 : dto.getDeleteFlg()).getAbbreviation());
    // 削除理由
    form.setDeleteReason(dto.getDeleteReason() == null ? "" : dto.getDeleteReason());
    // 申請状態
    String abbreviation = ApplyConditionEnum.valueof(dto.getApplyCondition()).getAbbreviation();
    form.setApplyCondition(abbreviation);
  }
  
  /**
   * <pre>
   * searchDto searchForm 相互転換
   * </pre>
   * 
   * @param form SetMasterSearchForm
   * @param dto SetMasterSearchDto
   * 
   */
  public static void gridFromFormatTodto(SetMasterDto dto, SetMasterGridForm form) {
    
    //check
    if(null==form.getCheck()){
      dto.setCheck(false);
    }else{
      dto.setCheck(form.getCheck());
    }
    // UUID
    if(!isEmptyStr(form.getUuid())){
      dto.setUuid(form.getUuid());
    }
    // 作成日時
    if(!isEmptyStr(form.getInsDtTime())){
      dto.setInsDtTime(stringToDate(form.getInsDtTime()));
    }
    // 更新日時
    if(!isEmptyStr(form.getUpdDtTime())){
      dto.setUpdDtTime(stringToDate(form.getUpdDtTime()));
    }
    // 作成アカウントID
    if(!isEmptyStr(form.getInsAccountId())){
      dto.setInsAccountId(form.getInsAccountId());
    }
    // 更新アカウントID
    if(!isEmptyStr(form.getUpdAccountId())){
      dto.setUpdAccountId(form.getUpdAccountId());
    }
    // 論理削除区分
    if(!isEmptyStr(form.getLogicalDelDiv())){
      dto.setLogicalDelDiv(Integer.valueOf(form.getLogicalDelDiv()));
    }
    //優良設定詳細コード１
    if(!isEmptyStr(form.getPrmSetDtlNo1())){
      dto.setPrmSetDtlNo1(Integer.valueOf(getCode(form.getPrmSetDtlNo1())));
    }
    
    //部品メーカーコード
    if(!isEmptyStr(form.getPartsMakerCd())){
      dto.setPartsMakerCd(Integer.valueOf(form.getPartsMakerCd()));// not null primary key
    }
    //商品中分類コード
    if(!isEmptyStr(form.getGoodsMGroup())){
      dto.setGoodsMGroup(Integer.valueOf(getCode(form.getGoodsMGroup())));
    }
    //BLコード
    if(!isEmptyStr(form.getTbsPartsCode())){
      dto.setTbsPartsCode(Integer.valueOf(getCode(form.getTbsPartsCode())));
    }
    //セット親品番
    if(!isEmptyStr(form.getSetMainPartsNo())){
      dto.setSetMainPartsNo(getCode(form.getSetMainPartsNo()));// not null primary key
    }
    //セット表示順位
    if(!isEmptyStr(form.getSetDispOrder())){
      dto.setSetDispOrder(Integer.valueOf(form.getSetDispOrder()));// not null primary key
    }
    //セット子品番
    if(!isEmptyStr(form.getSetSubPartsNo())){
      dto.setSetSubPartsNo(getCode(form.getSetSubPartsNo()));
    }
    //品名
    if(!isEmptyStr(form.getSetKanaName())){
      dto.setSetKanaName(form.getSetKanaName());
    }
    //セット名称
    if(!isEmptyStr(form.getSetName())){
      dto.setSetName(form.getSetName());
    }
    //セットQTY
    if(!isEmptyStr(form.getSetQty())){
      dto.setSetQty(Double.valueOf(form.getSetQty()));
    }
    //セット規格・特記事項
    if(!isEmptyStr(form.getSetSpecialNote())){
      dto.setSetSpecialNote(form.getSetSpecialNote());
    }
    //優良部品規格・特記事項(C向け)
    if(!isEmptyStr(form.getPrimePartsSpecialNoteC())){
      dto.setPrimePartsSpecialNoteC(form.getPrimePartsSpecialNoteC());
    }
    //適用日時
    if(!isEmptyStr(form.getStartTime())){
      dto.setStartTime(stringToDate(form.getStartTime()));// not null
    }
    //チェック区分
    if(!isEmptyStr(form.getCheckFlg())){
      dto.setCheckFlg((short) CheckFlgEnum.getCode(form.getCheckFlg()));// not null
    }
    //データステータス
    if(!isEmptyStr(form.getErrorFlg())){
      dto.setErrorFlg((short) ErrorFlgEnum.getCode(form.getErrorFlg()));// not null
    }
    //BL登録区分
    if(!isEmptyStr(form.getBlEntryFlg())){
      dto.setBlEntryFlg((short) BlEntryFlgEnum.getCode(form.getBlEntryFlg()));// not null
    }
    //インポート区分
    if(!isEmptyStr(form.getImportKbn())){
      dto.setImportKbn((short) ImportKbnEnum.getCode(form.getImportKbn()));// not null
    }
    //処理区分
    if(!isEmptyStr(form.getManageKbn())){
      dto.setManageKbn((short) ManageKbnEnum.getCode(form.getManageKbn()));// not null
    }
    //エラー内容
    if(!isEmptyStr(form.getErrorDetail())){
      dto.setErrorDetail(form.getErrorDetail());
    }
    //削除依頼区分
    if(!isEmptyStr(form.getDeleteFlg())){
      dto.setDeleteFlg((short) DeleteFlgEnum.getCode(form.getDeleteFlg()));
    }
    //削除理由
    if(!isEmptyStr(form.getDeleteReason())){
      dto.setDeleteReason(form.getDeleteReason());
    }
    //申請状態
    if(!isEmptyStr(form.getApplyCondition())){
      dto.setApplyCondition((short) ApplyConditionEnum.getCode(form.getApplyCondition()));
    }
    
  }
  
  /**
   * gridForm formatto key
   * 
   * @param id SetMasterMakerId
   * @param form SetMasterGridForm
   * @return SetMasterMakerId
   */
  
  public static SetMasterMakerId gridFormFormatToKey(SetMasterGridForm form){
    SetMasterMakerId id=null;
    if(form.getCheck()){
      id=new SetMasterMakerId();
      if(!isEmptyStr(form.getPrmSetDtlNo1())){
        id.setPrmSetDtlNo1(Integer.valueOf(getCode(form.getPrmSetDtlNo1())));
      }
      if(!isEmptyStr(form.getPartsMakerCd())){
        id.setPartsMakerCd(Integer.valueOf(form.getPartsMakerCd()));
      }
      if(!isEmptyStr(form.getSetMainPartsNo())){
        id.setSetMainPartsNo(getCode(form.getSetMainPartsNo()));
      }
      if(!isEmptyStr(form.getSetDispOrder())){
        id.setSetDispOrder(Integer.valueOf(form.getSetDispOrder()));
      }
    }
    return id;
  }
  
  /**
   * date to string
   * 
   * @param id SetMasterId
   * @return String
   */
  public SetMasterMakerId createSetMasterMakerId(SetMasterId id){
    return new SetMasterMakerId(id.getPrmSetDtlNo1(), id.getPartsMakerCd(), id.getSetMainPartsNo(), id.getSetDispOrder());
  }
  /**
   * date to string
   * 
   * @param id SetMasterMakerId
   * @return String
   */
  public SetMasterId createSetMasterId(SetMasterMakerId id){
    return new SetMasterId(id.getPrmSetDtlNo1(), id.getPartsMakerCd(), id.getSetMainPartsNo(), id.getSetDispOrder());
  }
  

  /**
   * date to string
   * 
   * @param timeStamp value
   * @return String
   */
  public static String dateToString(Timestamp timeStamp) {
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    String date = null;
    date = df.format(timeStamp);
    return date;
  }

  /**
   * string Dateになる
   * 
   * @param value value
   * @return Timestamp
   */
  public static Timestamp stringToDate(String value) {
    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    Date date = null;
    Timestamp timestamp = null;
    try {
      date = df.parse(value);
      timestamp = new Timestamp(date.getTime());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return timestamp;
  }
  
  /**
   * empty test 
   * 
   * @param str value
   * @return true  empty false noEmpty
   */
  public static  boolean isEmptyStr(String str){
    if(null==str||str.isEmpty()){
      return true;
    }
    return false;
  }
  
  /**
   * empty test 
   * 
   * @param str tobeFormatted
   * @return string  "" or str
   */
  public static String ifEmptyStrFormat(String str){
    return isEmptyStr(str)?"":str;
  }
  
  /**
   * getcode from  "code:name"
   * @param codeAndName value
   * @return code string
   */
  public static String getCode(String codeAndName){
    return codeAndName.split("：")[0];
  }

}
