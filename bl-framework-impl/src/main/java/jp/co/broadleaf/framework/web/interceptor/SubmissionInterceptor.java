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
package jp.co.broadleaf.framework.web.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ダブルsubmitの防ぐ用インターセプタークラスです。
 */
public class SubmissionInterceptor extends HandlerInterceptorAdapter {

  /**
   * ダブルsubmitの防ぐ用セッションキー
   */
  private static final String EXECUTING_METHODS_SESSION_KEY = "SubmissionInterceptor_executing_methods";

  /**
   * GET_HTTP_METHOD
   */
  private static final String GET_HTTP_METHOD = "GET";

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (GET_HTTP_METHOD.equalsIgnoreCase(request.getMethod())) {
      return true;
    }
    HttpSession session = request.getSession(false);
    if (session != null && handler instanceof HandlerMethod) {
      Set<String> executingMethods = getExecutingMethods(session);
      if (executingMethods == null) {
        setExecutingMethods(session, new HashSet<String>());
        executingMethods = getExecutingMethods(session);
      }
      String currentMethod = ((HandlerMethod) handler).toString();
      if (executingMethods.contains(currentMethod)) {
        response.setStatus(405);
        return false;
      } else {
        executingMethods.add(currentMethod);
      }
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                              Exception ex) throws Exception {
    if (GET_HTTP_METHOD.equalsIgnoreCase(request.getMethod())) {
      return;
    }
    HttpSession session = request.getSession(false);
    if (session != null && handler instanceof HandlerMethod) {
      Set<String> executingMethods = getExecutingMethods(session);
      if (executingMethods != null) {
        String currentMethod = ((HandlerMethod) handler).toString();
        if (executingMethods.contains(currentMethod)) {
          executingMethods.remove(currentMethod);
        }
        setExecutingMethods(session, executingMethods);
      }
    }
  }

  /**
   * セッションから実行中のメソッドリストを取得する。
   * 
   * @param session セッション
   * @return 実行中のメソッドリスト
   */
  @SuppressWarnings("unchecked")
  private Set<String> getExecutingMethods(HttpSession session) {
    return (Set<String>) session.getAttribute(EXECUTING_METHODS_SESSION_KEY);
  }

  /**
   * 実行中のメソッドリストがセッションに設定する。
   * 
   * @param session セッション
   * @param executingMethods 実行中のメソッドリスト
   */
  private void setExecutingMethods(HttpSession session, Set<String> executingMethods) {
    session.setAttribute(EXECUTING_METHODS_SESSION_KEY, executingMethods);
  }
}
