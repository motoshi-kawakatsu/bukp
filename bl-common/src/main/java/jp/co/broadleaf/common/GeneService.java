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
package jp.co.broadleaf.common;

import jp.co.broadleaf.framework.domain.entity.DomainEntity;

import java.io.Serializable;

/**
 * <pre>
 * 汎用論理と物理削除サービスインタフェース。
 * </pre>
 */
public interface GeneService {

  /**
   * 汎用論理削除（排他制御あり）。
   * 
   * @param <T> エンティティクラス
   * @param <ID> エンティティIDクラス
   * @param entity エンティティ（主キーと更新日時だけを設定必要）
   */
  <T extends DomainEntity<ID>, ID extends Serializable> void deleteLogically(T entity);

  /**
   * 汎用物理削除（排他制御あり）。
   * 
   * @param <T> エンティティクラス
   * @param <ID> エンティティIDクラス
   * @param entity エンティティ（主キーと更新日時だけを設定必要）
   */
  <T extends DomainEntity<ID>, ID extends Serializable> void delete(T entity);

  /**
   * <pre>
   * 汎用論理削除（排他制御なし））。
   * </pre>
   * 
   * @param <T> エンティティクラス
   * @param <ID> エンティティIDクラス
   * @param entity エンティティ
   */
  <T extends DomainEntity<ID>, ID extends Serializable> void deleteLogicallyWithoutExclusive(T entity);

  /**
   * <pre>
   * 汎用物理削除（排他制御なし））。
   * </pre>
   * 
   * @param <T> エンティティクラス
   * @param <ID> エンティティIDクラス
   * @param entity エンティティ
   */
  <T extends DomainEntity<ID>, ID extends Serializable> void deleteWithoutExclusive(T entity);

}
