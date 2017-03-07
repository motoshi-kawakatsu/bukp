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
package jp.co.broadleaf.breg.loginmaker.facade.impl;

import jp.co.broadleaf.common.property.PropertyResolver;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.framework.exception.BusinessException;
import jp.co.broadleaf.breg.loginmaker.facade.ReminderReceptFacade;
import jp.co.broadleaf.breg.loginmaker.service.UserMasterPwdService;
import jp.co.broadleaf.breg.loginmaker.service.UserMasterService;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.BregPropertyKeys;
import jp.co.broadleaf.breg.common.entity.CertifInfoCommon;
import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;
import jp.co.broadleaf.breg.common.entity.UserMasterCommon;
import jp.co.broadleaf.breg.common.enums.MailDivEnum;
import jp.co.broadleaf.breg.common.service.BizEmailService;
import jp.co.broadleaf.breg.companysetting.service.CompanyInfoMasterCommonService;
import jp.co.broadleaf.breg.loginmaker.enums.LoginLockDivEnum;

import org.apache.commons.lang3.Validate;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

/**
 * <pre>
 * リマインダ受付Facadeクラス.
 * </pre>
 */
public class ReminderReceptFacadeImpl implements ReminderReceptFacade {

  /** パスワード桁数 */
  private static final int PASSWORD_LEN = 16;
  /** 小文字 */
  private static final String REGEX_LOWER = "a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
  /** 大文字 */
  private static final String REGEX_UPPER = ",A,B,C,D,E,F,G,H,I,G,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
  /** 数字 */
  private static final String REGEX_NUMBER = ",1,2,3,4,5,6,7,8,9,0";
  /** 記号 */
  private static final String REGEX_CHAR = ",~,@,#,$,%,^,&,*,(,),_,+,|,`,.";

  /**
   * <pre>
   * リマインダ受付（パスワード忘れ）.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param ipAddr IPアドレス
   */
  @Override
  public void remindReceptForgetPwd(int makerCode, String loginId, String ipAddr) {
    // プロパティ値取得(ログイン画面URL)
    String loginUrl = propertyResolver.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_URL);

    // メーカーコードの必須チェック
    Validate.notNull(makerCode, "makerCode must not be empty");
    // ログインIDの必須チェック
    Validate.notEmpty(loginId, "loginId must not be empty");
    // IPアドレスの必須チェック
    Validate.notEmpty(ipAddr, "ipAddr must not be empty");

    // 担当者情報取得(メーカーコードとログインID)
    UserMasterCommon userMaster = userMasterService.getUserMaster(makerCode, loginId);
    if (null == userMaster) {
      throw new BusinessException(BregMessageCodes.E00105, "アカウント");
    }
    // 会社情報取得(メーカーコード)
    CompanyInfoMasterCommon companyInfoMaster = companyInfoMasterCommonService.getCompanyInfo(makerCode);
    if (null == companyInfoMaster) {
      throw new BusinessException(BregMessageCodes.E00105, "会社情報");
    }

    // 新規ランダム生成パスワード（16桁数）
    String password = createRandomPassWord(PASSWORD_LEN);
    // パスワード更新
    CertifInfoCommon certifInfo = userMasterPwdService.updatePassword(userMaster.getMakerCode(),
        userMaster.getLoginId(), password);
    if (certifInfo == null) {
      // 認証情報のパスワード更新結果がNullの場合
      throw new BusinessException(BregMessageCodes.E90001, "認証情報の更新");
    }

    // ログインロック状態更新
    UserMasterCommon userMasterStateUpdateAft = userMasterService.updateLoginLockState(userMaster,
        LoginLockDivEnum.LoginAble);
    userMasterStateUpdateAft.setUpdDtTime(new Timestamp(BroadleafUtils.getSystemDtTime().getTime()));
    userMasterStateUpdateAft.setUpdAccountId(loginId);
    // 担当者情報更新
    UserMasterCommon userMasterUpdateAfter = userMasterService.updateUserMaster(userMasterStateUpdateAft);
    if (userMasterUpdateAfter == null) {
      // 担当者情報の更新結果がNullの場合
      throw new BusinessException(BregMessageCodes.E90001, "担当者情報の更新");
    }

    // メール本文可変文言リスト
    List<String> mailBodyChangeableTextList = new ArrayList<String>();
    // 担当者名称
    mailBodyChangeableTextList.add(userMaster.getUserName());
    // ログインパスワード
    mailBodyChangeableTextList.add(password);
    // ログイン画面URL
    mailBodyChangeableTextList.add(loginUrl);

    // 業務メール送信
    boolean sendEmailRtn = bizEmailService.sendBizEmail(MailDivEnum.PasswordNotification, userMaster.getMailAdd(),
        new ArrayList<String>(), new ArrayList<String>(), mailBodyChangeableTextList);
    // 業務メール送信の戻り値がFalseの場合
    if (!sendEmailRtn) {
      throw new BusinessException(BregMessageCodes.E90002);
    }

    // BLへメール本文可変文言リスト
    List<String> blMailBodyChangeableTextList = new ArrayList<String>();
    blMailBodyChangeableTextList.add("ブロードリーフ　担当者"); // BL様名称
    blMailBodyChangeableTextList.add(String.valueOf(userMaster.getMakerCode()));
    blMailBodyChangeableTextList.add(userMaster.getLoginId());
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dataTime = format.format(certifInfo.getUpdDtTime());
    blMailBodyChangeableTextList.add(dataTime);

    // BLへメール送信
    boolean sendEmailToBlRtn = bizEmailService.sendBizEmail(MailDivEnum.PasswordChangedNotification,
        companyInfoMaster.getBlMailAdd(), new ArrayList<String>(), new ArrayList<String>(),
        blMailBodyChangeableTextList);
    if (!sendEmailToBlRtn) {
      throw new BusinessException(BregMessageCodes.E90002);
    }
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

  /**
   * <pre>
   * ランダムパスワードを生成する.
   * </pre>
   * 
   * @param length パスワード長度
   * @return 生成したランダムパスワード
   */
  private String createRandomPassWord(int length) {
    // ランダム数字を取得する
    int random = this.createRandomInt();
    return this.createPassWord(random, length);
  }

  /**
   * <pre>
   * ランダムパスワードを生成する.
   * </pre>
   * 
   * @param random ランダム数字
   * @param len パスワード桁数
   * @return 生成したランダムパスワード
   */
  private String createPassWord(int random, int len) {
    Random rd = new Random(random);
    final int maxNum = 77;
    StringBuffer pwd = new StringBuffer();
    int rdGet;// ランダム数を取得する

    StringBuffer buf = new StringBuffer(REGEX_LOWER);
    buf.append(REGEX_UPPER);
    buf.append(REGEX_CHAR);
    buf.append(REGEX_NUMBER);
    String[] str = buf.toString().split(",");

    int count = 0;
    while (count < len) {
      rdGet = Math.abs(rd.nextInt(maxNum));// 生成した最大数字は77-1
      if (rdGet >= 0 && rdGet < str.length) {
        pwd.append(str[rdGet]);
        count++;
      }
    }
    return pwd.toString();
  }

  /**
   * <pre>
   * ランダム数字を取得する.
   * </pre>
   * 
   * @return 生成したランダム数字
   */
  private int createRandomInt() {
    // 0.0～1.0の数字を取得し、100000倍に拡大する
    double temp = Math.random() * 100000;
    // 数字＞100000の場合、数字-1
    if (temp >= 100000) {
      temp = 99999;
    }
    int tempint = (int) Math.ceil(temp);
    return tempint;
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
