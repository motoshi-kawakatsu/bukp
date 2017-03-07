//****************************************************************************//
// システム                                    : 優良部品簡易登録システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : wuxin
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.service.impl;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.enums.MailDivEnum;
import jp.co.broadleaf.breg.common.service.BizEmailService;
import jp.co.broadleaf.common.message.MessageResolver;
import jp.co.broadleaf.framework.mail.MailClient;
import jp.co.broadleaf.framework.mail.MailClientFactory;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 業務メール送信スタブサービスクラス.
 * </pre>
 */
public class BizEmailServiceImpl implements BizEmailService {
  /**
   * <pre>
   * 業務メール送信スタブ.
   * </pre>
   * 
   * @param mailDiv メール区分
   * @param destMailAddr 送信先メールアドレス
   * @param ccMailAddr CCメールアドレス
   * @param bccMailAddr BCCメールアドレス
   * @param paraMailBodyChangeableTextList メール本文可変文言リスト
   * @return メール送信受付結果 true:送信成功、false:送信失敗
   */
  @Override
  public boolean sendBizEmail(MailDivEnum mailDiv, String destMailAddr, List<String> ccMailAddr,
                              List<String> bccMailAddr, List<String> paraMailBodyChangeableTextList) {
    // メール送信クラスオブジェクトを生成
    MailClient client = mailClientFactory.createInstance();
    // メール本文可変文言の処理
    List<String> mailBodyChangeableTextList = new ArrayList<String>();
    for (String mailBodyChangeableText : paraMailBodyChangeableTextList) {
      if (StringUtils.isEmpty(mailBodyChangeableText)) {
        mailBodyChangeableTextList.add("");
      } else {
        mailBodyChangeableTextList.add(mailBodyChangeableText);
      }
    }
    // CCメールアドレス
    for (String ccAdress : ccMailAddr) {
      client.addCcAddress(ccAdress);
    }
    // BCCメールアドレス
    for (String bccAdress : bccMailAddr) {
      client.addBccAddress(bccAdress);
    }
    String contentText = "";
    // メール種別
    String mailCategory = "";
    // メール件名
    String subject = "";
    if (mailDiv == MailDivEnum.PasswordNotification) {
      // ログインパスワード通知の場合
      mailCategory = "ログインパスワード通知";
      subject = messageResolver.getMessage(BregMessageCodes.I00101).getTitle();
      // メール本文
      contentText = messageResolver.getMessage(BregMessageCodes.I00101).getContents();
      if (mailBodyChangeableTextList.size() == 3) {
        contentText = tranContentText(contentText, mailBodyChangeableTextList);
      }
    } else if (mailDiv == MailDivEnum.PasswordChangedNotification) {
      // パスワード変更通知の場合
      mailCategory = "パスワード変更通知";
      subject = messageResolver.getMessage(BregMessageCodes.I00102).getTitle();
      // メール本文
      contentText = messageResolver.getMessage(BregMessageCodes.I00102).getContents();
      if (mailBodyChangeableTextList.size() == 4) {
        contentText = tranContentText(contentText, mailBodyChangeableTextList);
      }
    } else if (mailDiv == MailDivEnum.LoginAttemptCntNotification) {
      // ログイン試行回数通知の場合
      mailCategory = "ログイン試行回数通知";
      subject = messageResolver.getMessage(BregMessageCodes.I00103).getTitle();
      // メール本文
      contentText = messageResolver.getMessage(BregMessageCodes.I00103).getContents();
      if (mailBodyChangeableTextList.size() == 5) {
        contentText = tranContentText(contentText, mailBodyChangeableTextList);
      }
    }else if(mailDiv == MailDivEnum.ApplicationNotification){
      mailCategory = "申請完了通知";
      subject = messageResolver.getMessage(BregMessageCodes.I01101).getTitle();
      // メール本文
      contentText = messageResolver.getMessage(BregMessageCodes.I01101).getContents();
      if (mailBodyChangeableTextList.size() == 2) {
        contentText = tranContentText(contentText, mailBodyChangeableTextList);
      }
    }else if(mailDiv == MailDivEnum.ApplyCommonNotification){
      mailCategory = "申請完了通知";
      subject = messageResolver.getMessage(BregMessageCodes.I00801).getTitle();
      // メール本文
      contentText = messageResolver.getMessage(BregMessageCodes.I00801).getContents();
      if (mailBodyChangeableTextList.size() == 5) {
        contentText = tranContentText(contentText, mailBodyChangeableTextList);
      }
    }

    boolean isSuccessFlag = true;
    client.setSubject(subject);
    client.setMailCategory(mailCategory);
    client.addToAddress(destMailAddr);
    client.setFrom("no-reply@broadleaf.co.jp");
    client.setText(contentText);
    try {
      client.send();
    } catch (RuntimeException ex) {
      isSuccessFlag = false;
    }
    return isSuccessFlag;
  }

  /**
   * メール本文##の文字列置換する。
   * 
   * @param contentText メール本文
   * @param mailBodyChangeableTextList メール本文可変文言の処理
   * @return メール本文
   */
  private String tranContentText(String contentText, List<String> mailBodyChangeableTextList) {

    // メール本文の改行文字置換する。
    contentText = contentText.replace("<br>", "\n");

    for (int i = 0; i < mailBodyChangeableTextList.size(); i++) {
      contentText = contentText.replace("##" + (i + 1) + "##", mailBodyChangeableTextList.get(i));
    }
    return contentText;
  }

  /** メール送信機能 */
  private MailClientFactory mailClientFactory;

  /**
   * <pre>
   * メール送信機能用Factoryを設定する。
   * </pre>
   *
   * @param mailClientFactory メール送信機能用Factory
   */
  @Resource
  public void setMailClientFactory(MailClientFactory mailClientFactory) {
    this.mailClientFactory = mailClientFactory;
  }

  /** Broadleafプロジェクト用メッセージリゾルバ */
  private MessageResolver messageResolver;

  /**
   * <pre>
   * Broadleafプロジェクト用メッセージリゾルバを設定する。
   * </pre>
   *
   * @param messageResolver Broadleafプロジェクト用メッセージリゾルバ
   */
  @Resource
  public void setMessageResolver(MessageResolver messageResolver) {
    this.messageResolver = messageResolver;
  }
}
