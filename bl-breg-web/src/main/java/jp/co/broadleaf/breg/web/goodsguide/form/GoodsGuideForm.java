//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:                作成担当:趙　雷雷
// 作成日:2017/02/13        修正内容:商品ガイド:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.goodsguide.form;

/**
 * <pre>
 * 
 * </pre>
 */
public class GoodsGuideForm {
  
/**
 * 優良品番
 */
  private String primePartsNoWithH;
  
  /**
   * 品名(半角)
   */
  private String primePartsKanaNm;
  
  /**
   * 品名(全角)
   */
  private String primePartsName;

  /**
   * <pre>
   * 優良品番を取得する。
   * </pre>
   *
   * @return 優良品番
   */
  public String getPrimePartsNoWithH() {
    return primePartsNoWithH;
  }

  /**
   * <pre>
   * 優良品番を設定する。
   * </pre>
   *
   * @param primePartsNoWithH 優良品番
   */
  public void setPrimePartsNoWithH(String primePartsNoWithH) {
    this.primePartsNoWithH = primePartsNoWithH;
  }

  /**
   * <pre>
   * 品名(半角)を取得する。
   * </pre>
   *
   * @return 品名(半角)
   */
  public String getPrimePartsKanaNm() {
    return primePartsKanaNm;
  }

  /**
   * <pre>
   * 品名(半角)を設定する。
   * </pre>
   *
   * @param primePartsKanaNm 品名(半角)
   */
  public void setPrimePartsKanaNm(String primePartsKanaNm) {
    this.primePartsKanaNm = primePartsKanaNm;
  }

  /**
   * <pre>
   * 品名(全角)を取得する。
   * </pre>
   *
   * @return 品名(全角)
   */
  public String getPrimePartsName() {
    return primePartsName;
  }

  /**
   * <pre>
   * 品名(全角)を設定する。
   * </pre>
   *
   * @param primePartsName 品名(全角)
   */
  public void setPrimePartsName(String primePartsName) {
    this.primePartsName = primePartsName;
  }

  
}
