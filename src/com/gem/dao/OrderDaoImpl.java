package com.gem.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gem.entity.Order;
import com.gem.entity.OrderDetail;
import com.gem.util.JDBCUtil;

public class OrderDaoImpl implements OrderDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

	@Override
	public boolean insertOrder(Order order) {
		String sql = "insert into tb_order values(?,?,now(),?)";
		int count = template.update(sql, order.getOid(), order.getUid(), order.getSumPrice());
		return count > 0;
	}

	@Override
	public boolean insertOrderDetail(String oid, String did, int num) {
		String sql = "insert into tb_orderdetail values(null,?,?,?)";
		int count = template.update(sql, oid, did, num);
		return count > 0;
	}

	@Override
	public List<Order> selectByUid(int uid, int curPage, int pageNum) {
		String sql = "select * from tb_order where uid=? order by createTime desc limit ?,?";
		List<Order> orders = template.query(sql, new BeanPropertyRowMapper<Order>(Order.class), uid,
				(curPage - 1) * pageNum, pageNum);
		return orders;
	}

	@Override
	public List<OrderDetail> selectDetailByOid(String oid) {
		String sql = "select oid,tb_drug.did,dname,address,production_date,deadline,price,tb_orderdetail.num,price*tb_orderdetail.num xiaoji from tb_orderdetail LEFT JOIN tb_drug ON tb_drug.did=tb_orderdetail.did where oid=?";
		List<OrderDetail> orderDetails = template.query(sql, new BeanPropertyRowMapper<OrderDetail>(OrderDetail.class),
				oid);
		return orderDetails;
	}

	@Override
	public int orderCount(int uid) {
		String sql = "select count(oid) from tb_order where uid=?";
		int count = template.queryForObject(sql, Integer.class, uid);
		return count;
	}

}
