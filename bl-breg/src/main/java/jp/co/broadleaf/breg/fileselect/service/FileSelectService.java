//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//(c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号       作成担当 : 羅　進博
// 作 成 日       2017/02/08   修正内容 : ファイル選択：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.fileselect.service;

import jp.co.broadleaf.breg.common.entity.GoodsMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMaker;

import java.util.List;

/**
 * <pre>
 * ファイル選択サービスインタフェース.
 * </pre>
 */
public interface FileSelectService {

  /**
   * <pre>
   * loginUserCdについて、商品データを削除する。
   * </pre>
   *
   * @param loginUserCd Integer
   */
  void deleteGoodsInfoAll(int loginUserCd);
  /**
   * <pre>
   * loginUserCdについて、セットデータを削除する。
   * </pre>
   *
   * @param loginUserCd Integer
   */
  void deleteSetInfoAll(int loginUserCd);
  /**
   * <pre>
   * loginUserCdについて、結合データを削除する。
   * </pre>
   *
   * @param loginUserCd Integer
   */
  void deleteJoinInfoAll(int loginUserCd);
  /**
   * <pre>
   * 商品の情報はデータベースに挿入する。
   * </pre>
   *
   * @param goodsList List
   * @return Integer
   */
  int insertGoodsInfoList(List<GoodsMasterWorkMaker> goodsList);
  /**
   * <pre>
   * セットの情報はデータベースに挿入する。
   * </pre>
   *
   * @param setList List
   * @return Integer
   */
  int insertSetInfoList(List<SetMasterWorkMaker> setList);
  /**
   * <pre>
   * 結合の情報はデータベースに挿入する。
   * </pre>
   *
   * @param joinList List
   * @return Integer
   */
  int insertJoinInfoList(List<JoinMasterWorkMaker> joinList);
  /**
   * <pre>
   * インポート方法をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @return int[] 
   */
  int[] getImportType(int loginUserCd);
  
  /**
   * <pre>
   * 商品ファイルの情報をチェックする(エラー)。
   * </pre>
   *
   * @param fileData List
   * @return list List
   */
  List<String> goodsCheck(List<String[]> fileData);
  
  /**
   * <pre>
   * セットファイルの情報をチェックする(エラー)。
   * </pre>
   *
   * @param fileData List
   * @return list List
   */
  List<String> setCheck(List<String[]> fileData);
  
  /**
   * <pre>
   * 結合ファイルの情報をチェックする(エラー)。
   * </pre>
   *
   * @param fileData List
   * @return list List
   */
  List<String> joinCheck(List<String[]> fileData);
  
  /**
   * <pre>
   * 商品の処理区分をゲットする。
   * </pre>
   *
   * @param goodsMasterWorkMaker GoodsMasterWorkMaker
   * @return short
   */
  short getGoodsManage(GoodsMasterWorkMaker goodsMasterWorkMaker);
  
  /**
   * <pre>
   * セットの処理区分をゲットする。
   * </pre>
   *
   * @param setMasterWorkMaker SetMasterWorkMaker
   * @return setManage　short
   */
  short getSetManage(SetMasterWorkMaker setMasterWorkMaker);
  
  /**
   * <pre>
   * 結合の処理区分をゲットする。
   * </pre>
   *
   * @param joinMasterWorkMaker JoinMasterWorkMaker
   * @return joinManage　short
   */
  short getJoinManage(JoinMasterWorkMaker joinMasterWorkMaker);
  
  /**
   * <pre>
   * 既存BL承認済みの商品を取得する。
   * </pre>
   *
   * @param goodsMasterWorkMakerList List
   * @param loginUserCd int
   * @return int[0] 既存BL承認済みの商品の件数
   *                 int[1] TSVファイルに存在するBL承認済み商品件数
   *                 int[2] 既存BL承認済みの商品の件数-TSVファイルに存在するBL承認済み商品件数
   */
  int[] getConsentGoodsNum(int loginUserCd, List<GoodsMasterWorkMaker> goodsMasterWorkMakerList);
  
  /**
   * <pre>
   * 既存BL承認済みのセットを取得する。
   * </pre>
   *
   * @param setMasterWorkMakerList List
   * @param loginUserCd int
   * @return int[0] 既存BL承認済みのセットの件数
   *                 int[1] TSVファイルに存在するBL承認済みセット件数
   *                 int[2] 既存BL承認済みの商品の件数-TSVファイルに存在するBL承認済みセット件数
   */
  int[] getConsentSetNum(int loginUserCd, List<SetMasterWorkMaker> setMasterWorkMakerList);
  
  /**
   * <pre>
   * 既存BL承認済みの結合を取得する。
   * </pre>
   *
   * @param joinMasterWorkMakerList List
   * @param loginUserCd int
   * @return int[0] 既存BL承認済みの結合の件数
   *                 int[1] TSVファイルに存在するBL承認済み結合件数
   *                 int[2] 既存BL承認済みの商品の件数-TSVファイルに存在するBL承認済み結合件数
   */
  int[] getConsentJoinNum(int loginUserCd, List<JoinMasterWorkMaker> joinMasterWorkMakerList);  
}
