<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"   %>

<!DOCTYPE html>
<html lang="zh-CN">
	<head>	
		<title>首页</title>
	</head>
	<body>
	    <div class="backgroundImage">
		<!-- 容器,留白 -->
		<div class="container main">
		<!--    包含 -->
			 <%@include file="header.jsp" %>

		</div>
		
			<!-- 商品板块 -->
			<div class="row " style="margin:50px auto 0px; width: 80%" id="goodDiv">
			<!--  域对象中是页面,页面里面的列表内容,是商品列表-->
			    <c:forEach  items="${page.list}" var="drug">
				<!-- 平板 -->
				<div class='col-sm-6 col-md-3'>
					<div class='thumbnail'>
						<div class='caption' style='text-align: center;'>
							<a><h3>${drug.dname }</h3></a>
							<p>${drug.address }</p>
							<p>价格:${drug.price }元</p>
							<p>库存:${drug.stock }</p>
							<p><a href='${basepath}/addCar?did=${drug.did}&num=1' class='btn btn-primary' role='button'>加入购物车</a>
								<a href='${basepath}/addOrder?did=${drug.did}' class='btn btn-default' role='button'>立即购买</a></p>
						</div>
					</div>
				</div>
               </c:forEach> 
			</div>
			<!-- 分页 -->
			<nav aria-label="Page navigation" style="text-align: center;">
				<ul class="pagination pagination-lg">
					<li ><a href="${basepath}/operatorPage?curPage=${page.prePage}" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span></a></li>
					 <c:forEach items="${page.pageList}" var="pageNum">
					 <!-- 绘制的页面是否和当前页一致 -->
					 <c:choose>
					  <c:when test="${pageNum==page.curPage}">
					   <li  class="active">
						<a href="${basepath}/operatorPage?curPage=${pageNum}">${pageNum}</a>
					</li>
					  </c:when>
					  <c:otherwise>
					  <li  >
						<a href="${basepath}/operatorPage?curPage=${pageNum}">${pageNum}</a>
					</li>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<!-- 下一页 -->
					 <li ><a href="${basepath}/operatorPage?curPage=${page.nextPage}" aria-label="Nextvious">
					<span aria-hidden="true">&raquo;</span></a></li>
				</ul>
			</nav>
			
		</div>
		</div>
	</body>
</html>
    