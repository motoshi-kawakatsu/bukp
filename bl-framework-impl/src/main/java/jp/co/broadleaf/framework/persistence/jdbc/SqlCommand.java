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
package jp.co.broadleaf.framework.persistence.jdbc;

/**
 * SQLコマンドクラスです。
 */
public class SqlCommand {

  /**
   * SQLテキスト
   */
  private String text;

  /**
   * パラメータ配列
   */
  private Object[] parameters;

  /**
   * コンストラクタ
   */
  public SqlCommand() {

  }

  /**
   * コンストラクタ
   * 
   * @param text SQLテキスト
   * @param parameters パラメータ配列
   */
  public SqlCommand(String text, Object[] parameters) {
    this.text = text;
    this.parameters = parameters;
  }

  /**
   * SQLテキストを取得する。
   * 
   * @return SQLテキスト
   */
  public String getText() {
    return text;
  }

  /**
   * SQLテキストを設定する。
   * 
   * @param text SQLテキスト
   */
  public void setText(String text) {
    this.text = text;
  }

  /**
   * パラメータ配列を取得する。
   * 
   * @return パラメータ配列
   */
  public Object[] getParameters() {
    return parameters;
  }

  /**
   * パラメータ配列を設定する。
   * 
   * @param parameters パラメータ配列
   */
  public void setParameters(Object[] parameters) {
    this.parameters = parameters;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder();
    buf.append(text);
    if (parameters != null && parameters.length > 0) {
      buf.append(" [");
      int index = 0;
      for (Object param : parameters) {
        if (index > 0) {
          buf.append(", ");
        }
        buf.append(param);
        index++;
      }
      buf.append("]");
    }
    return buf.toString();
  }
}
