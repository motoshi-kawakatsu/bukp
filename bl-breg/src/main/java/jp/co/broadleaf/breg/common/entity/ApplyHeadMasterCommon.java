package jp.co.broadleaf.breg.common.entity;

import jp.co.broadleaf.common.domain.entity.BroadleafDomainEntity;
import jp.co.broadleaf.common.domain.entity.BroadleafOptimisticConcurrencyResolver;
import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.Entity;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrency;

import org.apache.commons.lang3.ArrayUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 申請ヘッダマスタ
 */
@Entity(name = "m_apply_head_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class ApplyHeadMasterCommon extends BroadleafDomainEntity<ApplyHeadMasterCommonId> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * 部品メーカーコード
   */
  public static final String PARTS_MAKER_CD  = "partsMakerCd ";

  /**
   * 申請No
   */
  public static final String APPLY_NO = "applyNo";

  /**
   * 申請時コメント
   */
  public static final String APPLY_COMMENTS = "applyComments";

  /**
   * 申請日時
   */
  public static final String APPLY_DT_TIME = "applyDtTime";

  /**
   * BL申請結果区分
   */
  public static final String BL_APPLY_RESULTS_FLG = "blApplyResultsFlg";

  /**
   * BL申請結果コメント
   */
  public static final String BL_APPLY_RESULTS_COMMENTS = "blApplyResultsComments";

  /**
   * BL申請判断日
   */
  public static final String BL_APPLY_JUDGMENT_DATE = "blApplyJudgmentDate";

  /**
   * BL申請判断時間
   */
  public static final String BL_APPLY_JUDGMENT_TIME = "blApplyJudgmentTime";

  /**
   * 申請種類
   */
  public static final String APPLY_TYPE = "applyType";

  /**
   * 前回申請No
   */
  public static final String PRE_APPLY_NO = "preApplyNo";

  /**
   * コンストラクタ。
   */
  public ApplyHeadMasterCommon() {

  }

  /**
   * コンストラクタ。
   * 
   * @param id ID
   */
  public ApplyHeadMasterCommon(ApplyHeadMasterCommonId id) {
    setPartsMakerCd (id.getPartsMakerCd ());
    setApplyNo(id.getApplyNo());
    setBlApplyResultsFlg(id.getBlApplyResultsFlg());
  }

  /**
   * 部品メーカーコードを返します。
   * 
   * @return 部品メーカーコード
   */
  public int getPartsMakerCd () {
    return container().getPropertyValue(PARTS_MAKER_CD , Integer.class);
  }

  /**
   * 部品メーカーコードを設定します。
   * 
   * @param partsMakerCd  セットする部品メーカーコード
   */
  public void setPartsMakerCd (int partsMakerCd ) {
    container().setPropertyValue(PARTS_MAKER_CD , partsMakerCd );
  }

  /**
   * 申請Noを返します。
   * 
   * @return 申請No
   */
  public int getApplyNo() {
    return container().getPropertyValue(APPLY_NO, Integer.class);
  }

  /**
   * 申請Noを設定します。
   * 
   * @param applyNo セットする申請No
   */
  public void setApplyNo(int applyNo) {
    container().setPropertyValue(APPLY_NO, applyNo);
  }

  /**
   * 申請時コメントを返します。
   * 
   * @return 申請時コメント
   */
  public String getApplyComments() {
    return container().getPropertyValue(APPLY_COMMENTS, String.class);
  }

  /**
   * 申請時コメントを設定します。
   * 
   * @param applyComments セットする申請時コメント
   */
  public void setApplyComments(String applyComments) {
    container().setPropertyValue(APPLY_COMMENTS, applyComments);
  }

  /**
   * 申請日時を返します。
   * 
   * @return 申請日時
   */
  public Timestamp getApplyDtTime() {
    return container().getPropertyValue(APPLY_DT_TIME, Timestamp.class);
  }

  /**
   * 申請日時を設定します。
   * 
   * @param applyDtTime セットする申請日時
   */
  public void setApplyDtTime(Timestamp applyDtTime) {
    container().setPropertyValue(APPLY_DT_TIME, applyDtTime);
  }

  /**
   * BL申請結果区分を返します。
   * 
   * @return BL申請結果区分
   */
  public short getBlApplyResultsFlg() {
    return container().getPropertyValue(BL_APPLY_RESULTS_FLG, Short.class);
  }

  /**
   * BL申請結果区分を設定します。
   * 
   * @param blApplyResultsFlg セットするBL申請結果区分
   */
  public void setBlApplyResultsFlg(short blApplyResultsFlg) {
    container().setPropertyValue(BL_APPLY_RESULTS_FLG, blApplyResultsFlg);
  }

  /**
   * BL申請結果コメントを返します。
   * 
   * @return BL申請結果コメント
   */
  public String getBlApplyResultsComments() {
    return container().getPropertyValue(BL_APPLY_RESULTS_COMMENTS, String.class);
  }

  /**
   * BL申請結果コメントを設定します。
   * 
   * @param blApplyResultsComments セットするBL申請結果コメント
   */
  public void setBlApplyResultsComments(String blApplyResultsComments) {
    container().setPropertyValue(BL_APPLY_RESULTS_COMMENTS, blApplyResultsComments);
  }

  /**
   * BL申請判断日を返します。
   * 
   * @return BL申請判断日
   */
  public Integer getBlApplyJudgmentDate() {
    return container().getPropertyValue(BL_APPLY_JUDGMENT_DATE, Integer.class);
  }

  /**
   * BL申請判断日を設定します。
   * 
   * @param blApplyJudgmentDate セットするBL申請判断日
   */
  public void setBlApplyJudgmentDate(Integer blApplyJudgmentDate) {
    container().setPropertyValue(BL_APPLY_JUDGMENT_DATE, blApplyJudgmentDate);
  }

  /**
   * BL申請判断時間を返します。
   * 
   * @return BL申請判断時間
   */
  public Integer getBlApplyJudgmentTime() {
    return container().getPropertyValue(BL_APPLY_JUDGMENT_TIME, Integer.class);
  }

  /**
   * BL申請判断時間を設定します。
   * 
   * @param blApplyJudgmentTime セットするBL申請判断時間
   */
  public void setBlApplyJudgmentTime(Integer blApplyJudgmentTime) {
    container().setPropertyValue(BL_APPLY_JUDGMENT_TIME, blApplyJudgmentTime);
  }

  /**
   * 申請種類を返します。
   * 
   * @return 申請種類
   */
  public Integer getApplyType() {
    return container().getPropertyValue(APPLY_TYPE, Integer.class);
  }

  /**
   * 申請種類を設定します。
   * 
   * @param applyType セットする申請種類
   */
  public void setApplyType(Integer applyType) {
    container().setPropertyValue(APPLY_TYPE, applyType);
  }

  /**
   * 前回申請Noを返します。
   * 
   * @return 前回申請No
   */
  public Integer getPreApplyNo() {
    return container().getPropertyValue(PRE_APPLY_NO, Integer.class);
  }

  /**
   * 前回申請Noを設定します。
   * 
   * @param preApplyNo セットする前回申請No
   */
  public void setPreApplyNo(Integer preApplyNo) {
    container().setPropertyValue(PRE_APPLY_NO, preApplyNo);
  }

  /**
   * エンティティIDを取得する。
   * 
   * @return エンティティID
   */
  @Override
  public ApplyHeadMasterCommonId identifier() {
    return new ApplyHeadMasterCommonId(getPartsMakerCd (), getApplyNo(), getBlApplyResultsFlg());
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
    list.add(new PropertyDefinition(PARTS_MAKER_CD , Integer.class, false));
    list.add(new PropertyDefinition(APPLY_NO, Integer.class, false));
    list.add(new PropertyDefinition(APPLY_COMMENTS, String.class, false));
    list.add(new PropertyDefinition(APPLY_DT_TIME, Timestamp.class, false));
    list.add(new PropertyDefinition(BL_APPLY_RESULTS_FLG, Short.class, false));
    list.add(new PropertyDefinition(BL_APPLY_RESULTS_COMMENTS, String.class, true));
    list.add(new PropertyDefinition(BL_APPLY_JUDGMENT_DATE, Integer.class, true));
    list.add(new PropertyDefinition(BL_APPLY_JUDGMENT_TIME, Integer.class, true));
    list.add(new PropertyDefinition(APPLY_TYPE, Integer.class, true));
    list.add(new PropertyDefinition(PRE_APPLY_NO, Integer.class, true));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
