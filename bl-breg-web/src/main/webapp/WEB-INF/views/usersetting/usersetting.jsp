<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/lib/reset/reset.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<link rel="stylesheet" href="${baseurl}/css/usersetting/usersetting.css">
<title>担当者情報</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<div class="page-header">
		<h2>担当者情報</h2>
	</div>
	<div class = "msg" hidden = "hidden">${msgBack}</div>
	<div class = "msg1" hidden = "hidden">${msgSure}</div>
	<div class = "msg2" hidden = "hidden">${msgNothing}</div>
	<div class = "msg3" hidden = "hidden">${msgSave}</div>
	<div class="container">
		<div id="webwrapper">
			<div class="main">
				<div class="panel-body">
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display">
							<span class="input-group-addon">ログインID</span>
							<input type="text" name="loginId" id="loginId" value="${loginId}"
							 class="form-control loginId" maxlength="24" disabled="disabled"/>
						</label>
					</div>
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display" style="display: block;">
							<span class="input-group-addon">担当者名称<span class="mark">*</span></span>
							<input type="text" name="userName" id="userName" value="${userName}"
							 class="form-control"
							maxlength="30" />
						</label>
					</div>
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display">
							<span class="input-group-addon">担当者名称(カナ)<span class="mark">*</span></span>
							<input type="text" name="userKana" id="userKana" value="${userKana}"
							 class="form-control" maxlength="30" />
						</label>
					</div>
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display">
							<span class="input-group-addon">部署<span class="mark">*</span></span>
							<input type="text" name="departmentName" id="departmentName"
							 value="${departmentName}" maxlength="60" class="form-control" />
						</label>
					</div>
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display">
							<span class="input-group-addon">担当品目<span class="mark">*</span></span>
							<input type="text" name="item" id="item" value="${item}"
							 class="form-control" maxlength="60" />
						</label>
					</div>
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display">
							<span class="input-group-addon">郵便番号<span class="mark">*</span></span>
							<input type="text" name="postCode" id="postCode" value="${postCode}"
							 class="form-control" maxlength="16" />
						</label>
					</div>
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display">
							<span class="input-group-addon">住所<span class="mark">*</span></span>
							<input type="text" name="address" id="address" value="${address}"
							 class="form-control" maxlength="60" />
						</label>
					</div>
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display">
							<span class="input-group-addon">メールアドレス<span class="mark">*</span></span>
							<input type="text" name="mailAdd" id="mailAdd" value="${mailAdd}"
							 class="form-control" maxlength="30" />
						</label>
					</div>
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display">
							<span class="input-group-addon">TEL<span class="mark">*</span></span>
							<input type="text" name="telNo" id="telNo" value="${telNo}"
							 class="form-control" maxlength="16" />
						</label>
					</div>
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display">
							<span class="input-group-addon">FAX<span class="mark">*</span></span>
							<input type="text" name="faxNo" id="faxNo" value="${faxNo}"
							 class="form-control" maxlength="16" />
						</label>
					</div>
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display">
							<span class="input-group-addon">パスワード<span class="mark">*</span></span>
							<input type="password" name="loginPwdFirst" id="loginPwdFirst"
							 value="●●●●●●●●" class="form-control" maxlength="16" />
							<input type="hidden" name="loginPwd" id="loginPwd" value="${loginPwd}" maxlength="16" />
						</label>
					</div>
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display">
							<span class="input-group-addon">パスワード(確認用)<span class="mark">*</span></span>
							<input type="password" name="loginPwdTwoSecond" id="loginPwdTwoSecond"
							 value="●●●●●●●●" class="form-control" maxlength="16" />
							<input type="hidden" name="loginPwdTwo" id="loginPwdTwo" value="${loginPwd}" maxlength="16" />
						</label>
					</div>
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display">
							<span class="input-group-addon">種別</span>
							<select name="userAdminFlag" id="userAdminFlag"
							 class="form-control userAdminFlag" disabled="disabled">
								<option value=0 ${userAdminFlagZero}>一般</option>
								<option value=1 ${userAdminFlagOne}>ユーザー管理者</option>
								<option value=2 ${userAdminFlagTwo}></option>
							</select>
						</label>
					</div>
					<div class="input-group usersetting-length-2">
						<label>
							<img class="rightspace4 leftspace6 msg_info_display">
							<span class="input-group-addon">備考</span>
							<input type="text" name="note" id="note" value="${note}"
							 class="form-control" maxlength="1024" />
						</label>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-12 usersetting-btn">
		<button type="button" class="btn bl-btn-wiz-prev bl-btn-wiz-prev-2 goback">
			<span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>戻る
		</button>
		<button type="submit" class="btn bl-btn-wiz-next bl-btn-wiz-next-2 save">
			<span class="glyphicon" aria-hidden="true"></span>保存
		</button>
	</div>
	<jsp:include page="../common/footer.jsp" />
	<script type="text/javascript" src="${baseurl}/lib/jquery/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/header.js"></script>
	<script type="text/javascript" src="${baseurl}/js/usersetting/usersetting.js"></script>
	<script type="text/javascript">
		window.baseurl = "${baseurl}";
	</script>
</body>
</html>