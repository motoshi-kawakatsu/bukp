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
package jp.co.broadleaf.framework.security;

import java.security.Principal;

/**
 * <pre>
 * 自動ログインリゾルバインタフェースです。
 * </pre>
 */
public interface LoginResolver {

  /**
   * ログイン情報を取得する。
   * 
   * @param cookieId CookieID
   * @param cookieSend CookieSend
   * @return ログイン情報
   */
  Principal getLoginPrincipal(String cookieId, String cookieSend);

  /**
   * キックアウトかどうかをチェックする
   * 
   * @param principal ログインユーザ情報
   * @param cookieId CookieID
   * @return True:キックアウト
   */
  boolean isKickout(Principal principal, String cookieId);

  /**
   * キックアウト処理を実行する。
   * 
   * @param principal ログインユーザ情報
   * @param newCookieId 新CookieID
   */
  void kickout(Principal principal, String newCookieId);

  /**
   * キックアウト用のCookie名を取得する。
   * 
   * @return Cookie名
   */
  String getCookieName();

  /**
   * キックアウト用のCookie有効時間を取得する。
   * 
   * @return Cookie有効時間
   */
  int getCookieMaxAge();

  /**
   * キックアウト用のCookieパスを取得する。
   * 
   * @return Cookieパス
   */
  String getCookiePath();

  /**
   * キックアウト用のCookieセキュア属性を取得する。
   * 
   * @return Cookieセキュア属性
   */
  String getCookieSecure();

  /**
   * ログインURLを取得する。
   * 
   * @return ログインURL
   */
  String getLoginUrl();

  /**
   * ログインCookie登録。
   * 
   * @param cookieId ログインCookie
   * @param principal ログイン情報
   * @return ログインCookieID
   */
  String loginCookieReg(String cookieId , Principal principal);
}
