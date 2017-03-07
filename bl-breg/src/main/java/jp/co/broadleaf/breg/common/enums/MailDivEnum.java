//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:曹　緩緩
// 作成日:2017/02/08    修正内容:メール区分のEnumファイル:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.enums;

/**
 * メール区分
 */
public enum MailDivEnum {

  /**
   * 1:パスワード通知
   */
  PasswordNotification(1),

  /**
   * 2:パスワード変更通知
   */
  PasswordChangedNotification(2),

  /**
   * 申請完了通知
   */
  ApplicationNotification(3),

  /**
   * ログイン試行回数通知
   */
  LoginAttemptCntNotification(4),
  
  /**
   * 申請完了通知
   */
  ApplyCommonNotification(5);

  /**
   * メール区分の数値
   */
  private int value = 0;

  /**
   * コンストラクタ
   * 
   * @param value メール区分の数値
   */
  MailDivEnum(int value) {
    this.value = value;
  }

  /**
   * 数値からメール区分を取得する
   * 
   * @param value メール区分の数値
   * @return メール区分
   */
  public static MailDivEnum valueOf(int value) {
    switch (value) {
    case 1:
      // パスワード通知
      return PasswordNotification;
    case 2:
      // パスワード変更通知
      return PasswordChangedNotification;
    // 申請通知
    case 3:
      return ApplicationNotification;
    // ログイン試行回数通知
    case 4:
      return LoginAttemptCntNotification;
    default:
      // その他場合
      return null;
    }
  }

  /**
   * メール区分の数値を取得する
   * 
   * @return メール区分の数値
   */
  public int getValue() {
    return this.value;
  }
}
