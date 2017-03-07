//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : wangnan
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.goodslist.controller;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.BlEntryFlgEnum;
import jp.co.broadleaf.breg.common.enums.CheckFlgEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.common.enums.JudgeEnum;
import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.common.enums.ModeEnum;
import jp.co.broadleaf.breg.common.enums.OpenPriceDivEnum;
import jp.co.broadleaf.breg.common.enums.SizeUnitEnum;
import jp.co.broadleaf.breg.common.enums.WeightUnitEnum;
import jp.co.broadleaf.breg.common.model.ExportOutModel;
import jp.co.broadleaf.breg.goodslist.facade.GoodsMasterMakerFacade;
import jp.co.broadleaf.breg.goodslist.facade.GoodsMasterWorkMakerFacade;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsGridDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerInfoDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterSearchDto;
import jp.co.broadleaf.breg.message.service.Facade.MessageFacade;
import jp.co.broadleaf.breg.web.checklist.controller.CheckListController;
import jp.co.broadleaf.breg.web.common.controller.ReportOutController;
import jp.co.broadleaf.breg.web.productlist.form.ProductListForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.BroadleafFormatUtils;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * 商品一覧Controllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {
  /** ログイン画面 */
  private static final String PRODUCTLIST_URL = "goodslist/goods";

  /** 商品マスタ(メーカー)Facade */
  private GoodsMasterMakerFacade goodsMasterFacade;

  /**
   * <pre>
   * 商品マスタFacadeを設定する。
   * </pre>
   * 
   * @param facade 商品マスタFacade
   */
  @Resource
  public void setItemMasterFacade(GoodsMasterMakerFacade facade) {
    this.goodsMasterFacade = facade;
  }

  /** 商品マスタ(メーカー)Facade */
  private GoodsMasterWorkMakerFacade goodsMasterWorkFacade;

  /**
   * <pre>
   * 商品マスタFacadeを設定する。
   * </pre>
   * 
   * @param goodsMasterWorkFacade 商品マスタFacade
   */
  @Resource
  public void setGoodsMasterWorkFacade(GoodsMasterWorkMakerFacade goodsMasterWorkFacade) {
    this.goodsMasterWorkFacade = goodsMasterWorkFacade;
  }

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
   * 初期表示時。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @ResponseBody
  @RequestMapping(path = "/goods", method = RequestMethod.GET)
  public ModelAndView itemCtlgInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("商品一覧の初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(PRODUCTLIST_URL);
    Map<String, Object> enumMap = new HashMap<String, Object>();
    enumMap.put("applyCondition", ApplyConditionEnum.values());
    enumMap.put("manageKbn", ManageKbnEnum.values());
    enumMap.put("deleteFlg", DeleteFlgEnum.values());
    result.addAllObjects(enumMap);
    logger.debug("商品一覧の初期表示処理を終了します。");
    return result;
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
  public WebResult goodsMGroup(@RequestBody ProductListForm form, HttpServletRequest request,
                               HttpServletResponse response) {
    logger.debug("商品一覧の商品中分類コードガイドモード設定処理を開始します。");
    WebResult result = new WebResult();
    HttpSession session = request.getSession();
    session.setAttribute(BroadleafSessionKeys.CLASSIFY_CODE_GUIDE, form.getGoodsMGroup());
    logger.debug("商品一覧の商品中分類コードガイドモード設定処理を終了します。");
    return result;
  }

  /**
   * grid 初期表示時
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @param form ProductListForm
   * @return WebResult
   */
  @RequestMapping(path = "/goods", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult normalLogin(@RequestBody ProductListForm form, HttpServletRequest request,
                               HttpServletResponse response) {
    logger.debug("商品一覧のグリッド初期表示処理を開始します。");
    WebResult result = new WebResult();
    try {
      Boolean isControl = goodsMasterFacade.isControl();
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      int makerCode = loginPrincipal.getMakerCode();
      Map<String, String> blCodeNameMap = goodsMasterFacade.getBlCodeNameMap(makerCode);
      Map<String, String> selectCodeNameMap = goodsMasterFacade.getSelectCodeNameMap(makerCode);
      Map<String, String> partsNameMap = goodsMasterFacade.getPartsNameMap(makerCode);
      Map<String, String> classifyGuideNameMap = goodsMasterFacade.getClassifyCodeGuideMap(makerCode);

      Map<Integer, String> applyConditionMap = new HashMap<>();
      setApplyConditionMap(form.getMode(), applyConditionMap);
      /** MessageMap **/
      Map<String, String> messageMap = getMessageMap(loginPrincipal.getMessageMap());

      Long goodsRows = (long) goodsMasterFacade.searchGoodsListRows(makerCode);
      int pageNo = form.getPageNo() == null || form.getPageNo().isEmpty() ? 1 : Integer.parseInt(form.getPageNo());
      Long skipRows = (long) goodsRows * (pageNo - 1);
      // 検索条件設定
      ArrayList<GoodsGridDto> dataSourceList = new ArrayList<GoodsGridDto>();
      int allNum = 0;
      int searchNum = 0;
      GoodsMasterMakerInfoDto goodsMasterInfoDtoAll = null;
      short importKbn = form.getImportKbn();
      if (form.getMode().intValue() == ModeEnum.Reference.getValue()
          || form.getMode().intValue() == ModeEnum.Input.getValue()) {
        int searchMode = 0;
        if (form.getMode().intValue() == ModeEnum.Reference.getValue() && importKbn == 0) {
          searchMode = 2;
        } else if (form.getMode().intValue() == ModeEnum.Reference.getValue() && importKbn == 1) {
          searchMode = 4;
        }
        allNum = goodsMasterFacade.searchGoodsMasterInfoAll(null, null, false, makerCode, searchMode)
            .getGoodsMasterDto().size();
        goodsMasterInfoDtoAll = goodsMasterFacade.searchGoodsMasterInfoAll(skipRows, goodsRows, true, makerCode,
            searchMode);
      } else if (form.getMode().intValue() == ModeEnum.Error.getValue()) {
        allNum = goodsMasterWorkFacade.searchGoodsMasterInfoAll(null, null, false, makerCode, importKbn)
            .getGoodsMasterDto().size();
        goodsMasterInfoDtoAll = goodsMasterWorkFacade.searchGoodsMasterInfoAll(skipRows, goodsRows, true, makerCode,
            importKbn);
      } else if (form.getMode().intValue() == ModeEnum.Select.getValue()) {
        // 全件数取得
        allNum = goodsMasterFacade.searchGoodsMasterInfoAll(null, null, false, makerCode, ModeEnum.Select.getValue())
            .getGoodsMasterDto().size();
        searchNum = goodsMasterFacade
            .searchGoodsMasterInfoAll(skipRows, -1L, true, makerCode, ModeEnum.Select.getValue()).getGoodsMasterDto()
            .size();
        goodsMasterInfoDtoAll = goodsMasterFacade.searchGoodsMasterInfoAll(skipRows, goodsRows, true, makerCode,
            ModeEnum.Select.getValue());
        goodsMasterInfoDtoAll = goodsMasterFacade.searchGoodsMasterInfoList(goodsMasterInfoDtoAll);
      }
      // 検索数
      result.put("searchNumInit", allNum);
      if (form.getMode().intValue() == ModeEnum.Select.getValue()) {
        result.put("searchNumInit", searchNum);
      }
      // 全件数
      result.put("allNumInit", allNum);

      getGoodsGridDto(goodsMasterInfoDtoAll, blCodeNameMap, selectCodeNameMap, partsNameMap, classifyGuideNameMap,
          form.getMode(), dataSourceList);
      result.put("gridData", getCheckList(dataSourceList, makerCode));

      // 初期プルダウン
      if (pageNo == 1) {
        // BLコード
        result.put("blCodeNameMap", blCodeNameMap);
        // セレクトコード
        result.put("selectCodeNameMap", selectCodeNameMap);
        // 層別
        result.put("partsNameMap", partsNameMap);
        // 申請状態
        result.put("applyConditionMap", applyConditionMap);
      }
      // messageMap
      result.put("messageMap", messageMap);

      result.put("maxRows", goodsRows);
      result.put("controlFlag", isControl);
      request.getSession().setAttribute(BroadleafSessionKeys.IMPORTKBN, importKbn);
      request.getSession().removeAttribute(BroadleafSessionKeys.GOODS_SEARCH_CONDITION);

    } catch (

    Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("商品一覧のグリッド初期表示処理を終了します。");
    return result;
  }

  /**
   * 申請状態を取得する
   * 
   * @param mode mode
   * @param applyConditionMap 申請状態
   */
  private void setApplyConditionMap(Integer mode, Map<Integer, String> applyConditionMap) {
    if (mode.intValue() == ModeEnum.Error.getValue() || mode.intValue() == ModeEnum.Reference.getValue()) {
      applyConditionMap.put(ApplyConditionEnum.NoApply.getValue(), ApplyConditionEnum.NoApply.getName());
    } else if (mode.intValue() == ModeEnum.Select.getValue()) {
      applyConditionMap.put(ApplyConditionEnum.NoApply.getValue(), ApplyConditionEnum.NoApply.getName());
      applyConditionMap.put(ApplyConditionEnum.Applyagain.getValue(), ApplyConditionEnum.Applyagain.getName());
    }
  }

  /**
   * getGoodsGridDto
   * 
   * @param goodsMasterInfoDtoAll goodsMasterInfoDtoAll
   * @param blCodeNameMap blCodeNameMap
   * @param selectCodeNameMap selectCodeNameMap
   * @param partsNameMap partsNameMap
   * @param classifyGuideNameMap classifyGuideNameMap
   * @param mode mode
   * @param dataSourceList dataSourceList
   */
  private void getGoodsGridDto(GoodsMasterMakerInfoDto goodsMasterInfoDtoAll, Map<String, String> blCodeNameMap,
                               Map<String, String> selectCodeNameMap, Map<String, String> partsNameMap,
                               Map<String, String> classifyGuideNameMap, Integer mode,
                               ArrayList<GoodsGridDto> dataSourceList) {
    int index = 1;
    if (goodsMasterInfoDtoAll != null) {
      for (GoodsMasterMakerDto goodsMasterMakerDto : goodsMasterInfoDtoAll.getGoodsMasterDto()) {
        GoodsGridDto goodsGridDto = convertGoodsGridDto(goodsMasterMakerDto, index, blCodeNameMap, selectCodeNameMap,
            partsNameMap, classifyGuideNameMap);
        index = index + 1;
        if (mode.intValue() == ModeEnum.Error.getValue()) {
          goodsGridDto.setCheck(true);
        } else if (mode.intValue() == ModeEnum.Select.getValue()) {
          goodsGridDto.setCheck(goodsMasterFacade.getCheckDiv(goodsMasterMakerDto));
        }
        dataSourceList.add(goodsGridDto);
      }
    }
  }

  /**
   * 検索
   * 
   * @param form ProductListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/search", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult onclickBtnSearch(@RequestBody ProductListForm form, HttpServletRequest request,
                                    HttpServletResponse response) {
    logger.debug("商品一覧の検索処理を開始します。");
    WebResult result = new WebResult();
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = loginPrincipal.getMakerCode();
    try {
      // 全件数
      int allNum = 0;
      // 検索数
      int searchNum = 0;

      GoodsMasterMakerInfoDto goodsMasterInfoDto = new GoodsMasterMakerInfoDto();
      ArrayList<GoodsGridDto> dataSourceList = new ArrayList<GoodsGridDto>();
      // 検索条件設定
      GoodsMasterSearchDto goodsMasterSearchDto = setGoodsMasterSearchDto(form);

      Long goodsRows = (long) goodsMasterFacade.searchGoodsListRows(makerCode);
      int pageNo = form.getPageNo() == null || form.getPageNo().isEmpty() ? 1 : Integer.parseInt(form.getPageNo());
      Long skipRows = (long) goodsRows * (pageNo - 1);
      short importKbn = form.getImportKbn();
      if (form.getMode() == ModeEnum.Input.getValue() || form.getMode() == ModeEnum.Reference.getValue()) {
        int searchMode = 0;
        if (form.getMode().intValue() == ModeEnum.Reference.getValue() && importKbn == 0) {
          searchMode = 2;
        } else if (form.getMode().intValue() == ModeEnum.Reference.getValue() && importKbn == 1) {
          searchMode = 4;
        }
        // 全件数取得
        allNum = goodsMasterFacade.searchGoodsMasterInfoAll(null, null, false, makerCode, searchMode)
            .getGoodsMasterDto().size();
        // 検索数取得
        searchNum = goodsMasterFacade
            .searchGoodsMasterInfoList(goodsMasterSearchDto, form.getOrder(), null, null, false, searchMode)
            .getGoodsMasterDto().size();
        // 検索結果取得
        goodsMasterInfoDto = goodsMasterFacade.searchGoodsMasterInfoList(goodsMasterSearchDto, form.getOrder(),
            skipRows, goodsRows, true, searchMode);

      } else if (form.getMode().intValue() == ModeEnum.Error.getValue()) {

        // 全件数取得
        allNum = goodsMasterWorkFacade.searchGoodsMasterInfoAll(null, null, false, makerCode, importKbn)
            .getGoodsMasterDto().size();
        // 検索数取得
        searchNum = goodsMasterWorkFacade
            .searchGoodsMasterInfoList(goodsMasterSearchDto, form.getOrder(), null, null, false, importKbn)
            .getGoodsMasterDto().size();
        // 検索結果取得
        goodsMasterInfoDto = goodsMasterWorkFacade.searchGoodsMasterInfoList(goodsMasterSearchDto, form.getOrder(),
            skipRows, goodsRows, true, importKbn);

      } else if (form.getMode().intValue() == ModeEnum.Select.getValue()) {
        // 全件数取得
        allNum = goodsMasterFacade.searchGoodsMasterInfoAll(null, null, false, makerCode, ModeEnum.Select.getValue())
            .getGoodsMasterDto().size();
        // 検索数取得
        searchNum = goodsMasterFacade.searchGoodsMasterInfoList(goodsMasterSearchDto, form.getOrder(), null, null,
            false, ModeEnum.Select.getValue()).getGoodsMasterDto().size();
        // 検索結果取得
        goodsMasterInfoDto = goodsMasterFacade.searchGoodsMasterInfoList(goodsMasterSearchDto, form.getOrder(),
            skipRows, goodsRows, true, ModeEnum.Select.getValue());
        goodsMasterInfoDto = goodsMasterFacade.searchGoodsMasterInfoList(goodsMasterInfoDto);
      }

      // BLコードリスト取得
      Map<String, String> blCodeNameMap = goodsMasterFacade.getBlCodeNameMap(makerCode);
      Map<String, String> selectCodeNameMap = goodsMasterFacade.getSelectCodeNameMap(makerCode);
      Map<String, String> partsNameMap = goodsMasterFacade.getPartsNameMap(makerCode);
      Map<String, String> classifyGuideNameMap = goodsMasterFacade.getClassifyCodeGuideMap(makerCode);

      int index = 1;
      // 商品一覧テータ設定
      for (GoodsMasterMakerDto goodsMasterMakerDto : goodsMasterInfoDto.getGoodsMasterDto()) {
        GoodsGridDto goodsGridDto = convertGoodsGridDto(goodsMasterMakerDto, index, blCodeNameMap, selectCodeNameMap,
            partsNameMap, classifyGuideNameMap);
        index = index + 1;
        if (form.getMode().intValue() == ModeEnum.Error.getValue()) {
          goodsGridDto.setCheck(true);
        } else if (form.getMode().intValue() == ModeEnum.Select.getValue()) {
          goodsGridDto.setCheck(goodsMasterFacade.getCheckDiv(goodsMasterMakerDto));
        }
        dataSourceList.add(goodsGridDto);
      }

      // 検索数
      result.put("searchNum", searchNum);
      // 全件数
      result.put("allNum", allNum);
      // 一覧テータ
      result.put("gridDataUpdate", getCheckList(dataSourceList, makerCode));
      result.put("maxRows", goodsRows);
      request.getSession().setAttribute(BroadleafSessionKeys.GOODS_SEARCH_CONDITION, goodsMasterSearchDto);
      request.getSession().setAttribute("goodsSearchOrder", form.getOrder());

    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("商品一覧の検索処理を終了します。");
    return result;
  }

  /**
   * 商品登録
   * 
   * @param form ProductListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/save", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult onclickBtnSave(@RequestBody ProductListForm form, HttpServletRequest request,
                                  HttpServletResponse response) {
    logger.debug("商品一覧の商品登録処理を開始します。");
    WebResult result = new WebResult();
    try {
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      int makerCode = loginPrincipal.getMakerCode();
      // 一覧テータ
      List<GoodsGridDto> dataSourceList = form.getGoodsGridDtoList();
      List<GoodsMasterMakerDto> goodsGridDtoUpdateList = new ArrayList<>();
      List<GoodsMasterMakerDto> goodsGridDtoInsertList = new ArrayList<>();
      List<GoodsMasterMakerDto> goodsGridDtoDeleteList = new ArrayList<>();

      // 項目チェック
      GoodsMasterMakerInfoDto goodsMasterInfoDto = goodsMasterFacade.checkImportList(dataSourceList, makerCode);
      Boolean isErrorExist = goodsMasterInfoDto.getIsErrorExist();

      if (!isErrorExist) {
        dataSourceList = new ArrayList<>();
        // 状態判断
        for (GoodsGridDto goodsGridDto : goodsMasterInfoDto.getGoodsGridDtoList()) {
          if (goodsGridDto.getHiddenArea().intValue() == JudgeEnum.Add.getValue()) {
            goodsGridDtoInsertList.add(convertGoodsMasterMakerDto(goodsGridDto));
            goodsGridDto.setHiddenArea(1);
            dataSourceList.add(goodsGridDto);
          } else if (goodsGridDto.getHiddenArea().intValue() == JudgeEnum.Delete.getValue()) {
            goodsGridDtoDeleteList.add(convertGoodsMasterMakerDto(goodsGridDto));
          } else if (goodsGridDto.getHiddenArea().intValue() == JudgeEnum.Update.getValue()) {
            if (goodsGridDto.getApplyStep().equals(ApplyConditionEnum.Approval.getAbbreviation())) {
              goodsGridDto.setApplyStep(ApplyConditionEnum.Applyagain.getAbbreviation());
            }
            goodsGridDto.setHiddenArea(1);
            dataSourceList.add(goodsGridDto);
            goodsGridDtoUpdateList.add(convertGoodsMasterMakerDto(goodsGridDto));
          } else {
            dataSourceList.add(goodsGridDto);
          }
        }

        // 商品マスタ(メーカー)情報更新.
        for (GoodsMasterMakerDto goodsMasterMakerDtoUpdate : goodsGridDtoUpdateList) {
          if (form.getMode().intValue() == ModeEnum.Input.getValue()) {
            goodsMasterFacade.updateGoodsMaster(goodsMasterMakerDtoUpdate);
          } else if (form.getMode().intValue() == ModeEnum.Error.getValue()) {
            goodsMasterWorkFacade.updateGoodsMaster(goodsMasterMakerDtoUpdate);
          }
        }
        // 商品マスタ(メーカー)削除
        for (GoodsMasterMakerDto goodsMasterMakerDtoDelete : goodsGridDtoDeleteList) {
          goodsMasterFacade.deleteGoodsInfo(goodsMasterMakerDtoDelete);
        }
        // 商品マスタ(メーカー)の登録.
        goodsMasterInfoDto.setGoodsMasterDto(goodsGridDtoInsertList);
        goodsMasterFacade.insertGoodsMasterInfoList(goodsMasterInfoDto);
      }

      // 一覧テータ更新
      result.put("gridDataUpdate", dataSourceList);
      result.put("isErrorExist", isErrorExist);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("商品一覧の商品登録処理を終了します。");
    return result;
  }

  /**
   * 商品申請機能
   * 
   * @param form ProductListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/certain", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult onclickBtnCertain(@RequestBody ProductListForm form, HttpServletRequest request,
                                     HttpServletResponse response) {
    logger.debug("商品一覧の商品申請機能処理を開始します。");
    WebResult result = new WebResult();
    try {
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      int makerCode = loginPrincipal.getMakerCode();
      for (GoodsGridDto goodsGridDto : form.getGoodsGridDtoList()) {
        goodsMasterFacade.manegeSelectMaker(goodsGridDto, makerCode);
      }
      result.put("selectSize", goodsMasterFacade.searchSelectBySelectKbn(makerCode));
      request.getSession().setAttribute("isSelected", Boolean.TRUE);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("商品一覧の商品申請機能処理を終了します。");
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
    logger.debug("商品一覧の帳票作成処理を開始します。");
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = loginPrincipal.getMakerCode();
    WebResult result = new WebResult();
    ExportOutModel exportOutModel = null;
    ReportOutController reportOutController = new ReportOutController();
    String isInit = request.getParameter("isInit");
    String fileType = request.getParameter("fileType");
    String mode = request.getParameter("mode");
    String fileName = getFileName(fileType);

    CheckListController checkListController = new CheckListController();
    List<GoodsGridDto> dataSourceList = new ArrayList<>();
    GoodsMasterMakerInfoDto goodsMasterInfoDto = new GoodsMasterMakerInfoDto();
    GoodsMasterSearchDto goodsMasterSearchDto = (GoodsMasterSearchDto) request.getSession()
        .getAttribute(BroadleafSessionKeys.GOODS_SEARCH_CONDITION);
    short importKbn = 0;
    if (!request.getParameter("importKbn").isEmpty()) {
      importKbn = Short.valueOf(request.getParameter("importKbn"));
    }
    if (mode.equals(String.valueOf(ModeEnum.Input.getValue()))
        || mode.equals(String.valueOf(ModeEnum.Reference.getValue()))) {
      // 検索結果取得
      // 検索条件
      int searchMode = 0;
      if (mode.equals(String.valueOf(ModeEnum.Reference.getValue())) && importKbn == 0) {
        searchMode = 2;
      } else if (mode.equals(String.valueOf(ModeEnum.Reference.getValue())) && importKbn == 1) {
        searchMode = 4;
      }
      if (isInit.equals("true")) {
        goodsMasterInfoDto = goodsMasterFacade.searchGoodsMasterInfoAll(null, null, false, makerCode, searchMode);
      } else {
        if (goodsMasterSearchDto != null) {
          int order = (int) request.getSession().getAttribute("goodsSearchOrder");
          goodsMasterInfoDto = goodsMasterFacade.searchGoodsMasterInfoList(goodsMasterSearchDto, order, null, null,
              false, searchMode);
        }
      }
    } else if (mode.equals(String.valueOf(ModeEnum.Error.getValue()))) {
      if (isInit.equals("true")) {
        goodsMasterInfoDto = goodsMasterWorkFacade.searchGoodsMasterInfoAll(null, null, false, makerCode, importKbn);
      } else {
        if (goodsMasterSearchDto != null) {
          int order = (int) request.getSession().getAttribute("goodsSearchOrder");
          goodsMasterInfoDto = goodsMasterWorkFacade.searchGoodsMasterInfoList(goodsMasterSearchDto, order, null, null,
              false, importKbn);
        }
      }
    }
    Map<String, String> blCodeNameMap = goodsMasterFacade.getBlCodeNameMap(makerCode);
    Map<String, String> selectCodeNameMap = goodsMasterFacade.getSelectCodeNameMap(makerCode);
    Map<String, String> partsNameMap = goodsMasterFacade.getPartsNameMap(makerCode);
    Map<String, String> classifyGuideNameMap = goodsMasterFacade.getClassifyCodeGuideMap(makerCode);

    int index = 1;
    for (GoodsMasterMakerDto goodsMasterMakerDto : goodsMasterInfoDto.getGoodsMasterDto()) {
      GoodsGridDto goodsGridDto = convertGoodsGridDto(goodsMasterMakerDto, index, blCodeNameMap, selectCodeNameMap,
          partsNameMap, classifyGuideNameMap);
      index = index + 1;
      dataSourceList.add(goodsGridDto);
    }
    exportOutModel = checkListController.makeGoodsFile(result, request, fileType, fileName, dataSourceList);

    try {
      reportOutController.reportOut(exportOutModel, response, result);
    } catch (Exception e) {
      e.printStackTrace();
    }
    logger.debug("商品一覧の帳票作成処理を終了します。");
    return result;
  }

  /**
   * 商品check
   * 
   * @param form ProductListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/check", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult check(@RequestBody ProductListForm form, HttpServletRequest request, HttpServletResponse response) {
    logger.debug("商品一覧の商品チェック処理を開始します。");
    WebResult result = new WebResult();
    try {
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      int makerCode = loginPrincipal.getMakerCode();
      // 一覧テータ
      List<GoodsGridDto> dataSourceList = form.getGoodsGridDtoList();
      // 一覧テータ更新
      result.put("gridDataUpdate", getCheckList(dataSourceList, makerCode));
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("商品一覧の商品チェック処理を終了します。");
    return result;
  }

  /**
   * 商品back
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/back", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult back(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("商品一覧の商品戻る処理を開始します。");
    WebResult result = new WebResult();
    try {
      request.getSession().setAttribute("isSelected", Boolean.TRUE);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("商品一覧の商品戻る処理を終了します。");
    return result;
  }

  /**
   * getCheckList
   * 
   * @param dataSourceList dataSourceList
   * @param makerCode makerCode
   * @return GoodsGridDto
   */
  private List<GoodsGridDto> getCheckList(List<GoodsGridDto> dataSourceList, int makerCode) {
    GoodsMasterMakerInfoDto goodsMasterInfoDto = goodsMasterFacade.checkImportList(dataSourceList, makerCode);
    return goodsMasterInfoDto.getGoodsGridDtoList();
  }

  /**
   * ファイル名を取得する。
   * 
   * @param fileType 出力ファイルタイプ。
   * @return ファイル名
   */
  private String getFileName(String fileType) {
    String fileName = "商品_".concat(BroadleafFormatUtils.dateToString(new Date(), BroadleafFormatUtils.DATE_F009));
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
   * 全データ表示
   */
  private static final String ALL = "9";
  /**
   * ERROR全データ表示
   */
  private static final String ERRORALL = "9";
  /**
   * EMPTY
   */
  private static final String EMPTY = "";

  /**
   * 検索条件
   * 
   * @param form ProductListForm
   * @return GoodsMasterSearchDto
   */
  private GoodsMasterSearchDto setGoodsMasterSearchDto(ProductListForm form) {
    GoodsMasterSearchDto goodsMasterSearchDto = new GoodsMasterSearchDto();
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = loginPrincipal.getMakerCode();
    goodsMasterSearchDto.setPartsMakerCd(makerCode);

    // 申請状態
    if (form.getApplyState().equals(ALL)) {
      goodsMasterSearchDto.setApplyCondition(null);
    } else {
      goodsMasterSearchDto.setApplyCondition(Short.valueOf(form.getApplyState()));
    }
    // 削除依頼区分
    if (form.getDeleteDiv().isEmpty()) {
      goodsMasterSearchDto.setDeleteFlg(null);
    } else {
      goodsMasterSearchDto.setDeleteFlg(Short.valueOf(form.getDeleteDiv()));
    }
    // 装備名称
    goodsMasterSearchDto.setEquipName(form.getEquipment());
    // データステータス
    if (form.getErrorDiv().equals(ERRORALL)) {
      goodsMasterSearchDto.setErrorFlg(null);
    } else {
      goodsMasterSearchDto.setErrorFlg(Short.valueOf(form.getErrorDiv()));
    }
    // 商品詳細(B向け)
    goodsMasterSearchDto.setGoodsDetailB(form.getGoodDetail());
    // 商品詳細(C向け)
    goodsMasterSearchDto.setGoodsDetailC(form.getGoodDetailCommon());
    // 分類コード
    if (form.getClassifyCd().isEmpty()) {
      goodsMasterSearchDto.setGoodsMGroup(null);
    } else {
      goodsMasterSearchDto.setGoodsMGroup(Integer.valueOf(form.getClassifyCd().split("：")[0]));
    }
    // 作成日END
    if (form.getInsertDateEnd().isEmpty()) {
      goodsMasterSearchDto.setInsDtTimeEnd(null);
    } else {
      goodsMasterSearchDto.setInsDtTimeEnd(BroadleafFormatUtils
          .stringToDate(form.getInsertDateEnd().concat("　23:59:59"), BroadleafFormatUtils.DATE_F007));
    }
    // 作成日START
    if (form.getInsertDateStart().isEmpty()) {
      goodsMasterSearchDto.setInsDtTimeStart(null);
    } else {
      goodsMasterSearchDto.setInsDtTimeStart(BroadleafFormatUtils
          .stringToDate(form.getInsertDateStart().concat("　00:00:00"), BroadleafFormatUtils.DATE_F007));
    }
    // 処理区分
    if (form.getProcessdiv().equals(ALL)) {
      goodsMasterSearchDto.setManageKbn(null);
    } else {
      goodsMasterSearchDto.setManageKbn(Short.valueOf(form.getProcessdiv()));
    }
    // OPENプランス
    if (form.getPriceClass().isEmpty()) {
      goodsMasterSearchDto.setOpenPriceDiv(null);
    } else {
      goodsMasterSearchDto.setOpenPriceDiv(Integer.valueOf(form.getPriceClass()));
    }
    // 層別コード
    goodsMasterSearchDto.setPartsLayerCd(form.getOldCd());
    // 優良部品カナ名称
    goodsMasterSearchDto.setPrimePartsKanaNm(form.getGoodsName());
    // 優良品番(－付き品番)
    goodsMasterSearchDto.setPrimePartsNoWithH(form.getGoodsCd());
    // 優良部品規格・特記事項(B向け)
    goodsMasterSearchDto.setPrimePartsSpecialNoteB(form.getPrimePartsSpecialNoteRFB());
    // 優良部品規格・特記事項(C向け)
    goodsMasterSearchDto.setPrimePartsSpecialNoteC(form.getPrimePartsSpecialNoteRFC());
    // 優良設定詳細コード１
    if (form.getSelectCd().isEmpty()) {
      goodsMasterSearchDto.setPrmSetDtlNo1(null);
    } else {
      goodsMasterSearchDto.setPrmSetDtlNo1(Integer.valueOf(form.getSelectCd()));
    }
    setGoodsMasterSearchDto(form, goodsMasterSearchDto);
    return goodsMasterSearchDto;
  }

  /**
   * 検索条件
   * 
   * @param form ProductListForm
   * @param goodsMasterSearchDto GoodsMasterSearchDto
   */
  private void setGoodsMasterSearchDto(ProductListForm form, GoodsMasterSearchDto goodsMasterSearchDto) {
    // 適用日END
    if (form.getApplyDateEnd().isEmpty()) {
      goodsMasterSearchDto.setStartDateEnd(null);
    } else {
      goodsMasterSearchDto.setStartDateEnd(new Timestamp(BroadleafFormatUtils
          .stringToDate(form.getApplyDateEnd().concat("　23:59:59"), BroadleafFormatUtils.DATE_F007).getTime()));
    }
    // 適用日start
    if (form.getApplyDateStart().isEmpty()) {
      goodsMasterSearchDto.setStartDateStart(null);
    } else {
      goodsMasterSearchDto.setStartDateStart(new Timestamp(BroadleafFormatUtils
          .stringToDate(form.getApplyDateStart().concat("　00:00:00"), BroadleafFormatUtils.DATE_F007).getTime()));
    }
    // BLコード
    if (form.getBlCd().isEmpty()) {
      goodsMasterSearchDto.setTbsPartsCode(null);
    } else {
      goodsMasterSearchDto.setTbsPartsCode(Integer.valueOf(form.getBlCd()));
    }
    // 更新日END
    if (form.getUpdateDateEnd().isEmpty()) {
      goodsMasterSearchDto.setUpdDtTimeEnd(null);
    } else {
      goodsMasterSearchDto.setUpdDtTimeEnd(BroadleafFormatUtils
          .stringToDate(form.getUpdateDateEnd().concat("　23:59:59"), BroadleafFormatUtils.DATE_F007));
    }
    // 更新日START
    if (form.getUpdateDateStart().isEmpty()) {
      goodsMasterSearchDto.setUpdDtTimeStart(null);
    } else {
      goodsMasterSearchDto.setUpdDtTimeStart(BroadleafFormatUtils
          .stringToDate(form.getUpdateDateStart().concat("　00:00:00"), BroadleafFormatUtils.DATE_F007));
    }
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
        : BroadleafFormatUtils.dateToString(goodsMasterMakerDto.getInsDtTime(), BroadleafFormatUtils.DATE_F004));
    // 更新日
    goodsGridDto.setDateRe(goodsMasterMakerDto.getUpdDtTime() == null ? EMPTY
        : BroadleafFormatUtils.dateToString(goodsMasterMakerDto.getUpdDtTime(), BroadleafFormatUtils.DATE_F004));
    // 適用日
    goodsGridDto.setDateSlice(goodsMasterMakerDto.getStartDate() == null ? EMPTY
        : BroadleafFormatUtils.timestampToString(goodsMasterMakerDto.getStartDate(), BroadleafFormatUtils.DATE_F004));
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
    goodsGridDto.setMoney(goodsMasterMakerDto.getNewPrice() == null ? null
        : goodsMasterFacade.getPrice(goodsMasterMakerDto.getNewPrice().toString()));
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
        : goodsMasterFacade.getPrice(goodsMasterMakerDto.getPackageSize1().toString()));
    // 梱包（幅）
    goodsGridDto.setPackwidth2(goodsMasterMakerDto.getPackageSize2() == null ? null
        : goodsMasterFacade.getPrice(goodsMasterMakerDto.getPackageSize2().toString()));
    // 梱包（高さ）
    goodsGridDto.setPackwidth3(goodsMasterMakerDto.getPackageSize3() == null ? null
        : goodsMasterFacade.getPrice(goodsMasterMakerDto.getPackageSize3().toString()));
    // 分類コード
    goodsGridDto.setSecCodeName(classifyGuideNameMap.get(String.valueOf(goodsMasterMakerDto.getGoodsMGroup())));
    // セレクトコード名称
    goodsGridDto.setSelCode(goodsMasterMakerDto.getPrmSetDtlNo1() == 9999 ? "9999：指定無し"
        : selectCodeNameMap.get(String.valueOf(goodsMasterMakerDto.getPrmSetDtlNo1())));
    // 規格/特記
    goodsGridDto.setSize(goodsMasterMakerDto.getPrimePartsSpecialNoteB());
    // 規格/特記（一般）
    goodsGridDto.setSizeCon(goodsMasterMakerDto.getPrimePartsSpecialNoteC());
    convertGoodsGridDto1(goodsMasterMakerDto, goodsGridDto);
    return goodsGridDto;
  }

  /**
   * gridのdata
   * 
   * @param goodsMasterMakerDto GoodsMasterMakerDto
   * @param goodsGridDto GoodsGridDto
   */
  private void convertGoodsGridDto1(GoodsMasterMakerDto goodsMasterMakerDto, GoodsGridDto goodsGridDto) {
    // URL1
    goodsGridDto.setUrl1(goodsMasterMakerDto.getUrl1());
    // URL2
    goodsGridDto.setUrl2(goodsMasterMakerDto.getUrl2());
    // URL3
    goodsGridDto.setUrl3(goodsMasterMakerDto.getUrl3());
    // 重量
    goodsGridDto.setWeight(goodsMasterMakerDto.getGoodsWeight() == null ? null
        : goodsMasterFacade.getPrice(goodsMasterMakerDto.getGoodsWeight().toString()));
    // 重量単位
    goodsGridDto.setWeightUnit(WeightUnitEnum.valueof(goodsMasterMakerDto.getWeightUnit()).getName());
    // 長さ
    goodsGridDto.setWidth1(goodsMasterMakerDto.getGoodsSize1() == null ? null
        : goodsMasterFacade.getPrice(goodsMasterMakerDto.getGoodsSize1().toString()));
    // 幅
    goodsGridDto.setWidth2(goodsMasterMakerDto.getGoodsSize2() == null ? null
        : goodsMasterFacade.getPrice(goodsMasterMakerDto.getGoodsSize2().toString()));
    // 高さ
    goodsGridDto.setWidth3(goodsMasterMakerDto.getGoodsSize3() == null ? null
        : goodsMasterFacade.getPrice(goodsMasterMakerDto.getGoodsSize3().toString()));
    // サイズ単位
    goodsGridDto.setWidthUnit(SizeUnitEnum.valueof(goodsMasterMakerDto.getSizeUnit()).getName());
    // 隠匿域
    goodsGridDto.setHiddenArea(JudgeEnum.UnChange.getValue());
    // インポート区分
    goodsGridDto.setImportKbn(Integer.valueOf(goodsMasterMakerDto.getImportKbn()));
    // 履歴比較のフラグ
    goodsGridDto.setCompareFlag(goodsMasterMakerDto.getCompareFlag());
  }

  /**
   * 登録 entity
   * 
   * @param goodsGridDto GoodsGridDto
   * @return GoodsMasterMakerDto
   */
  private GoodsMasterMakerDto convertGoodsMasterMakerDto(GoodsGridDto goodsGridDto) {
    GoodsMasterMakerDto goodsMasterDto = new GoodsMasterMakerDto();
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = loginPrincipal.getMakerCode();
    // 部品メーカーコード
    goodsMasterDto.setPartsMakerCd(makerCode);
    // 更新アカウントID
    goodsMasterDto.setUpdAccountId(loginPrincipal.getLoginId());
    // 優良品番(－付き品番)
    goodsMasterDto.setPrimePartsNoWithH(goodsGridDto.getGoodsNo());
    // 優良設定詳細コード１
    goodsMasterDto.setPrmSetDtlNo1(
        goodsGridDto.getSelCode() == null ? 9999 : Integer.parseInt(goodsGridDto.getSelCode().split("：")[0]));

    // BL登録区分
    goodsMasterDto.setBlEntryFlg((short) BlEntryFlgEnum.getCode(goodsGridDto.getBlSec()));
    // チェック区分
    goodsMasterDto.setCheckFlg((short) CheckFlgEnum.getCode(goodsGridDto.getCheckSec()));
    // 削除依頼区分
    goodsMasterDto.setDeleteFlg(goodsGridDto.getDelSec() == null ? null
        : (short) DeleteFlgEnum.getCodeByAbbreviation(goodsGridDto.getDelSec()));
    // 削除理由
    goodsMasterDto.setDeleteReason(goodsGridDto.getDelCon());
    // 装備名称
    goodsMasterDto.setEquipName(goodsGridDto.getEquip());
    // エラー内容
    goodsMasterDto.setErrorDetail(goodsGridDto.getErrCon());
    // データステータス
    goodsMasterDto.setErrorFlg((short) ErrorFlgEnum.getCode(goodsGridDto.getErrSec()));
    // 商品詳細(B向け)
    goodsMasterDto.setGoodsDetailB(goodsGridDto.getGoods());
    // 商品詳細(C向け)
    goodsMasterDto.setGoodsDetailC(goodsGridDto.getGoodsCon());
    // 商品中分類コード
    goodsMasterDto.setGoodsMGroup(goodsGridDto.getSecCodeName() == null || goodsGridDto.getSecCodeName().isEmpty()
        ? null : Integer.valueOf(goodsGridDto.getSecCodeName().split("：")[0]));
    // 商品サイズ(長さ）
    goodsMasterDto.setGoodsSize1(goodsGridDto.getWidth1() == null || goodsGridDto.getWidth1().isEmpty() ? null
        : Integer.valueOf(goodsGridDto.getWidth1().replace(",", "")));
    // 商品サイズ(幅）
    goodsMasterDto.setGoodsSize2(goodsGridDto.getWidth2() == null || goodsGridDto.getWidth2().isEmpty() ? null
        : Integer.valueOf(goodsGridDto.getWidth2().replace(",", "")));
    // 商品サイズ(高さ）
    goodsMasterDto.setGoodsSize3(goodsGridDto.getWidth3() == null || goodsGridDto.getWidth3().isEmpty() ? null
        : Integer.valueOf(goodsGridDto.getWidth3().replace(",", "")));
    // 商品重量
    goodsMasterDto.setGoodsWeight(goodsGridDto.getWeight() == null || goodsGridDto.getWeight().isEmpty() ? null
        : Integer.valueOf(goodsGridDto.getWeight().replace(",", "")));
    // 画像数
    if (goodsGridDto.getImg() != null) {
      goodsMasterDto
          .setImageNo(goodsGridDto.getImg().equals("×") ? (short) 0 : Short.parseShort(goodsGridDto.getImg()));
    } else {
      goodsMasterDto.setImageNo((short) 0);
    }
    // インポート区分
    goodsMasterDto.setImportKbn((short) 2);
    // JAN
    goodsMasterDto.setJan(
        goodsGridDto.getJan() == null || goodsGridDto.getJan().isEmpty() ? null : Long.valueOf(goodsGridDto.getJan()));
    // 処理区分
    goodsMasterDto.setManageKbn(
        goodsGridDto.getManageSec() == null ? null : (short) ManageKbnEnum.getCode(goodsGridDto.getManageSec()));
    // 新価格
    goodsMasterDto.setNewPrice(goodsGridDto.getMoney() == null || goodsGridDto.getMoney().isEmpty() ? null
        : Double.valueOf(goodsGridDto.getMoney().replace(",", "")));
    // オープン価格区分
    goodsMasterDto.setOpenPriceDiv(goodsGridDto.getOpen() == null || goodsGridDto.getOpen().isEmpty() ? null
        : OpenPriceDivEnum.getCode(goodsGridDto.getOpen()));
    // 梱包サイズ(長さ）
    goodsMasterDto.setPackageSize1(goodsGridDto.getPackwidth1() == null || goodsGridDto.getPackwidth1().isEmpty() ? null
        : Integer.valueOf(goodsGridDto.getPackwidth1().replace(",", "")));
    // 梱包サイズ(幅）
    goodsMasterDto.setPackageSize2(goodsGridDto.getPackwidth2() == null || goodsGridDto.getPackwidth2().isEmpty() ? null
        : Integer.valueOf(goodsGridDto.getPackwidth2().replace(",", "")));
    // 梱包サイズ(高さ）
    goodsMasterDto.setPackageSize3(goodsGridDto.getPackwidth3() == null || goodsGridDto.getPackwidth3().isEmpty() ? null
        : Integer.valueOf(goodsGridDto.getPackwidth3().replace(",", "")));
    convertGoodsMasterMakerDto1(goodsMasterDto, goodsGridDto);
    return goodsMasterDto;
  }

  /**
   * 登録 entity
   * 
   * @param goodsGridDto GoodsGridDto
   * @param goodsMasterDto GoodsMasterMakerDto
   */
  private void convertGoodsMasterMakerDto1(GoodsMasterMakerDto goodsMasterDto, GoodsGridDto goodsGridDto) {
    // 申請状態
    if (goodsGridDto.getApplyStep().equals(ApplyConditionEnum.NoApply.getAbbreviation())) {
      goodsMasterDto.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
    } else if (goodsGridDto.getApplyStep().equals(ApplyConditionEnum.Approval.getAbbreviation())
        || goodsGridDto.getApplyStep().equals(ApplyConditionEnum.Applyagain.getAbbreviation())) {
      goodsMasterDto.setApplyCondition((short) ApplyConditionEnum.Applyagain.getValue());
    }

    // 層別コード
    goodsMasterDto.setPartsLayerCd(goodsGridDto.getLayer() == null ? null : goodsGridDto.getLayer().split("：")[0]);
    // 優良部品カナ名称
    goodsMasterDto.setPrimePartsKanaNm(goodsGridDto.getNameS());
    // 優良部品名称
    goodsMasterDto.setPrimePartsName(goodsGridDto.getNameB());
    // 優良部品規格・特記事項(C向け)
    goodsMasterDto.setPrimePartsSpecialNoteC(goodsGridDto.getSizeCon());
    // 優良部品規格・特記事項(B向け)
    goodsMasterDto.setPrimePartsSpecialNoteB(goodsGridDto.getSize());
    // サイズ単位
    goodsMasterDto.setSizeUnit(SizeUnitEnum.getCode(goodsGridDto.getWidthUnit()));
    // 適用日付
    goodsMasterDto
        .setStartDate(goodsGridDto.getDateSlice() == null ? null
            : new Timestamp(
                BroadleafFormatUtils.stringToDate(goodsGridDto.getDateSlice().substring(0, 10).replace("/", "-"),
                    BroadleafFormatUtils.DATE_F003).getTime()));
    // Timestamp.valueOf(goodsGridDto.getDateSlice().replace("T",
    // "").substring(0, 19)));
    // URL1
    goodsMasterDto.setUrl1(goodsGridDto.getUrl1());
    // URL2
    goodsMasterDto.setUrl2(goodsGridDto.getUrl2());
    // URL3
    goodsMasterDto.setUrl3(goodsGridDto.getUrl3());
    // 重量単位
    goodsMasterDto.setWeightUnit(WeightUnitEnum.getCode(goodsGridDto.getWeightUnit()));
    // BLコード
    goodsMasterDto.setTbsPartsCode(goodsGridDto.getBlCodeName() == null || goodsGridDto.getBlCodeName().isEmpty() ? null
        : Integer.valueOf(goodsGridDto.getBlCodeName().split("：")[0]));
  }

  // --------------- add by liangsd ------------------>>>
  /**
   * 商品登録realtime
   * 
   * @param form ProductListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/saveRealTime", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult onSaveRealTime(@RequestBody ProductListForm form, HttpServletRequest request,
                                  HttpServletResponse response) {
    logger.debug("商品一覧の商品登録リアルタイム保存処理を開始します。");
    WebResult result = new WebResult();
    try {
      // 一覧テータ
      List<GoodsGridDto> dataSourceList = form.getGoodsGridDtoList();
      int mode = form.getMode();
      List<GoodsMasterMakerDto> goodsMasterDtoList = new ArrayList<>();
      if (mode == 1) {
        if (dataSourceList.size() > 0) {
          for (GoodsGridDto goodsGridDto : form.getGoodsGridDtoList()) {
            goodsMasterDtoList.add(convertGoodsMasterMakerDto(goodsGridDto));
          }

          goodsMasterFacade.saveRealTime(goodsMasterDtoList, mode);
        }
      } else if (mode == 2) {
        GoodsMasterMakerDto goodsMasterMakerDto = new GoodsMasterMakerDto();
        LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();

        goodsMasterMakerDto.setUpdAccountId(loginPrincipal.getLoginId());
        goodsMasterDtoList.add(goodsMasterMakerDto);
        goodsMasterFacade.saveRealTime(goodsMasterDtoList, mode);
      }

    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("商品一覧の商品登録リアルタイム保存処理を終了します。");
    return result;
  }

  // --------------- add by liangsd ------------------<<<
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
    messageIdList.add(BregMessageCodes.E00004);
    messageIdList.add(BregMessageCodes.E00008);
    messageIdList.add(BregMessageCodes.E00009);
    messageIdList.add(BregMessageCodes.E00010);
    messageIdList.add(BregMessageCodes.E00011);
    messageIdList.add(BregMessageCodes.E00012);
    messageIdList.add(BregMessageCodes.E00013);
    messageIdList.add(BregMessageCodes.E00014);
    messageIdList.add(BregMessageCodes.E00015);
    messageIdList.add(BregMessageCodes.E00016);
    messageIdList.add(BregMessageCodes.E00018);
    messageIdList.add(BregMessageCodes.Q00001);
    messageIdList.add(BregMessageCodes.Q00001);
    messageIdList.add(BregMessageCodes.Q00001);
    messageIdList.add(BregMessageCodes.Q00001);
    messageIdList.add(BregMessageCodes.Q00001);
    messageIdList.add(BregMessageCodes.I00001);
    return messageFacade.getMessageContentsMap(messageIdList, messageMapAll);
  }
}
