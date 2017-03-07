$(function() {
	'use strict';
	var isFromImport = document.getElementsByClassName("import_result")[0].value;
	var model = sessionStorage.getItem("applyCommonModel");
	var isLocked = document.getElementsByClassName("isLocked")[0].value;
	if ("model=2" == model||"model=4" == model) {
		if ("Y" == isFromImport) {
			if ("Y" == isLocked) {
				document.getElementsByClassName("shinsei-general_import")[0].disabled = true;
			}
			var nabigeshon = new BLUI.Nabigeshon();
			nabigeshon.getData(20);
		}
	}else if("model=1" == model||"model=3" == model) {
		//model = "model=1";
		if ("Y" == isLocked) {
			document.getElementsByClassName("shinsei-general_new")[0].disabled = true;
			document.getElementsByClassName("goods-select")[0].disabled = true;
			document.getElementsByClassName("set-select")[0].disabled = true;
			document.getElementsByClassName("union-select")[0].disabled = true;
			document.getElementsByClassName("apply-comments")[0].disabled = true;
			layer.alert("ほかのユーザーのデータがあります、申請ことができません。", {
				title : '',
				closeBtn : 0,
				btn : [ '確定' ]
			});
		}
		var nabigeshon = new BLUI.Nabigeshon();
		nabigeshon.getData(7);
	}
	var applyNo = sessionStorage.getItem("applyCommonNo");
	var str = model;
	var isSaved = sessionStorage.getItem("isSaved");
	if ("model=1" == str) {
		$(".show-model1").show();
		$(".show-bbb").show();
		$(".show-bbb-a").show();
		$(".show-bbb-b").show();
		$(".show-bbb-c").show();
		$(".modoru").show();
		$(".shinsei-general_new").show();
		$(".show-model2").hide();
		$(".show-aaa").hide();
		$(".shinsei-detal").hide();
		$(".shinsei-general_import").hide();
		$(".show-model").hide();
		$(".top-page").hide();
		$(".shinsei-history").hide();
		if("Y" == isSaved){
			document.getElementsByClassName("apply-comments")[0].value="";
			sessionStorage.removeItem("isSaved");
		}
		// 状態
		sessionStorage.setItem("applyInformation", "T");
	} else if ("model=2" == str) {
		$(".show-model2").show();
		$(".show-aaa").show();
		$(".show-bbb").show();
		$(".modoru").show();
		$(".shinsei-detal").show();
		$(".shinsei-general_import").show();
		$(".note").hide();
		$(".show-model1").hide();
		$(".show-bbb-a").hide();
		$(".show-bbb-b").hide();
		$(".show-bbb-c").hide();
		if("Y" == isSaved){
			document.getElementsByClassName("apply-comments")[0].value="";
			sessionStorage.removeItem("isSaved");
		}
		// 状態
		sessionStorage.setItem("applyInformation", "F");
	} else if ("model=3" == str) {
		$(".show-model1").show();
		$(".show-bbb").show();
		$(".show-bbb-a").show();
		$(".show-bbb-b").show();
		$(".show-bbb-c").show();
		$(".modoru").hide();
		$(".shinsei-general_new").hide();
		$(".show-model2").hide();
		$(".show-aaa").hide();
		$(".shinsei-detal").hide();
		$(".shinsei-general_import").hide();
		$(".show-model").hide();
		$(".top-page").show();
		$(".shinsei-history").show();
		$(".com-approval")[0].disabled = "disabled";
		$(".select").hide();
		$(".note").hide();
		$(".approval-id").val(applyNo);
		// 状態
		sessionStorage.setItem("applyInformation", "T");
	} else if ("model=4" == str) {
		$(".show-model2").show();
		$(".show-aaa").show();
		$(".show-bbb").show();
		$(".modoru").hide();
		$(".shinsei-detal").hide();
		$(".shinsei-general_import").hide();
		$(".note").hide();
		$(".show-model1").hide();
		$(".show-bbb-a").hide();
		$(".show-bbb-b").hide();
		$(".show-bbb-c").hide();
		$(".top-page").show();
		$(".shinsei-history").show();
		
		$(".com-approval")[0].disabled = "disabled";
		$(".main li").eq(3).removeClass("current");
		$(".main li").eq(3).addClass("completion");
		$(".main li").eq(4).addClass("completion");
		
		$(".select").hide();
		$(".approval-id").val(applyNo);
		// 状態
		sessionStorage.setItem("applyInformation", "T");
		sessionStorage.setItem("importErr", "true");
	}
	var Apply = function() {
	};
	Apply.prototype = {
		init : function() {
			$(".modoru").on("click", $.proxy(this.onBackBtnClick, this));
			$(".topmenu").on("click", $.proxy(this.onTopMenuClick, this));
			$(".shinsei-general_new").on("click",
					$.proxy(this.onShinseiGeneralNewClick, this));
			$(".shinsei-general_import").on("click",
					$.proxy(this.onShinseiGeneralImportClick, this));
			$(".top-page").on("click", $.proxy(this.onTopMenuClick, this));
			$(".shinsei-history").on("click",
					$.proxy(this.onApplyHistory, this));
			$(".shinsei-detal")
					.on("click", $.proxy(this.onShinseiDetail, this));
			$(".goods-select").on("click",
					$.proxy(this.onGoodSelBtnClick, this));
			$(".set-select").on("click", $.proxy(this.onSetSelBtnClick, this));
			$(".union-select").on("click",
					$.proxy(this.onUnionSelBtnClick, this));
			document.getElementsByClassName("form-control approval-time")[0].value = getDate();
		},
		onApplyHistory : function() {
			var model = sessionStorage.getItem("applyCommonModel");
			if ("model=1" == model) {
				sessionStorage.setItem("applyMode", BLENUM.HISTORYEnum.MODE4);
			} else {
				sessionStorage.setItem("applyMode", BLENUM.HISTORYEnum.MODE5);
			}
			window.location.href = baseurl + "/applyhistory/apply_history";
		},
		// 申請履歴戻る
		onBackBtnClick : function() {
			var model = sessionStorage.getItem("applyCommonModel");
			if ("model=2" == model) {
				window.location.href = baseurl + "/readresult/readresult";
			} else {
				window.location.href = baseurl + "/topmenu/topMenu";
			}
		},
		// TopMenuと申請履歴戻る
		onTopMenuClick : function() {
			window.location.href = baseurl + "/topmenu/topMenu";
		},
		// 申請詳細
		onShinseiDetail : function() {
			sessionStorage.setItem("applyDetail", BLENUM.MenuEnum.applycommon);
			layer.config({
				extend : "/skin/select/style.css"
			});
			layer
					.open({
						type : 2,
						title : false,
						skin : 'layer-ext-skin',
						shade : 0.1,
						area : [ '100%', '80%' ],
						closeBtn : false,
						// layer 戻る
						end : function() {
							sessionStorage.setItem("applyCommonModel",
									"model=2");
						},
						content : [ baseurl + '/apply/applydetail' ]
					});
		},
		onShinseiGeneralNewClick : function() {
			var form = {
					applyComments : $('.apply-comments').val()
			} 
			$.blAjax({
				url : baseurl + "/applycommon/check", 
				data : JSON.stringify(form), 
				type : 'POST', 
				error : function(xhr) {
					alert(Error); 
				}, 
				success : function(result) {
						var errorMessageList = result.errorMessageList; 
						var warningMessageList = result.warningMessageList;
						var overBytes = result.overBytes;
						var zeroMessage = result.zeroMessage;
						if(undefined != overBytes||null!= overBytes){
							$('.check-msg').append( overBytes + '</br>');							
							document.getElementsByClassName("shinsei-general_new")[0].disabled = true; 
							if(undefined != zeroMessage||null!=zeroMessage){
								$('.check-msg').append( zeroMessage + '</br>');
							}
							if (null != errorMessageList) { 
								for (var i = 0; i < errorMessageList.length; i++) {
									$('.check-msg').append( errorMessageList[i] + '</br>'); 
								}							
							}
							if (null != warningMessageList) {
								for (var i = 0; i < warningMessageList.length; i++) {
									$('.check-msg').append( warningMessageList[i] + '</br>'); 
								}
							}
						}else if(undefined != zeroMessage||null!=zeroMessage){
							$('.check-msg').append( zeroMessage + '</br>');
							document.getElementsByClassName("shinsei-general_new")[0].disabled = true; 
							if (null != errorMessageList) { 
								for (var i = 0; i < errorMessageList.length; i++) {
									$('.check-msg').append( errorMessageList[i] + '</br>'); 
								}							
							}
							if (null != warningMessageList) {
								for (var i = 0; i < warningMessageList.length; i++) {
									$('.check-msg').append( warningMessageList[i] + '</br>'); 
								}
							}
						}else{
							if (null != errorMessageList) { 
								for (var i = 0; i < errorMessageList.length; i++) {
									$('.check-msg').append( errorMessageList[i] + '</br>'); 
								}							
								document.getElementsByClassName("shinsei-general_new")[0].disabled = true;
								if (null != warningMessageList) {
									for (var i = 0; i < warningMessageList.length; i++) {
										$('.check-msg').append( warningMessageList[i] + '</br>'); 
									}
								}
							} else if (null != warningMessageList) {
								for (var i = 0; i < warningMessageList.length; i++) {
									$('.check-msg').append( warningMessageList[i] + '</br>'); 
								}								
								layer.confirm('チェック警告があります、警告を無視して、処理を続きますか？',
										{title: false, btn: ['はい', 'いいえ']},
										function(){
											applyImport();
										},
										function(){
											document.getElementsByClassName("shinsei-general_new")[0].disabled = true;
											layer.close();
										});  
							} else {
								applyGeneral();
							}
						}											
				} 
			});
		},
		onShinseiGeneralImportClick : function() {
			var form = {
					applyComments : $('.apply-comments').val()
			} 
			$.blAjax({ 
				url : baseurl + "/applycommon/check", 
				data : JSON.stringify(form), 
				type : 'POST', 
				error : function(xhr) {
					alert(Error); 
				}, 
				success : function(result) {
						var errorMessageList = result.errorMessageList; 
						var warningMessageList = result.warningMessageList;
						var overBytes = result.overBytes;
						var zeroMessage = result.zeroMessage;
						if(undefined != overBytes||null!= overBytes){
							$('.check-msg').append( overBytes + '</br>');							
							document.getElementsByClassName("shinsei-general_import")[0].disabled = true; 
							if(undefined != zeroMessage||null!=zeroMessage){
								$('.check-msg').append( zeroMessage + '</br>');
							}
							if (null != errorMessageList) { 
								for (var i = 0; i < errorMessageList.length; i++) {
									$('.check-msg').append( errorMessageList[i] + '</br>'); 
								}							
							}
							if (null != warningMessageList) {
								for (var i = 0; i < warningMessageList.length; i++) {
									$('.check-msg').append( warningMessageList[i] + '</br>'); 
								}
							}
						}else if(undefined != zeroMessage||null!=zeroMessage){
							$('.check-msg').append( zeroMessage + '</br>');
							document.getElementsByClassName("shinsei-general_import")[0].disabled = true; 
							if (null != errorMessageList) { 
								for (var i = 0; i < errorMessageList.length; i++) {
									$('.check-msg').append( errorMessageList[i] + '</br>'); 
								}							
							}
							if (null != warningMessageList) {
								for (var i = 0; i < warningMessageList.length; i++) {
									$('.check-msg').append( warningMessageList[i] + '</br>'); 
								}
							}
						}else{
							if (null != errorMessageList) { 
								for (var i = 0; i < errorMessageList.length; i++) {
									$('.check-msg').append( errorMessageList[i] + '</br>'); 
								}	
								document.getElementsByClassName("shinsei-general_import")[0].disabled = true; 
								if (null != warningMessageList) {
									for (var i = 0; i < warningMessageList.length; i++) {
										$('.check-msg').append( warningMessageList[i] + '</br>'); 
									}
								}
							} else if (null != warningMessageList) { 
								for (var i = 0; i < warningMessageList.length; i++) {
									$('.check-msg').append( warningMessageList[i] + '</br>'); 
								}
								if(null != overBytes){
									$('.check-msg').append( overBytes + '</br>'); 
								}
								layer.confirm('チェック警告があります、警告を無視して、処理を続きますか？',
										{title: false, btn: ['はい', 'いいえ']},
										function(){
											applyImport();
										},
										function(){
											document.getElementsByClassName("shinsei-general_import")[0].disabled = true;
											layer.close();
										}); 
							} else {
								applyImport();
							}
						}										
				} 
			});		 
		},
		onGoodSelBtnClick : function() {
			sessionStorage.setItem("goodsList",BLENUM.ModeEnum.Update);
			var form = {
					applyComments : $('.apply-comments').val()
			} 
			$.blAjax({ 
				url : baseurl + "/applycommon/getapplycomments", 
				data : JSON.stringify(form), 
				type : 'POST', 
				error : function(xhr) {
					alert(Error); 
				}, 
				success : function(result) {
					window.location.href = baseurl + "/goods/goods";
				}
			});
		},
		onSetSelBtnClick : function() {
			sessionStorage.setItem("setList",BLENUM.ModeEnum.Update);
			var form = {
					applyComments : $('.apply-comments').val()
			} 
			$.blAjax({ 
				url : baseurl + "/applycommon/getapplycomments", 
				data : JSON.stringify(form), 
				type : 'POST', 
				error : function(xhr) {
					alert(Error); 
				}, 
				success : function(result) {
					window.location.href = baseurl + "/setlist/setlist";
				}
			});						
		},
		onUnionSelBtnClick : function() {
			sessionStorage.setItem("joinList",BLENUM.ModeEnum.Update);
			var form = {
					applyComments : $('.apply-comments').val()
			} 
			$.blAjax({ 
				url : baseurl + "/applycommon/getapplycomments", 
				data : JSON.stringify(form), 
				type : 'POST', 
				error : function(xhr) {
					alert(Error); 
				}, 
				success : function(result) {
					window.location.href = baseurl + "/joinlist/joinList";
				}
			});				
		},
	};
	function getDate() {
		return getCurrentDate('yyyy/MM/dd HH:mm');
	}
	function applyGeneral() {
		var form = {
			applyComments : $('.apply-comments').val()
		}
		$.blAjax({
			allowModal : true,// true:二重送信制御が必要、false:二重送信制御が必要しない
			isTranFlag : true,// true:他画面へ遷移、false:他画面へ遷移しない
			url : baseurl + "/applycommon/applyCommon",
			data : JSON.stringify(form),
			type : 'POST',
			success : function(result) {
				if("Y" == result.applyError) {
					result = null;
					layer.alert("申請失敗しました、もう一度申請してください。",{
						title : '',
                        closeBtn : 0,
                        btn:['確認']
					});
				}else{
					document.getElementsByClassName("shinsei-general_new")[0].disabled = true;
					document.getElementsByClassName("goods-select")[0].disabled = true;
					document.getElementsByClassName("set-select")[0].disabled = true;
					document.getElementsByClassName("union-select")[0].disabled = true;
					document.getElementsByClassName("apply-comments")[0].disabled = true;
					$("#doubleCommitPrevent").hide();
					if(null != result.applyResult){
						layer.alert(result.applyResult,{
							title : '',
	                        closeBtn : 0,
	                        btn:['確認']
						});						
					}else{
						layer.config({
							extend : "/skin/select/style.css"
						});
						layer.open({
							type : 2,
							title : false,
							skin : 'layer-ext-skin',
							shade : 0.1,
							area : [ '60%', '70%' ],
							closeBtn : false,
							// layer 戻る
							end : function() {
								sessionStorage.setItem("applyCommonModel","model=3");
								sessionStorage.setItem("applyCommonNo",result.applyNo);
								sessionStorage.setItem("applyComments",form.applyComments);
								window.location.href = baseurl + "/applycommon/applyCommon";						
							},
							content : [ baseurl + '/applied/applied' ],
							btn : [ '閉じる' ],
						});
					}					
				}				
			},
			error : function(data, httpStatus, errorHandler) {
				errorHandler(data, httpStatus);
			}
		});
	}
	function applyImport() {
		var form = {
			applyComments : $('.apply-comments').val()
		}
		$.blAjax({
			allowModal : true,// true:二重送信制御が必要、false:二重送信制御が必要しない
			isTranFlag : true,// true:他画面へ遷移、false:他画面へ遷移しない
			url : baseurl + "/applycommon/applyCommon",
			data : JSON.stringify(form),
			type : "POST",
			success : function(result) {
				//session削除
				sessionStorage.removeItem("importModelMenu");
				if("Y" == result.applyError) {
					layer.alert("申請失敗しました、もう一度申請してください。",{
						title : '',
                        closeBtn : 0,
                        btn:['確認']
					});
				}else{
					sessionStorage.setItem("applyInformation", "T");
					document.getElementsByClassName("apply-comments")[0].disabled = true;
					document.getElementsByClassName("shinsei-general_import")[0].disabled = true;
					document.getElementsByClassName("shinsei-detal")[0].disabled = true;
					$("#doubleCommitPrevent").hide();
					if(null != result.applyResult){
						layer.alert(result.applyResult,{
							title : '',
	                        closeBtn : 0,
	                        btn:['確認']
						});
						
					}else{
						layer.config({
							extend : "/skin/select/style.css"
						});
						layer.open({
							type : 2,
							title : false,
							skin : 'layer-ext-skin',
							shade : 0.1,
							area : [ '60%', '70%' ],
							closeBtn : false,
							// layer 戻る
							end : function() {
								sessionStorage.setItem("applyCommonModel","model=4");
								sessionStorage.setItem("applyCommonNo",result.applyNo);
								sessionStorage.setItem("applyComments",form.applyComments);
								window.location.href = baseurl + "/applycommon/applyCommon";
							},
							content : [ baseurl + '/applied/applied' ],
							btn : [ '閉じる' ],
						});
					}					
				}				
			}
		});
	}
	var apply = new Apply;
	apply.init();
});