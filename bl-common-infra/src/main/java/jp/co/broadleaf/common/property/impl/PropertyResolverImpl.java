//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/24   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.common.property.impl;

import jp.co.broadleaf.common.cache.CacheKeySpecs;
import jp.co.broadleaf.common.cache.CacheKeyUtils;
import jp.co.broadleaf.common.domain.entity.BroadleafDomainEntity;
import jp.co.broadleaf.common.property.PropertyDefine;
import jp.co.broadleaf.common.property.PropertyKey;
import jp.co.broadleaf.common.property.PropertyResolveException;
import jp.co.broadleaf.common.property.PropertyResolver;
import jp.co.broadleaf.common.property.PropertyTypeDivEnum;
import jp.co.broadleaf.common.property.PropertyTypeSpecs;
import jp.co.broadleaf.framework.exception.SystemException;
import jp.co.broadleaf.framework.persistence.jdbc.JdbcDomainEntityManager;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.ParseException;

/**
 * <pre>
 * Broadleafプロジェクト用プロパティリゾルバ実現用クラスです。
 * </pre>
 */
public class PropertyResolverImpl implements PropertyResolver {

  /**
   * プロパティ値を取得する。
   * 
   * @param <T> プロパティのタイプ
   * @param key プロパティキー
   * @return プロパティ値
   */
  @Override
  public <T> T getProperty(PropertyKey<T> key) {
    String propertyKey = key.getName();
    Object propertyValue = getCachedPropertyValue(propertyKey);
    if (propertyValue != null) {
      return getTypedPropertyValue(propertyKey, propertyValue, key.getDataType());
    } else {
      propertyValue = getJdbcPropertyValue(propertyKey);
      if (propertyValue != null) {
        cachePropertyValue(propertyKey, propertyValue);
      }
      return getTypedPropertyValue(propertyKey, propertyValue, key.getDataType());
    }
  }

  /**
   * キャッシュからプロパティ値を取得する。
   * 
   * @param propertyKey プロパティキー
   * @return プロパティ値
   */
  private Object getCachedPropertyValue(String propertyKey) {
    String cacheKey = getPropertyCacheKey(propertyKey);
    return redisTemplate.boundValueOps(cacheKey).get();
  }

  /**
   * プロパティ値がキャッシュに格納する。
   * 
   * @param propertyKey プロパティキー
   * @param propertyValue プロパティ値
   */
  private void cachePropertyValue(String propertyKey, Object propertyValue) {
    String cacheKey = getPropertyCacheKey(propertyKey);
    redisTemplate.boundValueOps(cacheKey).set(propertyValue);
  }

  /**
   * プロパティキャッシュ用キーを取得する。
   * 
   * @param propertyKey プロパティキー
   * @return キャッシュ用キー
   */
  private String getPropertyCacheKey(String propertyKey) {
    return CacheKeyUtils.getCacheKey(CacheKeySpecs.PROPERTY_SCHEMA, propertyKey);
  }

  /**
   * 本来型のプロパティ値を取得する
   * 
   * @param <T> 本来の型
   * @param propertyKey プロパティキー
   * @param propertyValue Object型のプロパティ値
   * @param dataType 本来の型
   * @return 本来型のプロパティ値
   */
  @SuppressWarnings("unchecked")
  private <T> T getTypedPropertyValue(String propertyKey, Object propertyValue, Class<T> dataType) {
    if (propertyValue == null) {
      throw new SystemException(PROPERTY_NOT_FOUND_MSG_ID, propertyKey);
    }
    if (dataType.isAssignableFrom(propertyValue.getClass())) {
      return (T) propertyValue;
    } else {
      throw new PropertyResolveException("The property can not convert to " + dataType);
    }
  }

  /**
   * jdbcからプロパティの値を取得する。
   * 
   * @param propertyKey プロパティキー
   * @return プロパティの値
   */
  private Object getJdbcPropertyValue(String propertyKey) {
    PropertyDefine property = jdbcDomainEntityManager.findById(PropertyDefine.class, propertyKey);
    if (property != null && property.getLogicalDelDiv() == BroadleafDomainEntity.LOGICAL_UNDELETED) {
      return convertPropertyType(property.getPropertyVal(), property.getDataTypeDiv());
    }
    return null;
  }

  /**
   * データ型区分に従い型変換を実行する。
   * 
   * @param propertyValue プロパティ値
   * @param dataTypeDiv データ型区分
   * @return 変換した後 プロパティ値
   */
  private Object convertPropertyType(String propertyValue, int dataTypeDiv) {
    if (propertyValue == null) {
      return null;
    }
    PropertyTypeDivEnum dataType = PropertyTypeDivEnum.valueOf(dataTypeDiv);
    if (dataType == null) {
      throw new PropertyResolveException("DataTypeDiv is invalid, value=" + dataTypeDiv);
    }
    Object value = null;
    switch (dataType) {
    case String:
      value = propertyValue;
      break;
    case Int:
      try {
        value = Integer.parseInt(propertyValue);
      } catch (NumberFormatException ex) {
        throw new PropertyResolveException("Unable to parse the int:" + propertyValue, ex);
      }
      break;
    case Long:
      try {
        value = Long.parseLong(propertyValue);
      } catch (NumberFormatException ex) {
        throw new PropertyResolveException("Unable to parse the long:" + propertyValue, ex);
      }
      break;
    case Date:
      try {
        value = DateUtils.parseDateStrictly(propertyValue, PropertyTypeSpecs.VALID_DATE_FORMATS);
      } catch (ParseException ex) {
        throw new PropertyResolveException("Unable to parse the date:" + propertyValue, ex);
      }
      break;
    default:
      break;
    }
    return value;
  }

  /**
   * Redisテンプレート
   */
  private RedisTemplate<String, Object> redisTemplate;

  /**
   *jdbcドメインエンティティマネージ
   */
  private JdbcDomainEntityManager<PropertyDefine, String> jdbcDomainEntityManager;

  /**
   * Redisテンプレートを設定する。
   *
   * @param redisTemplate Redisテンプレート
   */
  public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * jdbcドメインエンティティマネージを設定する。
   *
   * @param jdbcDomainEntityManager jdbcドメインエンティティマネージ
   */
  public void setJdbcDomainEntityManager(JdbcDomainEntityManager<PropertyDefine, String> jdbcDomainEntityManager) {
    this.jdbcDomainEntityManager = jdbcDomainEntityManager;
  }

  /**
   * プロパティの取得失敗のメッセージID
   * 
   * <pre>
   * プロパティの取得に失敗しました。（プロパティキー：$1）
   * </pre>
   */
  private static final String PROPERTY_NOT_FOUND_MSG_ID = "E19010";
}
