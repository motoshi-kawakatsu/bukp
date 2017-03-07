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
package jp.co.broadleaf.breg.classifycodeguide.facade;

import jp.co.broadleaf.breg.classifycodeguide.facade.dto.ClassifyCodeGuideDto;

import java.util.List;

/**
 * 商品中分類コードガイドFacadeインタフェース.
 */
public interface ClassifyCodeGuideFacade {
  
  /**
   * 中分類コード検索結果.
   * 
   * @param logical 論理削除区分＝0:有効
   * @param makerCode メーカーコード
   * @param goodsRateGrpCode 中分類コード
   * @param goodsRateGrpName 中分類コード名
   * @return 中分類コード検索結果DtoList
   */
  List<ClassifyCodeGuideDto> searchPrimeByCode(int logical, int makerCode, String goodsRateGrpCode, String goodsRateGrpName);
}
