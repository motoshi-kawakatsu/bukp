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
package jp.co.broadleaf.breg.applycommon.facade;

import jp.co.broadleaf.breg.applyheadmastercommon.facade.dto.ApplyHeadMasterCommonInfoDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterCommonDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerInfoDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterCommonDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterDto;
import jp.co.broadleaf.breg.joinlist.facade.dto.JoinMasterInfoDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterCommonDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterInfoDto;

import java.util.List;

/**
 * <pre>
 * </pre>
 */
public interface ApplyCommonFacade {

  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param importKbn インポート区分(メーカー)
   * @param applyCondition 申請状態(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @param isFromImport boolean
   * @param isReApplication boolean
   * @return 商品マスタ(メーカー)Dto
   */
  GoodsMasterMakerInfoDto searchItemMasterInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                                   Integer partsMakerCd, Integer logicalDeleteCode,
                                                   boolean isFromImport, boolean isReApplication);

  /**
   * <pre>
   * セットマスタ(メーカー)の取得.
   * </pre>
   * 
   * @param importKbn インポート区分(メーカー)
   * @param applyCondition 申請状態(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @param isFromImport boolean
   * @param isReApplication boolean
   * @return セットマスタ(メーカー)Dto
   */
  SetMasterInfoDto searchSetMasterInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                           Integer partsMakerCd, Integer logicalDeleteCode, boolean isFromImport,
                                           boolean isReApplication);

  /**
   * <pre>
   * 結合マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param importKbn インポート区分(メーカー)
   * @param applyCondition 申請状態(メーカー)
   * @param updAccountId 更新アカウントID
   * @param partsMakerCd 部品メーカーコード
   * @param logicalDeleteCode 論理削除区分
   * @param isFromImport boolean
   * @param isReApplication boolean
   * @return 結合マスタ(メーカー)Dto
   */
  JoinMasterInfoDto searchJoinMasterInfoList(Short importKbn, Short applyCondition, String updAccountId,
                                             Integer partsMakerCd, Integer logicalDeleteCode, boolean isFromImport,
                                             boolean isReApplication);

  /**
   * <pre>
   * 商品マスタ(common)情報更新.
   * </pre>
   * 
   * @param goodsMasterDto 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void updateGoodsMaster(GoodsMasterCommonDto goodsMasterDto);

  /**
   * <pre>
   * セットマスタ(common)情報更新.
   * </pre>
   * 
   * @param setMasterDto 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void updateSetMaster(SetMasterCommonDto setMasterDto);

  /**
   * <pre>
   * 結合マスタ(common)情報更新.
   * </pre>
   * 
   * @param joinMasterDto 更新する項目（優良設定詳細コード１ など）の連係情報
   */
  void updateJoinMaster(JoinMasterCommonDto joinMasterDto);

  /**
   * <pre>
   * 申請ヘッダマスタ登録.
   * </pre>
   * 
   * @param applyHeadMasterDto 申請ヘッダマスタ
   * @return 登録した申請ヘッダマスタの件数
   * @throws Throwable Throwable
   */
  int insertApplyHeadMaster(ApplyHeadMasterCommonInfoDto applyHeadMasterDto) throws Throwable;

  /**
   * <pre>
   * 商品マスタの情報をチェックする(警告)。
   * </pre>
   *
   * @param errorMessageList goodsMasterMakerDto
   * @param goodsMasterMakerDto GoodsMasterMakerDto
   */
  void checkGoodsWarning(List<String> errorMessageList, GoodsMasterMakerDto goodsMasterMakerDto);

  /**
   * <pre>
   * 商品マスタの情報をチェックする(エラー)。
   * </pre>
   *
   * @param errorMessageList goodsMasterMakerDto
   * @param goodsMasterMakerDto GoodsMasterMakerDto
   * @throws Throwable Throwable
   */
  void checkGoodsError(List<String> errorMessageList, GoodsMasterMakerDto goodsMasterMakerDto) throws Throwable;

  /**
   * <pre>
   * セットマスタの情報をチェックする(エラー)。
   * </pre>
   *
   * @param errorMessageList goodsMasterMakerDto
   * @param setMasterMakerDto SetMasterDtos
   * @throws Throwable Throwable
   */
  void checkSetError(List<String> errorMessageList, SetMasterDto setMasterMakerDto) throws Throwable;

  /**
   * <pre>
   * 結合マスタの情報をチェックする(警告)。
   * </pre>
   *
   * @param errorMessageList goodsMasterMakerDto
   * @param joinMasterMakerDto JoinMasterDto
   */
  void checkJoinWarning(List<String> errorMessageList, JoinMasterDto joinMasterMakerDto);

  /**
   * <pre>
   * 結合マスタの情報をチェックする(エラー)。
   * </pre>
   *
   * @param errorMessageList goodsMasterMakerDto
   * @param joinMasterMakerDto JoinMasterDto
   */
  void checkJoinError(List<String> errorMessageList, JoinMasterDto joinMasterMakerDto);

  /**
   * メッセージを取得します.
   */
  void getMessage();

  /**
   * <pre>
   * リマインダ受付（パスワード忘れ）.
   * </pre>
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   * @param ipAddr IPアドレス
   * @param goodsCount int
   * @param setCount int
   * @param joinCount int
   * @return String
   */
  String sendMail(int makerCode, String loginId, String ipAddr, int goodsCount, int setCount, int joinCount);
  
  /**
   * <pre>
   * 商品マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param partsMakerCd int
   * @param logicalDeleteCode int
   * @return 商品マスタ(メーカー)Dto
   */
  GoodsMasterMakerInfoDto searchGoodsMasterInfoBySelect(int partsMakerCd, int logicalDeleteCode);
  
  /**
   * <pre>
   * セットマスタ(メーカー)の取得.
   * </pre>
   * 
   * @param partsMakerCd int
   * @param logicalDeleteCode int
   * @return セットマスタ(メーカー)Dto
   */
  SetMasterInfoDto searchSetMasterInfoBySelect(int partsMakerCd, int logicalDeleteCode);
  
  /**
   * <pre>
   * 結合マスタ(メーカー)の取得.
   * </pre>
   * 
   * @param partsMakerCd int
   * @param logicalDeleteCode int
   * @return 結合マスタ(メーカー)Dto
   */
  JoinMasterInfoDto searchJoinMasterInfoBySelect(int partsMakerCd, int logicalDeleteCode);
  
  /**
   * <pre>
   * チェック選択に商品情報削除.
   * </pre>
   * 
   * @param selectKbn セットする区分
   * @param partsMakerCd int
   */
  void removeBySelectKbn(int selectKbn, int partsMakerCd);
  
  /**
   * <pre>
   * Message.
   * </pre>
   * 
   * @param bregMessageCode String
   * @return String
   */
  String getMessage(String bregMessageCode);

}
