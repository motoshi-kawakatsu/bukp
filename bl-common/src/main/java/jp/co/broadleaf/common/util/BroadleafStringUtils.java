//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号     			    作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.common.util;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Broadleafプロジェクト用の全角文字半角文字変換クラスです。
 * </pre>
 */
public abstract class BroadleafStringUtils {

  /**
   * 値1と値2を比較する
   * 
   * @param src 値1
   * @param dest 値2
   * @return +:値＞値2|0:値1==値2|-:値1＜値2
   */
  public static int compareTo(String src, String dest) {

    if (BroadleafStringUtils.isEmpty(src) && BroadleafStringUtils.isEmpty(dest)) {
      return 0;
    }

    if (BroadleafStringUtils.isEmpty(src) && !BroadleafStringUtils.isEmpty(dest)) {
      return -1;
    }

    if (!BroadleafStringUtils.isEmpty(src) && BroadleafStringUtils.isEmpty(dest)) {
      return 1;
    }

    return src.compareTo(dest);
  }

  /**
   * 値1と値2を比較する
   * 
   * @param src 値1
   * @param dest 値2
   * @return +:値＞値2|0:値1==値2|-:値1＜値2
   */
  public static int compareTo(Integer src, Integer dest) {

    if (src == null) {
      if (dest == null) {
        return 0;
      } else {
        return -1;
      }
    } else {
      if (dest == null) {
        return 1;
      } else {
        return src.compareTo(dest);
      }
    }
  }

  /**
   * 値1と値2を比較する
   * 
   * @param src 値1
   * @param dest 値2
   * @return +:値＞値2|0:値1==値2|-:値1＜値2
   */
  public static int compareTo(Short src, Short dest) {

    if (src == null) {
      if (dest == null) {
        return 0;
      } else {
        return -1;
      }
    } else {
      if (dest == null) {
        return 1;
      } else {
        return src.compareTo(dest);
      }
    }
  }

  /**
   * 値1と値2を比較する
   * 
   * @param src 値1
   * @param dest 値2
   * @return +:値＞値2|0:値1==値2|-:値1＜値2
   */
  public static int compareTo(Double src, Double dest) {

    if (src == null) {
      if (dest == null) {
        return 0;
      } else {
        return -1;
      }
    } else {
      if (dest == null) {
        return 1;
      } else {
        return src.compareTo(dest);
      }
    }
  }

  /**
   * 値1と値2を比較する
   * 
   * @param src 値1
   * @param dest 値2
   * @return +:値＞値2|0:値1==値2|-:値1＜値2
   */
  public static int compareTo(Long src, Long dest) {

    if (src == null) {
      if (dest == null) {
        return 0;
      } else {
        return -1;
      }
    } else {
      if (dest == null) {
        return 1;
      } else {
        return src.compareTo(dest);
      }
    }
  }

  /**
   * 値1と値2を比較する
   * 
   * @param src 値1
   * @param dest 値2
   * @return +:値＞値2|0:値1==値2|-:値1＜値2
   */
  public static int compareTo(Timestamp src, Timestamp dest) {

    if (src == null) {
      if (dest == null) {
        return 0;
      } else {
        return -1;
      }
    } else {
      if (dest == null) {
        return 1;
      } else {
        return src.compareTo(dest);
      }
    }
  }

  /**
   * 空文字列かどうかチェック
   * 
   * @param text 対象文字列
   * @return boolean チェック結果
   */
  public static boolean isEmpty(String text) {

    return isEmpty(text, false);
  }

  /**
   * 空文字列かどうかチェック
   * 
   * @param text 対象文字列
   * @param trimFlg true:トリム|false:トリムなし
   * @return boolean チェック結果
   */
  public static boolean isEmpty(String text, boolean trimFlg) {

    if (text == null) {
      return true;
    }

    String value = text;
    if (trimFlg) {
      value = text.trim();
    }

    return value.length() == 0;
  }

  /**
   * 全角文字内容を半角文字内容に変換。
   * 
   * @param str 変換対象情報を設定します
   * @return String 変換された内容が返されます
   */
  public static final String fullSizeCharToHalfSizeChar(String str) {
    if (str == null || str.length() <= 0) {
      return str;
    }

    StringBuffer buf = new StringBuffer();
    char[] chars = str.toCharArray();
    int len = chars.length;
    for (int i = 0; i < len; i++) {
      if (DEFINED_CHARS.containsKey(String.valueOf(chars[i]))) {
        buf.append(DEFINED_CHARS.get(String.valueOf(chars[i])));
      } else {
        buf.append(chars[i]);
      }
    }
    return buf.toString();
  }

  /**
   * マッピング関係
   */
  public static final Map<String, String> DEFINED_CHARS;

  static {
    DEFINED_CHARS = Collections.unmodifiableMap(mapFullWidthAndHalfWidthChars());
  }

  /**
   * 全角文字と半角文字のマッピングを確立する
   * 
   * @return 全角文字と半角文字のマッピング
   */
  private static Map<String, String> mapFullWidthAndHalfWidthChars() {
    Map<String, String> result = new HashMap<String, String>();
    // 全角文字
    String[] fullSizeChars = {
        "ガ",
        "ギ",
        "グ",
        "ゲ",
        "ゴ",
        "ザ",
        "ジ",
        "ズ",
        "ゼ",
        "ゾ",
        "ダ",
        "ヂ",
        "ヅ",
        "デ",
        "ド",
        "パ",
        "ピ",
        "プ",
        "ペ",
        "ポ",
        "バ",
        "ビ",
        "ブ",
        "ベ",
        "ボ",
        "ヴ",
        "ア",
        "イ",
        "ウ",
        "エ",
        "オ",
        "カ",
        "キ",
        "ク",
        "ケ",
        "コ",
        "サ",
        "シ",
        "ス",
        "セ",
        "ソ",
        "タ",
        "チ",
        "ツ",
        "テ",
        "ト",
        "ナ",
        "ニ",
        "ヌ",
        "ネ",
        "ノ",
        "ハ",
        "ヒ",
        "フ",
        "ヘ",
        "ホ",
        "マ",
        "ミ",
        "ム",
        "メ",
        "モ",
        "ヤ",
        "ユ",
        "ヨ",
        "ラ",
        "リ",
        "ル",
        "レ",
        "ロ",
        "ワ",
        "ヲ",
        "ン",
        "ァ",
        "ィ",
        "ゥ",
        "ェ",
        "ォ",
        "ャ",
        "ュ",
        "ョ",
        "ッ",
        "ー",
        "０",
        "１",
        "２",
        "３",
        "４",
        "５",
        "６",
        "７",
        "８",
        "９",
        "Ａ",
        "Ｂ",
        "Ｃ",
        "Ｄ",
        "Ｅ",
        "Ｆ",
        "Ｇ",
        "Ｈ",
        "Ｉ",
        "Ｊ",
        "Ｋ",
        "Ｌ",
        "Ｍ",
        "Ｎ",
        "Ｏ",
        "Ｐ",
        "Ｑ",
        "Ｒ",
        "Ｓ",
        "Ｔ",
        "Ｕ",
        "Ｖ",
        "Ｗ",
        "Ｘ",
        "Ｙ",
        "Ｚ",
        "ａ",
        "ｂ",
        "ｃ",
        "ｄ",
        "ｅ",
        "ｆ",
        "ｇ",
        "ｈ",
        "ｉ",
        "ｊ",
        "ｋ",
        "ｌ",
        "ｍ",
        "ｎ",
        "ｏ",
        "ｐ",
        "ｑ",
        "ｒ",
        "ｓ",
        "ｔ",
        "ｕ",
        "ｖ",
        "ｗ",
        "ｘ",
        "ｙ",
        "ｚ",
        "！",
        "”",
        "＃",
        "＄",
        "￥",
        "％",
        "＆",
        "’",
        "（",
        "）",
        "＊",
        "＋",
        "，",
        "－",
        "．",
        "／",
        "：",
        "；",
        "＜",
        "＝",
        "＞",
        "？",
        "＠",
        "＾",
        "＿",
        "‘",
        "｛",
        "｜",
        "｝",
        "。",
        "「",
        "」",
        "、",
        "・",
        "　",
        "～",
        "［",
        "］",
        "｀" };
    // 半角文字
    String[] halfSizeChars = {
        "ｶﾞ",
        "ｷﾞ",
        "ｸﾞ",
        "ｹﾞ",
        "ｺﾞ",
        "ｻﾞ",
        "ｼﾞ",
        "ｽﾞ",
        "ｾﾞ",
        "ｿﾞ",
        "ﾀﾞ",
        "ﾁﾞ",
        "ﾂﾞ",
        "ﾃﾞ",
        "ﾄﾞ",
        "ﾊﾟ",
        "ﾋﾟ",
        "ﾌﾟ",
        "ﾍﾟ",
        "ﾎﾟ",
        "ﾊﾞ",
        "ﾋﾞ",
        "ﾌﾞ",
        "ﾍﾞ",
        "ﾎﾞ",
        "ｳﾞ",
        "ｱ",
        "ｲ",
        "ｳ",
        "ｴ",
        "ｵ",
        "ｶ",
        "ｷ",
        "ｸ",
        "ｹ",
        "ｺ",
        "ｻ",
        "ｼ",
        "ｽ",
        "ｾ",
        "ｿ",
        "ﾀ",
        "ﾁ",
        "ﾂ",
        "ﾃ",
        "ﾄ",
        "ﾅ",
        "ﾆ",
        "ﾇ",
        "ﾈ",
        "ﾉ",
        "ﾊ",
        "ﾋ",
        "ﾌ",
        "ﾍ",
        "ﾎ",
        "ﾏ",
        "ﾐ",
        "ﾑ",
        "ﾒ",
        "ﾓ",
        "ﾔ",
        "ﾕ",
        "ﾖ",
        "ﾗ",
        "ﾘ",
        "ﾙ",
        "ﾚ",
        "ﾛ",
        "ﾜ",
        "ｦ",
        "ﾝ",
        "ｧ",
        "ｨ",
        "ｩ",
        "ｪ",
        "ｫ",
        "ｬ",
        "ｭ",
        "ｮ",
        "ｯ",
        "ｰ",
        "0",
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9",
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "I",
        "J",
        "K",
        "L",
        "M",
        "N",
        "O",
        "P",
        "Q",
        "R",
        "S",
        "T",
        "U",
        "V",
        "W",
        "X",
        "Y",
        "Z",
        "a",
        "b",
        "c",
        "d",
        "e",
        "f",
        "g",
        "h",
        "i",
        "j",
        "k",
        "l",
        "m",
        "n",
        "o",
        "p",
        "q",
        "r",
        "s",
        "t",
        "u",
        "v",
        "w",
        "x",
        "y",
        "z",
        "!",
        "\"",
        "#",
        "$",
        "\\",
        "%",
        "&",
        "\'",
        "(",
        ")",
        "*",
        "+",
        ",",
        "-",
        ".",
        "/",
        ":",
        ";",
        "<",
        "=",
        ">",
        "?",
        "@",
        "^",
        "_",
        "`",
        "{",
        "|",
        "}",
        "｡",
        "｢",
        "｣",
        "､",
        "･",
        " ",
        "~",
        "[",
        "]",
        "`",
        "\"" };
    for (int i = 0; i < fullSizeChars.length; i++) {
      result.put(fullSizeChars[i], halfSizeChars[i]);
    }
    return result;
  }

}
