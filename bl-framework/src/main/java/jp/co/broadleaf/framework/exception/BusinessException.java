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
 * ビジネス例外クラスです。
 * ビジネス層（Facade以降）でエラー（業務チェックなどで問題、後続処理が続行不可）となった場合に利用する。
 * 対応するメッセージＩＤをコンストラクタで付与する。
 * </pre>
 */
public class BusinessException extends RuntimeException {

  /** serialVersionUID */
  private static final long serialVersionUID = 4227523612125538908L;

  /** エラーコード */
  private String errorCode;

  /** エラーのパラメータ */
  private String[] errorArguments;

  /**
   * コンストラクタ
   * 
   * @param errorCode エラーコード
   */
  public BusinessException(String errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * コンストラクタ
   * 
   * @param errorCode エラーコード
   * @param errorArgument エラーのパラメータ
   */
  public BusinessException(String errorCode, String errorArgument) {
    this.errorCode = errorCode;
    this.errorArguments = new String[] { errorArgument };
  }

  /**
   * コンストラクタ
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   */
  public BusinessException(String errorCode, String[] errorArguments) {
    this.errorCode = errorCode;
    this.errorArguments = errorArguments;
  }

  /**
   * エラーコードを設定する
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
