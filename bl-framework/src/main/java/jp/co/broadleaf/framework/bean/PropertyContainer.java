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

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * プロパティコンテナクラスです。
 */
public class PropertyContainer implements Cloneable {

  /**
   * <pre>
   * プロパティルックアップテーブル
   * 挿入順序を維持するために、LinkedHashMapを利用する。
   * </pre>
   */
  private LinkedHashMap<String, Property> lookupTable;

  /**
   * コンストラクタ
   *
   * @param definitions プロパティリスト
   */
  public PropertyContainer(PropertyDefinition[] definitions) {
    setupPropertyMap(definitions);
  }

  /**
   * プロパティ値など格納用のマップを初期化する。
   * 
   * @param propertyDefs プロパティリスト
   */
  private void setupPropertyMap(final PropertyDefinition[] propertyDefs) {
    if (propertyDefs == null) {
      throw new IllegalArgumentException("The propertyList must not be null.");
    }
    // コンテナ構築した後、プロパティのアクセスパフォーマンスを向上するために、空プロパティ値コンテナをマップに追加する。
    lookupTable = new LinkedHashMap<String, Property>();
    for (PropertyDefinition definition : propertyDefs) {
      if (definition.getName() == null) {
        throw new IllegalArgumentException("The property's name must not be null.");
      }
      if (definition.getType() == null) {
        throw new IllegalArgumentException("The property's type must not be null.");
      }
      Object initValue = null;
      if (!definition.isNullable()) {
        initValue = PrimitiveDefaults.getInstance().getDefaultValue(definition.getType());
      }
      lookupTable.put(definition.getName(), new Property(definition, initValue));
    }
  }

  /**
   * 変更したプロパティの名称配列を取得する。
   * 
   * @return 変更したプロパティの名称配列
   */
  public final String[] getChangedPropertyNames() {
    List<String> result = new ArrayList<String>();
    for (Property property : lookupTable.values()) {
      if (property.isChanged()) {
        result.add(property.getName());
      }
    }
    return result.toArray(new String[0]);
  }

  /**
   * プロパティのインデックスを取得する。
   * 
   * @param propertyName プロパティ名
   * @return プロパティのインデックス
   */
  public final int getPropertyIndex(String propertyName) {
    int index = 0;
    for (String name : lookupTable.keySet()) {
      if (name.equals(propertyName)) {
        return index;
      }
      index++;
    }
    return -1;
  }

  /**
   * プロパティ名リストを取得する。
   * 
   * @return プロパティ名リスト
   */
  public final String[] getPropertyNames() {
    return new ArrayList<String>(lookupTable.keySet()).toArray(new String[0]);
  }

  /**
   * プロパティ名よりプロパティタイプを取得する。
   * 
   * @param propertyName プロパティ名
   * @return プロパティタイプ
   */
  public Class<?> getPropertyType(String propertyName) {
    return lookupPropertyByName(propertyName).getType();
  }

  /**
   * プロパティ名よりプロパティ値を取得する。
   * 
   * @param propertyName プロパティ名
   * @return プロパティ値
   */
  public Object getPropertyValue(String propertyName) {
    return lookupPropertyByName(propertyName).getValue();
  }

  /**
   * プロパティ名とプロパティのタイプよりプロパティ値を取得する。
   * 
   * @param <T> プロパティのタイプ
   * @param propertyName プロパティ名
   * @param propertyType プロパティのタイプ
   * @return プロパティ値
   */
  @SuppressWarnings("unchecked")
  public <T> T getPropertyValue(String propertyName, Class<T> propertyType) {
    if (propertyType == null) {
      throw new IllegalArgumentException("The parameter 'propertyType' must not be null.");
    }
    Property property = lookupPropertyByName(propertyName);
    if (propertyType.isAssignableFrom(property.getType())) {
      return (T) property.getValue();
    } else {
      throw new PropertyAccessException("The property value cannot assgin to " + propertyType.getName());
    }
  }

  /**
   * プロパティ値のコンテナを取得する
   * 
   * @param propertyName プロパティ名
   * @return プロパティ値のコンテナ
   */
  private Property lookupPropertyByName(String propertyName) {
    if (propertyName == null) {
      throw new IllegalArgumentException("The parameter 'PropertyName' must not be null.");
    }
    Property property = lookupTable.get(propertyName);
    if (property == null) {
      throw new PropertyAccessException("The " + propertyName + " property is not exists.");
    }
    return property;
  }

  /**
   * プロパティ値リストを取得する。
   * 
   * @return プロパティ値リスト
   */
  public Object[] getPropertyValues() {
    List<Object> result = new ArrayList<Object>();
    for (Property property : lookupTable.values()) {
      result.add(property.getValue());
    }
    return result.toArray(new Object[0]);
  }

  /**
   * プロパティ名を存在するかどうかことを判断する。
   * 
   * @param propertyName プロパティ名
   * @return 判断結果 True:存在 False:存在しない
   */
  public boolean hasProperty(String propertyName) {
    return lookupTable.containsKey(propertyName);
  }

  /**
   * プロパティ値が変更かどうかことを判断する。
   * 
   * @param propertyName プロパティ名
   * @return 判断結果 True:変更した False:変更しない
   */
  public boolean isPropertyChanged(String propertyName) {
    Property property = lookupPropertyByName(propertyName);
    return property.isChanged();
  }

  /**
   * 変更かどうかことを判断する。
   * 
   * @return True:変更済み False:変更なし
   */
  public boolean isChanged() {
    return isChanged(null);
  }

  /**
   * 変更かどうかことを判断する。
   * 
   * @param excepted チェック外プルパティ名配列
   * @return True:変更済み False:変更なし
   */
  public boolean isChanged(String[] excepted) {
    for (Property property : lookupTable.values()) {
      if (property.isChanged()) {
        if (excepted != null && ArrayUtils.contains(excepted, property.getName())) {
          continue;
        } else {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * プロパティ値を取得する。
   * 
   * @param propertyName プロパティ名
   * @param value プロパティ値
   */
  public void setPropertyValue(String propertyName, Object value) {
    Property property = lookupPropertyByName(propertyName);
    if (value == null) {
      property.setValue(null);
    } else if (property.getType().isAssignableFrom(value.getClass())) {
      property.setValue(value);
    } else {
      throw new PropertyAccessException("The property value cannot assgin to " + property.getType());
    }
  }

  /**
   * すべでのプロパティの変更状態を認める。
   */
  public void acceptChanges() {
    for (Property property : lookupTable.values()) {
      property.acceptChanges();
    }
  }

  /**
   * コンテナの全てプロパティ制約チェックを実行する。
   * 
   * @throws PropertyConstraintException 制約チェックエラーがある場合、この
   */
  public void validate() {
    for (Property property : lookupTable.values()) {
      property.validate();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object clone() {
    PropertyContainer clone = null;
    try {
      clone = (PropertyContainer) super.clone();
    } catch (CloneNotSupportedException ex) {
      throw new BeanCloneException(ex);
    }
    LinkedHashMap<String, Property> lookupTableClone = new LinkedHashMap<String, Property>();
    for (Map.Entry<String, Property> entry : lookupTable.entrySet()) {
      lookupTableClone.put(entry.getKey(), (Property) entry.getValue().clone());
    }
    clone.lookupTable = lookupTableClone;
    return clone;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((lookupTable == null) ? 0 : lookupTable.hashCode());
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
    PropertyContainer other = (PropertyContainer) obj;
    if (lookupTable == null) {
      if (other.lookupTable != null) {
        return false;
      }
    } else if (!lookupTable.equals(other.lookupTable)) {
      return false;
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return lookupTable.toString();
  }

}
