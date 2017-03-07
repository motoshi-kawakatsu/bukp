<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<!--lib css-->
<link rel="stylesheet" href="${baseurl}/lib/reset/reset.css">
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/lib/wijmo5/styles/wijmo.css">
<!--common css-->
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">

<!-- 個別css -->
<link rel="stylesheet" href="${baseurl}/css/setDetail/setDetail.css">
<link rel="stylesheet" href="${baseurl}/css/setDetail/wijmoGrid.css">
<title>セット詳細</title>
</head>
<body>
		<div class="page-header page-header-extra">
			<h2 class="title-text">セット詳細</h2>
		</div>
		<div class="container">
			<div class="panel panel-primary slide-panel">
			<div class="panel-heading head">
				<h3 class="panel-title">
					親商品情報
					<span class="required-title" style="font-family: '\ff2d\ff33 \30b4\30b7\30c3\30af';">*は必須入力項目です</span>
				</h3>
			</div>
				<input class="hidden-mode" value="${mode }" />	
				<div class="panel-body panel-row">
					<div class="input-group bl-cd">
						<span class="input-group-addon column1">BLコード</span> <select
							name="blCd" class="form-control bl-code" >
							<option value=""></option>
							<c:forEach items="${blcodeinfo }" var="item">
								<option value="${item.blCode}">${item.blCode}：${item.blFullName}</option>
							</c:forEach>
						</select>
					</div>

					<div class="input-group select-cd">
						<span class="input-group-addon column1">セレクトコード<span
							style='color: red'>*</span></span> <select
							class="form-control select-code" name="selectCd">
							<option value=""></option>
							<c:forEach items="${selecterCode }" var="item">
								<option value="${item.prmSetDtlNo1}">${item.prmSetDtlNo1}：${item.prmSetDtlName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="input-group create-date">
						<span class="input-group-addon column1"><b>作成日時</b></span> <input
							name="insert_start_dt"
							class="form-control date-input text-right createdate"
							disabled="disabled" />
					</div>
					<div class="input-group set-father-no">
						<span class="input-group-addon column1">セット親品番<span
							style='color: red'>*</span></span> <input name="setFatherCd" type="text"
							class="form-control set-main-parts-no" maxlength="512"
							disabled="disabled">
						<button type="button"
							class="glyphicon glyphicon-star-empty btn btn-default setParentCdGuide"></button>
					</div>

					<div class="input-group classify-cd">
						<span class="input-group-addon column1">分類コード</span> <input
							name="classifyCd" type="text" disabled="disabled"
							class="form-control  classify-code" disabled="disabled"
							maxlength="4">
						<button type="button"
							class="glyphicon glyphicon-star-empty btn btn-default classifyCdGuide"
							name="blCodeGuide"></button>
					</div>
					<div class="input-group update-date">
						<span class="input-group-addon column1"><b>更新日時</b></span> <input
							name="insert_start_dt"
							class="form-control date-input text-right update-time"
							disabled="disabled" />
					</div>
					<div class="input-group apply-kubun">
						<span class="input-group-addon column1">申請状態</span> <select
							name="apply" class="form-control applyCondition"
							disabled="disabled">
							<c:forEach items="${applyCondition}" var="item">
								<option value="${item.value}">${item.name}</option>
							</c:forEach>
						</select>
					</div>

					<div class="input-group shuri-kubun">
						<span class="input-group-addon column1">処理区分</span> <select
							name="shuri-kubun" class="form-control del-kubun"
							disabled="disabled">
							<c:forEach items="${manageKbn}" var="item">
								<option value="${item.value}">${item.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="panel-primary">
			<div class="panel-heading ">
				<h3 class="panel-title">セット商品情報</h3>
			</div>
			<p></p>
				<div class=" grid-top-group-button">
					<div class="group-button ">
						<button class="btn bl-btn-panel bl-btn-4 btn-add">行挿入</button>
						<button class="btn bl-btn-panel bl-btn-4 btn-del">行削除</button>
						<button class="btn bl-btn-panel bl-btn-4 btn-copy">行コピー</button>
						<button class="btn bl-btn-panel bl-btn-4 btn-paste">行貼付</button>
						<button class="btn bl-btn-panel bl-btn-4 replaceGuide">検索・置換</button>
						<br />
					</div>
					<div id="grid" style="height: 200px"></div>
					<br />
				</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="text-center">
							<button type="button"
								class="bl-btn-panel btn bl-btn-wiz-prev bl-btn-5 btn-back">
								<span class="glyphicon glyphicon-menu-left"></span>戻る
							</button>
							<button type="button"
								class="btn bl-btn-panel bl-btn-5 btn-confirm">確定</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	<!-- lib js -->
	<script type="text/javascript"
		src="${baseurl}/lib/jquery/jquery-2.2.0.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/wijmo.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/cultures/wijmo.culture.ja.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/wijmo.grid.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/wijmo.input.min.js"></script>
	<!--common js-->
	<script src="${baseurl}/js/common/common.js" type="text/javascript"></script>
	<script src="${baseurl}/js/common/inputFormat.js"
		type="text/javascript"></script>
	<script src="${baseurl}/js/common/check.js"
		type="text/javascript"></script>
	<script type="text/javascript"
		src="${baseurl}/js/changecommon/show_changeCommon.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/classifycodeguide/showClassifyCodeGuide.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/goodsguide/showGoodsGuide.js"></script>
	<!-- 個別js -->
	<script type="text/javascript"
		src="${baseurl}/js/setdetail/setdetail.js"></script>
	<script type="text/javascript">
		window.baseurl = "${baseurl}";
	</script>
</body>
</html>