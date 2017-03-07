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

import jp.co.broadleaf.breg.classifycodeguide.service.ClassifyCodeGuideService;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerRealTime;
import jp.co.broadleaf.breg.common.entity.GoodsRateMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.PartsMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.enums.CheckFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.common.enums.JudgeEnum;
import jp.co.broadleaf.breg.common.enums.OpenPriceDivEnum;
import jp.co.broadleaf.breg.common.service.BlCodeMasterCommonService;
import jp.co.broadleaf.breg.common.service.PartsMasterCommonService;
import jp.co.broadleaf.breg.common.service.SelectCodeMasterCommonService;
import jp.co.broadleaf.breg.companysetting.service.CompanyInfoMasterCommonService;
import jp.co.broadleaf.breg.goodscommon.service.GoodsMasterCommonService;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterMakerModel;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterSearchModel;
import jp.co.broadleaf.breg.goodslist.facade.GoodsMasterMakerFacade;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsGridDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerInfoDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterSearchDto;
import jp.co.broadleaf.breg.goodslist.service.GoodsMasterMakerService;
import jp.co.broadleaf.breg.joinlist.service.JoinMasterService;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.breg.selectmaker.service.SelectMakerService;
import jp.co.broadleaf.breg.setlist.service.SetMasterService;
import jp.co.broadleaf.common.message.MessageResolver;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.framework.security.SecurityContextHolder;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * <pre>
 *  商品マスタ(メーカー)Facadeクラス.
 * </pre>
 */
public class GoodsMasterMakerFacadeImpl implements GoodsMasterMakerFacade {

  /** 優良設定詳細コード１ **/
  public static final String PRM_SET_DTL_NO_1 = "セレクトコード名称";
  /** 商品中分類コード **/
  private static final String GOODS_M_GROUP = "分類コード名称";
  /** BLコード **/
  private static final String TBS_PARTS_CODE = "BLコード名称";
  /** 優良部品名称 **/
  private static final String PRIME_PARTS_NAME = "品名（全角）";
  /** 優良部品カナ名称 **/
  private static final String PRIME_PARTS_KANA_NAME = "品名（半角）";
  /** 新価格 **/
  private static final String NEW_PRICE = "価格（税抜）";
  /** オープン価格区分 **/
  private static final String OPEN_PRICE = "OPENプライス";
  /** 優良品番 **/
  private static final String PRIME_PARTS_NO = "優良品番";
  /** 適用日時 **/
  public static final String START_TIME = "適用日時";
  /** 削除理由 **/
  public static final String DELETE_REASON = "削除理由";

  /** 商品マスタ(メーカー)サービス */
  private GoodsMasterMakerService goodsMasterService;

  /**
   * <pre>
   * 商品マスタ(メーカー)サービス.
   * </pre>
   * 
   * @param goodsMasterService 商品マスタ(メーカー)サービス
   */
  @Resource
  public void setGoodsMasterService(GoodsMasterMakerService goodsMasterService) {
    this.goodsMasterService = goodsMasterService;
  }

  /** BLコードマスタ情報サービス */
  private BlCodeMasterCommonService blCodeMasterCommonService;

  /**
   * <pre>
   * BLコードマスタ情報サービス.
   * </pre>
   * 
   * @param blCodeMasterCommonService BLコードマスタ情報サービス
   */
  @Resource
  public void setBlCodeMasterCommonService(BlCodeMasterCommonService blCodeMasterCommonService) {
    this.blCodeMasterCommonService = blCodeMasterCommonService;
  }

  /** BLコードマスタ情報サービス */
  private SelectCodeMasterCommonService selectCodeMasterCommonService;

  /**
   * <pre>
   * セレクトコードマスト
   * </pre>
   * 
   * @param selectCodeMasterCommonService セレクトコードマスト
   */
  @Resource
  public void setSelectCodeMasterCommonService(SelectCodeMasterCommonService selectCodeMasterCommonService) {
    this.selectCodeMasterCommonService = selectCodeMasterCommonService;
  }

  /** 層別マスタ情報サービス */
  private PartsMasterCommonService partsMasterCommonService;

  /**
   * <pre>
   * 層別マスタ情報サービス.
   * </pre>
   * 
   * @param partsMasterCommonService 層別マスタ情報サービス
   */
  @Resource
  public void setPartsMasterCommonService(PartsMasterCommonService partsMasterCommonService) {
    this.partsMasterCommonService = partsMasterCommonService;
  }

  /** 商品マスタ(common)のサービス */
  private GoodsMasterCommonService goodsMasterCommonService;

  /**
   * <pre>
   * 商品マスタ(common)
   * </pre>
   * 
   * @param goodsMasterCommonService 商品マスタ(common)
   */
  @Resource
  public void setGoodsMasterCommonService(GoodsMasterCommonService goodsMasterCommonService) {
    this.goodsMasterCommonService = goodsMasterCommonService;
  }

  /** Messageサービス */
  private MessageService messageService;

  /**
   * Messageサービス.
   * 
   * @param messageService サービス
   */
  @Resource
  public void setMessageService(MessageService messageService) {
    this.messageService = messageService;
  }

  /** 会社情報サービス */
  private CompanyInfoMasterCommonService companyInfoMasterCommonService;

  /**
   * <pre>
   * 会社情報サービス.
   * </pre>
   * 
   * @param companyInfoMasterCommonService 会社情報サービス
   */
  @Resource
  public void setCompanyInfoMasterCommonService(CompanyInfoMasterCommonService companyInfoMasterCommonService) {
    this.companyInfoMasterCommonService = companyInfoMasterCommonService;
  }

  /** チェック選択検索サビース **/
  private SelectMakerService selectMakerService;

  /**
   * <pre>
   * チェック選択検索サビースを設定する。
   * </pre>
   *
   * @param selectMakerService チェック選択検索サビース
   */
  @Resource
  public void setSelectMakerService(SelectMakerService selectMakerService) {
    this.selectMakerService = selectMakerService;
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

  /** セットマスタ(メーカー)サービス */
  private SetMasterService setMasterService;

  /**
   * <pre>
   * セットマスタ(メーカー)サービス.
   * </pre>
   * 
   * @param setMasterService セットマスタ(メーカー)サービス
   */
  @Resource
  public void setSetMasterService(SetMasterService setMasterService) {
    this.setMasterService = setMasterService;
  }

  /** 結合マスタ(メーカー)サービス */
  private JoinMasterService joinMasterService;

  /**
   * <pre>
   * 結合マスタ(メーカー)サービス.
   * </pre>
   * 
   * @param joinMasterService 結合マスタ(メーカー)サービス
   */
  @Resource
  public void setJoinMasterService(JoinMasterService joinMasterService) {
    this.joinMasterService = joinMasterService;
  }

  /**
   * MessageResolver
   */
  private MessageResolver messageResolver;

  /**
   * <pre>
   * MessageResolver.
   * </pre>
   * 
   * @param messageResolver MessageResolver
   */
  @Resource
  public void setMessageResolver(MessageResolver messageResolver) {
    this.messageResolver = messageResolver;
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
   * @param mode 画面mode
   * @return 商品マスタ(メーカー)Dto
   */
  @Override
  public GoodsMasterMakerInfoDto searchGoodsMasterInfoList(GoodsMasterSearchDto goodsMasterSearchDto, int order,
                                                           Long skipRows, Long maxRows, Boolean isPage, int mode) {
    GoodsMasterMakerInfoDto goodsMasterInfoDto = new GoodsMasterMakerInfoDto();
    GoodsMasterMakerModel goodsMasterMakerModel = goodsMasterService.searchGoodsInfoList(
        convertGoodsMasterSearchModel(goodsMasterSearchDto), order, skipRows, maxRows, isPage, mode);
    goodsMasterInfoDto.setGoodsMasterDto(convertGoodsMasterDtoList(goodsMasterMakerModel));
    goodsMasterInfoDto.setSearchCounts(goodsMasterMakerModel.getSearchCounts());
    return goodsMasterInfoDto;
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param primePartsNoWithH primePartsNoWithH
   * @param makerCode 部品メーカーコード
   * @param prmSetDtlNo1 prmSetDtlNo1
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public GoodsMasterMakerInfoDto searchById(int primePartsNoWithH, int makerCode, String prmSetDtlNo1) {
    GoodsMasterMaker goodsMasterMaker = goodsMasterService.searchGoodsById(primePartsNoWithH, makerCode, prmSetDtlNo1);
    GoodsMasterMakerInfoDto goodsMasterInfoDto = new GoodsMasterMakerInfoDto();
    List<GoodsMasterMaker> goodsList = new ArrayList<>();
    if (goodsMasterMaker != null) {
      goodsList.add(goodsMasterMaker);
    }
    GoodsMasterMakerModel goodsMasterMakerModel = new GoodsMasterMakerModel();
    goodsMasterMakerModel.setGoodsList(goodsList);
    goodsMasterInfoDto.setGoodsMasterDto(convertGoodsMasterDtoList(goodsMasterMakerModel));
    return goodsMasterInfoDto;
  }

  /**
   * <pre>
   * 商品選択モードの商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param goodsMasterMakerInfoDto 未編集の商品マスタリスト
   * @return 商品マスタ(メーカー)Dto
   */
  @Override
  public GoodsMasterMakerInfoDto searchGoodsMasterInfoList(GoodsMasterMakerInfoDto goodsMasterMakerInfoDto) {
    List<GoodsMasterMakerDto> goodsMasterDtoList = new ArrayList<>();
    for (GoodsMasterMakerDto goodsMasterMakerDto : goodsMasterMakerInfoDto.getGoodsMasterDto()) {

      List<GoodsMasterCommon> goodsMasterCommonList = goodsMasterCommonService
          .searchGoodsMasterCommonList(convertGoodsMaster(goodsMasterMakerDto));
      if (goodsMasterCommonList != null && !goodsMasterCommonList.isEmpty()) {
        GoodsMasterCommon goodsMasterCommon = goodsMasterCommonList.get(0);
        StringBuffer compareFlag = new StringBuffer();
        compareFlag.append("0"); // check
        compareFlag.append(",0"); // no
        compareFlag.append(",0"); // 申請状態
        compareFlag.append(",0"); // 処理区分
        compareFlag.append(",0"); // 優良設定詳細コード１
        Integer goodsMGroupCommon = goodsMasterCommon.getGoodsMGroup();
        dataJudge(goodsMasterMakerDto.getGoodsMGroup(), goodsMGroupCommon, compareFlag); // 商品中分類コード
        dataJudge(goodsMasterMakerDto.getTbsPartsCode(), goodsMasterCommon.getTbsPartsCode(), compareFlag); // BLコード
        compareFlag.append(",0"); // 優良品番
        dataJudge(goodsMasterMakerDto.getPrimePartsKanaNm(), goodsMasterCommon.getPrimePartsKanaNm(), compareFlag); // 品名（半角）
        dataJudge(goodsMasterMakerDto.getPrimePartsName(), goodsMasterCommon.getPrimePartsName(), compareFlag);// 品名（全角）
        dataJudge(goodsMasterMakerDto.getNewPrice(), goodsMasterCommon.getNewPrice(), compareFlag);// 価格
        dataJudge(goodsMasterMakerDto.getOpenPriceDiv(), goodsMasterCommon.getOpenPriceDiv(), compareFlag);// OPENプライス
        dataJudge(goodsMasterMakerDto.getJan(), goodsMasterCommon.getJan(), compareFlag);// Jan
        dataJudge(goodsMasterMakerDto.getPartsLayerCd(), goodsMasterCommon.getPartsLayerCd(), compareFlag);// 層別
        dataJudge(goodsMasterMakerDto.getEquipName(), goodsMasterCommon.getEquipName(), compareFlag);// 装備
        dataJudge(goodsMasterMakerDto.getPrimePartsSpecialNoteB(), goodsMasterCommon.getPrimePartsSpecialNote(),
            compareFlag); // 規格/特記
        dataJudge(goodsMasterMakerDto.getPrimePartsSpecialNoteC(), goodsMasterCommon.getPrimePartsSpecialNoteC(),
            compareFlag); // 規格/特記(一般)
        Short deleteFlgCommon = goodsMasterCommon.getDeleteFlg();
        dataJudge(goodsMasterMakerDto.getDeleteFlg(), deleteFlgCommon, compareFlag); // 削除依頼区分
        String delReasonCommon = goodsMasterCommon.getDeleteReason();
        dataJudge(goodsMasterMakerDto.getDeleteReason(), delReasonCommon, compareFlag); // 削除理由

        dataJudge(goodsMasterMakerDto.getGoodsDetailB(), goodsMasterCommon.getGoodsDetailB(), compareFlag); // 商品詳細
        dataJudge(goodsMasterMakerDto.getGoodsDetailC(), goodsMasterCommon.getGoodsDetailC(), compareFlag); // 商品詳細（一般）
        dataJudge(goodsMasterMakerDto.getGoodsSize1(), goodsMasterCommon.getGoodsSize1(), compareFlag); // 長さ
        dataJudge(goodsMasterMakerDto.getGoodsSize2(), goodsMasterCommon.getGoodsSize2(), compareFlag); // 幅
        dataJudge(goodsMasterMakerDto.getGoodsSize3(), goodsMasterCommon.getGoodsSize3(), compareFlag); // 高さ
        dataJudge(goodsMasterMakerDto.getPackageSize1(), goodsMasterCommon.getPackageSize1(), compareFlag); // 梱包長さ
        dataJudge(goodsMasterMakerDto.getPackageSize2(), goodsMasterCommon.getPackageSize2(), compareFlag); // 梱包幅
        dataJudge(goodsMasterMakerDto.getPackageSize3(), goodsMasterCommon.getPackageSize3(), compareFlag); // 梱包高さ
        dataJudge(goodsMasterMakerDto.getSizeUnit(), goodsMasterCommon.getSizeUnit(), compareFlag); // 梱包単位

        dataJudge(goodsMasterMakerDto.getGoodsWeight(), goodsMasterCommon.getGoodsWeight(), compareFlag); // 商品重量
        dataJudge(goodsMasterMakerDto.getWeightUnit(), goodsMasterCommon.getWeightUnit(), compareFlag); // 重量単位
        dataJudge(goodsMasterMakerDto.getUrl1(), goodsMasterCommon.getUrl1(), compareFlag); // URL1
        dataJudge(goodsMasterMakerDto.getUrl2(), goodsMasterCommon.getUrl2(), compareFlag); // URL2
        dataJudge(goodsMasterMakerDto.getUrl3(), goodsMasterCommon.getUrl3(), compareFlag); // URL3
        dataJudge(goodsMasterMakerDto.getImageNo(), goodsMasterCommon.getImageNo(), compareFlag); // 画像数

        compareFlag.append(",0"); // 作成日時
        compareFlag.append(",0"); // 更新日時
        Timestamp startTimeCommon = goodsMasterCommon.getStartTime();
        dataJudge(goodsMasterMakerDto.getStartDate(), startTimeCommon, compareFlag); // 適用日時
        compareFlag.append(",0"); // チェック区分
        dataJudge(goodsMasterMakerDto.getBlEntryFlg(), goodsMasterCommon.getBlEntryFlg(), compareFlag); // BL登録区分
        dataJudge(goodsMasterMakerDto.getErrorFlg(), goodsMasterCommon.getErrFlg(), compareFlag); // データステータス
        compareFlag.append(",0");// エラー内容
        goodsMasterMakerDto.setCompareFlag(compareFlag.toString());
      }
      goodsMasterDtoList.add(goodsMasterMakerDto);
    }
    GoodsMasterMakerInfoDto goodsMaster = new GoodsMasterMakerInfoDto();
    goodsMaster.setGoodsMasterDto(goodsMasterDtoList);
    return goodsMaster;
  }

  /**
   * メーカー側のデータと共有側のデータは比較する
   * 
   * @param maker Object
   * @param common Object
   * @param flag StringBuffer
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
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param mode 画面mode
   * @return 商品マスタ(メーカー)Dto
   */
  @Override
  public GoodsMasterMakerInfoDto searchGoodsMasterInfoAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd,
                                                          int mode) {
    GoodsMasterMakerInfoDto goodsMasterInfoDto = new GoodsMasterMakerInfoDto();
    goodsMasterInfoDto.setGoodsMasterDto(
        convertGoodsMasterDtoList(goodsMasterService.searchGoodsInfoAll(skipRows, maxRows, isPage, makerCd, mode)));
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
   * 商品ページ内行数設定の取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 商品ページ内行数設定
   */
  @Override
  public int searchGoodsListRows(int makerCd) {
    return companyInfoMasterCommonService.getCompanyInfo(makerCd).getGoodsRows();
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param goodsMasterDtoList 商品マスタ(メーカー)Dto
   * @return 商品マスタ(メーカー)Model
   */
  private GoodsMasterMakerModel convertGoodsMasterModel(List<GoodsMasterMakerDto> goodsMasterDtoList) {
    GoodsMasterMakerModel goodsMasterModel = new GoodsMasterMakerModel();
    if (null != goodsMasterDtoList && goodsMasterDtoList.size() > 0) {
      List<GoodsMasterMaker> goodsMasterList = new ArrayList<GoodsMasterMaker>();
      for (GoodsMasterMakerDto goodsMasterDto : goodsMasterDtoList) {
        GoodsMasterMaker goodsMaster = convertGoodsMaster(goodsMasterDto);
        goodsMasterList.add(goodsMaster);
      }
      goodsMasterModel.setGoodsList(goodsMasterList);
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
  private GoodsMasterMaker convertGoodsMaster(GoodsMasterMakerDto goodsMasterDto) {
    if (goodsMasterDto == null) {
      return null;
    }
    GoodsMasterMaker goodsMaster = new GoodsMasterMaker();
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
  private List<GoodsMasterMakerDto> convertGoodsMasterDtoList(GoodsMasterMakerModel goodsMasterModel) {
    List<GoodsMasterMakerDto> goodsMasterDtoList = new ArrayList<GoodsMasterMakerDto>();
    if (null != goodsMasterModel.getGoodsList() && goodsMasterModel.getGoodsList().size() > 0) {
      for (GoodsMasterMaker goodsMaster : goodsMasterModel.getGoodsList()) {
        GoodsMasterMakerDto goodsMasterDto = convertGoodsMasterDto(goodsMaster);
        goodsMasterDtoList.add(goodsMasterDto);
      }
    }
    return goodsMasterDtoList;
  }

  /**
   * <pre>
   * BLコード名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return BLコード名称マップ
   */
  @Override
  public Map<String, String> getBlCodeNameMap(int makerCd) {
    Map<String, String> blCodeNameMap = new HashMap<String, String>();
    List<BlCodeMasterCommon> blCodeMasterInfo = blCodeMasterCommonService.getBlCodeMasterInfo(makerCd);
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
   * @param makerCd メーカーコード
   * @return BLコード名称マップ
   */
  @Override
  public Map<String, String> getSelectCodeNameMap(int makerCd) {
    Map<String, String> selectNameMap = new HashMap<String, String>();
    List<SelectCodeMasterCommon> searchSelectCode = selectCodeMasterCommonService.getSelectCodeInfo(makerCd);
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
   * * 層別名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 層別
   */
  @Override
  public Map<String, String> getPartsNameMap(int makerCd) {
    Map<String, String> partsNameMap = new LinkedHashMap<String, String>();
    List<PartsMasterCommon> searchPartsCode = partsMasterCommonService.getPartsMasterInfo(makerCd);
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

  /**
   * <pre>
   * * 商品中分類コードマップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 商品中分類コード
   */
  @Override
  public Map<String, String> getClassifyCodeGuideMap(int makerCd) {
    Map<String, String> partsNameMap = new LinkedHashMap<String, String>();
    List<GoodsRateMasterCommon> searchClassifyCodeGuideCode = classifyCodeGuideService
        .getPrimeByCode(GoodsRateMasterCommon.LOGICAL_UNDELETED, makerCd, "", "");
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
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   * @return 商品マスタ(メーカー)Dto
   */
  private GoodsMasterMakerDto convertGoodsMasterDto(GoodsMasterMaker goodsMaster) {
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
   * チェックリスト画面の商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @return 商品マスタ(メーカー)情報
   */
  @Override
  public GoodsMasterMakerInfoDto searchGoodsList(String updAccountId, int partsMakerCd) {
    GoodsMasterMakerInfoDto goodsMasterInfoDto = new GoodsMasterMakerInfoDto();
    goodsMasterInfoDto
        .setGoodsMasterDto(convertGoodsMasterDtoList(goodsMasterService.searchGoodsList(updAccountId, partsMakerCd)));
    return goodsMasterInfoDto;
  }

  /**
   * CheckDivの取得
   * 
   * @param goodsMasterDto 商品マスタ(メーカー)Dto
   * @return CheckDiv
   */
  @Override
  public Boolean getCheckDiv(GoodsMasterMakerDto goodsMasterDto) {
    SelectMaker selectMaker = selectMakerService.searchGoodsById(goodsMasterDto.getPrmSetDtlNo1(),
        goodsMasterDto.getPartsMakerCd(), goodsMasterDto.getPrimePartsNoWithH());
    if (selectMaker == null) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * 商品マスタ(メーカー)のチェック.
   * 
   * @param goodsGridDtoList 商品マスタ
   * @param makerCd メーカーコード
   * @return goodsMasterMakerDtoList
   */
  @Override
  public GoodsMasterMakerInfoDto checkImportList(List<GoodsGridDto> goodsGridDtoList, int makerCd) {
    GoodsMasterMakerInfoDto goodsMasterMakerInfoDto = new GoodsMasterMakerInfoDto();
    int itemErrorCount = 0;

    // 商品マスタ(メーカー)案のチェック
    for (GoodsGridDto goodsGridDto : goodsGridDtoList) {
      if (checkItemWork(goodsGridDto, makerCd, goodsGridDtoList)) {
        itemErrorCount += 1;
      }
      goodsGridDto
          .setMoney(goodsGridDto.getMoney() == null ? null : getPrice(goodsGridDto.getMoney().replace(",", "")));
      goodsGridDto
          .setWidth1(goodsGridDto.getWidth1() == null ? null : getPrice(goodsGridDto.getWidth1().replace(",", "")));
      goodsGridDto
          .setWidth2(goodsGridDto.getWidth2() == null ? null : getPrice(goodsGridDto.getWidth2().replace(",", "")));
      goodsGridDto
          .setWidth3(goodsGridDto.getWidth3() == null ? null : getPrice(goodsGridDto.getWidth3().replace(",", "")));
      goodsGridDto.setPackwidth1(
          goodsGridDto.getPackwidth1() == null ? null : getPrice(goodsGridDto.getPackwidth1().replace(",", "")));
      goodsGridDto.setPackwidth2(
          goodsGridDto.getPackwidth2() == null ? null : getPrice(goodsGridDto.getPackwidth2().replace(",", "")));
      goodsGridDto.setPackwidth3(
          goodsGridDto.getPackwidth3() == null ? null : getPrice(goodsGridDto.getPackwidth3().replace(",", "")));
      goodsGridDto
          .setWeight(goodsGridDto.getWeight() == null ? null : getPrice(goodsGridDto.getWeight().replace(",", "")));
    }
    goodsMasterMakerInfoDto.setGoodsGridDtoList(goodsGridDtoList);
    if (itemErrorCount == 0) {
      goodsMasterMakerInfoDto.setIsErrorExist(Boolean.FALSE);
    } else {
      goodsMasterMakerInfoDto.setIsErrorExist(Boolean.TRUE);
    }

    return goodsMasterMakerInfoDto;
  }

  /**
   * getPrice
   * 
   * @param price price
   * @return String
   */
  @Override
  public String getPrice(String price) {
    if (price.isEmpty()) {
      return price;
    }
    java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
    nf.setGroupingUsed(false);
    price = nf.format(Double.valueOf(price));
    String newPrice = "";
    String[] priceArray = price.split("\\.");
    DecimalFormat myformat = new DecimalFormat();
    myformat.applyPattern("###,###,###");
    newPrice = newPrice + myformat.format(Long.valueOf(priceArray[0])).toString();
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
   * <pre>
   * 中分類コード検索サビースを設定する。
   * </pre>
   * 
   * @param messageId 中分類コード検索サビース
   * @param messageParameters 中分類コード検索サビース
   * @return 中分類コード検索結果DtoList
   */
  private String getMessage(String messageId, String... messageParameters) {
    return messageResolver.getMessage(messageId, messageParameters).getContents();
  }

  /**
   * 商品マスタ(メーカー)案のチェック.
   * 
   * @param goodsGridDto 商品一覧
   * @param makerCd メーカーコード
   * @param goodsGridDtoList 商品マスタ
   * @return 「True」：エラーあり、「False」：エラーなし
   */
  private boolean checkItemWork(GoodsGridDto goodsGridDto, int makerCd, List<GoodsGridDto> goodsGridDtoList) {
    // 削除テータ チェック無し
    if (goodsGridDto.getHiddenArea().intValue() == JudgeEnum.Delete.getValue()) {
      return false;
    }
    // check
    StringBuffer stringBuffer = new StringBuffer();
    // 必須項目チェック セレクトコード名称
    if (goodsGridDto.getSelCode() == null || goodsGridDto.getSelCode().isEmpty()) {
      stringBuffer.append(getMessage(BregMessageCodes.E00001, PRM_SET_DTL_NO_1));
    }
    // 必須項目チェック 優良品番
    if (goodsGridDto.getGoodsNo() == null || goodsGridDto.getGoodsNo().isEmpty()) {
      stringBuffer.append(getMessage(BregMessageCodes.E00001, PRIME_PARTS_NO));
    }
    //// 未入力項目チェック 商品中分類コード
    if (goodsGridDto.getSecCodeName() == null || goodsGridDto.getSecCodeName().isEmpty()) {
      stringBuffer.append(getMessage(BregMessageCodes.E00002, GOODS_M_GROUP));
    }
    // 未入力項目チェック BLコード
    if (goodsGridDto.getBlCodeName() == null || goodsGridDto.getBlCodeName().isEmpty()) {
      stringBuffer.append(getMessage(BregMessageCodes.E00002, TBS_PARTS_CODE));
    }
    // 未入力項目チェック 優良部品名称
    if (goodsGridDto.getNameB() == null || goodsGridDto.getNameB().isEmpty()) {
      stringBuffer.append(getMessage(BregMessageCodes.E00002, PRIME_PARTS_NAME));
    }
    // 未入力項目チェック 優良部品カナ名称
    if (goodsGridDto.getNameS() == null || goodsGridDto.getNameS().isEmpty()) {
      stringBuffer.append(getMessage(BregMessageCodes.E00002, PRIME_PARTS_KANA_NAME));
    }
    // 未入力項目チェック 新価格
    if (goodsGridDto.getMoney() == null || goodsGridDto.getMoney().isEmpty()) {
      stringBuffer.append(getMessage(BregMessageCodes.E00002, NEW_PRICE));
    }
    // 未入力項目チェック オープン価格区分
    if (goodsGridDto.getOpen() == null) {
      stringBuffer.append(getMessage(BregMessageCodes.E00002, OPEN_PRICE));
    }
    // 未入力項目チェック 適用日時
    if (goodsGridDto.getDateSlice() == null || goodsGridDto.getDateSlice().isEmpty()) {
      stringBuffer.append(getMessage(BregMessageCodes.E00002, START_TIME));
    }
    // 未入力項目チェック 削除理由
    if (goodsGridDto.getDelSec() != null && !goodsGridDto.getDelSec().isEmpty()
        && (goodsGridDto.getDelCon() == null || goodsGridDto.getDelCon().isEmpty())) {
      stringBuffer.append(getMessage(BregMessageCodes.E00002, DELETE_REASON));
    }
    checkItemWork1(goodsGridDto, makerCd, stringBuffer, goodsGridDtoList);
    boolean errorExistFlag = false;
    // エラーがありません
    if (stringBuffer == null || stringBuffer.length() == 0) {
      // エラー区分の設定
      goodsGridDto.setErrSec(ErrorFlgEnum.NoError.getAbbreviation());
      // エラー内容クリーン
      goodsGridDto.setErrCon("");
      // エラー有無Flag
      errorExistFlag = false;
    } else {
      // エラー内容の設定
      goodsGridDto.setErrCon(stringBuffer.toString());
      // エラー区分の設定
      goodsGridDto.setErrSec(ErrorFlgEnum.Error.getAbbreviation());
      errorExistFlag = true;
    }
    // 価格ゼロチェック
    if (goodsGridDto.getOpen() != null && goodsGridDto.getOpen().equals(OpenPriceDivEnum.Common.getName())
        && goodsGridDto.getMoney() != null && !goodsGridDto.getMoney().isEmpty()
        && Double.valueOf(goodsGridDto.getMoney().replace(",", "")) == 0) {
      stringBuffer.append(getMessage(BregMessageCodes.W00201));
      goodsGridDto.setErrSec(ErrorFlgEnum.Error.getAbbreviation());
      goodsGridDto.setErrCon(stringBuffer.toString());
    }
    // チェック区分の設定。
    goodsGridDto.setCheckSec(CheckFlgEnum.Checked.getAbbreviation());
    return errorExistFlag;
  }

  /**
   * 商品マスタ(メーカー)案のチェック.
   * 
   * @param goodsGridDto 商品一覧
   * @param makerCd メーカーコード
   * @param goodsGridDtoList 商品マスタ
   * @param stringBuffer stringBuffer
   */
  private void checkItemWork1(GoodsGridDto goodsGridDto, int makerCd, StringBuffer stringBuffer,
                              List<GoodsGridDto> goodsGridDtoList) {
    // オープン価格チェック
    if (goodsGridDto.getOpen() != null && goodsGridDto.getOpen().equals(OpenPriceDivEnum.OpenPrice.getName())
        && (goodsGridDto.getMoney() != null && !goodsGridDto.getMoney().isEmpty()
            && Double.valueOf(goodsGridDto.getMoney().replace(",", "")) != 0)) {
      stringBuffer.append(getMessage(BregMessageCodes.E00303));
    }
    // 優良品番重複チェック 新規登録場合
    if (goodsGridDto.getSelCode() != null && !goodsGridDto.getSelCode().isEmpty() && goodsGridDto.getGoodsNo() != null
        && !goodsGridDto.getGoodsNo().isEmpty()
        && goodsGridDto.getHiddenArea().intValue() == JudgeEnum.Add.getValue()) {
      GoodsMasterMakerId goodsMasterMakerId = new GoodsMasterMakerId(
          Integer.parseInt(goodsGridDto.getSelCode().split("：")[0]), makerCd, goodsGridDto.getGoodsNo());
      if (goodsMasterService.searchGoodsById(goodsMasterMakerId) != null
          || isKeyRepeat(goodsGridDto, goodsGridDtoList)) {
        stringBuffer.append(getMessage(BregMessageCodes.E00301));
      }
    }
    // 文字桁数チェック 優良部品名称
    if (goodsGridDto.getNameB() != null && goodsGridDto.getNameB().length() > 60) {
      stringBuffer.append(getMessage(BregMessageCodes.E00003, PRIME_PARTS_NAME, "60"));
    }
    // 文字桁数チェック 優良品番
    if (goodsGridDto.getGoodsNo() != null && goodsGridDto.getGoodsNo().length() > 24) {
      stringBuffer.append(getMessage(BregMessageCodes.E00003, PRIME_PARTS_NO, "24"));
    }
    // 文字桁数チェック 優良部品カナ名称
    if (goodsGridDto.getNameS() != null && goodsGridDto.getNameS().length() > 60) {
      stringBuffer.append(getMessage(BregMessageCodes.E00003, PRIME_PARTS_KANA_NAME, "60"));
    }
  }

  /**
   * 優良品番重複チェック
   * 
   * @param goodsGridDto 商品マスタ
   * @param goodsGridDtoList 商品マスタ
   * @return boolean
   */
  private boolean isKeyRepeat(GoodsGridDto goodsGridDto, List<GoodsGridDto> goodsGridDtoList) {
    Boolean isKeyRepeat = false;
    int index = 0;
    for (GoodsGridDto goodsGridDtoOld : goodsGridDtoList) {
      if (goodsGridDtoOld.getHiddenArea().intValue() == JudgeEnum.Add.getValue() && goodsGridDto.getSelCode() != null
          && !goodsGridDto.getSelCode().isEmpty() && goodsGridDto.getGoodsNo() != null
          && !goodsGridDto.getGoodsNo().isEmpty() && goodsGridDtoOld.getSelCode() != null
          && !goodsGridDtoOld.getSelCode().isEmpty() && goodsGridDtoOld.getGoodsNo() != null
          && !goodsGridDtoOld.getGoodsNo().isEmpty() && goodsGridDtoOld.getSelCode().equals(goodsGridDto.getSelCode())
          && goodsGridDtoOld.getGoodsNo().equals(goodsGridDto.getGoodsNo())) {
        index = index + 1;
      }
    }
    if (index > 1) {
      isKeyRepeat = true;
    }
    return isKeyRepeat;
  }

  /**
   * SelectMakerの処理
   * 
   * @param goodsGridDto 商品マスタ
   * @param makerCd メーカーコード
   */
  @Override
  public void manegeSelectMaker(GoodsGridDto goodsGridDto, int makerCd) {
    int gPrmSetDtlNo1 = goodsGridDto.getSelCode() == null ? 9999
        : Integer.parseInt(goodsGridDto.getSelCode().split("：")[0]);
    if (goodsGridDto.getCheck()) {
      SelectMaker selectMaker = convertSelectMaker(goodsGridDto, makerCd);
      selectMakerService.insertGoodsById(selectMaker);
    } else {
      selectMakerService.deleteGoodsById(gPrmSetDtlNo1, makerCd, goodsGridDto.getGoodsNo());
    }
  }

  /**
   * SelectMakerの取得
   * 
   * @param goodsGridDto goodsGridDto
   * @param makerCd makerCd
   * @return SelectMaker
   */
  private SelectMaker convertSelectMaker(GoodsGridDto goodsGridDto, int makerCd) {
    SelectMaker selectMaker = new SelectMaker();
    int initValue = 0;
    String empty = "";
    int gPrmSetDtlNo1 = goodsGridDto.getSelCode() == null ? 9999
        : Integer.parseInt(goodsGridDto.getSelCode().split("：")[0]);
    selectMaker.setGPartsMakerCd(makerCd);
    selectMaker.setGPrimePartsNoWithH(goodsGridDto.getGoodsNo());
    selectMaker.setGPrmSetDtlNo1(gPrmSetDtlNo1);
    selectMaker.setJJoinDispOrder(initValue);
    selectMaker.setJJoinSourceMakerCode(initValue);
    selectMaker.setJJoinSourPartsNoWithH(empty);
    selectMaker.setJPartsMakerCd(initValue);
    selectMaker.setJPrmSetDtlNo1(initValue);
    selectMaker.setJPrmSetDtlNo2(initValue);
    selectMaker.setJTbsPartsCode(initValue);
    selectMaker.setSPartsMakerCd(initValue);
    selectMaker.setSPrmSetDtlNo1(initValue);
    selectMaker.setSSetDispOrder(initValue);
    selectMaker.setSSetMainPartsNo(empty);
    selectMaker.setSelectKbn(0);
    return selectMaker;
  }

  /**
   * <pre>
   * チェック選択に情報取得.
   * </pre>
   * 
   * @param partsMakerCd メーカーコード
   * @return チェック選択情報
   */
  @Override
  public int searchSelectBySelectKbn(int partsMakerCd) {
    return selectMakerService.searchBySelectKbn(0, partsMakerCd, 0).size();
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param partsMakerCd メーカーコード
   * @return 商品マスタ(メーカー)Dto
   */
  @Override
  public GoodsMasterMakerInfoDto searchGoodsMasterInfoBySelect(int partsMakerCd) {
    GoodsMasterMakerInfoDto goodsMasterMakerInfoDto = new GoodsMasterMakerInfoDto();
    List<SelectMaker> selectMakerList = selectMakerService.searchBySelectKbn(0, partsMakerCd, 0);
    List<GoodsMasterMakerDto> goodsMasterDtoList = new ArrayList<>();
    for (SelectMaker selectMaker : selectMakerList) {
      GoodsMasterMakerId goodsMasterId = new GoodsMasterMakerId();
      goodsMasterId.setPartsMakerCd(selectMaker.getGPartsMakerCd());
      goodsMasterId.setPrimePartsNoWithH(selectMaker.getGPrimePartsNoWithH());
      goodsMasterId.setPrmSetDtlNo1(selectMaker.getGPrmSetDtlNo1());
      GoodsMasterMaker goodsMasterMaker = goodsMasterService.searchGoodsById(goodsMasterId);
      if (goodsMasterMaker != null) {
        goodsMasterDtoList.add(convertGoodsMasterDto(goodsMasterMaker));
      }
    }
    goodsMasterMakerInfoDto.setGoodsMasterDto(goodsMasterDtoList);
    return goodsMasterMakerInfoDto;
  }

  /**
   * 画面初期ロード時、機能制御の判断
   * 
   * @return 「True」：機能制御なし、「False」：機能制御あり
   */
  public Boolean isControl() {
    Short importKbn = 0;
    Short applyCondition = 0;
    LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
    String updAccountId = loginPrincipal.getLoginId();
    int partsMakerCd = loginPrincipal.getMakerCode();
    int logicalDeleteCode = 0;
    List<GoodsMasterMaker> goodList = goodsMasterService.searchGoodsInfoList(importKbn, applyCondition, updAccountId,
        partsMakerCd, logicalDeleteCode);
    List<SetMasterMaker> setList = setMasterService.searchSetInfoList(importKbn, applyCondition, updAccountId,
        partsMakerCd, logicalDeleteCode);
    List<JoinMasterMaker> joinList = joinMasterService.searchJoinInfoList(importKbn, applyCondition, updAccountId,
        partsMakerCd, logicalDeleteCode);
    return (goodList == null || goodList.isEmpty()) && (setList == null || setList.isEmpty())
        && (joinList == null || joinList.isEmpty());

  }

  /**
   * メッセージを取得します.
   * 
   * @param messageMap messageMap
   */
  public void getMessage(HashMap<String, String> messageMap) {
    messageMap.put(BregMessageCodes.Q00001, messageService.messageInfo(BregMessageCodes.Q00001) == null ? ""
        : messageService.messageInfo(BregMessageCodes.Q00001));
    messageMap.put(BregMessageCodes.Q00002, messageService.messageInfo(BregMessageCodes.Q00002) == null ? ""
        : messageService.messageInfo(BregMessageCodes.Q00002));
    messageMap.put(BregMessageCodes.E00008, messageService.getMessage("商品", "", BregMessageCodes.E00008));
    messageMap.put(BregMessageCodes.E00009, messageService.messageInfo(BregMessageCodes.E00009) == null ? ""
        : messageService.messageInfo(BregMessageCodes.E00009));
    messageMap.put(BregMessageCodes.E00010, messageService.messageInfo(BregMessageCodes.E00010) == null ? ""
        : messageService.messageInfo(BregMessageCodes.E00010));
    messageMap.put(BregMessageCodes.E00013, messageService.messageInfo(BregMessageCodes.E00013) == null ? ""
        : messageService.messageInfo(BregMessageCodes.E00013));
    messageMap.put(BregMessageCodes.E00014, messageService.messageInfo(BregMessageCodes.E00014) == null ? ""
        : messageService.messageInfo(BregMessageCodes.E00014));
    messageMap.put(BregMessageCodes.E00015, messageService.messageInfo(BregMessageCodes.E00015) == null ? ""
        : messageService.messageInfo(BregMessageCodes.E00015));
    messageMap.put(BregMessageCodes.E00016, messageService.messageInfo(BregMessageCodes.E00016) == null ? ""
        : messageService.messageInfo(BregMessageCodes.E00016));
    messageMap.put(BregMessageCodes.E00011, messageService.getMessage("申請中", "商品", BregMessageCodes.E00011));
    messageMap.put(BregMessageCodes.E00012.concat("1"),
        messageService.getMessage("申請中", "商品", BregMessageCodes.E00012));
    messageMap.put(BregMessageCodes.E00012.concat("2"),
        messageService.getMessage("インポート", "商品", BregMessageCodes.E00012));
    messageMap.put(BregMessageCodes.E00012.concat("3"),
        messageService.getMessage("承認済", "商品", BregMessageCodes.E00012));
    messageMap.put(BregMessageCodes.I00001, messageService.messageInfo(BregMessageCodes.I00001) == null ? ""
        : messageService.messageInfo(BregMessageCodes.I00001));

  }
  // --------------- add by liangsd ------------------>>>

  /**
   * sava real-time information that user edit in the grid
   * 
   * @param goodsMasterMakerDtoList list
   * @param mode mode
   */
  public void saveRealTime(List<GoodsMasterMakerDto> goodsMasterMakerDtoList, int mode) {
    goodsMasterService.deleteKGoodsInfo(goodsMasterMakerDtoList.get(0).getUpdAccountId());

    if (goodsMasterMakerDtoList.size() > 0 && mode == 1) {
      List<GoodsMasterMakerRealTime> result = goodsMasterService
          .searchAllbyLoginId(goodsMasterMakerDtoList.get(0).getUpdAccountId());

      if (result.size() == 0) {
        int lineNo = 1;
        List<GoodsMasterMakerRealTime> goodsMasterList = new ArrayList<>();
        for (GoodsMasterMakerDto goodsMasterMakerDto : goodsMasterMakerDtoList) {
          GoodsMasterMakerRealTime goodsMasterMaker = convertKGoodsMaster(goodsMasterMakerDto);
          goodsMasterMaker.setLineNo(lineNo);
          goodsMasterList.add(goodsMasterMaker);
          lineNo++;
        }
        goodsMasterService.insertKGoodsInfoList(goodsMasterList);
      }
    }
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param loginId loginId
   * @return 商品マスタ(メーカー)Dto
   */
  public List<GoodsMasterMakerDto> searchGoodsByloginId(String loginId) {
    List<GoodsMasterMakerDto> resultGoodsMasterMakerDto = new ArrayList<>();
    List<GoodsMasterMakerRealTime> resultGoodsMasterMakerRealTime = goodsMasterService.searchAllbyLoginId(loginId);

    for (GoodsMasterMakerRealTime goodsMasterMakerRealTime : resultGoodsMasterMakerRealTime) {
      GoodsMasterMakerDto goodsMasterMakerDto = convertKGoodsMasterDto(goodsMasterMakerRealTime);

      resultGoodsMasterMakerDto.add(goodsMasterMakerDto);
    }

    return resultGoodsMasterMakerDto;
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param goodsMasterMakerDto 商品マスタ(メーカー)Dto
   * @return 商品マスタ(メーカー)Model
   */
  private GoodsMasterMakerRealTime convertKGoodsMaster(GoodsMasterMakerDto goodsMasterMakerDto) {
    if (goodsMasterMakerDto == null) {
      return null;
    }
    GoodsMasterMakerRealTime goodsMaster = new GoodsMasterMakerRealTime();
    // 部品メーカーコード
    goodsMaster.setPartsMakerCd(goodsMasterMakerDto.getPartsMakerCd());
    // 優良品番(－付き品番)
    goodsMaster.setPrimePartsNoWithH(goodsMasterMakerDto.getPrimePartsNoWithH());
    // 優良設定詳細コード１
    goodsMaster.setPrmSetDtlNo1(goodsMasterMakerDto.getPrmSetDtlNo1());
    // 申請状態
    goodsMaster.setApplyCondition(goodsMasterMakerDto.getApplyCondition());
    // BL登録区分
    goodsMaster.setBlEntryFlg(goodsMasterMakerDto.getBlEntryFlg());
    // チェック区分
    goodsMaster.setCheckFlg(goodsMasterMakerDto.getCheckFlg());
    // 削除依頼区分
    goodsMaster.setDeleteFlg(goodsMasterMakerDto.getDeleteFlg());
    // 削除理由
    goodsMaster.setDeleteReason(goodsMasterMakerDto.getDeleteReason());
    // 装備名称
    goodsMaster.setEquipName(goodsMasterMakerDto.getEquipName());
    // エラー内容
    goodsMaster.setErrorDetail(goodsMasterMakerDto.getErrorDetail());
    // データステータス
    goodsMaster.setErrorFlg(goodsMasterMakerDto.getErrorFlg());
    // 商品詳細(B向け)
    goodsMaster.setGoodsDetailB(goodsMasterMakerDto.getGoodsDetailB());
    // 商品詳細(C向け)
    goodsMaster.setGoodsDetailC(goodsMasterMakerDto.getGoodsDetailC());
    // 商品中分類コード
    goodsMaster.setGoodsMGroup(goodsMasterMakerDto.getGoodsMGroup());
    // 商品サイズ(長さ）
    goodsMaster.setGoodsSize1(goodsMasterMakerDto.getGoodsSize1());
    // 商品サイズ(幅）
    goodsMaster.setGoodsSize2(goodsMasterMakerDto.getGoodsSize2());
    // 商品サイズ(高さ）
    goodsMaster.setGoodsSize3(goodsMasterMakerDto.getGoodsSize3());
    // 商品重量
    goodsMaster.setGoodsWeight(goodsMasterMakerDto.getGoodsWeight());
    // 画像数
    goodsMaster.setImageNo(goodsMasterMakerDto.getImageNo());
    // インポート区分
    goodsMaster.setImportKbn(goodsMasterMakerDto.getImportKbn());
    // JAN
    goodsMaster.setJan(goodsMasterMakerDto.getJan());
    // 処理区分
    goodsMaster.setManageKbn(goodsMasterMakerDto.getManageKbn());
    // 新価格
    goodsMaster.setNewPrice(goodsMasterMakerDto.getNewPrice());
    // オープン価格区分
    goodsMaster.setOpenPriceDiv(goodsMasterMakerDto.getOpenPriceDiv());
    // 梱包サイズ(長さ）
    goodsMaster.setPackageSize1(goodsMasterMakerDto.getPackageSize1());
    // 梱包サイズ(幅）
    goodsMaster.setPackageSize2(goodsMasterMakerDto.getPackageSize2());
    // 梱包サイズ(高さ）
    goodsMaster.setPackageSize3(goodsMasterMakerDto.getPackageSize3());
    // 層別コード
    goodsMaster.setPartsLayerCd(goodsMasterMakerDto.getPartsLayerCd());
    // 優良部品カナ名称
    goodsMaster.setPrimePartsKanaNm(goodsMasterMakerDto.getPrimePartsKanaNm());
    // 優良部品名称
    goodsMaster.setPrimePartsName(goodsMasterMakerDto.getPrimePartsName());
    // 優良部品規格・特記事項(C向け)
    goodsMaster.setPrimePartsSpecialNoteC(goodsMasterMakerDto.getPrimePartsSpecialNoteC());
    // 優良部品規格・特記事項(B向け)
    goodsMaster.setPrimePartsSpecialNoteB(goodsMasterMakerDto.getPrimePartsSpecialNoteB());
    // サイズ単位
    goodsMaster.setSizeUnit(goodsMasterMakerDto.getSizeUnit());
    // 適用日付
    goodsMaster.setStartTime(goodsMasterMakerDto.getStartDate());
    // URL1
    goodsMaster.setUrl1(goodsMasterMakerDto.getUrl1());
    // URL2
    goodsMaster.setUrl2(goodsMasterMakerDto.getUrl2());
    // URL3
    goodsMaster.setUrl3(goodsMasterMakerDto.getUrl3());
    // 重量単位
    goodsMaster.setWeightUnit(goodsMasterMakerDto.getWeightUnit());
    // BLコード
    goodsMaster.setTbsPartsCode(goodsMasterMakerDto.getTbsPartsCode());

    goodsMaster.setLoginId(goodsMasterMakerDto.getUpdAccountId());
    return goodsMaster;
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param goodsMaster 商品マスタ(メーカー)
   * @return 商品マスタ(メーカー)Dto
   */
  private GoodsMasterMakerDto convertKGoodsMasterDto(GoodsMasterMakerRealTime goodsMaster) {
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
  // --------------- add by liangsd ------------------<<<
}
