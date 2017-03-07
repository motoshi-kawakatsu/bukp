//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : tangyp
// 作 成 日       2017/02/10   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applyhistory.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 申請履歴一覧のDtoのクラス。
 */
public class ApplyHistoryCommonInfoDto implements Serializable {
  
  /**  serialVersionUID*/
  private static final long serialVersionUID = 1L;
  
  /**
   *　申請中件数
   */
  private int applyNum;

  /**
   *　承認済件数
   */
  private int approvalNum;
  
  /**
   *　検索中件数
   */
  private int searchNum;

  /**
   *　全件数
   */
  private long totalNum;
  
  /**
   *　申請履歴一覧
   */
  private List<ApplyHistoryCommonDto> applyHistoryCommonDto;

  
  /**
   * 申請中件数の取得。
   *
   * @return 申請中件数
   */
  public int getApplyNum() {
    return applyNum;
  }

  /**
   * 申請中件数の設定。
   *
   * @param applyNum 申請中件数
   */
  public void setApplyNum(int applyNum) {
    this.applyNum = applyNum;
  }

  /**
   * 承認済件数の取得。
   *
   * @return 承認済件数
   */
  public int getApprovalNum() {
    return approvalNum;
  }

  /**
   * 承認済件数の設定。
   *
   * @param approvalNum 承認済件数
   */
  public void setApprovalNum(int approvalNum) {
    this.approvalNum = approvalNum;
  }

  /**
   * 検索中件数の取得。
   *
   * @return 検索中件数
   */
  public int getSearchNum() {
    return searchNum;
  }
  /**
   * 検索中件数の設定。
   *
   * @param searchNum 検索中済件数
   */
  public void setSearchNum(int searchNum) {
    this.searchNum = searchNum;
  }

  /**
   * 全件数の取得。
   *
   * @return 全件数
   */
  public long getTotalNum() {
    return totalNum;
  }
  /**
   * 全件数の設定。
   *
   * @param totalNum 全件数
   */
  public void setTotalNum(long totalNum) {
    this.totalNum = totalNum;
  }

  /**
   * 申請履歴一覧の取得。
   *
   * @return 申請履歴一覧
   */
  public List<ApplyHistoryCommonDto> getApplyHistoryCommonDto() {
    return applyHistoryCommonDto;
  }

  /**
   * 申請履歴一覧の設定。
   *
   * @param applyHistoryCommonDto 申請履歴一覧
   */
  public void setApplyHistoryCommonDto(List<ApplyHistoryCommonDto> applyHistoryCommonDto) {
    this.applyHistoryCommonDto = applyHistoryCommonDto;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(applyHistoryCommonDto).toHashCode();
  }

  /**
   * equalsメソッド
   * 
   * @param obj オブジェクト
   * @return 比較結果
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    ApplyHistoryCommonInfoDto other = (ApplyHistoryCommonInfoDto) obj;
    return new EqualsBuilder().append(applyHistoryCommonDto, other.applyHistoryCommonDto).isEquals();
  }

}
