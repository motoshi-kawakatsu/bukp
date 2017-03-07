$(function() {
	'use strict';
	var data1 = 0;
	var data2 = 0;
	var data3 = 0;
	if ("null" != document.getElementsByClassName("count")[0].value) {
		data1 = document.getElementsByClassName("count")[0].value;
	}
	if ("null" != document.getElementsByClassName("count")[1].value) {
		data2 = document.getElementsByClassName("count")[1].value;
	}
	if ("null" != document.getElementsByClassName("count")[2].value) {
		data3 = document.getElementsByClassName("count")[2].value;
	}
	var isNewCategory = sessionStorage.getItem("isNewCategory");
	if("Y" == isNewCategory){
		data1 = 0;
		data2 = 0;
		data3 = 0;
	}
	sessionStorage.removeItem("isNewCategory");
	var ApplyComplete = function() {
	};
	ApplyComplete.prototype = {
		init : function() {
			this.getData();
		},
		getData : function() {
			$(".tab-item").append("<td class='value'>" + data1 + "件" + "</td>");
			$(".tab-set").append("<td class='value'>" + data2 + "件" + "</td>");
			$(".tab-union")
					.append("<td class='value'>" + data3 + "件" + "</td>");
		}
	};
	$(".show-table").hide();
	$(".tab-item").hide();
	$(".tab-set").hide();
	$(".tab-union").hide();
	var page = new ApplyComplete()
	page.init();
	if ("0" != data1 || "0" != data2 || "0" != data3) {
		$(".show-table").slideToggle();
	}
	if ("0" != data1) {
		$(".tab-item").slideToggle();
	}
	if ("0" != data2) {
		$(".tab-set").slideToggle();
	}
	if ("0" != data3) {
		$(".tab-union").slideToggle();
	}
	var applyNo = document.getElementsByClassName("applyNo")[0].value;
	sessionStorage.setItem("applyNo", applyNo);
	sessionStorage.setItem("isSaved","Y");
});
