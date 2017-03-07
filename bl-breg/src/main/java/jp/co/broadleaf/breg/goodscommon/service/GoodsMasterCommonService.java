//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : xuck
// 作 成 日       2017/02/13   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.goodscommon.service;

import jp.co.broadleaf.breg.common.entity.GoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;

import java.util.List;

/**
 * <pre>
 * 商品マスタ(common)サービスインタフェース.
 * </pre>
 */
public interface GoodsMasterCommonService {

  /**
   * <pre>
   * 商品マスタ(common)登録.
   * </pre>
   * 
   * @param goodsMasterList 商品マスタ(common)
   * @return 登録した商品マスタ(common)の件数
   */
  int insertGoodsInfoList(List<GoodsMasterCommon> goodsMasterList);

  /**
   * 商品マスタ(メーカー)更新処理.
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   */
  void updateGoodsInfo(GoodsMasterCommon goodsMaster);

  /**
   * <pre>
   * 商品マスタ(common)の検索.
   * </pre>
   * 
   * @param goodsMasterMaker 検索する項目（優良設定詳細コード１ など）の連係情報
   * @return 商品マスタ(common)
   */
  List<GoodsMasterCommon> searchGoodsMasterCommonList(GoodsMasterMaker goodsMasterMaker);
  
  /**
   * <pre>
   * 商品マスタ(common)削除.
   * </pre>
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   */
  void deleteGoods(GoodsMasterCommon goodsMaster);

}
