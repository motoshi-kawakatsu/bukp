//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 李超傑
// 作 成 日       2017/02/06   修正内容 : 結合詳細：新規作成
package jp.co.broadleaf.breg.joindetail.facade;

import jp.co.broadleaf.breg.common.dto.BlCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.SelectCodeMasterDto;
import jp.co.broadleaf.breg.joindetail.facade.dto.JoinDetailDto;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 結合詳細のFacadeインターフェース
 * </pre>
 */

public interface JoinDetailFacade {
	/**
	 * 結合詳細のDTOリストを取得します。
	 * 
	 * @param prmSetDtlNo1
	 *            優良設定詳細コード１
	 * @param partsMakerCd
	 *            部品メーカーコード
	 * @param tbsPartsCode
	 *            BLコード
	 * @param joinSourceMakerCode
	 *            結合元メーカーコード
	 * @param prmSetDtlNo2
	 *            優良設定詳細コード２
	 * @param joinSourPartsNoWithH
	 *            結合元品番(－付き品番)
	 * @param joinDestPartsNo
	 *            String
	 * @return 結合詳細のDTOリスト
	 */
	List<JoinDetailDto> getJoinDetail(String prmSetDtlNo1, String partsMakerCd, String tbsPartsCode,
			String joinSourceMakerCode, String prmSetDtlNo2, String joinSourPartsNoWithH, String joinDestPartsNo);

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
	 * 結合詳細ワークのDTOリストを取得します。
	 * 
	 * @param prmSetDtlNo1
	 *            優良設定詳細コード１
	 * @param partsMakerCd
	 *            部品メーカーコード
	 * @param tbsPartsCode
	 *            BLコード
	 * @param joinSourceMakerCode
	 *            結合元メーカーコード
	 * @param prmSetDtlNo2
	 *            優良設定詳細コード２
	 * @param joinSourPartsNoWithH
	 *            String
	 * @param joinDestPartsNo
	 *            String 結合元品番(－付き品番)
	 * @return 結合詳細のDTOリスト
	 */
	List<JoinDetailDto> getJoinMasterWorkList(String prmSetDtlNo1, String partsMakerCd, String tbsPartsCode,
			String joinSourceMakerCode, String prmSetDtlNo2, String joinSourPartsNoWithH, String joinDestPartsNo);

	/**
	 * <pre>
	 * * 種別コード名称マップを取得.
	 * </pre>
	 * 
	 * @param makerCd
	 *            メーカーコード
	 * @return 種別コード
	 */
	Map<String, String> getKindCodeNameMap(int makerCd);

	/**
	 * 結合マスタ(メーカー)のチェック.
	 * 
	 * @param joinDetailDtoList
	 *            結合マスタ
	 * @param makerCd
	 *            メーカーコード
	 * @param mode
	 *            int
	 * @return joinDetailDtoList
	 */
	boolean checkImportList(List<JoinDetailDto> joinDetailDtoList, int makerCd, int mode);
}
