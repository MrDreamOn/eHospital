package com.hospital.register.service;

import java.util.List;

import com.hospital.register.bean.Role;

public interface RoleService {
	List<Role> getRolesByUserId(Integer userId);
}
