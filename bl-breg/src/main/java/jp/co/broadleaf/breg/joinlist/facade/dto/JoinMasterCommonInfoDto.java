package jp.co.broadleaf.breg.joinlist.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 結合マスタ(メーカー)のDtoのクラス。
 */
public class JoinMasterCommonInfoDto implements Serializable {

  /**  */
  private static final long serialVersionUID = 1147383489925959100L;
  /**
   * 結合マスタ(メーカー)
   */
  private List<JoinMasterCommonDto> joinMasterCommonDto;

  /**
   * 結合マスタ(メーカー)の取得。
   *
   * @return 結合マスタ(メーカー)
   */
  public List<JoinMasterCommonDto> getJoinMasterCommonDto() {
    return joinMasterCommonDto;
  }

  /**
   * 結合マスタ(メーカー)の設定。
   *
   * @param joinMasterDto 結合マスタ(メーカー)
   */
  public void setJoinMasterCommonDto(List<JoinMasterCommonDto> joinMasterDto) {
    this.joinMasterCommonDto = joinMasterDto;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(joinMasterCommonDto).toHashCode();
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
    JoinMasterCommonInfoDto other = (JoinMasterCommonInfoDto) obj;
    return new EqualsBuilder().append(joinMasterCommonDto, other.joinMasterCommonDto).isEquals();
  }
}
