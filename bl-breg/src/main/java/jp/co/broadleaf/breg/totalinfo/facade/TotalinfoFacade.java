//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:                 作成担当:司　志超
// 作成日:2017/02/07    修正内容:累積情報:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.totalinfo.facade;

import jp.co.broadleaf.breg.totalinfo.facade.dto.SearchConditionDto;
import jp.co.broadleaf.breg.totalinfo.facade.dto.TotalinfoDto;

/**
 * <pre>
 * 累積情報取得
 * </pre>
 */
public interface TotalinfoFacade {

  /**
   * <pre>
   * 累積情報の取得.
   * </pre>
   * @param searchConditionDto 検索条件のDto
   * @return  累積情報Dto
   */
  TotalinfoDto getTotalInfo(SearchConditionDto searchConditionDto);

}
