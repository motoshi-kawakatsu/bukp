<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<header class="bl-base-layout-header system-header">
		<div class="title col-lg-3">
			<h4>
				<img src="${baseurl}/imgs/logo_broadleaf.gif" alt="Broadleaf"
					class="bllogo"><br>カーパーツマネージャー
			</h4>
		</div>
		<div class="pull-right col-lg-2 guest">
			<div class="text-center guest-profile" id="guestName">
				<b class="glyphicon glyphicon-user guest-pic"></b>
				<a href="#" class="tantoshaJhoho link">
				<span class="companyName"></span>　<span class="accountName"></span>　様
				</a>
				<b class="glyphicon glyphicon-chevron-down"></b>
			</div>
			<div class="guest-content text-center" id="logOut">ログアウト</div>
		</div>
	</header>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="topMenuMode navbar-brand link" href="#" id="topPage">トップページ</a>
			</div>
			<div>
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">商品情報管理<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#" class="ShohinMode link">商品</a></li>
							<li class="divider"></li>
							<li><a href="#" class="SetMode link">セット</a></li>
							<li class="divider"></li>
							<li><a href="#" class="KetsugouMode link">結合</a></li>
							<li class="divider"></li>
							<li><a href="#" class="importIkatsu link">インポート（一括申請）</a></li>
							<li class="divider"></li>
							<li><a href="#" class="importPage link">インポート</a></li>
							<li class="divider"></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">申請処理<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#" class="checkListMode link">チェックリスト</a></li>
							<li class="divider"></li>
							<li><a href="#" class="shinseiIpan link">申請（一般）</a></li>
							<li class="divider"></li>
							<li><a href="#" class="shinseiShinkiHimoku link">申請（新規品目）</a></li>
							<li class="divider"></li>
							<li><a href="#" class="shinseiRirekiHe link">申請履歴</a></li>
							<li class="divider"></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">設定<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#" class="kaishaJhoho link">会社情報</a></li>
							<li class="divider"></li>
							<li><a href="#" class="tantoshaJhoho link">担当者情報</a></li>
							<li class="divider"></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<script type="text/javascript">
		window.baseurl = "${baseurl}";
	</script>
</body>
</html>