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
package jp.co.broadleaf.breg.loginmaker.service;

import jp.co.broadleaf.breg.common.entity.CertifInfoCommon;

import java.util.Date;

/**
 * <pre>
 * 担当者パスワードサービスインタフェース.
 * </pre>
 */
public interface UserMasterPwdService {

  /**
   * <pre>
   * 認証情報のパスワード新規登録.
   * </pre>
   * 
   * @param makerCode 登録対象のメーカーコード
   * @param loginId 登録対象のログインID
   * @param encryptionPwd 登録対象の暗号化済パスワード
   * @param pwdConvKey 登録対象のパスワード変換キー
   * @return 登録した認証情報
   */
  CertifInfoCommon addPwdNew(int makerCode, String loginId, String encryptionPwd, String pwdConvKey);
  
  /**
   * <pre>
   * 認証情報の取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @return 認証情報
   */
  CertifInfoCommon getCertifInfo(int makerCode, String loginId);
  
  /**
   * <pre>
   * ログイン試行回数チェック処理.
   * </pre>
   * 
   * @param loginAttemptCount ログイン試行回数
   * @param loginAttemptCountSpecifiedValue ログイン試行回数規定値
   * @return チェック結果
   */
  boolean checkLoginAttemptCnt(int loginAttemptCount, int loginAttemptCountSpecifiedValue);
  
  /**
   * ログイン試行回数更新処理.
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @return 更新結果
   */
  CertifInfoCommon updateLoginAttemptCnt(int makerCode, String loginId);

  /**
   * <pre>
   * ログイン回数リセット.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @return 更新した認証情報
   */
  CertifInfoCommon resetLoginCnt(int makerCode, String loginId);

  /**
   * <pre>
   * パスワード一致チェック.
   * </pre>
   * 
   * @param pwdConvKey 認証情報のパスワード変換キー
   * @param loginPwd 認証情報のログインパスワード
   * @param password パスワード
   * @return チェック結果 チェック結果 True:一致、false:一致しない
   */
  boolean checkPwdAccord(String pwdConvKey, String loginPwd, String password);
  
  /**
   * <pre>
   * パスワード有効期限チェック.
   * </pre>
   * 
   * @param pwdExpiryDt 認証情報のパスワード有効期限
   * @param systemDtTime 現在日時
   * @return チェック結果 チェック結果 True:一致、false:一致しない
   */
  boolean checkPwdExpiryDt(Date pwdExpiryDt, Date systemDtTime);

  /**
   * <pre>
   * 認証情報のパスワード更新.
   * </pre>
   * 
   * @param makerCode 更新対象のメーカーコード
   * @param loginId 更新対象のログインID
   * @param loginPwd 更新対象のパスワード
   * @return 更新した認証情報
   */
  CertifInfoCommon updatePassword(int makerCode, String loginId, String loginPwd);

  /**
   * <pre>
   * パスワードを暗号化する.
   * </pre>
   * 
   * @param byteSalt パスワード変換キー
   * @param password パスワード
   * @param pdeKeyIterations PBEKeyのイテレーション回数
   * @return 暗号化したパスワード
   */
  String encryptPassword(byte[] byteSalt, String password, int pdeKeyIterations);
}
