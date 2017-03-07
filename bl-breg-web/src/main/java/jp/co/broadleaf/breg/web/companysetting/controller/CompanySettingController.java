//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 湯凱
// 作 成 日       2017/02/06   修正内容 : 会社情報：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.companysetting.controller;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.dto.BlCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.KindCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.PartsMasterDto;
import jp.co.broadleaf.breg.common.dto.SelectCodeMasterDto;
import jp.co.broadleaf.breg.companysetting.dto.CompanySettingDto;
import jp.co.broadleaf.breg.companysetting.enums.CompanySettingEnums;
import jp.co.broadleaf.breg.companysetting.facade.CompanyInfoMasterCommonFacade;
import jp.co.broadleaf.breg.message.service.Facade.MessageFacade;
import jp.co.broadleaf.breg.web.companysetting.form.CompanyInfoForm;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * 会社情報Controllerクラス。
 * </pre>
 */
@Controller
@RequestMapping("/employee")
public class CompanySettingController extends BaseController {

  /**
   * <pre>
   * 初期化表示。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @RequestMapping(path = "/companysetting", method = RequestMethod.GET)
  public ModelAndView companySettingInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("会社情報の初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(COMPANYSETTING_URL);

    try {
      // ログイン情報を取得
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      // メーカーIdを取得
      int makerCd = loginPrincipal.getMakerCode();
      // 初期会社情報を取得
      CompanySettingDto infoDto = employFacade.getCompanyInfo(makerCd);
      // Sessionで初期化のデータを保存する
      request.getSession().removeAttribute(BroadleafSessionKeys.COMPANYSETTING_INITIALINFO);
      request.getSession().setAttribute(BroadleafSessionKeys.COMPANYSETTING_INITIALINFO, infoDto);
      // BLコードマスタ情報を取得
      List<BlCodeMasterDto> blCodeInfo = employFacade.getBlCodeInfo(makerCd);
      // BLコードマスタ画面表示用のデータを転換
      List<String> blCodeDis = getBlCodeDis(blCodeInfo);
      // 種別コードマスト情報を取得
      List<KindCodeMasterDto> kindCodeInfo = employFacade.getKindCodeInfo(makerCd);
      // 種別コードマスト画面表示用のデータを転換
      List<String> kindCodeDis = getKindCodeDis(kindCodeInfo);
      // 層別コード情報を取得
      List<PartsMasterDto> partsInfo = employFacade.getPartsMasterInfo(makerCd);
      // 層別コード画面表示用のデータを転換
      List<String> partsCodeDis = getPartsCodeDis(partsInfo);
      // セレクトコード情報を取得
      List<SelectCodeMasterDto> selectCodeInfo = employFacade.getSelectCodeInfo(Integer.valueOf(makerCd));
      // セレクトコード画面表示用のデータを転換
      List<String> selectCodeDis = getSelectCodeDis(selectCodeInfo);
      
      // 画面表示用のメッセージを取る
      Map<String, String> msgList = getMessageMap(loginPrincipal.getMessageMap());
      String confirmMsg = msgList.get(BregMessageCodes.Q00002);
      String infoMsg = msgList.get(BregMessageCodes.I00001);
      String questionMsg = msgList.get(BregMessageCodes.Q00001);
      String saveFailMsg = msgList.get(BregMessageCodes.E00013);
      request.getSession().setAttribute(MOVECONFIRMMSG, questionMsg);
      request.getSession().setAttribute(SAVEFAILMSG, saveFailMsg);
      
      result.addObject(SAVECONFIRMMSG, confirmMsg);
      result.addObject(SAVESUCCESSMSG, infoMsg);
      result.addObject(COMPANYSETTING_MAKERCODE, makerCd);
      result.addObject(COMPANYINFOKEY, infoDto);
      result.addObject(SELECTERCODE, selectCodeDis);
      result.addObject(BLCODE, blCodeDis);
      result.addObject(KINDCODE, kindCodeDis);
      result.addObject(PARTSCODE, partsCodeDis);
      setImportType(result, infoDto);

    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("会社情報の初期表示処理を終了します。");
    return result;
  }

  /**
   * 保存ボタンをクリックする処理
   * 
   * @param paramData CompanyInfoForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/save", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult companySettingSave(@RequestBody CompanyInfoForm paramData, HttpServletRequest request,
                                      HttpServletResponse response) {
    logger.debug("会社情報の保存処理を開始します。");
    WebResult result = new WebResult();
    try {
      // 画面入力項目のチェック
      paramData.validate();
      // ログイン情報を取得
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      // ログインID
      String loginId = loginPrincipal.getLoginId();
      // String loginId = "0";
      CompanySettingDto initialDto = (CompanySettingDto) request.getSession()
          .getAttribute(BroadleafSessionKeys.COMPANYSETTING_INITIALINFO);
      CompanySettingDto updateDto = setCompanyInfo(paramData, initialDto, loginId);
      // 画面編集した項目ありかどうかの判断
      if (hasEditColumn(updateDto, initialDto)) {
        int updateDig = employFacade.updateCompanyInfo(updateDto);
        if (updateDig == 1) {
          handleException(new Throwable(), result);
          return result;
        }
        result.put(UPDATEFLAG, true);
      } else {
        result.put(UPDATEFLAG, false);
      }
      result.put(MOVEKEY, TOPMENU_URL);
      // 画面表示用のメッセージを取る
      String msg = (String) request.getSession().getAttribute(SAVEFAILMSG);
      result.put(SAVEFAILMSG, msg);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("会社情報の保存処理を終了します。");
    return result;
  }

  /**
   * 戻るボタンをクリックする処理
   * 
   * @param paramData CompanyInfoForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return WebResult
   */
  @RequestMapping(path = "/back", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult companySettingBack(@RequestBody CompanyInfoForm paramData, HttpServletRequest request,
                                      HttpServletResponse response) {
    logger.debug("会社情報の戻る処理を開始します。");
    WebResult result = new WebResult();
    try {
      CompanySettingDto initialDto = (CompanySettingDto) request.getSession()
          .getAttribute(BroadleafSessionKeys.COMPANYSETTING_INITIALINFO);
      CompanySettingDto updateDto = setCompanyInfo(paramData, initialDto, null);
      // 画面編集した項目ありかどうかの判断
      if (hasEditColumn(updateDto, initialDto)) {
        result.put(PAGEUPDATEFLAG, true);
      } else {
        result.put(PAGEUPDATEFLAG, false);
      }
      result.put(MOVEKEY, TOPMENU_URL);
      result.put(COMPANYSETTINGKEY, COMPANYSETTING_URL);
      // 画面表示用のメッセージを取る
      String msg = (String) request.getSession().getAttribute(MOVECONFIRMMSG);
      result.put(CONFIRMMESSAGE, msg);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("会社情報の戻る処理を終了します。");
    return result;
  }

  /** エンプロイーFacade */
  private CompanyInfoMasterCommonFacade employFacade;

  /** メーカーコード */
  private static final String COMPANYSETTING_MAKERCODE = "companySetting_makerCode";

  /** 会社情報のキー */
  private static final String COMPANYINFOKEY = "companyInfo";

  /** セレクトコード */
  private static final String SELECTERCODE = "selecterCode";

  /** BLコード */
  private static final String BLCODE = "blCode";

  /** 種別コード */
  private static final String KINDCODE = "kindCode";

  /** 層別コード */
  private static final String PARTSCODE = "partsCode";

  /** 商品全件インポート方法 */
  private static final String GOODSIMPORTALL = "goodsImportAll";

  /** 商品差分インポート方法 */
  private static final String GOODSIMPORTDIF = "goodsImportDif";

  /** セット全件インポート方法 */
  private static final String SETIMPORTALL = "setImportAll";

  /** セット差分インポート方法 */
  private static final String SETIMPORTDIF = "setImportDif";

  /** 結合全件インポート方法 */
  private static final String JOINIMPORTALL = "joinImportAll";

  /** 結合差分インポート方法 */
  private static final String JOINIMPORTDIF = "joinImportDif";

  /** 画面更新かどうかのFlag */
  private static final String PAGEUPDATEFLAG = "pageUpdateFlag";

  /** DB更新かどうかのFlag */
  private static final String UPDATEFLAG = "updateFlag";

  /** トップページのキー */
  private static final String MOVEKEY = "topMenuKey";

  /** 確認メッセージキー */
  private static final String CONFIRMMESSAGE = "confirmMessage";

  /** 会社情報画面のURLKEY */
  private static final String COMPANYSETTINGKEY = "companySettingKey";
  
  /** 保存確認メッセージ*/
  private static final String SAVECONFIRMMSG = "saveConfirmMsg";
  
  /** 保存成功メッセージ*/
  private static final String SAVESUCCESSMSG = "saveSuccessMsg";
  
  /** 編集中の確認メッセージ*/
  private static final String MOVECONFIRMMSG = "moveConfirmMsg";
  
  /** 保存内容がないメッセージ*/
  private static final String SAVEFAILMSG = "saveFailMsg";

  /** 会社情報画面のURL */
  private static final String COMPANYSETTING_URL = "companysetting/companysetting";

  /** トップページのURL */
  private static final String TOPMENU_URL = "/topmenu/topMenu";

  /**
   * <pre>
   * エンプロイーFacadeをセットする.
   * </pre>
   * 
   * @param employeeFacade EmployeeFacade
   */
  @Resource
  public void setCompanyInfoMasterCommonFacade(CompanyInfoMasterCommonFacade employeeFacade) {
    this.employFacade = employeeFacade;
  }

  /** メッセージFacade*/
  private MessageFacade messageFacade;

  /**
   * <pre>
   * 共通メッセージ取得を設定する。
   * </pre>
   *
   * @param messageFacade 共通メッセージ取得
   */
  @Resource
  public void setMessageFacade(MessageFacade messageFacade) {
    this.messageFacade = messageFacade;
  }
  
  /**
   * メッセージ情報取得
   * 
   * @param messageMapAll すべてメッセージ情報
   * @return 画面表示用メッセージ情報
   */
  private Map<String, String> getMessageMap(HashMap<String, String> messageMapAll) {
    List<String> messageIdList = new ArrayList<String>();
    messageIdList.add(BregMessageCodes.Q00001);
    messageIdList.add(BregMessageCodes.Q00002);
    messageIdList.add(BregMessageCodes.I00001);
    messageIdList.add(BregMessageCodes.E00013);
    return messageFacade.getMessageContentsMap(messageIdList, messageMapAll);
  }
  
  /**
   * 画面の会社情報をDtoで持つ
   * 
   * @param paramData CompanyInfoForm
   * @param initialDto CompanySettingDto
   * @param loginId ログインId
   * @return CompanySettingDto
   */
  private CompanySettingDto setCompanyInfo(CompanyInfoForm paramData, CompanySettingDto initialDto, String loginId) {
    if (paramData == null || initialDto == null) {
      return null;
    }
    CompanySettingDto updateDto = new CompanySettingDto();
    // 作成アカウントID
    updateDto.setInsAccountId(initialDto.getInsAccountId());
    // 作成日時
    updateDto.setInsDtTime(initialDto.getInsDtTime());
    // 初期化時の更新日時
    updateDto.setUpdDtTime(initialDto.getUpdDtTime());
    // 作成ID
    if (loginId != null) {
      updateDto.setUpdAccountId(loginId);
    }
    // メーカーコード
    updateDto.setMakerCd(initialDto.getMakerCd());
    // メーカー名称
    updateDto.setMakerCdName(paramData.getMakerCdName());
    // メーカーカナ
    updateDto.setMakerCdNameShort(paramData.getMakerCdKana());
    // 会社名称
    updateDto.setCompanyName(paramData.getCompanyName());
    // 会社名称（カナ）
    updateDto.setCompanyNameKana(paramData.getCompanyKana());
    // 郵便番号
    updateDto.setMailNo(paramData.getPostNo());
    // 住所
    updateDto.setAddress(paramData.getAddress());
    // TEL
    updateDto.setTel(paramData.getTel());
    // FAX
    updateDto.setFax(paramData.getFax());
    // 備考
    updateDto.setNotes(paramData.getNotes());
    // 商品ページ内行数設定
    if (isNumber(paramData.getGoodsRows())) {
      updateDto.setGoodsRows(Integer.parseInt(paramData.getGoodsRows()));
    }
    // セットページ内行数設定
    if (isNumber(paramData.getSetRows())) {
      updateDto.setSetRows(Integer.parseInt(paramData.getSetRows()));
    }
    // 結合ページ内行数設定
    if (isNumber(paramData.getJoinRows())) {
      updateDto.setJoinRows(Integer.parseInt(paramData.getJoinRows()));
    }
    // 申請履歴ページ内行数設定
    if (isNumber(paramData.getApplyResumeRows())) {
      updateDto.setApplyRecordRows(Integer.parseInt(paramData.getApplyResumeRows()));
    }
    // 申請詳細ページ内行数設定
    if (isNumber(paramData.getApplyDetailRows())) {
      updateDto.setApplyDetailRows(Integer.parseInt(paramData.getApplyDetailRows()));
    }
    // 商品インポート方法
    if (paramData.getGoodsImportType() != null) {
      if (paramData.getGoodsImportType().contains(CompanySettingEnums.ImportType.ALL.getFiledClassName())) {
        updateDto.setGoodsImportType(Integer.valueOf(0));
      } else {
        updateDto.setGoodsImportType(Integer.valueOf(1));
      }
    }
    // セットインポート方法
    if (paramData.getSetImportType() != null) {
      if (paramData.getSetImportType().contains(CompanySettingEnums.ImportType.ALL.getFiledClassName())) {
        updateDto.setSetImportType(Integer.valueOf(0));
      } else {
        updateDto.setSetImportType(Integer.valueOf(1));
      }
    }
    // 結合インポート方法
    if (paramData.getJoinImportType() != null) {
      if (paramData.getJoinImportType().contains(CompanySettingEnums.ImportType.ALL.getFiledClassName())) {
        updateDto.setJoinImportType(Integer.valueOf(0));
      } else {
        updateDto.setJoinImportType(Integer.valueOf(1));
      }
    }
    return updateDto;
  }

  /**
   * セレクトコード画面表示用のデータを取得
   * 
   * @param selectCodeInfo セレクトコード情報
   * @return セレクトコード画面表示用のデータ
   */
  private List<String> getSelectCodeDis(List<SelectCodeMasterDto> selectCodeInfo) {
    List<String> selectCodeDisList = null;
    if (selectCodeInfo != null && !selectCodeInfo.isEmpty()) {
      selectCodeDisList = new ArrayList<>();
      for (SelectCodeMasterDto dto : selectCodeInfo) {
        String selectCodeDis = String.valueOf(dto.getPrmSetDtlNo1()).concat("：").concat(dto.getPrmSetDtlName());
        selectCodeDisList.add(selectCodeDis);
      }
    }
    return selectCodeDisList;
  }

  /**
   * BLコードマスタ画面表示用のデータを転換
   * 
   * @param blCodeInfo BLコードマスタ情報
   * @return BLコードマスタ画面表示用のデータ
   */
  private List<String> getBlCodeDis(List<BlCodeMasterDto> blCodeInfo) {
    List<String> blCodeDisList = null;
    if (blCodeInfo != null && !blCodeInfo.isEmpty()) {
      blCodeDisList = new ArrayList<>();
      for (BlCodeMasterDto dto : blCodeInfo) {
        String blCodeDis = String.valueOf(dto.getBlCode()).concat("：").concat(dto.getBlFullName());
        blCodeDisList.add(blCodeDis);
      }
    }
    return blCodeDisList;
  }

  /**
   * 種別コードマスト画面表示用のデータを転換
   * 
   * @param kindCodeInfo 種別コードマスト情報
   * @return 種別コードマスト画面表示用のデータ
   */
  private List<String> getKindCodeDis(List<KindCodeMasterDto> kindCodeInfo) {
    List<String> kindCodeDisList = null;
    if (kindCodeInfo != null && !kindCodeInfo.isEmpty()) {
      kindCodeDisList = new ArrayList<>();
      for (KindCodeMasterDto kindCode : kindCodeInfo) {
        String kindCodeDis = String.valueOf(kindCode.getKindCd()).concat("：").concat(kindCode.getKindName());
        kindCodeDisList.add(kindCodeDis);
      }
    }
    return kindCodeDisList;
  }

  /**
   * 層別コード画面表示用のデータを転換
   * 
   * @param partsInfo 層別コード情報
   * @return 層別コード画面表示用のデータ
   */
  private List<String> getPartsCodeDis(List<PartsMasterDto> partsInfo) {
    List<String> partsCodeDisList = null;
    if (partsInfo != null && !partsInfo.isEmpty()) {
      partsCodeDisList = new ArrayList<>();
      for (PartsMasterDto parts : partsInfo) {
        String partsCodeDis = String.valueOf(parts.getPartsLayer()).concat("：").concat(parts.getPartsLayerName());
        partsCodeDisList.add(partsCodeDis);
      }
    }
    return partsCodeDisList;
  }

  /**
   * インポート方法をViewにセットする
   * 
   * @param result ModelAndView
   * @param infoDto CompanySettingDto
   */
  private void setImportType(ModelAndView result, CompanySettingDto infoDto) {
    int goodsImportType = infoDto.getGoodsImportType();
    String goodsImportAll = null;
    String goodsImportDif = null;
    if (goodsImportType == CompanySettingEnums.ImportType.ALL.getCode()) {
      goodsImportAll = String.valueOf(goodsImportType);
    } else {
      goodsImportDif = String.valueOf(goodsImportType);
    }

    int setImportType = infoDto.getSetImportType();
    String setImportAll = null;
    String setImportDif = null;
    if (setImportType == CompanySettingEnums.ImportType.ALL.getCode()) {
      setImportAll = String.valueOf(setImportType);
    } else {
      setImportDif = String.valueOf(setImportType);
    }

    int joinImportType = infoDto.getJoinImportType();
    String joinImportAll = null;
    String joinImportDif = null;
    if (joinImportType == CompanySettingEnums.ImportType.ALL.getCode()) {
      joinImportAll = String.valueOf(joinImportType);
    } else {
      joinImportDif = String.valueOf(joinImportType);
    }
    result.addObject(GOODSIMPORTALL, goodsImportAll);
    result.addObject(GOODSIMPORTDIF, goodsImportDif);
    result.addObject(SETIMPORTALL, setImportAll);
    result.addObject(SETIMPORTDIF, setImportDif);
    result.addObject(JOINIMPORTALL, joinImportAll);
    result.addObject(JOINIMPORTDIF, joinImportDif);
  }

  /**
   * 画面で編集した項目ありかどうかの判断
   * 
   * @param updateDto CompanySettingDto
   * @param initialDto CompanySettingDto
   * @return true:編集した項目あり false:編集した項目ない
   */
  private boolean hasEditColumn(CompanySettingDto updateDto, CompanySettingDto initialDto) {
    if (initialDto == null || updateDto == null) {
      return false;
    }
    return !updateDto.equals(initialDto);
  }

  /**
   * 文字列を数字に転換かどうかの判断
   * 
   * @param str 文字列
   * @return true:転換できる false:転換できない
   */
  private boolean isNumber(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
