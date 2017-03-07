/**
 * @file 取込完了
 */
$(function () {
    'use strict';
    var CheckResult = function () {};
    CheckResult.prototype={
        init: function(){
            $('.goods-con').focus();
            $('.apply').on('click', $.proxy(this.onApplyBtnClick, this));
            $('.top-page').on('click', $.proxy(this.onTopPageBtnClick, this));
            $('.goods-con').on('click',$.proxy(this.onGoodsBtnClick, this));
            $('.set-con').on('click', $.proxy(this.onSetConClick, this));
            $('.join-con').on('click', $.proxy(this.onJoinConClick, this));
        },
        /*「トップページ」画面へ遷移する。*/
        onTopPageBtnClick: function(){
             window.location.href = baseurl+'/topmenu/topMenu';
        },
        /*「申請」画面へ遷移する。*/
        onApplyBtnClick: function(){
            sessionStorage.setItem("applyCommonModel", "model=2");
            window.location.href = baseurl+'/applycommon/applyCommon';
        },
        /*商品一覧画面「参照モード」を遷移する。*/
        onGoodsBtnClick: function(){
            sessionStorage.setItem("goodsList", BLENUM.ModeEnum.Readonly);
            window.location.href =  baseurl+'/goods/goods';
        },
        /*セット一覧画面「参照モード」を遷移する。*/
        onSetConClick: function(){
            sessionStorage.setItem('setList',BLENUM.ModeEnum.Readonly);
            window.location.href = baseurl+'/setlist/setlist';
        },
        /*結合一覧画面「参照モード」を遷移する。*/
        onJoinConClick: function(){
            sessionStorage.setItem("joinList", BLENUM.ModeEnum.Readonly);
            window.location.href = baseurl+'/joinlist/joinList';
        },
    };
   
    var check = new CheckResult;
    check.init();
    var nabigeshon=new BLUI.Nabigeshon(); 
    var importType = sessionStorage.getItem("importModelMenu");
    if(importType==null || importType == "0"){
        nabigeshon.getData(19);
    }else if(importType == "1"){
        nabigeshon.getData(22);
    }

});