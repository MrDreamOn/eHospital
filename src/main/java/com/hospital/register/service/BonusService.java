package com.hospital.register.service;

import java.util.List;
import java.util.Map;

import com.hospital.register.vo.UserBonusVO;

public interface BonusService {

	public long countByCondition(Map<String,Object> paramsMap) throws Exception;
	
	public List<UserBonusVO> selectUserBonusByCondition(Map<String,Object> paramsMap) throws Exception;
}
