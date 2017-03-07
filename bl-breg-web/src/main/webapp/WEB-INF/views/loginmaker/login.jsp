<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<html>
<head>
<title>カーパーツマネージャー</title>
<meta charset="UTF-8">
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<link rel="shortcut icon" href="${baseurl}/imgs/favicon.ico">
<link rel="stylesheet" href="${baseurl}/lib/reset/reset.css">
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<link rel="stylesheet" href="${baseurl}/css/loginmaker/loginCommon.css">
<link rel="stylesheet" href="${baseurl}/css/loginmaker/login.css">
</head>
<body>
	<header class="bl-base-layout-header system-header">
		<div class="title col-lg-3">
			<h4>
				<img src="${baseurl}/imgs/logo_broadleaf.gif" alt="Broadleaf"
					class="bllogo"><br>カーパーツマネージャー
			</h4>
		</div>
	</header>
	<div id="webwrapper">
		<section>
			<div id="breadcrumb">
				<ul>
				</ul>
			</div>
			<bl:msgdisplay />
			<div class="txtarea">
				<h2>メーカーコード、ログインIDとパスワードを入力してください</h2>
				<div class="inputarea">
					<form>
						<p>
							<label class="normal">メーカーコード<span class="star-mark">*</span>
								<img class="rightspace4 leftspace6 msg_info_display"> 
								<input id="makerCd" type="text" class="form-control" value="<c:out value="${form.makerCd}"/>" oninput="numInput(this.id)" maxlength="4" />
							</label>
						</p>
						<p>
							<label class="normal">ログインID<span class="star-mark">*</span>
								<img class="rightspace4 leftspace6 msg_info_display"> 
								<input id="loginId" type="text" class="form-control" value="<c:out value="${form.loginId}"/>" maxlength="24" />
							</label>
						</p>
						<p>
							<label class="normal">パスワード<span class="star-mark">*</span>
								<img class="rightspace4 leftspace6 msg_info_display"> 
								<input id="password" type="password" class="form-control" maxlength="16" />
							</label>
						</p>
					</form>
				</div>
				<div class="checkboxarea">
					<form>
						<input type="checkbox" id="checkbox01" name="demo" checked /> 
						<label class="checkbox" for="checkbox01">ログイン状態を維持する</label>
					</form>
				</div>
				<button id="login" class="btn bl-btn-panel bl-btn-4">ログイン</button>
				<div class="otherarea">
					<div class="textlink">
						<p></p>
						<p></p>
						<a href="#" id="forgetPwd" class="normal">パスワードをお忘れの場合</a>
					</div>
				</div>
			</div>
		</section>
	</div>
	<input id="passwordReminderUrl" type="hidden" value="<c:out value='${passwordReminderUrl}'/>" />
	<jsp:include page="../common/footer.jsp" />

	<script type="text/javascript" src="${baseurl}/lib/jquery/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/jquery.mockjax/jquery.mockjax.min.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/inputFormat.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/header.js"></script>
	<script type="text/javascript" src="${baseurl}/js/loginmaker/login.js"></script>
	<script type="text/javascript">
		window.baseurl = "${baseurl}";
	</script>
</body>
</html>