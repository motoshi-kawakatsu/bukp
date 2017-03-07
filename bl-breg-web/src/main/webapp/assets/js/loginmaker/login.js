/**
 * ログイン
 */
$(function() {
	'use strict';

	try {
		// ログインボタンを押下する。
		$('#login').click(function() {
			try {
				var accessURL = baseurl + "/loginmaker/login";
				var form = {
					makerCd : $('#makerCd').val(),
					loginId : $('#loginId').val(),
					password : $('#password').val(),
					cookieSend : $('#checkbox01').is(":checked")
				}
				$.blAjax({
					allowModal : true,// true:二重送信制御が必要、false:二重送信制御が必要しない
					modalStyle : 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
					isTranFlag : true,// true:他画面へ遷移、false:他画面へ遷移しない
					url : accessURL,
					data : JSON.stringify(form),
					type : "POST",
					success : function(data) {
						window.location.href = data.sendurl;
						sessionStorage.setItem("message",JSON.stringify(data.messageMap));
					},
					error : function(data, httpStatus, errorHandler) {
						errorHandler(data, httpStatus);
					}
				});
			} catch (e) {
				sendClientErrorLog(e);
			}
		});

		// パスワードをお忘れ
		$("#forgetPwd").click(function() {
			window.location.href = baseurl + "/loginmaker/forgetPwd";
		});

		$('#makerCd').focus();
		// 画面のTOPへ移動
		window.scrollTo(0, 0);
		// ブラウザバック制御
		$.ProhibitBack({
			// 「true」はブラウザバックが使用不可
			prohibitBack : false
		});

		// 画面スクロール制御
		$.ScrollTop({
			// 「true」画面のTOPへ移動
			scrollTop : true
		});

	} catch (e) {
		sendClientErrorLog(e);
	}
})