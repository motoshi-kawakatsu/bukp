package jp.co.broadleaf.breg.joinlist.model;

import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * セットマスタ(common)のModelクラス。
 */
public class JoinMasterCommonModel {

  /**
   * セットマスタ(common)
   */
  private List<JoinMasterCommon> joinMasterCommonList;

  /**
   * セットマスタ(common)の取得。
   *
   * @return セットマスタ(common)
   */
  public List<JoinMasterCommon> getJoinMasterCommonList() {
    return joinMasterCommonList;
  }

  /**
   * セットマスタ(common)の設定。
   *
   * @param joinMasterCommonList セットマスタ(common)
   */
  public void setJoinMasterCommonList(List<JoinMasterCommon> joinMasterCommonList) {
    this.joinMasterCommonList = joinMasterCommonList;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(joinMasterCommonList).toHashCode();
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
    JoinMasterCommonModel other = (JoinMasterCommonModel) obj;
    return new EqualsBuilder().append(joinMasterCommonList, other.joinMasterCommonList).isEquals();
  }

}
