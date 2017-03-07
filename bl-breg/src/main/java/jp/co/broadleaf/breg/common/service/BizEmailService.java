//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : wuxin
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.service;

import jp.co.broadleaf.breg.common.enums.MailDivEnum;

import java.util.List;

/**
 * <pre>
 * 業務メール送信スタブサービスインタフェース.
 * </pre>
 */
public interface BizEmailService {

  /**
   * <pre>
   * 業務メール送信スタブ.
   * </pre>
   * 
   * @param mailDiv メール区分
   * @param destMailAddr 送信先メールアドレス
   * @param ccMailAddr CCメールアドレス
   * @param bccMailAddr BCCメールアドレス
   * @param mailBodyChangeableTextList メール本文可変文言リスト
   * @return メール送信受付結果 true:送信成功、false:送信失敗
   */
  boolean sendBizEmail(MailDivEnum mailDiv, String destMailAddr, List<String> ccMailAddr, List<String> bccMailAddr,
                       List<String> mailBodyChangeableTextList);
}
