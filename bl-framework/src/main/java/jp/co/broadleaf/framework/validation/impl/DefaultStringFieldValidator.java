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
package jp.co.broadleaf.framework.validation.impl;

import jp.co.broadleaf.framework.validation.FieldValidatorRule;
import jp.co.broadleaf.framework.validation.GenericValidator;
import jp.co.broadleaf.framework.validation.StringFieldValidator;

import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * <pre>
 * デフォルトString型フォームの検証
 * </pre>
 */
public class DefaultStringFieldValidator extends FieldValidatorSupport<String> implements StringFieldValidator {

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param fieldName フィールド名
   * @param fieldValue フィールド値
   */
  public DefaultStringFieldValidator(String fieldName, String fieldValue) {
    super(fieldName, fieldValue);
  }

  /**
   * 必須チェックする
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return フィールド検証データ
   */
  @Override
  public StringFieldValidator required(String errorCode, String... errorArguments) {
    if (isValid() && !GenericValidator.isNotEmpty(getFieldValue())) {
      setFieldError(errorCode, errorArguments);
    }
    return this;
  }

  /**
   * 必須チェックする
   * 
   * @param precondition チェック条件
   * @param errorCode エラーのパラメータ
   * @param errorArguments エラーのパラメータ
   * @return フィールド検証データ
   */
  @Override
  public StringFieldValidator required(boolean precondition, String errorCode, String... errorArguments) {
    if (!precondition) {
      return this;
    }
    return required(errorCode, errorArguments);
  }

  /**
   * 最大桁数をチェックする.
   * 
   * @param maxlen 最大桁数
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最大桁数を超える場合、NGとする
   */
  @Override
  public StringFieldValidator maxLength(int maxlen, String errorCode, String... errorArguments) {
    if (isValid()) {
      if (!GenericValidator.maxLength(getFieldValue(), maxlen)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 最小桁数をチェックする.
   * 
   * @param minlen 最小桁数
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最小桁数を達しない場合、NGとする
   */
  @Override
  public StringFieldValidator minLength(int minlen, String errorCode, String... errorArguments) {
    if (isValid()) {
      if (!GenericValidator.minLength(getFieldValue(), minlen)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 桁数範囲をチェックする.
   * 
   * @param minlen 最大桁数
   * @param maxlen 最小桁数
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、有効桁数範囲以外場合、NGとする
   */
  @Override
  public StringFieldValidator rangeLength(int minlen, int maxlen, String errorCode, String... errorArguments) {
    if (isValid()) {
      if (!GenericValidator.isInLengthRange(getFieldValue(), minlen, maxlen)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * メールアドレスフォーマットをチェックする.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、フォーマット不正場合、NGとする
   */
  @Override
  public StringFieldValidator email(String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isEmail(getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * URLフォーマットをチェックする.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、フォーマット不正場合、NGとする
   */
  @Override
  public StringFieldValidator url(String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isUrl(getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

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
  @Override
  public StringFieldValidator number(int maxIntDigits, int maxFracDigits, boolean allowNegativeSign, String errorCode,
                                     String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isNumber(getFieldValue(), maxIntDigits, maxFracDigits, allowNegativeSign)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

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
  @Override
  public StringFieldValidator japanPhone(boolean withHyphen, String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isJapanPhone(getFieldValue(), withHyphen)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 日付をチェックする.
   * 
   * @param datePattern 日付フォーマット
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、フォーマット不正場合、NGとする
   */
  @Override
  public StringFieldValidator date(String datePattern, String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isDate(getFieldValue(), datePattern)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * データ有効性をチェックする.
   * 
   * @param validValues 有効の値配列
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、有効範囲以外の場合、NGとする
   */
  @Override
  public StringFieldValidator in(String[] validValues, String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.contains(getFieldValue(), validValues)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * データ有効性をチェックする.
   * 
   * @param validValues データ
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、有効範囲以外の場合、NGとする
   */
  @Override
  public StringFieldValidator in(int[] validValues, String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      Integer value = parseInt(getFieldValue());
      if (value == null || !GenericValidator.contains(value, validValues)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 最大値をチェックする.
   * 
   * @param maxValue 最大値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最大値を超える場合、NGとする
   */
  @Override
  public StringFieldValidator max(int maxValue, String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      Integer value = parseInt(getFieldValue());
      if (value == null || !GenericValidator.maxValue(value, maxValue)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 最小値をチェックする.
   * 
   * @param minValue 最小値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最小値を達しない場合、NGとする
   */
  @Override
  public StringFieldValidator min(int minValue, String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      Integer value = parseInt(getFieldValue());
      if (value == null || !GenericValidator.minValue(value, minValue)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 値範囲をチェックする.
   * 
   * @param minValue 最大値
   * @param maxValue 最小値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、有効値範囲以外場合、NGとする
   */
  @Override
  public StringFieldValidator range(int minValue, int maxValue, String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      Integer value = parseInt(getFieldValue());
      if (value == null || !GenericValidator.isInRange(value, minValue, maxValue)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 最大値をチェックする.
   * 
   * @param maxValue 最大値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最大値を超える場合、NGとする
   */
  @Override
  public StringFieldValidator max(long maxValue, String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      Long value = parseLong(getFieldValue());
      if (value == null || !GenericValidator.maxValue(value, maxValue)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 最小値をチェックする.
   * 
   * @param minValue 最小値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最小値を達しない場合、NGとする
   */
  @Override
  public StringFieldValidator min(long minValue, String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      Long value = parseLong(getFieldValue());
      if (value == null || !GenericValidator.minValue(value, minValue)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 値範囲をチェックする.
   * 
   * @param minValue 最大値
   * @param maxValue 最小値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、有効値範囲以外場合、NGとする
   */
  @Override
  public StringFieldValidator range(long minValue, long maxValue, String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      Long value = parseLong(getFieldValue());
      if (value == null || !GenericValidator.isInRange(value, minValue, maxValue)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 最大値をチェックする.
   * 
   * @param maxValue 最大値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最大値を超える場合、NGとする
   */
  @Override
  public StringFieldValidator max(BigDecimal maxValue, String errorCode, String... errorArguments) {
    Validate.notNull(maxValue, "maxValue must not be null");
    if (isValid() && getFieldValue() != null) {
      BigDecimal value = parseBigDecimal(getFieldValue());
      if (value == null || !GenericValidator.maxValue(value, maxValue)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 最小値をチェックする.
   * 
   * @param minValue 最小値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、最小値を達しない場合、NGとする
   */
  @Override
  public StringFieldValidator min(BigDecimal minValue, String errorCode, String... errorArguments) {
    Validate.notNull(minValue, "minValue must not be null");
    if (isValid() && getFieldValue() != null) {
      BigDecimal value = parseBigDecimal(getFieldValue());
      if (value == null || !GenericValidator.minValue(value, minValue)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 値範囲をチェックする.
   * 
   * @param minValue 最大値
   * @param maxValue 最小値
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、有効値範囲以外場合、NGとする
   */
  @Override
  public StringFieldValidator range(BigDecimal minValue, BigDecimal maxValue, String errorCode,
                                    String... errorArguments) {
    Validate.notNull(minValue, "minValue must not be null");
    Validate.notNull(maxValue, "maxValue must not be null");
    if (isValid() && getFieldValue() != null) {
      BigDecimal value = parseBigDecimal(getFieldValue());
      if (value == null || !GenericValidator.isInRange(value, minValue, maxValue)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 正則関数でチェックする.
   * 
   * @param regex 正則関数
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、正則関数をマッチングしない場合、NGとする
   */
  @Override
  public StringFieldValidator regex(String regex, String errorCode, String... errorArguments) {
    Validate.notNull(regex, "Regex must not be null");
    if (isValid()) {
      if (getFieldValue() != null && !GenericValidator.matchRegex(getFieldValue(), regex)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 正則関数でチェックする.
   * 
   * @param regexp 正則関数
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、正則関数をマッチングしない場合、NGとする
   */
  @Override
  public StringFieldValidator regexp(Pattern regexp, String errorCode, String... errorArguments) {
    Validate.notNull(regexp, "Regexp must not be null");
    if (isValid()) {
      if (getFieldValue() != null && !GenericValidator.matchRegexp(getFieldValue(), regexp)) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * 特殊文字(機種依存文字)チェックを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、特殊文字(機種依存文字)があれば、NGとする
   */
  @Override
  public StringFieldValidator noSpecialChars(String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (GenericValidator.hasSpecialChars(getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * すべて全角文字(半角カナ、半角英字、半角数字、特殊文字(機種依存文字)を除外した文字)かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、全角文字以外文字があれば、NGとする
   */
  @Override
  public StringFieldValidator fullwidthChars(String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isFullwidthChars(getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * すべてひらがなかどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、ひらがな以外文字があれば、NGとする
   */
  @Override
  public StringFieldValidator hiraganas(String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isHiraganas(getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * すべて全角カタカナかどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、全角カタカナ以外文字があれば、NGとする
   */
  @Override
  public StringFieldValidator fullwidthKatakanas(String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isFullwidthKatakanas(getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * すべて半角カタカナかどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、全角カタカナ以外文字があれば、NGとする
   */
  @Override
  public StringFieldValidator halfwidthKatakanas(String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isHalfwidthKatakanas(getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }
  
  /**
   * すべて全角英字かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、全角英字以外文字があれば、NGとする
   */
  @Override
  public StringFieldValidator fullwidthLatinLetters(String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isFullwidthLatinLetters(getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * すべて半角英字かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、半角英字以外文字があれば、NGとする
   */
  @Override
  public StringFieldValidator halfwidthLatinLetters(String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isHalfwidthLatinLetters(getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * すべて全角数字かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、全角数字以外文字があれば、NGとする
   */
  @Override
  public StringFieldValidator fullwidthDigits(String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isFullwidthDigits(getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * すべて半角数字かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、半角数字以外文字があれば、NGとする
   */
  @Override
  public StringFieldValidator halfwidthDigits(String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isHalfwidthDigits(getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * すべて全角英数字かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、全角英数字以外文字があれば、NGとします。
   */
  @Override
  public StringFieldValidator fullwidthDigitLatinLetters(String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isFullwidthDigitLatinLetters(getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * すべて半角英数字かどうかを確認する.
   * 
   * @param errorCode エラーコード
   * @param errorArguments エラーパラメータ
   * @return StringFieldValidatorを戻る、半角英数字以外文字があれば、NGとする
   */
  @Override
  public StringFieldValidator halfwidthDigitLatinLetters(String errorCode, String... errorArguments) {
    if (isValid() && getFieldValue() != null) {
      if (!GenericValidator.isHalfwidthDigitLatinLetters(getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * チェックルールでチェックする.
   * 
   * @param rule チェックルール
   * @param errorCode エラーコード
   * @param errorArguments エラーのパラメータ
   * @return StringFieldValidatorを戻る、チェックルールをマッチングしない場合、NGとする
   */
  @Override
  public StringFieldValidator match(FieldValidatorRule<String> rule, String errorCode, String... errorArguments) {
    Validate.notNull(rule, "rule must not be null");
    if (isValid()) {
      if (!rule.match(getFieldName(), getFieldValue())) {
        setFieldError(errorCode, errorArguments);
      }
    }
    return this;
  }

  /**
   * String型の値はInt型へ変換する
   * 
   * @param value 変換前の値
   * @return 変換後の値、変換失敗場合、NULLを戻る
   */
  private Integer parseInt(String value) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException ex) {
      return null;
    }
  }

  /**
   * String型の値はLong型へ変換する
   * 
   * @param value 変換前の値
   * @return 変換後の値、変換失敗場合、NULLを戻る
   */
  private Long parseLong(String value) {
    try {
      return Long.parseLong(value);
    } catch (NumberFormatException ex) {
      return null;
    }
  }

  /**
   * String型の値はBigDecimal型へ変換する
   * 
   * @param value 変換前の値
   * @return 変換後の値、変換失敗場合、NULLを戻る
   */
  private BigDecimal parseBigDecimal(String value) {
    try {
      return new BigDecimal(value);
    } catch (NumberFormatException ex) {
      return null;
    }
  }

}
