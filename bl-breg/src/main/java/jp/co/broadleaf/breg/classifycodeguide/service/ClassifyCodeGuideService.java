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
package jp.co.broadleaf.breg.classifycodeguide.service;

import jp.co.broadleaf.breg.common.entity.GoodsRateMasterCommon;

import java.util.List;

/**
 * 中分類コード検索サビースインタフェース.
 */
public interface ClassifyCodeGuideService {
  
  /**
   * 中分類コード検索結果
   * 
   * @param logical 論理削除区分＝0:有効.
   * @param makerCode メーカーコード.
   * @param goodsCode 中分類コード
   * @param goodsName 中分類コード名
   * @return 中分類コード検索結果
   */
  List <GoodsRateMasterCommon> getPrimeByCode(int logical, int makerCode, String goodsCode, String goodsName);
  
  

}
