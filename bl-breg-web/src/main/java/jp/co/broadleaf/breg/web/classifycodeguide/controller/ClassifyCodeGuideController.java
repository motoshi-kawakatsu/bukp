//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:                 作成担当:趙　雷雷
// 作成日:2017/02/12         修正内容:商品中分類コードガイド:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.classifycodeguide.controller;

import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.WebResult;
import jp.co.broadleaf.breg.classifycodeguide.facade.ClassifyCodeGuideFacade;
import jp.co.broadleaf.breg.classifycodeguide.facade.dto.ClassifyCodeGuideDto;
import jp.co.broadleaf.breg.web.classifycodeguide.form.ClassifyCodeGuideForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.ListToJson;
import jp.co.broadleaf.common.web.BroadleafSessionKeys;
import jp.co.broadleaf.breg.common.enums.LogicDeleteDivEnum;

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
 * 商品中分類コードガイドControllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/classifycodeguide")
public class ClassifyCodeGuideController extends BaseController {
  
  /**
   * <pre>
   * 初期表示時.
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/classifyCodeGuide", method = RequestMethod.GET)
  @ResponseBody
  public ModelAndView classifyCdGuideInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("商品中分類コードガイドの初期表示処理を開始します。");
    String goodsRateGrpCode = "";
    String goodsRateGrpName = "";
    
    ModelAndView result = new ModelAndView(CLASSIFY_CODE_GUIDE_URL);
    HttpSession session = request.getSession();
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    
    if(null != session.getAttribute(BroadleafSessionKeys.CLASSIFY_CODE_GUIDE)){
      String[] array = session.getAttribute(BroadleafSessionKeys.CLASSIFY_CODE_GUIDE).toString().split("：");
      if (array.length == 1) {
        goodsRateGrpCode = array[0].trim();
      } else {
        goodsRateGrpCode = array[0].trim();
        goodsRateGrpName = array[1].trim();
      }
    }
    
    List<ClassifyCodeGuideDto> classifyCodeGuideDtoList = classifyCodeGuideFacade.searchPrimeByCode(LogicDeleteDivEnum.Valid.getValue(), 
        loginPrincipal.getMakerCode(), goodsRateGrpCode, goodsRateGrpName);
    String classifyCodeGuide = new ListToJson<ClassifyCodeGuideDto>().listToJson(classifyCodeGuideDtoList);
    result.addObject("classifyCodeGuide", classifyCodeGuide);
    result.addObject("goodsRateGrpCode", goodsRateGrpCode);
    result.addObject("goodsRateGrpName", goodsRateGrpName);
    logger.debug("商品中分類コードガイドの初期表示処理を終了します。");
    return result;
  }
  
  /**
   * 中分類コード検索を実行する
   * 
   * @param form 中分類コードform
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return 中分類コード検索結果
   */
  @RequestMapping(path = "/classifyCodeGuide", method = RequestMethod.POST)
  @ResponseBody
  public WebResult classifyCodeGuideResult(@RequestBody ClassifyCodeGuideForm form, HttpServletRequest request, HttpServletResponse response) {
    logger.debug("商品中分類コードガイドの検索処理を開始します。");
    WebResult result = new WebResult();
    // BLアカウントでログインする
    
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    
    List<ClassifyCodeGuideDto> classifyCodeGuideDtoList = classifyCodeGuideFacade.searchPrimeByCode(LogicDeleteDivEnum.Valid.getValue(), 
                               loginPrincipal.getMakerCode(), form.getGoodsRateGrpCode(),form.getGoodsRateGrpName());
    
   
    result.put("classifyCodeGuideDtoList", classifyCodeGuideDtoList);
    logger.debug("商品中分類コードガイドの検索処理を終了します。");
    return result;
  }
  
  /**
   * 商品中分類コードガイドFacade
   */
  private ClassifyCodeGuideFacade classifyCodeGuideFacade;
  
  
  
  /**
   * <pre>
   * 商品中分類コードガイドFacadeを設定する。
   * </pre>
   *
   * @param classifyCodeGuideFacade 商品中分類コードガイドFacade
   */
  @Resource
  public void setClassifyCodeGuideFacade(ClassifyCodeGuideFacade classifyCodeGuideFacade) {
    this.classifyCodeGuideFacade = classifyCodeGuideFacade;
  }
  
  /** 商品中分類コードガイド画面URL */
  private static final String CLASSIFY_CODE_GUIDE_URL = "classifycodeguide/classifyCodeGuide";
}
