//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 燕京
// 作 成 日       2017/02/06   修正内容 : セート詳細：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.setdetail.service;

import java.util.List;

import jp.co.broadleaf.breg.common.entity.SetMasterMaker;

/**
 * 
 * <pre>
 * セート詳細サービスインターフェース
 * </pre>
 */
public interface SetDetailService {
	/**
	 * セートマストリストを取得します。
	 * 
	 * @param prmSetDtlNo1
	 *            優良設定詳細コード１
	 * @param partsMakerCd
	 *            部品メーカーコード
	 * @param goodsMGroup
	 *            商品中分類コード
	 * @param setMainPartsNo
	 *            セット親品番
	 * @return セート詳細のDTOリスト
	 */
	List<SetMasterMaker> getSetDetail(String prmSetDtlNo1, String partsMakerCd, String goodsMGroup,
			String setMainPartsNo);


}
