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
package jp.co.broadleaf.breg.goodslist.service;

import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerRealTime;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterMakerModel;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterSearchModel;

import java.util.List;

/**
 * <pre>
 * 商品マスタ(メーカー)サービスインタフェース.
 * </pre>
 */
public interface GoodsMasterMakerService {
  /**
   * <pre>
   * チェックリスト画面の商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param count 現在位置
   * @param maxRows maxRows
   * @param makerCode makerCode
   * @return 商品マスタ(メーカー)情報
   */
  GoodsMasterMakerModel searchErrorGoodsList(Long count, Long maxRows, int makerCode);

  /**
   * <pre>
   * チェックリスト画面の商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @return 商品マスタ(メーカー)情報
   */
  GoodsMasterMakerModel searchGoodsList(String updAccountId, int partsMakerCd);

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param goodsMasterSearchModel 商品マスタ(メーカー)
   * @param order 順番の依頼
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param mode 画面mode
   * @return 商品マスタ(メーカー)情報
   */
  GoodsMasterMakerModel searchGoodsInfoList(GoodsMasterSearchModel goodsMasterSearchModel, int order, Long skipRows,
                                            Long maxRows, Boolean isPage, int mode);

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @return 商品マスタ(メーカー)情報
   */
  GoodsMasterMakerModel searchGoodsInfoAll();

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param mode 画面mode
   * @return 商品マスタ(メーカー)情報
   */
  GoodsMasterMakerModel searchGoodsInfoAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd, int mode);

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param goodsMasterId 商品マスタ(メーカー)ID
   * @return 商品マスタ(メーカー)情報
   */
  GoodsMasterMaker searchGoodsById(GoodsMasterMakerId goodsMasterId);

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param primePartsNoWithH 優良品番(－付き品番)
   * @return 商品マスタ(メーカー)情報
   */
  GoodsMasterMaker searchGoodsById(int prmSetDtlNo1, int partsMakerCd, String primePartsNoWithH);

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param partsMakerCd 部品メーカーコード
   * @param joinDestPartsNo 優良品番
   * @return 商品マスタ(メーカー)情報
   */
  List<GoodsMasterMaker> searchGoods(String partsMakerCd, String joinDestPartsNo);

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param importKbn インポート区分(メーカー)
   * @param applyCondition 申請状態(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @return 商品マスタ(メーカー)情報
   */
  List<GoodsMasterMaker> searchGoodsInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                             Integer partsMakerCd, Integer logicalDeleteCode);

  /**
   * <pre>
   * 商品マスタ(メーカー)削除.
   * </pre>
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   */
  void logicaldeleteGoodsInfo(GoodsMasterMaker goodsMaster);

  /**
   * <pre>
   * 商品マスタ(メーカー)削除.
   * </pre>
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   */
  void deleteGoodsInfo(GoodsMasterMaker goodsMaster);

  /**
   * <pre>
   * 商品マスタ(メーカー)登録.
   * </pre>
   * 
   * @param goodsModel 商品マスタ(メーカー)
   * @return 登録した商品マスタ(メーカー)の件数
   */
  int insertGoodsInfoList(GoodsMasterMakerModel goodsModel);

  /**
   * 商品マスタ(メーカー)更新処理.
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   */
  void updateGoodsInfo(GoodsMasterMaker goodsMaster);

  // --------------- add by liangsd ------------------>>>
  /**
   * <pre>
   * 商品マスタ(メーカー)削除.
   * </pre>
   * 
   * @param loginId loginId
   */
  void deleteKGoodsInfo(String loginId);

  /**
   * <pre>
   * 商品マスタ(メーカー)登録.
   * </pre>
   * 
   * @param goodsMasterList 商品マスタ(メーカー)
   */
  void insertKGoodsInfoList(List<GoodsMasterMakerRealTime> goodsMasterList);

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param loginId ID
   * @return 商品マスタ(メーカー)情報
   */
  List<GoodsMasterMakerRealTime> searchAllbyLoginId(String loginId);
  // --------------- add by liangsd ------------------<<<
}
