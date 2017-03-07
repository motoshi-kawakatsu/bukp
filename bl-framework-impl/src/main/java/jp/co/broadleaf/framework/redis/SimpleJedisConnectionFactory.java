//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : LDNS技術共通チーム
// 作 成 日       2016/04/19   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.framework.redis;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.MapPropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * JedisConnectionFactoryのカスタマイズクラスです
 */
public class SimpleJedisConnectionFactory implements InitializingBean, DisposableBean, RedisConnectionFactory {

  /**
   * JedisConnectionFactory
   */
  private JedisConnectionFactory jedisConnectionFactory;

  /**
   * ホスト
   */
  private String host;
  /**
   * パスワード
   */
  private String password;
  /**
   * ポート
   */
  private Integer port;
  /**
   * タイムアウト
   */
  private Integer timeout;
  /**
   * Pool利用フラグ
   */
  private Boolean usePool;
  /**
   * Sentinelマスタ
   */
  private String sentinelMaster;
  /**
   * Sentinelノード
   */
  private String sentinelNodes;

  /**
   * コンストラクタ
   */
  public SimpleJedisConnectionFactory() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void afterPropertiesSet() throws Exception {
    if (StringUtils.isEmpty(sentinelNodes)) {
      jedisConnectionFactory = new JedisConnectionFactory();
      if (host != null) {
        jedisConnectionFactory.setHostName(host);
      }
      if (port != null) {
        jedisConnectionFactory.setPort(port);
      }
      if (timeout != null) {
        jedisConnectionFactory.setTimeout(timeout);
      }
      if (usePool != null) {
        jedisConnectionFactory.setUsePool(usePool);
      }
      jedisConnectionFactory.setPassword(password);
    } else {
      Map<String, Object> source = new HashMap<String, Object>();
      source.put("spring.redis.sentinel.master", sentinelMaster);
      source.put("spring.redis.sentinel.nodes", sentinelNodes);
      RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration(
          new MapPropertySource("RedisSentinelConfiguration", source));
      jedisConnectionFactory = new JedisConnectionFactory(sentinelConfig);
    }
    jedisConnectionFactory.afterPropertiesSet();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void destroy() throws Exception {
    jedisConnectionFactory.destroy();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
    return jedisConnectionFactory.translateExceptionIfPossible(ex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public RedisConnection getConnection() {
    return jedisConnectionFactory.getConnection();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean getConvertPipelineAndTxResults() {
    return jedisConnectionFactory.getConvertPipelineAndTxResults();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public RedisSentinelConnection getSentinelConnection() {
    return jedisConnectionFactory.getSentinelConnection();
  }

  /**
   * ホストを設定する
   * 
   * @param host ホスト
   */
  public void setHost(String host) {
    this.host = host;
  }

  /**
   * パスワードを設定する
   * 
   * @param password パスワード
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * ポートを設定する
   * 
   * @param port ポート
   */
  public void setPort(Integer port) {
    this.port = port;
  }

  /**
   * タイムアウトを設定する
   * 
   * @param timeout タイムアウト
   */
  public void setTimeout(Integer timeout) {
    this.timeout = timeout;
  }

  /**
   * Sentinelマスタを設定する
   * 
   * @param sentinelMaster Sentinelマスタ
   */
  public void setSentinelMaster(String sentinelMaster) {
    this.sentinelMaster = sentinelMaster;
  }

  /**
   * Sentinelノードを設定する
   * 
   * @param sentinelNodes Sentinelノード
   */
  public void setSentinelNodes(String sentinelNodes) {
    this.sentinelNodes = sentinelNodes;
  }

  /**
   * Pool利用フラグを設定する
   * 
   * @param usePool Pool利用フラグ
   */
  public void setUsePool(Boolean usePool) {
    this.usePool = usePool;
  }

}
