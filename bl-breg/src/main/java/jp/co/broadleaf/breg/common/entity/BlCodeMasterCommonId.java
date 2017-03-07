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
 * BLコードマスタエンティティのIDクラス
 */
public class BlCodeMasterCommonId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public BlCodeMasterCommonId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param goodsMaker 商品メーカーコード
   * @param blCode BLコード
   */
  public BlCodeMasterCommonId(int goodsMaker, int blCode) {
    setGoodsMaker(goodsMaker);
    setBlCode(blCode);
  }

  /**
   * 商品メーカーコードを返します。
   * 
   * @return 商品メーカーコード
   */
  public int getGoodsMaker() {
    return container().getPropertyValue(BlCodeMasterCommon.GOODS_MAKER, Integer.class);
  }

  /**
   * 商品メーカーコードを設定します。
   * 
   * @param goodsMaker セットする商品メーカーコード
   */
  public void setGoodsMaker(int goodsMaker) {
    container().setPropertyValue(BlCodeMasterCommon.GOODS_MAKER, goodsMaker);
  }

  /**
   * BLコードを返します。
   * 
   * @return BLコード
   */
  public int getBlCode() {
    return container().getPropertyValue(BlCodeMasterCommon.BL_CODE, Integer.class);
  }

  /**
   * BLコードを設定します。
   * 
   * @param blCode セットするBLコード
   */
  public void setBlCode(int blCode) {
    container().setPropertyValue(BlCodeMasterCommon.BL_CODE, blCode);
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
    list.add(new PropertyDefinition(BlCodeMasterCommon.GOODS_MAKER, Integer.class, false));
    list.add(new PropertyDefinition(BlCodeMasterCommon.BL_CODE, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
