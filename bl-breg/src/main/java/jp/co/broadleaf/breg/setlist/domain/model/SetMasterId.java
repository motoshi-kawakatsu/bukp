//****************************************************************************//
// システム                                    : ????????
// ---------------------------------------------------------------------------//
//                (c) Copyright 2015 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号     ????????-??  作成担当 : ???
// 作 成 日       ????/??/??   修正内容 : ???
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.setlist.domain.model;

import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;

import java.io.Serializable;

/**
 * <pre>
 * 
 * </pre>
 */
public class SetMasterId implements Serializable{

  /**  */
  private static final long serialVersionUID = 4982072231926796823L;
  /**
   * 優良設定詳細コード１を返します。
   * 
   */
  private int prmSetDtlNo1;
  /**
   * 部品メーカーコードを返します。
   */
  private int partsMakerCd;
  /**
   * セット親品番を返します。
   */
  private String setMainPartsNo;
  /**
   * セット表示順位を返します。
   */
  private int setDispOrder;
  
  /**
   * コンストラクタ。
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param setMainPartsNo セット親品番
   * @param setDispOrder セット表示順位
   */
  public SetMasterId(int prmSetDtlNo1, int partsMakerCd, String setMainPartsNo, int setDispOrder) {
    setPrmSetDtlNo1(prmSetDtlNo1);
    setPartsMakerCd(partsMakerCd);
    setSetMainPartsNo(setMainPartsNo);
    setSetDispOrder(setDispOrder);
  }
  /**
   * コンストラクタ。
   */
  public SetMasterId() {
    
  }


  /**
   * <pre>
   * 【prmSetDtlNo1】を取得する。
   * </pre>
   *
   * @return 【prmSetDtlNo1】
   */
  public int getPrmSetDtlNo1() {
    return prmSetDtlNo1;
  }


  /**
   * <pre>
   * 【prmSetDtlNo1】を設定する。
   * </pre>
   *
   * @param prmSetDtlNo1 【prmSetDtlNo1】
   */
  public void setPrmSetDtlNo1(int prmSetDtlNo1) {
    this.prmSetDtlNo1 = prmSetDtlNo1;
  }


  /**
   * <pre>
   * 【partsMakerCd】を取得する。
   * </pre>
   *
   * @return 【partsMakerCd】
   */
  public int getPartsMakerCd() {
    return partsMakerCd;
  }


  /**
   * <pre>
   * 【partsMakerCd】を設定する。
   * </pre>
   *
   * @param partsMakerCd 【partsMakerCd】
   */
  public void setPartsMakerCd(int partsMakerCd) {
    this.partsMakerCd = partsMakerCd;
  }


  /**
   * <pre>
   * 【setMainPartsNo】を取得する。
   * </pre>
   *
   * @return 【setMainPartsNo】
   */
  public String getSetMainPartsNo() {
    return setMainPartsNo;
  }


  /**
   * <pre>
   * 【setMainPartsNo】を設定する。
   * </pre>
   *
   * @param setMainPartsNo 【setMainPartsNo】
   */
  public void setSetMainPartsNo(String setMainPartsNo) {
    this.setMainPartsNo = setMainPartsNo;
  }


  /**
   * <pre>
   * 【setDispOrder】を取得する。
   * </pre>
   *
   * @return 【setDispOrder】
   */
  public int getSetDispOrder() {
    return setDispOrder;
  }


  /**
   * <pre>
   * 【setDispOrder】を設定する。
   * </pre>
   *
   * @param setDispOrder 【setDispOrder】
   */
  public void setSetDispOrder(int setDispOrder) {
    this.setDispOrder = setDispOrder;
  }
  
  /**
   * <pre>
   * isEquals
   * </pre>
   *
   * @param id SetMasterId
   * @return Boolean true false
   */
  public Boolean isEqualsId(SetMasterId id){
    if(this.prmSetDtlNo1==id.getPrmSetDtlNo1()
        &&this.partsMakerCd==id.getPartsMakerCd()
        &&this.setMainPartsNo.equals(id.getSetMainPartsNo())
        &&this.setDispOrder==id.getSetDispOrder()){
      return true;
    }else{
      return false;
    }
      
  }
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "SetMasterId [prmSetDtlNo1=" + prmSetDtlNo1 + ", partsMakerCd=" + partsMakerCd + ", setMainPartsNo="
        + setMainPartsNo + ", setDispOrder=" + setDispOrder + "]\n";
  }
  
  

}
