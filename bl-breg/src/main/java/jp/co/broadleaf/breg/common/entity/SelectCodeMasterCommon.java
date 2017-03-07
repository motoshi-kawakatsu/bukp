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
 * セレクトコードマスト
 */
@Entity(name = "m_select_code_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class SelectCodeMasterCommon extends BroadleafDomainEntity<SelectCodeMasterCommonId> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * 商品メーカーコード
   */
  public static final String GOODS_MAKER_CD = "goodsMakerCd";

  /**
   * 優良設定詳細コード１
   */
  public static final String PRM_SET_DTL_NO_1 = "prmSetDtlNo1";

  /**
   * 優良設定詳細名称
   */
  public static final String PRM_SET_DTL_NAME = "prmSetDtlName";

  /**
   * 提供期日
   */
  public static final String OFFER_DATE = "offerDate";

  /**
   * コンストラクタ。
   */
  public SelectCodeMasterCommon() {

  }

  /**
   * コンストラクタ。
   * 
   * @param id ID
   */
  public SelectCodeMasterCommon(SelectCodeMasterCommonId id) {
    setGoodsMakerCd(id.getGoodsMakerCd());
    setPrmSetDtlNo1(id.getPrmSetDtlNo1());
  }

  /**
   * 商品メーカーコードを返します。
   * 
   * @return 商品メーカーコード
   */
  public int getGoodsMakerCd() {
    return container().getPropertyValue(GOODS_MAKER_CD, Integer.class);
  }

  /**
   * 商品メーカーコードを設定します。
   * 
   * @param goodsMakerCd セットする商品メーカーコード
   */
  public void setGoodsMakerCd(int goodsMakerCd) {
    container().setPropertyValue(GOODS_MAKER_CD, goodsMakerCd);
  }

  /**
   * 優良設定詳細コード１を返します。
   * 
   * @return 優良設定詳細コード１
   */
  public int getPrmSetDtlNo1() {
    return container().getPropertyValue(PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 優良設定詳細コード１を設定します。
   * 
   * @param prmSetDtlNo1 セットする優良設定詳細コード１
   */
  public void setPrmSetDtlNo1(int prmSetDtlNo1) {
    container().setPropertyValue(PRM_SET_DTL_NO_1, prmSetDtlNo1);
  }

  /**
   * 優良設定詳細名称を返します。
   * 
   * @return 優良設定詳細名称
   */
  public String getPrmSetDtlName() {
    return container().getPropertyValue(PRM_SET_DTL_NAME, String.class);
  }

  /**
   * 優良設定詳細名称を設定します。
   * 
   * @param prmSetDtlName セットする優良設定詳細名称
   */
  public void setPrmSetDtlName(String prmSetDtlName) {
    container().setPropertyValue(PRM_SET_DTL_NAME, prmSetDtlName);
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
  public SelectCodeMasterCommonId identifier() {
    return new SelectCodeMasterCommonId(getGoodsMakerCd(), getPrmSetDtlNo1());
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
    list.add(new PropertyDefinition(GOODS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(PRM_SET_DTL_NAME, String.class, true));
    list.add(new PropertyDefinition(OFFER_DATE, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
