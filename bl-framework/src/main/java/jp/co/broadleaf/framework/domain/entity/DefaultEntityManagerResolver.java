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
package jp.co.broadleaf.framework.domain.entity;

import java.io.Serializable;

/**
 * <pre>
 * デフォルトエンティティマネージャ取得用実現クラスです。
 * </pre>
 */
public class DefaultEntityManagerResolver implements EntityManagerResolver {

  /**
   * RDB用エンティティマネージャ
   */
  private DomainEntityManager<?, ?> rdbEntityManager;

  /**
   * エンティティマネージャを取得する
   * 
   * @param <T> エンティティクラス
   * @param <ID> エンティティIDクラス
   * @param entityClass エンティティクラス
   * @return エンティティマネージャ
   */
  @Override
  @SuppressWarnings("unchecked")
  public <T extends DomainEntity<ID>, ID extends Serializable> DomainEntityManager<T, ID> getEntityManager(Class<T> entityClass) {
    return (DomainEntityManager<T, ID>) rdbEntityManager;
  }

  /**
   * <pre>
   * RDB用エンティティマネージャを設定する。
   * </pre>
   *
   * @param rdbEntityManager RDB用エンティティマネージャ
   */
  public void setRdbEntityManager(DomainEntityManager<?, ?> rdbEntityManager) {
    this.rdbEntityManager = rdbEntityManager;
  }
}
