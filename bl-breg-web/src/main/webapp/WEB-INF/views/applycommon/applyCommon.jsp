<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <!-- css -->
    <link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
    <link rel="stylesheet" href="${baseurl}/css/applycommon/applyCommon.css">
    <title>申請</title>
  </head>
  <body>
  	<jsp:include page="../common/header.jsp" /> 
    <div class = "show-model1 page-header" style="display:none">
      <h2>申請</h2>
    </div>
    <div class = "show-model2 page-header" style="display:none">
      <h2>申請</h2>
    </div>
    <div class="main">
      <!--進捗条 -->
      <div class="show-aaa bl-diagram-container" style="display:none">
        <ul class="bl-diagram diagram-position">
          <li class="bl-diagram-item completion">ファイルインポート</li> 
          <li class="bl-diagram-item completion">インポート結果</li>     
          <li class="bl-diagram-item completion">取込完了</li>
          <li class="bl-diagram-item current">申請</li>
          <li class="bl-diagram-item ">申請完了</li>
        </ul>
      </div>
      <!--申請ID、申請日時、依頼コメント-->
      <div class="new">
      <div class ="shinsei-title">
        <div class="input-group div-center">
          <span class="input-group-addon column2">申請ID</span>
          <input type="text" class="form-control approval-id" disabled="disabled"/>
        </div>
        <div class="input-group div-center">
          <span class="input-group-addon column2">申請日時</span>
          <input type="text" class="form-control approval-time" disabled="disabled" value = "2017/1/5 12:00:00"/>
        </div>
        <div class="input-group div-center">
          <span class="input-group-addon column2 div-span">申請コメント</span>
          <textarea class="form-control com-approval apply-comments">${applyComments}</textarea>          	
        </div>
      </div>
      <div class="note">商品/セット/結合の申請データを選択してください。</div>
      <!--ステータス、BL判断日、BLコンメント-->
      <div class="showDiv shinsei-title" style="display:none">
			  <div class="input-group div-center">
				  <span class="input-group-addon column2">ステータス</span>
				  <input type="text" class="form-control approval-id" value="承認済" disabled="disabled" />
				</div>
				<div class="input-group div-center">
				  <span class="input-group-addon column2">BL判断日</span>
				  <input type="text" class="form-control approval-time" disabled="disabled" value = "2017/01/05 12:00:00"/>
			  </div>
				<div class="input-group div-center">
				  <span class="input-group-addon column2 div-span">BLコンメント</span>
				  <textarea class="form-control com-approval" disabled="disabled">△△を新規したいです。</textarea>
			  </div>
			</div>
      <!--商品/セット/結合-->
      <div class = "show-bbb tab-group" style="display:none">
        <div class = "show-bbb-d approval-title" style="display:none">商品/セット/結合の申請データを選択してください。</div>
        <div class= "tab-style goods-tab">
          <table class="table">
            <tr>
              <td class="tab-title" colspan="2">商品</td>
            </tr>
            <tr>
              <td class="tab">申請件数</td>
              <td class="value">${goodsCount} &nbsp;件</td>
            </tr>
          </table>
          <div class = "show-bbb-a all_con" style="display:none"><button class="btn bl-btn-panel select goods-select">商品選択</button></div>
        </div>
        <div class="tab-style set-tab">
          <table class="table">
            <tr>
              <td class="tab-title" colspan="2">セット</td>
            </tr>
            <tr>
              <td class="tab">申請件数</td>
              <td class="value">${setCount} &nbsp;件</td>
            </tr>
          </table>
          <div class = "show-bbb-b all_con" style="display:none"><button class="btn bl-btn-panel select set-select">セット選択</button></div>
        </div>
        <div class="tab-style union-tab">
          <table class="table">
            <tr>
              <td class="tab-title" colspan="2">結合</td>
            </tr>
            <tr>
              <td class="tab">申請件数</td>
              <td class="value">${joinCount} &nbsp;件</td>
            </tr>
          </table>
          <div class = "show-bbb-c all_con" style="display:none"><button class="btn bl-btn-panel select union-select">結合選択</button></div>
        </div>
      </div>
      </div>
      <!--button-->
      <div class ="group-button">
        <button type="button" class="btn bl-btn-panel modoru" style="display:none">戻る</button>
        <button type="button" class="btn bl-btn-panel top-page" style="display:none">トップページへ</button>
        <button type="button" class="btn bl-btn-panel shinsei-detal" style="display:none">申請詳細</button>
        <button type="button" class="btn bl-btn-panel shinsei-history" style="display:none">申請履歴へ</button>
        <button type="button" class="btn bl-btn-panel shinsei-general_new" style="display:none">申請</button>
        <button type="button" class="btn bl-btn-panel shinsei-general_import" style="display:none">申請</button>
      </div>
      <div class = "check-msg">
      </div>
    </div>
    <input type="hidden" class="import_result" value='<%=session.getAttribute("isFromImport")%>' />
	<input type="hidden" class="isLocked"
		value='<%=session.getAttribute("isLocked")%>' />
	<jsp:include page="../common/footer.jsp" />
  <!-- 画面用のjs -->
  <script type="text/javascript" src="${baseurl}/lib/jquery/jquery-2.2.0.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/jquery.mockjax/jquery.mockjax.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
  <script type="text/javascript" src="${baseurl}/js/common/common.js"></script> 
  <script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
  <script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
  <script type="text/javascript" src="${baseurl}/js/common/parts/header.js"></script>
  <script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script>
  <script type="text/javascript" src="${baseurl}/js/applycommon/applyCommon.js"></script>     
  <script type="text/javascript">            
        window.baseurl = "${baseurl}";
  </script>
  </body>

</html>