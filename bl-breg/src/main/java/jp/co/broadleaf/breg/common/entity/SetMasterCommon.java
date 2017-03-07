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
 * セットマスタ(共有)
 */
@Entity(name = "m_set_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class SetMasterCommon extends BroadleafDomainEntity<SetMasterCommonId> {

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
   * セット親品番
   */
  public static final String SET_MAIN_PARTS_NO = "setMainPartsNo";

  /**
   * セット表示順位
   */
  public static final String SET_DISP_ORDER = "setDispOrder";

  /**
   * セット子品番
   */
  public static final String SET_SUB_PARTS_NO = "setSubPartsNo";

  /**
   * 品名
   */
  public static final String SET_KANA_NAME = "setKanaName";

  /**
   * セット名称
   */
  public static final String SET_NAME = "setName";

  /**
   * セットQTY
   */
  public static final String SET_QTY = "setQty";

  /**
   * セット規格・特記事項
   */
  public static final String SET_APECIAL_NOTE = "setApecialNote";

  /**
   * 優良部品規格・特記事項(C向け)
   */
  public static final String PRIME_PARTS_SPECIAL_NOTEC = "primePartsSpecialNoteC";

  /**
   * 適用日時
   */
  public static final String START_TIME = "startTime";

  /**
   * 申請No
   */
  public static final String APPLY_NO = "applyNo";

  /**
   * 削除依頼区分
   */
  public static final String DELETE_FLG = "deleteFlg";

  /**
   * 削除理由
   */
  public static final String DELETE_REASON = "deleteReason";

  /**
   * 処理区分
   */
  public static final String DEAL_FLG = "dealFlg";

  /**
   * データステータス
   */
  public static final String ERR_FLG = "errFlg";

  /**
   * BL登録区分
   */
  public static final String BL_ENTRY_FLG = "blEntryFlg";

  /**
   * コンストラクタ。
   */
  public SetMasterCommon() {

  }

  /**
   * コンストラクタ。
   * 
   * @param id ID
   */
  public SetMasterCommon(SetMasterCommonId id) {
    setPrmSetDtlNo1(id.getPrmSetDtlNo1());
    setPartsMakerCd(id.getPartsMakerCd());
    setSetMainPartsNo(id.getSetMainPartsNo());
    setSetDispOrder(id.getSetDispOrder());
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
  public Integer getTbsPartsCode() {
    return container().getPropertyValue(TBS_PARTS_CODE, Integer.class);
  }

  /**
   * BLコードを設定します。
   * 
   * @param tbsPartsCode セットするBLコード
   */
  public void setTbsPartsCode(Integer tbsPartsCode) {
    container().setPropertyValue(TBS_PARTS_CODE, tbsPartsCode);
  }

  /**
   * セット親品番を返します。
   * 
   * @return セット親品番
   */
  public String getSetMainPartsNo() {
    return container().getPropertyValue(SET_MAIN_PARTS_NO, String.class);
  }

  /**
   * セット親品番を設定します。
   * 
   * @param setMainPartsNo セットするセット親品番
   */
  public void setSetMainPartsNo(String setMainPartsNo) {
    container().setPropertyValue(SET_MAIN_PARTS_NO, setMainPartsNo);
  }

  /**
   * セット表示順位を返します。
   * 
   * @return セット表示順位
   */
  public int getSetDispOrder() {
    return container().getPropertyValue(SET_DISP_ORDER, Integer.class);
  }

  /**
   * セット表示順位を設定します。
   * 
   * @param setDispOrder セットするセット表示順位
   */
  public void setSetDispOrder(int setDispOrder) {
    container().setPropertyValue(SET_DISP_ORDER, setDispOrder);
  }

  /**
   * セット子品番を返します。
   * 
   * @return セット子品番
   */
  public String getSetSubPartsNo() {
    return container().getPropertyValue(SET_SUB_PARTS_NO, String.class);
  }

  /**
   * セット子品番を設定します。
   * 
   * @param setSubPartsNo セットするセット子品番
   */
  public void setSetSubPartsNo(String setSubPartsNo) {
    container().setPropertyValue(SET_SUB_PARTS_NO, setSubPartsNo);
  }

  /**
   * 品名を返します。
   * 
   * @return 品名
   */
  public String getSetKanaName() {
    return container().getPropertyValue(SET_KANA_NAME, String.class);
  }

  /**
   * 品名を設定します。
   * 
   * @param setKanaName セットする品名
   */
  public void setSetKanaName(String setKanaName) {
    container().setPropertyValue(SET_KANA_NAME, setKanaName);
  }

  /**
   * セット名称を返します。
   * 
   * @return セット名称
   */
  public String getSetName() {
    return container().getPropertyValue(SET_NAME, String.class);
  }

  /**
   * セット名称を設定します。
   * 
   * @param setName セットするセット名称
   */
  public void setSetName(String setName) {
    container().setPropertyValue(SET_NAME, setName);
  }

  /**
   * セットQTYを返します。
   * 
   * @return セットQTY
   */
  public Double getSetQty() {
    return container().getPropertyValue(SET_QTY, Double.class);
  }

  /**
   * セットQTYを設定します。
   * 
   * @param setQty セットするセットQTY
   */
  public void setSetQty(Double setQty) {
    container().setPropertyValue(SET_QTY, setQty);
  }

  /**
   * セット規格・特記事項を返します。
   * 
   * @return セット規格・特記事項
   */
  public String getSetApecialNote() {
    return container().getPropertyValue(SET_APECIAL_NOTE, String.class);
  }

  /**
   * セット規格・特記事項を設定します。
   * 
   * @param setApecialNote セットするセット規格・特記事項
   */
  public void setSetApecialNote(String setApecialNote) {
    container().setPropertyValue(SET_APECIAL_NOTE, setApecialNote);
  }

  /**
   * 優良部品規格・特記事項(C向け)を返します。
   * 
   * @return 優良部品規格・特記事項(C向け)
   */
  public String getPrimePartsSpecialNoteC() {
    return container().getPropertyValue(PRIME_PARTS_SPECIAL_NOTEC, String.class);
  }

  /**
   * 優良部品規格・特記事項(C向け)を設定します。
   * 
   * @param primePartsSpecialNoteC セットする優良部品規格・特記事項(C向け)
   */
  public void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC) {
    container().setPropertyValue(PRIME_PARTS_SPECIAL_NOTEC, primePartsSpecialNoteC);
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
   * 削除理由を返します。
   * 
   * @return 削除理由
   */
  public String getDeleteReason() {
    return container().getPropertyValue(DELETE_REASON, String.class);
  }

  /**
   * 削除理由を設定します。
   * 
   * @param deleteReason セットする削除理由
   */
  public void setDeleteReason(String deleteReason) {
    container().setPropertyValue(DELETE_REASON, deleteReason);
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
   * エンティティIDを取得する。
   * 
   * @return エンティティID
   */
  @Override
  public SetMasterCommonId identifier() {
    return new SetMasterCommonId(getPrmSetDtlNo1(), getPartsMakerCd(), getSetMainPartsNo(), getSetDispOrder(), getApplyNo());
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
    list.add(new PropertyDefinition(TBS_PARTS_CODE, Integer.class, true));
    list.add(new PropertyDefinition(SET_MAIN_PARTS_NO, String.class, false));
    list.add(new PropertyDefinition(SET_DISP_ORDER, Integer.class, false));
    list.add(new PropertyDefinition(SET_SUB_PARTS_NO, String.class, true));
    list.add(new PropertyDefinition(SET_KANA_NAME, String.class, true));
    list.add(new PropertyDefinition(SET_NAME, String.class, true));
    list.add(new PropertyDefinition(SET_QTY, Double.class, true));
    list.add(new PropertyDefinition(SET_APECIAL_NOTE, String.class, true));
    list.add(new PropertyDefinition(PRIME_PARTS_SPECIAL_NOTEC, String.class, true));
    list.add(new PropertyDefinition(START_TIME, Timestamp.class, false));
    list.add(new PropertyDefinition(APPLY_NO, Integer.class, false));
    list.add(new PropertyDefinition(DELETE_FLG, Short.class, true));
    list.add(new PropertyDefinition(DELETE_REASON, String.class, true));
    list.add(new PropertyDefinition(DEAL_FLG, Short.class, false));
    list.add(new PropertyDefinition(ERR_FLG, Short.class, false));
    list.add(new PropertyDefinition(BL_ENTRY_FLG, Short.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
