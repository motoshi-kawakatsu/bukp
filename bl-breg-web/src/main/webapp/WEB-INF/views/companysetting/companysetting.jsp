<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<!--  <link rel="stylesheet" href="${baseurl}/lib/reset/reset.css">-->
<link rel="stylesheet"
	href="${baseurl}/lib/datatables/css/dataTables.bootstrap.css">
<!--  <link rel="stylesheet"
	href="${baseurl}/css/bootstrap/css/dataTables.responsive.css">-->
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<link rel="stylesheet" href="${baseurl}/css/companysetting/employee.css">
<title>会社情報</title>
</head>
<body class="companySettingBody">
	<jsp:include page="../common/header.jsp" />
	<div class="main">
	<div id="webwrapper">
		<div class="page-header" style="margin-top: 0">
			<h2 class="companysettingheader">会社情報</h2>
		</div>
			<ul class="nav nav-tabs" id="myTab">
				<li class="active"><a href="#comInfo" data-toggle="tab"
					class="backbg"><strong>会社情報</strong></a></li>
				<li><a href="#setInfo" data-toggle="tab" class="backbg"><strong>設定情報</strong></a>
				</li>
			</ul>
			
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane" id="comInfo">
					<div class="container">
							<div class="panel-body">
								<div class="input-group employee-length-2 makerCodeArea">
									<label> <img
										class="rightspace4 leftspace6 msg_info_display"> <span
										class="input-group-addon">メーカーコード</span> <input type="text"
										name="MakerCodeRF" class="form-control MakerCodeRF"
										maxlength="4" readonly="readonly"
										value="${requestScope.companySetting_makerCode }" />
									</label>
								</div>
								<div class="input-group employee-length-2">
									<label> <img
										class="rightspace4 leftspace6 msg_info_display"
										style="display: block;"> <span class="input-group-addon">メーカーコード名称<span
											class="input-check">*</span></span> <input id="makerName"
										type="text" name="MakerNameRF" class="form-control imeActive"
										maxlength="30"
										value="${requestScope.companyInfo.getMakerCdName() }" />
									</label>
								</div>
								<div class="input-group employee-length-2">
									<label> <img
										class="rightspace4 leftspace6 msg_info_display"> <span
										class="input-group-addon">メーカーコード名称（半角）<span
											class="input-check">*</span></span> <input id="makerKana"
										type="text" name="MakerNameHalfRF"
										class="form-control imeInactive" maxlength="30"
										value="${requestScope.companyInfo.getMakerCdNameShort() }" />
									</label>
								</div>
								<div class="input-group employee-length-2">
									<label> <img
										class="rightspace4 leftspace6 msg_info_display"> <span
										class="input-group-addon">会社名称<span class="input-check">*</span></span>
										<input id="companyName" type="text" name="ComRF"
										class="form-control imeActive" maxlength="30"
										value="${requestScope.companyInfo.getCompanyName() }" />
									</label>
								</div>
								<div class="input-group employee-length-2">
									<label> <img
										class="rightspace4 leftspace6 msg_info_display"> <span
										class="input-group-addon">会社名称（カナ）<span
											class="input-check">*</span></span> <input id="companyKana"
										type="text" name="ComKanaRF" class="form-control imeInactive"
										maxlength="30"
										value="${requestScope.companyInfo.getCompanyNameKana() }" />
									</label>
								</div>
								<div class="input-group employee-length-2">
									<label> <img
										class="rightspace4 leftspace6 msg_info_display"> <span
										class="input-group-addon">郵便番号<span class="input-check">*</span></span>
										<input id="postCode" type="text" name="MailCodeRF"
										class="form-control imeActive" maxlength="16"
										value="${requestScope.companyInfo.getMailNo() }" />
									</label>
								</div>
								<div class="input-group employee-length-2">
									<label> <img
										class="rightspace4 leftspace6 msg_info_display"> <span
										class="input-group-addon">住所<span class="input-check">*</span></span>
										<input id="address" type="text" name="AddRF"
										class="form-control imeActive" maxlength="60"
										value="${requestScope.companyInfo.getAddress() }" />
									</label>
								</div>
								<div class="input-group employee-length-2">
									<label> <img
										class="rightspace4 leftspace6 msg_info_display"> <span
										class="input-group-addon">TEL<span class="input-check">*</span></span>
										<input id="telNo" type="text" name="TelNoRF"
										class="form-control imeActive" maxlength="16"
										value="${requestScope.companyInfo.getTel() }" />
									</label>
								</div>
								<div class="input-group employee-length-2">
									<label> <img
										class="rightspace4 leftspace6 msg_info_display"> <span
										class="input-group-addon">FAX<span class="input-check">*</span></span>
										<input id="faxNo" type="text" name="FaxNoRF"
										class="form-control imeActive" maxlength="16"
										value="${requestScope.companyInfo.getFax() }" />
									</label>
								</div>
								<div class="input-group employee-length-2">
									<label> <img
										class="rightspace4 leftspace6 msg_info_display"> <span
										class="input-group-addon">備考</span> <input id="note"
										type="text" name="RemarkRF" class="form-control imeActive"
										maxlength="1024"
										value="${requestScope.companyInfo.getNotes() }" />
									</label>
								</div>
							</div>
						</div>
				</div>

				<div class="tab-pane" id="setInfo">
					<div class="container container-setter">
						<div class="panel-primary slide-panel grid-setter selectorCodeArea">
							<div class="panel-heading">
								<h3 class="panel-title panel-title-set">セレクトコード</h3>
							</div>
							<div class="table-div">
								<table class="table table-striped table-bordered table-hover"
									id="zqc">
									<c:forEach items="${requestScope.selecterCode }"
										var="selecterCodeDis">
										<tr>
											<td><c:out value="${selecterCodeDis }" /></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
						<div class="panel-primary slide-panel grid-setter">
							<div class="panel-heading">
								<h3 class="panel-title panel-title-set">BLコード</h3>
							</div>
							<div class="table-div">
								<table class="table table-striped table-bordered table-hover"
									id="zqc">
									<tbody>
										<c:forEach items="${requestScope.blCode }" var="blCodeDis">
											<tr>
												<td><c:out value="${blCodeDis }" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="panel-primary slide-panel grid-setter">
							<div class="panel-heading">
								<h3 class="panel-title panel-title-set">種別コード</h3>
							</div>
							<div class="table-div">
								<table class="table table-striped table-bordered table-hover"
									id="zqc">
									<c:forEach items="${requestScope.kindCode }" var="kindCodeDis">
										<tr>
											<td><c:out value="${kindCodeDis }" /></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
						<div class="panel-primary slide-panel grid-setter">
							<div class="panel-heading">
								<h3 class="panel-title panel-title-set">層別コード</h3>
							</div>
							<div class="table-div">
								<table class="table table-striped table-bordered table-hover"
									id="zqc">
									<c:forEach items="${requestScope.partsCode }"
										var="partsCodeDis">
										<tr>
											<td><c:out value="${partsCodeDis }" /></td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
						<div class="col-md-12 text-area-7">
							<div class="col-md-5 text-area-6">
								<h3 class="panel-title panel-title-left setter-title">ページ内行数設定</h3>
								<div class="panel-body table-behind">
									<div class="input-group employee-length-3">
										<label> <img
											class="rightspace4 leftspace6 msg_info_display"> <span
											class="input-group-addon input-group-addon-tab2">商品</span> <input
											id="goodsRows" type="text" name="GoodsRF"
											class="form-control form-control2 imeInactive" maxlength="4"
											value="${requestScope.companyInfo.getGoodsRows() }" />
										</label>
									</div>
									<div class="input-group employee-length-3">
										<label> <img
											class="rightspace4 leftspace6 msg_info_display"> <span
											class="input-group-addon input-group-addon-tab2">セット</span> <input
											id="setRows" type="text" name="SetRF"
											class="form-control form-control2 imeInactive" maxlength="4"
											value="${requestScope.companyInfo.getSetRows() }"/>
										</label>
									</div>
									<div class="input-group employee-length-3">
										<label> <img
											class="rightspace4 leftspace6 msg_info_display"> <span
											class="input-group-addon input-group-addon-tab2">結合</span> <input
											id="joinRows" type="text" name="JoinRF"
											class="form-control form-control2 imeInactive" maxlength="4"
											value="${requestScope.companyInfo.getJoinRows() }" />
										</label>
									</div>
									<div class="input-group employee-length-3">
										<label> <img
											class="rightspace4 leftspace6 msg_info_display"> <span
											class="input-group-addon input-group-addon-tab2">申請履歴</span>
											<input id="applyHistoryRows" type="text" name="ApplyResumeRF"
											class="form-control form-control2 imeInactive" maxlength="4"
											value="${requestScope.companyInfo.getApplyRecordRows() }" />
										</label>
									</div>
									<div class="input-group employee-length-3">
										<label> <img
											class="rightspace4 leftspace6 msg_info_display"> <span
											class="input-group-addon input-group-addon-tab2">申請詳細・累積情報</span>
											<input id="applyDetailRows" type="text" name="ApplyDetailRF"
											class="form-control form-control2 imeInactive" maxlength="4"
											value="${requestScope.companyInfo.getApplyDetailRows() }" />
										</label>
									</div>
								</div>
							</div>
							<div class="col-md-2"></div>
							<div class="col-md-5">
								<h3 class="panel-title panel-title-left setter-title">インポート方法</h3>
								<div class="panel-body table-behind">
									<div class="input-group employee-length-4 span-area-6">
										<div class="label-area-6">
										<span class="input-group-addon text-area-8">商品</span></div>
										<div class="goods-con">
											<div class="select_group">
												<label><input name="good_select_type"
													class="goods_select_all" type="radio"
													value="${goodsImportAll }" />全件</label> <label><input
													name="good_select_type" class="goods_select_dif"
													type="radio" value="${goodsImportDif }" />差分</label>
											</div>
										</div>
									</div>
									<div class="input-group employee-length-4 span-area-6">
										<div class="label-area-6">
										<span class="input-group-addon text-area-8">セット</span></div>
										<div class="set-con">
											<div class="select_group">
												<label><input name="set_select_type"
													class="set_select_all" type="radio"
													value="${setImportAll }" />全件</label> <label><input
													name="set_select_type" class="set_select_dif" type="radio"
													value="${setImportDif }" />差分</label>
											</div>
										</div>
									</div>
									<div class="input-group employee-length-4 span-area-6">
										<div class="label-area-6">
										<span class="input-group-addon text-area-8">結合</span></div>
										<div class="union-con">
											<div class="select_group">
												<label><input name="union_select_type"
													class="join_select_all" type="radio"
													value="${joinImportAll }" />全件</label> <label><input
													name="union_select_type" class="join_select_dif"
													type="radio" value="${joinImportDif }" />差分</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12 companySettingBtn">
				<button type="button"
						class="btn bl-btn-wiz-prev bl-btn-wiz-prev-2 back">戻る</button>
				<button type="button"
						class="btn bl-btn-wiz-next bl-btn-wiz-next-2 save">保存</button>
			</div>
	</div>
	</div>
	<div class="hidenArea">
		<input type="text" class="message" name="confirmMsg" value="${requestScope.saveConfirmMsg }" />
		<input type="text" class="message" name="informMsg" value="${requestScope.saveSuccessMsg }" />
	</div>
	<jsp:include page="../common/footer.jsp" />
	<script type="text/javascript"
		src="${baseurl}/lib/jquery/jquery-3.1.1.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/uri/URI.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/common/parts/panel.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/common/parts/header.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/companysetting/companysetting.js"></script>
	<script type="text/javascript">
		window.baseurl = "${baseurl}";
	</script>
</body>
</html>