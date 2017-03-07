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
package jp.co.broadleaf.common.cache.impl;

import jp.co.broadleaf.common.cache.CacheService;

import org.apache.commons.lang3.Validate;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * <pre>
 * キャッシュサービスクラス.
 * </pre>
 */
public class CacheServiceImpl implements CacheService {

  /**
   * <pre>
   * キャッシュ登録。
   * </pre>
   * 
   * @param cacheKey キャッシュキー
   * @param cacheVal キャッシュ値
   */
  @Override
  public void addCache(String cacheKey, Object cacheVal) {

    Validate.notEmpty(cacheKey, "cacheKey must not be empty");

    redisTemplate.boundValueOps(cacheKey).set(cacheVal);
  }

  /**
   * <pre>
   * キャッシュ取得。
   * </pre>
   * 
   * @param cacheKey キャッシュキー
   * @return キャッシュ値
   */
  @Override
  public Object getCache(String cacheKey) {

    Validate.notEmpty(cacheKey, "cacheKey must not be empty");

    return redisTemplate.boundValueOps(cacheKey).get();
  }

  /**
   * Redisテンプレート
   */
  private RedisTemplate<String, Object> redisTemplate;

  /**
   * Redisテンプレートを設定する。
   *
   * @param redisTemplate Redisテンプレート
   */
  @Resource
  public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

}
