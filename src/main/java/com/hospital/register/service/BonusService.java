package com.hospital.register.service;

import java.util.List;
import java.util.Map;

import com.hospital.register.vo.BonusDetailVO;
import com.hospital.register.bean.Bonus;
import com.hospital.register.bean.BonusDetail;
import com.hospital.register.vo.UserBonusVO;

public interface BonusService {

	public long countByCondition(Map<String,Object> paramsMap) throws Exception;
	
	public List<UserBonusVO> selectUserBonusByCondition(Map<String,Object> paramsMap) throws Exception;
	
	
	public long countBonusDetail(Map<String,Object> paramsMap) throws Exception;
	
	public List<BonusDetailVO> selectBonusDetail(Map<String,Object> paramsMap) throws Exception;
	public void addBonusDetail(BonusDetail bonusDetail);
	
	public List<Bonus> queryBonusByCondition(Bonus bonus);
	
	public void addBonus(Bonus bonus);
	
	public void updateBonus(Bonus bonus);
}
