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

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 
 * </pre>
 */
public class SetMasterGridFormList implements Serializable{
  /**  */
  private static final long serialVersionUID = 5321952563264312021L;
  /**
   * grid list
   */
  public List<SetMasterGridForm> setMasterGridFormList;
  /**
   * table maker or common or work
   */
  public String table;
  /**
   * <pre>
   * 【setMasterGridFormList】を取得する。
   * </pre>
   *
   * @return 【setMasterGridFormList】
   */
  public List<SetMasterGridForm> getSetMasterGridFormList() {
    return setMasterGridFormList;
  }

  /**
   * <pre>
   * 【setMasterGridFormList】を設定する。
   * </pre>
   *
   * @param setMasterGridFormList 【setMasterGridFormList】
   */
  public void setSetMasterGridFormList(List<SetMasterGridForm> setMasterGridFormList) {
    this.setMasterGridFormList = setMasterGridFormList;
  }

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
