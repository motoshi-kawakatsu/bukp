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
package jp.co.broadleaf.breg.loginmaker.service.impl;

import jp.co.broadleaf.breg.loginmaker.library.ExpiryDtCalc;
import jp.co.broadleaf.breg.loginmaker.library.PwdConvKey;
import jp.co.broadleaf.breg.loginmaker.service.UserMasterPwdService;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.BregPropertyKeys;
import jp.co.broadleaf.breg.common.entity.CertifInfoCommon;
import jp.co.broadleaf.breg.common.entity.CertifInfoCommonId;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.common.property.PropertyResolver;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.exception.BusinessException;
import jp.co.broadleaf.framework.exception.SystemException;

import org.apache.commons.lang3.Validate;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * <pre>
 * 担当者パスワードサービスクラス.
 * </pre>
 */
public class UserMasterPwdServiceImpl implements UserMasterPwdService {

  /**
   * <pre>
   * 認証情報のパスワード新規登録.
   * </pre>
   * 
   * @param makerCode 登録対象のメーカーコード
   * @param loginId 登録対象のログインID
   * @param encryptionPwd 登録対象の暗号化済パスワード
   * @param pwdConvKey 登録対象のパスワード変換キー
   * @return 登録した認証情報
   */
  @Override
  public CertifInfoCommon addPwdNew(int makerCode, String loginId, String encryptionPwd, String pwdConvKey) {

    // 登録対象の暗号化済パスワードのNullチェック
    Validate.notNull(encryptionPwd, "encryptionPwd must not be null");
    // 登録対象のパスワード変換キーのNullチェック
    Validate.notNull(pwdConvKey, "pwdConvKey must not be null");

    // 認証情報の設定
    CertifInfoCommon certifInfo = new CertifInfoCommon();
    // メーカーコード = 引数．メーカーコード
    certifInfo.setMakerCode(makerCode);
    // ログインID = 引数．ログインID
    certifInfo.setLoginId(loginId);
    // パスワード変換キー = 生成したパスワード変換キー
    certifInfo.setPwdConvKey(pwdConvKey);
    // ログインパスワード = 暗号化したパスワード
    certifInfo.setLoginPwd(encryptionPwd);
    // ログイン試行回数 = 0（固定）
    certifInfo.setLoginAttemptCnt(0);

    // 登録処理を行う
    certifInfoCommonDao.insert(certifInfo);

    return certifInfo;
  }

  /**
   * <pre>
   * 認証情報のパスワード更新.
   * </pre>
   * 
   * @param makerCode 登録対象のメーカーコード
   * @param loginId 登録対象のログインID
   * @param loginPwd 更新対象のパスワード
   * @return 更新した認証情報
   */
  @Override
  public CertifInfoCommon updatePassword(int makerCode, String loginId, String loginPwd) {

    // 更新対象のパスワードのNullチェック
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
      return null;
    }

    certifInfo.setUpdDtTime(new Timestamp(BroadleafUtils.getSystemDtTime().getTime()));
    certifInfo.setUpdAccountId(loginId);
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
    // プロパティファイルからパスワード有効時間を取得
    int pwdExpiryTime = propertyResolver.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_PASSWORD_EXPIRY_PERIOD);
    Date pwdExpiryDate = ExpiryDtCalc.calcExpiryDt(BroadleafUtils.getSystemDtTime(), pwdExpiryTime);
    // パスワード有効期限 = システム日時とプロパティのパスワード有効時間を算出する日時
    certifInfo.setPwdExpiryDt(new Timestamp(pwdExpiryDate.getTime()));

    // 更新処理を行う
    certifInfoCommonDao.forceUpdateForLogin(certifInfo);

    return certifInfo;
  }

  /**
   * <pre>
   * 認証情報の取得
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @return 認証情報
   */
  @Override
  public CertifInfoCommon getCertifInfo(int makerCode, String loginId) {

    // 認証情報の検索
    // 検索条件を設定
    CertifInfoCommonId param = new CertifInfoCommonId();
    // メーカーコード = 引数.メーカーコード
    param.setMakerCode(makerCode);
    // ログインID = 引数.ログインID
    param.setLoginId(loginId);
    // 検索結果を取得
    CertifInfoCommon res = certifInfoCommonDao.findById(param);
    // 取得できなかった場合、
    if (res == null || res.getLogicalDelDiv() != DeleteFlgEnum.NoDelete.getValue()) {
      return null;
    }
    return res;
  }
  
  /**
   * <pre>
   * ログイン試行回数チェック処理.
   * </pre>
   * 
   * @param loginAttemptCnt ログイン試行回数
   * @param loginAttemptCntSpecVal ログイン試行回数規定値
   * @return チェック結果 「True」:ログイン試行回数 ＜ ログイン試行回数規定値 のとき. 「False」: それ以外のとき.
   */
  @Override
  public boolean checkLoginAttemptCnt(int loginAttemptCnt, int loginAttemptCntSpecVal) {

    return loginAttemptCnt < loginAttemptCntSpecVal;
  }
  
  /**
   * <pre>
   * ログイン試行回数更新処理.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @return 更新結果
   */
  @Override
  public CertifInfoCommon updateLoginAttemptCnt(int makerCode, String loginId) {

    // ①認証情報の取得処理
    // 検索条件を設定
    CertifInfoCommonId param = new CertifInfoCommonId();
    // メーカーコード = 引数.メーカーコード
    param.setMakerCode(makerCode);
    // ログインID=引数．ログインID
    param.setLoginId(loginId);

    // 検索結果を取得
    CertifInfoCommon certifInfo = certifInfoCommonDao.findById(param);
    // 結果がないの場合、或いは論理削除区分 != 0（有効）の場合、nullを戻す
    if (certifInfo == null || certifInfo.getLogicalDelDiv() != DeleteFlgEnum.NoDelete.getValue()) {
      return null;
    }

    // ②ログイン試行回数更新処理
    // 更新用認証情報
    certifInfo.setUpdDtTime(getCurrentTimestamp());
    certifInfo.setUpdAccountId(loginId);
    // ログイン試行回数
    certifInfo.setLoginAttemptCnt(certifInfo.getLoginAttemptCnt() + 1);
    // 認証情報を更新する。
    certifInfoCommonDao.forceUpdateAllForLogin(certifInfo);

    return certifInfo;
  }

  /**
   * <pre>
   * ログイン回数リセット.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @return 更新した認証情報
   */
  @Override
  public CertifInfoCommon resetLoginCnt(int makerCode, String loginId) {
    // 検索条件を設定
    CertifInfoCommonId param = new CertifInfoCommonId();
    // メーカーコード = 引数.メーカーコード
    param.setMakerCode(makerCode);
    // ログインID=引数．ログインID
    param.setLoginId(loginId);
    // 検索結果を取得
    CertifInfoCommon resultAccountIdInfo = certifInfoCommonDao.findById(param);
    resultAccountIdInfo.setUpdDtTime(getCurrentTimestamp());
    resultAccountIdInfo.setUpdAccountId(loginId);
    // ログイン試行回数の変更
    resultAccountIdInfo.setLoginAttemptCnt(0);
    certifInfoCommonDao.forceUpdateForLogin(resultAccountIdInfo);
    return resultAccountIdInfo;
  }
  
  /**
   * システム日時を取得する 。
   * 
   * @return システム日時
   */
  private Timestamp getCurrentTimestamp() {
    return new Timestamp(BroadleafUtils.getSystemDtTime().getTime());
  }

  /**
   * <pre>
   * パスワード一致チェック.
   * </pre>
   * 
   * @param pwdConvKey 認証情報のパスワード変換キー
   * @param loginPwd 認証情報のログインパスワード
   * @param password パスワード
   * @return チェック結果 True:一致、false:一致しない
   */
  @Override
  public boolean checkPwdAccord(String pwdConvKey, String loginPwd, String password) {

    // パスワードのチェック
    Validate.notNull(password, "password must not be null");

    // PBEKeyのイテレーション回数
    int pbekeyIterationCount = getPbekeyIterationCount();
    // 暗号化したパスワード
    String encryptionPassword = getEncryptionPwd(pwdConvKey, password, pbekeyIterationCount);
    // 戻り値がNULLの場合、例外処理を行う
    if (null == encryptionPassword) {
      throw new BusinessException(BregMessageCodes.E90001);
    }

    // ログインパスワードと暗号化したパスワードの比較
    return compareByteList(convertFromHexToBin(loginPwd), convertFromHexToBin(encryptionPassword));
  }

  /**
   * <pre>
   * パスワード有効期限チェック.
   * </pre>
   * 
   * @param pwdExpiryDt 認証情報のパスワード有効期限
   * @param systemDtTime 現在日時
   * @return チェック結果 True:pwd有効期限有効、false:pwd有効期限失効
   */
  @Override
  public boolean checkPwdExpiryDt(Date pwdExpiryDt, Date systemDtTime) {

    Validate.notNull(pwdExpiryDt, "pwdExpiryDt must not be null");
    Validate.notNull(systemDtTime, "systemDtTime must not be null");

    return pwdExpiryDt.compareTo(systemDtTime) == 1;
  }

  /**
   * <pre>
   * パスワードを暗号化する.
   * </pre>
   * 
   * @param byteSalt パスワード変換キー
   * @param password パスワード
   * @param pbekeyIterationCount PBEKeyのイテレーション回数
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

  /**
   * <pre>
   * 16進数を2進数に変換する.
   * </pre>
   * 
   * @param hex 16進数
   * @return 2進数
   */
  private byte[] convertFromHexToBin(String hex) {
    byte[] binary = new byte[hex.length() / 2];
    for (int i = 0; i < binary.length; i++) {
      binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
    }
    return binary;
  }

  /**
   * <pre>
   * バイト配列を比較する.
   * </pre>
   * 
   * @param a バイト配列
   * @param b バイト配列
   * @return true:等しい、false:等しくない
   */
  private boolean compareByteList(byte[] a, byte[] b) {
    int diff = a.length ^ b.length;
    for (int i = 0; i < a.length && i < b.length; i++)
      diff |= a[i] ^ b[i];
    return diff == 0;
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
   * @param pwdConvKey パスワード変換キー
   * @param loginPwd パスワード
   * @param pbekeyIterationCount PBEKeyのイテレーション回数
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

  /** 認証情報DAO */
  private GenericDao<CertifInfoCommon, CertifInfoCommonId> certifInfoCommonDao;

  /**
   * <pre>
   * 認証情報DAOを設定する.
   * </pre>
   * 
   * @param certifInfoCommonDao 認証情報DAO
   */
  @Resource
  public void setCertifInfoCommonDao(GenericDao<CertifInfoCommon, CertifInfoCommonId> certifInfoCommonDao) {
    this.certifInfoCommonDao = certifInfoCommonDao;
  }

  /** プロパティ取得サービス */
  private PropertyResolver propertyResolver;

  /**
   * <pre>
   * プロパティ取得サービスを設定する.
   * </pre>
   * 
   * @param propertyResolver プロパティ取得サービス
   */
  @Resource
  public void setPropertyResolver(PropertyResolver propertyResolver) {
    this.propertyResolver = propertyResolver;
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
