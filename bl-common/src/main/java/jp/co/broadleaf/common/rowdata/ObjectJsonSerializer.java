//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2016 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2016/03/09   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.common.rowdata;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <pre>
 * Json転換クラスです。
 * </pre>
 */
public abstract class ObjectJsonSerializer {

  /**
   * <pre>
   * Json転換処理を行う。
   * </pre>
   * 
   * @param object 転換用オブジェクト
   * @return json
   */
  public static String toJsonString(Object object) {
    ObjectMapper mapper = new ObjectMapper();
    String json = null;
    if (null != object) {
      try {
        json = mapper.writeValueAsString(object);
      } catch (JsonProcessingException e) {
        json = object.getClass().toString() + "は転換失敗しました。";
      }
    }
    return json;
  }
}
