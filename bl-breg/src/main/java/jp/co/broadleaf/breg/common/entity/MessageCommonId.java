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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * メッセージマスタ(共有)エンティティのIDクラス
 */
public class MessageCommonId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public MessageCommonId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param applyStartDt 適用開始日
   */
  public MessageCommonId(Date applyStartDt) {
    setApplyStartDt(applyStartDt);
  }

  /**
   * 適用開始日を返します。
   * 
   * @return 適用開始日
   */
  public Date getApplyStartDt() {
    return container().getPropertyValue(MessageCommon.APPLY_START_DT, Date.class);
  }

  /**
   * 適用開始日を設定します。
   * 
   * @param applyStartDt セットする適用開始日
   */
  public void setApplyStartDt(Date applyStartDt) {
    container().setPropertyValue(MessageCommon.APPLY_START_DT, applyStartDt);
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
    list.add(new PropertyDefinition(MessageCommon.APPLY_START_DT, Date.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
