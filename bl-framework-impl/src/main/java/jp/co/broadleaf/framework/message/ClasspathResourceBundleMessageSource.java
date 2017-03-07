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
package jp.co.broadleaf.framework.message;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * クラスパスをサポート用メッセージソース実現クラス
 */
public class ClasspathResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

  /**
   * プロパティファイルの接尾辞
   */
  private static final String PROPERTIES_SUFFIX = ".properties";

  /**
   * SpringのPathMatchingResourcePatternResolver
   */
  private PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

  /**
   * プロパティをリフレッシュする
   * 
   * @param filename ファイル名
   * @param propHolder プロパティホルダー
   * @return プロパティホルダー
   */
  @Override
  protected PropertiesHolder refreshProperties(String filename, PropertiesHolder propHolder) {
    if (filename.startsWith(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX)
        || filename.startsWith(PathMatchingResourcePatternResolver.CLASSPATH_URL_PREFIX)) {
      return refreshClasspathProperties(filename, propHolder);
    } else {
      return super.refreshProperties(filename, propHolder);
    }
  }

  /**
   * クラスパス中のプロパティをリフレッシュする
   * 
   * @param filename ファイル名
   * @param propHolder プロパティホルダー
   * @return プロパティホルダー
   */
  protected PropertiesHolder refreshClasspathProperties(String filename, PropertiesHolder propHolder) {
    Properties properties = new Properties();
    long lastModified = -1;
    try {
      Resource[] resources = resourcePatternResolver.getResources(filename + PROPERTIES_SUFFIX);
      for (Resource resource : resources) {
        String sourcepath = resource.getURI().toString().replace(PROPERTIES_SUFFIX, "");
        PropertiesHolder holder = super.refreshProperties(sourcepath, propHolder);
        properties.putAll(holder.getProperties());
        if (lastModified < resource.lastModified()) {
          lastModified = resource.lastModified();
        }
      }
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
    return new PropertiesHolder(properties, lastModified);
  }

}
