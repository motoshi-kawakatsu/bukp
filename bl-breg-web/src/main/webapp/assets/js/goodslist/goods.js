/**
 * @file 商品一覧
 */
$(function() {
	'use strict';
	var flexGrid = new wijmo.grid.FlexGrid('#grid');

	var mode = sessionStorage.getItem("goodsList") == null ? BLENUM.ModeEnum.New
			: sessionStorage.getItem("goodsList");
	var title = $('#title');
	
	var requestFlag = false;
	switch (parseInt(mode)) {
	// 検索入力モード
	case BLENUM.ModeEnum.New:
		$("#certain").hide();
		$("#save")[0].innerHTML = "保存";
		break;
	// 商品選択モード
	case BLENUM.ModeEnum.Update:
		title.html(title.html().replace(/商品一覧/ig, '商品一覧（選択）'));
		$(".btn-add").hide();
		$(".btn-del").hide();
		$(".btn-copy").hide();
		$(".btn-paste").hide();
		$(".replaceGuide").hide();
		$(".btn_check_list").hide();
		$(".outputGuide").hide();
		$(".space").hide();
		$("#save").hide();
		$("#add").hide();
		break;
	// 参照モード
	case BLENUM.ModeEnum.Readonly:
		$(".btn-add").hide();
		$(".btn-del").hide();
		$(".btn-copy").hide();
		$(".btn-paste").hide();
		$(".replaceGuide").hide();
		$(".btn_check_list").hide();
		$(".space").hide();
		$("#save").hide();
		$("#certain").hide();
		$("#cancle").hide();
		$("#add").hide();
		flexGrid.isReadOnly = true;
		break;
	// エラー修正モード
	case BLENUM.ModeEnum.Error:
		$(".btn-add").hide();
		$(".btn-del").hide();
		$(".btn-copy").hide();
		$(".btn-paste").hide();
		$("#certain").hide();
		$("#save")[0].innerHTML = "確定";
		$("#add").hide();
		title.html(title.html().replace(/商品一覧/ig, '商品一覧（エラー修正）'));
		break;
	default:
		alert("error");
		break;
	}

	var ItemCtlgPage = function() {
	}

	ItemCtlgPage.prototype = {
		// / <summary>
		// / 初期処理
		// / </summary>
		init : function() {

			$('.btn-back').on('click', $.proxy(this.back, this));
			$('.classifyCdGuide').on('click', '', {},
					$.proxy(this.showClassifyGuide, this));
			$('.replaceGuide')
					.on('click', $.proxy(this.showReplaceGuide, this));
			$('#classifyCd').focus($.proxy(this.focus, this));
			$('#classifyCd').blur($.proxy(this.blur, this));
			$('#goodsName').on('blur',function() {
					var str = document.getElementById('goodsName').value;
					if (!checkHankaku(str)) {
						var paramArr = ['品名（半角）'];
						layer.alert(getMessageInfo(message.E00004, paramArr), {
							title : '',
							closeBtn : 0,
							btn : [ 'はい' ]
						});
					} else {
						document.getElementById('goodsName').value = zenkaku2Hankaku(str);
					}
				});
		},

		focus : function(event) {
			var value = event.currentTarget.value;
			value = value.split("：")[0];
			event.currentTarget.value = value;
		},

		blur : function(event) {
			var accessURL = baseurl + "/changecommon/getCode";
			var form = {
				code : event.currentTarget.value,
				guideType : "0"
			};
			$.blAjax({
				url : accessURL,
				type : "POST",
				data : JSON.stringify(form),
				error : function(data, httpStatus, errorHandler) {
					errorHandler(data, httpStatus);
				},
				success : function(data) {
					if (data.message != undefined) {
						layer.alert(data.message, {
							title : '',
							closeBtn : 0,
							btn : [ '確定' ],
							end : function() {
								event.currentTarget.value = "";
							}
						});
					}
					event.currentTarget.value = data.codeValue;
				},
			});
		},
		// / <summary>
		// / url処理
		// / </summary>
		urlJump : function(event) {
			location.href = event.data.url;
		},
		// / <summary>
		// / back処理
		// / </summary>
		back : function() {
			var back = function(index) {
				switch (parseInt(mode)) {
				// 検索入力モード
				case BLENUM.ModeEnum.New:
					window.location.href = "../topmenu/topMenu";
					break;
				// 結合選択モード
				case BLENUM.ModeEnum.Update:
					var accessURL = baseurl + "/goods/back";
					$.blAjax({
						url : accessURL,
						type : 'POST',
						dataType : 'Json',
						contentType : 'application/Json',
						success : function(data) {
							window.location.href = "../applycommon/applyCommon";
						},
						error : function(data, httpStatus, errorHandler) {
						}
					});			
					break;
				// 参照モード
				case BLENUM.ModeEnum.Readonly:
					window.location.href = "../readresult/readresult";
					break;
				// エラー修正モード
				case BLENUM.ModeEnum.Error:
					window.location.href = "../importresult/importresult";
					break;
				default:
					break;
				}
				layer.close(index);
			};
			if (isGridOut()) {
				layer.confirm(getMessageInfo(message.Q00001), {
					icon : 3,
					title : '',
					closeBtn : 0,
					btn : [ 'はい', 'いいえ' ]
				}, back)
			} else {
				back();
			}
		},
		// / <summary>
		// / popup guide
		// / </summary>
		showClassifyGuide : function(event) {
			try {
				var accessURL = baseurl + '/goods/goodsMGroup';
				var actionType = 'POST';
				var form = {
					goodsMGroup : $('[name="classifyCode"]').val()
				};
				$.blAjax({
					url : accessURL,
					type : actionType,
					data : JSON.stringify(form),
					success : function(data) {
						var guide = new BLUI.ClassifyCdGuide();
						guide.show('classifyCode', false, {});

					},
					error : function(data, httpStatus, errorHandler) {
						errorHandler(data, httpStatus);
					}
				});
			} catch (e) {
				sendClientErrorLog(e);
			}
		},
		// / <summary>
		// / popup guide
		// / </summary>
		showReplaceGuide : function() {
			var guide = new BLUI.ShowReplaceGuide();
			guide.show(BLENUM.MenuEnum.Item);
		},

	}

	var page = new ItemCtlgPage();
	var nav = new BLUI.Nabigeshon();
	if (mode == 0) {
		nav.getData(1);
	} else if (mode == 1) {
		nav.getData(25);
	} else if (mode == 2) {
		if(sessionStorage.getItem("importModelMenu")==0){
			nav.getData(24);
		}else{
			nav.getData(38);
		}
	} else if (mode == 3) {
		if(sessionStorage.getItem("importModelMenu")==0){
			nav.getData(23);
		}else{
			nav.getData(37);
		}
	}
	page.init();
	slidePanel();

	$('.btn-new').click(function() {
		var form = {
			applyDateStart : $("#applyDateStart").val(),
			selectCd : $("#selectCd").val(),
			oldCd : $("#oldCd").val(),
			classifyCd : $("#classifyCd").val(),
			priceClass : $("#priceClass").val(),
			blCd : $("#blCd").val(),
			equipment : $("#equipment").val(),
			goodsCd : $("#goodsCd").val(),
			primePartsSpecialNoteRFB : $("#primePartsSpecialNoteRFB").val(),
			primePartsSpecialNoteRFC : $("#primePartsSpecialNoteRFC").val(),
			goodsName : $("#goodsName").val(),
			goodDetail : $("#goodDetail").val(),
			goodDetailCommon : $("#goodDetailCommon").val()
		}
		sessionStorage.setItem("formData", JSON.stringify(form));
		$('.btn-del').removeClass('disabled');
		$('.btn-copy').removeClass('disabled');
		$('.btn-paste').removeClass('disabled');
		$('.replaceGuide').attr("disabled", false);
		$('.btn_detail').attr("disabled", false);

		flexGrid.itemsSource.addNew();
		flexGrid.itemsSource.commitNew();
		var cv = flexGrid.collectionView;
		var count = cv.totalItemCount;
		for (var i = 0; i < count; i++) {
			cv.items[i].no = i + 1;
			if (cv.items[i].hiddenArea == undefined) {
				cv.items[i].hiddenArea = 0;
			}
			if (cv.items[i].applyStep == undefined) {
				cv.items[i].applyStep = "未";
			}
			if (cv.items[i].manageSec == undefined) {
				cv.items[i].manageSec = "追";
			}
			if (cv.items[i].checkSec == undefined) {
				cv.items[i].checkSec = "未";
			}
			if (cv.items[i].blSec == undefined) {
				cv.items[i].blSec = "未";
			}
		}
		var gridlist = [];
		var accessURL = baseurl + "/goodsdetail/item";
		gridlist.push(cv.items[count - 1]);
		var form = {
			goodsGridDtoList : gridlist
		}
		$.blAjax({
			// allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
			// modalStyle: 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
			// isTranFlag: true,// true:他画面へ遷移、false:他画面へ遷移しない
			url : accessURL,
			type : 'POST',
			dataType : 'Json',
			data : JSON.stringify(form),
			contentType : 'application/Json',
			success : function(data) {
				var detailmode = BLENUM.ModeEnum.New;
				new BLUI.goodsdetail().show(detailmode);
				// window.location.href="../goodsdetail/goodsDetail?mode="+BLENUM.ModeEnum.Update;
			},
			error : function(data, httpStatus, errorHandler) {
			}
		});
	});

	$('.outputGuide').click(function() {
		outputGuide();
	});
	function outputGuide() {
		var grid = $(document.getElementById("grid"))[0]["wj-Control"];
		var output = function(index) {
			if (grid.rows.length == 0) {
				layer.alert(getMessageInfo(message.E00009), {
					title : '',
					closeBtn : 0,
					btn : [ 'はい' ]
				});
			} else {
				show();
			}
			layer.close(index);
		};
		if (isGridOut()) {
			layer.confirm(getMessageInfo(message.Q00001), {
				icon : 3,
				title : '',
				closeBtn : 0,
				btn : [ 'はい', 'いいえ' ]
			}, output)
		} else {
			output();
		}
	}
	;

	$(".btn_check_list").click(function() {
		layer.config({
			extend : '../../css/classifyCodeGuide/layerButton.css'
		});
		layer.open({
			type : 2,
			title : false,
			closeBtn : 0,
			skin : 'layer-ext-skin',
			shade : 0.1,
			area : [ '100%', '80%' ],
			content : [ baseurl + "/checkList/checkList" ],
		});

		if (mode == 0) {
			sessionStorage.setItem("checkList", BLENUM.MenuEnum.Item);
		} else if (mode == 3) {
			sessionStorage.setItem("checkList", BLENUM.MenuEnum.goodscorrect);
		}
	});

	$(".apply-date input").datetimepickerJp({
		format : 'YYYY/MM/DD'
	});
	$(".insert-date input").datetimepickerJp({
		format : 'YYYY/MM/DD'
	});
	$(".update-date input").datetimepickerJp({
		format : 'YYYY/MM/DD'
	});

	$('#clear').click(function() {
		$("#selectCd").val("");
		$("#oldCd").val("");
		$("#applyDateStart").val("");
		$("#applyDateEnd").val("");
		$("#classifyCd").val("");
		$("#priceClass").val("");
		$("#insertDateStart").val("");
		$("#insertDateEnd").val("");
		$("#blCd").val("");
		$("#equipment").val("");
		$("#updateDateStart").val("");
		$("#updateDateEnd").val("");
		$("#goodsCd").val("");
		$("#primePartsSpecialNoteRFB").val("");
		$("#processDiv").val("9");
		$("#deleteDiv").val("");
		$("#primePartsSpecialNoteRFC").val("");
		if (mode == 1) {
			$("#applyState").val("0");
		} else if (mode == 0) {
			$("#applyState").val("9");
		}
		$("#errorDiv").val("9");
		$("#goodsName").val("");
		$("#goodDetail").val("");
		$("#goodDetailCommon").val("");
	});
	function slidePanel() {
		$(".init-hide").css('display', 'none');
		var head = $(".panel-heading.head");
		var middle = $(".panel-heading.middle");
		head.addClass("panel-heading-down");
		head.append("<span class='glyphicon glyphicon-chevron-down'></span>");
		head.click(function() {
			var panel = $(this).parent(".slide-panel");
			var button = $('.btn-search');
			$(".init-hide").slideToggle();
			panel.toggleClass("slide-down");
			if (panel.hasClass('slide-down')) {
				button.removeClass('detail-condition').addClass(
						'close-condition').html('閉じる');
			} else {
				button.removeClass('close-condition').addClass(
						'detail-condition').html('詳細条件');
			}
		});

		var panelShow = function() {
			$(this).removeClass('detail-condition').addClass('close-condition')
					.html('閉じる');
			$(".init-hide").slideToggle();
			$(".panel-heading.head").parent(".slide-panel").toggleClass(
					"slide-down");
		};
		var panelHidden = function() {
			$(this).removeClass('close-condition').addClass('detail-condition')
					.html('詳細条件');
			$(".init-hide").slideToggle();
			$(".panel-heading.head").parent(".slide-panel").toggleClass(
					"slide-down");
		}
		$('.group-button').on('click', '.detail-condition', {}, panelShow);
		$('.group-button').on('click', '.close-condition', {}, panelHidden);

	}
	;

	function show() {
		layer.config({
			extend : 'style.css'
		});
		var parentFrame = null;
		layer
				.open({
					type : 2,
					title : false,
					closeBtn : 0,
					skin : 'layer-ext-skin',
					shade : 0.1,
					area : [ '500px', '300px' ],
					sucess : function() {
						parentFrame = $('#child_page')[0].contentWindow.document;
					},

					yes : function(index, layero) {
						var childFrame = layero.find('iframe')[0].contentWindow.document;
						var code = $("input", childFrame);
						var selectCode = showCont(code);

						var params = {};
						params["isInit"] = window.isInit;
						params["fileType"] = selectCode;
						params["mode"] = mode;
						params["importKbn"] = sessionStorage
								.getItem("importModelMenu");

						$.blAjax({
							url : baseurl + '/goods/makeFile',
							data : params,
							type : 'get',
							dataType : 'json',
							contentType : 'application/Json',
							success : function(data) {
								var result = data["data"];
								if (result[0] == "success") {
									$.download(baseurl
											+ '/checkList/downLoadFile',
											'post', result[1], result[2]); 
								} else {
									 alert("データ書き出し失敗！");
								}
							},
							error : function(data, httpStatus, errorHandler) {
							}
						});
						layer.close(index);
					},
					content : [ baseurl + '/checkList/checkListOutPut' ],
					btn : [ '出力', 'キャンセル' ]
				});
	}
	;

	function showCont(select) {
		for (var i = 1; i <= select.length; i++) {
			if (select[i - 1].checked == true) {
				return i;
			}
		}
	}
	;
	var check = function() {
		if(requestFlag)
		{
		    return;	
		}
		else
		{
			requestFlag = true;
		}
		var accessURL = baseurl + "/goods/check";
		var form = {
			mode : mode,
			goodsGridDtoList : flexGrid.itemsSource.items
		}
		$.blAjax({
			// allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
			// modalStyle: 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
			// isTranFlag: true,// true:他画面へ遷移、false:他画面へ遷移しない
			url : accessURL,
			type : 'POST',
			dataType : 'Json',
			data : JSON.stringify(form),
			contentType : 'application/Json',
			success : function(data) {
				collectionView = new wijmo.collections.CollectionView(
						data.gridDataUpdate);
					flexGrid.itemsSource = collectionView;
					flexGrid.refresh;	
					requestFlag = false;
			},
			error : function(data, httpStatus, errorHandler) {
				requestFlag = false;
			}
		});
	};
	var collectionView = null, pagerButtons = null;
	var accessURL = baseurl + "/goods/goods";
	var blStr = "";
	var selectStr = "9999：指定無し";
	var partsStr = "";
	var gridDataUpdate = [];
	var message = [];
	window.pageNo = 1;
	window.isPage = true;
	var controlFlag;
	gridInit();
	function gridInit(index) {
		layer.close(index);
		window.isInit = true;
		var form = {
			mode : mode,
			pageNo : window.pageNo,
			importKbn : sessionStorage.getItem("importModelMenu")
		}
		$
				.blAjax({
					url : accessURL,
					type : 'POST',
					dataType : 'Json',
					data : JSON.stringify(form),
					contentType : 'application/Json',
					success : function(data) {
						controlFlag = data.controlFlag;
						if (!controlFlag) {
							flexGrid.isReadOnly = true;
							$("#save").hide();
							$("#certain").hide();
							$("#cancle").hide();
							$("#add").hide();
							$('.btn-add').attr("disabled", true);
						}
						message = data.messageMap;
						if (mode == 2 || mode == 3) {
							$('#applyState')[0].options.length = 0;
							for ( var key in data.applyConditionMap) {
								$('#applyState')[0].options.add(new Option(
										data.applyConditionMap[key], key));
							}
						} else if (mode == 1) {
							$('#applyState')[0].options.length = 0;
							$('#applyState')[0].options.add(new Option(
									"全データ表示", 9));
							for ( var key in data.applyConditionMap) {
								$('#applyState')[0].options.add(new Option(
										data.applyConditionMap[key], key));
							}
							$('#applyState')[0].value = 0;
						}
						$('#blCd').focus();
						for ( var key in data.blCodeNameMap) {
							$('#blCd')[0].options.add(new Option(
									data.blCodeNameMap[key], key));
							blStr = blStr + "," + data.blCodeNameMap[key];
						}

						for ( var key in data.selectCodeNameMap) {
							$('#selectCd')[0].add(new Option(
									data.selectCodeNameMap[key], key));
							selectStr = selectStr + ","
									+ data.selectCodeNameMap[key];
						}

						for ( var key in data.partsNameMap) {
							$('#oldCd')[0].add(new Option(
									data.partsNameMap[key], key));
							partsStr = partsStr + "," + data.partsNameMap[key];
						}
						if (data.gridData == undefined
								|| data.gridData.length == 0 || !controlFlag) {
							$(".page-info").hide();
							$('.btn-del').addClass('disabled');
							$('.btn-copy').addClass('disabled');
							$('.btn-paste').addClass('disabled');
							$('.replaceGuide').attr("disabled", true);
							$('.btn_detail').attr("disabled", true);
							if(controlFlag){
							var paramArr = new Array();
							paramArr[0] = "商品";
							layer.alert(
									getMessageInfo(message.E00008, paramArr), {
										title : '',
										closeBtn : 0,
										btn : [ 'はい' ]
									});
							}
						} else {
							$('.btn-del').removeClass('disabled');
							$('.btn-copy').removeClass('disabled');
							$('.btn-paste').removeClass('disabled');
							$('.replaceGuide').attr("disabled", false);
							$('.btn_detail').attr("disabled", false);
						}
						gridDataUpdate = [];

						if (data.gridData != undefined
								&& data.gridData.length != 0) {
							$(".page-info").show();
							gridDataUpdate = data.gridData;
							$(".searchNum").text(data.searchNumInit + '/');
							$(".allNum").text(data.allNumInit);
							$('.historyrows').text(data.maxRows);
						}
						if (window.isPage) {
							paging(data.searchNumInit, data.maxRows);
						}

						collectionView = new wijmo.collections.CollectionView(
								gridDataUpdate);
						if (mode == 1 || mode == 3) {
							init(0);
						} else {
							init(1);
						}
					},
					error : function(data, httpStatus, errorHandler) {

						alert("Dataが存在しない")
					}
				});
	}
	;

	$('#searchShohi').click(function() {
		if(!searchBeforeCheck()){
			return;
		}
		if (isGridOut()) {
			layer.confirm(getMessageInfo(message.Q00001), {
				icon : 3,
				title : '',
				closeBtn : 0,
				btn : [ 'はい', 'いいえ' ]
			}, function(index) {
				window.isInit = false;
				window.isPage = true;
				window.pageNo = 1;
				search(index);
			})
		} else {
			window.isInit = false;
			window.isPage = true;
			window.pageNo = 1;
			search();
		}

	});
	$('#cancle').click(function() {
		if (window.isInit) {
			gridInit();
		} else {
			search();
		}
	});
	
	function searchBeforeCheck() {
		// 適用日
		if (!checkDateduration($('#applyDateStart').val(), $('#applyDateEnd').val())) {
			layer.alert("開始適用日が終了適用日より前になるように設定してください。", {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
			return false
		}
		// 作成日
		if (!checkDateduration($('#insertDateStart').val(), $('#insertDateEnd').val())) {
			layer.alert("開始作成日が終了作成日より前になるように設定してください。", {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
			return false
		}
		// 更新日
		if (!checkDateduration($('#updateDateStart').val(), $('#updateDateEnd').val())) {
			layer.alert("開始更新日が終了更新日より前になるように設定してください。", {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
			return false
		}
		return true;
	}

	var save = function(index) {
		layer.close(index);
		var accessURL = baseurl + "/goods/save";
		var form = {
			mode : mode,
			goodsGridDtoList : flexGrid.itemsSource.items
		}
		$.blAjax({
			// allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
			// modalStyle: 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
			// isTranFlag: true,// true:他画面へ遷移、false:他画面へ遷移しない
			url : accessURL,
			type : 'POST',
			dataType : 'Json',
			data : JSON.stringify(form),
			contentType : 'application/Json',
			success : function(data) {
				collectionView = new wijmo.collections.CollectionView(
						data.gridDataUpdate);
				if (!data.isErrorExist) {
					layer.alert(getMessageInfo(message.I00001), {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
					window.pageNo = 1;
					window.isPage = true;
					if (window.isInit) {
						gridInit();
					} else {
						search();
					}
					sessionStorage.setItem("confirmMessage", null);
				} else {
					layer.alert(getMessageInfo(message.E00018), {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
					if (data.gridDataUpdate.length == 0) {
						$('.btn-del').addClass('disabled');
						$('.btn-copy').addClass('disabled');
						$('.btn-paste').addClass('disabled');
						$('.replaceGuide').attr("disabled", true);
						$('.btn_detail').attr("disabled", true);
					} else {
						$('.btn-del').removeClass('disabled');
						$('.btn-copy').removeClass('disabled');
						$('.btn-paste').removeClass('disabled');
						$('.replaceGuide').attr("disabled", false);
						$('.btn_detail').attr("disabled", false);
					}
					if (mode == 1) {
						init(0);
					} else if (mode == 3) {
					    window.location.href = "../importresult/importresult";
					}
				}
			},
			error : function(data, httpStatus, errorHandler) {

			}
		});
	};

	$('#save').click(function() {
		if (!isGridOut()) {
			if(mode == 0){
			layer.alert(getMessageInfo(message.E00013), {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
			}else if(mode == 3){
				layer.alert(getMessageInfo(message.E00014), {
					title : '',
					closeBtn : 0,
					btn : [ 'はい' ]
				});
			}
			return;
		}
		layer.confirm(getMessageInfo(message.Q00002), {
			icon : 3,
			title : '',
			closeBtn : 0,
			btn : [ 'はい', 'いいえ' ]
		}, save);
	});

	$('#certain').click(function() {
		var accessURL = baseurl + "/goods/certain";
		var form = {
			goodsGridDtoList : flexGrid.itemsSource.items
		}
		$.blAjax({
			url : accessURL,
			type : 'POST',
			dataType : 'Json',
			data : JSON.stringify(form),
			contentType : 'application/Json',
			success : function(data) {
				if (data.selectSize == 0) {
					layer.alert(getMessageInfo(message.E00014), {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
					return;
				}
				window.location.href = "../applycommon/applyCommon";
			},
			error : function(data, httpStatus, errorHandler) {

			}
		});
	});
	$(".btn_detail").click(function() {
		var accessURL = baseurl + "/goodsdetail/item";
		var rows = flexGrid.selectedRows;
		if (rows.length == 0) {
			layer.alert(getMessageInfo(message.E00010), {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
			return;
		}
		var gridlist = [];
		for (var i = 0; i < rows.length; i++) {
			gridlist.push(rows[i].dataItem);
		}
		var form = {
			goodsGridDtoList : gridlist
		}
		$.blAjax({
			// allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
			// modalStyle: 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
			// isTranFlag: true,// true:他画面へ遷移、false:他画面へ遷移しない
			url : accessURL,
			type : 'POST',
			dataType : 'Json',
			data : JSON.stringify(form),
			contentType : 'application/Json',
			success : function(data) {
				var detailmode = BLENUM.ModeEnum.Update;
				if (mode == 0) {
					if (gridlist[0].applyStep == "中") {
						detailmode = BLENUM.ModeEnum.Readonly;
					} else {
						detailmode = BLENUM.ModeEnum.Update;
					}
				} else if (mode == 1) {
					detailmode = BLENUM.ModeEnum.Readonly;
				} else if (mode == 2) {
					detailmode = BLENUM.ModeEnum.Readonly;
				} else if (mode == 3) {
					detailmode = BLENUM.ModeEnum.Error;
				}
				new BLUI.goodsdetail().show(detailmode);
				// window.location.href="../goodsdetail/goodsDetail?mode="+BLENUM.ModeEnum.Update;
			},
			error : function(data, httpStatus, errorHandler) {

			}
		});
	});

	$("#order").change(function() {
		if (isGridOut()) {
			layer.confirm(getMessageInfo(message.Q00001), {
				icon : 3,
				title : '',
				closeBtn : 0,
				btn : [ 'はい', 'いいえ' ]
			}, function(index) {
				window.isPage = true;
				if (window.isInit) {
					gridInit();
				} else {
					search(index);
				}
			})
		} else {
			window.isPage = true;
			if (window.isInit) {
				gridInit();
			} else {
				search();
			}
		}
	});
	var gridDataUpdate;

	function paging(searchcount, historyrows) {
		var pageMath = Math.ceil(searchcount / historyrows);
		if (pageMath != 1) {
			if(pageMath==3){
			$('.page-box').show();
			$('.page-box').pagination({
				pageCount : pageMath,
				coping : false,
				count:1,
				keepShowPN : false,
				prevContent : '<前のページ',
				nextContent : '次のページ>',
				callback : pageCallback,
			});}else{
				$('.page-box').show();
				$('.page-box').pagination({
					pageCount : pageMath,
					coping : true,
					count:1,
					keepShowPN : false,
					prevContent : '<前のページ',
					nextContent : '次のページ>',
					callback : pageCallback,
				});
			}
		} else {
			$('.page-box').hide();
		}
		function pageCallback(page_index, jq) {
			if (mode != 1 && isGridOut()) {
				layer.confirm(getMessageInfo(message.Q00001), {
					icon : 3,
					title : '',
					closeBtn : 0,
					btn : [ 'はい', 'いいえ' ]
				}, function(index, layero) {
					var pageMath = Math.ceil(searchcount / historyrows);
					if (pageMath != 1) {
						if(pageMath==3){
						$('.page-box').pagination({
							pageCount : pageMath,
							coping : false,
							count:1,
							keepShowPN : false,
							current : page_index.getCurrent(),
							prevContent : '<前のページ',
							nextContent : '次のページ>',
							callback : pageCallback,
						});}else{
							$('.page-box').pagination({
								pageCount : pageMath,
								coping : true,
								count:1,
								keepShowPN : false,
								current : page_index.getCurrent(),
								prevContent : '<前のページ',
								nextContent : '次のページ>',
								callback : pageCallback,
							});
						}
					} else {
						$('.page-box').hide();
					}
					searchHandler(page_index.getCurrent());
					layer.close(index);
				}, function(index) {
					layer.close(index);
				})
				return false;
			} else {
				searchHandler(page_index.getCurrent());
			}

		}
		;
		// isPage pageOperator mark
		// isPage undefind
		var searchHandler = function(page) {
			if (mode == 1) {
				selectPage();
			}
			window.pageNo = page;
			if (window.isInit) {
				gridInit();
			} else {
				search();
			}
		};
		window.isPage = false;
	}
	;

	function selectPage() {
		var accessURL = baseurl + "/goods/certain";
		var form = {
			goodsGridDtoList : flexGrid.itemsSource.items
		}
		$.blAjax({
			url : accessURL,
			type : 'POST',
			dataType : 'Json',
			data : JSON.stringify(form),
			contentType : 'application/Json',
			success : function(data) {
			},
			error : function(data, httpStatus, errorHandler) {

			}
		});
	}
	;
	function search(index) {
		layer.close(index);
		var accessURL = baseurl + "/goods/search";
		var form = {
			selectCd : $("#selectCd").val(),
			oldCd : $("#oldCd").val(),
			applyDateStart : $("#applyDateStart").val(),
			applyDateEnd : $("#applyDateEnd").val(),
			classifyCd : $("#classifyCd").val(),
			priceClass : $("#priceClass").val(),
			insertDateStart : $("#insertDateStart").val(),
			insertDateEnd : $("#insertDateEnd").val(),
			blCd : $("#blCd").val(),
			equipment : $("#equipment").val(),
			updateDateStart : $("#updateDateStart").val(),
			updateDateEnd : $("#updateDateEnd").val(),
			goodsCd : $("#goodsCd").val(),
			primePartsSpecialNoteRFB : $("#primePartsSpecialNoteRFB").val(),
			processdiv : $("#processDiv").val(),
			deleteDiv : $("#deleteDiv").val(),
			primePartsSpecialNoteRFC : $("#primePartsSpecialNoteRFC").val(),
			applyState : $("#applyState").val(),
			errorDiv : $("#errorDiv").val(),
			goodsName : $("#goodsName").val(),
			goodDetail : $("#goodDetail").val(),
			goodDetailCommon : $("#goodDetailCommon").val(),
			order : $("#order").val(),
			pageNo : window.pageNo,
			mode : mode,
			importKbn : sessionStorage.getItem("importModelMenu")
		}
		$.blAjax({
			// allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
			// modalStyle: 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
			// isTranFlag: true,// true:他画面へ遷移、false:他画面へ遷移しない
			url : accessURL,
			type : 'POST',
			dataType : 'Json',
			data : JSON.stringify(form),
			contentType : 'application/Json',
			success : function(data) {
				$(".searchNum").text(data.searchNum + '/');
				$(".allNum").text(data.allNum);
				$('.historyrows').text(data.maxRows);
				if (window.isPage) {
					paging(data.searchNum, data.maxRows);
				}
				if (data.gridDataUpdate.length == 0) {
					$(".page-info").hide();
					$('.btn-del').addClass('disabled');
					$('.btn-copy').addClass('disabled');
					$('.btn-paste').addClass('disabled');
					$('.replaceGuide').attr("disabled", true);
					$('.btn_detail').attr("disabled", true);
					var paramArr = new Array();
					paramArr[0] = "商品";
					layer.alert(getMessageInfo(message.E00008, paramArr), {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
					window.isPage = true;
				} else {
					$(".page-info").show();
					$('.btn-del').removeClass('disabled');
					$('.btn-copy').removeClass('disabled');
					$('.btn-paste').removeClass('disabled');
					$('.replaceGuide').attr("disabled", false);
					$('.btn_detail').attr("disabled", false);
				}
				collectionView = new wijmo.collections.CollectionView(
						data.gridDataUpdate);
				if (mode == 1 || mode == 3) {
					init(0);
				} else {
					init(1);
				}
			},
			error : function(data, httpStatus, errorHandler) {

			}
		});
	}
	;

	// グリッドの初期化

	function init(e) {
		var grid_col_defs;
		if (e == 0) {
			grid_col_defs = {
				check : {
					header : '',
					binding : 'check',
					dataType : wijmo.DataType.Boolean,
					width : 80
				},
				no : {
					header : 'No.',
					binding : 'no',
					width : 50,
					isReadOnly : true
				},
				applyStep : {
					header : '申請状態',
					binding : 'applyStep',
					isReadOnly : true
				},
				manageSec : {
					header : '処理区分',
					binding : 'manageSec',
					width : 120,
					isReadOnly : true
				},
				selCode : {
					header : 'セレクトコード名称',
					binding : 'selCode',
					width : 140
				},
				secCodeName : {
					header : '分類コード名称',
					binding : 'secCodeName',
					isReadOnly : true,
					width : 140
				},
				blCodeName : {
					header : 'BLコード名称',
					binding : 'blCodeName',
					width : 220
				},
				goodsNo : {
					header : '優良品番',
					binding : 'goodsNo'
				},
				nameS : {
					header : '品名（半角）',
					binding : 'nameS',
					width : 220
				},
				nameB : {
					header : '品名（全角）',
					binding : 'nameB',
					width : 220,
				},
				money : {
					header : '価格（税抜）',
					binding : 'money',
					dataType : wijmo.DataType.Number
				},
				open : {
					header : 'OPENプライス',
					binding : 'open',
					width : 100
				},
				jan : {
					header : 'JAN',
					binding : 'jan',
					width : 100,
					dataType : wijmo.DataType.Number
				},
				layer : {
					header : '層別',
					binding : 'layer',
					width : 234
				},
				equip : {
					header : '装備',
					binding : 'equip',
					width : 170
				},
				size : {
					header : '規格/特記',
					binding : 'size',
					width : 130
				},
				sizeCon : {
					header : '規格/特記（一般）',
					binding : 'sizeCon',
					width : 170
				},
				delSec : {
					header : '削除依頼区分',
					binding : 'delSec',
					width : 120
				},
				delCon : {
					header : '削除理由',
					binding : 'delCon',
					width : 140
				},
				goods : {
					header : '商品詳細',
					binding : 'goods',
					width : 220
				},
				goodsCon : {
					header : '商品詳細（一般）',
					binding : 'goodsCon',
					width : 220
				},
				width1 : {
					header : '長さ',
					binding : 'width1',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				width2 : {
					header : '幅',
					binding : 'width2',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				width3 : {
					header : '高さ',
					binding : 'width3',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				packwidth1 : {
					header : '梱包（長さ）',
					binding : 'packwidth1',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				packwidth2 : {
					header : '梱包（幅）',
					binding : 'packwidth2',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				packwidth3 : {
					header : '梱包（高さ）',
					binding : 'packwidth3',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				widthUnit : {
					header : '梱包単位',
					binding : 'widthUnit',
					width : 120
				},
				weight : {
					header : '重量',
					binding : 'weight',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				weightUnit : {
					header : '重量単位',
					binding : 'weightUnit',
					width : 60
				},
				url1 : {
					header : 'URL1',
					binding : 'url1',
					width : 170
				},
				url2 : {
					header : 'URL2',
					binding : 'url2',
					width : 170
				},
				url3 : {
					header : 'URL3',
					binding : 'url3',
					width : 170
				},
				img : {
					header : '画像有無',
					binding : 'img',
					width : 120,
					isReadOnly : true
				},
				dateCom : {
					header : '作成日時',
					binding : 'dateCom',
					dataType : wijmo.DataType.Date,
					width : 130,
					isReadOnly : true
				},
				dateRe : {
					header : '更新日時',
					binding : 'dateRe',
					dataType : wijmo.DataType.Date,
					width : 130,
					isReadOnly : true
				},
				dateSlice : {
					header : '適用日時',
					binding : 'dateSlice',
					width : 130
				},
				checkSec : {
					header : 'チェック区分',
					binding : 'checkSec',
					width : 100,
					isReadOnly : true
				},
				BLSec : {
					header : 'BL登録区分',
					binding : 'blSec',
					width : 100,
					isReadOnly : true
				},
				errSec : {
					header : 'エラー区分',
					binding : 'errSec',
					width : 100,
					isReadOnly : true
				},
				errCon : {
					header : 'エラー内容',
					binding : 'errCon',
					width : 280,
					isReadOnly : true
				}
			};
		} else {
			grid_col_defs = {

				no : {
					header : 'No.',
					binding : 'no',
					width : 50,
					isReadOnly : true
				},
				applyStep : {
					header : '申請状態',
					binding : 'applyStep',
					isReadOnly : true
				},
				manageSec : {
					header : '処理区分',
					binding : 'manageSec',
					width : 120,
					isReadOnly : true
				},
				selCode : {
					header : 'セレクトコード名称',
					binding : 'selCode',
					width : 140
				},
				secCodeName : {
					header : '分類コード名称',
					binding : 'secCodeName',
					isReadOnly : true,
					width : 140
				},
				blCodeName : {
					header : 'BLコード名称',
					binding : 'blCodeName',
					width : 220
				},
				goodsNo : {
					header : '優良品番',
					binding : 'goodsNo'
				},
				nameS : {
					header : '品名（半角）',
					binding : 'nameS',
					width : 220
				},
				nameB : {
					header : '品名（全角）',
					binding : 'nameB',
					width : 220,
				},
				money : {
					header : '価格（税抜）',
					binding : 'money',
					dataType : wijmo.DataType.Number
				},
				open : {
					header : 'OPENプライス',
					binding : 'open',
					width : 100
				},
				jan : {
					header : 'JAN',
					binding : 'jan',
					width : 100,
					dataType : wijmo.DataType.Number
				},
				layer : {
					header : '層別',
					binding : 'layer',
					width : 234
				},
				equip : {
					header : '装備',
					binding : 'equip',
					width : 170
				},
				size : {
					header : '規格/特記',
					binding : 'size',
					width : 130
				},
				sizeCon : {
					header : '規格/特記（一般）',
					binding : 'sizeCon',
					width : 170
				},
				delSec : {
					header : '削除依頼区分',
					binding : 'delSec',
					width : 120
				},
				delCon : {
					header : '削除理由',
					binding : 'delCon',
					width : 140
				},
				goods : {
					header : '商品詳細',
					binding : 'goods',
					width : 220
				},
				goodsCon : {
					header : '商品詳細（一般）',
					binding : 'goodsCon',
					width : 220
				},
				width1 : {
					header : '長さ',
					binding : 'width1',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				width2 : {
					header : '幅',
					binding : 'width2',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				width3 : {
					header : '高さ',
					binding : 'width3',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				packwidth1 : {
					header : '梱包（長さ）',
					binding : 'packwidth1',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				packwidth2 : {
					header : '梱包（幅）',
					binding : 'packwidth2',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				packwidth3 : {
					header : '梱包（高さ）',
					binding : 'packwidth3',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				widthUnit : {
					header : '梱包単位',
					binding : 'widthUnit',
					width : 120
				},
				weight : {
					header : '重量',
					binding : 'weight',
					dataType : wijmo.DataType.Number,
					width : 120
				},
				weightUnit : {
					header : '重量単位',
					binding : 'weightUnit',
					width : 60
				},
				url1 : {
					header : 'URL1',
					binding : 'url1',
					width : 170
				},
				url2 : {
					header : 'URL2',
					binding : 'url2',
					width : 170
				},
				url3 : {
					header : 'URL3',
					binding : 'url3',
					width : 170
				},
				img : {
					header : '画像有無',
					binding : 'img',
					width : 120,
					isReadOnly : true
				},
				dateCom : {
					header : '作成日時',
					binding : 'dateCom',
					dataType : wijmo.DataType.Date,
					width : 130,
					isReadOnly : true
				},
				dateRe : {
					header : '更新日時',
					binding : 'dateRe',
					dataType : wijmo.DataType.Date,
					width : 130,
					isReadOnly : true
				},
				dateSlice : {
					header : '適用日時',
					binding : 'dateSlice',
					width : 130
				},
				checkSec : {
					header : 'チェック区分',
					binding : 'checkSec',
					width : 100,
					isReadOnly : true
				},
				BLSec : {
					header : 'BL登録区分',
					binding : 'blSec',
					width : 100,
					isReadOnly : true
				},
				errSec : {
					header : 'エラー区分',
					binding : 'errSec',
					width : 100,
					isReadOnly : true
				},
				errCon : {
					header : 'エラー内容',
					binding : 'errCon',
					width : 280,
					isReadOnly : true
				}
			};
		}

		var gridConfig = {};
		gridConfig.columns = [];
		for ( var grid_col_name in grid_col_defs) {
			gridConfig.columns.push(grid_col_defs[grid_col_name]);
		}
		gridConfig.autoGenerateColumns = false;
		gridConfig.allowSorting = false;
		gridConfig.autoClipboard = false;
		gridConfig.itemsSource = collectionView;
		flexGrid.initialize(gridConfig);
		flexGrid.viewData = {};
		flexGrid.viewData.blStr = blStr;
		flexGrid.viewData.selectStr = selectStr;
		flexGrid.viewData.partsStr = partsStr;

		flexGrid.formatItem
				.addHandler(function(s, e) {
					if (e.panel.cellType == wijmo.grid.CellType.Cell
							&& (getColumnBinding(e.col) == grid_col_defs.money.binding
									|| getColumnBinding(e.col) == grid_col_defs.width1.binding
									|| getColumnBinding(e.col) == grid_col_defs.width2.binding
									|| getColumnBinding(e.col) == grid_col_defs.width3.binding
									|| getColumnBinding(e.col) == grid_col_defs.packwidth1.binding
									|| getColumnBinding(e.col) == grid_col_defs.packwidth2.binding
									|| getColumnBinding(e.col) == grid_col_defs.packwidth3.binding || getColumnBinding(e.col) == grid_col_defs.weight.binding)) {
						e.cell.style.textAlign = 'right';
					}
					if (e.panel.cellType == wijmo.grid.CellType.Cell
							&& (getColumnBinding(e.col) == grid_col_defs.checkSec.binding
									|| getColumnBinding(e.col) == grid_col_defs.applyStep.binding
									|| getColumnBinding(e.col) == grid_col_defs.manageSec.binding
									|| getColumnBinding(e.col) == grid_col_defs.BLSec.binding
									|| getColumnBinding(e.col) == grid_col_defs.no.binding || getColumnBinding(e.col) == grid_col_defs.errSec.binding)) {
						e.cell.style.textAlign = 'center';
					}
					if (e.panel.cellType == wijmo.grid.CellType.Cell
							&& (mode == 0 || mode == 3)
							&& (getColumnBinding(e.col) == grid_col_defs.no.binding
									|| getColumnBinding(e.col) == grid_col_defs.applyStep.binding
									|| getColumnBinding(e.col) == grid_col_defs.manageSec.binding
									|| getColumnBinding(e.col) == grid_col_defs.dateCom.binding
									|| getColumnBinding(e.col) == grid_col_defs.dateRe.binding
									|| getColumnBinding(e.col) == grid_col_defs.errSec.binding
									|| getColumnBinding(e.col) == grid_col_defs.errCon.binding
									|| (mode == 3 && getColumnBinding(e.col) == grid_col_defs.check.binding)
									|| getColumnBinding(e.col) == grid_col_defs.errCon.binding
									|| getColumnBinding(e.col) == grid_col_defs.img.binding
									|| getColumnBinding(e.col) == grid_col_defs.BLSec.binding
									|| getColumnBinding(e.col) == grid_col_defs.checkSec.binding
									|| getColumnBinding(e.col) == grid_col_defs.selCode.binding
									|| getColumnBinding(e.col) == grid_col_defs.goodsNo.binding || (mode == 3 && getColumnBinding(e.col) == grid_col_defs.check.binding))) {
						$(e.cell).addClass('c-cell');
					}

					if (e.panel.cellType == wijmo.grid.CellType.ColumnHeader
							&& (mode == 0 || mode == 3)
							&& (getColumnBinding(e.col) == grid_col_defs.goodsNo.binding
									|| getColumnBinding(e.col) == grid_col_defs.dateSlice.binding
									|| getColumnBinding(e.col) == grid_col_defs.secCodeName.binding
									|| getColumnBinding(e.col) == grid_col_defs.blCodeName.binding
									|| getColumnBinding(e.col) == grid_col_defs.nameS.binding
									|| getColumnBinding(e.col) == grid_col_defs.nameB.binding
									|| getColumnBinding(e.col) == grid_col_defs.money.binding
									|| getColumnBinding(e.col) == grid_col_defs.open.binding || getColumnBinding(e.col) == grid_col_defs.selCode.binding)) {
						$(e.cell)
								.html(
										function() {
											if ($(e.cell).html().indexOf("*") <= 0) {
												return $(e.cell).html()
														+ "<span style='color:red'>*</span>";
											} else {
												$(e.cell).html();
											}
										});
					}
					// 全検索チェックボックスの追加 2017/02/27 by趙命ラン
					if (e.panel.cellType == wijmo.grid.CellType.ColumnHeader
							&& (mode == 3 || mode == 1)
							&& getColumnBinding(e.col) == grid_col_defs.check.binding) {
						// var flex = e.panel.grid;
						var col = flexGrid.columns[e.col];

						// check that this is a boolean column
						if (col.dataType == wijmo.DataType.Boolean) {

							// prevent sorting on click
							col.allowSorting = false;

							// count true values to initialize checkbox
							var cnt = 0;
							for (var i = 0; i < flexGrid.rows.length; i++) {
								if (flexGrid.getCellData(i, e.col) == true)
									cnt++;
							}

							// create and initialize checkbox
						    e.cell.innerHTML = '<input type="checkbox"> ';
						    if(mode==3){
						    	 e.cell.innerHTML = '<input type="checkbox" disabled = true> ';
						    }
							var cb = e.cell.firstChild;
							cb.checked = cnt > 0;
							cb.indeterminate = cnt > 0
									&& cnt < flexGrid.rows.length;

							// apply checkbox value to cells
							cb.addEventListener('click', function() {
								flexGrid.beginUpdate();
								for (var i = 0; i < flexGrid.rows.length; i++) {
									flexGrid.setCellData(i, e.col, cb.checked);
								}
								flexGrid.endUpdate();
							});
						}
					}
					var cv = flexGrid.collectionView;
					var count = cv.totalItemCount;
					for (var i = 0; i < count; i++) {
						if (cv.items[i].hiddenArea==3) {
							if (e.panel.cellType == wijmo.grid.CellType.Cell
									&& e.row == i) {
								e.cell.classList.add('d-cell');
							}
						}
					}
				});
		// ガイドボタンの実現デモ
		flexGrid.formatItem
				.addHandler(function(s, e) {
					if (e.panel.cellType == wijmo.grid.CellType.Cell) {
						var editRange = flexGrid.editRange;
						var row = e.row;
						var col = e.col;
						var isEdit = editRange && editRange.row == row
								&& editRange.col == col; // 編集モード

						// コードセル
						if (getColumnBinding(e.col) == grid_col_defs.secCodeName.binding) {
							wijmo.addClass(e.cell, 'guide-code-button-cell');
							// ガイドボタンセルを作成
							var button = createCodeGuideButton(row, col);
							var items = flexGrid.collectionView.items;
							if (items[e.row]['applyStep'] == '中'
									|| !controlFlag) {
								button.disabled = "disabled"
							}
							if (mode == 1 || mode == 2) {
								button.disabled = "disabled"
								button.style.display = 'none';
							}
							if (isEdit) {
								button.style.display = 'block';
								e.cell.appendChild(button);
							} else {
								e.cell.appendChild(button);
							}
						}
					}
				});
		flexGrid.formatItem
				.addHandler(function(s, e) {
					if (e.panel.cellType == wijmo.grid.CellType.Cell) {
						var colBinding = getColumnBinding(e.col);
						// NO.104 tooltipの対応
						var gridCellTooltipGetTitle = function() {
							if (e.cell.offsetHeight < e.cell.scrollHeight || // overflow場合だけ表示
							e.cell.offsetWidth < e.cell.scrollWidth) {
								return e.cell.innerHTML;
							} else {
								return '';
							}
						};
						var cell = $(e.cell);
						var gridCellTooltipMouseEnterHandler = function(event) {
							if (e.cell.children.length == 0) {
								$.fn.tooltip.Constructor.prototype.getCalculatedOffset = function(
										placement, pos, actualWidth,
										actualHeight) {
									return {
										top : pos.top - 1,
										left : pos.left - 1
									};
								};
								cell.tooltip({
									container : 'body',
									trigger : 'manual',
									placement : 'auto right', 
									title : gridCellTooltipGetTitle
								});
								cell.tooltip('show');

								$('.tooltip[role=tooltip]')
										.on(
												'mousemove mouseleave',
												function(event) {
													gridCellTooltipMouseMoveHandler(event);
												});
							}
						};
						var gridCellTooltipMouseMoveHandler = function(event) {
							// tooltipと関連のものを削除する
							if (!cell.hitTest(event.pageX, event.pageY)) {
								cell.closest('.wj-cell').tooltip('destroy')
										.removeAttr('title').removeAttr(
												'data-original-title');
								$('.tooltip[role=tooltip]').remove();
							}
						};
						cell
								.off('mouseenter',
										gridCellTooltipMouseEnterHandler);
						cell.off('mouseleave', gridCellTooltipMouseMoveHandler);
						cell.on('mouseenter', gridCellTooltipMouseEnterHandler);
						cell.on('mouseleave', gridCellTooltipMouseMoveHandler);
					}
				});
		// 商品選択モードの制御
		if (mode == 1) {
			flexGrid.formatItem
					.addHandler(function(s, e) {
						var cv = flexGrid.collectionView;
						var count = cv.totalItemCount;
						for (var i = 0; i < count; i++) {
							var flag = cv.items[i].compareFlag;
							var compareFlag = new Array();
							if (flag != null) {
								compareFlag = flag.split(",");
								for (var j = 0; j < compareFlag.length; j++) {
									if (compareFlag[j] == "1"
											&& e.panel.cellType == wijmo.grid.CellType.Cell
											&& e.row == i && e.col == j) {
										e.cell.classList.add('row-checked');
									}
								}
							} else {
								if (e.panel.cellType == wijmo.grid.CellType.Cell
										&& e.row == i) {
									e.cell.classList.add('row-checked');
								}
							}
						}
					});
		}
		if (mode == 0 || mode == 3) {
			flexGrid.formatItem
					.addHandler(function(s, e) {
						var cv = flexGrid.collectionView;
						var count = cv.totalItemCount;
						if (cv.items.length != 0
								&& cv.items[e.row].hiddenArea == 0
								&& (getColumnBinding(e.col) == grid_col_defs.goodsNo.binding || getColumnBinding(e.col) == grid_col_defs.selCode.binding)) {
							e.cell.classList.remove('c-cell');
						}
					});
		}
		$.fn.hitTest = function(x, y) {
			var bounds = this.offset();
			bounds.right = bounds.left + this.outerWidth();
			bounds.bottom = bounds.top + this.outerHeight();
			return x > bounds.left && x < bounds.right && y > bounds.top
					&& y < bounds.bottom;
		};

		// コードガイドボタンを作成
		function createCodeGuideButton(row, col) {
			var button = document.createElement('button');
			wijmo.addClass(button, 'glyphicon glyphicon-star-empty');
			var openGuide = function(e) {
				try {
					var accessURL = baseurl + '/goods/goodsMGroup';
					var actionType = 'POST';
					var form = {
						goodsMGroup : flexGrid.getCellData(row, col)
					};
					$.blAjax({
						url : accessURL,
						type : actionType,
						data : JSON.stringify(form),
						success : function(data) {
							var guide = new BLUI.ClassifyCdGuide();
							guide.show('', true, {
								row : row,
								col : col
							});
							e.stopPropagation();

						},
						error : function(data, httpStatus, errorHandler) {
							errorHandler(data, httpStatus);
						}
					});
				} catch (e) {
					sendClientErrorLog(e);
				}
			};
			button.addEventListener('click', openGuide);
			button.addEventListener('mousedown', openGuide);
			return button;
		}
		var getColumnDataType = function(col) {
			return flexGrid.cells.columns[col].dataType;
		}

		var oldData;
        var newData;
        
        flexGrid.cellEditEnded.addHandler(function(sender, e) {
        	var binding = getColumnBinding(e.col);
			// 適用日時
        	if(binding == grid_col_defs.dateSlice.binding) {
        		var newData = flexGrid.getCellData(e.row, e.col);
        		if(!checkDate(newData)){
          		  layer.alert('YYYY/MM/DD形式を入力してください。', {
                        title : '',
                        closeBtn : 0,
                        btn : [ 'はい' ]
                  });
          		flexGrid.setCellData(e.row, e.col, oldData);
          	  } else {
          		if (newData.length <= 10) {
          			flexGrid.setCellData(e.row, e.col, newData + "　00:00");
          		}
          	  }
        		oldData = undefined;
        		newData = undefined;
        	}
        	
        	if(binding == 'nameS'){
        		newData = flexGrid.getCellData(e.row, e.col);
        		if(!checkHankaku(newData)){
        			var paramArr = ['品名（半角）'];
        			layer.alert(getMessageInfo(message.E00004, paramArr), {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
        			if(typeof oldData == 'undefined'){
        				flexGrid.setCellData(e.row, e.col, '');
        			} else {
        				flexGrid.setCellData(e.row, e.col, oldData);
        			}
        		} else {
        			flexGrid.setCellData(e.row, e.col, zenkaku2Hankaku(newData));
        			oldData = zenkaku2Hankaku(newData);
        		}
        		oldData = undefined;
        		newData = undefined;
        	}
        });

		// Gridのセルフォーマット用
		var gridItemFormatter = function(panel, r, c, cell) {
			var gridItem = panel.grid;
			var editRange = flexGrid.editRange;

			if (!(editRange && panel.cellType == wijmo.grid.CellType.Cell
					&& editRange.row == r && editRange.col == c)) {
				return;
			}

			// 入力制御 長さ制限と数字入力制限
			flexGrid.prepareCellForEdit.addHandler(function(s,e){
                 var binding = getColumnBinding(e.col);
                 if(binding == 'nameS' || binding == 'nameB' || binding == 'equip'){
                     $(flexGrid.activeEditor).attr('maxlength', 60);
                 }
                 if(binding == 'size' || binding == 'sizeCon' || binding == 'delCon'){
                     $(flexGrid.activeEditor).attr('maxlength', 80);
                 }
                 if(binding == 'goods' || binding == 'goodsCon'){
                     $(flexGrid.activeEditor).attr('maxlength', 512);
                 }
                 if(binding == 'url1' || binding == 'url2' || binding == 'url3'){
                     $(flexGrid.activeEditor).attr('maxlength', 512);
                 }
                 if(binding == 'dateSlice'){
                	 $(flexGrid.activeEditor).alphanum({
                     	allow              : '/',
                     	allowSpace         : false,
                     	allowNumeric       : true,
                     	allowLatin         : false,
                 		maxLength          : 10    
                     });
                	 oldData = $(flexGrid.activeEditor).val();
                 }
             });

			var columnName = gridItem.columns[c].binding;
			
			flexGrid.prepareCellForEdit.addHandler(function(s,e){
				var binding = getColumnBinding(e.col);
				// 優良品番
	        	if(binding == 'goodsNo'){
	        		var oldNum = $(flexGrid.activeEditor).val();
	        		$(flexGrid.activeEditor).on('input', function(){
	        			var newNum = $(flexGrid.activeEditor).val();
	        			if(newNum.length != newNum.replace(/[^((0-9)|(a-zA-Z))]/g, '').length){
	        				$(flexGrid.activeEditor).val(oldNum);
	        			} else {
	        				oldNum = newNum;
	        			}
	        		})
	        	}
	        	if(binding == 'nameS'){
	        		var oldData = flexGrid.getCellData(e.row, e.col);
	        	}
			});
			// 価格（税抜）
			if (columnName == grid_col_defs.money.binding) {
				$(gridItem.activeEditor).numeric({
					allowDecSep : false,
					max : 999999999999,
					min : 0
				});
			}
			// jan
			if (columnName == grid_col_defs.jan.binding) {
				$(gridItem.activeEditor).numeric({
					allowDecSep : false,
					max : 9999999999999,
					min : 0
				});
			}
			// 長さ,幅,高さ,梱包（長さ）,梱包（幅）,梱包（高さ）,重量
			if (columnName == grid_col_defs.width1.binding
					|| columnName == grid_col_defs.width2.binding
					|| columnName == grid_col_defs.width3.binding
					|| columnName == grid_col_defs.packwidth1.binding
					|| columnName == grid_col_defs.packwidth2.binding
					|| columnName == grid_col_defs.packwidth3.binding
					|| columnName == grid_col_defs.weight.binding) {
				$(gridItem.activeEditor).numeric({
					allowDecSep : false,
					max : 999999,
					min : 0
				});
			}

			// console.log('[ItemFormatter] row:' + r + ', col:' + c);
			// NO.115 値の自動変換機能
			if (wijmo.DataType.Number == getColumnDataType(c)) {
				if (gridItem.activeEditor.value == null
						|| gridItem.activeEditor.value == '') { // 空文字はゼロに変換
					gridItem.activeEditor.value = 0;
				} else {
					gridItem.activeEditor.value = gridItem.activeEditor.value
							.replace(/,/g, ''); // コンマを削除
				}
			}

			var colBinding = getColumnBinding(c);

			// コンボボックスを作成する (FIXME:コンボボックスと関連の問題がいろいろがある)
			if (colBinding == grid_col_defs.blCodeName.binding) { // 種別列
				createGridComboBoxCell(r, c, cell, blStr.split(","));
			} else if (colBinding == grid_col_defs.delSec.binding) { // 作区列
				createGridComboBoxCell(r, c, cell, [ '', '削除する' ]);
			} else if (colBinding == grid_col_defs.selCode.binding) { // 作区列
				createGridComboBoxCell(r, c, cell, selectStr.split(","));
			} else if (colBinding == grid_col_defs.layer.binding) { // 作区列
				createGridComboBoxCell(r, c, cell, partsStr.split(","));
			} else if (colBinding == grid_col_defs.widthUnit.binding) { // 作区列
				createGridComboBoxCell(r, c, cell, [ '', 'mm', 'cm', 'm' ]);
			} else if (colBinding == grid_col_defs.weightUnit.binding) { // 作区列
				createGridComboBoxCell(r, c, cell, [ '', 'g', 'kg', 't' ]);
			} else if (colBinding == grid_col_defs.open.binding) { // 作区列
				createGridComboBoxCell(r, c, cell, [ '', '通常', 'オープン価格' ]);
			}
		};
		var createGridComboBoxCell = function(r, c, cell, itemsSource,
				classList) {
			if (classList) {
				cell.innerHTML = '<div id="grid_combox" class="' + classList
						+ '"></div>';
			} else {
				cell.innerHTML = '<div id="grid_combox"></div>';
			}
			var comboBox = new wijmo.input.ComboBox('#grid_combox');

			comboBox.isEditable = false;
			comboBox.required = false;
			comboBox.itemsSource = itemsSource;
			comboBox.text = flexGrid.getCellData(r, c);
			comboBox.textChanged.addHandler(function(e) {
				$(flexGrid.activeEditor).val(comboBox.selectedValue);
			});

			var gridComboxClickHandler = function(e) {
				if (comboBox.isDroppedDown) {
					comboBox.isDroppedDown = false;
				} else {
					comboBox.isDroppedDown = true;
				}
				e.stopImmediatePropagation();
				e.preventDefault();
			};
			$(comboBox.inputElement).bind('click', gridComboxClickHandler);
			$(comboBox.inputElement).bind('keydown', 'alt+down alt+up',
					gridComboxClickHandler);

			var gridComboxUpDownKeyHandler = function(e) {
				if (comboBox.isDroppedDown) {
					var shift = event.shiftKey && !event.ctrlKey
							&& !event.altKey && !event.metaKey;
					var alt = !event.shiftKey && !event.ctrlKey && event.altKey
							&& !event.metaKey;
					var ctrl = !event.shiftKey && event.ctrlKey
							&& !event.altKey && !event.metaKey;
					var nospec = !event.shiftKey && !event.ctrlKey
							&& !event.altKey && !event.metaKey;
					if (nospec && event.which == 38) { // up
						comboBox.selectedIndex = Math.max(0,
								comboBox.selectedIndex - 1);
						if (comboBox.selectedIndex < 0) { // 不具合現象をFIXために
							comboBox.selectedIndex = 0;
						}
						e.stopImmediatePropagation();
						e.preventDefault();
					} else if (nospec && event.which == 40) { // down
						comboBox.selectedIndex = Math.min(
								comboBox.itemsSource.length - 1,
								comboBox.selectedIndex + 1);
						e.stopImmediatePropagation();
						e.preventDefault();
					}
				}
			}
			$(comboBox.inputElement).bind('keydown', 'up down',
					gridComboxUpDownKeyHandler);
		};

		flexGrid.itemFormatter = gridItemFormatter;

		// NO.6 読取専用のセルを判断用
		var isReadonlyCell = function(row, col) {
			if (row < 0 || col < 0) {
				return true;
			}
			var panel = flexGrid.cells;
			var column = flexGrid.cells.columns[col];
			var colBinding = column.binding;
			if (column.isReadOnly) {
				return true;
			}
			var items = flexGrid.itemsSource.items;

			// 種別列の値でセル読取かどうかことを制御

			if (mode == 3 && col == getColumnIndex(grid_col_defs.check.binding)) {
				return true;
			}
			if (mode == 1
					&& (col == getColumnIndex(grid_col_defs.selCode.binding)
							|| col == getColumnIndex(grid_col_defs.secCodeName.binding)
							|| col == getColumnIndex(grid_col_defs.blCodeName.binding)
							|| col == getColumnIndex(grid_col_defs.goodsNo.binding)
							|| col == getColumnIndex(grid_col_defs.nameS.binding)
							|| col == getColumnIndex(grid_col_defs.nameB.binding)
							|| col == getColumnIndex(grid_col_defs.money.binding)
							|| col == getColumnIndex(grid_col_defs.open.binding)
							|| col == getColumnIndex(grid_col_defs.jan.binding)
							|| col == getColumnIndex(grid_col_defs.layer.binding)
							|| col == getColumnIndex(grid_col_defs.equip.binding)
							|| col == getColumnIndex(grid_col_defs.size.binding)
							|| col == getColumnIndex(grid_col_defs.sizeCon.binding)
							|| col == getColumnIndex(grid_col_defs.delSec.binding)
							|| col == getColumnIndex(grid_col_defs.delCon.binding)
							|| col == getColumnIndex(grid_col_defs.goods.binding)
							|| col == getColumnIndex(grid_col_defs.goodsCon.binding)
							|| col == getColumnIndex(grid_col_defs.width1.binding)
							|| col == getColumnIndex(grid_col_defs.width2.binding)
							|| col == getColumnIndex(grid_col_defs.width3.binding)
							|| col == getColumnIndex(grid_col_defs.packwidth1.binding)
							|| col == getColumnIndex(grid_col_defs.packwidth2.binding)
							|| col == getColumnIndex(grid_col_defs.packwidth3.binding)
							|| col == getColumnIndex(grid_col_defs.widthUnit.binding)
							|| col == getColumnIndex(grid_col_defs.weight.binding)
							|| col == getColumnIndex(grid_col_defs.weightUnit.binding)
							|| col == getColumnIndex(grid_col_defs.url1.binding)
							|| col == getColumnIndex(grid_col_defs.url2.binding)
							|| col == getColumnIndex(grid_col_defs.url3.binding)
							|| col == getColumnIndex(grid_col_defs.img.binding)
							|| col == getColumnIndex(grid_col_defs.dateSlice.binding)
							|| col == getColumnIndex(grid_col_defs.checkSec.binding)
							|| col == getColumnIndex(grid_col_defs.BLSec.binding)
							|| col == getColumnIndex(grid_col_defs.errSec.binding) || col == getColumnIndex(grid_col_defs.errCon.binding))) {
				return true;
			}
			if ((items[row].hiddenArea == 1 || items[row].hiddenArea == 3|| items[row].hiddenArea == 2)
					&& (col == getColumnIndex(grid_col_defs.selCode.binding) || col == getColumnIndex(grid_col_defs.goodsNo.binding))) {
				return true;
			} else {
				return false;
			}
		}

		var edtingCell = null;
		flexGrid.beginningEdit.addHandler(function(s, e) {
			if (e.panel.cellType == wijmo.grid.CellType.Cell) {
				var items = flexGrid.collectionView.items;
				if (isReadonlyCell(e.row, e.col)) {
					// NO.16 入力不可セルの制御
					e.cancel = true;
					return;
				} else if (items[e.row]['applyStep'] == '中') {
					var paramArr = new Array();
					paramArr[0] = "申請中";
					paramArr[1] = "商品";
					layer.alert(getMessageInfo(message.E00011, paramArr), {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
					e.cancel = true;
					return;
				} else {
					edtingCell = {
						row : e.row,
						col : e.col,
						value : findCell(e.row, e.col).innerText
					};
					if (mode == 1) {
						edtingCell = {
							row : e.row,
							col : e.col,
							value : items[e.row]['check']
						};
					}
					// NO.104 編集可セルのtooltipを削除する
					$('.tooltip[role=tooltip]').remove();
				}
			}
		});

		flexGrid.cellEditEnded.addHandler(function(s, e) {
			var items = flexGrid.collectionView.items;
			if(findCell(e.row, e.col)==null){
               if(edtingCell.value!=""&&edtingCell.row == e.row && edtingCell.col == e.col&&items[e.row]['hiddenArea'] == 1){
            	   items[e.row]['hiddenArea'] = 2;
               }
			}else{
			  if (edtingCell.row == e.row && edtingCell.col == e.col
					&& edtingCell.value != findCell(e.row, e.col).innerText) {
				if (items[e.row]['hiddenArea'] == 1) {
					items[e.row]['hiddenArea'] = 2;
				}
			  }
			}
			if (edtingCell.row == e.row && mode == 1
					&& edtingCell.value != items[e.row]['check']) {
				if (items[e.row]['hiddenArea'] == 1) {
					items[e.row]['hiddenArea'] = 2;
				}
			}
			if (isGridOut()) {
				sessionStorage.setItem("confirmMessage",
						getMessageInfo(message.Q00001));
			} else {
				sessionStorage.setItem("confirmMessage", null);
			}
			if(!requestFlag)
			{
				check();
			    saveRealTime(1);
			}
		});

		var findCell = function(row, col) { // FIXME:

			var rc = flexGrid.getCellBoundingRect(row, col);
			var cell = document.elementFromPoint(rc.left + rc.width / 2, rc.top
					+ rc.height / 2);

			if (wijmo.hasClass(cell, 'wj-header')) {
				cell = null;
			}

			while (cell && !wijmo.hasClass(cell, 'wj-cell')) {
				cell = cell.parentElement;
			}
			return cell;
		};
		var isDelete = true;
		var isEmpty = false;
		flexGrid.formatItem
				.addHandler(function(s, e) {
					if (e.panel.cellType == wijmo.grid.CellType.Cell) {
						// 必須項目チェック
						if (getColumnBinding(e.col) == grid_col_defs.selCode.binding
								|| getColumnBinding(e.col) == grid_col_defs.secCodeName.binding
								|| getColumnBinding(e.col) == grid_col_defs.blCodeName.binding
								|| getColumnBinding(e.col) == grid_col_defs.goodsNo.binding
								|| getColumnBinding(e.col) == grid_col_defs.nameS.binding
								|| getColumnBinding(e.col) == grid_col_defs.nameB.binding
								|| getColumnBinding(e.col) == grid_col_defs.money.binding
								|| getColumnBinding(e.col) == grid_col_defs.open.binding
								|| getColumnBinding(e.col) == grid_col_defs.dateSlice.binding) {
							var value = flexGrid.getCellData(e.row, e.col,
									false);
							if (!value) {
								e.cell.classList.add('null-cell');
							}
						}
						// 削除依頼区分は削除する場合、理由は必須入力TODO
						if (getColumnBinding(e.col) == grid_col_defs.delSec.binding) {
							if (flexGrid.getCellData(e.row, e.col, false) == undefined
									|| flexGrid
											.getCellData(e.row, e.col, false) == "") {
								isDelete = false;
							} else {
								isDelete = true;
							}
						}

						if (getColumnBinding(e.col) == grid_col_defs.delCon.binding
								&& isDelete
								&& (flexGrid.getCellData(e.row, e.col, false) == undefined || flexGrid
										.getCellData(e.row, e.col, false) == "")) {
							e.cell.classList.add('null-cell');
							isDelete = false;
						}
						
						// 「OPENプライス」が「オープン価格」and「価格（税抜）」≠0の場合
						if (getColumnBinding(e.col) == grid_col_defs.money.binding) {
							if (flexGrid.getCellData(e.row, e.col, false) != undefined
									&& flexGrid.getCellData(e.row, e.col, false) != ""
										&& flexGrid.getCellData(e.row, e.col, false)*1 != 0) {
								isEmpty = true;
							} else {
								isEmpty = false;
							}
						}

						if (getColumnBinding(e.col) == grid_col_defs.open.binding
								&& isEmpty
								&& (flexGrid.getCellData(e.row, e.col, false) != undefined && flexGrid
										.getCellData(e.row, e.col, false)!= ""
											&&flexGrid.getCellData(e.row, e.col, false)=="オープン価格")) {
							e.cell.classList.add('null-cell');
							isEmpty = false;
						}
						
					}
				});

		var getColumnIndex = function(binding) {
			return flexGrid.cells.columns.getColumn(binding).index;
		};
		flexGrid.select(-1, -1);
	}
	;

	function isGridOut() {
		var con = false;
		var cv = flexGrid.collectionView;
		var count = cv.totalItemCount;
		for (var i = 0; i < count; i++) {
			if (cv.items[i].hiddenArea == 0 || cv.items[i].hiddenArea == 2
					|| cv.items[i].hiddenArea == 3) {
				con = true;
			}
		}
		return con;
	}
	;

	var activeRow = -1;
	flexGrid.selectionChanged.addHandler(function(s, e) {
		var range = e.range;
		if (activeRow >= 0 && activeRow < flexGrid.rows.length) {
			flexGrid.rowHeaders.setCellData(activeRow, 0, '');
		}
		activeRow = range.row;
		flexGrid.rowHeaders.setCellData(activeRow, 0, '\uf0da');
	});

	$('.btn-add').click(function() {
		if(!requestFlag)
		{
			rowAddHandler();
		    saveRealTime(1);
		}
	});
	$('.btn-del').click(function() {
		if(!requestFlag)
		{
			rowDeleteHandler();
		    saveRealTime(1);
		}
	});
	$('.btn-copy').click(function() {
		rowCopyHandler();
	});
	$('.btn-paste').click(function() {
		if(!requestFlag)
		{
			rowPasteHandler();
		    saveRealTime(1);
		}
	});

	var rowAddHandler = function() {
		if ($('.btn-add').hasClass("disabled")) {
			return;
		}
		$('.btn-del').removeClass('disabled');
		$('.btn-copy').removeClass('disabled');
		$('.btn-paste').removeClass('disabled');
		$('.replaceGuide').attr("disabled", false);
		$('.btn_detail').attr("disabled", false);
		var newRowData = {};
		newRowData.hiddenArea = 0;
		newRowData.applyStep = "未";
		newRowData.manageSec = "追";
		newRowData.check = false;
		newRowData.checkSec = "未";
		newRowData.blSec = "未";

		var cv = flexGrid.collectionView;

		if (activeRow >= 0) {
			Array.prototype.splice.apply(flexGrid.itemsSource.sourceCollection,
					[ activeRow + 1, 0 ].concat(newRowData));
		} else {
			cv.sourceCollection.push(newRowData);
		}
		var count = cv.totalItemCount;
		for (var i = 0; i < count; i++) {
			cv.items[i].no = i + 1;
		}
		flexGrid.itemsSource.refresh();
		check();
		if (isGridOut()) {
			sessionStorage.setItem("confirmMessage",
					getMessageInfo(message.Q00001));
		} else {
			sessionStorage.setItem("confirmMessage", null);
		}
	};

	var rowDeleteHandler = function() {
		if ($('.btn-del').hasClass("disabled")) {
			return;
		}
		// 選択行を削除
		var rows = flexGrid.selectedRows;
		if (rows.length == 0) {
			layer.alert(getMessageInfo(message.E00010), {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
		}
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].dataItem.hiddenArea == 0) {
				flexGrid.collectionView.remove(rows[i].dataItem);
			} else {
				var paramArr = new Array();
				paramArr[1] = "商品";
				if (rows[i].dataItem.importKbn == 2) {
					if (rows[i].dataItem.applyStep == "未") {
						rows[i].dataItem.hiddenArea = 3;
						rows[i].cssClass = 'd-cell';
					} else if (rows[i].dataItem.applyStep == "中") {
						paramArr[0] = "申請中";
						layer.alert(getMessageInfo(message.E00012, paramArr), {
							title : '',
							closeBtn : 0,
							btn : [ 'はい' ]
						});
					} else if (rows[i].dataItem.applyStep == "済"
							|| rows[i].dataItem.applyStep == "再") {
						paramArr[0] = "承認済";
						layer.alert(getMessageInfo(message.E00012, paramArr), {
							title : '',
							closeBtn : 0,
							btn : [ 'はい' ]
						});
					}
				} else {
					paramArr[0] = "インポート";
					layer.alert(getMessageInfo(message.E00012, paramArr), {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
				}
			}
		}
		if (rows.length > 0) {
			// 行indexを更新
			var cv = flexGrid.collectionView;
			var count = cv.totalItemCount;
			for (var i = 0; i < count; i++) {
				cv.items[i].no = i + 1;
			}
			if(cv.items.length==0){
				$(".page-info").hide();
				$('.btn-del').addClass('disabled');
				$('.btn-copy').addClass('disabled');
				$('.btn-paste').addClass('disabled');
				$('.replaceGuide').attr("disabled", true);
				$('.btn_detail').attr("disabled", true);
			}
		}
		check();
		if (isGridOut()) {
			sessionStorage.setItem("confirmMessage",
					getMessageInfo(message.Q00001));
		} else {
			sessionStorage.setItem("confirmMessage", null);
		}
	};

	var copiedRowData = null;
	var rowCopyHandler = function() {
		if ($('.btn-copy').hasClass("disabled")) {
			return;
		}
		var rows = flexGrid.selectedRows;
		copiedRowData = [];
		for (var i = 0; i < rows.length; i++) {
			var row = rows[i].dataItem;
			copiedRowData.push(row);
		}
		if (copiedRowData.length > 0) {
			$('.btn-paste').removeClass('disabled');
		} else {
			layer.alert(getMessageInfo(message.E00010), {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
		}
	};

	var rowPasteHandler = function() {
		if ($('.btn-paste').hasClass("disabled")) {
			return;
		}
		var items = flexGrid.itemsSource.items;
		if (items[activeRow].hiddenArea != 0) {
			layer.alert(getMessageInfo(message.E00016), {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
			return;
		}
		if (copiedRowData && copiedRowData.length > 0) {
			var copiedRowDataCloned = $.extend(true, [], copiedRowData); // ここはディープコピー必要です
			for (var i = 0; i < copiedRowDataCloned.length; i++) {
				copiedRowDataCloned[i].applyStep = "未";
				copiedRowDataCloned[i].manageSec = "追";
				copiedRowDataCloned[i].img = "×";
				copiedRowDataCloned[i].dateCom = "";
				copiedRowDataCloned[i].dateRe = "";
				copiedRowDataCloned[i].checkSec = "未";
				copiedRowDataCloned[i].blSec = "未";
				copiedRowDataCloned[i].errSec = "無";
				copiedRowDataCloned[i].errCon = "";
				copiedRowDataCloned[i].hiddenArea = 0;
			}
			// 行挿入
			Array.prototype.splice.apply(flexGrid.itemsSource.sourceCollection,
					[ activeRow, 1 ].concat(copiedRowDataCloned)); // insert
			// rows
			flexGrid.itemsSource.refresh();
			var cv = flexGrid.collectionView;
			var count = cv.totalItemCount;
			for (var i = 0; i < count; i++) {
				cv.items[i].no = i + 1;
			}
		}
		check();
	};
	var getColumnBinding = function(col) {
		return flexGrid.cells.columns[col].binding;
	}

	// --------------- add by liangsd ------------------>>>
	function saveRealTime(saveMode) {
		if(requestFlag)
		{
		    return;	
		}
		else
		{
			requestFlag = true;
		}
		var accessURL = baseurl + "/goods/saveRealTime";
		var form = {
			mode : saveMode,
			goodsGridDtoList : flexGrid.itemsSource.items
		}
		$.blAjax({
			// allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
			// modalStyle: 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
			// isTranFlag: true,// true:他画面へ遷移、false:他画面へ遷移しない
			url : accessURL,
			type : 'POST',
			dataType : 'Json',
			data : JSON.stringify(form),
			contentType : 'application/Json',
			success : function(data) {
				requestFlag = false;
			},
			error : function(data, httpStatus, errorHandler) {
				requestFlag = false;
			}
		});
	}
	// --------------- add by liangsd ------------------<<<
});
