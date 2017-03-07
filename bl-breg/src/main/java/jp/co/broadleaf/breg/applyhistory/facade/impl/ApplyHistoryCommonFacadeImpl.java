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
package jp.co.broadleaf.breg.applyhistory.facade.impl;

import jp.co.broadleaf.breg.applyhistory.facade.ApplyHistoryCommonFacade;
import jp.co.broadleaf.breg.applyhistory.facade.dto.ApplyHistoryCommonDto;
import jp.co.broadleaf.breg.applyhistory.facade.dto.ApplyHistoryCommonInfoDto;
import jp.co.broadleaf.breg.applyhistory.facade.dto.ApplyHistorySearchDto;
import jp.co.broadleaf.breg.applyhistory.model.ApplyHistoryCommonModel;
import jp.co.broadleaf.breg.applyhistory.model.ApplyHistorySearchModel;
import jp.co.broadleaf.breg.applyhistory.service.ApplyHistoryCommonService;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.CarmakerMasterCommon;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.BlApplyResultsFlgEnum;
import jp.co.broadleaf.breg.common.service.BlCodeMasterCommonService;
import jp.co.broadleaf.breg.common.service.CarmakerMasterCommonService;
import jp.co.broadleaf.breg.companysetting.service.CompanyInfoMasterCommonService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * <pre>
 *  申請履歴一覧Facadeクラス.
 * </pre>
 */
public class ApplyHistoryCommonFacadeImpl implements ApplyHistoryCommonFacade {

  /** 申請履歴一覧サービス */
  private ApplyHistoryCommonService applyHistoryCommonService;
  /** 会社情報サービス */
  private CompanyInfoMasterCommonService companyInfoMasterCommonService;
  /** BLコードマスタサービス */
  private BlCodeMasterCommonService blCodeMasterCommonService;
  /** カーメーカーコードマスタ情報サービス */
  private CarmakerMasterCommonService carmakerMasterCommonService;
  /**
   * <pre>
   * 申請履歴一覧サービス.
   * </pre>
   * 
   * @param applyHistoryCommonService 申請履歴一覧サービス
   */
  @Resource
  public void setApplyHistoryCommonService(ApplyHistoryCommonService applyHistoryCommonService) {
    this.applyHistoryCommonService = applyHistoryCommonService;
  }
  
  /**
   * <pre>
   * 会社情報サービス.
   * </pre>
   * 
   * @param companyInfoMasterCommonService 会社情報サービス
   */
  @Resource
  public void setCompanyInfoMasterCommonService(CompanyInfoMasterCommonService companyInfoMasterCommonService) {
    this.companyInfoMasterCommonService = companyInfoMasterCommonService;
  }

  /**
   * BLコードマスタをセートします。
   * 
   * @param blCodeMasterCommonService BLコードマスタサービス
   */
  @Resource
  public void setBlCodeMasterCommonService(BlCodeMasterCommonService blCodeMasterCommonService) {
      this.blCodeMasterCommonService = blCodeMasterCommonService;
  }
  
  /**
   * <pre>
   * カーメーカーコードマスタ
   * </pre>
   * 
   * @param carmakerMasterCommonService カーメーカーコードマスタ
   */
  @Resource
  public void setCarmakerMasterCommonService(CarmakerMasterCommonService carmakerMasterCommonService) {
    this.carmakerMasterCommonService = carmakerMasterCommonService;
  }
  
  /**
   * BLコードマスタ情報を取得
   * 
   * @param makerCd
   *            メーカーコード
   * @return BLコードマスタ情報
   */
  @Override
  public Map<Integer,String> getBlCodeInfoMap(int makerCd) {
    Map<Integer,String> blCodeMap = new HashMap<Integer, String>();
      // BLコードマスタ情報リストを取得
      List<BlCodeMasterCommon> blCodeMasterInfo = blCodeMasterCommonService.getBlCodeMasterInfo(makerCd);

      if (blCodeMasterInfo != null && !blCodeMasterInfo.isEmpty()) {
        for (BlCodeMasterCommon blCodeMasterCommon : blCodeMasterInfo) {
          int blCode = blCodeMasterCommon.getBlCode();
          if (!blCodeMap.containsKey(blCode)) {
            blCodeMap.put(blCode, String.valueOf(blCode).concat("：").concat(blCodeMasterCommon.getBlFullName()));
          }
        }
      }
      return blCodeMap;
  }
  
  /**
   * <pre>
   * * カーメーカーコード名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return カーメーカーコード
   */
  @Override
  public Map<Integer, String> getCarmakerNameMap(int makerCd) {
    Map<Integer, String> carmakerNameMap = new HashMap<Integer, String>();
    List<CarmakerMasterCommon> carmakerInfo = carmakerMasterCommonService.getCarmakerMasterInfo(makerCd);
    if (carmakerInfo != null && !carmakerInfo.isEmpty()) {
      for (CarmakerMasterCommon carmakerMasterCommon : carmakerInfo) {
        Integer carmakerCode = carmakerMasterCommon.getMakerCode();
        if (!carmakerNameMap.containsKey(carmakerCode)) {
          carmakerNameMap.put(carmakerCode, String.valueOf(carmakerCode).concat("：").concat(carmakerMasterCommon.getMakerFullName()));
        }
      }
    }
    return carmakerNameMap;
  }
  
  /**
   * <pre>
   * 申請履歴ページ内行数設定の取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 申請履歴ページ内行数設定
   */
  @Override
  public int searchHistoryRows(int makerCd) {
    return companyInfoMasterCommonService.getCompanyInfo(makerCd).getApplyHistoryRows();
  }
  
  /**
   * <pre>
   * 申請履歴一覧の取得.
   * </pre>
   * 
   * @param applyHistorySearchDto 検索する項目の連係情報
   * @return 申請履歴一覧Dto
   */
  @Override
  public ApplyHistoryCommonInfoDto searchApplyHistoryInfoList(ApplyHistorySearchDto applyHistorySearchDto) {
    ApplyHistoryCommonInfoDto applyHistoryCommonInfoDto = new ApplyHistoryCommonInfoDto();
    ApplyHistorySearchModel searchModel = convertApplyHistorySearchModel(applyHistorySearchDto);
    //申請履歴一覧を取得
    List<ApplyHistoryCommonModel> applyHistoryCommonModelList = applyHistoryCommonService
        .searchApplyHistoryCommonInfoList(searchModel);
    
    applyHistoryCommonInfoDto.setApplyHistoryCommonDto(convertApplyHistoryCommonDtoList(applyHistoryCommonModelList));
    
    //申請中件数を取得
    int applyCount = applyHistoryCommonService.searchApplyCount(searchModel);
    applyHistoryCommonInfoDto.setApplyNum(applyCount);
    
    //承認済件数を取得
    int approvalCount = applyHistoryCommonService.searchApprovalCount(searchModel);
    applyHistoryCommonInfoDto.setApprovalNum(approvalCount);
    
    //検索件数を取得
    int searchCount = applyCount + approvalCount;
    applyHistoryCommonInfoDto.setSearchNum(searchCount);
    
    //全件数を取得
    long totalCount = applyHistoryCommonService.getTotalCount(searchModel);
    applyHistoryCommonInfoDto.setTotalNum(totalCount);

    return applyHistoryCommonInfoDto;
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param applyHistoryCommonModelList 申請履歴一覧Model
   * @return 申請履歴一覧Dto
   */
  private List<ApplyHistoryCommonDto> convertApplyHistoryCommonDtoList(List<ApplyHistoryCommonModel> applyHistoryCommonModelList) {
    List<ApplyHistoryCommonDto> applyHistoryCommonDtoList = new ArrayList<ApplyHistoryCommonDto>();

    if (null != applyHistoryCommonModelList && applyHistoryCommonModelList.size() > 0) {
      for (ApplyHistoryCommonModel applyHistoryCommonModel : applyHistoryCommonModelList) {
        ApplyHistoryCommonDto applyHistoryCommonDto = convertApplyHistoryCommonDto(applyHistoryCommonModel);
        applyHistoryCommonDtoList.add(applyHistoryCommonDto);
      }
    }
    return applyHistoryCommonDtoList;
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param applyHistoryCommonModel 申請履歴情報
   * @return 申請履歴情報Dto
   */
  private ApplyHistoryCommonDto convertApplyHistoryCommonDto(ApplyHistoryCommonModel applyHistoryCommonModel) {
    if (applyHistoryCommonModel == null) {
      return null;
    }
    ApplyHistoryCommonDto applyHistoryCommonDto = new ApplyHistoryCommonDto();
    // 申請No
    applyHistoryCommonDto.setApplyNo(applyHistoryCommonModel.getApplyNo());
    // 申請日時
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
      applyHistoryCommonDto.setApplyDateTime(sdf.format(applyHistoryCommonModel.getApplyDateTime()));
    } catch (Exception e) {
      applyHistoryCommonDto.setApplyDateTime("");
    }

    // 商品件数
    applyHistoryCommonDto.setGoodsNum(applyHistoryCommonModel.getGoodsNum());
    // セット件数
    applyHistoryCommonDto.setSetNum(applyHistoryCommonModel.getSetNum());
    // 結合件数
    applyHistoryCommonDto.setJoinNum(applyHistoryCommonModel.getJoinNum());
    // 新規品目登録件数
    applyHistoryCommonDto.setNewItemNum(applyHistoryCommonModel.getNewItemNum());

    // BL申請結果区分
    int status = applyHistoryCommonModel.getApplyStatus();
    // BL申請結果区分が1:申請中
    if (BlApplyResultsFlgEnum.Apply.getValue() == status) {
      // ステータスが1:申請中
      applyHistoryCommonDto.setStatus(ApplyConditionEnum.Apply.getName());
      // BL申請結果
      applyHistoryCommonDto.setBlApplyResults("");
    } else if (BlApplyResultsFlgEnum.Approval.getValue() == status
        || BlApplyResultsFlgEnum.ApplyAgain.getValue() == status
        || BlApplyResultsFlgEnum.Reject.getValue() == status) {
      // ステータスが1:承認済
      applyHistoryCommonDto.setStatus(ApplyConditionEnum.Approval.getName());
      // BL申請結果
      applyHistoryCommonDto.setBlApplyResults(BlApplyResultsFlgEnum.valueof(status).getName());
    } else {
      // ステータス
      applyHistoryCommonDto.setStatus("");
      // BL申請結果
      applyHistoryCommonDto.setBlApplyResults("");
    }

    // 申請コメント
    applyHistoryCommonDto.setApplyComment(applyHistoryCommonModel.getApplyComments());
    // BL申請判断日
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      Date date = sdf.parse(applyHistoryCommonModel.getBlApplyJudgmentDate());
      SimpleDateFormat sdformat = new SimpleDateFormat("yyyy/MM/dd");
      applyHistoryCommonDto.setBlApplyJudgmentDate(sdformat.format(date));
    } catch (Exception e) {
      applyHistoryCommonDto.setBlApplyJudgmentDate("");
    }
    // BLコメント
    applyHistoryCommonDto.setBlComment(applyHistoryCommonModel.getBlComments());
    return applyHistoryCommonDto;
  }

  /**
   * <pre>
   * DtoかModelへの転換.
   * </pre>
   * 
   * @param applyHistorySearchDto 申請履歴検索Dto
   * @return 申請履歴検索Model
   */
  private ApplyHistorySearchModel convertApplyHistorySearchModel(ApplyHistorySearchDto applyHistorySearchDto) {
    if (applyHistorySearchDto == null) {
      return null;
    }
    ApplyHistorySearchModel applyHistorySearchModel = new ApplyHistorySearchModel();
    // 申請No
    applyHistorySearchModel.setApplyNo(applyHistorySearchDto.getApplyNo());
    // BL申請結果区分
    applyHistorySearchModel.setBlApplyResultsFlg(applyHistorySearchDto.getBlApplyResultsFlg());
    // 申請期間（FROM）
    applyHistorySearchModel.setApplyDateTimeFrom(applyHistorySearchDto.getApplyDateTimeFrom());
    // 申請期間（TO）
    applyHistorySearchModel.setApplyDateTimeTo(applyHistorySearchDto.getApplyDateTimeTo());
    // BLコード
    applyHistorySearchModel.setTbsPartsCode(applyHistorySearchDto.getTbsPartsCode());
    // 優良品番
    applyHistorySearchModel.setPrimePartsNo(applyHistorySearchDto.getPrimePartsNo());
    // 申請種類
    applyHistorySearchModel.setApplyType(applyHistorySearchDto.getApplyType());
    // カーコード
    applyHistorySearchModel.setPartsMakerCd(applyHistorySearchDto.getPartsMakerCd());
    // 純正品番
    applyHistorySearchModel.setJoinSourPartsNoWithH(applyHistorySearchDto.getJoinSourPartsNoWithH());
    // 表示順
    applyHistorySearchModel.setApplySort(applyHistorySearchDto.getApplySort());
    // 当前頁
    applyHistorySearchModel.setPage(applyHistorySearchDto.getPage());
    // 申請履歴ページ内行数
    applyHistorySearchModel.setRows(applyHistorySearchDto.getRows());
    // 申請履歴ページ内行数
    applyHistorySearchModel.setMakerCd(applyHistorySearchDto.getMakerCd());

    return applyHistorySearchModel;
  }
}
