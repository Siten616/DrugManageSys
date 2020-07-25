package com.gem.service;

import java.util.List;

import com.gem.entity.Car;
import com.gem.entity.Order;
import com.gem.entity.PageInfor;

public interface OrderService {
	//购物车购买
	boolean buy(int uid, List<Car> car);

	//点击立即购买一个
	boolean buyRight(int uid, String did);

	//查看订单
	PageInfor<Order> getOrderList(int uid, int curPage, int pageNum);
}
