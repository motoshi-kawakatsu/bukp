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
package jp.co.broadleaf.breg.web.setdetail.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jp.co.broadleaf.breg.common.dto.BlCodeMasterDto;
import jp.co.broadleaf.breg.common.dto.SelectCodeMasterDto;
import jp.co.broadleaf.breg.common.enums.ApplyConditionEnum;
import jp.co.broadleaf.breg.common.enums.DeleteFlgEnum;
import jp.co.broadleaf.breg.common.enums.ErrorFlgEnum;
import jp.co.broadleaf.breg.common.enums.ManageKbnEnum;
import jp.co.broadleaf.breg.setdetail.facade.SetDetailFacade;
import jp.co.broadleaf.breg.setlist.facade.dto.SetMasterDto;
import jp.co.broadleaf.breg.web.setdetail.form.SetDetailSerchForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.web.BroadleafSessionKeys;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.WebResult;

/**
 * <pre>
 * セート詳細のControllerクラス。
 * </pre>
 */
@Controller
@RequestMapping("/setdetail")
public class SetDetailController extends BaseController {

	/** BLコード */
	private static final String BLCODE = "blcodeinfo";

	/** セレクトコード */
	private static final String SELECTERCODE = "selecterCode";

	/** モード */
	private static final String MODE = "mode";

	/** セート詳細画面のURL */
	private static final String SET_DETAIL_URL = "setdetail/setdetail";

	/** 選択モード */
	private static final String UPDATE_MODE = "1";

	/** 検索入力モード */
	private static final String ADD_MODE = "0";

	/** 参照モード */
	private static final String REFERENCE_MODE = "2";

	/** エラー修正モード */
	private static final String ERROR_MODE = "3";

	/** 初期表示時のリスト */
	private static final String INIT_LIST = "initList";
	/**
	 * 処理区分の変数名
	 */
	private static final String MANAGEKBN = "manageKbn";
	/**
	 * エラー区分の変数名
	 */
	private static final String ERRORFLAG = "errorFlg";
	/**
	 * 削除依頼区分の変数名
	 */
	private static final String DELETEFLAG = "deleteFlg";
	/**
	 * 申請状態の変数名
	 */
	private static final String APPLYCONDITION = "applyCondition";

	/**
	 * <pre>
	 * 初期表示時。
	 * </pre>
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/setdetail", method = RequestMethod.GET)
	public ModelAndView setDetailInit(HttpServletRequest request, HttpServletResponse response) {
	    logger.debug("セット詳細の初期表示処理を開始します。");
		ModelAndView result = new ModelAndView(SET_DETAIL_URL);
		LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();

		// BLコードマスタを取得します。
		List<BlCodeMasterDto> blCodeInfo = setDetailFacade.getBlCodeInfo(loginPrincipal.getMakerCode());

		// セレクトコード情報を取得
		List<SelectCodeMasterDto> selectCodeInfo = setDetailFacade.getSelectCodeInfo(loginPrincipal.getMakerCode());

		result.addObject(BLCODE, blCodeInfo);
		result.addObject(SELECTERCODE, selectCodeInfo);
		result.addObject(MANAGEKBN, ManageKbnEnum.values());
		result.addObject(ERRORFLAG, ErrorFlgEnum.values());
		result.addObject(DELETEFLAG, DeleteFlgEnum.values());
		result.addObject(APPLYCONDITION, ApplyConditionEnum.values());
		logger.debug("セット詳細の初期表示処理を終了します。");
		return result;
	}

	/**
	 * <pre>
	 * グリッド初期表示時。
	 * </pre>
	 * 
	 * @param form
	 *            セート詳細検索用フォーム
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/gridinit", method = RequestMethod.POST)
	@ResponseBody
	public WebResult setDetalGridInit(@RequestBody SetDetailSerchForm form, HttpServletRequest request,
			HttpServletResponse response) {
	    logger.debug("セット詳細のグリッド初期表示処理を開始します。");
		WebResult result = new WebResult();
		List<SetMasterDto> list = new ArrayList<>();
		try {
			// 更新モード、参照モードの場合
			if (UPDATE_MODE.equals(form.getMode()) || ADD_MODE.equals(form.getMode())
					|| REFERENCE_MODE.equals(form.getMode())) {
				// 項目チェック
				if (isLegal(form)) {
					// 初期のリストを取得します。
					list = setDetailFacade.getSetDetail(form.getPrmSetDtlNo1().split("：")[0],
							form.getPartsMakerCd(), form.getGoodsMGroup() == null || form.getGoodsMGroup().isEmpty()
									? null : form.getGoodsMGroup().split("：")[0],
							form.getSetMainPartsNo().split("：")[0]);
					// 初期のリストセッションにセートします
					request.getSession().setAttribute(BroadleafSessionKeys.SET_DETAIL_INIT_GRID, list);
				} 
			}
			// エラー修正モード
			if (ERROR_MODE.equals(form.getMode())) {
				// 項目チェック
				if (isLegal(form)) {
					// 初期のリストを取得します。
					list = setDetailFacade.getSetMasterWorkList(form.getPrmSetDtlNo1().split("：")[0],
							form.getPartsMakerCd(), form.getGoodsMGroup() == null || form.getGoodsMGroup().isEmpty()
									? null : form.getGoodsMGroup().split("：")[0],
							form.getSetMainPartsNo().split("：")[0]);
					// 初期のリストセッションにセートします
					request.getSession().setAttribute(BroadleafSessionKeys.SET_DETAIL_INIT_GRID, list);
				} 
			}
			result.put(MODE, form.getMode());
			result.put(INIT_LIST, list);
		} catch (Exception e) {
			handleException(e, result);
		}
		logger.debug("セット詳細のグリッド初期表示処理を終了します。");
		return result;
	}

	/** セット詳細Facade */
	private SetDetailFacade setDetailFacade;

	/**
	 * セット詳細FacadeのSetメッソード.
	 * 
	 * @param setDetailFacade
	 *            セット詳細Facade
	 */
	@Resource
	public void setSetDetailFacade(SetDetailFacade setDetailFacade) {
		this.setDetailFacade = setDetailFacade;
	}

	/**
	 * 項目チェックします
	 * 
	 * @param form
	 *            セート詳細検索用フォーム
	 * @return true:チェック通過 false:チェック不通過
	 */
	private boolean isLegal(SetDetailSerchForm form) {

		if (null == form.getPartsMakerCd() || form.getPartsMakerCd().isEmpty()) {
			return false;
		}
		if (null == form.getPrmSetDtlNo1() || form.getPrmSetDtlNo1().isEmpty()) {
			return false;
		}
		if (null == form.getSetMainPartsNo() || form.getSetMainPartsNo().isEmpty()) {
			return false;
		}

		return true;
	}

}
