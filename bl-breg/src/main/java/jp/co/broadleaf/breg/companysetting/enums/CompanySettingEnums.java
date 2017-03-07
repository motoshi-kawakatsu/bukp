//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : 湯凱
// 作 成 日       2017/02/10   修正内容 : 会社情報：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.companysetting.enums;

/**
 * <pre>
 * 会社情報のEnumクラスです
 * </pre>
 */
public class CompanySettingEnums {

  /**
   * インポート方法
   */
  public enum ImportType{
    /**
     * 全件
     */
    ALL(0,"全件","all"),
    
    /**
     * 差分
     */
    DIF(1,"差分","dif");
    
    /**
     * インポート方法コード
     */
    private int code;
    
    /**
     * インポート方法名称
     */
    private String name;
    
    /**
     * フィールド名称
     */
    private String filedClassName;

    /**
     * <pre>
     * コンストラクタ
     * </pre>
     *
     * @param code コード
     * @param name 名称
     * @param filedClassName フィールド名称
     */
    ImportType(int code, String name,String filedClassName) {
      this.code = code;
      this.name = name;
      this.filedClassName = filedClassName;
    }

    /**
     * <pre>
     * 【code】を取得する。
     * </pre>
     *
     * @return 【code】
     */
    public int getCode() {
      return code;
    }

    /**
     * <pre>
     * 【name】を取得する。
     * </pre>
     *
     * @return 【name】
     */
    public String getName() {
      return name;
    }

    /**
     * <pre>
     * 【filedClassName】を取得する。
     * </pre>
     *
     * @return 【filedClassName】
     */
    public String getFiledClassName() {
      return filedClassName;
    }
    
    
    
  }
}
