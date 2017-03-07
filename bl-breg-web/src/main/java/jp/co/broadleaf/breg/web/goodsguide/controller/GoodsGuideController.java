//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:                作成担当:趙　雷雷
// 作成日:2017/02/13        修正内容:商品ガイド:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.goodsguide.controller;

import jp.co.broadleaf.breg.web.goodsguide.form.GoodsGuideForm;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.WebResult;
import jp.co.broadleaf.breg.goodsguide.facade.GoodsGuideFacade;
import jp.co.broadleaf.breg.goodsguide.facade.dto.GoodsGuideDto;
import jp.co.broadleaf.breg.common.enums.LogicDeleteDivEnum;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.ListToJson;
import jp.co.broadleaf.common.web.BroadleafSessionKeys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 * <pre>
 * 商品ガイドControllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/goodsguide")
public class GoodsGuideController extends BaseController {
 
  /**
   * 初期表示時.
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/goodsGuide", method = RequestMethod.GET)
  @ResponseBody
  public ModelAndView productGuideInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("商品ガイドの初期表示処理を開始します。");
    String primeCode = "";
    String goodsCategory = "";
    String title = "";
    
    ModelAndView result = new ModelAndView(GOODS_GUIDE_URL);
    List<GoodsGuideDto> goodsGuideDtoList;
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    HttpSession session = request.getSession();
    
    if(null != session.getAttribute(BroadleafSessionKeys.GOODS_GUIDE)){
      String[] array = session.getAttribute(BroadleafSessionKeys.GOODS_GUIDE).toString().split("：");
      primeCode = array[0].trim();
    }
    
    goodsCategory = session.getAttribute(BroadleafSessionKeys.GOODS_CATEGORY).toString().trim();
    if ("maker".equals(goodsCategory)) {
      goodsGuideDtoList = goodsGuideFacade.searchPrimeByCode(LogicDeleteDivEnum.Valid.getValue(), loginPrincipal.getMakerCode(), primeCode);
      goodsCategory = "優良";
    } else {
      goodsGuideDtoList = goodsGuideFacade.searchPureGoodsByCode(LogicDeleteDivEnum.Valid.getValue(), loginPrincipal.getMakerCode(), primeCode);
      goodsCategory = "純正";
      title = "純正";
    }
    
    String goodsGuide = new ListToJson<GoodsGuideDto>().listToJson(goodsGuideDtoList);
    result.addObject("goodsGuide", goodsGuide);
    result.addObject("primeCode", primeCode);
    result.addObject("goodsCategory", goodsCategory);
    result.addObject("title", title);
    logger.debug("商品ガイドの初期表示処理を終了します。");
    return result;
  }
  
  
  /**
   * 優良品番検索を実行する
   * 
   * @param form 商品ガイドform
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return 優良品番検索結果
   */
  @RequestMapping(path = "/goodsGuide", method = RequestMethod.POST)
  @ResponseBody
  public WebResult productGuideResult(@RequestBody GoodsGuideForm form, HttpServletRequest request, HttpServletResponse response) {
    String goodsCategory = "maker";
    logger.debug("商品ガイドの優良品番検索処理を開始します。");
    WebResult result = new WebResult();
    List<GoodsGuideDto> goodsGuideDtoList;
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    HttpSession session = request.getSession();
    goodsCategory = session.getAttribute(BroadleafSessionKeys.GOODS_CATEGORY).toString().trim();
    if ("maker".equals(goodsCategory)) {
      goodsGuideDtoList = goodsGuideFacade.searchPrimeByCode(LogicDeleteDivEnum.Valid.getValue(), loginPrincipal.getMakerCode(), form.getPrimePartsNoWithH());
    } else {
      goodsGuideDtoList = goodsGuideFacade.searchPureGoodsByCode(LogicDeleteDivEnum.Valid.getValue(), loginPrincipal.getMakerCode(), form.getPrimePartsNoWithH());
    }
    
    
    result.put("goodsGuideDtoList", goodsGuideDtoList);
    logger.debug("商品ガイドの優良品番検索処理を終了します。");
    return result;
  }
  
  /** 商品中分類コードガイド画面URL */
  private static final String GOODS_GUIDE_URL = "goodsguide/goodsGuide";
  
  /** 商品中分類コードガイドFacade */
  private GoodsGuideFacade goodsGuideFacade;
  
  
  /**
   * <pre>
   * 商品ガイドFacadeを設定する。
   * </pre>
   *
   * @param goodsGuideFacade 商品ガイドFacade
   */
  @Resource
  public void setGoodsGuideFacade(GoodsGuideFacade goodsGuideFacade) {
    this.goodsGuideFacade = goodsGuideFacade;
  }

}
