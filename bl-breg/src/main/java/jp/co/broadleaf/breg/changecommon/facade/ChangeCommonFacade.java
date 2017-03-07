//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:陳　雪涛
// 作成日:2017/02/16    修正内容:検索・置換:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.changecommon.facade;

import jp.co.broadleaf.breg.classifycodeguide.facade.dto.ClassifyCodeGuideDto;

/**
 * <pre>
 * 検索置換facade.
 * </pre>
 */
public interface ChangeCommonFacade {

  /**
   * 画面用メッセージを取得する
   * 
   * @param messageId メッセージId
   * @param messageParameters メッセージパラメーター
   * @return メッセージ内容
   */
  String getMessage(String messageId, String... messageParameters);

  /**
   * 中分類コード検索結果
   * 
   * @param logical 論理削除区分＝0:有効.
   * @param makerCode メーカーコード.
   * @param goodsRateGrpCode 中分類コード
   * @param goodsRateGrpName 中分類コード名
   * @return 中分類コード検索結果Dto
   */
  ClassifyCodeGuideDto searchPrimeByCode(int logical, int makerCode, String goodsRateGrpCode, String goodsRateGrpName);
}
