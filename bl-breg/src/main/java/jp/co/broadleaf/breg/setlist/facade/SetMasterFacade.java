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
package jp.co.broadleaf.breg.setlist.facade;

import jp.co.broadleaf.breg.common.dto.BlCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.SelectCodeMasterDto;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.SelectMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterMakerId;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMakerId;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterId;
import jp.co.broadleaf.breg.setlist.domain.model.SetMasterMakerIdList;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterInfoDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterSearchDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * セットマスタ(メーカー)Facade　interface.
 * </pre>
 */
public interface SetMasterFacade {

  /**
   * <pre>
   * セットマスタ(メーカー)の取得.
   * </pre>
   * @param fileSelectMode 0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @param setMasterSearchDto 検索する項目（優良設定詳細コード１ など）の連係情報
   * @param isPage pageMark
   * @param table "maker"/"workmaker"
   * @return セットマスタ(メーカー)Dto
   */
  SetMasterInfoDto searchSetMasterInfoDto(SetMasterSearchDto setMasterSearchDto, boolean isPage, String table,int fileSelectMode, int mode);

  
  /**
   * <pre>
   * セットマスタ(メーカー)の全データ取得.
   * </pre>
   * @param makerCd makerCd
   * @param fileSelectMode   0, "インポート（一括申請）" 1, "インポート"
   * @param mode menuMode
   * @param table "maker"/"workmaker"
   * @return セットマスタ(メーカー)Dto
   */
  SetMasterInfoDto searchSetMasterInfoDto(String table,int fileSelectMode,int makerCd,int mode);

  /**
   * <pre>
   * セットマスタ(メーカー)の登録.
   * </pre>
   * 
   * @param setMasterInfoDto セットマスタ(メーカー)Dto
   * @param table "maker"/"workmaker"
   * @return 登録セットマスタ(メーカー)
   */
  int insertSetMasterInfoDto(SetMasterInfoDto setMasterInfoDto, String table);

  /**
   * <pre>
   * セットマスタ(メーカー)情報更新.
   * </pre>
   * 
   * @param table "maker"/"workmaker"
   * @param setMasterDto 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void updateSetMaster(SetMasterDto setMasterDto, String table);

  /**
   * <pre>
   * blCode 検索
   * </pre>
   * @param makerCd メーカーコード
   * @return blCodelist
   */
  List<BlCodeMasterDto> searchBlCode(int makerCd);

  /**
   * <pre>
   * SelectCode 検索
   * </pre>
   * @param makerCd メーカーコード
   * @return selectCodelist
   */
  List<SelectCodeMasterDto> searchSelectCode(int makerCd);
  
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param setMasterId セットマスタ(メーカー)ID
   * @return セットマスタ(メーカー)情報
   */
  List<SetMasterDto> searchSetMasterWorkById(SetMasterWorkMakerId setMasterId);
  /**
   * <pre>
   * blCode 検索
   * </pre>
   * @param makerCd メーカーコード
   * @return blCodelist
   */
  Map<String, String> getBlCodeNameMap(int makerCd);

  /**
   * <pre>
   * SelectCode 検索
   * </pre>
   * @param makerCd メーカーコード
   * @return selectCodelist
   */
  Map<String, String> getSelectCodeNameMap(int makerCd);
  
  /**
   * 優良品番検索結果
   *  
   * @param makerCd メーカーコード
   * 
   * @return 優良品番検索結果
   */
  Map<String, String> getGoodsCodeNameMap(int makerCd);


  /**
   * <pre>
   * 商品マスタ(メーカー)のチェックデータ取得.
   * </pre>
   * @param setMasterSearchDto チェックするデータの連係情報
   * @return 親商品の連係情報
   */
  GoodsMasterMaker searchMainGoodsForCheck(SetMasterSearchDto setMasterSearchDto);

  /**
   * <pre>
   * 商品マスタ(メーカー)のチェックデータ取得.
   * </pre>
   * @param setMasterSearchDto チェックするデータの連係情報
   * @return 子商品の連係情報
   */
  GoodsMasterMaker searchSubGoodsForCheck(SetMasterSearchDto setMasterSearchDto);
  
  /**
   * <pre>
   * セットマスタ(メーカー)キーのチェック（登録前チェック）.
   * </pre>
   * @param setMasterSearchDto チェックするデータの連係情報
   * @return チェック結果
   */
  boolean checkIdBeforeInsert(SetMasterSearchDto setMasterSearchDto);  
  /**
   * <pre>
   * セットマスタ(メーカー/work)delete.
   * </pre>
   * 
   * @param table セットマスタ(メーカー)ID
   * @param setMasterInfoDto セットマスタ(メーカー)ID
   */
  void deleteSetMaster(String table,SetMasterInfoDto setMasterInfoDto);
  /**
   * <pre>
   * セット選択モードのセットマスタ(メーカー)の取得.
   * </pre>
   * 
   * @param setMasterInfoDto 未編集の結合マスタリスト
   * @param idlist SetMasterMakerIdList
   * @return 結合マスタ(メーカー)Dto
   */
  SetMasterInfoDto searchSetMasterInfoList(SetMasterInfoDto setMasterInfoDto,SetMasterMakerIdList idlist);
  /**
   * メッセージを取得します.
   * 
   * @param messageMap messageMap
   */
  void getMessage(HashMap<String, String> messageMap);
  /**
   * 画面初期ロード時、機能制御の判断
   * 
   * @return 「True」：機能制御なし、「False」：機能制御あり
   */
  Boolean isControl();
  /**
   * SelectMakerの処理
   * 
   * @param setMasterDto セットマスタ
   * @param makerCd メーカーコード
   */
  void manegeSelectMaker(SetMasterDto setMasterDto, int makerCd);
  /**
   * SelectMakerのSession処理
   * 
   * @param idList セットマスタ
   */
  void manegeSelectMakerInSession(List<SetMasterId> idList);
  /**
   * <pre>
   * チェック選択に情報取得.
   * </pre>
   * 
   * @return チェック選択情報
   */
  int searchSelectBySelectKbn(int partsMakerCd);
  /**
   * <pre>
   * セットマスタ(メーカー)の取得.
   * </pre>
   * 
   * @return セットマスタ(メーカー)Dto
   */
  SetMasterInfoDto searchSetMasterInfoBySelect(int partsMakerCd);


  /**
   * @param setMasterId
   * @return SetMasterMaker
   */
  SetMasterMaker searchById(SetMasterMakerId setMasterId);
  
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * 
   * @param setMasterId セットマスタ(メーカー)ID
   * @return セットマスタ(メーカー)情報
   */
  List<SetMasterDto> searchSetMasterById(SetMasterMakerId setMasterId);
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * @param partsMakerCd int
   * @return セットマスタ(メーカー)情報
   */
  List<SelectMaker>  findAll(int partsMakerCd);
  /**
   * <pre>
   * セットマスタ(メーカー)取得.
   * </pre>
   * @param setMasterMaker setMasterMaker
   * @return セットマスタ(メーカー)情報
   */
  SelectMaker searchSelectMaker(SetMasterMaker setMasterMaker);

}
