<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html">
<html>
<head>
	<title>取込完了</title>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="${baseurl}/css/common/common/common.css"/>
    <!-- 個別css -->
    <link rel="stylesheet" href="${baseurl}/css/readresult/readresult.css"/>
  </head>
  <body>
    <jsp:include page="../common/header.jsp" />
    <div class = "page-header">
      <h2>取込完了</h2>
    </div>
    <div class = "main">
      <div class="bl-diagram-container">
       <ul class="bl-diagram">
          <li class="bl-diagram-item completion">ファイルインポート</li>
          <li class="bl-diagram-item completion">インポート結果</li>
          <li class="bl-diagram-item current">取込完了</li>
          <c:if test="${importTypeMenu}">
          <li class="bl-diagram-item ">申請</li>
          <li class="bl-diagram-item ">申請完了</li>
          </c:if>
       </ul>
      </div>
      <div class = "tab-group">
        <div class= "goods-tab">
          <table class="table">
            <tr>
              <td class="tab-title" colspan="2">商品</td>
            </tr>
            <tr>
              <td class="tab"><strong>処理総件数</strong></td>
              <td class="value">${goodsInfo[0]} &nbsp; 件</td>
            </tr>
            <tr>
              <td class="tab"><strong>追加</strong></td>
              <td class="value">${goodsInfo[1]} &nbsp; 件</td>
            </tr>
            <tr>
              <td class="tab"><strong>更新</strong></td>
              <td class="value">${goodsInfo[2]} &nbsp; 件</td>
            </tr>
            <tr>
              <td class="tab"><strong>削除</strong></td>
              <td class="value">${goodsInfo[3]} &nbsp; 件</td>
            </tr>
            <tr>
              <td class="tab"><strong>未処理</strong></td>
              <td class="value">${goodsInfo[4]} &nbsp; 件</td>
            </tr>
          </table>
          <div class = "all_con"><button class="btn bl-btn-panel goods-con">確認</button></div>
        </div>
        <div class="set-tab">
          <table class="table">
            <tr>
              <td class="tab-title" colspan="2">セット</td>
            </tr>
            <tr>
              <td class="tab"><strong>処理総件数</strong></td>
              <td class="value">${setInfo[0]} &nbsp; 件</td>
            </tr>
            <tr>
              <td class="tab"><strong>追加</strong></td>
              <td class="value">${setInfo[1]} &nbsp; 件</td>
            </tr>
            <tr>
              <td class="tab"><strong>更新</strong></td>
              <td class="value">${setInfo[2]} &nbsp; 件</td>
            </tr>
            <tr>
              <td class="tab"><strong>削除</strong></td>
              <td class="value">${setInfo[3]} &nbsp; 件</td>
            </tr>
            <tr>
              <td class="tab"><strong>未処理</strong></td>
              <td class="value">${setInfo[4]} &nbsp; 件</td>
            </tr>
          </table>
          <div class = "all_con"><button class="btn bl-btn-panel set-con">確認</button></div>
        </div>
        <div class="join-tab">
          <table class="table">
            <tr>
              <td class="tab-title" colspan="2">結合</td>
            </tr>
            <tr>
              <td class="tab"><strong>処理総件数</strong></td>
              <td class="value">${joinInfo[0]} &nbsp; 件</td>
            </tr>
            <tr>
              <td class="tab"><strong>追加</strong></td>
              <td class="value">${joinInfo[1]} &nbsp; 件</td>
            </tr>
            <tr>
              <td class="tab"><strong>更新</strong></td>
              <td class="value">${joinInfo[2]} &nbsp; 件</td>
            </tr>
            <tr>
              <td class="tab"><strong>削除</strong></td>
              <td class="value">${joinInfo[3]} &nbsp; 件</td>
            </tr>
            <tr>
              <td class="tab"><strong>未処理</strong></td>
              <td class="value">${joinInfo[4]} &nbsp; 件</td>
            </tr>
          </table>
          <div class = "all_con"><button class="btn bl-btn-panel join-con">確認</button></div>
        </div>
        <div class = "msg-text">
	        	※未処理対象： <br>
					&nbsp; ①インポートされた商品情報が、御社の登録済み商品・セット品・結合品情報と比較して、何も変わりません。 <br>
					&nbsp; ②申請中の商品・セット品・結合品
		</div>
        <div class ="group-button">
          <c:if test="${importTypeMenu}">
          	<button type="button" class="btn bl-btn-wiz-next apply">申請
          	<span class="glyphicon glyphicon-menu-right " aria-hidden="true"></span></button>
          </c:if>
          <c:if test="${!importTypeMenu}">
          	<button type="button" class="btn bl-btn-wiz-prev top-page">トップページ</button>
          </c:if>
        </div>
      </div>
    </div>
    <jsp:include page="../common/footer.jsp" />
    <!-- 画面用のjs -->
    <script type="text/javascript" src="${baseurl}/lib/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/common.js" ></script>
    <script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/parts/header.js"></script>
    <script type="text/javascript" src="${baseurl}/js/readresult/readresult.js"></script>
    <script type="text/javascript">
       window.baseurl = "${baseurl}";
    </script>
  </body>
</html>