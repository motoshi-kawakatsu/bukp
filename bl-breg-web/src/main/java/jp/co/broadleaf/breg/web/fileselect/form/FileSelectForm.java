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
package jp.co.broadleaf.breg.web.fileselect.form;


/**
 * <pre>
 * ファイル選択 用のFormクラス
 * </pre>
 */
public class FileSelectForm {
  
  /**
   * 商品 インポート方法
   */
  private String goodsType;
  
  /**
   * セット インポート方法
   */
  private String setType;
  
  /**
   * 結合 インポート方法
   */
  private String joinType;
  
  /**
   * 「インポート」や「インポート（一括申請）」起動の場合：0:インポート（一括申請）；1:インポート。
   */
  private String importTypeMenu;
  
  /**
   * <pre>
   * 【importTypeMenu】を取得する。
   * </pre>
   *
   * @return 【importTypeMenu】
   */
  public String getImportTypeMenu() {
    return importTypeMenu;
  }

  /**
   * <pre>
   * 【importTypeMenu】を設定する。
   * </pre>
   *
   * @param importTypeMenu 【importTypeMenu】
   */
  public void setImportTypeMenu(String importTypeMenu) {
    this.importTypeMenu = importTypeMenu;
  }

  /**
   * <pre>
   * 【goodsType】を取得する。
   * </pre>
   *
   * @return 【goodsType】
   */
  public String getGoodsType() {
    return goodsType;
  }

  /**
   * <pre>
   * 【goodsType】を設定する。
   * </pre>
   *
   * @param goodsType 【goodsType】
   */
  public void setGoodsType(String goodsType) {
    this.goodsType = goodsType;
  }

  /**
   * <pre>
   * 【setType】を取得する。
   * </pre>
   *
   * @return 【setType】
   */
  public String getSetType() {
    return setType;
  }

  /**
   * <pre>
   * 【setType】を設定する。
   * </pre>
   *
   * @param setType 【setType】
   */
  public void setSetType(String setType) {
    this.setType = setType;
  }

  /**
   * <pre>
   * 【joinType】を取得する。
   * </pre>
   *
   * @return 【joinType】
   */
  public String getJoinType() {
    return joinType;
  }

  /**
   * <pre>
   * 【joinType】を設定する。
   * </pre>
   *
   * @param joinType 【joinType】
   */
  public void setJoinType(String joinType) {
    this.joinType = joinType;
  }

  
}
