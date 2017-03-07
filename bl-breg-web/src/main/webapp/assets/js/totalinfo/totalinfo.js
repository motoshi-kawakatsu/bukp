$(function () {
	'use strict';
	
    var grid = new wijmo.grid.FlexGrid('#item');
    var setGrid = new wijmo.grid.FlexGrid('#set');
    var unionGrid = new wijmo.grid.FlexGrid('#union');
    
    var currentPageDef = 1;
    var objKbn = BLENUM.ObjectKbnEnum.GoodsKbn;
    var filterMode = filterMode_1;
    // 全検索
    var filterMode_0 = 0;
    // BLコードの場合
    var filterMode_1 = 1;
    // カーコードの場合
    var filterMode_2 = 2;
    // 優良品番の場合
    var filterMode_3 = 3;
    // 純正品番の場合
    var filterMode_4 = 4;
	var tbsPartsCode;
	var primePartsNo;
	var partsMakerCd;
	var joinSourPartsNoWithH;
	var searchcount;
	var maxcount;
	var currentPage;
    
    var Totalinfo = function () {
    };
    Totalinfo.prototype = {
        init: function () {
        	this.setInit();
            this.unionInit();
            this.itemInit();
            
            tbsPartsCode = sessionStorage.getItem("tbsPartsCode");
            primePartsNo = sessionStorage.getItem("primePartsNo");
    		partsMakerCd = sessionStorage.getItem("partsMakerCd");
    		joinSourPartsNoWithH = sessionStorage.getItem("joinSourPartsNoWithH");
    		
    		currentPage = currentPageDef;
    		showGoodsGrid();
            
            $('#myTab li').first().addClass('active');
            $('#item').addClass('active');
            $('#myTab li').first().click(function () {
            	objKbn = BLENUM.ObjectKbnEnum.GoodsKbn;
            	filterMode = filterMode_0;
            	currentPage = currentPageDef;
            	showGoodsGrid();
                grid.invalidate();
            });
            $('#myTab li:eq(1)').click(function () {
            	objKbn = BLENUM.ObjectKbnEnum.SetKbn;
            	filterMode = filterMode_0;
            	currentPage = currentPageDef;
            	showSetGrid();
                setGrid.invalidate();
            });
            $('#myTab li').last().click(function () {
            	objKbn = BLENUM.ObjectKbnEnum.JoinKbn;
            	filterMode = filterMode_0;
            	currentPage = currentPageDef;
            	showJoinGrid();
                unionGrid.invalidate();
            });

            if(tbsPartsCode == ""){
            	$('.blcode').attr("disabled", true);
            }
            if(primePartsNo == ""){
            	$('.goodsNo').attr("disabled", true);
            }
            if(partsMakerCd == ""){
        	   $('.carcode').attr("disabled", true);
           }
            if(joinSourPartsNoWithH == ""){
               $('.pureNo').attr("disabled", true);
            }
           
            $('.blcode').on('click','',{ 
            	subUrl : '/totalinfo/totalinfo',
                actionType : 'POST',
                },$.proxy(this.showblcode,this));
            $('.carcode').on('click','',{
            	subUrl : '/totalinfo/totalinfo',
            	actionType : 'POST',
            },$.proxy(this.showcarcode,this));
            $('.goodsNo').on('click','',{
            	subUrl : '/totalinfo/totalinfo',
            	actionType : 'POST',
            },$.proxy(this.showgoodsNo,this));
            $('.pureNo').on('click','',{
            	subUrl : '/totalinfo/totalinfo',
            	actionType : 'POST',
            },$.proxy(this.showpureNo,this));
        },
        showblcode:function(event){
        	filterMode = filterMode_1;
        	currentPage = currentPageDef;
        	refreshGrid();
        },
        showcarcode:function(event){
        	filterMode = filterMode_2;
        	currentPage = currentPageDef;
        	refreshGrid();
        },
        showgoodsNo:function(event){
        	filterMode = filterMode_3;
        	currentPage = currentPageDef;
        	refreshGrid();
        },
        showpureNo:function(event){
        	filterMode = filterMode_4;
        	currentPage = currentPageDef;
        	refreshGrid();
        },
        
        itemInit: function () {
            //商品
            grid.initialize({
                allowSorting: false,
                autoGenerateColumns: false,
                columns: [
                    {header: 'No.',  binding: 'recid', width: 50, isReadOnly: true},
                    {header: '申請状態', binding: 'blapplyresultsflg', isReadOnly: true},
                    {header: '処理区分', binding: 'dealflg', width: 120, isReadOnly: true},
                    {header: 'セレクトコード', binding: 'prmsetdtlno1', width: 140, isReadOnly: true},
                    {header: '分類コード', binding: 'goodsmgroup', width: 140, isReadOnly: true},
                    {header: 'BLコード', binding: 'tbspartscode', width: 140, isReadOnly: true},
                    {header: '優良品番', binding: 'primepartsnowithh', width: 120, isReadOnly: true},
                    {header: '品名（半角）', binding: 'primepartskananm', width: 120, isReadOnly: true},
                    {header: '品名（全角）', binding: 'primepartsname', width: 120, minWidth: 30, isReadOnly: true},
                    {header: '価格（税抜）', binding: 'newprice', dataType: wijmo.DataType.Number, isReadOnly: true},
                    {header: 'OPENプランス', binding: 'openpricediv',width: 100, isReadOnly: true},
                    {header: 'JAN', binding: 'jan',width: 60, isReadOnly: true},
                    {header: '層別', binding: 'partslayercd',width: 60, isReadOnly: true},
                    {header: '装備', binding: 'equipname',width: 60, isReadOnly: true},
                    {header: '規格/特記.',  binding: 'primepartsspecialnote', width: 130, isReadOnly: true},
                    {header: '規格/特記（一般）', binding: 'primepartsspecialnotec', width: 150, isReadOnly: true},
                    {header: '削除依頼区分', binding: 'deleteflg', width: 120, isReadOnly: true},
                    {header: '削除理由', binding: 'deletereason',  width: 140, isReadOnly: true},
                    {header: '商品詳細', binding: 'goodsdetailb', width: 120, isReadOnly: true},
                    {header: '商品詳細（一般）', binding: 'goodsdetailc', width: 100, isReadOnly: true},
                    {header: '長さ', binding: 'goodssize1', width: 120, isReadOnly: true},
                    {header: '幅', binding: 'goodssize2', width: 120, isReadOnly: true},
                    {header: '高さ', binding: 'goodssize3', width: 120, isReadOnly: true},
                    {header: '梱包（長さ）', binding: 'packagesize1', width: 120, isReadOnly: true},
                    {header: '梱包（幅）', binding: 'packagesize2', width: 120, isReadOnly: true},
                    {header: '梱包（高さ）', binding: 'packagesize3', width: 120, isReadOnly: true},
                    {header: '梱包単位', binding: 'sizeunit',width: 120, isReadOnly: true},
                    {header: '重量', binding: 'goodsweight',width: 120, isReadOnly: true},
                    {header: '重量単位', binding: 'weightunit',width: 60, isReadOnly: true},
                    {header: 'URL1', binding: 'url1',width: 170, isReadOnly: true},
                    {header: 'URL2', binding: 'url2',width: 170, isReadOnly: true},
                    {header: 'URL3',  binding: 'url3', width: 170, isReadOnly: true},
                    {header: '画像数', binding: 'imageno', width: 120, isReadOnly: true},
                    {header: '作成日時', binding: 'insdttime', dataType: wijmo.DataType.Date, width: 180, isReadOnly: true},
                    {header: '更新日時', binding: 'upddttime', dataType: wijmo.DataType.Date, width: 180, isReadOnly: true},
                    {header: '適用日時', binding: 'starttime',dataType: wijmo.DataType.Date, width: 180, isReadOnly: true},
                    {header: 'BL登録区分', binding: 'blentryflg', width: 100, isReadOnly: true}
                ],
                itemFormatter: function (panel, r, c, cell) {
                    if (wijmo.grid.CellType.Cell == panel.cellType &&
                        (panel.columns[c].binding == 'prmsetdtlno1'||panel.columns[c].binding == 'goodsmgroup'
                        ||panel.columns[c].binding == 'tbspartscode'||panel.columns[c].binding == 'primepartsnowithh'	
                        ||panel.columns[c].binding == 'primepartskananm'||panel.columns[c].binding == 'primepartsname'
                        ||panel.columns[c].binding == 'newprice'||panel.columns[c].binding == 'openpricediv'
                        ||panel.columns[c].binding == 'jan'||panel.columns[c].binding == 'partslayercd'
                        ||panel.columns[c].binding == 'equipname'||panel.columns[c].binding == 'primepartsspecialnote'
                        ||panel.columns[c].binding == 'primepartsspecialnotec'||panel.columns[c].binding == 'deleteflg'
                        ||panel.columns[c].binding == 'deletereason'||panel.columns[c].binding == 'goodsdetailb'
                        ||panel.columns[c].binding == 'goodsdetailc'||panel.columns[c].binding == 'goodssize1'
                        ||panel.columns[c].binding == 'goodssize2'||panel.columns[c].binding == 'goodssize3'
                        ||panel.columns[c].binding == 'packagesize1'||panel.columns[c].binding == 'packagesize2'
                        ||panel.columns[c].binding == 'packagesize3'||panel.columns[c].binding == 'sizeunit'
                        ||panel.columns[c].binding == 'goodsweight'||panel.columns[c].binding == 'url1'	
                        ||panel.columns[c].binding == 'url2'||panel.columns[c].binding == 'url3'	
                        ||panel.columns[c].binding == 'imageno'||panel.columns[c].binding == 'insdttime'
                        ||panel.columns[c].binding == 'upddttime'||panel.columns[c].binding == 'starttime'
                        ||panel.columns[c].binding == 'blentryflg'||panel.columns[c].binding == 'weightunit'
                        )) {
                        var cellData = panel.getCellData(r, c);
                        cell.style.textAlign = 'left';
                    }
                    if (wijmo.grid.CellType.Cell == panel.cellType &&
                        (panel.columns[c].binding == 'recid'
                        ||panel.columns[c].binding == 'blapplyresultsflg'
                        ||panel.columns[c].binding == 'dealflg')) {
                        var cellData = panel.getCellData(r, c);
                        cell.style.textAlign = 'center';
                    }
                    if (wijmo.grid.CellType.Cell == panel.cellType &&
		                    (panel.columns[c].binding == 'setdisporder'
		                    ||panel.columns[c].binding == 'setqty')) {
		                    var cellData = panel.getCellData(r, c);
		                    cell.style.textAlign = 'right';
		                }
                }
            });
            grid.headersVisibility = wijmo.grid.HeadersVisibility.Column;
        },
        setInit: function () {
            //セット
            setGrid.initialize({
                allowSorting: false,
                autoGenerateColumns: false,
                columns: [
                    {header: 'No.',  binding: 'recid', width: 50, isReadOnly: true},
                    {header: '申請状態', binding: 'blapplyresultsflg', isReadOnly: true},
                    {header: '処理区分', binding: 'dealflg', width: 120, isReadOnly: true},
                    {header: 'セレクトコード', binding: 'prmsetdtlno1', width: 140, isReadOnly: true},
                    {header: '分類コード', binding: 'goodsmgroup', width: 140, isReadOnly: true},
                    {header: 'BLコード', binding: 'tbspartscode', width: 140, isReadOnly: true},
                    {header: 'セット親品番', binding: 'setmainpartsno', width: 140, isReadOnly: true},
                    {header: '表示順位', binding: 'setdisporder', width: 80, isReadOnly: true,align:'right'},
                    {header: 'セット子品番', binding: 'setsubpartsno', width: 140, isReadOnly: true},
                    {header: '品名(半角)', binding: 'setkananame', width: 160, isReadOnly: true},
                    {header: '品名（全角）', binding: 'setname', width: 160, minWidth: 30, isReadOnly: true},
                    {header: 'QTY', binding: 'setqty',width: 60, isReadOnly: true,align:'right'},
                    {header: '規格/特記',  binding: 'setapecialnote', width: 180, isReadOnly: true},
                    {header: '削除依頼区分', binding: 'deleteflg', width: 100, isReadOnly: true},
                    {header: '削除理由', binding: 'deletereason',  width: 260, isReadOnly: true},   
                    {header: '作成日時', binding: 'insdttime', dataType: wijmo.DataType.Date, width: 180, isReadOnly: true},
                    {header: '更新日時', binding: 'upddttime', dataType: wijmo.DataType.Date, width: 180, isReadOnly: true},
                    {header: '適用日時', binding: 'starttime',dataType: wijmo.DataType.Date, width: 180, isReadOnly: true}
                ],
	            itemFormatter: function (panel, r, c, cell) {
	            	if (wijmo.grid.CellType.Cell == panel.cellType &&
	                        (panel.columns[c].binding == 'prmsetdtlno1'||panel.columns[c].binding == 'goodsmgroup'
	                        ||panel.columns[c].binding == 'tbspartscode'||panel.columns[c].binding == 'setmainpartsno'	
	                        ||panel.columns[c].binding == 'setsubpartsno'
	                        ||panel.columns[c].binding == 'setkananame'||panel.columns[c].binding == 'setname'
	                        ||panel.columns[c].binding == 'setapecialnote'
	                        ||panel.columns[c].binding == 'deleteflg'||panel.columns[c].binding == 'deletereason'
	                        ||panel.columns[c].binding == 'insdttime'||panel.columns[c].binding == 'upddttime'
	                        ||panel.columns[c].binding == 'starttime'             
	                        )) {
	                        var cellData = panel.getCellData(r, c);
	                        cell.style.textAlign = 'left';
	                    }
	                if (wijmo.grid.CellType.Cell == panel.cellType &&
	                    (panel.columns[c].binding == 'recid'
	                    ||panel.columns[c].binding == 'blapplyresultsflg'
	                    ||panel.columns[c].binding == 'dealflg')) {
	                    var cellData = panel.getCellData(r, c);
	                    cell.style.textAlign = 'center';
	                }
	                if (wijmo.grid.CellType.Cell == panel.cellType &&
		                    (panel.columns[c].binding == 'setdisporder'
		                    ||panel.columns[c].binding == 'setqty')) {
		                    var cellData = panel.getCellData(r, c);
		                    cell.style.textAlign = 'right';
		                }
	            }
            });
            setGrid.headersVisibility = wijmo.grid.HeadersVisibility.Column;
        },
        unionInit: function () {
            //結合
            unionGrid.initialize({
                allowSorting: false,
                autoGenerateColumns: false,
                columns: [
                    {header: 'No.',  binding: 'recid', width: 50, isReadOnly: true},
                    {header: '申請状態', binding: 'blapplyresultsflg', isReadOnly: true}, 
                    {header: '処理区分', binding: 'dealflg', width: 120, isReadOnly: true},
                    {header: 'セレクトコード', binding: 'prmsetdtlno1', width: 140, isReadOnly: true},
                    {header: '分類コード', binding: 'goodsmgroup', width: 140, isReadOnly: true},
                    {header: 'BLコード', binding: 'tbspartscode', width: 140, isReadOnly: true},
                    {header: 'カーコード', binding: 'partsmakercd', width: 140, isReadOnly: true}, 
                    {header: '純正品番', binding: 'joinsourpartsnowithh', width: 220, isReadOnly: true},
                    {header: '種別コード', binding: 'prmsetdtlno2', width: 140, isReadOnly: true},
                    {header: '表示順位', binding: 'joindisporder', width: 80, isReadOnly: true,align:'right'},
                    {header: '優良品番', binding: 'joindestpartsno', width: 140, isReadOnly: true},
                    {header: 'QTY', binding: 'joinqty', width: 140, isReadOnly: true,align:'right'},
                    {header: '規格/特記', binding: 'joinspecialnote', width: 140, minWidth: 30, isReadOnly: true},
                    {header: '規格/特記(一般)', binding: 'primepartsspecialnotec',width: 200, isReadOnly: true},
                    {header: '削除依頼区分',  binding: 'deleteflg', width: 160, isReadOnly: true},
                    {header: '削除理由', binding: 'deletereason',  width: 140, isReadOnly: true},        
                    {header: '作成日時', binding: 'insdttime', dataType: wijmo.DataType.Date, width: 180, isReadOnly: true},
                    {header: '更新日時', binding: 'upddttime', dataType: wijmo.DataType.Date, width: 180, isReadOnly: true},
                    {header: '適用日時', binding: 'starttime',dataType: wijmo.DataType.Date, width: 180, isReadOnly: true},
                    {header: 'BL登録区分', binding: 'blentryflg',  width: 100, isReadOnly: true}
                ],
                itemFormatter: function (panel, r, c, cell) {
                	if (wijmo.grid.CellType.Cell == panel.cellType &&
	                        (panel.columns[c].binding == 'prmsetdtlno1'||panel.columns[c].binding == 'goodsmgroup'
	                        ||panel.columns[c].binding == 'tbspartscode'||panel.columns[c].binding == 'partsmakercd'	
	                        ||panel.columns[c].binding == 'joinsourpartsnowithh'||panel.columns[c].binding == 'prmsetdtlno2'
	                        ||panel.columns[c].binding == 'joindestpartsno'
	                        ||panel.columns[c].binding == 'joinspecialnote'||panel.columns[c].binding == 'primepartsspecialnotec'
	                        ||panel.columns[c].binding == 'deleteflg'||panel.columns[c].binding == 'deletereason'
	                        ||panel.columns[c].binding == 'insdttime'||panel.columns[c].binding == 'upddttime'
	                        ||panel.columns[c].binding == 'starttime'||panel.columns[c].binding == 'blentryflg'	
	                        )) {
	                        var cellData = panel.getCellData(r, c);
	                        cell.style.textAlign = 'left';
	                    }
	                if (wijmo.grid.CellType.Cell == panel.cellType &&
	                    (panel.columns[c].binding == 'recid'
	                    ||panel.columns[c].binding == 'blapplyresultsflg'
	                    ||panel.columns[c].binding == 'dealflg')) {
	                    var cellData = panel.getCellData(r, c);
	                    cell.style.textAlign = 'center';
	                }
	                if (wijmo.grid.CellType.Cell == panel.cellType &&
		                    (panel.columns[c].binding == 'setdisporder'
		                    ||panel.columns[c].binding == 'setqty')) {
		                    var cellData = panel.getCellData(r, c);
		                    cell.style.textAlign = 'right';
		                }
	            }
            });
            unionGrid.headersVisibility = wijmo.grid.HeadersVisibility.Column;
          }
    };

    // 画面初期化
    var page = new Totalinfo();
    page.init();
    
    
    function refreshPage() {
    	var pageSum = Math.ceil(searchcount/maxcount);
    	if (1 == currentPage && pageSum > 1) {
    		if(pageSum==3){
    		$('.page-box').pagination({
                pageCount: pageSum,
                coping: false,
                count:1,
                isHide:true,
                keepShowPN:false,
                prevContent:'<前ページ',
                nextContent:'後ページ>',
                callback: pageCallback,
            });}else{
            	$('.page-box').pagination({
                    pageCount: pageSum,
                    coping: true,
                    count:1,
                    isHide:true,
                    keepShowPN:false,
                    prevContent:'<前ページ',
                    nextContent:'後ページ>',
                    callback: pageCallback,
                });
            }
    	}else if(pageSum == 1){
    		$('.page-box').hide();
    	}
    };
    
    function pageCallback(page_index,jq) {
    	currentPage = page_index.getCurrent();
    	refreshGrid(currentPage);
	};
	
	function showGoodsGrid() {
		try {
			var accessURL = baseurl + '/totalinfo/totalinfo';
            var actionType = 'POST';
            var form = {
               filterMode:filterMode,
               currentPage:currentPage,
               objKbn:objKbn,
               tbsPartsCode:tbsPartsCode,
               primePartsNo:primePartsNo,
        	   partsMakerCd:partsMakerCd,
        	   joinSourPartsNoWithH:joinSourPartsNoWithH,
            };
            $.blAjax({
                // allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
                // modalStyle: 'none', //
                // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
                // isTranFlag: true,// true:他画面へ遷移、false:他画面へ遷移しない
                url : accessURL,
                type : actionType,
                data : JSON.stringify(form),
                contentType : 'application/Json',
                success : function(data) {
                	var item = JSON.parse(data.ITEMMASTER);
            		if(item==null){
                		grid.itemsSource = new wijmo.collections.CollectionView(null);
                		$('.searchcount').text(data.searchcount);
                    	$('.totalcount').text(data.totalcount);
                    	$('.maxcount').text(data.maxcount);
                    	$('.page-box').hide();
                    	
                	}else{
                    	for (var i = 0;i <item.goodstotalinfodtos.length; i++){
                    		item.goodstotalinfodtos[i].recid = i+1+(data.maxcount)*(currentPage-1);
                		}
                    	searchcount = data.searchcount;
                    	maxcount = data.maxcount;
                    	$('.searchcount').text(data.searchcount);
                    	$('.totalcount').text(data.totalcount);
                    	$('.maxcount').text(data.maxcount);
                    	$('.page-box').show();
                    	refreshPage();
                    	grid.itemsSource = new wijmo.collections.CollectionView(item.goodstotalinfodtos);
                    	grid.formatItem.addHandler(function(s, e) {
                    		for (var i = 0; i < item.goodstotalinfodtos.length; i++) {
            					var columnstatus = new Array();
            					columnstatus = item.goodstotalinfodtos[i].columnstatus.split(",");
            					for (var j = 0; j < columnstatus.length; j++) {
            						if (e.panel.cellType == wijmo.grid.CellType.Cell && e.row == i && e.col == j) {
            							$(e.cell).removeClass('history');
            						}
            					}
            				}
                    		
            				for (var i = 0; i < item.goodstotalinfodtos.length; i++) {
            					var columnstatus = new Array();
            					columnstatus = item.goodstotalinfodtos[i].columnstatus.split(",");
            					for (var j = 0; j < columnstatus.length; j++) {
            						if (columnstatus[j] == "1" && e.panel.cellType == wijmo.grid.CellType.Cell && e.row == i && e.col == j) {
            							$(e.cell).addClass('history');
            						}
            					}
            				}
            			});
                	}
                },
                error : function(data, httpStatus, errorHandler) {
                    errorHandler(data, httpStatus);
                }
            });
        } catch (e) {
            sendClientErrorLog(e);
        }
	};
	
	function showSetGrid() {
		try {
			var accessURL = baseurl + '/totalinfo/totalinfo';
            var actionType = 'POST';
            var form = {
               filterMode:filterMode,
               currentPage:currentPage,
               objKbn:objKbn,
               tbsPartsCode:tbsPartsCode,
               primePartsNo:primePartsNo,
        	   partsMakerCd:partsMakerCd,
        	   joinSourPartsNoWithH:joinSourPartsNoWithH,
            };
            $.blAjax({
                // allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
                // modalStyle: 'none', //
                // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
                // isTranFlag: true,// true:他画面へ遷移、false:他画面へ遷移しない
                url : accessURL,
                type : actionType,
                data : JSON.stringify(form),
                contentType : 'application/Json',
                success : function(data) {
                	var set = JSON.parse(data.SETMASTER);
            		if(set==null){
                		setGrid.itemsSource = new wijmo.collections.CollectionView(null);
                		$('.searchcount').text(data.searchcount);
                    	$('.totalcount').text(data.totalcount);
                    	$('.maxcount').text(data.maxcount);
                    	$('.page-box').hide();
                	}else{
                    	for (var i = 0;i <set.settotalinfodtos.length; i++){
                    		set.settotalinfodtos[i].recid = i+1+(data.maxcount)*(currentPage-1);
                		}
                    	searchcount = data.searchcount;
                    	maxcount = data.maxcount;
                    	$('.searchcount').text(data.searchcount);
                    	$('.totalcount').text(data.totalcount);
                    	$('.maxcount').text(data.maxcount);
                    	$('.page-box').show();
                    	refreshPage();
                    	setGrid.itemsSource = new wijmo.collections.CollectionView(set.settotalinfodtos);
                    	setGrid.formatItem.addHandler(function(s, e) {
                    		for (var i = 0; i < set.settotalinfodtos.length; i++) {
            					var columnstatus = new Array();
            					columnstatus = set.settotalinfodtos[i].columnstatus.split(",");
            					for (var j = 0; j < columnstatus.length; j++) {
            						if (e.panel.cellType == wijmo.grid.CellType.Cell && e.row == i && e.col == j) {
            							$(e.cell).removeClass('history');
            						}
            					}
            				}
                    		
            				for (var i = 0; i < set.settotalinfodtos.length; i++) {
            					var columnstatus = new Array();
            					columnstatus = set.settotalinfodtos[i].columnstatus.split(",");
            					for (var j = 0; j < columnstatus.length; j++) {
            						if (columnstatus[j] == "1" && e.panel.cellType == wijmo.grid.CellType.Cell && e.row == i && e.col == j) {
            							$(e.cell).addClass('history');
            						}
            					}
            				}
            			});
                	}
                },
                error : function(data, httpStatus, errorHandler) {
                    errorHandler(data, httpStatus);
                }
            });
        } catch (e) {
            sendClientErrorLog(e);
        }
	};
	
	function showJoinGrid() {
		try {
            var accessURL = baseurl + '/totalinfo/totalinfo';
            var actionType = 'POST';
            var form = {
               filterMode:filterMode,
               currentPage:currentPage,
               objKbn:objKbn,
               tbsPartsCode:tbsPartsCode,
               primePartsNo:primePartsNo,
        	   partsMakerCd:partsMakerCd,
        	   joinSourPartsNoWithH:joinSourPartsNoWithH,
            };
            $.blAjax({
                // allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
                // modalStyle: 'none', //
                // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
                // isTranFlag: true,// true:他画面へ遷移、false:他画面へ遷移しない
                url : accessURL,
                type : actionType,
                data : JSON.stringify(form),
                contentType : 'application/Json',
                success : function(data) {
                	var join = JSON.parse(data.JOINMASTER);
            		if(join==null){
                		unionGrid.itemsSource = new wijmo.collections.CollectionView(null);
                		$('.searchcount').text(data.searchcount);
                    	$('.totalcount').text(data.totalcount);
                    	$('.maxcount').text(data.maxcount);
                    	$('.page-box').hide();
                	}else{
                    	for (var i = 0;i <join.jointotalinfodtos.length; i++){
                    		join.jointotalinfodtos[i].recid = i+1+(data.maxcount)*(currentPage-1);
                		}
                    	searchcount = data.searchcount;
                    	maxcount = data.maxcount;
                    	$('.searchcount').text(data.searchcount);
                    	$('.totalcount').text(data.totalcount);
                    	$('.maxcount').text(data.maxcount);
                    	$('.page-box').show();
                    	refreshPage();
                    	unionGrid.itemsSource = new wijmo.collections.CollectionView(join.jointotalinfodtos);
                    	unionGrid.formatItem.addHandler(function(s, e) {
                    		for (var i = 0; i < join.jointotalinfodtos.length; i++) {
            					var columnstatus = new Array();
            					columnstatus = join.jointotalinfodtos[i].columnstatus.split(",");
            					for (var j = 0; j < columnstatus.length; j++) {
            						if (e.panel.cellType == wijmo.grid.CellType.Cell && e.row == i && e.col == j) {
            							$(e.cell).removeClass('history');
            						}
            					}
            				}
                    		
            				for (var i = 0; i < join.jointotalinfodtos.length; i++) {
            					var columnstatus = new Array();
            					columnstatus = join.jointotalinfodtos[i].columnstatus.split(",");
            					for (var j = 0; j < columnstatus.length; j++) {
            						if (columnstatus[j] == "1" && e.panel.cellType == wijmo.grid.CellType.Cell && e.row == i && e.col == j) {
            							$(e.cell).addClass('history');
            						}
            					}
            				}
            			});
                	}
                },
                error : function(data, httpStatus, errorHandler) {
                    errorHandler(data, httpStatus);
                }
            });
        } catch (e) {
            sendClientErrorLog(e);
        }
	};
	
	function refreshGrid() {
        if (BLENUM.ObjectKbnEnum.GoodsKbn == objKbn) {
            showGoodsGrid();
        } else if (BLENUM.ObjectKbnEnum.SetKbn == objKbn) {
            showSetGrid();
        } else if (BLENUM.ObjectKbnEnum.JoinKbn == objKbn) {
            showJoinGrid();
        }
    };
	
});


