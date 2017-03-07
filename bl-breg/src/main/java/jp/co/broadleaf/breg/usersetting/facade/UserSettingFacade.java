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
package jp.co.broadleaf.breg.usersetting.facade;

import java.util.List;

import jp.co.broadleaf.breg.usersetting.facade.dto.UserSettingDto;
import jp.co.broadleaf.breg.usersetting.facade.dto.UserSettingPwdDto;

/**
 * <pre>
 * 担当者情報Facadeインタフェース.
 * </pre>
 */
public interface UserSettingFacade {

	/**
	 * ユーザー情報取得
	 * 
	 * @param loginId ログインID
	 * @param makerCode メーカーコード
	 * 
	 * @return UserSettingDto
	 */
	UserSettingDto searchUserInfoByLoginInfo(String loginId, int makerCode);

	/**
	 * パスワード取得
	 * 
	 * @param loginId ログインID
	 * @param makerCode メーカーコード
	 * @return UserSettingPwdDto
	 */
	UserSettingPwdDto searchPwdByLoginPwd(String loginId, int makerCode);

	/**
	 * 担当者情報取得
	 * 
	 * @param userSettingDto ユーザー
	 * @param userSettingPwdDto パスワード
	 */
	void saveUserInfo(UserSettingDto userSettingDto, UserSettingPwdDto userSettingPwdDto);

    /**
     * メッセージを取得
     * 
     * @return メッセージ
     */
    List<String> getMessage();
    
}
