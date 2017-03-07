//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : xuck
// 作 成 日       2017/02/14   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.setlistcommon.service;

import jp.co.broadleaf.breg.common.entity.SetMasterCommon;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;

import java.util.List;

/**
 * <pre>
 *セットマスタ(common)のサービスインタフェース.
 * </pre>
 */
public interface SetMasterCommonService {
  /**
   * <pre>
   * セットマスタ(common)の検索.
   * </pre>
   * 
   * @param setMasterMaker 検索する項目（優良設定詳細コード１ など）の連係情報
   * @return 結合マスタ(common)
   */
  List<SetMasterCommon> searchSetMasterCommonList(SetMasterMaker setMasterMaker);

  
  /**
   * <pre>
   * セットマスタ(common)の登録.
   * </pre>
   * 
   * @param setMasterList セットマスタ(common)Model
   * @return 登録セットマスタ(common)の件数
   */
  int insertSetMasterCommonList(List<SetMasterCommon> setMasterList);
  
  /**
   * <pre>
   * セットマスタ(common)情報更新.
   * </pre>
   * 
   * @param setMaster 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void updateSetMasterCommon(SetMasterCommon setMaster);
  
  /**
   * <pre>
   * セットマスタ(common)delete.
   * </pre>
   * 
   * @param setMasterMakerModel セットマスタ(メーカー)ID
   */
  void deleteSet(SetMasterCommon setMaster);

}
