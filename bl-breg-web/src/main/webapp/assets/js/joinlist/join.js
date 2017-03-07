/**
 * @file 結合一覧
 */
$(function() {
	'use strict';

	var flexGrid = new wijmo.grid.FlexGrid('#grid');

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
				});
			}else{
			$('.page-box').show();
			$('.page-box').pagination({
				pageCount : pageMath,
				coping : true,
				count:1,
				keepShowPN : false,
				prevContent : '<前のページ',
				nextContent : '次のページ>',
				callback : pageCallback,
			});}
		} else {
			$('.page-box').hide();
		}
		function pageCallback(page_index, jq) {
			if (mode != 1 && isGridOut()) {
				layer.confirm(message.Q00001, {
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
							});
						}else{
						$('.page-box').pagination({
							pageCount : pageMath,
							coping : true,
							count:1,
							keepShowPN : false,
							current : page_index.getCurrent(),
							prevContent : '<前のページ',
							nextContent : '次のページ>',
							callback : pageCallback,
						});}
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

	function selectPage() {
		var accessURL = baseurl + "/joinlist/certain";
		var form = {
			joinGridList : flexGrid.itemsSource.items
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
				errorHandler(data, httpStatus);
			}
		});
	}

	// 純正品番
	$('#joinSourPartsNoWithH').focus(function(event) {
		setValue();
	});

	$('#joinSourPartsNoWithH').blur(function(event) {
		getValue(event, "1");
	});

	// 優良品番
	$('#joinDestPartsNo').focus(function(event) {
		setValue();
	});

	$('#joinDestPartsNo').blur(function(event) {
		getValue(event, "2");
	});

	// 分類コード
	$('#goodsMGroup').focus(function(event) {
		setValue();
	});

	$('#goodsMGroup').blur(function(event) {
		getValue(event, "0");
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

	// 分類コードのガイド(検索条件のアリア)
	$('.classifyCdGuide').click(function() {
		try {
			var accessURL = baseurl + "/joinlist/goodsMGroup";
			var inputname = "classifyCd";
			var isGrid = false;
			var form = {
				goodsMGroup : $('[name="classifyCd"]').val()
			};
			$.blAjax({
				url : accessURL,
				type : "POST",
				data : JSON.stringify(form),
				success : function(data) {
					var guide = new BLUI.ClassifyCdGuide();
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

	// 分類コード(純正品番)のガイド(検索条件のアリア)
	$('.joinSourPartsNoWithH').click(function() {
		try {
			var accessURL = baseurl + "/joinlist/joinSourPartsNoWithH";
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

	// 分類コード(優良品番)のガイド(検索条件のアリア)
	$('.joinDestPartsNo').click(function() {
		try {
			var accessURL = baseurl + "/joinlist/joinDestPartsNo";
			var inputname = "joinDestPartsNo";
			var isGrid = false;
			var form = {
				joinDestPartsNo : $('[name="joinDestPartsNo"]').val()
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

	// 「詳細条件」と「閉じる」ボタンの設定
	slidePanel();

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
				window.slidePanel = 0;
			} else {
				button.removeClass('close-condition').addClass(
						'detail-condition').html('詳細条件');
				window.slidePanel = 1;
			}
		});

		var panelShow = function() {
			$(this).removeClass('detail-condition').addClass('close-condition')
					.html('閉じる');
			window.slidePanel = 0;
			$(".init-hide").slideToggle();
			$(".panel-heading.head").parent(".slide-panel").toggleClass(
					"slide-down");
		};
		var panelHidden = function() {
			$(this).removeClass('close-condition').addClass('detail-condition')
					.html('詳細条件');
			window.slidePanel = 1;
			$(".init-hide").slideToggle();
			$(".panel-heading.head").parent(".slide-panel").toggleClass(
					"slide-down");
		}
		$('.group-button').on('click', '.detail-condition', {}, panelShow);
		$('.group-button').on('click', '.close-condition', {}, panelHidden);

	}
	;
	// 「チェックリスト」ボタン
	$(".btn_check_list").click(
			function() {
				var checkList = function(index) {
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
						sessionStorage.setItem("checkList",
								BLENUM.MenuEnum.Union);
					} else if (mode == 3) {
						sessionStorage.setItem("checkList",
								BLENUM.MenuEnum.joincorrect);
					}
					layer.close(index);
				};
				if (isGridOut()) {
					layer.confirm(message.Q00001, {
						icon : 3,
						title : '',
						closeBtn : 0,
						btn : [ 'はい', 'いいえ' ]
					}, checkList)
				} else {
					search();
					checkList();
				}
			});

	// 「出力」ボタン
	$('.outputGuide').click(function() {
		var grid = $(document.getElementById("grid"))[0]["wj-Control"];
		var output = function(index) {
			if (grid.rows.length == 0) {
				layer.alert(message.E00009, {
					title : '',
					closeBtn : 0,
					btn : [ 'はい' ]
				});
			} else {
				showOutput();
			}
			layer.close(index);
		};
		if (isGridOut()) {
			layer.confirm(message.Q00001, {
				icon : 3,
				title : '',
				closeBtn : 0,
				btn : [ 'はい', 'いいえ' ]
			}, output)
		} else {
			// search();
			if (grid.rows.length == 0) {
				layer.alert(message.E00009, {
					title : '',
					closeBtn : 0,
					btn : [ 'はい' ]
				});
			} else {
				showOutput();
			}
			layer.close(index);
		}
	});

	function showOutput() {
		layer.config({
			extend : '../../css/classifyCodeGuide/layerButton.css'
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
					content : [ baseurl + '/checkList/checkListOutPut' ],
					btn : [ '出力', 'キャンセル' ],
					sucess : function() {
						parentFrame = $('#child_page')[0].contentWindow.document;
					},

					yes : function(index, layero) {
						var childFrame = layero.find('iframe')[0].contentWindow.document;
						var code = $("input", childFrame);

						var selectCode = showCont(code);
						var params = {};
						params["fileType"] = selectCode;
						$.blAjax({
							url : baseurl + '/joinlist/makeFile',
							data : params,
							type : 'get',
							dataType : 'json',
							contentType : 'application/Json',
							success : function(data) {
								var result = data["data"];
								if (result[0] == "success") {
									$.download(baseurl
											+ '/checkList/downLoadFile',
											'post', result[1], result[2]); //ファイルをダウンロード
								} else {
									alert("データーのエクスポートは失敗です！");
								}
							},
							error : function(data, httpStatus, errorHandler) {
								errorHandler(data, httpStatus);
							}
						});
						layer.close(index);
					},
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

	// 出力時間を取得
	var time = getCurrentDate("yyyyMMddHHmmss")
	// Excel出力
	function outExcle(showGrid) {
		var showGrid = showGrid;
		wijmo.grid.xlsx.FlexGridXlsxConverter.save(showGrid, {
			includeColumnHeaders : true,
			includeCellStyles : false
		}, "結合_" + time + ".xlsx");
	}

	// PDF出力
	function outPutPDF(showGrid) {
		wijmo.grid.pdf.FlexGridPdfConverter.export(showGrid, "結合_" + time
				+ ".pdf", {
			scaleMode : wijmo.grid.pdf.ScaleMode.ActualSize,
			maxPages : 10,
			styles : {
				cellStyle : {
					backgroundColor : '#ffffff',
					borderColor : '#c6c6c6'
				},
				headerCellStyle : {
					backgroundColor : '#eaeaea'
				}
			},
			documentOptions : {
				info : {
					title : 'Sample'
				}
			}
		});
	}

	// テキスト出力
	function outPutTxt(grid) {
		$(document).ready(
				function() {
					var now = new Date().toDateString();
					var title = "No." + "	" + "申請状態" + "	" + "処理区分" + "	"
							+ "セレクトコード名称*" + "	" + "分類コード名称" + "	" + "BLコード名称"
							+ "	" + "カーコード" + "	" + "純正品番" + "	" + "種別コード"
							+ "	" + "表示順位" + "	" + "優良品番" + "	" + "QTY" + "	"
							+ "規格/特記" + "	" + "規格/特記（一般）" + "	" + "削除依頼区分"
							+ "	" + "削除理由" + "	" + "作成日時" + "	" + "更新日時" + "	"
							+ "適用日時" + "	" + "チェック区分" + "	" + "BL登録区分" + "	"
							+ "エラー区分" + "	" + "エラー内容\r\n"
					var rows = grid.rows;
					for (var i = 0; i < rows.length; i++) {
						title += rows[i]._data.no + "	";
						title += rows[i]._data.applyCondition + "	"
						title += rows[i]._data.manageKbn + "	"
						title += rows[i]._data.prmSetDtlNo1 + "	"
						title += rows[i]._data.goodsMGroup + "	"
						title += rows[i]._data.tbsPartsCode + "	"
						title += rows[i]._data.joinSourceMakerCode + "	"
						title += rows[i]._data.joinSourPartsNoWithH + "	"
						title += rows[i]._data.prmSetDtlNo2 + "	"
						title += rows[i]._data.joinDispOrder + "	"
						title += rows[i]._data.joinDestPartsNo + "	"
						title += rows[i]._data.joinQty + "	"
						title += rows[i]._data.joinSpecialNote + "	"
						title += rows[i]._data.primePartsSpecialNoteC + "	"
						title += rows[i]._data.deleteFlg + "	"
						title += rows[i]._data.deleteReason + "	"
						title += rows[i]._data.insDtTime + "	"
						title += rows[i]._data.updDtTime + "	"
						title += rows[i]._data.startTime + "	"
						title += rows[i]._data.checkFlg + "	"
						title += rows[i]._data.blEntryFlg + "	"
						title += rows[i]._data.errorDetail + "\r\n";

					}
					var blob = new Blob([ title ], {
						type : "text/plain;charset=utf-8"
					});
					saveAs(blob, "結合_" + time + ".tsv");
				});
	}

	// bootstrapのdatetimepickerJp
	$(".apply-date input").datetimepickerJp();
	$(".insert-date input").datetimepickerJp();
	$(".update-date input").datetimepickerJp();
	// 「クリア」ボタンのクリック
	$('#clear').click(function() {
		clear()
	});

	function clear() {
		$("#tbsPartsCode").val("");
		$("#joinSourPartsNoWithH").val("");
		$("#joinDestPartsNo").val("");
		if (mode == 0) {
			$("#applyCondition").val("9");
		} else {
			$("#applyCondition").val("0");
		}
		$("#manageKbn").val("9");
		$("#errorFlg").val("9");
		$("#prmSetDtlNo1").val("");
		$("#joinSourceMakerCode").val("");
		$("#startTimeStart").val("");
		$("#startTimeEnd").val("");
		$("#goodsMGroup").val("");
		$("#prmSetDtlNo2").val("");
		$("#insDtTimeStart").val("");
		$("#insDtTimeEnd").val("");
		$("#joinSpecialNote").val("");
		$("#deleteFlg").val("9");
		$("#updDtTimeStart").val("");
		$("#updDtTimeEnd").val("");
		$("#primePartsSpecialNoteC").val("");
	}

	// モード
	var mode = sessionStorage.getItem("joinList") == null ? BLENUM.ModeEnum.New
			: sessionStorage.getItem("joinList");
	var requestFlag = false;
	var title = $('#title');
	switch (parseInt(mode)) {
	// 検索入力モード
	case BLENUM.ModeEnum.New:
		$("#certain").hide();
		$("#save")[0].innerHTML = "保存";
		break;
	// 結合選択モード
	case BLENUM.ModeEnum.Update:
		title.html(title.html().replace(/結合一覧/ig, '結合一覧（選択）'));
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
		title.html(title.html().replace(/結合一覧/ig, '結合一覧（エラー修正）'));
		break;
	default:
		layer.alert('モードはエラーです。', {
			title : '',
			closeBtn : 0,
			btn : [ 'はい' ]
		});
		break;
	}

	// / headerLink
	var nabigeshon = new BLUI.Nabigeshon();
	var importKbn = sessionStorage.importModelMenu;
	if (mode == BLENUM.ModeEnum.New) {
		nabigeshon.getData(3);
	} else if (mode == BLENUM.ModeEnum.Readonly) {
		if (importKbn == "0") {
			nabigeshon.getData(35);
		} else if (importKbn == "1") {
			nabigeshon.getData(27);
		}
	} else if (mode == BLENUM.ModeEnum.Update) {
		nabigeshon.getData(28);
	} else if (mode = BLENUM.ModeEnum.Error) {
		if (importKbn == "0") {
			nabigeshon.getData(26);
		} else if (importKbn == "1") {
			nabigeshon.getData(36);
		}
	}
	
	var check = function() {
		if(requestFlag)
		{
		    return;	
		}
		else
		{
			requestFlag = true;
		}
		var accessURL = baseurl + "/joinlist/check";
		var form = {
			mode : mode,
			joinGridList : flexGrid.itemsSource.items
		}
		$.blAjax({
			url : accessURL,
			type : 'POST',
			dataType : 'Json',
			data : JSON.stringify(form),
			contentType : 'application/Json',
			success : function(data) {
				collectionView = new wijmo.collections.CollectionView(
						data.gridData);
                flexGrid.itemsSource = collectionView;
                flexGrid.refresh;
                requestFlag = false;
			},
			error : function(data, httpStatus, errorHandler) {
				requestFlag = false;
				errorHandler(data, httpStatus);
			}
		});
	};

	var collectionView = null, pagerButtons = null;
	var accessURL = baseurl + "/joinlist/joinList";
	var blStr = "";
	var selectStr = "9999：指定無し";
	var kindCodeStr = "";
	var carmakerCodeStr = "";
	var message = [];
	var controlFlag;
	window.pageNo = 1;
	window.isPage = true;
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
					type : "POST",
					dataType : 'Json',
					data : JSON.stringify(form),
					contentType : 'application/Json',
					success : function(data) {
						controlFlag = data.controlFlag;
						if (!controlFlag) {
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
							mode = 2;
						}
						message = data.messageMap;
						if (mode == 2 || mode == 3) {
							$('#applyCondition')[0].options.length = 0;
							for ( var key in data.applyConditionMap) {
								$('#applyCondition')[0].options.add(new Option(
										data.applyConditionMap[key], key));
							}
						} else if (mode == 1) {
							$('#applyCondition')[0].options.length = 0;
							$('#applyCondition')[0].options.add(new Option(
									"全データ表示", 9));
							for ( var key in data.applyConditionMap) {
								$('#applyCondition')[0].options.add(new Option(
										data.applyConditionMap[key], key));
							}
							$('#applyCondition')[0].value = 0;
						}
						$('#tbsPartsCode').focus();
						var obj = $('#tbsPartsCode')[0];
						for ( var key in data.blCodeNameMap) {
							obj.add(new Option(data.blCodeNameMap[key], key));
							blStr = blStr + "," + data.blCodeNameMap[key];
						}
						var obj = $('#prmSetDtlNo1')[0];
						for ( var key in data.selectCodeNameMap) {
							obj
									.add(new Option(
											data.selectCodeNameMap[key], key));
							selectStr = selectStr + ","
									+ data.selectCodeNameMap[key];
						}
						var obj = $('#prmSetDtlNo2')[0];
						for ( var key in data.kindCodeNameMap) {
							obj.add(new Option(data.kindCodeNameMap[key], key));
							kindCodeStr = kindCodeStr + ","
									+ data.kindCodeNameMap[key];
						}
						var obj = $('#joinSourceMakerCode')[0];
						for ( var key in data.carmakerNameMap) {
							obj.add(new Option(data.carmakerNameMap[key], key));
							carmakerCodeStr = carmakerCodeStr + ","
									+ data.carmakerNameMap[key];
						}
						if (data.gridData == undefined
								|| data.gridData.length == 0|| !controlFlag) {
							addControl();
							if (controlFlag) {
								layer.alert(message.E00008, {
									title : '',
									closeBtn : 0,
									btn : [ 'はい' ]
								});
							}
						} else {
							removeControl();
						}
						var gridData = [];
						if (data.gridData != undefined
								&& data.gridData.length != 0) {
							gridData = data.gridData;
							for (var i = 0; i < gridData.length; i++) {
								gridData[i].no = i + 1;
							}
							$(".searchNum").text(data.searchNum + '/');
							$(".allNum").text(data.allNum);
							$('.historyrows').text(data.maxRows);
						}
						if (window.isPage) {
							paging(data.searchNum, data.maxRows);
						}
						collectionView = new wijmo.collections.CollectionView(
								gridData);
						if (mode == 1 || mode == 3) {
							init(0);
						} else {
							init(1);
						}
					},
					error : function(data, httpStatus, errorHandler) {
						errorHandler(data, httpStatus);
					}
				});
	}
	;

	// 「検索」ボタン
	$('#search').click(function() {
		if(!searchBeforeCheck()) {
			return;
		}
		if (isGridOut()) {
			layer.confirm(message.Q00001, {
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
	// 「取消」ボタン
	$('#cancle').click(function() {
		if (window.isInit) {
			gridInit();
		} else {
			search();
		}
	});
	
	function searchBeforeCheck() {
		// 適用日
		if (!checkDateduration($('#startTimeStart').val(), $('#startTimeEnd').val())) {
			layer.alert("開始適用日が終了適用日より前になるように設定してください。", {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
			return false
		}
		// 作成日
		if (!checkDateduration($('#insDtTimeStart').val(), $('#insDtTimeEnd').val())) {
			layer.alert("開始作成日が終了作成日より前になるように設定してください。", {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
			return false
		}
		// 更新日
		if (!checkDateduration($('#updDtTimeStart').val(), $('#updDtTimeEnd').val())) {
			layer.alert("開始更新日が終了更新日より前になるように設定してください。", {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
			return false
		}
		return true;
	}
	
	function searhJoinWork() {
		var accessURL = baseurl + "/joinlist/searchJoinWork";
		var form = {
			tbsPartsCode : $('#tbsPartsCode').val(),
			joinSourPartsNoWithH : $('#joinSourPartsNoWithH').val(),
			joinDestPartsNo : $('#joinDestPartsNo').val(),
			applyCondition : $('#applyCondition').val(),
			manageKbn : $('#manageKbn').val(),
			errorFlg : $('#errorFlg').val(),
			prmSetDtlNo1 : $('#prmSetDtlNo1').val(),
			joinSourceMakerCode : $('#joinSourceMakerCode').val(),
			startTimeStart : $('#startTimeStart').val(),
			startTimeEnd : $('#startTimeEnd').val(),
			goodsMGroup : $('#goodsMGroup').val(),
			prmSetDtlNo2 : $('#prmSetDtlNo2').val(),
			insDtTimeStart : $('#insDtTimeStart').val(),
			insDtTimeEnd : $('#insDtTimeEnd').val(),
			joinSpecialNote : $('#joinSpecialNote').val(),
			deleteFlg : $('#deleteFlg').val(),
			updDtTimeStart : $('#updDtTimeStart').val(),
			updDtTimeEnd : $('#updDtTimeEnd').val(),
			primePartsSpecialNoteC : $('#primePartsSpecialNoteC').val(),
			order : $("#order").val(),
			pageNo : window.pageNo,
			mode : mode,
			importKbn : sessionStorage.getItem("importModelMenu")
		};
		$.blAjax({
			url : accessURL,
			type : "POST",
			data : JSON.stringify(form),
			error : function(data, httpStatus, errorHandler) {
				errorHandler(data, httpStatus);
			},
			success : function(data) {
			},
		});
	}
	// 「保存」ボタン
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
		layer.confirm(message.Q00002, {
			title : false,
			btn : [ 'はい', 'いいえ' ]
		}, saveaction)
	});

	function saveaction() {
		searhJoinWork();
		var accessURL = baseurl + "/joinlist/save";
		var form = {
			mode : mode,
			joinGridList : flexGrid.itemsSource.items
		}
		$.blAjax({
			url : accessURL,
			type : 'POST',
			dataType : 'Json',
			data : JSON.stringify(form),
			contentType : 'application/Json',
			success : function(data) {
				collectionView = new wijmo.collections.CollectionView(
						data.gridDataRefresh);
				if (!data.isError) {
					if(mode!=3)
						{
						layer.alert(message.I00001, {
							title : '',
							closeBtn : 0,
							btn : [ 'はい' ]
						});
						}
					else
					{
						layer.confirm(message.I00001, {
							icon : 3,
							title : '',
							closeBtn : 0,
							btn : [ 'はい']
						}, function() {
							window.location.href = "../importresult/importresult";
						})
					}
					window.pageNo = 1;
					window.isPage = true;
					if (window.isInit) {
						gridInit();
					} else {
						search();
					}
				} else {
					layer.alert(getMessageInfo(message.E00018), {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
					if (mode == 1 || mode == 3) {
						init(0);
					} else {
						init(1);
					}
					if (data.gridDataUpdate.length == 0) {
						addControl();
					} else {
						removeControl();
					}
				}
			},
			error : function(data, httpStatus, errorHandler) {
				errorHandler(data, httpStatus);
			}
		});
	}
	$('#certain').click(function() {
		certain(true);
    });
	
	function certain(isCertain)
	{
		var accessURL = baseurl + "/joinlist/certain";
		var form = {
			joinGridList : flexGrid.itemsSource.items
		}
		$.blAjax({
			url : accessURL,
			type : 'POST',
			dataType : 'Json',
			data : JSON.stringify(form),
			contentType : 'application/Json',
			success : function(data) {
				if (data.selectSize == 0&&isCertain) {
					layer.alert(message.E00014, {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
					return;
				}
				window.location.href = "../applycommon/applyCommon";
			},
			error : function(data, httpStatus, errorHandler) {
				errorHandler(data, httpStatus);
			}
		});
	}
	// 表示順の変化
	$("#order").change(function() {
		if (isGridOut()) {
			layer.confirm(message.Q00001, {
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
	var searchResult;
	// 検索
	function search(index) {
		layer.close(index);
		var accessURL = baseurl + "/joinlist/search";
		var form = {
			tbsPartsCode : $('#tbsPartsCode').val(),
			joinSourPartsNoWithH : $('#joinSourPartsNoWithH').val(),
			joinDestPartsNo : $('#joinDestPartsNo').val(),
			applyCondition : $('#applyCondition').val(),
			manageKbn : $('#manageKbn').val(),
			errorFlg : $('#errorFlg').val(),
			prmSetDtlNo1 : $('#prmSetDtlNo1').val(),
			joinSourceMakerCode : $('#joinSourceMakerCode').val(),
			startTimeStart : $('#startTimeStart').val(),
			startTimeEnd : $('#startTimeEnd').val(),
			goodsMGroup : $('#goodsMGroup').val(),
			prmSetDtlNo2 : $('#prmSetDtlNo2').val(),
			insDtTimeStart : $('#insDtTimeStart').val(),
			insDtTimeEnd : $('#insDtTimeEnd').val(),
			joinSpecialNote : $('#joinSpecialNote').val(),
			deleteFlg : $('#deleteFlg').val(),
			updDtTimeStart : $('#updDtTimeStart').val(),
			updDtTimeEnd : $('#updDtTimeEnd').val(),
			primePartsSpecialNoteC : $('#primePartsSpecialNoteC').val(),
			order : $("#order").val(),
			pageNo : window.pageNo,
			mode : mode,
			importKbn : sessionStorage.getItem("importModelMenu")
		}
		$.blAjax({
			url : accessURL,
			type : "POST",
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
				searchResult = data.gridData;
				for (var i = 0; i < searchResult.length; i++) {
					searchResult[i].no = i + 1;
				}
				if (searchResult.length == 0) {
					addControl();
					layer.alert(message.E00008, {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
				} else {
					removeControl();
				}
				collectionView = new wijmo.collections.CollectionView(
						searchResult);
				if (mode == 1 || mode == 3) {
					init(0);
				} else {
					init(1);
				}
			},
			error : function(data, httpStatus, errorHandler) {
				errorHandler(data, httpStatus);
			}
		});
	}

	//
	function addControl() {
		$('.btn-del').addClass('disabled');
		$('.btn-copy').addClass('disabled');
		$('.btn-paste').addClass('disabled');
		$('.replaceGuide').attr("disabled", true);
		$('.btn_detail').attr("disabled", true);
	}

	function removeControl() {
		$('.btn-del').removeClass('disabled');
		$('.btn-copy').removeClass('disabled');
		$('.btn-paste').removeClass('disabled');
		$('.replaceGuide').attr("disabled", false);
		$('.btn_detail').attr("disabled", false);
	}

	// グリッドの初期化
	function init(e) {
		var grid_col_defs;
		if (e == 0) {
			grid_col_defs = {
				check : {
					header : '',
					binding : 'check',
					dataType : wijmo.DataType.Boolean,
					width : 50
				},
				no : {
					header : 'No.',
					binding : 'no',
					isReadOnly : true,
					width : 50
				},
				applyCondition : {
					header : '申請状態',
					binding : 'applyCondition',
					isReadOnly : true,
					width : 80
				},
				manageKbn : {
					header : '処理区分',
					binding : 'manageKbn',
					isReadOnly : true,
					width : 80
				},
				prmSetDtlNo1 : {
					header : 'セレクトコード名称',
					binding : 'prmSetDtlNo1',
					width : 180
				},
				goodsMGroup : {
					header : '分類コード名称',
					binding : 'goodsMGroup',
					isReadOnly : true,
					width : 200
				},
				tbsPartsCode : {
					header : 'BLコード名称',
					binding : 'tbsPartsCode',
					width : 220
				},
				joinSourceMakerCode : {
					header : 'カーコード名称',
					binding : 'joinSourceMakerCode',
					width : 130
				},
				joinSourPartsNoWithH : {
					header : '純正品番',
					binding : 'joinSourPartsNoWithH',
					isReadOnly : true,
					width : 140
				},
				prmSetDtlNo2 : {
					header : '種別コード名称',
					binding : 'prmSetDtlNo2',
					width : 130
				},
				joinDispOrder : {
					header : '表示順位',
					binding : 'joinDispOrder',
					dataType : wijmo.DataType.Number,
					width : 80,
					align:'right'
				},
				joinDestPartsNo : {
					header : '優良品番',
					binding : 'joinDestPartsNo',
					isReadOnly : true,
					width : 140
				},
				joinQty : {
					header : 'QTY',
					binding : 'joinQty',
					width : 50,
					dataType : "Number",
					align:'right'
				},
				joinSpecialNote : {
					header : '規格/特記',
					binding : 'joinSpecialNote',
					width : 120
				},
				primePartsSpecialNoteC : {
					header : '規格/特記（一般）',
					binding : 'primePartsSpecialNoteC',
					width : 180
				},
				deleteFlg : {
					header : '削除依頼区分',
					binding : 'deleteFlg',
					width : 120
				},
				deleteReason : {
					header : '削除理由',
					binding : 'deleteReason',
					width : 230
				},
				insDtTime : {
					header : '作成日時',
					binding : 'insDtTime',
					dataType : wijmo.DataType.Date,
					isReadOnly : true,
					width : 150
				},
				updDtTime : {
					header : '更新日時',
					binding : 'updDtTime',
					dataType : wijmo.DataType.Date,
					isReadOnly : true,
					width : 150
				},
				startTime : {
					header : '適用日時',
					binding : 'startTime',
					dataType : wijmo.DataType.dateTime,
					width : 150
				},
				checkFlg : {
					header : 'チェック区分',
					binding : 'checkFlg',
					isReadOnly : true,
					width : 100
				},
				blEntryFlg : {
					header : 'BL登録区分',
					binding : 'blEntryFlg',
					isReadOnly : true,
					width : 100
				},
				errorFlg : {
					header : 'エラー区分',
					binding : 'errorFlg',
					isReadOnly : true,
					width : 100
				},
				errorDetail : {
					header : 'エラー内容',
					binding : 'errorDetail',
					isReadOnly : true,
					width : 220
				}
			};
		} else {
			grid_col_defs = {
				no : {
					header : 'No.',
					binding : 'no',
					isReadOnly : true,
					width : 50
				},
				applyCondition : {
					header : '申請状態',
					binding : 'applyCondition',
					isReadOnly : true,
					width : 80
				},
				manageKbn : {
					header : '処理区分',
					binding : 'manageKbn',
					isReadOnly : true,
					width : 80
				},
				prmSetDtlNo1 : {
					header : 'セレクトコード名称',
					binding : 'prmSetDtlNo1',
					width : 180
				},
				goodsMGroup : {
					header : '分類コード名称',
					binding : 'goodsMGroup',
					isReadOnly : true,
					width : 200
				},
				tbsPartsCode : {
					header : 'BLコード名称',
					binding : 'tbsPartsCode',
					width : 220
				},
				joinSourceMakerCode : {
					header : 'カーコード名称',
					binding : 'joinSourceMakerCode',
					width : 130
				},
				joinSourPartsNoWithH : {
					header : '純正品番',
					binding : 'joinSourPartsNoWithH',
					isReadOnly : true,
					width : 140
				},
				prmSetDtlNo2 : {
					header : '種別コード名称',
					binding : 'prmSetDtlNo2',
					width : 130
				},
				joinDispOrder : {
					header : '表示順位',
					binding : 'joinDispOrder',
					width : 80,
					align:'right'
				},
				joinDestPartsNo : {
					header : '優良品番',
					binding : 'joinDestPartsNo',
					isReadOnly : true,
					width : 140
				},
				joinQty : {
					header : 'QTY',
					binding : 'joinQty',
					width : 50,
					dataType : 'Number'
				},
				joinSpecialNote : {
					header : '規格/特記',
					binding : 'joinSpecialNote',
					width : 120
				},
				primePartsSpecialNoteC : {
					header : '規格/特記（一般）',
					binding : 'primePartsSpecialNoteC',
					width : 180
				},
				deleteFlg : {
					header : '削除依頼区分',
					binding : 'deleteFlg',
					width : 120
				},
				deleteReason : {
					header : '削除理由',
					binding : 'deleteReason',
					width : 230
				},
				insDtTime : {
					header : '作成日時',
					binding : 'insDtTime',
					dataType : wijmo.DataType.Date,
					isReadOnly : true,
					width : 150
				},
				updDtTime : {
					header : '更新日時',
					binding : 'updDtTime',
					dataType : wijmo.DataType.Date,
					isReadOnly : true,
					width : 150
				},
				startTime : {
					header : '適用日時',
					binding : 'startTime',
					dataType : wijmo.DataType.dateTime,
					width : 150
				},
				checkFlg : {
					header : 'チェック区分',
					binding : 'checkFlg',
					isReadOnly : true,
					width : 100
				},
				blEntryFlg : {
					header : 'BL登録区分',
					binding : 'blEntryFlg',
					isReadOnly : true,
					width : 100
				},
				errorFlg : {
					header : 'エラー区分',
					binding : 'errorFlg',
					isReadOnly : true,
					width : 100
				},
				errorDetail : {
					header : 'エラー内容',
					binding : 'errorDetail',
					isReadOnly : true,
					width : 220
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
		gridConfig.selectionMode = wijmo.grid.SelectionMode.Cell;
		flexGrid.initialize(gridConfig);
		flexGrid.viewData = {};
		flexGrid.viewData.blStr = blStr;
		flexGrid.viewData.selectStr = selectStr;
		flexGrid.viewData.kindCodeStr = kindCodeStr;
		flexGrid.viewData.carmakerCodeStr = carmakerCodeStr;

		flexGrid.formatItem
				.addHandler(function(s, e) {
					if (e.panel.cellType == wijmo.grid.CellType.Cell
							&& (getColumnBinding(e.col) == grid_col_defs.checkFlg.binding
									|| getColumnBinding(e.col) == grid_col_defs.applyCondition.binding
									|| getColumnBinding(e.col) == grid_col_defs.manageKbn.binding
									|| getColumnBinding(e.col) == grid_col_defs.blEntryFlg.binding
									|| getColumnBinding(e.col) == grid_col_defs.no.binding || getColumnBinding(e.col) == grid_col_defs.errorFlg.binding)) {
						e.cell.style.textAlign = 'center';
					}
					if (e.panel.cellType == wijmo.grid.CellType.Cell
							&& (getColumnBinding(e.col) == grid_col_defs.no.binding
									|| getColumnBinding(e.col) == grid_col_defs.applyCondition.binding
									|| getColumnBinding(e.col) == grid_col_defs.manageKbn.binding
									|| getColumnBinding(e.col) == grid_col_defs.insDtTime.binding
									|| getColumnBinding(e.col) == grid_col_defs.updDtTime.binding
									|| getColumnBinding(e.col) == grid_col_defs.errorFlg.binding
									|| getColumnBinding(e.col) == grid_col_defs.errorDetail.binding
									|| getColumnBinding(e.col) == grid_col_defs.checkFlg.binding || getColumnBinding(e.col) == grid_col_defs.blEntryFlg.binding)) {
						$(e.cell).addClass('c-cell');
					}
					if (e.panel.cellType == wijmo.grid.CellType.ColumnHeader
							&& (mode == 0 || mode == 3) && controlFlag
							&& (getColumnBinding(e.col) == grid_col_defs.prmSetDtlNo1.binding
									|| getColumnBinding(e.col) == grid_col_defs.goodsMGroup.binding
									|| getColumnBinding(e.col) == grid_col_defs.tbsPartsCode.binding
									|| getColumnBinding(e.col) == grid_col_defs.joinSourPartsNoWithH.binding
									|| getColumnBinding(e.col) == grid_col_defs.joinSourceMakerCode.binding
									|| getColumnBinding(e.col) == grid_col_defs.joinDestPartsNo.binding
									|| getColumnBinding(e.col) == grid_col_defs.prmSetDtlNo2.binding
									|| getColumnBinding(e.col) == grid_col_defs.joinDispOrder.binding || getColumnBinding(e.col) == grid_col_defs.startTime.binding)) {
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
					
					if(e.panel.cellType == wijmo.grid.CellType.Cell
							&& getColumnBinding(e.col) == grid_col_defs.startTime.binding){
						$(e.cell).addClass('startTime');
					}
					
					// 全検索チェックボックスの追加
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
							e.cell.innerHTML = '<input type="checkbox"> '
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
				});
		
		//　適用日時のカレンーダを追加
		$(".startTime").datetimepickerJp({
			format : 'YYYY/MM/DD HH:mm'
		});
		
		// 結合選択モードの制御
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
		
		// エラーチェック
		if (mode == 0||mode==3) {
			flexGrid.formatItem
					.addHandler(function(s, e) {
						var cv = flexGrid.collectionView;
						var count = cv.totalItemCount;
						for (var i = 0; i < count; i++) {
							var flag = cv.items[i].errorFlag;
							var errorFlag = new Array();
							if (flag != null) {
								errorFlag = flag.split(",");
								for (var j = 0; j < errorFlag.length; j++) {
									if(e.panel.cellType == wijmo.grid.CellType.Cell)
								     {
									if (((errorFlag[j] == "0"&&getColumnBinding(e.col) == grid_col_defs.joinDestPartsNo.binding)||
											(errorFlag[j] == "1"&&getColumnBinding(e.col) == grid_col_defs.joinSourPartsNoWithH.binding)||
											(errorFlag[j] == "2"&&getColumnBinding(e.col) == grid_col_defs.joinDispOrder.binding)||
											(errorFlag[j] == "3"&&getColumnBinding(e.col) == grid_col_defs.joinSourceMakerCode.binding))&& e.row == i) {
										e.cell.classList.add('null-cell');
									}
									}
								}
							}
						}
					});
		}
		// ガイドボタンの実現デモ
		flexGrid.formatItem
				.addHandler(function(s, e) {
					if (e.panel.cellType == wijmo.grid.CellType.Cell) {
						var editRange = flexGrid.editRange;
						var row = e.row;
						var col = e.col;
						var isEdit = editRange && editRange.row == row
								&& editRange.col == col; // 編集モード
						var items = flexGrid.itemsSource.items;
						// 分類コードのガイド
						if (getColumnBinding(e.col) == grid_col_defs.goodsMGroup.binding) {
							wijmo.addClass(e.cell, 'guide-code-button-cell');
							// ガイドボタンセルを作成
							var button = createCodeGuideButton(row, col);
							if (mode == 1 || mode == 2 ||!controlFlag) {
								button.disabled = "disabled"
								button.style.display = 'none';
							}
							if (items[row].applyCondition == '中'&&mode!=3) {
								button.disabled = "disabled";
							}
							if (isEdit) {
								button.style.display = 'block';
								e.cell.appendChild(button);
							} else {
								e.cell.appendChild(button);
							}
						}
						// 商品ガイド(純正品番)
						if (getColumnBinding(e.col) == grid_col_defs.joinSourPartsNoWithH.binding) {
							wijmo.addClass(e.cell, 'guide-code-button-cell');
							// ガイドボタンセルを作成
							var button = createJoinSourPartsNoWithHGuideButton(
									row, col);
							if (mode == 1 || mode == 2||!controlFlag) {
								button.disabled = "disabled"
								button.style.display = 'none';
							}
							if ((items[row].hiddenArea == 1||items[row].hiddenArea == 2
									|| items[row].hiddenArea == 3)&&mode!=3) {
								button.disabled = "disabled";
							}
							if (isEdit) {
								button.style.display = 'block';
								e.cell.appendChild(button);
							} else {
								e.cell.appendChild(button);
							}
						}
						// 商品ガイド(優良品番)
						if (getColumnBinding(e.col) == grid_col_defs.joinDestPartsNo.binding) {
							wijmo.addClass(e.cell, 'guide-code-button-cell');
							// ガイドボタンセルを作成
							var button = createJoinDestPartsNoButton(row, col);
							if (mode == 1 || mode == 2||!controlFlag) {
								button.disabled = "disabled"
								button.style.display = 'none';
							}
							if (items[row].applyCondition == '中'&&mode!=3) {
								button.disabled = "disabled";
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

		// tooltip
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
									placement : 'auto right', // FIXME:TooltipのPosition問題
									title : gridCellTooltipGetTitle
								});
								cell.tooltip('show');

								$('.tooltip[role=tooltip]')
										.on(
												'mousemove mouseleave',
												function(event) {
													gridCellTooltipMouseMoveHandler(event);
												}).on(
												'click',
												function(event) {
													var ht = flexGrid.hitTest(
															event.pageX,
															event.pageY);
													gotoEditingCell(ht.row,
															ht.col);
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
					var accessURL = baseurl + '/joinlist/goodsMGroup';
					var actionType = 'POST';
					var goodsMGroupStr = "";
					if (flexGrid.getCellData(row, col) != undefined) {
						goodsMGroupStr = flexGrid.getCellData(row, col).split(
								':')[0];
					}
					var form = {
						goodsMGroup : goodsMGroupStr
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

		// 商品ガイド(純正品番)ボタンを作成
		function createJoinSourPartsNoWithHGuideButton(row, col) {
			var button = document.createElement('button');
			wijmo.addClass(button, 'glyphicon glyphicon-star-empty');
			var openGuide = function(e) {
				try {
					// 品番
					var accessURL = baseurl + '/joinlist/joinSourPartsNoWithH';
					var actionType = 'POST';
					var joinSourPartsNoWithHStr = "";
					if (flexGrid.getCellData(row, col) != undefined) {
						joinSourPartsNoWithHStr = flexGrid.getCellData(row, col).split(
								':')[0];
					}
					var form = {
						joinSourPartsNoWithH : joinSourPartsNoWithHStr
					};
					$.blAjax({
						url : accessURL,
						type : actionType,
						data : JSON.stringify(form),
						success : function(data) {
							var guide = new BLUI.GoodsGuide();
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

		// 商品ガイド(優良品番)ボタンを作成
		function createJoinDestPartsNoButton(row, col) {
			var button = document.createElement('button');
			wijmo.addClass(button, 'glyphicon glyphicon-star-empty');
			var openGuide = function(e) {
				try {
					// 品番
					var accessURL = baseurl + '/joinlist/joinDestPartsNo';
					var actionType = 'POST';
					var joinDestPartsNoStr = "";
					if (flexGrid.getCellData(row, col) != undefined) {
						joinDestPartsNoStr = flexGrid.getCellData(row, col).split(
								':')[0];
					}
					var form = {
						// 優良品番
						joinDestPartsNo : joinDestPartsNoStr,
					};
					$.blAjax({
						url : accessURL,
						type : actionType,
						data : JSON.stringify(form),
						success : function(data) {
							var guide = new BLUI.GoodsGuide();
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
		// Gridのセルフォーマット用
		var gridItemFormatter = function(panel, r, c, cell) {
			var gridItem = panel.grid;
			var editRange = flexGrid.editRange;

			if (!(editRange && panel.cellType == wijmo.grid.CellType.Cell
					&& editRange.row == r && editRange.col == c)) {
				return;
			}
			var oldData;
			// 入力制御 長さ制限と数字入力制限
			flexGrid.prepareCellForEdit.addHandler(function(s,e){
                 var binding = getColumnBinding(e.col);
                 //規格/特記 規格/特記(一般)/削除理由
                 if(binding == 'joinSpecialNote' || binding == 'primePartsSpecialNoteC' || binding == 'deleteReason'){
                     $(flexGrid.activeEditor).attr('maxlength', 80);
                 }
                 if(binding == 'startTime'){
                	 $(flexGrid.activeEditor).alphanum({
                     	allow              : '/',
                     	allowSpace         : false,
                     	allowNumeric       : true,
                     	allowLatin         : false,
                 		maxLength          : 10    
                     });
                	 oldData = $(flexGrid.activeEditor).val();
                 }
                 // QTY
                 if(binding == 'joinQty'){
                	 $(flexGrid.activeEditor).attr("id", "joinQty");

                	 $(flexGrid.activeEditor).attr("oninput", "doubleInputForQTY(this.id)");
                 }
             });
			
			var columnName = gridItem.columns[c].binding;
			// 純正品番
			if (columnName == grid_col_defs.joinSourPartsNoWithH.binding) {
				$(gridItem.activeEditor).alphanum({
					allow: '-',
					allowSpace : false,
					allowOtherCharSets : false,
					allowNumeric : true,
					maxLength : 24
				});
			}
			// 優良品番
			if (columnName == grid_col_defs.joinDestPartsNo.binding) {
				$(gridItem.activeEditor).alphanum({
					allow: '-',
					allowSpace : false,
					allowOtherCharSets : false,
					allowNumeric : true,
					maxLength : 24
				});
			}
			// 分類コード
			if (columnName == grid_col_defs.goodsMGroup.binding) {
				$(gridItem.activeEditor).numeric({
					allowDecSep : false,
					max : 9999,
					min : 0
				});
			}
			// 表示順位
			if (columnName == grid_col_defs.joinDispOrder.binding) {
				$(gridItem.activeEditor).numeric({
					allowDecSep : false,
					max : 9999,
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
			if (colBinding == grid_col_defs.tbsPartsCode.binding) { // BLcode列
				createGridComboBoxCell(r, c, cell, blStr.split(","));
			} else if (colBinding == grid_col_defs.deleteFlg.binding) { // 削除依頼列
				createGridComboBoxCell(r, c, cell, [ '', '削除する' ]);
			} else if (colBinding == grid_col_defs.prmSetDtlNo1.binding) { // セレクトコード列
				createGridComboBoxCell(r, c, cell, selectStr.split(","));
			} else if (colBinding == grid_col_defs.prmSetDtlNo2.binding) { // 種別コード列
				createGridComboBoxCell(r, c, cell, kindCodeStr.split(","));
			} else if (colBinding == grid_col_defs.joinSourceMakerCode.binding) { // カーコード列
				createGridComboBoxCell(r, c, cell, carmakerCodeStr.split(","));
			}
		};
		var createGridComboBoxCell = function(r, c, cell, itemsSource,
				classList) {
			// classList
			if (classList) {
				cell.innerHTML = '<div id="grid_combox" class="' + classList
						+ '"></div>';
			} else {
				cell.innerHTML = '<div id="grid_combox"></div>';
			}
			var comboBox = new wijmo.input.ComboBox('#grid_combox');

			comboBox.isEditable = false;
			comboBox.required = false;
			// itemsource コンボボックスのデータをセット
			comboBox.itemsSource = itemsSource;
			// text 今のテキストをセット
			comboBox.text = flexGrid.getCellData(r, c);
			comboBox.textChanged.addHandler(function(e) {
				$(flexGrid.activeEditor).val(comboBox.selectedValue);
			});

			// コンボボックスのクリック時の状態をセット
			var gridComboxClickHandler = function(e) {
				if (comboBox.isDroppedDown) {
					comboBox.isDroppedDown = false;
				} else {
					comboBox.isDroppedDown = true;
				}
				e.stopImmediatePropagation();
				e.preventDefault();
			};
			// inputElementは入力対象です。
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
					&& (col == getColumnIndex(grid_col_defs.prmSetDtlNo1.binding)
							|| col == getColumnIndex(grid_col_defs.goodsMGroup.binding)
							|| col == getColumnIndex(grid_col_defs.tbsPartsCode.binding)
							|| col == getColumnIndex(grid_col_defs.joinSourceMakerCode.binding)
							|| col == getColumnIndex(grid_col_defs.joinSourPartsNoWithH.binding)
							|| col == getColumnIndex(grid_col_defs.prmSetDtlNo2.binding)
							|| col == getColumnIndex(grid_col_defs.joinDispOrder.binding)
							|| col == getColumnIndex(grid_col_defs.joinDestPartsNo.binding)
							|| col == getColumnIndex(grid_col_defs.joinQty.binding)
							|| col == getColumnIndex(grid_col_defs.joinSpecialNote.binding)
							|| col == getColumnIndex(grid_col_defs.primePartsSpecialNoteC.binding)
							|| col == getColumnIndex(grid_col_defs.deleteFlg.binding)
							|| col == getColumnIndex(grid_col_defs.deleteReason.binding)
							|| col == getColumnIndex(grid_col_defs.insDtTime.binding)
							|| col == getColumnIndex(grid_col_defs.updDtTime.binding)
							|| col == getColumnIndex(grid_col_defs.startTime.binding)
							|| col == getColumnIndex(grid_col_defs.checkFlg.binding)
							|| col == getColumnIndex(grid_col_defs.blEntryFlg.binding)
							|| col == getColumnIndex(grid_col_defs.errorFlg.binding) || col == getColumnIndex(grid_col_defs.errorDetail.binding))) {
				return true;
			}
			if ((items[row].hiddenArea == 1 || items[row].hiddenArea == 3|| items[row].hiddenArea == 2)
					&& (col == getColumnIndex(grid_col_defs.prmSetDtlNo1.binding)
							|| col == getColumnIndex(grid_col_defs.tbsPartsCode.binding)
							|| col == getColumnIndex(grid_col_defs.joinSourceMakerCode.binding)
							|| col == getColumnIndex(grid_col_defs.prmSetDtlNo2.binding)
							|| col == getColumnIndex(grid_col_defs.joinDispOrder.binding) || col == getColumnIndex(grid_col_defs.joinSourPartsNoWithH.binding))
					&& mode != BLENUM.ModeEnum.Error) {
				return true;
			} else if ((items[row].hiddenArea == 1 || items[row].hiddenArea == 3)
					&& (col == getColumnIndex(grid_col_defs.joinSourPartsNoWithH.binding)
							|| col == getColumnIndex(grid_col_defs.goodsMGroup.binding) || col == getColumnIndex(grid_col_defs.joinDestPartsNo.binding))) {
				return true;
			} else {
				return false;
			}
		}
		var isDelete = true;
		flexGrid.formatItem
				.addHandler(function(s, e) {
					if (e.panel.cellType == wijmo.grid.CellType.Cell) {
						// 必須項目チェック
						if (getColumnBinding(e.col) == grid_col_defs.prmSetDtlNo1.binding
								|| getColumnBinding(e.col) == grid_col_defs.prmSetDtlNo1.binding
								|| getColumnBinding(e.col) == grid_col_defs.tbsPartsCode.binding
								|| getColumnBinding(e.col) == grid_col_defs.joinSourceMakerCode.binding
								|| getColumnBinding(e.col) == grid_col_defs.joinSourPartsNoWithH.binding
								|| getColumnBinding(e.col) == grid_col_defs.prmSetDtlNo2.binding
								|| getColumnBinding(e.col) == grid_col_defs.joinDispOrder.binding
								|| getColumnBinding(e.col) == grid_col_defs.startTime.binding
								|| getColumnBinding(e.col) == grid_col_defs.joinDestPartsNo.binding
								|| getColumnBinding(e.col) == grid_col_defs.goodsMGroup.binding) {
							var value = flexGrid.getCellData(e.row, e.col,
									false);
							if (!value) {
								e.cell.classList.add('null-cell');
							}
						}
						// 削除依頼区分は削除する場合、理由は必須入力
						if (getColumnBinding(e.col) == grid_col_defs.deleteFlg.binding) {
							if (flexGrid.getCellData(e.row, e.col, false) == undefined
									|| flexGrid
											.getCellData(e.row, e.col, false) == "") {
								isDelete = false;
							} else {
								isDelete = true;
							}
						}

						if (getColumnBinding(e.col) == grid_col_defs.deleteReason.binding
								&& isDelete
								&& (flexGrid.getCellData(e.row, e.col, false) == undefined || flexGrid
										.getCellData(e.row, e.col, false) == "")) {
							e.cell.classList.add('null-cell');
							isDelete = false;
						}
					}
				});

		var edtingCell = null;
		flexGrid.beginningEdit.addHandler(function(s, e) {
			if (e.panel.cellType == wijmo.grid.CellType.Cell) {
				var items = flexGrid.collectionView.items;
				if (isReadonlyCell(e.row, e.col)) {
					// NO.16 入力不可セルの制御
					e.cancel = true;
					return;
				} else if (items[e.row]['applyCondition'] == '中') {
					layer.alert(message.E00011, {
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
					// NO.104 編集可セルのtooltipを削除する
					$('.tooltip[role=tooltip]').remove();
				}
			}
		});

        flexGrid.cellEditEnded.addHandler(function(sender, e) {
        	var binding = getColumnBinding(e.col);
			// 適用日時
        	if(binding == grid_col_defs.startTime.binding) {
        		var newData = flexGrid.getCellData(e.row, e.col);
        		if(!checkDate(newData)){
          		  layer.alert('YYYY/MM/DD形式を入力してください。', {
                        title : '',
                        closeBtn : 0,
                        btn : [ 'はい' ]
                  });
          		flexGrid.setCellData(e.row, e.col, '');
          	  } else {
          		if (newData.length <= 10) {
          			flexGrid.setCellData(e.row, e.col, newData + "　00:00");
          		}
          	  }
        		oldData = undefined;
        		newData = undefined;
        	}});
		flexGrid.cellEditEnded.addHandler(function(s, e) {
			var items = flexGrid.collectionView.items;
			if(findCell(e.row, e.col)==null){
	               if(edtingCell.value!=""&&edtingCell.row == e.row && edtingCell.col == e.col){
	            	   if (items[e.row]['hiddenArea'] == 1
	   						&& mode != BLENUM.ModeEnum.Error) {
	   					items[e.row]['hiddenArea'] = 2;
	   				   }
	   				   if (items[e.row]['hiddenArea'] == 1
	   						&& mode == BLENUM.ModeEnum.Error) {
	   				 	items[e.row]['hiddenArea'] = 4;
	   				   }
	               }
			}else{
			  if (edtingCell.row == e.row && edtingCell.col == e.col
					&& edtingCell.value != findCell(e.row, e.col).innerText) {
				if (items[e.row]['hiddenArea'] == 1
						&& mode != BLENUM.ModeEnum.Error) {
					items[e.row]['hiddenArea'] = 2;
				}
				if (items[e.row]['hiddenArea'] == 1
						&& mode == BLENUM.ModeEnum.Error) {
					items[e.row]['hiddenArea'] = 4;
				}
			  }
		    }
			if (isGridOut()) {
				sessionStorage.setItem("confirmMessage", message.Q00001);
			} else {
				sessionStorage.setItem("confirmMessage", null);
			}
			if(!requestFlag)
			{
			  check();
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

		var getColumnIndex = function(binding) {
			return flexGrid.cells.columns.getColumn(binding).index;
		}
		flexGrid.select(-1, -1);
	}

	var activeRow = -1;
	flexGrid.selectionChanged.addHandler(function(s, e) {
		var range = e.range;
		if (activeRow >= 0 && activeRow < flexGrid.rows.length) {
			flexGrid.rowHeaders.setCellData(activeRow, 0, '');
		}
		activeRow = range.row;
		flexGrid.rowHeaders.setCellData(activeRow, 0, '\uf0da');
	});

	// 破棄確認
	function isGridOut() {
		var con = false;
		var cv = flexGrid.collectionView;
		var count = cv.totalItemCount;
		for (var i = 0; i < count; i++) {
			if (cv.items[i].hiddenArea == 0 || cv.items[i].hiddenArea == 2
					|| cv.items[i].hiddenArea == 3 || cv.items[i].hiddenArea == 4) {
				con = true;
			}
		}
		return con;
	}
	;

	// 「行挿入」ボタン
	$('.btn-add').click(function() {
		if(!requestFlag)
		{
		rowAddHandler();
		}
	});
	var rowAddHandler = function() {
		if ($('.btn-add').hasClass("disabled")) {
			return;
		}
		removeControl();
		var newRowData = {};
		newRowData.hiddenArea = 0;
		newRowData.applyCondition = "未";
		newRowData.manageKbn = "追";
		newRowData.check = false;
		newRowData.checkFlg = "未";
		newRowData.blEntryFlg = "未";

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
			sessionStorage.setItem("confirmMessage", message.Q00001);
		} else {
			sessionStorage.setItem("confirmMessage", null);
		}
	};
	// 「行削除」ボタン
	$('.btn-del').click(function() {
		if(!requestFlag)
		{
		rowDeleteHandler();
		}
	});
	var rowDeleteHandler = function() {
		if ($('.btn-del').hasClass("disabled")) {
			return;
		}
		// 選択行を削除
		var rows = flexGrid.selectedRows;
		if (rows.length == 0) {
			layer.alert(message.E00010, {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
		}
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].dataItem.hiddenArea == 0) {
				flexGrid.collectionView.remove(rows[i].dataItem);
			} else {
				if (rows[i].dataItem.importKbn == 2) {
					if (rows[i].dataItem.applyCondition == "未") {
						rows[i].dataItem.hiddenArea = 3;
						rows[i].cssClass = 'd-cell';
					} else if (rows[i].dataItem.applyCondition == "中") {
						layer.alert(message.E000121, {
							title : '',
							closeBtn : 0,
							btn : [ 'はい' ]
						});
					} else if (rows[i].dataItem.applyCondition == "済"
							|| rows[i].dataItem.applyCondition == "再") {
						layer.alert(message.E000123, {
							title : '',
							closeBtn : 0,
							btn : [ 'はい' ]
						});
					}
				} else {
					layer.alert(message.E000122, {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
				}

			}
		}
		if (rows.length > 0) {
			// 行のindexを更新
			var cv = flexGrid.collectionView;
			var count = cv.totalItemCount;
			for (var i = 0; i < count; i++) {
				cv.items[i].no = i + 1;
			}
		}
		check();
		if (isGridOut()) {
			sessionStorage.setItem("confirmMessage", message.Q00001);
		} else {
			sessionStorage.setItem("confirmMessage", null);
		}
	};
	// 「行コピー」ボタン
	$('.btn-copy').click(function() {
		rowCopyHandler();
	});
	var copiedRowData = null;
	var rowCopyHandler = function() {
		if ($('.btn-copy').hasClass("disabled")) {
			return;
		}
		var rows = flexGrid.selectedRows;
		copiedRowData = [];
		for (var i = 0; i < rows.length; i++) {
			copiedRowData.push(rows[i].dataItem);
		}

		if (copiedRowData.length > 0) {
			$('.btn-paste').removeClass('disabled');
		} else {
			layer.alert(message.E00010, {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
		}
	};
	// 「行貼付」ボタン
	$('.btn-paste').click(function() {
		if(!requestFlag)
		{
		rowPasteHandler();
		}
	});
	var rowPasteHandler = function() {
		if ($('.btn-paste').hasClass("disabled")) {
			return;
		}
		var items = flexGrid.itemsSource.items;
		if (items[activeRow].hiddenArea != 0) {
			layer.alert(message.E00016, {
				title : '',
				closeBtn : 0,
				btn : [ 'はい' ]
			});
			return;
		}
		if (copiedRowData && copiedRowData.length > 0) {
			var copiedRowDataCloned = $.extend(true, [], copiedRowData); // ここはディープコピー必要です
			for (var i = 0; i < copiedRowDataCloned.length; i++) {
				copiedRowDataCloned[i].applyCondition = "未";
				copiedRowDataCloned[i].manageKbn = "追";
				copiedRowDataCloned[i].insDtTime = "";
				copiedRowDataCloned[i].updDtTime = "";
				copiedRowDataCloned[i].checkFlg = "未";
				copiedRowDataCloned[i].blEntryFlg = "未";
				copiedRowDataCloned[i].hiddenArea = 0;
				copiedRowDataCloned[i].errorFlg = "";
			}
			Array.prototype.splice.apply(flexGrid.itemsSource.sourceCollection,
					[ activeRow, 1 ].concat(copiedRowDataCloned)); // insert
			flexGrid.itemsSource.refresh();
			var cv = flexGrid.collectionView;
			var count = cv.totalItemCount;
			for (var i = 0; i < count; i++) {
				cv.items[i].no = i + 1;
			}
		}
		check();
	};
	// 「検索・置換」ボタン
	$('.replaceGuide').click(function() {
		var guide = new BLUI.ShowReplaceGuide();
		guide.show(BLENUM.MenuEnum.Union);
	});
	// 「詳細」ボタン
	$(".btn_detail").click(
			function() {
				if ($('.btn_detail').hasClass("disabled")) {
					return;
				}
				var btnDetail = function(index) {
					var accessURL = baseurl + "/joinlist/detail";
					var rows = flexGrid.selectedRows;
					if (rows == null || rows == "" || rows == undefined) {
						layer.alert(message.E00010, {
							title : '',
							closeBtn : 0,
							btn : [ 'はい' ]
						});
						return;
					}
					var judge = rows[0].dataItem.hiddenArea;
					var gridlist = [];
					for (var i = 0; i < rows.length; i++) {
						gridlist.push(rows[i].dataItem);
					}
					var form = {
						mode : mode,
						joinGridList : gridlist
					}
					$.blAjax({
						url : accessURL,
						type : 'POST',
						data : JSON.stringify(form),
						success : function(data) {
							if (judge == 0) {
								sessionStorage.setItem("joinDetailMode",
										BLENUM.ModeEnum.DetailAdd);
							} else {
								sessionStorage.setItem("joinDetailMode", mode);
							}
							showDetail();
						},
						error : function(data, httpStatus, errorHandler) {
							errorHandler(data, httpStatus);
						}
					});
					layer.close(index);
				};
				search();
				btnDetail();
			});
	// 「新規追加」ボタン
	$('.btn-new').click(
			function() {
					sessionStorage.setItem("joinDetailMode",
							BLENUM.ModeEnum.DetailAdd);
					showDetail();
			});
	// 「戻る」ボタン
	$(".btn-back").click(function() {
		var back = function(index) {
			switch (parseInt(mode)) {
			// 検索入力モード
			case BLENUM.ModeEnum.New:
				window.location.href = "../topmenu/topMenu";
				break;
			// 結合選択モード
			case BLENUM.ModeEnum.Update:
				var accessURL = baseurl + "/joinlist/back";
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
			layer.confirm(message.Q00001, {
				icon : 3,
				title : '',
				closeBtn : 0,
				btn : [ 'はい', 'いいえ' ]
			}, back)
		} else {
			back();
		}
	});
	var getColumnBinding = function(col) {
		return flexGrid.cells.columns[col].binding;
	}

	var showDetail = function() {
		layer.config({
			extend : '../../css/classifyCodeGuide/layerButton.css'
		});
		var parentFrame = null;
		layer.open({
			type : 2,
			title : false,
			closeBtn : 0,
			skin : 'layer-ext-skin',
			shade : 0.1,
			area : [ '100%', '80%' ],
			sucess : function() {
				parentFrame = $('#child_page')[0].contentWindow.document;
			},

			// yes: function (index, layero) {
			// alert("2");
			// },
			content : [ baseurl + "/joindetail/joinDetail" ],
		// btn: ['確定', '閉じる'],
		// btn2: function(index, layero) {
		// layer.close(index);
		// }
		});
	}
});
