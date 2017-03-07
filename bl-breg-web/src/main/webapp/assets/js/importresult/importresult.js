/**
 * @file チェック結果
 */
$(function() {
    'use strict';
    BLUI.CheckResult = function() {
        this.mode = mode;
    };
    BLUI.CheckResult.prototype = {
        init : function() {
            this.viewInit();
            $(".torikomi").focus();
            $(".back").on("click", $.proxy(this.onBackBtnClick, this));
            $(".import").on("click", $.proxy(this.onTorikomiBtnClick, this));
            $(".goods-con").on("click", $.proxy(this.onGoodsConClick, this));
            $(".set-con").on("click", $.proxy(this.onSetConClick, this));
            $(".union-con").on("click", $.proxy(this.onUnionConClick, this));
        },

        viewInit : function() {

            var importResultRedo = sessionStorage.getItem("importResultRedo");
            var importTypeMenu = sessionStorage.getItem("importModelMenu");
            var importMode;
            var importSchedule = '<li class="bl-diagram-item ">申請</li><li class="bl-diagram-item ">申請完了</li>';
            if ('0' == importTypeMenu) {
                importMode = 'インポート（一括申請）';
                $('.diagram-position').append(importSchedule);
                $('.diagram-position').removeClass('hide');
            } else if ('1' == importTypeMenu) {
                importMode = 'インポート';
                $('.diagram-position').removeClass('hide');
            } 
            if (importResultRedo == 1) {
                document.title = '再提出確認';
                $("h2").html('再提出確認');
                $(".import").html('申請')
            }
        },

        onBackBtnClick : function() {
            location.href = baseurl + "/fileselect/fileselect";
        },
        onTorikomiBtnClick : function() {
            if ($(".goods-error").val() != "0" || $(".set-error").val() != "0"
                    || $(".join-error").val() != "0") {
                var message = "商品･セット･結合のインポート結果にエラーデータが存在するので、再度確認ください。";
                layer.alert(message, {
                    title : false,
                    btn : [ 'はい' ]
                });
            } else {
                layer.confirm('登録しますか？', {
                    title : false,
                    btn : [ 'はい', 'いいえ' ]
                }, saveaction);
            }
        },
        onGoodsConClick : function() {
            sessionStorage.setItem("goodsList", BLENUM.ModeEnum.Error)
            location.href = baseurl + "/goods/goods";
        },
        onSetConClick : function() {
            sessionStorage.setItem('setList', BLENUM.ModeEnum.Error)
            location.href = baseurl + "/setlist/setlist";
        },
        onUnionConClick : function() {
            sessionStorage.setItem('joinList', BLENUM.ModeEnum.Error)
            location.href = baseurl + "/joinlist/joinList";
        },
    };

    try {
        var importResultRedo = sessionStorage.getItem("importResultRedo");
        if (!importResultRedo) {
            importResultRedo = 0;
        }
        var accessURL = baseurl + "/importresult/reinit";
        var form = {
            importType : importResultRedo,
        };
        $.blAjax({
            modalStyle : 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
            isTranFlag : false,// true:他画面へ遷移、false:他画面へ遷移しない
            url : accessURL,
            type : "POST",
            data : JSON.stringify(form),
            success : function(data) {

                $(".tdGoodsTotal").html(data.goodsTotal + "&nbsp;件");
                $(".tdGoodsOk").html(data.goodsOkCount + "&nbsp;件");
                $(".tdGoodsError").html(data.goodsErrorCount + "&nbsp;件");
                $(".tdSetTotal").html(data.setTotal + "&nbsp;件");
                $(".tdSetOk").html(data.setOkCount + "&nbsp;件");
                $(".tdSetError").html(data.setErrorCount + "&nbsp;件");
                $(".tdJoinTotal").html(data.joinTotal + "&nbsp;件");
                $(".tdJoinOk").html(data.joinOkCount + "&nbsp;件");
                $(".tdErrorCount").html(data.joinErrorCount + "&nbsp;件");
                $(".goods-error").val(data.goodsErrorCount);
                $(".set-error").val(data.setErrorCount);
                $(".join-error").val(data.joinErrorCount);
            },
            error : function(data, httpStatus, errorHandler) {
                errorHandler(data, httpStatus);
            }
        });
    } catch (e) {
        sendClientErrorLog(e);
    }

    var saveaction = function(index) {
        try {
            layer.close(index);
            var accessURL = baseurl + "/importresult/importresult";
            $
                    .blAjax({
                        allowModal : true,// true:二重送信制御が必要、false:二重送信制御が必要しない
                        modalStyle : 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示
                        isTranFlag : true,// true:他画面へ遷移、false:他画面へ遷移しない
                        url : accessURL,
                        type : "POST",
                        success : function(data) {

                            $(".tdGoodsTotal")
                                    .html(data.goodsTotal + "&nbsp;件");
                            $(".tdGoodsOk").html(data.goodsOkCount + "&nbsp;件");
                            $(".tdGoodsError").html(
                                    data.goodsErrorCount + "&nbsp;件");
                            $(".tdSetTotal").html(data.setTotal + "&nbsp;件");
                            $(".tdSetOk").html(data.setOkCount + "&nbsp;件");
                            $(".tdSetError").html(
                                    data.setErrorCount + "&nbsp;件");
                            $(".tdJoinTotal").html(data.joinTotal + "&nbsp;件");
                            $(".tdJoinOk").html(data.joinOkCount + "&nbsp;件");
                            $(".tdErrorCount").html(
                                    data.joinErrorCount + "&nbsp;件");
                            if (data.goodsErrorCount != 0
                                    || data.setErrorCount != 0
                                    || data.joinErrorCount != 0) {

                                var message = "商品･セット･結合のインポート結果にエラーデータが存在するので、再度確認ください。";
                                layer.alert(message, {
                                    title : false,
                                    btn : [ 'はい' ]
                                });
                            } else {
                                var importResultRedo = sessionStorage
                                        .getItem("importResultRedo");
                                if (importResultRedo == 1) {
                                	sessionStorage.removeItem("importResultRedo");
                                    location.href = baseurl
                                            + '/applycommon/applyCommon';
                                } else {
                                    location.href = baseurl
                                            + "/readresult/readresult";
                                }
                                if (data.goodsUn != 0 || data.setUn != 0
                                        || data.joinUn != 0) {
                                    sessionStorage
                                            .setItem("importErr", "false");
                                } else {
                                    sessionStorage.setItem("importErr", "true");
                                }
                                sessionStorage.setItem("importUserCheck",
                                        "true");
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

    // 画面モード取得
    var query = new URI(window.location.href).query(true);
    var mode = parseInt(query.mode);
    var nabigeshon = new BLUI.Nabigeshon();
    var importType = sessionStorage.getItem("importModelMenu");
    if (importType == "0") {
        nabigeshon.getData(18);
    } else if (importType == "1") {
        nabigeshon.getData(21);
    } else if (sessionStorage.getItem("importResultRedo") == 1) {
        nabigeshon.getData(32);
    }
    var check = new BLUI.CheckResult(mode);
    check.init();
});