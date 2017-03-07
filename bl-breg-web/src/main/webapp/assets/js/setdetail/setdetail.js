$(function() {
	'use strict';
	// 初期データの準備
	var data = [];
	// 画面変更flag
	var isChanged = false;
	// 画面初期のグリッドデータ
	var gridInitData = [];
	// 画面初期のモード
	var mode = undefined;
	var message = JSON.parse(sessionStorage.message);

	var accessURL = baseurl + "/setdetail/gridinit";
	$
			.ajax({
				url : accessURL,
				type : 'post',
				dataType : 'Json',
				data : sessionStorage.setDetail,
				contentType : 'application/Json',
				error : function(xhr, status) {
					layer.alert("error" + xhr.status, {
						title : '',
						closeBtn : 0
					});
				},
				success : function(result, status, xhr) {
					$('.bl-code').focus();
					// 初期化のデータ存在しない
					if (result == undefined || result.initList == undefined
							|| result.initList.length == 0) {
						// 空のグリッドを初期化します
						flexGrid.itemsSource = new wijmo.collections.CollectionView(
								data);

						// 作成日時
						if ($("[name='insDtTimeStart']", window.parent.document)
								.val() == null
								|| $("[name='insDtTimeStart']",
										window.parent.document).val() == '') {
							$('.createdate').val(
									new Date().Format('yyyy/MM/dd HH:mm'));
						} else {
							$('.createdate').val(
									$("[name='insDtTimeStart']",
											window.parent.document).val());
						}

						// 更新日時
						$('.update-time').val(
								new Date().Format('yyyy/MM/dd HH:mm'))

						if ($("[name='insDtTimeStart']", window.parent.document)
								.val() == null
								|| $("[name='insDtTimeStart']",
										window.parent.document).val() == '') {
							$('.update-time').val(
									new Date().Format('yyyy/MM/dd HH:mm'));
						} else {
							$('.update-time').val(
									$("[name='insDtTimeStart']",
											window.parent.document).val());
						}

						$('.bl-code').val(
								$("[name='tbsPartsCode']",
										window.parent.document).val());
						$('.select-code').val(
								$("[name='prmSetDtlNo1']",
										window.parent.document).val());

						$('.set-main-parts-no').val(
								$("[name='setMainPartsNo']",
										window.parent.document).val());
						$('.classify-code').val(
								$("[name='goodsMGroup']",
										window.parent.document).val());

						$('.btn-del').attr('disabled', 'true');
						$('.btn-copy').attr('disabled', 'true');
						$('.btn-paste').attr('disabled', 'true');
						$('.replaceGuide').attr('disabled', 'true');

					} else {
						// 親セートの情報を取得します
						var initdata = result.initList[0];
						// グリッドのデータを初期化します
						for (var i = 0; i < result.initList.length; i++) {
							if (result.initList[i].startTime != null
									&& result.initList[i].startTime != "") {
								// 適用日時のフォーマット
								result.initList[i].startTime = new Date(
										result.initList[i].startTime)
										.Format('yyyy/MM/dd HH:mm');
							}
						}

						// 初期グリッドの件数
						sessionStorage.setItem("initListSize",
								result.initList.length);

						for (var i = 0; i < result.initList.length; i++) {
							result.initList[i].deleteFlg = result.initList[i].deleteFlg == 0 ? ""
									: "削除する";
							result.initList[i].no = i + 1;
							result.initList[i].hiddenArea = BLENUM.JudgeEnum.UnChange;

							var array = [];
							var j = 0;
							// 表示順位
							array[j++] = result.initList[i].setDispOrder;
							// セット子品番
							array[j++] = result.initList[i].setSubPartsNo;
							// セット品名（半角）
							array[j++] = result.initList[i].setKanaName;
							// セット品名（全角）
							array[j++] = result.initList[i].setName;
							// QTY
							array[j++] = result.initList[i].setQty;
							// 規格/特記
							array[j++] = result.initList[i].setSpecialNote;
							// 規格/特記（一般）
							array[j++] = result.initList[i].primePartsSpecialNoteC;
							// 適用日
							array[j++] = result.initList[i].startTime;
							// 削除依頼区分
							array[j++] = result.initList[i].deleteFlg;
							// 削除理由
							array[j++] = result.initList[i].deleteReason;
							// エラー内容
							array[j++] = result.initList[i].errorDetail;
							gridInitData[i] = array;
						}
						// グリッドのソース初期化
						flexGrid.itemsSource = new wijmo.collections.CollectionView(
								result.initList);
						// 作成日時
						$('.createdate').val(
								new Date(initdata.insDtTime)
										.Format('yyyy/MM/dd HH:mm'));
						// 更新日時
						$('.update-time').val(
								new Date(initdata.updDtTime)
										.Format('yyyy/MM/dd HH:mm'));
						// 申請状態
						$('.applyCondition').val(initdata.applyCondition);

						var grid = parent.document.getElementById("grid")["wj-Control"];

						// 分類コード
						$('.classify-code').val(
								grid.selectedRows[0].dataItem.goodsMGroup);
						// 処理区分
						$('.del-kubun').val(initdata.manageKbn);
						// セット親品番
						$('.set-main-parts-no').val(
								grid.selectedRows[0].dataItem.setMainPartsNo);
						// セレクトコード
						$('.select-code').val(initdata.prmSetDtlNo1);
						// BLコード
						$('.bl-code').val(initdata.tbsPartsCode);

					}
					// グリッドのデフォルトフォーカスをセートします。
					flexGrid.select(-1, -1);
					// 画面初期化します
					pageInit(result.mode);
					mode = result.mode;
				}
			});

	var flexGrid = new wijmo.grid.FlexGrid('#grid');

	flexGrid.initialize({
		// ソートを禁止します
		allowSorting : false,
		autoGenerateColumns : false,
		// headersVisibility : wijmo.grid.HeadersVisibility.Column,
		// グリッドの項目を初期化します
		columns : [ {
			header : '表示順位',
			binding : 'setDispOrder',
			width : 100,
			align : 'right'
		}, {
			header : 'セット子品番',
			binding : 'setSubPartsNo',
			width : 150,
			align : 'Center'
		}, {
			header : 'セット品名（半角）',
			binding : 'setKanaName',
			width : 150,
			align : 'Center'
		}, {
			header : 'セット品名（全角）',
			binding : 'setName',
			width : 180,
			align : 'Center'
		}, {
			header : 'QTY',
			binding : 'setQty',
			width : 180,
			align : 'right'
		}, {
			header : '規格/特記',
			binding : 'setSpecialNote',
			width : 200,
			align : 'Center'
		}, {
			header : '規格/特記（一般）',
			binding : 'primePartsSpecialNoteC',
			width : 200,
			align : 'Center'
		}, {
			header : '適用日時',
			binding : 'startTime',
			width : 150,
			align : 'Center'
		}, {
			header : '削除依頼区分',
			binding : 'deleteFlg',
			width : 150,
			minWidth : 30,
			align : 'Center'
		}, {
			header : '削除理由',
			binding : 'deleteReason',
			width : 180,
			align : 'Center'
		}, {
			header : 'エラー内容',
			binding : 'errorDetail',
			width : 248,
			align : 'Center',
			isReadOnly : true
		} ]
	});

	// 行挿入ボタンクリック
	$('.btn-add').click(function() {
		rowInsertHandler();
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
	// セット親品番ボタンクリック
	$('.setParentCdGuide').click(function() {
		try {

			var accessURL = baseurl + '/setlist/setPartsNo';
			var actionType = 'POST';
			var name = 'setFatherCd';
			var form = {
				setSubPartsNo : '',
				setMainPartsNo : $('.set-main-parts-no').val(),
			};
			$.blAjax({
				url : accessURL,
				type : actionType,
				data : JSON.stringify(form),
				success : function(data) {
					var guide = new BLUI.GoodsGuide();
					guide.show(name, false, {});
					e.stopPropagation();

				},
				error : function(data, httpStatus, errorHandler) {
					errorHandler(data, httpStatus);
				}
			});
		} catch (e) {
			sendClientErrorLog(e);
		}
	});
	$('.bl-code').change(function() {
		isChanged = true;
	})
	$('.classify-code').change(function() {
		isChanged = true;
	})
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
	// 検索・置換ボタンクリック
	$('.replaceGuide').click(function() {
		isChanged = true;
		new BLUI.ShowReplaceGuide().show(BLENUM.MenuEnum.setdetail);
	});
	// 確認ボタンクリック
	$('.btn-confirm').click(function() {
		var isChecked = checkHandler();

		if (isChecked) {
			getChangeHandler();
		} else {
			layer.alert('エラーがあります', {
				title : '',
				closeBtn : 0
			});
			flexGrid.refresh();
		}

	});

	// 戻るボタンクリック
	$('.btn-back').click(function() {
		if (isChanged) {
			if (layer.confirm(message.Q00001.split("-")[1], {
				icon : 3,
				title : message.Q00001.split("-")[0],
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

	flexGrid.formatItem.addHandler(function(s, e) {
		var rows = flexGrid.rows;
		if (rows[e.row]
				&& rows[e.row].dataItem.hiddenArea == BLENUM.JudgeEnum.Delete) {
			rows[e.row].cssClass = 'd-cell';
		}

		// セット子品番,品名(半角),品名(全角),適用日
		if (getColumnBinding(e.col) == "setSubPartsNo"
				|| getColumnBinding(e.col) == "setKanaName"
				|| getColumnBinding(e.col) == "setName"
				|| getColumnBinding(e.col) == "startTime"
				|| getColumnBinding(e.col) == "setDispOrder"
				|| getColumnBinding(e.col) == "setQty") {

			if (e.panel.cellType == wijmo.grid.CellType.ColumnHeader) {
				$(e.cell).html(
						function() {
							return $(e.cell).html()
									+ "<span style='color:red'>*</span>";
						});
			}
			// 必須項目チェック
			if (e.panel.cellType == wijmo.grid.CellType.Cell) {
				var value = flexGrid.getCellData(e.row, e.col, false);
				if (!value) {
					e.cell.classList.add('null-cell');
				}
			}
		}

	});

	// ガイドボタンの実現デモ
	flexGrid.formatItem.addHandler(function(s, e) {
		if (e.panel.cellType == wijmo.grid.CellType.Cell) {
			var editRange = grid.editRange;
			var row = e.row;
			var col = e.col;
			var isEdit = editRange && editRange.row == row
					&& editRange.col == col; // 編集モード

			// goodCd
			if (getColumnBinding(e.col) == 'setSubPartsNo') {
				wijmo.addClass(e.cell, 'guide-code-button-cell');
				// ガイドボタンセルを作成
				var button = createGoodsGuideButton(row, col);
				if (isEdit) {
					button.style.display = 'block';
					e.cell.appendChild(button);
				} else {
					e.cell.appendChild(button);
				}
			}

		}
	});

	// 行編集
	flexGrid.rowEditEnding.addHandler(function(sender, e) {
		isChanged = true;
	});

	// セル編集
	flexGrid.cellEditEnding
			.addHandler(function(sender, e) {

				if (flexGrid.getCellData(e.row, e.col) != flexGrid.activeEditor.value) {

					isChanged = true;

					if (flexGrid.rows[e.row].dataItem.hiddenArea == BLENUM.JudgeEnum.UnChange) {
						flexGrid.rows[e.row].dataItem.hiddenArea = BLENUM.JudgeEnum.Update;
					}
				}
				var binding = getColumnBinding(e.col);
				// セル編集したデータと初期データは同様場合
				if (gridInitData.length != 0
						&& gridInitData[e.row]
						&& flexGrid.activeEditor.value == gridInitData[e.row][e.col]) {
					isChanged = false;
				}
				var init = flexGrid.getCellData(e.row, e.col);
				if (binding == 'deleteFlg') {
					if (flexGrid.activeEditor.value == "") {
						flexGrid.setCellData(e.row, e.col, "", false);
					} else {
						flexGrid.setCellData(e.row, e.col, "削除する", false);
					}
				}
				// セット品名（半角）
				if (binding == 'setKanaName') {

					if (!checkHankaku(flexGrid.activeEditor.value)) {

						flexGrid.activeEditor.value = init;
						layer.alert(message.E00004.split("-")[1].replace("$1",
								"セット品名（半角）"), {
							title : message.E00004.split("-")[0],
							closeBtn : 0
						});
						return;
					}

					// 全角を半角に変換する
					flexGrid.activeEditor.value = zenkaku2Hankaku(flexGrid.activeEditor.value);
					// 文字桁数チェック
					if (flexGrid.activeEditor.value.length > 60) {
						flexGrid.activeEditor.value = init;
						layer.alert(message.E00003.split("-")[1].replace("$1",
								"セット品名（半角）").replace("$2", "60"), {
							title : message.E00003.split("-")[0],
							closeBtn : 0
						});
					}
				}
				// セット品名（全角）
				if (binding == 'setName') {
					// 文字桁数チェック
					if (flexGrid.activeEditor.value.length > 60) {
						flexGrid.activeEditor.value = init;
						layer.alert(message.E00003.split("-")[1].replace("$1",
								"セット品名（全角）").replace("$2", "60"), {
							title : message.E00003.split("-")[0],
							closeBtn : 0
						});
					}
				}
				// セットQTY
				if (binding == 'setQty') {
					if (flexGrid.activeEditor.value.length > 6) {
						flexGrid.activeEditor.value = init;
						layer.alert(message.E00003.split("-")[1].replace("$1",
								"セットQTY").replace("$2", "6"), {
							title : message.E00003.split("-")[0],
							closeBtn : 0
						});
					}
				}
				// 規格/特記,規格/特記（一般）
				if (binding == 'setSpecialNote'
						|| binding == 'primePartsSpecialNoteC') {
					if (flexGrid.activeEditor.value.length > 80) {
						flexGrid.activeEditor.value = init;
						layer.alert(message.E00003.split("-")[1].replace("$1",
								"特記事項").replace("$2", "80"), {
							title : message.E00003.split("-")[0],
							closeBtn : 0
						});
					}
				}

			});

	flexGrid.beginningEdit.addHandler(function(s, e) {
		if (e.panel.cellType == wijmo.grid.CellType.Cell) {
			if (isReadonlyCell(e.row, e.col)
					|| getColumnBinding(e.col) == "setSubPartsNo") {
				// NO.16 入力不可セルの制御
				e.cancel = true;
				return;
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
				var accessURL = baseurl + '/setlist/setPartsNo';
				var actionType = 'POST';
				var form = {
					setSubPartsNo : flexGrid.getCellData(row, col) == undefined ? ''
							: flexGrid.getCellData(row, col).split(':')[0],
					setMainPartsNo : '',
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
								placement : 'auto right', // TooltipのPosition
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
			// 規格/特記 規格/特記(一般)/削除理由
			if (binding == 'setSpecialNote'
					|| binding == 'primePartsSpecialNoteC'
					|| binding == 'deleteReason') {
				$(flexGrid.activeEditor).attr('maxlength', 80);
			}
			if (binding == 'startTime') {
				$(flexGrid.activeEditor).alphanum({
					allow : '/',
					allowSpace : false,
					allowNumeric : true,
					allowLatin : false,
					allowOtherCharSets : false,
					maxLength : 10
				});
				// oldData = $(flexGrid.activeEditor).val();
			}
			// QTY
			if (binding == 'setQty') {
				$(flexGrid.activeEditor).attr("id", "setQty");

				$(flexGrid.activeEditor).attr("oninput",
						"doubleInputForQTY(this.id)");
			}
		});

		var colBinding = getColumnBinding(c);

		if (colBinding == 'deleteFlg') {
			createGridComboBoxCell(r, c, cell, [ '', '削除する' ]);
		}

	};

	// グリッドのComboBox
	var createGridComboBoxCell = function(r, c, cell, itemsSource, classList) {

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

		// プルダウンフレームの状態を設定している場合の状態
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

	var activeRow = -1;

	flexGrid.selectionChanged.addHandler(function(s, e) {
		var range = e.range;
		if (activeRow >= 0 && activeRow < flexGrid.rows.length) {
			flexGrid.rowHeaders.setCellData(activeRow, 0, '');
		}
		activeRow = range.row;
		flexGrid.rowHeaders.setCellData(activeRow, 0, '\uf0da');
	});

	var rowInsertHandler = function() {
		var newRowData = {};
		newRowData
		if (flexGrid.selection.row <= 0) {
			newRowData.setDispOrder = 1;
		} else {
			newRowData.setDispOrder = flexGrid.itemsSource.items[activeRow].setDispOrder + 1;
		}

		newRowData.hiddenArea = BLENUM.JudgeEnum.Add;
		var cv = flexGrid.collectionView;

		$('.btn-del').removeAttr("disabled");
		$('.btn-copy').removeAttr("disabled");
		$('.btn-paste').removeAttr("disabled");
		$('.replaceGuide').removeAttr("disabled");
		if (activeRow >= 0) {
			Array.prototype.splice.apply(flexGrid.itemsSource.sourceCollection,
					[ activeRow + 1, 0 ].concat(newRowData));
		} else {
			cv.sourceCollection.push(newRowData);
		}

		for (var i = activeRow + 1; i < cv.totalItemCount; i++) {
			cv.items[i].setDispOrder = flexGrid.itemsSource.items[i - 1].setDispOrder + 1;
		}
		flexGrid.itemsSource.refresh();
		flexGrid.select(activeRow, 0);

	};

	var rowDeleteHandler = function() {
		if ($(this).hasClass("disabled")) {
			return;
		}

		if (BLENUM.ApplyConditionEnum.Apply == $('.applyCondition').val()) {
			layer.alert(message.E00012.split("-")[1].replace("$1", "申請中")
					.replace("$2", "セット"), {
				title : message.E00012.split("-")[0],
				closeBtn : 0
			});
			return;

		}
		if (BLENUM.ApplyConditionEnum.Approval == $('.applyCondition').val()) {
			layer.alert(message.E00012.split("-")[1].replace("$1", "承認済")
					.replace("$2", "セット"), {
				title : message.E00012.split("-")[0],
				closeBtn : 0
			});
			return;
		}
		// 選択行を削除
		var rows = flexGrid.selectedRows;
		var lastIndex = flexGrid.rows[flexGrid.selectedRows[0].index == 0 ? 0
				: flexGrid.selectedRows[0].index - 1].index;

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
			$('.btn-del').attr('disabled', 'true');
			$('.btn-copy').attr('disabled', 'true');
			$('.btn-paste').attr('disabled', 'true');
			$('.replaceGuide').attr('disabled', 'true');
		}
		var cv = flexGrid.collectionView;
		var count = cv.totalItemCount;
		var dispOrder = cv.items[lastIndex].setDispOrder;
		for (var i = lastIndex + 1; i < count; i++) {
			cv.items[i].setDispOrder = ++dispOrder;
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
			var selectRow = activeRow;

			copiedRowDataCloned[0].setDispOrder = flexGrid.itemsSource.items[selectRow].setDispOrder;
			copiedRowDataCloned[0].errorDetail = "";
			copiedRowDataCloned[0].hiddenArea = BLENUM.JudgeEnum.Add;
			// 行挿入
			Array.prototype.splice.apply(flexGrid.itemsSource.sourceCollection,
					[ activeRow, 1 ].concat(copiedRowDataCloned)); // insert
			// rows
			flexGrid.itemsSource.refresh();
			var cv = flexGrid.collectionView;
			var count = cv.totalItemCount;
			var cv = flexGrid.collectionView;
			var count = cv.totalItemCount;
			for (var i = 0; i < count; i++) {
				cv.items[i].no = i + 1;
			}
		}
	};
	// チェーク
	var checkHandler = function() {
		var items = flexGrid.itemsSource.items;
		var count = items.length;
		var isCheckedOk = true;
		// BLコードが未入力の場合
		if ($('.bl-code').val() == undefined || $('.bl-code').val() == null
				|| $('.bl-code').val() == '') {
			layer.alert(message.E00001.split("-")[1].replace("$1", "BLコード"), {
				title : message.E00001.split("-")[0],
				closeBtn : 0
			});

			isCheckedOk = false;
		}
		// セレクトコードが未入力の場合
		if ($('.select-code').val() == undefined
				|| $('.select-code').val() == null
				|| $('.select-code').val() == '') {
			layer.alert(message.E00001.split("-")[1].replace("$1", "セレクトコード"),
					{
						title : message.E00001.split("-")[0],
						closeBtn : 0
					});

			isCheckedOk = false;
		}

		// セット親品番が未入力の場合
		if ($('.set-main-parts-no').val() == undefined
				|| $('.set-main-parts-no').val() == null
				|| $('.set-main-parts-no').val() == '') {
			layer.alert(message.E00001.split("-")[1].replace("$1", "セット親品番"), {
				title : message.E00001.split("-")[0],
				closeBtn : 0
			});

			isCheckedOk = false;
		}
		var arr = new Array();
		for (var i = 0; i < count; i++) {
			items[i].errorDetail = "";
			arr[i] = items[i].setDispOrder;
			// セット子品番が未入力の場合
			if (items[i].setSubPartsNo == undefined
					|| items[i].setSubPartsNo == null
					|| items[i].setSubPartsNo == '') {
				items[i].errorDetail = items[i].errorDetail
						+ message.E00001.split("-")[1].replace("$1", "セット子品番");
				isCheckedOk = false;
			}
			// セットQTYが未入力の場合
			if (items[i].setQty == undefined || items[i].setQty == null
					|| items[i].setQty == '') {

				items[i].errorDetail = items[i].errorDetail
						+ message.E00002.split("-")[1].replace("$1", "QTY");
				isCheckedOk = false;
			}
			// 優良部品名称が未入力の場合
			if (items[i].setName == undefined || items[i].setName == null
					|| items[i].setName == '') {
				items[i].errorDetail = items[i].errorDetail
						+ message.E00002.split("-")[1].replace("$1", "優良部品名称");
				isCheckedOk = false;
			}
			// 優良部品カナ名称が未入力の場合
			if (items[i].setKanaName == undefined
					|| items[i].setKanaName == null
					|| items[i].setKanaName == '') {
				items[i].errorDetail = items[i].errorDetail
						+ message.E00002.split("-")[1]
								.replace("$1", "優良部品カナ名称");
				isCheckedOk = false;
			}
			// 適用日時が未入力の場合
			if (items[i].startTime == undefined || items[i].startTime == null
					|| items[i].startTime == '') {
				items[i].errorDetail = items[i].errorDetail
						+ message.E00002.split("-")[1].replace("$1", "適用日時");
				isCheckedOk = false;
			}
		}

		// 優良子品番表示順位重複チェック
		var nary = arr.sort();
		for (var i = 0; i < nary.length; i++) {

			if (nary[i] == nary[i + 1]) {
				items[i].errorDetail = items[i].errorDetail
						+ message.E00603.split("-")[1];
				isCheckedOk = false;
			}
		}
		return isCheckedOk;

	};

	// セット詳細のグリッドデータをセット一覧へマージ
	var getChangeHandler = function() {

		if (!isChanged) {
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		}

		var grid = parent.document.getElementById("grid")["wj-Control"];

		var gridData = flexGrid.itemsSource.items;
		var no = grid.selection.row;
		for (var i = 0; i < gridData.length; i++) {

			if (BLENUM.ApplyConditionEnum.NoApply == $(
					'.applyCondition option:selected').val()) {
				gridData[i].applyCondition = "未";
			}
			if (BLENUM.ApplyConditionEnum.Apply == $(
					'.applyCondition option:selected').val()) {
				gridData[i].applyCondition = "中";
			}
			if (BLENUM.ApplyConditionEnum.Approval == $(
					'.applyCondition option:selected').val()) {
				gridData[i].applyCondition = "済"
			}
			if (BLENUM.ApplyConditionEnum.Applyagain == $(
					'.applyCondition option:selected').val()) {
				gridData[i].applyCondition = "再"
			}

			if (BLENUM.ManageKbnEnum.Add == $('.del-kubun option:selected')
					.val()) {
				gridData[i].manageKbn = "追"
			}
			if (BLENUM.ManageKbnEnum.Update == $('.del-kubun option:selected')
					.val()) {
				gridData[i].manageKbn = "更"
			}
			if (BLENUM.ManageKbnEnum.Delete == $('.del-kubun option:selected')
					.val()) {
				gridData[i].manageKbn = "削"
			}

			gridData[i].setMainPartsNo = $('.set-main-parts-no').val();
			gridData[i].prmSetDtlNo1 = $('.select-code option:selected').text();
			gridData[i].goodsMGroup = $('.classify-code').val();
			gridData[i].tbsPartsCode = $('.bl-code option:selected').text();
			gridData[i].insDtTime = new Date().Format('yyyy/MM/dd HH:mm');
			gridData[i].updDtTime = new Date().Format('yyyy/MM/dd HH:mm');
		}

		var parentIndex = undefined;
		var deleteCount = 0;
		var count = grid.itemsSource.items.length;
		for (var i = 0; i < count; i++) {

			if (grid.itemsSource.items[i].prmSetDtlNo1 == $(
					'.select-code option:selected').text()
					&& grid.itemsSource.items[i].setMainPartsNo == $(
							'.set-main-parts-no').val()
					&& grid.itemsSource.items[i].goodsMGroup == $(
							'.classify-code').val()) {
				if (parentIndex == undefined) {
					parentIndex = i;
				}

			}
		}
		gridData.sort(function(a, b) {
			return a.setDispOrder - b.setDispOrder;
		});

		Array.prototype.splice.apply(grid.itemsSource.sourceCollection, [
				parentIndex, sessionStorage.getItem("initListSize") ]
				.concat(gridData)); // insert

		for (var i = 0; i < count; i++) {
			grid.itemsSource.items[i].no = i + 1;
		}

		grid.itemsSource.refresh();
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
	};
	// 画面の初期化
	var pageInit = function(mode) {
		// 参照モード
		if (BLENUM.ModeEnum.Readonly == mode || BLENUM.ModeEnum.Update == mode) {
			flexGrid.isReadOnly = true;
			$(".goods-maker-code").addClass("disabled");
			$(".bl-code").attr("disabled", "true");
			$(".select-code").attr("disabled", "true");
			$(".classifyCdGuide").attr("disabled", "true");
			$(".applyCondition").addClass("disabled");
			$(".del-kubun").addClass("disabled");
			$(".setParentCdGuide").addClass("disabled");
			$(".classify-code").addClass("disabled");
			$(".btn-add").hide();
			$(".btn-del").hide();
			$(".btn-copy").hide();
			$(".btn-paste").hide();
			$(".replaceGuide").hide();
			$(".btn-confirm").hide();
		}
		// エラーモード
		if (BLENUM.ModeEnum.Error == mode) {
			$(".btn-add").hide();
			$(".btn-del").hide();
			$(".btn-copy").hide();
			$(".btn-paste").hide();
		}
		// 更新モード
		if (BLENUM.ModeEnum.New == mode) {
			$(".select-code").attr("disabled", "true");
			$(".setParentCdGuide").attr("disabled", "true");
		}
	}

});
