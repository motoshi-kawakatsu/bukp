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
package jp.co.broadleaf.breg.goodsguide.service;

import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommon;

import java.util.List;

/**
 * <pre>
 * </pre>
 */
public interface GoodsGuideService {

  /**
   * 優良品番検索結果
   * 
   * @param logical 論理削除区分＝0:有効
   * @param makerCode メーカーコード
   * @param primeCode 優良品番
   * @return 優良品番検索結果
   */
  List<GoodsMasterMaker> getPrimeByCode(int logical, int makerCode, String primeCode);

  /**
   * 純正品番検索結果
   * 
   * @param logical 論理削除区分＝0:有効
   * @param makerCode メーカーコード
   * @param pureGoodsCode 純正品番
   * @return 純正品番検索結果
   */
  List<PuregoodsMasterCommon> getPureGoodsByCode(int logical, int makerCode, String pureGoodsCode);

  /**
   * 優良品番検索結果
   * 
   * @param logical 論理削除区分＝0:有効
   * @param makerCode メーカーコード
   * @param primeCode 優良品番
   * @return 優良品番検索結果
   */
  List<GoodsMasterMaker> getPrimeByCodeAll(int logical, int makerCode, String primeCode);

  /**
   * 純正品番検索結果
   * 
   * @param primePartsNoWithH 結合元品番
   * @param joinSourceMakerCode 結合元メーカーコード
   * @param partsMakerCd 部品メーカーコード
   * @param prmSetDtlNo1 純正設定詳細コード１
   * @return 純正品番検索結果
   */
  List<PuregoodsMasterCommon> getPuregoodsMaster(String primePartsNoWithH, Integer joinSourceMakerCode,
                                                 int partsMakerCd, int prmSetDtlNo1);
}
