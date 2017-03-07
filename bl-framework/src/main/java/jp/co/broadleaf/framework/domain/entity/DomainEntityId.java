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
package jp.co.broadleaf.framework.domain.entity;

import jp.co.broadleaf.framework.bean.PropertyContainer;
import jp.co.broadleaf.framework.bean.PropertyDefinition;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * ドメインエンティティID
 */
@SuppressWarnings("serial")
public abstract class DomainEntityId implements Serializable {

  /**
   * プロパティリストを取得する。
   * 
   * @return プロパティリスト
   */
  protected abstract PropertyDefinition[] defineProperties();

  /**
   * プロパティコンテナ
   */
  private PropertyContainer propertyContainer;

  /**
   * コンストラクタ
   */
  public DomainEntityId() {
    propertyContainer = new PropertyContainer(defineProperties());
  }

  /**
   * キー情報を取得する（データストア用）
   * 
   * @return キー情報
   */
  public String toKey() {
    Object[] values = container().getPropertyValues();
    return StringUtils.join(values, ',');
  }

  /**
   * プロパティコンテナを取得する。
   * 
   * @return プロパティコンテナ
   */
  public final PropertyContainer container() {
    return propertyContainer;
  }

  /**
   * <pre>
   * IDが有効かどうかことをチェックする。
   * エンティティIDがLongなどタイプが利用できるので、staticチェックメソッドを利用する。
   * </pre>
   * 
   * @param id IDオブジェクト
   * @throws EntityConstraintException チェックNG場合、この例外をスローする
   */
  public static void validate(Object id) {
    if (id == null) {
      throw new IllegalArgumentException("Id must not be null");
    }
    Class<?> idClass = id.getClass();
    if (DomainEntityId.class.isAssignableFrom(idClass)) {
      PropertyContainer container = ((DomainEntityId) id).container();
      String[] propNames = container.getPropertyNames();
      for (String propName : propNames) {
        if (container.getPropertyValue(propName) == null) {
          throw new IllegalArgumentException("Id property[" + propName + "]'s value must not be null");
        }
      }
    } else if (String.class.equals(idClass) || ClassUtils.isPrimitiveOrWrapper(idClass)) {
      return;
    } else {
      throw new IllegalArgumentException("Id's type is not support");
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + propertyContainer.hashCode();
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
    DomainEntityId other = (DomainEntityId) obj;
    if (!propertyContainer.equals(other.propertyContainer)) {
      return false;
    }
    return true;
  }
}
