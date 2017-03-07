//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号     　　　　　　　　　　    作成担当 : 王　天コン
// 作 成 日       2017/02/13   修正内容 : 結合一覧:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.joinlist.service;

import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterMakerId;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterModel;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterSearchModel;

import java.util.List;

/**
 * <pre>
 * 結合マスタ(メーカー)のサービスインタフェース.
 * </pre>
 */
public interface JoinMasterService {

  /**
   * <pre>
   * チェックリスト画面の結合マスタ(メーカー)取得.
   * </pre>
   * 
   * @param count 現在位置
   * @param maxRows maxRows
   * @param makerCode makerCode
   * @return 結合マスタ(メーカー)Model
   */
  JoinMasterModel searchJoinErrorList(Long count, Long maxRows, int makerCode);

  /**
   * <pre>
   * 結合マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param joinMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param order 表示順
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param searchMode searchMode
   * @return 結合マスタ(メーカー)Model
   */
  JoinMasterModel searchJoinMasterInfoList(JoinMasterSearchModel joinMasterSearchModel, int order, Long skipRows,
                                           Long maxRows, Boolean isPage, int searchMode);

  /**
   * <pre>
   * 結合マスタ(メーカー)取得.
   * </pre>
   * 
   * @param importKbn インポート区分(メーカー)
   * @param applyCondition 申請状態(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @return 結合マスタ(メーカー)情報
   */
  List<JoinMasterMaker> searchJoinInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                           Integer partsMakerCd, Integer logicalDeleteCode);

  /**
   * <pre>
   * 結合マスタ(メーカー)の登録.
   * </pre>
   * 
   * @param joinMasterModel 結合マスタ(メーカー)Model
   * @return 登録結合マスタ(メーカー)の件数
   */
  int insertJoinMasterInfoList(JoinMasterModel joinMasterModel);

  /**
   * <pre>
   * 結合マスタ(メーカー)の全データ取得.
   * </pre>
   * 
   * @return 結合マスタ(メーカー)Model
   */
  JoinMasterModel searchJoinMasterInfoList();

  /**
   * <pre>
   * 結合マスタ(メーカー)のデータ取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param searchMode searchMode
   * @return 結合マスタ(メーカー)Model
   */
  JoinMasterModel searchJoinMasterInfoDtoAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd, int searchMode);

  /**
   * 該当キーの結合マスタ（メーカー）本情報を取得
   * 
   * @param selectorCd セレクトコード
   * @param partsMakerCd 部品メーカーコード
   * @param blCode BLコード
   * @param joinMakerCd カーメーカーコード
   * @param kindCode 種別コード
   * @param joinPartsNo 純正品番
   * @return 結合マスタ(メーカー)Model
   */
  JoinMasterModel getJoinMasterMakerInfo(String selectorCd, String partsMakerCd, String blCode, String joinMakerCd,
                                         String kindCode, String joinPartsNo);

  /**
   * <pre>
   * 結合マスタ(メーカー)情報更新.
   * </pre>
   * 
   * @param joinMaster 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void updateJoinMaster(JoinMasterMaker joinMaster);

  /**
   * <pre>
   * 結合マスタ(メーカー)情報削除.
   * </pre>
   * 
   * @param joinMaster 削除する項目（優良設定詳細コード１ など）の連係情報
   */
  void deleteJoinMaster(JoinMasterMaker joinMaster);

  /**
   * <pre>
   * 結合マスタ(メーカー)取得.
   * </pre>
   * 
   * @param joinMasterId 結合マスタ(メーカー)ID
   * @return 結合マスタ(メーカー)情報
   */
  JoinMasterMaker searchByJoinMasterId(JoinMasterMakerId joinMasterId);

  /**
   * <pre>
   * 結合マスタ(メーカー)取得.
   * </pre>
   * 
   * @param joinMasterId 結合マスタ(メーカー)ID
   * @param joinDestPartsNo 結合先品番(－付き品番)
   * @return 結合マスタ(メーカー)情報
   */
  JoinMasterMaker searchJoinMaster(JoinMasterMakerId joinMasterId, String joinDestPartsNo);

}
