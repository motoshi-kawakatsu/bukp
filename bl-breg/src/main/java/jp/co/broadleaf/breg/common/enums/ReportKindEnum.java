//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:王　民華
// 作成日:2017/02/08    修正内容:帳票種類のEnumファイル:新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.common.enums;

/**
 * 帳票種類
 */
public enum ReportKindEnum {

    /**
     * 1:pdf
     */
    KindPdf(1, "pdf"),

    /**
     * 2:excel
     */
    KindExcel(2, "excel"),

    /**
     * 3:tsv
     */
    KindTsv(3, "tsv");

    /**
     * 帳票種類の数値
     */
    private int value = 0;

    /**
     * 帳票種類の名称
     */
    private String name = null;

    /**
     * コンストラクタ
     * 
     * @param value 帳票種類の数値
     * @param name 帳票種類の名称
     */
    ReportKindEnum(int value, String name) {
      this.value = value;
      this.name = name;
    }

    /**
     * 帳票種類の数値を取得する
     *
     * @return 帳票種類の数値
     */
    public int getValue() {
      return this.value;
    }
    /**
     * 帳票種類の名称を取得する
     *
     * @return 帳票種類の名称
     */
    public String getName() {
      return this.name;
    }

    /**
     * 数値から帳票種類を取得する
     *
     * @param value 帳票種類の数値
     * @return 帳票種類
     */
    public static ReportKindEnum valueof (int value) {
        switch (value) {
        case 1:
          // pdf
          return KindPdf;
        case 2:
          // excel
          return KindExcel;
        case 3:
          // tsv
          return KindTsv;
        default:
          // その他場合
          return null;
    }
  }

}

