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
package jp.co.broadleaf.framework.bean;

/**
 * <pre>
 * Bean工場クラスです。
 * </pre>
 */
public interface BeanFactory {

  /**
   * Beanのクラスより、Beanを取得する。
   * 
   * @param <T> Beanクラス
   * @param requiredBeanClass Beanクラス
   * @return Bean
   */
  <T> T getBean(Class<T> requiredBeanClass);

  /**
   * Beanクラス名より、Beanを取得する。
   * 
   * @param <T> Beanクラス
   * @param beanName Bean名
   * @param requiredBeanClass Beanクラス
   * @return Bean
   */
  <T> T getBean(String beanName, Class<T> requiredBeanClass);
}
