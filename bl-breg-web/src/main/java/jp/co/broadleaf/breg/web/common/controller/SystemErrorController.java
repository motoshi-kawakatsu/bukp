//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : pengs
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.common.controller;

import jp.co.broadleaf.breg.common.BregMessageCodes;
import jp.co.broadleaf.breg.common.BregPropertyKeys;
import jp.co.broadleaf.common.property.PropertyFacade;
import jp.co.broadleaf.framework.exception.SystemException;
import jp.co.broadleaf.framework.security.Anonymous;
import jp.co.broadleaf.framework.web.BaseController;
import jp.co.broadleaf.framework.web.WebResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <pre>
 * 業務共通例外画面（エラー終了）.
 * </pre>
 */
@Controller
public class SystemErrorController extends BaseController {

  /** 固定URL */
  private static final String FIXEDURL = "http://www.broadleaf.co.jp/";

  /**
   * <pre>
   * 戻り先取得.
   * </pre>
   * 
   * @return 戻り先
   */
  @Anonymous
  @ResponseBody
  @RequestMapping(value = "/system_error", method = RequestMethod.GET)
  public WebResult registProposalInfo() {
    WebResult result = new WebResult();
    try {
      String returnUrl = "";
      try {
        returnUrl = propertyFacade.getProperty(BregPropertyKeys.BREG_ACCOUNT_DEFAULT_URL);
      } catch (SystemException ex) {
        if (ex.getErrorCode().equals(BregMessageCodes.E90003)) {
          // 1-1.で取得した戻り先URL。戻り先URLが取得できなかった場合、※固定URLを設定する。
          returnUrl = FIXEDURL;
        }
      }
      if (StringUtils.isEmpty(returnUrl)) {
        // 1-1.で取得した戻り先URL。戻り先URLが取得できなかった場合、※固定URLを設定する。
        returnUrl = FIXEDURL;
      }
      result.put("returnUrl", returnUrl);
    } catch (Throwable ex) {
      handleException(ex, result);
    }
    return result;
  }

  /** プロパティFacade */
  private PropertyFacade propertyFacade;

  /**
   * <pre>
   * プロパティFacadeを設定する.
   * </pre>
   * 
   * @param propertyFacade プロパティFacade
   */
  @Resource
  public void setPropertyFacade(PropertyFacade propertyFacade) {
    this.propertyFacade = propertyFacade;
  }

}
