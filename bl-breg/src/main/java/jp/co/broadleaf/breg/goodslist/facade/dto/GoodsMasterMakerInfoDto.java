//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : wangnan
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.goodslist.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 商品マスタ(メーカー)のDtoのクラス。
 */
public class GoodsMasterMakerInfoDto implements Serializable {

  /**  */
  private static final long serialVersionUID = 1361622167730714221L;
  /**
   * 商品マスタ(メーカー)
   */
  private List<GoodsMasterMakerDto> goodsMasterDto;
  /**
   * error
   */
  Boolean isErrorExist;
  /**
   * 商品マスタ(メーカー)
   */
  private List<GoodsGridDto> goodsGridDtoList;
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
   * errorの取得。
   *
   * @return error
   */
  public Boolean getIsErrorExist() {
    return isErrorExist;
  }

  /**
   * errorの設定。
   *
   * @param isErrorExist error
   */
  public void setIsErrorExist(Boolean isErrorExist) {
    this.isErrorExist = isErrorExist;
  }

  /**
   * 商品マスタ(メーカー)の取得。
   *
   * @return セットマスタ(メーカー)
   */
  public List<GoodsMasterMakerDto> getGoodsMasterDto() {
    return goodsMasterDto;
  }

  /**
   * 商品マスタ(メーカー)の設定。
   *
   * @param goodsMasterDto セットマスタ(メーカー)
   */
  public void setGoodsMasterDto(List<GoodsMasterMakerDto> goodsMasterDto) {
    this.goodsMasterDto = goodsMasterDto;
  }

  /**
   * 商品マスタ(メーカー)の取得。
   *
   * @return セットマスタ(メーカー)
   */
  public List<GoodsGridDto> getGoodsGridDtoList() {
    return goodsGridDtoList;
  }

  /**
   * 商品マスタ(メーカー)の設定。
   *
   * @param goodsGridDtoList セットマスタ(メーカー)
   */
  public void setGoodsGridDtoList(List<GoodsGridDto> goodsGridDtoList) {
    this.goodsGridDtoList = goodsGridDtoList;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(goodsMasterDto).append(isErrorExist).append(goodsGridDtoList)
        .toHashCode();
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
    GoodsMasterMakerInfoDto other = (GoodsMasterMakerInfoDto) obj;
    return new EqualsBuilder().append(goodsMasterDto, other.goodsMasterDto).append(isErrorExist, other.isErrorExist)
        .append(goodsGridDtoList, other.goodsGridDtoList).isEquals();
  }

}
