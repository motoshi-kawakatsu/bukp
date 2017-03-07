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
 * 結合マスタ(共有)エンティティのIDクラス
 */
public class JoinMasterCommonId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public JoinMasterCommonId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param tbsPartsCode BLコード
   * @param joinSourceMakerCode 結合元メーカーコード
   * @param prmSetDtlNo2 優良設定詳細コード２
   * @param joinSourPartsNoWithH 結合元品番(－付き品番)
   * @param joinDispOrder 結合表示順位
   * @param applyNo 申請No
   */
  public JoinMasterCommonId(int prmSetDtlNo1, int partsMakerCd, int tbsPartsCode, int joinSourceMakerCode, int prmSetDtlNo2, String joinSourPartsNoWithH, int joinDispOrder, int applyNo) {
    setPrmSetDtlNo1(prmSetDtlNo1);
    setPartsMakerCd(partsMakerCd);
    setTbsPartsCode(tbsPartsCode);
    setJoinSourceMakerCode(joinSourceMakerCode);
    setPrmSetDtlNo2(prmSetDtlNo2);
    setJoinSourPartsNoWithH(joinSourPartsNoWithH);
    setJoinDispOrder(joinDispOrder);
    setApplyNo(applyNo);
  }

  /**
   * 優良設定詳細コード１を返します。
   * 
   * @return 優良設定詳細コード１
   */
  public int getPrmSetDtlNo1() {
    return container().getPropertyValue(JoinMasterCommon.PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 優良設定詳細コード１を設定します。
   * 
   * @param prmSetDtlNo1 セットする優良設定詳細コード１
   */
  public void setPrmSetDtlNo1(int prmSetDtlNo1) {
    container().setPropertyValue(JoinMasterCommon.PRM_SET_DTL_NO_1, prmSetDtlNo1);
  }

  /**
   * 部品メーカーコードを返します。
   * 
   * @return 部品メーカーコード
   */
  public int getPartsMakerCd() {
    return container().getPropertyValue(JoinMasterCommon.PARTS_MAKER_CD, Integer.class);
  }

  /**
   * 部品メーカーコードを設定します。
   * 
   * @param partsMakerCd セットする部品メーカーコード
   */
  public void setPartsMakerCd(int partsMakerCd) {
    container().setPropertyValue(JoinMasterCommon.PARTS_MAKER_CD, partsMakerCd);
  }

  /**
   * BLコードを返します。
   * 
   * @return BLコード
   */
  public int getTbsPartsCode() {
    return container().getPropertyValue(JoinMasterCommon.TBS_PARTS_CODE, Integer.class);
  }

  /**
   * BLコードを設定します。
   * 
   * @param tbsPartsCode セットするBLコード
   */
  public void setTbsPartsCode(int tbsPartsCode) {
    container().setPropertyValue(JoinMasterCommon.TBS_PARTS_CODE, tbsPartsCode);
  }

  /**
   * 結合元メーカーコードを返します。
   * 
   * @return 結合元メーカーコード
   */
  public int getJoinSourceMakerCode() {
    return container().getPropertyValue(JoinMasterCommon.JOIN_SOURCE_MAKER_CODE, Integer.class);
  }

  /**
   * 結合元メーカーコードを設定します。
   * 
   * @param joinSourceMakerCode セットする結合元メーカーコード
   */
  public void setJoinSourceMakerCode(int joinSourceMakerCode) {
    container().setPropertyValue(JoinMasterCommon.JOIN_SOURCE_MAKER_CODE, joinSourceMakerCode);
  }

  /**
   * 優良設定詳細コード２を返します。
   * 
   * @return 優良設定詳細コード２
   */
  public int getPrmSetDtlNo2() {
    return container().getPropertyValue(JoinMasterCommon.PRM_SET_DTL_NO_2, Integer.class);
  }

  /**
   * 優良設定詳細コード２を設定します。
   * 
   * @param prmSetDtlNo2 セットする優良設定詳細コード２
   */
  public void setPrmSetDtlNo2(int prmSetDtlNo2) {
    container().setPropertyValue(JoinMasterCommon.PRM_SET_DTL_NO_2, prmSetDtlNo2);
  }

  /**
   * 結合元品番(－付き品番)を返します。
   * 
   * @return 結合元品番(－付き品番)
   */
  public String getJoinSourPartsNoWithH() {
    return container().getPropertyValue(JoinMasterCommon.JOIN_SOUR_PARTS_NO_WITH_H, String.class);
  }

  /**
   * 結合元品番(－付き品番)を設定します。
   * 
   * @param joinSourPartsNoWithH セットする結合元品番(－付き品番)
   */
  public void setJoinSourPartsNoWithH(String joinSourPartsNoWithH) {
    container().setPropertyValue(JoinMasterCommon.JOIN_SOUR_PARTS_NO_WITH_H, joinSourPartsNoWithH);
  }

  /**
   * 結合表示順位を返します。
   * 
   * @return 結合表示順位
   */
  public int getJoinDispOrder() {
    return container().getPropertyValue(JoinMasterCommon.JOIN_DISP_ORDER, Integer.class);
  }

  /**
   * 結合表示順位を設定します。
   * 
   * @param joinDispOrder セットする結合表示順位
   */
  public void setJoinDispOrder(int joinDispOrder) {
    container().setPropertyValue(JoinMasterCommon.JOIN_DISP_ORDER, joinDispOrder);
  }

  /**
   * 申請Noを返します。
   * 
   * @return 申請No
   */
  public int getApplyNo() {
    return container().getPropertyValue(JoinMasterCommon.APPLY_NO, Integer.class);
  }

  /**
   * 申請Noを設定します。
   * 
   * @param applyNo セットする申請No
   */
  public void setApplyNo(int applyNo) {
    container().setPropertyValue(JoinMasterCommon.APPLY_NO, applyNo);
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
    list.add(new PropertyDefinition(JoinMasterCommon.PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(JoinMasterCommon.PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(JoinMasterCommon.TBS_PARTS_CODE, Integer.class, false));
    list.add(new PropertyDefinition(JoinMasterCommon.JOIN_SOURCE_MAKER_CODE, Integer.class, false));
    list.add(new PropertyDefinition(JoinMasterCommon.PRM_SET_DTL_NO_2, Integer.class, false));
    list.add(new PropertyDefinition(JoinMasterCommon.JOIN_SOUR_PARTS_NO_WITH_H, String.class, false));
    list.add(new PropertyDefinition(JoinMasterCommon.JOIN_DISP_ORDER, Integer.class, false));
    list.add(new PropertyDefinition(JoinMasterCommon.APPLY_NO, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
