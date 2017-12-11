package com.hospital.register.controller;

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

import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.service.UserService;
import com.hospital.register.util.PasswordHelper;
import com.hospital.register.util.RestResponse;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Value("${user.default.password:!qazXsw2}")
	private String defaultPassword;

	@Autowired
	private UserService userService;

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
	RestResponse userRegister(String openId, String userName, String realName, int sex,
			long birthday,String telephone) {
		logger.info("userRegister,openId={}", openId);
		try {
			User user = new User();
			user.setUserName(userName);
			user.setPassword(PasswordHelper.encryptPassword(user.getUserName(),defaultPassword));
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
			logger.error("用户注册出错,e={}",e);
			return RestResponse.errorRes("用户注册出错");
		}
	}

}
