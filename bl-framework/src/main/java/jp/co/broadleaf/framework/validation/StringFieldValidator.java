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

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * <pre>
 * String フィールド検証用クラスです。
 * </pre>
 */
public interface StringFieldValidator extends RequiredFieldValidator<StringFieldValidator> {

  /**
   * 最大桁数をチェックする.
   * 
   * @param maxlen 最大桁数
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最大桁数を超える場合、NGとする
   */
  StringFieldValidator maxLength(int maxlen, String errorCode, String... errorArguments);

  /**
   * 最小桁数をチェックする.
   * 
   * @param minlen 最小桁数
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最小桁数を達しない場合、NGとする
   */
  StringFieldValidator minLength(int minlen, String errorCode, String... errorArguments);

  /**
   * 桁数範囲をチェックする.
   * 
   * @param minlen 最大桁数
   * @param maxlen 最小桁数
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、有効桁数範囲以外場合、NGとする
   */
  StringFieldValidator rangeLength(int minlen, int maxlen, String errorCode, String... errorArguments);

  /**
   * メールアドレスフォーマットをチェックする.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、フォーマット不正場合、NGとする
   */
  StringFieldValidator email(String errorCode, String... errorArguments);

  /**
   * URLフォーマットをチェックする.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、フォーマット不正場合、NGとする
   */
  StringFieldValidator url(String errorCode, String... errorArguments);

  /**
   * 数値フォーマットをチェックする.
   * 
   * @param maxIntDigits 整数部分の最大桁数
   * @param maxFracDigits 小数部分の最大桁数
   * @param allowNegativeSign 負号があるか
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、数値フォーマット不正場合、NGとする
   */
  StringFieldValidator number(int maxIntDigits, int maxFracDigits, boolean allowNegativeSign, String errorCode,
                              String... errorArguments);

  /**
   * 日本の電話番号のフォーマットに合致する値かを確認する.
   * 
   * <pre>
   * ・ハイフン有り場合、電話番号（0\d{1,4}-\d{1,4}-\d{4})に合致すること
   * ・ハイフンなし場合、電話番号（0\d{1,4}\d{1,4}\d{4})に合致すること
   * </pre>
   * 
   * @param withHyphen ハイフン有り:true ハイフンなし:false
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、フォーマット不正場合、NGとする
   */
  StringFieldValidator japanPhone(boolean withHyphen, String errorCode, String... errorArguments);

  /**
   * 日付をチェックする.
   * 
   * @param datePattern 日付フォーマット
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、フォーマット不正場合、NGとする
   */
  StringFieldValidator date(String datePattern, String errorCode, String... errorArguments);

  /**
   * データ有効性をチェックする.
   * 
   * @param validValues 有効の値配列
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、有効範囲以外の場合、NGとする
   */
  StringFieldValidator in(String[] validValues, String errorCode, String... errorArguments);

  /**
   * データ有効性をチェックする.
   * 
   * @param validValues データ
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、有効範囲以外の場合、NGとする
   */
  StringFieldValidator in(int[] validValues, String errorCode, String... errorArguments);

  /**
   * 最大値をチェックする.
   * 
   * @param maxValue 最大値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最大値を超える場合、NGとする
   */
  StringFieldValidator max(int maxValue, String errorCode, String... errorArguments);

  /**
   * 最小値をチェックする.
   * 
   * @param minValue 最小値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最小値を達しない場合、NGとする
   */
  StringFieldValidator min(int minValue, String errorCode, String... errorArguments);

  /**
   * 値範囲をチェックする.
   * 
   * @param minValue 最大値
   * @param maxValue 最小値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、有効値範囲以外場合、NGとする
   */
  StringFieldValidator range(int minValue, int maxValue, String errorCode, String... errorArguments);

  /**
   * 最大値をチェックする.
   * 
   * @param maxValue 最大値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最大値を超える場合、NGとする
   */
  StringFieldValidator max(long maxValue, String errorCode, String... errorArguments);

  /**
   * 最小値をチェックする.
   * 
   * @param minValue 最小値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最小値を達しない場合、NGとする
   */
  StringFieldValidator min(long minValue, String errorCode, String... errorArguments);

  /**
   * 値範囲をチェックする.
   * 
   * @param minValue 最大値
   * @param maxValue 最小値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、有効値範囲以外場合、NGとする
   */
  StringFieldValidator range(long minValue, long maxValue, String errorCode, String... errorArguments);

  /**
   * 最大値をチェックする.
   * 
   * @param maxValue 最大値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最大値を超える場合、NGとする
   */
  StringFieldValidator max(BigDecimal maxValue, String errorCode, String... errorArguments);

  /**
   * 最小値をチェックする.
   * 
   * @param minValue 最小値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最小値を達しない場合、NGとする
   */
  StringFieldValidator min(BigDecimal minValue, String errorCode, String... errorArguments);

  /**
   * 値範囲をチェックする.
   * 
   * @param minValue 最大値
   * @param maxValue 最小値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、有効値範囲以外場合、NGとする
   */
  StringFieldValidator range(BigDecimal minValue, BigDecimal maxValue, String errorCode, String... errorArguments);

  /**
   * 正則関数でチェックする.
   * 
   * @param regex 正則関数
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、正則関数をマッチングしない場合、NGとする
   */
  StringFieldValidator regex(String regex, String errorCode, String... errorArguments);

  /**
   * 正則関数でチェックする.
   * 
   * @param regexp 正則関数
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、正則関数をマッチングしない場合、NGとする
   */
  StringFieldValidator regexp(Pattern regexp, String errorCode, String... errorArguments);

  /**
   * 特殊文字(機種依存文字)チェックを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、特殊文字(機種依存文字)があれば、NGとする
   */
  StringFieldValidator noSpecialChars(String errorCode, String... errorArguments);

  /**
   * すべて全角文字(半角カナ、半角英字、半角数字、特殊文字(機種依存文字)を除外した文字)かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、全角文字以外文字があれば、NGとする
   */
  StringFieldValidator fullwidthChars(String errorCode, String... errorArguments);

  /**
   * すべてひらがなかどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、ひらがな以外文字があれば、NGとする
   */
  StringFieldValidator hiraganas(String errorCode, String... errorArguments);

  /**
   * すべて全角カタカナかどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、全角カタカナ以外文字があれば、NGとする
   */
  StringFieldValidator fullwidthKatakanas(String errorCode, String... errorArguments);

  /**
   * すべて半角カタカナかどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、半角カタカナ以外文字があれば、NGとする
   */
  StringFieldValidator halfwidthKatakanas(String errorCode, String... errorArguments);
  
  /**
   * すべて全角英字かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、全角英字以外文字があれば、NGとする
   */
  StringFieldValidator fullwidthLatinLetters(String errorCode, String... errorArguments);

  /**
   * すべて半角英字かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、半角英字以外文字があれば、NGとする
   */
  StringFieldValidator halfwidthLatinLetters(String errorCode, String... errorArguments);

  /**
   * すべて全角数字かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、全角数字以外文字があれば、NGとする
   */
  StringFieldValidator fullwidthDigits(String errorCode, String... errorArguments);

  /**
   * すべて半角数字かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、半角数字以外文字があれば、NGとする
   */
  StringFieldValidator halfwidthDigits(String errorCode, String... errorArguments);

  /**
   * すべて全角英数字かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、全角英数字以外文字があれば、NGとします。
   */
  StringFieldValidator fullwidthDigitLatinLetters(String errorCode, String... errorArguments);

  /**
   * すべて半角英数字かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、半角英数字以外文字があれば、NGとする
   */
  StringFieldValidator halfwidthDigitLatinLetters(String errorCode, String... errorArguments);

  /**
   * チェックルールでチェックする.
   * 
   * @param rule チェックルール
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、チェックルールをマッチングしない場合、NGとする
   */
  StringFieldValidator match(FieldValidatorRule<String> rule, String errorCode, String... errorArguments);

}
