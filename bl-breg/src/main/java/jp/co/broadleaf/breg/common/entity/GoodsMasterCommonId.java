package jp.co.broadleaf.breg.common.entity;

import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.DomainEntityId;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品マスタ(共有)エンティティのIDクラス
 */
public class GoodsMasterCommonId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public GoodsMasterCommonId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param primePartsNoWithH 優良品番(－付き品番)
   * @param applyNo 申請No
   */
  public GoodsMasterCommonId(int prmSetDtlNo1, int partsMakerCd, String primePartsNoWithH, int applyNo) {
    setPrmSetDtlNo1(prmSetDtlNo1);
    setPartsMakerCd(partsMakerCd);
    setPrimePartsNoWithH(primePartsNoWithH);
    setApplyNo(applyNo);
  }

  /**
   * 優良設定詳細コード１を返します。
   * 
   * @return 優良設定詳細コード１
   */
  public int getPrmSetDtlNo1() {
    return container().getPropertyValue(GoodsMasterCommon.PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 優良設定詳細コード１を設定します。
   * 
   * @param prmSetDtlNo1 セットする優良設定詳細コード１
   */
  public void setPrmSetDtlNo1(int prmSetDtlNo1) {
    container().setPropertyValue(GoodsMasterCommon.PRM_SET_DTL_NO_1, prmSetDtlNo1);
  }

  /**
   * 部品メーカーコードを返します。
   * 
   * @return 部品メーカーコード
   */
  public int getPartsMakerCd() {
    return container().getPropertyValue(GoodsMasterCommon.PARTS_MAKER_CD, Integer.class);
  }

  /**
   * 部品メーカーコードを設定します。
   * 
   * @param partsMakerCd セットする部品メーカーコード
   */
  public void setPartsMakerCd(int partsMakerCd) {
    container().setPropertyValue(GoodsMasterCommon.PARTS_MAKER_CD, partsMakerCd);
  }

  /**
   * 優良品番(－付き品番)を返します。
   * 
   * @return 優良品番(－付き品番)
   */
  public String getPrimePartsNoWithH() {
    return container().getPropertyValue(GoodsMasterCommon.PRIME_PARTS_NO_WITH_H, String.class);
  }

  /**
   * 優良品番(－付き品番)を設定します。
   * 
   * @param primePartsNoWithH セットする優良品番(－付き品番)
   */
  public void setPrimePartsNoWithH(String primePartsNoWithH) {
    container().setPropertyValue(GoodsMasterCommon.PRIME_PARTS_NO_WITH_H, primePartsNoWithH);
  }

  /**
   * 申請Noを返します。
   * 
   * @return 申請No
   */
  public int getApplyNo() {
    return container().getPropertyValue(GoodsMasterCommon.APPLY_NO, Integer.class);
  }

  /**
   * 申請Noを設定します。
   * 
   * @param applyNo セットする申請No
   */
  public void setApplyNo(int applyNo) {
    container().setPropertyValue(GoodsMasterCommon.APPLY_NO, applyNo);
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
    list.add(new PropertyDefinition(GoodsMasterCommon.PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(GoodsMasterCommon.PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(GoodsMasterCommon.PRIME_PARTS_NO_WITH_H, String.class, false));
    list.add(new PropertyDefinition(GoodsMasterCommon.APPLY_NO, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
