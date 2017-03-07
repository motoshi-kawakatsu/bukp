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
package jp.co.broadleaf.common.web.handler;

import jp.co.broadleaf.common.message.MessageInfo;
import jp.co.broadleaf.common.message.MessageResolver;
import jp.co.broadleaf.framework.exception.BusinessException;
import jp.co.broadleaf.framework.exception.FieldError;
import jp.co.broadleaf.framework.exception.ValidationException;
import jp.co.broadleaf.framework.web.WebResult;
import jp.co.broadleaf.framework.web.handler.DefaultExceptionHandler;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * BL用例外ハンドラー実現クラスです。
 * </pre>
 */
public class BroadleafExceptionHandler extends DefaultExceptionHandler {

  /**
   * メッセージ取得用オブジェクト
   */
  private MessageResolver customMessageResolver;

  /**
   * 業務エラーのビュー名
   */
  private String businessErrorViewName;
  
  /**
   * メッセージ取得用オブジェクトを設定する。
   *
   * @param customMessageResolver メッセージ取得用オブジェクト
   */
  public void setCustomMessageResolver(MessageResolver customMessageResolver) {
    this.customMessageResolver = customMessageResolver;
  }

  /**
   * 業務エラーのビュー名を設定する。
   * 
   * @param businessErrorViewName 業務エラーのビュー名
   */
  public void setBusinessErrorViewName(String businessErrorViewName) {
    this.businessErrorViewName = businessErrorViewName;
  }

  /**
   * エラーコードよりエラーメッセージのテキストを取得する。
   * 
   * @param code エラーコード
   * @param arguments エラーのパラメータ
   * @param extra 拡張用データ
   * @return エラーメッセージのテキスト
   */
  protected Object getMessage(String code, String[] arguments, Object extra) {
    if (customMessageResolver == null) {
      throw new NullPointerException("customMessageResolver must not be null");
    }
    return customMessageResolver.getMessage(code, arguments);
  }

  /**
   * メッセージテキストを取得する。
   * 
   * @param message メッセージ
   * @return メッセージテキスト
   */
  protected String getMessageText(Object message) {
    return ((MessageInfo) message).getContents();
  }

  /**
   * 業務例外共通処理を行う。
   * 
   * @param ex 例外オブジェクト
   * @param result ビューモデル
   * @param extra 拡張用データ
   */
  protected void handleBusinessException(BusinessException ex, WebResult result, Object extra) {
    super.handleBusinessException(ex, result, extra);
    
    if (result instanceof ViewResult && ex.getErrorCode().startsWith("E19")) {
      ((ViewResult) result).setViewName(businessErrorViewName);
    }
  }
  
  /**
   * 検証エラー情報を作成する。
   * 
   * @param ex 例外オブジェクト
   * @param extra 拡張用データ
   * @return 検証エラー情報
   */
  protected Map<String, Object> getValidationErrorInfo(ValidationException ex, Object extra) {
    Map<String, Object> error = new LinkedHashMap<String, Object>();
    List<Map<String, String>> items = new ArrayList<Map<String, String>>();

    if (ex.getFieldErrors() != null) {
      for (FieldError fieldError : ex.getFieldErrors()) {
        Map<String, String> item = new LinkedHashMap<String, String>();

        item.put("field", fieldError.getField());
        MessageInfo message = (MessageInfo) getMessage(fieldError.getErrorCode(), fieldError.getErrorArguments(),
            extra);
        if (message != null) {
          item.put("message", message.getContents());
          item.put("title", message.getTitle());
          item.put("messageType", StringUtils.upperCase(message.getType()));
        }

        items.add(item);
      }
    }
    error.put("errors", items);
    putInfoField(error);

    return error;

  }

  /**
   * 業務エラー情報を作成する。
   * 
   * @param ex 例外オブジェクト
   * @param extra 拡張用データ
   * @return 業務エラー情報
   */
  protected Map<String, Object> getBusinessErrorInfo(BusinessException ex, Object extra) {
    Map<String, Object> error = new LinkedHashMap<String, Object>();
    List<Map<String, String>> items = new ArrayList<Map<String, String>>();

    Map<String, String> item = makeMessageItem(ex.getErrorCode(), ex.getErrorArguments(), extra);
    items.add(item);

    error.put("errors", items);
    putInfoField(error);
    
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
    Map<String, Object> error = new LinkedHashMap<String, Object>();
    List<Map<String, String>> items = new ArrayList<Map<String, String>>();
    
    Map<String, String> item = makeMessageItem(getSystemErrorMessageCode(), null, extra);
    items.add(item);

    error.put("errors", items);
    putInfoField(error);
    
    return error;
  }

  /**
   * メッセージアイテムを作成する。
   * 
   * @param code エラーコード
   * @param args エラーのパラメータ
   * @param extra 拡張用データ
   * @return メッセージアイテム情報
   */
  protected Map<String, String> makeMessageItem(String code, String[] args, Object extra) {
    
    Map<String, String> item = new LinkedHashMap<String, String>();
    item.put("code", code);
    
    MessageInfo message = (MessageInfo) getMessage(code, args, extra);
    if (message != null) {
      item.put("message", message.getContents());
      item.put("title", message.getTitle());
      item.put("messageType", StringUtils.upperCase(message.getType()));
    }
    return item;
  }
  
  /**
   * infoフィールドの追加処理。
   * 
   * @param error エラーマップ
   */
  private void putInfoField(Map<String, Object> error) {
    error.put("info", "");// TODO エラー発生時の対処方法を記述する予定,URLリンクによるお問い合わせやFAQへの遷移を用途とする
  }
}
