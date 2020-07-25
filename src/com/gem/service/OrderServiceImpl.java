package com.gem.service;

import java.util.List;
import java.util.UUID;

import com.gem.dao.DrugDao;
import com.gem.dao.OrderDao;
import com.gem.entity.*;
import com.gem.util.BeanFactory;

public class OrderServiceImpl implements OrderService {
	private DrugDao drugDao = (DrugDao) BeanFactory.getBean("com.gem.dao.DrugDaoImpl");
	private OrderDao orderDao = (OrderDao) BeanFactory.getBean("com.gem.dao.OrderDaoImpl");

	@Override
	public boolean buy(int uid, List<Car> car) {
		boolean flag = true;
		String oid = UUID.randomUUID().toString().replace("-", "");
		Order order = new Order(oid, uid);
		double sumPrice = 0;
		for (int i = 0; i < car.size(); i++) {
			Car car1 = car.get(i);
			flag &= orderDao.insertOrderDetail(oid, car1.getDid(), car1.getNum());
			Drug drug = drugDao.selectDrugById(car1.getDid());
			if ((drug.getStock() - car1.getNum()) >= 0) {
				drug.setStock(drug.getStock() - car1.getNum());
				drugDao.updateDrug(drug);
			} else {
				return false;
			}
			sumPrice += drug.getPrice() * car1.getNum();
		}
		order.setSumPrice(sumPrice);
		flag &= orderDao.insertOrder(order);
		return flag;
	}

	@Override
	public boolean buyRight(int uid, String did) {
		String oid = UUID.randomUUID().toString().replace("-", "");
		Order order = new Order(oid, uid);
		Drug drug = drugDao.selectDrugById(did);
		order.setSumPrice(drug.getPrice());
		boolean flag1 = orderDao.insertOrder(order);
		boolean flag2 = orderDao.insertOrderDetail(oid, did, 1);
		return flag1 & flag2;
	}

	@Override
	public PageInfor<Order> getOrderList(int uid, int curPage, int pageNum) {
		List<Order> orders = orderDao.selectByUid(uid, curPage, pageNum);
		for (int i = 0; i < orders.size(); i++) {
			List<OrderDetail> details = orderDao.selectDetailByOid(orders.get(i).getOid());
			orders.get(i).setDetails(details);
		}
		int totalNum = orderDao.orderCount(uid);
		PageInfor<Order> pageInfor = new PageInfor<Order>(orders, curPage, pageNum, totalNum);
		return pageInfor;
	}

}
