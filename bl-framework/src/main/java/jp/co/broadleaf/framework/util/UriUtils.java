//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2016/01/14   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * <pre>
 * URI用Utilsクラスです。
 * </pre>
 */
public abstract class UriUtils {

  /**
   * URIクエリパラメータをエンコードします(RFC 3986)。
   * 
   * @param uri エンコードするURI
   * @return エンコードした結果
   */
  public static String encodeQueryParam(String uri) {
    try {
      return URLEncoder.encode(uri, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%24", "\\$")
          .replaceAll("\\%27", "'").replaceAll("\\%28", "(").replaceAll("\\%29", ")").replaceAll("\\%2C", ",")
          .replaceAll("\\%2F", "/").replaceAll("\\%3A", ":").replaceAll("\\%3B", ";").replaceAll("\\%3F", "?")
          .replaceAll("\\%40", "@").replaceAll("\\%7E", "~");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * URIをエンコードするため使用します(RFC 3986)。
   * 
   * @param uri エンコードするURI
   * @return エンコードした結果
   */
  public static String encode(String uri) {
    try {
      return URLEncoder.encode(uri, "UTF-8").replaceAll("\\+", "%20").replaceAll("\\*", "%2A").replaceAll("\\%7E", "~");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }
}
