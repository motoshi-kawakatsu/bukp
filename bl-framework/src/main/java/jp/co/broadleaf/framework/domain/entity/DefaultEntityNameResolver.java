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

/**
 * エンティティの名称取得用実現クラス
 */
public class DefaultEntityNameResolver implements EntityNameResolver {

  /**
   * エンティティ名の接頭辞
   */
  private String entityPrefix;

  /**
   * プロパティ名の接頭辞
   */
  private String propertyPrefix;

  /**
   * エンティティ名を取得する
   * 
   * @param entityClass エンティティクラス
   * @return エンティティ名
   */
  @Override
  public String getEntityName(Class<?> entityClass) {
    Entity entityAnnotation = entityClass.getAnnotation(Entity.class);
    String name;
    if (entityAnnotation != null) {
      if (entityAnnotation.name() == null || entityAnnotation.name().trim().length() == 0) {
        throw new IllegalArgumentException("Entity name must not be empty");
      }
      name = entityAnnotation.name();
    } else {
      name = getNormalizedName(entityClass.getSimpleName());
    }
    if (getEntityPrefix() != null) {
      name = getEntityPrefix() + name;
    }
    return name;
  }

  /**
   * エンティティのプロパティ名を取得する
   * 
   * @param entityClass エンティティクラス
   * @param propertyName プロパティ名
   * @return エンティティのプロパティ名
   */
  @Override
  public String getEntityPropertyName(Class<?> entityClass, String propertyName) {
    String name = getNormalizedName(propertyName);
    if (getPropertyPrefix() != null) {
      name = getPropertyPrefix() + name;
    }
    return name;
  }

  /**
   * 正規化名称を取得する
   * 
   * @param name エンティティ名称
   * @return 正規化名称
   */
  protected String getNormalizedName(String name) {
    StringBuilder buf = new StringBuilder();
    char[] chars = name.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      char c = chars[i];
      if (i > 0 && (Character.isUpperCase(c) || Character.isDigit(c))) {
        buf.append('_');
      }
      buf.append(Character.toLowerCase(c));
    }
    return buf.toString();
  }

  /**
   * エンティティ名の接頭辞を取得する
   * 
   * @return エンティティ名の接頭辞
   */
  public String getEntityPrefix() {
    return entityPrefix;
  }

  /**
   * エンティティ名の接頭辞を設定する
   * 
   * @param entityPrefix エンティティ名の接頭辞
   */
  public void setEntityPrefix(String entityPrefix) {
    this.entityPrefix = entityPrefix;
  }

  /**
   * プロパティ名の接頭辞を取得する
   * 
   * @return プロパティ名の接頭辞
   */
  public String getPropertyPrefix() {
    return propertyPrefix;
  }

  /**
   * プロパティ名の接頭辞を設定する
   * 
   * @param propertyPrefix プロパティ名の接頭辞
   */
  public void setPropertyPrefix(String propertyPrefix) {
    this.propertyPrefix = propertyPrefix;
  }

}
