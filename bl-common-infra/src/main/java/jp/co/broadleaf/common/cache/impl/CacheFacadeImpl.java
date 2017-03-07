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

import jp.co.broadleaf.common.cache.CacheFacade;
import jp.co.broadleaf.common.cache.CacheService;

import org.apache.commons.lang3.Validate;

import javax.annotation.Resource;

/**
 * <pre>
 * キャッシュ管理Facadeクラス.
 * </pre>
 */
public class CacheFacadeImpl implements CacheFacade {

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

    cacheService.addCache(cacheKey, cacheVal);
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

    return cacheService.getCache(cacheKey);
  }

  /**
   * キャッシュサービス
   */
  private CacheService cacheService;

  /**
   * キャッシュサービスを設定する。
   *
   * @param cacheService キャッシュサービス
   */
  @Resource
  public void setCacheService(CacheService cacheService) {
    this.cacheService = cacheService;
  }

}
