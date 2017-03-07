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

import java.io.Serializable;

/**
 * プロパティ定義用クラスです。
 */
public class PropertyDefinition implements Serializable {

  /** serialVersionUID */
  private static final long serialVersionUID = -2368209656252725364L;

  /**
   * プロパティ名
   */
  private String name;

  /**
   * プロパティタイプ
   */
  private Class<?> type;

  /**
   * NULL可能フラグ
   */
  private boolean nullable;

  /**
   * コンストラクタ
   *
   * @param name プロパティ名
   * @param type プロパティタイプ
   * @param nullable NULL可能フラグ
   */
  public PropertyDefinition(String name, Class<?> type, boolean nullable) {
    this.name = name;
    this.type = type;
    this.nullable = nullable;
  }

  /**
   * プロパティ名を取得する。
   *
   * @return プロパティ名
   */
  public String getName() {
    return name;
  }

  /**
   * プロパティタイプを取得する。
   *
   * @return プロパティタイプ
   */
  public Class<?> getType() {
    return type;
  }

  /**
   * NULL可能フラグを取得する。
   * 
   * @return NULL可能フラグ
   */
  public boolean isNullable() {
    return nullable;
  }

}
