$(function () {

	'use strict';

    var grid=null;
    var setGrid=null;
    var joinGrid=null;
    var goodsCounts = 0;
    var setCounts = 0;
    var joinCounts = 0;
    var showFlag = 0;
    var ApplyDetail = function (mode) {
        this.mode = sessionStorage.getItem("applyDetail");
    };
    ApplyDetail.prototype = {
        init: function () {
            // 商品、セット、結合の切り換える
            this.mode = sessionStorage.getItem("applyDetail");
            switch (parseInt(this.mode)){

                // 商品は現れる
                case BLENUM.MenuEnum.Item:
                    this.goodsInit();
                    $('#myTab li:eq(1)').hide();
                    $('#myTab li').last().hide();
                    $('#item').addClass('active');
                    showItem = 1;
                    break;
                // セットは現れる
                case BLENUM.MenuEnum.Set:
                    this.setInit();
                    $('#myTab li:eq(1)').addClass('active')
                    $('#myTab li').first().hide();
                    $('#myTab li').last().hide();
                    $('#set').addClass('active');
                    showItem = 2;
                    break;
                // 結合は現れる
                case BLENUM.MenuEnum.Union:
                    this.joinInit();
                    $('#myTab li').last().addClass('active');
                    $('#myTab li').first().hide();
                    $('#myTab li:eq(1)').hide();
                    $('#union').addClass('active');
                    showItem = 3;
                    break;
                default:
                	var showItem = 1;
                    this.goodsInit();
                    this.setInit();
                    this.joinInit();
                    goodsData();
                    $('.again').hide();
                    $('.all-num').text(goodsCounts);
                    $('#myTab li').first().addClass('active');
                    $('#item').addClass('active');
                    $('#myTab li').first().click(function () {
                        goodsData();
                        grid.invalidate();
                        showItem = 1;
                    });
                    $('#myTab li:eq(1)').click(function () {
                        setData();
                        setGrid.invalidate();
                        showItem = 2;
                    });
                    $('#myTab li').last().click(function () {
                        joinData();
                        joinGrid.invalidate();
                        showItem = 3;
                    });
                    $('.return').on('click', $.proxy(this.onReturnClick, this));
                    $('.again').on('click', $.proxy(this.onApplyAgainClick, this));
                    break;
            }
        },
        
        /// <summary>
        /// popup guide
        /// </summary>

        // 商品
        goodsInit: function () {
            grid = new wijmo.grid.FlexGrid('#item');
            grid.initialize({
                allowSorting: false,
                autoGenerateColumns: false,
                columns: [
                    {header: 'No.',  binding: 'recid', width: 50, isReadOnly: true},
                    {header: '申請状態', binding: 'applycondition', isReadOnly: true},
                    {header: '処理区分', binding: 'managekbn', width: 120, isReadOnly: true},
                    {header: 'セレクトコード名称', binding: 'prmsetdtlno1', width: 180, isReadOnly: true},
                    {header: '分類コード名称', binding: 'goodsmgroup', width: 180, isReadOnly: true},
                    {header: 'BLコード名称', binding: 'tbspartscode', width: 180, isReadOnly: true},
                    {header: '優良品番', binding: 'primepartsnowithh', width: 120, isReadOnly: true},
                    {header: '品名（半角）', binding: 'primepartskananm', width: 120, isReadOnly: true},
                    {header: '品名（全角）', binding: 'primepartsname', width: 120, minWidth: 30, isReadOnly: true},
                    {header: '価格（税抜）', binding: 'newprice', dataType: wijmo.DataType.Number, isReadOnly: true},
                    {header: 'OPENプランス', binding: 'openpricediv',width: 100, isReadOnly: true},
                    {header: 'JAN', binding: 'jan',width: 60, isReadOnly: true},
                    {header: '層別', binding: 'partslayercd',width: 180, isReadOnly: true},
                    {header: '装備', binding: 'equipname',width: 80, isReadOnly: true},
                    {header: '規格/特記.',  binding: 'primepartsspecialnoteb', width: 130, isReadOnly: true},
                    {header: '規格/特記（一般）', binding: 'primepartsspecialnotec', width: 150, isReadOnly: true},
                    {header: '削除依頼区分', binding: 'deleteflg', width: 120, isReadOnly: true},
                    {header: '削除理由', binding: 'deletereason',  width: 140, isReadOnly: true},
                    {header: '商品詳細', binding: 'goodsdetailb', width: 120, isReadOnly: true},
                    {header: '商品詳細（一般）', binding: 'goodsdetailc', width: 120, isReadOnly: true},
                    {header: '長さ', binding: 'goodssize1', width: 120, isReadOnly: true},
                    {header: '幅', binding: 'goodssize2', width: 120, isReadOnly: true},
                    {header: '高さ', binding: 'goodssize3', width: 120, isReadOnly: true},
                    {header: '梱包（長さ）', binding: 'packagesize1', width: 120, isReadOnly: true},
                    {header: '梱包（幅）', binding: 'packagesize2', width: 120, isReadOnly: true},
                    {header: '梱包（高さ）', binding: 'packagesize3', width: 120, isReadOnly: true},
                    {header: '梱包単位', binding: 'sizeunit',width: 120, isReadOnly: true},
                    {header: '重量', binding: 'goodsweight',width: 120, isReadOnly: true},
                    {header: '重量単位', binding: 'weightunit',width: 60, isReadOnly: true},
                    {header: 'URL1', binding: 'url1',width: 180, isReadOnly: true},
                    {header: 'URL2', binding: 'url2',width: 180, isReadOnly: true},
                    {header: 'URL3',  binding: 'url3', width: 180, isReadOnly: true},
                    {header: '画像有無', binding: 'imageno', width: 120, isReadOnly: true},
                    {header: '作成日時',binding: 'insdttime',dataType: wijmo.DataType.Date, width: 140, isReadOnly: true},
                    {header: '更新日時',binding: 'upddttime',dataType: wijmo.DataType.Date, width: 140, isReadOnly: true},
                    {header: '適用日時', binding: 'applytime',dataType: wijmo.DataType.Date, width: 140, isReadOnly: true},
                    {header: 'BL登録区分', binding: 'blentryflg', width: 100, isReadOnly: true},
                    {header: 'エラー区分', binding: 'errorflg', width: 150, isReadOnly: true}
                ],
                itemFormatter: function (panel, r, c, cell) {
                    if (wijmo.grid.CellType.Cell == panel.cellType &&
                        (panel.columns[c].binding == 'money'
                        ||panel.columns[c].binding == 'width1'
                        ||panel.columns[c].binding == 'width2'
                        ||panel.columns[c].binding == 'width3'
                        ||panel.columns[c].binding == 'packwidth1'
                        ||panel.columns[c].binding == 'packwidth2'
                        ||panel.columns[c].binding == 'packwidth3'
                        ||panel.columns[c].binding == 'weight')) {
                        var cellData = panel.getCellData(r, c);
                        cell.style.textAlign = 'right';
                    }
                    if (wijmo.grid.CellType.Cell == panel.cellType &&
                        (panel.columns[c].binding == 'checkSec'
                        ||panel.columns[c].binding == 'applyStep'
                        ||panel.columns[c].binding == 'manageSec'
                        ||panel.columns[c].binding == 'BLSec'
                        ||panel.columns[c].binding == 'no'
                        ||panel.columns[c].binding == 'errSec')) {
                        var cellData = panel.getCellData(r, c);
                        cell.style.textAlign = 'center';
                    }
                }
            });


        },

        // セット
        setInit: function () {
            setGrid = new wijmo.grid.FlexGrid('#set');
            setGrid.initialize({
                allowSorting: false,
                autoGenerateColumns: false,
                columns: [
                    {header: 'No.',  binding: 'recid', width: 50, isReadOnly: true},
                    {header: '申請状態', binding: 'applycondition', isReadOnly: true},
                    {header: '処理区分', binding: 'managekbn', width: 120, isReadOnly: true},
                    {header: 'セレクトコード名称', binding: 'prmsetdtlno1',  width: 180, isReadOnly: true},
                    {header: '分類コード名称', binding: 'goodsmgroupcd', width: 180, isReadOnly: true},
                    {header: 'BLコード名称', binding: 'blcode', width: 180, isReadOnly: true},
                    {header: 'セット親品番', binding: 'setmainpartsno', width: 140, isReadOnly: true},
                    {header: '表示順位', binding: 'setdisporder', width: 80, isReadOnly: true},
                    {header: 'セット子品番', binding: 'setsubpartsno', width: 140, isReadOnly: true},
                    {header: '品名(半角)', binding: 'productname', width: 150, isReadOnly: true},
                    {header: '品名（全角）', binding: 'setname', width: 150, minWidth: 30, isReadOnly: true},
                    {header: 'QTY', binding: 'setqty',width: 60, isReadOnly: true},
                    {header: '規格/特記.',  binding: 'setspecialnote', width: 150, isReadOnly: true},
                    {header: '削除依頼区分', binding: 'deleteflg', width: 100, isReadOnly: true},
                    {header: '削除理由', binding: 'deletereason',  width: 260, isReadOnly: true},
                    {header: '作成日時', binding: 'insdttime',dataType: wijmo.DataType.Date, width: 140, isReadOnly: true},
                    {header: '更新日時', binding: 'upddttime', dataType: wijmo.DataType.Date, width: 140, isReadOnly: true},
                    {header: '適用日時', binding: 'applytime', dataType: wijmo.DataType.Date, width: 140, isReadOnly: true},
                    {header: 'エラー区分', binding: 'datastatus', width: 150, isReadOnly: true},
                ],
                itemFormatter: function (panel, r, c, cell) {
                    if (wijmo.grid.CellType.Cell == panel.cellType &&
                        (panel.columns[c].binding == 'money'
                        ||panel.columns[c].binding == 'width1'
                        ||panel.columns[c].binding == 'width2'
                        ||panel.columns[c].binding == 'width3'
                        ||panel.columns[c].binding == 'packwidth1'
                        ||panel.columns[c].binding == 'packwidth2'
                        ||panel.columns[c].binding == 'packwidth3'
                        ||panel.columns[c].binding == 'weight')) {
                        var cellData = panel.getCellData(r, c);
                        cell.style.textAlign = 'right';
                    }
                    if (wijmo.grid.CellType.Cell == panel.cellType &&
                        (panel.columns[c].binding == 'checkSec'
                        ||panel.columns[c].binding == 'applyStep'
                        ||panel.columns[c].binding == 'manageSec'
                        ||panel.columns[c].binding == 'BLSec'
                        ||panel.columns[c].binding == 'no'
                        ||panel.columns[c].binding == 'errSec')) {
                        var cellData = panel.getCellData(r, c);
                        cell.style.textAlign = 'center';
                    }
                }
            });
        },

        // 結合
        joinInit: function () {
            joinGrid = new wijmo.grid.FlexGrid('#union');
            joinGrid.initialize({
                allowSorting: false,
                autoGenerateColumns: false,
                columns: [
                    {header: 'No.',  binding: 'recid', width: 50, isReadOnly: true},
                    {header: '申請状態', binding: 'applycondition', isReadOnly: true},
                    {header: '処理区分', binding: 'managekbn', width: 120, isReadOnly: true},
                    {header: 'セレクトコード名称', binding: 'prmsetdtlno1', width: 180, isReadOnly: true},
                    {header: '分類コード名称', binding: 'goodsmgroup', width: 180, isReadOnly: true},
                    {header: 'BLコード名称', binding: 'tbspartscode', width: 180, isReadOnly: true},
                    {header: 'カーコード名称', binding: 'joinsourcemakercode', width: 180, isReadOnly: true},
                    {header: '純正品番', binding: 'joinsourcepartsno', width: 220, isReadOnly: true},
                    {header: '種別コード名称', binding: 'prmpetdtlno2', width: 180, isReadOnly: true},
                    {header: '表示順位', binding: 'joindisporder', width: 80, isReadOnly: true},
                    {header: '優良品番', binding: 'joindestpartsno', width: 140, isReadOnly: true},
                    {header: 'QTY', binding: 'joinqty', width: 160, isReadOnly: true},
                    {header: '規格/特記', binding: 'joinspecialnote', width: 150, minWidth: 30, isReadOnly: true},
                    {header: '規格/特記(一般)', binding: 'prmprtsspecialnote',width: 150, isReadOnly: true},
                    {header: '削除依頼区分.',  binding: 'deleteflg', width: 260, isReadOnly: true},
                    {header: '削除理由', binding: 'delreason',  width: 260, isReadOnly: true},
                    {header: '作成日時', binding: 'insdttime',dataType: wijmo.DataType.Date,width: 140, isReadOnly: true},
                    {header: '更新日時', binding:'upddttime',dataType: wijmo.DataType.Date, width: 140, isReadOnly: true},
                    {header: '適用日時', binding: 'applytime', dataType: wijmo.DataType.Date, width: 140, isReadOnly: true},
                    {header: 'BL登録区分', binding: 'bllogindiv',  width: 100, isReadOnly: true},
                    {header: 'エラー区分', binding: 'errorflg', width: 150, isReadOnly: true}
                ],
                itemFormatter: function (panel, r, c, cell) {
                    if (wijmo.grid.CellType.Cell == panel.cellType &&
                        (panel.columns[c].binding == 'money'
                        ||panel.columns[c].binding == 'width1'
                        ||panel.columns[c].binding == 'width2'
                        ||panel.columns[c].binding == 'width3'
                        ||panel.columns[c].binding == 'packwidth1'
                        ||panel.columns[c].binding == 'packwidth2'
                        ||panel.columns[c].binding == 'packwidth3'
                        ||panel.columns[c].binding == 'weight')) {
                        var cellData = panel.getCellData(r, c);
                        cell.style.textAlign = 'right';
                    }
                    if (wijmo.grid.CellType.Cell == panel.cellType &&
                        (panel.columns[c].binding == 'checkSec'
                        ||panel.columns[c].binding == 'applyStep'
                        ||panel.columns[c].binding == 'manageSec'
                        ||panel.columns[c].binding == 'BLSec'
                        ||panel.columns[c].binding == 'no'
                        ||panel.columns[c].binding == 'errSec')) {
                        var cellData = panel.getCellData(r, c);
                        cell.style.textAlign = 'center';
                    }
                }
            });
        },

        // 閉じるボタンを押す
        onReturnClick: function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        },

        // 申請戻し再提出ボタンを押す
        onApplyAgainClick: function(){
            parent.$(".apply-new-status").val('-2');
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }

    };

    // 画面モード取得
    var query = new URI(window.location.href).query(true);
    var mode = parseInt(query.mode);

    // 画面初期化
    var page = new ApplyDetail(mode);
    page.init();

    // 商品データの獲得
    function goodsData (page) {
    	var params = {};
        showFlag = 1;
        if(page == null || page == 0){
            page = 1;
        }
        params["currentPage"] = page;
        params["mode"] = sessionStorage.getItem("applyDetail");
    	$.blAjax({
    		url : baseurl + "/apply/goodsGrid",
    		data : params,
    		type : 'get',
    		dataType : 'json',
    		contentType : 'application/Json',
    		success: function(data){
                var pageSize = data.pageSize;
    			goodsCounts = data.allCounts;
                var records = data.goodsMaster;
                console.log(data);
                var message = data.messageMap;
                var applyMode = sessionStorage.getItem("applyDetail");
                var applyResult = data.applyResult;
                if(applyMode == BLENUM.MenuEnum.applyhistory && applyResult == 9) { // 申請履歴画面から、申請戻しの場合、再提出のボタンが現ずる
                    $('.again').show();
                }
                var paramArr = new Array();
                paramArr[0] = "商品";
                if(records != null){
                    records = JSON.parse(records);
                    var goodsCount = records.goodsmasterdtos.length;
                    for (var i = 0;i < goodsCount; i++) {
                        records.goodsmasterdtos[i].recid = i+1;
                    }
                    grid.itemsSource = new wijmo.collections.CollectionView(records.goodsmasterdtos);
                    grid.formatItem.addHandler(function(s, e) {
                        for (var i = 0; i < records.goodsmasterdtos.length; i++) { // 不同なデータが青いで標記する
                            var goodsFlag = new Array();
                            goodsFlag = records.goodsmasterdtos[i].goodsflg.split(",");
                            for (var j = 0; j < goodsFlag.length; j++) {
                                if (goodsFlag[j] == "1" && e.panel.cellType == wijmo.grid.CellType.Cell && e.row == i && e.col == j+1) {
                                    $(e.cell).addClass('history');
                                }
                            }
                        }
                    });
                    // 画面初期化の時、商品の全件数
                    $('.all-num').text(goodsCounts);
                    var pageCounts = Math.ceil(goodsCounts/pageSize);
                    if (page == 1) {
                    	if(pageCounts==3){
                        $('.page-number').show();
                        $('.page-number').pagination({
                            isHide: true,
                            keepShowPN: true,
                            pageCount: pageCounts,
                            coping: false,
                            count:1,
                            prevContent: '<前ページ',
                            nextContent: '後ページ>',
                            callback: pageCallback
                        });}else{
                        	$('.page-number').show();
                            $('.page-number').pagination({
                                isHide: true,
                                keepShowPN: true,
                                pageCount: pageCounts,
                                coping: true,
                                count:1,
                                prevContent: '<前ページ',
                                nextContent: '後ページ>',
                                callback: pageCallback
                            });
                        }
                    }
    	        }else{
                    $('.all-num').text("0");
                    layer.alert(getMessageInfo(message.E00008, paramArr), {
                        title : '',
                        closeBtn : 0,
                        btn : [ 'はい' ]
                    });
    	        }
    		}
    	})
    	
    }

    // セットデータの獲得
    function setData (page) {
        var params = {};
        showFlag = 2;
        if(page == null || page == 0){
            page = 1;
        }
        params["currentPage"] = page;
        params["mode"] = sessionStorage.getItem("applyDetail");
        $.blAjax({
            url : baseurl + "/apply/setGrid",
            data : params,
            type : 'get',
            dataType : 'json',
            contentType : 'application/Json',
            success: function(data){
                setCounts = data.allCounts;
                var pageSize = data.pageSize;
                var records = data.setMaster;
                console.log(data);
                var message = data.messageMap;
                var paramArr = new Array();
                paramArr[0] = "セット";
                if(records != null){
                    records = JSON.parse(records);
                    var setCount = records.setmasterdtos.length;
                    for (var i = 0;i < setCount; i++)
                    {
                        records.setmasterdtos[i].recid = i+1;
                    }
                    setGrid.itemsSource = new wijmo.collections.CollectionView(records.setmasterdtos);
                    setGrid.formatItem.addHandler(function(s, e) {
                        for (var i = 0; i < records.setmasterdtos.length; i++) {
                            var setFlag = new Array();
                            setFlag = records.setmasterdtos[i].setflg.split(",");
                            for (var j = 0; j < setFlag.length; j++) {
                                if (setFlag[j] == "1" && e.panel.cellType == wijmo.grid.CellType.Cell && e.row == i && e.col == j+1) {
                                    $(e.cell).addClass('history');
                                }
                            }
                        }
                    });
                    // セットの全件数
                    $('.all-num').text(setCounts);
                    var pageCounts = Math.ceil(setCounts/pageSize);
                    if (page == 1) {
                    	if(pageCounts==3){
                        $('.page-number').show();
                        $('.page-number').pagination({
                            isHide: true,
                            keepShowPN: true,
                            pageCount: pageCounts,
                            coping: false,
                            count:1,
                            prevContent: '<前ページ',
                            nextContent: '後ページ>',
                            callback: pageCallback
                        });}else{
                        	$('.page-number').show();
                            $('.page-number').pagination({
                                isHide: true,
                                keepShowPN: true,
                                pageCount: pageCounts,
                                coping: true,
                                count:1,
                                prevContent: '<前ページ',
                                nextContent: '後ページ>',
                                callback: pageCallback
                            });
                        }
                    }
                    $(".error-msg").text("");
                } else {
                    // セットの全件数
                    $('.all-num').text("0");
                    layer.alert(getMessageInfo(message.E00008, paramArr), {
                        title : '',
                        closeBtn : 0,
                        btn : [ 'はい' ]
                    });
                }
            }
        })
    }

    // 結合データの獲得
    function joinData (page) {
        var params = {};
        showFlag = 3;
        if(page == null || page == 0){
            page = 1;
        }
        params["currentPage"] = page;
        params["mode"] = sessionStorage.getItem("applyDetail");
        $.blAjax({
            url : baseurl + "/apply/joinGrid",
            data : params,
            type : 'get',
            dataType : 'json',
            contentType : 'application/Json',
            success: function(data){
                joinCounts = data.allCounts;
                var pageSize = data.pageSize;
                var records = data.joinMaster;
                console.log(data);
                var message = data.messageMap;
                var paramArr = new Array();
                paramArr[0] = "結合";
                if(records != null){
                    records = JSON.parse(records);
                    var joinCount = records.joinmasterdtos.length;
                    for (var i = 0;i < joinCount; i++)
                    {
                        records.joinmasterdtos[i].recid = i+1;
                    }
                    joinGrid.itemsSource = new wijmo.collections.CollectionView(records.joinmasterdtos);
                    joinGrid.formatItem.addHandler(function(s, e) {
                        for (var i = 0; i < records.joinmasterdtos.length; i++) {
                            var joinFlag = new Array();
                            joinFlag = records.joinmasterdtos[i].joinflg.split(",");
                            for (var j = 0; j < joinFlag.length; j++) {
                                if (joinFlag[j] == "1" && e.panel.cellType == wijmo.grid.CellType.Cell && e.row == i && e.col == j+1) {
                                    $(e.cell).addClass('history');
                                }
                            }
                        }
                    });
                    // 結合の全件数
                    $('.all-num').text(joinCounts);
                    var pageCounts = Math.ceil(joinCounts/pageSize);
                    if (page == 1) {
                    	if(pageCounts==3){
                        $('.page-number').show();
                        $('.page-number').pagination({
                            isHide: true,
                            keepShowPN: true,
                            pageCount: pageCounts,
                            coping: false,
                            count:1,
                            prevContent: '<前ページ',
                            nextContent: '後ページ>',
                            callback: pageCallback
                        });}else{
                        	$('.page-number').show();
                            $('.page-number').pagination({
                                isHide: true,
                                keepShowPN: true,
                                pageCount: pageCounts,
                                coping: true,
                                count:1,
                                prevContent: '<前ページ',
                                nextContent: '後ページ>',
                                callback: pageCallback
                            });
                        }
                    }
                    $(".error-msg").text("");
                } else {
                    $('.all-num').text("0");
                    layer.alert(getMessageInfo(message.E00008, paramArr), {
                        title : '',
                        closeBtn : 0,
                        btn : [ 'はい' ]
                    });
                }
            }
        })
    }

    // 商品、セット、結合の改頁
    function pageCallback(page_index) {
        if(showFlag == 1){ //　商品の場合
            goodsData(page_index.getCurrent());
        } else if(showFlag == 2) { //　セットの場合
            setData(page_index.getCurrent());
        } else if(showFlag == 3) { //　結合の場合
            joinData(page_index.getCurrent());
        }

    }

});

