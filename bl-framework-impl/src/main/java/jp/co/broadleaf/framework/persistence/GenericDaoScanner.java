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

import jp.co.broadleaf.framework.domain.entity.DomainEntity;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.Set;

/**
 * 共通DaoBeanのスキャナ
 */
public class GenericDaoScanner {

  /**
   * BeanDefinitionRegistry
   */
  private BeanDefinitionRegistry registry;

  /**
   * Daoのラップクラス
   */
  private Class<?> daoWrapClass;

  /**
   * エンティティマネージャリゾルバ
   */
  private String entityManagerResolverBeanName;

  /**
   * コンストラクタ
   * 
   * @param registry BeanDefinitionRegistry
   * @param daoWrapClass Daoのラップクラス
   * @param entityManagerResolverBeanName エンティティマネージャリゾルバ
   */
  public GenericDaoScanner(BeanDefinitionRegistry registry, Class<?> daoWrapClass, String entityManagerResolverBeanName) {
    this.registry = registry;
    this.daoWrapClass = daoWrapClass;
    this.entityManagerResolverBeanName = entityManagerResolverBeanName;
  }

  /**
   * DaoBeanのスキャ処理
   * 
   * @param basePackages ベースパッケージ
   */
  public void scan(String... basePackages) {
    Assert.notEmpty(basePackages, "At least one base package must be specified");
    ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
    scanner.addIncludeFilter(new TypeFilter() {
      public boolean match(MetadataReader metadataReader,
                           MetadataReaderFactory metadataReaderFactory) throws IOException {
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        boolean result = classMetadata.hasSuperClass()
            && classMetadata.getSuperClassName().endsWith(DomainEntity.class.getSimpleName());
        return result;
      }
    });
    for (String basePackage : basePackages) {
      Set<BeanDefinition> candidates = scanner.findCandidateComponents(basePackage);
      for (BeanDefinition candidate : candidates) {
        if (candidate instanceof ScannedGenericBeanDefinition) {
          ScannedGenericBeanDefinition entity = (ScannedGenericBeanDefinition) candidate;
          String entityClassName = entity.getMetadata().getClassName();
          Class<DomainEntity<Serializable>> entityClass = getEntityClass(entityClassName);
          String beanName = StringUtils.uncapitalize(entityClass.getSimpleName()) + DAO_BEAN_SUFFIX;
          GenericBeanDefinition dao = new GenericBeanDefinition();
          if (daoWrapClass == null) {
            ConstructorArgumentValues values = new ConstructorArgumentValues();
            values.addIndexedArgumentValue(0, entityClass);
            dao.setBeanClass(DefaultGenericDao.class);
            dao.setConstructorArgumentValues(values);
            dao.setLazyInit(true);
            dao.getPropertyValues().add("entityManagerResolver", new RuntimeBeanReference(entityManagerResolverBeanName));
          } else {
            // ラップクラスを利用する場合
            try {
              GenericBeanDefinition implDao = new GenericBeanDefinition();
              implDao.setBeanClass(DefaultGenericDao.class);
              ConstructorArgumentValues implValues = new ConstructorArgumentValues();
              implValues.addIndexedArgumentValue(0, entityClass);
              implDao.setConstructorArgumentValues(implValues);
              implDao.setLazyInit(true);
              implDao.getPropertyValues().add("entityManagerResolver", new RuntimeBeanReference(entityManagerResolverBeanName));

              dao.setBeanClass(daoWrapClass);
              ConstructorArgumentValues values = new ConstructorArgumentValues();
              values.addIndexedArgumentValue(0, implDao);
              dao.setConstructorArgumentValues(values);
              dao.setLazyInit(true);
            } catch (BeanInstantiationException ex) {
              throw new RuntimeException(ex);
            }
          }
          registry.registerBeanDefinition(beanName, dao);
        }
      }
    }
  }

  /**
   * エンティティクラスを取得する
   * 
   * @param <T> エンティティクラス
   * @param <ID> エンティティIDクラス
   * @param entityClassName エンティティクラス名
   * @return エンティティクラス
   */
  @SuppressWarnings("unchecked")
  private <T extends DomainEntity<ID>, ID extends Serializable> Class<T> getEntityClass(String entityClassName) {
    try {
      return (Class<T>) ClassUtils.getClass(entityClassName);
    } catch (ClassNotFoundException ex) {
      throw new IllegalStateException(ex);
    }
  }

  /**
   * Dao Beanの接尾辞
   */
  private static final String DAO_BEAN_SUFFIX = "Dao";
}
