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

import jp.co.broadleaf.breg.loginmaker.facade.dto.LoginRltDto;
import jp.co.broadleaf.breg.loginmaker.facade.dto.UserMasterDto;

/**
 * <pre>
 * 担当者Facadeインタフェース.
 * </pre>
 */
public interface UserMasterFacade {

  /**
   * <pre>
   * 担当者ロック判定.
   * </pre>
   * 
   * @param userMasterDto 担当者情報
   * @param loginAttemptCntSpecifiedValue ログイン試行回数規定値
   * @param blMailAdr Bl側連絡メールアドレス
   * @return ログイン試行回数が規定値を超えている場合：True／ログイン試行回数が規定値を超えていない場合：False
   */
  boolean isUserMasterLock(UserMasterDto userMasterDto, int loginAttemptCntSpecifiedValue, String blMailAdr);

  /**
   * <pre>
   * 担当者でログインする.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param password パスワード
   * @param ipAddress IPアドレス
   * @param cookieSend ログイン状態を維持
   * @return ログイン結果
   */
  LoginRltDto loginUserMaster(int makerCode, String loginId, String password, String ipAddress, String cookieSend);
}
