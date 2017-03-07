package jp.co.broadleaf.breg.joinlist.service;

import jp.co.broadleaf.breg.common.entity.JoinMasterWorkMaker;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterSearchModel;
import jp.co.broadleaf.breg.joinlist.model.JoinMasterWorkModel;

import java.util.List;

/**
 * <pre>
 * 結合マスタ(メーカー)ワークのサービスインタフェース.
 * </pre>
 */
public interface JoinMasterWorkService {

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの取得.
   * </pre>
   * 
   * @param joinMasterSearchModel 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param order 表示順
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param importKbn インポート区分
   * @return 結合マスタ(メーカー)ワークModel
   */
  JoinMasterWorkModel searchJoinMasterWorkInfoList(JoinMasterSearchModel joinMasterSearchModel, int order,
                                                   Long skipRows, Long maxRows, Boolean isPage, Integer importKbn);

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの全データ取得.
   * </pre>
   * 
   * @return 結合マスタ(メーカー)ワークModel
   */
  JoinMasterWorkModel searchJoinMasterWorkList();

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークのデータ取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param importKbn インポート区分
   * @return 結合マスタ(メーカー)ワークModel
   */
  JoinMasterWorkModel searchJoinMasterWorkAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd,
                                              Integer importKbn);

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの登録.
   * </pre>
   * 
   * @param joinMasterWorkModel 結合マスタ(メーカー)ワークModel
   * @return 登録結合マスタ(メーカー)ワークの件数
   */
  int insertJoinMasterWorkInfoList(JoinMasterWorkModel joinMasterWorkModel);

  /**
   * <pre>
   * 結合マスタ(メーカー)ワーク情報更新.
   * </pre>
   * 
   * @param joinMasterWork 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void updateJoinMasterWork(JoinMasterWorkMaker joinMasterWork);

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの取得.
   * </pre>
   * 
   * @param prmSetDtlNo1 優良設定詳細コード１
   * @param partsMakerCd 部品メーカーコード
   * @param tbsPartsCode BLコード
   * @param joinSourceMakerCode 結合元メーカーコード
   * @param prmSetDtlNo2 優良設定詳細コード２
   * @param joinSourPartsNoWithH 結合元品番(－付き品番)
   * @return 結合マスタ(メーカー)ワークの情報
   */
  List<JoinMasterWorkMaker> getJoinDetail(String prmSetDtlNo1, String partsMakerCd, String tbsPartsCode,
                                          String joinSourceMakerCode, String prmSetDtlNo2, String joinSourPartsNoWithH);

  /**
   * <pre>
   * 結合マスタ(メーカー)ワーク削除.
   * </pre>
   * 
   * @param joinMasterWorkMaker 結合マスタ(メーカー)ワーク
   */
  void deleteJoinMasterWork(JoinMasterWorkMaker joinMasterWorkMaker);

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの全データを削除する.
   * </pre>
   */
  void deleteAll();
}
