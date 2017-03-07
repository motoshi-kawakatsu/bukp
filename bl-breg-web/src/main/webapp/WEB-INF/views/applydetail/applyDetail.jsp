<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<html>
	<head>
		<title>優良部品簡易登録システム</title>
		<meta charset="UTF-8">
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<meta name="viewport" content="width=device-width,initial-scale=1" />
		<!-- lib css -->
		<link rel="stylesheet" href="${baseurl}/lib/reset/reset.css">
		<link rel="stylesheet" href="${baseurl}/css/common/webview.css">
		<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="${baseurl}/lib/wijmo5/styles/wijmo.css">
		<!-- common css -->
		<link rel="stylesheet" href="${baseurl}/css/common/font-awesome.min.css">
		<link rel="stylesheet" href="${baseurl}/css/common/common/base.css">
		<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
		<!-- primary css -->
		<link rel="stylesheet" href="${baseurl}/css/applydetail/applyDetail.css">
	</head>
	<body>
		<h2>申請詳細</h2>
		<ul class="nav nav-tabs ulf" id="myTab">
			<li><a href="#item" data-toggle="tab" class="backbg"><strong>商品</strong></a>
			</li>
			<li><a href="#set" data-toggle="tab" class="backbg"><strong>セット</strong></a>
			</li>
			<li><a href="#union" data-toggle="tab" class="backbg"><strong>結合</strong></a>
			</li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane" id="item"></div>
			<div class="tab-pane" id="set"></div>
			<div class="tab-pane" id="union"></div>
			<br>
			<div class="tab_grid">
				<div class="page_head">
					<div class="all-counts">
						全件数: 
						<span class="all-num"></span>
					</div>
					<div class="page-number"></div>
					<div class="page-info">
						<span>&nbsp; 1ページに、</span>
						<span class="page-rows">${pageSize }</span>
						<span>件</span>
					</div>
				</div>
			</div>
			<div class="error-msg"></div>
			<div class="btn-group">
				<div class="apply-again">
					<button type="button" class="btn bl-btn-panel bl-btn-5 again">再提出</button>
					<button type="button" class="btn bl-btn-panel bl-btn-5 return">閉じる</button>
				</div>
			</div>
		</div>
		<!-- javascript -->
		<script type="text/javascript">
			window.baseurl = "${baseurl}";
		</script>
		<!-- lib js -->
		<script type="text/javascript" src="${baseurl}/lib/jquery/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="${baseurl}/lib/jquery.mockjax/jquery.mockjax.min.js"></script>
		<script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="${baseurl}/lib/uri/URI.min.js"></script>
		<script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.min.js"></script>
		<script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/cultures/wijmo.culture.ja.min.js"></script>
		<script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.grid.min.js"></script>
		<script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.input.min.js"></script>
		<script type="text/javascript" src="${baseurl}/js/common/jquery.pagination.min.js"></script>
		<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
		<!-- common js -->
		<script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
		<script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
		<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
		<!-- primary js -->
		<script type="text/javascript" src="${baseurl}/js/applydetail/applydetail.js"></script>
	</body>
</html>