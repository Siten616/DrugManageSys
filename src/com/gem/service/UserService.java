package com.gem.service;

import com.gem.entity.User;

public interface UserService {
	//登录
	User login(String username, String password, String type);

	//注册
	boolean register(User user);

}
