//****************************************************************************//
// システム                                    : カーパーツマネージャー
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                            作成担当 : 秦志超
// 作 成 日       2017/02/06   修正内容 : 申請詳細:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.applydetail.controller;

import jp.co.broadleaf.breg.applydetail.facade.ApplyDetailFacade;
import jp.co.broadleaf.breg.applydetail.facade.dto.GoodsMasterDto;
import jp.co.broadleaf.breg.applydetail.facade.dto.JoinMasterDto;
import jp.co.broadleaf.breg.applydetail.facade.dto.SetMasterDto;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.enums.BlApplyResultsFlgEnum;
import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.common.enums.LogicDeleteDivEnum;
import jp.co.broadleaf.breg.message.service.Facade.MessageFacade;
import jp.co.broadleaf.breg.web.applyhistory.form.ApplyHistorySearchForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.ListToJson;
import jp.co.broadleaf.common.web.BroadleafSessionKeys;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.WebResult;

import org.springframework.stereotype.Controller;
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
 * Servlet implementation class ApplyDetailController
 */
@Controller
@RequestMapping("/apply")
public class ApplyDetailController extends BaseController {
  /** 申請詳細画面 */
  private static final String APPLYDETAIL_URL = "applydetail/applyDetail";
  /** ApplyCommon */
  private static final String APPLYCOMMON = "18";
  /** ApplyHistory */
  private static final String APPLYHISTORY = "22";

  /** applyDetailFacade */
  private ApplyDetailFacade applyDetailFacade;

  /**
   * <pre>
   * 【applyDetailFacade】を設定する。
   * </pre>
   *
   * @param applyDetailFacade 【applyDetailFacade】
   */
  @Resource
  public void setApplyDetailFacade(ApplyDetailFacade applyDetailFacade) {
    this.applyDetailFacade = applyDetailFacade;
  }
  
  /** messageFacade */
  private MessageFacade messageFacade;

  /**
   * <pre>
   * 【messageFacade】を設定する。
   * </pre>
   *
   * @param messageFacade 【messageFacade】
   */
  @Resource
  public void setMessageFacade(MessageFacade messageFacade) {
    this.messageFacade = messageFacade;
  }

  /**
   * 
   * <pre>
   * アップロード処理を実行する。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */

  @RequestMapping(path = "/applydetail", method = RequestMethod.GET)
  public ModelAndView applyDetailInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("申請詳細の初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(APPLYDETAIL_URL);
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = 0;
    if (loginPrincipal != null) {
      makerCode = loginPrincipal.getMakerCode();
    }
    int pageSize = applyDetailFacade.searchApplyDetailRows(makerCode);
    result.addObject("pageSize", pageSize);
    logger.debug("申請詳細の初期表示処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 商品テーブルが映し出される
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/goodsGrid", method = RequestMethod.GET)
  @ResponseBody
  public WebResult showGoodsGrid(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("申請詳細の商品グリッド初期表示処理を開始します。");
    WebResult result = new WebResult();
    ApplyHistorySearchForm form = (ApplyHistorySearchForm) request.getSession()
        .getAttribute(BroadleafSessionKeys.APPLY_DETAIL_KEY);
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    // メーカーコード、ページ、ログインIDの初期化  
    Map<String, String> messageMap = null;
    int makerCode = 0;
    int curPage = 1;
    String loginId = null;
    if (loginPrincipal != null) {
      makerCode = loginPrincipal.getMakerCode();
      loginId = loginPrincipal.getLoginId();
      messageMap = getMessageMap(loginPrincipal.getMessageMap());
    }
    String currentPage = request.getParameter("currentPage");
    if (currentPage != null && !currentPage.equals("")) {
      curPage = Integer.parseInt(currentPage);
    }
    String applyMode = (String) request.getParameter("mode");
    if( applyMode == null || applyMode.equals("")){
      applyMode = "1";
    }

    int pageSize = applyDetailFacade.searchApplyDetailRows(makerCode);
    getGoodsData(makerCode, loginId, form, applyMode, curPage, pageSize, result);
    result.put("messageMap", messageMap);
    logger.debug("申請詳細の商品グリッド初期表示処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 商品データの取得
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param form 申請履歴のパラメーター
   * @param applyMode 進入モード
   * @param curPage このページ
   * @param pageSize ページサイズ
   * @param result WebResult
   */
  private void getGoodsData(int makerCode, String loginId, ApplyHistorySearchForm form, String applyMode,
                            int curPage, int pageSize, WebResult result) {
    List<GoodsMasterDto> goodsMasterDtoListAll = new ArrayList<GoodsMasterDto>();
    List<GoodsMasterDto> goodsMasterDtoList = new ArrayList<GoodsMasterDto>();

    int applyNo = 0;
    if (form != null) {
      applyNo = Integer.parseInt(form.getApplyNo());
    }
    if (applyMode.equals(APPLYCOMMON)) { // 申請詳細画面
      goodsMasterDtoListAll = applyDetailFacade.searchGoodsMaster(makerCode, LogicDeleteDivEnum.Valid.getValue(),
          BlApplyResultsFlgEnum.NoApply.getValue(), loginId, ImportKbnEnum.ImportApp.getValue());
    } else if(applyMode.equals(APPLYHISTORY)){ // 申請詳細（申請一般）画面
      goodsMasterDtoListAll = applyDetailFacade.searchGoodsMasterCommon(makerCode, LogicDeleteDivEnum.Valid.getValue(),
          applyNo);
    }
    int startRow = pageSize * (curPage - 1);
    int endRow = Math.min(pageSize * curPage, goodsMasterDtoListAll.size());
    for (int i = startRow; i < endRow; i++) {
      goodsMasterDtoList.add(goodsMasterDtoListAll.get(i));
    }
    int applyResults = 0;
    if (!goodsMasterDtoList.isEmpty()) {
      String applyResult = goodsMasterDtoList.get(0).getApplyCondition();
      if (applyResult.equals("申請戻し")) { // 申請戻しの場合、申請結果のフラッグを設定する
        applyResults = BlApplyResultsFlgEnum.Reject.getValue();
      } else {
        applyResults = 0;
      }
    }
    String goodsMaster = new ListToJson<GoodsMasterDto>().listToJson(goodsMasterDtoList);
    int allCounts = goodsMasterDtoListAll.size();
    result.put("goodsMaster", goodsMaster);
    result.put("allCounts", allCounts);
    result.put("pageSize", pageSize);
    result.put("applyResult", applyResults);
  }

  /**
   * <pre>
   * セットテーブルが映し出される
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/setGrid", method = RequestMethod.GET)
  @ResponseBody
  public WebResult showSetGrid(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("申請詳細のセットグリッド初期表示処理を開始します。");
    WebResult result = new WebResult();

    ApplyHistorySearchForm form = (ApplyHistorySearchForm) request.getSession()
        .getAttribute(BroadleafSessionKeys.APPLY_DETAIL_KEY);
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    Map<String, String> messageMap = null;
    int makerCode = 0;
    String loginId = null;
    if (loginPrincipal != null) {
      makerCode = loginPrincipal.getMakerCode();
      loginId = loginPrincipal.getLoginId();
      messageMap = getMessageMap(loginPrincipal.getMessageMap());
    }
    int curPage = 1;
    String currentPage = request.getParameter("currentPage");
    if (currentPage != null && !currentPage.equals("")) {
      curPage = Integer.parseInt(currentPage);
    }
    String applyMode = (String) request.getParameter("mode");
    if(applyMode == null || applyMode.equals("")){
      applyMode = "1";
    }
    int pageSize = applyDetailFacade.searchApplyDetailRows(makerCode);
    getSetData(makerCode, loginId, form, applyMode, curPage, pageSize, result);
    result.put("messageMap", messageMap);
    logger.debug("申請詳細のセットグリッド初期表示処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * セットデータの取得
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param form 申請履歴のパラメーター
   * @param applyMode 進入モード
   * @param curPage このページ
   * @param pageSize ページサイズ
   * @param result WebResult
   */
  private void getSetData(int makerCode, String loginId, ApplyHistorySearchForm form, String applyMode, int curPage,
                          int pageSize, WebResult result) {
    List<SetMasterDto> setMasterDtoListAll = new ArrayList<SetMasterDto>();
    List<SetMasterDto> setMasterDtoList = new ArrayList<SetMasterDto>();

    int applyNo = 0;
    if (form != null) {
      applyNo = Integer.parseInt(form.getApplyNo());
    }
    if (applyMode.equals(APPLYCOMMON)) {
      setMasterDtoListAll = applyDetailFacade.searchSetMaster(makerCode, LogicDeleteDivEnum.Valid.getValue(),
          BlApplyResultsFlgEnum.NoApply.getValue(), loginId, ImportKbnEnum.ImportApp.getValue());
    } else if(applyMode.equals(APPLYHISTORY)){
      setMasterDtoListAll = applyDetailFacade.searchSetMasterCommon(makerCode, LogicDeleteDivEnum.Valid.getValue(),
          applyNo);
    }
    int startRow = pageSize * (curPage - 1);
    int endRow = Math.min(pageSize * curPage, setMasterDtoListAll.size());
    for (int i = startRow; i < endRow; i++) {
      setMasterDtoList.add(setMasterDtoListAll.get(i));
    }
    String setMaster = new ListToJson<SetMasterDto>().listToJson(setMasterDtoList);
    int allCounts = setMasterDtoListAll.size();
    result.put("setMaster", setMaster);
    result.put("allCounts", allCounts);
    result.put("pageSize", pageSize);
  }

  /**
   * <pre>
   * 結合テーブルが映し出される
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/joinGrid", method = RequestMethod.GET)
  @ResponseBody
  public WebResult showJoinGrid(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("申請詳細の結合グリッド初期表示処理を開始します。");
    WebResult result = new WebResult();

    ApplyHistorySearchForm form = (ApplyHistorySearchForm) request.getSession()
        .getAttribute(BroadleafSessionKeys.APPLY_DETAIL_KEY);
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    Map<String, String> messageMap = null;
    int makerCode = 0;
    String loginId = null;
    if (loginPrincipal != null) {
      makerCode = loginPrincipal.getMakerCode();
      loginId = loginPrincipal.getLoginId();
      messageMap = getMessageMap(loginPrincipal.getMessageMap());
    }
    int curPage = 1;
    String currentPage = request.getParameter("currentPage");
    if (currentPage != null && !currentPage.equals("")) {
      curPage = Integer.parseInt(currentPage);
    }
    String applyMode = (String) request.getParameter("mode");
    if(applyMode == null || applyMode.equals("")){
      applyMode = "1";
    }
    int pageSize = applyDetailFacade.searchApplyDetailRows(makerCode);
    getJoinData(makerCode, loginId, form, applyMode, curPage, pageSize, result);
    result.put("messageMap", messageMap);
    logger.debug("申請詳細の結合グリッド初期表示処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 結合データの取得
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param form 申請履歴のパラメーター
   * @param applyMode 進入モード
   * @param curPage このページ
   * @param pageSize ページサイズ
   * @param result WebResult
   */
  private void getJoinData(int makerCode, String loginId, ApplyHistorySearchForm form, String applyMode, int curPage,
                           int pageSize, WebResult result) {
    List<JoinMasterDto> joinMasterDtoListAll = new ArrayList<JoinMasterDto>();
    List<JoinMasterDto> joinMasterDtoList = new ArrayList<JoinMasterDto>();

    int applyNo = 0;
    if (form != null) {
      applyNo = Integer.parseInt(form.getApplyNo());
    }
    if (applyMode.equals(APPLYCOMMON)) {
      joinMasterDtoListAll = applyDetailFacade.searchJoinMaster(makerCode, LogicDeleteDivEnum.Valid.getValue(),
          BlApplyResultsFlgEnum.NoApply.getValue(), loginId, ImportKbnEnum.ImportApp.getValue());
    } else if(applyMode.equals(APPLYHISTORY)) {
      joinMasterDtoListAll = applyDetailFacade.searchJoinMasterCommon(makerCode, LogicDeleteDivEnum.Valid.getValue(),
          applyNo);
    }
    int startRow = pageSize * (curPage - 1);
    int endRow = Math.min(pageSize * curPage, joinMasterDtoListAll.size());
    for (int i = startRow; i < endRow; i++) {
      joinMasterDtoList.add(joinMasterDtoListAll.get(i));
    }
    String joinMaster = new ListToJson<JoinMasterDto>().listToJson(joinMasterDtoList);
    int allCounts = joinMasterDtoListAll.size();
    result.put("joinMaster", joinMaster);
    result.put("allCounts", allCounts);
    result.put("pageSize", pageSize);
  }
  
  /**
   * <pre>
   * メッセージ情報取得
   * </pre>
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
