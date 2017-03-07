//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:                作成担当:趙　雷雷
// 作成日:2017/02/13        修正内容:商品ガイド:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.goodsguide.facade;

import jp.co.broadleaf.breg.goodsguide.facade.dto.GoodsGuideDto;

import java.util.List;

/**
 * <pre>
 * 商品ガイドfacadeインタフェース.
 * </pre>
 */
public interface GoodsGuideFacade {

  /**
   * 優良品番検索結果
   * 
   * @param logical 論理削除区分＝0:有効
   * @param makerCode メーカーコード
   * @param primeCode 優良品番
   * @return 優良品番検索結果DtoList
   */
  List<GoodsGuideDto> searchPrimeByCode(int logical, int makerCode, String primeCode);
  
  /**
   * 純正品番検索結果
   * 
   * @param logical 論理削除区分＝0:有効
   * @param makerCode メーカーコード
   * @param pureGoodsCode 純正品番
   * @return 純正品番検索結果DtoList
   */
  List<GoodsGuideDto> searchPureGoodsByCode(int logical, int makerCode, String pureGoodsCode);
  
}
