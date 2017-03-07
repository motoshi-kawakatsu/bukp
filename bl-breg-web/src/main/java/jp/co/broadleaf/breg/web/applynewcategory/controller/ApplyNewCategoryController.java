//****************************************************************************//
// システム                                    :優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                  作成担当 : 楊蕾蕾
// 作 成 日       2017/02/13   修正内容 : 申請 (新規品目):新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.applynewcategory.controller;
import jp.co.broadleaf.breg.web.applyhistory.form.ApplyHistorySearchForm;
import jp.co.broadleaf.breg.web.applynewcategory.form.ApplyNewCategoryForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.BroadleafFormatUtils;
import jp.co.broadleaf.common.web.BroadleafSessionKeys;
import jp.co.broadleaf.breg.applynewcategory.enums.ApplyNewCategoryModeEnum;
import jp.co.broadleaf.breg.applynewcategory.facade.ApplyNewCategoryFacade;
import jp.co.broadleaf.breg.applynewcategory.facade.dto.ApplyNewCategoryDto;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.message.service.Facade.MessageFacade;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * 申請 (新規品目)Controllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/applynewcategory")
public class ApplyNewCategoryController extends BaseController {

  /**
   * <pre>
   * 初期表示時.
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/applynewcategory", method = RequestMethod.GET)
  public ModelAndView applyNewInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("申請(新規品目)の初期表示処理(モード１、２、３)を開始します");
    ModelAndView result = new ModelAndView( APPLY_NEW_CATEGORY_URL);
    // Modeの判断
    HttpSession session = request.getSession();
    try{
      ApplyHistorySearchForm form = (ApplyHistorySearchForm) session.getAttribute(BroadleafSessionKeys.APPLY_NEWITEM_KEY);
      int applyNewMode = form.getModeForNewItem();
      if(applyNewMode == ApplyNewCategoryModeEnum.mode2.getValue() 
          || applyNewMode == ApplyNewCategoryModeEnum.mode3.getValue()){
        int applyNewEnum = applyNewMode;
        int oldApplyNo = Integer.parseInt(form.getApplyNo());
        Short oldBlApplyResultsFlg = Short.valueOf(form.getBlApplyResultsFlg());
        int oldMakerCd = form.getMakerCd();
        
        // 初期データを取得する
        try{
          ApplyNewCategoryDto applyHistryInfo = new ApplyNewCategoryDto();
          applyHistryInfo.setApplyNo(oldApplyNo);
          applyHistryInfo.setBlApplyResultsFlg(oldBlApplyResultsFlg);
          applyHistryInfo.setPartsMakerCd(oldMakerCd);
          ApplyNewCategoryDto applyHistryInfoShow = applyNewCategoryFacede.getApplyHistoryInfo(applyHistryInfo,false);
          Timestamp applyDtTime = applyHistryInfoShow.getApplyDtTime();
          String oldapplyDtTime2 = BroadleafFormatUtils.timestampToString(applyDtTime, BroadleafFormatUtils.DATE_F004);
          String oldapplyComents = applyHistryInfoShow.getApplyComments();
          
          // 初期データを保存する
          ApplyNewCategoryForm formInfo = new ApplyNewCategoryForm();
          formInfo.setOldApplyNo(oldApplyNo);
          formInfo.setOldBlApplyResultsFlg(oldBlApplyResultsFlg);
          formInfo.setOldMakerCd(oldMakerCd);
          formInfo.setApplyComments(oldapplyComents);
          session.setAttribute(APPLY_NEW_CATEGORY_OLD_INFO, formInfo);
          
          //　データを表示する
          result.getModel().put("applyId", oldApplyNo);
          result.getModel().put("applyDtTime", oldapplyDtTime2);
          result.getModel().put("applyComents", oldapplyComents);
          result.getModel().put("applyNewEnum", applyNewEnum);
          session.removeAttribute(BroadleafSessionKeys.APPLY_NEWITEM_KEY);
        }catch(Throwable ex){
          handleException(ex, result);
        }
      }else if(applyNewMode == ApplyNewCategoryModeEnum.mode4.getValue()){
        int applyNewEnum = applyNewMode;
        ApplyNewCategoryForm oldForm = (ApplyNewCategoryForm)session.getAttribute(APPLY_NEW_CATEGORY_OLD_INFO);
        String oldComments = oldForm.getApplyComments();
        result.getModel().put("applyComents", oldComments);
        result.getModel().put("applyNewEnum", applyNewEnum);
        session.removeAttribute(BroadleafSessionKeys.APPLY_NEWITEM_KEY);
      }
      
    }catch(Throwable ex){
      result.getModel().put("applyNewEnum", null);
    }
    logger.debug("申請(新規品目)の初期表示処理(モード１、２、３)を終了します");
    return result;
  }
  
  /**
   * <pre>
   * 申請(新規品目)Mode4。
   * </pre>
   * 
   * @param form 申請(新規品目)Form
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/applyMode4", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult applyNewItemInit(@RequestBody ApplyHistorySearchForm form, HttpServletRequest request,
          HttpServletResponse response) {
      logger.debug("申請(新規品目)の初期表示処理(モード４)を開始します。");
      WebResult result = new WebResult();
      //起動モード(申請(新規品目)Mode4)
      form.setModeForNewItem(ApplyNewCategoryModeEnum.mode4.getValue());
      request.getSession().removeAttribute(BroadleafSessionKeys.APPLY_NEWITEM_KEY);
      // 申請(新規品目)画面用フォーム をセッションにセートします
      request.getSession().setAttribute(BroadleafSessionKeys.APPLY_NEWITEM_KEY, form);
      logger.debug("申請(新規品目)の初期表示処理(モード４)を終了します");
      return result;
  }
  
  /**
   * <pre>
   * 申請 (新規品目)更新と挿入を実行する.
   * </pre>
   * 
   * @param form 申請 (新規品目)Form
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return 申請ID
   */
  @RequestMapping(path = "/applynewcategory", method = RequestMethod.POST)
  @ResponseBody
  public WebResult applyNewCategoryResult(@RequestBody ApplyNewCategoryForm form, 
                                          HttpServletRequest request, HttpServletResponse response) {
    logger.debug("申請(新規品目)の申請処理を開始します。");
    WebResult result = new WebResult();
    try{
      form.validate();
      
      // 前回情報を更新する       
      if(form.getApplyState().equals("4")){
        HttpSession session = request.getSession();
        ApplyNewCategoryForm oldForm = (ApplyNewCategoryForm)session.getAttribute(APPLY_NEW_CATEGORY_OLD_INFO);
        ApplyNewCategoryDto applyHistryInfo = new ApplyNewCategoryDto();
        applyHistryInfo.setApplyNo(oldForm.getOldApplyNo());
        applyHistryInfo.setBlApplyResultsFlg(oldForm.getOldBlApplyResultsFlg());
        applyHistryInfo.setPartsMakerCd(oldForm.getOldMakerCd());
        applyNewCategoryFacede.getApplyHistoryInfo(applyHistryInfo,true);
        
        // 部品メーカーコードを取得する   
        LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
        int partsMakerCd = loginPrincipal.getMakerCode();
        
        // 申請IDを取得する
        int applyNO = applyNewCategoryFacede.searchApplyNO()+1;
        
        // 申請日時を取得する
        Timestamp applyTime = BroadleafFormatUtils.stringToTimestamp(form.getApplyDtTime(),BroadleafFormatUtils.DATE_F010);
        
        // 申請 (新規品目)の情報を設定する
        ApplyNewCategoryDto applyNewCategoryDto = new ApplyNewCategoryDto();
        applyNewCategoryDto.setApplyNo(applyNO);
        applyNewCategoryDto.setPartsMakerCd(partsMakerCd);
        applyNewCategoryDto.setApplyComments(form.getApplyComments());
        applyNewCategoryDto.setApplyDtTime(applyTime);
        applyNewCategoryDto.setPreApplyNo(oldForm.getOldApplyNo());
        int applyStatus = applyNewCategoryFacede.saveApplyNewCaregory(applyNewCategoryDto);
        // 申請成功の場合
        if(applyStatus == 0){

          // 申請完了画面へ申請IDを転送する
          result.put("applyNo", applyNO);
          session.setAttribute("applyNo", applyNO);
        }else{
          handleException(new Throwable(), result);
        }
      }else{
        // 部品メーカーコードを取得する   
        LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
        int partsMakerCd = loginPrincipal.getMakerCode();
        
        // 申請IDを取得する
        int applyNO = applyNewCategoryFacede.searchApplyNO()+1;
        
        // 申請日時を取得する
        Timestamp applyTime = BroadleafFormatUtils.stringToTimestamp(form.getApplyDtTime(),BroadleafFormatUtils.DATE_F010);
        
        // 申請 (新規品目)の情報を設定する
        ApplyNewCategoryDto applyNewCategoryDto = new ApplyNewCategoryDto();
        applyNewCategoryDto.setApplyNo(applyNO);
        applyNewCategoryDto.setPartsMakerCd(partsMakerCd);
        applyNewCategoryDto.setApplyComments(form.getApplyComments());
        applyNewCategoryDto.setApplyDtTime(applyTime);
        int applyStatus = applyNewCategoryFacede.saveApplyNewCaregory(applyNewCategoryDto);
        // 申請成功の場合
        if(applyStatus == 0){

          // 申請完了画面へ申請IDを転送する
          result.put("applyNo", applyNO);
          HttpSession session = request.getSession();
          session.setAttribute("applyNo", applyNO);
        }else{
          handleException(new Throwable(), result);
        }
      }
      
    }catch(Throwable ex){
       handleException(ex, result);
    }
    logger.debug("申請(新規品目)の申請処理を終了します。");
    return result;
  }
  
  /**
   * <pre>
   * 申請(新規品目)sendMail。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/sendMail", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult applyNewSengMail(HttpServletRequest request,
          HttpServletResponse response) {
      logger.debug("申請(新規品目)の送信処理を開始します。");
      WebResult result = new WebResult();
      //Mail
      LoginPrincipal principal = SecurityContextHolder.getPrincipal();
      int makerCode = principal.getMakerCode();
      String loginId = principal.getLoginId();
      // 利用者のIPアドレス
      String ipAddr = getIpAddrByRequest(request);
      
      boolean sendEmailRtn = applyNewCategoryFacede.sendMail(makerCode, loginId, ipAddr);
      result.put("sendEmailFlag", sendEmailRtn);
      if (!sendEmailRtn) {
        LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
        // messageMap
        Map<String, String> messageMap = getMessageMap(loginPrincipal.getMessageMap());
        result.put("messageMap", messageMap);
      }   
      logger.debug("申請(新規品目)の送信処理を終了します。");
      return result;
  }
  
  
  /**
   * 編集中項目がある画面を遷移場合処理
   * 
   * @param paramData CompanyInfoForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/back", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult applyNewLeave(@RequestBody ApplyNewCategoryForm paramData, HttpServletRequest request,
                                      HttpServletResponse response) {
    logger.debug("申請(新規品目)の遷移処理を開始します。");
    WebResult result = new WebResult();
    try {
      HttpSession session = request.getSession();
      ApplyNewCategoryForm oldForm = (ApplyNewCategoryForm)session.getAttribute(APPLY_NEW_CATEGORY_OLD_INFO);
      String oldApplyInfo = oldForm.getApplyComments();
      String newApplyInfo = paramData.getApplyComments();
      
      // 画面編集した項目ありかどうかの判断
      if (hasEditColumn(newApplyInfo, oldApplyInfo)) {
        result.put(PAGEUPDATEFLAG, true);
      } else {
        result.put(PAGEUPDATEFLAG, false);
      }
      
    } catch (Throwable ex) {
        String newApplyInfo = paramData.getApplyComments();
        if (hasEditColumn(newApplyInfo, null)) {
          result.put(PAGEUPDATEFLAG, true);
        } else {
          result.put(PAGEUPDATEFLAG, false);
        }
    }finally{
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      // messageMap
      Map<String, String> messageMap = getMessageMap(loginPrincipal.getMessageMap());
      result.put("messageMap", messageMap);
      result.put(MOVEKEY, TOPMENU_URL);
      result.put(APPLY_NEW_CATEGORY_KEY, APPLY_NEW_CATEGORY_URL);
    }
    logger.debug("申請(新規品目)の遷移処理を終了します。");
    return result;
  }
  
  /**
   * 画面で編集した項目ありかどうかの判断
   * 
   * @param updateInfo 新しい情報
   * @param initialInfo 初期情報
   * @return true:編集した項目あり false:編集した項目ない
   */
  private boolean hasEditColumn(String updateInfo, String initialInfo) {
    if (initialInfo == null && updateInfo == null){
      return false;
    }else if(initialInfo == null && updateInfo != null){
      return true;
    }
    return !updateInfo.equals(initialInfo);
  }

  /** 申請 (新規品目)画面　*/
  private static final String APPLY_NEW_CATEGORY_URL = "applynewcategory/applynewcategory";
  
  /** 画面更新かどうかのFlag */
  private static final String PAGEUPDATEFLAG = "pageUpdateFlag";
  
  /** トップページのキー */
  private static final String MOVEKEY = "topMenuKey";
  
  /** トップページのURL */
  private static final String TOPMENU_URL = "/topmenu/topMenu";
  
  /**初期情報 */
  private static final String APPLY_NEW_CATEGORY_OLD_INFO = "applynewcategoryOldInfo";
  
  /** 会社情報画面のURLKEY */
  private static final String APPLY_NEW_CATEGORY_KEY = "applynewcategoryKey";

  /**
   * Mail
   * 
   * @param request HttpServletRequest request
   * @return IP
   */
  private String getIpAddrByRequest(HttpServletRequest request) {
    // IPアドレス x-forwarded-for
    String ip = request.getHeader("x-forwarded-for");
    // IPアドレス Proxy-Client-IP
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    // IPアドレス WL-Proxy-Client-IP
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    // IPアドレス getRemoteAddr
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }
  
  /** 申請 (新規品目)Facade */
  private ApplyNewCategoryFacade applyNewCategoryFacede;
  
  /**
   * <pre>
   * 申請 (新規品目)Facadeを設定する。
   * </pre>
   *
   * @param applyNewCategoryFacede 申請 (新規品目)Facade
   */
  @Resource
  public void setApplyNewCategoryFacede(ApplyNewCategoryFacade applyNewCategoryFacede) {
    this.applyNewCategoryFacede = applyNewCategoryFacede;
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
   * メッセージ情報取得
   * 
   * @param messageMapAll すべてメッセージ情報
   * @return 画面表示用メッセージ情報
   */
  private Map<String, String> getMessageMap(HashMap<String, String> messageMapAll) {
    List<String> messageIdList = new ArrayList<String>();
    messageIdList.add(BregMessageCodes.Q00001);
    messageIdList.add(BregMessageCodes.E90002);
    return messageFacade.getMessageContentsMap(messageIdList, messageMapAll);
  }
}
