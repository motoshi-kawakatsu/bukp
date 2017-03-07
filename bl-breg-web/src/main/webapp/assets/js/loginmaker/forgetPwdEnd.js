/**
 * ログイン送信完了
 */
// timeout時間
var TIMEOUT = 15;
var number;

$(function() {
	'use strict';

	try {
		$("#close").focus();

		countDown();

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

// ページは15秒後に閉じます.
function countDown() {
	number = window.setInterval(
			function() {
				try {
					if (TIMEOUT > 1) {
						TIMEOUT--;
						// カウントダウン秒数を表示
						var timeStr = TIMEOUT <= 9 ? '&nbsp;&nbsp;' + TIMEOUT
								: TIMEOUT;
						$('#time_remain').html(timeStr);
					} else {
						window.clearInterval(number);
						$('#time_remain').html('&nbsp;&nbsp;0');
						var accessURL = baseurl + "/loginmaker/login";
						window.location.replace(accessURL);
					}
				} catch (e) {
					window.clearInterval(number);
					sendClientErrorLog(e);
				}
			}, 1000);
}