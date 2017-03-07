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
package jp.co.broadleaf.breg.companysetting.service.impl;

import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;
import jp.co.broadleaf.breg.companysetting.service.CompanyInfoMasterCommonService;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import org.apache.commons.lang3.Validate;
import javax.annotation.Resource;

/**
 * <pre>
 * 会社情報のServiceクラス.
 * </pre>
 */
public class CompanyInfoMasterCommonServiceImpl implements CompanyInfoMasterCommonService{

  /** 会社情報DAO */
  private GenericDao<CompanyInfoMasterCommon, Integer> companyInfoMasterCommonDao;
  
  /**
   * 会社情報DAOを設定する
   * 
   * @param companyInfoMasterCommonDao 会社情報DAO
   */
  @Resource
  public void setCompanyInfoMasterCommonDao(GenericDao<CompanyInfoMasterCommon, Integer> companyInfoMasterCommonDao) {
    this.companyInfoMasterCommonDao = companyInfoMasterCommonDao;
  }
  
  /**
   * 会社情報を取得。
   * 
   * @param makerCd メーカーコード
   * @return 会社情報
   */
  @Override
  public CompanyInfoMasterCommon getCompanyInfo(int makerCd) {
    Validate.notNull(makerCd, "makerCd must not be null.");
    NumberFilter makerCdFilter = NumberFilterBuilder.equals(CompanyInfoMasterCommon.MAKER_CODE, makerCd);
    NumberFilter logicUndeletedFilter = NumberFilterBuilder.equals(
        CompanyInfoMasterCommon.LOGICAL_DEL_DIV, CompanyInfoMasterCommon.LOGICAL_UNDELETED);
    Filter filter = new AndFilter(makerCdFilter,logicUndeletedFilter);
    CompanyInfoMasterCommon result = companyInfoMasterCommonDao.findOneByFilter(filter);
    return result;
  }

  /**
   * 会社情報を更新
   * 
   * @param companyInfo CompanySetting
   * @return 0:更新成功 1:更新失敗
   */
  @Override
  public int updateCompanyInfo(CompanyInfoMasterCommon companyInfo) {
    CompanyInfoMasterCommon updateEntity = (CompanyInfoMasterCommon) companyInfo.clone();
    try{
      companyInfoMasterCommonDao.update(updateEntity);
      return 0;
    }catch(Exception e){
      return 1;
    }
  }
}
