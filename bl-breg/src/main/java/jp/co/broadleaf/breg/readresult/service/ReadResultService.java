//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//(c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号       作成担当 : 羅　進博
// 作 成 日       2017/02/08   修正内容 : 取込完了：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.readresult.service;


/**
 * <pre>
 * 取込完了サービスインタフェース.
 * </pre>
 */
public interface ReadResultService {
  /**
   * <pre>
   * 商品の情報をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @param manageStatus Integer
   * @return goodsInfo Integer
   */
  int getGoodsInfo(int loginUserCd, int manageStatus);
  
  /**
   * <pre>
   * セットの情報をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @param manageStatus Integer
   * @return goodsInfo Integer
   */
  int getSetInfo(int loginUserCd, int manageStatus);
  
  /**
   * <pre>
   * 結合の情報をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @param manageStatus Integer
   * @return goodsInfo Integer
   */
  int getJoinInfo(int loginUserCd, int manageStatus);
}
