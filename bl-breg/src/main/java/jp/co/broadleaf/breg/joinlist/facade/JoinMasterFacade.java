package jp.co.broadleaf.breg.joinlist.facade;

import jp.co.broadleaf.breg.joinlist.facade.dto.JoinGridDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterInfoDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterSearchDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * </pre>
 */
public interface JoinMasterFacade {

  /**
   * <pre>
   * 結合マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param joinMasterSearchDto 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param order 表示順
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param searchMode searchMode
   * @return 結合マスタ(メーカー)Dto
   */
  JoinMasterInfoDto searchJoinMasterInfoList(JoinMasterSearchDto joinMasterSearchDto, int order, Long skipRows,
                                             Long maxRows, Boolean isPage, int searchMode);

  /**
   * <pre>
   * 結合選択モードの結合マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param joinMasterInfoDto 未編集の結合マスタリスト
   * @return 結合マスタ(メーカー)Dto
   */
  JoinMasterInfoDto searchJoinMasterInfoList(JoinMasterInfoDto joinMasterInfoDto);

  /**
   * <pre>
   * 結合マスタ(メーカー)の登録.
   * </pre>
   * 
   * @param joinMasterInfoDto 結合マスタ(メーカー)Dto
   * @return 登録結合マスタ(メーカー)
   */
  int insertJoinMasterInfoList(JoinMasterInfoDto joinMasterInfoDto);

  /**
   * <pre>
   * 結合マスタ(メーカー)の全データ取得.
   * </pre>
   * 
   * @return 結合マスタ(メーカー)Dto
   */
  JoinMasterInfoDto searchJoinMasterInfoList();

  /**
   * <pre>
   * 結合マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param skipRows skipRows
   * @param maxRows maxRows
   * @param isPage isPage
   * @param makerCd メーカーコード
   * @param searchMode searchMode
   * @return 商品マスタ(メーカー)Dto
   */
  JoinMasterInfoDto searchJoinMasterInfoDtoAll(Long skipRows, Long maxRows, Boolean isPage, int makerCd,
                                               int searchMode);

  /**
   * 該当キーの結合マスタ（メーカー）本情報を取得
   * 
   * @param selectorCd セレクトコード
   * @param partsMakerCd 部品メーカーコード
   * @param blCode BLコード
   * @param joinMakerCd カーメーカーコード
   * @param kindCode 種別コード
   * @param joinPartsNo 純正品番
   * @return 結合マスタ(メーカー)Dto
   */
  JoinMasterInfoDto getJoinMasterInfo(String selectorCd, String partsMakerCd, String blCode, String joinMakerCd,
                                      String kindCode, String joinPartsNo);

  /**
   * <pre>
   * 結合マスタ(メーカー)情報更新.
   * </pre>
   * 
   * @param joinMasterDto 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void updateJoinMaster(JoinMasterDto joinMasterDto);

  /**
   * <pre>
   * 結合マスタ(メーカー)情報削除.
   * </pre>
   * 
   * @param joinMasterDto 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void deleteJoinMaster(JoinMasterDto joinMasterDto);

  /**
   * <pre>
   * BLコード名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return BLコード名称マップ
   */
  Map<String, String> getBlCodeNameMap(int makerCd);

  /**
   * <pre>
   * * セレクトコードマスト名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return セレクトコード名称マップ
   */
  Map<String, String> getSelectCodeNameMap(int makerCd);

  /**
   * <pre>
   * * 商品中分類コードマップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 商品中分類コード
   */
  Map<String, String> getClassifyCodeGuideMap(int makerCd);

  /**
   * <pre>
   * * メーカーマスト名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return メーカー名称マップ
   */
  Map<String, String> getCarmakerNameMap(int makerCd);

  /**
   * <pre>
   * * 種別コードマスト名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 種別コード名称マップ
   */
  Map<String, String> getKindCodeNameMap(int makerCd);

  /**
   * <pre>
   * * 優良品番名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 優良品番名称マップ
   */
  Map<String, String> getPrimeCodeMap(int makerCd);

  /**
   * <pre>
   * * 純正品番名称マップを取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 純正品番名称マップ
   */
  Map<String, String> getPureCodeMap(int makerCd);

  /**
   * <pre>
   * 結合ページ内行数設定の取得.
   * </pre>
   * 
   * @param makerCd メーカーコード
   * @return 結合ページ内行数設定
   */
  int searchJoinListRows(int makerCd);

  /**
   * メッセージを取得します.
   *
   * @param messageMap messageMap
   */
  void getMessage(HashMap<String, String> messageMap);

  /**
   * 結合マスタ(メーカー)のチェック.
   * 
   * @param joinGridDtoList 結合マスタ
   * @param makerCd メーカーコード
   * @return joinMasterMakerDtoList
   */
  JoinMasterInfoDto checkImportList(List<JoinGridDto> joinGridDtoList, int makerCd);

  /**
   * 画面初期ロード時、機能制御の判断
   * 
   * @return 「True」：機能制御なし、「False」：機能制御あり
   */
  Boolean isControl();

  /**
   * 画面リストの比較
   * 
   * @param joinGridDtoList 改修後のリスト
   * @param joinMasterDtoList 改修前のリスト
   * @return joinMasterMakerDtoList
   */
  List<JoinMasterDto> campareList(List<JoinMasterDto> joinGridDtoList, List<JoinMasterDto> joinMasterDtoList);

  /**
   * CheckDivの取得
   * 
   * @param joinMasterDto 結合マスタ(メーカー)Dto
   * @return CheckDiv
   */
  Boolean getCheckDiv(JoinMasterDto joinMasterDto);

  /**
   * SelectMakerの処理
   * 
   * @param joinGridDto 結合マスタ
   * @param makerCd メーカーコード
   */
  void manegeSelectMaker(JoinGridDto joinGridDto, int makerCd);

  /**
   * <pre>
   * チェック選択に情報取得.
   * </pre>
   * 
   * @param partsMakerCd メーカーコード
   * @return チェック選択情報
   */
  int searchSelectBySelectKbn(int partsMakerCd);

  /**
   * <pre>
   * 結合マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param partsMakerCd メーカーコード
   * @return 結合マスタ(メーカー)Dto
   */
  JoinMasterInfoDto searchJoinMasterInfoBySelect(int partsMakerCd);
}
