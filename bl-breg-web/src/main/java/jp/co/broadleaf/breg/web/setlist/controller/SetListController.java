//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : chenlong
// 作 成 日       2017/02/08     修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.setlist.controller;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.dto.BlCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.SelectCodeMasterDto;
import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMakerId;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.BlEntryFlgEnum;
import jp.co.broadleaf.breg.common.enums.CheckFlgEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.common.enums.JudgeEnum;
import jp.co.broadleaf.breg.common.enums.LogicDeleteDivEnum;
import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.common.enums.ModeEnum;
import jp.co.broadleaf.breg.common.model.ExportOutModel;
import jp.co.broadleaf.breg.companysetting.service.CompanyInfoMasterCommonService;
import jp.co.broadleaf.breg.goodslist.facade.GoodsMasterMakerFacade;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerInfoDto;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterId;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterMakerIdList;
import jp.co.broadleaf.breg.setlist.facade.SetMasterFacade;
import jp.co.broadleaf.breg.setlist.facade.dto.SetGridDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterInfoDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterSearchDto;
import jp.co.broadleaf.breg.web.checklist.controller.CheckListController;
import jp.co.broadleaf.breg.web.common.controller.ReportOutController;
import jp.co.broadleaf.breg.web.setlist.form.SetMasterGridForm;
import jp.co.broadleaf.breg.web.setlist.form.SetMasterGridFormList;
import jp.co.broadleaf.breg.web.setlist.form.SetMasterSearchForm;
import jp.co.broadleaf.breg.web.setlist.util.DataConvert;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.ListToJson;
import jp.co.broadleaf.common.web.BroadleafSessionKeys;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.Submission;
import jp.co.broadleaf.framework.web.WebResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * セット一覧Controllerクラス.
 * </pre>
 */

@Controller
@RequestMapping("/setlist")
public class SetListController extends BaseController {
 
  /** EMPTY */
  private static final String EMPTY = "";
  
  /** SET_FILENAME */
  private static final String SET_FILENAME = "セット_";
  
  /** ログイン画面 */
  public static final String SET_LIST_URL = "setlist/setlist";
  /** applyCommon画面 */
  public static final String APPLYCOMMON_URL= "/applycommon/applyCommon";
  
  /** TABLE_MAKER */
  private static final String TABLE_MAKER = "maker";
  
  /** TABLE_WORK */
  private static final String TABLE_WORK = "work";

  /** SET_SELECT_DATA */
  private static final String SET_SELECT_DATA="setSelectData";
  
  

  
  /**
   * <pre>
   * 会社情報のService.
   * </pre>
   */
  private CompanyInfoMasterCommonService companyInfoMasterCommonService;
  
  
  /**
   * <pre>
   * 【会社情報のService】を設定する。
   * </pre>
   *
   * @param companyInfoMasterCommonService 【会社情報のService】
   */
  @Resource
  public void setCompanyInfoMasterCommonService(CompanyInfoMasterCommonService companyInfoMasterCommonService) {
    this.companyInfoMasterCommonService = companyInfoMasterCommonService;
  }
 
  /** setマスタ(メーカー)Facade */
  private SetMasterFacade setMasterFacade;

  /**
   * <pre>
   * 【setマスタ(メーカー)Facade】を設定する。
   * </pre>
   *
   * @param setMasterFacade 【setマスタ(メーカー)Facade】
   */
  @Resource
  public void setSetMasterFacade(SetMasterFacade setMasterFacade) {
    this.setMasterFacade = setMasterFacade;
  }


  /**
   * <pre>
   * 初期表示時。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/setlist", method = RequestMethod.GET)
  public ModelAndView setListInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("セット一覧の初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(SET_LIST_URL);
    Map<String, Object> enumMap = new HashMap<String, Object>();
    //画面初期化　searchDto　remove
    request.getSession().removeAttribute("setListSearchCondition");
    enumMap.put("applyCondition", ApplyConditionEnum.values());
    enumMap.put("manageKbn", ManageKbnEnum.values());
    enumMap.put("errorFlg", new ErrorFlgEnum[]{ErrorFlgEnum.Error,ErrorFlgEnum.NoError});
    enumMap.put("deleteFlg", DeleteFlgEnum.values());
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = loginPrincipal.getMakerCode();
    request.getSession().setAttribute("makerCd",makerCode);
    request.getSession().setAttribute("blCodeNameMap",setMasterFacade.getBlCodeNameMap(makerCode));
    request.getSession().setAttribute("goodsCodeNameMap",setMasterFacade.getGoodsCodeNameMap(makerCode));
    request.getSession().setAttribute("selectCodeNameMap",setMasterFacade.getSelectCodeNameMap(makerCode));
    request.getSession().setAttribute("classifyCodeGuideMap",goodsMasterFacade.getClassifyCodeGuideMap(makerCode));
    enumMap.put("blCode", setMasterFacade.searchBlCode(makerCode));
    enumMap.put("selectCode", setMasterFacade.searchSelectCode(makerCode));
    result.addAllObjects(enumMap);
    logger.debug("セット一覧の初期表示処理を終了します。");
    return result;
  }
  /**
   * <pre>
   * セット選択モード back button
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @ResponseBody
  @Submission(multiply = true)
  @RequestMapping(path = "/selectBack", method = RequestMethod.POST)
  public WebResult selectBack(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("セット一覧の初期表示処理を開始します。");
    WebResult result = new WebResult();
    request.getSession().removeAttribute(SET_SELECT_DATA);
    request.getSession().setAttribute("isSelected", Boolean.TRUE);
    logger.debug("セット一覧の初期表示処理を終了します。");
    return result;
  }
  
  /**
   * <pre>
   * 初期表示時 パラメータ初期化。
   * </pre>
   * @param form SetMasterSearchForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @ResponseBody
  @RequestMapping(path = "/init", method = RequestMethod.POST)
  @Submission(multiply = true)
  public WebResult dataInit(@RequestBody SetMasterSearchForm form,HttpServletRequest request, HttpServletResponse response){
    logger.debug("セット一覧のパラメータ初期化処理を開始します。");
    WebResult result = new WebResult();
    Boolean isControl = setMasterFacade.isControl();
    Map<Integer, String> applyConditionMap = new HashMap<>();
    if (form.getMode() == ModeEnum.Error.getValue()
        || form.getMode() == ModeEnum.Reference.getValue()) {
      applyConditionMap.put(ApplyConditionEnum.NoApply.getValue(), ApplyConditionEnum.NoApply.getName());
    } else if (form.getMode() == ModeEnum.Select.getValue()) {
      applyConditionMap.put(ApplyConditionEnum.NoApply.getValue(), ApplyConditionEnum.NoApply.getName());
      applyConditionMap.put(ApplyConditionEnum.Applyagain.getValue(), ApplyConditionEnum.Applyagain.getName());
    }
    
    Map<String, String> initListString = getInitListString(request);
    result.put("blCode", initListString.get("blCode"));
    result.put("selectCode", initListString.get("selectCode"));
    /** MessageMap **/
    HashMap<String, String> messageMap = new HashMap<>();
    setMasterFacade.getMessage(messageMap);
    // messageMap
    result.put("messageMap", messageMap);
    result.put("controlFlag", isControl);
    // 申請状態
    result.put("applyConditionMap", applyConditionMap);
    logger.debug("セット一覧のパラメータ初期化処理を終了します。");
    return result;
  }

 /* *//**
   * <pre>
   * セット選択モード 確定button
   * </pre>
   * @param form SetMasterGridFormList
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */


  /**
   * <pre>
   * 検索
   * </pre>
   * 
   * @param form ClassifyCodeGuideForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @ResponseBody
  @Submission(multiply = true)
  @RequestMapping(path = "/search", method = RequestMethod.POST)
  public WebResult search(@RequestBody SetMasterSearchForm form, HttpServletRequest request,
                          HttpServletResponse response) {
    logger.debug("セット一覧の検索処理を開始します。");
    WebResult result = new WebResult();
    
    int makerCode=(int)request.getSession().getAttribute("makerCd");
    CompanyInfoMasterCommon companyInfo = companyInfoMasterCommonService.getCompanyInfo(makerCode);
    SetMasterSearchDto setMasterSearchDto = new SetMasterSearchDto();
    DataConvert.searchFormFormatToSearchDto(form, setMasterSearchDto);
    int setRows = companyInfo.getSetRows();
    int pageNo = setMasterSearchDto.getPageNo();
    int skipRows=setRows*(pageNo-1);
    setMasterSearchDto.setSkipRows((long) skipRows);
    setMasterSearchDto.setMaxRows((long) companyInfo.getSetRows());
    //論理削除区分=0
    setMasterSearchDto.setLogicalDelDiv(LogicDeleteDivEnum.Valid.getValue());
    //部品メーカーコード
    setMasterSearchDto.setPartsMakerCd(makerCode);
    request.getSession().setAttribute("setListSearchCondition", setMasterSearchDto);
    int fileSelectMode=0;
    ImportKbnEnum fileMode = (ImportKbnEnum)request.getSession().getAttribute("importTypeMenu");
    if(null!=fileMode){
      fileSelectMode=fileMode.getValue();
    }
    //menu mode
    int mode=form.getMode();
    SetMasterInfoDto searchSetMasterInfoDto=null;
    if(mode==ModeEnum.Input.getValue()){
//    検索入力モード
      searchSetMasterInfoDto = setMasterFacade.searchSetMasterInfoDto(setMasterSearchDto, true, form.getTable(), fileSelectMode,  mode);
      result.put("totalNum", setMasterFacade.searchSetMasterInfoDto(form.getTable(),fileSelectMode,makerCode,mode).getSetMasterDtoList().size());
    }else if(mode==ModeEnum.Select.getValue()){
//     選択モード
      HttpSession session = request.getSession();
      SetMasterMakerIdList idList=(SetMasterMakerIdList) session.getAttribute(SET_SELECT_DATA);
      if(null==idList){
        //init the idList
        idList=new SetMasterMakerIdList();
        listInit(idList.getIdList(),makerCode);
        System.err.println(idList);
      }
      //maker db 's  datalist
      SetMasterInfoDto searchSetMasterInfoDto2 = setMasterFacade.searchSetMasterInfoDto(setMasterSearchDto, true, form.getTable(),fileSelectMode,  mode);
      //maker and common db check 's datalist
      searchSetMasterInfoDto=setMasterFacade.searchSetMasterInfoList(searchSetMasterInfoDto2,idList);
//      session.setAttribute(SET_SELECT_DATA, idList);
      
      result.put("totalNum", setMasterFacade.searchSetMasterInfoDto(form.getTable(),fileSelectMode,makerCode,mode).getSetMasterDtoList().size());
    }else if(mode==ModeEnum.Reference.getValue()){
//      参照モード
      searchSetMasterInfoDto = setMasterFacade.searchSetMasterInfoDto(setMasterSearchDto, true, form.getTable(),fileSelectMode,  mode);
      result.put("totalNum", setMasterFacade.searchSetMasterInfoDto(form.getTable(),fileSelectMode,makerCode,mode).getSetMasterDtoList().size());
    }else if(mode==ModeEnum.Error.getValue()){
//      エラー修正モード
      searchSetMasterInfoDto = setMasterFacade.searchSetMasterInfoDto(setMasterSearchDto, true, form.getTable(),fileSelectMode,  mode);
      result.put("totalNum", setMasterFacade.searchSetMasterInfoDto(form.getTable(),fileSelectMode,makerCode,mode).getSetMasterDtoList().size());
    }
    List<SetMasterGridForm> setMasterGridFormList= formatDtoListToGridForm(searchSetMasterInfoDto.getSetMasterDtoList(),request);
     for (SetMasterGridForm setMasterGridForm : setMasterGridFormList) {  
       checkItemWork(setMasterGridForm, makerCode, setMasterGridFormList);   
     }
    String json = listToJson.listToJson(setMasterGridFormList);
    result.put("result", json);
    result.put("thisTimeTotalNum",
        searchSetMasterInfoDto.getSearchCounts());
    result.put("maxRows",
        setRows);
    logger.debug("セット一覧の検索処理を終了します。");
    return result;

  }
  
  /**
   * セット申請機能 セット選択モード 確定button
   * 
   * @param form ProductListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @Submission(multiply = true)
  @ResponseBody
  @RequestMapping(path = "/yes", method = RequestMethod.POST)
  public WebResult onclickBtnCertain(@RequestBody SetMasterGridFormList form, HttpServletRequest request,
                                     HttpServletResponse response) {
    logger.debug("セット一覧のセット申請機能 処理を開始します。");
    WebResult result = new WebResult();
    HttpSession session = request.getSession();
    int makerCode=(int)session.getAttribute("makerCd");
    SetMasterMakerIdList idList=(SetMasterMakerIdList) session.getAttribute(SET_SELECT_DATA);
    if(null==idList){
      //init the idList
      idList=new SetMasterMakerIdList();
      listInit(idList.getIdList(),makerCode);
      System.err.println(idList);
    }
    System.err.println(idList);
    List<SetMasterId> checkedList=new LinkedList<SetMasterId>();
    List<SetMasterId> uncheckedList=new LinkedList<SetMasterId>();
    try {
      for (SetMasterGridForm setMasterGridForm : form.getSetMasterGridFormList()) {
        SetMasterDto setMasterDto = new SetMasterDto();
        DataConvert.gridFromFormatTodto(setMasterDto, setMasterGridForm);
        SetMasterId id=new SetMasterId();
        id.setPrmSetDtlNo1(setMasterDto.getPrmSetDtlNo1());
        id.setPartsMakerCd(setMasterDto.getPartsMakerCd());
        id.setSetMainPartsNo(setMasterDto.getSetMainPartsNo());
        id.setSetDispOrder(setMasterDto.getSetDispOrder());
        if(setMasterDto.getCheck()){
          checkedList.add(id);
        }else{
          uncheckedList.add(id);
        }
      }
      checkedMapulation(checkedList, idList);
      uncheckedMapulation(uncheckedList, idList);
      setMasterFacade.manegeSelectMakerInSession(idList.getIdList());
      session.setAttribute("isSelected", Boolean.TRUE);
      session.setAttribute(SET_SELECT_DATA, idList);
      // goodsMasterMakerInfoDto.setGoodsMasterDto(goodsMasterDtoList);
      result.put("selectSize", setMasterFacade.searchSelectBySelectKbn(makerCode));
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("セット一覧のセット申請機能 処理を終了します。");
    return result;
  }
  
  /**
   * セット申請機能 セット選択モード 改ページ
   * 
   * @param form ProductListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @Submission(multiply = true)
  @ResponseBody
  @RequestMapping(path = "/doSelectPage", method = RequestMethod.POST)
  public WebResult doSelectPage(@RequestBody SetMasterGridFormList form, HttpServletRequest request,
                                     HttpServletResponse response) {
    logger.debug("セット一覧のセット申請機能 処理を開始します。");
    WebResult result = new WebResult();
    HttpSession session = request.getSession();
    int makerCode=(int)session.getAttribute("makerCd");
    SetMasterMakerIdList idList=(SetMasterMakerIdList) session.getAttribute(SET_SELECT_DATA);
    if(null==idList){
      //init the idList
      idList=new SetMasterMakerIdList();
      listInit(idList.getIdList(),makerCode);
      System.err.println(idList);
    }
    System.err.println(idList);
    List<SetMasterId> checkedList=new LinkedList<SetMasterId>();
    List<SetMasterId> uncheckedList=new LinkedList<SetMasterId>();
    try {
      for (SetMasterGridForm setMasterGridForm : form.getSetMasterGridFormList()) {
        SetMasterDto setMasterDto = new SetMasterDto();
        DataConvert.gridFromFormatTodto(setMasterDto, setMasterGridForm);
        SetMasterId id=new SetMasterId();
        id.setPrmSetDtlNo1(setMasterDto.getPrmSetDtlNo1());
        id.setPartsMakerCd(setMasterDto.getPartsMakerCd());
        id.setSetMainPartsNo(setMasterDto.getSetMainPartsNo());
        id.setSetDispOrder(setMasterDto.getSetDispOrder());
        if(setMasterDto.getCheck()){
          checkedList.add(id);
        }else{
          uncheckedList.add(id);
        }
      }
      checkedMapulation(checkedList, idList);
      uncheckedMapulation(uncheckedList, idList);
      session.setAttribute(SET_SELECT_DATA, idList);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("セット一覧のセット申請機能 処理を終了します。");
    return result;
  }
  
  /**
   * セット申請機能 セット選択モード checkedList tobe added
   * @param checkedList List<SetMasterMakerId>
   * @param idList SetMasterMakerIdList
   */
  private void checkedMapulation(List<SetMasterId> checkedList,SetMasterMakerIdList idList){
    List<SetMasterId> setMasterIdList = idList.getIdList();
    List<SetMasterId> tobeadded = new LinkedList<SetMasterId>();
    Boolean flag;
    for (SetMasterId setMasterId : checkedList) {
      flag=false;
      for (SetMasterId checkedId : setMasterIdList) {
        if(setMasterId.isEqualsId(checkedId)){
          flag=true;
          break;
        }
      }
      if(!flag){
        tobeadded.add(setMasterId);
      }
    }
    for (SetMasterId setMasterId : tobeadded) {
      addSetMasterIdtoList(setMasterId,idList.getIdList());
    }
  }
  /**
   * セット申請機能 セット選択モード uncheckedList tobe deleted
   * @param uncheckedList List<SetMasterMakerId>
   * @param idList SetMasterMakerIdList
   */
  private void uncheckedMapulation(List<SetMasterId> uncheckedList,SetMasterMakerIdList idList){
    List<SetMasterId> setMasterIdList = idList.getIdList();
    List<SetMasterId> tobedeleted = new LinkedList<SetMasterId>();
    Boolean flag;
    for (SetMasterId checkedId : setMasterIdList) {
      flag=false;
      for (SetMasterId setMasterId : uncheckedList) {
        if(setMasterId.isEqualsId(checkedId)){
          flag=true;
          break;
        }
      }
      if(flag){
        tobedeleted.add(checkedId);
      }
    }
    for (SetMasterId setMasterId : tobedeleted) {
      removeSetMasterIdinList(setMasterId,idList.getIdList());
    }
  }
  
  /**
   * 帳票作成
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @Submission(multiply = true)
  @RequestMapping(path = "/makeFile", method = RequestMethod.GET)
  @ResponseBody
  public WebResult makeFile(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("セット一覧の帳票作成処理を開始します。");
    WebResult result = new WebResult();
    ExportOutModel exportOutModel = null;
    ReportOutController reportOutController = new ReportOutController();
    String fileType = request.getParameter("fileType");
    String fileName = getFileName(fileType);    
 // BLコードリスト取得
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = loginPrincipal.getMakerCode();
    int mode = Integer.parseInt(request.getParameter("mode"));
    int fileSelectMode=0;
    
    ImportKbnEnum fileMode = (ImportKbnEnum)request.getSession().getAttribute("importTypeMenu");
    if(null!=fileMode){
      fileSelectMode=fileMode.getValue();
    }
    SetMasterSearchDto setMasterSearchDto = (SetMasterSearchDto)request.getSession().getAttribute("setListSearchCondition");
    SetMasterInfoDto searchSetMasterInfoDto=new SetMasterInfoDto();
    if(mode==ModeEnum.Input.getValue()){
//    検索入力モード
      searchSetMasterInfoDto = setMasterFacade.searchSetMasterInfoDto(setMasterSearchDto, false, TABLE_MAKER,fileSelectMode,  mode);
    }else if(mode==ModeEnum.Select.getValue()){
//      セット選択モード
      HttpSession session = request.getSession();
      SetMasterMakerIdList idList=(SetMasterMakerIdList) session.getAttribute(SET_SELECT_DATA);
      if(null==idList){
        //init the idList
//        idList=new SetMasterMakerIdList();
//        listInit(idList.getIdList());
      }
      //maker db 's  datalist
      SetMasterInfoDto searchSetMasterInfoDto2 = setMasterFacade.searchSetMasterInfoDto(setMasterSearchDto, false,  TABLE_MAKER,fileSelectMode,  mode);
      //maker and common db check 's datalist
      searchSetMasterInfoDto=setMasterFacade.searchSetMasterInfoList(searchSetMasterInfoDto2,idList);
    }else if(mode==ModeEnum.Reference.getValue()){
//      参照モード
      searchSetMasterInfoDto = setMasterFacade.searchSetMasterInfoDto(setMasterSearchDto, false,  TABLE_MAKER,fileSelectMode,  mode);
    }else if(mode==ModeEnum.Error.getValue()){
//      エラー修正モード
      searchSetMasterInfoDto = setMasterFacade.searchSetMasterInfoDto(setMasterSearchDto, false,  TABLE_WORK,fileSelectMode,  mode);
      
    }
    List<SetMasterDto> outputList = searchSetMasterInfoDto.getSetMasterDtoList();
    List<SetGridDto> dataSourceList = new ArrayList<SetGridDto>();
    Map<String, String> blCodeNameMap = goodsMasterFacade.getBlCodeNameMap(makerCode);
    Map<String, String> selectCodeNameMap = goodsMasterFacade.getSelectCodeNameMap(makerCode);
    Map<String, String> partsNameMap = goodsMasterFacade.getPartsNameMap(makerCode);
      int rowNo=1;
      for(SetMasterDto setMasterDto:outputList){
        dataSourceList.add(convertSetGridDto(setMasterDto, blCodeNameMap, selectCodeNameMap, partsNameMap,rowNo++));
      }
      CheckListController checkListController=new CheckListController();
      exportOutModel = checkListController.makeSetFile(result, request, fileType, fileName, dataSourceList);

    try {
      reportOutController.reportOut(exportOutModel, response, result);
    } catch (Exception e) {
      e.printStackTrace();
    }  
    logger.debug("セット一覧の帳票作成処理を終了します。");
    return result;
  }
  
  /**
   * ファイル名を取得する。
   * 
   * @param fileType 出力ファイルタイプ。
   * @return ファイル名
   */
  private String getFileName(String fileType) {
    String fileName = SET_FILENAME;
    Date date = new Date();
    DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    fileName += format.format(date);
    if (fileType.equals("1")) {
      fileName += ".pdf";
    }
    if (fileType.equals("2")) {
      fileName += ".tsv";
    }
    if (fileType.equals("3")) {
      fileName += ".xls";
    }
    return fileName;
  }
  
  
  /**
   * setgridのdata
   * 
   * @param setMasterDto SetMasterDto
   * @param partsNameMap blCodeNameMap
   * @param selectCodeNameMap selectCodeNameMap
   * @param blCodeNameMap partsNameMap
   * @return setGridDto
   */
  private SetGridDto convertSetGridDto(SetMasterDto setMasterDto, Map<String, String> blCodeNameMap,
                                       Map<String, String> selectCodeNameMap, Map<String, String> partsNameMap,int rowNo) {
    SetGridDto setGridDto = new SetGridDto();
    // No
    setGridDto.setNo(rowNo);
    // エラー区分
    setGridDto.setErrorFlg(ErrorFlgEnum.valueof(setMasterDto.getErrorFlg()) == null ? EMPTY
        : ErrorFlgEnum.valueof(setMasterDto.getErrorFlg()).getAbbreviation());
    // エラー内容
    setGridDto.setErrorDetail(setMasterDto.getErrorDetail());
    // セレクトコード名称
    setGridDto.setPrmSetDtlNo1(setMasterDto.getPrmSetDtlNo1() == 9999 ? "9999：指定無し"
        : selectCodeNameMap.get(String.valueOf(setMasterDto.getPrmSetDtlNo1())));
    // 分類コード名称
    setGridDto
        .setGoodsMGroup(setMasterDto.getGoodsMGroup() == null ? null : String.valueOf(setMasterDto.getGoodsMGroup()));
    // BLコード名称
    setGridDto.setTbsPartsCode(blCodeNameMap.get(String.valueOf(setMasterDto.getTbsPartsCode())));
    // セット親品番
    setGridDto.setSetMainPartsNo(setMasterDto.getSetMainPartsNo());
    // 表示順位
    setGridDto.setSetDispOrder(setMasterDto.getSetDispOrder().toString());
    // セット子品番
    setGridDto.setSetSubPartsNo(setMasterDto.getSetSubPartsNo());
    // 品名（半角）
    setGridDto.setSetKanaName(setMasterDto.getSetKanaName());
    // 品名（全角）
    setGridDto.setSetName(setMasterDto.getSetName());
    // QTY
    setGridDto.setSetQty(setMasterDto.getSetQty());
    // 規格/特記
    setGridDto.setSetSpecialNote(setMasterDto.getSetSpecialNote());
    // 規格/特記（一般）
    setGridDto.setPrimePartsSpecialNoteC(setMasterDto.getPrimePartsSpecialNoteC());
    // 削除依頼区分
    setGridDto.setDeleteFlg(setMasterDto.getDeleteFlg() == null ? EMPTY
        : DeleteFlgEnum.valueof(setMasterDto.getDeleteFlg()).getAbbreviation());
    // 削除理由
    setGridDto.setDeleteReason(setMasterDto.getDeleteReason());
    // 作成日
    setGridDto.setInsDtTime(setMasterDto.getInsDtTime() == null ? EMPTY
        : String.valueOf(setMasterDto.getInsDtTime()).substring(0, 16).replace("-", "/"));
    // 更新日
    setGridDto.setUpdDtTime(setMasterDto.getUpdDtTime() == null ? EMPTY
        : String.valueOf(setMasterDto.getUpdDtTime()).substring(0, 16).replace("-", "/"));
    // 適用日
    setGridDto.setStartTime(setMasterDto.getStartTime() == null ? EMPTY
        : setMasterDto.getStartTime().toString().substring(0, 16).replace("-", "/"));
    // チェック区分
    setGridDto.setCheckFlg(setMasterDto.getCheckFlg() == null ? EMPTY
        : CheckFlgEnum.valueof(setMasterDto.getCheckFlg()).getAbbreviation());
    // BL登録区分
    setGridDto.setBlEntryFlg(setMasterDto.getBlEntryFlg() == null ? EMPTY
        : BlEntryFlgEnum.valueof(setMasterDto.getBlEntryFlg()).getAbbreviation());
    // 申請状態
    setGridDto.setApplyCondition(setMasterDto.getApplyCondition() == null ? EMPTY
        : ApplyConditionEnum.valueof(setMasterDto.getApplyCondition()).getAbbreviation());
    // 処理区分
    setGridDto.setManageKbn(setMasterDto.getManageKbn() == null ? EMPTY
        : ManageKbnEnum.valueof(setMasterDto.getManageKbn()).getAbbreviation());
    return setGridDto;
  }
  
  /** 商品マスタ(メーカー)Facade */
  private GoodsMasterMakerFacade goodsMasterFacade;
  
  /**
   * <pre>
   * 商品マスタFacadeを設定する。
   * </pre>
   * 
   * @param goodsMasterFacade 商品マスタFacade
   */
  @Resource
  public void setGoodsMasterWorkFacade(GoodsMasterMakerFacade goodsMasterFacade) {
    this.goodsMasterFacade = goodsMasterFacade;
  }
  
  /**
   * <pre>
   * save
   * </pre>
   * 
   * @param form ClassifyCodeGuideForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @ResponseBody
  @RequestMapping(path = "/save", method = RequestMethod.POST)
  public WebResult save(@RequestBody SetMasterGridFormList form, HttpServletRequest request,
                          HttpServletResponse response){
    logger.debug("セット一覧の保存処理を開始します。");
    WebResult result=new WebResult();
    List<SetMasterGridForm> setMasterGridFormList = form.getSetMasterGridFormList();
    SetMasterInfoDto setMasterInfoDto = new SetMasterInfoDto();
    ArrayList<SetMasterDto> insertList = new ArrayList<SetMasterDto>();
    ArrayList<SetMasterDto> deleteList = new ArrayList<SetMasterDto>();
    ArrayList<SetMasterDto> updataList = new ArrayList<SetMasterDto>();
    ArrayList<SetMasterDto> unchangedList = new ArrayList<SetMasterDto>();
    int itemErrorCount = 0;
    int makerCode=(int)request.getSession().getAttribute("makerCd");
    for (SetMasterGridForm setMasterGridForm : setMasterGridFormList) {  
      if (checkItemWork(setMasterGridForm, makerCode, setMasterGridFormList)) {
        itemErrorCount += 1;
      }
    }
    boolean  isErrorExist = itemErrorCount != 0;
    
    if(!isErrorExist){
      for (SetMasterGridForm setMasterGridForm : setMasterGridFormList) {
            if(setMasterGridForm.getHiddenArea().intValue() == JudgeEnum.Add.getValue()){
              SetMasterDto dto = new SetMasterDto();
              DataConvert.gridFromFormatTodto(dto, setMasterGridForm);
              dto.setLogicalDelDiv(LogicDeleteDivEnum.Valid.getValue());
              dto.setPartsMakerCd(makerCode);
              dto.setImportKbn((short) ImportKbnEnum.ManualInput.getValue());
              //insertList
              insertList.add(dto);
            }else if(setMasterGridForm.getHiddenArea().intValue() == JudgeEnum.Delete.getValue()){
              SetMasterDto dto = new SetMasterDto();
              DataConvert.gridFromFormatTodto(dto, setMasterGridForm);
              //deleteList
              deleteList.add(dto);
            }else if(setMasterGridForm.getHiddenArea().intValue() == JudgeEnum.Update.getValue()){
              SetMasterDto dto = new SetMasterDto();
              DataConvert.gridFromFormatTodto(dto, setMasterGridForm);
              //doupdate
              updataList.add(dto);
            }else{
              SetMasterDto dto = new SetMasterDto();
              DataConvert.gridFromFormatTodto(dto, setMasterGridForm);
              unchangedList.add(dto);
            }
        }
      if("work".equals(form.getTable())){
        int fileSelectMode=0;
        ImportKbnEnum fileMode = (ImportKbnEnum)request.getSession().getAttribute("importTypeMenu");
        if(null!=fileMode){
          fileSelectMode=fileMode.getValue();
        }
        SetMasterSearchDto setMasterSearchDto = (SetMasterSearchDto)request.getSession()
            .getAttribute("setListSearchCondition");
        SetMasterInfoDto searchSetMasterInfoDto=new SetMasterInfoDto();
        searchSetMasterInfoDto = setMasterFacade.searchSetMasterInfoDto(setMasterSearchDto, false,  
            TABLE_WORK,fileSelectMode,  ModeEnum.Error.getValue());
        List<SetMasterDto> setMasterDtoList = searchSetMasterInfoDto.getSetMasterDtoList();
        List<SetMasterDto> updateErrorList=new ArrayList<>();
        for(SetMasterDto setMasterDto:updataList){
          SetMasterWorkMakerId setMasterMakerId = new SetMasterWorkMakerId(setMasterDto.getPrmSetDtlNo1(),
              setMasterDto.getPartsMakerCd(),setMasterDto.getSetMainPartsNo(),setMasterDto.getSetDispOrder());
          List<SetMasterDto> setMasterDtos=setMasterFacade.searchSetMasterWorkById(setMasterMakerId);
          if(setMasterDtos.isEmpty()){
            insertList.add(setMasterDto);
          }else{
            updateErrorList.add(setMasterDtos.get(0));
          }   
        }
        removeRepeat(setMasterDtoList,updateErrorList);
        removeRepeat(setMasterDtoList,unchangedList);
//        setMasterDtoList.removeAll(updateErrorList);
        deleteList.addAll(setMasterDtoList);
        for(SetMasterDto setMasterDto:updateErrorList){
          setMasterFacade.updateSetMaster(setMasterDto, form.getTable());
        }
      }else{
        for(SetMasterDto dto:updataList){
          setMasterFacade.updateSetMaster(dto, form.getTable());
        }
      }
      if(insertList.size()>0){
          setMasterInfoDto.setSetMasterDtoList(insertList);
          //doinsert
          setMasterFacade.insertSetMasterInfoDto(setMasterInfoDto, form.getTable());
        }
        if(deleteList.size()>0){
          setMasterInfoDto.setSetMasterDtoList(deleteList);
          //dodelete
          setMasterFacade.deleteSetMaster(form.getTable(), setMasterInfoDto);
        }
    }
    result.put("gridDataUpdate", setMasterGridFormList);
    result.put("isErrorExist", isErrorExist);
    logger.debug("セット一覧の保存処理を終了します。");
    return result;
  }
  
  /**
   * セットcheck
   * 
   * @param form SetMasterGridFormList
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/check", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = true)
  public WebResult check(@RequestBody SetMasterGridFormList form, HttpServletRequest request, HttpServletResponse response) {
    logger.debug("セット一覧のセットチェック処理を開始します。");
    WebResult result=new WebResult();
//    List<SetMasterGridForm> setMasterGridFormList = form.getSetMasterGridFormList();
    try {
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      int makerCode = loginPrincipal.getMakerCode();
      // 一覧テータ
      List<SetMasterGridForm> setMasterGridFormList = form.getSetMasterGridFormList();
      for (SetMasterGridForm setMasterGridForm : setMasterGridFormList) {  
        checkItemWork(setMasterGridForm, makerCode, setMasterGridFormList);   
      }
      // 一覧テータ更新
      result.put("gridDataUpdate", setMasterGridFormList);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("セット一覧のセットチェック処理を終了します。");
    return result;
  }
  
  private void removeRepeat(List<SetMasterDto> setMasterDtoList,List<SetMasterDto> updateErrorList){
    for(SetMasterDto updateSetMasterDto:updateErrorList){
      for(SetMasterDto setMasterDto:setMasterDtoList){
        if(updateSetMasterDto.getPrmSetDtlNo1().equals(setMasterDto.getPrmSetDtlNo1())
            && updateSetMasterDto.getPartsMakerCd().equals(setMasterDto.getPartsMakerCd())
            && updateSetMasterDto.getSetDispOrder().equals(setMasterDto.getSetDispOrder())
            && updateSetMasterDto.getSetMainPartsNo().equals(setMasterDto.getSetMainPartsNo())){
          setMasterDtoList.remove(setMasterDto);
          break;
        }
      }
      continue;
    }
  }
  
 

  
  /** 優良設定詳細コード１ **/
  public static final String PRM_SET_DTL_NO_1 = "セレクトコード名称";
  /** 商品中分類コード **/
  private static final String GOODS_M_GROUP = "分類コード名称";
  /** BLコード **/
  private static final String TBS_PARTS_CODE = "BLコード名称";
  /** セット品名（全角）**/
  private static final String PRIME_PARTS_NAME = "セット品名（全角）";
  /** セット品名（半角） **/
  private static final String PRIME_PARTS_KANA_NAME = "セット品名（半角）";
  /** セット子品番 **/
  private static final String SET_SUB_PARTS_NO = "セット子品番";
  /** QTY **/
  private static final String SET_QTY = "QTY";
  /** セット親品番 **/
  private static final String SET_MAIN_PARTS_NO = "セット親品番";
  /** 表示順位 **/
  private static final String SET_DISP_ORDER = "表示順位";
  /** 適用日時 **/
  public static final String START_TIME = "適用日時";
  /** 削除理由 **/
  public static final String DELETE_REASON = "削除理由";
  
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
   * セットマスタ(メーカー)案のチェック.
   * 
   * @param setMasterGridForm セット一覧
   * @param makerCd メーカーコード
   * @param setMasterGridFormList セットマスタ
   * @return 「True」：エラーあり、「False」：エラーなし
   */
  private boolean checkItemWork(SetMasterGridForm setMasterGridForm, int makerCd, List<SetMasterGridForm> setMasterGridFormList) {
    // 削除テータ チェック無し
    if (setMasterGridForm.getHiddenArea() != null && setMasterGridForm.getHiddenArea().intValue() == JudgeEnum.Delete.getValue()) {
      return false;
    }
    // check
    StringBuffer stringBuffer = new StringBuffer();
    // 必須項目チェック セレクトコード名称
    if (setMasterGridForm.getPrmSetDtlNo1() == null || setMasterGridForm.getPrmSetDtlNo1().isEmpty()) {
      stringBuffer.append(messageService.getMessage(PRM_SET_DTL_NO_1, "", BregMessageCodes.E00001));
    }
    // 必須項目チェック セット親品番
    if (setMasterGridForm.getSetMainPartsNo() == null || setMasterGridForm.getSetMainPartsNo().isEmpty()) {
      stringBuffer.append(messageService.getMessage(SET_MAIN_PARTS_NO, "", BregMessageCodes.E00001));
    }
    // 必須項目チェック 表示順位
    if (setMasterGridForm.getSetDispOrder() == null || setMasterGridForm.getSetDispOrder().isEmpty()) {
      stringBuffer.append(messageService.getMessage(SET_DISP_ORDER, "", BregMessageCodes.E00001));
    }
    //// 未入力項目チェック 商品中分類コード
    if (setMasterGridForm.getGoodsMGroup()== null || setMasterGridForm.getGoodsMGroup().isEmpty()) {
      stringBuffer.append(messageService.getMessage(GOODS_M_GROUP, "", BregMessageCodes.E00002));
    }
    // 未入力項目チェック BLコード
    if (setMasterGridForm.getTbsPartsCode() == null || setMasterGridForm.getTbsPartsCode().isEmpty()) {
      stringBuffer.append(messageService.getMessage(TBS_PARTS_CODE, "", BregMessageCodes.E00002));
    }
    // 未入力項目チェック 優良部品名称
    if (setMasterGridForm.getSetName() == null || setMasterGridForm.getSetName().isEmpty()) {
      stringBuffer.append(messageService.getMessage(PRIME_PARTS_NAME, "", BregMessageCodes.E00002));
    }
    // 未入力項目チェック 優良部品カナ名称
    if (setMasterGridForm.getSetKanaName() == null || setMasterGridForm.getSetKanaName().isEmpty()) {
      stringBuffer.append(messageService.getMessage(PRIME_PARTS_KANA_NAME, "", BregMessageCodes.E00002));
    }
    // 未入力項目チェック 子品番
    if (setMasterGridForm.getSetSubPartsNo() == null || setMasterGridForm.getSetSubPartsNo().isEmpty()) {
      stringBuffer.append(messageService.getMessage(SET_SUB_PARTS_NO, "", BregMessageCodes.E00002));
    }
    // 未入力項目チェック QTY
    if (setMasterGridForm.getSetQty() == null || setMasterGridForm.getSetQty().isEmpty()) {
      stringBuffer.append(messageService.getMessage(SET_QTY, "", BregMessageCodes.E00002));
    }
    // 未入力項目チェック 適用日時
    if (setMasterGridForm.getStartTime() == null || setMasterGridForm.getStartTime().isEmpty()) {
      stringBuffer.append(messageService.getMessage(START_TIME, "", BregMessageCodes.E00002));
    }
    // 未入力項目チェック 削除理由
    if (setMasterGridForm.getDeleteFlg() != null && !setMasterGridForm.getDeleteFlg().isEmpty()
        && (setMasterGridForm.getDeleteReason() == null || setMasterGridForm.getDeleteReason().isEmpty())) {
      stringBuffer.append(messageService.getMessage(DELETE_REASON, "", BregMessageCodes.E00002));
    }
    checkItemWork1(setMasterGridForm, makerCd, stringBuffer, setMasterGridFormList);
    boolean errorExistFlag = false;
    // エラーがありません
    if (stringBuffer == null || stringBuffer.length() == 0) {
      // エラー区分の設定
      setMasterGridForm.setErrorFlg(ErrorFlgEnum.NoError.getAbbreviation());
      // エラー内容クリーン
      setMasterGridForm.setErrorDetail("");
      // エラー有無Flag
      errorExistFlag = false;
    } else {
      // エラー内容の設定
      setMasterGridForm.setErrorDetail(stringBuffer.toString());
      // エラー区分の設定
      setMasterGridForm.setErrorFlg(ErrorFlgEnum.Error.getAbbreviation());
      errorExistFlag = true;
    }
    
    // チェック区分の設定。
    setMasterGridForm.setCheckFlg(CheckFlgEnum.Checked.getAbbreviation());
    return errorExistFlag;
  }
  
  /**
   * セットマスタ(メーカー)案のチェック.
   * 
   * @param setMasterGridForm セット一覧
   * @param makerCd メーカーコード
   * @param setMasterGridFormList セットマスタ
   * @param stringBuffer stringBuffer
   */
  private void checkItemWork1(SetMasterGridForm setMasterGridForm, int makerCd, StringBuffer stringBuffer,
                              List<SetMasterGridForm> setMasterGridFormList) {
    
    if (setMasterGridForm.getPrmSetDtlNo1() != null && !setMasterGridForm.getPrmSetDtlNo1().isEmpty() && setMasterGridForm.getSetMainPartsNo() != null
        && !setMasterGridForm.getSetMainPartsNo().isEmpty()) {
//     GoodsMasterMakerId goodsMasterMakerId =new GoodsMasterMakerId(Integer.valueOf(DataConvert.getCode(setMasterGridForm.getPrmSetDtlNo1())),
//         makerCd,DataConvert.getCode(setMasterGridForm.getSetMainPartsNo()));
     GoodsMasterMakerInfoDto goodsMasterMakerInfoDto = goodsMasterFacade.searchById(Integer.valueOf(DataConvert.getCode(setMasterGridForm.getPrmSetDtlNo1())),
         makerCd,DataConvert.getCode(setMasterGridForm.getSetMainPartsNo()));
     if(goodsMasterMakerInfoDto.getGoodsMasterDto().size() == 0){
       stringBuffer.append(messageService.getMessage("親", "", BregMessageCodes.E00501));
     }else if(goodsMasterMakerInfoDto.getGoodsMasterDto().get(0).getApplyCondition() != (short)ApplyConditionEnum.Approval.getValue()){
       stringBuffer.append(messageService.getMessage("親", DataConvert.getCode(setMasterGridForm.getSetMainPartsNo()), BregMessageCodes.E00502)
           .replace("$3", ApplyConditionEnum.valueof(goodsMasterMakerInfoDto.getGoodsMasterDto().get(0).getApplyCondition()).getName()));
     } 
    } 
    if (setMasterGridForm.getPrmSetDtlNo1() != null && !setMasterGridForm.getPrmSetDtlNo1().isEmpty() && setMasterGridForm.getSetSubPartsNo() != null
        && !setMasterGridForm.getSetSubPartsNo().isEmpty()) {
//     GoodsMasterMakerId goodsMasterMakerId =new GoodsMasterMakerId(Integer.valueOf(DataConvert.getCode(setMasterGridForm.getPrmSetDtlNo1())),
//         makerCd,DataConvert.getCode(setMasterGridForm.getSetSubPartsNo()));
     GoodsMasterMakerInfoDto goodsMasterMakerInfoDto = goodsMasterFacade.searchById(Integer.valueOf(DataConvert.getCode(setMasterGridForm.getPrmSetDtlNo1())),
         makerCd,DataConvert.getCode(setMasterGridForm.getSetSubPartsNo()));
     if(goodsMasterMakerInfoDto.getGoodsMasterDto().size() == 0){
       stringBuffer.append(messageService.getMessage("子", "", BregMessageCodes.E00501));
     }else if(goodsMasterMakerInfoDto.getGoodsMasterDto().get(0).getApplyCondition() != (short)ApplyConditionEnum.Approval.getValue()){
       stringBuffer.append(messageService.getMessage("子", DataConvert.getCode(setMasterGridForm.getSetSubPartsNo()), BregMessageCodes.E00502)
           .replace("$3", ApplyConditionEnum.valueof(goodsMasterMakerInfoDto.getGoodsMasterDto().get(0).getApplyCondition()).getName()));
     } 
    } 
    // 優良品番重複チェック 新規登録場合
    if (setMasterGridForm.getPrmSetDtlNo1() != null && !setMasterGridForm.getPrmSetDtlNo1().isEmpty() && setMasterGridForm.getSetMainPartsNo() != null
        && !setMasterGridForm.getSetMainPartsNo().isEmpty() && setMasterGridForm.getSetDispOrder() != null && !setMasterGridForm.getSetDispOrder().isEmpty()    
        && setMasterGridForm.getHiddenArea() != null && setMasterGridForm.getHiddenArea().intValue() == JudgeEnum.Add.getValue()) {
      SetMasterMakerId setMasterMakerId = new SetMasterMakerId(Integer.valueOf(DataConvert.getCode(setMasterGridForm.getPrmSetDtlNo1())),
          makerCd,DataConvert.getCode(setMasterGridForm.getSetMainPartsNo()),Integer.valueOf(setMasterGridForm.getSetDispOrder()));
      if (setMasterFacade.searchById(setMasterMakerId) != null
          || isKeyRepeat(setMasterGridForm, setMasterGridFormList)) {
        stringBuffer.append(messageService.messageInfo(BregMessageCodes.E00503));
      }
    }
    if(setMasterGridForm.getSetMainPartsNo() != null && setMasterGridForm.getSetSubPartsNo() != null 
        && DataConvert.getCode(setMasterGridForm.getSetMainPartsNo()).equals(DataConvert.getCode(setMasterGridForm.getSetSubPartsNo()))){
        stringBuffer.append(messageService.messageInfo(BregMessageCodes.E00504));
    }
    // 文字桁数チェック 優良部品名称
    if (setMasterGridForm.getSetName() != null && setMasterGridForm.getSetName().length() > 60) {
      stringBuffer.append(messageService.getMessage(PRIME_PARTS_NAME, "60", BregMessageCodes.E00003));
    }
    // 文字桁数チェック 優良品番
    if (setMasterGridForm.getSetMainPartsNo() != null && DataConvert.getCode(setMasterGridForm.getSetMainPartsNo()).getBytes().length > 24) {
      stringBuffer.append(messageService.getMessage(SET_MAIN_PARTS_NO, "24", BregMessageCodes.E00003));
    }
    // 文字桁数チェック 優良品番
    if (setMasterGridForm.getSetSubPartsNo() != null && DataConvert.getCode(setMasterGridForm.getSetSubPartsNo()).getBytes().length > 24) {
      stringBuffer.append(messageService.getMessage(SET_SUB_PARTS_NO, "24", BregMessageCodes.E00003));
    }
    // 文字桁数チェック 優良部品カナ名称
    if (setMasterGridForm.getSetKanaName() != null && setMasterGridForm.getSetKanaName().getBytes().length > 60) {
      stringBuffer.append(messageService.getMessage(PRIME_PARTS_KANA_NAME, "60", BregMessageCodes.E00003));
    }
  }
  
  /**
   * 優良品番重複チェック
   * 
   * @param setMasterGridForm セットマスタ
   * @param setMasterGridFormList セットマスタ
   * @return boolean
   */
  private boolean isKeyRepeat(SetMasterGridForm setMasterGridForm, List<SetMasterGridForm> setMasterGridFormList) {
    Boolean isKeyRepeat = false;
    int index = 0;
    for (SetMasterGridForm setMasterGridFormOld : setMasterGridFormList) {
      if (setMasterGridFormOld.getHiddenArea().intValue() == JudgeEnum.Add.getValue() && setMasterGridFormOld.getPrmSetDtlNo1() != null
          && !setMasterGridFormOld.getPrmSetDtlNo1().isEmpty() && setMasterGridFormOld.getSetMainPartsNo() != null
          && !setMasterGridFormOld.getSetMainPartsNo().isEmpty() && setMasterGridFormOld.getSetDispOrder() != null
          && !setMasterGridFormOld.getSetDispOrder().isEmpty() && setMasterGridForm.getPrmSetDtlNo1() != null
          && !setMasterGridForm.getPrmSetDtlNo1().isEmpty() && setMasterGridForm.getSetMainPartsNo() != null
          && !setMasterGridForm.getSetMainPartsNo().isEmpty() && setMasterGridForm.getSetDispOrder() != null
          && !setMasterGridForm.getSetDispOrder().isEmpty() && setMasterGridForm.getPrmSetDtlNo1().equals(setMasterGridForm.getPrmSetDtlNo1())
          && setMasterGridFormOld.getSetMainPartsNo().equals(setMasterGridForm.getSetMainPartsNo())
          && setMasterGridFormOld.getSetDispOrder().equals(setMasterGridForm.getSetDispOrder())) {
        index = index + 1;
      }
    }
    if (index > 1) {
      isKeyRepeat = true;
    }
    return isKeyRepeat;
  }
  

  /**
   * <pre>
   * goodsMGroup
   * </pre>
   * 
   * @param form ClassifyCodeGuideForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @ResponseBody
  @Submission(multiply = false)
  @RequestMapping(path = "/goodsMGroup", method = RequestMethod.POST)
  public WebResult goodsMGroup(@RequestBody SetMasterSearchForm form, HttpServletRequest request,
                               HttpServletResponse response) {
    logger.debug("セット一覧の商品中分類コードガイドモード設定処理を開始します。");
    WebResult result = new WebResult();
    HttpSession session = request.getSession();
    session.setAttribute(BroadleafSessionKeys.CLASSIFY_CODE_GUIDE, form.getGoodsMGroup());
    logger.debug("セット一覧の商品中分類コードガイドモード設定処理を終了します。");
    return result;
  }
  /**
   * <pre>
   * 品番 setSubPartsNo setMainPartsNo
   * </pre>
   * 
   * @param form ClassifyCodeGuideForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @ResponseBody
  @Submission(multiply = false)
  @RequestMapping(path = "/setPartsNo", method = RequestMethod.POST)
  public WebResult setPartsNo(@RequestBody SetMasterSearchForm form, HttpServletRequest request,
                               HttpServletResponse response) {
    logger.debug("セット一覧のセット品番設定処理を開始します。");
    WebResult result = new WebResult();
    HttpSession session = request.getSession();
    session.setAttribute(BroadleafSessionKeys.GOODS_GUIDE, form.getSetSubPartsNo());
    session.setAttribute(BroadleafSessionKeys.GOODS_CATEGORY, "maker");
    logger.debug("セット一覧のセット品番設定処理を終了します。");
    return result;
  }

  

  
  /**
   * <pre>
   * 親品番チェック
   * </pre>
   * 
   * @param setMasterSearchDto チェックするデータの連係情報
   * @return 親商品登録区分
   */
  public short checkMainGoods(SetMasterSearchDto setMasterSearchDto) {
    return setMasterFacade.searchMainGoodsForCheck(setMasterSearchDto).getBlEntryFlg();
  }
  
  /**
   * <pre>
   * 子品番チェック
   * </pre>
   * 
   * @param setMasterSearchDto チェックするデータの連係情報
   * @return 子商品登録区分
   */
  public short checkSubGoods(SetMasterSearchDto setMasterSearchDto) {
    return setMasterFacade.searchSubGoodsForCheck(setMasterSearchDto).getBlEntryFlg();
  }
  
  /**
   * <pre>
   * セットマスタ(メーカー)キーのチェック（登録前チェック）.
   * </pre>
   * @param setMasterSearchDto チェックするデータの連係情報
   * @return チェック結果
   */
  public boolean checkIdBeforeInsert(SetMasterSearchDto setMasterSearchDto){
    return setMasterFacade.checkIdBeforeInsert(setMasterSearchDto);
  }

  /**
   * <pre>
   * search blCodeList and selectCodelist
   * </pre>
   * 
   * @return Map
   */
  private Map<String, String> getInitListString(HttpServletRequest request) {
    HashMap<String, String> hashMap = new HashMap<String, String>();
    int makerCode=(int)request.getSession().getAttribute("makerCd");
    String blcode = new ListToJson<BlCodeMasterDto>().listToJson(setMasterFacade.searchBlCode(makerCode));
    String selectCode = new ListToJson<SelectCodeMasterDto>().listToJson(setMasterFacade.searchSelectCode(makerCode));
    hashMap.put("blCode", blcode);
    hashMap.put("selectCode", selectCode);
    return hashMap;
  }

  /**
   * <pre>
   * dto format to grid
   * </pre>
   * 
   * @param dtolist List
   * @return List
   */
  @SuppressWarnings("unchecked")
  private List<SetMasterGridForm> formatDtoListToGridForm(List<SetMasterDto> dtolist,HttpServletRequest request) {
    List<SetMasterGridForm> gridFormList = new ArrayList<SetMasterGridForm>();
    
    Map<String, String> blCodeNameMap = (Map<String, String>)request.getSession().getAttribute("blCodeNameMap");
    Map<String, String> goodsCodeNameMap = (Map<String, String>)request.getSession().getAttribute("goodsCodeNameMap");
    Map<String, String> selectCodeNameMap = (Map<String, String>)request.getSession().getAttribute("selectCodeNameMap");
    Map<String, String> classifyCodeGuideMap =  (Map<String, String>)request.getSession().getAttribute("classifyCodeGuideMap");
    for (SetMasterDto setMasterDto : dtolist) {
      SetMasterGridForm gridForm = new SetMasterGridForm();
      DataConvert.dtoFormatToGridForm(setMasterDto, gridForm,blCodeNameMap,selectCodeNameMap,goodsCodeNameMap,classifyCodeGuideMap);
      gridFormList.add(gridForm);
    }
    return gridFormList;
  }

  /** override the class of ListToJosn */
  private ListToJson<SetMasterGridForm> listToJson = new ListToJson<SetMasterGridForm>() {
    public String listToJson(List<SetMasterGridForm> list) {
      StringBuffer jsonStr = new StringBuffer();
      if (null == list || 0 == list.size()) {
        return null;
      }
      Class<?> classType = list.get(0).getClass();
      jsonStr.append("{\"").append("result").append("\":[");
      Field[] fields = classType.getDeclaredFields();
      for (int i = 0; i < list.size(); i++) {
        jsonStr.append("{");
        for (Field field : fields) {
          String fieldName = field.getName();
          field.setAccessible(true);
          Object fieldValue;
          try {
            fieldValue = field.get(list.get(i));
            jsonStr.append("\"").append(fieldName).append("\":").append("\"").append(fieldValue).append("\"")
                .append(",");
          } catch (IllegalArgumentException e) {
            e.printStackTrace();
          } catch (IllegalAccessException e) {
            e.printStackTrace();
          }
        }
        jsonStr.deleteCharAt(jsonStr.length() - 1);
        jsonStr.append("},");
      }
      jsonStr.deleteCharAt(jsonStr.length() - 1);
      jsonStr.append("]}");
      return jsonStr.toString();
    }
  };

  /**
   * <pre>
   * 【idList】を設定する。
   * </pre>
   * @param partsMakerCd int
   * @param idList LinkedList<SetMasterMakerId>
   */
  private void listInit(List<SetMasterId> idList,int partsMakerCd) {
    List<SelectMaker> entityList = setMasterFacade.findAll(partsMakerCd);
    for (SelectMaker selectMaker : entityList) {
      SetMasterId id=new SetMasterId();
      id.setPartsMakerCd(selectMaker.getSPartsMakerCd());
      id.setPrmSetDtlNo1(selectMaker.getSPrmSetDtlNo1());
      id.setSetMainPartsNo(selectMaker.getSSetMainPartsNo());
      id.setSetDispOrder(selectMaker.getSSetDispOrder());
       addSetMasterIdtoList(id,idList);
    }
  }
  
  /**
   * <pre>
   * 【idList】を設定する。
   * </pre>
   * @param id SetMasterId
   * @param idList LinkedList<SetMasterMakerId>
   */
  private void addSetMasterIdtoList(SetMasterId id,List<SetMasterId> idList){
    //TODO 2017/03/04  [Sorting dichotomy Remove duplicate items] 
    for (SetMasterId setMasterId : idList) {
      if(setMasterId.isEqualsId(id)){
        return;
      }
    }
    idList.add(id);
  }
  /**
   * <pre>
   * 【idList】を設定する。
   * </pre>
   * @param id SetMasterId
   * @param idList LinkedList<SetMasterMakerId>
   */
  private void removeSetMasterIdinList(SetMasterId id,List<SetMasterId> idList){
    //TODO 2017/03/04  [Sorting dichotomy Remove duplicate items] 
    List<SetMasterId> trueDeleteList=new LinkedList<SetMasterId>();
    for (SetMasterId setMasterId : idList) {
      if(setMasterId.isEqualsId(id)){
        trueDeleteList.add(setMasterId);
      }
    }
    idList.removeAll(trueDeleteList);
  }

}
