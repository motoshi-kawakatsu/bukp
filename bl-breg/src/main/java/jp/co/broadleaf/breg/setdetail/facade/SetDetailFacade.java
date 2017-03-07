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
package jp.co.broadleaf.breg.setdetail.facade;

import java.util.List;
import java.util.Map;

import jp.co.broadleaf.breg.common.dto.BlCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.SelectCodeMasterDto;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterDto;

/**
 * <pre>
 * セート詳細のFacadeインターフェース
 * </pre>
 */
public interface SetDetailFacade {
	/**
	 * セート詳細のDTOリストを取得します。
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
	List<SetMasterDto> getSetDetail(String prmSetDtlNo1, String partsMakerCd, String goodsMGroup,
			String setMainPartsNo);

	/**
	 * BLコードマスタDtoを取得します。
	 * 
	 * @param makerCd
	 *            メーカコード
	 * @return BLコードマスタDtoリスト
	 */
	List<BlCodeMasterDto> getBlCodeInfo(int makerCd);

	/**
	 * セレクトコードDtoリストを取得します。
	 * 
	 * @param makerCd
	 *            メーカコード
	 * @return セレクトコードDtoリスト
	 */
	List<SelectCodeMasterDto> getSelectCodeInfo(int makerCd);

	/**
	 * セート詳細ワークのDTOリストを取得します。
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
	List<SetMasterDto> getSetMasterWorkList(String prmSetDtlNo1, String partsMakerCd, String goodsMGroup,
			String setMainPartsNo);

	/**
	 * 優良品番検索結果
	 * 
	 * @param makerCd
	 *            メーカーコード
	 * 
	 * @return 優良品番検索結果
	 */
	Map<String, String> getGoodsCodeNameMap(int makerCd);
}
