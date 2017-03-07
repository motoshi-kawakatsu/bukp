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

import jp.co.broadleaf.framework.bean.PropertyContainer;
import jp.co.broadleaf.framework.domain.entity.DomainEntity;
import jp.co.broadleaf.framework.domain.entity.EntityNameResolver;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ドメインエンティティ結果セット抽出実現クラスです。
 * 
 * @param <T> ドメインエンティティ
 */
public class DomainEntityResultSetExtractor<T extends DomainEntity<?>> implements ResultSetExtractor<List<T>> {

  /**
   * ドメインエンティティクラス
   */
  private Class<T> domainEntityClass;

  /**
   * エンティティ名リゾルバ
   */
  private EntityNameResolver entityNameResolver;

  /**
   * コンストラクタ
   * 
   * @param domainEntityClass ドメインエンティティクラス
   * @param entityNameResolver エンティティ名リゾルバ
   */
  public DomainEntityResultSetExtractor(Class<T> domainEntityClass, EntityNameResolver entityNameResolver) {
    this.domainEntityClass = domainEntityClass;
    this.entityNameResolver = entityNameResolver;
  }

  /**
   * 結果セットの行からエンティティを抽出する。
   * 
   * @param columnToPropertyMap カラムプロパティマップ
   * @param rs 結果セット
   * @return エンティティ
   * @throws SQLException SQLException例外発生する場合
   */
  protected T mapRow(Map<String, String> columnToPropertyMap, ResultSet rs) throws SQLException {
    T entity = BeanUtils.instantiate(domainEntityClass);
    PropertyContainer container = entity.container();
    ResultSetMetaData rsmd = rs.getMetaData();
    int columnCount = rsmd.getColumnCount();
    for (int index = 1; index <= columnCount; index++) {
      String column = JdbcUtils.lookupColumnName(rsmd, index);
      String property = columnToPropertyMap.get(column);
      if (property != null) {
        Object value = JdbcUtils.getResultSetValue(rs, index, container.getPropertyType(property));
        container.setPropertyValue(property, value);
      }
    }
    container.acceptChanges();
    return entity;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<T> extractData(ResultSet rs) throws SQLException, DataAccessException {
    List<T> results = new ArrayList<T>();
    T dummyEntity = BeanUtils.instantiate(domainEntityClass);
    Map<String, String> columnToPropertyMap = new HashMap<String, String>();
    String[] propertyNames = dummyEntity.container().getPropertyNames();
    for (String propertyName : propertyNames) {
      String column = entityNameResolver.getEntityPropertyName(domainEntityClass, propertyName);
      columnToPropertyMap.put(column, propertyName);
    }
    while (rs.next()) {
      results.add(mapRow(columnToPropertyMap, rs));
    }
    return results;
  }

}
