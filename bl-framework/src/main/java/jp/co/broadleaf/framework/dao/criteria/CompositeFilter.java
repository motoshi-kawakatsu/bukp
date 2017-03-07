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

import java.util.ArrayList;
import java.util.List;

/**
 * 複合フィルタの定義クラス
 */
public abstract class CompositeFilter implements Filter {

  /**
   * フィルタリスト
   */
  private List<Filter> filterList = new ArrayList<Filter>();

  /**
   * コンストラクタ
   * 
   * @param filters フィルタ
   */
  public CompositeFilter(Filter... filters) {
    if (filters != null) {
      for (Filter filter : filters) {
        addFilter(filter);
      }
    }
  }

  /**
   * フィルタ配列の取得
   * 
   * @return フィルタ配列
   */
  public Filter[] getFilters() {
    return filterList.toArray(new Filter[0]);
  }

  /**
   * フィルタの追加
   * 
   * @param filter フィルタ
   */
  public void addFilter(Filter filter) {
    if (filter != null) {
      filterList.add(filter);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + filterList.hashCode();
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    CompositeFilter other = (CompositeFilter) obj;
    if (!filterList.equals(other.filterList)) {
      return false;
    }
    return true;
  }

}
