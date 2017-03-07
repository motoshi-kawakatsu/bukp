//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:                 作成担当:趙　雷雷
// 作成日:2017/02/12         修正内容:商品中分類コードガイド:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.classifycodeguide.facade.impl;

import jp.co.broadleaf.breg.classifycodeguide.facade.ClassifyCodeGuideFacade;
import jp.co.broadleaf.breg.classifycodeguide.facade.dto.ClassifyCodeGuideDto;
import jp.co.broadleaf.breg.classifycodeguide.service.ClassifyCodeGuideService;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.breg.common.BregMessageCodes;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jp.co.broadleaf.breg.common.entity.GoodsRateMasterCommon;

/**
 * 商品中分類コードガイドFacade
 */
public class ClassifyCodeGuideFacadeImpl implements ClassifyCodeGuideFacade{

  /**
   * 中分類コード検索結果
   * 
   * @param logical 論理削除区分＝0:有効.
   * @param makerCode メーカーコード.
   * @param goodsRateGrpCode 中分類コード
   * @param goodsRateGrpName 中分類コード名
   * @return 中分類コード検索結果DtoList
   */
  @Override
  public List<ClassifyCodeGuideDto> searchPrimeByCode(int logical, int makerCode, String goodsRateGrpCode, String goodsRateGrpName) {
    
    String classifyCode = "商品中分類";
    List<ClassifyCodeGuideDto> classifyCodeGuideDtoList = new ArrayList<ClassifyCodeGuideDto>();
   
    List <GoodsRateMasterCommon> goodsMasterMakerList = classifyCodeGuideService.getPrimeByCode(logical, makerCode, goodsRateGrpCode, goodsRateGrpName);
     
   if (goodsMasterMakerList.size() != 0) {
    for(int i = 0; i < goodsMasterMakerList.size(); i++) {
      ClassifyCodeGuideDto classifyCodeGuideDto = new ClassifyCodeGuideDto();
      
      classifyCodeGuideDto.setGoodsRateGrpCode(goodsMasterMakerList.get(i).getGoodsRateGrpCode());
      classifyCodeGuideDto.setGoodsRateGrpName(goodsMasterMakerList.get(i).getGoodsRateGrpName());
      
      classifyCodeGuideDtoList.add(classifyCodeGuideDto);
    }
      } else {
        ClassifyCodeGuideDto classifyCodeGuideDto = new ClassifyCodeGuideDto();
        String message = messageService.messageInfo(BregMessageCodes.E00008).replace("$1", classifyCode);
        classifyCodeGuideDto.setMessage(message);
        classifyCodeGuideDtoList.add(classifyCodeGuideDto);
      }
    
    return classifyCodeGuideDtoList;
  }
  
  /** 中分類コード検索サビース **/
  private ClassifyCodeGuideService classifyCodeGuideService;

  /**
   * <pre>
   * 中分類コード検索サビースを設定する。
   * </pre>
   *
   * @param classifyCodeGuideService 中分類コード検索サビース
   */
  @Resource
  public void setClassifyCodeGuideService(ClassifyCodeGuideService classifyCodeGuideService) {
    this.classifyCodeGuideService = classifyCodeGuideService;
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
