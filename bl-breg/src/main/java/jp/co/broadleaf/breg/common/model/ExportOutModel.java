//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.model;

import java.util.List;

/**
 * 帳票出力モードクラス
 */
public class ExportOutModel {

  /**
   * 帳票種類
   */
  private int reportKind;

  /**
   * ヘッダ
   */
  private String[] tableHeader;

  /**
   * カラム種類
   */
  private String[] colKind;

  /**
   * 明細データ
   */
  private List<String[]> detailList;

  /**
   * 帳票名
   */
  private String reportName;
  
  /**
   * 列幅
   */
  private float[] colWidth;

  /**
   * 帳票種類の取得
   *
   * @return int 帳票種類
   */
  public int getReportKind() {
      return reportKind;
  }

  /**
   * ヘッダの取得
   *
   * @return String[] ヘッダ
   */
  public String[] getTableHeader() {
      return tableHeader;
  }

  /**
   * カラム種類の取得
   *
   * @return String[] カラム種類
   */
  public String[] getColKind() {
      return colKind;
  }

  /**
   * 明細データの取得
   *
   * @return List<String[]> 明細データ
   */
  public List<String[]> getDetailList() {
      return detailList;
  }

  /**
   * 帳票名の取得
   *
   * @return String 帳票名
   */
  public String getReportName() {
      return reportName;
  }
  
  /**
   * 列幅の取得
   *
   * @return float[] 列幅
   */
  public float[] getColWidth() {
      return colWidth;
  }

  /**
   * 帳票種類の設定
   *
   * @param newReportKind 帳票種類
   */
  public void setReportKind(int newReportKind) {
      this.reportKind = newReportKind;
  }

  /**
   * ヘッダの設定
   *
   * @param newTableHeader ヘッダ
   */
  public void setTableHeader(String[] newTableHeader) {
      this.tableHeader = newTableHeader;
  }

  /**
   * カラム種類の設定
   *
   * @param newColKind カラム種類
   */
  public void setColKind(String[] newColKind) {
      this.colKind = newColKind;
  }

  /**
   * 明細データの設定
   *
   * @param newDetailList 明細データ
   */
  public void setDetailList(List<String[]> newDetailList) {
      this.detailList = newDetailList;
  }

  /**
   * 帳票名の設定
   *
   * @param newReportName 帳票名
   */
  public void setReportName(String newReportName) {
      this.reportName = newReportName;
  }
  
  /**
   * 列幅の設定
   *
   * @param newColWidth 列幅
   */
  public void setColWidth(float[] newColWidth) {
      this.colWidth = newColWidth;
  }
}

