package com.hospital.register.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.bean.User;
import com.hospital.register.bean.UserExample;
import com.hospital.register.service.UserService;
import com.hospital.register.util.RestResponse;

@Controller
@RequestMapping(value="/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
    private UserService userService;
	
	@RequestMapping(value="/hasUser",method=RequestMethod.POST)
    @ResponseBody
    RestResponse hasUser(String openId) {
		logger.info("hasUser,openid={}",openId);
		UserExample example = new UserExample();
		example.createCriteria().andOpenIdEqualTo(openId.trim());
		List<User> users = userService.findUsersByCondition(example);
		if(CollectionUtils.isNotEmpty(users)) {
			return RestResponse.successResWithData(users.get(0));
		}
        return RestResponse.errorRes("未找到用户!");
    }
	

}
