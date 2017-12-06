package com.hospital.register.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hospital.register.bean.Permission;
import com.hospital.register.dao.PermissionMapper;
import com.hospital.register.service.PermissionService;

@Component
public class PermissionServiceImpl implements PermissionService {
	
	private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

	@Autowired
    private PermissionMapper permissionMapper;
	
	@Override
	public List<Permission> getPermissionsByRoleId(Integer roleId) {
		logger.info("get Permissions,roleId={}",roleId);
		return permissionMapper.selectByRoleId(roleId);
	}

}
