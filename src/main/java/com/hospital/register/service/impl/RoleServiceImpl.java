package com.hospital.register.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospital.register.bean.Role;
import com.hospital.register.dao.RoleMapper;
import com.hospital.register.service.RoleService;

@Component
public class RoleServiceImpl implements RoleService {
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	 
	@Autowired
    private RoleMapper roleMapper;
	
	@Override
	public List<Role> getRolesByUserId(Integer userId) {
		logger.info("get roles,userId={}",userId);
		return roleMapper.selectByUserId(userId);
	}

}
