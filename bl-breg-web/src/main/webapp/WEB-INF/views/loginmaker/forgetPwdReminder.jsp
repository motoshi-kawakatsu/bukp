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
<link rel="stylesheet" href="${baseurl}/css/common/font-awesome.min.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<link rel="stylesheet" href="${baseurl}/css/loginmaker/loginCommon.css">
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
	<div id="header2">
		<h2>パスワードをお忘れの場合</h2>
	</div>
	<div id="webwrapper">
		<section>
			<div id="breadcrumb">
				<ul></ul>
			</div>
			<bl:msgdisplay />

			<div class="txtarea">
				<h2>メーカーコードとログインIDを入力後、送信してください</h2>
				<div class="inputarea">
					<form onsubmit="return false">
						<p>
							<label class="normal">メーカーコード<span class="star-mark">*</span>
								<img class="rightspace4 leftspace6 msg_info_display"> 
								<input type="text" id="makerCd" class="form-control" oninput="numInput(this.id)" maxlength="4"/>
							</label> <label class="normal">ログインID<span class="star-mark">*</span>
								<img class="rightspace4 leftspace6 msg_info_display"> 
								<input type="text" id="loginId" class="form-control" maxlength="24"/>
							</label>
						</p>
					</form>
				</div>
				<button id="goback" class="btn bl-btn-panel bl-btn-4">戻る</button>
				<button id="sendMsg" class="btn bl-btn-panel bl-btn-4">
					<i class="fa fa-envelope"></i> 送信
				</button>
				<p></p>
				<p class="center">
					送信後、すぐにメールが届きます<br>受信後はメールの指示に従ってください<br>※5分経ってもメールが届かない場合、もう一度お試しください
				</p>
			</div>
		</section>
	</div>
	<jsp:include page="../common/footer.jsp" />
	
	<script type="text/javascript" src="${baseurl}/lib/jquery/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/jquery.mockjax/jquery.mockjax.min.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/inputFormat.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/header.js"></script>
	<script type="text/javascript" src="${baseurl}/js/loginmaker/forgetPwdReminder.js"></script>
	<script type="text/javascript">
		window.baseurl = "${baseurl}";
	</script>
</body>
</html>
