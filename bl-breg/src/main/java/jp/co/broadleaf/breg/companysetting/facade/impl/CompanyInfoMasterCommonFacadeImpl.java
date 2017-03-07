//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 湯凱
// 作 成 日       2017/02/06   修正内容 : 会社情報：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.companysetting.facade.impl;

import jp.co.broadleaf.breg.common.dto.BlCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.KindCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.PartsMasterDto;
import jp.co.broadleaf.breg.common.dto.SelectCodeMasterDto;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;
import jp.co.broadleaf.breg.common.entity.KindCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.PartsMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;
import jp.co.broadleaf.breg.common.service.BlCodeMasterCommonService;
import jp.co.broadleaf.breg.common.service.KindCodeMasterCommonService;
import jp.co.broadleaf.breg.common.service.PartsMasterCommonService;
import jp.co.broadleaf.breg.common.service.SelectCodeMasterCommonService;
import jp.co.broadleaf.breg.companysetting.dto.CompanySettingDto;
import jp.co.broadleaf.breg.companysetting.facade.CompanyInfoMasterCommonFacade;
import jp.co.broadleaf.breg.companysetting.service.CompanyInfoMasterCommonService;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * <pre>
 * エンプロイーFacadeクラス
 * </pre>
 */
public class CompanyInfoMasterCommonFacadeImpl implements CompanyInfoMasterCommonFacade {

  /** 会社情報エンプロイーサービス */
  private CompanyInfoMasterCommonService companyInfoservice;

  /**
   * 会社情報エンプロイーサービスをセットする
   * 
   * @param employeeService CompanyInfoMasterCommonService
   */
  @Resource
  public void setCompanyInfoMasterCommonService(CompanyInfoMasterCommonService employeeService) {
    this.companyInfoservice = employeeService;
  }

  /** BLコードマストエンプロイーサービス */
  private BlCodeMasterCommonService blCodeMasterCommonService;

  /**
   * BLコードマストエンプロイーサービスをセットする
   * 
   * @param blCodeMasterCommonService BlCodeMasterCommonService
   */
  @Resource
  public void setBlCodeMasterCommonService(BlCodeMasterCommonService blCodeMasterCommonService) {
    this.blCodeMasterCommonService = blCodeMasterCommonService;
  }

  /** 種別コードエンプロイーサービス */
  private KindCodeMasterCommonService kindCodeMasterCommonService;

  /**
   * 種別コードエンプロイーサービスをセットする
   * 
   * @param kindCodeMasterCommonService KindCodeMasterCommonService
   */
  @Resource
  public void setKindCodeMasterCommonService(KindCodeMasterCommonService kindCodeMasterCommonService) {
    this.kindCodeMasterCommonService = kindCodeMasterCommonService;
  }

  /** 層別マストエンプロイーサービス */
  private PartsMasterCommonService partsMasterCommonService;

  /**
   * 層別マストエンプロイーサービスをセットする
   * 
   * @param partsMasterCommonService PartsMasterCommonService
   */
  @Resource
  public void setPartsMasterCommonService(PartsMasterCommonService partsMasterCommonService) {
    this.partsMasterCommonService = partsMasterCommonService;
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

  /**
   * <pre>
   * 会社情報を取得
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 会社情報
   */
  @Override
  public CompanySettingDto getCompanyInfo(int makerCd) {
    CompanySettingDto dto = null;
    CompanyInfoMasterCommon companyInfo = companyInfoservice.getCompanyInfo(makerCd);
    if (companyInfo != null) {
      dto = new CompanySettingDto();
      // 作成アカウントID
      dto.setInsAccountId(companyInfo.getInsAccountId());
      // 作成日時
      dto.setInsDtTime(companyInfo.getInsDtTime());
      // 更新日時
      dto.setUpdDtTime(companyInfo.getUpdDtTime());
      // メーカーコード
      dto.setMakerCd(companyInfo.getMakerCode());
      // メーカー名称
      dto.setMakerCdName(companyInfo.getMakerName());
      // メーカー名称（半角）
      dto.setMakerCdNameShort(companyInfo.getMakerKana());
      // 会社名称
      dto.setCompanyName(companyInfo.getCompanyName());
      // 会社名称(カナ)
      dto.setCompanyNameKana(companyInfo.getCompanyKana());
      // 郵便番号
      dto.setMailNo(companyInfo.getPostCode());
      // 住所
      dto.setAddress(companyInfo.getAddress());
      // TEL
      dto.setTel(companyInfo.getTelNo());
      // FAX
      dto.setFax(companyInfo.getFaxNo());
      // 備考
      dto.setNotes(companyInfo.getNote());
      // 商品ページ内行数
      dto.setGoodsRows(companyInfo.getGoodsRows());
      // セットページ内行数
      dto.setSetRows(companyInfo.getSetRows());
      // 結合ページ内行数
      dto.setJoinRows(companyInfo.getJoinRows());
      // 申請詳細ページ内行数
      dto.setApplyDetailRows(companyInfo.getApplyDetailRows());
      // 申請履歴ページ内行数
      dto.setApplyRecordRows(companyInfo.getApplyHistoryRows());
      // 商品インポート方法
      dto.setGoodsImportType(companyInfo.getGoodsImportType());
      // セットインポート方法
      dto.setSetImportType(companyInfo.getSetImportType());
      // 結合インポート方法
      dto.setJoinImportType(companyInfo.getJoinImportType());
    }
    return dto;
  }

  /**
   * <pre>
   * 会社情報を更新
   * </pre>
   * 
   * @param updateDto CompanySettingDto
   * @return 0:更新成功 1:更新失敗
   */
  @Override
  public int updateCompanyInfo(CompanySettingDto updateDto) {
    if (updateDto != null) {
      CompanyInfoMasterCommon companyInfoUpdate = new CompanyInfoMasterCommon();
      // 更新ID
      companyInfoUpdate.setUpdAccountId(updateDto.getUpdAccountId());
      // 更新日時
      companyInfoUpdate.setUpdDtTime(updateDto.getUpdDtTime());
      // メーカーコード
      companyInfoUpdate.setMakerCode(updateDto.getMakerCd());
      // 会社名称
      companyInfoUpdate.setCompanyName(updateDto.getCompanyName());
      // メーカー名称
      companyInfoUpdate.setMakerName(updateDto.getMakerCdName());
      // メーカー名称（半角）
      companyInfoUpdate.setMakerKana(updateDto.getMakerCdNameShort());
      // 会社名称(カナ)
      companyInfoUpdate.setCompanyKana(updateDto.getCompanyNameKana());
      // TEL
      companyInfoUpdate.setTelNo(updateDto.getTel());
      // 郵便番号
      companyInfoUpdate.setPostCode(updateDto.getMailNo());
      // 住所
      companyInfoUpdate.setAddress(updateDto.getAddress());
      // FAX
      companyInfoUpdate.setFaxNo(updateDto.getFax());
      // 備考
      companyInfoUpdate.setNote(updateDto.getNotes());
      // 商品ページ内行数
      companyInfoUpdate.setGoodsRows(updateDto.getGoodsRows());
      // セットページ内行数
      companyInfoUpdate.setSetRows(updateDto.getSetRows());
      // 結合ページ内行数
      companyInfoUpdate.setJoinRows(updateDto.getJoinRows());
      // 申請履歴ページ内行数
      companyInfoUpdate.setApplyHistoryRows(updateDto.getApplyRecordRows());
      // 申請詳細ページ内行数
      companyInfoUpdate.setApplyDetailRows(updateDto.getApplyDetailRows());
      // 商品インポート方法
      companyInfoUpdate.setGoodsImportType(updateDto.getGoodsImportType());
      // セットインポート方法
      companyInfoUpdate.setSetImportType(updateDto.getSetImportType());
      // 結合インポート方法
      companyInfoUpdate.setJoinImportType(updateDto.getJoinImportType());
      return companyInfoservice.updateCompanyInfo(companyInfoUpdate);
    }
    return 1;
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
    List<SelectCodeMasterCommon> selectCodeInfo = selectCodeMasterCommonService.getSelectCodeInfo(makerCd);
    if (selectCodeInfo != null && !selectCodeInfo.isEmpty()) {
      selectCodeDto = new ArrayList<>();
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
   * BLコードマスタ情報を取得
   * 
   * @param makerCd メーカーコード
   * @return BLコードマスタ情報
   */
  @Override
  public List<BlCodeMasterDto> getBlCodeInfo(int makerCd) {
    List<BlCodeMasterDto> blCodeDto = null;
    List<BlCodeMasterCommon> blCodeMasterInfo = blCodeMasterCommonService.getBlCodeMasterInfo(makerCd);
    if (blCodeMasterInfo != null && !blCodeMasterInfo.isEmpty()) {
      blCodeDto = new ArrayList<>();
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
   * 種別コードマスト情報を取得
   * 
   * @param makerCd メーカーコード
   * @return 種別コードマスト情報
   */
  @Override
  public List<KindCodeMasterDto> getKindCodeInfo(int makerCd) {
    List<KindCodeMasterDto> kindCodeDto = null;
    List<KindCodeMasterCommon> kindCodeInfo = kindCodeMasterCommonService.getKindCodeInfo(makerCd);
    if (kindCodeInfo != null && !kindCodeInfo.isEmpty()) {
      kindCodeDto = new ArrayList<>();
      for (KindCodeMasterCommon kindCode : kindCodeInfo) {
        KindCodeMasterDto dto = new KindCodeMasterDto();
        dto.setGoodsMaker(kindCode.getGoodsMaker());
        dto.setKindCd(kindCode.getKindCd());
        dto.setKindName(kindCode.getKindName());
        dto.setOfferDate(kindCode.getOfferDate());
        kindCodeDto.add(dto);
      }
    }
    return kindCodeDto;
  }

  /**
   * 層別マスト情報を取得
   * 
   * @param makerCd メーカーコード
   * @return 層別マスト情報
   */
  @Override
  public List<PartsMasterDto> getPartsMasterInfo(int makerCd) {
    List<PartsMasterDto> partsMasterDto = null;
    List<PartsMasterCommon> partsMasterInfo = partsMasterCommonService.getPartsMasterInfo(makerCd);
    if (partsMasterInfo != null && !partsMasterInfo.isEmpty()) {
      partsMasterDto = new ArrayList<>();
      for (PartsMasterCommon parts : partsMasterInfo) {
        PartsMasterDto dto = new PartsMasterDto();
        dto.setGoodsMaker(parts.getGoodsMaker());
        dto.setOfferDate(parts.getOfferDate());
        dto.setPartsLayer(parts.getPartsLayer());
        dto.setPartsLayerName(parts.getPartsLayerName());
        partsMasterDto.add(dto);
      }
    }
    return partsMasterDto;
  }

}
