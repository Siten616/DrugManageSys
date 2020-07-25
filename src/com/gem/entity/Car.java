package com.gem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
	private Integer cid;
	private Integer uid;
	private String did;
	private Integer num;
}
