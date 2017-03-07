//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 司　志超
// 作 成 日       2017/02/08     修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.totalinfo.model;

import java.io.Serializable;


/**
 *
 * 検索条件のDtoのクラス。
 */
public class SearchConditionModel implements Serializable {
  private static final long serialVersionUID = 1L;
  
  /**
   * 申請No
   */
  private String applyNo;
  
  /**
   * 対象区分
   */
  private int objKbn;
  
  /**
   * filterモード
   */
  private int filterMode;

  /**
   * BLコード
   */
  private String tbsPartsCode;
  
  /**
   * カーコード
   */
  private String partsMakerCd;
  
  /**
   * 優良品番
   */
  private String primePartsNoWithH;
  
  /**
   * 純正品番
   */
  private String joinSourPartsNoWithH;
  
  /**
   * 論理削除区分
   */
  private int logicalDelDiv;
  
  /**
   * ページ内行数
   */
  private int maxCount;
  
  /**
   * 表示用ページ
   */
  private int currentPage;

  /**
   * <pre>
   * 【objKbn】を取得する。
   * </pre>
   *
   * @return 【objKbn】
   */
  public int getObjKbn() {
    return objKbn;
  }

  /**
   * <pre>
   * 【objKbn】を設定する。
   * </pre>
   *
   * @param objKbn 【objKbn】
   */
  public void setObjKbn(int objKbn) {
    this.objKbn = objKbn;
  }

  /**
   * <pre>
   * 【filterMode】を取得する。
   * </pre>
   *
   * @return 【filterMode】
   */
  public int getFilterMode() {
    return filterMode;
  }

  /**
   * <pre>
   * 【filterMode】を設定する。
   * </pre>
   *
   * @param filterMode 【filterMode】
   */
  public void setFilterMode(int filterMode) {
    this.filterMode = filterMode;
  }

  /**
   * <pre>
   * 【tbsPartsCode】を取得する。
   * </pre>
   *
   * @return 【tbsPartsCode】
   */
  public String getTbsPartsCode() {
    return tbsPartsCode;
  }

  /**
   * <pre>
   * 【tbsPartsCode】を設定する。
   * </pre>
   *
   * @param tbsPartsCode 【tbsPartsCode】
   */
  public void setTbsPartsCode(String tbsPartsCode) {
    this.tbsPartsCode = tbsPartsCode;
  }

  /**
   * <pre>
   * 【partsMakerCd】を取得する。
   * </pre>
   *
   * @return 【partsMakerCd】
   */
  public String getPartsMakerCd() {
    return partsMakerCd;
  }

  /**
   * <pre>
   * 【partsMakerCd】を設定する。
   * </pre>
   *
   * @param partsMakerCd 【partsMakerCd】
   */
  public void setPartsMakerCd(String partsMakerCd) {
    this.partsMakerCd = partsMakerCd;
  }

  /**
   * <pre>
   * 【primePartsNoWithH】を取得する。
   * </pre>
   *
   * @return 【primePartsNoWithH】
   */
  public String getPrimePartsNoWithH() {
    return primePartsNoWithH;
  }

  /**
   * <pre>
   * 【primePartsNoWithH】を設定する。
   * </pre>
   *
   * @param primePartsNoWithH 【primePartsNoWithH】
   */
  public void setPrimePartsNoWithH(String primePartsNoWithH) {
    this.primePartsNoWithH = primePartsNoWithH;
  }

  /**
   * <pre>
   * 【joinSourPartsNoWithH】を取得する。
   * </pre>
   *
   * @return 【joinSourPartsNoWithH】
   */
  public String getJoinSourPartsNoWithH() {
    return joinSourPartsNoWithH;
  }

  /**
   * <pre>
   * 【joinSourPartsNoWithH】を設定する。
   * </pre>
   *
   * @param joinSourPartsNoWithH 【joinSourPartsNoWithH】
   */
  public void setJoinSourPartsNoWithH(String joinSourPartsNoWithH) {
    this.joinSourPartsNoWithH = joinSourPartsNoWithH;
  }

  /**
   * <pre>
   * 【logicalDelDiv】を取得する。
   * </pre>
   *
   * @return 【logicalDelDiv】
   */
  public int getLogicalDelDiv() {
    return logicalDelDiv;
  }

  /**
   * <pre>
   * 【logicalDelDiv】を設定する。
   * </pre>
   *
   * @param logicalDelDiv 【logicalDelDiv】
   */
  public void setLogicalDelDiv(int logicalDelDiv) {
    this.logicalDelDiv = logicalDelDiv;
  }

  /**
   * <pre>
   * 【maxCount】を取得する。
   * </pre>
   *
   * @return 【maxCount】
   */
  public int getMaxCount() {
    return maxCount;
  }

  /**
   * <pre>
   * 【maxCount】を設定する。
   * </pre>
   *
   * @param maxCount 【maxCount】
   */
  public void setMaxCount(int maxCount) {
    this.maxCount = maxCount;
  }

  /**
   * <pre>
   * 【currentPage】を取得する。
   * </pre>
   *
   * @return 【currentPage】
   */
  public int getCurrentPage() {
    return currentPage;
  }

  /**
   * <pre>
   * 【currentPage】を設定する。
   * </pre>
   *
   * @param currentPage 【currentPage】
   */
  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  /**
   * <pre>
   * 【applyNo】を取得する。
   * </pre>
   *
   * @return 【applyNo】
   */
  public String getApplyNo() {
    return applyNo;
  }

  /**
   * <pre>
   * 【applyNo】を設定する。
   * </pre>
   *
   * @param applyNo 【applyNo】
   */
  public void setApplyNo(String applyNo) {
    this.applyNo = applyNo;
  }

}
