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
package jp.co.broadleaf.framework.web.handler;

import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrencyException;
import jp.co.broadleaf.framework.exception.BusinessException;
import jp.co.broadleaf.framework.exception.FieldError;
import jp.co.broadleaf.framework.exception.ValidationException;
import jp.co.broadleaf.framework.message.MessageResolver;
import jp.co.broadleaf.framework.web.ExceptionHandler;
import jp.co.broadleaf.framework.web.WebResult;
import jp.co.broadleaf.framework.web.WebResultStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * デフォルト例外ハンドラークラスです。
 */
public class DefaultExceptionHandler implements ExceptionHandler {

  /**
   * コンストラクタ
   */
  public DefaultExceptionHandler() {
  }

  /**
   * ログ
   */
  protected final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

  /**
   * 例外共通処理を行う。
   * 
   * @param ex 例外オブジェクト
   * @param result Controllerの戻り値(例：ModelAndView)
   */
  @Override
  public final void handleException(Throwable ex, Object result) {
    handleException(ex, result, null);
  }

  /**
   * 共通例外処理
   * 
   * @param ex 例外オブジェクト
   * @param result Controllerの戻り値(例：ModelAndView)
   * @param extra 拡張用データ
   */
  public void handleException(Throwable ex, Object result, Object extra) {
    WebResult webResult = convertToWebResult(result);
    handleException(ex, webResult, extra);
  }

  /**
   * 統一的に処理するために、WebResultへ変換する
   * 
   * @param result Controllerの戻り値(例：ModelAndView)
   * @return WebResult
   */
  protected WebResult convertToWebResult(Object result) {
    WebResult webResult = null;
    if (result instanceof WebResult) {
      webResult = (WebResult) result;
    } else if (result instanceof ModelAndView) {
      webResult = new ViewResult((ModelAndView) result);
    } else {
      throw new IllegalArgumentException("The result's type is not support");
    }
    return webResult;
  }

  /**
   * 共通例外処理
   * 
   * @param ex 例外オブジェクト
   * @param result WebResult
   * @param extra 拡張用データ
   */
  protected void handleException(Throwable ex, WebResult result, Object extra) {
    if (ex instanceof OptimisticConcurrencyException) {
      handleBusinessException(new BusinessException(concurrencyErrorMessageCode), result, extra);
    } else if (ex instanceof BusinessException) {
      handleBusinessException((BusinessException) ex, result, extra);
    } else if (ex instanceof ValidationException) {
      handleValidationException((ValidationException) ex, result, extra);
    } else {
      handleSystemException(ex, result, extra);
    }
  }

  /**
   * 業務例外共通処理を行う。
   * 
   * @param ex 例外オブジェクト
   * @param result ビューモデル
   * @param extra 拡張用データ
   */
  protected void handleBusinessException(BusinessException ex, WebResult result, Object extra) {
    result.setStatus(WebResultStatus.BUSINESS_ERROR);
    addErrorInfo(result, getBusinessErrorInfo(ex, extra), extra);
  }

  /**
   * 検証例外共通処理を行う。
   * 
   * @param ex 例外オブジェクト
   * @param result ビューモデル
   * @param extra 拡張用データ
   */
  protected void handleValidationException(ValidationException ex, WebResult result, Object extra) {
    result.setStatus(WebResultStatus.INPUT_ERROR);
    addErrorInfo(result, getValidationErrorInfo(ex, extra), extra);
  }

  /**
   * システム例外共通処理を行う。
   * 
   * @param ex 例外オブジェクト
   * @param result ビューモデル
   * @param extra 拡張用データ
   */
  protected void handleSystemException(Throwable ex, WebResult result, Object extra) {
    logger.error("Unexcepted System Exception Caused:", ex);
    result.setStatus(WebResultStatus.SYSTEM_ERROR);
    addErrorInfo(result, getSystemErrorInfo(ex, extra), extra);
    if (result instanceof ViewResult && systemErrorViewName != null) {
      ((ViewResult) result).setViewName(systemErrorViewName);
    }
  }

  /**
   * ビューモデルにエラー情報を追加する。
   * 
   * @param result ビューモデル
   * @param errorInfo エラー情報
   * @param extra 拡張用データ
   */
  protected void addErrorInfo(WebResult result, Map<String, Object> errorInfo, Object extra) {
    result.put("errorInfo", errorInfo);
  }

  /**
   * 業務エラー情報を作成する。
   * 
   * @param ex 例外オブジェクト
   * @return 業務エラー情報
   * @param extra 拡張用データ
   */
  protected Map<String, Object> getBusinessErrorInfo(BusinessException ex, Object extra) {
    Map<String, Object> error = new LinkedHashMap<String, Object>();
    error.put("code", ex.getErrorCode());
    error.put("displayMessage", getMessageText(getMessage(ex.getErrorCode(), ex.getErrorArguments(), extra)));
    return error;
  }

  /**
   * 検証エラー情報を作成する。
   * 
   * @param ex 例外オブジェクト
   * @return 検証エラー情報
   * @param extra 拡張用データ
   */
  protected Map<String, Object> getValidationErrorInfo(ValidationException ex, Object extra) {
    Map<String, Object> error = new LinkedHashMap<String, Object>();
    List<Map<String, String>> items = new ArrayList<Map<String, String>>();
    if (ex.getFieldErrors() != null) {
      for (FieldError fieldError : ex.getFieldErrors()) {
        Map<String, String> item = new LinkedHashMap<String, String>();
        item.put("key", fieldError.getField());
        item.put("message",
            getMessageText(getMessage(fieldError.getErrorCode(), fieldError.getErrorArguments(), extra)));
        items.add(item);
      }
    }
    error.put("details", items);
    return error;
  }

  /**
   * システムエラー情報を作成する。
   * 
   * @param ex 例外オブジェクト
   * @param extra 拡張用データ
   * @return システムエラー情報
   */
  protected Map<String, Object> getSystemErrorInfo(Throwable ex, Object extra) {
    String msgcode = systemErrorMessageCode;
    Map<String, Object> error = new LinkedHashMap<String, Object>();
    error.put("code", msgcode);
    if (ex != null) {
      error.put("debugMessage", ex.getMessage());
    }
    error.put("displayMessage", getMessageText(getMessage(msgcode, null, extra)));
    return error;
  }

  /**
   * エラーコードよりエラーメッセージを取得する。
   * 
   * @param code エラーコード
   * @param arguments エラーのパラメータ
   * @param extra 拡張用データ
   * @return エラーメッセージ
   */
  protected Object getMessage(String code, String[] arguments, Object extra) {
    if (messageResolver != null) {
      return messageResolver.getMessage(code, arguments, null, null);
    } else {
      return code;
    }
  }

  /**
   * メッセージテキストを取得する。
   * 
   * @param message メッセージ
   * @return メッセージテキスト
   */
  protected String getMessageText(Object message) {
    return message.toString();
  }

  /**
   * システムエラーのメッセージコード
   */
  private String systemErrorMessageCode;

  /**
   * システムエラーのビュー名
   */
  private String systemErrorViewName;

  /**
   * 排他エラーのメッセージコード
   */
  private String concurrencyErrorMessageCode;

  /**
   * メッセージ取得用オブジェクト
   */
  private MessageResolver messageResolver;

  /**
   * システムエラーのメッセージコードを設定する。
   * 
   * @param systemErrorMessageCode システムエラーのメッセージコード
   */
  public void setSystemErrorMessageCode(String systemErrorMessageCode) {
    this.systemErrorMessageCode = systemErrorMessageCode;
  }

  /**
   * メッセージ取得用オブジェクトを設定する。
   * 
   * @param messageResolver メッセージ取得用オブジェクト
   */
  public void setMessageResolver(MessageResolver messageResolver) {
    this.messageResolver = messageResolver;
  }

  /**
   * システムエラーのビュー名を設定する。
   * 
   * @param systemErrorViewName システムエラーのビュー名
   */
  public void setSystemErrorViewName(String systemErrorViewName) {
    this.systemErrorViewName = systemErrorViewName;
  }

  /**
   * システムエラーのメッセージコードを取得する。
   * 
   * @return システムエラーのメッセージコード
   */
  protected String getSystemErrorMessageCode() {
    return systemErrorMessageCode;
  }

  /**
   * システムエラーのビュー名を取得する。
   * 
   * @return システムエラーのビュー名
   */
  protected String getSystemErrorViewName() {
    return systemErrorViewName;
  }

  /**
   * メッセージ取得用オブジェクトを取得する。
   * 
   * @return メッセージ取得用オブジェクト
   */
  protected MessageResolver getMessageResolver() {
    return messageResolver;
  }

  /**
   * 排他エラーのメッセージコードを取得する。
   *
   * @return 排他エラーのメッセージコード
   */
  protected String getConcurrencyErrorMessageCode() {
    return concurrencyErrorMessageCode;
  }

  /**
   * 排他エラーのメッセージコードを設定する。
   *
   * @param concurrencyErrorMessageCode 排他エラーのメッセージコード
   */
  public void setConcurrencyErrorMessageCode(String concurrencyErrorMessageCode) {
    this.concurrencyErrorMessageCode = concurrencyErrorMessageCode;
  }

  /**
   * ModelAndView用のWebResultクラスです。
   */
  protected static class ViewResult extends WebResult {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * ModelAndView
     */
    private transient ModelAndView mv;

    /**
     * コンストラクタ
     * 
     * @param mv ModelAndView
     */
    public ViewResult(ModelAndView mv) {
      this.mv = mv;
      mv.addObject(WebResult.STATUS, WebResultStatus.NO_ERROR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStatus(int status) {
      mv.addObject(WebResult.STATUS, status);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object put(String key, Object value) {
      if (mv == null) {
        return null;
      }
      return mv.addObject(key, value);
    }

    /**
     * ビュー名を設定する。
     * 
     * @param viewName ビュー名
     */
    public void setViewName(String viewName) {
      mv.setViewName(viewName);
    }
  }

}
