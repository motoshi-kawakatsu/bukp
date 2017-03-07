$(function() {
	'use strict';
	// 初期データの準備
	var data = [];
	var isChanged = false;
	var gridInitData = [];
	var accessURL = baseurl + "/joindetail/gridinit";
	var kindCodeStr = "";
	var message = JSON.parse(sessionStorage.message);
	var mode = sessionStorage.joinDetailMode;
	$
			.ajax({
				url : accessURL,
				type : 'post',
				dataType : 'Json',
				contentType : 'application/Json',
				error : function(xhr, status) {
					alert("error" + xhr.status);
				},
				success : function(result, status, xhr) {
					kindCodeStr = result.kindCode;
					flexGrid.viewData = {};
					flexGrid.viewData.kindCodeStr = kindCodeStr;
					// 初期化のデータ存在しない
					if (result == null || result == undefined
							|| result.initList == null
							|| result.initList == undefined
							|| result.initList.length == 0) {
						// 空のグリッドを初期化します
						flexGrid.itemsSource = new wijmo.collections.CollectionView(
								data);
						$('.btn-del').addClass('disabled');
						$('.btn-copy').addClass('disabled');
						$('.btn-paste').addClass('disabled');
						$('.replaceGuide').addClass('disabled');

						$('.prm_set_dtl_no_1').val(
								$('#prmSetDtlNo1', window.parent.document)
										.val());

						$('.parts_maker_cd').val(
								$('#joinSourceMakerCode option:selected',
										window.parent.document).val());

						$('.classify-code')
								.val(
										$('#goodsMGroup',
												window.parent.document).val());

						$('.goodsName').val(
								$('#joinSourPartsNoWithH',
										window.parent.document).val());

						$('.apply_condition').val("未申請");
						$('.manage-kbn').val("追加");
						$('.error-flg').val("エラーなしのみ表示");
						$('.tbs-parts-code').val(
								$('#tbsPartsCode', window.parent.document)
										.val());
						$(".apply_condition").attr("disabled", "true");
						$(".error-flg").attr("disabled", "true");
						$(".manage-kbn").attr("disabled", "true");
						$('.title-text').text("新規結合登録");
					} else {
						// 情報を取得します
						var initdata = result.initList[0];
						var arr = kindCodeStr.split(",");
						flexGrid.itemsSource = new wijmo.collections.CollectionView(
								result.initList);
						// グリッドのデータを初期化します
						for (var i = 0; i < result.initList.length; i++) {
							var array = [];
							result.initList[i].no = i + 1;
							result.initList[i].hiddenArea = BLENUM.JudgeEnum.UnChange;
							var j = 0;
							// No
							array[j++] = result.initList[i].no;
							// 画面表示の時種別コード列は 「種別コード：種別名称」で表示
							for (var k = 0; k < arr.length; k++) {
								// 同じコード
								if (arr[k].split("：")[0] == result.initList[i].prmSetDtlNo2) {
									// 種別コード
									array[j++] = arr[k];

									flexGrid.setCellData(i, 1, arr[k]);
								}
							}
							// 結合表示順位
							array[j++] = result.initList[i].joinDispOrder;
							// 優良品番
							array[j++] = result.initList[i].joinDestPartsNo;
							// 優良部品名称
							array[j++] = result.initList[i].primePartsName;
							// QTY
							array[j++] = result.initList[i].joinQty;
							// 規格/特記
							array[j++] = result.initList[i].joinSpecialNote;
							// 規格/特記（一般）
							array[j++] = result.initList[i].primePartsSpecialNoteC;
							// 削除依頼区分
							array[j++] = result.initList[i].deleteFlg;
							// 削除理由
							array[j++] = result.initList[i].deleteReason;
							// 適用日付
							array[j++] = result.initList[i].startTime;
							// エラー内容
							array[j++] = result.initList[i].errorDetail;
							gridInitData[i] = array;
						}

						// セレクトコード
						$('.prm_set_dtl_no_1').val(initdata.prmSetDtlNo1);
						// カーコード
						$('.parts_maker_cd').val(initdata.joinSourceMakerCode);
						// 申請状態
						$('.apply_condition').val(initdata.applyCondition);
						// 分類コード
						$('.classify-code').val(result.goodsMGroup);
						// 純正品番
						$('.goodsName').val(result.joinSourPartsNoWithH);
						// 処理区分
						$('.manage-kbn').val(initdata.manageKbn);
						// BLコード
						$('.tbs-parts-code').val(initdata.tbsPartsCode);
						// エラー区分
						$('.error-flg').val(initdata.errorFlg);
						sessionStorage.setItem("initListSize",
								result.initList.length);

					}
					// グリッドのデフォルトフォーカスをセートします。
					//				
					// 画面初期化します
					pageInit();
				}
			});

	var flexGrid = new wijmo.grid.FlexGrid('#grid');
	flexGrid.initialize({
		// ソートを禁止します
		allowSorting : false,
		autoGenerateColumns : false,
		// グリッドの項目を初期化します
		columns : [ {
			header : 'No.',
			binding : 'no',
			width : 50,
			align : 'Center',
			isReadOnly : true
		}, {
			header : '種別コード',
			binding : 'prmSetDtlNo2',
			width : 150,
			align : 'Left'
		}, {
			header : '表示順位',
			binding : 'joinDispOrder',
			width : 110,
			align : 'Center',
			dataType : 'String'
		}, {
			header : '優良品番',
			binding : 'joinDestPartsNo',
			width : 130,
			align : 'Left'
		}, {
			header : '優良品名',
			binding : 'primePartsName',
			width : 150,
			align : 'Left',
			isReadOnly : true
		}, {
			header : 'QTY',
			binding : 'joinQty',
			width : 120,
			align : 'Right',
			dataType : 'Number'
		}, {
			header : '規格/特記',
			binding : 'joinSpecialNote',
			width : 170,
			align : 'Left'
		}, {
			header : '規格/特記（一般）',
			binding : 'primePartsSpecialNoteC',
			width : 170,
			align : 'Left'
		}, {
			header : '削除依頼区分',
			binding : 'deleteFlg',
			width : 150,
			minWidth : 30,
			align : 'Center'
		}, {
			header : '削除理由',
			binding : 'deleteReason',
			width : 200,
			align : 'Left'
		}, {
			header : '適用日時',
			binding : 'startTime',
			dataType : wijmo.DataType.dateTime,
			width : 230,
			align : 'Left'
		}, {
			header : 'エラー内容',
			binding : 'errorDetail',
			width : 220,
			align : 'Left',
			isReadOnly : true
		} ]
	});
	// 行挿入ボタンクリック
	$('.btn-add').click(function() {
		rowAddHandler();
	});
	// 行削除ボタンクリック
	$('.btn-del').click(function() {
		rowDeleteHandler();
	});
	// 行コピーボタンクリック
	$('.btn-copy').click(function() {
		rowCopyHandler();
	});
	// 行貼付ボタンクリック
	$('.btn-paste').click(function() {
		rowPasteHandler();
	});
	// 商品中分類ボタンクリック
	$('.classifyCdGuide').click(function() {
		try {
			var accessURL = baseurl + '/setlist/goodsMGroup';
			var actionType = "POST";
			var inputname = 'classifyCd';
			var isGrid = false;
			var form = {
				goodsMGroup : $('.classify-code').val()
			};
			$.blAjax({
				url : accessURL,
				type : actionType,
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

	// 商品中純正ボタンクリック
	$('.joinSourPartsNoWithH').click(function() {
		try {
			// 品番
			var accessURL = baseurl + '/joinlist/joinSourPartsNoWithH';
			var actionType = 'POST';
			var inputname = 'joinSourParts';
			var isGrid = false;
			var form = {
				// 純正品番
				joinSourPartsNoWithH : $('.goodsName').val()
			};
			$.blAjax({
				url : accessURL,
				type : actionType,
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
	// 検索・置換ボタンクリック
	$('.replaceGuide').click(function() {
		new BLUI.ShowReplaceGuide().show(BLENUM.MenuEnum.joindetail);
	});
	// 確認ボタンクリック
	$('.btn-confirm').click(function() {
		getChangeHandler();
	});
	// 戻るボタンクリック
	$('.btn-back').click(function() {
		if (isChanged) {
			if (layer.confirm("編集中の項目があります。入力された内容を破棄して、画面を遷移しますか？", {
				icon : 3,
				title : '',
				btn : [ '確定', '取消' ],
				closeBtn : 0
			}, closeDetail)) {
			}
		} else {
			closeDetail()
		}
	});
	var closeDetail = function() {
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	}

	// グリッドIndexによって、カラムのBindingを取得 （カラム移動を対応できる）
	var getColumnBinding = function(col) {
		return flexGrid.cells.columns[col].binding;
	}

	var getColumnDataType = function(col) {
		return flexGrid.cells.columns[col].dataType;
	}
	// Gridのセルフォーマット用
	var gridItemFormatter = function(panel, r, c, cell) {
		var gridItem = panel.grid;
		var editRange = flexGrid.editRange;

		if (!(editRange && panel.cellType == wijmo.grid.CellType.Cell
				&& editRange.row == r && editRange.col == c)) {
			return;
		}
		// 入力制御 長さ制限と数字入力制限
		flexGrid.prepareCellForEdit.addHandler(function(s, e) {
			var binding = getColumnBinding(e.col);
			// 表示順位
			if (binding == 'joinDispOrder') {
				$(flexGrid.activeEditor).attr('maxlength', 4);
			}
			// 優良品番
			if (binding == 'joinDestPartsNo') {
				$(flexGrid.activeEditor).attr('maxlength', 24);
			}
			// Qty
			if (binding == 'joinQty') {
				$(flexGrid.activeEditor).attr('maxlength', 6);
				$(flexGrid.activeEditor).attr("id", "joinQty");
           	 	$(flexGrid.activeEditor).attr("oninput", "doubleInputForQTY(this.id)");
			}
			// 規格/特記,規格/特記（一般）,削除理由
			if (binding == 'joinSpecialNote'
					|| binding == 'primePartsSpecialNoteC'
					|| binding == 'deleteReason') {
				$(flexGrid.activeEditor).attr('maxlength', 80);
			}
			// 適用日時
			if (binding == 'startTime') {
				$(flexGrid.activeEditor).attr('maxlength', 16);
			}
		});

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

		// コンボボックスを作成する
		if (colBinding == 'prmSetDtlNo2') { // kindCode列
			createGridComboBoxCell(r, c, cell, kindCodeStr.split(","));
		} else if (colBinding == 'deleteFlg') { // 削除依頼列
			createGridComboBoxCell(r, c, cell, [ '', '削除する' ]);
		}
	};
	var createGridComboBoxCell = function(r, c, cell, itemsSource, classList) {
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
				var shift = event.shiftKey && !event.ctrlKey && !event.altKey
						&& !event.metaKey;
				var alt = !event.shiftKey && !event.ctrlKey && event.altKey
						&& !event.metaKey;
				var ctrl = !event.shiftKey && event.ctrlKey && !event.altKey
						&& !event.metaKey;
				var nospec = !event.shiftKey && !event.ctrlKey && !event.altKey
						&& !event.metaKey;
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

	flexGrid.formatItem
			.addHandler(function(s, e) {

				if (e.panel.cellType == wijmo.grid.CellType.ColumnHeader
				// 種別コード,表示順位,優良品番,適用日時
				&& (e.col == 1 || e.col == 2 || e.col == 3 || e.col == 10)) {
					$(e.cell).html(
							function() {
								return $(e.cell).html()
										+ "<span style='color:red'>*</span>";
							});

				}
				// 必須項目チェック
				if (e.panel.cellType == wijmo.grid.CellType.Cell
						&& (getColumnBinding(e.col) == "prmSetDtlNo2"
								|| getColumnBinding(e.col) == "joinDispOrder"
								|| getColumnBinding(e.col) == "joinDestPartsNo" || getColumnBinding(e.col) == "startTime")) {
					var value = flexGrid.getCellData(e.row, e.col, false);
					if (!value) {
						e.cell.classList.add('null-cell');
					}
				}

			});
	// ガイドボタンの実現デモ
	flexGrid.formatItem.addHandler(function(s, e) {
		var rows = flexGrid.rows;
		if (rows[e.row]
				&& rows[e.row].dataItem.hiddenArea == BLENUM.JudgeEnum.Delete) {
			rows[e.row].cssClass = 'd-cell';
		}

		if (e.panel.cellType == wijmo.grid.CellType.Cell) {
			var editRange = grid.editRange;
			var row = e.row;
			var col = e.col;
			var isEdit = editRange && editRange.row == row
					&& editRange.col == col; // 編集モード

			// goodCd
			if (getColumnBinding(e.col) == 'joinDestPartsNo') {
				wijmo.addClass(e.cell, 'guide-code-button-cell');
				// ガイドボタンセルを作成
				var button = createGoodsGuideButton(row, col);
				if (isEdit) {
					button.style.display = 'block';
					e.cell.appendChild(button);
				} else {
					e.cell.appendChild(button);
				}
				// 品番で品名を検索し、戻り値は「品番：品名」の形ので、ここにフォーマットが必要
				if (flexGrid.getCellData(row, col) != undefined) {
					var str = flexGrid.getCellData(row, col).split("：");
					if (str[1] != undefined) {
						// 品番列は「品番」表示だけ
						flexGrid.setCellData(row, col, str[0]);
						// 品番列変更後、品名列も変更する
						flexGrid.setCellData(row, col + 1, str[1]);
					}
				}
			}
			// 入力不可の項目の背景色を設定する（黄色）
			if (getColumnBinding(e.col) == 'no'
					|| getColumnBinding(e.col) == 'primePartsName'
					|| getColumnBinding(e.col) == 'errorDetail') {
				$(e.cell).addClass('c-cell');
			}
		}
	});

	// goodsガイドボタンを作成
	function createGoodsGuideButton(row, col) {
		var button = document.createElement('button');
		wijmo.addClass(button, 'glyphicon glyphicon-star-empty');
		// 参照モード場合goodsガイドボタン無効化
		if (BLENUM.ModeEnum.Readonly == mode || BLENUM.ModeEnum.Update == mode) {
			wijmo.enable(button, false);
		}
		// goodsガイド
		var openGuide = function(e) {
			try {
				// 品番
				var accessURL = baseurl + '/joinlist/joinDestPartsNo';
				var actionType = 'POST';
				var form = {
					// 優良品番
					joinDestPartsNo : flexGrid.getCellData(row, col) == undefined ? ""
							: flexGrid.getCellData(row, col).split(':')[0],
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

	flexGrid.select(-1, -1);

	var activeRow = -1;
	flexGrid.selectionChanged.addHandler(function(s, e) {
		var range = e.range;
		if (activeRow >= 0 && activeRow < flexGrid.rows.length) {
			flexGrid.rowHeaders.setCellData(activeRow, 0, '');
		}
		activeRow = range.row;
		flexGrid.rowHeaders.setCellData(activeRow, 0, '\uf0da');
	});
	var rowAddHandler = function() {
		isChanged = true;
		if ($(this).hasClass("disabled")) {
			return;
		}

		flexGrid.itemsSource.addNew();
		flexGrid.itemsSource.commitNew();
		var cv = flexGrid.collectionView;
		var count = cv.totalItemCount;
		for (var i = 0; i < count; i++) {
			cv.items[i].no = i + 1;
			if (cv.items[i].hiddenArea == undefined) {
				cv.items[i].hiddenArea = BLENUM.JudgeEnum.Add;
			}

		}
		$('.btn-del').removeClass('disabled');
		$('.btn-copy').removeClass('disabled');
		$('.btn-paste').removeClass('disabled');
		$('.replaceGuide').removeClass('disabled');
	};

	var rowDeleteHandler = function() {
		if ($(this).hasClass("disabled")) {
			return;
		}
		if ("承認済" == $('.apply_condition').val()) {
			layer.alert(message.E00012.split("-")[1].replace("$1", "承認済")
					.replace("$2", "結合"), {
				title : message.E00012.split("-")[0],
				closeBtn : 0
			});
			return;

		}
		if ("申請中" == $('.apply_condition').val()) {
			layer.alert(message.E00012.split("-")[1].replace("$1", "申請中")
					.replace("$2", "結合"), {
				title : message.E00012.split("-")[0],
				closeBtn : 0
			});
			return;
		}
		if ("再申請" == $('.apply_condition').val()) {
			layer.alert(message.E00012.split("-")[1].replace("$1", "承認済")
					.replace("$2", "結合"), {
				title : message.E00012.split("-")[0],
				closeBtn : 0
			});
			return;
		}
		// 選択行を削除
		var rows = flexGrid.selectedRows;
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].dataItem.hiddenArea == BLENUM.JudgeEnum.Add) {
				flexGrid.collectionView.remove(rows[i].dataItem);
			} else {
				rows[i].dataItem.hiddenArea = BLENUM.JudgeEnum.Delete;
				rows[i].cssClass = 'd-cell'
			}
		}

		if (rows.length > 0) {
			// 行indexを更新
			var cv = flexGrid.collectionView;
			var count = cv.totalItemCount;
			for (var i = 0; i < count; i++) {
				cv.items[i].no = i + 1;
			}
		}

		if (flexGrid.collectionView.totalItemCount == 0) {
			$('.btn-del').addClass('disabled');
			$('.btn-copy').addClass('disabled');
			$('.btn-paste').addClass('disabled');
			$('.replaceGuide').addClass('disabled');
		}

	};

	var copiedRowData = null;
	var rowCopyHandler = function() {
		if ($(this).hasClass("disabled")) {
			return;
		}
		var rows = flexGrid.selectedRows;
		copiedRowData = [];
		for (var i = 0; i < rows.length; i++) {
			copiedRowData.push(rows[i].dataItem);
		}

		if (copiedRowData.length > 0) {
			$('.btn-paste').removeClass('disabled');
		}
	};

	var rowPasteHandler = function() {
		if ($(this).hasClass("disabled")) {
			return;
		}

		var items = flexGrid.itemsSource.items;
		if (items[activeRow].hiddenArea != BLENUM.JudgeEnum.Add) {
			layer.alert(message.E00016.split("-")[1], {
				title : message.E00016.split("-")[0],
				closeBtn : 0,
				btn : [ 'はい' ]
			});
			return;
		}

		if (copiedRowData && copiedRowData.length > 0) {
			var copiedRowDataCloned = $.extend(true, [], copiedRowData); // ここはディープコピー必要です
			for (var i = 0; i < copiedRowDataCloned.length; i++) {
				copiedRowDataCloned[i].errorDetail = "";
			}
			var selectRow = activeRow;

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
	};

	var nabigeshon = new BLUI.Nabigeshon();
	nabigeshon.getData(17);

	flexGrid.rowEditEnding.addHandler(function(sender, e) {
		isChanged = true;
	});

	// セル編集処理
	flexGrid.cellEditEnding.addHandler(function(sender, e) {
		if (flexGrid.getCellData(e.row, e.col) != flexGrid.activeEditor.value) {
			isChanged = true;
			if (flexGrid.rows[e.row].hiddenArea == BLENUM.JudgeEnum.UnChange) {
				flexGrid.rows[e.row].hiddenArea = BLENUM.JudgeEnum.Update;
			}
		}

		if (gridInitData.length != 0 && gridInitData[e.row]
				&& flexGrid.activeEditor.value == gridInitData[e.row][e.col]) {
			isChanged = false;
		}
		var binding = getColumnBinding(e.col);
		var init = flexGrid.getCellData(e.row, e.col);

		// 表示順位
		if (binding == 'joinDispOrder') {
			// 数字のみ
			if (isNaN(flexGrid.activeEditor.value)) {
				flexGrid.activeEditor.value = init;
			}
		}

		// 優良品番
		if (binding == 'joinDestPartsNo') {
			try {
				var fyCode = flexGrid.activeEditor.value.split("：")[0];
				if (fyCode == undefined) {
					fyCode = "";
				}
				var accessURL = baseurl + "/changecommon/getCode";
				var form = {
					code : flexGrid.activeEditor.value.split("：")[0],
					guideType : 2
				};
				if (fyCode != "") {
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
										flexGrid.setCellData(e.row, e.col, "");
										flexGrid.setCellData(e.row, e.col + 1,
												"");
									}
								});
							}
							if (data.codeValue.split("：")[1] == undefined) {
								layer.alert("該当する商品中分類データが存在しません。", {
									title : '',
									closeBtn : 0,
									btn : [ '確定' ],
									end : function() {
										flexGrid.setCellData(e.row, e.col, "");
										flexGrid.setCellData(e.row, e.col + 1,
												"");
									}
								});
							} else {
								flexGrid.setCellData(e.row, e.col,
										data.codeValue.split("：")[0]);
								flexGrid.setCellData(e.row, e.col + 1,
										data.codeValue.split("：")[1]);
							}
						},
					});
				} else {
					flexGrid.setCellData(e.row, e.col, "");
					flexGrid.setCellData(e.row, e.col + 1, "");
				}
			} catch (e) {
				sendClientErrorLog(e);
			}

		}

	});

	var getChangeHandler = function() {

		if (!isChanged) {
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}

		var joinGridList = flexGrid.itemsSource.items;

		var accessURL = baseurl + "/joindetail/comfirm";

		for (var i = 0; i < joinGridList.length; i++) {
			// セレクトコード
			joinGridList[i].prmSetDtlNo1 = $(
					'.prm_set_dtl_no_1 option:selected').text();
			// 分類コード
			joinGridList[i].goodsMGroup = $('.classify-code').val();

			// BLコード
			joinGridList[i].tbsPartsCode = $('.tbs-parts-code option:selected')
					.text();

			// カーコード名称
			joinGridList[i].joinSourceMakerCode = $(
					'.parts_maker_cd option:selected').text();

			// 純正品番
			joinGridList[i].joinSourPartsNoWithH = $('.goodsName').val();

			// 優良品番
			joinGridList[i].joinDestPartsNo = joinGridList[i].joinDestPartsNo;
			
			joinGridList[i].errorDetail="";

			joinGridList[i].errorDetail="無";
			// gridData[i].joinDestPartsNo = $('.goodsName').val();
		}
		$.blAjax({
			url : accessURL,
			type : 'POST',
			dataType : 'Json',
			data : JSON.stringify(joinGridList),
			contentType : 'application/Json',
			success : function(data) {
				if (data.isError) {
					for (var i = 0; i < data.refreshGrid.length; i++) {
						data.refreshGrid[i].no = i + 1;
					}

					var collectionView = new wijmo.collections.CollectionView(
							data.refreshGrid);
					flexGrid.itemsSource = collectionView;
					flexGrid.refresh;
					layer.alert("エラーがあります,エラー内容を参照してください。", {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
				} else {
					mergeGrid();
				}
			},
			error : function(data, httpStatus, errorHandler) {
				errorHandler(data, httpStatus);
			}
		});

	};

	var mergeGrid = function() {
		var grid = parent.document.getElementById("grid")["wj-Control"];
		var gridData = flexGrid.itemsSource.items;
		var no = grid.selection.row + 1;

		for (var i = 0; i < gridData.length; i++) {
			// セレクトコード
			gridData[i].prmSetDtlNo1 = $('.prm_set_dtl_no_1 option:selected')
					.text();
			// 分類コード
			gridData[i].goodsMGroup = $('.classify-code').val();

			// BLコード
			gridData[i].tbsPartsCode = $('.tbs-parts-code option:selected')
					.text();

			// カーコード名称
			gridData[i].joinSourceMakerCode = $(
					'.parts_maker_cd option:selected').text();

			// 純正品番
			gridData[i].joinSourPartsNoWithH = $('.goodsName').val();

			// 優良品番
			gridData[i].joinDestPartsNo = gridData[i].joinDestPartsNo + "："
					+ gridData[i].primePartsName;

			// gridData[i].joinDestPartsNo = $('.goodsName').val();
			gridData[i].no = no++;
			if ("未申請" == $('.apply_condition').val()) {
				gridData[i].applyCondition = "未";
			}
			if ("申請中" == $('.apply_condition').val()) {
				gridData[i].applyCondition = "中";
			}
			if ("承認済" == $('.apply_condition').val()) {
				gridData[i].applyCondition = "済"
			}
			if ("再申請" == $('.apply_condition').val()) {
				gridData[i].applyCondition = "再"
			}

			if ("追加" == $('.manage-kbn').val()) {
				gridData[i].manageKbn = "追"
			}
			if ("更新" == $('.manage-kbn').val()) {
				gridData[i].manageKbn = "更"
			}
			if ("削除" == $('.manage-kbn').val()) {
				gridData[i].manageKbn = "削"
			}
		}
		var parentIndex = undefined;
		var deleteCount = 0;
		var count = grid.itemsSource.items.length;
		for (var i = 0; i < count; i++) {
			if (grid.itemsSource.items[i].prmSetDtlNo1 == $(
					'.prm_set_dtl_no_1 option:selected').text()
					&& grid.itemsSource.items[i].tbsPartsCode == $(
							'.tbs-parts-code option:selected').text()
					&& grid.itemsSource.items[i].joinSourPartsNoWithH == $(
							'.goodsName').val()
					&& grid.itemsSource.items[i].joinSourceMakerCode == $(
							'.parts_maker_cd option:selected').val()

			) {
				if (parentIndex == undefined) {
					parentIndex = i;
				}

			}
		}

		Array.prototype.splice.apply(grid.itemsSource.sourceCollection, [
				parentIndex, sessionStorage.getItem("initListSize") ]
				.concat(gridData)); // insert

		for (var i = 0; i < grid.itemsSource.items.length; i++) {
			grid.itemsSource.items[i].no = i + 1;
		}

		grid.itemsSource.refresh();
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	};

	// 分類コード フォーカスアウト 処理
	$('[name="classifyCd"]').focusout(function() {
		try {
			var fyCode = $('[name="classifyCd"]').val().split("：")[0];
			if (fyCode == undefined) {
				fyCode = "";
			}
			var accessURL = baseurl + "/changecommon/getCode";
			var form = {
				code : $('[name="classifyCd"]').val().split("：")[0],
				guideType : 0
			};
			if (fyCode != "") {
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
									$('[name="classifyCd"]').val("");
								}
							});
						}
						if (data.codeValue.split("：")[1] == undefined) {
							layer.alert("該当する商品中分類データが存在しません。", {
								title : '',
								closeBtn : 0,
								btn : [ '確定' ],
								end : function() {
									$('[name="classifyCd"]').val("");
								}
							});
						} else {
							$('[name="classifyCd"]').val(data.codeValue);
						}
					},
				});
			}

		} catch (e) {
			sendClientErrorLog(e);
		}

	});

	// 純正品番 フォーカスアウト 処理
	$('[name="joinSourParts"]')
			.focusout(

					function() {
						try {
							var fyCode = $('[name="joinSourParts"]').val()
									.split("：")[0];
							if (fyCode == undefined) {
								fyCode = "";
							}
							var accessURL = baseurl + "/changecommon/getCode";
							var form = {
								code : $('[name="joinSourParts"]').val().split(
										"：")[0],
								guideType : 1
							};
							if (fyCode != "") {
								$
										.blAjax({
											url : accessURL,
											type : "POST",
											data : JSON.stringify(form),
											error : function(data, httpStatus,
													errorHandler) {
												errorHandler(data, httpStatus);
											},
											success : function(data) {
												if (data.message != undefined) {
													layer
															.alert(
																	data.message,
																	{
																		title : '',
																		closeBtn : 0,
																		btn : [ 'はい' ],
																		end : function() {
																			$(
																					'[name="joinSourParts"]')
																					.val(
																							"");
																		}
																	});
												}
												if (data.codeValue.split("：")[1] == undefined) {
													layer
															.alert(
																	"該当する商品中分類データが存在しません。",
																	{
																		title : '',
																		closeBtn : 0,
																		btn : [ '確定' ],
																		end : function() {
																			$(
																					'[name="joinSourParts"]')
																					.val(
																							"");
																		}
																	});
												} else {
													$('[name="joinSourParts"]')
															.val(
																	data.codeValue
																			.split("：")[0]
																			+ "："
																			+ data.codeValue
																					.split("：")[1]);
												}
											},
										});
							}
						} catch (e) {
							sendClientErrorLog(e);
						}

					});

	var pageInit = function() {
		// 参照モード
		if (BLENUM.ModeEnum.Readonly == mode || BLENUM.ModeEnum.Update == mode) {
			flexGrid.isReadOnly = true;
			$(".prm_set_dtl_no_1").attr("disabled", "true");
			$(".parts_maker_cd").attr("disabled", "true");
			$(".classifyCdGuide").attr("disabled", "true");
			$(".joinSourPartsNoWithH").attr("disabled", "true");
			$(".classify-code").attr("disabled", "true");
			$(".apply_condition").attr("disabled", "true");
			$(".goodsName").attr("disabled", "true");
			$(".manage-kbn").attr("disabled", "true");
			$(".tbs-parts-code").attr("disabled", "true");
			$(".error-flg").attr("disabled", "true");
			$(".btn-add").hide();
			$(".btn-del").hide();
			$(".btn-copy").hide();
			$(".btn-paste").hide();
			$(".replaceGuide").hide();
			$(".btn-confirm").hide();
		}
		// エラーモード
		if (BLENUM.ModeEnum.Error == mode) {
			$(".apply_condition").attr("disabled", "true");
			$(".manage-kbn").attr("disabled", "true");
			$(".error-flg").attr("disabled", "true");
			$(".btn-add").hide();
			$(".btn-del").hide();
			$(".btn-copy").hide();
			$(".btn-paste").hide();
		}
		// 更新モード
		if (BLENUM.ModeEnum.New == mode) {

			$(".prm_set_dtl_no_1").attr("disabled", "true");
			$(".parts_maker_cd").attr("disabled", "true");
			$(".del-kubun").attr("disabled", "true");
			$(".classify-code").attr("disabled", "true");
			$(".classifyCdGuide").attr("disabled", "true");
			$(".joinSourPartsNoWithH").attr("disabled", "true");
			$(".tbs-parts-code").attr("disabled", "true");
			$(".join_source_maker_code").attr("disabled", "true");
			$(".apply-state").attr("disabled", "true");
			$(".apply_condition").attr("disabled", "true");
			$(".error-flg").attr("disabled", "true");
			$(".manage-kbn").attr("disabled", "true");
			$(".error-distinction").attr("disabled", "true");
			$(".goodsName").attr("disabled", "true");
		}
	}
	// tooltip
	flexGrid.formatItem
			.addHandler(function(s, e) {
				if (e.panel.cellType == wijmo.grid.CellType.Cell) {
					var colBinding = getColumnBinding(e.col);

					if (isReadonlyCell(e.row, e.col)) {
						$(e.cell).addClass('c-cell');
					}
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
									placement, pos, actualWidth, actualHeight) {
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

							$('.tooltip[role=tooltip]').on(
									'mousemove mouseleave', function(event) {
										gridCellTooltipMouseMoveHandler(event);
									}).on(
									'click',
									function(event) {
										var ht = flexGrid.hitTest(event.pageX,
												event.pageY);
										gotoEditingCell(ht.row, ht.col);
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
					cell.off('mouseenter', gridCellTooltipMouseEnterHandler);
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

	// 読取専用のセルを判断用
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

		if (colBinding == 'errorDetail') {
			return true;
		} else {
			return false;
		}
	}

	// 適用日時
	flexGrid.cellEditEnded.addHandler(function(s, e) {
		var binding = getColumnBinding(e.col);
		if (binding == "startTime") {
			var newData = flexGrid.getCellData(e.row, e.col);
			if (!checkDate(newData)) {
				layer.alert('YYYY/MM/DD形式を入力してください。', {
					title : '',
					closeBtn : 0,
					btn : [ 'はい' ]
				});
				flexGrid.setCellData(e.row, e.col, "");
			} else {
				if (newData.length <= 10) {
					flexGrid.setCellData(e.row, e.col, newData + "　00:00");
				}
			}
		}
	});
});