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

import jp.co.broadleaf.breg.common.entity.UserMasterCommon;
import jp.co.broadleaf.breg.loginmaker.enums.LoginLockDivEnum;

/**
 * <pre>
 * 担当者情報サービスインタフェース.
 * </pre>
 */
public interface UserMasterService {

  /**
   * <pre>
   * 担当者情報取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @return 担当者情報
   */
  UserMasterCommon getUserMaster(int makerCode, String loginId);

  /**
   * <pre>
   * 担当者情報更新.
   * </pre>
   * 
   * @param userMasterInfo 更新する項目（ログインID など）の連係情報
   * @return 更新した担当者情報
   */
  UserMasterCommon updateUserMaster(UserMasterCommon userMasterInfo);

  /**
   * <pre>
   * ロック状態かチェックする.
   * </pre>
   * 
   * @param userMaster 連係情報（担当者情報）
   * @return チェック結果 ロック状態の場合、True／ロック状態ではない場合、False
   */
  boolean checkUserMasterLock(UserMasterCommon userMaster);

  /**
   * <pre>
  * ログインロック状態更新.
   * </pre>
   * 
   * @param userMaster 担当者
   * @param loginLockDivision ログインロック区分
   * @return 担当者情報
   */
  UserMasterCommon updateLoginLockState(UserMasterCommon userMaster, LoginLockDivEnum loginLockDivision);

}
