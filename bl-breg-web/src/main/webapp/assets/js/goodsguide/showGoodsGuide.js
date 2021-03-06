/**
 * @file 商品中分類コードガイド
 */
$(function () {
    'use strict';

    BLUI.GoodsGuide = function () {
    };

    BLUI.GoodsGuide.prototype = {
        show: function (inputName, isGrid, colRow) {
            var parentFrame = null;
            layer.inputName = inputName;
            layer.isGrid = isGrid;
            layer.colRow = colRow;
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
            		layer.inputName = undefined;
            	}
            });
        }
    }
});


