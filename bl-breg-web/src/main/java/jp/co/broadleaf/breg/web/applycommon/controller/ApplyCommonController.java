//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : xuck
// 作 成 日       2017/02/07   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.applycommon.controller;

import jp.co.broadleaf.breg.applycommon.facade.ApplyCommonFacade;
import jp.co.broadleaf.breg.applyheadmastercommon.facade.dto.ApplyHeadMasterCommonDto;
import jp.co.broadleaf.breg.applyheadmastercommon.facade.dto.ApplyHeadMasterCommonInfoDto;
import jp.co.broadleaf.breg.applynewcategory.facade.ApplyNewCategoryFacade;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.enums.BlApplyResultsFlgEnum;
import jp.co.broadleaf.breg.goodslist.facade.GoodsMasterMakerFacade;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterCommonDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterCommonInfoDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerInfoDto;
import jp.co.broadleaf.breg.joinlist.facade.JoinMasterFacade;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterCommonDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterCommonInfoDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterInfoDto;
import jp.co.broadleaf.breg.setlist.facade.SetMasterFacade;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterCommonDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterCommonInfoDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterInfoDto;
import jp.co.broadleaf.breg.web.applycommon.form.ApplyCommonForm;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ApplyCommonControllerのクラス。
 */
@Controller
@RequestMapping("/applycommon")
public class ApplyCommonController extends BaseController {

  private static final String APPLY_URL = "/applycommon/applyCommon";
  private static final Short SHORT_0 = Short.valueOf("0");
  private static final Short SHORT_3 = Short.valueOf("3");
  private static final Integer INTEGER_0 = Integer.valueOf(0);
  /** Shift_JIS **/
  private static final String SHIFT_JIS = "Shift_JIS";

  private ApplyCommonFacade applyCommonFacade;
  private ApplyNewCategoryFacade applyNewCategoryFacede;
  private GoodsMasterMakerFacade goodsMasterMakerFacade;
  private SetMasterFacade setMasterFacade;
  private JoinMasterFacade joinMasterFacade;

  /**
   * <pre>
   * 初期表示時。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/applyCommon", method = RequestMethod.GET)
  public ModelAndView onload(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView result = new ModelAndView(APPLY_URL);
    try {
      applyCommonFacade.getMessage();
      request.getSession().removeAttribute("isLocked");
      // loginユーザーの情報収得
      String updAccountId = "";
      Integer partsMakerCd = Integer.valueOf(0);
      LoginPrincipal principal = SecurityContextHolder.getPrincipal();
      if (null != principal) {
        updAccountId = principal.getLoginId();
        partsMakerCd = principal.getMakerCode();
      }
      String isFromImport = (String) request.getSession().getAttribute(BroadleafSessionKeys.IS_FROM_IMPORT);
      // 件数初期化
      int goodsCount = 0;
      int setCount = 0;
      int joinCount = 0;
      List<Integer> countList = new ArrayList<>();
      if ("Y".equals(isFromImport)) {
        // インポート
        countList = onloadImport(request, goodsCount, setCount, joinCount, updAccountId, partsMakerCd);
      } else {
        // topMenu
        countList = onloadCommon(request, goodsCount, setCount, joinCount, updAccountId, partsMakerCd);
      }
      // request.getSession().setAttribute("goodsCount", goodsCount);
      // request.getSession().setAttribute("setCount", setCount);
      // request.getSession().setAttribute("joinCount", joinCount);
      if (!countList.isEmpty() && 3 <= countList.size()) {
        result.addObject("goodsCount", countList.get(0));
        result.addObject("setCount", countList.get(1));
        result.addObject("joinCount", countList.get(2));
      }
      // 申請前０件チェック
      request.getSession().setAttribute("goodsCountForCheck", countList.get(0));
      request.getSession().setAttribute("setCountForCheck", countList.get(1));
      request.getSession().setAttribute("joinCountForCheck", countList.get(2));
      // 申請内容
      String applyComments = (String) request.getSession().getAttribute("applyComments");
      if (null != applyComments) {
        result.addObject("applyComments", applyComments);
      } else {
        result.addObject("applyComments", "");
      }
      request.getSession().removeAttribute("applyComments");
      // 一般申請の時、sessionを削除する
      Boolean isSaved = (Boolean) request.getSession().getAttribute("isSaved");
      if (null != isSaved && isSaved) {
        request.getSession().removeAttribute(BroadleafSessionKeys.IS_FROM_IMPORT);
        request.getSession().removeAttribute("isSaved");
      }
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    return result;
  }

  /**
   * <pre>
   * 初期表示時。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param goodsCount int
   * @param setCount int
   * @param joinCount int
   * @param updAccountId String
   * @param partsMakerCd Integer
   * @return List
   */
  private List<Integer> onloadImport(HttpServletRequest request, int goodsCount, int setCount, int joinCount,
                                     String updAccountId, Integer partsMakerCd) {
    List<Integer> countList = new ArrayList<>();
    // 申請詳細へのsession
    // request.getSession().setAttribute("applyGoodsRoute", Boolean.TRUE);
    // request.getSession().setAttribute("applySetRoute", Boolean.TRUE);
    // request.getSession().setAttribute("applyJoinRoute", Boolean.TRUE);
    Boolean isSaved = (Boolean) request.getSession().getAttribute("isSaved");
    // 申請完了の場合
    if (null != isSaved && isSaved) {
      goodsCount = (Integer) request.getSession().getAttribute("goodsCount");
      request.getSession().removeAttribute("goodsCount");
      setCount = (Integer) request.getSession().getAttribute("setCount");
      request.getSession().removeAttribute("setCount");
      joinCount = (Integer) request.getSession().getAttribute("joinCount");
      request.getSession().removeAttribute("joinCount");
    } else {
      // 商品メーカー側検索
      GoodsMasterMakerInfoDto goodsMasterMakerInfoDto = applyCommonFacade.searchItemMasterInfoList(SHORT_0, SHORT_0,
          updAccountId, partsMakerCd, INTEGER_0, true, false);
      if (null != goodsMasterMakerInfoDto && !goodsMasterMakerInfoDto.getGoodsMasterDto().isEmpty()) {
        goodsCount = goodsMasterMakerInfoDto.getGoodsMasterDto().size();
      }
      request.getSession().setAttribute("goodsFromOnload", goodsMasterMakerInfoDto);
      // セットメーカー側検索
      SetMasterInfoDto setMasterInfoDto = applyCommonFacade.searchSetMasterInfoList(SHORT_0, SHORT_0, updAccountId,
          partsMakerCd, INTEGER_0, true, false);
      if (null != setMasterInfoDto && !setMasterInfoDto.getSetMasterDtoList().isEmpty()) {
        setCount = setMasterInfoDto.getSetMasterDtoList().size();
      }
      request.getSession().setAttribute("setFromOnload", setMasterInfoDto);
      // 結合メーカー側検索
      JoinMasterInfoDto joinMasterInfoDto = applyCommonFacade.searchJoinMasterInfoList(SHORT_0, SHORT_0, updAccountId,
          partsMakerCd, INTEGER_0, true, false);
      if (null != joinMasterInfoDto && !joinMasterInfoDto.getJoinMasterDto().isEmpty()) {
        joinCount = joinMasterInfoDto.getJoinMasterDto().size();
      }
      request.getSession().setAttribute("joinFromOnload", joinMasterInfoDto);
    }
    countList.add(goodsCount);
    countList.add(setCount);
    countList.add(joinCount);
    return countList;
  }

  /**
   * <pre>
   * 初期表示時。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param goodsCount int
   * @param setCount int
   * @param joinCount int
   * @param updAccountId String
   * @param partsMakerCd Integer
   * @return List
   */
  private List<Integer> onloadCommon(HttpServletRequest request, int goodsCount, int setCount, int joinCount,
                                     String updAccountId, Integer partsMakerCd) {
    // チェック選択テブールのデータ削除
    Boolean isSelected = (Boolean) request.getSession().getAttribute("isSelected");
    if (null == isSelected || !isSelected) {
      applyCommonFacade.removeBySelectKbn(0, partsMakerCd);
      applyCommonFacade.removeBySelectKbn(1, partsMakerCd);
      applyCommonFacade.removeBySelectKbn(2, partsMakerCd);
    }
    request.getSession().setAttribute("isSelected", Boolean.FALSE);

    List<Integer> countList = new ArrayList<>();
    // 機能制御
    setApplyButton(request, updAccountId, partsMakerCd);
    // 再申請フラグ収得
    Boolean isReApplication = (Boolean) request.getSession().getAttribute("isReApplication");

    GoodsMasterMakerInfoDto goodsMasterMakerInfoDto = new GoodsMasterMakerInfoDto();
    List<GoodsMasterMakerDto> goodsList = new ArrayList<>();
    SetMasterInfoDto setMasterMakerInfoDto = new SetMasterInfoDto();
    List<SetMasterDto> setList = new ArrayList<>();
    JoinMasterInfoDto joinMasterMakerInfoDto = new JoinMasterInfoDto();
    List<JoinMasterDto> joinList = new ArrayList<>();
    // 再申請の場合
    if (null != isReApplication && isReApplication) {
      // 商品メーカー側検索
      goodsMasterMakerInfoDto = applyCommonFacade.searchItemMasterInfoList(SHORT_0, SHORT_3, updAccountId, partsMakerCd,
          INTEGER_0, false, true);
      if (null != goodsMasterMakerInfoDto && !goodsMasterMakerInfoDto.getGoodsMasterDto().isEmpty()) {
        goodsCount = goodsMasterMakerInfoDto.getGoodsMasterDto().size();
      }
      goodsList = goodsMasterMakerInfoDto.getGoodsMasterDto();
      // セットメーカー側検索
      setMasterMakerInfoDto = applyCommonFacade.searchSetMasterInfoList(SHORT_0, SHORT_3, updAccountId, partsMakerCd,
          INTEGER_0, false, true);
      if (null != setMasterMakerInfoDto && !setMasterMakerInfoDto.getSetMasterDtoList().isEmpty()) {
        setCount = setMasterMakerInfoDto.getSetMasterDtoList().size();
      }
      setList = setMasterMakerInfoDto.getSetMasterDtoList();
      // 結合メーカー側検索
      joinMasterMakerInfoDto = applyCommonFacade.searchJoinMasterInfoList(SHORT_0, SHORT_3, updAccountId, partsMakerCd,
          INTEGER_0, false, true);
      if (null != joinMasterMakerInfoDto && !joinMasterMakerInfoDto.getJoinMasterDto().isEmpty()) {
        joinCount = joinMasterMakerInfoDto.getJoinMasterDto().size();
      }
      joinList = joinMasterMakerInfoDto.getJoinMasterDto();
    } else {
      Boolean isSaved = (Boolean) request.getSession().getAttribute("isSaved");
      // 申請完了の場合
      if (null != isSaved && isSaved) {
        goodsCount = (Integer) request.getSession().getAttribute("goodsCount");
        request.getSession().removeAttribute("goodsCount");
        setCount = (Integer) request.getSession().getAttribute("setCount");
        request.getSession().removeAttribute("setCount");
        joinCount = (Integer) request.getSession().getAttribute("joinCount");
        request.getSession().removeAttribute("joinCount");
      } else {
        // 商品選択画面のデータ収得
        GoodsMasterMakerInfoDto goodsMasterSelectInfoDto = applyCommonFacade.searchGoodsMasterInfoBySelect(partsMakerCd,
            0);
        if (null != goodsMasterSelectInfoDto) {
          goodsCount += goodsMasterSelectInfoDto.getGoodsMasterDto().size();
          for (GoodsMasterMakerDto goods : goodsMasterSelectInfoDto.getGoodsMasterDto()) {
            goodsList.add(goods);
          }
        }
        goodsMasterMakerInfoDto.setGoodsMasterDto(goodsList);
        // セット選択画面のデータ収得
        SetMasterInfoDto setMasterSelectInfoDto = applyCommonFacade.searchSetMasterInfoBySelect(partsMakerCd, 0);
        if (null != setMasterSelectInfoDto) {
          setCount += setMasterSelectInfoDto.getSetMasterDtoList().size();
          for (SetMasterDto set : setMasterSelectInfoDto.getSetMasterDtoList()) {
            setList.add(set);
          }
        }
        setMasterMakerInfoDto.setSetMasterDtoList(setList);
        // 結合選択画面のデータ収得
        JoinMasterInfoDto joinMasterSelectInfoDto = applyCommonFacade.searchJoinMasterInfoBySelect(partsMakerCd, 0);
        if (null != joinMasterSelectInfoDto) {
          joinCount += joinMasterSelectInfoDto.getJoinMasterDto().size();
          for (JoinMasterDto join : joinMasterSelectInfoDto.getJoinMasterDto()) {
            joinList.add(join);
          }
        }
        joinMasterMakerInfoDto.setJoinMasterDto(joinList);
      }
    }
    request.getSession().setAttribute("goodsFromOnload", goodsMasterMakerInfoDto);
    request.getSession().setAttribute("setFromOnload", setMasterMakerInfoDto);
    request.getSession().setAttribute("joinFromOnload", joinMasterMakerInfoDto);
    countList.add(goodsCount);
    countList.add(setCount);
    countList.add(joinCount);
    return countList;
  }

  /**
   * <pre>
   * チェックを実行する。
   * </pre>
   * 
   * @param form ApplyCommonForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return 処理結果
   */
  @RequestMapping(path = "/check", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult check(@RequestBody ApplyCommonForm form, HttpServletRequest request, HttpServletResponse response) {
    WebResult result = new WebResult();
    try {
      request.getSession().removeAttribute(BroadleafSessionKeys.IS_FROM_IMPORT);
      // form.validate();
      // check
      applyCommonFacade.getMessage();
      List<String> errorMessageList = getErrorMessage(request);
      if (!errorMessageList.isEmpty()) {
        result.put("errorMessageList", errorMessageList);
      }
      List<String> warningMessageList = getWarningMessage(request);
      if (!warningMessageList.isEmpty()) {
        result.put("warningMessageList", warningMessageList);
      }

      // 申請前０件チェック
      Integer goodsCount = (Integer) request.getSession().getAttribute("goodsCountForCheck");
      Integer setCount = (Integer) request.getSession().getAttribute("setCountForCheck");
      Integer joinCount = (Integer) request.getSession().getAttribute("joinCountForCheck");
      if (0 == goodsCount && 0 == setCount && 0 == joinCount) {
        result.put("zeroMessage", applyCommonFacade.getMessage(BregMessageCodes.I00802));
      }

      // 申請コメントが512文字を超えた場合
      String applyComments = form.getApplyComments();
      if (512 < applyComments.getBytes(SHIFT_JIS).length) {
        result.put("overBytes", "申請コメントが512桁数超過しています。");
      }
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    return result;
  }

  /**
   * <pre>
   * 申請処理を実行する。
   * </pre>
   * 
   * @param form ApplyCommonForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return 処理結果
   */
  @RequestMapping(path = "/applyCommon", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult apply(@RequestBody ApplyCommonForm form, HttpServletRequest request, HttpServletResponse response) {
    WebResult result = new WebResult();
    try {
      // session削除
      request.getSession().removeAttribute("importTypeMenu");
      // request.getSession().removeAttribute("goodsCount");
      // request.getSession().removeAttribute("setCount");
      // request.getSession().removeAttribute("joinCount");
      int applyNo = applyNewCategoryFacede.searchApplyNO() + 1;
      request.getSession().setAttribute("applyNo", applyNo);
      result.put("applyNo", applyNo);
      // 保存
      // 商品マスタ(共有)
      int goodsCommonSaveResult = saveGoodsMasterCommon(request, applyNo);
      if (-1 == goodsCommonSaveResult) {
        result.put("applyError", "Y");
        return result;
      }
      // セットマスタ(共有)
      int setCommonSaveResult = saveSetMasterCommon(request, applyNo);
      if (-1 == setCommonSaveResult) {
        // deleteGoodsCommon(request);
        result.put("applyError", "Y");
        return result;
      }
      // 結合マスタ(共有)
      int joinCommonSaveResult = saveJoinMasterCommon(request, applyNo);
      if (-1 == joinCommonSaveResult) {
        // deleteGoodsCommon(request);
        // deleteSetCommon(request);
        result.put("applyError", "Y");
        return result;
      }

      // 商品メーカー
      int goodsCount = saveGoodsMasterMaker(request);
      if (-1 == goodsCount) {
        // deleteGoodsCommon(request);
        // deleteSetCommon(request);
        // deleteJoinCommon(request);
        result.put("applyError", "Y");
        return result;
      }
      // セットメーカー
      int setCount = saveSetMasterMaker(request);
      if (-1 == setCount) {
        // deleteGoodsCommon(request);
        // deleteSetCommon(request);
        // deleteJoinCommon(request);
        result.put("applyError", "Y");
        return result;
      }
      // 結合メーカー
      int joinCount = saveJoinMasterMaker(request);
      if (-1 == joinCount) {
        // deleteGoodsCommon(request);
        // deleteSetCommon(request);
        // deleteJoinCommon(request);
        result.put("applyError", "Y");
        return result;
      }

      // 申請ヘッダマスタ
      ApplyHeadMasterCommonInfoDto applyHeadMasterCommonInfoDto = createApplyHeadMasterCommonDto(applyNo, form);
      int applyHeadMasterCount = applyCommonFacade.insertApplyHeadMaster(applyHeadMasterCommonInfoDto);
      if (-1 == applyHeadMasterCount) {
        // deleteGoodsCommon(request);
        // deleteSetCommon(request);
        // deleteJoinCommon(request);
        result.put("applyError", "Y");
        return result;
      }
      request.getSession().setAttribute("goodsCount", goodsCount);
      request.getSession().setAttribute("setCount", setCount);
      request.getSession().setAttribute("joinCount", joinCount);
      request.getSession().setAttribute("applyComments", form.getApplyComments());
      request.getSession().setAttribute("isSaved", Boolean.TRUE);
      result.put("goodsCount", goodsCount);
      result.put("setCount", setCount);
      result.put("joinCount", joinCount);

      // Mail
      LoginPrincipal principal = SecurityContextHolder.getPrincipal();
      int makerCode = principal.getMakerCode();

      // チェック選択テブールのデータ削除
      applyCommonFacade.removeBySelectKbn(0, makerCode);
      applyCommonFacade.removeBySelectKbn(1, makerCode);
      applyCommonFacade.removeBySelectKbn(2, makerCode);

      String loginId = principal.getLoginId();
      // 利用者のIPアドレス
      String ipAddr = getIpAddrByRequest(request);
      String applyResult = applyCommonFacade.sendMail(makerCode, loginId, ipAddr, goodsCount, setCount, joinCount);
      if (null != applyResult) {
        result.put("applyResult", applyResult);
      }
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    return result;
  }

  /**
   * <pre>
   * クリア
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   */
  @RequestMapping(path = "/clear", method = RequestMethod.POST)
  public void clear(HttpServletRequest request, HttpServletResponse response) {
    // sessionの内容削除
    request.getSession().removeAttribute(BroadleafSessionKeys.IS_FROM_IMPORT);
    request.getSession().removeAttribute("goodsCount");
    request.getSession().removeAttribute("setCount");
    request.getSession().removeAttribute("joinCount");
  }

  /**
   * <pre>
   * 選択画面に戻る
   * </pre>
   * 
   * @param form ApplyCommonForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return 処理結果
   * 
   */
  @RequestMapping(path = "/getapplycomments", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult getApplyCommonts(@RequestBody ApplyCommonForm form, HttpServletRequest request, HttpServletResponse response) {
    WebResult result = new WebResult();
    request.getSession().setAttribute("applyComments", form.getApplyComments());
    return result;
  }

  /**
   * <pre>
   * 機能制御。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param updAccountId String
   * @param partsMakerCd Integer
   */
  private void setApplyButton(HttpServletRequest request, String updAccountId, Integer partsMakerCd) {
    int checkCount = 0;
    // 商品メーカー側検索(check)
    GoodsMasterMakerInfoDto goodsMasterCheckInfoDto = applyCommonFacade.searchItemMasterInfoList(SHORT_0, SHORT_0,
        updAccountId, partsMakerCd, INTEGER_0, false, false);
    if (null != goodsMasterCheckInfoDto && !goodsMasterCheckInfoDto.getGoodsMasterDto().isEmpty()) {
      checkCount++;
    }
    // セットメーカー側検索(check)
    SetMasterInfoDto setMasterCheckInfoDto = applyCommonFacade.searchSetMasterInfoList(SHORT_0, SHORT_0, updAccountId,
        partsMakerCd, INTEGER_0, false, false);
    if (null != setMasterCheckInfoDto && !setMasterCheckInfoDto.getSetMasterDtoList().isEmpty()) {
      checkCount++;
    }
    // 結合メーカー側検索(check)
    JoinMasterInfoDto joinMasterCheckInfoDto = applyCommonFacade.searchJoinMasterInfoList(SHORT_0, SHORT_0,
        updAccountId, partsMakerCd, INTEGER_0, false, false);
    if (null != joinMasterCheckInfoDto && !joinMasterCheckInfoDto.getJoinMasterDto().isEmpty()) {
      checkCount++;
    }
    if (0 < checkCount) {
      request.getSession().setAttribute("isLocked", "Y");
    }
  }

  /**
   * <pre>
   * WarningMessage。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @return List
   */
  private List<String> getWarningMessage(HttpServletRequest request) {
    List<String> warningMessageList = new ArrayList<>();
    // 商品
    GoodsMasterMakerInfoDto goodsMasterMakerInfoDto = (GoodsMasterMakerInfoDto) request.getSession()
        .getAttribute("goodsFromOnload");
    if (null != goodsMasterMakerInfoDto) {
      for (GoodsMasterMakerDto goodsMasterMakerDto : goodsMasterMakerInfoDto.getGoodsMasterDto()) {
        applyCommonFacade.checkGoodsWarning(warningMessageList, goodsMasterMakerDto);
      }
    }
    // 結合
    JoinMasterInfoDto joinMasterInfoDto = (JoinMasterInfoDto) request.getSession().getAttribute("joinFromOnload");
    if (null != joinMasterInfoDto) {
      for (JoinMasterDto joinMasterMakerDto : joinMasterInfoDto.getJoinMasterDto()) {
        applyCommonFacade.checkJoinWarning(warningMessageList, joinMasterMakerDto);
      }
    }
    return warningMessageList;
  }

  /**
   * <pre>
   * getErrorMessage。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @return List
   */
  private List<String> getErrorMessage(HttpServletRequest request) throws Throwable {
    List<String> errorMessageList = new ArrayList<>();
    try {
      // 商品
      GoodsMasterMakerInfoDto goodsMasterMakerInfoDto = (GoodsMasterMakerInfoDto) request.getSession()
          .getAttribute("goodsFromOnload");
      if (null != goodsMasterMakerInfoDto) {
        for (GoodsMasterMakerDto goodsMasterMakerDto : goodsMasterMakerInfoDto.getGoodsMasterDto()) {
          applyCommonFacade.checkGoodsError(errorMessageList, goodsMasterMakerDto);
        }
      }
      // セット
      SetMasterInfoDto setMasterInfoDto = (SetMasterInfoDto) request.getSession().getAttribute("setFromOnload");
      if (null != setMasterInfoDto) {
        for (SetMasterDto setMasterMakerDto : setMasterInfoDto.getSetMasterDtoList()) {
          applyCommonFacade.checkSetError(errorMessageList, setMasterMakerDto);
        }
      }
      // 結合
      JoinMasterInfoDto joinMasterInfoDto = (JoinMasterInfoDto) request.getSession().getAttribute("joinFromOnload");
      if (null != joinMasterInfoDto) {
        for (JoinMasterDto joinMasterMakerDto : joinMasterInfoDto.getJoinMasterDto()) {
          applyCommonFacade.checkJoinError(errorMessageList, joinMasterMakerDto);
        }
      }
    } catch (Throwable e) {
      throw e;
    }
    return errorMessageList;
  }

  /**
   * <pre>
   * GoodsMasterMaker保存。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @return int
   */
  private int saveGoodsMasterMaker(HttpServletRequest request) {
    try {
      GoodsMasterMakerInfoDto goodsMasterMakerInfoDto = (GoodsMasterMakerInfoDto) request.getSession()
          .getAttribute("goodsFromOnload");
      if (null != goodsMasterMakerInfoDto) {
        GoodsMasterMakerInfoDto goodsMasterMakerInfoDtoNew = new GoodsMasterMakerInfoDto();
        List<GoodsMasterMakerDto> goodsMasterMakerDtoList = new ArrayList<>();
        for (GoodsMasterMakerDto goodsMasterMakerDto : goodsMasterMakerInfoDto.getGoodsMasterDto()) {
          // 適用日付
          // goodsMasterMakerDto.setStartDate(new
          // Timestamp(System.currentTimeMillis()));
          // 申請状態
          goodsMasterMakerDto.setApplyCondition((short)BlApplyResultsFlgEnum.Apply.getValue());
          goodsMasterMakerDtoList.add(goodsMasterMakerDto);
        }
        goodsMasterMakerInfoDtoNew.setGoodsMasterDto(goodsMasterMakerDtoList);
        for (GoodsMasterMakerDto goodsMasterMakerDto : goodsMasterMakerInfoDtoNew.getGoodsMasterDto()) {
          goodsMasterMakerFacade.updateGoodsMaster(goodsMasterMakerDto);
        }
        return goodsMasterMakerInfoDto.getGoodsMasterDto().size();
      }
    } catch (Throwable ex) {
      return -1;
    }
    return 0;
  }

  /**
   * <pre>
   * GoodsMasterCommon保存。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param applyNo int
   * @return int
   */
  private int saveGoodsMasterCommon(HttpServletRequest request, int applyNo) {
    try {
      GoodsMasterMakerInfoDto goodsMasterMakerInfoDto = (GoodsMasterMakerInfoDto) request.getSession()
          .getAttribute("goodsFromOnload");
      if (null != goodsMasterMakerInfoDto) {
        GoodsMasterCommonInfoDto goodsMasterCommonInfoDto = new GoodsMasterCommonInfoDto();
        List<GoodsMasterCommonDto> goodsMasterCommonDtoList = new ArrayList<>();
        for (GoodsMasterMakerDto goodsMasterMakerDto : goodsMasterMakerInfoDto.getGoodsMasterDto()) {
          GoodsMasterCommonDto goodsMasterCommonDto = new GoodsMasterCommonDto();
          // 申請No
          goodsMasterCommonDto.setApplyNo(applyNo);
          // BL登録区分
          goodsMasterCommonDto.setBlEntryFlg(goodsMasterMakerDto.getBlEntryFlg());
          // 処理区分
          goodsMasterCommonDto.setDealFlg(goodsMasterMakerDto.getManageKbn());
          // 削除依頼区分
          goodsMasterCommonDto.setDeleteFig(goodsMasterMakerDto.getDeleteFlg());
          // 削除理由
          goodsMasterCommonDto.setDeleteReason(goodsMasterMakerDto.getDeleteReason());
          // 装備名称
          goodsMasterCommonDto.setEquipName(goodsMasterMakerDto.getEquipName());
          // データステータス
          goodsMasterCommonDto.setErrFlg(goodsMasterMakerDto.getErrorFlg());
          // エラー内容
          goodsMasterCommonDto.setErrorDetail(goodsMasterMakerDto.getErrorDetail());
          // 商品詳細(B向け)
          goodsMasterCommonDto.setGoodsDetailB(goodsMasterMakerDto.getGoodsDetailB());
          // 商品詳細(C向け)
          goodsMasterCommonDto.setGoodsDetailC(goodsMasterMakerDto.getGoodsDetailC());
          // 商品中分類コード
          goodsMasterCommonDto.setGoodsMGroup(goodsMasterMakerDto.getGoodsMGroup());
          // 商品サイズ(長さ）
          goodsMasterCommonDto.setGoodsSize1(goodsMasterMakerDto.getGoodsSize1());
          // 商品サイズ(幅）
          goodsMasterCommonDto.setGoodsSize2(goodsMasterMakerDto.getGoodsSize2());
          // 商品サイズ(高さ）
          goodsMasterCommonDto.setGoodsSize3(goodsMasterMakerDto.getGoodsSize3());
          // 商品重量
          goodsMasterCommonDto.setGoodsWeight(goodsMasterMakerDto.getGoodsWeight());
          // 画像数
          goodsMasterCommonDto.setImageNo(goodsMasterMakerDto.getImageNo());
          // JAN
          goodsMasterCommonDto.setJan(goodsMasterMakerDto.getJan());
          // 新価格
          goodsMasterCommonDto.setNewPrice(goodsMasterMakerDto.getNewPrice());
          // オープン価格区分
          goodsMasterCommonDto.setOpenPriceDiv(goodsMasterMakerDto.getOpenPriceDiv());
          // 梱包サイズ(長さ）
          goodsMasterCommonDto.setPackageSize1(goodsMasterMakerDto.getPackageSize1());
          // 梱包サイズ(幅）
          goodsMasterCommonDto.setPackageSize2(goodsMasterMakerDto.getPackageSize2());
          // 梱包サイズ(高さ）
          goodsMasterCommonDto.setPackageSize3(goodsMasterMakerDto.getPackageSize3());
          // 層別コード
          goodsMasterCommonDto.setPartsLayerCd(goodsMasterMakerDto.getPartsLayerCd());
          // 部品メーカーコード
          goodsMasterCommonDto.setPartsMakerCd(goodsMasterMakerDto.getPartsMakerCd());
          // 優良部品カナ名称
          goodsMasterCommonDto.setPrimePartsKanaNm(goodsMasterMakerDto.getPrimePartsKanaNm());
          // 優良部品名称
          goodsMasterCommonDto.setPrimePartsName(goodsMasterMakerDto.getPrimePartsName());
          // 優良品番(－付き品番)
          goodsMasterCommonDto.setPrimePartsNoWithH(goodsMasterMakerDto.getPrimePartsNoWithH());
          // 優良部品規格・特記事項(B向け)
          goodsMasterCommonDto.setPrimePartsSpecialNoteB(goodsMasterMakerDto.getPrimePartsSpecialNoteB());
          // 優良部品規格・特記事項(C向け)
          goodsMasterCommonDto.setPrimePartsSpecialNoteC(goodsMasterMakerDto.getPrimePartsSpecialNoteC());
          // 優良設定詳細コード１
          goodsMasterCommonDto.setPrmSetDtlNo1(goodsMasterMakerDto.getPrmSetDtlNo1());
          // サイズ単位
          goodsMasterCommonDto.setSizeUnit(goodsMasterMakerDto.getSizeUnit());
          // 適用日付
          goodsMasterCommonDto.setStartTime(goodsMasterMakerDto.getStartDate());
          // BLコード
          goodsMasterCommonDto.setTbsPartsCode(goodsMasterMakerDto.getTbsPartsCode());
          // URL1
          goodsMasterCommonDto.setUrl1(goodsMasterMakerDto.getUrl1());
          // URL2
          goodsMasterCommonDto.setUrl2(goodsMasterMakerDto.getUrl2());
          // URL3
          goodsMasterCommonDto.setUrl3(goodsMasterMakerDto.getUrl3());
          // 重量単位
          goodsMasterCommonDto.setWeightUnit(goodsMasterMakerDto.getWeightUnit());
          goodsMasterCommonDtoList.add(goodsMasterCommonDto);
        }
        goodsMasterCommonInfoDto.setGoodsMasterDto(goodsMasterCommonDtoList);
        for (GoodsMasterCommonDto goodsMasterCommonDto : goodsMasterCommonInfoDto.getGoodsMasterDto()) {
          applyCommonFacade.updateGoodsMaster(goodsMasterCommonDto);
        }
        request.getSession().setAttribute("goodsCommonFromSaved", goodsMasterCommonInfoDto);
        return goodsMasterCommonInfoDto.getGoodsMasterDto().size();
      }
    } catch (Throwable ex) {
      // エラーを返す
      return -1;
    }
    return 0;
  }

  /**
   * <pre>
   * SetMasterMaker保存。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @return int
   */
  private int saveSetMasterMaker(HttpServletRequest request) {
    try {
      SetMasterInfoDto setMasterInfoDto = (SetMasterInfoDto) request.getSession().getAttribute("setFromOnload");
      if (null != setMasterInfoDto) {
        SetMasterInfoDto setMasterInfoDtoNew = new SetMasterInfoDto();
        List<SetMasterDto> setMasterDtoList = new ArrayList<>();
        for (SetMasterDto setMasterDto : setMasterInfoDto.getSetMasterDtoList()) {
          // 適用日付
          // setMasterDto.setStartTime(new
          // Timestamp(System.currentTimeMillis()));
          // 申請状態
          setMasterDto.setApplyCondition((short)BlApplyResultsFlgEnum.Apply.getValue());
          setMasterDtoList.add(setMasterDto);
        }
        setMasterInfoDtoNew.setSetMasterDtoList(setMasterDtoList);
        for (SetMasterDto setMasterDto : setMasterInfoDtoNew.getSetMasterDtoList()) {
          setMasterFacade.updateSetMaster(setMasterDto, "maker");
        }
        return setMasterInfoDto.getSetMasterDtoList().size();
      }
    } catch (Throwable ex) {
      return -1;
    }
    return 0;
  }

  /**
   * <pre>
   * SetMasterCommon保存。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param applyNo int
   * @return int
   */
  private int saveSetMasterCommon(HttpServletRequest request, int applyNo) {
    try {
      SetMasterInfoDto setMasterInfoDto = (SetMasterInfoDto) request.getSession().getAttribute("setFromOnload");
      if (null != setMasterInfoDto) {
        SetMasterCommonInfoDto setMasterCommonInfoDto = new SetMasterCommonInfoDto();
        List<SetMasterCommonDto> setMasterCommonDtoList = new ArrayList<>();
        for (SetMasterDto setMasterDto : setMasterInfoDto.getSetMasterDtoList()) {
          SetMasterCommonDto setMasterCommonDto = new SetMasterCommonDto();
          // 申請No
          setMasterCommonDto.setApplyNo(applyNo);
          // BL登録区分
          setMasterCommonDto.setBlEntryFlg(setMasterDto.getBlEntryFlg());
          // 処理区分
          setMasterCommonDto.setDealFlg(setMasterDto.getManageKbn());
          // 削除依頼区分
          setMasterCommonDto.setDeleteFig(setMasterDto.getDeleteFlg());
          // 削除理由
          setMasterCommonDto.setDeleteReason(setMasterDto.getDeleteReason());
          // データステータス
          setMasterCommonDto.setErrFlg(setMasterDto.getErrorFlg());
          // 商品中分類コード
          setMasterCommonDto.setGoodsMGroup(setMasterDto.getGoodsMGroup());
          // 部品メーカーコード
          setMasterCommonDto.setPartsMakerCd(setMasterDto.getPartsMakerCd());
          // 優良部品規格・特記事項(C向け)
          setMasterCommonDto.setPrimePartsSpecialNoteC(setMasterDto.getPrimePartsSpecialNoteC());
          // 優良設定詳細コード１
          setMasterCommonDto.setPrmSetDtlNo1(setMasterDto.getPrmSetDtlNo1());
          // セット表示順位
          setMasterCommonDto.setSetDispOrder(setMasterDto.getSetDispOrder());
          // 品名
          setMasterCommonDto.setSetKanaName(setMasterDto.getSetKanaName());
          // セット親品番
          setMasterCommonDto.setSetMainPartsNo(setMasterDto.getSetMainPartsNo());
          // セット名称
          setMasterCommonDto.setSetName(setMasterDto.getSetName());
          // セットQTY
          setMasterCommonDto.setSetQty(setMasterDto.getSetQty());
          // セット規格・特記事項
          setMasterCommonDto.setSetSpecialNote(setMasterDto.getSetSpecialNote());
          // セット子品番
          setMasterCommonDto.setSetSubPartsNo(setMasterDto.getSetSubPartsNo());
          // startTime
          setMasterCommonDto.setStartTime(setMasterDto.getStartTime());
          // BLコード
          setMasterCommonDto.setTbsPartsCode(setMasterDto.getTbsPartsCode());
          setMasterCommonDtoList.add(setMasterCommonDto);
        }
        setMasterCommonInfoDto.setSetMasterCommonDtoList(setMasterCommonDtoList);
        for (SetMasterCommonDto setMasterCommonDto : setMasterCommonInfoDto.getSetMasterCommonDtoList()) {
          applyCommonFacade.updateSetMaster(setMasterCommonDto);
        }
        request.getSession().setAttribute("SetCommonFromSaved", setMasterCommonInfoDto);
        return setMasterCommonInfoDto.getSetMasterCommonDtoList().size();
      }
    } catch (Throwable ex) {
      return -1;
    }
    return 0;
  }

  /**
   * <pre>
   * JoinMasterMaker保存。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @return int
   */
  private int saveJoinMasterMaker(HttpServletRequest request) {
    try {
      JoinMasterInfoDto joinMasterInfoDto = (JoinMasterInfoDto) request.getSession().getAttribute("joinFromOnload");
      if (null != joinMasterInfoDto) {
        JoinMasterInfoDto joinMasterInfoDtoNew = new JoinMasterInfoDto();
        List<JoinMasterDto> joinMasterDtoList = new ArrayList<>();
        for (JoinMasterDto joinMasterDto : joinMasterInfoDto.getJoinMasterDto()) {
          // 適用日付
          // joinMasterDto.setStartTime(new
          // Timestamp(System.currentTimeMillis()));
          // 申請状態
          joinMasterDto.setApplyCondition((short)BlApplyResultsFlgEnum.Apply.getValue());
          joinMasterDtoList.add(joinMasterDto);
        }
        joinMasterInfoDtoNew.setJoinMasterDto(joinMasterDtoList);
        for (JoinMasterDto joinMasterDto : joinMasterInfoDtoNew.getJoinMasterDto()) {
          joinMasterFacade.updateJoinMaster(joinMasterDto);
        }
        return joinMasterInfoDto.getJoinMasterDto().size();
      }
    } catch (Throwable ex) {
      return -1;
    }
    return 0;
  }

  /**
   * <pre>
   * JoinMasterCommon保存。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param applyNo int
   * @return int
   */
  private int saveJoinMasterCommon(HttpServletRequest request, int applyNo) {
    try {
      JoinMasterInfoDto joinMasterInfoDto = (JoinMasterInfoDto) request.getSession().getAttribute("joinFromOnload");
      if (null != joinMasterInfoDto) {
        JoinMasterCommonInfoDto joinMasterCommonInfoDto = new JoinMasterCommonInfoDto();
        List<JoinMasterCommonDto> joinMasterCommonDtoList = new ArrayList<>();
        for (JoinMasterDto joinMasterDto : joinMasterInfoDto.getJoinMasterDto()) {
          JoinMasterCommonDto joinMasterCommonDto = new JoinMasterCommonDto();
          // 申請No
          joinMasterCommonDto.setApplyNo(applyNo);
          // BL登録区分
          joinMasterCommonDto.setBlEntryFlg(joinMasterDto.getBlEntryFlg());
          // 処理区分
          joinMasterCommonDto.setDealFlg(joinMasterDto.getManageKbn());
          // 削除依頼区分
          joinMasterCommonDto.setDeleteFig(joinMasterDto.getDeleteFlg());
          // 削除時申請理由
          joinMasterCommonDto.setDeleteReason(joinMasterDto.getDeleteReason());
          // データステータス
          joinMasterCommonDto.setErrFlg(joinMasterDto.getErrorFlg());
          // 商品中分類コード
          joinMasterCommonDto.setGoodsMGroup(joinMasterDto.getGoodsMGroup());
          // 結合先品番(－付き品番)
          joinMasterCommonDto.setJoinDestPartsNo(joinMasterDto.getJoinDestPartsNo());
          // 結合表示順位
          joinMasterCommonDto.setJoinDispOrder(joinMasterDto.getJoinDispOrder());
          // 結合QTY
          joinMasterCommonDto.setJoinQty(joinMasterDto.getJoinQty());
          // 結合元メーカーコード
          joinMasterCommonDto.setJoinSourceMakerCode(joinMasterDto.getJoinSourceMakerCode());
          // 結合元品番(－付き品番)
          joinMasterCommonDto.setJoinSourPartsNoWithH(joinMasterDto.getJoinSourPartsNoWithH());
          // 結合規格・特記事項
          joinMasterCommonDto.setJoinSpecialNote(joinMasterDto.getJoinSpecialNote());
          // 部品メーカーコード
          joinMasterCommonDto.setPartsMakerCd(joinMasterDto.getPartsMakerCd());
          // 優良部品規格・特記事項(C向け)
          joinMasterCommonDto.setPrimePartsSpecialNotec(joinMasterDto.getPrimePartsSpecialNoteC());
          // 優良設定詳細コード１
          joinMasterCommonDto.setPrmSetDtlNo1(joinMasterDto.getPrmSetDtlNo1());
          // 優良設定詳細コード２
          joinMasterCommonDto.setPrmSetDtlNo2(joinMasterDto.getPrmSetDtlNo2());
          // 適用日付
          joinMasterCommonDto.setStartTime(joinMasterDto.getStartTime());
          // BLコード
          joinMasterCommonDto.setTbsPartsCode(joinMasterDto.getTbsPartsCode());
          joinMasterCommonDtoList.add(joinMasterCommonDto);
        }
        joinMasterCommonInfoDto.setJoinMasterCommonDto(joinMasterCommonDtoList);
        for (JoinMasterCommonDto joinMasterCommonDto : joinMasterCommonInfoDto.getJoinMasterCommonDto()) {
          applyCommonFacade.updateJoinMaster(joinMasterCommonDto);
        }
        request.getSession().setAttribute("joinCommonFromSaved", joinMasterCommonInfoDto);
        return joinMasterCommonInfoDto.getJoinMasterCommonDto().size();
      }
    } catch (Throwable ex) {
      return -1;
    }
    return 0;
  }

  /**
   * <pre>
   * ApplyHeadMasterCommonDto作成。
   * </pre>
   * 
   * @param applyNo int
   * @param form ApplyCommonForm
   * @return ApplyHeadMasterCommonInfoDto
   */
  private ApplyHeadMasterCommonInfoDto createApplyHeadMasterCommonDto(int applyNo, ApplyCommonForm form) {
    ApplyHeadMasterCommonInfoDto applyHeadMasterCommonInfoDto = new ApplyHeadMasterCommonInfoDto();
    List<ApplyHeadMasterCommonDto> applyHeadMasterCommonDtoList = new ArrayList<>();
    ApplyHeadMasterCommonDto applyHeadMasterCommonDto = new ApplyHeadMasterCommonDto();
    // 部品メーカーコード
    LoginPrincipal principal = SecurityContextHolder.getPrincipal();
    applyHeadMasterCommonDto.setPartsMakerCd(principal.getMakerCode());
    // 申請No
    applyHeadMasterCommonDto.setApplyNo(applyNo);
    // 申請時コメント
    applyHeadMasterCommonDto.setApplyComments(form.getApplyComments());
    // 申請日時
    applyHeadMasterCommonDto.setApplyDtTime(new Timestamp(System.currentTimeMillis()));
    // BL申請結果区分
    applyHeadMasterCommonDto.setBlApplyResultsFlg((short)BlApplyResultsFlgEnum.Apply.getValue());
    // BL申請結果コメント
    applyHeadMasterCommonDto.setBlApplyResultsComments("");
    // BL申請判断日
    applyHeadMasterCommonDto.setBlApplyJudgmentDate(Integer.valueOf(0));
    // BL申請判断時間
    applyHeadMasterCommonDto.setBlApplyJudgmentTime(Integer.valueOf(0));
    // 申請種類
    applyHeadMasterCommonDto.setApplyType(Integer.valueOf(0));
    applyHeadMasterCommonDtoList.add(applyHeadMasterCommonDto);
    applyHeadMasterCommonInfoDto.setApplyHeadMasterCommonDto(applyHeadMasterCommonDtoList);
    return applyHeadMasterCommonInfoDto;
  }

  /**
   * <pre>
   * IPアドレスを取得する.
   * </pre>
   * 
   * @param request HttpServletRequest
   * @return IPアドレス
   */
  private String getIpAddrByRequest(HttpServletRequest request) {
    // IPアドレス x-forwarded-for
    String ip = request.getHeader("x-forwarded-for");
    // IPアドレス Proxy-Client-IP
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    // IPアドレス WL-Proxy-Client-IP
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    // IPアドレス getRemoteAddr
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  /**
   * <pre>
   * 申請取得を設定する。
   * </pre>
   *
   * @param applyCommonFacade 申請取得
   */
  @Resource
  public void setApplyCommonFacade(ApplyCommonFacade applyCommonFacade) {
    this.applyCommonFacade = applyCommonFacade;
  }

  /**
   * <pre>
   * 【applyNewCategoryFacede】を設定する。
   * </pre>
   *
   * @param applyNewCategoryFacede 【applyNewCategoryFacede】
   */
  @Resource
  public void setApplyNewCategoryFacede(ApplyNewCategoryFacade applyNewCategoryFacede) {
    this.applyNewCategoryFacede = applyNewCategoryFacede;
  }

  /**
   * <pre>
   * 【goodsMasterMakerFacade】を設定する。
   * </pre>
   *
   * @param goodsMasterMakerFacade 【goodsMasterMakerFacade】
   */
  @Resource
  public void setGoodsMasterMakerFacade(GoodsMasterMakerFacade goodsMasterMakerFacade) {
    this.goodsMasterMakerFacade = goodsMasterMakerFacade;
  }

  /**
   * <pre>
   * 【setMasterFacade】を設定する。
   * </pre>
   *
   * @param setMasterFacade 【setMasterFacade】
   */
  @Resource
  public void setSetMasterFacade(SetMasterFacade setMasterFacade) {
    this.setMasterFacade = setMasterFacade;
  }

  /**
   * <pre>
   * 【joinMasterFacade】を設定する。
   * </pre>
   *
   * @param joinMasterFacade 【joinMasterFacade】
   */
  @Resource
  public void setJoinMasterFacade(JoinMasterFacade joinMasterFacade) {
    this.joinMasterFacade = joinMasterFacade;
  }

}
