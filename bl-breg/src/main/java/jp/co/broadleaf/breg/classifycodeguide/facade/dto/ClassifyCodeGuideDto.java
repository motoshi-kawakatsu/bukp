//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:                 作成担当:趙　雷雷
// 作成日:2017/02/12         修正内容:商品中分類コードガイド:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.classifycodeguide.facade.dto;

/**
 * <pre>
 * 
 * </pre>
 */
public class ClassifyCodeGuideDto {
  
  /**
   * 中分類コード
   */
  private int goodsRateGrpCode;
  
  /**
   * 中分類コード名
   */
  private String goodsRateGrpName;
  
  /**
   * 検索結果のmessage
   */
  private String message; 
  
  /**
   * <pre>
   * 中分類コードを取得する。
   * </pre>
   *
   * @return 中分類コード
   */
  public int getGoodsRateGrpCode() {
    return goodsRateGrpCode;
  }

  /**
   * <pre>
   * 中分類コードを設定する。
   * </pre>
   *
   * @param goodsRateGrpCode 中分類コード
   */
  public void setGoodsRateGrpCode(int goodsRateGrpCode) {
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

  /**
   * <pre>
   * 検索結果のmessageを取得する。
   * </pre>
   *
   * @return 検索結果のmessage
   */
  public String getMessage() {
    return message;
  }

  /**
   * <pre>
   * 検索結果のmessageを設定する。
   * </pre>
   *
   * @param message 検索結果のmessage
   */
  public void setMessage(String message) {
    this.message = message;
  }

  
  
}
