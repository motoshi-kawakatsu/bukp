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
package jp.co.broadleaf.breg.loginmaker.service.impl;

import jp.co.broadleaf.breg.loginmaker.service.UserMasterService;
import jp.co.broadleaf.breg.common.entity.UserMasterCommon;
import jp.co.broadleaf.breg.common.entity.UserMasterCommonId;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.UserKindEnum;
import jp.co.broadleaf.breg.loginmaker.enums.LoginLockDivEnum;
import jp.co.broadleaf.framework.dao.GenericDao;
import org.apache.commons.lang3.Validate;

import javax.annotation.Resource;

/**
 * <pre>
 * 担当者情報サービスクラス.
 * </pre>
 */
public class UserMasterServiceImpl implements UserMasterService {

  /**
   * <pre>
   * 担当者情報取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @return 担当者情報
   */
  @Override
  public UserMasterCommon getUserMaster(int makerCode, String loginId) {
    // 入力パラメータチェック
    Validate.notNull(makerCode, "makerCode must not be null");
    // 入力パラメータチェック
    Validate.notNull(loginId, "loginId must not be null");

    // 検索条件を作る
    UserMasterCommon searchCmd = new UserMasterCommon();
    searchCmd.setMakerCode(makerCode);
    searchCmd.setLoginId(loginId);
    // ユーザー種別
    searchCmd.setUserKind(UserKindEnum.MakerUser.getValue());
    searchCmd.setLogicalDelDiv(DeleteFlgEnum.NoDelete.getValue());

    return userMasterCommonDao.findOneByExample(searchCmd);
  }

  /**
   * <pre>
   * 担当者情報更新.
   * </pre>
   * 
   * @param userMasterInfo 更新する項目（ログインID など）の連係情報
   * @return 更新した担当者情報
   */
  @Override
  public UserMasterCommon updateUserMaster(UserMasterCommon userMasterInfo) {

    Validate.notNull(userMasterInfo, "userMasterInfo must not be null");
    // テーブル登録処理
    UserMasterCommon updateUserMasterInfo = (UserMasterCommon) userMasterInfo.clone();
    
    // 担当者情報更新
    userMasterCommonDao.forceUpdateAllForLogin(updateUserMasterInfo);

    return updateUserMasterInfo;
  }

  /**
   * <pre>
   * ロック状態かチェックする.
   * </pre>
   * 
   * @param userMaster 連係情報（担当者情報）
   * @return チェック結果 ロック状態の場合、True／ロック状態ではない場合、False
   */
  @Override
  public boolean checkUserMasterLock(UserMasterCommon userMaster) {
    // 担当者情報のNullチェック
    Validate.notNull(userMaster, "userMaster must not be null");

    // 1:ログイン不可アカウント
    if (userMaster.getLoginLockDiv() == LoginLockDivEnum.LoginUnable.getValue()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * <pre>
  * ログインロック状態更新.
   * </pre>
   * 
   * @param userMaster 担当者
   * @param loginLockDiv ログインロック区分
   * @return 担当者情報
   */
  @Override
  public UserMasterCommon updateLoginLockState(UserMasterCommon userMaster, LoginLockDivEnum loginLockDiv) {
    Validate.notNull(userMaster, "userMaster must not be null");
    Validate.notNull(loginLockDiv, "loginLockDiv must not be null");

    // ログインロック区分 = 引数.ログインロック区分
    UserMasterCommon userMasterForUpd = (UserMasterCommon) userMaster.clone();
    userMasterForUpd.setLoginLockDiv(loginLockDiv.getValue());

    return userMasterForUpd;
  }

  /** 担当者情報DAO */
  private GenericDao<UserMasterCommon, UserMasterCommonId> userMasterCommonDao;

  /**
   * <pre>
   * 担当者情報DAOを設定する.
   * </pre>
   * 
   * @param userMasterCommonDao 担当者情報DAO
   */
  @Resource
  public void setUserMasterCommonDao(GenericDao<UserMasterCommon, UserMasterCommonId> userMasterCommonDao) {
    this.userMasterCommonDao = userMasterCommonDao;
  }
}
