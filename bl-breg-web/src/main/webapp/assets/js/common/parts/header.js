$(function() {
	'use strict';
	/**
	 * システムヘッダ
	 */
	BLUI.SystemHeader = function() {

		var accountNameVal = localStorage.getItem("accountName");
		var messageOne = sessionStorage.getItem("messageOne");
		var companyName = localStorage.getItem("companyName");
		sessionStorage.setItem("applyInformation", "T");
		//インポート（一括申請）をクリック場合
		$(".importIkatsu").click(function() {
			var importErr = sessionStorage.getItem("importErr");
			var importUserCheck = sessionStorage.getItem("importUserCheck");
			var messageHyoji = sessionStorage.getItem("messageHyoji");
			sessionStorage.setItem("importModelMenu", "0");
			var message = sessionStorage.getItem("confirmMessage");
			var toptranslate = function(index) {
				layer.close(index);
				// 未申請あるの場合の場合
				if (importErr == "false") {
					//本人の場合
					if (importUserCheck == "true") {
						window.location.href = baseurl + "/readresult/readresult";
					// 本人以外の場合
					}else {
						layer.alert(messageHyoji,{
							title : '',
							closeBtn : 0,
							btn : ['はい']
						});
					}
				// 未申請がないの場合の場合
				}else{
					window.location.href = baseurl + "/fileselect/fileselect";
				}
				sessionStorage.setItem("confirmMessage", null);
			}
			//画面は未保存の変更がない場合
			if (message == "null" || message == null) {
				// 未申請あるの場合の場合
				if (importErr == "false") {
					//本人の場合
					if (importUserCheck == "true") {
						
						window.location.href = baseurl + "/readresult/readresult";
					// 本人以外の場合
					}else {
						layer.alert(messageHyoji,{
							title : '',
							closeBtn : 0,
							btn : ['はい']
						});
					}
				// 未申請がないの場合の場合
				}else{
					window.location.href = baseurl + "/fileselect/fileselect";
				}
			//画面は未保存の変更がある場合	
			}else{
				layer.confirm(message, {
					icon : 3,
					title : '',
					closeBtn : 0,
					btn : ['はい','いいえ']
				}, toptranslate)
			}
		})
		//インポートをクリック場合
		$(".importPage").click(function() {
			var importErr = sessionStorage.getItem("importErr");
			var importUserCheck = sessionStorage.getItem("importUserCheck");
			var messageHyoji = sessionStorage.getItem("messageHyoji");
			sessionStorage.setItem("importModelMenu", "1");
			var message = sessionStorage.getItem("confirmMessage");
			var toptranslate = function(index) {
				layer.close(index);
				// 未申請あるの場合の場合
				if (importErr == "false") {
					//本人の場合
					if (importUserCheck == "true") {
						layer.alert(messageHyoji,{
							title : '',
							closeBtn : 0,
							btn : ['はい']
						});
					// 本人以外の場合
					}else {
						layer.alert(messageHyoji,{
							title : '',
							closeBtn : 0,
							btn : ['はい']
						});
					}
				// 未申請がないの場合の場合
				}else{
					window.location.href = baseurl + "/fileselect/fileselect";
				}
				sessionStorage.setItem("confirmMessage", null);
			}
			//画面は未保存の変更がない場合
			if (message == "null" || message == null) {
				// 未申請あるの場合の場合
				if (importErr == "false") {
					//本人の場合
					if (importUserCheck == "true") {
						layer.alert(messageHyoji,{
							title : '',
							closeBtn : 0,
							btn : ['はい']
						});
					// 本人以外の場合
					}else {
						layer.alert(messageHyoji,{
						title : '',
						closeBtn : 0,
						btn : ['はい']
						});
					}
				// 未申請がないの場合の場合
				}else{
					window.location.href = baseurl + "/fileselect/fileselect";
				}
			//画面は未保存の変更がある場合
			}else{
				layer.confirm(message, {
					icon : 3,
					title : '',
					closeBtn : 0,
					btn : ['はい','いいえ']
				}, toptranslate)
			}
		})
		
		function getcommondata(urlId, className, key, mode) {
			var importErr = sessionStorage.getItem("importErr");
			var importModelMenu = sessionStorage.getItem("importModelMenu");
			var applyInformation = sessionStorage.getItem("applyInformation");
			var message = sessionStorage.getItem("confirmMessage");
			var toptranslate = function(index) {
				layer.close(index);
				window.location.href = baseurl + urlId;
				sessionStorage.setItem("confirmMessage", null);
			}
			if (($('title').text() == "取込完了" && importModelMenu == 0 && key != null && mode != null && !importErr)|| (applyInformation == "F" && !importErr)) {
				sessionStorage.setItem(key, mode);
				window.location.href = baseurl + urlId;
			} else if (message != "null" && message != null) {
				layer.confirm(message, {
					icon : 3,
					title : '',
					closeBtn : 0,
					btn : ['はい','いいえ']
				}, toptranslate)
			} else {
				if (className == "checkListMode") {
					sessionStorage
							.setItem("checkList", BLENUM.MenuEnum.TopMenu);
				}else if(className == "shinseiRirekiHe"){
					sessionStorage
					.setItem("applyMode", 2);
				}else if(className == "ShohinMode"){
					sessionStorage.setItem("goodsList",BLENUM.ModeEnum.New);
				}else if(className == "shinseiIpan") {
					sessionStorage.setItem("applyCommonModel","model=1");
				}else if(className == "KetsugouMode") {
					sessionStorage.setItem("joinList",BLENUM.ModeEnum.New);
				}else if(className == "SetMode"){
					sessionStorage.setItem("setList",BLENUM.ModeEnum.New);
				}else if(className == "shinseiShinkiHimoku") {
					sessionStorage.setItem("applyMenuMode","1");
				}
				window.location.href = baseurl + urlId;
			}
		}

		$('.link').click(function() {
			var className = $(this).attr("class").split(" ")[0];
			switch (className) {
			case "ShohinMode":
				getcommondata("/goods/goods", className, "goodsList", BLENUM.ModeEnum.Readonly);
				break;
			case "SetMode":
				getcommondata("/setlist/setlist", className, "setList", BLENUM.ModeEnum.Readonly);
				break;
			case "KetsugouMode":
				getcommondata("/joinlist/joinList", className, "joinList", BLENUM.ModeEnum.Readonly);
				break;
			case "checkListMode":
				getcommondata("/checkList/checkList", className, null, null);
				break;
			case "shinseiIpan":
				$.post(baseurl + "/applycommon/clear");
				getcommondata("/applycommon/applyCommon", className, null, null);
				break;
			case "shinseiShinkiHimoku":
				getcommondata("/applynewcategory/applynewcategory", className, null, null);
				break;
			case "shinseiRirekiHe":
				getcommondata("/applyhistory/apply_history", className, null, null);
				break;
			case "kaishaJhoho":
				getcommondata("/employee/companysetting", className, null, null);
				break;
			case "tantoshaJhoho":
				getcommondata("/usersetting/usersetting", className, null, null);
				break;
			case "topMenuMode":
				getcommondata("/topmenu/topMenu", className, null, null);
				break;
			default:
				break;
			}
		});
		
		//cookieを削除
		function delAllCookie(){
            var myDate=new Date();
            myDate.setTime(-1000);
            var data=document.cookie;
            var dataArray=data.split("; ");
            for(var i=0;i<dataArray.length;i++){
                 var varName=dataArray[i].split("=");
                 document.cookie=varName[0]+"=''; expires="+myDate.toGMTString()+"; path=/";
            }
		}
		
		$(".guest-profile").click(function() {
			$(".guest-content").slideToggle();
		})
		
		$(".guest-content").click(function() {
			var	logout = function(index) {
				layer.close(index);
				sessionStorage.clear();
				delAllCookie();
				window.location.href = baseurl + "/loginmaker/login";
			}
			layer.confirm("ログアウトしますか?",{
				title : '',
				closeBtn : 0,
				btn : ['はい','いいえ']
			},logout);
		})
		$(".accountName").html(accountNameVal);
		$(".companyName").html(companyName);
	};
	var header = new BLUI.SystemHeader();
});