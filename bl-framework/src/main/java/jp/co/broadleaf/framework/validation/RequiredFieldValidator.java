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

/**
 * <pre>
 * 必須のフィールド検証
 * </pre>
 * 
 * @param <T> フィールド検証
 */
public interface RequiredFieldValidator<T extends FieldValidator> extends FieldValidator {

  /**
   * 必須チェックする.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return フィールド検証データ
   */
  T required(String errorCode, String... errorArguments);

  /**
   * 必須チェックする.
   * @param precondition チェック条件
   * @param errorCode エラーのパラメータ
   * @param errorArguments エラーのパラメータ
   * @return フィールド検証データ
   */
  T required(boolean precondition, String errorCode, String... errorArguments);
}
