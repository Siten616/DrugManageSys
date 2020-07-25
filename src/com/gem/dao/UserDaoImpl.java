package com.gem.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gem.entity.User;
import com.gem.util.JDBCUtil;

public class UserDaoImpl implements UserDao {
	JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

	@Override
	public User selectUserByUsernameAndPasswordAndType(String username, String password, String type) {
		String sql = "select * from tb_user where username=? and password=? and type=?";
		List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class), username, password, type);
		if (users != null && users.size() > 0)
			return users.get(0);
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		String sql = "insert into tb_user values(null,?,?,?)";
		int count = template.update(sql, user.getUsername(), user.getPassword(), user.getType());
		return count > 0;
	}

	@Override
	public List<User> selectUserByType(String type) {
		String sql = "select * from tb_user where type=?";
		List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class), type);
		return users;
	}

}
