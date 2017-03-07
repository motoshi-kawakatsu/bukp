//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : xuck
// 作 成 日       2017/02/13   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applyheadmastercommon.facade.dto;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * </pre>
 */
public class ApplyHeadMasterCommonInfoDto implements Serializable {
  /**  */
  private static final long serialVersionUID = -6963048769009350933L;
  /**
   * tesat
   */
  private List<ApplyHeadMasterCommonDto> applyHeadMasterCommonDto;

  /**
   * <pre>
   * 【applyHeadMasterCommonDto】を取得する。
   * </pre>
   *
   * @return 【applyHeadMasterCommonDto】
   */
  public List<ApplyHeadMasterCommonDto> getApplyHeadMasterCommonDto() {
    return applyHeadMasterCommonDto;
  }

  /**
   * <pre>
   * 【applyHeadMasterCommonDto】を設定する。
   * </pre>
   *
   * @param applyHeadMasterCommonDto 【applyHeadMasterCommonDto】
   */
  public void setApplyHeadMasterCommonDto(List<ApplyHeadMasterCommonDto> applyHeadMasterCommonDto) {
    this.applyHeadMasterCommonDto = applyHeadMasterCommonDto;
  }

  /**
   * <pre>
   * 【applyType】を取得する。
   * </pre>
   * 
   * @return 【applyType】
   */
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((applyHeadMasterCommonDto == null) ? 0 : applyHeadMasterCommonDto.hashCode());
    return result;
  }

  /**
   * <pre>
   * 【applyType】を取得する。
   * </pre>
   * 
   * @param obj obj
   * @return 【applyType】
   */
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ApplyHeadMasterCommonInfoDto other = (ApplyHeadMasterCommonInfoDto) obj;
    if (applyHeadMasterCommonDto == null) {
      if (other.applyHeadMasterCommonDto != null)
        return false;
    } else if (!applyHeadMasterCommonDto.equals(other.applyHeadMasterCommonDto))
      return false;
    return true;
  }

}
