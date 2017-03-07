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
package jp.co.broadleaf.breg.web.fileselect.controller;

import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.common.enums.ImportTypeEnum;
import jp.co.broadleaf.breg.fileselect.facade.FileSelectFacade;
import jp.co.broadleaf.breg.web.fileselect.form.FileSelectForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.web.BroadleafSessionKeys;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.WebResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * ファイル選択 Controllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/fileselect")
public class FileSelectController extends BaseController {
  /** インポート画面 */
  private static final String FILE_SELECT_URL = "fileselect/fileselect";
  /** 商品 フォーマットのルール */
  private static final String[] GOODS_FILE_FORMAT = new String[] {
      "セレクトコード",
      "メーカーコード",
      "分類コード",
      "BLコード",
      "優良品番",
      "品名（半角）",
      "品名（全角）",
      "優良定価",
      "オープン価格区分",
      "JAN",
      "層別",
      "規格",
      "特記事項",
      "規格/特記（一般）",
      "商品詳細(B向け)",
      "商品詳細(C向け)",
      "商品サイズ(長さ）",
      "商品サイズ(幅）",
      "商品サイズ(高さ）",
      "梱包サイズ(長さ）",
      "梱包サイズ(幅）",
      "梱包サイズ(高さ）",
      "サイズ単位",
      "商品重量",
      "重量単位",
      "URL1",
      "URL2",
      "URL3",
      "画像数",
      "適用日付",
      "削除依頼区分",
      "削除理由" };
  /** セット フォーマットのルール */
  private static final String[] SET_FILE_FORMAT = new String[] {
      "セレクトコード",
      "メーカーコード",
      "分類コード",
      "BLコード",
      "優良親品番",
      "セット表示順位",
      "優良子品番",
      "品名",
      "セット名称",
      "セットQTY",
      "セット規格・特記事項",
      "優良部品規格・特記事項(C向け)",
      "適用日付",
      "削除依頼区分",
      "削除理由" };
  /** 結合 フォーマットのルール */
  private static final String[] JOIN_FILE_FORMAT = new String[] {
      "セレクトコード",
      "メーカーコード",
      "分類コード",
      "BLコード",
      "結合元メーカーコード",
      "優良設定詳細コード２",
      "純正品番",
      "表示順位",
      "優良品番",
      "QTY",
      "規格・特記事項",
      "優良部品規格・特記事項(C向け)",
      "適用日付",
      "削除理由",
      "削除依頼区分" };
  /** インポート初期方法 */
  private static final String IMPORT_TYPE = "importType";
  /** 商品 */
  private static final String GOODS = "goods";
  /** セット */
  private static final String SET = "set";
  /** 結合 */
  private static final String JOIN = "join";
  /** 商品削除 */
  private static final String GOODS_DEL = "goodsDel";
  /** セット削除 */
  private static final String SET_DEL = "setDel";
  /** 結合削除 */
  private static final String JOIN_DEL = "joinDel";
  /** 商品のエラーをチェックするメッセージ。 */
  private static final String GOODS_FILE_CHECK_MSG = "goodsFileCheckMsg";
  /** セットのエラーをチェックするメッセージ。 */
  private static final String SET_FILE_CHECK_MSG = "setFileCheckMsg";
  /** 結合のエラーをチェックするメッセージ。 */
  private static final String JOIN_FILE_CHECK_MSG = "joinFileCheckMsg";
  /** 商品情報のインポート方法 */
  private static final String GOODS_TYPE = "goodsType";
  /** セット情報のインポート方法 */
  private static final String SET_TYPE = "setType";
  /** 結合情報のインポート方法 */
  private static final String JOIN_TYPE = "joinType";
  /** 既存BL承認済みの商品 */
  private static final String GOODS_CONSENT = "goodsConsent";
  /** 既存BL承認済みのセット */
  private static final String SET_CONSENT = "setConsent";
  /** 既存BL承認済みの結合 */
  private static final String JOIN_CONSENT = "joinConsent";
  /** 起動の場合:「インポート」や「インポート（一括申請）」 */
  private static final String IMPORT_TYPE_MENU = "importTypeMenu";
  /** 「全体」方式インポートする前、TSVファイル内BL承認済商品、セット品、結合品のチェックメセッジ*/
  private static final String ASK_MSG = "askMsg";
  /** 文字列：空白文字. */
  public static final String EMPTY = "";
  /** インポート方法 */
  private int importTypeMenu;

  /** fileSelectFacade */
  private FileSelectFacade fileSelectFacade;

  /**
   * <pre>
   * 【fileSelectFacade】を設定する。
   * </pre>
   *
   * @param fileSelectFacade 【fileSelectFacade】
   */
  @Resource
  public void setFileSelectFacade(FileSelectFacade fileSelectFacade) {
    this.fileSelectFacade = fileSelectFacade;
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
  @RequestMapping(path = "/fileselect", method = RequestMethod.GET)
  @ResponseBody
  public ModelAndView fielSelectInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("ファイル選択の初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(FILE_SELECT_URL);
    HttpSession session = request.getSession();
    try {
      /** ログイン情報を取得。 */
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      int loginUserCd = loginPrincipal.getMakerCode();
      /** インポート初期方法の取得 */
      int[] importType = fileSelectFacade.getImportType(loginUserCd);
      session.setAttribute(BroadleafSessionKeys.GOODS_FILE_INFO, null);
      session.setAttribute(BroadleafSessionKeys.SET_FILE_INFO, null);
      session.setAttribute(BroadleafSessionKeys.JOIN_FILE_INFO, null);
      result.addObject(IMPORT_TYPE, importType);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("ファイル選択の初期表示処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * アップロード処理を実行する。
   * </pre>
   *
   * @param req HttpServletRequest
   * @param response HttpServletResponse
   * @return 処理結果
   */
  @RequestMapping(path = "/fileupload", method = RequestMethod.POST)
  @ResponseBody
  public WebResult fileUpload(HttpServletRequest req, HttpServletResponse response) {
    logger.debug("ファイル選択のアップロード処理を開始します。");
    WebResult result = new WebResult();
    HttpSession session = req.getSession();
    try {
      /** HTTPの情報をゲットする。 */
      StringBuilder sb = new StringBuilder();
      try (BufferedReader reader = req.getReader()) {
        /** 毎1024ﾊﾞｲﾄを読む。 */
        char[] buff = new char[1024];
        int len;
        while ((len = reader.read(buff)) != -1) {
          sb.append(buff, 0, len);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      /** HTTPの情報を切って、保存する。 */
      String[] sbL = sb.toString().split("\r");
      /** タイプ変数をゲットして、保存する。 */
      String fileType = sbL[sbL.length - 3].trim();
      if (fileType.equals(GOODS)) { /** アップロードのファイルが商品のファイル。 */
        /** 商品のファイルの内容をゲットして、保存する。 */
        session.setAttribute(BroadleafSessionKeys.GOODS_FILE_INFO, fileSelectFacade.getFileValue(sbL));
      } else if (fileType.equals(SET)) { /** アップロードのファイルがセットのファイル。 */
        /** セットのファイルの内容をゲットして、保存する。 */
        session.setAttribute(BroadleafSessionKeys.SET_FILE_INFO, fileSelectFacade.getFileValue(sbL));
      } else if (fileType.equals(JOIN)) { /** アップロードのファイルが結合のファイル。 */
        /** 結合のファイルの内容をゲットして、保存する。 */
        session.setAttribute(BroadleafSessionKeys.JOIN_FILE_INFO, fileSelectFacade.getFileValue(sbL));
      } else if (fileType.equals(GOODS_DEL)) { /** 商品ファイルをアップロードする操作を取り消する。 */
        session.setAttribute(BroadleafSessionKeys.GOODS_FILE_INFO, null);
      } else if (fileType.equals(SET_DEL)) { /** セットファイルをアップロードする操作を取り消する。 */
        session.setAttribute(BroadleafSessionKeys.SET_FILE_INFO, null);
      } else if (fileType.equals(JOIN_DEL)) { /** 結合ファイルをアップロードする操作を取り消する。 */
        session.setAttribute(BroadleafSessionKeys.JOIN_FILE_INFO, null);
      }
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("ファイル選択のアップロード処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * インポート処理を実行する。
   * </pre>
   * 
   * @param form FileSelectForm
   * @param req HttpServletRequest
   * @return 処理結果
   */
  @SuppressWarnings("unchecked")
  @RequestMapping(path = "/fileinput", method = RequestMethod.POST)
  @ResponseBody
  public WebResult fileInput(@RequestBody FileSelectForm form, HttpServletRequest req) {
    logger.debug("ファイル選択のインポート処理を開始します。");
    /** ログイン情報を取得。 */
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    int loginUserCd = loginPrincipal.getMakerCode();
    WebResult result = new WebResult();
    HttpSession session = req.getSession();
    importTypeMenu = Integer.parseInt(form.getImportTypeMenu());
    /** たまの商品の情報。 */
    List<String[]> goodsListTemp = (List<String[]>) session.getAttribute(BroadleafSessionKeys.GOODS_FILE_INFO);
    /** たまのセットの情報。 */
    List<String[]> setListTemp = (List<String[]>) session.getAttribute(BroadleafSessionKeys.SET_FILE_INFO);
    /** たまの結合の情報。 */
    List<String[]> joinListTemp = (List<String[]>) session.getAttribute(BroadleafSessionKeys.JOIN_FILE_INFO);
    /** 商品をチェックするメッセージ。 */
    List<String> goodsFileCheckMsg = null;
    /** セットをチェックするメッセージ。 */
    List<String> setFileCheckMsg = null;
    /** 結合をチェックするメッセージ。 */
    List<String> joinFileCheckMsg = null;
    /** 既存BL承認済みの商品*/
    int[] goodsConsentNum = new int[3];
    /** 既存BL承認済みのセット*/
    int[] setConsentNum = new int[3];
    /** 既存BL承認済みの結合*/
    int[] joinConsentNum = new int[3];
    /** インポート方法：差分、全件 起動の場合:「インポート」や「インポート（一括申請）」 セッションに保存する */
    this.setSessionImportType(form, req);
    /** データベースに無効データを削除する。 */
    fileSelectFacade.deleteInvalidData(loginUserCd);
    try {
      if (goodsListTemp !=null && !goodsListTemp.isEmpty()) {
        /** 商品のフォーマットをチェックする。 */
        goodsFileCheckMsg = fileSelectFacade.fileFirstVai(goodsListTemp.get(0), GOODS_FILE_FORMAT);
        if (goodsFileCheckMsg == null) {
          goodsListTemp.remove(0);
          goodsFileCheckMsg = new ArrayList<String>();
          /** データベースに商品の情報を保存する。 */
          goodsFileCheckMsg.addAll(fileSelectFacade.fileConversionCheck(goodsListTemp, GOODS, importTypeMenu, loginUserCd));
          if(goodsFileCheckMsg.isEmpty() && form.getGoodsType().equals(EMPTY+ ImportTypeEnum.All.getValue())){
            goodsConsentNum = fileSelectFacade.getConsentNum(GOODS, loginUserCd);
          }
        }
      }
      if (setListTemp != null && !setListTemp.isEmpty()) {
        /** セットのフォーマットをチェックする。 */
        setFileCheckMsg = fileSelectFacade.fileFirstVai(setListTemp.get(0), SET_FILE_FORMAT);
        if (setFileCheckMsg == null) {
          setListTemp.remove(0);
          setFileCheckMsg = new ArrayList<String>();
          /** データベースにセットの情報を保存する。 */
          setFileCheckMsg.addAll(fileSelectFacade.fileConversionCheck(setListTemp, SET, importTypeMenu, loginUserCd));
          if(setFileCheckMsg.isEmpty() && form.getSetType().equals(EMPTY+ ImportTypeEnum.All.getValue())){
            setConsentNum = fileSelectFacade.getConsentNum(SET, loginUserCd);
          }
        }
      }
      if (joinListTemp != null && !joinListTemp.isEmpty()) {
        /** 結合のフォーマットをチェックする。 */
        joinFileCheckMsg = fileSelectFacade.fileFirstVai(joinListTemp.get(0), JOIN_FILE_FORMAT);
        if (joinFileCheckMsg == null) {
          joinListTemp.remove(0);
          joinFileCheckMsg = new ArrayList<String>();
          /** データベースに結合の情報を保存する。 */
          joinFileCheckMsg.addAll(fileSelectFacade.fileConversionCheck(joinListTemp, JOIN, importTypeMenu, loginUserCd));
          if(joinFileCheckMsg.isEmpty() && form.getJoinType().equals(EMPTY+ ImportTypeEnum.All.getValue())){
            joinConsentNum = fileSelectFacade.getConsentNum(JOIN, loginUserCd);
          }
        }
      }
      if(goodsConsentNum[2] == 0 && joinConsentNum[2] == 0 && setConsentNum[2] == 0){
        fileSelectFacade.fileToDb();
        result.put(GOODS_CONSENT,  null);
        result.put(SET_CONSENT, null);
        result.put(JOIN_CONSENT, null);
      }else{
        result.put(GOODS_CONSENT,  goodsConsentNum[2] == 0 ? null : goodsConsentNum);
        result.put(SET_CONSENT, setConsentNum[2] == 0 ? null : setConsentNum);
        result.put(JOIN_CONSENT, joinConsentNum[2] == 0 ? null : joinConsentNum);
      }
      /** ｊｓｐにメッセージを出す。 */
      result.put(ASK_MSG, fileSelectFacade.getAskMsg());
      result.put(GOODS_FILE_CHECK_MSG, goodsFileCheckMsg);
      result.put(SET_FILE_CHECK_MSG, setFileCheckMsg);
      result.put(JOIN_FILE_CHECK_MSG, joinFileCheckMsg);
      /** メッセージ、nullに設定する。 */
      goodsFileCheckMsg = null;
      setFileCheckMsg = null;
      joinFileCheckMsg = null;
      goodsConsentNum = null;
      joinConsentNum = null;
      setConsentNum = null;
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("ファイル選択のインポート処理を終了します。");
    return result;
  }
  
  /**
   * <pre>
   * データベースに続行する。
   * </pre>
   * 
   * @param req HttpServletRequest
   * @param response HttpServletResponse
   * @return 処理結果
   */
  @RequestMapping(path = "/continue", method = RequestMethod.POST)
  @ResponseBody
  public WebResult fileInsertContinue(HttpServletRequest req, HttpServletResponse response) {
    logger.debug("ファイル選択の保存処理を開始します。");
    WebResult result = new WebResult();
    fileSelectFacade.fileToDb();
    logger.debug("ファイル選択の保存処理を終了します。");
    return result;
  }
  /**
   * <pre>
   * インポート方法：差分、全件 起動の場合:「インポート」や「インポート（一括申請）」  セッションに保存する。
   * </pre>
   * 
   * @param form FileSelectForm
   * @param req HttpServletRequest
   */
  private void setSessionImportType(FileSelectForm form, HttpServletRequest req) {
    importTypeMenu = Integer.parseInt(form.getImportTypeMenu());
    HttpSession session = req.getSession();
    /** 商品情報のインポート方法：全件、 差分。*/
    session.setAttribute(GOODS_TYPE, ImportTypeEnum.valueof(Integer.parseInt(form.getGoodsType())));
    /** セット情報のインポート方法：全件、 差分。*/
    session.setAttribute(SET_TYPE, ImportTypeEnum.valueof(Integer.parseInt(form.getSetType())));
    /** 結合情報のインポート方法：全件、 差分。*/
    session.setAttribute(JOIN_TYPE, ImportTypeEnum.valueof(Integer.parseInt(form.getJoinType())));
    /** 起動の場合:「インポート」や「インポート（一括申請）」*/
    session.setAttribute(IMPORT_TYPE_MENU, ImportKbnEnum.valueof(importTypeMenu));
  }
  
}
