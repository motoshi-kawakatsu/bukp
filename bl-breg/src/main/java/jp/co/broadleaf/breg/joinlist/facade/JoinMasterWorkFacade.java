package jp.co.broadleaf.breg.joinlist.facade;

import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterInfoDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterSearchDto;

/**
 * <pre>
 * </pre>
 */
public interface JoinMasterWorkFacade {

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの取得.
   * </pre>
   * 
   * @param joinMasterSearchDto 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param order 表示順
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param importKbn インポート区分
   * @return 結合マスタ(メーカー)ワークDto
   */
  JoinMasterInfoDto searchJoinMasterWorkInfoList(JoinMasterSearchDto joinMasterSearchDto, int order, Long skipRows,
                                                 Long maxRows, Boolean isPage, Integer importKbn);

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの全データ取得.
   * </pre>
   * 
   * @return 結合マスタ(メーカー)ワークDto
   */
  JoinMasterInfoDto searchJoinMasterWorkInfoList();

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param importKbn インポート区分
   * @return 結合マスタ(メーカー)ワークDto
   */
  JoinMasterInfoDto searchJoinMasterWorkAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd,
                                            Integer importKbn);

  /**
   * <pre>
   * 結合マスタ(メーカー)ワークの登録.
   * </pre>
   * 
   * @param joinMasterWorkInfoDto 結合マスタ(メーカー)Dto
   * @return 登録結合マスタ(メーカー)
   */
  int insertJoinMasterWorkInfoList(JoinMasterInfoDto joinMasterWorkInfoDto);

  /**
   * <pre>
   * 結合マスタ(メーカー)ワーク情報更新.
   * </pre>
   * 
   * @param joinMasterWorkDto 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void updateJoinMasterWork(JoinMasterDto joinMasterWorkDto);

  /**
   * <pre>
   * 結合マスタ(メーカー)ワーク削除.
   * </pre>
   * 
   * @param joinMasterWorkDto 結合マスタ(メーカー)ワーク
   */
  void deleteJoinMasterWork(JoinMasterDto joinMasterWorkDto);

  /**
   * <pre>
   * 結合マスタ(メーカー)ワーク情報全削除.
   * </pre>
   */
  void deleteAllJoinMasterWork();

  /**
   * メッセージ情報を取得
   * 
   * @param messageCode メッセージコード
   * @return メッセージ情報
   */
  String getMessage(String messageCode);
}
