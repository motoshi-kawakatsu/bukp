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
package jp.co.broadleaf.common.domain.entity;

import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.DomainEntity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * ドメインエンティティインタフェース
 * 
 * @param <ID> ドメインエンティティIDクラス
 */
@SuppressWarnings("serial")
public abstract class BroadleafDomainEntity<ID extends Serializable> extends DomainEntity<ID> {

  /**
   * UUID
   */
  public static final String UUID = "uuid";

  /**
   * 作成日時
   */
  public static final String INS_DT_TIME = "insDtTime";

  /**
   * 更新日時
   */
  public static final String UPD_DT_TIME = "updDtTime";

  /**
   * 作成アカウントID
   */
  public static final String INS_ACCOUNT_ID = "insAccountId";

  /**
   * 更新アカウントID
   */
  public static final String UPD_ACCOUNT_ID = "updAccountId";

  /**
   * 論理削除区分
   */
  public static final String LOGICAL_DEL_DIV = "logicalDelDiv";

  /**
   * 企業コード
   */
  public static final String COMPANY_CD = "companyCd";

  /**
   * 適用開始日
   */
  public static final String APPLY_START_DT = "applyStartDt";

  /**
   * 適用終了日
   */
  public static final String APPLY_END_DT = "applyEndDt";

  /**
   * 論理削除区分 0:有効
   */
  public static final int LOGICAL_UNDELETED = 0;

  /**
   * 論理削除区分 1:論理削除
   */
  public static final int LOGICAL_DELETED = 1;

  /**
   * UUIDを返します。
   * 
   * @return UUID
   */
  public String getUuid() {
    return container().getPropertyValue(UUID, String.class);
  }

  /**
   * UUIDを設定します。
   * 
   * @param uuid セットするUUID
   */
  public void setUuid(String uuid) {
    container().setPropertyValue(UUID, uuid);
  }

  /**
   * 作成日時を返します。
   * 
   * @return 作成日時
   */
  public Timestamp getInsDtTime() {
    return container().getPropertyValue(INS_DT_TIME, Timestamp.class);
  }

  /**
   * 作成日時を設定します。
   * 
   * @param insertDateTime セットする作成日時
   */
  public void setInsDtTime(Timestamp insertDateTime) {
    container().setPropertyValue(INS_DT_TIME, insertDateTime);
  }

  /**
   * 更新日時を返します。
   * 
   * @return 更新日時
   */
  public Timestamp getUpdDtTime() {
    return container().getPropertyValue(UPD_DT_TIME, Timestamp.class);
  }

  /**
   * 更新日時を設定します。
   * 
   * @param updateDateTime セットする更新日時
   */
  public void setUpdDtTime(Timestamp updateDateTime) {
    container().setPropertyValue(UPD_DT_TIME, updateDateTime);
  }

  /**
   * 作成アカウントIDを返します。
   * 
   * @return 作成アカウントID
   */
  public String getInsAccountId() {
    return container().getPropertyValue(INS_ACCOUNT_ID, String.class);
  }

  /**
   * 作成アカウントIDを設定します。
   * 
   * @param insertAccountId セットする作成アカウントID
   */
  public void setInsAccountId(String insertAccountId) {
    container().setPropertyValue(INS_ACCOUNT_ID, insertAccountId);
  }

  /**
   * 更新アカウントIDを返します。
   * 
   * @return 更新アカウントID
   */
  public String getUpdAccountId() {
    return container().getPropertyValue(UPD_ACCOUNT_ID, String.class);
  }

  /**
   * 更新アカウントIDを設定します。
   * 
   * @param updateAccountId セットする更新アカウントID
   */
  public void setUpdAccountId(String updateAccountId) {
    container().setPropertyValue(UPD_ACCOUNT_ID, updateAccountId);
  }

  /**
   * 論理削除区分を返します。
   * 
   * @return 論理削除区分
   */
  public int getLogicalDelDiv() {
    return container().getPropertyValue(LOGICAL_DEL_DIV, Integer.class);
  }

  /**
   * 論理削除区分を設定します。
   * 
   * @param logicalDeleteDivision セットする論理削除区分
   */
  public void setLogicalDelDiv(int logicalDeleteDivision) {
    container().setPropertyValue(LOGICAL_DEL_DIV, logicalDeleteDivision);
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
    list.add(new PropertyDefinition(UUID, String.class, false));
    list.add(new PropertyDefinition(INS_DT_TIME, Timestamp.class, false));
    list.add(new PropertyDefinition(UPD_DT_TIME, Timestamp.class, false));
    list.add(new PropertyDefinition(INS_ACCOUNT_ID, String.class, false));
    list.add(new PropertyDefinition(UPD_ACCOUNT_ID, String.class, false));
    list.add(new PropertyDefinition(LOGICAL_DEL_DIV, Integer.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }
}
