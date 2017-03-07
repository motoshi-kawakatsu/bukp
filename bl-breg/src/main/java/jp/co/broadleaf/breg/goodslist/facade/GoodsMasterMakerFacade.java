//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : wangnan
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.goodslist.facade;

import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsGridDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerInfoDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterSearchDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * </pre>
 */
public interface GoodsMasterMakerFacade {

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param goodsMasterSearchDto 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param order 順番の依頼
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param mode 画面mode
   * @return 商品マスタ(メーカー)Dto
   */
  GoodsMasterMakerInfoDto searchGoodsMasterInfoList(GoodsMasterSearchDto goodsMasterSearchDto, int order, Long skipRows,
                                                    Long maxRows, Boolean isPage, int mode);

  /**
   * <pre>
   * 商品選択モードの商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param goodsMasterMakerInfoDto 未編集の商品マスタリスト
   * @return 商品マスタ(メーカー)Dto
   */
  GoodsMasterMakerInfoDto searchGoodsMasterInfoList(GoodsMasterMakerInfoDto goodsMasterMakerInfoDto);

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param primePartsNoWithH primePartsNoWithH
   * @param makerCode 部品メーカーコード
   * @param prmSetDtlNo1 prmSetDtlNo1
   * @return 商品マスタ(メーカー)情報
   */
  GoodsMasterMakerInfoDto searchById(int primePartsNoWithH, int makerCode, String prmSetDtlNo1);

  /**
   * <pre>
   * チェックリスト画面の商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @return 商品マスタ(メーカー)情報
   */
  GoodsMasterMakerInfoDto searchGoodsList(String updAccountId, int partsMakerCd);

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param mode 画面mode
   * @return 商品マスタ(メーカー)Dto
   */
  GoodsMasterMakerInfoDto searchGoodsMasterInfoAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd, int mode);

  /**
   * <pre>
   * 商品マスタ(メーカー)の登録.
   * </pre>
   * 
   * @param goodsMasterInfoDto 商品マスタ(メーカー)Dto
   * @return 登録商品マスタ(メーカー)
   */
  int insertGoodsMasterInfoList(GoodsMasterMakerInfoDto goodsMasterInfoDto);

  /**
   * <pre>
   * 商品マスタ(メーカー)情報更新.
   * </pre>
   * 
   * @param goodsMasterDto 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void updateGoodsMaster(GoodsMasterMakerDto goodsMasterDto);

  /**
   * <pre>
   * 商品マスタ(メーカー)削除.
   * </pre>
   * 
   * @param goodsMasterDto 商品マスタ(メーカー)Dto
   */
  void logicaldeleteGoodsInfo(GoodsMasterMakerDto goodsMasterDto);

  /**
   * <pre>
   * 商品マスタ(メーカー)削除.
   * </pre>
   * 
   * @param goodsMasterDto 商品マスタ(メーカー)Dto
   */
  void deleteGoodsInfo(GoodsMasterMakerDto goodsMasterDto);

  /**
   * <pre>
   * BLコード名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return BLコード名称マップ
   */
  Map<String, String> getBlCodeNameMap(int makerCd);

  /**
   * <pre>
   * * セレクトコードマスト名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return BLコード名称マップ
   */
  Map<String, String> getSelectCodeNameMap(int makerCd);

  /**
   * <pre>
   * * 層別名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 層別
   */
  Map<String, String> getPartsNameMap(int makerCd);

  /**
   * <pre>
   * * 商品中分類コードマップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 商品中分類コード
   */
  Map<String, String> getClassifyCodeGuideMap(int makerCd);

  /**
   * 商品マスタ(メーカー)のチェック.
   * 
   * @param goodsGridDtoList 商品マスタ
   * @param makerCd メーカーコード
   * @return goodsMasterMakerDtoList
   */
  GoodsMasterMakerInfoDto checkImportList(List<GoodsGridDto> goodsGridDtoList, int makerCd);

  /**
   * <pre>
   * 商品ページ内行数設定の取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 商品ページ内行数設定
   */
  int searchGoodsListRows(int makerCd);

  /**
   * CheckDivの取得
   * 
   * @param goodsMasterDto 商品マスタ(メーカー)Dto
   * @return CheckDiv
   */
  Boolean getCheckDiv(GoodsMasterMakerDto goodsMasterDto);

  /**
   * SelectMakerの処理
   * 
   * @param goodsGridDto 商品マスタ
   * @param makerCd メーカーコード
   */
  void manegeSelectMaker(GoodsGridDto goodsGridDto, int makerCd);

  /**
   * <pre>
   * チェック選択に情報取得.
   * </pre>
   * 
   * @param partsMakerCd メーカーコード
   * @return チェック選択情報
   */
  int searchSelectBySelectKbn(int partsMakerCd);

  /**
   * メッセージを取得します.
   * 
   * @param messageMap messageMap
   */
  void getMessage(HashMap<String, String> messageMap);

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param partsMakerCd メーカーコード
   * @return 商品マスタ(メーカー)Dto
   */
  GoodsMasterMakerInfoDto searchGoodsMasterInfoBySelect(int partsMakerCd);

  /**
   * 画面初期ロード時、機能制御の判断
   * 
   * @return 「True」：機能制御なし、「False」：機能制御あり
   */
  Boolean isControl();

  /**
   * getPrice
   * 
   * @param price price
   * @return String
   */
  String getPrice(String price);

  // --------------- add by liangsd ------------------<<<
  /**
   * sava real-time information that user edit in the grid
   * 
   * @param goodsMasterMakerDtoList list
   * @param mode mode
   */
  void saveRealTime(List<GoodsMasterMakerDto> goodsMasterMakerDtoList, int mode);

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param loginId loginId
   * @return 商品マスタ(メーカー)Dto
   */
  List<GoodsMasterMakerDto> searchGoodsByloginId(String loginId);
  // --------------- add by liangsd ------------------>>>
}
