package com.gem.service;

import java.util.List;

import com.gem.dao.UserDao;
import com.gem.entity.User;
import com.gem.util.BeanFactory;

public class UserServiceImpl implements UserService {

	UserDao userDao = (UserDao) BeanFactory.getBean("com.gem.dao.UserDaoImpl");

	@Override
	public User login(String username, String password, String type) {
		return userDao.selectUserByUsernameAndPasswordAndType(username, password, type);
	}

	@Override
	public boolean register(User user) {//待测试
		List<User> users = userDao.selectUserByType(user.getType());
		for (User user1 : users) {
			if (user.getUsername().equals(user1.getUsername()))
				return false;
		}
		return userDao.insertUser(user);
	}

}
