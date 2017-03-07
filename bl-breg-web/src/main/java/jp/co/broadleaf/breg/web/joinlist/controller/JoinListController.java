//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 王　天コン
// 作 成 日       2017/02/06   修正内容 : 結合一覧：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.joinlist.controller;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.BlEntryFlgEnum;
import jp.co.broadleaf.breg.common.enums.CheckFlgEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.common.enums.JudgeEnum;
import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.common.enums.ModeEnum;
import jp.co.broadleaf.breg.common.model.ExportOutModel;
import jp.co.broadleaf.breg.joinlist.facade.JoinMasterFacade;
import jp.co.broadleaf.breg.joinlist.facade.JoinMasterWorkFacade;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinGridDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterInfoDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterSearchDto;
import jp.co.broadleaf.breg.web.checklist.controller.CheckListController;
import jp.co.broadleaf.breg.web.common.controller.ReportOutController;
import jp.co.broadleaf.breg.web.joinlist.form.JoinListForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.BroadleafFormatUtils;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * 結合一覧Controllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/joinlist")
public class JoinListController extends BaseController {

  /**
   * <pre>
   * 結合一覧画面を表示する.
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/joinList", method = RequestMethod.GET)
  public ModelAndView init(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("結合一覧の初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(JOINLIST_URL);
    Map<String, Object> enumMap = new HashMap<String, Object>();
    enumMap.put("applyCondition", ApplyConditionEnum.values());
    enumMap.put("manageKbn", ManageKbnEnum.values());
    enumMap.put("deleteFlg", DeleteFlgEnum.values());
    result.addAllObjects(enumMap);
    logger.debug("結合一覧の初期表示処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 初期化。
   * </pre>
   *
   * @param form JoinListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/joinList", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult setGridInit(@RequestBody JoinListForm form, HttpServletRequest request,
                               HttpServletResponse response) {
    logger.debug("結合一覧のグリッド初期表示処理を開始します。");
    WebResult result = new WebResult();
    try {
      Boolean isControl = joinMasterFacade.isControl();
      Integer mode = form.getMode();
      Integer importKbn = form.getImportKbn();
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      int makerCode = loginPrincipal.getMakerCode();
      Map<String, String> blCodeNameMap = joinMasterFacade.getBlCodeNameMap(makerCode);
      Map<String, String> selectCodeNameMap = joinMasterFacade.getSelectCodeNameMap(makerCode);
      Map<String, String> kindCodeNameMap = joinMasterFacade.getKindCodeNameMap(makerCode);
      Map<String, String> classifyGuideNameMap = joinMasterFacade.getClassifyCodeGuideMap(makerCode);
      Map<String, String> carmakerNameMap = joinMasterFacade.getCarmakerNameMap(makerCode);
      Map<String, String> primeCodeNameMap = joinMasterFacade.getPrimeCodeMap(makerCode);
      Map<String, String> pureCodeNameMap = joinMasterFacade.getPureCodeMap(makerCode);
      Map<Integer, String> applyConditionMap = new HashMap<>();
      if (mode.intValue() == ModeEnum.Error.getValue() || mode.intValue() == ModeEnum.Reference.getValue()) {
        applyConditionMap.put(ApplyConditionEnum.NoApply.getValue(), ApplyConditionEnum.NoApply.getName());
      } else if (mode.intValue() == ModeEnum.Select.getValue()) {
        applyConditionMap.put(ApplyConditionEnum.NoApply.getValue(), ApplyConditionEnum.NoApply.getName());
        applyConditionMap.put(ApplyConditionEnum.Applyagain.getValue(), ApplyConditionEnum.Applyagain.getName());
      }
      // メッセージ
      HashMap<String, String> messageMap = new HashMap<>();
      joinMasterFacade.getMessage(messageMap);
      Long joinRows = (long) joinMasterFacade.searchJoinListRows(makerCode);
      int pageNo = form.getPageNo() == null || form.getPageNo().isEmpty() ? 1 : Integer.parseInt(form.getPageNo());
      Long skipRows = (long) joinRows * (pageNo - 1);
      List<JoinGridDto> gridDataList = new ArrayList<>();
      int allNum = 0;
      int searchNum = 0;
      JoinMasterInfoDto joinMasterInfoDtoAll = null;
      if (mode.intValue() == ModeEnum.Input.getValue()) {
        allNum = joinMasterFacade.searchJoinMasterInfoDtoAll(null, null, false, makerCode, 3).getJoinMasterDto().size();
        joinMasterInfoDtoAll = joinMasterFacade.searchJoinMasterInfoDtoAll(skipRows, joinRows, true, makerCode, 3);
      } else if (mode.intValue() == ModeEnum.Select.getValue()) {
        allNum = joinMasterFacade.searchJoinMasterInfoDtoAll(null, null, false, makerCode, 0).getJoinMasterDto().size();
        searchNum = joinMasterFacade.searchJoinMasterInfoDtoAll(skipRows, -1L, true, makerCode, 0).getJoinMasterDto()
            .size();
        joinMasterInfoDtoAll = joinMasterFacade.searchJoinMasterInfoDtoAll(skipRows, joinRows, true, makerCode, 0);
      } else if (mode.intValue() == ModeEnum.Reference.getValue()) {
        int searchMode = 0;
        if (importKbn.intValue() == 0) {
          searchMode = 1;
        } else if (importKbn.intValue() == 1) {
          searchMode = 2;
        }
        allNum = joinMasterFacade.searchJoinMasterInfoDtoAll(null, null, false, makerCode, searchMode)
            .getJoinMasterDto().size();
        joinMasterInfoDtoAll = joinMasterFacade.searchJoinMasterInfoDtoAll(skipRows, joinRows, true, makerCode,
            searchMode);
      } else if (mode.intValue() == ModeEnum.Error.getValue()) {
        allNum = joinMasterWorkFacade.searchJoinMasterWorkAll(null, null, false, makerCode, importKbn)
            .getJoinMasterDto().size();
        joinMasterInfoDtoAll = joinMasterWorkFacade.searchJoinMasterWorkAll(skipRows, joinRows, true, makerCode,
            importKbn);
      }
      result.put("searchNum", allNum);
      if (mode.intValue() == ModeEnum.Select.getValue()) {
        result.put("searchNum", searchNum);
      }
      result.put("allNum", allNum);
      if (joinMasterInfoDtoAll != null) {
        for (JoinMasterDto joinMasterDto : joinMasterInfoDtoAll.getJoinMasterDto()) {
          JoinGridDto joinGridDto = convertJoinGridDto(joinMasterDto, blCodeNameMap, selectCodeNameMap, kindCodeNameMap,
              classifyGuideNameMap, carmakerNameMap, primeCodeNameMap, pureCodeNameMap);
          if (mode.intValue() == ModeEnum.Error.getValue()) {
            joinGridDto.setCheck(true);
          } else if (mode.intValue() == ModeEnum.Select.getValue()) {
            joinGridDto.setCheck(joinMasterFacade.getCheckDiv(joinMasterDto));
          }
          gridDataList.add(joinGridDto);
        }
      }
      result.put("gridData", getCheckList(gridDataList, makerCode));
      if (pageNo == 1) {
        // BLコード
        result.put("blCodeNameMap", blCodeNameMap);
        // セレクトコード
        result.put("selectCodeNameMap", selectCodeNameMap);
        // 種別コード
        result.put("kindCodeNameMap", kindCodeNameMap);
        // カーコード
        result.put("carmakerNameMap", carmakerNameMap);
        // 申請状態
        result.put("applyConditionMap", applyConditionMap);
      }
      result.put("maxRows", joinRows);
      result.put("messageMap", messageMap);
      result.put("controlFlag", isControl);
      request.getSession().setAttribute(BroadleafSessionKeys.IMPORTKBN, importKbn);
      request.getSession().setAttribute(BroadleafSessionKeys.JOIN_LISTFORM, form);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("結合一覧のグリッド初期表示処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 検索。
   * </pre>
   *
   * @param form JoinListForm
   * @param request HttpServletRequest
   * @return ModelAndView
   */
  @RequestMapping(path = "/search", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult setGridInit(@RequestBody JoinListForm form, HttpServletRequest request) {
    logger.debug("結合一覧の検索処理を開始します。");
    WebResult result = new WebResult();
    try {
      Integer mode = form.getMode();
      Integer importKbn = form.getImportKbn();
      // 全件数取得
      int allNum = 0;
      // 検索数
      int searchNum = 0;
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      int makerCode = loginPrincipal.getMakerCode();
      JoinMasterInfoDto joinMasterInfoDto = new JoinMasterInfoDto();
      List<JoinGridDto> gridDataList = new ArrayList<>();
      Long joinRows = (long) joinMasterFacade.searchJoinListRows(makerCode);
      int pageNo = form.getPageNo() == null || form.getPageNo().isEmpty() ? 1 : Integer.parseInt(form.getPageNo());
      Long skipRows = (long) joinRows * (pageNo - 1);
      JoinMasterSearchDto joinMasterSearchDto = setJoinMasterSearchDto(form);
      if (mode.intValue() == ModeEnum.Input.getValue() || mode.intValue() == ModeEnum.Reference.getValue()) {
        int searchMode = 3;
        if (mode.intValue() == ModeEnum.Reference.getValue() && importKbn == 0) {
          searchMode = 1;
        } else if (mode.intValue() == ModeEnum.Reference.getValue() && importKbn == 1) {
          searchMode = 2;
        }
        // 全件数取得
        allNum = joinMasterFacade.searchJoinMasterInfoDtoAll(null, null, false, makerCode, searchMode)
            .getJoinMasterDto().size();
        // 検索数取得
        searchNum = joinMasterFacade
            .searchJoinMasterInfoList(joinMasterSearchDto, form.getOrder(), null, null, false, searchMode)
            .getJoinMasterDto().size();
        // 検索結果取得
        joinMasterInfoDto = joinMasterFacade.searchJoinMasterInfoList(joinMasterSearchDto, form.getOrder(), skipRows,
            joinRows, true, searchMode);
      } else if (mode.intValue() == ModeEnum.Error.getValue()) {
        // 全件数取得
        allNum = joinMasterWorkFacade.searchJoinMasterWorkAll(null, null, false, makerCode, importKbn)
            .getJoinMasterDto().size();
        // 検索数取得
        searchNum = joinMasterWorkFacade
            .searchJoinMasterWorkInfoList(joinMasterSearchDto, form.getOrder(), null, null, false, importKbn)
            .getJoinMasterDto().size();
        // 検索結果取得
        joinMasterInfoDto = joinMasterWorkFacade.searchJoinMasterWorkInfoList(joinMasterSearchDto, form.getOrder(),
            skipRows, joinRows, true, importKbn);
        joinMasterSearchDto.setImportKbn(importKbn);
        request.getSession().setAttribute("joinErrorSearch", joinMasterInfoDto);
      } else if (mode.intValue() == ModeEnum.Select.getValue()) {
        // 全件数取得
        allNum = joinMasterFacade.searchJoinMasterInfoDtoAll(null, null, false, makerCode, 0).getJoinMasterDto().size();
        // 検索数取得
        searchNum = joinMasterFacade
            .searchJoinMasterInfoList(joinMasterSearchDto, form.getOrder(), null, null, false, 0).getJoinMasterDto()
            .size();
        // 検索結果取得
        joinMasterInfoDto = joinMasterFacade.searchJoinMasterInfoList(joinMasterSearchDto, form.getOrder(), skipRows,
            joinRows, true, 0);
        joinMasterInfoDto = joinMasterFacade.searchJoinMasterInfoList(joinMasterInfoDto);
      }
      Map<String, String> blCodeNameMap = joinMasterFacade.getBlCodeNameMap(makerCode);
      Map<String, String> selectCodeNameMap = joinMasterFacade.getSelectCodeNameMap(makerCode);
      Map<String, String> kindCodeMap = joinMasterFacade.getKindCodeNameMap(makerCode);
      Map<String, String> classifyGuideNameMap = joinMasterFacade.getClassifyCodeGuideMap(makerCode);
      Map<String, String> carmakerNameMap = joinMasterFacade.getCarmakerNameMap(makerCode);
      Map<String, String> primeCodeNameMap = joinMasterFacade.getPrimeCodeMap(makerCode);
      Map<String, String> pureCodeNameMap = joinMasterFacade.getPureCodeMap(makerCode);
      // 一覧へ設定
      for (JoinMasterDto joinMasterDto : joinMasterInfoDto.getJoinMasterDto()) {
        JoinGridDto joinGridDto = convertJoinGridDto(joinMasterDto, blCodeNameMap, selectCodeNameMap, kindCodeMap,
            classifyGuideNameMap, carmakerNameMap, primeCodeNameMap, pureCodeNameMap);
        if (mode.intValue() == ModeEnum.Error.getValue()) {
          joinGridDto.setCheck(true);
        } else if (mode.intValue() == ModeEnum.Select.getValue()) {
          joinGridDto.setCheck(joinMasterFacade.getCheckDiv(joinMasterDto));
        }
        gridDataList.add(joinGridDto);
      }
      result.put("gridData", getCheckList(gridDataList, makerCode));
      result.put("searchNum", searchNum);
      result.put("allNum", allNum);
      result.put("maxRows", joinRows);
      request.getSession().setAttribute("joinSearch", joinMasterSearchDto);
      request.getSession().setAttribute("joinSearchOrder", form.getOrder());
      request.getSession().setAttribute(BroadleafSessionKeys.JOIN_LISTFORM, form);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("結合一覧の検索処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)ワーク検索。
   * </pre>
   *
   * @param form JoinListForm
   * @param request HttpServletRequest
   * @return ModelAndView
   */
  @RequestMapping(path = "/searchJoinWork", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult searhJoinWork(@RequestBody JoinListForm form, HttpServletRequest request) {
    logger.debug("結合一覧の結合マスタ(メーカー)ワーク検索処理を開始します。");
    WebResult result = new WebResult();
    try {
      Integer importKbn = form.getImportKbn();
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      int makerCode = loginPrincipal.getMakerCode();
      Long joinRows = (long) joinMasterFacade.searchJoinListRows(makerCode);
      int pageNo = form.getPageNo() == null || form.getPageNo().isEmpty() ? 1 : Integer.parseInt(form.getPageNo());
      Long skipRows = (long) joinRows * (pageNo - 1);
      JoinMasterSearchDto joinMasterSearchDto = setJoinMasterSearchDto(form);
      // 検索結果取得
      JoinMasterInfoDto joinMasterInfoDto = joinMasterWorkFacade.searchJoinMasterWorkInfoList(joinMasterSearchDto,
          form.getOrder(), skipRows, joinRows, true, importKbn);
      request.getSession().setAttribute("joinErrorSearch", joinMasterInfoDto);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("結合一覧の結合マスタ(メーカー)ワーク検索処理を終了します。");
    return result;
  }

  /**
   * 保存
   * 
   * @param form ProductListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/save", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult onclickBtnSave(@RequestBody JoinListForm form, HttpServletRequest request,
                                  HttpServletResponse response) {
    logger.debug("結合一覧の保存処理を開始します。");
    WebResult result = new WebResult();
    try {
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      int makerCode = loginPrincipal.getMakerCode();
      Integer mode = form.getMode();
      List<JoinGridDto> dataSourceList = form.getJoinGridList();
      List<JoinMasterDto> insertList = new ArrayList<>();
      List<JoinMasterDto> modifyList = new ArrayList<>();
      List<JoinMasterDto> editJoinGridList = new ArrayList<>();
      boolean isModify = false;
      // 登録前チェック
      JoinMasterInfoDto joinMasterInfo = joinMasterFacade.checkImportList(dataSourceList, makerCode);
      Boolean isError = joinMasterInfo.getIsErrorExist();
      if (!isError) {
        dataSourceList = new ArrayList<>();
        for (JoinGridDto joinGridDto : joinMasterInfo.getJoinGridDtoList()) {
          JoinMasterDto joinMasterDto = new JoinMasterDto();
          // 新規登録の場合
          if (joinGridDto.getHiddenArea().intValue() == JudgeEnum.Add.getValue()) {
            joinMasterDto = convertJoinMasterMakerDto(joinGridDto, JudgeEnum.Add.getValue());
            insertList.add(joinMasterDto);
            joinGridDto.setHiddenArea(1);
            dataSourceList.add(joinGridDto);
          }
          // 更新登録の場合
          else if (joinGridDto.getHiddenArea().intValue() == JudgeEnum.Update.getValue()) {
            joinMasterDto = convertJoinMasterMakerDto(joinGridDto, JudgeEnum.Update.getValue());
            if (mode.intValue() == ModeEnum.Input.getValue()) {
              joinMasterFacade.updateJoinMaster(joinMasterDto);
            } else if (mode.intValue() == ModeEnum.Error.getValue()) {
              joinMasterWorkFacade.updateJoinMasterWork(joinMasterDto);
            }
            joinGridDto.setHiddenArea(1);
            dataSourceList.add(joinGridDto);
          }
          // 物理削除の場合
          else if (joinGridDto.getHiddenArea().intValue() == JudgeEnum.Delete.getValue()) {
            joinMasterDto = convertJoinMasterMakerDto(joinGridDto, JudgeEnum.Add.getValue());
            joinMasterFacade.deleteJoinMaster(joinMasterDto);
          }
          // 修正の場合
          else if (joinGridDto.getHiddenArea().intValue() == JudgeEnum.Modify.getValue()) {
            joinMasterDto = convertJoinMasterMakerDto(joinGridDto, JudgeEnum.Modify.getValue());
            modifyList.add(joinMasterDto);
            dataSourceList.add(joinGridDto);
            isModify = true;

          }
          // 未変更の場合
          else if (joinGridDto.getHiddenArea().intValue() == JudgeEnum.UnChange.getValue()) {
            dataSourceList.add(joinGridDto);
          }
          editJoinGridList.add(convertJoinMasterMakerDto(joinGridDto, 0));
        }
        // 検索取得の改修前のリスト
        JoinMasterInfoDto joinMasterInfoWorkDto = (JoinMasterInfoDto) request.getSession()
            .getAttribute("joinErrorSearch");
        List<JoinMasterDto> joinMasterDtoList = joinMasterInfoWorkDto.getJoinMasterDto();
        if (isModify) {
          // 修正削除の場合
          List<JoinMasterDto> delJoinWorkList = joinMasterFacade.campareList(editJoinGridList, joinMasterDtoList);
          for (JoinMasterDto delJoinWork : delJoinWorkList) {
            joinMasterWorkFacade.deleteJoinMasterWork(delJoinWork);
          }
        }
        JoinMasterInfoDto joinMasterInfoDto = new JoinMasterInfoDto();
        joinMasterInfoDto.setJoinMasterDto(insertList);
        joinMasterFacade.insertJoinMasterInfoList(joinMasterInfoDto);
        for (JoinMasterDto joinMasterDto : modifyList) {
          joinMasterWorkFacade.updateJoinMasterWork(joinMasterDto);
        }
      }
      result.put("gridDataRefresh", dataSourceList);
      result.put("isError", isError);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("結合一覧の保存処理を終了します。");
    return result;
  }

  /**
   * 結合check
   * 
   * @param form JoinListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/check", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult check(@RequestBody JoinListForm form, HttpServletRequest request, HttpServletResponse response) {
    logger.debug("結合一覧の結合チェック処理を開始します。");
    WebResult result = new WebResult();
    try {
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      int makerCode = loginPrincipal.getMakerCode();
      // 一覧テータ
      List<JoinGridDto> dataSourceList = form.getJoinGridList();
      // 一覧テータ更新
      result.put("gridData", getCheckList(dataSourceList, makerCode));
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("結合一覧の結合チェック処理を終了します。");
    return result;
  }

  /**
   * 結合check結果を取得
   * 
   * @param dataSourceList dataSourceList
   * @param makerCode makerCode
   * @return ｊoinGridDtoList
   */
  private List<JoinGridDto> getCheckList(List<JoinGridDto> dataSourceList, int makerCode) {
    JoinMasterInfoDto joinMasterInfoDto = joinMasterFacade.checkImportList(dataSourceList, makerCode);
    return joinMasterInfoDto.getJoinGridDtoList();
  }

  /**
   * 詳細ボタンの処理
   * 
   * @param form JoinListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/detail", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult listToDetail(@RequestBody JoinListForm form, HttpServletRequest request,
                                HttpServletResponse response) {
    logger.debug("結合一覧の詳細ボタンの処理を開始します。");
    WebResult result = new WebResult();
    try {
      request.getSession().setAttribute(BroadleafSessionKeys.JOIN_LIST_TO_DETAIL, form);
      result.put(JOIN_DETAIL_KEY, JOIN_DETAIL_URL);
      // 確認メッセージを取得
      String msg = joinMasterWorkFacade.getMessage(BregMessageCodes.Q00001);
      result.put(CONFIRMMESSAGE, msg);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("結合一覧の詳細ボタンの処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 商品中分類コードガイド
   * </pre>
   * 
   * @param form JoinListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @ResponseBody
  @Submission(multiply = false)
  @RequestMapping(path = "/goodsMGroup", method = RequestMethod.POST)
  public WebResult goodsMGroup(@RequestBody JoinListForm form, HttpServletRequest request,
                               HttpServletResponse response) {
    logger.debug("結合一覧の商品中分類コードガイドモード設定処理を開始します。");
    WebResult result = new WebResult();
    HttpSession session = request.getSession();
    session.setAttribute(BroadleafSessionKeys.CLASSIFY_CODE_GUIDE, form.getGoodsMGroup());
    logger.debug("結合一覧の商品中分類コードガイドモード設定処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 純正品番
   * </pre>
   * 
   * @param form JoinListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @ResponseBody
  @Submission(multiply = false)
  @RequestMapping(path = "/joinSourPartsNoWithH", method = RequestMethod.POST)
  public WebResult joinSourPartsNoWithH(@RequestBody JoinListForm form, HttpServletRequest request,
                                        HttpServletResponse response) {
    logger.debug("結合一覧の純正品番設定処理を開始します。");
    WebResult result = new WebResult();
    HttpSession session = request.getSession();
    session.setAttribute(BroadleafSessionKeys.GOODS_GUIDE, form.getJoinSourPartsNoWithH());
    session.setAttribute(BroadleafSessionKeys.GOODS_CATEGORY, "pure");
    logger.debug("結合一覧の純正品番設定処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * 優良品番
   * </pre>
   * 
   * @param form JoinListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @ResponseBody
  @Submission(multiply = false)
  @RequestMapping(path = "/joinDestPartsNo", method = RequestMethod.POST)
  public WebResult joinDestPartsNo(@RequestBody JoinListForm form, HttpServletRequest request,
                                   HttpServletResponse response) {
    logger.debug("結合一覧の優良品番設定処理を開始します。");
    WebResult result = new WebResult();
    HttpSession session = request.getSession();
    session.setAttribute(BroadleafSessionKeys.GOODS_GUIDE, form.getJoinDestPartsNo());
    session.setAttribute(BroadleafSessionKeys.GOODS_CATEGORY, "maker");
    logger.debug("結合一覧の優良品番設定処理を終了します。");
    return result;
  }

  /**
   * 商品申請機能
   * 
   * @param form JoinListForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/certain", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult onclickBtnCertain(@RequestBody JoinListForm form, HttpServletRequest request,
                                     HttpServletResponse response) {
    logger.debug("結合一覧の商品申請機能処理を開始します。");
    WebResult result = new WebResult();
    try {
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      int makerCode = loginPrincipal.getMakerCode();
      for (JoinGridDto joinGridDto : form.getJoinGridList()) {
        joinMasterFacade.manegeSelectMaker(joinGridDto, makerCode);
      }
      request.getSession().setAttribute("isSelected", Boolean.TRUE);
      result.put("selectSize", joinMasterFacade.searchSelectBySelectKbn(makerCode));
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("結合一覧の商品申請機能処理を終了します。");
    return result;
  }

  /**
   * 帳票作成
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/makeFile", method = RequestMethod.GET)
  @ResponseBody
  public WebResult makeFile(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("結合一覧の帳票作成処理を開始します。");
    WebResult result = new WebResult();
    ExportOutModel exportOutModel = null;
    ReportOutController reportOutController = new ReportOutController();
    String fileType = request.getParameter("fileType");
    String fileName = getFileName(fileType);

    JoinListForm joinListForm = (JoinListForm) request.getSession().getAttribute(BroadleafSessionKeys.JOIN_LISTFORM);
    List<JoinGridDto> joinListDtos = getJoinGridList(joinListForm);
    int index = 1;
    for (JoinGridDto joinGridDto : joinListDtos) {
      joinGridDto.setNo(index);
      index = index + 1;
    }
    CheckListController checkListController = new CheckListController();
    exportOutModel = checkListController.makeJoinFile(result, request, fileType, fileName, joinListDtos);

    try {
      reportOutController.reportOut(exportOutModel, response, result);
    } catch (Exception e) {
      e.printStackTrace();
    }
    logger.debug("結合一覧の帳票作成処理を終了します。");
    return result;
  }

  /**
   * 結合back
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/back", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult back(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("結合一覧の結合戻る処理を開始します。");
    WebResult result = new WebResult();
    try {
      request.getSession().setAttribute("isSelected", Boolean.TRUE);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("結合一覧の結合戻る処理を終了します。");
    return result;
  }

  /**
   * ファイル名称を取得
   * 
   * @param fileType ファイルタイブ
   * @return ファイル名称
   */
  private String getFileName(String fileType) {
    String fileName = "結合_".concat(BroadleafFormatUtils.dateToString(new Date(), BroadleafFormatUtils.DATE_F009));
    if (fileType.equals("1")) {
      fileName = fileName.concat(".pdf");
    }
    if (fileType.equals("2")) {
      fileName = fileName.concat(".tsv");
    }
    if (fileType.equals("3")) {
      fileName = fileName.concat(".xls");
    }
    return fileName;
  }

  /**
   * 検索条件
   * 
   * @param form JoinListForm
   * @return JoinMasterSearchDto
   */
  private JoinMasterSearchDto setJoinMasterSearchDto(JoinListForm form) {
    JoinMasterSearchDto joinMasterSearchDto = new JoinMasterSearchDto();
    // 1ページの件数
    if (form.getMaxRows() == null || form.getMaxRows().isEmpty()) {
      joinMasterSearchDto.setMaxRows(null);
    } else {
      joinMasterSearchDto.setMaxRows(Long.valueOf(form.getMaxRows()));
    }
    // 全件数
    if (form.getSkipRows() == null || form.getSkipRows().isEmpty()) {
      joinMasterSearchDto.setSkipRows(null);
    } else {
      joinMasterSearchDto.setSkipRows(Long.valueOf(form.getSkipRows()));
    }
    // BLコード
    if (form.getTbsPartsCode() == null || form.getTbsPartsCode().isEmpty()) {
      joinMasterSearchDto.setTbsPartsCode(null);
    } else {
      joinMasterSearchDto.setTbsPartsCode(Integer.parseInt(form.getTbsPartsCode()));
    }
    // 純正品番
    if (form.getJoinSourPartsNoWithH() == null || form.getJoinSourPartsNoWithH().isEmpty()) {
      joinMasterSearchDto.setJoinSourPartsNoWithH(null);
    } else {
      joinMasterSearchDto.setJoinSourPartsNoWithH(form.getJoinSourPartsNoWithH().split("：")[0]);
    }
    // 優良品番
    if (form.getJoinDestPartsNo() == null || form.getJoinDestPartsNo().isEmpty()) {
      joinMasterSearchDto.setJoinDestPartsNo(null);
    } else {
      joinMasterSearchDto.setJoinDestPartsNo(form.getJoinDestPartsNo().split("：")[0]);
    }
    // 申請状態
    if (form.getApplyCondition() == null || form.getApplyCondition().equals(ALL)) {
      joinMasterSearchDto.setApplyCondition(null);
    } else {
      joinMasterSearchDto.setApplyCondition(Short.valueOf(form.getApplyCondition()));
    }
    // 処理区分
    if (form.getManageKbn() == null || form.getManageKbn().equals(ALL)) {
      joinMasterSearchDto.setManageKbn(null);
    } else {
      joinMasterSearchDto.setManageKbn(Short.valueOf(form.getManageKbn()));
    }
    // データステータス
    if (form.getErrorFlg() == null || form.getErrorFlg().equals(ALL)) {
      joinMasterSearchDto.setErrorFlg(null);
    } else {
      joinMasterSearchDto.setErrorFlg(Short.valueOf(form.getErrorFlg()));
    }
    // セレクトコード
    if (form.getPrmSetDtlNo1() == null || form.getPrmSetDtlNo1().isEmpty()) {
      joinMasterSearchDto.setPrmSetDtlNo1(null);
    } else {
      joinMasterSearchDto.setPrmSetDtlNo1(Integer.parseInt(form.getPrmSetDtlNo1()));
    }
    // カーコード
    if (form.getJoinSourceMakerCode() == null || form.getJoinSourceMakerCode().isEmpty()) {
      joinMasterSearchDto.setJoinSourceMakerCode(null);
    } else {
      joinMasterSearchDto.setJoinSourceMakerCode(Integer.parseInt(form.getJoinSourceMakerCode()));
    }
    // 適用日時From
    if (form.getStartTimeStart() == null || form.getStartTimeStart().isEmpty()) {
      joinMasterSearchDto.setStartTimeStart(null);
    } else {
      joinMasterSearchDto.setStartTimeStart(new Timestamp(BroadleafFormatUtils
          .stringToDate(form.getStartTimeStart().concat("　00:00:00"), BroadleafFormatUtils.DATE_F007).getTime()));
    }
    // 適用日時To
    if (form.getStartTimeEnd() == null || form.getStartTimeEnd().isEmpty()) {
      joinMasterSearchDto.setStartTimeEnd(null);
    } else {
      joinMasterSearchDto.setStartTimeEnd(new Timestamp(BroadleafFormatUtils
          .stringToDate(form.getStartTimeEnd().concat("　23:59:59"), BroadleafFormatUtils.DATE_F007).getTime()));
    }
    // 分類コード
    if (form.getGoodsMGroup() == null || form.getGoodsMGroup().isEmpty()) {
      joinMasterSearchDto.setGoodsMGroup(null);
    } else {
      joinMasterSearchDto.setGoodsMGroup(Integer.parseInt(form.getGoodsMGroup().split("：")[0]));
    }
    // 種別コード
    if (form.getPrmSetDtlNo2() == null || form.getPrmSetDtlNo2().isEmpty()) {
      joinMasterSearchDto.setPrmSetDtlNo2(null);
    } else {
      joinMasterSearchDto.setPrmSetDtlNo2(Integer.parseInt(form.getPrmSetDtlNo2()));
    }
    setJoinMasterSearchExtraDto(form, joinMasterSearchDto);
    return joinMasterSearchDto;
  }

  /**
   * 検索条件
   * 
   * @param form JoinListForm
   * @param joinMasterSearchDto JoinMasterSearchDto
   */
  private void setJoinMasterSearchExtraDto(JoinListForm form, JoinMasterSearchDto joinMasterSearchDto) {
    // 作成日時From
    if (form.getInsDtTimeStart() == null || form.getInsDtTimeStart().isEmpty()) {
      joinMasterSearchDto.setInsDtTimeStart(null);
    } else {
      joinMasterSearchDto.setInsDtTimeStart(BroadleafFormatUtils
          .stringToDate(form.getInsDtTimeStart().concat("　00:00:00"), BroadleafFormatUtils.DATE_F007));
    }
    // 作成日時To
    if (form.getInsDtTimeEnd() == null || form.getInsDtTimeEnd().isEmpty()) {
      joinMasterSearchDto.setInsDtTimeEnd(null);
    } else {
      joinMasterSearchDto.setInsDtTimeEnd(BroadleafFormatUtils.stringToDate(form.getInsDtTimeEnd().concat("　23:59:59"),
          BroadleafFormatUtils.DATE_F007));
    }
    // 規格・特記
    if (form.getJoinSpecialNote() == null || form.getJoinSpecialNote().isEmpty()) {
      joinMasterSearchDto.setJoinSpecialNote(null);
    } else {
      joinMasterSearchDto.setJoinSpecialNote(form.getJoinSpecialNote());
    }
    // 削除依頼区分
    if (form.getDeleteFlg() == null || form.getDeleteFlg().equals(ALL)) {
      joinMasterSearchDto.setDeleteFlg(null);
    } else {
      joinMasterSearchDto.setDeleteFlg(Short.valueOf(form.getDeleteFlg()));
    }
    // 更新日時From
    if (form.getUpdDtTimeStart() == null || form.getUpdDtTimeStart().isEmpty()) {
      joinMasterSearchDto.setUpdDtTimeStart(null);
    } else {
      joinMasterSearchDto.setUpdDtTimeStart(BroadleafFormatUtils
          .stringToDate(form.getUpdDtTimeStart().concat("　00:00:00"), BroadleafFormatUtils.DATE_F007));
    }
    // 更新日時To
    if (form.getUpdDtTimeEnd() == null || form.getUpdDtTimeEnd().isEmpty()) {
      joinMasterSearchDto.setUpdDtTimeEnd(null);
    } else {
      joinMasterSearchDto.setUpdDtTimeEnd(BroadleafFormatUtils.stringToDate(form.getUpdDtTimeEnd().concat("　23:59:59"),
          BroadleafFormatUtils.DATE_F007));
    }
    // 優良部品規格・特記事項(C向け)
    if (form.getPrimePartsSpecialNoteC() == null || form.getPrimePartsSpecialNoteC().isEmpty()) {
      joinMasterSearchDto.setPrimePartsSpecialNoteC(null);
    } else {
      joinMasterSearchDto.setPrimePartsSpecialNoteC(form.getPrimePartsSpecialNoteC());
    }
    // 部品メーカーコード
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = loginPrincipal.getMakerCode();
    joinMasterSearchDto.setPartsMakerCd(makerCode);
  }

  /**
   * gridのdataに転換
   * 
   * @param joinMasterDto JoinMasterDto
   * @param blCodeNameMap blCodeNameMap
   * @param selectCodeNameMap selectCodeNameMap
   * @param kindCodeMap kindCodeMap
   * @param classifyGuideNameMap classifyGuideNameMap
   * @param carmakerNameMap carmakerNameMap
   * @param primeCodeNameMap primeCodeNameMap
   * @param pureCodeNameMap pureCodeNameMap
   * @return JoinGridDto
   */
  private JoinGridDto convertJoinGridDto(JoinMasterDto joinMasterDto, Map<String, String> blCodeNameMap,
                                         Map<String, String> selectCodeNameMap, Map<String, String> kindCodeMap,
                                         Map<String, String> classifyGuideNameMap, Map<String, String> carmakerNameMap,
                                         Map<String, String> primeCodeNameMap, Map<String, String> pureCodeNameMap) {
    JoinGridDto joinGridDto = new JoinGridDto();
    // 申請状態
    if (joinMasterDto.getApplyCondition() != null
        && ApplyConditionEnum.valueof(joinMasterDto.getApplyCondition()) != null) {
      joinGridDto.setApplyCondition(ApplyConditionEnum.valueof(joinMasterDto.getApplyCondition()).getAbbreviation());
    }
    // 処理区分
    if (joinMasterDto.getManageKbn() != null && ManageKbnEnum.valueof(joinMasterDto.getManageKbn()) != null) {
      joinGridDto.setManageKbn(ManageKbnEnum.valueof(joinMasterDto.getManageKbn()).getAbbreviation());
    }
    // セレクトコード
    joinGridDto.setPrmSetDtlNo1(joinMasterDto.getPrmSetDtlNo1() == 9999 ? "9999：指定無し"
        : selectCodeNameMap.get(String.valueOf(joinMasterDto.getPrmSetDtlNo1())));
    // 分類コード名称
    joinGridDto.setGoodsMGroup(joinMasterDto.getGoodsMGroup() == null ? null
        : classifyGuideNameMap.get(String.valueOf(joinMasterDto.getGoodsMGroup())));
    // BLコード名称
    joinGridDto.setTbsPartsCode(blCodeNameMap.get(String.valueOf(joinMasterDto.getTbsPartsCode())));
    // カーコード名称
    joinGridDto.setJoinSourceMakerCode(carmakerNameMap.get(String.valueOf(joinMasterDto.getJoinSourceMakerCode())));
    // 純正品番
    joinGridDto.setJoinSourPartsNoWithH(pureCodeNameMap.get(joinMasterDto.getJoinSourPartsNoWithH()));
    // 種別コード名称
    joinGridDto.setPrmSetDtlNo2(kindCodeMap.get(String.valueOf(joinMasterDto.getPrmSetDtlNo2())));
    // 表示順位
    joinGridDto.setJoinDispOrder(String.valueOf(joinMasterDto.getJoinDispOrder()));
    // 優良品番
    joinGridDto.setJoinDestPartsNo(primeCodeNameMap.get(joinMasterDto.getJoinDestPartsNo()) == null
        ? joinMasterDto.getJoinDestPartsNo() : primeCodeNameMap.get(joinMasterDto.getJoinDestPartsNo()));
    // QTY
    String s = joinMasterDto.getJoinQty() == null ? null : String.valueOf(joinMasterDto.getJoinQty());
    // 整数の時、小数を表示しない「例：2.0の時、２で表示する」
    if (s != null) {
      String[] numStr = s.split("\\.");
      if (Double.valueOf(numStr[1] == null ? "0" : numStr[1]) > 0) {
        s = String.format("%.2f", Double.valueOf(s));
      } else {
        s = numStr[0];
      }
    } else {
      s = "0";
    }
    joinGridDto.setJoinQty(s);
    // 規格/特記
    joinGridDto.setJoinSpecialNote(joinMasterDto.getJoinSpecialNote());
    // 規格/特記（一般）
    joinGridDto.setPrimePartsSpecialNoteC(joinMasterDto.getPrimePartsSpecialNoteC());
    // 削除依頼区分
    if (joinMasterDto.getDeleteFlg() != null && DeleteFlgEnum.valueof(joinMasterDto.getDeleteFlg()) != null) {
      joinGridDto.setDeleteFlg(DeleteFlgEnum.valueof(joinMasterDto.getDeleteFlg()).getAbbreviation());
    }
    // 削除理由
    joinGridDto.setDeleteReason(joinMasterDto.getDeleteReason());
    // 作成日時
    joinGridDto.setInsDtTime(joinMasterDto.getInsDtTime() == null ? EMPTY
        : BroadleafFormatUtils.dateToString(joinMasterDto.getInsDtTime(), BroadleafFormatUtils.DATE_F004));
    // 更新日時
    joinGridDto.setUpdDtTime(joinMasterDto.getUpdDtTime() == null ? EMPTY
        : BroadleafFormatUtils.dateToString(joinMasterDto.getUpdDtTime(), BroadleafFormatUtils.DATE_F004));
    // 適用日時
    joinGridDto.setStartTime(joinMasterDto.getStartTime() == null ? EMPTY
        : BroadleafFormatUtils.timestampToString(joinMasterDto.getStartTime(), BroadleafFormatUtils.DATE_F004));
    // チェック区分
    if (joinMasterDto.getCheckFlg() != null && CheckFlgEnum.valueof(joinMasterDto.getCheckFlg()) != null) {
      joinGridDto.setCheckFlg(CheckFlgEnum.valueof(joinMasterDto.getCheckFlg()).getAbbreviation());
    }
    // BL登録区分
    if (joinMasterDto.getBlEntryFlg() != null && BlEntryFlgEnum.valueof(joinMasterDto.getBlEntryFlg()) != null) {
      joinGridDto.setBlEntryFlg(BlEntryFlgEnum.valueof(joinMasterDto.getBlEntryFlg()).getAbbreviation());
    }
    // エラー区分
    if (joinMasterDto.getErrorFlg() != null && ErrorFlgEnum.valueof(joinMasterDto.getErrorFlg()) != null) {
      joinGridDto.setErrorFlg(ErrorFlgEnum.valueof(joinMasterDto.getErrorFlg()).getAbbreviation());
    }
    // エラー内容
    joinGridDto.setErrorDetail(joinMasterDto.getErrorDetail());
    joinGridDto.setImportKbn(Integer.valueOf(joinMasterDto.getImportKbn()));
    joinGridDto.setHiddenArea(JudgeEnum.UnChange.getValue());
    joinGridDto.setCompareFlag(joinMasterDto.getCompareFlag());
    return joinGridDto;
  }

  /**
   * 登録 entity
   * 
   * @param joinGridDto JoinGridDto
   * @param handleDiv handleDiv
   * @return JoinMasterDto
   */
  private JoinMasterDto convertJoinMasterMakerDto(JoinGridDto joinGridDto, int handleDiv) {
    JoinMasterDto joinMasterDto = new JoinMasterDto();
    // 優良設定詳細コード１(セレクトコード)
    joinMasterDto.setPrmSetDtlNo1(
        joinGridDto.getPrmSetDtlNo1() == null ? 9999 : Integer.parseInt(joinGridDto.getPrmSetDtlNo1().split("：")[0]));
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = loginPrincipal.getMakerCode();
    // 部品メーカーコード
    joinMasterDto.setPartsMakerCd(makerCode);
    // 商品中分類コード
    joinMasterDto.setGoodsMGroup(joinGridDto.getGoodsMGroup() == null || joinGridDto.getGoodsMGroup().isEmpty() ? null
        : Integer.parseInt(joinGridDto.getGoodsMGroup().split("：")[0]));
    // BLコード
    joinMasterDto.setTbsPartsCode(joinGridDto.getTbsPartsCode() == null || joinGridDto.getTbsPartsCode().isEmpty() ? 0
        : Integer.parseInt(joinGridDto.getTbsPartsCode().split("：")[0]));
    // 結合元メーカーコード(カーコード)
    joinMasterDto.setJoinSourceMakerCode(
        joinGridDto.getJoinSourceMakerCode() == null || joinGridDto.getJoinSourceMakerCode().isEmpty() ? 0
            : Integer.parseInt(joinGridDto.getJoinSourceMakerCode().split("：")[0]));
    // 優良設定詳細コード２(種別コード)
    joinMasterDto.setPrmSetDtlNo2(joinGridDto.getPrmSetDtlNo2() == null || joinGridDto.getPrmSetDtlNo2().isEmpty() ? 0
        : Integer.parseInt(joinGridDto.getPrmSetDtlNo2().split("：")[0]));
    // 結合元品番(－付き品番)純正品番
    joinMasterDto.setJoinSourPartsNoWithH(
        joinGridDto.getJoinSourPartsNoWithH() == null || joinGridDto.getJoinSourPartsNoWithH().isEmpty() ? EMPTY
            : joinGridDto.getJoinSourPartsNoWithH().split("：")[0]);
    // 結合表示順位
    joinMasterDto.setJoinDispOrder(joinGridDto.getJoinDispOrder() == null || joinGridDto.getJoinDispOrder().isEmpty()
        ? 0 : Integer.parseInt(joinGridDto.getJoinDispOrder()));
    // 結合先品番(－付き品番)優良品番
    joinMasterDto
        .setJoinDestPartsNo(joinGridDto.getJoinDestPartsNo() == null || joinGridDto.getJoinDestPartsNo().isEmpty()
            ? EMPTY : joinGridDto.getJoinDestPartsNo().split("：")[0]);
    // 結合QTY
    joinMasterDto.setJoinQty(joinGridDto.getJoinQty() == null || joinGridDto.getJoinQty().isEmpty() ? null
        : Double.valueOf(joinGridDto.getJoinQty()));
    // 結合規格・特記事項
    joinMasterDto
        .setJoinSpecialNote(joinGridDto.getJoinSpecialNote() == null ? EMPTY : joinGridDto.getJoinSpecialNote());
    // 優良部品規格・特記事項(C向け)
    joinMasterDto.setPrimePartsSpecialNoteC(
        joinGridDto.getPrimePartsSpecialNoteC() == null ? EMPTY : joinGridDto.getPrimePartsSpecialNoteC());
    // 適用日時
    joinMasterDto.setStartTime(new Timestamp(
        BroadleafFormatUtils.stringToDate(joinGridDto.getStartTime(), BroadleafFormatUtils.DATE_F004).getTime()));
    // 削除時申請理由
    joinMasterDto.setDeleteReason(joinGridDto.getDeleteReason() == null ? EMPTY : joinGridDto.getDeleteReason());
    // チェック区分
    joinMasterDto.setCheckFlg((short) CheckFlgEnum.Checked.getValue());
    // データステータス
    joinMasterDto.setErrorFlg((short) ErrorFlgEnum.getCode(joinGridDto.getErrorFlg()));
    // BL登録区分
    if (handleDiv == JudgeEnum.Add.getValue()) {
      joinMasterDto.setBlEntryFlg((short) BlEntryFlgEnum.Unregistered.getValue());
    } else {
      joinMasterDto.setBlEntryFlg((short) BlEntryFlgEnum.getCode(joinGridDto.getBlEntryFlg()));
    }
    // インポート区分
    if (handleDiv == JudgeEnum.Add.getValue()) {
      joinMasterDto.setImportKbn((short) ImportKbnEnum.ManualInput.getValue());
    } else {
      joinMasterDto.setImportKbn((short) joinGridDto.getImportKbn());
    }
    // 処理区分
    joinMasterDto.setManageKbn((short) ManageKbnEnum.getCode(joinGridDto.getManageKbn()));
    // エラー内容
    joinMasterDto.setErrorDetail(joinGridDto.getErrorDetail() == null ? EMPTY : joinGridDto.getErrorDetail());
    // 削除依頼区分
    joinMasterDto.setDeleteFlg((short) DeleteFlgEnum.getCodeByAbbreviation(joinGridDto.getDeleteFlg()));
    // 申請状態
    if (handleDiv == JudgeEnum.Add.getValue() || handleDiv == JudgeEnum.Modify.getValue()) {
      joinMasterDto.setApplyCondition((short) ApplyConditionEnum.getCode(joinGridDto.getApplyCondition()));
    } else if (handleDiv == JudgeEnum.Update.getValue()) {
      if (joinGridDto.getApplyCondition().equals(ApplyConditionEnum.NoApply.getAbbreviation())) {
        joinMasterDto.setApplyCondition((short) ApplyConditionEnum.NoApply.getValue());
      } else if (joinGridDto.getApplyCondition().equals(ApplyConditionEnum.Approval.getAbbreviation())
          || joinGridDto.getApplyCondition().equals(ApplyConditionEnum.Applyagain.getAbbreviation())) {
        joinMasterDto.setApplyCondition((short) ApplyConditionEnum.Applyagain.getValue());
      }
    }
    return joinMasterDto;
  }

  /**
   * gridのデータを取得 entity
   * 
   * @param form JoinListForm
   * @return joinGridDtoList
   */
  private List<JoinGridDto> getJoinGridList(JoinListForm form) {
    Integer mode = form.getMode();
    Integer importKbn = form.getImportKbn();
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int makerCode = loginPrincipal.getMakerCode();
    JoinMasterInfoDto joinMasterInfoDto = new JoinMasterInfoDto();
    List<JoinGridDto> gridDataList = new ArrayList<>();
    Long joinRows = (long) joinMasterFacade.searchJoinListRows(makerCode);
    int pageNo = form.getPageNo() == null || form.getPageNo().isEmpty() ? 1 : Integer.parseInt(form.getPageNo());
    Long skipRows = (long) joinRows * (pageNo - 1);
    JoinMasterSearchDto joinMasterSearchDto = setJoinMasterSearchDto(form);
    if (mode.intValue() == ModeEnum.Input.getValue() || mode.intValue() == ModeEnum.Reference.getValue()) {
      int searchMode = 3;
      if (mode.intValue() == ModeEnum.Reference.getValue() && importKbn == 0) {
        searchMode = 1;
      } else if (mode.intValue() == ModeEnum.Reference.getValue() && importKbn == 1) {
        searchMode = 2;
      }
      // 検索結果取得
      joinMasterInfoDto = joinMasterFacade.searchJoinMasterInfoList(joinMasterSearchDto, form.getOrder(), skipRows,
          joinRows, true, searchMode);
    } else if (mode.intValue() == ModeEnum.Error.getValue()) {
      // 検索結果取得
      joinMasterInfoDto = joinMasterWorkFacade.searchJoinMasterWorkInfoList(joinMasterSearchDto, form.getOrder(),
          skipRows, joinRows, true, importKbn);
      joinMasterSearchDto.setImportKbn(importKbn);
    } else if (mode.intValue() == ModeEnum.Select.getValue()) {
      // 検索結果取得
      joinMasterInfoDto = joinMasterFacade.searchJoinMasterInfoList(joinMasterSearchDto, form.getOrder(), skipRows,
          joinRows, true, 0);
      joinMasterInfoDto = joinMasterFacade.searchJoinMasterInfoList(joinMasterInfoDto);
    }
    Map<String, String> blCodeNameMap = joinMasterFacade.getBlCodeNameMap(makerCode);
    Map<String, String> selectCodeNameMap = joinMasterFacade.getSelectCodeNameMap(makerCode);
    Map<String, String> kindCodeMap = joinMasterFacade.getKindCodeNameMap(makerCode);
    Map<String, String> classifyGuideNameMap = joinMasterFacade.getClassifyCodeGuideMap(makerCode);
    Map<String, String> carmakerNameMap = joinMasterFacade.getCarmakerNameMap(makerCode);
    Map<String, String> primeCodeNameMap = joinMasterFacade.getPrimeCodeMap(makerCode);
    Map<String, String> pureCodeNameMap = joinMasterFacade.getPureCodeMap(makerCode);
    // 一覧へ設定
    for (JoinMasterDto joinMasterDto : joinMasterInfoDto.getJoinMasterDto()) {
      JoinGridDto joinGridDto = convertJoinGridDto(joinMasterDto, blCodeNameMap, selectCodeNameMap, kindCodeMap,
          classifyGuideNameMap, carmakerNameMap, primeCodeNameMap, pureCodeNameMap);
      if (mode.intValue() == ModeEnum.Error.getValue()) {
        joinGridDto.setCheck(true);
      } else if (mode.intValue() == ModeEnum.Select.getValue()) {
        joinGridDto.setCheck(joinMasterFacade.getCheckDiv(joinMasterDto));
      }
      gridDataList.add(joinGridDto);
    }
    return gridDataList;
  }
  
  /**
   * 全データ表示
   */
  private static final String ALL = "9";

  /**
   * EMPTY
   */
  private static final String EMPTY = "";

  /** 結合詳細画面URL */
  private static final String JOIN_DETAIL_URL = "/joindetail/joinDetail";

  /** 結合詳細キー */
  private static final String JOIN_DETAIL_KEY = "joinDetailKey";

  /** 結合一覧画面 */
  private static final String JOINLIST_URL = "joinlist/joinList";

  /** メッセージキー */
  private static final String CONFIRMMESSAGE = "confirmMessage";

  /** 結合一覧（ワーク）Facade */
  private JoinMasterWorkFacade joinMasterWorkFacade;

  /**
   * <pre>
   * 結合一覧（ワーク）Facadeを設定する。
   * </pre>
   *
   * @param joinMasterWorkFacade Facade
   */
  @Resource
  public void setJoinMasterWorkFacade(JoinMasterWorkFacade joinMasterWorkFacade) {
    this.joinMasterWorkFacade = joinMasterWorkFacade;
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
