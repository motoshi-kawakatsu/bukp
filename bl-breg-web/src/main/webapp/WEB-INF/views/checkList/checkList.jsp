<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>チェックリスト</title>

<link rel="stylesheet" href="${baseurl}/lib/reset/reset.css">
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/lib/wijmo5/styles/wijmo.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">

<!-- 個別css -->
<link rel="stylesheet" href="${baseurl}/css/checklist/check_list.css">
</head>
<!-- Body部 -->
<body>
    <jsp:include page="../common/header.jsp" />
    <div class="container">
        <div class="page-header">
            <h2>チェックリスト</h2>
        </div>
        <p>このリストにはエラーがあるデータのみが表示されています。</p>
        <div>
            <ul class="nav nav-tabs ulf" id="myTab">
                <li><a href="#item" data-toggle="tab" class="backbg"><strong>商品</strong></a>
                </li>
                <li><a href="#set" data-toggle="tab" class="backbg"><strong>セット</strong></a>
                </li>
                <li><a href="#union" data-toggle="tab" class="backbg"><strong>結合</strong></a>
                </li>
            </ul>
        </div>
        <div class="tab-content bl-flex-column">
            <div class="tab-pane" id="item"></div>
            <div class="tab-pane" id="set"></div>
            <div class="tab-pane" id="union"></div>
        </div>
        <div class="tab_grid">
            <div class="page_head">
                <div class="page-box"></div>
                <div class="page-info">
                    <span>1ページに、</span> <span class="historyrows"></span> <span>件</span>
                </div>
            </div>
        </div>

        <div class="button">
            <button type="button" class="bl-btn-panel bl-btn-5 btn btn-back">戻る</button>
            <button type="button"
                class="bl-btn-panel bl-btn-5 btn classifyCdGuide">出力</button>
        </div>
    </div>
    <jsp:include page="../common/footer.jsp" />

    <script type="text/javascript">
        window.baseurl = "${baseurl}";
    </script>
    <!--lib js-->
    <script type="text/javascript"
        src="${baseurl}/lib/jquery/jquery-2.2.0.min.js"></script>
    <script type="text/javascript"
        src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript"
        src="${baseurl}/lib/moment/moment-with-locales.min.js"></script>
    <script type="text/javascript" src="${baseurl}/lib/uri/URI.min.js"></script>
    <script type="text/javascript" src="${baseurl}/lib/layer/layer.js"></script>
    <script type="text/javascript"
        src="${baseurl}/lib/wijmo5/controls/wijmo.min.js"></script>
    <script type="text/javascript"
        src="${baseurl}/lib/wijmo5/controls/wijmo.input.min.js"></script>
    <script type="text/javascript"
        src="${baseurl}/lib/wijmo5/controls/wijmo.grid.min.js"></script>
    <script type="text/javascript"
        src="${baseurl}/lib/wijmo5/controls/cultures/wijmo.culture.ja.min.js"></script>
    <!-- common  -->
    <script type="text/javascript" src="${baseurl}/js/common/base/base.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/base/code.js"></script>
    <script type="text/javascript"
        src="${baseurl}/js/common/parts/header.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/parts/menu.js"></script>
    <script type="text/javascript" src="${baseurl}/js/common/common.js"></script>
    <script type="text/javascript"
        src="${baseurl}/js/common/download/download.js"></script>
    <script type="text/javascript"
        src="${baseurl}/js/common/jquery.pagination.min.js"></script>
    <!-- 画面用のjs -->
    <script type="text/javascript"
        src="${baseurl}/js/checklist/checklist.js"></script>
    <script type="text/javascript"
        src="${baseurl}/js/checklistoutput/check_list_output.js"></script>

</body>
</html>