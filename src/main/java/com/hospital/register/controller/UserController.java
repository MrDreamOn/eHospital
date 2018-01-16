package com.hospital.register.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.hospital.register.annotation.OperateLogs;
import com.hospital.register.bean.Subscription;
import com.hospital.register.bean.SubscriptionExample;
import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.conditionVO.UserVO;
import com.hospital.register.service.SubscriptionService;
import com.hospital.register.service.UserService;
import com.hospital.register.util.PasswordHelper;
import com.hospital.register.util.PhoneFormatCheckUtils;
import com.hospital.register.util.RestResponse;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Value("${user.default.password:!qazXsw2}")
	private String defaultPassword;

	@Autowired
	private UserService userService;
	
	@Autowired
	private SubscriptionService subscriptionService;

	@RequestMapping(value = "/hasUser", method = RequestMethod.POST)
	@ResponseBody
	RestResponse hasUser(String openId) {
		logger.info("hasUser,openid={}", openId);
		UserExample example = new UserExample();
		example.createCriteria().andOpenIdEqualTo(openId.trim());
		List<User> users = userService.findUsersByCondition(example);
		if (CollectionUtils.isNotEmpty(users)) {
			return RestResponse.successResWithTokenData(users.get(0), "YGdykliy_+@124LK/");
		}
		return RestResponse.errorRes("未找到用户!");
	}

	@RequestMapping(value = "/isBindContact", method = RequestMethod.POST)
	@ResponseBody
	RestResponse isBindContact(String contact) {
		logger.info("isBindContact,contact={}", contact);
		UserExample example = new UserExample();
		example.createCriteria().andTelephoneEqualTo(contact.trim());
		List<User> users = userService.findUsersByCondition(example);
		Map<String, String> resultMap = new HashMap<String, String>();
		if (CollectionUtils.isNotEmpty(users)) {
			resultMap.put("isBind", "1");
			return RestResponse.successResWithData(resultMap);
		} else {
			resultMap.put("isBind", "0");
			return RestResponse.successResWithData(resultMap);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	RestResponse userRegister(String openId, String userName, String realName, int sex, long birthday,
			String telephone) {
		logger.info("userRegister,openId={}", openId);
		try {
			User user = new User();
			user.setUserName(userName);
			user.setPassword(PasswordHelper.encryptPassword(user.getUserName(), defaultPassword));
			user.setRealName(realName);
			user.setSex(sex);
			user.setTelephone(telephone);
			user.setBirthday(new Date(birthday));
			user.setOpenId(openId);
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			userService.updateRegister(user);
			return RestResponse.successResWithTokenData("", "YGdykliy_+@124LK/");
		} catch (Exception e) {
			logger.error("用户注册出错,e={}", e);
			return RestResponse.errorRes("用户注册出错");
		}
	}

	@RequestMapping(value = "/getUserByCondition", method = RequestMethod.POST)
	@ResponseBody
	@OperateLogs(operateInfo="查询用户信息")
	RestResponse getUser(String condition,Integer currentPage, Integer pageSize) {
		logger.info("getUser info,condition:{},currentPage:{},pageSize:{}", condition,currentPage, pageSize);
		if(StringUtils.isEmpty(condition)) {
			return RestResponse.errorRes("用户名或者手机号不能为空");
		}
		List<UserVO> resultList = new ArrayList<UserVO>();
		UserExample example = new UserExample();
		try {
			
			if(PhoneFormatCheckUtils.isPhoneLegal(condition)) {
				example.createCriteria().andTelephoneEqualTo(condition);
			}else {
				example.createCriteria().andRealNameEqualTo(condition);
			}
			long count = userService.countUsers(example);
			if(count > 0) {
				List<User> userList = userService.findUsersByPage(example, currentPage, pageSize);
				SubscriptionExample subExample = new SubscriptionExample();
				for(User item : userList){
					subExample.clear();
					UserVO vo = new UserVO();
					vo.setUserId(item.getUserId());
					vo.setRealName(item.getRealName());
					vo.setSex(item.getSex());
					vo.setTelphone(item.getTelephone());
					vo.setBrithday(item.getBirthday());
					subExample.createCriteria().andUserIdEqualTo(item.getUserId()).andSubscriptionStatusEqualTo(6);
					subExample.setOrderByClause("subscription_date DESC ");
					List<Subscription> subList = subscriptionService.querySubscription(subExample);
					Date lastSuccessDate = null;
					if(CollectionUtils.isNotEmpty(subList)) {
						lastSuccessDate = subList.get(0).getUpdateTime();
					}
					vo.setLastSuccessDate(lastSuccessDate);
					resultList.add(vo);
				}
			}
			return RestResponse.successRes4Find(resultList, Integer.parseInt(count + ""));
		} catch (Exception e) {
			logger.error("查询用户信息出错,e={}", e);
			return RestResponse.errorRes("查询用户信息出错");
		}
	}

}
