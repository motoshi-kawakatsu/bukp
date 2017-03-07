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
public class SetMasterCommonInfoDto implements Serializable {

  /**  */
  private static final long serialVersionUID = -8298227078616294147L;
  /**
   * セットマスタ(メーカー)
   */
  private List<SetMasterCommonDto> setMasterCommonDtoList;

  /**
   * セットマスタ(メーカー)の取得。
   *
   * @return セットマスタ(メーカー)
   */
  public List<SetMasterCommonDto> getSetMasterCommonDtoList() {
    return setMasterCommonDtoList;
  }

  /**
   * セットマスタ(メーカー)の設定。
   *
   * @param setMasterDtoList セットマスタ(メーカー)
   */
  public void setSetMasterCommonDtoList(List<SetMasterCommonDto> setMasterDtoList) {
    this.setMasterCommonDtoList = setMasterDtoList;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(setMasterCommonDtoList).toHashCode();
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
    SetMasterCommonInfoDto other = (SetMasterCommonInfoDto) obj;
    return new EqualsBuilder().append(setMasterCommonDtoList, other.setMasterCommonDtoList).isEquals();
  }

}
