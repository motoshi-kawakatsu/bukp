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
package jp.co.broadleaf.framework.validation;

import jp.co.broadleaf.framework.exception.ValidationException;

/**
 * <pre>
 * フォームの検証
 * </pre>
 */
public interface FormValidator {

  /**
   * フィールド検証listを設定する
   * 
   * @param fieldValidator フィールド検証データ
   * @param <T> フィールド検証コア
   * @return フィールド検証list
   */
  <T extends FieldValidator> T field(T fieldValidator);

  /**
   * フィールドを検証する
   * 
   * @throws ValidationException 検証例外フィールドが存在する
   */
  void validate() throws ValidationException;

  /**
   * コアを変更する
   * 
   * @param fieldName フィールド名
   * @param fieldValue フィールド値
   * @return 変更したコア
   */
  StringFieldValidator field(String fieldName, String fieldValue);
}
