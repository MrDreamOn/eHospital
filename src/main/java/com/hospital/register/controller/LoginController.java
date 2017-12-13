package com.hospital.register.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
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
	public String login(String userName,String password,
            HttpSession session) {
		logger.info("login,userName={}",userName);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
        		userName, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //在这里掉到了shiro中进行登录校验,如果权限校验没有通过就不会往下走
            subject.login(usernamePasswordToken); //完成登录
            User user = (User)subject.getPrincipal();
            session.setAttribute("user", user);
            
            return "redirect:/index";
        }
        catch (Exception e) {
            return "login";//返回登录页面
        }
    }
	
	@RequiresRoles("admin")
	@RequiresPermissions(value={"/index","modify"}, logical= Logical.OR)
	@RequestMapping(value="/index")
	public String index() {
		logger.info("index");
        return "index";
    }
	
	
	@RequestMapping(value="/indexAdmin")
	public String indexAdmin() {
		logger.info("indexAdmin");
        return "index_admin";
    }
	
	
	@RequestMapping("/403")
    public String unauthorizedRole(){
        logger.info("------没有权限-------");
        return "error";
    }
	
	@RequestMapping("/logout")
	@ResponseBody
	public Object logout() {
		logger.info("logout");
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
