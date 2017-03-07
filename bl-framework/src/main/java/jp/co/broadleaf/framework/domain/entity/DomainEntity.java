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

import jp.co.broadleaf.framework.bean.BeanCloneException;
import jp.co.broadleaf.framework.bean.PropertyContainer;
import jp.co.broadleaf.framework.bean.PropertyDefinition;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * ドメインエンティティインタフェース
 * 
 * @param <ID> ドメインエンティティIDクラス
 */
@SuppressWarnings("serial")
public abstract class DomainEntity<ID extends Serializable> implements Serializable, Cloneable {

  /**
   * エンティティIDのクラス
   */
  private Class<ID> identifierClass;

  /**
   * プロパティコンテナ
   */
  private PropertyContainer propertyContainer;

  /**
   * コンストラクタ
   */
  @SuppressWarnings("unchecked")
  public DomainEntity() {
    identifierClass = (Class<ID>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    propertyContainer = new PropertyContainer(defineProperties());
  }

  /**
   * プロパティリストを取得する。
   * 
   * @return プロパティリスト
   */
  protected abstract PropertyDefinition[] defineProperties();

  /**
   * ドメインエンティティIDを取得する
   * 
   * @return ドメインエンティティID
   */
  public abstract ID identifier();

  /**
   * プロパティコンテナを取得する。
   * 
   * @return プロパティコンテナ
   */
  public final PropertyContainer container() {
    return propertyContainer;
  }

  /**
   * エンティティIDクラスタイプを取得する
   * 
   * @return エンティティIDクラスタイプ
   */
  public final Class<ID> identifierClass() {
    return identifierClass;
  }

  /**
   * エンティティIDのプロパティリストを取得する
   * 
   * @return エンティティIDのプロパティリスト
   */
  public String[] identifierPropertyNames() {
    if (DomainEntityId.class.isAssignableFrom(identifierClass)) {
      ID entityId = identifier();
      Validate.notNull(entityId, "The entityId must not be null");
      return ((DomainEntityId) entityId).container().getPropertyNames();
    } else {
      throw new NotImplementedException("Not implement, child class should override the method");
    }
  }

  /**
   * <pre>
   * 親エンティティを取得する
   * 親エンティティがある場合、Ovrerideで実装してください。
   * </pre>
   * 
   * @return 親エンティティ
   */
  public DomainEntity<?> parent() {
    return null;
  }

  /**
   * エンティティの制限をチェックする
   * 
   * @throws EntityConstraintException チェックNG場合、この例外をスローする
   */
  public void validate() {
    // エンティティIDの制限をチェックする
    DomainEntityId.validate(identifier());
    // エンティティの制限をチェックする
    container().validate();
  }

  /**
   * 指定されたドメインエンティティのIDと比較する
   * 
   * @param other 指定されたドメインエンティティ
   * @return true:IDが同じ
   */
  public final boolean sameIdAs(DomainEntity<ID> other) {
    if (this == other) {
      return true;
    }
    if (other == null) {
      return false;
    }
    if (identifier() == null) {
      return false;
    }
    return identifier().equals(other.identifier());
  }

  /**
   * <pre>
   * エンティティの各項目の値を比較する。
   * これはデフォールト実現です。パフォーマンスのために、子クラスにOverrideしたほうがいい。
   * </pre>
   * 
   * @param obj 別のエンティティ
   * @return true:同じ
   */
  public final boolean sameValueAs(final Object obj) {
    return equals(obj);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public Object clone() {
    DomainEntity<ID> clone;
    try {
      clone = (DomainEntity<ID>) super.clone();
    } catch (CloneNotSupportedException ex) {
      throw new BeanCloneException(ex);
    }
    clone.identifierClass = identifierClass;
    clone.propertyContainer = (PropertyContainer) propertyContainer.clone();
    return clone;
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
    DomainEntity<?> other = (DomainEntity<?>) obj;
    if (!propertyContainer.equals(other.propertyContainer)) {
      return false;
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return propertyContainer.toString();
  }

}
