//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:曹　緩緩
// 作成日:2017/02/08    修正内容:ログイン：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.loginmaker.facade.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import java.util.Date;

/**
 * 担当者情報Dtoのクラス。
 */
public class UserMasterDto {

    /**
     * UUID
     */
    private String uuid;

    /**
     * 作成日時
     */
    private Date insDtTime;

    /**
     * 更新日時
     */
    private Date updDtTime;

    /**
     * 作成アカウントID
     */
    private String insAccountId;

    /**
     * 更新アカウントID
     */
    private String updAccountId;

    /**
     * 論理削除区分
     */
    private int logicalDelDiv;

    /**
     * メーカーコード
     */
    private int makerCode;

    /**
     * ログインID
     */
    private String loginId;

    /**
     * 担当者名称
     */
    private String userName;

    /**
     * 担当者名称（カナ）
     */
    private String userKana;

    /**
     * 部署
     */
    private String departmentName;

    /**
     * 担当品目
     */
    private String item;

    /**
     * 郵便番号
     */
    private String postCode;

    /**
     * 住所
     */
    private String address;

    /**
     * メールアドレス
     */
    private String mailAdd;

    /**
     * TEL
     */
    private String telNo;

    /**
     * FAX
     */
    private String faxNo;

    /**
     * ユーザー管理者フラグ
     */
    private int userAdminFlag;

    /**
     * ユーザー種別
     */
    private int userKind;

    /**
     * 備考
     */
    private String note;

    /**
     * ログインロック区分
     */
    private int loginLockDiv;


    /**
     * UUIDの取得。
     *
     * @return String UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 作成日時の取得。
     *
     * @return Date 作成日時
     */
    public Date getInsDtTime() {
        return insDtTime;
    }

    /**
     * 更新日時の取得。
     *
     * @return Date 更新日時
     */
    public Date getUpdDtTime() {
        return updDtTime;
    }

    /**
     * 作成アカウントIDの取得。
     *
     * @return String 作成アカウントID
     */
    public String getInsAccountId() {
        return insAccountId;
    }

    /**
     * 更新アカウントIDの取得。
     *
     * @return String 更新アカウントID
     */
    public String getUpdAccountId() {
        return updAccountId;
    }

    /**
     * 論理削除区分の取得。
     *
     * @return int 論理削除区分
     */
    public int getLogicalDelDiv() {
        return logicalDelDiv;
    }

    /**
     * メーカーコードの取得。
     *
     * @return int メーカーコード
     */
    public int getMakerCode() {
        return makerCode;
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
     * 担当者名称の取得。
     *
     * @return String 担当者名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 担当者名称（カナ）の取得。
     *
     * @return String 担当者名称（カナ）
     */
    public String getUserKana() {
        return userKana;
    }

    /**
     * 部署の取得。
     *
     * @return String 部署
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 担当品目の取得。
     *
     * @return String 担当品目
     */
    public String getItem() {
        return item;
    }

    /**
     * 郵便番号の取得。
     *
     * @return String 郵便番号
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 住所の取得。
     *
     * @return String 住所
     */
    public String getAddress() {
        return address;
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
     * TELの取得。
     *
     * @return String TEL
     */
    public String getTelNo() {
        return telNo;
    }

    /**
     * FAXの取得。
     *
     * @return String FAX
     */
    public String getFaxNo() {
        return faxNo;
    }

    /**
     * ユーザー管理者フラグの取得。
     *
     * @return int ユーザー管理者フラグ
     */
    public int getUserAdminFlag() {
        return userAdminFlag;
    }

    /**
     * ユーザー種別の取得。
     *
     * @return int ユーザー種別
     */
    public int getUserKind() {
        return userKind;
    }

    /**
     * 備考の取得。
     *
     * @return String 備考
     */
    public String getNote() {
        return note;
    }

    /**
     * ログインロック区分の取得。
     *
     * @return int ログインロック区分
     */
    public int getLoginLockDiv() {
        return loginLockDiv;
    }

    /**
     * UUIDの設定。
     *
     * @param uuid UUID
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 作成日時の設定。
     *
     * @param insDtTime 作成日時
     */
    public void setInsDtTime(Date insDtTime) {
        this.insDtTime = insDtTime;
    }

    /**
     * 更新日時の設定。
     *
     * @param updDtTime 更新日時
     */
    public void setUpdDtTime(Date updDtTime) {
        this.updDtTime = updDtTime;
    }

    /**
     * 作成アカウントIDの設定。
     *
     * @param insAccountId 作成アカウントID
     */
    public void setInsAccountId(String insAccountId) {
        this.insAccountId = insAccountId;
    }

    /**
     * 更新アカウントIDの設定。
     *
     * @param updAccountId 更新アカウントID
     */
    public void setUpdAccountId(String updAccountId) {
        this.updAccountId = updAccountId;
    }

    /**
     * 論理削除区分の設定。
     *
     * @param logicalDelDiv 論理削除区分
     */
    public void setLogicalDelDiv(int logicalDelDiv) {
        this.logicalDelDiv = logicalDelDiv;
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
     * ログインIDの設定。
     *
     * @param loginId ログインID
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * 担当者名称の設定。
     *
     * @param userName 担当者名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 担当者名称（カナ）の設定。
     *
     * @param userKana 担当者名称（カナ）
     */
    public void setUserKana(String userKana) {
        this.userKana = userKana;
    }

    /**
     * 部署の設定。
     *
     * @param departmentName 部署
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * 担当品目の設定。
     *
     * @param item 担当品目
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * 郵便番号の設定。
     *
     * @param postCode 郵便番号
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * 住所の設定。
     *
     * @param address 住所
     */
    public void setAddress(String address) {
        this.address = address;
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
     * TELの設定。
     *
     * @param telNo TEL
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    /**
     * FAXの設定。
     *
     * @param faxNo FAX
     */
    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    /**
     * ユーザー管理者フラグの設定。
     *
     * @param userAdminFlag ユーザー管理者フラグ
     */
    public void setUserAdminFlag(int userAdminFlag) {
        this.userAdminFlag = userAdminFlag;
    }

    /**
     * ユーザー種別の設定。
     *
     * @param userKind ユーザー種別
     */
    public void setUserKind(int userKind) {
        this.userKind = userKind;
    }

    /**
     * 備考の設定。
     *
     * @param note 備考
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * ログインロック区分の設定。
     *
     * @param loginLockDiv ログインロック区分
     */
    public void setLoginLockDiv(int loginLockDiv) {
        this.loginLockDiv = loginLockDiv;
    }

     /**
     * ハッシュコードを設定する。
     *
     * @return hashCode
     */
     @Override
     public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(uuid)
            .append(insDtTime)
            .append(updDtTime)
            .append(insAccountId)
            .append(updAccountId)
            .append(logicalDelDiv)
            .append(makerCode)
            .append(loginId)
            .append(userName)
            .append(userKana)
            .append(departmentName)
            .append(item)
            .append(postCode)
            .append(address)
            .append(mailAdd)
            .append(telNo)
            .append(faxNo)
            .append(userAdminFlag)
            .append(userKind)
            .append(note)
            .append(loginLockDiv)
          .toHashCode();
    }

     /**
     * equalsメソッド
     * @param obj オブジェクト
     * @return 比較結果
     */
     @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        UserMasterDto other = (UserMasterDto) obj;
        return new EqualsBuilder()
            .append(uuid, other.uuid)
            .append(insDtTime, other.insDtTime)
            .append(updDtTime, other.updDtTime)
            .append(insAccountId, other.insAccountId)
            .append(updAccountId, other.updAccountId)
            .append(logicalDelDiv, other.logicalDelDiv)
            .append(makerCode, other.makerCode)
            .append(loginId, other.loginId)
            .append(userName, other.userName)
            .append(userKana, other.userKana)
            .append(departmentName, other.departmentName)
            .append(item, other.item)
            .append(postCode, other.postCode)
            .append(address, other.address)
            .append(mailAdd, other.mailAdd)
            .append(telNo, other.telNo)
            .append(faxNo, other.faxNo)
            .append(userAdminFlag, other.userAdminFlag)
            .append(userKind, other.userKind)
            .append(note, other.note)
            .append(loginLockDiv, other.loginLockDiv)
            .isEquals();
    }

}
