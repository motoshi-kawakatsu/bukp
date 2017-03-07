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
package jp.co.broadleaf.framework.persistence;

import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.Order;
import jp.co.broadleaf.framework.domain.entity.DomainEntity;
import jp.co.broadleaf.framework.domain.entity.DomainEntityManager;
import jp.co.broadleaf.framework.domain.entity.EntityManagerResolver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * デフォルト汎用DAOの実現クラス
 * 
 * @param <T> ドメインエンティティクラス
 * @param <ID> ドメインエンティティIDクラス
 */
public class DefaultGenericDao<T extends DomainEntity<ID>, ID extends Serializable> implements GenericDao<T, ID> {

  /**
   * エンティティクラス
   */
  private Class<T> entityClass;

  /**
   * エンティティマネージャリゾルバ
   */
  private EntityManagerResolver entityManagerResolver;

  /**
   * コンストラクタ
   * 
   * @param entityClass エンティティクラス
   */
  public DefaultGenericDao(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  /**
   * 登録処理を実行する
   * 
   * @param entity エンティティ
   */
  @Override
  public void insert(T entity) {
    getEntityManager().insert(entity);
  }
  
  /**
   * 登録処理を実行する（ログイン専用）
   * 
   * @param entity エンティティ
   */
  @Override
  public void insertForLogin(T entity) {
    getEntityManager().insert(entity);
  }

  /**
   * 一括登録処理を実行する
   * 
   * @param entities エンティティリスト
   */
  @Override
  public void batchInsert(List<T> entities) {
    getEntityManager().batchInsert(entities);
  }

  /**
   * 排他チェックを実行して、変更項目だけを更新する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void update(T entity) {
    getEntityManager().update(entity, false, false);
  }

  /**
   * 排他チェックを実行して、全項目を更新する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void updateAll(T entity) {
    getEntityManager().update(entity, true, false);
  }

  /**
   * 排他チェックを実行しない、変更項目だけを更新する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceUpdate(T entity) {
    getEntityManager().update(entity, false, true);
  }
  
  /**
   * 排他チェックを実行しない、変更項目だけを更新する。（ログイン専用）
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceUpdateForLogin(T entity) {
    getEntityManager().update(entity, false, true);
  }

  /**
   * 排他チェックを実行しない、全項目を更新する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceUpdateAll(T entity) {
    getEntityManager().update(entity, true, true);
  }
  
  /**
   * 排他チェックを実行しない、全項目を更新する。（ログイン専用）
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceUpdateAllForLogin(T entity) {
    getEntityManager().update(entity, true, true);
  }

  /**
   * 排他チェックを実行して、エンティティを物理削除処理する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void delete(T entity) {
    getEntityManager().delete(entity, false);
  }

  /**
   * 排他チェックを実行しない、エンティティを物理削除する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceDelete(T entity) {
    getEntityManager().delete(entity, true);
  }

  /**
   * 排他チェックを実行して、エンティティをロジック削除処理する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void logicalDelete(T entity) {
    throw new UnsupportedOperationException();
  }

  /**
   * 排他チェックを実行しない、エンティティをロジック削除する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceLogicalDelete(T entity) {
    throw new UnsupportedOperationException();
  }

  /**
   * プライマリキーより検索する
   * 
   * @param id プライマリキー
   * @return レコード
   */
  @Override
  public T findById(ID id) {
    return getEntityManager().findById(getEntityClass(), id);
  }

  /**
   * プライマリキーより検索とロックする
   * 
   * @param id プライマリキー
   * @return レコード
   */
  @Override
  public T findAndLockById(ID id) {
    return getEntityManager().findAndLockById(getEntityClass(), id);
  }

  /**
   * IDよりグループエンティティを検索する
   *
   * @param id エンティティID
   * @param parentClasses 親グループエンティティのタイプ配列
   * @param parentIds 親エンティティID配列
   * @return グループエンティティ
   */
  @Override
  public T findById(ID id, Class<?>[] parentClasses, Object[] parentIds) {
    return getEntityManager().findById(getEntityClass(), id, parentClasses, parentIds);
  }

  /**
   * 条件なし検索
   * 
   * @return レコードリスト
   */
  @Override
  public List<T> findAll() {
    return findAll(Limit.NO_LIMIT, null);
  }

  /**
   * 条件なし検索
   * 
   * @param limit 戻る件数などの制限
   * @return 検索結果
   */
  @Override
  public List<T> findAll(Limit limit) {
    return findAll(limit, null);
  }

  /**
   * 順番などより検索
   * 
   * @param limit 戻る件数などの制限
   * @param order 検索結果の順番
   * @return 検索結果
   */
  @Override
  public List<T> findAll(Limit limit, Order order) {
    List<T> result = getEntityManager().findAll(getEntityClass(), limit, order);
    if (result == null) {
      result = new ArrayList<T>();
    }
    return result;
  }

  /**
   * 検索フィルタなどより検索、一件だけを戻す
   * 
   * @param filter 検索フィルタ
   * @return 検索結果
   */
  @Override
  public T findOneByFilter(Filter filter) {
    return findOneByFilter(filter, null);
  }

  /**
   * 検索フィルタなどより検索、一件だけを戻す
   * 
   * @param filter 検索フィルタ
   * @param order 検索結果の順番
   * @return 検索結果
   */
  @Override
  public T findOneByFilter(Filter filter, Order order) {
    List<T> result = findByFilter(filter, Limit.FIRST, order);
    if (result.size() > 0) {
      return result.get(0);
    } else {
      return null;
    }
  }

  /**
   * 検索フィルタなどより検索
   * 
   * @param filter 検索フィルタ
   * @param limit 戻る件数などの制限
   * @return 検索結果
   */
  @Override
  public List<T> findByFilter(Filter filter, Limit limit) {
    return findByFilter(filter, limit, null);
  }

  /**
   * 検索フィルタなどより検索
   * 
   * @param filter 検索フィルタ
   * @param limit 戻る件数などの制限
   * @param order 検索結果の順番
   * @return 検索結果
   */
  @Override
  public List<T> findByFilter(Filter filter, Limit limit, Order order) {
    List<T> result = getEntityManager().findByFilter(getEntityClass(), filter, limit, order);
    if (result == null) {
      result = new ArrayList<T>();
    }
    return result;
  }

  /**
   * 検索フィルタなどより検索、一件だけを戻す
   * 
   * @param entity 例
   * @return 検索結果
   */
  @Override
  public T findOneByExample(T entity) {
    return findOneByExample(entity, null);
  }

  /**
   * 検索フィルタなどより検索、一件だけを戻す
   * 
   * @param entity 例
   * @param order 検索結果の順番
   * @return 検索結果
   */
  @Override
  public T findOneByExample(T entity, Order order) {
    List<T> result = findByExample(entity, Limit.FIRST, order);
    if (result.size() > 0) {
      return result.get(0);
    } else {
      return null;
    }
  }

  /**
   * 例より検索
   * 
   * @param entity 例
   * @return 検索結果
   */
  @Override
  public List<T> findByExample(T entity) {
    return findByExample(entity, null, null);
  }

  /**
   * 例より検索
   * 
   * @param entity 例
   * @param limit 戻る件数などの制限
   * @return 検索結果
   */
  @Override
  public List<T> findByExample(T entity, Limit limit) {
    return findByExample(entity, limit, null);
  }

  /**
   * 例より検索
   * 
   * @param entity 例
   * @param limit 戻る件数などの制限
   * @param order 検索結果の順番
   * @return 検索結果
   */
  @Override
  public List<T> findByExample(T entity, Limit limit, Order order) {
    List<T> result = getEntityManager().findByExample(getEntityClass(), entity, limit, order);
    if (result == null) {
      result = new ArrayList<T>();
    }
    return result;
  }

  /**
   * 全て件数を統計する
   * 
   * @return 統計結果
   */
  @Override
  public long countAll() {
    return getEntityManager().countAll(getEntityClass());
  }

  /**
   * 検索フィルタより結果数を統計する
   * 
   * @param filter 検索フィルタ
   * @return 統計結果
   */
  @Override
  public long countByFilter(Filter filter) {
    return getEntityManager().countByFilter(getEntityClass(), filter);
  }

  /**
   * 例より結果数を統計する
   * 
   * @param entity 例
   * @return 統計結果
   */
  @Override
  public long countByExample(T entity) {
    return getEntityManager().countByExample(getEntityClass(), entity);
  }

  /**
   * <pre>
   * エンティティマネージャを取得する。
   * </pre>
   *
   * @return エンティティマネージャ
   */
  protected DomainEntityManager<T, ID> getEntityManager() {
    return entityManagerResolver.getEntityManager(getEntityClass());
  }

  /**
   * エンティティマネージャリゾルバを設定する。
   *
   * @param entityManagerResolver エンティティマネージャリゾルバ
   */
  public void setEntityManagerResolver(EntityManagerResolver entityManagerResolver) {
    this.entityManagerResolver = entityManagerResolver;
  }

  /**
   * <pre>
   * エンティティクラスを取得する。
   * </pre>
   *
   * @return エンティティクラス
   */
  protected Class<T> getEntityClass() {
    return entityClass;
  }

}
