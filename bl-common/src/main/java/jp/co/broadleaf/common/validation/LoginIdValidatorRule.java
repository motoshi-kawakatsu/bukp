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

import jp.co.broadleaf.framework.validation.FieldValidatorRule;

/**
 * <pre>
 * ログインIDのフォーマットチェック
 * 半角数字、半角英字、または"_"(アンダースコア）で構成されていること。
 * </pre>
 */
public class LoginIdValidatorRule implements FieldValidatorRule<String> {

  /**
   * ルールでフィールドを検証する
   * 
   * @param fieldName フィールドの名
   * @param fieldValue フィールドの値
   * @return マッチング結果
   */
  @Override
  public boolean match(String fieldName, String fieldValue) {
    return LoginIdValidator.isValid(fieldValue);
  }

}
