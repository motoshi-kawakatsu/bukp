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

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

/**
 * 共通DaoBeanスキャナの構成クラスです。
 */
public class GenericDaoScannerConfigurer implements BeanDefinitionRegistryPostProcessor, InitializingBean {

  /**
   * ベースパッケージ
   */
  private String basePackage;

  /**
   * Daoのラップクラス
   */
  private String daoWrapClass;

  /**
   * エンティティマネージャリゾルバ
   */
  private String entityManagerResolverBeanName;

  /**
   * ベースパッケージを設定する。
   * 
   * @param basePackage ベースパッケージ
   */
  public void setBasePackage(String basePackage) {
    this.basePackage = basePackage;
  }

  /**
   * Daoのラップクラスを設定する。
   * 
   * @param daoWrapClass Daoのラップクラス
   */
  public void setDaoWrapClass(String daoWrapClass) {
    this.daoWrapClass = daoWrapClass;
  }

  /**
   * エンティティマネージャリゾルバを設定する。
   *
   * @param entityManagerResolverBeanName エンティティマネージャリゾルバ
   */
  public void setEntityManagerResolverBeanName(String entityManagerResolverBeanName) {
    this.entityManagerResolverBeanName = entityManagerResolverBeanName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
    // left intentionally blank
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    if (this.basePackage == null) {
      throw new IllegalArgumentException("Property 'basePackage' is required");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
    if (entityManagerResolverBeanName == null) {
      throw new IllegalArgumentException("entityManagerResolverBeanName must be not null");
    }
    Class<?> daoWrapClazz = null;
    if (daoWrapClass != null) {
      daoWrapClazz = getClassForName(daoWrapClass);
    }
    GenericDaoScanner scanner = new GenericDaoScanner(registry, daoWrapClazz, entityManagerResolverBeanName);
    scanner.scan(
        StringUtils.tokenizeToStringArray(this.basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
  }

  /**
   * クラス名よりクラスを取得する。
   * 
   * @param className クラス名
   * @return クラス
   */
  private Class<?> getClassForName(String className) {
    if (className == null) {
      return null;
    }
    try {
      return Class.forName(className);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("the class not be found");
    }
  }
}
