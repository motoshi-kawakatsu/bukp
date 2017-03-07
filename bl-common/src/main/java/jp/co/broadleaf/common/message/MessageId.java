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
package jp.co.broadleaf.common.message;

import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.DomainEntityId;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * メッセージマスタエンティティのIDクラス
 */
public class MessageId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public MessageId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param applyStartDt 適用開始日
   * @param msgId メッセージID
   * @param localeId ロケールID
   */
  public MessageId(Date applyStartDt, String msgId, String localeId) {
    setApplyStartDt(applyStartDt);
    setMsgId(msgId);
    setLocaleId(localeId);
  }

  /**
   * 適用開始日を返します。
   * 
   * @return 適用開始日
   */
  public Date getApplyStartDt() {
    return container().getPropertyValue(Message.APPLY_START_DT, Date.class);
  }

  /**
   * 適用開始日を設定します。
   * 
   * @param applyStartDt セットする適用開始日
   */
  public void setApplyStartDt(Date applyStartDt) {
    container().setPropertyValue(Message.APPLY_START_DT, applyStartDt);
  }

  /**
   * メッセージIDを返します。
   * 
   * @return メッセージID
   */
  public String getMsgId() {
    return container().getPropertyValue(Message.MSG_ID, String.class);
  }

  /**
   * メッセージIDを設定します。
   * 
   * @param msgId セットするメッセージID
   */
  public void setMsgId(String msgId) {
    container().setPropertyValue(Message.MSG_ID, msgId);
  }

  /**
   * ロケールIDを返します。
   * 
   * @return ロケールID
   */
  public String getLocaleId() {
    return container().getPropertyValue(Message.LOCALE_ID, String.class);
  }

  /**
   * ロケールIDを設定します。
   * 
   * @param localeId セットするロケールID
   */
  public void setLocaleId(String localeId) {
    container().setPropertyValue(Message.LOCALE_ID, localeId);
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
    list.add(new PropertyDefinition(Message.APPLY_START_DT, Date.class, false));
    list.add(new PropertyDefinition(Message.MSG_ID, String.class, false));
    list.add(new PropertyDefinition(Message.LOCALE_ID, String.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
