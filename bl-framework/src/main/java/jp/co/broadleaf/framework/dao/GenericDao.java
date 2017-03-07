//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/02/05   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.framework.dao;

import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.Order;
import jp.co.broadleaf.framework.domain.entity.DomainEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 汎用DAOインタフェース
 * 
 * @param <T> ドメインエンティティクラス
 * @param <ID> ドメインエンティティIDクラス
 */
public interface GenericDao<T extends DomainEntity<ID>, ID extends Serializable> {

  /**
   * 登録処理を実行する
   * 
   * @param entity エンティティ
   */
  void insert(T entity);
  
  /**
   * 登録処理を実行する（ログイン専用）
   * 
   * @param entity エンティティ
   */
  void insertForLogin(T entity);

  /**
   * 一括登録処理を実行する
   * 
   * @param entities エンティティリスト
   */
  void batchInsert(List<T> entities);

  /**
   * 排他チェックを実行して、変更項目だけを更新する。
   * 
   * @param entity エンティティ
   */
  void update(T entity);

  /**
   * 排他チェックを実行して、全項目を更新する。
   * 
   * @param entity エンティティ
   */
  void updateAll(T entity);

  /**
   * 排他チェックを実行しない、変更項目だけを更新する。
   * 
   * @param entity エンティティ
   */
  void forceUpdate(T entity);
  
  /**
   * 排他チェックを実行しない、変更項目だけを更新する。（ログイン専用）
   * 
   * @param entity エンティティ
   */
  void forceUpdateForLogin(T entity);

  /**
   * 排他チェックを実行しない、全項目を更新する。
   * 
   * @param entity エンティティ
   */
  void forceUpdateAll(T entity);
  
  /**
   * 排他チェックを実行しない、全項目を更新する。（ログイン専用）
   * 
   * @param entity エンティティ
   */
  void forceUpdateAllForLogin(T entity);

  /**
   * 排他チェックを実行して、エンティティを物理削除処理する。
   * 
   * @param entity エンティティ
   */
  void delete(T entity);

  /**
   * 排他チェックを実行しない、エンティティを物理削除する。
   * 
   * @param entity エンティティ
   */
  void forceDelete(T entity);

  /**
   * 排他チェックを実行して、エンティティをロジック削除処理する。
   * 
   * @param entity エンティティ
   */
  void logicalDelete(T entity);

  /**
   * 排他チェックを実行しない、エンティティをロジック削除する。
   * 
   * @param entity エンティティ
   */
  void forceLogicalDelete(T entity);

  /**
   * プライマリキーより検索する
   * 
   * @param id プライマリキー
   * @return レコード
   */
  T findById(ID id);
  
  /**
   * プライマリキーより検索とロックする
   * @param id プライマリキー
   * @return レコード
   */
  T findAndLockById(ID id);

  /**
   * IDよりグループエンティティを検索する
   *
   * @param id エンティティID
   * @param parentClasses 親グループエンティティのタイプ配列
   * @param parentIds 親エンティティID配列
   * @return グループエンティティ
   */
  T findById(ID id, Class<?>[] parentClasses, Object[] parentIds);

  /**
   * 条件なし検索
   * 
   * @return レコードリスト
   */
  List<T> findAll();

  /**
   * 条件なし検索
   * 
   * @param limit 戻る件数などの制限
   * @return 検索結果
   */
  List<T> findAll(Limit limit);

  /**
   * 順番などより検索
   * 
   * @param limit 戻る件数などの制限
   * @param order 検索結果の順番
   * @return 検索結果
   */
  List<T> findAll(Limit limit, Order order);

  /**
   * 検索フィルタなどより検索、一件だけを戻す
   * 
   * @param filter 検索フィルタ
   * @return 検索結果
   */
  T findOneByFilter(Filter filter);

  /**
   * 検索フィルタなどより検索、一件だけを戻す
   * 
   * @param filter 検索フィルタ
   * @param order 検索結果の順番
   * @return 検索結果
   */
  T findOneByFilter(Filter filter, Order order);

  /**
   * 検索フィルタなどより検索
   * 
   * @param filter 検索フィルタ
   * @param limit 戻る件数などの制限
   * @return 検索結果
   */
  List<T> findByFilter(Filter filter, Limit limit);

  /**
   * 検索フィルタなどより検索
   * 
   * @param filter 検索フィルタ
   * @param limit 戻る件数などの制限
   * @param order 検索結果の順番
   * @return 検索結果
   */
  List<T> findByFilter(Filter filter, Limit limit, Order order);

  /**
   * 検索フィルタなどより検索、一件だけを戻す
   * 
   * @param entity 例
   * @return 検索結果
   */
  T findOneByExample(T entity);

  /**
   * 検索フィルタなどより検索、一件だけを戻す
   * 
   * @param entity 例
   * @param order 検索結果の順番
   * @return 検索結果
   */
  T findOneByExample(T entity, Order order);

  /**
   * 例より検索
   * 
   * @param entity 例
   * @return 検索結果
   */
  List<T> findByExample(T entity);

  /**
   * 例示より検索
   * 
   * @param entity 例示
   * @param limit 戻る件数などの制限
   * @return 検索結果
   */
  List<T> findByExample(T entity, Limit limit);

  /**
   * 例示より検索
   * 
   * @param entity 例示
   * @param limit 戻る件数などの制限
   * @param order 検索結果の順番
   * @return 検索結果
   */
  List<T> findByExample(T entity, Limit limit, Order order);

  /**
   * 全て件数を統計する
   * 
   * @return 統計結果
   */
  long countAll();

  /**
   * 検索フィルタより結果数を統計する
   * 
   * @param filter 検索フィルタ
   * @return 統計結果
   */
  long countByFilter(Filter filter);

  /**
   * 例示より結果数を統計する
   * 
   * @param entity 例示
   * @return 統計結果
   */
  long countByExample(T entity);
}
