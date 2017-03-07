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
package jp.co.broadleaf.common.validation;

import jp.co.broadleaf.framework.validation.GenericValidator;

import java.util.regex.Pattern;

/**
 * <pre>
 * ログインIDの共通チェック用クラスです。
 * </pre>
 */
public abstract class LoginIdValidator {

  /**
   * ログインIDのパターン
   */
  private static final Pattern LOGIN_ID_PATTERN = Pattern.compile("^[0-9a-zA-Z_]+$");

  /**
   * ログインIDが有効かどうかを確認する.
   * 
   * @param loginId ログインID
   * @return 有効場合、true
   */
  public static boolean isValid(String loginId) {
    return GenericValidator.isNotEmpty(loginId) && GenericValidator.matchRegexp(loginId, LOGIN_ID_PATTERN);
  }

  /**
   * ログインIDが有効かどうかを確認する.
   * 
   * @param loginId ログインID
   * @throws IllegalArgumentException 無効場合、この例外をスローする
   */
  public static void validate(String loginId) {
    if (!isValid(loginId)) {
      throw new IllegalArgumentException("loginId's format is not correct.");
    }
  }
}
