package com.hospital.register.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.hospital.register.bean.Bonus;
import com.hospital.register.bean.BonusDetail;
import com.hospital.register.bean.BonusExample;
import com.hospital.register.dao.BonusDetailMapper;
import com.hospital.register.dao.BonusMapper;
import com.hospital.register.service.BonusService;
import com.hospital.register.vo.BonusDetailVO;
import com.hospital.register.vo.UserBonusVO;

@Component
public class BonusServiceImpl implements BonusService {
	private static final Logger logger = LoggerFactory.getLogger(BonusServiceImpl.class);
	
	@Autowired
	private BonusMapper bonusMapper;
	
	@Autowired
	private BonusDetailMapper bonusDetailMapper;

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

    @Override
    public void addBonusDetail(BonusDetail bonusDetail) {
        bonusDetailMapper.insert(bonusDetail);
    }

    @Override
    public List<Bonus> queryBonusByCondition(Bonus bonus) {
        BonusExample bex = new BonusExample();
        bex.createCriteria().andBonusIdEqualTo(bonus.getUserId());
        return bonusMapper.selectByExample(bex);
    }

    @Override
    public void addBonus(Bonus bonus) {
        bonusMapper.insert(bonus);
    }

    @Override
    public void updateBonus(Bonus bonus) {
        bonusMapper.updateByPrimaryKey(bonus);
    }
	
	@Override
	public long countBonusDetail(Map<String, Object> paramsMap) throws Exception {
		logger.info("countBonusDetail");
		return bonusDetailMapper.countBonusDetail(paramsMap);
	}

	@Override
	public List<BonusDetailVO> selectBonusDetail(Map<String, Object> paramsMap) throws Exception {
		logger.info("selectBonusDetail");
		int currentPage = 1;
		int pageSize = 100;
		if(paramsMap.containsKey("currentPage")&&paramsMap.containsKey("pageSize")) {
			currentPage = (int)paramsMap.get("currentPage");
			pageSize = (int)paramsMap.get("pageSize");
		}
		PageHelper.startPage(currentPage, pageSize);
		return bonusDetailMapper.selectBonusDetail(paramsMap);
	}



	
	
}
