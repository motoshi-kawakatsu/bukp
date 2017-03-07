/**
 * @file 申請履歴一覧
 */
$(function() {

	var searchcount = 1;
	var historyrows = 1;
	'use strict';
	// 初期データの準備
	var data = [];
	var accessURL = baseurl + "/applyhistory/gridinit";
	var mode = sessionStorage.getItem("applyMode");
	if (mode == undefined || mode == null) {
		mode = 1;
	}
	var form = {
		mode : mode
	}
	$.ajax({
		url : accessURL,
		type : 'post',
		dataType : 'Json',
		data : JSON.stringify(form),
		contentType : 'application/Json',
		error : function(xhr, status) {
			alert("error" + xhr.status);
		},
		success : function(result, status, xhr) {
			$('.applystatus').val(result.status);
			$('.applytype').val(result.applytype);
			if (result == null || result == undefined
					|| result.initList == null || result.initList == undefined
					|| result.initList.length == 0) {
				flexGrid.itemsSource = new wijmo.collections.CollectionView(
						data);

				$('.applycount').text("0件");
				$('.approvalcount').text("0件");
				$('.searchcount').text("0");
				$('.totalcount').text(result.totalCount);
				$('.apply-shosai').attr("disabled", true);
				$('.total-joho').attr("disabled", true);
				message = result.message;
				var paramArr = new Array();
				paramArr[0] = "申請履歴";
				layer.alert(getMessageInfo(message.E00008, paramArr), {
					title : '',
					closeBtn : 0
				});
			} else {
				var initdata = result.initList[0];
				flexGrid.itemsSource = new wijmo.collections.CollectionView(
						result.initList);

				$('.applycount').text(result.applyCount + "件");
				$('.approvalcount').text(result.approvalCount + "件");
				$('.searchcount').text(result.searchCount);
				$('.totalcount').text(result.totalCount);
				$('.apply-shosai').attr("disabled", false);
				$('.total-joho').attr("disabled", true);
			}
			$('.historyrows').text(result.historyRows);
			var searchcount = result.searchCount;
			var historyrows = result.historyRows;
			var pageMath = Math.ceil(searchcount / historyrows);
			if (pageMath != 1) {
				if(pageMath==3){
				$('.page-box').pagination({
					pageCount : pageMath,
					coping : false,
					count:1,
					prevContent : '<前のページ',
					nextContent : '次のページ>',
					keepShowPN : false,
					callback : pageCallback,
				});}else{
					$('.page-box').pagination({
						pageCount : pageMath,
						coping : true,
						count:1,
						prevContent : '<前のページ',
						nextContent : '次のページ>',
						keepShowPN : false,
						callback : pageCallback,
					});
				}
			} else {
				$('.page-box').hide();
			}
		}
	});

	// グリッドの初期化
	var flexGrid = new wijmo.grid.FlexGrid('#grid');

	flexGrid.initialize({
		allowSorting : false,
		autoGenerateColumns : false,
		selectionMode : 'Row',
		columns : [ {
			header : '申請ID',
			binding : 'applyNo',
			width : 185,
			isReadOnly : true
		}, {
			header : '申請日時',
			binding : 'applyDateTime',
			width : 200,
			isReadOnly : true
		}, {
			header : '商品件数',
			binding : 'goodsNum',
			width : 120,
			isReadOnly : true,
		}, {
			header : 'セット件数',
			binding : 'setNum',
			width : 120,
			isReadOnly : true
		}, {
			header : '結合件数',
			binding : 'joinNum',
			width : 120,
			isReadOnly : true
		}, {
			header : '新規品目登録件数',
			binding : 'newItemNum',
			width : 140,
			isReadOnly : true
		}, {
			header : 'ステータス',
			binding : 'status',
			width : 150,
			isReadOnly : true
		}, {
			header : '申請コメント',
			binding : 'applyComment',
			width : 280,
			isReadOnly : true
		}, {
			header : 'BL申請結果',
			binding : 'blApplyResults',
			width : 140,
			isReadOnly : true
		}, {
			header : 'BL申請判断日',
			binding : 'blApplyJudgmentDate',
			width : 150,
			isReadOnly : true
		}, {
			header : 'BLコメント',
			binding : 'blComment',
			width : 280,
			isReadOnly : true
		}, ]
	});

	$('.btn-search').click(function() {
		if(!searchBeforeCheck()){
			return;
		}
		searchHandler(1);
	});
	
	function searchBeforeCheck() {
		// 申請期間
		if (!checkDateduration($("[name='insDtTimeStart']").val(), $("[name='insDtTimeStart']").val())) {
			layer.alert("開始申請日が終了申請日より前になるように設定してください。", {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
			return false
		}
		return true;
	}

	var searchHandler = function(page) {
		// 初期データの準備
		var data = [];
		var apply_history_search_key = {
			// 申請ID
			applyNo : $('.applyno').val(),
			// ステータス
			blApplyResultsFlg : $('.applystatus').val(),
			// 申請期間（FROM）
			applyDateTimeFrom : $('.applydatefrom').val(),
			// 申請期間（TO）
			applyDateTimeTo : $('.applydateto').val(),
			// BLコード
			tbsPartsCode : $('.codebl').val(),
			// 優良品番
			primePartsNo : $('.primepartsno').val(),
			// 申請種類
			applyType : $('.applytype').val(),
			// カーコード
			partsMakerCd : $('.partsmakercd').val(),
			// 純正品番
			joinSourPartsNoWithH : $('#joinSourPartsNoWithH').val(),
			// 表示順
			applySort : $('.sort').val(),
			// 当前頁
			page : page
		}
		var accessURL = baseurl + "/applyhistory/search";
		$
				.ajax({
					url : accessURL,
					type : 'post',
					dataType : 'Json',
					data : JSON.stringify(apply_history_search_key),
					contentType : 'application/Json',
					error : function(xhr, status) {
						alert("error" + xhr.status);
					},
					success : function(result, status, xhr) {
						if (result == null || result == undefined
								|| result.initList == null
								|| result.initList == undefined
								|| result.initList.length == 0) {
							flexGrid.itemsSource = new wijmo.collections.CollectionView(
									data);
							$('.applycount').text("0件");
							$('.approvalcount').text("0件");
							$('.searchcount').text("0");
							$('.totalcount').text(result.totalCount);
							$('.apply-shosai').attr("disabled", true);
							$('.total-joho').attr("disabled", true);
							message = result.message;
							var paramArr = new Array();
							paramArr[0] = "申請履歴";
							layer.alert(
									getMessageInfo(message.E00008, paramArr), {
										title : '',
										closeBtn : 0
									});
						} else {
							var initdata = result.initList[0];
							flexGrid.itemsSource = new wijmo.collections.CollectionView(
									result.initList);
							$('.applycount').text(result.applyCount + "件");
							$('.approvalcount')
									.text(result.approvalCount + "件");
							$('.searchcount').text(result.searchCount);
							$('.totalcount').text(result.totalCount);
							$('.apply-shosai').attr("disabled", false);
							// 「BLコード」、「カーコード」、「優良品番」、「純正品番」の中で、一つも入力されない
							if ("" == $('.codebl').val()
									&& "" == $('.partsmakercd').val()
									&& "" == $('.primepartsno').val()
									&& "" == $('.joinSourPartsNoWithH').val()) {
								$('.total-joho').attr("disabled", true);
							} else {
								$('.total-joho').attr("disabled", false);
							}
						}
						$('.historyrows').text(result.historyRows);
						var searchcount = result.searchCount;
						var historyrows = result.historyRows;
						var pageMath = Math.ceil(searchcount / historyrows);
						if (page == 1) {
							if (pageMath != 1) {
								if(pageMath==3){
								$('.page-box').show();
								$('.page-box').pagination({
									pageCount : pageMath,
									coping : false,
									count:1,
									prevContent : '<前のページ',
									nextContent : '次のページ>',
									keepShowPN : false,
									callback : pageCallback,
								});}else{
									$('.page-box').show();
									$('.page-box').pagination({
										pageCount : pageMath,
										coping : true,
										count:1,
										prevContent : '<前のページ',
										nextContent : '次のページ>',
										keepShowPN : false,
										callback : pageCallback,
									});
								}
							} else {
								$('.page-box').hide();
							}
						}
					}
				});
	};

	$('.btn-clear').click(function() {
		// 申請ID
		$('.applyno').val("");
		// ステータス
		$('.applystatus').val("");
		// 申請期間（FROM）
		$('.applydatefrom').val("");
		// 申請期間（TO）
		$('.applydateto').val("");
		// BLコード
		$('.codebl').val("");
		// 優良品番
		$('.primepartsno').val("");
		// 申請種類
		$('.applytype').val("2");
		// カーコード
		$('.partsmakercd').val("");
		// 純正品番
		$('#joinSourPartsNoWithH').val("");
	});

	$('.apply-shosai').click(function() {
		syosaiHandler();
	});

	var syosaiHandler = function() {
		if (0 == flexGrid.selectedRows[0].dataItem.newItemNum) {
			var form = {
				// 申請ID
				applyNo : flexGrid.selectedRows[0].dataItem.applyNo,
				// BL申請結果区分
				blApplyResultsFlg : flexGrid.selectedRows[0].dataItem.blApplyResults
			}
			var accessURL = baseurl + "/applyhistory/todetail";
			$.ajax({
				url : accessURL,
				type : 'post',
				dataType : 'Json',
				data : JSON.stringify(form),
				contentType : 'application/Json',
				error : function(xhr, status) {
					alert("error" + xhr.status);
				},
				success : function(result, status, xhr) {
					sessionStorage.setItem("applyDetail",
							BLENUM.MenuEnum.applyhistory);
					layer.config({
						extend : '../../lib/layer/skin/select/style.css'
					});
					layer.open({
						type : 2,
						title : false,
						skin : 'layer-ext-skin',
						shade : 0.1,
						area : [ '100%', '69%' ],
						closeBtn : false,
						content : [ baseurl + "/apply/applydetail" ],
						end : function() {
							var handleStatus = $(".apply-new-status").val();
							if (handleStatus == '-2') {
								$(".apply-new-status").val('');
								sessionStorage.setItem("importResultRedo", 1);
								window.location.href = baseurl
										+ '/importresult/importresult';
							}
						}
					});
				}

			});
		} else {
			var form = {
				// 申請ID
				applyNo : flexGrid.selectedRows[0].dataItem.applyNo,
				// BL申請結果区分
				blApplyResultsFlg : flexGrid.selectedRows[0].dataItem.blApplyResults
			}
			var accessURL = baseurl + "/applyhistory/tonewitem";
			$.ajax({
				url : accessURL,
				type : 'post',
				dataType : 'Json',
				data : JSON.stringify(form),
				contentType : 'application/Json',
				error : function(xhr, status) {
					alert("error" + xhr.status);
				},
				success : function(result, status, xhr) {
					layer.config({
						extend : '../../lib/layer/skin/select/style.css'
					});
					layer.open({
						type : 2,
						title : false,
						skin : 'layer-ext-skin',
						shade : 0.1,
						area : [ '100%', '69%' ],
						closeBtn : false,
						content : [ baseurl
								+ "/applynewcategory/applynewcategory" ],
						end : function() {
							var handleStatus = $(".apply-new-status").val();
							if (handleStatus == '4') {
								$(".apply-new-status").val('');
								window.location.href = baseurl
										+ '/applynewcategory/applynewcategory';
							}
						}
					});
				}

			});
		}

	};

	$('.total-joho').click(function() {
		totalJohoHandler();
	});

	var totalJohoHandler = function() {
		sessionStorage.setItem("tbsPartsCode", $('.codebl').val());
		sessionStorage.setItem("primePartsNo", $('.primepartsno').val());
		sessionStorage.setItem("partsMakerCd", $('.partsmakercd').val());
		sessionStorage.setItem("joinSourPartsNoWithH", $(
				'.joinSourPartsNoWithH').val());
		layer.config({
			extend : '../../lib/layer/skin/select/style.css'
		});
		layer.open({
			type : 2,
			title : false,
			skin : 'layer-ext-skin',
			shade : 0.1,
			area : [ '80%', '69%' ],
			closeBtn : false,
			content : [ baseurl + "/totalinfo/totalinfo" ],
			btn : [ '閉じる' ],
		});
	};

	$(".panel-heading").bind('click', function() {
		slidePanel();
		if ($(".panel-heading-icon").hasClass("glyphicon-chevron-down")) {
			$(".btn-slide").html("詳細条件");
		} else {
			$(".btn-slide").html("閉じる");
		}
	})
	$(".btn-slide").click(function() {
		slidePanel();
		if ($(".panel-heading-icon").hasClass("glyphicon-chevron-down")) {
			$(".btn-slide").html("詳細条件");
		} else {
			$(".btn-slide").html("閉じる");
		}
	})
	function slidePanel() {
		$(".panel-heading-icon").toggleClass("glyphicon-chevron-down");
		$(".panel-heading-icon").toggleClass("glyphicon-chevron-up");
		$(".slide-pannel").slideToggle();
	}
	$('.date-input').datetimepickerJp();
	/*
	 * if(pageMath != 1){ $('.page-box').pagination({ pageCount: pageMath,
	 * coping: true, prevContent:'<前ページ', nextContent:'後ページ>', callback:
	 * pageCallback, }); }
	 */
	function pageCallback(page_index, jq) {
		searchHandler(page_index.getCurrent());
	}
	;

	var nabigeshon = new BLUI.Nabigeshon();
	nabigeshon.getData(9);
	$(".page-back").click(function() {
		window.location.href = baseurl + "/topmenu/topMenu";
	});

	// 純正品番
	$('.joinSourPartsNoWithH').focus(function(event) {
		setValue();
	});

	$('.joinSourPartsNoWithH').blur(function(event) {
		getValue(event, "1");
	});

	// 分類コード(純正品番)のガイド(検索条件のアリア)
	$('.joinSourPartsNoWithH').click(function() {
		try {
			var accessURL = baseurl + "/applyhistory/joinSourPartsNoWithH";
			var inputname = "joinSourPartsNoWithH";
			var isGrid = false;
			var form = {
				joinSourPartsNoWithH : $('[name="joinSourPartsNoWithH"]').val()
			};
			$.blAjax({
				url : accessURL,
				type : "POST",
				data : JSON.stringify(form),
				success : function(data) {
					var guide = new BLUI.GoodsGuide();
					guide.show(inputname, isGrid, {});

				},
				error : function(data, httpStatus, errorHandler) {
					errorHandler(data, httpStatus);
				}
			});
		} catch (e) {
			sendClientErrorLog(e);
		}

	});

	function setValue() {
		var value = event.currentTarget.value;
		value = value.split("：")[0];
		event.currentTarget.value = value;
	}

	function getValue(event, div) {
		var accessURL = baseurl + "/changecommon/getCode";
		var form = {
			code : event.currentTarget.value,
			guideType : div
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
						btn : [ 'はい' ],
						end : function() {
							event.currentTarget.value = "";
						}
					});
				}
				event.currentTarget.value = data.codeValue;
			},
		});
	}

});