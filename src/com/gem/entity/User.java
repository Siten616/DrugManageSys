package com.gem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private Integer uid;
	private String username;
	private String password;
	private String type;
}
