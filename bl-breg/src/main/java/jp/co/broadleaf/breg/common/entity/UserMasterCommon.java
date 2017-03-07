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
package jp.co.broadleaf.breg.common.entity;

import jp.co.broadleaf.common.domain.entity.BroadleafDomainEntity;
import jp.co.broadleaf.common.domain.entity.BroadleafOptimisticConcurrencyResolver;
import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.Entity;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrency;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 担当者情報マスタ
 */
@Entity(name = "m_user_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class UserMasterCommon extends BroadleafDomainEntity<UserMasterCommonId> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * メーカーコード
   */
  public static final String MAKER_CODE = "makerCode";

  /**
   * ログインID
   */
  public static final String LOGIN_ID = "loginId";

  /**
   * 担当者名称
   */
  public static final String USER_NAME = "userName";

  /**
   * 担当者名称（カナ）
   */
  public static final String USER_KANA = "userKana";

  /**
   * 部署
   */
  public static final String DEPARTMENT_NAME = "departmentName";

  /**
   * 担当品目
   */
  public static final String ITEM = "item";

  /**
   * 郵便番号
   */
  public static final String POST_CODE = "postCode";

  /**
   * 住所
   */
  public static final String ADDRESS = "address";

  /**
   * メールアドレス
   */
  public static final String MAIL_ADD = "mailAdd";

  /**
   * 電話番号
   */
  public static final String TEL_NO = "telNo";

  /**
   * FAX
   */
  public static final String FAX_NO = "faxNo";

  /**
   * ユーザー管理者フラグ
   */
  public static final String USER_ADMIN_FLAG = "userAdminFlag";

  /**
   * ユーザー種別
   */
  public static final String USER_KIND = "userKind";

  /**
   * 備考
   */
  public static final String NOTE = "note";

  /**
   * ログインロック区分
   */
  public static final String LOGIN_LOCK_DIV = "loginLockDiv";

  /**
   * コンストラクタ。
   */
  public UserMasterCommon() {

  }

  /**
   * コンストラクタ。
   * 
   * @param id ID
   */
  public UserMasterCommon(UserMasterCommonId id) {
    setMakerCode(id.getMakerCode());
    setLoginId(id.getLoginId());
  }

  /**
   * メーカーコードを返します。
   * 
   * @return メーカーコード
   */
  public int getMakerCode() {
    return container().getPropertyValue(MAKER_CODE, Integer.class);
  }

  /**
   * メーカーコードを設定します。
   * 
   * @param makerCode セットするメーカーコード
   */
  public void setMakerCode(int makerCode) {
    container().setPropertyValue(MAKER_CODE, makerCode);
  }

  /**
   * ログインIDを返します。
   * 
   * @return ログインID
   */
  public String getLoginId() {
    return container().getPropertyValue(LOGIN_ID, String.class);
  }

  /**
   * ログインIDを設定します。
   * 
   * @param loginId セットするログインID
   */
  public void setLoginId(String loginId) {
    container().setPropertyValue(LOGIN_ID, loginId);
  }

  /**
   * 担当者名称を返します。
   * 
   * @return 担当者名称
   */
  public String getUserName() {
    return container().getPropertyValue(USER_NAME, String.class);
  }

  /**
   * 担当者名称を設定します。
   * 
   * @param userName セットする担当者名称
   */
  public void setUserName(String userName) {
    container().setPropertyValue(USER_NAME, userName);
  }

  /**
   * 担当者名称（カナ）を返します。
   * 
   * @return 担当者名称（カナ）
   */
  public String getUserKana() {
    return container().getPropertyValue(USER_KANA, String.class);
  }

  /**
   * 担当者名称（カナ）を設定します。
   * 
   * @param userKana セットする担当者名称（カナ）
   */
  public void setUserKana(String userKana) {
    container().setPropertyValue(USER_KANA, userKana);
  }

  /**
   * 部署を返します。
   * 
   * @return 部署
   */
  public String getDepartmentName() {
    return container().getPropertyValue(DEPARTMENT_NAME, String.class);
  }

  /**
   * 部署を設定します。
   * 
   * @param departmentName セットする部署
   */
  public void setDepartmentName(String departmentName) {
    container().setPropertyValue(DEPARTMENT_NAME, departmentName);
  }

  /**
   * 担当品目を返します。
   * 
   * @return 担当品目
   */
  public String getItem() {
    return container().getPropertyValue(ITEM, String.class);
  }

  /**
   * 担当品目を設定します。
   * 
   * @param item セットする担当品目
   */
  public void setItem(String item) {
    container().setPropertyValue(ITEM, item);
  }

  /**
   * 郵便番号を返します。
   * 
   * @return 郵便番号
   */
  public String getPostCode() {
    return container().getPropertyValue(POST_CODE, String.class);
  }

  /**
   * 郵便番号を設定します。
   * 
   * @param postCode セットする郵便番号
   */
  public void setPostCode(String postCode) {
    container().setPropertyValue(POST_CODE, postCode);
  }

  /**
   * 住所を返します。
   * 
   * @return 住所
   */
  public String getAddress() {
    return container().getPropertyValue(ADDRESS, String.class);
  }

  /**
   * 住所を設定します。
   * 
   * @param address セットする住所
   */
  public void setAddress(String address) {
    container().setPropertyValue(ADDRESS, address);
  }

  /**
   * メールアドレスを返します。
   * 
   * @return メールアドレス
   */
  public String getMailAdd() {
    return container().getPropertyValue(MAIL_ADD, String.class);
  }

  /**
   * メールアドレスを設定します。
   * 
   * @param mailAdd セットするメールアドレス
   */
  public void setMailAdd(String mailAdd) {
    container().setPropertyValue(MAIL_ADD, mailAdd);
  }

  /**
   * 電話番号を返します。
   * 
   * @return 電話番号
   */
  public String getTelNo() {
    return container().getPropertyValue(TEL_NO, String.class);
  }

  /**
   * 電話番号を設定します。
   * 
   * @param telNo セットする電話番号
   */
  public void setTelNo(String telNo) {
    container().setPropertyValue(TEL_NO, telNo);
  }

  /**
   * FAXを返します。
   * 
   * @return FAX
   */
  public String getFaxNo() {
    return container().getPropertyValue(FAX_NO, String.class);
  }

  /**
   * FAXを設定します。
   * 
   * @param faxNo セットするFAX
   */
  public void setFaxNo(String faxNo) {
    container().setPropertyValue(FAX_NO, faxNo);
  }

  /**
   * ユーザー管理者フラグを返します。
   * 
   * @return ユーザー管理者フラグ
   */
  public int getUserAdminFlag() {
    return container().getPropertyValue(USER_ADMIN_FLAG, Integer.class);
  }

  /**
   * ユーザー管理者フラグを設定します。
   * 
   * @param userAdminFlag セットするユーザー管理者フラグ
   */
  public void setUserAdminFlag(int userAdminFlag) {
    container().setPropertyValue(USER_ADMIN_FLAG, userAdminFlag);
  }

  /**
   * ユーザー種別を返します。
   * 
   * @return ユーザー種別
   */
  public int getUserKind() {
    return container().getPropertyValue(USER_KIND, Integer.class);
  }

  /**
   * ユーザー種別を設定します。
   * 
   * @param userKind セットするユーザー種別
   */
  public void setUserKind(int userKind) {
    container().setPropertyValue(USER_KIND, userKind);
  }

  /**
   * 備考を返します。
   * 
   * @return 備考
   */
  public String getNote() {
    return container().getPropertyValue(NOTE, String.class);
  }

  /**
   * 備考を設定します。
   * 
   * @param note セットする備考
   */
  public void setNote(String note) {
    container().setPropertyValue(NOTE, note);
  }

  /**
   * ログインロック区分を返します。
   * 
   * @return ログインロック区分
   */
  public int getLoginLockDiv() {
    return container().getPropertyValue(LOGIN_LOCK_DIV, Integer.class);
  }

  /**
   * ログインロック区分を設定します。
   * 
   * @param loginLockDiv セットするログインロック区分
   */
  public void setLoginLockDiv(int loginLockDiv) {
    container().setPropertyValue(LOGIN_LOCK_DIV, loginLockDiv);
  }

  /**
   * エンティティIDを取得する。
   * 
   * @return エンティティID
   */
  @Override
  public UserMasterCommonId identifier() {
    return new UserMasterCommonId(getMakerCode(), getLoginId());
  }

  /**
   * プロパティ定義の配列を取得する。
   * 
   * @return プロパティ定義の配列
   */
  @Override
  public PropertyDefinition[] defineProperties() {
    return ArrayUtils.addAll(super.defineProperties(), definitions);
  }

  /**
   * プロパティ定義の配列
   */
  private static PropertyDefinition[] definitions;

  static {
    List<PropertyDefinition> list = new ArrayList<PropertyDefinition>();
    list.add(new PropertyDefinition(MAKER_CODE, Integer.class, false));
    list.add(new PropertyDefinition(LOGIN_ID, String.class, false));
    list.add(new PropertyDefinition(USER_NAME, String.class, false));
    list.add(new PropertyDefinition(USER_KANA, String.class, false));
    list.add(new PropertyDefinition(DEPARTMENT_NAME, String.class, false));
    list.add(new PropertyDefinition(ITEM, String.class, false));
    list.add(new PropertyDefinition(POST_CODE, String.class, false));
    list.add(new PropertyDefinition(ADDRESS, String.class, false));
    list.add(new PropertyDefinition(MAIL_ADD, String.class, false));
    list.add(new PropertyDefinition(TEL_NO, String.class, false));
    list.add(new PropertyDefinition(FAX_NO, String.class, false));
    list.add(new PropertyDefinition(USER_ADMIN_FLAG, Integer.class, false));
    list.add(new PropertyDefinition(USER_KIND, Integer.class, false));
    list.add(new PropertyDefinition(NOTE, String.class, true));
    list.add(new PropertyDefinition(LOGIN_LOCK_DIV, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
