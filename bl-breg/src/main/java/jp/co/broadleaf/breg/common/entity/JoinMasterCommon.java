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
 * 結合マスタ(共有)
 */
@Entity(name = "m_join_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class JoinMasterCommon extends BroadleafDomainEntity<JoinMasterCommonId> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * 優良設定詳細コード１
   */
  public static final String PRM_SET_DTL_NO_1 = "prmSetDtlNo1";

  /**
   * 部品メーカーコード
   */
  public static final String PARTS_MAKER_CD = "partsMakerCd";

  /**
   * 商品中分類コード
   */
  public static final String GOODS_M_GROUP = "goodsMGroup";

  /**
   * BLコード
   */
  public static final String TBS_PARTS_CODE = "tbsPartsCode";

  /**
   * 結合元メーカーコード
   */
  public static final String JOIN_SOURCE_MAKER_CODE = "joinSourceMakerCode";

  /**
   * 優良設定詳細コード２
   */
  public static final String PRM_SET_DTL_NO_2 = "prmSetDtlNo2";

  /**
   * 結合元品番(－付き品番)
   */
  public static final String JOIN_SOUR_PARTS_NO_WITH_H = "joinSourPartsNoWithH";

  /**
   * 結合表示順位
   */
  public static final String JOIN_DISP_ORDER = "joinDispOrder";

  /**
   * 結合先品番(－付き品番)
   */
  public static final String JOIN_DEST_PARTS_NO = "joinDestPartsNo";

  /**
   * 結合QTY
   */
  public static final String JOIN_QTY = "joinQty";

  /**
   * 結合規格・特記事項
   */
  public static final String JOIN_SPECIAL_NOTE = "joinSpecialNote";

  /**
   * 優良部品規格・特記事項(C向け)
   */
  public static final String PRIME_PARTS_SPECIAL_NOTE_C = "primePartsSpecialNoteC";

  /**
   * 適用日時
   */
  public static final String START_TIME = "startTime";

  /**
   * 削除時申請理由
   */
  public static final String DELETE_REASON = "deleteReason";

  /**
   * 申請No
   */
  public static final String APPLY_NO = "applyNo";

  /**
   * 処理区分
   */
  public static final String DEAL_FLG = "dealFlg";

  /**
   * BL登録区分
   */
  public static final String BL_ENTRY_FLG = "blEntryFlg";

  /**
   * データステータス
   */
  public static final String ERR_FLG = "errFlg";

  /**
   * 削除依頼区分
   */
  public static final String DELETE_FLG = "deleteFlg";

  /**
   * コンストラクタ。
   */
  public JoinMasterCommon() {

  }

  /**
   * コンストラクタ。
   * 
   * @param id ID
   */
  public JoinMasterCommon(JoinMasterCommonId id) {
    setPrmSetDtlNo1(id.getPrmSetDtlNo1());
    setPartsMakerCd(id.getPartsMakerCd());
    setTbsPartsCode(id.getTbsPartsCode());
    setJoinSourceMakerCode(id.getJoinSourceMakerCode());
    setPrmSetDtlNo2(id.getPrmSetDtlNo2());
    setJoinSourPartsNoWithH(id.getJoinSourPartsNoWithH());
    setJoinDispOrder(id.getJoinDispOrder());
    setApplyNo(id.getApplyNo());
  }

  /**
   * 優良設定詳細コード１を返します。
   * 
   * @return 優良設定詳細コード１
   */
  public int getPrmSetDtlNo1() {
    return container().getPropertyValue(PRM_SET_DTL_NO_1, Integer.class);
  }

  /**
   * 優良設定詳細コード１を設定します。
   * 
   * @param prmSetDtlNo1 セットする優良設定詳細コード１
   */
  public void setPrmSetDtlNo1(int prmSetDtlNo1) {
    container().setPropertyValue(PRM_SET_DTL_NO_1, prmSetDtlNo1);
  }

  /**
   * 部品メーカーコードを返します。
   * 
   * @return 部品メーカーコード
   */
  public int getPartsMakerCd() {
    return container().getPropertyValue(PARTS_MAKER_CD, Integer.class);
  }

  /**
   * 部品メーカーコードを設定します。
   * 
   * @param partsMakerCd セットする部品メーカーコード
   */
  public void setPartsMakerCd(int partsMakerCd) {
    container().setPropertyValue(PARTS_MAKER_CD, partsMakerCd);
  }

  /**
   * 商品中分類コードを返します。
   * 
   * @return 商品中分類コード
   */
  public Integer getGoodsMGroup() {
    return container().getPropertyValue(GOODS_M_GROUP, Integer.class);
  }

  /**
   * 商品中分類コードを設定します。
   * 
   * @param goodsMGroup セットする商品中分類コード
   */
  public void setGoodsMGroup(Integer goodsMGroup) {
    container().setPropertyValue(GOODS_M_GROUP, goodsMGroup);
  }

  /**
   * BLコードを返します。
   * 
   * @return BLコード
   */
  public int getTbsPartsCode() {
    return container().getPropertyValue(TBS_PARTS_CODE, Integer.class);
  }

  /**
   * BLコードを設定します。
   * 
   * @param tbsPartsCode セットするBLコード
   */
  public void setTbsPartsCode(int tbsPartsCode) {
    container().setPropertyValue(TBS_PARTS_CODE, tbsPartsCode);
  }

  /**
   * 結合元メーカーコードを返します。
   * 
   * @return 結合元メーカーコード
   */
  public int getJoinSourceMakerCode() {
    return container().getPropertyValue(JOIN_SOURCE_MAKER_CODE, Integer.class);
  }

  /**
   * 結合元メーカーコードを設定します。
   * 
   * @param joinSourceMakerCode セットする結合元メーカーコード
   */
  public void setJoinSourceMakerCode(int joinSourceMakerCode) {
    container().setPropertyValue(JOIN_SOURCE_MAKER_CODE, joinSourceMakerCode);
  }

  /**
   * 優良設定詳細コード２を返します。
   * 
   * @return 優良設定詳細コード２
   */
  public int getPrmSetDtlNo2() {
    return container().getPropertyValue(PRM_SET_DTL_NO_2, Integer.class);
  }

  /**
   * 優良設定詳細コード２を設定します。
   * 
   * @param prmSetDtlNo2 セットする優良設定詳細コード２
   */
  public void setPrmSetDtlNo2(int prmSetDtlNo2) {
    container().setPropertyValue(PRM_SET_DTL_NO_2, prmSetDtlNo2);
  }

  /**
   * 結合元品番(－付き品番)を返します。
   * 
   * @return 結合元品番(－付き品番)
   */
  public String getJoinSourPartsNoWithH() {
    return container().getPropertyValue(JOIN_SOUR_PARTS_NO_WITH_H, String.class);
  }

  /**
   * 結合元品番(－付き品番)を設定します。
   * 
   * @param joinSourPartsNoWithH セットする結合元品番(－付き品番)
   */
  public void setJoinSourPartsNoWithH(String joinSourPartsNoWithH) {
    container().setPropertyValue(JOIN_SOUR_PARTS_NO_WITH_H, joinSourPartsNoWithH);
  }

  /**
   * 結合表示順位を返します。
   * 
   * @return 結合表示順位
   */
  public int getJoinDispOrder() {
    return container().getPropertyValue(JOIN_DISP_ORDER, Integer.class);
  }

  /**
   * 結合表示順位を設定します。
   * 
   * @param joinDispOrder セットする結合表示順位
   */
  public void setJoinDispOrder(int joinDispOrder) {
    container().setPropertyValue(JOIN_DISP_ORDER, joinDispOrder);
  }

  /**
   * 結合先品番(－付き品番)を返します。
   * 
   * @return 結合先品番(－付き品番)
   */
  public String getJoinDestPartsNo() {
    return container().getPropertyValue(JOIN_DEST_PARTS_NO, String.class);
  }

  /**
   * 結合先品番(－付き品番)を設定します。
   * 
   * @param joinDestPartsNo セットする結合先品番(－付き品番)
   */
  public void setJoinDestPartsNo(String joinDestPartsNo) {
    container().setPropertyValue(JOIN_DEST_PARTS_NO, joinDestPartsNo);
  }

  /**
   * 結合QTYを返します。
   * 
   * @return 結合QTY
   */
  public Double getJoinQty() {
    return container().getPropertyValue(JOIN_QTY, Double.class);
  }

  /**
   * 結合QTYを設定します。
   * 
   * @param joinQty セットする結合QTY
   */
  public void setJoinQty(Double joinQty) {
    container().setPropertyValue(JOIN_QTY, joinQty);
  }

  /**
   * 結合規格・特記事項を返します。
   * 
   * @return 結合規格・特記事項
   */
  public String getJoinSpecialNote() {
    return container().getPropertyValue(JOIN_SPECIAL_NOTE, String.class);
  }

  /**
   * 結合規格・特記事項を設定します。
   * 
   * @param joinSpecialNote セットする結合規格・特記事項
   */
  public void setJoinSpecialNote(String joinSpecialNote) {
    container().setPropertyValue(JOIN_SPECIAL_NOTE, joinSpecialNote);
  }

  /**
   * 優良部品規格・特記事項(C向け)を返します。
   * 
   * @return 優良部品規格・特記事項(C向け)
   */
  public String getPrimePartsSpecialNoteC() {
    return container().getPropertyValue(PRIME_PARTS_SPECIAL_NOTE_C, String.class);
  }

  /**
   * 優良部品規格・特記事項(C向け)を設定します。
   * 
   * @param primePartsSpecialNoteC セットする優良部品規格・特記事項(C向け)
   */
  public void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC) {
    container().setPropertyValue(PRIME_PARTS_SPECIAL_NOTE_C, primePartsSpecialNoteC);
  }

  /**
   * 適用日時を返します。
   * 
   * @return 適用日時
   */
  public Timestamp getStartTime() {
    return container().getPropertyValue(START_TIME, Timestamp.class);
  }

  /**
   * 適用日時を設定します。
   * 
   * @param startTime セットする適用日時
   */
  public void setStartTime(Timestamp startTime) {
    container().setPropertyValue(START_TIME, startTime);
  }

  /**
   * 削除時申請理由を返します。
   * 
   * @return 削除時申請理由
   */
  public String getDeleteReason() {
    return container().getPropertyValue(DELETE_REASON, String.class);
  }

  /**
   * 削除時申請理由を設定します。
   * 
   * @param deleteReason セットする削除時申請理由
   */
  public void setDeleteReason(String deleteReason) {
    container().setPropertyValue(DELETE_REASON, deleteReason);
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
   * 処理区分を返します。
   * 
   * @return 処理区分
   */
  public short getDealFlg() {
    return container().getPropertyValue(DEAL_FLG, Short.class);
  }

  /**
   * 処理区分を設定します。
   * 
   * @param dealFlg セットする処理区分
   */
  public void setDealFlg(short dealFlg) {
    container().setPropertyValue(DEAL_FLG, dealFlg);
  }

  /**
   * BL登録区分を返します。
   * 
   * @return BL登録区分
   */
  public short getBlEntryFlg() {
    return container().getPropertyValue(BL_ENTRY_FLG, Short.class);
  }

  /**
   * BL登録区分を設定します。
   * 
   * @param blEntryFlg セットするBL登録区分
   */
  public void setBlEntryFlg(short blEntryFlg) {
    container().setPropertyValue(BL_ENTRY_FLG, blEntryFlg);
  }

  /**
   * データステータスを返します。
   * 
   * @return データステータス
   */
  public short getErrFlg() {
    return container().getPropertyValue(ERR_FLG, Short.class);
  }

  /**
   * データステータスを設定します。
   * 
   * @param errFlg セットするデータステータス
   */
  public void setErrFlg(short errFlg) {
    container().setPropertyValue(ERR_FLG, errFlg);
  }

  /**
   * 削除依頼区分を返します。
   * 
   * @return 削除依頼区分
   */
  public Short getDeleteFlg() {
    return container().getPropertyValue(DELETE_FLG, Short.class);
  }

  /**
   * 削除依頼区分を設定します。
   * 
   * @param deleteFlg セットする削除依頼区分
   */
  public void setDeleteFlg(Short deleteFlg) {
    container().setPropertyValue(DELETE_FLG, deleteFlg);
  }

  /**
   * エンティティIDを取得する。
   * 
   * @return エンティティID
   */
  @Override
  public JoinMasterCommonId identifier() {
    return new JoinMasterCommonId(getPrmSetDtlNo1(), getPartsMakerCd(), getTbsPartsCode(), getJoinSourceMakerCode(), getPrmSetDtlNo2(), getJoinSourPartsNoWithH(), getJoinDispOrder(), getApplyNo());
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
    list.add(new PropertyDefinition(PRM_SET_DTL_NO_1, Integer.class, false));
    list.add(new PropertyDefinition(PARTS_MAKER_CD, Integer.class, false));
    list.add(new PropertyDefinition(GOODS_M_GROUP, Integer.class, true));
    list.add(new PropertyDefinition(TBS_PARTS_CODE, Integer.class, false));
    list.add(new PropertyDefinition(JOIN_SOURCE_MAKER_CODE, Integer.class, false));
    list.add(new PropertyDefinition(PRM_SET_DTL_NO_2, Integer.class, false));
    list.add(new PropertyDefinition(JOIN_SOUR_PARTS_NO_WITH_H, String.class, false));
    list.add(new PropertyDefinition(JOIN_DISP_ORDER, Integer.class, false));
    list.add(new PropertyDefinition(JOIN_DEST_PARTS_NO, String.class, true));
    list.add(new PropertyDefinition(JOIN_QTY, Double.class, true));
    list.add(new PropertyDefinition(JOIN_SPECIAL_NOTE, String.class, true));
    list.add(new PropertyDefinition(PRIME_PARTS_SPECIAL_NOTE_C, String.class, true));
    list.add(new PropertyDefinition(START_TIME, Timestamp.class, true));
    list.add(new PropertyDefinition(DELETE_REASON, String.class, false));
    list.add(new PropertyDefinition(APPLY_NO, Integer.class, false));
    list.add(new PropertyDefinition(DEAL_FLG, Short.class, false));
    list.add(new PropertyDefinition(BL_ENTRY_FLG, Short.class, false));
    list.add(new PropertyDefinition(ERR_FLG, Short.class, false));
    list.add(new PropertyDefinition(DELETE_FLG, Short.class, true));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
