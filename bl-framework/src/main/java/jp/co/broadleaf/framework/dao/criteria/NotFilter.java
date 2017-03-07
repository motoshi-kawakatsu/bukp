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

/**
 * Not演算子フィルタの定義クラス
 */
public class NotFilter implements Filter {

  /**
   * フィルタ
   */
  private Filter filter;

  /**
   * コンストラクタ
   * 
   * @param filter フィルタ
   */
  public NotFilter(Filter filter) {
    this.filter = filter;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(FilterVisitor visitor) {
    visitor.visitNotFilter(this);
  }

  /**
   * フィルタの取得
   * 
   * @return フィルタ
   */
  public Filter getFilter() {
    return filter;
  }

}
