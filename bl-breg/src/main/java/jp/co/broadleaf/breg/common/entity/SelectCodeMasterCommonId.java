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
 * セレクトコードマストエンティティのIDクラス
 */
public class SelectCodeMasterCommonId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public SelectCodeMasterCommonId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param goodsMakerCd 商品メーカーコード
   * @param prmSetDtlNo1 優良設定詳細コード１
   */
  public SelectCodeMasterCommonId(int goodsMakerCd, int prmSetDtlNo1) {
    setGoodsMakerCd(goodsMakerCd);
    setPrmSetDtlNo1(prmSetDtlNo1);
  }

  /**
   * 商品メーカーコードを返します。
   * 
   * @return 商品メーカーコード
   */
  public int getGoodsMakerCd() {
    return container().getPropertyValue(SelectCodeMasterCommon.GOODS_MAKER_CD, Integer.class);
  }

  /**
   * 商品メーカーコードを設定します。
   * 
   * @param goodsMakerCd セットする商品メーカーコード
   */
  public void setGoodsMakerCd(int goodsMakerCd) {
    container().setPropertyValue(SelectCodeMasterCommon.GOODS_MAKER_CD, goodsMakerCd);
  }

  /**
   * 優良設定詳細コード１を返します。
   * 
   * @return 優良設定詳細コード１
   */
  public int getPrmSetDtlNo1() {
    return container().getPropertyValue(SelectCodeMasterCommon.PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 優良設定詳細コード１を設定します。
   * 
   * @param prmSetDtlNo1 セットする優良設定詳細コード１
   */
  public void setPrmSetDtlNo1(int prmSetDtlNo1) {
    container().setPropertyValue(SelectCodeMasterCommon.PRM_SET_DTL_NO_1, prmSetDtlNo1);
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
    list.add(new PropertyDefinition(SelectCodeMasterCommon.GOODS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(SelectCodeMasterCommon.PRM_SET_DTL_NO_1, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
