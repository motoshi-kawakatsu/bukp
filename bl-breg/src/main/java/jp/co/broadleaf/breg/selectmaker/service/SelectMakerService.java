//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : wangnan
// 作 成 日       2017/02/27   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.selectmaker.service;

import jp.co.broadleaf.breg.common.entity.SelectMaker;

import java.util.List;

/**
 * <pre>
 *  チェック選択サービスインタフェース.
 * </pre>
 */
public interface SelectMakerService {
  /**
   * <pre>
   * チェック選択に商品情報取得.
   * </pre>
   * 
   * @param gPrmSetDtlNo1 商品_優良設定詳細コード１
   * @param gPartsMakerCd 商品_部品メーカーコード
   * @param gPrimePartsNoWithH 商品_優良品番(－付き品番)
   * @return チェック選択情報
   */
  SelectMaker searchGoodsById(int gPrmSetDtlNo1, int gPartsMakerCd, String gPrimePartsNoWithH);

  /**
   * <pre>
   * チェック選択にセット情報取得.
   * </pre>
   * 
   * @param gPrmSetDtlNo1 優良設定詳細コード１
   * @param gPartsMakerCd 部品メーカーコード
   * @param setMainPartsNo セット親品番
   * @param setDispOrder セット表示順位
   * @return チェック選択情報
   */
  SelectMaker searchSetById(int gPrmSetDtlNo1, int gPartsMakerCd, String setMainPartsNo, int setDispOrder);

  /**
   * <pre>
   * チェック選択に結合情報取得.
   * </pre>
   * 
   * @param jPrmSetDtlNo1 結合_優良設定詳細コード１
   * @param jPartsMakerCd 結合_部品メーカーコード
   * @param jTbsPartsCode 結合_BLコード
   * @param jJoinSourceMakerCode 結合_結合元メーカーコード
   * @param jPrmSetDtlNo2 結合_優良設定詳細コード２
   * @param jJoinSourPartsNoWithH 結合_結合元品番(－付き品番)
   * @param jJoinDispOrder 結合_結合表示順位
   * @return チェック結合情報
   */
  SelectMaker searchJoinById(int jPrmSetDtlNo1, int jPartsMakerCd, int jTbsPartsCode, int jJoinSourceMakerCode,
                             int jPrmSetDtlNo2, String jJoinSourPartsNoWithH, int jJoinDispOrder);

  /**
   * <pre>
   * チェック選択に情報取得.
   * </pre>
   * 
   * @param selectKbn セットする区分
   * @param partsMakerCd ログインのメーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @return チェック選択情報
   */
  List<SelectMaker> searchBySelectKbn(int selectKbn, int partsMakerCd, int logicalDeleteCode);

  /**
   * <pre>
   * チェック選択に商品情報削除.
   * </pre>
   * 
   * @param gPrmSetDtlNo1 商品_優良設定詳細コード１
   * @param gPartsMakerCd 商品_部品メーカーコード
   * @param gPrimePartsNoWithH 商品_優良品番(－付き品番)
   */
  void deleteGoodsById(int gPrmSetDtlNo1, int gPartsMakerCd, String gPrimePartsNoWithH);

  /**
   * <pre>
   * チェック選択にセット情報削除.
   * </pre>
   * 
   * @param gPrmSetDtlNo1 優良設定詳細コード１
   * @param gPartsMakerCd 部品メーカーコード
   * @param setMainPartsNo セット親品番
   * @param setDispOrder セット表示順位
   */
  void deleteSetById(int gPrmSetDtlNo1, int gPartsMakerCd, String setMainPartsNo, int setDispOrder);

  /**
   * <pre>
   * チェック選択結合情報削除.
   * </pre>
   * 
   * @param jPrmSetDtlNo1 結合_優良設定詳細コード１
   * @param jPartsMakerCd 結合_部品メーカーコード
   * @param jTbsPartsCode 結合_BLコード
   * @param jJoinSourceMakerCode 結合_結合元メーカーコード
   * @param jPrmSetDtlNo2 結合_優良設定詳細コード２
   * @param jJoinSourPartsNoWithH 結合_結合元品番(－付き品番)
   * @param jJoinDispOrder 結合_結合表示順位
   */
  void deleteJoinById(int jPrmSetDtlNo1, int jPartsMakerCd, int jTbsPartsCode, int jJoinSourceMakerCode,
                      int jPrmSetDtlNo2, String jJoinSourPartsNoWithH, int jJoinDispOrder);

  /**
   * <pre>
   * チェック選択に商品情報削除.
   * </pre>
   * 
   * @param selectKbn セットする区分
   */
  void deleteGoodsBySelectKbn(int selectKbn, int partsMakerCd);

  /**
   * <pre>
   * チェック選択にセット情報削除.
   * </pre>
   * 
   * @param selectKbn セットする区分
   */
  void deleteSetBySelectKbn(int selectKbn, int partsMakerCd);

  /**
   * <pre>
   * チェック選択に結合情報削除.
   * </pre>
   * 
   * @param selectKbn セットする区分
   */
  void deleteJoinBySelectKbn(int selectKbn, int partsMakerCd);

  /**
   * <pre>
   * チェック選択に商品情報登録
   * </pre>
   * 
   * @param selectMaker チェック選択情報
   */
  void insertGoodsById(SelectMaker selectMaker);

  /**
   * <pre>
   * チェック選択に商品情報登録
   * </pre>
   * 
   * @param selectMaker チェック選択情報
   */
  void insertSetById(SelectMaker selectMaker);

  /**
   * <pre>
   * チェック選択に結合情報登録
   * </pre>
   * 
   * @param selectMaker チェック選択情報
   */
  void insertJoinById(SelectMaker selectMaker);
}
