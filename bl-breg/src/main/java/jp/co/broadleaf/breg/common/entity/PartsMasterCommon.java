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
 * 層別マスタ
 */
@Entity(name = "m_parts_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class PartsMasterCommon extends BroadleafDomainEntity<PartsMasterCommonId> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * 商品メーカーコード
   */
  public static final String GOODS_MAKER = "goodsMaker";

  /**
   * 層別コード
   */
  public static final String PARTS_LAYER = "partsLayer";

  /**
   * 層別名称
   */
  public static final String PARTS_LAYER_NAME = "partsLayerName";

  /**
   * 提供期日
   */
  public static final String OFFER_DATE = "offerDate";

  /**
   * コンストラクタ。
   */
  public PartsMasterCommon() {

  }

  /**
   * コンストラクタ。
   * 
   * @param id ID
   */
  public PartsMasterCommon(PartsMasterCommonId id) {
    setGoodsMaker(id.getGoodsMaker());
    setPartsLayer(id.getPartsLayer());
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
   * 層別コードを返します。
   * 
   * @return 層別コード
   */
  public String getPartsLayer() {
    return container().getPropertyValue(PARTS_LAYER, String.class);
  }

  /**
   * 層別コードを設定します。
   * 
   * @param partsLayer セットする層別コード
   */
  public void setPartsLayer(String partsLayer) {
    container().setPropertyValue(PARTS_LAYER, partsLayer);
  }

  /**
   * 層別名称を返します。
   * 
   * @return 層別名称
   */
  public String getPartsLayerName() {
    return container().getPropertyValue(PARTS_LAYER_NAME, String.class);
  }

  /**
   * 層別名称を設定します。
   * 
   * @param partsLayerName セットする層別名称
   */
  public void setPartsLayerName(String partsLayerName) {
    container().setPropertyValue(PARTS_LAYER_NAME, partsLayerName);
  }

  /**
   * 提供期日を返します。
   * 
   * @return 提供期日
   */
  public int getOfferDate() {
    return container().getPropertyValue(OFFER_DATE, Integer.class);
  }

  /**
   * 提供期日を設定します。
   * 
   * @param offerDate セットする提供期日
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
  public PartsMasterCommonId identifier() {
    return new PartsMasterCommonId(getGoodsMaker(), getPartsLayer());
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
    list.add(new PropertyDefinition(PARTS_LAYER, String.class, false));
    list.add(new PropertyDefinition(PARTS_LAYER_NAME, String.class, true));
    list.add(new PropertyDefinition(OFFER_DATE, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
