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
import jp.co.broadleaf.framework.domain.entity.EntityNameResolver;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrency;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrencyHelper;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrencyResolver;
import jp.co.broadleaf.framework.util.BeanUtils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Sqlコマンドリゾルバ
 */
public class SqlCommandResolver {

  /**
   * エンティティ名リゾルバ
   */
  private EntityNameResolver entityNameResolver;

  /**
   * コンストラクタ
   * 
   * @param entityNameResolver エンティティ名リゾルバ
   */
  public SqlCommandResolver(EntityNameResolver entityNameResolver) {
    this.entityNameResolver = entityNameResolver;
  }

  /**
   * 追加コマンドを取得する
   * 
   * @param entity エンティティ
   * @return 追加コマンド
   */
  public SqlCommand getInsertCommand(DomainEntity<?> entity) {
    if (entity == null) {
      return null;
    }
    String[] propNames = entity.container().getPropertyNames();
    StringBuilder sql = new StringBuilder();
    sql.append("insert into ");
    sql.append(getTableName(entity));
    sql.append(" (");
    sql.append(getPropertyColumns(entity.getClass(), propNames));
    sql.append(") values (");
    sql.append(getPropertyPlaceHolders(propNames));
    sql.append(")");
    Object[] params = getPropertyValues(entity, propNames);
    return new SqlCommand(sql.toString(), params);
  }

  /**
   * 一括追加コマンドを取得する
   * 
   * @param <T> エンティティクラス
   * @param entities エンティティリスト
   * @return 一括追加コマンド
   */
  public <T extends DomainEntity<?>> List<SqlCommand> getBatchInsertCommands(List<T> entities) {
    if (entities == null || entities.size() == 0) {
      return new ArrayList<SqlCommand>();
    }
    DomainEntity<?> entity = entities.get(0);
    String[] propNames = entity.container().getPropertyNames();
    StringBuilder sql = new StringBuilder();
    sql.append("insert into ");
    sql.append(getTableName(entity));
    sql.append(" (");
    sql.append(getPropertyColumns(entity.getClass(), propNames));
    sql.append(") values (");
    sql.append(getPropertyPlaceHolders(propNames));
    sql.append(")");
    String sqlText = sql.toString();
    List<SqlCommand> commands = new ArrayList<SqlCommand>();
    for (DomainEntity<?> item : entities) {
      Object[] params = getPropertyValues(item, propNames);
      commands.add(new SqlCommand(sqlText, params));
    }
    return commands;
  }

  /**
   * IDより検索のコマンドを取得する
   * 
   * @param <T> エンティティクラス
   * @param entityClass エンティティクラス
   * @param id エンティティID
   * @param forUpdate FOR UPDATEを追加かどうか
   * @return IDより検索のコマンド
   */
  public <T extends DomainEntity<?>> SqlCommand getFindByIdCommand(Class<T> entityClass, Object id, boolean forUpdate) {
    DomainEntity<?> entity = BeanUtils.instantiate(entityClass);
    String[] propNames = entity.container().getPropertyNames();
    StringBuilder sql = new StringBuilder();
    sql.append("select ");
    sql.append(getPropertyColumns(entity.getClass(), propNames));
    sql.append(" from ");
    sql.append(getTableName(entity));
    sql.append(" where ");
    SqlCommand cmd = getIdCommand(entity, id);
    sql.append(cmd.getText());
    if (forUpdate) {
      sql.append(" for update");
    }
    return new SqlCommand(sql.toString(), cmd.getParameters());
  }

  /**
   * 更新コマンドを取得する
   * 
   * @param entity エンティティ
   * @param updateAllProperty True:全項目更新(PK項目を除く) False:変更項目だけ
   * @param forceUpdate True:排他チェックしない False:チェックチェック
   * @param trackingProperty 楽観的排他制御用プロパティ名
   * @param trackingPropertyNewValue 楽観的排他制御用プロパティの新しい値
   * @return 更新コマンド
   */
  public SqlCommand getUpdateCommand(DomainEntity<?> entity, boolean updateAllProperty, boolean forceUpdate,
                                     String trackingProperty, Object trackingPropertyNewValue) {
    StringBuilder sql = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    sql.append("update ");
    sql.append(getTableName(entity));
    sql.append(" set ");
    sql.append(getPropertySetters(entity, params, updateAllProperty, forceUpdate, trackingProperty, trackingPropertyNewValue));
    sql.append(" where ");
    SqlCommand cmd = getIdCommand(entity, entity.identifier());
    if (!forceUpdate) {
      cmd = getConflictDetectionCommand(entity, cmd);
    }
    sql.append(cmd.getText());
    CollectionUtils.addAll(params, cmd.getParameters());
    return new SqlCommand(sql.toString(), params.toArray(new Object[0]));
  }

  /**
   * 削除コマンドを取得する
   * 
   * @param <T> エンティティクラス
   * @param entity エンティティ
   * @param forceUpdate True:排他チェックしない False:チェックチェック
   * @return 削除コマンド
   */
  public <T extends DomainEntity<?>> SqlCommand getDeleteCommand(T entity, boolean forceUpdate) {
    StringBuilder sql = new StringBuilder();
    sql.append("delete from ");
    sql.append(getTableName(entity));
    sql.append(" where ");
    SqlCommand cmd = getIdCommand(entity, entity.identifier());
    if (!forceUpdate) {
      cmd = getConflictDetectionCommand(entity, cmd);
    }
    sql.append(cmd.getText());
    return new SqlCommand(sql.toString(), cmd.getParameters());
  }

  /**
   * フィルタで検索コマンドを取得する
   * 
   * @param <T> エンティティクラス
   * @param entityClass エンティティクラス
   * @param filter フィルタ
   * @param limit 結果制限
   * @param order ソート順
   * @return フィルタで検索コマンド
   */
  public <T extends DomainEntity<?>> SqlCommand getFindByFilterCommand(Class<T> entityClass, Filter filter, Limit limit,
                                                                       Order order) {
    return getSelectCommand(entityClass, null, filter, limit, order, false);
  }

  /**
   * フィルタで件数統計コマンドを取得する
   * 
   * @param <T> エンティティクラス
   * @param entityClass エンティティクラス
   * @param filter フィルタ
   * @return フィルタで件数統計コマンド
   */
  public <T extends DomainEntity<?>> SqlCommand getCountByFilterCommand(Class<T> entityClass, Filter filter) {
    return getSelectCommand(entityClass, null, filter, null, null, true);
  }

  /**
   * 例で検索コマンドを取得する
   * 
   * @param <T> エンティティクラス
   * @param entityClass エンティティクラス
   * @param example 例
   * @param limit 結果制限
   * @param order ソート順
   * @return 例で検索コマンド
   */
  public <T extends DomainEntity<?>> SqlCommand getFindByExampleCommand(Class<T> entityClass, T example, Limit limit,
                                                                        Order order) {
    return getSelectCommand(entityClass, example, null, limit, order, false);
  }

  /**
   * 例で件数統計コマンドを取得する
   * 
   * @param <T> エンティティクラス
   * @param entityClass エンティティクラス
   * @param example 例
   * @return 例で件数統計コマンド
   */
  public <T extends DomainEntity<?>> SqlCommand getCountByExampleCommand(Class<T> entityClass, T example) {
    return getSelectCommand(entityClass, example, null, null, null, true);
  }

  /**
   * 検索コマンドを取得する
   * 
   * @param <T> エンティティクラス
   * @param entityClass エンティティクラス
   * @param example 例
   * @param filter フィルタ
   * @param limit 結果制限
   * @param order ソート順
   * @param count 件数統計フラグ
   * @return 検索コマンド
   */
  private <T extends DomainEntity<?>> SqlCommand getSelectCommand(Class<T> entityClass, T example, Filter filter,
                                                                  Limit limit, Order order, boolean count) {
    DomainEntity<?> entity = BeanUtils.instantiate(entityClass);
    String[] propNames = entity.container().getPropertyNames();
    StringBuilder sql = new StringBuilder();
    List<Object> params = new ArrayList<Object>();
    sql.append("select ");
    if (count) {
      sql.append("count(1)");
    } else {
      sql.append(getPropertyColumns(entityClass, propNames));
    }
    sql.append(" from ");
    sql.append(getTableName(entity));
    if (example != null) {
      int index = 0;
      for (String propName : propNames) {
        Object propValue = example.container().getPropertyValue(propName);
        boolean propChanged = example.container().isPropertyChanged(propName);
        if (propValue != null && propChanged) {
          if (index == 0) {
            sql.append(" where ");
          } else {
            sql.append(" and ");
          }
          sql.append(getColumnName(entityClass, propName));
          sql.append(" = ?");
          params.add(propValue);
          index++;
        }
      }
    } else {
      if (filter != null) {
        SqlFilterVisitor filterVisitor = new SqlFilterVisitor(entityClass, entityNameResolver);
        filter.accept(filterVisitor);
        sql.append(filterVisitor.getWhere());
        params.addAll(filterVisitor.getParameters());
      }
    }
    if (!count) {
      if (order != null) {
        SqlOrderVisitor orderVisitor = new SqlOrderVisitor(entityClass, entityNameResolver);
        order.accept(orderVisitor);
        sql.append(orderVisitor.getOrderBy());
      }
      if (limit != null) {
        if (limit.getMaxRows() != null) {
          sql.append(" limit ?");
          params.add(limit.getMaxRows());
        }
        if (limit.getSkipRows() != null) {
          if (limit.getMaxRows() == null) { // MySQL must set limit
                                            // when using offset
            sql.append(" limit ?");
            params.add(Long.MAX_VALUE);
          }
          sql.append(" offset ?");
          params.add(limit.getSkipRows());
        }
      }
    }
    return new SqlCommand(sql.toString(), params.toArray(new Object[0]));
  }

  /**
   * テーブル名を取得する
   * 
   * @param <T> エンティティクラス
   * @param entityClass エンティティクラス
   * @return テーブル名
   */
  private <T extends DomainEntity<?>> String getTableName(Class<T> entityClass) {
    return entityNameResolver.getEntityName(entityClass);
  }

  /**
   * テーブル名を取得する
   * 
   * @param entity エンティティ
   * @return テーブル名
   */
  private String getTableName(DomainEntity<?> entity) {
    return getTableName(entity.getClass());
  }

  /**
   * カラム名を取得する
   * 
   * @param <T> エンティティクラス
   * @param entityClass エンティティクラス
   * @param propertyName プロパティ名
   * @return カラム名
   */
  private <T extends DomainEntity<?>> String getColumnName(Class<T> entityClass, String propertyName) {
    return entityNameResolver.getEntityPropertyName(entityClass, propertyName);
  }

  /**
   * カラム名を取得する
   * 
   * @param entity エンティティ
   * @param propertyName プロパティ名
   * @return カラム名
   */
  private String getColumnName(DomainEntity<?> entity, String propertyName) {
    return getColumnName(entity.getClass(), propertyName);
  }

  /**
   * エンティティのID検索用のコマンドを取得する
   * 
   * @param entity エンティティ
   * @param id エンティティID
   * @return エンティティのID検索用のコマンド
   */
  private SqlCommand getIdCommand(DomainEntity<?> entity, Object id) {
    SqlCommand command = new SqlCommand();
    if (id instanceof DomainEntityId) {
      DomainEntityId entityId = (DomainEntityId) id;
      StringBuilder sql = new StringBuilder();
      int index = 0;
      for (String propertyName : entityId.container().getPropertyNames()) {
        if (index > 0) {
          sql.append(" and ");
        }
        sql.append(getColumnName(entity, propertyName));
        sql.append(" = ?");
        index++;
      }
      command.setText(sql.toString());
      command.setParameters(entityId.container().getPropertyValues());
    } else {
      command.setText(getColumnName(entity, entity.identifierPropertyNames()[0]) + " = ?");
      command.setParameters(new Object[] { id });
    }
    return command;
  }

  /**
   * 排他チェック用SQLコマンドを取得する
   * 
   * @param <T> エンティティクラス
   * @param entity エンティティ
   * @param cmd 排他チェック追加前のSQLコマンド
   * @return SQLコマンド
   */
  private <T extends DomainEntity<?>> SqlCommand getConflictDetectionCommand(T entity, SqlCommand cmd) {
    OptimisticConcurrency def = entity.getClass().getAnnotation(OptimisticConcurrency.class);
    if (def == null) {
      return cmd;
    }
    OptimisticConcurrencyResolver resolver = OptimisticConcurrencyHelper
        .getOptimisticConcurrencyResolver(entity.getClass());
    if (resolver == null) {
      return cmd;
    }
    SqlCommand command = new SqlCommand();
    String trackingProperty = resolver.getTrackingProperty();
    command.setText(cmd.getText() + " and " + getColumnName(entity, trackingProperty) + " = ?");
    command.setParameters(ArrayUtils.add(cmd.getParameters(), entity.container().getPropertyValue(trackingProperty)));
    return command;
  }

  /**
   * カラム検索用SQL文を取得する
   * 
   * @param <T> エンティティクラス
   * @param entityClass エンティティクラス
   * @param propNames プロパティ名配列
   * @return カラム検索用SQL文
   */
  private <T extends DomainEntity<?>> String getPropertyColumns(Class<T> entityClass, String[] propNames) {
    StringBuilder buf = new StringBuilder();
    int index = 0;
    for (String propName : propNames) {
      if (index > 0) {
        buf.append(", ");
      }
      buf.append(getColumnName(entityClass, propName));
      index++;
    }
    return buf.toString();
  }

  /**
   * カラムセット用SQL文を取得する
   * 
   * @param entity エンティティ
   * @param params カラムのパラメータ値リスト
   * @param updateAllProperty True:全項目更新(PK項目を除く) False:変更項目だけ
   * @param forceUpdate True:排他チェックしない False:チェックチェック
   * @param trackingProperty 楽観的排他制御用プロパティ名
   * @param trackingPropertyNewValue 楽観的排他制御用プロパティの新しい値
   * @return カラムセット用SQL文
   */
  private String getPropertySetters(DomainEntity<?> entity, List<Object> params, boolean updateAllProperty,
                                    boolean forceUpdate, String trackingProperty, Object trackingPropertyNewValue) {
    String[] propNames = getPropertyNamesExceptId(entity, !updateAllProperty);
    StringBuilder buf = new StringBuilder();
    int index = 0;
    Object propValue = null;
    if (!forceUpdate && trackingProperty != null) {
      buf.append(getColumnName(entity, trackingProperty));
      buf.append(" = ?");
      params.add(trackingPropertyNewValue);
      index++;
    }
    for (String propName : propNames) {
      if (!forceUpdate && trackingProperty != null && propName.equals(trackingProperty)) {
        continue;
      } else {
        if (index > 0) {
          buf.append(", ");
        }
        propValue = entity.container().getPropertyValue(propName);
      }
      buf.append(getColumnName(entity, propName));
      buf.append(" = ?");
      params.add(propValue);
      index++;
    }
    return buf.toString();
  }

  /**
   * プロパティ名配列（IDを除く）を取得する
   * 
   * @param entity エンティティ
   * @param onlyIncludeChgProps 変更項目を含むだけ
   * @return プロパティ名配列
   */
  private String[] getPropertyNamesExceptId(DomainEntity<?> entity, boolean onlyIncludeChgProps) {
    String[] propNames = null;
    if (onlyIncludeChgProps) {
      propNames = entity.container().getChangedPropertyNames();
    } else {
      propNames = entity.container().getPropertyNames();
    }
    String[] idPropNames = entity.identifierPropertyNames();
    return ArrayUtils.removeElements(propNames, idPropNames);
  }

  /**
   * カラムのプレースホールダー文を取得する
   * 
   * @param propNames プロパティ名配列
   * @return カラムのプレースホールダー文
   */
  private String getPropertyPlaceHolders(String[] propNames) {
    StringBuilder buf = new StringBuilder();
    for (int index = 0; index < propNames.length; index++) {
      if (index > 0) {
        buf.append(", ");
      }
      buf.append("?");
    }
    return buf.toString();
  }

  /**
   * プロパティの値配列を取得する
   * 
   * @param entity エンティティ
   * @param propNames プロパティの名配列
   * @return プロパティの値配列
   */
  private Object[] getPropertyValues(DomainEntity<?> entity, String[] propNames) {
    Object[] propValues = new Object[propNames.length];
    for (int index = 0; index < propNames.length; index++) {
      propValues[index] = entity.container().getPropertyValue(propNames[index]);
    }
    return propValues;
  }

}
