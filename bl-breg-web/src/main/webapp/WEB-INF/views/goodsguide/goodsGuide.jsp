<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<!-- CSS -->
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<link rel="stylesheet" href="${baseurl}/css/goodsguide/goodsguide.css">
</head>

<body>
<div class="text-center">
  <h2><b>${title}商品ガイド</b></h2>
</div>
<div class="container">
  <div>
    <div class="panel-heading head panel-heading-down">
      <h3 class="panel-title">検索条件</h3>
    </div>
    <div class="panel-body panel-row">
      <form>
      <div class="bl-flex-column first-column">
        <div class="input-group ">
          <span class="input-group-addon "><b>${goodsCategory}品番</b></span> 
          <input class="form-control" id="code" value=<c:out value="${primeCode}" />>
        </div>
      </div>
     </form>
     </div>
      <div class="group-button">
        <button class="btn bl-btn-panel bl-btn-3 search" type="button">検索</button>
        <button class="btn bl-btn-panel bl-btn-3 clear" type="button">クリア</button>
      </div>
  </div>
  <div>
    <div class="panel-heading head panel-heading-down">
      <h3 class="panel-title">商品を選択してください。</h3>
      <label class="goodsGuide" hidden="hidden">${goodsGuide}</label>
    </div>
    <div class="bl-flex-row">
      <span class="col-md-4 text-center header">${goodsCategory}品番</span>
      <span class="col-md-4 text-center header">品名（半角）</span>
      <span class="col-md-4 text-center header">品名（全角）</span>
    </div>
    <div class="list-group">
      <ul class="select-list"></ul>
    </div>
  </div>
</div>
<div class="foot-button">
	<button class="btn bl-btn-panel bl-btn-4 decide" type="button">決定</button>
	<button class="btn bl-btn-panel bl-btn-4 close-btn" type="button">閉じる</button>
</div>
<!-- JavaScript -->
<script type="text/javascript" src="${baseurl}/lib/jquery/jquery-2.2.0.min.js"></script>
<script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
<script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
<script type="text/javascript" src="${baseurl}/js/goodsguide/goodsguide.js"></script>
<script type="text/javascript" src="${baseurl}/js/goodsguide/clear.js"></script>
<script type="text/javascript"> window.baseurl = "${baseurl}"; </script>
</body>

</html>
    