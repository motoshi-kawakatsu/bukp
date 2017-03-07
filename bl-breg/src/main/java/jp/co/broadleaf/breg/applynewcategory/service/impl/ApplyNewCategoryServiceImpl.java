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
package jp.co.broadleaf.breg.applynewcategory.service.impl;

import jp.co.broadleaf.breg.applynewcategory.service.ApplyNewCategoryService;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommon;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommonId;
import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;
import jp.co.broadleaf.breg.common.enums.BlApplyResultsFlgEnum;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.FieldDescOrder;
import jp.co.broadleaf.framework.persistence.jdbc.SqlCommand;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.annotation.Resource;

/**
 * <pre>
 * 申請 (新規品目)サービスクラス.
 * </pre>
 */
public class ApplyNewCategoryServiceImpl implements ApplyNewCategoryService {
  
  /**
   * <pre>
   * 初期データを取得と更新.
   * </pre>
   * @param applyHeadMasterCommon データ
   * @param isApplyAgain ApplyStatus
   * @return applyNo 申請ID
   */
  @Override
  public ApplyHeadMasterCommon searchApplyHistoryInfo(ApplyHeadMasterCommon applyHeadMasterCommon,boolean isApplyAgain) {
    ApplyHeadMasterCommon  applyHeadMasterCommonInfo = new  ApplyHeadMasterCommon();
    // 初期データを更新
    try{
      if(isApplyAgain){
        short blApplyResultsFlg = (short) BlApplyResultsFlgEnum.ApplyAgain.getValue();
        SqlCommand command = new SqlCommand();
        StringBuilder sql = new StringBuilder();
        sql.append("update m_apply_head_common set bl_apply_results_flg =");
        sql.append(blApplyResultsFlg);
        sql.append(" where apply_no =");
        sql.append(applyHeadMasterCommon.getApplyNo());
        sql.append(" and parts_maker_cd =");
        sql.append(applyHeadMasterCommon.getPartsMakerCd());
        sql.append(" and bl_apply_results_flg =");
        sql.append(applyHeadMasterCommon.getBlApplyResultsFlg());
        command.setText(sql.toString());
        jdbcTemplateCommon.execute(command.getText());
        
      }else{  // 初期データを取得
        ApplyHeadMasterCommonId applyHeadMasterCommonId = new ApplyHeadMasterCommonId();
        applyHeadMasterCommonId.setApplyNo(applyHeadMasterCommon.getApplyNo());
        applyHeadMasterCommonId.setPartsMakerCd(applyHeadMasterCommon.getPartsMakerCd());
        applyHeadMasterCommonId.setBlApplyResultsFlg(applyHeadMasterCommon.getBlApplyResultsFlg());
        applyHeadMasterCommonInfo = applyHeadMasterCommonDao.findById(applyHeadMasterCommonId);
      }
    }catch (Exception e){

    }
    return applyHeadMasterCommonInfo;
  }
  
  /**
   * <pre>
   * 申請ヘッダマスタのApplyNOを取得する.
   * </pre>
   * 
   * @return applyNo 申請ID
   */
  @Override
  public int getApplyNo() {
    ApplyHeadMasterCommon applyHeadMasterCommon = new ApplyHeadMasterCommon();
    FieldDescOrder fieldDescOrder = new FieldDescOrder(ApplyHeadMasterCommon.APPLY_NO);
    applyHeadMasterCommon = applyHeadMasterCommonDao.findOneByExample(applyHeadMasterCommon,fieldDescOrder);
    // ApplyNOを取得する
    int applyNo = applyHeadMasterCommon.getApplyNo();
    return applyNo;
  }

  /**
   * <pre>
   * 申請 (新規品目)を挿入する.
   * </pre>
   * 
   * @param applyHeadMasterCommon 申請ヘッダマスタのentity
   * @return 0:挿入成功　1:挿入失敗
   */
  @Override
  public int insertIntoApplyHistory(ApplyHeadMasterCommon applyHeadMasterCommon) {
    ApplyHeadMasterCommon insertEntity = (ApplyHeadMasterCommon) applyHeadMasterCommon.clone();
    try{
      applyHeadMasterCommonDao.insert(insertEntity);
      return 0;
    }catch(Exception e){
      return 1;
    }
  }
  

  /**
   * <pre>
   * MailAddressを取得する.
   * </pre>
   * 
   * @param makerCode makerCode
   * @return blMailAddress
   */
  @Override
  public CompanyInfoMasterCommon getBlMailAdd(int makerCode) {
    CompanyInfoMasterCommon companyInfoMasterCommon  = companyInfoMasterCommonDao.findById(makerCode);
    return companyInfoMasterCommon;
  }
  
  /** Jdbcテンプレート */ 
  private JdbcTemplate jdbcTemplateCommon; 
  
  /**
   * <pre>
   * Jdbcテンプレートを設定する。
   * </pre>
   *
   * @param jdbcTemplateCommon Jdbcテンプレート
   */
  @Resource
  public void setJdbcTemplateCommon(JdbcTemplate jdbcTemplateCommon) {
    this.jdbcTemplateCommon = jdbcTemplateCommon;
  }

  /** 申請ヘッダマスタDAO */
  private GenericDao<ApplyHeadMasterCommon, ApplyHeadMasterCommonId> applyHeadMasterCommonDao;

  /**
   * <pre>
   * 申請ヘッダマスタDAOを設定する.
   * </pre>
   *
   * @param applyHeadMasterCommonDao 申請ヘッダマスタDAO
   */
  @Resource
  public void setApplyHeadMasterCommonDao(GenericDao<ApplyHeadMasterCommon, ApplyHeadMasterCommonId> applyHeadMasterCommonDao) {
    this.applyHeadMasterCommonDao = applyHeadMasterCommonDao;
  }
  
  /** 会社情報マスタDAO */
  private  GenericDao<CompanyInfoMasterCommon, Integer> companyInfoMasterCommonDao;

  /**
   * <pre>
   * 会社情報マスタを設定する。
   * </pre>
   *
   * @param companyInfoMasterCommonDao 会社情報マスタ
   */
  @Resource
  public void setCompanyInfoMasterCommonDao(GenericDao<CompanyInfoMasterCommon, Integer> companyInfoMasterCommonDao) {
    this.companyInfoMasterCommonDao = companyInfoMasterCommonDao;
  }
  

}
