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

import java.util.LinkedHashMap;

/**
 * WebAPIの結果用クラス
 */
public class WebResult extends LinkedHashMap<String, Object> {

  /** serialVersionUID */
  private static final long serialVersionUID = -6959275558958175706L;

  /**
   * ステータス項目名
   */
  public static final String STATUS = "status";

  /**
   * コンストラクタ
   */
  public WebResult() {
    this.put(STATUS, WebResultStatus.NO_ERROR);
  }

  /**
   * コンストラクタ
   * 
   * @param status ステータス
   */
  public WebResult(int status) {
    this.put(STATUS, status);
  }

  /**
   * ステータスを取得する
   * 
   * @return ステータス
   */
  public int getStatus() {
    return (Integer) this.get(STATUS);
  }

  /**
   * ステータスを設定する
   * 
   * @param status ステータス
   */
  public void setStatus(int status) {
    this.put(STATUS, status);
  }

}
