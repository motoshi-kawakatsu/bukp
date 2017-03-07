//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : tangyp
// 作 成 日       2017/02/13   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applyhistory.service;

import jp.co.broadleaf.breg.applyhistory.model.ApplyHistoryCommonModel;
import jp.co.broadleaf.breg.applyhistory.model.ApplyHistorySearchModel;

import java.util.List;

/**
 * <pre>
 * 申請履歴情報サービスインタフェース.
 * </pre>
 */
public interface ApplyHistoryCommonService {
  
  /**
   * 全件数を取得します。
   * @param applyHistorySearchModel 申請履歴検索Model
   * 
   * @return 全件数
   */
  long getTotalCount(ApplyHistorySearchModel applyHistorySearchModel);
  /**
   * <pre>
   * 申請履歴情報取得.
   * </pre>
   * 
   * @param applyHistorySearchModel 申請履歴検索Model
   * 
   * @return 申請履歴情報
   */
  List<ApplyHistoryCommonModel> searchApplyHistoryCommonInfoList(ApplyHistorySearchModel applyHistorySearchModel);
  
  /**
   * <pre>
   * 申請中件数取得.
   * </pre>
   * 
   * @param applyHistorySearchModel 申請履歴検索Model
   * @return 申請中件数
   */
  int searchApplyCount(ApplyHistorySearchModel applyHistorySearchModel);
  
  /**
   * <pre>
   * 承認済件数取得.
   * </pre>
   * 
   * @param applyHistorySearchModel 申請履歴検索Model
   * @return 承認済件数
   */
  int searchApprovalCount(ApplyHistorySearchModel applyHistorySearchModel);
}
