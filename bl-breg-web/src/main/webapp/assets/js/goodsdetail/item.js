/**
 * @file 商品一件編集
 */
$(function () {

    'use strict';
    var num = 0;
    var goodsMode;
    var makerCode;
    var inputItems;
    var delImage = [];
    var message = [];
    $(".imageupload").hide();
    $(".magnify_toggle").hide();

    /// <summary>
    /// 商品一件編集画面
    /// </summary>
    var ItemPage = function (mode) {
        this.mode = mode;
        this.inputNames = [
            "dealDistinction",               
            "selectCd",
            "classifyCd",
            "blCd",
            "exceProNo",
            "proNameHalf",
            "proNameFull",
            "price",
            "openPrice",
            "jan",
            "classify",
            "equipment",
            "specialMatter",
            "specialMatterCommon",
            "productDetail",
            "productDetailCommon",
            "url1",
            "url2",
            "url3",
            "proLenA",
            "proLenB",
            "proLenC",
            "packLenA",
            "packLenB",
            "packLenC",
            "lengthUnit",
            "weight",
            "weightUnit",
            "setDate",
            "updateDate",
            "applyDate",
            "errorDistinction",
            "delDistinction",
            "delReason",
            "applyState"
        ]

    };

    ItemPage.prototype = {
        /// <summary>
        /// 初期処理
        /// </summary>
        init: function () {
        	goodsMode = this.mode;
        	inputItems = this.inputNames;
            this.bindAction();
            this.itemShow();
            this.onImageInit();
            //　メッセージを取得する
            this.messageInit();
            //全画像
            this.imageMax();
            this.imageShow();
            this.imageName();

            switch (this.mode) {
                case BLENUM.ModeEnum.New:               	
                	this.initData();
                    break;
                case BLENUM.ModeEnum.Update:
                case BLENUM.ModeEnum.Readonly:
                case BLENUM.ModeEnum.Error:
                    this.initData();
                    this.initImg();
                    break;
                default:
                    break;
            }
            this.inputDisabled();
            this.inputNecessary();

        },
        /// <summary>
        /// add項目のイベント
        /// </summary>
        bindAction: function () {
            $("[name='delDistinction']").on('change', $.proxy(this.delCheck, this));
            $('.back').on('click', this.confirmLayer);      
            $(".save").on("click", this.saveData);
            $('.classifyCdGuide').on('click', $.proxy(this.showClassifyGuide, this));
            $("[name='setDate'],[name='applyDate'],[name='updateDate']").datetimepickerJp({
          	  format: 'YYYY/MM/DD　HH:mm'
            });         
            $('[name="classifyCd"]').focus($.proxy(this.focus, this));
            $('[name="classifyCd"]').blur($.proxy(this.blur, this));
            $('#proNameHalf').on('blur',function(){
          	  var str =  document.getElementById('proNameHalf').value;
          	  if(!checkHankaku(str)){
          		  document.getElementById('proNameHalf').value = "";
          		  layer.alert('errorMsg', {
                        title : '',
                        closeBtn : 0,
                        btn : [ 'はい' ]
                  });
          	  } else {
          		  document.getElementById('proNameHalf').value = zenkaku2Hankaku(str);
          	  }
            });
            //フォーマット
            $('#price').on('blur',function(){
        		format_number('price');
    		});
            $('#weight').on('blur',function(){
        		format_number('weight');
    		});
            $('#proLenA').on('blur',function(){
        		format_number('proLenA');
    		});
            $('#proLenB').on('blur',function(){
        		format_number('proLenB');
    		});
            $('#proLenC').on('blur',function(){
        		format_number('proLenC');
    		});
            $('#packLenA').on('blur',function(){
        		format_number('packLenA');
    		});
            $('#packLenB').on('blur',function(){
        		format_number('packLenB');
    		});
            $('#packLenC').on('blur',function(){
        		format_number('packLenC');
    		});
            // 画像処理       
            $(".btn-left").on("click", this.onUpClick);
            $(".btn-right").on("click", this.onDownClick);
            $(".btn-all-select").on("click", this.onMultiSelectBtnClick);
            $("[name='multiUpload']").on("change", this.onMultiFileSelected);
            $("[name='multiUpload']").on("change", this.clearMultiFileInput);
            $(".image-select").on("click", this.onSelectBtnClick);
            $("[name='upload']").on("change", this.onFileSelected);
            $("[name='upload']").on("change", this.clearFileInput);
            $(".image-delete").on("click", this.onImageDel);
            $(".image-container").on("dragover", this.onDragOver);
            $(".image-container").on("drop", this.onDrop);            
            // 全画像処理
            $(".btn-all-image").on("click",this.onAllImageLayer); 
            $(".imageupload-save").on("click", this.onImageUploadSave);
            $(".img-max").on("click", this.onImageMax);

        },
        /// <summary>
        /// closeLayer
        /// </summary>
        closeLayer: function () {
        	var index = parent.layer.getFrameIndex(window.name);        	
        	parent.layer.close(index);
        },
        /// <summary>
        /// confirmLayer
        /// </summary>
        confirmLayer: function () {
        	if (goodsMode == BLENUM.ModeEnum.Readonly) {
        		ItemPage.prototype.closeLayer();
        	}
	        layer.confirm(message.Q00001, {
				icon : 3,
				title : '',
				closeBtn : 0,
				btn : [ 'はい', 'いいえ' ],
				yes : function(){
					if (goodsMode == BLENUM.ModeEnum.New) {
		        		var grid = parent.document.getElementById("grid")["wj-Control"];
		        		grid.collectionView.remove(grid.selectedRows[0].dataItem);
		        	}
					ItemPage.prototype.closeLayer();
				}	
			})
        },
        /// <summary>
        /// 項目の展示
        /// </summary>
        itemShow: function () {
            switch (this.mode) {
                case BLENUM.ModeEnum.New:
                    break;
                case BLENUM.ModeEnum.Update:
                    break;
                case BLENUM.ModeEnum.Readonly:
                    $('.save').addClass('hide');
                    $('.file-all').addClass('hide')
                    break;
                case BLENUM.ModeEnum.Error:
                    break;
                default:
                    break;
            }
           
        },
        /// <summary>
        /// message初期化
        /// </summary>
        messageInit: function () {
        	try {
        		var _this = this;
        		var accessURL = baseurl + "/goodsdetail/messageInit";
				$.blAjax({
//					allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
//					modalStyle: 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示	
//					isTranFlag: false,// true:他画面へ遷移、false:他画面へ遷移しない
					url: accessURL,
					type: "POST",
					success: function(result) {
						message = result.messageMap;
					},
					error : function(result, httpStatus, errorHandler) {
						errorHandler(result, httpStatus);
					}
				});
        	} catch (e) {
				sendClientErrorLog(e);
			}        	
        },
        /// <summary>
        /// newdata初期化
        /// </summary>
        initNewData: function () {
        	var formData = sessionStorage.getItem("formData");
    		formData = JSON.parse(formData);
    		$("input[name='applyDate']").val(formData.applyDateStart);
    		$("select[name='selectCd']").val(formData.selectCd);
    		$("input[name='specialMatter']").val(formData.primePartsSpecialNoteRFB);
    		$("input[name='classifyCd']").val(formData.classifyCd);
    		$("input[name='specialMatterCommon']").val(formData.primePartsSpecialNoteRFC);
    		$("select[name='blCd']").val(formData.blCd);
    		$("input[name='exceProNo']").val(formData.goodsCd);
    		$("input[name='proNameHalf']").val(formData.goodsName);
    		$("select[name='openPrice']").val(formData.priceClass);
    		$("input[name='equipment']").val(formData.equipment);
    		$("select[name='classify']").val(formData.oldCd);
    		$("textarea[name='productDetail']").val(formData.goodDetail);
    		$("textarea[name='productDetailCommon']").val(formData.goodDetailCommon);
        },
        /// <summary>
        /// data初期化
        /// </summary>
        initData: function () {
        	try {
        		var _this = this;
        		var accessURL = baseurl + "/goodsdetail/dateInit";
				$.blAjax({
//					allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
//					modalStyle: 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示	
//					isTranFlag: false,// true:他画面へ遷移、false:他画面へ遷移しない
					url: accessURL,
					type: "POST",
					data: JSON.stringify(goodsMode),
					success: function(result) {
						var data = result;
						makerCode = data.makerCode;
	                    for (var i in _this.inputNames) {
	                        var name = _this.inputNames[i];
	                        $("[name=" + name + "]").val(data.inputData[name]);
	                        if(name=='delDistinction'&&data.inputData[name]==1){
	                            $("[name='delReason']").prev().html(function () {
	                                return $(this).html() + "<span class='required-mark'>*</span>";
	                            });
	                        }
	                    }
	                    if (goodsMode == BLENUM.ModeEnum.New) {
	                    	ItemPage.prototype.initNewData();
	                    }
					},
					error : function(result, httpStatus, errorHandler) {
						errorHandler(result, httpStatus);
					}
				});
        	} catch (e) {
				sendClientErrorLog(e);
			}        	
        },
        /// <summary>
        /// Img初期化
        /// </summary>
        initImg: function () {
        	$(".btn-all-image").attr('disabled', true);
        	try {
        		var _this = this;
        		var accessURL = baseurl + "/goodsdetail/imgInit";
        		var googleURL = "https://storage.googleapis.com/";
        		var bucketName = "mystoragetest001";
				$.blAjax({
					allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
					modalStyle: 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示	
					isTranFlag: false,// true:他画面へ遷移、false:他画面へ遷移しない
					url: accessURL,
					type: "POST",
					success: function(result) {
						for (var i in result.inputImg) {
							var path = result.inputImg[i];
							var name = path.split("/")[2];
							var no = parseInt(name.split("_")[2].split(".")[0].substring(1)) + 1;
							$(".img" + no).find("img")[0].src = googleURL+bucketName+"/"+path;
							$(".img" + no).children()[2].innerText = name;
							if (goodsMode != BLENUM.ModeEnum.Readonly) {
								$(".img" + no).find("button")[1].disabled = false;
							}
						}
						$(".btn-all-image").attr('disabled', false);
					},
					error : function(result, httpStatus, errorHandler) {
						errorHandler(result, httpStatus);
						$(".btn-all-image").attr('disabled', false);
					}
				});
				//$(".btn-all-image").attr('disabled', false);
        	} catch (e) {
				sendClientErrorLog(e);
			}        	
        },
        /// <summary>
        /// saveData
        /// </summary>
        saveData: function () {
        	layer.confirm(message.Q00002, {
				icon : 3,
				title : '',
				closeBtn : 0,
				btn : [ 'はい', 'いいえ' ],
				yes : function(){
					try {	
			        	var accessURL_data = baseurl + "/goodsdetail/goodsDetailSave";
			    		var param = ItemPage.prototype.getParam();
			    		var imgNum = ItemPage.prototype.getImgNum();
			    		var form_data = {
							mode: goodsMode,
							goodsDetailDto: param,
							imageNum: imgNum
			    		}
			    		var accessURL_img = baseurl + "/goodsdetail/goodsImgSave";
			    		var src = ItemPage.prototype.getSrc();
			    		var name = ItemPage.prototype.getFileName();
			    		var form_img = {
							imgSrcList: src,
							imgNameList: name,
							delImgList: delImage,
							goodsCd: $("[name='exceProNo']").val(),
			    		}
			        	
			    		$.blAjax({
		    					allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
		    					modalStyle: 'none', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示	
		    					isTranFlag: false, // true:他画面へ遷移、false:他画面へ遷移しない
		    					url: accessURL_img,
		    					type: "POST",
		    					data: JSON.stringify(form_img),
		    					success: function(result_img) {
		    						$.blAjax({
				   // 					allowModal: true,// true:二重送信制御が必要、false:二重送信制御が必要しない
				   //					modalStyle: 'fade', // fade:「送信中」レイアが表示、none:「黒い」レイアが表示	
				   // 					isTranFlag: false,// true:他画面へ遷移、false:他画面へ遷移しない
				    					url: accessURL_data,
				    					type: "POST",
				    					data: JSON.stringify(form_data),
				    					success: function(result_data) {
				    						if (!result_data.isErrorExist) {
				    							if (result_data.isPriceZero) {
				    								layer.alert(result_data.errorMsg,{
						    							title : '',
						    							closeBtn : 0,
						    							btn : [ '確定' ],
					    								end : function(){
					    									ItemPage.prototype.goodsGridUpd(result_data.gridData);
						    								ItemPage.prototype.closeLayer();
						    							}
						    						});
				    							} else {
					    							layer.alert(message.I00001,{
						    							title : '',
						    							closeBtn : 0,
						    							btn : [ '確定' ],
						    							end : function(){
						    								ItemPage.prototype.goodsGridUpd(result_data.gridData);
						    								ItemPage.prototype.closeLayer();
						    							}
						    						});
				    							}
				    						} else {
				    							layer.alert(result_data.errorMsg,{
					    							title : '',
					    							closeBtn : 0,
					    							btn : [ '確定' ]
					    						});
				    						}
				    					},
				    					error : function(result_1, httpStatus_1, errorHandler_1) {
				    						alert("error1");
				    						errorHandler(result_1, httpStatus_1);	
				    					}
				    				});
		    					},
		    					error : function(result_img, httpStatus_img, errorHandler_img) {
		    						alert("error2");
		    						errorHandler(result_img, httpStatus_img);	
		    					}
		    					});	    					   				
		        	} catch (e) {
						sendClientErrorLog(e);
					}
				}
        	})
        },
        /// <summary>
        ///goodsGridUpd
        /// </summary>
        goodsGridUpd: function (gridData) {
        	var grid = parent.document.getElementById("grid")["wj-Control"];
        	var rowItem = grid.selectedRows[0].dataItem;
        	for (var name in gridData) {
        		if (name != "no" && name != "dateCom" && name != "dateRe") {
        			rowItem[name] = gridData[name];
        		}
        	}
        	grid.itemsSource.refresh();
        },
        /// <summary>
        ///入力制御項目
        /// </summary>
        inputDisabled: function () {
        	$(".image-delete").attr('disabled', 'true');
            switch (this.mode) {
                case BLENUM.ModeEnum.New:
                    $("[name='dealDistinction'],[name='setDate'],[name='updateDate'],[name='errorDistinction'],[name='applyState']")
                    .attr('disabled', 'true');
                    break;
                case BLENUM.ModeEnum.Update:
                    $("[name='dealDistinction'],[name='setDate'],[name='selectCd'],[name='exceProNo']" +
                    		",[name='updateDate'],[name='errorDistinction'],[name='applyState']")
                    .attr('disabled', 'true');
                    break;
                case BLENUM.ModeEnum.Readonly:
                	$("input,.btn-select,.image-select,.btn-all-select,.classifyCdGuide,textarea,select")
                    .attr('disabled', 'true');
                    break;
                case BLENUM.ModeEnum.Error:
                    $("[name='dealDistinction'],[name='setDate'],[name='selectCd'],[name='exceProNo']" +
                    		",[name='updateDate'],[name='errorDistinction'],[name='applyState']")
                    .attr('disabled', 'true');
                    break;
                default:
                    break;
            }

        },
        /// <summary>
        ///必須入力項目
        /// </summary>
        inputNecessary: function () {
            var necessaryInput = $("[name='blCd'],[name='selectCd'],[name='classifyCd'],[name='exceProNo']," +
            		"[name='price'],[name='openPrice'],[name='proNameFull'],[name='proNameHalf'],[name='applyDate']");
            necessaryInput.prev().html(function () {
                return $(this).html() + "<span class='required-mark'>*</span>";
            });
        },
        /// <summary>
        ///削除区分check
        /// </summary>
        delCheck: function () {
            var delReason = $("[name='delReason']");
            console.log($("[name='delDistinction']"));
            if ($("[name='delDistinction']").val() == '1') {

                delReason.prev().html(function () {
                    return $(this).html() + "<span class='required-mark'>*</span>";
                });
            } else {
                delReason.prev().find('span').remove();
            }
        },
        /// <summary>
		/// popup guide
		/// </summary>
		showClassifyGuide : function(event) {
			try {
                var accessURL = baseurl + '/goodsdetail/goodsMGroup';
                var actionType = 'POST';
                var form = {
                    goodsMGroup: $('[name="classifyCd"]').val()
                };
                $.blAjax({
                    url: accessURL,
                    type: actionType,
                    data: JSON.stringify(form),
                    success: function (data) {
                        var guide = new BLUI.ClassifyCdGuide();
                        guide.show('classifyCd', false, {});

                    },
                    error: function (data, httpStatus, errorHandler) {
                        errorHandler(data, httpStatus);
                    }
                });
            } catch (e) {
                sendClientErrorLog(e);
            }
		},
		// 分類コード  フォーカスアウト 処理
		focus : function(event) {
            var value = event.currentTarget.value;
            value = value.split("：")[0];
            event.currentTarget.value = value;
        },
        blur : function(event) {
            var accessURL = baseurl + "/changecommon/getCode";
            var form = {
                  code : event.currentTarget.value,
                  guideType : "0"
            };
            $.blAjax({
                  url : accessURL,
                  type : "POST",
                  data : JSON.stringify(form),
                  error : function(data, httpStatus, errorHandler) {
                        errorHandler(data, httpStatus);
                  },
                  success : function(data) {
                        if (data.message != undefined) {
                              layer.alert(data.message, {
                                    title : '',
                                    closeBtn : 0,
                                    btn : [ '確定' ],
                                    end : function() {
                                          event.currentTarget.value = "";
                                    }
                              });
                        }
                        event.currentTarget.value = data.codeValue;
                  },
            });
        },
        /// <summary>
        ///画面入力項目取得
        /// </summary>
        getParam: function () {

            var data = {};
            for (var i in inputItems) {
                var name = inputItems[i];
                data[name] = $("[name=" + name + "]").val();
            }
            return data;
        },
        /// <summary>
        ///画像数取得
        /// </summary>
        getImgNum: function () {
        	var num = 0;
        	for (var i = 1; i < 10; i++) {        		
        		if ($(".img" + i).find("img")[0].src != ""
        			&& $(".img" + i).find("img")[0].src != window.location.href) {
        			num = num + 1;
        		}
        	}
        	return num;
        },
        /// <summary>
        ///画面入力項目取得
        /// </summary>
        getSrc: function () {
        	var head = "data"
            var data = [];
            for (var i = 1; i < 10; i++) {
            	if ($(".img" + i).find("img")[0].src.substring(0,4) == head) {
            		data.push($(".img" + i).find("img")[0].src);
            	}
            }
            return data;
        },
        /// <summary>
        ///画面入力項目取得
        /// </summary>
        getFileName: function () {
        	var head = "data"
            var data = [];
            for (var i = 1; i < 10; i++) {                
            	if ($(".img" + i).find("img")[0].src.substring(0,4) == head) {
            		data.push($(".file-name")[i - 1].innerText);
            	}
            }
            return data;
        },
        /// <summary>
        /// back処理
        /// </summary>
        back: function () {
            history.go(-1);
        },
        /// <summary>
        /// url処理
        /// </summary>
        urlJump: function (event) {
            location.href = event.data.url;
        },
        /// <summary>
        /// HeadClickイベント
        /// </summary>
        onHeadClick: function () {
            $(".item-image").toggleClass("hide");
        },
        /// <summary>
        /// イメージinitイベント
        /// </summary>
        onImageInit: function () {
            $(".img4").hide();
            $(".img5").hide();
            $(".img6").hide();
            $(".img7").hide();
            $(".img8").hide();
            $(".img9").hide();
        },
        /// <summary>
        /// ファイル選択ボタンクリック
        /// </summary>
        onMultiSelectBtnClick: function () {
            $("[name='multiUpload']").click();
        },
        /// <summary>
        /// ファイル選択ボタンクリック
        /// </summary>
        onSelectBtnClick: function () {
        	//removeClass 
        	$(".select-btn-click-min").removeClass("select-btn-click-min");
            $(".select-btn-click-max").removeClass("select-btn-click-max");
        	var button = $(this).prop("name"); 
        	var buttonName = button.substring(6,8); 
        	if(parseInt(buttonName)<10){
        		var btnMinName = button; 
        		var btnMaxName = button.substring(0,6)+(parseInt(buttonName)+10)+"";
        		$("button[name="+btnMinName+"]").addClass("select-btn-click-min");
        		$("button[name="+btnMaxName+"]").addClass("select-btn-click-max");          
        		$("[name='upload']").click();
        	}else if(parseInt(buttonName)>10){
        		var btnMaxName = button; 
        		var btnMinName = button.substring(0,6)+(parseInt(buttonName)-10)+"";
        		$("button[name="+btnMinName+"]").addClass("select-btn-click-min");
        		$("button[name="+btnMaxName+"]").addClass("select-btn-click-max");
        		$("[name='upload']").click();
        	}
        },

        /// <summary>
        /// DragOverイベント
        /// </summary>
        onDragOver: function (e) {
            e.preventDefault();
        },

        /// <summary>
        /// Dropイベント
        /// </summary>
        onDrop: function (e) {
            var _this = $(this);
            e.preventDefault();
            var dt = e.originalEvent.dataTransfer;
            var file = dt.files[0];
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (event) {
                var image = _this.find("img");
                image.attr("src", event.target.result);
                ItemPage.prototype.imageWidthFormat(image);
            }
            _this.children()[2].innerText = file.name;
        },

        /// <summary>
        /// FileSelectイベント
        /// </summary>
        onMultiFileSelected: function (e) {           
        	//all画像を削除
        		var head = "data"
            	for(var i=1;i<10;i++){
            		var imgMinName = "img"+i+"";
            		var imgMaxName = "img"+(i+10)+"";           		
            		if ($("img[name="+imgMinName+"]")[0].src.substring(0,4) != head 
                    		&& $("img[name="+imgMinName+"]")[0].src != window.location.href) {
            			delImage.push($("img[name="+imgMinName+"]").parent().parent().children()[2].innerText);
                    }
            		var deleteBtnMin = $("img[name="+imgMinName+"]").parent().parent().find(".image-delete");
            		deleteBtnMin[0].disabled = true;
    				var deleteBtnMax = $("img[name="+imgMaxName+"]").parent().parent().find(".image-delete");
    				deleteBtnMax[0].disabled = true;
            		$("img[name="+imgMinName+"]").attr('src', "");
            		$("img[name="+imgMaxName+"]").attr('src', ""); 
            		$("img[name="+imgMaxName+"]").attr('src', "");
                    $("img[name="+imgMinName+"]").parent().parent().children()[2].innerText = "画像名";
                    $("img[name="+imgMaxName+"]").parent().parent().children()[2].innerText = "画像名";
                    $("img[name="+imgMinName+"]").parent().ch
                    
            	}    
            	
            var fileUpload = $("[name='multiUpload']")[0];
            var blankImg = $("img[id='imageupload'][src='']").length;
            var selectedImg = fileUpload.files.length;
            var count = blankImg > selectedImg ? selectedImg : blankImg;
            for (var i = 0; i < count; i++) {
                var reader = new FileReader();
                reader.readAsDataURL(fileUpload.files[i]);
                reader.onload = function (event) {                
                    var image = $("img[id='imageupload'][src='']:first");
                    image.attr("src", event.target.result);   			
                    ItemPage.prototype.imageWidthFormat(image);
                    $('.img-max').attr('src', $(".imga").attr("src"));                   
                    $('.img-max').attr('name', "1");

                } 
            var val = $("#goodsCd").val();
            if(val==""){
               val = "****";
            }
        	var code = makerCode;
        	var blimgName = code+"_"+val+"_";
                for (var p = 0; p < 9; p++) {
                	var codeName = "0"+(p+".jpg");
                    var name = $(".file-name")[p];
                    if (name.innerText == "画像名") {
                        name.innerText = fileUpload.files[i].name;
                        var spanMinName = "img"+(p+1)+"";
                        var spanMaxName = "img"+(p+11)+"";
                        $("img[name="+spanMinName+"]").parent().parent().children()[2].innerText = blimgName+codeName;
                        $("img[name="+spanMaxName+"]").parent().parent().children()[2].innerText = blimgName+codeName;
                        var deleteBtnMin = $("img[name="+spanMinName+"]").parent().parent().find(".image-delete");
                        deleteBtnMin[0].disabled = false;
        				var deleteBtnMax = $("img[name="+spanMaxName+"]").parent().parent().find(".image-delete");
        				deleteBtnMax[0].disabled = false;
                        
                        break;
                    }
                }
            }
          
        },
        /// <summary>
        /// clearFileInputイベント
        /// </summary>
        clearMultiFileInput: function () {
            var file = $("[name='multiUpload']")[0];
            var form=document.createElement('form');
            document.body.appendChild(form);
            var pos = file.nextSibling;
            form.appendChild(file);
            form.reset();
            pos.parentNode.insertBefore(file, pos);
            document.body.removeChild(form);
        },
        /// <summary>
        /// FileSelectイベント
        /// </summary>
        onFileSelected: function (e) {
            var fileUpload = $("[name='upload']")[0];
            var reader = new FileReader();
                reader.readAsDataURL(fileUpload.files[0]); 
                var imageMin = $(".select-btn-click-min").parent().parent().find("img");
            	var imageMax = $(".select-btn-click-max").parent().parent().find("img");
            	var imgMinName = imageMin.attr("name").substring(3,5);
                reader.onload = function (event) {                   	
                    $(".select-btn-click-min").removeClass("select-btn-click-min");
                    $(".select-btn-click-max").removeClass("select-btn-click-max");
                    imageMin.attr("src", event.target.result); 
                    imageMax.attr("src", event.target.result);                    
                    if(parseInt(imgMinName)==parseInt($(".img-max").attr("name"))){                   		                          
                				$('.img-max').attr("src", event.target.result);        				
                			}
                    ItemPage.prototype.imageWidthFormat(imageMin); 
                    ItemPage.prototype.imageWidthFormat(imageMax);
                } 
                var val = $("#goodsCd").val();
                if(val==""){
                	val = "****";
                }
        		var code = makerCode;
        		var blimgName = code+"_"+val+"_";
        		var codeName = "0"+(parseInt(imgMinName)-1)+".jpg";      			      			       		
            $(".select-btn-click-min").parent().parent().children()[2].innerText = blimgName+codeName; 
            $(".select-btn-click-max").parent().parent().children()[2].innerText = blimgName+codeName;
            $(".select-btn-click-min").parent().children()[1].disabled = false;
        	$(".select-btn-click-max").parent().children()[1].disabled = false;                    	
        },
        /// <summary>
        /// clearFileInputイベント
        /// </summary>
        clearFileInput: function () {
            var file = $("[name='upload']")[0];
            var form=document.createElement('form');
            document.body.appendChild(form);
            var pos = file.nextSibling;
            form.appendChild(file);
            form.reset();
            pos.parentNode.insertBefore(file, pos);
            document.body.removeChild(form);
        },
        /// <summary>
        /// イメージ削除イベント
        /// </summary>
        onImageDel: function(){
        	
        	var head = "data"
            var img = $(this.parentNode.parentNode).find("img");
        	var imgName = img[0].name.substring(3,5);
        	
        	//Max画像を削除
        	if(parseInt(imgName)==parseInt($(".img-max").attr("name")) 
        			|| (parseInt(imgName)-10)==parseInt($(".img-max").attr("name")))
        			{
        				$('.img-max').attr('src', "");
        			}
        	if(parseInt(imgName)<10){
        		var imgMinName = img[0].name;
        		var imgMaxName = img[0].name.substring(0,3)+(parseInt(imgName)+10)+"";
        		//imgMinName.srcを削除
                if (img[0].src.substring(0,4) != head 
                		&& img[0].src != window.location.href) {
                	delImage.push(img.parent().parent().children()[2].innerText);               
                }
                img[0].src = '';
                img.parent().parent().children()[2].innerText = "画像名";
                $(this).attr("disabled","true");
        		//imgMaxName.srcを削除
                var max = $("img[name="+imgMaxName+"]").attr('src', "");
                $("img[name="+imgMaxName+"]").parent().parent().children()[2].innerText = "画像名";
                var deleteBtn = $("img[name="+imgMaxName+"]").parent().parent().find(".image-delete");
				deleteBtn[0].disabled = true;
        	}else if(parseInt(imgName)>10){
        		var imgMaxName = img[0].name;
        		var imgMinName = img[0].name.substring(0,3)+(parseInt(imgName)-10)+"";
        		//imgMaxName.srcを削除
        		var max = $("img[name="+imgMaxName+"]").attr('src', "");
        		
        		$(this).attr("disabled","true");
        		//imgMinName.srcを削除      		        		 
                if ($("img[name="+imgMinName+"]")[0].src.substring(0,4) != head 
                		&& $("img[name="+imgMinName+"]")[0].src != window.location.href) {
                	delImage.push($("img[name="+imgMinName+"]").parent().parent().children()[2].innerText);
                }              
                var min = $("img[name="+imgMinName+"]").attr('src', "");
                $("img[name="+imgMinName+"]").parent().parent().children()[2].innerText = "画像名";
                $("img[name="+imgMaxName+"]").parent().parent().children()[2].innerText = "画像名";
                var deleteBtn = $("img[name="+imgMinName+"]").parent().parent().find(".image-delete");
				deleteBtn[0].disabled = true;
        	} 
        },

        /// <summary>
        /// イメージWidthformat
        /// </summary>
        imageWidthFormat: function (image) {
            var containerWidth = $('.item-image > div.panel-body').width();
            var imageWidth = parseInt(containerWidth / 9 - 15) + 'px';
            image.css('width', imageWidth);
        },
        /// <summary>
        /// UpClickイベント
        /// </summary>
        onUpClick: function () {
          var x = ".img";
          var y = num--;
             
          if(y>0){
            var m = x+y+"";
            var n = x+(y+3)+"";
                 
            $(m).show();
            $(n).hide();

          }else{
            var m = ".img9";
            var n = "begin";
            
            num=6;
            $(".img1").hide();
            $(".img2").hide();
            $(".img3").hide();
            $(".img7").show();
            $(".img8").show();
            $(".img9").show();
          }
        },
        /// <summary>
        /// DownClickイベント
        /// </summary>
        onDownClick: function () {
          var x = ".img";
          num++;
          var y = num;
          
          if(num<7){
            var m = x+y+"";
            var n = x+(y+3)+"";
                 
            $(m).hide();
            $(n).show();

          }else{
            var m = ".img1";
            var n = "begin";
            
            num=0;
            $(".img1").show();
            $(".img2").show();
            $(".img3").show();
            $(".img7").hide();
            $(".img8").hide();
            $(".img9").hide();
          }
        },
        /// <summary>
        /// 全画像処理
        /// </summary> 
        
        /// <summary>
        /// 全画像イベント
        /// </summary>  
        onAllImageLayer: function(){
        	$(".imageupload").show();

        	//画像 を更新
        	$('.imga').attr('src', $(".img01").attr("src"));
        	$('.imgb').attr('src', $(".img02").attr("src"));
        	$('.imgc').attr('src', $(".img03").attr("src"));
        	$('.imgd').attr('src', $(".img04").attr("src"));
        	$('.imge').attr('src', $(".img05").attr("src"));
        	$('.imgf').attr('src', $(".img06").attr("src"));
        	$('.imgg').attr('src', $(".img07").attr("src"));
        	$('.imgh').attr('src', $(".img08").attr("src"));
        	$('.imgi').attr('src', $(".img09").attr("src"));
        	$('.img-max').attr('src', $(".imga").attr("src")); 
//        	//画像名を更新
        	$(".span-name1").text($(".img01").parent().parent().children()[2].innerText);
        	$(".span-name2").text($(".img02").parent().parent().children()[2].innerText);     
        	$(".span-name3").text($(".img03").parent().parent().children()[2].innerText);
        	$(".span-name4").text($(".img04").parent().parent().children()[2].innerText);
        	$(".span-name5").text($(".img05").parent().parent().children()[2].innerText);
        	$(".span-name6").text($(".img06").parent().parent().children()[2].innerText);
        	$(".span-name7").text($(".img07").parent().parent().children()[2].innerText);
        	$(".span-name8").text($(".img08").parent().parent().children()[2].innerText);
        	$(".span-name9").text($(".img09").parent().parent().children()[2].innerText);
        	if (goodsMode != BLENUM.ModeEnum.Readonly) {
	        	for(var i = 1;i < 10;i++){
	    			var imageMaxName = "img"+(i+10)+"";
	    			var img = $("img[name="+imageMaxName+"]").attr("src");        			
	    			if(img !== ""){   			
	    				var deleteBtn = $("img[name="+imageMaxName+"]").parent().parent().find(".image-delete");
	    				deleteBtn[0].disabled = false;
	    			}
	    		}
        	}	
        },

        /// <summary>
        /// 確定イベント
        /// </summary>    
        onImageUploadSave: function(){
        	$(".imageupload").hide();
        	//画像 を更新
        	$('.img01').attr('src', $(".imga").attr("src"));
        	$('.img02').attr('src', $(".imgb").attr("src"));
        	$('.img03').attr('src', $(".imgc").attr("src"));
        	$('.img04').attr('src', $(".imgd").attr("src"));
        	$('.img05').attr('src', $(".imge").attr("src"));
        	$('.img06').attr('src', $(".imgf").attr("src"));
        	$('.img07').attr('src', $(".imgg").attr("src"));
        	$('.img08').attr('src', $(".imgh").attr("src"));
        	$('.img09').attr('src', $(".imgi").attr("src"));
        },
         
        /// <summary>
        /// 画面-max
        /// </summary>
        imageMax: function(){
        	$(".img-max").mouseenter(function(){
        		$(".magnify_toggle").show();
        	});
   
        	$(".img-max").mouseleave(function(){
        		$(".magnify_toggle").hide();
        	});
        },
        
        /// <summary>
        /// 画像名処理
        /// </summary>
        imageName: function(){
        	$("input[name='exceProNo']").blur(function(){               	
        		var val = $("#goodsCd").val();
        		if(val==""){
                	val = "****";
                }
        		var code = makerCode;
        		var blimgName = code+"_"+val+"_";
        		for(var i = 1;i < 10;i++){
        			var imageMinName = "img"+i+"";
        			var imageMaxName = "img"+(i+10)+"";
        			var codeName = "0"+(i-1)+".jpg";
        			var img = $("img[name="+imageMinName+"]").attr("src");       			
        			if(img !== ""){
        				$("img[name="+imageMinName+"]").parent().parent().children()[2].innerText = blimgName+codeName;
        				$("img[name="+imageMaxName+"]").parent().parent().children()[2].innerText = blimgName+codeName;
        			}
        		}
        		
        		    
          	});
        }, 
        /// <summary>
        /// 画面-max-show
        /// </summary>
        imageShow: function(){
        	$(".imga").click(function(){
                var res=$(".imga").attr("src");
                 
                if($('.img-max').attr('src') == res){ 
                  $('.img-max').attr('src', res); 
                }else{ 
                  $('.img-max').attr('src', res); 
                } 
                $('.img-max').attr('name', "1");
              });
        	
              $(".imgb").click(function(){
                var res=$(".imgb").attr("src");
                if($('.img-max').attr('src') == res){ 
                  $('.img-max').attr('src', res); 
                }else{ 
                  $('.img-max').attr('src', res); 
                } 
                $('.img-max').attr('name', "2");
              });
              $(".imgc").click(function(){
                var res=$(".imgc").attr("src");
                if($('.img-max').attr('src') == res){ 
                  $('.img-max').attr('src', res); 
                }else{ 
                  $('.img-max').attr('src', res); 
                } 
                $('.img-max').attr('name', "3");
              });
              $(".imgd").click(function(){
                var res=$(".imgd").attr("src");
                if($('.img-max').attr('src') == res){ 
                  $('.img-max').attr('src', res); 
                }else{ 
                  $('.img-max').attr('src', res); 
                } 
                $('.img-max').attr('name', "4");
              });
              $(".imge").click(function(){
                var res=$(".imge").attr("src");
                if($('.img-max').attr('src') == res){ 
                  $('.img-max').attr('src', res); 
                }else{ 
                  $('.img-max').attr('src', res); 
                } 
                $('.img-max').attr('name', "5");
              });
              $(".imgf").click(function(){
                var res=$(".imgf").attr("src");
                if($('.img-max').attr('src') == res){ 
                  $('.img-max').attr('src', res); 
                }else{ 
                  $('.img-max').attr('src', res); 
                } 
                $('.img-max').attr('name', "6");
              });
              $(".imgg").click(function(){
                var res=$(".imgg").attr("src");
                if($('.img-max').attr('src') == res){ 
                  $('.img-max').attr('src', res); 
                }else{ 
                  $('.img-max').attr('src', res); 
                } 
                $('.img-max').attr('name', "7");
              });
              $(".imgh").click(function(){
                var res=$(".imgh").attr("src");
                if($('.img-max').attr('src') == res){ 
                  $('.img-max').attr('src', res); 
                }else{ 
                  $('.img-max').attr('src', res); 
                } 
                $('.img-max').attr('name', "8");
              });
              $(".imgi").click(function(){
                var res=$(".imgi").attr("src");
                if($('.img-max').attr('src') == res){ 
                  $('.img-max').attr('src', res); 
                }else{ 
                  $('.img-max').attr('src', res); 
                }    
                $('.img-max').attr('name', "9");
              });
        },


    };

    // 画面モード取得
    var query = new URI(window.location.href).query(true);
    var mode = query.mode ? parseInt(query.mode) : BLENUM.ModeEnum.New;

    // 画面初期化
    var page = new ItemPage(mode);
    page.init();
    
});