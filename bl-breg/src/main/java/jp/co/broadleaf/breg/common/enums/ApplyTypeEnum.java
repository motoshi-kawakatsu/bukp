//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:申請種類のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * 申請種類
 */
public enum ApplyTypeEnum {

    /**
     * 0:申請一般
     */
    GenerApply(0, "申請一般"),

    /**
     * 1:新規品目
     */
    NewItem(1, "新規品目");

    /**
     * 申請種類の数値
     */
    private int value = 0;

    /**
     * 申請種類の名称
     */
    private String name = null;

    /**
     * コンストラクタ
     * 
     * @param value 申請種類の数値
     * @param name 申請種類の名称
     */
    ApplyTypeEnum(int value, String name) {
      this.value = value;
      this.name = name;
    }

    /**
     * 申請種類の数値を取得する
     *
     * @return 申請種類の数値
     */
    public int getValue() {
      return this.value;
    }
    /**
     * 申請種類の名称を取得する
     *
     * @return 申請種類の名称
     */
    public String getName() {
      return this.name;
    }

    /**
     * 数値から申請種類を取得する
     *
     * @param value 申請種類の数値
     * @return 申請種類
     */
    public static ApplyTypeEnum valueof (int value) {
        switch (value) {
        case 0:
          // 申請一般
          return GenerApply;
        case 1:
          // 新規品目
          return NewItem;
        default:
          // その他場合
          return null;
    }
  }

}

