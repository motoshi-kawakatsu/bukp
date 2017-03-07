package jp.co.broadleaf.breg.joinlist.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * 結合マスタ(メーカー)のDtoのクラス。
 */
public class JoinMasterWorkInfoDto {

  /**
   * 結合マスタ(メーカー)
   */
  private List<JoinMasterWorkDto> joinMasterWorkDto;

  /**
   * 結合マスタ(メーカー)の取得。
   *
   * @return 結合マスタ(メーカー)
   */
  public List<JoinMasterWorkDto> getJoinMasterWorkDto() {
    return joinMasterWorkDto;
  }

  /**
   * 結合マスタ(メーカー)の設定。
   *
   * @param joinMasterWorkDto 結合マスタ(メーカー)
   */
  public void setJoinMasterWorkDto(List<JoinMasterWorkDto> joinMasterWorkDto) {
    this.joinMasterWorkDto = joinMasterWorkDto;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(joinMasterWorkDto).toHashCode();
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
    JoinMasterWorkInfoDto other = (JoinMasterWorkInfoDto) obj;
    return new EqualsBuilder().append(joinMasterWorkDto, other.joinMasterWorkDto).isEquals();
  }
}
