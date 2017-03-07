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
package jp.co.broadleaf.framework.dao.wrap;

import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.Order;
import jp.co.broadleaf.framework.domain.entity.DomainEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 汎用DAOのラッパークラス
 *
 * @param <T> ドメインエンティティクラス
 * @param <ID> ドメインエンティティIDクラス
 */
public class GenericDaoWrapper<T extends DomainEntity<ID>, ID extends Serializable> implements GenericDao<T, ID> {

  /**
   * 汎用DAO
   */
  private GenericDao<T, ID> genericDao;

  /**
   * コンストラクタ
   * 
   * @param genericDao 汎用DAO
   */
  public GenericDaoWrapper(GenericDao<T, ID> genericDao) {
    this.genericDao = genericDao;
  }

  /**
   * 汎用DAOを取得する
   * 
   * @return 汎用DAO
   */
  protected GenericDao<T, ID> getGenericDao() {
    return genericDao;
  }

  /**
   * 登録処理を実行する
   * 
   * @param entity エンティティ
   */
  @Override
  public void insert(T entity) {
    getGenericDao().insert(entity);
  }
  
  /**
   * 登録処理を実行する（ログイン専用）
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
    getGenericDao().batchInsert(entities);
  }

  /**
   * 排他チェックを実行して、変更項目だけを更新する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void update(T entity) {
    getGenericDao().update(entity);
  }

  /**
   * 排他チェックを実行して、全項目を更新する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void updateAll(T entity) {
    getGenericDao().updateAll(entity);
  }

  /**
   * 排他チェックを実行しない、変更項目だけを更新する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceUpdate(T entity) {
    getGenericDao().forceUpdate(entity);
  }
  
  /**
   * 排他チェックを実行しない、変更項目だけを更新する。（ログイン専用）
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceUpdateForLogin(T entity) {
    getGenericDao().forceUpdate(entity);
  }

  /**
   * 排他チェックを実行しない、全項目を更新する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceUpdateAll(T entity) {
    getGenericDao().forceUpdateAll(entity);
  }
  
  /**
   * 排他チェックを実行しない、全項目を更新する。（ログイン専用）
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceUpdateAllForLogin(T entity) {
    getGenericDao().forceUpdateAll(entity);
  }

  /**
   * 排他チェックを実行して、エンティティを物理削除処理する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void delete(T entity) {
    getGenericDao().delete(entity);
  }

  /**
   * 排他チェックを実行しない、エンティティを物理削除する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceDelete(T entity) {
    getGenericDao().forceDelete(entity);
  }

  /**
   * 排他チェックを実行して、エンティティをロジック削除処理する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void logicalDelete(T entity) {
    getGenericDao().logicalDelete(entity);
  }

  /**
   * 排他チェックを実行しない、エンティティをロジック削除する。
   * 
   * @param entity エンティティ
   */
  @Override
  public void forceLogicalDelete(T entity) {
    getGenericDao().forceLogicalDelete(entity);
  }

  /**
   * プライマリキーより検索する
   * 
   * @param id プライマリキー
   * @return レコード
   */
  @Override
  public T findById(ID id) {
    return getGenericDao().findById(id);
  }

  /**
   * プライマリキーより検索とロックする
   * @param id プライマリキー
   * @return レコード
   */
  @Override
  public T findAndLockById(ID id) {
    return getGenericDao().findAndLockById(id);
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
    return getGenericDao().findById(id, parentClasses, parentIds);
  }

  /**
   * 条件なし検索
   * 
   * @return レコードリスト
   */
  @Override
  public List<T> findAll() {
    return getGenericDao().findAll();
  }

  /**
   * 条件なし検索
   * 
   * @param limit 戻る件数などの制限
   * @return 検索結果
   */
  @Override
  public List<T> findAll(Limit limit) {
    return getGenericDao().findAll(limit);
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
    return getGenericDao().findAll(limit, order);
  }

  /**
   * 検索フィルタなどより検索、一件だけを戻す
   * 
   * @param filter 検索フィルタ
   * @return 検索結果
   */
  @Override
  public T findOneByFilter(Filter filter) {
    return getGenericDao().findOneByFilter(filter);
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
    return getGenericDao().findOneByFilter(filter, order);
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
    return getGenericDao().findByFilter(filter, limit);
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
    return getGenericDao().findByFilter(filter, limit, order);
  }

  /**
   * 検索フィルタなどより検索、一件だけを戻す
   * 
   * @param entity 例
   * @return 検索結果
   */
  @Override
  public T findOneByExample(T entity) {
    return getGenericDao().findOneByExample(entity);
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
    return getGenericDao().findOneByExample(entity, order);
  }

  /**
   * 例より検索
   * 
   * @param entity 例
   * @return 検索結果
   */
  @Override
  public List<T> findByExample(T entity) {
    return getGenericDao().findByExample(entity);
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
    return getGenericDao().findByExample(entity, limit);
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
    return getGenericDao().findByExample(entity, limit, order);
  }

  /**
   * 全て件数を統計する
   * 
   * @return 統計結果
   */
  @Override
  public long countAll() {
    return getGenericDao().countAll();
  }

  /**
   * 検索フィルタより結果数を統計する
   * 
   * @param filter 検索フィルタ
   * @return 統計結果
   */
  @Override
  public long countByFilter(Filter filter) {
    return getGenericDao().countByFilter(filter);
  }

  /**
   * 例より結果数を統計する
   * 
   * @param entity 例
   * @return 統計結果
   */
  @Override
  public long countByExample(T entity) {
    return getGenericDao().countByExample(entity);
  }
}
