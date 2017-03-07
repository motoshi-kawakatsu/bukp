<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>累積情報</title>
<link rel="stylesheet" href="${baseurl}/lib/reset/reset.css">
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<link rel="stylesheet" href="${baseurl}/lib/wijmo5/styles/wijmo.css">
<link rel="stylesheet" href="${baseurl}/css/totalinfo/totalinfo.css">
</head>
<body>
		<h2>累積情報</h2>
		<div class="group-button grid-top grid-bottom">
		         <button class="btn bl-btn-panel bl-btn-4 blcode">BLコード</button>
		         <button class="btn bl-btn-panel bl-btn-4 carcode">カーコード</button>
		         <button class="btn bl-btn-panel bl-btn-4 goodsNo">優良品番</button>
		         <button class="btn bl-btn-panel bl-btn-4 pureNo">純正品番</button>
		</div>
		<ul class="nav nav-tabs ulf" id="myTab">
          <li >
              <a href="#item" data-toggle="tab" class="backbg"><strong>商品</strong></a>
          </li>
          <li>
              <a href="#set" data-toggle="tab" class="backbg"><strong>セット</strong></a>
          </li>
          <li>
              <a href="#union" data-toggle="tab" class="backbg"><strong>結合</strong></a>
          </li>
        </ul>
		<div class="tab-content">  
			<div class="tab-pane" id="item" ></div>
			<div class="tab-pane" id="set"></div>
			<div class="tab-pane" id="union"></div>
		</div>
		 <div class="page_head bl-flex-row">
		 	  <div>検索件/全件数:
		 	  <span  class="searchcount"></span>/
		      <span  class="totalcount"></span></div>
		      <div class="empty-inline"></div>
			  <div class="page-box"></div>
			  <div class="page-info">
					<span>1ページに、</span>
					<span class="maxcount"></span>
					<span>件</span>
			  </div>
		</div>
	<script type="text/javascript" src="${baseurl}/lib/jquery/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/jquery.mockjax/jquery.mockjax.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/cultures/wijmo.culture.ja.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.grid.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.input.min.js"></script>
	<script type="text/javascript" src="${baseurl}/js/changecommon/show_changeCommon.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/uri/URI.min.js"></script>
	
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/jquery.pagination.min.js"></script>
	
	<script type="text/javascript" src="${baseurl}/js/totalinfo/totalinfo.js"></script>
	<script type="text/javascript">
		window.baseurl = "${baseurl}";
	</script>
</body>
</html>