package jp.co.broadleaf.breg.common.entity;

import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.DomainEntityId;

import java.util.ArrayList;
import java.util.List;

/**
 * 純正部品情報マスタエンティティのIDクラス
 */
public class PuregoodsMasterCommonId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public PuregoodsMasterCommonId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param prmSetDtlNo1 純正設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param primePartsNoWithH 純正品番
   */
  public PuregoodsMasterCommonId(int prmSetDtlNo1, int partsMakerCd, String primePartsNoWithH) {
    setPrmSetDtlNo1(prmSetDtlNo1);
    setPartsMakerCd(partsMakerCd);
    setPrimePartsNoWithH(primePartsNoWithH);
  }

  /**
   * 純正設定詳細コード１を返します。
   * 
   * @return 純正設定詳細コード１
   */
  public int getPrmSetDtlNo1() {
    return container().getPropertyValue(PuregoodsMasterCommon.PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 純正設定詳細コード１を設定します。
   * 
   * @param prmSetDtlNo1 セットする純正設定詳細コード１
   */
  public void setPrmSetDtlNo1(int prmSetDtlNo1) {
    container().setPropertyValue(PuregoodsMasterCommon.PRM_SET_DTL_NO_1, prmSetDtlNo1);
  }

  /**
   * 部品メーカーコードを返します。
   * 
   * @return 部品メーカーコード
   */
  public int getPartsMakerCd() {
    return container().getPropertyValue(PuregoodsMasterCommon.PARTS_MAKER_CD, Integer.class);
  }

  /**
   * 部品メーカーコードを設定します。
   * 
   * @param partsMakerCd セットする部品メーカーコード
   */
  public void setPartsMakerCd(int partsMakerCd) {
    container().setPropertyValue(PuregoodsMasterCommon.PARTS_MAKER_CD, partsMakerCd);
  }

  /**
   * 純正品番を返します。
   * 
   * @return 純正品番
   */
  public String getPrimePartsNoWithH() {
    return container().getPropertyValue(PuregoodsMasterCommon.PRIME_PARTS_NO_WITH_H, String.class);
  }

  /**
   * 純正品番を設定します。
   * 
   * @param primePartsNoWithH セットする純正品番
   */
  public void setPrimePartsNoWithH(String primePartsNoWithH) {
    container().setPropertyValue(PuregoodsMasterCommon.PRIME_PARTS_NO_WITH_H, primePartsNoWithH);
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
    list.add(new PropertyDefinition(PuregoodsMasterCommon.PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(PuregoodsMasterCommon.PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(PuregoodsMasterCommon.PRIME_PARTS_NO_WITH_H, String.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
