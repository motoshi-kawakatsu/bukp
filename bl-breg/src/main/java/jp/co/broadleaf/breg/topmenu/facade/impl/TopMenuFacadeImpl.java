//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:趙　命ラン
// 作成日:2017/02/13    修正内容:TopMenuFacadeImpl.java:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.topmenu.facade.impl;


import jp.co.broadleaf.breg.applyheadmastercommon.domain.model.ApplyHeadMasterCommonModel;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterMakerModel;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterMakerModel;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterModel;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.breg.topmenu.facade.TopMenuFacade;
import jp.co.broadleaf.breg.topmenu.service.TopMenuService;

import javax.annotation.Resource;

/**
 * <pre>
 * トップメニューFacade
 * </pre>
 */
public class TopMenuFacadeImpl implements TopMenuFacade {
  
  /** Messageサービス */
  private MessageService messageService;

  /**
   * <pre>
   * Messageサービス.
   * </pre>
   * 
   * @param  messageService サービス
   */
  @Resource
  public void setMessageService(MessageService messageService) {
    this.messageService = messageService;
  }
  
  /** インポート未完了情報 */
  private String noMessageMode = "noMessageMode";
  
  /**
   * <pre>
   * 商品マスタの件数
   * </pre>
   * 
   * @return 商品マスタの件数
   */
  @Override
  public int shohinnKensuJhoho() {
    
    GoodsMasterMakerModel goodsMasterMakerModel=topMenuService.searchItemKensu(noMessageMode);
    int shohinLength = goodsMasterMakerModel.getGoodsList().size();
    return shohinLength;
  }
  
  /** トップメニュー(メーカー)サービス */
  private TopMenuService topMenuService;
  /**
   * <pre>
   * 【TopMenuService】を設定する。
   * </pre>
   *
   * @param topMenuService 【topMenuService】
   */
  @Resource
  private void setTopMenuService(TopMenuService topMenuService) {
    this.topMenuService = topMenuService;
  }

  /**
   * <pre>
   * セットマスタの件数
   * </pre>
   * 
   * @return セットマスタの件数
   */
  @Override
  public int setKensuJhoho() {
    
    SetMasterMakerModel setMasterMakerModel=topMenuService.searchSetKensu(noMessageMode);
    int setLength = setMasterMakerModel.getSetMasterMakerList().size();
    
    return setLength;
  }
  
  /**
   * <pre>
   * 結合マスタの件数
   * </pre>
   * 
   * @return 結合マスタの件数
   */
  @Override
  public int joinKensuJhoho() {
    
    JoinMasterModel joinMasterModel=topMenuService.searchJoinKensu(noMessageMode);
    int joinLength = joinMasterModel.getJoinMasterList().size();
    
    return joinLength;
  }
  
  /**
   * <pre>
   * 申請件数(メーカー)取得
   * </pre>
   * 
   * @return 申請件数(メーカー)情報
   */
  @Override
  public int applyKensuJhoho() {
    
    ApplyHeadMasterCommonModel applyHeadMasterCommonModel=topMenuService.searchApplyKensu();
    int applyLength = applyHeadMasterCommonModel.getApplyHeadMasterList().size();
    
    return applyLength;
  }
  
  /**
   * <pre>
   * メッセージ(メーカー)取得.
   * </pre>
   * 
   * @return メッセージ(メーカー)情報
   */
  public String messageInfo() {
    String messageTxt = topMenuService.message();
    return messageTxt;
  }
  /**
   * <pre>
   * 本人の判断.
   * </pre>
   * 
   * @return 本人の判断
   */
  public boolean honNin() {
    boolean honNinHandan = topMenuService.getHonnin();
    return honNinHandan;
  }
  /**
   * <pre>
   * 未申請件数の判断.
   * </pre>
   * 
   * @return 未申請件数の判断
   */
  public boolean miShinsei() {
    boolean miShinseiHandan = topMenuService.getMishinseiJoho();
    return miShinseiHandan;
  }
  
  /**
   * <pre>
   * messageOneの取得.
   * </pre>
   * 
   * @return messageOne　
   */
  public String getMessageOne() {
    String messageOne  =  messageService.messageInfo("W00701");
    return messageOne;
  }
}
