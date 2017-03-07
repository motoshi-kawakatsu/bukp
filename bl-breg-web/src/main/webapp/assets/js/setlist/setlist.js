/**
 * @file セット一覧
 */
$(function () {
    'use strict';
    try {

        var SetCtlgPage = function (mode) {
            this.mode = mode;
            this.grid = new wijmo.grid.FlexGrid('#grid');
            this.table = "";
            // パラメータ初期化
            this.dataInit();
            this.gridFormat = new GirdFormat(this.grid, this.mode);
            this.gridFormat.config();
            //test
            window.grid = this.grid;

        };
        var requestFlag = false;
        var SetListOutPut = function (grid) {
            this.grid = grid;
        };
        var GirdFormat = function (grid, mode) {
            this.grid = grid;
            this.mode = mode;

        };
        var check = function() {
    		if(requestFlag)
    		{
    		    return;	
    		}
    		else
    		{
    			requestFlag = true;
    		}
    		var accessURL = baseurl + "/setlist/check";
    		var itemList=[];
            for(var i = 0;i<grid.itemsSource.items.length;i++){
            	itemList.push(grid.itemsSource.items[i]);
            }
            var form = {
            		mode : mode,
                setMasterGridFormList: itemList
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
    				
    				var collectionView = new wijmo.collections.CollectionView(
    						data.gridDataUpdate);
    				for(var i = 0;i < collectionView.items.length;i++){
    				   collectionView.items[i].no = i + 1; 
    			     }
    				    grid.itemsSource = collectionView;
    				    grid.refresh;
    					requestFlag = false;
    			},
    			error : function(data, httpStatus, errorHandler) {
    				requestFlag = false;
    			}
    		});
    	};

        SetCtlgPage.prototype = {
            // / <summary>
            // / 初期表示時 パラメータ初期化
            // / </summary>
            dataInit: function () {
                var _this = this;
                var form = {
                    mode: _this.mode,
                };
                $.blAjax({
                    url: baseurl + '/setlist/init',
                    type: 'POST',
                    dataType: 'Json',
                    data: JSON.stringify(form),
                    contentType: 'application/Json',
                    success: function (data) {
                        var controlFlag = JSON.parse(data.controlFlag);
                        if (!controlFlag) {
                            _this.mode = 2
                        }
                        // blcodelist
                        window.blCode = (JSON.parse(data.blCode) || {}).blcodemasterdtos;
                        // selectCodelist
                        window.selectCode = (JSON.parse(data.selectCode) || {}).selectcodemasterdtos;
                        if (!window.blCode) {
                            window.blCode = [];
                        }
                        if (!window.selectCode) {
                            window.selectCode = [];
                        }

                        window.message = data.messageMap;

                        if (_this.mode == 2 || _this.mode == 3) {
                            $('#applyCondition')[0].options.length = 0;
                            for (var key in data.applyConditionMap) {
                                $('#applyCondition')[0].options.add(new Option(
                                    data.applyConditionMap[key], key));
                            }
                        } else if (_this.mode == 1) {
                            $('#applyCondition')[0].options.length = 0;
                            $('#applyCondition')[0].options.add(new Option(
                                "全データ表示", ""));
                            for (var key in data.applyConditionMap) {
                                $('#applyCondition')[0].options.add(new Option(
                                    data.applyConditionMap[key], key));
                            }
                            $('#applyCondition')[0].value = 0;
                        }


                        //パラメータ初期化完成する 時　ページinit
                        _this.init();
                    },
                    error: function (data, httpStatus, errorHandler) {
                        errorHandler(data, httpStatus);
                    }
                });
            },
            // / <summary>
            // / 初期処理
            // / </summary>
            init: function () {
                //f5 refresh  message is cleared
                sessionStorage.setItem("confirmMessage", null);
                var nav = new BLUI.Nabigeshon();
                // focus init
                $("[name='tbsPartsCode']").focus();
                // 詳細条件 閉じる button
                this.slidePanel();
                //半角
                $('#setKanaName')
                    .on(
                        'blur',
                        function () {
                            var str = $(this).val();
                            if (!checkHankaku(str)) {
                                layer.alert(window.message.E00004, {
                                    title: '',
                                    closeBtn: 0,
                                    btn: ['はい']
                                });
                                $(this).val("");
                            } else {
                                $(this).val(zenkaku2Hankaku(str));
                            }
                        });


                // input clear
                var mode = this.mode;
                $('.btn-clear').on('click', function () {
                    $('.search-panel input,.search-panel select').val("");
                    switch (mode) {
                        // セット選択モード
                        case BLENUM.ModeEnum.Update:
                        // 参照モード
                        case BLENUM.ModeEnum.Readonly:
                        // エラー修正モード
                        case BLENUM.ModeEnum.Error:
                            $('[name="applyCondition"]').val(0);
                    }

                });
                // date input
                $('.date-input').datetimepickerJp();
                // 商品中分類code
                $('.classifycdguide').on('click', '', {
                    subUrl: '/setlist/goodsMGroup',
                    actionType: 'POST',
                    name: 'goodsMGroup',
                    isGrid: false
                }, $.proxy(this.showClassifyCdGuide, this));

                //品番parent
                $('.goodsCdGuideParent').on('click','',{
                    subUrl:'/setlist/setPartsNo',
                    actionType:'POST',
                    name:'setMainPartsNo',
                    type:'parent',
                    isGrid:false
                },$.proxy(this.showGoodsGuide,this));
                //品番child
                $('.goodsCdGuideChild').on('click','',{
                    subUrl:'/setlist/setPartsNo',
                    actionType:'POST',
                    name:'setSubPartsNo',
                    type:'child',
                    isGrid:false
                },$.proxy(this.showGoodsGuide,this));
                // /コードを入力後、フォーカスアウトと、名称が取得されない ※【コード：名称】で表示しない
                //div  "1"  純正品番
                //div  "2"   優良品番
                //div  "0"  分類コード
                // this.codeFormat("2",$('[name="setMainPartsNo"]'));
                // this.codeFormat("2",$('[name="setSubPartsNo"]'));
                this.codeFormat("0", $('[name="goodsMGroup"]'));



                switch (this.mode) {
                    // 検索入力モード
                    case BLENUM.ModeEnum.New:
                        this.table = "maker";
                        nav.getData(2);
                        $('.page-header h2').html('セット一覧');
                        $('.btn-yes').hide();
                        // init search
                        this.gridInit({
                            data: {
                                subUrl: '/setlist/search',
                                actionType: 'POST',
                                form: true,
                                pageNo: 1,
                                isPage: true,
                                table: "maker"
                            }
                        });

                        // 検索
                        $('.btn-search').on('click', '', {
                            subUrl: '/setlist/search',
                            actionType: 'POST',
                            form: true,
                            pageNo: 1,
                            isPage: true,
                            btnType:'search',
                            thisPage:this,
                            table: "maker"
                        },searchBeforeCheck);

                        // checklist button
                        $('.btn-check-list').on('click', '', {
                            menu: BLENUM.MenuEnum.Set,
                            url: "/checkList/checkList"
                        }, $.proxy(this.toCheckList, this));
                        // 出力 button
                        $('.btn-output').on('click', '', {}, $.proxy(this.showOutput, this));
                        // 検索・置換
                        $('.btn-replaceGuide').on('click', '', {}, $.proxy(this.showReplaceGuide, this));
                        // セット詳細画面
                        $('.btn-detail').on('click', '', {
                            buttonMode: 'detail'
                        }, $.proxy(this.toDetail, this));
                        // 戻る button
                        $('.btn-back').on('click', '', {subUrl: '/topmenu/topMenu'}, $.proxy(this.back, this));
                        // save
                        $('.btn-save').on('click', '', {
                            subUrl: '/setlist/save',
                            actionType: 'POST',
                            table: "maker"
                        }, $.proxy(this.gridSave, this));
                        // 取消 button
                        $('.btn-cancel').on('click', '', {
                            subUrl: '/setlist/search',
                            actionType: 'POST',
                            form: true,
                            pageNo: getNowPageNo,
                            isPage: false,
                            table: "maker"
                        }, $.proxy(this.gridInit, this));
                        // 新規追加 button
                        $('.btn-new').on('click', '', {
                            buttonMode: 'new'
                        }, $.proxy(this.toDetail, this));


                        this.gridFormat.init([]);

                        break;
                    // セット選択モード
                    case BLENUM.ModeEnum.Update:
                        this.table = "maker";
                        nav.getData(31);
                        $('.page-header h2').html('セット一覧（選択）');
                        $('.btn-insert,.btn-del,.btn-copy,.btn-paste,.btn-replaceGuide,.btn-check-list,.btn-new,.btn-save,.btn-output').hide();
                        // init search
                        this.gridInit({
                            data: {
                                subUrl: '/setlist/search',
                                actionType: 'POST',
                                form: true,
                                pageNo: 1,
                                isPage: true,
                                table: "maker"
                            }
                        });
                        // 検索
                        $('.btn-search').on('click', '', {
                            subUrl: '/setlist/search',
                            actionType: 'POST',
                            form: true,
                            pageNo: 1,
                            isPage: true,
                            btnType:'search',
                            thisPage:this,
                            table: "maker"
                        }, searchBeforeCheck);
                        $('.space').hide();
                        // セット詳細画面
                        $('.btn-detail').on('click', '', {
                            buttonMode: 'detail'
                        }, $.proxy(this.toDetail, this));
                        // 戻る button
                        $('.btn-back').on('click', '', {subUrl: '/applycommon/applyCommon'}, $.proxy(this.back, this));
                        // 確定
                        $('.btn-yes').on('click', '', {
                            isPopMessage: true,
                        }, $.proxy(this.selectPage, this));
                        // 取消 button
                        $('.btn-cancel').on('click', '', {
                            subUrl: '/setlist/search',
                            actionType: 'POST',
                            form: true,
                            pageNo: getNowPageNo,
                            isPage: false,
                            table: "maker"
                        }, $.proxy(this.gridInit, this));

                        this.gridFormat.init([]);

                        break;
                    // 参照モード
                    case BLENUM.ModeEnum.Readonly:
                        this.table = "maker";
                        var importKbn = sessionStorage.importModelMenu;
                        if (importKbn == "0") {
                            nav.getData(30);
                        } else if (importKbn == "1") {
                            nav.getData(33);
                        }
                        $('.page-header h2').html('セット一覧（参照）');
                        $('.btn-insert,.btn-del,.btn-copy,.btn-paste,.btn-replaceGuide,.btn-check-list,.btn-new,.btn-save,.btn-cancel,.btn-yes').hide();
                        // init search
                        this.gridInit({
                            data: {
                                subUrl: '/setlist/search',
                                actionType: 'POST',
                                form: true,
                                pageNo: 1,
                                isPage: true,
                                table: "maker"
                            }
                        });
                        // 検索
                        $('.btn-search').on('click', '', {
                            subUrl: '/setlist/search',
                            actionType: 'POST',
                            form: true,
                            pageNo: 1,
                            isPage: true,
                            btnType:'search',
                            thisPage:this,
                            table: "maker"
                        },searchBeforeCheck);
                        $('.space').hide();
                        // 出力 button
                        $('.btn-output').on('click', '', {}, $.proxy(this.showOutput, this));
                        // セット詳細画面
                        $('.btn-detail').on('click', '', {
                            buttonMode: 'detail'
                        }, $.proxy(this.toDetail, this));
                        // 戻る button
                        $('.btn-back').on('click', '', {
                            subUrl: "/readresult/readresult"
                        }, $.proxy(this.back, this));

                        break;
                    // エラー修正モード
                    case BLENUM.ModeEnum.Error:
                        this.table = "work";
                        var importKbn = sessionStorage.importModelMenu;
                        if (importKbn == "0") {
                            nav.getData(29);
                        } else if (importKbn == "1") {
                            nav.getData(34);
                        }
                        $('.page-header h2').html('セット一覧（エラー修正）');
                        $('.btn-insert,.btn-del,.btn-copy,.btn-paste,.btn-new,.btn-save').hide();
                        // init search
                        this.gridInit({
                            data: {
                                subUrl: '/setlist/search',
                                actionType: 'POST',
                                form: true,
                                pageNo: 1,
                                isPage: true,
                                table: "work"
                            }
                        });
                        // 検索
                        $('.btn-search').on('click', '', {
                            subUrl: '/setlist/search',
                            actionType: 'POST',
                            form: true,
                            pageNo: 1,
                            isPage: true,
                            btnType:'search',
                            thisPage:this,
                            table: "work"
                        },searchBeforeCheck);
                        // 出力 button
                        $('.btn-output').on('click', '', {}, $.proxy(this.showOutput, this));
                        // セット詳細画面
                        $('.btn-detail').on('click', '', {
                            buttonMode: 'detail'
                        }, $.proxy(this.toDetail, this));
                        // 検索・置換
                        $('.btn-replaceGuide').on('click', '', {}, $.proxy(this.showReplaceGuide, this));
                        // checkList
                        $('.btn-check-list').on('click', '', {
                            menu: BLENUM.MenuEnum.setcorrect,
                            url: "/checkList/checkList"
                        }, $.proxy(this.toCheckList, this));
                        // 確定
                        $('.btn-yes').on('click', '', {
                            subUrl: '/setlist/save',
                            actionType: 'POST',
                            table: "work"
                        }, $.proxy(this.gridSave, this));
                        // cancel
                        $('.btn-cancel').on('click', '', {
                            subUrl: '/setlist/search',
                            actionType: 'POST',
                            form: true,
                            pageNo: getNowPageNo,
                            isPage: false,
                            table: "work"
                        }, $.proxy(this.gridInit, this));
                        // 戻る button
                        $('.btn-back').on('click', '', {
                            subUrl: "/importresult/importresult"
                        }, $.proxy(this.back, this));
                        break;

                    default:
                        break;
                }


            },
            // 詳細条件 閉じる button
            slidePanel: function () {
                $('.init-hide').css('display', 'none');
                var head = $('.panel-heading.head');
                var middle = $('.panel-heading.middle');
                head.addClass('panel-heading-down');
                head.append("<span class='glyphicon glyphicon-chevron-down'></span>");
                // panel click切り替え
                head.click(function () {
                    var panel = $(this).parent('.slide-panel');
                    var button = $('.search-scope-buttons button:nth-child(3)');
                    $('.init-hide').slideToggle();
                    panel.toggleClass('slide-down');
                    if (panel.hasClass('slide-down')) {
                        button.removeClass('detail-condition').addClass('close-condition').html('閉じる');
                    } else {
                        button.removeClass('close-condition').addClass('detail-condition').html('詳細条件');
                    }
                });
                // show panel
                var panelShow = function () {
                    $(this).removeClass('detail-condition').addClass('close-condition').html('閉じる');
                    $('.init-hide').slideToggle();
                    $('.panel-heading.head').parent('.slide-panel').toggleClass('slide-down');
                };
                // hide panel
                var panelHidden = function () {
                    $(this).removeClass('close-condition').addClass('detail-condition').html('詳細条件');
                    $('.init-hide').slideToggle();
                    $('.panel-heading.head').parent('.slide-panel').toggleClass('slide-down');
                }
                // 詳細条件
                $('.group-button').on('click', '.detail-condition', {}, panelShow);
                // 閉じる button
                $('.group-button').on('click', '.close-condition', {}, panelHidden);


            }
            ,


            // / <summary>
            // /コードを入力後、フォーカスアウトと、名称が取得されない ※【コード：名称】で表示しない
            // / </summary>
            codeFormat: function (div, $obj) {
                //div  "1"  純正品番
                //div  "2"   優良品番
                //div  "0"  分類コード
                $obj.focus(function (event) {
                    setValue();
                });
                $obj.blur(function (event) {
                    getValue(event, div);
                });
                function setValue() {
                    var value = event.currentTarget.value;
                    value = value.split("：")[0];
                    event.currentTarget.value = value;
                }

                function getValue(event, div) {
                    var accessURL = baseurl + "/changecommon/getCode";
                    var form = {
                        code: event.currentTarget.value,
                        guideType: div
                    };
                    $.blAjax({
                        url: accessURL,
                        type: "POST",
                        data: JSON.stringify(form),
                        error: function (data, httpStatus, errorHandler) {
                            errorHandler(data, httpStatus);
                        },
                        success: function (data) {
                            if (data.message != undefined) {
                                layer.alert("該当するデータが存在しません", {
                                    title: '',
                                    closeBtn: 0,
                                    btn: ['はい'],
                                    end: function () {
                                        event.currentTarget.value = "";
                                    }
                                });
                            }
                            event.currentTarget.value = data.codeValue;
                        },
                    });
                }
            },

            // / <summary>
            // / url処理
            // / </summary>
            urlJump: function (event) {
                location.href = event.data.url;
            },
            // / <summary>
            // / back処理
            // / </summary>
            back: function (event) {
                if(this.mode==BLENUM.ModeEnum.Update){
                    var accessURL =baseurl+'/setlist/selectBack';
                    var backUrl = baseurl + event.data.subUrl;
                    $.blAjax({
                        url: accessURL,
                        type: 'POST',
                        success: function (data) {
                        	window.location.href=backUrl;
                        },
                        error: function (data, httpStatus, errorHandler) {
                            errorHandler(data, httpStatus);
                        }
                    });
                }else{
                    var backUrl = baseurl + event.data.subUrl;
                    if (this.isGridEdited()) {
                        layer.confirm(window.message.Q00001, {
                            icon: 3,
                            title: '',
                            closeBtn: 0,
                            btn: ['はい', 'いいえ']
                        }, function (index) {
                            layer.close(index);
                            window.location.href = backUrl;
                        })
                    } else {
                        window.location.href = backUrl;
                    }
                }

            },
            // / <summary>
            // / popup guide
            // / </summary>
            showClassifyCdGuide: function (event) {
                try {
                    var accessURL = baseurl + event.data.subUrl;
                    var actionType = event.data.actionType;
                    var inputname = event.data.name;
                    var isGrid = event.data.isGrid;
                    var form = {
                        goodsMGroup: $('[name="goodsMGroup"]').val()
                    };
                    $.blAjax({
                        url: accessURL,
                        type: actionType,
                        data: JSON.stringify(form),
                        success: function (data) {
                            var guide = new BLUI.ClassifyCdGuide();
                            guide.show(inputname, isGrid, {});

                        },
                        error: function (data, httpStatus, errorHandler) {
                            errorHandler(data, httpStatus);
                        }
                    });
                } catch (e) {
                    sendClientErrorLog(e);
                }

            },

            // / <summary>
            // / goods guide
            // / </summary>
            showGoodsGuide: function (event) {
                    var accessURL = baseurl + event.data.subUrl;
                    var inputname = event.data.name;
                    var isGrid = event.data.isGrid;
                    var form={};
                    if(event.data.type=='parent'){
                        form={
                            setSubPartsNo:$('[name="setMainPartsNo"]').val(),
                        }
                    }else{
                        form={
                            setSubPartsNo:$('[name="setSubPartsNo"]').val(),
                        }
                    }
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
            },

            // / <summary>
            // / popup guide
            // / </summary>
            showReplaceGuide: function () {
                var guide = new BLUI.ShowReplaceGuide();
                guide.show(BLENUM.MenuEnum.Set);
            },
            // / <summary>
            // / outPut guide
            // / </summary>
            showOutput: function () {
                var _this = this;
                if (!_this.grid.collectionView || !_this.grid.collectionView.items || _this.grid.collectionView.items.length == 0) {
                    layer.alert(message.E00009, {
                        title: '',
                        closeBtn: 0,
                        btn: ['はい']
                    });
                    return;
                }
                var outPut = new SetListOutPut(_this.grid);
                if (this.isGridEdited()) {
                    layer.confirm(window.message.Q00001, {
                        icon: 3,
                        title: '',
                        closeBtn: 0,
                        btn: ['はい', 'いいえ']
                    }, function (index) {
                        layer.close(index);
                        //cancel mapulation
                        _this.gridInit({
                            data: {
                                subUrl: '/setlist/search',
                                actionType: 'POST',
                                form: true,
                                pageNo: getNowPageNo,
                                isPage: false,
                                table: _this.table
                            }
                        });
                        outPut.show();
                    })

                } else {
                    outPut.show();
                }

            },
            // / <summary>
            // / セット詳細画面へ
            // / </summary>
            toDetail: function (event) {
                var _this = this;
                var jumpToDetail = function () {
                    try {
                        // 初期データの準備
                        var form = null;
                        if (event.data.buttonMode == 'detail') {
                            var selectedRows = _this.grid.selectedRows;
                            var selectedItem = _this.grid.selectedItems[0];
                            if (selectedRows.length == 0) {
                                layer.alert("明細行が選択されていません。", {
                                    title: '',
                                    closeBtn: 0,
                                    btn: ['はい']
                                });
                                return;
                            }
                            form = {
                                prmSetDtlNo1: selectedItem.prmSetDtlNo1,
                                partsMakerCd: selectedItem.partsMakerCd,
                                goodsMGroup: selectedItem.goodsMGroup,
                                setMainPartsNo: selectedItem.setMainPartsNo,
                                mode: _this.mode

                            }
                        } else if (event.data.buttonMode == 'new') {
                            form = {
                                mode: 4
                            }
                        }
                        sessionStorage.setItem("setDetail", JSON.stringify(form));
                        new BLUI.Setdetail().show();
                    } catch (e) {
                        sendClientErrorLog(e);
                    }
                }
                jumpToDetail();

                // if (this.isGridEdited()) {
                //     layer.confirm(window.message.Q00001, {
                //         icon: 3,
                //         title: '',
                //         closeBtn: 0,
                //         btn: ['はい', 'いいえ']
                //     },  function(index){
                //         layer.close(index);
                //         //cancel mapulation
                //         _this.gridInit({data:{
                //             subUrl: '/setlist/search',
                //             actionType: 'POST',
                //             form: true,
                //             pageNo: window.pageNo || 1,
                //             isPage: false,
                //             table: _this.table
                //         }});
                //         jumpToDetail();
                //     })
                // } else {
                //     jumpToDetail();
                // }


            },
            // / <summary>
            // / checklist画面へ
            // / </summary>
            toCheckList: function (event) {
                var _this = this;
                var menu = event.data.menu;
                var url = baseurl + event.data.url;

                var checkList = function (index) {
                    layer.config({
                        extend: '../../css/classifyCodeGuide/layerButton.css'
                    });
                    layer.open({
                        type: 2,
                        title: false,
                        closeBtn: 0,
                        skin: 'layer-ext-skin',
                        shade: 0.1,
                        area: ['100%', '80%'],
                        content: [url],
                    });
                    sessionStorage.setItem("checkList", menu);
                    //window.location.href="../checkList/checkList";
                    layer.close(index);
                };

                if (this.isGridEdited()) {
                    layer.confirm(window.message.Q00001, {
                        icon: 3,
                        title: '',
                        closeBtn: 0,
                        btn: ['はい', 'いいえ']
                    }, function (index) {
                        layer.close(index);
                        //cancel mapulation
                        _this.gridInit({
                            data: {
                                subUrl: '/setlist/search',
                                actionType: 'POST',
                                form: true,
                                pageNo: getNowPageNo,
                                isPage: false,
                                table: _this.table
                            }
                        });
                        checkList();
                    })
                } else {
                    checkList();
                }
            },
            // / <summary>
            // / grid init
            // / </summary>
            gridInit: function (event) {
                var _this = this;
                try {

                    // グリッドの初期化
                    var grid = this.grid;
                    // 初期データの準備
                    var data = {};
                    var accessURL = baseurl + event.data.subUrl;
                    var actionType = event.data.actionType;
                    var form = event.data.form ? this.getSearchData() : {};
                    if (form.startTimeStart != "" && form.startTimeEnd != "" &&
                        form.startTimeStart > form.startTimeEnd
                    ) {
                        layer.alert(window.message.E000061, {
                            title: '',
                            closeBtn: 0,
                            btn: ['はい']
                        });
                        return;
                    }
                    if (form.insDtTimeStart != "" && form.insDtTimeEnd != "" &&
                        form.insDtTimeStart > form.insDtTimeEnd
                    ) {
                        layer.alert(window.message.E000062, {
                            title: '',
                            closeBtn: 0,
                            btn: ['はい']
                        });
                        return;
                    }
                    if (form.updDtTimeStart != "" && form.updDtTimeEnd != "" &&
                        form.updDtTimeStart > form.updDtTimeEnd
                    ) {
                        layer.alert(window.message.E000063, {
                            title: '',
                            closeBtn: 0,
                            btn: ['はい']
                        });
                        return;
                    }

                    var thisPageNo=event.data.pageNo;
                    if(typeof thisPageNo == 'number'){
                        // pageNo
                        form.pageNo =thisPageNo;
                    }else {
                        form.pageNo=thisPageNo();
                    }

                    // search table
                    form.table = event.data.table;
                    // mode
                    form.mode = _this.mode;

                    $.blAjax({
                        url: accessURL,
                        type: actionType,
                        data: JSON.stringify(form),
                        success: function (data) {
                            // setlist's data
                            var result = (JSON.parse(data.result) || {}).result;

                            //isPage pageOperator mark
                            if (event.data.isPage) {
                                // thisTimeTotalNum of page
                                var thisTimeTotalNum = data.thisTimeTotalNum;
                                // totalNum of page
                                var totalNum = data.totalNum;
                                var maxRows = data.maxRows;
                                $('.searchcount').text(thisTimeTotalNum);
                                $('.totalcount').text(totalNum);
                                $('.historyrows').text(maxRows);
                                _this.paging(event.data.table, thisTimeTotalNum, maxRows, event.data.pageNo);
                            }
                            // set grid data 's no and check
                            var adapter = function (result) {
                                for (var i = 0; i < result.length; i++) {
                                    result[i].no = i + 1;
                                    result[i].hiddenArea = result[i].hiddenArea!="null"||1;
                                    result[i].check = (result[i].check == "true");
                                }
                            }
                            if (result != null && result != '' && result != undefined) {
                                adapter(result);
                                $(".page-info").show();
                                $('.btn-del').attr("disabled", false);
                                $('.btn-copy').attr("disabled", false);
                                $('.btn-paste').attr("disabled", false);
                                $('.btn-replaceGuide').attr("disabled", false);
                                $('.btn-detail').attr("disabled", false);
                                _this.gridFormat.init(result)
                            } else {
                                $(".page-info").hide();
                                $('.btn-del').attr("disabled", true);
                                $('.btn-copy').attr("disabled", true);
                                $('.btn-paste').attr("disabled", true);
                                $('.btn-replaceGuide').attr("disabled", true);
                                $('.btn-detail').attr("disabled", true);
                                _this.gridFormat.init(result);
                                layer.alert(window.message.E00008, {
                                    title: '',
                                    closeBtn: 0,
                                    btn: ['はい']
                                });
                            }
                        },
                        error: function (data, httpStatus, errorHandler) {
                            errorHandler(data, httpStatus);
                        }
                    });
                } catch (e) {
                    sendClientErrorLog(e);
                }

            },
            // saveGridData
            gridSave: function (event) {
                var _this = this;
                // 保存内容 check
                var saveCheck = function () {
                    var cv = _this.grid.collectionView;
                    if (!cv) {
                        // menu data is null
                        return false;
                    }
                    var count = cv.totalItemCount;
                    var hasEdited = false;
                    for (var i = 0; i < count; i++) {
                        if (cv.items[i].hiddenArea == 0
                            || cv.items[i].hiddenArea == 2
                            || cv.items[i].hiddenArea == 3) {
                            hasEdited = true;
                            break;
                        }
                    }
                    return hasEdited;
                }
                if (!saveCheck()) {
                    if (_this.mode == BLENUM.ModeEnum.New) {
                        layer.alert(window.message.E00013, {
                            title: '',
                            closeBtn: 0,
                            btn: ['はい']
                        });
                    } else if (_this.mode == BLENUM.ModeEnum.Error) {
                        layer.alert(window.message.E00014, {
                            title: '',
                            closeBtn: 0,
                            btn: ['はい']
                        });
                    }
                    return;
                } else {
                    layer.confirm(window.message.Q00002,
                        {
                            icon: 3,
                            title: '',
                            closeBtn: 0,
                            btn: ['はい', 'いいえ']
                        }, function (index) {
                            layer.close(index);
                            var accessURL = baseurl + event.data.subUrl;
                            var actionType = event.data.actionType;
                            var itemList=[];
                            for(var i = 0;i<_this.grid.itemsSource.items.length;i++){
                            	itemList.push(_this.grid.itemsSource.items[i]);
                            }
                            var form = {
                                setMasterGridFormList: itemList
                            }
                            form.table = event.data.table;

                            $.blAjax({
                                url: accessURL,
                                type: actionType,
                                data: JSON.stringify(form),
                                success: function (data) {
                                    var adapter = function (result) {
                                        for (var i = 0; i < result.length; i++) {
                                            result[i].no = i + 1;
                                        }
                                    }
                                    adapter(data.gridDataUpdate);
                                    _this.gridFormat.init(data.gridDataUpdate);

//                                    	collectionView = new wijmo.collections.CollectionView(
//                        						data.gridDataUpdate);
                                    if (!data.isErrorExist) {
                                        layer.alert(window.message.I00001, {
//                        						layer.alert(getMessageInfo(message.I00001), {
                                            title: '',
                                            closeBtn: 0,
                                            btn: ['はい']
                                        });
                                        if (mode == BLENUM.ModeEnum.Error) {
                                            window.location.href = "../importresult/importresult";
                                        }
                                        // init search
                                        _this.gridInit({
                                            data: {
                                                subUrl: '/setlist/search',
                                                actionType: 'POST',
                                                form: true,
                                                pageNo: 1,
                                                isPage: true,
                                                table: "maker"
                                            }
                                        });
//                        					if (window.isInit) {
//                        						_this.gridFormat.init(data.gridDataUpdate);
//                        						gridInit();
//                        					} else {
//                        						search();
//                        					}
//                        					sessionStorage.setItem("confirmMessage", null);
                                    } else {
                                        layer.alert(window.message.E00018, {
//                        						layer.alert(getMessageInfo(message.I00001), {
                                            title: '',
                                            closeBtn: 0,
                                            btn: ['はい']
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
//                        					if (mode == 1) {
//                        						init(0);
//                        					} else if (mode == 3) {
//                        					    window.location.href = "../importresult/importresult";
//                        					}
                                    }
                                },
                                error: function (data, httpStatus, errorHandler) {
                                    layer.alert("Dataが存在しない", {
                                        title: '',
                                        closeBtn: 0,
                                        btn: ['はい']
                                    });
                                }
                            });

                        });
                }
//                layer.close(index);
            },
            // 選択 mode
            selectChangePage:function () {
                var accessURL = baseurl + "/setlist/doSelectPage";
                var form = {
                    setMasterGridFormList: this.grid.itemsSource.items
                }
                $.blAjax({
                    url: accessURL,
                    type: 'POST',
                    dataType: 'Json',
                    data: JSON.stringify(form),
                    contentType: 'application/Json',
                    success: function (data) {
                    },
                    error: function (data, httpStatus, errorHandler) {
                    }
                });
            },

            // 選択 mode
            selectPage: function (event) {
                var isPopMessage = event.data.isPopMessage;
                var accessURL = baseurl + "/setlist/yes";
                if(this.grid.itemsSource.items.length == 0){
                	layer.alert(window.message.E00014, {
                        title: '',
                        closeBtn: 0,
                        btn: ['はい']
                    });
                	return;
                }
                var form = {
                    setMasterGridFormList: this.grid.itemsSource.items
                }
                $.blAjax({
                    url: accessURL,
                    type: 'POST',
                    dataType: 'Json',
                    data: JSON.stringify(form),
                    contentType: 'application/Json',
                    success: function (data) {
                        if (isPopMessage) {
                            window.location.href = "../applycommon/applyCommon"
                        }
                    },
                    error: function (data, httpStatus, errorHandler) {
                        // 『 TODO 20170217 wangnan get error message from database 』
                        layer.alert("Dataが存在しない", {
                            title: '',
                            closeBtn: 0,
                            btn: ['はい']
                        });
                    }
                });

            },
            paging: function (table, searchcount, historyrows, currentPage) {
                var _this = this;
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
                    window.pageNo = page_index.getCurrent();
                    if (_this.mode != BLENUM.ModeEnum.Update && _this.isGridEdited()) {
                        layer.confirm(window.message.Q00001,
                            {
                                icon: 3,
                                title: '',
                                closeBtn: 0,
                                btn: ['はい', 'いいえ']
                            },
                            function (index, layero) {
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
                                    searchHandler(page_index.getCurrent());
                                    layer.close(index);
                                } else {
                                    $('.page-box').hide();
                                }
                            }, function (index) {
                                layer.close(index);
                            })
                        return false;
                    } else {
                        if (_this.mode == BLENUM.ModeEnum.Update) {
                            //update Mode  data need to be saved!!
                            _this.selectChangePage();
                        }
                        searchHandler(page_index.getCurrent());
                    }
                };


                //isPage pageOperator mark
                //isPage undefind
                var searchHandler = function (page) {
                    // console.log('thispageNo:'+page);

                    _this.gridInit({
                        data: {
                            subUrl: '/setlist/search',
                            actionType: 'POST',
                            form: true,
                            pageNo: page,
                            table: table
                        }
                    });
                };

            },
            // 編集破棄
            isGridEdited: function () {
                var cv = this.grid.collectionView;
                if (!cv) {
                    // menu data is null
                    return false;
                }
                var count = cv.totalItemCount;
                var hasEdited = false;

                for (var i = 0; i < count; i++) {
                    if (cv.items[i].hiddenArea == 0
                        || cv.items[i].hiddenArea == 2
                        || cv.items[i].hiddenArea == 3) {
                        hasEdited = true;
                        break;
                    }
                }


                return hasEdited;


            },

            getSearchData: function () {
                var form = {
                    // sort
                    sort: $("[name='sort']").val(),
                    // BLコード = 引数.BLコード\
                    tbsPartsCode: $("[name='tbsPartsCode']").val(),
                    // セット親品番 = 引数.セット親品番
                    setMainPartsNo: $("[name='setMainPartsNo']").val(),
                    // 品名 = 引数.品名
                    setKanaName: $("[name='setKanaName']").val(),
                    // 申請状態 = 引数.申請状態
                    applyCondition: $("[name='applyCondition']").val(),
                    // セット子品番 = 引数.セット子品番
                    setSubPartsNo: $("[name='setSubPartsNo']").val(),
                    // 優良設定詳細コード１ = 引数.セレクトコード
                    prmSetDtlNo1: $("[name='prmSetDtlNo1']").val(),
                    // 削除依頼区分
                    deleteFlg: $("[name='deleteFlg']").val(),
                    // 商品中分類コード = 引数.分類コード
                    goodsMGroup: $("[name='goodsMGroup']").val(),
                    // set規格・特記事項(C向け) = 引数.set規格/特記
                    setSpecialNote: $("[name='setSpecialNote']").val(),
                    // 処理区分=引数.処理区分
                    manageKbn: $("[name='manageKbn']").val(),
                    // データステータス = 引数.エラー区分
                    errorFlg: $("[name='errorFlg']").val(),
                    // 適用日付 >= 引数.適用日start
                    startTimeStart: $("[name='startTimeStart']").val(),
                    // 適用日付 <= 引数.適用日end
                    startTimeEnd: $("[name='startTimeEnd']").val(),
                    // 作成日時 >= 引数.作成日start
                    insDtTimeStart: $("[name='insDtTimeStart']").val(),
                    // 作成日時 <= 引数.作成日end
                    insDtTimeEnd: $("[name='insDtTimeEnd']").val(),
                    // 更新日時 >= 引数.更新日
                    updDtTimeStart: $("[name='updDtTimeStart']").val(),
                    // 更新日時 <= 引数.更新日
                    updDtTimeEnd: $("[name='updDtTimeEnd']").val(),

                }
                return form;
            }

        }


        GirdFormat.prototype = {
            config: function () {
                this.rowMapulation();
            },

            init: function (result) {
                // girdConifg init
                var grid = this.grid;
                var mode = this.mode;
                var collectionView = new wijmo.collections.CollectionView(result);
                var count = collectionView.totalItemCount;
                if (mode == BLENUM.ModeEnum.Error) {
                    for (var i = 0; i < count; i++) {
                        collectionView.items[i].check = true;
                    }
                }
                var grid_col_defs = {
                    check: {header: '', binding: 'check', dataType: wijmo.DataType.Boolean, width: 50, isReadOnly: true},
                    no: {header: 'No.', binding: 'no', width: 50, isReadOnly: true},
                    applyCondition: {header: '申請状態', binding: 'applyCondition', width: 80, isReadOnly: true},
                    manageKbn: {header: '処理区分', binding: 'manageKbn', width: 80, isReadOnly: true},
                    prmSetDtlNo1: {header: 'セレクトコード名称', binding: 'prmSetDtlNo1', width: 140},
                    goodsMGroup: {header: '分類コード名称', binding: 'goodsMGroup', width: 140},
                    tbsPartsCode: {header: 'BLコード名称', binding: 'tbsPartsCode', width: 220},
                    setMainPartsNo: {header: 'セット親品番', binding: 'setMainPartsNo', width: 140},
                    setDispOrder: {
                        header: '表示順位',
                        binding: 'setDispOrder',
                        dataType: wijmo.DataType.Number,
                        width: 140
                    },
                    setSubPartsNo: {header: 'セット子品番', binding: 'setSubPartsNo', width: 140},
                    setKanaName: {header: 'セット品名（半角）', binding: 'setKanaName', width: 160},
                    setName: {header: 'セット品名（全角）', binding: 'setName', width: 260, minWidth: 30},
                    setQty: {header: 'QTY', binding: 'setQty', width: 60, dataType: wijmo.DataType.Number},
                    setSpecialNote: {
                        header: '規格/特記',
                        binding: 'setSpecialNote',
                        width: 260,
                        dataType: wijmo.DataType.String
                    },
                    primePartsSpecialNoteC: {
                        header: '規格/特記（一般）',
                        binding: 'primePartsSpecialNoteC',
                        width: 260,
                        dataType: wijmo.DataType.String
                    },
                    deleteFlg: {header: '削除依頼区分', binding: 'deleteFlg', width: 100},
                    deleteReason: {
                        header: '削除理由',
                        binding: 'deleteReason',
                        width: 260,
                        dataType: wijmo.DataType.String
                    },
                    insDtTime: {header: '作成日時', binding: 'insDtTime', width: 100, isReadOnly: true},
                    updDtTime: {header: '更新日時', binding: 'updDtTime', width: 100, isReadOnly: true},
                    startTime: {header: '適用日時', binding: 'startTime', width: 100},
                    checkFlg: {header: 'チェック区分', binding: 'checkFlg', width: 100, isReadOnly: true},
                    blEntryFlg: {header: 'BL登録区分', binding: 'blEntryFlg', width: 100, isReadOnly: true},
                    errorFlg: {header: 'エラー区分', binding: 'errorFlg', width: 100, isReadOnly: true},
                    errorDetail: {header: 'エラー内容', binding: 'errorDetail', width: 130, isReadOnly: true}
                };


                var gridConfig = {};
                gridConfig.autoGenerateColumns = false;
                gridConfig.allowSorting = false;
                gridConfig.autoClipboard = false;
                gridConfig.itemsSource = collectionView;
                gridConfig.selectionMode = wijmo.grid.SelectionMode.Row;
                grid.headersVisibility = wijmo.grid.HeadersVisibility.Column;

                // chekbox isvisible
                switch (this.mode) {
                    case BLENUM.ModeEnum.New:
                        grid_col_defs.check.visible = false;
                        break;
                    case BLENUM.ModeEnum.Readonly:
                        grid_col_defs.check.visible = false;
                        for (var attr in grid_col_defs) {
                            grid_col_defs[attr].isReadOnly = true;
                        }
                        break;
                    case BLENUM.ModeEnum.Error:
                        grid_col_defs.check.isReadOnly = true;
                        break;
                    case BLENUM.ModeEnum.Update:
                        for (var attr in grid_col_defs) {
                            if (attr != 'check') {
                                grid_col_defs[attr].isReadOnly = true;
                            }
                        }
                        gridConfig.selectionMode = wijmo.grid.SelectionMode.ListBox;
                        break;
                    default:
                        break;
                }
                gridConfig.columns = [];
                for (var grid_col_name in grid_col_defs) {
                    gridConfig.columns.push(grid_col_defs[grid_col_name]);
                }


                grid.initialize(gridConfig);


                // グリッドの初期選択状態を設定
                grid.select(-1, -1);


                // public function
                // グリッドカラムの現在のIndexを取得 （カラム移動を対応できる）
                var getColumnIndex = function (binding) {
                    return grid.cells.columns.getColumn(binding).index;
                }

                // グリッドIndexによって、カラムのCssClassを取得 （カラム移動を対応できる）
                var getColumnCssClass = function (col) {
                    return grid.cells.columns[col].cssClass;
                }

                // グリッドIndexによって、カラムのDataTypeを取得 （カラム移動を対応できる）
                var getColumnDataType = function (col) {
                    return grid.cells.columns[col].dataType;
                }

                // グリッドIndexによって、カラムのBindingを取得 （カラム移動を対応できる）
                var getColumnBinding = function (col) {
                    return grid.cells.columns[col].binding;
                }

                // find cell object
                var findCell = function (row, col) { // FIXME:
                    // 不正場合がある、原因がわからない、FlexGridのバグ？
                    // find the cell from its bounding rectangle
                    var rc = grid.getCellBoundingRect(row, col);
                    var cell = document.elementFromPoint(rc.left + rc.width / 2, rc.top + rc.height / 2);

                    // make sure this is a regular cell (not a header)
                    if (wijmo.hasClass(cell, 'wj-header')) {
                        cell = null;
                    }

                    // make sure this is not an element within a cell
                    while (cell && !wijmo.hasClass(cell, 'wj-cell')) {
                        cell = cell.parentElement;
                    }
                    return cell;
                }


                // NO.25 列の表示/非表示設定
                var setColumnVisible = function (cols, visible) {
                    var colArray = cols.split(',');
                    for (var i = 0; i < colArray.length; i++) {
                        grid.cells.columns.getColumn(colArray[i]).visible = visible;
                    }
                }

                // 読取専用のセルを判断用
                var isReadonlyCell = function (row, col) {
                    if (row < 0 || col < 0) {
                        return true;
                    }
                    var column = grid.cells.columns[col];
                    var colBinding = column.binding;
                    if (column.isReadOnly) {
                        return true;
                    }
                    var hiddenArea=grid.itemsSource.items[row].hiddenArea;
                    // 種別列の値でセル読取かどうかことを制御

                    if (col == getColumnIndex(grid_col_defs.no.binding)
                        || col == getColumnIndex(grid_col_defs.applyCondition.binding)
                        || col == getColumnIndex(grid_col_defs.manageKbn.binding)
                        || col == getColumnIndex(grid_col_defs.insDtTime.binding)
                        || col == getColumnIndex(grid_col_defs.updDtTime.binding)
                        || col == getColumnIndex(grid_col_defs.checkFlg.binding)
                        || col == getColumnIndex(grid_col_defs.blEntryFlg.binding)
                        || col == getColumnIndex(grid_col_defs.errorFlg.binding)
                        || col == getColumnIndex(grid_col_defs.errorDetail.binding)
                        || col == getColumnIndex(grid_col_defs.goodsMGroup.binding)
                        || col == getColumnIndex(grid_col_defs.setMainPartsNo.binding)
                        || col == getColumnIndex(grid_col_defs.setSubPartsNo.binding)
                    ) {
                        return true;
                    } else if (col == getColumnIndex(grid_col_defs.prmSetDtlNo1.binding)) {
                        if (hiddenArea==BLENUM.JudgeEnum.Add) {
                            return false;
                        } else {
                            return true;
                        }

                    } else if(col == getColumnIndex(grid_col_defs.setDispOrder.binding)){
                        if(mode==BLENUM.ModeEnum.Error){
                            return false;
                        }else if(hiddenArea==BLENUM.JudgeEnum.Add){
                            return false;
                        }else{
                            return true;
                        }

                    }else{
                        return false;
                    }


//                    switch (mode){
//                        case BLENUM.ModeEnum.Error:
//                            if (col == getColumnIndex(grid_col_defs.setDispOrder.binding)
//                                || col == getColumnIndex(grid_col_defs.prmSetDtlNo1.binding)){
//                                return false;
//                            }
//                            break;
//                        case BLENUM.ModeEnum.New:
//                        case BLENUM.ModeEnum.Update:
//                        case BLENUM.ModeEnum.Readonly:
//                            if (col == getColumnIndex(grid_col_defs.setDispOrder.binding)
//                                || col == getColumnIndex(grid_col_defs.prmSetDtlNo1.binding)){
//                                return true;
//                            }
//                            break;
//                        default:
//                            break;
//
//                    }

                }

                // 見えるセルを判断用
                var isVisibleCell = function (row, col) {
                    for (var name in grid_col_defs) {
                        if (getColumnIndex(name) == col) {
                            return (typeof grid_col_defs[name].visible == "undefined" || grid_col_defs[name].visible == true);
                        }
                    }
                    return false;
                }

                // NO.7 Gridのコンボボックスを作成
                // create comboxbox in grid
                var createGridComboBoxCell = function (r, c, cell, itemsSource, classList) {
                    if (classList) {
                        cell.innerHTML = '<div id="grid_combox" class="' + classList + '"></div>';
                    } else {
                        cell.innerHTML = '<div id="grid_combox"></div>';
                    }
                    // <div id="grid_combox"></div>
                    var comboBox = new wijmo.input.ComboBox('#grid_combox');

                    comboBox.isEditable = false;
                    comboBox.required = false;
                    comboBox.itemsSource = itemsSource;
                    comboBox.text = grid.getCellData(r, c);
                    comboBox.textChanged.addHandler(function (e) {
                        $(grid.activeEditor).val(comboBox.selectedValue);
                    });
                    // clickHandler for key
                    var gridComboxClickHandler = function (e) {
                        if (comboBox.isDroppedDown) {
                            comboBox.isDroppedDown = false;
                        } else {
                            comboBox.isDroppedDown = true;
                        }
                        e.stopImmediatePropagation();
                        e.preventDefault();
                    };
                    $(comboBox.inputElement).bind('click', gridComboxClickHandler);
                    $(comboBox.inputElement).bind('keydown', 'alt+down alt+up', gridComboxClickHandler);

                    var gridComboxUpDownKeyHandler = function (e) {
                        if (comboBox.isDroppedDown) {
                            // shift key
                            var shift = event.shiftKey && !event.ctrlKey && !event.altKey && !event.metaKey;
                            // alt key
                            var alt = !event.shiftKey && !event.ctrlKey && event.altKey && !event.metaKey;
                            // ctrl key
                            var ctrl = !event.shiftKey && event.ctrlKey && !event.altKey && !event.metaKey;
                            // no special key
                            var nospec = !event.shiftKey && !event.ctrlKey && !event.altKey && !event.metaKey;
                            if (nospec && event.which == 38) { // up
                                comboBox.selectedIndex = Math.max(0, comboBox.selectedIndex - 1);
                                if (comboBox.selectedIndex < 0) {
                                    comboBox.selectedIndex = 0;
                                }
                                e.stopImmediatePropagation();
                                e.preventDefault();
                            } else if (nospec && event.which == 40) { // down
                                comboBox.selectedIndex = Math.min(comboBox.itemsSource.length - 1, comboBox.selectedIndex + 1);
                                e.stopImmediatePropagation();
                                e.preventDefault();
                            }
                        }
                    }
                    $(comboBox.inputElement).bind('keydown', 'up down', gridComboxUpDownKeyHandler);
                    $(comboBox.inputElement).bind('keydown', 'return tab shift+return shift+tab left right up down', gridKeydownHandler); // フォーカス制御用
                };

                // NO.7 GridのdatetimepickerJp
                var createGridInputCell = function (r, c, cell) {
                    // cell.innerHTML = '<div><input id="date"></div>';
                    // $("#date").datetimepickerJp();
// $(grid.activeEditor).datetimepickerJp();
// cell.innerHTML = '<div id="theInputTime"></div>';
// new wijmo.input.InputTime('#theInputTime', {
// min: new Date(2014, 8, 1, 9, 0),
// max: new Date(2014, 8, 1, 17, 0),
// step: 15,
// format: 'h:mm tt',
// value: new Date()
// });
                };


                // get blcodeArray
                var blCodeArray = function () {
                    var text = [];

                    var blCode = window.blCode;
                    if (!blCode) {
                        blCode = [];
                    }
                    for (var i = 0; i < blCode.length; i++) {
                        text.push(blCode[i].blcode + "：" + blCode[i].blfullname);
                    }

                    return text;
                }
                // get selectCodeArray
                var selectCodeArray = function () {
                    var text = [];
                    text.push("9999:指定無し");
                    var selectCode = window.selectCode;
                    if (!selectCode) {
                        selectCode = [];
                    }
                    for (var i = 0; i < selectCode.length; i++) {
                        text.push(selectCode[i].prmsetdtlno1 + "：" + selectCode[i].prmsetdtlname);
                    }
                    return text;
                }
                var blCodeArray = blCodeArray();
                var selectCodeArray = selectCodeArray();

                grid.viewData = {};
                grid.viewData.blStr = blCodeArray;
                grid.viewData.selectStr = selectCodeArray;

                var oldData;
                var newData;
                grid.cellEditEnding.addHandler(function (sender, e) {
                    var binding = getColumnBinding(e.col);
                    if (binding == grid_col_defs.setKanaName.binding) {
                        oldData = grid.getCellData(e.row, e.col);
                        console.log('old' + oldData);
                    }
                });
                grid.cellEditEnded.addHandler(function (s, e) {
                    var binding = getColumnBinding(e.col);
                    if (binding == grid_col_defs.setKanaName.binding) {
                        var newData = grid.getCellData(e.row, e.col);
                        if (!checkHankaku(newData)) {
                            layer.alert(window.message.E00004, {
                                title: '',
                                closeBtn: 0,
                                btn: ['はい']
                            });
                        	if(typeof oldData == 'undefined'){
                				grid.setCellData(e.row, e.col, '');
                			} else {
                				grid.setCellData(e.row, e.col, oldData);
                			}
                        } else {
                            grid.setCellData(e.row, e.col, zenkaku2Hankaku(newData));
                        }
                    }
                    oldData = undefined;
	        		newData = undefined;
                });
                grid.cellEditEnding.addHandler(function (sender, e) {
                    var binding = getColumnBinding(e.col);
                    if (binding == grid_col_defs.startTime.binding) {
                        oldData = grid.getCellData(e.row, e.col);
                        console.log('old' + oldData);
                    }
                });
                grid.cellEditEnded.addHandler(function (sender, e) {
                    var binding = getColumnBinding(e.col);
                    // 適用日時
                    if (binding == grid_col_defs.startTime.binding) {
                        var newData = grid.getCellData(e.row, e.col);
                        if (!checkDate(newData)) {
                            layer.alert('YYYY/MM/DD形式を入力してください。', {
                                title: '',
                                closeBtn: 0,
                                btn: ['はい']
                            });
                            grid.setCellData(e.row, e.col, '');
                        } else {
                            if (newData.length <= 10) {
                                grid.setCellData(e.row, e.col, newData + "　00:00");
                            }
                        }
                    }
                    oldData = undefined;
	        		newData = undefined;
                });
                // Gridのセルフォーマット用
                var gridItemFormatter = function (panel, r, c, cell) {
                    var grid = panel.grid;
                    var editRange = grid.editRange;

                    if (!(editRange && panel.cellType == wijmo.grid.CellType.Cell && editRange.row == r && editRange.col == c)) {
                        return;
                    }

                    // 入力制御 長さ制限と数字入力制限
                    var columnName = grid.columns[c].binding;
                    if (columnName == grid_col_defs.setQty.binding) {//qty
                        $(grid.activeEditor).numeric({
                            allowThouSep: true,			// Allow the thousands separator, default is the comma eg 12,000
                            allowDecSep: true,				// Allow the decimal separator, default is the fullstop eg 3.141
                            allowMinus: false, 				// Allow the - sign
                            maxDigits: 5					// The max number of digits
                        });

                    } else if (columnName == grid_col_defs.setMainPartsNo.binding) {// 削除理由
                        $(grid.activeEditor).alphanum({
                            maxLength: 24,
                            allow: '-()*.,。，'
                        });
                    } else if (columnName == grid_col_defs.setDispOrder.binding) {//セット表示順位
                        $(grid.activeEditor).numeric({
                            allowThouSep: true,			// Allow the thousands separator, default is the comma eg 12,000
                            allowDecSep: false,				// Allow the decimal separator, default is the fullstop eg 3.141
                            allowMinus: false, 				// Allow the - sign
                            maxDigits: 4					// The max number of digits
                        });
                    } else if (columnName == grid_col_defs.startTime.binding) {//適用日時
                        $(grid.activeEditor).alphanum({
                            allow: '/',
                            allowSpace: false,
                            allowNumeric: true,
                            allowLatin: false,
                            allowOtherCharSets : false,
                            maxLength: 10
                        });
                        oldData = $(grid.activeEditor).val();
                    }
                    // セット親品番
                    grid.prepareCellForEdit.addHandler(function (s, e) {
                        var binding = getColumnBinding(e.col);
                        var oldNum = $(grid.activeEditor).val();
                        if (binding == 'setMainPartsNo') {
                            $(grid.activeEditor).on('input', function () {
                                var newNum = $(grid.activeEditor).val();
                                if (newNum.length != newNum.replace(/[^((0-9)|(a-zA-Z))]/g, '').length) {
                                    $(grid.activeEditor).val(oldNum);
                                } else {
                                    oldNum = newNum;
                                }
                            })
                        }
                    });
                    // セット子品番
                    grid.prepareCellForEdit.addHandler(function (s, e) {
                        var binding = getColumnBinding(e.col);
                        var oldNum = $(grid.activeEditor).val();
                        if (binding == 'setSubPartsNo') {
                            $(grid.activeEditor).on('input', function () {
                                var newNum = $(grid.activeEditor).val();
                                if (newNum.length != newNum.replace(/[^((0-9)|(a-zA-Z))]/g, '').length) {
                                    $(grid.activeEditor).val(oldNum);
                                } else {
                                    oldNum = newNum;
                                }
                            })
                        }
                    });
                    // combox 生成
                    var colBinding = getColumnBinding(c);

                    // コンボボックスを作成する (FIXME:コンボボックスと関連の問題がいろいろがある)
                    if (colBinding == grid_col_defs.tbsPartsCode.binding) {  // 種別列
                        createGridComboBoxCell(r, c, cell, blCodeArray);
                    }
                    else if (colBinding == grid_col_defs.deleteFlg.binding) {  // 作区列
                        createGridComboBoxCell(r, c, cell, ['', '削除する']);
                    } else if (colBinding == grid_col_defs.prmSetDtlNo1.binding) {
                        createGridComboBoxCell(r, c, cell, selectCodeArray);
                    } else if (colBinding == grid_col_defs.startTime.binding) {
                        createGridInputCell(r, c, cell);
                    }

                };
                grid.itemFormatter = gridItemFormatter;

                //input length 制限
                grid.prepareCellForEdit.addHandler(function (s, e) {
                    var binding = getColumnBinding(e.col);
                    if (binding == grid_col_defs.setKanaName.binding || binding == grid_col_defs.setName.binding) {
                        $(grid.activeEditor).attr('maxlength', 60);
                    } else if (binding == grid_col_defs.setSpecialNote.binding ||
                        binding == grid_col_defs.primePartsSpecialNoteC.binding ||
                        binding == grid_col_defs.deleteReason.binding
                    ) {
                        $(grid.activeEditor).attr('maxlength', 80);
                    } else if (binding == grid_col_defs.startTime.binding) {
                    	$(grid.activeEditor).attr('maxlength',10);
                    } else if (binding == grid_col_defs.setMainPartsNo.binding ||
                        binding == grid_col_defs.setSubPartsNo.binding) {
                        $(grid.activeEditor).attr('maxlength', 24);
                    }
                });

//                inputMark
                if (this.mode == BLENUM.ModeEnum.New || this.mode == BLENUM.ModeEnum.Error) {
                    grid.formatItem.addHandler(function (s, e) {
                        if (e.panel.cellType == wijmo.grid.CellType.ColumnHeader
                            && (e.col == getColumnIndex(grid_col_defs.prmSetDtlNo1.binding)
                                || e.col == getColumnIndex(grid_col_defs.setMainPartsNo.binding)
                                || e.col == getColumnIndex(grid_col_defs.setDispOrder.binding)
                                || e.col == getColumnIndex(grid_col_defs.setKanaName.binding)
                                || e.col == getColumnIndex(grid_col_defs.setSubPartsNo.binding)
                                || e.col == getColumnIndex(grid_col_defs.startTime.binding)
                                || e.col == getColumnIndex(grid_col_defs.goodsMGroup.binding)
                                || e.col == getColumnIndex(grid_col_defs.tbsPartsCode.binding)
                                || e.col == getColumnIndex(grid_col_defs.setName.binding)
                                || e.col == getColumnIndex(grid_col_defs.setQty.binding)
                            )
                        ) {
                            $(e.cell).html(
                                function () {
                                    if ($(e.cell).html().indexOf("*") <= 0) {
                                        return $(e.cell).html()
                                            + "<span style='color:red'>*</span>";
                                    }
                                }
                            );

                        }
                    });
                }

                if (this.mode == BLENUM.ModeEnum.Error) {
                    grid.formatItem.addHandler(function (s, e) {
                        if (e.panel.cellType == wijmo.grid.CellType.ColumnHeader
                            && (e.col == getColumnIndex(grid_col_defs.check.binding))) {
                            $(e.cell).html(
                                ("<input type = 'checkbox' checked='checked' disabled = true>")
                            );
                        }
                    });
                }
                // セット選択モードの制御
                if (this.mode == BLENUM.ModeEnum.Update) {
                    grid.formatItem.addHandler(function (s, e) {
                        var isAllSelected = true;
                        var cv = grid.collectionView;
                        var count = cv.totalItemCount;
                        for (var i = 0; i < count; i++) {
                            isAllSelected = isAllSelected && cv.items[i].check;
                            var flag = cv.items[i].compareFlag;
                            var compareFlag = new Array();
                            if (flag != null) {
                                compareFlag = flag.split(",");
                                for (var j = 0; j < compareFlag.length; j++) {
                                    if (compareFlag[j] == "1" && e.panel.cellType == wijmo.grid.CellType.Cell && e.row == i && e.col == j) {
                                        e.cell.classList.add('row-checked');
                                    }
                                }
                            }
                            else {
                                if (e.panel.cellType == wijmo.grid.CellType.Cell && e.row == i) {
                                    e.cell.classList.add('row-checked');
                                }
                            }
                        }
                        if(count == 0){
                        	isAllSelected = false;	
                        }
                        if (e.panel.cellType == wijmo.grid.CellType.ColumnHeader
                            && (e.col == getColumnIndex(grid_col_defs.check.binding))) {
                            if (isAllSelected) {
                                $(e.cell).html(
                                    ("<input type = 'checkbox' checked='checked' onclick = 'checkAll(checked)' >")
                                );
                            } else {
                                $(e.cell).html(
                                    ("<input type = 'checkbox' onclick = 'checkAll(checked)' >")
                                );
                            }
                        }
                    });
                }


                var gotoEditingCellTimerId;
                var gotoEditingCell = function (row, col) {

                    grid.startEditing(true, row, col);

                    var activeEditor = grid.activeEditor;
                    if (grid.activeEditor) {
                        // NO.33 可視領域から可視領域以外に移動する場合、setTimeoutで利用必要
                        clearTimeout(gotoEditingCellTimerId);
                        gotoEditingCellTimerId = setTimeout(function () {
                            grid.startEditing(true, row, col);
                        }, 50);
                    } else {
                        //console.log("[ERR] grid.activeEditor:" + grid.activeEditor)
                    }
                }
                // tooltip show
                $.fn.hitTest = function (x, y) {
                    var bounds = this.offset();
                    bounds.right = bounds.left + this.outerWidth();
                    bounds.bottom = bounds.top + this.outerHeight();
                    return x > bounds.left && x < bounds.right && y > bounds.top && y < bounds.bottom;
                };

                grid.formatItem.addHandler(function (s, e) {
                    if (e.panel.cellType == wijmo.grid.CellType.Cell) {

                        var colBinding = getColumnBinding(e.col);


                        // if (isReadonlyCell(e.row, e.col) && getColumnBinding(e.col) != grid_col_defs.goodsMGroup.binding) {
                        if (isReadonlyCell(e.row, e.col)) {
                            // NO.16 入力不可セル背景色の制御
                            // $(e.cell).addClass('readonly');

                            if (mode == BLENUM.ModeEnum.New || mode == BLENUM.ModeEnum.Error) {
                                $(e.cell).addClass('c-cell');
                            }

                        } else {
                            // NO.104 tooltipの対応
                            var gridCellTooltipGetTitle = function () {
                                if (e.cell.offsetHeight < e.cell.scrollHeight || // overflow場合だけ表示
                                    e.cell.offsetWidth < e.cell.scrollWidth) {
                                    return e.cell.innerHTML;
                                } else {
                                    return '';
                                }
                            };
                            var cell = $(e.cell);
                            var gridCellTooltipMouseEnterHandler = function (event) {
                                if (e.cell.children.length == 0) {
                                    $.fn.tooltip.Constructor.prototype.getCalculatedOffset = function (placement, pos, actualWidth, actualHeight) {
                                        return {top: pos.top - 1, left: pos.left - 1};
                                    };
                                    cell.tooltip({
                                        container: 'body',
                                        trigger: 'manual',
                                        placement: 'auto right',
                                        title: gridCellTooltipGetTitle
                                    });
                                    cell.tooltip('show');

                                    $('.tooltip[role=tooltip]').on('mousemove mouseleave', function (event) {
                                        gridCellTooltipMouseMoveHandler(event);
                                    }).on('click', function (event) {
                                        var ht = grid.hitTest(event.pageX, event.pageY);
                                        gotoEditingCell(ht.row, ht.col);
                                    });
                                }
                            };
                            var gridCellTooltipMouseMoveHandler = function (event) {
                                // tooltipと関連のものを削除する
                                if (!cell.hitTest(event.pageX, event.pageY)) {
                                    cell.closest('.wj-cell').tooltip('destroy').removeAttr('title').removeAttr('data-original-title');
                                    $('.tooltip[role=tooltip]').remove();
                                }
                            };
                            cell.off('mouseenter', gridCellTooltipMouseEnterHandler);
                            cell.off('mouseleave', gridCellTooltipMouseMoveHandler);
                            cell.on('mouseenter', gridCellTooltipMouseEnterHandler);
                            cell.on('mouseleave', gridCellTooltipMouseMoveHandler);
                        }

                    }
                });

                var edtingCell = null;
                // cell beginedit 制御
                grid.beginningEdit.addHandler(function (s, e) {
                    if (e.panel.cellType == wijmo.grid.CellType.Cell && getColumnBinding(e.col) != grid_col_defs.check.binding) {
                        var items = grid.collectionView.items;
                        if (isReadonlyCell(e.row, e.col)) {
                            // NO.16 入力不可セルの制御
                            e.cancel = true;
                            return;
                        } else if (items[e.row]['applyCondition'] == '中') {
                            layer.alert("申請中のセットですので、編集できません。", {
                                title: '',
                                closeBtn: 0
                            });
                            e.cancel = true;
                            return;

                        } else if (getColumnBinding(e.col) == grid_col_defs.startTime.binding) {
// $(e.cell).on('click','input',{},function(){
// $(this).datetimepickerJp();
// })
// $(e.cell).find('input').datetimepickerJp();

                        } else {
                            // before edit getthe data
                            edtingCell = {row: e.row, col: e.col};
                            var cellData = grid.getCellData(e.row, e.col, false);
                            edtingCell.cellData = cellData;
                            // NO.104 編集可セルのtooltipを削除する
                            $('.tooltip[role=tooltip]').remove();
                        }
                    }
                });

                // rased when the input object was hidden
                // cell endedit 制御
                grid.cellEditEnded.addHandler(function (s, e) {
                    if (e.panel.cellType == wijmo.grid.CellType.Cell && getColumnBinding(e.col) != grid_col_defs.check.binding) {
                        // data before edit
                        var beforeEdit = edtingCell.cellData;
                        // data after edit
                        var afterEdit = grid.getCellData(e.row, e.col, false);
                        var items = grid.collectionView.items;
                        if (beforeEdit != afterEdit && (items[e.row]['hiddenArea'] == 1)) {
                            items[e.row]['hiddenArea'] = 2;
                            sessionStorage.setItem("confirmMessage", window.message.Q00001);
                        }
                        edtingCell = null;
                    }
                    if(!requestFlag)
	    			{
	    				check();
	    			}
                });


                // チェックボックスのセル上にクリック場合、選択チェックボックスも変更するのため。
                grid.hostElement.addEventListener('mousedown', function (e) {

                    var ht = grid.hitTest(e);
                    if (ht.cellType == wijmo.grid.CellType.Cell) {
                        if (grid.columns[ht.col].dataType == wijmo.DataType.Boolean) {
                            if (e.target instanceof HTMLDivElement) {
                                var chk = e.target.firstChild;
                                var edt = wijmo.tryCast(chk, HTMLInputElement);
                                if (edt != grid.activeEditor) {
                                    grid.startEditing(false, ht.row, ht.col);
                                    setTimeout(function () {
                                        edt = grid.activeEditor;
                                        if (edt && edt.type == 'checkbox') {
                                            edt.checked = !edt.checked;
                                            edt.focus();
                                            grid.finishEditing();
                                        }
                                    });
                                } else {
                                    grid.finishEditing();
                                }
                            }
                        }
                    }
                });


                grid.formatItem.addHandler(function (s, e) {
                    if (e.panel.cellType == wijmo.grid.CellType.Cell) {
                        // 必須項目チェック
                        if (getColumnBinding(e.col) == grid_col_defs.prmSetDtlNo1.binding
                            || getColumnBinding(e.col) == grid_col_defs.setMainPartsNo.binding
                            || getColumnBinding(e.col) == grid_col_defs.setDispOrder.binding
                            || getColumnBinding(e.col) == grid_col_defs.setSubPartsNo.binding
                            || getColumnBinding(e.col) == grid_col_defs.setKanaName.binding
                            || getColumnBinding(e.col) == grid_col_defs.goodsMGroup.binding
                            || getColumnBinding(e.col) == grid_col_defs.tbsPartsCode.binding
                            || getColumnBinding(e.col) == grid_col_defs.setName.binding
                            || getColumnBinding(e.col) == grid_col_defs.setQty.binding
                            || getColumnBinding(e.col) == grid_col_defs.startTime.binding
                        ) {
                            var value = grid.getCellData(e.row, e.col, false);
                            // red background
                            if (!value) {
                                e.cell.classList.add('null-cell');
                            }
                        }
                    }
                });


                // ガイドボタンの実現デモ
                grid.formatItem.addHandler(function (s, e) {
                    if (e.panel.cellType == wijmo.grid.CellType.Cell) {
                        var editRange = grid.editRange;
                        var row = e.row;
                        var col = e.col;
                        var isEdit = editRange && editRange.row == row && editRange.col == col; // 編集モード
                        var hiddenArea=grid.itemsSource.items[row].hiddenArea;

                        // classfyCd
                        if (getColumnBinding(e.col) == grid_col_defs.goodsMGroup.binding) {
                            wijmo.addClass(e.cell, 'guide-code-button-cell');
                            // ガイドボタンセルを作成
                            var button = createCodeGuideButton(row, col);
                            if (mode != BLENUM.ModeEnum.Update && mode != BLENUM.ModeEnum.Readonly) {
                                if (isEdit) {
                                    button.style.display = 'block';
                                    e.cell.appendChild(button);
                                } else {
                                    e.cell.appendChild(button);
                                }
                            }
                        }
                        // goodCd
                        if (getColumnBinding(e.col) == grid_col_defs.setMainPartsNo.binding
                            || getColumnBinding(e.col) == grid_col_defs.setSubPartsNo.binding
                        ) {
                            wijmo.addClass(e.cell, 'guide-code-button-cell');
                            // ガイドボタンセルを作成
                            var button = createGoodsGuideButton(row, col);
                            if (isEdit) {
                                button.style.display = 'block';
                            }
                            //選択モード and 詳細（参照） button hidden
                            var condition1=mode != BLENUM.ModeEnum.Update && mode != BLENUM.ModeEnum.Readonly;
                            //選択モード db data セット親品番 button hidden
                            var condition2=mode==BLENUM.ModeEnum.New&&hiddenArea!=BLENUM.JudgeEnum.Add&&col==getColumnIndex(grid_col_defs.setMainPartsNo.binding);
                            // //エラー編集   セット親品番 button hidden
                            // var condition3=mode==BLENUM.ModeEnum.Error&&col==getColumnIndex(grid_col_defs.setMainPartsNo.binding);
                            if(!condition1||condition2){
                                return;
                            }else{
                                e.cell.appendChild(button);
                            }
                        }


                    }
                });

                var guideButtonControl = function (button, row, col) {
                    var isApplying = false;
                    var items = grid.collectionView.items;
                    if (items[row].applyCondition == '中') {
                        isApplying = true;
                    }
                    if (mode == BLENUM.ModeEnum.Readonly || isApplying) {
                        button.disabled = 'disabled';
                    }

                }


                // コードガイドボタンを作成
                function createCodeGuideButton(row, col) {
                    var button = document.createElement('button');
                    wijmo.addClass(button, 'glyphicon glyphicon-star-empty');
                    var openGuide = function (e) {
                        try {
                            var accessURL = baseurl + '/setlist/goodsMGroup';
                            var actionType = 'POST';
                            var form = {
                                goodsMGroup: grid.getCellData(row, col)
                            };
                            $.blAjax({
                                url: accessURL,
                                type: actionType,
                                data: JSON.stringify(form),
                                success: function (data) {
                                    var guide = new BLUI.ClassifyCdGuide();
                                    guide.show('', true, {row: row, col: col});

                                },
                                error: function (data, httpStatus, errorHandler) {
                                    errorHandler(data, httpStatus);
                                }
                            });
                        } catch (e) {
                            sendClientErrorLog(e);
                        }
                        e.stopPropagation();
                    };
                    button.addEventListener('click', openGuide);
                    button.addEventListener('mousedown', openGuide);
                    guideButtonControl(button, row, col);
                    return button;
                }

                // goodsガイドボタンを作成
                function createGoodsGuideButton(row, col) {
                    var button = document.createElement('button');
                    wijmo.addClass(button, 'glyphicon glyphicon-star-empty');
                    var openGuide = function (e) {
                        try {
                            //品番
                            var accessURL = baseurl + '/setlist/setPartsNo';
                            var actionType = 'POST';
                            var form = {
                                setSubPartsNo: grid.getCellData(row, col),
                                setMainPartsNo: grid.getCellData(row, col)
                            };
                            $.blAjax({
                                url: accessURL,
                                type: actionType,
                                data: JSON.stringify(form),
                                success: function (data) {
                                    var guide = new BLUI.GoodsGuide();
                                    guide.show('', true, {row: row, col: col});

                                },
                                error: function (data, httpStatus, errorHandler) {
                                    errorHandler(data, httpStatus);
                                }
                            });
                        } catch (e) {
                            sendClientErrorLog(e);
                        }
                        e.stopPropagation();
                    };
                    button.addEventListener('click', openGuide);
                    button.addEventListener('mousedown', openGuide);
                    guideButtonControl(button, row, col);
                    return button;
                }


                // キーDownイベントの処理
                var gridKeydownHandler = function (event) {
                    if (grid.selection) {
                        var shift = event.shiftKey && !event.ctrlKey && !event.altKey && !event.metaKey;
                        var alt = !event.shiftKey && !event.ctrlKey && event.altKey && !event.metaKey;
                        var ctrl = !event.shiftKey && event.ctrlKey && !event.altKey && !event.metaKey;
                        var nospec = !event.shiftKey && !event.ctrlKey && !event.altKey && !event.metaKey;
                        if (nospec && event.which == 37) { // left
                            gotoNextInputCell('left');
                        } else if (nospec && event.which == 38) { // up
                            gotoNextInputCell('up');
                        } else if (nospec && event.which == 39) { // right
                            gotoNextInputCell('right');
                        } else if (nospec && event.which == 40) { // down
                            gotoNextInputCell('down');
                        } else if (shift && event.which == 13) { // shift
                            // +
                            // enter
                            gotoNextInputCell('prev');
                        } else if (shift && event.which == 9) { // shift +
                            // tab
                            gotoNextInputCell('prev');
                        } else if (nospec && event.which == 13) { // enter
                            gotoNextInputCell('next');
                        } else if (nospec && event.which == 9) { // tab
                            gotoNextInputCell('next');
                        } else if (ctrl && event.which == 65) { // ctrl + A
                            rowAddHandler();
                        } else if (ctrl && event.which == 73) { // ctrl + I
                            rowInsertHandler();
                        } else if (ctrl && event.which == 88) { // ctrl + X
                            rowCutHandler();
                        } else if (ctrl && event.which == 67) { // ctrl + C
                            rowCopyHandler();
                        } else if (ctrl && event.which == 86) { // ctrl + V
                            rowPasteHandler();
                        } else if (ctrl && event.which == 68) { // ctrl + D
                            rowDeleteHandler();
                        } else if (alt && event.which == 82) { // alt + R
                            rowClearHandler();
                        } else if (nospec && event.which == 36) { // home
                            setTimeout(function () {
                                grid.select(0, 0);
                            }, 100);
                        } else if (nospec && event.which == 35) { // end
                            grid.select(grid.rows.length - 1, 0);
                        } else if (ctrl && event.which == 36) { // ctrl +
                            // home
                            console.log('ctrl+home');
                            // FlexGridのデフォルトキーイベントと衝突がある、FlexGridのソースを改造必要かもしれない
                        } else if (ctrl && event.which == 35) { // ctrl +
                            // end
                            console.log('ctrl+end');
                            // FlexGridのデフォルトキーイベントと衝突がある、FlexGridのソースを改造必要かもしれない
                        }
                    }
                    event.preventDefault();
                    event.stopImmediatePropagation();
                };

                this.gridSetColorFormat();

            },
            // /
            // /セット商品 color format
            // /
            gridSetColorFormat: function () {
                try {
                    var grid = this.grid;
                    var old = grid.getCellData(0, 7, false);
                    var flag = 0;
                    grid.formatItem.addHandler(function (s, e) {
                        if (e.panel.cellType == wijmo.grid.CellType.Cell) {
                            var next = grid.getCellData(e.row, 7, false);
                            if (e.row == 0) {
                                old = grid.getCellData(0, 7, false);
                                flag = 0;
                            }
                            if (old != next) {
                                switch (flag) {
                                    case 0:
                                        e.cell.classList.add('b-cell');
                                        flag = 1;
                                        old = next;
                                        break;
                                    case 1:
                                        e.cell.classList.add('a-cell');
                                        flag = 0;
                                        old = next;
                                        break;
                                }
                            } else {
                                switch (flag) {
                                    case 0:
                                        e.cell.classList.add('a-cell');
                                        old = next;
                                        break;
                                    case 1:
                                        e.cell.classList.add('b-cell');
                                        old = next;
                                        break;
                                }
                            }
                        }
                    });

                } catch (e) {
                    sendClientErrorLog(e);
                }

            },


            // /
            // /row mapulation
            // /
            rowMapulation: function () {
                var flexGrid = this.grid;
                var activeRow = -1;
                flexGrid.selectionChanged.addHandler(function (s, e) {
                    var range = e.range;
                    activeRow = range.row;
                });

                $('.btn-insert').click(function () {
                    $(".page-info").show();
                    $('.btn-del').attr("disabled", false);
                    $('.btn-copy').attr("disabled", false);
                    $('.btn-paste').attr("disabled", false);
                    $('.btn-replaceGuide').attr("disabled", false);
                    $('.btn-detail').attr("disabled", false);
                    if(!requestFlag)
            		{
                      rowInsertHandler();
            		}
                });
                $('.btn-del').click(function () {
                	if(!requestFlag)
            		{
                      rowDeleteHandler();
            		}
                });
                $('.btn-copy').click(function () {
                      rowCopyHandler();
                });
                $('.btn-paste').click(function () {
                	if(!requestFlag)
            		{
                      rowPasteHandler();
            		}
                });

                var rowInsertHandler = function () {
                    var newRowData = {};
                    newRowData.hiddenArea = 0;
                    newRowData.applyCondition = "未";
                    newRowData.manageKbn = "追";
                    newRowData.check = false;
                    newRowData.checkFlg = "未";
                    newRowData.blEntryFlg = "未";
                    newRowData.errorFlg = "無";


                    var cv = flexGrid.collectionView;
                    if (activeRow >= 0) {
                        Array.prototype.splice.apply(flexGrid.itemsSource.sourceCollection, [activeRow + 1, 0].concat(newRowData));
                    } else {
                        cv.sourceCollection.push(newRowData);
                    }
                    cv.refresh();
                    check();
                    flexGrid.select(activeRow, 0);
                    sessionStorage.setItem("confirmMessage", window.message.Q00001);

                };

                var rowDeleteHandler = function () {
                    // 選択行を削除
                    var rows = flexGrid.selectedRows;
                    if (rows.length == 0) {
                        layer.alert("明細行が選択されていません。", {
                            title: '',
                            closeBtn: 0,
                            btn: ['はい']
                        });
                    }
                    for (var i = 0; i < rows.length; i++) {
                        if (rows[i].dataItem.hiddenArea == 0) {
                            flexGrid.collectionView.remove(rows[i].dataItem);
                            if (flexGrid.collectionView.items.length == 0) {
                                $(".page-info").hide();
                                $('.btn-del').attr("disabled", true);
                                $('.btn-copy').attr("disabled", true);
                                $('.btn-paste').attr("disabled", true);
                                $('.btn-replaceGuide').attr("disabled", true);
                                $('.btn-detail').attr("disabled", true);
                            }
                        }
                        else {
                            if (rows[i].dataItem.importKbn == "手入力") {
                                if (rows[i].dataItem.applyCondition == "未") {
                                    rows[i].dataItem.hiddenArea = 3;
                                    rows[i].cssClass = 'c-cell';
                                }
                                else if (rows[i].dataItem.applyCondition == "中") {
                                    layer.alert(message.E000121, {
                                        title: '',
                                        closeBtn: 0,
                                        btn: ['はい']
                                    });
                                }
                                else if (rows[i].dataItem.applyCondition == "済") {
                                    layer.alert(message.E000123, {
                                        title: '',
                                        closeBtn: 0,
                                        btn: ['はい']
                                    });
                                }
                                else if (rows[i].dataItem.applyCondition == "再") {
                                    layer.alert(message.E000123, {
                                        title: '',
                                        closeBtn: 0,
                                        btn: ['はい']
                                    });
                                }
                            }
                            else {
                                layer.alert(message.E000122, {
                                    title: '',
                                    closeBtn: 0,
                                    btn: ['はい']
                                });
                            }

                        }    
                    }
                    check();
                    sessionStorage.setItem("confirmMessage", window.message.Q00001);
                };

                var copiedRowData = null;
                var rowCopyHandler = function () {
                    var rows = flexGrid.selectedRows;
                    if (rows.length == 0) {
                        layer.alert("明細行が選択されていません。", {
                            title: '',
                            closeBtn: 0,
                            btn: ['はい']
                        });
                        return;
                    }
                    copiedRowData = [];
                    // set the new rowData
                    var formatCopiedData = function (rowData) {
                        var copiedData = $.extend(true, {}, rowData);
                        copiedData.hiddenArea = 0;
                        copiedData.applyCondition = '未';
                        copiedData.manageKbn = '追';
                        copiedData.insDtTime = '';
                        copiedData.updDtTime = '';
                        copiedData.checkFlg = '未';
                        copiedData.blEntryFlg = '未';
                        copiedData.errorFlg = '';
                        copiedData.errorDetail = '';
                        return copiedData;
                    }
                    for (var i = 0; i < rows.length; i++) {
                        copiedRowData.push(formatCopiedData(rows[i].dataItem));
                    }

                    if (copiedRowData.length > 0) {
                        $('.btn-paste').removeClass('disabled');
                    }
                };

                var rowPasteHandler = function () {
                    if (copiedRowData && copiedRowData.length > 0) {
                        var copiedRowDataCloned = $.extend(true, [], copiedRowData); // ここはディープコピー必要です
                        var selectRow = activeRow;

                        var rows = flexGrid.selectedRows;
                        for (var i = 0; i < rows.length; i++) {
                            if (rows[i].dataItem.hiddenArea == 0) {
                                // 行挿入
                                Array.prototype.splice.apply(flexGrid.itemsSource.sourceCollection, [activeRow, 1].concat(copiedRowDataCloned)); // insert
                                // rows
                            }
                            else {
                                layer.alert(message.E00016, {
                                    title: '',
                                    closeBtn: 0,
                                    btn: ['はい']
                                });
                                return;
                            }
                        }
                       
                        flexGrid.itemsSource.refresh();
                    }
                    check();
                    sessionStorage.setItem("confirmMessage", window.message.Q00001);
                };
            }


        };

        SetListOutPut.prototype = {
            // / <summary>
            // / show guide
            // / </summary>
            show: function () {
                layer.config({
                    extend: '../../css/checklistoutput/style.css'
                });
                var parentFrame = null;
                layer
                    .open({
                        type: 2,
                        title: false,
                        closeBtn: 0,
                        skin: 'layer-ext-skin',
                        shade: 0.1,
                        area: ['500px', '300px'],
                        content: [baseurl + '/checkList/checkListOutPut'],
                        btn: ['出力', 'キャンセル'],
                        sucess: function () {
                            parentFrame = $('#child_page')[0].contentWindow.document;
                        },

                        yes: function (index, layero) {
                            var childFrame = layero.find('iframe')[0].contentWindow.document;
                            var code = $("input", childFrame);
                            var selectCode = showCont(code);
                            var mode = query.mode ? parseInt(query.mode) : sessionStorage.getItem('setList') ? parseInt(sessionStorage.getItem('setList')) : BLENUM.ModeEnum.New;

                            var params = {};
                            params["fileType"] = selectCode;
                            //mode
                            params["mode"] = mode;
                            $.blAjax({
                                url: baseurl + '/setlist/makeFile',
                                data: params,
                                type: 'get',
                                dataType: 'json',
                                contentType: 'application/Json',
                                success: function (data) {
                                    var result = data["data"];
                                    if (result[0] == "success") {
                                        $.download(baseurl + '/checkList/downLoadFile', 'post', result[1], result[2]);
                                    } else {
                                        alert("data output error！");
                                    }
                                },
                                error: function (data, httpStatus, errorHandler) {
                                    // 『 TODO 20170217 wangnan get error message from database 』
                                    alert("Dataが存在しない")
                                }
                            });

                            layer.close(index);
                        },
                    });
            }

        }


        // 画面モード取得
        // コンボボックスを作成する (FIXME:コンボボックスと関連の問題がいろいろがある)
        var query = new URI(window.location.href).query(true);
        var mode = query.mode ? parseInt(query.mode) : sessionStorage.getItem('setList') ? parseInt(sessionStorage.getItem('setList')) : BLENUM.ModeEnum.New;
        try {
            var page = new SetCtlgPage(mode);
        } catch (e) {
            console.log(e);
        }

        // var nabigeshon=new BLUI.Nabigeshon();
        // nabigeshon.getData(1);
        // var nav = new BLUI.Nabigeshon(2);// navigation 商品情報管理>セット
    } catch (e) {
        sendClientErrorLog(e);
    }


});


//callback getNowPageNo
var getNowPageNo=function(){
    return window.pageNo||1;
}

function searchBeforeCheck(event) {
    var _this=event.data.thisPage;
    if(event.data.btnType=='search'){
        if (_this.isGridEdited()) {
            layer.confirm(window.message.Q00001, {
                icon: 3,
                title: '',
                closeBtn: 0,
                btn: ['はい', 'いいえ']
            }, function (index) {
                layer.close(index);
                _this.gridInit({data:event.data});
            })
        } else {
        	_this.gridInit({data:event.data});
        }
    }
}


function showCont(select) {
    for (var i = 1; i <= select.length; i++) {
        if (select[i - 1].checked == true) {
            return i;
        }
    }
}

function checkAll(checked) {
    var cv = grid.collectionView;
    var count = cv.totalItemCount;
    for (var i = 0; i < count; i++) {
        cv.items[i].check = checked;
    }
    cv.refresh();
}
