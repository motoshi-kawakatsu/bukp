//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:曹　緩緩
// 作成日:2017/02/08    修正内容:パスワード忘れ：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.loginmaker.form;

import jp.co.broadleaf.common.validation.LoginIdValidatorRule;
import jp.co.broadleaf.framework.validation.FormValidator;
import jp.co.broadleaf.framework.validation.FormValidatorFactory;
import jp.co.broadleaf.breg.common.BregMessageCodes;

/**
 * <pre>
 * パスワード忘れリマインダFormのクラス。
 * </pre>
 */
public class ForgetPwdReminderForm {

  /**
   * メーカーコード
   */
  private String makerCd;

  /**
   * ログインID
   */
  private String loginId;

  /**
   * メーカーコードの取得。
   *
   * @return String メーカーコード
   */
  public String getMakerCd() {
    return makerCd;
  }

  /**
   * <pre>
   * ログインIDを取得する。
   * </pre>
   *
   * @return ログインID
   */
  public String getLoginId() {
    return loginId;
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
   * <pre>
   * ログインIDを設定する。
   * </pre>
   *
   * @param loginId ログインID
   */
  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  /**
   * <pre>
   * 画面入力チェックを実行する。
   * </pre>
   */
  public void validate() {
    // 画面入力チェック
    FormValidator validator = FormValidatorFactory.create();
    validator.field("makerCd", makerCd).required(BregMessageCodes.E00101, "メーカーコード").maxLength(4,
        BregMessageCodes.E00104, "メーカーコード", "4");
    validator.field("loginId", loginId).required(BregMessageCodes.E00101, "ログインID")
        .match(new LoginIdValidatorRule(), BregMessageCodes.E00103, "ログインID")
        .maxLength(24, BregMessageCodes.E00104, "ログインID", "24");
    validator.validate();
    // チェック処理を行う
    validator.validate();
  }
}
