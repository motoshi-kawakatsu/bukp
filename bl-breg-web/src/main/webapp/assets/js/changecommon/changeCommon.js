/**
 * @file 置換
 */
$(function() {
	'use strict';

	var mode = parent.layer.mode;
	var grid = parent.document.getElementById("grid")["wj-Control"];
	var changeItems = getChangeItems();
	var selectedValue;
	var selectedItem;
	getSelectedInfo();
	var ReplaceGuide = function() {
	};

	ReplaceGuide.prototype = {
		init : function() {
			this.getData();
			$('.search').click($.proxy(this.search, this));
			$('.replace').click($.proxy(this.replace, this));
			$('.changeItem').change($.proxy(this.change, this));
			$('.closeup').click($.proxy(this.close, this));
			$('.guide').click($.proxy(this.showGuide, this));
			$('div.guideButton input').focus($.proxy(this.focus, this));
			$('div.guideButton input').blur($.proxy(this.blur, this));
			$('div.textBox input').focus($.proxy(this.textFocus, this));
			$('div.textBox input').blur($.proxy(this.textBlur, this));
		},

		getData : function() {
			$('.changeItem').focus();
			for ( var key in changeItems) {
				if (selectedItem == changeItems[key]) {
					$('select.changeItem').append(
							"<option value='' selected='selected'>"
									+ formatWithStarKey(changeItems[key])
									+ "</option>");
				} else {
					$('select.changeItem').append(
							"<option value='' >"
									+ formatWithStarKey(changeItems[key])
									+ "</option>");
				}
			}
			setInputDisplay(selectedItem);
		},

		search : function() {
			var accessURL = baseurl + "/changecommon/search";
			var form = {
				searchItem : getChangeItem(),
				searchValue : getChangeBeforeValue(),
				searchCount : getCount()
			}
			$.blAjax({
				url : accessURL,
				type : "POST",
				data : JSON.stringify(form),
				error : function(data, httpStatus, errorHandler) {
					errorHandler(data, httpStatus);
				},
				success : function(data) {
					layer.alert(data.message, {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
				},
			});
		},

		replace : function() {
			var accessURL = baseurl + "/changecommon/replace";
			var form = {
				checkValue : getChangeAfterValue(),
				checkItem : getChangeItem(),
				mustFlag : $('.changeItem option:selected').text().indexOf("*") > -1 ? true
						: false
			}
			$.blAjax({
				url : accessURL,
				type : "POST",
				data : JSON.stringify(form),
				error : function(data, httpStatus, errorHandler) {
					layer.alert(data.errorInfo.errors[0].message, {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ],
						end : function() {
							$('.changeAfter').val("");
						}
					});
					errorHandler(data, httpStatus);
				},
				success : function(data) {
					layer.confirm(data.message, {
						icon : 3,
						title : '',
						closeBtn : 0,
						btn : [ 'はい', 'いいえ' ]
					}, replace);
				},
			});

			var replace = function(index) {
				for (var i = 0; i < grid.rows.length; i++) {
					if ((null == grid.rows[i].dataItem[getChangeKey()] && "" == getChangeBeforeValue())
							|| grid.rows[i].dataItem[getChangeKey()] == getChangeBeforeValue()) {
						switch (mode) {
						case BLENUM.MenuEnum.Item:
							if ('中' != grid.rows[i].dataItem['applyStep']
									&& (0 == grid.rows[i].dataItem['hiddenArea'] || 'selCode' != getChangeKey()
											&& 'goodsNo' != getChangeKey()
											&& 'setMainPartsNo' != getChangeKey())) {
								grid.rows[i].dataItem[getChangeKey()] = getChangeAfterValue();
							}
							break;
						case BLENUM.MenuEnum.Set:
							if ('中' != grid.rows[i].dataItem['applyCondition']
									&& (0 == grid.rows[i].dataItem['hiddenArea'] || 'prmSetDtlNo1' != getChangeKey()
											&& 'setMainPartsNo' != getChangeKey()
											&& 'setDispOrder' != getChangeKey())) {
								grid.rows[i].dataItem[getChangeKey()] = getChangeAfterValue();
							}
							break;
						case BLENUM.MenuEnum.Union:
							if ('中' != grid.rows[i].dataItem['applyCondition']
									&& (0 == grid.rows[i].dataItem['hiddenArea'] || 'prmSetDtlNo1' != getChangeKey()
											&& 'tbsPartsCode' != getChangeKey()
											&& 'prmSetDtlNo2' != getChangeKey()
											&& 'joinSourPartsNoWithH' != getChangeKey()
											&& 'joinDispOrder' != getChangeKey())) {
								grid.rows[i].dataItem[getChangeKey()] = getChangeAfterValue();
							}
							break;
						case BLENUM.MenuEnum.setdetail:
							if (0 == grid.rows[i].dataItem['hiddenArea']
									|| 'joinDispOrder' != getChangeKey()) {
								grid.rows[i].dataItem[getChangeKey()] = getChangeAfterValue();
							}
							break;
						case BLENUM.MenuEnum.joindetail:
							if (0 == grid.rows[i].dataItem['hiddenArea']
									|| 'prmSetDtlNo2' != getChangeKey()
									&& 'joinDispOrder' != getChangeKey()) {
								grid.rows[i].dataItem[getChangeKey()] = getChangeAfterValue();
							}
							break;
						default:
							break;
						}
						if (1 == grid.rows[i].dataItem['hiddenArea']) {
							grid.rows[i].dataItem['hiddenArea'] = 2;
						}
					}
				}
				grid.refresh(false);
				layer.close(index);
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}
		},

		change : function() {
			try {
				var changeItemValue = getChangeItem();
				setInputDisplay(changeItemValue);
				clear();
			} catch (e) {
				sendClientErrorLog(e);
			}
		},

		close : function() {
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		},

		showGuide : function(event) {
			var accessURL = baseurl + "/changecommon/showGuide";
			var inputName = event.currentTarget.name;
			var sessionValue;
			switch (getChangeItem()) {
			case "優良品番":
			case "セット親品番":
			case "セット子品番":
				sessionValue = "maker";
				break;
			case "純正品番":
				sessionValue = "pure";
				break;
			default:
				break;
			}
			var form = {
				guideValue : inputName == "changeBefore" ? getChangeBeforeValue()
						: getChangeAfterValue(),
				sessionValue : sessionValue
			};

			$.blAjax({
				url : accessURL,
				type : "POST",
				data : JSON.stringify(form),
				error : function(data, httpStatus, errorHandler) {
					errorHandler(data, httpStatus);
				},
				success : function(data) {
					if (contains([ '優良品番', '純正品番', 'セット親品番', 'セット子品番' ],
							getChangeItem())) {
						if(mode == BLENUM.MenuEnum.joindetail){
							var parentFrame = null;
				            layer.inputName = inputName;
				            layer.isGrid = false;
				            layer.colRow = '';
				            layer.open({
				                type: 2,
				                title: false,
				                closeBtn: 0,
				                skin: 'layer-ext-skin',
				                shade: 0.1,
				                area: ['1000px', '500px'],
				                content: [baseurl+"/goodsguide/goodsGuide"],
				                success: function(layero,index){
				            		var i = index;
				            	},
				            	end : function(){
				            		var val = $("input[name="+layer.inputName+"]").val();
				            		if(val.indexOf("：")>0){
				            			$("input[name="+layer.inputName+"]").val(val.split("：")[0]);
				            		}
				            		layer.inputName = undefined;
				            	},
				            });
						}else{
							new BLUI.GoodsGuide().show(inputName, false, '');
						}
					} else {
						new BLUI.ClassifyCdGuide().show(inputName, false, '');
					}
				},
			});
		},

		focus : function(event) {
			if (event.currentTarget.readOnly) {
				return;
			}
			var value = event.currentTarget.value;
			value = value.split("：")[0];
			event.currentTarget.value = value;
		},

		blur : function(event) {
			if (event.currentTarget.readOnly) {
				return;
			}
			var accessURL = baseurl + "/changecommon/getCode";
			var form = {
				code : event.currentTarget.value
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
		},

		textFocus : function(event) {
			var changeItem = getChangeItem();
			if ("価格（税抜）" == changeItem) {
				var value = event.currentTarget.value.replace(/,/g, "");
				event.currentTarget.value = value;
			}
		},

		textBlur : function(event) {
			var changeItem = getChangeItem();
			var value = event.currentTarget.value;
			if ("価格（税抜）" == changeItem) {
				while (value.length > 1 && value.indexOf("0") == 0) {
					value = value.replace(/0/, "");
				}
				var length = value.length;
				if (length > 3) {
					for (var i = length - 3; i > 0; i -= 3) {
						var value1 = value.substr(0, i);
						var value2 = value.substr(i, value.length);
						value = value1 + "," + value2;
					}
				}
				event.currentTarget.value = value;
			}
			if ("QTY" == changeItem) {
				if (value.indexOf(".") > -1
						&& value.indexOf(".") == value.length - 2) {
					value = value.concat("0");
					event.currentTarget.value = value;
				}
			}
			if ("品名（半角）" == changeItem || "セット品名（半角）" == changeItem) {
				if (!checkHankaku(value)) {
					layer.alert('errorMsg', {
						title : '',
						closeBtn : 0,
						btn : [ 'はい' ]
					});
					event.currentTarget.value = "";
				} else {
					event.currentTarget.value = zenkaku2Hankaku(value);
				}
			}
		}
	};

	var page = new ReplaceGuide();
	page.init();

	function setInputDisplay(changeItemValue) {
		var guideButtonList = [ '分類コード名称', 'セット親品番', 'セット子品番', '純正品番' ];
		var dropDownList = [ 'セレクトコード名称', 'BLコード名称', 'カーコード名称', '種別コード名称',
				'削除依頼区分', 'OPENプライス', '層別', '梱包単位', '重量単位', '種別コード' ];
		if (mode == BLENUM.MenuEnum.Union || mode == BLENUM.MenuEnum.joindetail) {
			guideButtonList.push('優良品番');
		}
		$('.guideButton').hide();
		$('.dropDown').hide();
		$('.textBox').hide();
		if (contains(guideButtonList, changeItemValue)) {
			$('.guideButton').show();
			$('.guideButton .form-control').val(selectedValue);
			setGuideFormat();
		} else if (contains(dropDownList, changeItemValue)) {
			var changeList = getChangeList();
			$('.dropDown').show();
			$('.dropDown select').empty();
			for ( var key in changeList) {
				$('.dropDown select').append(
						"<option value='" + changeList[key] + "'>"
								+ changeList[key] + "</option>");
			}
			$(".dropDown .form-control").val(selectedValue);
		} else {
			$('.textBox').show();
			$('.textBox .form-control').val(selectedValue);
			setTextBoxFormat();
		}
	}

	function clear() {
		$('.guideButton .form-control').val("");
		$('.dropDown .form-control').get(0).selectedIndex = 0;
		$('.dropDown .form-control').get(1).selectedIndex = 0;
		$('.textBox .form-control').val("");
	}

	function contains(arr, obj) {
		var i = arr.length;
		while (i--) {
			if (arr[i] === obj) {
				return true;
			}
		}
		return false;
	}

	function getChangeBeforeValue() {
		if (!$('.dropDown').is(":hidden")) {
			return $('.changeBefore option:selected').text();
		}
		if (!$('.guideButton').is(":hidden")) {
			return $('.guideButton .changeBefore').val();
		}
		return $('.textBox .changeBefore').val();
	}

	function getChangeAfterValue() {
		if (!$('.dropDown').is(":hidden")) {
			return $('.changeAfter option:selected').text();
		}
		if (!$('.guideButton').is(":hidden")) {
			return $('.guideButton .changeAfter').val();
		}
		return $('.textBox .changeAfter').val();
	}

	function getChangeItems() {
		var items = [];
		for (var i = 0; i < grid.columns.length; i++) {
			var col = grid.columns[i];
			if (!col.isReadOnly || col.header == "分類コード名称"
					|| col.header == "純正品番" || col.header == "優良品番") {
				items.push(col.header);
			}
		}
		return items;
	}

	function getMustItems() {
		var items;
		if (mode == BLENUM.MenuEnum.Item) {
			items = [ 'セレクトコード名称', '分類コード名称', 'BLコード名称', '優良品番', '品名（半角）',
					'品名（全角）', '価格（税抜）', 'OPENプライス', '適用日時' ];
		}
		if (mode == BLENUM.MenuEnum.Set) {
			items = [ 'セレクトコード名称', '分類コード名称', 'BLコード名称', 'セット親品番', '表示順位',
					'セット子品番', 'セット品名（半角）', 'セット品名（全角）', 'QTY', '適用日時' ];
		}
		if (mode == BLENUM.MenuEnum.Union) {
			items = [ 'セレクトコード名称', '分類コード名称', 'BLコード名称', 'カーコード名称', '純正品番',
					'種別コード名称', '表示順位', '優良品番', '適用日時' ];
		}
		if (mode == BLENUM.MenuEnum.setdetail) {
			items = [ '表示順位', 'セット子品番', 'セット品名（半角）', 'セット品名（全角）', 'QTY', '適用日時' ];
		}
		if (mode == BLENUM.MenuEnum.joindetail) {
			items = [ '種別コード', '表示順位', '優良品番', '適用日時' ];
		}
		return items;
	}

	function getSelectedInfo() {
		var _selectedIndex = grid.selection.col;
		if (_selectedIndex == -1
				|| !contains(changeItems, grid.columns[_selectedIndex].header)) {
			selectedValue = "";
			selectedItem = changeItems[0];
		} else {
			var _selectedColumn = grid.columns[_selectedIndex];
			selectedValue = grid.selectedRows[0].dataItem[_selectedColumn.binding];
			selectedItem = _selectedColumn.header;
		}
	}

	function getCount() {
		var count = 0;
		var changeBeforeValue = getChangeBeforeValue();
		var rows = grid.rows;
		for (var i = 0; i < rows.length; i++) {
			if (changeBeforeValue == rows[i].dataItem[getChangeKey()]) {
				count++;
			}
		}
		return count;
	}

	function getChangeList() {
		var changeItemValue = getChangeItem();
		var itemsSource;

		var blStr;
		var selectStr;
		var partsStr;
		var kindCodeStr;
		var carmakerCodeStr;
		if (mode == BLENUM.MenuEnum.Item) {
			blStr = grid.viewData.blStr.split(",");
			selectStr = grid.viewData.selectStr.split(",");
			partsStr = grid.viewData.partsStr.split(",");
		} else if (mode == BLENUM.MenuEnum.Set) {
			blStr = grid.viewData.blStr;
			selectStr = grid.viewData.selectStr;
		} else if (mode == BLENUM.MenuEnum.Union) {
			blStr = grid.viewData.blStr.split(",");
			selectStr = grid.viewData.selectStr.split(",");
			kindCodeStr = grid.viewData.kindCodeStr.split(",");
			carmakerCodeStr = grid.viewData.carmakerCodeStr.split(",");
		} else if (mode == BLENUM.MenuEnum.joindetail) {
			kindCodeStr = grid.viewData.kindCodeStr.split(",");
		}

		if (changeItemValue == "セレクトコード名称") {
			itemsSource = selectStr;
		} else if (changeItemValue == "BLコード名称") {
			itemsSource = blStr;
		} else if (changeItemValue == "OPENプライス") {
			itemsSource = [ '', '通常', 'オープン価格' ];
		} else if (changeItemValue == "層別") {
			itemsSource = partsStr;
		} else if (changeItemValue == "削除依頼区分") {
			itemsSource = [ '', '削除する' ];
		} else if (changeItemValue == "梱包単位") {
			itemsSource = [ '', 'mm', 'cm', 'm' ];
		} else if (changeItemValue == "重量単位") {
			itemsSource = [ '', 'g', 'kg', 't' ];
		} else if (changeItemValue == "種別コード名称" || changeItemValue == "種別コード") {
			itemsSource = kindCodeStr;
		} else if (changeItemValue == "カーコード名称") {
			itemsSource = carmakerCodeStr;
		}
		return itemsSource;
	}

	function formatWithStarKey(changeItem) {
		if (contains(getMustItems(), changeItem)) {
			return changeItem + "*";
		}
		return changeItem;
	}

	function getChangeItem() {
		var result = $('.changeItem option:selected').text();
		if (result.indexOf("*" == result.length - 1)) {
			result = result.replace("*", "");
		}
		return result;
	}

	function getChangeKey() {
		var changeItemValue = getChangeItem();
		for (var i = 0; i < grid.columns.length; i++) {
			if (grid.columns[i].header == changeItemValue) {
				return grid.columns[i].binding;
			}
		}
		return "";
	}
	
	function setGuideFormat() {
		if ("分類コード名称" == getChangeItem()) {
			setFormatCommon(".guideButton .form-control", "value",
					"numInput(this.id)");
			$(".guideButton .form-control")[0]
					.setAttribute("readOnly", "false");
			$(".guideButton .form-control")[1]
					.setAttribute("readOnly", "false");
			$(".guideButton .form-control")[0].className = $(".guideButton .form-control")[0].className
					.replace(" input-readonly", "");
			$(".guideButton .form-control")[1].className = $(".guideButton .form-control")[1].className
					.replace(" input-readonly", "");
		} else {
			removeFormatCommon(".guideButton .form-control");
			$(".guideButton .form-control")[0].setAttribute("readOnly", "true");
			$(".guideButton .form-control")[1].setAttribute("readOnly", "true");
			$(".guideButton .form-control")[0].className += " input-readonly";
			$(".guideButton .form-control")[1].className += " input-readonly";
		}
	}

	function setTextBoxFormat() {
		removeFormatCommon(".guideButton .form-control");
		var changeItem = getChangeItem();
		var maxLength;
		switch (changeItem) {
		// 商品一覧
		case "JAN":
			maxLength = 13;
			setFormatCommon(".textBox .form-control", "value",
					"numInput(this.id)");
			break;
		case "長さ":
			maxLength = 6;
			setFormatCommon(".textBox .form-control", "value",
					"numInput(this.id)");
			break;
		case "幅":
			maxLength = 6;
			setFormatCommon(".textBox .form-control", "value",
					"numInput(this.id)");
			break;
		case "高さ":
			maxLength = 6;
			setFormatCommon(".textBox .form-control", "value",
					"numInput(this.id)");
			break;
		case "梱包（長さ）":
			maxLength = 6;
			setFormatCommon(".textBox .form-control", "value",
					"numInput(this.id)");
			break;
		case "梱包（幅）":
			maxLength = 6;
			setFormatCommon(".textBox .form-control", "value",
					"numInput(this.id)");
			break;
		case "梱包（高さ）":
			maxLength = 6;
			setFormatCommon(".textBox .form-control", "value",
					"numInput(this.id)");
			break;
		case "重量":
			maxLength = 6;
			setFormatCommon(".textBox .form-control", "value",
					"numInput(this.id)");
			break;
		case "表示順位":
			maxLength = 4;
			setFormatCommon(".textBox .form-control", "value",
					"numInput(this.id)");
			break;
		case "価格（税抜）":
			maxLength = 12;
			setFormatCommon(".textBox .form-control", "value",
					"numInput(this.id)");
			break;
		case "QTY":
			maxLength = 6;
			setFormatCommon(".textBox .form-control", "value",
					"doubleInputForQTY(this.id)");
			break;
		case "品名（半角）":
			maxLength = 60;
			removeFormatCommon(".textBox .form-control");
			break;
		case "セット品名（半角）":
			maxLength = 60;
			removeFormatCommon(".textBox .form-control");
			break;
		case "品名（全角）":
			maxLength = 60;
			removeFormatCommon(".textBox .form-control");
			break;
		case "セット品名（全角）":
			maxLength = 60;
			removeFormatCommon(".textBox .form-control");
			break;
		case "優良品番":
			maxLength = 24;
			removeFormatCommon(".textBox .form-control");
			break;
		case "装備":
			maxLength = 60;
			removeFormatCommon(".textBox .form-control");
			break;
		case "規格/特記":
			maxLength = 80;
			removeFormatCommon(".textBox .form-control");
			break;
		case "規格/特記（一般）":
			maxLength = 80;
			removeFormatCommon(".textBox .form-control");
			break;
		case "削除理由":
			maxLength = 80;
			removeFormatCommon(".textBox .form-control");
			break;
		case "商品詳細":
			maxLength = 512;
			removeFormatCommon(".textBox .form-control");
			break;
		case "商品詳細（一般）":
			maxLength = 512;
			removeFormatCommon(".textBox .form-control");
			break;
		case "URL1":
			maxLength = 512;
			removeFormatCommon(".textBox .form-control");
			break;
		case "URL2":
			maxLength = 512;
			removeFormatCommon(".textBox .form-control");
			break;
		case "URL3":
			maxLength = 512;
			removeFormatCommon(".textBox .form-control");
			break;
		case "適用日時":
			maxLength = 16;
			removeFormatCommon(".textBox .form-control");
			break;
		default:
			removeFormatCommon(".textBox .form-control");
			break;
		}
		setMaxLength(maxLength);

		if ("適用日時" == changeItem) {
			$(".textBox .form-control").datetimepickerJp({
				format : 'YYYY/MM/DD HH:mm'
			});
			$(".textBox .form-control")[0].setAttribute("name", "dateTime");
		} else if ("dateTime" == $(".textBox .form-control")[0]
				.getAttribute("name")) {
			$(".textBox .form-control").datetimepickerJp('destroy');
			$(".textBox .form-control")[0].removeAttribute("name");
		}
	}

	function setFormatCommon(className, id, oninput) {
		$(className)[0].setAttribute("id", id + "Before");
		$(className)[0].setAttribute("oninput", oninput);
		$(className)[1].setAttribute("id", id + "After");
		$(className)[1].setAttribute("oninput", oninput);
	}
	function removeFormatCommon(className) {
		$(className)[0].removeAttribute("id");
		$(className)[0].removeAttribute("oninput");
		$(className)[0].onpaste = null;
		$(className)[1].removeAttribute("id");
		$(className)[1].removeAttribute("oninput");
		$(className)[0].onpaste = null;
	}
	function setMaxLength(maxLength) {
		$(".textBox .form-control")[0].setAttribute("maxLength", maxLength);
		$(".textBox .form-control")[1].setAttribute("maxLength", maxLength);
	}
	function removeMaxLength() {
		$(".textBox .form-control")[0].removeAttribute("maxLength");
		$(".textBox .form-control")[1].removeAttribute("maxLength");
	}
});
