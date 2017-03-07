//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2016/03/01   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.framework.persistence.jdbc.c3p0;

import com.mchange.v2.c3p0.AbstractConnectionCustomizer;

import java.sql.Connection;

/**
 * <pre>
 * C3P0用のConnectionCustomizerクラスです。
 * </pre>
 */
public class ConnectionFixCustomizer extends AbstractConnectionCustomizer {

  /**
   * {@inheritDoc}
   */
  @Override
  public void onCheckOut(Connection connection, String parentDataSourceIdentityToken) throws Exception {
    // オートコミット
    if (connection.getAutoCommit()) {
      connection.setAutoCommit(false);
    }
    // トランザクションの隔離レベル
    if (connection.getTransactionIsolation() != Connection.TRANSACTION_READ_COMMITTED) {
      connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
    }
  }

}
