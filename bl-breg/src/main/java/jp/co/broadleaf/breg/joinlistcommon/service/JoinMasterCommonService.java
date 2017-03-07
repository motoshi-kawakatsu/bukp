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
package jp.co.broadleaf.breg.joinlistcommon.service;

import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;

import java.util.List;

/**
 * <pre>
 * 結合マスタ(common)のサービスインタフェース.
 * </pre>
 */
public interface JoinMasterCommonService {

  /**
   * <pre>
   * 結合マスタ(common)の検索.
   * </pre>
   * 
   * @param joinMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @return 結合マスタ(common)
   */
  List<JoinMasterCommon> searchJoinMasterCommonList(JoinMasterMaker joinMasterSearchModel);

  /**
   * <pre>
   * 結合マスタ(common)の登録.
   * </pre>
   * 
   * @param joinMasterList 結合マスタ(common)Model
   * @return 登録結合マスタ(common)の件数
   */
  int insertJoinMasterInfoList(List<JoinMasterCommon> joinMasterList);

  /**
   * <pre>
   * 結合マスタ(common)情報更新.
   * </pre>
   * 
   * @param joinMaster 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void updateJoinMaster(JoinMasterCommon joinMaster);

  /**
   * <pre>
   * 結合マスタ(common)削除.
   * </pre>
   * 
   * @param joinMaster 結合マスタ(common)
   */
  void deleteJoin(JoinMasterCommon joinMaster);

  /**
   * <pre>
   * 結合マスタ(common)検索.
   * </pre>
   * 
   * @param joinMasterCommon 結合マスタ(common)
   * @return joinMasterCommonList
   */
  List<JoinMasterCommon> findJoin(JoinMasterCommon joinMasterCommon);

}
