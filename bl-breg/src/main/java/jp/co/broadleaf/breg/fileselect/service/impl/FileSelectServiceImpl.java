//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//(c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号       作成担当 : 羅　進博
// 作 成 日       2017/02/08   修正内容 : ファイル選択：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.fileselect.service.impl;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;
import jp.co.broadleaf.breg.common.entity.GoodsMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterWorkMakerId;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMakerId;
import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMakerId;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMakerId;
import jp.co.broadleaf.breg.common.enums.BlEntryFlgEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.LogicDeleteDivEnum;
import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.fileselect.service.FileSelectService;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

/**
 * <pre>
 * ファイル選択サービスクラス.
 * </pre>
 */
public class FileSelectServiceImpl implements FileSelectService {

  /**
   * <pre>
   * loginUserCdについて、商品データを削除する。
   * </pre>
   *
   * @param loginUserCd Integer
   */
  @Override
  public void deleteGoodsInfoAll(int loginUserCd) {
    /*partsMakerCd = loginUserCd*/
    NumberFilter makerCdFilter = NumberFilterBuilder.equals(GoodsMasterWorkMaker.PARTS_MAKER_CD, loginUserCd);
    Filter filter = new AndFilter(makerCdFilter);
    List<GoodsMasterWorkMaker> goodsList = goodsMasterWorkMakerDao.findByFilter(filter, null);
    if (!goodsList.isEmpty()) {
      for (GoodsMasterWorkMaker goodsWork : goodsList) {
        goodsMasterWorkMakerDao.delete(goodsWork);
      }
    }
  }

  /**
   * <pre>
   * 商品の情報はデータベースに挿入する。
   * </pre>
   *
   * @param goodsList List
   * @return Integer
   */
  @Override
  public int insertGoodsInfoList(List<GoodsMasterWorkMaker> goodsList) {
    if (goodsList.isEmpty()) {
      return 0;
    }
    /** データベースに登録。**/
    goodsMasterWorkMakerDao.batchInsert(goodsList);
    /** 登録した件数を返却する。**/ 
    return goodsList.size();
    
  }

  /**
   * <pre>
   * loginUserCdについて、セットデータを削除する。
   * </pre>
   *
   * @param loginUserCd Integer
   */
  @Override
  public void deleteSetInfoAll(int loginUserCd) {
    /** partsMakerCd = loginUserCd*/
    NumberFilter makerCdFilter = NumberFilterBuilder.equals(SetMasterWorkMaker.PARTS_MAKER_CD, loginUserCd);
    Filter filter = new AndFilter(makerCdFilter);
    List<SetMasterWorkMaker> setList = setMasterWorkMakerDao.findByFilter(filter, null);
    if (!setList.isEmpty()) {
      for (SetMasterWorkMaker setWork : setList) {
        setMasterWorkMakerDao.delete(setWork);
      }
    }
  }

  /**
   * <pre>
   * loginUserCdについて、結合データを削除する。
   * </pre>
   *
   * @param loginUserCd Integer
   */
  @Override
  public void deleteJoinInfoAll(int loginUserCd) {
    /** partsMakerCd = loginUserCd*/
    NumberFilter makerCdFilter = NumberFilterBuilder.equals(JoinMasterWorkMaker.PARTS_MAKER_CD, loginUserCd);
    Filter filter = new AndFilter(makerCdFilter);
    List<JoinMasterWorkMaker> joinList = joinMasterWorkMakerDao.findByFilter(filter, null);
    if (!joinList.isEmpty()) {
      for (JoinMasterWorkMaker joinWork : joinList) {
        joinMasterWorkMakerDao.delete(joinWork);
      }
    }
  }

  /**
   * <pre>
   * セットの情報はデータベースに挿入する。
   * </pre>
   *
   * @param setList List
   * @return Integer
   */
  @Override
  public int insertSetInfoList(List<SetMasterWorkMaker> setList) {
    if (setList.isEmpty()) {
      return 0;
    }
    /** データベースに登録。**/
    setMasterWorkMakerDao.batchInsert(setList);
    /** 登録した件数を返却する。**/ 
    return setList.size();
  }

  /**
   * <pre>
   * 結合の情報はデータベースに挿入する。
   * </pre>
   *
   * @param joinList List
   * @return Integer
   */
  @Override
  public int insertJoinInfoList(List<JoinMasterWorkMaker> joinList) {
    if (joinList.isEmpty()) {
      return 0;
    }
    /** データベースに登録。**/
    joinMasterWorkMakerDao.batchInsert(joinList);
    /** 登録した件数を返却する。**/ 
    return joinList.size();
  }

  /**
   * <pre>
   * インポート方法をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @return int[] 
   */
  @Override
  public int[] getImportType(int loginUserCd) {
    
    CompanyInfoMasterCommon companyInfoMasterCommon = companyInfoMasterCommonDao.findById(loginUserCd);
    if(companyInfoMasterCommon != null){
      if( companyInfoMasterCommon.getLogicalDelDiv() == 0){
        int[] importType = new int[3];
        /** 商品のインポート方法をゲット*/
        importType[0] = companyInfoMasterCommon.getGoodsImportType();
        /** セットのインポート方法をゲット*/
        importType[1] = companyInfoMasterCommon.getSetImportType();
        /** 結合のインポート方法をゲット*/
        importType[2] = companyInfoMasterCommon.getJoinImportType();
        return  importType;
      }
    }
    return null;
  }
  
  /**
   * <pre>
   * 商品ファイルの情報をチェックする(エラー)。
   * </pre>
   *
   * @param fileData List
   * @return list List
   */
  @Override
  public List<String> goodsCheck(List<String[]> fileData) {
    List<String> errorMsg = new ArrayList<String>();
    String message;
    /** 必須項目*/
    String neededItem = messageService.messageInfo(BregMessageCodes.E00001);
    /** 文字桁数*/
    String maxLengthMsg = messageService.messageInfo(BregMessageCodes.E00003);
    /** 複製情報*/
    String repeatedMsg = messageService.messageInfo(BregMessageCodes.E00301);
    int rowNum = 1;
    for(String[] goods : fileData){
      /** 必須項目チェック: 必須項目が空欄の際にエラーとする*/
      /** 優良設定詳細コード１*/
      if(goods[ZERO] == null || goods[ZERO].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", PRM_SET_DTL_NO_1);
        errorMsg.add(messageTemp);
      }else{
        message = this.makeLengthMsg(goods[ZERO], FOUR, maxLengthMsg, PRM_SET_DTL_NO_1, rowNum);
        if(message != null){
          errorMsg.add(message);
        }
      }
      /** 部品メーカーコード*/
      if(goods[ONE] == null || goods[ONE].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", PARTS_MAKER_CD);
        errorMsg.add(messageTemp);
      }else {
        message = this.makeLengthMsg(goods[ONE], FOUR, maxLengthMsg, PARTS_MAKER_CD, rowNum);
        if(message != null){
          errorMsg.add(message);
        }
      }
      /** 優良品番*/
      if(goods[FOUR] == null || goods[FOUR].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", PRIME_PARTS_NO);
        errorMsg.add(messageTemp);
      }else{
        message = this.makeLengthMsg(goods[FOUR], TWENTY_FOUR, maxLengthMsg, PRIME_PARTS_NO, rowNum);
        if(message != null){
          errorMsg.add(message);
        }
      }
      /** 商品ファイルの複製情報をチェックする*/
      int rowNumRepeated = 1;
      for (String[] goodsRepeated : fileData) {
        if ( rowNumRepeated > rowNum && 
            goodsRepeated[ZERO].equals(goods[ZERO]) && 
            goodsRepeated[ONE].equals(goods[ONE]) && 
            goodsRepeated[FOUR].equals(goods[FOUR])) {
          errorMsg.add(MESSAGE_HEADER + rowNum + CONNECTION + rowNumRepeated + BLONG_TO + repeatedMsg);
          break;
        }
        rowNumRepeated++;
      }
      /** 文字桁数*/
      errorMsg.addAll(this.goodsLengthCheck(goods, rowNum));
      rowNum++;
    }
    return errorMsg;
  }
  
  /**
   * <pre>
   * 商品ファイルの情報の文字桁数をチェックする(エラー)。
   * </pre>
   *
   * @param goods String[]
   * @param rowNum int
   * @return list List
   */
  private List<String> goodsLengthCheck(String[] goods, int rowNum) {
    List<String> errorMsg = new ArrayList<String>();
    String maxLengthMsg = messageService.messageInfo(BregMessageCodes.E00003);
    /** 商品中分類コード*/
    errorMsg.add(this.makeLengthMsg(goods[TWO], FOUR, maxLengthMsg, GOODS_M_GROUP, rowNum));
    /** BLコード **/
    errorMsg.add(this.makeLengthMsg(goods[THREE], EIGHT, maxLengthMsg, TBS_PARTS_CODE, rowNum));
    /** 優良部品カナ名称*/
    errorMsg.add(this.makeLengthMsg(goods[FIVE], SIXTY, maxLengthMsg, PRIME_PARTS_KANA_NAME, rowNum));
    /** 優良部品名称*/
    errorMsg.add(this.makeLengthMsg(goods[SIX], SIXTY, maxLengthMsg, PRIME_PARTS_NAME, rowNum));
    /** オープン価格区分*/
    errorMsg.add(this.makeLengthMsg(goods[EIGHT], TWO, maxLengthMsg, OPEN_PRICE, rowNum));
    /** JAN*/
    errorMsg.add(this.makeLengthMsg(goods[NINE], THIRTEEN, maxLengthMsg, JAN, rowNum));
    /** 層別コード*/
    errorMsg.add(this.makeLengthMsg(goods[TEN], TWO, maxLengthMsg, PARTS_LAYER_CD, rowNum));
    /** 装備名称*/
    errorMsg.add(this.makeLengthMsg(goods[ELEVEN], SIXTY, maxLengthMsg, EQUIP_NAME, rowNum));
    /** 優良部品規格・特記事項(B向け)*/
    errorMsg.add(this.makeLengthMsg(goods[TWELVE], EIGHTY, maxLengthMsg, PRIME_PARTS_SPECIAL_NOTE_B, rowNum));
    /** 優良部品規格・特記事項(C向け)*/
    errorMsg.add(this.makeLengthMsg(goods[THIRTEEN], EIGHTY, maxLengthMsg, PRIME_PARTS_SPECIAL_NOTE_C, rowNum));
    /** 商品詳細(B向け)*/
    errorMsg.add(this.makeLengthMsg(goods[FOURTEEN], LONG_STRING, maxLengthMsg, GOODS_DETAIL_B, rowNum));
    /** 商品詳細(c向け)*/
    errorMsg.add(this.makeLengthMsg(goods[FIVETEEN], LONG_STRING, maxLengthMsg, GOODS_DETAIL_C, rowNum));
   /** 商品サイズ(長さ）*/
    errorMsg.add(this.makeLengthMsg(goods[SIXTEEN], SIX, maxLengthMsg, GOODS_SIZE1, rowNum));
    /** 商品サイズ(幅）*/
    errorMsg.add(this.makeLengthMsg(goods[SEVENTEEN], SIX, maxLengthMsg, GOODS_SIZE2, rowNum));
    /** 商品サイズ(高さ）*/
    errorMsg.add(this.makeLengthMsg(goods[EIGHTEEN], SIX, maxLengthMsg, GOODS_SIZE3, rowNum));
    /** 梱包サイズ(長さ）*/
    errorMsg.add(this.makeLengthMsg(goods[NINETEEN], SIX, maxLengthMsg, PACKAGE_SIZE1, rowNum));
    /** 梱包サイズ(幅）*/
    errorMsg.add(this.makeLengthMsg(goods[TWENTY], SIX, maxLengthMsg, PACKAGE_SIZE2, rowNum));
    /** 梱包サイズ(高さ）*/
    errorMsg.add(this.makeLengthMsg(goods[TWENTY_ONE], SIX, maxLengthMsg, PACKAGE_SIZE3, rowNum));
    /** サイズ単位*/
    errorMsg.add(this.makeLengthMsg(goods[TWENTY_TWO], THREE, maxLengthMsg, SIZE_UNIT, rowNum));
    /** 商品重量*/
    errorMsg.add(this.makeLengthMsg(goods[TWENTY_THREE], SIX, maxLengthMsg, GOODS_WEIGHT, rowNum));
    /** 重量単位*/
    errorMsg.add(this.makeLengthMsg(goods[TWENTY_FOUR], THREE, maxLengthMsg, WEIGHT_UNIT, rowNum));
    /** URL1*/
    errorMsg.add(this.makeLengthMsg(goods[TWENTY_FIVE], LONG_STRING, maxLengthMsg, URL1, rowNum));
    /** URL2*/
    errorMsg.add(this.makeLengthMsg(goods[TWENTY_SIX], LONG_STRING, maxLengthMsg, URL2, rowNum));
    /** URL3*/
    errorMsg.add(this.makeLengthMsg(goods[TWENTY_SEVEN], LONG_STRING, maxLengthMsg, URL3, rowNum));
    /** 画像有無区分*/
    errorMsg.add(this.makeLengthMsg(goods[TWENTY_EIGHT], TWO, maxLengthMsg, IMAGE_NO, rowNum));
    /** 削除依頼区分*/
    errorMsg.add(this.makeLengthMsg(goods[THIRTY], TWO, maxLengthMsg, DELETE_FLG, rowNum));
    /** 削除理由*/
    errorMsg.add(this.makeLengthMsg(goods[THIRTY_ONE], LONG_STRING, maxLengthMsg, DELETE_REASON, rowNum));

    return errorMsg;
  }
  /**
   * <pre>
   * セットファイルの情報をチェックする(エラー)。
   * </pre>
   *
   * @param fileData List
   * @return list List
   */
  @Override
  public List<String> setCheck(List<String[]> fileData) {
    List<String> errorMsg = new ArrayList<String>();
    String message;
    /** 必須項目*/
    String neededItem = messageService.messageInfo(BregMessageCodes.E00001);
    /** 複製情報*/
    String repeatedMsg = messageService.messageInfo(BregMessageCodes.E00503);
    String maxLengthMsg = messageService.messageInfo(BregMessageCodes.E00003);
    int rowNum = 1;
    for(String[] set : fileData){
      /** 必須項目チェック: 必須項目が空欄の際にエラーとする*/
      /** 優良設定詳細コード１*/
      if(set[ZERO] == null || set[ZERO].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", PRM_SET_DTL_NO_1);
        errorMsg.add(messageTemp);
      }else{
        message = this.makeLengthMsg(set[ZERO], FOUR, maxLengthMsg, PRM_SET_DTL_NO_1, rowNum);
        if(message != null){
          errorMsg.add(message);
        }
      }
      /** 部品メーカーコード*/
      if(set[ONE] == null || set[ONE].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", PARTS_MAKER_CD);
        errorMsg.add(messageTemp);
      }else {
        message = this.makeLengthMsg(set[ONE], FOUR, maxLengthMsg, PARTS_MAKER_CD, rowNum);
        if(message != null){
          errorMsg.add(message);
        }
      }
      /** セット親品番*/
      if(set[FOUR] == null || set[FOUR].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", SET_MAIN_PARTS_NO);
        errorMsg.add(messageTemp);
      }else {
        message = this.makeLengthMsg(set[FOUR], TWENTY_FOUR, maxLengthMsg, SET_MAIN_PARTS_NO, rowNum);
        if(message != null){
          errorMsg.add(message);
        }
      }
      /** セット表示順位*/
      if(set[FIVE] == null || set[FIVE].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", SET_DISP_ORDER);
        errorMsg.add(messageTemp);
      }else {
        message = this.makeLengthMsg(set[FIVE], FOUR, maxLengthMsg, SET_DISP_ORDER, rowNum);
        if(message != null){
          errorMsg.add(message);
        }
      }
      /** 商品ファイルの複製情報をチェックする*/
      int rowNumRepeated = 1;
        for (String[] setRepeated : fileData) {
          if ( rowNumRepeated > rowNum && 
              setRepeated[ZERO].equals(set[ZERO]) && 
              setRepeated[ONE].equals(set[ONE]) && 
              setRepeated[FOUR].equals(set[FOUR]) && 
              setRepeated[FIVE].equals(set[FIVE])) {
            errorMsg.add(MESSAGE_HEADER + rowNum + CONNECTION + rowNumRepeated + BLONG_TO + repeatedMsg);
            rowNumRepeated++;
            break;
          }
          rowNumRepeated++;
        }
      /** 文字桁数*/
      errorMsg.addAll(this.setLengthCheck(set, rowNum));
      rowNum++;
    }
    return errorMsg;
  }
  
  /**
   * <pre>
   * セットファイルの情報の文字桁数をチェックする(エラー)。
   * </pre>
   *
   * @param set String[]
   * @param rowNum int
   * @return list List
   */
  private List<String> setLengthCheck(String[] set, int rowNum) {
    List<String> errorMsg = new ArrayList<String>();
    String maxLengthMsg = messageService.messageInfo(BregMessageCodes.E00003);
    /** 商品中分類コード*/
    errorMsg.add(this.makeLengthMsg(set[TWO], FOUR, maxLengthMsg, GOODS_M_GROUP, rowNum));
    /** BLコード **/
    errorMsg.add(this.makeLengthMsg(set[THREE], EIGHT, maxLengthMsg, TBS_PARTS_CODE, rowNum));
    /** セット子品番*/
    errorMsg.add(this.makeLengthMsg(set[SIX], TWENTY_FOUR, maxLengthMsg, SET_SUB_PARTS_NO, rowNum));
    /** 品名*/
    errorMsg.add(this.makeLengthMsg(set[SEVEN], SIXTY, maxLengthMsg, SET_KANA_NAME, rowNum));
    /** セット名称*/
    errorMsg.add(this.makeLengthMsg(set[EIGHT], SIXTY, maxLengthMsg, SET_NAME, rowNum));
    /**セット規格・特記事項*/
    errorMsg.add(this.makeLengthMsg(set[TEN], EIGHTY, maxLengthMsg, SET_SPECIAL_NOTE, rowNum));
    /** 優良部品規格・特記事項(C向け)*/
    errorMsg.add(this.makeLengthMsg(set[ELEVEN], EIGHTY, maxLengthMsg, PRIME_PARTS_SPECIAL_NOTE_C, rowNum));
    /** 削除依頼区分*/
    errorMsg.add(this.makeLengthMsg(set[THIRTEEN], TWO, maxLengthMsg, DELETE_FLG, rowNum));
    /** 削除理由*/
    errorMsg.add(this.makeLengthMsg(set[FOURTEEN], LONG_STRING, maxLengthMsg, DELETE_REASON, rowNum));
    return errorMsg;
  }
  
  /**
   * <pre>
   * 結合ファイルの情報をチェックする(エラー)。
   * </pre>
   *
   * @param fileData List
   * @return list List
   */
  public List<String> joinCheck(List<String[]> fileData){
    List<String> errorMsg = new ArrayList<String>();
    String message;
    /** 必須項目*/
    String neededItem = messageService.messageInfo(BregMessageCodes.E00001);
    /** 複製情報*/
    String repeatedMsg = messageService.messageInfo(BregMessageCodes.E00705);
    String maxLengthMsg = messageService.messageInfo(BregMessageCodes.E00003);
    int rowNum = 1;
    for(String[] join : fileData){
      /** 必須項目チェック: 必須項目が空欄の際にエラーとする*/
      /** 優良設定詳細コード１*/
      if(join[ZERO] == null || join[ZERO].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", PRM_SET_DTL_NO_1);
        errorMsg.add(messageTemp);
      }else{
        errorMsg.add(this.makeLengthMsg(join[ZERO], FOUR, maxLengthMsg, PRM_SET_DTL_NO_1, rowNum));
      }
      /** 部品メーカーコード*/
      if(join[ONE] == null || join[ONE].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", PARTS_MAKER_CD);
        errorMsg.add(messageTemp);
      }else {
        errorMsg.add(this.makeLengthMsg(join[ONE], FOUR, maxLengthMsg, PARTS_MAKER_CD, rowNum));
      }
      /** BLコード*/
      if(join[THREE] == null || join[THREE].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", TBS_PARTS_CODE);
        errorMsg.add(messageTemp);
      }else {
        errorMsg.add(this.makeLengthMsg(join[THREE], EIGHT, maxLengthMsg, TBS_PARTS_CODE, rowNum));
      }
      /** 結合元メーカーコード*/
      if(join[FOUR] == null || join[FOUR].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", JOIN_SOURCE_MAKER_CODE);
        errorMsg.add(messageTemp);
      }else {
        errorMsg.add(this.makeLengthMsg(join[FOUR], FOUR, maxLengthMsg, JOIN_SOURCE_MAKER_CODE, rowNum));
      }
      /** 優良設定詳細コード２*/
      if(join[FIVE] == null || join[FIVE].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", PRM_SET_DTL_NO_2);
        errorMsg.add(messageTemp);
      }else {
        errorMsg.add(this.makeLengthMsg(join[FIVE], FOUR, maxLengthMsg, PRM_SET_DTL_NO_2, rowNum));
      }
      /** 結合元品番(－付き品番)*/
      if(join[SIX] == null || join[SIX].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", JOIN_PARTS_NO);
        errorMsg.add(messageTemp);
      }else {
        errorMsg.add(this.makeLengthMsg(join[SIX], TWENTY_FOUR, maxLengthMsg, JOIN_PARTS_NO, rowNum));
      }
      /** 結合表示順位*/
      if(join[SEVEN] == null || join[SEVEN].equals(EMPTY)){
        message = MESSAGE_HEADER + rowNum + BLONG_TO + neededItem;
        String messageTemp = message.replace("$1", JOIN_DISP_ORDER);
        errorMsg.add(messageTemp);
      }else {
        errorMsg.add(this.makeLengthMsg(join[SEVEN], FOUR, maxLengthMsg, JOIN_DISP_ORDER, rowNum));
      }
      /** 結合ファイルの複製情報をチェックする*/
      int rowNumRepeated = 1;
      for (String[] joinRepeated : fileData) {
        if ( rowNumRepeated > rowNum && 
            joinRepeated[ZERO].equals(join[ZERO]) && 
            joinRepeated[ONE].equals(join[ONE]) && 
            joinRepeated[THREE].equals(join[THREE]) && 
            joinRepeated[FOUR].equals(join[FOUR]) && 
            joinRepeated[FIVE].equals(join[FIVE]) && 
            joinRepeated[SIX].equals(join[SIX]) && 
            joinRepeated[SEVEN].equals(join[SEVEN])) {
          errorMsg.add(MESSAGE_HEADER + rowNum + CONNECTION + rowNumRepeated + BLONG_TO + repeatedMsg);
          rowNumRepeated++;
          break;
        }
        rowNumRepeated++;
      }
      /** 文字桁数*/
      errorMsg.addAll(this.joinLengthCheck(join, rowNum));
      rowNum++;
    }
    return errorMsg;
  }
  
  /**
   * <pre>
   * 結合ファイルの情報の文字桁数をチェックする(エラー)。
   * </pre>
   *
   * @param join String[]
   * @param rowNum int
   * @return list List
   */
  private List<String> joinLengthCheck(String[] join, int rowNum) {
    List<String> errorMsg = new ArrayList<String>();
    String maxLengthMsg = messageService.messageInfo(BregMessageCodes.E00003);
    /** 結合先品番(－付き品番)*/
    errorMsg.add(this.makeLengthMsg(join[EIGHT], TWENTY_FOUR, maxLengthMsg, PRIME_PARTS_NO, rowNum));
    /** 結合規格・特記事項*/
    errorMsg.add(this.makeLengthMsg(join[TEN], EIGHTY, maxLengthMsg, JOIN_SPECIAL_NOTE, rowNum));
    /** 優良部品規格・特記事項(C向け)*/
    errorMsg.add(this.makeLengthMsg(join[ELEVEN], EIGHTY, maxLengthMsg, PRIME_PARTS_SPECIAL_NOTE_C, rowNum));
    /** 削除理由*/
    errorMsg.add(this.makeLengthMsg(join[THIRTEEN], LONG_STRING, maxLengthMsg, DELETE_REASON, rowNum));
    /** 削除依頼区分*/
    errorMsg.add(this.makeLengthMsg(join[FOURTEEN], TWO, maxLengthMsg, DELETE_FLG, rowNum));
    return errorMsg;
  }
  
  /**
   * <pre>
   * 商品の処理区分をゲットする。
   * </pre>
   *
   * @param goodsMasterWorkMaker GoodsMasterWorkMaker
   * @return short
   */
  @Override
  public short getGoodsManage(GoodsMasterWorkMaker goodsMasterWorkMaker) {
    GoodsMasterMakerId goodsMasterMakerId = new GoodsMasterMakerId(goodsMasterWorkMaker.getPrmSetDtlNo1(),
        goodsMasterWorkMaker.getPartsMakerCd(), goodsMasterWorkMaker.getPrimePartsNoWithH());
    GoodsMasterMaker goodsMasterMaker = goodsMasterMakerDao.findById(goodsMasterMakerId);
    if (goodsMasterMaker == null) {
      return (short) ManageKbnEnum.Add.getValue();
    } else {
      if(goodsMasterWorkMaker.getDeleteFlg() != null){
          /** インポートデータの削除依頼区分＝1 削除するの場合*/
          if (DeleteFlgEnum.Delete.getValue() == goodsMasterWorkMaker.getDeleteFlg()) {
            return (short) ManageKbnEnum.Delete.getValue();
          }else if (DeleteFlgEnum.NoDelete.getValue() == goodsMasterWorkMaker.getDeleteFlg()) {
          /** インポートデータの削除依頼区分＝0 削除しないの場合*/
            return (short) ManageKbnEnum.Update.getValue();
          }
        }
      }
    /** インポートデータの削除依頼区分＝null 削除しないの場合*/
    return (short) ManageKbnEnum.Update.getValue();
  }

  /**
   * <pre>
   * セットの処理区分をゲットする。
   * </pre>
   *
   * @param setMasterWorkMaker SetMasterWorkMaker
   * @return short
   */
  @Override
  public short getSetManage(SetMasterWorkMaker setMasterWorkMaker) {
    SetMasterMakerId setMasterMakerId = new SetMasterMakerId(setMasterWorkMaker.getPrmSetDtlNo1(),
        setMasterWorkMaker.getPartsMakerCd(), setMasterWorkMaker.getSetMainPartsNo(),
        setMasterWorkMaker.getSetDispOrder());
    SetMasterMaker setMasterMaker = setMasterMakerDao.findById(setMasterMakerId);
    if (setMasterMaker == null) {
      return (short) ManageKbnEnum.Add.getValue();
    } else {
      if(setMasterWorkMaker.getDeleteFlg() != null){
        /** インポートデータの削除依頼区分＝1 削除するの場合*/
        if (setMasterWorkMaker.getDeleteFlg() == DeleteFlgEnum.Delete.getValue()) {
          return (short) ManageKbnEnum.Delete.getValue();
        }else if (setMasterWorkMaker.getDeleteFlg() == DeleteFlgEnum.NoDelete.getValue()) {
          /** インポートデータの削除依頼区分＝0 削除しないの場合*/
          return (short) ManageKbnEnum.Update.getValue();
        }
      }
    }
    /** インポートデータの削除依頼区分＝null 削除しないの場合*/
    return (short) ManageKbnEnum.Update.getValue();
  }

  /**
   * <pre>
   * 結合の処理区分をゲットする。
   * </pre>
   *
   * @param joinMasterWorkMaker JoinMasterWorkMaker
   * @return short
   */
  @Override
  public short getJoinManage(JoinMasterWorkMaker joinMasterWorkMaker) {
    JoinMasterMakerId joinMasterMakerId = new JoinMasterMakerId(joinMasterWorkMaker.getPrmSetDtlNo1(),
        joinMasterWorkMaker.getPartsMakerCd(), joinMasterWorkMaker.getTbsPartsCode(),
        joinMasterWorkMaker.getJoinSourceMakerCode(), joinMasterWorkMaker.getPrmSetDtlNo2(),
        joinMasterWorkMaker.getJoinSourPartsNoWithH(), joinMasterWorkMaker.getJoinDispOrder());

    JoinMasterMaker joinMasterMaker = joinMasterMakerDao.findById(joinMasterMakerId);
    if (joinMasterMaker == null) {
      return (short) ManageKbnEnum.Add.getValue();
    } else {
      if(joinMasterWorkMaker.getDeleteFlg() != null){
        /** インポートデータの削除依頼区分＝1 削除するの場合*/
        if (joinMasterWorkMaker.getDeleteFlg() == DeleteFlgEnum.Delete.getValue()) {
          return (short) ManageKbnEnum.Delete.getValue();
        }else if (joinMasterWorkMaker.getDeleteFlg() == DeleteFlgEnum.NoDelete.getValue()) {
          /** インポートデータの削除依頼区分＝0 削除しないの場合*/
          return (short) ManageKbnEnum.Update.getValue();
        }
      }
    }
    /** インポートデータの削除依頼区分＝null 削除しないの場合*/
    return (short) ManageKbnEnum.Update.getValue();
  }

  /**
   * <pre>
   * 既存BL承認済みの商品を取得する。
   * </pre>
   *
   * @param goodsMasterWorkMakerList List
   * @param loginUserCd int
   * @return int[0] 既存BL承認済みの商品の件数
   *                 int[1] TSVファイルに存在するBL承認済み商品件数
   *                 int[2] 既存BL承認済みの商品の件数-TSVファイルに存在するBL承認済み商品件数
   */
  @Override
  public int[] getConsentGoodsNum(int loginUserCd, List<GoodsMasterWorkMaker> goodsMasterWorkMakerList) {
    int[] consentNum = new int[3];
    /** メーカーコード＝ログインのメーカーコード*/
    NumberFilter makerCdFilter = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD, loginUserCd);
    /** 論理削除区分＝0:有効*/
    NumberFilter logicalDelDivFilter = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV, LogicDeleteDivEnum.Valid.getValue());
    /** BL登録区分＝2:登録済み*/
    NumberFilter blEntryFlgFilter = NumberFilterBuilder.equals(GoodsMasterMaker.BL_ENTRY_FLG, BlEntryFlgEnum.Registered.getValue());
    Filter filter = new AndFilter(makerCdFilter, logicalDelDivFilter, blEntryFlgFilter);
    List<GoodsMasterMaker> goodsMasterMakerList = goodsMasterMakerDao.findByFilter(filter, null);
    /** int[0] 既存BL承認済みの商品の件数*/
    consentNum[0] = goodsMasterMakerList.size();
    int numTemp = 0;
    for(GoodsMasterWorkMaker goodsMasterWorkMaker : goodsMasterWorkMakerList){
      /** 優良設定詳細コード１、メーカーコード、優良品番(－付き品番)*/
      GoodsMasterMakerId goodsMasterMakerId = new GoodsMasterMakerId(goodsMasterWorkMaker.getPrmSetDtlNo1(),
          goodsMasterWorkMaker.getPartsMakerCd(), goodsMasterWorkMaker.getPrimePartsNoWithH());
      GoodsMasterMaker goodsMasterMaker = goodsMasterMakerDao.findById(goodsMasterMakerId);
      if(goodsMasterMaker != null && 
          (goodsMasterMaker.getBlEntryFlg() == BlEntryFlgEnum.Registered.getValue()) &&
          (goodsMasterMaker.getLogicalDelDiv() == LogicDeleteDivEnum.Valid.getValue())){
        numTemp ++;
      }
    }
    /** int[1] TSVファイルに存在するBL承認済み商品件数*/
    consentNum[1] = numTemp;
    /** int[1] TSVファイルに存在するBL承認済み商品件数*/
    consentNum[2] = consentNum[0] - numTemp;
    return consentNum;
  }

  /**
   * <pre>
   * 既存BL承認済みのセットを取得する。
   * </pre>
   *
   * @param setMasterWorkMakerList List
   * @param loginUserCd int
   * @return int[0] 既存BL承認済みのセットの件数
   *                 int[1] TSVファイルに存在するBL承認済みセット件数
   *                 int[2] 既存BL承認済みの商品の件数-TSVファイルに存在するBL承認済みセット件数
   */
  @Override
  public int[] getConsentSetNum(int loginUserCd, List<SetMasterWorkMaker> setMasterWorkMakerList) {
    int[] consentNum = new int[3];
    /** メーカーコード＝ログインのメーカーコード*/
    NumberFilter makerCdFilter = NumberFilterBuilder.equals(SetMasterMaker.PARTS_MAKER_CD, loginUserCd);
    /** 論理削除区分＝0:有効*/
    NumberFilter logicalDelDivFilter = NumberFilterBuilder.equals(SetMasterMaker.LOGICAL_DEL_DIV, LogicDeleteDivEnum.Valid.getValue());
    /** BL登録区分＝2:登録済み*/
    NumberFilter blEntryFlgFilter = NumberFilterBuilder.equals(SetMasterMaker.BL_ENTRY_FLG, BlEntryFlgEnum.Registered.getValue());
    Filter filter = new AndFilter(makerCdFilter, logicalDelDivFilter, blEntryFlgFilter);
    List<SetMasterMaker> setMasterMakerList = setMasterMakerDao.findByFilter(filter, null);
    /** int[0] 既存BL承認済みのセットの件数*/
    consentNum[0] = setMasterMakerList.size();
    int numTemp = 0;
    for(SetMasterWorkMaker setMasterWorkMaker : setMasterWorkMakerList){
      /** 優良設定詳細コード１、ファイルのメーカーコード、優良親品番、セット表示順位*/
      SetMasterMakerId setMasterMakerId = new SetMasterMakerId(setMasterWorkMaker.getPrmSetDtlNo1(),
          setMasterWorkMaker.getPartsMakerCd(), setMasterWorkMaker.getSetMainPartsNo(),
          setMasterWorkMaker.getSetDispOrder());
      SetMasterMaker setMasterMaker = setMasterMakerDao.findById(setMasterMakerId);
      if(setMasterMaker != null && 
          (setMasterMaker.getBlEntryFlg() == BlEntryFlgEnum.Registered.getValue()) &&
          (setMasterMaker.getLogicalDelDiv() == LogicDeleteDivEnum.Valid.getValue())){
        numTemp ++;
      }
    }
    /** int[1] TSVファイルに存在するBL承認済みセット件数*/
    consentNum[1] = numTemp;
    /** int[1] TSVファイルに存在するBL承認済みセット件数*/
    consentNum[2] = consentNum[0] - numTemp;
    return consentNum;
  }
  
  /**
   * <pre>
   * 既存BL承認済みの結合を取得する。
   * </pre>
   *
   * @param joinMasterWorkMakerList List
   * @param loginUserCd int
   * @return int[0] 既存BL承認済みの結合の件数
   *                 int[1] TSVファイルに存在するBL承認済み結合件数
   *                 int[2] 既存BL承認済みの商品の件数-TSVファイルに存在するBL承認済み結合件数
   */
  @Override
  public int[] getConsentJoinNum(int loginUserCd, List<JoinMasterWorkMaker> joinMasterWorkMakerList) {
    int[] consentNum = new int[3];
    /** メーカーコード＝ログインのメーカーコード*/
    NumberFilter makerCdFilter = NumberFilterBuilder.equals(JoinMasterMaker.PARTS_MAKER_CD, loginUserCd);
    /** 論理削除区分＝0:有効*/
    NumberFilter logicalDelDivFilter = NumberFilterBuilder.equals(JoinMasterMaker.LOGICAL_DEL_DIV, LogicDeleteDivEnum.Valid.getValue());
    /** BL登録区分＝2:登録済み*/
    NumberFilter blEntryFlgFilter = NumberFilterBuilder.equals(JoinMasterMaker.BL_ENTRY_FLG, BlEntryFlgEnum.Registered.getValue());
    Filter filter = new AndFilter(makerCdFilter, logicalDelDivFilter, blEntryFlgFilter);
    List<JoinMasterMaker> joinMasterMakerList = joinMasterMakerDao.findByFilter(filter, null);
    /** int[0] 既存BL承認済みの結合の件数*/
    consentNum[0] = joinMasterMakerList.size();
    int numTemp = 0;
    for(JoinMasterWorkMaker joinMasterWorkMaker : joinMasterWorkMakerList){
      /** 優良設定詳細コード１、ファイルのメーカーコード、優良親品番、セット表示順位*/
      JoinMasterMakerId joinMasterMakerId = new JoinMasterMakerId(joinMasterWorkMaker.getPrmSetDtlNo1(),
          joinMasterWorkMaker.getPartsMakerCd(), joinMasterWorkMaker.getTbsPartsCode(),
          joinMasterWorkMaker.getJoinSourceMakerCode(), joinMasterWorkMaker.getPrmSetDtlNo2(),
          joinMasterWorkMaker.getJoinSourPartsNoWithH(), joinMasterWorkMaker.getJoinDispOrder());
      JoinMasterMaker joinMasterMaker = joinMasterMakerDao.findById(joinMasterMakerId);
      if(joinMasterMaker != null && 
          (joinMasterMaker.getBlEntryFlg() == BlEntryFlgEnum.Registered.getValue()) &&
          (joinMasterMaker.getLogicalDelDiv() == LogicDeleteDivEnum.Valid.getValue())){
        numTemp ++;
      }
    }
    /** int[1] TSVファイルに存在するBL承認済み結合件数*/
    consentNum[1] = numTemp;
    /** int[1] TSVファイルに存在するBL承認済み結合件数*/
    consentNum[2] = consentNum[0] - numTemp;
    return consentNum;
  }
  
  /**
   * <pre>
   * メセッジを作る。
   * </pre>
   *
   * @param data String
   * @param maxLength int
   * @param maxLengthMsg String
   * @param dataName String\
   * @param rowNum int
   * @return message String
   */
  private String makeLengthMsg(String data, int maxLength, String maxLengthMsg, String dataName, int rowNum){
    String message = null;
    if(data.length() > maxLength){
      message = MESSAGE_HEADER + rowNum + BLONG_TO + maxLengthMsg;
      String messageTemp = message.replace("$1", dataName);
      message = messageTemp.replace("$2", EMPTY+maxLength);
    }
    return message;
  }
  
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
  
  /** goodsMasterWorkMakerDao 商品マスタ(メーカー)ワーク*/
  private GenericDao<GoodsMasterWorkMaker, GoodsMasterWorkMakerId> goodsMasterWorkMakerDao;

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param goodsMasterWorkMakerDao 商品DAO
   */
  @Resource
  public void setGoodsMasterWorkMakerDao(GenericDao<GoodsMasterWorkMaker, GoodsMasterWorkMakerId> goodsMasterWorkMakerDao) {
    this.goodsMasterWorkMakerDao = goodsMasterWorkMakerDao;
  }
  
  /** SetMasterWorkMakerDao セットマスタ(メーカー)ワーク */
  private GenericDao<SetMasterWorkMaker, SetMasterWorkMakerId> setMasterWorkMakerDao;

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param setMasterWorkMakerDao セットDAO
   */
  @Resource
  public void setSetMasterWorkMakerDao(GenericDao<SetMasterWorkMaker, SetMasterWorkMakerId> setMasterWorkMakerDao) {
    this.setMasterWorkMakerDao = setMasterWorkMakerDao;
  }
  
  /** goodsMasterMakerDao 商品マスタ(メーカー)本*/
  private GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterMakerDao;

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
  
  /** setMasterMakerDao セットマスタ(メーカー)本*/
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
  
  /** JoinMasterWorkMakerDao　結合マスタ(メーカー)ワーク */
  private GenericDao<JoinMasterWorkMaker, JoinMasterWorkMakerId> joinMasterWorkMakerDao;

  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param joinMasterWorkMakerDao 結合DAO
   */
  @Resource
  public void setJoinMasterWorkMakerDao(GenericDao<JoinMasterWorkMaker, JoinMasterWorkMakerId> joinMasterWorkMakerDao) {
    this.joinMasterWorkMakerDao = joinMasterWorkMakerDao;
  }
  
  /** joinMasterMakerDao 結合マスタ(メーカー)本*/
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
  
  /** companyInfoMasterCommonDao　会社情報マスタ(共有)*/
  private GenericDao<CompanyInfoMasterCommon, Integer> companyInfoMasterCommonDao;

/**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param companyInfoMasterCommonDao 商品DAO
   */
  @Resource
  public void setCompanyInfoMasterCommonDao(GenericDao<CompanyInfoMasterCommon, Integer> companyInfoMasterCommonDao) {
    this.companyInfoMasterCommonDao = companyInfoMasterCommonDao;
  }
  
  /** 添字ゼロ*/
  private static final int ZERO = 0;
  /** 添字1*/
  private static final int ONE = 1;
  /** 添字2*/
  private static final int TWO= 2;
  /** 添字3*/
  private static final int THREE = 3;
  /** 添字4*/
  private static final int FOUR = 4;
  /** 添字5*/
  private static final int FIVE = 5;
  /** 添字6*/
  private static final int SIX = 6;
  /** 添字7*/
  private static final int SEVEN = 7;
  /** 添字8*/
  private static final int EIGHT = 8;
  /** 添字9*/
  private static final int NINE = 9;
  /** 添字10*/
  private static final int TEN = 10;
  /** 添字11*/
  private static final int ELEVEN = 11;
  /** 添字12*/
  private static final int TWELVE = 12;
  /** 添字13*/
  private static final int THIRTEEN = 13;
  /** 添字14*/
  private static final int FOURTEEN = 14;
  /** 添字15*/
  private static final int FIVETEEN = 15;
  /** 添字16*/
  private static final int SIXTEEN = 16;
  /** 添字17*/
  private static final int SEVENTEEN = 17;
  /** 添字18*/
  private static final int EIGHTEEN = 18;
  /** 添字19*/
  private static final int NINETEEN = 19;
  /** 添字20*/
  private static final int TWENTY = 20;
  /** 添字21*/
  private static final int TWENTY_ONE = 21;
  /** 添字22*/
  private static final int TWENTY_TWO = 22;
  /** 添字23*/
  private static final int TWENTY_THREE = 22;
  /** 添字24 **/
  private static final int TWENTY_FOUR = 24;
  /** 添字25 **/
  private static final int TWENTY_FIVE = 25;
  /** 添字26 **/
  private static final int TWENTY_SIX = 26;
  /** 添字27 **/
  private static final int TWENTY_SEVEN = 27;
  /** 添字28 **/
  private static final int TWENTY_EIGHT = 28;
  /** 添字30 **/
  private static final int THIRTY = 30;
  /** 添字31 **/
  private static final int THIRTY_ONE = 31;
  /** 添字60*/
  private static final int SIXTY  = 60;
  /** 添字80*/
  private static final int EIGHTY  = 80;
  /** 添字512*/
  private static final int LONG_STRING  = 512;
  /** メセッジヘッダ **/
  private static final String MESSAGE_HEADER = "のデータ行No.";
  /** 優良設定詳細コード１*/
  private static final String PRM_SET_DTL_NO_1 = "セレクトコード";
  /** メーカーコード*/
  private static final String PARTS_MAKER_CD = "メーカーコード";
  /** 商品中分類コード*/
  private static final String GOODS_M_GROUP = "分類コード";
  /** 優良品番 **/
  private static final String PRIME_PARTS_NO = "優良品番";
  /** オープン価格区分*/
  private static final String OPEN_PRICE = "オープン価格区分";
  /** JAN*/
  private static final String JAN = "JAN";
  /** セット親品番*/
  private static final String SET_MAIN_PARTS_NO = "優良親品番";
  /** セット子品番*/
  private static final String SET_SUB_PARTS_NO = "優良子品番";
  /** 商品サイズ(長さ）*/
  private static final String GOODS_SIZE1 = "商品サイズ(長さ）";
  /** 商品サイズ(幅）*/
  private static final String GOODS_SIZE2 = "商品サイズ(幅）";
  /** 商品サイズ(高さ））*/
  private static final String GOODS_SIZE3 = "商品サイズ(高さ）";
  /** 梱包サイズ(長さ）*/
  private static final String PACKAGE_SIZE1 = "梱包サイズ(長さ）";
  /** 梱包サイズ(幅）*/
  private static final String PACKAGE_SIZE2 = "梱包サイズ(幅）";
  /** 梱包サイズ(高さ））*/
  private static final String PACKAGE_SIZE3 = "梱包サイズ(高さ）";
  /** 商品重量*/
  private static final String GOODS_WEIGHT = "商品重量";
  /** 品名*/
  private static final String SET_KANA_NAME = "品名";
  /** 画像有無区分*/
  private static final String IMAGE_NO = "画像有無区分";
  /** 削除依頼区分*/
  private static final String DELETE_FLG = "削除依頼区分";
  /** セット名称*/
  private static final String SET_NAME = "セット名称";
  /** セット表示順位*/
  private static final String SET_DISP_ORDER = "セット表示順位";
  /** 優良部品名称 **/
  private static final String PRIME_PARTS_NAME = "優良部品名称";
  /** 優良部品カナ名称 **/
  private static final String PRIME_PARTS_KANA_NAME = "優良部品カナ名称";
  /** 層別コード*/
  private static final String PARTS_LAYER_CD = "層別コード";
  /** 装備名称*/
  private static final String EQUIP_NAME = "装備名称";
  /** 優良部品規格・特記事項(B向け)*/
  private static final String PRIME_PARTS_SPECIAL_NOTE_B = "優良部品規格・特記事項(B向け)";
  /** 優良部品規格・特記事項(C向け)*/
  private static final String PRIME_PARTS_SPECIAL_NOTE_C = "優良部品規格・特記事項(C向け)";
  /** セット規格・特記事項*/
  private static final String SET_SPECIAL_NOTE = "セット規格・特記事項";
  /** 結合規格・特記事項*/
  private static final String JOIN_SPECIAL_NOTE = "規格・特記事項";
  /** 商品詳細(B向け)*/
  private static final String GOODS_DETAIL_B = "商品詳細(B向け)";
  /** 商品詳細(C向け)*/
  private static final String GOODS_DETAIL_C = "商品詳細(C向け)";
  /** サイズ単位*/
  private static final String SIZE_UNIT = "サイズ単位";
  /** 重量単位*/
  private static final String WEIGHT_UNIT = "重量単位";
  /** URL1*/
  private static final String URL1 = "URL1";
  /** URL2*/
  private static final String URL2 = "URL2";
  /** URL3*/
  private static final String URL3 = "URL3";
  /** 削除理由*/
  private static final String DELETE_REASON = "削除理由";
  /** BLコード **/
  private static final String TBS_PARTS_CODE = "BLコード";
  /** 結合元メーカーコード*/
  private static final String JOIN_SOURCE_MAKER_CODE = "結合元メーカーコード";
  /** 優良設定詳細コード2*/
  private static final String PRM_SET_DTL_NO_2 = "優良設定詳細コード２";
  /** 結合元品番*/
  private static final String JOIN_PARTS_NO = "純正品番";
  /** 結合表示順位*/
  private static final String JOIN_DISP_ORDER= "表示順位";
  /** 文字列：の. */
  public static final String BLONG_TO = "の";
  /** 文字列：空白文字. */
  public static final String EMPTY = "";
  /** 接続. */
  public static final String CONNECTION = " と No.";
}
