<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <title>優良部品登録システム</title>
        <meta charset="UTF-8">
        <meta name="description" content="" />
        <meta name="keywords" content="" />
        <meta name="viewport" content="width=device-width,initial-scale=1" />
        <link rel="shortcut icon" href="${baseurl}/imgs/favicon.ico">
        <link href="${baseurl}/css/font-awesome.min.css" type="text/css" rel="stylesheet">
        <link href="${baseurl}/css/jquery.rcrumbs.css" type="text/css" rel="stylesheet" />
        <link href="${baseurl}/css/webview.css" type="text/css" rel="stylesheet" media="all">
        <script src="${baseurl}/js/jquery-3.1.1.min.js" type="text/javascript"></script>
        <script src="${baseurl}/js/jquery.rcrumbs.js" type="text/javascript"></script>
        <script src="${baseurl}/js/footerFixed.js" type="text/javascript"></script>
        <script src="${baseurl}/js/common.js" type="text/javascript"></script>
		<script type="text/javascript">
			window.baseurl = "${baseurl}";
		</script>
    </head>
    <body>
		<img src="${baseurl}/imgs/logo_broadleaf.gif" alt="Broadleaf" class="bllogo">
		<header class="change">優良部品登録システム</header>
		<div id="webwrapper">
			<section>
				<div style="text-align: center;">
					<img src="${baseurl}/imgs/icon_3.png" style="margin: 90px 0 15px 0;">
                    <c:set var="errorMessage" value="${errorInfo.errors[0].message}" />
					<p>
	                    ${fn:replace(errorMessage, "\\n", "<br>")}
					</p>
					<button id="errPageGoBack" class="btnno">戻る</button>
				</div>
			</section>
		</div>
		<footer id="footer">Copyright(C) Broadleaf Co.,Ltd. All right reserved.</footer>
    </body>
</html>
