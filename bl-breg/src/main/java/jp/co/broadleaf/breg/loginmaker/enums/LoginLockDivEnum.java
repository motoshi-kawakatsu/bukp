//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:曹　緩緩
// 作成日:2017/02/08    修正内容:ログインロック区分のEnumファイル:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.loginmaker.enums;

/**
 * ログインロック区分
 */
public enum LoginLockDivEnum {

  /**
   * 0:ログイン可能アカウント
   */
  LoginAble(0),

  /**
   * 1:ログイン不可アカウント
   */
  LoginUnable(1);

  /**
   * ログインロック区分の数値
   */
  private int value = 0;

  /**
   * コンストラクタ
   * 
   * @param value ログインロック区分の数値
   */
  LoginLockDivEnum(int value) {
    this.value = value;
  }

  /**
   * 数値からログインロック区分を取得する
   * 
   * @param value ログインロック区分の数値
   * @return ログインロック区分
   */
  public static LoginLockDivEnum valueOf(int value) {
    switch (value) {
    case 0:
      // ログイン可能アカウント
      return LoginAble;
    case 1:
      // ログイン不可アカウント
      return LoginUnable;
    default:
      // その他場合
      return null;
    }
  }

  /**
   * ログインロック区分の数値を取得する
   * 
   * @return ログインロック区分の数値
   */
  public int getValue() {
    return this.value;
  }
}
