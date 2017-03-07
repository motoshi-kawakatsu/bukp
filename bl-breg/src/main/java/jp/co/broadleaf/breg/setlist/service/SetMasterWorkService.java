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
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMakerId;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterSearchModel;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterWorkMakerModel;

import java.util.List;

/**
 * <pre>
 * セットマスタ(WORK)のinterface.
 * </pre>
 */
public interface SetMasterWorkService {
  /**
   * <pre>
   * セットマスタ(WORK)の取得.
   * </pre>
   * 
   * @param prmSetDtlNo1 primarykey
   * @param partsMakerCd primarykey
   * @param goodsMGroup primarykey
   * @param setMainPartsNo primarykey
   * @return entity list
   */
  List<SetMasterWorkMaker> getSetDetail(String prmSetDtlNo1, String partsMakerCd, String goodsMGroup,
                                        String setMainPartsNo);
  /**
   * <pre>
   * セットマスタ(work)delete.
   * </pre>
   * 
   * @param setMasterWorkMakerModel セットマスタ(メーカー)ID
   */
  void deleteSetMasterWorkMaker(SetMasterWorkMakerModel setMasterWorkMakerModel);
  
  /**
   * <pre>
   * セットマスタ(work)delete.
   * </pre>
   * 
   * @param setMasterWorkMakerModel セットマスタ(メーカー)ID
   */
  SetMasterWorkMaker searchById(SetMasterWorkMakerId setMasterId);
  
  /**
   * <pre>
   * セットマスタ(ワーク)情報更新.
   * </pre>
   * 
   * @param setMasterWork 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void updateSetMasterWorkMaker(SetMasterWorkMaker setMasterWork);
  /**
   * <pre>
   * セットマスタ(ワーク)の登録.
   * </pre>
   * 
   * @param setMasterWorkModel セットマスタ(ワーク)Model
   * @return 登録セットマスタ(ワーク)の件数
   */
  int insertSetMasterWorkMakerList(SetMasterWorkMakerModel setMasterWorkModel);
  /**
   * <pre>
   * セットマスタ(ワーク)の全データ取得.
   * </pre>
   * @param makerCd makerCd
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @return セットマスタ(ワーク)Model
   */
  SetMasterWorkMakerModel searchSetMasterWorkMakerList(int fileSelectMode, int makerCd, int mode);
  /**
   * <pre>
   * セットマスタ(ワーク)の取得.
   * </pre>
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @param setMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param skipRows スキップ
   * @param maxRows max
   * @return セットマスタ(ワーク)Model
   */
  SetMasterWorkMakerModel searchSetMasterWorkMakerList(SetMasterSearchModel setMasterSearchModel, Long skipRows,
                                                       Long maxRows,int fileSelectMode, int mode);
  /**
   * <pre>
   * セットマスタ(ワーク)の取得.
   * </pre>
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @param setMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @return セットマスタ(ワーク)Model
   */
  SetMasterWorkMakerModel searchSetMasterWorkMakerList(SetMasterSearchModel setMasterSearchModel,int fileSelectMode, int mode);

}
