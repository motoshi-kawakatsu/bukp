//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:インポート区分のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * インポート区分
 */
public enum ImportKbnEnum {

    /**
     * 0:インポート（一括申請）
     */
    ImportApp(0, "インポート（一括申請）"),

    /**
     * 1:インポート
     */
    Import(1, "インポート"),

    /**
     * 2:手入力
     */
    ManualInput(2, "手入力");

    /**
     * インポート区分の数値
     */
    private int value = 0;

    /**
     * インポート区分の名称
     */
    private String name = null;

    /**
     * コンストラクタ
     * 
     * @param value インポート区分の数値
     * @param name インポート区分の名称
     */
    ImportKbnEnum(int value, String name) {
      this.value = value;
      this.name = name;
    }

    /**
     * インポート区分の数値を取得する
     *
     * @return インポート区分の数値
     */
    public int getValue() {
      return this.value;
    }
    /**
     * インポート区分の名称を取得する
     *
     * @return インポート区分の名称
     */
    public String getName() {
      return this.name;
    }

    /**
     * 数値からインポート区分を取得する
     *
     * @param value インポート区分の数値
     * @return インポート区分
     */
    public static ImportKbnEnum valueof (int value) {
        switch (value) {
        case 0:
          // インポート（一括申請）
          return ImportApp;
        case 1:
          // インポート
          return Import;
        case 2:
          // 手入力
          return ManualInput;
        default:
          // その他場合
          return null;
    }
  }
    
    /**
     * インポート区分の数値を取得する
     * 
     * @param name インポート区分の称
     * @return チェック区分の数値
     */
    public static int getCode(String name) {
      if (name == null) {
        return ImportApp.getValue();
      }
      if (name.equals(ImportApp.getName())) {
        return ImportApp.getValue();
      }
      if (name.equals(Import.getName())) {
        return Import.getValue();
      }
      if(name.equals(ManualInput.getName())){
        return ManualInput.getValue();
      }
      return ImportApp.getValue();
    }

}

