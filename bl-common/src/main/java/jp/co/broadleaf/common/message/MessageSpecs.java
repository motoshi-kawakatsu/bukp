//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/24   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.common.message;

/**
 * <pre>
 * メッセージの定義規範クラスです。
 * </pre>
 */
public abstract class MessageSpecs {
  /**
   * エラーメッセージのタイプ
   */
  public static final String ERROR_MESSAGE_TYPE = "e";

  /**
   * ウォーニングメッセージのタイプ
   */
  public static final String WARN_MESSAGE_TYPE = "w";

  /**
   * インフォメッセージのタイプ
   */
  public static final String INFO_MESSAGE_TYPE = "i";

  /**
   * エラーメッセージの先頭文字
   */
  public static final String ERROR_MESSAGE_PREFIX = "E";

  /**
   * ウォーニングメッセージの先頭文字
   */
  public static final String WARN_MESSAGE_PREFIX = "W";

  /**
   * インフォメッセージの先頭文字
   */
  public static final String INFO_MESSAGE_PREFIX = "I";

  /**
   * プレースフォルダの開始インデックス
   */
  public static final int MESSAGE_PLACEHOLDER_START_INDEX = 1;

  /**
   * メッセージプレースフォルダの置換式を取得する
   * 
   * @param index インデックス
   * @return プレースフォルダの置換式
   */
  public static String getMessagePlaceHolderRegex(int index) {
    return "\\$" + index;
  }
}
