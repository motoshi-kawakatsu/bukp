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
 * 認証情報
 */
@Entity(name = "t_certif_info_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class CertifInfoCommon extends BroadleafDomainEntity<CertifInfoCommonId> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * メーカーコード
   */
  public static final String MAKER_CODE = "makerCode";

  /**
   * ログインID
   */
  public static final String LOGIN_ID = "loginId";

  /**
   * パスワード変換キー
   */
  public static final String PWD_CONV_KEY = "pwdConvKey";

  /**
   * ログインパスワード
   */
  public static final String LOGIN_PWD = "loginPwd";

  /**
   * ログイン試行回数
   */
  public static final String LOGIN_ATTEMPT_CNT = "loginAttemptCnt";

  /**
   * パスワード有効期限
   */
  public static final String PWD_EXPIRY_DT = "pwdExpiryDt";

  /**
   * コンストラクタ。
   */
  public CertifInfoCommon() {

  }

  /**
   * コンストラクタ。
   * 
   * @param id ID
   */
  public CertifInfoCommon(CertifInfoCommonId id) {
    setMakerCode(id.getMakerCode());
    setLoginId(id.getLoginId());
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
   * パスワード変換キーを返します。
   * 
   * @return パスワード変換キー
   */
  public String getPwdConvKey() {
    return container().getPropertyValue(PWD_CONV_KEY, String.class);
  }

  /**
   * パスワード変換キーを設定します。
   * 
   * @param pwdConvKey セットするパスワード変換キー
   */
  public void setPwdConvKey(String pwdConvKey) {
    container().setPropertyValue(PWD_CONV_KEY, pwdConvKey);
  }

  /**
   * ログインパスワードを返します。
   * 
   * @return ログインパスワード
   */
  public String getLoginPwd() {
    return container().getPropertyValue(LOGIN_PWD, String.class);
  }

  /**
   * ログインパスワードを設定します。
   * 
   * @param loginPwd セットするログインパスワード
   */
  public void setLoginPwd(String loginPwd) {
    container().setPropertyValue(LOGIN_PWD, loginPwd);
  }

  /**
   * ログイン試行回数を返します。
   * 
   * @return ログイン試行回数
   */
  public int getLoginAttemptCnt() {
    return container().getPropertyValue(LOGIN_ATTEMPT_CNT, Integer.class);
  }

  /**
   * ログイン試行回数を設定します。
   * 
   * @param loginAttemptCnt セットするログイン試行回数
   */
  public void setLoginAttemptCnt(int loginAttemptCnt) {
    container().setPropertyValue(LOGIN_ATTEMPT_CNT, loginAttemptCnt);
  }

  /**
   * パスワード有効期限を返します。
   * 
   * @return パスワード有効期限
   */
  public Timestamp getPwdExpiryDt() {
    return container().getPropertyValue(PWD_EXPIRY_DT, Timestamp.class);
  }

  /**
   * パスワード有効期限を設定します。
   * 
   * @param pwdExpiryDt セットするパスワード有効期限
   */
  public void setPwdExpiryDt(Timestamp pwdExpiryDt) {
    container().setPropertyValue(PWD_EXPIRY_DT, pwdExpiryDt);
  }

  /**
   * エンティティIDを取得する。
   * 
   * @return エンティティID
   */
  @Override
  public CertifInfoCommonId identifier() {
    return new CertifInfoCommonId(getMakerCode(), getLoginId());
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
    list.add(new PropertyDefinition(MAKER_CODE, Integer.class, false));
    list.add(new PropertyDefinition(LOGIN_ID, String.class, false));
    list.add(new PropertyDefinition(PWD_CONV_KEY, String.class, false));
    list.add(new PropertyDefinition(LOGIN_PWD, String.class, false));
    list.add(new PropertyDefinition(LOGIN_ATTEMPT_CNT, Integer.class, false));
    list.add(new PropertyDefinition(PWD_EXPIRY_DT, Timestamp.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
