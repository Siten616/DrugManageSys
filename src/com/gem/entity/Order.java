package com.gem.entity;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
	private String oid;
	private int uid;
	private Date createTime;
	private double sumPrice;
	//一个订单有多个订单详情(多个商品)
	private List<OrderDetail> details;

	public Order(String oid, int uid) {
		super();
		this.oid = oid;
		this.uid = uid;

	}

}
