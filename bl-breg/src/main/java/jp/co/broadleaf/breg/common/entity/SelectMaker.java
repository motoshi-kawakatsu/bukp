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
 * チェック選択
 */
@Entity(name = "t_select_maker", type = "maker")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class SelectMaker extends BroadleafDomainEntity<SelectMakerId> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * 商品_優良設定詳細コード１
   */
  public static final String G_PRM_SET_DTL_NO_1 = "gPrmSetDtlNo1";

  /**
   * 商品_部品メーカーコード
   */
  public static final String G_PARTS_MAKER_CD = "gPartsMakerCd";

  /**
   * 商品_優良品番(－付き品番)
   */
  public static final String G_PRIME_PARTS_NO_WITH_H = "gPrimePartsNoWithH";

  /**
   * セット_優良設定詳細コード１
   */
  public static final String S_PRM_SET_DTL_NO_1 = "sPrmSetDtlNo1";

  /**
   * セット_部品メーカーコード
   */
  public static final String S_PARTS_MAKER_CD = "sPartsMakerCd";

  /**
   * セット_セット親品番
   */
  public static final String S_SET_MAIN_PARTS_NO = "sSetMainPartsNo";

  /**
   * セット_セット表示順位
   */
  public static final String S_SET_DISP_ORDER = "sSetDispOrder";

  /**
   * 結合_優良設定詳細コード１
   */
  public static final String J_PRM_SET_DTL_NO_1 = "jPrmSetDtlNo1";

  /**
   * 結合_部品メーカーコード
   */
  public static final String J_PARTS_MAKER_CD = "jPartsMakerCd";

  /**
   * 結合_BLコード
   */
  public static final String J_TBS_PARTS_CODE = "jTbsPartsCode";

  /**
   * 結合_結合元メーカーコード
   */
  public static final String J_JOIN_SOURCE_MAKER_CODE = "jJoinSourceMakerCode";

  /**
   * 結合_優良設定詳細コード２
   */
  public static final String J_PRM_SET_DTL_NO_2 = "jPrmSetDtlNo2";

  /**
   * 結合_結合元品番(－付き品番)
   */
  public static final String J_JOIN_SOUR_PARTS_NO_WITH_H = "jJoinSourPartsNoWithH";

  /**
   * 結合_結合表示順位
   */
  public static final String J_JOIN_DISP_ORDER = "jJoinDispOrder";

  /**
   * 区分
   */
  public static final String SELECT_KBN = "selectKbn";

  /**
   * コンストラクタ。
   */
  public SelectMaker() {

  }

  /**
   * コンストラクタ。
   * 
   * @param id ID
   */
  public SelectMaker(SelectMakerId id) {
    setGPrmSetDtlNo1(id.getGPrmSetDtlNo1());
    setGPartsMakerCd(id.getGPartsMakerCd());
    setGPrimePartsNoWithH(id.getGPrimePartsNoWithH());
    setSPrmSetDtlNo1(id.getSPrmSetDtlNo1());
    setSPartsMakerCd(id.getSPartsMakerCd());
    setSSetMainPartsNo(id.getSSetMainPartsNo());
    setSSetDispOrder(id.getSSetDispOrder());
    setJPrmSetDtlNo1(id.getJPrmSetDtlNo1());
    setJPartsMakerCd(id.getJPartsMakerCd());
    setJTbsPartsCode(id.getJTbsPartsCode());
    setJJoinSourceMakerCode(id.getJJoinSourceMakerCode());
    setJPrmSetDtlNo2(id.getJPrmSetDtlNo2());
    setJJoinSourPartsNoWithH(id.getJJoinSourPartsNoWithH());
    setJJoinDispOrder(id.getJJoinDispOrder());
  }

  /**
   * 商品_優良設定詳細コード１を返します。
   * 
   * @return 商品_優良設定詳細コード１
   */
  public int getGPrmSetDtlNo1() {
    return container().getPropertyValue(G_PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 商品_優良設定詳細コード１を設定します。
   * 
   * @param gPrmSetDtlNo1 セットする商品_優良設定詳細コード１
   */
  public void setGPrmSetDtlNo1(int gPrmSetDtlNo1) {
    container().setPropertyValue(G_PRM_SET_DTL_NO_1, gPrmSetDtlNo1);
  }

  /**
   * 商品_部品メーカーコードを返します。
   * 
   * @return 商品_部品メーカーコード
   */
  public int getGPartsMakerCd() {
    return container().getPropertyValue(G_PARTS_MAKER_CD, Integer.class);
  }

  /**
   * 商品_部品メーカーコードを設定します。
   * 
   * @param gPartsMakerCd セットする商品_部品メーカーコード
   */
  public void setGPartsMakerCd(int gPartsMakerCd) {
    container().setPropertyValue(G_PARTS_MAKER_CD, gPartsMakerCd);
  }

  /**
   * 商品_優良品番(－付き品番)を返します。
   * 
   * @return 商品_優良品番(－付き品番)
   */
  public String getGPrimePartsNoWithH() {
    return container().getPropertyValue(G_PRIME_PARTS_NO_WITH_H, String.class);
  }

  /**
   * 商品_優良品番(－付き品番)を設定します。
   * 
   * @param gPrimePartsNoWithH セットする商品_優良品番(－付き品番)
   */
  public void setGPrimePartsNoWithH(String gPrimePartsNoWithH) {
    container().setPropertyValue(G_PRIME_PARTS_NO_WITH_H, gPrimePartsNoWithH);
  }

  /**
   * セット_優良設定詳細コード１を返します。
   * 
   * @return セット_優良設定詳細コード１
   */
  public int getSPrmSetDtlNo1() {
    return container().getPropertyValue(S_PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * セット_優良設定詳細コード１を設定します。
   * 
   * @param sPrmSetDtlNo1 セットするセット_優良設定詳細コード１
   */
  public void setSPrmSetDtlNo1(int sPrmSetDtlNo1) {
    container().setPropertyValue(S_PRM_SET_DTL_NO_1, sPrmSetDtlNo1);
  }

  /**
   * セット_部品メーカーコードを返します。
   * 
   * @return セット_部品メーカーコード
   */
  public int getSPartsMakerCd() {
    return container().getPropertyValue(S_PARTS_MAKER_CD, Integer.class);
  }

  /**
   * セット_部品メーカーコードを設定します。
   * 
   * @param sPartsMakerCd セットするセット_部品メーカーコード
   */
  public void setSPartsMakerCd(int sPartsMakerCd) {
    container().setPropertyValue(S_PARTS_MAKER_CD, sPartsMakerCd);
  }

  /**
   * セット_セット親品番を返します。
   * 
   * @return セット_セット親品番
   */
  public String getSSetMainPartsNo() {
    return container().getPropertyValue(S_SET_MAIN_PARTS_NO, String.class);
  }

  /**
   * セット_セット親品番を設定します。
   * 
   * @param sSetMainPartsNo セットするセット_セット親品番
   */
  public void setSSetMainPartsNo(String sSetMainPartsNo) {
    container().setPropertyValue(S_SET_MAIN_PARTS_NO, sSetMainPartsNo);
  }

  /**
   * セット_セット表示順位を返します。
   * 
   * @return セット_セット表示順位
   */
  public int getSSetDispOrder() {
    return container().getPropertyValue(S_SET_DISP_ORDER, Integer.class);
  }

  /**
   * セット_セット表示順位を設定します。
   * 
   * @param sSetDispOrder セットするセット_セット表示順位
   */
  public void setSSetDispOrder(int sSetDispOrder) {
    container().setPropertyValue(S_SET_DISP_ORDER, sSetDispOrder);
  }

  /**
   * 結合_優良設定詳細コード１を返します。
   * 
   * @return 結合_優良設定詳細コード１
   */
  public int getJPrmSetDtlNo1() {
    return container().getPropertyValue(J_PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 結合_優良設定詳細コード１を設定します。
   * 
   * @param jPrmSetDtlNo1 セットする結合_優良設定詳細コード１
   */
  public void setJPrmSetDtlNo1(int jPrmSetDtlNo1) {
    container().setPropertyValue(J_PRM_SET_DTL_NO_1, jPrmSetDtlNo1);
  }

  /**
   * 結合_部品メーカーコードを返します。
   * 
   * @return 結合_部品メーカーコード
   */
  public int getJPartsMakerCd() {
    return container().getPropertyValue(J_PARTS_MAKER_CD, Integer.class);
  }

  /**
   * 結合_部品メーカーコードを設定します。
   * 
   * @param jPartsMakerCd セットする結合_部品メーカーコード
   */
  public void setJPartsMakerCd(int jPartsMakerCd) {
    container().setPropertyValue(J_PARTS_MAKER_CD, jPartsMakerCd);
  }

  /**
   * 結合_BLコードを返します。
   * 
   * @return 結合_BLコード
   */
  public int getJTbsPartsCode() {
    return container().getPropertyValue(J_TBS_PARTS_CODE, Integer.class);
  }

  /**
   * 結合_BLコードを設定します。
   * 
   * @param jTbsPartsCode セットする結合_BLコード
   */
  public void setJTbsPartsCode(int jTbsPartsCode) {
    container().setPropertyValue(J_TBS_PARTS_CODE, jTbsPartsCode);
  }

  /**
   * 結合_結合元メーカーコードを返します。
   * 
   * @return 結合_結合元メーカーコード
   */
  public int getJJoinSourceMakerCode() {
    return container().getPropertyValue(J_JOIN_SOURCE_MAKER_CODE, Integer.class);
  }

  /**
   * 結合_結合元メーカーコードを設定します。
   * 
   * @param jJoinSourceMakerCode セットする結合_結合元メーカーコード
   */
  public void setJJoinSourceMakerCode(int jJoinSourceMakerCode) {
    container().setPropertyValue(J_JOIN_SOURCE_MAKER_CODE, jJoinSourceMakerCode);
  }

  /**
   * 結合_優良設定詳細コード２を返します。
   * 
   * @return 結合_優良設定詳細コード２
   */
  public int getJPrmSetDtlNo2() {
    return container().getPropertyValue(J_PRM_SET_DTL_NO_2, Integer.class);
  }

  /**
   * 結合_優良設定詳細コード２を設定します。
   * 
   * @param jPrmSetDtlNo2 セットする結合_優良設定詳細コード２
   */
  public void setJPrmSetDtlNo2(int jPrmSetDtlNo2) {
    container().setPropertyValue(J_PRM_SET_DTL_NO_2, jPrmSetDtlNo2);
  }

  /**
   * 結合_結合元品番(－付き品番)を返します。
   * 
   * @return 結合_結合元品番(－付き品番)
   */
  public String getJJoinSourPartsNoWithH() {
    return container().getPropertyValue(J_JOIN_SOUR_PARTS_NO_WITH_H, String.class);
  }

  /**
   * 結合_結合元品番(－付き品番)を設定します。
   * 
   * @param jJoinSourPartsNoWithH セットする結合_結合元品番(－付き品番)
   */
  public void setJJoinSourPartsNoWithH(String jJoinSourPartsNoWithH) {
    container().setPropertyValue(J_JOIN_SOUR_PARTS_NO_WITH_H, jJoinSourPartsNoWithH);
  }

  /**
   * 結合_結合表示順位を返します。
   * 
   * @return 結合_結合表示順位
   */
  public int getJJoinDispOrder() {
    return container().getPropertyValue(J_JOIN_DISP_ORDER, Integer.class);
  }

  /**
   * 結合_結合表示順位を設定します。
   * 
   * @param jJoinDispOrder セットする結合_結合表示順位
   */
  public void setJJoinDispOrder(int jJoinDispOrder) {
    container().setPropertyValue(J_JOIN_DISP_ORDER, jJoinDispOrder);
  }

  /**
   * 区分を返します。
   * 
   * @return 区分
   */
  public int getSelectKbn() {
    return container().getPropertyValue(SELECT_KBN, Integer.class);
  }

  /**
   * 区分を設定します。
   * 
   * @param selectKbn セットする区分
   */
  public void setSelectKbn(int selectKbn) {
    container().setPropertyValue(SELECT_KBN, selectKbn);
  }

  /**
   * エンティティIDを取得する。
   * 
   * @return エンティティID
   */
  @Override
  public SelectMakerId identifier() {
    return new SelectMakerId(getGPrmSetDtlNo1(), getGPartsMakerCd(), getGPrimePartsNoWithH(), getSPrmSetDtlNo1(), getSPartsMakerCd(), getSSetMainPartsNo(), getSSetDispOrder(), getJPrmSetDtlNo1(), getJPartsMakerCd(), getJTbsPartsCode(), getJJoinSourceMakerCode(), getJPrmSetDtlNo2(), getJJoinSourPartsNoWithH(), getJJoinDispOrder());
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
    list.add(new PropertyDefinition(G_PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(G_PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(G_PRIME_PARTS_NO_WITH_H, String.class, false));
    list.add(new PropertyDefinition(S_PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(S_PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(S_SET_MAIN_PARTS_NO, String.class, false));
    list.add(new PropertyDefinition(S_SET_DISP_ORDER, Integer.class, false));
    list.add(new PropertyDefinition(J_PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(J_PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(J_TBS_PARTS_CODE, Integer.class, false));
    list.add(new PropertyDefinition(J_JOIN_SOURCE_MAKER_CODE, Integer.class, false));
    list.add(new PropertyDefinition(J_PRM_SET_DTL_NO_2, Integer.class, false));
    list.add(new PropertyDefinition(J_JOIN_SOUR_PARTS_NO_WITH_H, String.class, false));
    list.add(new PropertyDefinition(J_JOIN_DISP_ORDER, Integer.class, false));
    list.add(new PropertyDefinition(SELECT_KBN, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
