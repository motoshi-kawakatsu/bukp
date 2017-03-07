/**
 * @file 商品中分類コードガイド
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
                var code = $('.selected-item .bl-code').text();
                var name = $('.selected-item .bl-name').text();
                if('' == code && '' == name) {
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
                		if(value!=(code + '：' + name)){
                			var items = grid.collectionView.items;
                			 if (items[row]['hiddenArea'] == 1) {
                                 items[row]['hiddenArea'] = 2;
                           }
                		}
                		grid.setCellData(row, col, code + '：' + name);
                	} else {
                	    parent.$("input[name='"+parent.layer.inputName+"']").val(code + '：' + name);
                	}
                	var index = parent.layer.getFrameIndex(window.name);
        			parent.layer.close(index);
                }
            });
            
            $('#code').focus();
            
            var data = $('.codeGuide')[0].textContent;
            
            data = JSON.parse(data).classifycodeguidedtos;
		    if(data[0].message == '') {
				$('.list-group-item').detach();
				for(var i = 0; i < data.length; i++){
                  $('.select-list').append(
                      '<a class=\'list-group-item\'>'+'<li class=\'list-item\'><div class=\'item-label\'>' +
                      '<div class=\'list-body bl-code text-center\'>' + data[i].goodsrategrpcode + '</div>' +
                      '<div class=\'list-body bl-name text-center\'>' + data[i].goodsrategrpname + '</div>' +
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
