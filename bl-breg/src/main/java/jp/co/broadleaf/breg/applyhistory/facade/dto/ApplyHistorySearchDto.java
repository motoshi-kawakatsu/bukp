//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : tangyp
// 作 成 日       2017/02/13   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applyhistory.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 申請履歴一覧検索条件のDtoのクラス。
 */
public class ApplyHistorySearchDto {

  /**
   * 申請No
   */
  private String applyNo;
  /**
   * BL申請結果区分
   */
  private String blApplyResultsFlg;
  /**
   * 申請期間（FROM）
   */
  private String applyDateTimeFrom;
  /**
   * 申請期間（TO）
   */
  private String applyDateTimeTo;
  /**
   * BLコード
   */
  private String tbsPartsCode;
  /**
   * 優良品番
   */
  private String primePartsNo;
  /**
   * 申請種類
   */
  private String applyType;
  /**
   * カーコード
   */
  private String partsMakerCd;
  /**
   * 純正品番
   */
  private String joinSourPartsNoWithH;
  /**
   * 表示順
   */
  private String applySort;
  /**
   * 当前ページ
   */
  private int page;
  /**
   * 申請履歴ページ内行数
   */
  private int rows;
  /**
   * メーカコード
   */
  private int makerCd;
  /**
   * 申請Noの取得。
   *
   * @return String 申請No
   */
  public String getApplyNo() {
    return applyNo;
  }

  /**
   * 申請Noの設定。
   *
   * @param applyNo 申請No
   */
  public void setApplyNo(String applyNo) {
    this.applyNo = applyNo;
  }
  
  /**
   * BL申請結果区分の取得。
   *
   * @return String BL申請結果区分
   */
  public String getBlApplyResultsFlg() {
    return blApplyResultsFlg;
  }
  
  /**
   * BL申請結果区分の設定。
   *
   * @param blApplyResultsFlg BL申請結果区分
   */
  public void setBlApplyResultsFlg(String blApplyResultsFlg) {
    this.blApplyResultsFlg = blApplyResultsFlg;
  }
  
  /**
   * 申請期間（FROM）の取得。
   *
   * @return String 申請期間（FROM）
   */
  public String getApplyDateTimeFrom() {
    return applyDateTimeFrom;
  }

  /**
   * 申請期間（FROM）の設定。
   *
   * @param applyDateTimeFrom 申請期間（FROM）
   */
  public void setApplyDateTimeFrom(String applyDateTimeFrom) {
    this.applyDateTimeFrom = applyDateTimeFrom;
  }

  /**
   * 申請期間（TO）の取得。
   *
   * @return String BLコード
   */
  public String getApplyDateTimeTo() {
    return applyDateTimeTo;
  }

  /**
   * 申請期間（TO）の設定。
   *
   * @param applyDateTimeTo 申請期間（TO）
   */
  public void setApplyDateTimeTo(String applyDateTimeTo) {
    this.applyDateTimeTo = applyDateTimeTo;
  }

  /**
   * BLコードの取得。
   *
   * @return String BLコード
   */
  public String getTbsPartsCode() {
    return tbsPartsCode;
  }
  
  /**
   * BLコードの設定。
   *
   * @param tbsPartsCode BLコード
   */
  public void setTbsPartsCode(String tbsPartsCode) {
    this.tbsPartsCode = tbsPartsCode;
  }
  
  /**
   * 優良品番の取得。
   *
   * @return String 優良品番
   */
  public String getPrimePartsNo() {
    return primePartsNo;
  }
  
  /**
   * 優良品番の設定。
   *
   * @param primePartsNo 優良品番
   */
  public void setPrimePartsNo(String primePartsNo) {
    this.primePartsNo = primePartsNo;
  }
  
  /**
   * 申請種類の取得。
   *
   * @return String 申請種類
   */
  public String getApplyType() {
    return applyType;
  }
  
  /**
   * 申請種類の設定。
   *
   * @param applyType 申請種類
   */
  public void setApplyType(String applyType) {
    this.applyType = applyType;
  }
  
  /**
   * カーコードの取得。
   *
   * @return String カーコード
   */
  public String getPartsMakerCd() {
    return partsMakerCd;
  }
  
  /**
   * カーコードの設定。
   *
   * @param partsMakerCd カーコード
   */
  public void setPartsMakerCd(String partsMakerCd) {
    this.partsMakerCd = partsMakerCd;
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
   * 表示順の取得。
   *
   * @return String 表示順
   */
  public String getApplySort() {
    return applySort;
  }
  
  /**
   * 表示順の設定。
   *
   * @param applySort 表示順
   */
  public void setApplySort(String applySort) {
    this.applySort = applySort;
  }
  /**
   * 当前ページの取得。
   *
   * @return int 当前ページ
   */
  public int getPage() {
    return page;
  }
  
  /**
   * 当前ページの設定。
   *
   * @param page 当前ページ
   */
  public void setPage(int page) {
    this.page = page;
  }
  
  /**
   * 申請履歴ページ内行数の取得。
   *
   * @return int 申請履歴ページ内行数
   */
  public int getRows() {
    return rows;
  }
  
  /**
   * 申請履歴ページ内行数の設定。
   *
   * @param rows 申請履歴ページ内行数
   */
  public void setRows(int rows) {
    this.rows = rows;
  }
  /**
   * メーカコードの取得。
   *
   * @return int メーカコード
   */
  public int getMakerCd() {
    return makerCd;
  }
  
  /**
   * メーカコードの設定。
   *
   * @param makerCd メーカコード
   */
  public void setMakerCd(int makerCd) {
    this.makerCd = makerCd;
  }
  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(applyNo).append(blApplyResultsFlg).append(applyDateTimeFrom)
        .append(applyDateTimeTo).append(tbsPartsCode).append(primePartsNo).append(applyType).append(partsMakerCd)
        .append(joinSourPartsNoWithH).append(applySort).append(page).append(rows).append(makerCd)
        .toHashCode();
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
    ApplyHistorySearchDto other = (ApplyHistorySearchDto) obj;
    return new EqualsBuilder().append(applyNo, other.applyNo)
        .append(blApplyResultsFlg, other.blApplyResultsFlg).append(applyDateTimeFrom, other.applyDateTimeFrom)
        .append(applyDateTimeTo, other.applyDateTimeTo)
        .append(tbsPartsCode, other.tbsPartsCode).append(primePartsNo, other.primePartsNo)
        .append(applyType, other.applyType).append(partsMakerCd, other.partsMakerCd)
        .append(joinSourPartsNoWithH, other.joinSourPartsNoWithH)
        .append(applySort, other.applySort).append(page, other.page).append(rows, other.rows)
        .append(makerCd, other.makerCd).isEquals();
  }
}
