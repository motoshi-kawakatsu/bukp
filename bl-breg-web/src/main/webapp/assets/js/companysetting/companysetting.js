$(function() {
	'use strict';
	var CompanySetting = function() {
	};
	try {
		CompanySetting.prototype = {
			init : function() {
				var goodsImportType = $("[name='good_select_type']");
				for (var i = 0; i < goodsImportType.length; i++) {
					if (goodsImportType[i].value != null
							&& goodsImportType[i].value != '') {
						goodsImportType[i].checked = true;
						break;
					}
				}
				var setImportType = $("[name='set_select_type']");
				for (var i = 0; i < setImportType.length; i++) {
					if (setImportType[i].value != null
							&& setImportType[i].value != '') {
						setImportType[i].checked = true;
						break;
					}
				}
				var unionImportType = $("[name='union_select_type']");
				for (var i = 0; i < unionImportType.length; i++) {
					if (unionImportType[i].value != null
							&& unionImportType[i].value != '') {
						unionImportType[i].checked = true;
						break;
					}
				}

				$('#myTab li').first().addClass('active');
				$('#comInfo').addClass('active');

				$('#myTab li').first().click(function() {
					$('#comInfo').hidden = false;
					$('#comInfo').refresh();
				});

				$('#myTab li').last().click(function() {
					$('#setInfo').hidden = true;
				});
			},
		};
		
		// TextBoxのonChangeイベント
		$('.form-control').change(textAreaChangeAction);
		
		function textAreaChangeAction() {
			var accessUrl = baseurl + "/employee/back";
			var goodsTypeValue = getGoodsType();
			var setTypeValue = getSetType();
			var joinTypeVaue = getJoinType();
			var paramData = {
				makerCd : $("[name='MakerCodeRF']").val(),
				makerCdName : $("[name='MakerNameRF']").val(),
				makerCdKana : $("[name='MakerNameHalfRF']").val(),
				companyName : $("[name='ComRF']").val(),
				companyKana : $("[name='ComKanaRF']").val(),
				postNo : $("[name='MailCodeRF']").val(),
				address : $("[name='AddRF']").val(),
				tel : $("[name='TelNoRF']").val(),
				fax : $("[name='FaxNoRF']").val(),
				remark : $("[name='RemarkRF']").val(),
				goodsRows : $("[name='GoodsRF']").val(),
				setRows : $("[name='SetRF']").val(),
				joinRows : $("[name='JoinRF']").val(),
				applyResumeRows : $("[name='ApplyResumeRF']").val(),
				applyDetailRows : $("[name='ApplyDetailRF']").val(),
				goodsImportType : goodsTypeValue,
				setImportType : setTypeValue,
				joinImportType : joinTypeVaue
			}
			$.blAjax({
				url : accessUrl,
				data : JSON.stringify(paramData),
				type : 'POST',
				success : function(data) {
					if (data.pageUpdateFlag) {
						sessionStorage.setItem("confirmMessage",data.confirmMessage == null ? "" : data.confirmMessage);
					} else {
						sessionStorage.setItem("confirmMessage", null);
					}
				},
				error : function(data, httpStatus, errorHandler) {
					errorHandler(data, httpStatus);
				}
			});
		}
		
		// 戻るボタン
		$(".back").click(backAction);

		function backAction() {
			var accessUrl = baseurl + "/employee/back";
			var goodsTypeValue = getGoodsType();
			var setTypeValue = getSetType();
			var joinTypeVaue = getJoinType();
			var paramData = {
				makerCd : $("[name='MakerCodeRF']").val(),
				makerCdName : $("[name='MakerNameRF']").val(),
				makerCdKana : $("[name='MakerNameHalfRF']").val(),
				companyName : $("[name='ComRF']").val(),
				companyKana : $("[name='ComKanaRF']").val(),
				postNo : $("[name='MailCodeRF']").val(),
				address : $("[name='AddRF']").val(),
				tel : $("[name='TelNoRF']").val(),
				fax : $("[name='FaxNoRF']").val(),
				remark : $("[name='RemarkRF']").val(),
				goodsRows : $("[name='GoodsRF']").val(),
				setRows : $("[name='SetRF']").val(),
				joinRows : $("[name='JoinRF']").val(),
				applyResumeRows : $("[name='ApplyResumeRF']").val(),
				applyDetailRows : $("[name='ApplyDetailRF']").val(),
				goodsImportType : goodsTypeValue,
				setImportType : setTypeValue,
				joinImportType : joinTypeVaue
			}
			$.blAjax({
				url : accessUrl,
				data : JSON.stringify(paramData),
				type : 'POST',
				success : function(data) {
					var relateUrl = data.topMenuKey;
					var toptrans = function(index) {
						layer.close(index);
						sessionStorage.setItem("confirmMessage", null);
						window.location.href = baseurl + relateUrl;
					}
					if (data.pageUpdateFlag) {
						var message = data.confirmMessage;
						layer.confirm(message == null ? "" : message, {
							icon : 3,
							title : '',
							closeBtn : 0,
							btn : [ 'はい', 'いいえ' ]
						}, toptrans)
					} else {
						sessionStorage.setItem("confirmMessage", null);
						window.location.href = baseurl + relateUrl;
					}
				},
				error : function(data, httpStatus, errorHandler) {
					errorHandler(data, httpStatus);
				}
			});
		}
		
		// 保存ボタン
		$('.save').click(function() {
			var msg = $("[name='confirmMsg']").val();
			layer.confirm(msg, {
				icon : 3,
				title : '',
				closeBtn : 0,
				btn : [ 'はい', 'いいえ' ]
			}, saveaction);
		});
		var saveaction = function(index) {
			var accessUrl = baseurl + "/employee/save";
			var goodsTypeValue = getGoodsType();
			var setTypeValue = getSetType();
			var joinTypeVaue = getJoinType();
			var paramData = {
				makerCd : $("[name='MakerCodeRF']").val(),
				makerCdName : $("[name='MakerNameRF']").val(),
				makerCdKana : $("[name='MakerNameHalfRF']").val(),
				companyName : $("[name='ComRF']").val(),
				companyKana : $("[name='ComKanaRF']").val(),
				postNo : $("[name='MailCodeRF']").val(),
				address : $("[name='AddRF']").val(),
				tel : $("[name='TelNoRF']").val(),
				fax : $("[name='FaxNoRF']").val(),
				remark : $("[name='RemarkRF']").val(),
				goodsRows : $("[name='GoodsRF']").val(),
				setRows : $("[name='SetRF']").val(),
				joinRows : $("[name='JoinRF']").val(),
				applyResumeRows : $("[name='ApplyResumeRF']").val(),
				applyDetailRows : $("[name='ApplyDetailRF']").val(),
				goodsImportType : goodsTypeValue,
				setImportType : setTypeValue,
				joinImportType : joinTypeVaue
			}

			$.blAjax({
				allowModal : true,// true:二重送信制御が必要、false:二重送信制御が必要しない
				modalStyle : 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
				url : accessUrl,
				data : JSON.stringify(paramData),
				type : 'POST',
				success : function(data) {
					$("#doubleCommitPrevent").remove();
					$("#blankModal").remove();
					$(".reveal-modal-bg").remove();
					if (data.updateFlag) {
						sessionStorage.setItem("confirmMessage", null);
						clearErrorMsg();
						var infoMsg = $("[name='informMsg']").val();
						layer.alert(infoMsg,{
							icon : 1,
							title : '',
							closeBtn : 0
						});
					} else {
						var message = data.saveFailMsg;
						clearErrorMsg();
						layer.alert(message, {
							icon : 0,
							title : '',
							closeBtn : 0
						});
					}
				},
				error : function(data, httpStatus, errorHandler) {
					errorHandler(data, httpStatus);
					$("#doubleCommitPrevent").remove();
					$("#blankModal").remove();
					$(".reveal-modal-bg").remove();
				}
			});
			layer.close(index);
		}
		
		var clearErrorMsg = function(){
			var errorMsg = $(".msg_info_display");
			for(var i=0;i<errorMsg.length;i++){
				errorMsg[i].style.display="none";
			}
		};

		// 画面初期化
		var page = new CompanySetting();
		var nav = new BLUI.Nabigeshon();
		nav.getData(10);
		page.init();
		$("#makerName").focus();
		$(".btn-back").click(function() {
			history.go(-1);
		});

		function getGoodsType() {
			var getType = $("[name='good_select_type']");
			for (var i = 0; i < getType.length; i++) {
				if (getType[i].checked) {
					return getType[i].className;
				}
			}
		}

		function getSetType() {
			var setType = $("[name='set_select_type']");
			for (var i = 0; i < setType.length; i++) {
				if (setType[i].checked) {
					return setType[i].className;
				}
			}
		}

		function getJoinType() {
			var joinType = $("[name='union_select_type']");
			for (var i = 0; i < joinType.length; i++) {
				if (joinType[i].checked) {
					return joinType[i].className;
				}
			}
		}

	} catch (e) {
		sendClientErrorLog(e);
	}

});
