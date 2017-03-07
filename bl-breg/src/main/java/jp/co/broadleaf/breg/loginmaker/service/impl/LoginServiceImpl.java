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

import jp.co.broadleaf.breg.loginmaker.service.LoginService;
import jp.co.broadleaf.breg.common.entity.LoginCookieMaker;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.exception.SystemException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Validate;

import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

/**
 * <pre>
 * ログインCookieサービスクラス.
 * </pre>
 */
public class LoginServiceImpl implements LoginService {

  /**
   * <pre>
   * ログインCookie削除.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @return 削除したCookie識別IDの件数
   */
  @Override
  public int deleteLoginCookie(int makerCode, String loginId) {

    // 検索用ログインCookie entity
    LoginCookieMaker searchParam = new LoginCookieMaker();
    // メーカーコード=引数：メーカーコード
    searchParam.setMakerCode(makerCode);
    // ログインID=引数．ログインID
    searchParam.setLoginId(loginId);
    // 論理削除区分 = 0（有効）
    searchParam.setLogicalDelDiv(DeleteFlgEnum.NoDelete.getValue());

    // 検索
    List<LoginCookieMaker> loginCookieList = loginCookieMakerDao.findByExample(searchParam);
    if (CollectionUtils.isEmpty(loginCookieList)) {
      return 0;
    }

    // 削除
    for (LoginCookieMaker cookie : loginCookieList) {
      loginCookieMakerDao.delete(cookie);
    }

    // 削除した件数を返却する
    return loginCookieList.size();
  }
  
  /**
   * <pre>
   * ログインCookie登録.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param cookieIdentifyId Cookie識別ID
   * @param cookieExpiryDate 有効期限
   * @return ログインCookie情報
   */
  @Override
  public LoginCookieMaker addLoginCookie(int makerCode, String loginId, String cookieIdentifyId,
                                         Timestamp cookieExpiryDate) {

    Validate.notEmpty(cookieIdentifyId, "cookieIdentifyId must not be null");
    Validate.notNull(cookieExpiryDate, "cookieExpiryDate must not be null");

    // 登録用ログインCookie entity
    LoginCookieMaker loginCookie = new LoginCookieMaker();
    Timestamp datetime = new Timestamp(BroadleafUtils.getSystemDtTime().getTime());
    loginCookie.setUuid(UUID.randomUUID().toString().replace("-", ""));
    loginCookie.setInsDtTime(datetime);
    loginCookie.setUpdDtTime(datetime);
    loginCookie.setInsAccountId(loginId);
    loginCookie.setUpdAccountId(loginId);
    loginCookie.setLogicalDelDiv(0);
    // Cookie識別ID=引数．Cookie識別ID
    loginCookie.setCookieIdentifyId(cookieIdentifyId);
    // メーカーコード=引数．メーカーコード
    loginCookie.setMakerCode(makerCode);
    // ログインID=引数．ログインID
    loginCookie.setLoginId(loginId);
    // Cookie有効期限=引数．有効期限
    loginCookie.setCookieExpiryDate(cookieExpiryDate);

    // 登録処理を行う
    loginCookieMakerDao.insertForLogin(loginCookie);

    return loginCookie;
  }

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
  @Override
  public String generateCookieIdentifyId(int makerCode, String loginId, Date systemDtTime) {

    // ランダム文字列（128桁）を生成する
    String randomStr = this.getRandomString();
    // 文字列を連結する
    String connectedStr = makerCode + loginId + Long.toString(systemDtTime.getTime()) + randomStr;
    // 文字列をSHA256でハッシュ化
    byte[] hashedSalt = getHashedSalt(connectedStr);

    // 16進数の文字列に変換
    String encryptedString = BroadleafUtils.convertBytesToHex(hashedSalt);

    // ハッシュ化した文字列をBase64（UrlSafe）でエンコードする
    String cookieIdentifyId = null;
    try {
      cookieIdentifyId = BroadleafUtils.encodeToString(encryptedString.getBytes(CHARSETNAME));
    } catch (Exception e) {
      throw new SystemException(e);
    }

    return cookieIdentifyId;
  }

  /**
   * <pre>
   * ログインCookie確認.
   * </pre>
   * 
   * @param cookieIdentifyId Cookie識別ID
   * @return Cookie情報
   */
  @Override
  public LoginCookieMaker confirmLoginCookie(String cookieIdentifyId) {

    Validate.notNull(cookieIdentifyId, "cookieIdentifyId must not be null");

    // Cookie情報検索
    LoginCookieMaker result = loginCookieMakerDao.findById(cookieIdentifyId);

    // 論理削除区分 = 0（有効）
    if (null == result || result.getLogicalDelDiv() != DeleteFlgEnum.NoDelete.getValue()) {
      return null;
    }
    // 戻る値
    return result;
  }

  /**
   * <pre>
   * Cookie有効期限チェック.
   * </pre>
   * 
   * @param cookieExpiryDt Cookie有効期限
   * @param systemDtTime 現在日時
   * @return true:Cookie有効期限有効,flase:Cookie有効期限失効
   */
  @Override
  public boolean checkCookieExpiryDt(Date cookieExpiryDt, Date systemDtTime) {

    Validate.notNull(cookieExpiryDt, "cookieExpiryDt must not be null");
    Validate.notNull(systemDtTime, "systemDtTime must not be null");

    return cookieExpiryDt.compareTo(systemDtTime) == 1;
  }

  /**
   * <pre>
   * ランダム文字列（128桁）を生成する
   * </pre>
   * 
   * @return ランダム文字列（128桁）
   */
  private String getRandomString() {

    return RandomStringUtils.randomAscii(RANDOMCOUNT);
  }

  /**
   * <pre>
   * ソルトをハッシュ化して返却します
   * ※ハッシュアルゴリズムはSHA-256を使用
   * </pre>
   *
   * @param salt ソルト
   * @return ハッシュ化されたバイト配列のソルト
   */
  private byte[] getHashedSalt(String salt) {

    MessageDigest messageDigest;

    try {
      messageDigest = MessageDigest.getInstance(SHA256);
      messageDigest.update(salt.getBytes(CHARSETNAME));
    } catch (Exception e) {
      throw new SystemException(e);
    }

    return messageDigest.digest();
  }

  /** ログインCookie */
  private GenericDao<LoginCookieMaker, String> loginCookieMakerDao;

  /**
   * <pre>
   * ログインCookieDAOを設定する.
   * </pre>
   * 
   * @param loginCookieMakerDao ログインCookieDAO
   */
  @Resource
  public void setLoginCookieMakerDao(GenericDao<LoginCookieMaker, String> loginCookieMakerDao) {
    this.loginCookieMakerDao = loginCookieMakerDao;
  }

  /** ランダム文字数量 */
  private static final int RANDOMCOUNT = 128;

  /** ハッシュ化方法 */
  private static final String SHA256 = "SHA-256";

  /** フォーマット */
  private static final String CHARSETNAME = "UTF-8";
}
