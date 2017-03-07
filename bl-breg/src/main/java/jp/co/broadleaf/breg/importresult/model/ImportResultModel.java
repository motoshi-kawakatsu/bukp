//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                               作成担当 : magy
// 作 成 日       2017/02/07   修正内容 : インポート結果:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.importresult.model;

import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMaker;

import java.util.List;

/**
 * インポート結果Model.
 */
public class ImportResultModel {
  /**
   * 商品マスタ(メーカー)
   */
  private List<GoodsMasterWorkMaker> itemWorkList;

  /**
   * 結合マスタ(メーカー)
   */
  private List<JoinMasterWorkMaker> joinMasterWorkList;

  /**
   * マスタ(メーカー)
   */
  private List<SetMasterWorkMaker> setMasterWorkList;

  /**
   * 商品マスタ(メーカー)
   */
  private List<GoodsMasterWorkMaker> itemWorkInvalidList;

  /**
   * 結合マスタ(メーカー)
   */
  private List<JoinMasterWorkMaker> joinMasterWorkInvalidList;

  /**
   * マスタ(メーカー)
   */
  private List<SetMasterWorkMaker> setMasterWorkInvalidList;

  /**
   * 商品マスタ(メーカー)
   */
  private List<GoodsMasterMaker> itemMasterList;

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
   * 商品エラー数
   */
  private int goodsDeleteCount = 0;
  /**
   * セットエラー数
   */
  private int setDeleteCount = 0;
  /**
   * 結合エラー数
   */
  private int joinDeleteCount = 0;

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
   * 商品マスタ(メーカー)の取得。
   *
   * @return セットマスタ(メーカー)
   */
  public List<GoodsMasterWorkMaker> getItemWorkList() {
    return itemWorkList;
  }

  /**
   * 商品マスタ(メーカー)の設定。
   *
   * @param itemWorkList セットマスタ(メーカー)
   */
  public void setItemWorkList(List<GoodsMasterWorkMaker> itemWorkList) {
    this.itemWorkList = itemWorkList;
  }

  /**
   * 結合マスタ(メーカー)の取得。
   *
   * @return 結合マスタ(メーカー)
   */
  public List<JoinMasterWorkMaker> getJoinMasterWorkList() {
    return joinMasterWorkList;
  }

  /**
   * 結合マスタ(メーカー)の設定。
   *
   * @param joinMasterWorkList 結合マスタ(メーカー)
   */

  public void setJoinMasterWorkList(List<JoinMasterWorkMaker> joinMasterWorkList) {
    this.joinMasterWorkList = joinMasterWorkList;
  }

  /**
   * セットマスタ(メーカー)の取得。
   *
   * @return セットマスタ(メーカー)
   */

  public List<SetMasterWorkMaker> getSetMasterWorkList() {
    return setMasterWorkList;
  }

  /**
   * セットマスタ(メーカー)の設定。
   *
   * @param setMasterWorkList セットマスタ(メーカー)
   */

  public void setSetMasterWorkList(List<SetMasterWorkMaker> setMasterWorkList) {
    this.setMasterWorkList = setMasterWorkList;
  }

  /**
   * 【itemMasterList】を取得する。
   * 
   * @return 【itemMasterList】
   */
  public List<GoodsMasterMaker> getItemMasterList() {
    return itemMasterList;
  }

  /**
   * 【itemMasterList】を設定する。
   * 
   * @param itemMasterList 【itemMasterList】
   */
  public void setItemMasterList(List<GoodsMasterMaker> itemMasterList) {
    this.itemMasterList = itemMasterList;
  }

  /**
   * 【itemWorkInvalidList】を取得する。
   * 
   * @return 【itemWorkInvalidList】
   */
  public List<GoodsMasterWorkMaker> getItemWorkInvalidList() {
    return itemWorkInvalidList;
  }

  /**
   * 【itemWorkInvalidList】を設定する。
   * 
   * @param itemWorkInvalidList 【itemWorkInvalidList】
   */
  public void setItemWorkInvalidList(List<GoodsMasterWorkMaker> itemWorkInvalidList) {
    this.itemWorkInvalidList = itemWorkInvalidList;
  }

  /**
   * 【joinMasterWorkInvalidList】を取得する。
   * 
   * @return 【joinMasterWorkInvalidList】
   */
  public List<JoinMasterWorkMaker> getJoinMasterWorkInvalidList() {
    return joinMasterWorkInvalidList;
  }

  /**
   * 【joinMasterWorkInvalidList】を設定する。
   * 
   * @param joinMasterWorkInvalidList 【joinMasterWorkInvalidList】
   */
  public void setJoinMasterWorkInvalidList(List<JoinMasterWorkMaker> joinMasterWorkInvalidList) {
    this.joinMasterWorkInvalidList = joinMasterWorkInvalidList;
  }

  /**
   * 【setMasterWorkInvalidList】を取得する。
   * 
   * @return 【setMasterWorkInvalidList】
   */
  public List<SetMasterWorkMaker> getSetMasterWorkInvalidList() {
    return setMasterWorkInvalidList;
  }

  /**
   * 【setMasterWorkInvalidList】を設定する。
   * 
   * @param setMasterWorkInvalidList 【setMasterWorkInvalidList】
   */
  public void setSetMasterWorkInvalidList(List<SetMasterWorkMaker> setMasterWorkInvalidList) {
    this.setMasterWorkInvalidList = setMasterWorkInvalidList;
  }

  /**
   * <pre>
   * 【goodsDeleteCount】を取得する。
   * </pre>
   *
   * @return 【goodsDeleteCount】
   */
  public int getGoodsDeleteCount() {
    return goodsDeleteCount;
  }

  /**
   * <pre>
   * 【goodsDeleteCount】を設定する。
   * </pre>
   *
   * @param goodsDeleteCount 【goodsDeleteCount】
   */
  public void setGoodsDeleteCount(int goodsDeleteCount) {
    this.goodsDeleteCount = goodsDeleteCount;
  }

  /**
   * <pre>
   * 【setDeleteCount】を取得する。
   * </pre>
   *
   * @return 【setDeleteCount】
   */
  public int getSetDeleteCount() {
    return setDeleteCount;
  }

  /**
   * <pre>
   * 【setDeleteCount】を設定する。
   * </pre>
   *
   * @param setDeleteCount 【setDeleteCount】
   */
  public void setSetDeleteCount(int setDeleteCount) {
    this.setDeleteCount = setDeleteCount;
  }

  /**
   * <pre>
   * 【joinDeleteCount】を取得する。
   * </pre>
   *
   * @return 【joinDeleteCount】
   */
  public int getJoinDeleteCount() {
    return joinDeleteCount;
  }

  /**
   * <pre>
   * 【joinDeleteCount】を設定する。
   * </pre>
   *
   * @param joinDeleteCount 【joinDeleteCount】
   */
  public void setJoinDeleteCount(int joinDeleteCount) {
    this.joinDeleteCount = joinDeleteCount;
  }

}
