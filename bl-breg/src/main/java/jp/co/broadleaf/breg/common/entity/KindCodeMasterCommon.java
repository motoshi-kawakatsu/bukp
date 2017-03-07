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
 * 種別コードマスト
 */
@Entity(name = "m_kind_code_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class KindCodeMasterCommon extends BroadleafDomainEntity<KindCodeMasterCommonId> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * 商品メーカーコード
   */
  public static final String GOODS_MAKER = "goodsMaker";

  /**
   * 種別コード
   */
  public static final String KIND_CD = "kindCd";

  /**
   * 種別名称
   */
  public static final String KIND_NAME = "kindName";

  /**
   * 提供日付
   */
  public static final String OFFER_DATE = "offerDate";

  /**
   * コンストラクタ。
   */
  public KindCodeMasterCommon() {

  }

  /**
   * コンストラクタ。
   * 
   * @param id ID
   */
  public KindCodeMasterCommon(KindCodeMasterCommonId id) {
    setKindCd(id.getKindCd());
    setKindName(id.getKindName());
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
   * 種別コードを返します。
   * 
   * @return 種別コード
   */
  public int getKindCd() {
    return container().getPropertyValue(KIND_CD, Integer.class);
  }

  /**
   * 種別コードを設定します。
   * 
   * @param kindCd セットする種別コード
   */
  public void setKindCd(int kindCd) {
    container().setPropertyValue(KIND_CD, kindCd);
  }

  /**
   * 種別名称を返します。
   * 
   * @return 種別名称
   */
  public String getKindName() {
    return container().getPropertyValue(KIND_NAME, String.class);
  }

  /**
   * 種別名称を設定します。
   * 
   * @param kindName セットする種別名称
   */
  public void setKindName(String kindName) {
    container().setPropertyValue(KIND_NAME, kindName);
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
  public KindCodeMasterCommonId identifier() {
    return new KindCodeMasterCommonId(getKindCd(), getKindName());
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
    list.add(new PropertyDefinition(KIND_CD, Integer.class, false));
    list.add(new PropertyDefinition(KIND_NAME, String.class, false));
    list.add(new PropertyDefinition(OFFER_DATE, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
