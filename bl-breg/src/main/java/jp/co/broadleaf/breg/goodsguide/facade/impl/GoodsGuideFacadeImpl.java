//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:                作成担当:趙　雷雷
// 作成日:2017/02/13        修正内容:商品ガイド:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.goodsguide.facade.impl;

import jp.co.broadleaf.breg.goodsguide.facade.GoodsGuideFacade;
import jp.co.broadleaf.breg.goodsguide.facade.dto.GoodsGuideDto;
import jp.co.broadleaf.breg.goodsguide.service.GoodsGuideService;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.breg.common.BregMessageCodes;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommon;

/**
 * <pre>
 * 商品ガイドfacadeインタフェース.
 * </pre>
 */
public class GoodsGuideFacadeImpl implements GoodsGuideFacade{

  /**
   * 優良品番検索結果
   * 
   * @param logical 論理削除区分＝0:有効
   * @param makerCode メーカーコード
   * @param primeCode 優良品番
   * @return 優良品番検索結果DtoList
   */
  @Override
  public List<GoodsGuideDto> searchPrimeByCode(int logical, int makerCode, String primeCode) {
    String goodsGuide = "商品";
    List<GoodsGuideDto> goodsGuideDtoList = new ArrayList<GoodsGuideDto>();
   
    List <GoodsMasterMaker> goodsMasterMakerList = goodsGuideService.getPrimeByCode(logical, makerCode, primeCode);
    if(goodsMasterMakerList.size() != 0) {
      for(int i = 0; i < goodsMasterMakerList.size(); i++) {
        GoodsGuideDto goodsGuideDto = new GoodsGuideDto();
        goodsGuideDto.setPrimePartsKanaNm(goodsMasterMakerList.get(i).getPrimePartsKanaNm());
        goodsGuideDto.setPrimePartsName(goodsMasterMakerList.get(i).getPrimePartsName());
        goodsGuideDto.setPrimePartsNoWithH(goodsMasterMakerList.get(i).getPrimePartsNoWithH());
        goodsGuideDtoList.add(goodsGuideDto);
        }
    } else {
      GoodsGuideDto goodsGuideDto = new GoodsGuideDto();
      String message = messageService.messageInfo(BregMessageCodes.E00008).replace("$1", goodsGuide);
      goodsGuideDto.setMessage(message);
      goodsGuideDtoList.add(goodsGuideDto);
    }
    return goodsGuideDtoList;
  }
  
  /** 優良品番検索サビース **/
  private GoodsGuideService goodsGuideService;

  /**
   * <pre>
   * 優良品番検索サビースを設定する。
   * </pre>
   *
   * @param goodsGuideService 優良品番検索サビース
   */
  @Resource
  public void setGoodsGuideService(GoodsGuideService goodsGuideService) {
    this.goodsGuideService = goodsGuideService;
  }
  
  
  /**
   * 純正品番検索結果
   * 
   * @param logical 論理削除区分＝0:有効
   * @param makerCode メーカーコード
   * @param pureGoodsCode 純正品番
   * @return 純正品番検索結果DtoList
   */
  @Override
  public List<GoodsGuideDto> searchPureGoodsByCode(int logical, int makerCode, String pureGoodsCode){
    String goodsGuide = "商品";
    List<GoodsGuideDto> pureGoodsDtoList = new ArrayList<GoodsGuideDto>();
    
    List<PuregoodsMasterCommon> pureGoodsList = 
         goodsGuideService.getPureGoodsByCode(logical, makerCode, pureGoodsCode);
    if (pureGoodsList.size() != 0) {
      for (int i = 0; i < pureGoodsList.size(); i++) {
        GoodsGuideDto goodsGuideDto = new GoodsGuideDto();
        goodsGuideDto.setPrimePartsNoWithH(pureGoodsList.get(i).getPrimePartsNoWithH());
        goodsGuideDto.setPrimePartsKanaNm(pureGoodsList.get(i).getPrimePartsKanaNm());
        goodsGuideDto.setPrimePartsName(pureGoodsList.get(i).getPrimePartsName());
        pureGoodsDtoList.add(goodsGuideDto);
      }
    } else {
      GoodsGuideDto goodsGuideDto = new GoodsGuideDto();
      String message = messageService.messageInfo(BregMessageCodes.E00008).replace("$1", goodsGuide);
      goodsGuideDto.setMessage(message);
      pureGoodsDtoList.add(goodsGuideDto);
    }
    
    return pureGoodsDtoList;
  }
  /**
   * messageサビース
   */
  private MessageService messageService;

  /**
   * <pre>
   * messageサビースを設定する。
   * </pre>
   *
   * @param messageService messageサビース
   */
  @Resource
  public void setMessageService(MessageService messageService) {
    this.messageService = messageService;
  }

}
