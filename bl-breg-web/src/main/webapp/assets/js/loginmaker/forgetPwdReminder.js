/**
 * パスワードお忘れ
 */
$(function() {
	'use strict';

	try {
		// 送信ボタンをクリックする
		$("#sendMsg").click(function() {
			try {
				var form = {
					// メーカーコード
					makerCd : $('#makerCd').val(),
					// ログインID
					loginId : $('#loginId').val()
				}
				var accessURL = baseurl + "/loginmaker/forgetPwd";

				$.blAjax({
					allowModal : true,// true:二重送信制御が必要、false:二重送信制御が必要しない
					modalStyle : 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
					isTranFlag : true,// true:他画面へ遷移、false:他画面へ遷移しない
					url : accessURL,
					data : JSON.stringify(form),
					type : 'POST',
					success : function(data) {
						location.href = baseurl + "/loginmaker/forgetPwdEnd";
					},
					error : function(data, httpStatus, errorHandler) {
						errorHandler(data, httpStatus);
					}
				});
			} catch (e) {
				sendClientErrorLog(e);
			}
		});

		// 戻るボタンをクリックする
		$("#goback").click(function() {
			try {
				var accessURL = baseurl + "/loginmaker/login";
				$.directTran({
					allowModal : true,// true:二重送信制御が必要、false:二重送信制御が必要しない
					modalStyle : 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
					isTranFlag : true,// true:他画面へ遷移、false:他画面へ遷移しない
					tranURL : accessURL
				})
			} catch (e) {
				sendClientErrorLog(e);
			}
		});

		$('#makerCd').focus();
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
});