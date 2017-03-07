//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                               作成担当 : magy
// 作 成 日       2017/02/07   修正内容 :インポート結果:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.importresult.facade.dto;

import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.common.enums.ImportTypeEnum;

/**
 * インポート結果のDtoのクラス。
 */
public class ImportResultDto {

  /**
   * ログインID
   */
  private String loginId;

  /**
   * メーカーコード
   */
  private int makerCode;

  /**
   * インポート区分
   */
  private ImportKbnEnum importKbnEnum;

  /**
   * 商品インポート類型
   */
  private ImportTypeEnum goodsImportType;

  /**
   * セットインポート類型
   */
  private ImportTypeEnum setImportType;

  /**
   * 結合インポート類型
   */
  private ImportTypeEnum joinImportType;

  /**
   * 商品エラー数
   */
  private int goodsErrorCount = 0;
  /**
   * セットエラー数
   */
  private int setErrorCount = 0;
  /**
   * 結合エラー数
   */
  private int joinErrorCount = 0;

  /**
   * 商品数
   */
  private int goodsCount = 0;
  /**
   * セット数
   */
  private int setCount = 0;
  /**
   * 結合数
   */
  private int joinCount = 0;

  /**
   * 商品情報
   */
  private int[] goodsInfo;
  /**
   * セット情報
   */
  private int[] setInfo;
  /**
   * 結合情報
   */
  private int[] joinInfo;
  /**
   * インポート類型
   */
  private int importType;

  /**
   * 【goodsInfo】を取得する。
   * 
   * @return 【goodsInfo】
   */
  public int[] getGoodsInfo() {
    return goodsInfo;
  }

  /**
   * 【goodsInfo】を設定する。
   * 
   * @param goodsInfo 【goodsInfo】
   */
  public void setGoodsInfo(int[] goodsInfo) {
    this.goodsInfo = goodsInfo;
  }

  /**
   * 【setInfo】を取得する。
   * 
   * @return 【setInfo】
   */
  public int[] getSetInfo() {
    return setInfo;
  }

  /**
   * 【setInfo】を設定する。
   * 
   * @param setInfo 【setInfo】
   */
  public void setSetInfo(int[] setInfo) {
    this.setInfo = setInfo;
  }

  /**
   * 【joinInfo】を取得する。
   * 
   * @return 【joinInfo】
   */
  public int[] getJoinInfo() {
    return joinInfo;
  }

  /**
   * 【joinInfo】を設定する。
   * 
   * @param joinInfo 【joinInfo】
   */
  public void setJoinInfo(int[] joinInfo) {
    this.joinInfo = joinInfo;
  }

  /**
   * 【goodsCount】を取得する。
   * 
   * @return 【goodsCount】
   */
  public int getGoodsCount() {
    return goodsCount;
  }

  /**
   * 【goodsCount】を設定する。
   * 
   * @param goodsCount 【goodsCount】
   */
  public void setGoodsCount(int goodsCount) {
    this.goodsCount = goodsCount;
  }

  /**
   * 【setCount】を取得する。
   * 
   * @return 【setCount】
   */
  public int getSetCount() {
    return setCount;
  }

  /**
   * 【setCount】を設定する。
   * 
   * @param setCount 【setCount】
   */
  public void setSetCount(int setCount) {
    this.setCount = setCount;
  }

  /**
   * 【joinCount】を取得する。
   * 
   * @return 【joinCount】
   */
  public int getJoinCount() {
    return joinCount;
  }

  /**
   * 【joinCount】を設定する。
   * 
   * @param joinCount 【joinCount】
   */
  public void setJoinCount(int joinCount) {
    this.joinCount = joinCount;
  }

  /**
   * 【goodsImportType】を取得する。
   * 
   * @return 【goodsImportType】
   */
  public ImportTypeEnum getGoodsImportType() {
    return goodsImportType;
  }

  /**
   * 【goodsImportType】を設定する。
   * 
   * @param goodsImportType 【goodsImportType】
   */
  public void setGoodsImportType(ImportTypeEnum goodsImportType) {
    this.goodsImportType = goodsImportType;
  }

  /**
   * 【setImportType】を取得する。
   * 
   * @return 【setImportType】
   */
  public ImportTypeEnum getSetImportType() {
    return setImportType;
  }

  /**
   * 【setImportType】を設定する。
   * 
   * @param setImportType 【setImportType】
   */
  public void setSetImportType(ImportTypeEnum setImportType) {
    this.setImportType = setImportType;
  }

  /**
   * 【joinImportType】を取得する。
   * 
   * @return 【joinImportType】
   */
  public ImportTypeEnum getJoinImportType() {
    return joinImportType;
  }

  /**
   * 【joinImportType】を設定する。
   * 
   * @param joinImportType 【joinImportType】
   */
  public void setJoinImportType(ImportTypeEnum joinImportType) {
    this.joinImportType = joinImportType;
  }

  /**
   * 【importKbnEnum】を取得する。
   * 
   * @return 【importKbnEnum】
   */
  public ImportKbnEnum getImportKbnEnum() {
    return importKbnEnum;
  }

  /**
   * 【importKbnEnum】を設定する。
   * 
   * @param importKbnEnum 【importKbnEnum】
   */
  public void setImportKbnEnum(ImportKbnEnum importKbnEnum) {
    this.importKbnEnum = importKbnEnum;
  }

  /**
   * 【loginId】を取得する。
   * 
   * @return 【loginId】
   */
  public String getLoginId() {
    return loginId;
  }

  /**
   * 【loginId】を設定する。
   * 
   * @param loginId 【loginId】
   */
  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  /**
   * 【makerCode】を取得する。
   * 
   * @return 【makerCode】
   */
  public int getMakerCode() {
    return makerCode;
  }

  /**
   * 【makerCode】を設定する。
   * 
   * @param makerCode 【makerCode】
   */
  public void setMakerCode(int makerCode) {
    this.makerCode = makerCode;
  }

  /**
   * 【goodsErrorCount】を取得する。
   * 
   * @return 【goodsErrorCount】
   */
  public int getGoodsErrorCount() {
    return goodsErrorCount;
  }

  /**
   * 【goodsErrorCount】を設定する。
   * 
   * @param goodsErrorCount 【goodsErrorCount】
   */
  public void setGoodsErrorCount(int goodsErrorCount) {
    this.goodsErrorCount = goodsErrorCount;
  }

  /**
   * 【setErrorCount】を取得する。
   * 
   * @return 【setErrorCount】
   */
  public int getSetErrorCount() {
    return setErrorCount;
  }

  /**
   * 【setErrorCount】を設定する。
   * 
   * @param setErrorCount 【setErrorCount】
   */
  public void setSetErrorCount(int setErrorCount) {
    this.setErrorCount = setErrorCount;
  }

  /**
   * 【joinErrorCount】を取得する。
   * 
   * @return 【joinErrorCount】
   */
  public int getJoinErrorCount() {
    return joinErrorCount;
  }

  /**
   * 【joinErrorCount】を設定する。
   * 
   * @param joinErrorCount 【joinErrorCount】
   */
  public void setJoinErrorCount(int joinErrorCount) {
    this.joinErrorCount = joinErrorCount;
  }

  /**
   * 【importType】を取得する。
   * 
   * @return 【importType】
   */
  public int getImportType() {
    return importType;
  }

  /**
   * 【importType】を設定する。
   * 
   * @param importType 【importType】
   */
  public void setImportType(int importType) {
    this.importType = importType;
  }

}
