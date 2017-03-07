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
package jp.co.broadleaf.common.security;

import jp.co.broadleaf.framework.bean.BeanCloneException;

import java.io.Serializable;
import java.security.Principal;
import java.util.HashMap;

/**
 * ログイン情報クラス。
 */
public class LoginPrincipal implements Principal, Cloneable, Serializable {

  /** serialVersionUID */
  private static final long serialVersionUID = -665033485814899781L;

  /**
   * ログインID
   */
  private String loginId;

  /**
   * メーカーコード
   */
  private int makerCode;

  /**
   * 担当者名称
   */
  private String userName;

  /**
   * 担当者名称（カナ）
   */
  private String userKana;

  /**
   * ユーザー管理者フラグ
   */
  private int userAdminFlag;
  
  /**
   * メーカーコード名称
   */
  private String makerNameRF;

  /**
   * メーカーコード名称（半角）
   */
  private String makerKanaRF;

  /**
   * 会社名称
   */
  private String companyNameRF;

  /**
   * 会社名称（カナ）
   */
  private String companyKanaRF;

  /**
   * 商品ページ内行数設定
   */
  private int goodsRows;

  /**
   * セットページ内行数設定
   */
  private int setRows;

  /**
   * 結合ページ内行数設定
   */
  private int joinRows;

  /**
   * 申請履歴ページ内行数設定
   */
  private int applyRecordRows;

  /**
   * 申請詳細ページ内行数設定
   */
  private int applyDetailRows;

  /**
   * 商品インポート方法
   */
  private int goodsImportType;

  /**
   * セットインポート方法
   */
  private int setImportType;

  /**
   * 結合インポート方法
   */
  private int joinImportType;

  /**
   * ユーザー種別
   */
  private int userKind;
  
  /**
   * ログイン状態を維持
   */
  private String cookieSend;
  
  /**
   * 共通メッセージマップ
   */
  private HashMap<String,String> messageMap;

  /**
   * コンストラクタ
   */
  public LoginPrincipal() {
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
   * メーカーコードの取得。
   *
   * @return int メーカーコード
   */
  public int getMakerCode() {
      return makerCode;
  }
  
  /**
   * メーカーコードの設定。
   *
   * @param makerCode メーカーコード
   */
  public void setMakerCode(int makerCode) {
      this.makerCode = makerCode;
  }

  /**
   * 担当者名称の取得。
   *
   * @return String 担当者名称
   */
  public String getUserName() {
      return userName;
  }
  
  /**
   * 担当者名称の設定。
   *
   * @param userName 担当者名称
   */
  public void setUserName(String userName) {
      this.userName = userName;
  }

  /**
   * 担当者名称（カナ）の取得。
   *
   * @return String 担当者名称（カナ）
   */
  public String getUserKana() {
      return userKana;
  }
  
  /**
   * 担当者名称（カナ）の設定。
   *
   * @param userKana 担当者名称（カナ）
   */
  public void setUserKana(String userKana) {
      this.userKana = userKana;
  }
  
  /**
   * ユーザー管理者フラグの取得。
   *
   * @return int ユーザー管理者フラグ
   */
  public int getUserAdminFlag() {
      return userAdminFlag;
  }
  
  /**
   * ユーザー管理者フラグの設定。
   *
   * @param userAdminFlag ユーザー管理者フラグ
   */
  public void setUserAdminFlag(int userAdminFlag) {
      this.userAdminFlag = userAdminFlag;
  }

  /**
   * ユーザー種別の取得。
   *
   * @return int ユーザー種別
   */
  public int getUserKind() {
      return userKind;
  }

  /**
   * ユーザー種別の設定。
   *
   * @param userKind ユーザー種別
   */
  public void setUserKind(int userKind) {
      this.userKind = userKind;
  }
  
  /**
   * <pre>
   * 【makerCdName】を取得する。
   * </pre>
   *
   * @return 【makerCdName】
   */
  public String getMakerCdName() {
    return makerNameRF;
  }

  /**
   * <pre>
   * 【makerCdName】を設定する。
   * </pre>
   *
   * @param makerCdName 【makerCdName】
   */
  public void setMakerCdName(String makerCdName) {
    this.makerNameRF = makerCdName;
  }

  /**
   * <pre>
   * 【makerCdNameShort】を取得する。
   * </pre>
   *
   * @return 【makerCdNameShort】
   */
  public String getMakerCdNameShort() {
    return makerKanaRF;
  }

  /**
   * <pre>
   * 【makerCdNameShort】を設定する。
   * </pre>
   *
   * @param makerCdNameShort 【makerCdNameShort】
   */
  public void setMakerCdNameShort(String makerCdNameShort) {
    this.makerKanaRF = makerCdNameShort;
  }

  /**
   * <pre>
   * 【companyName】を取得する。
   * </pre>
   *
   * @return 【companyName】
   */
  public String getCompanyName() {
    return companyNameRF;
  }

  /**
   * <pre>
   * 【companyName】を設定する。
   * </pre>
   *
   * @param companyName 【companyName】
   */
  public void setCompanyName(String companyName) {
    this.companyNameRF = companyName;
  }

  /**
   * <pre>
   * 【companyNameKana】を取得する。
   * </pre>
   *
   * @return 【companyNameKana】
   */
  public String getCompanyNameKana() {
    return companyKanaRF;
  }

  /**
   * <pre>
   * 【companyNameKana】を設定する。
   * </pre>
   *
   * @param companyNameKana 【companyNameKana】
   */
  public void setCompanyNameKana(String companyNameKana) {
    this.companyKanaRF = companyNameKana;
  }

  /**
   * <pre>
   * 【goodsRows】を取得する。
   * </pre>
   *
   * @return 【goodsRows】
   */
  public int getGoodsRows() {
    return goodsRows;
  }

  /**
   * <pre>
   * 【goodsRows】を設定する。
   * </pre>
   *
   * @param goodsRows 【goodsRows】
   */
  public void setGoodsRows(int goodsRows) {
    this.goodsRows = goodsRows;
  }

  /**
   * <pre>
   * 【setRows】を取得する。
   * </pre>
   *
   * @return 【setRows】
   */
  public int getSetRows() {
    return setRows;
  }

  /**
   * <pre>
   * 【setRows】を設定する。
   * </pre>
   *
   * @param setRows 【setRows】
   */
  public void setSetRows(int setRows) {
    this.setRows = setRows;
  }

  /**
   * <pre>
   * 【joinRows】を取得する。
   * </pre>
   *
   * @return 【joinRows】
   */
  public int getJoinRows() {
    return joinRows;
  }

  /**
   * <pre>
   * 【joinRows】を設定する。
   * </pre>
   *
   * @param joinRows 【joinRows】
   */
  public void setJoinRows(int joinRows) {
    this.joinRows = joinRows;
  }

  /**
   * <pre>
   * 【applyRecordRows】を取得する。
   * </pre>
   *
   * @return 【applyRecordRows】
   */
  public int getApplyRecordRows() {
    return applyRecordRows;
  }

  /**
   * <pre>
   * 【applyRecordRows】を設定する。
   * </pre>
   *
   * @param applyRecordRows 【applyRecordRows】
   */
  public void setApplyRecordRows(int applyRecordRows) {
    this.applyRecordRows = applyRecordRows;
  }

  /**
   * <pre>
   * 【applyDetailRows】を取得する。
   * </pre>
   *
   * @return 【applyDetailRows】
   */
  public int getApplyDetailRows() {
    return applyDetailRows;
  }

  /**
   * <pre>
   * 【applyDetailRows】を設定する。
   * </pre>
   *
   * @param applyDetailRows 【applyDetailRows】
   */
  public void setApplyDetailRows(int applyDetailRows) {
    this.applyDetailRows = applyDetailRows;
  }

  /**
   * <pre>
   * 【goodsImportType】を取得する。
   * </pre>
   *
   * @return 【goodsImportType】
   */
  public int getGoodsImportType() {
    return goodsImportType;
  }

  /**
   * <pre>
   * 【goodsImportType】を設定する。
   * </pre>
   *
   * @param goodsImportType 【goodsImportType】
   */
  public void setGoodsImportType(int goodsImportType) {
    this.goodsImportType = goodsImportType;
  }

  /**
   * <pre>
   * 【setImportType】を取得する。
   * </pre>
   *
   * @return 【setImportType】
   */
  public int getSetImportType() {
    return setImportType;
  }

  /**
   * <pre>
   * 【setImportType】を設定する。
   * </pre>
   *
   * @param setImportType 【setImportType】
   */
  public void setSetImportType(int setImportType) {
    this.setImportType = setImportType;
  }

  /**
   * <pre>
   * 【joinImportType】を取得する。
   * </pre>
   *
   * @return 【joinImportType】
   */
  public int getJoinImportType() {
    return joinImportType;
  }

  /**
   * <pre>
   * 【joinImportType】を設定する。
   * </pre>
   *
   * @param joinImportType 【joinImportType】
   */
  public void setJoinImportType(int joinImportType) {
    this.joinImportType = joinImportType;
  }
  
  /**
   * ログイン状態を維持の取得。
   *
   * @return String ログイン状態を維持
   */
  public String getCookieSend() {
    return cookieSend;
  }
  
  /**
   * ログイン状態を維持の設定。
   *
   * @param cookieSend ログイン状態を維持
   */
  public void setCookieSend(String cookieSend) {
    this.cookieSend = cookieSend;
  }
  
  /**
   * 共通メッセージマップの取得。
   *
   * @return HashMap 共通メッセージマップ
   */
  public HashMap<String,String> getMessageMap() {
    return messageMap;
  }
  
  /**
   * 共通メッセージマップの設定。
   *
   * @param messageMap 共通メッセージマップ
   */
  public void setMessageMap(HashMap<String,String> messageMap) {
    this.messageMap = messageMap;
  }
  

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return loginId;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object clone() {
    LoginPrincipal clone;
    try {
      clone = (LoginPrincipal) super.clone();
    } catch (CloneNotSupportedException ex) {
      throw new BeanCloneException(ex);
    }
    clone.setLoginId(loginId);
    clone.setMakerCode(makerCode);
    clone.setUserName(userName);
    clone.setUserKana(userKana);
    clone.setUserAdminFlag(userAdminFlag);
    clone.setUserKind(userKind);
    clone.setCookieSend(cookieSend);
    clone.setMakerCdName(makerNameRF);
    clone.setMakerCdNameShort(makerKanaRF);
    clone.setCompanyName(companyNameRF);
    clone.setCompanyNameKana(companyKanaRF);
    clone.setGoodsRows(goodsRows);
    clone.setSetRows(setRows);
    clone.setJoinRows(joinRows);
    clone.setApplyRecordRows(applyRecordRows);
    clone.setApplyDetailRows(applyDetailRows);
    clone.setGoodsImportType(goodsImportType);
    clone.setSetImportType(setImportType);
    clone.setJoinImportType(joinImportType);
    clone.setMessageMap(messageMap);
    return clone;
  }

}
