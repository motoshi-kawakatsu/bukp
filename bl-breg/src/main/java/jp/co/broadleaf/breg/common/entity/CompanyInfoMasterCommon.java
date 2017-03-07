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
 * 会社情報マスタ
 */
@Entity(name = "m_company_info_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class CompanyInfoMasterCommon extends BroadleafDomainEntity<Integer> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * メーカーコード
   */
  public static final String MAKER_CODE = "makerCode";

  /**
   * メーカー名称
   */
  public static final String MAKER_NAME = "makerName";

  /**
   * メーカー名称（カナ）
   */
  public static final String MAKER_KANA = "makerKana";

  /**
   * 会社名称
   */
  public static final String COMPANY_NAME = "companyName";

  /**
   * 会社名称（カナ）
   */
  public static final String COMPANY_KANA = "companyKana";

  /**
   * 郵便番号
   */
  public static final String POST_CODE = "postCode";

  /**
   * 住所
   */
  public static final String ADDRESS = "address";

  /**
   * TEL
   */
  public static final String TEL_NO = "telNo";

  /**
   * FAX
   */
  public static final String FAX_NO = "faxNo";

  /**
   * 備考
   */
  public static final String NOTE = "note";

  /**
   * 商品ページ内行数設定
   */
  public static final String GOODS_ROWS = "goodsRows";

  /**
   * セットページ内行数設定
   */
  public static final String SET_ROWS = "setRows";

  /**
   * 結合ページ内行数設定
   */
  public static final String JOIN_ROWS = "joinRows";

  /**
   * 申請履歴ページ内行数設定
   */
  public static final String APPLY_HISTORY_ROWS = "applyHistoryRows";

  /**
   * 申請詳細ページ内行数設定
   */
  public static final String APPLY_DETAIL_ROWS = "applyDetailRows";

  /**
   * 商品インポート方法
   */
  public static final String GOODS_IMPORT_TYPE = "goodsImportType";

  /**
   * セットインポート方法
   */
  public static final String SET_IMPORT_TYPE = "setImportType";

  /**
   * 結合インポート方法
   */
  public static final String JOIN_IMPORT_TYPE = "joinImportType";

  /**
   * Bl側連絡メールアドレス
   */
  public static final String BL_MAIL_ADD = "blMailAdd";

  /**
   * コンストラクタ。
   */
  public CompanyInfoMasterCommon() {

  }

  /**
   * コンストラクタ。
   * 
   * @param makerCode ID
   */
  public CompanyInfoMasterCommon(int makerCode) {
    setMakerCode(makerCode);
  }

  /**
   * メーカーコードを返します。
   * 
   * @return メーカーコード
   */
  public int getMakerCode() {
    return container().getPropertyValue(MAKER_CODE, Integer.class);
  }

  /**
   * メーカーコードを設定します。
   * 
   * @param makerCode セットするメーカーコード
   */
  public void setMakerCode(int makerCode) {
    container().setPropertyValue(MAKER_CODE, makerCode);
  }

  /**
   * メーカー名称を返します。
   * 
   * @return メーカー名称
   */
  public String getMakerName() {
    return container().getPropertyValue(MAKER_NAME, String.class);
  }

  /**
   * メーカー名称を設定します。
   * 
   * @param makerName セットするメーカー名称
   */
  public void setMakerName(String makerName) {
    container().setPropertyValue(MAKER_NAME, makerName);
  }

  /**
   * メーカー名称（カナ）を返します。
   * 
   * @return メーカー名称（カナ）
   */
  public String getMakerKana() {
    return container().getPropertyValue(MAKER_KANA, String.class);
  }

  /**
   * メーカー名称（カナ）を設定します。
   * 
   * @param makerKana セットするメーカー名称（カナ）
   */
  public void setMakerKana(String makerKana) {
    container().setPropertyValue(MAKER_KANA, makerKana);
  }

  /**
   * 会社名称を返します。
   * 
   * @return 会社名称
   */
  public String getCompanyName() {
    return container().getPropertyValue(COMPANY_NAME, String.class);
  }

  /**
   * 会社名称を設定します。
   * 
   * @param companyName セットする会社名称
   */
  public void setCompanyName(String companyName) {
    container().setPropertyValue(COMPANY_NAME, companyName);
  }

  /**
   * 会社名称（カナ）を返します。
   * 
   * @return 会社名称（カナ）
   */
  public String getCompanyKana() {
    return container().getPropertyValue(COMPANY_KANA, String.class);
  }

  /**
   * 会社名称（カナ）を設定します。
   * 
   * @param companyKana セットする会社名称（カナ）
   */
  public void setCompanyKana(String companyKana) {
    container().setPropertyValue(COMPANY_KANA, companyKana);
  }

  /**
   * 郵便番号を返します。
   * 
   * @return 郵便番号
   */
  public String getPostCode() {
    return container().getPropertyValue(POST_CODE, String.class);
  }

  /**
   * 郵便番号を設定します。
   * 
   * @param postCode セットする郵便番号
   */
  public void setPostCode(String postCode) {
    container().setPropertyValue(POST_CODE, postCode);
  }

  /**
   * 住所を返します。
   * 
   * @return 住所
   */
  public String getAddress() {
    return container().getPropertyValue(ADDRESS, String.class);
  }

  /**
   * 住所を設定します。
   * 
   * @param address セットする住所
   */
  public void setAddress(String address) {
    container().setPropertyValue(ADDRESS, address);
  }

  /**
   * TELを返します。
   * 
   * @return TEL
   */
  public String getTelNo() {
    return container().getPropertyValue(TEL_NO, String.class);
  }

  /**
   * TELを設定します。
   * 
   * @param telNo セットするTEL
   */
  public void setTelNo(String telNo) {
    container().setPropertyValue(TEL_NO, telNo);
  }

  /**
   * FAXを返します。
   * 
   * @return FAX
   */
  public String getFaxNo() {
    return container().getPropertyValue(FAX_NO, String.class);
  }

  /**
   * FAXを設定します。
   * 
   * @param faxNo セットするFAX
   */
  public void setFaxNo(String faxNo) {
    container().setPropertyValue(FAX_NO, faxNo);
  }

  /**
   * 備考を返します。
   * 
   * @return 備考
   */
  public String getNote() {
    return container().getPropertyValue(NOTE, String.class);
  }

  /**
   * 備考を設定します。
   * 
   * @param note セットする備考
   */
  public void setNote(String note) {
    container().setPropertyValue(NOTE, note);
  }

  /**
   * 商品ページ内行数設定を返します。
   * 
   * @return 商品ページ内行数設定
   */
  public int getGoodsRows() {
    return container().getPropertyValue(GOODS_ROWS, Integer.class);
  }

  /**
   * 商品ページ内行数設定を設定します。
   * 
   * @param goodsRows セットする商品ページ内行数設定
   */
  public void setGoodsRows(int goodsRows) {
    container().setPropertyValue(GOODS_ROWS, goodsRows);
  }

  /**
   * セットページ内行数設定を返します。
   * 
   * @return セットページ内行数設定
   */
  public int getSetRows() {
    return container().getPropertyValue(SET_ROWS, Integer.class);
  }

  /**
   * セットページ内行数設定を設定します。
   * 
   * @param setRows セットするセットページ内行数設定
   */
  public void setSetRows(int setRows) {
    container().setPropertyValue(SET_ROWS, setRows);
  }

  /**
   * 結合ページ内行数設定を返します。
   * 
   * @return 結合ページ内行数設定
   */
  public int getJoinRows() {
    return container().getPropertyValue(JOIN_ROWS, Integer.class);
  }

  /**
   * 結合ページ内行数設定を設定します。
   * 
   * @param joinRows セットする結合ページ内行数設定
   */
  public void setJoinRows(int joinRows) {
    container().setPropertyValue(JOIN_ROWS, joinRows);
  }

  /**
   * 申請履歴ページ内行数設定を返します。
   * 
   * @return 申請履歴ページ内行数設定
   */
  public int getApplyHistoryRows() {
    return container().getPropertyValue(APPLY_HISTORY_ROWS, Integer.class);
  }

  /**
   * 申請履歴ページ内行数設定を設定します。
   * 
   * @param applyHistoryRows セットする申請履歴ページ内行数設定
   */
  public void setApplyHistoryRows(int applyHistoryRows) {
    container().setPropertyValue(APPLY_HISTORY_ROWS, applyHistoryRows);
  }

  /**
   * 申請詳細ページ内行数設定を返します。
   * 
   * @return 申請詳細ページ内行数設定
   */
  public int getApplyDetailRows() {
    return container().getPropertyValue(APPLY_DETAIL_ROWS, Integer.class);
  }

  /**
   * 申請詳細ページ内行数設定を設定します。
   * 
   * @param applyDetailRows セットする申請詳細ページ内行数設定
   */
  public void setApplyDetailRows(int applyDetailRows) {
    container().setPropertyValue(APPLY_DETAIL_ROWS, applyDetailRows);
  }

  /**
   * 商品インポート方法を返します。
   * 
   * @return 商品インポート方法
   */
  public int getGoodsImportType() {
    return container().getPropertyValue(GOODS_IMPORT_TYPE, Integer.class);
  }

  /**
   * 商品インポート方法を設定します。
   * 
   * @param goodsImportType セットする商品インポート方法
   */
  public void setGoodsImportType(int goodsImportType) {
    container().setPropertyValue(GOODS_IMPORT_TYPE, goodsImportType);
  }

  /**
   * セットインポート方法を返します。
   * 
   * @return セットインポート方法
   */
  public int getSetImportType() {
    return container().getPropertyValue(SET_IMPORT_TYPE, Integer.class);
  }

  /**
   * セットインポート方法を設定します。
   * 
   * @param setImportType セットするセットインポート方法
   */
  public void setSetImportType(int setImportType) {
    container().setPropertyValue(SET_IMPORT_TYPE, setImportType);
  }

  /**
   * 結合インポート方法を返します。
   * 
   * @return 結合インポート方法
   */
  public int getJoinImportType() {
    return container().getPropertyValue(JOIN_IMPORT_TYPE, Integer.class);
  }

  /**
   * 結合インポート方法を設定します。
   * 
   * @param joinImportType セットする結合インポート方法
   */
  public void setJoinImportType(int joinImportType) {
    container().setPropertyValue(JOIN_IMPORT_TYPE, joinImportType);
  }

  /**
   * Bl側連絡メールアドレスを返します。
   * 
   * @return Bl側連絡メールアドレス
   */
  public String getBlMailAdd() {
    return container().getPropertyValue(BL_MAIL_ADD, String.class);
  }

  /**
   * Bl側連絡メールアドレスを設定します。
   * 
   * @param blMailAdd セットするBl側連絡メールアドレス
   */
  public void setBlMailAdd(String blMailAdd) {
    container().setPropertyValue(BL_MAIL_ADD, blMailAdd);
  }

  /**
   * エンティティIDを取得する。
   * 
   * @return エンティティID
   */
  @Override
  public Integer identifier() {
    return getMakerCode();
  }

  /**
   * エンティティIDのプロパティリストを取得する
   * 
   * @return エンティティIDのプロパティリスト
   */
  @Override
  public String[] identifierPropertyNames() {
    return new String[] { MAKER_CODE };
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
    list.add(new PropertyDefinition(MAKER_CODE, Integer.class, false));
    list.add(new PropertyDefinition(MAKER_NAME, String.class, false));
    list.add(new PropertyDefinition(MAKER_KANA, String.class, false));
    list.add(new PropertyDefinition(COMPANY_NAME, String.class, false));
    list.add(new PropertyDefinition(COMPANY_KANA, String.class, false));
    list.add(new PropertyDefinition(POST_CODE, String.class, false));
    list.add(new PropertyDefinition(ADDRESS, String.class, false));
    list.add(new PropertyDefinition(TEL_NO, String.class, false));
    list.add(new PropertyDefinition(FAX_NO, String.class, false));
    list.add(new PropertyDefinition(NOTE, String.class, false));
    list.add(new PropertyDefinition(GOODS_ROWS, Integer.class, false));
    list.add(new PropertyDefinition(SET_ROWS, Integer.class, false));
    list.add(new PropertyDefinition(JOIN_ROWS, Integer.class, false));
    list.add(new PropertyDefinition(APPLY_HISTORY_ROWS, Integer.class, false));
    list.add(new PropertyDefinition(APPLY_DETAIL_ROWS, Integer.class, false));
    list.add(new PropertyDefinition(GOODS_IMPORT_TYPE, Integer.class, false));
    list.add(new PropertyDefinition(SET_IMPORT_TYPE, Integer.class, false));
    list.add(new PropertyDefinition(JOIN_IMPORT_TYPE, Integer.class, false));
    list.add(new PropertyDefinition(BL_MAIL_ADD, String.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
