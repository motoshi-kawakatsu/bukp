//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:陳　雪涛
// 作成日:2017/02/16    修正内容:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.changecommon.facade.impl;

import jp.co.broadleaf.breg.changecommon.facade.ChangeCommonFacade;
import jp.co.broadleaf.breg.classifycodeguide.facade.dto.ClassifyCodeGuideDto;
import jp.co.broadleaf.breg.classifycodeguide.service.ClassifyCodeGuideService;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.entity.GoodsRateMasterCommon;
import jp.co.broadleaf.common.message.MessageResolver;

import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * 検索置換facadeインタフェース.
 * </pre>
 */
public class ChangeCommonFacadeImpl implements ChangeCommonFacade {

  /** MessageResolver **/
  private MessageResolver messageResolver;

  /**
   * <pre>
   * MessageResolverを設定する。
   * </pre>
   * 
   * @param messageResolver MessageResolver
   */
  @Resource
  public void setMessageResolver(MessageResolver messageResolver) {
    this.messageResolver = messageResolver;
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
   * <pre>
   * 中分類コード検索サビースを設定する。
   * </pre>
   * 
   * @param messageId 中分類コード検索サビース
   * @param messageParameters 中分類コード検索サビース
   * @return 中分類コード検索結果DtoList
   */
  @Override
  public String getMessage(String messageId, String... messageParameters) {
    return messageResolver.getMessage(messageId, messageParameters).getContents();
  }

  /**
   * 中分類コード検索結果
   * 
   * @param logical 論理削除区分＝0:有効.
   * @param makerCode メーカーコード.
   * @param goodsRateGrpCode 中分類コード
   * @param goodsRateGrpName 中分類コード名
   * @return 中分類コード検索結果Dto
   */
  @Override
  public ClassifyCodeGuideDto searchPrimeByCode(int logical, int makerCode, String goodsRateGrpCode,
                                                String goodsRateGrpName) {
    String[] classifyCode = { "商品中分類" };
    List<GoodsRateMasterCommon> goodsMasterMakerList = classifyCodeGuideService.getPrimeByCode(logical, makerCode,
        goodsRateGrpCode, goodsRateGrpName);

    ClassifyCodeGuideDto classifyCodeGuideDto = new ClassifyCodeGuideDto();
    if (goodsMasterMakerList.size() != 0) {
      for (int i = 0; i < goodsMasterMakerList.size(); i++) {
        if (goodsRateGrpCode.equals(String.valueOf(goodsMasterMakerList.get(i).getGoodsRateGrpCode()))) {
          classifyCodeGuideDto.setGoodsRateGrpCode(goodsMasterMakerList.get(i).getGoodsRateGrpCode());
          classifyCodeGuideDto.setGoodsRateGrpName(goodsMasterMakerList.get(i).getGoodsRateGrpName());
        }
      }
    } else {
      String message = messageResolver.getMessage(BregMessageCodes.E00008, classifyCode).getContents();
      classifyCodeGuideDto.setMessage(message);
    }

    return classifyCodeGuideDto;
  }
}
