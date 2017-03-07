//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                    作成担当 : 王　天コン
// 作 成 日       2017/02/27   修正内容 : 結合一覧：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.service;

import jp.co.broadleaf.breg.common.entity.CarmakerMasterCommon;

import java.util.List;

/**
 * <pre>
 * カーメーカーコードマスタのinterfaceクラス
 * </pre>
 */
public interface CarmakerMasterCommonService {

  /**
   * カーメーカーコードマスタ情報を取得
   * 
   * @param makerCd メーカーコード
   * @return カーメーカーコードマスタ情報
   */
  List<CarmakerMasterCommon> getCarmakerMasterInfo(int makerCd);

}
