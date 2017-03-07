//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : tangyp
// 作 成 日       2017/02/14   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applyhistory.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.sql.Timestamp;

/**
 * <pre>
 * 申請履歴情報Model.
 * </pre>
 */
public class ApplyHistoryCommonModel {
  /**
   * 申請No
   */
  private String applyNo;

  /**
   * 申請日時
   */
  private Timestamp applyDateTime;

  /**
   * 商品件数
   */
  private int goodsNum;

  /**
   * セット件数
   */
  private int setNum;

  /**
   * 結合件数
   */
  private int joinNum;

  /**
   * 新規品目登録件数
   */
  private int newItemNum;

  /**
   * ステータス
   */
  private int applyStatus;

  /**
   * 申請コメント
   */
  private String applyComments;

  /**
   * BL申請判断日
   */
  private String blApplyJudgmentDate;

  /**
   * BLコメント
   */
  private String blComments;

  /**
   * 申請Noの取得。
   *
   * @return String 申請No
   */
  public String getApplyNo() {
    return applyNo;
  }

  /**
   * 申請Noの設定。
   *
   * @param applyNo 申請No
   */
  public void setApplyNo(String applyNo) {
    this.applyNo = applyNo;
  }

  /**
   * 申請日時の取得。
   *
   * @return String 申請日時
   */
  public Timestamp getApplyDateTime() {
    return applyDateTime;
  }

  /**
   * 申請日時の設定。
   *
   * @param applyDateTime 申請日時
   */
  public void setApplyDateTime(Timestamp applyDateTime) {
    this.applyDateTime = applyDateTime;
  }

  /**
   * 商品件数の取得。
   *
   * @return String 商品件数
   */
  public int getGoodsNum() {
    return goodsNum;
  }

  /**
   * 商品件数の設定。
   *
   * @param goodsNum 商品件数
   */
  public void setGoodsNum(int goodsNum) {
    this.goodsNum = goodsNum;
  }

  /**
   * セット件数の取得。
   *
   * @return String セット件数
   */
  public int getSetNum() {
    return setNum;
  }

  /**
   * セット件数の設定。
   *
   * @param setNum セット件数
   */
  public void setSetNum(int setNum) {
    this.setNum = setNum;
  }

  /**
   * 結合件数の取得。
   *
   * @return String 結合件数
   */
  public int getJoinNum() {
    return joinNum;
  }

  /**
   * 結合件数の設定。
   *
   * @param joinNum 結合件数
   */
  public void setJoinNum(int joinNum) {
    this.joinNum = joinNum;
  }

  /**
   * 新規品目登録件数の取得。
   *
   * @return String 新規品目登録件数
   */
  public int getNewItemNum() {
    return newItemNum;
  }

  /**
   * 新規品目登録件数の設定。
   *
   * @param newItemNum 新規品目登録件数
   */
  public void setNewItemNum(int newItemNum) {
    this.newItemNum = newItemNum;
  }

  /**
   * ステータスの取得。
   *
   * @return String ステータス
   */
  public int getApplyStatus() {
    return applyStatus;
  }

  /**
   * ステータスの設定。
   *
   * @param applyStatus ステータス
   */
  public void setApplyStatus(int applyStatus) {
    this.applyStatus = applyStatus;
  }

  /**
   * 申請コメントの取得。
   *
   * @return String 申請コメント
   */
  public String getApplyComments() {
    return applyComments;
  }

  /**
   * 申請コメントの設定。
   *
   * @param applyComments 申請コメント
   */
  public void setApplyComments(String applyComments) {
    this.applyComments = applyComments;
  }

  /**
   * BL申請判断日の取得。
   *
   * @return String BL申請判断日
   */
  public String getBlApplyJudgmentDate() {
    return blApplyJudgmentDate;
  }

  /**
   * BL申請判断日の設定。
   *
   * @param blApplyJudgmentDate BL申請判断日
   */
  public void setBlApplyJudgmentDate(String blApplyJudgmentDate) {
    this.blApplyJudgmentDate = blApplyJudgmentDate;
  }

  /**
   * BLコメントの取得。
   *
   * @return String BLコメント
   */
  public String getBlComments() {
    return blComments;
  }

  /**
   * BLコメントの設定。
   *
   * @param blComments BLコメント
   */
  public void setBlComments(String blComments) {
    this.blComments = blComments;
  }

  /**
   * ハッシュコードを設定する。
   *
   * @return hashCode
   */
  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(applyNo).append(applyDateTime).append(goodsNum)
        .append(setNum).append(joinNum).append(newItemNum).append(applyStatus).append(applyComments)
        .append(blApplyJudgmentDate).append(blComments).toHashCode();
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
    ApplyHistoryCommonModel other = (ApplyHistoryCommonModel) obj;
    return new EqualsBuilder().append(applyNo, other.applyNo)
        .append(applyDateTime, other.applyDateTime).append(goodsNum, other.goodsNum).append(setNum, other.setNum)
        .append(joinNum, other.joinNum).append(newItemNum, other.newItemNum).append(applyStatus, other.applyStatus)
        .append(applyComments, other.applyComments)
        .append(blApplyJudgmentDate, other.blApplyJudgmentDate).append(blComments, other.blComments).isEquals();
  }
}
