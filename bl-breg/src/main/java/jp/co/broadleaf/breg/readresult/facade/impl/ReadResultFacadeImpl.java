//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//(c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号       作成担当 : 羅　進博
// 作 成 日       2017/02/08   修正内容 : 取込完了：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.readresult.facade.impl;

import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.readresult.facade.ReadResultFacade;
import jp.co.broadleaf.breg.readresult.service.ReadResultService;
import javax.annotation.Resource;

/**
 * <pre>
 * 取込完了Facadeクラス.
 * </pre>
 */
public class ReadResultFacadeImpl implements ReadResultFacade {
  /** readResultService*/
  private ReadResultService readResultService;
  
  /**
   * <pre>
   * 商品の情報をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @return goodsInfo Integer[]
   */
  @Override
  public int[] getGoodsInfo(int loginUserCd) {
    int[] goodsInfo = new int[5];
    int goodsAdd = readResultService.getGoodsInfo(loginUserCd, ManageKbnEnum.Add.getValue());
    int goodsUpdate = readResultService.getGoodsInfo(loginUserCd, ManageKbnEnum.Update.getValue());
    int goodsDelete = readResultService.getGoodsInfo(loginUserCd, ManageKbnEnum.Delete.getValue());
    goodsInfo[0] = goodsAdd + goodsUpdate + goodsDelete;
    goodsInfo[1] = goodsAdd;
    goodsInfo[2] = goodsUpdate;
    goodsInfo[3] = goodsDelete;
    goodsInfo[4] = 0;
    return goodsInfo;
  }
  
  /**
   * <pre>
   * セットの情報をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @return setInfo Integer[]
   */
  @Override
  public int[] getSetInfo(int loginUserCd) {
    int[] setInfo = new int[5];
    int setAdd = readResultService.getSetInfo(loginUserCd, ManageKbnEnum.Add.getValue());
    int setUpdate = readResultService.getSetInfo(loginUserCd, ManageKbnEnum.Update.getValue());
    int setDelete = readResultService.getSetInfo(loginUserCd, ManageKbnEnum.Delete.getValue());
    setInfo[0] = setAdd + setUpdate + setDelete;
    setInfo[1] = setAdd;
    setInfo[2] = setUpdate;
    setInfo[3] = setDelete;
    setInfo[4] = 0;
    return setInfo;
  }

  /**
   * <pre>
   * 結合の情報をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @return joinInfo Integer[]
   */
  @Override
  public int[] getJoinInfo(int loginUserCd) {
    int[] joinInfo = new int[5];
    int joinAdd = readResultService.getJoinInfo(loginUserCd, ManageKbnEnum.Add.getValue());
    int joinUpdate = readResultService.getJoinInfo(loginUserCd, ManageKbnEnum.Update.getValue());
    int joinDelete = readResultService.getJoinInfo(loginUserCd, ManageKbnEnum.Delete.getValue());
    joinInfo[0] = joinAdd + joinUpdate + joinDelete;
    joinInfo[1] = joinAdd;
    joinInfo[2] = joinUpdate;
    joinInfo[3] = joinDelete;
    joinInfo[4] = 0;
    return joinInfo;
  }

  /**
   * <pre>
   * 【readResultService】を設定する。
   * </pre>
   *
   * @param readResultService 【readResultService】
   */
  @Resource
  public void setReadResultService(ReadResultService readResultService) {
    this.readResultService = readResultService;
  }
}
