//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : xuxx
// 作 成 日       2017/01/30  修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.common.property.impl;

import jp.co.broadleaf.common.property.PropertyFacade;
import jp.co.broadleaf.common.property.PropertyKey;
import jp.co.broadleaf.common.property.PropertyResolver;

import javax.annotation.Resource;

/**
 * <pre>
 * Broadleafプロジェクト用プロパティFacade実現用クラスです。
 * </pre>
 */
public class PropertyFacadeImpl implements PropertyFacade {

  /**
   * プロパティ値を取得する。
   * 
   * @param <T> プロパティのタイプ
   * @param key プロパティキー
   * @return プロパティ値
   */
  @Override
  public <T> T getProperty(PropertyKey<T> key) {

    // プロパティ取得を呼び出す
    return propertyResolver.getProperty(key);
  }

  /** プロパティ取得 */
  private PropertyResolver propertyResolver;

  /**
   * <pre>
   * プロパティ取得を設定する。
   * </pre>
   *
   * @param propertyResolver プロパティ取得
   */
  @Resource
  public void setPropertyResolver(PropertyResolver propertyResolver) {
    this.propertyResolver = propertyResolver;
  }

}
