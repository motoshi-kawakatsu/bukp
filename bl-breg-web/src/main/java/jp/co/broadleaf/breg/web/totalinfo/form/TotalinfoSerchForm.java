package jp.co.broadleaf.breg.web.totalinfo.form;

/**
 * <pre>
 * 累積情報Formクラス.
 * </pre>
 */
public class TotalinfoSerchForm {

  /**
   * filterモード
   */
  private int filterMode;

  /**
   * 表示用ページ
   */
  private int currentPage;
  
  /**
   * 対象区分
   */
  private int objKbn;
  
  /**
   * BLコード
   */
  private String tbsPartsCode;
  /**
   * 優良品番
   */
  private String primePartsNo;
  /**
   * カーコード
   */
  private String partsMakerCd;
  /**
   * 純正品番
   */
  private String joinSourPartsNoWithH;

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
}
