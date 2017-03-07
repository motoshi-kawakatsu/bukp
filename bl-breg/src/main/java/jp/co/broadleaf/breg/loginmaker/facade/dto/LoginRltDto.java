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
package jp.co.broadleaf.breg.loginmaker.facade.dto;

import jp.co.broadleaf.breg.companysetting.dto.CompanySettingDto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 担当者でログイン用のクラス。
 */
public class LoginRltDto {

  /**
   * 担当者情報Dto
   */
  private UserMasterDto userMasterDto;

  /**
   * <pre>
   * 会社情報Dto.
   * </pre>
   */
  private CompanySettingDto companySettingDto;
  
  /**
   * パスワード一致チェック結果
   */
  private boolean pwdAgreementFlag;

  /**
   * パスワード有効期限チェック結果.
   */
  private boolean pwdConfirmFlag;

  /**
   * アカウントロック判定結果
   */
  private boolean accountLockFlag;

  /**
   * ログインCookie識別ID
   */
  private String loginCookieIDentifyId;
  
  /**
   * ログイン試行回数規定値
   */
  int loginAttemptCntSpecifiedValue;

  /**
   * 担当者情報Dtoの取得。
   *
   * @return UserMasterDto 担当者情報Dto
   */
  public UserMasterDto getUserMasterDto() {
    return userMasterDto;
  }
  
  /**
   * 会社情報Dtoの取得。
   *
   * @return companySettingDto　会社情報Dto
   */
  public CompanySettingDto getCompanySettingDto() {
    return companySettingDto;
  }

  /**
   * パスワード一致チェック結果の取得。
   *
   * @return boolean パスワード一致チェック結果
   */
  public boolean getPwdAgreementFlag() {
    return pwdAgreementFlag;
  }

  /**
   * パスワード有効期限チェック結果の取得。
   *
   * @return boolean パスワード有効期限チェック結果
   */
  public boolean getPwdConfirmFlag() {
    return pwdConfirmFlag;
  }

  /**
   * アカウントロック判定結果の取得。
   *
   * @return boolean アカウントロック判定結果
   */
  public boolean getAccountLockFlag() {
    return accountLockFlag;
  }

  /**
   * ログインCookie識別IDの取得。
   *
   * @return String ログインCookie識別ID
   */
  public String getLoginCookieIDentifyId() {
    return loginCookieIDentifyId;
  }
  
  /**
   * ログイン試行回数規定値の取得。
   *
   * @return int ログイン試行回数規定値
   */
  public int getLoginAttemptCntSpecifiedValue() {
    return loginAttemptCntSpecifiedValue;
  }

  /**
   * 担当者情報Dtoの設定。
   *
   * @param userMasterDto 担当者情報Dto
   */
  public void setUserMasterDto(UserMasterDto userMasterDto) {
    this.userMasterDto = userMasterDto;
  }
  
  /**
   * 会社情報Dtoの設定。
   *
   * @param companySettingDto 会社情報Dto
   */
  public void setCompanySettingDto(CompanySettingDto companySettingDto) {
    this.companySettingDto = companySettingDto;
  }

  /**
   * パスワード一致チェック結果の設定。
   *
   * @param pwdAgreementFlag パスワード一致チェック結果
   */
  public void setPwdAgreementFlag(boolean pwdAgreementFlag) {
    this.pwdAgreementFlag = pwdAgreementFlag;
  }

  /**
   * パスワード有効期限チェック結果の設定。
   *
   * @param pwdConfirmFlag パスワード有効期限チェック結果
   */
  public void setPwdConfirmFlag(boolean pwdConfirmFlag) {
    this.pwdConfirmFlag = pwdConfirmFlag;
  }

  /**
   * アカウントロック判定結果の設定。
   *
   * @param accountLockFlag アカウントロック判定結果
   */
  public void setAccountLockFlag(boolean accountLockFlag) {
    this.accountLockFlag = accountLockFlag;
  }

  /**
   * ログインCookie識別IDの設定。
   *
   * @param loginCookieIDentifyId ログインCookie識別ID
   */
  public void setLoginCookieIDentifyId(String loginCookieIDentifyId) {
    this.loginCookieIDentifyId = loginCookieIDentifyId;
  }
  
  /**
   * ログイン試行回数規定値の設定。
   *
   * @param loginAttemptCntSpecifiedValue ログイン試行回数規定値
   */
  public void setLoginAttemptCntSpecifiedValue(int loginAttemptCntSpecifiedValue) {
    this.loginAttemptCntSpecifiedValue = loginAttemptCntSpecifiedValue;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(userMasterDto).append(companySettingDto).append(pwdAgreementFlag).append(pwdConfirmFlag)
        .append(accountLockFlag).append(loginCookieIDentifyId).append(loginAttemptCntSpecifiedValue).toHashCode();
  }

  /**
   * equalsメソッド
   * 
   * @param obj オブジェクト
   * @return 比較結果
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    LoginRltDto other = (LoginRltDto) obj;
    return new EqualsBuilder().append(userMasterDto, other.userMasterDto)
        .append(companySettingDto, other.companySettingDto).append(pwdAgreementFlag, other.pwdAgreementFlag)
        .append(pwdConfirmFlag, other.pwdConfirmFlag).append(accountLockFlag, other.accountLockFlag)
        .append(loginCookieIDentifyId, other.loginCookieIDentifyId)
        .append(loginAttemptCntSpecifiedValue, other.loginAttemptCntSpecifiedValue)
        .isEquals();
  }

}
