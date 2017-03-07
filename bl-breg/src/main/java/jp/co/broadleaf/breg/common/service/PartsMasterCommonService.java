//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 湯凱
// 作 成 日       2017/02/13   修正内容 : 会社情報：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.service;

import jp.co.broadleaf.breg.common.entity.PartsMasterCommon;

import java.util.List;

/**
 * <pre>
 * 層別マストのinterfaceクラス
 * </pre>
 */
public interface PartsMasterCommonService {

  /**
   * 層別マスト情報を取得
   * 
   * @param makerCd メーカーコード
   * @return 層別マスト情報
   */
  List<PartsMasterCommon> getPartsMasterInfo(int makerCd);
}
