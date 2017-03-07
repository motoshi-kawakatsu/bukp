//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:曹　緩緩
// 作成日:2017/02/08    修正内容:ログイン：新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.loginmaker.library;

import jp.co.broadleaf.framework.exception.SystemException;

import java.security.SecureRandom;

/**
 * <pre>
 * パスワード変換キー生成処理.
 * </pre>
 */
public abstract class PwdConvKey {

  /**
   * <pre>
   * パスワード変換キーを生成する.
   * </pre>
   * 
   * @return パスワード変換キー
   */
  public static String generatePwdConvKey() {
    String pwdConvKey = null;
    SecureRandom sr = null;
    try {
      sr = SecureRandom.getInstance(ALGORITHM, PROVIDER);
      pwdConvKey = new String(getRandomCharArray(sr));
    } catch (Exception ex) {
      throw new SystemException(ex);
    }
    return pwdConvKey;
  }

  /**
   * <pre>
   * パスワード変換キー文字配列の変更.
   * </pre>
   * 
   * @param sr ランダム対象
   * @return パスワード変換キー文字配列
   */
  private static char[] getRandomCharArray(SecureRandom sr) {
    char[] ca = new char[BYTE_SIZE];
    for (int i = 0; i < ca.length; i++) {
      ca[i] = (char) (((sr.nextInt()) % 26) + (sr.nextBoolean() ? 65 : 97));
    }
    return ca;
  }

  /** バイトサイズ */
  private static final int BYTE_SIZE = 32;

  /** アルゴリズム */
  private static final String ALGORITHM = "SHA1PRNG";

  /** プロバイダー */
  private static final String PROVIDER = "SUN";
}
