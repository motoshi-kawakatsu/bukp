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

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

import jp.co.broadleaf.framework.mail.MailClient;

/**
 * SendGridのサービスを利用してメールを送信する機能を提供するクラスです。
 * <p>
 * このクラスは、内部でSendGridのAPIを使用しているため、仕様についてはSendGrid APIに従います。<br>
 * SendGridを利用してメールを送信するには、以下の様なコードを記述します。 <blockquote>
 * 
 * <pre>
 * <code>
 * SendGridMailClientFactory factory = new SendGridMailClientFactory();
 * factory.setApikey("API Key");
 * MailClient client = factory.createInstance();
 * client.addTo("送信先メールアドレス");
 * client.setFrom("送信元メールアドレス");
 * client.setSubject("件名");
 * client.setText("メール本文");
 * client.send();
 * </code>
 * </pre>
 * 
 * </blockquote>
 * 
 * @author yoshizawa
 * @see jp.co.broadleaf.framework.mail.impl.SendGridMailClientFactory
 */
public class SendGridMailClient implements MailClient {
  /**
   * SendGrid
   */
  private SendGrid sendgrid;
  /**
   * email
   */
  private SendGrid.Email email;
  /**
   * mailCategory
   */
  private String mailCategory;
  /**
   * headers
   */
  private Map<String, String> headers;
  /**
   * ObjectMapper
   */
  private static final ObjectMapper MAPPER = new ObjectMapper();
  /**
   * Logger
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SendGridMailClient.class);

  /**
   * SendGridのユーザ名、パスワードを指定してSendGridMailClientオブジェクトを生成するコンストラクタです。
   * 
   * @param username SendGridへアクセスするユーザ名
   * @param password SendGridへアクセスするパスワード
   */
  public SendGridMailClient(String username, String password) {
    this.sendgrid = new SendGrid(username, password);
    this.email = new SendGrid.Email();
    this.headers = new HashMap<String, String>();
  }

  /**
   * SendGridのAPIキーを指定してSendGridMailClientオブジェクトを生成するコンストラクタです。
   * 
   * @param apikey SendGridのAPI Key
   */
  public SendGridMailClient(String apikey) {
    this.sendgrid = new SendGrid(apikey);
    this.email = new SendGrid.Email();
    this.headers = new HashMap<String, String>();
  }

  /**
   * メール送信先(To)を追加します。
   * 
   * @param value メール送信先(To)
   */
  @Override
  public void addToAddress(String value) {
    this.email.addTo(value);
  }

  /**
   * メール送信先(To)を取得します。
   * 
   * @return メール送信先のリスト(To)
   */
  @Override
  public String[] getToAddresses() {
    return this.email.getTos();
  }

  /**
   * メール送信先(Cc)を追加します。
   * 
   * @param value メール送信先(Cc)
   */
  @Override
  public void addCcAddress(String value) {
    this.email.addCc(value);
  }

  /**
   * メール送信先(Cc)を取得します。
   * 
   * @return メール送信先のリスト(Cc)
   */
  @Override
  public String[] getCcAddresses() {
    return this.email.getCcs();
  }

  /**
   * メール送信先(Bcc)を追加します。
   * 
   * @param value メール送信先(Bcc)
   */
  @Override
  public void addBccAddress(String value) {
    this.email.addBcc(value);
  }

  /**
   * メール送信先(Bcc)を取得します。
   * 
   * @return メール送信先のリスト(Bcc)
   */
  @Override
  public String[] getBccAddresses() {
    return this.email.getBccs();
  }

  /**
   * メール送信元(From)を設定します。
   * 
   * @param value メール送信元(From)
   */
  @Override
  public void setFrom(String value) {
    this.email.setFrom(value);
  }

  /**
   * メール送信元を取得します。
   * 
   * @return メール送信元(From)
   */
  @Override
  public String getFrom() {
    return this.email.getFrom();
  }

  /**
   * メール返信先(Reply-To)を設定します。
   * 
   * @param value メール返信先(Reply-To)
   */
  @Override
  public void setReplyTo(String value) {
    this.email.setReplyTo(value);
  }

  /**
   * メール返信先(Reply-To)を取得します。
   * 
   * @return メール返信先(Reply-To)
   */
  @Override
  public String getReplyTo() {
    return this.email.getReplyTo();
  }

  /**
   * メールの件名(Subject)を設定します。
   * 
   * @param value メールの件名(Subject)
   */
  @Override
  public void setSubject(String value) {
    this.email.setSubject(value);
  }

  /**
   * メールの件名(Subject)を取得します。
   * 
   * @return メールの件名(Subject)
   */
  @Override
  public String getSubject() {
    return this.email.getSubject();
  }

  /**
   * メールヘッダに任意のヘッダを追加します。
   * 
   * @param key 追加するメールヘッダのキー
   * @param value 追加するメールヘッダの値
   */
  @Override
  public void addHeader(String key, String value) {
    this.headers.put(key, value);
  }

  /**
   * メールヘッダに追加した任意のヘッダ情報を取得します。
   * 
   * @return 追加した任意のメールヘッダ情報を保持するMapオブジェクト
   */
  @Override
  public Map<String, String> getHeaders() {
    return this.headers;
  }

  /**
   * メールの種別(Category)を設定します。
   * 
   * @param value メールの種別(Category)
   */
  @Override
  public void setMailCategory(String value) {
    this.mailCategory = value;
  }

  /**
   * メールの種別(Category)を取得します。
   * 
   * @return メールの種別(Category)
   */
  @Override
  public String getMailCategory() {
    return this.mailCategory;
  }

  /**
   * メール本文を設定します。
   * 
   * @param value メール本文
   */
  @Override
  public void setText(String value) {
    this.email.setText(value);
  }

  /**
   * メール本文を取得します。
   * 
   * @return メール本文
   */
  @Override
  public String getText() {
    return this.email.getText();
  }

  /**
   * 設定されたパラメータを元に、メールを送信します。<br>
   * SendGridの仕様上、以下のパラメータを設定しない場合はRuntimeExceptionが発生します。<br>
   * <ul>
   * <li>送信元メールアドレス (From)</li>
   * <li>メール件名 (Subject)</li>
   * <li>メール本文 (Text)</li>
   * </ul>
   * なお、送信先メールアドレス(To, Cc, Bcc)が何れも設定されなかった場合は、送信元メールアドレス(From)へメールが送信されます。
   * <p>
   * メール送信時に、送信したメールの内容をログファイルに出力します。<br>
   * ログファイルはslf4jの定義に従って、以下のメッセージ内容で出力します。<br>
   * <blockquote>
   * 
   * <pre>
   * {
   *   "mailCategory" : (メールのカテゴリ),
   *   "from" : (送信元メールアドレス),
   *   "to" : [(送信先メールアドレス), ‥‥‥],
   *   "cc" : [(送信先メールアドレス), ‥‥‥],
   *   "bcc" : [(送信先メールアドレス), ‥‥‥],
   *   "subject" : (メールの件名),
   *   "header" : {(メールヘッダのキー) : (メールヘッダの値), ‥‥‥},
   *   "text" : (メールの本文),
   *   "result" : (メール送信に成功した場合は "TRUE"、失敗した場合は "FALSE"),
   *   "resultMessage" : (メール送信に失敗した場合のメッセージ)
   * }
   * </pre>
   * 
   * </blockquote>
   * 
   * @throws RuntimeException SendGridへのメール送信に失敗した場合はこのエラーを返します。 エラーの原因は
   *           {@link RuntimeException#getMessage()}から取得します。
   * @see jp.co.broadleaf.framework.mail.MailClient#send()
   */
  @Override
  public void send() throws RuntimeException {
    // 任意ヘッダを設定
    this.headers.forEach((key, value) -> this.email.addHeader(key, value));

    // ログ出力用クラスの生成
    MailLogData log_data = new MailLogData();
    log_data.setFrom(this.email.getFrom());
    log_data.setTo(this.email.getTos());
    log_data.setCc(this.email.getCcs());
    log_data.setBcc(this.email.getBccs());
    log_data.setSubject(this.email.getSubject());
    log_data.setHeader(this.headers);
    log_data.setText(this.email.getText());
    log_data.setResult(true);

    // メールを送信する
    // メールを送信する
    SendGrid.Response response = null;
    try {
      response = sendgrid.send(this.email);
      // APIのレスポンスから、送信できなかった場合は例外を返す
      if (response == null || !response.getStatus()) {
        log_data.setResult(false);
        log_data.setResultMessage(response.getMessage());
        throw new RuntimeException(response.getMessage());
      }
    } catch (SendGridException ex) {
      log_data.setResult(false);
      log_data.setResultMessage(ex.getMessage());
      throw new RuntimeException(ex);
    } catch (RuntimeException ex) {
      log_data.setResult(false);
      log_data.setResultMessage(ex.getMessage());
      throw ex;
    } finally {
      try {
        String log_msg = MAPPER.writeValueAsString(log_data);
        LOGGER.info(log_msg);
      } catch (JsonProcessingException e) {
        /* Ignore */ }
    }
  }

  /**
   * <pre>
   * MailLogData
   * </pre>
   */
  @SuppressWarnings("unused")
  private static class MailLogData {
    /** mailCategory **/
    private String mailCategory;
    /** from **/
    private String from;
    /** to **/
    private String[] to;
    /** cc **/
    private String[] cc;
    /** bcc **/
    private String[] bcc;
    /** subject **/
    private String subject;
    /** header **/
    private Map<String, String> header;
    /** text **/
    private String text;
    /** result **/
    private boolean result;
    /** resultMessage **/
    private String resultMessage;

    /**
     * <pre>
     * 【mailCategory】を取得する。
     * </pre>
     *
     * @return 【mailCategory】
     */
    public String getMailCategory() {
      return mailCategory;
    }

    /**
     * <pre>
     * 【mailCategory】を設定する。
     * </pre>
     *
     * @param mailCategory 【mailCategory】
     */
    public void setMailCategory(String mailCategory) {
      this.mailCategory = mailCategory;
    }

    /**
     * <pre>
     * 【from】を取得する。
     * </pre>
     *
     * @return 【from】
     */
    public String getFrom() {
      return from;
    }

    /**
     * <pre>
     * 【from】を設定する。
     * </pre>
     *
     * @param from 【from】
     */
    public void setFrom(String from) {
      this.from = from;
    }

    /**
     * <pre>
     * 【to】を取得する。
     * </pre>
     *
     * @return 【to】
     */
    public String[] getTo() {
      return to;
    }

    /**
     * <pre>
     * 【to】を設定する。
     * </pre>
     *
     * @param to 【to】
     */
    public void setTo(String[] to) {
      this.to = to;
    }

    /**
     * <pre>
     * 【cc】を取得する。
     * </pre>
     *
     * @return 【cc】
     */
    public String[] getCc() {
      return cc;
    }

    /**
     * <pre>
     * 【cc】を設定する。
     * </pre>
     *
     * @param cc 【cc】
     */
    public void setCc(String[] cc) {
      this.cc = cc;
    }

    /**
     * <pre>
     * 【bcc】を取得する。
     * </pre>
     *
     * @return 【bcc】
     */
    public String[] getBcc() {
      return bcc;
    }

    /**
     * <pre>
     * 【bcc】を設定する。
     * </pre>
     *
     * @param bcc 【bcc】
     */
    public void setBcc(String[] bcc) {
      this.bcc = bcc;
    }

    /**
     * <pre>
     * 【subject】を取得する。
     * </pre>
     *
     * @return 【subject】
     */
    public String getSubject() {
      return subject;
    }

    /**
     * <pre>
     * 【subject】を設定する。
     * </pre>
     *
     * @param subject 【subject】
     */
    public void setSubject(String subject) {
      this.subject = subject;
    }

    /**
     * <pre>
     * 【header】を取得する。
     * </pre>
     *
     * @return 【header】
     */
    public Map<String, String> getHeader() {
      return header;
    }

    /**
     * <pre>
     * 【header】を設定する。
     * </pre>
     *
     * @param header 【header】
     */
    public void setHeader(Map<String, String> header) {
      this.header = header;
    }

    /**
     * <pre>
     * 【text】を取得する。
     * </pre>
     *
     * @return 【text】
     */
    public String getText() {
      return text;
    }

    /**
     * <pre>
     * 【text】を設定する。
     * </pre>
     *
     * @param text 【text】
     */
    public void setText(String text) {
      this.text = text;
    }

    /**
     * <pre>
     * 【result】を取得する。
     * </pre>
     *
     * @return 【result】
     */
    public boolean isResult() {
      return result;
    }

    /**
     * <pre>
     * 【result】を設定する。
     * </pre>
     *
     * @param result 【result】
     */
    public void setResult(boolean result) {
      this.result = result;
    }

    /**
     * <pre>
     * 【resultMessage】を取得する。
     * </pre>
     *
     * @return 【resultMessage】
     */
    public String getResultMessage() {
      return resultMessage;
    }

    /**
     * <pre>
     * 【resultMessage】を設定する。
     * </pre>
     *
     * @param resultMessage 【resultMessage】
     */
    public void setResultMessage(String resultMessage) {
      this.resultMessage = resultMessage;
    }

  }

}
