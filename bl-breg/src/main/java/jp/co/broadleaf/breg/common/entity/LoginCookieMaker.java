//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.entity;

import jp.co.broadleaf.common.domain.entity.BroadleafDomainEntity;
import jp.co.broadleaf.common.domain.entity.BroadleafOptimisticConcurrencyResolver;
import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.Entity;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrency;

import org.apache.commons.lang3.ArrayUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * ログインCookie(メーカー)本
 */
@Entity(name = "t_login_cookie_maker", type = "maker")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class LoginCookieMaker extends BroadleafDomainEntity<String> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * Cookie識別ID
   */
  public static final String COOKIE_IDENTIFY_ID = "cookieIdentifyId";

  /**
   * メーカーコード
   */
  public static final String MAKER_CODE = "makerCode";

  /**
   * ログインID
   */
  public static final String LOGIN_ID = "loginId";

  /**
   * Cookie有効期限
   */
  public static final String COOKIE_EXPIRY_DATE = "cookieExpiryDate";

  /**
   * コンストラクタ。
   */
  public LoginCookieMaker() {

  }

  /**
   * コンストラクタ。
   * 
   * @param cookieIdentifyId ID
   */
  public LoginCookieMaker(String cookieIdentifyId) {
    setCookieIdentifyId(cookieIdentifyId);
  }

  /**
   * Cookie識別IDを返します。
   * 
   * @return Cookie識別ID
   */
  public String getCookieIdentifyId() {
    return container().getPropertyValue(COOKIE_IDENTIFY_ID, String.class);
  }

  /**
   * Cookie識別IDを設定します。
   * 
   * @param cookieIdentifyId セットするCookie識別ID
   */
  public void setCookieIdentifyId(String cookieIdentifyId) {
    container().setPropertyValue(COOKIE_IDENTIFY_ID, cookieIdentifyId);
  }

  /**
   * メーカーコードを返します。
   * 
   * @return メーカーコード
   */
  public int getMakerCode() {
    return container().getPropertyValue(MAKER_CODE, Integer.class);
  }

  /**
   * メーカーコードを設定します。
   * 
   * @param makerCode セットするメーカーコード
   */
  public void setMakerCode(int makerCode) {
    container().setPropertyValue(MAKER_CODE, makerCode);
  }

  /**
   * ログインIDを返します。
   * 
   * @return ログインID
   */
  public String getLoginId() {
    return container().getPropertyValue(LOGIN_ID, String.class);
  }

  /**
   * ログインIDを設定します。
   * 
   * @param loginId セットするログインID
   */
  public void setLoginId(String loginId) {
    container().setPropertyValue(LOGIN_ID, loginId);
  }

  /**
   * Cookie有効期限を返します。
   * 
   * @return Cookie有効期限
   */
  public Timestamp getCookieExpiryDate() {
    return container().getPropertyValue(COOKIE_EXPIRY_DATE, Timestamp.class);
  }

  /**
   * Cookie有効期限を設定します。
   * 
   * @param cookieExpiryDate セットするCookie有効期限
   */
  public void setCookieExpiryDate(Timestamp cookieExpiryDate) {
    container().setPropertyValue(COOKIE_EXPIRY_DATE, cookieExpiryDate);
  }

  /**
   * エンティティIDを取得する。
   * 
   * @return エンティティID
   */
  @Override
  public String identifier() {
    return getCookieIdentifyId();
  }

  /**
   * エンティティIDのプロパティリストを取得する
   * 
   * @return エンティティIDのプロパティリスト
   */
  @Override
  public String[] identifierPropertyNames() {
    return new String[] { COOKIE_IDENTIFY_ID };
  }

  /**
   * プロパティ定義の配列を取得する。
   * 
   * @return プロパティ定義の配列
   */
  @Override
  public PropertyDefinition[] defineProperties() {
    return ArrayUtils.addAll(super.defineProperties(), definitions);
  }

  /**
   * プロパティ定義の配列
   */
  private static PropertyDefinition[] definitions;

  static {
    List<PropertyDefinition> list = new ArrayList<PropertyDefinition>();
    list.add(new PropertyDefinition(COOKIE_IDENTIFY_ID, String.class, false));
    list.add(new PropertyDefinition(MAKER_CODE, Integer.class, false));
    list.add(new PropertyDefinition(LOGIN_ID, String.class, false));
    list.add(new PropertyDefinition(COOKIE_EXPIRY_DATE, Timestamp.class, true));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
