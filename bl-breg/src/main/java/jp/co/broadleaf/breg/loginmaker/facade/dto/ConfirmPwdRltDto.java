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
 * <pre>
 * パスワード確認Dto.
 * </pre>
 */
public class ConfirmPwdRltDto {
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
   * <pre>
   * パスワード一致チェック結果.
   * </pre>
   */
  private boolean pwdAgreementFlag = false;
  
  /**
   * <pre>
   * パスワード有効期限チェック結果.
   * </pre>
   */
  private boolean pwdConfirmFlag = false;

  /**
   * <pre>
   * 【pwdAgreementFlag】を取得する。
   * </pre>
   *
   * @return 【pwdAgreementFlag】
   */
  public boolean getPwdAgreementFlag() {
    return pwdAgreementFlag;
  }

  /**
   * <pre>
   * 【pwdAgreementFlag】を設定する。
   * </pre>
   *
   * @param pwdAgreementFlag 【pwdAgreementFlag】
   */
  public void setPwdAgreementFlag(boolean pwdAgreementFlag) {
    this.pwdAgreementFlag = pwdAgreementFlag;
  }

  /**
   * <pre>
   * 【pwdConfirmFlag】を取得する。
   * </pre>
   *
   * @return 【pwdConfirmFlag】
   */
  public boolean getPwdConfirmFlag() {
    return pwdConfirmFlag;
  }

  /**
   * <pre>
   * 【pwdConfirmFlag】を設定する。
   * </pre>
   *
   * @param pwdConfirmFlag 【pwdConfirmFlag】
   */
  public void setPwdConfirmFlag(boolean pwdConfirmFlag) {
    this.pwdConfirmFlag = pwdConfirmFlag;
  }

  /**
   * <pre>
   * 【userMasterDto】を取得する。
   * </pre>
   *
   * @return 【userMasterDto】
   */
  public UserMasterDto getUserMasterDto() {
    return userMasterDto;
  }

  /**
   * <pre>
   * 【userMasterDto】を設定する。
   * </pre>
   *
   * @param userMasterDto 【userMasterDto】
   */
  public void setUserMasterDto(UserMasterDto userMasterDto) {
    this.userMasterDto = userMasterDto;
  }

  /**
   * <pre>
   * 【companySettingDto】を取得する。
   * </pre>
   *
   * @return 【companySettingDto】
   */
  public CompanySettingDto getCompanySettingDto() {
    return companySettingDto;
  }

  /**
   * <pre>
   * 【companySettingDto】を設定する。
   * </pre>
   *
   * @param companySettingDto 【companySettingDto】
   */
  public void setCompanySettingDto(CompanySettingDto companySettingDto) {
    this.companySettingDto = companySettingDto;
  }
  
  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(pwdAgreementFlag).append(pwdConfirmFlag).append(userMasterDto).append(companySettingDto)
        .toHashCode();
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
    ConfirmPwdRltDto other = (ConfirmPwdRltDto) obj;
    return new EqualsBuilder().append(pwdAgreementFlag, other.pwdAgreementFlag)
        .append(pwdConfirmFlag, other.pwdConfirmFlag).append(userMasterDto, other.userMasterDto)
        .append(companySettingDto, other.companySettingDto).isEquals();
  }
}
