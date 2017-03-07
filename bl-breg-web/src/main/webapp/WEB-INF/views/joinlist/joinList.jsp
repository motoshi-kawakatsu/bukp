<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>結合一覧</title>


<link rel="stylesheet" href="${baseurl}/lib/reset/reset.css">
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/lib/wijmo5/styles/wijmo.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">

<!-- 個別css -->
<link rel="stylesheet" href="${baseurl}/css/joinlist/join.css">

</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<div class="page-header">
		<h2 id="title">結合一覧</h2>
	</div>
    <div class="container">
        <div class="panel panel-primary slide-panel search-scope flow-x">
            <div class="panel-heading head">
                <h3 class="panel-title">検索条件</h3>
            </div>
            <div class="panel-body panel-row">
			 <div class="input-group bl-cd">
                    <span class="input-group-addon column1">BLコード</span> <select id="tbsPartsCode" class="form-control">
                    <option value=""></option>
                </select>
                </div>
				                <div class="input-group jyunse-hinban">
                    <span class="input-group-addon column2">純正品番</span> <input
                        id="joinSourPartsNoWithH" type="text" class="form-control c-cell" maxlength="24" oninput="charAndBarInput(this.id)"
                        name = "joinSourPartsNoWithH" readonly/>
                                                <button type="button"
                                class="glyphicon glyphicon-star-empty btn btn-default joinSourPartsNoWithH"
                                name="joinSourPartsNoWithHGuide"></button>
                </div>
				          <div class="input-group yuryo-hinban">
                    <span class="input-group-addon column3">優良品番</span> <input 
                        id="joinDestPartsNo" type="text" class="form-control  c-cell" maxlength="24" oninput="charAndBarInput(this.id)"
                        name = "joinDestPartsNo" readonly/>
                                                <button type="button"
                                class="glyphicon glyphicon-star-empty btn btn-default joinDestPartsNo"
                                name="joinDestPartsNoGuide"></button>
                </div>
				                <div class="input-group apply-status ">
                    <span class="input-group-addon column1">申請状態</span> <select
                        id="applyCondition" class="form-control" >
                    <option value="9">全データ表示</option>    
            <c:forEach items="${applyCondition}" var="item">
              <option value="${item.value}">${item.name}</option>
            </c:forEach>
                </select>
                </div>
				             <div class="input-group shori-kubun">
                    <span class="input-group-addon column2">処理区分</span> <select
                        id="manageKbn" class="form-control" >
					<option value="9">全データ表示</option>
          <c:forEach items="${manageKbn}" var="item">
              <option value="${item.value}">${item.name}</option>
            </c:forEach>
                </select>
                </div>
				        <div class="input-group error-distinction">
                    <span class="input-group-addon column3">エラー区分</span> <select
                        id="errorFlg" class="form-control">
                    <option value="9">全データ表示</option>
                    <option value="1">エラーありのみ表示</option>
                    <option value="0">エラーなしのみ表示</option>
                </select>
                </div>
                <div class="input-group set-cd">
                    <span class="input-group-addon column1">セレクトコード</span>
                    <select id="prmSetDtlNo1" class="form-control">
                    <option value=""></option>
                    <option value="9999">9999：指定無し</option>
                </select>
                </div>
				<div class="input-group car-cd">
                    <span class="input-group-addon column2">カーコード</span> <select 
                     id="joinSourceMakerCode" class="form-control" >
                     <option value=""></option>
                </select>
                </div>
				             <div class="input-group apply-date init-hide">
                    <span class="input-group-addon column3">適用日</span> <input type="text"
                       id="startTimeStart" class="form-control" maxlength="8"/> <span
                        class="input-group-addon between">～</span> <input type="text"
                        id="startTimeEnd" class="form-control" maxlength="8"/>
                </div>
				               <div class="input-group classify-cd init-hide">
                    <span class="input-group-addon column1">分類コード</span> <input type="text" name="classifyCd"
                                 id="goodsMGroup" class="form-control" maxlength="4" oninput="numInput(this.id)" />
                    <div class="code-guide">
                        <button type="button"
                                class="glyphicon glyphicon-star-empty btn btn-default classifyCdGuide"
                                name="classifyCdGuide"></button>
                    </div>
                </div>
                <div class="input-group shubestsu-cd init-hide">
                    <span class="input-group-addon column2">種別コード</span> <select
                        id="prmSetDtlNo2" class="form-control">
                    <option value=""></option>
                </select>
                </div>
                <div class="input-group insert-date init-hide">
                    <span class="input-group-addon column3">作成日</span> <input type="text"
                                           id="insDtTimeStart" class="form-control" maxlength="8"/> <span
                        class="input-group-addon between">～</span> <input type="text"
                                id="insDtTimeEnd" class="form-control" maxlength="8"/>
                </div>
                               <div class="input-group size init-hide">
                    <span class="input-group-addon column1">規格/特記</span> <input id="joinSpecialNote"
                        type="text" class="form-control" maxlength="80">
                </div>
                <div class="input-group del-distinction init-hide">
                    <span class="input-group-addon column2">削除依頼区分</span> <select
                        id="deleteFlg" class="form-control">
                    <option value="9">全データ表示</option>
            <c:forEach items="${deleteFlg}" var="item">
              <option value="${item.value}">${item.name}</option>
            </c:forEach>
                </select>
                </div>
                <div class="input-group update-date init-hide">
                    <span class="input-group-addon column3">更新日</span> <input type="text"
                                              id="updDtTimeStart" class="form-control" maxlength="8"/> <span
                        class="input-group-addon between">～</span> <input type="text"
                        id="updDtTimeEnd" class="form-control" maxlength="8"/>
                </div>
				  <div class="input-group sizenormal init-hide">
                    <span class="input-group-addon column1">規格/特記（一般）</span> <input
                        id="primePartsSpecialNoteC" type="text" class="form-control" maxlength="80">
                </div>
				<div class="empty-line"></div>
				<div class="group-button">
                    <button class="btn bl-btn-panel bl-btn-3" type="button" id="search">検索</button>
                    <button class="btn bl-btn-panel bl-btn-3" type="button" id="clear">クリア</button>
                    <button class="btn bl-btn-panel bl-btn-4 detail-condition btn-search" type="button">詳細条件</button>
                </div>
          </div>              
            </div>

        <div class="input-group sort">
            <span class="input-group-addon">表示順</span> <select id="order" class="form-control">
            <option value="0">セレクトコード＞分類コード＞BLコード＞カーコード＞純正品番＞種別コード＞表示順位</option>
            <option value="1">セレクトコード＞分類コード＞BLコード＞純正品番＞種別コード＞カーコード＞表示順位</option>
            <option value="2">セレクトコード＞分類コード＞BLコード＞優良品番＞カーコード＞純正品番</option>
        </select>
        </div>
        <p></p>

	<div>
      <div class="bl-flex-row grid-top-group-button">
        <div class="group-button ">
			<button class="btn bl-btn-panel bl-btn-4 btn-add">行挿入</button>
			<button class="btn bl-btn-panel bl-btn-4 btn-del">行削除</button>
			<button class="btn bl-btn-panel bl-btn-4 btn-copy">行コピー</button>
			<button class="btn bl-btn-panel bl-btn-4 btn-paste">行貼付</button>
			<button class="btn bl-btn-panel bl-btn-4 replaceGuide">検索・置換</button>
			<span class="space">&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<button class="btn bl-btn-panel bl-btn-4 btn_detail">詳細</button>
		</div>
		<div class="group-button ">
			<button class="btn bl-btn-panel bl-btn-5 btn_check_list"
				onclick="return false">チェックリスト</button>
			<button class="btn bl-btn-panel bl-btn-4 outputGuide">出力</button>
		</div>
	</div>
  </div>
		<div id="grid"></div>
		<div class="tab_grid">
			<div class="page_head">
				<div>検索数/全件数:
				<span class="searchNum"></span>
				<span class="allNum"></span></div>
				<div class="page-box"></div>  
                <div class="page-info">
					<span>1ページに、</span>
					<span class="historyrows"></span>
					<span>件</span>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="text-center">
					<button type="button"
						class="bl-btn-panel btn bl-btn-wiz-prev bl-btn-5 btn-back"
						id="back">
						<span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>戻る
					</button>
					<button type="button"
						class="bl-btn-panel btn bl-btn-wiz-prev bl-btn-5" id="save">保存</button>
					<button type="button"
						class="bl-btn-panel btn bl-btn-wiz-prev bl-btn-5" id="certain">確定</button>
					<button type="button"
						class="bl-btn-panel btn bl-btn-wiz-prev bl-btn-5" id="cancle">取消</button>
					<button type="button"
						class="bl-btn-panel btn bl-btn-wiz-prev bl-btn-5 btn-new" id="add">新規追加</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp" />
	
	<!--lib js-->
  <script type="text/javascript" src="${baseurl}/lib/jquery/jquery-2.2.0.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/jquery.mockjax/jquery.mockjax.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/moment/moment-with-locales.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/wijmo.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/cultures/wijmo.culture.ja.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/wijmo.grid.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/wijmo5/controls/wijmo.input.min.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/uri/URI.min.js"></script>
	<!--common js-->
	<script src="${baseurl}/js/common/common.js" type="text/javascript"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/applyperiodguide/applyperiodguide.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/common/parts/header.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/download/download.js"></script>
		<script type="text/javascript" src="${baseurl}/js/classifycodeguide/showClassifyCodeGuide.js"></script>
		<script type="text/javascript" src="${baseurl}/js/goodsguide/showGoodsGuide.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/common/parts/header.js"></script>
		<script type="text/javascript" src="${baseurl}/js/changecommon/show_changeCommon.js"></script>
		<script type="text/javascript" src="${baseurl}/js/common/inputFormat.js"></script>
		<script type="text/javascript" src="${baseurl}/js/common/check.js"></script>
		<script type="text/javascript" src="${baseurl}/js/common/jquery.pagination.min.js"></script>
		<script type="text/javascript" src="${baseurl}/js/common/jquery.alphanum.js"></script>
	<script type="text/javascript">
		window.baseurl = "${baseurl}";
	</script>    
	<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/jszip/jszip.min.js"></script>
	
	<!--personal js-->
	<script type="text/javascript"
		src="${baseurl}/js/joinlist/join.js"></script>
</body>
</html>