package com.hospital.register.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hospital.register.bean.User;
import com.hospital.register.context.Context;
import com.hospital.register.service.UserService;
import com.hospital.register.util.PasswordHelper;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    
    @Autowired
    private UserService userService;

	@RequestMapping("/hello")
    @ResponseBody
    Object home() {
		logger.info("hello");
        return Context.USER_MAP;
    }
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		logger.info("login");
        return "login";
    }
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String userName,String password) {
		logger.info("login,userName={}",userName);
        return "index";
    }
	
	@RequestMapping("/logout")
	@ResponseBody
	public Object logout() {
		logger.info("logout");
		User user = new User();
		user.setUserName("admin");
		user.setPassword(PasswordHelper.encryptPassword(user.getUserName(),"!qazXsw2"));
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		System.out.println("password:"+user.getPassword());
		userService.addUser(user);
        return Context.USER_MAP;
    }
	
	@RequiresRoles("admin")
	@RequestMapping("/freemarker")
    public String freemarker(Map<String, Object> map) {
		logger.info("freemarker");
        map.put("name", "[Angel -- 守护天使]");
        map.put("gender", 1);//gender:性别，1：男；0：女；  
        
        List<Map<String, Object>> friends = new ArrayList<Map<String, Object>>();
        Map<String, Object> friend = new HashMap<String, Object>();
        friend.put("name", "DD");
        friend.put("age", 28);
        friends.add(friend);
        friend = new HashMap<String, Object>();
        friend.put("name", "cai");
        friend.put("age", 27);
        friends.add(friend);
        map.put("friends", friends);
        return "freemarker2";
    }
}
