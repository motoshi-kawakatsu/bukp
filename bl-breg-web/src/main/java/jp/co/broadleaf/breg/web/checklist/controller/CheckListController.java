//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : lulei
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.checklist.controller;

import jp.co.broadleaf.breg.checklist.facade.CheckListFacade;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.BlEntryFlgEnum;
import jp.co.broadleaf.breg.common.enums.CheckFlgEnum;
import jp.co.broadleaf.breg.common.enums.ColumnKindEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.common.enums.JudgeEnum;
import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.common.enums.OpenPriceDivEnum;
import jp.co.broadleaf.breg.common.enums.OutPutTypeEnum;
import jp.co.broadleaf.breg.common.enums.SelectTabEnum;
import jp.co.broadleaf.breg.common.enums.SizeUnitEnum;
import jp.co.broadleaf.breg.common.enums.WeightUnitEnum;
import jp.co.broadleaf.breg.common.model.ExportOutModel;
import jp.co.broadleaf.breg.companysetting.dto.CompanySettingDto;
import jp.co.broadleaf.breg.companysetting.facade.CompanyInfoMasterCommonFacade;
import jp.co.broadleaf.breg.goodslist.facade.GoodsMasterMakerFacade;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsGridDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerInfoDto;
import jp.co.broadleaf.breg.joinlist.facade.JoinMasterFacade;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinGridDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterInfoDto;
import jp.co.broadleaf.breg.message.service.Facade.MessageFacade;
import jp.co.broadleaf.breg.setlist.facade.dto.SetGridDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterInfoDto;
import jp.co.broadleaf.breg.web.common.controller.ReportOutController;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.BroadleafFormatUtils;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.WebResult;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * チェックリスト Controllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/checkList")
public class CheckListController extends BaseController {

  /**
   * <pre>
   * 初期表示時。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/checkList", method = RequestMethod.GET)
  public ModelAndView checkListInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("チェックリストの初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(CHECKLIST);
    logger.debug("チェックリストの初期表示処理を終了します。");
    return result;
  }

  /**
   * 商品grid 初期表示時
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/goodsGrid", method = RequestMethod.GET)
  @ResponseBody
  public WebResult showGoodsGrid(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("チェックリストの商品グリッド初期表示処理を開始します。");
    WebResult result = new WebResult();
    try {
      String count = request.getParameter("count");
      String mode = request.getParameter("mode");
      getGoodsGrid(result, count, mode, request);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("チェックリストの商品グリッド初期表示処理を終了します。");
    return result;
  }

  /**
   * セットgrid 初期表示時
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/setGrid", method = RequestMethod.GET)
  @ResponseBody
  public WebResult showSetGrid(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("チェックリストのセットグリッド初期表示処理を開始します。");
    WebResult result = new WebResult();
    try {
      String count = request.getParameter("count");
      String mode = request.getParameter("mode");
      getSetGrid(result, count, mode, request);

    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("チェックリストのセットグリッド初期表示処理を終了します。");
    return result;
  }

  /**
   * 結合grid 初期表示時
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/joinGrid", method = RequestMethod.GET)
  @ResponseBody
  public WebResult showJoinGrid(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("チェックリストの結合グリッド初期表示処理を開始します。");
    WebResult result = new WebResult();
    try {
      String count = request.getParameter("count");
      String mode = request.getParameter("mode");
      getJoinGrid(result, count, mode, request);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("チェックリストの結合グリッド初期表示処理を終了します。");
    return result;
  }

  /**
   * 帳票作成
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/makeFile", method = RequestMethod.GET)
  @ResponseBody
  public WebResult makeFile(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("チェックリストの帳票作成処理データ画面からを開始します。");
    WebResult result = new WebResult();
    ExportOutModel exportOutModel = null;
    ReportOutController reportOutController = new ReportOutController();
    String showItem = request.getParameter("showItem");
    String fileType = request.getParameter("fileType");
    String fileName = getFileName(showItem, fileType);

    try {
      if (showItem.equals("1")) {
        String mode = request.getParameter("mode");
        List<GoodsGridDto> dataSourceList = getGoodsGrid(result, EMPTY, mode, request);
        exportOutModel = makeGoodsFile(result, request, fileType, fileName, dataSourceList);
      } else if (showItem.equals("2")) {
        String mode = request.getParameter("mode");
        List<SetGridDto> dataSourceList = getSetGrid(result, EMPTY, mode, request);
        exportOutModel = makeSetFile(result, request, fileType, fileName, dataSourceList);
      } else if (showItem.equals("3")) {
        String mode = request.getParameter("mode");
        List<JoinGridDto> dataSourceList = getJoinGrid(result, EMPTY, mode, request);
        exportOutModel = makeJoinFile(result, request, fileType, fileName, dataSourceList);
      }
      reportOutController.reportOut(exportOutModel, response, result);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("チェックリストの帳票作成処理データ画面からを終了します。");
    return result;
  }

  /**
   * 帳票作成
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/makeViewFile", method = RequestMethod.POST)
  @ResponseBody
  public WebResult makeViewFile(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("チェックリストの帳票作成処理データデータベースからを開始します。");
    WebResult result = new WebResult();
    ReportOutController reportOutController = new ReportOutController();
    ExportOutModel exportOutModel = null;
    String showItem = request.getParameter("showItem");
    String viewData = request.getParameter("viewData");
    String fileType = request.getParameter("fileType");
    String fileName = getFileName(showItem, fileType);
    try {
      JSONArray json = JSONArray.fromObject( viewData );  

      if (showItem.equals("1")) {
        @SuppressWarnings("unchecked")
        List<GoodsGridDto> dataSourceList= (List<GoodsGridDto>)JSONArray.toCollection(json, GoodsGridDto.class);
        exportOutModel = makeGoodsFile(result, request, fileType, fileName, dataSourceList);
      } else if (showItem.equals("2")) {
        @SuppressWarnings("unchecked")
        List<SetGridDto> dataSourceList= (List<SetGridDto>)JSONArray.toCollection(json, SetGridDto.class);
        exportOutModel = makeSetFile(result, request, fileType, fileName, dataSourceList);
      } else if (showItem.equals("3")) {
        @SuppressWarnings("unchecked")
        List<JoinGridDto> dataSourceList= (List<JoinGridDto>)JSONArray.toCollection(json, JoinGridDto.class);
        exportOutModel = makeJoinFile(result, request, fileType, fileName, dataSourceList);
      }
      reportOutController.reportOut(exportOutModel, response, result);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("チェックリストの帳票作成処理データデータベースからを終了します。");
    return result;
  }
  
  /** チェックリスト画面 */
  private static final String CHECKLIST = "checkList/checkList";
  /** ITEMMASTER */
  private static final String GOODSMASTER = "GOODSMASTER";
  /** SETMASTER */
  private static final String SETMASTER = "SETMASTER";
  /** JOINMASTER */
  private static final String JOINMASTER = "JOINMASTER";
  /** MAXROWS */
  private static final String MAXROWS = "MAXROWS";
  /** SEARCHCOUNTS */
  private static final String SEARCHCOUNTS = "SEARCHCOUNTS";
  /** EMPTY */
  private static final String EMPTY = "";
  /** チェックリスト取得 */
  private CheckListFacade checkListFacade;
  /** 結合一覧Facade */
  private JoinMasterFacade joinMasterFacade;
  /** エンプロイーFacade */
  private CompanyInfoMasterCommonFacade employFacade;
  /** 商品マスタ(メーカー)Facade */
  private GoodsMasterMakerFacade goodsMasterFacade;
  /** 共通メッセージFacade */
  private MessageFacade messageFacade;

  /**
   * <pre>
   * 共通メッセージ取得を設定する。
   * </pre>
   *
   * @param messageFacade 共通メッセージ取得
   */
  @Resource
  public void setMessageFacade(MessageFacade messageFacade) {
    this.messageFacade = messageFacade;
  }


  /**
   * <pre>
   * エンプロイーFacadeをセットする.
   * </pre>
   * 
   * @param employeeFacade EmployeeFacade
   */
  @Resource
  public void setCompanyInfoMasterCommonFacade(CompanyInfoMasterCommonFacade employeeFacade) {
    this.employFacade = employeeFacade;
  }

  /**
   * <pre>
   * チェックリスト取得を設定する。
   * </pre>
   *
   * @param checkListFacade チェックリスト取得
   */
  @Resource
  public void setCheckListFacade(CheckListFacade checkListFacade) {
    this.checkListFacade = checkListFacade;
  }

  /**
   * <pre>
   * 商品マスタFacadeを設定する。
   * </pre>
   * 
   * @param goodsMasterFacade 商品マスタFacade
   */
  @Resource
  public void setGoodsMasterFacade(GoodsMasterMakerFacade goodsMasterFacade) {
    this.goodsMasterFacade = goodsMasterFacade;
  }


  /**
   * <pre>
   * 結合一覧Facadeを設定する。
   * </pre>
   *
   * @param joinMasterFacade Facade
   */
  @Resource
  public void setJoinMasterFacade(JoinMasterFacade joinMasterFacade) {
    this.joinMasterFacade = joinMasterFacade;
  }

  /**
   * 商品GRID表示データ取得
   * 
   * @param result WebResult
   * @param pagecount pagecount
   * @param mode 画面区分
   * @param request request
   * @return GoodsGridDto GoodsGrid
   */
  private List<GoodsGridDto> getGoodsGrid(WebResult result, String pagecount, String mode, HttpServletRequest request) {

    List<GoodsGridDto> dataSourceList = new ArrayList<GoodsGridDto>();
    GoodsMasterMakerInfoDto gooodsMasterInfo = null;

    // BLコードリスト取得
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    // MessageMap
    Map<String, String> messageMap = getMessageMap(loginPrincipal.getMessageMap());
    result.put("MESSAGE", messageMap);
    int makerCode = loginPrincipal.getMakerCode();
    // 初期会社情報を取得
    CompanySettingDto infoDto = employFacade.getCompanyInfo(makerCode);
    Long maxRows = Long.valueOf(infoDto.getGoodsRows());
    result.put(MAXROWS, infoDto.getGoodsRows());
    Long count;
    if (EMPTY.equals(pagecount)) {
      count = null;
    } else {
      count = (Long.parseLong(pagecount) - 1L) * new Long(infoDto.getGoodsRows());
    }
    Map<String, String> blCodeNameMap = goodsMasterFacade.getBlCodeNameMap(makerCode);
    Map<String, String> selectCodeNameMap = goodsMasterFacade.getSelectCodeNameMap(makerCode);
    Map<String, String> partsNameMap = goodsMasterFacade.getPartsNameMap(makerCode);
    Map<String, String> classifyGuideNameMap = goodsMasterFacade.getClassifyCodeGuideMap(makerCode);

    gooodsMasterInfo = checkListFacade.searchErrorGoodsList(count, maxRows, makerCode);
    result.put(SEARCHCOUNTS, gooodsMasterInfo.getSearchCounts());
    // 商品一覧テータ設定
    int index = 1;
    for (GoodsMasterMakerDto goodsMasterMakerDto : gooodsMasterInfo.getGoodsMasterDto()) {
      GoodsGridDto goodsGridDto = convertGoodsGridDto(goodsMasterMakerDto, index, blCodeNameMap, selectCodeNameMap,
          partsNameMap, classifyGuideNameMap);
      index = index + 1;
      dataSourceList.add(goodsGridDto);
    }

    result.put(GOODSMASTER, dataSourceList);
    return dataSourceList;
  }

  /**
   * セットGRID表示データ取得
   * 
   * @param result WebResult
   * @param page page
   * @param mode 画面区分
   * @param request request
   * @return SetGridDto
   */
  private List<SetGridDto> getSetGrid(WebResult result, String page, String mode, HttpServletRequest request) {
    SetMasterInfoDto setMasterInfo;

    // BLコードリスト取得
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    // MessageMap
    Map<String, String> messageMap = getMessageMap(loginPrincipal.getMessageMap());
    result.put("MESSAGE", messageMap);
    int makerCode = loginPrincipal.getMakerCode();
    CompanySettingDto infoDto = employFacade.getCompanyInfo(makerCode);
    Long maxRows = Long.valueOf(infoDto.getSetRows());
    result.put(MAXROWS, infoDto.getSetRows());
    Long count = null;
    if (!EMPTY.equals(page)) {
      count = (Long.parseLong(page) - 1) * maxRows;
    }
    Map<String, String> blCodeNameMap = goodsMasterFacade.getBlCodeNameMap(makerCode);
    Map<String, String> selectCodeNameMap = goodsMasterFacade.getSelectCodeNameMap(makerCode);
    Map<String, String> partsNameMap = goodsMasterFacade.getPartsNameMap(makerCode);
    Map<String, String> classifyGuideNameMap = goodsMasterFacade.getClassifyCodeGuideMap(makerCode);

    setMasterInfo = checkListFacade.searchSetErrorList(count, maxRows, makerCode);
    result.put(SEARCHCOUNTS, setMasterInfo.getSearchCounts());
    List<SetGridDto> dataSourceList = new ArrayList<SetGridDto>();
    int no = 1;
    for (SetMasterDto setMasterDto : setMasterInfo.getSetMasterDtoList()) {
      SetGridDto setGridDto = convertSetGridDto(setMasterDto, blCodeNameMap, selectCodeNameMap, partsNameMap,
          classifyGuideNameMap);
      setGridDto.setNo(no++);
      dataSourceList.add(setGridDto);
    }
    result.put(SETMASTER, dataSourceList);
    return dataSourceList;
  }

  /**
   * 結合GRID表示データ取得
   * 
   * @param result WebResult
   * @param page page
   * @param mode 画面区分
   * @param request request
   * @return JoinGridDto
   */
  private List<JoinGridDto> getJoinGrid(WebResult result, String page, String mode, HttpServletRequest request) {
    JoinMasterInfoDto joinMasterInfoDto;

    // BLコードリスト取得
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    // MessageMap
    Map<String, String> messageMap = getMessageMap(loginPrincipal.getMessageMap());
    result.put("MESSAGE", messageMap);
    int makerCode = loginPrincipal.getMakerCode();
    CompanySettingDto infoDto = employFacade.getCompanyInfo(makerCode);
    Long maxRows = Long.valueOf(infoDto.getJoinRows());
    result.put(MAXROWS, infoDto.getJoinRows());
    Long count = null;
    if (!EMPTY.equals(page)) {
      count = (Long.parseLong(page) - 1L) * new Long(infoDto.getJoinRows());
    }
    Map<String, String> blCodeNameMap = goodsMasterFacade.getBlCodeNameMap(makerCode);
    Map<String, String> selectCodeNameMap = goodsMasterFacade.getSelectCodeNameMap(makerCode);
    Map<String, String> kindCodeNameMap = joinMasterFacade.getKindCodeNameMap(makerCode);
    Map<String, String> classifyGuideNameMap = goodsMasterFacade.getClassifyCodeGuideMap(makerCode);
    Map<String, String> carmakerNameMap = joinMasterFacade.getCarmakerNameMap(makerCode);

    joinMasterInfoDto = checkListFacade.searchJoinErrorList(count, maxRows, makerCode);
    result.put(SEARCHCOUNTS, joinMasterInfoDto.getSearchCounts());
    List<JoinGridDto> dataSourceList = new ArrayList<JoinGridDto>();
    int no = 1;
    // 商品一覧テータ設定
    for (JoinMasterDto joinMasterDto : joinMasterInfoDto.getJoinMasterDto()) {
      JoinGridDto joinGridDto = convertJoinGridDto(joinMasterDto, blCodeNameMap, selectCodeNameMap, kindCodeNameMap,
          classifyGuideNameMap, carmakerNameMap);
      joinGridDto.setNo(no++);
      dataSourceList.add(joinGridDto);
    }

    result.put(JOINMASTER, dataSourceList);
    return dataSourceList;

  }

  /**
   * gridのdata
   * 
   * @param goodsMasterMakerDto GoodsMasterMakerDto
   * @param index index
   * @param blCodeNameMap blCodeNameMap
   * @param selectCodeNameMap selectCodeNameMap
   * @param partsNameMap partsNameMap
   * @param classifyGuideNameMap classifyGuideNameMap
   * @return GoodsGridDto
   */
  private GoodsGridDto convertGoodsGridDto(GoodsMasterMakerDto goodsMasterMakerDto, int index,
                                           Map<String, String> blCodeNameMap, Map<String, String> selectCodeNameMap,
                                           Map<String, String> partsNameMap, Map<String, String> classifyGuideNameMap) {
    GoodsGridDto goodsGridDto = new GoodsGridDto();
    // No
    goodsGridDto.setNo(index);
    // 申請状態
    goodsGridDto.setApplyStep(ApplyConditionEnum.valueof(goodsMasterMakerDto.getApplyCondition()) == null ? EMPTY
        : ApplyConditionEnum.valueof(goodsMasterMakerDto.getApplyCondition()).getAbbreviation());
    // BLコード
    goodsGridDto.setBlCodeName(blCodeNameMap.get(String.valueOf(goodsMasterMakerDto.getTbsPartsCode())));
    // BL登録区分
    goodsGridDto.setBlSec(BlEntryFlgEnum.valueof(goodsMasterMakerDto.getBlEntryFlg()) == null ? EMPTY
        : BlEntryFlgEnum.valueof(goodsMasterMakerDto.getBlEntryFlg()).getAbbreviation());
    // チェック区分
    goodsGridDto.setCheckSec(CheckFlgEnum.valueof(goodsMasterMakerDto.getCheckFlg()) == null ? EMPTY
        : CheckFlgEnum.valueof(goodsMasterMakerDto.getCheckFlg()).getAbbreviation());
    // 作成日
    goodsGridDto.setDateCom(goodsMasterMakerDto.getInsDtTime() == null ? EMPTY
        : String.valueOf(goodsMasterMakerDto.getInsDtTime()).substring(0, 16).replace("-", "/"));
    // 更新日
    goodsGridDto.setDateRe(goodsMasterMakerDto.getUpdDtTime() == null ? EMPTY
        : String.valueOf(goodsMasterMakerDto.getUpdDtTime()).substring(0, 16).replace("-", "/"));
    // 適用日
    goodsGridDto.setDateSlice(goodsMasterMakerDto.getStartDate() == null ? EMPTY
        : goodsMasterMakerDto.getStartDate().toString().substring(0, 16).replace("-", "/"));
    // 削除理由
    goodsGridDto.setDelCon(goodsMasterMakerDto.getDeleteReason());
    // 削除依頼区分
    goodsGridDto.setDelSec(goodsMasterMakerDto.getDeleteFlg() == null ? EMPTY
        : DeleteFlgEnum.valueof(goodsMasterMakerDto.getDeleteFlg()).getAbbreviation());
    // 装備
    goodsGridDto.setEquip(goodsMasterMakerDto.getEquipName());
    // エラー内容
    goodsGridDto.setErrCon(goodsMasterMakerDto.getErrorDetail());
    // エラー区分
    goodsGridDto.setErrSec(ErrorFlgEnum.valueof(goodsMasterMakerDto.getErrorFlg()) == null ? EMPTY
        : ErrorFlgEnum.valueof(goodsMasterMakerDto.getErrorFlg()).getAbbreviation());
    // 商品詳細
    goodsGridDto.setGoods(goodsMasterMakerDto.getGoodsDetailB());
    // 商品詳細（一般）
    goodsGridDto.setGoodsCon(goodsMasterMakerDto.getGoodsDetailC());
    // 優良品番
    goodsGridDto.setGoodsNo(goodsMasterMakerDto.getPrimePartsNoWithH());
    // 画像有無
    goodsGridDto.setImg(goodsMasterMakerDto.getImageNo() == 0 ? "×" : String.valueOf(goodsMasterMakerDto.getImageNo()));
    // JAN
    goodsGridDto.setJan(goodsMasterMakerDto.getJan() == null ? null : String.valueOf(goodsMasterMakerDto.getJan()));
    // 層別
    goodsGridDto.setLayer(partsNameMap.get(goodsMasterMakerDto.getPartsLayerCd()));
    // 処理区分
    goodsGridDto.setManageSec(ManageKbnEnum.valueof(goodsMasterMakerDto.getManageKbn()) == null ? EMPTY
        : ManageKbnEnum.valueof(goodsMasterMakerDto.getManageKbn()).getAbbreviation());
    // 価格
    goodsGridDto.setMoney(
        goodsMasterMakerDto.getNewPrice() == null ? null : getPrice(goodsMasterMakerDto.getNewPrice().toString()));
    // 品名（全角）
    goodsGridDto.setNameB(goodsMasterMakerDto.getPrimePartsName());
    // 品名（半角）
    goodsGridDto.setNameS(goodsMasterMakerDto.getPrimePartsKanaNm());
    // OPENプランス
    if (goodsMasterMakerDto.getOpenPriceDiv() != null) {
      goodsGridDto.setOpen(OpenPriceDivEnum.valueof(goodsMasterMakerDto.getOpenPriceDiv()) == null ? null
          : OpenPriceDivEnum.valueof(goodsMasterMakerDto.getOpenPriceDiv()).getName());
    } else {
      goodsGridDto.setOpen(null);
    }
    // 梱包（長さ）
    goodsGridDto.setPackwidth1(goodsMasterMakerDto.getPackageSize1() == null ? null
        : getPrice(goodsMasterMakerDto.getPackageSize1().toString()));
    // 梱包（幅）
    goodsGridDto.setPackwidth2(goodsMasterMakerDto.getPackageSize2() == null ? null
        : getPrice(goodsMasterMakerDto.getPackageSize2().toString()));
    // 梱包（高さ）
    goodsGridDto.setPackwidth3(goodsMasterMakerDto.getPackageSize3() == null ? null
        : getPrice(goodsMasterMakerDto.getPackageSize3().toString()));
    // 分類コード
    goodsGridDto.setSecCodeName(classifyGuideNameMap.get(String.valueOf(goodsMasterMakerDto.getGoodsMGroup())));
    // セレクトコード名称
    goodsGridDto.setSelCode(goodsMasterMakerDto.getPrmSetDtlNo1() == 9999 ? "9999：指定無し"
        : selectCodeNameMap.get(String.valueOf(goodsMasterMakerDto.getPrmSetDtlNo1())));
    // 規格/特記
    goodsGridDto.setSize(goodsMasterMakerDto.getPrimePartsSpecialNoteB());
    // 規格/特記（一般）
    goodsGridDto.setSizeCon(goodsMasterMakerDto.getPrimePartsSpecialNoteC());
    // URL1
    goodsGridDto.setUrl1(goodsMasterMakerDto.getUrl1());
    // URL2
    goodsGridDto.setUrl2(goodsMasterMakerDto.getUrl2());
    // URL3
    goodsGridDto.setUrl3(goodsMasterMakerDto.getUrl3());
    // 重量
    goodsGridDto.setWeight(goodsMasterMakerDto.getGoodsWeight() == null ? null
        : getPrice(goodsMasterMakerDto.getGoodsWeight().toString()));
    // 重量単位
    goodsGridDto.setWeightUnit(WeightUnitEnum.valueof(goodsMasterMakerDto.getWeightUnit()).getName());
    // 長さ
    goodsGridDto.setWidth1(
        goodsMasterMakerDto.getGoodsSize1() == null ? null : getPrice(goodsMasterMakerDto.getGoodsSize1().toString()));
    // 幅
    goodsGridDto.setWidth2(
        goodsMasterMakerDto.getGoodsSize2() == null ? null : getPrice(goodsMasterMakerDto.getGoodsSize2().toString()));
    // 高さ
    goodsGridDto.setWidth3(
        goodsMasterMakerDto.getGoodsSize3() == null ? null : getPrice(goodsMasterMakerDto.getGoodsSize3().toString()));
    // サイズ単位
    goodsGridDto.setWidthUnit(SizeUnitEnum.valueof(goodsMasterMakerDto.getSizeUnit()).getName());
    // 隠匿域
    goodsGridDto.setHiddenArea(JudgeEnum.UnChange.getValue());
    // インポート区分
    goodsGridDto.setImportKbn(Integer.valueOf(goodsMasterMakerDto.getImportKbn()));
    return goodsGridDto;
  }

  /**
   * setgridのdata
   * 
   * @param setMasterDto SetMasterDto
   * @param partsNameMap blCodeNameMap
   * @param selectCodeNameMap selectCodeNameMap
   * @param blCodeNameMap partsNameMap
   * @param classifyGuideNameMap classifyGuideNameMap
   * @return setGridDto
   */
  private SetGridDto convertSetGridDto(SetMasterDto setMasterDto, Map<String, String> blCodeNameMap,
                                       Map<String, String> selectCodeNameMap, Map<String, String> partsNameMap,
                                       Map<String, String> classifyGuideNameMap) {
    SetGridDto setGridDto = new SetGridDto();
    // エラー区分
    setGridDto.setErrorFlg(ErrorFlgEnum.valueof(setMasterDto.getErrorFlg()) == null ? EMPTY
        : ErrorFlgEnum.valueof(setMasterDto.getErrorFlg()).getAbbreviation());
    // エラー内容
    setGridDto.setErrorDetail(setMasterDto.getErrorDetail());
    // セレクトコード名称
    setGridDto.setPrmSetDtlNo1(setMasterDto.getPrmSetDtlNo1() == 9999 ? "9999：指定無し"
        : selectCodeNameMap.get(String.valueOf(setMasterDto.getPrmSetDtlNo1())));
    // 分類コード
    setGridDto.setGoodsMGroup(classifyGuideNameMap.get(String.valueOf(setMasterDto.getGoodsMGroup())));
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

    return setGridDto;
  }

  /**
   * gridのdataに転換
   * 
   * @param joinMasterDto JoinMasterDto
   * @param blCodeNameMap blCodeNameMap
   * @param selectCodeNameMap selectCodeNameMap
   * @param kindCodeMap kindCodeMap
   * @param classifyGuideNameMap classifyGuideNameMap
   * @param carmakerNameMap carmakerNameMap
   * @return JoinGridDto
   */
  private JoinGridDto convertJoinGridDto(JoinMasterDto joinMasterDto, Map<String, String> blCodeNameMap,
                                         Map<String, String> selectCodeNameMap, Map<String, String> kindCodeMap,
                                         Map<String, String> classifyGuideNameMap,
                                         Map<String, String> carmakerNameMap) {
    JoinGridDto joinGridDto = new JoinGridDto();
    // 申請状態
    joinGridDto.setApplyCondition(ApplyConditionEnum.valueof(joinMasterDto.getApplyCondition()) == null ? EMPTY
        : ApplyConditionEnum.valueof(joinMasterDto.getApplyCondition()).getAbbreviation());
    // 処理区分
    joinGridDto.setManageKbn(ManageKbnEnum.valueof(joinMasterDto.getManageKbn()) == null ? EMPTY
        : ManageKbnEnum.valueof(joinMasterDto.getManageKbn()).getAbbreviation());
    // セレクトコード名称
    joinGridDto.setPrmSetDtlNo1(joinMasterDto.getPrmSetDtlNo1() == 9999 ? "9999：指定無し"
        : selectCodeNameMap.get(String.valueOf(joinMasterDto.getPrmSetDtlNo1())));
    // 分類コード名称
    joinGridDto.setGoodsMGroup(classifyGuideNameMap.get(String.valueOf(joinMasterDto.getGoodsMGroup())));
    // BLコード名称
    joinGridDto.setTbsPartsCode(blCodeNameMap.get(String.valueOf(joinMasterDto.getTbsPartsCode())));
    // カーコード名称
    joinGridDto.setJoinSourceMakerCode(carmakerNameMap.get(String.valueOf(joinMasterDto.getJoinSourceMakerCode())));
    // 純正品番
    joinGridDto.setJoinSourPartsNoWithH(joinMasterDto.getJoinSourPartsNoWithH());
    // 種別コード名称
    joinGridDto.setPrmSetDtlNo2(kindCodeMap.get(String.valueOf(joinMasterDto.getPrmSetDtlNo2())));
    // 表示順位
    joinGridDto.setJoinDispOrder(String.valueOf(joinMasterDto.getJoinDispOrder()));
    // 優良品番
    joinGridDto.setJoinDestPartsNo(joinMasterDto.getJoinDestPartsNo());
    // QTY
    joinGridDto.setJoinQty(joinMasterDto.getJoinQty() == null ? null : String.valueOf(joinMasterDto.getJoinQty()));
    // 規格/特記
    joinGridDto.setJoinSpecialNote(joinMasterDto.getJoinSpecialNote());
    // 規格/特記（一般）
    joinGridDto.setPrimePartsSpecialNoteC(joinMasterDto.getPrimePartsSpecialNoteC());
    // 削除依頼区分
    joinGridDto.setDeleteFlg(DeleteFlgEnum.valueof(joinMasterDto.getDeleteFlg()) == null ? EMPTY
        : DeleteFlgEnum.valueof(joinMasterDto.getDeleteFlg()).getAbbreviation());
    // 削除理由
    joinGridDto.setDeleteReason(joinMasterDto.getDeleteReason());
    // 作成日時
    joinGridDto.setInsDtTime(joinMasterDto.getInsDtTime() == null ? EMPTY : toString(joinMasterDto.getInsDtTime()));
    // 更新日時
    joinGridDto.setUpdDtTime(joinMasterDto.getUpdDtTime() == null ? EMPTY : toString(joinMasterDto.getUpdDtTime()));
    // 適用日時
    joinGridDto.setStartTime(joinMasterDto.getStartTime() == null ? EMPTY : toString(joinMasterDto.getStartTime()));
    // チェック区分
    joinGridDto.setCheckFlg(CheckFlgEnum.valueof(joinMasterDto.getCheckFlg()) == null ? EMPTY
        : CheckFlgEnum.valueof(joinMasterDto.getCheckFlg()).getAbbreviation());
    // BL登録区分
    joinGridDto.setBlEntryFlg(BlEntryFlgEnum.valueof(joinMasterDto.getBlEntryFlg()) == null ? EMPTY
        : BlEntryFlgEnum.valueof(joinMasterDto.getBlEntryFlg()).getAbbreviation());
    // エラー区分
    joinGridDto.setErrorFlg(ErrorFlgEnum.valueof(joinMasterDto.getErrorFlg()) == null ? EMPTY
        : ErrorFlgEnum.valueof(joinMasterDto.getErrorFlg()).getAbbreviation());
    // エラー内容
    joinGridDto.setErrorDetail(joinMasterDto.getErrorDetail());
    joinGridDto.setImportKbn(Integer.valueOf(joinMasterDto.getImportKbn()));
    joinGridDto.setHiddenArea(JudgeEnum.UnChange.getValue());
    return joinGridDto;
  }

  /**
   * DateからStringに転換
   * 
   * @param date date
   * @return 転換結果
   */
  private String toString(Date date) {
    String time;
    SimpleDateFormat formater = new SimpleDateFormat();
    formater.applyPattern("yyyy/MM/dd hh:mm");
    time = formater.format(date);
    return time;
  }

  /**
   * priceを取得
   * 
   * @param price price
   * @return price
   */
  private String getPrice(String price) {
    if (price == null || price.equals(EMPTY)) {
      return EMPTY;
    }
    String newPrice = "";
    String[] priceArray = price.split("\\.");
    DecimalFormat myformat = new DecimalFormat();
    myformat.applyPattern("###,###,###");
    newPrice = newPrice + myformat.format(Integer.valueOf(priceArray[0])).toString();
    if (priceArray.length == 2) {
      newPrice = newPrice + "." + priceArray[1];
    }
    if (newPrice.indexOf(".") > 0) {
      newPrice = newPrice.replaceAll("0+?$", "");
      newPrice = newPrice.replaceAll("[.]$", "");
    }
    return newPrice;
  }

  /**
   * ファイル名を取得する。
   * 
   * @param showItem ページを表示する
   * @param fileType 出力ファイルタイプ。
   * @return ファイル名
   */
  private String getFileName(String showItem, String fileType) {
    String fileName = EMPTY;
    if (showItem.equals(SelectTabEnum.GoodsTab.getValue())) {
      fileName += "チェックエラー商品情報";
    }
    if (showItem.equals(SelectTabEnum.SetTab.getValue())) {
      fileName += "チェックエラーセット情報";
    }
    if (showItem.equals(SelectTabEnum.JoinTab.getValue())) {
      fileName += "チェックエラー結合情報";
    }
    fileName = fileName.concat(BroadleafFormatUtils.dateToString(new Date(), BroadleafFormatUtils.DATE_F009));
    if (fileType.equals(OutPutTypeEnum.OutPutPdf.getValue())) {
      fileName += ".pdf";
    }
    if (fileType.equals(OutPutTypeEnum.OutPutTsv.getValue())) {
      fileName += ".tsv";
    }
    if (fileType.equals(OutPutTypeEnum.OutPutXls.getValue())) {
      fileName += ".xls";
    }
    return fileName;
  }

  /**
   * 出力ファイルタイプを取得する。
   * 
   * @param fileType 出力ファイルタイプ。
   * @return 出力ファイルタイプ。
   */
  private int getFileType(String fileType) {
    int type = 1;
    if (fileType.equals(OutPutTypeEnum.OutPutPdf.getValue())) {
      type = OutPutTypeEnum.OutPutPdf.getName();
    }
    if (fileType.equals(OutPutTypeEnum.OutPutTsv.getValue())) {
      type = OutPutTypeEnum.OutPutTsv.getName();
    }
    if (fileType.equals(OutPutTypeEnum.OutPutXls.getValue())) {
      type = OutPutTypeEnum.OutPutXls.getName();
    }
    return type;
  }

  /**
   * 出力商品ファイル
   * 
   * @param request request
   * @param result result
   * @param fileName ファイル名
   * @param fileType 出力ファイルタイプ
   * @param dataSourceList dataSourceList
   * @return ExportOutModel
   */
  public ExportOutModel makeGoodsFile(WebResult result, HttpServletRequest request, String fileType, String fileName,
                                      List<GoodsGridDto> dataSourceList) {
    logger.debug("出力商品ファイル処理を開始します。");
    ExportOutModel exportOutModel = new ExportOutModel();
    if (request.getParameter("flg") != null) {
      String[] tableHeader = {
          "No.",
          "エラー区分",
          "エラー内容",
          "申請状態",
          "処理区分",
          "セレクトコード名称",
          "分類コード名称",
          "BLコード名称",
          "優良品番",
          "品名（半角）",
          "品名（全角）",
          "価格（税抜）",
          "OPENプライス",
          "JAN",
          "層別",
          "装備",
          "規格/特記",
          "規格/特記（一般）",
          "削除依頼区分",
          "削除理由",
          "商品詳細",
          "商品詳細（一般）",
          "長さ",
          "幅",
          "高さ",
          "梱包（長さ）",
          "梱包（幅）",
          "梱包（高さ）",
          "梱包単位",
          "重量",
          "重量単位",
          "URL1",
          "URL2",
          "URL3",
          "画像有無",
          "作成日時",
          "更新日時",
          "適用日時",
          "チェック区分",
          "BL登録区分" };
      String[] colKind = {
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue() };
      float[] colWidth = {
          30F,
          50F,
          400F,
          40F,
          40F,
          90F,
          70F,
          70F,
          240F,
          200F,
          200F,
          120F,
          80F,
          130F,
          50F,
          200F,
          400F,
          400F,
          60F,
          400F,
          400F,
          400F,
          60F,
          60F,
          60F,
          60F,
          60F,
          60F,
          40F,
          60F,
          40F,
          400F,
          400F,
          400F,
          40F,
          60F,
          60F,
          60F,
          40F,
          60F };
      List<String[]> detailList = new ArrayList<String[]>();
      for (GoodsGridDto dto : dataSourceList) {
        int index = 0;
        String[] detailArr = new String[tableHeader.length];
        detailArr[index++] = String.valueOf(dto.getNo());
        detailArr[index++] = dto.getErrSec();
        detailArr[index++] = dto.getErrCon();
        detailArr[index++] = dto.getApplyStep();
        detailArr[index++] = dto.getManageSec();
        detailArr[index++] = dto.getSelCode();
        detailArr[index++] = dto.getSecCodeName();
        detailArr[index++] = dto.getBlCodeName();
        detailArr[index++] = dto.getGoodsNo();
        detailArr[index++] = dto.getNameS();
        detailArr[index++] = dto.getNameB();
        detailArr[index++] = dto.getMoney();
        detailArr[index++] = dto.getOpen();
        detailArr[index++] = dto.getJan();
        detailArr[index++] = dto.getLayer();
        detailArr[index++] = dto.getEquip();
        detailArr[index++] = dto.getSize();
        detailArr[index++] = dto.getSizeCon();
        detailArr[index++] = dto.getDelSec();
        detailArr[index++] = dto.getDelCon();
        detailArr[index++] = dto.getGoods();
        detailArr[index++] = dto.getGoodsCon();
        detailArr[index++] = dto.getWidth1();
        detailArr[index++] = dto.getWidth2();
        detailArr[index++] = dto.getWidth3();
        detailArr[index++] = dto.getPackwidth1();
        detailArr[index++] = dto.getPackwidth2();
        detailArr[index++] = dto.getPackwidth3();
        detailArr[index++] = dto.getWidthUnit();
        detailArr[index++] = dto.getWeight();
        detailArr[index++] = dto.getWeightUnit();
        detailArr[index++] = dto.getUrl1();
        detailArr[index++] = dto.getUrl2();
        detailArr[index++] = dto.getUrl3();
        detailArr[index++] = dto.getImg();
        detailArr[index++] = dto.getDateCom();
        detailArr[index++] = dto.getDateRe();
        detailArr[index++] = dto.getDateSlice();
        detailArr[index++] = dto.getCheckSec();
        detailArr[index++] = dto.getBlSec();
        detailList.add(detailArr);
      }
      exportOutModel.setColKind(colKind);
      exportOutModel.setColWidth(colWidth);
      exportOutModel.setTableHeader(tableHeader);
      exportOutModel.setDetailList(detailList);
    } else {
      String[] tableHeader = {
          "No.",
          "申請状態",
          "処理区分",
          "セレクトコード名称",
          "分類コード名称",
          "BLコード名称",
          "優良品番",
          "品名（半角）",
          "品名（全角）",
          "価格（税抜）",
          "OPENプライス",
          "JAN",
          "層別",
          "装備",
          "規格/特記",
          "規格/特記（一般）",
          "削除依頼区分",
          "削除理由",
          "商品詳細",
          "商品詳細（一般）",
          "長さ",
          "幅",
          "高さ",
          "梱包（長さ）",
          "梱包（幅）",
          "梱包（高さ）",
          "梱包単位",
          "重量",
          "重量単位",
          "URL1",
          "URL2",
          "URL3",
          "画像有無",
          "作成日時",
          "更新日時",
          "適用日時",
          "チェック区分",
          "BL登録区分",
          "エラー区分",
          "エラー内容" };
      String[] colKind = {
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue() };
      float[] colWidth = {
          30F,
          40F,
          40F,
          90F,
          70F,
          70F,
          240F,
          200F,
          200F,
          120F,
          80F,
          130F,
          50F,
          200F,
          400F,
          400F,
          60F,
          400F,
          400F,
          400F,
          60F,
          60F,
          60F,
          60F,
          60F,
          60F,
          40F,
          60F,
          40F,
          400F,
          400F,
          400F,
          40F,
          60F,
          60F,
          60F,
          40F,
          60F,
          50F,
          400F };
      List<String[]> detailList = new ArrayList<String[]>();
      for (GoodsGridDto dto : dataSourceList) {
        int index = 0;
        String[] detailArr = new String[tableHeader.length];
        detailArr[index++] = String.valueOf(dto.getNo());
        detailArr[index++] = dto.getApplyStep();
        detailArr[index++] = dto.getManageSec();
        detailArr[index++] = dto.getSelCode();
        detailArr[index++] = dto.getSecCodeName();
        detailArr[index++] = dto.getBlCodeName();
        detailArr[index++] = dto.getGoodsNo();
        detailArr[index++] = dto.getNameS();
        detailArr[index++] = dto.getNameB();
        detailArr[index++] = dto.getMoney();
        detailArr[index++] = dto.getOpen();
        detailArr[index++] = dto.getJan();
        detailArr[index++] = dto.getLayer();
        detailArr[index++] = dto.getEquip();
        detailArr[index++] = dto.getSize();
        detailArr[index++] = dto.getSizeCon();
        detailArr[index++] = dto.getDelSec();
        detailArr[index++] = dto.getDelCon();
        detailArr[index++] = dto.getGoods();
        detailArr[index++] = dto.getGoodsCon();
        detailArr[index++] = dto.getWidth1();
        detailArr[index++] = dto.getWidth2();
        detailArr[index++] = dto.getWidth3();
        detailArr[index++] = dto.getPackwidth1();
        detailArr[index++] = dto.getPackwidth2();
        detailArr[index++] = dto.getPackwidth3();
        detailArr[index++] = dto.getWidthUnit();
        detailArr[index++] = dto.getWeight();
        detailArr[index++] = dto.getWeightUnit();
        detailArr[index++] = dto.getUrl1();
        detailArr[index++] = dto.getUrl2();
        detailArr[index++] = dto.getUrl3();
        detailArr[index++] = dto.getImg();
        detailArr[index++] = dto.getDateCom();
        detailArr[index++] = dto.getDateRe();
        detailArr[index++] = dto.getDateSlice();
        detailArr[index++] = dto.getCheckSec();
        detailArr[index++] = dto.getBlSec();
        detailArr[index++] = dto.getErrSec();
        detailArr[index++] = dto.getErrCon();
        detailList.add(detailArr);
      }
      exportOutModel.setColKind(colKind);
      exportOutModel.setColWidth(colWidth);
      exportOutModel.setTableHeader(tableHeader);
      exportOutModel.setDetailList(detailList);
    }
    int type = getFileType(fileType);

    exportOutModel.setReportKind(type);
    exportOutModel.setReportName(fileName);
    logger.debug("出力商品ファイル処理を終了します。");
    return exportOutModel;
  }

  /**
   * 出力セットファイル
   * 
   * @param request request
   * @param result result
   * @param fileName ファイル名
   * @param fileType 出力ファイルタイプ
   * @param dataSourceList dataSourceList
   * @return ExportOutModel
   */
  public ExportOutModel makeSetFile(WebResult result, HttpServletRequest request, String fileType, String fileName,
                                    List<SetGridDto> dataSourceList) {
    logger.debug("出力セットファイル処理を開始します。");

    ExportOutModel exportOutModel = new ExportOutModel();
    if (request.getParameter("flg") != null) {
      String[] tableHeader = {
          "No.",
          "エラー区分",
          "エラー内容",
          "セレクトコード名称",
          "分類コード名称",
          "BLコード名称",
          "セット親品番",
          "表示順位",
          "セット子品番",
          "品名（半角）",
          "品名（全角）",
          "QTY",
          "規格/特記.",
          "削除依頼区分",
          "削除理由",
          "作成日時",
          "更新日時",
          "適用日時" };
      String[] colKind = {
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue() };
      float[] colWidth = {
          30F,
          50F,
          400F,
          90F,
          70F,
          70F,
          240F,
          40F,
          240F,
          200F,
          200F,
          50F,
          400F,
          60F,
          400F,
          60F,
          60F,
          60F };
      List<String[]> detailList = new ArrayList<String[]>();
      for (SetGridDto dto : dataSourceList) {
        int index = 0;
        String[] detailArr = new String[tableHeader.length];
        detailArr[index++] = String.valueOf(dto.getNo());
        detailArr[index++] = dto.getErrorFlg();
        detailArr[index++] = dto.getErrorDetail();
        detailArr[index++] = dto.getPrmSetDtlNo1();
        detailArr[index++] = dto.getGoodsMGroup();
        detailArr[index++] = dto.getTbsPartsCode();
        detailArr[index++] = dto.getSetMainPartsNo();
        detailArr[index++] = dto.getSetDispOrder();
        detailArr[index++] = dto.getSetSubPartsNo();
        detailArr[index++] = dto.getSetKanaName();
        detailArr[index++] = dto.getSetName();
        detailArr[index++] = dto.getSetQty().toString();
        detailArr[index++] = dto.getSetSpecialNote();
        detailArr[index++] = dto.getDeleteFlg();
        detailArr[index++] = dto.getDeleteReason();
        detailArr[index++] = dto.getInsDtTime();
        detailArr[index++] = dto.getUpdDtTime();
        detailArr[index++] = dto.getStartTime();
        detailList.add(detailArr);
      }
      exportOutModel.setColKind(colKind);
      exportOutModel.setColWidth(colWidth);
      exportOutModel.setTableHeader(tableHeader);
      exportOutModel.setDetailList(detailList);
    } else {
      String[] tableHeader = {
          "No.",
          "申請状態",
          "処理区分",
          "セレクトコード名称",
          "分類コード名称",
          "BLコード名称",
          "セット親品番",
          "表示順位",
          "セット子品番",
          "品名（半角）",
          "品名（全角）",
          "QTY",
          "規格/特記.",
          "規格/特記（一般）",
          "削除依頼区分",
          "削除理由",
          "作成日時",
          "更新日時",
          "適用日時",
          "チェック区分",
          "BL登録区分",
          "エラー区分",
          "エラー内容" };
      String[] colKind = {
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue() };
      float[] colWidth = {
          30F,
          40F,
          40F,
          90F,
          70F,
          70F,
          240F,
          40F,
          240F,
          200F,
          200F,
          50F,
          400F,
          60F,
          400F,
          400F,
          60F,
          60F,
          60F,
          40F,
          60F,
          50F,
          400F };
      List<String[]> detailList = new ArrayList<String[]>();
      for (SetGridDto dto : dataSourceList) {
        int index = 0;
        String[] detailArr = new String[tableHeader.length];
        detailArr[index++] = String.valueOf(dto.getNo());
        detailArr[index++] = dto.getApplyCondition();
        detailArr[index++] = dto.getManageKbn();
        detailArr[index++] = dto.getPrmSetDtlNo1();
        detailArr[index++] = dto.getGoodsMGroup();
        detailArr[index++] = dto.getTbsPartsCode();
        detailArr[index++] = dto.getSetMainPartsNo();
        detailArr[index++] = dto.getSetDispOrder();
        detailArr[index++] = dto.getSetSubPartsNo();
        detailArr[index++] = dto.getSetKanaName();
        detailArr[index++] = dto.getSetName();
        detailArr[index++] = dto.getSetQty().toString();
        detailArr[index++] = dto.getSetSpecialNote();
        detailArr[index++] = dto.getPrimePartsSpecialNoteC();
        detailArr[index++] = dto.getDeleteFlg();
        detailArr[index++] = dto.getDeleteReason();
        detailArr[index++] = dto.getInsDtTime();
        detailArr[index++] = dto.getUpdDtTime();
        detailArr[index++] = dto.getStartTime();
        detailArr[index++] = dto.getCheckFlg();
        detailArr[index++] = dto.getBlEntryFlg();
        detailArr[index++] = dto.getErrorFlg();
        detailArr[index++] = dto.getErrorDetail();
        detailList.add(detailArr);
      }

      exportOutModel.setColKind(colKind);
      exportOutModel.setColWidth(colWidth);
      exportOutModel.setTableHeader(tableHeader);
      exportOutModel.setDetailList(detailList);
    }
    int type = getFileType(fileType);
    exportOutModel.setReportKind(type);
    exportOutModel.setReportName(fileName);
    logger.debug("出力セットファイル処理を終了します。");
    return exportOutModel;
  }

  /**
   * 出力結合ファイル
   * 
   * @param request request
   * @param result result
   * @param fileName ファイル名
   * @param fileType 出力ファイルタイプ
   * @param dataSourceList dataSourceList
   * @return ExportOutModel
   */
  public ExportOutModel makeJoinFile(WebResult result, HttpServletRequest request, String fileType, String fileName,
                                     List<JoinGridDto> dataSourceList) {
    logger.debug("出力結合ファイル処理を開始します。");

    ExportOutModel exportOutModel = new ExportOutModel();
    if (request.getParameter("flg") != null) {
      String[] tableHeader = {
          "No.",
          "エラー区分",
          "エラー内容",
          "セレクトコード名称",
          "分類コード名称",
          "BLコード名称",
          "カーコード名称",
          "純正品番",
          "種別コード名称",
          "表示順位",
          "優良品番",
          "QTY",
          "規格/特記",
          "規格/特記（一般）",
          "削除依頼区分.",
          "削除理由",
          "作成日時",
          "更新日時",
          "適用日時",
          "チェック区分",
          "BL登録区分" };
      String[] colKind = {
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue() };
      float[] colWidth = {
          30F,
          50F,
          400F,
          90F,
          70F,
          70F,
          70F,
          240F,
          50F,
          40F,
          240F,
          50F,
          400F,
          400F,
          60F,
          400F,
          60F,
          60F,
          60F,
          40F,
          60F };
      List<String[]> detailList = new ArrayList<String[]>();
      for (JoinGridDto dto : dataSourceList) {
        int index = 0;
        String[] detailArr = new String[tableHeader.length];
        detailArr[index++] = String.valueOf(dto.getNo());
        detailArr[index++] = dto.getErrorFlg();
        detailArr[index++] = dto.getErrorDetail();
        detailArr[index++] = dto.getPrmSetDtlNo1();
        detailArr[index++] = dto.getGoodsMGroup();
        detailArr[index++] = dto.getTbsPartsCode();
        detailArr[index++] = dto.getJoinSourceMakerCode();
        detailArr[index++] = dto.getJoinSourPartsNoWithH();
        detailArr[index++] = dto.getPrmSetDtlNo2();
        detailArr[index++] = dto.getJoinDispOrder();
        detailArr[index++] = dto.getJoinDestPartsNo();
        detailArr[index++] = dto.getJoinQty();
        detailArr[index++] = dto.getJoinSpecialNote();
        detailArr[index++] = dto.getPrimePartsSpecialNoteC();
        detailArr[index++] = dto.getDeleteFlg();
        detailArr[index++] = dto.getDeleteReason();
        detailArr[index++] = dto.getInsDtTime();
        detailArr[index++] = dto.getUpdDtTime();
        detailArr[index++] = dto.getStartTime();
        detailArr[index++] = dto.getCheckFlg();
        detailArr[index++] = dto.getBlEntryFlg();
        detailList.add(detailArr);

      }
      exportOutModel.setColKind(colKind);
      exportOutModel.setColWidth(colWidth);
      exportOutModel.setTableHeader(tableHeader);
      exportOutModel.setDetailList(detailList);
    } else {
      String[] tableHeader = {
          "No.",
          "申請状態",
          "処理区分",
          "セレクトコード名称",
          "分類コード名称",
          "BLコード名称",
          "カーコード名称",
          "純正品番",
          "種別コード名称",
          "表示順位",
          "優良品番",
          "QTY",
          "規格/特記",
          "規格/特記（一般）",
          "削除依頼区分.",
          "削除理由",
          "作成日時",
          "更新日時",
          "適用日時",
          "チェック区分",
          "BL登録区分",
          "エラー区分",
          "エラー内容" };
      String[] colKind = {
          ColumnKindEnum.Rnumeric.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue(),
          ColumnKindEnum.Rstring.getValue() };
      float[] colWidth = {
          30F,
          40F,
          40F,
          90F,
          70F,
          70F,
          70F,
          240F,
          50F,
          40F,
          240F,
          50F,
          400F,
          400F,
          60F,
          400F,
          60F,
          60F,
          60F,
          40F,
          60F,
          50F,
          400F };
      List<String[]> detailList = new ArrayList<String[]>();
      for (JoinGridDto dto : dataSourceList) {
        int index = 0;
        String[] detailArr = new String[tableHeader.length];
        detailArr[index++] = String.valueOf(dto.getNo());
        detailArr[index++] = dto.getApplyCondition();
        detailArr[index++] = dto.getManageKbn();
        detailArr[index++] = dto.getPrmSetDtlNo1();
        detailArr[index++] = dto.getGoodsMGroup();
        detailArr[index++] = dto.getTbsPartsCode();
        detailArr[index++] = dto.getJoinSourceMakerCode();
        detailArr[index++] = dto.getJoinSourPartsNoWithH();
        detailArr[index++] = dto.getPrmSetDtlNo2();
        detailArr[index++] = dto.getJoinDispOrder();
        detailArr[index++] = dto.getJoinDestPartsNo();
        detailArr[index++] = dto.getJoinQty();
        detailArr[index++] = dto.getJoinSpecialNote();
        detailArr[index++] = dto.getPrimePartsSpecialNoteC();
        detailArr[index++] = dto.getDeleteFlg();
        detailArr[index++] = dto.getDeleteReason();
        detailArr[index++] = dto.getInsDtTime();
        detailArr[index++] = dto.getUpdDtTime();
        detailArr[index++] = dto.getStartTime();
        detailArr[index++] = dto.getCheckFlg();
        detailArr[index++] = dto.getBlEntryFlg();
        detailArr[index++] = dto.getErrorFlg();
        detailArr[index++] = dto.getErrorDetail();
        detailList.add(detailArr);
      }
      exportOutModel.setColKind(colKind);
      exportOutModel.setColWidth(colWidth);
      exportOutModel.setTableHeader(tableHeader);
      exportOutModel.setDetailList(detailList);
    }

    int type = getFileType(fileType);

    exportOutModel.setReportKind(type);
    exportOutModel.setReportName(fileName);
    logger.debug("出力結合ファイル処理を終了します。");
    return exportOutModel;
  }

  /**
   * メッセージ情報取得
   * 
   * @param messageMapAll すべてメッセージ情報
   * @return 画面表示用メッセージ情報
   */
  private Map<String, String> getMessageMap(HashMap<String, String> messageMapAll) {
    List<String> messageIdList = new ArrayList<String>();
    messageIdList.add(BregMessageCodes.Q00001);
    messageIdList.add(BregMessageCodes.Q00002);
    messageIdList.add(BregMessageCodes.E00008);
    messageIdList.add(BregMessageCodes.E00009);
    messageIdList.add(BregMessageCodes.E00010);
    messageIdList.add(BregMessageCodes.E00011);
    messageIdList.add(BregMessageCodes.E00012);
    messageIdList.add(BregMessageCodes.E00013);
    messageIdList.add(BregMessageCodes.E00014);
    messageIdList.add(BregMessageCodes.E00015);
    messageIdList.add(BregMessageCodes.E00016);
    messageIdList.add(BregMessageCodes.Q00001);
    messageIdList.add(BregMessageCodes.Q00001);
    messageIdList.add(BregMessageCodes.Q00001);
    messageIdList.add(BregMessageCodes.Q00001);
    messageIdList.add(BregMessageCodes.Q00001);
    messageIdList.add(BregMessageCodes.I00001);
    return messageFacade.getMessageContentsMap(messageIdList, messageMapAll);
  }
}
