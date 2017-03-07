//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:ユーザー種別のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * ユーザー種別
 */
public enum UserKindEnum {

    /**
     * 0:BLユーザー
     */
    BlUser(0, "BLユーザー"),

    /**
     * 1:メーカーユーザー
     */
    MakerUser(1, "メーカーユーザー");

    /**
     * ユーザー種別の数値
     */
    private int value = 0;

    /**
     * ユーザー種別の名称
     */
    private String name = null;

    /**
     * コンストラクタ
     * 
     * @param value ユーザー種別の数値
     * @param name ユーザー種別の名称
     */
    UserKindEnum(int value, String name) {
      this.value = value;
      this.name = name;
    }

    /**
     * ユーザー種別の数値を取得する
     *
     * @return ユーザー種別の数値
     */
    public int getValue() {
      return this.value;
    }
    /**
     * ユーザー種別の名称を取得する
     *
     * @return ユーザー種別の名称
     */
    public String getName() {
      return this.name;
    }

    /**
     * 数値からユーザー種別を取得する
     *
     * @param value ユーザー種別の数値
     * @return ユーザー種別
     */
    public static UserKindEnum valueof (int value) {
        switch (value) {
        case 0:
          // BLユーザー
          return BlUser;
        case 1:
          // メーカーユーザー
          return MakerUser;
        default:
          // その他場合
          return null;
    }
  }

}

