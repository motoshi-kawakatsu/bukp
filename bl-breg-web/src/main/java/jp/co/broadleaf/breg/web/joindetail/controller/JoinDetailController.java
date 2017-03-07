//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 李超傑
// 作 成 日       2017/02/06   修正内容 : 結合詳細：新規作成
package jp.co.broadleaf.breg.web.joindetail.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.co.broadleaf.breg.common.dto.BlCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.SelectCodeMasterDto;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.joindetail.facade.JoinDetailFacade;
import jp.co.broadleaf.breg.joindetail.facade.dto.JoinDetailDto;
import jp.co.broadleaf.breg.joinlist.facade.JoinMasterFacade;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinGridDto;
import jp.co.broadleaf.breg.web.joinlist.form.JoinListForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.web.BroadleafSessionKeys;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.Submission;
import jp.co.broadleaf.framework.web.WebResult;

/**
 * <pre>
 * 結合詳細Controllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/joindetail")
public class JoinDetailController extends BaseController {

  /** BLコード */
  private static final String BLCODE = "blcodeinfo";

  /** セレクトコード */
  private static final String SELECTERCODE = "selecterCode";

  /** モード */
  private static final String MODE = "mode";

  /** 結合詳細画面のURL */
  private static final String JOIN_DETAIL_URL = "joindetail/joinDetail";

  /** 新規モード */
  private static final Integer NEW = 0;

  /** 更新モード */
  private static final Integer UPDATE_MODE = 1;

  /** 参照モード */
  private static final Integer REFERENCE_MODE = 2;

  /** エラー修正モード */
  private static final Integer ERROR_MODE = 3;
  
  /** 追加モード */
  private static final Integer DETAIL_ADD = 4;

  /** 初期表示時のリスト */
  private static final String INIT_LIST = "initList";

  /**
   * 処理区分の変数名
   */
  private static final String MANAGEKBN = "manageKbn";
  /**
   * カーコードの変数名
   */
  private static final String CARMAKERNAMEMAP = "carmakerNameMap";
  /**
   * エラー区分の変数名
   */
  private static final String ERRORFLAG = "errorFlg";

  /**
   * 申請状態の変数名
   */
  private static final String APPLYCONDITION = "applyCondition";

  /**
   * <pre>
   * 初期表示時。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/joinDetail", method = RequestMethod.GET)
  @Submission(multiply = true)
  public ModelAndView joinDetalInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("結合詳細の初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(JOIN_DETAIL_URL);
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = loginPrincipal.getMakerCode();
    // BLコードマスタを取得します。
    List<BlCodeMasterDto> blCodeInfo = joinDetailFacade.getBlCodeInfo(makerCode);

    // セレクトコード情報を取得
    List<SelectCodeMasterDto> selectCodeInfo = joinDetailFacade.getSelectCodeInfo(makerCode);
    // カーコード情報を取得
    Map<String, String> carmakerNameMap = joinMasterFacade.getCarmakerNameMap(makerCode);

    result.addObject(BLCODE, blCodeInfo);
    result.addObject(SELECTERCODE, selectCodeInfo);
    // カーコード
    result.addObject(CARMAKERNAMEMAP, carmakerNameMap);
    result.addObject(MANAGEKBN, ManageKbnEnum.values());
    result.addObject(ERRORFLAG, ErrorFlgEnum.values());
    result.addObject(APPLYCONDITION, ApplyConditionEnum.values());
    result.addObject(MODE, request.getQueryString());
    logger.debug("結合詳細の初期表示処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * グリッド初期表示時。
   * </pre>
   *
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/gridinit", method = RequestMethod.POST)
  @ResponseBody
  public WebResult joinDetalGridInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("結合詳細のグリッド初期表示処理を開始します。");
    // 結合詳細検索用フォームを取得
    JoinListForm form = (JoinListForm) request.getSession().getAttribute(BroadleafSessionKeys.JOIN_LIST_TO_DETAIL);
    request.getSession().removeAttribute(BroadleafSessionKeys.JOIN_LIST_TO_DETAIL);
    WebResult result = new WebResult();
    List<JoinDetailDto> list = new ArrayList<>();

    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = loginPrincipal.getMakerCode();

    Map<String, String> kindCodeMap = joinMasterFacade.getKindCodeNameMap(makerCode);
    String kindCode = "";
    if (!kindCodeMap.isEmpty()) {
      for (Map.Entry<String, String> entry : kindCodeMap.entrySet()) {
        kindCode = kindCode + entry.getValue() + ',';
      }
    }
    kindCode = kindCode.substring(0, kindCode.length() - 1);

    if (form == null || form.getJoinGridList() == null) {
      result.put(INIT_LIST, list);
      result.put("kindCode", kindCode);
      request.getSession().setAttribute("joinDetailMode",DETAIL_ADD);
      return result;
    }

    JoinGridDto dto = form.getJoinGridList().get(0);

    request.getSession().setAttribute("joinDetailMode", form.getMode());
    // 更新モード、参照モードの場合
    if (UPDATE_MODE.equals(form.getMode()) || REFERENCE_MODE.equals(form.getMode()) || NEW.equals(form.getMode())) {
      // 項目チェック
      if (isLegal(dto)) {
        // 初期のリストを取得します。

        list = joinDetailFacade.getJoinDetail(dto.getPrmSetDtlNo1().split("：")[0], String.valueOf(makerCode),
            dto.getTbsPartsCode().split("：")[0], dto.getJoinSourceMakerCode().split("：")[0],
            dto.getPrmSetDtlNo2().split("：")[0], dto.getJoinSourPartsNoWithH().split("：")[0],
            dto.getJoinDestPartsNo() == null || dto.getJoinDestPartsNo().isEmpty() ? null
                : dto.getJoinDestPartsNo().split("：")[0]);
      }
    }
    // エラー修正モード
    if (ERROR_MODE.equals(form.getMode())) {
      // 項目チェック
      if (isLegal(dto)) {
        // 初期のリストを取得します。
        list = joinDetailFacade.getJoinMasterWorkList(dto.getPrmSetDtlNo1().split("：")[0], String.valueOf(makerCode),
            dto.getTbsPartsCode().split("：")[0], dto.getJoinSourceMakerCode().split("：")[0],
            dto.getPrmSetDtlNo2().split("：")[0], dto.getJoinSourPartsNoWithH().split("：")[0],
            dto.getJoinDestPartsNo() == null || dto.getJoinDestPartsNo().isEmpty() ? null
                : dto.getJoinDestPartsNo().split("：")[0]);

      }
    }
    result.put(INIT_LIST, list);
    result.put("kindCode", kindCode);
    // ヘッダー部の分類コード
    result.put("goodsMGroup", dto.getGoodsMGroup());
    // ヘッダー部の 純正品番
    result.put("joinSourPartsNoWithH", dto.getJoinSourPartsNoWithH());
    logger.debug("結合詳細のグリッド初期表示処理を終了します。");
    return result;
  }

  /**
   * joinDetailFacade
   */
  private JoinDetailFacade joinDetailFacade;

  /**
   * 結合詳細Facadeのセットメッソード
   * 
   * @param joinDetailFacade 結合詳細Facade
   */
  @Resource
  public void setJoinDetailFacade(JoinDetailFacade joinDetailFacade) {
    this.joinDetailFacade = joinDetailFacade;
  }

  /**
   * 項目チェックします
   * 
   * @param dto 結合詳細検索用フォーム
   * @return true:チェック通過 false:チェック不通過
   */
  private boolean isLegal(JoinGridDto dto) {
    if (null == dto.getJoinSourPartsNoWithH() || dto.getJoinSourPartsNoWithH().isEmpty()) {
      return false;
    }
    if (null == dto.getTbsPartsCode() || dto.getTbsPartsCode().isEmpty()) {
      return false;
    }
    if (null == dto.getPrmSetDtlNo1() || dto.getPrmSetDtlNo1().isEmpty()) {
      return false;
    }
    if (null == dto.getJoinSourceMakerCode() || dto.getJoinSourceMakerCode().isEmpty()) {
      return false;
    }
    if (null == dto.getPrmSetDtlNo2() || dto.getPrmSetDtlNo2().isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * webCheck
   * @param listForm listForm
   * @param request request
   * @param response response
   * @return WebResult
   */
  @RequestMapping(path = "/comfirm", method = RequestMethod.POST)
  @ResponseBody
  public WebResult webCheck(@RequestBody List<JoinDetailDto> listForm, HttpServletRequest request,
                            HttpServletResponse response) {
    WebResult result = new WebResult();
    int mode=(int)request.getSession().getAttribute("joinDetailMode");
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = loginPrincipal.getMakerCode();
    boolean isErrorExist = joinDetailFacade.checkImportList(listForm, makerCode,mode);
    result.put("isError", isErrorExist);
    result.put("refreshGrid", listForm);
    return result;
  }

  /** 結合一覧Facade */
  private JoinMasterFacade joinMasterFacade;

  /**
   * <pre>
   * 結合一覧Facadeを設定する。
   * </pre>
   *
   * @param joinMasterFacade Facade
   */
  @Resource
  public void setJoinMasterFacade(JoinMasterFacade joinMasterFacade) {
    this.joinMasterFacade = joinMasterFacade;

  }

}
