//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : ツール自動作成
// 作 成 日       2017/01/16   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common;

import jp.co.broadleaf.common.property.PropertyKey;

/**
 * <pre>
 * プロパティキー定義クラス
 * </pre>
 */
public abstract class BregPropertyKeys {

  /**
   * <pre>
   * ログイン試行回数規定値
   * </pre>
   */
  public static final PropertyKey<Integer> BREG_ACCOUNT_LOGIN_ATTEMPT_COUNT_LIMIT = new PropertyKey<Integer>(
      "breg.account.login_attempt_count_limit", Integer.class);

  /**
   * <pre>
   * ログインCookie有効時間
   * </pre>
   */
  public static final PropertyKey<Integer> BREG_ACCOUNT_LOGIN_COOKIE_EXPIRY_PERIOD = new PropertyKey<Integer>(
      "breg.account.login_cookie_expiry_period", Integer.class);

  /**
   * <pre>
   * ログインCookie名称
   * </pre>
   */
  public static final PropertyKey<String> BREG_ACCOUNT_LOGIN_COOKIE_NAME = new PropertyKey<String>(
      "breg.account.login_cookie_name", String.class);

  /**
   * <pre>
   * ログイン画面のURL
   * </pre>
   */
  public static final PropertyKey<String> BREG_ACCOUNT_LOGIN_URL = new PropertyKey<String>("breg.account.login_url",
      String.class);

  /**
   * <pre>
   * PBEKeyのイテレーション回数
   * </pre>
   */
  public static final PropertyKey<Integer> BREG_ACCOUNT_PBEKEY_ITERATION_COUNT = new PropertyKey<Integer>(
      "breg.account.pbekey_iteration_count", Integer.class);

  /**
   * <pre>
   * デフォルトURL
   * </pre>
   */
  public static final PropertyKey<String> BREG_ACCOUNT_DEFAULT_URL = new PropertyKey<String>("breg.account.default_url",
      String.class);

  /**
   * <pre>
   * ログインCookieパス
   * </pre>
   */
  public static final PropertyKey<String> BREG_ACCOUNT_LOGIN_COOKIE_PATH = new PropertyKey<String>(
      "breg.account.login_cookie_path", String.class);

  /**
   * <pre>
   * ログインCookieセキュア属性
   * </pre>
   */
  public static final PropertyKey<String> BREG_ACCOUNT_LOGIN_COOKIE_SECURE = new PropertyKey<String>(
      "breg.account.login_cookie_secure", String.class);
  
  /**
   * <pre>
   * パスワード有効時間
   * </pre>
   */
  public static final PropertyKey<Integer> BREG_ACCOUNT_LOGIN_PASSWORD_EXPIRY_PERIOD = new PropertyKey<Integer>(
      "breg.account.login_password_expiry_period", Integer.class);

}
