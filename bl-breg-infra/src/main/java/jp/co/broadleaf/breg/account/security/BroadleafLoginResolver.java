//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.account.security;

import jp.co.broadleaf.breg.loginmaker.facade.LoginFacade;
import jp.co.broadleaf.breg.loginmaker.facade.dto.LoginCertifConfirmDto;
import jp.co.broadleaf.breg.loginmaker.facade.dto.UserMasterDto;
import jp.co.broadleaf.breg.common.BregPropertyKeys;
import jp.co.broadleaf.breg.companysetting.dto.CompanySettingDto;
import jp.co.broadleaf.common.cache.CacheKeySpecs;
import jp.co.broadleaf.common.cache.CacheKeyUtils;
import jp.co.broadleaf.common.property.PropertyResolver;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.common.web.BroadleafSessionKeys;
import jp.co.broadleaf.framework.exception.BusinessException;
import jp.co.broadleaf.framework.security.LoginResolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import javax.annotation.Resource;

/**
 * <pre>
 * BL 自動ログインリゾルバインタフェースです。
 * </pre>
 */
public class BroadleafLoginResolver implements LoginResolver {

  /**
   * ログインFacade
   */
  private LoginFacade loginFacade;

  /**
   * プロパティリゾルバ
   */
  private PropertyResolver propertyResolver;

  /**
   * Redisテンプレート
   */
  private RedisTemplate<String, String> redisTemplate;

  /**
   * ログ
   */
  protected final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

  /**
   * ログイン情報を取得する。
   * 
   * @param token トークン
   * @param cookieSend ログイン状態を維持
   * @return ログイン情報
   */
  @Override
  public Principal getLoginPrincipal(String token, String cookieSend) {
    LoginCertifConfirmDto result = null;
    try {
      result = loginFacade.confirmLoginCertif(token, cookieSend);
    } catch (BusinessException ex) {
      return null;
    } catch (Exception ex) {
      logger.error("システムエラーが発生しました。", ex);
      return null;
    }
    UserMasterDto userMasterDto = result.getUserMasterDto();
    CompanySettingDto companySettingDto = result.getCompanySettingDto();
    LoginPrincipal principal = new LoginPrincipal();
    principal.setLoginId(userMasterDto.getLoginId());
    principal.setMakerCode(userMasterDto.getMakerCode());
    principal.setUserName(userMasterDto.getUserName());
    principal.setUserKana(userMasterDto.getUserKana());
    principal.setUserAdminFlag(userMasterDto.getUserAdminFlag());
    principal.setUserKind(userMasterDto.getUserKind());
    principal.setCompanyName(companySettingDto.getCompanyName());
    principal.setMakerCdName(companySettingDto.getMakerCdName());
    principal.setMakerCdNameShort(companySettingDto.getMakerCdNameShort());
    principal.setCompanyNameKana(companySettingDto.getCompanyNameKana());
    principal.setGoodsRows(companySettingDto.getGoodsRows());
    principal.setSetRows(companySettingDto.getSetRows());
    principal.setJoinRows(companySettingDto.getJoinRows());
    principal.setApplyRecordRows(companySettingDto.getApplyRecordRows());
    principal.setApplyDetailRows(companySettingDto.getApplyDetailRows());
    principal.setGoodsImportType(companySettingDto.getGoodsImportType());
    principal.setSetImportType(companySettingDto.getSetImportType());
    principal.setJoinImportType(companySettingDto.getJoinImportType());
    principal.setCookieSend(cookieSend);
    return principal;
  }

  /**
   * ログインCookie登録
   * 
   * @param cookieId ログインCookie
   * @param principal ログイン情報
   * @return ログインCookieID
   */
  @Override
  public String loginCookieReg(String cookieId, Principal principal) {
    // ログインCookie登録
    LoginPrincipal loginPrincipal = (LoginPrincipal) principal;
    Date systemDtTime = BroadleafUtils.getSystemDtTime();
    String loginCookieId = loginFacade.addLoginCookie(loginPrincipal.getMakerCode(), loginPrincipal.getLoginId(),
        new Timestamp(systemDtTime.getTime()), loginPrincipal.getCookieSend());
    return loginCookieId;
  }

  /**
   * キックアウトかどうかをチェックする
   * 
   * @param principal ログインユーザ情報
   * @param cookieId CookieID
   * @return True:キックアウト
   */
  @Override
  public boolean isKickout(Principal principal, String cookieId) {
    LoginPrincipal loginInfo = (LoginPrincipal) principal;
    String key = getLoginRedisKey(loginInfo.getMakerCode(), loginInfo.getLoginId());
    String loginedCookieId = redisTemplate.boundValueOps(key).get();
    return !Objects.equals(loginedCookieId, cookieId);
  }

  /**
   * キックアウト処理を実行する。
   * 
   * @param principal ログインユーザ情報
   * @param newCookieId 新CookieID
   */
  @Override
  public void kickout(Principal principal, String newCookieId) {
    LoginPrincipal loginInfo = (LoginPrincipal) principal;
    String key = getLoginRedisKey(loginInfo.getMakerCode(), loginInfo.getLoginId());
    redisTemplate.boundValueOps(key).set(newCookieId);
  }

  /**
   * メーカーコードとログインIDより、セッションIDを検索用のRedisキーを取得する。
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @return Redisキー
   */
  protected String getLoginRedisKey(int makerCode, String loginId) {
    String loginAccount = makerCode + "_" + loginId;
    return CacheKeyUtils.getCacheKey(CacheKeySpecs.LOGIN_SCHEMA, loginAccount);
  }

  /**
   * 自動ログイン用のCookie名を取得する。
   * 
   * @return Cookie名
   */
  @Override
  public String getCookieName() {
    return propertyResolver.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_COOKIE_NAME);
  }

  /**
   * 自動ログイン用のCookie有効時間を取得する。
   * 
   * @return Cookie有効時間
   */
  @Override
  public int getCookieMaxAge() {
    return propertyResolver.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_COOKIE_EXPIRY_PERIOD);
  }

  /**
   * 自動ログイン用のCookieパスを取得する。
   * 
   * @return Cookieパス
   */
  @Override
  public String getCookiePath() {
    return propertyResolver.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_COOKIE_PATH);
  }

  /**
   * 自動ログイン用のCookieセキュア属性を取得する。
   * 
   * @return Cookieセキュア属性
   */
  @Override
  public String getCookieSecure() {
    return propertyResolver.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_COOKIE_SECURE);
  }

  /**
   * ログインURLを取得する。
   * 
   * @return ログインURL
   */
  @Override
  public String getLoginUrl() {
    return propertyResolver.getProperty(BregPropertyKeys.BREG_ACCOUNT_LOGIN_URL);
  }

  /**
   * ログイン情報格納用のセッションキーを取得する。
   * 
   * @return ログイン情報格納用のセッションキー
   */
  public String getPrincipalSessionKey() {
    return BroadleafSessionKeys.LOGIN_INFO;
  }

  /**
   * ログインFacadeを設定する。
   *
   * @param loginFacade ログインFacade
   */
  @Resource
  public void setLoginFacade(LoginFacade loginFacade) {
    this.loginFacade = loginFacade;
  }

  /**
   * プロパティリゾルバを設定する。
   *
   * @param propertyResolver プロパティリゾルバ
   */
  @Resource
  public void setPropertyResolver(PropertyResolver propertyResolver) {
    this.propertyResolver = propertyResolver;
  }

  /**
   * Redisテンプレートを設定する。
   *
   * @param redisTemplate Redisテンプレート
   */
  @Resource
  public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

}
