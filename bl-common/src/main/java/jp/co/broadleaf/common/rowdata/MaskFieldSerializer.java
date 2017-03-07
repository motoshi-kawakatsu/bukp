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
package jp.co.broadleaf.common.rowdata;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * <pre>
 * マスクファイルの設定
 * </pre>
 */
public class MaskFieldSerializer extends JsonSerializer<Object> {

  /**
   * <pre>
   * シリアライズするモードを設定する
   * </pre>
   * 
   * @param value シリアライズする値
   * @param gen json結果の出力に使用するコンテンツジェネレータ
   * @param serializers シリアライザ
   * @throws IOException 入出力例外
   * @throws JsonProcessingException jsonの例外処理
   */
  @Override
  public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException,
                                                                                         JsonProcessingException {
    gen.writeString("***");
  }
}
