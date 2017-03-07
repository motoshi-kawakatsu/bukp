//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:インポート類型のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * インポート類型
 */
public enum ImportTypeEnum {

    /**
     * 0:全件
     */
    All(0, "全件"),

    /**
     * 1:差分
     */
    Diff(1, "差分"),

    /**
     * 2:なし
     */
    NoImport(2, "なし");

    /**
     * インポート類型の数値
     */
    private int value = 0;

    /**
     * インポート類型の名称
     */
    private String name = null;

    /**
     * コンストラクタ
     * 
     * @param value インポート類型の数値
     * @param name インポート類型の名称
     */
    ImportTypeEnum(int value, String name) {
      this.value = value;
      this.name = name;
    }

    /**
     * インポート類型の数値を取得する
     *
     * @return インポート類型の数値
     */
    public int getValue() {
      return this.value;
    }
    /**
     * インポート類型の名称を取得する
     *
     * @return インポート類型の名称
     */
    public String getName() {
      return this.name;
    }

    /**
     * 数値からインポート類型を取得する
     *
     * @param value インポート類型の数値
     * @return インポート類型
     */
    public static ImportTypeEnum valueof (int value) {
        switch (value) {
        case 0:
          // 全件
          return All;
        case 1:
          // 差分
          return Diff;
        case 2:
          // なし
          return NoImport;
        default:
          // その他場合
          return null;
    }
  }

}

