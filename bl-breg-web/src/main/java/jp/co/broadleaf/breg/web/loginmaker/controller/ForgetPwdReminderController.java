//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:曹　緩緩
// 作成日:2017/02/08    修正内容:パスワード忘れ：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.loginmaker.controller;

import jp.co.broadleaf.framework.security.Anonymous;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.Submission;
import jp.co.broadleaf.framework.web.WebResult;
import jp.co.broadleaf.breg.loginmaker.facade.ReminderReceptFacade;
import jp.co.broadleaf.breg.web.loginmaker.form.ForgetPwdReminderForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * PWD忘れリマインダControllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/loginmaker")
public class ForgetPwdReminderController extends BaseController {

  /**
   * <pre>
   * PWD忘れリマインダ画面を表示する.
   * </pre>
   * 
   * @param request HttpServletRequest
   * @return 初期表示のURL
   */
  @Anonymous
  @RequestMapping(path = "/forgetPwd", method = RequestMethod.GET)
  public ModelAndView init(HttpServletRequest request) {
    logger.debug("ログインパスワード忘れ画面のパスワード忘れ初期表示処理を開始します。");
    ModelAndView result = new ModelAndView("loginmaker/forgetPwdReminder");
    logger.debug("ログインパスワード忘れ画面のパスワード忘れ初期表示処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * PWD忘れリマインダ.
   * </pre>
   * 
   * @param form パスワード忘れリマインダForm
   * @param request HttpServletRequest
   * @return 処理結果
   */
  @Anonymous
  @RequestMapping(path = "/forgetPwd", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult forgetPwd(@RequestBody ForgetPwdReminderForm form, HttpServletRequest request) {
    logger.debug("ログインパスワード忘れ画面のパスワード忘れリマインダ処理を開始します。");
    WebResult result = new WebResult();
    try {
      // 画面入力チェック
      form.validate();

      // 利用者のIPアドレス
      String ip = getIpAddrByRequest(request);

      // 「リマインダ受付(パスワード忘れ)」Facadeを呼び出す
      int makerCd = Integer.parseInt(form.getMakerCd());
      reminderReceptFacade.remindReceptForgetPwd(makerCd, form.getLoginId(), ip);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("ログインパスワード忘れ画面のパスワード忘れリマインダ処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * IPアドレスを取得する.
   * </pre>
   * 
   * @param request HttpServletRequest
   * @return IPアドレス
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

  /** リマインダ受付Facade */
  private ReminderReceptFacade reminderReceptFacade;

  /**
   * <pre>
   * リマインダ受付Facadeを設定する.
   * </pre>
   *
   * @param reminderReceptFacade リマインダ受付Facade
   */
  @Resource
  public void setReminderReceptFacade(ReminderReceptFacade reminderReceptFacade) {
    this.reminderReceptFacade = reminderReceptFacade;
  }

}
