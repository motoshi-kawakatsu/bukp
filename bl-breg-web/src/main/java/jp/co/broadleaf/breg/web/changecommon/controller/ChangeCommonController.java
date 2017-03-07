//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:陳　雪涛
// 作成日:2017/02/15    修正内容:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.changecommon.controller;

import jp.co.broadleaf.breg.changecommon.facade.ChangeCommonFacade;
import jp.co.broadleaf.breg.classifycodeguide.facade.dto.ClassifyCodeGuideDto;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.enums.LogicDeleteDivEnum;
import jp.co.broadleaf.breg.web.changecommon.form.ChangeCommonForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.BroadleafStringUtils;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * 検索置換Controllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/changecommon")
public class ChangeCommonController extends BaseController {

  /** 検索置換画面 */
  private static final String CHANGECOMMON_URL = "changecommon/changeCommon";

  /** 検索置換Facade */
  private ChangeCommonFacade changeCommonFacde;

  /**
   * <pre>
   * 検索置換Facadeを設定する。
   * </pre>
   *
   * @param changeCommonFacade 検索置換Facade
   */
  @Resource
  public void setChangeCommonFacade(ChangeCommonFacade changeCommonFacade) {
    this.changeCommonFacde = changeCommonFacade;
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
  @RequestMapping(path = "/changeCommon", method = RequestMethod.GET)
  @ResponseBody
  public ModelAndView changeCommonInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("置換画面の初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(CHANGECOMMON_URL);
    logger.debug("置換画面の初期表示処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 検索処理を実行する。
   * </pre>
   * 
   * @param form 検索置換Form
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return 処理結果
   */
  @RequestMapping(path = "/search", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult search(@RequestBody ChangeCommonForm form, HttpServletRequest request,
                          HttpServletResponse response) {
    logger.debug("置換画面の検索処理を開始します。");
    WebResult result = new WebResult();
    String message = changeCommonFacde.getMessage(BregMessageCodes.I00201, form.getSearchItem(), form.getSearchValue(),
        form.getSearchCount());
    result.put("message", message);
    logger.debug("置換画面の検索処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 置換処理を実行する。
   * </pre>
   * 
   * @param form 検索置換Form
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return 処理結果
   */
  @RequestMapping(path = "/replace", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult replace(@RequestBody ChangeCommonForm form, HttpServletRequest request,
                           HttpServletResponse response) {
    logger.debug("置換画面の置換処理を開始します。");
    WebResult result = new WebResult();
    try {
      form.validateMust();
      String message = changeCommonFacde.getMessage(BregMessageCodes.Q00101);
      result.put("message", message);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("置換画面の置換処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 商品ガイドボタン処理を実行する。
   * </pre>
   * 
   * @param form 検索置換Form
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return 処理結果
   */
  @RequestMapping(path = "/showGuide", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult showGuide(@RequestBody ChangeCommonForm form, HttpServletRequest request,
                             HttpServletResponse response) {
    logger.debug("置換画面の商品ガイドボタン処理を開始します。");
    WebResult result = new WebResult();
    if (null != form.getSessionValue()) {
      request.getSession().setAttribute(BroadleafSessionKeys.GOODS_GUIDE, form.getGuideValue());
      request.getSession().setAttribute(BroadleafSessionKeys.GOODS_CATEGORY, form.getSessionValue());
    } else {
      request.getSession().setAttribute(BroadleafSessionKeys.CLASSIFY_CODE_GUIDE, form.getGuideValue());
    }
    logger.debug("置換画面の商品ガイドボタン処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 商品ガイドコードを取得処理を実行する。
   * </pre>
   * 
   * @param form 検索置換Form
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return 処理結果
   */
  @RequestMapping(path = "/getCode", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult getCode(@RequestBody ChangeCommonForm form, HttpServletRequest request,
                           HttpServletResponse response) {
    logger.debug("置換画面の商品ガイドコードを取得処理を開始します。");
    WebResult result = new WebResult();
    if (BroadleafStringUtils.isEmpty(form.getCode())) {
      result.put("codeValue", "");
      return result;
    }
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    StringBuffer codeValue = new StringBuffer();
    String message;
    String code = String.valueOf(Integer.parseInt(form.getCode()));
    ClassifyCodeGuideDto classifyCodeGuideDto = changeCommonFacde.searchPrimeByCode(LogicDeleteDivEnum.Valid.getValue(),
        loginPrincipal.getMakerCode(), code, "");
    String name = classifyCodeGuideDto.getGoodsRateGrpName();
    message = classifyCodeGuideDto.getMessage();
    codeValue.append(code);
    if (null != name) {
      codeValue.append("：").append(name);
    }
    if (null != message) {
      result.put("message", message);
    }
    result.put("codeValue", codeValue.toString());
    logger.debug("置換画面の商品ガイドコードを取得処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * チェック処理を実行する。
   * </pre>
   * 
   * @param form 検索置換Form
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return 処理結果
   */
  @RequestMapping(path = "/check", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult check(@RequestBody ChangeCommonForm form, HttpServletRequest request, HttpServletResponse response) {
    logger.debug("置換画面のチェック処理を開始します。");
    WebResult result = new WebResult();
    try {
      form.validate();
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("置換画面のチェック処理を終了します。");
    return result;
  }
}
