//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2017/01/24   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.common.rowdata.impl;

import jp.co.broadleaf.common.rowdata.Mask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * <pre>
 * ログ出力処理
 * </pre>
 */
public class RowDataLoggerImpl {

  /**
   * ポイントカット
   */
  public void pointCut() {
  }

  /**
   * <pre>
   * メソッドが呼ばれる前、ログ出力処理.
   * </pre>
   * 
   * @param jp JoinPoint
   * @throws JsonProcessingException jsonの例外処理
   */
  public void beforeMethod(JoinPoint jp) throws JsonProcessingException {
    // パラメータリスト
    Object[] objArgs = jp.getArgs();
    // 方法の名前
    String methodName = jp.getSignature().getName();
    // デバッグ情報
    StringBuffer debugSb = new StringBuffer();
    // ログ情報
    StringBuffer infoSb = new StringBuffer();
    ObjectMapper mapper = new ObjectMapper();
    // パラメータmask化
    MethodSignature signature = (MethodSignature) jp.getSignature();
    Method method = signature.getMethod();
    Annotation[][] methodAnnotations = method.getParameterAnnotations();
    if (objArgs != null && objArgs.length > 0) {
      int count = 0;
      for (Object obj : objArgs) {
        String json = mapper.writeValueAsString(obj);
        Annotation[] ats = methodAnnotations[count];
        for (Annotation at : ats) {
          if (at.annotationType().equals(Mask.class)) {
            json = mapper.writeValueAsString(((Mask) at).value().toString());
            break;
          }
        }
        String clazz = null;
        if (null != obj) {
          clazz = obj.getClass().toString();
        }
        debugSb.append(String.format("publicメソッド'%s'の前 getArgs %s %d：%s", methodName, clazz, count, json));
        debugSb.append("\r\n");
        count++;
      }
    }
    logger.debug(debugSb.toString());

    infoSb.append("publicメソッド'" + methodName + "'の前 getKind：" + jp.getKind().toString());
    infoSb.append("\r\n");
    infoSb.append("publicメソッド'" + methodName + "'の前 getClass：" + jp.getClass().toString());
    infoSb.append("\r\n");
    infoSb.append("publicメソッド'" + methodName + "'の前 getSignature：" + jp.getSignature().toString());
    infoSb.append("\r\n");
    infoSb.append("publicメソッド'" + methodName + "'の前 getSourceLocation：" + jp.getSourceLocation().toString());
    infoSb.append("\r\n");
    infoSb.append("publicメソッド'" + methodName + "'の前 getStaticPart：" + jp.getStaticPart().toString());
    infoSb.append("\r\n");
    infoSb.append("publicメソッド'" + methodName + "'の前 getTarget：" + jp.getTarget().toString());
    infoSb.append("\r\n");
    infoSb.append("publicメソッド'" + methodName + "'の前 getThis：" + jp.getThis().toString());
    infoSb.append("\r\n");
    logger.info(infoSb.toString());
  }

  /**
   * <pre>
   * メソッドが呼ばれる後、ログ出力処理.
   * </pre>
   * 
   * @param jp JoinPoint
   */
  public void afterMethod(JoinPoint jp) {
    logger.info(String.format("%s が終了しました。", jp.getThis().toString()));
  }

  /**
   * <pre>
   * メソッドが正常終了した後、ログ出力処理.
   * </pre>
   * 
   * @param joinPoint JoinPoint
   * @param returnVal 返り値
   * @throws Throwable 例外
   */
  public void afterReturningMethod(JoinPoint joinPoint, Object returnVal) throws Throwable {
    // ターゲットメソッドの戻り値を取得するサンプル
    StringBuffer sb = new StringBuffer();
    sb.append("After '" + joinPoint.getSignature().getName() + "' Method returning...");
    sb.append("\r\n");
    Signature signature = joinPoint.getSignature();
    sb.append(signature.getDeclaringTypeName() + "." + signature.getName());
    sb.append("\r\n");
    ObjectMapper mapper = new ObjectMapper();
    String json = null;
    if (null != returnVal) {
      json = mapper.writeValueAsString(returnVal);
    }
    sb.append("returnValue=" + json);
    sb.append("\r\n");
    logger.info(sb.toString());
  }

  /** ログ */
  private final Logger logger = LoggerFactory.getLogger(RowDataLoggerImpl.class);
}
