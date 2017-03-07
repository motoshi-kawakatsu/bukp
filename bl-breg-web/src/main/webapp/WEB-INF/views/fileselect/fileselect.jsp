<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html">
<html>
<head>
    <title>${importTypeMenuString}</title>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="${baseurl}/css/common/common/common.css"/>
    <!-- 個別css -->
    <link rel="stylesheet" href="${baseurl}/css/fileselect/fileselect.css"/>
  </head>
  <body>
  <jsp:include page="../common/header.jsp" />
    <div class="page-header">
    </div>
    <!-- メイン -->
    <div class="main container">
      <div class="sample-contents">
        <!-- ダイアグラム-->
        <div class="bl-diagram-container">
          <ul class="bl-diagram diagram-position">
            <li class="bl-diagram-item current">ファイルインポート</li>
            <li class="bl-diagram-item ">インポート結果</li>
            <li class="bl-diagram-item ">取込完了</li> 
          </ul>
        </div>
      </div>
      <!-- メイン -->
      <div class="choose-file">
          <div class="master">
            <h4>商品/セット/結合テキストフォーマットファイルを選択してください。</h4>
          </div>

          <div class="goods-con">
            <div class="goods-master import-title"><strong>商品</strong> </div>
            <div class= "btn-imp"><button class = "bli-btn goods-import-btn">ファイル選択</button></div>
            <input type="file" class= "goodsd" accept=".tsv"/>
            <div class = "select-group">
              <label><input name="goods_select_type" type="radio" value="0"  <c:if test="${importType[0]==0}">checked="true" </c:if> />全件</label>
              <label><input name="goods_select_type" type="radio" value="1"  <c:if test="${importType[0]==1}">checked="true" </c:if> />差分</label>
            </div>
            <div class = "file-show goods-file">ファイルが選択されていません。</div>
          </div>

          <div class="set-con">
            <div class="goods-master import-title"><strong>セット</strong> </div>
            <div class= "btn-imp"><button class = "bli-btn set-import-btn">ファイル選択</button></div>
            <input type="file" class= "setd" accept=".tsv"/>
            <div class = "select-group">
              <label><input name="set_select_type" type="radio" value="0" <c:if test="${importType[1]==0}">checked="true" </c:if> />全件</label>
              <label><input name="set_select_type" type="radio" value="1" <c:if test="${importType[1]==1}">checked="true" </c:if> />差分</label>
            </div>
            <div class = "file-show set-file">ファイルが選択されていません。</div>
          </div>

          <div class="join-con">
            <div class="join-master import-title"><strong>結合</strong> </div>
            <div class= "btn-imp"><button class = "bli-btn join-import-btn">ファイル選択</button></div>
            <input type="file" class= "joind" accept=".tsv"/>
            <div class = "select-group">
              <label><input name="join_select_type" type="radio" value="0" <c:if test="${importType[2]==0}">checked="true" </c:if> />全件</label>
              <label><input name="join_select_type" type="radio" value="1" <c:if test="${importType[2]==1}">checked="true" </c:if> />差分</label>
            </div>
            <div class = "file-show join-file">ファイルが選択されていません。</div>
          </div>

          <div class="button-position">
            <button class="bl-btn-wiz-next btn import"> インポート <span class="glyphicon glyphicon-menu-right " aria-hidden="false"> </span> </button>
          </div>
        </div>
      <div class = "check-msg">
      </div>
    </div> 
    <jsp:include page="../common/footer.jsp" />
    <!-- 画面用のjs -->
    <script type="text/javascript" src="${baseurl}/lib/jquery/jquery-2.2.0.min.js"></script>
    <script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/common.js" ></script>
    <script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/parts/header.js"></script>
    <script type="text/javascript" src="${baseurl}/js/fileselect/fileselect.js"></script>
    <script type="text/javascript">
       window.baseurl = "${baseurl}";
    </script>
  </body>
</html>