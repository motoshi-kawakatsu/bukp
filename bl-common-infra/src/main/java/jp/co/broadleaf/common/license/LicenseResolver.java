//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2016/05/10   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.common.license;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * ライセンスチェック結果の処理用インタフェース
 * </pre>
 */
public interface LicenseResolver {

  /**
   * ライセンスチェック用商品コードを取得する
   * 
   * @param request HttpServletRequest
   * @return ライセンスチェック用商品コード
   */
  int getItemCode(HttpServletRequest request);

  /**
   * ライセンスチェック結果の処理
   * 
   * @param itemCode ライセンスチェック用商品コード
   * @param result ライセンスチェック結果
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @return True:次のインターセプターを実行、 False:次のインターセプターを実行しない
   * @throws IOException IO例外
   */
  boolean handleResult(int itemCode, boolean result, HttpServletRequest request,
                       HttpServletResponse response) throws IOException;
}
