$(function() {
    'use strict';
    var goodsPage = 1;
    var setPage = 1;
    var joinPage = 1;
    var goodsCounts = 0;
    var setCounts = 0;
    var joinCounts = 0;
    var goodsRows = 0;
    var setRows = 0;
    var joinRows = 0;
    var message = [];
    var grid;
    var setGrid;
    var joinGrid;
    var goodslist;
    var setlist;
    var joinlist;
    var showItem;
	var getColumnBinding = function(col) {
		return grid.cells.columns[col].binding;
	};
	var getSetColumnBinding = function(col) {
		return setGrid.cells.columns[col].binding;
	};
	var getJoinColumnBinding = function(col) {
		return joinGrid.cells.columns[col].binding;
	};

    BLUI.CheckList = function() {
        this.mode = sessionStorage.getItem("checkList");
        this.grid = grid;
        this.setGrid = setGrid;
        this.joinGrid = joinGrid;
        this.goodslist = goodslist;
        this.setlist = setlist;
        this.joinlist = joinlist;
        this.showItem = showItem;
    };
    BLUI.CheckList.prototype = {
        init : function() {
            this.mode = sessionStorage.getItem("checkList");

            switch (parseInt(this.mode)) {

            case BLENUM.MenuEnum.Item:
            	navigation.getData(12);
            case BLENUM.MenuEnum.goodscorrect:
                itemInit(goodsPage);
                showGoodViewgrid();
                $("header")[0].style.display = "none";
                $("nav")[0].style.display = "none";
                $("ol")[0].style.display = "none";
                $("footer")[0].style.display = "none";
                $('#myTab li:eq(1)').hide();
                $('#myTab li').last().hide();
                $('#item').addClass('active');
                $('.tab_grid')[0].style.display = "none";
                showItem = 1;
                break;
            case BLENUM.MenuEnum.Set:
            	navigation.getData(14);
            case BLENUM.MenuEnum.setcorrect:
                setInit(setPage);
                showSetViewgrid();
                $("header")[0].style.display = "none";
                $("nav")[0].style.display = "none";
                $("ol")[0].style.display = "none";
                $("footer")[0].style.display = "none";
                $('#myTab li:eq(1)').addClass('active')
                $('#myTab li').first().hide();
                $('#myTab li').last().hide();
                $('#set').addClass('active');
                $('.tab_grid')[0].style.display = "none";
                showItem = 2;
                break;
            case BLENUM.MenuEnum.Union:
            	navigation.getData(16);
            case BLENUM.MenuEnum.joincorrect:
                unionInit(joinPage);
                showJoinViewGrid();
                $("header")[0].style.display = "none";
                $("nav")[0].style.display = "none";
                $("ol")[0].style.display = "none";
                $("footer")[0].style.display = "none";
                $('#myTab li').last().addClass('active');
                $('#myTab li').first().hide();
                $('#myTab li:eq(1)').hide();
                $('#union').addClass('active');
                $('.tab_grid')[0].style.display = "none";
                showItem = 3;
                break;
            default:
            	navigation.getData(6);
                itemInit(goodsPage);
                setInit(setPage);
                unionInit(joinPage);
                showGoodsGrid();
                $('#myTab li').first().addClass('active');
                $('#item').addClass('active');
                showItem = 1;
                $('#myTab li').first().click(function() {
                    showGoodsGrid();
                    grid.invalidate();
                    showItem = 1;
                });

                $('#myTab li:eq(1)').click(function() {
                    showSetGrid();
                    setGrid.invalidate();
                    showItem = 2;
                });
                $('#myTab li').last().click(function() {
                    showJoinGrid();
                    joinGrid.invalidate();
                    showItem = 3;
                });
                break;
            }
            $('.classifyCdGuide').on('click', '', {},
                    $.proxy(this.showClassifyGuide, this));

            $(".btn-back")
                    .click(
                            function() {
                                if (parseInt(sessionStorage
                                        .getItem("checkList")) == BLENUM.MenuEnum.TopMenu) {
                                    history.go(-1);
                                } else {
                                    var index = parent.layer
                                            .getFrameIndex(window.name);
                                    parent.layer.close(index);
                                }
                            });

        },

        // / <summary>
        // / popup guide
        // / </summary>
        showClassifyGuide : function(event) {
            var guide = new BLUI.CheckListOutPut();
            guide.show(showItem);
        },
    };

    // 画面初期化
    var page = new BLUI.CheckList();
    var navigation = new BLUI.Nabigeshon();

    page.init();

    function itemInit(nowPage) {
        // 商品
        var recid = 1;
        grid = new wijmo.grid.FlexGrid('#item');
        grid
                .initialize({
                    allowSorting : false,
                    autoGenerateColumns : false,
                    columns : [ {
                        header : 'No.',
                        binding : 'no',
                        width : 50,
                        isReadOnly : true
                    }, {
                        header : 'エラー区分',
                        binding : 'errSec',
                        width : 100,
                        isReadOnly : true
                    }, {
                        header : 'エラー内容',
                        binding : 'errCon',
                        width : 130,
                        isReadOnly : true
                    }, {
                        header : '申請状態',
                        binding : 'applyStep',
                        isReadOnly : true
                    }, {
                        header : '処理区分',
                        binding : 'manageSec',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : 'セレクトコード名称',
                        binding : 'selCode',
                        width : 140,
                        isReadOnly : true
                    }, {
                        header : '分類コード名称',
                        binding : 'secCodeName',
                        width : 140,
                        isReadOnly : true
                    }, {
                        header : 'BLコード名称',
                        binding : 'blCodeName',
                        width : 140,
                        isReadOnly : true
                    }, {
                        header : '優良品番',
                        binding : 'goodsNo',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : '品名（半角）',
                        binding : 'nameS',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : '品名（全角）',
                        binding : 'nameB',
                        width : 120,
                        minWidth : 30,
                        isReadOnly : true
                    }, {
                        header : '価格（税抜）',
                        binding : 'money',
                        dataType : wijmo.DataType.Number,
                        isReadOnly : true
                    }, {
                        header : 'OPENプランス',
                        binding : 'open',
                        width : 100,
                        isReadOnly : true
                    }, {
                        header : 'JAN',
                        binding : 'jan',
                        width : 60,
                        isReadOnly : true
                    }, {
                        header : '層別',
                        binding : 'layer',
                        width : 60,
                        isReadOnly : true
                    }, {
                        header : '装備',
                        binding : 'equip',
                        width : 60,
                        isReadOnly : true
                    }, {
                        header : '規格/特記.',
                        binding : 'size',
                        width : 130,
                        isReadOnly : true
                    }, {
                        header : '規格/特記（一般）',
                        binding : 'sizeCon',
                        width : 150,
                        isReadOnly : true
                    }, {
                        header : '削除依頼区分',
                        binding : 'delSec',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : '削除理由',
                        binding : 'delCon',
                        width : 140,
                        isReadOnly : true
                    }, {
                        header : '商品詳細',
                        binding : 'goods',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : '商品詳細（一般）',
                        binding : 'goodsCon',
                        width : 100,
                        isReadOnly : true
                    }, {
                        header : '長さ',
                        binding : 'width1',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : '幅',
                        binding : 'width2',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : '高さ',
                        binding : 'width3',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : '梱包（長さ）',
                        binding : 'packwidth1',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : '梱包（幅）',
                        binding : 'packwidth2',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : '梱包（高さ）',
                        binding : 'packwidth3',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : '梱包単位',
                        binding : 'widthUnit',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : '重量',
                        binding : 'weight',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : '重量単位',
                        binding : 'weightUnit',
                        width : 60,
                        isReadOnly : true
                    }, {
                        header : 'URL1',
                        binding : 'url1',
                        width : 70,
                        isReadOnly : true
                    }, {
                        header : 'URL2',
                        binding : 'url2',
                        width : 70,
                        isReadOnly : true
                    }, {
                        header : 'URL3',
                        binding : 'url3',
                        width : 70,
                        isReadOnly : true
                    }, {
                        header : '画像数',
                        binding : 'img',
                        width : 120,
                        isReadOnly : true
                    }, {
                        header : '作成日時',
                        binding : 'dateCom',
                        dataType : wijmo.DataType.Date,
                        width : 100,
                        isReadOnly : true
                    }, {
                        header : '更新日時',
                        binding : 'dateRe',
                        dataType : wijmo.DataType.Date,
                        width : 100,
                        isReadOnly : true
                    }, {
                        header : '適用日時',
                        binding : 'dateSlice',
                        dataType : wijmo.DataType.Date,
                        width : 100,
                        isReadOnly : true
                    }, {
                        header : 'チェック区分',
                        binding : 'checkSec',
                        width : 100,
                        isReadOnly : true
                    }, {
                        header : 'BL登録区分',
                        binding : 'blSec',
                        width : 100,
                        isReadOnly : true
                    } ],
                    itemFormatter : function(panel, r, c, cell) {
                        if (wijmo.grid.CellType.Cell == panel.cellType
                                && (panel.columns[c].binding == 'money'
                                        || panel.columns[c].binding == 'width1'
                                        || panel.columns[c].binding == 'width2'
                                        || panel.columns[c].binding == 'width3'
                                        || panel.columns[c].binding == 'packwidth1'
                                        || panel.columns[c].binding == 'packwidth2'
                                        || panel.columns[c].binding == 'packwidth3' || panel.columns[c].binding == 'weight')) {
                            var cellData = panel.getCellData(r, c);
                            cell.style.textAlign = 'right';
                        }
                        if (wijmo.grid.CellType.Cell == panel.cellType
                                && (panel.columns[c].binding == 'applyStep'
                                        || panel.columns[c].binding == 'manageSec'
                                        || panel.columns[c].binding == 'BLSec'
                                        || panel.columns[c].binding == 'no' || panel.columns[c].binding == 'errSec')) {
                            var cellData = panel.getCellData(r, c);
                            cell.style.textAlign = 'center';
                        }

                        grid.formatItem
                                .addHandler(function(s, e) {
                                    if (e.panel.cellType == wijmo.grid.CellType.Cell) {
                                        // 必須項目チェック
                                        if (getColumnBinding(e.col, grid) == "selCode"
                                                || getColumnBinding(e.col, grid) == "secCodeName"
                                                || getColumnBinding(e.col, grid) == "blCodeName"
                                                || getColumnBinding(e.col, grid) == "goodsNo"
                                                || getColumnBinding(e.col, grid) == "nameS"
                                                || getColumnBinding(e.col, grid) == "nameB"
                                                || getColumnBinding(e.col, grid) == "money"
                                                || getColumnBinding(e.col, grid) == "open"
                                                || getColumnBinding(e.col, grid) == "dateSlice") {
                                            var value = grid.getCellData(e.row,
                                                    e.col, false);
                                            if (!value) {
                                                e.cell.classList
                                                        .add('null-cell');
                                            }
                                        }
                                    }
                                });
                    }
                });
    };

    function showGoodViewgrid() {
    	var goodgrid = parent.document.getElementById("grid")["wj-Control"];
    	var goodsdata = new Array()
    	var j =0;
    	for (var i = 0; i < goodgrid.rows.length; i++) {
			var col = goodgrid.rows[i].dataItem;
    		if (col.errSec == "有") {
    			col.no = j +1;
    			goodsdata[j] = col;
    			j++;
    		}
    	}
    	grid.itemsSource = new wijmo.collections.CollectionView(
    			goodsdata);
        grid.select(-1, -1);
    }
    
    function showGoodsGrid() {
        var params = {};
        params["count"] = goodsPage;
        params["mode"] = sessionStorage.getItem("checkList");
        $.blAjax({
            url : baseurl + '/checkList/goodsGrid',
            data : params,
            type : 'get',
            dataType : 'json',
            contentType : 'application/Json',
            success : function(data) {
                message = data.MESSAGE;
                goodslist = data.GOODSMASTER;
                var maxRows = parseInt(data.MAXROWS);
                goodsCounts = parseInt(data.SEARCHCOUNTS);
                goodsRows = paging(maxRows, goodsCounts);
                if (goodslist != null && goodslist.length != 0) {
                    grid.itemsSource = new wijmo.collections.CollectionView(
                            goodslist);
                    grid.select(-1, -1);
                } else {
                    var paramArr = new Array();
                    paramArr[0] = "商品";
                    layer.alert(getMessageInfo(message.E00008, paramArr), {
                        title : '',
                        closeBtn : 0,
                        btn : [ 'はい' ]
                    });
                }
            },
            error : function(data, httpStatus, errorHandler) {
                errorHandler(data, httpStatus);
            }
        });
    }

    function setInit(nowPage) {
        // セット
        var sequence = 1;
        setGrid = new wijmo.grid.FlexGrid('#set');
        setGrid.initialize({
            allowSorting : false,
            autoGenerateColumns : false,
            columns : [ {
                header : 'No.',
                binding : 'no',
                width : 50,
                isReadOnly : true
            }, {
                header : 'エラー区分',
                binding : 'errorFlg',
                isReadOnly : true
            }, {
                header : 'エラー内容',
                binding : 'errorDetail',
                isReadOnly : true
            }, {
                header : 'セレクトコード名称',
                binding : 'prmSetDtlNo1',
                width : 140,
                isReadOnly : true
            }, {
                header : '分類コード名称',
                binding : 'goodsMGroup',
                width : 140,
                isReadOnly : true
            }, {
                header : 'BLコード名称',
                binding : 'tbsPartsCode',
                width : 220,
                isReadOnly : true
            }, {
                header : 'セット親品番',
                binding : 'setMainPartsNo',
                width : 140,
                isReadOnly : true
            }, {
                header : '表示順位',
                binding : 'setDispOrder',
                width : 80,
                isReadOnly : true,
                align:'right'
            }, {
                header : 'セット子品番',
                binding : 'setSubPartsNo',
                width : 140,
                isReadOnly : true
            }, {
                header : '品名(半角)',
                binding : 'setKanaName',
                width : 160,
                isReadOnly : true
            }, {
                header : '品名（全角）',
                binding : 'setName',
                width : 260,
                minWidth : 30,
                isReadOnly : true
            }, {
                header : 'QTY',
                binding : 'setQty',
                width : 60,
                isReadOnly : true,
                align:'right'
            }, {
                header : '規格/特記',
                binding : 'setSpecialNote',
                width : 260,
                isReadOnly : true
            }, {
                header : '削除依頼区分',
                binding : 'deleteFlg',
                width : 100,
                isReadOnly : true
            }, {
                header : '削除理由',
                binding : 'deleteReason',
                width : 260,
                isReadOnly : true
            }, {
                header : '作成日時',
                binding : 'insDtTime',
                dataType : wijmo.DataType.Date,
                width : 100,
                isReadOnly : true
            }, {
                header : '更新日時',
                binding : 'updDtTime',
                dataType : wijmo.DataType.Date,
                width : 100,
                isReadOnly : true
            }, {
                header : '適用日時',
                binding : 'startTime',
                dataType : wijmo.DataType.Date,
                width : 100,
                isReadOnly : true
            } ]
        });
        setGrid.formatItem.addHandler(function(s, e) {
            if (e.panel.cellType == wijmo.grid.CellType.Cell) {
                // 必須項目チェック
                if (getSetColumnBinding(e.col, setGrid) == "prmSetDtlNo1"
                        || getSetColumnBinding(e.col, setGrid) == "setMainPartsNo"
                        || getSetColumnBinding(e.col, setGrid) == "setDispOrder"
                        || getSetColumnBinding(e.col, setGrid) == "setSubPartsNo"
                        || getSetColumnBinding(e.col, setGrid) == "setKanaName"
                        || getSetColumnBinding(e.col, setGrid) == "setQty"

                ) {
                    var value = setGrid.getCellData(e.row, e.col, false);
                    if (!value) {
                        e.cell.classList.add('null-cell');
                    }
                }
            }
        });
    };

    function showSetViewgrid() {
    	var setgrid = parent.document.getElementById("grid")["wj-Control"];
    	var setdata = new Array()
    	var j =0;
    	for (var i = 0; i < setgrid.rows.length; i++) {
			var col = setgrid.rows[i].dataItem;
    		if (col.errorFlg == "有") {
    			col.no = j +1;
    			setdata[j] = col;
    			j++;
    		}
    	}
    	setGrid.itemsSource = new wijmo.collections.CollectionView(
    			setdata);
    	setGrid.select(-1, -1);
    }
    
    function showSetGrid() {
        var params = {};
        params["count"] = setPage;
        params["mode"] = sessionStorage.getItem("checkList");
        $.blAjax({
            url : baseurl + '/checkList/setGrid',
            data : params,
            type : 'get',
            dataType : 'json',
            contentType : 'application/Json',
            success : function(data) {
                message = data.MESSAGE;
                var maxRows = parseInt(data.MAXROWS);
                setCounts = parseInt(data.SEARCHCOUNTS);
                setRows = paging(maxRows, setCounts);
                setlist = data.SETMASTER;
                if (setlist != null && setlist.length != 0) {
                    setGrid.itemsSource = new wijmo.collections.CollectionView(
                            setlist);
                    setGrid.select(-1, -1);
                } else {
                    var paramArr = new Array();
                    paramArr[0] = "セット";
                    layer.alert(getMessageInfo(message.E00008, paramArr), {
                        title : '',
                        closeBtn : 0,
                        btn : [ 'はい' ]
                    });
                }
            },
            error : function(data, httpStatus, errorHandler) {
                errorHandler(data, httpStatus);
            }
        });
    }

    function unionInit(nowPage) {
        // 結合
        var squen = 1;
        joinGrid = new wijmo.grid.FlexGrid('#union');
        joinGrid.initialize({
            allowSorting : false,
            autoGenerateColumns : false,
            columns : [ {
                header : 'No.',
                binding : 'no',
                width : 50,
                isReadOnly : true
            }, {
                header : 'エラー区分',
                binding : 'errorFlg',
                isReadOnly : true
            }, {
                header : 'エラー内容',
                binding : 'errorDetail',
                isReadOnly : true
            }, {
                header : 'セレクトコード名称',
                binding : 'prmSetDtlNo1',
                width : 100,
                isReadOnly : true
            }, {
                header : '分類コード名称',
                binding : 'goodsMGroup',
                width : 130,
                isReadOnly : true
            }, {
                header : 'BLコード名称',
                binding : 'tbsPartsCode',
                width : 140,
                isReadOnly : true
            }, {
                header : 'カーコード名称',
                binding : 'joinSourceMakerCode',
                width : 140,
                isReadOnly : true
            }, {
                header : '純正品番',
                binding : 'joinSourPartsNoWithH',
                width : 220,
                isReadOnly : true
            }, {
                header : '種別コード名称',
                binding : 'prmSetDtlNo2',
                width : 140,
                isReadOnly : true
            }, {
                header : '表示順位',
                binding : 'joinDispOrder',
                width : 80,
                isReadOnly : true,
                align:'right'
            }, {
                header : '優良品番',
                binding : 'joinDestPartsNo',
                width : 140,
                isReadOnly : true
            }, {
                header : 'QTY',
                binding : 'joinQty',
                width : 160,
                isReadOnly : true,
                align:'right'
            }, {
                header : '規格/特記',
                binding : 'joinSpecialNote',
                width : 260,
                minWidth : 30,
                isReadOnly : true
            }, {
                header : '規格/特記(一般)',
                binding : 'primePartsSpecialNoteC',
                width : 60,
                isReadOnly : true
            }, {
                header : '削除依頼区分.',
                binding : 'deleteFlg',
                width : 260,
                isReadOnly : true
            }, {
                header : '削除理由',
                binding : 'deleteReason',
                width : 260,
                isReadOnly : true
            }, {
                header : '作成日時',
                binding : 'insDtTime',
                dataType : wijmo.DataType.Date,
                width : 100,
                isReadOnly : true
            }, {
                header : '更新日時',
                binding : 'updDtTime',
                dataType : wijmo.DataType.Date,
                width : 100,
                isReadOnly : true
            }, {
                header : '適用日時',
                binding : 'startTime',
                dataType : wijmo.DataType.Date,
                width : 100,
                isReadOnly : true
            }, {
                header : 'チェック区分',
                binding : 'checkFlg',
                width : 100,
                isReadOnly : true
            }, {
                header : 'BL登録区分',
                binding : 'blEntryFlg',
                width : 100,
                isReadOnly : true
            } ]
        });

        joinGrid.formatItem
                .addHandler(function(s, e) {
                    if (e.panel.cellType == wijmo.grid.CellType.Cell) {
                        // 必須項目チェック
                        if (getJoinColumnBinding(e.col, joinGrid) == "prmSetDtlNo1"
                                || getJoinColumnBinding(e.col, joinGrid) == "prmSetDtlNo1"
                                || getJoinColumnBinding(e.col, joinGrid) == "tbsPartsCode"
                                || getJoinColumnBinding(e.col, joinGrid) == "joinSourceMakerCode"
                                || getJoinColumnBinding(e.col, joinGrid) == "joinSourPartsNoWithH"
                                || getJoinColumnBinding(e.col, joinGrid) == "prmSetDtlNo2"
                                || getJoinColumnBinding(e.col, joinGrid) == "joinDispOrder"
                                || getJoinColumnBinding(e.col, joinGrid) == "startTime") {
                            var value = joinGrid.getCellData(e.row, e.col,
                                    false);
                            if (!value) {
                                e.cell.classList.add('null-cell');
                            }
                        }
                    }
                });
    }

    function showJoinViewGrid() {
    	var joingrid = parent.document.getElementById("grid")["wj-Control"];
    	var joindata = new Array()
    	var j =0;
    	for (var i = 0; i < joingrid.rows.length; i++) {
			var col = joingrid.rows[i].dataItem;
    		if (col.errorFlg == "有") {
    			col.no = j +1;
    			joindata[j] = col;
    			j++;
    		}
    	}
    	joinGrid.itemsSource = new wijmo.collections.CollectionView(
    			joindata);
    	joinGrid.select(-1, -1);
    }
    
    function showJoinGrid() {
        var params = {};
        params["count"] = joinPage;
        params["mode"] = sessionStorage.getItem("checkList");
        $
                .blAjax({
                    url : baseurl + '/checkList/joinGrid',
                    data : params,
                    type : 'get',
                    dataType : 'json',
                    contentType : 'application/Json',
                    success : function(data) {
                        message = data.MESSAGE;
                        var maxRows = parseInt(data.MAXROWS);
                        joinCounts = parseInt(data.SEARCHCOUNTS);
                        joinRows = paging(maxRows, joinCounts);
                        joinlist = data.JOINMASTER;
                        if (joinlist != null && joinlist.length != 0) {
                            joinGrid.itemsSource = new wijmo.collections.CollectionView(
                                    joinlist);
                            joinGrid.select(-1, -1);
                        } else {
                            var paramArr = new Array();
                            paramArr[0] = "結合";
                            layer.alert(getMessageInfo(message.E00008, paramArr), {
                                title : '',
                                closeBtn : 0,
                                btn : [ 'はい' ]
                            });
                        }
                    },
                    error : function(data, httpStatus, errorHandler) {
                        errorHandler(data, httpStatus);
                    }
                });
    }

    var getColumnBinding = function(col, flexgrid) {
        return flexgrid.cells.columns[col].binding;
    };

    function paging(maxRows, counts) {
        var searchcount = counts;
        var historyrows = maxRows;
        var current_page = 1;

        if (showItem == 1) {
            current_page = goodsPage;
        }
        if (showItem == 2) {
            current_page = setPage;
        }
        if (showItem == 3) {
            current_page = joinPage;
        }
        var pageCount = searchcount % historyrows == 0 ? Math.floor(searchcount
                / historyrows) : Math.floor(searchcount / historyrows) + 1;
        if (pageCount != 1) {
        	if(pageCount==3){
            $('.page-box').pagination({
                pageCount : pageCount,
                coping : false,
                count:1,
                    keepShowPN:false,
                prevContent : '<前ページ',
                nextContent : '後ページ>',
                callback : pageCallback,
                current : current_page,
            });}else{
            	$('.page-box').pagination({
                    pageCount : pageCount,
                    coping : true,
                    count:1,
                        keepShowPN:false,
                    prevContent : '<前ページ',
                    nextContent : '後ページ>',
                    callback : pageCallback,
                    current : current_page,
                });
            }
        }
        $('.historyrows')[0].textContent = historyrows;
    };

    function pageCallback(page_index, jq) {
        if (showItem == 1) {
            goodsPage = Number.parseInt(page_index.getCurrent());
            showGoodsGrid();
        }
        if (showItem == 2) {
            setPage = Number.parseInt(page_index.getCurrent());
            showSetGrid();
        }
        if (showItem == 3) {
            joinPage = Number.parseInt(page_index.getCurrent());
            showJoinGrid();
        }
    };

});
