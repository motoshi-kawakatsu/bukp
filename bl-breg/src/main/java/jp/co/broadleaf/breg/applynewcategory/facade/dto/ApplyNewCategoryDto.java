//****************************************************************************//
// システム                                    :優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                  作成担当 : 楊蕾蕾
// 作 成 日       2017/02/13   修正内容 : 申請 (新規品目):新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applynewcategory.facade.dto;

import java.sql.Timestamp;

/**
 * 申請 (新規品目)用のクラス。
 */
public class ApplyNewCategoryDto {
  
  /**
   * 申請No
   */
  private int applyNo;
  
  /**
   * 部品メーカーコード
   */
  private int partsMakerCd;
  
  /**
   * 申請日時
   */
  private Timestamp applyDtTime;
  
  /**
   * 申請時コメント
   */
  private String applyComments;
  
  /**
   * BL申請結果区分
   */
  private short blApplyResultsFlg;
  
  /**
   * 前回申請No
   */
  private int preApplyNo;

  /**
   * <pre>
   * 申請Noを取得する。
   * </pre>
   *
   * @return 申請No
   */
  public int getApplyNo() {
    return applyNo;
  }
  
  /**
   * <pre>
   * 申請Noを設定する。
   * </pre>
   *
   * @param applyNo 申請No
   */
  public void setApplyNo(int applyNo) {
    this.applyNo = applyNo;
  }
  
  /**
   * <pre>
   * 部品メーカーコードを取得する。
   * </pre>
   *
   * @return 部品メーカーコード
   */
  public int getPartsMakerCd() {
    return partsMakerCd;
  }

  /**
   * <pre>
   * 部品メーカーコードを設定する。
   * </pre>
   *
   * @param partsMakerCd 部品メーカーコード
   */
  public void setPartsMakerCd(int partsMakerCd) {
    this.partsMakerCd = partsMakerCd;
  }
  
  /**
   * <pre>
   * 申請日時を取得する。
   * </pre>
   *
   * @return 申請日時
   */
  public Timestamp getApplyDtTime() {
    return applyDtTime;
  }
  
  /**
   * <pre>
   * 申請日時を設定する。
   * </pre>
   *
   * @param applyDtTime 申請日時
   */
  public void setApplyDtTime(Timestamp applyDtTime) {
    this.applyDtTime = applyDtTime;
  }
  
  /**
   * <pre>
   * 申請時コメントを取得する。
   * </pre>
   *
   * @return 申請時コメント
   */
  public String getApplyComments() {
    return applyComments;
  }
  
  /**
   * <pre>
   * 申請時コメントを設定する。
   * </pre>
   *
   * @param applyComments 申請時コメント
   */
  public void setApplyComments(String applyComments) {
    this.applyComments = applyComments;
  }
  
  /**
   * <pre>
   * BL申請結果区分を取得する。
   * </pre>
   *
   * @return BL申請結果区分
   */
  public short getBlApplyResultsFlg() {
    return blApplyResultsFlg;
  }

  /**
   * <pre>
   * BL申請結果区分を設定する。
   * </pre>
   *
   * @param blApplyResultsFlg BL申請結果区分
   */
  public void setBlApplyResultsFlg(short blApplyResultsFlg) {
    this.blApplyResultsFlg = blApplyResultsFlg;
  }

  /**
   * <pre>
   * 前回申請Noを取得する。
   * </pre>
   *
   * @return 前回申請No
   */
  public int getPreApplyNo() {
    return preApplyNo;
  }

  /**
   * <pre>
   * 前回申請Noを設定する。
   * </pre>
   *
   * @param preApplyNo 前回申請No
   */
  public void setPreApplyNo(int preApplyNo) {
    this.preApplyNo = preApplyNo;
  }
  
}
