<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 响应式设置 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>Insert title here</title>
<!--  以后整个页面的资源文件都加上服务器的项目名-->

<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/carts.css">
<!-- Bootstrap -->
<!--  动态的获得项目路径 -->
<link href="${basepath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${basepath}/css/main.css" rel="stylesheet">
<!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
<!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
<!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="${basepath}/js/jquery-3.5.1.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="${basepath}/js/bootstrap.min.js"></script>
<style type="text/css">
.mark{
	background-image: url("../images/mark1.png");
}
.form
</style>
</head>
<body >
	<!-- 菜单 -->
	<nav class="navbar navbar-default navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${basepath}/operatorIndex">首页</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
			
				<form action="${basepath}/searchDrug" class="navbar-form navbar-left"  style="margin:8px auto 0;">
					<div class="form-group">
						<input type="text" required="required" class="form-control" name="dname" placeholder="搜索">
					</div>
					<button type="submit"  id="searchDrug" class="btn btn-default">搜索药品</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">${user.username} <span class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href="${basepath}/carList"><span
											class="glyphicon glyphicon-shopping-cart mycart"></span>&nbsp;&nbsp;待出库列表</a></li>
									<li><a href="${basepath}/orderList"><span
											class="glyphicon glyphicon-align-center mycart"></span>&nbsp;&nbsp;出库历史</a></li>
									<li><a href="${basepath}/path/toLogin"><span
											class="glyphicon glyphicon-share-alt mycart"></span>&nbsp;&nbsp;退出登录</a></li>
								</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

</body>
</html>