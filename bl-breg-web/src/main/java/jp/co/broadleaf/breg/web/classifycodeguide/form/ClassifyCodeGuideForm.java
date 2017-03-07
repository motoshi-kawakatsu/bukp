//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:                 作成担当:趙　雷雷
// 作成日:2017/02/07    修正内容:商品中分類コードガイド:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.web.classifycodeguide.form;

/**
 * <pre>
 * 
 * </pre>
 */
public class ClassifyCodeGuideForm {
  
  /**
   * 中分類コード
   */
  private String goodsRateGrpCode;
  
  /**
   * 中分類コード名
   */
  private String goodsRateGrpName;

  /**
   * <pre>
   * 中分類コードを取得する。
   * </pre>
   *
   * @return 中分類コード
   */
  public String getGoodsRateGrpCode() {
    return goodsRateGrpCode;
  }

  /**
   * <pre>
   * 中分類コードを設定する。
   * </pre>
   *
   * @param goodsRateGrpCode 中分類コード
   */
  public void setGoodsRateGrpCode(String goodsRateGrpCode) {
    this.goodsRateGrpCode = goodsRateGrpCode;
  }

  /**
   * <pre>
   * 中分類コード名を取得する。
   * </pre>
   *
   * @return 中分類コード名
   */
  public String getGoodsRateGrpName() {
    return goodsRateGrpName;
  }

  /**
   * <pre>
   * 中分類コード名を設定する。
   * </pre>
   *
   * @param goodsRateGrpName 中分類コード名
   */
  public void setGoodsRateGrpName(String goodsRateGrpName) {
    this.goodsRateGrpName = goodsRateGrpName;
  }

  
}
