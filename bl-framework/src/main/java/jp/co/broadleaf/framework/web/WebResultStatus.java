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
package jp.co.broadleaf.framework.web;

/**
 * WebAPIの結果ステータス定義クラス
 */
public abstract class WebResultStatus {

  /**
   * 正常終了、エラーなし
   */
  public static final int NO_ERROR = 0;

  /**
   * 入力検証エラー
   */
  public static final int INPUT_ERROR = 1;

  /**
   * ビジネスエラー
   */
  public static final int BUSINESS_ERROR = 2;

  /**
   * システムエラー (予想外エラー)
   */
  public static final int SYSTEM_ERROR = 3;

  /**
   * リクエストエラー
   */
  public static final int REQUEST_ERROR = 4;
  
  /**
   * 未ログインエラー
   */
  public static final int NOTLOGIN_ERROR = 5;
  
  /**
   * 権限なしエラー
   */
  public static final int NOPERMISSION_ERROR = 6;
}
