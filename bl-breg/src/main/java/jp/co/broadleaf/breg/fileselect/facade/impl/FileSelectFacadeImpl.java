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
package jp.co.broadleaf.breg.fileselect.facade.impl;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.entity.GoodsMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMaker;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.BlEntryFlgEnum;
import jp.co.broadleaf.breg.common.enums.CheckFlgEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.fileselect.facade.FileSelectFacade;
import jp.co.broadleaf.breg.fileselect.service.FileSelectService;
import jp.co.broadleaf.breg.message.service.MessageService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * ファイル選択Facadeクラス.
 * </pre>
 */
public class FileSelectFacadeImpl implements FileSelectFacade {
  
  /** 商品 */
  private static final String GOODS = "goods";
  /** セット */
  private static final String SET = "set";
  /** 結合 */
  private static final String JOIN = "join";
  /** 結合 */
  private static final String TIME_FORMAT = " 00:00:00";
  /** 優良設定詳細コード１*/
  private static final String PRM_SET_DTL_NO_1 = "セレクトコード";
  /** メーカーコード*/
  private static final String PARTS_MAKER_CD = "メーカーコード";
  /** 商品中分類コード*/
  private static final String GOODS_M_GROUP = "分類コード";
  /** BLコード **/
  private static final String TBS_PARTS_CODE = "BLコード";
  /** JAN*/
  private static final String JAN = "JAN";
  /** 優良定価*/
  private static final String NEW_PRICE = "優良定価";
  /** オープン価格区分*/
  private static final String OPEN_PRICE = "オープン価格区分";
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
  /** 画像有無区分*/
  private static final String IMAGE_NO = "画像有無区分";
  /** 削除依頼区分*/
  private static final String DELETE_FLG = "削除依頼区分";
  /** 優良設定詳細コード2*/
  private static final String PRM_SET_DTL_NO_2 = "優良設定詳細コード２";
  /** セット表示順位*/
  private static final String SET_DISP_ORDER = "セット表示順位";
  /** セットQTY*/
  private static final String SET_QTY = "セットQTY";
  /** 結合QTY*/
  private static final String JOIN_QTY = "結合QTY";
  /** 結合元メーカーコード*/
  private static final String JOIN_SOURCE_MAKER_CODE = "結合元メーカーコード";
  /** 結合表示順位*/
  private static final String JOIN_DISP_ORDER= "表示順位";
  /** デジタル型メセッジフッター **/
  private static final String NUMBER_MESSAGE = "半角数字";
  /** 適用日付 **/
  private static final String DATE_TIME = "適用日付";
  /** 異常な状態*/
  private static final String EXCEPTION_MSG = "error";
  /** 文字列：の. */
  public static final String BLONG_TO = "の";
  /** 文字列：空白文字. */
  public static final String EMPTY = "";
  /** 商品ファイルの情報*/
  List<GoodsMasterWorkMaker> goodsWorkList = null;
  /** セットファイルの情報*/
  List<SetMasterWorkMaker> setWorkList = null;
  /** 結合ファイルの情報*/
  List<JoinMasterWorkMaker> joinWorkList = null;
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
  /** FileSelectService クラス */
  private FileSelectService fileSelectService;
  
  /**
   * <pre>
   * ファイルの情報をゲットする。
   * </pre>
   *
   * @param string String[]
   * @return list List
   */
  @Override
  public List<String[]> getFileValue(String[] string) {
    List<String[]> list = new ArrayList<String[]>();
    int len = string.length;
    int strFinLen = len - 10;
    String[] strFinal = new String[strFinLen];
    /** HTTPの内容を削除する */
    System.arraycopy(string, 4, strFinal, 0, strFinLen);
    /** ファイルの情報を処理する */
    for(String fileRowTemp : strFinal){
      String fileRowS = fileRowTemp.replace("\n", "");
      String[] fileRow = fileRowS.split("\t");
      if(fileRow.length < 2){
        continue;
      }
      String[] fileRowContent = new String[32];
      for(int i = 0; i < fileRowContent.length; i++){
        if(fileRow.length > i){
          fileRowContent[i] = fileRow[i];
        }else{
          fileRowContent[i] = EMPTY;
        }
      }
      list.add(fileRowContent);
    }
    return list;
  }

  /**
   * <pre>
   * ファイルのフォーマートをチェックする。
   * </pre>
   *
   * @param fileFirst String[]
   * @param tableRow String[]
   * @return fileFirstErr List
   */
  @Override
  public List<String> fileFirstVai(String[] fileFirst, String[] tableRow) {
    int len = tableRow.length;
    String formatErrMsg = BLONG_TO + messageService.messageInfo(BregMessageCodes.E02102);
    List<String> fileFirstErr = new ArrayList<String>();
    for(int i = 0; i < len; i++){
      if (!fileFirst[i].equals(tableRow[i])){
        fileFirstErr.add(formatErrMsg);
        break;
      }
    }
    if(fileFirstErr.isEmpty()){
      return null;
    }
    return fileFirstErr;
  }

  /**
   * <pre>
   * ファイルの情報はデータベースに挿入する。
   * </pre>
   *
   */
  @Override
  public void fileToDb() {
    if(goodsWorkList != null && !goodsWorkList.isEmpty()){
      fileSelectService.insertGoodsInfoList(goodsWorkList);
    }
    if(setWorkList != null && !setWorkList.isEmpty()){
      fileSelectService.insertSetInfoList(setWorkList);
    }
    if(joinWorkList != null && !joinWorkList.isEmpty()){
      fileSelectService.insertJoinInfoList(joinWorkList);
    }
    goodsWorkList = null;
    setWorkList = null;
    joinWorkList = null;
  }
  
  /**
   * <pre>
   * ファイルの情報は挿入前にチェックする。
   * </pre>
   *
   * @param list String[]
   * @param fileType String
   * @param importTypeMenu Integer
   * @param loginUserCd Integer
   * @return insertStatus List
   */
  @Override
  public List<String> fileConversionCheck(List<String[]> list, String fileType, int importTypeMenu, int loginUserCd) {
    List<String> insertStatus = new ArrayList<String>();
    if(fileType.equals(GOODS)){
      insertStatus = this.goodsWorkConversion(list, importTypeMenu, loginUserCd);
    }else if(fileType.equals(SET)){
      insertStatus = this.setWorkConversion(list, importTypeMenu, loginUserCd);
    }else if(fileType.equals(JOIN)){
      insertStatus = this.joinWorkConversion(list, importTypeMenu, loginUserCd);
    }
    return insertStatus;
  }
  /**
   * <pre>
   * 商品オブジェクトに情報を追加する。
   * </pre>
   *
   * @param list String[]
   * @param importTypeMenu Integer
   * @param loginUserCd Integer
   * @return goodsInsertStatus List
   */
  private List<String> goodsWorkConversion(List<String[]> list, int importTypeMenu, int loginUserCd){
    List<String> goodsInsertStatus = new ArrayList<String>();
    List<GoodsMasterWorkMaker> goodsWorkListTemp = new ArrayList<GoodsMasterWorkMaker>();
    String makerCdMsg = messageService.messageInfo(BregMessageCodes.E02103);
    String dataTypeMsg = messageService.messageInfo(BregMessageCodes.E02101);
    String numberMsg = dataTypeMsg.replace("{2}", NUMBER_MESSAGE);
    String dateMsg = BLONG_TO + numberMsg.replace("{1}", DATE_TIME);
    int rowNum = 1;
    goodsInsertStatus.addAll(fileSelectService.goodsCheck(list));
    for(String[] goods : list){
      GoodsMasterWorkMaker goodsWork = new GoodsMasterWorkMaker();
      String prmSetNumberResult = this.numberIntFormatCheck(goods[0]);
      if(prmSetNumberResult != null && !prmSetNumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setPrmSetDtlNo1(Integer.parseInt(prmSetNumberResult));          /** 優良設定詳細コード１を設定します。*/
      }else{
        goodsInsertStatus.add(this.makeMsg(rowNum, PRM_SET_DTL_NO_1, numberMsg) );
      }
      String partsMakerCdNumberResult = this.numberIntFormatCheck(goods[1]);
      if(partsMakerCdNumberResult != null && !partsMakerCdNumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setPartsMakerCd(Integer.parseInt(partsMakerCdNumberResult));          /** 部品メーカーコードを設定します。*/
        if(Integer.parseInt(partsMakerCdNumberResult) != loginUserCd){
          goodsInsertStatus.add(BLONG_TO + makerCdMsg.replace("{0}", EMPTY+rowNum) );
        }
      }else{
        goodsInsertStatus.add(this.makeMsg(rowNum, PARTS_MAKER_CD, numberMsg));
      }
      String goodsMGroupNumberResult = this.numberIntFormatCheck(goods[2]);
      if(goodsMGroupNumberResult != null && !goodsMGroupNumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setGoodsMGroup(Integer.parseInt(goodsMGroupNumberResult));          /** 商品中分類コードを設定します。*/
      }else if(goodsMGroupNumberResult != null && goodsMGroupNumberResult.equals(EXCEPTION_MSG)){
        goodsInsertStatus.add(this.makeMsg(rowNum, GOODS_M_GROUP, numberMsg));
      }
      String tbsPartsCodeNumberResult = this.numberIntFormatCheck(goods[3]);
      if(tbsPartsCodeNumberResult != null && !tbsPartsCodeNumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setTbsPartsCode(Integer.parseInt(tbsPartsCodeNumberResult));          /** BLコードを設定します。*/
      }else if(tbsPartsCodeNumberResult != null && tbsPartsCodeNumberResult.equals(EXCEPTION_MSG)){
        goodsInsertStatus.add(this.makeMsg(rowNum, TBS_PARTS_CODE, numberMsg));
      }
      goodsWork.setPrimePartsNoWithH(goods[4]);                             /** 優良品番(－付き品番)を設定します。*/
      goodsWork.setPrimePartsKanaNm(goods[5]);                              /** 優良部品カナ名称を設定します。*/
      goodsWork.setPrimePartsName(goods[6]);                                  /** 優良部品名称を設定します。*/
      if(!goods[7].isEmpty()){                                  /** ファイルの情報がいる。*/
        String newPriceNumberResult = this.numberDouFormatCheck(goods[7]);
        if(!newPriceNumberResult.equals(EXCEPTION_MSG)){
          goodsWork.setNewPrice(Double.parseDouble(newPriceNumberResult));          /** 新価格を設定します。*/
        }else{
          goodsInsertStatus.add(this.makeMsg(rowNum, NEW_PRICE, numberMsg));
        }
      }
      String openPriceNumberResult = this.numberIntFormatCheck(goods[8]);
      if(openPriceNumberResult != null && !openPriceNumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setOpenPriceDiv(Integer.parseInt(openPriceNumberResult));          /** オープン価格区分を設定します。*/
      }else if(openPriceNumberResult != null && openPriceNumberResult.equals(EXCEPTION_MSG)){
        goodsInsertStatus.add(this.makeMsg(rowNum, OPEN_PRICE, numberMsg));
      }
      String janNumberResult = this.numberLongFormatCheck(goods[9]);
      if(janNumberResult != null && !janNumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setJan(Long.parseLong(janNumberResult));          /** JANを設定します。*/
      }else if(janNumberResult != null && janNumberResult.equals(EXCEPTION_MSG)){
        goodsInsertStatus.add(this.makeMsg(rowNum, JAN, numberMsg));
      }
      goodsWork.setPartsLayerCd(goods[10]);                                      /** 層別コードを設定します。*/
      goodsWork.setEquipName(goods[11]);                                        /** 装備名称を設定します。*/
      goodsWork.setPrimePartsSpecialNoteB(goods[12]);                   /** 優良部品規格・特記事項(B向け)を設定します。*/
      goodsWork.setPrimePartsSpecialNoteC(goods[13]);                   /** 優良部品規格・特記事項(C向け)を設定します。*/
      goodsWork.setGoodsDetailB(goods[14]);                                     /** 商品詳細(B向け)を設定します。*/
      goodsWork.setGoodsDetailC(goods[15]);                                     /** 商品詳細(C向け)を設定します。*/
      
      
      String goodsSize1NumberResult = this.numberIntFormatCheck(goods[16]);
      if(goodsSize1NumberResult != null && !goodsSize1NumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setGoodsSize1(Integer.parseInt(goodsSize1NumberResult));          /** 商品サイズ(長さ）を設定します。*/
      }else if(goodsSize1NumberResult != null && goodsSize1NumberResult.equals(EXCEPTION_MSG)){
        goodsInsertStatus.add(this.makeMsg(rowNum, GOODS_SIZE1, numberMsg));
      }
      
      String goodsSize2NumberResult = this.numberIntFormatCheck(goods[17]);
      if(goodsSize2NumberResult != null && !goodsSize2NumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setGoodsSize2(Integer.parseInt(goodsSize2NumberResult));          /** 商品サイズ(幅）を設定します。*/
      }else if(goodsSize2NumberResult != null && goodsSize2NumberResult.equals(EXCEPTION_MSG)){
        goodsInsertStatus.add(this.makeMsg(rowNum, GOODS_SIZE2, numberMsg));
      }
      String goodsSize3NumberResult = this.numberIntFormatCheck(goods[18]);
      if(goodsSize3NumberResult != null && !goodsSize3NumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setGoodsSize3(Integer.parseInt(goodsSize3NumberResult));          /** 商品サイズ(高さ）を設定します。*/
      }else if(goodsSize3NumberResult != null && goodsSize3NumberResult.equals(EXCEPTION_MSG)){
        goodsInsertStatus.add(this.makeMsg(rowNum, GOODS_SIZE3, numberMsg));
      }
      String packageSize1NumberResult = this.numberIntFormatCheck(goods[19]);
      if(packageSize1NumberResult != null && !packageSize1NumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setPackageSize1(Integer.parseInt(packageSize1NumberResult));          /** 梱包サイズ(長さ）を設定します。*/
      }else if(packageSize1NumberResult != null && packageSize1NumberResult.equals(EXCEPTION_MSG)){
        goodsInsertStatus.add(this.makeMsg(rowNum, PACKAGE_SIZE1, numberMsg));
      }
      String packageSize2NumberResult = this.numberIntFormatCheck(goods[20]);
      if(packageSize2NumberResult != null && !packageSize2NumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setPackageSize2(Integer.parseInt(packageSize2NumberResult));          /** 梱包サイズ(幅）を設定します。*/
      }else if(packageSize2NumberResult != null && packageSize2NumberResult.equals(EXCEPTION_MSG)){
        goodsInsertStatus.add(this.makeMsg(rowNum, PACKAGE_SIZE2, numberMsg));
      }
      String packageSize3NumberResult = this.numberIntFormatCheck(goods[21]);
      if(packageSize3NumberResult != null && !packageSize3NumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setPackageSize3(Integer.parseInt(packageSize3NumberResult));          /** 梱包サイズ(高さ）を設定します。*/
      }else if(packageSize3NumberResult != null && packageSize3NumberResult.equals(EXCEPTION_MSG)){
        goodsInsertStatus.add(this.makeMsg(rowNum, PACKAGE_SIZE3, numberMsg));
      }
      goodsWork.setSizeUnit(goods[22]);                                               /** サイズ単位を設定します。*/
      
      String goodsWeightNumberResult = this.numberIntFormatCheck(goods[23]);
      if(goodsWeightNumberResult != null && !goodsWeightNumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setGoodsWeight(Integer.parseInt(goodsWeightNumberResult));          /** 商品重量を設定します。*/
      }else if(goodsWeightNumberResult != null && goodsWeightNumberResult.equals(EXCEPTION_MSG)){
        goodsInsertStatus.add(this.makeMsg(rowNum, GOODS_WEIGHT, numberMsg));
      }
      goodsWork.setWeightUnit(goods[24]);                                        /** 重量単位を設定します。*/
      goodsWork.setUrl1(goods[25]);                                                    /** URL1を設定します。*/
      goodsWork.setUrl2(goods[26]);                                                    /** URL2を設定します。*/
      goodsWork.setUrl3(goods[27]);                                                    /** URL3を設定します。*/
      String imageNoNumberResult = this.numberShortFormatCheck(goods[28]);
      if(imageNoNumberResult != null && !imageNoNumberResult.equals(EXCEPTION_MSG)){
        goodsWork.setImageNo(Short.parseShort(imageNoNumberResult));          /** 画像有無区分を設定します。*/
      }else if(imageNoNumberResult != null && imageNoNumberResult.equals(EXCEPTION_MSG)){
        goodsInsertStatus.add(this.makeMsg(rowNum, IMAGE_NO, numberMsg));
      }
      Timestamp timestampResult = this.timestampFormatCheck(goods[29]);            /** 適用日付を設定します。*/
      if(timestampResult != null){
        goodsWork.setStartTime(timestampResult);
      }else{
        goodsInsertStatus.add(dateMsg.replace("{0}", EMPTY+rowNum));
      }
      String deleteFlgNumberResult = this.numberShortFormatCheck(goods[30]);
      if(deleteFlgNumberResult == null){
        goodsWork.setDeleteFlg((short) DeleteFlgEnum.NoDelete.getValue());          /** 削除依頼区分を設定します。*/
      }else if(deleteFlgNumberResult.equals(EXCEPTION_MSG)){
        goodsInsertStatus.add(this.makeMsg(rowNum, DELETE_FLG, numberMsg));
      }else{
        goodsWork.setDeleteFlg(Short.parseShort(deleteFlgNumberResult));
      }
      goodsWork.setDeleteReason(goods[31]);                                      /** 削除理由を設定します。*/
      goodsWork.setCheckFlg((short)CheckFlgEnum.Unchecked.getValue());     /** チェック区分を設定します。0:未チェック*/
      goodsWork.setErrorFlg((short) ErrorFlgEnum.NoError.getValue());   /** データステータスを設定します。0:エラー無し*/
      goodsWork.setBlEntryFlg((short)BlEntryFlgEnum.Unregistered.getValue());   /** BL登録区分を設定します。0:未登録*/   
      goodsWork.setImportKbn((short) importTypeMenu);                  /** インポート区分を設定します。0:インポート（一括申請）1:インポート*/
      goodsWork.setErrorDetail("");                                                       /** エラー内容を設定します。*/
      goodsWork.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());           /** 申請状態を設定します。*/
      /** 処理区分を設定します。0:追加,1:更新,2:削除*/
      goodsWork.setManageKbn(fileSelectService.getGoodsManage(goodsWork));
      goodsWorkListTemp.add(goodsWork);
      rowNum ++;
    }
    List<String> nullStringList = new ArrayList<String>();
    nullStringList.add(null);
    goodsInsertStatus.removeAll(nullStringList);
    if(goodsInsertStatus.isEmpty()){
      goodsWorkList = goodsWorkListTemp;
    }
    return goodsInsertStatus;
  }

  /**
   * <pre>
   * セットオブジェクトに情報を追加する。
   * </pre>
   *
   * @param list String[]
   * @param importTypeMenu Integer
   * @param loginUserCd Integer
   * @return setInsertStatus List
   */
  private List<String> setWorkConversion(List<String[]> list, int importTypeMenu, int loginUserCd){
    List<String> setInsertStatus = new ArrayList<String>();
    List<SetMasterWorkMaker> setWorkListTemp = new ArrayList<SetMasterWorkMaker>();
    String makerCdMsg = messageService.messageInfo(BregMessageCodes.E02103);
    String dataTypeMsg = messageService.messageInfo(BregMessageCodes.E02101);
    String numberMsg = dataTypeMsg.replace("{2}", NUMBER_MESSAGE);
    String dateMsg = BLONG_TO + numberMsg.replace("{1}", DATE_TIME);
    setInsertStatus.addAll(fileSelectService.setCheck(list));
    int rowNum = 1;
    for(String[] set : list){
      SetMasterWorkMaker setWork = new SetMasterWorkMaker();
      String prmSetNumberResult = this.numberIntFormatCheck(set[0]);
      if(prmSetNumberResult != null && !prmSetNumberResult.equals(EXCEPTION_MSG)){
        setWork.setPrmSetDtlNo1(Integer.parseInt(prmSetNumberResult));          /** 優良設定詳細コード１を設定します。*/
      }else{
        setInsertStatus.add(this.makeMsg(rowNum, PRM_SET_DTL_NO_1, numberMsg) );
      }
      String partsMakerCdNumberResult = this.numberIntFormatCheck(set[1]);
      if(partsMakerCdNumberResult != null && !partsMakerCdNumberResult.equals(EXCEPTION_MSG)){
        setWork.setPartsMakerCd(Integer.parseInt(partsMakerCdNumberResult));          /** 部品メーカーコードを設定します。*/
        if(Integer.parseInt(partsMakerCdNumberResult) != loginUserCd){
          setInsertStatus.add(BLONG_TO + makerCdMsg.replace("{0}", EMPTY+rowNum));
        }
      }else{
        setInsertStatus.add(this.makeMsg(rowNum, PARTS_MAKER_CD, numberMsg));
      }
      String setMGroupNumberResult = this.numberIntFormatCheck(set[2]);
      if(setMGroupNumberResult != null && !setMGroupNumberResult.equals(EXCEPTION_MSG)){
        setWork.setGoodsMGroup(Integer.parseInt(setMGroupNumberResult));          /** 商品中分類コードを設定します。*/
      }else if(setMGroupNumberResult != null && setMGroupNumberResult.equals(EXCEPTION_MSG)){
        setInsertStatus.add(this.makeMsg(rowNum, GOODS_M_GROUP, numberMsg));
      }
      String tbsPartsCodeNumberResult = this.numberIntFormatCheck(set[3]);
      if(tbsPartsCodeNumberResult != null && !tbsPartsCodeNumberResult.equals(EXCEPTION_MSG)){
        setWork.setTbsPartsCode(Integer.parseInt(tbsPartsCodeNumberResult));          /** BLコードを設定します。*/
      }else if(tbsPartsCodeNumberResult != null && tbsPartsCodeNumberResult.equals(EXCEPTION_MSG)){
        setInsertStatus.add(this.makeMsg(rowNum, TBS_PARTS_CODE, numberMsg));
      }
      setWork.setSetMainPartsNo(set[4]);                       /** セット親品番を設定します。*/
      
      String setDispOrderNumberResult = this.numberIntFormatCheck(set[5]);
      if(setDispOrderNumberResult != null && !setDispOrderNumberResult.equals(EXCEPTION_MSG)){
        setWork.setSetDispOrder(Integer.parseInt(setDispOrderNumberResult));          /** セット表示順位を設定します。*/
      }else{
        setInsertStatus.add(this.makeMsg(rowNum, SET_DISP_ORDER, numberMsg));
      }
      setWork.setSetSubPartsNo(set[6]);                        /** セット子品番を設定します。*/
      setWork.setSetKanaName(set[7]);                          /** 品名を設定します。*/
      setWork.setSetName(set[8]);                              /** セット名称を設定します。*/
      String setQtyNumberResult = this.numberDouFormatCheck(set[9]);
      if(setQtyNumberResult != null && !setQtyNumberResult.equals(EXCEPTION_MSG)){
        setWork.setSetQty(Double.parseDouble(setQtyNumberResult));          /** セットQTYを設定します。*/
      }else if(setQtyNumberResult != null && setQtyNumberResult.equals(EXCEPTION_MSG)){
        setInsertStatus.add(this.makeMsg(rowNum, SET_QTY, numberMsg));
      }
      setWork.setSetSpecialNote(set[10]);                      /** セット規格・特記事項を設定します。*/
      setWork.setPrimePartsSpecialNoteC(set[11]);              /** 優良部品規格・特記事項(C向け)を設定します。*/
      Timestamp timestampResult = this.timestampFormatCheck(set[12]);            /** 適用日付を設定します。*/
      if(timestampResult != null){
        setWork.setStartTime(timestampResult);
      }else{
        setInsertStatus.add(dateMsg.replace("{0}", EMPTY+rowNum));
      }
      String deleteFlgNumberResult = this.numberShortFormatCheck(set[13]);
      if(deleteFlgNumberResult == null){
        setWork.setDeleteFlg((short) DeleteFlgEnum.NoDelete.getValue());         /** 削除依頼区分を設定します。*/
      }else if(deleteFlgNumberResult.equals(EXCEPTION_MSG)){
        setInsertStatus.add(this.makeMsg(rowNum, DELETE_FLG, numberMsg));
      }else{
        setWork.setDeleteFlg(Short.parseShort(deleteFlgNumberResult));
      }
      setWork.setDeleteReason(set[14]);                                 /** 削除理由を設定します。*/
      setWork.setCheckFlg((short) CheckFlgEnum.Unchecked.getValue());    /** チェック区分を設定します。0:未チェック*/
      setWork.setErrorFlg((short) ErrorFlgEnum.NoError.getValue());     /** データステータスを設定します。0:エラー無し*/
      setWork.setBlEntryFlg((short)BlEntryFlgEnum.Unregistered.getValue()); /** BL登録区分を設定します。0:未登録*/
      setWork.setErrorDetail("");                                             /** エラー内容を設定します。*/
      setWork.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());  /** 申請状態を設定します。 0:未申請*/
      setWork.setImportKbn((short) importTypeMenu);               /** インポート区分を設定します。*/
      /** 処理区分を設定します。0:追加,1:更新,2:削除*/
      setWork.setManageKbn(fileSelectService.getSetManage(setWork));
      setWorkListTemp.add(setWork);
      rowNum ++;
    }
    List<String> nullStringList = new ArrayList<String>();
    nullStringList.add(null);
    setInsertStatus.removeAll(nullStringList);
    if(setInsertStatus.isEmpty()){
      setWorkList = setWorkListTemp;
    }
    return setInsertStatus;
  }
  
  /**
   * <pre>
   * 結合オブジェクトに情報を追加する。
   * </pre>
   *
   * @param list String[]
   * @param importTypeMenu Integer
   * @param loginUserCd Integer
   * @return joinInsertStatus List
   */
  private List<String> joinWorkConversion(List<String[]> list, int importTypeMenu, int loginUserCd){
    List<String> joinInsertStatus = new ArrayList<String>();
    List<JoinMasterWorkMaker> joinWorkListTemp = new ArrayList<JoinMasterWorkMaker>();
    String makerCdMsg = messageService.messageInfo(BregMessageCodes.E02103);
    String dataTypeMsg = messageService.messageInfo(BregMessageCodes.E02101);
    String numberMsg = dataTypeMsg.replace("{2}", NUMBER_MESSAGE);
    String dateMsg = BLONG_TO + numberMsg.replace("{1}", DATE_TIME);
    joinInsertStatus.addAll(fileSelectService.joinCheck(list));
    int rowNum = 1;
    for(String[] join : list){
      JoinMasterWorkMaker joinWork = new JoinMasterWorkMaker();
      String prmSetNumberResult = this.numberIntFormatCheck(join[0]);
      if(prmSetNumberResult != null && !prmSetNumberResult.equals(EXCEPTION_MSG)){
        joinWork.setPrmSetDtlNo1(Integer.parseInt(prmSetNumberResult));          /** 優良設定詳細コード１を設定します。*/
      }else{
        joinInsertStatus.add(this.makeMsg(rowNum, PRM_SET_DTL_NO_1, numberMsg) );
      }
      String partsMakerCdNumberResult = this.numberIntFormatCheck(join[1]);
      if(partsMakerCdNumberResult != null && !partsMakerCdNumberResult.equals(EXCEPTION_MSG)){
        joinWork.setPartsMakerCd(Integer.parseInt(partsMakerCdNumberResult));          /** 部品メーカーコードを設定します。*/
        if(Integer.parseInt(partsMakerCdNumberResult) != loginUserCd){
          joinInsertStatus.add(BLONG_TO + makerCdMsg.replace("{0}", EMPTY+rowNum));
        }
      }else{
        joinInsertStatus.add(this.makeMsg(rowNum, PARTS_MAKER_CD, numberMsg));
      }
      String setMGroupNumberResult = this.numberIntFormatCheck(join[2]);
      if(setMGroupNumberResult != null && !setMGroupNumberResult.equals(EXCEPTION_MSG)){
        joinWork.setGoodsMGroup(Integer.parseInt(setMGroupNumberResult));          /** 商品中分類コードを設定します。*/
      }else if(setMGroupNumberResult != null && setMGroupNumberResult.equals(EXCEPTION_MSG)){
        joinInsertStatus.add(this.makeMsg(rowNum, GOODS_M_GROUP, numberMsg));
      }
      String tbsPartsCodeNumberResult = this.numberIntFormatCheck(join[3]);
      if(tbsPartsCodeNumberResult != null && !tbsPartsCodeNumberResult.equals(EXCEPTION_MSG)){
        joinWork.setTbsPartsCode(Integer.parseInt(tbsPartsCodeNumberResult));          /** BLコードを設定します。*/
      }else{
        joinInsertStatus.add(this.makeMsg(rowNum, TBS_PARTS_CODE, numberMsg));
      }
      String joinSourceMakerCdNumberResult = this.numberIntFormatCheck(join[4]);
      if(joinSourceMakerCdNumberResult != null && !joinSourceMakerCdNumberResult.equals(EXCEPTION_MSG)){
        joinWork.setJoinSourceMakerCode(Integer.parseInt(joinSourceMakerCdNumberResult));          /** 結合元メーカーコードを設定します。*/
      }else{
        joinInsertStatus.add(this.makeMsg(rowNum, JOIN_SOURCE_MAKER_CODE, numberMsg));
      }
      String prmSetDtlNo2NumberResult = this.numberIntFormatCheck(join[5]);
      if(prmSetDtlNo2NumberResult != null && !prmSetDtlNo2NumberResult.equals(EXCEPTION_MSG)){
        joinWork.setPrmSetDtlNo2(Integer.parseInt(prmSetDtlNo2NumberResult));          /** 優良設定詳細コード２を設定します。*/
      }else{
        joinInsertStatus.add(this.makeMsg(rowNum, PRM_SET_DTL_NO_2, numberMsg));
      }
      joinWork.setJoinSourPartsNoWithH(join[6]);                 /** 結合元品番(－付き品番)を設定します。*/
      String joinDispOrderNumberResult = this.numberIntFormatCheck(join[7]);
      if(joinDispOrderNumberResult != null && !joinDispOrderNumberResult.equals(EXCEPTION_MSG)){
        joinWork.setJoinDispOrder(Integer.parseInt(joinDispOrderNumberResult));          /** 結合表示順位を設定します。*/
      }else{
        joinInsertStatus.add(this.makeMsg(rowNum, JOIN_DISP_ORDER, numberMsg));
      }
      joinWork.setJoinDestPartsNo(join[8]);                      /** 結合先品番(－付き品番)を設定します。*/
      String joinQtyNumberResult = this.numberDouFormatCheck(join[9]);
      if(joinQtyNumberResult != null && !joinQtyNumberResult.equals(EXCEPTION_MSG)){
        joinWork.setJoinQty(Double.parseDouble(joinQtyNumberResult));          /** 結合QTYを設定します。*/
      }else{
        joinInsertStatus.add(this.makeMsg(rowNum, JOIN_QTY, numberMsg));
      }
      joinWork.setJoinSpecialNote(join[10]);                     /** 結合規格・特記事項を設定します。*/
      joinWork.setPrimePartsSpecialNoteC(join[11]);              /** 優良部品規格・特記事項(C向け)を設定します。*/
      Timestamp timestampResult = this.timestampFormatCheck(join[12]);            /** 適用日付を設定します。*/
      if(timestampResult != null){
        joinWork.setStartTime(timestampResult);
      }else{
        joinInsertStatus.add(dateMsg.replace("{0}", EMPTY+rowNum));
      }
      joinWork.setDeleteReason(join[13]);                                  /** 削除時申請理由を設定します。*/
      String deleteFlgNumberResult = this.numberShortFormatCheck(join[14]);
      if(deleteFlgNumberResult == null){
        joinWork.setDeleteFlg((short) DeleteFlgEnum.NoDelete.getValue());          /** 削除依頼区分を設定します。*/
      }else if(deleteFlgNumberResult.equals(EXCEPTION_MSG)){
        joinInsertStatus.add(this.makeMsg(rowNum, DELETE_FLG, numberMsg));
      }else{
        joinWork.setDeleteFlg(Short.parseShort(deleteFlgNumberResult));
      }
      joinWork.setImportKbn((short) importTypeMenu);                   /** インポート区分を設定します。*/
      joinWork.setCheckFlg((short) CheckFlgEnum.Unchecked.getValue());    /** チェック区分を設定します。0:未チェック*/
      joinWork.setErrorFlg((short) ErrorFlgEnum.NoError.getValue());     /** データステータスを設定します。0:エラー無し*/
      joinWork.setBlEntryFlg((short)BlEntryFlgEnum.Unregistered.getValue()); /** BL登録区分を設定します。0:未登録*/
      joinWork.setErrorDetail("");                                             /** エラー内容を設定します。*/
      joinWork.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());  /** 申請状態を設定します。 0:未申請*/
      /** 処理区分を設定します。0:追加,1:更新,2:削除*/
      joinWork.setManageKbn(fileSelectService.getJoinManage(joinWork));
      joinWorkListTemp.add(joinWork);
      rowNum ++;
    }
    List<String> nullStringList = new ArrayList<String>();
    nullStringList.add(null);
    joinInsertStatus.removeAll(nullStringList);
    if(joinInsertStatus.isEmpty()){
      joinWorkList = joinWorkListTemp;
    }
    return joinInsertStatus;
  }

  /**
   * <pre>
   * インポート方法をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @return fileSelectService.getImportType(loginUserCd )
   */
  @Override
  public int[] getImportType(int loginUserCd) {
    return fileSelectService.getImportType(loginUserCd );
  }

  /**
   * <pre>
   * 【fileSelectService】を設定する。
   * </pre>
   *
   * @param fileSelectService 【fileSelectService】
   */
  @Resource
  public void setFileSelectService(FileSelectService fileSelectService) {
    this.fileSelectService = fileSelectService;
  }

  /**
   * <pre>
   * データベースに無効データを削除する。
   * </pre>
   * 
   * @param loginUserCd Integer
   *
   */
  @Override
  public void deleteInvalidData(int loginUserCd) {
    /** loginUserCdについて、商品データを削除する。*/
    fileSelectService.deleteGoodsInfoAll(loginUserCd);
    /** loginUserCdについて、セットデータを削除する。*/
    fileSelectService.deleteSetInfoAll(loginUserCd);
    /** loginUserCdについて、結合データを削除する。*/
    fileSelectService.deleteJoinInfoAll(loginUserCd);
  }
  

  /**
   * <pre>
   * 商品ファイルの情報をチェックする(文字列->整数)。
   * </pre>
   *
   * @param formatContent String
   * @return formatResult String
   */
  private String numberIntFormatCheck(String formatContent) {
    String formatResult = EXCEPTION_MSG;
    /** 文字列がヌル時*/
    if(formatContent.isEmpty()){
      return null;
    }
    /** 変換とき、異常がある、データを処理する。*/
    try {
      Integer.parseInt(formatContent);
      formatResult = formatContent;
    } catch (Exception e) {
      return formatResult;
    }
    return formatResult;
  }
  
  /**
   * <pre>
   * ファイルの情報をチェックする(文字列->倍精度浮動小数点数)。
   * </pre>
   *
   * @param formatContent String
   * @return formatResult String
   */
  private String numberDouFormatCheck(String formatContent) {
    String formatResult = EXCEPTION_MSG;
    /** 文字列がヌル時*/
    if(formatContent.isEmpty()){
      return null;
    }
    /** 変換とき、異常がある、データを処理する。*/
    try {
      Double.parseDouble(formatContent);
      formatResult = formatContent;
    } catch (Exception e) {
      return formatResult;
    }
    return formatResult;
  }
  
  /**
   * <pre>
   * ファイルの情報をチェックする。(文字列->短整数)
   * </pre>
   *
   * @param formatContent String
   * @return formatResult String
   */
  private String numberShortFormatCheck(String formatContent) {
    String formatResult = EXCEPTION_MSG;
    /** 文字列がヌル時*/
    if(formatContent.isEmpty()){
      return null;
    }
    /** 変換とき、異常がある、データを処理する。*/
    try {
      Short.parseShort(formatContent);
      formatResult = formatContent;
    } catch (Exception e) {
      return formatResult;
    }
    return formatResult;
  }
  
  /**
   * <pre>
   * 商品ファイルの情報をチェックする(文字列->長整数)。
   * </pre>
   *
   * @param formatContent String
   * @return formatResult Long
   */
  private String numberLongFormatCheck(String formatContent) {
    String formatResult = EXCEPTION_MSG;
    /** 文字列がヌル時*/
    if(formatContent.isEmpty()){
      return null;
    }
    /** 変換とき、異常がある、データを処理する。*/
    try {
      Long.parseLong(formatContent);
      formatResult = formatContent;
    } catch (Exception e) {
      return formatResult;
    }
    return formatResult;
  }
  
  /**
   * <pre>
   * 商品ファイルの情報をチェックする(文字列->Timestamp)。
   * </pre>
   *
   * @param formatContent String
   * @return formatResult Timestamp
   */
  private Timestamp timestampFormatCheck(String formatContent) {
    Timestamp formatResult = null;
    /** 文字列がヌル時*/
    if(formatContent.isEmpty()){
      return null;
    }
    /** 変換とき、異常がある、データを処理する。*/
    try {
      formatResult = Timestamp.valueOf(formatContent.replace('/', '-') + TIME_FORMAT);
    } catch (Exception e) {
      return null;
    }
    return formatResult;
  }
   
  /**
   * <pre>
   * フォーマットのメッセージを生成する。
   * </pre>
   * 
   * @param rowNum Integer
   * @param rowName String
   * @return message String
   */
  private String makeMsg(int rowNum, String rowName, String numberMsg){
    numberMsg = numberMsg.replace("{0}", EMPTY + rowNum);
    numberMsg = numberMsg.replace("{1}", rowName);
    return BLONG_TO + numberMsg;
  }

  /**
   * <pre>
   *  既存BL承認済み情報をゲットする。
   * </pre>
   * @param fileType String
   * @param loginUserCd Integer
   * @return consentNum int[]
   */
  @Override
  public int[] getConsentNum(String fileType, int loginUserCd) {
    int[] consentNum = new int[3];
    if(fileType.equals(GOODS)){
      consentNum = fileSelectService.getConsentGoodsNum(loginUserCd, goodsWorkList);
    }else if (fileType.equals(SET)) {
      consentNum = fileSelectService.getConsentSetNum(loginUserCd, setWorkList);
    }else if (fileType.equals(JOIN)) {
      consentNum = fileSelectService.getConsentJoinNum(loginUserCd, joinWorkList);
    }
    return consentNum;
  }

  /**
   * <pre>
   * 「全体」方式インポートする前、TSVファイル内BL承認済商品、セット品、結合品のチェックメセッジをゲットする。
   * </pre>
   *
   * @return list List
   */
  @Override
  public List<String> getAskMsg() {
    List<String> askMsg = new ArrayList<String>();
    /** 商品*/
    askMsg.add(messageService.messageInfo(BregMessageCodes.Q00201));
    /** セット*/
    askMsg.add(messageService.messageInfo(BregMessageCodes.Q00202));
    /** 結合*/
    askMsg.add(messageService.messageInfo(BregMessageCodes.Q00203));
    return askMsg;
  }
}
