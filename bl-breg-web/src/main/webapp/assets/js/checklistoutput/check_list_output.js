/**
 * @file 商品中分類コードGuide
 */
$(function() {
    'use strict';

    BLUI.CheckListOutPut = function() {
    };

    BLUI.CheckListOutPut.prototype = {
        // / <summary>
        // / show guide
        // / </summary>
        show : function(showItem) {
            layer.config({
                extend : '../../css/checklistoutput/style.css'
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
                            var viewGridData = new Array()
                            params["showItem"] = showItem;
                            params["fileType"] = selectCode;
                            params["mode"] = sessionStorage
                                    .getItem("checkList");
                            if (showItem == 1 && parseInt(params["mode"]) != BLENUM.MenuEnum.TopMenu) {
                                var goodgrid = document.getElementById("item")["wj-Control"];
                                for (var i = 0; i < goodgrid.rows.length; i++) {
                                	viewGridData[i] = goodgrid.rows[i].dataItem;
                            	}
                                var viewData = JSON.stringify(viewGridData);
                                jQuery(document).ready(function($){
                                    var url = baseurl + '/checkList/makeViewFile';
                                    var data =  {"showItem":showItem,"viewData":viewData, "fileType":selectCode};
                                    $.post(url,data,function(result){
                                    	var result = result["data"];
                                        if (result[0] == "success") {
                                            $
                                                    .download(
                                                            baseurl
                                                                    + '/checkList/downLoadFile',
                                                            'post',
                                                            result[1],
                                                            result[2]);
                                        }
                                    });
                                });
                                layer.close(index);
                                return;
                            }
                            if (showItem == 2 && parseInt(params["mode"]) != BLENUM.MenuEnum.TopMenu) {
                                var setgrid = document.getElementById("set")["wj-Control"];
                                for (var i = 0; i < setgrid.rows.length; i++) {
                                	viewGridData[i] = setgrid.rows[i].dataItem;
                            	}
                                var viewData = JSON.stringify(viewGridData);
                                jQuery(document).ready(function($){
                                    var url = baseurl + '/checkList/makeViewFile';
                                    var data =  {"showItem":showItem,"viewData":viewData, "fileType":selectCode};
                                    $.post(url,data,function(result){
                                    	var result = result["data"];
                                        if (result[0] == "success") {
                                            $
                                                    .download(
                                                            baseurl
                                                                    + '/checkList/downLoadFile',
                                                            'post',
                                                            result[1],
                                                            result[2]);
                                        }
                                    });
                                });
                                layer.close(index);
                                return;
                            }
                            if (showItem == 3 && parseInt(params["mode"]) != BLENUM.MenuEnum.TopMenu) {
                                var joingrid = document.getElementById("union")["wj-Control"];
                                for (var i = 0; i < joingrid.rows.length; i++) {
                                	viewGridData[i] = joingrid.rows[i].dataItem;
                            	}
                                var viewData = JSON.stringify(viewGridData);
                                jQuery(document).ready(function($){
                                    var url = baseurl + '/checkList/makeViewFile';
                                    var data =  {"showItem":showItem,"viewData":viewData, "fileType":selectCode};
                                    $.post(url,data,function(result){
                                    	var result = result["data"];
                                        if (result[0] == "success") {
                                            $
                                                    .download(
                                                            baseurl
                                                                    + '/checkList/downLoadFile',
                                                            'post',
                                                            result[1],
                                                            result[2]);
                                        }
                                    });
                                });
                                layer.close(index);
                                return;
                            }
                            params["flg"] = false;
                            $
                                    .blAjax({
                                        url : baseurl + '/checkList/makeFile',
                                        data : params,
                                        type : 'get',
                                        dataType : 'json',
                                        contentType : 'application/Json',
                                        success : function(data) {
                                            var result = data["data"];
                                            if (result[0] == "success") {
                                                $
                                                        .download(
                                                                baseurl
                                                                        + '/checkList/downLoadFile',
                                                                'post',
                                                                result[1],
                                                                result[2]);
                                            } else {
                                                alert("データ書き出し失敗！");
                                            }
                                        },
                                        error : function(data, httpStatus,
                                                errorHandler) {
                                        	errorHandler(data, httpStatus);
                                        }
                                    });
                            layer.close(index);
                        },
                    });
        },
    }
});

function showCont(select) {
    for (var i = 1; i <= select.length; i++) {
        if (select[i - 1].checked == true) {
            return i;
        }
    }
}
