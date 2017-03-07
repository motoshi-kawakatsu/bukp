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
package jp.co.broadleaf.breg.goodslist.domain.model;

import jp.co.broadleaf.breg.common.entity.GoodsMasterWorkMaker;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * <pre>
 * 商品マスタ(メーカー)Model.
 * </pre>
 */
public class GoodsMasterWorkMakerModel {
  /**
   * 商品マスタ(メーカー)
   */
  private List<GoodsMasterWorkMaker> goodsWorkList;

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
   * 商品マスタ(メーカー)の取得。
   *
   * @return 商品マスタ(メーカー)
   */
  public List<GoodsMasterWorkMaker> getGoodsList() {
    return goodsWorkList;
  }

  /**
   * 商品マスタ(メーカー)の設定。
   *
   * @param goodsWorkListNew 商品マスタ(メーカー)
   */
  public void setGoodsMasterWorkMakerList(List<GoodsMasterWorkMaker> goodsWorkListNew) {
    this.goodsWorkList = goodsWorkListNew;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(goodsWorkList).toHashCode();
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
    GoodsMasterWorkMakerModel other = (GoodsMasterWorkMakerModel) obj;
    return new EqualsBuilder().append(goodsWorkList, other.goodsWorkList).isEquals();
  }
}
