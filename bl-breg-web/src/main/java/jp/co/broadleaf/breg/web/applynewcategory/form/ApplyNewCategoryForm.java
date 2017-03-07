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
package jp.co.broadleaf.breg.web.applynewcategory.form;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.framework.validation.FormValidator;
import jp.co.broadleaf.framework.validation.FormValidatorFactory;

import java.io.Serializable;

/**
 * <pre>
 * 申請 (新規品目)用のFormクラス
 * </pre>
 */
public class ApplyNewCategoryForm implements Serializable {
  
  /**申請 (新規品目) */
  private static final long serialVersionUID = 1L;

  /**
   * 申請No
   */
  private int applyNo;
  
  /**
   * 申請日時
   */
  private String applyDtTime;
  
  /**
   * 申請時コメント
   */
  private String applyComments;
  
  /**
   * 申請Mode
   */
  private String applyState;
  
  /**
   * 初期申請No
   */
  private int oldApplyNo;
  
  /**
   * 初期BL申請結果区分
   */
  private short oldBlApplyResultsFlg;
  
  /**
   * 初期部品メーカーコード
   */
  private int oldMakerCd;
  
  /**
   * 初期申請時コメント
   */
  private String oldApplyComments;

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
   * 申請日時を取得する。
   * </pre>
   *
   * @return 申請日時
   */
  public String getApplyDtTime() {
    return applyDtTime;
  }

  /**
   * <pre>
   * 申請日時を設定する。
   * </pre>
   *
   * @param applyDtTime 申請日時
   */
  public void setApplyDtTime(String applyDtTime) {
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
   * 申請Modeを取得する。
   * </pre>
   *
   * @return 申請Mode
   */
  public String getApplyState() {
    return applyState;
  }

  /**
   * <pre>
   * 申請Modeを設定する。
   * </pre>
   *
   * @param applyState 申請Mode
   */
  public void setApplyState(String applyState) {
    this.applyState = applyState;
  }

  /**
   * <pre>
   * 初期申請Noを取得する。
   * </pre>
   *
   * @return 初期申請No
   */
  public int getOldApplyNo() {
    return oldApplyNo;
  }

  /**
   * <pre>
   * 初期申請Noを設定する。
   * </pre>
   *
   * @param oldApplyNo 初期申請No
   */
  public void setOldApplyNo(int oldApplyNo) {
    this.oldApplyNo = oldApplyNo;
  }

  /**
   * <pre>
   * 初期BL申請結果区分を取得する。
   * </pre>
   *
   * @return 初期BL申請結果区分
   */
  public short getOldBlApplyResultsFlg() {
    return oldBlApplyResultsFlg;
  }

  /**
   * <pre>
   * 初期BL申請結果区分を設定する。
   * </pre>
   *
   * @param oldBlApplyResultsFlg 初期BL申請結果区分
   */
  public void setOldBlApplyResultsFlg(short oldBlApplyResultsFlg) {
    this.oldBlApplyResultsFlg = oldBlApplyResultsFlg;
  }

  /**
   * <pre>
   * 初期部品メーカーコードを取得する。
   * </pre>
   *
   * @return 初期部品メーカーコード
   */
  public int getOldMakerCd() {
    return oldMakerCd;
  }

  /**
   * <pre>
   * 初期部品メーカーコードを設定する。
   * </pre>
   *
   * @param oldMakerCd 初期部品メーカーコード
   */
  public void setOldMakerCd(int oldMakerCd) {
    this.oldMakerCd = oldMakerCd;
  }

  /**
   * <pre>
   * 初期申請時コメントを取得する。
   * </pre>
   *
   * @return 初期申請時コメント
   */
  public String getOldApplyComments() {
    return oldApplyComments;
  }

  /**
   * <pre>
   * 初期申請時コメントを設定する。
   * </pre>
   *
   * @param oldApplyComments 初期申請時コメント
   */
  public void setOldApplyComments(String oldApplyComments) {
    this.oldApplyComments = oldApplyComments;
  }

  /**
   * 画面入力チェック
   */
  public void validate(){
    // 画面入力チェック
    FormValidator validator = FormValidatorFactory.create();
    validator.field("applyComments", applyComments).required(BregMessageCodes.E00001, "依頼内容").maxLength(512,
        BregMessageCodes.E00003, "依頼内容", "512");
    validator.validate();
  }
}
