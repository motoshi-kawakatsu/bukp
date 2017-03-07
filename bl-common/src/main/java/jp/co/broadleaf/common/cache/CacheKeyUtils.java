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
package jp.co.broadleaf.common.cache;

import java.lang.reflect.Array;

/**
 * <pre>
 * BL キャッシュキー用のUtils用クラスです。
 * </pre>
 */
public abstract class CacheKeyUtils {

  /**
   * キャッシュ用のキーを取得する。
   * 
   * @param schema スキーマ
   * @param identifier 識別子
   * @return キャッシュ用のキー
   */
  public static String getCacheKey(String schema, Object identifier) {
    return getCacheKey(schema, identifier, null);
  }

  /**
   * キャッシュ用のキーを取得する。
   * 
   * @param schema スキーマ
   * @param identifier 識別子
   * @param property プロパティ
   * @return キャッシュ用のキー
   */
  public static String getCacheKey(String schema, Object identifier, Object property) {
    StringBuffer buf = new StringBuffer();
    buf.append(schema);
    buf.append(CacheKeySpecs.ELEMENT_SEPARATOR);
    if (identifier.getClass().isArray()) {
      int length = Array.getLength(identifier);
      for (int i = 0; i < length; i++) {
        if (i > 0) {
          buf.append(CacheKeySpecs.IDENTIFIER_SEPARATOR);
        }
        buf.append(Array.get(identifier, i));
      }
    } else {
      buf.append(identifier);
    }
    if (property != null) {
      buf.append(CacheKeySpecs.ELEMENT_SEPARATOR);
      buf.append(property);
    }
    return buf.toString();
  }
}
