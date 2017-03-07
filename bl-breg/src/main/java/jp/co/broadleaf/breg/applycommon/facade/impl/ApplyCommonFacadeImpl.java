//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : xuck
// 作 成 日       2017/02/07   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applycommon.facade.impl;

import jp.co.broadleaf.breg.applycommon.facade.ApplyCommonFacade;
import jp.co.broadleaf.breg.applycommon.service.ApplyCommonService;
import jp.co.broadleaf.breg.applyheadmastercommon.facade.dto.ApplyHeadMasterCommonDto;
import jp.co.broadleaf.breg.applyheadmastercommon.facade.dto.ApplyHeadMasterCommonInfoDto;
import jp.co.broadleaf.breg.applyheadmastercommon.service.ApplyHeadMasterCommonService;
import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommon;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMakerId;
import jp.co.broadleaf.breg.common.entity.GoodsRateMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMakerId;
import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterCommon;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.MailDivEnum;
import jp.co.broadleaf.breg.common.enums.OpenPriceDivEnum;
import jp.co.broadleaf.breg.common.service.BizEmailService;
import jp.co.broadleaf.breg.goodscommon.service.GoodsMasterCommonService;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterCommonDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerInfoDto;
import jp.co.broadleaf.breg.goodslist.service.GoodsMasterMakerService;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterCommonDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterInfoDto;
import jp.co.broadleaf.breg.joinlist.service.JoinMasterService;
import jp.co.broadleaf.breg.joinlistcommon.service.JoinMasterCommonService;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.breg.selectmaker.service.SelectMakerService;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterCommonDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterInfoDto;
import jp.co.broadleaf.breg.setlist.service.SetMasterService;
import jp.co.broadleaf.breg.setlist.utils.DataConvert;
import jp.co.broadleaf.breg.setlistcommon.service.SetMasterCommonService;
import jp.co.broadleaf.common.util.BroadleafStringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

/**
 * 優良部品簡易登録システムBREG
 */
public class ApplyCommonFacadeImpl implements ApplyCommonFacade {

  /** MessageMap **/
  private static final HashMap<String, String> MESSAGE_MAP = new HashMap<>();
  /** 文字列:コンマ **/
  private static final String COMMA = "、";
  /** セレクトコード **/
  private static final String SELECT_CODE = "セレクトコード：";
  /** メーカーコード **/
  private static final String MAKER_CODE = "メーカーコード：";
  /** 優良品番 **/
  private static final String PRIME_PARTS_NO_WITH_H = "優良品番：";
  /** 商品データは **/
  private static final String GOODS_DATA = "商品データは";
  /** セット親品番 **/
  private static final String SET_MAIN_PARTS_NO = "セット親品番：";
  /** セット表示順位 **/
  private static final String SET_DISP_ORDER = "セット表示順位：";
  /** セットデータは **/
  private static final String SET_DATA = "セットデータは";
  /** BLコード **/
  private static final String TBS_PARTS_CODE = "BLコード：";
  /** カーコード **/
  private static final String JOIN_SOURCE_MAKER_CODE = "カーコード：";
  /** 種別コード **/
  private static final String TYPE_CODE = "種別コード：";
  /** 結合元品番 **/
  private static final String JOIN_SOUR_PARTS_NO_WITH_H = "結合元品番：";
  /** 結合表示順位 **/
  private static final String JOIN_DISP_ORDER = "結合表示順位：";
  /** 結合データは **/
  private static final String JOIN_DATA = "結合データは";
  /** ブロードリーフ **/
  private static final String BROADLEAF = "ブロードリーフ　担当者";
  /** 文字列:半角-. */
  private static final String HALF_HYPHEN = "-";
  /** 文字列：空白文字. */
  private static final String EMPTY = "";
  /** 文字列：半角スペース. */
  private static final String HALF_SPACE = " ";

  private static final List<String> EMPTY_LIST = new ArrayList<>();
  /** 申請(common)サービス */
  private ApplyCommonService applyCommonService;
  /** 申請(common)サービス */
  private GoodsMasterCommonService goodsMasterCommonService;
  /** 結合(common)サービス */
  private JoinMasterCommonService joinMasterCommonService;
  /** セット(common)サービス */
  private SetMasterCommonService setMasterCommonService;
  /** 申請ヘッダマスサービス */
  private ApplyHeadMasterCommonService applyHeadMasterCommonService;
  /** Messageサービス */
  private MessageService messageService;
  /** 業務メール送信 */
  private BizEmailService bizEmailService;

  private GoodsMasterMakerService goodsMasterMakerService;

  private SetMasterService setMasterMakerService;

  private JoinMasterService joinMasterMakerService;
  /** チェック選択検索サビース **/
  private SelectMakerService selectMakerService;

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param importKbn BL登録区分(メーカー)
   * @param applyCondition BL登録区分(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @param isFromImport boolean
   * @param isReApplication boolean
   * @return 商品マスタ(メーカー)Dto
   */
  @Override
  public GoodsMasterMakerInfoDto searchItemMasterInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                                          Integer partsMakerCd, Integer logicalDeleteCode,
                                                          boolean isFromImport, boolean isReApplication) {
    GoodsMasterMakerInfoDto itemMasterInfoDto = new GoodsMasterMakerInfoDto();
    itemMasterInfoDto.setGoodsMasterDto(convertGoodsMasterDtoList(applyCommonService.searchGoodsInfoList(importKbn,
        applyCondition, updAccountId, partsMakerCd, logicalDeleteCode, isFromImport, isReApplication)));
    return itemMasterInfoDto;
  }

  /**
   * <pre>
   * セットマスタ(メーカー)の取得.
   * </pre>
   * 
   * @param importKbn インポート区分(メーカー)
   * @param applyCondition 申請状態(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @param isFromImport boolean
   * @param isReApplication boolean
   * @return セットマスタ(メーカー)Dto
   */
  @Override
  public SetMasterInfoDto searchSetMasterInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                                  Integer partsMakerCd, Integer logicalDeleteCode, boolean isFromImport,
                                                  boolean isReApplication) {
    SetMasterInfoDto setMasterInfoDto = new SetMasterInfoDto();
    setMasterInfoDto.setSetMasterDtoList(convertToSetMasterDtoList(applyCommonService.searchSetInfoList(importKbn,
        applyCondition, updAccountId, partsMakerCd, logicalDeleteCode, isFromImport, isReApplication)));
    return setMasterInfoDto;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param importKbn インポート区分(メーカー)
   * @param applyCondition 申請状態(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @param isFromImport boolean
   * @param isReApplication boolean
   * @return 結合マスタ(メーカー)Dto
   */
  @Override
  public JoinMasterInfoDto searchJoinMasterInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                                    Integer partsMakerCd, Integer logicalDeleteCode,
                                                    boolean isFromImport, boolean isReApplication) {
    JoinMasterInfoDto joinMasterInfoDto = new JoinMasterInfoDto();
    joinMasterInfoDto.setJoinMasterDto(convertJoinMasterInfoDto(applyCommonService.searchJoinInfoList(importKbn,
        applyCondition, updAccountId, partsMakerCd, logicalDeleteCode, isFromImport, isReApplication)));
    return joinMasterInfoDto;
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param goodsMasterList 商品マスタ(メーカー)Model
   * @return 商品マスタ(メーカー)Dto
   */
  private List<GoodsMasterMakerDto> convertGoodsMasterDtoList(List<GoodsMasterMaker> goodsMasterList) {
    List<GoodsMasterMakerDto> goodsMasterDtoList = new ArrayList<GoodsMasterMakerDto>();
    if (null != goodsMasterList && goodsMasterList.size() > 0) {
      for (GoodsMasterMaker goodsMaster : goodsMasterList) {
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
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param setMasterMakerList セットマスタ(メーカー)Model
   * @return セットマスタ(メーカー)Dto
   */
  private List<SetMasterDto> convertToSetMasterDtoList(List<SetMasterMaker> setMasterMakerList) {
    List<SetMasterDto> setMasterDtoList = new ArrayList<SetMasterDto>();
    if (null != setMasterMakerList && setMasterMakerList.size() > 0) {
      for (SetMasterMaker setMasterMaker : setMasterMakerList) {
        SetMasterDto setMasterDto = new SetMasterDto();
        DataConvert.setMasterMakerconvertToDtoOrModel(setMasterMaker, setMasterDto);
        setMasterDtoList.add(setMasterDto);
      }
    }
    return setMasterDtoList;
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param joinMasterList 結合マスタ(メーカー)Model
   * @return 結合マスタ(メーカー)Dto
   */
  private List<JoinMasterDto> convertJoinMasterInfoDto(List<JoinMasterMaker> joinMasterList) {
    List<JoinMasterDto> joinMasterDtoList = new ArrayList<JoinMasterDto>();
    if (null != joinMasterList && joinMasterList.size() > 0) {
      for (JoinMasterMaker joinMaster : joinMasterList) {
        JoinMasterDto joinMasterDto = convertJoinMasterDto(joinMaster);
        joinMasterDtoList.add(joinMasterDto);
      }
    }
    return joinMasterDtoList;
  }

  private JoinMasterDto convertJoinMasterDto(JoinMasterMaker joinMaster) {
    JoinMasterDto joinMasterDto = new JoinMasterDto();
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
    joinMasterDto.setPrimePartsSpecialNoteC(joinMaster.getPrimePartsSpecialNoteC());
    // 適用日付
    joinMasterDto.setStartTime(joinMaster.getStartTime());
    // 削除時申請理由
    joinMasterDto.setDeleteReason(joinMaster.getDeleteReason());
    // チェック区分
    joinMasterDto.setCheckFlg(joinMaster.getCheckFlg());
    // データステータス
    joinMasterDto.setErrorFlg(joinMaster.getErrorFlg());
    // BL登録区分
    joinMasterDto.setBlEntryFlg(joinMaster.getBlEntryFlg());
    // インポート区分
    joinMasterDto.setImportKbn(joinMaster.getImportKbn());
    // 処理区分
    joinMasterDto.setManageKbn(joinMaster.getManageKbn());
    // エラー内容
    joinMasterDto.setErrorDetail(joinMaster.getErrorDetail());
    // 削除依頼区分
    joinMasterDto.setDeleteFlg(joinMaster.getDeleteFlg());
    // 申請状態
    joinMasterDto.setApplyCondition(joinMaster.getApplyCondition());
    return joinMasterDto;
  }

  /**
   * <pre>
   * 商品マスタ(common)情報更新.
   * </pre>
   * 
   * @param goodsMasterDto 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  @Override
  public void updateGoodsMaster(GoodsMasterCommonDto goodsMasterDto) {
    goodsMasterCommonService.updateGoodsInfo(convertGoodsMasterCommon(goodsMasterDto));
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param goodsMasterDto 商品マスタ(common)Dto
   * @return 商品マスタ(common)Model
   */
  private GoodsMasterCommon convertGoodsMasterCommon(GoodsMasterCommonDto goodsMasterDto) {
    if (goodsMasterDto == null) {
      return null;
    }
    GoodsMasterCommon goodsMaster = new GoodsMasterCommon();
    // 申請No
    goodsMaster.setApplyNo(goodsMasterDto.getApplyNo());
    // BL登録区分
    goodsMaster.setBlEntryFlg(goodsMasterDto.getBlEntryFlg());
    // 処理区分
    goodsMaster.setDealFlg(goodsMasterDto.getDealFlg());
    // 削除依頼区分
    goodsMaster.setDeleteFlg(goodsMasterDto.getDeleteFig());
    // 削除理由
    goodsMaster.setDeleteReason(goodsMasterDto.getDeleteReason());
    // 装備名称
    goodsMaster.setEquipName(goodsMasterDto.getEquipName());
    // データステータス
    goodsMaster.setErrFlg(goodsMasterDto.getErrFlg());
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
    // JAN
    goodsMaster.setJan(goodsMasterDto.getJan());
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
    // 部品メーカーコード
    goodsMaster.setPartsMakerCd(goodsMasterDto.getPartsMakerCd());
    // 優良部品カナ名称
    goodsMaster.setPrimePartsKanaNm(goodsMasterDto.getPrimePartsKanaNm());
    // 優良部品名称
    goodsMaster.setPrimePartsName(goodsMasterDto.getPrimePartsName());
    // 優良品番(－付き品番)
    goodsMaster.setPrimePartsNoWithH(goodsMasterDto.getPrimePartsNoWithH());
    // 優良部品規格・特記事項
    goodsMaster.setPrimePartsSpecialNote(goodsMasterDto.getPrimePartsSpecialNoteB());
    // 優良部品規格・特記事項(C向け)
    goodsMaster.setPrimePartsSpecialNoteC(goodsMasterDto.getPrimePartsSpecialNoteC());
    // 優良設定詳細コード１
    goodsMaster.setPrmSetDtlNo1(goodsMasterDto.getPrmSetDtlNo1());
    // サイズ単位
    goodsMaster.setSizeUnit(goodsMasterDto.getSizeUnit());
    // 適用日時
    goodsMaster.setStartTime(goodsMasterDto.getStartTime());
    // BLコード
    goodsMaster.setTbsPartsCode(goodsMasterDto.getTbsPartsCode());
    // URL1
    goodsMaster.setUrl1(goodsMasterDto.getUrl1());
    // URL2
    goodsMaster.setUrl2(goodsMasterDto.getUrl2());
    // URL3
    goodsMaster.setUrl3(goodsMasterDto.getUrl3());
    // 重量単位
    goodsMaster.setWeightUnit(goodsMasterDto.getWeightUnit());
    return goodsMaster;
  }

  /**
   * <pre>
   * セットマスタ(common)情報更新.
   * </pre>
   * 
   * @param setMasterDto 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  public void updateSetMaster(SetMasterCommonDto setMasterDto) {
    setMasterCommonService.updateSetMasterCommon(convertSetMasterCommon(setMasterDto));
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param setMasterDto セットマスタ(common)Dto
   * @return セットマスタ(common)Model
   */
  private SetMasterCommon convertSetMasterCommon(SetMasterCommonDto setMasterDto) {
    if (setMasterDto == null) {
      return null;
    }
    SetMasterCommon setMaster = new SetMasterCommon();
    // 申請No
    setMaster.setApplyNo(setMasterDto.getApplyNo());
    // BL登録区分
    setMaster.setBlEntryFlg(setMasterDto.getBlEntryFlg());
    // 処理区分
    setMaster.setDealFlg(setMasterDto.getDealFlg());
    // 削除依頼区分
    setMaster.setDeleteFlg(setMasterDto.getDeleteFig());
    // 削除理由
    setMaster.setDeleteReason(setMasterDto.getDeleteReason());
    // データステータス
    setMaster.setErrFlg(setMasterDto.getErrFlg());
    // 商品中分類コード
    setMaster.setGoodsMGroup(setMasterDto.getGoodsMGroup());
    // 部品メーカーコード
    setMaster.setPartsMakerCd(setMasterDto.getPartsMakerCd());
    // 優良部品規格・特記事項(C向け)
    setMaster.setPrimePartsSpecialNoteC(setMasterDto.getPrimePartsSpecialNoteC());
    // 優良設定詳細コード１
    setMaster.setPrmSetDtlNo1(setMasterDto.getPrmSetDtlNo1());
    // セット規格・特記事項
    setMaster.setSetApecialNote(setMasterDto.getSetSpecialNote());
    // セット表示順位
    setMaster.setSetDispOrder(setMasterDto.getSetDispOrder());
    // 品名
    setMaster.setSetKanaName(setMasterDto.getSetKanaName());
    // セット親品番
    setMaster.setSetMainPartsNo(setMasterDto.getSetMainPartsNo());
    // セット名称
    setMaster.setSetName(setMasterDto.getSetName());
    // セットQTY
    setMaster.setSetQty(setMasterDto.getSetQty());
    // セット子品番
    setMaster.setSetSubPartsNo(setMasterDto.getSetSubPartsNo());
    // 適用日時
    setMaster.setStartTime(setMasterDto.getStartTime());
    // BLコード
    setMaster.setTbsPartsCode(setMasterDto.getTbsPartsCode());
    return setMaster;
  }

  /**
   * <pre>
   * 結合マスタ(common)情報更新.
   * </pre>
   * 
   * @param joinMasterDto 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  public void updateJoinMaster(JoinMasterCommonDto joinMasterDto) {
    joinMasterCommonService.updateJoinMaster(convertJoinMasterCommon(joinMasterDto));
  }

  /**
   * test
   * 
   * @param joinMasterDto joinMasterDto
   * @return JoinMasterCommon
   */
  private JoinMasterCommon convertJoinMasterCommon(JoinMasterCommonDto joinMasterDto) {
    if (joinMasterDto == null) {
      return null;
    }
    JoinMasterCommon joinMaster = new JoinMasterCommon();
    // 申請No
    joinMaster.setApplyNo(joinMasterDto.getApplyNo());
    // BL登録区分
    joinMaster.setBlEntryFlg(joinMasterDto.getBlEntryFlg());
    // 処理区分
    joinMaster.setDealFlg(joinMasterDto.getDealFlg());
    // 削除依頼区分
    joinMaster.setDeleteFlg(joinMasterDto.getDeleteFig());
    // 削除時申請理由
    joinMaster.setDeleteReason(joinMasterDto.getDeleteReason());
    // データステータス
    joinMaster.setErrFlg(joinMasterDto.getErrFlg());
    // 商品中分類コード
    joinMaster.setGoodsMGroup(joinMasterDto.getGoodsMGroup());
    // 結合先品番(－付き品番)
    joinMaster.setJoinDestPartsNo(joinMasterDto.getJoinDestPartsNo());
    // 結合表示順位
    joinMaster.setJoinDispOrder(joinMasterDto.getJoinDispOrder());
    // 結合QTY
    joinMaster.setJoinQty(joinMasterDto.getJoinQty());
    // 結合元メーカーコード
    joinMaster.setJoinSourceMakerCode(joinMasterDto.getJoinSourceMakerCode());
    // 結合元品番(－付き品番)
    joinMaster.setJoinSourPartsNoWithH(joinMasterDto.getJoinSourPartsNoWithH());
    // 結合規格・特記事項
    joinMaster.setJoinSpecialNote(joinMasterDto.getJoinSpecialNote());
    // 部品メーカーコード
    joinMaster.setPartsMakerCd(joinMasterDto.getPartsMakerCd());
    // 優良部品規格・特記事項(C向け)
    joinMaster.setPrimePartsSpecialNoteC(joinMasterDto.getPrimePartsSpecialNotec());
    // 優良設定詳細コード１
    joinMaster.setPrmSetDtlNo1(joinMasterDto.getPrmSetDtlNo1());
    // 優良設定詳細コード２
    joinMaster.setPrmSetDtlNo2(joinMasterDto.getPrmSetDtlNo2());
    // 適用日時
    joinMaster.setStartTime(joinMasterDto.getStartTime());
    // BLコード
    joinMaster.setTbsPartsCode(joinMasterDto.getTbsPartsCode());
    return joinMaster;
  }

  /**
   * <pre>
   * 申請ヘッダマスタ登録.
   * </pre>
   * 
   * @param applyHeadMasterDto 申請ヘッダマスタ
   * @return 登録した申請ヘッダマスタの件数
   * @throws Throwable Throwable
   */
  public int insertApplyHeadMaster(ApplyHeadMasterCommonInfoDto applyHeadMasterDto) throws Throwable {
    return applyHeadMasterCommonService.insertApplyHeadMasterCommon(
        convertApplyHeadMasterCommonModel(applyHeadMasterDto.getApplyHeadMasterCommonDto()));
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param applyHeadMasterDtoList 申請ヘッダマスタDtoList
   * @return 申請ヘッダマスタModel
   */
  private List<ApplyHeadMasterCommon> convertApplyHeadMasterCommonModel(List<ApplyHeadMasterCommonDto> applyHeadMasterDtoList) {
    List<ApplyHeadMasterCommon> applyHeadMasterList = new ArrayList<>();
    if (null != applyHeadMasterDtoList && !applyHeadMasterDtoList.isEmpty()) {
      for (ApplyHeadMasterCommonDto applyHeadMasterDto : applyHeadMasterDtoList) {
        ApplyHeadMasterCommon applyHeadMasterCommon = convertApplyHeadMasterCommon(applyHeadMasterDto);
        applyHeadMasterList.add(applyHeadMasterCommon);
      }
    }
    return applyHeadMasterList;
  }

  /**
   * <pre>
   * DtoからEntityへの転換.
   * </pre>
   * 
   * @param applyHeadMasterDto 申請ヘッダマスタDto
   * @return 申請ヘッダマスタEntity
   */
  private ApplyHeadMasterCommon convertApplyHeadMasterCommon(ApplyHeadMasterCommonDto applyHeadMasterDto) {
    if (applyHeadMasterDto == null) {
      return null;
    }
    ApplyHeadMasterCommon applyHeadMasterCommon = new ApplyHeadMasterCommon();
    // 申請時コメン
    applyHeadMasterCommon.setApplyComments(applyHeadMasterDto.getApplyComments());
    // 申請日時
    applyHeadMasterCommon.setApplyDtTime(applyHeadMasterDto.getApplyDtTime());
    // 申請No
    applyHeadMasterCommon.setApplyNo(applyHeadMasterDto.getApplyNo());
    // 申請種類
    applyHeadMasterCommon.setApplyType(applyHeadMasterDto.getApplyType());
    // BL申請判断日
    applyHeadMasterCommon.setBlApplyJudgmentDate(applyHeadMasterDto.getBlApplyJudgmentDate());
    // BL申請判断時間
    applyHeadMasterCommon.setBlApplyJudgmentTime(applyHeadMasterDto.getBlApplyJudgmentTime());
    // BL申請結果コメント
    applyHeadMasterCommon.setBlApplyResultsComments(applyHeadMasterDto.getBlApplyResultsComments());
    // BL申請結果区分
    applyHeadMasterCommon.setBlApplyResultsFlg(applyHeadMasterDto.getBlApplyResultsFlg());
    // 部品メーカーコード
    applyHeadMasterCommon.setPartsMakerCd(applyHeadMasterDto.getPartsMakerCd());
    return applyHeadMasterCommon;
  }

  /**
   * <pre>
   * 商品マスタの情報をチェックする(警告)。
   * </pre>
   *
   * @param errorMessageList goodsMasterMakerDto
   * @param goodsMasterMakerDto GoodsMasterMakerDto
   */
  @Override
  public void checkGoodsWarning(List<String> errorMessageList, GoodsMasterMakerDto goodsMasterMakerDto) {
    if (null != goodsMasterMakerDto) {
      String message = "";
      String beforeText = SELECT_CODE + goodsMasterMakerDto.getPrmSetDtlNo1() + COMMA + MAKER_CODE
          + goodsMasterMakerDto.getPartsMakerCd() + COMMA + PRIME_PARTS_NO_WITH_H
          + goodsMasterMakerDto.getPrimePartsNoWithH() + GOODS_DATA;
      message = beforeText;
      // 価格ゼロチェック
      if (null != goodsMasterMakerDto.getNewPrice() && 0 == goodsMasterMakerDto.getNewPrice()) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.W00201), EMPTY_LIST);
      }
      if (message != beforeText) {
        errorMessageList.add(message);
      }
    }
  }

  /**
   * <pre>
   * 商品マスタの情報をチェックする(エラー)。
   * </pre>
   *
   * @param errorMessageList goodsMasterMakerDto
   * @param goodsMasterMakerDto GoodsMasterMakerDto
   */
  @Override
  public void checkGoodsError(List<String> errorMessageList, GoodsMasterMakerDto goodsMasterMakerDto) throws Throwable {
    if (null != goodsMasterMakerDto) {
      String message = "";
      String beforeText = SELECT_CODE + goodsMasterMakerDto.getPrmSetDtlNo1() + COMMA + MAKER_CODE
          + goodsMasterMakerDto.getPartsMakerCd() + COMMA + PRIME_PARTS_NO_WITH_H
          + goodsMasterMakerDto.getPrimePartsNoWithH() + GOODS_DATA;
      message = beforeText;
      // 未入力項目チェック
      message = goodsNotInputItemCheck(message, goodsMasterMakerDto);
      // オープン価格チェック
      if (null != goodsMasterMakerDto.getOpenPriceDiv()) {
        if (OpenPriceDivEnum.OpenPrice.getValue() == goodsMasterMakerDto.getOpenPriceDiv().intValue()) {
          if (null == goodsMasterMakerDto.getNewPrice()
              || (null != goodsMasterMakerDto.getNewPrice() && 0 != goodsMasterMakerDto.getNewPrice().intValue())) {
            message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00303), EMPTY_LIST);
          }
        }
      }
      // 文字桁数チェック
      message = goodsBeyondLengthCheck(message, goodsMasterMakerDto);

      // セレクトコード、分類コード、BLコード存在チェック
      HashSet<Integer> selectCodeMasterCommonSet = new HashSet<>();
      List<SelectCodeMasterCommon> selectCodeMasterCommons = applyCommonService
          .searchSelectCodeMaster(goodsMasterMakerDto.getPartsMakerCd());
      for (SelectCodeMasterCommon selectCodeMasterCommon : selectCodeMasterCommons) {
        selectCodeMasterCommonSet.add(selectCodeMasterCommon.getPrmSetDtlNo1());
      }
      HashSet<Integer> blCodeMasterCommonSet = new HashSet<>();
      List<BlCodeMasterCommon> blCodeMasterCommons = applyCommonService
          .searchBlCodeMaster(goodsMasterMakerDto.getPartsMakerCd());
      for (BlCodeMasterCommon blCodeMasterCommon : blCodeMasterCommons) {
        blCodeMasterCommonSet.add(blCodeMasterCommon.getBlCode());
      }
      HashSet<Integer> goodsRateMasterCommonSet = new HashSet<>();
      List<GoodsRateMasterCommon> goodsRateMasterCommons = applyCommonService
          .searchGoodsRateMaster(goodsMasterMakerDto.getPartsMakerCd());
      for (GoodsRateMasterCommon goodsRateMasterCommon : goodsRateMasterCommons) {
        goodsRateMasterCommonSet.add(goodsRateMasterCommon.getGoodsRateGrpCode());
      }

      if (selectCodeMasterCommonSet.add(goodsMasterMakerDto.getPrmSetDtlNo1())
          ? selectCodeMasterCommonSet.remove(goodsMasterMakerDto.getPrmSetDtlNo1()) : false) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00008), getMessageParam("セレクトコード"));
      }
      if (goodsMasterMakerDto.getTbsPartsCode() != null
          && blCodeMasterCommonSet.add(goodsMasterMakerDto.getTbsPartsCode())
              ? blCodeMasterCommonSet.remove(goodsMasterMakerDto.getTbsPartsCode()) : false) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00008), getMessageParam("BLコード"));
      }
      if (goodsMasterMakerDto.getGoodsMGroup() != null
          && goodsRateMasterCommonSet.add(goodsMasterMakerDto.getGoodsMGroup())
              ? goodsRateMasterCommonSet.remove(goodsMasterMakerDto.getGoodsMGroup()) : false) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00008), getMessageParam("商品中分類コード"));
      }

      // 削除申請理由チェック
      if (null != goodsMasterMakerDto.getDeleteFlg()
          && DeleteFlgEnum.Delete.getValue() == goodsMasterMakerDto.getDeleteFlg().intValue()
          && (null == goodsMasterMakerDto.getDeleteReason() || goodsMasterMakerDto.getDeleteReason().isEmpty())) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00017), EMPTY_LIST);
      }

      if (message != beforeText) {
        errorMessageList.add(message);
      }
    }
  }

  /**
   * <pre>
   * セットマスタの情報をチェックする(エラー)。
   * </pre>
   *
   * @param errorMessageList goodsMasterMakerDto
   * @param setMasterMakerDto SetMasterDto
   */
  @Override
  public void checkSetError(List<String> errorMessageList, SetMasterDto setMasterMakerDto) throws Throwable {
    if (null != setMasterMakerDto) {
      String message = "";
      String beforeText = SELECT_CODE + setMasterMakerDto.getPrmSetDtlNo1() + COMMA + MAKER_CODE
          + setMasterMakerDto.getPartsMakerCd() + COMMA + SET_MAIN_PARTS_NO + setMasterMakerDto.getSetMainPartsNo()
          + COMMA + SET_DISP_ORDER + setMasterMakerDto.getSetDispOrder() + SET_DATA;
      message = beforeText;
      // 未入力項目チェック
      if (null == setMasterMakerDto.getSetSubPartsNo() || "".equals(setMasterMakerDto.getSetSubPartsNo())) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00002), getMessageParam("セット子品番"));
      }
      if (null == setMasterMakerDto.getSetQty()) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00002), getMessageParam("セットQTY"));
      }
      if (null == setMasterMakerDto.getSetName() || "".equals(setMasterMakerDto.getSetName())) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00002), getMessageParam("セット名称"));
      }
      if (null == setMasterMakerDto.getSetKanaName() || "".equals(setMasterMakerDto.getSetKanaName())) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00002), getMessageParam("品名"));
      }
      // 文字桁数チェック
      message = setBeyondLengthCheck(message, setMasterMakerDto);
      // セット親品番 セット子品番
      message = setMainSubCheck(message, setMasterMakerDto);

      // セレクトコード、分類コード、BLコード存在チェック
      HashSet<Integer> selectCodeMasterCommonSet = new HashSet<>();
      List<SelectCodeMasterCommon> selectCodeMasterCommons = applyCommonService
          .searchSelectCodeMaster(setMasterMakerDto.getPartsMakerCd());
      for (SelectCodeMasterCommon selectCodeMasterCommon : selectCodeMasterCommons) {
        selectCodeMasterCommonSet.add(selectCodeMasterCommon.getPrmSetDtlNo1());
      }
      HashSet<Integer> blCodeMasterCommonSet = new HashSet<>();
      List<BlCodeMasterCommon> blCodeMasterCommons = applyCommonService
          .searchBlCodeMaster(setMasterMakerDto.getPartsMakerCd());
      for (BlCodeMasterCommon blCodeMasterCommon : blCodeMasterCommons) {
        blCodeMasterCommonSet.add(blCodeMasterCommon.getBlCode());
      }
      HashSet<Integer> goodsRateMasterCommonSet = new HashSet<>();
      List<GoodsRateMasterCommon> goodsRateMasterCommons = applyCommonService
          .searchGoodsRateMaster(setMasterMakerDto.getPartsMakerCd());
      for (GoodsRateMasterCommon goodsRateMasterCommon : goodsRateMasterCommons) {
        goodsRateMasterCommonSet.add(goodsRateMasterCommon.getGoodsRateGrpCode());
      }

      if (selectCodeMasterCommonSet.add(setMasterMakerDto.getPrmSetDtlNo1())
          ? selectCodeMasterCommonSet.remove(setMasterMakerDto.getPrmSetDtlNo1()) : false) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00008), getMessageParam("セレクトコード"));
      }
      if (null != setMasterMakerDto.getTbsPartsCode() && blCodeMasterCommonSet.add(setMasterMakerDto.getTbsPartsCode())
          ? blCodeMasterCommonSet.remove(setMasterMakerDto.getTbsPartsCode()) : false) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00008), getMessageParam("BLコード"));
      }
      if (setMasterMakerDto.getGoodsMGroup() != null && goodsRateMasterCommonSet.add(setMasterMakerDto.getGoodsMGroup())
          ? goodsRateMasterCommonSet.remove(setMasterMakerDto.getGoodsMGroup()) : false) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00008), getMessageParam("商品中分類コード"));
      }

      // 削除申請理由チェック
      if (null != setMasterMakerDto.getDeleteFlg()
          && DeleteFlgEnum.Delete.getValue() == setMasterMakerDto.getDeleteFlg().intValue()
          && (null == setMasterMakerDto.getDeleteReason() || setMasterMakerDto.getDeleteReason().isEmpty())) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00017), EMPTY_LIST);
      }

      // 親子品番重複チェック
      if (setMasterMakerDto.getSetSubPartsNo() != null && BroadleafStringUtils
          .compareTo(setMasterMakerDto.getSetMainPartsNo(), setMasterMakerDto.getSetSubPartsNo()) == 0) {
        message = message + "親商品と子商品が同一商品を指定できません。";
      }

      if (message != beforeText) {
        errorMessageList.add(message);
      }

    }
  }

  /**
   * <pre>
   * 結合マスタの情報をチェックする(警告)。
   * </pre>
   *
   * @param errorMessageList goodsMasterMakerDto
   * @param joinMasterMakerDto JoinMasterDto
   */
  @Override
  public void checkJoinWarning(List<String> errorMessageList, JoinMasterDto joinMasterMakerDto) {
    if (null != joinMasterMakerDto) {
      String message = "";
      String beforeText = SELECT_CODE + joinMasterMakerDto.getPrmSetDtlNo1() + COMMA + MAKER_CODE
          + joinMasterMakerDto.getPartsMakerCd() + COMMA + TBS_PARTS_CODE + joinMasterMakerDto.getTbsPartsCode() + COMMA
          + JOIN_SOURCE_MAKER_CODE + joinMasterMakerDto.getJoinSourceMakerCode() + COMMA + TYPE_CODE
          + joinMasterMakerDto.getPrmSetDtlNo2() + COMMA + JOIN_SOUR_PARTS_NO_WITH_H
          + joinMasterMakerDto.getJoinSourPartsNoWithH() + COMMA + JOIN_DISP_ORDER
          + joinMasterMakerDto.getJoinDispOrder() + JOIN_DATA;
      message = beforeText;
      // 削除履歴存在チェック
      JoinMasterCommon joinMasterCommon = new JoinMasterCommon();
      joinMasterCommon.setPrmSetDtlNo1(joinMasterMakerDto.getPrmSetDtlNo1());
      joinMasterCommon.setPartsMakerCd(joinMasterMakerDto.getPartsMakerCd());
      joinMasterCommon.setTbsPartsCode(joinMasterMakerDto.getTbsPartsCode());
      joinMasterCommon.setJoinSourceMakerCode(joinMasterMakerDto.getJoinSourceMakerCode());
      joinMasterCommon.setPrmSetDtlNo2(joinMasterMakerDto.getPrmSetDtlNo2());
      joinMasterCommon.setJoinSourPartsNoWithH(joinMasterMakerDto.getJoinSourPartsNoWithH());
      joinMasterCommon.setJoinDispOrder(joinMasterMakerDto.getJoinDispOrder());
      List<JoinMasterCommon> joinMasterCommonList = applyCommonService.searchByExample(joinMasterCommon);
      if (null != joinMasterCommonList) {
        for (JoinMasterCommon common : joinMasterCommonList) {
          if (null != common.getDeleteFlg() && DeleteFlgEnum.Delete.getValue() == common.getDeleteFlg().intValue()) {
            message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.W00501), EMPTY_LIST);
            break;
          }
        }
      }
      // BLコードアンマッチチェック
      boolean checkJoinMasterMaker = false;
      // String beforeCheckMessage = message;
      List<PuregoodsMasterCommon> puregoodsMasterCommons = applyCommonService.searchPureGoods(
          joinMasterMakerDto.getPrmSetDtlNo1(), joinMasterMakerDto.getPartsMakerCd(),
          joinMasterMakerDto.getJoinSourceMakerCode(), joinMasterMakerDto.getJoinSourPartsNoWithH());
      if (puregoodsMasterCommons.isEmpty()) {
        List<PuregoodsMasterCommon> puregoodsMasterCommonList = applyCommonService.searchPureGoodsByPartsMakerCd(
            joinMasterMakerDto.getPrmSetDtlNo1(), joinMasterMakerDto.getPartsMakerCd(),
            joinMasterMakerDto.getJoinSourceMakerCode());
        String joinPartNo = joinMasterMakerDto.getJoinSourPartsNoWithH();
        joinPartNo = joinPartNo.replace(HALF_HYPHEN, EMPTY).replace(HALF_SPACE, EMPTY);
        for (PuregoodsMasterCommon puregoodsMasterCommon : puregoodsMasterCommonList) {
          String pureGoodsNo = puregoodsMasterCommon.getPrimePartsNoWithH();
          pureGoodsNo = pureGoodsNo.replace(HALF_HYPHEN, EMPTY).replace(HALF_SPACE, EMPTY);
          // 結合元品番がPM部品マスタの純正品番一覧に無く､ハイフンやスペースの違いでPM部品マスタと一致する品番があれば､
          // 品番体系をPM部品マスタの体系へ変更した一覧を表示しエラーとする｡
          if (joinPartNo.equals(pureGoodsNo)) {
            checkJoinMasterMaker = checkJoinMasterMaker(puregoodsMasterCommon, joinMasterMakerDto);
            break;
          } else {
            if (joinMasterMakerDto.getJoinSourceMakerCode() == 5) {
              if (joinPartNo.length() == 11 || (joinPartNo.length() > 11 && Character.isDigit(joinPartNo.charAt(11)))) {
                if (joinPartNo.startsWith(pureGoodsNo)) {
                  checkJoinMasterMaker = checkJoinMasterMaker(puregoodsMasterCommon, joinMasterMakerDto);
                  break;
                }
              } else if (joinPartNo.length() > 11 && Character.isLetter(joinPartNo.charAt(11))) {
                if (joinPartNo.equals(pureGoodsNo)) {
                  checkJoinMasterMaker = checkJoinMasterMaker(puregoodsMasterCommon, joinMasterMakerDto);
                  break;
                }
              }
            }
          }
        }
      } else {
        for (PuregoodsMasterCommon puregoodsMasterCommon : puregoodsMasterCommons) {
          checkJoinMasterMaker = checkJoinMasterMaker(puregoodsMasterCommon, joinMasterMakerDto);
          break;
        }
      }
      if (checkJoinMasterMaker) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.W00502), EMPTY_LIST);
      }
      if (message != beforeText) {
        errorMessageList.add(message);
      }
    }
  }

  /**
   * <pre>
   * 結合マスタの情報をチェックする(エラー)。
   * </pre>
   *
   * @param errorMessageList goodsMasterMakerDto
   * @param joinMasterMakerDto JoinMasterDto
   * @param goodsMasterDtoList List
   */
  @Override
  public void checkJoinError(List<String> errorMessageList, JoinMasterDto joinMasterMakerDto) {
    if (null != joinMasterMakerDto) {
      String message = "";
      String beforeText = SELECT_CODE + joinMasterMakerDto.getPrmSetDtlNo1() + COMMA + MAKER_CODE
          + joinMasterMakerDto.getPartsMakerCd() + COMMA + TBS_PARTS_CODE + joinMasterMakerDto.getTbsPartsCode() + COMMA
          + JOIN_SOURCE_MAKER_CODE + joinMasterMakerDto.getJoinSourceMakerCode() + COMMA + TYPE_CODE
          + joinMasterMakerDto.getPrmSetDtlNo2() + COMMA + JOIN_SOUR_PARTS_NO_WITH_H
          + joinMasterMakerDto.getJoinSourPartsNoWithH() + COMMA + JOIN_DISP_ORDER
          + joinMasterMakerDto.getJoinDispOrder() + JOIN_DATA;
      message = beforeText;
      // 未入力項目チェック
      if (null == joinMasterMakerDto.getJoinDestPartsNo() || "".equals(joinMasterMakerDto.getJoinDestPartsNo())) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00002), getMessageParam("結合表示順位"));
      }
      if (null == joinMasterMakerDto.getJoinQty()) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00002), getMessageParam("結合QTY"));
      }
      // 文字桁数チェック
      message = joinBeyondLengthCheck(message, joinMasterMakerDto);
      // 商品マスタ状態チェック 商品マスタ存在チェック
      message = joinStateExistCheck(message, joinMasterMakerDto);
      // 削除申請理由チェック
      if (null != joinMasterMakerDto.getDeleteFlg()
          && DeleteFlgEnum.Delete.getValue() == joinMasterMakerDto.getDeleteFlg().intValue()
          && (null == joinMasterMakerDto.getDeleteReason() || joinMasterMakerDto.getDeleteReason().isEmpty())) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00017), EMPTY_LIST);
      }
      // 純正品番体系チェック
      message = joinPureCheck(message, joinMasterMakerDto);

      // セレクトコード、分類コード、BLコード存在チェック
      HashSet<Integer> selectCodeMasterCommonSet = new HashSet<>();
      List<SelectCodeMasterCommon> selectCodeMasterCommons = applyCommonService
          .searchSelectCodeMaster(joinMasterMakerDto.getPartsMakerCd());
      for (SelectCodeMasterCommon selectCodeMasterCommon : selectCodeMasterCommons) {
        selectCodeMasterCommonSet.add(selectCodeMasterCommon.getPrmSetDtlNo1());
      }
      HashSet<Integer> blCodeMasterCommonSet = new HashSet<>();
      List<BlCodeMasterCommon> blCodeMasterCommons = applyCommonService
          .searchBlCodeMaster(joinMasterMakerDto.getPartsMakerCd());
      for (BlCodeMasterCommon blCodeMasterCommon : blCodeMasterCommons) {
        blCodeMasterCommonSet.add(blCodeMasterCommon.getBlCode());
      }
      HashSet<Integer> goodsRateMasterCommonSet = new HashSet<>();
      List<GoodsRateMasterCommon> goodsRateMasterCommons = applyCommonService
          .searchGoodsRateMaster(joinMasterMakerDto.getPartsMakerCd());
      for (GoodsRateMasterCommon goodsRateMasterCommon : goodsRateMasterCommons) {
        goodsRateMasterCommonSet.add(goodsRateMasterCommon.getGoodsRateGrpCode());
      }

      if (selectCodeMasterCommonSet.add(joinMasterMakerDto.getPrmSetDtlNo1())
          ? selectCodeMasterCommonSet.remove(joinMasterMakerDto.getPrmSetDtlNo1()) : false) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00008), getMessageParam("セレクトコード"));
      }
      if (blCodeMasterCommonSet.add(joinMasterMakerDto.getTbsPartsCode())
          ? blCodeMasterCommonSet.remove(joinMasterMakerDto.getTbsPartsCode()) : false) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00008), getMessageParam("BLコード"));
      }
      if (joinMasterMakerDto.getGoodsMGroup() != null
          && goodsRateMasterCommonSet.add(joinMasterMakerDto.getGoodsMGroup())
              ? goodsRateMasterCommonSet.remove(joinMasterMakerDto.getGoodsMGroup()) : false) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00008), getMessageParam("商品中分類コード"));
      }

      // 優良品番の重複チェック
      List<JoinMasterMaker> joinMasterMakers = applyCommonService.searchJoinByKeys(joinMasterMakerDto.getPrmSetDtlNo1(),
          joinMasterMakerDto.getPartsMakerCd(), joinMasterMakerDto.getTbsPartsCode(),
          joinMasterMakerDto.getJoinSourceMakerCode(), joinMasterMakerDto.getJoinSourPartsNoWithH());
      HashSet<String> joinDestPartsNoSet = new HashSet<>();
      for (JoinMasterMaker joinMasterMaker : joinMasterMakers) {
        if (!joinDestPartsNoSet.add(joinMasterMaker.getJoinDestPartsNo())) {
          message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00301), EMPTY_LIST);
          break;
        }
      }

      if (message != beforeText) {
        errorMessageList.add(message);
      }
    }
  }

  /**
   * <pre>
   * ApplyCommonService.
   * </pre>
   * 
   * @param applyCommonService ApplyCommonService
   */
  @Resource
  public void setApplyCommonService(ApplyCommonService applyCommonService) {
    this.applyCommonService = applyCommonService;
  }

  /**
   * <pre>
   * 商品(common)サービス.
   * </pre>
   * 
   * @param goodsMasterCommonService 申請(common)サービス
   */
  @Resource
  public void setGoodsMasterCommonService(GoodsMasterCommonService goodsMasterCommonService) {
    this.goodsMasterCommonService = goodsMasterCommonService;
  }

  /**
   * <pre>
   * 結合(common)サービス.
   * </pre>
   * 
   * @param joinMasterCommonService 結合(common)サービス
   */
  @Resource
  public void setJoinMasterCommonService(JoinMasterCommonService joinMasterCommonService) {
    this.joinMasterCommonService = joinMasterCommonService;
  }

  /**
   * <pre>
   * セット(common)サービス.
   * </pre>
   * 
   * @param setMasterCommonService セット(common)サービス
   */
  @Resource
  public void setSetMasterCommonService(SetMasterCommonService setMasterCommonService) {
    this.setMasterCommonService = setMasterCommonService;
  }

  /**
   * <pre>
   * 申請ヘッダマスサービス.
   * </pre>
   * 
   * @param applyHeadMasterCommonService 申請ヘッダマスサービス
   */
  @Resource
  public void setApplyHeadMasterCommonService(ApplyHeadMasterCommonService applyHeadMasterCommonService) {
    this.applyHeadMasterCommonService = applyHeadMasterCommonService;
  }

  /**
   * Messageサービス.
   * 
   * @param messageService サービス
   */
  @Resource
  public void setMessageService(MessageService messageService) {
    this.messageService = messageService;
  }

  /**
   * <pre>
   * 業務メール送信を設定する。
   * </pre>
   *
   * @param bizEmailService 業務メール送信
   */
  @Resource
  public void setBizEmailService(BizEmailService bizEmailService) {
    this.bizEmailService = bizEmailService;
  }

  /**
   * <pre>
   * 商品(maker)サービス.
   * </pre>
   * 
   * @param goodsMasterMakerService GoodsMasterMakerService
   */
  @Resource
  public void setGoodsMasterMakerService(GoodsMasterMakerService goodsMasterMakerService) {
    this.goodsMasterMakerService = goodsMasterMakerService;
  }

  /**
   * <pre>
   * セット(maker)サービス.
   * </pre>
   * 
   * @param setMasterService SetMasterService
   */
  @Resource
  public void setSetMasterService(SetMasterService setMasterService) {
    this.setMasterMakerService = setMasterService;
  }

  /**
   * <pre>
   * 結合(maker)サービス.
   * </pre>
   * 
   * @param joinMasterService JoinMasterService
   */
  @Resource
  public void setUserMasterService(JoinMasterService joinMasterService) {
    this.joinMasterMakerService = joinMasterService;
  }

  /**
   * メッセージを取得します.
   */
  @Override
  public void getMessage() {

    MESSAGE_MAP.put(BregMessageCodes.E00001, messageService.messageInfo(BregMessageCodes.E00001));
    MESSAGE_MAP.put(BregMessageCodes.E00002, messageService.messageInfo(BregMessageCodes.E00002));
    MESSAGE_MAP.put(BregMessageCodes.E00003, messageService.messageInfo(BregMessageCodes.E00003));
    MESSAGE_MAP.put(BregMessageCodes.E00008, messageService.messageInfo(BregMessageCodes.E00008));
    MESSAGE_MAP.put(BregMessageCodes.E00303, messageService.messageInfo(BregMessageCodes.E00303));
    MESSAGE_MAP.put(BregMessageCodes.E00301, messageService.messageInfo(BregMessageCodes.E00301));
    MESSAGE_MAP.put(BregMessageCodes.E00503, messageService.messageInfo(BregMessageCodes.E00503));
    MESSAGE_MAP.put(BregMessageCodes.E00502, messageService.messageInfo(BregMessageCodes.E00502));
    MESSAGE_MAP.put(BregMessageCodes.E00501, messageService.messageInfo(BregMessageCodes.E00501));
    MESSAGE_MAP.put(BregMessageCodes.E00701, messageService.messageInfo(BregMessageCodes.E00701));
    MESSAGE_MAP.put(BregMessageCodes.E00702, messageService.messageInfo(BregMessageCodes.E00702));
    MESSAGE_MAP.put(BregMessageCodes.E00703, messageService.messageInfo(BregMessageCodes.E00703));
    MESSAGE_MAP.put(BregMessageCodes.E00704, messageService.messageInfo(BregMessageCodes.E00704));
    MESSAGE_MAP.put(BregMessageCodes.E00705, messageService.messageInfo(BregMessageCodes.E00705));
    MESSAGE_MAP.put(BregMessageCodes.W00501, messageService.messageInfo(BregMessageCodes.W00501));
    MESSAGE_MAP.put(BregMessageCodes.E00017, messageService.messageInfo(BregMessageCodes.E00017));
    MESSAGE_MAP.put(BregMessageCodes.W00201, messageService.messageInfo(BregMessageCodes.W00201));
    MESSAGE_MAP.put(BregMessageCodes.W00502, messageService.messageInfo(BregMessageCodes.W00502));

    MESSAGE_MAP.put(BregMessageCodes.E90002, messageService.messageInfo(BregMessageCodes.E90002));

    MESSAGE_MAP.put(BregMessageCodes.I00802, messageService.messageInfo(BregMessageCodes.I00802));

  }

  /**
   * <pre>
   * Message.
   * </pre>
   * 
   * @param bregMessageCode String
   * @return String
   */
  public String getMessage(String bregMessageCode) {
    return MESSAGE_MAP.get(bregMessageCode);
  }

  /**
   * <pre>
   * リマインダ受付（パスワード忘れ）.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param ipAddr IPアドレス
   * @param goodsCount int
   * @param setCount int
   * @param joinCount int
   */
  @Override
  public String sendMail(int makerCode, String loginId, String ipAddr, int goodsCount, int setCount, int joinCount) {
    // // 担当者情報取得(メーカーコードとログインID)
    // UserMasterCommon userMaster = userMasterService.getUserMaster(makerCode,
    // loginId);
    CompanyInfoMasterCommon companyInfoMasterCommon = applyCommonService.getBlMailAdd(makerCode);
    // Mail Address
    String blMailAddr = companyInfoMasterCommon.getBlMailAdd();
    String makerName = companyInfoMasterCommon.getCompanyName();
    // メール本文可変文言リスト
    List<String> mailBodyChangeableTextList = new ArrayList<String>();
    mailBodyChangeableTextList.add(BROADLEAF);
    mailBodyChangeableTextList.add(makerName);
    // mailBodyChangeableTextList.add(APPLICATION_NOTIFICATION_1);
    // mailBodyChangeableTextList.add(APPLICATION_NOTIFICATION_2);
    mailBodyChangeableTextList.add(String.valueOf(goodsCount));
    mailBodyChangeableTextList.add(String.valueOf(setCount));
    mailBodyChangeableTextList.add(String.valueOf(joinCount));
    // mailBodyChangeableTextList.add(APPLICATION_NOTIFICATION_3);
    boolean sendEmailRtn = bizEmailService.sendBizEmail(MailDivEnum.ApplyCommonNotification, blMailAddr,
        new ArrayList<>(), new ArrayList<>(), mailBodyChangeableTextList);
    // 業務メール送信の戻り値がFalseの場合
    if (!sendEmailRtn) {
      return MESSAGE_MAP.get(BregMessageCodes.E90002);
    }
    return null;
  }

  // /**
  // * <pre>
  // * DtoからEntityへの転換.
  // * </pre>
  // *
  // * @param goodsMasterDto 商品マスタ(メーカー)Dto
  // * @return 商品マスタ(メーカー)Model
  // */
  // private GoodsMasterMaker convertGoodsMaster(GoodsMasterMakerDto
  // goodsMasterDto) {
  // if (goodsMasterDto == null) {
  // return null;
  // }
  // GoodsMasterMaker goodsMaster = new GoodsMasterMaker();
  // // 部品メーカーコード
  // goodsMaster.setPartsMakerCd(goodsMasterDto.getPartsMakerCd());
  // // 優良品番(－付き品番)
  // goodsMaster.setPrimePartsNoWithH(goodsMasterDto.getPrimePartsNoWithH());
  // // 優良設定詳細コード１
  // goodsMaster.setPrmSetDtlNo1(goodsMasterDto.getPrmSetDtlNo1());
  // // 申請状態
  // goodsMaster.setApplyCondition(goodsMasterDto.getApplyCondition());
  // // BL登録区分
  // goodsMaster.setBlEntryFlg(goodsMasterDto.getBlEntryFlg());
  // // チェック区分
  // goodsMaster.setCheckFlg(goodsMasterDto.getCheckFlg());
  // // 削除依頼区分
  // goodsMaster.setDeleteFlg(goodsMasterDto.getDeleteFlg());
  // // 削除理由
  // goodsMaster.setDeleteReason(goodsMasterDto.getDeleteReason());
  // // 装備名称
  // goodsMaster.setEquipName(goodsMasterDto.getEquipName());
  // // エラー内容
  // goodsMaster.setErrorDetail(goodsMasterDto.getErrorDetail());
  // // データステータス
  // goodsMaster.setErrorFlg(goodsMasterDto.getErrorFlg());
  // // 商品詳細(B向け)
  // goodsMaster.setGoodsDetailB(goodsMasterDto.getGoodsDetailB());
  // // 商品詳細(C向け)
  // goodsMaster.setGoodsDetailC(goodsMasterDto.getGoodsDetailC());
  // // 商品中分類コード
  // goodsMaster.setGoodsMGroup(goodsMasterDto.getGoodsMGroup());
  // // 商品サイズ(長さ）
  // goodsMaster.setGoodsSize1(goodsMasterDto.getGoodsSize1());
  // // 商品サイズ(幅）
  // goodsMaster.setGoodsSize2(goodsMasterDto.getGoodsSize2());
  // // 商品サイズ(高さ）
  // goodsMaster.setGoodsSize3(goodsMasterDto.getGoodsSize3());
  // // 商品重量
  // goodsMaster.setGoodsWeight(goodsMasterDto.getGoodsWeight());
  // // 画像数
  // goodsMaster.setImageNo(goodsMasterDto.getImageNo());
  // // インポート区分
  // goodsMaster.setImportKbn(goodsMasterDto.getImportKbn());
  // // JAN
  // goodsMaster.setJan(goodsMasterDto.getJan());
  // // 処理区分
  // goodsMaster.setManageKbn(goodsMasterDto.getManageKbn());
  // // 新価格
  // goodsMaster.setNewPrice(goodsMasterDto.getNewPrice());
  // // オープン価格区分
  // goodsMaster.setOpenPriceDiv(goodsMasterDto.getOpenPriceDiv());
  // // 梱包サイズ(長さ）
  // goodsMaster.setPackageSize1(goodsMasterDto.getPackageSize1());
  // // 梱包サイズ(幅）
  // goodsMaster.setPackageSize2(goodsMasterDto.getPackageSize2());
  // // 梱包サイズ(高さ）
  // goodsMaster.setPackageSize3(goodsMasterDto.getPackageSize3());
  // // 層別コード
  // goodsMaster.setPartsLayerCd(goodsMasterDto.getPartsLayerCd());
  // // 優良部品カナ名称
  // goodsMaster.setPrimePartsKanaNm(goodsMasterDto.getPrimePartsKanaNm());
  // // 優良部品名称
  // goodsMaster.setPrimePartsName(goodsMasterDto.getPrimePartsName());
  // // 優良部品規格・特記事項(C向け)
  // goodsMaster.setPrimePartsSpecialNoteC(goodsMasterDto.getPrimePartsSpecialNoteC());
  // // 優良部品規格・特記事項(B向け)
  // goodsMaster.setPrimePartsSpecialNoteB(goodsMasterDto.getPrimePartsSpecialNoteB());
  // // サイズ単位
  // goodsMaster.setSizeUnit(goodsMasterDto.getSizeUnit());
  // // 適用日付
  // goodsMaster.setStartTime(goodsMasterDto.getStartDate());
  // // URL1
  // goodsMaster.setUrl1(goodsMasterDto.getUrl1());
  // // URL2
  // goodsMaster.setUrl2(goodsMasterDto.getUrl2());
  // // URL3
  // goodsMaster.setUrl3(goodsMasterDto.getUrl3());
  // // 重量単位
  // goodsMaster.setWeightUnit(goodsMasterDto.getWeightUnit());
  // // BLコード
  // goodsMaster.setTbsPartsCode(goodsMasterDto.getTbsPartsCode());
  // return goodsMaster;
  // }
  //
  // /**
  // * <pre>
  // * DtoからEntityへの転換.
  // * </pre>
  // *
  // * @param setMasterMakerDto セットマスタ(メーカー)Dto
  // * @return セットマスタ(メーカー)
  // */
  // private SetMasterMaker convertSetMaster(SetMasterDto setMasterMakerDto) {
  // if (null == setMasterMakerDto) {
  // return null;
  // }
  // SetMasterMaker setMaster = new SetMasterMaker();
  // // UUID
  // setMaster.setUuid(setMasterMakerDto.getUuid());
  // // 作成日時
  // setMaster.setInsDtTime(setMasterMakerDto.getInsDtTime());
  // // 更新日時
  // setMaster.setUpdDtTime(setMasterMakerDto.getUpdDtTime());
  // // 作成アカウントID
  // setMaster.setInsAccountId(setMasterMakerDto.getInsAccountId());
  // // 更新アカウントID
  // setMaster.setUpdAccountId(setMasterMakerDto.getUpdAccountId());
  // // 論理削除区分
  // setMaster.setLogicalDelDiv(setMasterMakerDto.getLogicalDelDiv());
  // // 優良設定詳細コード１not null primary key
  // setMaster.setPrmSetDtlNo1(setMasterMakerDto.getPrmSetDtlNo1());
  // // 部品メーカーコードnot null primary key
  // setMaster.setPartsMakerCd(setMasterMakerDto.getPartsMakerCd());
  // // 商品中分類コード
  // setMaster.setGoodsMGroup(setMasterMakerDto.getGoodsMGroup());
  // // BLコード
  // setMaster.setTbsPartsCode(setMasterMakerDto.getTbsPartsCode());
  // // セット親品番not null primary key
  // setMaster.setSetMainPartsNo(setMasterMakerDto.getSetMainPartsNo());
  // // セット表示順位not null primary key
  // setMaster.setSetDispOrder(setMasterMakerDto.getSetDispOrder());
  // // セット子品番
  // setMaster.setSetSubPartsNo(setMasterMakerDto.getSetSubPartsNo());
  // // 品名
  // setMaster.setSetKanaName(setMasterMakerDto.getSetKanaName());
  // // セット名称
  // setMaster.setSetName(setMasterMakerDto.getSetName());
  // // セットQTY
  // setMaster.setSetQty(setMasterMakerDto.getSetQty());
  // // セット規格・特記事項
  // setMaster.setSetSpecialNote(setMasterMakerDto.getSetSpecialNote());
  // // 優良部品規格・特記事項(C向け)
  // setMaster.setPrimePartsSpecialNoteC(setMasterMakerDto.getPrimePartsSpecialNoteC());
  // // 適用日付not null
  // setMaster.setStartTime(setMasterMakerDto.getStartTime());
  // // チェック区分not null
  // setMaster.setCheckFlg(setMasterMakerDto.getCheckFlg());
  // // データステータスnot null
  // setMaster.setErrorFlg(setMasterMakerDto.getErrorFlg());
  // // BL登録区分not null
  // setMaster.setBlEntryFlg(setMasterMakerDto.getBlEntryFlg());
  // // インポート区分not null
  // setMaster.setImportKbn(setMasterMakerDto.getImportKbn());
  // // 処理区分not null
  // setMaster.setManageKbn(setMasterMakerDto.getManageKbn());
  // // エラー内容
  // setMaster.setErrorDetail(setMasterMakerDto.getErrorDetail());
  // // 削除依頼区分
  // setMaster.setDeleteFlg(setMasterMakerDto.getDeleteFlg());
  // // 削除理由
  // setMaster.setDeleteReason(setMasterMakerDto.getDeleteReason());
  // // 申請状態
  // setMaster.setApplyCondition(setMasterMakerDto.getApplyCondition());
  // return setMaster;
  // }
  //
  // /**
  // * <pre>
  // * DtoからEntityへの転換.
  // * </pre>
  // *
  // * @param joinMasterDto 結合マスタ(メーカー)Dto
  // * @return 結合マスタ(メーカー)
  // */
  // private JoinMasterMaker convertJoinMaster(JoinMasterDto joinMasterDto) {
  // if (null == joinMasterDto) {
  // return null;
  // }
  // JoinMasterMaker joinMaster = new JoinMasterMaker();
  // // 優良設定詳細コード１
  // joinMaster.setPrmSetDtlNo1(joinMasterDto.getPrmSetDtlNo1());
  // // 部品メーカーコード
  // joinMaster.setPartsMakerCd(joinMasterDto.getPartsMakerCd());
  // // 商品中分類コード
  // joinMaster.setGoodsMGroup(joinMasterDto.getGoodsMGroup());
  // // BLコード
  // joinMaster.setTbsPartsCode(joinMasterDto.getTbsPartsCode());
  // // 結合元メーカーコード
  // joinMaster.setJoinSourceMakerCode(joinMasterDto.getJoinSourceMakerCode());
  // // 優良設定詳細コード２
  // joinMaster.setPrmSetDtlNo2(joinMasterDto.getPrmSetDtlNo2());
  // // 結合元品番(－付き品番)
  // joinMaster.setJoinSourPartsNoWithH(joinMasterDto.getJoinSourPartsNoWithH());
  // // 結合表示順位
  // joinMaster.setJoinDispOrder(joinMasterDto.getJoinDispOrder());
  // // 結合先品番(－付き品番)
  // joinMaster.setJoinDestPartsNo(joinMasterDto.getJoinDestPartsNo());
  // // 結合QTY
  // joinMaster.setJoinQty(joinMasterDto.getJoinQty());
  // // 結合規格・特記事項
  // joinMaster.setJoinSpecialNote(joinMasterDto.getJoinSpecialNote());
  // // 優良部品規格・特記事項(C向け)
  // joinMaster.setPrimePartsSpecialNoteC(joinMasterDto.getPrimePartsSpecialNoteC());
  // // 適用日付
  // joinMaster.setStartTime(joinMasterDto.getStartTime());
  // // 削除時申請理由
  // joinMaster.setDeleteReason(joinMasterDto.getDeleteReason());
  // // チェック区分
  // joinMaster.setCheckFlg(joinMasterDto.getCheckFlg());
  // // データステータス
  // joinMaster.setErrorFlg(joinMasterDto.getErrorFlg());
  // // BL登録区分
  // joinMaster.setBlEntryFlg(joinMasterDto.getBlEntryFlg());
  // // インポート区分
  // joinMaster.setImportKbn(joinMasterDto.getImportKbn());
  // // 処理区分
  // joinMaster.setManageKbn(joinMasterDto.getManageKbn());
  // // エラー内容
  // joinMaster.setErrorDetail(joinMasterDto.getErrorDetail());
  // // 削除依頼区分
  // joinMaster.setDeleteFlg(joinMasterDto.getDeleteFlg());
  // // 申請状態
  // joinMaster.setApplyCondition(joinMasterDto.getApplyCondition());
  // return joinMaster;
  // }

  /**
   * <pre>
   * チェック選択検索サビースを設定する。
   * </pre>
   *
   * @param selectMakerService SelectMakerService
   */
  @Resource
  public void setSelectMakerService(SelectMakerService selectMakerService) {
    this.selectMakerService = selectMakerService;
  }

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param partsMakerCd int
   * @param logicalDeleteCode int
   * @return 商品マスタ(メーカー)Dto
   */
  @Override
  public GoodsMasterMakerInfoDto searchGoodsMasterInfoBySelect(int partsMakerCd, int logicalDeleteCode) {
    GoodsMasterMakerInfoDto goodsMasterMakerInfoDto = new GoodsMasterMakerInfoDto();
    List<SelectMaker> selectMakerList = selectMakerService.searchBySelectKbn(0, partsMakerCd, logicalDeleteCode);
    List<GoodsMasterMakerDto> goodsMasterDtoList = new ArrayList<>();
    for (SelectMaker selectMaker : selectMakerList) {
      GoodsMasterMakerId goodsMasterId = new GoodsMasterMakerId();
      goodsMasterId.setPartsMakerCd(selectMaker.getGPartsMakerCd());
      goodsMasterId.setPrimePartsNoWithH(selectMaker.getGPrimePartsNoWithH());
      goodsMasterId.setPrmSetDtlNo1(selectMaker.getGPrmSetDtlNo1());
      GoodsMasterMaker goodsMasterMaker = goodsMasterMakerService.searchGoodsById(goodsMasterId);
      if (goodsMasterMaker != null) {
        goodsMasterDtoList.add(convertGoodsMasterDto(goodsMasterMaker));
      }
    }
    goodsMasterMakerInfoDto.setGoodsMasterDto(goodsMasterDtoList);
    return goodsMasterMakerInfoDto;
  }

  /**
   * <pre>
   * セットマスタ(メーカー)の取得.
   * </pre>
   * 
   * @param partsMakerCd int
   * @param logicalDeleteCode int
   * @return セットマスタ(メーカー)Dto
   */
  @Override
  public SetMasterInfoDto searchSetMasterInfoBySelect(int partsMakerCd, int logicalDeleteCode) {
    SetMasterInfoDto setMasterMakerInfoDto = new SetMasterInfoDto();
    List<SelectMaker> selectMakerList = selectMakerService.searchBySelectKbn(1, partsMakerCd, logicalDeleteCode);
    List<SetMasterDto> setMasterDtoList = new ArrayList<>();
    for (SelectMaker selectMaker : selectMakerList) {
      SetMasterMakerId setMasterId = new SetMasterMakerId();
      setMasterId.setPartsMakerCd(selectMaker.getSPartsMakerCd());
      setMasterId.setSetMainPartsNo(selectMaker.getSSetMainPartsNo());
      setMasterId.setPrmSetDtlNo1(selectMaker.getSPrmSetDtlNo1());
      setMasterId.setSetDispOrder(selectMaker.getSSetDispOrder());
      SetMasterMaker setMasterMaker = setMasterMakerService.searchBySetMasterId(setMasterId);
      if (setMasterMaker != null) {
        SetMasterDto setMasterDto = new SetMasterDto();
        DataConvert.setMasterMakerconvertToDtoOrModel(setMasterMaker, setMasterDto);
        setMasterDtoList.add(setMasterDto);
      }
    }
    setMasterMakerInfoDto.setSetMasterDtoList(setMasterDtoList);
    return setMasterMakerInfoDto;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param partsMakerCd int
   * @param logicalDeleteCode int
   * @return 結合マスタ(メーカー)Dto
   */
  @Override
  public JoinMasterInfoDto searchJoinMasterInfoBySelect(int partsMakerCd, int logicalDeleteCode) {
    JoinMasterInfoDto joinMasterMakerInfoDto = new JoinMasterInfoDto();
    List<SelectMaker> selectMakerList = selectMakerService.searchBySelectKbn(2, partsMakerCd, logicalDeleteCode);
    List<JoinMasterDto> joinMasterDtoList = new ArrayList<>();
    for (SelectMaker selectMaker : selectMakerList) {
      JoinMasterMakerId joinMasterId = new JoinMasterMakerId();
      joinMasterId.setJoinDispOrder(selectMaker.getJJoinDispOrder());
      joinMasterId.setJoinSourceMakerCode(selectMaker.getJJoinSourceMakerCode());
      joinMasterId.setJoinSourPartsNoWithH(selectMaker.getJJoinSourPartsNoWithH());
      joinMasterId.setPartsMakerCd(selectMaker.getJPartsMakerCd());
      joinMasterId.setPrmSetDtlNo1(selectMaker.getJPrmSetDtlNo1());
      joinMasterId.setPrmSetDtlNo2(selectMaker.getJPrmSetDtlNo2());
      joinMasterId.setTbsPartsCode(selectMaker.getJTbsPartsCode());
      JoinMasterMaker joinMasterMaker = joinMasterMakerService.searchByJoinMasterId(joinMasterId);
      if (joinMasterMaker != null) {
        joinMasterDtoList.add(this.convertJoinMasterDto(joinMasterMaker));
      }
    }
    joinMasterMakerInfoDto.setJoinMasterDto(joinMasterDtoList);
    return joinMasterMakerInfoDto;
  }

  /**
   * <pre>
   * チェック選択に商品情報削除.
   * </pre>
   * 
   * @param selectKbn セットする区分
   */
  @Override
  public void removeBySelectKbn(int selectKbn, int partsMakerCd) {
    selectMakerService.deleteGoodsBySelectKbn(selectKbn, partsMakerCd);
  }

  /**
   * メール本文##の文字列置換する。
   * 
   * @param contentText メール本文
   * @param mailBodyChangeableTextList メール本文可変文言の処理
   * @return メール本文
   */
  private String tranContentText(String contentText, List<String> mailBodyChangeableTextList) {

    // メール本文の改行文字置換する。
    contentText = contentText.replace("<br>", "\n");

    for (int i = 0; i < mailBodyChangeableTextList.size(); i++) {
      contentText = contentText.replace("$" + (i + 1), mailBodyChangeableTextList.get(i));
    }
    return contentText;
  }

  /**
   * getMessageParam。
   * 
   * @param param String...
   * @return List
   */
  private List<String> getMessageParam(String... param) {
    List<String> params = new ArrayList<>();
    for (String s : param) {
      params.add(s);
    }
    return params;
  }

  /**
   * 文字桁数チェック。
   * 
   * @param message String
   * @param goodsMasterMakerDto GoodsMasterMakerDto
   * @return String
   */
  private String goodsBeyondLengthCheck(String message, GoodsMasterMakerDto goodsMasterMakerDto) {
    message = goodsBeyondLengthCheck1(message, goodsMasterMakerDto);
    message = goodsBeyondLengthCheck2(message, goodsMasterMakerDto);
    return message;
  }

  /**
   * 文字桁数チェック。
   * 
   * @param message String
   * @param goodsMasterMakerDto GoodsMasterMakerDto
   * @return String
   */
  private String goodsBeyondLengthCheck1(String message, GoodsMasterMakerDto goodsMasterMakerDto) {
    // ｾﾚｸﾄｺｰﾄﾞ
    if (isLengthBeyond(4, stringValueOf(goodsMasterMakerDto.getPrmSetDtlNo1()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("ｾﾚｸﾄｺｰﾄﾞ", String.valueOf(4)));
    }
    // ﾒｰｶｰｺｰﾄﾞ
    if (isLengthBeyond(4, stringValueOf(goodsMasterMakerDto.getPartsMakerCd()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("ﾒｰｶｰｺｰﾄﾞ", String.valueOf(4)));
    }
    // 分類ｺｰﾄﾞ
    if (isLengthBeyond(4, stringValueOf(goodsMasterMakerDto.getGoodsMGroup()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("分類ｺｰﾄﾞ", String.valueOf(4)));
    }
    // BLｺｰﾄﾞ
    if (isLengthBeyond(8, stringValueOf(goodsMasterMakerDto.getTbsPartsCode()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("BLｺｰﾄﾞ", String.valueOf(8)));
    }
    // 優良品番
    if (isLengthBeyond(24, goodsMasterMakerDto.getPrimePartsNoWithH())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("優良品番", String.valueOf(24)));
    }
    // 優良品名（半角）
    if (isLengthBeyond(60, goodsMasterMakerDto.getPrimePartsKanaNm())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("優良品名（半角）", String.valueOf(60)));
    }
    // 優良品名（全角）
    if (isLengthBeyond(60, goodsMasterMakerDto.getPrimePartsName())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("優良品名（全角）", String.valueOf(60)));
    }
    // 優良定価
    if (isLengthBeyond(7, stringValueOf(goodsMasterMakerDto.getNewPrice()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("優良定価", String.valueOf(7)));
    }
    // オープン価格区分
    if (isLengthBeyond(2, stringValueOf(goodsMasterMakerDto.getOpenPriceDiv()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("オープン価格区分", String.valueOf(2)));
    }
    // JAN
    if (isLengthBeyond(13, stringValueOf(goodsMasterMakerDto.getJan()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("JAN", String.valueOf(13)));
    }
    // 層別
    if (isLengthBeyond(2, goodsMasterMakerDto.getPartsLayerCd())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("層別", String.valueOf(2)));
    }
    // 規格
    if (isLengthBeyond(60, goodsMasterMakerDto.getEquipName())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("規格", String.valueOf(60)));
    }
    // 特記事項
    if (isLengthBeyond(80, goodsMasterMakerDto.getPrimePartsSpecialNoteB())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("特記事項", String.valueOf(80)));
    }
    // 規格/特記（一般）
    if (isLengthBeyond(80, goodsMasterMakerDto.getPrimePartsSpecialNoteC())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("規格/特記（一般）", String.valueOf(80)));
    }
    // 商品詳細(B向け)
    if (isLengthBeyond(512, goodsMasterMakerDto.getGoodsDetailB())) {
      message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003),
          getMessageParam("商品詳細(B向け)", String.valueOf(512)));
    }
    // 商品詳細(C向け)
    if (isLengthBeyond(512, goodsMasterMakerDto.getGoodsDetailC())) {
      message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003),
          getMessageParam("商品詳細(C向け)", String.valueOf(512)));
    }
    return message;
  }

  /**
   * 文字桁数チェック。
   * 
   * @param message String
   * @param goodsMasterMakerDto GoodsMasterMakerDto
   * @return String
   */
  private String goodsBeyondLengthCheck2(String message, GoodsMasterMakerDto goodsMasterMakerDto) {
    // 商品サイズ(長さ）
    if (isLengthBeyond(6, stringValueOf(goodsMasterMakerDto.getGoodsSize1()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("商品サイズ(長さ）", String.valueOf(6)));
    }
    // 商品サイズ(幅）
    if (isLengthBeyond(6, stringValueOf(goodsMasterMakerDto.getGoodsSize2()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("商品サイズ(幅）", String.valueOf(6)));
    }
    // 商品サイズ(高さ）
    if (isLengthBeyond(6, stringValueOf(goodsMasterMakerDto.getGoodsSize3()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("商品サイズ(高さ）", String.valueOf(6)));
    }
    // 梱包サイズ(長さ）
    if (isLengthBeyond(6, stringValueOf(goodsMasterMakerDto.getPackageSize1()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("梱包サイズ(長さ）", String.valueOf(6)));
    }
    // 梱包サイズ(幅）
    if (isLengthBeyond(6, stringValueOf(goodsMasterMakerDto.getPackageSize2()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("梱包サイズ(幅）", String.valueOf(6)));
    }
    // 梱包サイズ(高さ）
    if (isLengthBeyond(6, stringValueOf(goodsMasterMakerDto.getPackageSize3()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("梱包サイズ(高さ）", String.valueOf(6)));
    }
    // サイズ単位
    if (isLengthBeyond(3, goodsMasterMakerDto.getSizeUnit())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("サイズ単位", String.valueOf(3)));
    }
    // 商品重量
    if (isLengthBeyond(6, stringValueOf(goodsMasterMakerDto.getGoodsWeight()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("商品重量", String.valueOf(6)));
    }
    // 重量単位
    if (isLengthBeyond(3, goodsMasterMakerDto.getWeightUnit())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("重量単位", String.valueOf(3)));
    }
    // URL1
    if (isLengthBeyond(512, goodsMasterMakerDto.getUrl1())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("URL1", String.valueOf(512)));
    }
    // URL2
    if (isLengthBeyond(512, goodsMasterMakerDto.getUrl2())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("ｾﾚｸﾄｺｰﾄﾞ", String.valueOf(512)));
    }
    // URL3
    if (isLengthBeyond(512, goodsMasterMakerDto.getUrl3())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("ｾﾚｸﾄｺｰﾄﾞ", String.valueOf(512)));
    }
    // 画像数
    if (isLengthBeyond(2, stringValueOf(goodsMasterMakerDto.getImageNo()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("画像数", String.valueOf(2)));
    }
    // 適用日付
    if (isDateBeyondLength(goodsMasterMakerDto.getStartDate())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("適用日付", String.valueOf(8)));
    }
    // 削除依頼区分
    if (isLengthBeyond(2, stringValueOf(goodsMasterMakerDto.getDeleteFlg()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("削除依頼区分", String.valueOf(2)));
    }
    // 削除理由
    if (isLengthBeyond(512, goodsMasterMakerDto.getDeleteReason())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("削除理由", String.valueOf(512)));
    }
    return message;
  }

  /**
   * 未入力項目チェック。
   * 
   * @param message String
   * @param goodsMasterMakerDto GoodsMasterMakerDto
   * @return String
   */
  private String goodsNotInputItemCheck(String message, GoodsMasterMakerDto goodsMasterMakerDto) {
    if (null == goodsMasterMakerDto.getGoodsMGroup()) {
      message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00002), getMessageParam("商品中分類コード"));
    }
    if (null == goodsMasterMakerDto.getTbsPartsCode()) {
      message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00002), getMessageParam("BLコード"));
    }
    if (null == goodsMasterMakerDto.getPrimePartsName() || "".equals(goodsMasterMakerDto.getPrimePartsName())) {
      message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00002), getMessageParam("優良部品名称"));
    }
    if (null == goodsMasterMakerDto.getPrimePartsKanaNm() || "".equals(goodsMasterMakerDto.getPrimePartsKanaNm())) {
      message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00002), getMessageParam("優良部品カナ名称"));
    }
    if (null == goodsMasterMakerDto.getNewPrice()) {
      message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00002), getMessageParam("新価格"));
    }
    if (null == goodsMasterMakerDto.getOpenPriceDiv()) {
      message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00002), getMessageParam("オープン価格区分"));
    }
    return message;
  }

  /**
   * 文字桁数チェック。
   * 
   * @param message String
   * @param setMasterMakerDto SetMasterDto
   * @return String
   */
  private String setBeyondLengthCheck(String message, SetMasterDto setMasterMakerDto) {
    // セレクトコード
    if (isLengthBeyond(4, stringValueOf(setMasterMakerDto.getPrmSetDtlNo1()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("セレクトコード", String.valueOf(4)));
    }
    // メーカーコード
    if (isLengthBeyond(4, stringValueOf(setMasterMakerDto.getPartsMakerCd()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("削除理由", String.valueOf(4)));
    }
    // 分類コード
    if (isLengthBeyond(4, stringValueOf(setMasterMakerDto.getGoodsMGroup()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("分類コード", String.valueOf(4)));
    }
    // BLコード
    if (isLengthBeyond(8, stringValueOf(setMasterMakerDto.getTbsPartsCode()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("BLコード", String.valueOf(8)));
    }
    // 優良親品番
    if (isLengthBeyond(24, setMasterMakerDto.getSetMainPartsNo())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("優良親品番", String.valueOf(24)));
    }
    // セット表示順位
    if (isLengthBeyond(4, stringValueOf(setMasterMakerDto.getSetDispOrder()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("セット表示順位", String.valueOf(4)));
    }
    // 優良子品番
    if (isLengthBeyond(24, setMasterMakerDto.getSetSubPartsNo())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("優良子品番", String.valueOf(24)));
    }
    // 品名
    if (isLengthBeyond(60, setMasterMakerDto.getSetKanaName())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("品名", String.valueOf(60)));
    }
    // セット名称
    if (isLengthBeyond(60, setMasterMakerDto.getSetName())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("セット名称", String.valueOf(60)));
    }
    // セットQTY
    if (isLengthBeyond(5, stringValueOf(setMasterMakerDto.getSetQty()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("セットQTY", String.valueOf(5)));
    }
    // セット規格・特記事項
    if (isLengthBeyond(80, setMasterMakerDto.getSetSpecialNote())) {
      message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003),
          getMessageParam("セット規格・特記事項", String.valueOf(80)));
    }
    // 優良部品規格・特記事項(C向け)
    if (isLengthBeyond(80, setMasterMakerDto.getPrimePartsSpecialNoteC())) {
      message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003),
          getMessageParam("優良部品規格・特記事項(C向け)", String.valueOf(80)));
    }
    // 適用日付
    if (isDateBeyondLength(setMasterMakerDto.getStartTime())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("適用日付", String.valueOf(8)));
    }
    // 削除依頼区分
    if (isLengthBeyond(2, stringValueOf(setMasterMakerDto.getDeleteFlg()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("削除依頼区分", String.valueOf(2)));
    }
    // 削除理由
    if (isLengthBeyond(512, setMasterMakerDto.getDeleteReason())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("削除理由", String.valueOf(512)));
    }
    return message;
  }

  /**
   * セット親品番、セット子品番チェック。
   * 
   * @param message String
   * @param setMasterMakerDto SetMasterDto
   * @return String
   */
  private String setMainSubCheck(String message, SetMasterDto setMasterMakerDto) {
    // セット親品番
    message = setMainCheck(message, setMasterMakerDto);
    // セット子品番
    message = setSubCheck(message, setMasterMakerDto);
    return message;
  }

  /**
   * セット親品番チェック。
   * 
   * @param message String
   * @param setMasterMakerDto SetMasterDto
   * @return String
   */
  private String setMainCheck(String message, SetMasterDto setMasterMakerDto) {
    // セット親品番
    // 商品マスタ状態チェック
    if (!BroadleafStringUtils.isEmpty(setMasterMakerDto.getSetMainPartsNo())) {
      // 商品メーカーを検索
      List<GoodsMasterMaker> goodsList1 = applyCommonService.searchGoods(0, setMasterMakerDto.getPrmSetDtlNo1(),
          setMasterMakerDto.getPartsMakerCd(), setMasterMakerDto.getSetMainPartsNo());
      HashSet<String> set1 = new HashSet<>();
      for (GoodsMasterMaker goods : goodsList1) {
        set1.add(goods.getPrimePartsNoWithH());
      }
      if (!(set1.add(setMasterMakerDto.getSetMainPartsNo()) ? set1.remove(setMasterMakerDto.getSetMainPartsNo())
          : false)) {
        // 商品マスタ存在チェック
        for (GoodsMasterMaker goods : goodsList1) {
          if (null != goods.getApplyCondition()
              && setMasterMakerDto.getSetMainPartsNo().equals(goods.getPrimePartsNoWithH())
              && ApplyConditionEnum.NoApply.getValue() == goods.getApplyCondition().intValue()) {
            message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00502),
                getMessageParam("親", setMasterMakerDto.getSetMainPartsNo(), "未申請"));
            break;
          } else if (null != goods.getApplyCondition()
              && setMasterMakerDto.getSetMainPartsNo().equals(goods.getPrimePartsNoWithH())
              && ApplyConditionEnum.Apply.getValue() == goods.getApplyCondition().intValue()) {
            message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00502),
                getMessageParam("親", setMasterMakerDto.getSetMainPartsNo(), "申請中"));
            break;
          }
        }
      } else {
        for (GoodsMasterMaker goods : goodsList1) {
          if (null != goods.getApplyCondition()
              && ApplyConditionEnum.Approval.getValue() == goods.getApplyCondition().intValue()) {
            message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00501), getMessageParam("親"));
            break;
          }
        }
      }
    }
    return message;
  }

  /**
   * セット子品番チェック。
   * 
   * @param message String
   * @param setMasterMakerDto SetMasterDto
   * @return String
   */
  private String setSubCheck(String message, SetMasterDto setMasterMakerDto) {
    // セット子品番
    if (!BroadleafStringUtils.isEmpty(setMasterMakerDto.getSetSubPartsNo())) {
      // 商品メーカーを検索
      List<GoodsMasterMaker> goodsList2 = applyCommonService.searchGoods(0, setMasterMakerDto.getPrmSetDtlNo1(),
          setMasterMakerDto.getPartsMakerCd(), setMasterMakerDto.getSetSubPartsNo());
      HashSet<String> set2 = new HashSet<>();
      for (GoodsMasterMaker goods : goodsList2) {
        set2.add(goods.getPrimePartsNoWithH());
      }
      if (!(set2.add(setMasterMakerDto.getSetSubPartsNo()) ? set2.remove(setMasterMakerDto.getSetSubPartsNo())
          : false)) {
        // 商品マスタ存在チェック
        for (GoodsMasterMaker goods : goodsList2) {
          if (null != setMasterMakerDto.getSetSubPartsNo() && null != goods.getApplyCondition()
              && setMasterMakerDto.getSetSubPartsNo().equals(goods.getPrimePartsNoWithH())
              && ApplyConditionEnum.NoApply.getValue() == goods.getApplyCondition().intValue()) {
            message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00502),
                getMessageParam("子", setMasterMakerDto.getSetSubPartsNo(), "未申請"));
            break;
          } else if (null != setMasterMakerDto.getSetSubPartsNo() && null != goods.getApplyCondition()
              && setMasterMakerDto.getSetSubPartsNo().equals(goods.getPrimePartsNoWithH())
              && ApplyConditionEnum.Apply.getValue() == goods.getApplyCondition().intValue()) {
            message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00502),
                getMessageParam("子", setMasterMakerDto.getSetSubPartsNo(), "申請中"));
            break;
          }
        }
      } else {
        for (GoodsMasterMaker goods : goodsList2) {
          if (null != goods.getApplyCondition()
              && ApplyConditionEnum.Approval.getValue() == goods.getApplyCondition().intValue()) {
            message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00501), getMessageParam("子"));
            break;
          }
        }
      }
    }
    return message;
  }

  /**
   * 文字桁数チェック。
   * 
   * @param message String
   * @param joinMasterMakerDto JoinMasterDto
   * @return String
   */
  private String joinBeyondLengthCheck(String message, JoinMasterDto joinMasterMakerDto) {
    // セレクトコード
    if (isLengthBeyond(4, stringValueOf(joinMasterMakerDto.getPrmSetDtlNo1()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("セレクトコード", String.valueOf(4)));
    }
    // メーカーコード
    if (isLengthBeyond(4, stringValueOf(joinMasterMakerDto.getPartsMakerCd()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("メーカーコード", String.valueOf(4)));
    }
    // 分類コード
    if (isLengthBeyond(4, stringValueOf(joinMasterMakerDto.getGoodsMGroup()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("分類コード", String.valueOf(4)));
    }
    // BLコード
    if (isLengthBeyond(8, stringValueOf(joinMasterMakerDto.getTbsPartsCode()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("BLコード", String.valueOf(8)));
    }
    // 結合元メーカーコード
    if (isLengthBeyond(4, stringValueOf(joinMasterMakerDto.getJoinSourceMakerCode()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("結合元メーカーコード", String.valueOf(4)));
    }
    // 優良設定詳細コード２
    if (isLengthBeyond(4, stringValueOf(joinMasterMakerDto.getPrmSetDtlNo2()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("優良設定詳細コード２", String.valueOf(4)));
    }
    // 純正品番
    if (isLengthBeyond(24, joinMasterMakerDto.getJoinSourPartsNoWithH())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("純正品番", String.valueOf(24)));
    }
    // 表示順位
    if (isLengthBeyond(4, stringValueOf(joinMasterMakerDto.getJoinDispOrder()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("表示順位", String.valueOf(4)));
    }
    // 優良品番
    if (isLengthBeyond(24, joinMasterMakerDto.getJoinDestPartsNo())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("優良品番", String.valueOf(24)));
    }
    // QTY
    if (isLengthBeyond(5, stringValueOf(joinMasterMakerDto.getJoinQty()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("QTY", String.valueOf(5)));
    }
    // 規格・特記事項
    if (isLengthBeyond(80, joinMasterMakerDto.getJoinSpecialNote())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("規格・特記事項", String.valueOf(80)));
    }
    // 優良部品規格・特記事項(C向け)
    if (isLengthBeyond(80, joinMasterMakerDto.getPrimePartsSpecialNoteC())) {
      message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003),
          getMessageParam("優良部品規格・特記事項(C向け)", String.valueOf(80)));
    }
    // 適用日付
    if (isDateBeyondLength(joinMasterMakerDto.getStartTime())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("適用日付", String.valueOf(8)));
    }
    // 削除理由
    if (isLengthBeyond(80, joinMasterMakerDto.getDeleteReason())) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("削除理由", String.valueOf(80)));
    }
    // 削除依頼区分
    if (isLengthBeyond(2, stringValueOf(joinMasterMakerDto.getDeleteFlg()))) {
      message = message
          + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00003), getMessageParam("削除依頼区分", String.valueOf(2)));
    }
    return message;
  }

  /**
   * 商品マスタ状態、商品マスタ存在チェック。
   * 
   * @param message String
   * @param joinMasterMakerDto JoinMasterDto
   * @return String
   */
  private String joinStateExistCheck(String message, JoinMasterDto joinMasterMakerDto) {
    // 商品マスタ状態チェック
    if (!BroadleafStringUtils.isEmpty(joinMasterMakerDto.getJoinDestPartsNo())) {
      List<GoodsMasterMaker> goodsList = applyCommonService.searchGoods(0, joinMasterMakerDto.getPrmSetDtlNo1(),
          joinMasterMakerDto.getPartsMakerCd(), joinMasterMakerDto.getJoinDestPartsNo());
      HashSet<String> set = new HashSet<>();
      for (GoodsMasterMaker goods : goodsList) {
        set.add(goods.getPrimePartsNoWithH());
      }
      if (!(set.add(joinMasterMakerDto.getJoinDestPartsNo()) ? set.remove(joinMasterMakerDto.getJoinDestPartsNo())
          : false)) {
        // 商品マスタ存在チェック
        for (GoodsMasterMaker goods : goodsList) {
          if (null != joinMasterMakerDto.getJoinDestPartsNo() && null != goods.getApplyCondition()
              && joinMasterMakerDto.getJoinDestPartsNo().equals(goods.getPrimePartsNoWithH())
              && ApplyConditionEnum.NoApply.getValue() == goods.getApplyCondition().intValue()) {
            message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00702),
                getMessageParam(joinMasterMakerDto.getJoinDestPartsNo(), "未申請"));
            break;
          } else if (null != joinMasterMakerDto.getJoinDestPartsNo() && null != goods.getApplyCondition()
              && joinMasterMakerDto.getJoinDestPartsNo().equals(goods.getPrimePartsNoWithH())
              && ApplyConditionEnum.Apply.getValue() == goods.getApplyCondition().intValue()) {
            message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00702),
                getMessageParam(joinMasterMakerDto.getJoinDestPartsNo(), "申請中"));
            break;
          }
        }
      } else {
        for (GoodsMasterMaker goods : goodsList) {
          if (null != goods.getApplyCondition()
              && ApplyConditionEnum.Approval.getValue() == goods.getApplyCondition().intValue()) {
            message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00701), EMPTY_LIST);
            break;
          }
        }
      }
    }
    return message;
  }

  /**
   * 純正品番体系チェック。
   * 
   * @param message String
   * @param joinMasterMakerDto JoinMasterDto
   * @return String
   */
  private String joinPureCheck(String message, JoinMasterDto joinMasterMakerDto) {
    // 純正品番体系チェック
    // boolean checkJoinMasterMaker = false;
    String beforeCheckMessage = message;
    List<PuregoodsMasterCommon> puregoodsMasterCommons = applyCommonService.searchPureGoods(
        joinMasterMakerDto.getPrmSetDtlNo1(), joinMasterMakerDto.getPartsMakerCd(),
        joinMasterMakerDto.getJoinSourceMakerCode(), joinMasterMakerDto.getJoinSourPartsNoWithH());
    if (puregoodsMasterCommons.isEmpty()) {
      List<PuregoodsMasterCommon> puregoodsMasterCommonList = applyCommonService.searchPureGoodsByPartsMakerCd(
          joinMasterMakerDto.getPrmSetDtlNo1(), joinMasterMakerDto.getPartsMakerCd(),
          joinMasterMakerDto.getJoinSourceMakerCode());
      String joinPartNo = joinMasterMakerDto.getJoinSourPartsNoWithH();
      joinPartNo = joinPartNo.replace(HALF_HYPHEN, EMPTY).replace(HALF_SPACE, EMPTY);
      for (PuregoodsMasterCommon puregoodsMasterCommon : puregoodsMasterCommonList) {
        String pureGoodsNo = puregoodsMasterCommon.getPrimePartsNoWithH();
        pureGoodsNo = pureGoodsNo.replace(HALF_HYPHEN, EMPTY).replace(HALF_SPACE, EMPTY);
        // 結合元品番がPM部品マスタの純正品番一覧に無く､ハイフンやスペースの違いでPM部品マスタと一致する品番があれば､
        // 品番体系をPM部品マスタの体系へ変更した一覧を表示しエラーとする｡
        if (joinPartNo.equals(pureGoodsNo)) {
          message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00704),
              getMessageParam(puregoodsMasterCommon.getPrimePartsNoWithH()));
          // checkJoinMasterMaker =
          // checkJoinMasterMaker(puregoodsMasterCommon, joinMasterMakerDto);
          break;
        } else {
          if (joinMasterMakerDto.getJoinSourceMakerCode() == 5) {
            if (joinPartNo.length() == 11 || (joinPartNo.length() > 11 && Character.isDigit(joinPartNo.charAt(11)))) {
              if (joinPartNo.startsWith(pureGoodsNo)) {
                message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00704),
                    getMessageParam(puregoodsMasterCommon.getPrimePartsNoWithH()));
                // checkJoinMasterMaker =
                // checkJoinMasterMaker(puregoodsMasterCommon,
                // joinMasterMakerDto);
                break;
              }
            } else if (joinPartNo.length() > 11 && Character.isLetter(joinPartNo.charAt(11))) {
              if (joinPartNo.equals(pureGoodsNo)) {
                message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00704),
                    getMessageParam(puregoodsMasterCommon.getPrimePartsNoWithH()));
                // checkJoinMasterMaker =
                // checkJoinMasterMaker(puregoodsMasterCommon,
                // joinMasterMakerDto);
                break;
              }
            }
          }
        }
      }
      // カーメーカー品番チェック
      if (beforeCheckMessage.equals(message)) {
        message = message + tranContentText(MESSAGE_MAP.get(BregMessageCodes.E00703), EMPTY_LIST);
      }
    }
    return message;
  }

  /**
   * 文字チェック。
   * 
   * @param length int
   * @param param String
   * @return boolean
   */
  private boolean isLengthBeyond(int length, String param) {
    return null != param && length < param.length();
  }

  /**
   * 結合マスタ(メーカー)案のチェック.
   * 
   * @param puregoodsMasterCommon 純正商品マスタ(共有)
   * @param joinMasterMakerDto 結合マスタ
   * @return チェック結果
   */
  private boolean checkJoinMasterMaker(PuregoodsMasterCommon puregoodsMasterCommon, JoinMasterDto joinMasterMakerDto) {
    if (puregoodsMasterCommon.getTbsPartsCode() != null
        && puregoodsMasterCommon.getTbsPartsCode().equals(joinMasterMakerDto.getTbsPartsCode())) {
      if (null == applyCommonService.searchBlCodeMasterCommonById(puregoodsMasterCommon.getJoinSourceMakerCode(),
          puregoodsMasterCommon.getTbsPartsCode())) {
        return true;
      }
    }
    return false;
  }

  /**
   * DateからStringに転換
   * 
   * @param date date
   * @return 転換結果
   */
  private String toString(Date date) {
    SimpleDateFormat formater = new SimpleDateFormat();
    formater.applyPattern("yyyyMMdd");
    String time = formater.format(date);
    return time;
  }

  /**
   * Timestamp文字チェック。
   * 
   * @param timestamp Timestamp
   * @return boolean
   */
  private boolean isDateBeyondLength(Timestamp timestamp) {
    if (null != timestamp) {
      Date d = new Date(timestamp.getTime());
      String dateStr = toString(d);
      return isLengthBeyond(8, dateStr);
    }
    return false;
  }

  /**
   * stringValueOf。
   * 
   * @param obj Object
   * @return String
   */
  private String stringValueOf(Object obj) {
    if (null == obj) {
      return EMPTY;
    }
    return String.valueOf(obj);
  }

}
