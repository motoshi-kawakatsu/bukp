//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 李超傑
// 作 成 日       2017/02/06   修正内容 : 結合詳細：新規作成
package jp.co.broadleaf.breg.joindetail.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.dto.BlCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.SelectCodeMasterDto;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.KindCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.common.service.BlCodeMasterCommonService;
import jp.co.broadleaf.breg.common.service.KindCodeMasterCommonService;
import jp.co.broadleaf.breg.common.service.SelectCodeMasterCommonService;
import jp.co.broadleaf.breg.goodslist.service.GoodsMasterMakerService;
import jp.co.broadleaf.breg.joindetail.facade.JoinDetailFacade;
import jp.co.broadleaf.breg.joindetail.facade.dto.JoinDetailDto;
import jp.co.broadleaf.breg.joindetail.service.JoinDetailService;
import jp.co.broadleaf.breg.joinlist.service.JoinMasterWorkService;
import jp.co.broadleaf.breg.message.service.Facade.MessageFacade;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.BroadleafFormatUtils;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.framework.security.SecurityContextHolder;

/**
 * <pre>
 * 結合詳細のFacadeクラス
 * </pre>
 */
public class JoinDetailFacadeImpl implements JoinDetailFacade {

  /**
   * 結合詳細のDTOリストを取得します。
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param tbsPartsCode BLコード
   * @param joinSourceMakerCode 結合元メーカーコード
   * @param prmSetDtlNo2 優良設定詳細コード２
   * @param joinSourPartsNoWithH 結合元品番(－付き品番)
   * @param joinDestPartsNo joinDestPartsNo
   * @return 結合詳細のDTOリスト
   */
  @Override
  public List<JoinDetailDto> getJoinDetail(String prmSetDtlNo1, String partsMakerCd, String tbsPartsCode,
                                           String joinSourceMakerCode, String prmSetDtlNo2, String joinSourPartsNoWithH,
                                           String joinDestPartsNo) {
    List<JoinDetailDto> dtoList = new ArrayList<>();
    // 結合ートマストリストを取得します。
    List<JoinMasterMaker> list = joinDetailService.getJoinDetail(prmSetDtlNo1, partsMakerCd, tbsPartsCode,
        joinSourceMakerCode, prmSetDtlNo2, joinSourPartsNoWithH);
    // 空のリストを返します。
    if (null == list || list.isEmpty()) {
      return null;
    }
    
    // コンバート
    for (JoinMasterMaker joinMasterMaker : list) {
        List<GoodsMasterMaker> goodsMasterMakerList = new ArrayList<GoodsMasterMaker>();
        if(joinMasterMaker.getJoinDestPartsNo() != null){
            goodsMasterMakerList = goodsMasterMakerService.searchGoods(partsMakerCd, joinMasterMaker.getJoinDestPartsNo());
        }
        GoodsMasterMaker goodsMasterMaker = null;
        if (!goodsMasterMakerList.isEmpty()) {
            goodsMasterMaker = goodsMasterMakerList.get(0);
        }
        dtoList.add(convertDto(joinMasterMaker, goodsMasterMaker));
    }
    return dtoList;
  }

  /**
   * 結合詳細ワークのDTOリストを取得します。
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param tbsPartsCode BLコード
   * @param joinSourceMakerCode 結合元メーカーコード
   * @param prmSetDtlNo2 優良設定詳細コード２
   * @param joinSourPartsNoWithH 結合元品番(－付き品番)
   * @param joinDestPartsNo joinDestPartsNo
   * @return 結合詳細のDTOリスト
   */
  @Override
  public List<JoinDetailDto> getJoinMasterWorkList(String prmSetDtlNo1, String partsMakerCd, String tbsPartsCode,
                                                   String joinSourceMakerCode, String prmSetDtlNo2,
                                                   String joinSourPartsNoWithH, String joinDestPartsNo) {
    List<JoinDetailDto> dtoList = new ArrayList<>();
    // 結合マストワークリストを取得します。
    List<JoinMasterWorkMaker> list = joinMasterWorkService.getJoinDetail(prmSetDtlNo1, partsMakerCd, tbsPartsCode,
        joinSourceMakerCode, prmSetDtlNo2, joinSourPartsNoWithH);
    // 空のリストを返します。
    if (null == list || list.isEmpty()) {
      return null;
    }
    // コンバート
    for (JoinMasterWorkMaker joinMasterWorkMaker : list) {
        List<GoodsMasterMaker> goodsMasterMakerList = new ArrayList<GoodsMasterMaker>();
        if(joinMasterWorkMaker.getJoinDestPartsNo() != null){
            goodsMasterMakerList = goodsMasterMakerService.searchGoods(partsMakerCd, joinMasterWorkMaker.getJoinDestPartsNo());
        }
        GoodsMasterMaker goodsMasterMaker = null;
        if (!goodsMasterMakerList.isEmpty()) {
            goodsMasterMaker = goodsMasterMakerList.get(0);
        }
        dtoList.add(convertDto(joinMasterWorkMaker, goodsMasterMaker));
    }
    return dtoList;
  }

  /**
   * BLコードマスタ情報を取得
   * 
   * @param makerCd メーカーコード
   * @return BLコードマスタ情報
   */
  @Override
  public List<BlCodeMasterDto> getBlCodeInfo(int makerCd) {
    List<BlCodeMasterDto> blCodeDto = null;
    // BLコードマスタ情報リストを取得
    List<BlCodeMasterCommon> blCodeMasterInfo = blCodeMasterCommonService.getBlCodeMasterInfo(makerCd);
    if (blCodeMasterInfo != null && !blCodeMasterInfo.isEmpty()) {
      blCodeDto = new ArrayList<>();
      // BLコードマスタ情報リストをBLコードマスタDtoへ変換
      for (BlCodeMasterCommon blCode : blCodeMasterInfo) {
        BlCodeMasterDto dto = new BlCodeMasterDto();
        dto.setBlCode(blCode.getBlCode());
        dto.setBlEdaCode(blCode.getBlEdaCode());
        dto.setBlFullName(blCode.getBlFullName());
        dto.setBlGroupCode(blCode.getBlGroupCode());
        dto.setBlHalfName(blCode.getBlHalfName());
        dto.setEquipGroup(blCode.getEquipGroup());
        dto.setGoodsRateGrpCode(blCode.getGoodsRateGrpCode());
        dto.setOfferDate(blCode.getOfferDate());
        dto.setPrimeSearchKbn(blCode.getPrimeSearchKbn());
        blCodeDto.add(dto);
      }
    }
    return blCodeDto;
  }

  /**
   * セレクトコードマスト情報を取得
   * 
   * @param makerCd メーカーコード
   * @return セレクトコードマスト情報
   */
  @Override
  public List<SelectCodeMasterDto> getSelectCodeInfo(int makerCd) {
    List<SelectCodeMasterDto> selectCodeDto = null;
    // セレクトコードマストリストを取得します。
    List<SelectCodeMasterCommon> selectCodeInfo = selectCodeMasterCommonService.getSelectCodeInfo(makerCd);
    if (selectCodeInfo != null && !selectCodeInfo.isEmpty()) {
      selectCodeDto = new ArrayList<>();
      // セレクトコードマストリストをセレクトコードマスDtoへ変換
      for (SelectCodeMasterCommon selectCode : selectCodeInfo) {
        SelectCodeMasterDto dto = new SelectCodeMasterDto();
        dto.setGoodsMakerCd(selectCode.getGoodsMakerCd());
        dto.setPrmSetDtlNo1(selectCode.getPrmSetDtlNo1());
        dto.setPrmSetDtlName(selectCode.getPrmSetDtlName());
        selectCodeDto.add(dto);
      }
    }
    return selectCodeDto;
  }

  /**
   * 結合詳細Entityを結合詳細Dtoへ変更します。
   * 
   * @param joinMaster 結合詳細
   * @param goodsMasterMaker 商品詳細
   * @return 結合詳細Dto
   */
  private JoinDetailDto convertDto(JoinMasterWorkMaker joinMaster, GoodsMasterMaker goodsMasterMaker) {

    JoinDetailDto dto = new JoinDetailDto();
    // 優良品名
    dto.setPrimePartsName(goodsMasterMaker == null ? "" : goodsMasterMaker.getPrimePartsName());
    // Not null
    dto.setPrmSetDtlNo1(String.valueOf(joinMaster.getPrmSetDtlNo1()));
    // 部品メーカーコード Not null
    dto.setPartsMakerCd(String.valueOf(joinMaster.getPartsMakerCd()));
    // 商品中分類コード
    dto.setGoodsMGroup(joinMaster.getGoodsMGroup() == null ? "" : String.valueOf(joinMaster.getGoodsMGroup()));
    // BLコード Not null
    dto.setTbsPartsCode(String.valueOf(joinMaster.getTbsPartsCode()));
    // 結合元メーカーコード Not null
    dto.setJoinSourceMakerCode(String.valueOf(joinMaster.getJoinSourceMakerCode()));
    // 優良設定詳細コード２ Not null
    dto.setPrmSetDtlNo2(String.valueOf(joinMaster.getPrmSetDtlNo2()));
    // 結合元品番(－付き品番) Not null
    dto.setJoinSourPartsNoWithH(String.valueOf(joinMaster.getJoinSourPartsNoWithH()));
    // 結合表示順位
    dto.setJoinDispOrder(joinMaster.getJoinDispOrder());
    // 結合先品番(－付き品番)
    dto.setJoinDestPartsNo(String.valueOf(joinMaster.getJoinDestPartsNo()));
    // 結合QTY
    dto.setJoinQty(joinMaster.getJoinQty());
    // 結合規格・特記事項
    dto.setJoinSpecialNote(joinMaster.getJoinSpecialNote());
    // 優良部品規格・特記事項(C向け)
    dto.setPrimePartsSpecialNoteC(joinMaster.getPrimePartsSpecialNoteC());
    // 適用日時
    dto.setStartTime(joinMaster.getStartTime().toString() == null ? EMPTY
        : BroadleafFormatUtils.dateToString(joinMaster.getStartTime(), BroadleafFormatUtils.DATE_F004));
    // 削除時申請理由
    dto.setDeleteReason(joinMaster.getDeleteReason());

    // データステータス
    if (joinMaster.getErrorFlg() != null && ErrorFlgEnum.valueof(joinMaster.getErrorFlg()) != null) {
      dto.setErrorFlg(ErrorFlgEnum.valueof(joinMaster.getErrorFlg()).getName());
    }
    // 処理区分 Not null
    if (joinMaster.getManageKbn() != null && ManageKbnEnum.valueof(joinMaster.getManageKbn()) != null) {
      dto.setManageKbn(ManageKbnEnum.valueof(joinMaster.getManageKbn()).getName());
    }
    // エラー内容
    dto.setErrorDetail(joinMaster.getErrorDetail());
    // 削除依頼区分
    if (joinMaster.getDeleteFlg() != null && DeleteFlgEnum.valueof(joinMaster.getDeleteFlg()) != null) {
      dto.setDeleteFlg(DeleteFlgEnum.valueof(joinMaster.getDeleteFlg()).getName());
    }
    // 申請状態
    if (joinMaster.getApplyCondition() != null && ApplyConditionEnum.valueof(joinMaster.getApplyCondition()) != null) {
      dto.setApplyCondition(ApplyConditionEnum.valueof(joinMaster.getApplyCondition()).getName());
    }
    return dto;
  }

  /**
   * 結合マスタを結合詳細のDTOへ変換
   * 
   * @param joinMaster 結合マスタ(メーカー)
   * @param goodsMasterMaker 商品マスタ(メーカー)
   * @return 結合詳細のDTO
   */
  private JoinDetailDto convertDto(JoinMasterMaker joinMaster, GoodsMasterMaker goodsMasterMaker) {
    JoinDetailDto dto = new JoinDetailDto();

    dto.setPrimePartsName(goodsMasterMaker == null ? "" : goodsMasterMaker.getPrimePartsName());
    // Not null
    dto.setPrmSetDtlNo1(String.valueOf(joinMaster.getPrmSetDtlNo1()));
    // 部品メーカーコード Not null
    dto.setPartsMakerCd(String.valueOf(joinMaster.getPartsMakerCd()));
    // 商品中分類コード
    dto.setGoodsMGroup(joinMaster.getGoodsMGroup() == null ? "" : String.valueOf(joinMaster.getGoodsMGroup()));
    // BLコード Not null
    dto.setTbsPartsCode(String.valueOf(joinMaster.getTbsPartsCode()));
    // 結合元メーカーコード Not null
    dto.setJoinSourceMakerCode(String.valueOf(joinMaster.getJoinSourceMakerCode()));
    // 優良設定詳細コード２ Not null
    dto.setPrmSetDtlNo2(String.valueOf(joinMaster.getPrmSetDtlNo2()));
    // 結合元品番(－付き品番) Not null
    dto.setJoinSourPartsNoWithH(String.valueOf(joinMaster.getJoinSourPartsNoWithH()));
    // 結合表示順位
    dto.setJoinDispOrder(joinMaster.getJoinDispOrder());
    // 結合先品番(－付き品番)
    dto.setJoinDestPartsNo(String.valueOf(joinMaster.getJoinDestPartsNo()));
    // 結合QTY
    dto.setJoinQty(joinMaster.getJoinQty());
    // 結合規格・特記事項
    dto.setJoinSpecialNote(joinMaster.getJoinSpecialNote());
    // 優良部品規格・特記事項(C向け)
    dto.setPrimePartsSpecialNoteC(joinMaster.getPrimePartsSpecialNoteC());
    // 適用日時
    dto.setStartTime(joinMaster.getStartTime().toString() == null ? EMPTY
        : BroadleafFormatUtils.dateToString(joinMaster.getStartTime(), BroadleafFormatUtils.DATE_F004));
    // 削除時申請理由
    dto.setDeleteReason(joinMaster.getDeleteReason());
    // データステータス
    dto.setErrorFlg(ErrorFlgEnum.valueof(joinMaster.getErrorFlg()).getName());
    // 処理区分 Not null
    dto.setManageKbn(ManageKbnEnum.valueof(joinMaster.getManageKbn()).getName());
    // エラー内容
    dto.setErrorDetail(joinMaster.getErrorDetail());
    // 削除依頼区分
    dto.setDeleteFlg(
        DeleteFlgEnum.valueof(joinMaster.getDeleteFlg() == null ? 0 : joinMaster.getDeleteFlg()).getAbbreviation());
    // 申請状態
    dto.setApplyCondition(ApplyConditionEnum.valueof(joinMaster.getApplyCondition()).getName());

    return dto;
  }

  /**
   * <pre>
   * * 種別コード名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 種別コード
   */
  @Override
  public Map<String, String> getKindCodeNameMap(int makerCd) {
    Map<String, String> kindCodeNameMap = new HashMap<String, String>();
    List<KindCodeMasterCommon> kindCodeInfo = kindCodeMasterCommonService.getKindCodeInfo(makerCd);
    if (kindCodeInfo != null && !kindCodeInfo.isEmpty()) {
      for (KindCodeMasterCommon kindCodeMaster : kindCodeInfo) {
        String kindCode = String.valueOf(kindCodeMaster.getKindCd());
        if (!kindCodeNameMap.containsKey(kindCode)) {
          kindCodeNameMap.put(kindCode, kindCode.concat("：").concat(kindCodeMaster.getKindName()));
        }
      }
    }
    return kindCodeNameMap;
  }

  /** 種別コードマスト情報サービス */
  private KindCodeMasterCommonService kindCodeMasterCommonService;

  /**
   * EMPTY
   */
  private static final String EMPTY = "";

  /**
   * <pre>
   * 種別コードマスト
   * </pre>
   * 
   * @param kindCodeMasterCommonService 種別コードマスト
   */
  @Resource
  public void setKindCodeMasterCommonService(KindCodeMasterCommonService kindCodeMasterCommonService) {
    this.kindCodeMasterCommonService = kindCodeMasterCommonService;
  }

  /**
   * joinDetailService
   */
  private JoinDetailService joinDetailService;

  /**
   * 結合マスタ(メーカー)ワークのサービス
   * 
   * @param joinDetailService 結合マスタ(メーカー)ワークのサービス
   */
  @Resource
  public void setJoinDetailService(JoinDetailService joinDetailService) {
    this.joinDetailService = joinDetailService;
  }

  /**
   * joinMasterWorkService
   */
  private JoinMasterWorkService joinMasterWorkService;

  /**
   * joinMasterWorkService
   * 
   * @param joinMasterWorkService joinMasterWorkService
   */
  @Resource
  public void setJoinMasterWorkService(JoinMasterWorkService joinMasterWorkService) {
    this.joinMasterWorkService = joinMasterWorkService;
  }

  /** BLコードマスタサービス */
  private BlCodeMasterCommonService blCodeMasterCommonService;

  /**
   * BLコードマスタをセートします。
   * 
   * @param blCodeMasterCommonService BLコードマスタのinterface
   */
  @Resource
  public void setBlCodeMasterCommonService(BlCodeMasterCommonService blCodeMasterCommonService) {
    this.blCodeMasterCommonService = blCodeMasterCommonService;
  }

  /** セレクトコードマストエンプロイーサービス */
  private SelectCodeMasterCommonService selectCodeMasterCommonService;

  /**
   * セレクトコードマストエンプロイーサービスをセットする
   * 
   * @param selectCodeMasterCommonService SelectCodeMasterCommonService
   */
  @Resource
  public void setSelectCodeMasterCommonService(SelectCodeMasterCommonService selectCodeMasterCommonService) {
    this.selectCodeMasterCommonService = selectCodeMasterCommonService;
  }

  /** 商品マスタメーカサービス */
  private GoodsMasterMakerService goodsMasterMakerService;

  /**
   * 商品マスタメーカサービスをセットする
   * 
   * @param goodsMasterMakerService GoodsMasterMakerService
   */
  @Resource
  public void setGoodsMasterMakerService(GoodsMasterMakerService goodsMasterMakerService) {
    this.goodsMasterMakerService = goodsMasterMakerService;
  }

  /** 共通メッセージFacade */
  private MessageFacade messageFacade;

  /**
   * <pre>
   * 共通メッセージ取得を設定する。
   * </pre>
   *
   * @param messageFacade 共通メッセージ取得
   */
  @Resource
  public void setMessageFacade(MessageFacade messageFacade) {
    this.messageFacade = messageFacade;
  }
  
  /**
   * 結合マスタ(メーカー)案のチェック.
   * 
   * @param joinDetailDtoList 結合一覧
   * @param makerCd メーカーコード
   * @param mode int
   * @return 「True」：エラーあり、「False」：エラーなし
   */
  @Override
  public boolean checkImportList(List<JoinDetailDto> joinDetailDtoList, int makerCd ,int mode) {
    /** MessageMap **/
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
	Map<String, String> messageMap = getMessageMap(loginPrincipal.getMessageMap());  
    int itemErrorCount = 0;
    // 結合マスタ(メーカー)案のチェック
    if (!BroadleafUtils.isEmpty(joinDetailDtoList)) {
	    for (JoinDetailDto joinDetailDto : joinDetailDtoList) {
	      if (!joinDetailService.checkJoin(joinDetailDto, makerCd, messageMap,mode)) {
	        itemErrorCount += 1;
	      }
	    }
    }
    // 表示順位重複チェック
    if(joinDetailService.checkDispOrder(joinDetailDtoList)){
      itemErrorCount += 1;
    }
    // 商品マスタ存在チェック
    if(joinDetailService.checkGoodsMasterIsExist(joinDetailDtoList)){
      itemErrorCount += 1;
    }
    if (itemErrorCount == 0) {
      return Boolean.FALSE;
    } else {
      return Boolean.TRUE;
    }

  }
  
  
  
  /**
   * メッセージ情報取得
   * 
   * @param messageMapAll すべてメッセージ情報
   * @return 画面表示用メッセージ情報
   */
  private Map<String, String> getMessageMap(HashMap<String, String> messageMapAll) {
    List<String> messageIdList = new ArrayList<String>();
    messageIdList.add(BregMessageCodes.E00001);
    messageIdList.add(BregMessageCodes.E00002);
    messageIdList.add(BregMessageCodes.E00003);
    messageIdList.add(BregMessageCodes.E00008);
    messageIdList.add(BregMessageCodes.E00303);
    messageIdList.add(BregMessageCodes.E00301);
    messageIdList.add(BregMessageCodes.E00503);
    messageIdList.add(BregMessageCodes.E00502);
    messageIdList.add(BregMessageCodes.E00501);
    messageIdList.add(BregMessageCodes.E00701);
    messageIdList.add(BregMessageCodes.E00702);
    messageIdList.add(BregMessageCodes.E00703);
    messageIdList.add(BregMessageCodes.E00704);
    messageIdList.add(BregMessageCodes.E00705);
    messageIdList.add(BregMessageCodes.W00501);
    messageIdList.add(BregMessageCodes.E00017);
    messageIdList.add(BregMessageCodes.W00201);
    messageIdList.add(BregMessageCodes.W00502);
    return messageFacade.getMessageContentsMap(messageIdList, messageMapAll);
  }

}
