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
 * 検索制限の定義クラス
 */
public class Limit {

  /**
   * スキップ件数
   */
  private Long skipRows;

  /**
   * 最大件数
   */
  private Long maxRows;

  /**
   * 最初1件
   */
  public static final Limit FIRST = new Limit(null, 1L);

  /**
   * 制限なし
   */
  public static final Limit NO_LIMIT = new Limit(null, null);

  /**
   * コンストラクタ
   * 
   * @param skipRows スキップ件数
   * @param maxRows 最大件数
   */
  public Limit(Long skipRows, Long maxRows) {
    this.skipRows = skipRows;
    this.maxRows = maxRows;
  }

  /**
   * スキップ件数を取得する
   * 
   * @return スキップ件数
   */
  public Long getSkipRows() {
    return skipRows;
  }

  /**
   * 最大件数を取得する
   * 
   * @return 最大件数
   */
  public Long getMaxRows() {
    return maxRows;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((maxRows == null) ? 0 : maxRows.hashCode());
    result = prime * result + ((skipRows == null) ? 0 : skipRows.hashCode());
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
    Limit other = (Limit) obj;
    if (maxRows == null) {
      if (other.maxRows != null) {
        return false;
      }
    } else if (!maxRows.equals(other.maxRows)) {
      return false;
    }
    if (skipRows == null) {
      if (other.skipRows != null) {
        return false;
      }
    } else if (!skipRows.equals(other.skipRows)) {
      return false;
    }
    return true;
  }

}
