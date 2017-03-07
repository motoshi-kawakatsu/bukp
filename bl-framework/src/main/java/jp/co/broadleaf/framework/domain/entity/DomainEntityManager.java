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

import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.Order;

import java.io.Serializable;
import java.util.List;

/**
 * ドメインエンティティマネージクラス
 * 
 * @param <T> エンティティクラス
 * @param <ID> エンティティIDクラス
 */
public interface DomainEntityManager<T extends DomainEntity<ID>, ID extends Serializable> {

  /**
   * 登録
   * 
   * @param entity エンティティ
   */
  void insert(T entity);

  /**
   * 一括登録処理を実行する
   * 
   * @param entities エンティティリスト
   */
  void batchInsert(List<T> entities);

  /**
   * 更新
   * 
   * @param entity エンティティ
   * @param updateAllProperty True:全項目更新(PK項目を除く) False:変更項目だけ
   * @param forceUpdate True:排他チェックしない False:チェックチェック
   * @throws OptimisticConcurrencyException 排他チェックエラー場合、この例外をスローする
   */
  void update(T entity, boolean updateAllProperty, boolean forceUpdate);

  /**
   * 削除
   *
   * @param entity エンティティ
   * @param forceUpdate True:排他チェックしない False:チェックチェック
   * @throws OptimisticConcurrencyException 排他チェックエラー場合、この例外をスローする
   */
  void delete(T entity, boolean forceUpdate);

  /**
   * エンティティIDより検索する
   * 
   * @param entityClass エンティティクラス
   * @param id エンティティID
   * @return 検索結果
   */
  T findById(Class<T> entityClass, Object id);

  /**
   * エンティティIDより検索とロックする
   * 
   * @param entityClass エンティティクラス
   * @param id エンティティID
   * @return 検索結果
   */
  T findAndLockById(Class<T> entityClass, Object id);

  /**
   * IDよりグループエンティティを検索する
   *
   * @param entityClass エンティティクラス
   * @param id エンティティID
   * @param parentClasses 親グループエンティティのタイプ配列
   * @param parentIds 親エンティティID配列
   * @return グループエンティティ
   */
  T findById(Class<T> entityClass, Object id, Class<?>[] parentClasses, Object[] parentIds);

  /**
   * 順番などより検索
   * 
   * @param entityClass エンティティクラス
   * @param limit 戻る件数などの制限
   * @param order 検索結果の順番
   * @return 検索結果
   */
  List<T> findAll(Class<T> entityClass, Limit limit, Order order);

  /**
   * 検索フィルタなどより検索
   * 
   * @param entityClass エンティティクラス
   * @param filter 検索フィルタ
   * @param limit 戻る件数などの制限
   * @param order 検索結果の順番
   * @return 検索結果
   */
  List<T> findByFilter(Class<T> entityClass, Filter filter, Limit limit, Order order);

  /**
   * 例より検索
   * 
   * @param entityClass エンティティクラス
   * @param entity 例
   * @param limit 戻る件数などの制限
   * @param order 検索結果の順番
   * @return 検索結果
   */
  List<T> findByExample(Class<T> entityClass, T entity, Limit limit, Order order);

  /**
   * 全て件数を統計する
   * 
   * @param entityClass エンティティクラス
   * @return 統計結果
   */
  long countAll(Class<T> entityClass);

  /**
   * 検索フィルタより結果数を統計する
   * 
   * @param entityClass エンティティクラス
   * @param filter 検索フィルタ
   * @return 統計結果
   */
  long countByFilter(Class<T> entityClass, Filter filter);

  /**
   * 例より結果数を統計する
   * 
   * @param entityClass エンティティクラス
   * @param entity 例
   * @return 統計結果
   */
  long countByExample(Class<T> entityClass, T entity);
}
