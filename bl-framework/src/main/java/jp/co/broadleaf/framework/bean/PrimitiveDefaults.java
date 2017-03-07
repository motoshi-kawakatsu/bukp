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
package jp.co.broadleaf.framework.bean;

/**
 * <pre>
 * Primitive型のデフォルト値取得用のユーティリティ
 * </pre>
 */
public class PrimitiveDefaults {

  /**
   * booleanのデフォルト値
   */
  private boolean defaultBoolean;

  /**
   * charのデフォルト値
   */
  private char defaultChar;

  /**
   * byteのデフォルト値
   */
  private byte defaultByte;

  /**
   * shortのデフォルト値
   */
  private short defaultShort;

  /**
   * intのデフォルト値
   */
  private int defaultInt;

  /**
   * longのデフォルト値
   */
  private long defaultLong;

  /**
   * floatのデフォルト値
   */
  private float defaultFloat;

  /**
   * doubleのデフォルト値
   */
  private double defaultDouble;

  /**
   * インスタンス
   */
  private static final PrimitiveDefaults INSTANCE = new PrimitiveDefaults();

  /**
   * インスタンスを取得する
   * 
   * @return インスタンス
   */
  public static PrimitiveDefaults getInstance() {
    return INSTANCE;
  }

  /**
   * Primitive型のデフォルト値を取得する。
   * 
   * @param clazz クラス
   * @return デフォルト値
   */
  public Object getDefaultValue(Class<?> clazz) {
    if (clazz.equals(Boolean.class)) {
      return defaultBoolean;
    } else if (clazz.equals(Character.class)) {
      return defaultChar;
    } else if (clazz.equals(Byte.class)) {
      return defaultByte;
    } else if (clazz.equals(Short.class)) {
      return defaultShort;
    } else if (clazz.equals(Integer.class)) {
      return defaultInt;
    } else if (clazz.equals(Long.class)) {
      return defaultLong;
    } else if (clazz.equals(Float.class)) {
      return defaultFloat;
    } else if (clazz.equals(Double.class)) {
      return defaultDouble;
    } else {
      return null;
    }
  }
}
