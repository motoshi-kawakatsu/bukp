//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.entity;

import jp.co.broadleaf.common.domain.entity.BroadleafDomainEntity;
import jp.co.broadleaf.common.domain.entity.BroadleafOptimisticConcurrencyResolver;
import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.Entity;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrency;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品中分類マスタ
 */
@Entity(name = "m_goods_rate_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class GoodsRateMasterCommon extends BroadleafDomainEntity<Integer> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * 商品メーカーコード
   */
  public static final String GOODS_MAKER = "goodsMaker";

  /**
   * 商品中分類コード
   */
  public static final String GOODS_RATE_GRP_CODE = "goodsRateGrpCode";

  /**
   * 商品中分類名称 
   */
  public static final String GOODS_RATE_GRP_NAME = "goodsRateGrpName";

  /**
   * 提供日付
   */
  public static final String OFFER_DATE = "offerDate";

  /**
   * コンストラクタ。
   */
  public GoodsRateMasterCommon() {

  }

  /**
   * コンストラクタ。
   * 
   * @param goodsMaker ID
   */
  public GoodsRateMasterCommon(int goodsMaker) {
    setGoodsMaker(goodsMaker);
  }

  /**
   * 商品メーカーコードを返します。
   * 
   * @return 商品メーカーコード
   */
  public int getGoodsMaker() {
    return container().getPropertyValue(GOODS_MAKER, Integer.class);
  }

  /**
   * 商品メーカーコードを設定します。
   * 
   * @param goodsMaker セットする商品メーカーコード
   */
  public void setGoodsMaker(int goodsMaker) {
    container().setPropertyValue(GOODS_MAKER, goodsMaker);
  }

  /**
   * 商品中分類コードを返します。
   * 
   * @return 商品中分類コード
   */
  public int getGoodsRateGrpCode() {
    return container().getPropertyValue(GOODS_RATE_GRP_CODE, Integer.class);
  }

  /**
   * 商品中分類コードを設定します。
   * 
   * @param goodsRateGrpCode セットする商品中分類コード
   */
  public void setGoodsRateGrpCode(int goodsRateGrpCode) {
    container().setPropertyValue(GOODS_RATE_GRP_CODE, goodsRateGrpCode);
  }

  /**
   * 商品中分類名称 を返します。
   * 
   * @return 商品中分類名称 
   */
  public String getGoodsRateGrpName() {
    return container().getPropertyValue(GOODS_RATE_GRP_NAME, String.class);
  }

  /**
   * 商品中分類名称 を設定します。
   * 
   * @param goodsRateGrpName セットする商品中分類名称 
   */
  public void setGoodsRateGrpName(String goodsRateGrpName) {
    container().setPropertyValue(GOODS_RATE_GRP_NAME, goodsRateGrpName);
  }

  /**
   * 提供日付を返します。
   * 
   * @return 提供日付
   */
  public int getOfferDate() {
    return container().getPropertyValue(OFFER_DATE, Integer.class);
  }

  /**
   * 提供日付を設定します。
   * 
   * @param offerDate セットする提供日付
   */
  public void setOfferDate(int offerDate) {
    container().setPropertyValue(OFFER_DATE, offerDate);
  }

  /**
   * エンティティIDを取得する。
   * 
   * @return エンティティID
   */
  @Override
  public Integer identifier() {
    return getGoodsMaker();
  }

  /**
   * エンティティIDのプロパティリストを取得する
   * 
   * @return エンティティIDのプロパティリスト
   */
  @Override
  public String[] identifierPropertyNames() {
    return new String[] { GOODS_MAKER };
  }

  /**
   * プロパティ定義の配列を取得する。
   * 
   * @return プロパティ定義の配列
   */
  @Override
  public PropertyDefinition[] defineProperties() {
    return ArrayUtils.addAll(super.defineProperties(), definitions);
  }

  /**
   * プロパティ定義の配列
   */
  private static PropertyDefinition[] definitions;

  static {
    List<PropertyDefinition> list = new ArrayList<PropertyDefinition>();
    list.add(new PropertyDefinition(GOODS_MAKER, Integer.class, false));
    list.add(new PropertyDefinition(GOODS_RATE_GRP_CODE, Integer.class, false));
    list.add(new PropertyDefinition(GOODS_RATE_GRP_NAME, String.class, false));
    list.add(new PropertyDefinition(OFFER_DATE, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
