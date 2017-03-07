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

import jp.co.broadleaf.breg.common.entity.SetMasterWorkMaker;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * <pre>
 * セットマスタ(ワーク)のModelクラス。
 * </pre>
 */
public class SetMasterWorkMakerModel {

  /**
   * セットマスタ(ワーク)
   */
  private List<SetMasterWorkMaker> setMasterWorkMakerList;

  /**
   * 検索件数
   */
  long searchCounts;

  /**
   * <pre>
   * 【検索件数】を取得する。
   * </pre>
   *
   * @return 【検索件数】
   */
  public long getSearchCounts() {
    return searchCounts;
  }

  /**
   * <pre>
   * 【検索件数】を設定する。
   * </pre>
   *
   * @param searchCounts 【検索件数】
   */
  public void setSearchCounts(long searchCounts) {
    this.searchCounts = searchCounts;
  }

  /**
   * セットマスタ(ワーク)の取得。
   *
   * @return セットマスタ(ワーク)
   */
  public List<SetMasterWorkMaker> getSetMasterWorkMakerList() {
    return setMasterWorkMakerList;
  }

  /**
   * セットマスタ(ワーク)の設定。
   *
   * @param setMasterWorkMakerList セットマスタ(ワーク)
   */
  public void setSetMasterWorkMakerList(List<SetMasterWorkMaker> setMasterWorkMakerList) {
    this.setMasterWorkMakerList = setMasterWorkMakerList;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(setMasterWorkMakerList).toHashCode();
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
    SetMasterWorkMakerModel other = (SetMasterWorkMakerModel) obj;
    return new EqualsBuilder().append(setMasterWorkMakerList, other.setMasterWorkMakerList).isEquals();
  }

}
