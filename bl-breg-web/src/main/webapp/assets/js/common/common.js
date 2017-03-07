/**
 * エラー区分
 */
var NO_ERROR = 0; // エラーなし
var INPUT_ERROR = 1; // 入力検証エラー
var BUSINESS_ERROR = 2; // ビジネスエラー
var SYSTEM_ERROR = 3; // システムエラー (予想外エラー)
var REQUEST_ERROR = 4; // リクエストエラー
var NOTLOGIN_ERROR = 5; // 未ログインエラー
var NOPERMISSION_ERROR = 6; // 権限なしエラー

/**
 * <pre>
 * inputVal取得inputValはemptyの場合、nullを戻す
 * </pre>
 */
$.fn.inputVal = function() {
	var inputVal = this.val()
	if (inputVal == "") {
		return null;
	}
	return inputVal;
}

/**
 * <pre>
 * 画面アジャックスリクエスト処理
 * </pre>
 */
$.blAjax = function(param) {
	param.blAjax = true;
	handleTrans(param);
}

/**
 * <pre>
 * 画面直接リクエスト処理
 * </pre>
 */
$.directTran = function(param) {
	param.blAjax = false;
	handleTrans(param);
}

/**
 * <pre>
 * リクエスト処理
 * </pre>
 */
var handleTrans = function(param) {
	var allowModal;
	var modalId;
	var animation;
	// 送信メッセージ
	var message;
	if (param.allowModal == null) {
		allowModal = false;
	} else {

		if (param.message == null) {
			message = '送信中...';
		} else {
			message = param.message;
		}

		$('<div id="doubleCommitPrevent" class="reveal-modal">'
						+ '<div class="loading-img" ><img src="'
						+ baseurl
						+ '/imgs/loading-2.gif"></div>'
						+ '<div class="loading-text">' + message
						+ '</div>' + '</div>').appendTo("body");
		$('<div id="blankModal" class="reveal-modal"></div>').appendTo(
				"body");
		allowModal = param.allowModal;
		if (param.modalStyle == 'fade') {
			modalId = 'doubleCommitPrevent';
			animation: 'fade';
		} else {
			modalId = 'blankModal';
			animation = 'none';
		}
	}
	
	try {
		if (allowModal) {
			$('#' + modalId).reveal({
				action : 'open',
				animation : animation
			});
		}
		if(param.blAjax == true){
			$.ajax({
				url : param.url,
				type : param.type,
				dataType : 'json',
				data : param.data,
				contentType : 'application/json; charset=utf-8',
				success : function(data, statusText, jqXHR) {
					try {
						var successCallback = param.success;
						var errorCallback = param.error;
		
						if (data.status == NO_ERROR) {
							if (typeof (successCallback) == "function") {
								successCallback(data);
							}
						} else if (data.status == BUSINESS_ERROR) {
							param.isTranFlag = false;
							if (typeof (errorCallback) == "function") {
								if (data.errorInfo.errors[0].code.indexOf('E90') == 0) {
									errorCallback(data, jqXHR.status, handleSystemError);
								} else {
									errorCallback(data, jqXHR.status, handleBussinessError);
								}
							}
						} else if (data.status == INPUT_ERROR) {
							param.isTranFlag = false;
							if (typeof (errorCallback) == "function") {
								errorCallback(data, jqXHR.status, handleInputError);
							}
						} else if (data.status == SYSTEM_ERROR) {
							param.isTranFlag = false;
							if (typeof (errorCallback) == "function") {
								errorCallback(data, jqXHR.status, handleSystemError);
							}
						} else if (data.status == NOTLOGIN_ERROR) {
							handelNoLogin(data);
						}
					} catch (e) {
						sendClientErrorLog(e);
					}finally {
						if (allowModal && !param.isTranFlag) {
							$('#' + modalId).reveal({
								action : 'close',
								animation : animation
							});
						}
					}
				},
				error : function(data) {
					$('#' + modalId).reveal({
						action : 'close',
						animation : animation
					});
					try {
						var errorCallback = param.error;
		
						var errors = [ {
							"code" : "E90001",
							"message" : "システムエラーが発生しました。\\nシステム管理者に問い合わせてください。",
							"messageType" : "E",
							"title" : "エラー"
						} ];
						var errorInfo = {
							"errors" : errors,
							"info" : "",
						};
						var jsonData = {
							"status" : SYSTEM_ERROR,
							"errorInfo" : errorInfo
						};
		
						if (typeof (errorCallback) == "function") {
							if (data.status != 429) {
								errorCallback(jsonData, data.status, handleSystemError);
							}
						}
					} catch (e) {
						sendClientErrorLog(e);
					}
				}
			});
		}else{
			try {
				window.location.href = param.tranURL;
		    }catch (e) {
				sendClientErrorLog(e);
			}finally {
				if (allowModal && !param.isTranFlag) {
					$('#' + modalId).reveal({
					action : 'close',
					animation : animation
					});
				}
		    }
		}
	} catch (e) {
		sendClientErrorLog(e);
	}
}

/**
 * <pre>
 * ビジネスエラーの処理
 * </pre>
 */
var handleBussinessError = function(data, httpStatus) {
	try {
		// 全てメッセージコントロールを隠れ
		$(".msg_info_display").hide();
		$("#messageInfo").removeClass("textareared");
		$("#messageInfo").html("");

		// ビジネスエラーを発生した場合
		if (data.status == BUSINESS_ERROR) {
			$("#messageInfo").addClass("textareared");
			$("#messageInfo").append("<ul id='messageInfoDetails'>")

			// ヘッダICONを設定する
			errorMessageIcon(data.errorInfo.errors[0].messageType);
			// メッセージヘッダ
			var messageTitle = "<span id='pos_errlocate' class='red'>「"
					+ data.errorInfo.errors[0].title + "」</span>";
			// 画面で表示用メッセージを作成
			$("#messageInfoDetails").append(
					"<li>"
							+ messageTitle
							+ data.errorInfo.errors[0].message.replace("\\n",
									"<br>") + "</li>");
			$("#messageInfo").append("</ul>")
			
			// ビジネスがが発生する場合、ページをスクロールして、エラーメッセージは、ウィンドウの中央に表示する。
			var scroll_offset = $("#pos_errlocate").offset();
			var window_height = $(window).height();
			$("body,html").animate({scrollTop:scroll_offset.top - window_height/2},0); 
		}
	} catch (e) {
		sendClientErrorLog(e);
	}
}

/**
 * <pre>
 * 入力検証エラーの処理
 * </pre>
 */
var handleInputError = function(data, httpStatus) {
	try {
		// 全てメッセージコントロールを隠れ
		$(".msg_info_display").hide();
		$("#messageInfo").removeClass("textareared");
		$("#messageInfo").html("");

		if (data.status == INPUT_ERROR) {
			// エラーアイコン
			var errorSmallIocn = baseurl + "/imgs/icon_1_small.png";
			// ウォーニングアイコン
			var warnSmallIocn = baseurl + "/imgs/icon_1_small.png";
			// インフォアイコン
			var infoSmallIcon = baseurl + "/imgs/icon_2_small.png";

			// 入力検証エラー内容
			var msgDetails = data.errorInfo.errors
			// メッセージヘッダ
			var messageHeader = "";
			// エラー発生対象
			var iputId = "";
			// メッセージアイコン
			var messageIocn = "";
			
			for (var i = 0; i < msgDetails.length; i++) {
				// メッセージヘッダ設定
				messageHeader = "<span class='red'>「" + msgDetails[i].title
						+ "」</span>";
				// メッセージアイコン設定
				if (msgDetails[i].messageType == "E") {
					messageIocn = errorSmallIocn;
				} else if (msgDetails[i].messageType == "W") {
					messageIocn = warnSmallIocn;
				} else if (msgDetails[i].messageType == "I") {
					messageIocn = infoSmallIcon;
				}
				// 画面で表示用メッセージを作成
				iputId = "#" + msgDetails[i].field;
				$(iputId).siblings("img.msg_info_display").attr("src", messageIocn);
				$(iputId).siblings("img.msg_info_display").after(
						"<div class='textareared msg_info_display' style='margin-top:10px'>"
								+ "<ul style='color:#000'><li>" + messageHeader
								+ msgDetails[i].message.replace("\\n","<br>") + "</li></ul>" + "</div>");
				// メッセージを表示する
				$(iputId).siblings("img.msg_info_display").show();
			}
			
			if(msgDetails.length > 0){
				var locateObj = msgDetails[0].field;
				// 初めのエラー発生対象にフォーカスをセット
				// フォーカスセット用のカスタマイズプロパティによって、エラー発生対象が見つかる場合、フォーカスをセット
				if($("input[data-error-focus="+locateObj+"]").length > 0){
					$("input[data-error-focus="+locateObj+"]").focus();
				}
				// 以外、IDによって、フォーカスをセット
				else{
					$("#" + locateObj).focus().select();
				}
			}
		}
	} catch (e) {
		sendClientErrorLog(e);
	}
}

/**
 * <pre>
 * ICONを設定する
 * </pre>
 */
var errorMessageIcon = function(type) {
	// エラーアイコン
	var errorIcon = "<li class='icon'><img src='" + baseurl
			+ "/imgs/icon_1_normal.png' class='rightspace4'></li>";
	// ウォーニングアイコン
	var warnIcon = "<li class='icon'><img src='" + baseurl
			+ "/imgs/icon_1_normal.png' class='rightspace4'></li>";
	// インフォアイコン
	var infoIcon = "<li class='icon'><img src='" + baseurl
			+ "/imgs/icon_2_normal.png' class='rightspace4'></li>";
	// メッセージアイコン設定
	if (type == "E") {
		$("#messageInfoDetails").append(errorIcon);
	} else if (type == "W") {
		$("#messageInfoDetails").append(warnIcon);
	} else if (type == "I") {
		$("#messageInfoDetails").append(infoIcon);
	}
}

/**
 * <pre>
 * 未ログイン処理
 * </pre>
 */
var handelNoLogin = function(data) {
	location.href = data.loginurl + "?return_url=" + encodeURIComponent(document.location.href);
}

/**
 * <pre>
 * システムエラーエラー処理.
 * </pre>
 */
var handleSystemError = function(data, httpStatus) {
	// エラーページを描く
	var errorMsg = data.errorInfo.errors[0].message.replace("\\n","<br>");
	$("#header2").html("");
	$("#webwrapper").html("");
	$("#webwrapper").append('<section>'
	                     + '<div style="text-align: center;">'
	                     + '<img src="' + baseurl + '/imgs/icon_3.png" style="margin: 90px 0 15px 0;">'
	                     + '<p>'
	                     + errorMsg
	                     + '</p>'
	                     + '<button id="errPageGoBack" 　 class="btn bl-btn-panel bl-btn-4">戻る</button>'
	                     + '</div>'	
	                     + '</section>');

	// footerの位置を調整するために、window.resizeイベントをトリガーする
	fireEvent(window, 'resize');
	window.scrollTo(0, 0);
}

/**
 * <pre>
 * イベントをトリガーする処理.
 * </pre>
 */
function fireEvent(obj, eventName){
	var event;
	if(document.createEvent){
		event = document.createEvent("HTMLEvents"); // for chrome and firefox
		event.initEvent(eventName, true, true);
	}else{
		event = document.createEventObject(); // for IE
		event.eventType = eventName;
	}
	if(document.createEvent){
		obj.dispatchEvent(event); // for chrome and firefox
	}else{
		obj.fireEvent("on" + event.eventType, event); // for IE
	}
}

/**
 * <pre>
 * エラーページで、戻るボタンのクリックイベント。
 * </pre>
 */
$(function() {
	try {
		$("#webwrapper").delegate("#errPageGoBack","click",function(){
			try {
				var accessURL = baseurl + "/system_error";
				$.blAjax({
					allowModal: true,
					modalStyle: 'fade', //fade or none	
					isTranFlag: true,
					url : accessURL,
					type : "GET",
					success : function(data) {
						location.href = data.returnUrl;
					},
					// エラー発生の場合
					error : function(data, httpStatus, errorHandler) {
						errorHandler(data, httpStatus);
					}
				});
			} catch (e) {
				sendClientErrorLog(e);
			}
		  });
		
		$('#errPageGoBack').focus();
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

/**
 * <pre>
 * クライアントエラーログ書込み
 * </pre>
 */
function sendClientErrorLog(e) {
	var clientErrorInfo = {
		clientErrorTime : getCurrentDate("yyyy-MM-dd HH:mm:ss.SSS"),
		domain : document.domain,
		requestPath : location.pathname,
		referrer : document.referrer,
		errorCode : e.code,
		errorMessage : e.message,
		errorName : e.name,
		errorFileName : e.fileName,
		errorLineNumber : e.lineNumber,
		errorStack : e.stack
	}
	var clientErrorInfoJSON = JSON.stringify(clientErrorInfo);
	$.ajax({
		url : baseurl + "/clientErrorInfo",
		type : 'POST',
		dataType : 'json',
		data : clientErrorInfoJSON,
		contentType : 'application/json; charset=utf-8',
		error : function(data) {
		}
	});

}

/**
 * <pre>
 * DateからStringへ転換する。
 * </pre>
 */
function getCurrentDate(format) {
	return new Date().Format(format);
}

/**
 * <pre>
 * 対Dateの拡張、DateからStringへ転換する。
 * </pre>
 */
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"H+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"SSS" : this.getMilliseconds()
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			if (!new RegExp("(" + k + ")").test("SSS")) {
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
			} else {
				fmt = fmt.replace(RegExp.$1, o[k]);
			}

		}
	}
	return fmt;
}

/**
 * <pre>
 * ブラウザバック制御
 * </pre>
 */
$.ProhibitBack = function(param) {
	if (param.prohibitBack) {
		if (window.history && window.history.pushState) {
			$(window).on('popstate', function() {
				window.history.pushState('forward', null, null);
				window.history.forward(1);
			});
		}
		window.history.pushState('forward', null, null);
		window.history.forward(1);
	}
}

$.ScrollTop = function(param) {
	if (param.scrollTop) {
		var body = 'body';
		var userAgent = window.navigator.userAgent.toLowerCase();
		if (userAgent.indexOf('msie') > -1 || userAgent.indexOf('trident') > -1
				|| userAgent.indexOf("firefox") > -1) { /* IE6.7.8.9.10.11 */
			body = 'html';
		}
		$(body).animate({
			scrollTop : 0
		}, 100, 'swing');
	}
}

/**
 * <pre>
 * 二重送信制御
 * </pre>
 */
$.fn.reveal = function(options) {
	// プロパティー設定
	var defaults = {
		animation : 'fade',
		animationspeed : 100,
		message : '',
		closeonbackgroundclick : false,
		dismissmodalclass : 'close-reveal-modal'
	};
	var options = $.extend({}, defaults, options);
	return this
			.each(function() {

				// モード対象を新規
				var modal = $(this), topMeasure = parseInt(modal.css('top')), topOffset = modal
						.height()
						+ topMeasure, locked = false, modalBG = $('.reveal-modal-bg');

				// モード背景を新規
				if (modalBG.length == 0) {
					if (options.animation == "fade") {
						modalBG = $('<div class="reveal-modal-bg" />')
								.insertAfter(modal);
					} else {
						modalBG = $('<div class="reveal-modal-blank-bg" />')
								.insertAfter(modal);
					}
				}

				// モードの表示
				modal.bind('reveal:open', function() {
					var clientWidth = $(window).width();
					var clientHeight = $(window).height();
					var scrolltop = $(document).scrollTop();
					var objLeft = (clientWidth-modal.width()) / 2 
					var objHeight = (clientHeight-modal.height()) / 2  + scrolltop;
					modalBG.unbind('click.modalEvent');
					if (!locked) {
						lockModal();
						if (options.animation == "fade") {
							modal.css({
								'opacity' : 0,
								'visibility' : 'visible',
								top:objHeight + 'px',
								left:objLeft +'px'
							});
							$('body').css("overflow", "hidden");
							modalBG.fadeIn(options.animationspeed / 2);
							modal.animate({
								"opacity" : 1
							}, options.animationspeed, unlockModal());
						}
						if (options.animation == "none") {
							modal.css({
								'visibility' : 'visible',
								'top' : $(document).scrollTop() + topMeasure,
								'opacity' : 0
							});
							modalBG.css({
								"display" : "block"
							});
							$('body').css("overflow", "hidden");
							unlockModal()
						}
					}
					modal.unbind('reveal:open');
				});

				// モードの閉じる
				modal.bind('reveal:close', function() {
					if (!locked) {
						lockModal();
						if (options.animation == "fade") {
							modalBG.delay(options.animationspeed).fadeOut(
									options.animationspeed);
							modal.animate({
								"opacity" : 0
							}, options.animationspeed, function() {
								modal.css({
									'opacity' : 1,
									'visibility' : 'hidden',
									'top' : topMeasure
								});
								unlockModal();
							});
							$('body').css("overflow", "scroll");
						}
						if (options.animation == "none") {
							modal.css({
								'visibility' : 'hidden',
								'top' : topMeasure
							});
							modalBG.css({
								'display' : 'none'
							});
							$('body').css("overflow", "scroll");
						}
					}
					modal.unbind('reveal:close');
				});

				// モードのイベント追加
				var action = options.action;
				// Open Modal Immediately
				if (action == 'open') {
					modal.trigger('reveal:open');
				}

				 if (action == 'close') {
				 modal.trigger('reveal:close');
				 }
				if (options.closeonbackgroundclick) {
					modalBG.css({
						"cursor" : "pointer"
					});

					modalBG.bind('click.modalEvent', function() {
						modal.trigger('reveal:close')
					});

					$('body').keyup(function(e) {
						if (e.which === 27) {
							modal.trigger('reveal:close');
						} // ESC
					});
				}

				// syncロック
				function unlockModal() {
					locked = false;
				}
				function lockModal() {
					locked = true;
				}

			});// each call
}// orbit plugin call

function getMessageInfo(messageStr, paramArr) {
	var messageReturn = messageStr.replace("\\n","<br>");
	if (paramArr == null) {
		return messageReturn;
	}
	if (paramArr.length >= 1) {
		messageReturn = messageReturn.replace( "$1", paramArr[0]);
	}
	if (paramArr.length >= 2) {
		messageReturn = messageReturn.replace( "$2", paramArr[1]);
	}
	if (paramArr.length >= 3) {
		messageReturn = messageReturn.replace( "$3", paramArr[2]);
	}
	return messageReturn;
}
