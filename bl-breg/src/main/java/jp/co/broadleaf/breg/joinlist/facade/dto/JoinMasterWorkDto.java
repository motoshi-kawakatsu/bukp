package jp.co.broadleaf.breg.joinlist.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 結合マスタ(メーカー)ワークのDtoのクラス。
 */
public class JoinMasterWorkDto {

  /**
   * UUID
   */
  private String uuid;

  /**
   * 作成日時
   */
  private String insDtTime;

  /**
   * 更新日時
   */
  private String updDtTime;

  /**
   * 作成アカウントID
   */
  private int insAccountId;

  /**
   * 更新アカウントID
   */
  private int updAccountId;

  /**
   * 論理削除区分
   */
  private int logicalDelDiv;

  /**
   * 優良設定詳細コード１
   */
  private int prmSetDtlNo1;

  /**
   * 部品メーカーコード
   */
  private int partsMakerCd;

  /**
   * 商品中分類コード
   */
  private int goodsMGroup;

  /**
   * BLコード
   */
  private int tbsPartsCode;

  /**
   * 結合元メーカーコード
   */
  private int joinSourceMakerCode;

  /**
   * 優良設定詳細コード２
   */
  private int prmSetDtlNo2;

  /**
   * 結合元品番(－付き品番)
   */
  private String joinSourPartsNoWithH;

  /**
   * 結合表示順位
   */
  private int joinDispOrder;

  /**
   * 結合先品番(－付き品番)
   */
  private String joinDestPartsNo;

  /**
   * 結合QTY
   */
  private Double joinQty;

  /**
   * 結合規格・特記事項
   */
  private String joinSpecialNote;

  /**
   * 優良部品規格・特記事項(C向け)
   */
  private String primePartsSpecialNoteC;

  /**
   * 適用日付
   */
  private String startTime;

  /**
   * 削除時申請理由
   */
  private String deleteReason;

  /**
   * チェック区分
   */
  private Short checkFlg;

  /**
   * データステータス
   */
  private Short errorFlg;

  /**
   * BL登録区分
   */
  private Short blEntryFlg;

  /**
   * インポート区分
   */
  private Short importKbn;

  /**
   * 処理区分
   */
  private Short manageKbn;

  /**
   * エラー内容
   */
  private String errorDetail;

  /**
   * 削除依頼区分
   */
  private Short deleteFlg;

  /**
   * 申請状態
   */
  private Short applyCondition;

  /**
   * UUIDの取得。
   *
   * @return String UUID
   */
  public String getUuid() {
    return uuid;
  }

  /**
   * 作成日時の取得。
   *
   * @return Date 作成日時
   */
  public String getInsDtTime() {
    return insDtTime;
  }

  /**
   * 更新日時の取得。
   *
   * @return Date 更新日時
   */
  public String getUpdDtTime() {
    return updDtTime;
  }

  /**
   * 作成アカウントIDの取得。
   *
   * @return int 作成アカウントID
   */
  public int getInsAccountId() {
    return insAccountId;
  }

  /**
   * 更新アカウントIDの取得。
   *
   * @return int 更新アカウントID
   */
  public int getUpdAccountId() {
    return updAccountId;
  }

  /**
   * 論理削除区分の取得。
   *
   * @return int 論理削除区分
   */
  public int getLogicalDelDiv() {
    return logicalDelDiv;
  }

  /**
   * 優良設定詳細コード１の取得。
   *
   * @return int 優良設定詳細コード１
   */
  public int getPrmSetDtlNo1() {
    return prmSetDtlNo1;
  }

  /**
   * 部品メーカーコードの取得。
   *
   * @return int 部品メーカーコード
   */
  public int getPartsMakerCd() {
    return partsMakerCd;
  }

  /**
   * 商品中分類コードの取得。
   *
   * @return int 商品中分類コード
   */
  public int getGoodsMGroup() {
    return goodsMGroup;
  }

  /**
   * BLコードの取得。
   *
   * @return int BLコード
   */
  public int getTbsPartsCode() {
    return tbsPartsCode;
  }

  /**
   * 結合元メーカーコードの取得。
   *
   * @return int 結合元メーカーコード
   */
  public int getJoinSourceMakerCode() {
    return joinSourceMakerCode;
  }

  /**
   * 優良設定詳細コード２の取得。
   *
   * @return int 優良設定詳細コード２
   */
  public int getPrmSetDtlNo2() {
    return prmSetDtlNo2;
  }

  /**
   * 結合元品番(－付き品番)の取得。
   *
   * @return String 結合元品番(－付き品番)
   */
  public String getJoinSourPartsNoWithH() {
    return joinSourPartsNoWithH;
  }

  /**
   * 結合表示順位の取得。
   *
   * @return int 結合表示順位
   */
  public int getJoinDispOrder() {
    return joinDispOrder;
  }

  /**
   * 結合先品番(－付き品番)の取得。
   *
   * @return String 結合先品番(－付き品番)
   */
  public String getJoinDestPartsNo() {
    return joinDestPartsNo;
  }

  /**
   * 結合QTYの取得。
   *
   * @return Double 結合QTY
   */
  public Double getJoinQty() {
    return joinQty;
  }

  /**
   * 結合規格・特記事項の取得。
   *
   * @return String 結合規格・特記事項
   */
  public String getJoinSpecialNote() {
    return joinSpecialNote;
  }

  /**
   * 優良部品規格・特記事項(C向け)の取得。
   *
   * @return String 優良部品規格・特記事項(C向け)
   */
  public String getPrimePartsSpecialNoteC() {
    return primePartsSpecialNoteC;
  }

  /**
   * 適用日付の取得。
   *
   * @return String 適用日付
   */
  public Timestamp getStartTime() {
    return toTimestamp(startTime);
  }

  /**
   * 削除時申請理由の取得。
   *
   * @return String 削除時申請理由
   */
  public String getDeleteReason() {
    return deleteReason;
  }

  /**
   * チェック区分の取得。
   *
   * @return Short チェック区分
   */
  public Short getCheckFlg() {
    return checkFlg;
  }

  /**
   * データステータスの取得。
   *
   * @return Short データステータス
   */
  public Short getErrorFlg() {
    return errorFlg;
  }

  /**
   * BL登録区分の取得。
   *
   * @return Short BL登録区分
   */
  public Short getBlEntryFlg() {
    return blEntryFlg;
  }

  /**
   * インポート区分の取得。
   *
   * @return Short インポート区分
   */
  public Short getImportKbn() {
    return importKbn;
  }

  /**
   * 処理区分の取得。
   *
   * @return Short 処理区分
   */
  public Short getManageKbn() {
    return manageKbn;
  }

  /**
   * エラー内容の取得。
   *
   * @return String エラー内容
   */
  public String getErrorDetail() {
    return errorDetail;
  }

  /**
   * 削除依頼区分の取得。
   *
   * @return Short 削除依頼区分
   */
  public Short getDeleteFlg() {
    return deleteFlg;
  }

  /**
   * 申請状態の取得。
   *
   * @return Short 申請状態
   */
  public Short getApplyCondition() {
    return applyCondition;
  }

  /**
   * UUIDの設定。
   *
   * @param uuid UUID
   */
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  /**
   * 作成日時の設定。
   *
   * @param insDtTime 作成日時
   */
  public void setInsDtTime(Date insDtTime) {
    this.insDtTime = toString(insDtTime);
  }

  /**
   * 更新日時の設定。
   *
   * @param updDtTime 更新日時s
   */
  public void setUpdDtTime(Date updDtTime) {
    this.updDtTime = toString(updDtTime);
  }

  /**
   * 作成アカウントIDの設定。
   *
   * @param insAccountId 作成アカウントID
   */
  public void setInsAccountId(int insAccountId) {
    this.insAccountId = insAccountId;
  }

  /**
   * 更新アカウントIDの設定。
   *
   * @param updAccountId 更新アカウントID
   */
  public void setUpdAccountId(int updAccountId) {
    this.updAccountId = updAccountId;
  }

  /**
   * 論理削除区分の設定。
   *
   * @param logicalDelDiv 論理削除区分
   */
  public void setLogicalDelDiv(int logicalDelDiv) {
    this.logicalDelDiv = logicalDelDiv;
  }

  /**
   * 優良設定詳細コード１の設定。
   *
   * @param prmSetDtlNo1 優良設定詳細コード１
   */
  public void setPrmSetDtlNo1(int prmSetDtlNo1) {
    this.prmSetDtlNo1 = prmSetDtlNo1;
  }

  /**
   * 部品メーカーコードの設定。
   *
   * @param partsMakerCd 部品メーカーコード
   */
  public void setPartsMakerCd(int partsMakerCd) {
    this.partsMakerCd = partsMakerCd;
  }

  /**
   * 商品中分類コードの設定。
   *
   * @param goodsMGroup 商品中分類コード
   */
  public void setGoodsMGroup(int goodsMGroup) {
    this.goodsMGroup = goodsMGroup;
  }

  /**
   * BLコードの設定。
   *
   * @param tbsPartsCode BLコード
   */
  public void setTbsPartsCode(int tbsPartsCode) {
    this.tbsPartsCode = tbsPartsCode;
  }

  /**
   * 結合元メーカーコードの設定。
   *
   * @param joinSourceMakerCode 結合元メーカーコード
   */
  public void setJoinSourceMakerCode(int joinSourceMakerCode) {
    this.joinSourceMakerCode = joinSourceMakerCode;
  }

  /**
   * 優良設定詳細コード２の設定。
   *
   * @param prmSetDtlNo2 優良設定詳細コード２
   */
  public void setPrmSetDtlNo2(int prmSetDtlNo2) {
    this.prmSetDtlNo2 = prmSetDtlNo2;
  }

  /**
   * 結合元品番(－付き品番)の設定。
   *
   * @param joinSourPartsNoWithH 結合元品番(－付き品番)
   */
  public void setJoinSourPartsNoWithH(String joinSourPartsNoWithH) {
    this.joinSourPartsNoWithH = joinSourPartsNoWithH;
  }

  /**
   * 結合表示順位の設定。
   *
   * @param joinDispOrder 結合表示順位
   */
  public void setJoinDispOrder(int joinDispOrder) {
    this.joinDispOrder = joinDispOrder;
  }

  /**
   * 結合先品番(－付き品番)の設定。
   *
   * @param joinDestPartsNo 結合先品番(－付き品番)
   */
  public void setJoinDestPartsNo(String joinDestPartsNo) {
    this.joinDestPartsNo = joinDestPartsNo;
  }

  /**
   * 結合QTYの設定。
   *
   * @param joinQty 結合QTY
   */
  public void setJoinQty(Double joinQty) {
    this.joinQty = joinQty;
  }

  /**
   * 結合規格・特記事項の設定。
   *
   * @param joinSpecialNote 結合規格・特記事項
   */
  public void setJoinSpecialNote(String joinSpecialNote) {
    this.joinSpecialNote = joinSpecialNote;
  }

  /**
   * 優良部品規格・特記事項(C向け)の設定。
   *
   * @param primePartsSpecialNoteC 優良部品規格・特記事項(C向け)
   */
  public void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC) {
    this.primePartsSpecialNoteC = primePartsSpecialNoteC;
  }

  /**
   * 適用日付の設定。
   *
   * @param startTime 適用日付
   */
  public void setStartTime(Timestamp startTime) {
    this.startTime = toString(startTime);
  }

  /**
   * 削除時申請理由の設定。
   *
   * @param deleteReason 削除時申請理由
   */
  public void setDeleteReason(String deleteReason) {
    this.deleteReason = deleteReason;
  }

  /**
   * チェック区分の設定。
   *
   * @param checkFlg チェック区分
   */
  public void setCheckFlg(Short checkFlg) {
    this.checkFlg = checkFlg;
  }

  /**
   * データステータスの設定。
   *
   * @param errorFlg データステータス
   */
  public void setErrorFlg(Short errorFlg) {
    this.errorFlg = errorFlg;
  }

  /**
   * BL登録区分の設定。
   *
   * @param blEntryFlg BL登録区分
   */
  public void setBlEntryFlg(Short blEntryFlg) {
    this.blEntryFlg = blEntryFlg;
  }

  /**
   * インポート区分の設定。
   *
   * @param importKbn インポート区分
   */
  public void setImportKbn(Short importKbn) {
    this.importKbn = importKbn;
  }

  /**
   * 処理区分の設定。
   *
   * @param manageKbn 処理区分
   */
  public void setManageKbn(Short manageKbn) {
    this.manageKbn = manageKbn;
  }

  /**
   * エラー内容の設定。
   *
   * @param errorDetail エラー内容
   */
  public void setErrorDetail(String errorDetail) {
    this.errorDetail = errorDetail;
  }

  /**
   * 削除依頼区分の設定。
   *
   * @param deleteFlg 削除依頼区分
   */
  public void setDeleteFlg(Short deleteFlg) {
    this.deleteFlg = deleteFlg;
  }

  /**
   * 申請状態の設定。
   *
   * @param applyCondition 申請状態
   */
  public void setApplyCondition(Short applyCondition) {
    this.applyCondition = applyCondition;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(uuid).append(insDtTime).append(updDtTime).append(insAccountId)
        .append(updAccountId).append(logicalDelDiv).append(prmSetDtlNo1).append(partsMakerCd).append(goodsMGroup)
        .append(tbsPartsCode).append(joinSourceMakerCode).append(prmSetDtlNo2).append(joinSourPartsNoWithH)
        .append(joinDispOrder).append(joinDestPartsNo).append(joinQty).append(joinSpecialNote)
        .append(primePartsSpecialNoteC).append(startTime).append(deleteReason).append(checkFlg).append(errorFlg)
        .append(blEntryFlg).append(importKbn).append(manageKbn).append(errorDetail).append(deleteFlg)
        .append(applyCondition).toHashCode();
  }

  /**
   * equalsメソッド
   * 
   * @param obj オブジェクト
   * @return 比較結果
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    JoinMasterWorkDto other = (JoinMasterWorkDto) obj;
    return new EqualsBuilder().append(uuid, other.uuid).append(insDtTime, other.insDtTime)
        .append(updDtTime, other.updDtTime).append(insAccountId, other.insAccountId)
        .append(updAccountId, other.updAccountId).append(logicalDelDiv, other.logicalDelDiv)
        .append(prmSetDtlNo1, other.prmSetDtlNo1).append(partsMakerCd, other.partsMakerCd)
        .append(goodsMGroup, other.goodsMGroup).append(tbsPartsCode, other.tbsPartsCode)
        .append(joinSourceMakerCode, other.joinSourceMakerCode).append(prmSetDtlNo2, other.prmSetDtlNo2)
        .append(joinSourPartsNoWithH, other.joinSourPartsNoWithH).append(joinDispOrder, other.joinDispOrder)
        .append(joinDestPartsNo, other.joinDestPartsNo).append(joinQty, other.joinQty)
        .append(joinSpecialNote, other.joinSpecialNote).append(primePartsSpecialNoteC, other.primePartsSpecialNoteC)
        .append(startTime, other.startTime).append(deleteReason, other.deleteReason).append(checkFlg, other.checkFlg)
        .append(errorFlg, other.errorFlg).append(blEntryFlg, other.blEntryFlg).append(importKbn, other.importKbn)
        .append(manageKbn, other.manageKbn).append(errorDetail, other.errorDetail).append(deleteFlg, other.deleteFlg)
        .append(applyCondition, other.applyCondition).isEquals();
  }

  /**
   * DateからStringに転換
   * 
   * @param date date
   * @return 転換結果
   */
  private String toString(Date date) {
    String time;
    SimpleDateFormat formater = new SimpleDateFormat();
    formater.applyPattern("yyyy-MM-dd");
    time = formater.format(date);
    return time;
  }

  /**
   * StringからDateに転換
   * 
   * @param dateStr dateStr
   * @return 転換結果
   */
  private Timestamp toTimestamp(String dateStr) {
    Timestamp ts = null;
    SimpleDateFormat formater = new SimpleDateFormat();
    formater.applyPattern("yyyy-MM-dd");
    try {
      ts = Timestamp.valueOf(dateStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ts;
  }

}
