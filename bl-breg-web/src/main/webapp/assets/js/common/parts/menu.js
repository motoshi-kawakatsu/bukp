$(function () {
	'use strict';
	BLUI.Nabigeshon = function() {
		
	};
    BLUI.Nabigeshon.prototype = {
    	validate: function(no) {
		    var node = null;
		    $.each(pages, function(index, item) {
		        if (item.No === no) { 
		            node = item;
		        }
		    });
		    return node;
		},
		
		action:function(no) {
		    var nodes = [];
		    var nav = null;
		    $.each(nabigeshon, function(index, item) {
		        if (item.No === no) {
		            nav = item;
		        }
		    });
		    var _this = this;
		    if (nav) {
		        $.each(nav.nabigeshon, function(index,item) {
		            nodes.push(_this.validate(item));
		        });
		    }
		    return nodes;
		},
		
        getData: function(no) {
        	var data = this.action(no);
            var navHtml = "<ol class='breadcrumb'>";
        	var importErr = sessionStorage.getItem("importErr");
        	var importUserCheck = sessionStorage.getItem("importUserCheck");
        	var messageHyoji = sessionStorage.getItem("messageHyoji");
        	var messageOne = sessionStorage.getItem("messageOne");
            $.each(data, function(index,item) {
            	var href = item.url;
            	if (no == 19) {
            		if (item.mode == 1) {
            			navHtml += "<li><a href="+href+">"+item.pageName +"</a></li>";
            		}else if(item.mode == 2){
            			navHtml += "<li><a href="+"#"+" onclick='shinseiTorikomiOne(\""+href+"\")'>"+item.pageName +"</a></li>";
            		}else if (item.mode == 3) {
            			navHtml += "<li><a href="+"#"+" onclick='shinseiTorikomiTwo(\""+href+"\")'>"+item.pageName +"</a></li>";
            		}else if (item.mode == 4){
            			navHtml += "<li>"+item.pageName +"</a></li>";
            		}
            	}
            	else if(no == 24 || no == 30 || no == 35) {
            		if (item.mode == 1) {
            			navHtml += "<li><a href="+href+">"+item.pageName +"</a></li>";
            		}else if(item.mode == 2){
            			navHtml += "<li><a href="+"#"+" onclick='shinseiTorikomiOne(\""+href+"\")'>"+item.pageName +"</a></li>";
            		}else if (item.mode == 3) {
            			navHtml += "<li><a href="+"#"+" onclick='shinseiTorikomiTwo(\""+href+"\")'>"+item.pageName +"</a></li>";
            		}else if (item.mode == 4) {
            			navHtml += "<li><a href="+href+">"+item.pageName +"</a></li>";
            		}else {
            			navHtml += "<li>"+item.pageName +"</a></li>";
            		}
            	}
            	//申請（取込）の場合
            	else if (no == 20) {
            		if (item.mode == 1) {
            			navHtml += "<li><a href="+href+">"+item.pageName +"</a></li>";
            		}else if(item.mode == 2){
            			navHtml += "<li><a href="+"#"+" onclick='shinseiTorikomiOne(\""+href+"\")'>"+item.pageName +"</a></li>";
            		}else if (item.mode == 3) {
            			navHtml += "<li><a href="+"#"+" onclick='shinseiTorikomiTwo(\""+href+"\")'>"+item.pageName +"</a></li>";
            		}else if (item.mode == 4) {
            			navHtml += "<li><a href="+"#"+" onclick='shinseiTorikomiThree(\""+href+"\")'>"+item.pageName +"</a></li>";
            		}else {
            			navHtml += "<li>"+item.pageName +"</a></li>";
            		}
            		
            	}
            	//インポート>インポート結果>取込完了の場合
            	else if (no == 22) {
            		//インポートの場合
            		if (item.params) {
            			//未申請がある
            			if (importErr == "false") {
        					navHtml += "<li><a href="+"#"+" class='messageTwo'>"+item.pageName +"</a></li>";
            			//未申請がない
            			}else{
            				navHtml += "<li><a href="+href+">"+item.pageName +"</a></li>";
            			}
            		}else if(index < data.length-1) {
    	                    navHtml += "<li><a href="+href+">"+item.pageName +"</a></li>";
    	            } else {
    	                    navHtml += "<li>"+item.pageName +"</a></li>";
    	            }
            	}
            	else if(index < data.length-1) {
	                    navHtml += "<li><a href="+"#"+" onclick='aClick(\""+href+"\")'>"+item.pageName +"</a></li>";
	                } else {
	                    navHtml += "<li>"+item.pageName +"</a></li>";
	                }
            	});
            navHtml += "</ol>";
            $("nav").after(navHtml);
            
           
            $(".messageTwo").click(function (){
    			layer.alert(messageHyoji,{
    				title : '',
    				closeBtn : 0,
    				btn : ['はい']
    			});
            })
            $(".messageOne").click(function (){
    			layer.alert(messageOne,{
    				title : '',
    				closeBtn : 0,
    				btn : ['はい']
    			});
            })
        },
    }
   
    var menuPage = new BLUI.Nabigeshon();
    menuPage.getData();

});

function shinseiTorikomiOne(href) {
	var importErr = sessionStorage.getItem("importErr");
	if (importErr == "false") {
		window.location.href = baseurl + "/readresult/readresult";
	}else{
		sessionStorage.setItem("importModelMenu", "0");
		window.location.href = href;
	}
}

function shinseiTorikomiTwo(href) {
	var importErr = sessionStorage.getItem("importErr");
	var messageOne = sessionStorage.getItem("messageOne");
	if (importErr == "true") {
		window.location.href = href;
	}else{
		layer.alert(messageOne,{
			title : '',
			closeBtn : 0,
			btn : ['はい']
		});
	}
}

function shinseiTorikomiThree(href) {
	var applyInformation = sessionStorage.getItem("applyInformation");
	if (applyInformation == "F") {
		window.location.href = href;
	}
}

function aClick(href) {
	var message = sessionStorage.getItem("confirmMessage");
	var toptrans = function(index) {
		layer.close(index);
		window.location.href = href;
		sessionStorage.setItem("confirmMessage", null);
	}
	if (message != "null" && message != null) {
		layer.confirm(message, {
			icon : 3,
			title : '',
			closeBtn : 0,
			btn : ['はい','いいえ']
		}, toptrans)
	}else{
		window.location.href = href;
	}
}
    var pages = [
        {No:1, pageName:"商品情報管理", url: baseurl+"/topmenu/topMenu",mode:1},
        {No:2, pageName:"申請処理", url: baseurl+"/topmenu/topMenu"},
        {No:3, pageName:"設定", url: baseurl+"/topmenu/topMenu"},
        {No:4, pageName:"商品一覧", url: baseurl+"/goods/goods"},
        {No:5, pageName:"セット一覧", url:  baseurl+"/setlist/setlist"},
        {No:6, pageName:"結合一覧", url:  baseurl+"/joinlist/joinList"},
        {No:7, pageName:"インポート（一括申請）", url:  baseurl+"/fileselect/fileselect",params:1,mode:2},
        {No:8, pageName:"インポート", url:  baseurl+"/fileselect/fileselect",params:2},
        {No:9, pageName:"チェックリスト", url:  baseurl+"/checkList/checkList"},
        {No:10, pageName:"申請(一般)", url:  baseurl+"/applycommon/applyCommon"},
        {No:11, pageName:"申請(新規品目)", url:  baseurl+"/applynewcategory/applynewcategory"},
        {No:12, pageName:"申請履歴", url:  baseurl+"/applydetail/applyDetail"},
        {No:13, pageName:"会社情報", url:  baseurl+"/employee/companysetting"},
        {No:14, pageName:"担当者情報", url:  baseurl+"/usersetting/usersetting"},
        {No:15, pageName:"商品詳細", url:  baseurl+"/goodsdetail/goodsDetail"},
        {No:16, pageName:"セット詳細", url:  baseurl+"/setdetail/setdetail"},
        {No:17, pageName:"結合詳細", url:  baseurl+"/joindetail/joinDetail"},
        {No:18, pageName:"インポート結果", url:  baseurl+"/importresult/importresult",mode:3},
        {No:19, pageName:"取込完了", url:  baseurl+"/readresult/readresult",mode:4},
        {No:20, pageName:"申請詳細", url:  baseurl+"/applydetail/applyDetail"},
        {No:21, pageName:"申請履歴一覧", url:  baseurl+"/applydetail/applyDetail"},
        {No:22, pageName:"商品一覧（エラー修正）", url:  baseurl+"/goods/goods"},
        {No:23, pageName:"商品一覧（選択）", url:  baseurl+"/goods/goods"},
        {No:24, pageName:"申請（取込）", url:  baseurl+"/applycommon/applyCommon"},
        {No:25, pageName:"結合一覧（エラー修正）", url:  baseurl+"/joinlist/joinList"},
        {No:26, pageName:"結合一覧（選択）", url:  baseurl+"/joinlist/joinList"},
        {No:27, pageName:"結合一覧（参照）", url:  baseurl+"/joinlist/joinList"},
		{No:28, pageName:"セット一覧（エラー修正）", url:  baseurl+"/setlist/setlist"},
		{No:29, pageName:"セット一覧（選択）", url:  baseurl+"/setlist/setlist"},
		{No:30, pageName:"セット一覧（参照）", url:  baseurl+"/setlist/setlist"},
		{No:31, pageName:"再提出確認", url:  baseurl+"/importresult/importresult"},
		{No:32, pageName:"トップページ", url: baseurl+"/topmenu/topMenu"},
		{No:33, pageName:"商品一覧（参照）", url:  baseurl+"/setlist/setlist"},

    ]

    var nabigeshon = [
        {No:1, nabigeshon:[1,4]},//商品情報管理>商品一覧
        {No:2, nabigeshon:[1,5]},//商品情報管理>セット一覧
        {No:3, nabigeshon:[1,6]},//商品情報管理>結合一覧
        {No:4, nabigeshon:[1,7]},//商品情報管理>インポート（一括申請）
        {No:5, nabigeshon:[1,8]},//商品情報管理>インポート
        {No:6, nabigeshon:[2,9]},//申請処理>チェックリスト
        {No:7, nabigeshon:[2,10]},//申請処理>申請(一般)
        {No:8, nabigeshon:[2,11]},//申請処理>申請(新規品目)
        {No:9, nabigeshon:[32,12]},//トップページ>申請履歴
        {No:10, nabigeshon:[3,13]},//設定>会社情報
        {No:11, nabigeshon:[3,14]},//設定>担当者情報
        {No:12, nabigeshon:[1,4,9]},//商品情報管理>商品一覧>チェックリスト
        {No:13, nabigeshon:[1,4,15]},//商品情報管理>商品一覧>商品詳細
        {No:14, nabigeshon:[1,5,9]},//商品情報管理>セット一覧>チェックリスト
        {No:15, nabigeshon:[1,5,16]},//商品情報管理>セット一覧>セット詳細
        {No:16, nabigeshon:[1,6,9]},//商品情報管理>結合一覧>チェックリスト
        {No:17, nabigeshon:[1,6,17]},//商品情報管理>結合一覧>結合詳細
        {No:18, nabigeshon:[1,7,18]},//商品情報管理>インポート（一括申請）>インポート結果
        {No:19, nabigeshon:[1,7,18,19]},//商品情報管理>インポート（一括申請）>インポート結果>取込完了
        {No:20, nabigeshon:[1,7,18,19,24]},//商品情報管理>インポート（一括申請）>インポート結果>取込完了>申請（取込）
        {No:21, nabigeshon:[1,8,18]},//商品情報管理>インポート>インポート結果
        {No:22, nabigeshon:[1,8,18,19]},//商品情報管理>インポート>インポート結果>取込完了
        {No:23, nabigeshon:[1,7,18,22]},//商品情報管理>インポート（一括申請）>インポート結果>商品一覧（エラー修正）
        {No:24, nabigeshon:[1,7,18,19,33]},//商品情報管理>インポート（一括申請）>インポート結果>取込完了>商品一覧（参照モード）
        {No:25, nabigeshon:[2,10,23]},//申請処理>申請(一般)>商品一覧（選択）
        {No:26, nabigeshon:[1,7,18,25]},//商品情報管理>インポート（一括申請）>インポート結果>結合一覧（エラー修正）
        {No:27, nabigeshon:[1,8,18,19,27]},//商品情報管理>インポート>インポート結果>取込完了>結合一覧（参照モード）
        {No:28, nabigeshon:[2,10,26]},//申請処理>申請(一般)>結合一覧（選択）
		{No:29, nabigeshon:[1,7,18,28]},//商品情報管理>インポート（一括申請）>インポート結果>セット一覧（エラー修正）
		{No:30, nabigeshon:[1,7,18,19,30]},//商品情報管理>インポート（一括申請）>インポート結果>取込完了>セット一覧（参照モード）
		{No:31, nabigeshon:[2,10,29]},//申請処理>申請(一般)>セット一覧（選択）
        {No:32, nabigeshon:[2,12,31]},//申請処理>申請履歴>再提出確認
		{No:33, nabigeshon:[1,8,18,19,30]},//商品情報管理>インポート>インポート結果>取込完了>セット一覧（参照モード）
		{No:34, nabigeshon:[1,8,18,28]},//商品情報管理>インポート>インポート結果>セット一覧（エラー修正）
		{No:35, nabigeshon:[1,7,18,19,27]},//商品情報管理>インポート（一括申請）>インポート結果>取込完了>結合一覧（参照モード）
		{No:36, nabigeshon:[1,8,18,25]},//商品情報管理>インポート>インポート結果>結合一覧（エラー修正）
		{No:37, nabigeshon:[1,8,18,22]},//商品情報管理>インポート>インポート結果>商品一覧（エラー修正）
		{No:38, nabigeshon:[1,8,18,19,33]},//商品情報管理>インポート>インポート結果>取込完了>商品一覧（参照モード）
    ]