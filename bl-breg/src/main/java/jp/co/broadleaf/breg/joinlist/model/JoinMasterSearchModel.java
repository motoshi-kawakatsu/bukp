package jp.co.broadleaf.breg.joinlist.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 結合マスタの検索条件のModelクラスです
 */
public class JoinMasterSearchModel {

  /**
   * BLコード
   */
  private Integer tbsPartsCode;
  /**
   * 純正品番
   */
  private String joinSourPartsNoWithH;
  /**
   * 優良品番
   */
  private String joinDestPartsNo;
  /**
   * 申請状態
   */
  private Short applyCondition;
  /**
   * 処理区分
   */
  private Short manageKbn;
  /**
   * データステータス
   */
  private Short errorFlg;

  /**
   * セレクトコード
   */
  private Integer prmSetDtlNo1;
  /**
   * カーコード
   */
  private Integer joinSourceMakerCode;

  /**
   * 適用日付From
   */
  private Timestamp startTimeStart;

  /**
   * 適用日付To
   */
  private Timestamp startTimeEnd;

  /**
   * 商品中分類コード
   */
  private Integer goodsMGroup;
  /**
   * 種別コード
   */
  private Integer prmSetDtlNo2;
  /**
   * 作成日時From
   */
  private Date insDtTimeStart;
  /**
   * 作成日時To
   */
  private Date insDtTimeEnd;

  /**
   * 規格・特記
   */
  private String joinSpecialNote;

  /**
   * 削除依頼区分
   */
  private Short deleteFlg;

  /**
   * 更新日時From
   */
  private Date updDtTimeStart;

  /**
   * 更新日時To
   */
  private Date updDtTimeEnd;

  /**
   * 規格/特記(一般)
   */
  private String primePartsSpecialNoteC;

  /**
   * 結合表示順位
   */
  private int joinDispOrder;

  /**
   * 部品メーカーコード
   */
  private int partsMakerCd;

  /**
   * <pre>
   * 【 セレクトコード】を取得する。
   * </pre>
   *
   * @return 【 セレクトコード】
   */
  public Integer getPrmSetDtlNo1() {
    return prmSetDtlNo1;
  }

  /**
   * <pre>
   * 【 セレクトコード】を設定する。
   * </pre>
   *
   * @param prmSetDtlNo1 【 セレクトコード】
   */
  public void setPrmSetDtlNo1(Integer prmSetDtlNo1) {
    this.prmSetDtlNo1 = prmSetDtlNo1;
  }

  /**
   * <pre>
   * 【 商品中分類コード】を取得する。
   * </pre>
   *
   * @return 【 商品中分類コード】
   */
  public Integer getGoodsMGroup() {
    return goodsMGroup;
  }

  /**
   * <pre>
   * 【 商品中分類コード】を設定する。
   * </pre>
   *
   * @param goodsMGroup 【 商品中分類コード】
   */
  public void setGoodsMGroup(Integer goodsMGroup) {
    this.goodsMGroup = goodsMGroup;
  }

  /**
   * <pre>
   * 【BLコード】を取得する。
   * </pre>
   *
   * @return 【BLコード】
   */
  public Integer getTbsPartsCode() {
    return tbsPartsCode;
  }

  /**
   * <pre>
   * 【BLコード】を設定する。
   * </pre>
   *
   * @param tbsPartsCode 【BLコード】
   */
  public void setTbsPartsCode(Integer tbsPartsCode) {
    this.tbsPartsCode = tbsPartsCode;
  }

  /**
   * <pre>
   * 規格/特記(一般)を取得する。
   * </pre>
   *
   * @return 規格/特記(一般)
   */
  public String getPrimePartsSpecialNoteC() {
    return primePartsSpecialNoteC;
  }

  /**
   * <pre>
   * 規格/特記(一般)を設定する。
   * </pre>
   *
   * @param primePartsSpecialNoteC 規格/特記(一般)
   */
  public void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC) {
    this.primePartsSpecialNoteC = primePartsSpecialNoteC;
  }

  /**
   * <pre>
   * 【 データステータス】を取得する。
   * </pre>
   *
   * @return 【 データステータス】
   */
  public Short getErrorFlg() {
    return errorFlg;
  }

  /**
   * <pre>
   * 【 データステータス】を設定する。
   * </pre>
   *
   * @param errorFlg 【 データステータス】
   */
  public void setErrorFlg(Short errorFlg) {
    this.errorFlg = errorFlg;
  }

  /**
   * <pre>
   * 【処理区分】を取得する。
   * </pre>
   *
   * @return 【処理区分】
   */
  public Short getManageKbn() {
    return manageKbn;
  }

  /**
   * <pre>
   * 【処理区分】を設定する。
   * </pre>
   *
   * @param manageKbn 【処理区分】
   */
  public void setManageKbn(Short manageKbn) {
    this.manageKbn = manageKbn;
  }

  /**
   * <pre>
   * 【 削除依頼区分】を取得する。
   * </pre>
   *
   * @return 【 削除依頼区分】
   */
  public Short getDeleteFlg() {
    return deleteFlg;
  }

  /**
   * <pre>
   * 【 削除依頼区分】を設定する。
   * </pre>
   *
   * @param deleteFlg 【 削除依頼区分】
   */
  public void setDeleteFlg(Short deleteFlg) {
    this.deleteFlg = deleteFlg;
  }

  /**
   * <pre>
   * 【申請状態】を取得する。
   * </pre>
   *
   * @return 【申請状態】
   */
  public Short getApplyCondition() {
    return applyCondition;
  }

  /**
   * <pre>
   * 【申請状態】を設定する。
   * </pre>
   *
   * @param applyCondition 【申請状態】
   */
  public void setApplyCondition(Short applyCondition) {
    this.applyCondition = applyCondition;
  }

  /**
   * 純正品番の取得。
   *
   * @return String 純正品番
   */
  public String getJoinSourPartsNoWithH() {
    return joinSourPartsNoWithH;
  }

  /**
   * 純正品番の設定。
   *
   * @param joinSourPartsNoWithH 純正品番
   */
  public void setJoinSourPartsNoWithH(String joinSourPartsNoWithH) {
    this.joinSourPartsNoWithH = joinSourPartsNoWithH;
  }

  /**
   * <pre>
   * 優良品番を取得する。
   * </pre>
   *
   * @return String 優良品番
   */
  public String getJoinDestPartsNo() {
    return joinDestPartsNo;
  }

  /**
   * 優良品番の設定。
   *
   * @param joinDestPartsNo 優良品番
   */
  public void setJoinDestPartsNo(String joinDestPartsNo) {
    this.joinDestPartsNo = joinDestPartsNo;
  }

  /**
   * <pre>
   * カーコードを取得する。
   * </pre>
   *
   * @return カーコード
   */
  public Integer getJoinSourceMakerCode() {
    return joinSourceMakerCode;
  }

  /**
   * カーコードの設定。
   *
   * @param joinSourceMakerCode カーコード
   */
  public void setJoinSourceMakerCode(Integer joinSourceMakerCode) {
    this.joinSourceMakerCode = joinSourceMakerCode;
  }

  /**
   * <pre>
   * 【適用日付From】を取得する。
   * </pre>
   *
   * @return 【適用日付From】
   */
  public Timestamp getStartTimeStart() {
    return startTimeStart;
  }

  /**
   * 適用日付Fromの設定。
   *
   * @param startTimeStart 適用日付From
   */
  public void setStartTimeStart(Timestamp startTimeStart) {
    this.startTimeStart = startTimeStart;
  }

  /**
   * <pre>
   * 【適用日付To】を取得する。
   * </pre>
   *
   * @return 【適用日付To】
   */
  public Timestamp getStartTimeEnd() {
    return startTimeEnd;
  }

  /**
   * 適用日付Toの設定。
   *
   * @param startTimeEnd 適用日付To
   */
  public void setStartTimeEnd(Timestamp startTimeEnd) {
    this.startTimeEnd = startTimeEnd;
  }

  /**
   * <pre>
   * 種別コードを取得する。
   * </pre>
   *
   * @return 種別コード
   */
  public Integer getPrmSetDtlNo2() {
    return prmSetDtlNo2;
  }

  /**
   * 種別コードの設定。
   *
   * @param prmSetDtlNo2 種別コード
   */
  public void setPrmSetDtlNo2(Integer prmSetDtlNo2) {
    this.prmSetDtlNo2 = prmSetDtlNo2;
  }

  /**
   * <pre>
   * 【作成日時From】を取得する。
   * </pre>
   *
   * @return 【作成日時From】
   */
  public Date getInsDtTimeStart() {
    return insDtTimeStart;
  }

  /**
   * 作成日時Fromの設定。
   *
   * @param insDtTimeStart 作成日時From
   */
  public void setInsDtTimeStart(Date insDtTimeStart) {
    this.insDtTimeStart = insDtTimeStart;
  }

  /**
   * <pre>
   * 【作成日時To】を取得する。
   * </pre>
   *
   * @return 【作成日時To】
   */
  public Date getInsDtTimeEnd() {
    return insDtTimeEnd;
  }

  /**
   * 作成日時Toの設定。
   *
   * @param insDtTimeEnd 作成日時To
   */
  public void setInsDtTimeEnd(Date insDtTimeEnd) {
    this.insDtTimeEnd = insDtTimeEnd;
  }

  /**
   * <pre>
   * 規格・特記を取得する。
   * </pre>
   *
   * @return 規格・特記
   */
  public String getJoinSpecialNote() {
    return joinSpecialNote;
  }

  /**
   * 規格・特記の設定。
   *
   * @param joinSpecialNote 規格・特記
   */
  public void setJoinSpecialNote(String joinSpecialNote) {
    this.joinSpecialNote = joinSpecialNote;
  }

  /**
   * <pre>
   * 【更新日時From】を取得する。
   * </pre>
   *
   * @return 【更新日時From】
   */
  public Date getUpdDtTimeStart() {
    return updDtTimeStart;
  }

  /**
   * 更新日時Fromの設定。
   *
   * @param updDtTimeStart 更新日時From
   */
  public void setUpdDtTimeStart(Date updDtTimeStart) {
    this.updDtTimeStart = updDtTimeStart;
  }

  /**
   * <pre>
   * 【更新日時To】を取得する。
   * </pre>
   *
   * @return 【更新日時To】
   */
  public Date getUpdDtTimeEnd() {
    return updDtTimeEnd;
  }

  /**
   * 更新日時Toの設定。
   *
   * @param updDtTimeEnd 更新日時To
   */
  public void setUpdDtTimeEnd(Date updDtTimeEnd) {
    this.updDtTimeEnd = updDtTimeEnd;
  }

  /**
   * <pre>
   * 【結合表示順位】を取得する。
   * </pre>
   *
   * @return 【結合表示順位】
   */
  public int getJoinDispOrder() {
    return joinDispOrder;
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
   * <pre>
   * 【 部品メーカーコード】を取得する。
   * </pre>
   *
   * @return 【 部品メーカーコード】
   */
  public int getPartsMakerCd() {
    return partsMakerCd;
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
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(tbsPartsCode).append(joinSourPartsNoWithH).append(joinDestPartsNo)
        .append(applyCondition).append(manageKbn).append(errorFlg).append(prmSetDtlNo1).append(joinSourceMakerCode)
        .append(startTimeStart).append(startTimeEnd).append(goodsMGroup).append(prmSetDtlNo2).append(insDtTimeStart)
        .append(insDtTimeEnd).append(joinSpecialNote).append(primePartsSpecialNoteC).append(updDtTimeStart)
        .append(updDtTimeEnd).append(deleteFlg).toHashCode();
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
    JoinMasterSearchModel other = (JoinMasterSearchModel) obj;
    return new EqualsBuilder().append(tbsPartsCode, other.tbsPartsCode)
        .append(joinSourPartsNoWithH, other.joinSourPartsNoWithH).append(joinDestPartsNo, other.joinDestPartsNo)
        .append(applyCondition, other.applyCondition).append(manageKbn, other.manageKbn)
        .append(errorFlg, other.errorFlg).append(prmSetDtlNo1, other.prmSetDtlNo1)
        .append(joinSourceMakerCode, other.joinSourceMakerCode).append(startTimeStart, other.startTimeStart)
        .append(startTimeEnd, other.startTimeEnd).append(goodsMGroup, other.goodsMGroup)
        .append(prmSetDtlNo2, other.prmSetDtlNo2).append(insDtTimeStart, other.insDtTimeStart)
        .append(insDtTimeEnd, other.insDtTimeEnd).append(joinSpecialNote, other.joinSpecialNote)
        .append(primePartsSpecialNoteC, other.primePartsSpecialNoteC).append(updDtTimeStart, other.updDtTimeStart)
        .append(updDtTimeEnd, other.updDtTimeEnd).append(deleteFlg, other.deleteFlg).isEquals();
  }
}
