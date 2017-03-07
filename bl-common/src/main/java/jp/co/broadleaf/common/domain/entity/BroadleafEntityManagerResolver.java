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

import jp.co.broadleaf.framework.domain.entity.DomainEntity;
import jp.co.broadleaf.framework.domain.entity.DomainEntityManager;
import jp.co.broadleaf.framework.domain.entity.Entity;
import jp.co.broadleaf.framework.domain.entity.EntityManagerResolver;

//import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Broadleafプロジェクト用エンティティマネージャリゾルバクラスです。
 */
public class BroadleafEntityManagerResolver implements EntityManagerResolver {

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
  @SuppressWarnings("unchecked")
  public <T extends DomainEntity<ID>, ID extends Serializable> DomainEntityManager<T, ID> getEntityManager(Class<T> entityClass) {
    Entity entityAnnotation = entityClass.getAnnotation(Entity.class);
    if (entityAnnotation == null || entityAnnotation.name() == null || entityAnnotation.name().length() == 0) {
      throw new IllegalArgumentException(
          "The entity class:" + entityClass.getName() + " does not set the entity annotation");
    }
    
    return (DomainEntityManager<T, ID>) rdbEntityManager;

//    String entityType = entityAnnotation.type();
//    if (MAKER_TYPE.equalsIgnoreCase(entityType)) {
//      return (DomainEntityManager<T, ID>) datastoreEntityManager;
//    } else if (BL_TYPE.equalsIgnoreCase(entityType)) {
//      return (DomainEntityManager<T, ID>) rdbEntityManager;
//    } else if (StringUtils.isNotEmpty(entityType)) {
//      throw new IllegalArgumentException("The entity's type is not support for locating the domain entity manager");
//    }
//
//    String entityName = entityAnnotation.name();
//    if (entityName.startsWith(MASTER_PREFIX) || entityName.startsWith(WORK_PREFIX)) {
//      return (DomainEntityManager<T, ID>) datastoreEntityManager;
//    } else if (entityName.startsWith(TRANSACTION_PREFIX)) {
//      return (DomainEntityManager<T, ID>) rdbEntityManager;
//    } else {
//      throw new IllegalArgumentException("The entity's prefix is not support for locating the domain entity manager");
//    }
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
//
//  /**
//   * マスタ系テーブルの接頭辞
//   */
//  private static final String MASTER_PREFIX = "m_";
//
//  /**
//   * トラン系テーブルの接頭辞
//   */
//  private static final String TRANSACTION_PREFIX = "t_";
//
//  /**
//   * ワーク系テーブルの接頭辞
//   */
//  private static final String WORK_PREFIX = "w_";
//
//  /**
//   * メーカー
//   */
//  private static final String MAKER_TYPE = "maker";
//
//  /**
//   * BL
//   */
//  private static final String BL_TYPE = "bl";
//  
//  /**
//   * 共通
//   */
//  private static final String COMMON_TYPE = "common";
}
