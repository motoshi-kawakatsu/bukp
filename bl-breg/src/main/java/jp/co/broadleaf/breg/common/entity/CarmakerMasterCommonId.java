package jp.co.broadleaf.breg.common.entity;

import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.DomainEntityId;

import java.util.ArrayList;
import java.util.List;

/**
 * カーメーカーコードマスタエンティティのIDクラス
 */
public class CarmakerMasterCommonId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public CarmakerMasterCommonId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param goodsMaker 商品メーカーコード
   * @param makerCode メーカーコード
   */
  public CarmakerMasterCommonId(int goodsMaker, int makerCode) {
    setGoodsMaker(goodsMaker);
    setMakerCode(makerCode);
  }

  /**
   * 商品メーカーコードを返します。
   * 
   * @return 商品メーカーコード
   */
  public int getGoodsMaker() {
    return container().getPropertyValue(CarmakerMasterCommon.GOODS_MAKER, Integer.class);
  }

  /**
   * 商品メーカーコードを設定します。
   * 
   * @param goodsMaker セットする商品メーカーコード
   */
  public void setGoodsMaker(int goodsMaker) {
    container().setPropertyValue(CarmakerMasterCommon.GOODS_MAKER, goodsMaker);
  }

  /**
   * メーカーコードを返します。
   * 
   * @return メーカーコード
   */
  public int getMakerCode() {
    return container().getPropertyValue(CarmakerMasterCommon.MAKER_CODE, Integer.class);
  }

  /**
   * メーカーコードを設定します。
   * 
   * @param makerCode セットするメーカーコード
   */
  public void setMakerCode(int makerCode) {
    container().setPropertyValue(CarmakerMasterCommon.MAKER_CODE, makerCode);
  }

  /**
   * プロパティ定義の配列を取得する。
   * 
   * @return プロパティ定義の配列
   */
  @Override
  public PropertyDefinition[] defineProperties() {
    return definitions;
  }

  /**
   * プロパティ定義の配列
   */
  private static PropertyDefinition[] definitions;

  static {
    List<PropertyDefinition> list = new ArrayList<PropertyDefinition>();
    list.add(new PropertyDefinition(CarmakerMasterCommon.GOODS_MAKER, Integer.class, false));
    list.add(new PropertyDefinition(CarmakerMasterCommon.MAKER_CODE, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
