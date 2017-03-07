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
 * カーメーカーコードマスタ
 */
@Entity(name = "m_carmaker_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class CarmakerMasterCommon extends BroadleafDomainEntity<CarmakerMasterCommonId> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * 商品メーカーコード
   */
  public static final String GOODS_MAKER = "goodsMaker";

  /**
   * メーカーコード
   */
  public static final String MAKER_CODE = "makerCode";

  /**
   * メーカー全角名称
   */
  public static final String MAKER_FULL_NAME = "makerFullName";

  /**
   * メーカー半角名称
   */
  public static final String MAKER_HALF_NAME = "makerHalfName";

  /**
   * 提供日付
   */
  public static final String OFFER_DATE = "offerDate";

  /**
   * コンストラクタ。
   */
  public CarmakerMasterCommon() {

  }

  /**
   * コンストラクタ。
   * 
   * @param id ID
   */
  public CarmakerMasterCommon(CarmakerMasterCommonId id) {
    setGoodsMaker(id.getGoodsMaker());
    setMakerCode(id.getMakerCode());
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
   * メーカーコードを返します。
   * 
   * @return メーカーコード
   */
  public int getMakerCode() {
    return container().getPropertyValue(MAKER_CODE, Integer.class);
  }

  /**
   * メーカーコードを設定します。
   * 
   * @param makerCode セットするメーカーコード
   */
  public void setMakerCode(int makerCode) {
    container().setPropertyValue(MAKER_CODE, makerCode);
  }

  /**
   * メーカー全角名称を返します。
   * 
   * @return メーカー全角名称
   */
  public String getMakerFullName() {
    return container().getPropertyValue(MAKER_FULL_NAME, String.class);
  }

  /**
   * メーカー全角名称を設定します。
   * 
   * @param makerFullName セットするメーカー全角名称
   */
  public void setMakerFullName(String makerFullName) {
    container().setPropertyValue(MAKER_FULL_NAME, makerFullName);
  }

  /**
   * メーカー半角名称を返します。
   * 
   * @return メーカー半角名称
   */
  public String getMakerHalfName() {
    return container().getPropertyValue(MAKER_HALF_NAME, String.class);
  }

  /**
   * メーカー半角名称を設定します。
   * 
   * @param makerHalfName セットするメーカー半角名称
   */
  public void setMakerHalfName(String makerHalfName) {
    container().setPropertyValue(MAKER_HALF_NAME, makerHalfName);
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
  public CarmakerMasterCommonId identifier() {
    return new CarmakerMasterCommonId(getGoodsMaker(), getMakerCode());
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
    list.add(new PropertyDefinition(MAKER_CODE, Integer.class, false));
    list.add(new PropertyDefinition(MAKER_FULL_NAME, String.class, false));
    list.add(new PropertyDefinition(MAKER_HALF_NAME, String.class, false));
    list.add(new PropertyDefinition(OFFER_DATE, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
