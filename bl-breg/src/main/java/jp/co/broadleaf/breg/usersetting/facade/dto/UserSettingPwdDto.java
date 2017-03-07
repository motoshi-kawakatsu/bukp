//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:張　清詞
// 作成日:2017/02/13    修正内容:担当者情報:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.usersetting.facade.dto;

/**
 * <pre>
 * UserSettingPwdDto
 * </pre>
 */
public class UserSettingPwdDto {

	/**
	 * ログインパスワド
	 */
	private String loginPwd;

	/**
	 * ログインパスワドの取得。
	 *
	 * @return String ログインパスワド
	 */
	public String getLoginPwd() {
		return loginPwd;
	}

	/**
	 * ログインパスワドの設定。
	 *
	 * @param loginPwd ログインパスワド
	 */
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

}
