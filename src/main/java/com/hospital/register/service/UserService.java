package com.hospital.register.service;

import java.util.List;

import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;

public interface UserService {
	int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);
    
    /**
     * 通过用户名查找用户信息
     * @param userName
     * @return
     */
    User findUserByName(String userName);
    
    List<User> findUsersByCondition(UserExample example);
    
    boolean isSubscribe(String openid);
    
    boolean isRegister(String openid);
    
    int updateRegister(User user);
    
    int updateUserByPrimaryKey(User user);
}
