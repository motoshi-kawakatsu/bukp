//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:ユーザー管理者フラグのEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * ユーザー管理者フラグ
 */
public enum UserAdminFlagEnum {

    /**
     * 0:一般
     */
    User(0, "一般"),

    /**
     * 1:ユーザー管理者
     */
    Admin(1, "ユーザー管理者");

    /**
     * ユーザー管理者フラグの数値
     */
    private int value = 0;

    /**
     * ユーザー管理者フラグの名称
     */
    private String name = null;

    /**
     * コンストラクタ
     * 
     * @param value ユーザー管理者フラグの数値
     * @param name ユーザー管理者フラグの名称
     */
    UserAdminFlagEnum(int value, String name) {
      this.value = value;
      this.name = name;
    }

    /**
     * ユーザー管理者フラグの数値を取得する
     *
     * @return ユーザー管理者フラグの数値
     */
    public int getValue() {
      return this.value;
    }
    /**
     * ユーザー管理者フラグの名称を取得する
     *
     * @return ユーザー管理者フラグの名称
     */
    public String getName() {
      return this.name;
    }

    /**
     * 数値からユーザー管理者フラグを取得する
     *
     * @param value ユーザー管理者フラグの数値
     * @return ユーザー管理者フラグ
     */
    public static UserAdminFlagEnum valueof (int value) {
        switch (value) {
        case 0:
          // 一般
          return User;
        case 1:
          // ユーザー管理者
          return Admin;
        default:
          // その他場合
          return null;
    }
  }

}

