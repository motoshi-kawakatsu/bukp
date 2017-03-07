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
 * 商品マスタ(メーカー)本エンティティのIDクラス
 */
public class GoodsMasterMakerId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public GoodsMasterMakerId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param primePartsNoWithH 優良品番(－付き品番)
   */
  public GoodsMasterMakerId(int prmSetDtlNo1, int partsMakerCd, String primePartsNoWithH) {
    setPrmSetDtlNo1(prmSetDtlNo1);
    setPartsMakerCd(partsMakerCd);
    setPrimePartsNoWithH(primePartsNoWithH);
  }

  /**
   * 優良設定詳細コード１を返します。
   * 
   * @return 優良設定詳細コード１
   */
  public int getPrmSetDtlNo1() {
    return container().getPropertyValue(GoodsMasterMaker.PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 優良設定詳細コード１を設定します。
   * 
   * @param prmSetDtlNo1 セットする優良設定詳細コード１
   */
  public void setPrmSetDtlNo1(int prmSetDtlNo1) {
    container().setPropertyValue(GoodsMasterMaker.PRM_SET_DTL_NO_1, prmSetDtlNo1);
  }

  /**
   * 部品メーカーコードを返します。
   * 
   * @return 部品メーカーコード
   */
  public int getPartsMakerCd() {
    return container().getPropertyValue(GoodsMasterMaker.PARTS_MAKER_CD, Integer.class);
  }

  /**
   * 部品メーカーコードを設定します。
   * 
   * @param partsMakerCd セットする部品メーカーコード
   */
  public void setPartsMakerCd(int partsMakerCd) {
    container().setPropertyValue(GoodsMasterMaker.PARTS_MAKER_CD, partsMakerCd);
  }

  /**
   * 優良品番(－付き品番)を返します。
   * 
   * @return 優良品番(－付き品番)
   */
  public String getPrimePartsNoWithH() {
    return container().getPropertyValue(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H, String.class);
  }

  /**
   * 優良品番(－付き品番)を設定します。
   * 
   * @param primePartsNoWithH セットする優良品番(－付き品番)
   */
  public void setPrimePartsNoWithH(String primePartsNoWithH) {
    container().setPropertyValue(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H, primePartsNoWithH);
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
    list.add(new PropertyDefinition(GoodsMasterMaker.PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(GoodsMasterMaker.PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(GoodsMasterMaker.PRIME_PARTS_NO_WITH_H, String.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
