<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
  <!-- css -->
    <link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
    <link rel="stylesheet" href="${baseurl}/css/importresult/importresult.css">
    <title>インポート結果</title>
  </head>
  <body>
    <jsp:include page="../common/header.jsp" />
    <div class = "page-header">
      <h2>インポート結果</h2>
    </div>
    <div class = "main">
    <div class="bl-diagram-container">
     <ul class="bl-diagram diagram-position hide">
        <li class="bl-diagram-item completion">ファイルインポート</li> 
        <li class="bl-diagram-item current">インポート結果</li>
        <li class="bl-diagram-item ">取込完了</li>
     </ul>
    </div>
    <div class = "tab-group">
      <div class= "tab-style goods-tab">
      <input type="hidden" class="goods-error" value="${goodsErrorCount}">
      <input type="hidden"class="set-error" value="${setErrorCount}">
      <input type="hidden" class="join-error"value="${joinErrorCount}">
        <table class="table">
          <tr>
            <td class="tab-title" colspan="3">商品</td>
          </tr>
          <tr>
            <td class="tab"><strong>総件数</strong></td>
            <td class="value tdGoodsTotal">${goodsTotal} &nbsp; 件</td>
          </tr>
          <tr>
            <td class="tab"><strong>OK</strong></td>
            <td class="value tdGoodsOk">${goodsOkCount} &nbsp; 件</td>
          </tr>
          <tr>
            <td class="tab"><strong>エラー</strong></td>
            <td class="value tdGoodsError">${goodsErrorCount} &nbsp; 件</td>
            
          </tr>
        </table>
        <div class = "all-con"><button class="btn bl-btn-panel goods-con">確認</button></div>
      </div>
      <div class="tab-style set-tab">
        <table class="table">
          <tr>
            <td class="tab-title" colspan="3">セット</td>
          </tr>
          <tr>
            <td class="tab"><strong>総件数</strong></td>
            <td class="value tdSetTotal">${setTotal} &nbsp; 件</td>
          </tr>
          <tr>
            <td class="tab"><strong>OK</strong></td>
            <td class="value tdSetOk">${setOkCount} &nbsp; 件</td>
          </tr>
          <tr>
            <td class="tab"><strong>エラー</strong></td>
            <td class="value tdSetError">${setErrorCount} &nbsp; 件</td>
          </tr>
        </table>
        <div class = "all-con"><button class="btn bl-btn-panel set-con">確認</button></div>
      </div>
      <div class="tab-style union-tab">
        <table class="table">
          <tr>
            <td class="tab-title" colspan="3">結合</td>
          </tr>
          <tr>
            <td class="tab"><strong>総件数</strong></td>
            <td class="value tdJoinTotal">${joinTotal} &nbsp; 件</td>
          </tr>
          <tr>
            <td class="tab"><strong>OK</strong></td>
            <td class="value tdJoinOk">${joinOkCount} &nbsp; 件</td>
          </tr>
          <tr>
            <td class="tab"><strong>エラー</strong></td>
            <td class="value tdErrorCount">${joinErrorCount} &nbsp; 件</td>
          </tr>
        </table>
        <div class = "all-con"><button class="btn bl-btn-panel union-con">確認</button></div>
      </div>
      <div class ="group-button">
        <button type="button" class="btn bl-btn-wiz-prev back">
        <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>戻る</button>
        <button type="button" class="btn bl-btn-wiz-next import" autofocus="autofocus">取込確定</button>
      </div>
     </div>
    </div>
    <jsp:include page="../common/footer.jsp" />
    <!-- 画面用のjs -->
    <script type="text/javascript"src="${baseurl}/lib/jquery/jquery-3.1.1.min.js" ></script>
    <script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${baseurl}/lib/moment/moment-with-locales.min.js"></script>
    <script type="text/javascript" src="${baseurl}/lib/uri/URI.min.js"></script>
    <script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
    <script type="text/javascript"src="${baseurl}/js/common/common.js" ></script>
        <script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script>
    <script type="text/javascript"src="${baseurl}/js/common/parts/header.js"></script>
    <script type="text/javascript" src="${baseurl}/js/importresult/importresult.js"></script>
    <script type="text/javascript"> window.baseurl = "${baseurl}";
   </script>
  </body>
</html>