//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:陸磊
// 作成日:2017/03/06    修正内容:出力ファイルタイプのEnumファイル:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common.enums;

/**
 * <pre>
 * 出力ファイルタイプ
 * </pre>
 */
public enum OutPutTypeEnum {

  /**
   * pdf
   */
  OutPutPdf("1", 1),

  /**
   * tsv
   */
  OutPutTsv("2", 3),

  /**
   * xls
   */
  OutPutXls("3", 2);

  /**
   * ファイルのモードの数値
   */
  private String value = null;

  /**
   * ファイルのモードの名称
   */
  private int name = 0;

  /**
   * コンストラクタ
   * 
   * @param value ファイルのモードの数値
   * @param name ファイルのモードの名称
   */
  OutPutTypeEnum(String value, int name) {
    this.value = value;
    this.name = name;
  }

  /**
   * 画面のモードの数値を取得する
   *
   * @return 画面のモードの数値
   */
  public String getValue() {
    return this.value;
  }

  /**
   * 画面のモードの名称を取得する
   *
   * @return 画面のモードの名称
   */
  public int getName() {
    return this.name;
  }
}
