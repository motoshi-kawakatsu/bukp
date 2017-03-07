//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
// (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴      
// ---------------------------------------------------------------------------//
// 管理番号       作成担当 : 羅　進博
// 作 成 日       2017/02/08   修正内容 : ファイル選択：新規作成
// ---------------------------------------------------------------------------//

package jp.co.broadleaf.breg.fileselect.facade;

import java.util.List;

/**
 * <pre>
 * ファイル選択Facadeインタフェース.
 * </pre>
 */
public interface FileSelectFacade {
  
  /**
   * <pre>
   * ファイルの情報をゲットする。
   * </pre>
   *
   * @param string String[]
   * @return list List
   */
  List<String[]> getFileValue(String[] string);
  
  /**
   * <pre>
   * 「全体」方式インポートする前、TSVファイル内BL承認済商品、セット品、結合品のチェックメセッジをゲットする。
   * </pre>
   *
   * @return list List
   */
  List<String> getAskMsg();
  
  /**
   * <pre>
   * ファイルのフォーマートをチェックする。
   * </pre>
   *
   * @param fileFirst String[]
   * @param tableRow String[]
   * @return fileFirstErr List
   */
  List<String> fileFirstVai(String[] fileFirst, String[] tableRow);
  
  /**
   * <pre>
   * データベースに無効データを削除する。
   * </pre>
   *
   * @param loginUserCd Integer
   */
  void deleteInvalidData(int loginUserCd);
  
  /**
   * <pre>
   *  ファイルの情報は挿入前にチェックする。
   * </pre>
   *
   * @param list String[]
   * @param fileType String
   * @param importTypeMenu Integer
   * @param loginUserCd Integer
   * @return insertStatus List
   */
  List<String> fileConversionCheck(List<String[]> list, String fileType, int importTypeMenu, int loginUserCd);
  
  /**
   * <pre>
   *  既存BL承認済み情報をゲットする。
   * </pre>
   *
   * @param fileType String
   * @param loginUserCd Integer
   * @return consentNum int[]
   */
  int[] getConsentNum(String fileType, int loginUserCd);
  
  /**
   * <pre>
   * インポート方法をゲットする。
   * </pre>
   *
   * @param loginUserCd Integer
   * @return fileSelectService.getImportType(loginUserCd )
   */
  int[] getImportType(int loginUserCd);
  
  /**
   * <pre>
   * ファイルの情報はデータベースに挿入する。
   * </pre>
   *
   */
  void fileToDb();
}
