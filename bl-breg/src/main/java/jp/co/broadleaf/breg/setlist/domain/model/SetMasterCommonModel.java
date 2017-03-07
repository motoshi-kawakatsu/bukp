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

import jp.co.broadleaf.breg.common.entity.SetMasterCommon;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * セットマスタ(common)のModelクラス。
 */
public class SetMasterCommonModel {

  /**
   * セットマスタ(common)
   */
  private List<SetMasterCommon> setMasterCommonList;

  /**
   * セットマスタ(common)の取得。
   *
   * @return セットマスタ(common)
   */
  public List<SetMasterCommon> getSetMasterCommonList() {
    return setMasterCommonList;
  }

  /**
   * セットマスタ(common)の設定。
   *
   * @param setMasterCommonList List
   */
  public void setSetMasterCommonList(List<SetMasterCommon> setMasterCommonList) {
    this.setMasterCommonList = setMasterCommonList;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(setMasterCommonList).toHashCode();
  }

  /**
   * equalsメソッド
   * 
   * @param obj オブジェクト
   * @return 比較結果
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    SetMasterCommonModel other = (SetMasterCommonModel) obj;
    return new EqualsBuilder().append(setMasterCommonList, other.setMasterCommonList).isEquals();
  }

}
