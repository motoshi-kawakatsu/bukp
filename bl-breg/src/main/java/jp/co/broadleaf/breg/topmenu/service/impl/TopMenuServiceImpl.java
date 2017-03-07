//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:趙　命ラン
// 作成日:2017/02/13    修正内容:TopMenuServiceImpl.java:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.topmenu.service.impl;

import jp.co.broadleaf.breg.applyheadmastercommon.domain.model.ApplyHeadMasterCommonModel;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommon;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommonId;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMakerId;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.common.entity.UserMasterCommon;
import jp.co.broadleaf.breg.common.entity.UserMasterCommonId;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterMakerModel;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterModel;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterMakerModel;
import jp.co.broadleaf.breg.topmenu.service.TopMenuService;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.framework.dao.GenericDao;
import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.Limit;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterBuilder;
import jp.co.broadleaf.framework.dao.criteria.StringFilter;
import jp.co.broadleaf.framework.dao.criteria.StringFilterBuilder;
import jp.co.broadleaf.framework.security.SecurityContextHolder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;


/**
 * <pre>
 * メーニューサービス
 * </pre>
 */
public class TopMenuServiceImpl implements TopMenuService{
  
  
  /** インポート未完了情報 */
  private String messageMode = "messageMode";
  
  /** 本人判断情報 */
  private boolean honninHandan = true;
  
  /** 未申請件数有無の情報*/
  private boolean mishinsei = true;
  
  /** 担当者情報マスタDAO. */
  private GenericDao<UserMasterCommon, UserMasterCommonId> userMasterCommonDao;

  /**
   * <pre>
   * 担当者情報マスタDAOを設定する.
   * </pre>
   * @param userMasterCommonDao ユーザー情報DAO
   *　担当者情報マスタDAO.
   */
  @Resource
  public void setUserMasterCommonDao(GenericDao<UserMasterCommon, UserMasterCommonId> userMasterCommonDao) {
      this.userMasterCommonDao = userMasterCommonDao;
  }
  
  /** 商品マスタ(メーカー)DAO */
  private GenericDao<GoodsMasterMaker, GoodsMasterMakerId> goodsMasterMakerDao;
  
  /**
   * <pre>
   * 商品マスタ(メーカー)DAOを設定する.
   * </pre>
   * 
   * @param goodsMasterMakerDao 商品マスタ(メーカー)DAO
   */
  @Resource
  public void setGoodsMasterMakerDao(GenericDao<GoodsMasterMaker, 
                                     GoodsMasterMakerId> goodsMasterMakerDao) {
    this.goodsMasterMakerDao = goodsMasterMakerDao;
  } 
  
  /** セットマスタ(メーカー)DAO */
  private GenericDao<SetMasterMaker, SetMasterMakerId> setMasterMakerDao;

  /**
   * <pre>
   * セットマスタ(メーカー)DAOを設定する.
   * </pre>
   * 
   * @param setMasterMakerDao セットマスタ(メーカー)DAO
   */
  @Resource
  public void setSetMasterMakerDao(GenericDao<SetMasterMaker, SetMasterMakerId> setMasterMakerDao) {
    this.setMasterMakerDao = setMasterMakerDao;
  }
  
  /** 結合マスタ(メーカー)DAO */
  private GenericDao<JoinMasterMaker, JoinMasterMakerId> joinMasterMakerDao;

  /**
   * <pre>
   * 結合マスタ(メーカー)DAOを設定する.
   * </pre>
   * 
   * @param joinMasterMakerDao 結合マスタ(メーカー)DAO
   */
  @Resource
  public void setJoinMasterMakerDao(GenericDao<JoinMasterMaker, JoinMasterMakerId> joinMasterMakerDao) {
    this.joinMasterMakerDao = joinMasterMakerDao;
  }
  
  /** 申請ヘッダマスタDAO */
  private GenericDao<ApplyHeadMasterCommon,ApplyHeadMasterCommonId> applyHeadMasterCommonDao;
  
  /**
   * <pre>
   * DAOを設定する.
   * </pre>
   * 
   * @param applyHeadMasterCommonDao 申請ヘッダマスタDAO
   */
  @Resource
  public void setApplyHeadMasterCommonDao(GenericDao<ApplyHeadMasterCommon,ApplyHeadMasterCommonId> applyHeadMasterCommonDao){
    this.applyHeadMasterCommonDao = applyHeadMasterCommonDao;
  }
  
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

  /**
   * <pre>
   * ユーザ名取得.
   * </pre>
   * @param loginId 
   * 
   * @return ユーザ名情報
   */
  @Override
  public String searchUserName(String loginId) {
    // loginId = 引数.loginId
    StringFilter loginIdfor = StringFilterBuilder.equals(UserMasterCommon.LOGIN_ID, loginId);
    
    Filter filter;
    filter = new AndFilter(loginIdfor);
    List<UserMasterCommon> userMasterList = userMasterCommonDao.findByFilter(filter, Limit.NO_LIMIT);
    String userName = userMasterList.get(0).getUserName();
    
    return userName;
  }
  
  /**
   * <pre>
   * 商品マスタ件数(メーカー)取得.
   * </pre>
   * @param mode 
   * 
   * @return 商品マスタ件数(メーカー)情報
   */
  @Override
  public GoodsMasterMakerModel searchItemKensu(String mode) {
    // ログイン情報を取得
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    GoodsMasterMakerModel goodsMasterMakerModel = new GoodsMasterMakerModel();

    // 論理削除区分 = 0
    NumberFilter logicalDelDiv = NumberFilterBuilder.equals(GoodsMasterMaker.LOGICAL_DEL_DIV, 0);
    // makerCd = 引数.makerCd
    NumberFilter makerCd = NumberFilterBuilder.equals(GoodsMasterMaker.PARTS_MAKER_CD,
        loginPrincipal.getMakerCode());
    // 申請状態　=　0：未申請
    NumberFilter shinseiJhokyo = NumberFilterBuilder.equals(SetMasterMaker.APPLY_CONDITION, 0);
    // インポート区分＝0:インポート（一括申請）
    NumberFilter importKubun = NumberFilterBuilder.equals(GoodsMasterMaker.IMPORT_KBN, 0);

    Filter filter;
    if (mode.equals(messageMode)) {
      filter = new AndFilter(logicalDelDiv, makerCd, shinseiJhokyo, importKubun);
    } else {
      filter = new AndFilter(logicalDelDiv, makerCd);
    }
    List<GoodsMasterMaker> goodsMasterList = goodsMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
    goodsMasterMakerModel.setGoodsList(goodsMasterList);
    return goodsMasterMakerModel;
  }
  
  /**
   * <pre>
   * セットマスタ件数(メーカー)取得.
   * </pre>
   * @param mode 
   * 
   * @return セットマスタ件数(メーカー)情報
   */
  @Override
  public SetMasterMakerModel searchSetKensu(String mode) {
    // ログイン情報を取得
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    SetMasterMakerModel setMasterMakerModel = new SetMasterMakerModel();

    // 論理削除区分 = 0
    NumberFilter logicalDelDiv = NumberFilterBuilder.equals(SetMasterMaker.LOGICAL_DEL_DIV, 0);
    // makerCd = 引数.makerCd
    NumberFilter makerCd = NumberFilterBuilder.equals(SetMasterMaker.PARTS_MAKER_CD,
        loginPrincipal.getMakerCode());
    // 申請状態　=　0：未申請
    NumberFilter shinseiJhokyo = NumberFilterBuilder.equals(SetMasterMaker.APPLY_CONDITION, 0);
    // インポート区分＝0:インポート（一括申請）
    NumberFilter importKubun = NumberFilterBuilder.equals(SetMasterMaker.IMPORT_KBN, 0);

    Filter filter;
    if (mode.equals(messageMode)) {
      filter = new AndFilter(logicalDelDiv, makerCd, shinseiJhokyo, importKubun);
    } else {
      filter = new AndFilter(logicalDelDiv, makerCd);
    }

    List<SetMasterMaker> setMasterList = setMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
    setMasterMakerModel.setSetMasterMakerList(setMasterList);
    return setMasterMakerModel;
  }
  
  /**
   * <pre>
   * 結合件数(メーカー)取得
   * </pre>
   * @param mode 
   * 
   * @return 結合マスタ件数(メーカー)情報
   */
  @Override
  public JoinMasterModel searchJoinKensu(String mode) {
    // ログイン情報を取得
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    JoinMasterModel joinMasterModel = new JoinMasterModel();

    // 論理削除区分 = 0
    NumberFilter logicalDelDiv = NumberFilterBuilder.equals(JoinMasterMaker.LOGICAL_DEL_DIV, 0);
    // makerCd = 引数.makerCd
    NumberFilter makerCd = NumberFilterBuilder.equals(JoinMasterMaker.PARTS_MAKER_CD,
        loginPrincipal.getMakerCode());
    // 申請状態　=　0：未申請
    NumberFilter shinseiJhokyo = NumberFilterBuilder.equals(JoinMasterMaker.APPLY_CONDITION, 0);
    // インポート区分＝0:インポート（一括申請）
    NumberFilter importKubun = NumberFilterBuilder.equals(JoinMasterMaker.IMPORT_KBN, 0);
    
    Filter filter;
    if (mode.equals(messageMode)) {
      filter = new AndFilter(logicalDelDiv, makerCd, shinseiJhokyo, importKubun);
    } else {
      filter = new AndFilter(logicalDelDiv, makerCd);
    }

    List<JoinMasterMaker> joinMasterList = joinMasterMakerDao.findByFilter(filter, Limit.NO_LIMIT);
    joinMasterModel.setJoinMasterList(joinMasterList);
    return joinMasterModel;
  }
  
  /**
   * <pre>
   * 申請件数(メーカー)取得
   * </pre>
   * 
   * @return 申請件数(メーカー)情報
   */
  @Override
  public ApplyHeadMasterCommonModel searchApplyKensu() {
    // ログイン情報を取得
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    ApplyHeadMasterCommonModel applyHeadMasterCommonModel = new ApplyHeadMasterCommonModel();

    // 論理削除区分 = 0
    NumberFilter logicalDelDiv = NumberFilterBuilder.equals(ApplyHeadMasterCommon.LOGICAL_DEL_DIV, 0);
    // BL申請結果区分＝1:申請中
    NumberFilter blApplyFlg = NumberFilterBuilder.equals(ApplyHeadMasterCommon.BL_APPLY_RESULTS_FLG, 1);
    //makerCd = 引数.makerCd
    NumberFilter makerCd = NumberFilterBuilder.equals(ApplyHeadMasterCommon.PARTS_MAKER_CD, loginPrincipal.getMakerCode());
        
    Filter filter;
    filter = new AndFilter(logicalDelDiv, blApplyFlg, makerCd);


    List<ApplyHeadMasterCommon> applyHeadMasterList = applyHeadMasterCommonDao.findByFilter(filter, Limit.NO_LIMIT);
    applyHeadMasterCommonModel.setApplyHeadMasterList(applyHeadMasterList);
    return applyHeadMasterCommonModel;
  }
  
  /**
   * <pre>
   * 本人の判断.
   * </pre> 
   * 
   * @return 本人の判断
   */
  public boolean getHonnin(){
   return honninHandan;
  }
  
  /**
   * <pre>
   * 未申請件数有無の判断.
   * </pre> 
   * 
   * @return 未申請件数有無の情報
   */
  public boolean getMishinseiJoho(){
    return mishinsei;
   }
  
  /**
   * <pre>
   * メッセージ(メーカー)取得.
   * </pre> 
   * 
   * @return メッセージ(メーカー)情報
   */
  public String message() {
    mishinsei = true;
    honninHandan = true;
    String messageInfo = null;
    String kouShinshaId = null;
    Timestamp kouShinhi = null;
    //商品マスタ
    int goodsMasterKensu=searchItemKensu(messageMode).getGoodsList().size();
    //セットマスタ
    int setMasterKensu=searchSetKensu(messageMode).getSetMasterMakerList().size();
    //結合マスタ
    int joinMasterKensu=searchJoinKensu(messageMode).getJoinMasterList().size();
    // ログイン情報を取得
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    //取得したのログインユーザー
    String loginId = loginPrincipal.getLoginId();
    //取得したの更新者
    if (goodsMasterKensu != 0) {
      kouShinshaId = searchItemKensu(messageMode).getGoodsList().get(0).getUpdAccountId();
      kouShinhi = searchItemKensu(messageMode).getGoodsList().get(0).getUpdDtTime();
    }else if (setMasterKensu != 0) {
      kouShinshaId = searchSetKensu(messageMode).getSetMasterMakerList().get(0).getUpdAccountId();
      kouShinhi = searchSetKensu(messageMode).getSetMasterMakerList().get(0).getUpdDtTime();
    }else if (joinMasterKensu != 0) {
      kouShinshaId = searchJoinKensu(messageMode).getJoinMasterList().get(0).getUpdAccountId();
      kouShinhi = searchJoinKensu(messageMode).getJoinMasterList().get(0).getUpdDtTime();
    }else{
      return null;
    }
    
    //取得したの更新日
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
    String kouShinhiFmt = sdf1.format(kouShinhi);
    if(goodsMasterKensu+setMasterKensu+joinMasterKensu>0) {
      mishinsei = false;
      //取得された更新者=ログインユーザーの場合
      if(loginId.equals(kouShinshaId)) {
        messageInfo = messageService.messageInfo(BregMessageCodes.W00701);
        messageInfo = messageInfo.replace("ん。メ", "ん。<br>メ");
        honninHandan = true;
      }else{
        honninHandan = false;
        String userName = searchUserName(kouShinshaId);
        messageInfo = messageService.getMessage(userName, kouShinhiFmt, BregMessageCodes.W00702);
        messageInfo = messageInfo.replace("為、商","為、<br>商").replace("せん。ご","せん。<br>ご");
      }
    }else{
      mishinsei = true;
      messageInfo=null;
    }
    return messageInfo;
  }
  }

