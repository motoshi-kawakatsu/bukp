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

import jp.co.broadleaf.framework.validation.FieldValidatorRule;

/**
 * <pre>
 * 
 * </pre>
 */
public class UserSettingValidatorRule implements FieldValidatorRule<String>{

	/**
	 * パスワード一致性判断
	 * @param fieldName fieldName
	 * @param fieldValue fieldValue
	 * @return UserSettingValidator UserSettingValidator
	 */
  @Override
  public boolean match(String fieldName, String fieldValue) {
    return UserSettingValidator.isValid(Boolean.valueOf(fieldValue));
  }

}
