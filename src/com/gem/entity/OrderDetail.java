package com.gem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetail {
	private int gid;
	private int num;
	private String dname;
	private String address;
	private double price;
	private double xiaoji;
}
