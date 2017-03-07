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
package jp.co.broadleaf.common.impl;

import jp.co.broadleaf.common.GeneService;
import jp.co.broadleaf.framework.bean.BeanFactory;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.domain.entity.DomainEntity;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;

import javax.annotation.Resource;

/**
 * <pre>
 * 汎用論理と物理削除サービスクラス。
 * </pre>
 */
public class GeneServiceImpl implements GeneService {

  /**
   * <pre>
   * 汎用論理削除（排他制御あり）。
   * </pre>
   * 
   * @param <T> エンティティクラス
   * @param <ID> エンティティIDクラス
   * @param entity エンティティ（主キーと更新日時だけを設定必要）
   */
  @Override
  public <T extends DomainEntity<ID>, ID extends Serializable> void deleteLogically(T entity) {

    Validate.notNull(entity, "entity must not be null");

    this.getGenericDao(entity).logicalDelete(entity);
  }

  /**
   * <pre>
   * 汎用物理削除（排他制御あり）。
   * </pre>
   * 
   * @param <T> エンティティクラス
   * @param <ID> エンティティIDクラス
   * @param entity エンティティ（主キーと更新日時だけを設定必要）
   */
  @Override
  public <T extends DomainEntity<ID>, ID extends Serializable> void delete(T entity) {

    Validate.notNull(entity, "entity must not be null");

    this.getGenericDao(entity).delete(entity);
  }

  /**
   * <pre>
   * 汎用論理削除（排他制御なし））。
   * </pre>
   * 
   * @param <T> エンティティクラス
   * @param <ID> エンティティIDクラス
   * @param entity エンティティ
   */
  @Override
  public <T extends DomainEntity<ID>, ID extends Serializable> void deleteLogicallyWithoutExclusive(T entity) {

    Validate.notNull(entity, "entity must not be null");

    this.getGenericDao(entity).forceLogicalDelete(entity);
  }

  /**
   * <pre>
   * 汎用物理削除（排他制御なし））。
   * </pre>
   * 
   * @param <T> エンティティクラス
   * @param <ID> エンティティIDクラス
   * @param entity エンティティ
   */
  @Override
  public <T extends DomainEntity<ID>, ID extends Serializable> void deleteWithoutExclusive(T entity) {

    Validate.notNull(entity, "entity must not be null");

    this.getGenericDao(entity).forceDelete(entity);
  }

  /**
   * <pre>
   * 汎用DAOを設定する。
   * </pre>
   * 
   * @param <T> エンティティクラス
   * @param <ID> エンティティIDクラス
   * @param entity エンティティ
   * @return 汎用DAO
   */
  @SuppressWarnings("unchecked")
  private <T extends DomainEntity<ID>, ID extends Serializable> GenericDao<T, ID> getGenericDao(T entity) {

    return (GenericDao<T, ID>) beanFactory.getBean(getGenericDaoBeanName(entity.getClass()), GenericDao.class);
  }

  /**
   * <pre>
   * Bean名を取得する。
   * </pre>
   * 
   * @param entityClass エンティティクラス
   * @return Bean名
   */
  private String getGenericDaoBeanName(Class<?> entityClass) {
    return StringUtils.uncapitalize(entityClass.getSimpleName()) + "Dao";
  }

  /** bean */
  private BeanFactory beanFactory;

  /**
   * <pre>
   * beanFactoryを設定する.
   * </pre>
   * 
   * @param beanFactory BeanFactory
   */
  @Resource
  public void setBeanFactory(BeanFactory beanFactory) {
    this.beanFactory = beanFactory;
  }
}
