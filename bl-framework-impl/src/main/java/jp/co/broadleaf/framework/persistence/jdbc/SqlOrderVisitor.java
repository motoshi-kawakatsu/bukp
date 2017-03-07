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
package jp.co.broadleaf.framework.persistence.jdbc;

import jp.co.broadleaf.framework.dao.criteria.FieldOrder;
import jp.co.broadleaf.framework.dao.criteria.MultiFieldOrder;
import jp.co.broadleaf.framework.dao.criteria.OrderVisitor;
import jp.co.broadleaf.framework.domain.entity.EntityNameResolver;

/**
 * Sqlソート順ビジター
 */
public class SqlOrderVisitor implements OrderVisitor {

  /**
   * エンティティクラス
   */
  private Class<?> entityClass;

  /**
   * エンティティ名のリゾルバ
   */
  private EntityNameResolver entityNameResolver;

  /**
   * コンストラクタ
   * 
   * @param entityClass エンティティクラス
   * @param entityNameResolver エンティティ名のリゾルバ
   */
  public SqlOrderVisitor(Class<?> entityClass, EntityNameResolver entityNameResolver) {
    this.entityClass = entityClass;
    this.entityNameResolver = entityNameResolver;
  }

  /**
   * ソート順エレメントをアクセスする
   * 
   * @param order ソート順
   */
  @Override
  public void visitFieldOrder(FieldOrder order) {
    String field = getColumnName(entityClass, order.getField());
    boolean descending = order.isDescending();
    buffer.append(field);
    buffer.append(" ");
    if (descending) {
      buffer.append("desc");
    } else {
      buffer.append("asc");
    }
  }

  /**
   * 複数ソート順エレメントをアクセスする
   * 
   * @param order ソート順
   */
  @Override
  public void visitMultiFieldOrder(MultiFieldOrder order) {
    int index = 0;
    for (FieldOrder fieldOrder : order.getFieldOrders()) {
      if (index > 0) {
        buffer.append(", ");
      }
      visitFieldOrder(fieldOrder);
      index++;
    }
  }

  /**
   * バッファ
   */
  private StringBuilder buffer = new StringBuilder();

  /**
   * エクスプレッションを取得する
   * 
   * @return エクスプレッション
   */
  public String getExpresion() {
    return buffer.toString();
  }

  /**
   * Order ByのSQL文を取得する
   * 
   * @return Order ByのSQL文
   */
  public String getOrderBy() {
    String expr = getExpresion().trim();
    if (expr.length() > 0) {
      return " order by " + expr;
    } else {
      return "";
    }
  }

  /**
   * カラム名を取得する
   * 
   * @param entityClazz エンティティクラス
   * @param propertyName プロパティ名
   * @return カラム名
   */
  private String getColumnName(Class<?> entityClazz, String propertyName) {
    return entityNameResolver.getEntityPropertyName(entityClazz, propertyName);
  }
}
