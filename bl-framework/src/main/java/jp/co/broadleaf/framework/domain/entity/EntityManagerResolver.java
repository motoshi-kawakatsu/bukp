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

import java.io.Serializable;

/**
 * エンティティマネージャ取得用のインタフェース
 */
public interface EntityManagerResolver {

  /**
   * エンティティマネージャを取得する
   * 
   * @param <T> エンティティクラス
   * @param <ID> エンティティIDクラス
   * @param entityClass エンティティクラス
   * @return エンティティマネージャ
   */
  <T extends DomainEntity<ID>, ID extends Serializable> DomainEntityManager<T, ID> getEntityManager(Class<T> entityClass);
}
