//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:趙　命ラン
// 作成日:2017/02/13    修正内容:TopMenuFacade.java:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.topmenu.facade;

/**
 * <pre>
 * トップメニューFacadeインタフェース
 * </pre>
 */
public interface TopMenuFacade {
  
  /**
   * <pre>
   * 商品件数(メーカー)の取得.
   * </pre>
   * 
   * @return 商品件数
   */
  int shohinnKensuJhoho ();
  
  /**
   * <pre>
   * セット件数(メーカー)の取得.
   * </pre>
   * 
   * @return セット件数
   */
  int setKensuJhoho ();
  
  /**
   * <pre>
   * 結合件数(メーカー)の取得.
   * </pre>
   * 
   * @return 結合件数
   */
  int joinKensuJhoho ();
  
  /**
   * <pre>
   * メッセージ(メーカー)の取得.
   * </pre>
   * 
   * @return メッセージ
   */
  String messageInfo();
  
  /**
   * <pre>
   * 本人の判断(メーカー).
   * </pre>
   * 
   * @return 本人の判断結果
   */
  boolean honNin();
  
  /**
   * <pre>
   * 未申請件数の判断(メーカー).
   * </pre>
   * 
   * @return 未申請件数の判断結果
   */
  boolean miShinsei();
  
  /**
   * <pre>
   * 申請件数の取得.
   * </pre>
   * 
   * @return 申請件数
   */
  int applyKensuJhoho();
  
  /**
   * <pre>
   * messageOneの取得.
   * </pre>
   * 
   * @return messageOne　
   */
  String getMessageOne();
}
