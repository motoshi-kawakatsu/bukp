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

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * <pre>
 * 汎用チェック用クラスです。
 * </pre>
 */
public abstract class GenericValidator {

  /**
   * 文字列がnullあるいは空文字列場合、trueを返します。
   * 
   * <pre>
   * isEmpty(null)      = true
   * isEmpty("")        = true
   * isEmpty(" ")       = false
   * isEmpty("bob")     = false
   * isEmpty("  bob  ") = false
   * </pre>
   * 
   * @param str 文字列
   * @return 文字列がnullあるいは空文字列場合、true
   */
  public static boolean isEmpty(String str) {
    return str == null || str.length() == 0;
  }

  /**
   * 文字列がnullでも空文字列でもなければtrueを返します。
   * 
   * <pre>
   * isNotEmpty(null)      = false
   * isNotEmpty("")        = false
   * isNotEmpty(" ")       = true
   * isNotEmpty("bob")     = true
   * isNotEmpty("  bob  ") = true
   * </pre>
   * 
   * @param str 文字列
   * @return 文字列がnullでも空文字列でもなければtrue
   */
  public static boolean isNotEmpty(String str) {
    return !isEmpty(str);
  }

  /**
   * 指定した文字数より多くないかを確認する。
   * 
   * <pre>
   * maxLength(null, 1)      = true
   * maxLength("", 1)        = true
   * maxLength(" ", 1)       = true
   * maxLength("  ", 1)      = false
   * maxLength("abc", 1)     = false
   * maxLength("  ab  ", 1)  = false
   * </pre>
   * 
   * @param str 文字列
   * @param maxlen 最大の文字数
   * @return 指定した文字数より多くない場合、true
   */
  public static boolean maxLength(String str, int maxlen) {
    return StringUtils.length(str) <= maxlen;
  }

  /**
   * 指定した文字数より少なくないかを確認する。
   * 
   * <pre>
   * minLength(null, 1)      = false
   * minLength("", 1)        = false
   * minLength(" ", 1)       = true
   * minLength("  ", 1)      = true
   * minLength("ab", 1)      = true
   * </pre>
   * 
   * @param str 文字列
   * @param minlen 最小の文字数
   * @return 指定した文字数より少なくない場合、true
   */
  public static boolean minLength(String str, int minlen) {
    return StringUtils.length(str) >= minlen;
  }

  /**
   * 指定した文字数の桁数を確認する。
   * 
   * <pre>
   * isInLengthRange(null, 1, 2)      = false
   * isInLengthRange("", 1, 2)        = false
   * isInLengthRange(" ", 1, 2)       = true
   * isInLengthRange("  ", 1, 2)      = true
   * isInLengthRange("ab", 1, 2)      = true
   * isInLengthRange("abc", 1, 2)     = false
   * isInLengthRange("  ab  ", 1, 2)  = false
   * </pre>
   * 
   * @param str 文字列
   * @param minlen 最大の文字数
   * @param maxlen 最小の文字数
   * @return 確認結果
   */
  public static boolean isInLengthRange(String str, int minlen, int maxlen) {
    return minLength(str, minlen) && maxLength(str, maxlen);
  }

  /**
   * メールアドレスのフォーマットに合致する値かを確認する。
   * 
   * <pre>
   * ＜チェック仕様＞
   * ・null もしくは 空文字でないこと
   * ・末尾がピリオド（.)でないこと。
   * ・emailをあらわす正規表現（"^\\s*?(.+)@(.+?)\\s*$"）に値が合致すること。
   * </pre>
   * 
   * @param str 文字列
   * @return メールアドレスのフォーマットに合致すれば、true
   */
  public static boolean isEmail(String str) {
    return isNotEmpty(str) && EmailValidator.getInstance().isValid(str);
  }

  /**
   * URLのフォーマットに合致する値かを確認する。
   * 
   * @param str 文字列
   * @return URLのフォーマットに合致すれば、true
   */
  public static boolean isUrl(String str) {
    return isNotEmpty(str) && UrlValidator.getInstance().isValid(str);
  }

  /**
   * 文字列が数値のみで構成されているかどうかを返します。
   * 
   * @param str 文字列
   * @param maxIntDigits 整数部分の最大桁数
   * @param maxFracDigits 小数部分の最大桁数
   * @param allowNegativeSign 負号があるか
   * @return 数値のみで構成されている場合、true
   */
  public static boolean isNumber(String str, int maxIntDigits, int maxFracDigits, boolean allowNegativeSign) {
    if (isEmpty(str)) {
      return false;
    }
    StringBuilder regex = new StringBuilder("^");
    if (allowNegativeSign) {
      regex.append("-?");
    }
    if (maxIntDigits > 0) {
      regex.append("\\d{1,");
      regex.append(maxIntDigits);
      regex.append("}");
    } else if (maxIntDigits == 0) {
      regex.append("0");
    } else {
      throw new IllegalArgumentException("maxIntDigits must be greater than or equal to zero");
    }
    if (maxFracDigits > 0) {
      regex.append("(\\.\\d{1,");
      regex.append(maxFracDigits);
      regex.append("})?");
    } else if (maxFracDigits == 0) {
      // なにもしない
    } else {
      throw new IllegalArgumentException("maxFracDigits must be greater than or equal to zero");
    }
    regex.append("$");
    return matchRegex(str, regex.toString());
  }

  /**
   * 電話番号のフォーマットに合致する値かを確認する。
   * 
   * <pre>
   * ＜チェック仕様＞
   * ・null もしくは 空文字でないこと
   * ・電話番号（0\d{1,4}-\d{1,4}-\d{4})に合致すること
   * </pre>
   * 
   * @param str 文字列
   * @param withHyphen ハイフン有り:true ハイフンなし:false
   * @return 電話番号のフォーマットに合致すれば、true
   */
  public static boolean isJapanPhone(String str, boolean withHyphen) {
    return isNotEmpty(str)
        && matchRegexp(str, withHyphen ? JAPAN_PHONE_NUMBER_WITH_HYPHEN : JAPAN_PHONE_NUMBER_WITHOUT_HYPHEN);
  }

  /**
   * 日時パターンに合致する値かを確認する。
   * 
   * @param str 文字列
   * @param datePattern 日時パターン
   * @return 日時パターンに合致すれば、true
   */
  public static boolean isDate(String str, String datePattern) {
    return isNotEmpty(str) && DateValidator.getInstance().isValid(str, datePattern);
  }

  /**
   * 指定されたオブジェクトが有効オブジェクト配列に含むかを確認する。
   * 
   * @param value オブジェクト
   * @param validValues 有効オブジェクト配列
   * @return 有効オブジェクト配列に含む場合、true
   */
  public static boolean contains(Object value, Object[] validValues) {
    return ArrayUtils.indexOf(validValues, value) != ArrayUtils.INDEX_NOT_FOUND;
  }

  /**
   * 指定されたint値が有効int配列に含むかを確認する。
   * 
   * @param value int値
   * @param validValues 有効オブジェクト配列
   * @return 有効オブジェクト配列に含む場合、true
   */
  public static boolean contains(int value, int[] validValues) {
    return ArrayUtils.indexOf(validValues, value) != ArrayUtils.INDEX_NOT_FOUND;
  }

  /**
   * 指定した最小値より小さいないかを確認する。
   * 
   * @param value 指定した値
   * @param minValue 最小値
   * @return 指定した最小値より小さいない場合、true
   */
  public static boolean minValue(int value, int minValue) {
    return value >= minValue;
  }

  /**
   * 指定した最小値より小さいないかを確認する。
   * 
   * @param value 指定した値
   * @param minValue 最小値
   * @return 指定した最小値より小さいない場合、true
   */
  public static boolean minValue(long value, long minValue) {
    return value >= minValue;
  }

  /**
   * 指定した最小値より小さいないかを確認する。
   * 
   * @param value 指定した値
   * @param minValue 最小値
   * @return 指定した最小値より小さいない場合、true
   */
  public static boolean minValue(BigDecimal value, BigDecimal minValue) {
    Validate.notNull(value, "value must not be null");
    Validate.notNull(minValue, "minValue must not be null");
    return value.compareTo(minValue) >= 0;
  }

  /**
   * 指定した最大値より大きいないかを確認する。
   * 
   * @param value 指定した値
   * @param maxValue 最大値
   * @return 指定した最大値より大きい場合、true
   */
  public static boolean maxValue(int value, int maxValue) {
    return value <= maxValue;
  }

  /**
   * 指定した最大値より大きいないかを確認する。
   * 
   * @param value 指定した値
   * @param maxValue 最大値
   * @return 指定した最大値より大きい場合、true
   */
  public static boolean maxValue(long value, long maxValue) {
    return value <= maxValue;
  }

  /**
   * 指定した最大値より大きいないかを確認する。
   * 
   * @param value 指定した値
   * @param maxValue 最大値
   * @return 指定した最大値より大きい場合、true
   */
  public static boolean maxValue(BigDecimal value, BigDecimal maxValue) {
    Validate.notNull(value, "value must not be null");
    Validate.notNull(maxValue, "maxValue must not be null");
    return value.compareTo(maxValue) <= 0;
  }

  /**
   * 指定した数字の範囲を確認する。
   * 
   * @param value 指定した数字
   * @param minValue 最小値
   * @param maxValue 最大値
   * @return 確認結果
   */
  public static boolean isInRange(int value, int minValue, int maxValue) {
    return value >= minValue && value <= maxValue;
  }

  /**
   * 指定した数字の範囲を確認する。
   * 
   * @param value 指定した数字
   * @param minValue 最小値
   * @param maxValue 最大値
   * @return 確認結果
   */
  public static boolean isInRange(long value, long minValue, long maxValue) {
    return value >= minValue && value <= maxValue;
  }

  /**
   * 指定した数字の範囲を確認する。
   * 
   * @param value 指定した数字
   * @param minValue 最小値
   * @param maxValue 最大値
   * @return 確認結果
   */
  public static boolean isInRange(BigDecimal value, BigDecimal minValue, BigDecimal maxValue) {
    Validate.notNull(value, "value must not be null");
    Validate.notNull(minValue, "minValue must not be null");
    Validate.notNull(maxValue, "maxValue must not be null");
    return value.compareTo(minValue) >= 0 && value.compareTo(maxValue) <= 0;
  }

  /**
   * 正規表現パターン文字列に合致する値かを確認する。
   * 
   * @param str 文字列
   * @param regex 正規表現パターン文字列
   * @return 正規表現パターン文字列に合致すれば、true
   * @throws PatternSyntaxException 正規表現パターン文字列が無効場合
   */
  public static boolean matchRegex(String str, String regex) throws PatternSyntaxException {
    return matchRegexp(str, Pattern.compile(regex));
  }

  /**
   * 正規表現パターンに合致する値かを確認する。
   * 
   * @param str 文字列
   * @param regexp 正規表現パターン
   * @return 正規表現パターンに合致すれば、true
   */
  public static boolean matchRegexp(String str, Pattern regexp) {
    return str != null && regexp.matcher(str).matches();
  }

  /**
   * 特殊文字(機種依存文字)チェックを確認する。
   * 
   * @param str 文字列
   * @return 機種依存文字を含む場合、true
   */
  public static boolean hasSpecialChars(String str) {
    if (isEmpty(str)) {
      return false;
    }
    for (int i = 0; i < str.length(); i++) {
      if (SPECIAL_CHARS.contains(str.charAt(i))) {
        return true;
      }
    }
    return false;
  }

  /**
   * 指定した文字列に含まれる文字がすべて全角文字(機種依存文字を含まない)かどうかを示します。
   * 
   * <pre>
   * 全角文字＝半角カナ、半角英字、半角数字、特殊文字(機種依存文字)を除外した文字
   * </pre>
   * 
   * @param str 文字列
   * @return すべて全角文字場合、true
   */
  public static boolean isFullwidthChars(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      // 半角数字、半角英字、半角カナ、特殊文字(機種依存文字)
      if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')
          || (c >= '\uFF61' && c <= '\uFFA0')) {
        return false;
      }
      if (SPECIAL_CHARS.contains((char) c)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 指定した文字列に含まれる文字がすべてひらがなかどうかを示します。
   * 
   * @param str 文字列
   * @return すべてひらがな場合、true
   */
  public static boolean isHiraganas(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      // ぁ- ん、http://www.unicode.org/charts/PDF/U3040.pdf
      if (c >= '\u3041' && c <= '\u3093') {
        // 何もしない
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 指定した文字列に含まれる文字がすべて全角カタカナかどうかを示します。
   * 
   * @param str 文字列
   * @return 全角カナのみであればtrue
   */
  public static boolean isFullwidthKatakanas(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      // ァ - ヶ、ダブルハイフン、中点と長音記号　http://www.unicode.org/charts/PDF/U30A0.pdf
      if ((c >= '\u30A0' && c <= '\u30F6') || c == '\u30FB' || c == '\u30FC') {
        // 何もしない
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 指定した文字列に含まれる文字がすべて半角カタカナかどうかを示します。
   * 
   * @param str 文字列
   * @return 半角カナのみであればtrue
   */
  public static boolean isHalfwidthKatakanas(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      // http://www.unicode.org/charts/PDF/UFF00.pdf
      if ((c >= '\uFF61' && c <= '\uFFA0')||(c == '\u0020')) {
        // 何もしない
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 指定した文字列に含まれる文字がすべて全角英字かどうかを示します。
   * 
   * @param str 文字列
   * @return すべて全角英字場合、true
   */
  public static boolean isFullwidthLatinLetters(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      // Ａ - Ｚ , ａ - ｚ、http://www.unicode.org/charts/PDF/UFF00.pdf
      if ((c >= '\uFF21' && c <= '\uFF3A') || (c >= '\uFF41' && c <= '\uFF5A')) {
        // 何もしない
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 指定した文字列に含まれる文字がすべて全角大英字かどうかを示します。
   * 
   * @param str 文字列
   * @return すべて全角大英字場合、true
   */
  public static boolean isFullwidthLatinCapitalLetters(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      // Ａ - Ｚ、http://www.unicode.org/charts/PDF/UFF00.pdf
      if (c >= '\uFF21' && c <= '\uFF3A') {
        // 何もしない
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 指定した文字列に含まれる文字がすべて全角小英字かどうかを示します。
   * 
   * @param str 文字列
   * @return すべて全角小英字場合、true
   */
  public static boolean isFullwidthLatinSmallLetters(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      // ａ - ｚ、http://www.unicode.org/charts/PDF/UFF00.pdf
      if (c >= '\uFF41' && c <= '\uFF5A') {
        // 何もしない
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 指定した文字列に含まれる文字がすべて半角英字かどうかを示します。
   * 
   * @param str 文字列
   * @return すべて半角英字場合、true
   */
  public static boolean isHalfwidthLatinLetters(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
        // 何もしない
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 指定した文字列に含まれる文字がすべて半角大英字かどうかを示します。
   * 
   * @param str 文字列
   * @return すべて半角大英字場合、true
   */
  public static boolean isHalfwidthLatinCapitalLetters(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      if (c >= 'A' && c <= 'Z') {
        // 何もしない
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 指定した文字列に含まれる文字がすべて半角小英字かどうかを示します。
   * 
   * @param str 文字列
   * @return すべて半角小英字場合、true
   */
  public static boolean isHalfwidthLatinSmallLetters(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      if (c >= 'a' && c <= 'z') {
        // 何もしない
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 指定した文字列に含まれる文字がすべて全角数字かどうかを示します。
   * 
   * @param str 文字列
   * @return すべて全角数字場合、true
   */
  public static boolean isFullwidthDigits(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      // ０ - ９、http://www.unicode.org/charts/PDF/UFF00.pdf
      if (c >= '\uFF10' && c <= '\uFF19') {
        // 何もしない
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 指定した文字列に含まれる文字がすべて半角数字かどうかを示します。
   * 
   * @param str 文字列
   * @return すべて半角数字場合、true
   */
  public static boolean isHalfwidthDigits(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      if (c >= '0' && c <= '9') {
        // 何もしない
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 指定した文字列に含まれる文字がすべて全角英数字かどうかを示します。
   * 
   * @param str 文字列
   * @return すべて全角英数字場合、true
   */
  public static boolean isFullwidthDigitLatinLetters(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      // ０ - ９, Ａ - Ｚ, ａ - ｚ、http://www.unicode.org/charts/PDF/UFF00.pdf
      if ((c >= '\uFF10' && c <= '\uFF19') || (c >= '\uFF21' && c <= '\uFF3A') || (c >= '\uFF41' && c <= '\uFF5A')) {
        // 何もしない
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 指定した文字列に含まれる文字がすべて半角英数字かどうかを示します。
   * 
   * @param str 文字列
   * @return すべて半角英数字場合、true
   */
  public static boolean isHalfwidthDigitLatinLetters(String str) {
    if (isEmpty(str)) {
      return false;
    }
    final int len = str.length();
    int c;
    for (int i = 0; i < len; i++) {
      c = str.charAt(i);
      if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
        // 何もしない
      } else {
        return false;
      }
    }
    return true;
  }

  /**
   * 日本の電話番号パターン（ハイフンなし）
   */
  public static final Pattern JAPAN_PHONE_NUMBER_WITHOUT_HYPHEN = Pattern.compile("^0\\d{1,4}\\d{1,4}\\d{4}$");

  /**
   * 日本の電話番号パターン（ハイフン有り）
   */
  public static final Pattern JAPAN_PHONE_NUMBER_WITH_HYPHEN = Pattern.compile("^0\\d{1,4}-\\d{1,4}-\\d{4}$");

  /**
   * 機種依存文字セット
   */
  public static final Set<Character> SPECIAL_CHARS;

  static {
    SPECIAL_CHARS = Collections.unmodifiableSet(readSpecialChars());
  }

  /**
   * 機種依存文字セットを読み込む。
   * 
   * @return 機種依存文字セット
   */
  private static Set<Character> readSpecialChars() {
    Set<Character> platformDependentChars = new HashSet<Character>();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(
          new InputStreamReader(GenericValidator.class.getResourceAsStream("/special-chars.txt"), "UTF8"));
      String line = null;
      while ((line = reader.readLine()) != null) {
        for (int i = 0; i < line.length(); i++) {
          platformDependentChars.add(line.charAt(i));
        }
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    } finally {
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
        }
      }
    }
    return platformDependentChars;
  }
}
