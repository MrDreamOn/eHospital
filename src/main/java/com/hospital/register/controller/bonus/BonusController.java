package com.hospital.register.controller.bonus;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.hospital.register.bean.BonusDetail;
import com.hospital.register.bean.BonusDetailExample;
import com.hospital.register.bean.User;
import com.hospital.register.conditionVO.UserVO;
import com.hospital.register.service.BonusService;
import com.hospital.register.service.UserService;
import com.hospital.register.util.PhoneFormatCheckUtils;
import com.hospital.register.util.RestResponse;
import com.hospital.register.vo.BonusDetailVO;
import com.hospital.register.vo.UserBonusVO;

@Controller
@RequestMapping(value = "/bonus")
public class BonusController {
	private static final Logger logger = LoggerFactory.getLogger(BonusController.class);
	
	@Autowired
	private BonusService bonusService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getBonusByCondition", method = RequestMethod.POST)
	@ResponseBody
	RestResponse getBonusByCondition(String condition,Integer currentPage, Integer pageSize) {
		logger.info("get Bonus info,condition:{},currentPage:{},pageSize:{}", condition,currentPage, pageSize);
		if(StringUtils.isEmpty(condition)) {
			return RestResponse.errorRes("用户名或者手机号不能为空");
		}
		List<UserBonusVO> userBonusList = new ArrayList<UserBonusVO>();
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		try {
			
			if(PhoneFormatCheckUtils.isPhoneLegal(condition)) {
				paramsMap.put("telephone", condition);
			}else {
				paramsMap.put("realName", condition);
			}
			long count = bonusService.countByCondition(paramsMap);
			if(count > 0) {
				paramsMap.put("currentPage", currentPage);
				paramsMap.put("pageSize", pageSize);
				userBonusList = bonusService.selectUserBonusByCondition(paramsMap);
			}
			return RestResponse.successRes4Find(userBonusList, Integer.parseInt(count + ""));
		} catch (Exception e) {
			logger.error("查询用户积分信息出错,e={}", e);
			return RestResponse.errorRes("查询用户积分信息出错");
		}
	}
	
	
	@RequestMapping(value = "/queryBonusDetail", method = RequestMethod.POST)
	@ResponseBody
	RestResponse queryBonusDetail(Integer userId,Date startTime,Date endTime,Integer currentPage, Integer pageSize) {
		logger.info("get Bonus info,userId:{},currentPage:{},pageSize:{},startTime={},endTime={}", userId,currentPage, pageSize,startTime,endTime);
		List<BonusDetailVO> bonusDetailList = new ArrayList<BonusDetailVO>();
		List<UserVO> resultList = new ArrayList<UserVO>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			User user = userService.findUserByPrimaryKey(userId);
			UserVO vo = new UserVO();
			vo.setUserId(user.getUserId());
			vo.setRealName(user.getRealName());
			vo.setSex(user.getSex());
			vo.setTelphone(user.getTelephone());
			vo.setBrithday(user.getBirthday());
			vo.setCreateTime(user.getCreateTime());
			resultList.add(vo);
			resultMap.put("memberInfo", resultList);
			
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("userId", userId);
			if(startTime != null && endTime != null) {
				paramsMap.put("startTime", startTime);
				paramsMap.put("endTime", endTime);
			}
			long count = bonusService.countBonusDetail(paramsMap);
			if(count > 0) {
				paramsMap.put("currentPage", currentPage);
				paramsMap.put("pageSize", pageSize);
				bonusDetailList = bonusService.selectBonusDetail(paramsMap);
			}
			resultMap.put("bonusList", bonusDetailList);
			return RestResponse.successRes4Find(resultMap, Integer.parseInt(count + ""));
		} catch (Exception e) {
			logger.error("查询用户积分明细信息出错,e={}", e);
			return RestResponse.errorRes("查询用户积分明细信息出错");
		}
	}
	
	
	@RequestMapping(value = "/queryBonusList", method = RequestMethod.POST)
	@ResponseBody
	RestResponse queryBonusList(HttpServletRequest request){
		logger.info("queryBonusList");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<BonusDetailVO> bonusDetailList = new ArrayList<BonusDetailVO>();
		try {
			String userId = request.getParameter("userId");
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("userId", userId);
			long count = bonusService.countBonusDetail(paramsMap);
			int totalPoints = 0;
			if(count > 0) {
				bonusDetailList = bonusService.selectBonusDetail(paramsMap);
				totalPoints = bonusDetailList.get(0).getTotalPoints();
			}
			resultMap.put("totalPoints", totalPoints);
			}
			resultMap.put("bonusList", bonusDetailList);
			return RestResponse.successRes4Find(resultMap, Integer.parseInt(count + ""));
		} catch (Exception e) {
			logger.error("查询用户积分明细信息出错,e={}", e);
			return RestResponse.errorRes("查询用户积分明细信息出错");
		}
	}
	
	@RequestMapping(value = "/queryPayInfo", method = RequestMethod.POST)
	@ResponseBody
	RestResponse queryPayInfo(HttpServletRequest request){
		logger.info("queryPayInfo");
		try {
			String subId = request.getParameter("subId");
			BonusDetailExample bex = new BonusDetailExample();
	        bex.createCriteria().andSubscriptionIdEqualTo(Integer.parseInt(subId));
	        List<BonusDetail> detailList = bonusService.queryBonusDetailByExample(bex);
	        if(CollectionUtils.isEmpty(detailList)) {
	        	return RestResponse.errorRes("支付信息为空");
	        }
	        return RestResponse.successResWithData(detailList.get(0));
		} catch (Exception e) {
			logger.error("查询支付明细信息出错,e={}", e);
			return RestResponse.errorRes("查询支付明细信息出错");
		}
	}
}
