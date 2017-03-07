//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:カラム種類のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * カラム種類
 */
public enum ColumnKindEnum {

    /**
     * 0:数字
     */
    Rnumeric("0"),

    /**
     * 1:文字
     */
    Rstring("1");

    /**
     * カラム種類の数値
     */
    private String value = "";

    /**
     * コンストラクタ
     * 
     * @param value カラム種類の数値
     */
    ColumnKindEnum(String value) {
      this.value = value;
    }

    /**
     * カラム種類の数値を取得する
     *
     * @return カラム種類の数値
     */
    public String getValue() {
      return this.value;
    }


    /**
     * 数値からカラム種類を取得する
     *
     * @param value カラム種類の数値
     * @return カラム種類
     */
    public static ColumnKindEnum valueof (String value) {
        switch (value) {
        case "0":
          // 数字
          return Rnumeric;
        case "1":
          // 文字
          return Rstring;
        default:
          // その他場合
          return null;
    }
  }

}

