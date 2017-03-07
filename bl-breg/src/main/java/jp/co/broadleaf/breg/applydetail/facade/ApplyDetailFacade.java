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
package jp.co.broadleaf.breg.applydetail.facade;

import jp.co.broadleaf.breg.applydetail.facade.dto.GoodsMasterDto;
import jp.co.broadleaf.breg.applydetail.facade.dto.JoinMasterDto;
import jp.co.broadleaf.breg.applydetail.facade.dto.SetMasterDto;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * ApplyDetailFacade
 * </pre>
 */
public interface ApplyDetailFacade {
  /**
   * メーカー側の商品データの取得
   * 
   * @param makerCode メーカーコード
   * @param logical 論理削除区分
   * @param blApplyResultsFlg BL申請結果区分
   * @param updAccountId 更新アカウントID
   * @param importKbn インポート区分
   * @return 商品マスタdto
   */
  List<GoodsMasterDto> searchGoodsMaster(int makerCode, int logical, int blApplyResultsFlg, String updAccountId,
                                         int importKbn);

  /**
   * メーカー側の結合データの取得
   * 
   * @param makerCode メーカーコード
   * @param logical 論理削除区分
   * @param blApplyResultsFlg BL申請結果区分
   * @param updAccountId 更新アカウントID
   * @param importKbn インポート区分
   * @return 結合マスタdto
   */
  List<JoinMasterDto> searchJoinMaster(int makerCode, int logical, int blApplyResultsFlg, String updAccountId,
                                       int importKbn);

  /**
   * メーカー側のセットデータの取得
   * 
   * @param makerCode メーカーコード
   * @param logical 論理削除区分
   * @param blApplyResultsFlg BL申請結果区分
   * @param updAccountId 更新アカウントID
   * @param importKbn インポート区分
   * @return セットマスタdto
   */
  List<SetMasterDto> searchSetMaster(int makerCode, int logical, int blApplyResultsFlg, String updAccountId,
                                     int importKbn);

  /**
   * 共有側の商品データの取得
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return 商品マスターdto
   */
  List<GoodsMasterDto> searchGoodsMasterCommon(int makerCode, int logicDeleteDiv, int applyNo);

  /**
   * 共有側の結合データの取得
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return 結合マスタdto
   */
  List<JoinMasterDto> searchJoinMasterCommon(int makerCode, int logicDeleteDiv, int applyNo);

  /**
   * 共有側のセットデータの取得
   * 
   * @param makerCode メーカーコード
   * @param logicDeleteDiv 論理削除区分
   * @param applyNo 申請No
   * @return セットマスタdto
   */
  List<SetMasterDto> searchSetMasterCommon(int makerCode, int logicDeleteDiv, int applyNo);

  /**
   * 【申請詳細】ページ内行数設定の取得.
   * 
   * @param makerCode メーカーコード
   * @return 【申請詳細】ページ内行数
   */
  int searchApplyDetailRows(int makerCode);

  /**
   * <pre>
   * BLコード名称マップを取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @return BLコード名称マップ
   */
  Map<String, String> getBlCodeNameMap(int makerCode);

  /**
   * <pre>
   * * セレクトコードマスト名称マップを取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @return セレクトコードマスト名称マップ
   */
  Map<String, String> getSelectCodeNameMap(int makerCode);

  /**
   * <pre>
   * * 種別コード名称マップを取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @return 種別コード
   */
  Map<String, String> getKindCodeNameMap(int makerCode);

  /**
   * <pre>
   * * カーメーカーコード名称マップを取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @return カーメーカーコード
   */
  Map<String, String> getCarmakerNameMap(int makerCode);

  /**
   * <pre>
   * * 商品中分類コードマップを取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @return 商品中分類コード
   */
  Map<String, String> getClassifyCodeGuideMap(int makerCode);

  /**
   * <pre>
   * * 層別名称マップを取得.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @return 層別
   */
  Map<String, String> getPartsNameMap(int makerCode);
}
