package jp.co.broadleaf.breg.joinlist.model;

import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMaker;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * 結合マスタ(メーカー)ワークのModelクラス。
 */
public class JoinMasterWorkModel {
  /**
   * 結合マスタ(メーカー)ワーク
   */
  private List<JoinMasterWorkMaker> joinMasterWorkList;
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
   * 結合マスタ(メーカー)ワークの取得。
   *
   * @return 結合マスタ(メーカー)ワーク
   */
  public List<JoinMasterWorkMaker> getJoinMasterWorkList() {
    return joinMasterWorkList;
  }

  /**
   * 結合マスタ(メーカー)ワークの設定。
   *
   * @param joinMasterWorkList 結合マスタ(メーカー)ワーク
   */
  public void setJoinMasterWorkList(List<JoinMasterWorkMaker> joinMasterWorkList) {
    this.joinMasterWorkList = joinMasterWorkList;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(joinMasterWorkList).toHashCode();
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
    JoinMasterWorkModel other = (JoinMasterWorkModel) obj;
    return new EqualsBuilder().append(joinMasterWorkList, other.joinMasterWorkList).isEquals();
  }
}
