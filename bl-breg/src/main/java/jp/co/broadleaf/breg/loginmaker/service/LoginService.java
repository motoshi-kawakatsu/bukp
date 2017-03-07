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
package jp.co.broadleaf.breg.loginmaker.service;

import jp.co.broadleaf.breg.common.entity.LoginCookieMaker;

import java.sql.Timestamp;
import java.util.Date;

/**
 * <pre>
 * ログインCookieサービスインタフェース.
 * </pre>
 */
public interface LoginService {

  /**
   * <pre>
   * ログインCookie削除.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @return 削除したCookie識別IDの件数
   */
  int deleteLoginCookie(int makerCode, String loginId);

  /**
   * <pre>
   * ログインCookie登録.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param cookieIdentifyId Cookie識別ID
   * @param cookieExpiryDt 有効期限
   * @return ログインCookie情報
   */
  LoginCookieMaker addLoginCookie(int makerCode, String loginId, String cookieIdentifyId, Timestamp cookieExpiryDt);

  /**
   * <pre>
   * ログインCookie識別ID発行.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param systemDtTime システム日時
   * @return Cookie識別ID
   */
  String generateCookieIdentifyId(int makerCode, String loginId, Date systemDtTime);

  /**
   * <pre>
   * ログインCookie確認.
   * </pre>
   * 
   * @param cookieIdentifyId Cookie識別ID
   * @return Cookie情報
   */
  LoginCookieMaker confirmLoginCookie(String cookieIdentifyId);

  /**
   * <pre>
   * Cookie有効期限チェック.
   * </pre>
   * 
   * @param cookieExpiryDt Cookie有効期限
   * @param systemDtTime 現在日時
   * @return true:Cookie有効期限有効,flase:Cookie有効期限失効
   */
  boolean checkCookieExpiryDt(Date cookieExpiryDt, Date systemDtTime);
}
