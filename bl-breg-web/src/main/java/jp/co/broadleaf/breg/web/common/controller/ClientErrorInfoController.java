//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : xuxx
// 作 成 日       2017/01/30   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.common.controller;

import jp.co.broadleaf.breg.web.common.form.ClientErrorInfoForm;
import jp.co.broadleaf.common.security.LoginPrincipal;
import jp.co.broadleaf.common.util.BroadleafUtils;
import jp.co.broadleaf.framework.security.Anonymous;
import jp.co.broadleaf.framework.security.SecurityContextHolder;
import jp.co.broadleaf.framework.web.BaseController;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.Format;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * クライアントエラーログ書込みコントローラクラス.
 * </pre>
 */
@Controller
public class ClientErrorInfoController extends BaseController {

  /**
   * <pre>
   * クライアントエラーログ書込み.
   * </pre>
   * 
   * @param clientErrorInfo クライアントエラーログ書込みForm
   * @param request HttpServletRequest
   */
  @Anonymous
  @RequestMapping(path = "/clientErrorInfo", method = RequestMethod.POST)
  @ResponseBody
  public void writeClientErrorInfo(@RequestBody ClientErrorInfoForm clientErrorInfo, HttpServletRequest request) {
    try {
      // ログイン情報を取得
      LoginPrincipal loginPrincipal = SecurityContextHolder.getPrincipal();
      if (null != loginPrincipal) {
        clientErrorInfo.setMakerCode(String.valueOf(loginPrincipal.getMakerCode()));
        clientErrorInfo.setLoginId(loginPrincipal.getLoginId());
      }

      Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      clientErrorInfo.setServerReceiveTime(format.format(BroadleafUtils.getSystemDtTime()));

      String userAgent = request.getHeader("user-agent");
      clientErrorInfo.setUserAgent(userAgent);

      // ObjectからStringへ転換する
      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(clientErrorInfo);

      logger.error(json);
    } catch (Exception ex) {
      logger.error("クライアントエラーログ書込みコントローラのエラーが発生しました。", ex);
    }
  }

  /** ログ */
  private final Logger logger = LoggerFactory.getLogger(ClientErrorInfoController.class);
}
