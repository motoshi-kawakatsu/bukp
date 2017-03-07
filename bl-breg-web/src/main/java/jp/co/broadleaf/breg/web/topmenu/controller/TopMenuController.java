//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:趙　命ラン
// 作成日:2017/02/13    修正内容:TopMenuController.java:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.topmenu.controller;

import jp.co.broadleaf.breg.topmenu.facade.TopMenuFacade;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * メーニューControllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/topmenu")
public class TopMenuController extends BaseController {

  /**
   * <pre>
   * 初期表示時。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  
  @RequestMapping(path = "/topMenu", method = RequestMethod.GET)
  public ModelAndView topMenuInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("トップページの初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(MENU_URL);

    // ログイン情報を取得
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    // アカウント名称を取得
    String accountName = loginPrincipal.getUserName();
    // メーカー名を取得
    String companyName = loginPrincipal.getCompanyName();
    //画面用のメッセージ
    String messageHyoji = topMenuFacade.messageInfo();
    //本人の判断結果
    boolean honninInfo = topMenuFacade.honNin();
    //未申請件数の判断結果
    boolean miShinseiHandan = topMenuFacade.miShinsei();
    //商品件数
    int shinkiHenko = topMenuFacade.shohinnKensuJhoho();
    //セット件数
    int shinseichu = topMenuFacade.setKensuJhoho();
    //結合件数
    int ketugou = topMenuFacade.joinKensuJhoho();
    //申請件数
    int shinseiKensuu = topMenuFacade.applyKensuJhoho();
    //messageOne
    String messageOne = topMenuFacade.getMessageOne();

    result.addObject("accountName", accountName);
    result.addObject("shinkiHenko", shinkiHenko);
    result.addObject("shinseichu", shinseichu);
    result.addObject("messageHyoji", messageHyoji);
    result.addObject("ketugou", ketugou);
    result.addObject("honninInfo", honninInfo);
    result.addObject("miShinseiHandan", miShinseiHandan);
    result.addObject("shinseiKensuu", shinseiKensuu);
    result.addObject("messageOne", messageOne);
    result.addObject("companyName", companyName);
    logger.debug("トップページの初期表示処理を終了します。");
    return result;
  }
  
  /** メーニュー画面 */
  private static final String MENU_URL = "topmenu/topMenu";

  /** TopMenuFacade */
  private TopMenuFacade topMenuFacade;

  /**
   * <pre>
   * TopMenuFacade取得を設定する。
   * </pre>
   *
   * @param topMenuFacade チェックリスト取得
   */
  @Resource
  public void setTopMenuFacade(TopMenuFacade topMenuFacade) {
    this.topMenuFacade = topMenuFacade;
  }
}
