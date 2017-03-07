//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                               作成担当 : LDNS
// 作 成 日       2017/02/22   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.common.validation;

/**
 * <pre>
 * 
 * </pre>
 */
public abstract class UserSettingValidator {
  
	/**
	 * パスワード一致性判断
	 * @param isPasswordSame パスワード一致性判断
	 * @return isPasswordSame
	 */
  public static boolean isValid(boolean isPasswordSame){
    return isPasswordSame;
  }
  
  /**
   * パスワード一致性判断
   * @param isPasswordSame パスワード一致性判断
   */
  public static void validate(boolean isPasswordSame){
    if(!isPasswordSame){
      throw new IllegalArgumentException("password1 and password2 is not same.");
    }
  }

}
