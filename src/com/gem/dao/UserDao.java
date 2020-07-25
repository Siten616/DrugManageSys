package com.gem.dao;

import java.util.List;

import com.gem.entity.User;

public interface UserDao {
	//根据用户名，密码，类型查找用户
	User selectUserByUsernameAndPasswordAndType(String username, String password, String type);

	//添加用户（操作员、管理员）
	boolean insertUser(User user);

	//获得所有同类型的用户
	List<User> selectUserByType(String type);
}
