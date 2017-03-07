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
package jp.co.broadleaf.breg.web.usersetting.form;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.common.validation.UserSettingValidatorRule;
import jp.co.broadleaf.common.validation.UserSettingValidatorRuleTwo;
import jp.co.broadleaf.framework.validation.FormValidator;
import jp.co.broadleaf.framework.validation.FormValidatorFactory;

/**
 * 担当者情報Formのクラス.
 */
public class UserSettingForm {

	/**
	 * ログインID
	 */
	private String loginId;

	/**
	 * ユーザーName
	 */
	private String userName;

	/**
	 * ユーザーカナ
	 */
	private String userKana;

	/**
	 * デパートメントName
	 */
	private String departmentName;

	/**
	 * アイテム
	 */
	private String item;

	/**
	 * ポストコード
	 */
	private String postCode;

	/**
	 * アドレス
	 */
	private String address;

	/**
	 * メールアドレス
	 */
	private String mailAdd;

	/**
	 * テレNo
	 */
	private String telNo;

	/**
	 * ファックスNo
	 */
	private String faxNo;

	/**
	 * ユーザーアドミンフラッグ
	 */
	private int userAdminFlag;

	/**
	 * ノート
	 */
	private String note;

	/**
	 * ログインパスワド
	 */
	private String loginPwd;

	/**
	 * ログインパスワドTWO
	 */
	private String loginPwdTwo;

	/**
	 * ログインパスワドFIRST
	 */
	private String loginPwdFirst;

	/**
	 * ログインパスワドTWOSECOND
	 */
	private String loginPwdTwoSecond;
	
	/**
	 * パスワード一致性判断
	 */
	private String isPasswordSame;

	/**
	 * 種別NULL判断
	 */
	private String isUserAdminFlagNull;

	/**
	 * パスワード修正判断
	 */
	private boolean passwordChange;
	
	/**
	 * パスワード修正判断の取得。
	 *
	 * @return String ログインパスワドFIRST
	 */
	public boolean isPasswordChange() {
		return passwordChange;
	}

	/**
	 * パスワード修正判断の設定。
	 *
	 * @param passwordChange ログインパスワドFIRST
	 */
	public void setPasswordChange(boolean passwordChange) {
		this.passwordChange = passwordChange;
	}

	/**
	 * ログインパスワードFIRSTの取得。
	 *
	 * @return String ログインパスワドFIRST
	 */
	public String getLoginPwdFirst() {
		return loginPwdFirst;
	}

	/**
	 * ログインパスワードFIRSTの設定。
	 *
	 * @param loginPwdFirst ログインパスワドFIRST
	 */
	public void setLoginPwdFirst(String loginPwdFirst) {
		this.loginPwdFirst = loginPwdFirst;
	}

	/**
	 * ログインパスワードTWOSECONDの取得。
	 *
	 * @return String ログインパスワドTWOSECOND
	 */
	public String getLoginPwdTwoSecond() {
		return loginPwdTwoSecond;
	}

	/**
	 * ログインパスワードTWOSECONDの設定。
	 *
	 * @param loginPwdTwoSecond ログインパスワドTWOSECOND
	 */
	public void setLoginPwdTwoSecond(String loginPwdTwoSecond) {
		this.loginPwdTwoSecond = loginPwdTwoSecond;
	}

	/**
	 * 種別NULL判断の取得。
	 *
	 * @return String 種別NULL判断
	 */
	public String getIsUserAdminFlagNull() {
		return isUserAdminFlagNull;
	}

	/**
	 * 種別NULL判断の設定。
	 *
	 * @param isUserAdminFlagNull 種別NULL判断
	 */
	public void setIsUserAdminFlagNull(String isUserAdminFlagNull) {
		this.isUserAdminFlagNull = isUserAdminFlagNull;
	}

	/**
	 * ログインパスワド一致性判断の取得。
	 *
	 * @return String パスワド一致性判断
	 */
	public String getIsPasswordSame() {
		return isPasswordSame;
	}

	/**
	 * ログインパスワド一致性の設定。
	 *
	 * @param isPasswordSame ログインパスワド一致性
	 */
	public void setIsPasswordSame(String isPasswordSame) {
		this.isPasswordSame = isPasswordSame;
	}

	/**
	 * ログインパスワドTWOの取得。
	 *
	 * @return String ログインパスワド
	 */
	public String getLoginPwdTwo() {
		return loginPwdTwo;
	}

	/**
	 * ログインパスワドTWOの設定。
	 *
	 * @param loginPwdTwo ログインパスワド
	 */
	public void setLoginPwdTwo(String loginPwdTwo) {
		this.loginPwdTwo = loginPwdTwo;
	}

	/**
	 * ログインパスワドTWOの取得。
	 *
	 * @return String ログインパスワドTWO
	 */

	public String getLoginPwd() {
		return loginPwd;
	}

	/**
	 * ログインパスワドの設定。
	 *
	 * @param loginPwd ログインパスワド
	 */
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	/**
	 * ログインIDの取得。
	 *
	 * @return String ログインID
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * ログインIDの設定。
	 *
	 * @param loginId ログインID
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * ユーザーNameの取得。
	 *
	 * @return String ユーザーName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * ユーザーNameの設定。
	 *
	 * @param userName ユーザーName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * ユーザーカナの取得。
	 *
	 * @return String ユーザーカナ
	 */
	public String getUserKana() {
		return userKana;
	}

	/**
	 * ユーザーカナの設定。
	 *
	 * @param userKana ユーザーカナ
	 */
	public void setUserKana(String userKana) {
		this.userKana = userKana;
	}

	/**
	 * デパートメントNameの取得。
	 *
	 * @return String デパートメントName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * デパートメントNameの設定。
	 *
	 * @param departmentName デパートメントName
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * アイテムの取得。
	 *
	 * @return String アイテム
	 */
	public String getItem() {
		return item;
	}

	/**
	 * アイテムの設定。
	 *
	 * @param item アイテム
	 */
	public void setItem(String item) {
		this.item = item;
	}

	/**
	 * ポストコードの取得。
	 *
	 * @return String ポストコード
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * ポストコードの設定。
	 *
	 * @param postCode ポストコード
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * アドレスの取得。
	 *
	 * @return String アドレス
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * アドレスの設定。
	 *
	 * @param address アドレス
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * メールアドレスの取得。
	 *
	 * @return String メールアドレス
	 */
	public String getMailAdd() {
		return mailAdd;
	}

	/**
	 * メールアドレスの設定。
	 *
	 * @param mailAdd メールアドレス
	 */
	public void setMailAdd(String mailAdd) {
		this.mailAdd = mailAdd;
	}

	/**
	 * テレNoの取得。
	 *
	 * @return String テレNo
	 */
	public String getTelNo() {
		return telNo;
	}

	/**
	 * テレNoの設定。
	 *
	 * @param telNo テレNo
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	/**
	 * ファックスNoの取得。
	 *
	 * @return String ファックスNo
	 */
	public String getFaxNo() {
		return faxNo;
	}

	/**
	 * ファックスNoの設定。
	 *
	 * @param faxNo ファックスNo
	 */
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	/**
	 * ユーザーアドミンフラッグの取得。
	 *
	 * @return String ユーザーアドミンフラッグ
	 */
	public int getUserAdminFlag() {
		return userAdminFlag;
	}

	/**
	 * ユーザーアドミンフラッグの設定。
	 *
	 * @param userAdminFlag ユーザーアドミンフラッグ
	 */
	public void setUserAdminFlag(int userAdminFlag) {
		this.userAdminFlag = userAdminFlag;
	}

	/**
	 * ノートの取得。
	 *
	 * @return String ノート
	 */
	public String getNote() {
		return note;
	}

	/**
	 * ノートの設定。
	 *
	 * @param note  ノート
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * 画面入力チェック
	 */
	public void validate() {
		// 画面入力チェック
		FormValidator validator = FormValidatorFactory.create();
		
		validator.field("userName", userName).required(BregMessageCodes.E00001, "担当者名称")
				.maxLength(30,BregMessageCodes.E00003, "担当者名称", "30");
		validator.field("userKana", userKana).required(BregMessageCodes.E00001, "担当者名称(カナ)")
				.maxLength(30, BregMessageCodes.E00003, "担当者名称(カナ)", "30")
				.halfwidthKatakanas(BregMessageCodes.E00004, "担当者名称(カナ)");
		validator.field("departmentName", departmentName).required(BregMessageCodes.E00001, "部署")
				.maxLength(60,BregMessageCodes.E00003, "部署", "60");
		validator.field("item", item).required(BregMessageCodes.E00001, "担当品目")
				.maxLength(60, BregMessageCodes.E00003,"担当品目", "60");
		validator.field("postCode", postCode).required(BregMessageCodes.E00001, "郵便番号")
				.maxLength(16,BregMessageCodes.E00003, "郵便番号", "16");
		validator.field("address", address).required(BregMessageCodes.E00001, "住所")
				.maxLength(60,BregMessageCodes.E00003, "住所", "60");
		validator.field("mailAdd", mailAdd).required(BregMessageCodes.E00001, "メールアドレス")
				.maxLength(30,BregMessageCodes.E00003, "メールアドレス", "30");
		validator.field("telNo", telNo).required(BregMessageCodes.E00001, "TEL")
				.maxLength(16, BregMessageCodes.E00003,"TEL", "16");
		validator.field("faxNo", faxNo).required(BregMessageCodes.E00001, "FAX")
				.maxLength(16, BregMessageCodes.E00003,"FAX", "16");
		validator.field("note", note).maxLength(1024, BregMessageCodes.E00003, "備考", "1024");
		validator.field("loginPwdFirst", loginPwdFirst).required(BregMessageCodes.E00001, "パスワード")
				.rangeLength(8, 16,BregMessageCodes.E01001, "パスワード");
		if(loginPwdTwoSecond == null || loginPwdTwoSecond.length()>16 || loginPwdTwoSecond.length()<8){
			validator.field("loginPwdTwoSecond", loginPwdTwoSecond).required(BregMessageCodes.E00001, "パスワード(確認用)")
					.rangeLength(8, 16,BregMessageCodes.E01001, "パスワード(確認用)");
		}else{
			validator.field("loginPwdTwoSecond", String.valueOf(isPasswordSame))
					.match(new UserSettingValidatorRule(),BregMessageCodes.E01002);
		}
		validator.field("userAdminFlag", String.valueOf(isUserAdminFlagNull))
				.match(new UserSettingValidatorRuleTwo(),BregMessageCodes.E00001, "種別");

		validator.validate();
	}

	/**
	 * 画面入力チェック2
	 */
	public void validate2() {
		// 画面入力チェック
		FormValidator validator = FormValidatorFactory.create();
		validator.field("userName", userName).required(BregMessageCodes.E00001, "担当者名称")
				.maxLength(30,BregMessageCodes.E00003, "担当者名称", "30");
		validator.field("userKana", userKana).required(BregMessageCodes.E00001, "担当者名称(カナ)")
				.maxLength(30, BregMessageCodes.E00003, "担当者名称(カナ)", "30")
				.halfwidthKatakanas(BregMessageCodes.E00004, "担当者名称(カナ)");
		validator.field("departmentName", departmentName).required(BregMessageCodes.E00001, "部署")
				.maxLength(60,BregMessageCodes.E00003, "部署", "60");
		validator.field("item", item).required(BregMessageCodes.E00001, "担当品目")
				.maxLength(60, BregMessageCodes.E00003,"担当品目", "60");
		validator.field("postCode", postCode).required(BregMessageCodes.E00001, "郵便番号")
				.maxLength(16,BregMessageCodes.E00003, "郵便番号", "16");
		validator.field("address", address).required(BregMessageCodes.E00001, "住所")
				.maxLength(60,BregMessageCodes.E00003, "住所", "60");
		validator.field("mailAdd", mailAdd).required(BregMessageCodes.E00001, "メールアドレス")
				.maxLength(30,BregMessageCodes.E00003, "メールアドレス", "30");
		validator.field("telNo", telNo).required(BregMessageCodes.E00001, "TEL")
				.maxLength(16, BregMessageCodes.E00003,"TEL", "16");
		validator.field("faxNo", faxNo).required(BregMessageCodes.E00001, "FAX")
				.maxLength(16, BregMessageCodes.E00003,"FAX", "16");
		validator.field("note", note).maxLength(1024, BregMessageCodes.E00003, "備考", "1024");
		validator.field("userAdminFlag", String.valueOf(isUserAdminFlagNull))
				.match(new UserSettingValidatorRuleTwo(),BregMessageCodes.E00001, "種別");

		validator.validate();
	}
	
}
