package com.gem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
//购物车中商品的描述
public class CarDrugs {
	//商品表
	private String did;
	private String dname;
	private String address;
	private Double price;
	private String production_date;
	private String deadline;
	//购物车表
	private Integer num;
	private double sumPrice;

}
