package com.hospital.register.service;

import java.util.List;

import com.hospital.register.bean.Permission;

public interface PermissionService {
	List<Permission> getPermissionsByRoleId(Integer roleId);
}
