//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : tangyp
// 作 成 日       2017/02/10   修正内容 : 申請履歴:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.applyhistory.controller;

import jp.co.broadleaf.breg.applyhistory.enums.ApplyHistoryModeEnum;
import jp.co.broadleaf.breg.applyhistory.facade.ApplyHistoryCommonFacade;
import jp.co.broadleaf.breg.applyhistory.facade.dto.ApplyHistoryCommonInfoDto;
import jp.co.broadleaf.breg.applyhistory.facade.dto.ApplyHistorySearchDto;
import jp.co.broadleaf.breg.applynewcategory.enums.ApplyNewCategoryModeEnum;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.ApplyTypeEnum;
import jp.co.broadleaf.breg.common.enums.BlApplyResultsFlgEnum;
import jp.co.broadleaf.breg.message.service.Facade.MessageFacade;
import jp.co.broadleaf.breg.web.applyhistory.form.ApplyHistorySearchForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * 申請履歴一覧Controllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/applyhistory")
public class ApplyHistoryController extends BaseController {
  /** ログイン画面 */
  private static final String APPLYHISTORY_URL = "applyhistory/apply_history";

  /** 申請履歴Facade */
  private ApplyHistoryCommonFacade applyHistoryCommonFacade;

  /**
   * <pre>
   * 申請履歴Facadeを設定する。
   * </pre>
   * 
   * @param applyHistoryCommonFacade 申請履歴Facade
   */
  @Resource
  public void setApplyHistoryCommonFacade(ApplyHistoryCommonFacade applyHistoryCommonFacade) {
    this.applyHistoryCommonFacade = applyHistoryCommonFacade;
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
  @RequestMapping(path = "/apply_history", method = RequestMethod.GET)
  @Submission(multiply = false)
  public ModelAndView applyHistoryInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("申請履歴一覧の初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(APPLYHISTORY_URL);

    int makerCd = 0;
    LoginPrincipal principal = SecurityContextHolder.getPrincipal();
    if (null != principal) {
      makerCd = principal.getMakerCode();
    }
    // BLコードマスタを取得します。
    Map<Integer, String> blCodeInfo = applyHistoryCommonFacade.getBlCodeInfoMap(makerCd);
    result.addObject("blcodeinfo", blCodeInfo);

    // カーコード
    Map<Integer, String> carmakerNameMap = applyHistoryCommonFacade.getCarmakerNameMap(makerCd);
    result.addObject("carmakerinfo", carmakerNameMap);

    // ステータス
    Map<Integer, String> applyStatus = getApplyStatus();
    result.addObject("applystatus", applyStatus);

    // 申請種類
    Map<Integer, String> applyType = getApplyType();
    result.addObject("applytype", applyType);
    logger.debug("申請履歴一覧の初期表示処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 初期表示時。
   * </pre>
   * 
   * @param form ApplyHistorySearchForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/gridinit", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult setApplyHistoryGridInit(@RequestBody ApplyHistorySearchForm form, HttpServletRequest request,
                                           HttpServletResponse response) {
    logger.debug("申請履歴一覧のグリッド初期表示処理を開始します。");
    WebResult result = new WebResult();

    // 起動方式
    int mode = form.getMode();

    // 起動方式１の場合
    if (ApplyHistoryModeEnum.mode1.getValue() == mode) {
      // ステータス
      form.setBlApplyResultsFlg(String.valueOf(BlApplyResultsFlgEnum.Apply.getValue()));
      result.put("status", BlApplyResultsFlgEnum.Apply.getValue());
      // 申請種類
      form.setApplyType("2");
      result.put("applytype", "2");
    } else if (ApplyHistoryModeEnum.mode2.getValue() == mode) {
      // ステータス
      form.setBlApplyResultsFlg("");
      result.put("status", "0");
      // 申請種類
      form.setApplyType("2");
      result.put("applytype", "2");
    } else if (ApplyHistoryModeEnum.mode3.getValue() == mode) {
      // ステータス
      form.setBlApplyResultsFlg(String.valueOf(BlApplyResultsFlgEnum.Apply.getValue()));
      result.put("status", BlApplyResultsFlgEnum.Apply.getValue());
      // 申請種類
      form.setApplyType(String.valueOf(ApplyTypeEnum.NewItem.getValue()));
      result.put("applytype", ApplyTypeEnum.NewItem.getValue());
    } else if (ApplyHistoryModeEnum.mode4.getValue() == mode || ApplyHistoryModeEnum.mode5.getValue() == mode) {
      // ステータス
      form.setBlApplyResultsFlg(String.valueOf(BlApplyResultsFlgEnum.Apply.getValue()));
      result.put("status", BlApplyResultsFlgEnum.Apply.getValue());
      // 申請種類
      form.setApplyType(String.valueOf(ApplyTypeEnum.GenerApply.getValue()));
      result.put("applytype", ApplyTypeEnum.GenerApply.getValue());
    }

    // 表示順
    form.setApplySort("2");
    // 当前頁
    form.setPage(1);
    // メーカコード
    int makerCd = 0;
    LoginPrincipal principal = SecurityContextHolder.getPrincipal();
    if (null != principal) {
      makerCd = principal.getMakerCode();
    }
    form.setMakerCd(makerCd);

    // 申請履歴ページ内行数
    int historyRows = applyHistoryCommonFacade.searchHistoryRows(makerCd);

    ApplyHistorySearchDto search = getSearchDto(form);
    search.setRows(historyRows);

    ApplyHistoryCommonInfoDto dataSourceList = applyHistoryCommonFacade.searchApplyHistoryInfoList(search);

    /** MessageMap **/
    Map<String, String> messageMap = getMessageMap(principal.getMessageMap());
    result.put("message", messageMap);

    // 申請履歴ページ内行数
    result.put("historyRows", historyRows);
    // 申請履歴一覧
    result.put("initList", dataSourceList.getApplyHistoryCommonDto());
    // 申請中件数
    result.put("applyCount", dataSourceList.getApplyNum());
    // 承認済件数
    result.put("approvalCount", dataSourceList.getApprovalNum());
    // 検索件数
    result.put("searchCount", dataSourceList.getSearchNum());
    // 全件数
    result.put("totalCount", dataSourceList.getTotalNum());
    logger.debug("申請履歴一覧のグリッド初期表示処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 検索ボタン処理。
   * </pre>
   * 
   * @param form ApplyHistorySearchForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/search", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult btnSearch(@RequestBody ApplyHistorySearchForm form, HttpServletRequest request,
                             HttpServletResponse response) {
    logger.debug("申請履歴一覧の検索ボタン処理を開始します。");
    // メーカコード
    int makerCd = 0;
    LoginPrincipal principal = SecurityContextHolder.getPrincipal();
    if (null != principal) {
      makerCd = principal.getMakerCode();
    }
    form.setMakerCd(makerCd);
    ApplyHistorySearchDto search = getSearchDto(form);

    int historyRows = applyHistoryCommonFacade.searchHistoryRows(makerCd);
    search.setRows(historyRows);
    WebResult result = new WebResult();
    ApplyHistoryCommonInfoDto dataSourceList = applyHistoryCommonFacade.searchApplyHistoryInfoList(search);

    /** MessageMap **/
    Map<String, String> messageMap = getMessageMap(principal.getMessageMap());
    result.put("message", messageMap);

    // 申請履歴ページ内行数
    result.put("historyRows", historyRows);
    // 申請履歴一覧
    result.put("initList", dataSourceList.getApplyHistoryCommonDto());
    // 申請中件数
    result.put("applyCount", dataSourceList.getApplyNum());
    // 承認済件数
    result.put("approvalCount", dataSourceList.getApprovalNum());
    // 検索件数
    result.put("searchCount", dataSourceList.getSearchNum());
    // 全件数
    result.put("totalCount", dataSourceList.getTotalNum());
    logger.debug("申請履歴一覧の検索ボタン処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 申請詳細画面へ。
   * </pre>
   * 
   * @param form 申請詳細画面用申請ID
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/todetail", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult applyDetailInit(@RequestBody ApplyHistorySearchForm form, HttpServletRequest request,
                                   HttpServletResponse response) {
    logger.debug("申請履歴一覧の申請詳細画面モード設定処理を開始します。");
    WebResult result = new WebResult();
    BlApplyResultsFlgEnum[] flgCodeList = BlApplyResultsFlgEnum.values();

    if (null == form.getBlApplyResultsFlg() || form.getBlApplyResultsFlg().isEmpty()) {
      form.setBlApplyResultsFlg(BlApplyResultsFlgEnum.Apply.getName());
    }
    for (BlApplyResultsFlgEnum flgcode : flgCodeList) {
      if (flgcode.getName().equals(form.getBlApplyResultsFlg())) {
        form.setBlApplyResultsFlg(String.valueOf(flgcode.getValue()));
      }
    }
    // 申請詳細画面用フォーム をセッションにセートします
    request.getSession().setAttribute(BroadleafSessionKeys.APPLY_DETAIL_KEY, form);
    logger.debug("申請履歴一覧の申請詳細画面モード設定処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 申請(新規品目)画面へ。
   * </pre>
   * 
   * @param form 申請(新規品目)画面用申請ID
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/tonewitem", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult applyNewItemInit(@RequestBody ApplyHistorySearchForm form, HttpServletRequest request,
                                    HttpServletResponse response) {
    logger.debug("申請履歴一覧の申請(新規品目)画面モード設定処理を開始します。");
    WebResult result = new WebResult();

    // BL申請結果区分
    if (null == form.getBlApplyResultsFlg() || form.getBlApplyResultsFlg().isEmpty()) {
      form.setBlApplyResultsFlg(BlApplyResultsFlgEnum.Apply.getName());
    }

    BlApplyResultsFlgEnum[] flgCodeList = BlApplyResultsFlgEnum.values();
    for (BlApplyResultsFlgEnum flgcode : flgCodeList) {
      if (flgcode.getName().equals(form.getBlApplyResultsFlg())) {
        form.setBlApplyResultsFlg(String.valueOf(flgcode.getValue()));
      }
    }
    // 起動モード(申請(新規品目))
    if (String.valueOf(BlApplyResultsFlgEnum.Reject.getValue()).equals(form.getBlApplyResultsFlg())) {
      form.setModeForNewItem(ApplyNewCategoryModeEnum.mode3.getValue());
    } else {
      form.setModeForNewItem(ApplyNewCategoryModeEnum.mode2.getValue());
    }

    // メーカコード
    int makerCd = 0;
    LoginPrincipal principal = SecurityContextHolder.getPrincipal();
    if (null != principal) {
      makerCd = principal.getMakerCode();
    }
    form.setMakerCd(makerCd);

    // 申請(新規品目)画面用フォーム をセッションにセートします
    request.getSession().setAttribute(BroadleafSessionKeys.APPLY_NEWITEM_KEY, form);
    logger.debug("申請履歴一覧の申請(新規品目)画面モード設定処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 累積情報画面へ。
   * </pre>
   * 
   * @param form 累積情報用フォーム
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/totalinfo", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult totalInfoInit(@RequestBody ApplyHistorySearchForm form, HttpServletRequest request,
                                 HttpServletResponse response) {
    logger.debug("申請履歴一覧の累積情報画面モード設定処理を開始します。");
    WebResult result = new WebResult();
    // 累積情報用フォーム をセッションにセートします
    request.getSession().setAttribute(BroadleafSessionKeys.TOTAL_INFO_KEY, form);
    logger.debug("申請履歴一覧の累積情報画面モード設定処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 純正品番
   * </pre>
   * 
   * @param form JoinListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @ResponseBody
  @Submission(multiply = false)
  @RequestMapping(path = "/joinSourPartsNoWithH", method = RequestMethod.POST)
  public WebResult joinSourPartsNoWithH(@RequestBody ApplyHistorySearchForm form, HttpServletRequest request,
                                        HttpServletResponse response) {
    logger.debug("申請履歴一覧の純正品番設定処理を開始します。");
    WebResult result = new WebResult();
    request.getSession().setAttribute(BroadleafSessionKeys.GOODS_GUIDE, form.getJoinSourPartsNoWithH());
    request.getSession().setAttribute(BroadleafSessionKeys.GOODS_CATEGORY, "pure");
    logger.debug("申請履歴一覧の純正品番設定処理を終了します。");
    return result;
  }

  /**
   * ステータスのデータを転換
   * 
   * @return ステータスのデータ
   */
  private Map<Integer, String> getApplyStatus() {
    Map<Integer, String> statusMap = new HashMap<Integer, String>();
    ApplyConditionEnum[] statusCodeList = ApplyConditionEnum.values();
    if (statusCodeList != null) {
      for (ApplyConditionEnum statusCode : statusCodeList) {
        if (statusCode.getValue() == ApplyConditionEnum.Apply.getValue()
            || statusCode.getValue() == ApplyConditionEnum.Approval.getValue()) {
          statusMap.put(statusCode.getValue(), statusCode.getName());
        }
      }
    }

    return statusMap;
  }

  /**
   * 申請種類のデータを転換
   * 
   * @return 申請種類のデータ
   */
  private Map<Integer, String> getApplyType() {
    Map<Integer, String> applyTypeMap = new HashMap<Integer, String>();
    ApplyTypeEnum[] typeList = ApplyTypeEnum.values();
    if (typeList != null) {
      for (ApplyTypeEnum type : typeList) {
        applyTypeMap.put(type.getValue(), type.getName());
      }
    }
    return applyTypeMap;
  }

  /**
   * formのデータを転換
   * 
   * @param form ApplyHistorySearchForm
   * @return applyHistorySearchDto
   */
  private ApplyHistorySearchDto getSearchDto(ApplyHistorySearchForm form) {
    if (form == null) {
      return null;
    }
    ApplyHistorySearchDto search = new ApplyHistorySearchDto();
    // 申請ID
    search.setApplyNo(form.getApplyNo());
    // ステータス
    search.setBlApplyResultsFlg(form.getBlApplyResultsFlg());
    // 申請期間（FROM）
    search.setApplyDateTimeFrom(form.getApplyDateTimeFrom());
    // 申請期間（TO）
    search.setApplyDateTimeTo(form.getApplyDateTimeTo());
    // BLコード
    search.setTbsPartsCode(form.getTbsPartsCode());
    // 優良品番
    search.setPrimePartsNo(form.getPrimePartsNo());
    // 申請種類
    search.setApplyType(form.getApplyType());
    // カーコード
    search.setPartsMakerCd(form.getPartsMakerCd());
    // 純正品番
    search.setJoinSourPartsNoWithH(null == form.getJoinSourPartsNoWithH() || form.getJoinSourPartsNoWithH().isEmpty()
        ? null : form.getJoinSourPartsNoWithH().split("：")[0]);
    // 表示順
    search.setApplySort(form.getApplySort());
    // 当前ページ
    search.setPage(form.getPage());
    // メーカコード
    search.setMakerCd(form.getMakerCd());
    return search;
  }

  /**
   * メッセージ情報取得
   * 
   * @param messageMapAll すべてメッセージ情報
   * @return 画面表示用メッセージ情報
   */
  private Map<String, String> getMessageMap(HashMap<String, String> messageMapAll) {
    List<String> messageIdList = new ArrayList<String>();
    messageIdList.add(BregMessageCodes.E00008);
    return messageFacade.getMessageContentsMap(messageIdList, messageMapAll);
  }
}
