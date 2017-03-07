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
package jp.co.broadleaf.common.message;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * Broadleafプロジェクト用メッセージクラスです。
 * </pre>
 */
public class MessageInfo implements Serializable {

  /** serialVersionUID */
  private static final long serialVersionUID = 4930042766963990485L;

  /**
   * メッセージID
   */
  private String id;

  /**
   * メッセージタイプ
   */
  private String type;

  /**
   * メッセージタイトル
   */
  private String title;

  /**
   * メッセージ内容
   */
  private String contents;

  /**
   * 適用開始日
   */
  private Date applyStartDate;

  /**
   * 適用終了日
   */
  private Date applyEndDate;

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   */
  protected MessageInfo() {
  }

  /**
   * <pre>
   * コンストラクタ
   * </pre>
   *
   * @param id メッセージID
   * @param type メッセージタイプ
   * @param title メッセージタイトル
   * @param contents メッセージ内容
   * @param applyStartDate 適用開始日
   * @param applyEndDate 適用終了日
   */
  public MessageInfo(final String id,
                     final String type,
                     final String title,
                     final String contents,
                     final Date applyStartDate,
                     final Date applyEndDate) {
    this.id = id;
    this.type = type;
    this.title = title;
    this.contents = contents;
    this.applyStartDate = applyStartDate;
    this.applyEndDate = applyEndDate;
  }

  /**
   * <pre>
   * メッセージIDを取得する。
   * </pre>
   *
   * @return メッセージID
   */
  public String getId() {
    return id;
  }

  /**
   * <pre>
   * メッセージタイプを取得する。
   * </pre>
   *
   * @return メッセージタイプ
   */
  public String getType() {
    return type;
  }

  /**
   * <pre>
   * メッセージタイトルを取得する。
   * </pre>
   *
   * @return メッセージタイトル
   */
  public String getTitle() {
    return title;
  }

  /**
   * <pre>
   * メッセージ内容を取得する。
   * </pre>
   *
   * @return メッセージ内容
   */
  public String getContents() {
    return contents;
  }

  /**
   * <pre>
   * 適用開始日を取得する。
   * </pre>
   *
   * @return 適用開始日
   */
  public Date getApplyStartDate() {
    return applyStartDate != null ? new Date(applyStartDate.getTime()) : null;
  }

  /**
   * <pre>
   * 適用終了日を取得する。
   * </pre>
   *
   * @return 適用終了日
   */
  public Date getApplyEndDate() {
    return applyEndDate != null ? new Date(applyEndDate.getTime()) : null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((applyEndDate == null) ? 0 : applyEndDate.hashCode());
    result = prime * result + ((applyStartDate == null) ? 0 : applyStartDate.hashCode());
    result = prime * result + ((contents == null) ? 0 : contents.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    MessageInfo other = (MessageInfo) obj;
    if (applyEndDate == null) {
      if (other.applyEndDate != null) {
        return false;
      }
    } else if (!applyEndDate.equals(other.applyEndDate)) {
      return false;
    }
    if (applyStartDate == null) {
      if (other.applyStartDate != null) {
        return false;
      }
    } else if (!applyStartDate.equals(other.applyStartDate)) {
      return false;
    }
    if (contents == null) {
      if (other.contents != null) {
        return false;
      }
    } else if (!contents.equals(other.contents)) {
      return false;
    }
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    if (title == null) {
      if (other.title != null) {
        return false;
      }
    } else if (!title.equals(other.title)) {
      return false;
    }
    if (type == null) {
      if (other.type != null) {
        return false;
      }
    } else if (!type.equals(other.type)) {
      return false;
    }
    return true;
  }

}
