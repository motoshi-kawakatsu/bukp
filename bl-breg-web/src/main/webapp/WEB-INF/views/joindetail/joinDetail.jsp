<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>結合詳細</title>


<link rel="stylesheet" href="${baseurl}/lib/reset/reset.css">
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/lib/wijmo5/styles/wijmo.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<!-- 個別css -->
<link rel="stylesheet" href="${baseurl}/css/joindetail/joinDetail.css">
<link rel="stylesheet"
	href="${baseurl}/css/joindetail/joinDetail_grid.css">

</head>
<body>
	<div class="page-header page-header-extra">
		<h2 class="title-text">結合詳細</h2>
	</div>

	<div class="container">
		<div class="panel panel-primary slide-panel">
			<div class="panel-heading head">
				<h3 class="panel-title">
					純正部品情報<span class="required-title"
						style="font-family: '\ff2d\ff33 \30b4\30b7\30c3\30af';">*は必須入力項目です</span>
				</h3>
			</div>
			<div class="panel-body panel-row">
				<div class="input-group select-cd">
					<span class="input-group-addon column1"> セレクトコード<span
						style="color: red">*</span></span> <select id="selectCd"
						class="form-control prm_set_dtl_no_1">
						<option value=""></option>
						<option value="9999">9999：指定無し</option>
						<c:forEach items="${selecterCode }" var="item">
							<option value="${item.prmSetDtlNo1}">${item.prmSetDtlNo1}：${item.prmSetDtlName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="input-group car-cd">
					<span class="input-group-addon column2"> カーコード<span
						style="color: red">*</span></span> <select id="joinSourceMakerCode"
						class="form-control parts_maker_cd">
						<option value=""></option>
						<c:forEach items="${carmakerNameMap }" var="item">
							<option value="${item.key}">${item.value}</option>
						</c:forEach>
					</select>
				</div>
				<div class="input-group apply-state">
					<span class="input-group-addon column3"><b>申請状態</b></span> <input
						type="text" class="form-control apply_condition" maxlength="60" />
				</div>
				<div class="input-group classify-cd">
					<span class="input-group-addon column1"> 分類コード<span
						style="color: red">*</span></span> <input name="classifyCd" type="text"
						class="form-control classify-code" maxlength="4" />
					<button type="button"
						class="glyphicon glyphicon-star-empty btn btn-default classifyCdGuide"
						name="blCodeGuide"></button>
				</div>
				<div class="input-group ketsugou-num">
					<span class="input-group-addon column2"> 純正品番<span
						style="color: red">*</span></span> <input name="joinSourParts"
						type="text" class="form-control goodsName" maxlength="24" />
					<button type="button"
						class="glyphicon glyphicon-star-empty btn btn-default joinSourPartsNoWithH"
						name="joinSourPartsNoWithHGuide"></button>
				</div>
				<div class="input-group shori-kubun">
					<span class="input-group-addon column3">処理区分</span> <input
						type="text" class="form-control manage-kbn" maxlength="60" />
				</div>
				<div class="input-group bl-cd ">
					<span class="input-group-addon column1">BLコード<span
						style="color: red">*</span></span> <select id="blCd"
						class="form-control tbs-parts-code">
						<option value=""></option>
						<c:forEach items="${blcodeinfo }" var="item">
							<option value="${item.blCode}">${item.blCode}：${item.blFullName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="input-group error-distinction ">
					<span class="input-group-addon column2">エラー区分</span> <input
						type="text" class="form-control error-flg" id="" maxlength="60" />
				</div>
			</div>
		</div>

		<div class="panel panel-primary section">
			<div class="panel-heading ">
				<h3 class="panel-title">優良部品情報</h3>
			</div>
			<p></p>
			<div class="bl-flex-row grid-top-group-button">
				<div class="group-button">
					<button class="btn bl-btn-panel bl-btn-4 btn-add">行挿入</button>
					<button class="btn bl-btn-panel bl-btn-4 btn-del">行削除</button>
					<button class="btn bl-btn-panel bl-btn-4 btn-copy">行コピー</button>
					<button class="btn bl-btn-panel bl-btn-4 btn-paste">行貼付</button>
					<button class="btn bl-btn-panel bl-btn-4 replaceGuide">検索・置換</button>
				</div>
			</div>
		</div>

		<div id="grid" style="height: 400px"></div>
		<br />

		<div class="row">
			<div class="col-sm-12">
				<div class="text-center">
					<button type="button"
						class="bl-btn-panel btn bl-btn-wiz-prev bl-btn-5 btn-back">
						<span class="glyphicon glyphicon-menu-left"></span>戻る
					</button>
					<button type="button" class="btn bl-btn-panel bl-btn-5 btn-confirm">確定</button>
				</div>
			</div>
		</div>
	</div>
	<!--lib js-->
	<script type="text/javascript"
		src="${baseurl}/lib/jquery/jquery-2.2.0.min.js"></script>

	<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/common/parts/header.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/wijmo.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/cultures/wijmo.culture.ja.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/wijmo.grid.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/wijmo.input.min.js"></script>
	<!--common js-->
	<script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/changecommon/show_changeCommon.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/classifycodeguide/showClassifyCodeGuide.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/goodsguide/showGoodsGuide.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/check.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/common/inputFormat.js"></script>
	<!--personal js-->
	<script type="text/javascript"
		src="${baseurl}/js/joindetail/joindetail.js"></script>
	<script type="text/javascript">
		window.baseurl = "${baseurl}";
	</script>
</body>
</html>