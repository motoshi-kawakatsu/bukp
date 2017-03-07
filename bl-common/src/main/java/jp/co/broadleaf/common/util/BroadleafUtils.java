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
package jp.co.broadleaf.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Base64;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * <pre>
 * Broadleafプロジェクト用のUtilsクラスです。
 * </pre>
 */
public abstract class BroadleafUtils {

  /**
   * システム日時を取得する。
   * 
   * @return システム日時
   */
  public static Date getSystemDtTime() {
    return new Date();
  }

  /**
   * システム日付（年月日）を取得する。
   * 
   * @return システム日付（年月日）
   */
  public static Date getSystemDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(getSystemDtTime());
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  /**
   * <pre>
   * バイト配列を16進数の文字列に変換し、連結して返す。.
   * </pre>
   * 
   * @param array 変換配列
   * @return 16進法の文字列
   */
  public static String convertBytesToHex(byte[] array) {

    String result = null;
    if (array != null) {
      StringBuilder sb = new StringBuilder();
      for (byte b : array) {
        String hex = String.format("%02x", b);
        sb.append(hex);
      }
      result = sb.toString();
    }
    return result;
  }

  /**
   * <pre>
   * URLセーフ型、
   * エンコードされたバイト・データの末尾にパディング文字を追加せず、
   * Base64エンコーディング・スキームを使用して、
   * 指定されたバイト配列をエンコードし、Stringに書き込みます。
   * </pre>
   * 
   * @param src エンコードするバイト配列
   * @return 結果となるBase64でエンコードされた文字を格納するString
   */
  public static String encodeToString(byte[] src) {

    return Base64.getUrlEncoder().withoutPadding().encodeToString(src);
  }

  /**
   * <pre>
   * 改行コード変換。
   * </pre>
   * 
   * @param str 改行コード変換前の文字列
   * @return 改行コード変換後の文字列
   */
  public static String convertNewline(String str) {

    if (StringUtils.isEmpty(str)) {
      return str;
    }
    return str.replace("<br>", "\\n");
  }

  /**
   * <pre>
   * HTMLエンコード。
   * </pre>
   * 
   * @param str HTMLエンコード前の文字列
   * @return HTMLエンコード後の文字列
   */
  public static String encodeHtml(String str) {

    if (StringUtils.isEmpty(str)) {
      return str;
    }
    return str.replace("&", "&amp;").replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;").replace(" ",
        "&nbsp;");
  }

  /**
   * 空コレクションの判定を行う
   * 
   * @param collection コレクション
   * @return true:空|false:非空
   */
  public static boolean isEmpty(Collection<?> collection) {

    return null == collection || collection.isEmpty();
  }

}
