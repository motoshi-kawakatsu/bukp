/**
 * ユーザーセッティング
 */
$(function() {
	'use strict';
	
	$('#userName').focus();

	var nav = new BLUI.Nabigeshon();
	nav.getData(11);
	
	var userAdminFlagDefault = $('#userAdminFlag').val();
	
	var passwordChange = true;
	
	// TextBoxのonChangeイベント
	$('.form-control').change(textAreaChangeAction);
	
	function textAreaChangeAction() {
		// 入力データーの定義
		var pwd1 = $('#loginPwdFirst').val();
		var pwd2 = $('#loginPwdTwoSecond').val();
		var userName = $('#userName').val();
		var userKana = $('#userKana').val();
		var departmentName = $('#departmentName').val();
		var item = $('#item').val();
		var postCode = $('#postCode').val();
		var address = $('#address').val();
		var mailAdd = $('#mailAdd').val();
		var telNo = $('#telNo').val();
		var faxNo = $('#faxNo').val();
		var userAdminFlag = $('#userAdminFlag').val();
		var note = $('#note').val();
		//編集する時
		if (userName != document.getElementById("userName").defaultValue
				|| userKana != document.getElementById("userKana").defaultValue
				|| departmentName != document.getElementById("departmentName").defaultValue
				|| item != document.getElementById("item").defaultValue
				|| postCode != document.getElementById("postCode").defaultValue
				|| address != document.getElementById("address").defaultValue
				|| mailAdd != document.getElementById("mailAdd").defaultValue
				|| telNo != document.getElementById("telNo").defaultValue
				|| faxNo != document.getElementById("faxNo").defaultValue
				|| pwd1 != document.getElementById("loginPwdFirst").defaultValue
				|| pwd2 != document.getElementById("loginPwdTwoSecond").defaultValue
				|| userAdminFlag != userAdminFlagDefault
				|| note != document.getElementById("note").defaultValue) {
			var msg = $('.msg').html();							
			sessionStorage.setItem("confirmMessage", msg);
		}
	}
	
	// 戻るボタンを押下する
	$(".goback").click(function () {
		var msg = $('.msg').html();
		var imp = sessionStorage.getItem("confirmMessage");
		var toptrans = function(index) {
			layer.close(index);
			sessionStorage.setItem("confirmMessage", null);
			window.location.href = baseurl + "/topmenu/topMenu";
		}
		if(imp != null && imp != "null") {
			layer.confirm(msg, {
				icon : 3,
				title : '',
				closeBtn : 0,
				btn : [ 'はい', 'いいえ' ]
			},toptrans)
		}else {
			window.location.href = baseurl + "/topmenu/topMenu";
		}
	});
	
	// 戻るボタンを押下するとTextBoxのonChangeイベント
	function backAction(e){
		// 入力データーの定義
		var pwd1 = $('#loginPwdFirst').val();
		var pwd2 = $('#loginPwdTwoSecond').val();
		var userName = $('#userName').val();
		var userKana = $('#userKana').val();
		var departmentName = $('#departmentName').val();
		var item = $('#item').val();
		var postCode = $('#postCode').val();
		var address = $('#address').val();
		var mailAdd = $('#mailAdd').val();
		var telNo = $('#telNo').val();
		var faxNo = $('#faxNo').val();
		var userAdminFlag = $('#userAdminFlag').val();
		var note = $('#note').val();
		//編集しない時
		if (userName == document.getElementById("userName").defaultValue
				&& userKana == document.getElementById("userKana").defaultValue
				&& departmentName == document.getElementById("departmentName").defaultValue
				&& item == document.getElementById("item").defaultValue
				&& postCode == document.getElementById("postCode").defaultValue
				&& address == document.getElementById("address").defaultValue
				&& mailAdd == document.getElementById("mailAdd").defaultValue
				&& telNo == document.getElementById("telNo").defaultValue
				&& faxNo == document.getElementById("faxNo").defaultValue
				&& pwd1 == document.getElementById("loginPwdFirst").defaultValue
				&& pwd2 == document.getElementById("loginPwdTwoSecond").defaultValue
				&&userAdminFlag==userAdminFlagDefault
				&&note == document.getElementById("note").defaultValue) {
			window.location.href = '/breg/topmenu/topMenu';
		}
		//編集する時
		else {
			var msg = $('.msg').html();							
			sessionStorage.setItem("confirmMessage", msg);
		}
	};

	// 保存ボタンを押下する
	$('.save').click(function() {
		var msg = $('.msg1').html();						
		layer.confirm( msg, {
			icon : 3,
			title : '',
			closeBtn : 0,
			btn : [ 'はい', 'いいえ' ]
		}, saveaction);
	});
	var saveaction = function(index) {

		// 入力データーの定義
		var pwd1 = $('#loginPwdFirst').val();
		var pwd2 = $('#loginPwdTwoSecond').val();
		var userName = $('#userName').val();
		var userKana = $('#userKana').val();
		var departmentName = $('#departmentName').val();
		var item = $('#item').val();
		var postCode = $('#postCode').val();
		var address = $('#address').val();
		var mailAdd = $('#mailAdd').val();
		var telNo = $('#telNo').val();
		var faxNo = $('#faxNo').val();
		var userAdminFlag = $('#userAdminFlag').val();
		var note = $('#note').val();
		var pswdCheck = pwd2 == pwd1;
		var userAdminFlagCheck = userAdminFlag!=2;
		var passwordChange = true;
		//保存内容がありません時
		if (userName == document.getElementById("userName").defaultValue
				&& userKana == document.getElementById("userKana").defaultValue
				&& departmentName == document.getElementById("departmentName").defaultValue
				&& item == document.getElementById("item").defaultValue
				&& postCode == document.getElementById("postCode").defaultValue
				&& address == document.getElementById("address").defaultValue
				&& mailAdd == document.getElementById("mailAdd").defaultValue
				&& telNo == document.getElementById("telNo").defaultValue
				&& faxNo == document.getElementById("faxNo").defaultValue
				&& pwd1 == "●●●●●●●●"
				&& pwd2 == "●●●●●●●●"
				&& userAdminFlag == userAdminFlagDefault
				&& note == document.getElementById("note").defaultValue) {
			passwordChange = false;
			var trans = function(index) {
				layer.close(index);
				window.location.href = baseurl + "/usersetting/usersetting";
			}
			var msg = $('.msg2').html();
			layer.alert( msg, {
				icon : 0,
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			},trans);
			return false;
		}
		//パスワードを変化する時
		else if(pwd1 != "●●●●●●●●" || pwd2 != "●●●●●●●●"){
					passwordChange = true;
					var accessURL = baseurl + '/usersetting/save';
					// 入力データーを取得。
					var form = {
						isPasswordSame : pswdCheck.toString(),
						isUserAdminFlagNull : userAdminFlagCheck.toString(),
						loginId : $('#loginId').val(),
						userName : $('#userName').val(),
						userKana : $('#userKana').val(),
						departmentName : $('#departmentName').val(),
						item : $('#item').val(),
						postCode : $('#postCode').val(),
						address : $('#address').val(),
						mailAdd : $('#mailAdd').val(),
						telNo : $('#telNo').val(),
						faxNo : $('#faxNo').val(),
						loginPwd : $('#loginPwdFirst').val(),
						loginPwdTwo : $('#loginPwdTwoSecond').val(),
						loginPwdFirst: $('#loginPwdFirst').val(),
						loginPwdTwoSecond: $('#loginPwdTwoSecond').val(),
						userAdminFlag : $('#userAdminFlag').val(),
						note : $('#note').val(),
						passwordChange:true
					}
					var trans = function(index) {
						layer.close(index);
						window.location.href = baseurl + "/usersetting/usersetting";
					}
					$.blAjax({
						url : accessURL,
						data : JSON.stringify(form),
						type : 'POST',
						success : function(data) {
							var msg = $('.msg3').html();						
							layer.alert( msg, {
								icon : 1,
								title : '',
								closeBtn : 0,
								btn : [ 'はい' ]
							},trans);
						},
						error : function(data, httpStatus, errorHandler) {
							errorHandler(data, httpStatus);
						}
					})
		}
		//パスワードを変化しなくて、他の情報を変化する時
		else if(pwd1 == "●●●●●●●●"
				&& pwd2 == "●●●●●●●●" &&
				(userName != document.getElementById("userName").defaultValue
				|| userKana != document.getElementById("userKana").defaultValue
				|| departmentName != document.getElementById("departmentName").defaultValue
				|| item != document.getElementById("item").defaultValue
				|| postCode != document.getElementById("postCode").defaultValue
				|| address != document.getElementById("address").defaultValue
				|| mailAdd != document.getElementById("mailAdd").defaultValue
				|| telNo != document.getElementById("telNo").defaultValue
				|| faxNo != document.getElementById("faxNo").defaultValue
				|| userAdminFlag != userAdminFlagDefault
				|| note != document.getElementById("note").defaultValue)
				){
				passwordChange = false;
				var accessURL = baseurl + '/usersetting/save';
				// 入力データーを取得。
				var form = {
					isPasswordSame : pswdCheck.toString(),
					isUserAdminFlagNull : userAdminFlagCheck.toString(),
					loginId : $('#loginId').val(),
					userName : $('#userName').val(),
					userKana : $('#userKana').val(),
					departmentName : $('#departmentName').val(),
					item : $('#item').val(),
					postCode : $('#postCode').val(),
					address : $('#address').val(),
					mailAdd : $('#mailAdd').val(),
					telNo : $('#telNo').val(),
					faxNo : $('#faxNo').val(),
					userAdminFlag : $('#userAdminFlag').val(),
					note : $('#note').val(),
					passwordChange:false
				}
				var trans = function(index) {
					layer.close(index);
					window.location.href = baseurl + "/usersetting/usersetting";
				}
				$.blAjax({
					url : accessURL,
					data : JSON.stringify(form),
					type : 'POST',
					success : function(data) {
						var msg = $('.msg3').html();						
						layer.alert( msg, {
							icon : 1,
							title : '',
							closeBtn : 0,
							btn : [ 'はい' ]
						},trans);
					},
					error : function(data, httpStatus, errorHandler) {
						errorHandler(data, httpStatus);
					}
				})
		}
		layer.close(index);
	}
});
