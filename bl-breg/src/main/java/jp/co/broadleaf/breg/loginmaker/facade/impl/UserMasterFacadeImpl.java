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
import jp.co.broadleaf.breg.loginmaker.facade.UserMasterFacade;
import jp.co.broadleaf.breg.loginmaker.facade.UserMasterPwdFacade;
import jp.co.broadleaf.breg.loginmaker.facade.dto.ConfirmPwdRltDto;
import jp.co.broadleaf.breg.loginmaker.facade.dto.LoginRltDto;
import jp.co.broadleaf.breg.loginmaker.facade.dto.UserMasterDto;
import jp.co.broadleaf.breg.loginmaker.service.UserMasterPwdService;
import jp.co.broadleaf.breg.loginmaker.service.UserMasterService;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.BregPropertyKeys;
import jp.co.broadleaf.breg.common.entity.CertifInfoCommon;
import jp.co.broadleaf.breg.common.entity.UserMasterCommon;
import jp.co.broadleaf.breg.common.enums.MailDivEnum;
import jp.co.broadleaf.breg.common.service.BizEmailService;
import jp.co.broadleaf.breg.loginmaker.enums.LoginLockDivEnum;
import jp.co.broadleaf.common.property.PropertyResolver;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.framework.exception.BusinessException;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

/**
 * <pre>
 * 担当者情報Facadeクラス.
 * </pre>
 */
public class UserMasterFacadeImpl implements UserMasterFacade {

  /**
   * <pre>
   * 担当者ロック判定.
   * </pre>
   * 
   * @param userMasterDto 担当者情報
   * @param loginAttemptCntSpecifiedValue ログイン試行回数規定値
   * @param blMailAdr Bl側連絡メールアドレス
   * @return 「true」:ログイン試行回数が規定値を超えている場合 「false」:ログイン試行回数が規定値を超えていない場合
   */
  @Override
  public boolean isUserMasterLock(UserMasterDto userMasterDto, int loginAttemptCntSpecifiedValue, String blMailAdr) {
    Validate.notNull(userMasterDto, "userMasterDto must not be null");
    // メーカーコード
    Validate.notNull(userMasterDto.getMakerCode(), "makerCode must not be null");
    // ログインID
    Validate.notEmpty(userMasterDto.getLoginId(), "loginId must not be empty");
    // メールアドレス
    Validate.notEmpty(userMasterDto.getMailAdd(), "emailAddr must not be empty");
    // ログインロック区分
    Validate.notNull(userMasterDto.getLoginLockDiv(), "loginLockDiv must not be null");
    // ユーザー管理者フラグ
    Validate.notNull(userMasterDto.getUserAdminFlag(), "userAdminFlag must not be null");
    // ユーザー種別
    Validate.notNull(userMasterDto.getUserKind(), "userKind must not be null");
    // ログインロック区分
    Validate.isTrue(LoginLockDivEnum.valueOf(userMasterDto.getLoginLockDiv()) != null,
        "loginLockDiv must be effective");

    // ログイン試行回数更新
    CertifInfoCommon certifInfo = userMasterPwdService.updateLoginAttemptCnt(userMasterDto.getMakerCode(),
        userMasterDto.getLoginId());
    if (null == certifInfo) {
      throw new BusinessException(BregMessageCodes.E90001, "認証情報の更新");
    }

    // ログイン試行回数チェック
    boolean checkLoginAttemptCountResult = userMasterPwdService.checkLoginAttemptCnt(certifInfo.getLoginAttemptCnt(),
        loginAttemptCntSpecifiedValue);
    if (checkLoginAttemptCountResult) {
      return false;
    } else {
      // 更新処理
      UserMasterCommon updateUserMasterInfo = new UserMasterCommon();
      // UUID
      updateUserMasterInfo.setUuid(userMasterDto.getUuid());
      // 作成日時
      updateUserMasterInfo.setInsDtTime(new Timestamp(userMasterDto.getInsDtTime().getTime()));
      // 更新日時
      updateUserMasterInfo.setUpdDtTime(new Timestamp(BroadleafUtils.getSystemDtTime().getTime()));
      // 作成アカウントID
      updateUserMasterInfo.setInsAccountId(userMasterDto.getInsAccountId());
      // 更新アカウントID
      updateUserMasterInfo.setUpdAccountId(userMasterDto.getUpdAccountId());
      // 論理削除区分
      updateUserMasterInfo.setLogicalDelDiv(userMasterDto.getLogicalDelDiv());
      // メーカーコード
      updateUserMasterInfo.setMakerCode(userMasterDto.getMakerCode());
      // ログインID
      updateUserMasterInfo.setLoginId(userMasterDto.getLoginId());
      // 担当者名称
      updateUserMasterInfo.setUserName(userMasterDto.getUserName());
      // 担当者名称（カナ）
      updateUserMasterInfo.setUserKana(userMasterDto.getUserKana());
      // 部署
      updateUserMasterInfo.setDepartmentName(userMasterDto.getDepartmentName());
      // 担当品目
      updateUserMasterInfo.setItem(userMasterDto.getItem());
      // 郵便番号
      updateUserMasterInfo.setPostCode(userMasterDto.getPostCode());
      // 住所
      updateUserMasterInfo.setAddress(userMasterDto.getAddress());
      // メールアドレス
      updateUserMasterInfo.setMailAdd(userMasterDto.getMailAdd());
      // 電話番号TEL
      updateUserMasterInfo.setTelNo(userMasterDto.getTelNo());
      // FAX
      updateUserMasterInfo.setFaxNo(userMasterDto.getFaxNo());
      // ユーザー管理者フラグ
      updateUserMasterInfo.setUserAdminFlag(userMasterDto.getUserAdminFlag());
      // ユーザー種別
      updateUserMasterInfo.setUserKind(userMasterDto.getUserKind());
      // 備考
      updateUserMasterInfo.setNote(userMasterDto.getNote());
      // ログインロック状態更新
      updateUserMasterInfo = userMasterService.updateLoginLockState(updateUserMasterInfo, LoginLockDivEnum.LoginUnable);
      // 担当者情報更新
      userMasterService.updateUserMaster(updateUserMasterInfo);

      // BLへメール本文可変文言リスト
      List<String> blMailBodyChangeableTextList = new ArrayList<String>();
      blMailBodyChangeableTextList.add("ブロードリーフ　担当者"); // BL様名称
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String dataTime = format.format(BroadleafUtils.getSystemDtTime());
      blMailBodyChangeableTextList.add(dataTime);
      blMailBodyChangeableTextList.add(String.valueOf(loginAttemptCntSpecifiedValue));
      blMailBodyChangeableTextList.add(String.valueOf(userMasterDto.getMakerCode()));
      blMailBodyChangeableTextList.add(userMasterDto.getLoginId());
      
      // BLへメール送信
      boolean sendEmailToBlRtn = bizEmailService.sendBizEmail(MailDivEnum.LoginAttemptCntNotification, blMailAdr,
          new ArrayList<String>(), new ArrayList<String>(), blMailBodyChangeableTextList);
      if (!sendEmailToBlRtn) {
        throw new BusinessException(BregMessageCodes.E90002);
      }
      return true;
    }
  }

  /**
   * <pre>
   * 担当者でログインする.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param password パスワード
   * @param ipAddress IPアドレス
   * @param cookieSend ログイン状態を維持
   * @return ログイン結果
   */
  @Override
  public LoginRltDto loginUserMaster(int makerCode, String loginId, String password, String ipAddress,
                                     String cookieSend) {
    // 必須入力チェック処理
    checkRequired(makerCode, loginId, password, ipAddress);
    // ログイン結果
    LoginRltDto result = new LoginRltDto();

    // ログイン試行回数規定値取得
    int loginAttemptCntSpecifiedValue = propertyResolver
        .getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_ATTEMPT_COUNT_LIMIT);

    // パスワード確認
    ConfirmPwdRltDto confirmPwdRltDto = userMasterPwdFacade.confirmUserMasterPwd(makerCode, loginId, password,
        loginAttemptCntSpecifiedValue);
    result.setUserMasterDto(confirmPwdRltDto.getUserMasterDto());
    result.setCompanySettingDto(confirmPwdRltDto.getCompanySettingDto());
    result.setPwdAgreementFlag(confirmPwdRltDto.getPwdAgreementFlag());
    result.setPwdConfirmFlag(confirmPwdRltDto.getPwdConfirmFlag());

    // パスワード一致チェック結果&&パスワード有効期限チェック結果
    if (confirmPwdRltDto.getPwdAgreementFlag()) {
      if (confirmPwdRltDto.getPwdConfirmFlag()) {
        // パスワード確認Facadeの実行結果（パスワード一致チェック結果）がTrueの場合
        // ログインCookie登録
        Date systemDtTime = BroadleafUtils.getSystemDtTime();
        String loginCookie = loginFacade.addLoginCookie(confirmPwdRltDto.getUserMasterDto().getMakerCode(),
            confirmPwdRltDto.getUserMasterDto().getLoginId(), new Timestamp(systemDtTime.getTime()), cookieSend);
        result.setLoginCookieIDentifyId(loginCookie);
      }

    } else {
      result.setLoginAttemptCntSpecifiedValue(loginAttemptCntSpecifiedValue);
      // 担当者ロック判定
      result.setAccountLockFlag(isUserMasterLock(confirmPwdRltDto.getUserMasterDto(), loginAttemptCntSpecifiedValue,
          confirmPwdRltDto.getCompanySettingDto().getBlMailAdd()));
    }
    return result;
  }

  /**
   * <pre>
   * 担当者でログイン必須入力チェック処理
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param password パスワード
   * @param ipAddress IPアドレス
   */
  private void checkRequired(int makerCode, String loginId, String password, String ipAddress) {
    // 引数.メーカーコードの必須入力チェック
    Validate.notNull(makerCode, "makerCode must not be null");
    // 引数.ログインIDの必須入力チェック
    Validate.notEmpty(loginId, "loginId must not be empty");
    // 引数.パスワードの必須入力チェック
    Validate.notEmpty(password, "password must not be empty");
    // 引数.IPアドレスの必須入力チェック
    Validate.notEmpty(ipAddress, "ipAddress must not be empty");
  }

  /** 担当者パスワードFacade */
  private UserMasterPwdFacade userMasterPwdFacade;

  /**
   * <pre>
   * 担当者パスワード
   * </pre>
   * 
   * @param userMasterPwdFacade 担当者パスワードFacade
   */
  @Resource
  public void setUserMasterPwdFacade(UserMasterPwdFacade userMasterPwdFacade) {
    this.userMasterPwdFacade = userMasterPwdFacade;
  }

  /** ログインFacade */
  private LoginFacade loginFacade;

  /**
   * <pre>
   * ログインFacade
   * </pre>
   * 
   * @param loginFacade ログインサービス
   */
  @Resource
  public void setLoginFacade(LoginFacade loginFacade) {
    this.loginFacade = loginFacade;
  }

  /** 担当者情報サービス */
  private UserMasterService userMasterService;

  /**
   * <pre>
   * 担当者情報
   * </pre>
   * 
   * @param userMasterService 担当者情報サービス
   */
  @Resource
  public void setUserMasterService(UserMasterService userMasterService) {
    this.userMasterService = userMasterService;
  }
  
  /** 担当者パスワードサービス */
  private UserMasterPwdService userMasterPwdService;

  /**
   * <pre>
   * 担当者パスワード.
   * </pre>
   * 
   * @param userMasterPwdService 担当者パスワードサービス
   */
  @Resource
  public void setUserMasterPwdService(UserMasterPwdService userMasterPwdService) {
    this.userMasterPwdService = userMasterPwdService;
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

  /** 業務メール送信 */
  private BizEmailService bizEmailService;

  /**
   * <pre>
   * 業務メール送信を設定する。
   * </pre>
   *
   * @param bizEmailService 業務メール送信
   */
  @Resource
  public void setBizEmailService(BizEmailService bizEmailService) {
    this.bizEmailService = bizEmailService;
  }
}
