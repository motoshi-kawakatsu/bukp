package jp.co.broadleaf.breg.joinlist.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 結合マスタ(メーカー)のDtoのクラス。
 */
public class JoinMasterInfoDto implements Serializable {

  /**  */
  private static final long serialVersionUID = 5885008501175915953L;
  /**
   * 結合マスタ(メーカー)
   */
  private List<JoinMasterDto> joinMasterDto;
  /**
   * error
   */
  Boolean isErrorExist;
  /**
   * 結合マスタ(メーカー)
   */
  private List<JoinGridDto> joinGridDtoList;
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
   * 結合マスタ(メーカー)の取得。
   *
   * @return 結合マスタ(メーカー)
   */
  public List<JoinMasterDto> getJoinMasterDto() {
    return joinMasterDto;
  }

  /**
   * 結合マスタ(メーカー)の設定。
   *
   * @param joinMasterDto 結合マスタ(メーカー)
   */
  public void setJoinMasterDto(List<JoinMasterDto> joinMasterDto) {
    this.joinMasterDto = joinMasterDto;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(joinMasterDto).toHashCode();
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
    JoinMasterInfoDto other = (JoinMasterInfoDto) obj;
    return new EqualsBuilder().append(joinMasterDto, other.joinMasterDto).isEquals();
  }

  /**
   * errorの取得。
   *
   * @return error
   */
  public Boolean getIsErrorExist() {
    return isErrorExist;
  }

  /**
   * errorの設定。
   *
   * @param isErrorExist error
   */
  public void setIsErrorExist(Boolean isErrorExist) {
    this.isErrorExist = isErrorExist;
  }


  /**
   * <pre>
   * 【joinGridDtoList】を取得する。
   * </pre>
   *
   * @return 【joinGridDtoList】
   */
  public List<JoinGridDto> getJoinGridDtoList() {
    return joinGridDtoList;
  }

  /**
   * <pre>
   * 【joinGridDtoList】を設定する。
   * </pre>
   *
   * @param joinGridDtoList 【joinGridDtoList】
   */
  public void setJoinGridDtoList(List<JoinGridDto> joinGridDtoList) {
    this.joinGridDtoList = joinGridDtoList;
  }
}
