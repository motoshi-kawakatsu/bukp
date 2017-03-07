$(function () {
    'use strict';
    $('header').hide();
    $('nav').hide();
    $('footer').hide();
    var applyMode1 = sessionStorage.getItem('applyMenuMode');
    var applyNewMode = $('.apply-new-mode').val();
    var applyMode5 = sessionStorage.getItem('applyNewMode');
    if (applyMode1 != null){
        var applyNewEnum = applyMode1;
        sessionStorage.removeItem("applyMenuMode");
    }else if(applyNewMode != null){
        var applyNewEnum = applyNewMode;
    }else{
        var applyNewEnum = applyMode5;
    }
    var Apply = function (){};
    Apply.prototype={
        init: function(){
            if(applyNewEnum == BLENUM.ApplyNewEnum.MODE1){
                $('header').show();
                $('nav').show();
                $('footer').show();
                // システム時間
                var systime = getCurrentDate('yyyy/MM/dd HH:mm');
                window.systime2 = getCurrentDate('yyyy-MM-dd HH:mm:ss.SSS');
                $('.apply-before').show();
                $('.apply-time').val(systime);
                var applyNo = $('.apply-no').val();
                var applyComments = $('.apply-comments').val();
                sessionStorage.setItem("applyNo",applyNo); 
                sessionStorage.setItem("applyComments",applyComments); 
                window.applyState = '1';
            }else if(applyNewEnum == BLENUM.ApplyNewEnum.MODE2){
                $('.apply-accept').show();
                $('.apply-comments').attr('disabled',true);
                window.applyState = '2';
            }else if(applyNewEnum == BLENUM.ApplyNewEnum.MODE3){
                $('.apply-reject').show();
                $('.apply-comments').attr('disabled',true);
                window.applyState = '3';
            }else if(applyNewEnum == BLENUM.ApplyNewEnum.MODE4){
                $('header').show();
                $('nav').show();
                $('footer').show();
                // システム時間
                var systime = getCurrentDate('yyyy/MM/dd HH:mm');
                window.systime2 = getCurrentDate('yyyy-MM-dd HH:mm:ss.SSS');
                $('.apply-before').show();
                $('.apply-time').val(systime);
                window.applyState = '4';
            }else{
                $('header').show();
                $('nav').show();
                $('footer').show();
                window.applyState = '5';
                var applyNo = sessionStorage.getItem('applyNo');
                var applyTime = sessionStorage.getItem('applyTime');
                var applyComments = sessionStorage.getItem('applyComments');
                $('.apply-no').val(applyNo);
                $('.apply-time').val(applyTime);
                $('.apply-comments').val(applyComments);
                if(applyNo != ''){
                    $('.apply-after').show();
                    $('.apply-comments').attr('disabled',true);
                }else{
                    $('.apply-before').show();
                    var systime = getCurrentDate('yyyy/MM/dd HH:mm');
                    window.systime2 = getCurrentDate('yyyy-MM-dd HH:mm:ss.SSS');
                    $('.apply-time').val(systime);
                }
                
            }
            var contentLen = $('.apply-comments').val(); 
            $('.apply-comments').val('').focus().val(contentLen);
            $('.apply').on('click', $.proxy(this.onApplyClick, this));
            $('.topmenu').on('click', $.proxy(this.onTopMenuClick, this));
            $('.apply-history').on('click', $.proxy(this.onApplyHistoryClick, this));
            $('.return').on('click', $.proxy(this.onReturnClick, this));
            $('.apply-again').on('click', $.proxy(this.onApplyAgainClick, this));
        },
        
        //　トップページへボタンを押す
        onTopMenuClick: function(){
            if(window.applyState == '4'){
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
            window.location.href = baseurl + '/topmenu/topMenu';
        },
        
        // 申請履歴へボタンを押す
        onApplyHistoryClick: function(){
            if(window.applyState == '4'){
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
            sessionStorage.setItem("applyMode",BLENUM.HISTORYEnum.MODE3);
            window.location.href = baseurl + '/applyhistory/apply_history';
        },
        
        // 閉じるボタンを押す
        onReturnClick: function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        },
        
        // 申請戻し再提出ボタンを押す
        onApplyAgainClick: function(){
            var accessURL = baseurl + "/applynewcategory/applyMode4";
            var form = {
                    
                }
            $.blAjax({
                isTranFlag: true,// true:他画面へ遷移、false:他画面へ遷移しない
                url: accessURL,
                type: 'POST',
                data: JSON.stringify(form),
                success: function(data) {
                    parent.$(".apply-new-status").val('4');
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                },
                error: function(data, httpStatus, errorHandler) {
                    errorHandler(data, httpStatus);
                }
            });
        },
        
        // 申請ボタンを押す
        onApplyClick: function(){
            sessionStorage.setItem("isNewCategory","Y");
            try {
                var accessURL = baseurl + '/applynewcategory/applynewcategory';
                var form = {
                    applyState: applyState,
                    applyComments: $('.apply-comments').val(),
                    applyDtTime: systime2,
                }
                $.blAjax({
                    isTranFlag: true,// true:他画面へ遷移、false:他画面へ遷移しない
                    url: accessURL,
                    data: JSON.stringify(form),
                    type: 'POST',
                    success: function(data) {
                        $('<div id="doubleCommitPrevent" class="reveal-modal">'
                                + '<div class="loading-img" ><img src="'
                                + baseurl
                                + '/imgs/loading-2.gif"></div>'
                                + '<div class="loading-text">' + '送信中...'
                                + '</div>' + '</div>').appendTo("body");
                        $('<div id="blankModal" class="reveal-modal"></div>').appendTo(
                        "body");
                        // 申請完了画面をポップアップ表示する
                        layer.config({
                            extend: 'skin/select/style.css'
                        });
                        layer.open({
                          type: 2,
                          title: false,
                          skin: 'layer-ext-skin',
                          shade: 0.1,
                          area: [ '60%', '60%' ],
                          closeBtn :false,
                          end : function () {
                              $('label img').removeAttr("src");
                              $('.msg_info_display').hide();
                              $('.apply-before').hide();
                              $('.apply-after').show();
                              $('.apply-no').val(data.applyNo);
                              $('.apply-comments').attr('disabled',true);
                              $('.reveal-modal').hide();
                              sessionStorage.setItem("confirmMessage",null); 
                              sessionStorage.setItem("applyNewMode",'5'); 
                              var applyNo = $('.apply-no').val();
                              var applyTime = $('.apply-time').val();
                              var applyComments = $('.apply-comments').val();
                              sessionStorage.setItem("applyNo",applyNo); 
                              sessionStorage.setItem("applyTime",applyTime); 
                              sessionStorage.setItem("applyComments",applyComments); 
                              var sendMailURL = baseurl + "/applynewcategory/sendMail";
                                var form = {
                                        
                                    }
                                $.blAjax({
                                    isTranFlag: true,// true:他画面へ遷移、false:他画面へ遷移しない
                                    url: sendMailURL,
                                    type: 'POST',
                                    data: JSON.stringify(form),
                                    success: function(data) {
                                        var sendEmailFlag = data.sendEmailFlag;
                                        if(sendEmailFlag){
                                            console.log('Send Mail Success');
                                        }else{
                                            var message = data.messageMap;
                                             layer.alert(getMessageInfo(message.E90002),
                                                        {title : '',
                                                         closeBtn : 0,
                                                         btn : ['確定']}
                                                );
                                        }
                                    },
                                    error: function(data, httpStatus, errorHandler) {
                                        errorHandler(data, httpStatus);
                                    }
                                });
                                      
                              
                          },
                          content:[baseurl + '/applied/applied'],
                          btn:['閉じる'],
                        });
                    },
                    error: function(data, httpStatus, errorHandler) {
                        errorHandler(data, httpStatus);
                    }
                });
            } catch (e) {
                sendClientErrorLog(e);
            }
        },
        
    };
    var apply = new Apply;
    apply.init();
    if(applyNewEnum != '2' && applyNewEnum != '3'){
        var nabigeshon = new BLUI.Nabigeshon();
        nabigeshon.getData(8); 
    }
    
    $('.apply-comments').change(backAction);
    
    function backAction(e) {
        var accessUrl = baseurl + "/applynewcategory/back";
        var paramData = {
                applyComments: $('.apply-comments').val(),
        }
        $.blAjax({
            url : accessUrl,
            data : JSON.stringify(paramData),
            type : 'POST',
            success : function(data) {
                var relateUrl = data.topMenuKey;
                var dom = e.target;
                var toptrans = function(index){
                    layer.close(index);
                    sessionStorage.setItem("confirmMessage",null);
                    window.location.href = baseurl + relateUrl;
                }
                if(data.pageUpdateFlag){
                    var message = data.messageMap;
                    sessionStorage.setItem("confirmMessage", getMessageInfo(message.Q00001));
                }else{
                    sessionStorage.setItem("confirmMessage",null);
                    window.location.href = baseurl + relateUrl;
                }
            },
            error : function(data, httpStatus, errorHandler) {
                errorHandler(data, httpStatus);
            }
        });
    }
    
    
});