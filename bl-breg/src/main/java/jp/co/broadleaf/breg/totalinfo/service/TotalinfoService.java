//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 司　志超
// 作 成 日       2017/02/08     修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.totalinfo.service;

import jp.co.broadleaf.breg.applyheadmastercommon.domain.model.ApplyHeadMasterCommonModel;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterCommonModel;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterCommonModel;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterCommonModel;

/**
 * <pre>
 * マスタサービス
 * </pre>
 */
public interface TotalinfoService {
  
  /**
   * <pre>
   * 申請ヘッダマスタ取得.
   * </pre>
   * 
   * @return 申請ヘッダマスタ情報
   */
  ApplyHeadMasterCommonModel searchApplyHeadMasterCommonList();
  /**
   * <pre>
   * 商品マスタ取得.
   * </pre>
   * @param logicalDelDiv 論理削除区分
   * @param filterMode filterモード
   * @param tbsPartsCode BLコード
   * @param partsMakerCd カーコード
   * @param primePartsNoWithH 優良品番
   * @return 商品マスタModel
   */
  GoodsMasterCommonModel searchItemMasterList(int logicalDelDiv,int filterMode,
             String tbsPartsCode,String partsMakerCd,String primePartsNoWithH);

  /**
   * <pre>
   * セットマスタ取得.
   * </pre>
   * @param logicalDelDiv 論理削除区分
   * @param filterMode filterモード
   * @return セットマスタModel
   */
  SetMasterCommonModel searchSetMasterInfoList(int logicalDelDiv,int filterMode);

  /**
   * <pre>
   * 結合マスタ取得.
   * </pre>
   * @param logicalDelDiv 論理削除区分
   * @param filterMode filterモード
   * @return 結合マスタModel
   */
  JoinMasterCommonModel searchJoinMasterInfoList(int logicalDelDiv,int filterMode);

}
