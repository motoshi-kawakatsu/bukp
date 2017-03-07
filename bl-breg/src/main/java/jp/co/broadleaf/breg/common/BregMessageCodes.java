//****************************************************************************//
// システム                                    : 優良部品登録簡易システム
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号:            作成担当:曹　緩緩
// 作成日:2017/02/08    修正内容:新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.common;

/**
 * <pre>
 * メッセージコード定義クラス
 * </pre>
 */
public abstract class BregMessageCodes {

  /** システムエラー **/
  public static final String E90001 = "E90001";

  /** メール送信エラー **/
  public static final String E90002 = "E90002";

  /** プロパティ取得失敗エラー **/
  public static final String E90003 = "E90003";

  /** 必須項目チェック **/
  public static final String E00001 = "E00001";

  /** 未入力項目チェック **/
  public static final String E00002 = "E00002";

  /** 文字桁数チェック **/
  public static final String E00003 = "E00003";

  /** 半角カナチェック **/
  public static final String E00004 = "E00004";

  /** 半角数字チェック **/
  public static final String E00005 = "E00005";

  /** 大小関係不正 **/
  public static final String E00006 = "E00006";

  /** 日付不正 **/
  public static final String E00007 = "E00007";

  /** 検索結果データなし **/
  public static final String E00008 = "E00008";

  /** 出力対象データなし **/
  public static final String E00009 = "E00009";

  /** 選択行なし **/
  public static final String E00010 = "E00010";

  /** 編集不可 **/
  public static final String E00011 = "E00011";

  /** 行削除不可 **/
  public static final String E00012 = "E00012";

  /** 保存内容なし **/
  public static final String E00013 = "E00013";

  /** 確定内容なし **/
  public static final String E00014 = "E00014";

  /** 置換内容なし **/
  public static final String E00015 = "E00015";

  /** 行貼付不可 **/
  public static final String E00016 = "E00016";

  /** 削除理由チェック **/
  public static final String E00017 = "E00017";
  
  /** 保存チェックエラー **/
  public static final String E00018 = "E00018";

  /** 必須入力チェック **/
  public static final String E00101 = "E00101";

  /** メーカーコード入力文字チェック **/
  public static final String E00102 = "E00102";

  /** ログインID入力文字チェック **/
  public static final String E00103 = "E00103";

  /** 長度チェック **/
  public static final String E00104 = "E00104";

  /** 担当者/会社情報存在チェック **/
  public static final String E00105 = "E00105";

  /** ログイン試行回数チェック **/
  public static final String E00106 = "E00106";

  /** パスワード一致性チェック **/
  public static final String E00107 = "E00107";

  /** パスワード有効期限チェック **/
  public static final String E00108 = "E00108";

  /** 優良品番重複チェック **/
  public static final String E00301 = "E00301";

  /** 商品既に存在 **/
  public static final String E00302 = "E00302";

  /** オープン価格チェック **/
  public static final String E00303 = "E00303";

  /** 商品マスタ存在チェック **/
  public static final String E00501 = "E00501";

  /** 商品マスタ状態チェック **/
  public static final String E00502 = "E00502";

  /** 優良親品番重複チェック **/
  public static final String E00503 = "E00503";
  
  /** 優良親子品番重複チェック**/
  public static final String E00504 = "E00504";
  
  /** 商品マスタ存在チェック **/
  public static final String E00701 = "E00701";

  /** 商品マスタ状態チェック **/
  public static final String E00702 = "E00702";

  /** カーメーカー品番チェック **/
  public static final String E00703 = "E00703";

  /** 純正品番体系チェック **/
  public static final String E00704 = "E00704";

  /** 純正品番重複チェック **/
  public static final String E00705 = "E00705";

  /** 純正品番重複チェック **/
  public static final String E00706 = "E00706";
  
  /** 純正品番重複チェック **/
  public static final String E00707 = "E00707";

  /** パスワードの長度チェック **/
  public static final String E01001 = "E01001";

  /** パスワードと パスワード（確認用）の一致チェック **/
  public static final String E01002 = "E01002";

  /** チェックエラーがある **/
  public static final String E02001 = "E02001";

  /** 項目データ型式チェック **/
  public static final String E02101 = "E02101";

  /** フォーマートチェック **/
  public static final String E02102 = "E02102";

  /** メーカーコードのチェック **/
  public static final String E02103 = "E02103";

  /** 未入力項目チェック **/
  public static final String W00001 = "W00001";

  /** 文字桁数チェック **/
  public static final String W00002 = "W00002";

  /** クッキーID存在チェック **/
  public static final String W00101 = "W00101";

  /** クッキー有効期限チェック **/
  public static final String W00102 = "W00102";

  /** 価格ゼロチェック **/
  public static final String W00201 = "W00201";

  /** オープン価格チェック **/
  public static final String W00202 = "W00202";

  /** 削除履歴存在チェック **/
  public static final String W00501 = "W00501";

  /** BLコードアンマッチチェック **/
  public static final String W00502 = "W00502";

  /** トップページ **/
  public static final String W00701 = "W00701";

  /** トップページ **/
  public static final String W00702 = "W00702";

  /** 編集中の確認 **/
  public static final String Q00001 = "Q00001";

  /** 登録前の確認 **/
  public static final String Q00002 = "Q00002";

  /** 置換確認 **/
  public static final String Q00101 = "Q00101";

  /** 「全体」方式インポートする前、TSVファイル内BL承認済商品、セット品、結合品のチェック。 **/
  public static final String Q00201 = "Q00201";

  /** 「全体」方式インポートする前、TSVファイル内BL承認済商品、セット品、結合品のチェック。 **/
  public static final String Q00202 = "Q00202";

  /** 「全体」方式インポートする前、TSVファイル内BL承認済商品、セット品、結合品のチェック。 **/
  public static final String Q00203 = "Q00203";

  /** 警告のみ存在の確認 **/
  public static final String Q00301 = "Q00301";

  /** DB登録完了 **/
  public static final String I00001 = "I00001";

  /** ログインパスワード通知 **/
  public static final String I00101 = "I00101";

  /** パスワード変更通知 **/
  public static final String I00102 = "I00102";

  /** ログイン試行回数通知 **/
  public static final String I00103 = "I00103";

  /** 検索結果件数 **/
  public static final String I00201 = "I00201";
  
  /** 画面遷移の通知 **/
  public static final String I00301 = "I00301";
  
  /** 申請完了通知 **/
  public static final String I00801 = "I00801";
  
  /** 申請前０件チェック **/
  public static final String I00802 = "I00802";
  
  /** 申請完了通知 **/
  public static final String I01101 = "I01101";
  
}
