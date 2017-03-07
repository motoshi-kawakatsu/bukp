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

import jp.co.broadleaf.breg.common.entity.SetMasterMaker;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * セットマスタ(メーカー)のModelクラス。
 */
public class SetMasterMakerModel {

  /**
   * セットマスタ(メーカー)
   */
  private List<SetMasterMaker> setMasterMakerList;
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
   * セットマスタ(メーカー)の取得。
   *
   * @return セットマスタ(メーカー)
   */
  public List<SetMasterMaker> getSetMasterMakerList() {
    return setMasterMakerList;
  }

  /**
   * セットマスタ(メーカー)の設定。
   *
   * @param setMasterMakerList セットマスタ(メーカー)
   */
  public void setSetMasterMakerList(List<SetMasterMaker> setMasterMakerList) {
    this.setMasterMakerList = setMasterMakerList;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(setMasterMakerList).toHashCode();
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
    SetMasterMakerModel other = (SetMasterMakerModel) obj;
    return new EqualsBuilder().append(setMasterMakerList, other.setMasterMakerList).isEquals();
  }

}
