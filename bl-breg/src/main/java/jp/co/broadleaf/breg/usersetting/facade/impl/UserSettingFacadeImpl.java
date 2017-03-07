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
package jp.co.broadleaf.breg.usersetting.facade.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.entity.CertifInfoCommon;
import jp.co.broadleaf.breg.common.entity.UserMasterCommon;
import jp.co.broadleaf.breg.loginmaker.service.UserMasterPwdService;
import jp.co.broadleaf.breg.loginmaker.service.UserMasterService;
import jp.co.broadleaf.breg.message.service.MessageService;
import jp.co.broadleaf.breg.usersetting.facade.UserSettingFacade;
import jp.co.broadleaf.breg.usersetting.facade.dto.UserSettingDto;
import jp.co.broadleaf.breg.usersetting.facade.dto.UserSettingPwdDto;
import jp.co.broadleaf.breg.usersetting.service.UserSettingService;

/**
 * <pre>
 * UserSettingFacadeImplのクラス.
 * </pre>
 */
public class UserSettingFacadeImpl implements UserSettingFacade {

	/**
	 * 担当者情報取得
	 * 
	 * @param loginId ログインID
	 * @param makerCode メーカーコード
	 * @return 担当者情報
	 */
	@Override
	public UserSettingDto searchUserInfoByLoginInfo(String loginId, int makerCode) {

		UserMasterCommon userMasterCommon = userMasterService.getUserMaster(makerCode, loginId);
		UserSettingDto userSettingDto = new UserSettingDto();
		if (null != userMasterCommon) {
			userSettingDto.setLoginId(userMasterCommon.getLoginId());
			userSettingDto.setMakerCode(userMasterCommon.getMakerCode());
			userSettingDto.setUserName(userMasterCommon.getUserName());
			userSettingDto.setUserKana(userMasterCommon.getUserKana());
			userSettingDto.setDepartmentName(userMasterCommon.getDepartmentName());
			userSettingDto.setItem(userMasterCommon.getItem());
			userSettingDto.setPostCode(userMasterCommon.getPostCode());
			userSettingDto.setAddress(userMasterCommon.getAddress());
			userSettingDto.setMailAdd(userMasterCommon.getMailAdd());
			userSettingDto.setTelNo(userMasterCommon.getTelNo());
			userSettingDto.setFaxNo(userMasterCommon.getFaxNo());
			userSettingDto.setUserAdminFlag(userMasterCommon.getUserAdminFlag());
			userSettingDto.setNote(userMasterCommon.getNote());
		}
		return userSettingDto;
	}

	/**
	 * パスワード取得
	 * 
	 * @param loginId ログインID
	 * @param makerCode メーカーコード
	 * @return userSettingPwdDto
	 */
	@Override
	public UserSettingPwdDto searchPwdByLoginPwd(String loginId, int makerCode) {
		CertifInfoCommon certifInfoCommon = userMasterPwdService.getCertifInfo(makerCode, loginId);

		UserSettingPwdDto userSettingPwdDto = new UserSettingPwdDto();

		if (null != certifInfoCommon) {
			userSettingPwdDto.setLoginPwd(certifInfoCommon.getLoginPwd());
		}
		return userSettingPwdDto;
	}

	/**
	 * ユーザー情報保存
	 * 
	 * @param userSettingDto 担当者情報Dto
	 * @param userSettingPwdDto パスワードDto
	 */
	@Override
	public void saveUserInfo(UserSettingDto userSettingDto, UserSettingPwdDto userSettingPwdDto) {
		
		if(userSettingDto.isPasswordChange()){
			UserMasterCommon userMasterCommon = new UserMasterCommon();
			CertifInfoCommon certifInfoCommon = new CertifInfoCommon();
			userMasterCommon.setLoginId(userSettingDto.getLoginId());
			userMasterCommon.setMakerCode(userSettingDto.getMakerCode());
			userMasterCommon.setUserName(userSettingDto.getUserName());
			userMasterCommon.setUserKana(userSettingDto.getUserKana());
			userMasterCommon.setDepartmentName(userSettingDto.getDepartmentName());
			userMasterCommon.setItem(userSettingDto.getItem());
			userMasterCommon.setPostCode(userSettingDto.getPostCode());
			userMasterCommon.setAddress(userSettingDto.getAddress());
			userMasterCommon.setMailAdd(userSettingDto.getMailAdd());
			userMasterCommon.setTelNo(userSettingDto.getTelNo());
			userMasterCommon.setFaxNo(userSettingDto.getFaxNo());
			userMasterCommon.setUserAdminFlag(userSettingDto.getUserAdminFlag());
			userMasterCommon.setNote(userSettingDto.getNote());
			certifInfoCommon.setLoginPwd(userSettingPwdDto.getLoginPwd());
			certifInfoCommon.setLoginId(userSettingDto.getLoginId());
	
			userSettingService.saveUserInfo(userMasterCommon, certifInfoCommon);
		}else{
			UserMasterCommon userMasterCommon = new UserMasterCommon();
			CertifInfoCommon certifInfoCommon = new CertifInfoCommon();
			userMasterCommon.setLoginId(userSettingDto.getLoginId());
			userMasterCommon.setMakerCode(userSettingDto.getMakerCode());
			userMasterCommon.setUserName(userSettingDto.getUserName());
			userMasterCommon.setUserKana(userSettingDto.getUserKana());
			userMasterCommon.setDepartmentName(userSettingDto.getDepartmentName());
			userMasterCommon.setItem(userSettingDto.getItem());
			userMasterCommon.setPostCode(userSettingDto.getPostCode());
			userMasterCommon.setAddress(userSettingDto.getAddress());
			userMasterCommon.setMailAdd(userSettingDto.getMailAdd());
			userMasterCommon.setTelNo(userSettingDto.getTelNo());
			userMasterCommon.setFaxNo(userSettingDto.getFaxNo());
			userMasterCommon.setUserAdminFlag(userSettingDto.getUserAdminFlag());
			userMasterCommon.setNote(userSettingDto.getNote());
	
			userSettingService.saveUserInfo(userMasterCommon, certifInfoCommon);
		}
	}

	/**
	 * UserMasterService
	 */
	private UserMasterService userMasterService;

	/**
	 * UserMasterServiceをセットする
	 * 
	 * @param userMasterService userMasterService
	 */
	@Resource
	public void setUserMasterService(UserMasterService userMasterService) {
		this.userMasterService = userMasterService;
	}

	/**
	 * UserMasterPwdService
	 */
	private UserMasterPwdService userMasterPwdService;

	/**
	 * UserMasterPwdServiceをセットする
	 * 
	 * @param userMasterPwdService userMasterPwdService
	 */
	@Resource
	public void setUserMasterPwdService(UserMasterPwdService userMasterPwdService) {
		this.userMasterPwdService = userMasterPwdService;
	}

	/**
	 * userSettingService
	 * 
	 */
	private UserSettingService userSettingService;

	/**
	 * 担当者情報サービスをセットする
	 * 
	 * @param userSettingService UserSettingService
	 */
	@Resource
	public void setUserSettingService(UserSettingService userSettingService) {
		this.userSettingService = userSettingService;
	}
		 
	  /** メッセージサービス*/
	  private MessageService messageService;

	  
	  /**
	   * メッセージサービスをセットする
	   * 
	   * @param messageService MessageService
	   */
	  @Resource
	  public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	  /**
	   * メッセージを取得
	   * 
	   * @return メッセージ
	   */
	  @Override
	  public List<String> getMessage() {
		List<String> msgList = new ArrayList<String>();
		msgList.add(messageService.messageInfo(BregMessageCodes.Q00001));
		msgList.add(messageService.messageInfo(BregMessageCodes.Q00002));
		msgList.add(messageService.messageInfo(BregMessageCodes.E00013));
		msgList.add(messageService.messageInfo(BregMessageCodes.I00001));
	    return msgList;
	  }  
  
}
