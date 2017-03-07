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
package jp.co.broadleaf.framework.persistence.jdbc;

import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.Order;
import jp.co.broadleaf.framework.domain.entity.DomainEntity;
import jp.co.broadleaf.framework.domain.entity.DomainEntityId;
import jp.co.broadleaf.framework.domain.entity.DomainEntityManager;
import jp.co.broadleaf.framework.domain.entity.Entity;
import jp.co.broadleaf.framework.domain.entity.EntityNameResolver;
import jp.co.broadleaf.framework.domain.entity.NoEntityPropertyNeedUpdateException;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrencyException;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrencyHelper;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrencyResolver;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Jdbcで実現のドメインエンティティマネジャー
 * 
 * @param <T> ドメインエンティティクラス
 * @param <ID> エンティティIDクラス
 */
public class JdbcDomainEntityManager<T extends DomainEntity<ID>, ID extends Serializable>
                                    implements DomainEntityManager<T, ID> {

  /**
   * ログ
   */
  private final Logger logger = LoggerFactory.getLogger(JdbcDomainEntityManager.class);

  /**
   * メーカー
   */
  private static final String MAKER_TYPE = "maker";

  /**
   * BL
   */
  private static final String BL_TYPE = "bl";

  /**
   * 共通
   */
  private static final String COMMON_TYPE = "common";

  /**
   * JdbcMakerテンプレート
   */
  private JdbcTemplate jdbcTemplateMaker;

  /**
   * JdbcCommonテンプレート
   */
  private JdbcTemplate jdbcTemplateCommon;

  /**
   * JdbcBLテンプレート
   */
  private JdbcTemplate jdbcTemplateBL;

  /**
   * エンティティ名のリゾルバ
   */
  private EntityNameResolver entityNameResolver;

  /**
   * 登録
   * 
   * @param entity エンティティ
   */
  @Override
  public void insert(T entity) {
    Validate.notNull(entity, "Entity must not be null");

    // エンティティの制約をチェックする
    entity.validate();

    // 登録用のコマンドを作成する
    SqlCommand command = getSqlCommandResolver().getInsertCommand(entity);

    // 登録用のコマンドを実行する
    executeJdbcCommand(command, entity.getClass());

    // エンティティの変更を承認する
    entity.container().acceptChanges();
  }

  /**
   * 一括登録処理を実行する
   * 
   * @param entities エンティティリスト
   */
  @Override
  public void batchInsert(List<T> entities) {
    Validate.notNull(entities, "Entities must not be null");

    // 登録用リストが空場合、何もしない、そのまま戻る。
    if (entities.size() == 0) {
      return;
    }

    // エンティティの制約をチェックする
    for (T entity : entities) {
      entity.validate();
    }

    // 一括登録用のコマンドを作成する
    List<SqlCommand> commands = getSqlCommandResolver().getBatchInsertCommands(entities);

    // 一括登録用のコマンドを実行する
    batchExecuteJdbcCommand(commands, entities.get(0).getClass());

    // エンティティの変更を承認する
    for (T entity : entities) {
      entity.container().acceptChanges();
    }
  }

  /**
   * 更新
   * 
   * @param entity エンティティ
   * @param updateAllProperty True:全項目更新(PK項目を除く) False:変更項目だけ
   * @param forceUpdate True:排他チェックしない False:チェックチェック
   * @throws OptimisticConcurrencyException 排他チェックエラー場合、この例外をスローする
   */
  @Override
  public void update(T entity, boolean updateAllProperty, boolean forceUpdate) {
    Validate.notNull(entity, "Entity must not be null");

    if (updateAllProperty) {
      // 全項目更新場合、エンティティの制約をチェックする
      entity.validate();
    } else {
      // 変更項目だけ更新場合、エンティティIDの制約をチェックする
      DomainEntityId.validate(entity.identifier());

      // 変更項目があるかどうかをチェックする
      if (!entity.container().isChanged(entity.identifierPropertyNames())) {
        throw new NoEntityPropertyNeedUpdateException();
      }
    }

    // 楽観的排他用のプロパティ名と新しい値を取得する。
    String trackingProperty = null;
    Object trackingPropertyNewValue = null;
    OptimisticConcurrencyResolver resolver = OptimisticConcurrencyHelper
        .getOptimisticConcurrencyResolver(entity.getClass());
    if (resolver != null) {
      trackingProperty = resolver.getTrackingProperty();
      trackingPropertyNewValue = resolver.getTrackingPropertyNewValue();
    }

    // 更新処理実行コマンドを作成する
    SqlCommand command = getSqlCommandResolver().getUpdateCommand(entity, updateAllProperty, forceUpdate,
        trackingProperty, trackingPropertyNewValue);

    // 更新処理を実行する
    int updateRows = executeJdbcCommand(command, entity.getClass());

    // 楽観的排他制御チェックを実行する
    if (!forceUpdate && updateRows == 0 && OptimisticConcurrencyHelper.hasOptimisticConcurrency(entity.getClass())) {
      throw new OptimisticConcurrencyException();
    }

    // 楽観的排他用のプロパティ値を更新する。
    if (resolver != null) {
      entity.container().setPropertyValue(trackingProperty, trackingPropertyNewValue);
    }

    // エンティティの変更を承認する
    entity.container().acceptChanges();
  }

  /**
   * 削除
   * 
   * @param entity エンティティ
   * @param forceUpdate True:排他チェックしない False:チェックチェック
   * @throws OptimisticConcurrencyException 排他チェックエラー場合、この例外をスローする
   */
  @Override
  public void delete(T entity, boolean forceUpdate) {
    Validate.notNull(entity, "Entity must not be null");

    // エンティティIDの制約をチェックする
    DomainEntityId.validate(entity.identifier());

    // 削除コマンドを作成する
    SqlCommand command = getSqlCommandResolver().getDeleteCommand(entity, forceUpdate);

    // 削除コマンドを実行する
    int deleteRows = executeJdbcCommand(command, entity.getClass());

    // 楽観的排他制御チェックを実行する
    if (!forceUpdate && deleteRows == 0 && OptimisticConcurrencyHelper.hasOptimisticConcurrency(entity.getClass())) {
      throw new OptimisticConcurrencyException();
    }
  }

  /**
   * エンティティIDより検索する
   * 
   * @param entityClass エンティティクラス
   * @param id エンティティID
   * @return 検索結果
   */
  @Override
  public T findById(Class<T> entityClass, Object id) {
    Validate.notNull(entityClass, "EntityClass must not be null");
    Validate.notNull(id, "Id must not be null");

    // エンティティIDの制約をチェックする
    DomainEntityId.validate(id);

    // 検索コマンドを作成する
    SqlCommand command = getSqlCommandResolver().getFindByIdCommand(entityClass, id, false);

    // 検索コマンドを実行する
    List<T> result = queryJdbcCommand(command, entityClass);

    // 検索結果を戻る
    return DataAccessUtils.singleResult(result);
  }

  /**
   * エンティティIDより検索とロックする
   * 
   * @param entityClass エンティティクラス
   * @param id エンティティID
   * @return 検索結果
   */
  @Override
  public T findAndLockById(Class<T> entityClass, Object id) {
    Validate.notNull(entityClass, "EntityClass must not be null");
    Validate.notNull(id, "Id must not be null");

    // エンティティIDの制約をチェックする
    DomainEntityId.validate(id);

    // 検索コマンドを作成する
    SqlCommand command = getSqlCommandResolver().getFindByIdCommand(entityClass, id, true);

    // 検索コマンドを実行する
    List<T> result = queryJdbcCommand(command, entityClass);

    // 検索結果を戻る
    return DataAccessUtils.singleResult(result);
  }

  /**
   * IDよりグループエンティティを検索する
   * 
   * @param entityClass エンティティクラス
   * @param id エンティティID
   * @param parentClasses 親グループエンティティのタイプ配列
   * @param parentIds 親エンティティID配列
   * @return グループエンティティ
   */
  @Override
  public T findById(Class<T> entityClass, Object id, Class<?>[] parentClasses, Object[] parentIds) {
    throw new UnsupportedOperationException();
  }

  /**
   * 順番などより検索
   * 
   * @param entityClass エンティティクラス
   * @param limit 戻る件数などの制限
   * @param order 検索結果の順番
   * @return 検索結果
   */
  @Override
  public List<T> findAll(Class<T> entityClass, Limit limit, Order order) {
    Validate.notNull(entityClass, "EntityClass must not be null");
    return findByFilter(entityClass, null, limit, order);
  }

  /**
   * 検索フィルタなどより検索
   * 
   * @param entityClass エンティティクラス
   * @param filter 検索フィルタ
   * @param limit 戻る件数などの制限
   * @param order 検索結果の順番
   * @return 検索結果
   */
  @Override
  public List<T> findByFilter(Class<T> entityClass, Filter filter, Limit limit, Order order) {
    Validate.notNull(entityClass, "EntityClass must not be null");
    SqlCommand command = getSqlCommandResolver().getFindByFilterCommand(entityClass, filter, limit, order);
    List<T> result = queryJdbcCommand(command, entityClass);
    return result;
  }

  /**
   * 例より検索
   * 
   * @param entityClass エンティティクラス
   * @param entity 例
   * @param limit 戻る件数などの制限
   * @param order 検索結果の順番
   * @return 検索結果
   */
  @Override
  public List<T> findByExample(Class<T> entityClass, T entity, Limit limit, Order order) {
    Validate.notNull(entityClass, "EntityClass must not be null");
    Validate.notNull(entity, "Entity must not be null");
    SqlCommand command = getSqlCommandResolver().getFindByExampleCommand(entityClass, entity, limit, order);
    List<T> result = queryJdbcCommand(command, entityClass);
    return result;
  }

  /**
   * 全て件数を統計する
   * 
   * @param entityClass エンティティクラス
   * @return 統計結果
   */
  @Override
  public long countAll(Class<T> entityClass) {
    Validate.notNull(entityClass, "EntityClass must not be null");
    return countByFilter(entityClass, null);
  }

  /**
   * 検索フィルタより結果数を統計する
   * 
   * @param entityClass エンティティクラス
   * @param filter 検索フィルタ
   * @return 統計結果
   */
  @Override
  public long countByFilter(Class<T> entityClass, Filter filter) {
    Validate.notNull(entityClass, "EntityClass must not be null");
    SqlCommand command = getSqlCommandResolver().getCountByFilterCommand(entityClass, filter);
    long count = countJdbcCommand(command, entityClass);
    return count;
  }

  /**
   * 例より結果数を統計する
   * 
   * @param entityClass エンティティクラス
   * @param entity 例
   * @return 統計結果
   */
  @Override
  public long countByExample(Class<T> entityClass, T entity) {
    Validate.notNull(entityClass, "EntityClass must not be null");
    Validate.notNull(entity, "Entity must not be null");
    SqlCommand command = getSqlCommandResolver().getCountByExampleCommand(entityClass, entity);
    long count = countJdbcCommand(command, entityClass);
    return count;
  }

  /**
   * Sqlコマンドリゾルバを取得する
   * 
   * @return Sqlコマンドリゾルバ
   */
  private SqlCommandResolver getSqlCommandResolver() {
    return new SqlCommandResolver(getEntityNameResolver());
  }

  /**
   * JdbcテンプレートでSql更新コマンドを実行する
   * 
   * @param command Sqlコマンド
   * @param entityClass エンティティクラス
   * @return 実行結果
   */
  private int executeJdbcCommand(SqlCommand command, Class<?> entityClass) {
    logger.debug("{}", command);
    return getJdbcTemplate(entityClass).update(command.getText(), command.getParameters());
  }

  /**
   * JdbcテンプレートでSql更新コマンドを一括実行する
   * 
   * @param commands Sqlコマンドリスト
   * @param entityClass エンティティクラス
   * @return 実行結果
   */
  private int[] batchExecuteJdbcCommand(List<SqlCommand> commands, Class<?> entityClass) {
    String commandText = commands.get(0).getText();
    logger.debug("{}", commandText);
    List<Object[]> parameters = new ArrayList<Object[]>();
    for (SqlCommand command : commands) {
      parameters.add(command.getParameters());
    }
    return getJdbcTemplate(entityClass).batchUpdate(commandText, parameters);
  }

  /**
   * JdbcテンプレートでSql検索コマンドを実行する
   * 
   * @param command Sqlコマンド
   * @param entityClass エンティティクラス
   * @return 検索結果
   */
  private List<T> queryJdbcCommand(SqlCommand command, Class<T> entityClass) {
    logger.debug("{}", command);
    return getJdbcTemplate(entityClass).query(command.getText(), command.getParameters(),
        new DomainEntityResultSetExtractor<T>(entityClass, getEntityNameResolver()));
  }

  /**
   * JdbcテンプレートでSql件数統計コマンドを実行する
   * 
   * @param command Sqlコマンド
   * @param entityClass エンティティクラス
   * @return 統計結果
   */
  private long countJdbcCommand(SqlCommand command, Class<T> entityClass) {
    logger.debug("{}", command);
    return getJdbcTemplate(entityClass).queryForObject(command.getText(), command.getParameters(), Long.class);
  }

  /**
   * Jdbcテンプレートを取得する
   * 
   * @param entityClass エンティティクラス
   * @return Jdbcテンプレート
   */
  protected JdbcTemplate getJdbcTemplate(Class<?> entityClass) {
    Entity entityAnnotation = entityClass.getAnnotation(Entity.class);
    if (entityAnnotation == null || entityAnnotation.name() == null || entityAnnotation.name().length() == 0) {
      throw new IllegalArgumentException(
          "The entity class:" + entityClass.getName() + " does not set the entity annotation");
    }

    String entityType = entityAnnotation.type();
    if (MAKER_TYPE.equalsIgnoreCase(entityType)) {
      return jdbcTemplateMaker;
    } else if (BL_TYPE.equalsIgnoreCase(entityType)) {
      return jdbcTemplateBL;
    } else if (COMMON_TYPE.equalsIgnoreCase(entityType)) {
      return jdbcTemplateCommon;
    } else if (StringUtils.isNotEmpty(entityType)) {
      throw new IllegalArgumentException("The entity's type is not support for locating the domain entity manager");
    }
    return null;
  }

  /**
   * JdbcMakerテンプレートを設定する
   * 
   * @param jdbcTemplateMaker JdbcMakerテンプレート
   */
  public void setJdbcTemplateMaker(JdbcTemplate jdbcTemplateMaker) {
    this.jdbcTemplateMaker = jdbcTemplateMaker;
  }

  /**
   * JdbcCommonテンプレートを設定する
   * 
   * @param jdbcTemplateCommon JdbcCommonテンプレート
   */
  public void setJdbcTemplateCommon(JdbcTemplate jdbcTemplateCommon) {
    this.jdbcTemplateCommon = jdbcTemplateCommon;
  }

  /**
   * JdbcBLテンプレートを設定する
   * 
   * @param jdbcTemplateBL JdbcBLテンプレート
   */
  public void setJdbcTemplateBL(JdbcTemplate jdbcTemplateBL) {
    this.jdbcTemplateBL = jdbcTemplateBL;
  }

  /**
   * エンティティ名のリゾルバを取得する
   * 
   * @return エンティティ名のリゾルバ
   */
  public EntityNameResolver getEntityNameResolver() {
    return entityNameResolver;
  }

  /**
   * エンティティ名のリゾルバを設定する
   * 
   * @param entityNameResolver エンティティ名のリゾルバ
   */
  public void setEntityNameResolver(EntityNameResolver entityNameResolver) {
    this.entityNameResolver = entityNameResolver;
  }

}
