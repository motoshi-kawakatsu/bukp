//****************************************************************************//
// システム                                    : カーパーツマネージャー
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                            作成担当 : 秦志超
// 作 成 日       2017/02/06   修正内容 : 申請詳細:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applydetail.facade.impl;

import jp.co.broadleaf.breg.applydetail.facade.ApplyDetailFacade;
import jp.co.broadleaf.breg.applydetail.facade.dto.GoodsMasterDto;
import jp.co.broadleaf.breg.applydetail.facade.dto.JoinMasterDto;
import jp.co.broadleaf.breg.applydetail.facade.dto.SetMasterDto;
import jp.co.broadleaf.breg.applydetail.service.ApplyDetailService;
import jp.co.broadleaf.breg.classifycodeguide.service.ClassifyCodeGuideService;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.CarmakerMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsRateMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.KindCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.PartsMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.SetMasterCommon;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.BlApplyResultsFlgEnum;
import jp.co.broadleaf.breg.common.enums.BlEntryFlgEnum;
import jp.co.broadleaf.breg.common.enums.CheckFlgEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.common.enums.OpenPriceDivEnum;
import jp.co.broadleaf.breg.common.enums.SizeUnitEnum;
import jp.co.broadleaf.breg.common.enums.WeightUnitEnum;
import jp.co.broadleaf.breg.common.service.BlCodeMasterCommonService;
import jp.co.broadleaf.breg.common.service.CarmakerMasterCommonService;
import jp.co.broadleaf.breg.common.service.KindCodeMasterCommonService;
import jp.co.broadleaf.breg.common.service.PartsMasterCommonService;
import jp.co.broadleaf.breg.common.service.SelectCodeMasterCommonService;
import jp.co.broadleaf.breg.companysetting.service.CompanyInfoMasterCommonService;
import jp.co.broadleaf.common.util.BroadleafStringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * <pre>
 * ApplyDetailFacadeImpl
 * </pre>
 */
public class ApplyDetailFacadeImpl implements ApplyDetailFacade {
  /** applyDetailService */
  private ApplyDetailService applyDetailService;
  /** companyInfoMasterCommonService */
  private CompanyInfoMasterCommonService companyInfoMasterCommonService;
  /** BLコードマスタ情報サービス */
  private BlCodeMasterCommonService blCodeMasterCommonService;
  /** セレクトコードマスト情報サービス */
  private SelectCodeMasterCommonService selectCodeMasterCommonService;
  /** 種別コードマスト情報サービス */
  private KindCodeMasterCommonService kindCodeMasterCommonService;
  /** カーメーカーコードマスタ情報サービス */
  private CarmakerMasterCommonService carmakerMasterCommonService;
  /** 中分類コード検索サビース **/
  private ClassifyCodeGuideService classifyCodeGuideService;
  /** 層別マスタ情報サービス */
  private PartsMasterCommonService partsMasterCommonService;

  /**
   * <pre>
   * 【partsMasterCommonService】を設定する。
   * </pre>
   *
   * @param partsMasterCommonService 【partsMasterCommonService】
   */
  @Resource
  public void setPartsMasterCommonService(PartsMasterCommonService partsMasterCommonService) {
    this.partsMasterCommonService = partsMasterCommonService;
  }

  /**
   * <pre>
   * 【applyDetailService】を設定する。
   * </pre>
   *
   * @param applyDetailService 【applyDetailService】
   */
  @Resource
  public void setApplyDetailService(ApplyDetailService applyDetailService) {
    this.applyDetailService = applyDetailService;
  }

  /**
   * <pre>
   * 【companyInfoMasterCommonService】を設定する。
   * </pre>
   *
   * @param companyInfoMasterCommonService 【companyInfoMasterCommonService】
   */
  @Resource
  public void setCompanyInfoMasterCommonService(CompanyInfoMasterCommonService companyInfoMasterCommonService) {
    this.companyInfoMasterCommonService = companyInfoMasterCommonService;
  }

  /**
   * <pre>
   * 【blCodeMasterCommonService】を設定する。
   * </pre>
   *
   * @param blCodeMasterCommonService 【blCodeMasterCommonService】
   */
  @Resource
  public void setBlCodeMasterCommonService(BlCodeMasterCommonService blCodeMasterCommonService) {
    this.blCodeMasterCommonService = blCodeMasterCommonService;
  }

  /**
   * <pre>
   * 【selectCodeMasterCommonService】を設定する。
   * </pre>
   *
   * @param selectCodeMasterCommonService 【selectCodeMasterCommonService】
   */
  @Resource
  public void setSelectCodeMasterCommonService(SelectCodeMasterCommonService selectCodeMasterCommonService) {
    this.selectCodeMasterCommonService = selectCodeMasterCommonService;
  }

  /**
   * <pre>
   * 【kindCodeMasterCommonService】を設定する。
   * </pre>
   *
   * @param kindCodeMasterCommonService 【kindCodeMasterCommonService】
   */
  @Resource
  public void setKindCodeMasterCommonService(KindCodeMasterCommonService kindCodeMasterCommonService) {
    this.kindCodeMasterCommonService = kindCodeMasterCommonService;
  }

  /**
   * <pre>
   * 【carmakerMasterCommonService】を設定する。
   * </pre>
   *
   * @param carmakerMasterCommonService 【carmakerMasterCommonService】
   */
  @Resource
  public void setCarmakerMasterCommonService(CarmakerMasterCommonService carmakerMasterCommonService) {
    this.carmakerMasterCommonService = carmakerMasterCommonService;
  }

  /**
   * <pre>
   * 【classifyCodeGuideService】を設定する。
   * </pre>
   *
   * @param classifyCodeGuideService 【classifyCodeGuideService】
   */
  @Resource
  public void setClassifyCodeGuideService(ClassifyCodeGuideService classifyCodeGuideService) {
    this.classifyCodeGuideService = classifyCodeGuideService;
  }

  /**
   * <pre>
   * 【申請詳細】の商品データの獲得
   * </pre>
   *
   * @param makerCode メーカーコード
   * @param logical 論理削除区分
   * @param blApplyResultsFlg BL申請結果区分
   * @param updAccountId 更新アカウントID
   * @param importKbn インポート区分
   * @return 商品マスタdto
   */
  @Override
  public List<GoodsMasterDto> searchGoodsMaster(int makerCode, int logical, int blApplyResultsFlg, String updAccountId,
                                                int importKbn) {
    List<GoodsMasterDto> goodsMasterDtoList = new ArrayList<GoodsMasterDto>();
    List<GoodsMasterMaker> goodsMasterMakerList = applyDetailService.searchGoods(makerCode, logical, blApplyResultsFlg,
        updAccountId, importKbn);
    Map<String, String> selectCodeName = getSelectCodeNameMap(makerCode);
    Map<String, String> classifyCodeGuide = getClassifyCodeGuideMap(makerCode);
    Map<String, String> blCodeName = getBlCodeNameMap(makerCode);
    Map<String, String> partsName = getPartsNameMap(makerCode);
    List<GoodsMasterCommon> goodsMasterCommonList = applyDetailService.searchGoodsCommon(makerCode);
    if (!goodsMasterMakerList.isEmpty()) {
      editGoodsMasterDto(goodsMasterMakerList, goodsMasterCommonList, goodsMasterDtoList, selectCodeName,
          classifyCodeGuide, blCodeName, partsName);
    }
    return goodsMasterDtoList;
  }

  /**
   * <pre>
   * Dtoの中で、メーカー側商品データを付け加える
   * </pre>
   * 
   * @param goodsMasterMakerList メーカー側商品マスタデータ
   * @param goodsMasterCommonList 共有側商品マスタデータ
   * @param goodsMasterDtoList 商品マスタdto
   * @param selectCodeName セレクトコード名称
   * @param classifyCodeGuide 分類コード名称
   * @param blCodeName BLコード名称
   * @param partsName 層別コード名称
   */
  public void editGoodsMasterDto(List<GoodsMasterMaker> goodsMasterMakerList,
                                 List<GoodsMasterCommon> goodsMasterCommonList, List<GoodsMasterDto> goodsMasterDtoList,
                                 Map<String, String> selectCodeName, Map<String, String> classifyCodeGuide,
                                 Map<String, String> blCodeName, Map<String, String> partsName) {
    for (int i = 0; i < goodsMasterMakerList.size(); i++) {
      GoodsMasterDto goodsMasterDto = new GoodsMasterDto();
      int applyCondition = goodsMasterMakerList.get(i).getApplyCondition(); // 申請状態
      int manageDifferent = goodsMasterMakerList.get(i).getManageKbn(); // 処理区分
      int prmSetDtlNo1 = goodsMasterMakerList.get(i).getPrmSetDtlNo1(); // 優良設定詳細コード１
      int goodsMGroup = goodsMasterMakerList.get(i).getGoodsMGroup(); // 商品中分類コード
      int tbsPartsCode = goodsMasterMakerList.get(i).getTbsPartsCode(); // BLコード
      String primePartsNoWithH = goodsMasterMakerList.get(i).getPrimePartsNoWithH(); // 優良品番(－付き品番)
      String primePartsKanaNm = goodsMasterMakerList.get(i).getPrimePartsKanaNm(); // 優良部品カナ名称
      String primePartsName = goodsMasterMakerList.get(i).getPrimePartsName(); // 優良部品名称
      double newPrice = goodsMasterMakerList.get(i).getNewPrice(); // 新価格
      int openPriceDiv = goodsMasterMakerList.get(i).getOpenPriceDiv(); // オープン価格区分
      Long jan = goodsMasterMakerList.get(i).getJan(); // JAN
      String partsLayerCd = goodsMasterMakerList.get(i).getPartsLayerCd(); // 層別コード
      String equipName = goodsMasterMakerList.get(i).getEquipName(); // 装備名称
      String noC = goodsMasterMakerList.get(i).getPrimePartsSpecialNoteC(); // 優良部品規格・特記事項(C向け)
      String noB = goodsMasterMakerList.get(i).getPrimePartsSpecialNoteB(); // 優良部品規格・特記事項(B向け)
      Short deleteFlag = goodsMasterMakerList.get(i).getDeleteFlg(); // 削除依頼区分
      int deleteFlg = deleteFlag == null ? 0 : deleteFlag;
      String deleteReason = goodsMasterMakerList.get(i).getDeleteReason(); // 削除理由
      String goodsDetailB = goodsMasterMakerList.get(i).getGoodsDetailB(); // 商品詳細(B向け)
      String goodsDetailC = goodsMasterMakerList.get(i).getGoodsDetailC(); // 商品詳細(C向け)
      int goodsSize1 = goodsMasterMakerList.get(i).getGoodsSize1(); // 商品サイズ(長さ）
      int goodsSize2 = goodsMasterMakerList.get(i).getGoodsSize2(); // 商品サイズ(幅）
      int goodsSize3 = goodsMasterMakerList.get(i).getGoodsSize3(); // 商品サイズ(高さ）
      int packageSize1 = goodsMasterMakerList.get(i).getPackageSize1(); // 梱包サイズ(長さ）
      int packageSize2 = goodsMasterMakerList.get(i).getPackageSize2(); // 梱包サイズ(幅）
      int packageSize3 = goodsMasterMakerList.get(i).getPackageSize3(); // 梱包サイズ(高さ）
      String sizeUnit = goodsMasterMakerList.get(i).getSizeUnit(); // サイズ単位
      int goodsWeight = goodsMasterMakerList.get(i).getGoodsWeight(); // 商品重量
      String weightUnit = goodsMasterMakerList.get(i).getWeightUnit(); // 重量単位
      short imageNo = goodsMasterMakerList.get(i).getImageNo(); // 画像数
      int blEntryFlg = goodsMasterMakerList.get(i).getBlEntryFlg(); // BL登録区分
      int errorFlg = goodsMasterMakerList.get(i).getErrorFlg(); // データステータス

      goodsMasterDto.setApplyCondition(ApplyConditionEnum.valueof(applyCondition).getName()); // 申請状態
      goodsMasterDto.setManageKbn(ManageKbnEnum.valueof(manageDifferent).getName()); // 処理区分
      goodsMasterDto
          .setPrmSetDtlNo1(prmSetDtlNo1 == 9999 ? "9999：指定無し" : selectCodeName.get(String.valueOf(prmSetDtlNo1))); // 優良設定詳細コード１
      goodsMasterDto.setGoodsMGroup(classifyCodeGuide.get(String.valueOf(goodsMGroup))); // 商品中分類コード
      goodsMasterDto.setTbsPartsCode(blCodeName.get(String.valueOf(tbsPartsCode))); // BLコード
      goodsMasterDto.setPrimePartsNoWithH(primePartsNoWithH); // 優良品番(－付き品番)
      goodsMasterDto.setPrimePartsKanaNm(primePartsKanaNm); // 優良部品カナ名称
      goodsMasterDto.setPrimePartsName(primePartsName); // 優良部品名称
      goodsMasterDto.setNewPrice(numberFormat(newPrice)); // 新価格
      goodsMasterDto.setOpenPriceDiv(OpenPriceDivEnum.valueof(openPriceDiv).getName()); // オープン価格区分
      goodsMasterDto.setJan(jan); // JAN
      goodsMasterDto.setPartsLayerCd(partsName.get(String.valueOf(partsLayerCd))); // 層別コード
      goodsMasterDto.setEquipName(equipName); // 装備名称
      goodsMasterDto.setPrimePartsSpecialNoteC(noC); // 優良部品規格・特記事項(C向け)
      goodsMasterDto.setPrimePartsSpecialNoteB(noB); // 優良部品規格・特記事項(B向け)
      goodsMasterDto.setDeleteFlg(DeleteFlgEnum.valueof(deleteFlg).getName()); // 削除依頼区分
      goodsMasterDto.setDeleteReason(deleteReason); // 削除理由
      goodsMasterDto.setGoodsDetailB(goodsDetailB); // 商品詳細(B向け)
      goodsMasterDto.setGoodsDetailC(goodsDetailC); // 商品詳細(C向け)
      goodsMasterDto.setGoodsSize1(numberFormat(goodsSize1)); // 商品サイズ(長さ）
      goodsMasterDto.setGoodsSize2(numberFormat(goodsSize2)); // 商品サイズ(幅）
      goodsMasterDto.setGoodsSize3(numberFormat(goodsSize3)); // 商品サイズ(高さ）
      goodsMasterDto.setPackageSize1(numberFormat(packageSize1)); // 梱包サイズ(長さ）
      goodsMasterDto.setPackageSize2(numberFormat(packageSize2)); // 梱包サイズ(幅）
      goodsMasterDto.setPackageSize3(numberFormat(packageSize3)); // 梱包サイズ(高さ）
      goodsMasterDto.setSizeUnit(SizeUnitEnum.valueof(sizeUnit).getName()); // サイズ単位
      goodsMasterDto.setGoodsWeight(goodsWeight); // 商品重量
      goodsMasterDto.setWeightUnit(WeightUnitEnum.valueof(weightUnit).getName()); // 重量単位
      goodsMasterDto.setUrl1(goodsMasterMakerList.get(i).getUrl1()); // URL1
      goodsMasterDto.setUrl2(goodsMasterMakerList.get(i).getUrl2()); // URL2
      goodsMasterDto.setUrl3(goodsMasterMakerList.get(i).getUrl3()); // URL3
      goodsMasterDto.setImageNo(imageNo); // 画像数
      goodsMasterDto.setInsDtTime(timeFormat(goodsMasterMakerList.get(i).getInsDtTime())); // 作成日時
      goodsMasterDto.setUpdDtTime(timeFormat(goodsMasterMakerList.get(i).getUpdDtTime())); // 更新日時
      goodsMasterDto.setApplyTime(timeFormat(goodsMasterMakerList.get(i).getStartTime())); // 適用日時
      goodsMasterDto.setBlEntryFlg(BlEntryFlgEnum.valueof(blEntryFlg).getName()); // BL登録区分
      goodsMasterDto.setErrorFlg(ErrorFlgEnum.valueof(errorFlg).getName()); // データステータス

      if (manageDifferent == 1) { // 処理区分は更新の場合
        if (!goodsMasterCommonList.isEmpty()) {
          List<GoodsMasterCommon> goodsMasterCommonSingle = new ArrayList<GoodsMasterCommon>();
          for (int j = 0; j < goodsMasterCommonList.size(); j++) {
            if (goodsMasterCommonList.get(j).getPrmSetDtlNo1() == prmSetDtlNo1 && BroadleafStringUtils
                .compareTo(goodsMasterCommonList.get(j).getPrimePartsNoWithH(), primePartsNoWithH) == 0) {
              goodsMasterCommonSingle.add(goodsMasterCommonList.get(j)); // 優良品番(－付き品番)
                                                                         // 優良設定詳細コード１について、最新の商品履歴を検索する
              break;
            }
          }
          if (!goodsMasterCommonSingle.isEmpty()) {
            historyJudge(goodsMasterCommonSingle, goodsMasterDto);
          }
        }
      }
      goodsMasterDtoList.add(goodsMasterDto);
    }
  }

  /**
   * メーカー側の商品データと申請履歴のデータを比較する
   * 
   * @param goodsMasterCommonSingle List
   * @param goodsMasterDto GoodsMasterDto
   */
  public void historyJudge(List<GoodsMasterCommon> goodsMasterCommonSingle, GoodsMasterDto goodsMasterDto) {
    StringBuffer goodsFlg = new StringBuffer();
    goodsFlg.append("0,0,0,0,0,0"); // 申請状態 処理区分 優良設定詳細コード１ 商品中分類コード BLコード
                                    // 優良品番(－付き品番)
    dataJudge(goodsMasterDto.getPrimePartsKanaNm(), goodsMasterCommonSingle.get(0).getPrimePartsKanaNm(), goodsFlg); // 優良部品カナ名称
    dataJudge(goodsMasterDto.getPrimePartsName(), goodsMasterCommonSingle.get(0).getPrimePartsName(), goodsFlg); // 優良部品名称
    dataJudge(goodsMasterDto.getNewPrice(), numberFormat(goodsMasterCommonSingle.get(0).getNewPrice()), goodsFlg); // 新価格
    int openPriceDiv = goodsMasterCommonSingle.get(0).getOpenPriceDiv();
    dataJudge(goodsMasterDto.getOpenPriceDiv(), OpenPriceDivEnum.valueof(openPriceDiv).getName(), goodsFlg); // オープン価格区分
    dataJudge(goodsMasterDto.getJan(), goodsMasterCommonSingle.get(0).getJan(), goodsFlg); // JAN
    goodsFlg.append(",0"); // 層別コード
    dataJudge(goodsMasterDto.getEquipName(), goodsMasterCommonSingle.get(0).getEquipName(), goodsFlg); // 装備名称
    String primePartsSpecialNoteC = goodsMasterCommonSingle.get(0).getPrimePartsSpecialNoteC();
    dataJudge(goodsMasterDto.getPrimePartsSpecialNoteC(), primePartsSpecialNoteC, goodsFlg); // 優良部品規格・特記事項(C向け)
    String primePartsSpecialNoteB = goodsMasterCommonSingle.get(0).getPrimePartsSpecialNote();
    dataJudge(goodsMasterDto.getPrimePartsSpecialNoteB(), primePartsSpecialNoteB, goodsFlg); // 優良部品規格・特記事項(B向け)
    int deleteFlg = goodsMasterCommonSingle.get(0).getDeleteFlg();
    dataJudge(goodsMasterDto.getDeleteFlg(), DeleteFlgEnum.valueof(deleteFlg).getName(), goodsFlg); // 削除依頼区分
    dataJudge(goodsMasterDto.getDeleteReason(), goodsMasterCommonSingle.get(0).getDeleteReason(), goodsFlg); // 削除理由
    dataJudge(goodsMasterDto.getGoodsDetailB(), goodsMasterCommonSingle.get(0).getGoodsDetailB(), goodsFlg); // 商品詳細(B向け)
    dataJudge(goodsMasterDto.getGoodsDetailC(), goodsMasterCommonSingle.get(0).getGoodsDetailC(), goodsFlg); // 商品詳細(C向け)
    dataJudge(goodsMasterDto.getGoodsSize1(), numberFormat(goodsMasterCommonSingle.get(0).getGoodsSize1()), goodsFlg); // 商品サイズ(長さ）
    dataJudge(goodsMasterDto.getGoodsSize2(), numberFormat(goodsMasterCommonSingle.get(0).getGoodsSize2()), goodsFlg); // 商品サイズ(幅）
    dataJudge(goodsMasterDto.getGoodsSize3(), numberFormat(goodsMasterCommonSingle.get(0).getGoodsSize3()), goodsFlg); // 商品サイズ(高さ）
    dataJudge(goodsMasterDto.getPackageSize1(), numberFormat(goodsMasterCommonSingle.get(0).getPackageSize1()),
        goodsFlg); // 梱包サイズ(長さ）
    dataJudge(goodsMasterDto.getPackageSize2(), numberFormat(goodsMasterCommonSingle.get(0).getPackageSize2()),
        goodsFlg); // 梱包サイズ(幅）
    dataJudge(goodsMasterDto.getPackageSize3(), numberFormat(goodsMasterCommonSingle.get(0).getPackageSize3()),
        goodsFlg); // 梱包サイズ(高さ）
    String sizeUnit = goodsMasterCommonSingle.get(0).getSizeUnit();
    dataJudge(goodsMasterDto.getSizeUnit(), SizeUnitEnum.valueof(sizeUnit).getName(), goodsFlg); // サイズ単位
    dataJudge(goodsMasterDto.getGoodsWeight(), goodsMasterCommonSingle.get(0).getGoodsWeight(), goodsFlg); // 商品重量
    String weightUnit = goodsMasterCommonSingle.get(0).getWeightUnit();
    dataJudge(goodsMasterDto.getWeightUnit(), WeightUnitEnum.valueof(weightUnit).getName(), goodsFlg); // 重量単位
    dataJudge(goodsMasterDto.getUrl1(), goodsMasterCommonSingle.get(0).getUrl1(), goodsFlg); // URL1
    dataJudge(goodsMasterDto.getUrl2(), goodsMasterCommonSingle.get(0).getUrl2(), goodsFlg); // URL2
    dataJudge(goodsMasterDto.getUrl3(), goodsMasterCommonSingle.get(0).getUrl3(), goodsFlg); // URL3
    dataJudge(goodsMasterDto.getImageNo(), goodsMasterCommonSingle.get(0).getImageNo(), goodsFlg); // 画像数
    goodsFlg.append(",0,0,0"); // 作成日時 更新日時 適用日時
    int blEntryFlg = goodsMasterCommonSingle.get(0).getBlEntryFlg();
    dataJudge(goodsMasterDto.getBlEntryFlg(), BlEntryFlgEnum.valueof(blEntryFlg).getName(), goodsFlg); // BL登録区分
    int errorFlg = goodsMasterCommonSingle.get(0).getErrFlg();
    dataJudge(goodsMasterDto.getErrorFlg(), ErrorFlgEnum.valueof(errorFlg).getName(), goodsFlg); // データステータス
    goodsMasterDto.setGoodsFlg(goodsFlg.toString());
  }

  /**
   * <pre>
   * 【申請詳細】の結合データ
   * </pre>
   *
   * @param makerCode メーカーコード
   * @param logical 論理削除区分
   * @param blApplyResultsFlg BL申請結果区分
   * @param updAccountId 更新アカウントID
   * @param importKbn インポート区分
   * @return 結合マスタdto
   */
  @Override
  public List<JoinMasterDto> searchJoinMaster(int makerCode, int logical, int blApplyResultsFlg, String updAccountId,
                                              int importKbn) {
    List<JoinMasterDto> joinMasterDtoList = new ArrayList<JoinMasterDto>();
    List<JoinMasterMaker> joinMasterMakerList = applyDetailService.searchJoin(makerCode, logical, blApplyResultsFlg, updAccountId, importKbn);
    Map<String, String> selectCodeName = getSelectCodeNameMap(makerCode);
    Map<String, String> classifyCodeGuide = getClassifyCodeGuideMap(makerCode);
    Map<String, String> blCodeName = getBlCodeNameMap(makerCode);
    Map<String, String> carmakerName = getCarmakerNameMap(makerCode);
    Map<String, String> kindCodeName = getKindCodeNameMap(makerCode);
    List<JoinMasterCommon> joinMasterCommonList = applyDetailService.searchJoinCommon(makerCode);

    if (!joinMasterMakerList.isEmpty()) {
      editJoinMaster(joinMasterMakerList, joinMasterCommonList, joinMasterDtoList, selectCodeName, classifyCodeGuide,
          blCodeName, carmakerName, kindCodeName);
    }
    return joinMasterDtoList;
  }

  /**
   * dtoの中で、メーカー側の結合データを保存する
   * 
   * @param joinMasterMakerList メーカー側結合マスタデータ
   * @param joinMasterCommonList 共有側結合マスタデータ
   * @param joinMasterDtoList 結合マスタdto
   * @param selectCodeName セレクトコード名称
   * @param classifyCodeGuide 分類コード名称
   * @param blCodeName BLコード名称
   * @param carmakerName カーコード名称
   * @param kindCodeName 種別コード名称
   */
  public void editJoinMaster(List<JoinMasterMaker> joinMasterMakerList, List<JoinMasterCommon> joinMasterCommonList,
                             List<JoinMasterDto> joinMasterDtoList, Map<String, String> selectCodeName,
                             Map<String, String> classifyCodeGuide, Map<String, String> blCodeName,
                             Map<String, String> carmakerName, Map<String, String> kindCodeName) {
    for (int i = 0; i < joinMasterMakerList.size(); i++) {
      JoinMasterDto joinMasterDto = new JoinMasterDto();
      int applyCondition = joinMasterMakerList.get(i).getApplyCondition(); // 申請状態
      int manageKbn = joinMasterMakerList.get(i).getManageKbn(); // 処理区分
      int prmSetDtlNo1 = joinMasterMakerList.get(i).getPrmSetDtlNo1(); // 優良設定詳細コード１
      int goodsMGroup = joinMasterMakerList.get(i).getGoodsMGroup(); // 商品中分類コード
      int tbsPartsCode = joinMasterMakerList.get(i).getTbsPartsCode(); // BLコード
      int joinSourceMakerCode = joinMasterMakerList.get(i).getJoinSourceMakerCode(); // 結合元メーカーコード
      String joinSourPartsNoWithH = joinMasterMakerList.get(i).getJoinSourPartsNoWithH(); // 結合元品番(－付き品番)
      int prmSetDtlNo2 = joinMasterMakerList.get(i).getPrmSetDtlNo2(); // 優良設定詳細コード2
      int joinDispOrder = joinMasterMakerList.get(i).getJoinDispOrder(); // 結合表示順位
      String joinDestPartsNo = joinMasterMakerList.get(i).getJoinDestPartsNo(); // 結合先品番(－付き品番)
      double joinQty = joinMasterMakerList.get(i).getJoinQty(); // QTY
      String joinSpecialNote = joinMasterMakerList.get(i).getJoinSpecialNote(); // 規格/特記
      String prmPrtsSpecialNote = joinMasterMakerList.get(i).getJoinSpecialNote(); // 規格/特記(一般)
      Short deleteFlag = joinMasterMakerList.get(i).getDeleteFlg(); // 削除依頼区分
      int deleteFlg = deleteFlag == null ? 0 : deleteFlag;
      String delReason = joinMasterMakerList.get(i).getDeleteReason(); // 削除理由
      int blEntryFlg = joinMasterMakerList.get(i).getBlEntryFlg(); // BL登録区分
      int errorFlg = joinMasterMakerList.get(i).getErrorFlg(); // データステータス
      int partsMakerCd = joinMasterMakerList.get(i).getPartsMakerCd(); // 部品メーカーコード
      int checkFlg = joinMasterMakerList.get(i).getCheckFlg(); // チェック区分
      int joinImportKbn = joinMasterMakerList.get(i).getImportKbn(); // インポート区分

      joinMasterDto.setPrtsMakerCd(partsMakerCd); // 部品メーカーコード
      joinMasterDto.setCheckDiv(CheckFlgEnum.valueof(checkFlg).getName()); // チェック区分
      joinMasterDto.setImportKbn(ImportKbnEnum.valueof(joinImportKbn).getName()); // インポート区分
      joinMasterDto.setApplyCondition(ApplyConditionEnum.valueof(applyCondition).getName()); // 申請状態
      joinMasterDto.setManageKbn(ManageKbnEnum.valueof(manageKbn).getName()); // 処理区分
      joinMasterDto
          .setPrmSetDtlNo1(prmSetDtlNo1 == 9999 ? "9999：指定無し" : selectCodeName.get(String.valueOf(prmSetDtlNo1))); // 優良設定詳細コード１
      joinMasterDto.setGoodsMGroup(classifyCodeGuide.get(String.valueOf(goodsMGroup))); // 商品中分類コード
      joinMasterDto.setTbsPartsCode(blCodeName.get(String.valueOf(tbsPartsCode))); // BLコード
      joinMasterDto.setJoinSourceMakerCode(carmakerName.get(String.valueOf(joinSourceMakerCode))); // 結合元メーカーコード
      joinMasterDto.setJoinSourcePartsno(joinSourPartsNoWithH); // 結合元品番(－付き品番)
      joinMasterDto.setPrmSetDtlNo2(kindCodeName.get(String.valueOf(prmSetDtlNo2))); // 優良設定詳細コード2
      joinMasterDto.setJoinDispOrder(joinDispOrder); // 結合表示順位
      joinMasterDto.setJoinDestPartsno(joinDestPartsNo); // 結合先品番(－付き品番)
      joinMasterDto.setJoinQty(joinQty); // QTY
      joinMasterDto.setJoinSpecialNote(joinSpecialNote); // 規格/特記
      joinMasterDto.setPrmPrtsSpecialNote(prmPrtsSpecialNote); // 規格/特記(一般)
      joinMasterDto.setDeleteFlg(DeleteFlgEnum.valueof(deleteFlg).getName()); // 削除依頼区分
      joinMasterDto.setDelReason(delReason); // 削除理由
      joinMasterDto.setInsDtTime(timeFormat(joinMasterMakerList.get(i).getInsDtTime())); // 作成日時
      joinMasterDto.setUpdDtTime(timeFormat(joinMasterMakerList.get(i).getUpdDtTime())); // 更新日時
      joinMasterDto.setApplyTime(timeFormat(joinMasterMakerList.get(i).getStartTime())); // 適用日時
      joinMasterDto.setBlLoginDiv(BlEntryFlgEnum.valueof(blEntryFlg).getName()); // BL登録区分
      joinMasterDto.setErrorFlg(ErrorFlgEnum.valueof(errorFlg).getName()); // データステータス

      if (manageKbn == 1) { // 処理区分は更新の場合
        if (!joinMasterCommonList.isEmpty()) {
          List<JoinMasterCommon> joinMasterCommonSingle = new ArrayList<JoinMasterCommon>();
          for (int j = 0; j < joinMasterCommonList.size(); j++) {
            if (joinMasterCommonList.get(j).getPrmSetDtlNo1() == prmSetDtlNo1
                && joinMasterCommonList.get(j).getTbsPartsCode() == tbsPartsCode
                && joinMasterCommonList.get(j).getJoinSourceMakerCode() == joinSourceMakerCode
                && BroadleafStringUtils.compareTo(joinMasterCommonList.get(j).getJoinSourPartsNoWithH(),
                    joinSourPartsNoWithH) == 0
                && joinMasterCommonList.get(j).getPrmSetDtlNo2() == prmSetDtlNo2
                && joinMasterCommonList.get(j).getJoinDispOrder() == joinDispOrder) {
              joinMasterCommonSingle.add(joinMasterCommonList.get(j)); // 優良設定詳細コード１
                                                                       // BLコード
                                                                       // 結合元メーカーコード
                                                                       // 結合元品番(－付き品番)
                                                                       // 優良設定詳細コード2 結合表示順位について、最新の結合履歴を検索する
              break;
            }
          }
          if (!joinMasterCommonSingle.isEmpty()) {
            StringBuffer joinFlg = new StringBuffer();
            joinFlg.append("0,0,0,0,0,0,0,0,0"); // 申請状態 処理区分 優良設定詳細コード１
                                                 // 商品中分類コード BLコード 結合元メーカーコード
                                                 // 結合元品番(－付き品番) 優良設定詳細コード2
                                                 // 結合表示順位
            String joinDestPartsNoCommon = joinMasterCommonSingle.get(0).getJoinDestPartsNo();
            dataJudge(joinDestPartsNo, joinDestPartsNoCommon, joinFlg); // 結合先品番(－付き品番)
            double joinQtyCommon = joinMasterCommonSingle.get(0).getJoinQty();
            dataJudge(joinQty, joinQtyCommon, joinFlg); // QTY
            String joinSpecialNoteCommon = joinMasterCommonSingle.get(0).getJoinSpecialNote();
            dataJudge(joinSpecialNote, joinSpecialNoteCommon, joinFlg); // 規格/特記
            String prmPrtsSpecialNoteCommon = joinMasterCommonSingle.get(0).getJoinSpecialNote();
            dataJudge(prmPrtsSpecialNote, prmPrtsSpecialNoteCommon, joinFlg); // 規格/特記(一般)
            int deleteFlgCommon = joinMasterCommonSingle.get(0).getDeleteFlg();
            dataJudge(deleteFlg, deleteFlgCommon, joinFlg); // 削除依頼区分
            String delReasonCommon = joinMasterCommonSingle.get(0).getDeleteReason();
            dataJudge(delReason, delReasonCommon, joinFlg); // 削除理由
            joinFlg.append(",0,0,0"); // 作成日時 更新日時 適用日時
            int blEntryFlgCommon = joinMasterCommonSingle.get(0).getBlEntryFlg();
            dataJudge(blEntryFlg, blEntryFlgCommon, joinFlg); // BL登録区分
            int errorFlgCommon = joinMasterCommonSingle.get(0).getErrFlg();
            dataJudge(errorFlg, errorFlgCommon, joinFlg); // データステータス

            joinMasterDto.setJoinFlg(joinFlg.toString());
          }
        }
      }
      joinMasterDtoList.add(joinMasterDto);
    }
  }

  /**
   * <pre>
   * 【申請詳細】のセットデータの獲得
   * </pre>
   *
   * @param makerCode メーカーコード
   * @param logical 論理削除区分
   * @param blApplyResultsFlg BL申請結果区分
   * @param updAccountId 更新アカウントID
   * @param importKbn インポート区分
   * @return セットマスタdto
   */
  @Override
  public List<SetMasterDto> searchSetMaster(int makerCode, int logical, int blApplyResultsFlg, String updAccountId,
                                            int importKbn) {
    List<SetMasterDto> setMasterDtoList = new ArrayList<SetMasterDto>();
    List<SetMasterMaker> setMasterMakerList = applyDetailService.searchSet(makerCode, logical, blApplyResultsFlg,
        updAccountId, importKbn);
    Map<String, String> selectCodeName = getSelectCodeNameMap(makerCode);
    Map<String, String> classifyCodeGuide = getClassifyCodeGuideMap(makerCode);
    Map<String, String> blCodeName = getBlCodeNameMap(makerCode);
    List<SetMasterCommon> setMasterCommonList = applyDetailService.searchSetCommon(makerCode);

    if (!setMasterMakerList.isEmpty()) {
      editSetMsterDto(setMasterMakerList, setMasterCommonList, setMasterDtoList, selectCodeName, classifyCodeGuide,
          blCodeName);
    }
    return setMasterDtoList;
  }

  /**
   * dtoの中で、メーカー側のセットデータを保存する
   * 
   * @param setMasterMakerList セットマスタデータ
   * @param setMasterCommonList 共有側セットマスタデータ
   * @param setMasterDtoList セットマスタdto
   * @param selectCodeName セレクトコード名称
   * @param classifyCodeGuide 分類コード名称
   * @param blCodeName BLコード名称
   */
  public void editSetMsterDto(List<SetMasterMaker> setMasterMakerList, List<SetMasterCommon> setMasterCommonList,
                              List<SetMasterDto> setMasterDtoList, Map<String, String> selectCodeName,
                              Map<String, String> classifyCodeGuide, Map<String, String> blCodeName) {
    for (int i = 0; i < setMasterMakerList.size(); i++) {
      SetMasterDto setMasterDto = new SetMasterDto();
      int applyCondition = setMasterMakerList.get(i).getApplyCondition(); // 申請状態
      int manageKbn = setMasterMakerList.get(i).getManageKbn(); // 処理区分
      int prmSetDtlNo1 = setMasterMakerList.get(i).getPrmSetDtlNo1(); // 優良設定詳細コード１
      int goodsMGroupCd = setMasterMakerList.get(i).getGoodsMGroup(); // 商品中分類コード
      int tbsPartsCode = setMasterMakerList.get(i).getTbsPartsCode(); // BLコード
      String setMainPartsNo = setMasterMakerList.get(i).getSetMainPartsNo(); // セット親品番
      int setDispOrder = setMasterMakerList.get(i).getSetDispOrder(); // 表示順位
      String setSubPartsNo = setMasterMakerList.get(i).getSetSubPartsNo(); // 表示順位
      String setKanaName = setMasterMakerList.get(i).getSetKanaName(); // 品名（半角）
      String setName = setMasterMakerList.get(i).getSetName(); // 品名（全角）
      double setQty = setMasterMakerList.get(i).getSetQty(); // QTY
      String setSpecialNote = setMasterMakerList.get(i).getSetSpecialNote(); // 規格/特記
      Short deleteFlag = setMasterMakerList.get(i).getDeleteFlg(); // 削除依頼区分
      int deleteFlg = deleteFlag == null ? 0 : deleteFlag;
      String deleteReason = setMasterMakerList.get(i).getDeleteReason(); // 削除理由
      int errorFlg = setMasterMakerList.get(i).getErrorFlg(); // データステータス
      int partsMakerCd = setMasterMakerList.get(i).getPartsMakerCd(); // 部品メーカーコード
      int setImportKbn = setMasterMakerList.get(i).getImportKbn(); // インポート区分

      setMasterDto.setPartsMakerCd(partsMakerCd); // 部品メーカーコード
      setMasterDto.setImportKbn(ImportKbnEnum.valueof(setImportKbn).getName()); // インポート区分
      setMasterDto.setApplyCondition(ApplyConditionEnum.valueof(applyCondition).getName()); // 申請状態
      setMasterDto.setManageKbn(ManageKbnEnum.valueof(manageKbn).getName()); // 処理区分
      setMasterDto
          .setPrmSetDtlNo1(prmSetDtlNo1 == 9999 ? "9999：指定無し" : selectCodeName.get(String.valueOf(prmSetDtlNo1))); // 優良設定詳細コード１
      setMasterDto.setGoodsMGroupCd(classifyCodeGuide.get(String.valueOf(goodsMGroupCd))); // 商品中分類コード
      setMasterDto.setBlCode(blCodeName.get(String.valueOf(tbsPartsCode))); // BLコード
      setMasterDto.setSetMainPartsNo(setMainPartsNo); // セット親品番
      setMasterDto.setSetDispOrder(setDispOrder); // 表示順位
      setMasterDto.setSetSubPartsNo(setSubPartsNo); // セット子品番
      setMasterDto.setProductName(setKanaName); // 品名（半角）
      setMasterDto.setSetName(setName); // 品名（全角）
      setMasterDto.setSetQty(setQty); // QTY
      setMasterDto.setSetSpecialNote(setSpecialNote); // 規格/特記
      setMasterDto.setDeleteFlg(DeleteFlgEnum.valueof(deleteFlg).getName()); // 削除依頼区分
      setMasterDto.setDeleteReason(deleteReason); // 削除理由
      setMasterDto.setInsDtTime(timeFormat(setMasterMakerList.get(i).getInsDtTime())); // 作成日時
      setMasterDto.setUpdDtTime(timeFormat(setMasterMakerList.get(i).getUpdDtTime())); // 更新日時
      setMasterDto.setApplyTime(timeFormat(setMasterMakerList.get(i).getStartTime())); // 適用日時
      setMasterDto.setDataStatus(ErrorFlgEnum.valueof(errorFlg).getName()); // データステータス

      if (manageKbn == 1) { // 処理区分は更新の場合
        List<SetMasterCommon> setMasterCommonSingle = new ArrayList<SetMasterCommon>();

        for (int j = 1; j < setMasterCommonList.size(); j++) {
          if (!setMasterCommonList.isEmpty()) {
            if (setMasterCommonList.get(j).getPrmSetDtlNo1() == prmSetDtlNo1
                && BroadleafStringUtils.compareTo(setMasterCommonList.get(j).getSetMainPartsNo(), setMainPartsNo) == 0
                && setMasterCommonList.get(j).getSetDispOrder() == setDispOrder) {
              setMasterCommonSingle.add(setMasterCommonList.get(j)); // 優良設定詳細コード１
                                                                     // セット親品番
                                                                     // 表示順位について、最新のセット履歴を検索する
              break;
            }
          }
          if (!setMasterCommonSingle.isEmpty()) {
            StringBuffer setFlg = new StringBuffer();
            setFlg.append("0,0,0,0,0,0,0"); // 申請状態 処理区分 優良設定詳細コード１ 商品中分類コード
                                            // BLコード // セット親品番 表示順位
            String setSubPartsNoCommon = setMasterCommonSingle.get(0).getSetSubPartsNo();
            dataJudge(setSubPartsNo, setSubPartsNoCommon, setFlg); // セット子品番
            String setKanaNameCommon = setMasterCommonSingle.get(0).getSetKanaName();
            dataJudge(setKanaName, setKanaNameCommon, setFlg); // 品名（半角）
            String setNameCommon = setMasterCommonSingle.get(0).getSetName();
            dataJudge(setName, setNameCommon, setFlg); // 品名（全角）
            double setQtyCommon = setMasterCommonSingle.get(0).getSetQty();
            dataJudge(setQty, setQtyCommon, setFlg); // QTY
            String setSpecialNoteCommon = setMasterCommonSingle.get(0).getSetApecialNote();
            dataJudge(setSpecialNote, setSpecialNoteCommon, setFlg); // 規格/特記
            int deleteFlgCommon = setMasterCommonSingle.get(0).getDeleteFlg();
            dataJudge(deleteFlg, deleteFlgCommon, setFlg); // 削除依頼区分
            String deleteReasonCommon = setMasterCommonSingle.get(0).getDeleteReason();
            dataJudge(deleteReason, deleteReasonCommon, setFlg); // 削除理由
            setFlg.append(",0,0,0"); // 作成日時
            int errorFlgCommon = setMasterCommonSingle.get(0).getErrFlg();
            dataJudge(errorFlg, errorFlgCommon, setFlg); // データステータス

            setMasterDto.setSetFlg(setFlg.toString());
          }
        }
      }
      setMasterDtoList.add(setMasterDto);
    }
  }

  /**
   * <pre>
   * 【申請詳細】共有側の商品データ
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return 商品マスターdto
   */
  @Override
  public List<GoodsMasterDto> searchGoodsMasterCommon(int makerCode, int logicDeleteDiv, int applyNo) {

    List<GoodsMasterDto> goodsMasterDtoList = new ArrayList<GoodsMasterDto>();
    List<GoodsMasterCommon> goodsMasterCommonList = applyDetailService.searchGoodsCommonShow(makerCode, logicDeleteDiv,
        applyNo);
    Map<String, String> selectCodeName = getSelectCodeNameMap(makerCode);
    Map<String, String> classifyCodeGuide = getClassifyCodeGuideMap(makerCode);
    Map<String, String> blCodeName = getBlCodeNameMap(makerCode);
    Map<String, String> partsName = getPartsNameMap(makerCode);
    List<GoodsMasterCommon> goodsMasterCommon = applyDetailService.searchGoodsCommonJudge(makerCode, logicDeleteDiv, applyNo);
    int applyResult = applyDetailService.searchApplyResult(makerCode, applyNo).get(0).getBlApplyResultsFlg();
    if (!goodsMasterCommonList.isEmpty()) {
      editGoodsCommon(goodsMasterCommonList, goodsMasterCommon, goodsMasterDtoList, applyResult, selectCodeName,
          classifyCodeGuide, blCodeName, partsName);
    }

    return goodsMasterDtoList;
  }

  /**
   * dtoの中で、商品データを保存する
   * 
   * @param goodsMasterCommonList 商品マスタリスト
   * @param goodsMasterCommon 履歴商品マスタリスト
   * @param goodsMasterDtoList 商品マスタdto
   * @param selectCodeName セレクトコード
   * @param classifyCodeGuide 商品中分類コード
   * @param blCodeName BLコード
   * @param partsName 層別コード
   * @param applyResult 申請状態
   */
  public void editGoodsCommon(List<GoodsMasterCommon> goodsMasterCommonList, List<GoodsMasterCommon> goodsMasterCommon,
                              List<GoodsMasterDto> goodsMasterDtoList, int applyResult,
                              Map<String, String> selectCodeName, Map<String, String> classifyCodeGuide,
                              Map<String, String> blCodeName, Map<String, String> partsName) {
    for (int i = 0; i < goodsMasterCommonList.size(); i++) {
      GoodsMasterDto goodsMasterDto = new GoodsMasterDto();
      int manageDifferent = goodsMasterCommonList.get(i).getDealFlg(); // 処理区分
      int prmSetDtlNo1 = goodsMasterCommonList.get(i).getPrmSetDtlNo1(); // 優良設定詳細コード１
      int goodsMGroup = goodsMasterCommonList.get(i).getGoodsMGroup(); // 商品中分類コード
      int tbsPartsCode = goodsMasterCommonList.get(i).getTbsPartsCode(); // BLコード
      String primePartsNoWithH = goodsMasterCommonList.get(i).getPrimePartsNoWithH(); // 優良品番(－付き品番)
      String primePartsKanaNm = goodsMasterCommonList.get(i).getPrimePartsKanaNm(); // 優良部品カナ名称
      String primePartsName = goodsMasterCommonList.get(i).getPrimePartsName(); // 優良部品名称
      double newPrice = goodsMasterCommonList.get(i).getNewPrice(); // 新価格
      int openPriceDiv = goodsMasterCommonList.get(i).getOpenPriceDiv(); // オープン価格区分
      Long jan = goodsMasterCommonList.get(i).getJan(); // JAN
      String partsLayerCd = goodsMasterCommonList.get(i).getPartsLayerCd(); // 層別コード
      String equipName = goodsMasterCommonList.get(i).getEquipName(); // 装備名称
      String primePartsSpecialNoteC = goodsMasterCommonList.get(i).getPrimePartsSpecialNoteC(); // 優良部品規格・特記事項(C向け)
      String primePartsSpecialNoteB = goodsMasterCommonList.get(i).getPrimePartsSpecialNote(); // 優良部品規格・特記事項(B向け)
      Short deleteFlag = goodsMasterCommonList.get(i).getDeleteFlg(); // 削除依頼区分
      int deleteFlg = deleteFlag == null ? 0 : deleteFlag;
      String deleteReason = goodsMasterCommonList.get(i).getDeleteReason(); // 削除理由
      String goodsDetailB = goodsMasterCommonList.get(i).getGoodsDetailB(); // 商品詳細(B向け)
      String goodsDetailC = goodsMasterCommonList.get(i).getGoodsDetailC(); // 商品詳細(C向け)
      int goodsSize1 = goodsMasterCommonList.get(i).getGoodsSize1(); // 商品サイズ(長さ）
      int goodsSize2 = goodsMasterCommonList.get(i).getGoodsSize2(); // 商品サイズ(幅）
      int goodsSize3 = goodsMasterCommonList.get(i).getGoodsSize3(); // 商品サイズ(高さ）
      int packageSize1 = goodsMasterCommonList.get(i).getPackageSize1(); // 梱包サイズ(長さ）
      int packageSize2 = goodsMasterCommonList.get(i).getPackageSize2(); // 梱包サイズ(幅）
      int packageSize3 = goodsMasterCommonList.get(i).getPackageSize3(); // 梱包サイズ(高さ）
      String sizeUnit = goodsMasterCommonList.get(i).getSizeUnit(); // サイズ単位
      int goodsWeight = goodsMasterCommonList.get(i).getGoodsWeight(); // 商品重量
      String weightUnit = goodsMasterCommonList.get(i).getWeightUnit(); // 重量単位
      String url1 = goodsMasterCommonList.get(i).getUrl1(); // URL1
      String url2 = goodsMasterCommonList.get(i).getUrl2(); // URL2
      String url3 = goodsMasterCommonList.get(i).getUrl3(); // URL3
      short imageNo = goodsMasterCommonList.get(i).getImageNo(); // 画像数
      int blEntryFlg = goodsMasterCommonList.get(i).getBlEntryFlg(); // BL登録区分
      int errorFlg = goodsMasterCommonList.get(i).getErrFlg(); // データステータス

      goodsMasterDto.setApplyCondition(BlApplyResultsFlgEnum.valueof(applyResult).getName()); // 申請状態
      goodsMasterDto.setManageKbn(ManageKbnEnum.valueof(manageDifferent).getName()); // 処理区分
      goodsMasterDto
          .setPrmSetDtlNo1(prmSetDtlNo1 == 9999 ? "9999：指定無し" : selectCodeName.get(String.valueOf(prmSetDtlNo1))); // 優良設定詳細コード１
      goodsMasterDto.setGoodsMGroup(classifyCodeGuide.get(String.valueOf(goodsMGroup))); // 商品中分類コード
      goodsMasterDto.setTbsPartsCode(blCodeName.get(String.valueOf(tbsPartsCode))); // BLコード
      goodsMasterDto.setPrimePartsNoWithH(primePartsNoWithH); // 優良品番(－付き品番)
      goodsMasterDto.setPrimePartsKanaNm(primePartsKanaNm); // 優良部品カナ名称
      goodsMasterDto.setPrimePartsName(primePartsName); // 優良部品名称
      goodsMasterDto.setNewPrice(numberFormat(newPrice)); // 新価格
      goodsMasterDto.setOpenPriceDiv(OpenPriceDivEnum.valueof(openPriceDiv).getName()); // オープン価格区分
      goodsMasterDto.setJan(jan); // JAN
      goodsMasterDto.setPartsLayerCd(partsName.get(String.valueOf(partsLayerCd))); // 層別コード
      goodsMasterDto.setEquipName(equipName); // 装備名称
      goodsMasterDto.setPrimePartsSpecialNoteC(primePartsSpecialNoteC); // 優良部品規格・特記事項(C向け)
      goodsMasterDto.setPrimePartsSpecialNoteB(primePartsSpecialNoteB); // 優良部品規格・特記事項(B向け)
      goodsMasterDto.setDeleteFlg(DeleteFlgEnum.valueof(deleteFlg).getName()); // 削除依頼区分
      goodsMasterDto.setDeleteReason(deleteReason); // 削除理由
      goodsMasterDto.setGoodsDetailB(goodsDetailB); // 商品詳細(B向け)
      goodsMasterDto.setGoodsDetailC(goodsDetailC); // 商品詳細(C向け)
      goodsMasterDto.setGoodsSize1(numberFormat(goodsSize1)); // 商品サイズ(長さ）
      goodsMasterDto.setGoodsSize2(numberFormat(goodsSize2)); // 商品サイズ(幅）
      goodsMasterDto.setGoodsSize3(numberFormat(goodsSize3)); // 商品サイズ(高さ）
      goodsMasterDto.setPackageSize1(numberFormat(packageSize1)); // 梱包サイズ(長さ）
      goodsMasterDto.setPackageSize2(numberFormat(packageSize2)); // 梱包サイズ(幅）
      goodsMasterDto.setPackageSize3(numberFormat(packageSize3)); // 梱包サイズ(高さ）
      goodsMasterDto.setSizeUnit(SizeUnitEnum.valueof(sizeUnit).getName()); // サイズ単位
      goodsMasterDto.setGoodsWeight(goodsWeight); // 商品重量
      goodsMasterDto.setWeightUnit(WeightUnitEnum.valueof(weightUnit).getName()); // 重量単位
      goodsMasterDto.setUrl1(url1); // URL1
      goodsMasterDto.setUrl2(url2); // URL2
      goodsMasterDto.setUrl3(url3); // URL3
      goodsMasterDto.setImageNo(imageNo); // 画像数
      goodsMasterDto.setInsDtTime(timeFormat(goodsMasterCommonList.get(i).getInsDtTime())); // 作成日時
      goodsMasterDto.setUpdDtTime(timeFormat(goodsMasterCommonList.get(i).getUpdDtTime())); // 更新日時
      goodsMasterDto.setApplyTime(timeFormat(goodsMasterCommonList.get(i).getStartTime())); // 適用日時
      goodsMasterDto.setBlEntryFlg(BlEntryFlgEnum.valueof(blEntryFlg).getName()); // BL登録区分
      goodsMasterDto.setErrorFlg(ErrorFlgEnum.valueof(errorFlg).getName()); // データステータス

      if (!goodsMasterCommon.isEmpty()) {
        List<GoodsMasterCommon> goodsMasterCommonSingle = new ArrayList<GoodsMasterCommon>();
        for (int j = 0; j < goodsMasterCommon.size(); j++) {
          if (goodsMasterCommon.get(j).getPrmSetDtlNo1() == prmSetDtlNo1 && BroadleafStringUtils
              .compareTo(goodsMasterCommon.get(j).getPrimePartsNoWithH(), primePartsNoWithH) == 0) {
            goodsMasterCommonSingle.add(goodsMasterCommon.get(j));
            break;
          }
        }
        if (!goodsMasterCommonSingle.isEmpty()) {
          historyJudgeCommon(goodsMasterDto, goodsMasterCommonSingle);
        }
      }
      goodsMasterDtoList.add(goodsMasterDto);
    }
  }

  /**
   * 共有側の商品データと履歴のデータを比較する
   * 
   * @param goodsMasterDto GoodsMasterDto
   * @param goodsMasterCommonSingle List
   */
  public void historyJudgeCommon(GoodsMasterDto goodsMasterDto, List<GoodsMasterCommon> goodsMasterCommonSingle) {
    StringBuffer goodsFlg = new StringBuffer();
    goodsFlg.append("0,0,0,0,0,0"); // 申請状態 処理区分 優良設定詳細コード１ 商品中分類コード BLコード
                                    // 優良品番(－付き品番)
    dataJudge(goodsMasterDto.getPrimePartsKanaNm(), goodsMasterCommonSingle.get(0).getPrimePartsKanaNm(), goodsFlg); // 優良部品カナ名称
    dataJudge(goodsMasterDto.getPrimePartsName(), goodsMasterCommonSingle.get(0).getPrimePartsName(), goodsFlg); // 優良部品名称
    dataJudge(goodsMasterDto.getNewPrice(), numberFormat(goodsMasterCommonSingle.get(0).getNewPrice()), goodsFlg); // 新価格
    int openPriceDiv = goodsMasterCommonSingle.get(0).getOpenPriceDiv();
    dataJudge(goodsMasterDto.getOpenPriceDiv(), OpenPriceDivEnum.valueof(openPriceDiv).getName(), goodsFlg); // オープン価格区分
    dataJudge(goodsMasterDto.getJan(), goodsMasterCommonSingle.get(0).getJan(), goodsFlg); // JAN
    goodsFlg.append(",0"); // 層別コード
    dataJudge(goodsMasterDto.getEquipName(), goodsMasterCommonSingle.get(0).getEquipName(), goodsFlg); // 装備名称
    String primePartsSpecialNoteCCommon = goodsMasterCommonSingle.get(0).getPrimePartsSpecialNoteC();
    dataJudge(goodsMasterDto.getPrimePartsSpecialNoteC(), primePartsSpecialNoteCCommon, goodsFlg); // 優良部品規格・特記事項(C向け)
    String primePartsSpecialNoteBCommon = goodsMasterCommonSingle.get(0).getPrimePartsSpecialNote();
    dataJudge(goodsMasterDto.getPrimePartsSpecialNoteB(), primePartsSpecialNoteBCommon, goodsFlg); // 優良部品規格・特記事項(B向け)
    int deleteFlg = goodsMasterCommonSingle.get(0).getDeleteFlg();
    dataJudge(goodsMasterDto.getDeleteFlg(), DeleteFlgEnum.valueof(deleteFlg).getName(), goodsFlg); // 削除依頼区分
    dataJudge(goodsMasterDto.getDeleteReason(), goodsMasterCommonSingle.get(0).getDeleteReason(), goodsFlg); // 削除理由
    dataJudge(goodsMasterDto.getGoodsDetailB(), goodsMasterCommonSingle.get(0).getGoodsDetailB(), goodsFlg); // 商品詳細(B向け)
    dataJudge(goodsMasterDto.getGoodsDetailC(), goodsMasterCommonSingle.get(0).getGoodsDetailC(), goodsFlg); // 商品詳細(C向け)
    dataJudge(goodsMasterDto.getGoodsSize1(), numberFormat(goodsMasterCommonSingle.get(0).getGoodsSize1()), goodsFlg); // 商品サイズ(長さ）
    dataJudge(goodsMasterDto.getGoodsSize2(), numberFormat(goodsMasterCommonSingle.get(0).getGoodsSize2()), goodsFlg); // 商品サイズ(幅）
    dataJudge(goodsMasterDto.getGoodsSize3(), numberFormat(goodsMasterCommonSingle.get(0).getGoodsSize3()), goodsFlg); // 商品サイズ(高さ）
    dataJudge(goodsMasterDto.getPackageSize1(), numberFormat(goodsMasterCommonSingle.get(0).getPackageSize1()),
        goodsFlg); // 梱包サイズ(長さ）
    dataJudge(goodsMasterDto.getPackageSize2(), numberFormat(goodsMasterCommonSingle.get(0).getPackageSize2()),
        goodsFlg); // 梱包サイズ(幅）
    dataJudge(goodsMasterDto.getPackageSize3(), numberFormat(goodsMasterCommonSingle.get(0).getPackageSize3()),
        goodsFlg); // 梱包サイズ(高さ）
    String sizeUnit = goodsMasterCommonSingle.get(0).getSizeUnit();
    dataJudge(goodsMasterDto.getSizeUnit(), SizeUnitEnum.valueof(sizeUnit).getName(), goodsFlg); // サイズ単位
    dataJudge(goodsMasterDto.getGoodsWeight(), goodsMasterCommonSingle.get(0).getGoodsWeight(), goodsFlg); // 商品重量
    String weightUnit = goodsMasterCommonSingle.get(0).getWeightUnit();
    dataJudge(goodsMasterDto.getWeightUnit(), WeightUnitEnum.valueof(weightUnit).getName(), goodsFlg); // 重量単位
    dataJudge(goodsMasterDto.getUrl1(), goodsMasterCommonSingle.get(0).getUrl1(), goodsFlg); // URL1
    dataJudge(goodsMasterDto.getUrl2(), goodsMasterCommonSingle.get(0).getUrl2(), goodsFlg); // URL2
    dataJudge(goodsMasterDto.getUrl3(), goodsMasterCommonSingle.get(0).getUrl3(), goodsFlg); // URL3
    dataJudge(goodsMasterDto.getImageNo(), goodsMasterCommonSingle.get(0).getImageNo(), goodsFlg); // 画像数
    goodsFlg.append(",0,0,0"); // 作成日時 更新日時 適用日時
    int blEntryFlg = goodsMasterCommonSingle.get(0).getBlEntryFlg();
    dataJudge(goodsMasterDto.getBlEntryFlg(), BlEntryFlgEnum.valueof(blEntryFlg).getName(), goodsFlg); // BL登録区分
    int errorFlg = goodsMasterCommonSingle.get(0).getErrFlg();
    dataJudge(goodsMasterDto.getErrorFlg(), ErrorFlgEnum.valueof(errorFlg).getName(), goodsFlg); // データステータス

    goodsMasterDto.setGoodsFlg(goodsFlg.toString());
  }

  /**
   * 共有側データベースから、結合データの獲得
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return 結合マスタdto
   */
  @Override
  public List<JoinMasterDto> searchJoinMasterCommon(int makerCode, int logicDeleteDiv, int applyNo) {
    List<JoinMasterDto> joinMasterDtoList = new ArrayList<JoinMasterDto>();

    List<JoinMasterCommon> joinMasterCommonList = applyDetailService.searchJoinCommonShow(makerCode, logicDeleteDiv, applyNo);
    Map<String, String> selectCodeName = getSelectCodeNameMap(makerCode);
    Map<String, String> classifyCodeGuide = getClassifyCodeGuideMap(makerCode);
    Map<String, String> blCodeName = getBlCodeNameMap(makerCode);
    Map<String, String> carmakerName = getCarmakerNameMap(makerCode);
    Map<String, String> kindCodeName = getKindCodeNameMap(makerCode);
    List<JoinMasterCommon> joinMasterCommon = applyDetailService.searchJoinCommonJudge(makerCode, logicDeleteDiv, applyNo);
    int applyResult = applyDetailService.searchApplyResult(makerCode, applyNo).get(0).getBlApplyResultsFlg();
    if (!joinMasterCommonList.isEmpty()) {
      editJoinCommon(joinMasterCommonList, joinMasterCommon, joinMasterDtoList, applyResult, selectCodeName,
          classifyCodeGuide, blCodeName, carmakerName, kindCodeName);
    }
    return joinMasterDtoList;
  }

  /**
   * dtoの中で、セットデータを保存する
   * 
   * @param joinMasterCommonList 結合マスタデータ
   * @param joinMasterCommon 履歴結合マスタデータ
   * @param joinMasterDtoList 結合マスタdto
   * @param selectCodeName セレクトコード名称
   * @param classifyCodeGuide 分類コード名称
   * @param blCodeName BLコード名称
   * @param carmakerName カーコード名称
   * @param kindCodeName 種別コード名称
   * @param applyResult 申請状態
   */
  public void editJoinCommon(List<JoinMasterCommon> joinMasterCommonList, List<JoinMasterCommon> joinMasterCommon,
                             List<JoinMasterDto> joinMasterDtoList, int applyResult, Map<String, String> selectCodeName,
                             Map<String, String> classifyCodeGuide, Map<String, String> blCodeName,
                             Map<String, String> carmakerName, Map<String, String> kindCodeName) {
    int len = joinMasterCommonList.size();
    for (int i = 0; i < len; i++) {
      JoinMasterDto joinMasterDto = new JoinMasterDto();
      int manageKbn = joinMasterCommonList.get(i).getDealFlg(); // 処理区分
      int prmSetDtlNo1 = joinMasterCommonList.get(i).getPrmSetDtlNo1(); // 優良設定詳細コード１
      int joinMGroup = joinMasterCommonList.get(i).getGoodsMGroup(); // 商品中分類コード
      int tbsPartsCode = joinMasterCommonList.get(i).getTbsPartsCode(); // BLコード
      int joinSourceMakerCode = joinMasterCommonList.get(i).getJoinSourceMakerCode(); // 結合元メーカーコード
      String joinSourPartsNoWithH = joinMasterCommonList.get(i).getJoinSourPartsNoWithH(); // 結合元品番(－付き品番)
      int prmSetDtlNo2 = joinMasterCommonList.get(i).getPrmSetDtlNo2(); // 優良設定詳細コード2
      int joinDispOrder = joinMasterCommonList.get(i).getJoinDispOrder(); // 結合表示順位
      String joinDestPartsNo = joinMasterCommonList.get(i).getJoinDestPartsNo(); // 結合先品番(－付き品番)
      double joinQty = joinMasterCommonList.get(i).getJoinQty(); // QTY
      String joinSpecialNote = joinMasterCommonList.get(i).getJoinSpecialNote(); // 規格/特記
      String prmPrtsSpecialNote = joinMasterCommonList.get(i).getJoinSpecialNote(); // 規格/特記(一般)
      Short deleteFlag = joinMasterCommonList.get(i).getDeleteFlg(); // 削除依頼区分
      int deleteFlg = deleteFlag == null ? 0 : deleteFlag;
      String delReason = joinMasterCommonList.get(i).getDeleteReason(); // 削除理由
      int blEntryFlg = joinMasterCommonList.get(i).getBlEntryFlg(); // BL登録区分
      int errorFlg = joinMasterCommonList.get(i).getErrFlg(); // データステータス

      joinMasterDto.setApplyCondition(BlApplyResultsFlgEnum.valueof(applyResult).getName()); // 申請状態
      joinMasterDto.setManageKbn(ManageKbnEnum.valueof(manageKbn).getName()); // 処理区分
      joinMasterDto
          .setPrmSetDtlNo1(prmSetDtlNo1 == 9999 ? "9999：指定無し" : selectCodeName.get(String.valueOf(prmSetDtlNo1))); // 優良設定詳細コード１
      joinMasterDto.setGoodsMGroup(classifyCodeGuide.get(String.valueOf(joinMGroup))); // 商品中分類コード
      joinMasterDto.setTbsPartsCode(blCodeName.get(String.valueOf(tbsPartsCode))); // BLコード
      joinMasterDto.setJoinSourceMakerCode(carmakerName.get(String.valueOf(joinSourceMakerCode))); // 結合元メーカーコード
      joinMasterDto.setJoinSourcePartsno(joinSourPartsNoWithH); // 結合元品番(－付き品番)
      joinMasterDto.setPrmSetDtlNo2(kindCodeName.get(String.valueOf(prmSetDtlNo2))); // 優良設定詳細コード2
      joinMasterDto.setJoinDispOrder(joinDispOrder); // 結合表示順位
      joinMasterDto.setJoinDestPartsno(joinDestPartsNo); // 結合先品番(－付き品番)
      joinMasterDto.setJoinQty(joinQty); // QTY
      joinMasterDto.setJoinSpecialNote(joinSpecialNote); // 規格/特記
      joinMasterDto.setPrmPrtsSpecialNote(prmPrtsSpecialNote); // 規格/特記(一般)
      joinMasterDto.setDeleteFlg(DeleteFlgEnum.valueof(deleteFlg).getName()); // 削除依頼区分
      joinMasterDto.setDelReason(delReason); // 削除理由
      joinMasterDto.setInsDtTime(timeFormat(joinMasterCommonList.get(i).getInsDtTime())); // 作成日時
      joinMasterDto.setUpdDtTime(timeFormat(joinMasterCommonList.get(i).getUpdDtTime())); // 更新日時
      joinMasterDto.setApplyTime(timeFormat(joinMasterCommonList.get(i).getStartTime())); // 適用日時
      joinMasterDto.setBlLoginDiv(BlEntryFlgEnum.valueof(blEntryFlg).getName()); // BL登録区分
      joinMasterDto.setErrorFlg(ErrorFlgEnum.valueof(errorFlg).getName()); // データステータス

      if (!joinMasterCommon.isEmpty()) {
        List<JoinMasterCommon> joinMasterCommonSingle = new ArrayList<JoinMasterCommon>();
        for (int j = 0; j < joinMasterCommon.size(); j++) {
          if (joinMasterCommon.get(j).getPrmSetDtlNo1() == prmSetDtlNo1
              && joinMasterCommon.get(j).getTbsPartsCode() == tbsPartsCode
              && joinMasterCommon.get(j).getJoinSourceMakerCode() == joinSourceMakerCode
              && joinMasterCommon.get(j).getPrmSetDtlNo2() == prmSetDtlNo2
              && joinMasterCommon.get(j).getJoinSourPartsNoWithH().equals(joinSourPartsNoWithH)
              && joinMasterCommon.get(j).getJoinDispOrder() == joinDispOrder) {
            joinMasterCommonSingle.add(joinMasterCommon.get(j));
            break;
          }
        }
        if (!(joinMasterCommonSingle.isEmpty())) {
          StringBuffer joinFlg = new StringBuffer();

          joinFlg.append("0,0,0,0,0,0,0,0,0"); // 申請状態 処理区分 優良設定詳細コード１ 商品中分類コード
                                               // BLコード 結合元メーカーコード 結合元品番(－付き品番)
          String joinDestPartsNoCommon = joinMasterCommonSingle.get(0).getJoinDestPartsNo();
          dataJudge(joinDestPartsNo, joinDestPartsNoCommon, joinFlg); // 結合先品番(－付き品番)
          double joinQtyCommon = joinMasterCommonSingle.get(0).getJoinQty();
          dataJudge(joinQty, joinQtyCommon, joinFlg); // QTY
          String joinSpecialNoteCommon = joinMasterCommonSingle.get(0).getJoinSpecialNote();
          dataJudge(joinSpecialNote, joinSpecialNoteCommon, joinFlg); // 規格/特記
          String prmPrtsSpecialNoteCommon = joinMasterCommonSingle.get(0).getJoinSpecialNote();
          dataJudge(prmPrtsSpecialNote, prmPrtsSpecialNoteCommon, joinFlg); // 規格/特記(一般)
          int deleteFlgCommon = joinMasterCommonSingle.get(0).getDeleteFlg();
          dataJudge(deleteFlg, deleteFlgCommon, joinFlg); // 削除依頼区分
          String delReasonCommon = joinMasterCommonSingle.get(0).getDeleteReason();
          dataJudge(delReason, delReasonCommon, joinFlg); // 削除理由
          joinFlg.append(",0,0,0"); // 作成日時 更新日時 適用日時
          int blEntryFlgCommon = joinMasterCommonSingle.get(0).getBlEntryFlg();
          dataJudge(blEntryFlg, blEntryFlgCommon, joinFlg); // BL登録区分
          int errorFlgCommon = joinMasterCommonSingle.get(0).getErrFlg();
          dataJudge(errorFlg, errorFlgCommon, joinFlg); // データステータス

          joinMasterDto.setJoinFlg(joinFlg.toString());
        }
      }
      joinMasterDtoList.add(joinMasterDto);
    }
  }

  /**
   * 共有側データベースから、セットデータが獲得
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return セットマスタdto
   */
  @Override
  public List<SetMasterDto> searchSetMasterCommon(int makerCode, int logicDeleteDiv, int applyNo) {
    List<SetMasterDto> setMasterDtoList = new ArrayList<SetMasterDto>();
    List<SetMasterCommon> setMasterCommonList = applyDetailService.searchSetCommonShow(makerCode, logicDeleteDiv,
        applyNo);
    Map<String, String> selectCodeName = getSelectCodeNameMap(makerCode);
    Map<String, String> classifyCodeGuide = getClassifyCodeGuideMap(makerCode);
    Map<String, String> blCodeName = getBlCodeNameMap(makerCode);
    List<SetMasterCommon> setMasterCommon = applyDetailService.searchSetCommonJudge(makerCode, logicDeleteDiv, applyNo);
    int applyResult = applyDetailService.searchApplyResult(makerCode, applyNo).get(0).getBlApplyResultsFlg();
    if (!setMasterCommonList.isEmpty()) {
      editSetCommon(setMasterCommonList, setMasterCommon, setMasterDtoList, applyResult, selectCodeName,
          classifyCodeGuide, blCodeName);
    }

    return setMasterDtoList;
  }

  /**
   * dtoの中で、共有側セットデータを保存する
   * 
   * @param setMasterCommonList セットマスタデータ
   * @param setMasterCommon 共有側セットマスタデータ
   * @param setMasterDtoList セットマスタdto
   * @param selectCodeName セレクトコード名称
   * @param classifyCodeGuide 分類コード名称
   * @param blCodeName BLコード名称
   * @param applyResult 申請状態
   */
  public void editSetCommon(List<SetMasterCommon> setMasterCommonList, List<SetMasterCommon> setMasterCommon,
                            List<SetMasterDto> setMasterDtoList, int applyResult, Map<String, String> selectCodeName,
                            Map<String, String> classifyCodeGuide, Map<String, String> blCodeName) {
    int len = setMasterCommonList.size();
    for (int i = 0; i < len; i++) {
      SetMasterDto setMasterDto = new SetMasterDto();
      int manageKbn = setMasterCommonList.get(i).getDealFlg(); // 処理区分
      int prmSetDtlNo1 = setMasterCommonList.get(i).getPrmSetDtlNo1(); // 優良設定詳細コード１
      int goodsMGroupCd = setMasterCommonList.get(i).getGoodsMGroup(); // 商品中分類コード
      int tbsPartsCode = setMasterCommonList.get(i).getTbsPartsCode(); // BLコード
      String setMainPartsNo = setMasterCommonList.get(i).getSetMainPartsNo(); // セット親品番
      int setDispOrder = setMasterCommonList.get(i).getSetDispOrder(); // 表示順位
      String setSubPartsNo = setMasterCommonList.get(i).getSetSubPartsNo(); // 表示順位
      String setKanaName = setMasterCommonList.get(i).getSetKanaName(); // 品名（半角）
      String setName = setMasterCommonList.get(i).getSetName(); // 品名（全角）
      double setQty = setMasterCommonList.get(i).getSetQty(); // QTY
      String setSpecialNote = setMasterCommonList.get(i).getSetApecialNote(); // 規格/特記
      Short deleteFlag = setMasterCommonList.get(i).getDeleteFlg(); // 削除依頼区分
      int deleteFlg = deleteFlag == null ? 0 : deleteFlag;
      String deleteReason = setMasterCommonList.get(i).getDeleteReason(); // 削除理由
      int errorFlg = setMasterCommonList.get(i).getErrFlg(); // データステータス

      setMasterDto.setApplyCondition(BlApplyResultsFlgEnum.valueof(applyResult).getName()); // 申請状態
      setMasterDto.setManageKbn(ManageKbnEnum.valueof(manageKbn).getName()); // 処理区分
      setMasterDto
          .setPrmSetDtlNo1(prmSetDtlNo1 == 9999 ? "9999：指定無し" : selectCodeName.get(String.valueOf(prmSetDtlNo1))); // 優良設定詳細コード１
      setMasterDto.setGoodsMGroupCd(classifyCodeGuide.get(String.valueOf(goodsMGroupCd))); // 商品中分類コード
      setMasterDto.setBlCode(blCodeName.get(String.valueOf(tbsPartsCode))); // BLコード
      setMasterDto.setSetMainPartsNo(setMainPartsNo); // セット親品番
      setMasterDto.setSetDispOrder(setDispOrder); // 表示順位
      setMasterDto.setSetSubPartsNo(setSubPartsNo); // セット子品番
      setMasterDto.setProductName(setKanaName); // 品名（半角）
      setMasterDto.setSetName(setName); // 品名（全角）
      setMasterDto.setSetQty(setQty); // QTY
      setMasterDto.setSetSpecialNote(setSpecialNote); // 規格/特記
      setMasterDto.setDeleteFlg(DeleteFlgEnum.valueof(deleteFlg).getName()); // 削除依頼区分
      setMasterDto.setDeleteReason(deleteReason); // 削除理由
      setMasterDto.setInsDtTime(timeFormat(setMasterCommonList.get(i).getInsDtTime())); // 作成日時
      setMasterDto.setUpdDtTime(timeFormat(setMasterCommonList.get(i).getUpdDtTime())); // 更新日時
      setMasterDto.setApplyTime(timeFormat(setMasterCommonList.get(i).getStartTime())); // 適用日時
      setMasterDto.setDataStatus(ErrorFlgEnum.valueof(errorFlg).getName()); // データステータス

      if (!setMasterCommon.isEmpty()) {
        List<SetMasterCommon> setMasterCommonSingle = new ArrayList<SetMasterCommon>();
        for (int j = 0; j < setMasterCommon.size(); j++) {
          if (setMasterCommon.get(j).getPrmSetDtlNo1() == prmSetDtlNo1
              && setMasterCommon.get(j).getSetMainPartsNo().equals(setMainPartsNo)
              && setMasterCommon.get(j).getSetDispOrder() == setDispOrder) {
            setMasterCommonSingle.add(setMasterCommon.get(j));
            break;
          }
        }
        if (!(setMasterCommonSingle.isEmpty())) {
          StringBuffer setFlg = new StringBuffer();
          setFlg.append("0,0,0,0,0,0,0"); // 申請状態 処理区分 優良設定詳細コード１ 商品中分類コード BLコード
                                          // セット親品番 表示順位
          String setSubPartsNoCommon = setMasterCommonList.get(0).getSetSubPartsNo();
          dataJudge(setSubPartsNo, setSubPartsNoCommon, setFlg); // セット子品番
          String setKanaNameCommon = setMasterCommonList.get(0).getSetKanaName();
          dataJudge(setKanaName, setKanaNameCommon, setFlg); // 品名（半角）
          String setNameCommon = setMasterCommonList.get(0).getSetName();
          dataJudge(setName, setNameCommon, setFlg); // 品名（全角）
          double setQtyCommon = setMasterCommonList.get(0).getSetQty();
          dataJudge(setQty, setQtyCommon, setFlg); // QTY
          String setSpecialNoteCommon = setMasterCommonList.get(0).getSetApecialNote();
          dataJudge(setSpecialNote, setSpecialNoteCommon, setFlg); // 規格/特記
          int deleteFlgCommon = setMasterCommonList.get(0).getDeleteFlg();
          dataJudge(deleteFlg, deleteFlgCommon, setFlg); // 削除依頼区分
          String deleteReasonCommon = setMasterCommonList.get(0).getDeleteReason();
          dataJudge(deleteReason, deleteReasonCommon, setFlg); // 削除理由
          setFlg.append(",0,0,0"); // 作成日時 更新日時 適用日時
          int errorFlgCommon = setMasterCommonList.get(0).getErrFlg();
          dataJudge(errorFlg, errorFlgCommon, setFlg); // データステータス

          setMasterDto.setSetFlg(setFlg.toString());
        }
      }
      setMasterDtoList.add(setMasterDto);
    }
  }

  /**
   * <pre>
   *　申請データと申請履歴データは一致するかどうかを比較する
   * </pre>
   * 
   * @param maker Object
   * @param common Object
   * @param flag 一致するフラッグ
   */
  public void dataJudge(Object maker, Object common, StringBuffer flag) {
    if (maker == null) {
      if (common == null) {
        flag.append(",0");
      } else {
        flag.append(",1");
      }
    } else {
      if (!maker.equals(common)) {
        flag.append(",1");
      } else {
        flag.append(",0");
      }
    }
  }

  /**
   * <pre>
   * 時間のフォーマットの限定
   * </pre>
   * 
   * @param timeStamp 時間
   * @return applyDtTime
   */
  public String timeFormat(Timestamp timeStamp) {
    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    String applyDtTime = sdf.format(timeStamp);
    return applyDtTime;
  }

  /**
   * 数字文字列のフォーマットを処理する
   * 
   * @param number 数字文字列(null禁止)
   * @return コマ化した文字列
   */
  public String numberFormat(Object number) {
    String num = number.toString();
    boolean shortLine = false;
    boolean isPoint = false;
    String numberleft = null;
    String numberright = null;
    if (!num.equals("")) {
      if (0 < num.indexOf(".")) {
        isPoint = true;
        numberleft = num.substring(0, num.indexOf("."));
        numberright = num.substring(num.indexOf("."), num.length());
      } else {
        numberleft = num;
      }
      if (numberleft.startsWith("-")) {
        shortLine = true;
        numberleft = numberleft.substring(1);
      }
      while (numberleft.startsWith("0") && 1 < numberleft.length()) {
        numberleft = numberleft.substring(1);
      }
      final int len = numberleft.length();
      for (int i = len - 3; i > 0; i -= 3) {
        numberleft = numberleft.substring(0, i) + "," + numberleft.substring(i);
      }
      if (shortLine) {
        numberleft = "-" + numberleft;
      }
      if (isPoint) {
        num = numberleft + numberright;
      } else {
        num = numberleft;
      }
    }
    return num;
  }

  /**
   * <pre>
   * 【申請詳細】ページ内行数設定の取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @return 【申請詳細】ページ内行数
   */
  public int searchApplyDetailRows(int makerCode) {
    return companyInfoMasterCommonService.getCompanyInfo(makerCode).getApplyDetailRows();
  }

  /**
   * <pre>
   * BLコード名称マップを取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @return BLコード名称マップ
   */
  @Override
  public Map<String, String> getBlCodeNameMap(int makerCode) {
    Map<String, String> blCodeNameMap = new HashMap<String, String>();
    List<BlCodeMasterCommon> blCodeMasterInfo = blCodeMasterCommonService.getBlCodeMasterInfo(makerCode);
    if (blCodeMasterInfo != null && !blCodeMasterInfo.isEmpty()) {
      for (BlCodeMasterCommon blInfo : blCodeMasterInfo) {
        String blCode = String.valueOf(blInfo.getBlCode());
        if (!blCodeNameMap.containsKey(blCode)) {
          blCodeNameMap.put(blCode, blCode.concat("：").concat(blInfo.getBlFullName()));
        }
      }
    }
    return blCodeNameMap;
  }

  /**
   * <pre>
   * * セレクトコードマスト名称マップを取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @return セレクトコードマスト名称マップ
   */
  @Override
  public Map<String, String> getSelectCodeNameMap(int makerCode) {
    Map<String, String> selectNameMap = new HashMap<String, String>();
    List<SelectCodeMasterCommon> searchSelectCode = selectCodeMasterCommonService.getSelectCodeInfo(makerCode);
    if (searchSelectCode != null && !searchSelectCode.isEmpty()) {
      for (SelectCodeMasterCommon selectInfo : searchSelectCode) {
        String selectCode = String.valueOf(selectInfo.getPrmSetDtlNo1());
        if (!selectNameMap.containsKey(selectCode)) {
          selectNameMap.put(selectCode, selectCode.concat("：").concat(selectInfo.getPrmSetDtlName()));
        }
      }
    }
    return selectNameMap;
  }

  /**
   * <pre>
   * * 種別コード名称マップを取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @return 種別コード
   */
  @Override
  public Map<String, String> getKindCodeNameMap(int makerCode) {
    Map<String, String> kindCodeNameMap = new HashMap<String, String>();
    List<KindCodeMasterCommon> kindCodeInfo = kindCodeMasterCommonService.getKindCodeInfo(makerCode);
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

  /**
   * <pre>
   * * カーメーカーコード名称マップを取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @return カーメーカーコード
   */
  @Override
  public Map<String, String> getCarmakerNameMap(int makerCode) {
    Map<String, String> carmakerNameMap = new HashMap<String, String>();
    List<CarmakerMasterCommon> carmakerInfo = carmakerMasterCommonService.getCarmakerMasterInfo(makerCode);
    if (carmakerInfo != null && !carmakerInfo.isEmpty()) {
      for (CarmakerMasterCommon carmakerMasterCommon : carmakerInfo) {
        String carmakerCode = String.valueOf(carmakerMasterCommon.getMakerCode());
        if (!carmakerNameMap.containsKey(carmakerCode)) {
          carmakerNameMap.put(carmakerCode, carmakerCode.concat("：").concat(carmakerMasterCommon.getMakerFullName()));
        }
      }
    }
    return carmakerNameMap;
  }

  /**
   * <pre>
   * * 商品中分類コードマップを取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @return 商品中分類コード
   */
  @Override
  public Map<String, String> getClassifyCodeGuideMap(int makerCode) {
    Map<String, String> partsNameMap = new LinkedHashMap<String, String>();
    List<GoodsRateMasterCommon> searchClassifyCodeGuideCode = classifyCodeGuideService
        .getPrimeByCode(GoodsRateMasterCommon.LOGICAL_UNDELETED, makerCode, "", "");
    if (searchClassifyCodeGuideCode != null && !searchClassifyCodeGuideCode.isEmpty()) {
      for (GoodsRateMasterCommon classifyCodeGuide : searchClassifyCodeGuideCode) {
        String classifyCode = String.valueOf(classifyCodeGuide.getGoodsRateGrpCode());
        if (!partsNameMap.containsKey(classifyCode)) {
          partsNameMap.put(classifyCode, classifyCode.concat("：").concat(classifyCodeGuide.getGoodsRateGrpName()));
        }
      }
    }
    return partsNameMap;
  }

  /**
   * <pre>
   * * 層別名称マップを取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @return 層別
   */
  @Override
  public Map<String, String> getPartsNameMap(int makerCode) {
    Map<String, String> partsNameMap = new LinkedHashMap<String, String>();
    List<PartsMasterCommon> searchPartsCode = partsMasterCommonService.getPartsMasterInfo(makerCode);
    if (searchPartsCode != null && !searchPartsCode.isEmpty()) {
      for (PartsMasterCommon partsInfo : searchPartsCode) {
        String partsCode = String.valueOf(partsInfo.getPartsLayer());
        if (!partsNameMap.containsKey(partsCode)) {
          partsNameMap.put(partsCode, partsCode.concat("：").concat(partsInfo.getPartsLayerName()));
        }
      }
    }
    return partsNameMap;
  }
}
