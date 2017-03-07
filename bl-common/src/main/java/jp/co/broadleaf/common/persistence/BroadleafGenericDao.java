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
import jp.co.broadleaf.framework.bean.PropertyContainer;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.wrap.GenericDaoWrapper;
import jp.co.broadleaf.framework.domain.entity.DomainEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Broadleafプロジェクト用共通Dao（共通項目が自動的に設定できる）。
 *
 * @param <T> ドメインエンティティクラス
 * @param <ID> ドメインエンティティIDクラス
 */
public class BroadleafGenericDao<T extends DomainEntity<ID>, ID extends Serializable> extends GenericDaoWrapper<T, ID> {

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param genericDao 汎用DAO
   */
  public BroadleafGenericDao(GenericDao<T, ID> genericDao) {
    super(genericDao);
  }

  /**
   * 登録を実行する。登録処理を実行する
   * 
   * @param entity エンティティ
   */
  @Override
  public void insert(T entity) {
    BroadleafPersistenceHelper.setCommonInfoForInsert(entity);
    getGenericDao().insert(entity);
  }
  
  /**
   * 登録を実行する。登録処理を実行する（ログイン専用）
   * 
   * @param entity エンティティ
   */
  @Override
  public void insertForLogin(T entity) {
    getGenericDao().insert(entity);
  }

  /**
   * 一括登録処理を実行する
   * 
   * @param entities エンティティリスト
   */
  @Override
  public void batchInsert(List<T> entities) {
    if (entities != null) {
      for (T entity : entities) {
        BroadleafPersistenceHelper.setCommonInfoForInsert(entity);
      }
    }
    getGenericDao().batchInsert(entities);
  }

  /**
   * 更新を実行する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void update(T entity) {
    BroadleafPersistenceHelper.setCommonInfoForUpdate(entity);
    getGenericDao().update(entity);
  }

  /**
   * 項目更新処理を実行する(排他チェックしない)
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceUpdate(T entity) {
    BroadleafPersistenceHelper.setCommonInfoForForceUpdate(entity);
    getGenericDao().forceUpdate(entity);
  }
  
  /**
   * 項目更新処理を実行する(排他チェックしない)（ログイン専用）
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceUpdateForLogin(T entity) {
    getGenericDao().forceUpdate(entity);
  }
  
  /**
   * 項目更新処理を実行する(排他チェックしない、全項目を更新する)
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceUpdateAll(T entity) {
    BroadleafPersistenceHelper.setCommonInfoForForceUpdate(entity);
    getGenericDao().forceUpdateAll(entity);
  }
  
  /**
   * 項目更新処理を実行する(排他チェックしない、全項目を更新する)（ログイン専用）
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceUpdateAllForLogin(T entity) {
    getGenericDao().forceUpdateAll(entity);
  }

  /**
   * 排他チェックを実行して、エンティティをロジック削除処理する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void logicalDelete(T entity) {
    PropertyContainer container = entity.container();
    container.setPropertyValue(BroadleafDomainEntity.LOGICAL_DEL_DIV, BroadleafDomainEntity.LOGICAL_DELETED);
    update(entity);
  }

  /**
   * 排他チェックを実行しない、エンティティをロジック削除する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceLogicalDelete(T entity) {
    PropertyContainer container = entity.container();
    container.setPropertyValue(BroadleafDomainEntity.LOGICAL_DEL_DIV, BroadleafDomainEntity.LOGICAL_DELETED);
    forceUpdate(entity);
  }

}
