package jp.co.broadleaf.breg.common.entity;

import jp.co.broadleaf.framework.bean.PropertyDefinition;
import jp.co.broadleaf.framework.domain.entity.DomainEntityId;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品マスタ(メーカー)仮エンティティのIDクラス
 */
public class GoodsMasterMakerRealTimeId extends DomainEntityId {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = 1L;

  /**
   * コンストラクタ。
   */
  public GoodsMasterMakerRealTimeId() {

  }

  /**
   * コンストラクタ。
   * 
   * @param lineNo 申請番号
   * @param loginId ログインID
   */
  public GoodsMasterMakerRealTimeId(int lineNo, String loginId) {
    setLineNo(lineNo);
    setLoginId(loginId);
  }

  /**
   * 申請番号を返します。
   * 
   * @return 申請番号
   */
  public int getLineNo() {
    return container().getPropertyValue(GoodsMasterMakerRealTime.LINE_NO, Integer.class);
  }

  /**
   * 申請番号を設定します。
   * 
   * @param lineNo セットする申請番号
   */
  public void setLineNo(int lineNo) {
    container().setPropertyValue(GoodsMasterMakerRealTime.LINE_NO, lineNo);
  }

  /**
   * ログインIDを返します。
   * 
   * @return ログインID
   */
  public String getLoginId() {
    return container().getPropertyValue(GoodsMasterMakerRealTime.LOGIN_ID, String.class);
  }

  /**
   * ログインIDを設定します。
   * 
   * @param loginId セットするログインID
   */
  public void setLoginId(String loginId) {
    container().setPropertyValue(GoodsMasterMakerRealTime.LOGIN_ID, loginId);
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
    list.add(new PropertyDefinition(GoodsMasterMakerRealTime.LINE_NO, Integer.class, false));
    list.add(new PropertyDefinition(GoodsMasterMakerRealTime.LOGIN_ID, String.class, false));
    definitions = list.toArray(new PropertyDefinition[0]);
  }

}
