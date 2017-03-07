package jp.co.broadleaf.breg.common.entity;

import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.DomainEntityId;

import java.util.ArrayList;
import java.util.List;

/**
 * 申請ヘッダマスタエンティティのIDクラス
 */
public class ApplyHeadMasterCommonId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public ApplyHeadMasterCommonId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param partsMakerCd  部品メーカーコード
   * @param applyNo 申請No
   * @param blApplyResultsFlg BL申請結果区分
   */
  public ApplyHeadMasterCommonId(int partsMakerCd , int applyNo, short blApplyResultsFlg) {
    setPartsMakerCd (partsMakerCd );
    setApplyNo(applyNo);
    setBlApplyResultsFlg(blApplyResultsFlg);
  }

  /**
   * 部品メーカーコードを返します。
   * 
   * @return 部品メーカーコード
   */
  public int getPartsMakerCd () {
    return container().getPropertyValue(ApplyHeadMasterCommon.PARTS_MAKER_CD , Integer.class);
  }

  /**
   * 部品メーカーコードを設定します。
   * 
   * @param partsMakerCd  セットする部品メーカーコード
   */
  public void setPartsMakerCd (int partsMakerCd ) {
    container().setPropertyValue(ApplyHeadMasterCommon.PARTS_MAKER_CD , partsMakerCd );
  }

  /**
   * 申請Noを返します。
   * 
   * @return 申請No
   */
  public int getApplyNo() {
    return container().getPropertyValue(ApplyHeadMasterCommon.APPLY_NO, Integer.class);
  }

  /**
   * 申請Noを設定します。
   * 
   * @param applyNo セットする申請No
   */
  public void setApplyNo(int applyNo) {
    container().setPropertyValue(ApplyHeadMasterCommon.APPLY_NO, applyNo);
  }

  /**
   * BL申請結果区分を返します。
   * 
   * @return BL申請結果区分
   */
  public short getBlApplyResultsFlg() {
    return container().getPropertyValue(ApplyHeadMasterCommon.BL_APPLY_RESULTS_FLG, Short.class);
  }

  /**
   * BL申請結果区分を設定します。
   * 
   * @param blApplyResultsFlg セットするBL申請結果区分
   */
  public void setBlApplyResultsFlg(short blApplyResultsFlg) {
    container().setPropertyValue(ApplyHeadMasterCommon.BL_APPLY_RESULTS_FLG, blApplyResultsFlg);
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
    list.add(new PropertyDefinition(ApplyHeadMasterCommon.PARTS_MAKER_CD , Integer.class, false));
    list.add(new PropertyDefinition(ApplyHeadMasterCommon.APPLY_NO, Integer.class, false));
    list.add(new PropertyDefinition(ApplyHeadMasterCommon.BL_APPLY_RESULTS_FLG, Short.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
