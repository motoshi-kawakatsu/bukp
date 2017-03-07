//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : xuxx
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.common.form;

/**
 * クライアントエラーログ書込みFormのクラス。のクラス。
 */
public class ClientErrorInfoForm {

  /**
   * メーカーコード
   */
  private String makerCode;

  /**
   * ログインID
   */
  private String loginId;

  /**
   * クライアントエラー発生日時
   */
  private String clientErrorTime;

  /**
   * サーバ受信日時
   */
  private String serverReceiveTime;

  /**
   * ドメインURL
   */
  private String domain;

  /**
   * リクエストパス
   */
  private String requestPath;

  /**
   * リファラ
   */
  private String referrer;

  /**
   * ユーザエージェント
   */
  private String userAgent;

  /**
   * エラーコード
   */
  private String errorCode;

  /**
   * エラーメッセージ
   */
  private String errorMessage;

  /**
   * エラー名
   */
  private String errorName;

  /**
   * エラー発生ファイル名
   */
  private String errorFileName;

  /**
   * エラー発生ライン番号
   */
  private String errorLineNumber;

  /**
   * エラースタックトレース
   */
  private String errorStack;

  /**
   * メーカーコードの取得。
   *
   * @return String メーカーコード
   */
  public String getMakerCode() {
      return makerCode;
  }

  /**
   * ログインIDの取得。
   *
   * @return String ログインID
   */
  public String getLoginId() {
      return loginId;
  }

  /**
   * クライアントエラー発生日時の取得。
   *
   * @return String クライアントエラー発生日時
   */
  public String getClientErrorTime() {
    return clientErrorTime;
  }

  /**
   * サーバ受信日時の取得。
   *
   * @return String サーバ受信日時
   */
  public String getServerReceiveTime() {
    return serverReceiveTime;
  }

  /**
   * ドメインURLの取得。
   *
   * @return String ドメインURL
   */
  public String getDomain() {
    return domain;
  }

  /**
   * リクエストパスの取得。
   *
   * @return String リクエストパス
   */
  public String getRequestPath() {
    return requestPath;
  }

  /**
   * リファラの取得。
   *
   * @return String リファラ
   */
  public String getReferrer() {
    return referrer;
  }

  /**
   * ユーザエージェントの取得。
   *
   * @return String ユーザエージェント
   */
  public String getUserAgent() {
    return userAgent;
  }

  /**
   * エラーコードの取得。
   *
   * @return String エラーコード
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * エラーメッセージの取得。
   *
   * @return String エラーメッセージ
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * エラー名の取得。
   *
   * @return String エラー名
   */
  public String getErrorName() {
    return errorName;
  }

  /**
   * エラー発生ファイル名の取得。
   *
   * @return String エラー発生ファイル名
   */
  public String getErrorFileName() {
    return errorFileName;
  }

  /**
   * エラー発生ライン番号の取得。
   *
   * @return String エラー発生ライン番号
   */
  public String getErrorLineNumber() {
    return errorLineNumber;
  }

  /**
   * エラースタックトレースの取得。
   *
   * @return String エラースタックトレース
   */
  public String getErrorStack() {
    return errorStack;
  }

  /**
   * メーカーコードの設定。
   *
   * @param makerCode メーカーコード
   */
  public void setMakerCode(String makerCode) {
      this.makerCode = makerCode;
  }

  /**
   * ログインIDの設定。
   *
   * @param loginId ログインID
   */
  public void setLoginId(String loginId) {
      this.loginId = loginId;
  }

  /**
   * クライアントエラー発生日時の設定。
   *
   * @param clientErrorTime クライアントエラー発生日時
   */
  public void setClientErrorTime(String clientErrorTime) {
    this.clientErrorTime = clientErrorTime;
  }

  /**
   * サーバ受信日時の設定。
   *
   * @param serverReceiveTime サーバ受信日時
   */
  public void setServerReceiveTime(String serverReceiveTime) {
    this.serverReceiveTime = serverReceiveTime;
  }

  /**
   * ドメインURLの設定。
   *
   * @param domain ドメインURL
   */
  public void setDomain(String domain) {
    this.domain = domain;
  }

  /**
   * リクエストパスの設定。
   *
   * @param requestPath リクエストパス
   */
  public void setRequestPath(String requestPath) {
    this.requestPath = requestPath;
  }

  /**
   * リファラの設定。
   *
   * @param referrer リファラ
   */
  public void setReferrer(String referrer) {
    this.referrer = referrer;
  }

  /**
   * ユーザエージェントの設定。
   *
   * @param userAgent ユーザエージェント
   */
  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  /**
   * エラーコードの設定。
   *
   * @param errorCode エラーコード
   */
  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  /**
   * エラーメッセージの設定。
   *
   * @param errorMessage エラーメッセージ
   */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * エラー名の設定。
   *
   * @param errorName エラー名
   */
  public void setErrorName(String errorName) {
    this.errorName = errorName;
  }

  /**
   * エラー発生ファイル名の設定。
   *
   * @param errorFileName エラー発生ファイル名
   */
  public void setErrorFileName(String errorFileName) {
    this.errorFileName = errorFileName;
  }

  /**
   * エラー発生ライン番号の設定。
   *
   * @param errorLineNumber エラー発生ライン番号
   */
  public void setErrorLineNumber(String errorLineNumber) {
    this.errorLineNumber = errorLineNumber;
  }

  /**
   * エラースタックトレースの設定。
   *
   * @param errorStack エラースタックトレース
   */
  public void setErrorStack(String errorStack) {
    this.errorStack = errorStack;
  }

}
