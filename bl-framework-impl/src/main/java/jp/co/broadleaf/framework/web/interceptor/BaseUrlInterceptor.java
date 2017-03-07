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

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ベースURL設定用のインターセプタークラスです。
 */
public class BaseUrlInterceptor extends HandlerInterceptorAdapter {

  /**
   * ベースURLの変数名
   */
  private static final String BASEURL = "baseurl";

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    request.setAttribute(BASEURL, baseurl);
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {
    if (modelAndView != null && modelAndView.getModelMap() != null) {
      modelAndView.getModelMap().addAttribute(BASEURL, baseurl);
    }
  }

  /**
   * ベースURL値
   */
  private String baseurl;

  /**
   * ベースURL値を設定する。
   * 
   * @param baseurl ベースURL値
   */
  public void setBaseurl(String baseurl) {
    this.baseurl = baseurl;
  }

}
