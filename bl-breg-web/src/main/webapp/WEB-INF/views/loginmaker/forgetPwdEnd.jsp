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
<link rel="stylesheet" href="${baseurl}/css/loginmaker/forgetPwdEnd.css">
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
				<ul></ul>
			</div>
			<div class ="send">
				<i class="fa fa-envelope"></i>
				<p>メールを送信しました</p>
				<p>
					<span id="time_remain">15</span>秒後にログインページへ戻ります<br>
					メールを受信できない場合は、システム管理者へお問い合わせください
				</p>
				<a href="login" class="normal">
				<button class="btn bl-btn-panel bl-btn-4" type="submit" id="close">戻る</button>
				</a>
			</div>
		</section>
	</div>
	<jsp:include page="../common/footer.jsp" />

	<script type="text/javascript" src="${baseurl}/lib/jquery/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/jquery.mockjax/jquery.mockjax.min.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/header.js"></script>
	<script type="text/javascript" src="${baseurl}/js/loginmaker/forgetPwdEnd.js"></script>
	<script type="text/javascript">
		window.baseurl = "${baseurl}";
	</script>
</body>
</html>