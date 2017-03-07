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
 * <pre>
 * システム例外
 * ビジネス層(Facade以降）で、ユーザが復旧不可能なエラーが発生した場合に利用する。
 * 対応するメッセージＩＤおよび、元例外をコンストラクタでセットする。
 * </pre>
 */
public class SystemException extends RuntimeException {

  /** serialVersionUID */
  private static final long serialVersionUID = -7667676880603289215L;

  /**
   * エラーコード
   */
  private String errorCode;

  /**
   * エラーのパラメータ
   */
  private String[] errorArguments;

  /**
   * コンストラクタ
   * 
   * @param cause エラーの原因
   */
  public SystemException(Throwable cause) {
    super(cause);
  }

  /**
   * コンストラクタ
   * 
   * @param errorCode エラーコード
   */
  public SystemException(String errorCode) {
    this(errorCode, null, null);
  }

  /**
   * コンストラクタ
   * 
   * @param errorCode エラーコード
   * @param errorArgument エラーのパラメータ
   */
  public SystemException(String errorCode, String errorArgument) {
    this(errorCode, new String[] { errorArgument }, null);
  }

  /**
   * コンストラクタ
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   */
  public SystemException(String errorCode, String[] errorArguments) {
    this(errorCode, errorArguments, null);
  }

  /**
   * コンストラクタ
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @param cause エラーの原因
   */
  public SystemException(String errorCode, String[] errorArguments, Throwable cause) {
    super(cause);
    this.errorCode = errorCode;
    this.errorArguments = errorArguments;
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
   * エラーのパラメータを取得する
   * 
   * @return エラーのパラメータ
   */
  public String[] getErrorArguments() {
    return errorArguments;
  }
}
