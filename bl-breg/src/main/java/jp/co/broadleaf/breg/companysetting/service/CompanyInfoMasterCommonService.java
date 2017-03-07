//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 湯凱
// 作 成 日       2017/02/13   修正内容 : 会社情報：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.companysetting.service;

import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;

/**
 * <pre>
 * 会社情報のInterfaceクラス
 * </pre>
 */
public interface CompanyInfoMasterCommonService {

  /**
   * 会社情報を取る
   * 
   * @param makerCd メーカーコード
   * @return 会社情報
   */
  CompanyInfoMasterCommon getCompanyInfo(int makerCd);

  /**
   * 会社情報を更新
   * 
   * @param companyInfo 会社情報
   * @return 0:更新成功 1:更新失敗
   */
  int updateCompanyInfo(CompanyInfoMasterCommon companyInfo);
  
}
