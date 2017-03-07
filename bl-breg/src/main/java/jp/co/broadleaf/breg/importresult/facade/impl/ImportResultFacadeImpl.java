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
package jp.co.broadleaf.breg.importresult.facade.impl;

import jp.co.broadleaf.breg.importresult.facade.ImportResultFacade;
import jp.co.broadleaf.breg.importresult.facade.dto.ImportResultDto;
import jp.co.broadleaf.breg.importresult.model.ImportResultModel;
import jp.co.broadleaf.breg.importresult.service.ImportResultService;
import jp.co.broadleaf.common.util.BroadleafUtils;

import javax.annotation.Resource;

/**
 * インポート結果Facade.
 */
public class ImportResultFacadeImpl implements ImportResultFacade {

  /**
   * インポート結果のチェック.
   * 
   * @param importResultDto インポート結果Dto
   * @return インポート結果Dto
   */
  @Override
  public ImportResultDto checkImportResult(ImportResultDto importResultDto) {

    ImportResultModel importResultModel = importResultService.searchAll(importResultDto.getImportType(),
        importResultDto.getMakerCode(), importResultDto.getLoginId(), importResultDto.getImportKbnEnum());
    if (importResultDto.getImportType() == 0) {
      importResultModel = importResultService.checkImportList(importResultModel);
    }

    if (!BroadleafUtils.isEmpty(importResultModel.getItemWorkList())) {
      importResultDto.setGoodsCount(importResultModel.getItemWorkList().size());
    }
    if (!BroadleafUtils.isEmpty(importResultModel.getSetMasterWorkList())) {
      importResultDto.setSetCount(importResultModel.getSetMasterWorkList().size());
    }
    if (!BroadleafUtils.isEmpty(importResultModel.getJoinMasterWorkList())) {
      importResultDto.setJoinCount(importResultModel.getJoinMasterWorkList().size());
    }

    importResultDto.setGoodsErrorCount(importResultModel.getGoodsErrorCount());
    importResultDto.setSetErrorCount(importResultModel.getSetErrorCount());
    importResultDto.setJoinErrorCount(importResultModel.getJoinErrorCount());

    return importResultDto;
  }

  /**
   * インポート結果の取込.
   * 
   * @param importResultDto インポート結果Dto
   * @return インポート結果Dto
   */
  @Override
  public ImportResultDto torikomiResult(ImportResultDto importResultDto) {
    ImportResultModel importResultModel = importResultService.searchAll(importResultDto.getImportType(),
        importResultDto.getMakerCode(), importResultDto.getLoginId(), importResultDto.getImportKbnEnum());
    importResultModel = importResultService.updateHonDb(importResultDto.getMakerCode(), importResultDto.getLoginId(),
        importResultDto.getGoodsImportType(), importResultDto.getSetImportType(), importResultDto.getJoinImportType(),
        importResultModel, importResultDto.getImportKbnEnum());
    importResultDto.setGoodsInfo(importResultModel.getGoodsInfo());
    importResultDto.setSetInfo(importResultModel.getSetInfo());
    importResultDto.setJoinInfo(importResultModel.getJoinInfo());

    return importResultDto;
  }

  /** インポート結果サビース **/

  private ImportResultService importResultService;

  /**
   * 【importResultService】を設定する。
   * 
   * @param importResultService 【importResultService】
   */
  @Resource
  public void setImportResultService(ImportResultService importResultService) {
    this.importResultService = importResultService;
  }

}
