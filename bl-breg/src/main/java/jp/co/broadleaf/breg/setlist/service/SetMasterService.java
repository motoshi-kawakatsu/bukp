//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : chenlong
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.setlist.service;

import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterMakerModel;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterSearchModel;

import java.util.List;

/**
 * <pre>
 *セットマスタ(メーカー)のサービスインタフェース.
 * </pre>
 */
public interface SetMasterService {

  /**
   * <pre>
   * セットマスタ(メーカー)の取得.
   * </pre>
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @param setMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param skipRows スキップ
   * @param maxRows max
   * @return セットマスタ(メーカー)Model
   */
  SetMasterMakerModel searchSetMasterMakerList(SetMasterSearchModel setMasterSearchModel,Long skipRows,
                                               Long maxRows,int fileSelectMode, int mode);

  /**
   * <pre>
   * セットマスタ(メーカー)の取得.
   * </pre>
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @param setMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @return セットマスタ(メーカー)Model
   */
  SetMasterMakerModel searchSetMasterMakerList(SetMasterSearchModel setMasterSearchModel,int fileSelectMode, int mode);

  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @return セットマスタ(メーカー)
   */
  List<SetMasterMaker>  findAll();
  
  /**
   * <pre>
   * チェックリスト画面のセットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param count 現在位置
   * @param maxRows maxRows
   * @param makerCode makerCode
   * @return セットマスタ(メーカー)Model
   */
  SetMasterMakerModel searchSetErrorList(Long count, Long maxRows, int makerCode);

  /**
   * <pre>
   * セットマスタ(メーカー)の全データ取得.
   * </pre>
   * @param makerCd makerCd
   * @param fileSelectMode   0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @return セットマスタ(メーカー)Model
   */
  SetMasterMakerModel searchSetMasterMakerList(int fileSelectMode,int makerCd,int mode);

  

  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param importKbn インポート区分(メーカー)
   * @param applyCondition 申請状態(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @return セットマスタ(メーカー)情報
   */
  List<SetMasterMaker> searchSetInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                         Integer partsMakerCd, Integer logicalDeleteCode);

  /**
   * <pre>
   * セットマスタ(メーカー)の登録.
   * </pre>
   * 
   * @param setMasterModel セットマスタ(メーカー)Model
   * @return 登録セットマスタ(メーカー)の件数
   */
  int insertSetMasterMakerList(SetMasterMakerModel setMasterModel);

  

  /**
   * <pre>
   * セットマスタ(メーカー)情報更新.
   * </pre>
   * 
   * @param setMaster 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void updateSetMasterMaker(SetMasterMaker setMaster);
  
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param setMasterMaker セットマスタ(メーカー)ID
   * @return セットマスタ(メーカー)情報
   */
  SetMasterMaker searchBySetMasterId(SetMasterMaker setMasterMaker);
  
  /**
   * <pre>
   * セットマスタ(メーカー)delete.
   * </pre>
   * 
   * @param setMasterMakerModel セットマスタ(メーカー)ID
   */
  void deleteSetMasterMaker(SetMasterMakerModel setMasterMakerModel);
  
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param setMasterId  セットマスタ(メーカー)ID
   * @return  セットマスタ(メーカー)情報
   */
  SetMasterMaker searchBySetMasterId(SetMasterMakerId setMasterId);
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param setMasterMakerId セットマスタ(メーカー)ID
   * @return セットマスタ(メーカー)情報
   */
  SetMasterMaker searchSetById(SetMasterMakerId setMasterMakerId);

  /**
   * @param setMasterId
   * @return
   */
  SetMasterMaker searchById(SetMasterMakerId setMasterId);
  
}
