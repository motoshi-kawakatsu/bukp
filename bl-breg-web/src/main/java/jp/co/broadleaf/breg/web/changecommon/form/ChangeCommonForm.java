//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:陳　雪涛
// 作成日:2017/02/16    修正内容:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.changecommon.form;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.framework.validation.FormValidator;
import jp.co.broadleaf.framework.validation.FormValidatorFactory;

/**
 * 検索置換Formのクラス。
 */
public class ChangeCommonForm {
  /**
   * 検索項目
   */
  private String searchItem;

  /**
   * 検索値
   */
  private String searchValue;

  /**
   * 検索結果
   */
  private String searchCount;

  /**
   * guideValue
   */
  private String guideValue;
  /**
   * sessionValue
   */
  private String sessionValue;
  /**
   * コード
   */
  private String code;

  /**
   * ガイドタイプ（0：商品中分類，1:純正品番,2:優良品番）
   */
  private String guideType;

  /**
   * チェック項目値
   */
  private String checkValue;

  /**
   * チェック項目名
   */
  private String checkItem;
  /**
   * 必要フラグ
   */
  private boolean mustFlag;

  /**
   * <pre>
   * 【searchItem】を取得する。
   * </pre>
   *
   * @return 【searchItem】
   */
  public String getSearchItem() {
    return searchItem;
  }

  /**
   * <pre>
   * 【searchItem】を設定する。
   * </pre>
   *
   * @param searchItem 【searchItem】
   */
  public void setSearchItem(String searchItem) {
    this.searchItem = searchItem;
  }

  /**
   * <pre>
   * 【searchValue】を取得する。
   * </pre>
   *
   * @return 【searchValue】
   */
  public String getSearchValue() {
    return searchValue;
  }

  /**
   * <pre>
   * 【searchValue】を設定する。
   * </pre>
   *
   * @param searchValue 【searchValue】
   */
  public void setSearchValue(String searchValue) {
    this.searchValue = searchValue;
  }

  /**
   * <pre>
   * 【searchCount】を取得する。
   * </pre>
   *
   * @return 【searchCount】
   */
  public String getSearchCount() {
    return searchCount;
  }

  /**
   * <pre>
   * 【searchCount】を設定する。
   * </pre>
   *
   * @param searchCount 【searchCount】
   */
  public void setSearchCount(String searchCount) {
    this.searchCount = searchCount;
  }

  /**
   * <pre>
   * 【guideValue】を取得する。
   * </pre>
   *
   * @return 【guideValue】
   */
  public String getGuideValue() {
    return guideValue;
  }

  /**
   * <pre>
   * 【guideValue】を設定する。
   * </pre>
   *
   * @param guideValue 【guideValue】
   */
  public void setGuideValue(String guideValue) {
    this.guideValue = guideValue;
  }

  /**
   * <pre>
   * 【sessionValue】を取得する。
   * </pre>
   *
   * @return 【sessionValue】
   */
  public String getSessionValue() {
    return sessionValue;
  }

  /**
   * <pre>
   * 【sessionValue】を設定する。
   * </pre>
   *
   * @param sessionValue 【sessionValue】
   */
  public void setSessionValue(String sessionValue) {
    this.sessionValue = sessionValue;
  }

  /**
   * <pre>
   * 【code】を取得する。
   * </pre>
   *
   * @return 【code】
   */
  public String getCode() {
    return code;
  }

  /**
   * <pre>
   * 【code】を設定する。
   * </pre>
   *
   * @param code 【code】
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * <pre>
   * 【guideType】を取得する。
   * </pre>
   *
   * @return 【guideType】
   */
  public String getGuideType() {
    return guideType;
  }

  /**
   * <pre>
   * 【guideType】を設定する。
   * </pre>
   *
   * @param guideType 【guideType】
   */
  public void setGuideType(String guideType) {
    this.guideType = guideType;
  }

  /**
   * <pre>
   * 【checkValue】を取得する。
   * </pre>
   *
   * @return 【checkValue】
   */
  public String getCheckValue() {
    return checkValue;
  }

  /**
   * <pre>
   * 【checkValue】を設定する。
   * </pre>
   *
   * @param checkValue 【checkValue】
   */
  public void setCheckValue(String checkValue) {
    this.checkValue = checkValue;
  }

  /**
   * <pre>
   * 【checkItem】を取得する。
   * </pre>
   *
   * @return 【checkItem】
   */
  public String getCheckItem() {
    return checkItem;
  }

  /**
   * <pre>
   * 【checkItem】を設定する。
   * </pre>
   *
   * @param checkItem 【checkItem】
   */
  public void setCheckItem(String checkItem) {
    this.checkItem = checkItem;
  }

  /**
   * <pre>
   * 【mustFlag】を取得する。
   * </pre>
   *
   * @return 【mustFlag】
   */
  public boolean getMustFlag() {
    return mustFlag;
  }

  /**
   * <pre>
   * 【mustFlag】を設定する。
   * </pre>
   *
   * @param mustFlag 【mustFlag】
   */
  public void setMustFlag(boolean mustFlag) {
    this.mustFlag = mustFlag;
  }

  /**
   * <pre>
   * 画面入力チェックを実行する。
   * </pre>
   */
  public void validate() {
    // 画面入力チェック
    FormValidator validator = FormValidatorFactory.create();
    switch (checkItem) {
    case "優良品番":
      validator.field("", checkValue).maxLength(24, BregMessageCodes.E00104, checkItem, "24");
      break;
    case "品名（半角）":
      validator.field("", checkValue).maxLength(60, BregMessageCodes.E00104, checkItem, "60");
      break;
    case "品名（全角）":
      validator.field("", checkValue).maxLength(60, BregMessageCodes.E00104, checkItem, "60");
      break;
    case "JAN":
      validator.field("", checkValue).maxLength(13, BregMessageCodes.E00104, checkItem, "13");
      break;
    case "装備":
      validator.field("", checkValue).maxLength(60, BregMessageCodes.E00104, checkItem, "60");
      break;
    case "規格/特記":
      validator.field("", checkValue).maxLength(80, BregMessageCodes.E00104, checkItem, "80");
      break;
    case "規格/特記（一般）":
      validator.field("", checkValue).maxLength(80, BregMessageCodes.E00104, checkItem, "80");
      break;
    case "削除理由":
      validator.field("", checkValue).maxLength(80, BregMessageCodes.E00104, checkItem, "80");
      break;
    case "商品詳細":
      validator.field("", checkValue).maxLength(512, BregMessageCodes.E00104, checkItem, "512");
      break;
    case "商品詳細（一般）":
      validator.field("", checkValue).maxLength(512, BregMessageCodes.E00104, checkItem, "512");
      break;
    case "長さ":
      validator.field("", checkValue).maxLength(6, BregMessageCodes.E00104, checkItem, "6");
      break;
    case "幅":
      validator.field("", checkValue).maxLength(6, BregMessageCodes.E00104, checkItem, "6");
      break;
    case "高さ":
      validator.field("", checkValue).maxLength(6, BregMessageCodes.E00104, checkItem, "6");
      break;
    case "梱包（長さ）":
      validator.field("", checkValue).maxLength(6, BregMessageCodes.E00104, checkItem, "6");
      break;
    case "梱包（幅）":
      validator.field("", checkValue).maxLength(6, BregMessageCodes.E00104, checkItem, "6");
      break;
    case "梱包（高さ）":
      validator.field("", checkValue).maxLength(6, BregMessageCodes.E00104, checkItem, "6");
      break;
    case "重量":
      validator.field("", checkValue).maxLength(6, BregMessageCodes.E00104, checkItem, "6");
      break;
    case "URL1":
      validator.field("", checkValue).maxLength(512, BregMessageCodes.E00104, checkItem, "512");
      break;
    case "URL2":
      validator.field("", checkValue).maxLength(512, BregMessageCodes.E00104, checkItem, "512");
      break;
    case "URL3":
      validator.field("", checkValue).maxLength(512, BregMessageCodes.E00104, checkItem, "512");
      break;
    case "表示順位":
      validator.field("", checkValue).maxLength(4, BregMessageCodes.E00104, checkItem, "4");
      break;
    case "セット品名（半角）":
      validator.field("", checkValue).maxLength(60, BregMessageCodes.E00104, checkItem, "60");
      break;
    case "セット品名（全角）":
      validator.field("", checkValue).maxLength(60, BregMessageCodes.E00104, checkItem, "60");
      break;
    case "優良品名":
      validator.field("", checkValue).maxLength(60, BregMessageCodes.E00104, checkItem, "60");
      break;
    default:
      break;
    }
    validator.validate();
  }

  /**
   * <pre>
   * 画面入力チェックを実行する。
   * </pre>
   */
  public void validateMust(){
    FormValidator validator = FormValidatorFactory.create();
    if(mustFlag){
      validator.field("", checkValue).required(BregMessageCodes.E00101, checkItem);
    }
    validator.validate();
  }

}
