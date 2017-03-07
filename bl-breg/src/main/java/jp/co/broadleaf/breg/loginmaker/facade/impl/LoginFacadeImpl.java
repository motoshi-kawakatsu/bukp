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
package jp.co.broadleaf.breg.loginmaker.facade.impl;

import jp.co.broadleaf.breg.loginmaker.facade.LoginFacade;
import jp.co.broadleaf.breg.loginmaker.facade.dto.LoginCertifConfirmDto;
import jp.co.broadleaf.breg.loginmaker.facade.dto.UserMasterDto;
import jp.co.broadleaf.breg.loginmaker.library.ExpiryDtCalc;
import jp.co.broadleaf.breg.loginmaker.service.LoginService;
import jp.co.broadleaf.breg.loginmaker.service.UserMasterService;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.BregPropertyKeys;
import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;
import jp.co.broadleaf.breg.common.entity.LoginCookieMaker;
import jp.co.broadleaf.breg.common.entity.UserMasterCommon;
import jp.co.broadleaf.breg.companysetting.dto.CompanySettingDto;
import jp.co.broadleaf.breg.companysetting.service.CompanyInfoMasterCommonService;
import jp.co.broadleaf.common.property.PropertyResolver;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.framework.exception.BusinessException;

import org.apache.commons.lang3.Validate;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;

/**
 * <pre>
 * ログインFacadeクラス.
 * </pre>
 */
public class LoginFacadeImpl implements LoginFacade {

  /**
   * <pre>
   * ログインCookie登録.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param cookieExpiryDt Cookie有効期限
   * @param cookieSend ログイン状態を維持
   * @return Cookie識別ID
   */
  @Override
  public String addLoginCookie(int makerCode, String loginId, Timestamp cookieExpiryDt, String cookieSend) {

    Validate.notNull(makerCode, "makerCode must not be null");
    Validate.notNull(loginId, "loginId must not be null");

    // プロパティファイルからCookie有効時間が取得
    int cookieExpiryTime = propertyResolver.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_COOKIE_EXPIRY_PERIOD);

    // ログインCookie削除
    loginService.deleteLoginCookie(makerCode, loginId);
    // システム日時
    Date time = BroadleafUtils.getSystemDtTime();
    // ログインCookie識別ID発行
    String cookieIdentifyId = loginService.generateCookieIdentifyId(makerCode, loginId, time);
    // 有効期限計算
    if ("true".equals(cookieSend)) {
      Date cookieExpiryDate = ExpiryDtCalc.calcExpiryDt(time, cookieExpiryTime);
      cookieExpiryDt = new Timestamp(cookieExpiryDate.getTime());
    }

    // ログインCookie登録
    loginService.addLoginCookie(makerCode, loginId, cookieIdentifyId, cookieExpiryDt);

    return cookieIdentifyId;
  }

  /**
   * <pre>
   * ログイン認証確認.
   * </pre>
   * 
   * @param cookieIdentifyId Cookie識別ID
   * @param cookieSend ログイン状態を維持
   * @return ログイン認証確認Dto.
   */
  @Override
  public LoginCertifConfirmDto confirmLoginCertif(String cookieIdentifyId, String cookieSend) {

    // Cookie識別IDの必須入力チェック
    Validate.notEmpty(cookieIdentifyId, "cookieIdentifyId must not be empty");
    // 現在日時取得
    Date systemDtTime = BroadleafUtils.getSystemDtTime();
    
    // ログインCookie確認
    LoginCookieMaker loginCookie = loginService.confirmLoginCookie(cookieIdentifyId);
    if (null == loginCookie) {
      throw new BusinessException(BregMessageCodes.W00101, "ログインCookie");
    }
    
    if ("true".equals(cookieSend)) {
      // Cookie有効確認
      boolean isCookieconfirmFlag = loginService.checkCookieExpiryDt(loginCookie.getCookieExpiryDate(), systemDtTime);
      if (!isCookieconfirmFlag) {
        throw new BusinessException(BregMessageCodes.W00102, "ログインCookie");
      }
    }
    
    // 担当者情報取得
    UserMasterCommon userMaster = userMasterService.getUserMaster(loginCookie.getMakerCode(), loginCookie.getLoginId());
    if (userMaster == null) {
      // 担当者情報取得結果がNullの場合
      throw new BusinessException(BregMessageCodes.E00105, "アカウント");
    }
    
    // 会社情報取得
    CompanyInfoMasterCommon companyInfoMaster = companyInfoMasterCommonService.getCompanyInfo(loginCookie.getMakerCode());
    if (companyInfoMaster == null) {
      // 会社情報取得結果がNullの場合
      throw new BusinessException(BregMessageCodes.E00105, "会社情報");
    }
    
    // ログイン認証確認Dto
    LoginCertifConfirmDto loginCertifConfirmDto = new LoginCertifConfirmDto();
    loginCertifConfirmDto.setUserMasterDto(convertUserMasterToDto(userMaster));
    loginCertifConfirmDto.setCompanySettingDto(convertCompanyInfoMasterToDto(companyInfoMaster));
    loginCertifConfirmDto.setCookieExpiryDt(loginCookie.getCookieExpiryDate());

    return loginCertifConfirmDto;

  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param userMaster 担当者情報Entity
   * @return 担当者情報Dto
   */
  private UserMasterDto convertUserMasterToDto(UserMasterCommon userMaster) {
    UserMasterDto userMasterDto = new UserMasterDto();

    // UUID
    userMasterDto.setUuid(userMaster.getUuid());
    // 作成日時
    userMasterDto.setInsDtTime(userMaster.getInsDtTime());
    // 更新日時
    userMasterDto.setUpdDtTime(userMaster.getUpdDtTime());
    // 作成アカウントID
    userMasterDto.setInsAccountId(userMaster.getInsAccountId());
    // 更新アカウントID
    userMasterDto.setUpdAccountId(userMaster.getUpdAccountId());
    // 論理削除区分
    userMasterDto.setLogicalDelDiv(userMaster.getLogicalDelDiv());
    // メーカーコード
    userMasterDto.setMakerCode(userMaster.getMakerCode());
    // ログインID
    userMasterDto.setLoginId(userMaster.getLoginId());
    // 担当者名称
    userMasterDto.setUserName(userMaster.getUserName());
    // 担当者名称（カナ）
    userMasterDto.setUserKana(userMaster.getUserKana());
    // 部署
    userMasterDto.setDepartmentName(userMaster.getDepartmentName());
    // 担当品目
    userMasterDto.setItem(userMaster.getItem());
    // 郵便番号
    userMasterDto.setPostCode(userMaster.getPostCode());
    // 住所
    userMasterDto.setAddress(userMaster.getAddress());
    // メールアドレス
    userMasterDto.setMailAdd(userMaster.getMailAdd());
    // 電話番号TEL
    userMasterDto.setTelNo(userMaster.getTelNo());
    // FAX
    userMasterDto.setFaxNo(userMaster.getFaxNo());
    // ユーザー管理者フラグ
    userMasterDto.setUserAdminFlag(userMaster.getUserAdminFlag());
    // ユーザー種別
    userMasterDto.setUserKind(userMaster.getUserKind());
    // 備考
    userMasterDto.setNote(userMaster.getNote());
    // ログインロック区分
    userMasterDto.setLoginLockDiv(userMaster.getLoginLockDiv());
    return userMasterDto;
  }
  
  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param companyInfoMaster 会社情報Entity
   * @return 会社情報Dto
   */
  private CompanySettingDto convertCompanyInfoMasterToDto(CompanyInfoMasterCommon companyInfoMaster) {
    CompanySettingDto companySettingDto = new CompanySettingDto();

    // 更新ID
    companySettingDto.setUpdAccountId(companyInfoMaster.getUpdAccountId());
    // 更新日時
    companySettingDto.setUpdDtTime(companyInfoMaster.getUpdDtTime());
    //メーカーコード
    companySettingDto.setMakerCd(companyInfoMaster.getMakerCode());
    //会社名称
    companySettingDto.setCompanyName(companyInfoMaster.getCompanyName());
    //メーカー名称
    companySettingDto.setMakerCdName(companyInfoMaster.getMakerName());
    //メーカー名称（半角）
    companySettingDto.setMakerCdNameShort(companyInfoMaster.getMakerKana());
    //会社名称(カナ)
    companySettingDto.setCompanyNameKana(companyInfoMaster.getCompanyKana());
    //TEL
    companySettingDto.setTel(companyInfoMaster.getTelNo());
    //郵便番号
    companySettingDto.setMailNo(companyInfoMaster.getPostCode());
    //住所
    companySettingDto.setAddress(companyInfoMaster.getAddress());
    //FAX
    companySettingDto.setFax(companyInfoMaster.getFaxNo());
    //備考
    companySettingDto.setNotes(companyInfoMaster.getNote());
    //商品ページ内行数
    companySettingDto.setGoodsRows(companyInfoMaster.getGoodsRows());
    //セットページ内行数
    companySettingDto.setSetRows(companyInfoMaster.getSetRows());
    //結合ページ内行数
    companySettingDto.setJoinRows(companyInfoMaster.getJoinRows());
    //申請履歴ページ内行数
    companySettingDto.setApplyRecordRows(companyInfoMaster.getApplyHistoryRows());
    //申請詳細ページ内行数
    companySettingDto.setApplyDetailRows(companyInfoMaster.getApplyDetailRows());
    //商品インポート方法
    companySettingDto.setGoodsImportType(companyInfoMaster.getGoodsImportType());
    //セットインポート方法
    companySettingDto.setSetImportType(companyInfoMaster.getSetImportType());
    //結合インポート方法
    companySettingDto.setJoinImportType(companyInfoMaster.getJoinImportType());
    //Bl側連絡メールアドレス
    companySettingDto.setBlMailAdd(companyInfoMaster.getBlMailAdd());
    return companySettingDto;
  }

  /** ログインCookieサービス */
  private LoginService loginService;

  /**
   * <pre>
   * ログインCookie.
   * </pre>
   * 
   * @param loginService ログインCookieサービス
   */
  @Resource
  public void setLoginService(LoginService loginService) {
    this.loginService = loginService;
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

  /** 担当者情報サービス */
  private UserMasterService userMasterService;

  /**
   * <pre>
   * 担当者情報.
   * </pre>
   * 
   * @param userMasterService 担当者情報サービス
   */
  @Resource
  public void setUserMasterService(UserMasterService userMasterService) {
    this.userMasterService = userMasterService;
  }
  
  /** 会社情報情報サービス */
  private CompanyInfoMasterCommonService companyInfoMasterCommonService;

  /**
   * <pre>
   * 会社情報情報.
   * </pre>
   * 
   * @param companyInfoMasterCommonService 会社情報情報サービス
   */
  @Resource
  public void setCompanyInfoMasterCommonService(CompanyInfoMasterCommonService companyInfoMasterCommonService) {
    this.companyInfoMasterCommonService = companyInfoMasterCommonService;
  }

}
