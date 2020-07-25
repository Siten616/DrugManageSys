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
   
    <section class="cartMain " >
        <div class="cartMain_hd">
            <ul class="order_lists cartTop">
                <li class="list_chk">
                    <!--所有商品全选-->
                    <input type="checkbox" id="all" class="whole_check">
                    <label for="all"></label>
                  	全选
                </li>
                <li class="list_con">商品信息</li>
                <li class="list_info">商品参数</li>
                <li class="list_price">单价</li>
                <li class="list_amount">数量</li>
                <li class="list_sum">金额</li>
                <li class="list_op">操作</li>
            </ul>
        </div>

        <div class="cartBox">
            <div class="order_content">

              <c:forEach items="${drugs}" var="drug">
                <ul class="order_lists">
                    <li class="list_chk">
                        <input type="checkbox" id="${drug.did}" class="son_check">
                        <label for="${drug.did}" class="childCheck"></label>
                    </li>
                    <li class="list_con">
                        <div class="list_text"><a href="javascript:;">${drug.dname }</a></div>
                    </li>
                    <li class="list_info">
                        <p>${drug.address }</p>
                        <p>${drug.production_date }</p>
                        <p>${drug.deadline }</p>
                    </li>
                    <li class="list_price">
                        <p class="price">￥${drug.price}</p>
                      
                    </li>
                    <li class="list_amount mylist_num">
                        <div class="amount_box">
                            <a href="javascript:;" class="reduce reSty jian">-</a>
                            <!-- 商品号隐藏 -->
                            <input type="hidden"  class="did" value="${drug.did}">
                            <input type="text"  class="sum mynum" value="${drug.num}">
                            <a href="javascript:;" class="plus">+</a>
                        </div>
                    </li>
                    <li class="list_sum">
                        <p class="sum_price">￥${drug.sumPrice}</p>
                    </li>
                    <li class="list_op">
                        <p class="del"><a href="javascript:;" class="delBtn">移除商品</a></p>
                    </li>
                </ul>
               </c:forEach>
                </div>
        </div>
 <!--底部-->
        <div class="bar-wrapper">
            <div class="bar-right">
                <div class="piece">已选商品<strong class="piece_num">0</strong>件</div>
                <div class="totalMoney">共计: <strong class="total_text">0.00</strong></div>
                <div class="calBtn"><a href="javascript:void(0);" id="jiesuan">结算</a></div>
            </div>
        </div>
    </section>
    <section class="model_bg"></section>
    <section class="my_model">
        <p class="title">删除宝贝<span class="closeModel">X</span></p>
        <p>您确认要删除吗？</p>
        <div class="opBtn"><a href="javascript:;" class="dialog-sure">确定</a><a href="javascript:;" class="dialog-close">关闭</a></div>
    </section>
	 <input type="hidden" value="${basepath}" id="basePath">  
    <script src="${basepath}/js/jquery-3.5.1.js"></script>
    <script src="${basepath}/js/carts.js"></script>
    
   <script type="text/javascript">
    
      //js的函数
      function changNum(num,did) {
    	  //向服务发送请求,服务器servlet修改数据库,必须用ajax与服务器交互
    	  var path=$("#basePath").val();
    	  $.ajax({
    		  url:path+"/ChangeNumServlet",
    		  type:"POST",
    		  data:"num="+num+"&did="+did,
    		  success:function(rtn){
    			  //成功了
    		  },
    		  error:function(){
    			  //失败
    		  }
    	  });
	}
      
      //文本框修改后失去焦点
   	  $(".sum").on("blur",function(){
    	  var num= $(this).val();
    	  var did=$(this).prev().val();
    	  changNum(num,did);
      }); 
      //a标签的结算
      $("#jiesuan").click(function (){
    	  var path=$("#basePath").val();
    	  if($("#jiesuan").hasClass("btn_sty")){
	    	  var dids= $(".childCheck").filter(".mark");
	    	  var str="";
	    	  var nums=""
	    	  //lable前面的input
	    	  for(var i=0;i<dids.length-1;i++){
	    		  str+=dids.eq(i).prev().attr("id")+";";
	    		  //label
	    		  nums+= dids.eq(i).parent("li").nextAll(".mylist_num").children("div").children(".mynum").val()+";";
	    	  }
	    	  //最后一个无分号
	    	  str+=dids.eq(dids.length-1).prev().attr("id");
	    	  nums+= dids.eq(dids.length-1).parent("li").nextAll(".mylist_num").children("div").children(".mynum").val();
	    	  alert(str)
	    	  var path=$("#basePath").val();
	    	  //window.location.href=path+"/zhifu?dids="+str+"&nums="+nums;
	    	  window.location.href=path+"/buya?dids="+str+"&nums="+nums;
    	  }
	  });
      
    </script>
	
</body>
</html>
</html>
    