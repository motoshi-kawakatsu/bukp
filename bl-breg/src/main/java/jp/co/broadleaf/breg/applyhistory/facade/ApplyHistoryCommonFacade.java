//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : tangyp
// 作 成 日       2017/02/10   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applyhistory.facade;

import jp.co.broadleaf.breg.applyhistory.facade.dto.ApplyHistoryCommonInfoDto;
import jp.co.broadleaf.breg.applyhistory.facade.dto.ApplyHistorySearchDto;

import java.util.Map;

/**
 * <pre>
 * 申請履歴一覧Facadeクラス.
 * </pre>
 */
public interface ApplyHistoryCommonFacade {

  /**
   * <pre>
   * BLコードマスタマップを取得.
   * </pre>
   * 
   * @param makerCd メーカコード
   * @return BLコードマスタマップト
   */
  Map<Integer,String> getBlCodeInfoMap(int makerCd);
  
  /**
   * <pre>
   * * メーカーマスト名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return メーカー名称マップ
   */
  Map<Integer, String> getCarmakerNameMap(int makerCd);
  /**
   * <pre>
   * 申請履歴一覧の取得.
   * </pre>
   * 
   * @param applyHistorySearchDto 検索する項目の連係情報
   * @return 申請履歴一覧Dto
   */
  ApplyHistoryCommonInfoDto searchApplyHistoryInfoList(ApplyHistorySearchDto applyHistorySearchDto);

  /**
   * <pre>
   * 申請履歴ページ内行数設定の取得.
   * </pre>
   * 
   * @param partsMakerCd メーカーコード
   * @return 申請履歴ページ内行数設定
   */
  int searchHistoryRows(int partsMakerCd);
}
