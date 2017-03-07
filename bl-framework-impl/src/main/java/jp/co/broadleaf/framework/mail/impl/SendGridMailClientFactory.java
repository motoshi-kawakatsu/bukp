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
package jp.co.broadleaf.framework.mail.impl;

import jp.co.broadleaf.framework.mail.MailClient;
import jp.co.broadleaf.framework.mail.MailClientFactory;

/**
 * SendGridサービスを利用したメール送信機能オブジェクトを生成するFactoryクラスです。
 * このクラスのオブジェクトはSendGridMailClientオブジェクトを生成します。
 * <p>
 * SendGridMailClientオブジェクトを生成する際、
 * <ul>
 * <li>APIキー</li>
 * <li>ユーザ名とパスワード</li>
 * </ul>
 * の何れかを設定しない場合、オブジェクト生成時にIllegalStateExceptionが発生します。
 * <p>
 * SendGridMailClientオブジェクトの生成手順は、以下のとおりです。
 * <p>
 * ●APIキーを利用して生成する <blockquote>
 * 
 * <pre>
 * <code>
 * SendGridMailClientFactory factory = new SendGridMailClientFactory();
 * factory.setApikey("API Key");
 * MailClient client = factory.createInstance();
 * </code>
 * </pre>
 * 
 * </blockquote>
 * <p>
 * ●ユーザ名とパスワードを利用して生成する <blockquote>
 * 
 * <pre>
 * <code>
 * SendGridMailClientFactory factory = new SendGridMailClientFactory();
 * factory.setUsername("User Name");
 * factory.setPassword("Password");
 * MailClient client = factory.createInstance();
 * </code>
 * </pre>
 * 
 * </blockquote>
 * 
 * @author yoshizawa
 */
public class SendGridMailClientFactory implements MailClientFactory {
  /** username **/
  private String username;
  /** password **/
  private String password;
  /** username **/
  private String apikey;

  /**
   * メールを送信するMailClientオブジェクトを生成します。
   * 
   * @return メールを送信するMailClientオブジェクト
   * @throws IllegalStateException インスタンス生成時に必要なパラメータが設定されていない場合、この例外を返します。
   */
  @Override
  public MailClient createInstance() throws IllegalStateException {
    if ((this.apikey != null) && !this.apikey.isEmpty())
      return new SendGridMailClient(apikey);

    if ((this.username != null) && !this.username.isEmpty() && (this.password != null) && !this.password.isEmpty())
      return new SendGridMailClient(username, password);
    throw new IllegalStateException();
  }

  /**
   * 設定されたSendGridサービス利用のユーザ名を取得します。
   * 
   * @return 設定されたSendGridサービスのユーザ名
   */
  public String getUsername() {
    return username;
  }

  /**
   * SendGridサービス利用時のユーザ名を設定します。
   * 
   * @param username SendGridサービスのユーザ名
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * 設定されたSendGridサービス利用のパスワードを取得します。
   * 
   * @return 設定されたSendGridサービスのパスワード
   */
  public String getPassword() {
    return password;
  }

  /**
   * SendGridサービス利用時のパスワードを設定します。
   * 
   * @param password SendGridサービスのパスワード
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * 設定されたSendGridサービス利用のAPIキーを取得します。
   * 
   * @return 設定されたSendGridサービスのAPIキー
   */
  public String getApikey() {
    return apikey;
  }

  /**
   * SendGridサービス利用時のAPIキーを設定します。
   * 
   * @param apikey SendGridサービスのAPIキー
   */
  public void setApikey(String apikey) {
    this.apikey = apikey;
  }

}
