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
package jp.co.broadleaf.breg.setdetail.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jp.co.broadleaf.breg.common.dto.BlCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.SelectCodeMasterDto;
import jp.co.broadleaf.breg.common.entity.BlCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.GoodsMasterMaker;
import jp.co.broadleaf.breg.common.entity.SelectCodeMasterCommon;
import jp.co.broadleaf.breg.common.entity.SetMasterMaker;
import jp.co.broadleaf.breg.common.entity.SetMasterWorkMaker;
import jp.co.broadleaf.breg.common.enums.LogicDeleteDivEnum;
import jp.co.broadleaf.breg.common.service.BlCodeMasterCommonService;
import jp.co.broadleaf.breg.common.service.SelectCodeMasterCommonService;
import jp.co.broadleaf.breg.goodsguide.service.GoodsGuideService;
import jp.co.broadleaf.breg.setdetail.facade.SetDetailFacade;
import jp.co.broadleaf.breg.setdetail.service.SetDetailService;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterDto;
import jp.co.broadleaf.breg.setlist.service.SetMasterWorkService;
import jp.co.broadleaf.breg.setlist.utils.DataConvert;

/**
 * <pre>
 * セート詳細のFacadeクラス
 * </pre>
 */
public class SetDetailFacadeImpl implements SetDetailFacade {
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
	@Override
	public List<SetMasterDto> getSetDetail(String prmSetDtlNo1, String partsMakerCd, String goodsMGroup,
			String setMainPartsNo) {
		List<SetMasterDto> dtoList = new ArrayList<>();

		// セートマストリストを取得します。
		List<SetMasterMaker> list = setDetailService.getSetDetail(prmSetDtlNo1, partsMakerCd, goodsMGroup,
				setMainPartsNo);
		Map<String, String> goodsCodeMap = getGoodsCodeNameMap(Integer.parseInt(partsMakerCd));
		// 空のリストを返します。
		if (null == list || list.isEmpty()) {
			return null;
		}
		// コンバート
		for (SetMasterMaker setMasterMaker : list) {
			SetMasterDto dto = new SetMasterDto();
			DataConvert.setMasterMakerconvertToDtoOrModel(setMasterMaker, dto);
			dto.setSetSubPartsNo(goodsCodeMap.get(dto.getSetSubPartsNo()));
			dtoList.add(dto);
		}
		return dtoList;
	}

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
	@Override
	public List<SetMasterDto> getSetMasterWorkList(String prmSetDtlNo1, String partsMakerCd, String goodsMGroup,
			String setMainPartsNo) {
		List<SetMasterDto> dtoList = new ArrayList<>();
		// セートマストワークリストを取得します。
		List<SetMasterWorkMaker> list = setMasterWorkService.getSetDetail(prmSetDtlNo1, partsMakerCd, goodsMGroup,
				setMainPartsNo);
		// 空のリストを返します。
		if (null == list || list.isEmpty()) {
			return null;
		}
		Map<String, String> goodsCodeMap = getGoodsCodeNameMap(Integer.parseInt(partsMakerCd));
		// コンバート
		for (SetMasterWorkMaker setMasterMaker : list) {
			SetMasterDto dto = new SetMasterDto();
			DataConvert.setMasterWorkMakerconvertToDtoOrModel(setMasterMaker, dto);
			dto.setSetSubPartsNo(goodsCodeMap.get(dto.getSetSubPartsNo()));
			dtoList.add(dto);
		}
		return dtoList;
	}

	/**
	 * BLコードマスタ情報を取得
	 * 
	 * @param makerCd
	 *            メーカーコード
	 * @return BLコードマスタ情報
	 */
	@Override
	public List<BlCodeMasterDto> getBlCodeInfo(int makerCd) {
		List<BlCodeMasterDto> blCodeDto = null;
		// BLコードマスタ情報リストを取得
		List<BlCodeMasterCommon> blCodeMasterInfo = blCodeMasterCommonService.getBlCodeMasterInfo(makerCd);

		if (blCodeMasterInfo != null && !blCodeMasterInfo.isEmpty()) {
			blCodeDto = new ArrayList<>();
			// BLコードマスタ情報リストをBLコードマスタDtoへ変換
			for (BlCodeMasterCommon blCode : blCodeMasterInfo) {
				BlCodeMasterDto dto = new BlCodeMasterDto();
				dto.setBlCode(blCode.getBlCode());
				dto.setBlEdaCode(blCode.getBlEdaCode());
				dto.setBlFullName(blCode.getBlFullName());
				dto.setBlGroupCode(blCode.getBlGroupCode());
				dto.setBlHalfName(blCode.getBlHalfName());
				dto.setEquipGroup(blCode.getEquipGroup());
				dto.setGoodsRateGrpCode(blCode.getGoodsRateGrpCode());
				dto.setOfferDate(blCode.getOfferDate());
				dto.setPrimeSearchKbn(blCode.getPrimeSearchKbn());
				blCodeDto.add(dto);
			}
		}
		return blCodeDto;
	}

	/**
	 * セレクトコードマスト情報を取得
	 * 
	 * @param makerCd
	 *            メーカーコード
	 * @return セレクトコードマスト情報
	 */
	@Override
	public List<SelectCodeMasterDto> getSelectCodeInfo(int makerCd) {
		List<SelectCodeMasterDto> selectCodeDto = null;
		// セレクトコードマストリストを取得します。
		List<SelectCodeMasterCommon> selectCodeInfo = selectCodeMasterCommonService.getSelectCodeInfo(makerCd);
		if (selectCodeInfo != null && !selectCodeInfo.isEmpty()) {
			selectCodeDto = new ArrayList<>();
			// セレクトコードマストリストをセレクトコードマスDtoへ変換
			for (SelectCodeMasterCommon selectCode : selectCodeInfo) {
				SelectCodeMasterDto dto = new SelectCodeMasterDto();
				dto.setGoodsMakerCd(selectCode.getGoodsMakerCd());
				dto.setPrmSetDtlNo1(selectCode.getPrmSetDtlNo1());
				dto.setPrmSetDtlName(selectCode.getPrmSetDtlName());
				selectCodeDto.add(dto);
			}
		}
		return selectCodeDto;
	}

	/**
	 * 優良品番検索結果
	 * 
	 * @param makerCd
	 *            メーカーコード
	 * 
	 * @return 優良品番検索結果
	 */
	@Override
	public Map<String, String> getGoodsCodeNameMap(int makerCd) {
		String primeCode = "";
		int logical = LogicDeleteDivEnum.Valid.getValue();
		List<GoodsMasterMaker> primeByCode = goodsGuideService.getPrimeByCode(logical, makerCd, primeCode);
		Map<String, String> goodsNameMap = new HashMap<String, String>();

		if (primeByCode != null && !primeByCode.isEmpty()) {
			for (GoodsMasterMaker goodsMasterMaker : primeByCode) {
				String primePartsNoWithH = goodsMasterMaker.getPrimePartsNoWithH();
				if (!goodsNameMap.containsKey(primePartsNoWithH)) {
					goodsNameMap.put(primePartsNoWithH,
							primePartsNoWithH.concat("：").concat(goodsMasterMaker.getPrimePartsName()));
				}
			}
		}
		return goodsNameMap;

	}

	/** 優良品番検索サビース **/
	private GoodsGuideService goodsGuideService;

	/**
	 * <pre>
	 * 優良品番検索サビースを設定する。
	 * </pre>
	 *
	 * @param goodsGuideService
	 *            優良品番検索サビース
	 */
	@Resource
	public void setGoodsGuideService(GoodsGuideService goodsGuideService) {
		this.goodsGuideService = goodsGuideService;
	}

	/**
	 * setDetailService
	 */
	private SetDetailService setDetailService;

	/**
	 * セート詳細サービスをセット
	 * 
	 * @param setDetailService
	 *            セート詳細サービス
	 */
	@Resource
	public void setSetDetailService(SetDetailService setDetailService) {
		this.setDetailService = setDetailService;
	}

	/**
	 * setDetailService
	 */
	private SetMasterWorkService setMasterWorkService;

	/**
	 * test
	 * 
	 * @param setMasterWorkService
	 *            setMasterWorkService
	 */
	@Resource
	public void setSetMasterWorkService(SetMasterWorkService setMasterWorkService) {
		this.setMasterWorkService = setMasterWorkService;
	}

	/** BLコードマスタサービス */
	private BlCodeMasterCommonService blCodeMasterCommonService;

	/**
	 * BLコードマスタをセートします。
	 * 
	 * @param blCodeMasterCommonService
	 *            BLコードマスタのサービス
	 */
	@Resource
	public void setBlCodeMasterCommonService(BlCodeMasterCommonService blCodeMasterCommonService) {
		this.blCodeMasterCommonService = blCodeMasterCommonService;
	}

	/** セレクトコードマストエンプロイーサービス */
	private SelectCodeMasterCommonService selectCodeMasterCommonService;

	/**
	 * セレクトコードマストエンプロイーサービスをセットする
	 * 
	 * @param selectCodeMasterCommonService
	 *            SelectCodeMasterCommonService
	 */
	@Resource
	public void setSelectCodeMasterCommonService(SelectCodeMasterCommonService selectCodeMasterCommonService) {
		this.selectCodeMasterCommonService = selectCodeMasterCommonService;
	}
}
