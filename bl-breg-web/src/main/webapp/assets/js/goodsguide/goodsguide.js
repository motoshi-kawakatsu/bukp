$(function() {
	'use strict';
	try {
		// ログインボタンを押下する。
		$('.search').click(
			function() {
				try {
					var accessURL = baseurl + '/goodsguide/goodsGuide';
					var form = {
						primePartsNoWithH : $('#code').val(),
						};
					$('.list-group-item').detach();
					$.blAjax({
						modalStyle: 'none', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示	
						isTranFlag: true,// true:他画面へ遷移、false:他画面へ遷移しない
						url : accessURL,
						data: JSON.stringify(form),
						type : 'POST',
						success : function(data) {
							var temp = data.goodsGuideDtoList;
							if(temp[0].message == null){
								for(var i = 0; i < temp.length; i++){
									$('.select-list').append(
										'<a class=\'list-group-item\'>'+
										'<li class=\'list-item\'><div class=\'item-label\'>' +
			                            '<div class=\'col-md-4 bl-nowithh text-center\'>' + temp[i].primePartsNoWithH + '</div>' +
			                            '<div class=\'col-md-4 bl-kananm text-center\'>' + temp[i].primePartsKanaNm + '</div>' +
			                            '<div class=\'col-md-4 bl-name text-center\'>' + temp[i].primePartsName + '</div>' +
			                            '</div></li></a>'
			                              );
							     	}} else {
							     		layer.alert(temp[0].message,
				                    			{title : '',
											     closeBtn : 0,
											     btn : ['確定']}
				                    	);
							     	}
							},
							error : function(data, httpStatus, errorHandler) {
								errorHandler(data, httpStatus);
							}
						});
					} catch (e) {
						sendClientErrorLog(e);
					}
				});
	} catch (e) {
		sendClientErrorLog(e);
	}
})
