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
package jp.co.broadleaf.framework.bean;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;

/**
 * プロパティ値など格納用コンテナクラスです。
 */
public class Property implements Cloneable {

  /**
   * プロパティの定義
   */
  private PropertyDefinition definition;

  /**
   * プロパティ値
   */
  private Object value;

  /**
   * 値変更フラグ
   */
  private boolean changed;

  /**
   * コンストラクタ
   * 
   * @param definition プロパティタイプ
   */
  public Property(PropertyDefinition definition) {
    this.definition = definition;
  }

  /**
   * コンストラクタ
   * 
   * @param definition プロパティタイプ
   * @param value 初期値
   */
  public Property(PropertyDefinition definition, Object value) {
    this.definition = definition;
    this.value = value;
  }

  /**
   * プロパティ名を取得する。
   *
   * @return プロパティ名
   */
  public String getName() {
    return definition.getName();
  }

  /**
   * プロパティタイプを取得する。
   *
   * @return プロパティタイプ
   */
  public Class<?> getType() {
    return definition.getType();
  }

  /**
   * NULL可能フラグを取得する。
   * 
   * @return NULL可能フラグ
   */
  public boolean isNullable() {
    return definition.isNullable();
  }

  /**
   * プロパティ値を取得する。
   * 
   * @return プロパティ値
   */
  public Object getValue() {
    return value;
  }

  /**
   * プロパティ値を設定する。
   * 
   * @param value プロパティ値
   */
  public void setValue(Object value) {
    this.value = value;
    this.changed = true;
  }

  /**
   * <pre>
   * 変更かどうかことを取得する。
   * </pre>
   *
   * @return True:変更済み False:変更なし
   */
  public boolean isChanged() {
    return this.changed;
  }

  /**
   * 変更状態を認める。
   */
  public void acceptChanges() {
    this.changed = false;
  }

  /**
   * プロパティ制約チェックを実行する。
   * 
   * @throws PropertyConstraintException 制約チェックエラーがある場合、この
   */
  public void validate() {
    // NOT NULL制約のチェックを実行する
    if (getValue() == null && !isNullable()) {
      throw new PropertyConstraintException("NOT NULL constraint failed: " + getName());
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object clone() {
    Property clone = null;
    try {
      clone = (Property) super.clone();
    } catch (CloneNotSupportedException ex) {
      throw new BeanCloneException(ex);
    }
    clone.definition = definition;
    if (value == null) {
      clone.value = null;
    } else if (String.class.equals(value.getClass())) {
      clone.value = value;
    } else if (BigDecimal.class.equals(value.getClass())) {
      clone.value = value;
    } else if (ClassUtils.isPrimitiveOrWrapper(value.getClass())) {
      clone.value = value;
    } else {
      clone.value = ObjectUtils.clone(value);
      if (clone.value == null) {
        throw new BeanCloneException("Cannot clone the value of " + getName() + " property");
      }
    }
    clone.changed = changed;
    return clone;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((value == null) ? 0 : value.hashCode());
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
    Property other = (Property) obj;
    if (value == null) {
      if (other.value != null) {
        return false;
      }
    } else if (value instanceof BigDecimal && ((BigDecimal) value).compareTo((BigDecimal) other.value) != 0) {
      return false;
    } else if (!value.equals(other.value)) {
      return false;
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return value != null ? value.toString() : null;
  }

}
