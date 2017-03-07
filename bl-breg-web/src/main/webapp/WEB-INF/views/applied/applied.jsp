<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ja">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- lib css -->
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<!--共通部品 css -->
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<!-- 個別css -->
<link rel="stylesheet" href="${baseurl}/css/applied/applied.css">
<title>処理結果</title>
</head>
<body>
	<div class="page-header">
		<h2>申請完了</h2>
	</div>
	<div class="col-md-12 text-center complete-msg div-textarea">
		<h4>
			申請番号：<%=session.getAttribute("applyNo")%>
			が申請されました。
		</h4>
	</div>
	<div class="container">
		<div class="col-md-12 show-table">
			<table class="table">
				<tr>
					<td class="tab-title" colspan="2">申請結果</td>
				</tr>
				<tr class="tab-item">
					<td class="tab"><strong>商品</strong></td>
				</tr>
				<tr class="tab-set">
					<td class="tab"><strong>結合</strong></td>
				</tr>
				<tr class="tab-union">
					<td class="tab"><strong>セット</strong></td>
				</tr>
			</table>
			<input type="hidden" class="count"
				value='<%=session.getAttribute("goodsCount")%>'> <input
				type="hidden" class="count"
				value='<%=session.getAttribute("setCount")%>'> <input
				type="hidden" class="count"
				value='<%=session.getAttribute("joinCount")%>'>
		</div>
	</div>
	<input type="hidden" class="applyNo"
		value='<%=session.getAttribute("applyNo")%>' />
	<!-- 画面用のjs -->
	<script type="text/javascript"
		src="${baseurl}/lib/jquery/jquery-3.1.1.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/applied/applied.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
</body>
</html>