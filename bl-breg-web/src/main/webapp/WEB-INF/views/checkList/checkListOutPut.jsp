<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="bl" tagdir="/WEB-INF/tags/"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<!-- css -->
<link rel="stylesheet" href="${baseurl}/lib/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${baseurl}/css/common/common/common.css">
<link rel="stylesheet" href="${baseurl}/css/checklistoutput/check_list_output.css">
</head>

<body>
    <h2>
        <b>出力選択</b>
    </h2>
    <div class="become-small">
        <div class="button-group output">
            <div class="goods-con">
                <div class="select_group">
                    <label><input name="good_select_type" type="radio" value=""
                        checked="true">PDF出力</label> <label><input
                        name="good_select_type" type="radio" value="">TSV出力</label> <label><input
                        name="good_select_type" type="radio" value="">Excel出力</label>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript"
        src="${baseurl}/lib/jquery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript"
        src="${baseurl}/lib/bootstrap/bootstrap.min.js"></script>
</body>

</html>