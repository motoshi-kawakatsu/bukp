//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2016/05/12   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.common.license.impl;

import jp.co.broadleaf.common.license.LicenseResolver;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * デフォールトライセンスチェック結果の処理用実現クラス
 * </pre>
 */
public abstract class DefaultLicenseResolver implements LicenseResolver {
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean handleResult(int itemCd, boolean result, HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
    if (result) {
      return true;
    } else {
      response.sendRedirect("/breg/accounts/mymenu");
      return false;
    }
  }
}
