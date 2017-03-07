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

import jp.co.broadleaf.breg.loginmaker.facade.dto.ConfirmPwdRltDto;

/**
 * <pre>
 * 担当者パスワードFacadeインタフェース.
 * </pre>
 */
public interface UserMasterPwdFacade {
  /**
   * <pre>
   * パスワード確認.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @param loginId ログインID
   * @param password パスワード
   * @param loginAttemptCntSpecifiedValue ログイン試行回数規定値
   * @return パスワード確認Dto
   */
  ConfirmPwdRltDto confirmUserMasterPwd(int makerCd, String loginId, String password, int loginAttemptCntSpecifiedValue);
}
