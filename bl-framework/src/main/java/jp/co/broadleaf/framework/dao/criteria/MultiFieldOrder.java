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

/***
 * 複数ソート項目の定義クラス
 */
public class MultiFieldOrder implements Order {

  /**
   * ソート項目リスト
   */
  private List<FieldOrder> fieldOrderList = new ArrayList<FieldOrder>();

  /***
   * コンストラクタ
   * 
   * @param fieldOrders ソート項目
   */
  public MultiFieldOrder(FieldOrder... fieldOrders) {
    if (fieldOrders != null) {
      for (FieldOrder fieldOrder : fieldOrders) {
        addFieldOrder(fieldOrder);
      }
    }
  }

  /***
   * ソート項目の取得
   * 
   * @return ソート項目配列
   */
  public FieldOrder[] getFieldOrders() {
    return fieldOrderList.toArray(new FieldOrder[0]);
  }

  /***
   * ソート項目の追加
   * 
   * @param fieldOrder ソート項目
   */
  public void addFieldOrder(FieldOrder fieldOrder) {
    if (fieldOrder != null) {
      fieldOrderList.add(fieldOrder);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void accept(OrderVisitor visitor) {
    visitor.visitMultiFieldOrder(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + fieldOrderList.hashCode();
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
    MultiFieldOrder other = (MultiFieldOrder) obj;
    if (!fieldOrderList.equals(other.fieldOrderList)) {
      return false;
    }
    return true;
  }

}
