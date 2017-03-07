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
package jp.co.broadleaf.breg.usersetting.service;

import jp.co.broadleaf.breg.common.entity.CertifInfoCommon;
import jp.co.broadleaf.breg.common.entity.UserMasterCommon;

/**
 * <pre>
 * UserSettingServiceインタフェース.
 * </pre>
 */
public interface UserSettingService {

	/**
	 * <pre>
	 * アカウント情報取得(ログインID).
	 * </pre>
	 * 
	 * @param makerCode メーカーコード
	 * @param loginId ログインID
	 * @return アカウント情報
	 */
	UserMasterCommon searchUserInfoByLoginInfo(String loginId, int makerCode);

	/**
	 * パスワード取得
	 * 
	 * @param loginId ログインID
	 * @param makerCode メーカーコード
	 * @return パスワード取得
	 */
	CertifInfoCommon searchPwdByLoginPwd(String loginId, int makerCode);

	/**
	 * ユーザー情報取得
	 * 
	 * @param userMasterCommon ユーザー情報データ
	 * @param certifInfoCommon パスワード情報データ
	 */
	void saveUserInfo(UserMasterCommon userMasterCommon, CertifInfoCommon certifInfoCommon);

	/**
	 * <pre>
	 * パスワードを暗号化する.
	 * </pre>
	 * 
	 * @param byteSalt パスワード変換キー
	 * @param password パスワード
	 * @param pdeKeyIterations PBEKeyのイテレーション回数
	 * @return 暗号化したパスワード
	 */
	String encryptPassword(byte[] byteSalt, String password, int pdeKeyIterations);

}
