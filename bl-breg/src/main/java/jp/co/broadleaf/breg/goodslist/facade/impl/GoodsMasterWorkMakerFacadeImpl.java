//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : wangnan
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.goodslist.facade.impl;

import jp.co.broadleaf.breg.common.entity.GoodsMasterWorkMaker;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterSearchModel;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterWorkMakerModel;
import jp.co.broadleaf.breg.goodslist.facade.GoodsMasterWorkMakerFacade;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerInfoDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterSearchDto;
import jp.co.broadleaf.breg.goodslist.service.GoodsMasterWorkMakerService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 *  商品マスタ(メーカー)Facadeクラス.
 * </pre>
 */
public class GoodsMasterWorkMakerFacadeImpl implements GoodsMasterWorkMakerFacade {

  /** 商品マスタ(メーカー)サービス */
  private GoodsMasterWorkMakerService goodsMasterService;

  /**
   * <pre>
   * 商品マスタ(メーカー)サービス.
   * </pre>
   * 
   * @param goodsMasterService 商品マスタ(メーカー)サービス
   */
  @Resource
  public void setGoodsMasterService(GoodsMasterWorkMakerService goodsMasterService) {
    this.goodsMasterService = goodsMasterService;
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param goodsMasterSearchDto 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param order 順番の依頼
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param importKbn インポート区分
   * @return 商品マスタ(メーカー)Dto
   */
  @Override
  public GoodsMasterMakerInfoDto searchGoodsMasterInfoList(GoodsMasterSearchDto goodsMasterSearchDto, int order,
                                                           Long skipRows, Long maxRows, Boolean isPage,
                                                           Short importKbn) {
    GoodsMasterMakerInfoDto goodsMasterInfoDto = new GoodsMasterMakerInfoDto();
    GoodsMasterWorkMakerModel goodsMasterWorkMakerModel = goodsMasterService.searchGoodsInfoList(
        convertGoodsMasterSearchModel(goodsMasterSearchDto), order, skipRows, maxRows, isPage, importKbn);
    goodsMasterInfoDto.setGoodsMasterDto(convertGoodsMasterDtoList(goodsMasterWorkMakerModel));
    goodsMasterInfoDto.setSearchCounts(goodsMasterWorkMakerModel.getSearchCounts());
    return goodsMasterInfoDto;
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param importKbn インポート区分
   * @return 商品マスタ(メーカー)Dto
   */
  @Override
  public GoodsMasterMakerInfoDto searchGoodsMasterInfoAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd,
                                                          Short importKbn) {
    GoodsMasterMakerInfoDto goodsMasterInfoDto = new GoodsMasterMakerInfoDto();
    goodsMasterInfoDto.setGoodsMasterDto(convertGoodsMasterDtoList(
        goodsMasterService.searchGoodsInfoAll(skipRows, maxRows, isPage, makerCd, importKbn)));
    return goodsMasterInfoDto;
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)の登録.
   * </pre>
   * 
   * @param goodsMasterInfoDto 商品マスタ(メーカー)Dto
   * @return 登録商品マスタ(メーカー)
   */
  @Override
  public int insertGoodsMasterInfoList(GoodsMasterMakerInfoDto goodsMasterInfoDto) {
    return goodsMasterService.insertGoodsInfoList(convertGoodsMasterModel(goodsMasterInfoDto.getGoodsMasterDto()));
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)情報更新.
   * </pre>
   * 
   * @param goodsMasterDto 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  @Override
  public void updateGoodsMaster(GoodsMasterMakerDto goodsMasterDto) {
    goodsMasterService.updateGoodsInfo(convertGoodsMaster(goodsMasterDto));
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)削除.
   * </pre>
   * 
   * @param goodsMasterDto 商品マスタ(メーカー)Dto
   */
  @Override
  public void logicaldeleteGoodsInfo(GoodsMasterMakerDto goodsMasterDto) {
    goodsMasterService.logicaldeleteGoodsInfo(convertGoodsMaster(goodsMasterDto));
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)削除.
   * </pre>
   * 
   * @param goodsMasterDto 商品マスタ(メーカー)Dto
   */
  @Override
  public void deleteGoodsInfo(GoodsMasterMakerDto goodsMasterDto) {
    goodsMasterService.deleteGoodsInfo(convertGoodsMaster(goodsMasterDto));
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param goodsMasterDtoList 商品マスタ(メーカー)Dto
   * @return 商品マスタ(メーカー)Model
   */
  private GoodsMasterWorkMakerModel convertGoodsMasterModel(List<GoodsMasterMakerDto> goodsMasterDtoList) {
    GoodsMasterWorkMakerModel goodsMasterModel = new GoodsMasterWorkMakerModel();
    if (null != goodsMasterDtoList && goodsMasterDtoList.size() > 0) {
      List<GoodsMasterWorkMaker> goodsMasterList = new ArrayList<GoodsMasterWorkMaker>();
      for (GoodsMasterMakerDto goodsMasterDto : goodsMasterDtoList) {
        GoodsMasterWorkMaker goodsMaster = convertGoodsMaster(goodsMasterDto);
        goodsMasterList.add(goodsMaster);
      }
      goodsMasterModel.setGoodsMasterWorkMakerList(goodsMasterList);
    }
    return goodsMasterModel;
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param goodsMasterDto 商品マスタ(メーカー)Dto
   * @return 商品マスタ(メーカー)Model
   */
  private GoodsMasterSearchModel convertGoodsMasterSearchModel(GoodsMasterSearchDto goodsMasterDto) {
    if (goodsMasterDto == null) {
      return null;
    }
    GoodsMasterSearchModel goodsMasterSearchModel = new GoodsMasterSearchModel();
    // 優良品番(－付き品番)
    goodsMasterSearchModel.setPrimePartsNoWithH(goodsMasterDto.getPrimePartsNoWithH());
    // 優良設定詳細コード１
    goodsMasterSearchModel.setPrmSetDtlNo1(goodsMasterDto.getPrmSetDtlNo1());
    // 申請状態
    goodsMasterSearchModel.setApplyCondition(goodsMasterDto.getApplyCondition());
    // 削除依頼区分
    goodsMasterSearchModel.setDeleteFlg(goodsMasterDto.getDeleteFlg());
    // 装備名称
    goodsMasterSearchModel.setEquipName(goodsMasterDto.getEquipName());
    // データステータス
    goodsMasterSearchModel.setErrorFlg(goodsMasterDto.getErrorFlg());
    // 商品詳細(B向け)
    goodsMasterSearchModel.setGoodsDetailB(goodsMasterDto.getGoodsDetailB());
    // 商品詳細(C向け)
    goodsMasterSearchModel.setGoodsDetailC(goodsMasterDto.getGoodsDetailC());
    // 商品中分類コード
    goodsMasterSearchModel.setGoodsMGroup(goodsMasterDto.getGoodsMGroup());
    // 処理区分
    goodsMasterSearchModel.setManageKbn(goodsMasterDto.getManageKbn());
    // オープン価格区分
    goodsMasterSearchModel.setOpenPriceDiv(goodsMasterDto.getOpenPriceDiv());
    // 層別コード
    goodsMasterSearchModel.setPartsLayerCd(goodsMasterDto.getPartsLayerCd());
    // 優良部品カナ名称
    goodsMasterSearchModel.setPrimePartsKanaNm(goodsMasterDto.getPrimePartsKanaNm());
    // 優良部品規格・特記事項(C向け)
    goodsMasterSearchModel.setPrimePartsSpecialNoteC(goodsMasterDto.getPrimePartsSpecialNoteC());
    // 優良部品規格・特記事項(B向け)
    goodsMasterSearchModel.setPrimePartsSpecialNoteB(goodsMasterDto.getPrimePartsSpecialNoteB());
    // BLコード
    goodsMasterSearchModel.setTbsPartsCode(goodsMasterDto.getTbsPartsCode());
    // 適用日付
    goodsMasterSearchModel.setStartDateStart(goodsMasterDto.getStartDateStart());
    // 適用日付
    goodsMasterSearchModel.setStartDateEnd(goodsMasterDto.getStartDateEnd());
    // 作成日時
    goodsMasterSearchModel.setInsDtTimeStart(goodsMasterDto.getInsDtTimeStart());
    // 作成日時
    goodsMasterSearchModel.setInsDtTimeEnd(goodsMasterDto.getInsDtTimeEnd());
    // 更新日時
    goodsMasterSearchModel.setUpdDtTimeStart(goodsMasterDto.getUpdDtTimeStart());
    // 更新日時
    goodsMasterSearchModel.setUpdDtTimeEnd(goodsMasterDto.getUpdDtTimeEnd());
    // 部品メーカーコード
    goodsMasterSearchModel.setPartsMakerCd(goodsMasterDto.getPartsMakerCd());
    return goodsMasterSearchModel;
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param goodsMasterDto 商品マスタ(メーカー)Dto
   * @return 商品マスタ(メーカー)Model
   */
  private GoodsMasterWorkMaker convertGoodsMaster(GoodsMasterMakerDto goodsMasterDto) {
    if (goodsMasterDto == null) {
      return null;
    }
    GoodsMasterWorkMaker goodsMaster = new GoodsMasterWorkMaker();
    // 部品メーカーコード
    goodsMaster.setPartsMakerCd(goodsMasterDto.getPartsMakerCd());
    // 優良品番(－付き品番)
    goodsMaster.setPrimePartsNoWithH(goodsMasterDto.getPrimePartsNoWithH());
    // 優良設定詳細コード１
    goodsMaster.setPrmSetDtlNo1(goodsMasterDto.getPrmSetDtlNo1());
    // 申請状態
    goodsMaster.setApplyCondition(goodsMasterDto.getApplyCondition());
    // BL登録区分
    goodsMaster.setBlEntryFlg(goodsMasterDto.getBlEntryFlg());
    // チェック区分
    goodsMaster.setCheckFlg(goodsMasterDto.getCheckFlg());
    // 削除依頼区分
    goodsMaster.setDeleteFlg(goodsMasterDto.getDeleteFlg());
    // 削除理由
    goodsMaster.setDeleteReason(goodsMasterDto.getDeleteReason());
    // 装備名称
    goodsMaster.setEquipName(goodsMasterDto.getEquipName());
    // エラー内容
    goodsMaster.setErrorDetail(goodsMasterDto.getErrorDetail());
    // データステータス
    goodsMaster.setErrorFlg(goodsMasterDto.getErrorFlg());
    // 商品詳細(B向け)
    goodsMaster.setGoodsDetailB(goodsMasterDto.getGoodsDetailB());
    // 商品詳細(C向け)
    goodsMaster.setGoodsDetailC(goodsMasterDto.getGoodsDetailC());
    // 商品中分類コード
    goodsMaster.setGoodsMGroup(goodsMasterDto.getGoodsMGroup());
    // 商品サイズ(長さ）
    goodsMaster.setGoodsSize1(goodsMasterDto.getGoodsSize1());
    // 商品サイズ(幅）
    goodsMaster.setGoodsSize2(goodsMasterDto.getGoodsSize2());
    // 商品サイズ(高さ）
    goodsMaster.setGoodsSize3(goodsMasterDto.getGoodsSize3());
    // 商品重量
    goodsMaster.setGoodsWeight(goodsMasterDto.getGoodsWeight());
    // 画像数
    goodsMaster.setImageNo(goodsMasterDto.getImageNo());
    // インポート区分
    goodsMaster.setImportKbn(goodsMasterDto.getImportKbn());
    // JAN
    goodsMaster.setJan(goodsMasterDto.getJan());
    // 処理区分
    goodsMaster.setManageKbn(goodsMasterDto.getManageKbn());
    // 新価格
    goodsMaster.setNewPrice(goodsMasterDto.getNewPrice());
    // オープン価格区分
    goodsMaster.setOpenPriceDiv(goodsMasterDto.getOpenPriceDiv());
    // 梱包サイズ(長さ）
    goodsMaster.setPackageSize1(goodsMasterDto.getPackageSize1());
    // 梱包サイズ(幅）
    goodsMaster.setPackageSize2(goodsMasterDto.getPackageSize2());
    // 梱包サイズ(高さ）
    goodsMaster.setPackageSize3(goodsMasterDto.getPackageSize3());
    // 層別コード
    goodsMaster.setPartsLayerCd(goodsMasterDto.getPartsLayerCd());
    // 優良部品カナ名称
    goodsMaster.setPrimePartsKanaNm(goodsMasterDto.getPrimePartsKanaNm());
    // 優良部品名称
    goodsMaster.setPrimePartsName(goodsMasterDto.getPrimePartsName());
    // 優良部品規格・特記事項(C向け)
    goodsMaster.setPrimePartsSpecialNoteC(goodsMasterDto.getPrimePartsSpecialNoteC());
    // 優良部品規格・特記事項(B向け)
    goodsMaster.setPrimePartsSpecialNoteB(goodsMasterDto.getPrimePartsSpecialNoteB());
    // サイズ単位
    goodsMaster.setSizeUnit(goodsMasterDto.getSizeUnit());
    // 適用日付
    goodsMaster.setStartTime(goodsMasterDto.getStartDate());
    // URL1
    goodsMaster.setUrl1(goodsMasterDto.getUrl1());
    // URL2
    goodsMaster.setUrl2(goodsMasterDto.getUrl2());
    // URL3
    goodsMaster.setUrl3(goodsMasterDto.getUrl3());
    // 重量単位
    goodsMaster.setWeightUnit(goodsMasterDto.getWeightUnit());
    // BLコード
    goodsMaster.setTbsPartsCode(goodsMasterDto.getTbsPartsCode());
    return goodsMaster;
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param goodsMasterModel 商品マスタ(メーカー)Model
   * @return 商品マスタ(メーカー)Dto
   */
  private List<GoodsMasterMakerDto> convertGoodsMasterDtoList(GoodsMasterWorkMakerModel goodsMasterModel) {
    List<GoodsMasterMakerDto> goodsMasterDtoList = new ArrayList<GoodsMasterMakerDto>();
    if (null != goodsMasterModel.getGoodsList() && goodsMasterModel.getGoodsList().size() > 0) {
      for (GoodsMasterWorkMaker goodsMaster : goodsMasterModel.getGoodsList()) {
        GoodsMasterMakerDto goodsMasterDto = convertGoodsMasterDto(goodsMaster);
        goodsMasterDtoList.add(goodsMasterDto);
      }
    }
    return goodsMasterDtoList;
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   * @return 商品マスタ(メーカー)Dto
   */
  private GoodsMasterMakerDto convertGoodsMasterDto(GoodsMasterWorkMaker goodsMaster) {
    if (goodsMaster == null) {
      return null;
    }
    GoodsMasterMakerDto goodsMasterDto = new GoodsMasterMakerDto();
    // 部品メーカーコード
    goodsMasterDto.setPartsMakerCd(goodsMaster.getPartsMakerCd());
    // 優良品番(－付き品番)
    goodsMasterDto.setPrimePartsNoWithH(goodsMaster.getPrimePartsNoWithH());
    // 優良設定詳細コード１
    goodsMasterDto.setPrmSetDtlNo1(goodsMaster.getPrmSetDtlNo1());
    // 申請状態
    goodsMasterDto.setApplyCondition(goodsMaster.getApplyCondition());
    // BL登録区分
    goodsMasterDto.setBlEntryFlg(goodsMaster.getBlEntryFlg());
    // チェック区分
    goodsMasterDto.setCheckFlg(goodsMaster.getCheckFlg());
    // 削除依頼区分
    goodsMasterDto.setDeleteFlg(goodsMaster.getDeleteFlg());
    // 削除理由
    goodsMasterDto.setDeleteReason(goodsMaster.getDeleteReason());
    // 装備名称
    goodsMasterDto.setEquipName(goodsMaster.getEquipName());
    // エラー内容
    goodsMasterDto.setErrorDetail(goodsMaster.getErrorDetail());
    // データステータス
    goodsMasterDto.setErrorFlg(goodsMaster.getErrorFlg());
    // 商品詳細(B向け)
    goodsMasterDto.setGoodsDetailB(goodsMaster.getGoodsDetailB());
    // 商品詳細(C向け)
    goodsMasterDto.setGoodsDetailC(goodsMaster.getGoodsDetailC());
    // 商品中分類コード
    goodsMasterDto.setGoodsMGroup(goodsMaster.getGoodsMGroup());
    // 商品サイズ(長さ）
    goodsMasterDto.setGoodsSize1(goodsMaster.getGoodsSize1());
    // 商品サイズ(幅）
    goodsMasterDto.setGoodsSize2(goodsMaster.getGoodsSize2());
    // 商品サイズ(高さ）
    goodsMasterDto.setGoodsSize3(goodsMaster.getGoodsSize3());
    // 商品重量
    goodsMasterDto.setGoodsWeight(goodsMaster.getGoodsWeight());
    // 画像数
    goodsMasterDto.setImageNo(goodsMaster.getImageNo());
    // インポート区分
    goodsMasterDto.setImportKbn(goodsMaster.getImportKbn());
    // JAN
    goodsMasterDto.setJan(goodsMaster.getJan());
    // 処理区分
    goodsMasterDto.setManageKbn(goodsMaster.getManageKbn());
    // 新価格
    goodsMasterDto.setNewPrice(goodsMaster.getNewPrice());
    // オープン価格区分
    goodsMasterDto.setOpenPriceDiv(goodsMaster.getOpenPriceDiv());
    // 梱包サイズ(長さ）
    goodsMasterDto.setPackageSize1(goodsMaster.getPackageSize1());
    // 梱包サイズ(幅）
    goodsMasterDto.setPackageSize2(goodsMaster.getPackageSize2());
    // 梱包サイズ(高さ）
    goodsMasterDto.setPackageSize3(goodsMaster.getPackageSize3());
    // 層別コード
    goodsMasterDto.setPartsLayerCd(goodsMaster.getPartsLayerCd());
    // 優良部品カナ名称
    goodsMasterDto.setPrimePartsKanaNm(goodsMaster.getPrimePartsKanaNm());
    // 優良部品名称
    goodsMasterDto.setPrimePartsName(goodsMaster.getPrimePartsName());
    // 優良部品規格・特記事項(C向け)
    goodsMasterDto.setPrimePartsSpecialNoteC(goodsMaster.getPrimePartsSpecialNoteC());
    // 優良部品規格・特記事項(B向け)
    goodsMasterDto.setPrimePartsSpecialNoteB(goodsMaster.getPrimePartsSpecialNoteB());
    // サイズ単位
    goodsMasterDto.setSizeUnit(goodsMaster.getSizeUnit());
    // 適用日付
    goodsMasterDto.setStartDate(goodsMaster.getStartTime());
    // URL1
    goodsMasterDto.setUrl1(goodsMaster.getUrl1());
    // URL2
    goodsMasterDto.setUrl2(goodsMaster.getUrl2());
    // URL3
    goodsMasterDto.setUrl3(goodsMaster.getUrl3());
    // 重量単位
    goodsMasterDto.setWeightUnit(goodsMaster.getWeightUnit());
    // BLコード
    goodsMasterDto.setTbsPartsCode(goodsMaster.getTbsPartsCode());
    // UUID
    goodsMasterDto.setUuid(goodsMaster.getUuid());
    // 作成日時
    goodsMasterDto.setInsDtTime(goodsMaster.getInsDtTime());
    // 更新日時
    goodsMasterDto.setUpdDtTime(goodsMaster.getUpdDtTime());
    // 作成アカウントID
    goodsMasterDto.setInsAccountId(goodsMaster.getInsAccountId());
    // 更新アカウントID
    goodsMasterDto.setUpdAccountId(goodsMaster.getUpdAccountId());
    // 論理削除区分
    goodsMasterDto.setLogicalDeleteCode(goodsMaster.getLogicalDelDiv());
    return goodsMasterDto;

  }
}
