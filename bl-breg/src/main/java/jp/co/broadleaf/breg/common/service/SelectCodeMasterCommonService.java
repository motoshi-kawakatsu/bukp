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

import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;

import java.util.List;

/**
 * <pre>
 * セレクトコードマストのinterfaceクラス。
 * </pre>
 */
public interface SelectCodeMasterCommonService {

  /**
   * セレクトコード情報を取得
   * 
   * @param goodsMakerCd メーカーコード
   * @return セレクトコード情報
   */
  List<SelectCodeMasterCommon> getSelectCodeInfo(int goodsMakerCd);
}
