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
 * フィルタ ビジターのインターフェース
 */
public interface FilterVisitor {

  /**
   * 文字フィルタを注入する
   * 
   * @param filter フィルタ
   */
  void visitStringFilter(StringFilter filter);

  /**
   * 数字フィルタを注入する
   * 
   * @param filter フィルタ
   */
  void visitNumberFilter(NumberFilter filter);

  /**
   * ブーリアンフィルタを注入する
   * 
   * @param filter フィルタ
   */
  void visitBooleanFilter(BooleanFilter filter);

  /**
   * 日付フィルタを注入する
   * 
   * @param filter フィルタ
   */
  void visitDateFilter(DateFilter filter);

  /**
   * And論理演算子を注入する
   * 
   * @param filter フィルタ
   */
  void visitAndFilter(AndFilter filter);

  /**
   * Or論理演算子を注入する
   * 
   * @param filter フィルタ
   */
  void visitOrFilter(OrFilter filter);

  /**
   * Not論理演算子を注入する
   * 
   * @param filter フィルタ
   */
  void visitNotFilter(NotFilter filter);
}
