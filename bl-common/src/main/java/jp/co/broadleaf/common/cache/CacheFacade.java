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
package jp.co.broadleaf.common.cache;

/**
 * <pre>
 * キャッシュ管理Facadeインタフェース.
 * </pre>
 */
public interface CacheFacade {

  /**
   * <pre>
   * キャッシュ登録。
   * </pre>
   * 
   * @param cacheKey キャッシュキー
   * @param cacheVal キャッシュ値
   */
  void addCache(String cacheKey, Object cacheVal);

  /**
   * <pre>
   * キャッシュ取得。
   * </pre>
   * 
   * @param cacheKey キャッシュキー
   * @return キャッシュ値
   */
  Object getCache(String cacheKey);

}
