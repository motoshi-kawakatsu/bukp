<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<!-- css -->

<link rel="stylesheet" href="${baseurl}/lib/reset/reset.css">
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<link rel="stylesheet" href="${baseurl}/css/topmenu/topMenu.css">
<title></title>

</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<div class="message">${companyName}　${accountName}　様、ようこそ！</div>
	<div class="main">
		<!-- <div class="panel panel-primary"> -->
		<div class="panel-body">
			<div class="input-group">
				<span class="input-group-addon">商品情報</span> 
				<label class="input-num">${shinkiHenko} 件</label> 
				<input type="button" name="shohinnIchiran" value="商品一覧へ" class="btn bl-btn-panel">
			</div>
		</div>
		<div class="panel-body">
			<div class="input-group">
				<span class="input-group-addon">セット情報</span> 
				<label class="input-num">${shinseichu} 件</label>
				<input type="button" name="setIchiran" value="セット一覧へ" class="btn bl-btn-panel">
			</div>
		</div>
		<div class="panel-body">
			<div class="input-group">
				<span class="input-group-addon">結合情報</span> 
				<label class="input-num">${ketugou} 件</label>
				<input type="button" name="ketugouIchiran" value="結合一覧へ" class="btn bl-btn-panel">
			</div>
		</div>
	</div>
	<br>
	<div class="colline"></div>
	<br>
	<div class="panel-body">
		<div class="input-group">
			<span class="input-group-addon">申請件数</span> 
			<label class="input-num">${shinseiKensuu} 件</label>
			<input type="button" name="shinseiRireki" value="申請履歴へ" class="btn bl-btn-panel">
		</div>
	</div>
	<div class='menu-msg'>${messageHyoji}</div>
	
	<input type="hidden" value="${miShinseiHandan}" id="miShinseiHandan"/>
	<input type="hidden" value="${honninInfo}" id="honninInfo"/>
	<input type="hidden" value="${messageHyoji}" id="messageHyoji"/>
	<input type="hidden" value="${accountName}" id="accountName"/>
	<input type="hidden" value="${messageOne}" id="messageOne"/>
	<input type="hidden" value="${companyName}" id="companyName"/>
	<jsp:include page="../common/footer.jsp" />

	<script type="text/javascript" src="${baseurl}/lib/jquery/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script>
	<script type="text/javascript" src="${baseurl}/js/topmenu/topMenu.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/header.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
	<script type="text/javascript">
		window.baseurl = "${baseurl}";
	</script>
</body>
</html>