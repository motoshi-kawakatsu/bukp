//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : tangyp
// 作 成 日       2017/02/15   修正内容 : 申請履歴:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.applyhistory.form;

import java.io.Serializable;

/**
 * <pre>
 * 申請履歴一覧Formクラス.
 * </pre>
 */
public class ApplyHistorySearchForm implements Serializable {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -4602559845843720667L;

	  /**
       * 起動モード
       */
      private int mode;
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
       * メーカコード
       */
      private int makerCd;
      /**
       * 起動モード(申請(新規品目))
       */
      private int modeForNewItem;

      /**
       * 起動モードの取得。
       *
       * @return int 起動モード
       */
      public int getMode() {
        return mode;
      }

      /**
       * 起動モードの設定。
       *
       * @param mode 起動モード
       */
      public void setMode(int mode) {
        this.mode = mode;
      }
      
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
       * 起動モード(申請(新規品目))の取得。
       *
       * @return int 起動モード
       */
      public int getModeForNewItem() {
        return modeForNewItem;
      }

      /**
       * 起動モード(申請(新規品目))の設定。
       *
       * @param modeForNewItem 起動モード
       */
      public void setModeForNewItem(int modeForNewItem) {
        this.modeForNewItem = modeForNewItem;
      }
}
