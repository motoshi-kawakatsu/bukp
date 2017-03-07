//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/24   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.framework.web.interceptor;

import jp.co.broadleaf.framework.security.Anonymous;
import jp.co.broadleaf.framework.security.LoginResolver;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.WebResult;
import jp.co.broadleaf.framework.web.WebResultStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * セキュリティインターセプタークラスです。
 * </pre>
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

  /**
   * ログインリゾルバ
   */
  private LoginResolver loginResolver;

  /**
   * ログイン情報格納用のセッションキー
   */
  private String principalSessionKey;

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    // パラメータhandlerがHandlerMethod以外の場合（img,css,jsなどリソース）、ログインチェックがしない
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }

    // このリクエストがログインチェック必要かどうかを確認する
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    if (!isNeedCheckLogin(request, handlerMethod)) {
      return true;
    }

    if (loginResolver == null) {
      throw new NullPointerException("loginResolver must not be null");
    }

    // キックアウト用Cookie識別IDを取得する。
    String cookieId = getCookieValueByName(request, loginResolver.getCookieName());
    if (cookieId == null || cookieId.isEmpty()) {
      // 未ログインとして処理する。
      handleNotLogin(request, response, handlerMethod);
      return false;
    }

    // セッションからアカウント情報を取得する。
    Principal principal = getPrincipalFromSession(request);
    if (principal != null) {

      // セッションがある場合、キックアウトチェックを実行する。
      if (loginResolver.isKickout(principal, cookieId)) {
        // 未ログインとして処理する。
        handleNotLogin(request, response, handlerMethod);
        return false;
      } else {
        // サービスなどにログイン情報を簡単に利用できるために、ログイン情報がログインコンテキストに格納する。
        SecurityContextHolder.setPrincipal(principal);
        return true;
      }

    } else {
      // 自動ログイン用のログイン情報を取得する。
      principal = loginResolver.getLoginPrincipal(cookieId, "false");
      if (principal == null) {
        // 未ログインとして処理する。
        handleNotLogin(request, response, handlerMethod);
        return false;
      }
      // ログインCookie登録
      cookieId = loginResolver.loginCookieReg(cookieId, principal);
      // ログインCookieを設定する。
      setCookieInfo(cookieId, response);
      // 新セッションを作成
      HttpSession session = request.getSession(true);
      // ログイン後の処理をコールする
      loginResolver.kickout(principal, cookieId);
      // ログイン情報はセッションに格納する。
      savePrincipalToSession(session, principal);
      // サービスなどにログイン情報を簡単に利用できるために、ログイン情報がログインコンテキストに格納する。
      SecurityContextHolder.setPrincipal(principal);
      return true;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                              Exception ex) throws Exception {
    // ログインコンテキストをリセットする。
    SecurityContextHolder.resetPrincipal();
  }

  /**
   * このリクエストがログインチェック必要かどうかを確認する
   * 
   * @param request リクエスト
   * @param handlerMethod ハンドラメソッド
   * @return ログインチェックが必要であれば、true
   */
  protected boolean isNeedCheckLogin(HttpServletRequest request, HandlerMethod handlerMethod) {

    // 匿名アクセス用アノテーションがある場合、ログインチェックがチェックしない。
    // 例：ログイン画面、ログインボタンのSubmit処理など
    if (handlerMethod.getMethod().getAnnotation(Anonymous.class) != null) {
      return false;
    }
    return true;
  }

  /**
   * Ajaxリクエストかどうかを確認する
   * 
   * @param request リクエスト
   * @param handlerMethod ハンドラメソッド
   * @return Ajaxリクエスト場合、true
   */
  protected boolean isAjaxRequest(HttpServletRequest request, HandlerMethod handlerMethod) {
    // ResponseBodyアノテーションがある場合、AJAXリクエストとします。
    if (handlerMethod.getMethod().getAnnotation(ResponseBody.class) != null) {
      return true;
    }
    return false;
  }

  /**
   * 未ログイン状態場合の処理を実行する。
   * 
   * @param request リクエスト
   * @param response レスポンス
   * @param handlerMethod ハンドラメソッド
   * @throws IOException IO例外が発生する場合
   */
  protected void handleNotLogin(HttpServletRequest request, HttpServletResponse response,
                                HandlerMethod handlerMethod) throws IOException {
    // ログイン取得できない場合は、ログイン画面へ遷移する。
    String loginUrl = loginResolver.getLoginUrl();

    if (isAjaxRequest(request, handlerMethod)) {
      WebResult result = new WebResult();
      result.setStatus(WebResultStatus.NOTLOGIN_ERROR);
      result.put("loginurl", loginUrl);
      ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(response.getWriter(), result);
    } else {
      response.sendRedirect(loginUrl);
    }
  }

  /**
   * <pre>
   * ログインCookieを設定する。
   * </pre>
   * 
   * @param loginCookieId Cookie識別ID
   * @param response HttpServletResponse
   */
  private void setCookieInfo(String loginCookieId, HttpServletResponse response) {
    // cookieNameをプロパティに取得
    String cookieName = loginResolver.getCookieName();
    // ログインCookie有効時間
    int cookieMaxAge = loginResolver.getCookieMaxAge();
    // Cookieパス
    String cookiePath = loginResolver.getCookiePath();
    // Cookieセキュア属性
    String cookieSecure = loginResolver.getCookieSecure();

    // ログインCookie設定
    Cookie cookie = new Cookie(cookieName, loginCookieId);
    // Cookie有効期限
    cookie.setMaxAge(cookieMaxAge);
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
   * セッションにログイン情報が取得する。
   * 
   * @param request リクエスト
   * @return ログイン情報
   */
  protected Principal getPrincipalFromSession(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return null;
    }
    return (Principal) session.getAttribute(principalSessionKey);
  }

  /**
   * ログイン情報はセッションに格納する。
   * 
   * @param session セッション
   * @param principal ログイン情報
   */
  protected void savePrincipalToSession(HttpSession session, Principal principal) {
    session.setAttribute(principalSessionKey, principal);
  }

  /**
   * Cookie名よりCookie値を取得する。
   * 
   * @param request リクエスト
   * @param cookieName Cookie名
   * @return Cookie値
   */
  protected String getCookieValueByName(HttpServletRequest request, String cookieName) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals(cookieName)) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }

  /**
   * ログインリゾルバを設定する。
   *
   * @param loginResolver ログインリゾルバ
   */
  public void setLoginResolver(LoginResolver loginResolver) {
    this.loginResolver = loginResolver;
  }

  /**
   * ログイン情報格納用のセッションキーを設定する。
   *
   * @param principalSessionKey ログイン情報格納用のセッションキー
   */
  public void setPrincipalSessionKey(String principalSessionKey) {
    this.principalSessionKey = principalSessionKey;
  }

}
