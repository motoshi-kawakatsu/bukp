package jp.co.broadleaf.breg.joinlist.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * 結合一覧のグリッドDtoのクラス。のクラス。
 */
public class JoinGridDto implements Serializable {

  /**  */
  private static final long serialVersionUID = 4828199879508559008L;

  /**
   * check
   */
  private Boolean check;

  /**
   * no
   */
  private int no;

  /**
   * 申請状態
   */
  private String applyCondition;

  /**
   * 処理区分
   */
  private String manageKbn;

  /**
   * セレクトコード
   */
  private String prmSetDtlNo1;

  /**
   * 分類コード名称
   */
  private String goodsMGroup;

  /**
   * BLコード名称
   */
  private String tbsPartsCode;

  /**
   * カーコード名称
   */
  private String joinSourceMakerCode;

  /**
   * 純正品番
   */
  private String joinSourPartsNoWithH;

  /**
   * 種別コード名称
   */
  private String prmSetDtlNo2;

  /**
   * 表示順位
   */
  private String joinDispOrder;

  /**
   * 優良品番
   */
  private String joinDestPartsNo;

  /**
   * QTY
   */
  private String joinQty;

  /**
   * 規格/特記
   */
  private String joinSpecialNote;

  /**
   * 規格/特記（一般）
   */
  private String primePartsSpecialNoteC;

  /**
   * 削除依頼区分
   */
  private String deleteFlg;

  /**
   * 削除理由
   */
  private String deleteReason;

  /**
   * 作成日時
   */
  private String insDtTime;

  /**
   * 更新日時
   */
  private String updDtTime;

  /**
   * 適用日時
   */
  private String startTime;

  /**
   * チェック区分
   */
  private String checkFlg;

  /**
   * BL登録区分
   */
  private String blEntryFlg;

  /**
   * エラー区分
   */
  private String errorFlg;

  /**
   * エラー内容
   */
  private String errorDetail;

  /**
   * インポート区分
   */
  private int importKbn;

  /**
   * 隠匿域
   */
  private Integer hiddenArea;

  /**
   * 履歴比較のフラグ
   */
  private String compareFlag;

  /**
   * エラー項目のフラグ
   */
  private String errorFlag;

  /**
   * checkの取得。
   *
   * @return Boolean check
   */
  public Boolean getCheck() {
    return check;
  }

  /**
   * checkの設定。
   *
   * @param check check
   */
  public void setCheck(Boolean check) {
    this.check = check;
  }

  /**
   * <pre>
   * 【no】を取得する。
   * </pre>
   *
   * @return 【no】
   */
  public int getNo() {
    return no;
  }

  /**
   * <pre>
   * 【no】を設定する。
   * </pre>
   *
   * @param no 【no】
   */
  public void setNo(int no) {
    this.no = no;
  }

  /**
   * 申請状態の取得。
   *
   * @return String 申請状態
   */
  public String getApplyCondition() {
    return applyCondition;
  }

  /**
   * 処理区分の取得。
   *
   * @return String 処理区分
   */
  public String getManageKbn() {
    return manageKbn;
  }

  /**
   * セレクトコードの取得。
   *
   * @return String セレクトコード
   */
  public String getPrmSetDtlNo1() {
    return prmSetDtlNo1;
  }

  /**
   * 分類コード名称の取得。
   *
   * @return String 分類コード名称
   */
  public String getGoodsMGroup() {
    return goodsMGroup;
  }

  /**
   * BLコード名称の取得。
   *
   * @return String BLコード名称
   */
  public String getTbsPartsCode() {
    return tbsPartsCode;
  }

  /**
   * カーコード名称の取得。
   *
   * @return String カーコード名称
   */
  public String getJoinSourceMakerCode() {
    return joinSourceMakerCode;
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
   * 種別コード名称の取得。
   *
   * @return String 種別コード名称
   */
  public String getPrmSetDtlNo2() {
    return prmSetDtlNo2;
  }

  /**
   * 表示順位の取得。
   *
   * @return String 表示順位
   */
  public String getJoinDispOrder() {
    return joinDispOrder;
  }

  /**
   * 優良品番の取得。
   *
   * @return String 優良品番
   */
  public String getJoinDestPartsNo() {
    return joinDestPartsNo;
  }

  /**
   * QTYの取得。
   *
   * @return String QTY
   */
  public String getJoinQty() {
    return joinQty;
  }

  /**
   * 規格/特記の取得。
   *
   * @return String 規格/特記
   */
  public String getJoinSpecialNote() {
    return joinSpecialNote;
  }

  /**
   * 規格/特記（一般）の取得。
   *
   * @return String 規格/特記（一般）
   */
  public String getPrimePartsSpecialNoteC() {
    return primePartsSpecialNoteC;
  }

  /**
   * 削除依頼区分の取得。
   *
   * @return String 削除依頼区分
   */
  public String getDeleteFlg() {
    return deleteFlg;
  }

  /**
   * 削除理由の取得。
   *
   * @return String 削除理由
   */
  public String getDeleteReason() {
    return deleteReason;
  }

  /**
   * 作成日時の取得。
   *
   * @return String 作成日時
   */
  public String getInsDtTime() {
    return insDtTime;
  }

  /**
   * 更新日時の取得。
   *
   * @return String 更新日時
   */
  public String getUpdDtTime() {
    return updDtTime;
  }

  /**
   * 適用日時の取得。
   *
   * @return String 適用日時
   */
  public String getStartTime() {
    return startTime;
  }

  /**
   * チェック区分の取得。
   *
   * @return String チェック区分
   */
  public String getCheckFlg() {
    return checkFlg;
  }

  /**
   * BL登録区分の取得。
   *
   * @return String BL登録区分
   */
  public String getBlEntryFlg() {
    return blEntryFlg;
  }

  /**
   * エラー区分の取得。
   *
   * @return String エラー区分
   */
  public String getErrorFlg() {
    return errorFlg;
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
   * インポート区分の取得。
   *
   * @return Short インポート区分
   */
  public int getImportKbn() {
    return importKbn;
  }

  /**
   * 隠匿域の取得。
   *
   * @return Integer 隠匿域
   */
  public Integer getHiddenArea() {
    return hiddenArea;
  }

  /**
   * 履歴比較のフラグの取得。
   *
   * @return String 履歴比較のフラグ
   */
  public String getCompareFlag() {
    return compareFlag;
  }

  /**
   * エラー項目のフラグの取得。
   *
   * @return String エラー項目のフラグ
   */
  public String getErrorFlag() {
    return errorFlag;
  }

  /**
   * 申請状態の設定。
   *
   * @param applyCondition 申請状態
   */
  public void setApplyCondition(String applyCondition) {
    this.applyCondition = applyCondition;
  }

  /**
   * 処理区分の設定。
   *
   * @param manageKbn 処理区分
   */
  public void setManageKbn(String manageKbn) {
    this.manageKbn = manageKbn;
  }

  /**
   * セレクトコードの設定。
   *
   * @param prmSetDtlNo1 セレクトコード
   */
  public void setPrmSetDtlNo1(String prmSetDtlNo1) {
    this.prmSetDtlNo1 = prmSetDtlNo1;
  }

  /**
   * 分類コード名称の設定。
   *
   * @param goodsMGroup 分類コード名称
   */
  public void setGoodsMGroup(String goodsMGroup) {
    this.goodsMGroup = goodsMGroup;
  }

  /**
   * BLコード名称の設定。
   *
   * @param tbsPartsCode BLコード名称
   */
  public void setTbsPartsCode(String tbsPartsCode) {
    this.tbsPartsCode = tbsPartsCode;
  }

  /**
   * カーコード名称の設定。
   *
   * @param joinSourceMakerCode カーコード名称
   */
  public void setJoinSourceMakerCode(String joinSourceMakerCode) {
    this.joinSourceMakerCode = joinSourceMakerCode;
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
   * 種別コード名称の設定。
   *
   * @param prmSetDtlNo2 種別コード名称
   */
  public void setPrmSetDtlNo2(String prmSetDtlNo2) {
    this.prmSetDtlNo2 = prmSetDtlNo2;
  }

  /**
   * 表示順位の設定。
   *
   * @param joinDispOrder 表示順位
   */
  public void setJoinDispOrder(String joinDispOrder) {
    this.joinDispOrder = joinDispOrder;
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
   * QTYの設定。
   *
   * @param joinQty QTY
   */
  public void setJoinQty(String joinQty) {
    this.joinQty = joinQty;
  }

  /**
   * 規格/特記の設定。
   *
   * @param joinSpecialNote 規格/特記
   */
  public void setJoinSpecialNote(String joinSpecialNote) {
    this.joinSpecialNote = joinSpecialNote;
  }

  /**
   * 規格/特記（一般）の設定。
   *
   * @param primePartsSpecialNoteC 規格/特記（一般）
   */
  public void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC) {
    this.primePartsSpecialNoteC = primePartsSpecialNoteC;
  }

  /**
   * 削除依頼区分の設定。
   *
   * @param deleteFlg 削除依頼区分
   */
  public void setDeleteFlg(String deleteFlg) {
    this.deleteFlg = deleteFlg;
  }

  /**
   * 削除理由の設定。
   *
   * @param deleteReason 削除理由
   */
  public void setDeleteReason(String deleteReason) {
    this.deleteReason = deleteReason;
  }

  /**
   * 作成日時の設定。
   *
   * @param insDtTime 作成日時
   */
  public void setInsDtTime(String insDtTime) {
    this.insDtTime = insDtTime;
  }

  /**
   * 更新日時の設定。
   *
   * @param updDtTime 更新日時
   */
  public void setUpdDtTime(String updDtTime) {
    this.updDtTime = updDtTime;
  }

  /**
   * 適用日時の設定。
   *
   * @param startTime 適用日時
   */
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  /**
   * チェック区分の設定。
   *
   * @param checkFlg チェック区分
   */
  public void setCheckFlg(String checkFlg) {
    this.checkFlg = checkFlg;
  }

  /**
   * BL登録区分の設定。
   *
   * @param blEntryFlg BL登録区分
   */
  public void setBlEntryFlg(String blEntryFlg) {
    this.blEntryFlg = blEntryFlg;
  }

  /**
   * エラー区分の設定。
   *
   * @param errorFlg エラー区分
   */
  public void setErrorFlg(String errorFlg) {
    this.errorFlg = errorFlg;
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
   * インポート区分の設定。
   *
   * @param importKbn インポート区分
   */
  public void setImportKbn(int importKbn) {
    this.importKbn = importKbn;
  }

  /**
   * 隠匿域の設定。
   *
   * @param hiddenArea 隠匿域
   */
  public void setHiddenArea(Integer hiddenArea) {
    this.hiddenArea = hiddenArea;
  }

  /**
   * 履歴比較のフラグの設定。
   *
   * @param compareFlag 履歴比較のフラグ
   */
  public void setCompareFlag(String compareFlag) {
    this.compareFlag = compareFlag;
  }

  /**
   * エラー項目のフラグの設定。
   *
   * @param errorFlag エラー項目のフラグ
   */
  public void setErrorFlag(String errorFlag) {
    this.errorFlag = errorFlag;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(applyCondition).append(manageKbn).append(prmSetDtlNo1).append(goodsMGroup)
        .append(tbsPartsCode).append(joinSourceMakerCode).append(joinSourPartsNoWithH).append(prmSetDtlNo2)
        .append(joinDispOrder).append(joinDestPartsNo).append(joinQty).append(joinSpecialNote)
        .append(primePartsSpecialNoteC).append(deleteFlg).append(deleteReason).append(insDtTime).append(updDtTime)
        .append(startTime).append(checkFlg).append(blEntryFlg).append(errorFlg).append(errorDetail).append(importKbn)
        .append(hiddenArea).toHashCode();
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
    JoinGridDto other = (JoinGridDto) obj;
    return new EqualsBuilder().append(applyCondition, other.applyCondition).append(manageKbn, other.manageKbn)
        .append(prmSetDtlNo1, other.prmSetDtlNo1).append(goodsMGroup, other.goodsMGroup)
        .append(tbsPartsCode, other.tbsPartsCode).append(joinSourceMakerCode, other.joinSourceMakerCode)
        .append(joinSourPartsNoWithH, other.joinSourPartsNoWithH).append(prmSetDtlNo2, other.prmSetDtlNo2)
        .append(joinDispOrder, other.joinDispOrder).append(joinDestPartsNo, other.joinDestPartsNo)
        .append(joinQty, other.joinQty).append(joinSpecialNote, other.joinSpecialNote)
        .append(primePartsSpecialNoteC, other.primePartsSpecialNoteC).append(deleteFlg, other.deleteFlg)
        .append(deleteReason, other.deleteReason).append(insDtTime, other.insDtTime).append(updDtTime, other.updDtTime)
        .append(startTime, other.startTime).append(checkFlg, other.checkFlg).append(blEntryFlg, other.blEntryFlg)
        .append(errorFlg, other.errorFlg).append(errorDetail, other.errorDetail).append(importKbn, other.importKbn)
        .append(hiddenArea, other.hiddenArea).isEquals();
  }

}
