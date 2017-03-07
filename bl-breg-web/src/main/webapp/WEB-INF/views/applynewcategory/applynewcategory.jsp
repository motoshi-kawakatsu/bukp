<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
    <link rel="stylesheet" href="${baseurl}/css/applynewcategory/applynewcategory.css">
    <script type="text/javascript"> window.baseurl = "${baseurl}";</script>
    <title>新規品目</title>
  </head>

  <body>
    <jsp:include page="../common/header.jsp" /> 
    <div class = "page-header">
        <h2>申請 (新規品目)</h2>
    </div>
    <div class="container">
        <input type="hidden" class="apply-new-mode" value="${applyNewEnum}">
        <form>
        <div class = "apply-content">
            <div class="input-group">
                <span class="input-row input-group-addon">申請ID</span>
                <input type="text" class="form-control apply-no input-hight"  disabled="disabled" value="${applyId}"/>
            </div>
            <div class="input-group">
                <span class="input-row input-group-addon">申請日時</span>
                <input type="text" class="form-control apply-time input-hight" disabled="disabled" value="${applyDtTime}"/>
            </div>
            <div class="input-group input-group-textarea">
                <label>
                    <span class="input-row input-group-addon">依頼内容</span>
                    <textarea id="applyComments" class="form-control apply-comments">${applyComents}</textarea>
                    <img class="msg_info_display" > 
                </label>
            </div>  
        </div>
        <div class ="group-button apply-before" style="display:none">
            <div>
                <button class="btn bl-btn-panel apply" type="button">申請</button>
            </div>
        </div>
        
        <div class ="group-button apply-after" style="display:none">
            <div class="topmenu">
               <button class="btn bl-btn-panel topmenu" type="button">トップページへ</button>
            </div>
            <div class="apply-history">
               <button class="btn bl-btn-panel apply-history" type="button">申請履歴へ</button>
            </div>
        </div>
        <div class ="group-button apply-accept" style="display:none">
            <div>
               <button class="btn bl-btn-panel return" type="button">閉じる</button>
            </div>
        </div>
        <div class ="group-button apply-reject" style="display:none">
            <div>
               <button class="btn bl-btn-panel apply-again" type="button">再提出</button>
            </div>
            <div>
               <button class="btn bl-btn-panel return" type="button">閉じる</button>
            </div>
        </div>
        </form>
    </div>
    <jsp:include page="../common/footer.jsp" />
    <script type="text/javascript" src="${baseurl}/lib/jquery/jquery-2.2.0.min.js"></script>
    <script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/common.js" ></script>
    <script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/parts/header.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script>
    <script type="text/javascript" src="${baseurl}/js/applynewcategory/applynewcategory.js"></script>
  </body>
</html>