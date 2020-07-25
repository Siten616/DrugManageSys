package com.gem.dao;

import java.util.List;

import com.gem.entity.Order;
import com.gem.entity.OrderDetail;

public interface OrderDao {
	//添加订单
	boolean insertOrder(Order order);

	//店家订单详情
	boolean insertOrderDetail(String oid, String did, int num);

	//查看订单
	List<Order> selectByUid(int uid, int curPage, int pageNum);

	//查看订单里面的商品
	List<OrderDetail> selectDetailByOid(String oid);

	//用户有多少个订单
	int orderCount(int uid);
}
