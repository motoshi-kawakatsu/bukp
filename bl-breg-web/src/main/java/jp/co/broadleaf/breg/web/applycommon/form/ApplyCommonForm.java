//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : xuck
// 作 成 日       2017/02/07   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.applycommon.form;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.framework.validation.FormValidator;
import jp.co.broadleaf.framework.validation.FormValidatorFactory;

/**
 * ApplyCommonFormのクラス。
 */
public class ApplyCommonForm {
  
  /**
   * 申請時コメント
   */
  private String applyComments;

  /**
   * <pre>
   * 【applyComments】を取得する。
   * </pre>
   *
   * @return 【applyComments】
   */
  public String getApplyComments() {
    return applyComments;
  }

  /**
   * <pre>
   * 【applyComments】を設定する。
   * </pre>
   *
   * @param applyComments 【applyComments】
   */
  public void setApplyComments(String applyComments) {
    this.applyComments = applyComments;
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
