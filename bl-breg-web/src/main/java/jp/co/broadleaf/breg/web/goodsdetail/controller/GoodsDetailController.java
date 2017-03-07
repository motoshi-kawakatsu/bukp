//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                       作成担当 : chenbei
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.goodsdetail.controller;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.codec.binary.Base64;

import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.BlEntryFlgEnum;
import jp.co.broadleaf.breg.common.enums.CheckFlgEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.common.enums.ImportKbnEnum;
import jp.co.broadleaf.breg.common.enums.JudgeEnum;
import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.common.enums.ModeEnum;
import jp.co.broadleaf.breg.common.enums.OpenPriceDivEnum;
import jp.co.broadleaf.breg.common.enums.SizeUnitEnum;
import jp.co.broadleaf.breg.common.enums.WeightUnitEnum;
import jp.co.broadleaf.breg.goodsdetail.facade.dto.GoodsDetailDto;
import jp.co.broadleaf.breg.goodslist.facade.GoodsMasterMakerFacade;
import jp.co.broadleaf.breg.goodslist.facade.GoodsMasterWorkMakerFacade;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsGridDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerDto;
import jp.co.broadleaf.breg.goodslist.facade.dto.GoodsMasterMakerInfoDto;
import jp.co.broadleaf.breg.web.goodsdetail.form.GoodsDetailForm;
import jp.co.broadleaf.breg.web.goodsdetail.form.GoodsImgForm;
import jp.co.broadleaf.breg.web.productlist.form.ProductListForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.BroadleafFormatUtils;
import jp.co.broadleaf.common.web.BroadleafSessionKeys;
import jp.co.broadleaf.framework.persistence.storage.StorageManager;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.Submission;
import jp.co.broadleaf.framework.web.WebResult;

/**
 * <pre>
 * 商品詳細Controllerクラス。
 * </pre>
 */
@Controller
@RequestMapping("/goodsdetail")
public class GoodsDetailController extends BaseController {

	/**
	 * PRODUCTDETAIL_URL
	 */
	private static final String PRODUCTDETAIL_URL = "goodsdetail/goodsDetail";

	/**
	 * EMPTY
	 */
	private static final String EMPTY = "";

	/**
	 * LINE
	 */
	private static final String LINE = "_";

	/**
	 * ZERO
	 */
	private static final String ZERO = "0";

	/**
	 * SLASH
	 */
	private static final String SLASH = "/";

	/**
	 * TEMP_PATH
	 */
	//private static final String TEMP_PATH = "D:\\temp\\";
	private static final String TEMP_PATH = "/usr/local/breg/tmp/";

	/**
	 * BUCKET_NAME
	 */
	private static final String BUCKET_NAME = "mystoragetest001";

	/**
	 * CONTENT_TYPE
	 */
	private static final String CONTENT_TYPE = "image/jpeg";

	/**
	 * blCodeNameMap
	 */
	private Map<String, String> blCodeNameMap;

	/**
	 * selectCodeNameMap
	 */
	private Map<String, String> selectCodeNameMap;

	/**
	 * partsNameMap
	 */
	private Map<String, String> partsNameMap;
	
	/**
	 * classifyGuideNameMap
	 */
	private Map<String, String> classifyGuideNameMap;

	/**
	 * goodsDetailData
	 */
	private GoodsGridDto goodsDetailData;

	/**
	 * makerCode
	 */
	private int makerCode;
	
	/**
	 * loginId
	 */
	private String loginId;
	
	/** 商品マスタ(メーカー)Facade */
	private GoodsMasterMakerFacade goodsMasterFacade;


	/** 商品マスタ(メーカー)Facade */
    private GoodsMasterWorkMakerFacade goodsMasterWorkFacade;
    
	/**
	 * <pre>
	 * 商品マスタFacadeを設定する。
	 * </pre>
	 * 
	 * @param goodsMasterFacade 商品マスタFacade
	 */
	@Resource
	public void setGoodsMasterFacade(GoodsMasterMakerFacade goodsMasterFacade) {
		this.goodsMasterFacade = goodsMasterFacade;
	}
	
	

	  /**
	   * <pre>
	   * 商品マスタFacadeを設定する。
	   * </pre>
	   * 
	   * @param goodsMasterWorkFacade 商品マスタFacade
	   */
	  @Resource
	  public void setGoodsMasterWorkFacade(GoodsMasterWorkMakerFacade goodsMasterWorkFacade) {
	    this.goodsMasterWorkFacade = goodsMasterWorkFacade;
	  }

	/**
	 * <pre>
	 * 初期表示時。
	 * </pre>
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/goodsDetail", method = RequestMethod.GET)
	public ModelAndView goodsDetailInit(HttpServletRequest request, HttpServletResponse response) {
	    logger.debug("商品詳細の初期表示処理を開始します。");
		ModelAndView result = new ModelAndView(PRODUCTDETAIL_URL);
		LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
		makerCode = loginPrincipal.getMakerCode();
		loginId = loginPrincipal.getLoginId();
		blCodeNameMap = goodsMasterFacade.getBlCodeNameMap(makerCode);
		selectCodeNameMap = goodsMasterFacade.getSelectCodeNameMap(makerCode);
		partsNameMap = goodsMasterFacade.getPartsNameMap(makerCode);
		classifyGuideNameMap = goodsMasterFacade.getClassifyCodeGuideMap(makerCode);
		Map<String, Object> enumMap = new HashMap<String, Object>();
		enumMap.put("blCodeNameMap", blCodeNameMap);
		enumMap.put("selectCodeNameMap", selectCodeNameMap);
		enumMap.put("partsNameMap", partsNameMap);
		result.addAllObjects(enumMap);
		logger.debug("商品詳細の初期表示処理を終了します。");
		return result;
	}
	
	  /**
	   * <pre>
	   * messageInit。
	   * </pre>
	   * 
	   * @param request HttpServletRequest
	   * @param response HttpServletResponse
	   * @return WebResult
	   */
	  @ResponseBody
	  @Submission(multiply = false)
	  @RequestMapping(path = "/messageInit", method = RequestMethod.POST)
	  public WebResult messageInit(HttpServletRequest request, HttpServletResponse response) {
	    logger.debug("商品詳細のメッセージ取得処理を開始します。");
	    WebResult result = new WebResult();
	    /** MessageMap **/
        HashMap<String, String> messageMap = new HashMap<>();
        goodsMasterFacade.getMessage(messageMap);
	    // messageMap
	    result.put("messageMap", messageMap);
	    logger.debug("商品詳細のメッセージ取得処理を終了します。");
	    return result;
	  }
	
	
	  /**
	   * <pre>
	   * goodsMGroup。
	   * </pre>
	   * 
	   * @param form ClassifyCodeGuideForm
	   * @param request HttpServletRequest
	   * @param response HttpServletResponse
	   * @return WebResult
	   */
	  @ResponseBody
	  @Submission(multiply = false)
	  @RequestMapping(path = "/goodsMGroup", method = RequestMethod.POST)
	  public WebResult goodsMGroup(@RequestBody ProductListForm form, HttpServletRequest request,
	                               HttpServletResponse response) {
	    logger.debug("商品詳細の商品中分類コードガイドモード設定処理を開始します。");
	    WebResult result = new WebResult();
	    HttpSession session = request.getSession();
	    session.setAttribute(BroadleafSessionKeys.CLASSIFY_CODE_GUIDE, form.getGoodsMGroup());
	    logger.debug("商品詳細の商品中分類コードガイドモード設定処理を終了します。");
	    return result;
	  }

	/**
	 * <pre>
	 * 商品詳細取得。
	 * </pre>
	 * 
	 * @param form ProductListForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return WebResult
	 */
	@RequestMapping(path = "/item", method = RequestMethod.POST)
	@ResponseBody
	@Submission(multiply = false)
	public WebResult getGoodsDetailData(@RequestBody ProductListForm form, HttpServletRequest request,
			HttpServletResponse response) {
	  logger.debug("商品詳細の商品詳細取得処理を開始します。");
		WebResult result = new WebResult();
		try {
			goodsDetailData = form.getGoodsGridDtoList().get(0);
		} catch (Throwable ex) {
			handleException(ex, result);
		}
		logger.debug("商品詳細の商品詳細取得処理を終了します。");
		return result;
	}

	/**
	 * <pre>
	 * Data初期表示時。
	 * </pre>
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return WebResult
	 */
	@RequestMapping(path = "/dateInit", method = RequestMethod.POST)
	@ResponseBody
	@Submission(multiply = false)
	public WebResult goodsDataInit(@RequestBody int mode, HttpServletRequest request, HttpServletResponse response) {
	    logger.debug("商品詳細のデータ初期表示処理を開始します。");
		WebResult result = new WebResult();
		ModeEnum modeEnum = ModeEnum.valueof(mode);
		GoodsDetailDto goodsDetailDto = new GoodsDetailDto();		
		try {
			switch (modeEnum) {
			case Input:
				goodsDetailDto = dateNew(goodsDetailDto);
				break;
			case Select:
			case Reference:	
			case Error:
				goodsDetailDto = dateInit(goodsDetailDto);
				break;
			default:
				break;
			}
			result.put("makerCode", makerCode);
			result.put("inputData", goodsDetailDto);
		} catch (Throwable ex) {
			handleException(ex, result);
		}
		logger.debug("商品詳細のデータ初期表示処理を終了します。");
		return result;
	}
	
	/**
	 * 新規Data初期表示時。
	 * 
	 * @param goodsDetailDto GoodsDetailDto
	 * @return goodsDetailDto
	 */
	private GoodsDetailDto dateNew(GoodsDetailDto goodsDetailDto) {
		// 処理区分
		goodsDetailDto.setDealDistinction(
				ManageKbnEnum.valueof(ManageKbnEnum.getCode(goodsDetailData.getManageSec())).getName());
		// 優良設定詳細コード１
//		goodsDetailDto.setSelectCd(getKey(goodsDetailData.getSelCode()));
		// 商品中分類コード
//		goodsDetailDto.setClassifyCd(goodsDetailData.getSecCodeName());
		// BLコード
//		goodsDetailDto.setBlCd(getKey(goodsDetailData.getBlCodeName()));
		// 優良品番(－付き品番)
//		goodsDetailDto.setExceProNo(goodsDetailData.getGoodsNo());
		// 優良部品カナ名称
//		goodsDetailDto.setProNameHalf(goodsDetailData.getNameS());
		// 優良部品名称
//		goodsDetailDto.setProNameFull(goodsDetailData.getNameB());
		// 新価格
//		goodsDetailDto.setPrice(goodsDetailData.getMoney());
		// オープン価格区分
//		goodsDetailDto.setOpenPrice(String.valueOf(OpenPriceDivEnum.getCode(goodsDetailData.getOpen())));
		// JAN
//		goodsDetailDto.setJan(goodsDetailData.getJan());
		// 層別コード
//		goodsDetailDto.setClassify(getKey(goodsDetailData.getLayer()));
		// 装備名称
//		goodsDetailDto.setEquipment(goodsDetailData.getEquip());
		// 優良部品規格・特記事項(B向け)
//		goodsDetailDto.setSpecialMatter(goodsDetailData.getSize());
		// 優良部品規格・特記事項(C向け)
//		goodsDetailDto.setSpecialMatterCommon(goodsDetailData.getSizeCon());
		// 商品詳細(B向け)
//		goodsDetailDto.setProductDetail(goodsDetailData.getGoods());
		// 商品詳細(C向け)
//		goodsDetailDto.setProductDetailCommon(goodsDetailData.getGoodsCon());
		// URL1
//		goodsDetailDto.setUrl1(goodsDetailData.getUrl1());
		// URL2
//		goodsDetailDto.setUrl2(goodsDetailData.getUrl2());
		// URL3
//		goodsDetailDto.setUrl3(goodsDetailData.getUrl3());
		// 商品サイズ(長さ)
//		goodsDetailDto.setProLenA(goodsDetailData.getWidth1());
		// 商品サイズ(幅)
//		goodsDetailDto.setProLenB(goodsDetailData.getWidth2());
		// 商品サイズ(高さ)
//		goodsDetailDto.setProLenC(goodsDetailData.getWidth3());
		// 梱包サイズ(長さ)
//		goodsDetailDto.setPackLenA(goodsDetailData.getPackwidth1());
		// 梱包サイズ(幅)
//		goodsDetailDto.setPackLenB(goodsDetailData.getPackwidth2());
		// 梱包サイズ(高さ)
//		goodsDetailDto.setPackLenC(goodsDetailData.getPackwidth3());
		// サイズ単位
//		goodsDetailDto.setLengthUnit(String.valueOf(SizeUnitEnum.getCode(goodsDetailData.getWidthUnit())));
		// 商品重量
//		goodsDetailDto.setWeight(goodsDetailData.getWeight());
		// 重量単位
//		goodsDetailDto.setWeightUnit(String.valueOf(WeightUnitEnum.getCode(goodsDetailData.getWeightUnit())));
		// 作成日付
//		goodsDetailDto.setSetDate(goodsDetailData.getDateCom());
		// 更新日付
//		goodsDetailDto.setUpdateDate(goodsDetailData.getDateRe());
		// 適用日付
//		goodsDetailDto.setApplyDate(goodsDetailData.getDateSlice());
		// エラー区分
		goodsDetailDto
				.setErrorDistinction(ErrorFlgEnum.NoError.getAbbreviation());
		// 削除依頼区分
		goodsDetailDto
				.setDelDistinction(String.valueOf(DeleteFlgEnum.getCodeByAbbreviation(goodsDetailData.getDelSec())));
		// 削除理由
//		goodsDetailDto.setDelReason(goodsDetailData.getDelCon());
		// 申請状態
		goodsDetailDto.setApplyState(
				ApplyConditionEnum.valueof(ApplyConditionEnum.getCode(goodsDetailData.getApplyStep())).getName());
		return goodsDetailDto;
	}
	
	/**
	 * 更新Data初期表示時。
	 * 
	 * @param goodsDetailDto GoodsDetailDto
	 * @return goodsDetailDto
	 */
	private GoodsDetailDto dateInit(GoodsDetailDto goodsDetailDto) {
		// 処理区分
	  if(ManageKbnEnum.valueof(ManageKbnEnum.getCode(goodsDetailData.getManageSec())) != null){
	    goodsDetailDto.setDealDistinction(
            ManageKbnEnum.valueof(ManageKbnEnum.getCode(goodsDetailData.getManageSec())).getName());
	  }
		// 優良設定詳細コード１
		goodsDetailDto.setSelectCd(getKey(goodsDetailData.getSelCode()));
		// 商品中分類コード
		goodsDetailDto.setClassifyCd(goodsDetailData.getSecCodeName());
		// BLコード
		goodsDetailDto.setBlCd(getKey(goodsDetailData.getBlCodeName()));
		// 優良品番(－付き品番)
		goodsDetailDto.setExceProNo(goodsDetailData.getGoodsNo());
		// 優良部品カナ名称
		goodsDetailDto.setProNameHalf(goodsDetailData.getNameS());
		// 優良部品名称
		goodsDetailDto.setProNameFull(goodsDetailData.getNameB());
		// 新価格
		goodsDetailDto.setPrice(goodsDetailData.getMoney());
		// オープン価格区分
		goodsDetailDto.setOpenPrice(goodsDetailData.getOpen() == null 
				? null : String.valueOf(OpenPriceDivEnum.getCode(goodsDetailData.getOpen())));
		// JAN
		goodsDetailDto.setJan(goodsDetailData.getJan());
		// 層別コード
		goodsDetailDto.setClassify(getKey(goodsDetailData.getLayer()));
		// 装備名称
		goodsDetailDto.setEquipment(goodsDetailData.getEquip());
		// 優良部品規格・特記事項(B向け)
		goodsDetailDto.setSpecialMatter(goodsDetailData.getSize());
		// 優良部品規格・特記事項(C向け)
		goodsDetailDto.setSpecialMatterCommon(goodsDetailData.getSizeCon());
		// 商品詳細(B向け)
		goodsDetailDto.setProductDetail(goodsDetailData.getGoods());
		// 商品詳細(C向け)
		goodsDetailDto.setProductDetailCommon(goodsDetailData.getGoodsCon());
		// URL1
		goodsDetailDto.setUrl1(goodsDetailData.getUrl1());
		// URL2
		goodsDetailDto.setUrl2(goodsDetailData.getUrl2());
		// URL3
		goodsDetailDto.setUrl3(goodsDetailData.getUrl3());
		// 商品サイズ(長さ)
		goodsDetailDto.setProLenA(goodsDetailData.getWidth1());
		// 商品サイズ(幅)
		goodsDetailDto.setProLenB(goodsDetailData.getWidth2());
		// 商品サイズ(高さ)
		goodsDetailDto.setProLenC(goodsDetailData.getWidth3());
		// 梱包サイズ(長さ)
		goodsDetailDto.setPackLenA(goodsDetailData.getPackwidth1());
		// 梱包サイズ(幅)
		goodsDetailDto.setPackLenB(goodsDetailData.getPackwidth2());
		// 梱包サイズ(高さ)
		goodsDetailDto.setPackLenC(goodsDetailData.getPackwidth3());
		// サイズ単位
		goodsDetailDto.setLengthUnit(String.valueOf(SizeUnitEnum.getCode(goodsDetailData.getWidthUnit())));
		// 商品重量
		goodsDetailDto.setWeight(goodsDetailData.getWeight());
		// 重量単位
		goodsDetailDto.setWeightUnit(String.valueOf(WeightUnitEnum.getCode(goodsDetailData.getWeightUnit())));
		// 作成日付
		goodsDetailDto.setSetDate(goodsDetailData.getDateCom());
		// 更新日付
		goodsDetailDto.setUpdateDate(goodsDetailData.getDateRe());
		// 適用日付
		goodsDetailDto.setApplyDate(goodsDetailData.getDateSlice());
		// エラー区分
		if(ErrorFlgEnum.valueof(ErrorFlgEnum.getCode(goodsDetailData.getErrSec())).getAbbreviation() != null){
		  goodsDetailDto
          .setErrorDistinction(ErrorFlgEnum.valueof(ErrorFlgEnum.getCode(goodsDetailData.getErrSec())).getAbbreviation());
		}
		// 削除依頼区分
		goodsDetailDto
				.setDelDistinction(String.valueOf(DeleteFlgEnum.getCodeByAbbreviation(goodsDetailData.getDelSec())));
		// 削除理由
		goodsDetailDto.setDelReason(goodsDetailData.getDelCon());
		// 申請状態
		if(ApplyConditionEnum.valueof(ApplyConditionEnum.getCode(goodsDetailData.getApplyStep())) != null){
		  goodsDetailDto.setApplyState(
              ApplyConditionEnum.valueof(ApplyConditionEnum.getCode(goodsDetailData.getApplyStep())).getName());
		}
		return goodsDetailDto;
	}

	/**
	 * <pre>
	 * Img初期表示時。
	 * </pre>
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return WebResult
	 */
	@RequestMapping(path = "/imgInit", method = RequestMethod.POST)
	@ResponseBody
	@Submission(multiply = false)
	public WebResult goodsImgInit(HttpServletRequest request, HttpServletResponse response) {
	    logger.debug("商品詳細の画像初期表示処理を開始します。");
		WebResult result = new WebResult();
		String makerCodeString = String.valueOf(makerCode);
		String goodsNo = goodsDetailData.getGoodsNo();
		List<String> imgList = new ArrayList<>();
		try {
			for (int i = 0; i < 9; i++) {
				String fileName = makerCodeString.concat(LINE).concat(goodsNo).concat(LINE).concat(ZERO)
						.concat(String.valueOf(i)).concat(".jpg");
				String name = makerCodeString.concat(SLASH).concat(goodsNo).concat(SLASH).concat(fileName);
				if (StorageManager.getObject(BUCKET_NAME, name) != null) {
					imgList.add(name);
				}
			}
			result.put("inputImg", imgList);
		} catch (Throwable ex) {
			handleException(ex, result);
		}
		logger.debug("商品詳細の画像初期表示処理を終了します。");
		return result;
	}

	/**
	 * <pre>
	 * Data保存。
	 * </pre>
	 * 
	 * @param form GoodsDetailForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return WebResult
	 */
	@RequestMapping(path = "/goodsDetailSave", method = RequestMethod.POST)
	@ResponseBody
	@Submission(multiply = false)
	public WebResult goodsDataSave(@RequestBody GoodsDetailForm form, HttpServletRequest request,
			HttpServletResponse response) {
	  logger.debug("商品詳細のデータ保存処理を開始します。");
		WebResult result = new WebResult();
		ModeEnum modeEnum = ModeEnum.valueof(form.getGoodsDetaiMode());
		GoodsDetailDto goodsDetailDto = form.getGoodsDetailDto();
		int imageNum = form.getImageNum();
		try {
			switch (modeEnum) {
			case Input:
				saveNew(goodsDetailDto, imageNum, result);
				break;
			case Select:	
			case Error:
				saveUpdate(goodsDetailDto, imageNum, result ,modeEnum);				
				break;
			default:
				break;
			}

		} catch (Throwable ex) {
			handleException(ex, result);
		}
		logger.debug("商品詳細のデータ保存処理を終了します。");
		return result;
	}
	
	/**
	 * save新規。
	 * 
	 * @param goodsDetailDto GoodsDetailDto
	 * @param result WebResult
	 */
	private void saveNew(GoodsDetailDto goodsDetailDto, int imageNum, WebResult result) {
		GoodsMasterMakerDto itemMasterResult = convertGoodsMasterMakerDtoNew(goodsDetailDto, imageNum);
		GoodsGridDto goodsGridDto = convertGoodsGridDto(itemMasterResult, JudgeEnum.Add);
		List<GoodsGridDto> dataSourceList = new ArrayList<>();
		dataSourceList.add(goodsGridDto);
		// 項目チェック
		GoodsMasterMakerInfoDto goodsMasterInfoDto = goodsMasterFacade.checkImportList(dataSourceList,
				makerCode);
		Boolean isErrorExist = goodsMasterInfoDto.getIsErrorExist();
		Boolean isPriceZero = false;
		result.put("isErrorExist", isErrorExist);
		if (!isErrorExist) {
			itemMasterResult.setErrorFlg((short) ErrorFlgEnum.getCode(goodsGridDto.getErrSec()));
			itemMasterResult.setErrorDetail(goodsGridDto.getErrCon());
			List<GoodsMasterMakerDto> goodsGridDtoInsertList = new ArrayList<>();
			goodsGridDtoInsertList.add(itemMasterResult);
			goodsMasterInfoDto.setGoodsMasterDto(goodsGridDtoInsertList);
			goodsMasterFacade.insertGoodsMasterInfoList(goodsMasterInfoDto);
			goodsGridDto.setHiddenArea(JudgeEnum.UnChange.getValue());
			result.put("gridData", goodsGridDto);
			if (goodsGridDto.getErrSec().equals(ErrorFlgEnum.Error.getAbbreviation())) {
				isPriceZero = true;
				result.put("isPriceZero", isPriceZero);
				result.put("errorMsg", goodsGridDto.getErrCon());
			}					
		} else {
			result.put("errorMsg", goodsGridDto.getErrCon());
		}
	}
	
	/**
	 * save更新。
	 * 
	 * @param goodsDetailDto GoodsDetailDto
	 * @param result WebResult
	 */
	private void saveUpdate(GoodsDetailDto goodsDetailDto, int imageNum, WebResult result, ModeEnum modeEnum) {
		GoodsMasterMakerDto itemMasterResult = convertGoodsMasterMakerDto(goodsDetailDto, imageNum);
		GoodsGridDto goodsGridDto = convertGoodsGridDto(itemMasterResult, JudgeEnum.Update);
		List<GoodsGridDto> dataSourceList = new ArrayList<>();
		dataSourceList.add(goodsGridDto);
		// 項目チェック
		GoodsMasterMakerInfoDto goodsMasterInfoDto = goodsMasterFacade.checkImportList(dataSourceList,
				makerCode);
		Boolean isErrorExist = goodsMasterInfoDto.getIsErrorExist();
		Boolean isPriceZero = false;
		result.put("isErrorExist", isErrorExist);
		if (!isErrorExist) {
			itemMasterResult.setErrorFlg((short) ErrorFlgEnum.getCode(goodsGridDto.getErrSec()));
			itemMasterResult.setErrorDetail(goodsGridDto.getErrCon());
			switch (modeEnum) {
			case Select:
				goodsMasterFacade.updateGoodsMaster(itemMasterResult);
				break;
			case Error:
				goodsMasterWorkFacade.updateGoodsMaster(itemMasterResult);		
				break;
			default:
				break;
			}
			goodsGridDto.setHiddenArea(JudgeEnum.UnChange.getValue());
			result.put("gridData", goodsGridDto);
			if (goodsGridDto.getErrSec().equals(ErrorFlgEnum.Error.getAbbreviation())) {
				isPriceZero = true;
				result.put("isPriceZero", isPriceZero);
				result.put("errorMsg", goodsGridDto.getErrCon());
			}					
		} else {
			result.put("errorMsg", goodsGridDto.getErrCon());
		}
	}

	/**
	 * <pre>
	 * Img保存。
	 * </pre>
	 * 
	 * @param form GoodsImgForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return WebResult
	 */
	@RequestMapping(path = "/goodsImgSave", method = RequestMethod.POST)
	@ResponseBody
	@Submission(multiply = false)
	public WebResult goodsImgSave(@RequestBody GoodsImgForm form, HttpServletRequest request,
			HttpServletResponse response) {
	  logger.debug("商品詳細の画像保存処理を開始します。");
		WebResult result = new WebResult();
		String makerCodeString = String.valueOf(makerCode);
		String goodsNo = form.getGoodsCd();

		for (String delImg : form.getDelImgList()) {
			String name = makerCodeString.concat(SLASH).concat(goodsNo).concat(SLASH).concat(delImg);

			try {
				StorageManager.deleteObject(BUCKET_NAME, name);
			} catch (Throwable ex) {
				handleException(ex, result);
			}
		}

		int index = 0;
		for (String img : form.getImgSrcList()) {
			String base64Code = img.substring(img.split(",")[0].length());
			String fileName = form.getImgNameList().get(index);
			String filePath = TEMP_PATH + fileName;
			try {
				byte[] b = Base64.decodeBase64(base64Code);
				OutputStream out = new FileOutputStream(filePath);
				out.write(b);
				out.flush();
				out.close();
			} catch (Throwable ex) {
				handleException(ex, result);
			}
			String name = makerCodeString.concat(SLASH).concat(goodsNo).concat(SLASH).concat(fileName);
			File file = new File(filePath);
			try {
				StorageManager.uploadFile(name, CONTENT_TYPE, file, BUCKET_NAME);
				boolean success = file.delete();
				if (!success) {
					result.put("msg", "msg");
				}
				index++;
			} catch (Throwable ex) {
				handleException(ex, result);
			}
		}
		logger.debug("商品詳細の画像保存処理を終了します。");
		return result;
	}

	/**
	 * DecimalFormat。
	 * 
	 * @param kuBun String
	 * @return Key
	 */
	private String getKey(String kuBun) {
		if (kuBun == null) {
			return EMPTY;
		}
		return kuBun.split("：")[0];
	}

	/**
	 * DecimalFormat。
	 * 
	 * @param data String
	 * @return data
	 */
	private String getDecimalFormat(String data) {
		String newData = "";
		String[] dataArray = data.split("\\.");
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern("###,###,###");
		newData = newData + myformat.format(Integer.valueOf(dataArray[0])).toString();
		if (dataArray.length == 2) {
			newData = newData + "." + dataArray[1];
		}
		if (newData.indexOf(".") > 0) {
			newData = newData.replaceAll("0+?$", "");
			newData = newData.replaceAll("[.]$", "");
		}
		return newData;
	}
    
	/**
	 * GoodsMasterMakerDto新規。
	 * 
	 * @param goodsDetailDto GoodsDetailDto
	 * @return GoodsMasterMakerDto
	 */
	private GoodsMasterMakerDto convertGoodsMasterMakerDtoNew(GoodsDetailDto goodsDetailDto, int imageNum) {
		GoodsMasterMakerDto goodsMasterDto = new GoodsMasterMakerDto();
		// 部品メーカーコード
		goodsMasterDto.setPartsMakerCd(makerCode);
		// 作成アカウントID
		goodsMasterDto.setInsAccountId(loginId);
		// 更新アカウントID
		goodsMasterDto.setUpdAccountId(loginId);
		// 優良品番(－付き品番)
		goodsMasterDto.setPrimePartsNoWithH(goodsDetailDto.getExceProNo());
		// 優良設定詳細コード１
		goodsMasterDto.setPrmSetDtlNo1(goodsDetailDto.getSelectCd() == null || goodsDetailDto.getSelectCd().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getSelectCd()));
		// 申請状態
		goodsMasterDto.setApplyCondition((short) ApplyConditionEnum.getCode(goodsDetailDto.getApplyState()));
		//// BL登録区分
		goodsMasterDto.setBlEntryFlg((short) BlEntryFlgEnum.Unregistered.getValue());
		//// チェック区分
		goodsMasterDto.setCheckFlg((short) CheckFlgEnum.Checked.getValue());
		// 削除依頼区分
		goodsMasterDto.setDeleteFlg(Integer.valueOf(goodsDetailDto.getDelDistinction()).shortValue());
		// 削除理由
		goodsMasterDto.setDeleteReason(goodsDetailDto.getDelReason());
		// 装備名称
		goodsMasterDto.setEquipName(goodsDetailDto.getEquipment());
		//// エラー内容
//		goodsMasterDto.setErrorDetail(goodsDetailData.getErrCon());
		// エラー区分
		goodsMasterDto.setErrorFlg((short) ErrorFlgEnum.getCode(goodsDetailDto.getErrorDistinction()));
		// 商品詳細(B向け)
		goodsMasterDto.setGoodsDetailB(goodsDetailDto.getProductDetail());
		// 商品詳細(C向け)
		goodsMasterDto.setGoodsDetailC(goodsDetailDto.getProductDetailCommon());
		// 商品中分類コード
		goodsMasterDto.setGoodsMGroup(goodsDetailDto.getClassifyCd() == null || goodsDetailDto.getClassifyCd().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getClassifyCd().split("：")[0]));
		// 商品サイズ(長さ)
		goodsMasterDto.setGoodsSize1(goodsDetailDto.getProLenA() == null || goodsDetailDto.getProLenA().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getProLenA().replace(",", "")));
		// 商品サイズ(幅)
		goodsMasterDto.setGoodsSize2(goodsDetailDto.getProLenB() == null || goodsDetailDto.getProLenB().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getProLenB().replace(",", "")));
		// 商品サイズ(高さ)
		goodsMasterDto.setGoodsSize3(goodsDetailDto.getProLenC() == null || goodsDetailDto.getProLenC().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getProLenC().replace(",", "")));
		// 商品重量
		goodsMasterDto.setGoodsWeight(goodsDetailDto.getWeight() == null || goodsDetailDto.getWeight().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getWeight().replace(",", "")));
		//// 画像数
		goodsMasterDto.setImageNo((short) imageNum);
		// インポート区分
		goodsMasterDto.setImportKbn((short) ImportKbnEnum.ManualInput.getValue());
		// JAN
		goodsMasterDto.setJan(goodsDetailDto.getJan() == null || goodsDetailDto.getJan().isEmpty()
				? null : Long.valueOf(goodsDetailDto.getJan()));
		//// 処理区分
		goodsMasterDto.setManageKbn((short) ManageKbnEnum.getCode(goodsDetailDto.getDealDistinction()));
		// 新価格
		goodsMasterDto.setNewPrice(goodsDetailDto.getPrice() == null || goodsDetailDto.getPrice().isEmpty()
				? null : Double.valueOf(goodsDetailDto.getPrice().replace(",", "")));
		// オープン価格区分
		goodsMasterDto.setOpenPriceDiv(goodsDetailDto.getOpenPrice() == null || goodsDetailDto.getOpenPrice().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getOpenPrice()));
		// 梱包サイズ(長さ)
		goodsMasterDto.setPackageSize1(goodsDetailDto.getPackLenA() == null || goodsDetailDto.getPackLenA().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getPackLenA().replace(",", "")));
		// 梱包サイズ(幅)
		goodsMasterDto.setPackageSize2(goodsDetailDto.getPackLenB() == null || goodsDetailDto.getPackLenB().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getPackLenB().replace(",", "")));
		// 梱包サイズ(高さ)
		goodsMasterDto.setPackageSize3(goodsDetailDto.getPackLenC() == null || goodsDetailDto.getPackLenC().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getPackLenC().replace(",", "")));
		// 層別コード
		goodsMasterDto.setPartsLayerCd(goodsDetailDto.getClassify());
		// 優良部品カナ名称
		goodsMasterDto.setPrimePartsKanaNm(goodsDetailDto.getProNameHalf());
		// 優良部品名称
		goodsMasterDto.setPrimePartsName(goodsDetailDto.getProNameFull());
		// 優良部品規格・特記事項(C向け)
		goodsMasterDto.setPrimePartsSpecialNoteC(goodsDetailDto.getSpecialMatterCommon());
		// 優良部品規格・特記事項(B向け)
		goodsMasterDto.setPrimePartsSpecialNoteB(goodsDetailDto.getSpecialMatter());
		// サイズ単位
		goodsMasterDto.setSizeUnit(goodsDetailDto.getLengthUnit());
		// 適用日付
		goodsMasterDto.setStartDate(goodsDetailDto.getApplyDate() == null || goodsDetailDto.getApplyDate().isEmpty()
				? null : new Timestamp(
						BroadleafFormatUtils.stringToDate(goodsDetailDto.getApplyDate().substring(0, 16).replace("/", "-"),
								BroadleafFormatUtils.DATE_F005).getTime()));
		// URL1
		goodsMasterDto.setUrl1(goodsDetailDto.getUrl1());
		// URL2
		goodsMasterDto.setUrl2(goodsDetailDto.getUrl2());
		// URL3
		goodsMasterDto.setUrl3(goodsDetailDto.getUrl3());
		// 重量単位
		goodsMasterDto.setWeightUnit(goodsDetailDto.getWeightUnit());
		// BLコード
		goodsMasterDto.setTbsPartsCode(goodsDetailDto.getBlCd() == null || goodsDetailDto.getBlCd().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getBlCd()));

		return goodsMasterDto;
	}
	
	/**
	 * GoodsMasterMakerDto更新。
	 * 
	 * @param goodsDetailDto GoodsDetailDto
	 * @return GoodsMasterMakerDto
	 */
	private GoodsMasterMakerDto convertGoodsMasterMakerDto(GoodsDetailDto goodsDetailDto, int imageNum) {
		GoodsMasterMakerDto goodsMasterDto = new GoodsMasterMakerDto();
		// 部品メーカーコード
		goodsMasterDto.setPartsMakerCd(makerCode);
		// 作成アカウントID
		goodsMasterDto.setInsAccountId(loginId);
		// 更新アカウントID
		goodsMasterDto.setUpdAccountId(loginId);
		// 優良品番(－付き品番)
		goodsMasterDto.setPrimePartsNoWithH(goodsDetailDto.getExceProNo());
		// 優良設定詳細コード１
		goodsMasterDto.setPrmSetDtlNo1(goodsDetailDto.getSelectCd() == null || goodsDetailDto.getSelectCd().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getSelectCd()));
		// 申請状態
		if (goodsDetailDto.getApplyState().equals(ApplyConditionEnum.Approval.getName())) {
			goodsMasterDto.setApplyCondition((short) ApplyConditionEnum.Applyagain.getValue());
		} else {
			goodsMasterDto.setApplyCondition((short) ApplyConditionEnum.getCode(goodsDetailDto.getApplyState()));
		}
		//// BL登録区分
		goodsMasterDto.setBlEntryFlg((short) BlEntryFlgEnum.getCode(goodsDetailData.getBlSec()));
		//// チェック区分
		goodsMasterDto.setCheckFlg((short) CheckFlgEnum.getCode(goodsDetailData.getCheckSec()));
		// 削除依頼区分
		goodsMasterDto.setDeleteFlg(Integer.valueOf(goodsDetailDto.getDelDistinction()).shortValue());
		// 削除理由
		goodsMasterDto.setDeleteReason(goodsDetailDto.getDelReason());
		// 装備名称
		goodsMasterDto.setEquipName(goodsDetailDto.getEquipment());
		//// エラー内容
		goodsMasterDto.setErrorDetail(goodsDetailData.getErrCon());
		// エラー区分
		goodsMasterDto.setErrorFlg((short) ErrorFlgEnum.getCode(goodsDetailDto.getErrorDistinction()));
		// 商品詳細(B向け)
		goodsMasterDto.setGoodsDetailB(goodsDetailDto.getProductDetail());
		// 商品詳細(C向け)
		goodsMasterDto.setGoodsDetailC(goodsDetailDto.getProductDetailCommon());
		// 商品中分類コード
		goodsMasterDto.setGoodsMGroup(goodsDetailDto.getClassifyCd() == null || goodsDetailDto.getClassifyCd().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getClassifyCd().split("：")[0]));
		// 商品サイズ(長さ)
		goodsMasterDto.setGoodsSize1(goodsDetailDto.getProLenA() == null || goodsDetailDto.getProLenA().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getProLenA().replace(",", "")));
		// 商品サイズ(幅)
		goodsMasterDto.setGoodsSize2(goodsDetailDto.getProLenB() == null || goodsDetailDto.getProLenB().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getProLenB().replace(",", "")));
		// 商品サイズ(高さ)
		goodsMasterDto.setGoodsSize3(goodsDetailDto.getProLenC() == null || goodsDetailDto.getProLenC().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getProLenC().replace(",", "")));
		// 商品重量
		goodsMasterDto.setGoodsWeight(goodsDetailDto.getWeight() == null || goodsDetailDto.getWeight().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getWeight().replace(",", "")));
		//// 画像数
		goodsMasterDto.setImageNo((short) imageNum);
		// インポート区分
		goodsMasterDto.setImportKbn(goodsDetailData.getImportKbn() == null
				? (short) ImportKbnEnum.ManualInput.getValue() : goodsDetailData.getImportKbn().shortValue());
		// JAN
		goodsMasterDto.setJan(goodsDetailDto.getJan() == null || goodsDetailDto.getJan().isEmpty()
				? null : Long.valueOf(goodsDetailDto.getJan()));
		//// 処理区分
		goodsMasterDto.setManageKbn((short) ManageKbnEnum.getCode(goodsDetailDto.getDealDistinction()));
		// 新価格
		goodsMasterDto.setNewPrice(goodsDetailDto.getPrice() == null || goodsDetailDto.getPrice().isEmpty()
				? null : Double.valueOf(goodsDetailDto.getPrice().replace(",", "")));
		// オープン価格区分
		goodsMasterDto.setOpenPriceDiv(goodsDetailDto.getOpenPrice() == null || goodsDetailDto.getOpenPrice().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getOpenPrice()));
		// 梱包サイズ(長さ)
		goodsMasterDto.setPackageSize1(goodsDetailDto.getPackLenA() == null || goodsDetailDto.getPackLenA().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getPackLenA().replace(",", "")));
		// 梱包サイズ(幅)
		goodsMasterDto.setPackageSize2(goodsDetailDto.getPackLenB() == null || goodsDetailDto.getPackLenB().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getPackLenB().replace(",", "")));
		// 梱包サイズ(高さ)
		goodsMasterDto.setPackageSize3(goodsDetailDto.getPackLenC() == null || goodsDetailDto.getPackLenC().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getPackLenC().replace(",", "")));
		// 層別コード
		goodsMasterDto.setPartsLayerCd(goodsDetailDto.getClassify());
		// 優良部品カナ名称
		goodsMasterDto.setPrimePartsKanaNm(goodsDetailDto.getProNameHalf());
		// 優良部品名称
		goodsMasterDto.setPrimePartsName(goodsDetailDto.getProNameFull());
		// 優良部品規格・特記事項(C向け)
		goodsMasterDto.setPrimePartsSpecialNoteC(goodsDetailDto.getSpecialMatterCommon());
		// 優良部品規格・特記事項(B向け)
		goodsMasterDto.setPrimePartsSpecialNoteB(goodsDetailDto.getSpecialMatter());
		// サイズ単位
		goodsMasterDto.setSizeUnit(goodsDetailDto.getLengthUnit());
		// 適用日付
		goodsMasterDto.setStartDate(goodsDetailDto.getApplyDate() == null || goodsDetailDto.getApplyDate().isEmpty()
				? null : new Timestamp(
						BroadleafFormatUtils.stringToDate(goodsDetailDto.getApplyDate().substring(0, 16).replace("/", "-"),
								BroadleafFormatUtils.DATE_F005).getTime()));
		// URL1
		goodsMasterDto.setUrl1(goodsDetailDto.getUrl1());
		// URL2
		goodsMasterDto.setUrl2(goodsDetailDto.getUrl2());
		// URL3
		goodsMasterDto.setUrl3(goodsDetailDto.getUrl3());
		// 重量単位
		goodsMasterDto.setWeightUnit(goodsDetailDto.getWeightUnit());
		// BLコード
		goodsMasterDto.setTbsPartsCode(goodsDetailDto.getBlCd() == null || goodsDetailDto.getBlCd().isEmpty()
				? null : Integer.valueOf(goodsDetailDto.getBlCd()));

		return goodsMasterDto;
	}

	/**
	 * gridのdata
	 * 
	 * @param goodsMasterMakerDto GoodsMasterMakerDto
	 * @param judgeEnum JudgeEnum
	 * @return GoodsGridDto
	 */
	private GoodsGridDto convertGoodsGridDto(GoodsMasterMakerDto goodsMasterMakerDto, JudgeEnum judgeEnum) {
		GoodsGridDto goodsGridDto = new GoodsGridDto();
		// 申請状態
		goodsGridDto.setApplyStep(ApplyConditionEnum.valueof(goodsMasterMakerDto.getApplyCondition()) == null ? EMPTY
				: ApplyConditionEnum.valueof(goodsMasterMakerDto.getApplyCondition()).getAbbreviation());
		// BLコード
		goodsGridDto.setBlCodeName(blCodeNameMap.get(String.valueOf(goodsMasterMakerDto.getTbsPartsCode())));
		// BL登録区分
		goodsGridDto.setBlSec(BlEntryFlgEnum.valueof(goodsMasterMakerDto.getBlEntryFlg()) == null ? EMPTY
				: BlEntryFlgEnum.valueof(goodsMasterMakerDto.getBlEntryFlg()).getAbbreviation());
		// チェック区分
		goodsGridDto.setCheckSec(CheckFlgEnum.valueof(goodsMasterMakerDto.getCheckFlg()) == null ? EMPTY
				: CheckFlgEnum.valueof(goodsMasterMakerDto.getCheckFlg()).getAbbreviation());
		// 適用日
		goodsGridDto.setDateSlice(goodsMasterMakerDto.getStartDate() == null ? EMPTY
				: BroadleafFormatUtils.timestampToString(goodsMasterMakerDto.getStartDate(), BroadleafFormatUtils.DATE_F004));
		// 削除理由
		goodsGridDto.setDelCon(goodsMasterMakerDto.getDeleteReason());
		// 削除依頼区分
		goodsGridDto.setDelSec(goodsMasterMakerDto.getDeleteFlg() == null ? EMPTY
				: DeleteFlgEnum.valueof(goodsMasterMakerDto.getDeleteFlg()).getAbbreviation());
		// 装備
		goodsGridDto.setEquip(goodsMasterMakerDto.getEquipName());
		// エラー内容
		goodsGridDto.setErrCon(goodsMasterMakerDto.getErrorDetail());
		// エラー区分
		goodsGridDto.setErrSec(ErrorFlgEnum.valueof(goodsMasterMakerDto.getErrorFlg()) == null ? EMPTY
				: ErrorFlgEnum.valueof(goodsMasterMakerDto.getErrorFlg()).getAbbreviation());
		// 商品詳細
		goodsGridDto.setGoods(goodsMasterMakerDto.getGoodsDetailB());
		// 商品詳細（一般）
		goodsGridDto.setGoodsCon(goodsMasterMakerDto.getGoodsDetailC());
		// 優良品番
		goodsGridDto.setGoodsNo(goodsMasterMakerDto.getPrimePartsNoWithH());
		// 画像有無
		goodsGridDto
				.setImg(goodsMasterMakerDto.getImageNo() == 0 ? "×" : String.valueOf(goodsMasterMakerDto.getImageNo()));
		// JAN
		goodsGridDto.setJan(goodsMasterMakerDto.getJan() == null ? null : String.valueOf(goodsMasterMakerDto.getJan()));
		// 層別
		goodsGridDto.setLayer(partsNameMap.get(goodsMasterMakerDto.getPartsLayerCd()));
		// 処理区分
		goodsGridDto.setManageSec(ManageKbnEnum.valueof(goodsMasterMakerDto.getManageKbn()) == null ? EMPTY
				: ManageKbnEnum.valueof(goodsMasterMakerDto.getManageKbn()).getAbbreviation());
		// 価格
		goodsGridDto.setMoney(goodsMasterMakerDto.getNewPrice() == null ? null
				: getDecimalFormat(goodsMasterMakerDto.getNewPrice().toString()));
		// 品名（全角）
		goodsGridDto.setNameB(goodsMasterMakerDto.getPrimePartsName());
		// 品名（半角）
		goodsGridDto.setNameS(goodsMasterMakerDto.getPrimePartsKanaNm());
		// OPENプランス
		if (goodsMasterMakerDto.getOpenPriceDiv() != null) {
			goodsGridDto.setOpen(OpenPriceDivEnum.valueof(goodsMasterMakerDto.getOpenPriceDiv()) == null ? null
					: OpenPriceDivEnum.valueof(goodsMasterMakerDto.getOpenPriceDiv()).getName());
		} else {
			goodsGridDto.setOpen(null);
		}
		// 梱包（長さ）
		goodsGridDto.setPackwidth1(goodsMasterMakerDto.getPackageSize1() == null ? null
				: getDecimalFormat(goodsMasterMakerDto.getPackageSize1().toString()));
		// 梱包（幅）
		goodsGridDto.setPackwidth2(goodsMasterMakerDto.getPackageSize2() == null ? null
				: getDecimalFormat(goodsMasterMakerDto.getPackageSize2().toString()));
		// 梱包（高さ）
		goodsGridDto.setPackwidth3(goodsMasterMakerDto.getPackageSize3() == null ? null
				: getDecimalFormat(goodsMasterMakerDto.getPackageSize3().toString()));
		// 分類コード
		goodsGridDto.setSecCodeName(goodsMasterMakerDto.getGoodsMGroup() == null ? null
				: classifyGuideNameMap.get(String.valueOf(goodsMasterMakerDto.getGoodsMGroup())));
		// セレクトコード名称
		if (goodsMasterMakerDto.getPrmSetDtlNo1() != null) {
			goodsGridDto.setSelCode(goodsMasterMakerDto.getPrmSetDtlNo1() == 9999 ? "9999：指定無し"
					: selectCodeNameMap.get(String.valueOf(goodsMasterMakerDto.getPrmSetDtlNo1())));
		} else {
			goodsGridDto.setSelCode(null);
		}
		// 規格/特記
		goodsGridDto.setSize(goodsMasterMakerDto.getPrimePartsSpecialNoteB());
		// 規格/特記（一般）
		goodsGridDto.setSizeCon(goodsMasterMakerDto.getPrimePartsSpecialNoteC());
		// URL1
		goodsGridDto.setUrl1(goodsMasterMakerDto.getUrl1());
		// URL2
		goodsGridDto.setUrl2(goodsMasterMakerDto.getUrl2());
		// URL3
		goodsGridDto.setUrl3(goodsMasterMakerDto.getUrl3());
		// 重量
		goodsGridDto.setWeight(goodsMasterMakerDto.getGoodsWeight() == null ? null
				: getDecimalFormat(goodsMasterMakerDto.getGoodsWeight().toString()));
		// 重量単位
		goodsGridDto.setWeightUnit(WeightUnitEnum.valueof(goodsMasterMakerDto.getWeightUnit()).getName());
		// 長さ
		goodsGridDto.setWidth1(goodsMasterMakerDto.getGoodsSize1() == null ? null
				: getDecimalFormat(goodsMasterMakerDto.getGoodsSize1().toString()));
		// 幅
		goodsGridDto.setWidth2(goodsMasterMakerDto.getGoodsSize2() == null ? null
				: getDecimalFormat(goodsMasterMakerDto.getGoodsSize2().toString()));
		// 高さ
		goodsGridDto.setWidth3(goodsMasterMakerDto.getGoodsSize3() == null ? null
				: getDecimalFormat(goodsMasterMakerDto.getGoodsSize3().toString()));
		// サイズ単位
		goodsGridDto.setWidthUnit(SizeUnitEnum.valueof(goodsMasterMakerDto.getSizeUnit()).getName());
		// 隠匿域
		goodsGridDto.setHiddenArea(judgeEnum.getValue());
		// インポート区分
	    goodsGridDto.setImportKbn(Integer.valueOf(goodsMasterMakerDto.getImportKbn()));

		return goodsGridDto;
	}
}
