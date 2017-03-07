//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/24   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.common.property;

import jp.co.broadleaf.common.domain.entity.BroadleafDomainEntity;
import jp.co.broadleaf.common.domain.entity.BroadleafOptimisticConcurrencyResolver;
import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.Entity;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrency;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * プロパティ定義
 */
@Entity(name = "t_property_define_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class PropertyDefine extends BroadleafDomainEntity<String> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * プロパティキー
   */
  public static final String PROPERTY_KEY = "propertyKey";

  /**
   * プロダクトコード
   */
  public static final String PRODUCT_CD = "productCd";

  /**
   * プロパティ値
   */
  public static final String PROPERTY_VAL = "propertyVal";

  /**
   * データ型区分
   */
  public static final String DATA_TYPE_DIV = "dataTypeDiv";

  /**
   * コンストラクタ。
   */
  public PropertyDefine() {

  }

  /**
   * コンストラクタ。
   * 
   * @param propertyKey ID
   */
  public PropertyDefine(String propertyKey) {
    setPropertyKey(propertyKey);
  }

  /**
   * プロパティキーを返します。
   * 
   * @return プロパティキー
   */
  public String getPropertyKey() {
    return container().getPropertyValue(PROPERTY_KEY, String.class);
  }

  /**
   * プロパティキーを設定します。
   * 
   * @param propertyKey セットするプロパティキー
   */
  public void setPropertyKey(String propertyKey) {
    container().setPropertyValue(PROPERTY_KEY, propertyKey);
  }

  /**
   * プロダクトコードを返します。
   * 
   * @return プロダクトコード
   */
  public int getProductCd() {
    return container().getPropertyValue(PRODUCT_CD, Integer.class);
  }

  /**
   * プロダクトコードを設定します。
   * 
   * @param productCd セットするプロダクトコード
   */
  public void setProductCd(int productCd) {
    container().setPropertyValue(PRODUCT_CD, productCd);
  }

  /**
   * プロパティ値を返します。
   * 
   * @return プロパティ値
   */
  public String getPropertyVal() {
    return container().getPropertyValue(PROPERTY_VAL, String.class);
  }

  /**
   * プロパティ値を設定します。
   * 
   * @param propertyVal セットするプロパティ値
   */
  public void setPropertyVal(String propertyVal) {
    container().setPropertyValue(PROPERTY_VAL, propertyVal);
  }

  /**
   * データ型区分を返します。
   * 
   * @return データ型区分
   */
  public int getDataTypeDiv() {
    return container().getPropertyValue(DATA_TYPE_DIV, Integer.class);
  }

  /**
   * データ型区分を設定します。
   * 
   * @param dataTypeDiv セットするデータ型区分
   */
  public void setDataTypeDiv(int dataTypeDiv) {
    container().setPropertyValue(DATA_TYPE_DIV, dataTypeDiv);
  }

  /**
   * エンティティIDを取得する。
   * 
   * @return エンティティID
   */
  @Override
  public String identifier() {
    return getPropertyKey();
  }

  /**
   * エンティティIDのプロパティリストを取得する
   * 
   * @return エンティティIDのプロパティリスト
   */
  @Override
  public String[] identifierPropertyNames() {
    return new String[] { PROPERTY_KEY };
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
    list.add(new PropertyDefinition(PROPERTY_KEY, String.class, false));
    list.add(new PropertyDefinition(PRODUCT_CD, Integer.class, false));
    list.add(new PropertyDefinition(PROPERTY_VAL, String.class, false));
    list.add(new PropertyDefinition(DATA_TYPE_DIV, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
