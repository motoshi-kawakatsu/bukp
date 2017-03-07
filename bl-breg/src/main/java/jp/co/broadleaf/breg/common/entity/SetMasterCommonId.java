package jp.co.broadleaf.breg.common.entity;

import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.DomainEntityId;

import java.util.ArrayList;
import java.util.List;

/**
 * セットマスタ(共有)エンティティのIDクラス
 */
public class SetMasterCommonId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public SetMasterCommonId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param setMainPartsNo セット親品番
   * @param setDispOrder セット表示順位
   * @param applyNo 申請No
   */
  public SetMasterCommonId(int prmSetDtlNo1, int partsMakerCd, String setMainPartsNo, int setDispOrder, int applyNo) {
    setPrmSetDtlNo1(prmSetDtlNo1);
    setPartsMakerCd(partsMakerCd);
    setSetMainPartsNo(setMainPartsNo);
    setSetDispOrder(setDispOrder);
    setApplyNo(applyNo);
  }

  /**
   * 優良設定詳細コード１を返します。
   * 
   * @return 優良設定詳細コード１
   */
  public int getPrmSetDtlNo1() {
    return container().getPropertyValue(SetMasterCommon.PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 優良設定詳細コード１を設定します。
   * 
   * @param prmSetDtlNo1 セットする優良設定詳細コード１
   */
  public void setPrmSetDtlNo1(int prmSetDtlNo1) {
    container().setPropertyValue(SetMasterCommon.PRM_SET_DTL_NO_1, prmSetDtlNo1);
  }

  /**
   * 部品メーカーコードを返します。
   * 
   * @return 部品メーカーコード
   */
  public int getPartsMakerCd() {
    return container().getPropertyValue(SetMasterCommon.PARTS_MAKER_CD, Integer.class);
  }

  /**
   * 部品メーカーコードを設定します。
   * 
   * @param partsMakerCd セットする部品メーカーコード
   */
  public void setPartsMakerCd(int partsMakerCd) {
    container().setPropertyValue(SetMasterCommon.PARTS_MAKER_CD, partsMakerCd);
  }

  /**
   * セット親品番を返します。
   * 
   * @return セット親品番
   */
  public String getSetMainPartsNo() {
    return container().getPropertyValue(SetMasterCommon.SET_MAIN_PARTS_NO, String.class);
  }

  /**
   * セット親品番を設定します。
   * 
   * @param setMainPartsNo セットするセット親品番
   */
  public void setSetMainPartsNo(String setMainPartsNo) {
    container().setPropertyValue(SetMasterCommon.SET_MAIN_PARTS_NO, setMainPartsNo);
  }

  /**
   * セット表示順位を返します。
   * 
   * @return セット表示順位
   */
  public int getSetDispOrder() {
    return container().getPropertyValue(SetMasterCommon.SET_DISP_ORDER, Integer.class);
  }

  /**
   * セット表示順位を設定します。
   * 
   * @param setDispOrder セットするセット表示順位
   */
  public void setSetDispOrder(int setDispOrder) {
    container().setPropertyValue(SetMasterCommon.SET_DISP_ORDER, setDispOrder);
  }

  /**
   * 申請Noを返します。
   * 
   * @return 申請No
   */
  public int getApplyNo() {
    return container().getPropertyValue(SetMasterCommon.APPLY_NO, Integer.class);
  }

  /**
   * 申請Noを設定します。
   * 
   * @param applyNo セットする申請No
   */
  public void setApplyNo(int applyNo) {
    container().setPropertyValue(SetMasterCommon.APPLY_NO, applyNo);
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
    list.add(new PropertyDefinition(SetMasterCommon.PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(SetMasterCommon.PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(SetMasterCommon.SET_MAIN_PARTS_NO, String.class, false));
    list.add(new PropertyDefinition(SetMasterCommon.SET_DISP_ORDER, Integer.class, false));
    list.add(new PropertyDefinition(SetMasterCommon.APPLY_NO, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
