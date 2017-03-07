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
package jp.co.broadleaf.breg.setlist.domain.model;

import java.sql.Timestamp;

/**
 * <pre>
 * dto and model's public getset
 * </pre>
 */
public interface SetMasterI {

  /**
   * UUIDを返します。
   * 
   * @return UUID
   */
  String getUuid();

  /**
   * UUIDを設定します。
   * 
   * @param uuid セットするUUID
   */
  void setUuid(String uuid);

  /**
   * 作成日時を返します。
   * 
   * @return 作成日時
   */
  Timestamp getInsDtTime();

  /**
   * 作成日時を設定します。
   * 
   * @param insertDateTime セットする作成日時
   */
  void setInsDtTime(Timestamp insertDateTime);

  /**
   * 更新日時を返します。
   * 
   * @return 更新日時
   */
  Timestamp getUpdDtTime();

  /**
   * 更新日時を設定します。
   * 
   * @param updateDateTime セットする更新日時
   */
  void setUpdDtTime(Timestamp updateDateTime);

  /**
   * 作成アカウントIDを返します。
   * 
   * @return 作成アカウントID
   */
  String getInsAccountId();

  /**
   * 作成アカウントIDを設定します。
   * 
   * @param insertAccountId セットする作成アカウントID
   */
  void setInsAccountId(String insertAccountId);

  /**
   * 更新アカウントIDを返します。
   * 
   * @return 更新アカウントID
   */
  String getUpdAccountId();

  /**
   * 更新アカウントIDを設定します。
   * 
   * @param updateAccountId セットする更新アカウントID
   */
  void setUpdAccountId(String updateAccountId);

  /**
   * 論理削除区分を返します。
   * 
   * @return 論理削除区分
   */
  Integer getLogicalDelDiv();

  /**
   * 論理削除区分を設定します。
   * 
   * @param logicalDeleteDivision セットする論理削除区分
   */
  void setLogicalDelDiv(Integer logicalDeleteDivision);

  /**
   * 優良設定詳細コード１を返します。
   * 
   * @return 優良設定詳細コード１
   */
  Integer getPrmSetDtlNo1();

  /**
   * 優良設定詳細コード１を設定します。
   * 
   * @param prmSetDtlNo1 セットする優良設定詳細コード１
   */
  void setPrmSetDtlNo1(Integer prmSetDtlNo1);

  /**
   * 部品メーカーコードを返します。
   * 
   * @return 部品メーカーコード
   */
  Integer getPartsMakerCd();

  /**
   * 部品メーカーコードを設定します。
   * 
   * @param partsMakerCd セットする部品メーカーコード
   */
  void setPartsMakerCd(Integer partsMakerCd);

  /**
   * 商品中分類コードを返します。
   * 
   * @return 商品中分類コード
   */
  Integer getGoodsMGroup();

  /**
   * 商品中分類コードを設定します。
   * 
   * @param goodsMGroup セットする商品中分類コード
   */
  void setGoodsMGroup(Integer goodsMGroup);

  /**
   * BLコードを返します。
   * 
   * @return BLコード
   */
  Integer getTbsPartsCode();

  /**
   * BLコードを設定します。
   * 
   * @param tbsPartsCode セットするBLコード
   */
  void setTbsPartsCode(Integer tbsPartsCode);

  /**
   * セット親品番を返します。
   * 
   * @return セット親品番
   */
  String getSetMainPartsNo();

  /**
   * セット親品番を設定します。
   * 
   * @param setMainPartsNo セットするセット親品番
   */
  void setSetMainPartsNo(String setMainPartsNo);

  /**
   * セット表示順位を返します。
   * 
   * @return セット表示順位
   */
  Integer getSetDispOrder();

  /**
   * セット表示順位を設定します。
   * 
   * @param setDispOrder セットするセット表示順位
   */
  void setSetDispOrder(Integer setDispOrder);

  /**
   * セット子品番を返します。
   * 
   * @return セット子品番
   */
  String getSetSubPartsNo();

  /**
   * セット子品番を設定します。
   * 
   * @param setSubPartsNo セットするセット子品番
   */
  void setSetSubPartsNo(String setSubPartsNo);

  /**
   * 品名を返します。
   * 
   * @return 品名
   */
  String getSetKanaName();

  /**
   * 品名を設定します。
   * 
   * @param setKanaName セットする品名
   */
  void setSetKanaName(String setKanaName);

  /**
   * セット名称を返します。
   * 
   * @return セット名称
   */
  String getSetName();

  /**
   * セット名称を設定します。
   * 
   * @param setName セットするセット名称
   */
  void setSetName(String setName);

  /**
   * セットQTYを返します。
   * 
   * @return セットQTY
   */
  Double getSetQty();

  /**
   * セットQTYを設定します。
   * 
   * @param setQty セットするセットQTY
   */
  void setSetQty(Double setQty);

  /**
   * セット規格・特記事項を返します。
   * 
   * @return セット規格・特記事項
   */
  String getSetSpecialNote();

  /**
   * セット規格・特記事項を設定します。
   * 
   * @param setSpecialNote セットするセット規格・特記事項
   */
  void setSetSpecialNote(String setSpecialNote);

  /**
   * 優良部品規格・特記事項(C向け)を返します。
   * 
   * @return 優良部品規格・特記事項(C向け)
   */
  String getPrimePartsSpecialNoteC();

  /**
   * 優良部品規格・特記事項(C向け)を設定します。
   * 
   * @param primePartsSpecialNoteC セットする優良部品規格・特記事項(C向け)
   */
  void setPrimePartsSpecialNoteC(String primePartsSpecialNoteC);

  /**
   * 適用日付を返します。
   * 
   * @return 適用日付
   */
  Timestamp getStartTime();

  /**
   * 適用日付を設定します。
   * 
   * @param startTime セットする適用日付
   */
  void setStartTime(Timestamp startTime);

  /**
   * チェック区分を返します。
   * 
   * @return チェック区分
   */
  Short getCheckFlg();

  /**
   * チェック区分を設定します。
   * 
   * @param checkFlg セットするチェック区分
   */
  void setCheckFlg(Short checkFlg);

  /**
   * データステータスを返します。
   * 
   * @return データステータス
   */
  Short getErrorFlg();

  /**
   * データステータスを設定します。
   * 
   * @param errorFlg セットするデータステータス
   */
  void setErrorFlg(Short errorFlg);

  /**
   * BL登録区分を返します。
   * 
   * @return BL登録区分
   */
  Short getBlEntryFlg();

  /**
   * BL登録区分を設定します。
   * 
   * @param blEntryFlg セットするBL登録区分
   */
  void setBlEntryFlg(Short blEntryFlg);

  /**
   * インポート区分を返します。
   * 
   * @return インポート区分
   */
  Short getImportKbn();

  /**
   * インポート区分を設定します。
   * 
   * @param importKbn セットするインポート区分
   */
  void setImportKbn(Short importKbn);

  /**
   * 処理区分を返します。
   * 
   * @return 処理区分
   */
  Short getManageKbn();

  /**
   * 処理区分を設定します。
   * 
   * @param manageKbn セットする処理区分
   */
  void setManageKbn(Short manageKbn);

  /**
   * エラー内容を返します。
   * 
   * @return エラー内容
   */
  String getErrorDetail();

  /**
   * エラー内容を設定します。
   * 
   * @param errorDetail セットするエラー内容
   */
  void setErrorDetail(String errorDetail);

  /**
   * 削除依頼区分を返します。
   * 
   * @return 削除依頼区分
   */
  Short getDeleteFlg();

  /**
   * 削除依頼区分を設定します。
   * 
   * @param deleteFlg セットする削除依頼区分
   */
  void setDeleteFlg(Short deleteFlg);

  /**
   * 削除理由を返します。
   * 
   * @return 削除理由
   */
  String getDeleteReason();

  /**
   * 削除理由を設定します。
   * 
   * @param deleteReason セットする削除理由
   */
  void setDeleteReason(String deleteReason);

  /**
   * 申請状態を返します。
   * 
   * @return 申請状態
   */
  Short getApplyCondition();

  /**
   * 申請状態を設定します。
   * 
   * @param applyCondition セットする申請状態
   */
  void setApplyCondition(Short applyCondition);

}
