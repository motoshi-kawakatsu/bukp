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
package jp.co.broadleaf.breg.setlist.domain.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * </pre>
 */
public class SetMasterMakerIdList implements Serializable {



  /**  */
  private static final long serialVersionUID = -5742029827180470606L;

  public String toString() {
    return "SetMasterMakerIdList [idList=" + idList + "]";
  }

  /***/
  private List<SetMasterId> idList;

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   */
  public SetMasterMakerIdList() {
    super();
    this.idList=new LinkedList<SetMasterId>();
  }

  /**
   * <pre>
   * 【idList】を取得する。
   * </pre>
   *
   * @return 【idList】
   */
  public List<SetMasterId> getIdList() {
    return idList;
  }

  /**
   * <pre>
   * 【idList】を設定する。
   * </pre>
   *
   * @param idList 【idList】
   */
  public void setIdList(List<SetMasterId> idList) {
    this.idList = idList;
  }

  

}
