package com.hospital.register.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.dao.UserMapper;
import com.hospital.register.service.UserService;

//@Service(value = "userService")
@Component
public class UserServiceImpl implements UserService{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
    private UserMapper userMapper;
	
	@Override
	public int addUser(User user) {
		logger.info("add user");
		return userMapper.insert(user);
	}

	@Override
	public List<User> findAllUser(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return userMapper.selectAllUser();
	}

	@Override
	public User findUserByName(String userName) {
		if(StringUtils.isEmpty(userName)) {
			return new User();
		}
		UserExample example = new UserExample();
		example.createCriteria().andUserNameEqualTo(userName.trim());
		List<User> results = userMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(results)) {
			return results.get(0);
		}
		return new User();
	}

	@Override
	public List<User> findUsersByCondition(UserExample example) {
		List<User> results = userMapper.selectByExample(example);
		for(User item : results) {
			item.setPassword(null);
		}
		return results;
	}
	
}
