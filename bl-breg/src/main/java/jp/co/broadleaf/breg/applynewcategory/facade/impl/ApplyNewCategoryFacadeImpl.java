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
package jp.co.broadleaf.breg.applynewcategory.facade.impl;

import jp.co.broadleaf.breg.applynewcategory.facade.ApplyNewCategoryFacade;
import jp.co.broadleaf.breg.applynewcategory.facade.dto.ApplyNewCategoryDto;
import jp.co.broadleaf.breg.applynewcategory.service.ApplyNewCategoryService;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommon;
import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;
import jp.co.broadleaf.breg.common.enums.ApplyTypeEnum;
import jp.co.broadleaf.breg.common.enums.BlApplyResultsFlgEnum;
import jp.co.broadleaf.breg.common.enums.MailDivEnum;
import jp.co.broadleaf.breg.common.service.BizEmailService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

/**
 * <pre>
 * 申請 (新規品目)Facadeクラス.
 * </pre>
 */
public class ApplyNewCategoryFacadeImpl implements ApplyNewCategoryFacade {
  
  /** ブロードリーフ **/
  private static final String BROADLEAF = "ブロードリーフ　担当者";
  /**
   * <pre>
   * 初期データを取得する.
   * </pre>
   * @param applyNewCategoryDto 申請 (新規品目)のDTO
   * @param isApplyAgain ApplyStatus
   * @return applyNo 申請ID
   */
  @Override
  public ApplyNewCategoryDto getApplyHistoryInfo(ApplyNewCategoryDto applyNewCategoryDto,boolean isApplyAgain) {
    ApplyNewCategoryDto applyHistryInfo = new ApplyNewCategoryDto();
    ApplyHeadMasterCommon applyHeadMasterCommon= new ApplyHeadMasterCommon();
    applyHeadMasterCommon.setApplyNo(applyNewCategoryDto.getApplyNo());
    applyHeadMasterCommon.setPartsMakerCd(applyNewCategoryDto.getPartsMakerCd());
    applyHeadMasterCommon.setBlApplyResultsFlg(applyNewCategoryDto.getBlApplyResultsFlg());
    ApplyHeadMasterCommon applyHeadMasterCommonInfo = applyNewCategoryService.searchApplyHistoryInfo(applyHeadMasterCommon,isApplyAgain);
    applyHistryInfo.setApplyDtTime(applyHeadMasterCommonInfo.getApplyDtTime());
    applyHistryInfo.setApplyComments(applyHeadMasterCommonInfo.getApplyComments());
    return applyHistryInfo;
  }
  
  /**
   * <pre>
   * 申請ヘッダマスタのApplyNOを取得する.
   * </pre>
   * 
   * @return applyNo 申請ID.
   */
  @Override
  public int searchApplyNO() {
    int applyNo = applyNewCategoryService.getApplyNo();
    return applyNo;
  }
  
  /**
   * <pre>
   * 申請 (新規品目)の情報.
   * </pre>
   * 
   * @param applyNewCategoryDto 申請 (新規品目)のDTO
   * @return 0:挿入成功　1:挿入失敗
   */
  @Override
  public int saveApplyNewCaregory(ApplyNewCategoryDto applyNewCategoryDto) {
    ApplyHeadMasterCommon applyHeadMasterCommon = new ApplyHeadMasterCommon();
    short blApplyResultsFlg = (short) BlApplyResultsFlgEnum.Apply.getValue();
    applyHeadMasterCommon.setPartsMakerCd(applyNewCategoryDto.getPartsMakerCd());
    applyHeadMasterCommon.setApplyNo(applyNewCategoryDto.getApplyNo());
    applyHeadMasterCommon.setApplyComments(applyNewCategoryDto.getApplyComments());
    applyHeadMasterCommon.setApplyDtTime(applyNewCategoryDto.getApplyDtTime());
    applyHeadMasterCommon.setBlApplyResultsFlg(blApplyResultsFlg);
    applyHeadMasterCommon.setApplyType(ApplyTypeEnum.NewItem.getValue());
    applyHeadMasterCommon.setPreApplyNo(applyNewCategoryDto.getPreApplyNo());
    int insertStatus = applyNewCategoryService.insertIntoApplyHistory(applyHeadMasterCommon);
    return insertStatus;
  }
  
  /** 申請 (新規品目)サービス */
  private ApplyNewCategoryService applyNewCategoryService;

  /**
   * <pre>
   * 申請 (新規品目)サービスを設定する.
   * </pre>
   *
   * @param applyNewCategoryService 申請 (新規品目)サービス
   */
  @Resource
  public void setApplyNewCategoryService(ApplyNewCategoryService applyNewCategoryService) {
    this.applyNewCategoryService = applyNewCategoryService;
  }

  /** 業務メール送信 */
  private BizEmailService bizEmailService;
  
  /**
   * <pre>
   * 業務メール送信を設定する。
   * </pre>
   *
   * @param bizEmailService 業務メール送信
   */
  @Resource
  public void setBizEmailService(BizEmailService bizEmailService) {
    this.bizEmailService = bizEmailService;
  }

  /**
   * Mail
   * 
   * @param makerCode makerCode
   * @param loginId loginId
   * @param ipAddr ipAddr
   * @return  sendEmailRtn
   */
  @Override
  public boolean sendMail(int makerCode, String loginId, String ipAddr) {
    CompanyInfoMasterCommon companyInfoMasterCommon = applyNewCategoryService.getBlMailAdd(makerCode);
    // Mail Address
    String blMailAddr = companyInfoMasterCommon.getBlMailAdd();
    String makerName = companyInfoMasterCommon.getCompanyName();
    
    // メール本文可変文言リスト
    List<String> mailBodyChangeableTextList = new ArrayList<String>();
    mailBodyChangeableTextList.add(BROADLEAF);
    mailBodyChangeableTextList.add(makerName);
    boolean sendEmailRtn = bizEmailService.sendBizEmail(MailDivEnum.ApplicationNotification, blMailAddr,
        new ArrayList<>(), new ArrayList<>(), mailBodyChangeableTextList);
 
    return sendEmailRtn;
    
  }
}
