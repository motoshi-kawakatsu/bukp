//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : lulei
// 作 成 日       2017/02/09   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.checklist.facade;

import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerInfoDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterInfoDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterInfoDto;

/**
 * <pre>
 * チェックリスト Facadeクラス.
 * </pre>
 */
public interface CheckListFacade {

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param count 現在位置
   * @param maxRows maxRows
   * @param makerCode makerCode
   * @return 商品マスタ(メーカー)Dto
   */
  GoodsMasterMakerInfoDto searchErrorGoodsList(Long count, Long maxRows, int makerCode);

  /**
   * <pre>
   * セットマスタ(メーカー)の取得.
   * </pre>
   * 
   * @param count 現在位置
   * @param maxRows maxRows
   * @param makerCode makerCode
   * @return セットマスタ(メーカー)Dto
   */
  SetMasterInfoDto searchSetErrorList(Long count, Long maxRows, int makerCode);

  /**
   * <pre>
   * 結合マスタ(メーカー)の全データ取得.
   * </pre>
   * 
   * @param count 現在位置
   * @param maxRows maxRows
   * @param makerCode makerCode
   * @return 結合マスタ(メーカー)Dto
   */
  JoinMasterInfoDto searchJoinErrorList(Long count, Long maxRows, int makerCode);

}
