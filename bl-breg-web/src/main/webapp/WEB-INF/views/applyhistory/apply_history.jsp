<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<!--lib css -->
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/css/common/font-awesome.min.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/base.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<!--共通部品 css -->
<link rel="stylesheet" href="${baseurl}/lib/wijmo5/styles/wijmo.css">
<!-- 個別css -->
<link rel="stylesheet" href="${baseurl}/css/applyhistory/apply_history.css">
<link rel="stylesheet" href="${baseurl}/css/apply/apply_grid.css">

<title>申請履歴一覧</title>
</head>

<body>
	<jsp:include page="../common/header.jsp" />
	<div class="page-header">
		<h2>申請履歴一覧</h2>
	</div>
	<input type="hidden" class="apply-new-status">
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<span class='pull-right panel-heading-icon glyphicon glyphicon-chevron-up'></span>
				<h3 class="panel-title">検索条件</h3>
			</div>
			<div class="panel-body">
				<div class="input-group apply-no">
					<span class="input-group-addon">申請ID</span> <input type="text"
						class="form-control applyno" maxlength="8">
				</div>
				<div class="input-group apply-status">
					<span class="input-group-addon">ステータス</span> <select
						class="form-control applystatus">
						<option value=""></option>
						<c:forEach items="${applystatus }" var="status">
							<option value="${status.key }">${status.value }</option>
						</c:forEach>
					</select>
				</div>
				<div class="input-group insert-date">
          			<span class="input-group-addon ">申請期間</span> <input name="insDtTimeStart"
          			  class=" form-control date-input text-right applydatefrom" maxlength="10" />
          			  <span class="input-group-addon between">～</span><input
           			 name="insDtTimeEnd" class=" form-control date-input text-right applydateto" maxlength="10" />
       			</div>
				<div class="input-group apply-status slide-pannel">
						<span class="input-group-addon column1">BLコード</span> <select
							name="blCd" class="form-control codebl">
							<option value=""></option>
							<c:forEach items="${blcodeinfo }" var="blcode">
								<option value="${blcode.key }">${blcode.value }</option>
							</c:forEach>
						</select>
					</div>
				<div class="input-group apply-no slide-pannel">
					<span class="input-group-addon">優良品番</span> <input type="text"
						class="form-control primepartsno" maxlength="24">
				</div>
				<div class="input-group apply-type slide-pannel">
					<span class="input-group-addon">申請種類</span> <select
						class="form-control applytype">
						<option value="2">全データ表示</option>
						<c:forEach items="${applytype }" var="type">
							<option value="${type.key }">${type.value }</option>
						</c:forEach>
					</select>
				</div>
				<div class="input-group apply-no">   
					<span class="input-group-addon">カーコード</span>  <select
							name="blCd" class="form-control partsmakercd">
							<option value=""></option>
							<c:forEach items="${carmakerinfo }" var="carmakercode">
								<option value="${carmakercode.key }">${carmakercode.value }</option>
							</c:forEach>
						</select>
				</div>
				<div class="input-group apply-no">
					<span class="input-group-addon">純正品番</span><input
                        id="joinSourPartsNoWithH" type="text" class="form-control" maxlength="10" oninput="numInput(this.id)"
                        name = "joinSourPartsNoWithH" />
                                                <button type="button"
                                class="glyphicon glyphicon-star-empty btn btn-default joinSourPartsNoWithH"
                                name="joinSourPartsNoWithHGuide"></button>
				</div>
				<div class="blank"></div>
				<div class="group-button">
					<button class="btn bl-btn-panel bl-btn-3 btn-search" type="button">検索</button>
					<button class="btn bl-btn-panel bl-btn-3 btn-clear" type="button">クリア</button>
					<button class="btn bl-btn-panel btn-slide" type="button">閉じる</button>
				</div>
			</div>
		</div>

		<div class="result-list">
			<div class="result-title">
				<div class="input-group pull-left data-sort">
					<span class="input-group-addon">表示順</span> <select
						class="form-control sort">
						<option value="1">申請日時昇順</option>
						<option value="2" selected>申請日時降順</option>
					</select>
				</div>
				<div class="apply_kensu">
					<span>申請中:</span><span class="badge applycount"></span> <span>承認済:</span><span
						class="badge approvalcount"></span>
				</div>
				<div class="group-button pull-left">
					<button class="btn bl-btn-panel apply-shosai" type="button">詳細</button>
					<button class="btn bl-btn-panel total-joho" type="button">累積情報</button>
				</div>
			</div>
			<div class="flexgrid-content" id="grid"></div>
			<div class="tab_grid">
			<div class="page_head">
				<div>検索数/全件数:
				<span class="searchcount"></span>/
				<span class="totalcount"></span></div>
				<div class="page-box"></div>	
				<div class="page-info">
					<span>1ページに、</span>
					<span class="historyrows"></span>
					<span>件</span>
				</div>
			</div>
			<div class="text-center">
				<button class="btn bl-btn-panel page-back">戻る</button>
			</div>
		</div>
		</div>
	</div>
	
	<jsp:include page="../common/footer.jsp" />
	<script type="text/javascript" src="${baseurl}/lib/jquery/jquery-2.2.0.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
	<script src="${baseurl}/js/common/common.js" type="text/javascript"></script>
	<script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/cultures/wijmo.culture.ja.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.grid.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.input.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/header.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script> 
	<script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/moment/moment-with-locales.min.js"></script>
	<script type="text/javascript" src="${baseurl}/js/applyperiodguide/applyperiodguide.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/jquery.pagination.min.js"></script>
	<script type="text/javascript" src="${baseurl}/js/goodsguide/showGoodsGuide.js"></script>
	<script type="text/javascript" src="${baseurl}/js/applyhistory/apply_history.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/check.js"></script>
	<script type="text/javascript">window.baseurl = "${baseurl}";</script>
	
</body>

</html>