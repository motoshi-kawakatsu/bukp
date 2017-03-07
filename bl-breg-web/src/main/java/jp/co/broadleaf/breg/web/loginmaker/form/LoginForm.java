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
package jp.co.broadleaf.breg.web.loginmaker.form;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.common.validation.LoginIdValidatorRule;
import jp.co.broadleaf.framework.validation.FormValidator;
import jp.co.broadleaf.framework.validation.FormValidatorFactory;

/**
 * ログインFormのクラスのクラス。
 */
public class LoginForm {

  /**
   * メーカーコード
   */
  private String makerCd;

  /**
   * ログインID
   */
  private String loginId;

  /**
   * パスワード
   */
  private String password;

  /**
   * ログイン状態を維持
   */
  private String cookieSend;

  /**
   * メーカーコードの取得。
   *
   * @return String メーカーコード
   */
  public String getMakerCd() {
    return makerCd;
  }

  /**
   * ログインIDの取得。
   *
   * @return String ログインID
   */
  public String getLoginId() {
    return loginId;
  }

  /**
   * パスワードの取得。
   *
   * @return String パスワード
   */
  public String getPassword() {
    return password;
  }

  /**
   * ログイン状態を維持の取得。
   *
   * @return String ログイン状態を維持
   */
  public String getCookieSend() {
    return cookieSend;
  }

  /**
   * メーカーコードの設定。
   *
   * @param makerCd メーカーコード
   */
  public void setMakerCd(String makerCd) {
    this.makerCd = makerCd;
  }

  /**
   * ログインIDの設定。
   *
   * @param loginId ログインID
   */
  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  /**
   * パスワードの設定。
   *
   * @param password パスワード
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * ログイン状態を維持の設定。
   *
   * @param cookieSend ログイン状態を維持
   */
  public void setCookieSend(String cookieSend) {
    this.cookieSend = cookieSend;
  }

  /**
   * <pre>
   * 画面入力チェックを実行する。
   * </pre>
   */
  public void validate() {
    // 画面入力チェック
    FormValidator validator = FormValidatorFactory.create();
    // メーカーコードのチェック
    validator.field("makerCd", makerCd).required(BregMessageCodes.E00101, "メーカーコード").maxLength(4,
        BregMessageCodes.E00104, "メーカーコード", "4");
    // ログインIDのチェック
    validator.field("loginId", loginId).required(BregMessageCodes.E00101, "ログインID")
        .match(new LoginIdValidatorRule(), BregMessageCodes.E00103, "ログインID")
        .maxLength(24, BregMessageCodes.E00104, "ログインID", "24");
    // パスワードのチェック
    validator.field("password", password).required(BregMessageCodes.E00101, "パスワード");
    validator.validate();
  }

}
