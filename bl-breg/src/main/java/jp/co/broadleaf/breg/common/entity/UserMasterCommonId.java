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
package jp.co.broadleaf.breg.common.entity;

import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.DomainEntityId;

import java.util.ArrayList;
import java.util.List;

/**
 * 担当者情報マスタエンティティのIDクラス
 */
public class UserMasterCommonId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public UserMasterCommonId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param makerCode メーカーコード
   * @param loginId ログインID
   */
  public UserMasterCommonId(int makerCode, String loginId) {
    setMakerCode(makerCode);
    setLoginId(loginId);
  }

  /**
   * メーカーコードを返します。
   * 
   * @return メーカーコード
   */
  public int getMakerCode() {
    return container().getPropertyValue(UserMasterCommon.MAKER_CODE, Integer.class);
  }

  /**
   * メーカーコードを設定します。
   * 
   * @param makerCode セットするメーカーコード
   */
  public void setMakerCode(int makerCode) {
    container().setPropertyValue(UserMasterCommon.MAKER_CODE, makerCode);
  }

  /**
   * ログインIDを返します。
   * 
   * @return ログインID
   */
  public String getLoginId() {
    return container().getPropertyValue(UserMasterCommon.LOGIN_ID, String.class);
  }

  /**
   * ログインIDを設定します。
   * 
   * @param loginId セットするログインID
   */
  public void setLoginId(String loginId) {
    container().setPropertyValue(UserMasterCommon.LOGIN_ID, loginId);
  }

  /**
   * プロパティ定義の配列を取得する。
   * 
   * @return プロパティ定義の配列
   */
  @Override
  public PropertyDefinition[] defineProperties() {
    return definitions;
  }

  /**
   * プロパティ定義の配列
   */
  private static PropertyDefinition[] definitions;

  static {
    List<PropertyDefinition> list = new ArrayList<PropertyDefinition>();
    list.add(new PropertyDefinition(UserMasterCommon.MAKER_CODE, Integer.class, false));
    list.add(new PropertyDefinition(UserMasterCommon.LOGIN_ID, String.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
