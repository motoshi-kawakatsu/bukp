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
 * 種別コードマストエンティティのIDクラス
 */
public class KindCodeMasterCommonId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public KindCodeMasterCommonId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param kindCd 種別コード
   * @param kindName 種別名称
   */
  public KindCodeMasterCommonId(int kindCd, String kindName) {
    setKindCd(kindCd);
    setKindName(kindName);
  }

  /**
   * 種別コードを返します。
   * 
   * @return 種別コード
   */
  public int getKindCd() {
    return container().getPropertyValue(KindCodeMasterCommon.KIND_CD, Integer.class);
  }

  /**
   * 種別コードを設定します。
   * 
   * @param kindCd セットする種別コード
   */
  public void setKindCd(int kindCd) {
    container().setPropertyValue(KindCodeMasterCommon.KIND_CD, kindCd);
  }

  /**
   * 種別名称を返します。
   * 
   * @return 種別名称
   */
  public String getKindName() {
    return container().getPropertyValue(KindCodeMasterCommon.KIND_NAME, String.class);
  }

  /**
   * 種別名称を設定します。
   * 
   * @param kindName セットする種別名称
   */
  public void setKindName(String kindName) {
    container().setPropertyValue(KindCodeMasterCommon.KIND_NAME, kindName);
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
    list.add(new PropertyDefinition(KindCodeMasterCommon.KIND_CD, Integer.class, false));
    list.add(new PropertyDefinition(KindCodeMasterCommon.KIND_NAME, String.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
