//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.framework.web.filter;

import jp.co.broadleaf.framework.security.SecurityContextHolder;

import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * シンプルセキュリティフィルタクラスです。
 */
public class SimpleSecurityFilter implements Filter {

  /**
   * ログイン情報のセッションキーのパラメータ名
   */
  private static final String PRINCIPAL_SESSION_NAME_INIT_PARAM = "principalSessionName";

  /**
   * デフォルトログイン情報のセッションキー
   */
  private static final String DEFAULT_PRINCIPAL_SESSION_NAME = "principal";

  /**
   * ログインURIのパラメータ名
   */
  private static final String LOGIN_URI_INIT_PARAM = "loginUri";

  /**
   * デフォルトログインURI
   */
  private static final String DEFAULT_LOGIN_URI = "/login";

  /**
   * ログアウトURIのパラメータ名
   */
  private static final String LOGOUT_URI_INIT_PARAM = "logoutUri";

  /**
   * デフォルトログアウトURI
   */
  private static final String DEFAULT_LOGOUT_URI = "/logout";

  /**
   * サクセスURIのパラメータ名
   */
  private static final String SUCCESS_URI_INIT_PARAM = "successUri";

  /**
   * デフォルトログインサクセスURI
   */
  private static final String DEFAULT_SUCCESS_URI = "";

  /**
   * ログイン情報クラスのパラメータ名
   */
  private static final String PRINCIPAL_CLASS_INIT_PARAM = "principalClass";

  /**
   * ログイン情報クラス
   */
  private static final String LOGIN_PAGE_INIT_PARAM = "loginPage";

  /**
   * フィルタ設定
   */
  private FilterConfig config;

  /**
   * {@inheritDoc}
   */
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    this.config = filterConfig;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
                                                                                   ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;

    Principal principal = getPrincipalFromSession(request);
    if (principal != null) {
      SecurityContextHolder.setPrincipal(principal);
    }

    try {
      if (showLogin(request)) {
        showLoginPage(request, response);
        return;
      }

      if (submitLogout(request)) {
        doLogout(request, response);
        return;
      }

      if (submitLogin(request)) {
        if (doLogin(request, response)) {
          return;
        }
      }

      if (principal == null) {
        gotoLoginPage(request, response);
        return;
      }

      chain.doFilter(request, response);
    } finally {
      SecurityContextHolder.resetPrincipal();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void destroy() {
  }

  /**
   * セッションからログイン情報を取得する。
   * 
   * @param request リクエスト
   * @return ログイン情報
   */
  private Principal getPrincipalFromSession(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return null;
    }
    return (Principal) session.getAttribute(getLoginInfoSessionName());
  }

  /**
   * ログイン情報格納用セッションキー名を取得する。
   * 
   * @return ログイン情報格納用セッションキー名
   */
  private String getLoginInfoSessionName() {
    String name = config.getInitParameter(PRINCIPAL_SESSION_NAME_INIT_PARAM);
    return name != null ? name : DEFAULT_PRINCIPAL_SESSION_NAME;
  }

  /**
   * ログインURIを取得する。
   * 
   * @return ログインURI
   */
  private String getLoginUri() {
    String uri = config.getInitParameter(LOGIN_URI_INIT_PARAM);
    return uri != null ? uri : DEFAULT_LOGIN_URI;
  }

  /**
   * ログアウトURIを取得する。
   * 
   * @return ログアウトURI
   */
  private String getLogoutUri() {
    String uri = config.getInitParameter(LOGOUT_URI_INIT_PARAM);
    return uri != null ? uri : DEFAULT_LOGOUT_URI;
  }

  /**
   * サクセスURIを取得する。
   * 
   * @return サクセスURI
   */
  private String getSuccessUri() {
    String uri = config.getInitParameter(SUCCESS_URI_INIT_PARAM);
    return uri != null ? uri : DEFAULT_SUCCESS_URI;
  }

  /**
   * ログインページへ遷移する。
   * 
   * @param request リクエスト
   * @param response レスポンス
   * @throws IOException IOException
   */
  private void gotoLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.sendRedirect(request.getContextPath() + getLoginUri());
  }

  /**
   * ログインsubmitかどうかを判断する。
   * 
   * @param request リクエスト
   * @return 判断結果
   */
  private boolean submitLogin(HttpServletRequest request) {
    return request.getMethod().equalsIgnoreCase("post")
        && request.getRequestURI().equals(request.getContextPath() + getLoginUri());
  }

  /**
   * ログアウトsubmitかどうかを判断する。
   * 
   * @param request リクエスト
   * @return 判断結果
   */
  private boolean submitLogout(HttpServletRequest request) {
    return request.getRequestURI().equals(request.getContextPath() + getLogoutUri());
  }

  /**
   * ログインを実行する。
   * 
   * @param request リクエスト
   * @param response レスポンス
   * @return ログイン結果
   * @throws IOException IOException
   */
  private boolean doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Map<String, String> requestMap = getRequestMap(request);
    Principal principal = getPrincipal(requestMap);
    if (principal != null && principal.getName() != null && principal instanceof Serializable) {
      request.getSession().setAttribute(getLoginInfoSessionName(), (Serializable) principal);
      SecurityContextHolder.setPrincipal(principal);
      response.sendRedirect(request.getContextPath() + getSuccessUri());
      return true;
    }
    return false;
  }

  /**
   * ログアウトを実行する。
   * 
   * @param request リクエスト
   * @param response レスポンス
   * @throws IOException IOException
   */
  private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.removeAttribute(getLoginInfoSessionName());
    }
    response.sendRedirect(request.getContextPath() + getLoginUri());
  }

  /**
   * ログインページかどうかを判断する。
   * 
   * @param request リクエスト
   * @return 判断結果
   */
  private boolean showLogin(HttpServletRequest request) {
    return request.getMethod().equalsIgnoreCase("get")
        && request.getRequestURI().equals(request.getContextPath() + getLoginUri());
  }

  /**
   * マップからログイン情報を抽出する。
   * 
   * @param map マップ
   * @return ログイン情報
   */
  private Principal getPrincipal(Map<String, String> map) {
    try {
      String principalClassString = config.getInitParameter(PRINCIPAL_CLASS_INIT_PARAM);
      Class<?> principalClass = Class.forName(principalClassString);
      Principal principal = (Principal) principalClass.newInstance();
      BeanUtils.copyProperties(principal, map);
      return principal;
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * リクエストからマップを取得する。
   * 
   * @param request リクエスト
   * @return マップ
   */
  private Map<String, String> getRequestMap(HttpServletRequest request) {
    Enumeration<String> enumeration = request.getParameterNames();
    Map<String, String> map = new HashMap<String, String>();
    while (enumeration.hasMoreElements()) {
      String name = enumeration.nextElement();
      map.put(name, request.getParameter(name));
    }
    return map;
  }

  /**
   * ログインページへ遷移する。
   * 
   * @param request リクエスト
   * @param response レスポンス
   * @throws IOException IOException
   * @throws ServletException ServletException
   */
  private void showLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException,
                                                                                       ServletException {
    String loginPage = config.getInitParameter(LOGIN_PAGE_INIT_PARAM);
    request.getRequestDispatcher(loginPage).forward(request, response);
  }
}
