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
 * BLコードマスタ
 */
@Entity(name = "m_bl_code_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class BlCodeMasterCommon extends BroadleafDomainEntity<BlCodeMasterCommonId> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * 商品メーカーコード
   */
  public static final String GOODS_MAKER = "goodsMaker";

  /**
   * BLグループコード
   */
  public static final String BL_GROUP_CODE = "blGroupCode";

  /**
   * BLコード
   */
  public static final String BL_CODE = "blCode";

  /**
   * BLコード枝番
   */
  public static final String BL_EDA_CODE = "blEdaCode";

  /**
   * BLコード名称（全角）
   */
  public static final String BL_FULL_NAME = "blFullName";

  /**
   * BLコード名称（半角）
   */
  public static final String BL_HALF_NAME = "blHalfName";

  /**
   * 商品中分類コード
   */
  public static final String GOODS_RATE_GRP_CODE = "goodsRateGrpCode";

  /**
   * 提供日付
   */
  public static final String OFFER_DATE = "offerDate";

  /**
   * 装備分類
   */
  public static final String EQUIP_GROUP = "equipGroup";

  /**
   * 優良検索区分
   */
  public static final String PRIME_SEARCH_KBN = "primeSearchKbn";

  /**
   * コンストラクタ。
   */
  public BlCodeMasterCommon() {

  }

  /**
   * コンストラクタ。
   * 
   * @param id ID
   */
  public BlCodeMasterCommon(BlCodeMasterCommonId id) {
    setGoodsMaker(id.getGoodsMaker());
    setBlCode(id.getBlCode());
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
   * BLグループコードを返します。
   * 
   * @return BLグループコード
   */
  public int getBlGroupCode() {
    return container().getPropertyValue(BL_GROUP_CODE, Integer.class);
  }

  /**
   * BLグループコードを設定します。
   * 
   * @param blGroupCode セットするBLグループコード
   */
  public void setBlGroupCode(int blGroupCode) {
    container().setPropertyValue(BL_GROUP_CODE, blGroupCode);
  }

  /**
   * BLコードを返します。
   * 
   * @return BLコード
   */
  public int getBlCode() {
    return container().getPropertyValue(BL_CODE, Integer.class);
  }

  /**
   * BLコードを設定します。
   * 
   * @param blCode セットするBLコード
   */
  public void setBlCode(int blCode) {
    container().setPropertyValue(BL_CODE, blCode);
  }

  /**
   * BLコード枝番を返します。
   * 
   * @return BLコード枝番
   */
  public int getBlEdaCode() {
    return container().getPropertyValue(BL_EDA_CODE, Integer.class);
  }

  /**
   * BLコード枝番を設定します。
   * 
   * @param blEdaCode セットするBLコード枝番
   */
  public void setBlEdaCode(int blEdaCode) {
    container().setPropertyValue(BL_EDA_CODE, blEdaCode);
  }

  /**
   * BLコード名称（全角）を返します。
   * 
   * @return BLコード名称（全角）
   */
  public String getBlFullName() {
    return container().getPropertyValue(BL_FULL_NAME, String.class);
  }

  /**
   * BLコード名称（全角）を設定します。
   * 
   * @param blFullName セットするBLコード名称（全角）
   */
  public void setBlFullName(String blFullName) {
    container().setPropertyValue(BL_FULL_NAME, blFullName);
  }

  /**
   * BLコード名称（半角）を返します。
   * 
   * @return BLコード名称（半角）
   */
  public String getBlHalfName() {
    return container().getPropertyValue(BL_HALF_NAME, String.class);
  }

  /**
   * BLコード名称（半角）を設定します。
   * 
   * @param blHalfName セットするBLコード名称（半角）
   */
  public void setBlHalfName(String blHalfName) {
    container().setPropertyValue(BL_HALF_NAME, blHalfName);
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
   * 装備分類を返します。
   * 
   * @return 装備分類
   */
  public int getEquipGroup() {
    return container().getPropertyValue(EQUIP_GROUP, Integer.class);
  }

  /**
   * 装備分類を設定します。
   * 
   * @param equipGroup セットする装備分類
   */
  public void setEquipGroup(int equipGroup) {
    container().setPropertyValue(EQUIP_GROUP, equipGroup);
  }

  /**
   * 優良検索区分を返します。
   * 
   * @return 優良検索区分
   */
  public int getPrimeSearchKbn() {
    return container().getPropertyValue(PRIME_SEARCH_KBN, Integer.class);
  }

  /**
   * 優良検索区分を設定します。
   * 
   * @param primeSearchKbn セットする優良検索区分
   */
  public void setPrimeSearchKbn(int primeSearchKbn) {
    container().setPropertyValue(PRIME_SEARCH_KBN, primeSearchKbn);
  }

  /**
   * エンティティIDを取得する。
   * 
   * @return エンティティID
   */
  @Override
  public BlCodeMasterCommonId identifier() {
    return new BlCodeMasterCommonId(getGoodsMaker(), getBlCode());
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
    list.add(new PropertyDefinition(BL_GROUP_CODE, Integer.class, false));
    list.add(new PropertyDefinition(BL_CODE, Integer.class, false));
    list.add(new PropertyDefinition(BL_EDA_CODE, Integer.class, false));
    list.add(new PropertyDefinition(BL_FULL_NAME, String.class, false));
    list.add(new PropertyDefinition(BL_HALF_NAME, String.class, false));
    list.add(new PropertyDefinition(GOODS_RATE_GRP_CODE, Integer.class, false));
    list.add(new PropertyDefinition(OFFER_DATE, Integer.class, false));
    list.add(new PropertyDefinition(EQUIP_GROUP, Integer.class, false));
    list.add(new PropertyDefinition(PRIME_SEARCH_KBN, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
