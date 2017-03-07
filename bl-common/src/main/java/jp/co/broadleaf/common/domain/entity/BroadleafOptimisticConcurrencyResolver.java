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
package jp.co.broadleaf.common.domain.entity;

import jp.co.broadleaf.common.persistence.BroadleafPersistenceHelper;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrencyResolver;

/**
 * Broadleafプロジェクト用楽観的排他制御用リゾルバ
 */
public class BroadleafOptimisticConcurrencyResolver implements OptimisticConcurrencyResolver {

  /**
   * 楽観的排他制御用プロパティ名を取得する。
   * 
   * @return 楽観的排他制御用プロパティ名
   */
  @Override
  public String getTrackingProperty() {
    return BroadleafDomainEntity.UPD_DT_TIME;
  }

  /**
   * 更新時、楽観的排他制御用プロパティの新しい値を取得する。
   * 
   * @return 楽観的排他制御用プロパティの新しい値
   */
  @Override
  public Object getTrackingPropertyNewValue() {
    return BroadleafPersistenceHelper.getCurrentTimestamp();
  }

}
