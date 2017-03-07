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
package jp.co.broadleaf.framework.security;

import org.apache.commons.lang3.ObjectUtils;

import java.security.Principal;

/**
 * セキュリティコンテキストホルダー
 */
public abstract class SecurityContextHolder {

  /**
   * ThreadLocalで格納用のセキュリティコンテキストホルダー
   */
  private static final ThreadLocal<Object> SECURITY_CONTEXT_HOLDER = new ThreadLocal<Object>();

  /**
   * ログイン情報を取得する
   * 
   * @param <T> Principal
   * @return ログイン情報
   */
  @SuppressWarnings("unchecked")
  public static <T extends Principal> T getPrincipal() {
    T principal = (T) SECURITY_CONTEXT_HOLDER.get();
    if (principal instanceof Cloneable) {
      return ObjectUtils.clone(principal);
    } else {
      return principal;
    }
  }

  /**
   * ログイン情報を設定する
   * 
   * @param <T> Principal
   * @param principal ログイン情報
   */
  public static <T extends Principal> void setPrincipal(T principal) {
    if (principal != null) {
      SECURITY_CONTEXT_HOLDER.set(principal);
    } else {
      resetPrincipal();
    }
  }

  /**
   * ログイン情報をクリアする
   */
  public static void resetPrincipal() {
    SECURITY_CONTEXT_HOLDER.remove();
  }
}
