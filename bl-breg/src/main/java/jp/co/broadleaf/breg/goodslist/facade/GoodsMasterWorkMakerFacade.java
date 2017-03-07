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

import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerInfoDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterSearchDto;

/**
 * <pre>
 * </pre>
 */
public interface GoodsMasterWorkMakerFacade {

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
   * @param importKbn インポート区分
   * @return 商品マスタ(メーカー)Dto
   */
  GoodsMasterMakerInfoDto searchGoodsMasterInfoList(GoodsMasterSearchDto goodsMasterSearchDto, int order, Long skipRows,
                                                    Long maxRows, Boolean isPage, Short importKbn);

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param importKbn インポート区分
   * @return 商品マスタ(メーカー)Dto
   */
  GoodsMasterMakerInfoDto searchGoodsMasterInfoAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd,
                                                   Short importKbn);

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
}
