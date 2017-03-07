//****************************************************************************//
// システム                                    :優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                  作成担当 : 楊蕾蕾
// 作 成 日       2017/02/13   修正内容 : 申請 (新規品目):新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applynewcategory.service;

import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommon;
import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;

/**
 * <pre>
 * 申請 (新規品目)サービスインタフェース.
 * </pre>
 */
public interface ApplyNewCategoryService {
  /**
   * <pre>
   * 初期データを取得する.
   * </pre>
   * @param applyHeadMasterCommon データ
   * @param isApplyAgain ApplyStatus
   * @return applyNo 申請ID
   */
  ApplyHeadMasterCommon searchApplyHistoryInfo(ApplyHeadMasterCommon applyHeadMasterCommon,boolean isApplyAgain);
  /**
   * <pre>
   * 申請ヘッダマスタのApplyNOを取得する.
   * </pre>
   * 
   * @return applyNo 申請ID
   */
  int getApplyNo(); 
  
  /**
   * <pre>
   * 申請 (新規品目)を挿入する.
   * </pre>
   * 
   * @param applyHeadMasterCommon 申請ヘッダマスタのentity
   * @return 0:挿入成功　1:挿入失敗
   */
  int insertIntoApplyHistory(ApplyHeadMasterCommon applyHeadMasterCommon);
  
  /**
   * <pre>
   * MailAddressを取得する.
   * </pre>
   * 
   * @param makerCode makerCode
   * @return blMailAddress
   */
  CompanyInfoMasterCommon getBlMailAdd(int makerCode);
  
}
