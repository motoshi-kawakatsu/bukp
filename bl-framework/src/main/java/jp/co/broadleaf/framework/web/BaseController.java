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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * ベースコントローラー
 */
public abstract class BaseController {

  /**
   * ログ
   */
  protected final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

  /**
   * 例外ハンドラー
   */
  private ExceptionHandler exceptionHandler = null;

  /**
   * 例外ハンドラーを設定する
   * 
   * @param exceptionHandler 例外ハンドラー
   */
  @Resource
  public void setExceptionHandler(ExceptionHandler exceptionHandler) {
    this.exceptionHandler = exceptionHandler;
  }

  /**
   * 共通例外処理
   * 
   * @param ex 例外
   * @param result Controllerの戻り値(例：ModelAndView)
   */
  protected void handleException(Throwable ex, Object result) {
    if (exceptionHandler != null) {
      exceptionHandler.handleException(ex, result);
    }
  }

  /**
   * 共通例外処理
   * 
   * @param ex 例外
   * @param result Controllerの戻り値(例：ModelAndView)
   * @param custemSystemErrorMessageCode システムエラーメッセージコード
   */
  protected void handleException(Throwable ex, Object result, String custemSystemErrorMessageCode) {
    if (exceptionHandler != null) {
      exceptionHandler.handleException(ex, result, custemSystemErrorMessageCode);
    }
  }
}
