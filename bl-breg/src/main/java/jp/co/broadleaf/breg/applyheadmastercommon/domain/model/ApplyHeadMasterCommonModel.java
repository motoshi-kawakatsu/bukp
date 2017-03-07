//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                    作成担当 : xuck
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applyheadmastercommon.domain.model;

import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommon;

import java.util.List;

/**
 * <pre>
 * </pre>
 */
public class ApplyHeadMasterCommonModel {

  /**
   * test
   */
  private List<ApplyHeadMasterCommon> applyHeadMasterList;

  /**
   * <pre>
   * 【applyHeadMasterList】を取得する。
   * </pre>
   *
   * @return 【applyHeadMasterList】
   */
  public List<ApplyHeadMasterCommon> getApplyHeadMasterList() {
    return applyHeadMasterList;
  }

  /**
   * <pre>
   * 【applyHeadMasterList】を設定する。
   * </pre>
   *
   * @param applyHeadMasterList 【applyHeadMasterList】
   */
  public void setApplyHeadMasterList(List<ApplyHeadMasterCommon> applyHeadMasterList) {
    this.applyHeadMasterList = applyHeadMasterList;
  }

  /**
   * <pre>
   * 【applyHeadMasterList】を取得する。
   * </pre>
   *
   * @return 【applyHeadMasterList】
   */
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((applyHeadMasterList == null) ? 0 : applyHeadMasterList.hashCode());
    return result;
  }

  /**
   * <pre>
   * 【applyHeadMasterList】を取得する。
   * </pre>
   * 
   * @param obj obj
   * @return 【applyHeadMasterList】
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ApplyHeadMasterCommonModel other = (ApplyHeadMasterCommonModel) obj;
    if (applyHeadMasterList == null) {
      if (other.applyHeadMasterList != null)
        return false;
    } else if (!applyHeadMasterList.equals(other.applyHeadMasterList))
      return false;
    return true;
  }

}
