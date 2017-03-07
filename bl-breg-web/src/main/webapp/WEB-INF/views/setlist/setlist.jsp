<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
<title>セット</title>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- common css -->
<link rel="stylesheet" href="${baseurl}/lib/reset/reset.css">
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/lib/wijmo5/styles/wijmo.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<!-- 個別css -->
<link rel="stylesheet" href="${baseurl}/css/setlist/setlist.css">
<title>セット</title>
</head>
<body>
  <jsp:include page="../common/header.jsp" />
  <div class="page-header ">
    <h2>セット</h2>
  </div>

  <div class="container">
    <div class="panel panel-primary slide-panel search-scope flow-x ">

      <div class="panel-heading head">
        <h3 class="panel-title">検索条件</h3>

      </div>
      <div class="panel-body panel-row search-panel ">
        <div class="input-group bl-cd  column1">
          <span class="input-group-addon "><b>BLコード</b></span> <select name="tbsPartsCode" class="form-control">
            <option></option>
            <c:forEach items="${blCode}" var="item">
              <option value="${item.blCode}">${item.blCode}：${item.blFullName}</option>
            </c:forEach>
          </select>
        </div>
        <div class="input-group set-parent-cd column2">
          <span class="input-group-addon "><b>セット親品番</b></span> <input type="text" id="setMainPartsNo" name="setMainPartsNo" 
          oninput="charAndBarInput(this.id)" class="form-control" maxlength="24" />
        </div>

        <div class="input-group item-name  column3">
          <span class="input-group-addon "><b>品名（半角）</b></span> <input type="text" id="setKanaName" name="setKanaName"
            class="form-control" maxlength="60" />
        </div>

        <div class="input-group apply-status  column1">
          <span class="input-group-addon "><b>申請状態</b></span> <select id="applyCondition" name="applyCondition" class="form-control">
            <option value="">全データ表示</option>
            <c:forEach items="${applyCondition}" var="item">
              <option value="${item.value}">${item.name}</option>
            </c:forEach>
          </select>
        </div>
        <div class="input-group set-child-cd column2">
          <span class="input-group-addon "><b>セット子品番</b></span> <input type="text" id="setSubPartsNo" name="setSubPartsNo" 
          oninput="charAndBarInput(this.id)" class="form-control" maxlength="24" />
        </div>


        <div class="input-group apply-date  column3">
                  <span class="input-group-addon "><b>適用日</b></span> <input name="startTimeStart"
                    class=" form-control date-input text-right" maxlength="10" /> <span class="input-group-addon between">～</span><input
                    name="startTimeEnd" class=" form-control date-input text-right" maxlength="10" />
                </div>

        <div class="input-group select-cd init-hide column1">
          <span class="input-group-addon ">セレクトコード</span> <select name="prmSetDtlNo1" class="form-control">
            <option></option>
            <c:forEach items="${selectCode}" var="item">
              <option value="${item.prmSetDtlNo1}">${item.prmSetDtlNo1}：${item.prmSetDtlName}</option>
            </c:forEach>
          </select>
        </div>

        <div class="input-group del-distinction init-hide column2">
          <span class="input-group-addon "><b>削除依頼区分</b></span> <select name="deleteFlg" class="form-control">
            <option value="">全データ表示</option>
            <c:forEach items="${deleteFlg}" var="item">
              <option value="${item.value}">${item.name}</option>
            </c:forEach>
          </select>
        </div>

        <div class="input-group update-date init-hide column3">
          <span class="input-group-addon "><b>更新日</b></span> <input name="updDtTimeStart"
            class=" form-control date-input text-right" maxlength="10" /> <span class="input-group-addon between">～</span>
          <input name="updDtTimeEnd" class=" form-control date-input text-right" maxlength="10" />
        </div>

        <div class="input-group classify-cd init-hide column1">
          <span class="input-group-addon ">分類コード</span> <input id="goodsMGroup" type="text" name="goodsMGroup"
            class="form-control" maxlength="4" value="" oninput="numInput(this.id)" />
          <button type="button" class="glyphicon glyphicon-star-empty btn btn-default guide classifycdguide"
            name="classifyCdGuide"></button>
        </div>

        <div class="input-group special-matters init-hide column2">
          <span class="input-group-addon "><b>規格/特記</b></span> <input type="text" id="setSpecialNote" name="setSpecialNote"
            class="form-control" maxlength="80" />
        </div>

       <div class="input-group insert-date init-hide  column3">
                 <span class="input-group-addon "><b>作成日</b></span> <input name="insDtTimeStart"
                   class=" form-control date-input text-right" maxlength="10" /> <span class="input-group-addon between">～</span><input
                   name="insDtTimeEnd" class=" form-control date-input text-right" maxlength="10" />
               </div>

        <div class="input-group process-distinction  column1">
          <span class="input-group-addon "><b>処理区分</b></span> <select name="manageKbn" class="form-control">
            <option value="">全データ表示</option>
            <c:forEach items="${manageKbn}" var="item">
              <option value="${item.value}">${item.name}</option>
            </c:forEach>
          </select>
        </div>

        <div class="input-group error-distinction  column2">
          <span class="input-group-addon "><b>エラー区分</b></span> <select name="errorFlg" class="form-control">
            <option value="">全データ表示</option>
            <c:forEach items="${errorFlg}" var="item">
              <option value="${item.value}">${item.name}</option>
            </c:forEach>
          </select>
        </div>

        <div class="empty-inline"></div>

        <div class="group-button search-scope-buttons">
          <button class="btn bl-btn-panel bl-btn-3 btn-search">検索</button>
          <button class="btn bl-btn-panel bl-btn-3 btn-clear">クリア</button>
          <button class="btn bl-btn-panel bl-btn-4 detail-condition">詳細条件</button>
        </div>
      </div>
    </div>

    <div class="input-group sort">
      <span class="input-group-addon"><b>表示順</b></span> <select name="sort" class="form-control">
        <!--<option value=""></option>-->
        <option value="1">セレクトコード＞分類コード＞BLコード＞セット親品番＞表示順位</option>
        <option value="2">セレクトコード＞分類コード＞BLコード＞セット子品番</option>
      </select>
    </div>

    <p></p>
    <div>
      <div class="bl-flex-row grid-top-group-button">
        <div class="group-button ">
          <button class="btn bl-btn-panel bl-btn-4 btn-insert">行挿入</button>
          <button class="btn bl-btn-panel bl-btn-4 btn-del">行削除</button>
          <button class="btn bl-btn-panel bl-btn-4 btn-copy">行コピー</button>
          <button class="btn bl-btn-panel bl-btn-4 btn-paste">行貼付</button>
          <button class="btn bl-btn-panel bl-btn-4 btn-replaceGuide">検索・置換</button>
          <span class="space">&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <button class="btn bl-btn-panel bl-btn-4 btn-detail">詳細</button>

        </div>

        <div class="group-button ">
          <button class="btn bl-btn-panel bl-btn-5 btn-check-list">チェックリスト</button>
          <button class="btn bl-btn-panel bl-btn-4 btn-output">出力</button>
        </div>
      </div>
    </div>
    <div id="grid"></div>
    <div>
    <div class="page_head">
        <div>検索数/全件数:
        <span class="searchcount"></span>/
        <span class="totalcount"></span></div>
        <div class="page-box"></div>  
        <div class="page-info">
          <span>1ページに、</span>
          <span class="historyrows"></span>
          <span>件</span>
        </div>
      </div>
      </div>

    <!-- <div>
      <div class="page  bl-flex-row">
        <div class="totalNum">検索件/全件数:21/22</div>
        <div class="empty-inline"></div>
        <div class="previous-page">
          <a href="#">＜ 前のページ</a>
        </div>
        <div class="pageNo"></div>

        <div class="next-page">
          <a href="#">次のページ ＞</a>
        </div>
        <div class="pageNumber"></div>


      </div>
    </div> -->

    <div class="row">
      <div class="col-sm-12">
        <div class="text-center">
          <div class="group-button grid-bottom">
            <button type="button" class="btn bl-btn-panel bl-btn-5 bl-btn-wiz-prev btn-back">
              <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>戻る
            </button>
            <button type="button" class="btn bl-btn-panel bl-btn-5 btn-save">保存</button>
            <button type="button" class="btn bl-btn-panel bl-btn-5 btn-yes">確定</button>
            <button type="button" class="btn bl-btn-panel bl-btn-5 btn-cancel">取消</button>
            <button type="button" class="btn bl-btn-panel bl-btn-5 btn-new">新規追加</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <jsp:include page="../common/footer.jsp" />
  <!-- lib js -->
  <script type="text/javascript" src="${baseurl}/lib/jquery/jquery-2.2.0.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/moment/moment-with-locales.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/cultures/wijmo.culture.ja.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.grid.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.input.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.pdf.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.grid.pdf.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.xlsx.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/wijmo5/controls/wijmo.grid.xlsx.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/jszip/jszip.min.js"></script>
  <script type="text/javascript" src="${baseurl}/lib/uri/URI.min.js"></script>
  <!-- common js -->
  <script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
  <script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
  <script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
  <script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script>
  <script type="text/javascript" src="${baseurl}/js/common/parts/header.js"></script>
  <script type="text/javascript" src="${baseurl}/js/common/download/download.js"></script>
  <script type="text/javascript" src="${baseurl}/js/applyperiodguide/applyperiodguide.js"></script>
  <script type="text/javascript" src="${baseurl}/js/classifycodeguide/showClassifyCodeGuide.js"></script>
  <script type="text/javascript" src="${baseurl}/js/goodsguide/showGoodsGuide.js"></script>
  <script type="text/javascript" src="${baseurl}/js/setdetail/showSetdetail.js"></script>
  <script type="text/javascript" src="${baseurl}/js/changecommon/show_changeCommon.js"></script>
  <script type="text/javascript" src="${baseurl}/js/common/inputFormat.js"></script>
  <script type="text/javascript" src="${baseurl}/js/common/jquery.pagination.min.js"></script>
  <script type="text/javascript" src="${baseurl}/js/common/jquery.alphanum.js"></script>
  <script type="text/javascript" src="${baseurl}/js/common/check.js"></script>
  <!-- personal js -->
  <script type="text/javascript" src="${baseurl}/js/setlist/setlist.js"></script>
  <script type="text/javascript">
            window.baseurl = "${baseurl}";
        </script>
</body>
</html>