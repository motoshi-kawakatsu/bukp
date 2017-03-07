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
 * UserSettingValidatorTwo　のクラス
 * </pre>
 */
public abstract class UserSettingValidatorTwo {
 
/**
 * UserAdminFlagNULL判断
 * @param isUserAdminFlagNull UserAdminFlagNULL判断
 * @return isUserAdminFlagNull
 */	
  public static boolean isValid(boolean isUserAdminFlagNull){
	  return isUserAdminFlagNull;
  }
  
  /**
   * UserAdminFlagNULL判断
   * @param isUserAdminFlagNull UserAdminFlagNULL判断
   */
  public static void validate(boolean isUserAdminFlagNull){
	  if(isUserAdminFlagNull){
		  throw new IllegalArgumentException("userAdminFlag is null");
	  }
  }
  

}
