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
package jp.co.broadleaf.common.web.interceptor;

import jp.co.broadleaf.common.cache.CacheKeySpecs;
import jp.co.broadleaf.common.cache.CacheKeyUtils;
import jp.co.broadleaf.framework.web.Submission;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <pre>
 * BL用ダブルsubmitの防ぐ用クラスです。
 * </pre>
 */
public class BroadleafSubmissionInterceptor extends HandlerInterceptorAdapter {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }
    HttpSession session = request.getSession(false);
    if (session != null) {
      String sessionId = session.getId();
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      if (!isAllowMultiply(handlerMethod)) {
        String method = handlerMethod.getMethod().toGenericString();
        if (isMethodInvoking(sessionId, method)) {
          handlerMethodInvoked(request, response, (HandlerMethod) handler, method);
          return false;
        } else {
          markForMethodInvoke(sessionId, method);
        }
      }
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                              Exception ex) throws Exception {
    if (!(handler instanceof HandlerMethod)) {
      return;
    }
    HttpSession session = request.getSession(false);
    if (session != null) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      if (!isAllowMultiply(handlerMethod)) {
        String sessionId = session.getId();
        String method = handlerMethod.getMethod().toGenericString();
        unmarkForMethodInvoke(sessionId, method);
      }
    }
  }

  /**
   * ダブルsubmitできるかどうかを判断する
   * 
   * @param handlerMethod HandlerMethod
   * @return True:できる False:できない
   */
  protected boolean isAllowMultiply(HandlerMethod handlerMethod) {
    Submission submission = handlerMethod.getMethodAnnotation(Submission.class);
    if (submission != null && !submission.multiply()) {
      return false;
    }
    return true;
  }

  /**
   * 既に同じメソッドが実行中場合の処理
   * 
   * @param request HttpServletRequest
   * @param response HttpServletResponse
   * @param handler HandlerMethod
   * @param method メソッド
   * @throws IOException IO例外が発生する場合
   */
  protected void handlerMethodInvoked(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler,
                                      String method) throws IOException {
    response.sendError(HttpStatus.TOO_MANY_REQUESTS.value());
  }

  /**
   * メソッドが実行中かどうかを判断する
   * 
   * @param sessionId セッションID
   * @param method メソッド
   * @return true:実行中
   */
  protected boolean isMethodInvoking(String sessionId, String method) {
    String redisKey = getRedisKey(sessionId);
    return redisTemplate.boundSetOps(redisKey).isMember(method);
  }

  /**
   * メソッド実行中をマックする
   * 
   * @param sessionId セッションID
   * @param method メソッド
   */
  protected void markForMethodInvoke(String sessionId, String method) {
    String redisKey = getRedisKey(sessionId);
    BoundSetOperations<String, String> boundSetOps = redisTemplate.boundSetOps(redisKey);
    boundSetOps.add(method);
    boundSetOps.expire(1, TimeUnit.HOURS);
  }

  /**
   * メソッド実行中のマックを削除する
   * 
   * @param sessionId セッションID
   * @param method メソッド
   */
  protected void unmarkForMethodInvoke(String sessionId, String method) {
    String redisKey = getRedisKey(sessionId);
    redisTemplate.boundSetOps(redisKey).remove(method);
  }

  /**
   * Redis格納用キーを取得する
   * 
   * @param sessionId セッションID
   * @return Redis格納用キー
   */
  protected String getRedisKey(String sessionId) {
    return CacheKeyUtils.getCacheKey(CacheKeySpecs.SUBMISSION_SCHEMA, sessionId);
  }

  /**
   * Redisテンプレートを設定する。
   *
   * @param redisTemplate Redisテンプレート
   */
  @Resource
  public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * Redisテンプレート
   */
  private RedisTemplate<String, String> redisTemplate;
}
