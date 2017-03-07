package jp.co.broadleaf.breg.web.checklist.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * ファイルダウンロードクラス
 * </pre>
 */
public class DownloadFile extends HttpServlet {
  /**  */
  private static final long serialVersionUID = 1L;

  /**
   * ダウンロード
   * 
   * @param request request
   * @param response response
   * 
   * @throws ServletException ServletException
   * @throws IOException IOException
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    ServletOutputStream out = response.getOutputStream();
    String filepath = request.getParameter("filedir").trim();
    String filename = request.getParameter("filename").trim();
    File file = new File(filepath + filename);
    if (!file.exists()) {
      return;
    }
    FileInputStream fileInputStream = new FileInputStream(file);
    if (filename != null && filename.length() > 0) {
      response.setContentType("application/x-msdownload");
      response.setHeader("Content-Disposition",
          "attachment; filename=" + URLEncoder.encode(filename, "utf-8"));
      if (fileInputStream != null) {
        int filelen = fileInputStream.available();
        byte[] a = new byte[filelen];
        fileInputStream.read(a);
        out.write(a);
      }
    }
    fileInputStream.close();
    out.close();
  }
}
