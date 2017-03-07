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

import java.sql.Timestamp;

/**
 * <pre>
 * ログイン認証確認Dto.
 * </pre>
 */
public class LoginCertifConfirmDto {

  /**
   * <pre>
   * 担当者情報Dto.
   * </pre>
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
   * Cookie有効期限.
   * </pre>
   */
  private Timestamp cookieExpiryDt;

  /**
   * <pre>
   * 【cookieExpiryDt】を取得する。
   * </pre>
   *
   * @return 【cookieExpiryDt】
   */
  public Timestamp getCookieExpiryDt() {
    return cookieExpiryDt;
  }

  /**
   * <pre>
   * 【cookieExpiryDt】を設定する。
   * </pre>
   *
   * @param cookieExpiryDt 【cookieExpiryDt】
   */
  public void setCookieExpiryDt(Timestamp cookieExpiryDt) {
    this.cookieExpiryDt = cookieExpiryDt;
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
    return new HashCodeBuilder(17, 37).append(cookieExpiryDt).append(userMasterDto).append(companySettingDto)
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
    LoginCertifConfirmDto other = (LoginCertifConfirmDto) obj;
    return new EqualsBuilder().append(cookieExpiryDt, other.cookieExpiryDt).append(userMasterDto, other.userMasterDto)
        .append(companySettingDto, other.companySettingDto).isEquals();
  }
}
