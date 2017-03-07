//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 湯凱
// 作 成 日       2017/03/03   修正内容 : 結合詳細：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.service;

import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommon;

import java.util.List;

/**
 * <pre>
 * 純正商品マスタのinterfaceクラス
 * </pre>
 */
public interface PuregoodsMasterCommonService {

  /**
   * 純正部品情報を取得
   * 
   * @param partsMakerCd 部品メーカーコード
   * @param joinSourPartsNo 結合元品番(－付き品番)
   * @return 純正部品情報
   */
  List<PuregoodsMasterCommon> getPureGoodsInfo(int partsMakerCd,String joinSourPartsNo);
  
  /**
   * 純正部品情報を取得
   * 
   * @param partsMakerCd 部品メーカーコード
   * @return 純正部品情報
   */
  List<PuregoodsMasterCommon> getPureGoodsMaster(int partsMakerCd);
}
