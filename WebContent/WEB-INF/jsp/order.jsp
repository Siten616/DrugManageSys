<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"   %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
    content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
    <meta name="author" content="js代码" />
    <meta name="copyright" content="js代码" />

</head>
<body style="width: 80%;margin: 0px auto;">
   <%@include file="header.jsp" %> 
    <section class="cartMain">
   
    <!--    订单 -->
    <c:forEach items="${page.list}" var="order">
       <div class="cartBox">
            <div class="shop_info">
                
                <div class="shop_name">
                  订单号：<a href="javascript:;">${order.oid }</a>&nbsp;&nbsp;&nbsp;&nbsp;
                  购买时间：<a href="javascript:;">${order.createTime }</a>
                </div>
            </div>
            <div class="order_content">

             
             <c:forEach items="${order.details }" var="drug">
                   <ul class="order_lists">
                   
                    <li class="list_con">
                        <div class="list_text"><a href="javascript:;">${drug.dname}</a></div>
                    </li>
                    <li class="list_info">
                        <p>${drug.address}</p>
                    </li>
                    <li class="list_price">
                        <p class="price">￥${drug.price}</p>
                      
                    </li>
                    <li class="list_amount">
                        <div class="amount_box">
                            <p class="sum_price">${drug.num}</p>
                        </div>
                    </li>
                    <li class="list_sum">
                        <p class="sum_price">${drug.xiaoji}</p>
                    </li>
                  
                </ul>
                
                
                </c:forEach>
               
               
                <ul >
              
                    <li  >
                       <p class="sum_price" style="text-align: right;">合计:${order.sumPrice }</p>
                    </li>

                  
                </ul>
                
               
                
                
                </div>
        </div>

     </c:forEach>

 <!--底部--> 
           	<!-- 分页 -->
			<nav aria-label="Page navigation" style="text-align: center;">
				<ul class="pagination pagination-lg">
					<li ><a href="${basepath}/orderList?curPage=${page.prePage}" aria-label="Previous">
					
					<span aria-hidden="true">&laquo;</span></a></li>
				<!-- 	<li class="active">
						<a href="#">1 <span class="sr-only">(current)</span></a>
					</li>
					 -->
					 <c:forEach items="${page.pageList}" var="pageNum">
					 <!-- 绘制的页面是否和当前页一致 -->
					 <c:choose>
					  <c:when test="${pageNum==page.curPage}">
					   <li  class="active">
						<a href="${basepath}/orderList?curPage=${pageNum}">${pageNum}<span class="sr-only">(current)</span></a>
					</li>
					  </c:when>
					  <c:otherwise>
					  <li  >
						<a href="${basepath}/orderList?curPage=${pageNum}">${pageNum}<span class="sr-only">(current)</span></a>
					</li>
					  
					  </c:otherwise>
					 
					 </c:choose>
					
					</c:forEach>
					<!-- 下一页 -->
					 <li ><a href="${basepath}/orderList?curPage=${page.nextPage}" aria-label="Nextvious">
					
					<span aria-hidden="true">&raquo;</span></a></li>
				</ul>
			</nav>
    </section>

</body>
</html>

    