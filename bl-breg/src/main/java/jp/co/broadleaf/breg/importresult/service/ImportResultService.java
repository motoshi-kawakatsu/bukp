//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                               作成担当 : magy
// 作 成 日       2017/02/07   修正内容 : インポート結果:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.importresult.service;

import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.common.enums.ImportTypeEnum;
import jp.co.broadleaf.breg.importresult.model.ImportResultModel;

/**
 * インポート結果サビース
 */
public interface ImportResultService {

  /**
   * インポート結果取得します.
   * 
   * @param importType インポート類型
   * @param makerCd メーカーコード
   * @param accountCd アカウントID
   * @param importKbnEnum インポート類型
   * @return インポート結果のModel
   */
  ImportResultModel searchAll(int importType, int makerCd, String accountCd, ImportKbnEnum importKbnEnum);

  /**
   * インポート結果のチェック.
   * 
   * @param importResultModel インポート結果のModel
   * @return インポート結果のModel
   */
  ImportResultModel checkImportList(ImportResultModel importResultModel);

  /**
   * 本DB更新登録.
   * 
   * @param makerCd メーカーコード
   * @param accountCd アカウントID
   * @param goodsType 商品インポート類型
   * @param setType セットインポート類型
   * @param joinType 結合インポート類型
   * @param importResultModel インポート結果のModel
   * @param importKbnEnum インポート類型
   * @return インポート結果のModel
   */
  ImportResultModel updateHonDb(int makerCd, String accountCd, ImportTypeEnum goodsType, ImportTypeEnum setType,
                                ImportTypeEnum joinType, ImportResultModel importResultModel,
                                ImportKbnEnum importKbnEnum);

}
