/**
 * @file システムメニュー
 */
$(function() {
	'use strict';
	/**
	 * @class
	 * @classdesc システムメニュートップページ
	 */
	
	var importErr = $("#miShinseiHandan").val();
	var importUserCheck = $("#honninInfo").val();
	var accountNameVal = $("#accountName").val();
	var messageHyoji = $("#messageHyoji").val();
	var messageOne = $("#messageOne").val();
	var companyName = $("#companyName").val();
	sessionStorage.setItem("importErr",importErr);
	sessionStorage.setItem("importUserCheck",importUserCheck);
	localStorage.setItem("accountName",accountNameVal);
	sessionStorage.setItem("messageHyoji",messageHyoji);
	sessionStorage.setItem("messageOne",messageOne);
	localStorage.setItem("companyName",companyName);
	
	// 商品一覧へ
	$("input[name=shohinnIchiran]").click(function() {
		sessionStorage.setItem("goodsList",BLENUM.ModeEnum.New);
		window.location.href = baseurl+"/goods/goods";
	})
	// セット一覧へ
	$("input[name=setIchiran]").click(function() {
		sessionStorage.setItem("setList",BLENUM.ModeEnum.New);
		window.location.href = baseurl+"/setlist/setlist";
	})
	// 結合一覧へ
	$("input[name=ketugouIchiran]").click(function() {
		sessionStorage.setItem("joinList",BLENUM.ModeEnum.New);
		window.location.href = baseurl+"/joinlist/joinList";
	})
	// 申請履歴へ
	$("input[name=shinseiRireki]").click(function() {
		sessionStorage.setItem("applyMode", 1);
		window.location.href = baseurl+"/applyhistory/apply_history";
	})
	

});