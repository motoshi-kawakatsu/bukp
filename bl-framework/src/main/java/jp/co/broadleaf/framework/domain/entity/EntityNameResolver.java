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
 * エンティティの名称リゾルバ
 */
public interface EntityNameResolver {

  /**
   * エンティティ名を取得する
   * 
   * @param entityClass エンティティクラス
   * @return エンティティ名
   */
  String getEntityName(Class<?> entityClass);

  /**
   * エンティティのプロパティ名を取得する
   * 
   * @param entityClass エンティティクラス
   * @param propertyName プロパティ名
   * @return エンティティのプロパティ名
   */
  String getEntityPropertyName(Class<?> entityClass, String propertyName);
}
