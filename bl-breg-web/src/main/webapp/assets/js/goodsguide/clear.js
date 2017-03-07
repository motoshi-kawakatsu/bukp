/**
 * @file 商品ガイド
 */
$(function () {
    'use strict';

    var BLCodeGuideComplete = function () {
    };

    BLCodeGuideComplete.prototype = {
        init: function () {
            // リストのクリックイベント
            $('.select-list').on('click', 'a', function () {
                $('a').removeClass('selected-item');
                $(this).addClass('selected-item');
            });
            
            $('.clear').on('click', function () {
                $('input').val('');
            });
            
            $('.close-btn').on('click', function () {
            	var index = parent.layer.getFrameIndex(window.name);
    			parent.layer.close(index);
            });
            
            $('.decide').on('click', function () {
                var nowithh = $('.selected-item .bl-nowithh').text();
                var kananm = $('.selected-item .bl-kananm').text();
                var name = $('.selected-item .bl-name').text();
                if('' == nowithh && '' == kananm && '' == name) {
                    layer.alert('明細行が選択されていません。',
                			{title : '',
						     closeBtn : 0,
						     btn : ['確定']}
                	);
                } else {
                	if(parent.layer.isGrid){
                		var grid = parent.document.getElementById("grid")["wj-Control"];
                		var col = parseInt(parent.layer.colRow.col);
                		var row = parseInt(parent.layer.colRow.row);
                        var value = grid.getCellData(row, col);
                        if(value!=(nowithh + '：' + name)){
                            var items = grid.collectionView.items;
                            if (items[row]['hiddenArea'] == 1) {
                                items[row]['hiddenArea'] = 2;
                            }
                        }
                		if(grid.columnHeaders.columns[col]._hdr == 'セット子品番'){
                			grid.setCellData(row, col, nowithh + '：' + name);
                			grid.setCellData(row, col+1, kananm);
                			grid.setCellData(row, col+2, name);
                		} else {
                			grid.setCellData(row, col, nowithh + '：' + name);
                		}
                		
                	} else {
                	    parent.$("input[name='"+parent.layer.inputName+"']").val(nowithh + '：' + name);
                	}
                	var index = parent.layer.getFrameIndex(window.name);
        			parent.layer.close(index);
                }
            });
            
            $('#code').focus();
            
            var data = $('.goodsGuide')[0].textContent;
            data = JSON.parse(data).goodsguidedtos;
		    if(data[0].message == ''){
				  $('.list-group-item').detach();
				  for(var i = 0; i < data.length; i++){
                    $('.select-list').append(
                        '<a class=\'list-group-item\'>'+'<li class=\'list-item\'><div class=\'item-label\'>' +
                        '<div class=\'list-body bl-nowithh text-center\'>' + data[i].primepartsnowithh + '</div>' +
                        '<div class=\'list-body bl-kananm text-center\'>' + data[i].primepartskananm + '</div>' +
                        '<div class=\'list-body bl-name text-center\'>' + data[i].primepartsname + '</div>' +
                        '</div></li></a>'
                        );
					  }} else {
						  layer.alert(data[0].message,
		                			{title : '',
								     closeBtn : 0,
								     btn : ['確定']}
		                	);
					  }
        }
    };

    var page = new BLCodeGuideComplete()
    page.init();
});
