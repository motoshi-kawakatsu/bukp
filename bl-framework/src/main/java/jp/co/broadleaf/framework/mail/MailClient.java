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
package jp.co.broadleaf.framework.mail;

import java.util.Map;

/**
 * メール送信機能を提供するクラスのインターフェース定義です。
 * 
 * @author yoshizawa
 */
public interface MailClient {
  /**
   * メール送信先(To)を追加します。
   * 
   * @param value メール送信先(To)
   */
  void addToAddress(String value);

  /**
   * メール送信先(To)を取得します。
   * 
   * @return メール送信先のリスト(To)
   */
  String[] getToAddresses();

  /**
   * メール送信先(Cc)を追加します。
   * 
   * @param value メール送信先(Cc)
   */
  void addCcAddress(String value);

  /**
   * メール送信先(Cc)を取得します。
   * 
   * @return メール送信先のリスト(Cc)
   */
  String[] getCcAddresses();

  /**
   * メール送信先(Bcc)を追加します。
   * 
   * @param value メール送信先(Bcc)
   */
  void addBccAddress(String value);

  /**
   * メール送信先(Bcc)を取得します。
   * 
   * @return メール送信先のリスト(Bcc)
   */
  String[] getBccAddresses();

  /**
   * メール送信元(From)を設定します。
   * 
   * @param value メール送信元(From)
   */
  void setFrom(String value);

  /**
   * メール送信元を取得します。
   * 
   * @return メール送信元(From)
   */
  String getFrom();

  /**
   * メール返信先(Reply-To)を設定します。
   * 
   * @param value メール返信先(Reply-To)
   */
  void setReplyTo(String value);

  /**
   * メール返信先(Reply-To)を取得します。
   * 
   * @return メール返信先(Reply-To)
   */
  String getReplyTo();

  /**
   * メールの件名(Subject)を設定します。
   * 
   * @param value メールの件名(Subject)
   */
  void setSubject(String value);

  /**
   * メールの件名(Subject)を取得します。
   * 
   * @return メールの件名(Subject)
   */
  String getSubject();

  /**
   * メールヘッダに任意のヘッダを追加します。
   * 
   * @param key 追加するメールヘッダのキー
   * @param value 追加するメールヘッダの値
   */
  void addHeader(String key, String value);

  /**
   * メールヘッダに追加した任意のヘッダ情報を取得します。
   * 
   * @return 追加した任意のメールヘッダ情報を保持するMapオブジェクト
   */
  Map<String, String> getHeaders();

  /**
   * メールの種別(Category)を設定します。
   * 
   * @param value メールの種別(Category)
   */
  void setMailCategory(String value);

  /**
   * メールの種別(Category)を取得します。
   * 
   * @return メールの種別(Category)
   */
  String getMailCategory();

  /**
   * メール本文を設定します。
   * 
   * @param value メール本文
   */
  void setText(String value);

  /**
   * メール本文を取得します。
   * 
   * @return メール本文
   */
  String getText();

  /**
   * 設定されたパラメータを元に、メールを送信します。
   * 
   * @throws RuntimeException メールの送信に失敗した場合、この例外を発生します。
   *           この例外を返すのは、「メールサーバへの送信に失敗した」場合のみであり、
   *           実際にメールが宛先に送信できたかどうかについては管理しません。
   */
  void send() throws RuntimeException;
}
