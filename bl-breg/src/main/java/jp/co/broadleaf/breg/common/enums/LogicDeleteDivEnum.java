//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:論理削除区分のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * 論理削除区分
 */
public enum LogicDeleteDivEnum {

    /**
     * 0:有効
     */
    Valid(0, "有効"),

    /**
     * 1:論理削除
     */
    LogicDelete(1, "論理削除"),

    /**
     * 2:保留
     */
    Retain(2, "保留"),

    /**
     * 3:完全削除
     */
    CompleteDelete(3, "完全削除");

    /**
     * 論理削除区分の数値
     */
    private int value = 0;

    /**
     * 論理削除区分の名称
     */
    private String name = null;

    /**
     * コンストラクタ
     * 
     * @param value 論理削除区分の数値
     * @param name 論理削除区分の名称
     */
    LogicDeleteDivEnum(int value, String name) {
      this.value = value;
      this.name = name;
    }

    /**
     * 論理削除区分の数値を取得する
     *
     * @return 論理削除区分の数値
     */
    public int getValue() {
      return this.value;
    }
    /**
     * 論理削除区分の名称を取得する
     *
     * @return 論理削除区分の名称
     */
    public String getName() {
      return this.name;
    }

    /**
     * 数値から論理削除区分を取得する
     *
     * @param value 論理削除区分の数値
     * @return 論理削除区分
     */
    public static LogicDeleteDivEnum valueof (int value) {
        switch (value) {
        case 0:
          // 有効
          return Valid;
        case 1:
          // 論理削除
          return LogicDelete;
        case 2:
          // 保留
          return Retain;
        case 3:
          // 完全削除
          return CompleteDelete;
        default:
          // その他場合
          return null;
    }
  }

}

