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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.storage.Storage;
import com.google.api.services.storage.model.Bucket;
import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.api.services.storage.model.StorageObject;
import com.google.api.services.storage.model.Objects;

/**
 * 保存管理クラスです。
 */
public abstract class StorageManager {

  /**
   * バケットにオブジェクトをアップロードします。
   *
   * @param name 目標オブジェクトの名称です。
   * @param contentType 目標オブジェクトのタイプです。
   * @param file アップロードするファイル。
   * @param bucketName 目標バケットの名称です。
   * @throws IOException 異常
   * @throws GeneralSecurityException GeneralSecurityException異常
   */
  public static void uploadFile(String name, String contentType, File file,
                                String bucketName) throws IOException, GeneralSecurityException {
    InputStreamContent contentStream = new InputStreamContent(contentType, new FileInputStream(file));

    contentStream.setLength(file.length());
    StorageObject objectMetadata = new StorageObject().setName(name)
        .setAcl(Arrays.asList(new ObjectAccessControl().setEntity("allUsers").setRole("READER")));

    // insert処理
    Storage client = StorageFactory.getService();
    Storage.Objects.Insert insertRequest = client.objects().insert(bucketName, objectMetadata, contentStream);

    insertRequest.execute();
  }

  /**
   * 目標バケット内のオブジェクトのリストを取得します。
   *
   * @param bucketName 目標バケットの名称です。
   * @throws IOException 異常
   * @throws GeneralSecurityException GeneralSecurityException異常
   * @return 指定されたバケットの内容のリスト。
   */
  public static List<StorageObject> listBucket(String bucketName) throws IOException, GeneralSecurityException {
    Storage client = StorageFactory.getService();
    Storage.Objects.List listRequest = client.objects().list(bucketName);

    List<StorageObject> results = new ArrayList<StorageObject>();
    Objects objects;

    do {
      objects = listRequest.execute();
      results.addAll(objects.getItems());

      listRequest.setPageToken(objects.getNextPageToken());
    } while (null != objects.getNextPageToken());

    return results;
  }

  /**
   * バケット名称より、バケットオブジェクトを取得します。
   *
   * @param bucketName 目標バケットの名称です。
   * @throws IOException 異常
   * @throws GeneralSecurityException GeneralSecurityException異常
   * @return バケットオブジェクト。
   */
  public static Bucket getBucket(String bucketName) throws IOException, GeneralSecurityException {
    Storage client = StorageFactory.getService();

    Storage.Buckets.Get bucketRequest = client.buckets().get(bucketName);

    bucketRequest.setProjection("full");
    return bucketRequest.execute();
  }

  /**
   * バケットにオブジェクトを削除します。
   *
   * @param bucketName 目標バケットの名称です。
   * @param objName 目標オブジェクトの名称です。
   * @throws IOException 異常
   * @throws GeneralSecurityException GeneralSecurityException異常
   */
  public static void deleteObject(String bucketName, String objName) throws IOException, GeneralSecurityException {
    Storage client = StorageFactory.getService();

    Storage.Objects.Delete deleteRequest = client.objects().delete(bucketName, objName);

    deleteRequest.execute();
  }

  /**
   * バケットにオブジェクトを取得します。
   *
   * @param bucketName 目標バケットの名称です。
   * @param objName 目標オブジェクトの名称です。 * @return 指定されたオブジェクト。
   * @throws IOException 異常
   * @throws GeneralSecurityException GeneralSecurityException異常
   * @return バケットオブジェクト。
   */
  public static StorageObject getObject(String bucketName, String objName) throws IOException,
                                                                           GeneralSecurityException {
    Storage client = StorageFactory.getService();
    Storage.Objects.Get getRequest = client.objects().get(bucketName, objName);
    try {
      return getRequest.execute();
    } catch (Throwable ex) {
      return null;
    }
  }
}
