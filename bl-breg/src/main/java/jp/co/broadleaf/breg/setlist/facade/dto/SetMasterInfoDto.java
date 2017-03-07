//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : chenlong
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.setlist.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * セットマスタ(メーカー)のDtoのクラス。
 */
public class SetMasterInfoDto implements Serializable {

  /**  */
  private static final long serialVersionUID = -1247549709593892112L;

  /**
   * セットマスタ(メーカー)
   */
  private List<SetMasterDto> setMasterDtoList;
  /**
   * 検索件数
   */
  long searchCounts;

  /**
   * <pre>
   * 【検索件数】を取得する。
   * </pre>
   *
   * @return 【検索件数】
   */
  public long getSearchCounts() {
    return searchCounts;
  }

  /**
   * <pre>
   * 【検索件数】を設定する。
   * </pre>
   *
   * @param searchCounts 【検索件数】
   */
  public void setSearchCounts(long searchCounts) {
    this.searchCounts = searchCounts;
  }

  /**
   * セットマスタ(メーカー)の取得。
   *
   * @return セットマスタ(メーカー)
   */
  public List<SetMasterDto> getSetMasterDtoList() {
    return setMasterDtoList;
  }

  /**
   * セットマスタ(メーカー)の設定。
   *
   * @param setMasterDtoList セットマスタ(メーカー)
   */
  public void setSetMasterDtoList(List<SetMasterDto> setMasterDtoList) {
    this.setMasterDtoList = setMasterDtoList;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(setMasterDtoList).toHashCode();
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
    SetMasterInfoDto other = (SetMasterInfoDto) obj;
    return new EqualsBuilder().append(setMasterDtoList, other.setMasterDtoList).isEquals();
  }

}
