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
package jp.co.broadleaf.framework.domain.entity;

/**
 * 楽観的排他制御用ヘルプクラスです。
 */
public abstract class OptimisticConcurrencyHelper {

  /**
   * 楽観的排他制御必要かどうかことを判断する。
   * 
   * @param entityClass エンティティクラス
   * @return 判断結果
   */
  public static boolean hasOptimisticConcurrency(Class<?> entityClass) {
    return getOptimisticConcurrencyResolverClass(entityClass) != null;
  }

  /**
   * 楽観的排他制御用オブジェクトを取得する。
   * 
   * @param <T> 楽観的排他制御用リゾルバクラス
   * @param entityClass エンティティクラス
   * @return 楽観的排他制御用オブジェクト
   */
  @SuppressWarnings("unchecked")
  public static <T extends OptimisticConcurrencyResolver> T getOptimisticConcurrencyResolver(Class<?> entityClass) {
    try {
      Class<?> resolverClass = getOptimisticConcurrencyResolverClass(entityClass);
      if (resolverClass == null) {
        return null;
      }
      OptimisticConcurrencyResolver resolver = (OptimisticConcurrencyResolver) resolverClass.newInstance();
      return (T) resolver;
    } catch (InstantiationException | IllegalAccessException ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * 楽観的排他制御用リゾルバクラスを取得する。
   * 
   * @param entityClass エンティティクラス
   * @return 楽観的排他制御用リゾルバクラス
   */
  private static Class<?> getOptimisticConcurrencyResolverClass(Class<?> entityClass) {
    OptimisticConcurrency optimisticConcurrency = entityClass.getAnnotation(OptimisticConcurrency.class);
    if (optimisticConcurrency == null) {
      return null;
    }
    Class<?> resolverClass = optimisticConcurrency.resolver();
    if (resolverClass == null) {
      throw new IllegalArgumentException("OptimisticConcurrency's resolver must not be null");
    }
    if (!OptimisticConcurrencyResolver.class.isAssignableFrom(resolverClass)) {
      throw new IllegalArgumentException(
          "OptimisticConcurrency's resolver must be the implement class of OptimisticConcurrencyResolver");
    }
    return resolverClass;
  }

}
