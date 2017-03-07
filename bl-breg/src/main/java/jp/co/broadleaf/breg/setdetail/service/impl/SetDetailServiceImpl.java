//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 燕京
// 作 成 日       2017/02/06   修正内容 : セート詳細：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.setdetail.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.Validate;

import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.common.enums.LogicDeleteDivEnum;
import jp.co.broadleaf.breg.setdetail.service.SetDetailService;
import jp.co.broadleaf.framework.dao.GenericDao;

/**
 * <pre>
 * セート詳細サービスクラスです。
 * </pre>
 */
public class SetDetailServiceImpl implements SetDetailService {
  /**
   * セートマストリストを取得します。
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param goodsMGroup 商品中分類コード
   * @param setMainPartsNo セット親品番
   * @return セート詳細のDTOリスト
   */
  public List<SetMasterMaker> getSetDetail(String prmSetDtlNo1, String partsMakerCd, String goodsMGroup,
                                           String setMainPartsNo) {
    Validate.notNull(prmSetDtlNo1, "prmSetDtlNo1 must not be null");
    Validate.notNull(partsMakerCd, "partsMakerCd must not be null");
    Validate.notNull(setMainPartsNo, "setMainPartsNo must not be null");

    SetMasterMaker searchCmd = new SetMasterMaker();
    searchCmd.setPrmSetDtlNo1(Integer.parseInt(prmSetDtlNo1));
    searchCmd.setPartsMakerCd(Integer.parseInt(partsMakerCd));
    if(goodsMGroup!=null){
    	 searchCmd.setGoodsMGroup(Integer.parseInt(goodsMGroup));
    }
    searchCmd.setSetMainPartsNo(setMainPartsNo);
    searchCmd.setLogicalDelDiv(LogicDeleteDivEnum.Valid.getValue());

    return setMasterMakerDao.findByExample(searchCmd);
  }

  /** セットマスタ(メーカー)DAO */
  private GenericDao<SetMasterMaker, SetMasterMakerId> setMasterMakerDao;

  /**
   * <pre>
   * セットマスタ(メーカー)DAOを設定する.
   * </pre>
   * 
   * @param setMasterMakerDao セットマスタ(メーカー)DAO
   */
  @Resource
  public void setSetMasterMakerDao(GenericDao<SetMasterMaker, SetMasterMakerId> setMasterMakerDao) {
    this.setMasterMakerDao = setMasterMakerDao;
  }
}
