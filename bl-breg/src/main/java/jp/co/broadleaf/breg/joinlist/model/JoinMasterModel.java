//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 王　天コン
// 作 成 日       2017/02/06   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.joinlist.model;

import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * 結合マスタ(メーカー)のModelクラス。
 */
public class JoinMasterModel {
  /**
   * 結合マスタ(メーカー)
   */
  private List<JoinMasterMaker> joinMasterList;
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
   * 結合マスタ(メーカー)の取得。
   *
   * @return 結合マスタ(メーカー)
   */
  public List<JoinMasterMaker> getJoinMasterList() {
    return joinMasterList;
  }

  /**
   * 結合マスタ(メーカー)の設定。
   *
   * @param joinMasterList 結合マスタ(メーカー)
   */
  public void setJoinMasterList(List<JoinMasterMaker> joinMasterList) {
    this.joinMasterList = joinMasterList;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(joinMasterList).toHashCode();
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
    JoinMasterModel other = (JoinMasterModel) obj;
    return new EqualsBuilder().append(joinMasterList, other.joinMasterList).isEquals();
  }
}
