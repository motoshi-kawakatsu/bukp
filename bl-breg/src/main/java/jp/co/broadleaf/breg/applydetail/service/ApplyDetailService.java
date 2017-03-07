//****************************************************************************//
// システム                                    : カーパーツマネージャー
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                            作成担当 : 秦志超
// 作 成 日       2017/02/06   修正内容 : 申請詳細:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.applydetail.service;

import jp.co.broadleaf.breg.common.entity.ApplyHeadMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterCommon;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;

import java.util.List;

/**
 * <pre>
 * ApplyDetailService
 * </pre>
 */
public interface ApplyDetailService {

  /**
   * データベースから、メーカー側の商品データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logical 論理削除区分
   * @param blApplyResultsFlg BL申請結果区分
   * @param updAccountId 更新アカウントID
   * @param importKbn インポート区分
   * @return 商品マスタ
   */
  List<GoodsMasterMaker> searchGoods(int makerCode, int logical, int blApplyResultsFlg, String updAccountId,
                                     int importKbn);

  /**
   * データベースから、メーカー側の結合データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logical 論理削除区分
   * @param blApplyResultsFlg BL申請結果区分
   * @param updAccountId 更新アカウントID
   * @param importKbn インポート区分
   * @return 結合マスタ
   */
  List<JoinMasterMaker> searchJoin(int makerCode, int logical, int blApplyResultsFlg, String updAccountId,
                                   int importKbn);

  /**
   * データベースから、メーカー側のセットデータを検索する
   * 
   * @param makerCode メーカーコード
   * @param logical 論理削除区分
   * @param blApplyResultsFlg BL申請結果区分
   * @param updAccountId 更新アカウントID
   * @param importKbn インポート区分
   * @return セットマスタ
   */
  List<SetMasterMaker> searchSet(int makerCode, int logical, int blApplyResultsFlg, String updAccountId, int importKbn);

  /**
   * データベースから、共有側の商品データを検索する
   * 
   * @param makerCode メーカーコード
   * @return 商品マスタ履歴
   */
  List<GoodsMasterCommon> searchGoodsCommon(int makerCode);

  /**
   * データベースから、共有側の結合データを検索する
   * 
   * @param makerCode メーカーコード
   * @return 結合マスタ履歴
   */
  List<JoinMasterCommon> searchJoinCommon(int makerCode);

  /**
   * データベースから、共有側のセットデータを検索する
   * 
   * @param makerCode メーカーコード
   * @return セットマスタ履歴
   */
  List<SetMasterCommon> searchSetCommon(int makerCode);

  /**
   * データベースから、共有側の商品データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return 商品マスタ履歴
   */
  List<GoodsMasterCommon> searchGoodsCommonShow(int makerCode, int logicDeleteDiv, int applyNo);

  /**
   * データベースから、共有側の結合データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return 結合マスタ履歴
   */
  List<JoinMasterCommon> searchJoinCommonShow(int makerCode, int logicDeleteDiv, int applyNo);

  /**
   * データベースから、共有側のセットデータを検索する
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return セットマスタ履歴
   */
  List<SetMasterCommon> searchSetCommonShow(int makerCode, int logicDeleteDiv, int applyNo);

  /**
   * データベースから、共有側の商品履歴データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return 商品マスタ
   */
  List<GoodsMasterCommon> searchGoodsCommonJudge(int makerCode, int logicDeleteDiv, int applyNo);

  /**
   * データベースから、共有側の結合履歴データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return 結合マスタ
   */
  List<JoinMasterCommon> searchJoinCommonJudge(int makerCode, int logicDeleteDiv, int applyNo);

  /**
   * データベースから、共有側のセット履歴データを検索する
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return セットマスタ
   */
  List<SetMasterCommon> searchSetCommonJudge(int makerCode, int logicDeleteDiv, int applyNo);

  /**
   * 申請状態の取得
   * 
   * @param makerCode メーカーコード
   * @param applyNo 申請No
   * @return 申請状態
   */
  List<ApplyHeadMasterCommon> searchApplyResult(int makerCode, int applyNo);
  
}
