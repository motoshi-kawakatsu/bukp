//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                    作成担当 : xuck
// 作 成 日       2017/02/13   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applyheadmastercommon.service;

import jp.co.broadleaf.breg.applyheadmastercommon.domain.model.ApplyHeadMasterCommonModel;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommon;

import java.util.List;

/**
 * <pre>
 * </pre>
 */
public interface ApplyHeadMasterCommonService {

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
   * 申請ヘッダマスタ登録.
   * </pre>
   * 
   * @param applyHeadMasterCommonList 申請ヘッダマスタ
   * @throws Throwable Throwable
   * @return 登録した申請ヘッダマスタの件数
   */
  int insertApplyHeadMasterCommon(List<ApplyHeadMasterCommon> applyHeadMasterCommonList) throws Throwable;

}
