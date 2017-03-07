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
package jp.co.broadleaf.framework.bean.impl;

import jp.co.broadleaf.framework.bean.BeanFactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * <pre>
 * SpringのBean工場クラスです。
 * </pre>
 */
public class SpringBeanFactory implements BeanFactory, BeanFactoryAware {

  /**
   * SpringのBean工場
   */
  private org.springframework.beans.factory.BeanFactory springBeanFactory;

  /**
   * {@inheritDoc}
   */
  @Override
  public void setBeanFactory(org.springframework.beans.factory.BeanFactory beanFactory) throws BeansException {
    this.springBeanFactory = beanFactory;
  }

  /**
   * Beanのクラスより、Beanを取得する。
   * 
   * @param <T> Beanクラス
   * @param requiredBeanClass Beanクラス
   * @return Bean
   */
  @Override
  public <T> T getBean(Class<T> requiredBeanClass) {
    return springBeanFactory.getBean(requiredBeanClass);
  }

  /**
   * Beanクラス名より、Beanを取得する。
   * 
   * @param <T> Beanクラス
   * @param beanName Bean名
   * @param requiredBeanClass Beanクラス
   * @return Bean
   */
  @Override
  public <T> T getBean(String beanName, Class<T> requiredBeanClass) {
    return springBeanFactory.getBean(beanName, requiredBeanClass);
  }

}
