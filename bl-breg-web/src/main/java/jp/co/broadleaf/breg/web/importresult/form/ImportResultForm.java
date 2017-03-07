//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
// (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号    作成担当 : 羅　進博
// 作 成 日       2017/02/08   修正内容 : ファイル選択：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.importresult.form;

/**
 * インポート結果 用のFormクラス
 */
public class ImportResultForm {

  /**
   * 「インポート」や「インポート（一括申請）」起動の場合：0:インポート（一括申請）；1:インポート。
   */
  private int importType;

  /**
   * 【importTypeMenu】を取得する。
   * 
   * @return 【importTypeMenu】
   */
  public int getImportTypeMenu() {
    return importType;
  }

  /**
   * 【importTypeMenu】を設定する。
   * 
   * @param importTypeMenu 【importTypeMenu】
   */
  public void setImportTypeMenu(int importTypeMenu) {
    this.importType = importTypeMenu;
  }

}
