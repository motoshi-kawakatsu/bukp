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

import jp.co.broadleaf.breg.common.entity.GoodsMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterWorkMakerId;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterSearchModel;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterWorkMakerModel;

/**
 * <pre>
 * 商品マスタ(メーカー)サービスインタフェース.
 * </pre>
 */
public interface GoodsMasterWorkMakerService {
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
   * @param importKbn インポート区分
   * @return 商品マスタ(メーカー)情報
   */
  GoodsMasterWorkMakerModel searchGoodsInfoList(GoodsMasterSearchModel goodsMasterSearchModel, int order, Long skipRows,
                                                Long maxRows, Boolean isPage, Short importKbn);

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param importKbn インポート区分
   * @return 商品マスタ(メーカー)情報
   */
  GoodsMasterWorkMakerModel searchGoodsInfoAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd,
                                               Short importKbn);

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param goodsMasterId 商品マスタ(メーカー)ID
   * @return 商品マスタ(メーカー)情報
   */
  GoodsMasterWorkMaker searchGoodsById(GoodsMasterWorkMakerId goodsMasterId);

  /**
   * <pre>
   * 商品マスタ(メーカー)削除.
   * </pre>
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   */
  void logicaldeleteGoodsInfo(GoodsMasterWorkMaker goodsMaster);

  /**
   * <pre>
   * 商品マスタ(メーカー)削除.
   * </pre>
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   */
  void deleteGoodsInfo(GoodsMasterWorkMaker goodsMaster);

  /**
   * <pre>
   * 商品マスタ(メーカー)登録.
   * </pre>
   * 
   * @param goodsModel 商品マスタ(メーカー)
   * @return 登録した商品マスタ(メーカー)の件数
   */
  int insertGoodsInfoList(GoodsMasterWorkMakerModel goodsModel);

  /**
   * 商品マスタ(メーカー)更新処理.
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   */
  void updateGoodsInfo(GoodsMasterWorkMaker goodsMaster);
}
