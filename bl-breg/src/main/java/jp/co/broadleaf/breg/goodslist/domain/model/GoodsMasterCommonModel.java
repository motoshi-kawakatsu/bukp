
package jp.co.broadleaf.breg.goodslist.domain.model;

import jp.co.broadleaf.breg.common.entity.GoodsMasterCommon;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * <pre>
 * 商品マスタ(common)Model.
 * </pre>
 */
public class GoodsMasterCommonModel {
  /**
   * 商品マスタ(common)
   */
  private List<GoodsMasterCommon> goodsList;

  /**
   * 商品マスタ(common)の取得。
   *
   * @return 商品マスタ(common)
   */
  public List<GoodsMasterCommon> getGoodsList() {
    return goodsList;
  }

  /**
   * 商品マスタ(common)の設定。
   *
   * @param goodsList 商品マスタ(common)
   */
  public void setGoodsList(List<GoodsMasterCommon> goodsList) {
    this.goodsList = goodsList;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(goodsList).toHashCode();
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
    GoodsMasterCommonModel other = (GoodsMasterCommonModel) obj;
    return new EqualsBuilder().append(goodsList, other.goodsList).isEquals();
  }
}
