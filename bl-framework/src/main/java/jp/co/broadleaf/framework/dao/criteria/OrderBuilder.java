//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.framework.dao.criteria;

/***
 * ソートビルダーの定義クラス
 */
public final class OrderBuilder {

  /**
   * 複数ソート項目
   */
  private MultiFieldOrder multiFieldOrder;

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   */
  private OrderBuilder() {
    this.multiFieldOrder = new MultiFieldOrder();
  }

  /***
   * ソートビルダーのインスタンス
   * 
   * @return ソートビルダー
   */
  public static OrderBuilder instance() {
    return new OrderBuilder();
  }

  /***
   * 昇順のソートビルダーの定義処理
   * 
   * @param field ソート項目
   * @return ソートビルダー
   */
  public OrderBuilder asc(String field) {
    multiFieldOrder.addFieldOrder(new FieldAscOrder(field));
    return this;
  }

  /***
   * 降順のソートビルダーの定義処理
   * 
   * @param field ソート項目
   * @return ソートビルダー
   */
  public OrderBuilder desc(String field) {
    multiFieldOrder.addFieldOrder(new FieldDescOrder(field));
    return this;
  }

  /***
   * ソートビルド
   * 
   * @return ソート
   */
  public Order build() {
    return multiFieldOrder;
  }
}
