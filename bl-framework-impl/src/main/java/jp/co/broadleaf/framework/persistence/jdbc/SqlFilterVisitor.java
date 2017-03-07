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

import jp.co.broadleaf.framework.dao.criteria.AndFilter;
import jp.co.broadleaf.framework.dao.criteria.BooleanFilter;
import jp.co.broadleaf.framework.dao.criteria.BooleanFilterOp;
import jp.co.broadleaf.framework.dao.criteria.DateFilter;
import jp.co.broadleaf.framework.dao.criteria.DateFilterOp;
import jp.co.broadleaf.framework.dao.criteria.Filter;
import jp.co.broadleaf.framework.dao.criteria.FilterVisitor;
import jp.co.broadleaf.framework.dao.criteria.NotFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilter;
import jp.co.broadleaf.framework.dao.criteria.NumberFilterOp;
import jp.co.broadleaf.framework.dao.criteria.OrFilter;
import jp.co.broadleaf.framework.dao.criteria.StringFilter;
import jp.co.broadleaf.framework.dao.criteria.StringFilterOp;
import jp.co.broadleaf.framework.domain.entity.EntityNameResolver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Sqlフィルタビジタークラスです。
 */
public class SqlFilterVisitor implements FilterVisitor {

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
  public SqlFilterVisitor(Class<?> entityClass, EntityNameResolver entityNameResolver) {
    this.entityClass = entityClass;
    this.entityNameResolver = entityNameResolver;
  }

  /**
   * 文字列カラムフィルタをアクセスする。
   * 
   * @param filter 文字列カラムフィルタ
   */
  @Override
  public void visitStringFilter(StringFilter filter) {
    // Make sure the filter is valid
    filter.validate();

    String field = filter.getField();
    StringFilterOp op = filter.getOperator();
    String value = filter.getValue();
    String opstr = null;
    String expr = null;
    boolean escapeFlg = false;
    List<String> values = null;
    if (StringFilterOp.IsNull == op) {
      expr = getColumnName(field) + " is null";
    } else if (StringFilterOp.IsNotNull == op) {
      expr = getColumnName(field) + " is not null";
    } else {
      switch (op) {
      case Equals:
        opstr = "=";
        break;
      case NotEquals:
        opstr = "<>";
        break;
      case BeginsWith:
        opstr = "like";
        value = replaceValue(value) + "%";
        escapeFlg = true;
        break;
      case NotBeginsWith:
        opstr = "not like";
        value = replaceValue(value) + "%";
        escapeFlg = true;
        break;
      case EndsWith:
        opstr = "like";
        value = "%" + replaceValue(value);
        escapeFlg = true;
        break;
      case NotEndsWith:
        opstr = "not like";
        value = "%" + replaceValue(value);
        escapeFlg = true;
        break;
      case Contains:
        opstr = "like";
        value = "%" + replaceValue(value) + "%";
        escapeFlg = true;
        break;
      case NotContains:
        opstr = "not like";
        value = "%" + replaceValue(value) + "%";
        escapeFlg = true;
        break;
      case In:
      case NotIn:
        opstr = "in";
        if (op == StringFilterOp.NotIn) {
          opstr = "not " + opstr;
        }
        StringBuilder buf = new StringBuilder();
        buf.append(getColumnName(field));
        buf.append(" ");
        buf.append(opstr);
        buf.append(" (");
        int index = 0;
        values = new ArrayList<String>();
        for (String val : filter.getValues()) {
          if (index > 0) {
            buf.append(", ");
          }
          buf.append("?");
          values.add(val);
          index++;
        }
        buf.append(")");
        expr = buf.toString();
        break;
      default:
        throw new IllegalArgumentException();
      }
      
      if (values != null) {
        parameters.addAll(values);
      } else {
        expr = getColumnName(field) + " " + opstr + " ?";
        parameters.add(value);
        if (escapeFlg) {
          expr = expr + " escape ?";
          parameters.add("/");
        }
      }
    }
    write(expr);
  }

  /**
   * 数字カラムフィルタをアクセスする。
   * 
   * @param filter 数字カラムフィルタ
   */
  @Override
  public void visitNumberFilter(NumberFilter filter) {
    // Make sure the filter is valid
    filter.validate();

    String field = filter.getField();
    NumberFilterOp op = filter.getOperator();
    Number value = filter.getValue();
    String opstr = null;
    String expr = null;
    List<Number> values = null;
    if (NumberFilterOp.IsNull == op) {
      expr = getColumnName(field) + " is null";
    } else if (NumberFilterOp.IsNotNull == op) {
      expr = getColumnName(field) + " is not null";
    } else {
      switch (op) {
      case Equals:
        opstr = "=";
        break;
      case NotEquals:
        opstr = "<>";
        break;
      case GreaterThan:
        opstr = ">";
        break;
      case GreaterThanOrEquals:
        opstr = ">=";
        break;
      case LessThan:
        opstr = "<";
        break;
      case LessThanOrEquals:
        opstr = "<=";
        break;
      case In:
      case NotIn:
        opstr = "in";
        if (op == NumberFilterOp.NotIn) {
          opstr = "not " + opstr;
        }
        StringBuilder buf = new StringBuilder();
        buf.append(getColumnName(field));
        buf.append(" ");
        buf.append(opstr);
        buf.append(" (");
        int index = 0;
        values = new ArrayList<Number>();
        for (Number val : filter.getValues()) {
          if (index > 0) {
            buf.append(", ");
          }
          buf.append("?");
          values.add(val);
          index++;
        }
        buf.append(")");
        expr = buf.toString();
        break;
      default:
        throw new IllegalArgumentException();
      }
      if (values != null) {
        parameters.addAll(values);
      } else {
        expr = getColumnName(field) + " " + opstr + " ?";
        parameters.add(value);
      }
    }
    write(expr);
  }

  /**
   * ブールカラムフィルタをアクセスする。
   * 
   * @param filter ブールカラムフィルタ
   */
  @Override
  public void visitBooleanFilter(BooleanFilter filter) {
    // Make sure the filter is valid
    filter.validate();

    String field = filter.getField();
    BooleanFilterOp op = filter.getOperator();
    Boolean value = filter.getValue();
    String opstr = null;
    String expr = null;
    if (BooleanFilterOp.IsNull == op) {
      expr = getColumnName(field) + " is null";
    } else if (BooleanFilterOp.IsNotNull == op) {
      expr = getColumnName(field) + " is not null";
    } else {
      switch (op) {
      case Equals:
        opstr = "=";
        break;
      case NotEquals:
        opstr = "<>";
        break;
      default:
        throw new IllegalArgumentException();
      }
      expr = getColumnName(field) + " " + opstr + " ?";
      parameters.add(value);
    }
    write(expr);
  }

  /**
   * 日付カラムフィルタをアクセスする。
   * 
   * @param filter 日付カラムフィルタ
   */
  @Override
  public void visitDateFilter(DateFilter filter) {
    // Make sure the filter is valid
    filter.validate();

    String field = filter.getField();
    DateFilterOp op = filter.getOperator();
    Date value = filter.getValue();
    String opstr = null;
    String expr = null;
    if (DateFilterOp.IsNull == op) {
      expr = getColumnName(field) + " is null";
    } else if (DateFilterOp.IsNotNull == op) {
      expr = getColumnName(field) + " is not null";
    } else {
      switch (op) {
      case Equals:
        opstr = "=";
        break;
      case NotEquals:
        opstr = "<>";
        break;
      case GreaterThan:
        opstr = ">";
        break;
      case GreaterThanOrEquals:
        opstr = ">=";
        break;
      case LessThan:
        opstr = "<";
        break;
      case LessThanOrEquals:
        opstr = "<=";
        break;
      default:
        throw new IllegalArgumentException();
      }
      expr = getColumnName(field) + " " + opstr + " ?";
      parameters.add(value);
    }
    write(expr);
  }

  /**
   * Andフィルタをアクセスする。
   * 
   * @param filter Andフィルタ
   */
  @Override
  public void visitAndFilter(AndFilter filter) {
    Filter[] filters = filter.getFilters();
    if (filters != null) {
      if (filters.length > 1) {
        write("(");
      }
      int index = 0;
      for (Filter child : filters) {
        if (index > 0) {
          write(" and ");
        }
        child.accept(this);
        index++;
      }
      if (filters.length > 1) {
        write(")");
      }
    }
  }

  /**
   * Orフィルタをアクセスする。
   * 
   * @param filter Orフィルタ
   */
  @Override
  public void visitOrFilter(OrFilter filter) {
    Filter[] filters = filter.getFilters();
    if (filters != null) {
      if (filters.length > 1) {
        write("(");
      }
      int index = 0;
      for (Filter child : filters) {
        if (index > 0) {
          write(" or ");
        }
        child.accept(this);
        index++;
      }
      if (filters.length > 1) {
        write(")");
      }
    }
  }

  /**
   * Notフィルタをアクセスする。
   * 
   * @param filter Notフィルタ
   */
  @Override
  public void visitNotFilter(NotFilter filter) {
    Filter innerFilter = filter.getFilter();
    if (innerFilter != null) {
      write("not (");
      innerFilter.accept(this);
      write(")");
    }
  }

  /**
   * エクスプレッションを書き込みます。
   * 
   * @param expression エクスプレッション
   */
  private void write(String expression) {
    if (expression != null) {
      buffer.append(expression);
    }
  }

  /**
   * バッファ
   */
  private StringBuilder buffer = new StringBuilder();

  /**
   * パラメータリスト
   */
  private List<Object> parameters = new ArrayList<Object>();

  /**
   * エクスプレッションを取得する。
   * 
   * @return エクスプレッション
   */
  public String getExpresion() {
    return buffer.toString();
  }

  /**
   * WhereのSQL文を取得する。
   * 
   * @return WhereのSQL文
   */
  public String getWhere() {
    String expr = getExpresion().trim();
    if (expr.length() > 0) {
      return " where " + expr;
    } else {
      return "";
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return getExpresion();
  }

  /**
   * パラメータリストを取得する。
   * 
   * @return パラメータリスト
   */
  public List<Object> getParameters() {
    return parameters;
  }

  /**
   * カラム名を取得する。
   * 
   * @param propertyName プロパティ名
   * @return カラム名
   */
  private String getColumnName(String propertyName) {
    return entityNameResolver.getEntityPropertyName(entityClass, propertyName);
  }
  
  /**
   * 置き換え
   * 
   * @param value 値
   * @return 値
   */
  private String replaceValue(String value) {
    if (value.contains("%")) {
      return value.replaceAll("%", "/%");
    } else {
      return value;
    }
  }
  
}
