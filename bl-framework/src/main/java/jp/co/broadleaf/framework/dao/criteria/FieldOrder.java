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
 * フィールドのソート順定義クラス
 */
public abstract class FieldOrder implements Order {

  /**
   * フィールド
   */
  private String field;

  /**
   * 降順かどうか（true：降順、false：昇順）
   */
  private boolean descending;

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param field フィールド
   */
  public FieldOrder(String field) {
    this(field, false);
  }

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param field フィールド
   * @param descending 降順かどうか
   */
  public FieldOrder(String field, boolean descending) {
    if (field == null) {
      throw new IllegalArgumentException("Field must not be null");
    }
    this.field = field;
    this.descending = descending;
  }

  /**
   * フィールドの取得
   * 
   * @return フィールド
   */
  public String getField() {
    return field;
  }

  /**
   * 降順かどうかの取得
   * 
   * @return 降順かどうか（true：降順、false：昇順）
   */
  public boolean isDescending() {
    return descending;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(OrderVisitor visitor) {
    visitor.visitFieldOrder(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (descending ? 1231 : 1237);
    result = prime * result + field.hashCode();
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
    FieldOrder other = (FieldOrder) obj;
    if (descending != other.descending) {
      return false;
    }
    if (!field.equals(other.field)) {
      return false;
    }
    return true;
  }

}
