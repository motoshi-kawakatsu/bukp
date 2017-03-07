//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:対象区分のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * 対象区分
 */
public enum ObjectKbnEnum {

    /**
     * 1:商品
     */
    GoodsKbn(1, "商品"),

    /**
     * 2:セット
     */
    SetKbn(2, "セット"),

    /**
     * 3:結合
     */
    JoinKbn(3, "結合");

    /**
     * 対象区分の数値
     */
    private int value = 0;

    /**
     * 対象区分の名称
     */
    private String name = null;

    /**
     * コンストラクタ
     * 
     * @param value 対象区分の数値
     * @param name 対象区分の名称
     */
    ObjectKbnEnum(int value, String name) {
      this.value = value;
      this.name = name;
    }

    /**
     * 対象区分の数値を取得する
     *
     * @return 対象区分の数値
     */
    public int getValue() {
      return this.value;
    }

    /**
     * 対象区分の名称を取得する
     *
     * @return 対象区分の名称
     */
    public String getName() {
      return this.name;
    }

    /**
     * 数値から対象区分を取得する
     *
     * @param value 対象区分の数値
     * @return 対象区分
     */
    public static ObjectKbnEnum valueof (int value) {
        switch (value) {
        case 1:
          // 商品
          return GoodsKbn;
        case 2:
          // セット
          return SetKbn;
        case 3:
          // 結合
          return JoinKbn;
        default:
          // その他場合
          return null;
    }
  }

}

