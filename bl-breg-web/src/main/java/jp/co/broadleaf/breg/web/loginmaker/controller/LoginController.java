//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:曹　緩緩
// 作成日:2017/02/08    修正内容:ログイン：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.loginmaker.controller;

import jp.co.broadleaf.breg.loginmaker.facade.LoginFacade;
import jp.co.broadleaf.breg.loginmaker.facade.UserMasterFacade;
import jp.co.broadleaf.breg.loginmaker.facade.dto.LoginCertifConfirmDto;
import jp.co.broadleaf.breg.loginmaker.facade.dto.LoginRltDto;
import jp.co.broadleaf.breg.loginmaker.facade.dto.UserMasterDto;
import jp.co.broadleaf.breg.message.service.Facade.MessageFacade;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.BregPropertyKeys;
import jp.co.broadleaf.breg.companysetting.dto.CompanySettingDto;
import jp.co.broadleaf.breg.web.loginmaker.form.LoginForm;
import jp.co.broadleaf.common.cache.CacheKeySpecs;
import jp.co.broadleaf.common.cache.CacheKeyUtils;
import jp.co.broadleaf.common.property.PropertyFacade;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.web.BroadleafSessionKeys;
import jp.co.broadleaf.framework.exception.BusinessException;
import jp.co.broadleaf.framework.security.Anonymous;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.Submission;
import jp.co.broadleaf.framework.web.WebResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * ログインControllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/loginmaker")
public class LoginController extends BaseController {

  /**
   * <pre>
   * 初期表示時。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return ModelAndView
   */
  @Anonymous
  @RequestMapping(path = "/login", method = RequestMethod.GET)
  public ModelAndView loginInit(HttpServletRequest request, HttpServletResponse response) {
    logger.debug("ログインの初期表示処理を開始します。");
    ModelAndView result = new ModelAndView(LOGIN_URL);
    try {
      // 共通メッセージ情報を取得
      messageMap = messageFacade.getMessageMap();

      // cookieNameをプロパティに取得
      String cookieName = propertyFacade.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_COOKIE_NAME);
      // Cookie名よりCookie値を取得する
      String loginCookieId = getCookieValueByName(request, cookieName);

      if (!StringUtils.isEmpty(loginCookieId)) {
        // ログイン認証確認処理
        result = setLoginInfo(request, response, loginCookieId, result);
      }
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("ログインの初期表示処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * ログイン処理を実行する。
   * </pre>
   * 
   * @param form ログインForm
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return 処理結果
   */
  @Anonymous
  @RequestMapping(path = "/login", method = RequestMethod.POST)
  @ResponseBody
  @Submission(multiply = false)
  public WebResult normalLogin(@RequestBody LoginForm form, HttpServletRequest request, HttpServletResponse response) {
    logger.debug("ログインのログイン処理を開始します。");
    WebResult result = new WebResult();
    try {
      // 画面入力チェック
      form.validate();

      // ipAddress取得
      String ipAddress = getIpAddrByRequest(request);
      // 担当者でログインする
      int makerCode = Integer.parseInt(form.getMakerCd());
      String cookieSend = form.getCookieSend();
      LoginRltDto loginResult = userMasterFacade.loginUserMaster(makerCode, form.getLoginId(), form.getPassword(),
          ipAddress, cookieSend);

      // パスワード一致チェック結果がFalseの場合、エラーメッセージを設定し画面へ返却する
      if (!loginResult.getPwdAgreementFlag()) {
        if (loginResult.getAccountLockFlag()) {
          // 担当者ロック判定実行結果がtrueの場合
          throw new BusinessException(BregMessageCodes.E00106,
              String.valueOf(loginResult.getLoginAttemptCntSpecifiedValue()));
        } else {
          // 担当者ロック判定 実行結果がfalseの場合
          throw new BusinessException(BregMessageCodes.E00107);
        }
      } else if (!loginResult.getPwdConfirmFlag()) {
        // パスワード有効期限チェック結果がfalseの場合
        throw new BusinessException(BregMessageCodes.E00108);
      }

      // ログイン認証確認
      String loginCookieId = loginResult.getLoginCookieIDentifyId();

      if (request.getSession(false) != null) {
        // 過去のセッションを削除する
        request.getSession().invalidate();
      }

      // セッションへ値を設定する
      // ログイン情報を取得する
      Principal principal = getLoginPrincipal(loginResult, cookieSend);
      // 新セッションを作成
      HttpSession session = request.getSession(true);
      // ログイン後の処理をコールする
      kickout(principal, loginCookieId);
      // ログイン情報はセッションに格納する。
      savePrincipalToSession(session, principal);
      // サービスなどにログイン情報を簡単に利用できるために、ログイン情報がログインコンテキストに格納する。
      SecurityContextHolder.setPrincipal(principal);
      // ログインCookieを設定する
      setCookieInfo(loginCookieId, response, true, cookieSend);

      // 共通メッセージマップを設定する
      result.put("messageMap", messageMap);
      // 遷移URLへ遷移する
      // プロパティからデフォルトURLを取得する。
      String sendURL = propertyFacade.getProperty(BregPropertyKeys.BREG_ACCOUNT_DEFAULT_URL);
      // 遷移URL画面へ遷移する
      result.put("sendurl", sendURL);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    logger.debug("ログインのログイン処理を終了します。");
    return result;
  }

  /**
   * <pre>
   * ログイン認証確認処理。
   * </pre>
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @param loginCookieId Cookie識別ID
   * @param modelAndView ModelAndView
   * @return 遷移ModelAndView
   */
  private ModelAndView setLoginInfo(HttpServletRequest request, HttpServletResponse response, String loginCookieId,
                                    ModelAndView modelAndView) {
    logger.debug("ログインのログイン認証確認処理を開始します。");
    ModelAndView returnModelAndView = modelAndView;
    try {
      // セッションから担当者情報を取得する。
      Principal principal = getPrincipalFromSession(request);

      // ログイン状態を維持取得
      String cookieSend = "";
      if (principal != null) {
        if (!isKickout(principal, loginCookieId)) {
          cookieSend = ((LoginPrincipal) principal).getCookieSend();
          // リクエストからログインCookieが取得できた場合、ログイン認証確認処理へ
          // ログイン認証確認
          LoginCertifConfirmDto loginCertifConfirmDto = loginFacade.confirmLoginCertif(loginCookieId, cookieSend);

          if (request.getSession(false) != null) {
            // 過去のセッションを削除する
            request.getSession().invalidate();
          }

          // セッションへ値を設定する
          if (loginCertifConfirmDto != null) {
            // ログイン情報を取得する
            principal = getLoginPrincipal(loginCertifConfirmDto, cookieSend);
            // 新セッションを作成
            HttpSession session = request.getSession(true);
            // ログインCookie登録
            loginCookieId = loginFacade.addLoginCookie(loginCertifConfirmDto.getUserMasterDto().getMakerCode(),
                loginCertifConfirmDto.getUserMasterDto().getLoginId(), loginCertifConfirmDto.getCookieExpiryDt(),
                cookieSend);
            // ログインCookieを設定する
            setCookieInfo(loginCookieId, response, false, null);
            // ログイン後の処理をコールする
            kickout(principal, loginCookieId);
            // ログイン情報はセッションに格納する。
            savePrincipalToSession(session, principal);
            // サービスなどにログイン情報を簡単に利用できるために、ログイン情報がログインコンテキストに格納する。
            SecurityContextHolder.setPrincipal(principal);
            // ログイン情報をログインFormに設定する
            if ("true".equals(cookieSend)) {
              LoginForm form = setPrincipalToForm(principal);
              returnModelAndView.addObject("form", form);
            }
          }
        }
      } else {
        // ログイン認証確認
        LoginCertifConfirmDto loginCertifConfirmDto = loginFacade.confirmLoginCertif(loginCookieId, cookieSend);

        if (loginCertifConfirmDto != null) {
          // セッションへ値を設定する
          // ログイン情報を取得する
          principal = getLoginPrincipal(loginCertifConfirmDto, cookieSend);
          // 新セッションを作成
          HttpSession session = request.getSession(true);
          // ログインCookie登録
          loginCookieId = loginFacade.addLoginCookie(loginCertifConfirmDto.getUserMasterDto().getMakerCode(),
              loginCertifConfirmDto.getUserMasterDto().getLoginId(), loginCertifConfirmDto.getCookieExpiryDt(),
              cookieSend);
          // ログインCookieを設定する
          setCookieInfo(loginCookieId, response, false, null);
          // ログイン後の処理をコールする
          kickout(principal, loginCookieId);
          // ログイン情報はセッションに格納する。
          savePrincipalToSession(session, principal);
          // サービスなどにログイン情報を簡単に利用できるために、ログイン情報がログインコンテキストに格納する。
          SecurityContextHolder.setPrincipal(principal);
          // ログイン情報を画面にセットする
          if ("true".equals(cookieSend)) {
            LoginForm form = setPrincipalToForm(principal);
            returnModelAndView.addObject("form", form);
          }
        }
      }
    } catch (Throwable ex) {
      // Facade実行時に例外が発生しない場合、未ログイン状態処理へ
    }
    logger.debug("ログインのログイン認証確認処理を終了します。");
    return returnModelAndView;
  }

  /**
   * <pre>
   * ログインCookieを設定する。
   * </pre>
   * 
   * @param loginCookieId Cookie識別ID
   * @param response HttpServletResponse
   * @param isFirstLogin true:初回ログイン、false:ログイン済み
   * @param cookieSend ログイン状態を維持(初回ログイン用)
   */
  private void setCookieInfo(String loginCookieId, HttpServletResponse response, boolean isFirstLogin,
                             String cookieSend) {
    // cookieNameをプロパティに取得
    String cookieName = propertyFacade.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_COOKIE_NAME);
    // ログインCookie有効時間
    int cookieMaxAge = propertyFacade.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_COOKIE_EXPIRY_PERIOD);
    // Cookieパス
    String cookiePath = propertyFacade.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_COOKIE_PATH);
    // Cookieセキュア属性
    String cookieSecure = propertyFacade.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_COOKIE_SECURE);
    // ログインCookie設定
    Cookie cookie = new Cookie(cookieName, loginCookieId);
    // Cookie有効期限
    if (isFirstLogin) {
      if ("true".equals(cookieSend)) {
        cookie.setMaxAge(cookieMaxAge);
      }
    } else {
      cookie.setMaxAge(cookieMaxAge);
    }
    if (StringUtils.isNotEmpty(cookiePath)) {
      cookie.setPath(cookiePath);
    }
    if ("true".equals(cookieSecure)) {
      cookie.setSecure(true);
    } else {
      cookie.setSecure(false);
    }
    response.addCookie(cookie);
  }

  /**
   * <pre>
   * ログイン情報を取得する。
   * </pre>
   * 
   * @param loginCertifConfirmDto ログイン認証確認Dto
   * @param cookieSend ログイン状態を維持
   * @return ログイン情報
   */
  private Principal getLoginPrincipal(LoginCertifConfirmDto loginCertifConfirmDto, String cookieSend) {
    // ログイン情報
    LoginPrincipal principal = new LoginPrincipal();
    if (null != loginCertifConfirmDto) {
      // 担当者情報Dto
      UserMasterDto userMaster = loginCertifConfirmDto.getUserMasterDto();
      // 会社情報Dto
      CompanySettingDto companySetting = loginCertifConfirmDto.getCompanySettingDto();
      principal = convertUserMasterToLoginInfo(userMaster, companySetting, cookieSend);
    }
    // 共通メッセージマップ
    principal.setMessageMap(messageMap);
    return principal;
  }

  /**
   * <pre>
   * ログイン情報を取得する。
   * </pre>
   * 
   * @param loginRltDto ログインチェック結果Dto
   * @param cookieSend ログイン状態を維持
   * @return ログイン情報
   */
  private Principal getLoginPrincipal(LoginRltDto loginRltDto, String cookieSend) {
    // ログイン情報
    LoginPrincipal principal = new LoginPrincipal();
    if (null != loginRltDto) {
      // 担当者情報Dto
      UserMasterDto userMaster = loginRltDto.getUserMasterDto();
      // 会社情報Dto
      CompanySettingDto companySetting = loginRltDto.getCompanySettingDto();
      principal = convertUserMasterToLoginInfo(userMaster, companySetting, cookieSend);
    }
    // 共通メッセージマップ
    principal.setMessageMap(messageMap);
    return principal;
  }

  /**
   * <pre>
   * 担当者情報Dtoと会社情報Dtoからログイン情報を取得する。
   * </pre>
   * 
   * @param userMaster 担当者情報Dto
   * @param companySetting 会社情報Dto
   * @param cookieSend ログイン状態を維持
   * @return ログイン情報
   */
  private LoginPrincipal convertUserMasterToLoginInfo(UserMasterDto userMaster, CompanySettingDto companySetting,
                                                      String cookieSend) {
    // ログイン情報
    LoginPrincipal principal = new LoginPrincipal();
    // ログインID
    principal.setLoginId(userMaster.getLoginId());
    // メーカーコード
    principal.setMakerCode(userMaster.getMakerCode());
    // 担当者名称
    principal.setUserName(userMaster.getUserName());
    // 担当者名称（カナ）
    principal.setUserKana(userMaster.getUserKana());
    // ユーザー管理者フラグ
    principal.setUserAdminFlag(userMaster.getUserAdminFlag());
    // 会社名称
    principal.setCompanyName(companySetting.getCompanyName());
    // メーカー名称
    principal.setMakerCdName(companySetting.getMakerCdName());
    // メーカー名称（半角）
    principal.setMakerCdNameShort(companySetting.getMakerCdNameShort());
    // 会社名称(カナ)
    principal.setCompanyNameKana(companySetting.getCompanyNameKana());
    // 商品ページ内行数
    principal.setGoodsRows(companySetting.getGoodsRows());
    // セットページ内行数
    principal.setSetRows(companySetting.getSetRows());
    // 結合ページ内行数
    principal.setJoinRows(companySetting.getJoinRows());
    // 申請履歴ページ内行数
    principal.setApplyRecordRows(companySetting.getApplyRecordRows());
    // 申請詳細ページ内行数
    principal.setApplyDetailRows(companySetting.getApplyDetailRows());
    // 商品インポート方法
    principal.setGoodsImportType(companySetting.getGoodsImportType());
    // セットインポート方法
    principal.setSetImportType(companySetting.getSetImportType());
    // 結合インポート方法
    principal.setJoinImportType(companySetting.getJoinImportType());
    // ユーザー種別
    principal.setUserKind(userMaster.getUserKind());
    // ログイン状態を維持
    principal.setCookieSend(cookieSend);
    return principal;
  }

  /**
   * <pre>
   * キックアウトかどうかをチェックする。
   * </pre>
   * 
   * @param principal ログインユーザ情報
   * @param cookieId CookieID
   * @return True:キックアウト
   */
  private boolean isKickout(Principal principal, String cookieId) {
    LoginPrincipal loginInfo = (LoginPrincipal) principal;
    String key = getLoginRedisKey(loginInfo.getMakerCode(), loginInfo.getLoginId());
    String loginedCookieId = redisTemplate.boundValueOps(key).get();
    return !Objects.equals(loginedCookieId, cookieId);
  }

  /**
   * <pre>
   * セッションにログイン情報が取得する。
   * </pre>
   * 
   * @param request リクエスト
   * @return ログイン情報
   */
  private Principal getPrincipalFromSession(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return null;
    }
    return (Principal) session.getAttribute(BroadleafSessionKeys.LOGIN_INFO);
  }

  /**
   * <pre>
   * ログイン情報はセッションに格納する。
   * </pre>
   * 
   * @param session セッション
   * @param principal ログイン情報
   */
  private void savePrincipalToSession(HttpSession session, Principal principal) {
    session.setAttribute(BroadleafSessionKeys.LOGIN_INFO, principal);
  }

  /**
   * <pre>
   * ログイン情報はログインFormに設定する。
   * </pre>
   * 
   * @param principal ログイン情報
   * @return ログインForm
   */
  private LoginForm setPrincipalToForm(Principal principal) {
    LoginForm form = new LoginForm();
    LoginPrincipal loginPrincipal = (LoginPrincipal) principal;
    form.setMakerCd(String.valueOf(loginPrincipal.getMakerCode()));
    form.setLoginId(loginPrincipal.getLoginId());
    return form;
  }

  /**
   * <pre>
   * IPアドレスを取得する。
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
   * キックアウト処理を実行する。
   * </pre>
   * 
   * @param principal ログインユーザ情報
   * @param newCookieId 新CookieID
   */
  private void kickout(Principal principal, String newCookieId) {
    LoginPrincipal loginInfo = (LoginPrincipal) principal;
    String key = getLoginRedisKey(loginInfo.getMakerCode(), loginInfo.getLoginId());
    redisTemplate.boundValueOps(key).set(newCookieId);
  }

  /**
   * <pre>
   * メーカーコードとログインIDより、セッションIDを検索用のRedisキーを取得する。
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @return Redisキー
   */
  private String getLoginRedisKey(int makerCode, String loginId) {
    String loginAccount = makerCode + "_" + loginId;
    return CacheKeyUtils.getCacheKey(CacheKeySpecs.LOGIN_SCHEMA, loginAccount);
  }

  /**
   * <pre>
   * Cookie名よりCookie値を取得する。
   * </pre>
   * 
   * @param request リクエスト
   * @param cookieName Cookie名
   * @return Cookie値
   */
  private String getCookieValueByName(HttpServletRequest request, String cookieName) {
    Cookie[] cookies = request.getCookies();
    String cookieValue = null;
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(cookieName)) {
          cookieValue = cookie.getValue();
        }
      }
    }
    return cookieValue;
  }

  /** 担当者Facade */
  private UserMasterFacade userMasterFacade;

  /** ログインFacade */
  private LoginFacade loginFacade;

  /** ログイン画面 */
  private static final String LOGIN_URL = "loginmaker/login";

  /** Redisテンプレート */
  private RedisTemplate<String, String> redisTemplate;

  /** プロパティ取得 */
  private PropertyFacade propertyFacade;

  /** 共通メッセージFacade */
  private MessageFacade messageFacade;

  /** 共通メッセージマップ */
  private HashMap<String, String> messageMap;

  /**
   * <pre>
   * 担当者Facadeを設定する。
   * </pre>
   * 
   * @param userMasterFacade 担当者Facade
   */
  @Resource
  public void setUserMasterFacade(UserMasterFacade userMasterFacade) {
    this.userMasterFacade = userMasterFacade;
  }

  /**
   * <pre>
   * ログインFacadeを設定する。
   * </pre>
   *
   * @param loginFacade ログインFacade
   */
  @Resource
  public void setLoginFacade(LoginFacade loginFacade) {
    this.loginFacade = loginFacade;
  }

  /**
   * <pre>
   * RedisTemplateを設定する。
   * </pre>
   * 
   * @param redisTemplate Redisテンプレート
   */
  @Resource
  public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * <pre>
   * プロパティ取得を設定する。
   * </pre>
   *
   * @param propertyFacade プロパティ取得
   */
  @Resource
  public void setPropertyFacade(PropertyFacade propertyFacade) {
    this.propertyFacade = propertyFacade;
  }

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

}
