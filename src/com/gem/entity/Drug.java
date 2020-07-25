package com.gem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Drug {
	private String did;
	private String dname;
	private String address;
	private String production_date;
	private String deadline;
	private Double price;
	private Integer stock;
}
