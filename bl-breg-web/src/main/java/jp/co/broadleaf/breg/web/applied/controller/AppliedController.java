//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : xuck
// 作 成 日       2017/02/14   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.applied.controller;

import jp.co.broadleaf.framework.security.Anonymous;
import jp.co.broadleaf.framework.web.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * </pre>
 */
@Controller
@RequestMapping("/applied")
public class AppliedController extends BaseController {
  /** 申請完了　*/
  private static final String APPLY_URL = "/applied/applied";

  /**
   * <pre>
   * 初期表示時。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @Anonymous
  @RequestMapping(path = "/applied", method = RequestMethod.GET)
  @ResponseBody
  public ModelAndView onload(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("申請完了の初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(APPLY_URL);
    logger.debug("申請完了の初期表示処理を終了します。");
    return result;
  }

}
