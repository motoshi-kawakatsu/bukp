package jp.co.broadleaf.breg.goodslist.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 商品マスタ(メーカー)のDtoのクラス。
 */
public class GoodsMasterCommonInfoDto implements Serializable {

  /**  */
  private static final long serialVersionUID = -1462292274633864002L;
  /**
   * 商品マスタ(メーカー)
   */
  private List<GoodsMasterCommonDto> goodsMasterDto;

  /**
   * 商品マスタ(メーカー)の取得。
   *
   * @return セットマスタ(メーカー)
   */
  public List<GoodsMasterCommonDto> getGoodsMasterDto() {
    return goodsMasterDto;
  }

  /**
   * 商品マスタ(メーカー)の設定。
   *
   * @param goodsMasterDto セットマスタ(メーカー)
   */
  public void setGoodsMasterDto(List<GoodsMasterCommonDto> goodsMasterDto) {
    this.goodsMasterDto = goodsMasterDto;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(goodsMasterDto).toHashCode();
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
    GoodsMasterCommonInfoDto other = (GoodsMasterCommonInfoDto) obj;
    return new EqualsBuilder().append(goodsMasterDto, other.goodsMasterDto).isEquals();
  }

}
