$(function() {
	'use strict';
	try {
		// ログインボタンを押下する。
		$('.search').click(
			function() {
				try {
					var accessURL = baseurl + '/classifycodeguide/classifyCodeGuide';
					var form = {
						goodsRateGrpCode : $('#code').val(),
						goodsRateGrpName : $('#name').val(),
						};
					$('.list-group-item').detach();
					$.blAjax({
						url : accessURL,
						data: JSON.stringify(form),
						type : 'POST',
						success : function(data) {
							var temp = data.classifyCodeGuideDtoList;
						    if(temp[0].message == null){
							  for(var i = 0; i < temp.length; i++){
		                          $('.select-list').append(
		                              '<a class=\'list-group-item\'>'+
		                              '<li class=\'list-item\'><div class=\'item-label\'>' +
		                              '<div class=\'col-md-6 bl-code text-center\'>' + 
		                              temp[i].goodsRateGrpCode + '</div>' +
		                              '<div class=\'col-md-6 bl-name text-center\'>' + 
		                              temp[i].goodsRateGrpName + '</div>' +
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
