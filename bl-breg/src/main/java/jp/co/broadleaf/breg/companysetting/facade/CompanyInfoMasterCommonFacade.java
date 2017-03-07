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
package jp.co.broadleaf.breg.companysetting.facade;

import jp.co.broadleaf.breg.common.dto.BlCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.KindCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.PartsMasterDto;
import jp.co.broadleaf.breg.common.dto.SelectCodeMasterDto;
import jp.co.broadleaf.breg.companysetting.dto.CompanySettingDto;

import java.util.List;

/**
 * <pre>
 * エンプロイーFacadeインタフェース。
 * </pre>
 */
public interface CompanyInfoMasterCommonFacade {

  /**
   * 会社情報を取得
   * 
   * @param makerCd メーカーコード
   * @return 会社情報
   */
  CompanySettingDto getCompanyInfo(int makerCd);

  /**
   * 会社情報を更新
   * 
   * @param updateDto CompanySettingDto
   * @return 0:更新成功 1:更新失敗
   */
  int updateCompanyInfo(CompanySettingDto updateDto);
  
  /**
   * セレクトコードマスト情報を取得
   * 
   * @param makerCd メーカーコード
   * @return セレクトコードマスト情報
   */
  List<SelectCodeMasterDto> getSelectCodeInfo(int makerCd);
  
  /**
   * BLコードマスタ情報を取得
   * 
   * @param makerCd メーカーコード
   * @return BLコードマスタ情報
   */
  List<BlCodeMasterDto> getBlCodeInfo(int makerCd);
  
  /**
   * 種別コードマスト情報を取得
   * 
   * @param makerCd メーカーコード
   * @return 種別コードマスト情報
   */
  List<KindCodeMasterDto> getKindCodeInfo(int makerCd);
  
  /**
   * 層別マスト情報を取得
   * 
   * @param makerCd メーカーコード
   * @return 層別マスト情報
   */
  List<PartsMasterDto> getPartsMasterInfo(int makerCd);
  
}
