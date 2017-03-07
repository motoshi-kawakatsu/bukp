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
package jp.co.broadleaf.breg.usersetting.facade.dto;

/**
 * <pre>
 * 担当者情報のDTOクラス.
 * </pre>
 */
public class UserSettingDto {

	/**
	 * ログインID
	 */
	private String loginId;

	/**
	 * メーカーコード
	 */
	private int makerCode;

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
	 * メーカーコードの取得。
	 *
	 * @return String ログインID
	 */
	public int getMakerCode() {
		return makerCode;
	}

	/**
	 * メーカーコードの設定。
	 *
	 * @param makerCode メーカーコード
	 */
	public void setMakerCode(int makerCode) {
		this.makerCode = makerCode;
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
	 * @param note ノート
	 */
	public void setNote(String note) {
		this.note = note;
	}
	
}
