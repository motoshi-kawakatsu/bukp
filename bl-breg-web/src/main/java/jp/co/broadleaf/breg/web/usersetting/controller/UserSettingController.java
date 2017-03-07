//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:張　清詞
// 作成日:2017/02/13    修正内容:担当者情報:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.usersetting.controller;

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

import jp.co.broadleaf.breg.usersetting.facade.UserSettingFacade;
import jp.co.broadleaf.breg.usersetting.facade.dto.UserSettingDto;
import jp.co.broadleaf.breg.usersetting.facade.dto.UserSettingPwdDto;
import jp.co.broadleaf.breg.web.usersetting.form.UserSettingForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.Submission;
import jp.co.broadleaf.framework.web.WebResult;

/**
 * <pre>
 * UserSettingControllerクラス.
 * </pre>
 */
@Controller
@RequestMapping("/usersetting")
public class UserSettingController extends BaseController {

	/**
	 * <pre>
	 * 初期化表示。
	 * </pre>
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/usersetting", method = RequestMethod.GET)
	public ModelAndView userSettingInit(HttpServletRequest request, HttpServletResponse response) {
	  logger.debug("担当者情報の初期表示処理を開始します。");
	  ModelAndView result = new ModelAndView(USER_SETTING_URL);

		try {
			// ログイン情報を取得
			LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
			// ログインIDを取得
			String loginId = loginPrincipal.getLoginId();
			// メーカーコドの定義
			int makerCode = loginPrincipal.getMakerCode();

			// 初期担当者情報を取得
			UserSettingDto userSettingDto = userSettingFacade.searchUserInfoByLoginInfo(loginId, makerCode);
			UserSettingPwdDto userSettingPwdDto = userSettingFacade.searchPwdByLoginPwd(loginId, makerCode);
			List<String> msgList = userSettingFacade.getMessage();
			// メッセジを取得
			result.addObject("msgBack", msgList.get(0));
			result.addObject("msgSure", msgList.get(1));
			result.addObject("msgNothing", msgList.get(2));
			result.addObject("msgSave", msgList.get(3));
			// 初期担当者情報を取得
			result.getModel().put("loginId", loginId);
			result.getModel().put("userName", userSettingDto.getUserName());
			result.getModel().put("userKana", userSettingDto.getUserKana());
			result.getModel().put("departmentName", userSettingDto.getDepartmentName());
			result.getModel().put("item", userSettingDto.getItem());
			result.getModel().put("postCode", userSettingDto.getPostCode());
			result.getModel().put("address", userSettingDto.getAddress());
			result.getModel().put("mailAdd", userSettingDto.getMailAdd());
			result.getModel().put("telNo", userSettingDto.getTelNo());
			result.getModel().put("faxNo", userSettingDto.getFaxNo());

			// ユーザー種類のプルダウンメニューのデーターを取得
			int typeflag = userSettingDto.getUserAdminFlag();
			if (typeflag == 0) {
				result.getModel().put("userAdminFlagZeor", "selected");
				result.getModel().put("userAdminFlagOne", "");
				result.getModel().put("userAdminFlagTwo", "");
			} else if(typeflag == 1){
				result.getModel().put("userAdminFlagZeor", "");
				result.getModel().put("userAdminFlagOne", "selected");
				result.getModel().put("userAdminFlagTwo", "");
			}else if(typeflag == 2){
				result.getModel().put("userAdminFlagZeor", "");
				result.getModel().put("userAdminFlagOne", "");
				result.getModel().put("userAdminFlagTwo", "selected");
			}

			result.getModel().put("note", userSettingDto.getNote());
			result.getModel().put("loginPwd", userSettingPwdDto.getLoginPwd());
	        
		} catch (Throwable ex) {
			handleException(ex, result);
		}
		logger.debug("担当者情報の初期表示処理を終了します。");
		return result;
	}

	/**
	 * 保存ボタンをクリックする処理
	 * 
	 * @param form UserSettingForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return WebResult
	 */
	@RequestMapping(path = "/save", method = RequestMethod.POST)
	@ResponseBody
	@Submission(multiply = false)
	public WebResult userSetting(@RequestBody UserSettingForm form, HttpServletRequest request,
			HttpServletResponse response) {
	  logger.debug("担当者情報の保存処理を開始します。");
		WebResult result = new WebResult();

		try {
			
			if(form.isPasswordChange()){
				// パスワードを変える時の画面入力項目のチェック
				form.validate();
				
				// ログイン情報を取得
				LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
				// メーカーコドの定義
				int makerCode = loginPrincipal.getMakerCode();

				List<UserSettingDto> userSettingDtoList = new ArrayList<UserSettingDto>();
				UserSettingDto userSettingDto = new UserSettingDto();
				UserSettingPwdDto userSettingPwdDto = new UserSettingPwdDto();
				// 担当者情報を設定
				userSettingDto.setLoginId(form.getLoginId());
				userSettingDto.setMakerCode(makerCode);
				userSettingDto.setUserName(form.getUserName());
				userSettingDto.setUserKana(form.getUserKana());
				userSettingDto.setDepartmentName(form.getDepartmentName());
				userSettingDto.setItem(form.getItem());
				userSettingDto.setPostCode(form.getPostCode());
				userSettingDto.setAddress(form.getAddress());
				userSettingDto.setMailAdd(form.getMailAdd());
				userSettingDto.setTelNo(form.getTelNo());
				userSettingDto.setFaxNo(form.getFaxNo());
				userSettingDto.setUserAdminFlag(form.getUserAdminFlag());
				userSettingDto.setNote(form.getNote());
				userSettingPwdDto.setLoginPwd(form.getLoginPwd());
				userSettingDto.setPasswordChange(form.isPasswordChange());
				
				userSettingFacade.saveUserInfo(userSettingDto, userSettingPwdDto);
				result.put("UserSettingDtoList", userSettingDtoList);
							
			}else{
				// パスワードが不変時の画面入力項目のチェック
				form.validate2();
	
				// ログイン情報を取得
				LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
				// メーカーコドの定義
				int makerCode = loginPrincipal.getMakerCode();
	
				List<UserSettingDto> userSettingDtoList = new ArrayList<UserSettingDto>();
				UserSettingDto userSettingDto = new UserSettingDto();
				UserSettingPwdDto userSettingPwdDto = new UserSettingPwdDto();
				// 担当者情報を設定
				userSettingDto.setLoginId(form.getLoginId());
				userSettingDto.setMakerCode(makerCode);
				userSettingDto.setUserName(form.getUserName());
				userSettingDto.setUserKana(form.getUserKana());
				userSettingDto.setDepartmentName(form.getDepartmentName());
				userSettingDto.setItem(form.getItem());
				userSettingDto.setPostCode(form.getPostCode());
				userSettingDto.setAddress(form.getAddress());
				userSettingDto.setMailAdd(form.getMailAdd());
				userSettingDto.setTelNo(form.getTelNo());
				userSettingDto.setFaxNo(form.getFaxNo());
				userSettingDto.setUserAdminFlag(form.getUserAdminFlag());
				userSettingDto.setNote(form.getNote());
				userSettingDto.setPasswordChange(form.isPasswordChange());

				userSettingFacade.saveUserInfo(userSettingDto, userSettingPwdDto);
				result.put("UserSettingDtoList", userSettingDtoList);
				}
			
		} catch (Throwable ex) {
			handleException(ex, result);
		}
		logger.debug("担当者情報の保存処理を終了します。");
		return result;
	}

	/** 担当者情報ーFacade */
	private UserSettingFacade userSettingFacade;

	/** 担当者情報画面のURL */
	private static final String USER_SETTING_URL = "usersetting/usersetting";

	/**
	 * 担当者情報Facadeをセットする.
	 * 
	 * @param userSettingFacadeM userSettingFacade
	 */
	@Resource
	public void setUserSettingFacad(UserSettingFacade userSettingFacadeM) {
		this.userSettingFacade = userSettingFacadeM;
	}
}
