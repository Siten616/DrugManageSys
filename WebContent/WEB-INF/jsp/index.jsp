<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>    
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<!-- css -->
		<script type="text/javascript" src="${basepath}/js/jquery-3.5.1.js"></script>
		<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
		<link rel="stylesheet" href="${basepath}/css/bootstrap.css" />

		<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
		<link rel="stylesheet" href="${basepath}/css/bootstrap-theme.min.css">

		<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
		<script src="${basepath}/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		 crossorigin="anonymous"></script>
	</head>
	<style>
		.*{
			width: 100%;
			height: 100%;

			margin: 0px;
		}

		#main {
			width: 100%;
			height: 1000px;
			 /* background-color: #D0E9C6; */
			display: flex;
			flex-wrap: wrap;
			flex-direction: row;
			justify-content: start;
		}

		.top-head {
			width: 100%;
			height: 10%;
			/* background: orange;*/
			display: flex;
			flex-wrap: no-wrap;
			
		}

		.left-menu {
			width: 24%;
			height: 90%;
			/*background:yellow;*/
		}

		.right-content {
			width: 76%;
			height: 90%;
			/*   background:blue;*/
		}

		#logo {
			width: 24%;
			height: 100%;
			line-height: 80px;
			text-align: center;
			background-color: #4CAE4C;
			/*background:purple;*/
		}

		#title {
			width: 60%;
			font-size: 28px;
			text-align: center;
			line-height: 90px;
			background:#C8E5BC;
		}

		#per {
			width: 16%;
			height: 100%;
			line-height: 90px;
			background-color: #C8E5BC;
		}

		li {
			list-style: none;
			padding-top: 30px;
			text-align: center;
		}

		a {
			text-decoration: none;
			padding: 5px;
			border-radius: 20%;
			color: #F8EFC0;
			width: 70%;
		}

		a:focus,
		a:hover {
			color: #F5E79E;
			text-decoration: none;
			
		}

		li:hover {
			list-style: none;
		}
		
		scan{
			font-weight: 600;
			color: #000000;
		}
		
		
		
	</style>
	<body>
		<div id="main">
			<nav class="top-head" style="background-color: #C8E5BC">
				<div id="logo">
					<div style="width: 50%;height: 50% ;background-color: #B2DBA1; margin: auto; line-height: 45px">
					您好，<c:choose><c:when test="${not empty user }">${user.username}</c:when></c:choose>
					<p><scan><a href="${basepath}/path/toLogin" style="color: black">退出登录</a></scan></p>
					</div>
				</div>
				<div id="title">药品后台管理系统</div>
				<div id="per"> </div>

			</nav>
			<div class="left-menu" style="background-color: #C1E2B3;">
				<!-- 侧边栏标签页 -->
				<ul class="nav nav-stacked ">
					<!-- 侧边栏选项 -->
					<li role="presentation" class="active">
						<!-- 选项控制data-target对应的显隐 -->
						<scan>管理员</scan>
						<!-- 隐藏的详细菜单 -->
						<ul id="list" class="collapse in" style="text-align: left;">
							<li class="active">
								<a href="javascript:void(0)" id="showAllDrug" class="btn btn-success">
									显示所有药品
								</a>
							</li>
							<li class="active">
								<a href="javascript:void(0)" id="addDrug" class="btn btn-success">
									增加/更新一个药品
								</a>
							</li>
							<li class="active">
								<a href="javascript:void(0)"  id="deleteDrug" class="btn btn-success">
									删除一个药品
								</a>
							</li>
							<li class="active">
								<a href="javascript:void(0)"  id="addUser" class="btn btn-success">
									添加操作员/管理员
								</a>
							</li>
						</ul>
					</li>
					<li role="presentation" class="active">
						<scan>系统公告</scan>
						<p>医院。。。</p>

					</li>
				</ul>
			</div>
			<div class="right-content" style="background-image:url(images/呼伦贝尔河道.jpg);background-size: cover;">
				<!-- ------------------------------------------------------------------------------------------------------------- -->
				<div class="col-lg-8" id="rcontent" style="margin-top: 50px;margin-left: 100px;">
						<!--  填入内容-->	
				
				</div>
				
			
				<input type="hidden" id="basepath" value="${basepath}"/>
			</div>
		</div>
		
		<script type="text/javascript">
		
			$(function(){
				var path=$("#basepath").val();
				//点击显示全部药品按钮
				$("#showAllDrug").click(function() {
					$.ajax({
						url:path+"/drugServlet",
						type:"GET",
						data:"curPage=1",
						success:function(rtn){
							var page=$.parseJSON(rtn);
							rtn=$.parseJSON(rtn).list;
							var str="<table class='table' style='float:left; width:85%; font-weight:600;font-size:17px;'>"+
									"<tr>"+
										"<td>编号</td>"+
										"<td>药品名称</td>"+
										"<td>地址</td>"+
										"<td>生产日期</td>"+
										"<td>截至日期</td>"+
										"<td>单价</td>"+
										"<td>库存量</td>"+
										"<td>数量提示</td>"+
									"</tr>";
							for(var i=0;i<rtn.length;i++){
								var drug=rtn[i];
								var content=
								"<tr>"+
									"<td>"+drug.did+"</td>"+
									"<td>"+drug.dname+"</td>"+
									"<td>"+drug.address+"</td>"+
									"<td>"+drug.production_date+"</td>"+
									"<td>"+drug.deadline+"</td>"+
									"<td>"+drug.price+"</td>"+
									"<td>"+drug.stock+"</td>"+
								"</tr>";
								str+=content;
								
							}
							str+="</table>";
							str+="<nav aria-label='Page navigation' style='text-align: center;float:right;'>"+
								"<ul class='pagination ul' id='pageCodeUl'>"+
									"<li ><a id='prePage' aria-label='Previous'>"+
									"<span aria-hidden='true'>&laquo;</span></a></li>";
									for(var i=0;i<page.pageList.length;i++){
										var pageNum=page.pageList[i];
										if(pageNum==page.curPage){
											str+= "<li  class='active'>"+
												"<a class='clickPage'>"+pageNum+"</a>"+
											"</li>";
										}else{
											str+="<li>"+
											"<a class='clickPage'>"+pageNum+"</a>"+
											"</li>";
										}
									}
									 str+="<li ><a id='nextPage' aria-label='Nextvious'>"+
									"<span aria-hidden='true'>&raquo;</span></a></li>"+
								"</ul>"+
							"</nav>";
							//分页
							$("#rcontent").html(str);
	        			},
	        			error:function(){
	        				
	        			}
					})
				});
				//处理翻页
				$("#rcontent").on("click",'#prePage',function(){
					var ul=$(".ul");
					var child=ul.children("li");
					var curPage=1;
					for(var i=1;i<child.length;i++){
						if(child.eq(i).hasClass("active")){
							curPage=child.eq(i).children("a").text();
							break;
						}
					}
					$.ajax({
						url:path+"/drugServlet",
						type:"GET",
						data:"curPage="+(parseInt(curPage)-1),
						success:function(rtn){
							//分页
	        				//上一页
							var page=$.parseJSON(rtn);
							rtn=$.parseJSON(rtn).list;
							var str="<table class='table' style='float:left; width:85%;font-weight:600;font-size:17px;'>"+
									"<tr>"+
										"<td>编号</td>"+
										"<td>药品名称</td>"+
										"<td>地址</td>"+
										"<td>生产日期</td>"+
										"<td>截至日期</td>"+
										"<td>单价</td>"+
										"<td>库存量</td>"+
										"<td>数量提示</td>"+
									"</tr>";
							for(var i=0;i<rtn.length;i++){
								var drug=rtn[i];
								var content=
								"<tr>"+
									"<td>"+drug.did+"</td>"+
									"<td>"+drug.dname+"</td>"+
									"<td>"+drug.address+"</td>"+
									"<td>"+drug.production_date+"</td>"+
									"<td>"+drug.deadline+"</td>"+
									"<td>"+drug.price+"</td>"+
									"<td>"+drug.stock+"</td>"+
								"</tr>";
								str+=content;
								
							}
							str+="</table>";
							str+="<nav aria-label='Page navigation' style='text-align: center;float:right;'>"+
								"<ul class='pagination ul' id='pageCodeUl'>"+
									"<li ><a  id='prePage' aria-label='Previous'>"+
									"<span aria-hidden='true'>&laquo;</span></a></li>";
									for(var i=0;i<page.pageList.length;i++){
										var pageNum=page.pageList[i];
										if(pageNum==page.curPage){
											str+= "<li  class='active'>"+
												"<a class='clickPage'>"+pageNum+"<span class='sr-only'></span></a>"+
											"</li>";
										}else{
											str+="<li>"+
											"<a class='clickPage'>"+pageNum+"<span class='sr-only'></span></a>"+
											"</li>";
										}
									}
									 str+="<li ><a id='nextPage' aria-label='Nextvious'>"+
									"<span aria-hidden='true'>&raquo;</span></a></li>"+
								"</ul>"+
							"</nav>";
							//分页
							$("#rcontent").html(str);
	        			},
						error:function(){
							
						}
				})
				})
				//下一页
				$("#rcontent").on("click",'#nextPage',function(){
					var ul=$(".ul");
					var child=ul.children("li");
					var curPage=1;
					for(var i=1;i<child.length;i++){
						if(child.eq(i).hasClass("active")){
							curPage=child.eq(i).children("a").text();
							break;
						}
					}
					$.ajax({
						url:path+"/drugServlet",
						type:"GET",
						data:"curPage="+(parseInt(curPage)+1),
						success:function(rtn){
							//分页
	        				//上一页
							var page=$.parseJSON(rtn);
							rtn=$.parseJSON(rtn).list;
							var str="<table class='table' style='float:left; width:85%;font-weight:600;font-size:17px;'>"+
									"<tr>"+
										"<td>编号</td>"+
										"<td>药品名称</td>"+
										"<td>地址</td>"+
										"<td>生产日期</td>"+
										"<td>截至日期</td>"+
										"<td>单价</td>"+
										"<td>库存量</td>"+
										"<td>数量提示</td>"+
									"</tr>";
							for(var i=0;i<rtn.length;i++){
								var drug=rtn[i];
								var content=
								"<tr>"+
									"<td>"+drug.did+"</td>"+
									"<td>"+drug.dname+"</td>"+
									"<td>"+drug.address+"</td>"+
									"<td>"+drug.production_date+"</td>"+
									"<td>"+drug.deadline+"</td>"+
									"<td>"+drug.price+"</td>"+
									"<td>"+drug.stock+"</td>"+
								"</tr>";
								str+=content;
								
							}
							str+="</table>";
							str+="<nav aria-label='Page navigation' style='text-align: center;float:right;'>"+
								"<ul class='pagination ul' id='pageCodeUl'>"+
									"<li ><a  id='prePage' aria-label='Previous'>"+
									"<span aria-hidden='true'>&laquo;</span></a></li>";
									for(var i=0;i<page.pageList.length;i++){
										var pageNum=page.pageList[i];
										if(pageNum==page.curPage){
											str+= "<li  class='active'>"+
												"<a class='clickPage'>"+pageNum+"<span class='sr-only'></span></a>"+
											"</li>";
										}else{
											str+="<li>"+
											"<a class='clickPage'>"+pageNum+"<span class='sr-only'></span></a>"+
											"</li>";
										}
									}
									 str+="<li ><a id='nextPage' aria-label='Nextvious'>"+
									"<span aria-hidden='true'>&raquo;</span></a></li>"+
								"</ul>"+
							"</nav>";
							//分页
							$("#rcontent").html(str);
	        			},
						error:function(){
							
						}
				})
				})
				$("#rcontent").on("click",'.clickPage',function() {
					var pageNum=$(this).text();
					$.ajax({
						url:path+"/drugServlet",
						type:"GET",
						data:"curPage="+pageNum,
						success:function(rtn){
							//分页
	        				//上一页
							var page=$.parseJSON(rtn);
							rtn=$.parseJSON(rtn).list;
							var str="<table class='table' style='float:left; width:85%;font-weight:600;font-size:17px;'>"+
									"<tr>"+
										"<td>编号</td>"+
										"<td>药品名称</td>"+
										"<td>地址</td>"+
										"<td>生产日期</td>"+
										"<td>截至日期</td>"+
										"<td>单价</td>"+
										"<td>库存量</td>"+
										"<td>数量提示</td>"+
									"</tr>";
							for(var i=0;i<rtn.length;i++){
								var drug=rtn[i];
								var content=
								"<tr>"+
									"<td>"+drug.did+"</td>"+
									"<td>"+drug.dname+"</td>"+
									"<td>"+drug.address+"</td>"+
									"<td>"+drug.production_date+"</td>"+
									"<td>"+drug.deadline+"</td>"+
									"<td>"+drug.price+"</td>"+
									"<td>"+drug.stock+"</td>"+
								"</tr>";
								str+=content;
							}
							str+="</table>";
							str+="<nav aria-label='Page navigation' style='text-align: center;float:right;'>"+
								"<ul class='pagination ul' id='pageCodeUl'>"+
									"<li ><a  id='prePage' aria-label='Previous'>"+
									"<span aria-hidden='true'>&laquo;</span></a></li>";
									for(var i=0;i<page.pageList.length;i++){
										var pageNum=page.pageList[i];
										if(pageNum==page.curPage){
											str+= "<li  class='active'>"+
												"<a class='clickPage'>"+pageNum+"<span class='sr-only'></span></a>"+
											"</li>";
										}else{
											str+="<li>"+
											"<a class='clickPage'>"+pageNum+"<span class='sr-only'></span></a>"+
											"</li>";
										}
									}
									 str+="<li ><a id='nextPage' aria-label='Nextvious'>"+
									"<span aria-hidden='true'>&raquo;</span></a></li>"+
								"</ul>"+
							"</nav>";
							//分页
							$("#rcontent").html(str);
	        			},
						error:function(){
							
						}
				})
				} )
				//点击添加药品按钮
				$("#addDrug,#updateDrug").click(function(){
					var rcontent="添加药品"+
					"<form action='"+path+"/addDrugServlet'>"+
						"<p><scan>请输入药品数据</scan></p>"+
						"<p><scan>药品编码：</scan><input type='text' required='required' name='did' id='did' class='form-control-static textNotNull'></p>"+
						"<p><scan>药品名称：</scan><input type='text' required='required' name='dname' id='dname' class='form-control-static textNotNull'></p>"+
						"<p><scan>药品厂家：</scan><input type='text' required='required' name='address' id='address' class='form-control-static textNotNull'></p>"+
						"<p><scan>生产日期：</scan><input type='date' required='required' name='production_date' id='production_date' class='form-control-static textNotNull'></p>"+
						"<p><scan>截至日期：</scan><input type='date' required='required' name='deadline' id='deadline' class='form-control-static textNotNull'></p>"+
						"<p><scan>药品单价：</scan><input type='text' required='required' name='price' id='price' class='form-control-static textNotNull'></p>"+
						"<p><scan>药品数量：</scan><input type='text' name='num' id='num' class='form-control-static'></p>"+
						"<p><scan>药品库存：</scan><input type='text' required='required' name='stock' id='stock' class='form-control-static textNotNull'></p>"+
						"<p><input type='submit'  value='加入/更新' /></p>"+
					"</form>";
					$("#rcontent").html(rcontent);
				});
				//添加文本框光标失去事件
				$("#rcontent").on("blur",'#did',function() {
					var did= $("#did").val();
					$.ajax({
						url:path+"/blurDidDrug",
						type:"GET",
						data:"did="+did,
						success:function(rtn){
							rtn=$.parseJSON(rtn);
							if(rtn!=null){
								$("#dname").val(rtn.dname)
								$("#address").val(rtn.address)
								$("#production_date").val(rtn.production_date)
								$("#deadline").val(rtn.deadline)
								$("#price").val(rtn.price)
								$("#stock").val(rtn.stock)
							}else{
								$("#dname").val("")
								$("#address").val("")
								$("#production_date").val("")
								$("#deadline").val("")
								$("#price").val("")
								$("#stock").val("")
							}
						},
						error:function(){
							alert("失败")
						}
					});
				})
				
				//点击删除药品按钮
				$("#deleteDrug").click(function(){
					var rcontent="删除药品"+
						"<p><scan>药品ID：</scan><input type='text' id='deleteDrugId' required='required' class='form-control-static'></p>"+
						"<p><input type='button' id='showDrug' value='显示' /></p>";
					
					$("#rcontent").html(rcontent);
				});
				
				$("#rcontent").on("click",'#showDrug',function(){
					var did= $("#deleteDrugId").val();
					$.ajax({
						url:path+"/blurDidDrug",
						type:"GET",
						data:"did="+did,
						success:function(rtn){
							rtn=$.parseJSON(rtn);
							if(rtn!=null){
								var rcontent=
									"<p><scan>药品编码：</scan><input type='text' required='required' name='did' id='did' class='form-control-static textNotNull' value='"+rtn.did+"' ></p>"+
									"<p><scan>药品名称：</scan><input type='text' readonly='readonly' name='dname' id='dname' class='form-control-static textNotNull' value='"+rtn.dname+"'></p>"+
									"<p><scan>药品厂家：</scan><input type='text' readonly='readonly' name='address' id='address' class='form-control-static textNotNull' value='"+rtn.address+"'></p>"+
									"<p><scan>生产日期：</scan><input type='date' readonly='readonly' name='production_date' id='production_date' class='form-control-static textNotNull' value='"+rtn.production_date+"'></p>"+
									"<p><scan>截至日期：</scan><input type='date' readonly='readonly' name='deadline' id='deadline' class='form-control-static textNotNull' value='"+rtn.deadline+"'></p>"+
									"<p><scan>药品单价：</scan><input type='text' readonly='readonly' name='price' id='price' class='form-control-static textNotNull' value='"+rtn.price+"'></p>"+
									"<p><scan>药品库存：</scan><input type='text' readonly='readonly' name='stock' id='stock' class='form-control-static textNotNull' value='"+rtn.stock+"'></p>"+
									"<input type='button' id='deleteDrug' value='删除药品'>";
									$("#rcontent").html(rcontent);
							}else{
								alert("药品不存在");
							}
						},
						error:function(){
							alert("失败")
						}
					});
				})
				
				$("#rcontent").on("click","#deleteDrug",function(){
					var did=$("#did").val();
					$.ajax({
						url:path+"/deleteDrug",
						type:"GET",
						data:"did="+did,
						success:function(rtn){
							if(rtn=="success"){
								alert("删除成功");
								$("#did").val("");
								$("#dname").val("");
								$("#address").val("");
								$("#production_date").val("");
								$("#deadline").val("");
								$("#price").val("");
								$("#stock").val("");
							}
							else if(rtn=="error")
								alert("删除失败");
							
						},
						error:function(){
							
						}
					})
					
				})
				//注册，增加管理员或操作员
				$("#addUser").click(function(){
					var rcontent=
					"<label for='InputUsername1'>用户名</label>"+
					"<input type='text' name='username' required='required'  class='form-control' id='InputUsername1' placeholder='用户名'/>"+
					"<label for='InputPassword1'>密码</label>"+
					"<input type='password'  name='password' required='required'  class='form-control' id='InputPassword1' placeholder='密码'/>"+
					"<select name='type' id='type'>"+
						"<option value='operator'>操作员</option>"+
						"<option value='admin'>管理员</option>"+
					"</select>"+
					"<p><button  id='btn_register' class='btn btn-default'>注册</button></p>";
					$("#rcontent").html(rcontent);
				})
				
				$("#rcontent").on("click",'#btn_register',function(){
					var username=$("#InputUsername1").val();
					var password=$("#InputPassword1").val();
					var type=$("#type").val();
					if(username!=null&&password!=null){
					$.ajax({
						url:path+"/addUser",
						type:"GET",
						data:"username="+username+"&password="+password+"&type="+type,
						success:function(rtn){
							if(rtn=="success")
								alert("注册成功");
							else if(rtn=="error")
								alert("注册失败");
						},
						error:function(){
							alert("注册失败");
						}
					})
					}else{
						alert("用户名或密码不能为空")
					}
				})
				
				
				
			})
			
					
			
		</script>
		
	</body>

</html>
