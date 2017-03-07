//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:曹　緩緩
// 作成日:2017/02/08    修正内容:ログイン：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.loginmaker.library;

import org.apache.commons.lang3.Validate;

import java.util.Calendar;
import java.util.Date;

/**
 * <pre>
 * 有効期限計算.
 * </pre>
 */
public abstract class ExpiryDtCalc {
  /**
   * <pre>
   * 有効期限計算.
   * </pre>
   * 
   * @param systemTime システム日時
   * @param second 対象機能の有効期間（秒数,単位はs付き）
   * @return 有効期限 日付（Date）
   */
  public static Date calcExpiryDt(Date systemTime, int second) {
    Validate.notNull(systemTime, "systemTime must not be null");
    // 有効期間にマイナス値が設定されていた場合
    if (second < 0) {
      // NULLを返却する
      return null;
    }
    // 対象となるシステム日時を取得する
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(systemTime);
    // システム日時＋有効期間
    calendar.add(Calendar.SECOND, second);
    // 有効期限を返却する
    return calendar.getTime();
  }
}
