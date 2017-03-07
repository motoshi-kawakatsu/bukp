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
package jp.co.broadleaf.framework.web;

/**
 * 例外ハンドラーのインタフェース
 */
public interface ExceptionHandler {

  /**
   * 共通例外処理
   * 
   * @param ex 例外オブジェクト
   * @param result Controllerの戻り値(例：ModelAndView)
   */
  void handleException(Throwable ex, Object result);

  /**
   * 共通例外処理
   * 
   * @param ex 例外オブジェクト
   * @param result Controllerの戻り値(例：ModelAndView)
   * @param extra 拡張用データ
   */
  void handleException(Throwable ex, Object result, Object extra);
}
