//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                               作成担当 : magy
// 作 成 日       2017/02/07   修正内容 : インポート結果:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.importresult.facade;

import jp.co.broadleaf.breg.importresult.facade.dto.ImportResultDto;

/**
 * インポート結果Facadeインタフェース.
 */
public interface ImportResultFacade {

  /**
   * インポート結果のチェック.
   * 
   * @param importResultDto インポート結果Dto
   * @return インポート結果Dto
   */
  ImportResultDto checkImportResult(ImportResultDto importResultDto);

  /**
   * インポート結果の取込.
   * 
   * @param importResultDto インポート結果Dto
   * @return インポート結果Dto
   */
  ImportResultDto torikomiResult(ImportResultDto importResultDto);

}
