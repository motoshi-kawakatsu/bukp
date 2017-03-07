//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                作成担当 : magy
// 作 成 日       2017/02/06   修正内容 : インポート結果:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.importresult.controller;

import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.common.enums.ImportTypeEnum;
import jp.co.broadleaf.breg.importresult.facade.ImportResultFacade;
import jp.co.broadleaf.breg.importresult.facade.dto.ImportResultDto;
import jp.co.broadleaf.breg.web.importresult.form.ImportResultForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.web.BroadleafSessionKeys;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.Submission;
import jp.co.broadleaf.framework.web.WebResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * インポート結果Controllerクラス.
 */
@Controller
@RequestMapping("/importresult")
public class ImportResultController extends BaseController {

  /**
   * インポート結果画面初期化.
   * 
   * @param migrationURL 遷移先URL
   * @param request HttpServletRequest
   * @return 表示するURL
   */
  @RequestMapping(path = "importresult", method = RequestMethod.GET)
  public ModelAndView init(@RequestParam(name = "return_url", required = false) String migrationURL,
                           HttpServletRequest request) {
    logger.debug("インポート結果の初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(IMPORT_RESULT_URL);

    try {
      ImportResultDto importResultDto = new ImportResultDto();
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();

      if (loginPrincipal != null) {
        importResultDto.setMakerCode(loginPrincipal.getMakerCode());
        importResultDto.setLoginId(loginPrincipal.getLoginId());
      }

      HttpSession session = request.getSession();

      importResultDto.setGoodsImportType(session.getAttribute(GOODS_TYPE) == null ? ImportTypeEnum.NoImport
          : (ImportTypeEnum) session.getAttribute(GOODS_TYPE));
      importResultDto.setSetImportType((ImportTypeEnum) session.getAttribute(SET_TYPE) == null ? ImportTypeEnum.NoImport
          : (ImportTypeEnum) session.getAttribute(SET_TYPE));
      importResultDto.setJoinImportType((ImportTypeEnum) session.getAttribute(JOIN_TYPE) == null
          ? ImportTypeEnum.NoImport : (ImportTypeEnum) session.getAttribute(JOIN_TYPE));

      importResultDto.setImportKbnEnum(session.getAttribute(IMPORT_TYPE_MENU) == null ? ImportKbnEnum.Import
          : (ImportKbnEnum) session.getAttribute(IMPORT_TYPE_MENU));

      request.setAttribute(RETURN_URL, migrationURL);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("インポート結果の初期表示処理を終了します。");
    return result;
  }

  /**
   * インポート結果取込.
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return 処理結果
   */
  @ResponseBody
  @Submission(multiply = false)
  @RequestMapping(path = "importresult", method = RequestMethod.POST)
  public WebResult torikomi(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("インポート結果のインポート結果取込処理を開始します。");
    WebResult result = new WebResult();
    try {
      ImportResultDto importResultDto = new ImportResultDto();
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();

      if (loginPrincipal != null) {
        importResultDto.setMakerCode(loginPrincipal.getMakerCode());
        importResultDto.setLoginId(loginPrincipal.getLoginId());
      }

      HttpSession session = request.getSession();

      importResultDto.setGoodsImportType(session.getAttribute(GOODS_TYPE) == null ? ImportTypeEnum.All
          : (ImportTypeEnum) session.getAttribute(GOODS_TYPE));
      importResultDto.setSetImportType((ImportTypeEnum) session.getAttribute(SET_TYPE) == null ? ImportTypeEnum.All
          : (ImportTypeEnum) session.getAttribute(SET_TYPE));
      importResultDto.setJoinImportType((ImportTypeEnum) session.getAttribute(JOIN_TYPE) == null ? ImportTypeEnum.All
          : (ImportTypeEnum) session.getAttribute(JOIN_TYPE));
      importResultDto.setImportKbnEnum(session.getAttribute(IMPORT_TYPE_MENU) == null ? ImportKbnEnum.Import
          : (ImportKbnEnum) session.getAttribute(IMPORT_TYPE_MENU));
      importResultDto = importResultFacade.checkImportResult(importResultDto);

      // 商品
      result.put("goodsTotal", importResultDto.getGoodsCount());
      result.put("goodsOkCount", importResultDto.getGoodsCount() - importResultDto.getGoodsErrorCount());
      result.put("goodsErrorCount", importResultDto.getGoodsErrorCount());
      // セット
      result.put("setTotal", importResultDto.getSetCount());
      result.put("setOkCount", importResultDto.getSetCount() - importResultDto.getSetErrorCount());
      result.put("setErrorCount", importResultDto.getSetErrorCount());

      // 結合
      result.put("joinTotal", importResultDto.getJoinCount());
      result.put("joinOkCount", importResultDto.getJoinCount() - importResultDto.getJoinErrorCount());
      result.put("joinErrorCount", importResultDto.getJoinErrorCount());

      if (importResultDto.getGoodsErrorCount() == 0 && importResultDto.getSetErrorCount() == 0
          && importResultDto.getJoinErrorCount() == 0) {
        importResultFacade.torikomiResult(importResultDto);
        int[] goodsInfo = importResultDto.getGoodsInfo();
        int[] setInfo = importResultDto.getSetInfo();
        int[] joinInfo = importResultDto.getJoinInfo();
        session.setAttribute(BroadleafSessionKeys.GOODS_INFO, goodsInfo);
        session.setAttribute(BroadleafSessionKeys.SET_INFO, setInfo);
        session.setAttribute(BroadleafSessionKeys.JOIN_INFO, joinInfo);
        int unManage = goodsInfo[0];
        int unManageSet = setInfo[0];
        int unManageJoin = joinInfo[0];

        result.put("goodsUn", unManage);
        result.put("setUn", unManageSet);
        result.put("joinUn", unManageJoin);

      }
      result.put("sendurl", IMPORT_RESULT_URL);

    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("インポート結果のインポート結果取込処理を終了します。");
    return result;

  }

  /**
   * インポート結果取込.
   * 
   * @param form インポート結果 用のForm
   * @param request HttpServletRequest
   * @return 処理結果
   */
  @ResponseBody
  @RequestMapping(path = "reinit", method = RequestMethod.POST)
  public WebResult reInit(@RequestBody ImportResultForm form, HttpServletRequest request) {
    logger.debug("インポート結果の画面刷新処理を開始します。");
    WebResult result = new WebResult();
    try {
      ImportResultDto importResultDto = new ImportResultDto();
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();

      if (loginPrincipal != null) {
        importResultDto.setMakerCode(loginPrincipal.getMakerCode());
        importResultDto.setLoginId(loginPrincipal.getLoginId());
      }

      HttpSession session = request.getSession();

      importResultDto.setGoodsImportType(session.getAttribute(GOODS_TYPE) == null ? ImportTypeEnum.All
          : (ImportTypeEnum) session.getAttribute(GOODS_TYPE));
      importResultDto.setSetImportType((ImportTypeEnum) session.getAttribute(SET_TYPE) == null ? ImportTypeEnum.All
          : (ImportTypeEnum) session.getAttribute(SET_TYPE));
      importResultDto.setJoinImportType((ImportTypeEnum) session.getAttribute(JOIN_TYPE) == null ? ImportTypeEnum.All
          : (ImportTypeEnum) session.getAttribute(JOIN_TYPE));
      importResultDto.setImportKbnEnum(session.getAttribute(IMPORT_TYPE_MENU) == null ? ImportKbnEnum.Import
          : (ImportKbnEnum) session.getAttribute(IMPORT_TYPE_MENU));
      importResultDto.setImportType(form.getImportTypeMenu());
      importResultDto = importResultFacade.checkImportResult(importResultDto);

      // 商品
      result.put("goodsTotal", importResultDto.getGoodsCount());
      result.put("goodsOkCount", importResultDto.getGoodsCount() - importResultDto.getGoodsErrorCount());
      result.put("goodsErrorCount", importResultDto.getGoodsErrorCount());
      // セット
      result.put("setTotal", importResultDto.getSetCount());
      result.put("setOkCount", importResultDto.getSetCount() - importResultDto.getSetErrorCount());
      result.put("setErrorCount", importResultDto.getSetErrorCount());

      // 結合
      result.put("joinTotal", importResultDto.getJoinCount());
      result.put("joinOkCount", importResultDto.getJoinCount() - importResultDto.getJoinErrorCount());
      result.put("joinErrorCount", importResultDto.getJoinErrorCount());

    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("インポート結果の画面刷新処理を終了します。");
    return result;

  }

  /**
   * インポートURL
   */
  public static final String IMPORT_RESULT_URL = "importresult/importresult";

  /**
   * インポートURL
   */
  public static final String RETURN_URL = "return_url";

  /** 起動の場合:「インポート」や「インポート（一括申請）」 */
  private static final String IMPORT_TYPE_MENU = "importTypeMenu";

  /**
   * 商品インポート類型
   */
  public static final String GOODS_TYPE = "goodsType";
  /**
   * セットインポート類型
   */
  public static final String SET_TYPE = "setType";
  /**
   * 結合インポート類型
   */
  public static final String JOIN_TYPE = "joinType";

  /**
   * 商品総数
   */
  public static final String GOODS_TOTAL = "goodsTotal";
  /**
   * セット総数
   */
  public static final String SET_TOTAL = "setTotal";
  /**
   * 結合総数
   */
  public static final String JOIN_TOTAL = "joinTotal";

  /**
   * 商品OK数
   */
  public static final String GOODS_OK = "goodsOkCount";
  /**
   * セットOK数
   */
  public static final String SET_OK = "setOkCount";
  /**
   * 結合OK数
   */
  public static final String JOIN_OK = "joinOkCount";

  /**
   * 商品OK数
   */
  public static final String GOODS_ERROR = "goodsErrorCount";
  /**
   * セットOK数
   */
  public static final String SET_ERROR = "setErrorCount";
  /**
   * 結合OK数
   */
  public static final String JOIN_ERROR = "joinErrorCount";
  /**
   * 商品情報
   */
  public static final String GOODS_INFO = "goodsInfo";
  /**
   * セット情報
   */
  public static final String SET_INFO = "setInfo";
  /**
   * 結合情報
   */
  public static final String JOIN_INFO = "joinInfo";

  /**
   * インポート結果Facadeインタフェース
   */
  private ImportResultFacade importResultFacade;

  /**
   * 【importResultFacade】を取得する。
   * 
   * @return 【importResultFacade】
   */
  public ImportResultFacade getImportResultFacade() {
    return importResultFacade;
  }

  /**
   * 【importResultFacade】を設定する。
   * 
   * @param importResultFacade 【importResultFacade】
   */
  @Resource
  public void setImportResultFacade(ImportResultFacade importResultFacade) {
    this.importResultFacade = importResultFacade;
  }
}
