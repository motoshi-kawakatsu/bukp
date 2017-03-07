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

import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.DomainEntityId;

import java.util.ArrayList;
import java.util.List;

/**
 * 層別マスタエンティティのIDクラス
 */
public class PartsMasterCommonId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public PartsMasterCommonId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param goodsMaker 商品メーカーコード
   * @param partsLayer 層別コード
   */
  public PartsMasterCommonId(int goodsMaker, String partsLayer) {
    setGoodsMaker(goodsMaker);
    setPartsLayer(partsLayer);
  }

  /**
   * 商品メーカーコードを返します。
   * 
   * @return 商品メーカーコード
   */
  public int getGoodsMaker() {
    return container().getPropertyValue(PartsMasterCommon.GOODS_MAKER, Integer.class);
  }

  /**
   * 商品メーカーコードを設定します。
   * 
   * @param goodsMaker セットする商品メーカーコード
   */
  public void setGoodsMaker(int goodsMaker) {
    container().setPropertyValue(PartsMasterCommon.GOODS_MAKER, goodsMaker);
  }

  /**
   * 層別コードを返します。
   * 
   * @return 層別コード
   */
  public String getPartsLayer() {
    return container().getPropertyValue(PartsMasterCommon.PARTS_LAYER, String.class);
  }

  /**
   * 層別コードを設定します。
   * 
   * @param partsLayer セットする層別コード
   */
  public void setPartsLayer(String partsLayer) {
    container().setPropertyValue(PartsMasterCommon.PARTS_LAYER, partsLayer);
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
    list.add(new PropertyDefinition(PartsMasterCommon.GOODS_MAKER, Integer.class, false));
    list.add(new PropertyDefinition(PartsMasterCommon.PARTS_LAYER, String.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
