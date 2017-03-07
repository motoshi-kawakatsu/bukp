
$(function () {
    'use strict';

    BLUI.goodsdetail = function () {
    };

    BLUI.goodsdetail.prototype = {
        show: function (mode) {
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
                content: [baseurl+"/goodsdetail/goodsDetail?mode="+mode],
            });
        }
    }
});


