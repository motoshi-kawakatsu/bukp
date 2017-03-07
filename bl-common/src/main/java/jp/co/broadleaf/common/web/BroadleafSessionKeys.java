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
package jp.co.broadleaf.common.web;

/**
 * <pre>
 * Broadleafプロジェクト共通用セッションキー定義用クラスです。
 * </pre>
 */
public abstract class BroadleafSessionKeys {

  /**
   * ログイン情報格納用のセッションキー
   */
  public static final String LOGIN_INFO = "login_info";
  /**
   * セート詳細検索用のセッションキー
   */
  public static final String SET_DETAIL_SEARCH_KEY = "set_detail_search_key";
  /**
   * セート詳細グリッド用のセッションキー
   */
  public static final String SET_DETAIL_INIT_GRID = "set_detail_init_grid";
  /**
   * 商品中分類コードガイド用のセッションキー
   */
  public static final String CLASSIFY_CODE_GUIDE = "classify_code_guide";
  /**
   * 商品ガイド用のセッションキー
   */
  public static final String GOODS_GUIDE = "goods_guide";
  /**
   * 商品ガイド用のセッションキー
   */
  public static final String GOODS_CATEGORY = "goods_category";
  /**
   * 会社情報画面の初期化情報用のセッションキー
   */
  public static final String COMPANYSETTING_INITIALINFO = "companySetting_initialDto";
  /**
   * 申請履歴一覧画面検索用のセッションキー
   */
  public static final String APPLY_HISTORY_SEARCH_KEY = "apply_history_search_key";
  /**
   * 申請履歴一覧画面→申請詳細画面初期化情報用のセッションキー
   */
  public static final String APPLY_DETAIL_KEY = "apply_detail_key";
  /**
   * 申請履歴一覧画面→累積情報画面初期化情報用のセッションキー
   */
  public static final String TOTAL_INFO_KEY = "total_info_key";
  /**
   * 結合詳細検索用のセッションキー
   */
  public static final String JOIN_DETAIL_SEARCH_KEY = "join_detail_search_key";
  /**
   * 結合詳細グリッド用のセッションキー
   */
  public static final String JOIN_DETAIL_INIT_GRID = "join_detail_init_grid";
  /**
   * 結合一覧と結合詳細画面データ交換用のセッションキー
   */
  public static final String JOIN_LIST_TO_DETAIL = "joinListToDetail";
  /**
   * 結合一覧グリッドリスト用のセッションキー
   */
  public static final String JOIN_GRIDLIST = "joinGridList";
  /**
   * 結合一覧グリッドリスト用のフォームキー
   */
  public static final String JOIN_LISTFORM = "joinListForm";
  /**
   * インポート結果画面のセッションキー
   */
  public static final String GOODS_INFO = "goodsInfo";
  /**
   * インポート結果画面のセッションキー
   */
  public static final String SET_INFO = "setInfo";
  /**
   * インポート結果画面のセッションキー
   */
  public static final String JOIN_INFO = "joinInfo";
  /**
   * 取込完了画面→申請画面初期化情報用のセッションキー
   */
  public static final String IS_FROM_IMPORT = "isFromImport";
  /**
   * 申請履歴一覧画面→申請(新規品目)画面初期化情報用のセッションキー
   */
  public static final String APPLY_NEWITEM_KEY = "apply_newitem_key";
  /**
   * ファイル選択機能商品ファイルのセッションキー
   */
  public static final String GOODS_FILE_INFO = "goodsFileInfo";
  /**
   * ファイル選択機能セットファイルのセッションキー
   */
  public static final String SET_FILE_INFO = "setFileInfo";
  /**
   * ファイル選択機能結合ファイルのセッションキー
   */
  public static final String JOIN_FILE_INFO = "joinFileInfo";
  /**
   * 商品検索
   */
  public static final String GOODS_SEARCH_CONDITION = "goodsSearchCondition";
  /**
   * インポート区分
   */
  public static final String IMPORTKBN = "importKbn";
}
