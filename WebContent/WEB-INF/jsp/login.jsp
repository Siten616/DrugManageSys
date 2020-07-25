<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- 编码集-->
		<meta charset="utf-8">
		<!-- 响应式设置 -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>登录</title>

		<!-- Bootstrap -->
		<link href="${basepath}/css/bootstrap.min.css" rel="stylesheet">

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

		<style>
		
		
		.fo{
			margin-top: 5%;
		}
		</style>
	</head>
	<body>
		<div style="width: 100%;height: 1000px; background-image: url(${basepath}/images/Arbaroj_kaj_lagoj.jpeg);background-size:cover;">
			
		<p style="text-align: center;line-height:200px;font-size: 40px;"><b>药品管理系统</b></p>
		<div class="col-lg-3"></div>
		<div class="col-lg-6 fo" style="background-color: #5CB85C; border-radius: 10%; padding: 20px;opacity:0.9;">
			<form action="${basepath}/login" method="post">
				<div class="form-group">
					<label for="InputUsername1">用户名</label>
					<input type="text" name="username" class="form-control" id="InputUsername1" placeholder="用户名">
				</div>
				<div class="form-group">
					<label for="InputPassword1">密码</label>
					<input type="password"  name="password" class="form-control" id="InputPassword1" placeholder="密码">
				</div>
				<div class="form-group">
					<select name="type">
						<option value="operator">操作员</option>
						<option value="admin">管理员</option>
					</select>
					<label id="error" style="color: red">${error}</label>
				</div>
				<p>
					<button type="submit" class="btn btn-default">登录</button>
				</p>
				
			</form>
		</div>
		</div>
	</body>
</html>
