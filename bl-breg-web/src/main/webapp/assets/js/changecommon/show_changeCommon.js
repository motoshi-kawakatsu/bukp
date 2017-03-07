$(function() {
	'use strict';
	BLUI.ShowReplaceGuide = function() {
	};
	BLUI.ShowReplaceGuide.prototype = {
		show : function(mode) {
			layer.config({
				extend : '../../css/changecommon/style.css'
			});
			layer.mode = mode;
			layer.open({
				type :  2,
				title : false,
				skin : 'layer-ext-skin',
				shade : 0.1,
				area : [ '80%', '70%' ],
				closeBtn :  0,
				content : [ '../changecommon/changeCommon'],
				success : function(layero,index){
					layero.find("iframe")[0].contentWindow.document.mode = mode; 
				},
				end : function(){
					layer.mode = undefined;
				},
			});
		},
	};
});