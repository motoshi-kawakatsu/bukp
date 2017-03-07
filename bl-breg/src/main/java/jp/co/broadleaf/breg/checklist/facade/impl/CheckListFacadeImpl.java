//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : lulei
// 作 成 日       2017/02/09   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.checklist.facade.impl;

import jp.co.broadleaf.breg.checklist.facade.CheckListFacade;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.goodslist.domain.model.GoodsMasterMakerModel;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerInfoDto;
import jp.co.broadleaf.breg.goodslist.service.GoodsMasterMakerService;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterInfoDto;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterModel;
import jp.co.broadleaf.breg.joinlist.service.JoinMasterService;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterMakerModel;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterInfoDto;
import jp.co.broadleaf.breg.setlist.service.SetMasterService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * チェックリスト FacadeImplクラス.
 * </pre>
 */
public class CheckListFacadeImpl implements CheckListFacade {

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
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param count 現在位置
   * @param maxRows maxRows
   * @param makerCode makerCode
   * @return 商品マスタ(メーカー)Dto
   */
  @Override
  public GoodsMasterMakerInfoDto searchErrorGoodsList(Long count, Long maxRows, int makerCode) {
    GoodsMasterMakerInfoDto itemMasterInfoDto = new GoodsMasterMakerInfoDto();
    GoodsMasterMakerModel goodsMasterMakerModel = goodsMasterService.searchErrorGoodsList(count, maxRows, makerCode);
    itemMasterInfoDto.setSearchCounts(goodsMasterMakerModel.getSearchCounts());
    itemMasterInfoDto.setGoodsMasterDto(convertGoodsMasterDtoList(goodsMasterMakerModel));
    return itemMasterInfoDto;
  }

  /**
   * <pre>
   * セットマスタ(メーカー)の全データ取得.
   * </pre>
   * 
   * @param count 現在位置
   * @param maxRows maxRows
   * @param makerCode makerCode
   * @return セットマスタ(メーカー)Dto
   */
  @Override
  public SetMasterInfoDto searchSetErrorList(Long count, Long maxRows, int makerCode) {
    SetMasterInfoDto setMasterInfoDto = new SetMasterInfoDto();
    SetMasterMakerModel setMasterMakerModel = setMasterService.searchSetErrorList(count, maxRows, makerCode);
    setMasterInfoDto.setSearchCounts(setMasterMakerModel.getSearchCounts());
    setMasterInfoDto.setSetMasterDtoList(convertToSetMasterDtoList(setMasterMakerModel));
    return setMasterInfoDto;
  }

  /**
   * <pre>
   * 結合マスタ(メーカー)取得.
   * </pre>
   * 
   * @param count 現在位置
   * @param maxRows maxRows
   * @param makerCode makerCode
   * @return 結合マスタ(メーカー)Dto
   */
  @Override
  public JoinMasterInfoDto searchJoinErrorList(Long count, Long maxRows, int makerCode) {
    JoinMasterInfoDto joinMasterInfoDto = new JoinMasterInfoDto();
    JoinMasterModel joinMasterModel = joinMasterService.searchJoinErrorList(count, maxRows, makerCode);
    joinMasterInfoDto.setSearchCounts(joinMasterModel.getSearchCounts());
    joinMasterInfoDto.setJoinMasterDto(convertJoinMasterInfoDto(joinMasterModel));
    return joinMasterInfoDto;
  }

  /**
   * <pre>
   * EntityからDtoへの転換.
   * </pre>
   * 
   * @param setMasterMakerModel セットマスタ(メーカー)Model
   * @return セットマスタ(メーカー)Dto
   */
  private List<SetMasterDto> convertToSetMasterDtoList(SetMasterMakerModel setMasterMakerModel) {
    List<SetMasterDto> setMasterDtoList = new ArrayList<SetMasterDto>();
    if (null != setMasterMakerModel.getSetMasterMakerList() && setMasterMakerModel.getSetMasterMakerList().size() > 0) {
      for (SetMasterMaker setMasterMaker : setMasterMakerModel.getSetMasterMakerList()) {
        SetMasterDto setMasterDto = new SetMasterDto();

        // UUID
        setMasterDto.setUuid(setMasterMaker.getUuid());
        // 作成日時
        setMasterDto.setInsDtTime(setMasterMaker.getInsDtTime());
        // 更新日時
        setMasterDto.setUpdDtTime(setMasterMaker.getUpdDtTime());
        // 作成アカウントID
        setMasterDto.setInsAccountId(setMasterMaker.getInsAccountId());
        // 更新アカウントID
        setMasterDto.setUpdAccountId(setMasterMaker.getUpdAccountId());
        // 論理削除区分
        setMasterDto.setLogicalDelDiv(setMasterMaker.getLogicalDelDiv());
        
        // 優良設定詳細コード１not null primary key
        setMasterDto.setPrmSetDtlNo1(setMasterMaker.getPrmSetDtlNo1());
        // 部品メーカーコードnot null primary key
        setMasterDto.setPartsMakerCd(setMasterMaker.getPartsMakerCd());
        // 商品中分類コード
        setMasterDto.setGoodsMGroup(setMasterMaker.getGoodsMGroup());
        // BLコード
        setMasterDto.setTbsPartsCode(setMasterMaker.getTbsPartsCode());
        // セット親品番not null primary key
        setMasterDto.setSetMainPartsNo(setMasterMaker.getSetMainPartsNo());
        // セット表示順位not null primary key
        setMasterDto.setSetDispOrder(setMasterMaker.getSetDispOrder());
        // セット子品番
        setMasterDto.setSetSubPartsNo(setMasterMaker.getSetSubPartsNo());
        // 品名
        setMasterDto.setSetKanaName(setMasterMaker.getSetKanaName());
        // セット名称
        setMasterDto.setSetName(setMasterMaker.getSetName());
        // セットQTY
        setMasterDto.setSetQty(setMasterMaker.getSetQty());
        // セット規格・特記事項
        setMasterDto.setSetSpecialNote(setMasterMaker.getSetSpecialNote());
        // 優良部品規格・特記事項(C向け)
        setMasterDto.setPrimePartsSpecialNoteC(setMasterMaker.getPrimePartsSpecialNoteC());
        // 適用日付not null
        setMasterDto.setStartTime(setMasterMaker.getStartTime());
        // チェック区分not null
        setMasterDto.setCheckFlg(setMasterMaker.getCheckFlg());
        // データステータスnot null
        setMasterDto.setErrorFlg(setMasterMaker.getErrorFlg());
        // BL登録区分not null
        setMasterDto.setBlEntryFlg(setMasterMaker.getBlEntryFlg());
        // インポート区分not null
        setMasterDto.setImportKbn(setMasterMaker.getImportKbn());
        // 処理区分not null
        setMasterDto.setManageKbn(setMasterMaker.getManageKbn());
        // エラー内容
        setMasterDto.setErrorDetail(setMasterMaker.getErrorDetail());
        // 削除依頼区分
        setMasterDto.setDeleteFlg(setMasterMaker.getDeleteFlg());
        // 削除理由
        setMasterDto.setDeleteReason(setMasterMaker.getDeleteReason());
        // 申請状態
        setMasterDto.setApplyCondition(setMasterMaker.getApplyCondition());
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
   * @param joinMasterModel 結合マスタ(メーカー)Model
   * @return 結合マスタ(メーカー)Dto
   */
  private List<JoinMasterDto> convertJoinMasterInfoDto(JoinMasterModel joinMasterModel) {
    List<JoinMasterDto> joinMasterDtoList = new ArrayList<JoinMasterDto>();
    if (null != joinMasterModel.getJoinMasterList() && joinMasterModel.getJoinMasterList().size() > 0) {
      for (JoinMasterMaker joinMaster : joinMasterModel.getJoinMasterList()) {
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
        // 作成日時
        joinMasterDto.setInsDtTime(joinMaster.getInsDtTime());
        // 更新日時
        joinMasterDto.setUpdDtTime(joinMaster.getUpdDtTime());
        joinMasterDtoList.add(joinMasterDto);
      }
    }
    return joinMasterDtoList;
  }

}
