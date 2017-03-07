//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:曹　緩緩
// 作成日:2017/02/08    修正内容:パスワード忘れ：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.loginmaker.facade;

/**
 * <pre>
 * リマインダ受付Facadeインタフェース.
 * </pre>
 */
public interface ReminderReceptFacade {
  /**
   * <pre>
   * リマインダ受付（パスワード忘れ）.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @param loginId ログインID
   * @param ipAddr IPアドレス
   */
  void remindReceptForgetPwd(int makerCd, String loginId, String ipAddr);

}
