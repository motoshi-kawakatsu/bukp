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
package jp.co.broadleaf.framework.util;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Beanユーティリティクラスです。
 */
public abstract class BeanUtils {

  /**
   * クラスよりオブジェクトインスタンスを作成する。
   * 
   * @param <T> 任意クラス
   * @param clazz クラス
   * @return オブジェクトインスタンス
   */
  public static <T> T instantiate(Class<T> clazz) {
    Validate.notNull(clazz, "Class must not be null");
    if (clazz.isInterface()) {
      throw new IllegalArgumentException("Specified class is an interface");
    }
    try {
      return clazz.newInstance();
    } catch (InstantiationException ex) {
      throw new RuntimeException("Is it an abstract class?", ex);
    } catch (IllegalAccessException ex) {
      throw new RuntimeException("Is the constructor accessible?", ex);
    }
  }

  /**
   * <pre>
   * オブジェクトのプロパティをコピーする。
   * ※このメソッドはJunitにだけ利用できます、Junit以外の場合が利用禁止です。
   * </pre>
   * 
   * @param source コピー元
   * @param target コピー先
   */
  public static void copyProperties(Object source, Object target) {

    Validate.notNull(source, "Source must not be null");
    Validate.notNull(target, "Target must not be null");

    PropertyDescriptor[] targetPds = null;
    Map<String, PropertyDescriptor> sourcePdMap = new HashMap<String, PropertyDescriptor>();
    try {
      targetPds = Introspector.getBeanInfo(target.getClass()).getPropertyDescriptors();
      BeanInfo sourceBeanInfo = Introspector.getBeanInfo(source.getClass());
      for (PropertyDescriptor sourcePd : sourceBeanInfo.getPropertyDescriptors()) {
        sourcePdMap.put(sourcePd.getName(), sourcePd);
      }
    } catch (IntrospectionException ex) {
      throw new RuntimeException(ex);
    }

    for (PropertyDescriptor targetPd : targetPds) {
      Method writeMethod = targetPd.getWriteMethod();
      if (writeMethod != null && sourcePdMap.containsKey(targetPd.getName())) {
        Method readMethod = sourcePdMap.get(targetPd.getName()).getReadMethod();
        if (readMethod != null
            && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
          try {
            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
              readMethod.setAccessible(true);
            }
            Object value = readMethod.invoke(source);
            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
              writeMethod.setAccessible(true);
            }
            writeMethod.invoke(target, value);
          } catch (Throwable ex) {
            throw new RuntimeException("Could not copy property '" + targetPd.getName() + "' from source to target",
                ex);
          }
        }
      }
    }
  }
}
