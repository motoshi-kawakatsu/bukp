//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
// (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴      
// ---------------------------------------------------------------------------//
// 管理番号       作成担当 : 羅　進博
// 作 成 日       2017/02/08   修正内容 : ファイル選択：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.readresult.controller;

import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.readresult.facade.ReadResultFacade;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.web.BroadleafSessionKeys;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * 取込完了 Controllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/readresult")
public class ReadResultController extends BaseController {
  /** 取込完了画面 */
  private static final String FILE_SELECT_URL = "readresult/readresult";
  /** readResultFacade */
  private ReadResultFacade readResultFacade;

  /**
   * <pre>
   * 【readResultFacade】を設定する。
   * </pre>
   *
   * @param readResultFacade 【readResultFacade】
   */
  @Resource
  public void setReadResultFacade(ReadResultFacade readResultFacade) {
    this.readResultFacade = readResultFacade;
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
  @RequestMapping(path = "/readresult", method = RequestMethod.GET)
  @ResponseBody
  public ModelAndView readResultInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("取込完了の初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(FILE_SELECT_URL);
    /** ログイン情報を取得。 */
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int loginUserCd = loginPrincipal.getMakerCode();
    int[] goodsInfo;
    int[] setInfo;
    int[] joinInfo;
    HttpSession session = request.getSession();
    /** 「インポート」や「インポート（一括申請）」起動の場合：0:インポート（一括申請）；1:インポート。null:インポート未完了情報がある、データ再処理の場合 */
    ImportKbnEnum importTypeMenu = null;
    importTypeMenu = (ImportKbnEnum) session.getAttribute("importTypeMenu");
    if (importTypeMenu == null) {
      result.addObject("importTypeMenu", true);
      /** 商品の情報をゲットする。 */
      goodsInfo = readResultFacade.getGoodsInfo(loginUserCd);
      /** セットの情報をゲットする。 */
      setInfo = readResultFacade.getSetInfo(loginUserCd);
      /** 結合の情報をゲットする。 */
      joinInfo = readResultFacade.getJoinInfo(loginUserCd);
    } else {
      result.addObject("importTypeMenu", importTypeMenu.getValue() == 0 ? true : false);
      /** 商品の情報をゲットする。 */
      goodsInfo = (int[]) session.getAttribute(BroadleafSessionKeys.GOODS_INFO);
      /** セットの情報をゲットする。 */
      setInfo = (int[]) session.getAttribute(BroadleafSessionKeys.SET_INFO);
      /** 結合の情報をゲットする。 */
      joinInfo = (int[]) session.getAttribute(BroadleafSessionKeys.JOIN_INFO);
    }
    request.getSession().setAttribute(BroadleafSessionKeys.IS_FROM_IMPORT, "Y");
    /** ｊｓｐにメッセージを出す。 */
    result.addObject("goodsInfo", goodsInfo);
    result.addObject("setInfo", setInfo);
    result.addObject("joinInfo", joinInfo);
    logger.debug("取込完了の初期表示処理を終了します。");
    return result;
  }
}
