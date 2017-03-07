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
 * BL キャッシュ用キーの規範定義用クラスです。
 * キーの規範:
 *  SCHEMA:IDENTIFER:PROPERTY
 * 例：
 *  user:1  IDが１のユーザオブジェクト
 *  user:1:name　IDが１のユーザ名
 *  
 * キーの取得はCacheKeyUtilsを利用してください。
 * </pre>
 */
public abstract class CacheKeySpecs {

  /**
   * スキーマ区きり文字
   */
  public static final String ELEMENT_SEPARATOR = ":";

  /**
   * キー区きり文字
   */
  public static final String IDENTIFIER_SEPARATOR = "_";

  /**
   * メッセージ用のスキーマ
   */
  public static final String MESSAGE_SCHEMA = "message";

  /**
   * プロパティ用のスキーマ
   */
  public static final String PROPERTY_SCHEMA = "property";

  /**
   * ログイン済みユーザ用のスキーマ
   */
  public static final String LOGIN_SCHEMA = "login";
 
  /**
   * ダブルsubmitの防ぐ用のスキーマ
   */
  public static final String SUBMISSION_SCHEMA = "submission";
}
