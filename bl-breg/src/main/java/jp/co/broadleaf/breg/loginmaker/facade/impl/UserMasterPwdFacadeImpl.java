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

import jp.co.broadleaf.breg.loginmaker.facade.UserMasterPwdFacade;
import jp.co.broadleaf.breg.loginmaker.facade.dto.ConfirmPwdRltDto;
import jp.co.broadleaf.breg.loginmaker.facade.dto.UserMasterDto;
import jp.co.broadleaf.breg.loginmaker.service.UserMasterPwdService;
import jp.co.broadleaf.breg.loginmaker.service.UserMasterService;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.entity.CertifInfoCommon;
import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;
import jp.co.broadleaf.breg.common.entity.UserMasterCommon;
import jp.co.broadleaf.breg.companysetting.dto.CompanySettingDto;
import jp.co.broadleaf.breg.companysetting.service.CompanyInfoMasterCommonService;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.framework.exception.BusinessException;
import org.apache.commons.lang3.Validate;
import javax.annotation.Resource;

/**
 * <pre>
 * 担当者パスワードFacadeクラス.
 * </pre>
 */
public class UserMasterPwdFacadeImpl implements UserMasterPwdFacade {

  /**
   * <pre>
   * パスワード確認.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param password パスワード
   * @param loginAttemptCntSpecifiedValue ログイン試行回数規定値
   * @return パスワード確認Dto
   */
  @Override
  public ConfirmPwdRltDto confirmUserMasterPwd(int makerCode, String loginId, String password,
                                               int loginAttemptCntSpecifiedValue) {

    // 引数.メーカーコードの必須入力チェック
    Validate.notNull(makerCode, "makerCode must not be null");
    // 引数.ログインIDの必須入力チェック
    Validate.notEmpty(loginId, "loginId must not be empty");
    // 引数.パスワードの必須入力チェック
    Validate.notEmpty(password, "password must not be empty");
    // パスワード確認結果
    ConfirmPwdRltDto result = new ConfirmPwdRltDto();
    // 担当者情報取得（メーカーコードとログインID）
    UserMasterCommon userMaster = userMasterService.getUserMaster(makerCode, loginId);

    if (userMaster == null) {
      throw new BusinessException(BregMessageCodes.E00105, "アカウント");
    }

    // 会社情報取得
    CompanyInfoMasterCommon companyInfoMaster = companyInfoMasterCommonService.getCompanyInfo(userMaster.getMakerCode());
    if (companyInfoMaster == null) {
      // 会社情報取得結果がNullの場合
      throw new BusinessException(BregMessageCodes.E00105, "会社情報");
    }

    // EntityからDtoへの転換.
    result.setUserMasterDto(convertUserMasterToDto(userMaster));
    result.setCompanySettingDto(convertCompanyInfoMasterToDto(companyInfoMaster));
    // 担当者ロックチェック
    boolean isAccountLock = userMasterService.checkUserMasterLock(userMaster);
    if (isAccountLock) {
      throw new BusinessException(BregMessageCodes.E00106, String.valueOf(loginAttemptCntSpecifiedValue));
    }

    // 認証情報の取得
    CertifInfoCommon res = userMasterPwdService.getCertifInfo(userMaster.getMakerCode(), userMaster.getLoginId());
    // 取得できなかった場合、戻り値にFALSEを設定して返却する
    if (res == null) {
      return result;
    }

    // パスワード一致チェック
    Boolean isAgreementFlag = userMasterPwdService.checkPwdAccord(res.getPwdConvKey(), res.getLoginPwd(), password);
    result.setPwdAgreementFlag(isAgreementFlag);

    // パスワード有効期限チェック
    Boolean isPwdConfirmFlag = userMasterPwdService.checkPwdExpiryDt(res.getPwdExpiryDt(),
        BroadleafUtils.getSystemDtTime());
    result.setPwdConfirmFlag(isPwdConfirmFlag);

    if (isAgreementFlag && isPwdConfirmFlag && res.getLoginAttemptCnt() != 0) {
      // ログイン回数リセット
      userMasterPwdService.resetLoginCnt(userMaster.getMakerCode(), userMaster.getLoginId());
    }
    return result;
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
