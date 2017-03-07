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

import java.lang.reflect.Field;
import java.util.List;

 

/**
 * <pre>
 * ListToJson用のクラスです。
 * </pre>
 * @param <T> List対象
 */
public class ListToJson<T> {

  /**
   * listToJson
   * 
   * @param list List対象
   * @return JSONString
   */
public String listToJson(List<T> list) {

StringBuffer jsonStr = new StringBuffer();

if (null == list || 0 == list.size()) {

return null;

}

Class<?> classType = list.get(0).getClass();

String javaBeanClassName = classType.getSimpleName();

 

jsonStr.append("{\"").append(javaBeanClassName.toLowerCase()).append("s").append("\":[");

Field[] fields = classType.getDeclaredFields();

for (int i = 0; i < list.size(); i++) {

jsonStr.append("{");

for (Field field : fields) {

String fieldName = field.getName();

field.setAccessible(true);

Object fieldValue;

try {

fieldValue = field.get(list.get(i));
if (fieldValue == null) {
  fieldValue = "";
}
jsonStr.append("\"").append(fieldName.toLowerCase()).append("\":").append("\"").append(jsonString(fieldValue.toString())).append("\"").append(",");

} catch (IllegalArgumentException e) {

e.printStackTrace();

} catch (IllegalAccessException e) {

e.printStackTrace();

}

}

jsonStr.deleteCharAt(jsonStr.length() - 1);

jsonStr.append("},"); 

}

jsonStr.deleteCharAt(jsonStr.length() - 1);

jsonStr.append("]}");
return jsonStr.toString();

}

/**
 * ListToJson用のエスケープキャラクタ です。
 * @param s s
 * @return String
 */
private static String jsonString(String s){
  String str1 = s.replace("\\","\\\\\\\\");
  String str2 = str1.replace("\"","\\\"");
  return str2;
   
}



}

