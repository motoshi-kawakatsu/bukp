//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : lulei
// 作 成 日       2017/02/09   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.checklist.controller;

import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.WebResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * チェックリスト帳票出力 Controllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/checkList")
public class CheckListOutPutController extends BaseController {

  /**
   * <pre>
   * 初期表示時。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/checkListOutPut", method = RequestMethod.GET)
  public ModelAndView checkListInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("タイプ選択出力処理を開始します。");
    ModelAndView result = new ModelAndView(CHECKLIST);
    logger.debug("タイプ選択出力処理を終了します。");
    return result;
  }


  /**
   * ダウンロード処理
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/downLoadFile", method = RequestMethod.POST)
  @ResponseBody
  public WebResult downLoadFile(HttpServletRequest request, HttpServletResponse response) {
    WebResult result = new WebResult();
    try{
    logger.debug("帳票のダウンロード処理を開始します。");
    DownloadFile download = new DownloadFile();
    download.doGet(request, response);
    logger.debug("帳票のダウンロード処理を終了します。");
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    return result;
  }

  
  /** チェックリスト画面 */
  private static final String CHECKLIST = "checkList/checkListOutPut";
  
}
