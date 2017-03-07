//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 王　天コン
// 作 成 日       2016/02/09   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.joinlist.form;

import jp.co.broadleaf.breg.joinlist.facade.dto.JoinGridDto;

import java.io.Serializable;
import java.util.List;

/**
 * 結合Formのクラスのクラス。
 */
public class JoinListForm implements Serializable {

  /** serialVersionUID */
  private static final long serialVersionUID = 1L;
  /**
   * BLコード
   */
  private String tbsPartsCode;
  /**
   * 純正品番
   */
  private String joinSourPartsNoWithH;
  /**
   * 優良品番
   */
  private String joinDestPartsNo;
  /**
   * 申請状態
   */
  private String applyCondition;
  /**
   * 処理区分
   */
  private String manageKbn;
  /**
   * エラー区分
   */
  private String errorFlg;
  /**
   * セレクトコード
   */
  private String prmSetDtlNo1;
  /**
   * カーコード
   */
  private String joinSourceMakerCode;
  /**
   * 部品メーカーコード
   */
  private String partsMakerCd;

  /**
   * 適用日start
   */
  private String startTimeStart;
  /**
   * 適用日END
   */
  private String startTimeEnd;
  /**
   * 分類コード
   */
  private String goodsMGroup;
  /**
   * 種別コード
   */
  private String prmSetDtlNo2;
  /**
   * 作成日START
   */
  private String insDtTimeStart;
  /**
   * 作成日END
   */
  private String insDtTimeEnd;
  /**
   * 規格/特記
   */
  private String joinSpecialNote;
  /**
   * 削除依頼区分
   */
  private String deleteFlg;
  /**
   * 更新日START
   */
  private String updDtTimeStart;
  /**
   * 更新日END
   */
  private String updDtTimeEnd;

  /**
   * 規格/特記(一般)
   */
  private String primePartsSpecialNoteC;

  /**
   * 表示順
   */
  private int order;

  /**
   * mode
   */
  private Integer mode;

  /**
   * 1ページの件数
   */
  public String skipRows;
  /**
   * 全件数
   */
  public String maxRows;
  /**
   * pageNo
   */
  public String pageNo;
  /**
   * 結合表示順位
   */
  private String joinDispOrder;
  /**
   * 結合一覧画面グリッドのリスト
   */
  private List<JoinGridDto> joinGridList;
  /**
   * インポート区分
   */
  public Integer importKbn;

  /**
   * <pre>
   * 【インポート区分】を取得する。
   * </pre>
   *
   * @return 【インポート区分】
   */
  public Integer getImportKbn() {
    return importKbn;
  }

  /**
   * <pre>
   * 【インポート区分】を設定する。
   * </pre>
   *
   * @param importKbn 【インポート区分】
   */
  public void setImportKbn(Integer importKbn) {
    this.importKbn = importKbn;
  }

  /**
   * 純正品番の取得。
   *
   * @return String 純正品番
   */
  public String getJoinSourPartsNoWithH() {
    return joinSourPartsNoWithH;
  }

  /**
   * 純正品番の設定。
   *
   * @param joinSourPartsNoWithH 純正品番
   */
  public void setJoinSourPartsNoWithH(String joinSourPartsNoWithH) {
    this.joinSourPartsNoWithH = joinSourPartsNoWithH;
  }

  /**
   * BLコードの取得。
   *
   * @return String BLコード
   */
  public String getTbsPartsCode() {
    return tbsPartsCode;
  }

  /**
   * BLコードの設定。
   *
   * @param tbsPartsCode BLコード
   */
  public void setTbsPartsCode(String tbsPartsCode) {
    this.tbsPartsCode = tbsPartsCode;
  }

  /**
   * 優良品番の取得。
   *
   * @return String 優良品番
   */
  public String getJoinDestPartsNo() {
    return joinDestPartsNo;
  }

  /**
   * 優良品番の設定。
   *
   * @param joinDestPartsNo 優良品番
   */
  public void setJoinDestPartsNo(String joinDestPartsNo) {
    this.joinDestPartsNo = joinDestPartsNo;
  }

  /**
   * 申請状態の取得。
   *
   * @return String 申請状態
   */
  public String getApplyCondition() {
    return applyCondition;
  }

  /**
   * 申請状態の設定。
   *
   * @param applyCondition 申請状態
   */
  public void setApplyCondition(String applyCondition) {
    this.applyCondition = applyCondition;
  }

  /**
   * 処理区分の取得。
   *
   * @return String 処理区分
   */
  public String getManageKbn() {
    return manageKbn;
  }

  /**
   * 処理区分の設定。
   *
   * @param manageKbn 処理区分
   */
  public void setManageKbn(String manageKbn) {
    this.manageKbn = manageKbn;
  }

  /**
   * エラー区分の取得。
   *
   * @return String エラー区分
   */
  public String getErrorFlg() {
    return errorFlg;
  }

  /**
   * エラー区分の設定。
   *
   * @param errorFlg エラー区分
   */
  public void setErrorFlg(String errorFlg) {
    this.errorFlg = errorFlg;
  }

  /**
   * セレクトコードの取得。
   *
   * @return String セレクトコード
   */
  public String getPrmSetDtlNo1() {
    return prmSetDtlNo1;
  }

  /**
   * セレクトコードの設定。
   *
   * @param prmSetDtlNo1 セレクトコード
   */
  public void setPrmSetDtlNo1(String prmSetDtlNo1) {
    this.prmSetDtlNo1 = prmSetDtlNo1;
  }

  /**
   * 層別
   *
   * @return String 層別
   */
  public String getJoinSourceMakerCode() {
    return joinSourceMakerCode;
  }

  /**
   * 層別の設定。
   *
   * @param joinSourceMakerCode 層別
   */
  public void setJoinSourceMakerCode(String joinSourceMakerCode) {
    this.joinSourceMakerCode = joinSourceMakerCode;
  }

  /**
   * 適用日startの取得。
   *
   * @return String 適用日start
   */
  public String getStartTimeStart() {
    return startTimeStart;
  }

  /**
   * 適用日startの設定。
   *
   * @param startTimeStart 適用日start
   */
  public void setStartTimeStart(String startTimeStart) {
    this.startTimeStart = startTimeStart;
  }

  /**
   * 適用日ENDの取得。
   *
   * @return String 適用日END
   */
  public String getStartTimeEnd() {
    return startTimeEnd;
  }

  /**
   * 適用日ENDの設定。
   *
   * @param startTimeEnd 適用日END
   */
  public void setStartTimeEnd(String startTimeEnd) {
    this.startTimeEnd = startTimeEnd;
  }

  /**
   * 分類コードの取得。
   *
   * @return String 分類コード
   */
  public String getGoodsMGroup() {
    return goodsMGroup;
  }

  /**
   * 分類コードの設定。
   *
   * @param goodsMGroup 分類コード
   */
  public void setGoodsMGroup(String goodsMGroup) {
    this.goodsMGroup = goodsMGroup;
  }

  /**
   * 種別コードの取得。
   *
   * @return String 種別コード
   */
  public String getPrmSetDtlNo2() {
    return prmSetDtlNo2;
  }

  /**
   * 種別コードの設定。
   *
   * @param prmSetDtlNo2 種別コード
   */
  public void setPrmSetDtlNo2(String prmSetDtlNo2) {
    this.prmSetDtlNo2 = prmSetDtlNo2;
  }

  /**
   * 作成日STARTの取得。
   *
   * @return String 作成日START
   */
  public String getInsDtTimeStart() {
    return insDtTimeStart;
  }

  /**
   * 作成日STARTの設定。
   *
   * @param insDtTimeStart 作成日START
   */
  public void setInsDtTimeStart(String insDtTimeStart) {
    this.insDtTimeStart = insDtTimeStart;
  }

  /**
   * 作成日ENDの取得。
   *
   * @return String 作成日END
   */
  public String getInsDtTimeEnd() {
    return insDtTimeEnd;
  }

  /**
   * 作成日ENDの設定。
   *
   * @param insDtTimeEnd 作成日END
   */
  public void setInsDtTimeEnd(String insDtTimeEnd) {
    this.insDtTimeEnd = insDtTimeEnd;
  }

  /**
   * 規格/特記の取得。
   *
   * @return String 規格/特記
   */
  public String getJoinSpecialNote() {
    return joinSpecialNote;
  }

  /**
   * 規格/特記の設定。
   *
   * @param joinSpecialNote 規格/特記
   */
  public void setJoinSpecialNote(String joinSpecialNote) {
    this.joinSpecialNote = joinSpecialNote;
  }

  /**
   * 更新日STARTの取得。
   *
   * @return String 更新日START
   */
  public String getUpdDtTimeStart() {
    return updDtTimeStart;
  }

  /**
   * 更新日STARTの設定。
   *
   * @param updDtTimeStart 更新日START
   */
  public void setUpdDtTimeStart(String updDtTimeStart) {
    this.updDtTimeStart = updDtTimeStart;
  }

  /**
   * 更新日ENDの取得。
   *
   * @return String 更新日END
   */
  public String getUpdDtTimeEnd() {
    return updDtTimeEnd;
  }

  /**
   * 更新日ENDの設定。
   *
   * @param updDtTimeEnd 更新日END
   */
  public void setUpdDtTimeEnd(String updDtTimeEnd) {
    this.updDtTimeEnd = updDtTimeEnd;
  }

  /**
   * 削除依頼区分の取得。
   *
   * @return String 削除依頼区分
   */
  public String getDeleteFlg() {
    return deleteFlg;
  }

  /**
   * 削除依頼区分の設定。
   *
   * @param deleteFlg 削除依頼区分
   */
  public void setDeleteFlg(String deleteFlg) {
    this.deleteFlg = deleteFlg;
  }

  /**
   * 規格/特記(一般)の取得。
   *
   * @return String 規格/特記(一般)
   */
  public String getPrimePartsSpecialNoteC() {
    return primePartsSpecialNoteC;
  }

  /**
   * 規格/特記(一般)の設定。
   *
   * @param primePartsSpecialNoteC 規格/特記(一般)
   */
  public void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC) {
    this.primePartsSpecialNoteC = primePartsSpecialNoteC;
  }

  /**
   * 表示順の取得。
   *
   * @return int 表示順
   */
  public int getOrder() {
    return order;
  }

  /**
   * 表示順の設定。
   *
   * @param order 表示順
   */
  public void setOrder(int order) {
    this.order = order;
  }

  /**
   * modeの取得。
   *
   * @return Integer mode
   */
  public Integer getMode() {
    return mode;
  }

  /**
   * modeの設定。
   *
   * @param mode mode
   */
  public void setMode(Integer mode) {
    this.mode = mode;
  }

  /**
   * グリッドのリストの取得。
   *
   * @return リスト グリッドのリスト
   */
  public List<JoinGridDto> getJoinGridList() {
    return joinGridList;
  }

  /**
   * グリッドのリストの設定。
   *
   * @param joinGridList グリッドのリスト
   */
  public void setJoinGridList(List<JoinGridDto> joinGridList) {
    this.joinGridList = joinGridList;
  }

  /**
   * <pre>
   * 1ページの件数を取得する。
   * </pre>
   *
   * @return 【skipRows】
   */
  public String getSkipRows() {
    return skipRows;
  }

  /**
   * <pre>
   * 1ページの件数を設定する。
   * </pre>
   *
   * @param skipRows 【skipRows】
   */
  public void setSkipRows(String skipRows) {
    this.skipRows = skipRows;
  }

  /**
   * <pre>
   * 全件数を取得する。
   * </pre>
   *
   * @return 【maxRows】
   */
  public String getMaxRows() {
    return maxRows;
  }

  /**
   * <pre>
   * 全件数を設定する。
   * </pre>
   *
   * @param maxRows 【maxRows】
   */
  public void setMaxRows(String maxRows) {
    this.maxRows = maxRows;
  }

  /**
   * <pre>
   * 【partsMakerCd】を取得する。
   * </pre>
   *
   * @return 【partsMakerCd】
   */
  public String getPartsMakerCd() {
    return partsMakerCd;
  }

  /**
   * <pre>
   * 【partsMakerCd】を設定する。
   * </pre>
   *
   * @param partsMakerCd 【partsMakerCd】
   */
  public void setPartsMakerCd(String partsMakerCd) {
    this.partsMakerCd = partsMakerCd;
  }

  /**
   * <pre>
   * 【joinDispOrder】を取得する。
   * </pre>
   *
   * @return 【joinDispOrder】
   */
  public String getJoinDispOrder() {
    return joinDispOrder;
  }

  /**
   * <pre>
   * 【joinDispOrder】を設定する。
   * </pre>
   *
   * @param joinDispOrder 【joinDispOrder】
   */
  public void setJoinDispOrder(String joinDispOrder) {
    this.joinDispOrder = joinDispOrder;
  }

  /**
   * <pre>
  * 【pageNo】を取得する。
   * </pre>
   *
   * @return 【pageNo】
   */
  public String getPageNo() {
    return pageNo;
  }

  /**
   * <pre>
  * 【pageNo】を設定する。
   * </pre>
   *
   * @param pageNo 【pageNo】
   */
  public void setPageNo(String pageNo) {
    this.pageNo = pageNo;
  }

}
