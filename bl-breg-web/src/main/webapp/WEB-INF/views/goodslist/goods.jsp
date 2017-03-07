<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>商品一覧</title>


<link rel="stylesheet" href="${baseurl}/lib/reset/reset.css">
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/lib/wijmo5/styles/wijmo.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<!-- 個別css -->
<link rel="stylesheet" href="${baseurl}/css/goodslist/goods.css">

</head>
<body>
    <jsp:include page="../common/header.jsp" />
    <div class="page-header">
		<h2 id="title">商品一覧</h2>
	</div>

	<div class="container">
		<div class="panel panel-primary slide-panel search-scope flow-x">
			<div class="panel-heading head">
				<h3 class="panel-title">検索条件</h3>
			</div>
			<div class="panel-body panel-row">
				<div class="input-group bl-cd">
					<span class="input-group-addon column1">BLコード</span> <select
						id="blCd" class="form-control">
						<option value=""></option>
					</select>
				</div>
				<div class="input-group goods-Cd">
					<span class="input-group-addon column2"><b>優良品番</b></span> <input
						type="text" class="form-control" id="goodsCd" maxlength="24"
						oninput="charAndBarInput(this.id)" />
				</div>
				<div class="input-group goods-name">
					<span class="input-group-addon column3"><b>品名（半角）</b></span> <input
						type="text" class="form-control" id="goodsName" maxlength="60" />
				</div>
				<div class="input-group apply-status">
					<span class="input-group-addon column1">申請状態</span> <select
						id="applyState" class="form-control">
						<option value="9" selected="selected">全データ表示</option>
						<c:forEach items="${applyCondition}" var="item">
                         <option value="${item.value}">${item.name}</option>
                        </c:forEach>
					</select>
				</div>
				<div class="input-group shori-kubun">
					<span class="input-group-addon column2">処理区分</span> <select
						id="processDiv" class="form-control">
						<option value="9" selected="selected">全データ表示</option>
                        <c:forEach items="${manageKbn}" var="item">
                          <option value="${item.value}">${item.name}</option>
                        </c:forEach>
					</select>
				</div>
				<div class="input-group error-distinction">
					<span class="input-group-addon column3">エラー区分</span> <select
						id="errorDiv" class="form-control">
						<option value="9">全データ表示</option>
						<option value="1">エラーありのみ表示</option>
						<option value="0">エラーなしのみ表示</option>
					</select>
				</div>
				<div class="input-group set-cd init-hide">
					<span class="input-group-addon column1">セレクトコード</span> <select
						id="selectCd" class="form-control">
						<option value=""></option>
					</select>
				</div>
				<div class="input-group old-Cd init-hide">
					<span class="input-group-addon column2"><b>層別</b></span> <select
						id="oldCd" class="form-control">
						<option value=""></option>
					</select>
				</div>
				<div class="input-group apply-date init-hide">
					<span class="input-group-addon column3">適用日</span> <input
						type="text" class="form-control" maxlength="8"
						id="applyDateStart" /> <span class="input-group-addon between">～</span>
					<input type="text" class="form-control" maxlength="8"
						id="applyDateEnd" />
				</div>
				<div class="input-group classify-cd init-hide">
					<span class="input-group-addon column1">分類コード</span> <input
						type="text" class="form-control" name="classifyCode" maxlength="4" id="classifyCd" oninput="numInput(this.id)" >
					<div class="code-guide">
						<button type="button"
							class="glyphicon glyphicon-star-empty btn btn-default classifyCdGuide"></button>
					</div>
				</div>
				<div class="input-group op-en init-hide">
					<span class="input-group-addon column2"><b>OPENプライス</b></span> <select
						id="priceClass" class="form-control">
						<option value="">全データ表示</option>
						<option value="0">通常</option>
						<option value="1">オープン価格</option>
					</select>
				</div>
				<div class="input-group insert-date init-hide">
					<span class="input-group-addon column3">作成日</span> <input
						type="text" class="form-control" maxlength="8"
						id="insertDateStart" /> <span class="input-group-addon between">～</span>
					<input type="text" class="form-control" maxlength="8"
						id="insertDateEnd" />
				</div>
				<!-- 初期隠れ部分start -->

				<div class="input-group equip-ment init-hide">
					<span class="input-group-addon column1"><b>装備</b></span> <input
						type="text" class="form-control" id="equipment" maxlength="60" />
				</div>
				<div class="input-group PrimePartsSpecialNoteRFB init-hide">
					<span class="input-group-addon column2"><b>規格/特記</b></span> <input
						type="text" class="form-control" id="primePartsSpecialNoteRFB"
						maxlength="80" />
				</div>
				<div class="input-group update-date init-hide">
					<span class="input-group-addon column3">更新日</span> <input
						type="text" class="form-control" maxlength="8"
						id="updateDateStart" /> <span class="input-group-addon between">～</span>
					<input type="text" class="form-control" maxlength="8"
						id="updateDateEnd" />
				</div>
				<div class="input-group del-distinction init-hide">
					<span class="input-group-addon column1">削除依頼区分</span> <select
						id="deleteDiv" class="form-control">
						<option value="">全データ表示</option>
						<c:forEach items="${deleteFlg}" var="item">
                          <option value="${item.value}">${item.name}</option>
                        </c:forEach>
					</select>
				</div>
				<div class="input-group PrimePartsSpecialNoteRFC init-hide">
					<span class="input-group-addon column2"><b>規格/特記（一般）</b></span> <input
						type="text" class="form-control" id="primePartsSpecialNoteRFC"
						maxlength="80" />
				</div>
				<div class="input-group good-detail init-hide">
					<span class="input-group-addon column3"><b>商品詳細</b></span> <input
						type="text" class="form-control" id="goodDetail" maxlength="512" />
				</div>
				<div class="input-group good-detail-common init-hide">
					<span class="input-group-addon column1"><b>商品詳細（一般）</b></span> <input
						type="text" class="form-control" id="goodDetailCommon"
						maxlength="512" />
				</div>
				<div class="empty-line"></div>
				<div class="group-button">
					<button class="btn bl-btn-panel bl-btn-3" type="button"
						id="searchShohi">検索</button>
					<button class="btn bl-btn-panel bl-btn-3" type="button" id="clear">クリア</button>
					<button class="btn bl-btn-panel bl-btn-4 detail-condition btn-search"
						type="button">詳細条件</button>
				</div>
			</div>
		</div>
		<div class="input-group sort">
			<span class="input-group-addon">表示順</span> <select id="order"
				class="form-control">
				<option value="1">セレクトコード＞分類コード＞BLコード＞優良品番</option>
				<option value="2">セレクトコード＞分類コード＞BLコード＞層別＞優良品番</option>
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
          <button class="btn bl-btn-panel bl-btn-5 btn_check_list">チェックリスト</button>
          <button class="btn bl-btn-panel bl-btn-4 outputGuide">出力</button>
        </div>
      </div>
    </div>

		<div id="grid"></div>	
		<div class="tab_grid">
			<div class="page_head" id="pPager">
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
				<div class="button">
					<button type="button"
						class="bl-btn-panel btn bl-btn-wiz-prev bl-btn-5 btn-back"
						onclick="back();">
						<span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>戻る
					</button>
					<button type="button"
						class="bl-btn-panel btn bl-btn-wiz-prev bl-btn-5" id="save">保存
					</button>
					<button type="button"
						class="bl-btn-panel btn bl-btn-wiz-prev bl-btn-5" id="certain">確定</button>
					<button type="button"
						class="bl-btn-panel btn bl-btn-wiz-prev bl-btn-5" id="cancle">取消
					</button>
					<button type="button"
						class="bl-btn-panel btn bl-btn-wiz-prev bl-btn-5 btn-new" id="add">新規追加</button>
				</div>
			</div>
		</div>
	</div>
	    <%@ include file="../common/footer.jsp"%>
    
	<!--lib js-->
	<script type="text/javascript">            
            window.baseurl = "${baseurl}";
    </script>
	<script type="text/javascript"
		src="${baseurl}/lib/jquery/jquery-2.2.0.min.js"></script>
	<script type="text/javascript"
		src="${baseurl}/lib/jquery.mockjax/jquery.mockjax.min.js"></script>
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
	<!--common js-->
	<script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/parts/header.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/applyperiodguide/applyperiodguide.js"></script>
	<!--personal js-->
	<script type="text/javascript"
		src="${baseurl}/js/goodslist/goods.js"></script>
	<script type="text/javascript"
		src="${baseurl}/js/goodsdetail/showgoodsdetail.js"></script>	
    <script src="${baseurl}/js/common/common.js" type="text/javascript"></script> 
	<script type="text/javascript" src="${baseurl}/lib/jszip/jszip.min.js"></script>
    <script type="text/javascript" src="${baseurl}/js/changecommon/show_changeCommon.js"></script>
    <script type="text/javascript" src="${baseurl}/js/classifycodeguide/showClassifyCodeGuide.js"></script>
	<script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/jquery.pagination.min.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/jquery.alphanum.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/inputFormat.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/download/download.js"></script>
	<script type="text/javascript" src="${baseurl}/js/common/check.js"></script>

</body>
</html>