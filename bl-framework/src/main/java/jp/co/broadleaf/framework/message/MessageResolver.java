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
package jp.co.broadleaf.framework.message;

import java.util.Locale;

/**
 * メッセージリゾルバのインタフェース
 */
public interface MessageResolver {

  /**
   * メッセージコードなどより、メッセージコンテントを取得する
   * 
   * @param code メッセージコード
   * @param args メッセージパラメータ (パラメータ定義例： "{0}", "{1,date}", "{2,time}")
   * @param defaultMessage デフォルトメッセージコンテント
   * @param locale ロケール
   * @return メッセージコンテント、見つかれない場合、デフォルトメッセージコンテントを戻る
   */
  String getMessage(String code, Object[] args, String defaultMessage, Locale locale);

  /**
   * メッセージコードなどより、メッセージコンテントを取得する
   * 
   * @param code メッセージコード
   * @param args メッセージパラメータ (パラメータ定義例： "{0}", "{1,date}", "{2,time}")
   * @param locale ロケール
   * @throws MessageNotFoundException
   *           メッセージを見つかれない場合、MessageNotFoundExceptionをスローする
   * @return メッセージコンテント
   */
  String getMessage(String code, Object[] args, Locale locale) throws MessageNotFoundException;
}
