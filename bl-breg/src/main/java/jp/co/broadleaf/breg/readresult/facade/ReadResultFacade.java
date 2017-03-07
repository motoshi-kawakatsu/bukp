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
package jp.co.broadleaf.breg.readresult.facade;
/**
 * <pre>
 * 取込完了Facadeインタフェース.
 * </pre>
 */
public interface ReadResultFacade {
  /**
   * <pre>
   * 商品の情報をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @return goodsInfo Integer[]
   */
   int[] getGoodsInfo(int loginUserCd);
   
   /**
    * <pre>
    * セットの情報をゲットする。
    * </pre>
    *
    * @param loginUserCd Integer
    * @return setInfo Integer[]
    */
   int[] getSetInfo(int loginUserCd);
   
   /**
    * <pre>
    * 結合の情報をゲットする。
    * </pre>
    *
    * @param loginUserCd Integer
    * @return joinInfo Integer[]
    */
   int[] getJoinInfo(int loginUserCd);
}
