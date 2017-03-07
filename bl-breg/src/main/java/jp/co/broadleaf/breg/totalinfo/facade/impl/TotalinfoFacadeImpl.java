//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:                 作成担当:司　志超
// 作成日:2017/02/07    修正内容:累積情報:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.totalinfo.facade.impl;

import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;
import jp.co.broadleaf.breg.common.entity.SetMasterCommon;
import jp.co.broadleaf.breg.common.enums.BlApplyResultsFlgEnum;
import jp.co.broadleaf.breg.common.enums.BlEntryFlgEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.common.enums.ObjectKbnEnum;
import jp.co.broadleaf.breg.common.enums.OpenPriceDivEnum;
import jp.co.broadleaf.breg.totalinfo.enums.FilterObjectEnum;
import jp.co.broadleaf.breg.totalinfo.facade.TotalinfoFacade;
import jp.co.broadleaf.breg.totalinfo.facade.dto.GoodsTotalinfoDto;
import jp.co.broadleaf.breg.totalinfo.facade.dto.JoinTotalinfoDto;
import jp.co.broadleaf.breg.totalinfo.facade.dto.SearchConditionDto;
import jp.co.broadleaf.breg.totalinfo.facade.dto.SetTotalinfoDto;
import jp.co.broadleaf.breg.totalinfo.facade.dto.TotalinfoDto;
import jp.co.broadleaf.breg.totalinfo.service.TotalinfoService;
import jp.co.broadleaf.common.util.BroadleafStringUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 累積情報取得
 * </pre>
 */
public class TotalinfoFacadeImpl implements TotalinfoFacade {

  /** 商品マスタサービス */
  private TotalinfoService totalinfoService;

  /**
   * <pre>
   * 商品マスタサービス.
   * </pre>
   * 
   * @param totalinfoService マスタサービス
   */
  @Resource
  public void setTotalinfoService(TotalinfoService totalinfoService) {
    this.totalinfoService = totalinfoService;
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * @param goodsMasterCommonList   商品マスタ(共有)
   * @return 商品マスタDto
   */
  private List<GoodsTotalinfoDto> convertItemMasterDtoList(List<GoodsMasterCommon> goodsMasterCommonList) {
    List<GoodsTotalinfoDto> itemMasterDtoList = new ArrayList<GoodsTotalinfoDto>();
    if (null != goodsMasterCommonList && goodsMasterCommonList.size() > 0) {
      List<ApplyHeadMasterCommon> headMasterList = totalinfoService.searchApplyHeadMasterCommonList().getApplyHeadMasterList();
      for (int i = goodsMasterCommonList.size() - 1; i >= 0; i--) {
        GoodsMasterCommon itemMaster = goodsMasterCommonList.get(i);
        GoodsTotalinfoDto itemMasterDto = 
            convertItemMasterDto(itemMaster, getApplyHeadMasterCommon(itemMaster.getApplyNo(), itemMaster.getPartsMakerCd(), headMasterList));
        if (i != goodsMasterCommonList.size() - 1) {
          itemMasterDto.setColumnStatus(getColumnStatus(goodsMasterCommonList.get(i + 1), itemMaster));
        }
        itemMasterDtoList.add(itemMasterDto);
      }
    }
    return itemMasterDtoList;
  }
  /**
   * <pre>
   * ColumnStatus
   * </pre>
   * 
   * @param  itemMasterOld 商品マスタ(共有)
   * @param  itemMasterNew 商品マスタ(共有)
   * @return  ColumnStatus
   */
  private String getColumnStatus(GoodsMasterCommon itemMasterOld, GoodsMasterCommon itemMasterNew) {
    StringBuffer sb = new StringBuffer();
    appendColumnStatus(judgeEquals(itemMasterOld.getPrimePartsKanaNm(), itemMasterNew.getPrimePartsKanaNm()), sb, 7);
    appendColumnStatus(judgeEquals(itemMasterOld.getPrimePartsName(), itemMasterNew.getPrimePartsName()), sb, 7);
    appendColumnStatus(judgeEquals(itemMasterOld.getNewPrice(), itemMasterNew.getNewPrice()), sb, 8);
    appendColumnStatus(judgeEquals(itemMasterOld.getOpenPriceDiv(), itemMasterNew.getOpenPriceDiv()), sb, 9);
    appendColumnStatus(judgeEquals(itemMasterOld.getJan(), itemMasterNew.getJan()), sb, 10);
    appendColumnStatus(judgeEquals(itemMasterOld.getPartsLayerCd(), itemMasterNew.getPartsLayerCd()), sb, 11);
    appendColumnStatus(judgeEquals(itemMasterOld.getEquipName(), itemMasterNew.getEquipName()), sb, 12);
    appendColumnStatus(judgeEquals(itemMasterOld.getPrimePartsSpecialNote(), itemMasterNew.getPrimePartsSpecialNote()), sb, 13);
    appendColumnStatus(judgeEquals(itemMasterOld.getPrimePartsSpecialNoteC(), itemMasterNew.getPrimePartsSpecialNoteC()), sb, 14);
    appendColumnStatus(judgeEquals(itemMasterOld.getDeleteFlg(), itemMasterNew.getDeleteFlg()), sb, 15);
    appendColumnStatus(judgeEquals(itemMasterOld.getDeleteReason(), itemMasterNew.getDeleteReason()), sb, 16);
    appendColumnStatus(judgeEquals(itemMasterOld.getGoodsDetailB(), itemMasterNew.getGoodsDetailB()), sb, 17);
    appendColumnStatus(judgeEquals(itemMasterOld.getGoodsDetailC(), itemMasterNew.getGoodsDetailC()), sb, 18);
    appendColumnStatus(judgeEquals(itemMasterOld.getGoodsSize1(), itemMasterNew.getGoodsSize1()), sb, 19);
    appendColumnStatus(judgeEquals(itemMasterOld.getGoodsSize2(), itemMasterNew.getGoodsSize2()), sb, 20);
    appendColumnStatus(judgeEquals(itemMasterOld.getGoodsSize3(), itemMasterNew.getGoodsSize3()), sb, 21);
    appendColumnStatus(judgeEquals(itemMasterOld.getPackageSize1(), itemMasterNew.getPackageSize1()), sb, 22);
    appendColumnStatus(judgeEquals(itemMasterOld.getPackageSize2(), itemMasterNew.getPackageSize2()), sb, 23);
    appendColumnStatus(judgeEquals(itemMasterOld.getPackageSize3(), itemMasterNew.getPackageSize3()), sb, 24);
    appendColumnStatus(judgeEquals(itemMasterOld.getSizeUnit(), itemMasterNew.getSizeUnit()), sb, 25);
    appendColumnStatus(judgeEquals(itemMasterOld.getGoodsWeight(), itemMasterNew.getGoodsWeight()), sb, 26);
    appendColumnStatus(judgeEquals(itemMasterOld.getWeightUnit(), itemMasterNew.getWeightUnit()), sb, 27);
    appendColumnStatus(judgeEquals(itemMasterOld.getUrl1(), itemMasterNew.getUrl1()), sb, 28);
    appendColumnStatus(judgeEquals(itemMasterOld.getUrl2(), itemMasterNew.getUrl2()), sb, 29);
    appendColumnStatus(judgeEquals(itemMasterOld.getUrl3(), itemMasterNew.getUrl3()), sb, 30);
    appendColumnStatus(judgeEquals(itemMasterOld.getImageNo(), itemMasterNew.getImageNo()), sb, 31);
    appendColumnStatus(judgeEquals(itemMasterOld.getBlEntryFlg(), itemMasterNew.getBlEntryFlg()), sb, 37);
    return sb.toString();
  }
  
  /**
   * <pre>
   * ColumnStatus
   * </pre>
   * 
   * @param  flg boolean
   * @param  sb StringBuffer
   * @param  column int
   */
  private void appendColumnStatus(boolean flg, StringBuffer sb, int column) {
     int start = sb.toString().split(",").length;
     for (int i = start; i <=column; i++) {
       sb.append("0,");
     }
     if (flg) {
       sb.append("0,");
     } else {
       sb.append("1,");
     }
  }
  /**
   * <pre>
   * judgeEquals
   * </pre>
   * 
   * @param  obj1 Object
   * @param  obj2 Object
   * @return  boolean
   */
  private boolean judgeEquals(Object obj1, Object obj2) {
    if (obj1 == null) {
      return obj2 == null;
    }
    return obj1.equals(obj2);
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param setMasterCommonList セットマスタModel
   * @return セットマスタDto
   */
  private List<SetTotalinfoDto> convertToSetMasterDtoList(List<SetMasterCommon> setMasterCommonList) {
    List<SetTotalinfoDto> setMasterDtoList = new ArrayList<SetTotalinfoDto>();
    if (null != setMasterCommonList && setMasterCommonList.size() > 0) {
      List<ApplyHeadMasterCommon> headMasterList = totalinfoService.searchApplyHeadMasterCommonList().getApplyHeadMasterList();
      
      for (int i = setMasterCommonList.size() - 1; i >= 0; i--) {
        SetMasterCommon setMaster = setMasterCommonList.get(i);
        SetTotalinfoDto setMasterDto = 
            convertsetMasterDto(setMaster, getApplyHeadMasterCommon(setMaster.getApplyNo(),setMaster.getPartsMakerCd(), headMasterList));
        if (i != setMasterCommonList.size() - 1) {
          setMasterDto.setColumnStatus(getsetColumnStatus(setMasterCommonList.get(i + 1), setMaster));
        }
        setMasterDtoList.add(setMasterDto);
      }
    }
    return setMasterDtoList;
  }
  /**
   * <pre>
   * ColumnStatus
   * </pre>
   * @param setMasterOld セットマスタ(共有)
   * @param setMasterNew セットマスタ(共有)
   * @return  ColumnStatus
   */
  private String getsetColumnStatus(SetMasterCommon setMasterOld, SetMasterCommon setMasterNew) {
    StringBuffer sb = new StringBuffer();
    appendColumnStatus(judgeEquals(setMasterOld.getSetSubPartsNo(), setMasterNew.getSetSubPartsNo()), sb, 8);
    appendColumnStatus(judgeEquals(setMasterOld.getSetKanaName(), setMasterNew.getSetKanaName()), sb, 8);
    appendColumnStatus(judgeEquals(setMasterOld.getSetName(), setMasterNew.getSetName()), sb, 9);
    appendColumnStatus(judgeEquals(setMasterOld.getSetQty(), setMasterNew.getSetQty()), sb, 10);
    appendColumnStatus(judgeEquals(setMasterOld.getSetApecialNote(), setMasterNew.getSetApecialNote()), sb, 11);
    appendColumnStatus(judgeEquals(setMasterOld.getDeleteFlg(), setMasterNew.getDeleteFlg()), sb, 12);
    appendColumnStatus(judgeEquals(setMasterOld.getDeleteReason(), setMasterNew.getDeleteReason()), sb, 13);
    return sb.toString();
  }
    
  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param joinMasterCommonList 結合マスタ
   * @return 結合マスタDto
   */
  private List<JoinTotalinfoDto> convertJoinMasterInfoDtoList(List<JoinMasterCommon> joinMasterCommonList) {
    List<JoinTotalinfoDto> joinMasterDtoList = new ArrayList<JoinTotalinfoDto>();
    if (null != joinMasterCommonList && joinMasterCommonList.size() > 0) {
      List<ApplyHeadMasterCommon> headMasterList = totalinfoService.searchApplyHeadMasterCommonList().getApplyHeadMasterList();
      
      for (int i = joinMasterCommonList.size() - 1; i >= 0; i--) {
        JoinMasterCommon joinMaster = joinMasterCommonList.get(i);
        JoinTotalinfoDto joinMasterDto = 
            convertJoinMasterInfoDto(joinMaster, getApplyHeadMasterCommon(joinMaster.getApplyNo(),joinMaster.getPartsMakerCd(), headMasterList));
        if (i != joinMasterCommonList.size() - 1) {
          joinMasterDto.setColumnStatus(getjoinColumnStatus(joinMasterCommonList.get(i + 1), joinMaster));
        }
        joinMasterDtoList.add(joinMasterDto);
      }
    }
      return  joinMasterDtoList;
  }
  
  /**
   * <pre>
   * ColumnStatus
   * </pre>
   * @param joinMasterOld 結合マスタ(共有)
   * @param joinMasterNew 結合マスタ(共有)
   * @return  ColumnStatus
   */
  private String getjoinColumnStatus(JoinMasterCommon joinMasterOld, JoinMasterCommon joinMasterNew) {
    StringBuffer sb = new StringBuffer();
    appendColumnStatus(judgeEquals(joinMasterOld.getJoinDestPartsNo(), joinMasterNew.getJoinDestPartsNo()), sb, 10);
    appendColumnStatus(judgeEquals(joinMasterOld.getJoinQty(), joinMasterNew.getJoinQty()), sb, 10);
    appendColumnStatus(judgeEquals(joinMasterOld.getJoinSpecialNote(), joinMasterNew.getJoinSpecialNote()), sb, 11);
    appendColumnStatus(judgeEquals(joinMasterOld.getPrimePartsSpecialNoteC(), joinMasterNew.getPrimePartsSpecialNoteC()), sb, 12);
    appendColumnStatus(judgeEquals(joinMasterOld.getDeleteFlg(), joinMasterNew.getDeleteFlg()), sb, 13);
    appendColumnStatus(judgeEquals(joinMasterOld.getDeleteReason(), joinMasterNew.getDeleteReason()), sb, 14);
    appendColumnStatus(judgeEquals(joinMasterOld.getBlEntryFlg(), joinMasterNew.getBlEntryFlg()), sb, 18);
    return sb.toString();
  }
  /**
   * <pre>
   * ApplyHeadMasterCommon
   * </pre>
   * @param applyNo 申請No
   * @param partsMakerCd 部品メーカーコード
   * @param headMasterList 申請ヘッダマスタ
   * @return  申請ヘッダマスタ
   */
  private ApplyHeadMasterCommon getApplyHeadMasterCommon(int applyNo, int partsMakerCd, List<ApplyHeadMasterCommon> headMasterList) {
    for (ApplyHeadMasterCommon applyHeadMasterCommon : headMasterList) {
       if (applyNo == applyHeadMasterCommon.getApplyNo() && partsMakerCd == applyHeadMasterCommon.getPartsMakerCd()) {
         return applyHeadMasterCommon;
       }
    }
    return null;
  }
  
  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param itemMaster 商品マスタ
   * @param applyHeadMasterCommon 申請ヘッダマスタ
   * @return 商品 マスタDto
   */
  private GoodsTotalinfoDto convertItemMasterDto(GoodsMasterCommon itemMaster, ApplyHeadMasterCommon applyHeadMasterCommon) {
    if (itemMaster == null) {
      return null;
    }
    GoodsTotalinfoDto itemMasterDto = new GoodsTotalinfoDto();
    if (null != applyHeadMasterCommon) {
      itemMasterDto.setBlApplyResultsFlg(BlApplyResultsFlgEnum.valueof(applyHeadMasterCommon.getBlApplyResultsFlg()).getRyakuName());
    }
        // 優良品番(－付き品番)
        itemMasterDto.setPrimePartsNoWithH(itemMaster.getPrimePartsNoWithH());
        // 優良設定詳細コード１
        itemMasterDto.setPrmSetDtlNo1(itemMaster.getPrmSetDtlNo1());
        // 処理区分
        itemMasterDto.setDealFlg(ManageKbnEnum.valueof(itemMaster.getDealFlg()).getAbbreviation());
        // BL登録区分
        itemMasterDto.setBlEntryFlg(BlEntryFlgEnum.valueof(itemMaster.getBlEntryFlg()).getName());
        // 削除依頼区分
        itemMasterDto.setdeleteFlg(itemMaster.getDeleteFlg() == null ? ""
            : DeleteFlgEnum.valueof(itemMaster.getDeleteFlg()).getName());
        // 削除理由
        itemMasterDto.setDeleteReason(itemMaster.getDeleteReason());
        // 装備名称
        itemMasterDto.setEquipName(itemMaster.getEquipName());
        // データステータス
        itemMasterDto.setErrFlg(itemMaster.getErrFlg());
        // 商品詳細(B向け)
        itemMasterDto.setGoodsDetailB(itemMaster.getGoodsDetailB());
        // 商品詳細(C向け)
        itemMasterDto.setGoodsDetailC(itemMaster.getGoodsDetailC());
        // 商品中分類コード
        itemMasterDto.setGoodsMGroup(itemMaster.getGoodsMGroup());
        // 商品サイズ(長さ）
        itemMasterDto.setGoodsSize1(itemMaster.getGoodsSize1() == null ? null
            : getPrice(itemMaster.getGoodsSize1().toString()));
        // 商品サイズ(幅）
        itemMasterDto.setGoodsSize2(itemMaster.getGoodsSize2() == null ? null
            : getPrice(itemMaster.getGoodsSize2().toString()));
        // 商品サイズ(高さ）
        itemMasterDto.setGoodsSize3(itemMaster.getGoodsSize3() == null ? null
            : getPrice(itemMaster.getGoodsSize3().toString()));
        // 商品重量
        itemMasterDto.setGoodsWeight(itemMaster.getGoodsWeight() == null ? null
            : getPrice(itemMaster.getGoodsWeight().toString()));
        // 画像数
        itemMasterDto.setImageNo(itemMaster.getImageNo());
        // JAN
        itemMasterDto.setJan(itemMaster.getJan());
        // 新価格
        itemMasterDto.setNewPrice(itemMaster.getNewPrice() == null ?null
            : getPrice(itemMaster.getNewPrice().toString()));
        // オープン価格区分
        itemMasterDto.setOpenPriceDiv(itemMaster.getOpenPriceDiv() ==null ? ""
            : OpenPriceDivEnum.valueof(itemMaster.getOpenPriceDiv()).getName());
        // 梱包サイズ(長さ）
        itemMasterDto.setPackageSize1(itemMaster.getPackageSize1() == null ? null
            : getPrice(itemMaster.getPackageSize1().toString()));
        // 梱包サイズ(幅）
        itemMasterDto.setPackageSize2(itemMaster.getPackageSize2() == null ? null
            : getPrice(itemMaster.getPackageSize2().toString()));
        // 梱包サイズ(高さ）
        itemMasterDto.setPackageSize3(itemMaster.getPackageSize2() == null ? null
            : getPrice(itemMaster.getPackageSize3().toString()));
        // 層別コード
        itemMasterDto.setPartsLayerCd(itemMaster.getPartsLayerCd());
        // 優良部品カナ名称
        itemMasterDto.setPrimePartsKanaNm(itemMaster.getPrimePartsKanaNm());
        // 優良部品名称
        itemMasterDto.setPrimePartsName(itemMaster.getPrimePartsName());
        // 優良部品規格・特記事項
        itemMasterDto.setPrimePartsSpecialNote(itemMaster.getPrimePartsSpecialNote());
        // 優良部品規格・特記事項(C向け)
        itemMasterDto.setPrimePartsSpecialNoteC(itemMaster.getPrimePartsSpecialNoteC());
        // サイズ単位
        itemMasterDto.setSizeUnit(itemMaster.getSizeUnit());
        // 作成日時
        itemMasterDto.setInsDtTime(toString(itemMaster.getInsDtTime()));
        // 更新日時
        itemMasterDto.setUpdDtTime(toString(itemMaster.getUpdDtTime()));
        // 適用日付
        itemMasterDto.setStartTime(toString(itemMaster.getInsDtTime()));
        // URL1
        itemMasterDto.setUrl1(itemMaster.getUrl1());
        // URL2
        itemMasterDto.setUrl2(itemMaster.getUrl2());
        // URL3
        itemMasterDto.setUrl3(itemMaster.getUrl3());
        // 重量単位
        itemMasterDto.setWeightUnit(itemMaster.getWeightUnit());
        // BLコード
        itemMasterDto.setTbsPartsCode(itemMaster.getTbsPartsCode());
        return itemMasterDto;
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param setMaster セットマスタ
   * @param applyHeadMasterCommon 申請ヘッダマスタ
   * @return セットマスタDto
   */
  private SetTotalinfoDto convertsetMasterDto(SetMasterCommon setMaster, ApplyHeadMasterCommon applyHeadMasterCommon) {
    if (setMaster == null) {
      return null;
    }
    SetTotalinfoDto setMasterDto = new SetTotalinfoDto();
    if (null != applyHeadMasterCommon) {
      setMasterDto.setBlApplyResultsFlg(BlApplyResultsFlgEnum.valueof(applyHeadMasterCommon.getBlApplyResultsFlg()).getRyakuName());
    }      
        // 作成日時
        setMasterDto.setInsDtTime(toString(setMaster.getInsDtTime()));
        // 更新日時
        setMasterDto.setUpdDtTime(toString(setMaster.getUpdDtTime()));
        // 論理削除区分
        setMasterDto.setLogicalDelDiv(setMaster.getLogicalDelDiv());
        // 削除依頼区分
        setMasterDto.setDeleteFlg(setMaster.getDeleteFlg() == null ? ""
            : DeleteFlgEnum.valueof(setMaster.getDeleteFlg()).getName());
        // 処理区分
        setMasterDto.setDealFlg(ManageKbnEnum.valueof(setMaster.getDealFlg()).getAbbreviation());
        // 削除時申請理由
        setMasterDto.setDeleteReason(setMaster.getDeleteReason());
        // 優良設定詳細コード１
        setMasterDto.setPrmSetDtlNo1(setMaster.getPrmSetDtlNo1());
        // 商品中分類コード
        setMasterDto.setGoodsMGroup(setMaster.getGoodsMGroup());
        // BLコード
        setMasterDto.setTbsPartsCode(setMaster.getTbsPartsCode());
        // セット親品番
        setMasterDto.setSetMainPartsNo(setMaster.getSetMainPartsNo());
        // セット表示順位
        setMasterDto.setSetDispOrder(setMaster.getSetDispOrder());
        // セット子品番
        setMasterDto.setSetSubPartsNo(setMaster.getSetSubPartsNo());
        // 品名
        setMasterDto.setSetKanaName(setMaster.getSetKanaName());
        // セット名称
        setMasterDto.setSetName(setMaster.getSetName());
        // セットQTY
        setMasterDto.setSetQty(setMaster.getSetQty());
        // 優良部品規格・特記事項
        setMasterDto.setSetApecialNote(setMaster.getSetApecialNote());
        // 適用日付
        setMasterDto.setStartTime(toString(setMaster.getStartTime()));
        return setMasterDto;
  }
  
  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param joinMaster 結合マスタ
   * @param  applyHeadMasterCommon 申請ヘッダマスタ
   * @return 結合マスタDto
   */
  private JoinTotalinfoDto convertJoinMasterInfoDto(JoinMasterCommon joinMaster, ApplyHeadMasterCommon applyHeadMasterCommon) {
    if (joinMaster == null) {
      return null;
    }
    JoinTotalinfoDto joinMasterDto = new JoinTotalinfoDto();
    if (null != applyHeadMasterCommon) {
      joinMasterDto.setBlApplyResultsFlg(BlApplyResultsFlgEnum.valueof(applyHeadMasterCommon.getBlApplyResultsFlg()).getRyakuName());
    }     
        // 優良設定詳細コード１
        joinMasterDto.setPrmSetDtlNo1(joinMaster.getPrmSetDtlNo1());
        // 部品メーカーコード
        joinMasterDto.setPartsMakerCd(joinMaster.getPartsMakerCd());
        // 商品中分類コード
        joinMasterDto.setGoodsMGroup(joinMaster.getGoodsMGroup());
        // BLコード
        joinMasterDto.setTbsPartsCode(joinMaster.getTbsPartsCode());
        // 結合元メーカーコード
        joinMasterDto.setJoinSourceMakerCode(joinMaster.getJoinSourceMakerCode());
        // 優良設定詳細コード２
        joinMasterDto.setPrmSetDtlNo2(joinMaster.getPrmSetDtlNo2());
        // 結合元品番(－付き品番)
        joinMasterDto.setJoinSourPartsNoWithH(joinMaster.getJoinSourPartsNoWithH());
        // 結合表示順位
        joinMasterDto.setJoinDispOrder(joinMaster.getJoinDispOrder());
        // 結合先品番(－付き品番)
        joinMasterDto.setJoinDestPartsNo(joinMaster.getJoinDestPartsNo());
        // 結合QTY
        joinMasterDto.setJoinQty(joinMaster.getJoinQty());
        // 結合規格・特記事項
        joinMasterDto.setJoinSpecialNote(joinMaster.getJoinSpecialNote());
        // 優良部品規格・特記事項(C向け)
        joinMasterDto.setPrimePartsSpecialNotec(joinMaster.getPrimePartsSpecialNoteC());
        // 削除時申請理由
        joinMasterDto.setDeleteReason(joinMaster.getDeleteReason());
        // データステータス
        joinMasterDto.setErrFlg(joinMaster.getErrFlg());
        // BL登録区分
        joinMasterDto.setBlEntryFlg(BlEntryFlgEnum.valueof(joinMaster.getBlEntryFlg()).getName());
        // 削除依頼区分
        joinMasterDto.setDeleteFlg(joinMaster.getDeleteFlg() == null ? ""
            : DeleteFlgEnum.valueof(joinMaster.getDeleteFlg()).getName());
        // 処理区分
        joinMasterDto.setDealFlg(ManageKbnEnum.valueof(joinMaster.getDeleteFlg()).getAbbreviation());
        // 作成日時
        joinMasterDto.setInsDtTime(toString(joinMaster.getInsDtTime()));
        // 更新日時
        joinMasterDto.setUpdDtTime(toString(joinMaster.getUpdDtTime()));
        // 適用日付
        joinMasterDto.setStartTime(toString(joinMaster.getStartTime()));
        return joinMasterDto;
  }
  /**
   * priceを取得
   * @param price price
   * @return price
   */
  private String getPrice(String price) {
    String newPrice = "";
    String[] priceArray = price.split("\\.");
    DecimalFormat myformat = new DecimalFormat();
    myformat.applyPattern("###,###,###");
    newPrice = newPrice + myformat.format(Integer.valueOf(priceArray[0])).toString();
    if (priceArray.length == 2) {
      newPrice = newPrice + "." + priceArray[1];
    }
    if (newPrice.indexOf(".") > 0) {
      newPrice = newPrice.replaceAll("0+?$", "");
      newPrice = newPrice.replaceAll("[.]$", "");
    }
    return newPrice;
  }
  /**
   * DateからStringに転換
   * 
   * @param date date
   * @return 転換結果
   */
  private String toString(Date date) {
    String time;
    SimpleDateFormat formater = new SimpleDateFormat();
    formater.applyPattern("yyyy/MM/dd hh:mm");
    time = formater.format(date);
    return time;
  }
  
  /**
   * <pre>
   * 累積情報の取得.
   * </pre>
   * @param searchConditionDto 検索条件のDto
   * @return TotalinfoDto 累積情報Dto
   */
  @Override
  public TotalinfoDto getTotalInfo(SearchConditionDto searchConditionDto) {
    TotalinfoDto totalinfoDto = new TotalinfoDto();
    List<JoinMasterCommon> joinMasterCommonListAll = new ArrayList<JoinMasterCommon>();
    if (FilterObjectEnum.PartsMakerCd.getValue() == searchConditionDto.getFilterMode()
        || FilterObjectEnum.JoinSourPartsNoWithH.getValue() == searchConditionDto.getFilterMode()
        || ObjectKbnEnum.JoinKbn.getValue() == searchConditionDto.getObjKbn()) {
      // 全結合情報の取得
      joinMasterCommonListAll = getjoinCountByFilter(searchConditionDto.getLogicalDelDiv(),
          searchConditionDto.getFilterMode());
    }
    
    if (ObjectKbnEnum.SetKbn.getValue() == searchConditionDto.getObjKbn()) {
      // 全セット情報の取得
      List<SetMasterCommon> setMasterCommonListAll = getsetCountByFilter(searchConditionDto.getLogicalDelDiv(),
          searchConditionDto.getFilterMode());
      // セット全件数
      totalinfoDto.setSetAllCount(setMasterCommonListAll.size());
      // セット情報の取得
      List<SetMasterCommon> setMasterCommonListByFilter = filterSetMasterCommonList(searchConditionDto, setMasterCommonListAll, joinMasterCommonListAll);
      // セット検索数
      totalinfoDto.setSetSearchCount(setMasterCommonListByFilter.size());
      // セット一覧表示用情報設定
      setSetMasterCommonInfo(searchConditionDto, setMasterCommonListByFilter, totalinfoDto);
    } else if (ObjectKbnEnum.JoinKbn.getValue() == searchConditionDto.getObjKbn()) {
      // 結合全件数
      totalinfoDto.setJoinAllCount(joinMasterCommonListAll.size());
      // 結合情報の取得
      List<JoinMasterCommon> joinMasterCommonListByFilter = filterSetMasterCommonList(searchConditionDto, joinMasterCommonListAll);
      // 結合検索数
      totalinfoDto.setJoinSearchCount(joinMasterCommonListByFilter.size());
      // 結合一覧表示用情報設定
      setJoinMasterCommonInfo(searchConditionDto, joinMasterCommonListByFilter, totalinfoDto);
    } else {
      // 全商品情報の取得
      List<GoodsMasterCommon> goodsMasterCommonListAll = getGoodsMasterCommon(searchConditionDto.getLogicalDelDiv(),
          searchConditionDto.getFilterMode(),searchConditionDto.getTbsPartsCode(),searchConditionDto.getPartsMakerCd(),
          searchConditionDto.getPrimePartsNoWithH());
      // 商品全件数
      totalinfoDto.setGoodsAllCount(goodsMasterCommonListAll.size());
      // 商品情報の取得
      List<GoodsMasterCommon> goodsMasterCommonListByFilter = filterGoodsMasterCommonList(searchConditionDto, goodsMasterCommonListAll, joinMasterCommonListAll);
      // 商品検索数
      totalinfoDto.setGoodsSearchCount(goodsMasterCommonListByFilter.size());
      // 商品一覧表示用情報設定
      setGoodsMasterCommonInfo(searchConditionDto, goodsMasterCommonListByFilter, totalinfoDto);
    }
    return totalinfoDto;
  }
  
  /**
   * <pre>
   * 全商品情報の取得.
   * </pre>
   * @param logicalDelDiv 論理削除区分
   * @param filterMode filterモード
   * @param tbsPartsCode BLコード
   * @param partsMakerCd カーコード
   * @param primePartsNoWithH 優良品番
   * @return 全商品情報
   */
  private List<GoodsMasterCommon> getGoodsMasterCommon(int logicalDelDiv,int filterMode,
          String tbsPartsCode,String partsMakerCd,String primePartsNoWithH) {
    return totalinfoService.searchItemMasterList(logicalDelDiv,filterMode,
       tbsPartsCode,partsMakerCd,primePartsNoWithH).getGoodsList();
  }
  
  /**
   * <pre>
   * 全セット情報の取得.
   * </pre>
   * @param logicalDelDiv 論理削除区分
   * @param filterMode filterモード
   * @return 全セット情報
   */
  private List<SetMasterCommon> getsetCountByFilter(int logicalDelDiv,int filterMode) {
    return totalinfoService.searchSetMasterInfoList(logicalDelDiv,filterMode).getSetMasterCommonList();
  }
  
  /**
   * <pre>
   * 全結合情報の取得.
   * </pre>
   * @param logicalDelDiv 論理削除区分
   * @param filterMode filterモード
   * @return 全結合情報
   */
  private List<JoinMasterCommon> getjoinCountByFilter(int logicalDelDiv,int filterMode) {
    return totalinfoService.searchJoinMasterInfoList(logicalDelDiv,filterMode).getJoinMasterCommonList();
  }
  
  /**
   * <pre>
   * 商品情報の取得.
   * </pre>
   * @param searchConditionDto 検索条件のDto
   * @param goodsMasterCommonListAll 全商品情報
   * @param joinMasterCommonListAll 全結合情報
   * @return 商品情報
   */
  private List<GoodsMasterCommon> filterGoodsMasterCommonList(SearchConditionDto searchConditionDto
           , List<GoodsMasterCommon> goodsMasterCommonListAll, List<JoinMasterCommon> joinMasterCommonListAll) {
    List<GoodsMasterCommon> goodsMasterCommonList = new ArrayList<GoodsMasterCommon>();
    if (FilterObjectEnum.TbsPartsCode.getValue() == searchConditionDto.getFilterMode()) {
      // BLコードの場合
      if (!BroadleafStringUtils.isEmpty(searchConditionDto.getTbsPartsCode())) {
        for (GoodsMasterCommon goodsMasterCommon : goodsMasterCommonListAll) {
          if ((goodsMasterCommon.getTbsPartsCode() == null && BroadleafStringUtils.isEmpty(searchConditionDto.getTbsPartsCode())) ||
              (goodsMasterCommon.getTbsPartsCode() != null && searchConditionDto.getTbsPartsCode().equals(String.valueOf(goodsMasterCommon.getTbsPartsCode().intValue())))) {
            goodsMasterCommonList.add(goodsMasterCommon);
          }
        }
      } else {
        goodsMasterCommonList.addAll(goodsMasterCommonListAll);
      }
    } else if (FilterObjectEnum.PartsMakerCd.getValue() == searchConditionDto.getFilterMode()) {
      // カーコードの場合
      List<String> keyList = getJoinMasterKeyList(joinMasterCommonListAll, searchConditionDto.getFilterMode());
      StringBuffer sb = new StringBuffer();
      for (GoodsMasterCommon goodsMasterCommon : goodsMasterCommonListAll) {
        sb.append(String.valueOf(goodsMasterCommon.getPrmSetDtlNo1())).append(",").append(goodsMasterCommon.getPartsMakerCd())
          .append(",").append(goodsMasterCommon.getPrimePartsNoWithH());
        if (keyList.contains(sb.toString())) {
          goodsMasterCommonList.add(goodsMasterCommon);
        }
      }
    } else if (FilterObjectEnum.PrimePartsNoWithH.getValue() == searchConditionDto.getFilterMode()) {
      // 優良品番の場合
      if (!BroadleafStringUtils.isEmpty(searchConditionDto.getPrimePartsNoWithH())) {
        for (GoodsMasterCommon goodsMasterCommon : goodsMasterCommonListAll) {
          if (searchConditionDto.getPrimePartsNoWithH().equals(goodsMasterCommon.getPrimePartsNoWithH())) {
            goodsMasterCommonList.add(goodsMasterCommon);
          }
        }
      } else {
        goodsMasterCommonList.addAll(goodsMasterCommonListAll);
      }
    } else if (FilterObjectEnum.JoinSourPartsNoWithH.getValue() == searchConditionDto.getFilterMode()) {
      // 純正品番の場合
      List<String> keyList = getJoinMasterKeyList(joinMasterCommonListAll, searchConditionDto.getFilterMode());
      StringBuffer sb = new StringBuffer();
      for (GoodsMasterCommon goodsMasterCommon : goodsMasterCommonListAll) {
        sb.append(String.valueOf(goodsMasterCommon.getApplyNo())).append(",").append(goodsMasterCommon.getTbsPartsCode())
          .append(",").append(goodsMasterCommon.getPartsMakerCd());
        if (keyList.contains(sb.toString())) {
          goodsMasterCommonList.add(goodsMasterCommon);
        }
      }
    } else {
      goodsMasterCommonList.addAll(goodsMasterCommonListAll);
    }
    return goodsMasterCommonList;
  }
  
  /**
   * <pre>
   * セット情報の取得.
   * </pre>
   * @param searchConditionDto 検索条件のDto
   * @param setMasterCommonListAll 全セット情報
   * @param joinMasterCommonListAll 全結合情報
   * @return セット情報
   */
  private List<SetMasterCommon> filterSetMasterCommonList(SearchConditionDto searchConditionDto
           , List<SetMasterCommon> setMasterCommonListAll, List<JoinMasterCommon> joinMasterCommonListAll) {
    List<SetMasterCommon> setMasterCommonList = new ArrayList<SetMasterCommon>();
    if (FilterObjectEnum.TbsPartsCode.getValue() == searchConditionDto.getFilterMode()) {
      // BLコードの場合
      if (!BroadleafStringUtils.isEmpty(searchConditionDto.getTbsPartsCode())) {
        for (SetMasterCommon setMasterCommon : setMasterCommonListAll) {
          if ((setMasterCommon.getTbsPartsCode() == null && BroadleafStringUtils.isEmpty(searchConditionDto.getTbsPartsCode())) ||
              (setMasterCommon.getTbsPartsCode() != null && searchConditionDto.getTbsPartsCode().equals(String.valueOf(setMasterCommon.getTbsPartsCode().intValue())))) {
            setMasterCommonList.add(setMasterCommon);
          }
        }
      } else {
        setMasterCommonList.addAll(setMasterCommonListAll);
      }
    } else if (FilterObjectEnum.PartsMakerCd.getValue() == searchConditionDto.getFilterMode()) {
      // カーコードの場合
      if (!BroadleafStringUtils.isEmpty(searchConditionDto.getPartsMakerCd())) {
        for (SetMasterCommon setMasterCommon : setMasterCommonListAll) {
          if (searchConditionDto.getPartsMakerCd().equals(String.valueOf(setMasterCommon.getPartsMakerCd()))) {
            setMasterCommonList.add(setMasterCommon);
          }
        }
      } else {
        setMasterCommonList.addAll(setMasterCommonListAll);
      }
    } else if (FilterObjectEnum.PrimePartsNoWithH.getValue() == searchConditionDto.getFilterMode()) {
      // 優良品番の場合
      if (!BroadleafStringUtils.isEmpty(searchConditionDto.getPrimePartsNoWithH())) {
        for (SetMasterCommon setMasterCommon : setMasterCommonListAll) {
          if (searchConditionDto.getPrimePartsNoWithH().equals(setMasterCommon.getSetMainPartsNo())) {
            setMasterCommonList.add(setMasterCommon);
          }
        }
      } else {
        setMasterCommonList.addAll(setMasterCommonListAll);
      }
    } else if (FilterObjectEnum.JoinSourPartsNoWithH.getValue() == searchConditionDto.getFilterMode()) {
      // 純正品番の場合
      List<String> keyList = getJoinMasterKeyList(joinMasterCommonListAll, searchConditionDto.getFilterMode());
      StringBuffer sb = new StringBuffer();
      for (SetMasterCommon setMasterCommon : setMasterCommonListAll) {
        sb.append(String.valueOf(setMasterCommon.getApplyNo())).append(",").append(setMasterCommon.getTbsPartsCode())
          .append(",").append(setMasterCommon.getPartsMakerCd());
        if (keyList.contains(sb.toString())) {
          setMasterCommonList.add(setMasterCommon);
        }
      }
    } else {
      setMasterCommonList.addAll(setMasterCommonListAll);
    }
    return setMasterCommonList;
  }
  
  /**
   * <pre>
   * 結合情報の取得.
   * </pre>
   * @param searchConditionDto 検索条件のDto
   * @param joinMasterCommonListAll 全結合情報
   * @return 結合情報
   */
  private List<JoinMasterCommon> filterSetMasterCommonList(SearchConditionDto searchConditionDto
           , List<JoinMasterCommon> joinMasterCommonListAll) {
    List<JoinMasterCommon> joinMasterCommonList = new ArrayList<JoinMasterCommon>();
    if (FilterObjectEnum.TbsPartsCode.getValue() == searchConditionDto.getFilterMode()) {
      // BLコードの場合
      if (!BroadleafStringUtils.isEmpty(searchConditionDto.getTbsPartsCode())) {
        for (JoinMasterCommon joinMasterCommon : joinMasterCommonListAll) {
          if (searchConditionDto.getTbsPartsCode().equals(String.valueOf(joinMasterCommon.getTbsPartsCode()))) {
            joinMasterCommonList.add(joinMasterCommon);
          }
        }
      } else {
        joinMasterCommonList.addAll(joinMasterCommonListAll);
      }
    } else if (FilterObjectEnum.PartsMakerCd.getValue() == searchConditionDto.getFilterMode()) {
      // カーコードの場合
      if (!BroadleafStringUtils.isEmpty(searchConditionDto.getPartsMakerCd())) {
        for (JoinMasterCommon joinMasterCommon : joinMasterCommonListAll) {
          if (searchConditionDto.getPartsMakerCd().equals(String.valueOf(joinMasterCommon.getPartsMakerCd()))) {
            joinMasterCommonList.add(joinMasterCommon);
          }
        }
      } else {
        joinMasterCommonList.addAll(joinMasterCommonListAll);
      }
    } else if (FilterObjectEnum.PrimePartsNoWithH.getValue() == searchConditionDto.getFilterMode()) {
      // 優良品番の場合
      if (!BroadleafStringUtils.isEmpty(searchConditionDto.getPrimePartsNoWithH())) {
        for (JoinMasterCommon joinMasterCommon : joinMasterCommonListAll) {
          if (searchConditionDto.getPrimePartsNoWithH().equals(joinMasterCommon.getJoinDestPartsNo())) {
            joinMasterCommonList.add(joinMasterCommon);
          }
        }
      } else {
        joinMasterCommonList.addAll(joinMasterCommonListAll);
      }
    } else if (FilterObjectEnum.JoinSourPartsNoWithH.getValue() == searchConditionDto.getFilterMode()) {
      // 純正品番の場合
      if (!BroadleafStringUtils.isEmpty(searchConditionDto.getJoinSourPartsNoWithH())) {
        for (JoinMasterCommon joinMasterCommon : joinMasterCommonListAll) {
          if (searchConditionDto.getJoinSourPartsNoWithH().equals(joinMasterCommon.getJoinSourPartsNoWithH())) {
            joinMasterCommonList.add(joinMasterCommon);
          }
        }
      } else {
        joinMasterCommonList.addAll(joinMasterCommonListAll);
      }
    } else {
      joinMasterCommonList.addAll(joinMasterCommonListAll);
    }
    return joinMasterCommonList;
  }
  
  /**
   * <pre>
   * 商品情報表示用の設定.
   * </pre>
   * 
   * @param searchConditionDto 検索条件のDto
   * @param goodsMasterCommonListByFilter 商品情報
   * @param totalinfoDto 累積情報
   */
  private void setGoodsMasterCommonInfo(SearchConditionDto searchConditionDto
    , List<GoodsMasterCommon> goodsMasterCommonListByFilter, TotalinfoDto totalinfoDto) {
    // 商品情報表示用
    List<GoodsMasterCommon> goodsMasterCommonList = new ArrayList<GoodsMasterCommon>();
    int startRow = searchConditionDto.getMaxCount() * (searchConditionDto.getCurrentPage() - 1);
    int endRow = Math.min(searchConditionDto.getMaxCount() * searchConditionDto.getCurrentPage(), goodsMasterCommonListByFilter.size());
    for (int i = startRow; i < endRow; i++) {
      goodsMasterCommonList.add(goodsMasterCommonListByFilter.get(i));
    }
    totalinfoDto.setGoodsTotalinfoDtoList(convertItemMasterDtoList(goodsMasterCommonList));
  }
  
  /**
   * <pre>
   * セット情報表示用の設定.
   * </pre>
   * 
   * @param searchConditionDto 検索条件のDto
   * @param setMasterCommonListByFilter セット情報
   * @param totalinfoDto 累積情報
   */
  private void setSetMasterCommonInfo(SearchConditionDto searchConditionDto
    , List<SetMasterCommon> setMasterCommonListByFilter, TotalinfoDto totalinfoDto) {
    // セット情報表示用
    List<SetMasterCommon> setMasterCommonList = new ArrayList<SetMasterCommon>();
    int startRow = searchConditionDto.getMaxCount() * (searchConditionDto.getCurrentPage() - 1);
    int endRow = Math.min(searchConditionDto.getMaxCount() * searchConditionDto.getCurrentPage(), setMasterCommonListByFilter.size());
    for (int i = startRow; i < endRow; i++) {
      setMasterCommonList.add(setMasterCommonListByFilter.get(i));
    }
    totalinfoDto.setSetTotalinfoDtoList(convertToSetMasterDtoList(setMasterCommonList));
  }
  
  /**
   * <pre>
   * 結合情報表示用の設定.
   * </pre>
   * 
   * @param searchConditionDto 検索条件のDto
   * @param joinMasterCommonListByFilter 結合情報
   * @param totalinfoDto 累積情報
   */
  private void setJoinMasterCommonInfo(SearchConditionDto searchConditionDto
    , List<JoinMasterCommon> joinMasterCommonListByFilter, TotalinfoDto totalinfoDto) {
    // 結合情報表示用
    List<JoinMasterCommon> joinMasterCommonList = new ArrayList<JoinMasterCommon>();
    int startRow = searchConditionDto.getMaxCount() * (searchConditionDto.getCurrentPage() - 1);
    int endRow = Math.min(searchConditionDto.getMaxCount() * searchConditionDto.getCurrentPage(), joinMasterCommonListByFilter.size());
    for (int i = startRow; i < endRow; i++) {
      joinMasterCommonList.add(joinMasterCommonListByFilter.get(i));
    }
    totalinfoDto.setJoinTotalinfoDtoList(convertJoinMasterInfoDtoList(joinMasterCommonList));
  }
  
  /**
   * <pre>
   * 結合マスタのキーリスト取得.
   * </pre>
   * @param joinMasterCommonList 結合情報
   * @param filterMode 検索条件のDto
   * @return 結合マスタのキーリスト
   */
  private List<String> getJoinMasterKeyList(List<JoinMasterCommon> joinMasterCommonList, int filterMode) {
    List<String> keyList = new ArrayList<String>();
    StringBuffer sb;
    for (JoinMasterCommon joinMasterCommon : joinMasterCommonList) {
      sb = new StringBuffer();
      if (FilterObjectEnum.PartsMakerCd.getValue() == filterMode) {
        // 「カーコード」の場合、優良設定詳細コード１ + 部品メーカーコード + 結合先品番(－付き品番)
        sb.append(String.valueOf(joinMasterCommon.getPrmSetDtlNo1())).append(",").append(joinMasterCommon.getPartsMakerCd())
          .append(",").append(joinMasterCommon.getJoinDestPartsNo());
      } else if (FilterObjectEnum.JoinSourPartsNoWithH.getValue() == filterMode) {
        // 「純正品番」の場合、申請No + BLコード + 部品メーカーコード
        sb.append(String.valueOf(joinMasterCommon.getApplyNo())).append(",").append(joinMasterCommon.getTbsPartsCode())
          .append(",").append(joinMasterCommon.getPartsMakerCd());
      }
      keyList.add(sb.toString());
    }
    return keyList;
  }
}
