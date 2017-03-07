//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:曹　緩緩
// 作成日:2017/02/08    修正内容:ログイン送信完了：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.loginmaker.controller;

import jp.co.broadleaf.framework.security.Anonymous;
import jp.co.broadleaf.framework.web.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * パスワード忘れリマインダControllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/loginmaker")
public class ForgetPwdEndController extends BaseController {

  /**
   * リマインダ受付（パスワード忘れ）完了.
   * 
   * @param request HttpServletRequest
   * @return 表示するURL
   */
  @Anonymous
  @RequestMapping(path = "forgetPwdEnd", method = RequestMethod.GET)
  public ModelAndView init(HttpServletRequest request) {
    logger.debug("ログイン送信完了のパスワード忘れ完了表示処理を開始します。");
    ModelAndView result = new ModelAndView("loginmaker/forgetPwdEnd");
    logger.debug("ログイン送信完了のパスワード忘れ完了表示処理を終了します。");
    return result;
  }

}
