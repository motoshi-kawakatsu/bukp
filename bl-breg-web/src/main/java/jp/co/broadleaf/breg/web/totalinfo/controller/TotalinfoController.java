//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:                 作成担当:司　志超
// 作成日:2017/02/07    修正内容:累積情報:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.web.totalinfo.controller;

import jp.co.broadleaf.breg.common.enums.LogicDeleteDivEnum;
import jp.co.broadleaf.breg.common.enums.ObjectKbnEnum;
import jp.co.broadleaf.breg.companysetting.dto.CompanySettingDto;
import jp.co.broadleaf.breg.companysetting.facade.CompanyInfoMasterCommonFacade;
import jp.co.broadleaf.breg.totalinfo.facade.TotalinfoFacade;
import jp.co.broadleaf.breg.totalinfo.facade.dto.GoodsTotalinfoDto;
import jp.co.broadleaf.breg.totalinfo.facade.dto.JoinTotalinfoDto;
import jp.co.broadleaf.breg.totalinfo.facade.dto.SearchConditionDto;
import jp.co.broadleaf.breg.totalinfo.facade.dto.SetTotalinfoDto;
import jp.co.broadleaf.breg.totalinfo.facade.dto.TotalinfoDto;
import jp.co.broadleaf.breg.web.totalinfo.form.TotalinfoSerchForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.ListToJson;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
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
 * </pre>
 */
@Controller
@RequestMapping("/totalinfo")
public class TotalinfoController extends BaseController {
  

  /**
   * <pre>
   * 初期表示時。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/totalinfo", method = RequestMethod.GET)
  @ResponseBody
  public ModelAndView totalinfoInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("累積情報の初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(TOTALINFO);
    logger.debug("累積情報の初期表示処理を終了します。");
    return result;
  }
  
  /**
   * <pre>
   * 初期表示時。
   * </pre>
   * @param form 累積情報Formクラス
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/totalinfo", method = RequestMethod.POST)
  @ResponseBody
  public WebResult filterSearch(@RequestBody TotalinfoSerchForm form, HttpServletRequest request, HttpServletResponse response) {
    logger.debug("累積情報の検索処理を開始します。");
    WebResult result = new WebResult();
    
    SearchConditionDto searchConditionDto = new SearchConditionDto();
    // 検索条件設定
    searchConditionDto.setObjKbn(form.getObjKbn());
    searchConditionDto.setFilterMode(form.getFilterMode());
    searchConditionDto.setCurrentPage(form.getCurrentPage());
    searchConditionDto.setTbsPartsCode(form.getTbsPartsCode());
    searchConditionDto.setPartsMakerCd(form.getPartsMakerCd());
    searchConditionDto.setPrimePartsNoWithH(form.getPrimePartsNo());
    searchConditionDto.setJoinSourPartsNoWithH(form.getJoinSourPartsNoWithH());
    searchConditionDto.setLogicalDelDiv(LogicDeleteDivEnum.Valid.getValue());
    if (request.getSession().getAttribute(MAXCOUNT_BY_PAGE_KEY) == null) {
      int makerCd = 0;
      LoginPrincipal principal = SecurityContextHolder.getPrincipal();
      if(null != principal){
        makerCd = principal.getMakerCode();
      }
      // 初期会社情報を取得
      CompanySettingDto companySettingDto = companyInfoMasterCommonFacade.getCompanyInfo(makerCd);
      request.getSession().setAttribute(MAXCOUNT_BY_PAGE_KEY, companySettingDto.getApplyDetailRows());
    }
    searchConditionDto.setMaxCount(Integer.parseInt(String.valueOf(request.getSession().getAttribute(MAXCOUNT_BY_PAGE_KEY))));
    
    // 累積情報の取得
    TotalinfoDto totalinfoDto = totalinfoFacade.getTotalInfo(searchConditionDto);
    if (ObjectKbnEnum.SetKbn.getValue() == searchConditionDto.getObjKbn()) {
      // セット情報
      String json2 = new ListToJson<SetTotalinfoDto>().listToJson(totalinfoDto.getSetTotalinfoDtoList());
      result.put(SETMASTER, json2);
      // セット検索数
      result.put("searchcount", totalinfoDto.getSetSearchCount());
      // セット全件数
      result.put("totalcount", totalinfoDto.getSetAllCount());
    } else if (ObjectKbnEnum.JoinKbn.getValue() == searchConditionDto.getObjKbn()) {
      // 結合情報
      String json3 = new ListToJson<JoinTotalinfoDto>().listToJson(totalinfoDto.getJoinTotalinfoDtoList());
      result.put(JOINMASTER, json3);
      // 結合検索数
      result.put("searchcount", totalinfoDto.getJoinSearchCount());
      // 結合全件数
      result.put("totalcount", totalinfoDto.getJoinAllCount());
    } else {
      // 商品情報
      String json = new ListToJson<GoodsTotalinfoDto>().listToJson(totalinfoDto.getGoodsTotalinfoDtoList());
      result.put(ITEMMASTER, json);
      // 商品検索数
      result.put("searchcount", totalinfoDto.getGoodsSearchCount());
      // 商品全件数
      result.put("totalcount", totalinfoDto.getGoodsAllCount());
    }
      result.put("maxcount", searchConditionDto.getMaxCount());
      logger.debug("累積情報の検索処理を終了します。");
    return result;
  }
  
  /** totalinfo画面 */
  private static final String TOTALINFO = "totalinfo/totalinfo";
  /** ITEMMASTER */
  private static final String ITEMMASTER = "ITEMMASTER";
  /** JOINMASTER */
  private static final String JOINMASTER = "JOINMASTER";
  /** STEMASTER */
  private static final String SETMASTER = "SETMASTER";
  /**  毎ページ最大表示件数 */
  private static final String MAXCOUNT_BY_PAGE_KEY = "maxCountByPage";
  /** totalinfo取得 */
  private TotalinfoFacade totalinfoFacade;

  /**
   * <pre>
   * totalinfo取得を設定する。
   * </pre>
   *
   * @param totalinfoFacade 累積情報取得
   */
  @Resource
  public void setTotalinfoFacade(TotalinfoFacade totalinfoFacade) {
    this.totalinfoFacade = totalinfoFacade;
  }
  
  /** エンプロイーFacade */
  private CompanyInfoMasterCommonFacade companyInfoMasterCommonFacade;
  
  /**
   * <pre>
   * エンプロイーFacadeをセットする.
   * </pre>
   * 
   * @param companyInfoMasterCommonFacade CompanyInfoMasterCommonFacade
   */
  @Resource
  public void setCompanyInfoMasterCommonFacade(CompanyInfoMasterCommonFacade companyInfoMasterCommonFacade) {
    this.companyInfoMasterCommonFacade = companyInfoMasterCommonFacade;
  }

}
