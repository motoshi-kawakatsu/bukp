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
package jp.co.broadleaf.common.persistence;

import jp.co.broadleaf.common.domain.entity.BroadleafDomainEntity;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.framework.bean.PropertyContainer;
import jp.co.broadleaf.framework.domain.entity.DomainEntity;
import jp.co.broadleaf.framework.security.SecurityContextHolder;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Broadleafヘルプクラスです。
 */
public abstract class BroadleafPersistenceHelper {

  /**
   * エンティティを新規する場合、エンティティの共通項目を設定する。
   * 
   * @param entity エンティティ
   */
  public static void setCommonInfoForInsert(DomainEntity<?> entity) {
    if (entity == null) {
      return;
    }
    Timestamp datetime = getCurrentTimestamp();
    PropertyContainer container = entity.container();
    container.setPropertyValue(BroadleafDomainEntity.UUID, makeUUID());
    container.setPropertyValue(BroadleafDomainEntity.INS_DT_TIME, datetime);
    container.setPropertyValue(BroadleafDomainEntity.UPD_DT_TIME, datetime);

    LoginPrincipal principal = SecurityContextHolder.getPrincipal();
    if (principal != null) {
      container.setPropertyValue(BroadleafDomainEntity.INS_ACCOUNT_ID, principal.getLoginId());
      container.setPropertyValue(BroadleafDomainEntity.UPD_ACCOUNT_ID, principal.getLoginId());
    } else {
      // 未ログイン状態のときは、システムアカウントIDとして"0"を設定する
      container.setPropertyValue(BroadleafDomainEntity.INS_ACCOUNT_ID, SYSTEM_ACCOUNT_ID);
      container.setPropertyValue(BroadleafDomainEntity.UPD_ACCOUNT_ID, SYSTEM_ACCOUNT_ID);
    }
    container.setPropertyValue(BroadleafDomainEntity.LOGICAL_DEL_DIV, BroadleafDomainEntity.LOGICAL_UNDELETED);
  }

  /**
   * エンティティを更新する場合、エンティティの共通項目を設定する。
   * 
   * @param entity エンティティ
   */
  public static void setCommonInfoForUpdate(DomainEntity<?> entity) {
    if (entity == null) {
      return;
    }
    LoginPrincipal principal = SecurityContextHolder.getPrincipal();
    PropertyContainer container = entity.container();
    if (principal != null) {
      container.setPropertyValue(BroadleafDomainEntity.UPD_ACCOUNT_ID, principal.getLoginId());
    } else {
      // 未ログイン状態のときは、システムアカウントIDとして"0"を設定する
      container.setPropertyValue(BroadleafDomainEntity.UPD_ACCOUNT_ID, SYSTEM_ACCOUNT_ID);
    }
  }

  /**
   * エンティティを強制（排他チェックしない）で更新する場合、エンティティの共通項目を設定する。
   * 
   * @param entity エンティティ
   */
  public static void setCommonInfoForForceUpdate(DomainEntity<?> entity) {
    setCommonInfoForUpdate(entity);
    PropertyContainer container = entity.container();
    container.setPropertyValue(BroadleafDomainEntity.UPD_DT_TIME, BroadleafPersistenceHelper.getCurrentTimestamp());
  }

  /**
   * システム日時を取得する 。
   * 
   * @return システム日時
   */
  public static Timestamp getCurrentTimestamp() {
    return new Timestamp(BroadleafUtils.getSystemDtTime().getTime());
  }

  /**
   * UUIDを作成する。
   * 
   * @return UUID
   */
  public static String makeUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }
  
  /**
   * システムアカウントID
   */
  private static final String SYSTEM_ACCOUNT_ID = "";
}
