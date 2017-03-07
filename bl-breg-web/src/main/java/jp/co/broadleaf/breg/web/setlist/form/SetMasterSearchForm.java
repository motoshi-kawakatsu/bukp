//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : chenlong
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.setlist.form;

/**
 * <pre>
 * セットマスタのsearchFormのクラス。
 * </pre>
 */
public class SetMasterSearchForm extends SetMasterForm {
  /**mode*/
  public int mode;
  
  /**
   * <pre>
   * 【mode】を取得する。
   * </pre>
   *
   * @return 【mode】
   */
  public int getMode() {
    return mode;
  }

  /**
   * <pre>
   * 【mode】を設定する。
   * </pre>
   *
   * @param mode 【mode】
   */
  public void setMode(int mode) {
    this.mode = mode;
  }

  /**maker or work or common*/
  public String table;

  /**
   * <pre>
   * 【table】を取得する。
   * </pre>
   *
   * @return 【table】
   */
  public String getTable() {
    return table;
  }

  /**
   * <pre>
   * 【table】を設定する。
   * </pre>
   *
   * @param table 【table】
   */
  public void setTable(String table) {
    this.table = table;
  }
  
}
