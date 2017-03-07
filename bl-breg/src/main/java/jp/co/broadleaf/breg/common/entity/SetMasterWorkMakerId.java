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
 * セットマスタ(メーカー)ワークエンティティのIDクラス
 */
public class SetMasterWorkMakerId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public SetMasterWorkMakerId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param setMainPartsNo セット親品番
   * @param setDispOrder セット表示順位
   */
  public SetMasterWorkMakerId(int prmSetDtlNo1, int partsMakerCd, String setMainPartsNo, int setDispOrder) {
    setPrmSetDtlNo1(prmSetDtlNo1);
    setPartsMakerCd(partsMakerCd);
    setSetMainPartsNo(setMainPartsNo);
    setSetDispOrder(setDispOrder);
  }

  /**
   * 優良設定詳細コード１を返します。
   * 
   * @return 優良設定詳細コード１
   */
  public int getPrmSetDtlNo1() {
    return container().getPropertyValue(SetMasterWorkMaker.PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 優良設定詳細コード１を設定します。
   * 
   * @param prmSetDtlNo1 セットする優良設定詳細コード１
   */
  public void setPrmSetDtlNo1(int prmSetDtlNo1) {
    container().setPropertyValue(SetMasterWorkMaker.PRM_SET_DTL_NO_1, prmSetDtlNo1);
  }

  /**
   * 部品メーカーコードを返します。
   * 
   * @return 部品メーカーコード
   */
  public int getPartsMakerCd() {
    return container().getPropertyValue(SetMasterWorkMaker.PARTS_MAKER_CD, Integer.class);
  }

  /**
   * 部品メーカーコードを設定します。
   * 
   * @param partsMakerCd セットする部品メーカーコード
   */
  public void setPartsMakerCd(int partsMakerCd) {
    container().setPropertyValue(SetMasterWorkMaker.PARTS_MAKER_CD, partsMakerCd);
  }

  /**
   * セット親品番を返します。
   * 
   * @return セット親品番
   */
  public String getSetMainPartsNo() {
    return container().getPropertyValue(SetMasterWorkMaker.SET_MAIN_PARTS_NO, String.class);
  }

  /**
   * セット親品番を設定します。
   * 
   * @param setMainPartsNo セットするセット親品番
   */
  public void setSetMainPartsNo(String setMainPartsNo) {
    container().setPropertyValue(SetMasterWorkMaker.SET_MAIN_PARTS_NO, setMainPartsNo);
  }

  /**
   * セット表示順位を返します。
   * 
   * @return セット表示順位
   */
  public int getSetDispOrder() {
    return container().getPropertyValue(SetMasterWorkMaker.SET_DISP_ORDER, Integer.class);
  }

  /**
   * セット表示順位を設定します。
   * 
   * @param setDispOrder セットするセット表示順位
   */
  public void setSetDispOrder(int setDispOrder) {
    container().setPropertyValue(SetMasterWorkMaker.SET_DISP_ORDER, setDispOrder);
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
    list.add(new PropertyDefinition(SetMasterWorkMaker.PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(SetMasterWorkMaker.PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(SetMasterWorkMaker.SET_MAIN_PARTS_NO, String.class, false));
    list.add(new PropertyDefinition(SetMasterWorkMaker.SET_DISP_ORDER, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
