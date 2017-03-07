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
package jp.co.broadleaf.breg.loginmaker.facade;

import jp.co.broadleaf.breg.loginmaker.facade.dto.LoginCertifConfirmDto;

import java.sql.Timestamp;

/**
 * <pre>
 * ログインFacadeインタフェース.
 * </pre>
 */
public interface LoginFacade {

  /**
   * <pre>
   * ログインCookie登録.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @param loginId ログインID
   * @param cookieExpiryDt Cookie有効期限
   * @param cookieSend ログイン状態を維持
   * @return Cookie識別ID
   */
  String addLoginCookie(int makerCd, String loginId, Timestamp cookieExpiryDt, String cookieSend);

  /**
   * <pre>
   * ログイン認証確認.
   * </pre>
   * 
   * @param cookieIdentifyId Cookie識別ID
   * @param cookieSend ログイン状態を維持
   * @return ログイン認証確認Dto.
   */
  LoginCertifConfirmDto confirmLoginCertif(String cookieIdentifyId, String cookieSend);
}
