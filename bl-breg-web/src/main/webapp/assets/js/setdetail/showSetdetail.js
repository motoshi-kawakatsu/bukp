$(function() {
	'use strict';

	BLUI.Setdetail = function() {
	};

	BLUI.Setdetail.prototype = {
		show : function(activeRow) {
			layer.config({
				extend : '../../css/classifyCodeGuide/layerButton.css'
			});
			var parentFrame = null;
			layer
					.open({
						type : 2,
						title : false,
						closeBtn : 0,
						skin : 'layer-ext-skin',
						shade : 0.1,
						area : [ '100%', '80%' ],
						sucess : function() {
							parentFrame = $('#child_page')[0].contentWindow.document;
						},
						content : [ baseurl + "/setdetail/setdetail" ]
					});
		}
	}
});
