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
package jp.co.broadleaf.breg.usersetting.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.lang3.Validate;

import jp.co.broadleaf.breg.common.BregPropertyKeys;
import jp.co.broadleaf.breg.common.entity.CertifInfoCommon;
import jp.co.broadleaf.breg.common.entity.CertifInfoCommonId;
import jp.co.broadleaf.breg.common.entity.UserMasterCommon;
import jp.co.broadleaf.breg.common.entity.UserMasterCommonId;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.loginmaker.library.ExpiryDtCalc;
import jp.co.broadleaf.breg.loginmaker.library.PwdConvKey;
import jp.co.broadleaf.breg.usersetting.service.UserSettingService;
import jp.co.broadleaf.common.property.PropertyResolver;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.exception.SystemException;

/**
 * <pre>
 * UserSettingサービスクラス.
 * </pre>
 */
public class UserSettingServiceImpl implements UserSettingService {

	/**
	 * ユーザー情報取得
	 * 
	 * @param loginId
	 *            ログインID
	 * @param makerCode
	 *            メーカーコード
	 * @return ユーザー情報
	 */
	@Override
	public UserMasterCommon searchUserInfoByLoginInfo(String loginId, int makerCode) {
		Validate.notNull(makerCode, "makerCd must not be null.");

		String login_Id = loginId;

		int maker_Code = makerCode;

		UserMasterCommonId userMasterCommonId = new UserMasterCommonId();
		userMasterCommonId.setLoginId(login_Id);
		userMasterCommonId.setMakerCode(maker_Code);

		UserMasterCommon userMasterCommon = userMasterCommonDao.findById(userMasterCommonId);

		return userMasterCommon;
	}

	/** BLアカウント情報登録DAO. */
	private GenericDao<UserMasterCommon, UserMasterCommonId> userMasterCommonDao;

	/**
	 * <pre>
	 * アカウント情報登録DAOを設定する.
	 * </pre>
	 * 
	 * @param userMasterCommonDao
	 *            ユーザー情報DAO アカウント情報登録DAO.
	 */
	@Resource
	public void setUserMasterCommonDao(GenericDao<UserMasterCommon, UserMasterCommonId> userMasterCommonDao) {
		this.userMasterCommonDao = userMasterCommonDao;
	}

	/**
	 * パスワード情報取得
	 * 
	 * @param loginId
	 *            ログインID
	 * @param makerCode
	 *            メーカーコード
	 * @return パスワード情報
	 */
	@Override
	public CertifInfoCommon searchPwdByLoginPwd(String loginId, int makerCode) {
		Validate.notNull(makerCode, "makerCd must not be null.");
		CertifInfoCommon certifInfoCommon = new CertifInfoCommon();

		String login_Id = loginId;
		int maker_Code = makerCode;

		certifInfoCommon.setLoginId(login_Id);
		CertifInfoCommonId certifInfoCommonId = new CertifInfoCommonId();
		certifInfoCommonId.setLoginId(login_Id);
		certifInfoCommonId.setMakerCode(maker_Code);

		certifInfoCommon = certifInfoCommonDao.findById(certifInfoCommonId);

		return certifInfoCommon;
	}

	/**
	 * ユーザー情報を保存する
	 * 
	 * @param userMasterCommon
	 *            ユーザー情報データ
	 * @param certifInfoCommon
	 *            パスワード情報データ
	 */
	@Override
	public void saveUserInfo(UserMasterCommon userMasterCommon, CertifInfoCommon certifInfoCommon) {

		CertifInfoCommon certifInfoInput = new CertifInfoCommon();
		UserMasterCommon userMasterInfo = userMasterCommon;

		int makerCode = userMasterInfo.getMakerCode();
		String loginId = userMasterInfo.getLoginId();

		if (null != certifInfoCommon.getLoginPwd() && (!"".equals(certifInfoCommon.getLoginPwd()))) {
			certifInfoInput.setLoginPwd(certifInfoCommon.getLoginPwd());
			certifInfoInput.setMakerCode(certifInfoCommon.getMakerCode());
			String loginPwd = certifInfoInput.getLoginPwd();

			Validate.notNull(loginPwd, "loginPwd must not be null");

			// 認証情報を取得
			// 検索条件を設定
			CertifInfoCommonId param = new CertifInfoCommonId();
			// メーカーコード = 引数.メーカーコード
			param.setMakerCode(makerCode);
			// ログインID = 引数.ログインID
			param.setLoginId(loginId);
			// 検索結果を取得
			CertifInfoCommon certifInfo = certifInfoCommonDao.findById(param);
			// 正常データがない場合、NULLを返却する
			if (certifInfo == null || certifInfo.getLogicalDelDiv() != DeleteFlgEnum.NoDelete.getValue()) {
				return;
			}

			// パスワード変換キー
			String pwdConvKey = PwdConvKey.generatePwdConvKey();
			// PBEKeyのイテレーション回数
			int pbekeyIterationCount = getPbekeyIterationCount();
			// 暗号化したパスワード
			String encryptionPwd = getEncryptionPwd(pwdConvKey, loginPwd, pbekeyIterationCount);
			// パスワード変換キー = 生成したパスワード変換キー
			certifInfo.setPwdConvKey(pwdConvKey);
			// ログインパスワード = 生成した暗号化したパスワード
			certifInfo.setLoginPwd(encryptionPwd);
			// ログイン試行回数=0（固定）
			certifInfo.setLoginAttemptCnt(0);
			int pwdExpiry = propertyResolver.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_PASSWORD_EXPIRY_PERIOD);
			Date systemDt = BroadleafUtils.getSystemDtTime();
			Date pwdExpiryDt = ExpiryDtCalc.calcExpiryDt(systemDt, pwdExpiry);
			certifInfo.setPwdExpiryDt(new Timestamp(pwdExpiryDt.getTime()));

			// 更新処理を行う
			certifInfoCommonDao.update(certifInfo);
		}

		// 更新対象のパスワードのNullチェック
		Validate.notNull(makerCode, "makerCode must not be null");

		userMasterCommonDao.forceUpdate(userMasterCommon);
	}

	/**
	 * CertifInfoCommonId
	 */
	private GenericDao<CertifInfoCommon, CertifInfoCommonId> certifInfoCommonDao;

	/**
	 * パスワードDAO
	 * 
	 * @param certifInfoCommonDao
	 *            パスワード情報DAO
	 */
	@Resource
	public void setCertifInfoCommonDao(GenericDao<CertifInfoCommon, CertifInfoCommonId> certifInfoCommonDao) {
		this.certifInfoCommonDao = certifInfoCommonDao;
	}

	/** プロパティ取得サービス. */
	private PropertyResolver propertyResolver;

	/**
	 * <pre>
	 * プロパティ取得サービスを設定する.
	 * </pre>
	 * 
	 * @param propertyResolver
	 *            プロパティ取得サービス
	 */
	@Resource
	public void setPropertyResolver(PropertyResolver propertyResolver) {
		this.propertyResolver = propertyResolver;
	}

	/**
	 * <pre>
	 * PBEKeyのイテレーション回数の取得.
	 * </pre>
	 * 
	 * @return PBEKeyのイテレーション回数
	 */
	private int getPbekeyIterationCount() {
		// PBEKeyのイテレーション回数
		return propertyResolver.getProperty(BregPropertyKeys.BREG_ACCOUNT_PBEKEY_ITERATION_COUNT);
	}

	/**
	 * <pre>
	 * 暗号化したパスワードの取得.
	 * </pre>
	 * 
	 * @param pwdConvKey
	 *            パスワード変換キー
	 * @param loginPwd
	 *            パスワード
	 * @param pbekeyIterationCount
	 *            PBEKeyのイテレーション回数
	 * @return 暗号化したパスワード
	 */
	private String getEncryptionPwd(String pwdConvKey, String loginPwd, int pbekeyIterationCount) {
		// 暗号化したパスワード
		String encryptionPwd = null;
		try {
			// パスワード暗号化
			encryptionPwd = encryptPassword(pwdConvKey.getBytes(CHARSETNAME), loginPwd, pbekeyIterationCount);
		} catch (Exception ex) {
			throw new SystemException(ex);
		}
		return encryptionPwd;
	}

	/**
	 * <pre>
	 * パスワードを暗号化する.
	 * </pre>
	 * 
	 * @param byteSalt
	 *            パスワード変換キー
	 * @param password
	 *            パスワード
	 * @param pbekeyIterationCount
	 *            PBEKeyのイテレーション回数
	 * @return 暗号化したパスワード
	 */
	@Override
	public String encryptPassword(byte[] byteSalt, String password, int pbekeyIterationCount) {
		// パスワード変換キーが32桁未満チェック
		if (byteSalt.length != DIGIT_32) {
			return null;
		}
		char[] charPassWord = password.toCharArray();
		byte[] hash;
		try {
			PBEKeySpec spec = new PBEKeySpec(charPassWord, byteSalt, pbekeyIterationCount, HASH_BYTE_SIZE);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
			hash = skf.generateSecret(spec).getEncoded();
		} catch (Exception ex) {
			throw new SystemException(ex);
		}
		return BroadleafUtils.convertBytesToHex(hash);
	}

	/** 文字セットの名前 */
	private static final String CHARSETNAME = "ASCII";
	/** 暗号化アルゴリズム */
	private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA256";
	/** PBEKeyのバイト数 */
	private static final int HASH_BYTE_SIZE = 1024;
	/** 定数３２桁 */
	private static final int DIGIT_32 = 32;
}
