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
package jp.co.broadleaf.framework.exception;

/**
 * 検証例外用のフィールドエラークラス
 */
public class FieldError {

  /**
   * フィールド
   */
  private String field;

  /**
   * エラーコード
   */
  private String errorCode;

  /**
   * エラーのパラメータ
   */
  private String[] errorArguments;

  /**
   * デフォルトメッセージ
   */
  private String defaultMessage;

  /**
   * コンストラクタ
   * 
   * @param field フィールド
   * @param errorCode エラーコード
   */
  public FieldError(String field, String errorCode) {
    this.field = field;
    this.errorCode = errorCode;
    this.errorArguments = new String[0];
  }

  /**
   * コンストラクタ
   * 
   * @param field フィールド
   * @param errorCode エラーコード
   * @param errorArgument エラーのパラメータ
   */
  public FieldError(String field, String errorCode, String errorArgument) {
    this.field = field;
    this.errorCode = errorCode;
    this.errorArguments = new String[] { errorArgument };
  }

  /**
   * コンストラクタ
   * 
   * @param field フィールド
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   */
  public FieldError(String field, String errorCode, String[] errorArguments) {
    this.field = field;
    this.errorCode = errorCode;
    this.errorArguments = errorArguments;
  }

  /**
   * コンストラクタ
   * 
   * @param field フィールド
   * @param errorCode エラーコード
   * @param errorArgument エラーのパラメータ
   * @param defaultMessage デフォルトメッセージ
   */
  public FieldError(String field, String errorCode, String errorArgument, String defaultMessage) {
    this.field = field;
    this.errorCode = errorCode;
    this.errorArguments = new String[] { errorArgument };
    this.defaultMessage = defaultMessage;
  }

  /**
   * コンストラクタ
   * 
   * @param field フィールド
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @param defaultMessage デフォルトメッセージ
   */
  public FieldError(String field, String errorCode, String[] errorArguments, String defaultMessage) {
    this.field = field;
    this.errorCode = errorCode;
    this.errorArguments = errorArguments;
    this.defaultMessage = defaultMessage;
  }

  /**
   * フィールドを取得する
   * 
   * @return フィールド
   */
  public String getField() {
    return field;
  }

  /**
   * エラーコードを取得する
   * 
   * @return エラーコード
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * エラーのパラメータを取得する エラーのパラメータ
   * 
   * @return エラーのパラメータ
   */
  public String[] getErrorArguments() {
    return errorArguments;
  }

  /**
   * デフォルトメッセージを取得する
   * 
   * @return デフォルトメッセージ
   */
  public String getDefaultMessage() {
    return defaultMessage;
  }
}
