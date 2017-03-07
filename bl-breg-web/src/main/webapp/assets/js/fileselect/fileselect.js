$(function () {
        'use strict';
        var ImportComplete = function () {
        };
        ImportComplete.prototype={
                init: function(){
                    this.viewInit();
                    $('.goods-import-btn').focus();
                    $('.goods-import-btn').on('click', $.proxy(this.onGoodsImportClick, this));
                    $('.set-import-btn').on('click', $.proxy(this.onSetImportClick, this));
                    $('.join-import-btn').on('click', $.proxy(this.onjoinImportClick, this));
                    $('.import').on('click', $.proxy(this.onImportClick, this));
                },
                /*画面のタイトルなど項目をゲットする。*/
                viewInit: function(){
                    var importTypeMenu = sessionStorage.getItem("importModelMenu");
                    var importMode;
                    var importSchedule ='<li class="bl-diagram-item ">申請</li><li class="bl-diagram-item ">申請完了</li>';
                    if('0' == importTypeMenu){
                        importMode = 'インポート（一括申請）';
                        $('.diagram-position').append(importSchedule);
                    }else if('1' == importTypeMenu){
                        importMode = 'インポート';
                    }
                    console.log(importTypeMenu);
                    $("title").html(importMode); 
                    $('.page-header').append('<h2>'+ importMode + '</h2>');
                },
                
                /*インポートボタンをクリックする時。*/
                onImportClick: function(){
                    if(goodsUploadFlag || setUploadFlag || joinUploadFlag){
                        layer.alert('ファイルがアップロードしていますが、少々待ちください。', {title: false, btn: ['はい']});
                        return;
                    }
                    var goodsFile = $('.goodsd').prop('files')[0];
                    var setFile = $('.setd').prop('files')[0];
                    var joinFile = $('.joind').prop('files')[0];
                    var goodsType = $('input[name="goods_select_type"]:checked').val();
                    var setType = $('input[name="set_select_type"]:checked').val();
                    var joinType = $('input[name="join_select_type"]:checked').val();
                    /*ファイルが選択してない時、メッセージを表示する。*/
                    if(goodsFile == null && setFile == null && joinFile == null){
                        layer.alert('ファイルを選択してください。', {title: false, btn: ['はい']});
                        $('.check-msg').empty();
                    }else{
                        layer.confirm('登録しますか？',  {title: false, btn: ['はい', 'いいえ']}, 
                        function(index){
                            $('.check-msg').empty();
                            /*データベースに登録*/
                            try{
                                var importURL = baseurl + '/fileselect/fileinput';
                                var importTypeMenu = sessionStorage.getItem("importModelMenu");
                                var form ={
                                        goodsType: goodsType ,
                                        setType: setType,
                                        joinType: joinType,
                                        importTypeMenu: importTypeMenu,
                                }
                                console.log(form);
                                $.blAjax({
                                    url : importURL, 
                                    modalStyle : 'fade',
                                    isTranFlag : false,
                                    type : 'POST',
                                    data : JSON.stringify(form),
                                    success : function(result) {
                                        /*エラーメッセージをゲットする。*/
                                        var goodsFileCheckMsg = result.goodsFileCheckMsg;
                                        var setFileCheckMsg = result.setFileCheckMsg;
                                        var joinFileCheckMsg = result.joinFileCheckMsg;
                                        var goodsConsent = result.goodsConsent;
                                        var setConsent = result.setConsent;
                                        var joinConsent = result.joinConsent;
                                        var goodsFileName = $('.goods-file').text();
                                        var setFileName = $('.set-file').text();
                                        var joinFileName = $('.join-file').text();
                                        /*エラーがあるの場合、ジャンプしない。*/
                                        if((goodsFileCheckMsg != null && goodsFileCheckMsg.length != 0) ||
                                            (setFileCheckMsg != null && setFileCheckMsg.length != 0) || 
                                            (joinFileCheckMsg != null && joinFileCheckMsg.length != 0) ){
                                            if(goodsFileCheckMsg != null){
                                                for (var i=0;i<goodsFileCheckMsg.length;i++)
                                                {
                                                    $('.check-msg').append('ファイル'+goodsFileName+goodsFileCheckMsg[i]+'</br>');
                                                }
                                            }
                                            if(setFileCheckMsg != null){
                                                for (var i=0;i<setFileCheckMsg.length;i++)
                                                {
                                                    $('.check-msg').append('ファイル'+setFileName+setFileCheckMsg[i]+'</br>');
                                                }
                                            }
                                            if(joinFileCheckMsg != null){
                                                for (var i=0;i<joinFileCheckMsg.length;i++)
                                                {
                                                    $('.check-msg').append('ファイル'+joinFileName+joinFileCheckMsg[i]+'</br>');
                                                }
                                            }
                                        }else{
                                            if(goodsConsent != null || setConsent != null || joinConsent != null){
                                                var askMsg = result.askMsg;
                                                var goodsConfirmMsg = null;
                                                var setConfirmMsg = null;
                                                var joinConfirmMsg = null;
                                                if(goodsConsent != null){
                                                    goodsConfirmMsg =askMsg[0];
                                                    goodsConfirmMsg = goodsConfirmMsg.replace('{0}', goodsConsent[0]);
                                                    goodsConfirmMsg = goodsConfirmMsg.replace('{1}', goodsConsent[1]);
                                                    goodsConfirmMsg = goodsConfirmMsg.replace('{2}', goodsConsent[2]);
                                                    
                                                }
                                                if(setConsent != null){
                                                    setConfirmMsg = askMsg[1];
                                                    setConfirmMsg = setConfirmMsg.replace('{0}', setConsent[0]);
                                                    setConfirmMsg = setConfirmMsg.replace('{1}', setConsent[1]);
                                                    setConfirmMsg = setConfirmMsg.replace('{2}', setConsent[2]);
                                                }
                                                if(joinConsent != null){
                                                    joinConfirmMsg = askMsg[2];
                                                    joinConfirmMsg = joinConfirmMsg.replace('{0}', joinConsent[0]);
                                                    joinConfirmMsg = joinConfirmMsg.replace('{1}', joinConsent[1]);
                                                    joinConfirmMsg = joinConfirmMsg.replace('{2}', joinConsent[2]);
                                                }
                                                var continueMsg = (null == goodsConfirmMsg ? '' : goodsConfirmMsg + '</br>') 
                                                                            + (null == setConfirmMsg  ? '' : setConfirmMsg  + '</br>')
                                                                              + (null == joinConfirmMsg  ? '' : joinConfirmMsg  + '</br>');
                                                if(continueMsg != null){
                                                    layer.confirm(continueMsg, {icon: 3, title: false, btn: ['はい', 'いいえ']}, 
                                                            function(index1){
                                                                var continueImportURL = baseurl + '/fileselect/continue';
                                                                $.blAjax({
                                                                    url : continueImportURL,  
                                                                    type : 'POST',
                                                                    data : 'continue',
                                                                    success : function(result) {
                                                                        window.location.href = baseurl + '/importresult/importresult';
                                                                    }
                                                                });
                                                            }
                                                            );
                                                }
                                            }else{
                                                window.location.href = baseurl + '/importresult/importresult';
                                            }
                                        }
                                        
                                    },  
                                    error : function(result) {
                                    }
                                });  
                            }catch (e) {
                                sendClientErrorLog(e);
                            }
                            layer.close(index);
                        }
                        );
                    }
                },
                onGoodsImportClick: function(){
                        var htmlGoods;
                        $('.goodsd').click();
                        $('.goodsd').change(function(){
                                goodsUploadFlag = true;
                                htmlGoods = $('.goodsd').val();
                                var formData = new FormData();
                                var fileUpload = baseurl + '/fileselect/fileupload';
                                if('' == htmlGoods){
                                        $('.goods-file').html(noFileSelect);
                                        formData.append('filetype','goodsDel');
                                        $.ajax({
                                                url : fileUpload,  
                                                type : 'POST',
                                                data : formData,
                                                processData : false,
                                                contentType : false, 
                                                success : function(result) {
                                                    goodsUploadFlag = false;
                                                },  
                                                error : function(result) {
                                                    goodsUploadFlag = false;
                                                }
                                        });
                                }else{  
                                        var a = htmlGoods.split('\\')
                                        htmlGoods = a[a.length-1];
                                        $('.goods-file').html(htmlGoods);
                                        /*get the file*/
                                        var file = $('.goodsd').prop('files')[0];
                                        formData.append('filename', file); 
                                        formData.append('filetype','goods');
                                        $.ajax({
                                                url : fileUpload,  
                                                type : 'POST',
                                                data : formData,
                                                processData : false,
                                                contentType : false,
                                                success : function(result) {
                                                    goodsUploadFlag = false;
                                                },  
                                                error : function(result) {
                                                    goodsUploadFlag = false;
                                                }  
                                        });
                     
                                }
                        });
                },
                onSetImportClick: function(){
                        var htmlSet;
                        $('.setd').click();
                        $('.setd').change(function(){
                                setUploadFlag = true;
                                htmlSet = $('.setd').val();
                                var formData = new FormData();
                                var fileUpload = baseurl + '/fileselect/fileupload';
                                if('' == htmlSet){
                                        $('.set-file').html(noFileSelect);
                                        formData.append('filetype','setDel');
                                        $.ajax({
                                                url : fileUpload,  
                                                type : 'POST',
                                                data : formData,
                                                processData : false,
                                                contentType : false,
                                                success : function(result) {
                                                    setUploadFlag = false;
                                                },  
                                                error : function(result) {
                                                    setUploadFlag = false;
                                                }
                                        });
                                }else{
                                        var a = htmlSet.split('\\')
                                        htmlSet = a[a.length-1];
                                        $('.set-file').html(htmlSet);
                                        /*get the file*/
                                        var file = $('.setd').prop('files')[0];
                                        console.log(file);
                                        formData.append('filename', file); 
                                        formData.append('filetype','set');
                                        $.ajax({
                                                url : fileUpload,  
                                                type : 'POST',
                                                data : formData,
                                                processData : false,
                                                contentType : false,
                                                success : function(result) {
                                                    setUploadFlag = false;
                                                },  
                                                error : function(result) {
                                                    setUploadFlag = false;
                                                }  
                                        });
                                }
                        });
                },
                onjoinImportClick: function(){
                        var htmljoin;
                        $('.joind').click();
                        $('.joind').change(function(){
                                joinUploadFlag = true;
                                htmljoin = $('.joind').val();
                                var formData = new FormData();
                                var fileUpload = baseurl + '/fileselect/fileupload';
                                if('' == htmljoin){
                                        $('.join-file').html(noFileSelect);
                                        formData.append('filetype','joinDel');
                                        $.ajax({
                                                url : fileUpload,  
                                                type : 'POST',
                                                data : formData,
                                                processData : false,
                                                contentType : false,
                                                success : function(result) {  
                                                    joinUploadFlag = false;
                                                },
                                                error : function(result) {
                                                    joinUploadFlag = false;
                                                }
                                        });
                                }else{
                                        var a = htmljoin.split('\\')
                                        htmljoin = a[a.length-1];
                                        $('.join-file').html(htmljoin);
                                        /*get the file*/
                                        var file = $('.joind').prop('files')[0];
                                        console.log(file);
                                        formData.append('filename', file); 
                                        formData.append('filetype','join');
                                        $.ajax({
                                                url : fileUpload,  
                                                type : 'POST',
                                                data : formData,
                                                processData : false,
                                                contentType : false,
                                                success : function(result) {
                                                    joinUploadFlag = false;
                                                },  
                                                error : function(result) {
                                                    joinUploadFlag = false;
                                                }  
                                        });
                                }
                        });
                },
        };
        var goodsUploadFlag = false;
        var setUploadFlag = false;
        var joinUploadFlag = false;
        var noFileSelect = 'ファイルが選択されていません。';
        var importFile = new ImportComplete;
        importFile.init();
        var nabigeshon=new BLUI.Nabigeshon();
        if('0' == sessionStorage.getItem("importModelMenu")){
            nabigeshon.getData(4);
        }else if('1' == sessionStorage.getItem("importModelMenu")){
            nabigeshon.getData(5);
        }
 });