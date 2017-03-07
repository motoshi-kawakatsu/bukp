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
package jp.co.broadleaf.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * Broadleafプロジェクトフォーマット編集用のUtilsクラスです。
 * </pre>
 */
public abstract class BroadleafFormatUtils {

  /**
   * (西暦日付表示)日付フォーマット：yyyyMMdd。
   */
  public static final String DATE_F001 = "yyyyMMdd";

  /**
   * (西暦日付表示)日付フォーマット：yyyy/mm/dd。
   */
  public static final String DATE_F002 = "yyyy/MM/dd";

  /**
   * (西暦日付表示)日付フォーマット：yyyy-mm-dd。
   */
  public static final String DATE_F003 = "yyyy-MM-dd";

  /**
   * (西暦日付表示)日付フォーマット：yyyy/mm/dd hh24:mm。
   */
  public static final String DATE_F004 = "yyyy/MM/dd　HH:mm";

  /**
   * (西暦日付表示)日付フォーマット：yyyy-mm-dd hh24:mm。
   */
  public static final String DATE_F005 = "yyyy-MM-dd　HH:mm";

  /**
   * (西暦日付表示)日付フォーマット：yyyyMMddHHmm。
   */
  public static final String DATE_F006 = "yyyyMMddHHmm";

  /**
   * (西暦日付表示)日付フォーマット：yyyy/mm/dd hh24:mm:ss。
   */
  public static final String DATE_F007 = "yyyy/MM/dd　HH:mm:ss";

  /**
   * (西暦日付表示)日付フォーマット：yyyy-mm-dd hh24:mm:ss。
   */
  public static final String DATE_F008 = "yyyy-MM-dd　HH:mm:ss";

  /**
   * (西暦日付表示)日付フォーマット：yyyymmddhh24mmss。
   */
  public static final String DATE_F009 = "yyyyMMddHHmmss";

  /**
   * (西暦日付表示)日付フォーマット：yyyy-MM-dd hh24:mm:ss.SSS。
   */
  public static final String DATE_F010 = "yyyy-MM-dd HH:mm:ss.SSS";

  /**
   * (西暦日付表示)日付フォーマット：yyyymmddhh24mmssSSS。
   */
  public static final String DATE_F011 = "yyyyMMddHHmmssSSS";
  
  /**
   * 指定されたフォーマットによって、日付をフォーマットする.
   * 
   * @param sDate 入力した日付
   * @param sFmt 入力したフォーマット
   * @return Timestamp 日付
   */
  public static Timestamp stringToTimestamp(String sDate, String sFmt) {
    Timestamp ts = null;
    SimpleDateFormat formater = new SimpleDateFormat();
    formater.applyPattern(sFmt);
    try {
      ts = Timestamp.valueOf(sDate);
    } catch (Exception e) {
      return ts;
    }
    return ts;
  }

  /**
   * 指定されたフォーマットによって、日付をフォーマットする.
   * 
   * @param sDate 入力した日付
   * @param sFmt 入力したフォーマット
   * @return String 日付
   */
  public static String timestampToString(Timestamp sDate, String sFmt) {
    SimpleDateFormat sdfFrom = new SimpleDateFormat(sFmt);
    return sdfFrom.format(sDate).toString();
  }

  /**
   * 指定されたフォーマットによって、日付をフォーマットする.
   * 
   * @param sDate 入力した日付
   * @param sFmt 入力したフォーマット
   * @return Date 日付
   */
  public static Date stringToDate(String sDate, String sFmt) {
    Date dt = null;
    try {
      dt = new SimpleDateFormat(sFmt).parse(sDate);
    } catch (ParseException e) {
      return dt;
    }
    return dt;
  }

  /**
   * 指定されたフォーマットによって、日付をフォーマットする.
   * 
   * @param sDate 入力した日付
   * @param sFmt 入力したフォーマット
   * @return String 日付
   */
  public static String dateToString(Date sDate, String sFmt) {
    SimpleDateFormat sdfFrom = new SimpleDateFormat(sFmt);
    return sdfFrom.format(sDate).toString();
  }

}
