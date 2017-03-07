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
package jp.co.broadleaf.breg.applycommon.service;

import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.CompanyInfoMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.GoodsRateMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterCommon;
import jp.co.broadleaf.breg.common.entity.JoinMasterMaker;
import jp.co.broadleaf.breg.common.entity.PuregoodsMasterCommon;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;

import java.util.List;

/**
 * <pre>
 * </pre>
 */
public interface ApplyCommonService {

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param importKbn インポート区分(メーカー)
   * @param applyCondition 申請状態(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @param isFromImport 論理boolean区分
   * @param isReApplication 論理boolean区分
   * @return 商品マスタ(メーカー)情報
   */
  List<GoodsMasterMaker> searchGoodsInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                             Integer partsMakerCd, Integer logicalDeleteCode, boolean isFromImport,
                                             boolean isReApplication);

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
   * @param isFromImport 論理boolean区分
   * @param isReApplication 論理boolean区分
   * @return セットマスタ(メーカー)情報
   */
  List<SetMasterMaker> searchSetInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                         Integer partsMakerCd, Integer logicalDeleteCode, boolean isFromImport,
                                         boolean isReApplication);

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
   * @param isFromImport boolean
   * @param isReApplication 論理boolean区分
   * @return 結合マスタ(メーカー)情報
   */
  List<JoinMasterMaker> searchJoinInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                           Integer partsMakerCd, Integer logicalDeleteCode, boolean isFromImport,
                                           boolean isReApplication);

  /**
   * <pre>
   * searchByExample
   * </pre>
   *
   * @param joinMaster JoinMasterCommon
   * @return List
   */
  List<JoinMasterCommon> searchByExample(JoinMasterCommon joinMaster);

  /**
   * <pre>
   * MailAddressを取得する.
   * </pre>
   * 
   * @param makerCode makerCode
   * @return blMailAddress
   */
  CompanyInfoMasterCommon getBlMailAdd(int makerCode);

  /**
   * <pre>
   * 商品マスタ(メーカー)取得.
   * </pre>
   * 
   * @param logicalDelDiv int
   * @param prmSetDtlNo1 int
   * @param partsMakerCd int
   * @param primePartsNoWithH String
   * @return 商品マスタ(メーカー)情報
   */
  List<GoodsMasterMaker> searchGoods(int logicalDelDiv, int prmSetDtlNo1, int partsMakerCd, String primePartsNoWithH);

  /**
   * <pre>
   * 純正品番取得.
   * </pre>
   * 
   * @param prmSetDtlNo1 int
   * @param partsMakerCd int
   * @param joinSourceMakerCode int
   * @param joinSourPartsNoWithH String
   * @return 純正品番情報
   */
  List<PuregoodsMasterCommon> searchPureGoods(int prmSetDtlNo1, int partsMakerCd, int joinSourceMakerCode,
                                              String joinSourPartsNoWithH);

  /**
   * <pre>
   * 純正品番取得.
   * </pre>
   * 
   * @param prmSetDtlNo1 int
   * @param partsMakerCd int
   * @param joinSourceMakerCode int
   * @return 純正品番情報
   */
  List<PuregoodsMasterCommon> searchPureGoodsByPartsMakerCd(int prmSetDtlNo1, int partsMakerCd,
                                                            int joinSourceMakerCode);

  /**
   * <pre>
   * BLコードマスタ取得.
   * </pre>
   * 
   * @param joinSourceMakerCode int
   * @param tbsPartsCode int
   * @return BLコードマスタ情報
   */
  BlCodeMasterCommon searchBlCodeMasterCommonById(int joinSourceMakerCode, int tbsPartsCode);

  /**
   * <pre>
   * セレクトコードマスト取得.
   * </pre>
   * 
   * @param partsMakerCd int
   * @return セレクトコードマスト情報
   */
  List<SelectCodeMasterCommon> searchSelectCodeMaster(int partsMakerCd);
  
  /**
   * <pre>
   * BLコードマスタ取得.
   * </pre>
   * 
   * @param partsMakerCd int
   * @return BLコードマスタ情報
   */
  List<BlCodeMasterCommon> searchBlCodeMaster(int partsMakerCd);

  /**
   * <pre>
   * 商品中分類マスタ取得.
   * </pre>
   * 
   * @param partsMakerCd int
   * @return 商品中分類マスタ情報
   */
  List<GoodsRateMasterCommon> searchGoodsRateMaster(int partsMakerCd);

  /**
   * <pre>
   * 結合マスタ取得.
   * </pre>
   * 
   * @param prmSetDtlNo1 int
   * @param partsMakerCd int
   * @param tbsPartsCode int
   * @param joinSourceMakerCode int
   * @param joinSourPartsNoWithH String
   * @return 結合マスタ情報
   */
  List<JoinMasterMaker> searchJoinByKeys(int prmSetDtlNo1, int partsMakerCd, int tbsPartsCode, int joinSourceMakerCode,
                                         String joinSourPartsNoWithH);

}
