package com.hospital.register.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.hospital.register.dao.BonusMapper;
import com.hospital.register.service.BonusService;
import com.hospital.register.vo.UserBonusVO;

@Component
public class BonusServiceImpl implements BonusService {
	private static final Logger logger = LoggerFactory.getLogger(BonusServiceImpl.class);
	
	@Autowired
	private BonusMapper bonusMapper;

	@Override
	public long countByCondition(Map<String, Object> paramsMap) throws Exception {
		logger.info("countByCondition");
		return bonusMapper.countByCondition(paramsMap);
	}

	@Override
	public List<UserBonusVO> selectUserBonusByCondition(Map<String, Object> paramsMap) throws Exception {
		logger.info("selectUserBonusByCondition");
		int currentPage = 1;
		int pageSize = 100;
		if(paramsMap.containsKey("currentPage")&&paramsMap.containsKey("pageSize")) {
			currentPage = (int)paramsMap.get("currentPage");
			pageSize = (int)paramsMap.get("pageSize");
		}
		PageHelper.startPage(currentPage, pageSize);
		return bonusMapper.selectBonusByCondition(paramsMap);
	}

	
	
}
