//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:趙　命ラン
// 作成日:2017/02/13    修正内容:TopMenuService.java:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.topmenu.service;


import jp.co.broadleaf.breg.applyheadmastercommon.domain.model.ApplyHeadMasterCommonModel;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterMakerModel;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterModel;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterMakerModel;

/**
 * <pre>
 * メーニューサービスインタフェース
 * </pre>
 */
public interface TopMenuService {
  
  
  /**
   * <pre>
   * 商品マスタ件数(メーカー)取得.
   * </pre>
   * @param mode 
   * 
   * @return 商品マスタ件数(メーカー)情報
   */
  GoodsMasterMakerModel searchItemKensu(String mode);

  /**
   * <pre>
   * セットマスタ件数(メーカー)取得.
   * </pre>
   * @param mode 
   * 
   * @return セットマスタ件数(メーカー)情報
   */
  SetMasterMakerModel searchSetKensu(String mode);
  
  /**
   * <pre>
   * 結合件数(メーカー)取得.
   * </pre>
   * @param mode 
   * 
   * @return 結合マスタ件数(メーカー)情報
   */
  JoinMasterModel searchJoinKensu(String mode);
  
  /**
   * <pre>
   * 申請件数(メーカー)取得.
   * </pre>
   * 
   * @return 申請マスタ件数(メーカー)情報
   */
  ApplyHeadMasterCommonModel searchApplyKensu();
  
  /**
   * <pre>
   * メッセージ(メーカー)取得.
   * </pre>
   * 
   * @return メッセージ(メーカー)情報
   */
  String message();

  /**
   * <pre>
   * 本人の判断.
   * </pre>
   * 
   * @return 本人の判断
   */
  boolean getHonnin();
  
  /**
   * <pre>
   * 未申請件数有無の判断.
   * </pre>
   * 
   * @return 未申請件数有無の判断
   */
  boolean getMishinseiJoho();

  /**
   * <pre>
   * ユーザ名取得.
   * </pre>
   * @param loginId 
   * 
   * @return ユーザ名情報
   */
  String searchUserName(String loginId);
  
}
