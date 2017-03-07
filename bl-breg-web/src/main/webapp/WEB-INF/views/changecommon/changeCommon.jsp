<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${baseurl}/lib/reset/reset.css" />
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<link rel="stylesheet"
	href="${baseurl}/css/changecommon/changeCommon.css">
</head>

<body>
	<div class="text-center">
		<h2>
			<b>検索・置換</b>
		</h2>
	</div>
	<div class="container">
		<div>
			<div class="panel-heading head panel-heading-down">
				<h3 class="panel-title">置換項目と置換値を設定してください。</h3>
			</div>
			<div class="panel-body panel-row container-center">
				<div class="input-group">
					<span class="input-group-addon column1"><b>置換項目</b></span> <select
						class="form-control changeItem">
					</select>
				</div>
				<div class="input-group changeText guideButton"
					style="display: none">
					<span class="input-group-addon column1"><b>変更前</b></span> <input
						type="text" class="form-control changeBefore" name="changeBefore" />
					<button type="button"
						class="glyphicon glyphicon-star-empty btn btn-default guide"
						name="changeBefore"></button>
				</div>
				<div class="input-group changeText dropDown" style="display: none">
					<span class="input-group-addon column1"><b>変更前</b></span> <select
						class="form-control changeBefore"></select>
				</div>
				<div class="input-group changeText textBox">
					<span class="input-group-addon column1"><b>変更前</b></span> <input
						type="text" class="form-control changeBefore" />
				</div>

				<div class="input-group changeText guideButton"
					style="display: none">
					<span class="input-group-addon column1"><b>変更後</b></span> <input
						type="text" class="form-control changeAfter" name="changeAfter" />
					<button type="button"
						class="glyphicon glyphicon-star-empty btn btn-default guide"
						name="changeAfter"></button>
				</div>
				<div class="input-group changeText dropDown" style="display: none">
					<span class="input-group-addon column1"><b>変更後</b></span> <select
						class="form-control changeAfter">
					</select>
				</div>
				<div class="input-group changeText textBox">
					<span class="input-group-addon column1"><b>変更後</b></span> <input
						type="text" class="form-control changeAfter" />
				</div>
				<div class="button-group">
					<button class="btn bl-btn-panel bl-btn-3 search" type="button">検索</button>
					<button class="btn bl-btn-panel bl-btn-3 replace" type="button">置換</button>
					<button class="btn bl-btn-panel bl-btn-3 closeup" type="button">閉じる</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${baseurl}/lib/uri/URI.min.js"></script>
	<script src="${baseurl}/lib/jquery/jquery-2.2.0.min.js"
		type="text/javascript"></script>
	<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
	<script src="${baseurl}/lib/bootstrap/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="${baseurl}/js/common/common.js" type="text/javascript"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/wijmo.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/cultures/wijmo.culture.ja.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/wijmo.grid.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/wijmo.input.min.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/common/check.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/common/inputFormat.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/moment/moment-with-locales.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/applyperiodguide/applyperiodguide.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/goodsguide/showGoodsGuide.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/classifycodeguide/showClassifyCodeGuide.js"></script>
	<script src="${baseurl}/js/changecommon/changeCommon.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		window.baseurl = "${baseurl}";
	</script>
</body>
</html>