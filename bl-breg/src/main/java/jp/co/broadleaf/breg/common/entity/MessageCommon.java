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

import jp.co.broadleaf.common.domain.entity.BroadleafDomainEntity;
import jp.co.broadleaf.common.domain.entity.BroadleafOptimisticConcurrencyResolver;
import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.Entity;
import jp.co.broadleaf.framework.domain.entity.OptimisticConcurrency;

import org.apache.commons.lang3.ArrayUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * メッセージマスタ(共有)
 */
@Entity(name = "t_message_common", type = "common")
@OptimisticConcurrency(resolver = BroadleafOptimisticConcurrencyResolver.class)
public class MessageCommon extends BroadleafDomainEntity<MessageCommonId> {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * 適用開始日
   */
  public static final String APPLY_START_DT = "applyStartDt";

  /**
   * 適用終了日
   */
  public static final String APPLY_END_DT = "applyEndDt";

  /**
   * メッセージID
   */
  public static final String MSG_ID = "msgId";

  /**
   * ロケールID
   */
  public static final String LOCALE_ID = "localeId";

  /**
   * プロダクトコード
   */
  public static final String PRODUCT_CD = "productCd";

  /**
   * メッセージタイプ
   */
  public static final String MSG_TYPE = "msgType";

  /**
   * メッセージタイトル
   */
  public static final String MSG_TITLE = "msgTitle";

  /**
   * メッセージ内容
   */
  public static final String MSG_CONTENTS = "msgContents";

  /**
   * コンストラクタ。
   */
  public MessageCommon() {

  }

  /**
   * コンストラクタ。
   * 
   * @param id ID
   */
  public MessageCommon(MessageCommonId id) {
    setApplyStartDt(id.getApplyStartDt());
  }

  /**
   * 適用開始日を返します。
   * 
   * @return 適用開始日
   */
  public Date getApplyStartDt() {
    return container().getPropertyValue(APPLY_START_DT, Date.class);
  }

  /**
   * 適用開始日を設定します。
   * 
   * @param applyStartDt セットする適用開始日
   */
  public void setApplyStartDt(Date applyStartDt) {
    container().setPropertyValue(APPLY_START_DT, applyStartDt);
  }

  /**
   * 適用終了日を返します。
   * 
   * @return 適用終了日
   */
  public Date getApplyEndDt() {
    return container().getPropertyValue(APPLY_END_DT, Date.class);
  }

  /**
   * 適用終了日を設定します。
   * 
   * @param applyEndDt セットする適用終了日
   */
  public void setApplyEndDt(Date applyEndDt) {
    container().setPropertyValue(APPLY_END_DT, applyEndDt);
  }

  /**
   * メッセージIDを返します。
   * 
   * @return メッセージID
   */
  public String getMsgId() {
    return container().getPropertyValue(MSG_ID, String.class);
  }

  /**
   * メッセージIDを設定します。
   * 
   * @param msgId セットするメッセージID
   */
  public void setMsgId(String msgId) {
    container().setPropertyValue(MSG_ID, msgId);
  }

  /**
   * ロケールIDを返します。
   * 
   * @return ロケールID
   */
  public String getLocaleId() {
    return container().getPropertyValue(LOCALE_ID, String.class);
  }

  /**
   * ロケールIDを設定します。
   * 
   * @param localeId セットするロケールID
   */
  public void setLocaleId(String localeId) {
    container().setPropertyValue(LOCALE_ID, localeId);
  }

  /**
   * プロダクトコードを返します。
   * 
   * @return プロダクトコード
   */
  public int getProductCd() {
    return container().getPropertyValue(PRODUCT_CD, Integer.class);
  }

  /**
   * プロダクトコードを設定します。
   * 
   * @param productCd セットするプロダクトコード
   */
  public void setProductCd(int productCd) {
    container().setPropertyValue(PRODUCT_CD, productCd);
  }

  /**
   * メッセージタイプを返します。
   * 
   * @return メッセージタイプ
   */
  public String getMsgType() {
    return container().getPropertyValue(MSG_TYPE, String.class);
  }

  /**
   * メッセージタイプを設定します。
   * 
   * @param msgType セットするメッセージタイプ
   */
  public void setMsgType(String msgType) {
    container().setPropertyValue(MSG_TYPE, msgType);
  }

  /**
   * メッセージタイトルを返します。
   * 
   * @return メッセージタイトル
   */
  public String getMsgTitle() {
    return container().getPropertyValue(MSG_TITLE, String.class);
  }

  /**
   * メッセージタイトルを設定します。
   * 
   * @param msgTitle セットするメッセージタイトル
   */
  public void setMsgTitle(String msgTitle) {
    container().setPropertyValue(MSG_TITLE, msgTitle);
  }

  /**
   * メッセージ内容を返します。
   * 
   * @return メッセージ内容
   */
  public String getMsgContents() {
    return container().getPropertyValue(MSG_CONTENTS, String.class);
  }

  /**
   * メッセージ内容を設定します。
   * 
   * @param msgContents セットするメッセージ内容
   */
  public void setMsgContents(String msgContents) {
    container().setPropertyValue(MSG_CONTENTS, msgContents);
  }

  /**
   * エンティティIDを取得する。
   * 
   * @return エンティティID
   */
  @Override
  public MessageCommonId identifier() {
    return new MessageCommonId(getApplyStartDt());
  }

  /**
   * プロパティ定義の配列を取得する。
   * 
   * @return プロパティ定義の配列
   */
  @Override
  public PropertyDefinition[] defineProperties() {
    return ArrayUtils.addAll(super.defineProperties(), definitions);
  }

  /**
   * プロパティ定義の配列
   */
  private static PropertyDefinition[] definitions;

  static {
    List<PropertyDefinition> list = new ArrayList<PropertyDefinition>();
    list.add(new PropertyDefinition(APPLY_START_DT, Date.class, false));
    list.add(new PropertyDefinition(APPLY_END_DT, Date.class, false));
    list.add(new PropertyDefinition(MSG_ID, String.class, false));
    list.add(new PropertyDefinition(LOCALE_ID, String.class, false));
    list.add(new PropertyDefinition(PRODUCT_CD, Integer.class, false));
    list.add(new PropertyDefinition(MSG_TYPE, String.class, true));
    list.add(new PropertyDefinition(MSG_TITLE, String.class, true));
    list.add(new PropertyDefinition(MSG_CONTENTS, String.class, true));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
