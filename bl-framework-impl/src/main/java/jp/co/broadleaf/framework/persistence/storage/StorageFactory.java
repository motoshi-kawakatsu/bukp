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
package jp.co.broadleaf.framework.persistence.storage;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.StorageScopes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collection;

/**
 * Storageサービスファクトリークラスです。 このクラスは、認証を含むストレージサービスの作成の詳細を管理します。
 */
public abstract class StorageFactory {

  /**
   * Storageです。
   */
  private static Storage instance = null;

  /**
   * パースです。
   */
  // private static final String JSONPATH = "C:\\My Project-7cdd3ef809dc.json";
  private static final String JSONPATH = "/usr/local/breg/storage/My Project-7cdd3ef809dc.json";

  /**
   * Serviceを取得します。
   * 
   * @return Storage
   * @throws IOException 異常
   * @throws GeneralSecurityException GeneralSecurityException異常
   */
  public static synchronized Storage getService() throws IOException, GeneralSecurityException {
    if (instance == null) {
      instance = buildService();
    }
    return instance;
  }

  /**
   * buildService
   * 
   * @return Storage
   * @throws IOException 異常
   * @throws GeneralSecurityException GeneralSecurityException異常
   */
  private static Storage buildService() throws IOException, GeneralSecurityException {
    HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
    JsonFactory jsonFactory = new JacksonFactory();
    // GoogleCredential credential =
    // GoogleCredential.getApplicationDefault(transport, jsonFactory);
    InputStream credentialStream = new FileInputStream(new File(JSONPATH));
    GoogleCredential credential = GoogleCredential.fromStream(credentialStream, transport, jsonFactory);

    if (credential.createScopedRequired()) {
      Collection<String> scopes = StorageScopes.all();
      credential = credential.createScoped(scopes);
    }

    return new Storage.Builder(transport, jsonFactory, credential).setApplicationName("GCS").build();
  }
}
