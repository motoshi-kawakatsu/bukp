package jp.co.broadleaf.breg.common.entity;

import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.DomainEntityId;

import java.util.ArrayList;
import java.util.List;

/**
 * チェック選択エンティティのIDクラス
 */
public class SelectMakerId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public SelectMakerId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param gPrmSetDtlNo1 商品_優良設定詳細コード１
   * @param gPartsMakerCd 商品_部品メーカーコード
   * @param gPrimePartsNoWithH 商品_優良品番(－付き品番)
   * @param sPrmSetDtlNo1 セット_優良設定詳細コード１
   * @param sPartsMakerCd セット_部品メーカーコード
   * @param sSetMainPartsNo セット_セット親品番
   * @param sSetDispOrder セット_セット表示順位
   * @param jPrmSetDtlNo1 結合_優良設定詳細コード１
   * @param jPartsMakerCd 結合_部品メーカーコード
   * @param jTbsPartsCode 結合_BLコード
   * @param jJoinSourceMakerCode 結合_結合元メーカーコード
   * @param jPrmSetDtlNo2 結合_優良設定詳細コード２
   * @param jJoinSourPartsNoWithH 結合_結合元品番(－付き品番)
   * @param jJoinDispOrder 結合_結合表示順位
   */
  public SelectMakerId(int gPrmSetDtlNo1, int gPartsMakerCd, String gPrimePartsNoWithH, int sPrmSetDtlNo1, int sPartsMakerCd, String sSetMainPartsNo, int sSetDispOrder, int jPrmSetDtlNo1, int jPartsMakerCd, int jTbsPartsCode, int jJoinSourceMakerCode, int jPrmSetDtlNo2, String jJoinSourPartsNoWithH, int jJoinDispOrder) {
    setGPrmSetDtlNo1(gPrmSetDtlNo1);
    setGPartsMakerCd(gPartsMakerCd);
    setGPrimePartsNoWithH(gPrimePartsNoWithH);
    setSPrmSetDtlNo1(sPrmSetDtlNo1);
    setSPartsMakerCd(sPartsMakerCd);
    setSSetMainPartsNo(sSetMainPartsNo);
    setSSetDispOrder(sSetDispOrder);
    setJPrmSetDtlNo1(jPrmSetDtlNo1);
    setJPartsMakerCd(jPartsMakerCd);
    setJTbsPartsCode(jTbsPartsCode);
    setJJoinSourceMakerCode(jJoinSourceMakerCode);
    setJPrmSetDtlNo2(jPrmSetDtlNo2);
    setJJoinSourPartsNoWithH(jJoinSourPartsNoWithH);
    setJJoinDispOrder(jJoinDispOrder);
  }

  /**
   * 商品_優良設定詳細コード１を返します。
   * 
   * @return 商品_優良設定詳細コード１
   */
  public int getGPrmSetDtlNo1() {
    return container().getPropertyValue(SelectMaker.G_PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 商品_優良設定詳細コード１を設定します。
   * 
   * @param gPrmSetDtlNo1 セットする商品_優良設定詳細コード１
   */
  public void setGPrmSetDtlNo1(int gPrmSetDtlNo1) {
    container().setPropertyValue(SelectMaker.G_PRM_SET_DTL_NO_1, gPrmSetDtlNo1);
  }

  /**
   * 商品_部品メーカーコードを返します。
   * 
   * @return 商品_部品メーカーコード
   */
  public int getGPartsMakerCd() {
    return container().getPropertyValue(SelectMaker.G_PARTS_MAKER_CD, Integer.class);
  }

  /**
   * 商品_部品メーカーコードを設定します。
   * 
   * @param gPartsMakerCd セットする商品_部品メーカーコード
   */
  public void setGPartsMakerCd(int gPartsMakerCd) {
    container().setPropertyValue(SelectMaker.G_PARTS_MAKER_CD, gPartsMakerCd);
  }

  /**
   * 商品_優良品番(－付き品番)を返します。
   * 
   * @return 商品_優良品番(－付き品番)
   */
  public String getGPrimePartsNoWithH() {
    return container().getPropertyValue(SelectMaker.G_PRIME_PARTS_NO_WITH_H, String.class);
  }

  /**
   * 商品_優良品番(－付き品番)を設定します。
   * 
   * @param gPrimePartsNoWithH セットする商品_優良品番(－付き品番)
   */
  public void setGPrimePartsNoWithH(String gPrimePartsNoWithH) {
    container().setPropertyValue(SelectMaker.G_PRIME_PARTS_NO_WITH_H, gPrimePartsNoWithH);
  }

  /**
   * セット_優良設定詳細コード１を返します。
   * 
   * @return セット_優良設定詳細コード１
   */
  public int getSPrmSetDtlNo1() {
    return container().getPropertyValue(SelectMaker.S_PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * セット_優良設定詳細コード１を設定します。
   * 
   * @param sPrmSetDtlNo1 セットするセット_優良設定詳細コード１
   */
  public void setSPrmSetDtlNo1(int sPrmSetDtlNo1) {
    container().setPropertyValue(SelectMaker.S_PRM_SET_DTL_NO_1, sPrmSetDtlNo1);
  }

  /**
   * セット_部品メーカーコードを返します。
   * 
   * @return セット_部品メーカーコード
   */
  public int getSPartsMakerCd() {
    return container().getPropertyValue(SelectMaker.S_PARTS_MAKER_CD, Integer.class);
  }

  /**
   * セット_部品メーカーコードを設定します。
   * 
   * @param sPartsMakerCd セットするセット_部品メーカーコード
   */
  public void setSPartsMakerCd(int sPartsMakerCd) {
    container().setPropertyValue(SelectMaker.S_PARTS_MAKER_CD, sPartsMakerCd);
  }

  /**
   * セット_セット親品番を返します。
   * 
   * @return セット_セット親品番
   */
  public String getSSetMainPartsNo() {
    return container().getPropertyValue(SelectMaker.S_SET_MAIN_PARTS_NO, String.class);
  }

  /**
   * セット_セット親品番を設定します。
   * 
   * @param sSetMainPartsNo セットするセット_セット親品番
   */
  public void setSSetMainPartsNo(String sSetMainPartsNo) {
    container().setPropertyValue(SelectMaker.S_SET_MAIN_PARTS_NO, sSetMainPartsNo);
  }

  /**
   * セット_セット表示順位を返します。
   * 
   * @return セット_セット表示順位
   */
  public int getSSetDispOrder() {
    return container().getPropertyValue(SelectMaker.S_SET_DISP_ORDER, Integer.class);
  }

  /**
   * セット_セット表示順位を設定します。
   * 
   * @param sSetDispOrder セットするセット_セット表示順位
   */
  public void setSSetDispOrder(int sSetDispOrder) {
    container().setPropertyValue(SelectMaker.S_SET_DISP_ORDER, sSetDispOrder);
  }

  /**
   * 結合_優良設定詳細コード１を返します。
   * 
   * @return 結合_優良設定詳細コード１
   */
  public int getJPrmSetDtlNo1() {
    return container().getPropertyValue(SelectMaker.J_PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 結合_優良設定詳細コード１を設定します。
   * 
   * @param jPrmSetDtlNo1 セットする結合_優良設定詳細コード１
   */
  public void setJPrmSetDtlNo1(int jPrmSetDtlNo1) {
    container().setPropertyValue(SelectMaker.J_PRM_SET_DTL_NO_1, jPrmSetDtlNo1);
  }

  /**
   * 結合_部品メーカーコードを返します。
   * 
   * @return 結合_部品メーカーコード
   */
  public int getJPartsMakerCd() {
    return container().getPropertyValue(SelectMaker.J_PARTS_MAKER_CD, Integer.class);
  }

  /**
   * 結合_部品メーカーコードを設定します。
   * 
   * @param jPartsMakerCd セットする結合_部品メーカーコード
   */
  public void setJPartsMakerCd(int jPartsMakerCd) {
    container().setPropertyValue(SelectMaker.J_PARTS_MAKER_CD, jPartsMakerCd);
  }

  /**
   * 結合_BLコードを返します。
   * 
   * @return 結合_BLコード
   */
  public int getJTbsPartsCode() {
    return container().getPropertyValue(SelectMaker.J_TBS_PARTS_CODE, Integer.class);
  }

  /**
   * 結合_BLコードを設定します。
   * 
   * @param jTbsPartsCode セットする結合_BLコード
   */
  public void setJTbsPartsCode(int jTbsPartsCode) {
    container().setPropertyValue(SelectMaker.J_TBS_PARTS_CODE, jTbsPartsCode);
  }

  /**
   * 結合_結合元メーカーコードを返します。
   * 
   * @return 結合_結合元メーカーコード
   */
  public int getJJoinSourceMakerCode() {
    return container().getPropertyValue(SelectMaker.J_JOIN_SOURCE_MAKER_CODE, Integer.class);
  }

  /**
   * 結合_結合元メーカーコードを設定します。
   * 
   * @param jJoinSourceMakerCode セットする結合_結合元メーカーコード
   */
  public void setJJoinSourceMakerCode(int jJoinSourceMakerCode) {
    container().setPropertyValue(SelectMaker.J_JOIN_SOURCE_MAKER_CODE, jJoinSourceMakerCode);
  }

  /**
   * 結合_優良設定詳細コード２を返します。
   * 
   * @return 結合_優良設定詳細コード２
   */
  public int getJPrmSetDtlNo2() {
    return container().getPropertyValue(SelectMaker.J_PRM_SET_DTL_NO_2, Integer.class);
  }

  /**
   * 結合_優良設定詳細コード２を設定します。
   * 
   * @param jPrmSetDtlNo2 セットする結合_優良設定詳細コード２
   */
  public void setJPrmSetDtlNo2(int jPrmSetDtlNo2) {
    container().setPropertyValue(SelectMaker.J_PRM_SET_DTL_NO_2, jPrmSetDtlNo2);
  }

  /**
   * 結合_結合元品番(－付き品番)を返します。
   * 
   * @return 結合_結合元品番(－付き品番)
   */
  public String getJJoinSourPartsNoWithH() {
    return container().getPropertyValue(SelectMaker.J_JOIN_SOUR_PARTS_NO_WITH_H, String.class);
  }

  /**
   * 結合_結合元品番(－付き品番)を設定します。
   * 
   * @param jJoinSourPartsNoWithH セットする結合_結合元品番(－付き品番)
   */
  public void setJJoinSourPartsNoWithH(String jJoinSourPartsNoWithH) {
    container().setPropertyValue(SelectMaker.J_JOIN_SOUR_PARTS_NO_WITH_H, jJoinSourPartsNoWithH);
  }

  /**
   * 結合_結合表示順位を返します。
   * 
   * @return 結合_結合表示順位
   */
  public int getJJoinDispOrder() {
    return container().getPropertyValue(SelectMaker.J_JOIN_DISP_ORDER, Integer.class);
  }

  /**
   * 結合_結合表示順位を設定します。
   * 
   * @param jJoinDispOrder セットする結合_結合表示順位
   */
  public void setJJoinDispOrder(int jJoinDispOrder) {
    container().setPropertyValue(SelectMaker.J_JOIN_DISP_ORDER, jJoinDispOrder);
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
    list.add(new PropertyDefinition(SelectMaker.G_PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(SelectMaker.G_PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(SelectMaker.G_PRIME_PARTS_NO_WITH_H, String.class, false));
    list.add(new PropertyDefinition(SelectMaker.S_PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(SelectMaker.S_PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(SelectMaker.S_SET_MAIN_PARTS_NO, String.class, false));
    list.add(new PropertyDefinition(SelectMaker.S_SET_DISP_ORDER, Integer.class, false));
    list.add(new PropertyDefinition(SelectMaker.J_PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(SelectMaker.J_PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(SelectMaker.J_TBS_PARTS_CODE, Integer.class, false));
    list.add(new PropertyDefinition(SelectMaker.J_JOIN_SOURCE_MAKER_CODE, Integer.class, false));
    list.add(new PropertyDefinition(SelectMaker.J_PRM_SET_DTL_NO_2, Integer.class, false));
    list.add(new PropertyDefinition(SelectMaker.J_JOIN_SOUR_PARTS_NO_WITH_H, String.class, false));
    list.add(new PropertyDefinition(SelectMaker.J_JOIN_DISP_ORDER, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
